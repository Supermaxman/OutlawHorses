package me.supermaxman.outlawhorses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;



public class OutlawHorses extends JavaPlugin {
    public static FileConfiguration conf;
	public static OutlawHorses plugin;
	public static final Logger log = Logger.getLogger("Minecraft");
	
	static HashMap<String, ArrayList<Pet>> horses = new HashMap<String, ArrayList<Pet>>();

	
	public void onEnable() {
		plugin = this;
		
		saveDefaultConfig();
        getCommand("name").setExecutor(new NameExecutor(this));    
		
		loadHorses();
        
		getServer().getPluginManager().registerEvents(new OutlawHorsesListener(), plugin);
		
		log.info(getName() + " has been enabled.");
		
	}
	
	@SuppressWarnings("unchecked")
	void loadHorses() {
		try {
			horses = (HashMap<String, ArrayList<Pet>>) new ObjectInputStream(new FileInputStream(getDataFolder() + File.separator + "horses.ser")).readObject();
			new ObjectInputStream(new FileInputStream(getDataFolder() + File.separator + "horses.ser")).close();
		} catch (Exception e) {
			log.warning("[" + getName() + "] horses.ser could not be read! All horses are now reset.");
		}
		for(ArrayList<Pet> l : horses.values()) {
			for(Pet p : l) {
				p.findEntity(plugin.getServer().getWorld(p.getWorld()), p.getUID());
			}
		}
	}
	
	
	
	
	public void onDisable() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getDataFolder() + File.separator + "horses.ser"));
			oos.writeObject(horses);
			oos.close();
		} catch (Exception e) {
			log.warning("[" + getName() + "] horses could not be saved to horses.ser!");
			e.printStackTrace();
		}
		
		log.info(getName() + " has been disabled.");
	}
}