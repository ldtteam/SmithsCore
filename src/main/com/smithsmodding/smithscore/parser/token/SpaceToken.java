package com.smithsmodding.smithscore.parser.token;

/**
 * @author Ordinastie
 */
public class SpaceToken extends Token<Character>
{
    @Override
    public boolean matches(String s, int index)
    {
        if (Character.isWhitespace(s.charAt(index)))
        {
            value = s.charAt(index);
            return true;
        }
        else
        {
            value = null;
            return false;
        }
    }

    @Override
    public int size()
    {
        return 1;
    }
}
