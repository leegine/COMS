// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CacheStatistics.java

package com.fitechlabs.dbind.impl;

import java.util.*;

public class CacheStatistics
{
    public static class InvInfo extends Summary
    {

        private void add(long timeMillis, int rowDropCount, int pkDropCount, int enumDropCount)
        {
            add(timeMillis);
            rowDrops += rowDropCount;
            pkDrops += pkDropCount;
            enumDrops += enumDropCount;
        }

        public long getRowDrops()
        {
            return rowDrops;
        }

        public long getPkDrops()
        {
            return pkDrops;
        }

        public long getEnumDrops()
        {
            return enumDrops;
        }

        private long rowDrops;
        private long pkDrops;
        private long enumDrops;


        public InvInfo()
        {
        }
    }

    public static class SizeInfo extends Summary
    {

        private void addSize(long size)
        {
            add(size);
        }

        private void setCapacity(long capacity)
        {
            this.capacity = capacity;
        }

        public long getCapacity()
        {
            return capacity;
        }

        private long capacity;



        public SizeInfo()
        {
        }
    }

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

    public static class Tally
    {

        private synchronized void addHit()
        {
            hits++;
            total++;
        }

        private synchronized void addMiss()
        {
            misses++;
            total++;
        }

        private synchronized void addSpecialCase(String caseName)
        {
            if(specialCases == null)
                specialCases = new HashMap();
            long count[] = (long[])specialCases.get(caseName);
            if(count == null)
                specialCases.put(caseName, count = new long[1]);
            count[0]++;
        }

        public synchronized long getHitCount()
        {
            return hits;
        }

        public synchronized long getMissCount()
        {
            return misses;
        }

        public synchronized long getAccessCount()
        {
            return total;
        }

        public synchronized double getHitRate()
        {
            return (double)hits / (double)total;
        }

        public synchronized double getMissRate()
        {
            return (double)misses / (double)total;
        }

        public synchronized Map getSpecialCases()
        {
            Map m = null;
            if(specialCases != null)
            {
                m = new HashMap();
                java.util.Map.Entry entry;
                long value[];
                for(Iterator it = specialCases.entrySet().iterator(); it.hasNext(); m.put(entry.getKey(), new Long(value[0])))
                {
                    entry = (java.util.Map.Entry)it.next();
                    value = (long[])entry.getValue();
                }

            }
            return m;
        }

        private long hits;
        private long misses;
        private long total;
        private Map specialCases;




        public Tally()
        {
        }
    }


    private CacheStatistics()
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
        clear(sizeInfoMapMap);
        clear(invalidationMapMap);
        clear(timingMapMapMapMap);
        clear(hitRatioMapMap);
    }

    private static void clear(Map map)
    {
        synchronized(map)
        {
            map.clear();
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

    private static SizeInfo getOrCreateSizeInfo(Map map, String key)
    {
        Map map1 = map;
        JVM INSTR monitorenter ;
        SizeInfo obj;
        obj = (SizeInfo)map.get(key);
        if(obj == null)
            map.put(key, obj = new SizeInfo());
        return obj;
        Exception exception;
        exception;
        throw exception;
    }

    private static Tally getOrCreateTally(Map map, String key)
    {
        Map map1 = map;
        JVM INSTR monitorenter ;
        Tally obj;
        obj = (Tally)map.get(key);
        if(obj == null)
            map.put(key, obj = new Tally());
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

    private static InvInfo getOrCreateInvInfo(Map map, String key)
    {
        Map map1 = map;
        JVM INSTR monitorenter ;
        InvInfo obj;
        obj = (InvInfo)map.get(key);
        if(obj == null)
            map.put(key, obj = new InvInfo());
        return obj;
        Exception exception;
        exception;
        throw exception;
    }

    public static Map getSizeInfoMapMap()
    {
        return sizeInfoMapMap;
    }

    public static void addSizeChange(String table, String type, int newSize, int capacity)
    {
        if(!collecting)
        {
            return;
        } else
        {
            Map map = getOrCreateMap(sizeInfoMapMap, table);
            SizeInfo sizeInfo = getOrCreateSizeInfo(map, type);
            sizeInfo.addSize(newSize);
            sizeInfo.setCapacity(capacity);
            return;
        }
    }

    public static Map getHitRatioMapMap()
    {
        return hitRatioMapMap;
    }

    public static void addHit(String table, String type, String where, long timeMillis, String specialCase)
    {
        if(!collecting)
            return;
        Map map = getOrCreateMap(hitRatioMapMap, table);
        Tally tally = getOrCreateTally(map, type);
        tally.addHit();
        if(specialCase != null)
            tally.addSpecialCase(specialCase);
        addTiming(table, type, where, specialCase != null ? "hit - " + specialCase : "hit", timeMillis);
        map = getOrCreateMap(hitRatioMapMap, " Totals");
        tally = getOrCreateTally(map, "");
        tally.addHit();
    }

    public static void addMiss(String table, String type, String where, long timeMillis, String specialCase)
    {
        if(!collecting)
            return;
        Map map = getOrCreateMap(hitRatioMapMap, table);
        Tally tally = getOrCreateTally(map, type);
        tally.addMiss();
        if(specialCase != null)
            tally.addSpecialCase(specialCase);
        addTiming(table, type, where, specialCase != null ? "miss - " + specialCase : "miss", timeMillis);
        map = getOrCreateMap(hitRatioMapMap, " Totals");
        tally = getOrCreateTally(map, "");
        tally.addMiss();
    }

    public static Map getInvalidationMapMap()
    {
        return invalidationMapMap;
    }

    static void addInv(String table, String invType, long timeMillis, int rowDropCount, int pkDropCount, int enumDropCount)
    {
        if(!collecting)
        {
            return;
        } else
        {
            Map map = getOrCreateMap(invalidationMapMap, table);
            InvInfo invInfo = getOrCreateInvInfo(map, invType);
            invInfo.add(timeMillis, rowDropCount, pkDropCount, enumDropCount);
            map = getOrCreateMap(invalidationMapMap, " Totals");
            invInfo = getOrCreateInvInfo(map, "");
            invInfo.add(timeMillis, rowDropCount, pkDropCount, enumDropCount);
            return;
        }
    }

    public static Map getTimingMapMapMapMap()
    {
        return timingMapMapMapMap;
    }

    private static void addTiming(String table, String type, String where, String detail, long timingMillis)
    {
        if(where == null)
            where = "(all)";
        Map map = getOrCreateMap(timingMapMapMapMap, table);
        map = getOrCreateMap(map, type);
        map = getOrCreateMap(map, where);
        Summary summary = getOrCreateSummary(map, detail);
        summary.add(timingMillis);
    }

    private static boolean collecting = false;
    private static Map categories = new HashMap();
    private static final String TOTALS_LABEL = " Totals";
    private static Map sizeInfoMapMap = new TreeMap();
    private static Map hitRatioMapMap = new TreeMap();
    private static Map invalidationMapMap = new TreeMap();
    private static Map timingMapMapMapMap = new TreeMap();

}
