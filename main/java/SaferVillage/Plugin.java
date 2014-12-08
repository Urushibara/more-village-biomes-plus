package SaferVillage;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import com.google.common.eventbus.EventBus;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@MCVersion("1.8")
@TransformerExclusions({"SaferVillage"})
public class Plugin extends DummyModContainer 
implements IFMLLoadingPlugin, IClassTransformer
{
    static File location;
    public static boolean isHARD = false; 

    public Plugin()
    {
       super(new ModMetadata());
       ModMetadata meta = getMetadata();
       {
          meta.modId       = "SAFER_VILLAGE";
          meta.name        = "Safer Village";
          meta.version     = "1.0.1";
          meta.authorList  = Arrays.asList("MG001Maya");
          meta.description = "";
          meta.url         = "http://forum.minecraftuser.jp/viewtopic.php?f=13&t=17617";
          meta.credits     = "";
       }
       this.setEnabledState(true);
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
       bus.register(this);
       return true;
    }

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{this.getClass().getName()};
	}

	@Override
	public String getModContainerClass() {
		return this.getClass().getName();
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
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
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		byte[] templateClass = AsmHelper.loadclass(location.getPath(), name + ".class");
		if(templateClass != null) return templateClass;
		return basicClass;
	}
}
