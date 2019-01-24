/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteConstantsクラス(DOTQuoteConstants.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/24 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;


/**
 * (時価アダプタ定数定義インターフェース)<BR>
 * <BR>
 * 時価アダプタで使用する定数を定義したインターフェース。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
interface DOTQuoteConstants
{
    
    /**
     * ハートビートメッセージに含まれるホスト名の桁数
     */
    static final int HOST_NAME_SIZE = 8;
    
    /**
     * ハートビートメッセージに含まれるフラグの桁数
     */
    static final int FLAG_SIZE = 1;
    
    /**
     * ハートビートメッセージの桁数
     */
    static final int HEARTBEAT_SIZE = (HOST_NAME_SIZE * 2) + FLAG_SIZE; // 17
    
    /**
     * 時価サーバより受信するシーケンスNOの桁数
     * 
     * ルールエンジン上で管理される時価情報のシーケンスNOの桁数とは別に管理される
     */
    static final int SEQUENCE_NO_SIZE = 10;
    
    /**
     * 時価サーバより受信するレコード数の桁数
     */
    static final int NUM_OF_RECORDS_SIZE = 2;
    
    /**
     * 時価サーバより受信する時価情報メッセージ.ヘッダー部の桁数
     */
    static final int HEADER_SIZE = SEQUENCE_NO_SIZE + NUM_OF_RECORDS_SIZE; // 12
    
    /**
     * 時価サーバより受信する1件の時価情報の桁数
     */
    static final int RECORD_SIZE = 180;
    
    /**
     * 時価サーバより1度に受信する最大の時価情報レコード数
     */
    static final int MAX_RECORDS = 10;
    
    /**
     * 時価サーバより受信する時価情報メッセージ.データ部の桁数
     */
    static final int MAX_DATA_SIZE = RECORD_SIZE * MAX_RECORDS; // 1800
    
    /**
     * 文字セット
     */
    static final String DEFAULT_ENCODING = "ISO8859_1";
    
    /**
     * 時価サーバのアドレス
     */
    static final String SERVER_ADDRESS = "localhost";
    
    /**
     * 時価サーバのポート番号
     */
    static final int SERVER_PORT = 8000;
    
    /**
     * 時価メッセージキューのサイズ
     */
    static final int QUEUE_SIZE = 8;
    
    /**
     * WEB3DefaultQuoteCacheStoreImplの初期容量
     */
    static final int CACHE_SIZE = 4999;
    
    /**
     * 受信処理を実行スレッドの優先度
     */
    static final int RECEIVER_PRIORITY = 3;
    
    /**
     * 更新処理を実行スレッドの優先度
     */
    static final int UPDATER_PRIORITY = 2;
    
    /**
     * 時価サーバに再接続を試みる間隔（通常）
     */
    static final long RETRY_INTERVAL_NORAML = 5000;
    
    /**
     * 時価サーバに再接続を試みる間隔（エコノミー）
     */
    static final long RETRY_INTERVAL_ECONOMY = 30000;
    
    /**
     * 時価サーバに再接続を試みる間隔を切り替える閾値
     */
    static final int RETRY_THRESHOLD = 20;
    
    /**
     * 日経225の時価情報が更新されているか確認する間隔
     */
    static final long MONITOR_INTERVAL = 60000;
    
    /**
     * 日経225の時価情報が更新されていない場合にエラーを発生させる閾値
     */
    static final int MONITOR_WARNING_THRESHOLD = 3;
    
    /**
     * 日経225の時価情報が更新されているか確認する時間帯
     */
    static final String MONITOR_TIME_DEF = "09:00-11:00,12:30-15:00";
    
    /**
     * ハートビートメッセージを時価サーバに送信する間隔
     */
    static final long HEARTBEAT_INTERVAL = 5000;
    
    /**
     * 時価サーバとの接続を行うソケットに設定するSO_TIMEOUTオプションの値
     */
    static final int HEARTBEAT_TIMEOUT = 10000;
    
    /**
     * ハートビートメッセージを時価サーバに送信するスレッドの優先度
     */
    static final int HEARTBEAT_PRIORITY = 1;
    

}
