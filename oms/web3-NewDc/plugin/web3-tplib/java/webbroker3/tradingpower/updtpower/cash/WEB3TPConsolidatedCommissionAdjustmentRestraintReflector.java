head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 堀野 和美(FLJ) 新規作成
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (手数料一口処理拘束金)<BR>
 * 手数料一口処理拘束金を表現する。
 */
public class WEB3TPConsolidatedCommissionAdjustmentRestraintReflector
    extends WEB3TPRestraintReflector
{

    /**
     * (手数料商品コード)<BR>
     */
    private String productCode;

    /**
     * (手数料一口処理調整額)<BR>
     */
    private double adjustment;

    /** ログ　@ユーティリティ　@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.class);


    /**
     * @@roseuid 4104915803C7
     */
    public WEB3TPConsolidatedCommissionAdjustmentRestraintReflector()
    {

    }

    /**
     * (create手数料一口処理拘束金)<BR>
     * 手数料一口処理拘束金を生成し、返却する。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * <BR>
     * ２）　@値を設定<BR>
     * 	手数料商品コード＝手数料商品コード<BR>
     * 	手数料一口処理調整額＝手数料一口処理調整額<BR>
     *     変動反映開始日、変動反映終了日設定：calc変動反映日(受渡日)<BR>
     * <BR>
     * ３）　@インスタンスを返却<BR>
     * @@param l_strProductCode - (手数料一口商品コード)
     * @@param l_dblAdjustment - (手数料一口調整額)
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 40D8D8C300FC
     */
    public static WEB3TPConsolidatedCommissionAdjustmentRestraintReflector
        create(WEB3TPCalcCondition l_calcCondition, String l_strProductCode,
               double l_dblAdjustment, Date l_datDeliveryDate)
    {
        WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_instance =
            new WEB3TPConsolidatedCommissionAdjustmentRestraintReflector();

        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setProductCode(l_strProductCode);
        l_instance.setAdjustment(l_dblAdjustment);
        l_instance.setAmount(l_dblAdjustment);
        l_instance.calcReflectDay(l_datDeliveryDate);

        return l_instance;

    }

    /**
     * (get手数料商品コード)<BR>
     * 手数料一口商品コードを返す。<BR>
     * @@return String
     * @@roseuid 40E014C5037C
     */
    public String getProductCode()
    {
        return productCode;
    }

    /**
     * (set手数料商品コード)<BR>
     * 引数を手数料一口商品コードにセットする。<BR>
     * @@param l_strProductCode - (手数料商品コード)
     * @@roseuid 40E014C900FC
     */
    public void setProductCode(String l_strProductCode)
    {
        productCode = l_strProductCode;
    }

    /**
     * (get手数料一口処理調整額)<BR>
     * 手数料一口処理調整額を返す。<BR>
     * @@return double
     * @@roseuid 40F75F7B0325
     */
    public double getAdjustment()
    {
        return adjustment;
    }

    /**
     * (set手数料一口処理調整額)<BR>
     * 引数を手数料一口処理調整額にセットする。<BR>
     * @@param l_dblAdjustment - (手数料一口処理調整額)
     * @@roseuid 40F75F8301BE
     */
    public void setAdjustment(double l_dblAdjustment)
    {
        adjustment = l_dblAdjustment;
    }

    /**
     * (calc変動反映日)<BR>
     * 変動反映開始日、変動反映終了日を以下のようにセットする。<BR>
     * <BR>
     *  変動反映開始日＝引数.受渡日<BR>
     *  変動反映終了日＝営業日[5]<BR>
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 40ECA13803A8
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";

        if (l_datDeliveryDate == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        setReflectStartDay(l_datDeliveryDate);
        setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));
    }

    /**
     * このオブジェクトの文字列表現を返す。<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("productCode", this.getProductCode())
            .append("adjustment", this.getAdjustment())
            .appendSuper(super.toString())
            .toString();
    }
}
@
