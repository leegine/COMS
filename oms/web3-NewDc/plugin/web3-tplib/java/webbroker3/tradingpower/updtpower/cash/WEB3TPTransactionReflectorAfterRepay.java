head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionReflectorAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返済後取引情報(WEB3TPTransactionReflectorAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) 新規作成
                   2006/09/15 車進　@  (中訊)モデルNo.29
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.util.WEB3LogUtility;

/**
 * (返済後取引情報)
 */
public class WEB3TPTransactionReflectorAfterRepay extends WEB3TPTransactionReflector
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTransactionReflectorAfterRepay.class);

    /**
     * (未約定分返済決済損益)
     */
    private double unexecutedRepaySettleProfitLoss;

    /**
     * (今回発注分返済決済損益)
     */
    private double currOrderRepaySettleProfitLoss;

    /**
     * @@roseuid 41E383D6022C
     */
    public WEB3TPTransactionReflectorAfterRepay()
    {

    }

    /**
     * (static)(create返済後取引情報)<BR>
     * <BR>
     * 返済後取引情報を作成し、返却する。<BR>
     * <BR>
     * 1)返済後取引情報インスタンス(="返済後取引情報")を生成する。<BR> 
     * 　@-デフォルトコンストラクタをコール <BR>
     * <BR>
     * 2)生成した返済後取引情報インスタンスの属性に値をセット<BR> 
     * <BR>
     * 　@－"返済後取引情報".set余力計算条件(:余力計算条件 = 引数.余力計算条件)<BR> 
     * 　@－"返済後取引情報".set銘柄タイプ(:ProductionType = 引数.銘柄タイプ)<BR>
     * 　@－"返済後取引情報".set銘柄ID(:long = 引数.銘柄ID)<BR>
     * 　@－"返済後取引情報".setトランザクションタイプ(:FinTransactionType = 引数.トランザクションタイプ)<BR> 
     * 　@－"返済後取引情報".setトランザクション発生日(:Date = 引数.トランザクション発生日)<BR>
     * 　@－"返済後取引情報".set受渡日(:Date = 引数.受渡日)<BR>
     * 　@－"返済後取引情報".set未約定数量(:double = 引数.未約定数量)<BR> 
     * 　@－"返済後取引情報".set未約定代金(:double = 引数.未約定代金)<BR>
     * 　@－"返済後取引情報".set約定済数量(:double = 引数.約定済数量)<BR>
     * 　@－"返済後取引情報".set約定済代金(:double = 引数.約定済代金)<BR>
     * 　@－"返済後取引情報".set税区分(:TaxTypeEnum = 引数.税区分)<BR>
     * 　@－"返済後取引情報".calc変動反映日(:Date = 引数.受渡日)<BR>
     * <BR>
     * 　@－"返済後取引情報".set未約定返済決済損益(:double = 引数.未約定返済決済損益)<BR> 
     * 　@－"返済後取引情報".set今回発注分返済決済損益(:double = 引数.今回発注分返済決済損益)<BR> 
     * <BR>
     * 3)返済後取引情報インスタンスを返却する。<BR> 
     * <BR>
     * <BR>
     * @@param l_calcCondition - (余力計算条件)
     * @@param l_productType - (銘柄タイプ)
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_finTransactionType - (トランザクションタイプ)
     * @@param l_finTransactionDate - (トランザクション発生日)
     * @@param l_dblUnexecutedQuantity - (未約定数量)
     * @@param l_dblUnexecutedAmount - (未約定代金)
     * @@param l_dblExecutedQuantity - (約定済数量)
     * @@param l_dblExecutedAmount - (約定済代金)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_taxType - (税区分)
     * @@param l_dblUnexecutedRepaySettleProfitLoss - (未約定返済決済損益)
     * @@param l_dblCurrOrderRepaySettleProfitLoss - (今回発注分返済決済損益)
     * @@return 
     * webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflectorAfterRepay
     * @@roseuid 41C95EA00020
     */
    public static WEB3TPTransactionReflectorAfterRepay createWEB3TPTransactionReflectorAftreRepay(
        WEB3TPCalcCondition l_calcCondition,
        ProductTypeEnum l_productType,
        long l_lngProductId,
        FinTransactionType l_finTransactionType,
        Date l_finTransactionDate,
        double l_dblUnexecutedQuantity,
        double l_dblUnexecutedAmount,
        double l_dblExecutedQuantity,
        double l_dblExecutedAmount,
        Date l_datDeliveryDate,
        TaxTypeEnum l_taxType,
        double l_dblUnexecutedRepaySettleProfitLoss,
        double l_dblCurrOrderRepaySettleProfitLoss)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPTransactionReflectorAftreRepay(WEB3TPCalcCondition, ProductTypeEnum, long, FinTransactionType, Date, double, double, double, double, Date, double, double)";
        log.entering(STR_METHOD_NAME);

        WEB3TPTransactionReflectorAfterRepay l_instance =
            new WEB3TPTransactionReflectorAfterRepay();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setProductType(l_productType);
        l_instance.setProductId(l_lngProductId);
        l_instance.setFinTransactionType(l_finTransactionType);
        l_instance.setFinTransactionDate(l_finTransactionDate);
        l_instance.setUnexecutedQuantity(l_dblUnexecutedQuantity);
        l_instance.setUnexecutedAmount(l_dblUnexecutedAmount);
        l_instance.setExecutedQuantity(l_dblExecutedQuantity);
        l_instance.setExecutedAmount(l_dblExecutedAmount);
        l_instance.setDeliveryDate(l_datDeliveryDate);
        l_instance.setTaxType(l_taxType);
        l_instance.calcReflectDay(l_datDeliveryDate);

        l_instance.setUnexecutedRepaySettleProfitLoss(l_dblUnexecutedRepaySettleProfitLoss);
        l_instance.setCurrOrderRepaySettleProfitLoss(l_dblCurrOrderRepaySettleProfitLoss);

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (get未約定分返済決済損益)
     * @@return double
     * @@roseuid 41C94E6E00FB
     */
    public double getUnexecutedRepaySettleProfitLoss()
    {
        final String STR_METHOD_NAME = "getUnexecutedRepaySettleProfitLoss()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.unexecutedRepaySettleProfitLoss;
    }

    /**
     * (set未約定分返済決済損益)
     * @@param l_dblUnexecutedRepaySettleProfitLoss - (未約定分返済決済損益)
     * @@roseuid 41C94E8302E0
     */
    public void setUnexecutedRepaySettleProfitLoss(double l_dblUnexecutedRepaySettleProfitLoss)
    {
        final String STR_METHOD_NAME = "setUnexecutedRepaySettleProfitLoss(double)";
        log.entering(STR_METHOD_NAME);

        this.unexecutedRepaySettleProfitLoss = l_dblUnexecutedRepaySettleProfitLoss;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get今回発注分返済決済損益)
     * @@return double
     * @@roseuid 41C94E83039B
     */
    public double getCurrOrderRepaySettleProfitLoss()
    {
        final String STR_METHOD_NAME = "getCurrOrderRepaySettleProfitLoss()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.currOrderRepaySettleProfitLoss;
    }

    /**
     * (set今回発注分返済決済損益)
     * @@param l_dblCurrOrderRepaySettleProfitLoss - (今回返済分返済決済損益)
     * @@roseuid 41C94E84008E
     */
    public void setCurrOrderRepaySettleProfitLoss(double l_dblCurrOrderRepaySettleProfitLoss)
    {
        final String STR_METHOD_NAME = "setCurrOrderRepaySettleProfitLoss(double)";
        log.entering(STR_METHOD_NAME);

        this.currOrderRepaySettleProfitLoss = l_dblCurrOrderRepaySettleProfitLoss;

        log.exiting(STR_METHOD_NAME);
    }
}
@
