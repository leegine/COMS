head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteConstants.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WebBroker3用時価サービスのための定数定義クラス(WEB3QuoteConstants.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 山田　@卓司(FLJ) 新規作成
                 : 2005/04/28 山田　@卓司(FLJ) 時価監視のための定数定義を追加
                 : 2005/05/17 山田　@卓司(FLJ) ハートビート関連の定数定義を追加
                 : 2005/05/24 山田　@卓司(FLJ) ローカルポート、ハートビートスレッドの優先度のデフォルト値を変更
                 : 2009/01/28 許　@　@競(FLJ) CSK時価情報チェックの強化対応
                 : 2009/04/23 齋藤　@栄三(FLJ) 時価システムQUICKへの移行
*/
package webbroker3.quoteadaptor.stdimpls;

/**
 * WebBroker3用時価サービスのための定数定義。
 *
 * @@author Yoshihara Tadafumi
 * @@version 1.0
 */
public interface WEB3QuoteConstants
{
    
    /**
     * ハートビートメッセージに含まれるホスト名の桁数
     */
    int HOST_NAME_SIZE = 8;
    
    /**
     * ハートビートメッセージの長さ
     */
    int HEARTBEAT_SIZE = 17;
    
    /**
     * 時価サーバより受信するFROMホスト名の桁数
     * 
     */
    int FROM_MACHINE_NAME_SIZE = 8;
    
    /**
     * 時価サーバより受信するTOホスト名の桁数
     * 
     */
    int TO_MACHINE_NAME_SIZE = 8;

    /**
     * 時価情報データユニットのシーケンス番号のサイズ（バイト数）。
     *
     */
    int SEQUENCE_NO_SIZE = 10;

    /**
     * 時価情報データユニットのレコード数のサイズ（バイト数）。
     *
     */
    int NUM_OF_RECORDS_SIZE = 2;

    /**
     *　@時価情報データユニットのヘッダ部のサイズ（バイト数）。
     *
     */
    int HEADER_SIZE = SEQUENCE_NO_SIZE + NUM_OF_RECORDS_SIZE; //12

    /**
     * 時価情報データユニットに含まれるレコードのサイズ（バイト数）。
     *
     */
    int RECORD_SIZE = 180;

    /**
     * 時価情報データユニットに含まれるレコード数の上限。
     *
     */
    int MAX_RECORDS = 10;

    /**
     * 時価情報データユニットのデータ部のサイズの上限（バイト数）。
     *
     */
    int MAX_DATA_SIZE = MAX_RECORDS * RECORD_SIZE; //1790

    /**
     * Encoding for char->int/double conversion.
     */
    String ENCODING = "ISO8859_1";

    /**
     * デフォルトのサービスID。
     */
    String WEB3_QUOTE_SERVICE = "TCP";

    /**
     * デフォルトのサービスID。
     */
    String WEB3_QUOTE_RMM_PRIMARY_SERVICE = "RMM.PRIMARY";

    /**
     * デフォルトのサービスID。
     */
    String WEB3_QUOTE_RMM_SECONDARY_SERVICE = "RMM.SECONDARY";

    /**
     * 全サービスを表すサービスID。
     */
    String ALL_SERVICES = "ALL";

    /**
     * デフォルトのデータ取得用接続先アドレス。
     *
     */
    String SERVER_ADDRESS = "localhost";

    /**
     * デフォルトのデータ取得用接続先ポート番号。
     *
     */
    int SERVER_PORT = 8000;

    /**
     * デフォルトのデータ取得用接続元アドレス。
     *
     */
    String LOCAL_ADDRESS = "localhost";

    /**
     * デフォルトのデータ取得用接続元ポート番号。
     *
     */
    int LOCAL_PORT = 0;
    
    /**
     * 受信した時価情報データユニットから生成される時価情報
     * 通知イベントを一時的に保存するためのキューのサイズ。
     *
     */
    int QUEUE_SIZE = 16; // must be the power of two

    /**
     * 最終的に時価情報を保存するテーブルの初期サイズ。
     *
     */
    int CACHE_SIZE = 5009; // prime number is better

    /**
     * 時価サーバへの接続に失敗した場合の再試行間隔（ミリ秒）。
     *
     */
    long RETRY_INTERVAL_NORMAL = 3000;

    /**
     * 時価サーバへの接続にRETRY_THRESHHOLDで指定された回数以上
     * 失敗した場合の再試行間隔（ミリ秒）。
     *
     */
    long RETRY_INTERVAL_ECONOMY = 60000;

    /**
     * ここで指定された回数以上、時価サーバへの接続に失敗する
     * と再試行間隔がRETRY_INTERVAL_NORMALからRETRY_INTERVAL_ECONOMY
     * に変わる。
     *
     */
    int RETRY_THRESHHOLD = 20;

    /**
     * 時価情報通知イベントを時価サーバから受信するスレッドの、
     * デフォルトのスレッドプライオリティーに対する優先度。
     * java.lang.Thread.NORM_PRIORITY + RECEIVER_PRIORITYが
     * 当該スレッドのスレッドプライオリティーとなる。
     */
    int RECEIVER_PRIORITY = 3;

    /**
     * 時価情報通知イベントをハンドラーに渡すスレッドの、
      * デフォルトのスレッドプライオリティーに対する優先度。
     * java.lang.Thread.NORM_PRIORITY + RECEIVER_PRIORITYが
     * 当該スレッドのスレッドプライオリティーとなる。
     */
    int UPDATER_PRIORITY = 2;
    
    /**
     * 特定の銘柄（指数）が更新されているか監視する間隔
     */
    long MONITOR_INTERVAL = 60000;
    
    /**
     * 特定の銘柄（指数）が更新されていない場合に警告を表示する閾値
     */
    int MONITOR_WARNING_THRESHOLD = 3;
    
    /**
     * 時価更新されていない場合にチェックエラーとする銘柄の個数閾値
     */
    int MONITOR_COUNT_THRESHOLD = 1;
    
    /**
     * ハートビートを実行する間隔
     */
    int HEARTBEAT_INTERVAL = 5000;
    
    /**
     * ハートビートを実行するソケットのタイムアウト時間
     */
    int HEARTBEAT_TIMEOUT = 10000;
    
    /**
     * ハートビートを実行するスレッドのプライオリティー
     */
    int HEARTBEAT_PRIORITY = 1;
    
    /**
     * 銘柄ID、市場IDに含まれる会社コードの桁数
     */
    int INSTITUTION_CODE_SIZE = 2;
    
    /**
     * 銘柄IDに含まれる会社、商品コードの桁数
     */
    int PRODUCT_HEAD_SIZE = 4;
    
    /**
     * 銘柄コードの桁数
     */
    int PRODUCT_CODE_LENGTH = 5;
    
    /**
     * 監視対象外の先物銘柄限月
     */
    String EXCEPT_MONTH_OF_DELIVERY = "999912";
    
    /**
     * 監視ログ出力の上限値
     */
    int MISSING_QUOTE_MAX_NUMBER = 50;
    
    /**
     * 時価サーバより受信するRMM時価情報メッセージ.ヘッダー部の桁数
     */
    int RMM_HEADER_SIZE = FROM_MACHINE_NAME_SIZE + TO_MACHINE_NAME_SIZE + SEQUENCE_NO_SIZE + NUM_OF_RECORDS_SIZE; // 28
    
    /**
     * RMM初期化要求リトライ間隔(ミリ秒)
     */
    long RMM_INIT_RETRY_INTERVAL = 5000;
    
    /**
     * RMMデータソースを切替する初期化要求失敗閾値
     */
    int RMM_DS_CHANGE_INIT_THRESHOLD = 2;
    
    /**
     * 時価プロトコル(TCP)
     */
    String QUOTE_PROTOCOL_TCP = "TCP";
    
    /**
     * 時価プロトコル(RMM)
     */
    String QUOTE_PROTOCOL_RMM = "RMM";
    
    /**
     * 区切り文字
     */
    String DELIMITER = ",";

    /**
     * RMM時価情報通知イベントをハンドラーに渡すスレッドの、
      * デフォルトのスレッドプライオリティーに対する優先度。
     * java.lang.Thread.NORM_PRIORITY + RMM_UPDATER_PRIORITYが
     * 当該スレッドのスレッドプライオリティーとなる。
     */
    int RMM_UPDATER_PRIORITY = 2;
    
    /**
     * 受信した時価情報データユニットから生成されるRMM時価情報
     * 通知イベントを一時的に保存するためのキューのサイズ。
     *
     */
    int RMM_QUEUE_SIZE = 8192; // must be the power of two
    
    /**
     * RMM受信トレース設定
     */
    boolean RMM_RCVD_TRACE = false;
}
@
