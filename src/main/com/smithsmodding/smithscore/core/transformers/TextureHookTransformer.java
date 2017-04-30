package com.smithsmodding.smithscore.core.transformers;

import com.smithsmodding.smithscore.core.asm.AsmHook;
import com.smithsmodding.smithscore.core.asm.SmithsCoreClassTransformer;
import com.smithsmodding.smithscore.core.mappings.McpMethodMapping;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import javax.annotation.Nonnull;

import static org.objectweb.asm.Opcodes.*;

/**
 * The transformer for the TextureHook.
 */
public class TextureHookTransformer extends SmithsCoreClassTransformer
{

    private static final String CST_LOAD_SPRITES_MCPNAME    = "loadSprites";
    private static final String CST_LOAD_SPRITES_SRGNAME    = "func_174943_a";
    private static final String CST_LOAD_SPRITES_OWNER      = "net.minecraft.client.renderer.texture.TextureMap";
    private static final String CST_LOAD_SPRITES_DESCRIPTOR = "(Lnet/minecraft/client/resources/IResourceManager;Lnet/minecraft/client/renderer/texture/ITextureMapPopulator;)V";

    private static final McpMethodMapping CST_LOAD_SPRITES_MAPPING = new McpMethodMapping(
                                                                                           CST_LOAD_SPRITES_MCPNAME,
                                                                                           CST_LOAD_SPRITES_SRGNAME,
                                                                                           CST_LOAD_SPRITES_OWNER,
                                                                                           CST_LOAD_SPRITES_DESCRIPTOR
    );

    private static final String CST_INIT_MISSING_TEXTURE_MCPNAME    = "initMissingImage";
    private static final String CST_INIT_MISSING_TEXTURE_SRGNAME    = "func_110569_e";
    private static final String CST_INIT_MISSING_TEXTURE_OWNER      = "net.minecraft.client.renderer.texture.TextureMap";
    private static final String CST_INIT_MISSING_TEXTURE_DESCRIPTOR = "()V";

    private static final McpMethodMapping CST_INIT_MISSING_TEXTURE_MAPPING = new McpMethodMapping(
                                                                                                   CST_INIT_MISSING_TEXTURE_MCPNAME,
                                                                                                   CST_INIT_MISSING_TEXTURE_SRGNAME,
                                                                                                   CST_INIT_MISSING_TEXTURE_OWNER,
                                                                                                   CST_INIT_MISSING_TEXTURE_DESCRIPTOR
    );

    private static final String CST_TEXTURE_HOOK_OWNER      = "com/smithsmodding/smithscore/core/hooks/TextureMapHook";
    private static final String CST_TEXTURE_HOOK_METHOD     = "onTextureStichCollected";
    private static final String CST_TEXTURE_HOOK_DESCRIPTOR = "(Lnet/minecraft/client/renderer/texture/TextureMap;)V";

    private static final MethodInsnNode CST_TEXTURE_HOOK_INSN = new MethodInsnNode(
                                                                                    INVOKESTATIC,
                                                                                    CST_TEXTURE_HOOK_OWNER,
                                                                                    CST_TEXTURE_HOOK_METHOD,
                                                                                    CST_TEXTURE_HOOK_DESCRIPTOR,
                                                                                    false
    );

    /**
     * Method used to register the ASM Hooks that are required for this transformer.
     */
    @Override
    public void registerHooks()
    {
        register(getTextureCollectedAsmHook());
    }

    /**
     * Method used to get the ASMHook used to transform the TextureMap so it calls the Texture
     * Collected Hook.
     *
     * @return The asm hook.
     */
    private AsmHook getTextureCollectedAsmHook()
    {
        @Nonnull AsmHook hook = new AsmHook(CST_LOAD_SPRITES_MAPPING);

        InsnList insertionList = new InsnList();
        insertionList.add(new VarInsnNode(ALOAD, 2));
        insertionList.add(new VarInsnNode(ALOAD, 0));
        insertionList.add(CST_TEXTURE_HOOK_INSN);

        InsnList matchingList = new InsnList();
        matchingList.add(new VarInsnNode(ALOAD, 0));
        matchingList.add(CST_INIT_MISSING_TEXTURE_MAPPING.getInsnNode(INVOKESPECIAL));

        hook.jumpAfter(matchingList).insert(insertionList);

        return hook;
    }
}
