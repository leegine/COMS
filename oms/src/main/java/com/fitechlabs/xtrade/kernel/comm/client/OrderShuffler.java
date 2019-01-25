// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderShuffler.java

package com.fitechlabs.xtrade.kernel.comm.client;

import java.util.*;

public final class OrderShuffler
{

    public OrderShuffler()
    {
    }

    public static int[] shuffle(int count, int hash)
    {
        if(count <= 0)
            return new int[0];
        int order[] = new int[count];
        hash &= 0x7fffffff;
        List all = new ArrayList(count * 2);
        for(int i = 0; i < count; i++)
            all.add(new Integer(i));

        for(int i = 0; i < count; i++)
        {
            int j = hash % (count - i);
            Integer removed = (Integer)all.remove(j);
            order[i] = removed.intValue();
        }

        return order;
    }

    public static int[] shuffle(float weight[], long hash)
    {
        int count = weight.length;
        if(count <= 0)
            return new int[0];
        int order[] = new int[count];
        float sums[] = new float[count];
        Random rand = new Random(hash);
        rand.nextLong();
        rand.nextLong();
        List all = new ArrayList(count);
        for(int i = 0; i < count; i++)
            all.add(new Integer(i));

        for(int i = 0; i < count; i++)
        {
            int c = count - i;
            int j;
            for(j = 0; j < c; j++)
            {
                int k = ((Integer)all.get(j)).intValue();
                sums[j] = (j <= 0 ? 0.0F : sums[j - 1]) + weight[k];
            }

            float range = sums[c - 1];
            float likelihood = rand.nextFloat() * range;
            for(j = 0; j < c - 1 && likelihood > sums[j]; j++);
            Integer removed = (Integer)all.remove(j);
            order[i] = removed.intValue();
        }

        return order;
    }
}
