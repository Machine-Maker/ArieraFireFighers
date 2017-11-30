package me.x1machinemaker1x.arierafirefighters.objects;

import org.bukkit.Location;

import me.x1machinemaker1x.arierafirefighters.utils.ConfigManager.Config;

public class Gas {
	
	Location loc = null;
	Long timePlaced = null;
	
	public Gas(Location loc, long timePlaced) {
		this.loc = loc;
		this.timePlaced = timePlaced;
	}
	
	public Location getLoc() {
		return loc;
	}
	
	public boolean isExpired() {
		return (timePlaced + ((Integer) Config.GAS_TIME_BEFORE_VANISH.def()) * 1000)  < System.currentTimeMillis();
	}

}
