package com.smithsmodding.smithscore.core.asm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.smithsmodding.smithscore.SmithsCore;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Collection;

/**
 * Class that handles the transforming using the SmithsCore ASM system.
 */
public abstract class SmithsCoreClassTransformer implements IClassTransformer
{
    public Multimap<String, AsmHook> listHooks = HashMultimap.create();

    public SmithsCoreClassTransformer()
    {
        registerHooks();
    }

    public abstract void registerHooks();

    public void register(AsmHook ah)
    {
        ah.setTransformer(this.getClass().getSimpleName());
        listHooks.put(ah.getTargetClass(), ah);
        SmithsCore.getLogger().info("[{}] Hook registered for {}", ah.getTransformer(), ah.getTargetClass());
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        Collection<AsmHook> hooks = listHooks.get(transformedName);
        if (hooks == null || hooks.size() == 0)
        {
            return bytes;
        }

        SmithsCore.getLogger().info("Found hooks for {} ({})", transformedName, name);

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);

        for (AsmHook hook : hooks)
        {
            MethodNode methodNode = AsmUtils.findMethod(classNode, hook.getMethodName(), hook.getMethodDescriptor());
            if (methodNode != null)
            {
                if (!hook.walkSteps(methodNode))
                {
                    SmithsCore.getLogger().error("[{}] The instruction list was not found in {}:{}{}", hook.getTransformer(),
                      hook.getTargetClass(), hook.getMethodName(), hook.getMethodDescriptor());
                }

                if (hook.isDebug() == true && (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment"))
                {
                    System.err.println(AsmUtils.getMethodNodeAsString(methodNode));
                }
            }
            else
            {
                SmithsCore.getLogger().error("[{}] Method not found : {}:{}{}", hook.getTransformer(), hook.getTargetClass(),
                  hook.getMethodName(), hook.getMethodDescriptor());
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS /* | ClassWriter.COMPUTE_FRAMES */);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}