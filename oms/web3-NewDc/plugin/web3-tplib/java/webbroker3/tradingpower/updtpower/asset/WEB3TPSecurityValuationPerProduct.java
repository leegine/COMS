head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityValuationPerProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 銘柄ごと証券評価額(WEB3TPSecurityValuationPerProduct.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 山田　@卓司 (FLJ) 新規作成
                    2006/09/14 車進　@     (中訊)モデルNo.42
 Revesion History : 2007/07/28 孟亜南     (中訊)モデルNo.117
 Revesion History : 2007/07/31 孟亜南     (中訊)モデルNo.145
 Revesion History : 2007/10/16 トウ鋒鋼   (中訊)モデルNo.195
 Revesion History : 2008/01/22 孟亞南     (中訊)モデルNo.238
 Revesion History : 2008/11/21 三島淳一郎 (SCS)モデルNo.370, 371
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPExecTypeDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (銘柄ごと証券評価額)
 *
 * 銘柄ごと証券評価額の情報を表現する
 */
public class WEB3TPSecurityValuationPerProduct
    extends WEB3TPAssetValuation
{

    /**
     * ログユーティリティ
     */
    private static final Logit log =
        Logit.getInstance(WEB3TPSecurityValuationPerProduct.class);

    /**
     * (対象銘柄)
     */
    private WEB3TPSecurityValuationProduct product;

    /**
     * (銘柄ごと代用証券評価額)
     * 計算済み銘柄ごと代用証券評価額
     */
    private double[] substituteValuationPrices;

    /**
     * (銘柄ごと代用証券評価額(前日単価評価))
     */
    private double[] prevPriceSubstituteValuation;

    /**
     * (銘柄ごと預り証券評価額)
     * 計算済み銘柄ごと預り証券評価額
     */
    private double[] valuationPrices;

    /**
     * (銘柄ごと預り資産評価額)
     * 計算済み銘柄ごと預り資産評価額
     */
    private double[] assetValuationPrices;

    /**
     * (銘柄ごと発注分代用証券評価額)
     * 計算済み銘柄ごと発注分代用証券評価額
     */
    private double[] unExecSubstituteValuationPrices;

    /**
     * (銘柄ごと発注分預り証券評価額)
     * 計算済み銘柄ごと発注分預り証券評価額
     */
    private double[] unExecValuationPrices;

    /**
     * (既存保有変動)
     */
    private List assetChanges;

    /**
     * (振替変動)
     */
    private List transferChanges;

    /**
     * (出庫変動)
     */
    private List deliveryChanges;

    /**
     * (取引変動)
     */
    private List transactionChanges;

    /**
     * @@roseuid 41087D6100DD
     */
    public WEB3TPSecurityValuationPerProduct()
    {
        substituteValuationPrices = null;
        valuationPrices = null;
        assetValuationPrices = null;
        unExecSubstituteValuationPrices = null;
        unExecValuationPrices = null;
        assetChanges = new ArrayList();
        transferChanges = new ArrayList();
        deliveryChanges = new ArrayList();
        transactionChanges = new ArrayList();
    }

    /**
     * (create銘柄ごと証券評価額)<BR>
     * 銘柄ごと証券評価額インスタンスを作成する。
     * @@param l_product - (対象銘柄)
     * @@param l_valuation - (証券評価)
     * @@roseuid 40D7E2B103D6
     */
    public static WEB3TPSecurityValuationPerProduct create(
        WEB3TPSecurityValuationProduct l_product,
        WEB3TPSecurityValuation l_valuation)
    {
        WEB3TPSecurityValuationPerProduct l_vpp =
            new WEB3TPSecurityValuationPerProduct();
        l_vpp.setAccountInfo(l_valuation.getAccountInfo());
        l_vpp.setNewOrderSpecs(l_valuation.getNewOrderSpecs());
        l_vpp.setCalcCondition(l_valuation.getCalcCondition());
        l_vpp.setProduct(l_product);
        return l_vpp;
    }

    /**
     * (get証券評価額)<BR>
     * 引数の指定日におけるある銘柄の証券評価額を取得する。<BR>
     * 1.引数の指定日におけるある銘柄の証券評価額を取得する<BR>
     *   ［a. 銘柄ごと証券評価.get計算済み銘柄ごと証券評価額()! = null の場合］<BR>
     *      (該当銘柄の証券評価額(T+0～T+5)すでに計算済み)<BR>
     *      引数の指定日におけるある銘柄の証券評価額を取得する<BR>
     *      証券評価額(引数.指定日) =
     * 銘柄ごと証券評価.get計算済み銘柄ごと証券評価額(引数.預り区分)<BR>
     * <BR>
     *   ［b. 銘柄ごと証券評価.get計算済み銘柄ごと証券評価額() = nullの場合］<BR>
     *     (該当銘柄の証券評価額(T+0～T+5)未計算)<BR>
     *
     * まず、calc証券評価額(預り区分)メソッドをコールして、
     * 集計、計算結果を保存する。<BR>
     *     引数の指定日におけるある銘柄の証券評価額を取得する<BR>
     *     証券評価額(引数.指定日) =
     * 銘柄ごと証券評価.get計算済み銘柄ごと証券評価額(引数.預り区分)<BR>
     * <BR>
     * 2.証券評価額(引数.指定日)を返す<BR>
     * <BR>
     * ※ nは、引数の指定日。<BR>
     *
     * @@param l_dblDate - (指定日)
     * @@param l_strDepositType - (預り区分)
     * @@return double
     * @@roseuid 40E51A1500BD
     */
    public double getValuationPrice(Date l_datDate, String l_strDepositType)
    {
        // 評価額が計算されているか？
        if (getValuationPrices(l_strDepositType) == null)
        {
            // 未計算の場合は、評価額を計算する
            calcValuationPrice(l_strDepositType);
        }
        // 指定日の評価額を返す
        int l_intIndex = getCalcCondition().calcSpecifiedPoint(l_datDate);
        //バグ Y00011  証券評価額の端数が切り捨て対応
        return Math.floor(getValuationPrices(l_strDepositType)[l_intIndex]);
    }

    /**
     * (get代用証券評価額(前日単価評価))<BR>
     * <BR>
     * 該当銘柄の代用証券評価額を返却する。<BR>
     * <BR>
     * １）”評価額[T+0..T+5]”を取得する。 <BR>
     * <BR>
     * 　@－”評価額[T+0..T+5]” = this.銘柄ごと代用証券評価額(前日単価評価)<BR>
     * <BR>
     * [a.計算未済みの場合] <BR>
     * 　@（”評価額[T+0..T+5]” == null） <BR>
     * <BR>
     * 　@　@・”評価額[T+0..T+5]”を計算する。 <BR>
     * 　@　@－this.calc証券評価額(String = 1：代用)をコール <BR>
     * <BR>
     * 　@　@・”評価額[T+0..T+5]”を取得する。 <BR>
     * 　@　@　@－”評価額[T+0..T+5]” = this.銘柄ごと代用証券評価額(前日単価評価) <BR>
     * <BR>
     * ２）該当銘柄の代用証券評価額を返却する。 <BR>
     * <BR>
     * 　@返却値：”評価額[T+0]”<BR>
     * <BR>
     * @@return double
     */
    public double getPrevPriceSubstituteValuation()
    {
        // 評価額[T+0..T+5]” = this.銘柄ごと代用証券評価額(前日単価評価)
        double[] l_dblPrices = this.prevPriceSubstituteValuation;

        // 計算未済みの場合
        if (l_dblPrices == null)
        {
            // this.calc証券評価額(String = 1：代用)をコール
            this.calcValuationPrice(WEB3TPDepositTypeDef.SUBSTITUTE);

            // 評価額[T+0..T+5]” = this.銘柄ごと代用証券評価額(前日単価評価)
            l_dblPrices = this.prevPriceSubstituteValuation;
        }

        // 該当銘柄の代用証券評価額を返却する
        return Math.floor(l_dblPrices[0]);
    }

    /**
     * (get預り資産評価額) <BR>
     * <BR>
     * 引数.指定日における、該当銘柄の預り資産評価額を返却する。<BR>
     * <BR>
     * １）”銘柄の預り資産評価額[T+0～T+5]”を計算する。 <BR>
     * <BR>
     * 　@[a.計算未済みの場合] <BR>
     * 　@（this.銘柄の預り資産評価額 == null）<BR>
     * <BR>
     * 　@　@・”銘柄の預り資産評価額[T+0～T+5]”を計算する。<BR>
     * 　@　@－”銘柄の預り資産評価額[T+0～T+5]” = this.calc既存保有評価額<預り資産>()<BR>
     * <BR>
     * 　@　@・計算した”銘柄の預り資産評価額[T+0～T+5]”を、プロパティにセットする。<BR>
     * 　@　@－this.銘柄の預り資産評価額 = ”銘柄の預り資産評価額[T+0～T+5]”<BR>
     * <BR>
     * <BR>
     * ２）引数.指定日における、該当銘柄の預り資産評価額を返却する。<BR>
     * <BR>
     * 　@返却値：this.銘柄の預り資産評価額[(指定日(*)] <BR>
     * <BR>
     * 　@(*)指定日 = this.get余力計算条件().calc指定日(:Date = 引数.指定日)<BR>
     * <BR>
     * 　@※戻り値は小数点以下を切り捨てる。<BR>
     * <BR>
     * @@param l_dblDate - (指定日)
     * @@return double
     */
    public double getAssetValuationPrice(Date l_datDate)
    {
        // 評価額が計算されているか？
        if (assetValuationPrices == null)
        {
            //配列宣言
            double[] l_valuationPrices = new double[6];

            // 既存保有評価額取得
            double[] l_assetValuationPrices = calcDepositAssetValuationPrice();

            // 銘柄ごと預り資産評価額を集計
            for (int i = 0; i < l_valuationPrices.length; i++)
            {
                //集計を行う
                l_valuationPrices[i] = l_assetValuationPrices[i];

                log.debug(
                    " DepositAssetValuationPrice["
                    + i
                    + "]="
                    + l_assetValuationPrices[i]);
                log.debug(" ValuationPrices[" + i + "]=" + l_valuationPrices[i]);
            }

            //集計結果を保存
            assetValuationPrices = l_valuationPrices;

        }
        // 引数に対する指定日を返す
        int l_intIndex = getCalcCondition().calcSpecifiedPoint(l_datDate);
        //預り資産評価額を設定する
        return Math.floor(assetValuationPrices[l_intIndex]);
    }

    /**
     * (get発注分証券評価額)<BR>
     * 引数の指定日におけるある銘柄の発注分証券評価額を取得する。<BR>
     * 1.引数の指定日におけるある銘柄の発注分証券評価額を取得する<BR>
     *   ［a. 銘柄ごと証券評価.get計算済み銘柄ごと発注分証券評価額()! = null の場合］<BR>
     *      (該当銘柄の発注分証券評価額(T+0～T+5)すでに計算済み)<BR>
     *      引数の指定日におけるある銘柄の発注分証券評価額を取得する<BR>
     *      証券評価額(引数.指定日) =
     * 銘柄ごと証券評価.get計算済み銘柄ごと発注分証券評価額(引数.預り区分)<BR>
     * <BR>
     *   ［b. 銘柄ごと証券評価.get計算済み銘柄ごと発注分証券評価額() = nullの場合］<BR>
     *     (該当銘柄の発注分証券評価額(T+0～T+5)未計算)<BR>
     *
     * まず、calc取引評価額<当日以降>(預り区分)メソッドをコールして、
     * 集計、計算結果を保存する。<BR>
     *     引数の指定日におけるある銘柄の発注分証券評価額を取得する<BR>
     *     発注分証券評価額(引数.指定日) =
     * 銘柄ごと証券評価.get計算済み銘柄ごと発注分証券評価額(引数.預り区分)<BR>
     * <BR>
     * 2.発注分証券評価額(引数.指定日)を返す<BR>
     * <BR>
     * ※ nは、引数の指定日。<BR>
     *
     * @@param l_datDate - (指定日)
     * @@param l_strDepositType - (預り区分)
     * @@return double
     */
    public double getUnExecValuationPrice(Date l_datDate, String l_strDepositType)
    {
        // 発注分評価額が計算されているか？
        if (getUnExecValuationPrices(l_strDepositType) == null)
        {
            // 未計算の場合は、評価額を計算する

            // 未約定買付代金(掛目考慮済み)
            double[] l_unexecutedValuationPrices =
                this.calcTransactionValuationPrice(
                l_strDepositType,
                WEB3TPExecTypeDef.UNEXECUTED,
                SideEnum.BUY);

            if (WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_strDepositType))
            {
                unExecSubstituteValuationPrices = l_unexecutedValuationPrices;
            }
            else
            {
                unExecValuationPrices = l_unexecutedValuationPrices;
            }
        }
        // 指定日の評価額を返す
        int l_intIndex = getCalcCondition().calcSpecifiedPoint(l_datDate);

        return Math.floor(getUnExecValuationPrices(l_strDepositType)[l_intIndex]);
    }

    /**
     * (set対象銘柄)<BR>
     * 対象銘柄を設定する。
     * @@param l_product - (対象銘柄)
     * @@roseuid 40B449E302D2
     */
    public void setProduct(WEB3TPSecurityValuationProduct l_product)
    {
        product = l_product;
    }

    /**
     * (add証券変動)<BR>
     * 証券変動を追加する。
     * @@param l_securityChange - (証券変動)
     * @@roseuid 40D7E397032A
     */
    public void addSecurityChange(WEB3TPSecurityChange l_securityChange)
    {
        final String STR_METHOD_NAME = "addSecurityChange(WEB3TPSecurityChange l_securityChange)";

        if (l_securityChange instanceof WEB3TPSecurityAssetChange)
        {
            assetChanges.add(l_securityChange);
        }
        else if (l_securityChange instanceof WEB3TPSecurityTransferChange)
        {
            transferChanges.add(l_securityChange);
        }
        else if (l_securityChange instanceof WEB3TPSecurityDeliveryChange)
        {
            deliveryChanges.add(l_securityChange);
        }
        else if (l_securityChange instanceof WEB3TPSecurityTransactionChange)
        {
            transactionChanges.add(l_securityChange);
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (calc既存保有数量<確定>) <BR>
     * <BR>
     * 銘柄の既存保有数量<確定>を、引数.指定日ごと集計する。 <BR>
     * <BR>
     * 1.既存保有変動一覧<確定>取得。 <BR>
     * <BR>
     * 2.一覧にある既存保有変動の数量を以下処理で集計する <BR>
     * <BR>
     *   数<BR>量集計 =Σ(*)既存保有変動.数量 <BR>
     *    (*)集計条件： <BR>
     *       既存保有変動一覧<確定>にある既存保有変動 <BR>
     * 　@ 　@ 且つ、既存保有変動.is変動期間中(n)=true <BR>
     * <BR>
     *    (*)nの設定値<BR>
     * 　@　@ [a.既存保有変動.預り区分 == "0:保護" かつ 既存保有変動.is分割新株式区分 == true]<BR>
     * 　@　@ 　@n = 引数.指定日+1 ※指定日==T+5の場合⇒指定日+1=T+5とする<BR>
     * 　@　@ [a.以外の場合]<BR>
     * 　@　@　@ n = 引数.指定日<BR>
     * <BR>
     * 3 数量集計を返す。<BR> 
     * <BR>
     * ※計算式に使用する各値の取得方法@ <BR>
     * ・預り区分・・・引数.預り区分 <BR>
     * ・既存保有変動一覧<確定>・・・銘柄ごと証券評価.get既存保有変動一覧<確定>(預り区分) <BR>
     * <BR>
     * @@param l_strDepositType - 預り区分
     * @@param l_dblDate - 指定日
     * @@return double
     */
    public double calcAssetQuantity(Date l_datDate, String l_strDepositType)
    {
        log.debug(
            "Calculating asset quantities, condition=[DepositType="
            + l_strDepositType
            + "],"
            + "Date=[" + l_datDate
            + "]");

        double l_dblQty = 0.0d;
        List l_changes = getAssetChanges(l_strDepositType);

        /*
         * 指定日+1を取得する。
         * ※指定日>=T+5の場合⇒指定日+1=T+5とする。
         */
        //指定日+1
        Date l_datNextDate;

        //指定日>=T+5の場合
        if(l_datDate.getTime() >= getCalcCondition().getBizDate(
            WEB3TPSpecifiedPointDef.T_5).getTime())
        {
            l_datNextDate = l_datDate;
        }
        //以外(指定日<T+5)の場合
        else
        {
            l_datNextDate = this.getCalcCondition().rollBizDate(l_datDate, 1);
        }

        for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
        {
            WEB3TPSecurityAssetChange l_change =
                (WEB3TPSecurityAssetChange) l_it.next();

            //[a.既存保有変動.預り区分 == "0:保護" かつ 既存保有変動.is分割新株式区分 == true]
            if(WEB3TPDepositTypeDef.TRUST.equals(l_change.getDepositType()) == true
                && l_change.isSplitNewStock() == true)
            {
                //既存保有変動.is変動期間中(指定日+1)=trueの場合
                if (l_change.isDuringReflectTime(l_datNextDate))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }
            //以外の場合
            else
            {
                //既存保有変動.is変動期間中(指定日)=trueの場合
                if (l_change.isDuringReflectTime(l_datDate))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }
        }

        return l_dblQty;

    }

    /**
     * (calc確定取引数量<当日以降>)<BR>
     * 引数.指定日の確定取引数量<当日以降>を集計する。<BR>
     * <BR>
     * １）当日約定済数量を集計する。<BR>
     * 　@１－１）取引変動一覧<当日以降>を取得。<BR>
     * 　@　@※銘柄ごと証券評価. 取引変動一覧<当日以降><BR>
     * <BR>
     * 　@１－２）一覧にある取引変動の数量を以下処理で集計する<BR>
     *   　@　@　@引数の指定日ごと集計の処理(n =指定日)<BR>
     * <BR>
     * 　@　@　@　@[取引変動.売買区分=1:BUYの場合]<BR>
     * 　@  　@　@　@当日約定済数量 =Σ(*)取引変動.数量 <BR>
     * <BR>
     * 　@　@　@　@[取引変動.売買区分=2:BUYの場合]<BR>
     * 　@  　@　@　@当日約定済数量 =Σ(*)（－取引変動.数量） <BR>
     * <BR>
     * 　@　@　@　@(*)集計条件：<BR>
     * 　@　@　@　@　@取引変動.get受渡日() <= 引数.指定日　@且つ、<BR>
     * 　@　@　@　@　@（　@<BR>
     * 　@　@　@　@　@　@（取引変動.約定区分=約定済）　@または　@<BR>
     * 　@　@　@　@　@　@（取引変動.注文カテゴリ=7：現引・現渡））<BR>
     * 　@　@　@　@　@且つ、 <BR>
     * 　@　@　@　@　@取引変動.税区分≠5：ストックオプション <BR>
     * <BR>
     * ２）確定取引数量<当日以降>を返却する。<BR>
     * 　@[返却値]　@当日約定済数量<BR>
     * <BR>
     * @@param l_dblDate - 指定日
     * @@return double
     */
    public double calcExecutedTransactionQuantity(Date l_datDate)
    {
        log.debug(
            "Calculating Executed Transaction quantities, "
            + "Date=[" + l_datDate
            + "]");
        
        /*
         * 当日約定済数量を集計する。
         */
        
        //確定取引数量<当日以降>
        double l_dblQty = 0.0d;
        for(Iterator l_it = this.transactionChanges.iterator(); l_it.hasNext();)
        {
            WEB3TPSecurityTransactionChange l_change = (WEB3TPSecurityTransactionChange) l_it
                .next();

            //取引変動.get受渡日() <= 引数.指定日 且つ、
            //（（取引変動.約定区分=約定済） または （取引変動.注文カテゴリ=7：現引・現渡））
            // 且つ、 取引変動.税区分≠5：ストックオプション 
            if(l_change.getDeliveryDate().getTime() <= l_datDate.getTime()&& 
                (l_change.getExecType().equals(WEB3TPExecTypeDef.EXECUTED) || 
                OrderCategEnum.SWAP_MARGIN.equals(l_change.getOrderCateg())) && 
                !TaxTypeEnum.STOCK_OPTION.equals(l_change.getTaxType()))
            {
                //買付の場合
                //(取引変動.売買区分 == 1:買付)
                if(l_change.getSide().equals(SideEnum.BUY))
                {
                    //確定取引数量<当日以降>に変動数量を加算する。
                    //(確定取引数量<当日以降> = 確定取引数量<当日以降> + 取引変動.変動数量)
                    l_dblQty += l_change.getQuantity();

                }
                //売付の場合
                //(取引変動.売買区分 == 2:売付)
                else if(l_change.getSide().equals(SideEnum.SELL))
                {
                    //確定取引数量<当日以降>に変動数量を減算する。
                    //(確定取引数量<当日以降> = 確定取引数量<当日以降> - 取引変動.変動数量)
                    l_dblQty -= l_change.getQuantity();

                }
            }
        }
        //確定取引数量<当日以降>を返却する。
        return l_dblQty;
    }
    
    /**
     * (calc証券評価額)<BR>
     * <BR>
     * 銘柄の証券評価額（T+0～T+5）を計算する。<BR>
     * <BR>
     * １）証券評価額[T+0..5]、発注分証券評価額[T+0..5]、<BR>
     * 　@　@証券評価額（前日単価評価）[T+0..5]を計算する。<BR> 
     * <BR>
     * 　@[a.対象銘柄.銘柄タイプ=株式の場合]<BR>
     * 　@（this.対象銘柄.get銘柄タイプ == 1:株式）<BR>
     * <BR>
     * 　@　@①@既存保有数量[T+0..5]、振替数量[T+0..5]、出庫数量[T+0..5]を取得する<BR>
     * <BR>
     * 　@　@　@－既存保有数量[T+0..5] = this.calc既存保有数量<確定>(:String = 引数.預り区分)<BR>
     * 　@　@　@－振替数量[T+0..5] = this.calc振替数量<当日以降>(:String = 引数.預り区分)<BR>
     * 　@　@　@－出庫数量[T+0..5] = this.calc出庫数量<当日以降>(:String = 引数.預り区分)<BR>
     * <BR>
     * 　@　@②約定済買付数量[T+0..5]、約定済買付代金[T+0..5]、未約定買付数量[T+0..5]、<BR>
     * 　@　@　@　@未約定買付代金[T+0..5] 、未約定売付数量[T+0..5]、約定済売付数量[T+0..5]を取得する<BR>
     * <BR>
     * 　@　@　@－約定済買付数量[T+0..5] = this.calc取引数量<当日以降>(:String = 引数.預り区分, :String = 2:約定済, :SideEnum = 1:買付)<BR>
     * 　@　@　@－約定済売付数量[T+0..5] = this.calc取引数量<当日以降>(:String = 引数.預り区分, :String = 2:約定済, :SideEnum = 2:売付)<BR>
     * 　@　@　@－未定済売付数量[T+0..5] = this.calc取引数量<当日以降>(:String = 引数.預り区分, :String = 1:未約定, :SideEnum = 2:売付)<BR>
     * <BR>
     * 　@　@　@－約定済買付代金[T+0..5] = this.calc取引評価額<当日以降>(:String = 引数.預り区分, :String = 2:約定済, :SideEnum = 1:買付)<BR>
     * 　@　@　@－未定済買付代金[T+0..5] = this.calc取引評価額<当日以降>(:String = 引数.預り区分, :String = 1:未約定, :SideEnum = 1:買付)<BR>
     * <BR>
     * 　@　@③売付数量[T+0...5]、保有数量[T+0...5]、売付数量余剰分[T+0...5]、差引後約定済買付数量[T+0...5]を計算する。<BR>
     * <BR>
     * 　@　@　@－売付数量[T+0...5] = 約定済売付数量[T+0..5] + 未定済売付数量[T+0..5]<BR>
     * 　@　@　@－保有数量[T+0...5] = 既存保有数量[T+0..5] + 振替数量[T+0..5] - 出庫数量[T+0..5] - 売付数量[T+0...5]<BR>
     * 　@　@　@－売付数量余剰分[T+0...5] = Abs(MIN(保有数量[T+0...5],0))<BR>
     * 　@　@　@－差引後約定済買付数量[T+0...5] = Max(約定済買付数量[T+0...5] - 売付数量余剰分[T+0...5], 0)<BR>
     * <BR>
     * 　@　@④評価掛目、評価単価、前日単価を取得する。<BR>
     * <BR>
     * 　@　@　@－評価掛目 = this.対象銘柄.get証券評価掛目(:String = 引数.預り区分)<BR>
     * 　@　@　@－評価単価 = this.対象銘柄.get評価単価()<BR>
     * 　@　@　@－前日単価 = this.対象銘柄.get前日単価()<BR>
     * <BR>
     * 　@　@⑤証券評価額[T+0..2]、証券評価額（前日単価評価）[T+0..2]を計算する。<BR>
     * <BR>
     * 　@　@　@－証券評価額[T+0..2] = MAX(保有数量[T+0..2], 0) × 評価掛目 × 評価単価<BR>
     * 　@　@　@－証券評価額（前日単価評価）[T+0..2] = MAX(保有数量[T+0..2], 0) × 評価掛目 × 前日単価<BR>
     * <BR>
     * 　@　@⑥証券評価額[T+3..5]、証券評価額（前日単価評価）[T+3..5]を計算する。<BR>
     * <BR>
     * 　@　@　@[b.当日発注時間帯の場合]<BR>
     * 　@　@　@（this.get余力計算条件().is翌日注文受付開始区分<現物株式>() == false）<BR>
     * <BR>
     * 　@　@　@　@－証券評価額[T+3..5] = MAX(保有数量[T+3..5], 0) × 評価掛目 × 評価単価<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 約定済買付代金[T+3..5] × 差引後約定済買付数量[T+3..5] ÷ 約定済買付数量[T+3..5]<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 未定済買付代金[T+3..5]<BR> 
     * <BR>
     * 　@　@　@　@－証券評価額（前日単価評価）[T+3..5] = MAX(保有数量[T+3..5], 0) × 評価掛目 × 前日単価<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 約定済買付代金[T+3..5] × 差引後約定済買付数量[T+3..5] ÷ 約定済買付数量[T+3..5]<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 未定済買付代金[T+3..5]<BR>
     * <BR>
     * 　@　@　@[b.翌日発注時間帯の場合]<BR>
     * 　@　@　@（this.get余力計算条件().is翌日注文受付開始区分<現物株式>() == true）<BR> 
     * <BR>
     * 　@　@　@　@－証券評価額[T+3..5] = (MAX(保有数量[T+3..5], 0) + 差引後約定済買付数量[T+3..5]) × 評価掛目 × 評価単価<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 未定済買付代金[T+3..5]<BR>
     * <BR>
     * 　@　@　@　@－証券評価額（前日単価評価）[T+3..5] = (MAX(保有数量[T+3..5], 0) + 差引後約定済買付数量[T+3..5]) × 評価掛目 × 前日単価<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 未定済買付代金[T+3..5]<BR>
     * <BR>
     * 　@　@⑦発注分証券評価額[T+0..5]を計算する。<BR>
     * <BR>
     * 　@　@　@－発注分証券評価額[T+0..5] = 未定済買付代金[T+0..5]<BR>
     * <BR>
     * <BR>
     * 　@[a.対象銘柄.銘柄タイプ=累積投資の場合]<BR>
     * 　@（this.対象銘柄.get銘柄タイプ == 7:累積投信）<BR>
     * <BR>
     * 　@　@①@既存保有評価額[T+0..5]、GP解約代金[T+0..5]、GP買付代金[T+0..5]<BR>
     * <BR>
     * 　@　@　@－既存保有評価額[T+0..5] = this.calc既存保有評価額<確定>(:String = 引数.預り区分)<BR>
     * 　@　@　@－GP解約代金[T+0..5] = this.calc取引評価額<当日以降>(:String = 引数.預り区分, :String = 1:未約定, :SideEnum = 2:売付)<BR>
     * 　@　@　@－GP買付代金[T+0..5] = this.calc取引評価額<当日以降>(:String = 引数.預り区分, :String = 1:未約定, :SideEnum = 1:買付)<BR>
     * <BR>
     * 　@　@②証券評価額[T+0..5]を計算する。<BR>
     * <BR>
     * 　@　@　@－証券評価額[T+0..5] = MAX((既存保有評価額[T+0..5] - GP解約代金[T+0..5] + GP買付代金[T+0..5]), 0)<BR>
     * <BR>
     * 　@　@③発注分証券評価額[T+0..5]を計算する。<BR>
     * <BR>
     * 　@　@　@－発注分証券評価額[T+0..5] = 0<BR>
     * <BR>
     * <BR>
     * 　@[a.対象銘柄.銘柄タイプ=投信,債券,その他証券の場合]<BR>
     * 　@（this.対象銘柄.get銘柄タイプ == 2:債券 ||<BR>
     * 　@　@　@this.対象銘柄.get銘柄タイプ == 3:投資信託 ||<BR>
     * 　@　@　@　@this.対象銘柄.get銘柄タイプ == 0:その他）<BR>
     * <BR>
     * 　@　@①@既存保有評価額[T+0..5]、振替評価額[T+0..5]<BR>
     * <BR>
     * 　@　@　@－既存保有評価額[T+0..5] = this.calc既存保有評価額<確定>(:String = 引数.預り区分)<BR>
     * 　@　@　@－振替評価額[T+0..5] = this.calc振替評価額<当日以降>(:String = 引数.預り区分)<BR>
     * <BR>
     * 　@　@②証券評価額[T+0..5]を計算する。<BR>
     * <BR>
     * 　@　@　@－証券評価額[T+0..5] = MAX((既存保有評価額[T+0..5] + 振替評価額[T+0..5]), 0)<BR>
     * 　@　@　@－証券評価額（前日単価評価）[T+0..5] = MAX((既存保有評価額[T+0..5] + 振替評価額[T+0..5]), 0)<BR>
     * <BR>
     * 　@　@③発注分証券評価額[T+0..5]を計算する。<BR>
     * <BR>
     * 　@　@　@－発注分証券評価額[T+0..5] = 0<BR>
     * <BR>
     * <BR>
     * ２）計算した、証券評価額[T+0..5]、発注分証券評価額[T+0..5]、証券評価額（前日単価評価）[T+0..5]をプロパティにセットする。<BR> 
     * <BR>
     * 　@[a.代用の場合]<BR>
     * 　@（引数.預り区分 == 1:代用）<BR>
     * <BR>
     * 　@　@－this.銘柄ごと代用証券評価額 = 証券評価額[T+0..5]<BR>
     * 　@　@－this.銘柄ごと発注分代用証券評価額 = 発注分証券評価額[T+0..5]<BR>
     * 　@　@－this.銘柄ごと代用証券評価額（前日単価評価） = 証券評価額（前日単価評価）[T+0..5]<BR>
     * <BR>
     * 　@[a.以外（保護の）場合]<BR>
     * <BR>
     * 　@　@－this.銘柄ごと預り証券評価額 = 証券評価額[T+0..5]<BR>
     * 　@　@－this.銘柄ごと発注分預り証券評価額 = 発注分証券評価額[T+0..5]<BR>
     * <BR>
     * @@param l_strDepositType - (預り区分)
     */
    private void calcValuationPrice(String l_strDepositType)
    {
        final String STR_METHOD_NAME = "calcValuationPrice(String l_strDepositType)";

        log.debug(
            ">>>>> Calculationg valuation prices. condition=[DepositType="
            + l_strDepositType
            + "] <<<<<");

        //証券評価額
        double[] l_valuationPrices = new double[6];

        //証券評価額（前日単価評価）
        double[] l_dblValuationPrices1 = new double[6];

        // 発注分証券評価用
        double[] l_unexecutedValuationPrices = new double[6];
        
        ProductTypeEnum l_productType = product.getProductType();

        log.debug(" product type=" + l_productType);

        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {

            // 既存保有数量
            double[] l_assetQuantities = calcAssetQuantity(l_strDepositType);
            // 振替数量
            double[] l_transferQuantities =
                calcTransferQuantity(l_strDepositType);
            // 出庫数量
            double[] l_deliveryQuantities =
                calcDeliveryQuantity(l_strDepositType);
            // 約定済売付数量
            double[] l_executedSellOrderQuantities =
                calcTransactionQuantity(
                l_strDepositType,
                WEB3TPExecTypeDef.EXECUTED,
                SideEnum.SELL);
            // 未約定売付数量
            double[] l_unexecutedSellOrderQuantities =
                calcTransactionQuantity(
                l_strDepositType,
                WEB3TPExecTypeDef.UNEXECUTED,
                SideEnum.SELL);
            // 約定済買付数量
            double[] l_executedBuyOrderQuantities =
                this.calcTransactionQuantity(
                l_strDepositType,
                WEB3TPExecTypeDef.EXECUTED,
                SideEnum.BUY);
            // 未約定買付代金
            double[] l_unexecutedBuyOrderPrices =
                this.calcTransactionValuationPrice(
                l_strDepositType,
                WEB3TPExecTypeDef.UNEXECUTED,
                SideEnum.BUY);
            // 約定済買付代金
            double[] l_executedBuyOrderPrices =
                this.calcTransactionValuationPrice(
                l_strDepositType,
                WEB3TPExecTypeDef.EXECUTED,
                SideEnum.BUY);

            // 売付数量
            double[] l_sellOrderQuantities = new double[6];
            for (int i = 0; i < l_sellOrderQuantities.length; i++)
            {
                // 売付数量 = 約定済売付数量 - 未約定売付数量
                l_sellOrderQuantities[i] =
                    l_executedSellOrderQuantities[i]
                    + l_unexecutedSellOrderQuantities[i];

                log.debug(
                    " SellOrderQuantities["
                    + i
                    + "]="
                    + l_sellOrderQuantities[i]);

            }

            // 保有数量
            double[] l_adjustedAssetQuantities = new double[6];
            for (int i = 0; i < l_adjustedAssetQuantities.length; i++)
            {
                // 保有数量 = 既存保有数量 + 振替数量 - 出庫数量 - 売付数量
                l_adjustedAssetQuantities[i] =
                    l_assetQuantities[i]
                    + l_transferQuantities[i]
                    - l_deliveryQuantities[i]
                    - l_sellOrderQuantities[i];

                log.debug(
                    " AdjustedAssetQuantities["
                    + i
                    + "]="
                    + l_adjustedAssetQuantities[i]);

            }

            // 売付数量余剰分（デフォルト=0）
            double l_dblSellOrderSurplus[] = new double[6];
            // 保有数量[T+5] < 0 の場合
            for (int i = 0; i < 6; i++)
            {
                if (l_adjustedAssetQuantities[i] < 0)
                {
                    // 売付数量余剰分 = Abs(保有数量[T+5])
                    l_dblSellOrderSurplus[i] = Math.abs(l_adjustedAssetQuantities[i]);
                }
            }

            log.debug(" SellOrderSurplus=" + l_dblSellOrderSurplus);

            // 保有数量 = Max(保有数量, 0)
            for (int i = 0; i < l_adjustedAssetQuantities.length; i++)
            {
                l_adjustedAssetQuantities[i] =
                    Math.max(l_adjustedAssetQuantities[i], 0);

                log.debug(
                    " AdjustedAssetQuantities["
                    + i
                    + "]="
                    + l_adjustedAssetQuantities[i]);

            }

            // 評価掛目
            double l_dblValuationRatio =
                product.getValuationRatio(l_strDepositType);
            // 評価単価
            double l_dblUnitPrice = product.getUnitPrice();

            // 前日単価
            double l_dblPrePrice = product.getPrePrice();

            log.debug(" ValuationRatio=" + l_dblValuationRatio);
            log.debug(" UnitPrice=" + l_dblUnitPrice);

            // 証券評価額[T+0..2]、証券評価額（前日単価評価）[T+0..2]を計算する
            for (int i = 0; i < 3; i++)
            {
                //証券評価額[T+0..2] = MAX(保有数量[T+0..2], 0) × 評価掛目 × 評価単価
                l_valuationPrices[i] =
                    l_adjustedAssetQuantities[i]
                    * l_dblValuationRatio
                    * l_dblUnitPrice;

                //証券評価額（前日単価評価）[T+0..2] = MAX(保有数量[T+0..2], 0) × 評価掛目 × 前日単価
                l_dblValuationPrices1[i] =
                    l_adjustedAssetQuantities[i]
                    * l_dblValuationRatio
                    * l_dblPrePrice;

                log.debug(" ValuationPrices[" + i + "]=" + l_valuationPrices[i]);

            }

            boolean l_isEquityExecutionDiv =
                getCalcCondition().isEquityNextDayOrderStartDiv();
            log.debug(" isEquityExecutionDiv=" + l_isEquityExecutionDiv);

            // 証券評価額[T+3..5]、証券評価額（前日単価評価）[T+3..5]を計算する
            for (int i = 3; i < l_valuationPrices.length; i++)
            {
                // 差引後約定済買付数量 = Max(約定済買付数量 - 売付数量余剰分, 0)
                double l_dblAdjustedExecutedBuyOrderQuantity =
                    Math.max(
                    (l_executedBuyOrderQuantities[i]
                     - l_dblSellOrderSurplus[i]),
                    0.0);

                log.debug(
                    " AdjustedExecutedBuyOrderQuantities["
                    + i
                    + "]="
                    + l_dblAdjustedExecutedBuyOrderQuantity);

                if (!l_isEquityExecutionDiv)
                {

                    log.debug(
                        " ExecutedBuyOrderPrices["
                        + i
                        + "]"
                        + l_executedBuyOrderPrices[i]);
                    log.debug(
                        " ExecutedBuyOrderQuantities["
                        + i
                        + "]"
                        + l_executedBuyOrderQuantities[i]);
                    log.debug(
                        " UnexecutedBuyOrderPrices["
                        + i
                        + "]"
                        + l_unexecutedBuyOrderPrices[i]);

                    // 評価額 = 保有数量 * 評価単価 * 評価掛目
                    //          + (約定済買付代金 * 差引後約定済買付数量 / 約定済買付数量
                    //             + 未約定買付代金) * 評価掛目
                    l_valuationPrices[i] =
                        (l_adjustedAssetQuantities[i]
                         * l_dblUnitPrice
                         * l_dblValuationRatio)
                        + ( ( (l_executedBuyOrderQuantities[i] == 0
                               ? 0
                               : l_executedBuyOrderPrices[i]
                               * l_dblAdjustedExecutedBuyOrderQuantity
                               / l_executedBuyOrderQuantities[i])
                             + l_unexecutedBuyOrderPrices[i])
                           );
//                           * l_dblValuationRatio);

                    //証券評価額（前日単価評価）[T+3..5] = MAX(保有数量[T+3..5], 0) × 評価掛目 × 前日単価
                    //         + 約定済買付代金[T+3..5] × 差引後約定済買付数量[T+3..5] ÷ 約定済買付数量[T+3..5]
                    //         + 未定済買付代金[T+3..5]
                    l_dblValuationPrices1[i] =
                        (l_adjustedAssetQuantities[i]
                         * l_dblPrePrice
                         * l_dblValuationRatio)
                        + ( ( (l_executedBuyOrderQuantities[i] == 0
                               ? 0
                               : l_executedBuyOrderPrices[i]
                               * l_dblAdjustedExecutedBuyOrderQuantity
                               / l_executedBuyOrderQuantities[i])
                             + l_unexecutedBuyOrderPrices[i])
                           );
                }
                else
                {
                    // 保有数量 = 保有数量 - 差引後約定済買付数量
                    double l_dblAdjustedAssetQuantity =
                        l_adjustedAssetQuantities[i]
                        + l_dblAdjustedExecutedBuyOrderQuantity;

                    log.debug(
                        " AdjustedAssetQuantities["
                        + i
                        + "]="
                        + l_adjustedAssetQuantities[i]);

                    log.debug(
                        " UnexecutedBuyOrderPrices["
                        + i
                        + "]"
                        + l_unexecutedBuyOrderPrices[i]);

//                    // 評価額 = (保有数量 * 評価単価 + 未約定買付代金) * 評価掛目
//                    l_valuationPrices[i] =
//                        (l_dblAdjustedAssetQuantity * l_dblUnitPrice
//                         + l_unexecutedBuyOrderPrices[i])
//                        * l_dblValuationRatio;
                        
                    // 評価額 = (保有数量 * 評価単価 * 評価掛目) + 未約定買付代金
                    l_valuationPrices[i] =
                        (l_dblAdjustedAssetQuantity * l_dblUnitPrice * l_dblValuationRatio)
                         + l_unexecutedBuyOrderPrices[i];

                    // 証券評価額（前日単価評価）[T+3..5] = (MAX(保有数量[T+3..5], 0)
                    //         + 差引後約定済買付数量[T+3..5]) × 評価掛目 × 前日単価
                    //         + 未定済買付代金[T+3..5]
                    l_dblValuationPrices1[i] =
                        l_dblAdjustedAssetQuantity
                        * l_dblValuationRatio
                        * l_dblPrePrice
                        + l_unexecutedBuyOrderPrices[i];
                }

                log.debug(" ValuationPrices[" + i + "]=" + l_valuationPrices[i]);

                // 発注分証券評価
                l_unexecutedValuationPrices[i] = l_unexecutedBuyOrderPrices[i];

                log.debug(" UnExecValuationPrices[" + i + "]=" + l_unexecutedValuationPrices[i]);
            }
        }
        else if (ProductTypeEnum.RUITO.equals(l_productType))
        {

            // 既存保有評価額
            double[] l_assetValuationPrices =
                calcAssetValuationPrice(l_strDepositType);

            // GP解約代金
            double[] l_gpSellTransactionPrices =
                calcTransactionValuationPrice(
                l_strDepositType,
                WEB3TPExecTypeDef.UNEXECUTED,
                SideEnum.SELL);

            // GP買付代金
            double[] l_gpBuyTransactionPrices =
                calcTransactionValuationPrice(
                l_strDepositType,
                WEB3TPExecTypeDef.UNEXECUTED,
                SideEnum.BUY);

            // 銘柄ごと証券評価額を集計
            for (int i = 0; i < l_valuationPrices.length; i++)
            {

                l_valuationPrices[i] = Math.max(
                    l_assetValuationPrices[i]
                    - l_gpSellTransactionPrices[i]
                    + l_gpBuyTransactionPrices[i]
                    , 0);

                log.debug(
                    " AssetValuationPrices["
                    + i
                    + "]="
                    + l_assetValuationPrices[i]);
                log.debug(
                    " GPSellPrices[" + i + "]=" + l_gpSellTransactionPrices[i]);
                log.debug(
                    " GPBuyPrices[" + i + "]=" + l_gpBuyTransactionPrices[i]);
                log.debug(" ValuationPrices[" + i + "]=" + l_valuationPrices[i]);

            }

        }
        else if (
            ProductTypeEnum.BOND.equals(l_productType)
            || ProductTypeEnum.MUTUAL_FUND.equals(l_productType)
            || ProductTypeEnum.OTHER.equals(l_productType))
        {

            // 既存保有評価額
            double[] l_assetValuationPrices =
                calcAssetValuationPrice(l_strDepositType);

            // 振替評価額
            double[] l_transferValuationPrices =
                calcTransferValuationPrice(l_strDepositType);

            // 銘柄ごと証券評価額を集計
            for (int i = 0; i < l_valuationPrices.length; i++)
            {

                l_valuationPrices[i] =
                    Math.max(l_assetValuationPrices[i] + l_transferValuationPrices[i], 0);

                log.debug(
                    " AssetValuationPrices["
                    + i
                    + "]="
                    + l_assetValuationPrices[i]);
                log.debug(
                    " TransferValuationPrices["
                    + i
                    + "]="
                    + l_transferValuationPrices[i]);
                log.debug(" ValuationPrices[" + i + "]=" + l_valuationPrices[i]);

            }

            // 証券評価額（前日単価評価）を集計
            for (int i = 0; i < l_dblValuationPrices1.length; i++)
            {

                l_dblValuationPrices1[i] =
                    Math.max(l_assetValuationPrices[i] + l_transferValuationPrices[i], 0);

                log.debug(
                    " AssetValuationPrices["
                    + i
                    + "]="
                    + l_assetValuationPrices[i]);
                log.debug(
                    " TransferValuationPrices["
                    + i
                    + "]="
                    + l_transferValuationPrices[i]);
                log.debug(" ValuationPrices[" + i + "]=" + l_dblValuationPrices1[i]);

            }

        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80025, this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_strDepositType))
        {
            substituteValuationPrices = l_valuationPrices;
            unExecSubstituteValuationPrices = l_unexecutedValuationPrices;
            prevPriceSubstituteValuation = l_dblValuationPrices1;
        }
        else
        {
            valuationPrices = l_valuationPrices;
            unExecValuationPrices = l_unexecutedValuationPrices;
        }

        log.debug(
            "<<<<<  Valuation prices calculated. >>>>>");

    }

    /**
     * (get銘柄ごと証券評価額)<BR>
     * 計算済み銘柄ごと証券評価額証券評価額（T+0～T+5）を取得する。
     * @@param l_strDepositType - (預り区分)
     * @@return double[]
     * @@roseuid 40E5728D02E4
     */
    private double[] getValuationPrices(String l_strDepositType)
    {
        if (WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_strDepositType))
        {
            return substituteValuationPrices;
        }
        else
        {
            return valuationPrices;
        }
    }

    /**
     * (get銘柄ごと発注分証券評価額)<BR>
     * 計算済み銘柄ごと発注分証券評価額（T+0～T+5）を取得する。
     * @@param l_strDepositType - (預り区分)
     * @@return double[]
     */
    private double[] getUnExecValuationPrices(String l_strDepositType)
    {
        if (WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_strDepositType))
        {
            return unExecSubstituteValuationPrices;
        }
        else
        {
            return unExecValuationPrices;
        }
    }

    /**
>>>>>>> 1.28
     * (calc既存保有評価額<確定>)<BR>
     * ある銘柄の既存保有評価額<確定>(T+0～T+5)を計算する。<BR>
     * <BR>
     * 1.既存保有変動一覧<確定>取得。<BR>
     * <BR>
     * 2.一覧にある既存保有変動の評価額を以下処理で集計する<BR>
     *   各営業日ごとの処理(n の 取り得る範囲はT+0～ T+5)<BR>
     * <BR>
     *   評価額集計(n) = Σ(*)既存保有変動.評価額 <BR>
     *    (*)集計条件：<BR>
     *       既存保有変動一覧<確定>にある既存保有変動<BR>
     * 　@ 　@ 且つ、既存保有変動.is変動期間中(n) = true<BR>
     * <BR>
     * 3.配列: 評価額集計[T+0 ...5]を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・預り区分・・・引数.預り区分<BR>
     * ・既存保有変動一覧<確定>・・・銘柄ごと証券評価.get既存保有変動一覧<確定>(預り区分)<BR>
     * @@param l_strDepositType - (預り区分)
     * @@return double[]
     * @@roseuid 40B2E56002F5
     */
    private double[] calcAssetValuationPrice(String l_strDepositType)
    {

        log.debug(
            "Calculating asset change prices, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblPrices = new double[6];
        List l_changes = getAssetChanges(l_strDepositType);

        for (int i = 0; i < l_dblPrices.length; i++)
        {

            double l_dblPrice = 0.0;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    //預り資産対応（ここで掛目を演算するように修正）
                    l_dblPrice += l_change.getValuationPrice() * l_change.getValuationRatio();
                }
            }

            l_dblPrices[i] = l_dblPrice;

        }

        return l_dblPrices;

    }

    /**
     * (calc既存保有数量<確定>)<BR>
     * ある銘柄の既存保有数量<確定>(T+0～T+5)を計算する。<BR>
     * <BR>
     * 1.既存保有変動一覧<確定>取得。<BR>
     * <BR>
     * 2.一覧にある既存保有変動の評価額を以下処理で集計する<BR>
     *   各営業日ごとの処理(n の 取り得る範囲はT+0～ T+5)<BR>
     * <BR>
     *   数量集計(n) =Σ(*)既存保有変動.数量 <BR>
     *    (*)集計条件：<BR>
     *       既存保有変動一覧<確定>にある既存保有変動<BR>
     * 　@ 　@ 且つ、既存保有変動.is変動期間中(n)=true<BR>
     * <BR>
     * 3.配列: 数量集計[T+0...5]を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・預り区分・・・引数.預り区分<BR>
     * ・既存保有変動一覧<確定>・・・銘柄ごと証券評価.
     * get既存保有変動一覧<確定>(預り区分)<BR>
     * @@param l_strDepositType - (預り区分)
     * @@return double[]
     * @@roseuid 40B2EC000269
     */
    private double[] calcAssetQuantity(String l_strDepositType)
    {

        log.debug(
            "Calculating asset change quantities, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblQtys = new double[6];
        List l_changes = getAssetChanges(l_strDepositType);

        for (int i = 0; i < l_dblQtys.length; i++)
        {

            double l_dblQty = 0.0d;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }

            l_dblQtys[i] = l_dblQty;

        }

        return l_dblQtys;

    }

    /**
     * (calc振替評価額<当日以降>)<BR>
     * ある銘柄の振替評価額<当日以降>(T+0～T+5)を計算する。<BR>
     * <BR>
     * 1.振替変動一覧<当日以降>取得。<BR>
     * <BR>
     * 2.一覧にある振替変動の評価額を以下処理で集計する<BR>
     *   各営業日ごとの処理(nの取り得る範囲はT+0～T+5)<BR>
     * <BR>
     *   評価額集計(n) = Σ(*)振替変動.評価額 <BR>
     *     (*)集計条件：<BR>
     *        振替変動一覧<当日以降>にある振替変動<BR>
     * 　@  　@ 且つ、振替変動.is変動期間中(n) = true<BR>
     * <BR>
     * 3.配列: 評価額集計[T+0...5]を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・預り区分・・・引数.預り区分<BR>
     * ・振替変動一覧<当日以降>・・・銘柄ごと証券評価.
     * get振替変動一覧<当日以降>(預り区分)<BR>
     * @@param l_strDepositType - (預り区分)
     * @@return double[]
     * @@roseuid 40B2E664017E
     */
    private double[] calcTransferValuationPrice(String l_strDepositType)
    {

        log.debug(
            "Calculating transfer change prices, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblPrices = new double[6];
        List l_changes = getTransferChanges(l_strDepositType);

        for (int i = 0; i < l_dblPrices.length; i++)
        {

            double l_dblPrice = 0.0;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    //預り資産対応（ここで掛目を演算するように修正）
                    l_dblPrice += l_change.getValuationPrice() * l_change.getValuationRatio();
                }
            }

            l_dblPrices[i] = l_dblPrice;

        }

        return l_dblPrices;

    }

    /**
     * (calc振替数量<当日以降>)<BR>
     * ある銘柄の振替数量<当日以降>(T+0～T+5)を計算する。<BR>
     * <BR>
     * 1.振替変動一覧<当日以降>取得。<BR>
     * <BR>
     * 2.一覧にある振替変動の数量を以下処理で集計する<BR>
     *   各営業日ごとの処理(n の 取り得る範囲はT+0～T+5)<BR>
     * <BR>
     *   数量集計(n) = Σ(*)振替変動.数量<BR>
     *     (*)集計条件：<BR>
     *        振替変動一覧<当日以降>にある振替変動<BR>
     * 　@ 　@  且つ、振替変動.is変動期間中(n) = true<BR>
     * <BR>
     * 3.配列: 数量集計[T+0 ...5]を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・預り区分・・・引数.預り区分<BR>
     * ・振替変動一覧<当日以降>・・・銘柄ごと証券評価.
     * get振替変動一覧<当日以降>(預り区分)<BR>
     * @@param l_strDepositType - (預り区分)
     * @@return double[]
     * @@roseuid 40B2EC000278
     */
    private double[] calcTransferQuantity(String l_strDepositType)
    {

        log.debug(
            "Calculating transfer change quantities, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblQtys = new double[6];
        List l_changes = getTransferChanges(l_strDepositType);

        for (int i = 0; i < l_dblQtys.length; i++)
        {

            double l_dblQty = 0.0d;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }

            l_dblQtys[i] = l_dblQty;

        }

        return l_dblQtys;

    }

    /**
     * (calc出庫評価額<当日以降>)<BR>
     * ある銘柄の出庫評価額<当日以降>(T+0～T+5)を計算する。<BR>
     * <BR>
     * 1.出庫変動一覧<当日以降>取得。<BR>
     * <BR>
     * 2.一覧にある出庫変動の評価額を以下処理で集計する<BR>
     *   各営業日ごとの処理(n の 取り得る範囲はT+0～ T+5)<BR>
     * <BR>
     *   評価額集計(n) = Σ(*)出庫変動.評価額<BR>
     *     (*)集計条件：<BR>
     *        出庫変動一覧<当日以降>にある出庫変動<BR>
     * 　@ 　@  且つ、出庫変動.is変動期間中(n) = true<BR>
     * <BR>
     * 3.配列: 評価額集計[T+0...5]を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・預り区分・・・引数.預り区分<BR>
     * ・出庫変動一覧<当日以降>・・・銘柄ごと証券評価.
     * get出庫変動一覧<当日以降>(預り区分)<BR>
     * @@param l_strDepositType - (預り区分)
     * @@return double[]
     * @@roseuid 40E5672E02C0
     */
    private double[] calcDeliveryValuationPrice(String l_strDepositType)
    {

        log.debug(
            "Calculating delivery change prices, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblPrices = new double[6];
        List l_changes = getDeliveryChanges(l_strDepositType);

        for (int i = 0; i < l_dblPrices.length; i++)
        {

            double l_dblPrice = 0.0;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    //預り資産対応（ここで掛目を演算するように修正）
                    l_dblPrice += l_change.getValuationPrice() * l_change.getValuationRatio();
                }
            }

            l_dblPrices[i] = l_dblPrice;

        }

        return l_dblPrices;

    }

    /**
     * (calc出庫数量<当日以降>)<BR>
     * ある銘柄の出庫数量<当日以降>(T+0～T+5)を計算する。<BR>
     * <BR>
     * 1.出庫変動一覧<当日以降>取得。<BR>
     * <BR>
     * 2.一覧にある出庫変動の数量を以下処理で集計する<BR>
     *   各営業日ごとの処理(n の 取り得る範囲はT+0～T+5)<BR>
     * <BR>
     *   数量集計(n) = Σ(*)出庫変動.数量<BR>
     *     (*)集計条件：<BR>
     *        出庫変動一覧<当日以降>にある出庫変動<BR>
     * 　@ 　@  且つ、出庫変動.is変動期間中(n) = true<BR>
     * <BR>
     * 3.配列: 数量集計[T+0 ...5]を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・預り区分・・・引数.預り区分<BR>
     * ・出庫変動一覧<当日以降>・・・銘柄ごと証券評価.
     * get出庫変動一覧<当日以降>(預り区分)<BR>
     * @@param l_strDepositType - (預り区分)
     * @@return double[]
     * @@roseuid 40B2EC000298
     */
    private double[] calcDeliveryQuantity(String l_strDepositType)
    {

        log.debug(
            "Calculating delivery change quantities, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblQtys = new double[6];
        List l_changes = getDeliveryChanges(l_strDepositType);

        for (int i = 0; i < l_dblQtys.length; i++)
        {

            double l_dblQty = 0.0d;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }

            l_dblQtys[i] = l_dblQty;

        }

        return l_dblQtys;

    }

    /**
     * (calc取引評価額<当日以降>)<BR>
     * ある銘柄の取引変動評価額<当日以降>(T+0～T+5)を計算する。<BR>
     * <BR>
     * 1.取引変動一覧<当日以降>取得。<BR>
     * <BR>
     * 2.一覧にある取引変動の評価額を以下処理で集計する<BR>
     *   各営業日ごとの処理(n の 取り得る範囲はT+0～T+5)<BR>
     * <BR>
     *   評価額集計(n) =Σ(*)取引変動.評価額<BR>
     *     (*)集計条件：<BR>
     *        取引変動一覧<当日以降>にある取引変動<BR>
     *        且つ、取引変動.is変動期間中(n) =true<BR>
     *        且つ、取引変動.売買区分=引数.売買区分<BR>
     *        且つ、取引変動.約定区分=引数.約定区分<BR>
     * <BR>
     * 3.配列: 評価額集計[T+0...5]を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・預り区分・・・引数.預り区分<BR>
     * ・取引変動一覧<当日以降>・・・銘柄ごと証券評価.
     * get取引変動一覧<当日以降>(預り区分)<BR>
     * @@param l_strDepositType - (預り区分)
     * @@param l_strExecType - (約定区分)
     * @@param l_side - (売買区分)
     * @@return double[]
     * @@roseuid 40B30FDC0298
     */
    private double[] calcTransactionValuationPrice(
        String l_strDepositType,
        String l_strExecType,
        SideEnum l_side)
    {

        log.debug(
            "Calculating transaction change prices, condition=[DepositType="
            + l_strDepositType
            + ", ExecType="
            + l_strExecType
            + ", Side="
            + l_side
            + "]");

        double[] l_dblPrices = new double[6];
        List l_changes = getTransactionChanges(l_strDepositType);

        for (int i = 0; i < l_dblPrices.length; i++)
        {

            double l_dblPrice = 0.0;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityTransactionChange l_change =
                    (WEB3TPSecurityTransactionChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate)
                    && l_change.getExecType().equals(l_strExecType)
                    && l_change.getSide().equals(l_side))
                {
                    //預り資産対応（ここで掛目を追加するように修正）
                    l_dblPrice += l_change.getValuationPrice() * l_change.getValuationRatio();
                }
            }

            l_dblPrices[i] = l_dblPrice;

        }

        return l_dblPrices;

    }

    /**
     * (calc取引数量<当日以降>)<BR>
     * ある銘柄の取引変動数量<当日以降>(T+0～T+5)を計算する。<BR>
     * <BR>
     * 1.取引変動一覧<当日以降>取得。<BR>
     * <BR>
     * 2.一覧にある取引変動の数量を以下処理で集計する<BR>
     *   各営業日ごとの処理(n の 取り得る範囲はT+0～T+5)<BR>
     * <BR>
     *   数量集計(n) = Σ(*)取引変動.数量<BR>
     *     (*)集計条件：<BR>
     *        取引変動一覧<当日以降>にある取引変動<BR>
     *        且つ、取引変動.is変動期間中(n) = true<BR>
     *        且つ、取引変動.売買区分=引数.売買区分<BR>
     *        且つ、取引変動.約定区分=引数.約定区分<BR>
     * <BR>
     * 3.配列: 数量集計[T+0...5]を返す。<BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・預り区分・・・引数.預り区分<BR>
     * ・取引変動一覧<当日以降>・・・銘柄ごと証券評価.
     * get取引変動一覧<当日以降>(預り区分)<BR>
     * @@param l_strDepositType - (預り区分)
     * @@param l_strExecType - (約定区分)
     * @@param l_side - (売買区分)
     * @@return double[]
     * @@roseuid 40B30FCD015F
     */
    private double[] calcTransactionQuantity(
        String l_strDepositType,
        String l_strExecType,
        SideEnum l_side)
    {

        log.debug(
            "Calculating transaction change quantities, condition=[DepositType="
            + l_strDepositType
            + ", ExecType="
            + l_strExecType
            + ", Side="
            + l_side
            + "]");

        double[] l_dblQtys = new double[6];
        List l_changes = getTransactionChanges(l_strDepositType);

        for (int i = 0; i < l_dblQtys.length; i++)
        {

            double l_dblQty = 0.0d;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityTransactionChange l_change =
                    (WEB3TPSecurityTransactionChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate)
                    && l_change.getExecType().equals(l_strExecType)
                    && l_change.getSide().equals(l_side))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }

            l_dblQtys[i] = l_dblQty;

        }

        return l_dblQtys;

    }

    /**
     * (calc既存保有評価額<預り資産>)<BR>
     * ある銘柄の既存保有評価額<預り資産>(T+0～T+5)を計算する。<BR>
     * <BR>
     * 1.既存保有変動一覧<確定>取得。<BR>
     * <BR>
     * 2.一覧にある既存保有変動の評価額を以下処理で集計する<BR>
     *   各営業日ごとの処理(n の 取り得る範囲はT+0～ T+5)<BR>
     * <BR>
     *   評価額集計(n) = Σ(*)既存保有変動.評価額 <BR>
     *    (*)集計条件：<BR>
     *       既存保有変動一覧<確定>にある既存保有変動<BR>
     * 　@ 　@ 且つ、既存保有変動.is変動期間中(n) = true<BR>
     * <BR>
     * 3.配列: 評価額集計[T+0 ...5]を返す。<BR>
     * <BR>
     * ※計算式に使用する各値の取得方法@<BR>
     * ・預り区分・・・'保護'<BR>
     * ・既存保有変動一覧<確定>・・・銘柄ごと証券評価.get既存保有変動一覧<確定>(預り区分)<BR>
     * @@return double[]
     */
    private double[] calcDepositAssetValuationPrice()
    {

        double[] l_dblPrices = new double[6];
        List l_changes = getAssetChanges(WEB3TPDepositTypeDef.TRUST);

        for (int i = 0; i < l_dblPrices.length; i++)
        {

            double l_dblPrice = 0.0;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    //掛目は演算しない
                    l_dblPrice += l_change.getValuationPrice();
                }
            }

            l_dblPrices[i] = l_dblPrice;

        }

        return l_dblPrices;

    }

    /**
     * (get既存保有変動一覧<確定>)<BR>
     * <BR>
     * 引数.預り区分に該当する既存保有変動一覧<確定>を返却する。<BR>
     * 注）税区分=5：ストックオプションについて証券評価の対象から除外する。<BR> 
     * よって、返却リストに含めない。 <BR>
     * <BR>
     * １）this.既存保有変動リストの既存保有変動オブジェクトの内、<BR> 
     * 　@引数.預り区分に該当するリストを生成する。 <BR>
     * <BR>
     * 　@[LOOP処理：n=this.既存保有変動リストの要素数回]<BR> 
     * <BR>
     * 　@　@①@this.既存保有変動リストより既存保有変動オブジェクトを取得する。<BR> 
     * <BR>
     * 　@　@　@－既存保有変動 = this.既存保有変動リスト.get(n)<BR> 
     * <BR>
     * 　@　@②既存保有変動を、既存保有変動一覧<確定>に追加する。<BR> 
     * <BR>
     * 　@　@　@[a.既存保有変動.get預り区分()=引数.預り区分<BR>  
     * 　@　@　@　@　@&&  <BR>
     * 　@　@　@　@　@　@既存保有変動.get税区分()≠5：ストックオプション]<BR> 
     * <BR>
     * 　@　@　@　@－既存保有変動一覧<確定>.add(:Object = 既存保有変動)<BR> 
     * <BR>
     * ２）既存保有変動一覧<確定>を返却する。<BR> 
     * <BR>
     * <BR>
     * @@param l_strDepositType - (預り区分)
     * @@return List
     * @@roseuid 40B5C6010062
     */
    protected List getAssetChanges(String l_strDepositType)
    {
        //１）this.既存保有変動リストの既存保有変動オブジェクトの内、
        //引数.預り区分に該当するリストを生成する。
        //[LOOP処理：n=this.既存保有変動リストの要素数回]
        //①@this.既存保有変動リストより既存保有変動オブジェクトを取得する。
        //－既存保有変動 = this.既存保有変動リスト.get(n)
        //②既存保有変動を、既存保有変動一覧<確定>に追加する。
        //[a.既存保有変動.get預り区分()=引数.預り区分
        //&&既存保有変動.get税区分()≠5：ストックオプション]
        //－既存保有変動一覧<確定>.add(:Object = 既存保有変動)
        List l_list = new ArrayList();
        for (Iterator l_it = assetChanges.iterator(); l_it.hasNext(); )
        {
            WEB3TPSecurityChange l_change = (WEB3TPSecurityChange) l_it.next();
            if (l_change.getDepositType().equals(l_strDepositType) && 
                !TaxTypeEnum.STOCK_OPTION.equals(l_change.getTaxType()))
            {
                l_list.add(l_change);
            }
        }
        
        //２）既存保有変動一覧<確定>を返却する。
        return l_list;
    }

    /**
     * (get振替変動一覧<当日以降>)<BR>
     * <BR>
     * 引数.預り区分に該当する振替変動一覧<当日以降>を返却する。<BR> 
     * 注）税区分=5：ストックオプションについて証券評価の対象から除外する。<BR> 
     * よって、返却リストに含めない。 <BR>
     * <BR>
     * １）this.振替変動リストの振替変動オブジェクトの内、<BR> 
　@   * 引数.預り区分に該当するリストを生成する。 <BR>
     * <BR>
　@   * [LOOP処理：n=this.振替変動リストの要素数回]<BR> 
     * <BR>
     * 　@　@①@this.振替変動リストより振替変動オブジェクトを取得する。<BR> 
     * <BR>
     * 　@　@　@－振替変動 = this.振替変動リスト.get(n)<BR> 
     * <BR>
　@　@ * ②振替変動を、振替変動一覧<当日以降>に追加する。<BR> 
     * <BR>
     * 　@　@　@[a.振替変動.get預り区分()=引数.預り区分<BR> 
     * 　@　@　@　@　@&&  <BR>
     * 　@　@　@　@　@振替変動.get税区分()≠5：ストックオプション]<BR> 
     * <BR>
     * 　@　@　@　@－振替変動一覧<当日以降>.add(:Object = 振替変動)<BR> 
     * <BR>
     * ２）振替変動一覧<当日以降>を返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_strDepositType - (預り区分)
     * @@return List
     * @@roseuid 40B5C6010091
     */
    protected List getTransferChanges(String l_strDepositType)
    {
        // １）this.振替変動リストの振替変動オブジェクトの内、
        //引数.預り区分に該当するリストを生成する。
        //[LOOP処理：n=this.振替変動リストの要素数回]
        //①@this.振替変動リストより振替変動オブジェクトを取得する。
        //－振替変動 = this.振替変動リスト.get(n)
        //②振替変動を、振替変動一覧<当日以降>に追加する。
        //　@　@　@[a.振替変動.get預り区分()=引数.預り区分
        //&&振替変動.get税区分()≠5：ストックオプション]
        //－振替変動一覧<当日以降>.add(:Object = 振替変動)
        List l_list = new ArrayList();
        for (Iterator l_it = transferChanges.iterator(); l_it.hasNext(); )
        {
            WEB3TPSecurityChange l_change = (WEB3TPSecurityChange) l_it.next();
            if (l_change.getDepositType().equals(l_strDepositType) && 
                !TaxTypeEnum.STOCK_OPTION.equals(l_change.getTaxType()))
            {
                l_list.add(l_change);
            }
        }
        
        //２）振替変動一覧<当日以降>を返却する。
        return l_list;
    }

    /**
     * (get出庫変動一覧<当日以降>)<BR>
     * <BR>
     * 引数.預り区分に該当する出庫変動一覧<当日以降>を返却する。<BR> 
     * 注）税区分=5：ストックオプションについて証券評価の対象から除外する。<BR> 
     * よって、返却リストに含めない。 <BR>
     * <BR>
     * １）this.出庫変動リストの出庫変動オブジェクトの内、<BR> 
     * 　@引数.預り区分に該当するリストを生成する。 <BR>
     * <BR>
     * 　@[LOOP処理：n=this.出庫変動リストの要素数回]<BR> 
     * <BR>
     * 　@　@①@this.出庫変動リストより出庫変動オブジェクトを取得する。<BR> 
     * <BR>
     * 　@　@　@－出庫変動 = this.出庫変動リスト.get(n)<BR> 
     * <BR>
     * 　@　@②出庫変動を、出庫変動一覧<当日以降>に追加する。<BR> 
     * <BR>
     * 　@　@　@[a.出庫変動.get預り区分()=引数.預り区分<BR> 
     * 　@　@　@　@　@&&  <BR>
     * 　@　@　@　@　@　@出庫変動.get税区分()≠5：ストックオプション]<BR> 
     * <BR>
     * 　@　@　@　@－出庫変動一覧<当日以降>.add(:Object = 出庫変動)<BR> 
     * <BR>
     * ２）出庫変動一覧<当日以降>を返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_strDepositType - (預り区分)
     * @@return List
     * @@roseuid 40B5C60100BF
     */
    protected List getDeliveryChanges(String l_strDepositType)
    {
        //１）this.出庫変動リストの出庫変動オブジェクトの内、
        //引数.預り区分に該当するリストを生成する。
        //[LOOP処理：n=this.出庫変動リストの要素数回]
        //①@this.出庫変動リストより出庫変動オブジェクトを取得する。
        //－出庫変動 = this.出庫変動リスト.get(n)
        //②出庫変動を、出庫変動一覧<当日以降>に追加する。
        //[a.出庫変動.get預り区分()=引数.預り区分
        //&&出庫変動.get税区分()≠5：ストックオプション]
        //－出庫変動一覧<当日以降>.add(:Object = 出庫変動)
        List l_list = new ArrayList();
        for (Iterator l_it = deliveryChanges.iterator(); l_it.hasNext(); )
        {
            WEB3TPSecurityChange l_change = (WEB3TPSecurityChange) l_it.next();
            if (l_change.getDepositType().equals(l_strDepositType) && 
                !TaxTypeEnum.STOCK_OPTION.equals(l_change.getTaxType()))
            {
                l_list.add(l_change);
            }
        }
        
        //２）出庫変動一覧<当日以降>を返却する。
        return l_list;
    }

    /**
     * (get取引変動一覧<当日以降>)<BR>
     * <BR>
     * 引数.預り区分に該当する取引変動一覧<当日以降>を返却する。<BR> 
     * 注）税区分=5：ストックオプションについて証券評価の対象から除外する。<BR> 
     * よって、返却リストに含めない。 <BR>
     * <BR>
     * １）this.取引変動リストの取引変動オブジェクトの内、<BR> 
     * 　@引数.預り区分に該当するリストを生成する。 <BR>
     * <BR>
     * 　@[LOOP処理：n=this.取引変動リストの要素数回]<BR> 
     * <BR>
     * 　@　@①@this.取引変動リストより取引変動オブジェクトを取得する。<BR> 
     * <BR>
     * 　@　@　@－取引変動 = this.取引変動リスト.get(n)<BR> 
     * <BR>
     * 　@　@②取引変動を、取引変動一覧<当日以降>に追加する。<BR> 
     * <BR>
     * 　@　@　@[a.取引変動.get預り区分()=引数.預り区分<BR>
     * 　@　@　@　@　@&&<BR>
     * 　@　@　@　@　@　@取引変動.get税区分()≠5：ストックオプション]<BR> 
     * <BR>
     * 　@　@　@　@－取引変動一覧<当日以降>.add(:Object = 取引変動)<BR> 
     * <BR>
     * ２）取引変動一覧<当日以降>を返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_depositType - (預り区分)
     * @@return List
     * @@roseuid 40B5C7370040
     */
    protected List getTransactionChanges(String l_strDepositType)
    {
        //１）this.取引変動リストの取引変動オブジェクトの内、
        //引数.預り区分に該当するリストを生成する。
        //[LOOP処理：n=this.取引変動リストの要素数回] 
        //①@this.取引変動リストより取引変動オブジェクトを取得する。
        //－取引変動 = this.取引変動リスト.get(n)
        //②取引変動を、取引変動一覧<当日以降>に追加する。
        //[a.取引変動.get預り区分()=引数.預り区分
        //&&取引変動.get税区分()≠5：ストックオプション]
        //－取引変動一覧<当日以降>.add(:Object = 取引変動)
        List l_list = new ArrayList();
        for (Iterator l_it = transactionChanges.iterator(); l_it.hasNext(); )
        {
            WEB3TPSecurityChange l_change = (WEB3TPSecurityChange) l_it.next();
            if (l_change.getDepositType().equals(l_strDepositType) && 
                !TaxTypeEnum.STOCK_OPTION.equals(l_change.getTaxType()))
            {
                l_list.add(l_change);
            }
        }
        
        //２）取引変動一覧<当日以降>を返却する。
        return l_list;
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        String l_str = "\n既存保有:";
        List l_list = new ArrayList();
        for (Iterator l_it = assetChanges.iterator(); l_it.hasNext(); )
        {
            l_str = l_str + l_it.next();
        }

        l_str = l_str + "振替:";
        l_list = new ArrayList();
        for (Iterator l_it = transferChanges.iterator(); l_it.hasNext(); )
        {
            l_str = l_str + l_it.next();
        }

        l_str = l_str + "出庫:";
        l_list = new ArrayList();
        for (Iterator l_it = deliveryChanges.iterator(); l_it.hasNext(); )
        {
            l_str = l_str + l_it.next();
        }

        l_str = l_str + "取引:";
        l_list = new ArrayList();
        for (Iterator l_it = transactionChanges.iterator(); l_it.hasNext(); )
        {
            l_str = l_str + l_it.next();
        }

        return ToStringUtils
            .newToStringBuilder(this)
            .append("product", product)
            .append("securityChanges", l_str)
            .toString();
    }
}
@
