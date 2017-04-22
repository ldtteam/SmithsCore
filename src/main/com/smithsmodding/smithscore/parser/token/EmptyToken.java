package com.smithsmodding.smithscore.parser.token;

/**
 * @author Ordinastie
 */
public class EmptyToken extends Token<Void>
{
    @Override
    public boolean matches(String s, int index)
    {
        return false;
    }

    @Override
    public int size()
    {
        return 1;
    }
}