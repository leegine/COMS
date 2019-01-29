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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteMonitorSettings�N���X(WEB3QuoteMonitorSettings.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/05/23 �R�c�@@��i(FLJ) �V�K�쐬
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
 * �����Ď��T�[�r�X�̐ݒ��ێ�����N���X
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3QuoteMonitorSettings
{
    
    /** ���O�o�̓��[�e�B���e�B */
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3QuoteMonitorSettings.class);
    
    /** �f�o�b�N�o�̓t���O */
    private static final boolean DBG = LOG.ison();
    
    /** �V���O���g�� */
    private static final WEB3QuoteMonitorSettings INSTANCE = 
        new WEB3QuoteMonitorSettings();
    
    /** ������u0�v�̒萔��` */
    private static final String ZERO = "0";
    
    /** �敨OP�Ɋւ���L�[���[�h */
    public final String IFO = "IFO";
    
    /**
     * �Ď�����Ԋu
     */
    private long interval;
    
    /**
     * �x�����o�͂���臒l
     */
    private int warningThreshold;
    
    /**
     * ���s���鎞�ԁiFROM-TO�j�̔z��
     */
    private Timezone[] timezoneList;
    
    /**
     * �R���X�g���N�^
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
     * ���̃N���X�̃V���O���g�����擾����B
     * 
     * @@return �V���O���g��
     */
    static WEB3QuoteMonitorSettings getInstance()
    {
        return INSTANCE;
    }

    /**
     * @@return interval ���擾����B
     */
    long getInterval()
    {
        return interval;
    }
    
    /**
     * @@return warningThreshold ���擾����B
     */
    int getWarningThreshold()
    {
        return warningThreshold;
    }
    
    /**
     * �J�E���g臒l���擾����B
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
     * @@return �w�肵�����Ԃ����s���Ԃ����肷��B
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
     * ���̃N���X������������B
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
     * ���{���Ԃ̔z�������������B
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
     * ���{���Ԃ�������`����Ă���ꍇ��INDEX���擾����B
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
     * ���{����From-To��\���N���X
     */
    private class Timezone
    {
        String startTime;
        String endTime;
    }
    
}
@
