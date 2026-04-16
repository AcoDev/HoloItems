package org.kora.holoitems.hologram;

import org.kora.holoitems.enchant.EnchantmentLineBuilder;
import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

public final class DroppedItemHologramListener implements Listener {
  private final EnchantmentLineBuilder enchantmentLineBuilder;
  private final TransientHologramManager hologramManager;

  public DroppedItemHologramListener(EnchantmentLineBuilder enchantmentLineBuilder, TransientHologramManager hologramManager) {
    this.enchantmentLineBuilder = enchantmentLineBuilder;
    this.hologramManager = hologramManager;
  }

  @EventHandler
  public void onItemSpawn(ItemSpawnEvent event) {
    List<String> lines = this.enchantmentLineBuilder.buildLines(event.getEntity().getItemStack());
    if (lines.isEmpty())
      return;
    this.hologramManager.spawnForItem(event.getEntity(), lines);
  }
}
