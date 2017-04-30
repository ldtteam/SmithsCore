package com.smithsmodding.smithscore.core.mappings;

import net.minecraft.launchwrapper.Launch;
import org.objectweb.asm.tree.AbstractInsnNode;

/**
 * Class used to represent a McpMapping.
 */
public abstract class McpMapping<T extends AbstractInsnNode>
{
    protected String srgName;
    protected String mcpName;
    protected String owner;
    protected String descriptor;
    private boolean isObfEnv = !(boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

    /**
     * Constructor for a Mapping. Might be a field or a Method.
     *
     * @param mcp        The MCP name of the mapping.
     * @param srg        The Searge name of the mapping.
     * @param owner      The class that holds the targeted object.
     * @param descriptor The descriptor of the object.
     */
    public McpMapping(String mcp, String srg, String owner, String descriptor)
    {
        this.mcpName = mcp;
        this.srgName = srg;
        this.owner = owner;
        this.descriptor = descriptor;
    }

    /**
     * Method used to get the owner of the obfuscated object.
     *
     * @return The name of the owner.
     */
    public String getOwner()
    {
        return owner.replace('.', '/');
    }

    /**
     * Method used to get the class that this mapping belongs to.
     *
     * @return The class that this mapping belongs to.
     */
    public String getTargetClass()
    {
        return owner.replace('/', '.');
    }

    /**
     * Method to get the name of the mapping.
     *
     * @return Returns the srgName when we are in a obfuscated environment, else the readable mcp name.
     */
    public String getName()
    {
        return isObfEnv ? srgName : mcpName;
    }

    /**
     * Method to get the descriptor of this mapping.
     *
     * @return The java descriptor of this mapping.
     */
    public String getDescriptor()
    {
        return descriptor;
    }

    /**
     * Method used to get the {@link org.objectweb.asm.tree.InsnNode} that represents this mapping in
     * ASM ByteCode.
     *
     * @param opcode The operations instruction code for which the {@link org.objectweb.asm.tree.InsnNode} is retrieved
     * @return The {@link org.objectweb.asm.tree.InsnNode} for the given OpCode.
     */
    public abstract T getInsnNode(int opcode);
}