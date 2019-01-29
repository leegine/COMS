head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityValuation.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証券評価額(WEB3TPSecurityValuation.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 山田　@卓司 (FLJ) 新規作成
                    2006/09/14 車進　@　@　@ (中訊)モデルNo.37、No.38、No.39、No.40、No.41
 Revesion History : 2007/07/28 崔遠鵬     (中訊)モデルNo.117
 Revesion History : 2008/01/09 トウ鋒鋼 (中訊) 仕様変更　@モデルNo.244
 Revesion History : 2008/01/10 トウ鋒鋼 (中訊) 仕様変更　@モデルNo.245,246
 Revesion History : 2008/01/22 張騰宇 (中訊) 仕様変更　@モデルNo.234、235
 */
package webbroker3.tradingpower.updtpower.asset;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.data.DailyAssetRow;
import webbroker3.gentrade.data.ExchangeRateRow;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPExecTypeDef;
import webbroker3.tradingpower.define.WEB3TPSplitNewStockDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceCallback;
import webbroker3.tradingpower.updtpower.contract.WEB3TPClosingContractSpec;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * （証券評価額）
 *
 * 全証券評価
 */
public class WEB3TPSecurityValuation
    extends WEB3TPAssetValuation
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPSecurityValuation.class);

    /**
     * ブランク対象銘柄配列
     */
    private final WEB3TPSecurityValuationProduct[] EMPTY_PRODUCTS =
        new WEB3TPSecurityValuationProduct[0];

    /**
     * データベースアクセス管理
     */
    protected WEB3TPPersistentDataManager dataMgr = WEB3TPPersistentDataManager.
        getInstance();

    /**
     * (建玉情報)
     */
    private WEB3TPContractInfo contractInfo;

    /**
     * (対象銘柄リスト)
     */
    private List products;

    /**
     * (銘柄タイプごと対象銘柄リスト）<BR>
     * 銘柄タイプごとに分けられた対象銘柄のリストを保持するマップ<BR>
     * キー：銘柄タイプ<BR>
     * 値：対象銘柄を保持したリスト<BR>
     */
    private Map classifiedProducts;

    /**
     * (銘柄ごと証券評価額マップ)<BR>
     * キー：対象銘柄<BR>
     * 値：銘柄ごと証券評価額<BR>
     */
    private Map valuationPerProducts;

    /**
     * @@roseuid 41087CA2035E
     */
    public WEB3TPSecurityValuation()
    {
        products = new ArrayList();
        classifiedProducts = new HashMap();
        valuationPerProducts = new HashMap();
    }

    /**
     * (create証券評価)<BR>
     * 証券評価インスタンスを作成する。
     * @@param accountInfo - (顧客属性)
     * @@param calcCondition - (計算条件)
     * @@param newOrderSpecs - (現注文内容)
     * @@param contractInfo - (建玉情報)
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation
     * @@roseuid 40F3C85201DB
     */
    public static WEB3TPSecurityValuation create(
        WEB3TPAccountInfo accountInfo,
        WEB3TPCalcCondition calcCondition,
        WEB3TPNewOrderSpec[] newOrderSpecs,
        WEB3TPContractInfo contractInfo)
    {
        WEB3TPSecurityValuation l_valuation = new WEB3TPSecurityValuation();
        l_valuation.setAccountInfo(accountInfo);
        l_valuation.setCalcCondition(calcCondition);
        l_valuation.setNewOrderSpecs(newOrderSpecs);
        l_valuation.setContractInfo(contractInfo);
        return l_valuation;
    }

    /**
     * (calc代用証券評価額)<BR>
     * 引数の指定日における全評価対象銘柄の代用評価額を集計する。<BR>
     * <BR>
     * 1.代用証券の評価額を集計する。<BR>
     *  代用証券評価額(n) ・・・ 株式代用証券評価額(n)<BR>
     *                           + 債券代用証券評価額(n)<BR>
     *                           + 投信代用証券評価額(n)<BR>
     *                           + その他代用証券評価額(n)<BR>
     * 2.計算した代用証券評価額(n)を返す。<BR>
     * <BR>
     * ※ nは、引数の指定日<BR>
     * ※ 計算式に使用する各値の取得方法@<BR>
     *  ・株式代用証券評価額(n) ・・・ 証券評価.calc商品ごと証券評価額(n, 代用,
     * 株式)<BR>
     *  ・債券代用証券評価額(n) ・・・ 証券評価.calc商品ごと証券評価額(n, 代用,
     * 債券)<BR>
     *  ・投信代用証券評価額(n) ・・・ 証券評価.calc商品ごと証券評価額(n, 代用,
     * 投信)<BR>
     *  ・その他代用証券評価額(n) ・・・ 証券評価.calc商品ごと証券評価額(n, 代用,
     * その他)<BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 40ADDB9103E1
     */
    public double calcSubstituteValuationPrice(Date l_datDate)
    {

        // 株式代用証券評価額
        double l_dblEquityVP =
            this.calcValuationPrice(
            l_datDate,
            ProductTypeEnum.EQUITY,
            WEB3TPDepositTypeDef.SUBSTITUTE,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //預り資産対応(ミニ株を含まないため区分の追加) 

        // 債券代用証券評価額
        double l_dblBondVP =
            this.calcValuationPrice(
            l_datDate,
            ProductTypeEnum.BOND,
            WEB3TPDepositTypeDef.SUBSTITUTE,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //預り資産対応(ミニ株を含まないため区分の追加)

        // 投信代用証券評価額
        double l_dblMFVP =
            this.calcValuationPrice(
            l_datDate,
            ProductTypeEnum.MUTUAL_FUND,
            WEB3TPDepositTypeDef.SUBSTITUTE,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //預り資産対応(ミニ株を含まないため区分の追加) 

        // その他代用証券評価額
        double l_dblOtherVP =
            this.calcValuationPrice(
            l_datDate,
            ProductTypeEnum.OTHER,
            WEB3TPDepositTypeDef.SUBSTITUTE,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //預り資産対応(ミニ株を含まないため区分の追加)

        // 代用証券評価額 = 株式代用証券評価額
        //                  + 債券代用証券評価額
        //                  + 投信代用証券評価額
        //                  + 他代用証券評価額
        return l_dblEquityVP + l_dblBondVP + l_dblMFVP + l_dblOtherVP;

    }

    /**
     * (calc代用証券評価額(前日単価評価)) <BR>
     * <BR>
     * 前日単価における全評価対象銘柄の代用評価額を集計する。
     * <BR>
     * １）銘柄タイプごとの代用証券評価額を取得する。
     * <BR>
     * 　@”株式代用証券評価額” = this.calc商品ごと代用証券評価額(前日単価評価)<BR>
     * 　@　@　@　@(ProductTypeEnum = 1：株式) <BR>
     * <BR>
     * 　@”債券代用証券評価額” = this.calc商品ごと代用証券評価額(前日単価評価)<BR>
     * 　@　@　@　@(ProductTypeEnum = 2：債券) <BR>
     * <BR>
     * 　@”投信代用証券評価額” = this.calc商品ごと代用証券評価額(前日単価評価)<BR>
     * 　@　@　@　@(ProductTypeEnum = 3：投資信託) <BR>
     * <BR>
     * 　@”その他代用証券評価額” = this.calc商品ごと代用証券評価額(前日単価評価)<BR>
     * 　@　@　@　@(ProductTypeEnum = 0：その他) <BR>
     * <BR>
     * ２）前日単価にて集計した代用証券評価額を返却する。<BR>
     * 　@返却値：”株式代用証券評価額” + ”債券代用証券評価額” + <BR>
     * 　@　@　@　@　@”投信代用証券評価額” + ”その他代用証券評価額”<BR>
     * <BR>
     * @@return double
     */
    public double calcPrevPriceSubstituteValuation()
    {
        final String STR_METHOD_NAME = "calcPrevPriceSubstituteValuation()";
        log.entering(STR_METHOD_NAME);

        //株式代用証券評価額
        double l_dblEquity = this.calcPrevPricePerProductSubstituteValuation(ProductTypeEnum.EQUITY);
        //債券代用証券評価額
        double l_dblBond = this.calcPrevPricePerProductSubstituteValuation(ProductTypeEnum.BOND);
        //投信代用証券評価額
        double l_dblMutualFund = this.calcPrevPricePerProductSubstituteValuation(ProductTypeEnum.MUTUAL_FUND);
        //その他代用証券評価額
        double l_dblOther = this.calcPrevPricePerProductSubstituteValuation(ProductTypeEnum.OTHER);

        BigDecimal l_bdEquity = new BigDecimal("" + l_dblEquity);
        BigDecimal l_bdBond = new BigDecimal("" + l_dblBond);
        BigDecimal l_bdMutualFund = new BigDecimal("" + l_dblMutualFund);
        BigDecimal l_bdOther = new BigDecimal("" + l_dblOther);
        BigDecimal l_bdValuation = l_bdEquity.add(l_bdBond);
        l_bdValuation = l_bdValuation.add(l_bdMutualFund);
        l_bdValuation = l_bdValuation.add(l_bdOther);

        //返却値：”株式代用証券評価額” + ”債券代用証券評価額” + ”投信代用証券評価額” + ”その他代用証券評価額”
        log.debug(" 前日単価にて集計した代用証券評価額 = " + l_bdValuation.doubleValue());
        log.exiting(STR_METHOD_NAME);
        return l_bdValuation.doubleValue();
    }

    /**
     * （calc預り証券評価額）<BR>
     * 引数で指定日における全評価対象銘柄の預り証券評価額を集計する。<BR>
     * 1. 預り証券の評価額を集計する。<BR>
     *  預り証券評価額(n) = 株式預かり証券評価額(n)<BR>
     *                      + 債券預かり証券評価額(n)<BR>
     *                      + 投信預かり証券評価額(n)<BR>
     *                      + GP預かり証券評価額(n)<BR>
     * 2. 預り証券評価限度額との比較<BR>
     *  預り証券評価額(n) = Min(預り証券評価額(n), 預り証券評価限度額）<BR>
     * 3. 計算した預り証券評価額（n)を返す。<BR>
     * <BR>
     * ※ nは引数で指定された指定日<BR>
     * ※ 計算式に使用する各値の取得方法@<BR>
     *  ・is株式預かり証券評価 ・・・ 計算条件.is預り証券評価制計算対象（株式）<BR>
     *  ・is債券預かり証券評価 ・・・ 計算条件.is預り証券評価制計算対象（債券）<BR>
     *  ・is投信預かり証券評価 ・・・ 計算条件.is預り証券評価制計算対象（投信）<BR>
     *  ・isGP預かり証券評価 ・・・ 計算条件.is預り証券評価制計算対象（GP）<BR>
     *  ・株式預かり証券評価額(n) ・・・ 証券評価.calc商品ごと証券評価額（n, 保護,
     * 株式)<BR>
     *
     * is株式預かり証券評価=falseの場合、株式預かり証券評価額(n)=0<BR>
     *  ・債券預かり証券評価額(n) ・・・ 証券評価.calc商品ごと証券評価額（n, 保護,
     * 債券)<BR>
     *
     * is債券預かり証券評価=falseの場合、債券預かり証券評価額(n)=0<BR>
     *  ・投信預かり証券評価額(n) ・・・ 証券評価.calc商品ごと証券評価額（n, 保護,
     * 投信)<BR>
     *
     * is投信預かり証券評価=falseの場合、投信預かり証券評価額(n)=0<BR>
     *  ・GP預かり証券評価額(n) ・・・ 証券評価.calc商品ごと証券評価額（n, 保護,
     * GP)<BR>
     *
     * isGP預かり証券評価=falseの場合、GP預かり証券評価額(n)=0<BR>
     * @@param l_datDate - (指定日)
     * @@return double
     * @@roseuid 40ADDBA502C8
     */
    public double calcValuationPrice(Date l_datDate)
    {

        // 株式預り証券評価額
        double l_dblEquityVP = 0.0;
        if (getCalcCondition().isEquityEvalDiv())
        {
            l_dblEquityVP =
                calcValuationPrice(
                l_datDate,
                ProductTypeEnum.EQUITY,
                WEB3TPDepositTypeDef.TRUST,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //預り資産対応(ミニ株を含まないため区分の追加) 
        }

        // 債券預り証券評価額
        double l_dblBondVP = 0.0;
        if (getCalcCondition().isBondEvalDiv())
        {
            l_dblBondVP =
                calcValuationPrice(
                l_datDate,
                ProductTypeEnum.BOND,
                WEB3TPDepositTypeDef.TRUST,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //預り資産対応(ミニ株を含まないため区分の追加) 
        }

        // 投信預り証券評価額
        double l_dblMFVP = 0.0;
        if (getCalcCondition().isFundEvalDiv())
        {
            l_dblMFVP =
                calcValuationPrice(
                l_datDate,
                ProductTypeEnum.MUTUAL_FUND,
                WEB3TPDepositTypeDef.TRUST,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //預り資産対応(ミニ株を含まないため区分の追加) 
        }

        // GP証預り券評価額
        double l_dblGPVP = 0.0;
        if (getCalcCondition().isGpEvalDiv())
        {
            l_dblGPVP =
                calcValuationPrice(
                l_datDate,
                ProductTypeEnum.RUITO,
                WEB3TPDepositTypeDef.TRUST,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //預り資産対応(ミニ株を含まないため区分の追加)
        }

        // 預り証券評価額 = 株式預り証券評価額
        //                  + 債券預り証券評価額
        //                  + 投信預り証券評価額
        //                  + GP証預り券評価額
        double l_dblVP = l_dblEquityVP + l_dblBondVP + l_dblMFVP + l_dblGPVP;

        // 預り証券評価額 = Min(預り証券評価額, 預り証券評価限度額)
        return Math.min(l_dblVP, getCalcCondition().getMaxAssessment());

    }

    /**
     * (calc発注分代用証券評価額)<BR>
     * 引数の指定日における評価対象銘柄の発注分代用評価額を集計する。<BR>
     * <BR>
     * 1.発注分代用証券の評価額を集計する。<BR>
     *  発注分代用証券評価額(n) ・・・ 株式発注分代用証券評価額(n)<BR>
     * 2.計算した発注分代用証券評価額(n)を返す。<BR>
     * <BR>
     * ※ nは、引数の指定日<BR>
     * ※ 計算式に使用する各値の取得方法@<BR>
     *  ・株式代用証券評価額(n) ・・・ 証券評価.calc商品ごと証券評価額(n, 代用,
     * 株式,ミニ株でない)<BR>
     * <BR>
     * @@param l_datDate - (指定日)
     * @@return double
     */
    public double calcUnExecSubstituteValuationPrice(Date l_datDate)
    {

        // 株式代用証券評価額
        double l_dblUnExecEquityVP =
            this.calcUnExecValuationPrice(
            l_datDate,
            ProductTypeEnum.EQUITY,
            WEB3TPDepositTypeDef.SUBSTITUTE,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        
        return l_dblUnExecEquityVP;

    }

    /**
     * （calc発注分預り証券評価額）<BR>
     * 引数で指定日における評価対象銘柄の発注分預り証券評価額を集計する。<BR>
     * 1. 発注分預り証券の評価額を集計する。<BR>
     *  発注分預り証券評価額(n) = 株式発注分預かり証券評価額(n)<BR>

     * 2. 預り証券評価限度額との比較<BR>
     *  預り証券評価額(n) = Min(発注分預り証券評価額(n), 預り証券評価限度額）<BR>
     * 3. 計算した発注分預り証券評価額（n)を返す。<BR>
     * <BR>
     * ※ nは引数で指定された指定日<BR>
     * ※ 計算式に使用する各値の取得方法@<BR>
     *  ・is株式預かり証券評価 ・・・ 計算条件.is預り証券評価制計算対象（株式）<BR>
     *  ・株式発注分預かり証券評価額(n) ・・・ 証券評価.calc商品ごと発注分証券評価額（n, 保護,
     * 株式)<BR>
     *
     * is株式預かり証券評価=falseの場合、株式発注分預かり証券評価額(n)=0<BR>
     * @@param l_datDate - (指定日)
     * @@return double
     */
    public double calcUnExecValuationPrice(Date l_datDate)
    {

        // 株式発注分預り証券評価額
        double l_dblUnExecEquityVP = 0.0;
        if (getCalcCondition().isEquityEvalDiv())
        {
            l_dblUnExecEquityVP =
                calcUnExecValuationPrice(
                l_datDate,
                ProductTypeEnum.EQUITY,
                WEB3TPDepositTypeDef.TRUST,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        }

        // 預り証券評価額 = Min(発注分預り証券評価額, 預り証券評価限度額)
        return Math.min(l_dblUnExecEquityVP, getCalcCondition().getMaxAssessment());

    }

    /**
     * (do銘柄ごと証券評価額ロード)<BR>
     * 集計対象ロードする。<BR>
     * 1.証券評価額.do既存保有変動＜確定＞ロード<BR>
     * 2.証券評価額.do振替変動＜当日以降＞ロード<BR>
     * 3.証券評価額.do出庫変動＜当日以降>ロード<BR>
     * 4.証券評価額.do取引変動＜当日以降＞ロード<BR>
     * @@roseuid 40ADDE670047
     */
    public void doSecurityValuationPerProductLoad()
    {
        // 1.証券評価額.do既存保有変動＜確定＞ロード
        this.doAssetChangesLoad();
        // 2.証券評価額.do振替変動＜当日以降＞ロード
        this.doTransferChangesLoad();
        // 3.証券評価額.do出庫変動＜当日以降>ロード
        this.doDeliveryChangesLoad();
        // 4.証券評価額.do取引変動＜当日以降＞ロード
        this.doTransactionChangesLoad();

    }

    /**
     * (get銘柄ごと証券評価額)<BR>
     * 引数で指定された対象銘柄をキーとして、銘柄ごと証券評価額を保持するMapを<BR>
     * 検索し、Mapより取得した銘柄ごと証券評価額を返す。<BR>
     * 該当する銘柄ごと証券評価額がMapに存在しない場合は<code>null</code>を返す。<BR>
     * @@param l_product - (対象銘柄)
     * @@return WEB3TPSecurityValuationPerProduct
     * @@roseuid 40D7E3330165
     */
    public WEB3TPSecurityValuationPerProduct getSecurityValuationPerProduct(
        WEB3TPSecurityValuationProduct l_product)
    {
        WEB3TPSecurityValuationPerProduct l_valuationPerProduct =
            (WEB3TPSecurityValuationPerProduct) valuationPerProducts.get(
            l_product);

        return l_valuationPerProduct;
    }

    /**
     * (calc商品ごと預り資産評価額)<BR>
     * 引数の指定日における評価対象銘柄の預り資産評価額を集計する。<BR>
     * <BR>
     * 1.対象銘柄一覧を取得<BR>
     * 2.引数に指定日（T+0～T+5）を受け取り、一覧内の対象銘柄の指定日に対応した銘柄ご
     * と<BR>
     *  証券評価を集計する<BR>
     *  商品ごと証券評価額(n) = Σ(*)対象銘柄.get銘柄ごと証券評価.get証券評価額(n,
     * 預り区分）<BR>
     *  (*) 集計条件<BR>
     *      一覧にある全ての対象銘柄<BR>
     * 3.計算した商品ごと証券評価額(n)を返す<BR>
     * <BR>
     * ※ nは、引数の指定日<BR>
     * ※ 計算式に使用する各値の取得方法@<BR>
     *  ・預り区分 ・・・ 引数.預り区分<BR>
     *  ・対象銘柄一覧 ・・・ 証券評価.get対象銘柄一覧(銘柄タイプ)<BR>
     * @@param l_datDate - (指定日)
     * @@param l_productType - (銘柄タイプ)
     * @@param l_strMiniStockDivDef - (ミニ株区分)
     * @@return double
     */
    public double calcAssetValuationPrice(
        Date l_datDate,
        ProductTypeEnum l_productType,
        String l_strMiniStockDivDef)
    {

        // 銘柄タイプごと対象銘柄リストを取得
        WEB3TPSecurityValuationProduct[] l_products =
            getProducts(l_productType, l_strMiniStockDivDef);

        // 取得した対象銘柄について評価額を集計
        double l_dblValuationPrice = 0.0;
        for (int i = 0; i < l_products.length; i++)
        {

            // 銘柄ごと預り資産評価額を取得
            WEB3TPSecurityValuationPerProduct l_valuation =
                (WEB3TPSecurityValuationPerProduct) valuationPerProducts.get(
            l_products[i]);

            // 指定日で引数に設定
            l_dblValuationPrice
                += l_valuation.getAssetValuationPrice(l_datDate);

        }

        return l_dblValuationPrice;
    }

    /**
     * (calc商品ごと証券評価額)<BR>
     * 引数の指定日における商品ごと評価対象銘柄の証券評価額を集計する。<BR>
     * <BR>
     * 1.対象銘柄一覧を取得<BR>
     * 2.引数に低指定日（T+0～T+5）を受け取り、一覧内の対象銘柄の指定日に対応した銘柄ご
     * と<BR>
     *  証券評価を集計する<BR>
     *  商品ごと証券評価額(n) = Σ(*)対象銘柄.get銘柄ごと証券評価.get証券評価額(n,
     * 預り区分）<BR>
     *  (*) 集計条件<BR>
     *      一覧にある全ての対象銘柄<BR>
     * 3.計算した商品ごと証券評価額(n)を返す<BR>
     * <BR>
     * ※ nは、引数の指定日<BR>
     * ※ 計算式に使用する各値の取得方法@<BR>
     *  ・預り区分 ・・・ 引数.預り区分<BR>
     *  ・対象銘柄一覧 ・・・ 証券評価.get対象銘柄一覧(銘柄タイプ)<BR>
     * @@param l_datDate - (指定日)
     * @@param l_productType - (銘柄タイプ)
     * @@param l_strDepositType - (預り区分)
     * @@param l_strMiniStockDivDef - (ミニ株区分) 預り資産対応追加
     * @@return double
     * @@roseuid 40ADD82D0306
     */
    protected double calcValuationPrice(
        Date l_datDate,
        ProductTypeEnum l_productType,
        String l_strDepositType,
        String l_strMiniStockDivDef)
    {

        // 銘柄タイプごと対象銘柄リストを取得
        WEB3TPSecurityValuationProduct[] l_products =
            getProducts(l_productType, l_strMiniStockDivDef);

        // 取得した対象銘柄について評価額を集計
        double l_dblValuationPrice = 0.0;
        for (int i = 0; i < l_products.length; i++)
        {

            // 銘柄ごと証券評価額を取得
            WEB3TPSecurityValuationPerProduct l_valuation =
                getSecurityValuationPerProduct(l_products[i]);
            l_dblValuationPrice
                += l_valuation.getValuationPrice(l_datDate, l_strDepositType);

        }

        return l_dblValuationPrice;
    }

    /**
     * (calc商品ごと代用証券評価額(前日単価評価)) <BR>
     * <BR>
     * 評価対象銘柄の代用証券評価額を集計する。<BR>
     * <BR>
     * １）銘柄タイプごとの代用証券評価額を取得する。<BR>
     * 　@”対象銘柄一覧[]” = this.get対象銘柄一覧(:ProductTypeEnum = 引数.銘柄タイプ,<BR>
     * 　@:String = 0：DEFAULT(ミニ株でない)) <BR>
     * <BR>
     * ２）代用証券評価額を集計する。<BR>
     * <BR>
     * [LOOP処理：n=１）で取得した”対象銘柄一覧[]”の要素数回]<BR>
     * <BR>
     * ①@this.銘柄ごと証券評価額マップより対象銘柄オブジェクトに対応する<BR>
     * 銘柄ごと証券評価額オブジェクトを取得する。<BR>
     * <BR>
     * －”銘柄ごと証券評価額” = this.銘柄ごと証券評価額マップ.get(<BR>
     * 　@:Object = ”対象銘柄一覧[n]”) <BR>
     * <BR>
     * ②代用証券評価額を集計する。<BR>
     * <BR>
     * －”代用証券評価額” = ”代用証券評価額” + ”銘柄ごと証券評価額”.<BR>
     * 　@get代用証券評価額(前日単価評価)() <BR>
     * <BR>
     * ３）集計した”代用証券評価額”を返却する。<BR>
     * <BR>
     * 返却値：”代用証券評価額”<BR>
     * <BR>
     * @@param l_productType - 銘柄タイプ<BR>
     * (銘柄タイプ)
     * @@return double
     */
    protected double  calcPrevPricePerProductSubstituteValuation(ProductTypeEnum l_productType)
    {
        final String STR_METHOD_NAME = "calcPrevPricePerProductSubstituteValuation(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //対象銘柄一覧を取得する。
        WEB3TPSecurityValuationProduct[] l_valuationProducts =
            this.getProducts(l_productType, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

        int l_intLength = l_valuationProducts.length;

        //”代用証券評価額”
        double l_dblPrevPriceSubstituteValuation = 0.0D;
        BigDecimal l_bdPrevPriceSubstituteValuation =
            new BigDecimal("" + l_dblPrevPriceSubstituteValuation);

        for (int i = 0; i < l_intLength; i++)
        {
            //this.銘柄ごと証券評価額マップより対象銘柄オブジェクトに対応する
            //銘柄ごと証券評価額オブジェクトを取得する。
            WEB3TPSecurityValuationPerProduct l_valuationPerProduct =
                (WEB3TPSecurityValuationPerProduct)this.valuationPerProducts.get(l_valuationProducts[i]);

            //”銘柄ごと証券評価額”.get代用証券評価額(前日単価評価)()
            double l_dblPrevPrice = l_valuationPerProduct.getPrevPriceSubstituteValuation();
            BigDecimal l_bdPrevPrice = new BigDecimal("" + l_dblPrevPrice);

            //代用証券評価額を集計する
            l_bdPrevPriceSubstituteValuation =
                l_bdPrevPriceSubstituteValuation.add(l_bdPrevPrice);
        }

        //集計した”代用証券評価額”を返却する。
        log.debug(" 代用証券評価額 = " + l_bdPrevPriceSubstituteValuation.doubleValue());
        log.exiting(STR_METHOD_NAME);
        return l_bdPrevPriceSubstituteValuation.doubleValue();
    }

    /**
     * (calc商品ごと発注分証券評価額)<BR>
     * 引数の指定日における商品ごと評価対象銘柄の発注分証券評価額を集計する。<BR>
     * <BR>
     * 1.対象銘柄一覧を取得<BR>
     * 2.引数にて指定日（T+0～T+5）を受け取り、一覧内の対象銘柄の指定日に対応した銘柄ご
     * と<BR>
     *  発注分証券評価を集計する<BR>
     *  商品ごと発注分証券評価額(n) = Σ(*)対象銘柄.get銘柄ごと発注分証券評価.get発注分証券評価額(n,
     * 預り区分）<BR>
     *  (*) 集計条件<BR>
     *      一覧にある全ての対象銘柄<BR>
     * 3.計算した商品ごと発注分証券評価額(n)を返す<BR>
     * <BR>
     * ※ nは、引数の指定日<BR>
     * ※ 計算式に使用する各値の取得方法@<BR>
     *  ・預り区分 ・・・ 引数.預り区分<BR>
     *  ・対象銘柄一覧 ・・・ 証券評価.get対象銘柄一覧(銘柄タイプ)<BR>
     * @@param l_datDate - (指定日)
     * @@param l_productType - (銘柄タイプ)
     * @@param l_strDepositType - (預り区分)
     * @@param l_strMiniStockDivDef - (ミニ株区分) 預り資産対応追加
     * @@return double
     */
    protected double calcUnExecValuationPrice(
        Date l_datDate,
        ProductTypeEnum l_productType,
        String l_strDepositType,
        String l_strMiniStockDivDef)
    {

        // 銘柄タイプごと対象銘柄リストを取得
        WEB3TPSecurityValuationProduct[] l_products =
            getProducts(l_productType, l_strMiniStockDivDef);

        // 取得した対象銘柄について評価額を集計
        double l_dblUnExecValuationPrice = 0.0;
        for (int i = 0; i < l_products.length; i++)
        {

            // 銘柄ごと発注分証券評価額を取得
            WEB3TPSecurityValuationPerProduct l_valuation =
                getSecurityValuationPerProduct(l_products[i]);
            l_dblUnExecValuationPrice
                += l_valuation.getUnExecValuationPrice(l_datDate, l_strDepositType);

        }

        return l_dblUnExecValuationPrice;
    }

    /**
     * (get対象銘柄一覧)<BR>
     * 評価対象銘柄一覧を取得する。<BR>
     * 銘柄タイプごと対象銘柄から引数で指定された銘柄タイプの対象銘柄を取得する。<BR>
     * @@param l_productType - (銘柄タイプ)
     * @@param l_strMiniStockDivDef - (ミニ株区分)
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct[]
     * @@roseuid 40B5A1A303BA
     */
    public WEB3TPSecurityValuationProduct[] getProducts(ProductTypeEnum l_productType, String l_strMiniStockDivDef)
    {
        List l_list = (List) classifiedProducts.get(l_productType);
        if (l_list != null)
        {

            List l_target = new ArrayList();
            
            //ミニ株区分が引数と同一の一覧のみ、配列に設定                
            for (int i = 0; i < l_list.size(); i++)
            {
                WEB3TPSecurityValuationProduct l_product = null;
                l_product = (WEB3TPSecurityValuationProduct) l_list.get(i);
                
                //同一かどうかチェック
                if (l_product.getMiniStockDivDef().equals(l_strMiniStockDivDef))
                {
                    l_target.add(l_product);    
                }

            }

            if (l_target.size() != 0)
            {
                WEB3TPSecurityValuationProduct[] l_products =
                    new WEB3TPSecurityValuationProduct[l_target.size()];     
                
                return (WEB3TPSecurityValuationProduct[])l_target.toArray( l_products );
            }
            else
            {
                return EMPTY_PRODUCTS;
            }

        }
        else
        {
            return EMPTY_PRODUCTS;
        }
    }

    /**
     * (load対象銘柄)<BR>
     * <BR>
     * 対象銘柄をロードする。<BR>
     * <BR>
     * ※シーケンス図「(証券評価額)load対象銘柄」参照<BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_strMiniStockDivDef - (ミニ株区分)
     * @@return WEB3TPSecurityValuationProduct
     * @@roseuid 40D7DDF803A7
     */
    protected WEB3TPSecurityValuationProduct loadProduct(long l_lngProductId, String l_strMiniStockDivDef)
    {

        final String STR_METHOD_NAME = "loadProduct(long l_lngProductId, String l_strMiniStockDivDef)";

        // 銘柄情報を取得
        ProductParams l_pRow =
            dataMgr.getProduct(
            l_lngProductId);

        //計算条件を取得
        WEB3TPCalcCondition l_calcCondition = this.getCalcCondition();
        
        //評価単価Callbackを取得
        WEB3TPUnitPriceCallback l_unitPriceCallback = l_calcCondition.getUnitPriceCallback();
        
        // 対象銘柄のインスタンスを生成
        WEB3TPSecurityValuationProduct l_product =
            WEB3TPSecurityValuationProduct.create();
        l_product.setProductId(l_lngProductId);
        l_product.setProductType(l_pRow.getProductType());
        l_product.setUnitSize(l_pRow.getCalcSize());
        l_product.setSubstituteValuationRatio(l_pRow.getMarginRatio() / 100);
        l_product.setValuationRatio(l_pRow.getSecuritiesEstimationRatio() / 100);
        l_product.setPrimaryMarketId(l_pRow.getPrimaryMarketId());
//            l_product.setNotRequiredFlag(false);
        l_product.setMiniStockDivDef(l_strMiniStockDivDef); //預り資産対応（ミニ株区分追加）
        //評価単価を求める
        double l_dblUnitPrice = l_unitPriceCallback.getUnitPrice(l_pRow);
        l_product.setUnitPrice(l_dblUnitPrice);
        l_product.setPrePrice(l_pRow.getEstimationPrice());
        
//            // 株式の場合
//            if (ProductTypeEnum.EQUITY.equals(l_product.getProductType()))
//            {
//                // 取引銘柄を取得
//                List l_tpRows =
//                    dataMgr
//                    .getEqTypeTradedProduct(
//                    l_lngProductId,
//                    this);
//                if (l_tpRows != null && l_tpRows.size() > 0)
//                {
//                    if (l_tpRows.get(0) instanceof EqtypeTradedProductRow)
//                    {
//                        for (Iterator l_it = l_tpRows.iterator(); l_it.hasNext(); )
//                           {
//                            // 取引銘柄が上場区分=店頭かつ、店頭公開区分=管理の場合
//                            EqtypeTradedProductRow tpRow =
//                                (EqtypeTradedProductRow) l_it.next();
//                            if (WEB3ListTypeDef.OTC.equals(tpRow.getListType())
//                                && WEB3OpenOtcDivDef.MANAGEMENT_PRODUCT.equals(
//                                tpRow.getOpenOtcDiv()))
//                            {
//                                // 評価対象外フラグ=trueに設定
//                                l_product.setNotRequiredFlag(true);
//                                break;
//                            }
//                        }
//                    }
//                    else if (l_tpRows.get(0) instanceof EqtypeTradedProductUpdqRow)
//                    {
//                        for (Iterator l_it = l_tpRows.iterator(); l_it.hasNext(); )
//                        {
//                            // 取引銘柄が上場区分=店頭かつ、店頭公開区分=管理の場合
//                            EqtypeTradedProductUpdqRow tpRow =
//                                (EqtypeTradedProductUpdqRow) l_it.next();
//                            if (WEB3ListTypeDef.OTC.equals(tpRow.getListType())
//                                && WEB3OpenOtcDivDef.MANAGEMENT_PRODUCT.equals(
//                                tpRow.getOpenOtcDiv()))
//                            {
//                                // 評価対象外フラグ=trueに設定
//                                l_product.setNotRequiredFlag(true);
//                                break;
//                            }
//                        }
//                    }
//                }
//            }

        // 生成した対象銘柄のインスタンスを返す
        return l_product;

    }

    /**
     * (do既存保有変動<確定>ロード)<BR>
     * <BR>
     * 既存保有変動<確定>をデータロードする。<BR> 
     * <BR>
     * ※シーケンス図「(証券評価額)do既存保有変動<確定>ロード」参照<BR>
     * <BR>
     * @@roseuid 40B6F6D4004D
     */
    protected void doAssetChangesLoad()
    {

        String ls_MiniStockDiv = null;

        // 1.既存保有変動＜確定＞（GP以外）をロードする
        // 1.1預り証券関連データリソースアクセス管理.get既存受渡日別保有資産レコード(顧客属性)
        List l_dayAssets = dataMgr.getDayAssets(getAccountInfo());
        
        // 1.2受渡日別保有資産テーブルにレコードが存在する場合
        if (l_dayAssets != null)
        {
            log.debug(l_dayAssets.size() + " day asset row found.");
            // 1.2.1 LOOP処理：get既存受渡日別保有資産レコード()の戻り値の要素数回
            for (Iterator l_it = l_dayAssets.iterator(); l_it.hasNext(); )
            {

                DailyAssetRow l_dayAsset = (DailyAssetRow) l_it.next();

                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                //ミニ株区分を取得(預り資産対応により追加) 
                if (l_dayAsset.getMiniStockDiv().equals(BooleanEnum.TRUE))
                {
                    //ミニ株の場合
                    ls_MiniStockDiv = WEB3MiniStockDivDef.MINI_STOCK;
                }
                else
                {
                    //ミニ株ではない場合
                    ls_MiniStockDiv = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
                }

                // 1.2.1.1 ロード対象銘柄がロード済みかどうか判定する。
                boolean isLoadedProduct = this.isLoadedProduct(l_dayAsset.
                    getProductId(), ls_MiniStockDiv);

                // 1.2.1.2 ロード対象銘柄がロード未済の場合
                // (isロード済み対象銘柄() == false)
                if (!isLoadedProduct)
                {
                    // 1.2.1.2.1 load対象銘柄(銘柄ID)
                    // 引数の銘柄IDは既存受渡日別保有資産.銘柄ID
                    // ミニ株区分追加（預り資産対応）
                    l_product =
                        loadProduct(l_dayAsset.getProductId(), ls_MiniStockDiv);

                    // 1.2.1.2.2 銘柄ごと証券評価.create銘柄ごと証券評価（対象銘柄）
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.2.1.2.3 add銘柄ごと証券評価(対象銘柄, 銘柄ごと証券評価)
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                // 1.2.1.3 以外（ロード対象銘柄がロード済）の場合
                else
                {
                    // 1.2.1.3.1 get対象銘柄(銘柄ID)
                    // 引数の銘柄IDは資産移動テーブル.銘柄ID
                    // ミニ株区分追加（預り資産対応）
                    l_product =
                        getProduct(l_dayAsset.getProductId(), ls_MiniStockDiv);

                    // 1.2.1.3.2 get銘柄ごと証券評価(対象銘柄)
                    // 引数の対象銘柄は get対象銘柄()の戻り値 
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }

                // 1.2.1.4.既存保有変動.create既存保有変動()
                WEB3TPSecurityAssetChange l_change =
                    WEB3TPSecurityAssetChange.create();
                
                // 1.2.1.5 余力計算条件をセットする。
                // 余力計算条件 = this.get余力計算条件()の戻り値
                l_change.setCalcCondition(getCalcCondition());

                // 1.2.1.6 受渡日別保有資産テーブル.分割新株式 = "1:分割新株式"の場合             
                if(WEB3TPSplitNewStockDef.SPLIT_NEW_STOCK.equals(l_dayAsset.getSplitNewStockDiv()) == true)
                {
                    // 1.2.1.6.1 既存保有変動.set分割新株式区分(boolean)
                    l_change.setSplitNewStock(true);
                }
                // 1.2.1.7以外の（分割新株式でない）場合
                else
                {
                    // 1.2.1.7.1 既存保有変動.set分割新株式区分(boolean)
                    l_change.setSplitNewStock(false);
                }
                
                // 1.2.1.8 既存保有変動.set預り区分(預り区分)
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_dayAsset.getSubAccountId()));
                
                // 1.2.1.9 既存保有変動.set原約定日(Timestamp)
                l_change.setOriginalExecDate(l_dayAsset.getOriginalExecDate());
                
                // 1.2.1.10 既存保有変動.calc変動反映日(受渡日)
                // 引数の受渡日は、受渡日別残高テーブル.受渡日
                l_change.calcReflectDay(l_dayAsset.getDeliveryDate());

                // 1.2.1.11 既存保有変動.set税区分(税区分)
                // 引数の税区分は、受渡日別保有資産Row.税区分
                l_change.setTaxType(l_dayAsset.getTaxType());
                
                // 1.2.1.12 税区分を変換する。 
                // 1.2.1.13 既存保有変動.set特定口座区分(税区分)
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(l_dayAsset.
                    getTaxType()));

                // 1.2.1.14 既存保有変動.set変動数量(数量)
                l_change.setQuantity(l_dayAsset.getQuantity());

                // 1.2.1.15.既存保有変動.set評価掛目(評価掛目)
                l_change.setValuationRatio(
                    l_product.getValuationRatio(l_change.getDepositType()));
                
                // 1.2.1.16 投信信託の場合
                //投信信託の場合
                //(対象銘柄.銘柄タイプ == ３：投資信託
                //外国株式の場合
                //対象銘柄.銘柄タイプ == ４：外国株
                if(ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType())
                    || ProductTypeEnum.FOREIGN_EQUITY.equals(l_product.getProductType()))
                {
                    // 1.2.1.16.1 評価単価をセットする。
                    // 引数double = 対象銘柄.get評価単価()の戻り値 
                    l_change.setUnitPrice(
                            l_product.getUnitPrice());                	
                }
                // 以外（投信信託でない、かつ、外国株式でない）の場合
                else
                {
                    // 1.2.1.17.1 評価単価をセットする。
                    // 引数double = 対象銘柄.get評価単価() × 対象銘柄.get計算単位() 
                    l_change.setUnitPrice(
                            l_product.getUnitPrice() * l_product.getUnitSize());                	
                }                
                
                //1.2.1.16 投信信託の場合
                if(ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
                {
                    // 1.2.1.16.2 投信信託資産評価額を計算する。 
                    // 1.2.1.16.3 評価額をセットする。
                	l_change.setValuationPrice(
                            calcMutualFundValuationPricePerChange(l_product, l_change)
                            );                	                	                	
                }
                //外国株式の場合
                //対象銘柄.銘柄タイプ == ４：外国株
                else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_product.getProductType()))
                {
                    //set評価額
                    //[引数]
                    //評価額 = calc外国株式評価額()の戻り値
                    l_change.setValuationPrice(calcFeqValuation(l_product, l_change));
                }
                else
                {
                    // 1.2.1.17.2 評価単価をセットする。
                    // 引数 double = 既存保有変動.get変動数量() * 既存保有変動.get評価単価() 
                	l_change.setValuationPrice(
                    l_change.getQuantity()
                    * l_change.getUnitPrice());                	                	
                }
                
                // 1.2.1.18 銘柄ごと証券変動.add証券変動(証券変動)
                l_valuation.addSecurityChange(l_change);
            }
        }
        else
        {
            log.debug(" day asset row not found.");
        }

        // 1.3 保有資産テーブルRowオブジェクトのリストを取得する。 
        // 引数:顧客属性 = this.get顧客属性()の戻り値 
        List l_gpAssets =
            dataMgr.getAssets(
            getAccountInfo());
        
        // 1.4 保有資産テーブルにレコードが存在する場合
        //（get既存保有資産レコード()　@!= null）
        if (l_gpAssets != null)
        {
            log.debug(l_gpAssets.size() + " GP asset row found.");

            // 1.4.1 LOOP処理：get既存保有資産レコード()の戻り値の要素数回
            for (Iterator l_it = l_gpAssets.iterator(); l_it.hasNext(); )
            {
                AssetRow l_gpAsset = (AssetRow) l_it.next();

                // 1.4.1.1 load対象銘柄(銘柄ID)
                //     ミニ株区分追加（預り資産対応）
                WEB3TPSecurityValuationProduct l_product =
                    loadProduct(l_gpAsset.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.4.1.2 銘柄ごと証券評価.create銘柄ごと証券評価（対象銘柄）
                WEB3TPSecurityValuationPerProduct l_valuation =
                    WEB3TPSecurityValuationPerProduct.create(l_product, this);

                // 1.4.1.3 add銘柄ごと証券評価(対象銘柄, 銘柄ごと証券評価)
                addSecurityValuationPerProduct(l_product, l_valuation);

                // 1.4.1.4 既存保有変動.create既存保有変動()
                WEB3TPSecurityAssetChange l_change =
                    WEB3TPSecurityAssetChange.create();
                
                // 1.4.1.5 既存保有変動.set余力計算条件(余力計算条件)
                l_change.setCalcCondition(getCalcCondition());

                // 1.4.1.6 既存保有変動.calc変動反映日(T+0))
                l_change.calcReflectDay(getCalcCondition().getBizDate(0));

                // 1.4.1.7 既存保有変動.set預り区分(預り区分)
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_gpAsset.getSubAccountId()));

                // 1.4.1.8 既存保有変動.set税区分(税区分)
                l_change.setTaxType(l_gpAsset.getTaxType());
                
                // 1.4.1.9  既存保有変動.to特定口座区分(税区分)
                // 1.4.1.10 既存保有変動.set特定口座区分(税区分)
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(l_gpAsset.
                    getTaxType()));

                // 1.4.1.11 既存保有変動.set変動数量(数量) 数量=確定保有口数-ペナルティ空け前保有数量*0.001 小数点以下捨
                l_change.setQuantity(l_gpAsset.getQuantity() -
                                     Math.floor(l_gpAsset.getCountBeforePenalty() * 0.001));

                // 1.4.1.12 既存保有変動.set評価単価(単価)
                l_change.setUnitPrice(
                        l_product.getUnitPrice() * l_product.getUnitSize());                	                

                // 1.4.1.13 既存保有変動.set評価掛目(評価掛目)
                l_change.setValuationRatio(
                    l_product.getValuationRatio(l_change.getDepositType()));

                // 1.4.1.14 既存保有変動.set評価額(評価額)
                l_change.setValuationPrice(
                    l_change.getQuantity()
                    * l_change.getUnitPrice());                

                // 1.4.1.15 銘柄ごと証券変動.add証券変動(証券変動)
                l_valuation.addSecurityChange(l_change);
            }
        }
        else
        {
            log.debug("GP asset row not found.");
        }
    }

    /**
     * (do振替変動<当日以降>ロード)<BR>
     * 振替変動<当日以降>をロードする。<BR>
     * <BR>
     * 基本設計シーケンス「do振替変動<当日以降>ロード」を参照する
     * <BR>
     */    
    protected void doTransferChangesLoad()
    {
        // 1.1 預り証券関連データリソースアクセス管理.get資産移動レコード<振替>()
        List l_assetTransfers = dataMgr.getAssetTransferAioOrderUnits(this);
        
        // 1.2 AIO注文単位テーブルに振替レコードが存在する場合
        //（get資産移動<振替>レコード()　@!= null）
        if (l_assetTransfers != null)
        {
            log.debug(l_assetTransfers.size() + " asset Transfer row found.");

            // 1.2.1 取得した資産移動テーブルの各レコードに対して以下の処理を繰り返す。
            for (Iterator l_it = l_assetTransfers.iterator(); l_it.hasNext(); )
            {
                AioOrderUnitParams l_assetTransfer = (AioOrderUnitParams) l_it.next();
                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                // 1.2.1.1 is存在ロード済み対象銘柄(銘柄ID
                //  引数の銘柄IDは資産移動テーブル.銘柄ID
                boolean isLoadedProduct = this.isLoadedProduct(l_assetTransfer.
                    getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.2.1.2 is存在ロード済み対象銘柄(銘柄ID) = falseの場合
                if (!isLoadedProduct)
                {
                    // 1.2.1.2.1 load対象銘柄(銘柄ID)
                    // 引数の銘柄IDは資産移動テーブル.銘柄ID
                    // ミニ株区分追加（預り資産対応）
                    l_product =
                        loadProduct(l_assetTransfer.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.2.2 銘柄ごと証券評価.create銘柄ごと証券評価(対象銘柄)
                    // 引数の対象銘柄は、「2.2.1」の戻り値
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.2.1.2.3 add銘柄ごと証券評価（対象銘柄, 銘柄ごと証券評価)
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                // 1.2.1.3 is存在ロード済み対象銘柄(銘柄ID) = trueの場合
                else
                {
                    // 1.2.1.3.1 get対象銘柄(銘柄ID)
                    // 引数の銘柄IDは資産移動テーブル.銘柄ID
                    // ミニ株区分追加（預り資産対応）
                    l_product =
                        getProduct(l_assetTransfer.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.3.2 get銘柄ごと証券評価(対象銘柄)
                    // 引数の対象銘柄は「5.1」の戻り値
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }

                // 1.2.1.4  振替変動.create振替変動()
                WEB3TPSecurityTransferChange l_change =
                    WEB3TPSecurityTransferChange.create();
                
                // 1.2.1.5 振替変動.set余力計算条件(余力計算条件)
                l_change.setCalcCondition(getCalcCondition());

                //  1.2.1.6 振替変動.set預り区分(預り区分)
                // 引数の預り区分は、証券評価額.顧客属性.get預り区分（資産移動テーブル.補助口座ID）
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_assetTransfer.getSubAccountId()));

                //  1.2.1.7 振替変動.calc変動反映日(受渡日)
                //   振替変動は「2.4」の戻り値
                // 　@引数の受渡日は、null
                l_change.calcReflectDay(null);

                //  1.2.1.8 振替変動.set税区分(税区分)
                l_change.setTaxType(TaxTypeEnum.UNDEFINED);
                
                // 1.2.1.9 to特定口座区分(TaxTypeEnum)
                // 1.2.1.10 振替変動.set特定口座区分(特定口座区分)
                //  引数の税区分は、資産移動テーブル.税区分
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(TaxTypeEnum.
                    UNDEFINED));

                // 1.2.1.11 振替変動.set変動数量(変動数量)
                l_change.setQuantity(l_assetTransfer.getQuantity());


                // 引数の単価は、対象銘柄.評価単価×対象銘柄.計算単位
                //投信対応(20050401)：計算単位は数量に対して作用するので
                //評価単価に乗算しない 
                //1.2.1.13投信信託の場合
                //(対象銘柄.銘柄タイプ == ３：投資信託)
                if (ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
                {
                    //1.2.1.13.1 振替変動.set評価単価(評価単価)
                    l_change.setUnitPrice(l_product.getUnitPrice());
                }
                //1.2.1.14 以外（投信信託でない）の場合
                else
                {
                    l_change.setUnitPrice(l_product.getUnitPrice() * l_product.getUnitSize());
                }
                log.debug("ProductType=" + l_product.getProductType() + " UnitPrice=" + l_change.getUnitPrice());

                // 1.2.1.12  振替変動.set評価掛目(評価掛目)
                //   引数の評価掛目は「8.」の預り区分が「代用」の場合、対象銘柄.代用掛目
                //   「保護」の場合、対象銘柄.預り証券評価掛目
                l_change.setValuationRatio(
                    l_product.getValuationRatio(l_change.getDepositType()));

                //   引数の評価額は、変動数量×評価単価×評価掛目
                //投信対応(20050401)：計算単位は数量に対して作用する
                //1.2.1.13投信信託の場合
                //(対象銘柄.銘柄タイプ == ３：投資信託)
                if (ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
                {
                    //1.2.1.13.3 振替変動.set評価単価(評価単価)
                    l_change.setValuationPrice(
                            calcMutualFundValuationPricePerChange(l_product, l_change)
                            );
                }
                //1.2.1.14 以外（投信信託でない）の場合
                else
                {
                    l_change.setValuationPrice(l_change.getQuantity() * l_change.getUnitPrice());
                }
           	                
                // 1.2.1.15 銘柄ごと証券評価.add証券変動(証券変動)
                l_valuation.addSecurityChange(l_change);
            }
        }
        else
        {
            log.debug(" asset Transfer row not found.");
        }
    }

    /**
     * (do出庫変動<当日以降>ロード)<BR>
     * 出庫変動<当日以降>をロードする。<BR>
     * <BR>
     * 基本設計シーケンス「do出庫変動<当日以降>ロード」を参照する
     * <BR>
     */
    protected void doDeliveryChangesLoad()
    {
        // 1.1 預り証券関連データリソースアクセス管理.get資産移動レコード<出庫>(),
        List l_assetOuts = dataMgr.getAssetOutAioOrderUnits(this);

        // 1.2 AIO注文単位テーブルに出庫レコードが存在する場合
        //（get資産移動<出庫>レコード()　@!
        if (l_assetOuts != null)
        {
            log.debug(l_assetOuts.size() + " asset out row found.");

            // 1.2.1 LOOP処理：get資産移動<出庫>レコード()の戻り値の要素数回
            for (Iterator l_it = l_assetOuts.iterator(); l_it.hasNext(); )
            {
                AioOrderUnitParams l_assetOut = (AioOrderUnitParams) l_it.next();
                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                // 1.2.1.1 is存在ロード済み対象銘柄(銘柄ID）
                //  引数の銘柄IDは資産移動テーブル.銘柄ID
                boolean isLoadedProduct = this.isLoadedProduct(l_assetOut.getProductId(), 
                                                                 WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.2.1.2 is存在ロード済み対象銘柄(銘柄ID) = falseの場合
                if (!isLoadedProduct)
                {
                    //1.2.1.2.1 load対象銘柄(銘柄ID)
                    // 引数の銘柄IDは資産移動テーブル.銘柄ID
                    //   ミニ株区分追加（預り資産対応）
                    l_product =
                        loadProduct(l_assetOut.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.2.2 銘柄ごと証券評価.create銘柄ごと証券評価(対象銘柄)
                    // 引数の対象銘柄は、「4.1.」の戻り値
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.2.1.2.3 add銘柄ごと証券評価（対象銘柄, 銘柄ごと証券評価)
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                //1.2.1.3 is存在ロード済み対象銘柄(銘柄ID) = trueの場合
                else
                {
                    // 1.2.1.3.1 get対象銘柄(銘柄ID)
                    // 引数の銘柄IDは資産移動テーブル.銘柄ID
                    // ミニ株区分追加（預り資産対応）
                    l_product =
                        getProduct(l_assetOut.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.3..2 get銘柄ごと証券評価(対象銘柄)
                    // 引数の対象銘柄は「5.1」の戻り値
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }
                // 1.2.1.4 出庫変動.create出庫変動()
                WEB3TPSecurityDeliveryChange l_change =
                    WEB3TPSecurityDeliveryChange.create();
                
                // 1.2.1.5 出庫変動. set余力計算条件(余力計算条件)
                l_change.setCalcCondition(getCalcCondition());

                // 1.2.1.6 出庫変動.set預り区分(預り区分)
                // 引数の預り区分は、証券評価額.顧客属性.get預り区分（資産移動テーブル.補助口座ID）
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_assetOut.getSubAccountId()));

                // 1.2.1.7 出庫変動.calc変動反映日(受渡日)
                //   出庫変動は「6.」の戻り値
                // 　@引数の受渡日は、null
                l_change.calcReflectDay(null);

                //  1.2.1.8 出庫変動.set税区分 (税区分)
                l_change.setTaxType(TaxTypeEnum.UNDEFINED);
                
                // 1.2.1.9 to特定口座区分(TaxTypeEnum)
                // 1.2.1.10 出庫変動.set特定口座区分(特定口座区分)
                //  引数の税区分は、資産移動テーブル.税区分
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(TaxTypeEnum.
                    UNDEFINED));

                // 1.2.1.11出庫変動.set変動数量(変動数量)
                l_change.setQuantity(l_assetOut.getQuantity());

                // 引数の単価は、対象銘柄.評価単価×対象銘柄.計算単位
                //投信対応(20050401)：計算単位は数量に対して作用するので
                //評価単価に乗算しない  
                // 1.2.1.13投信信託の場合(対象銘柄.銘柄タイプ == ３：投資信託)
                if (ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
                {
                    //1.2.1.13.1 出庫変動.set評価単価(評価単価)
                    l_change.setUnitPrice(l_product.getUnitPrice());
                }
                //1.2.1.14以外（投信信託でない）の場合
                else
                {
                    // 1.2.1.14.1 出庫変動.set評価単価(評価単価)
                    l_change.setUnitPrice(l_product.getUnitPrice() * l_product.getUnitSize());
                }
                
                // 1.2.1.12 出庫変動.set評価掛目(評価掛目)
                l_change.setValuationRatio(
                    l_product.getValuationRatio(l_change.getDepositType()));

                //   引数の評価額は、変動数量×評価単価×評価掛目
                //投信対応(20050401)：計算単位は数量に対して作用する
                // 1.2.1.13投信信託の場合(対象銘柄.銘柄タイプ == ３：投資信託)
                if (ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
                {
                    //1.2.1.13.3 出庫変動.set評価額(評価額)
                    l_change.setValuationPrice(
                            calcMutualFundValuationPricePerChange(l_product, l_change)
                            );
                }
                //1.2.1.14以外（投信信託でない）の場合
                else
                {
                    // 1.2.1.14.2 出庫変動.set評価額(評価額)
                    l_change.setValuationPrice(l_change.getQuantity() * l_change.getUnitPrice());
                }
                
                // 1.2.1.15 銘柄ごと証券変動.add証券変動(証券変動)
                l_valuation.addSecurityChange(l_change);
            }
        }
        else
        {
            log.debug(" asset out row not found.");
        }

    }

    /**
     * (do取引変動<当日以降>ロード)<BR>
     * <BR>
     * 取引変動<当日以降>をデータロードする。<BR> 
     * <BR>
     * ※シーケンス図「(証券評価額)do取引変動<当日以降>ロード～GP～」参照 <BR>
     * ※シーケンス図「(証券評価額)do取引変動<当日以降>ロード～株式～」参照<BR>
     * <BR>
     * @@roseuid 40B6FADE032B
     */
    protected void doTransactionChangesLoad()
    {
        //評価単価Callbackを取得
        WEB3TPUnitPriceCallback l_unitPriceCallback = getCalcCondition().getUnitPriceCallback();

        // 1.1 預り証券データリソースアクセス管理.getGP取引レコード(資産変動)
        List l_ruitoOrderUnits = dataMgr.getRuitoOrderUnits(this);
        
        // 1.2 累投注文単位テーブルにレコードが存在する場合
        //（getGP取引レコード()　@!= null）
        if (l_ruitoOrderUnits != null)
        {
            log.debug(l_ruitoOrderUnits.size() + " ruito order unit row found.");

            // 1.2.1 LOOP処理：getGP取引レコード()の戻り値の要素数回
            for (Iterator l_it = l_ruitoOrderUnits.iterator(); l_it.hasNext(); )
            {
                RuitoOrderUnitRow l_ruitoOrderUnit = (RuitoOrderUnitRow) l_it.next();
                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                // 1.2.1.1 is存在ロード済み対象銘柄(銘柄ID）
                //   引数の銘柄IDは累投注文単位テーブル.銘柄ID
                boolean isLoadedProduct = this.isLoadedProduct(l_ruitoOrderUnit.
                    getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.2.1.2 is存在ロード済み対象銘柄(銘柄ID) = falseの場合、
                if (!isLoadedProduct)
                {
                    // 1.2.1.2.1 load対象銘柄(銘柄ID)
                    //   引数の銘柄IDは累投注文単位テーブル.銘柄ID
                    //   ミニ株区分追加（預り資産対応）
                    l_product =
                        loadProduct(l_ruitoOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.2.2 銘柄ごと証券評価.create銘柄ごと証券評価(対象銘柄)
                    //   引数の対象銘柄は、「4.1.」の戻り値
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.2.1.2.3 add銘柄ごと証券評価（対象銘柄, 銘柄ごと証券評価)
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                // 1.2.1.3 is存在ロード済み対象銘柄(銘柄ID) = trueの場合、
                else
                {
                    // 1.2.1.3.1 get対象銘柄(銘柄ID)
                    //   引数の銘柄IDは累投注文単位テーブル.銘柄ID
                    //   ミニ株区分追加（預り資産対応）
                    l_product =
                        getProduct(l_ruitoOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.3.2 get銘柄ごと証券評価(対象銘柄)
                    //   引数の対象銘柄は「2.3.1」の戻り値
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }

                // 1.2.1.4 取引変動.create取引変動()
                WEB3TPSecurityTransactionChange l_change =
                    WEB3TPSecurityTransactionChange.create();
                
                // 1.2.1.5 取引変動.set余力計算条件(余力計算条件)
                l_change.setCalcCondition(getCalcCondition()); 
                
                // 1.2.1.6 取引変動.set銘柄タイプ(ProductTypeEnum)
                // 引数:ProductTypeEnum = 対象銘柄.get銘柄タイプ() 
                l_change.setProductType(l_product.getProductType());

                // 1.2.1.7 取引変動.set預り区分(預り区分)
                //   取引変動は、｢1.6｣の戻り値
                // 引数の預り区分は、get顧客属性().get預り区分(累等注文単位テーブル.補助口座ID)
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_ruitoOrderUnit.getSubAccountId()));

                // 1.2.1.8 取引変動.set税区分(税区分)
                l_change.setTaxType(l_ruitoOrderUnit.getTaxType());
                
                // 1.2.1.9 取引変動.to特定口座区分(TaxTypeEnum)
                //   引数の特定口座区分は、累等注文単位テーブル.税区分
                // 1.2.1.10 取引変動.set特定口座区分(特定口座区分)
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(
                    l_ruitoOrderUnit.getTaxType()));

                // 1.2.1.11 取引変動.set注文カテゴリ(注文カテゴリ)
                //   引数の注文カテゴリは、累等注文単位テーブル.注文カテゴリ
                l_change.setOrderCateg(l_ruitoOrderUnit.getOrderCateg());

                // 1.2.1.12 取引変動.set約定区分(約定区分)
                //   引数の約定区分は、未約定
                l_change.setExecType(WEB3TPExecTypeDef.UNEXECUTED);

                // 1.2.1.13 取引変動.set売買区分(売買区分)
                //   引数の売買区分は、SideEnum.get売買区分(累投注文単位テーブル.注文種別)
                l_change.setSide(SideEnum.getSide(l_ruitoOrderUnit.getOrderType()));

                // 1.2.1.14 取引変動.set変動数量(変動数量)
                //   引数の変動数量は、累投注文単位テーブル.数量
                l_change.setQuantity(l_ruitoOrderUnit.getQuantity());

                // 1.2.1.15 取引変動.set評価単価(評価単価)
                //   引数の評価単価は、対象銘柄.get評価単価()×対象銘柄.get計算単位()
                l_change.setUnitPrice(
                        l_product.getUnitPrice() * l_product.getUnitSize());                	

                // 1.2.1.16 取引変動.set評価掛目(評価掛目)
                //   引数の評価掛目は、対象銘柄.get証券評価掛目()
                l_change.setValuationRatio(
                    l_product.getValuationRatio(WEB3TPDepositTypeDef.TRUST));

                // 1.2.1.17 取引変動.calc変動反映日(受渡日)
                //   引数の受渡日は、累等注文単位テーブル.受渡日
                l_change.calcReflectDay(l_ruitoOrderUnit.getDeliveryDate());

                // 1.2.1.18 取引変動.set評価額(評価額)
                //   引数の評価額は、変動数量×評価単価×評価掛目
                l_change.setValuationPrice(
                    l_change.getQuantity()
                    * l_change.getUnitPrice());

                // 1.2.1.19 銘柄ごと証券評価額.add証券変動(証券変動)
                //   銘柄ごと証券評価額に生成した取引変動オブジェクトを追加する。 
                //   証券変動 = create取引変動()の戻り値
                l_valuation.addSecurityChange(l_change);

            }
        }
        else
        {
            log.debug(" ruito order unit row not found.");
        }

        // 1.3 取引変動<当日以降>(株式)をロードする。
        // 預り証券データリソースアクセス管理.get株式取引レコード(資産評価）
        List l_equityOrderUnits = dataMgr.getEqTypeOrderUnits(this);
        
        // 1.4 株式注文単位テーブルにレコードが存在する場合
        //（get株式取引レコード()　@!= null）
        if (l_equityOrderUnits != null)
        {
            log.debug(l_equityOrderUnits.size() + " eqtype order unit row found.");

            // 1.4.1 LOOP処理：getGP取引レコード()の戻り値の要素数回
            for (Iterator l_it = l_equityOrderUnits.iterator(); l_it.hasNext(); )
            {
                EqtypeOrderUnitRow l_equityOrderUnit = (EqtypeOrderUnitRow) l_it.next();
                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                // 1.4.1.1 is存在ロード済み対象銘柄(銘柄ID）
                //   引数の銘柄IDは株式注文単位テーブル.銘柄ID
                //   ミニ株区分追加（預り資産対応）
                boolean isLoadedProduct = this.isLoadedProduct(l_equityOrderUnit.
                    getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.4.1.2 is存在ロード済み対象銘柄(銘柄ID) = falseの場合、
                if (!isLoadedProduct)
                {
                    // 1.4.1.2.1 load対象銘柄(銘柄ID)
                    //   引数の銘柄IDは株式注文単位テーブル.銘柄ID
                    //   ミニ株区分追加（預り資産対応）
                    l_product =
                        loadProduct(l_equityOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.4.1.2.2 銘柄ごと証券評価.create銘柄ごと証券評価(対象銘柄)
                    //   引数の対象銘柄は、「2.2.1」の戻り値
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.4.1.2.3 add銘柄ごと証券評価（対象銘柄, 銘柄ごと証券評価)
                    //   引数の対象銘柄は「2.2.1」の戻り値
                    //   引数の銘柄ごと証券評価は「2.2.2」の戻り値
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                // 1.4.1.3 is存在ロード済み対象銘柄(銘柄ID) = trueの場合、
                else
                {
                    // 1.4.1.3.1 get対象銘柄(銘柄ID)
                    //   引数の銘柄IDは株式注文単位テーブル.銘柄ID
                    //   ミニ株区分追加（預り資産対応）
                    l_product =
                        getProduct(l_equityOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.4.1.3.2 get銘柄ごと証券評価(対象銘柄)
                    //   引数の対象銘柄はget対象銘柄()の戻り値
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }

                // 1.4.1.4 取引変動.create取引変動()--未約定取引変動
                WEB3TPSecurityTransactionChange l_unExecChange =
                    WEB3TPSecurityTransactionChange.create();

                // 1.4.1.5 set余力計算条件(余力計算条件)
                l_unExecChange.setCalcCondition(getCalcCondition());

                // 1.4.1.6 未約定取引変動.set銘柄タイプ()
                l_unExecChange.setProductType(l_product.getProductType());

                // 1.4.1.7 未約定取引変動.set預り区分(預り区分)
                // 引数の預り区分は、get顧客属性().get預り区分(株式注文単位テーブル.補助口座ID)
                l_unExecChange.setDepositType(
                    getAccountInfo().getDepositType(
                    l_equityOrderUnit.getSubAccountId()));
                
                // 1.4.1.8 未約定取引変動.set税区分(税区分)
                l_unExecChange.setTaxType(l_equityOrderUnit.getTaxType());
                
                // 1.4.1.9 to特定口座区分(TaxTypeEnum)
                // 1.4.1.10 未約定取引変動.set特定口座区分(特定口座区分)
                l_unExecChange.setSpecialAccountFlag(l_unExecChange.toSpecialAccountFlag(
                    l_equityOrderUnit.getTaxType()));

                // 1.4.1.11 未約定取引変動.set注文カテゴリ(注文カテゴリ)
                //   引数の注文カテゴリは、株式注文単位テーブル.注文カテゴリ
                l_unExecChange.setOrderCateg(l_equityOrderUnit.getOrderCateg());

                // 1.4.1.12 未約定取引変動.set売買区分(売買区分)
                l_unExecChange.setSide(SideEnum.getSide(l_equityOrderUnit.getOrderType()));

                // 1.4.1.13 未約定取引変動.set評価掛目(評価掛目)
                l_unExecChange.setValuationRatio(
                    l_product.getValuationRatio(l_unExecChange.getDepositType()));

                // 1.4.1.14 未約定取引変動.calc変動反映日(受渡日)
                //   引数の受渡日は、株式注文単位テーブル.受渡日
                l_unExecChange.calcReflectDay(l_equityOrderUnit.getDeliveryDate());

                // 1.4.1.15 取引変動.create取引変動()--約定取引変動
                WEB3TPSecurityTransactionChange l_execChange =
                    WEB3TPSecurityTransactionChange.create();
                l_execChange.setExecType(WEB3TPExecTypeDef.EXECUTED);

                // 1.4.1.16 約定取引変動.set計算条件()
                l_execChange.setCalcCondition(getCalcCondition());

                // 1.4.1.17 約定取引変動.set銘柄タイプ
                l_execChange.setProductType(l_product.getProductType());

                // 1.4.1.18 約定取引変動.set預り区分(預り区分)
                // 引数の預り区分は、get顧客属性().get預り区分(株式注文単位テーブル.補助口座ID)
                l_execChange.setDepositType(
                    getAccountInfo().getDepositType(
                    l_equityOrderUnit.getSubAccountId()));

                // 1.4.1.19 取引変動.set税区分(税区分)
                l_execChange.setTaxType(l_equityOrderUnit.getTaxType());
                
                // 1.4.1.20  to特定口座区分(TaxTypeEnum)
                // 1.4.1.21 取引変動.set特定口座区分(特定口座区分)
                l_execChange.setSpecialAccountFlag(l_execChange.toSpecialAccountFlag(
                    l_equityOrderUnit.getTaxType()));

                // 1.4.1.22 取引変動.set注文カテゴリ(注文カテゴリ)
                //   引数の注文カテゴリは、株式注文単位テーブル.注文カテゴリ
                l_execChange.setOrderCateg(l_equityOrderUnit.getOrderCateg());

                // 1.4.1.23 未約定取引変動.set売買区分(売買区分)
                l_execChange.setSide(SideEnum.getSide(l_equityOrderUnit.getOrderType()));

                // 1.4.1.24 取引変動.set評価掛目(評価掛目)
                l_execChange.setValuationRatio(
                    l_product.getValuationRatio(l_unExecChange.getDepositType()));

                // 1.4.1.25 未約定取引変動.calc変動反映日(受渡日)
                //   引数の受渡日は、株式注文単位テーブル.受渡日
                l_execChange.calcReflectDay(l_equityOrderUnit.getDeliveryDate());

                // 1.4.1.26
                //  売けの場合：
                //      株式注文単位テーブル.注文数量 - 株式注文単位テーブル.約定数量 > 0
                //       かつ、株式注文単位テーブル.注文タイプ = 現渡の場合
                //あるいは
                //  買付の場合
                //  株式注文単位テーブル.注文数量 - 株式注文単位テーブル.約定数量 > 0
                if ( (SideEnum.SELL.equals(l_execChange.getSide())
                      && (
                    l_equityOrderUnit.getQuantity() -
                    l_equityOrderUnit.getExecutedQuantity() > 0 &&
                    OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_equityOrderUnit.getOrderType())
                    )
                      )

                    || (SideEnum.BUY.equals(l_execChange.getSide())
                        && (
                    l_equityOrderUnit.getQuantity() -
                    l_equityOrderUnit.getExecutedQuantity() > 0
                    )
                        )
                    )
                {
                    // 1.4.1.26.1 取引変動.set約定区分(約定区分)
                    //   引数の約定区分は、未約定
                    l_unExecChange.setExecType(WEB3TPExecTypeDef.UNEXECUTED);

                    // 1.4.1.26.2 現引・現渡でない場合
                    // (株式注文単位Row.注文カテゴリ != 7:現引・現渡注文)
                    if (!OrderCategEnum.SWAP_MARGIN.equals(l_equityOrderUnit.
                        getOrderCateg()))
                    {
                        // 1.4.1.26.2.2  取引変動.set変動数量(変動数量)
                        //   引数の変動数量は、株式注文単位テーブル.数量 -
                        // 株式注文単位テーブル.約定数量
                        l_unExecChange.setQuantity(l_equityOrderUnit.getQuantity() -
                            l_equityOrderUnit.getExecutedQuantity());

                        // 1.4.1.26.2.3  取引変動.set評価単価(評価単価)
                        //   引数の評価単価は、株式注文単位テーブル.注文単価 × 対象銘柄.get計算単位()
                        l_unExecChange.setUnitPrice(
//                            Math.min(l_equityOrderUnit.getPrice(), l_product.getUnitPrice()) * 設計時の間違い
                            l_unitPriceCallback.getUnitPriceNotCompare(l_equityOrderUnit.getPrice(), l_product) * 
                            l_product.getUnitSize());                                                

                        // 1.4.1.26.2.4 取引変動.set評価額(評価額)
                        //
                        // 引数の評価額は、取引変動.get変動数量()×取引変動.get評価単価()
                        //売買代金を計算するため、掛目を掛けない
                        l_unExecChange.setValuationPrice(
                            l_unExecChange.getQuantity()
                            * l_unExecChange.getUnitPrice()
                            );
                    }
                    
                    // 1.4.1.26.3 以外（現引・現渡）の場合
                    else
                    {
                        // バグY00019：信用現引注文の未約定代金計算に使用する注文単価にnullが入る
                        // 返済情報から建単価で評価する
                        //一括返済の場合も対応する。
                        // 1.4.1.26.3.1 建玉返済指定情報オブジェクトのリストを取得する。 
                        List l_closingContractSpecs = this.contractInfo.
                            getClosingContractSpecs(l_equityOrderUnit.getOrderUnitId());
                        double l_quantity = 0.0;
                        double l_amount = 0.0;
                        
                        //1.4.1.26.3.2 LOOP処理：get建玉返済指定情報一覧()の戻り値の要素数回
                        for (int i = 0; i < l_closingContractSpecs.size(); i++)
                        {
                            //1.4.1.26.3.2.1 返済注文数量を取得する。 
                            WEB3TPClosingContractSpec l_closingContractSpec = (
                                WEB3TPClosingContractSpec)
                                l_closingContractSpecs.get(i);
                            l_quantity +=
                                (l_closingContractSpec.getQuantity() -
                                 l_closingContractSpec.getExecutedQuantity());
                            l_amount += (l_closingContractSpec.getQuantity() -
                                         l_closingContractSpec.getExecutedQuantity()) *
//                                Math.min(l_closingContractSpec.getContractPrice(),
//                                         l_product.getUnitPrice()) *
                                l_unitPriceCallback.getUnitPrice(l_closingContractSpec.getContractPrice(), l_product) *                                          
                                l_product.getUnitSize();
                            
                            // 1.4.1.26.3.3 変動数量をセットする 
                            l_unExecChange.setQuantity(l_quantity);
                            // 1.4.1.26.3.5 評価額をセットする 
                            l_unExecChange.setValuationPrice(l_amount);
                            // 1.4.1.26.3.4 評価単価をセットする 
                            //引数:double = 集計した”評価額”　@／　@集計した”変動数量” 
                            //※”変動数量” = 0 の場合は、0をセット 
                            if (l_quantity > 0)
                            {
                                l_unExecChange.setUnitPrice(l_amount / l_quantity);
                            }
                        }
                    }

                    // 1.4.1.26.4 銘柄ごと証券評価額.add証券変動(証券変動)
                    l_valuation.addSecurityChange(l_unExecChange);

                }

                // 1.4.1.27 株式注文単位テーブル.約定数量 > 0
                if (l_equityOrderUnit.getExecutedQuantity() > 0)
                {

                    // 1.4.1.27.1 取引変動.set約定区分(約定区分)
                    //   引数の約定区分は、約定済
                    l_execChange.setExecType(WEB3TPExecTypeDef.EXECUTED);

                    // 1.4.1.27.2 取引変動.get注文カテゴリ() != 「現引・現渡」の場合
                    if (!OrderCategEnum.SWAP_MARGIN.equals(l_unExecChange.getOrderCateg()))
                    {
                        // 預り証券データリソースアクセス管理.get株式約定レコード(注文単位ID)
                        //   引数の注文単位IDは株式注文単位テーブル.注文単位ID
                        List l_equityOrderExecutions =
                            dataMgr.
                            getEqTypeOrderExecutions(l_equityOrderUnit);

                        // 1.4.1.27.2.2 取得したレコードを集計し以下の値を算出する
                        //   変動数量 += 株式注文約定テーブル.約定数量
                        //   評価額 += 株式注文約定テーブル.約定数量×Min（株式約定テーブル.約定単価,
                        // 対象銘柄.get評価単価()× 対象銘柄.get計算単位()
                        double l_quantity = 0.0;
                        double l_amount = 0.0;
                        for (int i = 0; i < l_equityOrderExecutions.size(); i++)
                        {
                            EqtypeOrderExecutionRow l_equityOrderExecution = (
                                EqtypeOrderExecutionRow)
                                l_equityOrderExecutions.get(i);
                            //約定取消対応のため、取消された約定を対象外にする
                            if (BooleanEnum.TRUE.equals(l_equityOrderExecution.
                                getDeleteFlag()))
                            {
                                continue;
                            }
                            l_quantity += l_equityOrderExecution.getExecQuantity();
                            l_amount += l_equityOrderExecution.getExecQuantity() *
//                                l_equityOrderExecution.getExecPrice() *
                                l_unitPriceCallback.getUnitPriceNotCompare(l_equityOrderExecution.getExecPrice(), l_product) *                                          
                                l_product.getUnitSize();
                        }
                        // 1.4.1.27.2.3 取引変動.set変動数量(変動数量)
                        l_execChange.setQuantity(l_quantity);

                        // 1.4.1.27.2.4 取引変動.set評価額(評価額)
                        // 　@引数の評価額は、算出した評価額
                        ////売買代金を計算するため、掛目を掛けない
                        l_execChange.setValuationPrice(l_amount);
                        // 1.4.1.27.2.5 取引変動.set評価単価(評価単価)
                        // 　@引数の評価単価は、 2.12.2.2算出した評価額 / 取引変動.get変動数量()
                        if (l_quantity > 0)
                        {
                            l_execChange.setUnitPrice(l_amount / l_quantity);
                        }

                        //   銘柄ごと証券評価額.add証券変動(証券変動)
                        l_valuation.addSecurityChange(l_execChange);
                    }
                    // 1.4.1.27.3 取引変動.get注文カテゴリ() =「現引・現渡」の場合
                    else
                    {
                        if (this.contractInfo != null)
                        {
                            // 1.4.1.27.3.1 建玉情報.get返済指定情報(注文単位テーブル.注文単位ID)で建玉返済指定情報
                            // を取得する。
                            List l_closingContractSpecs = this.contractInfo.
                                getClosingContractSpecs(l_equityOrderUnit.getOrderUnitId());
                            // 取得した建玉返済指定情報を集計し以下の値を算出する
                            // 　@変動数量 += 建玉返済指定情報.約定数量
                            // 　@評価額 + = 建玉返済指定情報.返済約定数量
                            // 　@　@　@　@　@　@　@× Min(建玉返済指定情報.建単価, 対象銘柄.get評価単価()) × 対象銘柄.get計算単位()
                            // 　@　@　@　@　@　@　@
                            double l_quantity = 0.0;
                            double l_amount = 0.0;
                            // 1.4.1.27.3.2 LOOP処理：get建玉返済指定情報一覧()の戻り値の要素数回
                            for (int i = 0; i < l_closingContractSpecs.size(); i++)
                            {
                                WEB3TPClosingContractSpec l_closingContractSpec = (
                                    WEB3TPClosingContractSpec)
                                    l_closingContractSpecs.get(i);
                                l_quantity += l_closingContractSpec.getExecutedQuantity();
                                l_amount += l_closingContractSpec.getExecutedQuantity() *
//                                    Math.min(l_closingContractSpec.getContractPrice(),
//                                             l_product.getUnitPrice()) *
                                    l_unitPriceCallback.getUnitPrice(l_closingContractSpec.getContractPrice(), l_product) * 
                                    l_product.getUnitSize();
                            }

                            // 1.4.1.27.3.3 取引変動.set変動数量(変動数量)
                            l_execChange.setQuantity(l_quantity);

                            // 1.4.1.27.3.4 取引変動.set評価額(評価額）
                            // 　@引数の評価額は算出した評価額
                            //  売買代金を計算するため、掛目を掛けない
                            l_execChange.setValuationPrice(l_amount);

                            // 1.4.1.27.3.5 取引変動.set評価単価(評価単価)
                            // 　@引数の評価単価は、算出した評価額 / 取引変動.get変動数量()
                            if (l_quantity > 0)
                            {
                                l_execChange.setUnitPrice(l_amount / l_quantity);
                            }

                            // 1.4.1.27.4 銘柄ごと証券評価額.add証券変動(証券変動)
                            //   銘柄ごと証券評価額は、｢2.2.2｣または｢2.3.2｣の戻り値
                            //   引数の証券変動は、｢2.4｣の戻り値
                            l_valuation.addSecurityChange(l_execChange);
                        }

                    }
                }

            }
        }
        else
        {
            log.debug(" equity order unit row not found.");
        }

    }

    /**
     * (isロード済み対象銘柄)<BR>
     * ロード済みの対象銘柄かどうかをチェックする。<BR>
     * 対象銘柄リストに、引数で指定された銘柄IDと一致する銘柄IDを持つ<BR>
     * 対象銘柄が存在するかチェックする。<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_strMiniStockDivDef - (ミニ株区分)
     * @@return boolean
     * @@roseuid 40D7D816033A
     */
    protected boolean isLoadedProduct(long l_lngProductId, String l_strMiniStockDivDef)
    {
        if (this.products != null)
        {
            for (int i = 0; i < products.size(); i++)
            {
                WEB3TPSecurityValuationProduct l_product =
                    (WEB3TPSecurityValuationProduct) products.get(i);
                //預り資産対応により、ミニ株区分も対称銘柄チェックに追加する
                if (l_lngProductId == l_product.getProductId() && 
                    l_strMiniStockDivDef.equals(l_product.getMiniStockDivDef()) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * (get対象銘柄)<BR>
     * 銘柄ＩＤにより対象銘柄を取得する。<BR>
     * 対象銘柄リストから銘柄IDをキーとして対象銘柄を取得する。<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_strMiniStockDivDef - (ミニ株区分)
     * @@return WEB3TPSecurityValuationProduct
     * @@roseuid 40D7DDF803A7
     */
    public WEB3TPSecurityValuationProduct getProduct(long l_lngProductId, String l_strMiniStockDivDef)
    {
        if (this.products != null)
        {
            for (int i = 0; i < products.size(); i++)
            {
                WEB3TPSecurityValuationProduct l_product =
                    (WEB3TPSecurityValuationProduct) products.get(i);
                //預り資産対応により、ミニ株区分も対称銘柄チェックに追加する
                if (l_lngProductId == l_product.getProductId() && 
                    l_strMiniStockDivDef.equals(l_product.getMiniStockDivDef()) )
                {
                    return l_product;
                }
            }
        }
        return null;
    }

    /**
     * (add銘柄ごと証券評価額)<BR>
     * 銘柄ごと証券評価額を追加する。<BR>
     * 1.対象銘柄リスト.add(引数.対象銘柄)<BR>
     * 2.銘柄ごと証券評価額マップ.put(引数.対象銘柄, 引数.銘柄ごと証券評価額)<BR>
     * <BR>
     * @@param l_product - (対象銘柄)
     * @@param l_valuation - (銘柄ごと証券評価額)
     * @@roseuid 40D7E40903C6
     */
    protected void addSecurityValuationPerProduct(
        WEB3TPSecurityValuationProduct l_product,
        WEB3TPSecurityValuationPerProduct l_valuation)
    {
        // 銘柄タイプごと対象銘柄リストを取得
        List l_list = (List) classifiedProducts.get(l_product.getProductType());
        if (l_list == null)
        {
            l_list = new ArrayList();
            classifiedProducts.put(l_product.getProductType(), l_list);
        }
        // 銘柄タイプごと対象銘柄リストに追加
        l_list.add(l_product);
        // 対象銘柄リストに追加
        products.add(l_product);
        // 銘柄ごと証券評価額マップに追加
        valuationPerProducts.put(l_product, l_valuation);
    }

    /**
     * (calc投資信託資産評価額)<BR>
     * <BR>
     * 投資信託の評価額を計算する。 <BR>
     * <BR>
     * ※シーケンス図「(証券評価額)calc投資信託資産評価額」参照<BR>
     * <BR>
     * @@param l_product - (対象銘柄)
     * @@param l_change - (証券変動)
     */
    protected double calcMutualFundValuationPricePerChange(
        WEB3TPSecurityValuationProduct l_product,
        WEB3TPSecurityChange l_change)
    {
        //計算単位を取得
        BigDecimal l_bdUnitSize = new BigDecimal("" + l_product.getUnitSize());

        //変動数量を取得
        BigDecimal l_bdQuantity = new BigDecimal("" + l_change.getQuantity());

        //評価単価を取得
        BigDecimal l_bdUnitPrice = new BigDecimal("" + l_change.getUnitPrice());

        //”評価額”を計算する。
        //※小数点第7位を四捨五入する。
        BigDecimal l_bdValuationPrice =
            l_bdUnitPrice.multiply(l_bdQuantity).divide(
                l_bdUnitSize, 6, BigDecimal.ROUND_HALF_UP);
        
        //銘柄ID取得
        long l_lngProductId = l_product.getProductId();
        
        //投信銘柄マスターを取得
        MutualFundProductRow l_mutualFundProductRow = dataMgr.getMutualFundProduct(l_lngProductId);
        
        //投信銘柄 != null
        if(l_mutualFundProductRow != null)
        {
            //通貨コード
            String l_strCurrencyCode = l_mutualFundProductRow.getCurrencyCode();
            
            //通貨コード != null
            if(l_strCurrencyCode != null)
            {
                //証券会社コードを取得
                String l_strInstitutionCode = this.getAccountInfo().getInstitutionCode();
                
                //為替レートを取得
                ExchangeRateRow l_exchangeRateRow = dataMgr.getExchangeRate(l_strInstitutionCode, l_mutualFundProductRow.getCurrencyCode());

                //為替レート != null
                if(l_exchangeRateRow != null)
                {
                    //TTB
                    BigDecimal l_bdTTB = new BigDecimal("" + l_exchangeRateRow.getTtBuyingRate());

                    //為替レート計算単位
                    BigDecimal l_bdExchangeCalcUnit = new BigDecimal("" + l_exchangeRateRow.getExchangeCalcUnit());

                    //評価額計算(円貨に換算)
                    l_bdValuationPrice =
                        l_bdValuationPrice.multiply(l_bdTTB).divide(
                            l_bdExchangeCalcUnit, 6, BigDecimal.ROUND_HALF_UP);
                }
            }
        }

        //"評価額"の小数点以下を四捨五入する
        return l_bdValuationPrice.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * (get建玉情報)<BR>
     * <BR>
     * 建玉情報を取得する。<BR>
     * @@return  WEB3TPContractInfo
     */
    public WEB3TPContractInfo getContractInfo()
    {
        return this.contractInfo;
    }

    /**
     * (set建玉情報)<BR>
     * <BR>
     * 建玉情報をセットする。<BR>
     * @@param l_contractInfo - 建玉情報
     */
    public void setContractInfo(WEB3TPContractInfo l_contractInfo)
    {
        this.contractInfo = l_contractInfo;
    }

    /**
     * (calc外国株式評価額)<BR>
     * （calc外国株式評価額) <BR>
     * <BR>
     * 外国株式の評価額を計算する。<BR>
     * @@param l_securityValuationProduct - (対象銘柄)<BR>
     * (対象銘柄)<BR>
     * @@param l_securityChange - (証券変動)<BR>
     * (証券変動)<BR>
     * @@return double
     */
    protected double calcFeqValuation(
        WEB3TPSecurityValuationProduct l_securityValuationProduct,
        WEB3TPSecurityChange l_securityChange)
    {
        final String STR_METHOD_NAME =
            "calcFeqValuation(WEB3TPSecurityValuationProduct, WEB3TPSecurityChange)";
        log.entering(STR_METHOD_NAME);

        //get計算単位
        double l_dblUnitSize = l_securityValuationProduct.getUnitSize();

        //get変動数量
        double l_dblQuantity = l_securityChange.getQuantity();

        // get評価単価
        double l_dblUnitPrice = l_securityChange.getUnitPrice();

        //get銘柄ＩＤ
        long l_lngProductId = l_securityValuationProduct.getProductId();

        //get外株銘柄(long)
        //[引数]
        //銘柄ID = get銘柄ID()の戻り値
        FeqProductRow l_feqPeoductRow = dataMgr.getFeqProduct(l_lngProductId);

        //顧客属性を取得
        WEB3TPAccountInfo l_accountInfo = getAccountInfo();
        //get証券会社コード
        String l_strInstitutionCode = l_accountInfo.getInstitutionCode();

        //get（共通）通貨
        //[引数]
        //証券会社コード = get証券会社コード()の戻り値
        //通貨コード = 外株銘柄Row.通貨コード
        GenCurrencyRow l_genCurrencyRow =
            dataMgr.getGenCurrency(l_strInstitutionCode, l_feqPeoductRow.getCurrencyCode());

        //(*)"評価額"を計算する
        //数量
        BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity + "");
        //評価単価
        BigDecimal l_bdUnitPrice = new BigDecimal(l_dblUnitPrice + "");
        //為替レート
        BigDecimal l_bdCurrentSellExecRate = new BigDecimal(l_genCurrencyRow.getCurrentSellExecRate() + "");
        //計算単位
        BigDecimal l_bdUnitSize = new BigDecimal(l_dblUnitSize + "");

        //外国株式評価額
        BigDecimal l_bdFeqValuation =
            l_bdQuantity.multiply(l_bdUnitPrice).multiply(l_bdCurrentSellExecRate).multiply(l_bdUnitSize);

        log.debug("外国株式の評価額 = " + l_bdFeqValuation.doubleValue());

        log.exiting(STR_METHOD_NAME);
        return l_bdFeqValuation.doubleValue();
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {

        ProductTypeEnum[] l_types = new ProductTypeEnum[]
            {
            ProductTypeEnum.EQUITY,
            ProductTypeEnum.BOND,
            ProductTypeEnum.MUTUAL_FUND,
            ProductTypeEnum.RUITO,
            ProductTypeEnum.OTHER};

        String l_strProductAll = "\n";
        String l_strWEB3TPSecurityValuationPerProductAll = "";
        for (int k = 0; k < l_types.length; k++)
        {
            List list = (List) classifiedProducts.get(l_types[k]);
            if (list == null)
            {
                continue;
            }
            int i = 0;
            String l_strProducts = l_types[k].stringValue() + ":\n";
            //対象銘柄
            for (Iterator l_it = list.iterator(); l_it.hasNext(); )
            {
                l_strProducts += "[" + i + "]" + l_it.next();
                i += 1;
            }
            l_strProductAll = l_strProductAll + l_strProducts;
            //銘柄ごと評価
            i = 0;
            String l_strWEB3TPSecurityValuationPerProduct = l_types[k].stringValue() +
                ":\n";
            for (Iterator l_it = list.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityValuationProduct l_product = (
                    WEB3TPSecurityValuationProduct)
                    l_it.next();
                l_strWEB3TPSecurityValuationPerProduct += "[" + i + "]" +
                    this.getSecurityValuationPerProduct(l_product); ;
                i += 1;
            }
            l_strWEB3TPSecurityValuationPerProductAll =
                l_strWEB3TPSecurityValuationPerProductAll +
                l_strWEB3TPSecurityValuationPerProduct;
        }

        return ToStringUtils
            .newToStringBuilder(this)
            .append("super", super.toString())
            .append("products", l_strProductAll)
            .append("valuationPerProducts", l_strWEB3TPSecurityValuationPerProductAll)
            .append("contractInfo", contractInfo)
            .toString();
    }

}
@
