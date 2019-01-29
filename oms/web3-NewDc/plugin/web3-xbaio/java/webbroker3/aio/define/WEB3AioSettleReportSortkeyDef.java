head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.47.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSettleReportSortkeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioSettleReportSortkeyDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/05/31 韋念瓊 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 決済連携レポートソートキーのキー項目　@定数定義インタフェイス
 *                                                                     
 * @@author 韋念瓊
 * @@version 1.0
 */
public interface WEB3AioSettleReportSortkeyDef {
    
    /**
     * accountCode： 顧客コード 
     */
    public static final String ACCOUNT_CODE = "accountCode";
     
    /**
     * comDebitNumber： .comデビット決済取引番号 
     */
    public static final String COMDEBIT_NUMBER = "comDebitNumber";

    /**
     * shopOrderId： 加盟店注文番号  
     */
    public static final String SHOP_ORDERID = "shopOrderId";
    
    /**
     * receptionDate： 受付日時  
     */
    public static final String RECEPTION_DATE = "receptionDate";
     
}
@
