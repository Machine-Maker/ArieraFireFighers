package me.x1machinemaker1x.arierafirefighters.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import me.x1machinemaker1x.arierafirefighters.objects.Gas;
import me.x1machinemaker1x.arierafirefighters.utils.GasManager;
import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class PlayerInteract implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
		if (e.getItem() == null) return;
		if (e.getItem().getType() != Material.GOLD_HOE) return;
		net.minecraft.server.v1_12_R1.ItemStack item = CraftItemStack.asNMSCopy(e.getItem());
		if (item.hasTag()) {
			NBTTagCompound tag = item.getTag();
			if (tag.hasKey("gascan")) {
				if (tag.getBoolean("gascan")) {
					Block b = e.getPlayer().getLocation().getBlock();
					if ((b.getType() == Material.CARPET && DyeColor.getByWoolData(b.getData()) == DyeColor.BLACK) || (b.getType() != Material.AIR)) return;
					if (b.getRelative(BlockFace.DOWN).getType() == Material.AIR || !b.getRelative(BlockFace.DOWN).getType().isSolid()) return;
					ItemMeta meta = e.getItem().getItemMeta();
					List<String> lore = meta.getLore();
					int uses = Integer.parseInt(ChatColor.stripColor(lore.get(1)).split(": ")[1]);
					if (uses > 0) {
						b.setType(Material.CARPET);
						GasManager.getInstance().addGas(b.getLocation(), System.currentTimeMillis());
						b.setData(DyeColor.BLACK.getWoolData());
						uses --;
						lore.set(1, ChatColor.RESET.toString() + ChatColor.AQUA + "Uses left: " + ChatColor.UNDERLINE + uses);
						meta.setLore(lore);
						e.getItem().setItemMeta(meta);
					}
				}
			}
			else if (tag.hasKey("lighter")) {
				if (tag.getBoolean("lighter")) {
					Location clickedLoc = e.getClickedBlock().getLocation();
					Gas gas = GasManager.getInstance().getGas(clickedLoc);
					if (gas == null) return;
					clickedLoc.getBlock().setType(Material.FIRE);
					List<BlockFace> blockfaces = new ArrayList<BlockFace>();
					blockfaces.add(BlockFace.EAST); blockfaces.add(BlockFace.NORTH); blockfaces.add(BlockFace.NORTH_EAST); blockfaces.add(BlockFace.NORTH_WEST); 
					blockfaces.add(BlockFace.SOUTH); blockfaces.add(BlockFace.SOUTH_EAST); blockfaces.add(BlockFace.SOUTH_WEST); blockfaces.add(BlockFace.WEST);
					e.getItem().setType(Material.AIR);
					for (BlockFace face : blockfaces) {
						Gas oGas = GasManager.getInstance().getGas(clickedLoc.getBlock().getRelative(face).getLocation());
						if (oGas != null) {
							oGas.getLoc().getBlock().setType(Material.FIRE);
						}
					}
				}
			}
		}
	}
}