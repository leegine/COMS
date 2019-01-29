head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信の計算サービス(WEB3MutualFundBizLogicProvider)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 韋念瓊 (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2006/06/26 周捷 (中訊) 計算式書  No.022
Revesion History : 2006/07/18 松本 (SRA) 計算式書  No.023
Revesion History : 2007/01/04 松本 (SRA) 計算式書  No.024
Revesion History : 2007/02/06 肖志偉(中訊) モデルNo.531
Revesion History : 2007/10/15 孫洪江(中訊) モデルNo.582
**/

package webbroker3.mf;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundBizLogicProvider;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3RecruitCommissionDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.mf.define.WEB3MFCommissionDivDef;
import webbroker3.mf.define.WEB3MFDealDivDef;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * 投信の計算サービス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundBizLogicProvider extends WEB3GentradeBizLogicProvider
    implements GlobalBizLogicProvider, MutualFundBizLogicProvider 
{
    /**
     * 源泉徴収率（国税）
     */
    public static final String INCOME_TAX = "INCOME_TAX";

    /**
     * 源泉徴収率（地方税）
     */
    public static final String LOCAL_TAX = "LOCAL_TAX";

    /**
     * 消費税率
     */
    public static final String TAX_RATE = "TAX_RATE";
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundBizLogicProvider.class);

    /**
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
     * @@param pType - プロダクトタイプ
     * @@param pId - プロダクトID
     * @@param price - 約定単価
     * @@param quantity - 約定数量
     * @@param qType - 数量タイプ
     *
     * @@return double - 約定金額
     * @@roseuid 4010AF2C0227
     */

    public double calcExecutionAmount(ProductTypeEnum pType, long pId, double price, double quantity, QuantityTypeEnum qType)
    {
        return 0.0;
    }
    
    /**
     * @@param l_orderExecution
     * @@return double
     * @@roseuid 40A3495A00B7
     */
    public ConsolidatedCommissionInfo calcCommission(Object[] l_objData)
    {
        //ある注文の約定手数料を算出する。
        return null;
    }
    
    /**
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
     * @@param l_subAccount - 補助口座
     * @@param l_orderSpec - 注文内容
     *
     * @@return OrderValidationResult
     * @@roseuid 4010AF2C0227
     */
    public OrderValidationResult checkTradingPower(SubAccount l_subAccount, OrderSpec l_orderSpec)
    {
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }
    /**
     * (calc概算売買代金)<BR>
     * 概算売買代金を算出して概算受渡代金オブジェクトの概算売買代金あるいは<BR>
     * 概算売買代金(外貨）にセットする。<BR>
     * <BR>
     * <BR>
     * <BR>
     * (1) 引数の拡張投信銘柄オブジェクト．通貨コード<BR>
     * ＝T0（WEB3MFOrderQuantityType.EN））<BR>
     * <BR>
     * 　@【買付】　@処理区分＝買付の場合<BR>
     * <BR>
     * 　@概算売買代金 ＝ 注文口数 × 買付基準価額 ／ 基準価額計算単位<BR>
     * <BR>
     * 　@　@注文口数　@　@　@　@　@　@：引数の注文数量<BR>
     * 　@　@買付基準価額　@　@　@：引数の拡張投信銘柄の買付基準価額<BR>
     * 　@　@基準価額計算単位：引数の拡張投信銘柄の基準価額計算単位<BR>
     * <BR>
     * <BR>
     * 　@【解約／乗換】 処理区分＝解約 or 乗換の場合<BR>
     * <BR>
     * 　@概算売買代金 ＝ 注文口数 × 解約価額  ／ 基準価額計算単位<BR>
     * <BR>
     * 　@　@注文口数　@　@　@　@　@　@：引数の注文数量<BR>
     * 　@　@解約価額　@　@　@　@　@　@：拡張投信銘柄の解約価額<BR>
     * 　@　@基準価額計算単位：拡張投信銘柄の基準価額計算単位<BR>
     * <BR>
     *  【募集】　@処理区分＝募集の場合 <BR>
     * <BR>
     * 　@概算売買代金 ＝ 注文口数 × 募集価額 ／ 基準価額計算単位 <BR>
     * <BR>
     * 　@　@注文口数　@　@　@　@　@　@：引数の注文数量 <BR>
     * 　@　@募集価額　@　@　@　@　@　@：引数の拡張投信銘柄の募集価額 <BR>
     * 　@　@基準価額計算単位：引数の拡張投信銘柄の基準価額計算単位 <BR>
     * <BR>
     * (2) 引数の拡張投信銘柄オブジェクト．通貨コード<BR>
     * ！＝T0（WEB3MFOrderQuantityType.EN））<BR>
     * <BR>
     * 　@①@ 外貨建ての概算売買代金を算出する。（小数点3位以下切り捨て）<BR>
     * <BR>
     * 　@　@【買付】　@処理区分＝買付の場合<BR>
     * <BR>
     * 　@　@　@概算売買代金（外貨） ＝ 注文口数 × 買付基準価額 ／ 基準価額計算単位<BR>
     * 　@　@　@概算売買代金 ＝ 概算売買代金（外貨）<BR>
     * <BR>
     * 　@　@　@　@注文口数　@　@　@　@　@　@：引数の注文数量<BR>
     * 　@　@　@　@買付基準価額　@　@　@：拡張投信銘柄の買付基準価額<BR>
     * 　@　@　@　@基準価額計算単位：拡張投信銘柄の基準価額計算単位<BR>
     * <BR>
     * <BR>
     * 　@　@【解約／乗換】 処理区分＝解約 or 乗換の場合<BR>
     * <BR>
     * 　@　@　@概算売買代金（外貨） ＝ 注文口数 × 解約価額 ／ 基準価額計算単位<BR>
     * 　@　@　@概算売買代金 ＝ 概算売買代金（外貨）<BR>
     * <BR>
     * 　@　@　@　@注文口数　@　@　@　@　@　@：引数の注文数量<BR>
     * 　@　@　@　@解約価額　@　@　@　@　@　@：拡張投信銘柄の解約価額<BR>
     * 　@　@　@　@基準価額計算単位：拡張投信銘柄の基準価額計算単位<BR>
     * 　@　@【募集】　@処理区分＝募集の場合 <BR>
     * <BR>
     * 　@　@　@概算売買代金（外貨） ＝ 注文口数 × 募集価額 ／ 基準価額計算単位 <BR>
     * 　@　@　@概算売買代金 ＝ 概算売買代金（外貨） <BR>
     * <BR>
     * 　@　@　@　@注文口数　@　@　@　@　@　@：引数の注文数量 <BR>
     * 　@　@　@　@募集価額　@　@　@　@　@　@：拡張投信銘柄の募集価額 <BR>
     * 　@　@　@　@基準価額計算単位：拡張投信銘柄の基準価額計算単位 <BR>
     * <BR>
     * 　@② 引数の決済方法@が円貨の場合に、円貨建ての概算売買代金を算出する。<BR>
     * （小数点以下切り捨て）<BR>
     * <BR>
     *      概算売買代金 ＝ ①@で算出した概算売買代金（外貨） × 為替レート ／ 為替レート計算単位<BR>
     * <BR>
     * 　@　@為替レート、計算単位： 引数.拡張投信銘柄.get為替レート()にて為替レートParams取得<BR>
     * 　@　@　@・買付／募集の場合の為替レート ： TTS<BR>
     * 　@　@　@・解約／乗換の場合の為替レート ： TTB<BR>
     * <BR>
     * @@param l_strProcessDiv - 処理区分<BR>
     * <BR>
     * １：買付<BR>
     * ２：解約<BR>
     * ３：乗換<BR>
     * ４：募集 <BR>
     * @@param l_dblOrderQuantity - (注文数量)<BR>
     * @@param l_strSettleDiv - 決済方法@<BR>
     * <BR>
     * １：円貨<BR>
     * ２：外貨<BR>
     * @@param l_mutualFundProduct - (拡張投信銘柄)<BR>
     * @@param l_estimatedPrice - 概算受渡代金オブジェクト<BR>
     * @@roseuid 40ADD3790399
     */
    public void calcEstimatedTradeAmount(
        String l_strProcessDiv, 
        double l_dblOrderQuantity, 
        String l_strSettleDiv, 
        WEB3MutualFundProduct l_web3MutualFundProduct, 
        WEB3MutualFundEstimatedPrice l_estimatedPrice) throws WEB3BaseException        
    {
        final String STR_METHOD_NAME = "calcEstimatedTradeAmount(" +
            "String, double, String, WEB3MutualFundProduct, " +
            "WEB3MutualFundEstimatedPrice)";
        log.entering(STR_METHOD_NAME);
        
        if (l_web3MutualFundProduct == null || l_estimatedPrice == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //概算売買代金
        BigDecimal l_bdEstimatedTradeAmount = null;

        //注文口数
        BigDecimal l_bdOrderQuantity = new BigDecimal(Double.toString(l_dblOrderQuantity));

        //基準価格、基準価格計算単位
        BigDecimal l_bdConstantValue = null;
        BigDecimal l_bdConstantValueCalcUnit =
        	new BigDecimal(Double.toString(l_web3MutualFundProduct.getConstantValueCalcUnit()));
        
		//処理区分＝買付の場合
		if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv))
		{
			log.debug("処理区分＝買付");

			//基準価格 ＝ 買付基準価格
			l_bdConstantValue = new BigDecimal(Double.toString(l_web3MutualFundProduct.getConstantValue()));
		}
		//処理区分＝解約 or 乗換の場合
		if (WEB3ProcessDivDef.SELL.equals(l_strProcessDiv) || 
			WEB3ProcessDivDef.SWITCHING.equals(l_strProcessDiv))
		{
			log.debug("処理区分＝解約or乗換");

			//基準価格 ＝ 解約価格
			l_bdConstantValue = new BigDecimal(Double.toString(l_web3MutualFundProduct.getSellValue()));
		}
            
		//処理区分＝募集の場合
		if (WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
		{
			log.debug("処理区分＝募集");

			//基準価格 ＝ 募集価格
			l_bdConstantValue = new BigDecimal(Double.toString(l_web3MutualFundProduct.getRecruitConstantValue()));
		}

		log.debug("基準価格 = " + l_bdConstantValue);
		log.debug("基準価格計算単位 = " + l_bdConstantValueCalcUnit);

        //(1)引数の拡張投信銘柄オブジェクト．通貨コード = T0 の場合
        if (WEB3MFOrderQuantityType.EN.equals(
            l_web3MutualFundProduct.getCurrencyCode()))
        {
			log.debug("引数の拡張投信銘柄オブジェクト．通貨コード = T0 の場合");

			//概算売買代金 ＝ 注文口数 × 基準価額 ／ 基準価額計算単位
			l_bdEstimatedTradeAmount =
				l_bdOrderQuantity.multiply(l_bdConstantValue).divide(
					l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_FLOOR);

			log.debug("概算売買代金 = " + l_bdEstimatedTradeAmount);

			l_estimatedPrice.setEstimatedTradeAmount(l_bdEstimatedTradeAmount.doubleValue());
        }
        
        //概算売買代金(外貨）
		BigDecimal l_bdForeignEstimatedTradeAmount = null;
        
        //(2)引数の拡張投信銘柄オブジェクト．通貨コード != T0 の場合
        if (!WEB3MFOrderQuantityType.EN.equals(
            l_web3MutualFundProduct.getCurrencyCode()))
        {
			log.debug("引数の拡張投信銘柄オブジェクト．通貨コード != T0 の場合");

            //①@ 外貨建ての概算売買代金を算出する
			l_bdForeignEstimatedTradeAmount =
				l_bdOrderQuantity.multiply(l_bdConstantValue).divide(
						l_bdConstantValueCalcUnit,2,BigDecimal.ROUND_FLOOR);

			log.debug("概算売買代金(外貨) = " + l_bdForeignEstimatedTradeAmount);

			l_estimatedPrice.setForeignCurrencyEstimatedTradeAmount(
				l_bdForeignEstimatedTradeAmount.doubleValue());
			l_estimatedPrice.setEstimatedTradeAmount(
				l_bdForeignEstimatedTradeAmount.doubleValue());
                
            //② 引数の決済方法@が円貨の場合に、円貨建ての概算売買代金を算出する
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
            {
            	//為替レート、計算単位
            	BigDecimal l_bdExchangeRate = null;
				BigDecimal l_bdExchangeRateCalcUnit = null;

				// 引数.拡張投信銘柄.get為替レート()をコールし、為替レートと計算単位を取得する。
				ExchangeRateParams l_exchangeRateParams = 
					l_web3MutualFundProduct.getExchangeRate();

				//   処理区分 ＝ 買付、募集 の場合 ： 為替レート ＝ TTS
				if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv) ||
					WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
				{
					l_bdExchangeRate =
						new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
				}
				//   処理区分 ＝ 解約、乗換 の場合 ： 為替レート ＝ TTB
				else if (WEB3ProcessDivDef.SELL.equals(l_strProcessDiv) || 
					WEB3ProcessDivDef.SWITCHING.equals(l_strProcessDiv))
				{
					l_bdExchangeRate =
						new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
				}
			
				l_bdExchangeRateCalcUnit =
					new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
            	
				log.debug("為替レート = " + l_bdExchangeRate);
				log.debug("為替レート計算単位 = " + l_bdExchangeRateCalcUnit);

                //概算売買代金 ＝ ①@で算出した概算売買代金（外貨） × 為替レート／ 
                //                為替レート計算単位
				l_bdEstimatedTradeAmount =
					l_bdForeignEstimatedTradeAmount.multiply(l_bdExchangeRate).divide(
						l_bdExchangeRateCalcUnit,0,BigDecimal.ROUND_FLOOR);

				log.debug("概算売買代金(円貨) = " + l_bdEstimatedTradeAmount);

                l_estimatedPrice.setEstimatedTradeAmount(l_bdEstimatedTradeAmount.doubleValue());
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc概算売買口数)<BR>
     * 概算売買口数の計算をして概算受渡代金オブジェクトの概算売買口数にセットする。<BR>
     * 計算結果は小数点以下切り捨て<BR>
     * <BR>
     * <BR>
     * <BR>
     * 概算売買口数を算出する。<BR>
     * <BR>
     * 　@　@【買付】　@処理区分＝買付の場合<BR>
     * <BR>
     * 　@　@概算売買口数 ＝ 注文金額 × 基準価額計算単位 ／ 買付基準価額<BR>
     * <BR>
     * 　@　@注文金額　@　@　@　@　@　@：引数の注文数量<BR>
     * 　@　@基準価額計算単位：拡張投信銘柄の基準価額計算単位<BR>
     * 　@　@買付基準価額　@　@　@：拡張投信銘柄の買付基準価額<BR>
     * <BR>
     * <BR>
     * 　@　@【解約／乗換】 処理区分＝解約 または 乗換の場合<BR>
     * <BR>
     * 　@　@概算売買口数 ＝ （注文金額 × 基準価額計算単位 ）／ 解約価額<BR>
     * <BR>
     * 　@　@注文金額　@　@　@　@　@　@：引数の注文数量<BR>
     * 　@　@基準価額計算単位：拡張投信銘柄の基準価額計算単位<BR>
     * 　@　@解約価額　@　@　@　@　@　@：拡張投信銘柄の解約価額<BR>
     * <BR>
     * 　@　@【募集】　@処理区分＝募集の場合 <BR>
     * <BR>
     * 　@　@概算売買口数 ＝ 注文金額 × 基準価額計算単位 ／ 募集価額 <BR>
     * <BR>
     * 　@　@注文金額　@　@　@　@　@　@：引数の注文数量 <BR>
     * 　@　@基準価額計算単位：拡張投信銘柄の基準価額計算単位 <BR>
     * 　@　@募集価額　@　@　@　@　@　@：拡張投信銘柄の募集価額 <BR>v
     * @@param l_strProcessDiv - 処理区分<BR>
     * <BR>
     * １：買付<BR>
     * ２：解約<BR>
     * ３：乗換<BR>
     * ４：募集<BR>
     * @@param l_dblOrderQuantity - (注文数量)<BR>
     * @@param l_mutualFundProduct - 拡張投信銘柄<BR>
     * @@param l_estimatedPrice - 概算受渡代金オブジェクト<BR>
     * @@roseuid 40B174A101D3
     */
    public void calcEstimatedQty(
        String l_strProcessDiv, 
        double l_dblOrderQuantity, 
        WEB3MutualFundProduct l_web3MutualFundProduct, 
        WEB3MutualFundEstimatedPrice l_estimatedPrice) throws WEB3BaseException         
    {
        String STR_METHOD_NAME = "calcEstimatedQty()";
        log.entering(STR_METHOD_NAME);
        
        if (l_web3MutualFundProduct == null || l_estimatedPrice == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
       
        //概算売買口数
		BigDecimal l_bdEstimatedQty = null;

		// １）為替レートを取得する。
		BigDecimal l_bdExchangeRate = new BigDecimal("1");
		BigDecimal l_bdExchangeRateCalcUnit = new BigDecimal("1");
		
		// １－１）引数.拡張投信銘柄.is外貨建投信()の戻り値 == true の場合
		// １－２）それ以外（円建投信）の場合
		// 為替レートと計算単位をそれぞれ1とする。
		if (l_web3MutualFundProduct.isForeignCurencyFund())
		{
			log.debug("引数.拡張投信銘柄.is外貨建投信()の戻り値 == true の場合");
			
			// 引数.拡張投信銘柄.get為替レート()をコールし、為替レートと計算単位を取得する。
			ExchangeRateParams l_exchangeRateParams = 
				l_web3MutualFundProduct.getExchangeRate();

			//   処理区分 ＝ 買付、募集 の場合 ： 為替レート ＝ TTS
			if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv) ||
				WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
			{
				l_bdExchangeRate =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
			}
			//   処理区分 ＝ 解約、乗換 の場合 ： 為替レート ＝ TTB
			else if (WEB3ProcessDivDef.SELL.equals(l_strProcessDiv) || 
				WEB3ProcessDivDef.SWITCHING.equals(l_strProcessDiv))
			{
				l_bdExchangeRate =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
			}
			
			l_bdExchangeRateCalcUnit =
				new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));

			log.debug("為替レート = " + l_bdExchangeRate);
			log.debug("為替レート計算単位 = " + l_bdExchangeRateCalcUnit);
		}
		
		// ２）概算売買口数を算出する。
		
		// 基準価格の決定
		BigDecimal l_bdConstantValue = null;
		
		//【買付】処理区分＝買付の場合
		if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv))
		{
			log.debug("処理区分＝買付");

			// 買付基準価額
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_web3MutualFundProduct.getConstantValue()));
		}
		//【解約／乗換】 処理区分＝解約 または 乗換の場合
		else if (WEB3ProcessDivDef.SELL.equals(l_strProcessDiv) || 
			WEB3ProcessDivDef.SWITCHING.equals(l_strProcessDiv))
		{
			log.debug("処理区分＝解約or乗換");

			// 解約価額  
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_web3MutualFundProduct.getSellValue()));
		}
		//【募集】　@処理区分＝募集の場合
		else if (WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
		{
			log.debug("処理区分＝募集");

			// 募集価額   
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_web3MutualFundProduct.getRecruitConstantValue()));
		}        

		log.debug("基準価格 = " + l_bdConstantValue);

		//概算売買口数 ＝ 注文金額 × 基準価額計算単位 ／（基準価額 × 為替レート ÷ 計算単位）
		//計算結果は小数点以下切り捨て
		BigDecimal l_bdOrderQuantity =
			new BigDecimal(Double.toString(l_dblOrderQuantity));
		BigDecimal l_bdConstantValueCalcUnit =
			new BigDecimal(Double.toString(l_web3MutualFundProduct.getConstantValueCalcUnit()));

		log.debug("基準価格計算単位 = " + l_bdConstantValueCalcUnit);

		l_bdEstimatedQty =
			l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
				l_bdConstantValue.multiply(l_bdExchangeRate).divide(
					l_bdExchangeRateCalcUnit,10,BigDecimal.ROUND_HALF_UP),
				0,BigDecimal.ROUND_FLOOR);

		log.debug("概算売買口数 = " + l_bdEstimatedQty);

		//概算受渡代金オブジェクトの概算売買口数にセットする
		l_estimatedPrice.setEstimatedQty(l_bdEstimatedQty.doubleValue());

		log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc割増拘束金)<BR>
     * 概算売買代金、あるいは概算売買口数に対して割増拘束率を加味した割増拘束金を返却する。<BR>
     * <BR>
     * (1) 会社部店商品テーブルより割増拘束率、割引拘束率を取得する。<BR>
     * （部店IDと商品コードで取得）<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@部店ID　@　@：補助口座オブジェクトの部店ID<BR>
     * 　@　@商品コード：20（投信）<BR>
     * <BR>
     * (2) 処理区分が募集の場合かつ拡張投信銘柄.募集手数料区分が「2：外枠」以外の場合、<BR>
     * 割増拘束率、割引拘束率は1にする。<BR>
     * <BR>
     * (3) 口数指定の場合、割増拘束金を算出して、<BR>
     * 概算受渡代金オブジェクトの概算受渡代金にセットする。<BR>
     * <BR>
     * 　@　@【買付／募集】<BR>
     * <BR>
     * 　@　@　@概算受渡代金 ＝ 概算売買代金 × 割増拘束率<BR>
     * <BR>
     * 　@　@　@概算売買代金：引数の数量<BR>
     * 　@　@　@割増拘束率　@ ：(1)で取得した割増拘束率<BR>
     * <BR>
     * <BR>
     * 　@　@【解約／乗換】<BR>
     * <BR>
     * 　@　@　@概算受渡代金 ＝ 概算売買代金 × 割引拘束率<BR>
     * <BR>
     * 　@　@　@概算売買代金：引数の数量<BR>
     * 　@　@　@割引拘束率　@ ：(1)で取得した割引拘束率<BR>
     * <BR>
     * <BR>
     * <BR>
     * (4) 金額指定の場合、割増拘束口数を算出して、<BR>
     * 概算受渡代金オブジェクトの概算売買口数にセットする。小数点以下切り捨て<BR>
     * <BR>
     * 　@　@【買付／募集】<BR>
     * <BR>
     * 　@　@概算売買口数 ＝ 概算売買口数 × 割引拘束率<BR>
     * <BR>
     * 　@　@　@概算売買口数：引数の数量<BR>
     * 　@　@　@割引拘束率　@ ：(1)で取得した割引拘束率<BR>
     * <BR>
     * 　@　@【解約／乗換】<BR>
     * <BR>
     * 　@　@概算売買口数 ＝ 概算売買口数 × 割増拘束率<BR>
     * <BR>
     * 　@　@　@概算売買口数：引数の数量<BR>
     * 　@　@　@割増拘束率　@ ：(1)で取得した割増拘束率<BR>
     * @@param l_strProcessDiv - 処理区分<BR>
     * <BR>
     * １：買付<BR>
     * ２：解約<BR>
     * ３：乗換<BR>
     * ４：募集<BR>
     * @@param l_strDesignateMethod - 指定方法@<BR>
     * <BR>
     * ３：金額指定<BR>
     * ４：口数指定<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 口数指定時は概算売買金額、金額指定時は概算売買口数を設定<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_estimatedPrice - 概算受渡代金オブジェクト<BR>
     * @@param l_web3MutualFundProduct - 拡張投信銘柄オブジェクト<BR>
     * @@roseuid 40B1ACB60127
     */
    public void calcIncreaseRestraintPriceInRatio(
        String l_strProcessDiv, 
        String l_strDesignateMethod, 
        double l_dblQuantity, 
        SubAccount l_subAccount, 
        WEB3MutualFundEstimatedPrice l_estimatedPrice,
        WEB3MutualFundProduct l_web3MutualFundProduct)
        throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcIncreaseRestraintPriceInRatio(" +
            "String, String, double, SubAccount, WEB3MutualFundEstimatedPrice)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        List l_lisRows = new Vector();
        String l_strWhereClause;
        
        //[検索条件]<BR>
        //部店ID　@　@：補助口座オブジェクトの部店ID<BR>
        //商品コード：20（投信）<BR>
        l_strWhereClause = "branch_id = ? and commission_product_code = ?";
        
        long l_lngBranchId = 0L;						//部店ID        
        BigDecimal l_bdPremiumRestraintRate = null;	//割増拘束率        
        BigDecimal l_bdDiscountRestraintRate = null;	//割引拘束率

        //get部店ID
        l_lngBranchId = l_subAccount.getMainAccount().getBranch().getBranchId();
        
        Object l_bindVars[] = { new Long(l_lngBranchId), 
                                WEB3CommisionProductCodeDef.MUTUAL_FUND};
        try
        {
            //DataQueryException,DataNetworkException
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                InstBranchProductRow.TYPE,
                    l_strWhereClause,
                    l_bindVars);
                        
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.debug("__テーブルに該当するデータがありません__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                InstBranchProductParams l_instBranchProductParams = 
                    (InstBranchProductParams)l_lisRows.get(0);                               
                //(1) 会社部店商品テーブルより割増拘束率を取得する
                l_bdPremiumRestraintRate = 
                    new BigDecimal(Double.toString(l_instBranchProductParams.getPremiumRestraintRate()));
                //(1) 会社部店商品テーブルより割引拘束率を取得する
                l_bdDiscountRestraintRate = 
                    new BigDecimal(Double.toString(l_instBranchProductParams.getDiscountRestraintRate()));
                //(2) 処理区分が募集の場合かつ拡張投信銘柄.募集手数料区分が「2：外枠」以外の場合、
                //割増拘束率、割引拘束率は1にする。
                if (WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv)
                    && !(WEB3RecruitCommissionDivDef.WITHOUT_THE_LIMIT.equals(
                        l_web3MutualFundProduct.getRecruitCommissionDiv())))
                {
                	l_bdPremiumRestraintRate  = new BigDecimal(1);
                	l_bdDiscountRestraintRate = new BigDecimal(1);
                }
            }           
            
			BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_dblQuantity));
			
            //(3) 口数指定の場合
            if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strDesignateMethod))
            {
				//概算受渡代金
				BigDecimal l_bdEstimatedPrice = null;

                //【買付／募集】
                if (WEB3MFProcessDivDef.BUY.equals(l_strProcessDiv) || 
                    WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
                {
					log.debug("拘束率 = " + l_bdPremiumRestraintRate);

                    //概算受渡代金 ＝ 概算売買代金 × 割増拘束率
                    l_bdEstimatedPrice = l_bdQuantity.multiply(l_bdPremiumRestraintRate);
                }
                //【解約／乗換】
                else if (WEB3MFProcessDivDef.SELL.equals(l_strProcessDiv) || 
                    WEB3MFProcessDivDef.SWITCHING.equals(l_strProcessDiv))
                {
					log.debug("拘束率 = " + l_bdDiscountRestraintRate);

                    //概算受渡代金 ＝ 概算売買代金 × 割引拘束率 
					l_bdEstimatedPrice = l_bdQuantity.multiply(l_bdDiscountRestraintRate);
                }

				l_bdEstimatedPrice = l_bdEstimatedPrice.setScale(0,BigDecimal.ROUND_FLOOR);

				log.debug("概算受渡代金 = " + l_bdEstimatedPrice);

				//概算受渡代金オブジェクトの概算受渡代金にセットする
				l_estimatedPrice.setEstimatedPrice(l_bdEstimatedPrice.doubleValue());                       
            }
            //(4) 金額指定の場合
            else if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod))
            {
				//概算売買口数
				BigDecimal l_bdEstimatedQty = null;

                //【買付／募集】
                if (WEB3MFProcessDivDef.BUY.equals(l_strProcessDiv) ||
                    WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
                {
					log.debug("拘束率 = " + l_bdDiscountRestraintRate);

                    //概算売買口数 ＝ 概算売買口数 × 割引拘束率(小数点以下切り捨て)
					l_bdEstimatedQty = l_bdQuantity.multiply(l_bdDiscountRestraintRate);
                }
                //【解約／乗換】
                else if (WEB3MFProcessDivDef.SELL.equals(l_strProcessDiv) || 
                    WEB3MFProcessDivDef.SWITCHING.equals(l_strProcessDiv))
                {
					log.debug("拘束率 = " + l_bdPremiumRestraintRate);

                    //概算売買口数 ＝ 概算売買口数 × 割増拘束率(小数点以下切り捨て)
					l_bdEstimatedQty = l_bdQuantity.multiply(l_bdPremiumRestraintRate);
                }

				l_bdEstimatedQty = l_bdEstimatedQty.setScale(0,BigDecimal.ROUND_FLOOR);

				log.debug("概算売買口数 = " + l_bdEstimatedQty);

				//概算受渡代金オブジェクトの概算売買口数にセットする
				l_estimatedPrice.setEstimatedQty(l_bdEstimatedQty.doubleValue());
            }

        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                 
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc概算受渡代金)
     * 概算受渡代金の計算を行い、計算結果を概算受渡代金オブジェクトにセットし返却する。 <BR>
     * <BR>
     * 詳細は、計算式書参照。<BR>
     * @@param l_mainAccount - 顧客
     * @@param l_mfProduct - 銘柄
     * @@param l_mfSwtProduct - 銘柄（乗換先）
     * @@param l_strDealDiv - 取引区分
     * @@param l_strOrderChannel - 注文チャネル
     * @@param l_strDesignDiv - 指定区分
     * @@param l_dblOrderQuantity - 注文数量
     * @@param l_strRequestDiv - 請求区分
     * @@param l_strAccountDiv - 口座区分
     * @@param l_strSettleDiv - 決済区分
     * @@param l_datBizDate - 発注日
     * @@return WEB3MutualFundEstimatedPrice
     * @@throws WEB3BaseException
     * @@roseuid 40A3495A00B7
     */
    public WEB3MutualFundEstimatedPrice calcEstimatedPrice(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_mfSwtProduct, 
        String l_strDealDiv, 
        String l_strOrderChannel, 
        String l_strDesignDiv, 
        double l_dblOrderQuantity, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
        String l_strSettleDiv,
        Date l_datBizDate)
            throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcEstimatedPrice(WEB3GentradeMainAccount, " +
            "WEB3MutualFundProduct, WEB3MutualFundProduct, String, String, " +
            "String, double, String, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //①@   注文数量を取得する。
        BigDecimal l_bdCalcOrderQuantity = null;
		BigDecimal l_bdOrderQuantity = new BigDecimal(Double.toString(l_dblOrderQuantity));
        
        //1)  引数.指定区分 ＝ “金額” and 引数.決済区分 ＝ “外貨” の場合
        if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv) && 
            WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv))
        {
            //(*) 為替レート： 引数.銘柄.get為替レート()にて取得。
            ExchangeRateParams l_exchangeRateParams = 
                l_mfProduct.getExchangeRate();
            
            BigDecimal l_bdExchangeRate = null;
			BigDecimal l_bdExchangeCalcUnit = 
				new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
			
            //(a) 引数.取引区分 ＝ “買付” or “募集” の場合     
            if (WEB3MFDealDivDef.BUY.equals(l_strDealDiv) || 
                WEB3MFDealDivDef.RECRUIT.equals(l_strDealDiv))
            {                
				l_bdExchangeRate = new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
            }
            //(b) 引数.取引区分 ＝ “解約” or “乗換” の場合
            else if (WEB3MFDealDivDef.SELL.equals(l_strDealDiv) || 
                WEB3MFDealDivDef.SWITCHING.equals(l_strDealDiv))
            {
				l_bdExchangeRate = new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
            }
			//注文数量 ＝ 引数.注文数量 × 為替レート ÷ 為替レート.為替レート計算単位
			l_bdCalcOrderQuantity = l_bdOrderQuantity.multiply(l_bdExchangeRate).divide(
					l_bdExchangeCalcUnit,BigDecimal.ROUND_FLOOR);
        }
        //2)  それ以外の場合
        else
        {
            //注文数量 ＝ 引数.注文数量
            l_bdCalcOrderQuantity = l_bdOrderQuantity;
        }
        
        log.debug(">>>>> １）注文数量 = " + l_bdCalcOrderQuantity);
        
        //②  投信会社別手数料を取得する。
        //投信会社別手数料のインスタンスを生成する。

        //[コンストラクタにセットする引数]
        //証券会社コード： 引数.顧客.getInstitution().getInstitutionCode() の戻り値
        //銘柄コード： （以下のとおり）
        //引数.取引区分 ＝ “乗換” の場合、引数.銘柄（乗換先）.getProductCode() の戻り値
        //引数.取引区分 ≠ “乗換” の場合、引数.銘柄.getProductCode() の戻り値
        //取引区分： 引数.取引区分
        //注文チャネル： 引数.注文チャネル
        //発注日： 引数.発注日
        
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strProductCode = null;
        
        if (WEB3MFDealDivDef.SWITCHING.equals(l_strDealDiv))
        {
            l_strProductCode = l_mfSwtProduct.getProductCode();
        }
        else
        {
            l_strProductCode = l_mfProduct.getProductCode();
        }
      
        WEB3MutualFundInstCommission l_mfInstCommission = 
            new WEB3MutualFundInstCommission(
                l_strInstitutionCode, 
                l_strProductCode, 
                l_strDealDiv, 
                l_strOrderChannel, 
                l_datBizDate);
        
		log.debug(">>>>> ２）会社別手数料 = " + l_mfInstCommission);
        
        //③ 検索値を算出する。
        //手数料単価、率を決定するための検索値を算出する。
        //this.calc検索値() をコールする。

        //[this.calc検索値()にセットする引数]
        //顧客： 引数.顧客
        //銘柄： 引数.銘柄
        //会社別手数料： ②で取得した会社別手数料
        //注文数量： ①@で取得した注文数量
        //指定区分： 引数.指定区分
        //請求区分： 引数.請求区分
        //口座区分： 引数.口座区分
        //  発注日： 引数.発注日
        BigDecimal l_bdSearchValue = 
            this.calcSearchValue(
                l_mainAccount, 
                l_mfProduct, 
                l_mfInstCommission, 
                l_bdCalcOrderQuantity, 
                l_strDesignDiv, 
                l_strRequestDiv, 
                l_strAccountDiv, 
                l_datBizDate);
        
		log.debug(">>>>> ３）検索値 = " + l_bdSearchValue);
        
        //④ 手数料単価、率を取得する。

        //会社別手数料で持つ数量範囲（01）から（10）の中から検索値が検索条件に当てはまるものを選択し、
        //その数量範囲とリンクする手数料単価、率を取得する。
        //会社別手数料.get手数料単価、率() をコールする。

        //[会社別手数料.get手数料単価、率()にセットする引数]
        //顧客： 引数.顧客
        //銘柄： 引数.銘柄
		//乗換先の銘柄： 引数.銘柄(乗換先)
        //指定区分： 引数.指定区分
        //検索値： ③で算出した検索値
        //発注日： 引数.発注日
        BigDecimal l_bdCommisionPriceRate = 
            l_mfInstCommission.getCommisionPriceRate(
                l_mainAccount, 
                l_mfProduct, 
                l_mfSwtProduct, 
                l_strDesignDiv, 
                l_bdSearchValue, 
                l_datBizDate);
        
		log.debug(">>>>> ４）手数料単価、率 = " + l_bdCommisionPriceRate);
        
		//⑤ 概算売買口数を算出する。

		//概算売買口数を算出する。
		//this.calc概算売買口数() をコールする。

		//[this.calc概算売買口数()にセットする引数]
		//顧客： 引数.顧客
		//銘柄： 引数.銘柄
		//会社別手数料： ②で取得した会社別手数料
		//指定区分： 引数.指定区分
		//請求区分： 引数.請求区分
		//口座区分： 引数.口座区分
		//注文数量： ①@で取得した注文数量
		//手数料単価、率： ④で取得した手数料単価、率
		//発注日： 引数.発注日
		BigDecimal l_bdEstimatedQty = 
			this.calcEstimatedQty(
				l_mainAccount, 
				l_mfProduct, 
				l_mfInstCommission, 
				l_strDesignDiv,
				l_strRequestDiv, 
				l_strAccountDiv, 
				l_bdCalcOrderQuantity, 
				l_bdCommisionPriceRate, 
				l_datBizDate);

		log.debug(">>>>> ５）概算売買口数 = " + l_bdEstimatedQty);
        
        //⑥ 概算売買代金を算出する。

        //this.calc概算売買代金() をコールする。
        //[this.calc概算売買代金()にセットする引数]
        //銘柄： 引数.銘柄
        //会社別手数料： ②で取得した会社別手数料
		//概算売買口数： ⑤で算出した概算売買口数
        
        BigDecimal l_bdEstimatedTradeAmount = 
            this.calcEstimatedTradeAmount(
                l_mfProduct, 
                l_mfInstCommission, 
				l_bdEstimatedQty);
        
		log.debug(">>>>> ６）概算売買代金 = " + l_bdEstimatedTradeAmount);
        
        //⑦ 手数料を算出する。

        //this.calc手数料() をコールする。
        //[this.calc手数料()にセットする引数]
        //顧客： 引数.顧客
        //会社別手数料： ②で取得した会社別手数料
		//銘柄： 引数.銘柄
		//乗換先の銘柄： 引数.銘柄(乗換先)
		//概算売買口数： ⑤で算出した概算売買口数
        //概算売買代金： ⑥で算出した概算売買代金
        //手数料単価、率： ④で取得した手数料単価、率
        
        BigDecimal l_bdCommission = 
            this.calcCommission(
                l_mainAccount, 
                l_mfInstCommission, 
                l_mfProduct, 
                l_mfSwtProduct, 
				l_bdEstimatedQty, 
                l_bdEstimatedTradeAmount, 
                l_bdCommisionPriceRate);
        
		log.debug(">>>>> ７）手数料 = " + l_bdCommission);
        
        //⑧ 手数料消費税を算出する。

        //this.calc手数料消費税() をコールする。
        //[this.calc手数料消費税()にセットする引数]
        //引数.顧客
        //手数料： ⑦で算出した手数料
        //発注日： 引数.発注日
        
        BigDecimal l_bdCommissionTax = 
            this.calcCommissionTax(
                l_mainAccount, 
                l_bdCommission, 
                l_datBizDate);
        
		log.debug(">>>>> ８）手数料消費税 = " + l_bdCommissionTax);
        
        //⑨ 所得税を算出する。   
        
        //this.calc所得税() をコールする。
        //[this.calc所得税()にセットする引数]
        //顧客： 引数.顧客
        //銘柄： 引数.銘柄
        //会社別手数料： ②で取得した会社別手数料
        //請求区分： 引数.請求区分
        //口座区分： 引数.口座区分
		//概算売買口数： ⑤で算出した概算売買口数
        //発注日： 引数.発注日

        //※引数.取引区分＝“解約” or “乗換” and 国内投信
        //（引数.銘柄.is外国投信()＝false and 引数.銘柄.isFWF()＝false）の場合のみ、実行。
        //上記の条件に該当しない場合は、所得税 ＝ 0 とする。        
        
        BigDecimal l_bdIncomeTax = this.calcIncomeTax(
            l_mainAccount, 
            l_mfProduct, 
            l_mfInstCommission,
            l_strRequestDiv, 
            l_strAccountDiv, 
			l_bdEstimatedQty, 
            l_datBizDate);
        
		log.debug(">>>>> ９）所得税 = " + l_bdIncomeTax);
        
        //⑩ 地方税を算出する。

        //地方税を算出する。
        //this.calc地方税() をコールする。

        //[this.calc地方税()にセットする引数]
        //顧客： 引数.顧客
        //銘柄： 引数.銘柄
        //会社別手数料： ②で取得した会社別手数料
        //請求区分： 引数.請求区分
        //口座区分： 引数.口座区分
		//概算売買口数： ⑤で算出した概算売買口数
        //発注日： 引数.発注日

        //※引数.取引区分 ＝ “解約” or “乗換” and 
        //  国内投信（引数.銘柄.is外国投信() ＝ false and 引数.銘柄.isFWF()＝false）の場合のみ、実行。
        //  上記の条件に該当しない場合は、地方税 ＝ 0 とする。
        
        BigDecimal l_bdLocalTax = this.calcLocalTax(
            l_mainAccount, 
            l_mfProduct, 
            l_mfInstCommission,
            l_strRequestDiv, 
            l_strAccountDiv, 
			l_bdEstimatedQty, 
            l_datBizDate);        
        
		log.debug(">>>>> １０）地方税 = " + l_bdLocalTax);
        
        //⑪ 概算受渡代金を算出する。

        //概算受渡代金を算出する。
        //this.calc概算受渡代金() をコールする。

        //[this.calc概算受渡代金()にセットする引数]
        //会社別手数料： ②で取得した会社別手数料
        //指定区分： 引数.指定区分
        //注文数量： ①@で取得した注文数量
        //概算売買代金： ⑥で算出した概算売買代金
        //手数料： ⑦で算出した手数料
        //消費税： ⑧で算出した手数料消費税
        //所得税： ⑨で算出した所得税
        //地方税： ⑩で算出した地方税
        BigDecimal l_bdEstimatedPrice = 
            this.calcEstimatedPrice(
                l_mfInstCommission, 
                l_strDesignDiv,
                l_bdCalcOrderQuantity, 
                l_bdEstimatedTradeAmount, 
                l_bdCommission, 
                l_bdCommissionTax, 
                l_bdIncomeTax, 
                l_bdLocalTax);
        
		log.debug(">>>>> １１）概算受渡代金 = " + l_bdEstimatedPrice);
        
        //⑫ 概算受渡代金オブジェクトを生成し、プロパティに計算結果をセットする。
        
        //1)  概算受渡代金のインスタンスを生成する。
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = 
            new WEB3MutualFundEstimatedPrice();
        
        //2)  上記にて算出した結果を、概算受渡代金のプロパティにセットする。
        l_mfEstimatedPrice.setCommission(l_bdCommission.doubleValue());
        l_mfEstimatedPrice.setCommissionTax(l_bdCommissionTax.doubleValue());
        l_mfEstimatedPrice.setEstimatedPrice(l_bdEstimatedPrice.doubleValue());
        l_mfEstimatedPrice.setEstimatedQty(l_bdEstimatedQty.doubleValue());
        l_mfEstimatedPrice.setEstimatedTradeAmount(l_bdEstimatedTradeAmount.doubleValue());
        l_mfEstimatedPrice.setIncomeTax(l_bdIncomeTax.doubleValue());
        l_mfEstimatedPrice.setLocalTax(l_bdLocalTax.doubleValue());
        
        //3)  引数.銘柄.is外貨建投信() ＝ true の場合は以下の計算を行い、プロパティに値をセットする。
        if (l_mfProduct.isForeignCurencyFund())
        {
            log.debug("引数.銘柄.is外貨建投信() ＝ true の場合");
            
            BigDecimal l_bdForergnEstTradeAmount = null;
			
			//※為替レート： 引数.銘柄.get為替レートにて取得。
			ExchangeRateParams l_exchangeRateParams = 
				l_mfProduct.getExchangeRate();
            
            BigDecimal l_bdExchangeRate = null;
			BigDecimal l_bdExchangeCalcUnit = 
				new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
            
            //(a) 引数.取引区分 ＝ “買付” or “募集” の場合
            if (WEB3MFDealDivDef.BUY.equals(l_strDealDiv) || 
                WEB3MFDealDivDef.RECRUIT.equals(l_strDealDiv))
            {
                log.debug("(a) 引数.取引区分 ＝ “買付” or “募集” の場合");
				l_bdExchangeRate =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
            }
            //(b) 引数.取引区分 ＝ “解約” or “乗換” の場合
            else if (WEB3MFDealDivDef.SELL.equals(l_strDealDiv) || 
                        WEB3MFDealDivDef.SWITCHING.equals(l_strDealDiv))
            {
                log.debug("(b) 引数.取引区分 ＝ “解約” or “乗換” の場合");
				l_bdExchangeRate =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
            }

			//概算売買代金（外貨） ＝ 概算売買代金 × 為替レート（※）.為替レート計算単位 ÷ 為替レート
			//※小数点第3位以下は、切り捨て。
			l_bdForergnEstTradeAmount = 
				l_bdEstimatedTradeAmount.multiply(l_bdExchangeCalcUnit).divide(
						l_bdExchangeRate,2,BigDecimal.ROUND_FLOOR);

			l_mfEstimatedPrice.setForeignCurrencyEstimatedTradeAmount(
				l_bdForergnEstTradeAmount.doubleValue());
        }
        
        //引数.決済区分 ＝ “外貨” の場合。
		if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv))
		{        
			//4)    引数.指定区分 ＝ “金額” の場合は以下のとおりに、プロパティに値をセットする。
			if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
			{        
				//概算受渡代金 ＝ 引数.注文数量
				l_mfEstimatedPrice.setEstimatedPrice(l_dblOrderQuantity);
			}
			//5)    引数.指定区分 ＝ “口数” の場合は以下の計算を行い、プロパティに値をセットする。
			else if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
			{        
				//概算受渡代金
				BigDecimal l_bdEstPrice = null;
				
				//※為替レート： 引数.銘柄.get為替レートにて取得。
				ExchangeRateParams l_exchangeRateParams = 
					l_mfProduct.getExchangeRate();
	
				BigDecimal l_bdExchangeRate = null;
				BigDecimal l_bdExchangeCalcUnit = 
					new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
	            
				//(a) 引数.取引区分 ＝ “買付” or “募集” の場合
				if (WEB3MFDealDivDef.BUY.equals(l_strDealDiv) || 
					WEB3MFDealDivDef.RECRUIT.equals(l_strDealDiv))
				{
					l_bdExchangeRate =
						new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
				}
				//(b) 引数.取引区分 ＝ “解約” or “乗換” の場合
				else if (WEB3MFDealDivDef.SELL.equals(l_strDealDiv) || 
							WEB3MFDealDivDef.SWITCHING.equals(l_strDealDiv))
				{
					l_bdExchangeRate =
						new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
				}
	
				//概算受渡代金 ＝ 概算受渡代金（⑪の戻り値） × 
				//      為替レート（※）.為替レート計算単位 ÷ 為替レート
				//※小数点第3位以下は、切り捨て。
				l_bdEstPrice = 
					l_bdEstimatedPrice.multiply(l_bdExchangeCalcUnit).divide(
							l_bdExchangeRate,2,BigDecimal.ROUND_FLOOR);
	
				l_mfEstimatedPrice.setEstimatedPrice(l_bdEstPrice.doubleValue());
			}
		}
        
        log.exiting(STR_METHOD_NAME);
        return l_mfEstimatedPrice;
    }
    
    /**
     * (calc検索値)
     * 検索値を算出する。 <BR>
     * <BR>
     * 詳細は、計算式書参照。 <BR>
     * @@param l_maiAccount - 顧客
     * @@param l_mfProduct - 銘柄
     * @@param l_mfInstCommission - 会社別手数料
     * @@param l_bdOrderQuantity - 注文数量
     * @@param l_strDesignDiv - 指定区分
     * @@param l_strRequestDiv - 請求区分
     * @@param l_strAccountDiv - 口座区分
     * @@param l_datBizDate - 発注日
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcSearchValue(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        BigDecimal l_bdOrderQuantity, 
        String l_strDesignDiv, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
        Date l_datBizDate)
            throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcSearchValue(WEB3GentradeMainAccount," +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, " +
            "BigDecimal, String, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        BigDecimal l_bdSearchValue = null;
		
        //is外国投信
        boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
        //isFWF
        boolean l_blnIsFWF = l_mfProduct.isFWF();
        //is外貨建投信
        boolean l_blnIsForeignCurencyFund = l_mfProduct.isForeignCurencyFund();
        
		//(*1)基準価格
		BigDecimal l_bdConstantValue = null;
		BigDecimal l_bdConstantValueCalcUnit =
			new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit()));

		//買付の場合は買付基準価格。
		if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getConstantValue()));
		}
		//募集の場合は募集価格。
		else if (WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getRecruitConstantValue()));
		}
		//解約・乗換の場合は解約価格。
		else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
				WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getSellValue()));
		}
		log.debug("(*1)基準価格 = " + l_bdConstantValue);
                        
		//(*2)為替レート： 引数.銘柄.get為替レート()にて取得。
		//取得できない場合（引数.銘柄.is外貨建投信()＝falseの場合）は、
		//TTB、TTS、為替レート計算単位とも1として計算。
		BigDecimal l_bdTTB = new BigDecimal("1");
		BigDecimal l_bdTTS = new BigDecimal("1");
		BigDecimal l_bdCalcUnit = new BigDecimal("1");

		if (l_blnIsForeignCurencyFund)
		{
			ExchangeRateParams l_exchangeRateParams = 
				l_mfProduct.getExchangeRate();       

			l_bdTTB =
				new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
			l_bdTTS =
				new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
			l_bdCalcUnit =
				new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
		}

        //①@ 引数.会社別手数料.手数料区分 ＝ “単位数量当たり手数料単価” or “限度数量毎の手数料率” の場合

        if (WEB3MFCommissionDivDef.UNIT_COUNT_COMMISSION_PRICE.equals(
                l_mfInstCommission.getCommisionDiv()) || 
            WEB3MFCommissionDivDef.UNIT_ONE_COMMISSION_RATE.equals(
                l_mfInstCommission.getCommisionDiv()))
        {        
            log.debug("①@ 引数.会社別手数料.手数料区分 ＝ “単位数量当たり手数料単価” " +
                    "or “限度数量毎の手数料率” の場合");
            
            //1)  引数.指定区分 ＝ “口数指定” の場合
            if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
            {
                log.debug("1)引数.指定区分 ＝ “口数指定” の場合");
                //検索値 ＝ 引数.注文数量
                l_bdSearchValue = l_bdOrderQuantity;
            }    
            //2)  引数.指定区分 ＝ “金額指定” の場合
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
            {                
                log.debug("2)引数.指定区分 ＝ “金額指定” の場合");
                
                //(a) 引数.会社別手数料.取引区分 ＝ “買付” or “募集” の場合
                if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()) || 
                    WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
                {
                    log.debug("(a) 引数.会社別手数料.取引区分 ＝ “買付” or “募集” の場合");
                    
                    //(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合
                    if (l_blnIsForeignFund || l_blnIsFWF)
                    {
                        log.debug("(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合");
                        
                        //検索値 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷
                        //（ 引数.銘柄.基準価格(*1) × 為替レート(*2).TTS ÷ 為替レート.為替レート計算単位 ）
						//※小数点以下切り捨て。
						l_bdSearchValue =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTS).divide(l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP),
								0,BigDecimal.ROUND_FLOOR);
                    }                    
                    //(ii) 上記以外の場合
                    else
                    {                   
                        log.debug("上記以外の場合");
                        
                        //検索値 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷ 引数.銘柄.基準価格(*)
						//※小数点以下切り捨て。
						l_bdSearchValue =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue,0,BigDecimal.ROUND_FLOOR);
                    }
                }
                //(b) 引数.会社別手数料.取引区分 ＝ “解約” or “乗換” の場合
                else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
                        WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
                {
                    log.debug("(b) 引数.会社別手数料.取引区分 ＝ “解約” or “乗換” の場合");
                    
                    //(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合
                    if (l_blnIsForeignFund || l_blnIsFWF)
                    {    
                        log.debug("引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合");
                        
                        //検索値 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷
                        //（ 引数.銘柄.解約価格 × 為替レート(*).TTB ÷ 為替レート.為替レート計算単位 ）                        
						//※小数点以下切り捨て。
						l_bdSearchValue =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTB).divide(l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP),
								0,BigDecimal.ROUND_FLOOR);
                    }
                    //(ii)  上記以外の場合
                    else
                    {                        
                        log.debug("(ii) 上記以外の場合");
                        
                        //(*1) calc計算単位当たり所得税メソッドの手続き参照。
                        BigDecimal l_bdUnitIncomeTax = 
                            this.calcUnitIncomeTax(
                                l_mainAccount, 
                                l_mfProduct, 
                                l_mfInstCommission, 
                                l_strRequestDiv, 
                                l_strAccountDiv, 
                                l_datBizDate);
                        log.debug("計算単位当たり所得税額(*1) = " + l_bdUnitIncomeTax);
                        
						//(*2) calc計算単位当たり地方税メソッドの手続き参照。
						BigDecimal l_bdUnitLocalTax = 
                            this.calcUnitLocalTax(
                                l_mainAccount, 
                                l_mfProduct, 
                                l_mfInstCommission, 
                                l_strRequestDiv, 
                                l_strAccountDiv, 
                                l_datBizDate);
                        log.debug("計算単位当たり地方税額(*2) = " + l_bdUnitLocalTax);
                        
                        //検索値 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷
                        //（ 引数.銘柄.解約価格 － 計算単位当たり所得税額(*1) － 計算単位当たり地方税額(*2) ）
						//※小数点以下切り捨て。
                        l_bdSearchValue =
                        	l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.subtract(l_bdUnitIncomeTax).subtract(l_bdUnitLocalTax),
                        		0,BigDecimal.ROUND_FLOOR);
                    }
                }
            }
        }
        //②   引数.会社別手数料.手数料区分 ＝ “受渡代金（手数料率）” or “売買代金（手数料率）” の場合
        if (WEB3MFCommissionDivDef.PAYMENT_PRICE_COMMISSION_RATE.equals(
            l_mfInstCommission.getCommisionDiv()) || 
            WEB3MFCommissionDivDef.TRADE_PRICE_COMMISSION_RATE.equals(
                l_mfInstCommission.getCommisionDiv()))
        {
            log.debug("② 引数.会社別手数料.手数料区分 ＝ “受渡代金（手数料率）”" +
                    "or “売買代金（手数料率）” の場合");
            
            //1)  引数.指定区分 ＝ “金額指定” の場合
            if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
            {
                log.debug("1)  引数.指定区分 ＝ “金額指定” の場合");
                //検索値 ＝ 引数.注文数量
                l_bdSearchValue = l_bdOrderQuantity;
            }
            //2)  引数.指定区分 ＝ “口数指定” の場合
            else if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
            {
                log.debug("2)  引数.指定区分 ＝ “口数指定” の場合");
                
                //(a) 引数.会社別手数料.取引区分 ＝ “買付” or “募集” の場合
                if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()) || 
                    WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
                {
                    log.debug("(a) 引数.会社別手数料.取引区分 ＝ “買付” or “募集” の場合");
                        
                    //(i)  引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合
                    if (l_blnIsForeignFund || l_blnIsFWF)
                    {        
                        log.debug("(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合");
                        
                        //検索値 ＝ 引数.注文数量 ×
                        //（ 引数.投信銘柄.基準価格(*1) × 為替レート(*2).TTS ÷ 為替レート.為替レート計算単位 ） ÷
                        //引数.銘柄.基準価格計算単位
						//※小数点以下切り捨て。
						l_bdSearchValue =
							l_bdOrderQuantity.multiply(l_bdConstantValue).multiply(l_bdTTS).divide(
								l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).divide(
									l_bdConstantValueCalcUnit,
									0,BigDecimal.ROUND_FLOOR);
                    }
                    //(ii)  上記以外の場合
                    else
                    {
                        log.debug("(ii) 上記以外の場合");
                        
                        //検索値 ＝ 引数.注文数量 × 引数.投信銘柄.基準価格(*) ÷ 引数.銘柄.基準価格計算単位
						//※小数点以下切り捨て。
                        l_bdSearchValue = l_bdOrderQuantity.multiply(l_bdConstantValue).divide(
                        	l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_FLOOR);
                    }
                }
                //(b)   引数.会社別手数料.取引区分 ＝ “解約” or “乗換” の場合
                else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
                    WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
                {
                    log.debug("(b) 引数.会社別手数料.取引区分 ＝ “解約” or “乗換” の場合");
                    
                    //(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合
                    if (l_blnIsForeignFund || l_blnIsFWF)
                    {                    
                        log.debug("(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合");
                        
                        //検索値 ＝ 引数.注文数量 ×
                        //（ 引数.投信銘柄.解約価格 × 為替レート(*).TTB ÷ 為替レート.為替レート計算単位 ） ÷
                        //引数.銘柄.基準価格計算単位
						//※小数点以下切り捨て。
                        l_bdSearchValue =
                        	l_bdOrderQuantity.multiply(l_bdConstantValue).multiply(l_bdTTB).divide(
                        		l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).divide(
                        			l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_FLOOR);
                    }
                    //(ii)  上記以外の場合
                    else
                    {
                        log.debug("(ii) 上記以外の場合");
                        
                        //(*1) calc計算単位当たり所得税メソッドの手続き参照。
                        BigDecimal l_bdUnitIncomeTax = 
                            this.calcUnitIncomeTax(
                                l_mainAccount, 
                                l_mfProduct, 
                                l_mfInstCommission, 
                                l_strRequestDiv, 
                                l_strAccountDiv, 
                                l_datBizDate);
                        log.debug("計算単位当たり所得税額(*1) = " + l_bdUnitIncomeTax);
                        
						//(*2) calc計算単位当たり地方税メソッドの手続き参照。
                        BigDecimal l_bdUnitLocalTax = 
                            this.calcUnitLocalTax(
                                l_mainAccount, 
                                l_mfProduct, 
                                l_mfInstCommission, 
                                l_strRequestDiv, 
                                l_strAccountDiv, 
                                l_datBizDate);
                        log.debug("計算単位当たり地方税額(*2) = " + l_bdUnitLocalTax);
                        
                        //検索値 ＝ 引数.注文数量 ×
                        //（ 引数.投信銘柄.解約価格 － 計算単位当たり所得税額(*1) － 計算単位当たり地方税額(*2) ） ÷
                        //引数.銘柄.基準価格計算単位
						//※小数点以下切り捨て。
                        l_bdSearchValue = l_bdOrderQuantity.multiply(
							l_bdConstantValue.subtract(l_bdUnitIncomeTax).subtract(l_bdUnitLocalTax)).divide(
                        		l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_FLOOR);
                    }
                }
            }
        }

        log.debug("検索値 ＝ " + l_bdSearchValue);
        log.exiting(STR_METHOD_NAME);

        return l_bdSearchValue;
    }
    
    /**
     * (calc計算単位当たり所得税)
     * 計算単位当たりの所得税を算出する。 <BR>
     * <BR>
     * 詳細は、計算式書参照。 <BR>
     * @@param l_maiAccount - 顧客
     * @@param l_mfProduct - 銘柄
     * @@param l_mfInstCommission - 会社別手数料
     * @@param l_strRequestDiv - 請求区分
     * @@param l_strAccountDiv - 口座区分
     * @@param l_datBizDate - 発注日
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcUnitIncomeTax(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
        Date l_datBizDate) throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcUnitIncomeTax(WEB3GentradeMainAccount," +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }

		//  拡張投信注文マネージャを取得する
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //①@   以下の条件と一致する場合
        
        //（ 引数.請求区分 ＝ “買取請求” and 引数.銘柄.is株式型 ＝ true ） or 
        //  拡張投信注文マネージャ.is非課税顧客(引数.顧客) ＝ true
        
        if ((WEB3ClaimDivDef.BUY.equals(l_strRequestDiv) && l_mfProduct.isStockType()) ||
			l_orderManager.isTaxFreeAccount(l_mainAccount))
        {
            log.debug("引数.請求区分 ＝ “買取請求” and 引数.銘柄.is株式型 ＝ true " +
                "and 拡張投信注文マネージャ.is非課税顧客(引数.顧客) ＝ true");
            //計算単位当たり所得税 ＝ 0
            log.exiting(STR_METHOD_NAME);
            return new BigDecimal("0");
        }
        //②   上記以外の場合
        else
        {
            //(*1)calc取得単価メソッドの手続き参照。
            BigDecimal l_bdGetPrice = 
                this.calcGetPrice(l_mfProduct, l_mainAccount, l_strAccountDiv);
            
            log.debug("取得単価(*1) = " + l_bdGetPrice);
            
            //(*2)税率の取得参照。
            BigDecimal l_bdTaxRate = this.getTaxRate(
                INCOME_TAX, 
                l_mainAccount, 
                l_mfProduct, 
                l_datBizDate);
            
            log.debug("源泉徴収率（国税）(*2) = " + l_bdTaxRate);
            
			//計算単位当たり所得税 ＝ （ 引数.銘柄.解約価格 － 取得単価(*1) ） × 源泉徴収率（国税）(*2)
			BigDecimal l_bdSellValue = new BigDecimal(Double.toString(l_mfProduct.getSellValue()));
			
			BigDecimal l_bdCalcUnitIncomeTax = 
				l_bdSellValue.subtract(l_bdGetPrice).multiply(l_bdTaxRate);
            
            //※小数点以下切り捨て。計算結果 ＜ 0 の場合は、計算単位当たり所得税 ＝ 0とする。
			l_bdCalcUnitIncomeTax = l_bdCalcUnitIncomeTax.setScale(0,BigDecimal.ROUND_FLOOR);

            if (l_bdCalcUnitIncomeTax.compareTo(new BigDecimal("0")) < 0)
            {
                log.debug("計算結果 ＜ 0 の場合は、計算単位当たり所得税 ＝ 0");
                l_bdCalcUnitIncomeTax = new BigDecimal("0");
            }
            
            log.debug("計算単位当たり所得税 = " + l_bdCalcUnitIncomeTax);
            log.exiting(STR_METHOD_NAME);

            return l_bdCalcUnitIncomeTax;
        }        
    }
    
    /**
     * (calc計算単位当たり地方税)
     * 計算単位当たりの地方税を算出する。 <BR>
     * <BR>
     * 詳細は、計算式書参照。 <BR>
     * @@param l_maiAccount - 顧客
     * @@param l_mfProduct - 銘柄
     * @@param l_mfInstCommission - 会社別手数料
     * @@param l_strRequestDiv - 請求区分
     * @@param l_strAccountDiv - 口座区分
     * @@param l_datBizDate - 発注日
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcUnitLocalTax(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
        Date l_datBizDate) throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcUnitLocalTax(WEB3GentradeMainAccount," +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
		//  拡張投信注文マネージャを取得する
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //①@   以下の条件と一致する場合

        //（ 引数.請求区分 ＝ “買取請求” and 引数.銘柄.is株式型 ＝ true ） or
        //（ 引数.請求区分 ＝ “解約請求” and 引数.銘柄.is株式型 ＝ true and 引数.顧客.is法@人 ＝ true ） or
        //拡張投信注文マネージャ.is非課税顧客(引数.顧客) ＝ true ＝ true
        
        if ((WEB3ClaimDivDef.BUY.equals(l_strRequestDiv) && l_mfProduct.isStockType()) || 
            (WEB3ClaimDivDef.SELL.equals(l_strRequestDiv) && l_mfProduct.isStockType() && 
                l_mainAccount.isCorporation()) || l_orderManager.isTaxFreeAccount(l_mainAccount))
        {
            //計算単位当たり地方税 ＝ 0
            log.exiting(STR_METHOD_NAME);
            return new BigDecimal("0");
        }
        //②   上記以外の場合
        else
        {
            //(*1)calc取得単価メソッドの手続き参照。
            BigDecimal l_bdGetPrice = 
                this.calcGetPrice(l_mfProduct, l_mainAccount, l_strAccountDiv);
            
            log.debug("取得単価(*1) = " + l_bdGetPrice);
            
            //(*2) 税率の取得参照。
            BigDecimal l_bdTaxRate = this.getTaxRate(
                LOCAL_TAX, 
                l_mainAccount, 
                l_mfProduct, 
                l_datBizDate);
            
            log.debug("源泉徴収率（地方税）(*2) = " + l_bdTaxRate);
            
            //計算単位当たり地方税 ＝ （ 引数.銘柄.解約価格 － 取得単価(*1) ） × 源泉徴収率（地方税）(*2)            
			BigDecimal l_bdSellValue = new BigDecimal(Double.toString(l_mfProduct.getSellValue()));

			BigDecimal l_bdCalcUnitLocalTax = 
            	l_bdSellValue.subtract(l_bdGetPrice).multiply(l_bdTaxRate);
			
            //※小数点以下切り捨て。計算結果 ＜ 0 の場合は、計算単位当たり地方税 ＝ 0とする。
			l_bdCalcUnitLocalTax = l_bdCalcUnitLocalTax.setScale(0,BigDecimal.ROUND_FLOOR);

			if (l_bdCalcUnitLocalTax.compareTo(new BigDecimal("0")) < 0)
			{
				log.debug("計算結果 ＜ 0 の場合は、計算単位当たり地方税 ＝ 0");
				l_bdCalcUnitLocalTax = new BigDecimal("0");
            }

            log.debug("計算単位当たり地方税 ＝ " + l_bdCalcUnitLocalTax);
            log.exiting(STR_METHOD_NAME);
            
            return l_bdCalcUnitLocalTax;
        }        
    }
    
    /**
     * (calc取得単価)
     * 取得単価（簿価単価 or 元本単価）を算出する。 <BR>
     * <BR>
     * 詳細は、計算式書参照。 <BR>
     * @@param l_mfProduct - 銘柄
     * @@param l_mainAccount - 顧客
     * @@param l_strAccountDiv - 口座区分
     * @@return BigDecimal
     * @@throws WEB3BaseException
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcGetPrice(
        WEB3MutualFundProduct l_mfProduct, 
        WEB3GentradeMainAccount l_mainAccount, 
        String l_strAccountDiv)
            throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcGetPrice(" +
        "WEB3MutualFundProduct, WEB3GentradeMainAccount, String)";
    log.entering(STR_METHOD_NAME);
    
    if (l_mainAccount == null || l_mfProduct == null)
    {
        log.debug(" パラメータ値がNULL ");
         throw new WEB3BaseRuntimeException(
             WEB3ErrorCatalog.SYSTEM_ERROR_80017,
             this.getClass().getName() + "." + STR_METHOD_NAME);
    }        
    
    TaxTypeEnum l_taxType = null;
    if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strAccountDiv))
    {        
        log.debug("引数.口座区分 ＝ “一般”の場合");
        l_taxType = TaxTypeEnum.NORMAL;
    }
    else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strAccountDiv))
    {        
        log.debug("引数.口座区分 ＝ “特定”の場合");
        l_taxType = TaxTypeEnum.SPECIAL;
    }
    
    //①@ 保有資産テーブルから、以下の条件でレコードを取得する。

    //[取得条件]
    //口座ID ＝ 引数.顧客から取得した口座ID
    //補助口座ID ＝ 引数.顧客から取得した補助口座ID(*)
    //銘柄ID ＝ 引数.銘柄から取得した銘柄ID
    //税区分 ＝ 引数.口座区分

    //(*) 顧客が信用客の場合は、保証金口座の補助口座ID。現物客の場合は、預り金口座の補助口座ID。

    List l_lisRows = new ArrayList();
    
    String l_strWhere = 
        "account_id = ? and sub_account_id = ? and product_id = ? and tax_type = ?";
    
    long l_lngSubAccountId = 0; 
    try
    {
        if (l_mainAccount.isMarginAccountEstablished(
            WEB3GentradeRepaymentDivDef.DEFAULT))
        {
            l_lngSubAccountId = l_mainAccount.getSubAccount(
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT).getSubAccountId();
        }
        else
        {
            l_lngSubAccountId = l_mainAccount.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT).getSubAccountId();   
        }
        log.debug("補助口座ID ＝ " + l_lngSubAccountId);
    }
    catch (NotFoundException l_ex)
    {
        log.error("___NotFoundException___" , l_ex);
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
    }
    Object[] l_bindVars = {
        new Long(l_mainAccount.getAccountId()),
        new Long(l_lngSubAccountId),
        new Long(l_mfProduct.getProductId()), 
        l_taxType};

    try
    {
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_lisRows = l_queryProcessor.doFindAllQuery(
            AssetRow.TYPE,
            l_strWhere,
            l_bindVars);
    
    }
    catch (DataNetworkException l_ex)
    {
        log.error("DBへのアクセスに失敗しました when search Asset");
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
    }
    catch (DataQueryException l_ex)
    {
        log.error("DBへのアクセスに失敗しました when search Asset");
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);    
    }
    
    if(l_lisRows == null || l_lisRows.size() == 0)
    {
        //－検索結果の件数=0件の場合、（データ不整合）の例外をスローする。
        log.debug("テーブルに該当するデータがありません。");
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME
        );
    }
    
    //②   取得した保有資産テーブル.簿価（簿価単価計算用）を返却する。
    AssetRow l_assetRow = (AssetRow)l_lisRows.get(0);
    BigDecimal l_bdUnitPrice =
    	new BigDecimal(Double.toString(l_assetRow.getBookValue()));
	
    log.debug("取得単価 ＝" + l_bdUnitPrice);
    log.exiting(STR_METHOD_NAME);

    return l_bdUnitPrice;        
    }

	/**
	 * (calc概算売買口数)
	 * 概算売買口数の計算を行う。 <BR>
	 * <BR>
	 * 詳細は、計算式書参照。<BR>
	 * @@param l_maiAccount - 顧客
	 * @@param l_mfProduct - 銘柄
	 * @@param l_mfInstCommission - 会社別手数料
	 * @@param l_strDesignDiv - 指定区分
	 * @@param l_strRequestDiv - 請求区分
	 * @@param l_strAccountDiv - 口座区分
	 * @@param l_bdOrderQuantity - 注文数量
	 * @@param l_bdCommissionPriceRate - 手数料単価、率
	 * @@param l_datBizDate - 発注日
	 * @@return BigDecimal
	 * @@roseuid 40A3495A00B7
	 */
	protected BigDecimal calcEstimatedQty(
		WEB3GentradeMainAccount l_mainAccount, 
		WEB3MutualFundProduct l_mfProduct, 
		WEB3MutualFundInstCommission l_mfInstCommission, 
		String l_strDesignDiv, 
		String l_strRequestDiv, 
		String l_strAccountDiv, 
		BigDecimal l_bdOrderQuantity, 
		BigDecimal l_bdCommissionPriceRate, 
		Date l_datBizDate)
			throws WEB3BaseException
	{        
		String STR_METHOD_NAME = "calcEstimatedQty(WEB3GentradeMainAccount, " +
			"WEB3MutualFundProduct, WEB3MutualFundInstCommission, String, " +
			"String, String, BigDecimal, BigDecimal, Date)";
		log.entering(STR_METHOD_NAME);
        
		if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
		{
			log.debug(" パラメータ値がNULL ");
			 throw new WEB3BaseRuntimeException(
				 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				 this.getClass().getName() + "." + STR_METHOD_NAME);
		}                
        
		BigDecimal l_bdEstimatedQty = null;

		//is外国投信
		boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
		//isFWF
		boolean l_blnIsFWF = l_mfProduct.isFWF();
		//is外貨建投信
		boolean l_blnIsForeignCurencyFund = l_mfProduct.isForeignCurencyFund();
        
		//①@   引数.指定区分 ＝ “口数指定”の場合
		if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
		{
			log.debug("①@引数.指定区分 ＝ “口数指定”の場合");
			//概算売買口数 ＝ 引数.注文数量
			l_bdEstimatedQty = l_bdOrderQuantity;
		}        

		//②   引数.指定区分 ＝ “金額指定”の場合
		else if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
		{            
			log.debug("②引数.指定区分 ＝ “金額指定”の場合");
            
			//(*1)基準価格
			BigDecimal l_bdConstantValue = null;
			BigDecimal l_bdConstantValueCalcUnit =
				new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit()));

			//買付の場合は買付基準価格。
			if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()))
			{
				l_bdConstantValue =
					new BigDecimal(Double.toString(l_mfProduct.getConstantValue()));
			}
			//募集の場合は募集価格。
			else if (WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
			{
				l_bdConstantValue =
					new BigDecimal(Double.toString(l_mfProduct.getRecruitConstantValue()));
			}
			//解約・乗換の場合は解約価格。
			else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
					WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
			{
				l_bdConstantValue =
					new BigDecimal(Double.toString(l_mfProduct.getSellValue()));
			}
			log.debug("(*1)基準価格 = " + l_bdConstantValue);

			//(*2)為替レート： 引数.銘柄.get為替レート()にて取得。
			//取得できない場合（引数.銘柄.is外貨建投信()＝falseの場合）は、TTB、TTS、為替レート計算単位とも1として計算。
			BigDecimal l_bdTTB = new BigDecimal("1");
			BigDecimal l_bdTTS = new BigDecimal("1");
			BigDecimal l_bdCalcUnit = new BigDecimal("1");

			if (l_blnIsForeignCurencyFund)
			{
				ExchangeRateParams l_exchangeRateParams = 
					l_mfProduct.getExchangeRate();       

				l_bdTTB =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
				l_bdTTS =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
				l_bdCalcUnit =
					new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
			}

			//(*3) 税率の取得参照。
			BigDecimal l_bdTaxRate = this.getTaxRate(
				TAX_RATE, 
				l_mainAccount, 
				l_mfProduct, 
				l_datBizDate);
                        
			log.debug("(*3)税率 = " + l_bdTaxRate);
            
			//1)  引数.会社別手数料.取引区分 ＝ “買付” or “募集” の場合
			if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()) || 
				WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
			{            
				log.debug("1)引数.会社別手数料.取引区分 ＝ “買付” or “募集” の場合");
                
				//(a) 引数.会社別手数料.手数料区分 ＝ “単位数量当たり手数料単価”の場合
				if (WEB3MFCommissionDivDef.UNIT_COUNT_COMMISSION_PRICE.equals(
					l_mfInstCommission.getCommisionDiv()))
				{                    
					log.debug("(a)引数.会社別手数料.手数料区分 ＝ “単位数量当たり手数料単価”の場合");
                    
					//(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合
					if (l_blnIsForeignFund || l_blnIsFWF)
					{
						log.debug("(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合");
                        
						//概算売買口数 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷
						//（ 引数.銘柄.基準価格(*1) × 為替レート(*2).TTS ÷ 為替レート.為替レート計算単位 
						//＋ 引数.手数料単価、率 × （ １ ＋ 消費税率(*3) ） ）
						//※小数点以下切り捨て。
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTS).divide(
									l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).add(
										l_bdCommissionPriceRate.multiply(
											new BigDecimal("1").add(l_bdTaxRate))),
								0,BigDecimal.ROUND_FLOOR);
					}
					//(ii)  上記以外の場合
					else
					{            
						log.debug("(ii) 上記以外の場合");
                        
						//概算売買口数 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷
						//（ 引数.銘柄.基準価格(*1) ＋ 引数.手数料単価、率 × 
						//（ １ ＋ 消費税率(*2) ） ）
						//※小数点以下切り捨て。
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.add(l_bdCommissionPriceRate.multiply(
									new BigDecimal("1").add(l_bdTaxRate))),
								0,BigDecimal.ROUND_FLOOR);
					}
				}
				//(b)   引数.会社別手数料.手数料区分 ≠ “単位数量当たり手数料単価”の場合
				else
				{
					log.debug("(b)引数.会社別手数料.手数料区分 ≠ “単位数量当たり手数料単価”の場合");
                    
					//(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合
					if (l_blnIsForeignFund || l_blnIsFWF)
					{    
						log.debug("(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合");
                        
						//概算売買口数 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷
						//（ （ 引数.銘柄.基準価格(*1) × 為替レート(*2).TTS ÷ 
						//  為替レート.為替レート計算単位 ） ×
						//（ １ ＋ 引数.手数料単価、率 ×（ １ ＋ 消費税率(*3) ） ） ）
						//※小数点以下切り捨て。
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTS).divide(
									l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).multiply(
										new BigDecimal("1").add(l_bdCommissionPriceRate.multiply(
											new BigDecimal("1").add(l_bdTaxRate)))),
								0,BigDecimal.ROUND_FLOOR);
					}
					//(ii)  上記以外の場合
					else
					{
						log.debug("(ii) 上記以外の場合");
                        
						//概算売買口数 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷ 
						//（ 引数.銘柄.基準価格(*1) × （ １ ＋ 引数.手数料単価、率 × 
						//（ １ ＋ 消費税率(*2) ） ） ）
						//※小数点以下切り捨て。
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(new BigDecimal("1").add(
									l_bdCommissionPriceRate.multiply(
										new BigDecimal("1").add(l_bdTaxRate)))),
								0,BigDecimal.ROUND_FLOOR);
					}
				}

			}
			//2)    引数.会社別手数料.取引区分 ＝ “解約” or “乗換” の場合
			else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
				WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
			{    
				log.debug("2)引数.会社別手数料.取引区分 ＝ “解約” or “乗換” の場合");

				//(a) 引数.会社別手数料.手数料区分 ＝ “単位数量当たり手数料単価”の場合
				if (WEB3MFCommissionDivDef.UNIT_COUNT_COMMISSION_PRICE.equals(
					l_mfInstCommission.getCommisionDiv()))
				{
					log.debug("(a) 引数.会社別手数料.手数料区分 ＝ “単位数量当たり手数料単価”の場合");
                    
					//(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合
					if (l_blnIsForeignFund || l_blnIsFWF)
					{
						log.debug("(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合");
                        
						//概算売買口数 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷
						//（ 引数.銘柄.解約価格 × 為替レート(*1).TTB ÷ 為替レート.為替レート計算単位 －
						//引数.手数料単価、率 × （ １ ＋ 消費税率(*2) ） ）
						//※小数点以下切り捨て。
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTB).divide(
									l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).subtract(
										l_bdCommissionPriceRate.multiply(
											new BigDecimal("1").add(l_bdTaxRate))),
								0,BigDecimal.ROUND_FLOOR);
					}
					//(ii)  上記以外の場合
					else
					{
						log.debug("(ii) 上記以外の場合");
						//(*1) calc計算単位当たり所得税メソッドの手続き参照。
						BigDecimal l_bdUnitIncomeTax = 
							this.calcUnitIncomeTax(
								l_mainAccount, 
								l_mfProduct, 
								l_mfInstCommission, 
								l_strRequestDiv, 
								l_strAccountDiv, 
								l_datBizDate);
                        
						log.debug("計算単位当たり所得税(*1) = " + l_bdUnitIncomeTax);
                         
						//(*2) calc計算単位当たり地方税メソッドの手続き参照。
						BigDecimal l_bdUnitLocalTax = 
							this.calcUnitLocalTax(
								l_mainAccount, 
								l_mfProduct, 
								l_mfInstCommission, 
								l_strRequestDiv, 
								l_strAccountDiv, 
								l_datBizDate);
                        
						log.debug("計算単位当たり地方税(*2) = " + l_bdUnitLocalTax);
                        
						//概算売買口数 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷
						//（ 引数.銘柄.解約価格 － 計算単位当たり所得税(*1) － 計算単位当たり地方税(*2) －
						//引数.手数料単価、率 × （ １ ＋ 消費税率(*3)  ） ）            
						//※小数点以下切り捨て。
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.subtract(l_bdUnitIncomeTax).subtract(
									l_bdUnitLocalTax).subtract(l_bdCommissionPriceRate.multiply(
										new BigDecimal("1").add(l_bdTaxRate))),
								0,BigDecimal.ROUND_FLOOR);
					}
				}
				//(b) 引数.会社別手数料.手数料区分 ≠ “単位数量当たり手数料単価”の場合
				else
				{
					log.debug("(b) 引数.会社別手数料.手数料区分 ≠ “単位数量当たり手数料単価”の場合");
                    
					//(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合
					if (l_blnIsForeignFund || l_blnIsFWF)
					{
						log.debug("(i) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合");
                        
						//概算売買口数 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷
						//（ （ 引数.銘柄.解約価格 × 為替レート(*1).TTB ÷ 為替レート.為替レート計算単位 ） ×
						//（ １ － 引数.手数料単価、率 × （ １ ＋ 消費税率(*2) ） ） ）
						//※小数点以下切り捨て。
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTB).divide(
									l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).multiply(
										new BigDecimal("1").subtract(l_bdCommissionPriceRate.multiply(
											new BigDecimal("1").add(l_bdTaxRate)))),
								0,BigDecimal.ROUND_FLOOR);
					}
					//(ii)  上記以外の場合
					else
					{        
						log.debug("(ii) 上記以外の場合");
						//(*1) calc計算単位当たり所得税メソッドの手続き参照。
						BigDecimal l_bdUnitIncomeTax = 
							this.calcUnitIncomeTax(
								l_mainAccount, 
								l_mfProduct, 
								l_mfInstCommission, 
								l_strRequestDiv, 
								l_strAccountDiv, 
								l_datBizDate);
                        
						log.debug("計算単位当たり所得税(*1) = " + l_bdUnitIncomeTax);
                         
						//(*2) calc計算単位当たり地方税メソッドの手続き参照。
						BigDecimal l_bdUnitLocalTax = 
							this.calcUnitLocalTax(
								l_mainAccount, 
								l_mfProduct, 
								l_mfInstCommission, 
								l_strRequestDiv, 
								l_strAccountDiv, 
								l_datBizDate);
                        
						log.debug("計算単位当たり地方税(*2) = " + l_bdUnitLocalTax);
                        
						//概算売買口数 ＝ 引数.注文数量 × 引数.銘柄.基準価格計算単位 ÷
						//（ （ 引数.銘柄.解約価格 － 計算単位当たり所得税(*1) － 計算単位当たり地方税(*2) ） ×
						//（ １ － 引数.手数料単価、率 × （ １ ＋ 消費税率(*3)  ） ） ）
						//※小数点以下切り捨て。
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.subtract(l_bdUnitIncomeTax).subtract(
									l_bdUnitLocalTax).multiply(new BigDecimal("1").subtract(
										l_bdCommissionPriceRate.multiply(
											new BigDecimal("1").add(l_bdTaxRate)))),
								0,BigDecimal.ROUND_FLOOR);
					}
				}
			}
		}

		log.debug("概算売買口数 ＝" + l_bdEstimatedQty);
		log.exiting(STR_METHOD_NAME);        
		return l_bdEstimatedQty;
	}

    /**
     * (calc概算売買代金)
     * 概算売買代金を算出する。 <BR>
     * <BR>
     * 詳細は、計算式書参照。 <BR>
     * @@param l_mfProduct - 銘柄
     * @@param l_mfInstCommission - 会社別手数料
     * @@param l_bdEstimatedQty - 概算売買口数
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcEstimatedTradeAmount(
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        BigDecimal l_bdEstimatedQty)
            throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcEstimatedTradeAmount(" +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, BigDecimal)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        BigDecimal l_bdEstimatedTradeAmount = null;
		
        //is外国投信
        boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
        //isFWF
        boolean l_blnIsFWF = l_mfProduct.isFWF();
        //is外貨建投信
        boolean l_blnIsForeignCurencyFund = l_mfProduct.isForeignCurencyFund();

		//(*1)基準価格
		BigDecimal l_bdConstantValue = null;
		BigDecimal l_bdConstantValueCalcUnit =
			new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit()));

		//買付の場合は買付基準価格。
		if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getConstantValue()));
		}
		//募集の場合は募集価格。
		else if (WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getRecruitConstantValue()));
		}
		//解約・乗換の場合は解約価格。
		else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
				WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getSellValue()));
		}

		//(*2)為替レート： 引数.銘柄.get為替レート()にて取得。
		//取得できない場合（引数.銘柄.is外貨建投信()＝falseの場合）は、TTB、TTS、為替レート計算単位とも1として計算。
		BigDecimal l_bdTTB = new BigDecimal("1");
		BigDecimal l_bdTTS = new BigDecimal("1");
		BigDecimal l_bdCalcUnit = new BigDecimal("1");

		if (l_blnIsForeignCurencyFund)
		{
			ExchangeRateParams l_exchangeRateParams = 
				l_mfProduct.getExchangeRate();       

			l_bdTTB =
				new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
			l_bdTTS =
				new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
			l_bdCalcUnit =
				new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
		}

        //①@ 引数.会社別手数料.取引区分 ＝ “買付”or“募集” の場合
        if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()) || 
            WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
        {
            log.debug("①@ 引数.会社別手数料.取引区分 ＝ “買付”or“募集” の場合");
            
            //1) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合
            if (l_blnIsForeignFund || l_blnIsFWF)
            {   
                log.debug("1) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合");
                
                //概算売買代金 ＝ 引数.概算売買口数 × 引数.銘柄.基準価格(*1) × 
                //為替レート(*2).TTS ÷ 為替レート.為替レート計算単位 ÷
                //引数.銘柄.基準価格計算単位        
				//※小数点以下は第1位で四捨五入。
				l_bdEstimatedTradeAmount =
					l_bdEstimatedQty.multiply(l_bdConstantValue).multiply(l_bdTTS).divide(
						l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).divide(
							l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_HALF_UP);
            }
            //2)  上記以外の場合
            else
            {
                log.debug("2)  上記以外の場合");
                
                //概算売買代金 ＝ 引数.概算売買口数 × 引数.銘柄.基準価格(*) ÷ 引数.銘柄.基準価格計算単位
				//※小数点以下は第1位で四捨五入。
				l_bdEstimatedTradeAmount =
					l_bdEstimatedQty.multiply(l_bdConstantValue).divide(
							l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_HALF_UP);
            }
        }
        //② 引数.会社別手数料.取引区分 ＝ “解約”or“乗換” の場合
        if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
            WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
        {
            log.debug("② 引数.会社別手数料.取引区分 ＝ “解約”or“乗換” の場合");

            //1)  引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合
            if (l_blnIsForeignFund || l_blnIsFWF)
            {
                log.debug("1) 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合");
                
                //概算売買代金 ＝ 引数.概算売買口数 ×
                //引数.銘柄.解約価格 × 為替レート(*).TTB ÷ 為替レート.為替レート計算単位 ÷
                //引数.銘柄.基準価格計算単位        
				//※小数点以下は第1位で四捨五入。
				l_bdEstimatedTradeAmount =
					l_bdEstimatedQty.multiply(l_bdConstantValue).multiply(l_bdTTB).divide(
						l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).divide(
							l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_HALF_UP);
            }
            //2)  上記以外の場合
            else
            {    
                log.debug("2)  上記以外の場合");
                
                //概算売買代金 ＝ 引数.概算売買口数 × 引数.銘柄.解約価格 ÷ 引数.銘柄.基準価格計算単位
				//※小数点以下は第1位で四捨五入。
				l_bdEstimatedTradeAmount =
					l_bdEstimatedQty.multiply(l_bdConstantValue).divide(
							l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_HALF_UP);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_bdEstimatedTradeAmount;
    }
    
    /**
     * (calc手数料)
     * 手数料を算出する。 <BR>
     * <BR>
     * 詳細は、計算式書参照。 <BR>
     * @@param l_maiAccount - 顧客
     * @@param l_mfInstCommission - 会社別手数料
     * @@param l_mfProduct - 銘柄
     * @@param l_swtProduct - 乗換先の銘柄
     * @@param l_bdEstimatedQty - 概算売買口数
     * @@param l_bdEstimatedTradeAmount - 概算売買代金
     * @@param l_bdCommissionPriceRate - 手数料単価、率
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcCommission(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_swtProduct, 
        BigDecimal l_bdEstimatedQty, 
		BigDecimal l_bdEstimatedTradeAmount, 
		BigDecimal l_bdCommissionPriceRate)
            throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcCommission(WEB3GentradeMainAccount, " +
            "WEB3MutualFundInstCommission, BigDecimal, BigDecimal, BigDecimal)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfInstCommission == null || l_mfProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		//  拡張投信注文マネージャを取得する
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        BigDecimal l_bdCommission = null;
        
        WEB3MutualFundProduct l_wkMfProduct = null;
        //①@ 引数.乗換先の銘柄がnullの場合
        if (l_swtProduct == null)
        {
            log.debug("①@ 引数.乗換先の銘柄がnullの場合");
        	l_wkMfProduct = l_mfProduct;
        }
        //② 引数.乗換先の銘柄がnull以外の場合
        else
        {
        	l_wkMfProduct = l_swtProduct;
            log.debug("② 引数.乗換先の銘柄がnull以外の場合");
        }
        
        //③ 拡張投信注文マネージャ.is手数料無料顧客(引数.顧客,引数.銘柄) ＝ true の場合
        if (l_orderManager.isCommissionFreeAccount(l_mainAccount, l_wkMfProduct))
        {
            log.debug("③ 拡張投信注文マネージャ.is手数料無料顧客(引数.顧客,引数.銘柄) ＝ true の場合");
            //手数料 ＝ 0
            l_bdCommission = new BigDecimal("0");
            
        }
        //④ 拡張投信注文マネージャ.is手数料無料顧客(引数.顧客,引数.銘柄) ＝ false の場合
        else
        {
            log.debug("④ 拡張投信注文マネージャ.is手数料無料顧客(引数.顧客,引数.銘柄) ＝ false の場合");
            //1)  引数.会社別手数料.手数料区分 ＝ “単位数量当たり手数料単価” の場合
            
            if (WEB3MFCommissionDivDef.UNIT_COUNT_COMMISSION_PRICE.equals(
                l_mfInstCommission.getCommisionDiv()))
            {                
                log.debug("1)引数.会社別手数料.手数料区分 ＝ “単位数量当たり手数料単価” の場合");
                //手数料 ＝ 引数.概算売買口数 × 引数.手数料単価、率 ÷ 引数.銘柄.基準価格計算単位        
				//※小数点以下は第1位で四捨五入。
                l_bdCommission =
                	l_bdEstimatedQty.multiply(l_bdCommissionPriceRate).divide(
						new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit())),
						0,BigDecimal.ROUND_HALF_UP);
            }
            //2) 引数.会社別手数料.手数料区分 ≠ “単位数量当たり手数料単価” の場合
            else
            {        
                log.debug("2) 引数.会社別手数料.手数料区分 ≠ “単位数量当たり手数料単価” の場合");
                //手数料 ＝ 引数.概算売買代金 × 引数.手数料単価、率        
				//※小数点以下は第1位で四捨五入。
				l_bdCommission =
					l_bdEstimatedTradeAmount.multiply(l_bdCommissionPriceRate).setScale(
						0,BigDecimal.ROUND_HALF_UP);
            }
        }        

        log.debug("手数料 ＝ " + l_bdCommission);
        log.exiting(STR_METHOD_NAME);        
        return l_bdCommission;
    }
    
    /**
     * (calc手数料消費税)
     * 手数料消費税を算出する。 <BR>
     * <BR>
     * 詳細は、計算式書参照。 <BR>
     * @@param l_mainAccount - 顧客オブジェクト
     * @@param l_bdCommission - 手数料
     * @@param l_datBizDate - 発注日
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcCommissionTax(
        WEB3GentradeMainAccount l_mainAccount, 
        BigDecimal l_bdCommission, 
        Date l_datBizDate) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcCommissionTax(" +
            "WEB3GentradeMainAccount, BigDecimal, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //(*3) 税率の取得参照。
        BigDecimal l_bdTaxRate = this.getTaxRate(
            TAX_RATE, 
            l_mainAccount, 
            null, 
            l_datBizDate);
        
        //手数料消費税 ＝ 引数.手数料 × 消費税率(*)
		BigDecimal l_bdCommissionTax =
        	l_bdCommission.multiply(l_bdTaxRate);
		//※小数点以下は第1位で四捨五入。
		l_bdCommissionTax =
			l_bdCommissionTax.setScale(0,BigDecimal.ROUND_HALF_UP);

        log.exiting(STR_METHOD_NAME);        
        return l_bdCommissionTax;
    }
    
    /**
     * (calc所得税)
     * 所得税を算出する。 <BR>
     * <BR>
     * 詳細は、計算式書参照。 <BR>
     * @@param l_maiAccount - 顧客
     * @@param l_mfProduct - 銘柄
     * @@param l_mfInstCommission - 会社別手数料
     * @@param l_strRequestDiv - 請求区分
     * @@param l_strAccountDiv - 口座区分
     * @@param l_bdEstimatedQty - 概算売買口数
     * @@param l_datBizDate - 発注日
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcIncomeTax(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
		BigDecimal l_bdEstimatedQty, 
        Date l_datBizDate) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcIncomeTax(WEB3GentradeMainAccount, " +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, " +
            "String, String, BigDecimal, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		//  拡張投信注文マネージャを取得する
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

		BigDecimal l_bdIncomeTax = new BigDecimal("0");
                
        //※会社別手数料.取引区分 ＝ “解約” or “乗換” and 
        //国内投信（銘柄.is外国投信()＝false and 銘柄.isFWF()＝false） の場合のみ、算出。
        
        if ((WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
            WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv())) && 
            (!l_mfProduct.isForeignFund() && !l_mfProduct.isFWF()))
        {
            
            //①@ 以下の条件と一致する場合
    
            //（ 引数.請求区分 ＝ “買取請求”and 引数.銘柄.is株式型 ＝ true ） or
            //拡張投信注文マネージャ.is非課税顧客(引数.顧客) ＝ true
            if ((WEB3ClaimDivDef.BUY.equals(l_strRequestDiv) && l_mfProduct.isStockType()) || 
                l_orderManager.isTaxFreeAccount(l_mainAccount))
            {
                //所得税 ＝ 0
                l_bdIncomeTax = new BigDecimal("0");
                
            }
            //②   上記以外の場合
            else
            {
				//(*) calc計算単位当たり所得税メソッドの手続き参照。
				BigDecimal l_bdUnitIncomeTax = 
					this.calcUnitIncomeTax(
						l_mainAccount, 
						l_mfProduct, 
						l_mfInstCommission, 
						l_strRequestDiv, 
						l_strAccountDiv, 
						l_datBizDate);
                        
				log.debug("計算単位当たり所得税額(*) = " + l_bdUnitIncomeTax);

                //所得税 ＝ 引数.概算売買口数 × 計算単位当たり所得税(*) ÷ 引数.銘柄.基準価格計算単位            
				//※小数点以下切り捨て。
                l_bdIncomeTax =
					l_bdEstimatedQty.multiply(l_bdUnitIncomeTax).divide(
						new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit())),
						0,BigDecimal.ROUND_FLOOR);

				//※計算結果 ＜ 0 の場合は、所得税 ＝ 0とする。
                if (l_bdIncomeTax.compareTo(new BigDecimal("0")) < 0)
                {
                    l_bdIncomeTax = new BigDecimal("0");
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);        
        return l_bdIncomeTax;
    }
    
    /**
     * (calc地方税)
     * 地方税を算出する。 <BR>
     * <BR>
     * 詳細は、計算式書参照。 <BR>
     * @@param l_maiAccount - 顧客
     * @@param l_mfProduct - 銘柄
     * @@param l_mfInstCommission - 会社別手数料
     * @@param l_strRequestDiv - 請求区分
     * @@param l_strAccountDiv - 口座区分
     * @@param l_bdEstimatedQty - 概算売買口数
     * @@param l_datBizDate - 発注日
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcLocalTax(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
        BigDecimal l_bdEstimatedQty, 
        Date l_datBizDate)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcLocalTax(WEB3GentradeMainAccount, " +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, " +
            "String, String, BigDecimal, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
		//  拡張投信注文マネージャを取得する
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        BigDecimal l_bdLocalTax = new BigDecimal("0");

        //※会社別手数料.取引区分 ＝ “解約” or “乗換” and 
        //国内投信（銘柄.is外国投信()＝false and 銘柄.isFWF()＝false） の場合のみ、算出。
        
        if ((WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
            WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv())) && 
            (!l_mfProduct.isForeignFund() && !l_mfProduct.isFWF()))
        {
        
            //①@ 以下の条件と一致する場合
    
            //（ 引数.請求区分 ＝ “買取請求”and 引数.銘柄.is株式型 ＝ true ） or
            //（ 引数.請求区分 ＝ “解約請求”and 引数.銘柄.is株式型 ＝ true and 引数.顧客.is法@人 ＝ true ） or
            //拡張投信注文マネージャ.is非課税顧客(引数.顧客) ＝ true
    
            if ((WEB3ClaimDivDef.BUY.equals(l_strRequestDiv) && l_mfProduct.isStockType()) || 
                (WEB3ClaimDivDef.SELL.equals(l_strRequestDiv) && l_mfProduct.isStockType() && l_mainAccount.isCorporation()) ||
                l_orderManager.isTaxFreeAccount(l_mainAccount))
            {
                //地方税 ＝ 0
                l_bdLocalTax = new BigDecimal("0");
            }
            //②   上記以外の場合
            else
            {
				//(*) calc計算単位当たり地方税メソッドの手続き参照。
				BigDecimal l_bdUnitLocalTax = 
					this.calcUnitLocalTax(
						l_mainAccount, 
						l_mfProduct, 
						l_mfInstCommission, 
						l_strRequestDiv, 
						l_strAccountDiv, 
						l_datBizDate);
                        
				log.debug("計算単位当たり地方税額(*) = " + l_bdUnitLocalTax);

                //地方税 ＝ 引数.概算売買口数 × 計算単位当たり地方税(*) ÷ 引数.銘柄.基準価格計算単位
				//※小数点以下切り捨て。
                l_bdLocalTax =
                	l_bdEstimatedQty.multiply(l_bdUnitLocalTax).divide(
						new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit())),
						0,BigDecimal.ROUND_FLOOR);

				//※計算結果 ＜ 0 の場合は、地方税 ＝ 0とする。
				if (l_bdLocalTax.compareTo(new BigDecimal("0")) < 0)
				{
					l_bdLocalTax = new BigDecimal("0");
				}
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_bdLocalTax;
    }
    
    /**
     * (calc概算受渡代金)
     * 概算受渡代金の計算を行う。 <BR>
     * <BR>
     * 詳細は、計算式書参照。<BR>
     * @@param l_mfInstCommission - 会社別手数料
     * @@param l_strDesignDiv - 指定区分
     * @@param l_bdOrderQuantity - 注文数量
     * @@param l_bdEstimatedTradeAmount - 概算売買代金
     * @@param l_bdCommission - 手数料
     * @@param l_bdConsumptionTax - 消費税
     * @@param l_bdIncomeTax - 所得税
     * @@param l_bdLocalTax - 地方税
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcEstimatedPrice(
        WEB3MutualFundInstCommission l_mfInstCommission, 
        String l_strDesignDiv, 
        BigDecimal l_bdOrderQuantity, 
		BigDecimal l_bdEstimatedTradeAmount, 
		BigDecimal l_bdCommission, 
		BigDecimal l_bdConsumptionTax, 
		BigDecimal l_bdIncomeTax, 
		BigDecimal l_bdLocalTax)
    {        
        String STR_METHOD_NAME = "calcEstimatedPrice(WEB3MutualFundInstCommission," +
                "String, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mfInstCommission == null)
        {
            log.debug(" パラメータ値がNULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        BigDecimal l_bdEstimatedPrice = null;
        
        //①@   引数.指定区分 ＝ “金額指定”の場合
        if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
        {
            log.debug("引数.指定区分 ＝ “金額指定”の場合");
            
            //概算受渡代金 ＝ 引数.注文数量
            l_bdEstimatedPrice = l_bdOrderQuantity;
        }
        //②   引数.指定区分 ＝ “口数指定”の場合
        else if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
        {
            log.debug("引数.指定区分 ＝ “口数指定”の場合");
            
            //1)  引数.会社別手数料.取引区分 ＝ “買付” or “募集” の場合
            if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()) || 
                WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
            {
                log.debug("引数.会社別手数料.取引区分 ＝ “買付” or “募集” の場合");
                
                //概算受渡代金 ＝ 引数.概算売買代金 ＋ 引数.手数料 ＋ 引数.消費税
                l_bdEstimatedPrice =
                	l_bdEstimatedTradeAmount.add(l_bdCommission).add(l_bdConsumptionTax);
            }
            
            //2)  引数.会社別手数料.取引区分 ＝ “解約” or “乗換” の場合
            else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
                WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
            {    
                log.debug("引数.会社別手数料.取引区分 ＝ “解約” or “乗換” の場合");
                
                //概算受渡代金 ＝ 引数.概算売買代金 － 引数.手数料 － 引数.消費税 － 引数.所得税 － 引数.地方税
				l_bdEstimatedPrice =
					l_bdEstimatedTradeAmount.subtract(l_bdCommission).subtract(
						l_bdConsumptionTax).subtract(l_bdIncomeTax).subtract(l_bdLocalTax);
                    
                //※計算結果 ＜ 0 の場合は、概算受渡代金 ＝ 0 とする。
                if (l_bdEstimatedPrice.compareTo(new BigDecimal("0")) < 0)
                {
                    log.debug("※計算結果 ＜ 0 の場合は、概算受渡代金 ＝ 0");
                    l_bdEstimatedPrice = new BigDecimal("0");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_bdEstimatedPrice;
        
        //※国内投信以外（引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true）の場合は、
        //引数.所得税と引数.地方税が0になる。
    }
    
    private BigDecimal getTaxRate(
        String l_strParamType, 
        WEB3GentradeMainAccount l_mainAccount,
        WEB3MutualFundProduct l_mfProduct, 
        Date l_datBizDate) throws WEB3BaseException
    {    
        //1.17.   税率の取得
    
        //税率インスタンスを生成し、税率を取得する。
    
        //①@   税率インスタンスを生成する。
        //[コンストラクタにセットする引数]
        //証券会社コード： 引数.顧客.getInstitution().getInstitutionCode()の戻り値
        //税種類： （以下のとおり）
        //・   源泉徴収率（国税）を取得 and 引数.銘柄.is株式型 ＝ true の場合
        //      投信源泉徴収(株式型・国税)
        //・   源泉徴収率（国税）を取得 and 引数.銘柄.is株式型 ＝ false の場合
        //      投信源泉徴収(債券型・国税)
        //・   源泉徴収率（地方税）を取得 and 引数.銘柄.is株式型 ＝ true の場合
        //      投信源泉徴収(株式型・地方税)
        //・   源泉徴収率（地方税）を取得 and 引数.銘柄.is株式型 ＝ false の場合
        //      投信源泉徴収(債券型・地方税)
        //・   消費税率を取得の場合
        //      消費税
        //発注日： 引数.発注日
        
        String l_strTaxType = null;
        if (INCOME_TAX.equals(l_strParamType))
        {
            if (l_mfProduct.isStockType())
            {
                l_strTaxType = WEB3DutyTypeDef.MF_WITHHOLDING_STOCK_NATIONAL_TAX;
            }
            else
            {
                l_strTaxType = WEB3DutyTypeDef.MF_WITHHOLDING_BOND__NATIONAL_TAX;
            }
        }
        else if (LOCAL_TAX.equals(l_strParamType))
        {
            if (l_mfProduct.isStockType())
            {
                l_strTaxType = WEB3DutyTypeDef.MF_WITHHOLDING_STOCK_LOCAL_TAX;
            }
            else
            {
                l_strTaxType = WEB3DutyTypeDef.MF_WITHHOLDING_BOND_LOCAL_TAX;
            }
        }
        else if (TAX_RATE.equals(l_strParamType)) 
        {
            l_strTaxType = WEB3DutyTypeDef.CONSUMPTION_TAX;
        }
        
        log.debug("税種類 = " + l_strTaxType);
        
        WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(
            l_mainAccount.getInstitution().getInstitutionCode(), 
            l_strTaxType, 
            new Timestamp(l_datBizDate.getTime()));        
    
        //②   税率を取得する。
        //取得した税率インスタンス.get税率()メソッドをコールする。
        
        BigDecimal l_bdTaxRate = new BigDecimal(Double.toString(l_taxRate.getTaxRate()));
        
        //③ 税率を返却する。
        //取得した税率×0.01を返却する。
		l_bdTaxRate = l_bdTaxRate.multiply(new BigDecimal("0.01"));

        return l_bdTaxRate;
    }
    
	/**
	 * (calc概算買付口数)
	 * 乗換先銘柄の概算買付口数の計算を行う。<BR>
	 * <BR>
	 * @@param l_mfProduct - 銘柄
	 * @@param l_dblOrderQuantity - 注文数量
	 * @@return double
	 * @@roseuid 40A3495A00B7
	 */
	public double calcEstimatedBuyQty(
		WEB3MutualFundProduct l_mfProduct, 
		double l_dblOrderQuantity)
			throws WEB3BaseException
	{
		String STR_METHOD_NAME = "calcEstimatedBuyQty(WEB3MutualFundProduct, double)";
		log.entering(STR_METHOD_NAME);
        
		if (l_mfProduct == null)
		{
			log.debug(" パラメータ値がNULL ");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
         
		//１）概算受渡代金オブジェクトを生成する。 
			WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = 
				new WEB3MutualFundEstimatedPrice();
        
		//２）this.calc概算売買口数()に処理を委譲する。 
		//[引数] 
		//処理区分： ”買付”
		//注文数量： 引数.注文数量 
		//銘柄： 引数.銘柄 
		//概算受渡代金： ３-１）で生成したオブジェクト 
        
		this.calcEstimatedQty(
			WEB3MFDealDivDef.BUY, 
			l_dblOrderQuantity, 
			l_mfProduct, 
			l_mfEstimatedPrice); 

		log.debug(">>>>> 概算買付口数 = " + l_mfEstimatedPrice.getEstimatedQty());
        
		//３）概算受渡代金.get概算売買口数()の戻り値を返却する。 
		log.exiting(STR_METHOD_NAME); 
		return l_mfEstimatedPrice.getEstimatedQty();
            
	}
    
    /**
     * (calc外貨MMF概算受渡代金)<BR>
     * 以下の値を算出して、それぞれ概算受渡代金オブジェクトにセットする。<BR> 
     * ・概算売買代金 <BR>
     * ・概算売買代金(外貨）<BR> 
     * ・概算受渡代金 <BR>
     * <BR>
     * １）引数.拡張投信銘柄.get外貨MMF為替レート()をコールする。<BR> 
     * <BR>
     * ２）概算売買代金を算出して、計算結果を概算受渡代金オブジェクト.概算売買代金にセットする。<BR> 
     * <BR>
     * 　@　@　@概算売買代金 ＝ 引数.注文数量 × 為替レート ／  補助通貨単位比<BR> 
     * 　@　@　@※計算結果は小数点以下四捨五入 <BR>
     * <BR>
     * ３）概算売買代金（外貨）を算出して、<BR>
     * 計算結果を概算受渡代金オブジェクト.概算売買代金（外貨）にセットする。<BR> 
     * <BR>
     * 　@　@　@概算売買代金（外貨）＝ 引数.注文数量  ／  補助通貨単位比<BR> 
     * 　@　@　@※計算結果は、小数第３位を切り捨て小数第２位までとする。<BR> 
     * <BR>
     * ４）概算受渡代金を算出して、計算結果を概算受渡代金オブジェクト.概算受渡代金にセットする。<BR> 
     * <BR>
     * 　@　@４-１）　@引数.決済方法@ ＝  "円貨"の場合 <BR>
     * <BR>
     * 　@　@　@　@　@４-１-１） 処理区分 ＝  買付の場合 <BR>
     * 　@　@　@　@　@　@　@　@概算受渡代金 ＝ <BR>
     * 　@　@　@　@　@　@　@　@引数.注文数量 × 為替レート ／  補助通貨単位比  × （ 1 ＋ 割増拘束率 ）<BR> 
     * 　@　@　@　@　@　@　@　@※計算結果は小数点以下切り捨て <BR>
     * <BR>
     * 　@　@　@　@　@４-１-２） 処理区分 ＝  解約の場合 <BR>
     * 　@　@　@　@　@　@　@　@概算受渡代金 ＝ 引数.注文数量 × 為替レート ／  補助通貨単位比<BR> 
     * 　@　@　@　@　@　@　@　@※計算結果は小数点以下切り捨て <BR>
     * <BR>
     * 　@　@４-２）　@引数.決済方法@  != "円貨"の場合 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@概算受渡代金 ＝ 引数.注文数量  ／  補助通貨単位比<BR> 
     * 　@　@　@　@　@　@　@　@※計算結果は、小数第３位を切り捨て小数第２位までとする。<BR> 
     * <BR>
     * 為替レート: <BR>
     * 　@　@処理区分 ＝  買付の場合 ： 取得した外貨MMF為替レート.TTS <BR>
     * 　@　@処理区分 ＝  解約の場合 ： 取得した外貨MMF為替レート.TTB <BR>
     * <BR>
     * 補助通貨単位比：取得した外貨MMF為替レート.補助通貨単位比 <BR>
     * 割増拘束率      ：取得した外貨MMF為替レート.割増拘束率 <BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 処理区分 <BR>
     * <BR>
     * １：買付 <BR>
     * ２：解約 <BR>
     * ３：乗換 <BR>
     * ４：募集<BR>
     * @@param l_dblOrderQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * @@param l_strSettleDiv - (決済方法@)<BR>
     * 決済方法@<BR>
     * <BR>
     * １：円貨<BR>
     * ２：外貨<BR>
     * @@param l_mutualFundProduct - (拡張投信銘柄)<BR>
     * 拡張投信銘<BR>
     * @@param l_estimatedPrice - (概算受渡代金オブジェクト)<BR>
     * 概算受渡代金オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    public void calcFrgnMmfEstimatedTradeAmount(
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strSettleDiv,
        WEB3MutualFundProduct l_mutualFundProduct,
        WEB3MutualFundEstimatedPrice l_estimatedPrice) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimatedBuyQty("
            + "String, double, String, WEB3MutualFundProduct, WEB3MutualFundEstimatedPrice)";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFundProduct == null || l_estimatedPrice == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）引数.拡張投信銘柄.get外貨MMF為替レート()をコールする。
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams =
            l_mutualFundProduct.getFrgnMmfExchangeRate();

        //為替レート
        BigDecimal l_bdExchangeRate = new BigDecimal("1");

        //  処理区分 ＝  買付の場合
        if (WEB3MFProcessDivDef.BUY.equals(l_strProcessDiv))
        {
            //為替レート:取得した外貨MMF為替レート.TTS
            l_bdExchangeRate =
                new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getTtSellingRate()));
        }
        //処理区分 ＝  解約の場合
        else if (WEB3MFProcessDivDef.SELL.equals(l_strProcessDiv))
        {
            //為替レート:取得した外貨MMF為替レート.TTB
            l_bdExchangeRate =
                new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getTtBuyingRate()));
        }

        //補助通貨単位比：取得した外貨MMF為替レート.補助通貨単位比
        BigDecimal l_bdSubCurrencyRatio =
            new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getSubCurrencyRatio()));

        //注文数量
        BigDecimal l_bdOrderQuantity = new BigDecimal(String.valueOf(l_dblOrderQuantity));

        //２）概算売買代金を算出して、計算結果を概算受渡代金オブジェクト.概算売買代金にセットする。
        //      概算売買代金 ＝ 引数.注文数量×為替レート／補助通貨単位比
        //      ※計算結果は小数点以下四捨五入
        BigDecimal l_bdEstimatedTradeAmount =
            l_bdOrderQuantity.multiply(l_bdExchangeRate).divide(
                l_bdSubCurrencyRatio, 0, BigDecimal.ROUND_HALF_UP);
        l_estimatedPrice.setEstimatedTradeAmount(l_bdEstimatedTradeAmount.doubleValue());

        //３）概算売買代金（外貨）を算出して、計算結果を概算受渡代金オブジェクト.概算売買代金（外貨）にセットする。
        //      概算売買代金（外貨）＝ 引数.注文数量／補助通貨単位比
        //      ※計算結果は、小数第３位を切り捨て小数第２位までとする。
        BigDecimal l_bdFrgnCurrencyEstimatedTradeAmount =
            l_bdOrderQuantity.divide(
                l_bdSubCurrencyRatio, 2, BigDecimal.ROUND_FLOOR);
        l_estimatedPrice.setForeignCurrencyEstimatedTradeAmount(
            l_bdFrgnCurrencyEstimatedTradeAmount.doubleValue());

        //割増拘束率
        BigDecimal l_bdRestrictRate =
            new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getRestrictRate()));

        //概算受渡代金
        BigDecimal l_bdEstimatedPrice = new BigDecimal("0");

        //４）概算受渡代金を算出して、計算結果を概算受渡代金オブジェクト.概算受渡代金にセットする。
        //  　@　@４-１）　@引数.決済方法@ ＝  "円貨"の場合
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
        {
            //４-１-１） 処理区分 ＝  買付の場合
            if (WEB3MFProcessDivDef.BUY.equals(l_strProcessDiv))
            {
                //概算受渡代金 ＝ 引数.注文数量 × 為替レート／補助通貨単位比×(1＋割増拘束率)
                //※計算結果は小数点以下切り捨て
                BigDecimal l_bdOne = new BigDecimal("1");
                l_bdEstimatedPrice =
                    l_bdOrderQuantity.multiply(l_bdExchangeRate).divide(
                        l_bdSubCurrencyRatio, 10, BigDecimal.ROUND_HALF_DOWN).multiply(
                            l_bdOne.add(l_bdRestrictRate));
                l_bdEstimatedPrice = l_bdEstimatedPrice.setScale(0, BigDecimal.ROUND_FLOOR);
            }
            //４-１-２） 処理区分 ＝  解約の場合
            else if (WEB3MFProcessDivDef.SELL.equals(l_strProcessDiv))
            {
                // 概算受渡代金 ＝ 引数.注文数量 × 為替レート／補助通貨単位比
                // ※計算結果は小数点以下切り捨て
                l_bdEstimatedPrice =
                    l_bdOrderQuantity.multiply(l_bdExchangeRate).divide(
                        l_bdSubCurrencyRatio, 0, BigDecimal.ROUND_FLOOR);
            }
        }
        //４-２）　@引数.決済方法@  != "円貨"の場合
        else
        {
            //概算受渡代金 ＝ 引数.注文数量／補助通貨単位比
            //※計算結果は、小数第３位を切り捨て小数第２位までとする。
            l_bdEstimatedPrice =
                l_bdOrderQuantity.divide(l_bdSubCurrencyRatio, 2, BigDecimal.ROUND_FLOOR);
        }
        l_estimatedPrice.setEstimatedPrice(l_bdEstimatedPrice.doubleValue());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc外貨MMF概算売買口数)<BR>
     * 概算売買口数の計算をして概算受渡代金オブジェクトの概算売買口数にセットする。<BR>
     * <BR>
     * １）引数.拡張投信銘柄.get外貨MMF為替レート()をコールする。 <BR>
     * <BR>
     * ２）概算売買口数を算出する。 <BR>
     * <BR>
     * 　@２-１）  引数.決済方法@ = "円貨" の場合 <BR>
     * <BR>
     * 　@　@　@　@概算売買口数 ＝ 引数.注文数量  ×  補助通貨単位比  ／  為替レート<BR>
     * 　@　@　@　@※計算結果は小数点以下切り捨て <BR>
     * <BR>
     * 　@２-２）  引数.決済方法@  != "円貨" の場合 <BR>
     * <BR>
     * 　@　@　@　@概算売買口数 ＝ 引数.注文数量  ×  補助通貨単位比 <BR>
     * 　@　@　@　@※計算結果は小数点以下切り捨て <BR>
     * <BR>
     * <BR>
     * ・補助通貨単位比 ： 取得した外貨MMF為替レート.補助通貨単位比 <BR>
     * ・為替レート <BR>
     * 　@　@処理区分 ＝ 買付の場合 ： 取得した外貨MMF為替レート.TTS<BR>
     * 　@　@処理区分 ＝ 解約の場合 ： 取得した外貨MMF為替レート.TTB<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 処理区分 <BR>
     * <BR>
     * １：買付 <BR>
     * ２：解約 <BR>
     * ３：乗換 <BR>
     * ４：募集<BR>
     * @@param l_dblOrderQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * @@param l_strSettleDiv - (決済方法@)<BR>
     * 決済方法@<BR>
     * <BR>
     * １：円貨<BR>
     * ２：外貨<BR>
     * @@param l_mutualFundProduct - (拡張投信銘柄)<BR>
     * 拡張投信銘<BR>
     * @@param l_estimatedPrice - (概算受渡代金オブジェクト)<BR>
     * 概算受渡代金オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    public void calcFrgnMmfEstimatedQty(
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strSettleDiv,
        WEB3MutualFundProduct l_mutualFundProduct,
        WEB3MutualFundEstimatedPrice l_estimatedPrice) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimatedBuyQty("
            + "String, double, String, WEB3MutualFundProduct, WEB3MutualFundEstimatedPrice)";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFundProduct == null || l_estimatedPrice == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）引数.拡張投信銘柄.get外貨MMF為替レート()をコールする。
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams =
            l_mutualFundProduct.getFrgnMmfExchangeRate();

        //注文数量
        BigDecimal l_bdOrderQuantity = new BigDecimal(String.valueOf(l_dblOrderQuantity));

        //補助通貨単位比：取得した外貨MMF為替レート.補助通貨単位比
        BigDecimal l_bdSubCurrencyRatio =
            new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getSubCurrencyRatio()));

        //為替レート
        BigDecimal l_bdExchangeRate = new BigDecimal("1");

        //  処理区分 ＝  買付の場合
        if (WEB3MFProcessDivDef.BUY.equals(l_strProcessDiv))
        {
            //為替レート:取得した外貨MMF為替レート.TTS
            l_bdExchangeRate =
                new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getTtSellingRate()));
        }
        //処理区分 ＝  解約の場合
        else if (WEB3MFProcessDivDef.SELL.equals(l_strProcessDiv))
        {
            //為替レート:取得した外貨MMF為替レート.TTB
            l_bdExchangeRate =
                new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getTtBuyingRate()));
        }

        //概算売買口数
        BigDecimal l_bdEstimatedQty = new BigDecimal("0");

        //２）概算売買口数を算出する。
        //  ２-１）  引数.決済方法@ = "円貨" の場合
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
        {
            //概算売買口数 ＝ 引数.注文数量×補助通貨単位比／為替レート
            //※計算結果は小数点以下切り捨て
            l_bdEstimatedQty = l_bdOrderQuantity.multiply(l_bdSubCurrencyRatio).divide(
                l_bdExchangeRate, 0, BigDecimal.ROUND_FLOOR);
        }

        //  ２-２）  引数.決済方法@  != "円貨" の場合
        else
        {
            //概算売買口数 ＝ 引数.注文数量  ×  補助通貨単位比
            //※計算結果は小数点以下切り捨て
            l_bdEstimatedQty = l_bdOrderQuantity.multiply(l_bdSubCurrencyRatio).setScale(
                0, BigDecimal.ROUND_FLOOR);
        }
        l_estimatedPrice.setEstimatedQty(l_bdEstimatedQty.doubleValue());

        log.exiting(STR_METHOD_NAME);
    }
}
@
