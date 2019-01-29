head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteReferenceDetailUnitDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文約定照会明細 定数定義インタフェイス(WEB3BondExecuteReferenceDetailUnitDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 周捷 (中訊) 新規作成
*/
package webbroker3.bd.define;

/**
 * 債券注文約定照会明細 定数定義インタフェイス<BR>
 * 
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3BondExecuteReferenceDetailUnitDef
{
    /**
     * 銘柄名
     */
    public static final String PRODUCT_NAME = "productName";
    
    /**
     * 取引区分
     */
    public static final String STATE_DIV = "stateDiv";
    
    /**
     * 決済区分
     */
    public static final String SETTLE_DIV = "settleDiv";
    
    /**
     * 注文日時
     */
    public static final String ORDER_DATE = "orderDate";
    
    /**
     * 注文状態
     */
    public static final String EXECUTION_STATE = "executionState";
}
@
