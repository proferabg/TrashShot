package com.probgtech.trashshot;

import java.util.Arrays;
import java.util.Random;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class TrashListener implements Listener {

	// ----------------------------------------- CONSTRUCTOR -----------------------------------------------
	TrashShot plugin;
	public TrashListener(TrashShot p) {
		plugin = p;
		addCrafting();
	}
	
	// -------------------------------------------- EVENTS -------------------------------------------------
	@SuppressWarnings("deprecation")
	@EventHandler
    public void playerDeath(EntityShootBowEvent event){
		if (event.getEntity() instanceof Player){
	        Player p = (Player)event.getEntity();
	        ItemStack item = p.getItemInHand().clone();
	        item.setDurability((short) 0);
	        if (item.equals(getFireShot())){
	        	if(p.hasPermission("trashshot.use.fireshot")){
	        		if(p.getInventory().contains(Material.FIREWORK_ROCKET)){
	        			shootFireWork(p);
	        			p.getInventory().removeItem(new ItemStack(Material.FIREWORK_ROCKET));
	        			event.setCancelled(true);
	        		}
	        		else if (p.getGameMode().equals(GameMode.CREATIVE)){
	        			shootFireWork(p);
	        			event.setCancelled(true);
	        		}
	        	}
	        	else {
	        		p.sendMessage(MessageHandler.getPrefix() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("CantUse.FireShot")));
	        	}
	        }
	        else if (item.equals(getTnTShot())){
	        	if(p.hasPermission("trashshot.use.tntshot")){
	        		if(p.getInventory().contains(Material.TNT)){
		        		shootTnT(p);
		        		p.getInventory().removeItem(new ItemStack(Material.TNT));
			        	event.setCancelled(true);
		        	}
	        		else if (p.getGameMode().equals(GameMode.CREATIVE)){
	        			shootTnT(p);
	        			event.setCancelled(true);
	        		}
	        	}
	        	else {
	        		p.sendMessage(MessageHandler.getPrefix() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("CantUse.TNTShot")));
	        	}
	        }
	        else if (item.equals(getEnderShot())){
	        	if(p.hasPermission("trashshot.use.endershot")){
	        		boolean hasItem = false;
	        		for (ItemStack i : p.getInventory()){
	        			if(i != null && i.isSimilar(new ItemStack(Material.ENDER_PEARL))){
	        				hasItem = true;
	        			}
	        		}
	        		if(hasItem){
		        		shootEP(p);
		        		p.getInventory().removeItem(new ItemStack(Material.ENDER_PEARL));
			        	event.setCancelled(true);
		        	}
	        		else if (p.getGameMode().equals(GameMode.CREATIVE)){
	        			shootEP(p);
	        			event.setCancelled(true);
	        		}
	        	}
	        	else {
	        		p.sendMessage(MessageHandler.getPrefix() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("CantUse.EnderShot")));
	        	}
	        }
	        else if (item.equals(getChargeShot())){
	        	if(p.hasPermission("trashshot.use.chargeshot")){
	        		if(p.getInventory().contains(Material.FIRE_CHARGE)){
		        		shootCharge(p);
		        		p.getInventory().removeItem(new ItemStack(Material.FIRE_CHARGE));
		        		event.setCancelled(true);
		        	}
	        		else if (p.getGameMode().equals(GameMode.CREATIVE)){
	        			shootCharge(p);
	        			event.setCancelled(true);
	        		}
	        	}
	        	else {
	        		p.sendMessage(MessageHandler.getPrefix() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("CantUse.ChargeShot")));
	        	}
	        }
	        else if (item.equals(getSnowShot())){
	        	if(p.hasPermission("trashshot.use.snowshot")){
	        		if(p.getInventory().contains(Material.SNOWBALL)){
		        		shootSnow(p);
		        		p.getInventory().removeItem(new ItemStack(Material.SNOWBALL));
		        		event.setCancelled(true);
	        		}
	        		else if (p.getGameMode().equals(GameMode.CREATIVE)){
	        			shootSnow(p);
	        			event.setCancelled(true);
	        		}
	        	}
	        	else {
	        		p.sendMessage(MessageHandler.getPrefix() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("CantUse.SnowShot")));
	        	}
	        }
	        else if (item.equals(getLavaShot())){
	        	if(p.hasPermission("trashshot.use.lavashot")){
	        		if(p.getInventory().contains(Material.LAVA_BUCKET)){
		        		shootLava(p);
		        		p.getInventory().removeItem(new ItemStack(Material.LAVA_BUCKET));
			        	event.setCancelled(true);
	        		}
	        		else if (p.getGameMode().equals(GameMode.CREATIVE)){
	        			shootLava(p);
	        			event.setCancelled(true);
	        		}
	        	}
	        	else {
	        		p.sendMessage(MessageHandler.getPrefix() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("CantUse.LavaShot")));
	        	}
	        }
	        else if (item.equals(getWaterShot())){
	        	if(p.hasPermission("trashshot.use.watershot")){
	        		if(p.getInventory().contains(Material.WATER_BUCKET)){
	        			shootWater(p);
		        		p.getInventory().removeItem(new ItemStack(Material.WATER_BUCKET));
			        	event.setCancelled(true);
		        	}
	        		else if (p.getGameMode().equals(GameMode.CREATIVE)){
	        			shootWater(p);
	        			event.setCancelled(true);
	        		}
	        	}
	        	else {
	        		p.sendMessage(MessageHandler.getPrefix() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("CantUse.WaterShot")));
	        	}
	        }
		}
    }
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent e){
		ItemStack i = e.getItem().getItemStack();
		if(i.equals(getLavaBucket())){
			e.setCancelled(true);
		}
	}
	

	
	@EventHandler
	public void onCmdParty(PlayerCommandPreprocessEvent e){
		if(e.getMessage().startsWith("/tsfw")){
			if (e.getPlayer().hasPermission("ts.fw")){
				Firework arr = (Firework) e.getPlayer().getWorld().spawn(e.getPlayer().getLocation().add(0, 1, 0), Firework.class);
		        Random r = new Random();
		        int rt = r.nextInt(1) + 1;
		        Type type = Type.BALL_LARGE;   
		        if (rt == 1) type = Type.BALL;
		        if (rt == 2) type = Type.BALL_LARGE;
		        int r1i = r.nextInt(17) + 1;
		        int r2i = r.nextInt(17) + 1;
		        Color c1 = getColor(r1i);
		        Color c2 = getColor(r2i);
		        FireworkMeta fwm = arr.getFireworkMeta();
		        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
		        fwm.addEffect(effect);
		        arr.setFireworkMeta(fwm);
		        e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent event){
        if (event.getRecipe().getResult().equals(getChargeShot())){
        	if(!event.getView().getPlayer().hasPermission("trashshot.craft.chargeshot")){
                event.setCancelled(true);
        	}
        }
        else if (event.getRecipe().getResult().equals(getLavaShot())){
        	if(!event.getView().getPlayer().hasPermission("trashshot.craft.lavashot")){
                event.setCancelled(true);
        	}
        }
        else if (event.getRecipe().getResult().equals(getWaterShot())){
        	if(!event.getView().getPlayer().hasPermission("trashshot.craft.watershot")){
                event.setCancelled(true);
        	}
        }
        else if (event.getRecipe().getResult().equals(getFireShot())){
        	if(!event.getView().getPlayer().hasPermission("trashshot.craft.fireshot")){
                event.setCancelled(true);
        	}
        }
        else if (event.getRecipe().getResult().equals(getEnderShot())){
        	if(!event.getView().getPlayer().hasPermission("trashshot.craft.endershot")){
                event.setCancelled(true);
        	}
        }
        else if (event.getRecipe().getResult().equals(getSnowShot())){
        	if(!event.getView().getPlayer().hasPermission("trashshot.craft.snowshot")){
                event.setCancelled(true);
        	}
        }
        else if (event.getRecipe().getResult().equals(getTnTShot())){
        	if(!event.getView().getPlayer().hasPermission("trashshot.craft.tntshot")){
                event.setCancelled(true);
        	}
        }
        else if (event.getRecipe().getResult().equals(getDiscoStick())){
        	if(!event.getView().getPlayer().hasPermission("trashshot.craft.discostick")){
                event.setCancelled(true);
        	}
        }
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void discoStick(EntityDamageByEntityEvent e){
		Entity ent = e.getDamager();
		if (ent instanceof Player){
			Player damager = (Player) ent;
			if (damager.getItemInHand().equals(getDiscoStick())){
	        	if(damager.hasPermission("trashshot.use.discostick")){
	        		//e.getEntity().setVelocity(e.getEntity().getVelocity().add(e.getEntity().getLocation().toVector().subtract(damager.getLocation().toVector()).normalize().multiply(plugin.getConfig().getInt("DiscoStick_Power"))));
	        		e.setDamage(0.0);
	        	}
	        	else {
	        		damager.sendMessage(MessageHandler.getPrefix() + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("CantUse.DiscoStick")));
	        	}
			}
		}
		
	}
	
	
	// ------------------------------------------- SHOOTING -------------------------------------------------
	private void shootTnT(Player p) {
		TNTPrimed tnt = (TNTPrimed) p.getWorld().spawn(p.getLocation().add(0, 1, 0), TNTPrimed.class);
		tnt.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
	}
	
	private void shootLava(final Player p) {
		final Entity lava = (Entity) p.getWorld().dropItem(p.getEyeLocation(), getLavaBucket());
		lava.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
		new BukkitRunnable(){
			@Override
			public void run(){
				Location l = lava.getLocation();
				lava.remove();
				l.getBlock().setType(Material.LAVA);
				l.getWorld().createExplosion(l, 0);
				if (plugin.getConfig().getBoolean("Log_LavaShot")){
					plugin.getLogger().warning(p.getName() + " LavaShot: " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ());
				}
			} 
		}.runTaskLater(plugin, 100L);
	}
	
	private void shootWater(final Player p) {
		final Entity water = (Entity) p.getWorld().dropItem(p.getEyeLocation(), getWaterBucket());
		water.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
		new BukkitRunnable(){
			@Override
			public void run(){
				Location l = water.getLocation();
				water.remove();
				l.getBlock().setType(Material.WATER);
				l.getWorld().createExplosion(l, 0);
				if (plugin.getConfig().getBoolean("Log_WaterShot")){
					plugin.getLogger().warning(p.getName() + " WaterShot: " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ());
				}
			} 
		}.runTaskLater(plugin, 100L);
	}
	
	private void shootEP(Player p) {
		EnderPearl ep = p.launchProjectile(EnderPearl.class);
		//EnderPearl ep = p.getWorld().spawn(p.getLocation().add(0, 1, 0), EnderPearl.class);
		ep.setShooter(p);
		ep.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
	}
	
	private void shootCharge(Player p) {
		Fireball fb = p.getWorld().spawn(p.getLocation().add(0, 1, 0), Fireball.class);
		fb.setIsIncendiary(true);
		fb.setShooter(p);
		fb.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
	}
	
	private void shootSnow(Player p) {
		Random r = new Random();
		for (int i = 0;i <=16;i++){
			Snowball sb = (Snowball) p.getWorld().spawn(p.getLocation().add(0, 1, 0), Snowball.class);
			sb.setShooter(p);
			if(r.nextInt(6 - 1 + 1) + 1 == 1) sb.setVelocity(p.getLocation().getDirection().normalize().multiply(2).add(new Vector(-r.nextDouble(), r.nextDouble(), r.nextDouble())));
			else if (r.nextInt(6 - 1 + 1) + 1 == 2) sb.setVelocity(p.getLocation().getDirection().normalize().multiply(2).add(new Vector(r.nextDouble(), -r.nextDouble(), r.nextDouble())));
			else if (r.nextInt(6 - 1 + 1) + 1 == 3) sb.setVelocity(p.getLocation().getDirection().normalize().multiply(2).add(new Vector(r.nextDouble(), r.nextDouble(), -r.nextDouble())));
			else if (r.nextInt(6 - 1 + 1) + 1 == 4) sb.setVelocity(p.getLocation().getDirection().normalize().multiply(2).add(new Vector(r.nextDouble(), r.nextDouble(), r.nextDouble())));
			else if (r.nextInt(6 - 1 + 1) + 1 == 5) sb.setVelocity(p.getLocation().getDirection().normalize().multiply(2).add(new Vector(-r.nextDouble(), -r.nextDouble(), -r.nextDouble())));
			else if (r.nextInt(6 - 1 + 1) + 1 == 6) sb.setVelocity(p.getLocation().getDirection().normalize().multiply(2).add(new Vector(r.nextDouble(), -r.nextDouble(), -r.nextDouble())));
			else if (r.nextInt(6 - 1 + 1) + 1 == 5) sb.setVelocity(p.getLocation().getDirection().normalize().multiply(2).add(new Vector(-r.nextDouble(), -r.nextDouble(), r.nextDouble())));
			else if (r.nextInt(6 - 1 + 1) + 1 == 7) sb.setVelocity(p.getLocation().getDirection().normalize().multiply(2).add(new Vector(-r.nextDouble(), r.nextDouble(), -r.nextDouble())));
			else sb.setVelocity(p.getLocation().getDirection().normalize().multiply(2).add(new Vector(r.nextDouble(), 0, r.nextDouble())));
		}
	}
	
	private void shootFireWork(Player p) {
		Firework arr = (Firework) p.getWorld().spawn(p.getLocation().add(0, 1, 0), Firework.class);
        Random r = new Random();
        int rt = r.nextInt(1) + 1;
        Type type = Type.BALL_LARGE;   
        if (rt == 1) type = Type.BALL;
        if (rt == 2) type = Type.BALL_LARGE;
        int r1i = r.nextInt(17) + 1;
        int r2i = r.nextInt(17) + 1;
        Color c1 = getColor(r1i);
        Color c2 = getColor(r2i);
        FireworkMeta fwm = arr.getFireworkMeta();
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fwm.addEffect(effect);
        fwm.setPower(0);
        arr.setFireworkMeta(fwm);
        arr.setVelocity(p.getLocation().getDirection().normalize());
	}
	
	// ------------------------------------------ CRAFTING -------------------------------------------------
	@SuppressWarnings("deprecation")
	public void addCrafting(){
		ShapelessRecipe tntshot = new ShapelessRecipe(getTnTShot());
		tntshot.addIngredient(Material.BOW);
		tntshot.addIngredient(Material.TNT);
		Bukkit.getServer().addRecipe(tntshot);

		ShapelessRecipe fireshot = new ShapelessRecipe(getFireShot());
		fireshot.addIngredient(Material.BOW);
		fireshot.addIngredient(Material.FIREWORK_ROCKET);
		Bukkit.getServer().addRecipe(fireshot);
		
		ShapelessRecipe endershot = new ShapelessRecipe(getEnderShot());
		endershot.addIngredient(Material.BOW);
		endershot.addIngredient(Material.ENDER_PEARL);
		Bukkit.getServer().addRecipe(endershot);
		
		ShapelessRecipe chargeshot = new ShapelessRecipe(getChargeShot());
		chargeshot.addIngredient(Material.BOW);
		chargeshot.addIngredient(Material.FIRE_CHARGE);
		Bukkit.getServer().addRecipe(chargeshot);
		
		ShapelessRecipe snowshot = new ShapelessRecipe(getSnowShot());
		snowshot.addIngredient(Material.BOW);
		snowshot.addIngredient(Material.SNOWBALL);
		Bukkit.getServer().addRecipe(snowshot);
		
		ShapelessRecipe lavashot = new ShapelessRecipe(getLavaShot());
		lavashot.addIngredient(Material.BOW);
		lavashot.addIngredient(Material.LAVA_BUCKET);
		Bukkit.getServer().addRecipe(lavashot);
		
		ShapelessRecipe watershot = new ShapelessRecipe(getWaterShot());
		watershot.addIngredient(Material.BOW);
		watershot.addIngredient(Material.WATER_BUCKET);
		Bukkit.getServer().addRecipe(watershot);

		ShapelessRecipe stick = new ShapelessRecipe(getDiscoStick());
		stick.addIngredient(Material.STICK);
		stick.addIngredient(Material.DIAMOND);
		Bukkit.getServer().addRecipe(stick);
	}

	// -------------------------------------------- ITEMS -------------------------------------------------
	private ItemStack getTnTShot() {
		ItemStack tntshot = new ItemStack(Material.BOW);
		ItemMeta tntshotmeta = tntshot.getItemMeta();
		tntshotmeta.setDisplayName("§4TNT§3Shot");
		tntshotmeta.setLore(Arrays.asList("§6Shoot TNT","§6out of the bow!","§1 ","§aAmmo Consumption:","  - §c1 Arrow","  - §c1 TNT"));
		tntshot.setItemMeta(tntshotmeta);
		return tntshot;
	}

	private ItemStack getChargeShot() {
		ItemStack chargeshot = new ItemStack(Material.BOW);
		ItemMeta chargeshotmeta = chargeshot.getItemMeta();
		chargeshotmeta.setDisplayName("§8Charge§3Shot");
		chargeshotmeta.setLore(Arrays.asList("§6Shoot fire charges","§6out of the bow!","§1 ","§aAmmo Consumption:","  - §c1 Arrow","  - §c1 FireCharge"));
		chargeshot.setItemMeta(chargeshotmeta);
		return chargeshot;
	}
	
	private ItemStack getSnowShot() {
		ItemStack snowshot = new ItemStack(Material.BOW);
		ItemMeta snowshotmeta = snowshot.getItemMeta();
		snowshotmeta.setDisplayName("§bSnow§3Shot");
		snowshotmeta.setLore(Arrays.asList("§6Shoot snowballs","§6out of the bow!","§1 ","§aAmmo Consumption:","  - §c1 Arrow","  - §c1 Snowball"));
		snowshot.setItemMeta(snowshotmeta);
		return snowshot;
	}
	
	private ItemStack getLavaBucket() {
		ItemStack lbshot = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta lbshotmeta = lbshot.getItemMeta();
		lbshotmeta.setDisplayName("§1§9§8§9NOPICKUP");
		lbshotmeta.setLore(Arrays.asList("§1§9§8§9NOPICKUP"));
		lbshot.setItemMeta(lbshotmeta);
		return lbshot;
	}
	
	private ItemStack getWaterBucket() {
		ItemStack lbshot = new ItemStack(Material.WATER_BUCKET);
		ItemMeta lbshotmeta = lbshot.getItemMeta();
		lbshotmeta.setDisplayName("§1§9§8§9NOPICKUP");
		lbshotmeta.setLore(Arrays.asList("§1§9§8§9NOPICKUP"));
		lbshot.setItemMeta(lbshotmeta);
		return lbshot;
	}
	
	private ItemStack getLavaShot() {
		ItemStack snowshot = new ItemStack(Material.BOW);
		ItemMeta snowshotmeta = snowshot.getItemMeta();
		snowshotmeta.setDisplayName("§6Lava§3Shot");
		snowshotmeta.setLore(Arrays.asList("§6Shoot lava buckets","§6out of the bow!","§6After 5 seconds the","§6lava bucket will spill §d§l:)","§1 ","§aAmmo Consumption:","  - §c1 Arrow","  - §c1 Lava Bucket"));
		snowshot.setItemMeta(snowshotmeta);
		return snowshot;
	}
	
	private ItemStack getWaterShot() {
		ItemStack snowshot = new ItemStack(Material.BOW);
		ItemMeta snowshotmeta = snowshot.getItemMeta();
		snowshotmeta.setDisplayName("§9Water§3Shot");
		snowshotmeta.setLore(Arrays.asList("§6Shoot water buckets","§6out of the bow!","§6After 5 seconds the","§6water bucket will spill §d§l:)","§1 ","§aAmmo Consumption:","  - §c1 Arrow","  - §c1 Water Bucket"));
		snowshot.setItemMeta(snowshotmeta);
		return snowshot;
	}

	private ItemStack getFireShot(){
		ItemStack fireshot = new ItemStack(Material.BOW);
		ItemMeta fireshotmeta = fireshot.getItemMeta();
		fireshotmeta.setDisplayName("§5Fire§3Shot");
		fireshotmeta.setLore(Arrays.asList("§6Shoot fireworks","§6out of the bow!","§1 ","§aAmmo Consumption:","  - §c1 Arrow","  - §c1 Firework"));
		fireshot.setItemMeta(fireshotmeta);
		return fireshot;
	}

	private ItemStack getEnderShot(){
		ItemStack endershot = new ItemStack(Material.BOW);
		ItemMeta endershotmeta = endershot.getItemMeta();
		endershotmeta.setDisplayName("§2Ender§3Shot");
		endershotmeta.setLore(Arrays.asList("§6Shoot enderpearls","§6out of the bow!","§1 ","§aAmmo Consumption:","  - §c1 Arrow","  - §c1 Ender Pearl"));
		endershot.setItemMeta(endershotmeta);
		return endershot;
	}
	
	private ItemStack getDiscoStick(){
		ItemStack stick = new ItemStack(Material.STICK);
		ItemMeta stickmeta = stick.getItemMeta();
		stickmeta.setDisplayName("§5Disco§3Stick");
		stickmeta.setLore(Arrays.asList("§6Hit players to send","§6them flying very far"));
		stick.setItemMeta(stickmeta);
		stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, plugin.getConfig().getInt("DiscoStick_Power"));
		return stick;
	}
	
	
	// -------------------------------------------- UTILS -------------------------------------------------
	private Color getColor(int i) {
        Color c = null;
        if(i==1) c=Color.AQUA;
        if(i==2) c=Color.BLACK;
        if(i==3) c=Color.BLUE;
        if(i==4) c=Color.FUCHSIA;
        if(i==5) c=Color.GRAY;
        if(i==6) c=Color.GREEN;
        if(i==7) c=Color.LIME;
        if(i==8) c=Color.MAROON;
        if(i==9) c=Color.NAVY;
        if(i==10) c=Color.OLIVE;
        if(i==11) c=Color.ORANGE;
        if(i==12) c=Color.PURPLE;
        if(i==13) c=Color.RED;
        if(i==14) c=Color.SILVER;
        if(i==15) c=Color.TEAL;
        if(i==16) c=Color.WHITE;
        if(i==17) c=Color.YELLOW;
        return c;
    }
}
