head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託注文照会サービス実装クラス(WEB3MutualOrderReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 周勇 (中訊) 新規作成
Revesion History : 2004/08/23 黄建 (中訊) 新規作成 
Revesion History : 2004/12/10 于美麗 (中訊) 残対応
Revesion History : 2005/10/23 黄建 (中訊) フィデリティ対応
Revesion History : 2007/02/05 丁昭奎 (中訊) モデル No.526                      
Revesion History : 2007/04/09 唐性峰 (中訊) 実装005
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ExecStatusDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEB3OrderStatusDivDef;
import webbroker3.mf.define.WEB3ReferenceDivDef;
import webbroker3.mf.message.WEB3MutualFundTradeDivComparator;
import webbroker3.mf.message.WEB3MutualOrderDateComparator;
import webbroker3.mf.message.WEB3MutualOrderGroup;
import webbroker3.mf.message.WEB3MutualOrderReferenceRequest;
import webbroker3.mf.message.WEB3MutualOrderReferenceResponse;
import webbroker3.mf.message.WEB3MutualRequestDivComparator;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mf.message.WEB3MutualTaxTypeComparator;
import webbroker3.mf.service.delegate.WEB3MutualOrderReferenceService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託注文照会サービス実装クラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualOrderReferenceServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualOrderReferenceService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderReferenceServiceImpl.class);
    /**
     * 投資信託注文照会サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（投資信託）注文照会」参照。<BR>
     *  ========================================================<BR>
     * シーケンス図「（投資信託）注文照会」: <BR>
     *         5((注文チェック.validate取引可能顧客( )をコールし例外が返された場合、<BR>
     *         例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00275<BR>
     * ==========================================================<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 405669DE03AB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3MutualOrderReferenceResponse l_mutualOrderReferenceResponse = null;
        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualOrderReferenceRequest l_mutualOrderReferenceRequest = null;
        if (l_request instanceof WEB3MutualOrderReferenceRequest)
        {
            l_mutualOrderReferenceRequest =
                (WEB3MutualOrderReferenceRequest) l_request;
        }
        else
        {
            log.debug(
                "the parameter of method isn't WEB3MutualOrderReferenceRequest type");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1) リクエストデータのvalidate処理
        l_mutualOrderReferenceRequest.validate();
        
        //投信・外貨MMF表示区分
        //※nullの場合、「0:投信のみ」とする
        if (l_mutualOrderReferenceRequest.mutualFrgnMmfDisplayDiv == null)
        {
            l_mutualOrderReferenceRequest.mutualFrgnMmfDisplayDiv =
                WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND;
        }

        // --顧客別取引停止属性チェック
        //1.2) this.get補助口座( )をコールし、補助口座オブジェクトを取得
        SubAccount l_subAccount = this.getSubAccount();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
        
        //1.3）FinApp.getCommonOrderValidator( )
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        // --発注日取得
        //1.4) 投信取引時間管理.get投信発注日()をコールし、発注日を取得する            
        Timestamp l_datBizDate = 
            new Timestamp(WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate().getTime());
        
        //1.5) 注文チェック.validate取引可能顧客( )
        WEB3GentradeMainAccount l_genMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        OrderValidationResult l_validationResult =  
            l_gentradeOrderValidator.validateAccountForTrading(
                l_genMainAccount,
                l_datBizDate);
//---->実装005
//        if (l_validationResult.getProcessingResult().isFailedResult())
//        {
//            log.debug("取引停止顧客エラー");
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                "取引停止顧客エラー");
//        }
//<-----実装005

        // --”照会”としての受付時間チェック 
        boolean l_blnValidateOrderFlag = true;
        boolean l_blnReference =
            WEB3ReferenceDivDef.ORDER_REFERENCE.equals(
                l_mutualOrderReferenceRequest.referenceType);
                
        // 1.6) 投信注文照会リクエスト.照会区分＝”照会”の場合
        if (l_blnReference)
        {
            //1.6.1)取引時間管理.validate注文受付可能( )をコールする。
            //取引時間管理.validate注文受付可能( )から例外が返された場合、
            //例外をスローする。
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        
        // --”取消一覧”としての受付時間チェック
        //    無条件に”取消”の受付時間チェックを実施
        //1.7) 投信取引時間管理.reset注文受付トランザクション( )をコールする。
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.CANCEL);
            
        //1.8) 投信取引時間管理.setTimestamp() 
        WEB3MutualFundTradingTimeManagement.setTimestamp(); 
        
        try
        {
			//1.9) 取引時間管理.validate注文受付可能( )
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch(WEB3BaseException l_ex)
        {
            if (l_blnReference)
            {
                l_blnValidateOrderFlag = false;
            }
            else
            {
                //投信注文照会リクエスト.照会区分="取消一覧"の場合 
                //取引時間管理.validate注文受付可能( )から例外が返された場合、例外をスローする。
                log.debug("取引時間管理.validate注文受付可能( )から例外が返された");
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        //1.10) 注文単位検索処理
        WEB3MutualFundOrderManager l_mutualFundOrderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();
        List l_OrderUnitList =
            l_mutualFundOrderManager.getOrderUnitList(
                l_subAccount,
                l_mutualOrderReferenceRequest.mutualFrgnMmfDisplayDiv);
        int l_intOrderUnitList = l_OrderUnitList.size();
        log.debug("注文単位検索.size = " + l_intOrderUnitList);
        
        // --明細の作成        
		//1.11) get注文単位一覧の戻り値の件数分、
		//     繰り返して投信注文照会注文単位の配列を作成する。  
        List l_lisOrderGroup = new Vector();
        
        for (int i = 0; i < l_intOrderUnitList; i++)
        {
            WEB3MutualOrderGroup l_mutualOrderGroup = new WEB3MutualOrderGroup();
            MutualFundOrderUnit l_mutualFundOrderUnit =
                (MutualFundOrderUnit) l_OrderUnitList.get(i);
                
            //1.11.1) is取消可否（）
            boolean l_blnCancelAbleUnable =
                this.isCancelAbleUnable(l_mutualFundOrderUnit);
            
            //1.11.2) 明細の作成
            //「”取消一覧”としての受付時間チェック」の取引時間管理.validate注文受付可能( ) 
            //で例外が返されていた場合
            if (!l_blnValidateOrderFlag)
            {
                l_blnCancelAbleUnable = false;
            }                
            //・投信注文照会リクエスト.照会区分が"注文照会"の場合、取消不可として 
            //    投信注文照会注文単位の取消可能フラグに false　@をセットする。 
            //・投信注文照会リクエスト.照会区分が"取消一覧"の場合、表示対象外として 
            //    以下の投信注文照会注文単位作成を行わない。
            if (l_blnReference || (!l_blnReference && l_blnCancelAbleUnable))                
            {
				//1.11.2.1) 投信注文照会注文単位の生成
                MutualFundOrderUnitRow l_mutualFundOrderUnitRow =
                    (MutualFundOrderUnitRow) l_mutualFundOrderUnit.getDataSourceObject();

				//1.11.2.2) プロパティ・セット
                //IDセット
                log.debug("l_mutualFundOrderUnitRow.getOrderId() = " + l_mutualFundOrderUnitRow.getOrderId());
                l_mutualOrderGroup.id =
                    Long.toString(l_mutualFundOrderUnitRow.getOrderId());
                    
                //投信拡張銘柄マネージャを取得
				WEB3MutualFundProductManager l_mfProductManager =
					(WEB3MutualFundProductManager) l_finApp.getTradingModule(
						   ProductTypeEnum.MUTUAL_FUND).getProductManager();
                //銘柄オブジェクトを取得
				WEB3MutualFundProduct l_Product =
					(WEB3MutualFundProduct) l_mutualFundOrderUnit.getProduct();
								
				MutualFundProductRow l_mutualFundProductRow =
					(MutualFundProductRow) l_Product.getDataSourceObject();
				if (l_mutualFundProductRow == null)
				{
					log.debug("ProductRow is null");
					throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00377,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"拡張投信銘柄取得できない場合エラー");
				}					
				//拡張投信銘柄を取得
				WEB3MutualFundProduct l_mutualFundProduct =
					(WEB3MutualFundProduct) l_mfProductManager.toProduct(l_mutualFundProductRow);

				//銘柄名セット
				l_mutualOrderGroup.mutualProductName =
					l_mutualFundProduct.getMutualProductName();

                //口座区分セット
                if (TaxTypeEnum.NORMAL.equals(l_mutualFundOrderUnitRow.getTaxType()))
                {
                    l_mutualOrderGroup.taxType =
                        WEB3MFAccountDivDef.NORMAL;
                }
                if (TaxTypeEnum.SPECIAL.equals(l_mutualFundOrderUnit.getTaxType())
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mutualFundOrderUnit.getTaxType()))
                {
                    l_mutualOrderGroup.taxType =
                        WEB3MFAccountDivDef.SPECIAL;
                }
                if (TaxTypeEnum.UNDEFINED.equals(l_mutualFundOrderUnitRow.getTaxType()))
                {
                    l_mutualOrderGroup.taxType =
                        WEB3MFAccountDivDef.OTHER;
                }
                //売買区分(投信)セット
                l_mutualOrderGroup.mutualDealingType =
                    l_mutualFundOrderManager.getMutualTradeDiv(
                        l_mutualFundOrderUnit);
                //請求方法@セット
                l_mutualOrderGroup.sellBuyDiv =
                    l_mutualFundOrderUnitRow.getRequestDiv();
                //指定方法@              = (以下の手順で取得する)
                //-start---------------------------------------------
                //投信注文単位Params．get注文種別()の戻り値="201：投資信託買注文"、又は、 
               //投信注文単位Params．get注文種別( )の戻り値="203：投資信託募集注文"の場合 
                //投信注文単位Params.get数量タイプ( )="数量"の場合、"口数指定"をセット。
                //投信注文単位Params.get数量タイプ( )="金額"の場合、"金額指定"をセット。
                //上記以外の場合、投信注文単位Params．get投信解約区分()の戻り値をセットする。
                //-end-----------------------------------------------
                if(OrderTypeEnum.MF_BUY.equals(l_mutualFundOrderUnitRow.getOrderType()) ||
                   OrderTypeEnum.MF_RECRUIT.equals(l_mutualFundOrderUnitRow.getOrderType()))
                {
                    if(QuantityTypeEnum.QUANTITY.equals(l_mutualFundOrderUnitRow.getQuantityType()))
                    {
                        l_mutualOrderGroup.specifyDiv = WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE;
                    }
                    else if(QuantityTypeEnum.AMOUNT.equals(l_mutualFundOrderUnitRow.getQuantityType()))
                    {
                        l_mutualOrderGroup.specifyDiv = WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE;                        
                    }
                }
                else
                {
                    l_mutualOrderGroup.specifyDiv = l_mutualFundOrderUnitRow.getFundSellDiv();
                }
                //決済方法@
                l_mutualOrderGroup.settleDiv =
                    l_mutualFundOrderUnitRow.getSettlementDiv();
                //注文数量区分
                l_mutualOrderGroup.mutualOrderQuantityType =
                    l_mutualFundOrderManager.getOrderQuantityDiv(
                        l_mutualFundOrderUnit);
                //注文数量
                l_mutualOrderGroup.mutualOrderQuantity =
                    WEB3StringTypeUtility.formatNumber(
                    l_mutualFundOrderUnitRow.getQuantity());
                //基準価額通貨コード
                l_mutualOrderGroup.constantValueCurrencyCode =
                    l_mutualFundProduct.getCurrencyCode();
                //基準価額
                if (l_mutualFundOrderUnitRow.getCalcConstantValueIsNull())
                {
                    l_mutualOrderGroup.constantValue = null;
                }
                else
                {
                    l_mutualOrderGroup.constantValue =
                        WEB3StringTypeUtility.formatNumber(
                            l_mutualFundOrderUnitRow.getCalcConstantValue());
                    log.debug("明細:基準価額= " + l_mutualOrderGroup.constantValue);
                }
                //基準価額適用日
                if (!OrderTypeEnum.MF_RECRUIT.equals(l_mutualFundOrderUnitRow.getOrderType()))
                {
                    l_mutualOrderGroup.constantValueAppDate =
                        l_mutualFundOrderUnitRow.getConstantValueAppDate();
                    log.debug("明細:基準価額適用日= " + l_mutualOrderGroup.constantValueAppDate);
                }
                
                //概算受渡代金通貨コード
                l_mutualOrderGroup.estimatedPriceCurrencyCode =
                    l_mutualFundOrderManager.getEstimateDeliveryAmountCurrencyCode(
                        l_mutualFundOrderUnit);
                log.debug("明細:概算受渡代金通貨コード= " + l_mutualOrderGroup.estimatedPriceCurrencyCode);
                //概算受渡代金
                l_mutualOrderGroup.estimatedPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_mutualFundOrderUnitRow.getEstimatedPrice());
                log.debug("明細:概算受渡代金= " + l_mutualOrderGroup.estimatedPrice);
                //概算売買口数
                l_mutualOrderGroup.estimatedQty =
                    WEB3StringTypeUtility.formatNumber(l_mutualFundOrderUnitRow.getEstimateDealingQty());
                log.debug("明細:概算売買口数= " + l_mutualOrderGroup.estimatedQty);
                //受渡方法@ = 投信注文単位.get受渡方法@( )
                l_mutualOrderGroup.deliveryDiv =
                    l_mutualFundOrderUnitRow.getPaymentMethod();
                log.debug("明細:受渡方法@= " + l_mutualOrderGroup.deliveryDiv);
                //注文日時 = 投信注文単位.get受注日時( )
                l_mutualOrderGroup.orderDate =
                    l_mutualFundOrderUnitRow.getReceivedDateTime();
                //発注日
                log.debug("l_mutualFundOrderUnitRow.getBizDate() = " + l_mutualFundOrderUnitRow.getBizDate());
                l_mutualOrderGroup.orderBizDate =
                    WEB3DateUtility.getDate(
                        l_mutualFundOrderUnitRow.getBizDate(),
                        "yyyyMMdd");
                log.debug("明細:発注日= " + l_mutualOrderGroup.orderBizDate);
                //約定日
                l_mutualOrderGroup.executionTimestamp =
                    l_mutualFundOrderUnitRow.getExecDate();
                //受渡日
                l_mutualOrderGroup.deliveryDate =
                    l_mutualFundOrderUnitRow.getDeliveryDate();
                //注文状態区分
                l_mutualOrderGroup.orderState =
                    this.geOrderStatusDivision(l_mutualFundOrderUnit);
                //外貨MMFフラグ
                boolean l_blnFrgnMmfFlag = false;
                if(MutualFundTypeEnum.FOREIGN_MMF.equals(
                    l_mutualFundOrderUnitRow.getFundType()))
                {
                    l_blnFrgnMmfFlag = true;
                }
                l_mutualOrderGroup.frgnMmfFlag = l_blnFrgnMmfFlag;
                
                //投信銘柄オブジェクト（乗換先）の取得
                String l_strSwtProductCode =
                    l_mutualFundOrderUnitRow.getSwtProductCode();
                
				//1.11.2.3) 投信銘柄オブジェクト（乗換先）!= nullの場合
                if (l_strSwtProductCode != null)
                {
                    //概算買付口数（乗換先）
                    l_mutualOrderGroup.switchingEstimatedQty =
                        WEB3StringTypeUtility.formatNumber(l_mutualFundOrderUnitRow.getSwtEstimateDealingQty());

                    log.debug("明細:概算買付口数（乗換先= " + l_mutualOrderGroup.switchingEstimatedQty);
                
                    //1.11.2.3.1)　@拡張投信銘柄マネージャ.get拡張投信銘柄( )
                    WEB3MutualFundProductManager l_mutualFundProductManager =
                        (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                            ProductTypeEnum.MUTUAL_FUND).getProductManager();
                    WEB3MutualFundProduct l_web3MutualFundProduct = null;
                    try
                    {
                        l_web3MutualFundProduct =
                            (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                                l_subAccount.getInstitution(),
                                l_strSwtProductCode);
                    }
                    catch (NotFoundException e)
                    {
                        log.debug("no find MutualFundProduct");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName()
                                + "."
                                + STR_METHOD_NAME);
                    }
                    //1.11.2.3.2)　@取得した拡張投信銘柄オブジェクトより、以下を設定する。     
                    //銘柄名（乗換先）　@　@　@ = 乗換先の拡張投信銘柄オブジェクト.get銘柄名( )     
                    l_mutualOrderGroup.switchingProductName =
                        l_web3MutualFundProduct.getMutualProductName();
                    //計算基準価額通貨コード（乗換先） = 乗換先の拡張投信銘柄オブジェクト.get通貨コード( )
                    l_mutualOrderGroup.switchingConstantValueCurrencyCode =
                        l_web3MutualFundProduct.getCurrencyCode();
                    //計算基準価額（乗換先） = 乗換先の拡張投信銘柄オブジェクト.get基準価額( )                       
                    l_mutualOrderGroup.switchingConstantValue =
                        WEB3StringTypeUtility.formatNumber(
                            l_web3MutualFundProduct.getConstantValue());
                    //口座区分（乗換先）　@　@　@ = (以下の手順で取得する)
                    //-start---------------------------------------------
                    //b)　@投信注文単位.getDataSourceObject( ).get税区分（乗換先）( )をコールする。
                    //b-1)　@戻り値が"1:一般"の場合、"0:一般"をセット。
                    //b-2)　@戻り値が"2:特定"または"3:特定口座かつ源泉徴収"の場合、"1:特定"をセット。
                    //-end-----------------------------------------------
                    if(TaxTypeEnum.NORMAL.equals(l_mutualFundOrderUnitRow.getSwtTaxType()))
                    {
                        l_mutualOrderGroup.switchingTaxType =
                            WEB3TaxTypeSpecialDef.NORMAL;
                    }
                    if (TaxTypeEnum.SPECIAL.equals(l_mutualFundOrderUnitRow.getSwtTaxType())
                        || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mutualFundOrderUnitRow.getSwtTaxType()))
                    {
                        l_mutualOrderGroup.switchingTaxType =
                            WEB3TaxTypeSpecialDef.SPECIAL;
                    }
                    //源泉徴収拘束金（乗換元） = （以下のとおり） 
                    //　@－投信注文単位.get源泉徴収拘束金isNull( )==false and
                    //　@　@（投信注文単位.約定日 == 投信注文単位.乗換元約定日 or
                    //　@　@　@投信注文単位.注文有効状態 != ”クローズ”）の場合、投信注文単位.get源泉徴収拘束金( )
                    //　@－それ以外、null。
                    l_mutualOrderGroup.switchingWHRestraintPrice = null;
                    
                    String l_strExecDate =
                    	WEB3DateUtility.formatDate(l_mutualFundOrderUnitRow.getExecDate(),"yyyyMMdd");
                    String l_strSwtExecDate =
                    	WEB3DateUtility.formatDate(l_mutualFundOrderUnitRow.getSwtExecDate(),"yyyyMMdd");
                    
                    if (!l_mutualFundOrderUnitRow.getWithholdingTaxRestrictionIsNull() &&
                    		(l_strExecDate.equals(l_strSwtExecDate) ||
                    			!l_mutualFundOrderUnitRow.getOrderOpenStatus().equals(OrderOpenStatusEnum.CLOSED)))
                   	{
                        l_mutualOrderGroup.switchingWHRestraintPrice =
                            WEB3StringTypeUtility.formatNumber(
                                l_mutualFundOrderUnitRow
                                    .getWithholdingTaxRestriction());
                    }
                }
				//1.11.2.4) 投信銘柄オブジェクト（乗換先）== nullの場合
                else
                {
                	//1.11.2.4.1) プロパティ・セット
                    //銘柄名（乗換先) = 乗換先の拡張投信銘柄オブジェクト.get銘柄名( )     
                    l_mutualOrderGroup.switchingProductName = null;
                    //計算基準価額通貨コード（乗換先） = 乗換先の拡張投信銘柄オブジェクト.get通貨コード( )
                    l_mutualOrderGroup.switchingConstantValueCurrencyCode =null;
                    //計算基準価額（乗換先） = 乗換先の拡張投信銘柄オブジェクト.get基準価額( )                       
                    l_mutualOrderGroup.switchingConstantValue = null;
                    //口座区分（乗換先）　@ = 投信注文単位.getDataSourceObject( ).get税区分（乗換先）( )
                    l_mutualOrderGroup.switchingTaxType = null;
                    //源泉徴収拘束金（乗換元） = null
                    l_mutualOrderGroup.switchingWHRestraintPrice = null;
                }
                //取消可能フラグ = 1.11.1) is取消可否の戻り値
                l_mutualOrderGroup.cancelFlag = l_blnCancelAbleUnable;
                
                //1.11.2.5) get代理入力者（）
                //1.11.2.6) get代理入力者（）!= nullの場合
                if (this.getTrader() != null)
                {
					//1.11.2.6.1) プロパティ・セット
                    //注文チャネル=投信注文単位.初回注文.get初回からの注文チャネル( )
                    l_mutualOrderGroup.orderChannel =
                        l_mutualFundOrderUnitRow.getOrderChanel();
                    //注文経路区分=投信注文単位.get注文経路区分( )
                    l_mutualOrderGroup.orderRootDiv =
                        l_mutualFundOrderUnitRow.getOrderRootDiv();
                    //オペレータコード=投信注文単位.get取引者ID( )≠0の場合のみ、
                    //拡張金融オブジェクトマネージャ
                    WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                        (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                    if (l_mutualFundOrderUnit.getTraderId() != 0)
                    {
                        try
                        {
                            l_mutualOrderGroup.operatorCode =
                                l_gentradeFinObjectManager.getTrader(
                                    l_mutualFundOrderUnit.getTraderId()).getTraderCode();
                        }
                        catch (NotFoundException l_ex)
                        {
                            log.error("no find Trader");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName()
                                    + "."
                                    + STR_METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);
                        }
                    }
                }
                l_lisOrderGroup.add(l_mutualOrderGroup);
            }
        }   //end for
        
        WEB3MutualOrderGroup[] l_mutualOrderGroups = 
            new WEB3MutualOrderGroup[l_lisOrderGroup.size()];
        l_lisOrderGroup.toArray(l_mutualOrderGroups);
        
        // --ソート処理
        // 1.11.3) sort注文照会明細（）
        WEB3MutualOrderGroup[] l_web3MutualOrderGroups =
            this.sortOrderInquiryDetails(
                l_mutualOrderGroups,
                l_mutualOrderReferenceRequest.sortKeys);
         
		// 1.11.4) 投信注文照会レスポンスクラスを生成
		l_mutualOrderReferenceResponse = 
			(WEB3MutualOrderReferenceResponse) l_mutualOrderReferenceRequest.createResponse();
			       
        // 1.11.5) ページング処理
        //　@投信注文照会レスポンスの以下の項目を設定
        //○投信注文照会レスポンス.総ページ数:
        //int l_lnMutualOrderGroupLength = l_intOrderUnitList;
        int l_intMutualOrderGroupLength = l_web3MutualOrderGroups.length;
        int l_intTotalPages =
            l_intMutualOrderGroupLength
                / Integer.parseInt(l_mutualOrderReferenceRequest.pageSize);
        if (l_intMutualOrderGroupLength == 0)
        {
            l_mutualOrderReferenceResponse.totalPages = "0";
        }
        else if (l_intMutualOrderGroupLength % Integer.parseInt(l_mutualOrderReferenceRequest.pageSize) == 0)
        {
            l_mutualOrderReferenceResponse.totalPages = Integer.toString(l_intTotalPages);
        }
        else if (l_intMutualOrderGroupLength % Integer.parseInt(l_mutualOrderReferenceRequest.pageSize) > 0)
        {
            l_mutualOrderReferenceResponse.totalPages = Integer.toString(l_intTotalPages + 1);
        }
        //○投信注文照会レスポンス.総レコード数:　@明細の要素数
        l_mutualOrderReferenceResponse.totalRecords = Integer.toString(l_web3MutualOrderGroups.length);
        
        //○投信注文照会レスポンス.表示ページ番号(表示が何ページ目にあたるか):以下をセット。
        if (l_intMutualOrderGroupLength > Integer.parseInt(l_mutualOrderReferenceRequest.pageSize)
                * (Integer.parseInt(l_mutualOrderReferenceRequest.pageIndex) - 1))
        {
            l_mutualOrderReferenceResponse.pageIndex =
                l_mutualOrderReferenceRequest.pageIndex;
        }
        else
        {
            l_mutualOrderReferenceResponse.pageIndex = l_mutualOrderReferenceResponse.totalPages;
        }
        
        //設定後、投信注文照会レスポンス.総ページ数 = 0 の場合は、
        //投信注文照会レスポンス.買付銘柄一覧(投信注文照会注文単位[ ])にnullを
        //セットし、レスポンスをreturnする 
        if ("0".equals(l_mutualOrderReferenceResponse.totalPages))
        {
            l_mutualOrderReferenceResponse.mutualOrderGroups = null;
            log.debug("投信注文照会レスポンス.総ページ数 = 0");
            return l_mutualOrderReferenceResponse;
        }
        
        //1.11.6) 明細のセット
        //　@(投信注文照会リクエスト.ページ内表示行数×(投信注文照会レスポンス. 
        //    表示ページ番号 - 1)数分、７)で確定した投信注文照会レスポンス明細データ 
        //    一覧の要素をスキップする。 
        int l_intRecordBegin =
            Integer.parseInt(l_mutualOrderReferenceRequest.pageSize)
                * (Integer.parseInt(l_mutualOrderReferenceResponse.pageIndex) - 1);
        int l_intRecordEnd = 0;
        if (l_mutualOrderReferenceResponse.pageIndex.equalsIgnoreCase(l_mutualOrderReferenceResponse.totalPages))
        {
            l_intRecordEnd = l_intMutualOrderGroupLength;
        }
        else
        {
            l_intRecordEnd = l_intRecordBegin + Integer.parseInt(l_mutualOrderReferenceRequest.pageSize);
        }
        // 上記で決定した投信注文照会レスポンス明細データ一覧の要素番号～ 
        // (投信注文照会レスポンス明細データ一覧の要素番号＋投信注文照会リクエスト. 
        // ページ内表示行数)までに該当する投信注文照会レスポンス明細データを、 
        // 投信注文照会レスポンスデータ.買付銘柄一覧としてセットする。
        List l_lisBuyProductGroups = new Vector();
        for (int i = l_intRecordBegin ; i < l_intRecordEnd; i++) 
        {
            l_lisBuyProductGroups.add(l_web3MutualOrderGroups[i]);
        }
        WEB3MutualOrderGroup[] l_BuyProductGroups = new WEB3MutualOrderGroup[l_lisBuyProductGroups.size()];
        l_lisBuyProductGroups.toArray(l_BuyProductGroups); 
        l_mutualOrderReferenceResponse.mutualOrderGroups = l_BuyProductGroups;

        log.exiting(STR_METHOD_NAME);
        return l_mutualOrderReferenceResponse;
    }
    /**
     * (get注文状態区分)<BR>
     * 引数:投信注文単位オブジェクトより、注文状態区分を判定し返却する。<BR>
     * <BR>
     * 1)　@get注文経路区分()の戻り値が「HOST」である場合<BR>
     * 　@　@かつ、注文状態が"発注済(取消注文)"であり、かつ注文有効状態が"クローズである場合、<BR>
     * 　@　@　@　@"注文取消済"をリターンする。<BR>
     * 　@　@それ以外の場合、"取消不可"をリターンする。<BR> 
　@   *   ○。<BR> 
     * 2) 以下の条件に合致した場合、"注文中"をリターンする。<BR>
     * 　@○約定状態がNULLであり、かつ注文状態が"受付済（新規注文）"または<BR>
     *    "発注済(新規注文)"であり、かつ 銘柄コード（乗換先）がnullである<BR>
     * 3)　@以下の条件に合致した場合、"乗換中"をリターンする。 <BR>
     * 　@○約定状態がNULLであり、かつ注文状態が"受付済（新規注文）"または<BR>
     *    "発注済(新規注文)"であり、かつ 銘柄コード（乗換先）がnull以外である <BR>
     * <BR>
     * 4)　@以下の条件に合致した場合、"約定中"をリターンする。<BR>
     * 　@○注文状態が"発注済(新規注文)"であり、かつ約定状態が"約定中"である<BR>
     * <BR>
     * 5)　@以下の条件に合致した場合、"注文取消済"をリターンする。<BR>
     * 　@○注文状態が"発注済(取消注文)"であり、かつ注文有効状態が"クローズである。<BR>
     * <BR>
     * 6)　@上記以外の場合、注文状態の値をリターンする。<BR>
     * @@param l_mutualFundOrderUnit - (投信注文単位)<BR>
     * 投信注文単位オブジェクト
     * @@return String
     * @@roseuid 40B6A6520044
     */
    protected String geOrderStatusDivision(MutualFundOrderUnit l_mutualFundOrderUnit)
    {
        final String STR_METHOD_NAME =
            "geOrderStatusDivision(MutualFundOrderUnit l_mutualFundOrderUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderUnit == null && "".equals(l_mutualFundOrderUnit))
        {
            log.debug("the parameter of method is null or blank ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        MutualFundOrderUnitRow l_mutualFundOrderUnitRow =
            (MutualFundOrderUnitRow) l_mutualFundOrderUnit.getDataSourceObject();
        OrderStatusEnum l_orderStatusEnum =
            l_mutualFundOrderUnitRow.getOrderStatus();
        String l_strExecStatus = l_mutualFundOrderUnitRow.getExecStatus();
        OrderOpenStatusEnum l_orderOpenStatusEnum =
            l_mutualFundOrderUnitRow.getOrderOpenStatus();
            
        // 1) ○get注文経路区分()の戻り値が「HOST」である場合 
        if(WEB3OrderRootDivDef.HOST.equals(l_mutualFundOrderUnitRow.getOrderRootDiv()))
        {
			// 注文状態が"発注済(取消注文)"であり、かつ注文有効状態が"クローズである場合、
			// "注文取消済"をリターンする。
			if (OrderStatusEnum.CANCELLED.intValue() == l_orderStatusEnum.intValue()
					&& OrderOpenStatusEnum.CLOSED.intValue() == l_orderOpenStatusEnum.intValue())
			{
				return WEB3OrderStatusDivDef.ORDER_CANCELED;
			}
			// それ以外の場合、"取消不可"をリターンする。
			else
			{
        		return WEB3OrderStatusDivDef.CANCEL_DISABLE;
			}
        }
  
        // 2) 以下の条件に合致した場合、"注文中"をリターンする。
        //　@  ○約定状態がNULLであり、かつ注文状態が"受付済（新規注文）"または
        //   "発注済(新規注文)"であり、かつ 銘柄コード（乗換先）がnullである
        if(l_strExecStatus == null 
           && (OrderStatusEnum.ACCEPTED.intValue() == l_orderStatusEnum.intValue() 
           || OrderStatusEnum.ORDERED.intValue() == l_orderStatusEnum.intValue())
           && l_mutualFundOrderUnitRow.getSwtProductCode() == null   
           )
        {  
            return WEB3OrderStatusDivDef.ORDERING;  
        }
        // 3) 以下の条件に合致した場合、"乗換中"をリターンする
        //  ○約定状態がNULLであり、かつ注文状態が"受付済（新規注文）"または
        //  "発注済(新規注文)"であり、かつ 銘柄コード（乗換先）がnull以外である
        if (l_strExecStatus == null 
            && (OrderStatusEnum.ACCEPTED.intValue() == l_orderStatusEnum.intValue()
            || OrderStatusEnum.ORDERED.intValue() == l_orderStatusEnum.intValue())
            && l_mutualFundOrderUnitRow.getSwtProductCode() != null)
        {
            return WEB3OrderStatusDivDef.SWITCHING;
        }
        // 4) 以下の条件に合致した場合、"約定中"をリターンする。
        //    ○注文状態が"発注済(新規注文)"であり、かつ約定状態が"約定中"である
        if (OrderStatusEnum.ORDERED.intValue() == l_orderStatusEnum.intValue()
            && WEB3ExecStatusDef.EXECUTED_IN_PROCESS.equals(l_strExecStatus))
        {
            return WEB3OrderStatusDivDef.EXECUTED_IN_PROCESS;
        }
        // 5) 以下の条件に合致した場合、"注文取消済"をリターンする
        //    ○注文状態が"発注済(取消注文)"であり、かつ注文有効状態が"クローズである
        if (OrderStatusEnum.CANCELLED.intValue() == l_orderStatusEnum.intValue()
            && OrderOpenStatusEnum.CLOSED.intValue() == l_orderOpenStatusEnum.intValue())
        {
            return WEB3OrderStatusDivDef.ORDER_CANCELED;
        }
        // 6) 上記以外の場合、注文状態の値をリターンする
        String l_strStatues = Integer.toString(l_orderStatusEnum.intValue());
        log.exiting(STR_METHOD_NAME);
        return l_strStatues;
    }
    /**
     * (sort注文照会明細)<BR>
     * 指定されたソートキー、昇降順に基づいて投信注文照会明細のソートを行う。 <BR>
     * <BR>
     * １)　@ArrayListを作成<BR> 
     * <BR>
     * ２)　@引数:ソートキーの配列数分Loop処理 <BR>
     * <BR>
     * 　@２－１)　@引数:ソートキー.キー項目を取得 <BR>
     * <BR>
     * 　@２－２)　@引数:ソートキー.昇順/降順を取得 <BR>
     * <BR>
     * 　@２－３)　@キー項目による分岐処理 <BR>
     * 　@　@キー項目が口座区分であった場合、口座区分Comparatorを生成 <BR>
     * 　@　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@　@キー項目が売買区分(投信)であった場合、売買区分(投信)Comparatorを生成 <BR>
     * 　@　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@　@キー項目が注文日時であった場合、注文日時Comparatorを生成 <BR>
     * 　@　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@２－４)　@２－３)にて作成したComparatorをArrayListに追加 <BR>
     * <BR>
     * ３)　@ArrayListからComparatorの配列を作成 <BR>
     * <BR>
     * ４)　@Comparatorの配列順のソート処理 <BR>
     * (web3-common)WEB3ArraysUtility.sort(引数:投信注文照会明細、<BR>Comparator[]) <BR>
     * <BR>
     * ５)　@ソートされた投信注文照会明細の配列を返却<BR>
     * @@param l_mutualFundOrderInquiryDetails - 投信注文照会明細
     * @@param l_sortKey - ソートキー
     * @@return webbroker3.mf.message.WEB3MutualOrderGroup
     * @@roseuid 40B6A67A0380
     */
    protected WEB3MutualOrderGroup[] sortOrderInquiryDetails(
        WEB3MutualOrderGroup[] l_mutualFundOrderInquiryDetails,
        WEB3MutualSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME =
            "sortOrderInquiryDetails(WEB3MutualOrderGroup[] l_mutualFundOrderInquiryDetails,WEB3MutualSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderInquiryDetails == null || l_sortKey == null)
        {
            log.debug("the parameter of method is null  ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if(l_sortKey.length == 0)
        {
            log.debug("the parameter of method is length == 0 ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);        
        }
        if(l_mutualFundOrderInquiryDetails.length == 0 )
        {
			return l_mutualFundOrderInquiryDetails;
        }
		else
		{
        //１)　@ArrayListを作成 
        ArrayList l_arrayList = new ArrayList();
        //２)　@引数:ソートキーの配列数分Loop処理 
        for (int i = 0; i < l_sortKey.length; i++)
        {
            //２－１)　@引数:ソートキー.キー項目を取得
            String l_strKeyItem = l_sortKey[i].keyItem;
            //　@２－２)　@引数:ソートキー.昇順/降順を取得 
            String l_strAscDesc = l_sortKey[i].ascDesc;
            //２－３)　@キー項目による分岐処理
            if (WEB3MFSortkeyItemDef.TAX_TYPE.equals(l_strKeyItem))
            {
                WEB3MutualTaxTypeComparator l_taxTypeComparator =
                    new WEB3MutualTaxTypeComparator(l_strAscDesc);
                //２－４)　@２－３)にて作成したComparatorをArrayListに追加
                l_arrayList.add(l_taxTypeComparator);
            }
            if (WEB3MFSortkeyItemDef.MUTUAL_DEALING_TYPE.equals(l_strKeyItem))
            {
                WEB3MutualFundTradeDivComparator l_mutualFundTradeDivComparator =
                    new WEB3MutualFundTradeDivComparator(l_strAscDesc);
                l_arrayList.add(l_mutualFundTradeDivComparator);
            }
            if (WEB3MFSortkeyItemDef.ORDER_DATE.equals(l_strKeyItem))
            {
                WEB3MutualOrderDateComparator l_orderDateComparator =
                    new WEB3MutualOrderDateComparator(l_strAscDesc);
                l_arrayList.add(l_orderDateComparator);
            }
            if (WEB3MFSortkeyItemDef.SELL_BUY_DIV.equals(l_strKeyItem))
            {
                WEB3MutualRequestDivComparator l_requestDivComparator =
                    new WEB3MutualRequestDivComparator(l_strAscDesc);
                l_arrayList.add(l_requestDivComparator);
            }
        }
        //３)　@ArrayListからComparatorの配列を作成
        Comparator[] l_comparators = new Comparator[l_arrayList.size()];
        l_arrayList.toArray(l_comparators);
        //４)　@Comparatorの配列順のソート処理 
        WEB3ArraysUtility.sort(l_mutualFundOrderInquiryDetails, l_comparators);
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderInquiryDetails;
		}
    }
    /**
     * (is取消可否)<BR>
     * 投信注文単位より、その注文が取消可能な注文かどうかを判定し、取消可能な場合<BR>
     * trueを、取消不可の場合は"false"を返却する。 <BR>
     * <BR>
     * １)　@取引カレンダコンテキストの更新<BR>
     * 　@１－１)　@投信取引時間管理.reset銘柄コード( )をコールする。<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@引数:投信注文単位.getProduct( ).getProductCode( )<BR>
     * <BR>
     * 　@１－２)　@投信取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ２)　@取消可否判定<BR>
     * 　@２－１)　@投信発注審査個別チェック.validate取消可能( )をコールし、<BR>
     * 　@　@　@　@例外が返された場合、falseをリターンする。<BR>
     * <BR>
     * 　@２－２)　@上記以外の場合、trueをリターンする。<BR>
     * @@param l_mutualFundOrderUnit - (投信注文単位)<BR>
     * 投信注文単位オブジェクト
     * @@return boolean
     * @@roseuid 40B6A6E403AF
     */
    protected boolean isCancelAbleUnable(MutualFundOrderUnit l_mutualFundOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isCancelAbleUnable(MutualFundOrderUnit l_mutualFundOrderUnit))";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderUnit == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１)　@取引カレンダコンテキストの更新
        //１－１)　@投信取引時間管理.reset銘柄コード( )をコールする
        WEB3MutualFundProduct l_Product =
            (WEB3MutualFundProduct) l_mutualFundOrderUnit.getProduct();
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_Product.getProductCode());
        log.debug("l_Product.getProductCode() = " + l_Product.getProductCode());
        //１－２)　@投信取引時間管理.setTimestamp()をコールする。
        try
        {
            WEB3MutualFundTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException e)
        {
            log.error("投信取引時間管理.setTimestamp()をコールする,例外が返された場合");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80014,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //２)　@取消可否判定
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            	(WEB3MutualFundOrderManagerReusableValidationsCheck)
            			MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        try
        {
            l_validationsCheck.validateCancelPossible(
                this.getSubAccount(),
                l_mutualFundOrderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
