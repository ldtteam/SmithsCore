package com.ldtteam.smithscore.parser.token;

/**
 * @author Ordinastie
 */
public class KeywordToken extends Token<String>
{
    public KeywordToken(String keyword)
    {
        value = keyword;
    }

    @Override
    public boolean matches(String s, int index)
    {
        if (value == null)
        {
            return false;
        }
        int e = index + value.length();
        if (e > s.length())
        {
            return false;
        }
        if (s.substring(index, index + value.length()).toLowerCase().equals(value.toLowerCase()))
        {
            return true;
        }
        return false;
    }

    @Override
    public int size()
    {
        return value.length();
    }
}
