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

public class Extinguisher implements CommandExecutor {
	
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			cs.sendMessage(Messages.PREFIX.toString() + Messages.NOT_PLAYER.toString());
			return true;
		}
		Player p = (Player) cs;
		if (!p.hasPermission("aff.firefighter")) {
			p.sendMessage(Messages.PREFIX.toString() + Messages.NO_PERMISSION.toString());
			return true;
		}
		for (ItemStack i : p.getInventory().getContents()) {
			net.minecraft.server.v1_12_R1.ItemStack is = CraftItemStack.asNMSCopy(i);
			if (is.hasTag())
				if (is.getTag().hasKey("extinguisher"))
					if (is.getTag().getBoolean("extinguisher")) {
						p.sendMessage(Messages.PREFIX.toString() + Messages.ALREADY_HAVE_EX.toString());
						return true;
					}
		}
		ItemStack ex = new ItemStack(Material.GOLD_HOE, 1, (short) (Material.GOLD_HOE.getMaxDurability() - 3));
		net.minecraft.server.v1_12_R1.ItemStack exNMS = CraftItemStack.asNMSCopy(ex);
		NBTTagCompound tag = (exNMS.getTag() == null) ? new NBTTagCompound() : exNMS.getTag();
		tag.setBoolean("extinguisher", true);
		exNMS.setTag(tag);
		ex = CraftItemStack.asBukkitCopy(exNMS);
		ItemMeta meta = ex.getItemMeta();
		meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.GOLD + "Extinguisher");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RESET.toString() + ChatColor.WHITE + "Use this to put out fires");
		meta.setLore(lore);
		ex.setItemMeta(meta);
		p.getInventory().addItem(ex);
		return true;
	}

}
