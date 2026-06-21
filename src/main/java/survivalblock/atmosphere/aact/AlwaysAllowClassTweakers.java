package survivalblock.atmosphere.aact;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.LanguageAdapter;
import net.fabricmc.loader.api.LanguageAdapterException;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.impl.FabricLoaderImpl;

@SuppressWarnings("unused")
public class AlwaysAllowClassTweakers implements LanguageAdapter {
	public static final String MOD_ID = "aact";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	//public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public <T> T create(ModContainer mod, String value, Class<T> type) throws LanguageAdapterException {
        return LanguageAdapter.getDefault().create(mod, value, type);
    }

    static {
        FabricLoaderImpl.INSTANCE.setGameProvider(new AACTMinecraftGameProvider(FabricLoaderImpl.INSTANCE.getGameProvider()));
    }
}
