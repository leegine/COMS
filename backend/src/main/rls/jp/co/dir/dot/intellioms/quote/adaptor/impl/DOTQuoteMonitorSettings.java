/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QuoteMonitorSettingsクラス(DOTQuoteMonitorSettings.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/13 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

/**
 * (時価モニター設定)<BR>
 * <BR>
 * 時価モニターの各種設定情報を保持するクラス。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteMonitorSettings
{

    /** 監視間隔 */
    private final long interval;

    /** 警告表示閾値 */
    private final int warningThreshold;

    /** 時価モニター有効時間 */
    private final String monitoringTimeDef;
    
    /**
     * コンストラクタ<BR>
     * 
     * 時価モニター有効時間には「HH:mm-HH:mm」形式の文字列を指定する。<BR>
     * <BR>
     * 「-」前の「HH:mm」が有効時間【自】、「-」後の「HH:mm」が有効時間【至】を表す。
     * 複数の有効時間を設定する場合は、カンマ区切りで指定する。<BR>
     * 【例】「09:00-11:00,12:30-15:00」<BR>
     * 
     * @param interval 監視間隔
     * @param warningThreshold 警告表示閾値
     * @param monitoringTimeDef 時価モニター有効時間
     */
    DOTQuoteMonitorSettings(long interval, int warningThreshold,
        String monitoringTimeDef)
    {
        this.interval = interval;
        this.warningThreshold = warningThreshold;
        this.monitoringTimeDef = monitoringTimeDef;
    }

    /**
     * (get監視間隔)<BR>
     * <BR>
     * 監視間隔を取得する。<BR>
     * 
     * @return 監視間隔
     */
    public long getInterval()
    {
        return interval;
    }

    /**
     * (get警告表示閾値)<BR>
     * <BR>
     * 警告表示閾値を取得する。<BR>
     * 
     * @return 警告表示閾値
     */
    public int getWarningThreshold()
    {
        return warningThreshold;
    }

    /**
     * (get時価モニター有効時間)<BR>
     * <BR>
     * 時価モニター有効時間を取得する。<BR>
     * 
     * @return 時価モニター有効時間
     */
    public String getMonitoringTimeDef()
    {
        return monitoringTimeDef;
    }
    
}