head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスクラス(WEB3EquityBizLogicProvider.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/12 欒学峰(中訊) 新規作成
Revesion History : 2006/07/15 栄イ 【株式】仕様変更管理台帳・モデル952、954を対応
Revesion History : 2006/08/14 栄イ 【株式】仕様変更管理台帳・モデル973を対応
                 : 2006/08/29 張騰宇(中訊) モデル 970
                 : 2006/11/01 趙林鵬 (中訊) モデル No.1031
                 : 2006/12/21 唐性峰 (中訊) モデル No.1093
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CapitalGainStatusDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3PremiumRestraintRateDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.EquityCommCondMstRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeNumberConstDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （計算サービス （株式計算））。<BR>
 * <BR>
 * 各種計算を提供するユーティリティクラス。<BR>
 * 余力計算は、xTradeが提供するインタフェイス以外にもパラメータが必要。
 * @@version 1.0
 */
public class WEB3EquityBizLogicProvider
    extends WEB3GentradeBizLogicProvider
    implements GlobalBizLogicProvider, EqTypeBizLogicProvider
{

    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBizLogicProvider.class);

    /**
     * @@roseuid 40A3495902F0
     */
    public WEB3EquityBizLogicProvider()
    {

    }

    public double calcExecutionAmount(
        ProductTypeEnum l_productTypeEnum,
        long l_lng,
        double l_dbl1,
        double l_dbl2,
        QuantityTypeEnum l_quantityTypeEnum)
    {
        return 0.0;
    }

    /**
     * (calc取引余力)<BR>
     *<BR>
     * 指定口座の取引余力を計算し、計算結果を返却します。<BR>
     * （GlobalBizLogicProvider内のメソッドのカラ実装）<BR>
     *<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@return double
     * @@roseuid 3FFBD9950222
     */
    public double calcTradingPower(SubAccount l_subAccount)
    {
        return Double.NaN;
    }

    /**
     * (check取引余力)<BR>
     *<BR>
     * 買付余力チェックを行う。<BR>
     * （checkTradingPowerのオーバーライド。カラ実装）<BR>
     *<BR>
     * @@param l_subAccount - 補助口座
     * @@param l_orderSpec - 注文内容
     *
     * @@return OrderValidationResult
     * @@roseuid 4010AF2C0227
     */
    public OrderValidationResult checkTradingPower(
        SubAccount l_subAccount,
        OrderSpec l_orderSpec)
    {
        return null;
    }

    /**
     * (calc拘束売買代金)<BR>
     *<BR>
     * 計算単価、株数より拘束売買代金を計算して返却する。<BR>
     *<BR>
     * ※計算の詳細については、基本設計計算式書（共通）.docを参照<BR>
     *<BR>
     * １）会社部店商品テーブルより割増拘束率を取得する。（部店IDと商品コードで取得）<BR>
     *<BR>
     * ２）拘束売買代金を計算する。<BR>
     *<BR>
     * 　@拘束売買代金＝注文株数×計算単価×割増拘束率<BR>
     *
     * （注）割増拘束率を採用しないケースについては、当該項目には'1'が設定されているたあ 、<BR>
     * 一律上記の計算式で計算する。<BR>
     * @@param l_dblQuantity - (株数)<BR>
     *<BR>
     * 注文（約定）株数<BR>
     * @@param l_dblUnitPrice - (計算単価)<BR>
     *<BR>
     * 拘束売買代金を計算するための単価<BR>
     * @@param l_lngBranchId - 部店ID<BR>
     * @@param l_strCommissionProductCode - 手数料商品コード<BR>
     * １０：上場株式<BR><BR>
     * １１：店頭株式(JASDAQ)<BR>
     * １２：ミニ株式<BR>
     * ４０：外国株式<BR>
     * ５０：先物<BR>
     * ５１：株価指数ＯＰ<BR>
     * @@param l_blnIsLimitPrice - (is指値)<BR>
     *
     * 指値注文の場合はtrue、成行注文の場合はfalse。<BR>
     * @@return double
     * @@throws WEB3BaseEception 拘束売買代金の計算に失敗した場合
     * @@roseuid 4014761A0387
     */
    public double calcRestraintTurnover(
        double l_dblQuantity,
        double l_dblUnitPrice,
        long l_lngBranchId,
        String l_strCommissionProductCode,
        boolean l_blnIsLimitPrice)
        throws WEB3BaseException
    {
        String l_errorMessage1 = "会社部店商品テーブルに該当するデータがありません。";
        long l_lngRestraintTurnover;
        double l_dbRestraintTurnover = 0.0;
        InstBranchProductRow l_instBranchProductRow;
        BigDecimal l_bdUnitPrice;
        BigDecimal l_bdQuantity;
        BigDecimal l_bdPremiumRestraintRate;

        final String STR_METHOD_NAME =
            "calcRestraintTurnover(double , double , long , String , boolean)";
        log.entering(STR_METHOD_NAME);

        // 会社部店商品テーブルを読み込む
        try
        {
            l_instBranchProductRow =
                InstBranchProductDao.findRowByPk(
                    l_lngBranchId,
                    l_strCommissionProductCode);
        }
        catch (DataException de)
        {
            // DBアクセスエラー。
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        if (l_instBranchProductRow == null)
        {
            // 該当するデータが無い。
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_errorMessage1);
        }

        //get 概算金額計算方式
        int l_intEstimatePriceCalcForm =
            l_instBranchProductRow.getEstimatePriceCalcForm();

        //get 割増拘束率
        l_bdPremiumRestraintRate =
            new BigDecimal(l_instBranchProductRow.getPremiumRestraintRate());

        //注文単価区分が成行
        if (!l_blnIsLimitPrice)
        {
            //注文単価区分が成行（手数料.is指値() == false）　@且つ　@
            //概算金額計算方式が割増拘束金方式　@　@
            //拘束売買代金 ＝ 計算単価 × 注文株数× 割増拘束率
            if (WEB3PremiumRestraintRateDef.PREMIUM_RESTRANT
                == l_intEstimatePriceCalcForm)
            {
                l_bdUnitPrice = new BigDecimal(l_dblUnitPrice);
                l_bdQuantity = new BigDecimal(l_dblQuantity);
                l_dbRestraintTurnover =
                    l_bdUnitPrice.multiply(l_bdQuantity).multiply(l_bdPremiumRestraintRate).doubleValue();
                log.debug("****** 計算単価：[" + l_dblUnitPrice + "]");
                log.debug("****** 注文株数：[" + l_dblQuantity + "]");
                log.debug("****** 割増拘束率：[" + l_bdPremiumRestraintRate.doubleValue() + "]");
                log.debug("****** 概算金額計算方式が割増拘束金方式");
                log.debug("****** 拘束売買代金 ＝ 計算単価 × 注文株数× 割増拘束率 ： ["
                    + l_dbRestraintTurnover + "]");
            }
            //注文単価区分が成行（手数料.is指値() == false）　@且つ　@
            //概算金額計算方式がSTOP高方式　@
            //拘束売買代金 ＝ 計算単価 × 注文株数
            else
            {
                l_bdUnitPrice = new BigDecimal(l_dblUnitPrice);
                l_bdQuantity = new BigDecimal(l_dblQuantity);
                l_dbRestraintTurnover =
                    l_bdUnitPrice.multiply(l_bdQuantity).doubleValue();
                log.debug("****** 計算単価：[" + l_dblUnitPrice + "]");
                log.debug("****** 注文株数：[" + l_dblQuantity + "]");
                log.debug("****** 概算金額計算方式がSTOP高方式");
                log.debug("****** 拘束売買代金 ＝ 計算単価 × 注文株数 ： [" 
                    + l_dbRestraintTurnover + "]");
            }
        }
        //注文単価区分が指値
        else
        {
            //拘束売買代金 ＝ 計算単価（指値） × 注文株数
            l_bdUnitPrice = new BigDecimal(l_dblUnitPrice);
            l_bdQuantity = new BigDecimal(l_dblQuantity);
            l_dbRestraintTurnover =
                l_bdUnitPrice.multiply(l_bdQuantity).doubleValue();
            log.debug("****** 計算単価（指値）：[" + l_dblUnitPrice + "]");
            log.debug("****** 注文株数：[" + l_dblQuantity + "]");
            log.debug("****** 拘束売買代金 ＝ 計算単価（指値） × 注文株数 ： [" 
                + l_dbRestraintTurnover + "]");
        }

        // 小数点以下切り捨て
        l_lngRestraintTurnover = (long) l_dbRestraintTurnover;
        l_dbRestraintTurnover = l_lngRestraintTurnover;

        log.exiting(STR_METHOD_NAME);
        return l_dbRestraintTurnover;
    }

    /**
     * （create手数料）<BR>
     * <BR>
     * 概算売買代金計算に使用する手数料オブジェクトを生成し、<BR>
     * 引数で指定された注文の注文単位オブジェクトよりプロパティをセットする。<BR>
     * <BR>
     * １）　@拡張株式注文マネージャ.getOrderUnits( )で、<BR>
     * 　@　@　@引数の注文IDに該当する注文単位オブジェクトのリストを取得する。<BR>
     * 以降、取得した注文単位オブジェクトの0番目の要素を使用する。<BR>
     * <BR>
     * ２）　@手数料インスタンスを生成し、プロパティに以下の通りに注文単位オブジェクトの値をセットする。<BR>
     * <BR>
     * 証券会社コード： 注文単位オブジェクト.部店IDから部店オブジェクトを取得し、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@部店オブジェクトの同項目をセット<BR>
     * 部店ID： 注文単位オブジェクトの同項目<BR>
     * 手数料商品コード： 注文単位オブジェクトの同項目<BR>
     * 取引コード（SONAR）： 注文単位オブジェクトの同項目<BR>
     * 発注日：　@注文単位オブジェクトの同項目<BR>
     * 弁済区分：<BR>
     * 　@○注文単位オブジェクト.弁済区分（SONAR）==nullの場合<BR>
     * 　@　@　@弁済区分.その他（"00"）をセット（固定）<BR>
     * 　@○注文単位オブジェクト.弁済区分（SONAR）!=nullの場合<BR>
     * 　@　@　@注文単位オブジェクト.弁済区分（SONAR）をそのままセット<BR>
     * 注文チャネル： 注文単位オブジェクト.初回注文の注文チャネル<BR>
     * 原注文注文チャネル： 注文単位オブジェクト.初回注文の注文チャネル<BR>
     * 原注文手数料No： 注文単位オブジェクト.初回注文の手数料No<BR>
     * 原注文手数料No枝番： 注文単位オブジェクト.初回注文の手数料No枝番<BR>
     * is指値：　@注文単位オブジェクト.isMarketOrder( )==falseの場合はtrueを、<BR>
     * 　@　@　@　@　@　@注文単位オブジェクト.isMarketOrder( )==trueの場合はfalseを、それぞれセット<BR>
     * 市場コード（SONAR）：　@注文単位オブジェクト.市場IDに該当する<BR>
     * 　@　@　@　@　@　@市場オブジェクト.市場コード（SONAR）をセット<BR>
     * <BR>
     * ※以下のプロパティは設定しない。<BR>
     * 手数料No<BR>
     * 手数料No枝番<BR>
     * 手数料金額<BR>
     * 諸経費計算用代金<BR>
     * 手数料コースコード<BR>
     * <BR>
     * ３）　@生成した手数料オブジェクトのインスタンスを返却する。<BR>
     * <BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID
     * @@return WEB3GentradeCommission<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D2FEA004D
     */
    public WEB3GentradeCommission createCommission(long l_lngOrderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCommission(long)";

        log.entering(STR_METHOD_NAME);

        //１）拡張株式注文マネージャ.getOrderUnits()で、
        // 引数の注文IDに該当する注文単位オブジェクトのリストを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderMgr =
            (EqTypeOrderManager) l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(l_lngOrderId);
        OrderUnit l_orderUnit = l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        //２）手数料インスタンスを生成し、プロパティに以下の通りに
        //  注文単位オブジェクトの値をセットする。
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //部店IDから部店オブジェクトを取得する
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = new WEB3GentradeBranch(l_orderUnit.getBranchId());
        }
        catch (DataException l_de)
        {
            // DBアクセスエラー。
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        //証券会社コード：注文単位オブジェクト.部店IDから部店オブジェクトを取得し、
        // 部店オブジェクトの同項目をセット 
        l_commission.setInstitutionCode(
            l_branch.getInstitution().getInstitutionCode());
        //部店ID： 注文単位オブジェクトの同項目
        l_commission.setBranchId(l_orderUnit.getBranchId());
        //手数料商品コード： 注文単位オブジェクトの同項目
        l_commission.setCommissionProductCode(
            l_orderUnitRow.getCommProductCode());
        //取引コード（SONAR）： 注文単位オブジェクトの同項目
        l_commission.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());
        //発注日：　@注文単位オブジェクトの同項目
        String l_strBizDate = l_orderUnitRow.getBizDate();
        Date l_datBizDate = null;
        try
        {
            l_datBizDate = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().parse(l_strBizDate);
        }
        catch (ParseException l_pe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_pe.getMessage(),
                l_pe);
        }
        l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
        //弁済区分：　@注文単位オブジェクト.弁済区分（SONAR）
        if (l_orderUnitRow.getSonarRepaymentType() != null)
        {
            l_commission.setPayType(l_orderUnitRow.getSonarRepaymentType());
        }
        else
        {
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
        }
        //原注文注文チャネル： 注文単位オブジェクト.初回注文の注文チャネル
        l_commission.setOrderChannel(l_orderUnitRow.getOrderChanel());
        //原注文手数料No： 注文単位オブジェクト.初回注文の手数料No
        l_commission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
        //原注文手数料No枝番： 注文単位オブジェクト.初回注文の手数料No枝番
        l_commission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
        // is指値：　@注文単位オブジェクト.isMarketOrder()==falseの場合はtrueを、
        //　@注文単位オブジェクト.isMarketOrder()==trueの場合はfalseを、それぞれセット
        if (l_orderUnit.isMarketOrder() == false)
        {
            l_commission.setIsLimitPrice(true);
        }
        else
        {
            l_commission.setIsLimitPrice(false);
        }
        //市場コード（SONAR）
        l_commission.setSonarMarketCode(l_orderUnitRow.getSonarMarketCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_commission;
    }

    /**
     * (calc売買代金)<BR>
     *<BR>
     * 計算単価、株数より売買代金を計算して返却する。<BR>
     * 
     *<BR>
     * ※計算の詳細については、基本設計計算式書（共通）.docを参照<BR>
     *<BR>
     * １）売買代金を計算する。<BR>
     *<BR>
     * 　@売買代金＝注文株数×計算単価<BR>
     * @@param l_dblPartContQuantity - (株数)<BR>
     *     注文（約定）株数<BR>
     * @@param l_dblCalcPrice - (計算単価)<BR>
     *     売買代金を計算するための単価<BR>
     * <BR>
     * @@return double
     * @@roseuid 4056BC3B0107
     */
    public double calcTurnover(
        double l_dblPartContQuantity,
        double l_dblCalcPrice)
    {
        long l_lngRestraintTurnover;
        double l_dbRestraintTurnover = 0.0;
        BigDecimal l_bdUnitPrice;
        BigDecimal l_bdQuantity;

        final String STR_METHOD_NAME = "calcTurnover(double , double)";
        log.entering(STR_METHOD_NAME);

        if (l_dblPartContQuantity < 0 || l_dblCalcPrice < 0)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "株数 = " + l_dblPartContQuantity + "、計算単価 = " + l_dblCalcPrice);
        }

        // 売買代金 ＝ 計算単価 × 注文株数
        l_bdUnitPrice = new BigDecimal(l_dblCalcPrice);
        l_bdQuantity = new BigDecimal(l_dblPartContQuantity);
        l_dbRestraintTurnover =
            l_bdUnitPrice.multiply(l_bdQuantity).doubleValue();
        log.debug("****** 計算単価：[" + l_dblCalcPrice + "]");
        log.debug("****** 注文株数：[" + l_dblPartContQuantity + "]");
        log.debug(
            "****** 売買代金 ＝ 計算単価 × 注文株数 ： ["
                + l_dbRestraintTurnover
                + "]");

        // 小数点以下切り捨て
        l_lngRestraintTurnover = (long) l_dbRestraintTurnover;
        l_dbRestraintTurnover = l_lngRestraintTurnover;

        log.exiting(STR_METHOD_NAME);
        return l_dbRestraintTurnover;
    }

    /**
     * (calc譲渡損益)<BR>
     * <BR>
     * (calc譲渡損益)<BR>
     * <BR>
     * 譲渡損益の金額を計算する。<BR>
     * <BR>
     * １）　@注文の税区分が、譲渡損益計算対象外の場合は０を返却する。<BR>
     * 　@　@　@（引数の税区分＝（”特定口座”、または”特定口座かつ源泉徴収”）の場合<BR>
     * 　@　@　@　@＝「譲渡損益計算対象」。<BR>
     * 　@　@　@　@上記以外の場合＝「譲渡損益計算対象外」。）<BR>
     * <BR>
     * ２）　@注文が「譲渡損益計算対象」の場合は、以下の処理を行う。<BR>
     * <BR>
     * ２－１）　@this.calc概算譲渡損益( )で譲渡損益を計算する。 <BR>
     * <BR>
     * 　@　@　@　@　@-----------------------------------------------------------<BR>
     * 　@　@　@　@　@＜calc概算譲渡損益( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@　@　@金額：　@引数の金額<BR>
     * 　@　@　@　@　@売数量：　@引数の売数量<BR>
     * 　@　@　@　@　@銘柄ID：　@引数の銘柄ID <BR>
     * 　@　@　@　@　@補助口座：　@引数の補助口座<BR>
     * 	         税区分：　@引数の税区分<BR>
     * 　@　@　@　@　@-----------------------------------------------------------<BR>
     * ３）this.calc概算譲渡損益( )の戻り値を返却する。
     * <BR>
     * @@param l_dblExpensesCalcAmount - (金額)<BR>
     * <BR>
     * 譲渡損益を算出する対象の金額。<BR>
     *  - 金額
     * @@param l_dblOrderQuantity - (売数量)<BR>
     * <BR>
     * 売注文の注文数量。<BR>
     *  - 売数量
     * @@param l_lngProductId - 銘柄ID。<BR>
     * 【保有資産テーブル】より簿価を取得する際に使用する。<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_taxType - 税区分<BR>
     * @@return double<BR>
     * @@throws WEB3BaseEception
     * @@throws WEB3BaseException
     * @@roseuid 413D2FEA01AC
     */
    public double calcCapitaGain(
        double l_dblExpensesCalcAmount,
        double l_dblOrderQuantity,
        long l_lngProductId,
        SubAccount l_subAccount,
        TaxTypeEnum l_taxType)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME =
            "calcCapitaGain(double, double, long, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@注文の税区分が、譲渡損益計算対象外の場合は０を返却する。
        // 　@　@　@（引数の税区分＝（”特定口座”、または”特定口座かつ源泉徴収”）の場合<BR>
        // 　@　@　@　@＝「譲渡損益計算対象」。<BR>
        // 　@　@　@　@上記以外の場合＝「譲渡損益計算対象外」。）
        if (!TaxTypeEnum.SPECIAL.equals(l_taxType) &&
            !TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            log.debug("****** 譲渡損益計算対象外 ： [譲渡損益 = 0]");
            log.exiting(STR_METHOD_NAME);
            return 0.0D;
        }
  
        // ２－１）概算譲渡損益を計算する。
        double l_dblEstimatedCapitalGain = this.calcEstimatedCapitalGain(
        	l_dblExpensesCalcAmount,
        	l_dblOrderQuantity,
        	l_lngProductId,
        	l_subAccount,
        	l_taxType);

        log.debug("****** 引数の金額：[" + l_dblExpensesCalcAmount + "]");
        log.debug("****** 引数の売数量：[" + l_dblOrderQuantity + "]");
        log.exiting(STR_METHOD_NAME);
        return l_dblEstimatedCapitalGain; 
    }

    /**
     * (calc手数料（按分）)<BR>
     * 一口注文に対する委託手数料、委託手数料消費税を計算し、<BR>
     * その内容を表すConsolidatedCommissionInfoオブジェクトを返す。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（一口約定計算）.doc」参照。<BR>
     * <BR>
     * super.calc委託手数料(手数料, 補助口座)をコールする際の引数に設定する<BR>
     * 手数料オブジェクトの作成仕様は、以下の通りとする。<BR>
     * ---------------------------------------------------------<BR>
     * ＜手数料オブジェクトの作成＞<BR>
     * <BR>
     * １）　@this.create手数料(引数の株式顧客勘定明細Params[0].注文ID)により、<BR>
     * 　@　@　@手数料オブジェクトのインスタンスを生成する。<BR>
     * <BR>
     * ２）　@１）で生成した手数料オブジェクトのプロパティ「諸経費計算用代金」に、<BR>
     * 　@　@　@合計約定金額(*1)をセットする。<BR>
     * <BR>
     * 　@　@　@手数料.set諸経費計算用代金(合計約定金額(*1))によりセットする。<BR>
     * <BR>
     * 　@　@　@(*1)合計約定金額<BR>
     * 　@　@　@　@　@　@＝ 引数の株式顧客勘定明細Paramsの全要素の<BR>
     * （約定単価 × 約定数量）のSUM値<BR>
     * ---------------------------------------------------------<BR>
     * @@param l_eqFinTransactionRows
     * @@return ConsolidatedCommissionInfo
     * @@throws WEB3BaseException
     * @@roseuid 413D2FEA02C4
     */
    public ConsolidatedCommissionInfo calcCommission(EqtypeFinTransactionRow[] l_eqFinTransactionRows)
        throws WEB3BaseException
    {
        EqtypeFinTransactionRow l_row;
        EqtypeFinTransactionRow l_maxRow = null;
        BigDecimal l_bdMaxAmount = null;
        BigDecimal l_bdTotalAmount = new BigDecimal("0.0");
        BigDecimal l_bdTotalCommission;
        BigDecimal l_bdTotalSalesTax = null;
        BigDecimal l_bdPrice;
        BigDecimal l_bdQuantity;
        BigDecimal[] l_bdAmounts;
        BigDecimal l_bdCommission;
        BigDecimal l_bdSalesTax;
        BigDecimal l_bdSigmaCommission = new BigDecimal("0.0");
        BigDecimal l_bdSigmaSalesTax = new BigDecimal("0.0");
        double[] l_dbCommissions;
        double[] l_dbSalesTaxs;
        String l_strMessage;
        int l_intLength;
        int l_intIndex = -1;
        int i;

        final String STR_METHOD_NAME =
            "calcCommission(EqtypeFinTransactionRow[])";

        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderMgr =
            (EqTypeOrderManager) l_tm.getOrderManager();

        // ［合計］約定代金と最大約定を求める
        l_intLength = l_eqFinTransactionRows.length;
        l_bdAmounts = new BigDecimal[l_intLength];
        for (i = 0; i < l_intLength; i++)
        {
            l_row = l_eqFinTransactionRows[i];
            l_bdPrice = new BigDecimal(l_row.getPrice());
            log.debug("株式顧客勘定[" + i + "].約定単価：" + l_bdPrice.doubleValue());
            l_bdQuantity = new BigDecimal(l_row.getQuantity());
            log.debug("株式顧客勘定[" + i + "].約定数量：" + l_bdQuantity.doubleValue());
            l_bdAmounts[i] = l_bdPrice.multiply(l_bdQuantity);
            log.debug("株式顧客勘定[" + i + "].約定金額：" + l_bdAmounts[i].doubleValue());
            l_bdTotalAmount = l_bdTotalAmount.add(l_bdAmounts[i]);

            if (l_maxRow == null)
            {
                l_maxRow = l_row;
                l_bdMaxAmount = l_bdAmounts[i];
                l_intIndex = i;
            }
            else
            {
                int l_intResult = l_bdAmounts[i].compareTo(l_bdMaxAmount);
                if (l_intResult == 1)
                {
                    // 約定金額が最大約定金額より大きい場合
                    l_maxRow = l_row;
                    l_bdMaxAmount = l_bdAmounts[i];
                    l_intIndex = i;
                }
                else if (l_intResult == 0)
                {
                    if (l_row.getPrice() > l_maxRow.getPrice())
                    {
                        // 約定金額が同額で、約定単価が高い場合
                        l_maxRow = l_row;
                        l_bdMaxAmount = l_bdAmounts[i];
                        l_intIndex = i;
                    }
                    else if (l_row.getPrice() == l_maxRow.getPrice())
                    {
                        EqTypeOrderExecution l_orderExec = null;
                        EqTypeOrderExecution l_orderExecMax = null;
                        try
                        {
                            l_orderExec =
                                (EqTypeOrderExecution)l_orderMgr.getOrderExecution(
                                    l_row.getOrderExecutionId());
                            l_orderExecMax =
                                (EqTypeOrderExecution) l_orderMgr.getOrderExecution(
                                    l_maxRow.getOrderExecutionId());
                        }
                        catch (NotFoundException nfe)
                        {
                            l_strMessage = "株式約定データが見つかりません。";
                            log.error(l_strMessage, nfe);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName()
                                    + "."
                                    + STR_METHOD_NAME,
                                l_strMessage,
                                nfe);
                        }
                        if (l_orderExec.getExecutionSerialNo()
                            > l_orderExecMax.getExecutionSerialNo())
                        {
                            // 約定単価が同額で、約定順番号が大きい場合
                            l_maxRow = l_row;
                            l_bdMaxAmount = l_bdAmounts[i];
                            l_intIndex = i;
                        }
                    }
                }
            }
        }
        log.debug("［合計］約定代金：" + l_bdTotalAmount.doubleValue());
        log.debug("最大約定代金：" + l_bdMaxAmount.doubleValue());
        log.debug("最大約定の順番：" + l_intIndex);

        // ［合計］委託手数料を求める
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_maxRow.getAccountId(),
                    l_maxRow.getSubAccountId());
        }
        catch (NotFoundException nfe)
        {
            l_strMessage =
                "補助口座が見つかりません： account_id("
                    + l_maxRow.getAccountId()
                    + ") sub_account_id ("
                    + l_maxRow.getSubAccountId()
                    + ")";
            log.error(l_strMessage, nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMessage,
                nfe);
        }
        
        // 委託手数料の計算
        WEB3GentradeCommission l_commission =
            this.createCommission(l_eqFinTransactionRows[0].getOrderId());
        l_commission.setExpensesCalcAmount(l_bdTotalAmount.doubleValue());
        calcCommission(l_commission, l_subAccount);

        l_bdTotalCommission = new BigDecimal(l_commission.getCommission());
        log.debug("［合計］委託手数料：" + l_bdTotalCommission.doubleValue());

        // ［合計］消費税の計算を行う
        l_bdTotalSalesTax =
            new BigDecimal(
                calcSalesTax(
                    l_commission.getCommission(),
                    l_commission.getOrderBizDate(),
                    l_subAccount));

        log.debug("［合計］消費税：" + l_bdTotalSalesTax.doubleValue());

        // 分割(n)の委託手数料と消費税、委託手数料の総和、消費税の総和の計算
        // 最大約定は処理をスキップ
        l_dbCommissions = new double[l_intLength];
        l_dbSalesTaxs = new double[l_intLength];
        BigDecimal l_bdProRata;
        //int l_scale = 10;
        int l_scale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);

        for (i = 0; i < l_intLength; i++)
        {
            if (i != l_intIndex)
            {
                l_bdProRata =
                    l_bdAmounts[i].divide(
                        l_bdTotalAmount,
                        l_scale,
                        BigDecimal.ROUND_HALF_EVEN);
                log.debug("［分割(" + i + ")］按分比率：" + l_bdProRata.doubleValue());
                l_bdCommission = l_bdTotalCommission.multiply(l_bdProRata);
                l_dbCommissions[i] = l_bdCommission.longValue();
                log.debug("［分割(" + i + ")］委託手数料：" + l_dbCommissions[i]);
                l_bdSalesTax = l_bdTotalSalesTax.multiply(l_bdProRata);
                l_dbSalesTaxs[i] = l_bdSalesTax.longValue();
                log.debug("［分割(" + i + ")］消費税：" + l_dbSalesTaxs[i]);
                l_bdSigmaCommission =
                    l_bdSigmaCommission.add(new BigDecimal(l_dbCommissions[i]));
                l_bdSigmaSalesTax =
                    l_bdSigmaSalesTax.add(new BigDecimal(l_dbSalesTaxs[i]));
            }
        }
        log.debug("委託手数料の総和：" + l_bdSigmaCommission.doubleValue());
        log.debug("消費税の総和：" + l_bdSigmaSalesTax.doubleValue());

        // 委託手数料、消費税の端数を計算し、最大約定の委託手数料、消費税に寄せる
        l_bdCommission = l_bdTotalCommission.subtract(l_bdSigmaCommission);
        l_dbCommissions[l_intIndex] = l_bdCommission.doubleValue();
        log.debug(
            "［分割(" + l_intIndex + ")］委託手数料：" + l_dbCommissions[l_intIndex]);
        l_bdSalesTax = l_bdTotalSalesTax.subtract(l_bdSigmaSalesTax);
        l_dbSalesTaxs[l_intIndex] = l_bdSalesTax.doubleValue();
        log.debug("［分割(" + l_intIndex + ")］消費税：" + l_dbSalesTaxs[l_intIndex]);

        log.exiting(STR_METHOD_NAME);
        return new ConsolidatedCommissionInfo(
            l_dbCommissions,
            l_bdTotalCommission.doubleValue(),
            l_dbSalesTaxs,
            l_bdTotalSalesTax.doubleValue());
    }

    /**
     * (calc概算簿価単価)<BR>
     * 概算簿価単価を計算する。<BR>
     * <BR>
     * this.calc簿価単価(銘柄ID, 補助口座, 税区分, <BR>
     * 円未満有効桁数)にdelegateする。<BR>
     * 引数の「円未満有効桁数」には0を、<BR>
     * その他の引数には当メソッドの引数の値をそのままセットする。<BR>
     * @@param l_lngProductId - 銘柄ID。
     * 【保有資産テーブル】より簿価を取得する際に使用する。
     * @@param l_subAccount - 補助口座
     * @@param l_taxType - 税区分。
     * @@throws WEB3BaseException
     * @@return double@@throws WEB3BaseException
     * @@roseuid 413D2FEA03D3
     */
    public double calcEstimatedBookPrice(
        long l_lngProductId,
        SubAccount l_subAccount,
        TaxTypeEnum l_taxType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcEstimatedBookPrice(long, SubAccount, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        double l_dbEstimatedBookPrice = 
            this.calcBookValuePrice(l_lngProductId,l_subAccount,l_taxType,0);
        log.exiting(STR_METHOD_NAME);
        
        return l_dbEstimatedBookPrice;
    }

    /**
     * （EqTypeBizLogicProvider.calcCommission(OrderExecution orderexecution)の実装）<BR>
     * <BR>
     * @@param l_orderExecution
     * @@return double
     * @@roseuid 40A3495A00B7
     */
    public double calcCommission(OrderExecution l_orderExecution)
    {
        //ある注文の約定手数料を算出する。
        return 0.0;
    }

    /**
     * （EqTypeBizLogicProvider.calcCapitalGainTax(SubAccount subaccount, TaxTypeEnum taxtypeenum, double d)の実装）<BR>
     * <BR>
     * @@param l_subAccount
     * @@param l_taxTypeEnum
     * @@param l_dblData
     * @@return double
     */
    public double calcCapitalGainTax(
        SubAccount l_subAccount,
        TaxTypeEnum l_taxTypeEnum,
        double l_dblData)
    {
        //譲渡益税の計算を行う。
        return 0.0;
    }

    /**
     * (calc諸経費)<BR>
     * 諸経費を計算し返却する。<BR>
     * <BR>
     * ------------------------------------------------------<BR>
     * ○引数の建区分＝”買建”の場合<BR>
     * <BR>
     * （引数の委託手数料＋委託手数料消費税＋建手数料＋建手数料消費税<BR>
     * 　@＋名義書換料＋名義書換料消費税＋管理費＋管理費消費税<BR>
     * 　@＋順日歩－逆日歩＋その他）の値を計算し返却する。<BR>
     * <BR>
     * ------------------------------------------------------<BR>
     * ○引数の建区分＝”売建”の場合<BR>
     * <BR>
     * （引数の委託手数料＋委託手数料消費税＋建手数料＋建手数料消費税<BR>
     * 　@＋名義書換料＋名義書換料消費税＋管理費＋管理費消費税<BR>
     * 　@－順日歩＋逆日歩＋貸株料＋その他）の値を計算し返却する。<BR>
     * <BR>
     * @@param l_dblCommissionFee - 委託手数料。
     * @@param l_dblCommissionFeeTax - 委託手数料消費税。
     * @@param l_dblSetupFee - 建手数料。
     * @@param l_dblSetupFeeTax - 建手数料消費税。
     * @@param l_dblNameTransferFee - 名義書換料。
     * @@param l_dblNameTransferFeeTax - 名義書換料消費税。
     * @@param l_dblManagementFee - 管理費。
     * @@param l_dblManagementFeeTax - 管理費消費税。
     * @@param l_dblInterestFee - 順日歩。
     * @@param l_dblPayInterestFee - 逆日歩。
     * @@param l_dblLoanEquityFee - 貸株料。
     * @@param l_dblOther - その他。
     * @@param l_contractType - 建区分。
     * @@return double
     * @@roseuid 40E218F400F8
     */
    public double calcExpenses(
        double l_dblCommissionFee,
        double l_dblCommissionFeeTax,
        double l_dblSetupFee,
        double l_dblSetupFeeTax,
        double l_dblNameTransferFee,
        double l_dblNameTransferFeeTax,
        double l_dblManagementFee,
        double l_dblManagementFeeTax,
        double l_dblInterestFee,
        double l_dblPayInterestFee,
        double l_dblLoanEquityFee,
        double l_dblOther,
        ContractTypeEnum l_contractType)
    {
        BigDecimal l_bdCommissionFee = new BigDecimal(l_dblCommissionFee);
        BigDecimal l_bdCommissionFeeTax = new BigDecimal(l_dblCommissionFeeTax);
        BigDecimal l_bdSetupFee = new BigDecimal(l_dblSetupFee);
        BigDecimal l_bdSetupFeeTax = new BigDecimal(l_dblSetupFeeTax);
        BigDecimal l_bdNameTransferFee = new BigDecimal(l_dblNameTransferFee);
        BigDecimal l_bdNameTransferFeeTax = new BigDecimal(l_dblNameTransferFeeTax);
        BigDecimal l_bdManagementFee = new BigDecimal(l_dblManagementFee);
        BigDecimal l_bdManagementFeeTax = new BigDecimal(l_dblManagementFeeTax);
        BigDecimal l_bdInterestFee = new BigDecimal(l_dblInterestFee);
        BigDecimal l_bdPayInterestFee = new BigDecimal(l_dblPayInterestFee);
        BigDecimal l_bdLoanEquityFee = new BigDecimal(l_dblLoanEquityFee);
        BigDecimal l_bdOther = new BigDecimal(l_dblOther);
        
        final String STR_METHOD_NAME =
            "calcExpenses(double, double, double, double, double, double, double, double, double, double, double, double,ContractTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        double l_dbExpenses;
        //○引数の建区分＝”買建”の場合
        if(ContractTypeEnum.LONG.equals(l_contractType))
        {
            //（引数の委託手数料＋委託手数料消費税＋建手数料＋建手数料消費税
            // ＋名義書換料＋名義書換料消費税＋管理費＋管理費消費税
            // ＋順日歩－逆日歩＋その他）の値を計算し返却する。
            l_dbExpenses = l_bdCommissionFee
                .add(l_bdCommissionFeeTax)
                .add(l_bdSetupFee)
                .add(l_bdSetupFeeTax)
                .add(l_bdNameTransferFee)
                .add(l_bdNameTransferFeeTax)
                .add(l_bdManagementFee)
                .add(l_bdManagementFeeTax)
                .add(l_bdInterestFee)
                .subtract(l_bdPayInterestFee)
                .add(l_bdOther)
                .doubleValue();
        }
        // ○引数の建区分＝”売建”の場合
        else if(ContractTypeEnum.SHORT.equals(l_contractType))
        {
            //（引数の委託手数料＋委託手数料消費税＋建手数料＋建手数料消費税
            // ＋名義書換料＋名義書換料消費税＋管理費＋管理費消費税
            // －順日歩＋逆日歩＋貸株料＋その他）の値を計算し返却する。
            l_dbExpenses = l_bdCommissionFee
                .add(l_bdCommissionFeeTax)
                .add(l_bdSetupFee)
                .add(l_bdSetupFeeTax)
                .add(l_bdNameTransferFee)
                .add(l_bdNameTransferFeeTax)
                .add(l_bdManagementFee)
                .add(l_bdManagementFeeTax)
                .subtract(l_bdInterestFee)
                .add(l_bdPayInterestFee)
                .add(l_bdLoanEquityFee)
                .add(l_bdOther)
                .doubleValue();
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "建区分 = " + l_contractType);
        }
        
        log.debug("****** 建区分：[" + l_contractType + "]");
        log.debug("****** 諸経費：[" + l_dbExpenses + "]");
        log.exiting(STR_METHOD_NAME);
        return l_dbExpenses;
                       
    }

    /**
     * (calc簿価単価)<BR>
     * <BR>
     * 簿価単価を計算する。<BR>
     * <BR>
     * １）　@株式ポジションマネージャ.get保有資産(口座ID, 補助口座ID, 銘柄ID, 引<BR>数の税区分)により、<BR>
     * 　@　@　@保有資産オブジェクトを取得する。<BR>
     * 　@　@　@該当データなし時は例外をthrowする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00204<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------
     * <BR>
     * 　@　@　@＜株式ポジションマネージャ.get保有資産( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@口座ID：　@引数の補助口座.口座ID<BR>
     * 　@　@　@補助口座ID：　@引数の補助口座.補助口座ID<BR>
     * 　@　@　@銘柄ID：　@引数の銘柄ID<BR>
     * 　@　@　@税区分：　@引数の税区分<BR>
     * 　@　@　@----------------------------------------------------------
     * <BR>
     * ２）　@簿価単価を求め、計算結果を返却する。<BR>
     * <BR>
     * 　@　@　@簿価単価＝保有資産.簿価（簿価単価計算用） ÷ 保有資産.数量（簿価単価計算用）<BR>
     * 　@　@※計算結果を、引数の「円未満有効桁数」までの桁数に、切捨てにより丸める。<BR>
     * <BR>
     * @@param l_lngProductId - 銘柄ID。
     * 【保有資産テーブル】より簿価を取得する際に使用する。
     * @@param l_subAccount - 補助口座
     * @@param l_taxType - 税区分。
     * @@param l_lngUnderYenEffectiveBit - (円未満有効桁数)<BR>
     * 円未満（小数点以下）第何位までを有効とするかを指定する。<BR>
     * ex.) 小数点以下第５位を切捨てし、小数点以下第４位までの値を返す場合は、<BR>
     * 　@　@　@「４」を指定する。<BR>
     * <BR>
     * @@return double
     * @@roseuid 40EDE63B035B
     */
    public double calcBookValuePrice(
        long l_lngProductId,
        SubAccount l_subAccount,
        TaxTypeEnum l_taxType,
        long l_lngUnderYenEffectiveBit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "calcBookValuePrice(long, SubAccount, TaxTypeEnum, long)";
        log.entering(STR_METHOD_NAME);
        
        if(l_lngUnderYenEffectiveBit < 0)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "引数の「円未満有効桁数」 = " + l_lngUnderYenEffectiveBit);
        }
        
        //１）株式ポジションマネージャ.get保有資産(口座ID, 補助口座ID, 銘柄ID, 引数の税区分)
        //   により、保有資産オブジェクトを取得する。該当データなし時は例外をthrowする。
        
        // get 株式ポジションマネージャ
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        // get 保有資産
        EqTypeAsset l_asset;

        l_asset =
            l_positionManager.getAsset(
                l_subAccount.getAccountId(),
                l_subAccount.getSubAccountId(),
                l_lngProductId,
                l_taxType);
                
        if (l_asset == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass() + "." + STR_METHOD_NAME,
                "保有資産テーブルに該当するデータがありません");
        }
        
        // ２）　@簿価単価を求め、計算結果を返却する。
        
        AssetRow l_assetRow =
            (AssetRow)l_asset.getDataSourceObject();
        int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
        //get 保有資産.数量（簿価単価計算用）
        double l_dblTotalQuantity = l_assetRow.getQuantityForBookValue();
        BigDecimal l_bdTotalQutity = new BigDecimal(l_dblTotalQuantity);
        if (l_dblTotalQuantity == 0.0D)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass() + "." + STR_METHOD_NAME,
                "保有資産テーブル.数量（簿価単価計算用）に0が設定されています");
        }
        //get 保有資産.簿価（簿価単価計算用）
        double l_dbBookValue = l_assetRow.getBookValue();
        BigDecimal l_bdBookValue = new BigDecimal(l_dbBookValue);
        
        //簿価単価＝保有資産.簿価÷保有資産.数量
        BigDecimal l_bdBookPrice =
            l_bdBookValue.divide(
                l_bdTotalQutity,
                l_intScale,
                BigDecimal.ROUND_HALF_EVEN);
                
        log.debug("****** 保有資産.簿価（簿価単価計算用）：[" + l_dbBookValue + "]");
        log.debug("****** 保有資産.数量（簿価単価計算用）：[" + l_dblTotalQuantity + "]");
        log.debug("****** 簿価単価(桁数切捨前)＝保有資産.簿価÷保有資産.数量：[" + l_bdBookPrice.doubleValue() + "]");
                
        //計算結果を、引数の「円未満有効桁数」までの桁数に、切捨てにより丸める  
        long l_lngBaseValue = 1;
        for(int i = 0; i < l_lngUnderYenEffectiveBit; i++)
        {
            l_lngBaseValue = l_lngBaseValue * 10;
        }
        BigDecimal l_bdBaseValue = new BigDecimal(l_lngBaseValue);
        l_bdBookPrice = l_bdBookPrice.multiply(l_bdBaseValue);
        long l_lngBookPrice = (long)l_bdBookPrice.doubleValue();
        l_bdBookPrice = new BigDecimal(l_lngBookPrice);
        l_bdBookPrice = 
            l_bdBookPrice.divide(
                l_bdBaseValue,
                l_intScale,
                BigDecimal.ROUND_HALF_EVEN);
        
        log.debug("****** 引数の「円未満有効桁数」：[" + l_lngUnderYenEffectiveBit + "]");
        log.debug("****** 簿価単価(桁数切捨後)：[" + l_bdBookPrice.doubleValue() + "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_bdBookPrice.doubleValue();
    }

    /**
     * （create手数料）<BR>
     * <BR>
     * 手数料オブジェクトを生成し、引数の内容よりプロパティをセットして返却する。<BR>
     * <BR>
     * １）　@手数料インスタンスを生成する。<BR>
     * <BR>
     * ２）　@プロパティを以下の通りに設定する。<BR>
     * <BR>
     * ====================================================================<BR>
     * 証券会社コード： 引数の補助口座オブジェクト.証券会社コード<BR>
     * 部店ID： 引数の補助口座オブジェクト.部店ID<BR>
     * 手数料商品コード： 手数料商品コード.上場株式<BR>
     * 取引コード（SONAR）： <BR>
     * 　@---------------------------------------------------------------<BR>
     * 　@＜引数の信用取引区分＝DEFAULTの場合＞<BR>
     * 　@　@　@取引コード（SONAR）.普通株式<BR>
     * 　@---------------------------------------------------------------<BR>
     * 　@＜引数の信用取引区分≠DEFAULTの場合＞<BR>
     * 　@　@　@注文カテゴリ＝新規建注文の場合、取引コード（SONAR）.信用建<BR>
     * 　@　@　@注文カテゴリ＝返済注文の場合、取引コード（SONAR）.信用返済<BR>
     * 　@　@　@注文カテゴリ＝現引・現渡注文の場合、取引コード（SONAR）.現引現渡<BR>
     * 　@　@　@※注文カテゴリが上記以外の場合は、例外をthrowする。<BR>
     * 　@---------------------------------------------------------------<BR>
     * 発注日：　@引数の発注日<BR>
     * 弁済区分：<BR>
     * 　@引数の信用取引区分＝DEFAULTの場合、<BR>
     * 　@　@　@00：その他<BR>
     * 　@引数の信用取引区分≠DEFAULTの場合、<BR>
     * 　@　@　@（部店市場弁済別）取扱条件のインスタンスを取得し、<BR>
     * 　@　@　@取得したインスタンス.弁済区分（SONAR）の値をセット。<BR>
     * 　@<BR>
     * 　@　@　@[（部店市場弁済別）取扱条件・コンストラクタ引数]<BR>
     * 　@　@　@証券会社コード：　@引数の補助口座.証券会社コード<BR>
     * 　@　@　@部店コード：　@引数の補助口座.部店IDの部店オブジェクト.部店コード<BR>
     * 　@　@　@市場コード：　@引数の市場コード<BR>
     * 　@　@　@弁済区分：　@引数の信用取引区分<BR>
     * 　@　@　@弁済期限値：　@引数の弁済期限値<BR>
     * <BR>
     * 注文チャネル： 引数の注文チャネル<BR>
     * 原注文注文チャネル： 引数の注文チャネル<BR>
     * 市場コード（SONAR）：<BR>
     * 　@引数の市場コード≠nullの場合は、拡張金融オブジェクトマネージャ.get市場(<BR>
     * 　@　@　@補助口座.証券会社コード, 引数の市場コード)に該当する市場オブジェクト.市場コード（SONAR）<BR>
     * 　@　@　@をセット。<BR>
     * 　@引数の市場コード＝nullの場合は、nullをセット。<BR>
     * ====================================================================<BR>
     * <BR>
     * ※以下のプロパティは設定しない。<BR>
     * is指値<BR>
     * 手数料No<BR>
     * 手数料No枝番<BR>
     * 手数料金額<BR>
     * 諸経費計算用代金<BR>
     * 原注文手数料No<BR>
     * 原注文手数料No枝番<BR>
     * 手数料コースコード<BR>
     * <BR>
     * ３）　@生成した手数料オブジェクトのインスタンスを返却する。<BR>
     * <BR>
     * @@param l_genSubAccount - (補助口座) <BR>    
     * @@param l_strMarketCode - (市場コード) <BR>    
     * @@param l_datBizDate - (発注日) <BR>          
     * @@param l_strOrderChanel - (注文チャネル) <BR> 
     *     0:営業店　@1：インターネット　@2：コールセンター　@3：モバイル<BR> 
     * @@param l_strMarginType - (信用取引区分) <BR> 
     *     0：DEFAULT（信用取引以外）　@1：制度信用　@2：一般信用<BR> 
     * @@param l_dblRepaymentNum - (弁済期限値) <BR>   
     * @@param l_orderCateg - (注文カテゴリ) <BR> 
     *    ※信用取引区分≠0：DEFAULT以外の場合のみ、設定要。<BR>                      
     *    3：新規建注文　@5：返済注文　@7：現引・現渡注文 <BR>                                                                                                              
     */
    public WEB3GentradeCommission createCommission(
        WEB3GentradeSubAccount l_genSubAccount,
        String l_strMarketCode,
        Date l_datBizDate,
        String l_strOrderChanel,
        String l_strMarginType,
        double l_dblRepaymentNum,
        OrderCategEnum l_orderCateg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createCommission(SubAccount, String, Date, String, String, double, OrderCategEnum)";
        log.entering(STR_METHOD_NAME);
        
        SubAccountRow l_subAccountRow = (SubAccountRow)l_genSubAccount.getDataSourceObject();
        
        //１）　@手数料インスタンスを生成する。
        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();
        
        //２）　@プロパティを以下の通りに設定する。
        
        //get 証券会社コード
        String l_strInstitutionCode = l_genSubAccount.getInstitution().getInstitutionCode();
        
        //get 部店コード
        WEB3GentradeBranch l_gentradeBranch;
        try
        {
            l_gentradeBranch = new WEB3GentradeBranch(l_subAccountRow.getBranchId());
        }
        catch (DataException de)
        {
            // DBアクセスエラー。
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
                               
        //2-1) 証券会社コード： 引数の補助口座オブジェクト.証券会社コード
        l_gentradeCommission.setInstitutionCode(l_strInstitutionCode);
            
        //2-2) 部店ID： 引数の補助口座オブジェクト.部店ID
        l_gentradeCommission.setBranchId(l_subAccountRow.getBranchId());
        //2-3）手数料商品コード： 手数料商品コード.上場株式
        l_gentradeCommission.setCommissionProductCode(
            WEB3CommisionProductCodeDef.LISTING_STOCK);
        
        //2-4) 取引コード（SONAR）：   
        // ＜引数の信用取引区分＝DEFAULTの場合＞
        //　@   取引コード（SONAR）.普通株式
        // ＜引数の信用取引区分≠DEFAULTの場合＞
        // 　@　@注文カテゴリ＝新規建注文の場合、取引コード（SONAR）.信用建 
        // 　@　@注文カテゴリ＝返済注文の場合、取引コード（SONAR）.信用返済 
        // 　@　@注文カテゴリ＝現引・現渡注文の場合、取引コード（SONAR）.現引現渡 
        //　@　@※注文カテゴリが上記以外の場合は、例外をthrowする。
        String l_strSonarTradedCode;
        if(WEB3MarginTradingDivDef.DEFAULT.equals(l_strMarginType))
        {
                l_strSonarTradedCode = 
                    WEB3TransactionTypeSONARDef.MARKET_TRADING;
        }
        else
        {
            if(OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg))
            {
                l_strSonarTradedCode = 
                    WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
            }
            else if(OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
            {
                l_strSonarTradedCode = 
                    WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
            }
            else if(OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
            {
                l_strSonarTradedCode = 
                    WEB3TransactionTypeSONARDef.SWAP_CONTRACT;
            }
            else
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文カテゴリ = " + l_orderCateg);
            }
        }
        l_gentradeCommission.setSonarTradedCode(l_strSonarTradedCode);
        
        //2-5) 発注日：　@引数の発注日 
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
        
        //2-6) 弁済区分：                                            
        //  引数の信用取引区分＝DEFAULTの場合、00：その他 
        //  引数の信用取引区分≠DEFAULTの場合、
        //   （部店市場弁済別）取扱条件のインスタンスを取得し、
        //    取得したインスタンス.弁済区分（SONAR）の値をセット。
        String l_strPayType;
        if(WEB3MarginTradingDivDef.DEFAULT.equals(l_strMarginType))
        {
            l_strPayType = WEB3PayTypeDef.OTHER;
        }
        else
        {
            //（部店市場弁済別）取扱条件のインスタンスを取得する 
            WEB3GentradeBranchMarketRepayDealtCond l_genBranchMarketRepayDealtCond = 
                new WEB3GentradeBranchMarketRepayDealtCond(
                    l_strInstitutionCode,//引数の補助口座.証券会社コード
                    l_gentradeBranch.getBranchCode(),//引数の補助口座.部店IDの部店オブジェクト.部店コード
                    l_strMarketCode,//引数の市場コード
                    l_strMarginType,//引数の信用取引区分
                    l_dblRepaymentNum//引数の弁済期限値
                    );   
            BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow = 
                (BranchMarketRepayDealtCondRow)
                l_genBranchMarketRepayDealtCond.getDataSourceObject();
            l_strPayType = 
                l_branchMarketRepayDealtCondRow.getSonarRepaymentType();
        }
        l_gentradeCommission.setPayType(l_strPayType);

        //2-7) 注文チャネル： 引数の注文チャネル
        l_gentradeCommission.setOrderChannel(l_strOrderChanel);    
                              
        //2-8) 原注文注文チャネル： 引数の注文チャネル
        l_gentradeCommission.setOrgOrderChannel(l_strOrderChanel);    
        
        //市場コード（SONAR）
        if (l_strMarketCode != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                Market l_market =
                    l_finObjectManager.getMarket(l_strInstitutionCode, l_strMarketCode);
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                l_gentradeCommission.setSonarMarketCode(l_marketRow.getSonarMarketCode());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_nfe.getMessage(),
                   l_nfe);
            }
        }
        else
        {
            l_gentradeCommission.setSonarMarketCode(null);
        }
        
        // ３）　@生成した手数料オブジェクトのインスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return  l_gentradeCommission;

    }
    
    /**
     * (calc譲渡益税)<BR>
     * 譲渡益税を計算する。<BR>
     * （double calcCapitalGainTax(SubAccount sub_account, TaxTypeEnum tax, double amount)<BR>
     * 　@のオーバーロード）<BR>
     * <BR>
     * １）　@引数の税区分＝”一般口座”　@or ”ストックオプション口座”の場合は、０を返却する。<BR>
     * <BR>
     * ２）　@引数の税区分が上記以外の場合（＝特定口座の場合）、<BR>
     * 　@　@　@引数の顧客税区分より、譲渡益税徴収対象かどうかを判定する。<BR>
     * <BR>
     * 　@　@　@引数の顧客税区分＝”特定口座かつ源泉徴収”の場合：　@「譲渡益税徴収対象」。<BR>
     * 　@　@　@上記以外の場合：　@「譲渡益税徴収対象外」。<BR>
     * <BR>
     * 　@　@　@譲渡益税徴収対象外の場合は０を返却する。<BR>
     * <BR>
     * ３）　@「譲渡益税徴収対象」の場合は、以下の処理を行う。<BR>
     * <BR>
     * ３－１）　@譲渡益税を計算し、計算結果を返却する。<BR>
     * <BR>
     * 　@　@・税率テーブルを検索し、基準日に対応する譲渡益税率を取得する。<BR>
     * 　@　@　@※証券会社コード：　@引数の補助口座.証券会社コード<BR>
     * 　@　@・譲渡益税 ＝ 引数の金額 × 譲渡益税率<BR>
     * 　@　@※計算結果を円未満切捨て。<BR>
     * 　@　@※マイナス値の場合も、そのまま返却する。<BR>
     * <BR>
     * @@param l_subAccount - (SubAccount)<BR>
     * @@param l_taxType - (税区分)<BR>
     * @@param l_dblAmount - (金額)<BR>
     * @@param l_baseDate - (基準日)<BR>
     *      譲渡益税を算出する基準日<BR>
     * @@param l_accountTaxType - (顧客税区分)<BR>
     * 客の税区分。<BR>
     * （顧客.税区分／税区分（次年）／信用取引税区分／信用取引税区分（次年））<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double calcCapitalGainTax(
        SubAccount l_subAccount,
        TaxTypeEnum l_taxType,
        double l_dblAmount,
        Timestamp l_baseDate,
        TaxTypeEnum l_accountTaxType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcCapitalGainTax(TaxTypeEnum, double, Timestamp)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@引数の税区分＝”一般口座”or ”ストックオプション口座”の場合は、０を返却する。
        if (TaxTypeEnum.NORMAL.equals(l_taxType) || TaxTypeEnum.STOCK_OPTION.equals(l_taxType))
        {
            log.debug("譲渡益税徴収対象外");
            log.exiting(STR_METHOD_NAME);
            return 0D;
        }
        // ２）　@引数の税区分が上記以外の場合（＝特定口座の場合）
        // 　@　@　@引数の顧客税区分より、譲渡益税徴収対象かどうかを判定する
        else
        {
            if (!TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_accountTaxType))
            {
                log.debug("譲渡益税徴収対象外");
                log.exiting(STR_METHOD_NAME);
                return 0D;
            }
        }
        
        // ３）　@「譲渡益税徴収対象」の場合は、以下の処理を行う。
        // ３－１）　@譲渡益税を計算し、計算結果を返却する。
        //　@　@　@・税率テーブルを検索し、基準日に対応する譲渡益税率を取得する。
        WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(
            l_subAccount.getInstitution().getInstitutionCode(),
            WEB3GentradeTaxRate.CAPITAL_GAIN_TAX,
            l_baseDate);
        
        //　@　@　@・譲渡益税 ＝ 引数の金額 × 譲渡益税率
        BigDecimal l_oneHandred =
            new BigDecimal(WEB3GentradeNumberConstDef.HYAKU);
        int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
        BigDecimal l_bdAmount = new BigDecimal(l_dblAmount);
        BigDecimal l_bdTaxRate =
            new BigDecimal(l_taxRate.getTaxRate()).divide(
                l_oneHandred,
                l_intScale,
                BigDecimal.ROUND_HALF_EVEN);
        int l_intCapitalGainTax = l_bdAmount.multiply(l_bdTaxRate).intValue();
        
        log.debug("****** 引数の金額：[" + l_dblAmount + "]");
        log.debug("****** 譲渡益税率：[" + l_bdTaxRate.doubleValue() + "]");
        log.debug("****** 計算結果を円未満切捨て");
        log.debug("****** 譲渡益税 ＝ 引数の金額 × 譲渡益税率 ：[" + l_intCapitalGainTax + "]");
        log.exiting(STR_METHOD_NAME);
        return (double)l_intCapitalGainTax;
    }

    /**
     * （get手数料コースコード）<BR>
     * <BR>
     * 委託手数料条件登録マスターから手数料コースコードを取得する。<BR>
     * <BR>
     * -------------------<BR>
     * １）　@委託手数料条件登録マスターを下記条件で検索する。<BR>
     * <BR>
     * 　@　@　@証券会社コード　@＝　@引数.証券会社コード<BR>
     * 　@　@　@手数料商品コード　@＝　@引数手数料商品コード<BR>
     * 　@　@　@登録NO　@＝　@引数.手数料NO＋引数.枝番<BR>
     * 　@　@　@適用開始年月日　@≦　@引数.発注日　@≦　@適用終了年月日<BR>
     * <BR>
     * 　@　@該当データなしの場合、または複数行レコードが取得された場合は<BR>
     * 　@　@例外をスローする。<BR>
     * <BR>
     * ２）　@取得したレコードの手数料コースコードを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strCommProductCode - (手数料商品コード)<BR>
     * @@param l_strRegNo - (手数料NO)<BR>
     * @@param l_strRevision - (枝番)<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * @@throws WEB3BaseException
     * @@return String
     */
     public String getCommissionCourseDiv(
         String l_strInstitutionCode,
         String l_strCommProductCode,
         String l_strRegNo,
         String l_strRevision,
         Date l_datBizDate)
         throws WEB3BaseException
     {
         final String STR_METHOD_NAME =
             "getCommissionCourseDiv(String, String, String, String, Date)";
         log.entering(STR_METHOD_NAME);

         String l_strCommissionCourseDiv = null;
         try
         {
             // １）　@委託手数料条件登録マスターを下記条件で検索する。
             // 　@　@　@証券会社コード　@＝　@引数.証券会社コード
             // 　@　@　@手数料商品コード　@＝　@引数手数料商品コード
             // 　@　@　@登録NO　@＝　@引数.手数料NO＋引数.枝番
             // 　@　@　@適用開始年月日　@≦　@引数.発注日　@≦　@適用終了年月日
             String l_strWhere = "institution_code = ? and comm_product_code = ? and reg_no = ? and appli_start_date <= ? and appli_end_date >= ?";
             QueryProcessor l_qp = l_qp = Processors.getDefaultProcessor();
             List l_lisRows = null;
             Object[] l_objWhereValues = { l_strInstitutionCode, l_strCommProductCode, l_strRegNo + l_strRevision, l_datBizDate, l_datBizDate };
             l_lisRows = l_qp.doFindAllQuery(EquityCommCondMstRow.TYPE, l_strWhere, l_objWhereValues);
             int l_intRowSize = l_lisRows.size();
             // 該当データなしの場合、または複数行レコードが取得された場合は例外をスローする。
             if (l_intRowSize == 0)
             {
                 log.error("委託手数料条件登録マスターに該当するデータが件存在しません。");
                 throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "委託手数料条件登録マスターに該当するデータが件存在しません。");
             }
             else if (l_intRowSize > 1)
             {
                 log.error("委託手数料条件登録マスターに該当データが " + l_intRowSize + " 件存在します。");
                 throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "委託手数料条件登録マスターに該当データが " + l_intRowSize + " 件存在します。");
             }
             
             l_strCommissionCourseDiv = ((EquityCommCondMstRow)l_lisRows.get(0)).getCommissionCourseDiv();
         }
         catch (DataNetworkException l_dnwe)
         {
             log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_dnwe.getMessage(),
                 l_dnwe);
         }
         catch (DataQueryException l_dqe)
         {
             log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_dqe.getMessage(),
                 l_dqe);
         }

         // ２）　@取得したレコードの手数料コースコードを返却する。
         log.exiting(STR_METHOD_NAME);
         return l_strCommissionCourseDiv;
    }
    
    /**
     * (create手数料)<BR>
     * <BR>
     * 約定時の委託手数料計算に使用する手数料オブジェクトを生成する。<BR>
     * <BR>
     * １）　@this.create手数料(引数の注文単位.注文ID)により、手数料オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@手数料.is指値 プロパティのセットし直しが必要な場合(*1)、<BR>
     * 　@　@　@手数料.setIs指値(false)により、手数料.is指値 プロパティを"成行"に変更する。<BR>
     * <BR>
     * 　@　@　@(*1)手数料.is指値 プロパティのセットし直しが必要な場合：<BR>
     * 　@　@　@　@　@成行残数指値注文、かつ 初回約定 かつ 一部出来 の場合のみ。<BR>
     * 　@　@　@　@　@※今回約定により指値注文となるが、初回は成行で計算するため。<BR>
     * <BR>
     * 　@　@　@　@　@引数の注文単位.値段条件=="成行残数指値"<BR>
     * 　@　@　@　@　@かつ 引数の注文単位.約定数量==約定.約定数量<BR>
     * 　@　@　@　@　@かつ 引数の注文単位.isPartiallyExecuted( )==true（＝一部約定）<BR>
     * 　@　@　@　@　@かつ 引数の約定.削除フラグ==FALSE（≠約定取消）<BR>
     * <BR>
     * ３）　@作成した手数料オブジェクトを返す。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@param l_orderExec - (約定)<BR>
     * 約定オブジェクト。
     * @@return WEB3GentradeCommission
     * @@throws WEB3BaseException
     */
    public WEB3GentradeCommission createCommission(
        EqTypeOrderUnit l_orderUnit,
        EqTypeOrderExecution l_orderExec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCommission(EqTypeOrderUnit, EqTypeOrderExecution)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCommission l_commission = this.createCommission(l_orderUnit.getOrderId());
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderExecutionRow l_orderExecRow =
            (EqtypeOrderExecutionRow)l_orderExec.getDataSourceObject();
        if (WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(l_orderUnitRow.getPriceConditionType()) &&
            l_orderUnitRow.getExecutedQuantity() == l_orderExecRow.getExecQuantity() &&
            l_orderUnit.isPartiallyExecuted() &&
            BooleanEnum.FALSE.equals(l_orderExecRow.getDeleteFlag()))
        {
            l_commission.setIsLimitPrice(false);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_commission;
    }
    
    /**
     * (calc概算簿価単価)<BR>
     * <BR>
     * 概算簿価単価を計算する。<BR>
     * <BR>
     * 　@パラメータ.簿価 ÷ パラメータ.数量の計算結果を返却する。<BR>
     * 　@※計算結果を、引数の「円未満有効桁数」までの桁数に、切捨てにより丸める。<BR>
     * <BR>
     * @@param l_dblBookValue - 簿価<BR>
     * 簿価<BR>
     * @@param l_dblQuantity - 数量<BR>
     * 数量<BR>
     * @@param l_intUnderYenEffectiveBit - 円未満有効桁数<BR>
     * 円未満（小数点以下）第何位までを有効とするかを指定する。<BR>
     * ex.) 小数点以下第５位を切捨てし、小数点以下第４位までの値を返す場合は、<BR>
     * 　@　@　@「４」を指定する。<BR>
     * <BR>
     * @@return double
     */
    public double calcEstimatedBookPrice(
        double l_dblBookValue,
        double l_dblQuantity,
        int l_intUnderYenEffectiveBit)
    {
        final String STR_METHOD_NAME = 
            "calcEstimatedBookPrice(double, double, long)";
        log.entering(STR_METHOD_NAME);
        
        if(l_intUnderYenEffectiveBit < 0)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "引数の「円未満有効桁数」 = " + l_intUnderYenEffectiveBit);
        }
        
        //簿価単価＝簿価÷数量
        BigDecimal l_bdBookValue = new BigDecimal(l_dblBookValue);
        BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity);
        BigDecimal l_bdBookPrice =
            l_bdBookValue.divide(
                l_bdQuantity,
                l_intUnderYenEffectiveBit,
                BigDecimal.ROUND_DOWN);
                
        log.debug("****** パラメータ.簿価：[" + l_dblBookValue + "]");
        log.debug("****** パラメータ.数量：[" + l_dblQuantity + "]");
        log.debug("****** パラメータ.円未満有効桁数：[" + l_intUnderYenEffectiveBit + "]");
        log.debug("****** 概算簿価単価＝保有資産.簿価÷保有資産.数量：[" + l_bdBookPrice.doubleValue() + "]");
                
        log.exiting(STR_METHOD_NAME);
        return l_bdBookPrice.doubleValue();
    }
    
    /**
     * （create手数料）<BR>
     * <BR>
     * 概算売買代金計算に使用する手数料オブジェクトを生成し、<BR>
     * 引数で指定された注文単位オブジェクトよりプロパティをセットする。<BR>
     * <BR>
     * １）　@手数料インスタンスを生成し、プロパティに以下の通りに注文単位オブジェクトの値をセットする。<BR> 
     * <BR>
     * 　@証券会社コード： パラメータ.注文単位.部店IDから部店オブジェクトを取得し、<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@部店オブジェクトの同項目をセット <BR>
     * 　@部店ID： パラメータ.注文単位の同項目 <BR>
     * 　@手数料商品コード： パラメータ.注文単位の同項目<BR> 
     * 　@取引コード（SONAR）： パラメータ.注文単位の同項目<BR>
     * 　@発注日：　@パラメータ.注文単位の同項目<BR> 
     * 　@弁済区分： (*1)<BR>
     * 　@注文チャネル： パラメータ.注文単位.初回注文の注文チャネル<BR> 
     * 　@原注文注文チャネル： パラメータ.注文単位.初回注文の注文チャネル<BR> 
     * 　@原注文手数料No： パラメータ.注文単位.初回注文の手数料No <BR>
     * 　@原注文手数料No枝番： パラメータ.注文単位.初回注文の手数料No枝番<BR> 
     * 　@is指値：　@パラメータ.注文単位.isMarketOrder( )==falseの場合はtrueを、<BR> 
     * 　@　@　@　@　@　@　@パラメータ.注文単位.isMarketOrder( )==trueの場合はfalseを、それぞれセット<BR> 
     * 　@市場コード（SONAR）：　@パラメータ.注文単位.市場IDに該当する <BR>
     * 　@　@　@　@　@　@　@市場オブジェクト.市場コード（SONAR）をセット <BR>
     * <BR>
     * 　@(*1)弁済区分<BR>
     * 　@　@・パラメータ.注文単位.弁済区分（SONAR）==nullの場合<BR> 
     * 　@　@　@　@弁済区分.その他（"00"）をセット（固定）<BR>
     * 　@　@・パラメータ.注文単位.弁済区分（SONAR）!=nullの場合<BR> 
     * 　@　@　@　@パラメータ.注文単位.弁済区分（SONAR）をそのままセット<BR> 
     * <BR>
     * ※以下のプロパティは設定しない。<BR>
     * 手数料No<BR>
     * 手数料No枝番<BR>
     * 手数料金額<BR>
     * 諸経費計算用代金<BR>
     * 手数料コースコード<BR>
     * <BR>
     * ２）　@生成した手数料オブジェクトのインスタンスを返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位
     * @@return WEB3GentradeCommission<BR>
     * @@throws WEB3BaseException
     */
    public WEB3GentradeCommission createCommission(EqTypeOrderUnitImpl l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCommission(EqTypeOrderUnitImpl)";

        log.entering(STR_METHOD_NAME);

        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        //手数料インスタンスを生成し、プロパティに以下の通りに
        //注文単位オブジェクトの値をセットする。
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //部店IDから部店オブジェクトを取得する
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = new WEB3GentradeBranch(l_orderUnit.getBranchId());
        }
        catch (DataException l_de)
        {
            // DBアクセスエラー。
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        //証券会社コード：注文単位オブジェクト.部店IDから部店オブジェクトを取得し、
        // 部店オブジェクトの同項目をセット 
        l_commission.setInstitutionCode(
            l_branch.getInstitution().getInstitutionCode());
        //部店ID： 注文単位オブジェクトの同項目
        l_commission.setBranchId(l_orderUnit.getBranchId());
        //手数料商品コード： 注文単位オブジェクトの同項目
        l_commission.setCommissionProductCode(
            l_orderUnitRow.getCommProductCode());
        //取引コード（SONAR）：注文単位オブジェクトの同項目
        l_commission.setSonarTradedCode(
            l_orderUnitRow.getSonarTradedCode());
        
        //発注日：　@注文単位オブジェクトの同項目
        String l_strBizDate = l_orderUnitRow.getBizDate();
        Date l_datBizDate = null;
        try
        {
            l_datBizDate = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().parse(l_strBizDate);
        }
        catch (ParseException l_pe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_pe.getMessage(),
                l_pe);
        }
        l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
        //弁済区分：　@注文単位オブジェクト.弁済区分（SONAR）
        if (l_orderUnitRow.getSonarRepaymentType() != null)
        {
            l_commission.setPayType(l_orderUnitRow.getSonarRepaymentType());
        }
        else
        {
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
        }
        //原注文注文チャネル： 注文単位オブジェクト.初回注文の注文チャネル
        l_commission.setOrderChannel(l_orderUnitRow.getOrderChanel());
        //原注文手数料No： 注文単位オブジェクト.初回注文の手数料No
        l_commission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
        //原注文手数料No枝番： 注文単位オブジェクト.初回注文の手数料No枝番
        l_commission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
        // is指値：　@注文単位オブジェクト.isMarketOrder()==falseの場合はtrueを、
        //　@注文単位オブジェクト.isMarketOrder()==trueの場合はfalseを、それぞれセット
        if (l_orderUnit.isMarketOrder() == false)
        {
            l_commission.setIsLimitPrice(true);
        }
        else
        {
            l_commission.setIsLimitPrice(false);
        }
        //市場コード（SONAR）
        l_commission.setSonarMarketCode(l_orderUnitRow.getSonarMarketCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_commission;
    }
    
    /**
     * (calc概算譲渡損益)<BR>
     * <BR>
     * 譲渡損益の金額を計算する。 <BR>
     * ※税区分　@=　@"一般"　@の場合は概算譲渡損益を計算する。<BR>
     * <BR>
     * １）　@譲渡損益を計算する。<BR>
     * <BR>
     * 　@　@　@譲渡損益 ＝ 引数の金額－（引数の売数量 × 簿価単価(*1)） <BR>
     * 　@　@　@※（引数の売数量 × 簿価単価）の結果を円未満切捨てした後、引数の金額から減算する。<BR>
     * <BR>
     *       計算した譲渡損益を返却する。<BR>
     * <BR>
     *       (*1)簿価単価：株式計算サービス.calc簿価単価( )で取得。<BR>
     * 　@　@　@　@　@-----------------------------------------------------------<BR>
     * 　@　@　@　@　@＜calc簿価単価( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@　@　@銘柄ID：　@引数の銘柄ID <BR>
     * 　@　@　@　@　@補助口座：　@引数の補助口座<BR>
     * 　@　@　@　@　@税区分：　@引数の税区分<BR>
     * 　@　@　@　@　@円未満有効桁数：　@4（小数点第５位を切捨て）<BR>
     * 　@　@　@　@　@-----------------------------------------------------------<BR>
     * <BR>
     * @@param l_dblExpensesCalcAmount - (金額)<BR>
     * @@param l_dblOrderQuantity - (売数量)<BR>
     * @@param l_lngProductId - 銘柄ID。<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_taxType - 税区分<BR>
     * @@return double<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D2FEA01AC
     */
    public double calcEstimatedCapitalGain(
        double l_dblExpensesCalcAmount,
        double l_dblOrderQuantity,
        long l_lngProductId,
        SubAccount l_subAccount,
        TaxTypeEnum l_taxType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcEstimatedCapitalGain(double, double, long, SubAccount, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //１）　@譲渡損益を計算する。 
        //  (*1)簿価単価：株式計算サービス.calc簿価単価( )で取得。 
        double l_dblBookValuePrice = this.calcBookValuePrice(
          	l_lngProductId,
           	l_subAccount,
           	l_taxType,
           	4);

        //　@譲渡損益 ＝ 引数の金額－（引数の売数量 × 簿価単価(*1)） 
        //　@※（引数の売数量 × 簿価単価）の結果を円未満切捨てした後、引数の金額から減算する。 
        double l_dblCapitalGain = l_dblExpensesCalcAmount - Math.floor(l_dblOrderQuantity * l_dblBookValuePrice);

        //  計算した譲渡損益を返却する。 
        log.exiting(STR_METHOD_NAME);
        return l_dblCapitalGain;
    }
    
    /**
     * (get譲渡益有効状態)<BR>
     * <BR>
     * 譲渡益有効状態を判定する。 <BR>
     * <BR>
     * １．引数.トランザクションタイプ≠（現物売取引　@または　@現渡取引）の場合、<BR>
     * 　@　@0：無効　@を返却する。<BR>
     * <BR>
     * ２．保有資産を取得する。 <BR>
     * <BR>
     * 　@　@株式ポジションマネージャ.get保有資産  <BR>
     * --------------------------------------------- <BR>
     * 　@　@口座ＩＤ：　@引数の口座ＩＤ  <BR>
     * 　@　@補助口座ＩＤ：　@引数の補助口座ＩＤ  <BR>
     * 　@　@銘柄ＩＤ：　@引数の銘柄ＩＤ  <BR>
     * 　@　@税区分：　@引数の税区分  <BR>
     * --------------------------------------------- <BR>
     * <BR>
     * ３．譲渡益有効状態の判定を行う。 <BR>
     * 　@３－１）　@保有資産テーブル.簿価（簿価単価計算用）≠0の場合、 <BR>
     * 　@　@　@　@　@　@　@　@　@1：有効 を返却する。 <BR>
     * <BR>
     * 　@３－３）　@上記以外の場合、0：無効 を返却する。<BR>
     * <BR>
     * @@param l_lngAccountId - 口座ＩＤ<BR>
     * @@param l_lngSubAccountId - 補助口座ＩＤ<BR>
     * @@param l_lngProductId - 銘柄ＩＤ<BR>
     * @@param l_taxType - 税区分<BR>
     * @@param l_finTransactionType  - トランザクションタイプ<BR>
     * @@return String <BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D2FEA01AC
     */
    public String getCapitalGainStatus(
    	long l_lngAccountId,
    	long l_lngSubAccountId,
        long l_lngProductId,
        TaxTypeEnum l_taxType,
        FinTransactionType l_finTransactionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getCapitalGainStatus(long, long, long, TaxTypeEnum, FinTransactionType)";
        log.entering(STR_METHOD_NAME);
        
        //１．引数.トランザクションタイプ≠（現物売取引　@または　@現渡取引）の場合、
        //　@　@0：無効　@を返却する。
        if(!FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_finTransactionType) 
            && !FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_finTransactionType))
        {
        	log.exiting(STR_METHOD_NAME);
        	return WEB3CapitalGainStatusDef.INVALIDITY;
        }
        
        //２．保有資産を取得する。 
        //    株式ポジションマネージャ.get保有資産  
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        EqTypeAsset l_eqTypeAsset = l_positionManager.getAsset(
        	l_lngAccountId,
        	l_lngSubAccountId,
        	l_lngProductId,
        	l_taxType);
        
        //３．譲渡益有効状態の判定を行う。
        //  ３－1）　@保有資産テーブル.簿価（簿価単価計算用）≠0の場合、 
        //             1：有効 を返却する。 
        if(l_eqTypeAsset.getBookValue() != 0)
        {
        	log.exiting(STR_METHOD_NAME);
        	return WEB3CapitalGainStatusDef.VALIDITY;
        }

        //  ３－３）　@上記以外の場合、0：無効 を返却する。 
    	log.exiting(STR_METHOD_NAME);
    	return WEB3CapitalGainStatusDef.INVALIDITY;
    }

    /**
     * (set手数料計算結果)<BR>
     * <BR>
     * 引数のコピー元手数料の以下の計算結果プロパティを、引数のコピー先手数料にセットする。<BR>
     * <BR>
     * ＜対象プロパティ＞<BR>
     * is指値<BR>
     * 手数料金額、手数料No、手数料No枝番<BR>
     * 手数料コースコード、最低手数料<BR>
     * 諸経費計算用代金、<BR>
     * 原注文手数料No、<BR>
     * 原注文手数料No枝番<BR>
     * @@param l_copyCommission - (コピー先手数料)<BR>
     * コピー先の手数料オブジェクト。<BR>
     * @@param l_copyOrgCommission - (コピー元手数料)<BR>
     * コピー元の手数料オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    protected void setCommissionCalcResult(
        WEB3GentradeCommission l_copyCommission,
        WEB3GentradeCommission l_copyOrgCommission)throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setCommissionCalcResult(WEB3GentradeCommission, WEB3GentradeCommission)";
        log.entering(STR_METHOD_NAME);

        if (l_copyCommission == null || l_copyOrgCommission == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //引数のコピー元手数料の以下の計算結果プロパティを、引数のコピー先手数料にセットする。
        //＜対象プロパティ＞
        //is指値
        //手数料金額、手数料No、手数料No枝番
        //手数料コースコード、最低手数料
        //諸経費計算用代金、
        //原注文手数料No、
        //原注文手数料No枝番

        //is指値
        l_copyCommission.setIsLimitPrice(l_copyOrgCommission.isLimitPrice());

        //手数料金額
        l_copyCommission.setCommission(l_copyOrgCommission.getCommission());

        //手数料No
        l_copyCommission.setCommissionNo(l_copyOrgCommission.getCommissionNo());

        //手数料No枝番
        l_copyCommission.setCommissionRevNo(l_copyOrgCommission.getCommissionRevNo());

        //手数料コースコード
        l_copyCommission.setCommissionCourseDiv(l_copyOrgCommission.getCommissionCourseDiv());

        //最低手数料
        l_copyCommission.setMinCommission(l_copyOrgCommission.getMinCommission());

        //諸経費計算用代金
        l_copyCommission.setExpensesCalcAmount(l_copyOrgCommission.getExpensesCalcAmount());

        //原注文手数料No
        l_copyCommission.setOrgCommissionNo(l_copyOrgCommission.getOrgCommissionNo());

        //原注文手数料No枝番
        l_copyCommission.setOrgCommissionRevNo(l_copyOrgCommission.getOrgCommissionRevNo());

        log.exiting(STR_METHOD_NAME);
    }

}
@
