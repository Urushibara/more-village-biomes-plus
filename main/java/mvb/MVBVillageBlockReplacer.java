package mvb;
import java.util.Random;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;
public class MVBVillageBlockReplacer 
{
   @SubscribeEvent
   public void OnGetVillageBlockID(BiomeEvent.GetVillageBlockID event) {
	  event.replacement = mvbReplaceBlock(event.biome, event.original);
      event.setResult(Event.Result.DENY);
   }
   
   public static IBlockState mvbReplaceBlock(BiomeGenBase biome, IBlockState var1, int ... var2) {
      int biomeID = MVBBiome.ParentBiome(biome).biomeID;

      Random rr = new Random();
      if (biomeID == MVBBiome.Plains.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.ORANGE);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return var1;
         }
      } else
      if (biomeID == MVBBiome.Desert.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.BROWN);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.sandstone.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return MVBBlock.Meta(MVBBlock.sandstone, 0);
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.Meta(MVBBlock.sandstone, 2);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.sandstone_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.sandstone_stairs);
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return MVBBlock.sandstone.getDefaultState();
         }
      } else
      if (biomeID == MVBBiome.Extreme_Hills.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.RED);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.double_stone_slab.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return MVBBlock.stone.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.Meta(MVBBlock.stonebrick, 2);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.stone_brick_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.stone_brick_stairs);
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return MVBBlock.brick_block.getDefaultState();
         }
      } else
      if (biomeID == MVBBiome.Forest.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.LIME);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.Meta(MVBBlock.log, MVBBlock.OAK);
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.Meta(MVBBlock.planks, MVBBlock.BIRCH);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.birch_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.oak_stairs);
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return (var2.length > 0 ? MVBBlock.Meta(MVBBlock.planks, var2[0]) : MVBBlock.Meta(MVBBlock.planks, MVBBlock.OAK));
         } else
         if (var1.getBlock() == MVBBlock.fence) {
            return MVBBlock.birch_fence.getDefaultState();
         }
      } else
      if (biomeID == MVBBiome.Ice_Plains.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.wool.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.snow.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return MVBBlock.snow.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.packed_ice.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.spruce_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return MVBBlock.ice.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.fence) {
            return MVBBlock.cobblestone_wall.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.wooden_pressure_plate) {
            return MVBBlock.Meta(MVBBlock.carpet, MVBBlock.GRAY);
         }
      } else
      if (biomeID == MVBBiome.Jungle.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.GREEN);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.Meta(MVBBlock.log, MVBBlock.JUNGLE);
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            if (rr.nextInt(2) == 0) {
               return MVBBlock.cobblestone.getDefaultState();
            } else {
               return MVBBlock.mossy_cobblestone.getDefaultState();
            }
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.Meta(MVBBlock.planks, MVBBlock.JUNGLE);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.jungle_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.jungle_stairs);
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return MVBBlock.stonebrick.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.fence) {
            return MVBBlock.jungle_fence.getDefaultState();
         }
      } else
      if (biomeID == MVBBiome.Swampland.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.CYAN);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return MVBBlock.Meta(MVBBlock.stained_hardened_clay, MVBBlock.CYAN);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return (var2.length > 0 ? (var2[0] == 0 ? MVBBlock.Meta(MVBBlock.stained_hardened_clay, MVBBlock.LIGHT_BLUE) : MVBBlock.clay.getDefaultState()) : MVBBlock.clay.getDefaultState());
         }
      } else
      if (biomeID == MVBBiome.Mega_Taiga.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.BROWN);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.Meta(MVBBlock.planks, MVBBlock.SPRUCE);
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            if (rr.nextInt(2) == 0) {
               return MVBBlock.cobblestone.getDefaultState();
            } else {
               return MVBBlock.mossy_cobblestone.getDefaultState();
            }
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.spruce_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.spruce_stairs);
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.Meta(MVBBlock.planks, MVBBlock.SPRUCE);
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.fence) {
            if (rr.nextInt(2) == 0) {
               return MVBBlock.Meta(MVBBlock.cobblestone_wall, 0);
            } else {
               return MVBBlock.Meta(MVBBlock.cobblestone_wall, 1);
            }
         } else
         if (var1.getBlock() == MVBBlock.wooden_pressure_plate) {
            return MVBBlock.Meta(MVBBlock.carpet, MVBBlock.GRAY);
         }
      } else
      if (biomeID == MVBBiome.Taiga.biomeID || biomeID == MVBBiome.Cold_Taiga.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.BROWN);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.Meta(MVBBlock.planks, MVBBlock.SPRUCE);
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            if (biomeID == MVBBiome.Taiga.biomeID) return var1;
            if (biomeID == MVBBiome.Cold_Taiga.biomeID) return MVBBlock.snow.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.Meta(MVBBlock.planks, MVBBlock.SPRUCE);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.spruce_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.spruce_stairs);
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return MVBBlock.cobblestone.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.fence) {
            return MVBBlock.spruce_fence.getDefaultState();
         }
      } else
      if (biomeID == MVBBiome.MushroomIsland.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.RED);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.Meta(MVBBlock.red_mushroom_block, 14);
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return MVBBlock.Meta(MVBBlock.red_mushroom_block, 15);
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            //return mvbBlock.Meta(mvbBlock.brick_block, 0);
            return MVBBlock.Meta(MVBBlock.brown_mushroom_block, 14);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            //return mvbBlock.Cnv(var1, mvbBlock.brick_stairs);
            return MVBBlock.Meta(MVBBlock.red_mushroom_block, 14);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            //return mvbBlock.Cnv(var1, mvbBlock.brick_stairs);
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return MVBBlock.Meta(MVBBlock.brown_mushroom_block, 0);
         } else
         if (var1.getBlock() == MVBBlock.fence) {
            return MVBBlock.cobblestone_wall.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.wooden_pressure_plate) {
            return MVBBlock.Meta(MVBBlock.carpet, MVBBlock.GRAY);
         }
      } else
      if (biomeID == MVBBiome.Birch_Forest.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.WHITE);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.Meta(MVBBlock.planks, MVBBlock.BIRCH);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.birch_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.birch_stairs);
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return (var2.length > 0 && var2[0] == 0 ? MVBBlock.Meta(MVBBlock.planks, MVBBlock.BIRCH) : MVBBlock.Meta(MVBBlock.planks, MVBBlock.OAK));
         } else
         if (var1.getBlock() == MVBBlock.fence) {
            return MVBBlock.birch_fence.getDefaultState();
         }
      } else
      if (biomeID == MVBBiome.Roofed_Forest.biomeID) // Roofed Forest
      {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.GREEN);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.Meta(MVBBlock.log2, MVBBlock.DARK_OAK - 4);
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return MVBBlock.cobblestone.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            //return var1;
            return MVBBlock.Meta(MVBBlock.planks, MVBBlock.DARK_OAK);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.dark_oak_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return (var2.length > 0 && var2[0] == 0 ? MVBBlock.Meta(MVBBlock.planks, MVBBlock.DARK_OAK) : MVBBlock.Meta(MVBBlock.planks, MVBBlock.OAK));
         } else
         if (var1.getBlock() == MVBBlock.fence) {
            return MVBBlock.dark_oak_fence.getDefaultState();
         }
      } else
      if (biomeID == MVBBiome.Savanna.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.ORANGE);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.Meta(MVBBlock.log2, 0);
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return MVBBlock.Meta(MVBBlock.double_stone_slab, 8);
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.Meta(MVBBlock.planks, MVBBlock.ACACIA);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.acacia_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return MVBBlock.Meta(MVBBlock.dirt, 1);
         } else
         if (var1.getBlock() == MVBBlock.fence) {
            return MVBBlock.acacia_fence.getDefaultState();
         }
      } else
      if (biomeID == MVBBiome.Mesa.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.PURPLE);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.Meta(MVBBlock.stained_hardened_clay, MVBBlock.WHITE);
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return MVBBlock.Meta(MVBBlock.stained_hardened_clay, MVBBlock.BLACK);
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.Meta(MVBBlock.stained_hardened_clay, MVBBlock.ORANGE);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.red_sandstone_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.red_sandstone_stairs);
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return MVBBlock.Meta(MVBBlock.stained_hardened_clay, MVBBlock.WHITE);
         }
      } else
      if (biomeID == MVBBiome.Ocean.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.WHITE);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return var1;
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return MVBBlock.Meta(MVBBlock.log, 4);
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.Meta(MVBBlock.leaves, 4);
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Meta(MVBBlock.leaves, 7);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.oak_stairs);
         } else
         if (var1.getBlock() == MVBBlock.gravel) {
            return MVBBlock.Meta(MVBBlock.planks, MVBBlock.SPRUCE);
         } else
         if (var1.getBlock() == MVBBlock.flowing_lava) {
            return MVBBlock.flowing_water.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.dirt) {
            return MVBBlock.stone.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.stone) {
           return MVBBlock.planks.getDefaultState();
         }
      } else
      if (biomeID == MVBBiome.FrozenOcean.biomeID) {
         if (var1.getBlock() == MVBBlock.wool) {
            return MVBBlock.Meta(MVBBlock.wool, MVBBlock.WHITE);
         } else
         if (var1.getBlock() == MVBBlock.log || var1.getBlock() == MVBBlock.log2) {
            return MVBBlock.hardened_clay.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.cobblestone) {
            return MVBBlock.hardened_clay.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.planks) {
            return MVBBlock.stonebrick.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.oak_stairs) {
            return MVBBlock.Cnv(var1, MVBBlock.stone_brick_stairs);
         } else
         if (var1.getBlock() == MVBBlock.stone_stairs) {
             return MVBBlock.Cnv(var1, MVBBlock.stone_brick_stairs);
         } else
         if (var1.getBlock() == MVBBlock.dirt) {
            return MVBBlock.stone.getDefaultState();
         } else
         if (var1.getBlock() == MVBBlock.stone) {
            return MVBBlock.planks.getDefaultState();
         }
      }
      return var1;
   }
   
   public static Block replace_doors(BiomeGenBase biome)
   {
	  biome = MVBBiome.ParentBiome(biome);
      if (biome.biomeID == MVBBiome.Forest.biomeID) {
          return MVBBlock.birch_door;
       } else
       if (biome.biomeID == MVBBiome.Taiga.biomeID || biome.biomeID == MVBBiome.Cold_Taiga.biomeID || biome.biomeID == MVBBiome.Mega_Taiga.biomeID) {
          return MVBBlock.spruce_door;
       } else
       if (biome.biomeID == MVBBiome.Jungle.biomeID) {
          return MVBBlock.jungle_door;
       } else
       if (biome.biomeID == MVBBiome.MushroomIsland.biomeID) {
          return MVBBlock.air;
       } else
       if (biome.biomeID == MVBBiome.Ice_Plains.biomeID) {
          return MVBBlock.spruce_door;
       } else
       if (biome.biomeID == MVBBiome.Birch_Forest.biomeID) {
          return MVBBlock.birch_door;
       } else
       if (biome.biomeID == MVBBiome.Roofed_Forest.biomeID) {
          return MVBBlock.dark_oak_door;
       } else
       if (biome.biomeID == MVBBiome.Savanna.biomeID) {
          return MVBBlock.acacia_door;
       }
       return MVBBlock.wooden_door;
   }
}