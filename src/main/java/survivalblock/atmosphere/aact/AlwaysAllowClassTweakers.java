package survivalblock.atmosphere.aact;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.fabricmc.loader.impl.FabricLoaderImpl;

public class AlwaysAllowClassTweakers implements PreLaunchEntrypoint {
	public static final String MOD_ID = "aact";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	//public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onPreLaunch() {
        FabricLoaderImpl.INSTANCE.setGameProvider(new AACTMinecraftGameProvider());
    }
}
