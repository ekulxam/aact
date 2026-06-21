package survivalblock.atmosphere.aact;

import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.fabricmc.loader.impl.game.minecraft.MinecraftGameProvider;

import java.lang.reflect.Field;
import java.util.Set;

public class AACTMinecraftGameProvider extends MinecraftGameProvider {
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
}
