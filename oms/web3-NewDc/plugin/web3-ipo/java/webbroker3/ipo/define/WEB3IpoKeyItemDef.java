head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目定義インタフェイス(WEB3IpoKeyItemDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10  qi-lin(sinocom)　@新規作成
*/
package webbroker3.ipo.define;

/**
 * キー項目
 *                                                                     
 * @@author qi-lin
 * @@version 1.0
 */
public interface WEB3IpoKeyItemDef
{

    /**
     * branchCode : 部店コード
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * accountCode : 顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * accountName : 顧客名
     */
    public static final String ACCOUNT_NAME = "accountName";

    /**
     * lotResultDiv : 抽選結果区分
     */
    public static final String LOT_RESULT_DIV = "lotResultDiv";

    /**
     * prizeQuantity : 当選数量
     */
    public static final String PRIZE_QUANTITY = "prizeQuantity";
        
    /**
     * offerQuantity : 購入申込数量
     */
    public static final String OFFER_QUANTITY = "offerQuantity";

    /**
     * offerCancelDate : 購入申込辞退日時
     */
    public static final String OFFER_CANCEL_DATE = "offerCancelDate";

    /**
     * offerStateDiv : 購入申込状況区分
     */
    public static final String OFFER_STATE_DIV = "offerStateDiv";
    
    /**
     * receiveStateDiv : 受付状態区分
     */
    public static final String RECEIVE_STATE_DIV = "receiveStateDiv";
    
    /**
     * priority : 優先順位
     */
    public static final String PRIORITY = "priority";
    
}@
