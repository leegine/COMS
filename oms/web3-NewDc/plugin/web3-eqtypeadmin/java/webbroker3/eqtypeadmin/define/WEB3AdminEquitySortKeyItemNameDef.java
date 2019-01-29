head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquitySortKeyItemNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目の項目名 定数定義インタフェイス(WEB3AdminEquitySortKeyItemNameDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 柴双紅 (中訊) 新規作成
Revesion History : 2007/09/11 柴双紅 (中訊) モデルNo.164
Revesion History : 2008/12/31 李キョウ (中訊) モデルNo.216
*/
package webbroker3.eqtypeadmin.define;

/**
 * キー項目の項目名 定義インタフェイス
 *
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminEquitySortKeyItemNameDef
{
    /**
     * 部店コード
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * 顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * 強制決済理由
     */
    public static final String FORCED_SETTLE_REASON = "forcedSettleReason";

    /**
     * 市場コード
     */
    public static final String MARKET_CODE = "marketCode";

    /**
     * 銘柄コード
     */
    public static final String PRODUCT_CODE = "productCode";

    /**
     * 口座区分
     */
    public static final String TAX_TYPE = "taxType";

    /**
     * 建区分
     */
    public static final String CONTRACT_TYPE = "contractType";

    /**
     * 弁済区分
     */
    public static final String REPAYMENT_DIV = "repaymentDiv";

    /**
     * 弁済期限値
     */
    public static final String REPAYMENTTIME_LIMIT = "repaymentTimeLimit";

    /**
     * 建日
     */
    public static final String OPEN_DATE = "openDate";

    /**
     * 決済期日
     */
    public static final String CLOSE_DATE = "closeDate";

    /**
     * 作成日時
     */
    public static final String CREATE_DATE = "createDate";

    /**
     * （非）承認日時
     */
    public static final String APPROVE_DATE = "approveDate";

    /**
     * 建株数
     */
    public static final String CONTRACT_QUANTITY = "contractQuantity";

    /**
     * 建単価
     */
    public static final String CONTRACT_PRICE = "contractPrice";

    /**
     * 建代金
     */
    public static final String CONTRACT_EXEC_PRICE = "contractExecPrice";

    /**
     * 注文株数
     */
    public static final String ORDER_QUANTITY = "orderQuantity";

    /**
     * 保証金預託率
     */
    public static final String MARGIN_COLLATERAL_RATE = "marginCollateralRate";

    /**
     * 承認状態
     */
    public static final String APPROVE_STATE = "approveState";

    /**
     * 注意情報種別
     */
    public static final String ATTENTION_INFO_TYPE = "attentionInfoType";

    /**
     * 注意情報区分コード
     */
    public static final String ATTENTION_INFO_DIV_CODE = "attentionInfoDivCode";

    /**
     * 情報発生日時
     */
    public static final String INFO_OCCURED_DATE = "infoOccuredDate";

    /**
     * 銘柄ＩＤ
     */
    public static final String PRODUCT_ID = "productId";

    /**
     * 市場ＩＤ
     */
    public static final String MARKET_ID = "marketId";

}
@
