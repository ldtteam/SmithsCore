package com.smithsmodding.smithscore.parser.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ordinastie
 */
public class ExpressionToken extends Token<String>
{
    private Pattern pattern;

    public ExpressionToken(String regex)
    {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean matches(String s, int index)
    {
        value = null;
        Matcher m = pattern.matcher(s.substring(index));
        if (!m.find())
        {
            return false;
        }

        value = m.group(0);
        return true;
    }

    @Override
    public int size()
    {
        return value != null ? value.length() : 0;
    }
}
