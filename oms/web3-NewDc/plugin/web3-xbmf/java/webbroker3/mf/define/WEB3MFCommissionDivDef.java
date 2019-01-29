head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFCommissionDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料区分 定数定義インタフェイス(WEB3MFDealDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 黄建(中訊)　@新規作成
*/

package webbroker3.mf.define;

/**
 * 手数料区分 定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0
 */

public class WEB3MFCommissionDivDef
{
    /**
     * 0:単位数量当たり手数料単価 
     */
    public static final String UNIT_COUNT_COMMISSION_PRICE = "0";

    /**
     * 1:限度数量毎の手数料率
     */
    public static final String UNIT_ONE_COMMISSION_RATE = "1";
    
    /**
     * 2:受渡代金（手数料率）
     */
    public static final String PAYMENT_PRICE_COMMISSION_RATE = "2";
    
    /**
     * 3:売買代金（手数料率）
     */
    public static final String TRADE_PRICE_COMMISSION_RATE = "3";  
}
@
