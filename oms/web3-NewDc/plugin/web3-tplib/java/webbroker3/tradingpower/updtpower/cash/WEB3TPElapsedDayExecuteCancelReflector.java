head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPElapsedDayExecuteCancelReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 過日約定取消情報(WEB3TPElapsedDayExecuteCancelReflector.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/25 車進  (中訊)  新規作成
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (過日約定取消情報)<BR>
 * <BR>
 * 過日約定取消注文のうち、確定預り金に<BR>
 * 未反映である取引情報を格納するクラス<BR>
 * 
 * @@author 車進(中訊)
 * @@version 1.0
 */
public class WEB3TPElapsedDayExecuteCancelReflector extends WEB3TPAssetReflector
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPElapsedDayExecuteCancelReflector.class);

    /**
     * (銘柄タイプ)<BR>
     */
    private ProductTypeEnum productType;

    /**
     * (銘柄ID)<BR>
     */
    private long productId;

    /**
     * (トランザクションタイプ)<BR>
     */
    private FinTransactionType finTransactionType;

    /**
     * (受渡日)<BR>
     */
    private Date deliveryDate;

    /**
     * (トランザクション発生日)<BR>
     */
    private Date finTransactionDate;

    /**
     * (取消数量)<BR>
     */
    private double cancelQuantity;

    /**
     * (取消代金)<BR>
     */
    private double cancelAmount;

    /**
     * (税区分)<BR>
     */
    private TaxTypeEnum taxType;

    /**
     * (過日約定取消情報)<BR>
     * (デフォルトコンストラクタ)<BR>
     * <BR>
     * @@roseuid 45052F0C0169
     */
    public WEB3TPElapsedDayExecuteCancelReflector()
    {

    }

    /**
     * (static)(create過日約定取消情報)<BR>
     * <BR>
     * 過日約定取消情報を作成し、返却する。<BR>
     * <BR>
     * 1)過日約定取消情報インスタンス(="過日約定取消情報")を生成する。<BR>
     * 　@-デフォルトコンストラクタをコール<BR>
     * <BR>
     * 2)生成した過日約定取消情報インスタンスの属性に値をセット<BR>
     * <BR>
     * 　@－"過日約定取消情報".set余力計算条件(:余力計算条件 = 引数.余力計算条件)<BR>
     * 　@－"過日約定取消情報".set銘柄タイプ(:ProductionType = 引数.銘柄タイプ)<BR>
     * 　@－"過日約定取消情報".set銘柄ID(:long = 引数.銘柄ID)<BR>
     * 　@－"過日約定取消情報".setトランザクションタイプ(:FinTransactionType = 引数.トランザクションタイプ)<BR>
     * 　@－"過日約定取消情報".setトランザクション発生日(:Date = 引数.トランザクション発生日)<BR>
     * 　@－"過日約定取消情報".set取消数量(:double = 引数.取消数量)<BR>
     * 　@－"過日約定取消情報".set取消代金(:double = 引数.取消代金)<BR>
     * 　@－"過日約定取消情報".set受渡日(:Date = 引数.受渡日)<BR>
     * 　@－"過日約定取消情報".calc変動反映日(:Date = 引数.受渡日)<BR>
     * 　@－"過日約定取消情報".set税区分(:TaxTypeEnum = 引数.税区分)<BR>
     * <BR>
     * 3)過日約定取消情報インスタンスを返却する。<BR>
     * <BR>
     * @@param l_calcCondition - (余力計算条件)
     * @@param l_productType - (銘柄タイプ)
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_finTransactionType - (トランザクションタイプ)
     * @@param l_finTransactionDate - (トランザクション発生日)
     * @@param l_dblCancelQuantity - (取消数量)
     * @@param l_dblCancelAmount - (取消代金)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_taxType - (税区分)
     * @@return WEB3TPElapsedDayExecuteCancelReflector
     * @@roseuid 45052F0C0188
     */
    public static WEB3TPElapsedDayExecuteCancelReflector createWEB3TPElapsedDayExecuteCancelReflector(
        WEB3TPCalcCondition l_calcCondition, 
        ProductTypeEnum l_productType, 
        long l_lngProductId, 
        FinTransactionType l_finTransactionType, 
        Date l_finTransactionDate, 
        double l_dblCancelQuantity, 
        double l_dblCancelAmount, 
        Date l_datDeliveryDate, 
        TaxTypeEnum l_taxType) 
    {
        final String STR_METHOD_NAME =
            "createWEB3TPElapsedDayExecuteCancelReflector(WEB3TPCalcCondition, " + 
            "ProductTypeEnum, long, FinTransactionType, Date, double, double, Date, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //1)過日約定取消情報インスタンス(="過日約定取消情報")を生成する。
        WEB3TPElapsedDayExecuteCancelReflector l_instance = 
            new WEB3TPElapsedDayExecuteCancelReflector();

        //2)生成した過日約定取消情報インスタンスの属性に値をセット
        //"過日約定取消情報".set余力計算条件(:余力計算条件 = 引数.余力計算条件)
        l_instance.setCalcCondition(l_calcCondition);
        //"過日約定取消情報".set銘柄タイプ(:ProductionType = 引数.銘柄タイプ)
        l_instance.setProductType(l_productType);
        //"過日約定取消情報".set銘柄ID(:long = 引数.銘柄ID)
        l_instance.setProductId(l_lngProductId);
        //"過日約定取消情報".setトランザクションタイプ(:FinTransactionType = 引数.トランザクションタイプ)
        l_instance.setTransactionType(l_finTransactionType);
        //"過日約定取消情報".setトランザクション発生日(:Date = 引数.トランザクション発生日)
        l_instance.setTransactionDate(l_finTransactionDate);
        //"過日約定取消情報".set取消数量(:double = 引数.取消数量)
        l_instance.setCancelQuantity(l_dblCancelQuantity);
        //"過日約定取消情報".set取消代金(:double = 引数.取消代金)
        l_instance.setCancelAmount(l_dblCancelAmount);
        //"過日約定取消情報".set受渡日(:Date = 引数.受渡日)
        l_instance.setDeliveryDate(l_datDeliveryDate);
        //"過日約定取消情報".calc変動反映日(:Date = 引数.受渡日)
        l_instance.calcReflectDay(l_datDeliveryDate);
        //"過日約定取消情報".set税区分(:TaxTypeEnum = 引数.税区分)
        l_instance.setTaxType(l_taxType);

        //3)過日約定取消情報インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (get銘柄タイプ)<BR>
     * <BR>
     * this.銘柄タイプを返却する。<BR>
     * <BR>
     * @@return ProductTypeEnum
     * @@roseuid 45052F0C01A8
     */
    public ProductTypeEnum getProductType()
    {
        final String STR_METHOD_NAME = "getProductType()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.productType;
    }

    /**
     * (set銘柄タイプ)<BR>
     * <BR>
     * 引数.銘柄タイプを、this.銘柄タイプにセットする。<BR>
     * <BR>
     * @@param l_productType - (銘柄タイプ)
     * @@roseuid 45052F0C01B7
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        final String STR_METHOD_NAME = "setProductType(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        this.productType = l_productType;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get銘柄ID)<BR>
     * <BR>
     * this.銘柄IDを返却する。<BR>
     * <BR>
     * @@return long
     * @@roseuid 45052F0C01D6
     */
    public long getProductId()
    {
        final String STR_METHOD_NAME = "getProductId()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.productId;
    }

    /**
     * (set銘柄ID)<BR>
     * <BR>
     * 引数.銘柄IDを、this.銘柄IDにセットする。<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@roseuid 45052F0C01F6
     */
    public void setProductId(long l_lngProductId)
    {
        final String STR_METHOD_NAME = "setProductId(long)";
        log.entering(STR_METHOD_NAME);

        this.productId = l_lngProductId;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getトランザクションタイプ)<BR>
     * <BR>
     * this.トランザクションタイプを返却する。<BR>
     * <BR>
     * @@return FinTransactionType
     * @@roseuid 45052F0C0215
     */
    public FinTransactionType getTransactionType()
    {
        final String STR_METHOD_NAME = "getTransactionType()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.finTransactionType;
    }

    /**
     * (setトランザクションタイプ)<BR>
     * <BR>
     * 引数.トランザクションタイプを、this.トランザクションタイプにセットする。<BR>
     * <BR>
     * @@param l_finTransactionType - (トランザクションタイプ)
     * @@roseuid 45052F0C0234
     */
    public void setTransactionType(FinTransactionType l_finTransactionType)
    {
        final String STR_METHOD_NAME = "setTransactionType(FinTransactionType)";
        log.entering(STR_METHOD_NAME);

        this.finTransactionType = l_finTransactionType;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getトランザクション発生日)<BR>
     * <BR>
     * this.トランザクション発生日を返却する。<BR>
     * <BR>
     * @@return Date
     * @@roseuid 45052F0C0253
     */
    public Date getTransactionDate()
    {
        final String STR_METHOD_NAME = "getTransactionDate()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.finTransactionDate;
    }

    /**
     * (setトランザクション発生日)<BR>
     * <BR>
     * 引数.トランザクション発生日を、this.トランザクション発生日にセットする。<BR>
     * <BR>
     * @@param l_finTransactionDate - (トランザクション発生日)<BR>
     * <BR>
     * @@roseuid 45052F0C0273
     */
    public void setTransactionDate(Date l_finTransactionDate)
    {
        final String STR_METHOD_NAME = "setTransactionDate(Date)";
        log.entering(STR_METHOD_NAME);

        this.finTransactionDate = l_finTransactionDate;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get受渡日)<BR>
     * <BR>
     * this.受渡日を返却する。<BR>
     * <BR>
     * @@return Date
     * @@roseuid 45052F0C0292
     */
    public Date getDeliveryDate()
    {
        final String STR_METHOD_NAME = "getDeliveryDate()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.deliveryDate;
    }

    /**
     * (set受渡日)<BR>
     * <BR>
     * 引数.受渡日を、this.受渡日にセットする。<BR>
     * <BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * @@roseuid 45052F0C02A2
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "setDeliveryDate(Date)";
        log.entering(STR_METHOD_NAME);

        this.deliveryDate = l_datDeliveryDate;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get取消数量)<BR>
     * <BR>
     * this.取消数量を返却する。<BR>
     * <BR>
     * @@return double
     * @@roseuid 45052F0C02C1
     */
    public double getCancelQuantity()
    {
        final String STR_METHOD_NAME = "getCancelQuantity()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.cancelQuantity;
    }

    /**
     * (set取消数量)<BR>
     * <BR>
     * 引数.取消数量を、this.取消数量にセットする。<BR>
     * <BR>
     * @@param l_dblCancelQuantity - (取消数量)<BR>
     * @@roseuid 45052F0C02F0
     */
    public void setCancelQuantity(double l_dblCancelQuantity)
    {
        final String STR_METHOD_NAME = "setCancelQuantity(double)";
        log.entering(STR_METHOD_NAME);

        this.cancelQuantity = l_dblCancelQuantity;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get取消代金)<BR>
     * <BR>
     * this.取消代金を返却する。<BR>
     * <BR>
     * @@return double
     * @@roseuid 45052F0C030F
     */
    public double getCancelAmount()
    {
        final String STR_METHOD_NAME = "getCancelAmount()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.cancelAmount;
    }

    /**
     * (set取消代金)<BR>
     * <BR>
     * 引数.取消代金を、this.取消代金にセットする。<BR>
     * @@param l_dblCancelAmount - (取消代金)<BR>
     * @@roseuid 45052F0C036D
     */
    public void setCancelAmount(double l_dblCancelAmount)
    {
        final String STR_METHOD_NAME = "setCancelAmount(double)";
        log.entering(STR_METHOD_NAME);

        this.cancelAmount = l_dblCancelAmount;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get税区分)<BR>
     * <BR>
     * this.税区分を返却する。<BR>
     * @@return TaxTypeEnum
     * @@roseuid 45052F0D0040
     */
    public TaxTypeEnum getTaxType()
    {
        final String STR_METHOD_NAME = "getTaxType()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.taxType;
    }

    /**
     * (set税区分)<BR>
     * <BR>
     * 引数.税区分を、this.税区分にセットする。<BR>
     * <BR>
     * @@param l_taxType - (税区分)<BR>
     * <BR>
     * <BR>
     * @@roseuid 45052F0D005F
     */
    public void setTaxType(TaxTypeEnum l_taxType)
    {
        final String STR_METHOD_NAME = "setTaxType(TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        this.taxType = l_taxType;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc変動反映日)<BR>
     * <BR>
     * 変動反映開始日、変動反映終了日をセットする。<BR>
     * <BR>
     * １）変動反映開始日をセットする。<BR>
     * <BR>
     * 　@[a.T+5 > 引数.受渡日の場合]<BR>
     * 　@　@－this.set変動反映開始日(:Date = 引数.受渡日)<BR>
     * 　@<BR>
     * 　@[a.以外 の場合]<BR>
     * 　@　@－this.set変動反映開始日(:Date = T+5)<BR>
     * <BR>
     * ２）変動反映終了日をセットする。<BR>
     * 　@　@－this.set変動反映終了日(:Date = T+5)<BR>
     * <BR>
     * ※T+5 = this.get余力計算条件().get営業日(:int = 5)<BR>
     * <BR>
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 45052F0D00AE
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        //T+5 > 引数.受渡日の場合
        //set変動反映開始日(:Date = 引数.受渡日)
        if (WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) > 0)
        {
            setReflectStartDay(l_datDeliveryDate);
        }
        //以外 の場合 set変動反映開始日(:Date = T+5)
        else
        {
            setReflectStartDay(l_datT5);
        }

        //変動反映終了日をセットする。
        setReflectEndDay(l_datT5);
    }
}@
