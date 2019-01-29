head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング申告サービス実装クラス(WEB3IpoBookbuildingOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
Revesion History : 2004/12/27 坂上(SRA) 残対応>>>036
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>047,044
Revesion History : 2005/01/11 坂上(SRA) 残対応>>>036(電子鳩障害のプロパティ削除)
Revesion History : 2006/01/26 郭英（中訊）仕様変更・モデル118
Revesion History : 2006/06/27 魏(中訊) 仕様変更No.148対応
Revesion History : 2006/11/22 何文敏 (中訊) 仕様変更No.168対応
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3DocReadingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.ipo.WEB3IpoBookbuildingNewOrderSpec;
import webbroker3.ipo.WEB3IpoBookbuildingPriceCalcResult;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoOrderValidator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPOブックビルディング申告サービス実装クラス
 * <br> 
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingOrderServiceImpl 
    extends WEB3IpoClientRequestService 
    implements WEB3IpoBookbuildingOrderService 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingOrderServiceImpl.class);
            
    /**
     * @@roseuid 4112F18E03E7
     */
    public WEB3IpoBookbuildingOrderServiceImpl() 
    {
     
    }
    
    /**
     * IPOブックビルディング申告処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、IPOブックビルディング申告入力リクエストの場合<BR>
     * 　@－get入力画面()をコールする。<BR>
     * ○ 引数のリクエストデータが、IPOブックビルディング申告確認リクエストの場合<BR>
     * 　@－validateブックビルディング申告()をコールする。<BR>
     * ○ 引数のリクエストデータが、IPOブックビルディング申告完了リクエストの場合<BR>
     * 　@－submitブックビルディング申告()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D284740333
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3IPOBookBuildingDemandInputRequest)
        {
            //IPOブックビルディング申告入力リクエストの場合
            l_response = getInputScreen(
                (WEB3IPOBookBuildingDemandInputRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingDemandConfirmRequest)
        {
            //IPOブックビルディング申告確認リクエストの場合
            l_response = validateBookbuildingOrder(
                (WEB3IPOBookBuildingDemandConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3IPOBookBuildingDemandCompleteRequest)
        {
            //IPOブックビルディング申告完了リクエストの場合
            l_response = submitBookbuildingOrder(
                (WEB3IPOBookBuildingDemandCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "パラメータの類型が不正、該当するWEB3IPOBookBuildingOrderInputRequestt," + 
                "WEB3IPOBookBuildingOrderConfirmRequest,WEB3IPOBookBuildingOrderCompleteRequest類型。";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * IPOブックビルディング申告入力画面表示データ作成処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告）get入力画面」参照。<BR>
     * @@param l_request - IPOブックビルディング申告入力リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderInputResponse
     * @@roseuid 40D2843A020A
     */
    protected WEB3IPOBookBuildingDemandInputResponse getInputScreen(
        WEB3IPOBookBuildingDemandInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3IpoBookbuildingOrderInputRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3IPOBookBuildingDemandInputResponse l_response = null;
        try
        {
            //1.1 validate注文受付可能
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //1.2 getOrderValidator( )
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
            WEB3IpoOrderValidator l_orderValidator = 
                (WEB3IpoOrderValidator)l_ipoOrderManager.getOrderValidator();
    
            //1.3 validate取引可能顧客
            OrderValidationResult l_orderValidationResult = 
                l_orderValidator.validateSubAccountForTrading(this.getSubAccount());
            if(!OrderValidationResult.VALIDATION_OK_RESULT.equals(l_orderValidationResult))
            {
                String l_strErrorMessage = "get入力画面error!";
                log.error(l_strErrorMessage);
                // 2004/11/08 障害管理票No.U00377 出力エラーの修正 水落@@SRA START
                throw new WEB3BaseException(
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);
                // 2004/11/08 障害管理票No.U00377 出力エラーの修正 水落@@SRA END  
            }
            
            Integer l_branchPreferences = 
                this.getBranchPreferences((WEB3GentradeSubAccount) this.getSubAccount());
            if(l_branchPreferences != null && l_branchPreferences.intValue() == 1)
            {
                //1.4  validate居住者(補助口座)
                l_orderValidator.validateResident((WEB3GentradeSubAccount)this.getSubAccount());
            }
            
            //1.5 IPO銘柄
            WEB3IpoProductManagerImpl l_productManager = 
                (WEB3IpoProductManagerImpl)l_tradingModule.getProductManager();
            long l_lngProductID = Long.parseLong(l_request.id);
            WEB3IpoProductImpl l_product = 
                (WEB3IpoProductImpl)l_productManager.getProduct(l_lngProductID);//NotFoundException
        
//            //1.5 validate目論見書既読
//            l_orderValidator.validateProspectusExistingRead(this.getSubAccount(), l_product);
    
            //1.6 validateブックビルディング銘柄
            l_orderValidator.validateBookbuildingProduct(l_product);
            
            //1.7 validate二重申告
            l_orderValidator.validateDuplicateOrder(this.getSubAccount(), l_product);
            
//            //1.8 is目論見書既読
//            WEB3GentradeDocumentSystemConnectService l_documentSystemConnect = 
//                (WEB3GentradeDocumentSystemConnectService)Services.getService(WEB3GentradeDocumentSystemConnectService.class);
//            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getSubAccount().getMainAccount();
//            boolean l_blnProspectusAccept = l_documentSystemConnect.isProspectusAccept(
//                l_mainAccount,
//                ((IpoProductParams)l_product.getDataSourceObject()).getProductType(),
//                ((IpoProductParams)l_product.getDataSourceObject()).getProductCode());
        
//            //1.9 is停止中
//            boolean l_blnStop = l_documentSystemConnect.isSystemStop();


            //1.8 getその他商品買付可能額(補助口座,受渡日)
            TradingSystem l_trdSys = l_finApp.getTradingSystem();         
            WEB3TPTradingPowerService l_tpTPS  
                = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            double l_trdPow = l_tpTPS.getOtherTradingPower((WEB3GentradeSubAccount)this.getSubAccount(),l_trdSys.getBizDate() );
            
            //1.9 (*) 電子鳩チェックを行う（リクエストデータ.電子鳩チェックフラグ == true
            //且つ IPO銘柄.get目論見書閲覧区分() == 0:閲覧要）の場合
            boolean l_blnBatoCheckFlag = l_request.batoCheckFlag;
            
            WEB3GentradeProspectusResult l_prospectusResult = null;
            
            if (l_blnBatoCheckFlag
                && WEB3DocReadingDivDef.DEFAULT.equals(l_product.getDocReadingDiv()))
            {
                //1.9.1  validate目論見書閲覧(String, String)
                WEB3GentradeBatoClientService l_batoClientService = (WEB3GentradeBatoClientService) Services.getService(WEB3GentradeBatoClientService.class);
                
                String l_strProductCode = ((IpoProductParams)l_product.getDataSourceObject()).getProductCode();
                
                log.debug("銘柄コード: " + l_strProductCode);
                
                if (l_strProductCode != null)
                {
                    l_strProductCode = l_strProductCode.trim();
                    if (l_strProductCode.endsWith("1"))
                    {
                        if (l_strProductCode.length() >= 4)
                        {
                            l_strProductCode = l_strProductCode.trim().substring(0, 4);
                        }
                        else
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                getClass().getName() + STR_METHOD_NAME,
                                "銘柄コードの桁数は4桁より小さい。");
                        }
                    }
                    else
                    {
                        if (l_strProductCode.length() >= 5)
                        {
                            l_strProductCode = l_strProductCode.trim().substring(0, 5);
                        }
                        else
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                getClass().getName() + STR_METHOD_NAME,
                                "銘柄コードの桁数は5桁より小さい。");
                        }
                    }
                }
                
                l_prospectusResult = l_batoClientService.validateProspectus(l_request.typeCode, l_strProductCode);
            }
            
            
            //1.10 IPOブックビルディング申告入力レスポンス
            l_response = 
                (WEB3IPOBookBuildingDemandInputResponse)l_request.createResponse();
            
            //1.11 プロパティセット
            IpoProductRow l_productRow = (IpoProductRow)l_product.getDataSourceObject();
            
            //銘柄コード
            
            l_response.productCode = l_productRow.getProductCode();
            
            //銘柄名
            l_response.productName = l_product.getStandardName();
            
            //公開市場コード
            l_response.publicOfferingMarketCode = l_product.getPublicMarket();
            
            //仮条件区分
            l_response.temporaryConditionDiv = l_productRow.getProvisionalValueDiv();
            
            //仮条件下限値
            if(!l_productRow.getProvisionalMinValueIsNull())
            {
                l_response.temporaryConditionLower = WEB3StringTypeUtility.formatNumber(
                    l_productRow.getProvisionalMinValue());
            }
            
            //仮条件上限値
            if(!l_productRow.getProvisionalMaxValueIsNull())
            {
                l_response.temporaryConditionUpper = WEB3StringTypeUtility.formatNumber(
                    l_productRow.getProvisionalMaxValue());
            }
                
            //刻み
            if(!l_productRow.getTickValueIsNull())
            {
                l_response.tickValue = WEB3StringTypeUtility.formatNumber(
                    l_productRow.getTickValue());
            }
            
            //表示用単位区分
            l_response.displayUnitDiv = l_productRow.getIpoUnitDiv();
            
            //購入申込単位
            if(!l_productRow.getLotSizeIsNull())
            {
                l_response.offerUnit = WEB3StringTypeUtility.formatNumber(l_productRow.getLotSize());
            }
            
            //成行可能
            l_response.marketOrderFlag = l_productRow.getEnableMarketOrder();
            
            //出金余力
            l_response.paymentPower = WEB3StringTypeUtility.formatNumber(l_trdPow);
                
