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
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Tools.Efficiency", "Efficiency", new String[] { "DIG_SPEED", "efficiency" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Tools.Fortune", "Fortune", new String[] { "LOOT_BONUS_BLOCKS", "fortune" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Tools.Silktouch", "Silktouch", new String[] { "SILK_TOUCH", "silk_touch" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Tools.Unbreaking", "Unbreaking", new String[] { "DURABILITY", "unbreaking" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Swords.Sharpness", "Sharpness", new String[] { "DAMAGE_ALL", "sharpness" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Swords.Smite", "Smite", new String[] { "DAMAGE_UNDEAD", "smite" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Swords.FireAspect", "Fire Aspect", new String[] { "FIRE_ASPECT", "fire_aspect" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Swords.Looting", "Looting", new String[] { "LOOT_BONUS_MOBS", "looting" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Swords.KnockBack", "KnockBack", new String[] { "KNOCKBACK", "knockback" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Swords.BaneOfArthropods", "Bane Of Arthropods", new String[] { "DAMAGE_ARTHROPODS", "bane_of_arthropods" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Swords.SweepingEdge", "Sweeping Edge", new String[] { "SWEEPING_EDGE", "sweeping_edge" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Protection.Environmental", "Protection", new String[] { "PROTECTION_ENVIRONMENTAL", "protection" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Protection.Blast", "Blast Protection", new String[] { "PROTECTION_EXPLOSIONS", "blast_protection" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Protection.Fire", "Fire Protection", new String[] { "PROTECTION_FIRE", "fire_protection" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Protection.Projectile", "Projectile Protection", new String[] { "PROTECTION_PROJECTILE", "projectile_protection" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.FeatherFalling", "Feather Falling", new String[] { "PROTECTION_FALL", "feather_falling" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.FrostWalker", "Frost Walker", new String[] { "FROST_WALKER", "frost_walker" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Thorns", "Thorns", new String[] { "THORNS", "thorns" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.Respiration", "Respiration", new String[] { "OXYGEN", "respiration" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.DepthStrider", "Depth Strider", new String[] { "DEPTH_STRIDER", "depth_strider" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Armor.AquaAffinity", "Aqua Affinity", new String[] { "WATER_WORKER", "aqua_affinity" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Bow.Power", "Power", new String[] { "ARROW_DAMAGE", "power" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Bow.Punch", "Punch", new String[] { "ARROW_KNOCKBACK", "punch" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Bow.Flame", "Flame", new String[] { "ARROW_FIRE", "flame" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.Bow.Infinity", "Infinity", new String[] { "ARROW_INFINITE", "infinity" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.FishingRod.Luck", "Luck", new String[] { "LUCK", "luck_of_the_sea" }),
            EnchantmentDefinition.withLevel("HoloItem.Enchantments.FishingRod.Lure", "Lure", new String[] { "LURE", "lure" }),
            EnchantmentDefinition.withoutLevel("HoloItem.Enchantments.Extras.Mending", "Mending", new String[] { "MENDING", "mending" }),
            EnchantmentDefinition.withoutLevel("HoloItem.Enchantments.Extras.Binding", "Curse of Binding", new String[] { "BINDING_CURSE", "binding_curse", "curse_of_binding" }),
            EnchantmentDefinition.withoutLevel("HoloItem.Enchantments.Extras.Vanishing", "Curse of Vanishing", new String[] { "VANISHING_CURSE", "vanishing_curse", "curse_of_vanishing" }) }));

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
    String label = this.plugin.getColoredConfigString(definition.configPath, "&5&l" + definition.fallbackLabel);
    return definition.showLevel ? (String.valueOf(label) + " " + level) : label;
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
    try {
      Method getKeyMethod = enchantment.getClass().getMethod("getKey", new Class[0]);
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
    private final String fallbackLabel;
    private final boolean showLevel;
    private final Set<String> aliases;

    private EnchantmentDefinition(String configPath, String fallbackLabel, boolean showLevel, String... aliases) {
      this.configPath = configPath;
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
      return new EnchantmentDefinition(configPath, fallbackLabel, true, aliases);
    }

    private static EnchantmentDefinition withoutLevel(String configPath, String fallbackLabel, String... aliases) {
      return new EnchantmentDefinition(configPath, fallbackLabel, false, aliases);
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
