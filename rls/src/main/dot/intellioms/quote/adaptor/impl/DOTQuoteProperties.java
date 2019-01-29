/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QuotePropertiesクラス(DOTQuoteProperties.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/01/27 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.util.Map;

import jp.co.dir.dot.intellioms.util.DOTSystemPreferencesAdaptor;


/**
 * (時価アダプタプロパティ)<BR>
 * <BR>
 * 時価アダプタの各設定情報を保持するクラス。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteProperties
{

    /** プロパティ名：時価サーバのIPアドレス */
    public static final String SERVER_ADDRESS_PREF_NAME = "webbroker3.quoteadaptor.server.address";

    /** プロパティ名：時価サーバのポート番号 */
    public static final String SERVER_PORT_PREF_NAME = "webbroker3.quoteadaptor.server.port";

    /** プロパティ名：受信スレッドの優先度 */
    public static final String RECEIVER_PRIOTIRY_PREF_NAME = "webbroker3.quoteadaptor.receiver.priority";

    /** プロパティ名：更新スレッドの優先度 */
    public static final String UPDATER_PRIORITY_PREF_NAME = "webbroker3.quoteadaptor.updater.priority";

    /** プロパティ名：接続をリトライする間隔【エコノミー】 */
    public static final String RETRY_INTERVAL_ECONOMY_PREF_NAME = "webbroker3.quoteadaptor.retry.interval.economy";

    /** プロパティ名：接続をリトライする間隔【通常】 */
    public static final String RETRY_INTERVAL_NORMAL_PREF_NAME = "webbroker3.quoteadaptor.retry.interval.normal";

    /** プロパティ名：接続をリトライする間隔を変更する閾値 */
    public static final String RETRY_THRESHOLD_PREF_NAME = "webbroker3.quoteadaptor.retry.threshold";

    /** プロパティ名：時価メッセージキューの容量 */
    public static final String QUEUE_SIZE_PREF_NAME = "webbroker3.quoteadaptor.queue.size";

    /** プロパティ名：デフォルト時価情報管理テーブルの初期容量 */
    public static final String CACHE_SIZE_PREF_NAME = "webbroker3.quoteadaptor.cache.size";
    
    /** プロパティ名：NK225の時価情報が更新されているか監視する間隔 */
    public static final String MONITOR_INTERVAL_PREF_NAME = "webbroker3.quoteadaptor.monitor.interval";

    /** プロパティ名：NK225の時価情報が更新されていない場合に警告を表示する閾値 */
    public static final String MONITOR_WARNING_THRESHOLD_PREF_NAME = "webbroker3.quoteadaptor.monitor.waring.threshold";

    /** プロパティ名：NK225の時価情報を監視する時間【自】 */
    public static final String MONITOR_START_TIME_PREF_NAME = "webbroker3.quoteadaptor.monitor.start.time";

    /** プロパティ名：NK225の時価情報を監視する時間【至】 */
    public static final String MONITOR_END_TIME_PREF_NAME = "webbroker3.quoteadaptor.monitor.end.time";
    
    /** プロパティ名：時価サーバにハートビートメッセージを送信する間隔 */
    public static final String HEARTBEAT_INTERVAL_PREF_NAME = "webbroker3.quoteadaptor.heartbeat.interval";
    
    /** プロパティ名：時価サーバとの接続ソケットに設定するSO_TIMEOUTオプションの値 */
    public static final String HEARTBEAT_TIMEOUT_PREF_NAME = "webbroker3.quoteadaptor.heartbeat.timeout";
    
    /** プロパティ名：ハートビートスレッドの優先度 */
    public static final String HEARTBEAT_PRIORITY_PREF_NAME = "'webbroker3.quoteadaptor.heartbeat.priority";

    /** SYSTEM_PREFERENCESテーブルアダプタ */
    private DOTSystemPreferencesAdaptor properties;

    /**
     * コンストラクタ<BR>
     * 
     * @param l_systemPreferencesAdaptor SYSTEM_PREFERENCESテーブルアダプタ
     */
    public DOTQuoteProperties(
        DOTSystemPreferencesAdaptor l_systemPreferencesAdaptor)
    {
        this.properties = l_systemPreferencesAdaptor;
    }

    /**
     * 指定した接頭辞で始まる設定のキーと設定値を保持するマップを取得する。<BR>
     * 
     * @param l_strPrefix 接頭辞
     * @return 指定した接頭辞で始まる設定のキーと設定値を保持するマップ
     */
    public Map getProperties(String l_strPrefix)
    {
        return properties.getProperties(l_strPrefix);
    }

    /**
     * 指定したキーの設定値を取得する。<BR>
     * <BR>
     * 指定したキーの設定値が存在しない場合<code>null</code>を返す。
     * 
     * @param l_strKey キー
     * @return 設定値
     */
    public String getProperty(String l_strKey)
    {
        return properties.getProperty(l_strKey);
    }

    /**
     * 指定したキーの設定値を取得する。<BR>
     * <BR>
     * 指定したキーの設定値が存在しない場合デフォルト値を返す。
     * 
     * @param l_strKey キー
     * @param l_intDefaultValue デフォルト値
     * @return
     */
    public int getProperty(String l_strKey, int l_intDefaultValue)
    {
        return properties.getProperty(l_strKey, l_intDefaultValue);
    }

    /**
     * 指定したキーの設定値を取得する。<BR>
     * <BR>
     * 指定したキーの設定値が存在しない場合デフォルト値を返す。
     * 
     * @param l_strKey キー
     * @param l_lngDefaultValue デフォルト値
     * @return
     */
    public long getProperty(String l_strKey, long l_lngDefaultValue)
    {
        return properties.getProperty(l_strKey, l_lngDefaultValue);
    }

    /**
     * 指定したキーの設定値を取得する。<BR>
     * <BR>
     * 指定したキーの設定値が存在しない場合デフォルト値を返す。
     * 
     * @param l_strKey キー
     * @param l_strDefaultValue デフォルト値
     * @return
     */
    public String getProperty(String l_strKey, String l_strDefaultValue)
    {
        return properties.getProperty(l_strKey, l_strDefaultValue);
    }
}