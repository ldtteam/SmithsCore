package com.smithsmodding.smithscore.core.mappings;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodInsnNode;

/**
 * Class describing a mapping for a Method.
 */
public class McpMethodMapping extends McpMapping<MethodInsnNode>
{
    public McpMethodMapping(String mcp, String srg, String owner, String descriptor)
    {
        super(mcp, srg, owner, descriptor);
    }

    @Override
    public MethodInsnNode getInsnNode(int opcode)
    {
        return new MethodInsnNode(opcode, getOwner(), getName(), getDescriptor(), opcode == Opcodes.INVOKEINTERFACE);
    }
}