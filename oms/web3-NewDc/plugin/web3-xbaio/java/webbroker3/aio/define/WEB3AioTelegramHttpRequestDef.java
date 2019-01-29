head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.50.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTelegramHttpRequestDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioTelegramHttpRequestDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 王蘭芬 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * マルチバンク電文処理サービスImplのweb3Requestのキー値　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioTelegramHttpRequestDef
{

    /**
     * OrderDemand 注文要求受付  
     */
    public static final String ORDERDEMAND = "OrderDemand";

    /**
     * SettleStart 決済開始受付  
     */
    public static final String SETTLE_START = "SettleStart";

    /**
     * SettleResult 決済結果通知  
     */
    public static final String SETTLE_RESULT = "SettleResult";
    
    /**
     * 127.0.0.1 電文発行元のリモートアドレス  
     */
    //public static final String REMOTE_ADDRESS = "127.0.0.1";
    public static final String REMOTE_ADDRESS = "_REMOTE_ADDRESS";

    /*
     * タイムアウト
     */
    public static final String AIO_TIMEOUT = "_AIO_TIMEOUT";
    
    // ========remain zhou-yong NO.1 begin ==========
    
    /**
     * URL 処理がキャンセルされた場合、エラーが発生した場合に呼び出されるWEB3のURL   
     */
    //public static final String URL = "[URL]";
    //public static final String URL = "https://trade.wb3dev.jp/webbroker3/Web3App";
    //public static final String URL = "https://trade.retela.co.jp/webbroker3/Web3App";

    /**
     *  0   
     */
    public static final String IO_RTURL_ADDRESS0 = "0";
    
    /**
     *  1   
     */
    public static final String IO_RTURL_ADDRESS1 = "1";
    
    /**
     *  2   
     */
    public static final String IO_RTURL_ADDRESS2 = "2";  
    
    /**
     * URL 処理が完了した場合、エラーが発生した場合に呼び出されるWEB3のURL 
     */
    //public static final String COMPLETE_URL = "[URL]";
    //public static final String COMPLETE_URL = "https://trade.wb3dev.jp/webbroker3/Web3App";
    //public static final String COMPLETE_URL = "https://trade.retela.co.jp/webbroker3/Web3App";
    
    public static final String WEB3_URL = "_WEB3_URL";
    
    // ========remain zhou-yong NO.1 end ==========
    
    /**
     * PF_URL 処理が決済PFのURL  
     */
    //public static final String PF_URL = "PF_URL";
    //public static final String PF_URL = "http://172.17.186.49/webbroker/web2web/web3OrderDemand.html";
    //public static final String PF_URL = "https://www.safetydebit2.jp/asp/user/payment/servlet/E10AspFuriwakeSvlt";
    public static final String PF_URL = "_PF_URL";
    
    /**
     *　@受信電文データ.get("payStatus")の戻り値が"START" の場合
     */
    public static final String START = "START";
    
    /**
     *　@受信電文データ.get("payStatus")の戻り値が"ERROR" の場合
     */
    public static final String ERROR = "ERROR";

    /**
     * 受信電文データ.get("protocolVersion")の戻り値が"V1.0" の場合 
     */
    public static final String V1DOT0 = "V1.0";

    /**
     * レスポンスデータにContentType: text/plain 
     */
    public static final String ContentType = "text/plain; charset=Shift_JIS";

    /**
     * レスポンスのメッセージボディ START TAG: <SHOPMSG>
     */
    public static final String SHOPMSG_START = "<SHOPMSG>";

    /**
     * レスポンスのメッセージボディ END TAG: <SHOPMSG>
     */
    public static final String SHOPMSG_END = "</SHOPMSG>";
}
@
