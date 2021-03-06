package com.vanhal.progressiveautomation.items;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.vanhal.progressiveautomation.ref.Ref;
import com.vanhal.progressiveautomation.ref.ToolHelper;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemStoneUpgrade extends ItemUpgrade {
	public ItemStoneUpgrade() {
		super("StoneUpgrade", ToolHelper.LEVEL_STONE);
		this.setTextureName(Ref.MODID+":Stone_Upgrade");
	}
	
	protected void addNormalRecipe() {
		ShapedOreRecipe recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
			"ppp", "prp", "ppp", 'p', Blocks.stone, 'r', Items.redstone});
		GameRegistry.addRecipe(recipe);
	}
	
	protected void addUpgradeRecipe() {
		ShapedOreRecipe recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
			"ppp", "prp", "ppp", 'p', Blocks.stone, 'r', PAItems.woodUpgrade});
		GameRegistry.addRecipe(recipe);
	}
}
