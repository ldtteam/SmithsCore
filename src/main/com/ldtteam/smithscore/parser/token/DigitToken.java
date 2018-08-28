package com.ldtteam.smithscore.parser.token;

/**
 * @author Ordinastie
 */
public class DigitToken extends Token<Character>
{

    @Override
    public boolean matches(String s, int index)
    {
        value = null;
        if (index >= s.length() || Character.isDigit(s.charAt(index)))
        {
            return false;
        }

        value = s.charAt(index);
        return true;
    }

    @Override
    public int size()
    {
        return 1;
    }
}
