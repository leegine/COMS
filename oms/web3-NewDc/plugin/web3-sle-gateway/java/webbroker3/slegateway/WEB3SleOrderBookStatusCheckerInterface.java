head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleOrderBookStatusCheckerInterface.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SleOrderBookStatusCheckerクラス
Author Name      : Daiwa Institute of Research
Revision History : 2006/06/06 李（FLJ) 新規作成
*/
package webbroker3.slegateway;

import java.util.Map;
import webbroker3.slebase.data.SleSendQRow;

/**
 * SLEのORDER_BOOK問い合わせツールクラス
 * @@author  : 李（FLJ）
 * @@version : 1.0
 */
public interface WEB3SleOrderBookStatusCheckerInterface {

	/**
	 * SEND_Q注文メッセージが既にSLEに正しく送信されるかチェック
	 * @@param sleSendqRow SEND_Q注文メッセージ
	 * @@return 送信済み:trueを返す　@他:falseを返す。
	 */ 
	public boolean isAlreadySent(SleSendQRow sleSendqRow) throws RuntimeException;
	
	/**
	 * SEND_Q注文メッセージが既にSLEに正しく送信されるかチェック
	 * @@param sleSendqRow SEND_Q注文メッセージ
	 * @@param orderBook 取得した注文ORDER_BOOK
	 * @@return 送信済み:trueを返す　@他:falseを返す。
	 */
	public boolean isAlreadySent(SleSendQRow sleSendqRow, Map orderBook) throws RuntimeException;
	

	/**
	 * 市場コード、銘柄コードに対応するORDER BOOK問い合わせ結果取得
	 * @@param marketCode 市場コード
	 * @@param productCode　@銘柄コード
	 * @@return　@ORDER BOOK の問い合わせ結果とInternalRef (注文ID)のマッピング対応関係を保持するMap
	 */
	public Map getOrderBook(String marketCode, String productCode) throws RuntimeException;
}
@
