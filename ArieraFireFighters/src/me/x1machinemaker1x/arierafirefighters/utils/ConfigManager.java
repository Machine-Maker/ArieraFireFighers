package me.x1machinemaker1x.arierafirefighters.utils;

import org.bukkit.plugin.Plugin;

public class ConfigManager {
	
	private ConfigManager() { }
	
	private static ConfigManager instance = new ConfigManager();
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	public void setup(Plugin pl) {
		
		Config.loadValues(pl);
	}
	
	
	public enum Config {
		GAS_USES("gas-uses", 50),
		GAS_TIME_BEFORE_VANISH("gas-time-before-vanish", 30);
		
		
		String path;
		Object def;
		
		private Config(String path, int def) {
			this.path = path;
			this.def = def;
		}
		
		public String path() {
			return path;
		}
		
		public Object def() {
			return def;
		}
		
		public void setDef(Object def) {
			this.def = def;
		}

		public static void loadValues(Plugin pl) {
			for (Config value : Config.values()) {
				if (!pl.getConfig().isSet(value.path())) {
					pl.getConfig().set(value.path(), value.def());
				}
				else {
					value.setDef(pl.getConfig().get(value.path()));
				}
			}
			pl.saveConfig();
		}
	}

}
