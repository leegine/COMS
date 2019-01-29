head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PlsBvsDetailOrderRecDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 明細&入出金レコード(WEB3PlsBvsDetailOrderRecDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 王敏(sinocom) 新規作成
Revesion History : 2004/11/12 賈元春(sinocom) 作成
*/
package webbroker3.tradehistory.define;

/**
 * 明細&入出金レコード 定数定義インタフェイス
 * @@author 王敏
 * @@version 1.0
 */
public interface WEB3PlsBvsDetailOrderRecDef
{


    /**
     * 0：残高レコード
     */
    public final static String BALANCE_REC = "0";
    /**
     * 1：明細レコード
     */
    public final static String DETAIL_REC = "1";

    /**
     * 2：入出金レコード
     */
    public final static String PAYMENT_REC = "2";

    /**
     * 3: 明細&入出金レコード(DETAIL_ORDER_REC)
     */
    public final static String DETAIL_ORDER_REC = "3";


}
@
