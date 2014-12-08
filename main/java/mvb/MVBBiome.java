package mvb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;

public class MVBBiome {

   private static BiomeGenBase Id2Biome(int id)
   {
	   BiomeGenBase ret = BiomeGenBase.getBiome(id); 
       return ret == null ? BiomeGenBase.getBiome(0) : ret;
   }

   public static final BiomeGenBase Ocean = Id2Biome(0);
   public static final BiomeGenBase River = Id2Biome(7);
   public static final BiomeGenBase FrozenOcean = Id2Biome(10);
   public static final BiomeGenBase FrozenRiver = Id2Biome(11);
   public static final BiomeGenBase Beach = Id2Biome(16);
   public static final BiomeGenBase Deep_Ocean = Id2Biome(24);
   public static final BiomeGenBase Stone_Beach = Id2Biome(25);
   public static final BiomeGenBase Cold_Beach = Id2Biome(26);

   public static final BiomeGenBase Plains = Id2Biome(1);

   public static final BiomeGenBase Desert = Id2Biome(2);
   public static final BiomeGenBase DesertHills = Id2Biome(17);

   public static final BiomeGenBase Extreme_Hills = Id2Biome(3);
   public static final BiomeGenBase Extreme_Hills_Edge = Id2Biome(20);
   public static final BiomeGenBase Extreme_Hills_Plus = Id2Biome(34);

   public static final BiomeGenBase Forest = Id2Biome(4);
   public static final BiomeGenBase ForestHills = Id2Biome(18);

   public static final BiomeGenBase Taiga = Id2Biome(5);
   public static final BiomeGenBase TaigaHills = Id2Biome(19);

   public static final BiomeGenBase Swampland = Id2Biome(6);

   public static final BiomeGenBase Ice_Plains = Id2Biome(12);
   public static final BiomeGenBase Ice_Mountains = Id2Biome(13);

   public static final BiomeGenBase MushroomIsland = Id2Biome(14);
   public static final BiomeGenBase MushroomIslandShore = Id2Biome(15);

   public static final BiomeGenBase Jungle = Id2Biome(21);
   public static final BiomeGenBase JungleHills = Id2Biome(22);
   public static final BiomeGenBase JungleEdge = Id2Biome(23);

   public static final BiomeGenBase Birch_Forest = Id2Biome(27);
   public static final BiomeGenBase Birch_Forest_Hills = Id2Biome(28);

   public static final BiomeGenBase Roofed_Forest = Id2Biome(29);

   public static final BiomeGenBase Cold_Taiga = Id2Biome(30);
   public static final BiomeGenBase Cold_Taiga_Hills = Id2Biome(31);

   public static final BiomeGenBase Mega_Taiga = Id2Biome(32);
   public static final BiomeGenBase Mega_Taiga_Hills = Id2Biome(33);

   public static final BiomeGenBase Savanna = Id2Biome(35);
   public static final BiomeGenBase Savanna_Plateau = Id2Biome(36);

   public static final BiomeGenBase Mesa = Id2Biome(37);
   public static final BiomeGenBase Mesa_Plateau_F = Id2Biome(38);
   public static final BiomeGenBase Mesa_Plateau = Id2Biome(39);

