head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOfferServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPO購入申込サービス実装(WEB3IpoOfferServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 杜珉 新規作成
Revesion History : 2004/12/27 坂上(SRA) 残対応>>>036
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>044
Revesion History : 2005/01/11 坂上(SRA) 残対応>>>036(電子鳩障害のプロパティ削除)
*/
package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnableIpoQuantityChangeDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.ipo.WEB3IpoChangeOrderSpec;
import webbroker3.ipo.WEB3IpoClientRequestService;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoOrderValidator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.message.WEB3IPOOfferCompleteRequest;
import webbroker3.ipo.message.WEB3IPOOfferCompleteResponse;
import webbroker3.ipo.message.WEB3IPOOfferConfirmRequest;
import webbroker3.ipo.message.WEB3IPOOfferConfirmResponse;
import webbroker3.ipo.message.WEB3IPOOfferInputRequest;
import webbroker3.ipo.message.WEB3IPOOfferInputResponse;
import webbroker3.ipo.service.delegate.WEB3IpoOfferService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
* ( IPO購入申込サービス実装)<BR>
* 
* @@author 杜珉
* @@version 1.0
*/
public class WEB3IpoOfferServiceImpl
    extends WEB3IpoClientRequestService implements WEB3IpoOfferService 
{
        
     
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoOfferServiceImpl.class);
    /**
     * @@roseuid 4112F19102B5
     */
    public WEB3IpoOfferServiceImpl() 
    {

    }

    /**
     * IPO購入申込処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、IPO購入申込入力リクエストの場合<BR>
     * 　@－get入力画面()をコールする。<BR>
     * ○ 引数のリクエストデータが、IPO購入申込確認リクエストの場合<BR>
     * 　@－validate購入申込()をコールする。<BR>
     * ○ 引数のリクエストデータが、IPO購入申込完了リクエストの場合<BR>
     * 　@－submit購入申込()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40DA5BBF00CF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        if (l_request instanceof WEB3IPOOfferInputRequest) 
        {
            log.exiting(STR_METHOD_NAME);
            return this.getOfferInput((WEB3IPOOfferInputRequest) l_request);
        } 
        else if (l_request instanceof WEB3IPOOfferConfirmRequest) 
        {
            log.exiting(STR_METHOD_NAME);
            return this.validateOffer((WEB3IPOOfferConfirmRequest) l_request);
        } 
        else if (l_request instanceof WEB3IPOOfferCompleteRequest) 
        {
            log.exiting(STR_METHOD_NAME);
            return this.submitOffer((WEB3IPOOfferCompleteRequest) l_request);
        }
        else
        {
            String l_strErrorMessage = 
                "パラメータの類型が不正、該当するWEB3IPOBookBuildingEnterRequest," + 
                "WEB3IPOProductInfoRequest類型。";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

    }

    /**
     * (get入力画面)<BR>
     * IPO購入申込画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（購入申込）get入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO購入申込入力リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoOfferInputResponse
     * @@roseuid 40DA5BBF00FE
     */
    protected WEB3IPOOfferInputResponse getOfferInput(WEB3IPOOfferInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOfferInput(WEB3IpoOfferInputRequest)";
        log.entering(STR_METHOD_NAME);
        //1.1 validate注文受付可能メソッドをコール
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.2 OrderValidatorを取得する    
        WEB3IpoOrderManagerImpl l_orderManger = null;
        FinApp finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule tradingModule =
            finApp.getTradingModule(ProductTypeEnum.IPO);
        l_orderManger = (WEB3IpoOrderManagerImpl) tradingModule.getOrderManager();
        l_orderManger.getOrderValidator();

        //1.3 補助口座を取得する 
        SubAccount l_subAccount = this.getSubAccount();

        //1.4 validate取引可能顧客メソッドをコール
        WEB3IpoOrderValidator l_orderCheck = new WEB3IpoOrderValidator();
        
        // 2004/12/01 障害管理票No.U00496 取引可能顧客の処理結果が異常だった場合例外をスロー 情野@@SRA START 
		OrderValidationResult l_orderValidationResult = l_orderCheck.validateSubAccountForTrading(l_subAccount);
		if(l_orderValidationResult.getProcessingResult().isFailedResult())
		{
			log.exiting(STR_METHOD_NAME);
			
			throw new WEB3BaseException(
			l_orderValidationResult.getProcessingResult().getErrorInfo(),
			this.getClass().getName() + STR_METHOD_NAME,
			l_orderValidationResult.getProcessingResult().getErrorInfo().error_message); 
		}
//		l_orderCheck.validateSubAccountForTrading(l_subAccount);	 
		// 2004/12/01 障害管理票No.U00496 取引可能顧客の処理結果が異常だった場合例外をスロー 情野@@SRA END 
            

        //1.5 IPO申告
        long l_lngOrderId = Long.parseLong(l_request.id);
        log.debug("l_lngOrderId = " + l_lngOrderId);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderImpl l_ipoOrder = null;
        try 
        {
            WEB3IpoOrderManagerImpl l_ipoOrderManager = 
                (WEB3IpoOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
            l_ipoOrder = (WEB3IpoOrderImpl)l_ipoOrderManager.getOrderUnit(l_lngOrderId);
                    
            log.debug("l_ipoOrder = " + l_ipoOrder);
            //1.6 IPO銘柄を取得する
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl) l_ipoOrder.getProduct();

//            //is目論見書既読メソッドをコール
//            WEB3GentradeDocumentSystemConnectService l_service = 
//                (WEB3GentradeDocumentSystemConnectService)Services.getService(WEB3GentradeDocumentSystemConnectService.class);
//            boolean l_blnProspectusAccept = l_service.isProspectusAccept((WEB3GentradeMainAccount) l_subAccount.getMainAccount(),
//            ((IpoProductRow)l_product.getDataSourceObject()).getProductType(), 
//            ((IpoProductRow)l_product.getDataSourceObject()).getProductCode());
//        
//            //is停止中メソッドをコール
//            boolean l_blnSystemStop = l_service.isSystemStop();

            //1.7 validate購入申込可能銘柄メソッドをコール
            l_orderCheck.validateOfferPossibleProduct((WEB3IpoProductImpl) l_product);

            //1.8 validate購入申込・辞退可能メソッドをコール
            l_orderCheck.validateOfferDeclinePossible(l_ipoOrder);

            //1.9 validate二重申込・辞退メソッドをコール
            l_orderCheck.validateDuplicateAppDecline(l_ipoOrder);

            //1.10 MainAccountを取得する
            WEB3GentradeMainAccount l_mainAccount  = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        
            //1.11 is特定口座開設メソッドをコール
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_product.getDataSourceObject();
            Timestamp l_publicOfferingDate = l_ipoProductRow.getPublicOfferingDate();
            boolean l_blnSpecialAccountEstablished = l_mainAccount.isSpecialAccountEstablished(l_publicOfferingDate, l_subAccount);

            //1.12 Institutionを取得する
            InstitutionParams l_institution = (InstitutionParams)l_subAccount.getInstitution().getDataSourceObject();
            
            //1.13:(*) 電子鳩チェックを行う（リクエストデータ.電子鳩チェックフラグ == true）の場合
            WEB3GentradeProspectusResult l_prospectusResult = null;
            if (l_request.batoCheckFlag)
            {
                WEB3GentradeBatoClientService l_service = (WEB3GentradeBatoClientService)
                    Services.getService(WEB3GentradeBatoClientService.class);
                
                //1.13.1:validate目論見書閲覧(種別コード : String, 銘柄コード : String)
                //銘柄コード：　@IPO銘柄().銘柄コード※
                IpoProductRow l_row = (IpoProductRow)l_product.getDataSourceObject();
                String l_strProductCode = l_row.getProductCode();
                log.debug("initial:IPO銘柄().銘柄コード:" + l_strProductCode);
                if(l_strProductCode != null)
                {
                    l_strProductCode = l_strProductCode.trim();
                    int l_intLength = l_strProductCode.length();
                    
                    if (l_strProductCode.endsWith("1"))
                    {
                        if ( l_intLength >= 4)
                        {
                            l_strProductCode = l_strProductCode.substring(0, 4);
                        }
                        else
                        {
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                                this.getClass().getName() + STR_METHOD_NAME,
                                "銘柄コードの桁数は4桁より小さい。");  
                        }
                    }
                    else
                    {
                        if (l_intLength >= 5)
                        {
                            l_strProductCode = l_strProductCode.substring(0, 5);
                        }
                        else
                        {
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                                this.getClass().getName() + STR_METHOD_NAME,
                                "銘柄コードの桁数は5桁より小さい。");  
                        }
                    }
                }
                log.debug("final:IPO銘柄().銘柄コード:" + l_strProductCode);
                l_prospectusResult = l_service.validateProspectus(l_request.typeCode, l_strProductCode);
            }

            //1.14 IPO購入申込入力レスポンス
            WEB3IPOOfferInputResponse l_response =(WEB3IPOOfferInputResponse) l_request.createResponse();

            //プロパティセット   
        
            IpoOrderRow l_IpoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
        
            l_response.productCode = l_ipoProductRow.getProductCode();
        
            l_response.productName = l_product.getStandardName();  
        
            l_response.publicOfferingMarketCode = l_product.getPublicMarket();
        
            l_response.offerUnit = WEB3StringTypeUtility.formatNumber(l_product.getLotSize());
        
            l_response.displayUnitDiv = l_ipoProductRow.getIpoUnitDiv();
        
            l_response.prizeQuantity = Long.toString(l_IpoOrderRow.getElectedQuantity());
            
            double l_dblQuantity = l_ipoOrder.getQuantity();
            log.debug("l_dblQuantity = " + l_dblQuantity);
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }
            l_response.demandQuantity =WEB3StringTypeUtility.formatNumber(l_dblQuantity);
        
            if(l_blnSpecialAccountEstablished )
            {
                
                String[] taxType = new String[2];
                taxType[0] = WEB3TaxTypeDef.NORMAL;
                taxType[1] = WEB3TaxTypeDef.SPECIAL;
                l_response.taxTypeList = taxType;

            }
            else if( !(l_blnSpecialAccountEstablished) )
            {
            	// 2004/11/30 障害管理票No.U00489 配列(口座区分一覧)の初期化 情野@@SRA START            
                l_response.taxTypeList = new String[]{WEB3TaxTypeDef.NORMAL};
//				l_response.taxTypeList[0] = WEB3TaxTypeDef.NORMAL;  
                // 2004/11/30 障害管理票No.U00489 配列(口座区分一覧)の初期化 情野@@SRA END
            }
            if( WEB3EnableIpoQuantityChangeDef.CAN_CHANGE.equals(l_institution.getEnableIpoQuantityChange()) )
            {
                l_response.offerQuantityFlag = true;
            }
            else
            {
                l_response.offerQuantityFlag = false;
            }
            log.debug("l_response.offerQuantityFlag = " + l_response.offerQuantityFlag);      
