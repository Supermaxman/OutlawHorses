package me.supermaxman.outlawhorses;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

public class NameExecutor extends BaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if(player.getVehicle() != null && args.length != 0){
        	if(player.getVehicle() instanceof Horse) {
        		for (Pet p : OutlawHorses.horses.get(player.getName())) {
        			if(p.getUID().equals(player.getVehicle().getUniqueId().toString())) {
        				p.setName(args[0]);
        				((Horse)player.getVehicle()).setCustomName(args[0]);
        				((Horse)player.getVehicle()).setCustomNameVisible(true);
        	        	player.sendMessage("Horse now named "+ args[0]);
        			}else {
        	        	player.sendMessage("Command used incorrectly, please sit on your horse and type /name [name]");
        			}
        		}
        	}else {
            	player.sendMessage("Command used incorrectly, please sit on your horse and type /name [name]");
        	}
        }else {
        	player.sendMessage("Command used incorrectly, please sit on your horse and type /name [name]");
        }
    }

    public NameExecutor(OutlawHorses pl) {
        super(pl);
    }
}
