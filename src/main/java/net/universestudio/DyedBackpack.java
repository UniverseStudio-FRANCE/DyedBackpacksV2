package net.universestudio;

import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.implementation.items.backpacks.SlimefunBackpack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NonNull;

public class DyedBackpack extends SlimefunBackpack {
    private final BackpackColor color;

    public DyedBackpack(int size, ItemGroup itemGroup, SlimefunItemStack item, SlimefunItemStack backpack, BackpackColor color) {
        super(size, itemGroup, item, RecipeType.ENHANCED_CRAFTING_TABLE, createRecipe(backpack, color));
        this.color = color;
    }

    /**
     * This returns the {@link BackpackColor} of this {@link DyedBackpack}.
     *
     * @return The {@link BackpackColor}
     */
    @NonNull
    public BackpackColor getColor() {
        return color;
    }

    @NonNull
    private static ItemStack[] createRecipe(SlimefunItemStack backpack, BackpackColor color) {
        ItemStack wool = new ItemStack(color.getWoolMaterial());
        ItemStack leather = new ItemStack(Material.LEATHER);

        return new ItemStack[] {
            wool, wool, wool,
            leather, backpack.item(), leather,
            wool, wool, wool,
        };
    }
}
