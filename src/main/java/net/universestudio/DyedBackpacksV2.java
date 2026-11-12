package net.universestudio;

import io.github.thebusybiscuit.slimefun5.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun5.api.researches.Research;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun5.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import io.github.thebusybiscuit.slimefun5.utils.SlimefunUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

public class DyedBackpacksV2 extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        Config cfg = new Config(this);

        Research research = new Research(new NamespacedKey(this, "dyed_backpacks"), 17200, "Dyed Backpacks", 24);
        NestedItemGroup mainGroup = new NestedItemGroup(
            new NamespacedKey(this, "dyed_backpacks_group"),
            CustomItemStack.create(SlimefunUtils.getCustomHead(BackpackColor.RED.getTexture()), "&cDyed Backpacks")
        );
        mainGroup.setCategory("items");
        mainGroup.register(this);

        if (cfg.getBoolean("backpacks.small")) {
            SubItemGroup smallBackpacksGroup = new SubItemGroup(new NamespacedKey(this, "SMALL_BACKPACKS"), mainGroup,
                CustomItemStack.create(
                    SlimefunUtils.getCustomHead(BackpackColor.GREEN.getTexture()),
                    meta -> meta.customName(MiniMessage.miniMessage().deserialize("<green>Small Backpacks</green>"))
                )
            );

            createBackpacks(smallBackpacksGroup, research, SlimefunItems.BACKPACK_SMALL, 9);
        }

        if (cfg.getBoolean("backpacks.normal")) {
            SubItemGroup mediumBackpacksGroup = new SubItemGroup(new NamespacedKey(this, "MEDIUM_BACKPACKS"), mainGroup,
                CustomItemStack.create(
                    SlimefunUtils.getCustomHead(BackpackColor.LIGHT_GRAY.getTexture()),
                    meta -> meta.customName(MiniMessage.miniMessage().deserialize("<gray>Medium Backpacks</gray>"))
                )
            );

            createBackpacks(mediumBackpacksGroup, research, SlimefunItems.BACKPACK_MEDIUM, 18);
        }

        if (cfg.getBoolean("backpacks.large")) {
            SubItemGroup largeBackpacksGroup = new SubItemGroup(new NamespacedKey(this, "LARGE_BACKPACKS"), mainGroup,
                CustomItemStack.create(
                    SlimefunUtils.getCustomHead(BackpackColor.RED.getTexture()),
                    meta -> meta.customName(MiniMessage.miniMessage().deserialize("<red>Large Backpacks</red>"))
                )
            );

            createBackpacks(largeBackpacksGroup, research, SlimefunItems.BACKPACK_LARGE, 27);
        }

        if (cfg.getBoolean("backpacks.woven")) {
            SubItemGroup wovenBackpacksGroup = new SubItemGroup(new NamespacedKey(this, "WOVEN_BACKPACKS"), mainGroup,
                CustomItemStack.create(
                    SlimefunUtils.getCustomHead(BackpackColor.YELLOW.getTexture()),
                    meta -> meta.customName(MiniMessage.miniMessage().deserialize("<yellow>Woven Backpacks</yellow>"))
                )
            );

            createBackpacks(wovenBackpacksGroup, research, SlimefunItems.WOVEN_BACKPACK, 36);
        }

        if (cfg.getBoolean("backpacks.gilded")) {
            SubItemGroup gildedBackpacksGroup = new SubItemGroup(new NamespacedKey(this, "GILDED_BACKPACKS"), mainGroup,
                CustomItemStack.create(
                    SlimefunUtils.getCustomHead(BackpackColor.ORANGE.getTexture()),
                    meta -> meta.customName(MiniMessage.miniMessage().deserialize("<gold>Gilded Backpacks</gold>"))
                )
            );

            createBackpacks(gildedBackpacksGroup, research, SlimefunItems.GILDED_BACKPACK, 45);
        }

        if (cfg.getBoolean("backpacks.radiant")) {
            SubItemGroup radiantBackpacksGroup = new SubItemGroup(new NamespacedKey(this, "RADIANT_BACKPACKS"), mainGroup,
                CustomItemStack.create(
                    SlimefunUtils.getCustomHead(BackpackColor.BLUE.getTexture()),
                    meta -> meta.customName(MiniMessage.miniMessage().deserialize("<blue>Radiant Backpacks</blue>"))
                )
            );

            createBackpacks(radiantBackpacksGroup, research, SlimefunItems.RADIANT_BACKPACK, 54);
        }

        research.register();
        Slimefun.getItemTranslationService().registerTranslations(this);
    }

    private void createBackpacks(ItemGroup itemGroup, Research research, SlimefunItemStack backpack, int size) {
        for (BackpackColor color : BackpackColor.values()) {
            String backpackId = "DYED_%s_%s".formatted(backpack.getItemId(), color.name());
            SlimefunItemStack item = new SlimefunItemStack(backpackId, SlimefunUtils.getCustomHead(color.getTexture()));
            DyedBackpack dyed = new DyedBackpack(size, itemGroup, item, backpack, color);
            research.addItems(dyed);
            dyed.register(this);
        }
    }

    @Override
    public @NonNull JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return null;
    }
}
