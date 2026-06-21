package survivalblock.atmosphere.aact;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.fabricmc.loader.impl.FormattedException;
import net.fabricmc.loader.impl.game.GameProvider;
import net.fabricmc.loader.impl.game.minecraft.MinecraftGameProvider;
import net.fabricmc.loader.impl.game.patch.GameTransformer;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import net.fabricmc.loader.impl.util.Arguments;
import net.fabricmc.loader.impl.util.LoaderUtil;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.*;

public class AACTMinecraftGameProvider extends MinecraftGameProvider {

    public final GameProvider delegate;

    public AACTMinecraftGameProvider(GameProvider delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getGameId() {
        return this.delegate.getGameId();
    }

    @Override
    public String getGameName() {
        return this.delegate.getGameName();
    }

    @Override
    public String getRawGameVersion() {
        return this.delegate.getRawGameVersion();
    }

    @Override
    public String getNormalizedGameVersion() {
        return this.delegate.getNormalizedGameVersion();
    }

    @Override
    public Collection<BuiltinMod> getBuiltinMods() {
        return this.delegate.getBuiltinMods();
    }

    @Override
    public Path getGameJar() {
        if (this.delegate instanceof MinecraftGameProvider minecraftGameProvider) {
            return minecraftGameProvider.getGameJar();
        }
        throw new UnsupportedOperationException("Delegate " + this.delegate.getClass() + " is not of type MinecraftGameProvider!");
    }

    @Override
    public String getEntrypoint() {
        return this.delegate.getEntrypoint();
    }

    @Override
    public Path getLaunchDirectory() {
        return this.delegate.getLaunchDirectory();
    }

    @Override
    public boolean requiresUrlClassLoader() {
        return this.delegate.requiresUrlClassLoader();
    }

    @Override
    public Set<BuiltinTransform> getBuiltinTransforms(String className) {
        try {
            String fieldName = FabricLoaderImpl.INSTANCE.isDevelopmentEnvironment() ? "TRANSFORM_WIDENALL_STRIPENV_CLASSTWEAKS" : "TRANSFORM_WIDENALL_CLASSTWEAKS";
            Field field = MinecraftGameProvider.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            //noinspection unchecked
            return (Set<BuiltinTransform>) field.get(null);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEnabled() {
        return this.delegate.isEnabled();
    }

    @Override
    public boolean locateGame(FabricLauncher launcher, String[] args) {
        return this.delegate.locateGame(launcher, args);
    }

    @Override
    public void initialize(FabricLauncher launcher) {
        this.delegate.initialize(launcher);
    }

    @Override
    public Arguments getArguments() {
        return this.delegate.getArguments();
    }

    @Override
    public String[] getLaunchArguments(boolean sanitize) {
        return this.delegate.getLaunchArguments(sanitize);
    }

    @Override
    public GameTransformer getEntrypointTransformer() {
        return this.delegate.getEntrypointTransformer();
    }

    @Override
    public boolean canOpenErrorGui() {
        return this.delegate.canOpenErrorGui();
    }

    @Override
    public boolean hasAwtSupport() {
        return this.delegate.hasAwtSupport();
    }

    @Override
    public void unlockClassPath(FabricLauncher launcher) {
        this.delegate.unlockClassPath(launcher);
    }

    @Override
    public void launch(ClassLoader loader) {
        this.delegate.launch(loader);
    }
}
