head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        :  外国株式計算サービス(WEB3FeqBizLogicProvider.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/18 芦露 (中訊) 新規作成
                   2005/07/27 魏馨(中訊) レビュー
Revesion History : 2007/11/07 何文敏(中訊) 実装No.005
Revesion History : 2008/01/14 柴双紅(中訊) モデルNo.369、モデルNo.372
Revesion History : 2008/10/02 大澤(SRA) 【外国株式】仕様変更管理台帳（計算式書）No.12
Revesion History : 2008/11/12 許丹(中訊) モデルNo.492、No.495、No.497、計算式書No.13
Revesion History : 2010/01/11 張騰宇(中訊) モデルNo.528
Revesion History : 2010/10/14 趙天月(中訊) モデルNo.556, No.557, No.558, 計算式書No.015
*/

package webbroker3.feq;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqAsset;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3ChangeRoundDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.feq.data.ForeignCostParams;
import webbroker3.feq.data.ForeignCostRow;
import webbroker3.feq.data.SpecialProductForeignCostParams;
import webbroker3.feq.define.WEB3FeqCostDivDef;
import webbroker3.feq.define.WEB3FeqRoundDivDef;
import webbroker3.feq.util.WEB3FeqOrderUtility;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.EquityCommAccountCondMstDao;
import webbroker3.gentrade.data.EquityCommAccountCondMstRow;
import webbroker3.gentrade.data.GenCurrencyDao;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式計算サービス) <BR>
 * 外国株式計算サービス <BR>
 * <BR>                    
 * @@ author 芦露 <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3FeqBizLogicProvider
    extends WEB3GentradeBizLogicProvider 
    implements GlobalBizLogicProvider, FeqBizLogicProvider

