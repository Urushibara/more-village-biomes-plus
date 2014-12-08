package mvb.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mvb.MVBVillageBlockReplacer;
import mvb.MVBBiome;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = MVBInit.MODID, name = MVBInit.NAME, version = MVBInit.VERSION)
public class MVBInit
{
	static final String MODID = "MVB-FORGE";
	static final String NAME = "More Village Biomes+";
	static final String VERSION = "2.0.10.2";
	//@Instance("MVB-FORGE")
	//protected static mvbFMLInit instance;

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.TERRAIN_GEN_BUS.register(new MVBVillageBlockReplacer());

		List villageBiomes = MVBBiome.villageSpawnBiomes();
		if ((villageBiomes != null) && (villageBiomes.size() > 0))
		{
			Iterator itl = villageBiomes.iterator();
			while (itl.hasNext())
			{
				Object biome = itl.next();
				if ((biome != null) && ((biome instanceof BiomeGenBase)))
				{
					BiomeManager.addVillageBiome((BiomeGenBase) biome, true);
				}
			}
		}
		else
		{
			BiomeManager.addVillageBiome(MVBBiome.Ocean, true);
			BiomeManager.addVillageBiome(MVBBiome.River, true);
			BiomeManager.addVillageBiome(MVBBiome.FrozenOcean, true);
			BiomeManager.addVillageBiome(MVBBiome.FrozenRiver, true);
			BiomeManager.addVillageBiome(MVBBiome.Beach, true);

			BiomeManager.addVillageBiome(MVBBiome.Stone_Beach, true);
			BiomeManager.addVillageBiome(MVBBiome.Cold_Beach, true);
			BiomeManager.addVillageBiome(MVBBiome.DesertHills, true);
			BiomeManager.addVillageBiome(MVBBiome.Extreme_Hills, true);
			BiomeManager.addVillageBiome(MVBBiome.Extreme_Hills_Edge, true);
			BiomeManager.addVillageBiome(MVBBiome.Extreme_Hills_Plus, true);
			BiomeManager.addVillageBiome(MVBBiome.Forest, true);
			BiomeManager.addVillageBiome(MVBBiome.ForestHills, true);
			BiomeManager.addVillageBiome(MVBBiome.Taiga, true);
			BiomeManager.addVillageBiome(MVBBiome.TaigaHills, true);
			BiomeManager.addVillageBiome(MVBBiome.Swampland, true);
			BiomeManager.addVillageBiome(MVBBiome.Ice_Plains, true);
			BiomeManager.addVillageBiome(MVBBiome.Ice_Mountains, true);
			BiomeManager.addVillageBiome(MVBBiome.MushroomIsland, true);
			BiomeManager.addVillageBiome(MVBBiome.MushroomIslandShore, true);
			BiomeManager.addVillageBiome(MVBBiome.Jungle, true);
			BiomeManager.addVillageBiome(MVBBiome.JungleHills, true);
			BiomeManager.addVillageBiome(MVBBiome.JungleEdge, true);
			BiomeManager.addVillageBiome(MVBBiome.Birch_Forest, true);
			BiomeManager.addVillageBiome(MVBBiome.Birch_Forest_Hills, true);
			BiomeManager.addVillageBiome(MVBBiome.Roofed_Forest, true);
			BiomeManager.addVillageBiome(MVBBiome.Cold_Taiga, true);
			BiomeManager.addVillageBiome(MVBBiome.Cold_Taiga_Hills, true);
			BiomeManager.addVillageBiome(MVBBiome.Mega_Taiga, true);
			BiomeManager.addVillageBiome(MVBBiome.Mega_Taiga_Hills, true);
			BiomeManager.addVillageBiome(MVBBiome.Savanna_Plateau, true);
			BiomeManager.addVillageBiome(MVBBiome.Mesa, true);
			BiomeManager.addVillageBiome(MVBBiome.Mesa_Plateau_F, true);
			BiomeManager.addVillageBiome(MVBBiome.Mesa_Plateau, true);
			BiomeManager.addVillageBiome(MVBBiome.Sunflower_Plains, true);
			BiomeManager.addVillageBiome(MVBBiome.Desert_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Extreme_Hills_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Flower_Forest, true);
			BiomeManager.addVillageBiome(MVBBiome.Taiga_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Swampland_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Ice_Plains_Spikes, true);
			BiomeManager.addVillageBiome(MVBBiome.Jungle_M, true);
			BiomeManager.addVillageBiome(MVBBiome.JungleEdge_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Birch_Forest_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Birch_Forest_Hills_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Roofed_Forest_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Cold_Taiga_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Mega_Spruce_Taiga, true);
			BiomeManager.addVillageBiome(MVBBiome.Mega_Spruce_Taiga_Hills, true);
			BiomeManager.addVillageBiome(MVBBiome.Extreme_Hills_Plus_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Savanna_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Savanna_Plateau_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Mesa_Bryce, true);
			BiomeManager.addVillageBiome(MVBBiome.Mesa_Plateau_F_M, true);
			BiomeManager.addVillageBiome(MVBBiome.Mesa_Plateau_M, true);
		}
	}

	@Mod.EventHandler
	public void serverStarted(FMLServerStartedEvent paramFMLServerStartedEvent)
	{
		File file = new File("config/BiomeIDs.txt");
		FileWriter filewriter = null;
		try
		{
			filewriter = new FileWriter(file);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sdf.format(cal.getTime());
			filewriter.write(MVBInit.NAME + " ver" + MVBInit.VERSION + ": Generate date: " + strDate + "\r\n");
			filewriter.write("\r\n###   #  ###  #   # ####  ###\r\n#  #  # #   # ## ## #    #\r\n###   # #   # # # # ###   ##\r\n#  #  # #   # #   # #       #\r\n###   #  ###  #   # #### ###\r\n\r\n");
			for (int i = 0; i < BiomeGenBase.getBiomeGenArray().length; i++)
			{
				BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[i];
				if (biome != null)
				{
					String name = biome.biomeName;
					if (name == null)
					{
						name = "<null>";
					}
					filewriter.write(biome.biomeID + "\t:\t" + name + "\r\n");
				}
			}
			filewriter.write("#EOL");
			return;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				filewriter.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	@net.minecraftforge.fml.common.network.NetworkCheckHandler
	public boolean netCheckHandler(Map < String, String > mods, Side side)
	{
		return true;
	}
}