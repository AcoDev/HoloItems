package org.kora.holoitems.enchant;

import org.kora.holoitems.main;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public final class EnchantmentLineBuilder {
  private static final String ENCHANTMENTS_MESSAGE_PATH = "HoloItem.Enchantments-Message";
  private static final String DEFAULT_ENCHANTMENTS_MESSAGE = "&b&lEnchantments";
  private static final List<EnchantmentDefinition> ENCHANTMENT_DEFINITIONS = Collections.unmodifiableList(Arrays.asList(new EnchantmentDefinition[] { 
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Utility.Efficiency", "HoloItem.Enchantments.Tools.Efficiency", "Efficiency", new String[] { "DIG_SPEED", "efficiency" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Utility.Fortune", "HoloItem.Enchantments.Tools.Fortune", "Fortune", new String[] { "LOOT_BONUS_BLOCKS", "fortune" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Utility.Silktouch", "HoloItem.Enchantments.Tools.Silktouch", "Silk Touch", new String[] { "SILK_TOUCH", "silk_touch" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Utility.Unbreaking", "HoloItem.Enchantments.Tools.Unbreaking", "Unbreaking", new String[] { "DURABILITY", "unbreaking" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Combat.Sharpness", "HoloItem.Enchantments.Swords.Sharpness", "Sharpness", new String[] { "DAMAGE_ALL", "sharpness" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Combat.Smite", "HoloItem.Enchantments.Swords.Smite", "Smite", new String[] { "DAMAGE_UNDEAD", "smite" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Combat.FireAspect", "HoloItem.Enchantments.Swords.FireAspect", "Fire Aspect", new String[] { "FIRE_ASPECT", "fire_aspect" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Combat.Looting", "HoloItem.Enchantments.Swords.Looting", "Looting", new String[] { "LOOT_BONUS_MOBS", "looting" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Combat.KnockBack", "HoloItem.Enchantments.Swords.KnockBack", "KnockBack", new String[] { "KNOCKBACK", "knockback" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Combat.BaneOfArthropods", "HoloItem.Enchantments.Swords.BaneOfArthropods", "Bane Of Arthropods", new String[] { "DAMAGE_ARTHROPODS", "bane_of_arthropods" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Combat.SweepingEdge", "HoloItem.Enchantments.Swords.SweepingEdge", "Sweeping Edge", new String[] { "SWEEPING_EDGE", "sweeping_edge" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Protection.Environmental", null, "Protection", new String[] { "PROTECTION_ENVIRONMENTAL", "protection" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Protection.Blast", null, "Blast Protection", new String[] { "PROTECTION_EXPLOSIONS", "blast_protection" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Protection.Fire", null, "Fire Protection", new String[] { "PROTECTION_FIRE", "fire_protection" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Protection.Projectile", null, "Projectile Protection", new String[] { "PROTECTION_PROJECTILE", "projectile_protection" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.FeatherFalling", null, "Feather Falling", new String[] { "PROTECTION_FALL", "feather_falling" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.FrostWalker", null, "Frost Walker", new String[] { "FROST_WALKER", "frost_walker" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Thorns", null, "Thorns", new String[] { "THORNS", "thorns" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Respiration", null, "Respiration", new String[] { "OXYGEN", "respiration" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.DepthStrider", null, "Depth Strider", new String[] { "DEPTH_STRIDER", "depth_strider" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.AquaAffinity", null, "Aqua Affinity", new String[] { "WATER_WORKER", "aqua_affinity" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Ranged.Power", "HoloItem.Enchantments.Bow.Power", "Power", new String[] { "ARROW_DAMAGE", "power" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Ranged.Punch", "HoloItem.Enchantments.Bow.Punch", "Punch", new String[] { "ARROW_KNOCKBACK", "punch" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Ranged.Flame", "HoloItem.Enchantments.Bow.Flame", "Flame", new String[] { "ARROW_FIRE", "flame" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Ranged.Infinity", "HoloItem.Enchantments.Bow.Infinity", "Infinity", new String[] { "ARROW_INFINITE", "infinity" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Trident.Loyalty", null, "Loyalty", new String[] { "LOYALTY", "loyalty" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Trident.Impaling", null, "Impaling", new String[] { "IMPALING", "impaling" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Trident.Riptide", null, "Riptide", new String[] { "RIPTIDE", "riptide" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Trident.Channeling", null, "Channeling", new String[] { "CHANNELING", "channeling" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Crossbow.Multishot", null, "Multishot", new String[] { "MULTISHOT", "multishot" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Crossbow.Piercing", null, "Piercing", new String[] { "PIERCING", "piercing" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Crossbow.QuickCharge", null, "Quick Charge", new String[] { "QUICK_CHARGE", "quick_charge" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.FishingRod.Luck", null, "Luck", new String[] { "LUCK", "luck_of_the_sea" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.FishingRod.Lure", null, "Lure", new String[] { "LURE", "lure" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.SoulSpeed", null, "Soul Speed", new String[] { "SOUL_SPEED", "soul_speed" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.SwiftSneak", null, "Swift Sneak", new String[] { "SWIFT_SNEAK", "swift_sneak" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Mace.Density", "Density", new String[] { "DENSITY", "density" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Mace.Breach", "Breach", new String[] { "BREACH", "breach" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Mace.WindBurst", "Wind Burst", new String[] { "WIND_BURST", "wind_burst" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Spear.Lunge", "Lunge", new String[] { "LUNGE", "lunge" }),
            EnchantmentDefinition.withoutLevel("HoloItem.Enchantments.Utility.Mending", "HoloItem.Enchantments.Extras.Mending", "Mending", new String[] { "MENDING", "mending" }),
            EnchantmentDefinition.withoutLevel("HoloItem.Enchantments.Curses.Binding", "HoloItem.Enchantments.Extras.Binding", "Curse of Binding", new String[] { "BINDING_CURSE", "binding_curse", "curse_of_binding" }),
            EnchantmentDefinition.withoutLevel("HoloItem.Enchantments.Curses.Vanishing", "HoloItem.Enchantments.Extras.Vanishing", "Curse of Vanishing", new String[] { "VANISHING_CURSE", "vanishing_curse", "curse_of_vanishing" }) }));

  private final main plugin;

  public EnchantmentLineBuilder(main plugin) {
    this.plugin = plugin;
  }

  public List<String> buildLines(ItemStack itemStack) {
    if (itemStack == null || itemStack.getEnchantments().isEmpty())
      return Collections.emptyList();
    List<ResolvedEnchantment> enchantments = resolveEnchantments(itemStack);
    List<String> lines = new ArrayList<>();
    for (EnchantmentDefinition definition : ENCHANTMENT_DEFINITIONS) {
      ResolvedEnchantment match = removeMatch(enchantments, definition);
      if (match != null)
        lines.add(formatConfiguredLine(definition, match.level));
    }
    enchantments.sort(Comparator.comparing(resolved -> prettify(resolved.primaryIdentifier)));
    for (ResolvedEnchantment enchantment : enchantments)
      lines.add(formatFallbackLine(enchantment));
    if (lines.isEmpty())
      return Collections.emptyList();
    lines.add(0, this.plugin.getColoredConfigString(ENCHANTMENTS_MESSAGE_PATH, DEFAULT_ENCHANTMENTS_MESSAGE));
    return lines;
  }

  private String formatConfiguredLine(EnchantmentDefinition definition, int level) {
    String label = resolveConfiguredLabel(definition);
    return definition.showLevel ? (String.valueOf(label) + " " + level) : label;
  }

  private String resolveConfiguredLabel(EnchantmentDefinition definition) {
    if (this.plugin.getConfig().contains(definition.configPath))
      return this.plugin.getColoredConfigString(definition.configPath, "&5&l" + definition.fallbackLabel);
    if (definition.legacyConfigPath != null && this.plugin.getConfig().contains(definition.legacyConfigPath))
      return this.plugin.getColoredConfigString(definition.legacyConfigPath, "&5&l" + definition.fallbackLabel);
    return this.plugin.colorize("&5&l" + definition.fallbackLabel);
  }

  private String formatFallbackLine(ResolvedEnchantment enchantment) {
    return String.valueOf(this.plugin.colorize("&5&l" + prettify(enchantment.primaryIdentifier))) + " " + enchantment.level;
  }

  private ResolvedEnchantment removeMatch(List<ResolvedEnchantment> enchantments, EnchantmentDefinition definition) {
    Iterator<ResolvedEnchantment> iterator = enchantments.iterator();
    while (iterator.hasNext()) {
      ResolvedEnchantment enchantment = iterator.next();
      if (definition.matches(enchantment)) {
        iterator.remove();
        return enchantment;
      }
    }
    return null;
  }

  private List<ResolvedEnchantment> resolveEnchantments(ItemStack itemStack) {
    List<ResolvedEnchantment> enchantments = new ArrayList<>();
    for (Map.Entry<Enchantment, Integer> entry : itemStack.getEnchantments().entrySet())
      enchantments.add(new ResolvedEnchantment(entry.getKey(), ((Integer)entry.getValue()).intValue(), resolveAliases(entry.getKey())));
    return enchantments;
  }

  private Set<String> resolveAliases(Enchantment enchantment) {
    LinkedHashSet<String> aliases = new LinkedHashSet<>();
    String modernIdentifier = resolveModernIdentifier(enchantment);
    if (modernIdentifier != null)
      aliases.add(normalizeIdentifier(modernIdentifier));
    String legacyIdentifier = enchantment.getName();
    if (legacyIdentifier != null)
      aliases.add(normalizeIdentifier(legacyIdentifier));
    return aliases;
  }

  private String resolveModernIdentifier(Enchantment enchantment) {
    String keyOrThrowIdentifier = resolveIdentifier(enchantment, "getKeyOrThrow");
    if (keyOrThrowIdentifier != null)
      return keyOrThrowIdentifier;
    return resolveIdentifier(enchantment, "getKey");
  }

  private String resolveIdentifier(Enchantment enchantment, String methodName) {
    try {
      Method getKeyMethod = enchantment.getClass().getMethod(methodName, new Class[0]);
      Object namespacedKey = getKeyMethod.invoke(enchantment, new Object[0]);
      if (namespacedKey == null)
        return null;
      Method keyGetterMethod = namespacedKey.getClass().getMethod("getKey", new Class[0]);
      Object keyValue = keyGetterMethod.invoke(namespacedKey, new Object[0]);
      return (keyValue == null) ? null : keyValue.toString();
    } catch (ReflectiveOperationException ignored) {
      return null;
    }
  }

  private String normalizeIdentifier(String identifier) {
    String normalized = identifier.toLowerCase(Locale.ROOT);
    int separatorIndex = normalized.indexOf(':');
    return (separatorIndex >= 0) ? normalized.substring(separatorIndex + 1) : normalized;
  }

  private String prettify(String identifier) {
    String[] parts = identifier.split("_");
    StringBuilder builder = new StringBuilder();
    for (String part : parts) {
      if (part.isEmpty())
        continue;
      if (builder.length() > 0)
        builder.append(' ');
      builder.append(Character.toUpperCase(part.charAt(0)));
      if (part.length() > 1)
        builder.append(part.substring(1).toLowerCase(Locale.ROOT));
    }
    return builder.toString();
  }

  private static final class EnchantmentDefinition {
    private final String configPath;
    private final String legacyConfigPath;
    private final String fallbackLabel;
    private final boolean showLevel;
    private final Set<String> aliases;

    private EnchantmentDefinition(String configPath, String legacyConfigPath, String fallbackLabel, boolean showLevel, String... aliases) {
      this.configPath = configPath;
      this.legacyConfigPath = legacyConfigPath;
      this.fallbackLabel = fallbackLabel;
      this.showLevel = showLevel;
      this.aliases = new LinkedHashSet<>();
      for (String alias : aliases)
        this.aliases.add(alias.toLowerCase(Locale.ROOT));
    }

    private boolean matches(ResolvedEnchantment enchantment) {
      for (String alias : enchantment.aliases) {
        if (this.aliases.contains(alias))
          return true;
      }
      return false;
    }

    private static EnchantmentDefinition withLevel(String configPath, String fallbackLabel, String... aliases) {
      return new EnchantmentDefinition(configPath, null, fallbackLabel, true, aliases);
    }

    private static EnchantmentDefinition withLevel(String configPath, String legacyConfigPath, String fallbackLabel, String... aliases) {
      return new EnchantmentDefinition(configPath, legacyConfigPath, fallbackLabel, true, aliases);
    }

    private static EnchantmentDefinition withoutLevel(String configPath, String fallbackLabel, String... aliases) {
      return new EnchantmentDefinition(configPath, null, fallbackLabel, false, aliases);
    }

    private static EnchantmentDefinition withoutLevel(String configPath, String legacyConfigPath, String fallbackLabel, String... aliases) {
      return new EnchantmentDefinition(configPath, legacyConfigPath, fallbackLabel, false, aliases);
    }
  }

  private static final class ResolvedEnchantment {
    private final int level;
    private final Set<String> aliases;
    private final String primaryIdentifier;

    private ResolvedEnchantment(Enchantment enchantment, int level, Set<String> aliases) {
      this.level = level;
      this.aliases = aliases;
      this.primaryIdentifier = aliases.isEmpty() ? enchantment.getName().toLowerCase(Locale.ROOT) : aliases.iterator().next();
    }
  }
}