   public static final BiomeGenBase Sunflower_Plains = Id2Biome(129);
   public static final BiomeGenBase Desert_M = Id2Biome(130);
   public static final BiomeGenBase Extreme_Hills_M = Id2Biome(131);
   public static final BiomeGenBase Flower_Forest = Id2Biome(132);
   public static final BiomeGenBase Taiga_M = Id2Biome(133);
   public static final BiomeGenBase Swampland_M = Id2Biome(134);
   public static final BiomeGenBase Ice_Plains_Spikes = Id2Biome(140);
   public static final BiomeGenBase Jungle_M = Id2Biome(149);
   public static final BiomeGenBase JungleEdge_M = Id2Biome(151);
   public static final BiomeGenBase Birch_Forest_M = Id2Biome(155);
   public static final BiomeGenBase Birch_Forest_Hills_M = Id2Biome(156);
   public static final BiomeGenBase Roofed_Forest_M = Id2Biome(157);
   public static final BiomeGenBase Cold_Taiga_M = Id2Biome(158);
   public static final BiomeGenBase Mega_Spruce_Taiga = Id2Biome(160);
   public static final BiomeGenBase Mega_Spruce_Taiga_Hills = Id2Biome(161);
   public static final BiomeGenBase Extreme_Hills_Plus_M = Id2Biome(162);
   public static final BiomeGenBase Savanna_M = Id2Biome(163);
   public static final BiomeGenBase Savanna_Plateau_M = Id2Biome(164);
   public static final BiomeGenBase Mesa_Bryce = Id2Biome(165);
   public static final BiomeGenBase Mesa_Plateau_F_M = Id2Biome(166);
   public static final BiomeGenBase Mesa_Plateau_M = Id2Biome(167);
   

   public static BiomeGenBase ParentBiome(BiomeGenBase biome)
   {
	  if (biome == null) return MVBBiome.Plains; 
	  
      if (biome == MVBBiome.Sunflower_Plains) {
         return MVBBiome.Plains;
      } else if (biome == MVBBiome.DesertHills || biome == MVBBiome.Desert_M) {
         return MVBBiome.Desert;
      } else if (biome == MVBBiome.Extreme_Hills_Edge || biome == MVBBiome.Extreme_Hills_Plus || biome == MVBBiome.Extreme_Hills_M || biome == MVBBiome.Extreme_Hills_Plus_M) {
         return MVBBiome.Extreme_Hills;
      } else if (biome == MVBBiome.ForestHills || biome == MVBBiome.Flower_Forest) {
         return MVBBiome.Forest;
      } else if (biome == MVBBiome.TaigaHills || biome == MVBBiome.Taiga_M) {
         return MVBBiome.Taiga;
      } else if (biome == MVBBiome.Swampland_M) {
         return MVBBiome.Swampland;
      } else if (biome == MVBBiome.JungleHills || biome == MVBBiome.JungleEdge || biome == MVBBiome.Jungle_M || biome == MVBBiome.JungleEdge_M) {
         return MVBBiome.Jungle;
      } else if (biome == MVBBiome.Ice_Mountains || biome == MVBBiome.Ice_Plains_Spikes) {
         return MVBBiome.Ice_Plains;
      } else if (biome == MVBBiome.MushroomIslandShore) {
         return MVBBiome.MushroomIsland;
      } else if (biome == MVBBiome.Birch_Forest_Hills || biome == MVBBiome.Birch_Forest_M || biome == MVBBiome.Birch_Forest_Hills_M) {
         return MVBBiome.Birch_Forest;
      } else if (biome == MVBBiome.Cold_Taiga_Hills || biome == MVBBiome.Cold_Taiga_M) {
         return MVBBiome.Cold_Taiga;
      } else if (biome == MVBBiome.Mega_Taiga_Hills || biome == MVBBiome.Mega_Spruce_Taiga || biome == MVBBiome.Mega_Spruce_Taiga_Hills) {
         return MVBBiome.Mega_Taiga;
      } else if (biome == MVBBiome.Savanna_Plateau || biome == MVBBiome.Savanna_M || biome == MVBBiome.Savanna_Plateau_M) {
         return MVBBiome.Savanna;
      } else if (biome == MVBBiome.Mesa_Plateau_F || biome == MVBBiome.Mesa_Plateau || biome == MVBBiome.Mesa_Bryce || biome == MVBBiome.Mesa_Plateau_F_M || biome == MVBBiome.Mesa_Plateau_M) {
         return MVBBiome.Mesa;
      } else if (biome == MVBBiome.River || biome == MVBBiome.Beach) {
         return MVBBiome.Ocean;
      } else if (biome == MVBBiome.FrozenRiver || biome == MVBBiome.Cold_Beach || biome == MVBBiome.Stone_Beach) {
         return MVBBiome.FrozenOcean;
      }
      return biome;
   }

