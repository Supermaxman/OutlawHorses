package me.supermaxman.outlawhorses;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

	
public class OutlawHorsesListener implements Listener {
	
	@EventHandler
	public void onEntityTameEvent(EntityTameEvent e) {
		if(e.getEntity() instanceof Horse && e.getOwner() instanceof Player) {
			String n = e.getOwner().getName();
			if(!OutlawHorses.horses.containsKey(n))OutlawHorses.horses.put(n, new ArrayList<Pet>());
			Pet p = new Pet(n, e.getEntity().getUniqueId().toString(), e.getEntity().getWorld());
			OutlawHorses.horses.get(n).add(p);
			if(e.getEntity().hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
				p.setBreeding(1);
			}
			
		}
	}
	
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if(e.getRightClicked() instanceof Horse && OutlawHorses.horses.containsKey(e.getPlayer().getName())) {
			String n = e.getPlayer().getName();
			for(Pet p : OutlawHorses.horses.get(n)) {
				if(p.getUID().equals(e.getRightClicked().getUniqueId().toString())) {
					p.setFast(p.getSpeed(), e.getRightClicked().getWorld());
					if(e.getPlayer().getItemInHand().getType()==Material.SADDLE) {
						if(p.getName()!=null) {
							e.getPlayer().sendMessage("Stats for "+p.getName()+":");
						}else {
							e.getPlayer().sendMessage("Stats:");
						}
						e.getPlayer().sendMessage("Strength: "+ p.getStrength());
						e.getPlayer().sendMessage("Speed: " + p.getSpeed());
					}
					break;
				}
			}
			
		}
	}
	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent e) {
		if (e.getSpawnReason().equals(SpawnReason.BREEDING)&&e.getEntity() instanceof Horse) {
			Horse h = (Horse) e.getEntity();
			h.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000000, 1, true));
		}
		
	}
	
}