//            //目論見書同意
//            l_response.prospectusAgreement = l_blnProspectusAccept; 
            
            //電子鳩障害フラグ
//            WEB3GentradeDocumentSystemConnectService l_documentSystemConnect = 
//                (WEB3GentradeDocumentSystemConnectService)Services.getService(WEB3GentradeDocumentSystemConnectService.class);
//            l_response.batoTroubleFlag = l_documentSystemConnect.isSystemStop();
            
            //レスポンスデータ.目論見書閲覧チェック結果
            if (l_blnBatoCheckFlag)
            {
                log.debug("電子鳩チェックを行う場合、validate目論見書閲覧()の戻り値。");
                l_response.prospectusResult = l_prospectusResult;
            }
            else
            {
                log.debug("電子鳩チェックを行う以外場合、nullの戻り値。");
                l_response.prospectusResult = null;
            }
        }
        catch (NotFoundException l_e)
        {
            String l_strErrorMessage = "get入力画面error!";
            log.error(l_strErrorMessage, l_e);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage, 
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * (validateブックビルディング申告)<BR>
     * IPOブックビルディング申告確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告）validateブックビルディング申告」参照。<BR>
     * @@param l_request - IPOブックビルディング申告確認リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderConfirmResponse
     * @@roseuid 40D2843A0268
     */
    protected WEB3IPOBookBuildingDemandConfirmResponse validateBookbuildingOrder(
        WEB3IPOBookBuildingDemandConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateBookbuildingOrder(WEB3IpoBookbuildingOrderConfirmRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3IPOBookBuildingDemandConfirmResponse l_response = null;
        try
        {
            //1.1 validate
            l_request.validate();
            
            //1.2 validate注文受付可能
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //1.3 get発注日
            Date l_bizOrderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //1.4 get代理入力者
            Trader l_trader = this.getTrader();
            
			//1.5 ブックビルディング申告内容
			// 2004/11/4 U00361 BB申告内容インスタンス生成不具合のため"0"をセット　@坂上@@SRA START
			//　@成行でかつ申告価格がNullの時、申告価格は"0"をセットする
			//if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.demandPriceDiv) && l_request.demandPrice == null){
			//	l_request.demandPrice = "0";
			//}
			// 2004/11/4 U00361 BB申告内容インスタンス生成不具合のため"0"をセット　@坂上@@SRA END
			
			// 2004/11/10 U00393 BB申告内容インスタンス生成不具合のため一時変数に"0"をセット　@坂上@@SRA START
			//　@申告価格の一時変数l_dbldemandPriceをセットし、それを引数として渡す
			  double l_dbldemandPrice = 0;
			  if(WEB3StringTypeUtility.isNumber(l_request.demandPrice)){
				  l_dbldemandPrice = Double.parseDouble(l_request.demandPrice);
			  }
					  WEB3IpoBookbuildingNewOrderSpec l_ipoBookBuildingOrderSpec = 
									  new WEB3IpoBookbuildingNewOrderSpec(
											  l_trader,
											  Long.parseLong(l_request.id),
											  Double.parseDouble(l_request.demandQuantity),
											  l_dbldemandPrice,
											  0);			
//					  WEB3IpoBookbuildingNewOrderSpec l_ipoBookBuildingOrderSpec = 
//									  new WEB3IpoBookbuildingNewOrderSpec(
//											  l_trader,
//											  Long.parseLong(l_request.id),
//											  Double.parseDouble(l_request.demandQuantity),
//											  Double.parseDouble(l_request.demandPrice),
//											  0);
			// 2004/11/10 U00393 BB申告内容インスタンス生成不具合のため一時変数に"0"をセット　@坂上@@SRA END            
            
            //1.6 get補助口座
            SubAccount l_subAccount = this.getSubAccount();
            
            //1.7 validateブックビルディング申告
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
            NewOrderValidationResult l_orderValidationResult = l_ipoOrderManager.validateNewOrder(
                l_subAccount, 
                ProductTypeEnum.IPO, 
                l_ipoBookBuildingOrderSpec);
                
            if(!l_orderValidationResult.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
            {
                String l_strErrorMessage = "invalidateブックビルディング申告.";
                log.error(l_strErrorMessage);
                // 2004/11/08 障害管理票No.U00377 出力エラー修正 水落@@SRA START
                throw new WEB3BaseException(
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    l_orderValidationResult.getProcessingResult().getErrorInfo(), 
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);
                // 2004/11/08 障害管理票No.U00377 出力エラー修正 水落@@SRA END  
            }
            
            //1.8 calc申告相当額
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount )
                this.getSubAccount().getMainAccount();
                
            //IPO銘柄
            WEB3IpoProductManagerImpl l_productManager = 
                (WEB3IpoProductManagerImpl)l_tradingModule.getProductManager();
            long l_lngProductID = Long.parseLong(l_request.id);
            WEB3IpoProductImpl l_product = 
                (WEB3IpoProductImpl)l_productManager.getProduct(l_lngProductID);

            WEB3IpoBookbuildingPriceCalcResult l_ipoBookBuildingPriceCalcResult = 
                l_ipoOrderManager.calcBookbuildingPrice(
                    l_mainAccount,
                    l_product,
                    l_ipoBookBuildingOrderSpec.getLimitPrice(),
                    l_ipoBookBuildingOrderSpec.getQuantity(),
                    0);
                    
            //1.9 get申告相当額
            double l_dblOrderPrice = l_ipoBookBuildingPriceCalcResult.getBookbuildingPrice();
            
            //1.10 get基準値（時価）
            double l_dblBasePrice = l_ipoBookBuildingPriceCalcResult.getCurrentPrice();
            
            //1.11 createResponse(
            l_response = (WEB3IPOBookBuildingDemandConfirmResponse)l_request.createResponse();
             
            //1.12 プロパティセット
            l_response.demandEquivalentPrice = WEB3StringTypeUtility.formatNumber(l_dblOrderPrice);
            l_response.checkDate = l_bizOrderDate;
            
            if(!Double.isNaN(l_dblBasePrice))
            {
                l_response.checkValue = WEB3StringTypeUtility.formatNumber(l_dblBasePrice);
            }
            else
            {
                l_response.checkValue = "0";
            }
            
        }
        catch(NotFoundException l_e)
        {
            String l_strErrorMessage = "validateブックビルディング申告-error.";
             log.error(l_strErrorMessage, l_e);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                 this.getClass().getName() + STR_METHOD_NAME,
                 l_strErrorMessage, 
                 l_e);  
        }

        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (submitブックビルディング申告)<BR>
     * IPOブックビルディング申告完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告）submitブックビルディング申告」参照。<BR>
     * @@param l_request - IPOブックビルディング申告完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderCompleteRequest
     * @@roseuid 40D2843A0278
     */
    protected WEB3IPOBookBuildingDemandCompleteResponse submitBookbuildingOrder(
        WEB3IPOBookBuildingDemandCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitBookbuildingOrder(WEB3IpoBookbuildingOrderCompleteRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3IPOBookBuildingDemandCompleteResponse l_response = null;
        try
        {
            //1.1 validate
            l_request.validate();
            
            //1.2 validate注文受付可能
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //1.3 get発注日
            Date l_bizOrderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //1.4 get代理入力者
            Trader l_trader = this.getTrader();
            
			//1.5 ブックビルディング申告内容
			//2004/11/4 BB申告内容インスタンス生成不具合のため"0"をセット　@坂上@@SRA START
			//　@成行でかつ申告価格がNullの時、申告価格は"0"をセットする
			//if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.demandPriceDiv) && l_request.demandPrice == null){
			//	 l_request.demandPrice = "0";
			// }
			// 2004/11/4 BB申告内容インスタンス生成不具合のため"0"をセット　@坂上@@SRA END
            
			//2004/11/10 U00393 BB申告内容インスタンス生成不具合のため一時変数に"0"をセット　@坂上@@SRA START
			double l_dbldemandPrice = 0;
						  if(WEB3StringTypeUtility.isNumber(l_request.demandPrice)){
							  l_dbldemandPrice = Double.parseDouble(l_request.demandPrice);
						  }
			//2004/11/10 U00393 BB申告内容インスタンス生成不具合のため一時変数に"0"をセット　@坂上@@SRA END
            
			double l_dblCheckValue = 0;
			if(l_request.checkValue != null)
			{
				l_dblCheckValue = Double.parseDouble(l_request.checkValue);
			}
            
			//2004/11/10 U00393 BB申告内容インスタンス生成不具合のため一時変数に"0"をセット　@坂上@@SRA START
			WEB3IpoBookbuildingNewOrderSpec l_ipoBookBuildingOrderSpec = 
							new WEB3IpoBookbuildingNewOrderSpec(
									l_trader,
									Long.parseLong(l_request.id),
									Double.parseDouble(l_request.demandQuantity),
									l_dbldemandPrice,
									l_dblCheckValue);
//			  WEB3IpoBookbuildingNewOrderSpec l_ipoBookBuildingOrderSpec = 
//				  new WEB3IpoBookbuildingNewOrderSpec(
//						  l_trader,
//						  Long.parseLong(l_request.id),
//						  Double.parseDouble(l_request.demandQuantity),
//						  Double.parseDouble(l_request.demandPrice),
//						  l_dblCheckValue);
			//2004/11/10 U00393 BB申告内容インスタンス生成不具合のため一時変数に"0"をセット　@坂上@@SRA END
	          
            
            //1.6 get補助口座
            SubAccount l_subAccount = this.getSubAccount();
            
            //1.7 validateブックビルディング申告
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl)l_tradingModule.getOrderManager();
            NewOrderValidationResult l_orderValidationResult = l_ipoOrderManager.validateNewOrder(
                l_subAccount, 
                ProductTypeEnum.IPO, 
                l_ipoBookBuildingOrderSpec);
            if(!l_orderValidationResult.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
            {
                String l_strErrorMessage = "invalidateブックビルディング申告.";
                log.error(l_strErrorMessage);
                // 2004/11/08 障害管理票No.U00377 出力エラーの修正 水落@@SRA START
                throw new WEB3BaseException(
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);
                // 2004/11/08 障害管理票No.U00377 出力エラーの修正 水落@@SRA END  
            }
            
            //1.8 submitブックビルディング申告
            OrderSubmissionResult submissionResult = l_ipoOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.IPO,
                l_ipoBookBuildingOrderSpec,
                l_orderValidationResult.getNewOrderId(),
                l_request.password,
                true);
            if(!submissionResult.getProcessingResult().equals(ProcessingResult.SUCCESS_RESULT))
            {
                String l_strErrorMessage = "submitブックビルディング申告error!";
                log.error(l_strErrorMessage);
                // 2004/11/09 障害管理票No.U00392 出力エラー修正 水落@@SRA START 
                throw new WEB3BaseException(
                    //WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    submissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);  
                // 2004/11/09 障害管理票No.U00392 出力エラー修正 水落@@SRA END
            }
            
            //1.9 IPO申告
            WEB3IpoOrderImpl l_ipoOrder = 
                (WEB3IpoOrderImpl)l_ipoOrderManager.getOrderUnit(l_orderValidationResult.getNewOrderId());
                        
            //1.10 createResponse(
            l_response = (WEB3IPOBookBuildingDemandCompleteResponse)l_request.createResponse();
            
            //1.11 プロパティセット
            l_response.orderActionId = 
                WEB3StringTypeUtility.formatNumber(l_orderValidationResult.getNewOrderId());

            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
            l_response.lastUpdatedTimestamp = 
                l_tradingSystem.getSystemTimestamp();
            
        }
        catch (NotFoundException l_e)
        {
            String l_strErrorMessage = "IPO申告error!";
            log.error(l_strErrorMessage, l_e);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage, 
                l_e);  
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (get部店プリファ@レンス)<BR>
     * 顧客オブジェクトより、部店用プリファ@レンステーブルから<BR>
     * IPO居住者チェックを取得する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@　@部店プリファ@レンステーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@部店ID = 補助口座.getBranch().getBranchId() And<BR>
     * 　@　@プリファ@レンス名 = プリファ@レンス名.IPO居住者チェック And <BR>
     * 　@　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * 　@　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果.プリファ@レンスの値を返却する。<BR>
     * @@param l_subAccount - (補助口座オブジェクト)<BR>
     * 補助口座オブジェクト。
     * @@return Integer
     * @@throws WEB3BaseException
     */
    protected Integer getBranchPreferences(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchPreferences(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        try 
        {
            //１）DB検索 
            //  部店プリファ@レンステーブルを以下の条件で検索する。
            BranchPreferencesRow l_branchReferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccount.getWeb3GenBranch().getBranchId(), 
                    WEB3BranchPreferencesNameDef.IPO_RESIDENT_CHECK,
                    1);
            
            log.exiting(STR_METHOD_NAME);
            
            //２）　@検索結果.プリファ@レンスの値を返却する。
            if (l_branchReferencesRow == null)
            {
                return null;
            }
            else
            {
                return Integer.valueOf(l_branchReferencesRow.getValue());
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

}
@
