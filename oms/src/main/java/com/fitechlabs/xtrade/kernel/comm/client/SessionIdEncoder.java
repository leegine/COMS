// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SessionIdEncoder.java

package com.fitechlabs.xtrade.kernel.comm.client;

import java.io.PrintStream;
import java.util.Random;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            OrderShuffler

public final class SessionIdEncoder
{

    private SessionIdEncoder()
    {
    }

    public static String encodeSessionId(int poolSize, int hashSequence, String sessionPk)
    {
        if(poolSize == 0)
        {
            return sessionPk;
        } else
        {
            int order[] = OrderShuffler.shuffle(poolSize, hashSequence);
            return encodeSessionId(order, sessionPk);
        }
    }

    public static String encodeSessionId(int serverTryOrder[], String sessionPk)
    {
        if(serverTryOrder == null)
            return sessionPk;
        int n = serverTryOrder.length;
        StringBuffer sb = new StringBuffer(n + sessionPk.length() + 1);
        for(int i = 0; i < n; i++)
            sb.append(indexToChar(serverTryOrder[i]));

        sb.append('.');
        sb.append(sessionPk);
        return sb.toString();
    }

    public static int[] decodeServerTryOrder(String sessionId)
    {
        if(sessionId == null)
            return null;
        int i = sessionId.indexOf('.');
        if(i < 0)
            return null;
        String chars = sessionId.substring(0, i);
        int order[] = new int[chars.length()];
        for(i = 0; i < chars.length(); i++)
            order[i] = charToIndex(chars.charAt(i));

        return order;
    }

    public static String decodeSessionPk(String sessionId)
    {
        if(sessionId == null)
            return null;
        else
            return sessionId.substring(sessionId.indexOf('.') + 1);
    }

    private static final char indexToChar(int index)
    {
        index = (index & 0x7fffffff) % 62;
        return index >= 10 ? index >= 36 ? (char)(65 + (index - 36)) : (char)(97 + (index - 10)) : (char)(48 + index);
    }

    private static final int charToIndex(char c)
    {
        int ic = c;
        return ((ic > 57 ? ic < 97 ? (36 + ic) - 65 : (10 + ic) - 97 : ic - 48) & 0x7fffffff) % 62;
    }

    public static void main(String a[])
        throws Exception
    {
        System.out.println("Character / index conversions:");
        for(int i = 0; i < 62; i++)
            System.out.println("index=" + i + " character=" + indexToChar(i) + " index(c)=" + charToIndex(indexToChar(i)));

        System.out.println("Test encoding on fixed pool size:");
        int hash = random.nextInt();
        for(int j = 0; j < 20;)
        {
            testEncode(8, hash);
            j++;
            hash++;
        }

    }

    private static void testEncode(int count, int hash)
    {
        String pk = String.valueOf(random.nextLong());
        int order[] = OrderShuffler.shuffle(count, hash);
        String sessId1 = encodeSessionId(order, pk);
        String sessId2 = encodeSessionId(decodeServerTryOrder(sessId1), decodeSessionPk(sessId1));
        String sessId3 = encodeSessionId(decodeServerTryOrder(null), decodeSessionPk(null));
        System.out.println("session_id 1=" + sessId1 + " 2=" + sessId2 + " (" + sessId1.equals(sessId2) + ") 3=" + sessId3);
    }

    public static final int MAX_INDEX = 62;
    private static final char FIELD_SEP = 46;
    private static final Random random = new Random(System.currentTimeMillis());

}
