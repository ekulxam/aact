package survivalblock.atmosphere.aact;

import de.zonlykroks.massasmer.MassASMTransformer;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;

public class AlwaysAllowClassTweakers implements Runnable {
    public static final String MOD_ID = "aact";

    @Override
    public void run() {
        MassASMTransformer.registerNodeTransformer("aact", _ -> true, (name, node) -> {
            ClassNode node1 = new ClassNode();
            node.accept(FabricLoaderImpl.INSTANCE.getClassTweaker().createClassVisitor(FabricLoaderImpl.ASM_VERSION, node1, null));
            resetNode(node);
            node1.accept(node);
            return true;
        });
    }

    private void resetNode(ClassNode node) {
        node.interfaces = null;
        node.module = null;
        node.outerClass = null;
        node.outerMethod = null;
        node.outerMethodDesc = null;
        node.visibleAnnotations = new ArrayList<>();
        node.invisibleAnnotations = new ArrayList<>();
        node.visibleTypeAnnotations = new ArrayList<>();
        node.invisibleTypeAnnotations = new ArrayList<>();
        node.attrs = new ArrayList<>();
        node.innerClasses = new ArrayList<>();
        node.nestHostClass = null;
        node.nestMembers = new ArrayList<>();
        node.permittedSubclasses = new ArrayList<>();
        node.recordComponents = new ArrayList<>();
        node.fields = new ArrayList<>();
        node.methods = new ArrayList<>();
    }
}
