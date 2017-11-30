package me.x1machinemaker1x.arierafirefighters.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import me.x1machinemaker1x.arierafirefighters.objects.Gas;
import me.x1machinemaker1x.arierafirefighters.objects.GasTimer;

public class GasManager {
	
	private GasManager() { }
	
	private static GasManager instance = new GasManager();
	
	public static GasManager getInstance() {
		return instance;
	}
	
	List<Gas> gasList;
	
	public void setup(Plugin pl) {
		gasList = new ArrayList<Gas>();
		new GasTimer().runTaskTimer(pl, 0L, 10L);
	}
	
	public void addGas(Location loc, Long timePlaced) {
		gasList.add(new Gas(loc, timePlaced));
	}
	
	public Gas getGas(Location loc) {
		if (gasList.isEmpty()) return null;
		for (Gas gas : gasList) {
			if (gas.getLoc().equals(loc)) {
				return gas;
			}
		}
		return null;
	}
	
	public void removeGas(Gas gas) {
		gas.getLoc().getBlock().setType(Material.AIR);
		gasList.remove(gas);
	}
	
	public List<Gas> getGasList() {
		return gasList;
	}
}
