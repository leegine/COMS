// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SqlStatistics.java

package com.fitechlabs.dbind.impl;

import java.util.*;

public class SqlStatistics
{
    public static class Summary
    {

        private synchronized void add(long value)
        {
            count++;
            total += value;
            sumsquares += value * value;
            max = value <= max ? max : value;
        }

        public synchronized long getCount()
        {
            return count;
        }

        public synchronized double getCumulativeTotal()
        {
            return (double)total;
        }

        public synchronized double getMean()
        {
            return (double)total / (double)count;
        }

        public synchronized long getMaximum()
        {
            return max;
        }

        public synchronized double getStandardDeviation()
        {
            double nsq_variance = sumsquares * count - total * total;
            double sdev = Math.sqrt(nsq_variance) / (double)count;
            return sdev;
        }

        private long count;
        private long total;
        private long sumsquares;
        private long max;


        public Summary()
        {
        }
    }


    private SqlStatistics()
    {
    }

    public static void setCollecting(boolean newValue)
    {
        collecting = newValue;
    }

    public static boolean isCollecting()
    {
        return collecting;
    }

    public static void clear()
    {
        synchronized(timingMapMapMapMap)
        {
            timingMapMapMapMap.clear();
        }
    }

    private static Map getOrCreateMap(Map map, String key)
    {
        Map map1 = map;
        JVM INSTR monitorenter ;
        Map obj;
        obj = (Map)map.get(key);
        if(obj == null)
            map.put(key, obj = new TreeMap());
        return obj;
        Exception exception;
        exception;
        throw exception;
    }

    private static Summary getOrCreateSummary(Map map, String key)
    {
        Map map1 = map;
        JVM INSTR monitorenter ;
        Summary obj;
        obj = (Summary)map.get(key);
        if(obj == null)
            map.put(key, obj = new Summary());
        return obj;
        Exception exception;
        exception;
        throw exception;
    }

    public static Map getTimingMapMapMapMap()
    {
        return timingMapMapMapMap;
    }

    public static void addTiming(String table, String type, String sql, String detail, long timingMillis)
    {
        if(detail == null)
            detail = "-";
        Map map = getOrCreateMap(timingMapMapMapMap, table);
        map = getOrCreateMap(map, type);
        map = getOrCreateMap(map, sql);
        Summary summary = getOrCreateSummary(map, detail);
        summary.add(timingMillis);
    }

    private static boolean collecting = false;
    private static Map categories = new HashMap();
    private static final String TOTALS_LABEL = " Totals";
    private static Map timingMapMapMapMap = new TreeMap();

}
