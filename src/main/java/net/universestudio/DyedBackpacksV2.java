package net.universestudio;

import io.github.thebusybiscuit.slimefun5.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.researches.Research;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun5.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import io.github.thebusybiscuit.slimefun5.utils.HeadTexture;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

public class DyedBackpacksV2 extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        Config cfg = new Config(this);

        Research research = new Research(new NamespacedKey(this, "dyed_backpacks"), 17200, "Dyed Backpacks", 24);
        ItemGroup itemGroup = new ItemGroup(new NamespacedKey(this, "dyed_backpacks"), CustomItemStack.create(HeadTexture.valueOf(BackpackColor.RED.getTexture()).getAsItemStack(), "&4Dyed Backpacks"), 2);

        if (cfg.getBoolean("backpacks.small")) {
            createBackpacks(itemGroup, research, SlimefunItems.BACKPACK_SMALL, 9);
        }
        if (cfg.getBoolean("backpacks.normal")) {
            createBackpacks(itemGroup, research, SlimefunItems.BACKPACK_MEDIUM, 18);
        }
        if (cfg.getBoolean("backpacks.large")) {
            createBackpacks(itemGroup, research, SlimefunItems.BACKPACK_LARGE, 27);
        }
        if (cfg.getBoolean("backpacks.woven")) {
            createBackpacks(itemGroup, research, SlimefunItems.WOVEN_BACKPACK, 36);
        }
        if (cfg.getBoolean("backpacks.gilded")) {
            createBackpacks(itemGroup, research, SlimefunItems.GILDED_BACKPACK, 45);
        }
        if (cfg.getBoolean("backpacks.radiant")) {
            createBackpacks(itemGroup, research, SlimefunItems.RADIANT_BACKPACK, 54);
        }

        research.register();
    }

    private void createBackpacks(ItemGroup itemGroup, Research research, SlimefunItemStack backpack, int size) {
        for (BackpackColor color : BackpackColor.values()) {
            SlimefunItemStack item = new SlimefunItemStack("DYED_" + backpack.getItemId() + "_" + color.name(), color.getTexture(), backpack.getItemMeta().getDisplayName() + " &7(" + color.getName() + "&7)", backpack.getItemMeta().getLore().toArray(new String[0]));
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
