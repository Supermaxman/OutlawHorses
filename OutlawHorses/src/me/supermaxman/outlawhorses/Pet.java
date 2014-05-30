package me.supermaxman.outlawhorses;

import java.io.Serializable;
import java.util.Random;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Pet implements Serializable{
	
	private static final long serialVersionUID = -5463910893914771452L;
	private String name;
    private String owner;
    private int strength;
    private int speed;
    private int breeding;
    private String UID;
    private String world;
    public Pet(String owner, String UID, World world) {
        this.setOwner(owner);
        this.setUID(UID);
        this.setStrength(strengthGen()); 
        this.setSpeed(speedGen()); 
        this.setWorld(world.getName());
        setBreeding(0);
        findEntity(world, UID);
        setHealth(strength, world);
        setFast(speed, world);
    }
    public Horse findEntity(World world, String uid) {
        for(Entity e : world.getEntities()){
        	if (e instanceof Horse){
        		if(e.getUniqueId().toString().equals(UID)){
        			return (Horse) e;
        		}
        	}
        }
        return null;
    }
    @SuppressWarnings("deprecation")
	public void setHealth(int mod, World w) {
    	Horse h = findEntity(w, UID);
    	int hp = 10+(strength*(3+breeding));
    	h.setMaxHealth(hp);
    	h.setHealth(hp);
    	
    }
    
    public void setFast(int mod, World w) {
    	Horse h = findEntity(w, UID);
    	int sp = 0;
    	if(speed<=2) {
    		sp = 0;
    	}else if(speed<=4) {
    		sp = 1;
    	}else if(speed<=6) {
    		sp = 2;
    	}else if(speed<=8) {
    		sp = 3;
    	}else if(speed<=10) {
    		sp = 4;
    	}
    	if(sp == 0)return;
    	sp = sp+breeding;
    	h.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000000, sp, true));
    }
    
    public int strengthGen() {
    	Random rand = new Random(); 
    	int i = 0;
    	int c = rand.nextInt(99)+1;
    	if(c<=60) {
    		i = rand.nextInt(2)+1;
    	}else if(c>60 && c<=90) {
    		i = rand.nextInt(3)+4;
    	}else if(c>90){
    		i = rand.nextInt(2)+8;
    	}
    	
    	return i;
    }
    
    public int speedGen() {
    	Random rand = new Random(); 
    	int i = 0;
    	int c = rand.nextInt(99)+1;
    	if(c<=60) {
    		i = rand.nextInt(2)+1;
    	}else if(c>60 && c<=90) {
    		i = rand.nextInt(3)+4;
    	}else if(c>90){
    		i = rand.nextInt(2)+8;
    	}
    	
    	return i;
    }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID2) {
		UID = uID2;
	}

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}
	public int getBreeding() {
		return breeding;
	}
	public void setBreeding(int breeding) {
		this.breeding = breeding;
	}
    
    


}