   static final String confname = "config/MoreVillageBiomes.txt";
   static final String defaultsetting = "#---------------------------------------\r\n# Village spawn Biomes\r\n#\r\n# <BiomeID>[:<DummyName>]=<true|false>\r\n\r\n1:Plains=true\r\n2:Desert=true\r\n17:DesertHills=true\r\n3:Extreme_Hills=true\r\n20:Extreme_Hills_Edge=true\r\n34:Extreme_Hills_Plus=true\r\n4:Forest=true\r\n18:ForestHills=true\r\n5:Taiga=true\r\n19:TaigaHills=true\r\n6:Swampland=true\r\n12:Ice_Plains=true\r\n13:Ice_Mountains=true\r\n14:MushroomIsland=true\r\n15:MushroomIslandShore=true\r\n21:Jungle=true\r\n22:JungleHills=true\r\n23:JungleEdge=true\r\n27:Birch_Forest=true\r\n28:Birch_Forest_Hills=true\r\n29:Roofed_Forest=true\r\n30:Cold_Taiga=true\r\n31:Cold_Taiga_Hills=true\r\n32:Mega_Taiga=true\r\n33:Mega_Taiga_Hills=true\r\n35:Savanna=true\r\n36:Savanna_Plateau=true\r\n37:Mesa=true\r\n38:Mesa_Plateau_F=true\r\n39:Mesa_Plateau=true\r\n129:Sunflower_Plains=true\r\n130:Desert_M=true\r\n131:Extreme_Hills_M=true\r\n132:Flower_Forest=true\r\n133:Taiga_M=true\r\n134:Swampland_M=true\r\n140:Ice_Plains_Spikes=true\r\n149:Jungle_M=true\r\n151:JungleEdge_M=true\r\n155:Birch_Forest_M=true\r\n156:Birch_Forest_Hills_M=true\r\n157:Roofed_Forest_M=true\r\n158:Cold_Taiga_M=true\r\n160:Mega_Spruce_Taiga=true\r\n161:Mega_Spruce_Taiga_Hills=true\r\n162:Extreme_Hills_Plus_M=true\r\n163:Savanna_M=true\r\n164:Savanna_Plateau_M=true\r\n165:Mesa_Bryce=true\r\n166:Mesa_Plateau_F_M=true\r\n167:Mesa_Plateau_M=true\r\n\r\n0:Ocean=true\r\n7:River=true\r\n10:FrozenOcean=true\r\n11:FrozenRiver=true\r\n16:Beach=true\r\n24:Deep_Ocean=false\r\n25:Stone_Beach=true\r\n26:Cold_Beach=true\r\n";

   public static List villageSpawnBiomes()
   {
      ArrayList < BiomeGenBase > ret = new ArrayList < BiomeGenBase > ();
      File file = new File(confname);
      if (file.exists())
      {
         FileReader filereader = null;
         BufferedReader bufreader = null;
         try {
            filereader = new FileReader(file);
            bufreader = new BufferedReader(filereader);
            String line;
            while ((line = bufreader.readLine()) != null)
            {
               if (line.indexOf("=") < 0) continue;
               line = line.replaceFirst("^\\s+", "");
               if (line.indexOf("#") == 0) continue;
               String[] sp = line.split("=");
               if (sp.length != 2) continue;
               String id = sp[0];
               if (sp[1].toLowerCase().equals("true"))
               {
                  if (sp[0].indexOf(":") >= -1) {
                     String[] idname = sp[0].split(":");
                     if (idname.length < 1) continue;
                     id = idname[0];
                  }
                  int idnum = -1;
                  try {
                     idnum = Integer.parseInt(id);
                  } catch (NumberFormatException e) {
                     continue;
                  }
                  ret.add(Id2Biome(idnum));
               }
            }
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } finally {
            try {
               filereader.close();
               bufreader.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      } else {
         FileWriter filewriter = null;
         try {
            filewriter = new FileWriter(file);
            filewriter.write(defaultsetting);
         } catch (IOException e) {
            e.printStackTrace();
         } finally {
            try {
               filewriter.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
         return null;
      }
      return ret;
   }
}
