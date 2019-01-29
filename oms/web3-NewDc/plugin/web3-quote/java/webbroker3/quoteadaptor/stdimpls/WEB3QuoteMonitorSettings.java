head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteMonitorSettings.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteMonitorSettingsクラス(WEB3QuoteMonitorSettings.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/05/23 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * 時価監視サービスの設定を保持するクラス
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3QuoteMonitorSettings
{
    
    /** ログ出力ユーティリティ */
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3QuoteMonitorSettings.class);
    
    /** デバック出力フラグ */
    private static final boolean DBG = LOG.ison();
    
    /** シングルトン */
    private static final WEB3QuoteMonitorSettings INSTANCE = 
        new WEB3QuoteMonitorSettings();
    
    /** 文字列「0」の定数定義 */
    private static final String ZERO = "0";
    
    /** 先物OPに関するキーワード */
    public final String IFO = "IFO";
    
    /**
     * 監視する間隔
     */
    private long interval;
    
    /**
     * 警告を出力する閾値
     */
    private int warningThreshold;
    
    /**
     * 実行する時間（FROM-TO）の配列
     */
    private Timezone[] timezoneList;
    
    /**
     * コンストラクタ
     */
    protected WEB3QuoteMonitorSettings()
    {
        init();
        if (DBG)
        {
            LOG.debug("QuoteMonitorSettings initialized. " + toString());
        }
    }
    
    /**
     * このクラスのシングルトンを取得する。
     * 
     * @@return シングルトン
     */
    static WEB3QuoteMonitorSettings getInstance()
    {
        return INSTANCE;
    }

    /**
     * @@return interval を取得する。
     */
    long getInterval()
    {
        return interval;
    }
    
    /**
     * @@return warningThreshold を取得する。
     */
    int getWarningThreshold()
    {
        return warningThreshold;
    }
    
    /**
     * カウント閾値を取得する。
     */
    int getCountThreshold(String l_marketCode)
    {
        int countThreshold;
        
        if(WEB3MarketCodeDef.TOKYO.equals(l_marketCode))
        {
            countThreshold = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.MONITOR_COUNT_THRESHOLD_TSE_PREF_NAME, 
                    WEB3QuoteConstants.MONITOR_COUNT_THRESHOLD);
        }
        else if(WEB3MarketCodeDef.OSAKA.equals(l_marketCode))
        {
            countThreshold = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.MONITOR_COUNT_THRESHOLD_OSE_PREF_NAME, 
                    WEB3QuoteConstants.MONITOR_COUNT_THRESHOLD);
        }
        else if(WEB3MarketCodeDef.NAGOYA.equals(l_marketCode))
        {
            countThreshold = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.MONITOR_COUNT_THRESHOLD_MSE_PREF_NAME, 
                    WEB3QuoteConstants.MONITOR_COUNT_THRESHOLD);
        }
        else if(WEB3MarketCodeDef.JASDAQ.equals(l_marketCode))
        {
            countThreshold = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.MONITOR_COUNT_THRESHOLD_JASDAQ_PREF_NAME, 
                    WEB3QuoteConstants.MONITOR_COUNT_THRESHOLD);
        }
        else if(WEB3MarketCodeDef.NNM.equals(l_marketCode))
        {
            countThreshold = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.MONITOR_COUNT_THRESHOLD_HC_PREF_NAME, 
                    WEB3QuoteConstants.MONITOR_COUNT_THRESHOLD);
        }
        else if(WEB3MarketCodeDef.FUKUOKA.equals(l_marketCode))
        {
            countThreshold = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.MONITOR_COUNT_THRESHOLD_FSE_PREF_NAME, 
                    WEB3QuoteConstants.MONITOR_COUNT_THRESHOLD);
        }
        else if(WEB3MarketCodeDef.SAPPORO.equals(l_marketCode))
        {
            countThreshold = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.MONITOR_COUNT_THRESHOLD_SSE_PREF_NAME, 
                    WEB3QuoteConstants.MONITOR_COUNT_THRESHOLD);
        }
        else if(IFO.equals(l_marketCode))
        {
            countThreshold = WEB3QuotePropertyManager.getProperty(
                    WEB3QuotePlugin.MONITOR_COUNT_THRESHOLD_IFO_PREF_NAME, 
                    WEB3QuoteConstants.MONITOR_COUNT_THRESHOLD);
        }
        else
        {
            countThreshold = WEB3QuoteConstants.MONITOR_COUNT_THRESHOLD;
        }

        return countThreshold;
    }
    
    /**
     * @@return 指定した時間が実行時間か判定する。
     */
    boolean isActiveTimezone(Timestamp targetTime)
    {
        String target = 
            GtlUtils.getThreadSafeSimpleDateFormat("HH:mm").format(targetTime);
        boolean result = false;
        for (int i = 0; i < timezoneList.length; i++)
        {
            if (timezoneList[i].startTime != null
                && timezoneList[i].endTime != null
                && timezoneList[i].startTime.compareTo(target) <= 0
                && timezoneList[i].endTime.compareTo(target) >= 0)
            {
                result = true;
                break;
            }
        }
        return result;
    }
    
    /**
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("QuoteMonitorSettings={");
        sb.append("interval=").append(interval);
        sb.append(",warningThreshold=").append(warningThreshold);
        for (int i = 0; i < timezoneList.length; i++)
        {
            sb.append(",timezone[").append(i).append("]=");
            sb.append("{startTime=").append(timezoneList[i].startTime);
            sb.append(",endTime=").append(timezoneList[i].endTime);
            sb.append("}");
        }
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * このクラスを初期化する。
     */
    private void init()
    {
        this.interval = 
            WEB3QuotePropertyManager.getProperty(
                WEB3QuotePlugin.MONITOR_INTERVAL_PREF_NAME, 
                WEB3QuoteConstants.MONITOR_INTERVAL);
        this.warningThreshold = 
            WEB3QuotePropertyManager.getProperty(
                WEB3QuotePlugin.MONITOR_WARNING_THRESHOLD_PREF_NAME, 
                WEB3QuoteConstants.MONITOR_WARNING_THRESHOLD);
        initTimezone();
    }
    
    /**
     * 実施時間の配列を初期化する。
     */
    private void initTimezone()
    {
        Map timezoneMap = new TreeMap();
        Map preferences = GtlUtils.getTradingSystem().getPreferences();
        for (Iterator it = preferences.entrySet().iterator(); it.hasNext();)
        {
            String index = null;
            String startTime = null;
            String endTime = null;
            Map.Entry entry = (Map.Entry) it.next();
            String name = (String) entry.getKey();
            if (name.startsWith(WEB3QuotePlugin.MONITOR_START_TIME_PREF_NAME))
            {
                index = getIndex(name, WEB3QuotePlugin.MONITOR_START_TIME_PREF_NAME);
                startTime = (String) entry.getValue();
            } else if (name.startsWith(WEB3QuotePlugin.MONITOR_END_TIME_PREF_NAME))
            {
                index = getIndex(name, WEB3QuotePlugin.MONITOR_END_TIME_PREF_NAME);
                endTime = (String) entry.getValue();
            }
            if (index != null)
            {
                Timezone timezone = (Timezone) timezoneMap.get(index);
                if (timezone == null)
                {
                    timezone = new Timezone();
                    timezoneMap.put(index, timezone);
                }
                if (startTime != null)
                {
                    timezone.startTime = startTime;
                } else if (endTime != null)
                {
                    timezone.endTime = endTime;	
                }
            }
            
        }
        Timezone[] timezoneList = 
            new Timezone[timezoneMap.entrySet().size()];
        this.timezoneList = 
            (Timezone[]) timezoneMap.values().toArray(timezoneList);
        
    }
    
    /** 
     * 実施時間が複数定義されている場合のINDEXを取得する。
     */
    private String getIndex(String name, String prefix)
    {
        String index = ZERO;
        if (name.length() > prefix.length())
        {
            index = name.substring(prefix.length() + 1);
        }
        return index;
    }
    
    /**
     * 実施時間From-Toを表すクラス
     */
    private class Timezone
    {
        String startTime;
        String endTime;
    }
    
}
@
