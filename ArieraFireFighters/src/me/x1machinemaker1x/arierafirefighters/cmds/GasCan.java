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

import me.x1machinemaker1x.arierafirefighters.utils.ConfigManager.Config;
import me.x1machinemaker1x.arierafirefighters.utils.Messages;
import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class GasCan implements CommandExecutor {
	
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			cs.sendMessage(Messages.PREFIX.toString() + Messages.NOT_PLAYER.toString());
			return true;
		}
		Player p = (Player) cs;
		if (!p.hasPermission("ff.arsonist")) {
			cs.sendMessage(Messages.PREFIX.toString() + Messages.NO_PERMISSION.toString());
			return true;
		}
		for (ItemStack i: p.getInventory().getContents()) { //check if player has a gas can already
			net.minecraft.server.v1_12_R1.ItemStack is = CraftItemStack.asNMSCopy(i);
			if (is.hasTag())
				if (is.getTag().hasKey("gascan"))
					if (is.getTag().getBoolean("gascan")) {
						p.sendMessage(Messages.PREFIX.toString() + Messages.ALREADY_HAVE_GC.toString());
						return true;
					}
		}
		ItemStack gasCan = new ItemStack(Material.GOLD_HOE, 1, (short) (Material.GOLD_HOE.getMaxDurability()-1));
		net.minecraft.server.v1_12_R1.ItemStack gC = CraftItemStack.asNMSCopy(gasCan);
		NBTTagCompound tag = (gC.getTag() == null) ? new NBTTagCompound() : gC.getTag();
		tag.setBoolean("gascan", true);
		gC.setTag(tag);
		gasCan = CraftItemStack.asCraftMirror(gC);
		ItemMeta meta = gasCan.getItemMeta();
		meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.GOLD + "Gas Can");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RESET.toString() + ChatColor.WHITE + "Use this can to pour gasoline on the ground.");
		lore.add(ChatColor.RESET.toString() + ChatColor.AQUA + "Uses left: " + ChatColor.UNDERLINE + Config.GAS_USES.def());
		meta.setLore(lore);
		gasCan.setItemMeta(meta);
		p.getInventory().addItem(gasCan);
		return true;
	}
}