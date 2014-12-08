package mvb;
 
import java.io.File;
import java.util.Arrays;
import java.util.Map;

import com.google.common.eventbus.EventBus;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.*;

@MCVersion("1.8")
@TransformerExclusions({"mvb"})
public class MVBCore extends DummyModContainer 
implements IFMLLoadingPlugin
{
    static File location;
    
    public MVBCore()
    {
       super(new ModMetadata());
       ModMetadata meta = getMetadata();
       {
          meta.modId       = "MVB-COREMOD";
          meta.name        = "More Village Biomes+";
          meta.version     = "2.0.10.1";
          meta.authorList  = Arrays.asList("MG001Maya");
          meta.description = "More types of villages for many biomes MOD for Forge.";
          meta.url         = "http://forum.minecraftuser.jp/viewtopic.php?f=13&t=17617";
          meta.credits     = "More Village Biome(original) by shortwind";
       }
       this.setEnabledState(true);
    }

    public String[] getLibraryRequestClass()
    {
        return null;
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[]{MVBAsmTransformer.class.getName()};
    }
 
    @Override
    public String getModContainerClass()
    {
        return MVBCore.class.getName();
    }
 
    @Override
    public String getSetupClass()
    {
        return null;
    }
 
    @Override
    public void injectData(Map<String, Object> data)
    {
        if (data.containsKey("coremodLocation"))
        {
            location = (File) data.get("coremodLocation");
        }
    }

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
	
    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
       bus.register(this);
       return true;
    }
 }