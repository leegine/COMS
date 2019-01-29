head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.34.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3BondExecRefUnitKeyItemDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会行キー項目定義インタフェイス(WEB3BondExecRefUnitKeyItemDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25 黄建(中訊) 新規作成
Revesion History : 2007/07/10 劉立峰(中訊) 仕様変更モデルNo.100
Revesion History : 2007/09/26 武波(中訊) モデルNo.108
*/

package webbroker3.adminorderexecinquiry.define;

/**
 * 債券管理者注文約定照会行キー項目 定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0 
 */
public interface WEB3BondExecRefUnitKeyItemDivDef
{
    /**
     * 顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";
    
    /**
     * 銘柄コード（WEB3）
     */
    public static final String PRODUCT_CODE = "productCode";
    
    /**
     * 注文種別
     */
    public static final String TRADING_TYPE = "tradingType";
    
    /**
     * 決済区分
     */
    public static final String SETTLE_DIV = "settleDiv";
    
    /**
     * 受注日時
     */
    public static final String ACCEPT_ORDER_TIMESTAMP = "acceptOrderTimeStamp";
    /**
     * 発注日
     */
    public static final String ORDER_BIZ_DATE = "orderBizDate";
    
    /**
     * 約定日
     */
    public static final String DOMESTIC_EXECUTION_DATE = "domesticExecutionDate";
    
    /**
     * 現地約定日
     */
    public static final String FOREIGN_EXECUTION_DATE = "foreignExecutionDate";
    
    /**
     * 受渡日
     */
    public static final String DOMESTIC_DELIVERY_DATE = "domesticDeliveryDate";
    
    /**
     * 現地受渡日
     */
    public static final String FOREIGN_DELIVERY_DATE = "foreignDeliveryDate";

    /**
     * 部店コード
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * 扱者コード(SONAR)
     */
    public static final String SONAR_TRADER_CODE = "sonarTraderCode";
}
@
