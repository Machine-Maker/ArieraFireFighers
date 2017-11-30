package me.x1machinemaker1x.arierafirefighters.cmds;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.x1machinemaker1x.arierafirefighters.utils.Messages;
import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class Lighter implements CommandExecutor {
	
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			cs.sendMessage(Messages.PREFIX.toString() + Messages.NOT_PLAYER.toString());
			return true;
		}
		Player p = (Player) cs;
		if (!p.hasPermission("ff.arsonist")) {
			p.sendMessage(Messages.PREFIX.toString() + Messages.NO_PERMISSION.toString());
			return true;
		}
		for (ItemStack i : p.getInventory().getContents()) {
			net.minecraft.server.v1_12_R1.ItemStack item = CraftItemStack.asNMSCopy(i);
			if (item.hasTag())
				if (item.getTag().hasKey("lighter"))
					if (item.getTag().getBoolean("lighter")) {
						p.sendMessage(Messages.PREFIX.toString() + Messages.ALREADY_HAVE_LT.toString());
						return true;
					}
		}
		ItemStack lighter = new ItemStack(Material.GOLD_HOE, 1, (short) (Material.GOLD_HOE.getMaxDurability() - 2));
		net.minecraft.server.v1_12_R1.ItemStack lt = CraftItemStack.asNMSCopy(lighter);
		NBTTagCompound tag = (lt.getTag() == null) ? new NBTTagCompound() : lt.getTag();
		tag.setBoolean("lighter", true);
		lt.setTag(tag);
		lighter = CraftItemStack.asBukkitCopy(lt);
		ItemMeta meta = lighter.getItemMeta();
		meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.GOLD + "Lighter");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RESET.toString() + ChatColor.WHITE + "Use this to start fires on gasoline");
		meta.setLore(lore);
		lighter.setItemMeta(meta);
		p.getInventory().addItem(lighter);
		return true;
	}

}
