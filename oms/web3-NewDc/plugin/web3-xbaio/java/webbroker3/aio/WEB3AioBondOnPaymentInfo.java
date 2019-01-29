head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioBondOnPaymentInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券出金情報(WEB3AioBondOnPaymentInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 徐宏偉 (中訊) 新規作成
Revesion History : 2007/03/12 何文敏 (中訊)  モデルNo.710
*/
package webbroker3.aio;

import java.util.Date;

import webbroker3.util.WEB3LogUtility;

/**
 * (債券出金情報)<BR>
 * 債券出金情報<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioBondOnPaymentInfo
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AioBondOnPaymentInfo.class);

    /**
     * 口座ID
     */
    private Long accountId;

    /**
     * 部店ID
     */
    private Long branchId;

    /**
     * 受渡日
     */
    private Date deliveryDate;

    /**
     * 通貨コード
     */
    private String currencyCode;

    /**
     * 決済区分
     */
    private String settlementDiv;

    /**
     * 受渡代金（円貨）
     */
    private Double estimatedPrice;

    /**
     * 受渡代金（外貨）
     */
    private Double foreignEstimatedPrice;
   
    /**
     * (債券注文単位ID)<BR>
     * 債券注文単位ID<BR>
     */
    private Long bondOrderUnitId;

    /**
     * デフォルトコンストラクタ
     */
    public WEB3AioBondOnPaymentInfo()
    {

    }

    /**
     * (set口座ID)<BR>
     * 口座IDをセットする。<BR>
     * <BR>
     * @@param l_accountId - 口座ID
     */
    public void setAccountId(Long l_accountId)
    {
        final String STR_METHOD_NAME = "setAccountId(Long l_accountId)";
        log.entering(STR_METHOD_NAME);

        this.accountId = l_accountId;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set部店ID)<BR>
     * 部店IDをセットする。<BR>
     * <BR>
     * @@param l_branchId - 部店ID
     */
    public void setBranchId(Long l_branchId)
    {
        final String STR_METHOD_NAME = "setBranchId(Long l_branchId)";
        log.entering(STR_METHOD_NAME);

        this.branchId = l_branchId;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。<BR>
     * <BR>
     * @@param l_datDeliveryDate - 受渡日
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "setDeliveryDate(Date l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);

        this.deliveryDate = l_datDeliveryDate;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set通貨コード)<BR>
     * 通貨コードをセットする。<BR>
     * <BR>
     * @@param l_strCurrencyCode - 通貨コード
     */
    public void setCurrencyCode(String l_strCurrencyCode)
    {
        final String STR_METHOD_NAME = "setCurrencyCode(String l_strCurrencyCode)";
        log.entering(STR_METHOD_NAME);

        this.currencyCode = l_strCurrencyCode;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set決済区分)<BR>
     * 決済区分をセットする。<BR>
     * <BR>
     * @@param l_strSettlementDiv - 決済区分
     */
    public void setSettlementDiv(String l_strSettlementDiv)
    {
        final String STR_METHOD_NAME = "setSettlementDiv(String l_strSettlementDiv)";
        log.entering(STR_METHOD_NAME);

        this.settlementDiv = l_strSettlementDiv;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set受渡代金（円貨）)<BR>
     * 受渡代金（円貨）をセットする。<BR>
     * <BR>
     * @@param l_estimatedPrice - 受渡代金（円貨）
     */
    public void setEstimatedPrice(Double l_estimatedPrice)
    {
        final String STR_METHOD_NAME = "setEstimatedPrice(Double l_estimatedPrice)";
        log.entering(STR_METHOD_NAME);

        this.estimatedPrice = l_estimatedPrice;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set受渡代金（外貨）)<BR>
     * 受渡代金（外貨）をセットする。<BR>
     * <BR>
     * @@param l_foreignEstimatedPrice - 受渡代金（外貨）
     */
    public void setForeignEstimatedPrice(Double l_foreignEstimatedPrice)
    {
        final String STR_METHOD_NAME = "setForeignEstimatedPrice(Double)";
        log.entering(STR_METHOD_NAME);

        this.foreignEstimatedPrice = l_foreignEstimatedPrice;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set債券注文単位ID)<BR>
     * 債券注文単位IDをセットする。<BR>
     * <BR>
     * @@param l_lngBondOrderUnitIds - (債券注文単位ID)<BR>
     * 債券注文単位ID
     */
    public void setBondOrderUnitId(Long l_lngBondOrderUnitIds)
    {
        final String STR_METHOD_NAME = "setBondOrderUnitId(Long l_lngBondOrderUnitIds)";
        log.entering(STR_METHOD_NAME);

        this.bondOrderUnitId = l_lngBondOrderUnitIds;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get口座ID ())<BR>
     * 口座IDを返却する。<BR>
     * <BR>
     * @@return Long
     */
    public Long getAccountId()
    {
        return this.accountId;
    }

    /**
     * (get部店ID)<BR>
     * 部店IDを返却する。<BR>
     * <BR>
     * @@return Long
     */
    public Long getBranchId()
    {
        return this.branchId;
    }

    /**
     * (get受渡日)<BR>
     * 受渡日を返却する。<BR>
     * <BR>
     * @@return Date
     */
    public Date getDeliveryDate()
    {
        return this.deliveryDate;
    }

    /**
     * (get通貨コード)<BR>
     * 通貨コードを返却する。<BR>
     * <BR>
     * @@return String
     */
    public String getCurrencyCode()
    {
        return this.currencyCode;
    }

    /**
     * (get決済区分)<BR>
     * 決済区分を返却する。<BR>
     * <BR>
     * @@return String
     */
    public String getSettlementDiv()
    {
        return this.settlementDiv;
    }

    /**
     * (get受渡代金（円貨）)<BR>
     * 受渡代金（円貨）を返却する。<BR>
     * <BR>
     * @@return Double
     */
    public Double getEstimatedPrice()
    {
        return this.estimatedPrice;
    }

    /**
     * (get受渡代金（外貨）)<BR>
     * 受渡代金（外貨）を返却する。<BR>
     * <BR>
     * @@return Double
     */
    public Double getForeignEstimatedPrice()
    {
        return this.foreignEstimatedPrice;
    }

    /**
     * (get債券注文単位ID)<BR>
     * 債券注文単位IDを返却する。<BR>
     * <BR>
     * @@return Long
     */
    public Long getBondOrderUnitId()
    {
        return this.bondOrderUnitId;
    }
}
@
