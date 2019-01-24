/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QuoteMonitorSettingsFactoryクラス(DOTQuoteMonitorSettingsFactory.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/01/27 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * (時価モニター設定ファクトリ)<BR>
 * 
 * 時価モニター設定のファクトリクラス。<BR>
 * <BR>
 * SYSTEM_PREFERENCESテーブルから読み込んだ値で、
 * 時価モニター設定のインスタンスを生成する。<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteMonitorSettingsFactory
{

    /**
     * 時価アダプタプロパティ
     */
    private DOTQuoteProperties properties;

    /**
     * コンストラクタ
     * 
     * @param l_properties 時価アダプタプロパティ
     */
    public DOTQuoteMonitorSettingsFactory(DOTQuoteProperties l_properties)
    {
        this.properties = l_properties;
    }

    /**
     * (create)<BR>
     * <BR>
     * WEB3QuoteMonitorSettingsクラスのインスタンスを生成する。<BR>
     * 
     * @return 生成されたWEB3QuoteMonitorSettingsクラス。
     */
    public DOTQuoteMonitorSettings create()
    {
        long l_lngInterval = properties.getProperty(
            DOTQuoteProperties.MONITOR_INTERVAL_PREF_NAME,
            DOTQuoteConstants.MONITOR_INTERVAL);
        int l_intWarningThreshold = properties.getProperty(
            DOTQuoteProperties.MONITOR_WARNING_THRESHOLD_PREF_NAME,
            DOTQuoteConstants.MONITOR_WARNING_THRESHOLD);
        String l_strMonitoringTimeDef = getMonitoringTimeDef();
        return new DOTQuoteMonitorSettings(
            l_lngInterval,
            l_intWarningThreshold,
            l_strMonitoringTimeDef);
    }

    /**
     * (get時価モニター有効時間)<BR>
     * <BR>
     * 時価モニター有効時間を取得する。<BR>
     * 
     * @return 時価監視時間帯
     */
    private String getMonitoringTimeDef()
    {

        Map l_startTimeDefs = properties
            .getProperties(DOTQuoteProperties.MONITOR_START_TIME_PREF_NAME);

        int l_intCount = 0;
        StringBuffer l_sb = new StringBuffer();

        if (l_startTimeDefs != null && !l_startTimeDefs.isEmpty())
        {

            l_startTimeDefs = new TreeMap(l_startTimeDefs);
            for (Iterator l_it = l_startTimeDefs.entrySet().iterator(); l_it
                .hasNext();)
            {

                String l_strStartTime = null;
                String l_strEndTime = null;

                Map.Entry l_entry = (Map.Entry) l_it.next();
                String l_strStartTimeKey = (String) l_entry.getKey();
                l_strStartTime = (String) l_entry.getValue();
                l_strEndTime = getEndTime(l_strStartTimeKey);

                if (l_strStartTime != null && l_strEndTime != null)
                {
                    if (l_intCount > 0)
                    {
                        l_sb.append(",");
                    }
                    l_sb.append(l_strStartTime);
                    l_sb.append("-");
                    l_sb.append(l_strEndTime);
                    l_intCount++;
                }

            }
        }
        return (l_intCount > 0) ? l_sb.toString()
            : DOTQuoteConstants.MONITOR_TIME_DEF;
    }

    /**
     * (get時価モニター有効時間【至】)<BR>
     * <BR>
     * 時価監視時間帯【自】に対応する時価監視時間帯【至】を取得する。<BR>
     * 
     * @param l_startTimeKey　時価監視時間帯【自】のプロパティ名 
     * @return 時価監視時間帯【至】
     */
    private String getEndTime(String l_startTimeKey)
    {
        String l_strEndTimeKey = DOTQuoteProperties.MONITOR_END_TIME_PREF_NAME;
        if (!DOTQuoteProperties.MONITOR_START_TIME_PREF_NAME
            .equals(l_startTimeKey))
        {
            l_strEndTimeKey = DOTQuoteProperties.MONITOR_END_TIME_PREF_NAME;
            int l_intIndex = l_startTimeKey.lastIndexOf(".");
            if (l_intIndex >= DOTQuoteProperties.MONITOR_START_TIME_PREF_NAME
                .length())
            {
                l_strEndTimeKey += l_startTimeKey.substring(l_intIndex);
            }
        }
        return properties.getProperty(l_strEndTimeKey);
    }

}