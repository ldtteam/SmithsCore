package com.smithsmodding.smithscore.parser.token;

/**
 * @author Ordinastie
 */
public class CharToken extends Token<Character>
{
    public CharToken(char c)
    {
        this.value = c;
    }

    @Override
    public boolean matches(String s, int index)
    {
        return index < s.length() && s.charAt(index) == value;
    }

    @Override
    public int size()
    {
        return 1;
    }
}
