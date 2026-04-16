package org.kora.holoitems.hologram;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class TransientHologramManager {
  private static final double HOLOGRAM_Y_OFFSET = 2.0D;

  private final JavaPlugin plugin;
  private final Set<Hologram> activeHolograms = new HashSet<>();

  public TransientHologramManager(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  public void spawnForItem(final Item item, List<String> lines) {
    final Hologram hologram = DHAPI.createHologram("holoitem-" + UUID.randomUUID().toString().replace("-", ""), getHologramLocation(item), false, lines);
    this.activeHolograms.add(hologram);
    (new BukkitRunnable() {
        public void run() {
          if (!item.isValid() || item.isDead()) {
            TransientHologramManager.this.delete(hologram);
            cancel();
            return;
          }
          DHAPI.moveHologram(hologram, TransientHologramManager.this.getHologramLocation(item));
        }
      }).runTaskTimer(this.plugin, 1L, 1L);
  }

  public void deleteAll() {
    for (Hologram hologram : new ArrayList<>(this.activeHolograms))
      delete(hologram);
  }

  private void delete(Hologram hologram) {
    if (this.activeHolograms.remove(hologram))
      hologram.delete();
  }

  private Location getHologramLocation(Item item) {
    return item.getLocation().clone().add(0.0D, HOLOGRAM_Y_OFFSET, 0.0D);
  }
}