{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqBizLogicProvider.class); 

    /**
     * @@roseuid 42D0D01B037A
     */
    public WEB3FeqBizLogicProvider()
    {

    }
    
    /**
     * (calcExecutionAmount) <BR>
     * （未使用） <BR>
     * 0.0を返却する。
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@param arg4
     * @@return double
     * @@roseuid 42806C3901FC
     */
    public double calcExecutionAmount(
        ProductTypeEnum arg0,
        long arg1,
        double arg2,
        double arg3,
        double arg4)
    {
        return 0.0;
    }

    /**
     * (calcSalesTax) <BR>
     * （未使用） <BR>
     * 0.0を返却する。 <BR>
     * @@param arg0
     * @@return double
     * @@roseuid 42806C40020C
     */
    public double calcSalesTax(double arg0)
    {
        return 0.0;
    }

    /**
     * (checkTradingPower) <BR>
     * （未使用） <BR>
     * nullを返却する。 <BR>
     * @@param arg0
     * @@param arg1
     * @@return OrderValidationResult
     * @@roseuid 42806C40021C
     */
    public OrderValidationResult checkTradingPower(
        SubAccount arg0,
        OrderSpec arg1)
    {
        return null;
    }

    /**
     * (calc売買代金) <BR>
     * （calcExecutionAmount） <BR>
     * <BR>
     * 株数×単価を返却する。
     * @@param l_dblStockQuantity - (株数)
     * @@param l_dblPrice - (単価) <BR>
     * 単価（外貨）
     * @@return double
     * @@roseuid 4281B30801B4
     */
    public double calcExecutionAmount(
        double l_dblStockQuantity, 
        double l_dblPrice)
    {
        String STR_METHOD_NAME = "calcExecutionAmount(double, double)";
        log.entering(STR_METHOD_NAME);
        
        if ((l_dblStockQuantity < 0) || (l_dblPrice < 0))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "株数 = " + l_dblStockQuantity);
        }
        
        //株数×単価を返却する。
        double l_dblExecutionAmount =
            new BigDecimal(String.valueOf(l_dblStockQuantity))
            .multiply(new BigDecimal(String.valueOf(l_dblPrice)))
            .doubleValue();

        log.debug("単価：[" + l_dblPrice + "]");
        log.debug("株数：[" + l_dblStockQuantity + "]");
        log.debug("売買代金 ＝ 株数 × 単価   ： ["+ l_dblExecutionAmount+ "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_dblExecutionAmount;
    }

    /**
     * (calc現地諸経費)
     * 海外諸経費（現地手数料，現地取引税，その他コスト１，その他コスト２）と <BR>
     * 現地清算代金を計算する。<BR>
     * <BR>
     * １）　@発注日取得<BR>
     * 　@基準日が未指定（null）の場合、<BR>
     *   取引時間管理.get発注日()にて発注日を取得し、基準日とする。<BR>
     * <BR>
     * ２）　@海外諸経費マスタ取得<BR>
     * 　@get海外諸経費マスタ()にて、海外諸経費マスタ行（Params）を取得する。<BR>
     * <BR>
     * 　@[get海外諸経費マスタ()に指定する引数] <BR>
     * 　@銘柄ID：　@銘柄ID<BR>
     * 　@証券会社コード：　@証券会社コード <BR>
     * 　@市場コード：　@市場コード <BR>
     * 　@売買代金：　@売買代金（外貨） <BR>
     * 　@基準日：　@基準日 <BR>
     * 　@諸経費区分：　@null <BR>
     * 　@売買区分：is買付 == true の場合、”買い”をセット、以外”売り”をセットする。<BR>
     *  <BR>
     * ３）　@海外諸経費計算 <BR>
     * 　@現地諸経費オブジェクトを生成し、<BR>
     * 　@各計算結果を対応するプロパティにセットする。 <BR>
     *  <BR>
     * 　@○　@現地手数料（諸経費区分 == ”現地手数料”） <BR>
     * 　@　@[計算式] <BR>
     * 　@　@現地手数料 = 売買代金（外貨）×徴収率(*1)＋付加金額(*2) <BR>
     * 　@　@（計算結果は、小数部桁数(*3)未満を、計算結果丸め方式(*4) <BR>
     * 　@　@にて丸め処理を行う） <BR>
     *  <BR>
     * 　@○　@現地取引税（諸経費区分 == ”現地取引税”） <BR>
     * 　@　@[計算式] <BR>
     * 　@　@現地取引税 = 売買代金（外貨）×徴収率(*1)＋付加金額(*2) <BR>
     * 　@　@（計算結果は、小数部桁数(*3)未満を、計算結果丸め方式(*4) <BR>
     * 　@　@にて丸め処理を行う） <BR>
     *  <BR>
     * 　@○　@その他コスト１（諸経費区分 == ”その他コスト１”） <BR>
     * 　@　@[計算式] <BR>
     * 　@　@その他コスト１ = 売買代金（外貨）×徴収率(*1)＋付加金額(*2) <BR>
     * 　@　@（計算結果は、小数部桁数(*3)未満を、計算結果丸め方式(*4) <BR>
     * 　@　@にて丸め処理を行う） <BR>
     *  <BR>
     * 　@○　@その他コスト２（諸経費区分 == ”その他コスト２”） <BR>
     * 　@　@[計算式] <BR>
     * 　@　@その他コスト２ = 売買代金（外貨）×徴収率(*1)＋付加金額(*2) <BR>
     * 　@　@（計算結果は、小数部桁数(*3)未満を、計算結果丸め方式(*4) <BR>
     * 　@　@にて丸め処理を行う） <BR>
     *  <BR>
     * 　@(*1)　@徴収率 <BR>
     * 　@(*2)　@付加金額 <BR>
     * 　@(*3)　@小数部桁数 <BR>
     * 　@(*4)　@計算結果丸め方式 <BR>
     * 　@２）で取得した海外諸経費マスタ行のうち、<BR>
     * 　@　@諸経費区分に該当する行より取得する。 <BR>
     * 　@　@対応する諸経費区分の行がない場合は、計算を0とする。 <BR>
     *  <BR>
     * ４）　@現地清算代金計算 <BR>
     * 　@以下の計算結果を現地諸経費.現地清算代金にセットする。 <BR>
     *  <BR>
     * 　@○　@買の場合（is買付 == true） <BR>
     * 　@　@現地清算代金 = 売買代金（外貨）＋現地手数料＋現地取引税＋ <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@その他コスト１＋その他コスト２ <BR>
     *  <BR>
     * 　@○　@売の場合（is買付 == false） <BR>
     * 　@　@現地清算代金 = 売買代金（外貨）－現地手数料－現地取引税－ <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@その他コスト１－その他コスト２ <BR>
     *  <BR>
     * ５）　@計算結果返却 <BR>
     * 　@現地諸経費オブジェクトを返却する。 <BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_strOffshoreProductCode - (現地銘柄コード)
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strMarketCode - (市場コード)
     * @@param l_dblTradePriceFc - (売買代金（外貨）)
     * @@param l_datBaseDate - (基準日) <BR>
     * nullの場合、発注日を使用する。
     * @@param l_blnIsBuy - (is買付) <BR>
     * 買付かの判定 <BR>
     *  <BR>
     * true：買 <BR>
     * false：売 <BR>
     * @@return webbroker3.feq.WEB3FeqBalanceCost
     * @@roseuid 4282ECBC00FD
     */
    public WEB3FeqForeignCost calcForeignCost(
        Long l_lngProductId,
        String l_strOffshoreProductCode,
        String l_strInstitutionCode,
        String l_strMarketCode,
        double l_dblTradePriceFc,
        Date l_datBaseDate,
        boolean l_blnIsBuy) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcForeignCost(Long, String, String, String, double, Date, boolean)";
        log.entering(STR_METHOD_NAME);

        //１）発注日取得 
        //基準日が未指定（null）の場合、 
        //取引時間管理.get発注日()にて発注日を取得し、基準日とする。 
        Date l_datOrderBizDate = l_datBaseDate;
        if (l_datBaseDate == null)
        {
            l_datOrderBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }

        //２）海外諸経費マスタ取得
        //get海外諸経費マスタ()にて、海外諸経費マスタ行（Params）を取得する。
        //[get海外諸経費マスタ()に指定する引数]
        //銘柄ID：　@銘柄ID
        //現地銘柄コード：　@現地銘柄コード
        //証券会社コード：　@証券会社コード
        //市場コード：　@市場コード 
        //売買代金：　@売買代金（外貨）
        //基準日：　@基準日 
        //諸経費区分：　@null 
        //売買区分：is買付 == true の場合、”買い”をセット、以外”売り”をセットする。
        String l_strSideDiv;
        if (l_blnIsBuy)
        {
            l_strSideDiv = WEB3BuySellTypeDef.BUY;
        }
        else
        {
            l_strSideDiv = WEB3BuySellTypeDef.SELL;
        }

        ForeignCostParams[] l_foreignCostParams = 
            this.getForeignCost(
                l_lngProductId,
                l_strOffshoreProductCode,
                l_strInstitutionCode,
                l_strMarketCode,
                l_dblTradePriceFc,
                l_datOrderBizDate,
                null,
                l_strSideDiv);

        //３）海外諸経費計算
        //現地諸経費オブジェクトを生成し
        WEB3FeqForeignCost l_feqForeignCost = new WEB3FeqForeignCost();

        //各計算結果を対応するプロパティにセットする。
        //現地手数料（諸経費区分 == ”現地手数料”）
        //[計算式] 
        //現地手数料 = 売買代金（外貨）×徴収率(*1)＋付加金額(*2) 
        //（計算結果は、小数部桁数(*3)未満を、計算結果丸め方式(*4)にて丸め処理を行う）
        //現地取引税（諸経費区分 == ”現地取引税”） 
        //[計算式] 
        //現地取引税 = 売買代金（外貨）×徴収率(*1)＋付加金額(*2) 
        //（計算結果は、小数部桁数(*3)未満を、計算結果丸め方式(*4)にて丸め処理を行う） 
        //その他コスト１（諸経費区分 == ”その他コスト１”） 
        //[計算式] 
        //その他コスト１ = 売買代金（外貨）×徴収率(*1)＋付加金額(*2)
        //（計算結果は、小数部桁数(*3)未満を、計算結果丸め方式(*4)にて丸め処理を行う）
        //その他コスト２（諸経費区分 == ”その他コスト２”）
        //[計算式] 
        //その他コスト２ = 売買代金（外貨）×徴収率(*1)＋付加金額(*2)
        //（計算結果は、小数部桁数(*3)未満を、計算結果丸め方式(*4)にて丸め処理を行う）
        //(*1)　@徴収率 
        //(*2)　@付加金額 
        //(*3)　@小数部桁数 
        //(*4)　@計算結果丸め方式 
        //２）で取得した海外諸経費マスタ行のうち、
        //諸経費区分に該当する行より取得する。
        //対応する諸経費区分の行がない場合は、計算を0とする。
        BigDecimal l_bdTradePriceFc = new BigDecimal(String.valueOf(l_dblTradePriceFc));
        BigDecimal l_bdCommisionRate;
        BigDecimal l_bdAddAmount;
        BigDecimal l_bdPrice;
        double l_dblResult = 0.0D;
        for (int i = 0; i < l_foreignCostParams.length; i++)
        {
            l_bdCommisionRate = new BigDecimal(String.valueOf(l_foreignCostParams[i].getCommisionRate()));
            l_bdAddAmount = new BigDecimal(String.valueOf(l_foreignCostParams[i].getAddAmount()));
            l_bdPrice = l_bdTradePriceFc.multiply(l_bdCommisionRate)
                        .divide(new BigDecimal("100"), 10, BigDecimal.ROUND_HALF_EVEN)
                        .add(l_bdAddAmount);
            l_dblResult = l_bdPrice.doubleValue();
            int l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdPrice.toString());
            int l_intScale = l_foreignCostParams[i].getScale();
            if (l_intDigits > l_intScale)
            {
                if (WEB3FeqRoundDivDef.ROUND.equals(l_foreignCostParams[i].getRoundDiv()))
                {
                    l_dblResult = WEB3FeqOrderUtility.getCutOutValue(
                        l_bdPrice.doubleValue(),
                        l_intScale,
                        WEB3FeqOrderUtility.ROUND);
                }
                else if (WEB3FeqRoundDivDef.CUTOFF.equals(l_foreignCostParams[i].getRoundDiv()))
                {
                    l_dblResult = WEB3FeqOrderUtility.getCutOutValue(
                        l_bdPrice.doubleValue(),
                        l_intScale,
                        WEB3FeqOrderUtility.CUTOFF);
                }
                else if (WEB3FeqRoundDivDef.CUT_UP.equals(l_foreignCostParams[i].getRoundDiv()))
                {
                    l_dblResult = WEB3FeqOrderUtility.getCutOutValue(
                        l_bdPrice.doubleValue(),
                        l_intScale,
                        WEB3FeqOrderUtility.CEIL);
                }
            }
            
            if (WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE.equals(l_foreignCostParams[i].getCostDiv()))
            {
                log.debug("現地手数料（外貨：[" + l_dblResult + "]");
                l_feqForeignCost.setForeignCommissionFee(l_dblResult);

            }
            else if (WEB3FeqCostDivDef.FOREIGN_TAX.equals(l_foreignCostParams[i].getCostDiv()))
            {
                log.debug("現地取引税（外貨：[" + l_dblResult + "]");
                l_feqForeignCost.setForeignTax(l_dblResult);

            }
            else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(l_foreignCostParams[i].getCostDiv()))
            {
                log.debug("その他コスト１（外貨：[" + l_dblResult + "]");
                l_feqForeignCost.setForeignFeeExt1(l_dblResult);

            }
            else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(l_foreignCostParams[i].getCostDiv()))
            {
                log.debug("その他コスト２（外貨：[" + l_dblResult + "]");
                l_feqForeignCost.setForeignFeeExt2(l_dblResult);

            }
        }

        //４）現地清算代金計算以下の計算結果を現地諸経費.現地清算代金にセットする。
        //買の場合（is買付 == true）
        //現地清算代金 = 売買代金（外貨）＋現地手数料＋現地取引税＋その他コスト１＋その他コスト２ 
        if (l_blnIsBuy)
        {
            double l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    l_dblTradePriceFc, 
                    l_feqForeignCost.getForeignCommissionFee());
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignTax());                     
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignFeeExt1());                     
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignFeeExt2());                     
            l_feqForeignCost.setBalanceAmountFc(l_dblTemp);
        }
        //売の場合（is買付 == false） 
        //現地清算代金 = 売買代金（外貨）－現地手数料－現地取引税－その他コスト１－その他コスト２ 
        else
        {
            double l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    l_dblTradePriceFc, 
                    l_feqForeignCost.getForeignCommissionFee());
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignTax());                     
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignFeeExt1());                     
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignFeeExt2());                     
            l_feqForeignCost.setBalanceAmountFc(l_dblTemp);
        }

        log.exiting(STR_METHOD_NAME);
        //５）計算結果返却現地諸経費オブジェクトを返却する。
        return l_feqForeignCost;
    }

    /**
     * (calc拘束売買代金) <BR>
     * 拘束売買代金を計算する。 <BR>
     *  <BR>
     * １）　@会社部店商品テーブル検索 <BR>
     * 　@以下の条件で会社部店商品テーブルを検索し、割増拘束率を取得する。 <BR>
     *  <BR>
     * 　@[条件] <BR>
     * 　@部店ＩＤ = 部店ＩＤ And <BR>
     * 　@手数料商品コード = this.get手数料商品コード() <BR>
     *  <BR>
     * ２）　@拘束売買代金を計算する。  <BR>
     *  <BR>
     * 　@拘束売買代金 = 現地清算代金（円貨）×割増拘束率  <BR>
     *  <BR>
     * @@param l_lngBranchId - 部店ＩＤ
     * @@param l_dblBalanceAmount - 現地清算代金（円貨）
     * @@return double
     * @@roseuid 42832E9700DD
     */
    public double calcRestraintAmount(long l_lngBranchId, double l_dblBalanceAmount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcRestraintAmount(long, double)";
        log.entering(STR_METHOD_NAME);

        //１）会社部店商品テーブル検索
        //以下の条件で会社部店商品テーブルを検索し、割増拘束率を取得する。 
        //[条件]
        //部店ＩＤ = 部店ＩＤ And 手数料商品コード = this.get手数料商品コード() 
        try
        {
            InstBranchProductRow l_instBranchProductRow =
                (InstBranchProductRow)InstBranchProductDao.findRowByBranchIdCommissionProductCode(
                    l_lngBranchId,
                    this.getCommissionProductCode());

            //拘束売買代金 = 現地清算代金（円貨）×割増拘束率
            double l_dblPremiumRestraintRate = 0.0D;
            if (l_instBranchProductRow != null)
            {
                l_dblPremiumRestraintRate = 
                    l_instBranchProductRow.getPremiumRestraintRate();
            }
            double l_dblRestraintAmount = 
                l_dblBalanceAmount * l_dblPremiumRestraintRate;
            log.debug("現地清算代金（円貨：[" + l_dblBalanceAmount + "]");
            log.debug("割増拘束率：[" + l_dblPremiumRestraintRate + "]");
            log.debug("拘束売買代金 = 現地清算代金（円貨）×割増拘束率   ： [" 
                + l_dblRestraintAmount + "]");

            log.exiting(STR_METHOD_NAME);
            return l_dblRestraintAmount;
        }
        catch (DataFindException l_ex)
        {
            log.error("データ不整合エラー: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() +  "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataNetworkException l_ex)        
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (calc委託手数料) <BR>
     * （calcCommissionのオーバーロード） <BR>
     *  <BR>
     * １）　@発注日取得 <BR>
     * 　@基準日が未指定（null）の場合、 <BR>
     * 　@取引時間管理.get発注日()にて発注日を取得し、基準日とする。 <BR>
     *  <BR>
     * ２）　@手数料オブジェクトを生成し、プロパティに以下の通りセットを行う。  <BR>
     *  <BR>
     * 　@証券会社コード： 補助口座.getInstitution().getInstitutionCode() <BR>
     * 　@部店ID： 補助口座.get取引店().BranchId() <BR>
     * 　@手数料商品コード： this.get手数料商品コード() <BR>
     * 　@取引コード（SONAR）：　@this.get取引コード（SONAR）() <BR>
     * 　@発注日：　@発注日 <BR>
     * 　@弁済区分：　@弁済区分.00：その他  <BR>
     * 　@注文チャネル： 注文チャネル <BR>
     * 　@is指値： is指値注文 <BR>
     * 　@市場コード（SONAR）：　@市場.市場コード（SONAR） <BR>
     * 　@諸経費計算用代金：　@現地清算代金（円貨） <BR>
     *  <BR>
     * ３）　@手数料計算 <BR>
     * 　@this.calc委託手数料(手数料，補助口座)に委譲（deligate）する。 <BR>
     *  <BR>
     * ４）　@手数料オブジェクト返却 <BR>
     * 　@手数料オブジェクトを返却する。 <BR>
     * @@param l_subAccount - (補助口座) 
     * 補助口座オブジェクト
     * @@param l_market - (市場) 
     * 市場オブジェクト
     * @@param l_datBaseDate - (基準日) 
     * nullの場合、発注日を使用する。
     * @@param l_blnIsBuy - is買付
     * 買付かの判定<BR>
     * <BR>
     * true：買<BR>
     * false：売 <BR>
     * @@param l_blnIsLimitOrder - （isLimitOrder）
     * 指値注文かを判定する。 <BR>
     *  <BR>
     * true：指値注文 <BR>
     * false：成行注文 <BR>
     * @@param l_dblBalanceAmount - (現地清算代金（円貨）)
     * @@param l_strOrderChannel - 注文チャネル
     * @@return WEB3GentradeCommission
     * @@roseuid 42830455015A
     */
    public WEB3GentradeCommission calcCommission(
        SubAccount l_subAccount,
        Market l_market,
        Date l_datBaseDate,
        boolean l_blnIsBuy,
        boolean l_blnIsLimitOrder,
        double l_dblBalanceAmount,
        String l_strOrderChannel) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcCommission(SubAccount, Market, Date, boolean, double, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_market == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）　@発注日取得 
        //    　@基準日が未指定（null）の場合、 
        //    　@取引時間管理.get発注日()にて発注日を取得し、基準日とする。 
        Date l_datOrderBizDate = l_datBaseDate;
        if (l_datBaseDate == null)
        {
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }

        //２）　@手数料オブジェクトを生成し、プロパティに以下の通りセットを行う。 

        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();

        // 　@証券会社コード： 補助口座.getInstitution().getInstitutionCode() 
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        // 　@部店ID： 補助口座.get取引店().BranchId() 
        l_gentradeCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());

        // 　@手数料商品コード： this.get手数料商品コード()
        l_gentradeCommission.setCommissionProductCode(this.getCommissionProductCode());

        // 　@取引コード（SONAR）：　@this.get取引コード（SONAR）() 
        l_gentradeCommission.setSonarTradedCode(this.getSonarTradedCode(l_blnIsBuy));

        // 　@発注日：　@発注日 
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));

        // 　@弁済区分：　@弁済区分.00：その他  
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
        
        // 　@注文チャネル： 注文チャネル 
        l_gentradeCommission.setOrderChannel(l_strOrderChannel);

        // 　@is指値： is指値注文 
        l_gentradeCommission.setIsLimitPrice(l_blnIsLimitOrder);

        // 　@市場コード（SONAR）：　@市場.市場コード（SONAR） 
        MarketRow l_marketRow = (MarketRow) (l_market.getDataSourceObject());       
        l_gentradeCommission.setSonarMarketCode(l_marketRow.getSonarMarketCode());

        // 　@諸経費計算用代金：　@現地清算代金（円貨） 
        l_gentradeCommission.setExpensesCalcAmount(l_dblBalanceAmount);
        
        //３）　@手数料計算 
        //this.calc委託手数料(手数料，補助口座)に委譲（deligate）する。 
        this.calcCommission(l_gentradeCommission,l_subAccount);
        
        //４）　@手数料オブジェクト返却
        log.exiting(STR_METHOD_NAME);
        return l_gentradeCommission;
    }

    /**
     * (calc消費税) <BR>
     * （calcSalesTaxのオーバーロード） <BR>
     * 指定金額に対する消費税を返却する。 <BR>
     *  <BR>
     * super.calc消費税()に委譲（deligate）する。 <BR>
     *  <BR>
     * @@see WEB3GentradeBizLogicProvider.calcSalesTax( <BR>
     * 　@double, Timestamp, SubAccount) <BR>
     * @@param l_dblAmount - (金額) <BR>
     * 消費税を算出する対象の金額
     * @@param l_datBaseDate - (基準日) <BR>
     * 消費税を算出する基準日 <BR>
     * 注文の場合は発注日にあたる。
     * @@param l_subAccount - (補助口座)
     * @@return double
     * @@roseuid 428879CA0109
     */
    public double calcSalesTax(
        double l_dblAmount, 
        Date l_datBaseDate, 
        SubAccount l_subAccount) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcSalesTax(double, Date, SubAccount)";
        log.entering(STR_METHOD_NAME);

        Timestamp l_tsBaseDate = new Timestamp(l_datBaseDate.getTime());

        double l_dblSalesTax = 
            super.calcSalesTax(l_dblAmount, l_tsBaseDate, l_subAccount);

        log.exiting(STR_METHOD_NAME);
        return l_dblSalesTax;
    }

    /**
     * (calc譲渡益税) <BR>
     * 譲渡益税を計算する。 <BR>
     *  <BR>
     * 譲渡益税に０を設定してリターン
     * （外国株式の納税は申告のみ）
     ****************以下コメントアウト051201******************
     * 株式計算サービス.calc譲渡益税()に委譲（deligate）する。 <BR>
     *  <BR>
     * 　@[株式計算サービス.calc譲渡益税()に指定する引数] <BR>
     * 　@補助口座：　@補助口座 <BR>
     * 　@税区分：　@税区分 <BR>
     * 　@金額：　@売買代金（円貨） <BR>
     * 　@基準日：　@受渡日 <BR>
     * 　@顧客税区分：　@(**1) <BR>
     *  <BR>
     * (**1) 顧客税区分の取得 <BR>
     * 　@顧客.get受渡日税区分() <BR>
     * 　@（顧客は、アカウントマネージャ.get顧客(補助口座.getAccountId()) <BR>
     * 　@にて取得する） <BR>
     *  <BR>
     * @@see WEB3EquityBizLogicProvider.calcCapitalGainTax(  <BR>
     * 　@SubAccount,TaxTypeEnum,double,Timestamp,TaxTypeEnum) <BR>
     ****************以上コメントアウト051201******************
     * @@param l_subAccount - (補助口座) 
     * 補助口座。
     * @@param l_taxType - (税区分) 
     * 税区分。
     * @@param l_dblTradeAmount - 売買代金（円貨）
     * @@param l_datDeliveryDate - (受渡日) 
     * 譲渡益税を算出する基準日（=受渡日）
     * @@return double
     * @@roseuid 4281B6030251
     */
    public double calcCapitalGainTax(
        WEB3GentradeSubAccount l_subAccount,
        TaxTypeEnum l_taxType,
        double l_dblTradeAmount,
        Date l_datDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "calcCapitalGainTax(WEB3GentradeSubAccount, TaxTypeEnum, double, Date)";
        log.entering(STR_METHOD_NAME);

        return 0;
     /*
        if ((l_subAccount == null) || (l_datDeliveryDate == null))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider = 
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        try
        {
            //顧客は、アカウントマネージャ.get顧客(補助口座.getAccountId()
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_subAccount.getAccountId());
            Timestamp l_timBaseDate = 
                new Timestamp(l_datDeliveryDate.getTime());
            double l_dblCainTax = 
                l_bizLogicProvider.calcCapitalGainTax(
                    l_subAccount, 
                    l_taxType, 
                    l_dblTradeAmount, 
                    l_timBaseDate, 
                    l_mainAccount.getDeliveryDateTaxType(l_datDeliveryDate));

            log.exiting(STR_METHOD_NAME);
            return l_dblCainTax;
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName() +
                "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
     */   
    }

    /**
     * (calc譲渡損益) <BR>
     * 譲渡益税を計算する。 <BR>
     *  <BR>
     * 株式計算サービス.calc譲渡損益()に委譲（deligate）する。 <BR>
     *  <BR>
     * 　@[calc譲渡損益()に指定する引数] <BR>
     * 　@金額：　@売買代金（円貨） <BR>
     * 　@売数量：　@売株数 <BR>
     * 　@銘柄ＩＤ：　@銘柄ＩＤ <BR>
     * 　@補助口座：　@補助口座 <BR>
     * 　@税区分：　@税区分 <BR>
     *  <BR>
     * @@see WEB3EquityBizLogicProvider.calcCapitaGain( <BR>
     * double, double, long, SubAccount, TaxTypeEnum) <BR>
     * @@param l_dblTradeAmount - 売買代金（円貨）
     * @@param l_dblSellStockQuantity - (売株数)
     * @@param l_lngProductId - 銘柄ＩＤ
     * @@param l_subAccount - (補助口座)
     * @@param l_taxType - (税区分)
     * @@return double
     * @@roseuid 42887ECD01A5
     */
    public double calcCapitalProfitLoss(
        double l_dblTradeAmount,
        double l_dblSellStockQuantity,
        long l_lngProductId,
        WEB3GentradeSubAccount l_subAccount,
        TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcCapitalProfitLoss(double, double, long, WEB3GentradeSubAccount, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if ((l_dblSellStockQuantity < 0) || (l_subAccount == null))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider = 
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();

        //株式計算サービス.calc譲渡損益()に委譲（deligate）する。
        double l_dblCainTax = 
            l_bizLogicProvider.calcCapitaGain(
                l_dblTradeAmount, 
                l_dblSellStockQuantity, 
                l_lngProductId, 
                l_subAccount, 
                l_taxType);

        log.exiting(STR_METHOD_NAME);
        return l_dblCainTax;
    }

    /**
     * (calc外貨換算) <BR>
     * （calcForignCCYAmount） <BR>
     * 指定金額を指定レートで外貨換算を行う。 <BR>
     *  <BR>
     * 金額（円貨）÷レートの計算結果を返却する。 <BR>
     * （計算結果は、小数部桁数未満を外貨換算丸め方式で丸め処理を行う） <BR>
     * @@param l_dblAmount - (金額（円貨）) 
     * 金額（円貨）
     * @@param l_dblRate - レート
     * @@param l_intScale - (小数部桁数) 
     * 小数部桁数
     * @@param l_strChangeFccyRoundDiv - (外貨換算丸め方式) 
     * 外貨換算丸め方式 <BR>
     *  <BR>
     * 0：四捨五入　@1：切捨　@2：切上 <BR>
     * @@return double
     * @@roseuid 4281B35F028F
     */
    public double calcForeignCCYAmount(
        double l_dblAmount,
        double l_dblRate,
        int l_intScale,
        String l_strChangeFccyRoundDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcForeignCCYAmount(double, double, int, String)";
        log.entering(STR_METHOD_NAME);

        //金額（円貨）÷レート
        BigDecimal l_bdAmount = new BigDecimal(String.valueOf(l_dblAmount));
        BigDecimal l_bdForignCCYAmount =
            l_bdAmount.divide(new BigDecimal(String.valueOf(l_dblRate)), 10, BigDecimal.ROUND_HALF_EVEN);
        double l_dblForignCCYAmount = l_bdForignCCYAmount.doubleValue();
        if (l_dblAmount < 0.0D)
        {
            l_dblForignCCYAmount *= -1.0D;
        }

        if ((Double.isInfinite(l_dblForignCCYAmount)) || 
            (Double.isNaN(l_dblForignCCYAmount)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        double l_dblReturnAmount = l_dblForignCCYAmount;
        int l_intDigits = 
            WEB3StringTypeUtility.getFractionDigits(
                new Double(l_dblForignCCYAmount).toString());

        if (l_intDigits > l_intScale)
        {
            //1：切捨
            if (WEB3FeqRoundDivDef.CUTOFF.equals(l_strChangeFccyRoundDiv))
            {
                l_dblReturnAmount = 
                    WEB3FeqOrderUtility.getCutOutValue(
                        l_dblForignCCYAmount,
                        l_intScale,
                        WEB3FeqOrderUtility.CUTOFF);    
            }
            //2：切上
            else if (WEB3FeqRoundDivDef.CUT_UP.equals(l_strChangeFccyRoundDiv))
            {
                l_dblReturnAmount = 
                    WEB3FeqOrderUtility.getCutOutValue(
                        l_dblForignCCYAmount,
                        l_intScale,
                        WEB3FeqOrderUtility.CEIL);    
            }
            //0：四捨五入
            else
            {
                l_dblReturnAmount = 
                    WEB3FeqOrderUtility.getCutOutValue(
                        l_dblForignCCYAmount,
                        l_intScale,
                        WEB3FeqOrderUtility.ROUND);    
            }
        }
        if (l_dblAmount < 0.0D)
        {
            l_dblReturnAmount *= -1.0D;
        }

        log.debug("金額（円貨）：[" + l_dblAmount + "]");
        log.debug("レート：[" + l_dblRate + "]");
        log.debug("calc外貨換算 ＝ 金額（円貨）÷レート ： [" + 
            l_dblReturnAmount + "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_dblReturnAmount; 
    }

    /**
     * (calc円貨換算) <BR>
     * （calcJPYAmount） <BR>
     * 指定金額を指定レートで邦貨換算を行う。 <BR>
     *  <BR>
     * 金額（外貨）×レートの計算結果を返却する。 <BR>
     * （計算結果は、小数部を円貨換算丸め方式で丸め処理を行う） <BR>
     * @@param l_dblForeignAmount - 金額（外貨）
     * @@param l_dblRate - レート
     * @@param l_strChangeFccyRoundDiv - (円貨換算丸め方式) <BR>
     * 円貨換算丸め方式 <BR>
     *  <BR>
     * 0：四捨五入　@1：切捨　@2：切上 <BR>
     * @@return double
     * @@roseuid 4281B3B20241
     */
    public double calcJPYAmount(
        double l_dblForeignAmount, 
        double l_dblRate, 
        String l_strChangeFccyRoundDiv)
    {
        String STR_METHOD_NAME = "calcJPYAmount(double, double, String)";
        log.entering(STR_METHOD_NAME);
        
        //金額（外貨）×レート
        BigDecimal l_bdJPYAmount =
            new BigDecimal(String.valueOf(l_dblForeignAmount)).multiply(new BigDecimal(String.valueOf(l_dblRate)));
        double l_dblJPYAmount = l_bdJPYAmount.doubleValue();
        if (l_dblForeignAmount < 0.0D)
        {
            l_dblJPYAmount *= -1.0D;
        }
        double l_dblReturnJPYAmount = l_dblJPYAmount;
        
        //1：切捨
        if (WEB3FeqRoundDivDef.CUTOFF.equals(l_strChangeFccyRoundDiv))
        {
            l_dblReturnJPYAmount = 
                WEB3FeqOrderUtility.getCutOutValue(
                    l_dblJPYAmount,
                    0,
                    WEB3FeqOrderUtility.CUTOFF);    
        }
        //2：切上
        else if (WEB3FeqRoundDivDef.CUT_UP.equals(l_strChangeFccyRoundDiv))
        {
            l_dblReturnJPYAmount = 
                WEB3FeqOrderUtility.getCutOutValue(
                    l_dblJPYAmount,
                    0,
                    WEB3FeqOrderUtility.CEIL);    
        }
        //0：四捨五入
        else
        {
            l_dblReturnJPYAmount = 
                WEB3FeqOrderUtility.getCutOutValue(
                    l_dblJPYAmount,
                    0,
                    WEB3FeqOrderUtility.ROUND);    
        }
        if (l_dblForeignAmount < 0.0D)
        {
            l_dblReturnJPYAmount *= -1.0D;
        }
        
        log.debug("金額（外貨）：[" + l_dblForeignAmount + "]");
        log.debug("レート：[" + l_dblRate + "]");
        log.debug("calc外貨換算 ＝ 金額（外貨）×レート ： [" + 
            l_dblReturnJPYAmount + "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_dblReturnJPYAmount; 
    } 

    /**
     * (get手数料商品コード) <BR>
     * 手数料商品コード.40：外国株式　@を返却する。
     * @@return String
     * @@roseuid 4283169E02A3
     */
    public String getCommissionProductCode()
    {
        String l_strCommissionProductCode = 
            WEB3CommisionProductCodeDef.FOREIGN_EQITY;
        return l_strCommissionProductCode;        
    }

    /**
     * (get取引コード（SONAR）) <BR>
     * <BR>
     * 引数.is買付 == true の場合、取引コード（SONAR）.11：普通株式を返却する。<BR> 
     * 引数.is買付 == false の場合、取引コード（SONAR）.91：売付を返却する。<BR>
     * <BR>
     * @@param l_blnIsBuy is買付
     * 買付注文かどうかの判定フラグ<BR>
     * <BR>
     * true： 買付注文<BR>
     * false： 売付注文<BR> 
     * @@return String 取引コード（SONAR）
     * @@roseuid 428316FB0080
     */
    public String getSonarTradedCode(boolean l_blnIsBuy)
    {
        if (l_blnIsBuy)
        {
            return WEB3TransactionTypeSONARDef.MARKET_TRADING;
        }
        else
        {
            return WEB3TransactionTypeSONARDef.SELL;
        }
    }

    /**
     * (get海外諸経費マスタ) <BR>
     * 海外諸経費マスタを取得する。 <BR>
     *  <BR>
     * １）　@発注日取得 <BR>
     * 　@基準日が未指定（null）の場合、 <BR>
     * 　@取引時間管理.get発注日()にて発注日を取得し、基準日とする。 <BR>
     *  <BR>
     * ２）　@海外諸経費マスタ検索 <BR>
     * 　@以下の条件にて、海外諸経費マスタテーブルを検索し、 <BR>
     * 　@取得した海外諸経費マスタ行オブジェクトを配列。 <BR>
     *  <BR>
     * 　@[（諸経費区分 == null）の場合の条件] <BR>
     * 　@海外諸経費マスタ.証券会社コード = 証券会社コード And　@ <BR>
     * 　@海外諸経費マスタ.市場コード = 市場コード And　@ <BR>
     * 　@海外諸経費マスタ.摘要開始年月日 <= 基準日 And <BR>
     * 　@海外諸経費マスタ.摘要終了年月日 >= 基準日 And <BR>
     * 　@海外諸経費マスタ.売買代金（FROM） < 売買代金 And <BR>
     * 　@海外諸経費マスタ.売買代金（TO） >= 売買代金 <BR>
     * 　@海外諸経費マスタ.売買区分 = 売買区分<BR>
     *  <BR>
     * 　@[（諸経費区分 != null）の場合の条件] <BR>
     * 　@海外諸経費マスタ.証券会社コード = 証券会社コード And　@<BR>
     * 　@海外諸経費マスタ.市場コード = 市場コード And　@<BR>
     * 　@海外諸経費マスタ.摘要開始年月日 <= 基準日 And <BR>
     * 　@海外諸経費マスタ.摘要終了年月日 >= 基準日 And <BR>
     * 　@海外諸経費マスタ.売買代金（FROM） < 売買代金 And <BR>
     * 　@海外諸経費マスタ.売買代金（TO） >= 売買代金 And <BR>
     * 　@海外諸経費マスタ.諸経費区分 = 諸経費区分 <BR>
     * 　@海外諸経費マスタ.売買区分 = 売買区分<BR>
     *  <BR>
     *  同一諸経費区分のレコードが複数行取得された場合は例外をスローする。<BR>
     *  ※「同一区分の複数行のレコードが選択されました」<BR>
     *  class:WEB3BusinessLayerException<BR>
     *  tag:BUSINESS_ERROR_03209<BR>
     *  <BR>
     * ３）　@特殊銘柄用海外諸経費マスタ検索<BR>
     * 　@以下の条件にて、特殊銘柄用海外諸経費マスタテーブルを検索し、<BR>
     * 取得した特殊銘柄用海外諸経費マスタ行オブジェクトを配列。 <BR>
     *  <BR>
     * 　@[（諸経費区分 == null）の場合の条件]<BR>
     * 　@特殊銘柄用海外諸経費マスタ.銘柄ID = 銘柄ID And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.現地銘柄コード = 現地銘柄コード And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.証券会社コード = 証券会社コード And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.市場コード = 市場コード And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.摘要開始年月日 <= 基準日 And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.摘要終了年月日 >= 基準日 And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.売買代金（FROM） < 売買代金 And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.売買代金（TO） >= 売買代金 And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.売買区分 = 売買区分<BR>
     *  <BR>
     * 　@[（諸経費区分 != null）の場合の条件]<BR>
     * 　@特殊銘柄用海外諸経費マスタ.銘柄ID = 銘柄ID And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.現地銘柄コード = 現地銘柄コード And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.証券会社コード = 証券会社コード And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.市場コード = 市場コード And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.摘要開始年月日 <= 基準日 And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.摘要終了年月日 >= 基準日 And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.売買代金（FROM） < 売買代金 And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.売買代金（TO） >= 売買代金 And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.諸経費区分 = 諸経費区分 And<BR>
     * 　@特殊銘柄用海外諸経費マスタ.売買区分 = 売買区分<BR>
     *  <BR>
     *  同一諸経費区分のレコードが複数行取得された場合は例外をスローする。<BR>
     *  ※「同一区分の複数行のレコードが選択されました」<BR>
     *  class:WEB3BusinessLayerException<BR>
     *  tag:BUSINESS_ERROR_03209<BR>
     *  <BR>
     * ４）上記３）にて特殊銘柄用海外諸経費マスタ行の配列を取得しない場合、<BR>
     * ２）取得した海外諸経費マスタ行の配列を返却する。<BR>
     *  <BR>
     * ５）上記３）にて特殊銘柄用海外諸経費マスタ行の配列を取得した場合、<BR>
     * 各特殊銘柄用海外諸経費マスタ行にLoop処理を行う。<BR>
     *  <BR>
     * 　@５－１）特殊銘柄用海外諸経費マスタ行.諸経費区分は<BR>
     * ２）取得した配列の各海外諸経費マスタ行.諸経費区分と一致する場合、以下の操作を行う。<BR> 
     *  <BR>
     * 　@５－２）「銘柄ID」、「現地銘柄コード」以外のフィールド、<BR>
     * 海外諸経費マスタ行を特殊銘柄用海外諸経費マスタ行に付け替える。<BR>
     *  <BR>
     * ６）付け替える後の海外諸経費マスタ行の配列を返却する。<BR>
     * <BR>
     * @@param l_lngProductId - 銘柄ID
     * @@param l_strOffshoreProductCode - 現地銘柄コード
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strMarketCode - 市場コード
     * @@param l_dblForeignTradeAmount - 売買代金（外貨）
     * @@param l_datBaseDate - (基準日) <BR>
     * nullの場合、発注日を使用する。
     * @@param l_strCostDiv - (諸経費区分) <BR>
     * 諸経費区分。すべて取得する場合、nullを指定。 <BR>
     *  <BR>
     * 01：　@現地手数料 <BR>
     * 02：　@現地取引税  <BR>
     * 03：　@その他現地コスト１ <BR>
     * 04：　@その他現地コスト２ <BR>
     * @@param l_strSideDiv - (売買区分)<BR>
     * 売買区分<BR>
     * １：買い<BR>
     * ２：売り<BR>
     * @@return webbroker3.feq.data.ForeignCostParams[]
     * @@throws WEB3BaseException
     * @@roseuid 4282F0510032
     */
    public ForeignCostParams[] getForeignCost(
        Long l_lngProductId,
        String l_strOffshoreProductCode,
        String l_strInstitutionCode,
        String l_strMarketCode,
        double l_dblForeignTradeAmount,
        Date l_datBaseDate,
        String l_strCostDiv,
        String l_strSideDiv)
        throws WEB3BaseException
    {
            
        String STR_METHOD_NAME = "getForeignCost(Long, String, String, String, double, Date, String, String)";
        log.entering(STR_METHOD_NAME);
      
        //１）発注日取得 
        //基準日が未指定（null）の場合、 
        //取引時間管理.get発注日()にて発注日を取得し、基準日とする。 
        Date l_datOrderBizDate = l_datBaseDate;
        if (l_datBaseDate == null)
        {
            l_datOrderBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }

        try
        {
            //２）海外諸経費マスタ検索
            //以下の条件にて、海外諸経費マスタテーブルを検索し、取得した海外諸経費マスタ行オブジェクトを配列。
            List l_lstreturnForeignCost = null;
            Object[] l_strBindValue = null;
            //データ査詢
            QueryProcessor processor = Processors.getDefaultProcessor();
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and market_code = ? ");  //市場コード
            l_sbWhere.append(" and to_char(appli_start_date,'yyyyMMdd') <= ? "); //摘要開始年月日
            l_sbWhere.append(" and to_char(appli_end_date,'yyyyMMdd') >= ? ");   //摘要終了年月日
            l_sbWhere.append(" and amount_from < ? ");  //売買代金（FROM）
            l_sbWhere.append(" and amount_to >= ? ");   //売買代金（TO）

            if (l_strCostDiv == null)
            {
                //[（諸経費区分 == null）の場合の条件] 
                //海外諸経費マスタ.証券会社コード = 証券会社コード And　@
                //海外諸経費マスタ.市場コード = 市場コード And　@ 
                //海外諸経費マスタ.摘要開始年月日 <= 基準日 And 
                //海外諸経費マスタ.摘要終了年月日 >= 基準日 And 
                //海外諸経費マスタ.売買代金（FROM） < 売買代金 And 
                //海外諸経費マスタ.売買代金（TO） >= 売買代金 
                //海外諸経費マスタ.売買区分 = 売買区分
                //１）　@海外諸経費マスタテーブル検索
                //売買区分
                l_sbWhere.append(" and side_div = ? ");

                l_strBindValue = new Object[7];
                l_strBindValue[0] = l_strInstitutionCode;
                l_strBindValue[1] = l_strMarketCode;
                l_strBindValue[2] = 
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
                l_strBindValue[3] = 
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
                l_strBindValue[4] = new Double (l_dblForeignTradeAmount); 
                l_strBindValue[5] = new Double (l_dblForeignTradeAmount); 
                l_strBindValue[6] = l_strSideDiv;
            }
            else
            {
                //[（諸経費区分 != null）の場合の条件] 
                //海外諸経費マスタ.証券会社コード = 証券会社コード And　@
                //海外諸経費マスタ.市場コード = 市場コード And　@
                //海外諸経費マスタ.摘要開始年月日 <= 基準日 And 
                //海外諸経費マスタ.摘要終了年月日 >= 基準日 And 
                //海外諸経費マスタ.売買代金（FROM） < 売買代金 And 
                //海外諸経費マスタ.売買代金（TO） >= 売買代金 And 
                //海外諸経費マスタ.諸経費区分 = 諸経費区分 
                //海外諸経費マスタ.売買区分 = 売買区分
                //１）海外諸経費マスタテーブル検索
                l_sbWhere.append(" and cost_div = ? ");   //諸経費区分
                //売買区分
                l_sbWhere.append(" and side_div = ? ");

                l_strBindValue = new Object[8];
                l_strBindValue[0] = l_strInstitutionCode;
                l_strBindValue[1] = l_strMarketCode;
                l_strBindValue[2] = 
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
                l_strBindValue[3] = 
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
                l_strBindValue[4] = new Double (l_dblForeignTradeAmount); 
                l_strBindValue[5] = new Double (l_dblForeignTradeAmount); 
                l_strBindValue[6] = l_strCostDiv;
                l_strBindValue[7] = l_strSideDiv;
            }

            l_lstreturnForeignCost = 
                processor.doFindAllQuery(
                    ForeignCostRow.TYPE,
                    l_sbWhere.toString(),
                    l_strBindValue);

            int l_intForeignCostSize = l_lstreturnForeignCost.size(); 

            ForeignCostParams[] l_foreignCostParams =
                new ForeignCostParams[l_intForeignCostSize];
            for(int i = 0; i < l_intForeignCostSize; i++)
            {
                l_foreignCostParams[i] = new ForeignCostParams((ForeignCostRow)l_lstreturnForeignCost.get(i));
            }

            // 同一諸経費区分のレコードが複数行取得された場合は例外をスローする。
            // ※「同一区分の複数行のレコードが選択されました」
            int l_intCounter01 = 0;
            int l_intCounter02 = 0;
            int l_intCounter03 = 0;
            int l_intCounter04 = 0;

            for (int i = 0; i < l_intForeignCostSize; i++)
            {
                if (WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    l_intCounter01++;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_TAX.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    l_intCounter02++;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    l_intCounter03++;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    l_intCounter04++;
                }

                if (l_intCounter01 > 1 || l_intCounter02 >1 || l_intCounter03 > 1 || l_intCounter04 > 1)
                {
                    log.error("同一区分の複数行のレコードが選択されました");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03209,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "同一区分の複数行のレコードが選択されました");
                }
            }

            // ３）特殊銘柄用海外諸経費マスタ検索
            // 以下の条件にて、特殊銘柄用海外諸経費マスタテーブルを検索し、
            // 取得した特殊銘柄用海外諸経費マスタ行オブジェクトを配列。 
            List l_lstProductForeignCost = null;
            Object[] l_arrayBindValue = null;
            StringBuffer l_sbQuery = new StringBuffer();

            // 特殊銘柄用海外諸経費マスタ.銘柄ID = 銘柄ID And
            // 特殊銘柄用海外諸経費マスタ.現地銘柄コード = 現地銘柄コード And
            // 特殊銘柄用海外諸経費マスタ.証券会社コード = 証券会社コード And
            // 特殊銘柄用海外諸経費マスタ.市場コード = 市場コード And
            // 特殊銘柄用海外諸経費マスタ.摘要開始年月日 <= 基準日 And
            // 特殊銘柄用海外諸経費マスタ.摘要終了年月日 >= 基準日 And
            // 特殊銘柄用海外諸経費マスタ.売買代金（FROM） < 売買代金 And
            // 特殊銘柄用海外諸経費マスタ.売買代金（TO） >= 売買代金 And
            l_sbQuery.append("product_id = ?");
            l_sbQuery.append(" and offshore_product_code = ?");
            l_sbQuery.append(" and institution_code = ?");
            l_sbQuery.append(" and market_code = ?");
            l_sbQuery.append(" and to_char(appli_start_date, 'yyyyMMdd') <= ?");
            l_sbQuery.append(" and to_char(appli_end_date, 'yyyyMMdd') >= ?");
            l_sbQuery.append(" and amount_from < ?");
            l_sbQuery.append(" and amount_to >= ?");

            if (l_strCostDiv == null)
            {
                // [（諸経費区分 == null）の場合の条件]
                // 特殊銘柄用海外諸経費マスタ.売買区分 = 売買区分
                l_sbQuery.append(" and side_div = ?");

                l_arrayBindValue = new Object[9];
                l_arrayBindValue[0] = l_lngProductId;
                l_arrayBindValue[1] = l_strOffshoreProductCode;
                l_arrayBindValue[2] = l_strInstitutionCode;
                l_arrayBindValue[3] = l_strMarketCode;
                l_arrayBindValue[4] = WEB3DateUtility.formatDate(l_datOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                l_arrayBindValue[5] = WEB3DateUtility.formatDate(l_datOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                l_arrayBindValue[6] = new Double(l_dblForeignTradeAmount);
                l_arrayBindValue[7] = new Double(l_dblForeignTradeAmount);
                l_arrayBindValue[8] = l_strSideDiv;
            }
            else
            {
                // [（諸経費区分 != null）の場合の条件]
                // 特殊銘柄用海外諸経費マスタ.諸経費区分 = 諸経費区分 And
                // 特殊銘柄用海外諸経費マスタ.売買区分 = 売買区分
                l_sbQuery.append(" and cost_div = ?");
                l_sbQuery.append(" and side_div = ?");

                l_arrayBindValue = new Object[10];
                l_arrayBindValue[0] = l_lngProductId;
                l_arrayBindValue[1] = l_strOffshoreProductCode;
                l_arrayBindValue[2] = l_strInstitutionCode;
                l_arrayBindValue[3] = l_strMarketCode;
                l_arrayBindValue[4] = WEB3DateUtility.formatDate(l_datOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                l_arrayBindValue[5] = WEB3DateUtility.formatDate(l_datOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                l_arrayBindValue[6] = new Double(l_dblForeignTradeAmount);
                l_arrayBindValue[7] = new Double(l_dblForeignTradeAmount);
                l_arrayBindValue[8] = l_strCostDiv;
                l_arrayBindValue[9] = l_strSideDiv;
            }

            l_lstProductForeignCost =
                processor.doFindAllQuery(
                    SpecialProductForeignCostParams.TYPE,
                    l_sbQuery.toString(),
                    l_arrayBindValue);

            if (l_lstProductForeignCost.isEmpty())
            {
                // ４）上記３）にて特殊銘柄用海外諸経費マスタ行の配列を取得しない場合、
                //     ２）取得した海外諸経費マスタ行の配列を返却する。
                log.exiting(STR_METHOD_NAME);
                return l_foreignCostParams;
            }
            else
            {
                // 同一諸経費区分のレコードが複数行取得された場合は例外をスローする。
                // ※「同一区分の複数行のレコードが選択されました」
                l_intCounter01 = 0;
                l_intCounter02 = 0;
                l_intCounter03 = 0;
                l_intCounter04 = 0;

                for (int i = 0; i < l_lstProductForeignCost.size(); i++)
                {
                    SpecialProductForeignCostParams l_productForeignCostParams =
                        (SpecialProductForeignCostParams)l_lstProductForeignCost.get(i);
                    
                    if (WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE.equals(
                        l_productForeignCostParams.getCostDiv()))
                    {
                        l_intCounter01++;
                    }
                    else if (WEB3FeqCostDivDef.FOREIGN_TAX.equals(
                        l_productForeignCostParams.getCostDiv()))
                    {
                        l_intCounter02++;
                    }
                    else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(
                        l_productForeignCostParams.getCostDiv()))
                    {
                        l_intCounter03++;
                    }
                    else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(
                        l_productForeignCostParams.getCostDiv()))
                    {
                        l_intCounter04++;
                    }

                    if (l_intCounter01 > 1 || l_intCounter02 >1 || l_intCounter03 > 1 || l_intCounter04 > 1)
                    {
                        log.error("同一区分の複数行のレコードが選択されました");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03209,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "同一区分の複数行のレコードが選択されました");
                    }
                }

                // ５）上記３）にて特殊銘柄用海外諸経費マスタ行の配列を取得した場合、
                //     各特殊銘柄用海外諸経費マスタ行にLoop処理を行う。
                for (int i = 0; i < l_lstProductForeignCost.size(); i++)
                {
                    SpecialProductForeignCostParams l_productForeignCostParams =
                        (SpecialProductForeignCostParams)l_lstProductForeignCost.get(i);

                    for (int j = 0; j < l_foreignCostParams.length; j++)
                    {
                        // ５－１）特殊銘柄用海外諸経費マスタ.諸経費区分は２）取得した
                        //     配列の各海外諸経費マスタ行.諸経費区分と一致する場合、以下の操作を行う。
                        // ５－２）「銘柄ID」以外のフィールド、海外諸経費マスタ行を特殊銘柄用海外諸経費マスタ行に付け替える。
                        if (l_productForeignCostParams.getCostDiv().equals(
                            l_foreignCostParams[j].getCostDiv()))
                        {
                            l_foreignCostParams[j].setAppliStartDate(
                                l_productForeignCostParams.getAppliStartDate());
                            l_foreignCostParams[j].setAppliEndDate(
                                l_productForeignCostParams.getAppliEndDate());
                            l_foreignCostParams[j].setAmountFrom(
                                l_productForeignCostParams.getAmountFrom());
                            l_foreignCostParams[j].setAmountTo(
                                l_productForeignCostParams.getAmountTo());
                            l_foreignCostParams[j].setCommisionRate(
                                l_productForeignCostParams.getCommisionRate());
                            l_foreignCostParams[j].setAddAmount(
                                l_productForeignCostParams.getAddAmount());
                            l_foreignCostParams[j].setScale(
                                l_productForeignCostParams.getScale());
                            l_foreignCostParams[j].setRoundDiv(
                                l_productForeignCostParams.getRoundDiv());
                            l_foreignCostParams[j].setLastUpdater(
                                l_productForeignCostParams.getLastUpdater());
                            l_foreignCostParams[j].setCreatedTimestamp(
                                l_productForeignCostParams.getCreatedTimestamp());
                            l_foreignCostParams[j].setLastUpdatedTimestamp(
                                l_productForeignCostParams.getLastUpdatedTimestamp());
                        }
                    }
                }

                // ６）付け替える後の海外諸経費マスタ行の配列を返却する。
                log.exiting(STR_METHOD_NAME);
                return l_foreignCostParams;
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)        
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
   
    /**
     * (calc外国株式金額) <BR>
     * 外国株式金額計算を行う。 <BR>
     *  <BR>
     * 　@・現地手数料 <BR>
     * 　@・現地取引税 <BR>
     * 　@・その他コスト１ <BR>
     * 　@・その他コスト２ <BR>
     * 　@・現地清算代金 <BR>
     * 　@・現地清算代金（円貨） <BR>
     * 　@・委託手数料 <BR>
     * 　@・委託手数料（外貨） <BR>
     * 　@・委託手数料消費税 <BR>
     * 　@・委託手数料消費税（外貨） <BR>
     * 　@・受渡代金 <BR>
     * 　@・受渡代金（外貨） <BR>
     *  <BR>
     * １）　@発注日取得 <BR>
     * 　@基準日が未指定（null）の場合、取引時間管理.get発注日() <BR>
     * 　@にて発注日を取得し、基準日とする。 <BR>
     *  <BR>
     * ２）　@手数料オブジェクトを生成し、プロパティに以下の通りセットを行う。<BR>
     *  <BR>
     * 　@証券会社コード： 補助口座.getInstitution().getInstitutionCode() <BR>
     * 　@部店ID： 補助口座.get取引店().BranchId() <BR>
     * 　@手数料商品コード： this.get手数料商品コード() <BR>
     * 　@取引コード（SONAR）：　@this.get取引コード（SONAR）() <BR>
     * 　@発注日：　@発注日 <BR>
     * 　@弁済区分：　@弁済区分.00：その他  <BR>
     * 　@注文チャネル： 注文チャネル <BR>
     * 　@is指値： is指値注文 <BR>
     * 　@市場コード（SONAR）：　@市場.市場コード（SONAR） <BR>
     *  <BR>
     * ３）　@外国株式金額計算 <BR>
     * 　@this.calc外国株式金額(手数料, 補助口座, 外国株式銘柄, 市場,  <BR>
     * 　@基準日, 売買代金（外貨）, 為替レート, is買付, is約定計算, is指値)に<BR>
     * 　@委譲（deligate）する。 <BR>
     *  <BR>
     * ４）　@外国株式金額計算結果オブジェクト返却 <BR>
     * 　@外国株式金額計算結果オブジェクトを返却する。 <BR>
     * @@param l_subAccount - 補助口座
     * @@param l_feqProduct - 外国株式銘柄
     * @@param l_market - 市場
     * @@param l_datBaseDate - 基準日
     * @@param l_dblTradePriceFc - 売買代金（外貨）
     * @@param l_dblFxRate - 為替レート
     * （※ 0指定の場合は、通貨テーブルの為替レート）
     * @@param l_blnIsBuy - (is買付) 
     * 買付かの判定 <BR>
     *  <BR>
     * true：買 <BR>
     * false：売 <BR>
     * @@param l_blnIsExecCalc - (is約定計算)
     * 約定計算かの判定 <BR>
     *  <BR>
     * true：約定計算 <BR>
     * false：概算計算 <BR>
     * @@param l_blnIsLimitPrice - (is指値)
     * 指値かの判定 <BR>
     *  <BR>
     * true：指値 <BR>
     * false：成行 <BR>
     * @@param l_strOrderChannel 注文チャネル
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 42898CEF00EA
     */
    public WEB3FeqAmountCalcResult calcFeqAmount(
        WEB3GentradeSubAccount l_subAccount,
        WEB3FeqProduct l_feqProduct,
        WEB3GentradeMarket l_market,
        Date l_datBaseDate,
        Date l_datExecDate,
        double l_dblTradePriceFc,
        double l_dblFxRate,
        boolean l_blnIsBuy,
        boolean l_blnIsExecCalc,
        boolean l_blnIsLimitPrice,
        String l_strOrderChannel) throws WEB3BaseException
    {
        
        String STR_METHOD_NAME = 
            "calcFeqAmount(WEB3GentradeSubAccount, WEB3FeqProduct, " + 
            "WEB3GentradeMarket, Date, double, double, boolean, " + 
            "boolean, boolean, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_feqProduct == null || l_market == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）発注日取得 
        //基準日が未指定（null）の場合、 
        //取引時間管理.get発注日()にて発注日を取得し、基準日とする。 
        Date l_datOrderBizDate = l_datBaseDate;
        if (l_datBaseDate == null)
        {
            l_datOrderBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }

        log.debug("発注日：[" + l_datOrderBizDate + "]");
        log.debug("約定日：[" + l_datExecDate + "]");
        //２）手数料オブジェクトを生成し、プロパティに以下の通りセットを行う。  

        WEB3GentradeCommission l_gentradeCommission 
            = new WEB3GentradeCommission();

        //証券会社コード： 補助口座.getInstitution().getInstitutionCode() 
        l_gentradeCommission.setInstitutionCode(
            l_subAccount.getInstitution().getInstitutionCode());

        //部店ID： 補助口座.get取引店().BranchId() 
        l_gentradeCommission.setBranchId(
            l_subAccount.getMainAccount().getBranch().getBranchId());

        //手数料商品コード： this.get手数料商品コード()
        l_gentradeCommission.setCommissionProductCode(
            this.getCommissionProductCode());

        //取引コード（SONAR）：　@this.get取引コード（SONAR）()
        l_gentradeCommission.setSonarTradedCode(this.getSonarTradedCode(l_blnIsBuy));

        //発注日：　@発注日
        l_gentradeCommission.setOrderBizDate(
            new Timestamp(l_datOrderBizDate.getTime()));

        //弁済区分：　@弁済区分.00：その他
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);

        //注文チャネル： 注文チャネル
        l_gentradeCommission.setOrderChannel(l_strOrderChannel);

        //is指値： is指値注文
        l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);

        //市場コード（SONAR）：　@市場.市場コード（SONAR） 
        MarketRow l_marketRow = (MarketRow) (l_market.getDataSourceObject());       
        l_gentradeCommission.setSonarMarketCode(
            l_marketRow.getSonarMarketCode());

        //３）外国株式金額計算
        //this.calc外国株式金額(手数料, 補助口座, 外国株式銘柄, 市場, 
        //基準日, 売買代金（外貨）, 為替レート, is買付, is約定計算, is指値)に
        //委譲（deligate）する。
        WEB3FeqAmountCalcResult l_calcResult = 
            this.calcFeqAmount(
                l_gentradeCommission,
                l_subAccount,
                l_feqProduct,
                l_market,
                l_datExecDate,
                l_dblTradePriceFc,
                l_dblFxRate,
                l_blnIsBuy,
                l_blnIsExecCalc,
                l_blnIsLimitPrice);

        //４）外国株式金額計算結果オブジェクト返却
        //外国株式金額計算結果オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }

    /**
     * (外国株式金額) <BR>
     * 外国株式金額計算を行う。 <BR>
     *  <BR>
     * 　@・現地手数料 <BR>
     * 　@・現地取引税 <BR>
     * 　@・その他コスト１ <BR>
     * 　@・その他コスト２ <BR>
     * 　@・現地清算代金 <BR>
     * 　@・現地清算代金（円貨） <BR>
     * 　@・委託手数料 <BR>
     * 　@・委託手数料（外貨） <BR>
     * 　@・委託手数料消費税 <BR>
     * 　@・委託手数料消費税（外貨） <BR>
     * 　@・受渡代金 <BR>
     * 　@・受渡代金（外貨） <BR>
     *  <BR>
     * シーケンス図 <BR>
     * 「（外株計算）calc外国株式金額」参照 <BR>
     * @@param l_commission - (手数料) 
     * 手数料オブジェクト
     * @@param l_subAccount - 補助口座
     * @@param l_feqProduct - 外国株式銘柄
     * @@param l_market - 市場
     * @@param l_datBaseDate - 基準日
     * @@param l_dblTradePriceFc - 売買代金（外貨）
     * @@param l_dblFxRate - (為替レート) 
     * （※ 0指定の場合は、通貨テーブルの為替レート）
     * @@param l_blnIsBuy - (is買付)
     * 買付かの判定 <BR>
     *  <BR>
     * true：買 <BR>
     * false：売 <BR>
     * @@param l_blnIsExecCalc - (is約定計算)
     * 約定計算かの判定 <BR>
     *  <BR>
     * true：約定計算 <BR>
     * false：概算計算 <BR>
     * @@param l_blnIsLimitPrice - (is指値)
     * 指値かの判定 <BR>
     *  <BR>
     * true：指値 <BR>
     * false：成行 <BR>
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 42A5359F026F
     */
    protected WEB3FeqAmountCalcResult calcFeqAmount(
        WEB3GentradeCommission l_commission,
        WEB3GentradeSubAccount l_subAccount,
        WEB3FeqProduct l_feqProduct,
        WEB3GentradeMarket l_market,
        Date l_datBaseDate,
        double l_dblTradePriceFc,
        double l_dblFxRate,
        boolean l_blnIsBuy,
        boolean l_blnIsExecCalc,
        boolean l_blnIsLimitPrice) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcFeqAmount(WEB3GentradeCommission, " +
            "WEB3GentradeSubAccount, WEB3FeqProduct, WEB3GentradeMarket, " +
            "Date, double, double, boolean, boolean, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_feqProduct == null || l_market == null || l_commission == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 計算結果オブジェクトを生成する。 
        WEB3FeqAmountCalcResult l_calcResult = new WEB3FeqAmountCalcResult();

        //1.2 売買代金（外貨）をセットする。 
        l_calcResult.setTradePriceFc(l_dblTradePriceFc);

        //1.3 通貨オブジェクトを取得する。 
        WEB3GentradeCurrency l_genCurrency = l_feqProduct.getCurrency();

        //1.4 為替レートを取得する。 
        double l_dblNewFxRate = 
            l_genCurrency.getExchangeRate(l_blnIsBuy, l_blnIsExecCalc, l_dblFxRate);
        log.debug("為替レート = " + l_dblNewFxRate);

        //1.5 円貨換算丸め方式を取得する 
        String l_strJPYRoundDiv = l_genCurrency.getChangeJpyRoundDiv();
        log.debug("円貨換算丸め方式 = " + l_strJPYRoundDiv);

        //1.6 現地売買代金（円貨）を計算する。 
        double l_dblJPYAmount = 
            this.calcJPYAmount(l_dblTradePriceFc, l_dblNewFxRate, l_strJPYRoundDiv);
        log.debug("現地売買代金（円貨） = " + l_dblJPYAmount);
        
        //1.7 売買代金（円貨）をセットする。
        l_calcResult.setTradePrice(l_dblJPYAmount);
        
        //1.8 get証券会社コード( )
        String l_strInstitutionCod = l_genCurrency.getInstitutionCode();
        
        //1.9 海外諸経費，現地清算代金を取得する。 
        WEB3FeqForeignCost l_foreignCost = 
            this.calcForeignCost(
                new Long(l_feqProduct.getProductId()),
                l_feqProduct.getOffshoreProductCode(),
                l_strInstitutionCod, 
                l_market.getMarketCode(), 
                l_dblTradePriceFc, 
                l_datBaseDate, 
                l_blnIsBuy);
            
        //1.10 現地諸経費をセットする。 
        l_calcResult.setForeignCost(l_foreignCost);
        
        //1.11 現地清算代金を取得する。
        double l_dblBalanceAmount = l_calcResult.getBalanceAmountFc();
        log.debug("現地清算代金 = " + l_dblBalanceAmount);
        
        //     現地清算代金が０以下の場合エラーとする。
        if (l_dblBalanceAmount <= 0)
        {
            throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_02295,
            this.getClass() + "." + STR_METHOD_NAME,
            "現地売付代金が現地諸経費を下回っています。");
        }
        
        //1.12 現地清算代金（円貨）を計算する。 
        double l_dblJPYBalanceAmount = 
            this.calcJPYAmount(l_dblBalanceAmount, l_dblNewFxRate, l_strJPYRoundDiv);
        log.debug("現地清算代金（円貨） = " + l_dblJPYBalanceAmount);
        
        //1.13 現地清算代金（円貨）をセットする。
        l_calcResult.setBalanceAmount(l_dblJPYBalanceAmount);
        
        //1.14 諸経費計算用代金をセットする。
        l_commission.setExpensesCalcAmount(l_dblJPYBalanceAmount);
        
        //1.15 委託手数料を計算する。 
        this.calcCommission(l_commission, l_subAccount);
        
        //1.16 委託手数料を取得する。
        double l_dblCommission = l_commission.getCommission();
        log.debug("委託手数料 = " + l_dblCommission);
        
        //1.17 委託手数料をセットする。 
        l_calcResult.setCommissionFee(l_dblCommission);
        
        //1.18 手数料Ｎｏを取得する
        String  l_strCommissionNo = l_commission.getCommissionNo();
        log.debug("手数料Ｎｏ = " + l_strCommissionNo);
        
        //1.19 手数料Ｎｏをセットする。 
        l_calcResult.setCommissionNumber(l_strCommissionNo);

        //1.20 get手数料No枝番
        String l_strCommissionBranchNumber = l_commission.getCommissionRevNo();

        //1.21 set手数料No枝番(String)
        l_calcResult.setCommissionBranchNumber(l_strCommissionBranchNumber);
        log.debug("手数料No枝番 = " + l_strCommissionBranchNumber);
        
        //1.22 外貨換算丸め方式を取得する
        String l_strFCCYRoundDiv = l_genCurrency.getChangeFCcyRoundDiv();
        log.debug("外貨換算丸め方式 = " + l_strFCCYRoundDiv);
        
        //1.23 小数部桁数を取得する。
        int l_intScale = l_genCurrency.getScale();
        log.debug("小数部桁数 = " + l_intScale);
        
        //1.24 委託手数料（外貨）を計算する。 
        double l_dblForeignCCYAmount = 
            this.calcForeignCCYAmount(
                l_dblCommission, 
                l_dblNewFxRate, 
                l_intScale, 
                l_strFCCYRoundDiv);
        log.debug("委託手数料（外貨） = " + l_dblForeignCCYAmount);

        //1.25 委託手数料（外貨）をセットする。 
        l_calcResult.setCommissionFeeFc(l_dblForeignCCYAmount);

        //1.26 委託手数料消費税を計算する。 
        double l_dblSalesTax = 
            this.calcSalesTax(l_dblCommission, l_datBaseDate, l_subAccount);
        log.debug("委託手数料消費税 = " + l_dblSalesTax);

        //1.27 委託手数料消費税をセットする。
        l_calcResult.setCommissionFeeTax(l_dblSalesTax);

        //1.28 委託手数料消費税（外貨）を計算する。 
        double l_dblForeignSalesTax = 
            this.calcForeignCCYAmount(
                l_dblSalesTax, 
                l_dblNewFxRate,
                l_intScale, 
                l_strFCCYRoundDiv);
        log.debug("委託手数料消費税（外貨） = " + l_dblForeignSalesTax);

        //1.29 委託手数料消費税（外貨）をセットする。 
        l_calcResult.setCommissionFeeTaxFc(l_dblForeignSalesTax);
        //受渡代金計算をセットする。 
        //[set受渡代金()に指定する引数] 
        //●買（is買付==true）の場合、以下の計算結果 
        //　@現地清算代金（円貨）＋委託手数料＋委託手数料消費税 
        //●売（is買付==false）の場合、以下の計算結果 
        //　@現地清算代金（円貨）－委託手数料－委託手数料消費税
        
        //[set受渡代金（外貨）()に指定する引数] 
        //●買付（is買付==true）の場合、以下の計算結果 
        // 清算代金（外貨）＋委託手数料（外貨）＋委託手数料消費税（外貨） 
        //●売付（is買付==false）の場合、以下の計算結果 
        //清算代金（外貨）－委託手数料（外貨）－委託手数料消費税（外貨）

        if (l_blnIsBuy)
        {
            //1.30 set受渡代金(double)
            double l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    WEB3FeqOrderUtility.decimalPlus(
                        l_dblJPYBalanceAmount, 
                        l_dblCommission), 
                    l_dblSalesTax);
            l_calcResult.setNetAmount(l_dblTemp);
            //1.31 set受渡代金（外貨）(double)
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    WEB3FeqOrderUtility.decimalPlus(
                        l_dblBalanceAmount, 
                        l_dblForeignCCYAmount), 
                    l_dblForeignSalesTax);
            l_calcResult.setNetAmountFc(l_dblTemp);
        }
        else
        {
            //1.30 set受渡代金(double)
            double l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    WEB3FeqOrderUtility.decimalMinus(
                        l_dblJPYBalanceAmount, 
                        l_dblCommission), 
                    l_dblSalesTax);
            l_calcResult.setNetAmount(l_dblTemp);
            //1.31 set受渡代金（外貨）(double)
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    WEB3FeqOrderUtility.decimalMinus(
                        l_dblBalanceAmount, 
                        l_dblForeignCCYAmount), 
                    l_dblForeignSalesTax);
            l_calcResult.setNetAmountFc(l_dblTemp);
        }
        log.debug("受渡代金 = " + l_calcResult.getNetAmount());
        log.debug("受渡代金（外貨） = " + l_calcResult.getNetAmountFc());

        double l_dblChargeRatio =
            this.getChargeRatio(
                l_commission.getInstitutionCode(),
                l_commission.getBranchId(),
                l_subAccount.getAccountId(),
                l_commission.getOrderBizDate());
        l_calcResult.setChargeRatio(l_dblChargeRatio);
        log.debug("徴収率 = " + l_calcResult.getChargeRatio());
        
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }

    /**
     * (最大約定要素取得) <BR>
     * 最大約定要素、［分割(n)］売買代金（外貨）、<BR>
     * 売買代金合計、売買代金（外貨）合計を取得。 <BR>
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行)
     * @@param l_currency 通貨
     * @@param l_dblTradePrices［分割(n)］売買代金
     * @@param l_dblTradePriceFcs［分割(n)］売買代金（外貨）
     * @@param l_dblTotals 売買代金合計
     * index0 円貨　@index1 外貨
     * @@return int 最大約定要素
     * @@exception WEB3BaseException
     */
    public int calcMaxExecIndex(
        FeqFinTransactionParams[] l_feqFinTransactionParams,
        WEB3GentradeCurrency l_currency,
        double[] l_dblTradePrices,
        double[] l_dblTradePriceFcs,
        double[] l_dblTotals) 
        throws WEB3BaseException, DataQueryException, DataNetworkException
    {
        String STR_METHOD_NAME = 
            "calcMaxExecIndex(FeqFinTransactionParams[], WEB3GentradeCurrency, double[], double[], double[])";
        log.entering(STR_METHOD_NAME);

        if (l_feqFinTransactionParams == null || 
            l_feqFinTransactionParams.length ==0 ||
            l_dblTotals == null ||
            l_dblTotals.length != 2 ||
            l_dblTradePriceFcs  == null ||
            l_dblTradePrices  == null ||
            l_dblTradePriceFcs.length != l_feqFinTransactionParams.length ||
            l_dblTradePrices.length != l_feqFinTransactionParams.length)             
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        double l_dblMaxTradePriceFc = 0.0D;
        double l_dblMaxPrice = 0.0D;
        long l_lngMaxExecSerialNo = 0L;
        Timestamp l_lngMaxExecTimestamp = null;
        Timestamp l_tsMaxFinTransactionTimestamp = null;
        
        int l_intMax = 0;

        l_dblTotals[0] = 0.0D;
        l_dblTotals[1] = 0.0D;

        for (int i = 0; i < l_feqFinTransactionParams.length; i++)
        {
            //売買代金
            double l_dblTradePrice = 0.0D;
            //単価
            double l_dblPrice = l_feqFinTransactionParams[i].getPrice();

            //売買代金（外貨）
            double l_dblTradePriceFc =
                l_feqFinTransactionParams[i].getQuantity() * l_dblPrice;
            BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblTradePriceFc);
            int l_intDecimalPlace = l_currency.getScale();
            l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            l_dblTradePriceFc = l_bdTradePriceFc.doubleValue();
            l_dblTradePrice = 
                this.calcJPYAmount(
                    l_dblTradePriceFc,
                    l_feqFinTransactionParams[i].getFxRate(),
                    l_currency.getChangeJpyRoundDiv());

            //［分割(n)］売買代金
            l_dblTradePrices[i] = l_dblTradePrice;
            //［分割(n)］売買代金（外貨）
            l_dblTradePriceFcs[i] = l_dblTradePriceFc;

            long l_lngExecutionId = 
                l_feqFinTransactionParams[i].getOrderExecutionId();
            
            WEB3FeqOrderExecution l_orderExecution = null;
            
            //約定オブジェクトが存在する
            boolean l_isFoundData = true;

            try
            {
                l_orderExecution = new WEB3FeqOrderExecution(l_lngExecutionId); 
            }
            catch (DataFindException e)
            {
                l_isFoundData = false;
            }
            
            if (l_orderExecution == null)
            {
                l_isFoundData = false;
            }
            //約定通番
            long l_lngExecSerialNo = 0L;
            //約定日時
            Timestamp l_tsExecTimestamp = null; 
            //トランザクション(取引勘定明細)行.トランザクション発生日時
            Timestamp l_tsFinTransactionTimestamp = null;
            
            if (l_isFoundData)
            {
                l_lngExecSerialNo = l_orderExecution.getExecutionSerialNo();
                l_tsExecTimestamp = l_orderExecution.getExecutionTimestamp();
            }
            else
            {
                l_tsFinTransactionTimestamp =
                    l_feqFinTransactionParams[i].getFinTransactionTimestamp();
            }
            
            log.debug("売買代金 = " + l_dblTradePrice);
            log.debug("売買代金（外貨） = " + l_dblTradePriceFc);

            //②　@最大約定要素の判定
            //引数.トランザクション（取引勘定明細）行[]の各要素のうち、以下の条件を満たすものを最大約定とする。
            //（最大約定の判定）
            //－　@売買代金（①@で計算した[分割（n）]売買代金）が最大の約定を最大約定とする。
            //－　@売買代金（①@で計算した[分割（n）]売買代金）が同額の場合、
            //約定単価（トランザクション（取引勘定明細）行.約定単価）が高い約定を最大約定とする。
            //－　@約定単価が同額の場合、
            //約定（トランザクション（取引勘定明細）行.getOrderExecutionId()に
            //該当する約定オブジェクト）.約定順番号、または約定日時が大きい約定を最大約定とする。
            //※約定オブジェクトが存在しない場合は、
            //トランザクション(取引勘定明細)行.トランザクション発生日時が大きいほうを
            //最大約定とする。
            if (l_dblTradePriceFc > l_dblMaxTradePriceFc)
            {
                l_dblMaxTradePriceFc = l_dblTradePriceFc;
                l_dblMaxPrice = l_dblPrice;
                l_lngMaxExecSerialNo = l_lngExecSerialNo;
                l_lngMaxExecTimestamp = l_tsExecTimestamp;
                l_tsMaxFinTransactionTimestamp = null;
                    
                l_intMax = i;
            }
            else if (l_dblTradePriceFc == l_dblMaxTradePriceFc)
            {
                if (l_dblPrice > l_dblMaxPrice)
                {
                    l_dblMaxTradePriceFc = l_dblTradePriceFc;
                    l_dblMaxPrice = l_dblPrice;
                    l_lngMaxExecSerialNo = l_lngExecSerialNo;
                    l_lngMaxExecTimestamp = l_tsExecTimestamp;
                    l_tsMaxFinTransactionTimestamp = null;

                    l_intMax = i;
                }
                else if (l_dblMaxPrice == l_dblPrice)
                {
                    //約定オブジェクトが存在する場合
                    if (l_isFoundData)
                    {
                        if (l_lngExecSerialNo > l_lngMaxExecSerialNo)
                        {
                            l_dblMaxTradePriceFc = l_dblTradePriceFc;
                            l_dblMaxPrice = l_dblPrice;
                            l_lngMaxExecSerialNo = l_lngExecSerialNo;
                            l_lngMaxExecTimestamp = l_tsExecTimestamp;
                            l_tsMaxFinTransactionTimestamp = null;
                    
                            l_intMax = i;
                        }
                        else if (l_lngExecSerialNo == l_lngMaxExecSerialNo)
                        {
                            if (WEB3DateUtility.compareToSecond(l_tsExecTimestamp, l_lngMaxExecTimestamp) > 0)
                            {
                                l_dblMaxTradePriceFc = l_dblTradePriceFc;
                                l_dblMaxPrice = l_dblPrice;
                                l_lngMaxExecSerialNo = l_lngExecSerialNo;
                                l_lngMaxExecTimestamp = l_tsExecTimestamp;
                                l_tsMaxFinTransactionTimestamp = null;
                                
                                l_intMax = i;
                            }
                        }
                    }
                    else
                    {
                        //トランザクション発生日時が大きいほうを最大約定とする
                        if (WEB3DateUtility.compareToSecond(l_tsFinTransactionTimestamp, l_tsMaxFinTransactionTimestamp) > 0)
                        {
                            l_dblMaxTradePriceFc = l_dblTradePriceFc;
                            l_dblMaxPrice = l_dblPrice;
                            l_lngMaxExecSerialNo = 0;
                            l_lngMaxExecTimestamp = null;
                            l_tsMaxFinTransactionTimestamp = l_tsFinTransactionTimestamp;
                            
                            l_intMax = i;
                        }
                    }
                }
            }

            //③　@[合　@計]売買代金を計算する
            //①@の計算結果の合計値を[合　@計]売買代金とする。
            l_dblTotals[0] = 
                WEB3FeqOrderUtility.decimalPlus(l_dblTotals[0], l_dblTradePrice);
            l_dblTotals[1] = 
                WEB3FeqOrderUtility.decimalPlus(l_dblTotals[1], l_dblTradePriceFc);
        }

        log.exiting(STR_METHOD_NAME);
        return l_intMax;
    }

    /**
     * (calc外国株式金額合計)<BR>
     * 外国株式金額合計を取得。<BR>
     * @@param l_feqFinTransactionParams トランザクション（取引勘定明細）行オブジェクト
     * @@param l_feqProduct 外国株式銘柄
     * @@param l_orderManager 注文マネージャ
     * @@param l_accountManager 拡張アカウントマネージャ
     * @@param l_market 市場
     * @@param l_datBaseDate 基準日
     * @@return l_dblTradePriceFcTotal 売買代金（外貨）合計
     * @@exception WEB3BaseException
     */
    public WEB3FeqAmountCalcResult calcFeqAmountTotal(
        FeqFinTransactionParams l_feqFinTransactionParams,
        WEB3FeqProduct l_feqProduct,
        WEB3FeqOrderManager l_orderManager,
        WEB3GentradeAccountManager l_accountManager,
        WEB3GentradeMarket l_market,
        Date l_datBaseDate,
        Date l_ExecDate,
        double l_dblTradePriceFcTotal 
        ) throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = 
            "calcFeqAmountTotal(FeqFinTransactionParams, WEB3FeqProduct, " + 
            "WEB3FeqOrderManager, WEB3GentradeAccountManager, WEB3GentradeMarket, Date, double)";
        log.entering(STR_METHOD_NAME);

        if (l_feqFinTransactionParams == null || 
            l_feqProduct == null ||
            l_orderManager == null ||
            l_accountManager == null ||
            l_ExecDate == null ||
            l_market == null)             
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //④　@［合　@ 計］外国株式金額（諸経費）を計算する
        //外国株式計算サービス.calc外国株式金額()にて計算した値を[合　@計]計算結果とする。
        //[calc外国株式金額()に指定する引数]
        //補助口座：　@トランザクション（取引勘定明細）行.getSubAccountId()に該当する補助口座
        long l_lngSubAccountId = 
            l_feqFinTransactionParams.getSubAccountId();
        long l_lngAccountId = l_feqFinTransactionParams.getAccountId();
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)l_accountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);

        //売買代金（外貨）：　@②[合 計]売買代金
        //為替レート：　@0（※ 0指定の場合は、通貨テーブルの為替レート）
        //is買付：　@トランザクション（取引勘定明細）行.getFinTransactionType() == ”外株買い”の場合true。以外、false。
        boolean l_blnIsBuy = false;
        if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionParams.getFinTransactionType()))
        {
            l_blnIsBuy = true;
        }
            
        //is約定計算：　@true
        //is指値：　@（注文単位(**).getLimitPrice() == 0）の場合false、以外true。
        //(**)トランザクション（取引勘定明細）行.getOrderUnitId()に該当する注文単位。
        OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_feqFinTransactionParams.getOrderUnitId());
        FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_blnIsPrice = true;
        if (l_orderUnitRow.getLimitPrice() == 0.0D)
        {
            l_blnIsPrice = false;
        }
        double l_dbFxRate = l_feqFinTransactionParams.getFxRate();
        WEB3FeqAmountCalcResult l_calcResult = 
            this.calcFeqAmount(
                l_subAccount, 
                l_feqProduct, 
                l_market,
                l_datBaseDate, 
                l_ExecDate,
                l_dblTradePriceFcTotal, 
                l_dbFxRate, 
                l_blnIsBuy, 
                true, 
                l_blnIsPrice, 
                l_orderUnitRow.getOrderChanel());

        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }

    /**
     * (［分割(n)］各諸経費，受渡代金) <BR>
     * 最大約定要素以外の要素について、［分割(n)］各諸経費，受渡代金、合計値を計算する<BR>
     * @@param l_intMax 最大約定要素 
     * @@param l_strMarketCode 市場コード
     * @@param l_dblTradePrices ［分割(n)］売買代金
     * @@param l_dblTradePriceFcs ［分割(n)］売買代金（外貨）
     * @@param l_calcResult 外国株式金額（諸経費）合計
     * @@param l_dblTradePriceFcTotal 売買代金（外貨）合計
     * @@param l_intDecimalPlace 通貨.小数部桁数
     * @@param l_feqFinTransactionParams トランザクション（取引勘定明細）行オブジェクトの配列
     * @@param l_feqProduct 外国株式銘柄
     * @@param l_datBaseDate 基準日
     * @@param l_amountTotals 各諸経費，受渡代金の合計の配列
     * 要素0 現地手数料合計<BR>
     * 要素1 現地取引税合計<BR>
     * 要素2 その他コスト１合計<BR>
     * 要素3 その他コスト2合計<BR>
     * 要素4 現地清算代金合計<BR>
     * 要素5 現地清算代金（円貨）合計<BR>
     * 要素6 委託手数料合計<BR>
     * 要素7 委託手数料（外貨）合計<BR>
     * 要素8 委託手数料消費税合計<BR>
     * 要素9 委託手数料消費税（外貨）合計<BR>
     * @@return WEB3FeqAmountCalcResult[]
     * @@exception WEB3BaseException
     */
    public WEB3FeqAmountCalcResult[] calcAllAmount(
        int l_intMax,
        String l_strMarketCode,
        double[] l_dblTradePrices,
        double[] l_dblTradePriceFcs,
        WEB3FeqAmountCalcResult l_calcResult,
        double l_dblTradePriceFcTotal,
        int l_intDecimalPlace,
        FeqFinTransactionParams[] l_feqFinTransactionParams,
        WEB3FeqProduct l_feqProduct,
        Date l_ExecDate,
        double[] l_amountTotals) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcAllAmount(int, String, double[], double[], " +
            "WEB3FeqAmountCalcResult, double, int, WEB3FeqCurrency, " +
            "FeqFinTransactionParams[], WEB3FeqProduct, Date, double[])";
        log.entering(STR_METHOD_NAME);

        if (l_feqFinTransactionParams == null ||
            l_feqFinTransactionParams.length == 0 ||
            l_feqProduct == null ||
            l_dblTradePrices == null ||
            l_dblTradePrices.length != l_feqFinTransactionParams.length ||
            l_dblTradePriceFcs == null ||
            l_dblTradePriceFcs.length != l_feqFinTransactionParams.length ||
            l_calcResult == null ||
            l_ExecDate == null ||
            l_amountTotals == null ||
            l_amountTotals.length != 10)             
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        int l_intSize = l_feqFinTransactionParams.length;

        WEB3FeqAmountCalcResult[] l_feqAmountCalcResultDetails = 
            new WEB3FeqAmountCalcResult[l_intSize];

        for (int i = 0; i < 10; i++)
        {
            l_amountTotals[i] = 0D;
        }
        
        BigDecimal l_bdTradePriceFcTotal = new BigDecimal(l_dblTradePriceFcTotal);
        for (int i = 0; i < l_intSize; i++)
        {
            if (i == l_intMax)
            {
                continue;
            }

            l_feqAmountCalcResultDetails[i] = new WEB3FeqAmountCalcResult();
            WEB3FeqForeignCost l_foreignCost = new WEB3FeqForeignCost();
            l_feqAmountCalcResultDetails[i].setForeignCost(l_foreignCost);
            
            BigDecimal l_bdScale =
                new BigDecimal(l_dblTradePriceFcs[i]).divide(l_bdTradePriceFcTotal, 10, BigDecimal.ROUND_HALF_EVEN);
            
            boolean l_blnIsBuy =
                FinTransactionType.EQTYPE_FEQ_BUY.equals(
                    l_feqFinTransactionParams[i].getFinTransactionType());
            double l_dbFxRate = l_feqFinTransactionParams[i].getFxRate();
            
            WEB3GentradeCurrency l_genCurrency = l_feqProduct.getCurrency();
            double l_dblFxRate = 
                l_genCurrency.getExchangeRate(l_blnIsBuy, true, l_dbFxRate);
            
            //⑤　@最大約定要素以外の要素について、［分割(n)］各諸経費，受渡代金を計算する
            //トランザクション（取引勘定明細）行の各要素について、
            //各諸経費(*1)を求める。最大約定要素（②で判定）は、この処理をスキップする。
            //［分割(n)］各諸経費(*1)計算式(*2)
            //［分割(n)］各諸経費＝［合　@計］各諸経費　@×　@［分割(n)］売買代金　@／　@［合　@計］売買代金
            // (*1)　@各諸経費
            //・現地手数料
            //・現地取引税
            //・その他コスト１
            //・その他コスト２
            //・委託手数料
            //・委託手数料（外貨）
            //・委託手数料消費税
            //・委託手数料消費税（外貨）
            //(*2)　@計算結果は有効桁数未満切捨。

            //現地手数料　@⇒海外諸経費マスタ.小数部桁数
            //外国株式計算サービス.get海外諸経費マスタ()にて取得する。
            String l_strSideDiv;
            if (l_blnIsBuy)
            {
                l_strSideDiv = WEB3BuySellTypeDef.BUY;
            }
            else
            {
                l_strSideDiv = WEB3BuySellTypeDef.SELL;
            }
            ForeignCostParams[] l_foreignCostParams = 
                this.getForeignCost(
                    new Long(l_feqProduct.getProductId()),
                    l_feqProduct.getOffshoreProductCode(),
                    l_feqProduct.getInstitution().getInstitutionCode(), 
                    l_strMarketCode, 
                    l_dblTradePriceFcs[i], 
                    l_ExecDate,
                    WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE,
                    l_strSideDiv);
            BigDecimal l_bdForignCommissionFee =
                new BigDecimal(l_calcResult.getForignCommissionFee()).multiply(l_bdScale);
            int l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdForignCommissionFee.toString());
            //最大約定要素（②で判定）以外の[分割（n）]現地手数料合計値（sum）を求める。
            if (l_foreignCostParams != null && l_foreignCostParams.length != 0)
            {
                int l_intScale = l_foreignCostParams[0].getScale();
                if (l_intDigits > l_intScale)
                {
                    l_bdForignCommissionFee = 
                        l_bdForignCommissionFee.setScale(l_intScale, BigDecimal.ROUND_DOWN);
                }
            }
            double l_dblForignCommissionFee = l_bdForignCommissionFee.doubleValue();
            l_amountTotals[0] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[0], l_dblForignCommissionFee);
            l_feqAmountCalcResultDetails[i].setForeignCommissionFee(l_dblForignCommissionFee);
            log.debug("現地手数料 = " + l_dblForignCommissionFee);

            //現地取引税　@⇒海外諸経費マスタ.小数部桁数
            l_foreignCostParams = 
                this.getForeignCost(
                    new Long(l_feqProduct.getProductId()),
                    l_feqProduct.getOffshoreProductCode(),
                    l_feqProduct.getInstitution().getInstitutionCode(), 
                    l_strMarketCode, 
                    l_dblTradePriceFcs[i], 
                    l_ExecDate,
                    WEB3FeqCostDivDef.FOREIGN_TAX,
                    l_strSideDiv);
            BigDecimal l_bdForeignTax =
                new BigDecimal(l_calcResult.getForeignTax()).multiply(l_bdScale);
            l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdForeignTax.toString());
            //最大約定要素（②で判定）以外の[分割（n）]現地取引税合計値（sum）を求める。
            if (l_foreignCostParams != null && l_foreignCostParams.length != 0)
            {
                int l_intScale = l_foreignCostParams[0].getScale();
                if (l_intDigits > l_intScale)
                {
                    l_bdForeignTax = 
                        l_bdForeignTax.setScale(l_intScale, BigDecimal.ROUND_DOWN);
                }
            }
            double l_dblForeignTax = l_bdForeignTax.doubleValue();
            l_amountTotals[1] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[1], l_dblForeignTax);
            l_feqAmountCalcResultDetails[i].setForeignTax(l_dblForeignTax);
            log.debug("現地取引税 = " + l_dblForeignTax);

            //その他コスト１　@⇒海外諸経費マスタ.小数部桁数
            l_foreignCostParams = 
                this.getForeignCost(
                    new Long(l_feqProduct.getProductId()),
                    l_feqProduct.getOffshoreProductCode(),
                    l_feqProduct.getInstitution().getInstitutionCode(), 
                    l_strMarketCode, 
                    l_dblTradePriceFcs[i], 
                    l_ExecDate,
                    WEB3FeqCostDivDef.FOREIGN_FEE_EXT1,
                    l_strSideDiv);
            BigDecimal l_bdForeignFeeExt1 = 
                new BigDecimal(l_calcResult.getForeignFeeExt1()).multiply(l_bdScale);
            l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdForeignFeeExt1.toString());
            //最大約定要素（②で判定）以外の[分割（n）]その他コスト１合計値（sum）を求める。
            if (l_foreignCostParams != null && l_foreignCostParams.length != 0)
            {
                int l_intScale = l_foreignCostParams[0].getScale();
                if (l_intDigits > l_intScale)
                {
                    l_bdForeignFeeExt1 = 
                        l_bdForeignFeeExt1.setScale(l_intScale, BigDecimal.ROUND_DOWN);
                }
            }
            double l_dblForeignFeeExt1 = l_bdForeignFeeExt1.doubleValue();
            l_amountTotals[2] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[2], l_dblForeignFeeExt1);
            l_feqAmountCalcResultDetails[i].setForeignFeeExt1(l_dblForeignFeeExt1);
            log.debug("その他コスト１ = " + l_dblForeignFeeExt1);
                
            //その他コスト２　@⇒海外諸経費マスタ.小数部桁数
            l_foreignCostParams = 
                this.getForeignCost(
                    new Long(l_feqProduct.getProductId()),
                    l_feqProduct.getOffshoreProductCode(),
                    l_feqProduct.getInstitution().getInstitutionCode(), 
                    l_strMarketCode,
                    l_dblTradePriceFcs[i],
                    l_ExecDate,
                    WEB3FeqCostDivDef.FOREIGN_FEE_EXT2,
                    l_strSideDiv);
            BigDecimal l_bdForeignFeeExt2 = 
                new BigDecimal(l_calcResult.getForeignFeeExt2()).multiply(l_bdScale);
            l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdForeignFeeExt2.toString());
            //最大約定要素（②で判定）以外の[分割（n）]その他コスト２合計値（sum）を求める。
            if (l_foreignCostParams != null && l_foreignCostParams.length != 0)
            {
                int l_intScale = l_foreignCostParams[0].getScale();
                if (l_intDigits > l_intScale)
                {
                    l_bdForeignFeeExt2 = 
                        l_bdForeignFeeExt2.setScale(l_intScale, BigDecimal.ROUND_DOWN);
                }
            }
            double l_dblForeignFeeExt2 = l_bdForeignFeeExt2.doubleValue();
            l_amountTotals[3] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[3], l_dblForeignFeeExt2);
            l_feqAmountCalcResultDetails[i].setForeignFeeExt2(l_dblForeignFeeExt2);
            log.debug("その他コスト２ = " + l_dblForeignFeeExt2);

            //現地清算代金
            double l_dblBalanceAmountFc = 0.0D;
            if (l_blnIsBuy)
            {
                l_dblBalanceAmountFc = new BigDecimal(String.valueOf(l_dblTradePriceFcs[i]))
                    .add(new BigDecimal(String.valueOf(l_dblForignCommissionFee)))
                    .add(new BigDecimal(String.valueOf(l_dblForeignTax)))
                    .add(new BigDecimal(String.valueOf(l_dblForeignFeeExt1)))
                    .add(new BigDecimal(String.valueOf(l_dblForeignFeeExt2))).doubleValue();
            }
            else
            {
                l_dblBalanceAmountFc = new BigDecimal(String.valueOf(l_dblTradePriceFcs[i]))
                    .subtract(new BigDecimal(String.valueOf(l_dblForignCommissionFee)))
                    .subtract(new BigDecimal(String.valueOf(l_dblForeignTax)))
                    .subtract(new BigDecimal(String.valueOf(l_dblForeignFeeExt1)))
                    .subtract(new BigDecimal(String.valueOf(l_dblForeignFeeExt2))).doubleValue();
            }
            l_amountTotals[4] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[4], l_dblBalanceAmountFc);
            l_feqAmountCalcResultDetails[i].setBalanceAmountFc(l_dblBalanceAmountFc);
            log.debug("現地清算代金 = " + l_dblBalanceAmountFc);

            //現地清算代金（円貨）
            double l_dblBalanceAmount =
                this.calcJPYAmount(
                    l_dblBalanceAmountFc,
                    l_dblFxRate,
                    WEB3FeqRoundDivDef.CUTOFF);
            l_amountTotals[5] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[5], l_dblBalanceAmount);
            l_feqAmountCalcResultDetails[i].setBalanceAmount(l_dblBalanceAmount);
            log.debug("現地清算代金（円貨） = " + l_dblBalanceAmount);

            //委託手数料
            BigDecimal l_bdCommissionFee = 
                new BigDecimal(l_calcResult.getCommissionFee()).multiply(l_bdScale);
            l_bdCommissionFee = 
                l_bdCommissionFee.setScale(0, BigDecimal.ROUND_DOWN);
            //最大約定要素（②で判定）以外の[分割（n）]委託手数料合計値（sum）を求める。
            double l_dblCommissionFee = l_bdCommissionFee.doubleValue();
            l_amountTotals[6] =
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[6], l_dblCommissionFee);
            l_feqAmountCalcResultDetails[i].setCommissionFee(l_dblCommissionFee);
            log.debug("委託手数料 = " + l_dblCommissionFee);

            //外貨換算丸め方式を取得する
            String l_strFCCYRoundDiv = l_genCurrency.getChangeFCcyRoundDiv();
            log.debug("外貨換算丸め方式 = " + l_strFCCYRoundDiv);

            //小数部桁数を取得する。
            int l_intScale = l_genCurrency.getScale();
            log.debug("小数部桁数 = " + l_intScale);

            //委託手数料（外貨）を計算する。 
            double l_dblForeignCCYAmount = 
                this.calcForeignCCYAmount(
                    l_dblCommissionFee, 
                    l_dblFxRate, 
                    l_intScale, 
                    l_strFCCYRoundDiv);

            BigDecimal l_bdCommissionFeeFc = new BigDecimal(l_dblForeignCCYAmount);
            l_intDigits = WEB3StringTypeUtility.getFractionDigits(l_bdCommissionFeeFc.toString());
            //最大約定要素（②で判定）以外の[分割（n）]委託手数料（外貨）合計値（sum）を求める。
            if (l_intDigits > l_intDecimalPlace)
            {
                l_bdCommissionFeeFc = 
                    l_bdCommissionFeeFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_DOWN);
            }
            double l_dblCommissionFeeFc = l_bdCommissionFeeFc.doubleValue();
            l_amountTotals[7] =
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[7], l_dblCommissionFeeFc);
            l_feqAmountCalcResultDetails[i].setCommissionFeeFc(l_dblCommissionFeeFc);
            log.debug("委託手数料（外貨） = " + l_dblCommissionFeeFc);

            //委託手数料消費税　@⇒小数部なし（円未満切捨て）
            BigDecimal l_bdCommissionFeeTax = 
                new BigDecimal(l_calcResult.getCommisionFeeTax()).multiply(l_bdScale);
            l_bdCommissionFeeTax = 
                l_bdCommissionFeeTax.setScale(0, BigDecimal.ROUND_DOWN);
            //最大約定要素（②で判定）以外の[分割（n）]委託手数料消費税合計値（sum）を求める。
            double l_dblCommissionFeeTax = l_bdCommissionFeeTax.doubleValue();
            l_amountTotals[8] =
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[8], l_dblCommissionFeeTax); 
            l_feqAmountCalcResultDetails[i].setCommissionFeeTax(l_dblCommissionFeeTax);
            log.debug("委託手数料消費税 = " + l_dblCommissionFeeTax);

            //委託手数料消費税（外貨）を計算する。 
            l_dblForeignCCYAmount = 
                this.calcForeignCCYAmount(
                    l_dblCommissionFeeTax, 
                    l_dblFxRate, 
                    l_intScale, 
                    l_strFCCYRoundDiv);

            BigDecimal l_bdCommissionFeeTaxFc = new BigDecimal(l_dblForeignCCYAmount);
            l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdCommissionFeeTaxFc.toString());
            //最大約定要素（②で判定）以外の[分割（n）]委託手数料消費税（外貨）合計値（sum）を求める。
            if (l_intDigits > l_intDecimalPlace)
            {
                l_bdCommissionFeeTaxFc = 
                    l_bdCommissionFeeTaxFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_DOWN);
            }
            double l_dblCommissionFeeTaxFc = l_bdCommissionFeeTaxFc.doubleValue();
            l_amountTotals[9] =
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[9], l_dblCommissionFeeTaxFc);
            l_feqAmountCalcResultDetails[i].setCommissionFeeTaxFc(l_dblCommissionFeeTaxFc);
            log.debug("委託手数料消費税（外貨） = " + l_dblCommissionFeeTaxFc);

            //［分割(n)］売買代金
            l_feqAmountCalcResultDetails[i].setTradePrice(l_dblTradePrices[i]);
            log.debug("売買代金 = " + l_dblTradePrices[i]);
            //［分割(n)］売買代金（外貨）
            l_feqAmountCalcResultDetails[i].setTradePriceFc(l_dblTradePriceFcs[i]);
            log.debug("売買代金（外貨） = " + l_dblTradePriceFcs[i]);

            //［分割(n)］受渡代金＝［分割(n)］売買代金±(*3)　@（［分割(n)］委託手数料　@＋［分割(n)］委託手数料消費税）
            //(*2)　@計算結果は有効桁数未満切捨。
            //(*3) 買付の場合＋、売付の場合－
            //［分割(n)］受渡代金（外貨）＝［分割(n)］売買代金（外貨）±(*3)（［分割(n)］委託手数料（外貨）＋［分割(n)］委託手数料消費税（外貨））
            double l_dblNetAmount = 0.0D;
            double l_dblNetAmountFc = 0.0D;
            BigDecimal l_bdBalanceAmountFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[i].getBalanceAmountFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            l_bdCommissionFeeFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[i].getCommissionFeeFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            l_bdCommissionFeeTaxFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[i].getCommisionFeeTaxFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            if (l_blnIsBuy)
            {
                //受渡代金
                l_dblNetAmount =
                    l_feqAmountCalcResultDetails[i].getBalanceAmount() + 
                    l_feqAmountCalcResultDetails[i].getCommissionFee() +
                    l_feqAmountCalcResultDetails[i].getCommisionFeeTax();

                //受渡代金（外貨）
                l_bdBalanceAmountFc = l_bdCommissionFeeFc.add(l_bdBalanceAmountFc);
                l_bdBalanceAmountFc = l_bdCommissionFeeTaxFc.add(l_bdBalanceAmountFc);
                l_intDigits = WEB3StringTypeUtility.getFractionDigits(l_bdBalanceAmountFc.toString());
                if (l_intDigits > l_intDecimalPlace)
                {
                    l_bdBalanceAmountFc = 
                        l_bdBalanceAmountFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_DOWN);
                }
                l_dblNetAmountFc = l_bdBalanceAmountFc.doubleValue();
            }
            else
            {
                //受渡代金
                l_dblNetAmount =
                    l_feqAmountCalcResultDetails[i].getBalanceAmount() - 
                    (l_feqAmountCalcResultDetails[i].getCommissionFee() +
                    l_feqAmountCalcResultDetails[i].getCommisionFeeTax());
                //受渡代金（外貨）
                l_bdCommissionFeeFc = l_bdCommissionFeeFc.add(l_bdCommissionFeeTaxFc);
                l_bdBalanceAmountFc = l_bdBalanceAmountFc.subtract(l_bdCommissionFeeFc);
                l_intDigits = WEB3StringTypeUtility.getFractionDigits(l_bdBalanceAmountFc.toString());
                if (l_intDigits > l_intDecimalPlace)
                {
                    l_bdBalanceAmountFc = 
                         l_bdBalanceAmountFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_DOWN);
                }
                l_dblNetAmountFc = l_bdBalanceAmountFc.doubleValue();
            }

            l_dblNetAmount =
                WEB3FeqOrderUtility.getCutOutValue(
                    l_dblNetAmount,
                    0,
                    WEB3FeqOrderUtility.CUTOFF);

            l_intDigits = WEB3StringTypeUtility.getFractionDigits(new Double(l_dblNetAmountFc).toString());
            if (l_intDigits > l_intDecimalPlace)
            {
                l_dblNetAmountFc =
                    WEB3FeqOrderUtility.getCutOutValue(
                        l_dblNetAmountFc,
                        l_intDecimalPlace,
                        WEB3FeqOrderUtility.CUTOFF);
            }
            l_feqAmountCalcResultDetails[i].setNetAmount(l_dblNetAmount);
            l_feqAmountCalcResultDetails[i].setNetAmountFc(l_dblNetAmountFc);
            log.debug("受渡代金 = " + l_dblNetAmount);
            log.debug("受渡代金（外貨） = " + l_dblNetAmountFc);
        }

        log.exiting(STR_METHOD_NAME);
        return l_feqAmountCalcResultDetails;
    }

    /**
     * (calc外国株式金額（按分）) <BR>
     * 外国株式金額按分計算を行う。 <BR>
     *  <BR>
     * (*) 計算の詳細は、「基本設計計算式書（外国株式）.doc  <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@１.手数料按分計算（一口約定）」参照。 <BR>
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) 
     * トランザクション（取引勘定明細）行オブジェクトの配列
     * @@return webbroker3.feq.WEB3FeqAmountCalcResultFactor
     * @@throws WEB3BaseException
     * @@roseuid 4289CA2A01E4
     */
    public WEB3FeqAmountCalcResultFactor calcFeqAmountFactor(
        FeqFinTransactionParams[] l_feqFinTransactionParams)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcFeqAmountFactor(FeqFinTransactionParams[])";
        log.entering(STR_METHOD_NAME);

        if (l_feqFinTransactionParams == null || l_feqFinTransactionParams.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

        int l_intSize = l_feqFinTransactionParams.length;

        try
        {
            //外国株式銘柄：　@トランザクション（取引勘定明細）行.getProductId()に該当する外国株式銘柄
            WEB3FeqProduct l_feqProduct = 
                (WEB3FeqProduct)l_productManager.getProduct(l_feqFinTransactionParams[0].getProductId());
            WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
            WEB3FeqAmountCalcResult[] l_feqAmountCalcResultDetails = 
                new WEB3FeqAmountCalcResult[l_intSize];
            
            double[] l_dblTradePrices = new double[l_intSize];
            double[] l_dblTradePriceFcs = new double[l_intSize];
            double[] l_dblTotals = new double[2];
             //①@　@[分割（n）]売買代金を計算する
            //引数.トランザクション（取引勘定明細）行[]の各要素について、
            //売買代金（*1）を計算し[分割（n）]売買代金とする。
            //[分割（n）]のnは、配列のindex。
            //(*1)　@売買代金計算式
            //　@トランザクション（取引勘定明細）行.約定単価 × トランザクション（取引勘定明細）行.約定数量
            //②　@最大約定要素の判定
            //③　@[合　@計]売買代金を計算する
            int l_intMax = 
                calcMaxExecIndex(
                    l_feqFinTransactionParams,
                    l_currency,
                    l_dblTradePrices,
                    l_dblTradePriceFcs,
                    l_dblTotals);

            double l_dblTradePriceFcTotal = l_dblTotals[1];

            //市場：　@トランザクション（取引勘定明細）行.getMarketId()に該当する市場
            WEB3GentradeMarket l_market = 
                (WEB3GentradeMarket)l_finObjectManager.getMarket(l_feqFinTransactionParams[0].getMarketId());
            
            //基準日：　@約定(*).約定日時の日付部分（※時間部分は00:00:00）
            //(*)トランザクション（取引勘定明細）行.getOrderExecutionId()に該当する約定。
            //該当データなしの場合は、トランザクション(取引勘定明細)行.トランザクション発生日時をセット。
            Date l_ExecDate = 
                GtlUtils.truncateDate(l_feqFinTransactionParams[0].getFinTransactionTimestamp());
            OrderExecution l_orderExecution = null;
            Date l_datExecutionDate = null;
            try
            {
                l_orderExecution = 
                    l_orderManager.getOrderExecution(l_feqFinTransactionParams[0].getOrderExecutionId());
                l_datExecutionDate = l_orderExecution.getExecutionTimestamp();
                if (l_orderExecution == null)
                {
                    l_ExecDate = GtlUtils.truncateDate(l_datExecutionDate);
                }
            }
            catch (NotFoundException e)
            {
                log.debug("約定レコードを取得しない");                
            }
            Date l_datBaseDate = WEB3DateUtility.getDate(l_feqFinTransactionParams[0].getBizDate(),"yyyyMMdd");
            
            //④　@［合　@ 計］外国株式金額（諸経費）を計算する
            WEB3FeqAmountCalcResult l_calcResult = 
                calcFeqAmountTotal(
                    l_feqFinTransactionParams[0],
                    l_feqProduct,
                    l_orderManager,
                    l_accountManager,
                    l_market,
                    l_datBaseDate,
                    l_ExecDate,
                    l_dblTradePriceFcTotal); 
                
            double[] l_amountTotals = new double[10];
            int l_intDecimalPlace = l_currency.getScale();
            
            //⑤　@最大約定要素以外の要素について、［分割(n)］各諸経費，受渡代金を計算する
            //⑥　@最大約定要素以外の［分割(n)］各諸経費について、合計値を計算する             
            l_feqAmountCalcResultDetails = calcAllAmount(
                l_intMax,
                l_market.getMarketCode(),
                l_dblTradePrices,
                l_dblTradePriceFcs,
                l_calcResult,
                l_dblTradePriceFcTotal,
                l_intDecimalPlace,
                l_feqFinTransactionParams,
                l_feqProduct,
                l_ExecDate,
                l_amountTotals);

            //⑦ 最大約定要素（②で判定）の[分割（n）]各諸経費，受渡代金を計算する
            //端数を寄せるため、次の計算式で最大約定の委託手数料、消費税を計算する。
            //[分割(n)]各諸経費 ＝　@[合　@ 計]各諸経費（⑤で計算）　@－　@各諸経費の合計（⑦で計算）
            l_feqAmountCalcResultDetails[l_intMax] = 
                new WEB3FeqAmountCalcResult();
            WEB3FeqForeignCost l_foreignCost = new WEB3FeqForeignCost();
            l_feqAmountCalcResultDetails[l_intMax].setForeignCost(l_foreignCost);

            //現地手数料合計
            l_feqAmountCalcResultDetails[l_intMax].setForeignCommissionFee(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getForignCommissionFee(),
                    l_amountTotals[0]));
            log.debug("最大約定要素の現地手数料合計 = " + 
                l_feqAmountCalcResultDetails[l_intMax].getForignCommissionFee());
            //現地取引税
            l_feqAmountCalcResultDetails[l_intMax].setForeignTax(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getForeignTax(),
                    l_amountTotals[1]));
            log.debug("最大約定要素の現地取引税 = " + 
                l_feqAmountCalcResultDetails[l_intMax].getForeignTax());
            //その他コスト1
            l_feqAmountCalcResultDetails[l_intMax].setForeignFeeExt1(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getForeignFeeExt1(),
                    l_amountTotals[2]));
            log.debug("最大約定要素のその他コスト1 = " + 
                l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt1());
            //その他コスト2
            l_feqAmountCalcResultDetails[l_intMax].setForeignFeeExt2(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getForeignFeeExt2(),
                    l_amountTotals[3]));
            log.debug("最大約定要素のその他コスト2 = " + 
                l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt2());
            //現地清算代金
            double l_dblMaxBalanceAmountFc = 0.0D;
            boolean l_blnIsBuy =
                FinTransactionType.EQTYPE_FEQ_BUY.equals(
                    l_feqFinTransactionParams[l_intMax].getFinTransactionType());
            if (l_blnIsBuy)
            {
                l_dblMaxBalanceAmountFc =
                      new BigDecimal(String.valueOf(l_dblTradePriceFcs[l_intMax]))
                      .add(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForignCommissionFee())))
                      .add(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignTax())))
                      .add(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt1())))
                      .add(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt2()))).doubleValue();
            }
            else
            {
                l_dblMaxBalanceAmountFc =
                      new BigDecimal(String.valueOf(l_dblTradePriceFcs[l_intMax]))
                      .subtract(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForignCommissionFee())))
                      .subtract(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignTax())))
                      .subtract(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt1())))
                      .subtract(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt2()))).doubleValue();
            }
            l_feqAmountCalcResultDetails[l_intMax].setBalanceAmountFc(l_dblMaxBalanceAmountFc);
            log.debug("最大約定要素の現地清算代金 = " + 
                l_feqAmountCalcResultDetails[l_intMax].getBalanceAmountFc());
            //現地清算代金（円貨）
            WEB3GentradeCurrency l_genCurrency = l_feqProduct.getCurrency();

            double l_dblTransactionFxRate = l_feqFinTransactionParams[0].getFxRate();
            double l_dblFxRate = 
                l_genCurrency.getExchangeRate(l_blnIsBuy, true, l_dblTransactionFxRate);
            double l_dblTotalBalanceAmountFc =
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[4], l_dblMaxBalanceAmountFc);
            double l_dblTotalBalanceAmount =
                this.calcJPYAmount(
                    l_dblTotalBalanceAmountFc,
                    l_dblFxRate,
                    WEB3FeqRoundDivDef.CUTOFF);
            l_feqAmountCalcResultDetails[l_intMax].setBalanceAmount(
                WEB3FeqOrderUtility.decimalMinus(l_dblTotalBalanceAmount,
                    l_amountTotals[5]));
            log.debug("最大約定要素の現地清算代金（円貨） = " + 
                l_feqAmountCalcResultDetails[l_intMax].getBalanceAmount());
            //委託手数料
            l_feqAmountCalcResultDetails[l_intMax].setCommissionFee(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getCommissionFee(),
                    l_amountTotals[6]));
            log.debug("最大約定要素の委託手数料 = " + 
                l_feqAmountCalcResultDetails[l_intMax].getCommissionFee());

            //外貨換算丸め方式を取得する
            String l_strFCCYRoundDiv = l_genCurrency.getChangeFCcyRoundDiv();
            log.debug("外貨換算丸め方式 = " + l_strFCCYRoundDiv);

            //小数部桁数を取得する。
            int l_intScale = l_genCurrency.getScale();
            log.debug("小数部桁数 = " + l_intScale);

            //委託手数料（外貨）を換算する。 
            double l_dblForeignCCYAmount = 
                this.calcForeignCCYAmount(
                    l_feqAmountCalcResultDetails[l_intMax].getCommissionFee(), 
                    l_dblFxRate, 
                    l_intScale, 
                    l_strFCCYRoundDiv);
            //委託手数料（外貨）
            l_feqAmountCalcResultDetails[l_intMax].setCommissionFeeFc(l_dblForeignCCYAmount);
            log.debug("最大約定要素の委託手数料（外貨） = " + 
                l_feqAmountCalcResultDetails[l_intMax].getCommissionFeeFc());
            //委託手数料消費税
            l_feqAmountCalcResultDetails[l_intMax].setCommissionFeeTax(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getCommisionFeeTax(),
                    l_amountTotals[8]));
            log.debug("最大約定要素の委託手数料消費税 = " + 
                l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTax());

            //委託手数料消費税を外貨に換算する。 
            l_dblForeignCCYAmount = 
                this.calcForeignCCYAmount(
                    l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTax(), 
                    l_dblFxRate, 
                    l_intScale, 
                    l_strFCCYRoundDiv);
            //委託手数料消費税（外貨）
            l_feqAmountCalcResultDetails[l_intMax].setCommissionFeeTaxFc(l_dblForeignCCYAmount);
            log.debug("最大約定要素の委託手数料消費税（外貨） = " + 
                l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTaxFc());
            //売買代金
            l_feqAmountCalcResultDetails[l_intMax].setTradePrice(l_dblTradePrices[l_intMax]);
            log.debug("最大約定要素の売買代金 = " + l_dblTradePrices[l_intMax]);
            //売買代金（外貨）
            l_feqAmountCalcResultDetails[l_intMax].setTradePriceFc(l_dblTradePriceFcs[l_intMax]);
            log.debug("最大約定要素の売買代金（外貨） = " + l_dblTradePriceFcs[l_intMax]);

            //［分割(n)］受渡代金＝［分割(n)］売買代金±(*3)　@（［分割(n)］委託手数料　@＋［分割(n)］委託手数料消費税）
            //(*2)　@計算結果は有効桁数未満切捨。
            //(*3) 買付の場合＋、売付の場合－
            //［分割(n)］受渡代金（外貨）＝［分割(n)］売買代金（外貨）±(*3)（［分割(n)］委託手数料（外貨）＋［分割(n)］委託手数料消費税（外貨））
            //(*2)　@計算結果は有効桁数未満切捨。
            //(*3) 買付の場合＋、売付の場合－
            double l_dblNetAmount = 0.0D;
            double l_dblNetAmountFc = 0.0D;
            BigDecimal l_bdBalanceAmountFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[l_intMax].getBalanceAmountFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            BigDecimal l_bdCommissionFeeFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[l_intMax].getCommissionFeeFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            BigDecimal l_bdCommissionFeeTaxFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTaxFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            if (l_blnIsBuy)
            {
                //受渡代金
                l_dblNetAmount =
                    l_feqAmountCalcResultDetails[l_intMax].getBalanceAmount() + 
                    l_feqAmountCalcResultDetails[l_intMax].getCommissionFee() +
                    l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTax();
                //受渡代金（外貨）
                l_bdBalanceAmountFc = l_bdCommissionFeeFc.add(l_bdBalanceAmountFc);
                l_bdBalanceAmountFc = l_bdCommissionFeeTaxFc.add(l_bdBalanceAmountFc);
            }
            else
            {
                //受渡代金
                l_dblNetAmount =
                    l_feqAmountCalcResultDetails[l_intMax].getBalanceAmount() - 
                    (l_feqAmountCalcResultDetails[l_intMax].getCommissionFee() +
                    l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTax());
                //受渡代金（外貨）
                l_bdCommissionFeeFc = l_bdCommissionFeeFc.add(l_bdCommissionFeeTaxFc);
                l_bdBalanceAmountFc = l_bdBalanceAmountFc.subtract(l_bdCommissionFeeFc);
            }
            
            BigDecimal l_bdNetAmount = new BigDecimal(l_dblNetAmount);
            l_bdNetAmount = l_bdNetAmount.setScale(0, BigDecimal.ROUND_HALF_EVEN);
            l_dblNetAmount = l_bdNetAmount.doubleValue();

            int l_intDigits =
                WEB3StringTypeUtility.getFractionDigits(
                    new Double(
                        l_feqAmountCalcResultDetails[l_intMax].getBalanceAmountFc()).toString());
            if (l_intDigits > l_intDecimalPlace)
            {
                l_bdBalanceAmountFc =
                l_bdBalanceAmountFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_DOWN);
            }
            else
            {
                l_bdBalanceAmountFc =
                l_bdBalanceAmountFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            }
            l_dblNetAmountFc = l_bdBalanceAmountFc.doubleValue();
            l_feqAmountCalcResultDetails[l_intMax].setNetAmount(l_dblNetAmount);
            l_feqAmountCalcResultDetails[l_intMax].setNetAmountFc(l_dblNetAmountFc);
            log.debug("最大約定要素の受渡代金 = " + 
                l_feqAmountCalcResultDetails[l_intMax].getNetAmount());
            log.debug("最大約定要素の受渡代金（外貨） = " + 
                l_feqAmountCalcResultDetails[l_intMax].getNetAmountFc());

            WEB3FeqAmountCalcResultFactor l_resultFactor = 
                new WEB3FeqAmountCalcResultFactor(l_feqAmountCalcResultDetails, l_calcResult);
            log.exiting(STR_METHOD_NAME);
            return l_resultFactor;
        }
        catch (NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + 
                STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("DBへのアクセスに失敗しました: ", l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + 
                STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DBへのアクセスに失敗しました: ", l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + 
                STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
    }

    /**
     * (calc簿価単価) <BR>
     * 簿価単価を計算する。 <BR>
     *  <BR>
     * １）保有資産オブジェクトを取得する。 <BR>
     *  <BR>
     *    外国株式ポジションマネージャ.get保有資産()をコールする。 <BR>
     *  <BR>
     *    [引数] <BR>
     *    口座ID： 引数.補助口座.口座ID <BR>
     *    補助口座ID： 引数.補助口座.補助口座ID <BR>
     *    銘柄ID： 引数.銘柄ID <BR>
     *    税区分： 引数.税区分 <BR>
     *  <BR>
     *    ※該当データなしの場合は、例外をスローする。 <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00204 <BR>
     *  <BR>
     * ２）簿価単価を求め、計算結果を返却する。 <BR>
     *  <BR>
     *    簿価単価＝保有資産.簿価（簿価単価計算用） ÷  <BR>
     * 　@　@　@　@　@　@　@　@　@保有資産.数量（簿価単価計算用） <BR>
     *  <BR>
     *    ※計算結果を、引数の「円未満有効桁数」までの桁数に、 <BR>
     * 　@　@　@四捨五入により丸める。 <BR>
     * @@param l_subAccount - (補助口座) <BR>
     * 補助口座オブジェクト
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_taxType - (税区分)
     * @@param l_lngValidGirder - (円未満有効桁数)
     * @@return BigDecimal
     * @@roseuid 4294634F0203
     */
    public BigDecimal calcBookValuePrice(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngProductId,
        TaxTypeEnum l_taxType,
        long l_lngValidGirder) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcBookValuePrice(WEB3GentradeSubAccount, long, TaxTypeEnum, long)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lngValidGirder < 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "引数の「円未満有効桁数」 = " + l_lngValidGirder);
        }
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）保有資産オブジェクトを取得する。
        //    外国株式ポジションマネージャ.get保有資産()をコールする。
        //    [引数] 
        //    口座ID： 引数.補助口座.口座ID 
        //    補助口座ID： 引数.補助口座.補助口座ID 
        //    銘柄ID： 引数.銘柄ID 
        //    税区分： 引数.税区分 
        //    ※該当データなしの場合は、例外をスローする。 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_positionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
        
        // get 保有資産
        FeqAsset l_asset =
            (FeqAsset) l_positionManager.getAsset(
                l_subAccount.getAccountId(),
                l_subAccount.getSubAccountId(),
                l_lngProductId,
                l_taxType);
        
        if (l_asset == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass() + "." + STR_METHOD_NAME,
                "保有資産テーブルに該当するデータがありません");
        }
        //簿価単価を求め、計算結果を返却する。
        AssetRow l_assetRow =
            (AssetRow)l_asset.getDataSourceObject();

        //get 保有資産.数量（簿価単価計算用）
        double l_dblTotalQuantity = l_assetRow.getQuantityForBookValue();
        BigDecimal l_bdTotalQutity =
            new BigDecimal(String.valueOf(l_dblTotalQuantity));
        if (l_dblTotalQuantity == 0.0D)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass() + "." + STR_METHOD_NAME,
                "保有資産テーブル.数量（簿価単価計算用）に0が設定されています");
        }
        //get 保有資産.簿価（簿価単価計算用）
        double l_dbBookValue = l_assetRow.getBookValue();
        BigDecimal l_bdBookValue =
            new BigDecimal(String.valueOf(l_dbBookValue));

        //簿価単価＝保有資産.簿価÷保有資産.数量
        //計算結果を、引数の「円未満有効桁数」までの桁数に、四捨五入により丸める。
        BigDecimal l_bdBookPrice =
            l_bdBookValue.divide(
                l_bdTotalQutity,
                (int)l_lngValidGirder,
                BigDecimal.ROUND_HALF_UP);

        log.debug("保有資産.簿価：[" + l_dbBookValue + "]");
        log.debug("保有資産.数量：[" + l_dblTotalQuantity + "]");

        log.exiting(STR_METHOD_NAME);
        return l_bdBookPrice;
    }

    /**
     * (calc概算簿価単価) <BR>
     * 概算簿価単価を計算する。 <BR>
     *  <BR>
     * this.calc簿価単価()をコールする。 <BR>
     *  <BR>
     * [引数] <BR>
     * 補助口座： 引数.補助口座 <BR>
     * 銘柄ID： 引数.銘柄ID <BR>
     * 税区分： 引数.税区分 <BR>
     * 円未満有効桁数： 5 <BR>
     * @@param l_subAccount - (補助口座) <BR>
     * 補助口座オブジェクト
     * 
     * @@param l_lngProductId - (銘柄ID)
     * 
     * @@param l_taxType - (税区分)
     * 
     * @@return BigDecimal
     * @@roseuid 4294650403E7
     */
    public BigDecimal calcEstimatedBookValuePrice(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngProductId,
        TaxTypeEnum l_taxType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcEstimatedBookValuePrice(WEB3GentradeSubAccount, long, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        BigDecimal l_dbEstimatedBookPrice =
            this.calcBookValuePrice(l_subAccount, l_lngProductId, l_taxType, 5);
        
        log.exiting(STR_METHOD_NAME);
        return l_dbEstimatedBookPrice;
    }

    /**
     * (calc概算簿価単価) <BR>
     * 引数の値より概算簿価単価を計算する。 <BR>
     *  <BR>
     * パラメータ.簿価 ÷ パラメータ.数量を返却する。 <BR>
     * ※計算結果は、丸め処理(小数点19位を四捨五入)する。<BR>
     * @@param l_dblBookValue - (簿価)
     * @@param l_dblQuantity - (数量)
     * @@return BigDecimal
     * @@roseuid 42AD1D3B031B
     */
    public BigDecimal calcEstimatedBookValuePrice(
        double l_dblBookValue, 
        double l_dblQuantity)
    {
        String STR_METHOD_NAME = "calcEstimatedBookValuePrice(double, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_dblQuantity < 0)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "株数 = " + l_dblQuantity);
        }

        if (l_dblQuantity == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return new BigDecimal("0");
        }

        BigDecimal l_bdBookValue = new BigDecimal(String.valueOf(l_dblBookValue));
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));

        //小数点19位を四捨五入
        BigDecimal l_bdResult =
            l_bdBookValue.divide(l_bdQuantity, 18, BigDecimal.ROUND_HALF_UP);

        log.exiting(STR_METHOD_NAME);
        return l_bdResult;
    }

    /**
     * (calc訂正外国株式金額) <BR>
     * 訂正後の外国株式金額計算を行う。 <BR>
     *  <BR>
     * 　@・現地手数料 <BR>
     * 　@・現地取引税 <BR>
     * 　@・その他コスト１ <BR>
     * 　@・その他コスト２ <BR>
     * 　@・現地清算代金 <BR>
     * 　@・現地清算代金（円貨） <BR>
     * 　@・委託手数料 <BR>
     * 　@・委託手数料（外貨） <BR>
     * 　@・委託手数料消費税 <BR>
     * 　@・委託手数料消費税（外貨） <BR>
     * 　@・受渡代金 <BR>
     * 　@・受渡代金（外貨） <BR>
     *  <BR>
     * １）　@発注日取得 <BR>
     * 　@基準日が未指定（null）の場合、取引時間管理.get発注日() <BR>
     *   にて発注日を取得し、基準日とする。 <BR>
     *  <BR>
     * ２）　@手数料オブジェクトを生成し、プロパティに以下の通りセットを行う。  <BR>
     *  <BR>
     * 　@証券会社コード： 補助口座.getInstitution().getInstitutionCode() <BR>
     * 　@部店ID： 補助口座.get取引店().BranchId() <BR>
     * 　@手数料商品コード： this.get手数料商品コード() <BR>
     * 　@取引コード（SONAR）：　@this.get取引コード（SONAR）() <BR>
     * 　@発注日：　@発注日 <BR>
     * 　@弁済区分：　@弁済区分.00：その他  <BR>
     * 　@注文チャネル： 注文チャネル <BR>
     * 　@is指値： is指値注文 <BR>
     * 　@市場コード（SONAR）：　@市場.市場コード（SONAR） <BR>
     * 　@原注文注文チャネル：　@注文単位.初回注文の注文チャネル <BR>
     * 　@原注文手数料No：　@注文単位.初回注文の手数料No <BR>
     * 　@原注文手数料No枝番：　@注文単位.初回注文の手数料No枝番 <BR>
     *  <BR>
     * ３）　@売買代金（外貨）に約定分を加算 <BR>
     *  <BR>
     * 　@　@売買代金（外貨）： <BR>
     *     引数.売買代金（外貨） + 注文単位.合計約定金額 <BR>
     *  <BR>
     * ４）　@外国株式金額計算 <BR>
     * 　@this.calc外国株式金額(手数料, 補助口座, 外国株式銘柄, 市場,  <BR>
     * 　@基準日, 売買代金（外貨）(*), 為替レート, is買付, is約定計算, is指値)に <BR>
     * 　@委譲（deligate）する。 <BR>
     *  <BR>
     *   (*)３）で算出したもの <BR>
     *  <BR>
     * ５）　@外国株式金額計算結果オブジェクト返却 <BR>
     * 　@外国株式金額計算結果オブジェクトを返却する。 <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_feqProduct - (外国株式銘柄)
     * @@param l_market - (市場)
     * @@param l_datBaseDate - (基準日)
     * @@param l_dblTradePriceFc - (売買代金（外貨）)
     * @@param l_dblFxRate - (為替レート) 
     * （※ 0指定の場合は、通貨テーブルの為替レート）
     * @@param l_blnIsBuy - (is買付) 
     * 買付かの判定 <BR>
     *  <BR>
     * true：買 <BR>
     * false：売 <BR>
     * @@param l_blnIsExecCalc - (is約定計算)
     * 約定計算かの判定 <BR>
     *  <BR>
     * true：約定計算 <BR>
     * false：概算計算 <BR>
     * @@param l_blnIsLimitPrice - (is指値) 
     * 指値かの判定 <BR>
     *  <BR>
     * true：指値 <BR>
     * false：成行 <BR>
     * @@param l_feqOrderUnit - (注文単位) 
     * 注文単位オブジェクト
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 42A538FE03A8
     */
    public WEB3FeqAmountCalcResult calcChangeFeqAmount(
        WEB3GentradeSubAccount l_subAccount,
        WEB3FeqProduct l_feqProduct,
        WEB3GentradeMarket l_market,
        Date l_datBaseDate,
        double l_dblTradePriceFc,
        double l_dblFxRate,
        boolean l_blnIsBuy,
        boolean l_blnIsExecCalc,
        boolean l_blnIsLimitPrice,
        FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcChangeFeqAmount(WEB3GentradeSubAccount, WEB3FeqProduct, " +
            "WEB3GentradeMarket, Date, double, double, boolean, boolean, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_feqProduct == null || 
            l_market == null || l_feqOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）発注日取得 
        //基準日が未指定（null）の場合、 
        //取引時間管理.get発注日()にて発注日を取得し、基準日とする。 
        Date l_datOrderBizDate = l_datBaseDate;
        if (l_datBaseDate == null)
        {
            l_datOrderBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        
        //２）手数料オブジェクトを生成し、プロパティに以下の通りセットを行う。

        WEB3GentradeCommission l_gentradeCommission = 
            new WEB3GentradeCommission();

        //証券会社コード： 補助口座.getInstitution().getInstitutionCode()
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //部店ID： 補助口座.get取引店().BranchId()
        l_gentradeCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());

        //手数料商品コード： this.get手数料商品コード()
        l_gentradeCommission.setCommissionProductCode(this.getCommissionProductCode());

        //取引コード（SONAR）：　@this.get取引コード（SONAR）(is買付)
        l_gentradeCommission.setSonarTradedCode(this.getSonarTradedCode(l_blnIsBuy));

        //発注日：　@発注日
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));

        //弁済区分：　@弁済区分.00：その他 
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
        
        FeqOrderUnitRow l_orderUnitRow = 
            (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        
        //注文チャネル： 注文チャネル
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
            OpLoginSecurityService.class);
        String l_strorderChannel = WEB3SessionAttributeDef.ORDER_CHANNEL;
        l_gentradeCommission.setOrderChannel(l_opLoginSec.getSessionProperty(l_strorderChannel));
        
        //is指値： is指値注文
        l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);

        //市場コード（SONAR）：　@市場.市場コード（SONAR） 
        MarketRow l_marketRow = (MarketRow) (l_market.getDataSourceObject());       
        l_gentradeCommission.setSonarMarketCode(l_marketRow.getSonarMarketCode());
        
        //原注文注文チャネル：　@注文単位.初回注文の注文チャネル
        l_gentradeCommission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());
        
        //原注文手数料No：　@注文単位.初回注文の手数料No 
        l_gentradeCommission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
        
        //原注文手数料No枝番：　@注文単位.初回注文の手数料No枝番 
        l_gentradeCommission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
        
        //３）売買代金（外貨）に約定分を加算
        // 売買代金（外貨）：
        // 引数.売買代金（外貨） + 注文単位.合計約定金額 
        double l_dblTolTradePriceFc = 
            WEB3FeqOrderUtility.decimalPlus(l_dblTradePriceFc, l_orderUnitRow.getExecutedAmount()); 
        
        //４）外国株式金額計算 
        //this.calc外国株式金額(手数料, 補助口座, 外国株式銘柄, 市場, 
        //基準日, 売買代金（外貨）(*), 為替レート, is買付, is約定計算, is指値)に
        //委譲（deligate）する。
        //(*)３）で算出したもの 
        WEB3FeqAmountCalcResult l_calcResult = 
            this.calcFeqAmount(
                l_gentradeCommission,
                l_subAccount,
                l_feqProduct,
                l_market,
                l_datOrderBizDate,
                l_dblTolTradePriceFc,
                l_dblFxRate,
                l_blnIsBuy,
                l_blnIsExecCalc,
                l_blnIsLimitPrice);
        
        //５）　@外国株式金額計算結果オブジェクト返却 
        //外国株式金額計算結果オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }

    /**
     * (calc外貨換算)<BR>
     * 指定金額を、指定銘柄に該当する通貨レートで外貨換算を行う。<BR>
     * <BR>
     * this.calc外貨換算 (double 金額（円貨）, double レート, int 小数部桁数, String 外貨換算丸め方式)にdelegateする。
     * @@param l_dblAmount - 金額（円貨）
     * @@param l_dblRate - 為替レート<BR>
     * ※通貨テーブルの為替レートを使用する場合は0を指定する。
     * @@param l_lngProductId - 銘柄ID
     * @@param l_blnIsBuy - is買付<BR>
     * 買付かの判定<BR>
     * <BR>
     * true：買<BR>
     * false：売<BR>
     * @@param l_blnIsExecCalc - is約定計算<BR>
     * 約定計算かの判定<BR>
     * <BR>
     * true：約定計算<BR>
     * false：概算計算<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcForeignCCYAmount(
        double l_dblAmount,
        double l_dblRate,
        long l_lngProductId,
        boolean l_blnIsBuy,
        boolean l_blnIsExecCalc)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcForeignCCYAmount(double, double, long, boolean, boolean)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqProductManager l_productManager =
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        WEB3FeqProduct l_product = l_productManager.getFeqProduct(l_lngProductId);
        WEB3GentradeCurrency l_genCurrency = l_product.getCurrency();
        double l_dblFxRate = 
            l_genCurrency.getExchangeRate(l_blnIsBuy, l_blnIsExecCalc, l_dblRate);
        log.debug("為替レート = " + l_dblFxRate);
        int l_intScale = l_genCurrency.getScale();
        log.debug("小数部桁数 = " + l_intScale);
        String l_dblFCCYRoundDiv = l_genCurrency.getChangeFCcyRoundDiv();
        log.debug("外貨換算丸め方式 = " + l_dblFCCYRoundDiv);
        double l_dblForeignCCYAmount = 
            this.calcForeignCCYAmount(l_dblAmount, l_dblFxRate, l_intScale, l_dblFCCYRoundDiv);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblForeignCCYAmount;
    }
    
    /**
     * (get徴収率)<BR>
     * 手数料計算で使用する委託手数料顧客条件登録マスター.顧客徴収率を取得する。<BR>
     * 取得方法@は機@能定義書「共通処理（諸経費計算）」を参照。<BR>
     * ※手数料商品コードは”外国株式”固定。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_lngBranchId - 部店ID
     * @@param l_lngAccountId - 口座ID
     * @@param l_tsOrderBizDate - 発注日
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getChargeRatio(
        String l_strInstitutionCode,
        long l_lngBranchId,
        long l_lngAccountId,
        Timestamp l_tsOrderBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChargeRatio(String, long, long, Timestamp)";
        log.entering(STR_METHOD_NAME);
        
        EquityCommAccountCondMstRow l_equityCommAccountCondMstRow = null;
        String l_strOrderBizDate =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_tsOrderBizDate);
        try
        {
            l_equityCommAccountCondMstRow =
                EquityCommAccountCondMstDao.findRowByPk(
                    l_strInstitutionCode,
                    l_lngBranchId,
                    l_lngAccountId,
                    WEB3CommisionProductCodeDef.FOREIGN_EQITY,
                    l_strOrderBizDate);
        }
        catch (DataFindException l_dfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        double l_dblChargeRatio = l_equityCommAccountCondMstRow.getAccountChargeRatio();
        log.debug("委託手数料顧客条件登録マスター：顧客徴収率：[" + l_dblChargeRatio + "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_dblChargeRatio;
    }

    /**
     * (calc外貨換算)<BR>
     * （calcForignCCYAmount）<BR>
     * 指定金額を指定レートで外貨換算を行う。<BR>
     * <BR>
     * 金額（円貨）÷レートの計算結果を返却する。<BR>
     * （計算結果は、小数部桁数未満を外貨換算丸め方式で丸め処理を行う）<BR>
     * @@param l_bdAmount - (金額（円貨）)<BR>
     * 小数点を含む金額（円貨）<BR>
     * @@param l_dblRate - (レート)<BR>
     * レート<BR>
     * @@param l_intScale - (小数部桁数)<BR>
     * 小数部桁数<BR>
     * @@param l_strChangeFccyRoundDiv - (外貨換算丸め方式)<BR>
     * 外貨換算丸め方式<BR>
     * 0：四捨五入　@1：切捨　@2：切上<BR>
     * @@return BigDecimal
     * @@throws WEB3BaseException
     */
    public BigDecimal calcForeignCCYAmount(
        BigDecimal l_bdAmount,
        double l_dblRate,
        int l_intScale,
        String l_strChangeFccyRoundDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcForeignCCYAmount(BigDecimal, double, int, String)";
        log.entering(STR_METHOD_NAME);

        if (l_dblRate == 0D)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        BigDecimal l_bdRate = new BigDecimal(String.valueOf(l_dblRate));
        BigDecimal l_bdResult = null;

        //0：四捨五入
        if (WEB3ChangeRoundDivDef.ROUNDING_OFF.equals(l_strChangeFccyRoundDiv))
        {
            l_bdResult =
                l_bdAmount.divide(l_bdRate, l_intScale, BigDecimal.ROUND_HALF_UP);
        }
        //1：切捨
        else if (WEB3ChangeRoundDivDef.CUTTING_OFF.equals(l_strChangeFccyRoundDiv))
        {
            l_bdResult =
                l_bdAmount.divide(l_bdRate, l_intScale, BigDecimal.ROUND_DOWN);
        }
        //2：切上
        else if (WEB3ChangeRoundDivDef.RAISING_TO_A_UNIT.equals(l_strChangeFccyRoundDiv))
        {
            l_bdResult =
                l_bdAmount.divide(l_bdRate, l_intScale, BigDecimal.ROUND_UP);
        }
        else
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_bdResult;
    }

    /**
     * (getネッティング為替レート)<BR>
     * ネッティング為替レートを取得する。 <BR>
     * <BR>
     * １）　@ネッティング為替レートを格納するHashMapを生成する。 <BR>
     * <BR>
     * ２）　@引数.トランザクション（取引勘定明細）の要素数分Loop処理 。 <BR>
     * <BR>
     * ２－１）通貨コード分、現地清算代金（外貨）を集計 <BR>
     * 通貨コードが同じのﾄﾗﾝｻﾞｸｼｮﾝ（取引勘定明細）行.現地清算代金（外貨）を集計する。 <BR>
     * ２－１－１）取引勘定明細行.トランザクションタイプ==701：外株買いの場合 <BR>
     * 買付現地清算代金（外貨）（通貨コードｎ）＋＝取引勘定明細行.現地清算代金（外貨） <BR>
     * ※ｎ：1,2,3...ｎ <BR>
     * ２－１－２）取引勘定明細行.トランザクションタイプ==702：外株売りの場合 <BR>
     * 売付現地清算代金（外貨）（通貨コードｎ）＋＝取引勘定明細行.現地清算代金（外貨） <BR>
     * ※ｎ：1,2,3...ｎ <BR>
     * <BR>
     * ３）通貨コード分ネッティング為替レートの計算 <BR>
     * 　@２）取得した通貨コードｎの要素数分Loop処理 <BR>
     * 　@３－１）売付現地清算代金（外貨）（通貨コードｎ） == 0　@または　@買付現地清算代金（外貨）（通貨コードｎ） == 0の場合、 <BR>
     * 　@HashMapに当該通貨コードのネッティング為替レートを設定しない。 <BR>
     * 　@３－２）上記以外の場合、 <BR>
     * 　@売付現地清算代金（外貨）（通貨コードｎ） - 買付現地清算代金（外貨）（通貨コードｎ） ＞ ０の場合、 <BR>
     * 　@ネッティング為替レート（通貨コードｎ）＝（共通）通貨.get売付約定為替レート( ) <BR>
     * 　@以外の場合、 <BR>
     * 　@ネッティング為替レート（通貨コードｎ）＝（共通）通貨.get買付約定為替レート( ) <BR>
     * 　@……… <BR>
     * 　@※ｎ：1,2,3...ｎ <BR>
     * <BR>
     * ４）HashMapにネッティング為替レートを設定する。 <BR>
     * key　@　@　@　@　@　@　@value <BR>
     * 通貨コード　@　@　@ネッティング為替レート <BR>
     * ---------- ----------------------------- <BR>
     * 通貨コード１　@　@ネッティング為替レート（通貨コード１） <BR>
     * 通貨コード２　@　@ネッティング為替レート（通貨コード２） <BR>
     * …　@　@　@　@　@　@　@　@… <BR>
     * 通貨コードｎ　@　@ネッティング為替レート（通貨コードｎ） <BR>
     * @@param l_lisFeqFinTransactionParams - (トランザクション（取引勘定明細）行List)<BR>
     * トランザクション（取引勘定明細）行List<BR>
     * @@return HashMap
     */
    public HashMap getNettingExchangeRate(List l_lisFeqFinTransactionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "getNettingExchangeRate(List)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisFeqFinTransactionParams == null
            || l_lisFeqFinTransactionParams.size() == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        HashMap l_hmNettingExchangeRate = new HashMap();
        HashMap l_hmBalanceAmountFcBuy = new HashMap();
        HashMap l_hmBalanceAmountFcSell = new HashMap();
        HashSet l_hsCurrencyCode = new HashSet();
        Iterator l_iterator = l_lisFeqFinTransactionParams.iterator();
        while (l_iterator.hasNext())
        {
            FeqFinTransactionRow l_feqFinTransactionRow = (FeqFinTransactionRow)l_iterator.next();
            String l_strCurrencyCode = l_feqFinTransactionRow.getCurrencyCode();
            BigDecimal l_bdBalanceAmountFc = new BigDecimal(Double.toString(l_feqFinTransactionRow.getBalanceAmountFc()));
            //２－１－１）取引勘定明細行.トランザクションタイプ==701：外株買いの場合
            if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionRow.getFinTransactionType()))
            {
                //買付現地清算代金（外貨）（通貨コードｎ）＋＝取引勘定明細行.現地清算代金（外貨）
                if (l_hmBalanceAmountFcBuy.containsKey(l_strCurrencyCode))
                {
                    double l_dblBalanceAmountFcBuy = ((Double)l_hmBalanceAmountFcBuy.get(l_strCurrencyCode)).doubleValue();
                    l_hmBalanceAmountFcBuy.put(l_strCurrencyCode,
                        new Double(l_bdBalanceAmountFc.add(new BigDecimal(Double.toString(l_dblBalanceAmountFcBuy))).doubleValue()));
                }
                else
                {
                    l_hmBalanceAmountFcBuy.put(l_strCurrencyCode, new Double(l_feqFinTransactionRow.getBalanceAmountFc()));
                }
            }
            //２－１－２）取引勘定明細行.トランザクションタイプ==702：外株売りの場合
            else if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_feqFinTransactionRow.getFinTransactionType()))
            {
                //売付現地清算代金（外貨）（通貨コードｎ）＋＝取引勘定明細行.現地清算代金（外貨）
                if (l_hmBalanceAmountFcSell.containsKey(l_strCurrencyCode))
                {
                    double l_dblBalanceAmountFcSell = ((Double)l_hmBalanceAmountFcSell.get(l_strCurrencyCode)).doubleValue();
                    l_hmBalanceAmountFcSell.put(l_strCurrencyCode,
                        new Double(l_bdBalanceAmountFc.add(new BigDecimal(Double.toString(l_dblBalanceAmountFcSell))).doubleValue()));
                }
                else
                {
                    l_hmBalanceAmountFcSell.put(l_strCurrencyCode, new Double(l_feqFinTransactionRow.getBalanceAmountFc()));
                }
            }
            l_hsCurrencyCode.add(l_strCurrencyCode);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        long l_lngAccountId = ((FeqFinTransactionRow)l_lisFeqFinTransactionParams.get(0)).getAccountId();
        long l_lngSubAccountId = ((FeqFinTransactionRow)l_lisFeqFinTransactionParams.get(0)).getSubAccountId();
        WEB3GentradeSubAccount l_subAccount = null;
        Institution l_institution = null;
        String l_strInstitutionCode = "";
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);
            l_institution = (Institution)l_subAccount.getInstitution();
            l_strInstitutionCode = l_institution.getInstitutionCode();
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //３）通貨コード分ネッティング為替レートの計算
        Iterator l_iteratorCurrencyCode = l_hsCurrencyCode.iterator();
        while(l_iteratorCurrencyCode.hasNext())
        {
            String l_strCurrencyCode = (String)l_iteratorCurrencyCode.next();
            if (l_hmBalanceAmountFcBuy.containsKey(l_strCurrencyCode)
                && l_hmBalanceAmountFcSell.containsKey(l_strCurrencyCode))
            {
                GenCurrencyRow l_currencyRow = null;
                try
                {
                    l_currencyRow =
                        GenCurrencyDao.findRowByPk(l_strInstitutionCode, l_strCurrencyCode);
                }
                catch (DataFindException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 
                catch (DataQueryException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。 ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 
                catch (DataNetworkException l_ex)        
                {
                    log.error("DBへのアクセスに失敗しました。 ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                //（共通）通貨を取得する
                WEB3GentradeCurrency l_gentradeCurrency =
                    new WEB3GentradeCurrency(new GenCurrencyParams(l_currencyRow));
                //（共通）通貨.get売付約定為替レート( )
                double l_dblSellExecRate = l_gentradeCurrency.getSellExecRate();
                //（共通）通貨.get買付約定為替レート( )
                double l_dblBuyExecRate = l_gentradeCurrency.getBuyExecRate();

                double l_dblBalanceAmountFcBuy = ((Double)l_hmBalanceAmountFcBuy.get(l_strCurrencyCode)).doubleValue();
                double l_dblBalanceAmountFcSell = ((Double)l_hmBalanceAmountFcSell.get(l_strCurrencyCode)).doubleValue();
                BigDecimal l_bdBalanceAmountFcBuy = new BigDecimal(Double.toString(l_dblBalanceAmountFcBuy));
                BigDecimal l_bdBalanceAmountFcSell = new BigDecimal(Double.toString(l_dblBalanceAmountFcSell));
                //売付現地清算代金（外貨）（通貨コードｎ） - 買付現地清算代金（外貨）（通貨コードｎ） ＞ ０の場合
                if (l_bdBalanceAmountFcSell.subtract(l_bdBalanceAmountFcBuy).doubleValue() > 0)
                {
                    l_hmNettingExchangeRate.put(l_strCurrencyCode, new Double(l_dblSellExecRate));
                }
                else
                {
                    l_hmNettingExchangeRate.put(l_strCurrencyCode, new Double(l_dblBuyExecRate));
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_hmNettingExchangeRate;
    }
    
    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcCommission(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution)
     */
    public double calcCommission(OrderExecution arg0)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcCapitalGainTax(com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum, double)
     */
    public double calcCapitalGainTax(SubAccount arg0, TaxTypeEnum arg1, double arg2)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcExecutionAmount(com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, long, double, double, com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum)
     */
    public double calcExecutionAmount(ProductTypeEnum arg0, long arg1, double arg2, double arg3, QuantityTypeEnum arg4)
    {
        // TODO Auto-generated method stub
        return 0;
    }
}
@
