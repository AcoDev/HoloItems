package org.kora.holoitems;

import org.kora.holoitems.enchant.EnchantmentLineBuilder;
import org.kora.holoitems.hologram.DroppedItemHologramListener;
import org.kora.holoitems.hologram.TransientHologramManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
  private TransientHologramManager hologramManager;

  public void onEnable() {
    saveDefaultConfig();

    this.hologramManager = new TransientHologramManager(this);
    EnchantmentLineBuilder enchantmentLineBuilder = new EnchantmentLineBuilder(this);

    Bukkit.getPluginManager().registerEvents(new DroppedItemHologramListener(enchantmentLineBuilder, this.hologramManager), this);
    logStartup();
  }

  public void onDisable() {
    if (this.hologramManager != null)
      this.hologramManager.deleteAll();
  }

  public String colorize(String message) {
    return ChatColor.translateAlternateColorCodes('&', (message == null) ? "" : message);
  }

  public String getColoredConfigString(String path, String defaultValue) {
    return colorize(getConfig().getString(path, defaultValue));
  }

  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    if (!cmd.getName().equalsIgnoreCase("holoreload"))
      return false;
    if (!sender.hasPermission("holoitems.admin")) {
      sender.sendMessage(colorize("&cYou don't have permission to do this"));
      return true;
    }
    if (args.length != 0) {
      sender.sendMessage(colorize("&cUsage: /holoreload"));
      return true;
    }
    reloadConfig();
    sender.sendMessage(colorize("&ePlugin created by: &6Aco &ewith love <3"));
    sender.sendMessage(colorize("&cHoloItem has been reloaded"));
    return true;
  }

  private void logStartup() {
    Bukkit.getConsoleSender().sendMessage(colorize("&6------------------------------"));
    Bukkit.getConsoleSender().sendMessage(colorize("&emain it's loaded."));
    Bukkit.getConsoleSender().sendMessage(colorize("&eDecentHolograms successfully &ahooked&e!"));
    Bukkit.getConsoleSender().sendMessage(colorize("&eBy: &6Aco &eWith Love <3 "));
    Bukkit.getConsoleSender().sendMessage(colorize("&6------------------------------"));
  }
}
