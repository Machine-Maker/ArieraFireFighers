package me.x1machinemaker1x.arierafirefighters.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.scheduler.BukkitRunnable;

import me.x1machinemaker1x.arierafirefighters.utils.GasManager;

public class GasTimer extends BukkitRunnable {
	
	@Override
	public void run() {
		List<Gas> copyList = new ArrayList<Gas>(GasManager.getInstance().getGasList());
		for (Gas gas : copyList) {
			if (gas.isExpired()) {
				GasManager.getInstance().removeGas(gas);
			}
		}
	}

}
