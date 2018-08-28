package com.ldtteam.smithscore.parser;

import com.ldtteam.smithscore.parser.token.Token;
import org.apache.commons.lang3.mutable.Mutable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Generic parser that can disect a given string .
 */
public abstract class Parser<T>
{
    protected String text;
    protected Token<?> token   = Token.None;
    protected boolean  cached  = false;
    protected int      index   = 0;
    protected String   matched = "";

    protected Set<Token<?>> listTokens   = new LinkedHashSet<>();
    protected Set<Token<?>> ignoreTokens = new HashSet<>();

    //	protected Token currentToken;
    //	protected int currentSize;
    //	protected Token lastToken;
    //	protected int lastSize;

    public Parser(String s)
    {
        text = s;
    }

    protected void ignoreTokens(Token<?>... tokens)
    {
        withTokens(tokens);
        ignoreTokens.addAll(Arrays.asList(tokens));
    }

    protected void withTokens(Token<?>... tokens)
    {
        listTokens.addAll(Arrays.asList(tokens));
    }

    private Token<?> getToken()
    {
        cached = true;
        if (isEnd())
        {
            return token = Token.EndOfInput;
        }

        for (Token<?> t : listTokens)
        {
            if (t.matches(text, index))
            {
                if (ignoreTokens.contains(t))
                {
                    index += t.size();
                    return token = getToken();
                }
                else
                {
                    return token = t;
                }
            }
        }

        return token = Token.None;
    }

    public Token<?> peekToken()
    {
        if (!cached)
        {
            getToken();
        }
        return token;
    }

    public Token<?> readToken()
    {
        forward();
        peekToken();
        return token;
    }

    private void forward()
    {
        if (!cached)
        {
            peekToken();
        }
        cached = false;
        index += token.size();
    }

    public boolean isEnd()
    {
        return index >= text.length();
    }

    public char read()
    {
        return text.charAt(index++);
    }

    public char peek()
    {
        return text.charAt(index);
    }

    public <S> boolean match(Token<S> token)
    {
        return match(token, null);
    }

    public <S> boolean match(Token<S> t, Mutable<S> obj)
    {
        if (!listTokens.contains(t))
        {
            throw new IllegalArgumentException("Tried to match Token " + t + " not present is parser list tokens");
        }

        if (obj != null)
        {
            obj.setValue(null);
        }

        peekToken();
        if (token != t)
        {
            return false;
        }

        if (obj != null)
        {
            obj.setValue(t.getValue());
        }

        matched += token.getValue();
        forward();
        return true;
    }

    public String readUntil(Token<?>... tokens)
    {
        int s = index;
        int e = index;
        while (!isEnd() && !peekToken().isOneOf(tokens))
        {
            forward();
            e = index;
        }

        String txt = text.substring(s, e);
        return txt;
    }

    public abstract T parse();

    public void error(Token<?> expected)
    {
        throw new ParserException("Expecting '" + expected + "' at " + index + " but found " + token);
    }

    public static class ParserException extends RuntimeException
    {
        private static final long serialVersionUID = -3913680544137921678L;//generated

        public ParserException(String message)
        {
            super(message);
        }
    }
}
