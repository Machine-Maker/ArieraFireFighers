package me.x1machinemaker1x.arierafirefighters;

import org.bukkit.plugin.java.JavaPlugin;

import me.x1machinemaker1x.arierafirefighters.cmds.GasCan;
import me.x1machinemaker1x.arierafirefighters.cmds.Lighter;
import me.x1machinemaker1x.arierafirefighters.events.PlayerInteract;
import me.x1machinemaker1x.arierafirefighters.utils.ConfigManager;
import me.x1machinemaker1x.arierafirefighters.utils.GasManager;
import me.x1machinemaker1x.arierafirefighters.utils.Messages;

public class ArieraFireFighters extends JavaPlugin {
	
	public void onEnable() {
		
		ConfigManager.getInstance().setup(this);
		
		Messages.setup(this);
		getCommand("gascan").setExecutor(new GasCan());
		getCommand("lighter").setExecutor(new Lighter());
		
		this.getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		
		GasManager.getInstance().setup(this);
		
		
	}
	
}