//            l_response.prospectusAgreement = l_blnProspectusAccept;
  
//            //電子鳩障害フラグ
//            WEB3GentradeDocumentSystemConnectService l_documentSystemConnect = 
//                (WEB3GentradeDocumentSystemConnectService)Services.getService(WEB3GentradeDocumentSystemConnectService.class);
//            l_response.batoTroubleFlag = l_documentSystemConnect.isSystemStop();

            if (l_request.batoCheckFlag)
            {
                l_response.prospectusResult = l_prospectusResult;
            }
            else
            {
                l_response.prospectusResult = null;
            }
            
            //レスポンスオブジェクトを返却する。
            
            log.exiting(STR_METHOD_NAME);
            
            return l_response;        

                    
        } 
        catch (NotFoundException l_ex) 
        {
            log.error("データ不整合エラー。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
    }

    /**
     * (validate購入申込)<BR>
     * IPO購入申込確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（購入申込）validate購入申込」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO購入申込確認リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoOfferConfirmResponse
     * @@roseuid 40DA5C720014
     */
    protected WEB3IPOOfferConfirmResponse validateOffer(WEB3IPOOfferConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOffer(WEB3IpoOfferConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate注文受付可能メソッドをコール
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //発注日を取得する     
        Date l_datOrderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //代理入力者を取得する
        Trader l_trader = this.getTrader();

        //購入申込内容
        long l_lngId = Long.parseLong(l_request.id);
        double l_dblOfferQty = Double.parseDouble(l_request.offerQuantity);
        
        WEB3IpoChangeOrderSpec l_offerSpec = null;
        if (WEB3TaxTypeDef.NORMAL.equals(l_request.taxType)) 
        {
            l_offerSpec = new WEB3IpoChangeOrderSpec(l_trader, l_lngId, l_dblOfferQty, TaxTypeEnum.NORMAL);
        } 
        else if (WEB3TaxTypeDef.SPECIAL.equals(l_request.taxType)) 
        {
            l_offerSpec = new WEB3IpoChangeOrderSpec(l_trader, l_lngId, l_dblOfferQty, TaxTypeEnum.SPECIAL);
        }
        log.debug("l_request.taxType = " + l_request.taxType);
        //補助口座を取得する 
        SubAccount l_subAccount = this.getSubAccount();

        //validate購入申込メソッドをコール
        WEB3IpoOrderManagerImpl l_orderManger = null;
        FinApp finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule tradingModule =
            finApp.getTradingModule(ProductTypeEnum.IPO);
        l_orderManger =
            (WEB3IpoOrderManagerImpl) tradingModule.getOrderManager();
        
		// 2004/12/01 障害管理票No.U00496 validate購入申込の処理結果が異常だった場合例外をスロー 情野@@SRA END 
		OrderValidationResult l_orderValidationResult = l_orderManger.validateOffer(l_subAccount, l_offerSpec);
		if(l_orderValidationResult.getProcessingResult().isFailedResult())
		{
			log.exiting(STR_METHOD_NAME);
					
			throw new WEB3BaseException(
			l_orderValidationResult.getProcessingResult().getErrorInfo(),
			this.getClass().getName() + STR_METHOD_NAME,
			l_orderValidationResult.getProcessingResult().getErrorInfo().error_message); 
		}
//		l_orderManger.validateOffer(l_subAccount, l_offerSpec);
		// 2004/12/01 障害管理票No.U00496 validate購入申込の処理結果が異常だった場合例外をスロー 情野@@SRA END

    
        //IPO申告
        long l_lngOrderId = Long.parseLong(l_request.id);
        log.debug("l_lngOrderId = " + l_lngOrderId);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
 
        WEB3IpoOrderImpl l_ipoOrder;
        try 
        {
            l_ipoOrder =
                (WEB3IpoOrderImpl) l_finApp
                    .getTradingModule(ProductTypeEnum.IPO)
                    .getOrderManager()
                    .getOrderUnit(l_lngOrderId);
                    
            //税区分を取得する 
            TaxTypeEnum l_taxType = l_offerSpec.getTaxType();

            //購入申込数量を取得する 
            double l_dblOfferQuantity = l_offerSpec.getApplicationQuantity();

            //購入申込
            l_ipoOrder.offer(l_trader, l_dblOfferQuantity, l_taxType);

            //購入申込代金を取得する
            double l_dblOfferPrice = l_ipoOrder.getPayAmount();

            //IPO銘柄を取得する
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl) l_ipoOrder.getProduct();

            //createResponse

            WEB3IPOOfferConfirmResponse l_offerConfirmResponse =
                (WEB3IPOOfferConfirmResponse) l_request.createResponse();

            //プロパティセット
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_product.getDataSourceObject();

            double l_dblQuantity = l_ipoProductRow.getPublicPrice();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }            
            l_offerConfirmResponse.publicOfferingPrice = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            l_offerConfirmResponse.offerPrice = WEB3StringTypeUtility.formatNumber(l_dblOfferPrice);;
            l_offerConfirmResponse.checkDate = l_datOrderBizDate;

            //レスポンスオブジェクトを返却する。
            
            log.exiting(STR_METHOD_NAME);
            return l_offerConfirmResponse;
                    
        } 
        catch (NotFoundException l_ex) 
        {
            log.error("データ不整合エラー。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }


    }

    /**
     * (submit購入申込)<BR>
     * IPO購入申込完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（購入申込）submit購入申込」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO購入申込完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoOfferCompleteResponse
     * @@roseuid 40DA5C720033
     */
    protected WEB3IPOOfferCompleteResponse submitOffer(WEB3IPOOfferCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitOffer(WEB3IpoOfferCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        //validate注文受付可能メソッドをコール
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //発注日を取得する     
        WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //代理入力者を取得する
        Trader l_trader = this.getTrader();

        //購入申込内容
        long l_lngId = Long.parseLong(l_request.id);
        log.debug("l_lngId = " + l_lngId);
        double l_dblOfferQty = Double.parseDouble(l_request.offerQuantity);
        log.debug("l_dblOfferQty = " + l_dblOfferQty);
        
        WEB3IpoChangeOrderSpec l_offerSpec = null;
        if (WEB3TaxTypeDef.NORMAL.equals(l_request.taxType)) 
        {
            l_offerSpec = new WEB3IpoChangeOrderSpec(l_trader, l_lngId, l_dblOfferQty, TaxTypeEnum.NORMAL);
        }
        else if (WEB3TaxTypeDef.SPECIAL.equals(l_request.taxType)) 
        {
            l_offerSpec = new WEB3IpoChangeOrderSpec( l_trader, l_lngId, l_dblOfferQty, TaxTypeEnum.SPECIAL);
        }
        log.debug("l_request.taxType = " + l_request.taxType);
        //補助口座を取得する 
        SubAccount l_subAccount = this.getSubAccount();

        //submit購入申込メソッドをコール
        WEB3IpoOrderManagerImpl l_orderManger = null;
        FinApp finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule tradingModule =
            finApp.getTradingModule(ProductTypeEnum.IPO);
        l_orderManger = (WEB3IpoOrderManagerImpl) tradingModule.getOrderManager();
        
		// 2004/12/01 障害管理票No.U00496 submit購入申込の処理結果が異常だった場合例外をスロー 情野@@SRA END 
		OrderSubmissionResult l_orderSubmittionResult = l_orderManger.submitOffer(l_subAccount, l_offerSpec, l_request.password, false);
		if(l_orderSubmittionResult.getProcessingResult().isFailedResult())
		{
			log.exiting(STR_METHOD_NAME);
					
			throw new WEB3BaseException(
			l_orderSubmittionResult.getProcessingResult().getErrorInfo(),
			this.getClass().getName() + STR_METHOD_NAME,
			l_orderSubmittionResult.getProcessingResult().getErrorInfo().error_message); 
		}
//		l_orderManger.submitOffer(l_subAccount, l_offerSpec, l_request.password, false);
		// 2004/12/01 障害管理票No.U00496 submit購入申込の処理結果が異常だった場合例外をスロー 情野@@SRA END


        //1.7.余力再計算()
        WEB3TPTradingPowerService l_tpTPS  
            = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        l_tpTPS.reCalcTradingPower((WEB3GentradeSubAccount) l_subAccount);

        //1.8.IPO申告
        long l_lngOrderId = Long.parseLong(l_request.id);
        log.debug("l_lngOrderId = " + l_lngOrderId);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
 
        WEB3IpoOrderImpl l_ipoOrder;
        try 
        {
            l_ipoOrder =
                (WEB3IpoOrderImpl) l_finApp
                    .getTradingModule(ProductTypeEnum.IPO)
                    .getOrderManager()
                    .getOrderUnit(l_lngOrderId);
            
            //IPO申告ＩＤを取得する 
            long l_id = l_ipoOrder.getOrderId();

            //createResponse
            //WEB3IPOOfferCompleteRequest l_offerCompleteRequest = new WEB3IPOOfferCompleteRequest();
            WEB3IPOOfferCompleteResponse l_offerResponse = (WEB3IPOOfferCompleteResponse) l_request.createResponse();

            //プロパティセット
            //FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            Timestamp l_currentTime = l_finApp.getTradingSystem().getSystemTimestamp();
            l_offerResponse.lastUpdatedTimestamp = l_currentTime;
            l_offerResponse.orderActionId = "" + l_id;
            
            log.debug("l_offerResponse.lastUpdatedTimestamp = " + l_offerResponse.lastUpdatedTimestamp);
            log.debug("l_offerResponse.orderActionId = " + l_offerResponse.orderActionId);
            //レスポンスオブジェクトを返却する。
            log.exiting(STR_METHOD_NAME);
            return l_offerResponse;            

                    
        } 
        catch (NotFoundException l_ex) 
        {
            log.error("データ不整合エラー。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }

    }
}
@
