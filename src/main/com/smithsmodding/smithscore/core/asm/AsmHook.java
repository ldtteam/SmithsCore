package com.smithsmodding.smithscore.core.asm;

import com.smithsmodding.smithscore.core.mappings.McpMethodMapping;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class representing a Hook into the Minecraft code to modify it when it gets loaded.
 */
public class AsmHook
{
    private static int END = Short.MIN_VALUE;
    private String           transformer;
    private McpMethodMapping mapping;
    private boolean             debug        = false;
    private ArrayList<InsnList> inserts      = new ArrayList<>();
    private ArrayList<InsnList> matches      = new ArrayList<>();
    private ArrayList<Integer>  jumps        = new ArrayList<>();
    private ArrayList<HookStep> steps        = new ArrayList<>();
    private HookState           currentState = HookState.UNPROCESSED;

    public AsmHook(McpMethodMapping mapping)
    {
        this.mapping = mapping;
    }

    public String getTransformer()
    {
        return transformer;
    }

    public void setTransformer(String transformerName)
    {
        this.transformer = transformerName;
    }

    public AsmHook jumpToEnd()
    {
        return this.jump(END);
    }

    public AsmHook jump(int jump)
    {
        this.steps.add(HookStep.JUMP);
        this.jumps.add(jump);
        return this;
    }

    public AsmHook jumpAfter(InsnList match)
    {
        this.jumpTo(match);
        this.jump(match.size());
        return this;
    }

    public AsmHook jumpTo(InsnList match)
    {
        this.steps.add(HookStep.FIND);
        this.matches.add(match);
        this.jump(-1);
        return this;
    }

    public AsmHook insert(AbstractInsnNode insert)
    {
        InsnList list = new InsnList();
        list.add(insert);
        return insert(list);
    }

    public AsmHook insert(InsnList insert)
    {
        this.steps.add(HookStep.INSERT);
        this.inserts.add(insert);
        return this;
    }

    public AsmHook previous()
    {
        return jump(-1);
    }

    public AsmHook next()
    {
        return jump(1);
    }

    public AsmHook debug()
    {
        this.debug = true;
        return this;
    }

    public boolean walkSteps(MethodNode methodNode)
    {
        int index = 0;
        int matchesIndex = 0;
        int insertsIndex = 0;
        int jumpsIndex = 0;
        for (HookStep step : steps)
        {
            switch (step)
            {
                case FIND:
                    InsnList match = matches.get(matchesIndex++);
                    AbstractInsnNode node = AsmUtils.findInstruction(methodNode, match, index);
                    if (node == null)
                    {
                        currentState = HookState.FAILED;
                        return false;
                    }
                    else
                    {
                        index = methodNode.instructions.indexOf(node);
                    }
                    break;
                case INSERT:
                    InsnList insert = inserts.get(insertsIndex++);
                    index += insert.size();
                    methodNode.instructions.insert(methodNode.instructions.get(index - insert.size()), insert);
                    break;
                case JUMP:
                    int jump = jumps.get(jumpsIndex++);
                    if (jump == END)
                    {
                        index = methodNode.instructions.size() - 1;
                    }
                    else
                    {
                        index += jump;
                    }
                default:
                    break;
            }
        }

        currentState = HookState.SUCCESS;
        return true;
    }

    public void register(HashMap<String, ArrayList<AsmHook>> listHooks)
    {
        ArrayList<AsmHook> hooks = listHooks.get(mapping.getTargetClass());
        if (hooks == null)
        {
            hooks = new ArrayList<>();
        }
        hooks.add(this);
        listHooks.put(mapping.getTargetClass(), hooks);
    }

    public String getTargetClass()
    {
        return mapping.getTargetClass();
    }

    public String getMethodName()
    {
        return mapping.getName();
    }

    public String getMethodDescriptor()
    {
        return mapping.getDescriptor();
    }

    public boolean isDebug()
    {
        return debug;
    }

    public HookState getState()
    {
        return currentState;
    }

    private enum HookStep
    {
        FIND,
        INSERT,
        JUMP,
    }

    public enum HookState
    {
        UNPROCESSED,
        SUCCESS,
        FAILED
    }
}