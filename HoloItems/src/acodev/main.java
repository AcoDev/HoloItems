package acodev;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class main extends JavaPlugin implements Listener {
  private static main instance;
  
  ArrayList<Hologram> holograms = new ArrayList<>();
  
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
    instance = this;
    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6------------------------------"));
    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&emain it's loaded."));
    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eHolographicDisplays successfully &ahooked&e!"));
    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eBy: &6Aco &eWith Love <3 "));
    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6------------------------------"));
    loadConfig();
  }
  
  public void onDisable() {
    for (Hologram h : this.holograms)
      h.delete(); 
    instance = null;
  }
  
  public void loadConfig() {
    getConfig().options().copyDefaults(true);
    saveConfig();
  }
  
  public String colorize(String msg) {
    String coloredMsg = "";
    for (int i = 0; i < msg.length(); i++) {
      if (msg.charAt(i) == '&') {
        coloredMsg = String.valueOf(String.valueOf(coloredMsg)) + '§';
      } else {
        coloredMsg = String.valueOf(String.valueOf(coloredMsg)) + msg.charAt(i);
      } 
    } 
    return coloredMsg;
  }
  
  public static main getInstance() {
    return instance;
  }
  
  @EventHandler
  public void onDroppingOnGround(final ItemSpawnEvent e) {
    Location d = e.getLocation();
    ItemStack itemdrop = e.getEntity().getItemStack();
    final Hologram k = HologramsAPI.createHologram((Plugin)getInstance(), d);
    int Aco = 0;
    if (itemdrop.getType() == Material.DIAMOND_SWORD || itemdrop.getType() == Material.WOOD_SWORD || itemdrop.getType() == Material.STONE_SWORD || itemdrop.getType() == Material.IRON_SWORD || itemdrop.getType() == Material.GOLD_SWORD || itemdrop.getType() == Material.DIAMOND_PICKAXE || itemdrop.getType() == Material.GOLD_PICKAXE || itemdrop.getType() == Material.IRON_PICKAXE || itemdrop.getType() == Material.STONE_PICKAXE || itemdrop.getType() == Material.WOOD_PICKAXE || itemdrop.getType() == Material.DIAMOND_AXE || itemdrop.getType() == Material.GOLD_AXE || itemdrop.getType() == Material.IRON_AXE || itemdrop.getType() == Material.STONE_AXE || itemdrop.getType() == Material.WOOD_AXE || itemdrop.getType() == Material.DIAMOND_SPADE || itemdrop.getType() == Material.GOLD_SPADE || itemdrop.getType() == Material.IRON_SPADE || itemdrop.getType() == Material.STONE_SPADE || itemdrop.getType() == Material.WOOD_SPADE || itemdrop.getType() == Material.DIAMOND_HOE || itemdrop.getType() == Material.GOLD_HOE || itemdrop.getType() == Material.IRON_HOE || itemdrop.getType() == Material.STONE_HOE || itemdrop.getType() == Material.WOOD_HOE || itemdrop.getType() == Material.DIAMOND_HELMET || itemdrop.getType() == Material.GOLD_HELMET || itemdrop.getType() == Material.IRON_HELMET || itemdrop.getType() == Material.LEATHER_HELMET || itemdrop.getType() == Material.CHAINMAIL_HELMET || itemdrop.getType() == Material.DIAMOND_CHESTPLATE || itemdrop.getType() == Material.GOLD_CHESTPLATE || itemdrop.getType() == Material.IRON_CHESTPLATE || itemdrop.getType() == Material.LEATHER_CHESTPLATE || itemdrop.getType() == Material.CHAINMAIL_CHESTPLATE || itemdrop.getType() == Material.DIAMOND_LEGGINGS || itemdrop.getType() == Material.GOLD_LEGGINGS || itemdrop.getType() == Material.IRON_LEGGINGS || itemdrop.getType() == Material.LEATHER_LEGGINGS || itemdrop.getType() == Material.CHAINMAIL_LEGGINGS || itemdrop.getType() == Material.DIAMOND_BOOTS || itemdrop.getType() == Material.GOLD_BOOTS || itemdrop.getType() == Material.IRON_BOOTS || itemdrop.getType() == Material.LEATHER_BOOTS || itemdrop.getType() == Material.CHAINMAIL_BOOTS || itemdrop.getType() == Material.FISHING_ROD || itemdrop.getType() == Material.BOW) {
      if (itemdrop.getEnchantmentLevel(Enchantment.DIG_SPEED) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Tools.Efficiency")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.DIG_SPEED));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Tools.Fortune")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.SILK_TOUCH) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Tools.Silktouch")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.SILK_TOUCH));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.DURABILITY) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Tools.Unbreaking")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.DURABILITY));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.DAMAGE_ALL) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Swords.Sharpness")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.DAMAGE_ALL));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Swords.Smite")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Swords.BaneOfArthropods")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.FIRE_ASPECT) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Swords.FireAspect")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.FIRE_ASPECT));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Swords.Looting")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.KNOCKBACK) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Swords.KnockBack")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.KNOCKBACK));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.SWEEPING_EDGE) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Swords.SweepingEdge")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.SWEEPING_EDGE));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Armor.Protection.Environmental")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Armor.Protection.Blast")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.PROTECTION_FIRE) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Armor.Protection.Fire")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.PROTECTION_FIRE));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Armor.Protection.Projectile")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.PROTECTION_FALL) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Armor.FeatherFalling")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.PROTECTION_FALL));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.FROST_WALKER) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Armor.FrostWalker")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.FROST_WALKER));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.THORNS) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Armor.Thorns")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.THORNS));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.OXYGEN) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Armor.Respiration")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.OXYGEN));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.DEPTH_STRIDER) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Armor.DepthStrider")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.DEPTH_STRIDER));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.WATER_WORKER) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Armor.AquaAffinity")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.WATER_WORKER));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Bow.Power")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.ARROW_DAMAGE));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Bow.Punch")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.ARROW_FIRE) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Bow.Flame")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.ARROW_FIRE));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.ARROW_INFINITE) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.Bow.Infinity")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.ARROW_INFINITE));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.LUCK) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.FishingRod.Luck")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.LUCK));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.LURE) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, String.valueOf(String.valueOf(colorize(getConfig().getString("HoloItem.Enchantments.FishingRod.Lure")))) + " " + itemdrop.getEnchantmentLevel(Enchantment.LURE));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.MENDING) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, colorize(getConfig().getString("HoloItem.Enchantments.Extras.Mending")));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.BINDING_CURSE) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, colorize(getConfig().getString("HoloItem.Enchantments.Extras.Binding")));
        Aco++;
      } 
      if (itemdrop.getEnchantmentLevel(Enchantment.VANISHING_CURSE) != 0) {
        if (Aco == 0) {
          k.insertTextLine(0, colorize(getConfig().getString("HoloItem.Enchantments-Message")));
          Aco++;
        } 
        k.insertTextLine(Aco, colorize(getConfig().getString("HoloItem.Enchantments.Extras.Vanishing")));
        Aco++;
      } 
    } 
    this.holograms.add(k);
    (new BukkitRunnable() {
        public void run() {
          if (!e.getEntity().isDead()) {
            k.teleport(e.getLocation().add(0.0D, 2.0D, 0.0D));
          } else {
            main.this.holograms.remove(k);
            k.delete();
            cancel();
          } 
        }
      }).runTaskTimer((Plugin)this, 1L, 1L);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    Player psender = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("holoreload")) {
      if (psender.hasPermission("holoitems.admin")) {
        if (args.length == 0) {
          reloadConfig();
          psender.sendMessage(colorize("&ePlugin created by: &6Aco &ewith love <3"));
          psender.sendMessage(colorize("&cHoloItem has been reloaded"));
          return true;
        } 
        psender.sendMessage(colorize("&cYou don't have permission to do this"));
        return true;
      } 
      return true;
    } 
    return true;
  }
}
