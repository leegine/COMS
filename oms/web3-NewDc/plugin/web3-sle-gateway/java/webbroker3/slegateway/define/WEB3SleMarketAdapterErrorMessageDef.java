head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleMarketAdapterErrorMessageDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3SleMarketAdapterErrorMessageDefineクラス
Author Name      : Daiwa Institute of Research
Revision History : 2006/10/23 李 新規作成
*/

package webbroker3.slegateway.define;

/**
 * 市場直結SLEエラーメッセージ 定義インタフェイス
 */
public class WEB3SleMarketAdapterErrorMessageDef {
	
	/**
	 * 市場受付可能な時間帯外のため、送信失敗でした。
	 */
	public static final String MARKET_STATUS_NOVALID_PERIOD = "The market is already closed for the day.";
	
	/** 
	 * 市場が送信できないステータスのため、送信しませんでした。 
	 */
	public static final String MARKET_STATUS_NOVALID_GLLD = "the order sending failed by the invalid market status.";

	/** 
	 * SLE送信コネクタはオフラインまたはクローズ状態であるため、送信失敗でした。
	 */
	public static final String SLE_CONNECTOR_OFFLINE_STATUS = "the order sending failed because SLE Connector is at offline.";

	/** 
	 * 処理スレッド専用ロック取得できないため処理中止。 
	 */
	public static final String SEND_THREAD_LOCKED_STATUS = "The Thread belonging the same no is locked.";

	/** 
	 * 注文リクエストを重複して送信しないため、送信しませんでした。 
	 */
	public static final String ORDER_MESSAGE_SEND_DUPLI = "the order request will be send duplicately.";
	
	/**
	 * 市場に対応するSLEコネクタのGLIDが存在しないため送信しませんでした。
	 */
	public static final String MARKET_GLID_NOT_EXISTED = "sle GLID for specified Market is not existed .";
    
	/** 
	 * SLEコネクタが停止または接続失いのため送信失敗でした。 
	 */
	public static final String SLE_ADAPTER_STOP = "the order sending failed because the sle connector is closed or link lost.";   

	/** 
	 * SLEのORDER_BOOKへの問い合わせが失敗でした。
	 */
	public static final String SLE_ORDER_BOOK_REQUEST_FAIL = "the query request for sle order book failed.";
    
	/**
	 * リカバリー不可能状態であるため処理失敗でした。 
	 */
	public static final String SLE_RECOVERY_NOT_AVAILABLE = "recovery operation stopped because SLE connection is not available.";
}


@
