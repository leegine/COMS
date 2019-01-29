head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceDetailUnitDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券残高照会明細 定数定義インタフェイス(WEB3BondBalanceReferenceDetailUnitDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 周捷 (中訊) 新規作成
*/
package webbroker3.bd.define;

/**
 * 債券残高照会明細 定数定義インタフェイス<BR>
 * 
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3BondBalanceReferenceDetailUnitDef
{
    /**
     * 銘柄名
     */
    public static final String PRODUCT_NAME = "productName";
    
    /**
     * 売却可能数量
     */
    public static final String SELL_ABLE_QTY = "sellAbleQty";
    
    /**
     * 通貨
     */
    public static final String CURRENCY_CODE = "currencyCode";
    
    /**
     * 概算評価額（円貨）
     */
    public static final String YEN_ESTIMATED_ASSET = "yenEstimatedAsset";
    
    /**
     * 概算評価額（外貨）
     */
    public static final String FOREIGN_ESTIMATED_ASSET = "foreignEstimatedAsset";
    
    /**
     * 発行日
     */
    public static final String ISSUE_DATE = "issueDate";
    
    /**
     * 償還日
     */
    public static final String MATURITY_DATE = "maturityDate";
    
    /**
     * 種別
     */
    public static final String BOND_CATEG_CODE = "bondCategCode";
    
    /**
     * 銘柄コード
     */
    public static final String PRODUCT_CODE = "productCode";
    
    /**
     * 回号コード
     */
    public static final String PRODUCT_ISSUE_CODE = "productIssueCode";
}
@
