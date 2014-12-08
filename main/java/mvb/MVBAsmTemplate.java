package mvb;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.*;
import net.minecraftforge.event.terraingen.BiomeEvent;

public class MVBAsmTemplate extends net.minecraft.world.gen.structure.StructureVillagePieces.Village {

	private BiomeGenBase biome;
    private StructureVillagePieces.Start startPiece;

	// for Village
    void Village(StructureVillagePieces.Start p_i2107_1_, int p_i2107_2_)
    {
    	this.biome = p_i2107_1_.biome;
    }
	
	// for Village
	@Override
    protected int getAverageGroundLevel(World var1, StructureBoundingBox var2)
    {
        int max = var1.provider.getAverageGroundLevel();
        for (int var5 = this.boundingBox.minZ; var5 <= this.boundingBox.maxZ; ++var5) {
            for (int var6 = this.boundingBox.minX; var6 <= this.boundingBox.maxX; ++var6) {
                BlockPos var7 = new BlockPos(var6, 64, var5);
                if (var2.func_175898_b(var7)) {
                    max = Math.max(this.getTopSolidOrLiquidBlock(var1, var7).getY(), max);
                }
            }
        }
        max = max == 64 ? 63 : max;
        return max;
    }
//    protected int getAverageGroundLevel(World var1, StructureBoundingBox var2)
//    {
//        int var3 = 0;
//        int var4 = 0;
//        for (int var5 = this.boundingBox.minZ; var5 <= this.boundingBox.maxZ; ++var5) {
//            for (int var6 = this.boundingBox.minX; var6 <= this.boundingBox.maxX; ++var6) {
//                BlockPos var7 = new BlockPos(var6, 63, var5);
//                if (var2.func_175898_b(var7)) {
//                    int top = var1.provider.getAverageGroundLevel();
//                    top = top == 64 ? 63 : top;
//                    var3 += Math.max(this.getTopSolidOrLiquidBlock(var1, var7).getY(), top);
//                    ++var4;
//                }
//            }
//        }
//        if (var4 == 0) {
//            return -1;
//        }
//        else {
//            return var3 / var4;
//        }
//    }

	// for Village
    protected BlockPos getTopSolidOrLiquidBlock(World var0, BlockPos var1)
    {
        Chunk var2 = var0.getChunkFromBlockCoords(var1);
        BlockPos var3;
        BlockPos var4;
        for (var3 = new BlockPos(var1.getX(), var2.getTopFilledSegment() + 16, var1.getZ()); var3.getY() >= 0; var3 = var4) {
            var4 = var3.offsetDown();
            Material var5 = var2.getBlock(var4).getMaterial();
            if (var5 == Material.water || var5.blocksMovement() && var5 != Material.leaves) {
                break;
            }
        }
        return var3;
    }

	// for Village
	public BiomeGenBase getBiome() {
		return this.biome;
	}

	// for Village
	public void setBiome(BiomeGenBase biome) {
		this.biome = biome;
	}
	
	// for Village
	void A(IBlockState p_175847_1_)
	{
		BiomeEvent.GetVillageBlockID event = new BiomeEvent.GetVillageBlockID(
				startPiece == null ? 
						this.biome : 
						startPiece.biome
						, p_175847_1_);
	}
	
	// for Village
	@Override
    protected void func_175810_a(World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, EnumFacing var7)
    {
        BlockPos var8 = new BlockPos(this.getXWithOffset(var4, var6), this.getYWithOffset(var5), this.getZWithOffset(var4, var6));
        Block door = MVBVillageBlockReplacer.replace_doors(this.biome);

        if (door != MVBBlock.air && var2.func_175898_b(var8))
        {
            ItemDoor.func_179235_a(var1, var8, var7.rotateYCCW(), door);
        }
    }

	// for Start
    void Start(WorldChunkManager var1, int var2, Random var3, int var4, int var5, List var6, int var7)
    {
        this.setBiome(var1.func_180300_a(new BlockPos(var4, 0, var5), BiomeGenBase.plains));
    }

	@Override
	protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
	}

	// for Path
	@Override
    public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3)
    {
		BiomeGenBase biome = this.getBiome();
        IBlockState var5 = MVBVillageBlockReplacer.mvbReplaceBlock(biome, Blocks.cobblestone.getDefaultState());
        IBlockState[] tile = new IBlockState[] {
        		  MVBVillageBlockReplacer.mvbReplaceBlock(biome, Blocks.gravel.getDefaultState(), 2)
        		, MVBVillageBlockReplacer.mvbReplaceBlock(biome, Blocks.gravel.getDefaultState(), 0)
        		, MVBVillageBlockReplacer.mvbReplaceBlock(biome, Blocks.gravel.getDefaultState(), 1)
        		};

        for (int var6 = this.boundingBox.minX; var6 <= this.boundingBox.maxX; ++var6)
        {
            for (int var7 = this.boundingBox.minZ; var7 <= this.boundingBox.maxZ; ++var7)
            {
                BlockPos var8 = new BlockPos(var6, 64, var7);

                if (var3.func_175898_b(var8))
                {
                    var8 = this.getTopSolidOrLiquidBlock(var1, var8).offsetDown();
                    int pattern = (var6 + var7 % 2) % 2;
                    pattern = pattern < 0 ? 1 : pattern;

                    if (var1.getBlockState(var8).getBlock() == MVBBlock.water && tile[var6 % 2 == 0 && pattern == 0 ? 2 : pattern].getBlock() != MVBBlock.planks)
                    {
                        var1.setBlockState(var8, MVBBlock.planks.getDefaultState(), 2);
                    }
                    else
                    {
                        var1.setBlockState(var8, tile[var6 % 2 == 0 && pattern == 0 ? 2 : pattern], 2);
                    }

                    var1.setBlockState(var8.offsetDown(), var5, 2);
                }
            }
        }

        return true;
    }

}
