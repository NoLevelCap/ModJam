package com.vanhal.progressiveautomation.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.vanhal.progressiveautomation.ProgressiveAutomation;
import com.vanhal.progressiveautomation.entities.generator.TileGenerator;
import com.vanhal.progressiveautomation.entities.generator.TileGeneratorDiamond;
import com.vanhal.progressiveautomation.entities.generator.TileGeneratorIron;
import com.vanhal.progressiveautomation.entities.generator.TileGeneratorStone;
import com.vanhal.progressiveautomation.items.PAItems;
import com.vanhal.progressiveautomation.ref.Ref;
import com.vanhal.progressiveautomation.ref.ToolHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGenerator extends BaseBlock {
	public IIcon iconTop;
	public IIcon iconSide;
	public IIcon inactiveGenerator;
	public IIcon activeGenerator;

	public BlockGenerator(int level) {
		super("Generator", level);
		GUIid = ProgressiveAutomation.guiHandler.GeneratorGUI;
	}
	
	public TileEntity createNewTileEntity(World world, int var2) {
		if (blockLevel >= ToolHelper.LEVEL_DIAMOND) return new TileGeneratorDiamond();
		else if (blockLevel == ToolHelper.LEVEL_IRON) return new TileGeneratorIron();
		else if (blockLevel == ToolHelper.LEVEL_STONE) return new TileGeneratorStone();
		else return new TileGenerator();
	}
	
	public void addRecipe() {
		ShapedOreRecipe recipe = null;
		
		if (blockLevel == ToolHelper.LEVEL_STONE) {
			recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
				"ses", "scs", "sss", 's', Blocks.stone, 'c', PABlocks.woodenGenerator, 'e', PAItems.rfEngine});
		} else if (blockLevel == ToolHelper.LEVEL_IRON) {
			recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
				"ses", "scs", "sbs", 's', Items.iron_ingot, 'c', PABlocks.stoneGenerator, 'b', Blocks.iron_block, 'e', PAItems.rfEngine});
		} else if (blockLevel >= ToolHelper.LEVEL_DIAMOND) {
			recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
				"ses", "scs", "sss", 's', Items.diamond, 'c', PABlocks.ironGenerator, 'e', PAItems.rfEngine});
		} else {
			recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
				"sps", "ses", "srs", 's', Blocks.log, 'r', Blocks.furnace, 'p', Blocks.redstone_block, 'e', PAItems.rfEngine});
		}
		
		
		GameRegistry.addRecipe(recipe);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
		iconTop =  register.registerIcon(Ref.MODID+":generator/"+getLevelName()+"_Top");
		iconSide = register.registerIcon(Ref.MODID+":generator/"+getLevelName()+"_Side");
		inactiveGenerator = register.registerIcon(Ref.MODID+":generator/"+getLevelName()+"_Front");
		activeGenerator= register.registerIcon(Ref.MODID+":generator/"+getLevelName()+"_FrontActive");
    }
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
    	if (metadata==0) metadata = 3;
        return side == 1 ? iconTop : (side == 0 ? iconTop : (side != metadata ? iconSide : inactiveGenerator));
    }

	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
		int metadata = block.getBlockMetadata(x, y, z);
		TileGenerator entity = (TileGenerator) block.getTileEntity(x, y, z);
		if ( (side==metadata) && (entity.isBurning()) ) return activeGenerator;
        return this.getIcon(side, metadata);
    }
    
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (itemStack.hasDisplayName()) {
            ((TileEntityFurnace)world.getTileEntity(x, y, z)).func_145951_a(itemStack.getDisplayName());
        }
    }
}
