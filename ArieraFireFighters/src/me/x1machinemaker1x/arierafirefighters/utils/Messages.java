package me.x1machinemaker1x.arierafirefighters.utils;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public enum Messages {
	
	PREFIX("prefix", "&4[&7AFF&4] &e"),
	NOT_PLAYER("not-player", "&cYou must be a player to use that command!"),
	NO_PERMISSION("no-permission", "&cYou do not have permission!"),
	ALREADY_HAVE_GC("already-have-gc", "&cYou already have a gas can!"),
	ALREADY_HAVE_LT("already-have-lt", "&cYou already have a lighter!");
	
	String path;
	String message;
	
	private Messages(String path, String def) {
		this.path = path;
		this.message = def;
	}
	
	public String path() {
		return this.path;
	}
	
	public String message() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	private static File mfile;
	private static FileConfiguration mconfig;
	public static void setup(Plugin p) {
		mfile = new File(p.getDataFolder(), "messages.yml");
		if (!mfile.exists()) {
			try {
				p.getDataFolder().mkdir();
				mfile.createNewFile();
			} catch (Exception e) {
				p.getLogger().severe("Could not create messages.yml!");
				return;
			}
		}
		mconfig = YamlConfiguration.loadConfiguration(mfile);
		for (Messages message : Messages.values()) {
			if (!mconfig.isSet(message.path()))
				mconfig.set(message.path, message.message());
			else
				if (!mconfig.getString(message.path()).equals(message.message()))
					message.setMessage(mconfig.getString(message.path()));
		}
		try {
			mconfig.save(mfile);
		} catch (Exception e) {
			p.getLogger().severe("Could not save messages.yml");
		}
	}
	
	public static void reloadMessages() {
		mconfig = YamlConfiguration.loadConfiguration(mfile);
	}
	
	@Override
	public String toString() {
		return ChatColor.translateAlternateColorCodes('&', this.message());
	}
}