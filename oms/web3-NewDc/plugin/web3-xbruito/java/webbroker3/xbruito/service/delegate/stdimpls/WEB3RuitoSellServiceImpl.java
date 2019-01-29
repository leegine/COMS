head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資解約サービス実装クラス(WEB3RuitoSellServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 李志強 (中訊) 新規作成
                   2004/12/06 韋念瓊 (中訊) 残対応
*/

package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoNewOrderDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoNewOrderSpec;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoOrderManagerReusableValidationsCheck;
import webbroker3.xbruito.WEB3RuitoPositionManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.xbruito.message.WEB3RuitoSellCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoSellCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoSellConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoSellConfirmResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellService;

/**
 * 累積投資解約サービス実装クラス<BR>
 */
public class WEB3RuitoSellServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoSellService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellServiceImpl.class);

    /**
     * 累積投資解約サービス処理を実施する。<BR>
     * リクエストデータの型により、<BR>
     * validate解約()、submit解約()<BR>
     * いずれかのメソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405817F7012B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        if (l_request instanceof WEB3RuitoSellConfirmRequest)
        {
            //リクエストデータの具象データ型が「累投解約確認リクエスト」の場合
            log.exiting(STR_METHOD_NAME);
            return this.validateSell((WEB3RuitoSellConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3RuitoSellCompleteRequest)
        {
            //リクエストデータの具象データ型が「累投解約正完了リクエスト」の場合
            log.exiting(STR_METHOD_NAME);
            return this.submitSell((WEB3RuitoSellCompleteRequest) l_request);
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[入力値が不正です]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }        
    }

    /**
     * 累積投資解約審査を行う。<BR>
     * <BR>
     * シーケンス図「累投解約／（累投）解約審査」参照。<BR>
     * <BR>
     * 1.19 is判定フラグがfalseなら例外をスローする <BR>
     * 「取引余力エラー」<BR>
     *      classpath:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01306<BR>
     * <BR>
     * @@param l_request - 累積投資解約確認リクエストオブジェクト<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoSellConfirmResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405818CE031F
     */
    public WEB3RuitoSellConfirmResponse validateSell(WEB3RuitoSellConfirmRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateSell(WEB3RuitoSellConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        //1.1　@リクエストデータ妥当性チェック
        l_request.validate();

        //1.2　@補助口座取得
        SubAccount l_subAccount = getSubAccount();
        
        //1.3　@顧客別取引停止属性チェック
        //－FinApp.getCommonOrderValidator()をコールし、注文チェックオブジェクトを取得する。
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        //1.4 －注文チェック.validate取引可能顧客()をコールする。
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        //－チェックエラーの場合はを例外をスローする。
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引停止顧客エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "取引停止顧客チェックエラー");
        }
        
        //1.5　@累投銘柄取得  
        //－拡張累投銘柄マネージャを取得する。
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getProductManager();

        RuitoProduct l_ruitoProduct = null;
        WEB3RuitoProduct l_web3RuitoProduct = null;
        
        try
        {
            //－拡張累投銘柄マネージャ.get累投銘柄()をコールし、拡張累投銘柄を取得する。
            l_ruitoProduct =
                l_web3RuitoProductManager.getRuitoProduct(
                    l_subAccount.getInstitution(),
                    l_request.ruitoProductCode,
                    "0");
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }            
        l_web3RuitoProduct = (WEB3RuitoProduct) l_ruitoProduct;
        log.debug("l_web3RuitoProduct = " + l_web3RuitoProduct);

        long l_lngProductId = l_web3RuitoProduct.getProductId();
        log.debug("l_lngProductId = " + l_lngProductId);
        
        //1.6 累投銘柄の累投タイプを取得する
        RuitoTypeEnum l_ruitoType = l_web3RuitoProduct.getRuitoType();
        log.debug("l_ruitoType = " + l_ruitoType);
        
        //　@受付時間チェック、システム取引停止チェック
        //累投銘柄の累投タイプが、中国Fの場合
        if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoType))
        {
            log.debug("RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum)");
            //1.7 受付時間区分のリセットをする 
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
            
            //1.8 setTimestamp( )
            WEB3GentradeTradingTimeManagement.setTimestamp();
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        //累投銘柄の累投タイプが、MMFの場合
        else if (RuitoTypeEnum.MMF.equals(l_ruitoType))
        {
            log.debug("RuitoTypeEnum.MMF.equals(l_ruitoType)");
            //1.7 受付時間区分のリセットをする 
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MMF_SET_CANCEL);
            
            //1.8 setTimestamp( )
            WEB3GentradeTradingTimeManagement.setTimestamp();
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }

        WEB3RuitoOrderManagerReusableValidationsCheck l_ruitoOrderManagerReusableValidCheck =
            new WEB3RuitoOrderManagerReusableValidationsCheck();

        double l_dblSellPossibleBalance = 0;    //解約可能残高

        //1.10　@全部解約の場合に、解約可能残高を算出する
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            //拡張累投ポジションマネージャ.get解約可能残高()を 
            //コールし、解約可能残高を取得する。
            WEB3RuitoPositionManager l_web3RuitoPositionManager =
                (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getPositionManager();
                    
            l_dblSellPossibleBalance =       
                l_web3RuitoPositionManager.getSellPossibleBalance(
                    l_subAccount, l_web3RuitoProduct);
        }
        else
        {
            l_dblSellPossibleBalance =
                Double.parseDouble(l_request.ruitoOrderQuantity);  
        }            

        WEB3RuitoOrderManager l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();
        
        double l_dblOrderQuantity = 0; 
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {            
            l_dblOrderQuantity = l_dblSellPossibleBalance;
        }
        else
        {
            l_dblOrderQuantity =
                Double.parseDouble(l_request.ruitoOrderQuantity);
        }
        //1.11 発注審査を行う。
        NewOrderValidationResult l_newOrderValidationResult = 
            l_web3RuitoOrderManager.validateNewOrder(
                l_subAccount,
                l_web3RuitoProduct,
                l_dblOrderQuantity,
                false,
                l_request.deliveryDiv,
                l_request.specifyDiv);
        
        if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
        {
            log.debug("発注審査チェックエラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00174,
                this.getClass().getName() + STR_METHOD_NAME, 
                "発注審査チェックエラー");
        } 

        //1.20　@発注日取得
        Date l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //1.21　@注文ID採番 
        //拡張累投注文マネージャ.createNewOrderId()をコールして注文IDを採番する。
        long l_lngOrderId = l_web3RuitoOrderManager.createNewOrderId();
        
        //【累投解約確認レスポンスに設定する値】      
        WEB3RuitoSellConfirmResponse l_response =
            (WEB3RuitoSellConfirmResponse) l_request.createResponse();
        //確認時発注日： 取得した発注日を設定 
        l_response.checkDate = l_orderDate;
        //注文ID： 採番した注文IDを設定 
        l_response.orderId = l_lngOrderId + "";
        log.debug("注文ID：" + l_response.orderId);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit解約)
     * 累積投資解約登録処理を行う。<BR>
     * シーケンス図「（累投）解約登録」参照。<BR>
     * <BR>
     * 1.13 発注審査を行い、チェックエラーの場合は、例外をスローする。
     *      classpath:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00174<BR>
     * <BR>
     * 1.24 累投注文マネージャ.submitNewOrder()の戻り値判定<BR>
     *  　@　@累投注文マネージャ.submitNewOrder()の戻り値<BR>
     *      .getProcessingResult().isSuccessfulResult()==false<BR>
     * 　@ 　@の場合、例外をスローする。<BR>
     *      classpath:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00286<BR>
     * <BR>
     * @@param l_request - 累積投資解約完了リクエストオブジェクト<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoSellCompleteResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405818D502A2
     */
    public WEB3RuitoSellCompleteResponse submitSell(WEB3RuitoSellCompleteRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitSell(WEB3RuitoSellCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        //1.1　@リクエストデータ妥当性チェック
        l_request.validate();

        //1.2　@補助口座取得
        SubAccount l_subAccount = getSubAccount();

        //1.3　@顧客別取引停止属性チェック
        //－FinApp.getCommonOrderValidator()をコールし、注文チェックオブジェクトを取得する。
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        //1.4 －注文チェック.validate取引可能顧客()をコールする。
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        //－チェックエラーの場合はを例外をスローする。
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引停止顧客エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "取引停止顧客エラー");
        }
        
        //1.5 －this.get代理入力者( )をコールし、代理入力者オブジェクトを取得する。
        Trader l_trader = this.getTrader();        
        
        //1.6 －validate取引パスワード( )をコールする。
        log.debug("リクエストデータ.暗証番号 = " + l_request.password);
        l_orderValidationResult = l_orderValidator.validateTradingPassword(
                l_trader,
                l_subAccount,
                l_request.password);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引パスワード不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.7　@累投銘柄取得
        //－拡張累投銘柄マネージャを取得する。
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getProductManager();

        RuitoProduct l_ruitoProduct = null;
        String l_strNewNumber = null;
        RuitoTypeEnum l_ruitoType = null;
        WEB3RuitoProduct l_web3RuitoProduct = null;
        double l_dblRuitoOrderQuantity = 0;

        try
        {
           //－拡張累投銘柄マネージャ.get累投銘柄()をコールし、拡張累投銘柄を取得する。
           l_ruitoProduct =
                l_web3RuitoProductManager.getRuitoProduct(
                    l_subAccount.getInstitution(),
                    l_request.ruitoProductCode,
                    "0");

            l_web3RuitoProduct = (WEB3RuitoProduct) l_ruitoProduct;

            long l_lngProductId = l_ruitoProduct.getProductId();
            log.debug("l_lngProductId = " + l_lngProductId);
            
            //1.8  getRuitoType( )
            l_ruitoType = l_ruitoProduct.getRuitoType();
            log.debug("l_ruitoTypeEnum = " + l_ruitoType);

            //　@受付時間チェック、システム取引停止チェック 
            //累投銘柄の累投タイプが、中国Fの場合
            if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoType))
            {
                log.debug("RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum)");
                //1.9 受付時間区分のリセットをする 
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                    WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
                //1.10 setTimestamp( )
                WEB3GentradeTradingTimeManagement.setTimestamp();
                //1.11 validate注文受付可能( )
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            //累投銘柄の累投タイプが、MMFの場合
            else if (RuitoTypeEnum.MMF.equals(l_ruitoType))
            {
                log.debug("RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum)");
                //1.9 受付時間区分のリセットをする 
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                    WEB3TradingTimeTypeDef.MMF_SET_CANCEL);
                //1.10 setTimestamp( )
                WEB3GentradeTradingTimeManagement.setTimestamp();
                //1.11 validate注文受付可能( )
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }

            //1.12 全部解約の場合に、解約可能残高を算出する
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
            {
                //1.12.1 拡張累投ポジションマネージャ.get解約可能残高()を 
                //コールし、解約可能残高を取得する。
                WEB3RuitoPositionManager l_web3RuitoPositionManager =
                    (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                        ProductTypeEnum.RUITO).getPositionManager();
                        
                l_dblRuitoOrderQuantity =       
                    l_web3RuitoPositionManager.getSellPossibleBalance(
                        l_subAccount, l_web3RuitoProduct);
            }
            else
            {
                l_dblRuitoOrderQuantity =
                    Double.parseDouble(l_request.ruitoOrderQuantity);  
            }           

            //　@発注審査 
            WEB3RuitoOrderManager l_web3RuitoOrderManager =
                (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getOrderManager();
                    
            //1.13 発注審査を行い、チェックエラーの場合は、例外をスローする。 
            NewOrderValidationResult l_newOrderValidationResult = 
                l_web3RuitoOrderManager.validateNewOrder(
                    l_subAccount,
                    l_web3RuitoProduct,
                    l_dblRuitoOrderQuantity,
                    false,
                    l_request.deliveryDiv,
                    l_request.specifyDiv
                );
            if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
            {
                log.debug("発注審査チェックエラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00174,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "発注審査チェックエラー");
            }   
            
          //1.14　@確認時と発注日が同じかチェックする 
          WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

          //1.15　@識別コードを採番する 
          WEB3HostReqOrderNumberManageService l_WEB3HostReqOrderNumberManageService=
              (WEB3HostReqOrderNumberManageService)Services.getService(
                  WEB3HostReqOrderNumberManageService.class);
          
          l_strNewNumber = l_WEB3HostReqOrderNumberManageService.getNewNumber(
                  l_subAccount.getInstitution().getInstitutionCode(),
                  l_subAccount.getMainAccount().getBranch().getBranchCode(),
                  ProductTypeEnum.RUITO);         
          
            log.debug("l_strNewNumber = " + l_strNewNumber);        
      }
      catch (NotFoundException l_ex)
      {
          log.error("__NotFoundExcetion__", l_ex);
          log.exiting(STR_METHOD_NAME);
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80006,
              this.getClass().getName() + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);
      }

      //解約注文処理
      //1.16 累投新規注文確定インタセプタを生成する 
      WEB3RuitoNewOrderDecisionInterceptor l_web3RuitoNewOrderDecisionInterceptor =
          new WEB3RuitoNewOrderDecisionInterceptor();

      //累投新規注文確定インタセプタを累投注文マネージャに設定する
      WEB3RuitoOrderManager l_web3RuitoOrderManager =
          (WEB3RuitoOrderManager) l_finApp.getTradingModule(
              ProductTypeEnum.RUITO).getOrderManager();
      
      //1.17 累投新規累投新規注文確定インタセプタを設定する。 
      l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
          l_web3RuitoNewOrderDecisionInterceptor);

      //累投新規注文確定インタセプタに識別コードを設定する
      l_web3RuitoNewOrderDecisionInterceptor.setRequestNumber(l_strNewNumber);

      //累投新規注文確定インタセプタにnullを設定する
      l_web3RuitoNewOrderDecisionInterceptor.setMRFOrderRequestNumber(null);

      //累投新規注文確定インタセプタに返還方法@を設定する
      l_web3RuitoNewOrderDecisionInterceptor.setReturnMethod(null);

      //累投新規注文確定インタセプタに受渡方法@を設定する
      l_web3RuitoNewOrderDecisionInterceptor.setPaymentMethod(l_request.deliveryDiv);

      //累投新規注文確定インタセプタに累投タイプを設定する
      l_web3RuitoNewOrderDecisionInterceptor.setRuitoTypeEnum(l_ruitoType);

      //累投新規注文確定インタセプタに累投解約区分を設定する
      l_web3RuitoNewOrderDecisionInterceptor.setRuitoSellDiv(l_request.specifyDiv);

      //累投新規注文確定インタセプタに注文経路区分を設定する
	  OpLoginSecurityService l_opLoginSecService =
			  (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class); 
		l_web3RuitoNewOrderDecisionInterceptor.setOrderRootDiv(
			  l_opLoginSecService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
	  log.debug("注文経路区分 = " + l_opLoginSecService.getSessionProperty
					  (WEB3SessionAttributeDef.ORDER_ROOT_DIV));

      //累投新規注文確定インタセプタに注文チャネルを設定する
      l_web3RuitoNewOrderDecisionInterceptor.setOrderChannel(getLoginChannel());
           
      //累投市場リクエスト送信サービスに、市場送信処理を実施するという設定を行う
      TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
      MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
      WEB3RuitoMarketRequestSubmitServiceImpl l_web3RuitoMarketRequestSubmitService =
          (WEB3RuitoMarketRequestSubmitServiceImpl)l_marketAdapter.getMarketRequestSenderServce();
        
      l_web3RuitoMarketRequestSubmitService.setMarketSubmit(true);

      //新規注文内容の生成
      WEB3RuitoNewOrderSpec l_web3RuitoNewOrderSpec =
          new WEB3RuitoNewOrderSpec(
              this.getTrader(),
              false,
              "0",
              l_request.ruitoProductCode,
              "0",
              l_dblRuitoOrderQuantity,
              this.getOrderQuantityType(l_request.specifyDiv, l_web3RuitoProduct),
              TaxTypeEnum.UNDEFINED);
              
      //－拡張累投注文マネージャ.submitNewOrder()をコールする。 
      OrderSubmissionResult l_orderSubmissionResult =
         l_web3RuitoOrderManager.submitNewOrder(
             l_subAccount,
             ProductTypeEnum.RUITO,
             l_web3RuitoNewOrderSpec,
             Long.parseLong(l_request.orderId),
             l_request.password,
             true);
      if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
      {
         log.debug("__Result_False__" + l_orderSubmissionResult.getProcessingResult().getErrorInfo());
         log.exiting(STR_METHOD_NAME);
         throw new WEB3BusinessLayerException(
             WEB3ErrorCatalog.BUSINESS_ERROR_00286,
             getClass().getName() + "." + STR_METHOD_NAME,
            "解約注文提出失敗");
      }

      //－取引余力サービスを取得して、余力再計算( )をコールする。
      WEB3TPTradingPowerService l_tpTradingPowerService = 
          (WEB3TPTradingPowerService) Services.getService(
              WEB3TPTradingPowerService.class);

      l_tpTradingPowerService.reCalcTradingPower(
          (WEB3GentradeSubAccount)l_subAccount);      
     
      //累投解約完了レスポンスオブジェクトを生成し、リターンする
      WEB3RuitoSellCompleteResponse l_response =
          (WEB3RuitoSellCompleteResponse) l_request.createResponse();

      OrderUnit[] l_orderUnits = null; //累投注文単位オブジェクト

      l_orderUnits = l_web3RuitoOrderManager.getOrderUnits(
              Integer.parseInt(l_request.orderId));
        
      RuitoOrderUnitRow l_ruitoOrderUnitRow =
          (RuitoOrderUnitRow) l_orderUnits[0].getDataSourceObject();
      
      //　@累投解約完了レスポンスオブジェクトを生成し、リターンする。
      //[累投解約完了レスポンスに設定する値] 
      //更新時間： 注文単位.更新日時       
      l_response.lastUpdatedTimestamp =
          (Date) l_ruitoOrderUnitRow.getLastUpdatedTimestamp();
      
      //識別番号： リクエストデータ.注文ID
      l_response.orderActionId = l_request.orderId;

      log.exiting(STR_METHOD_NAME);

      return l_response;
    }

    /**
     * 注文数量タイプを返す。<BR>
     * <BR>
     * １）　@引数.指定方法@の値が”2：全部”の場合は、<BR>
     *     以下の処理を行う。<BR>
     *  　@－引数.拡張累投銘柄.get指定方法@（解約）()の戻り値が”<BR>
     *     0：選択指定”の場合は<BR>
     * 　@　@QuantityTypeEnum.金額を返す。<BR>
     *  　@－引数.拡張累投銘柄.get指定方法@（解約）()の戻り値が”<BR>
     *     3：金額指定”の場合は<BR>
     * 　@　@QuantityTypeEnum.金額を返す。<BR>
     *  　@－引数.拡張累投銘柄.get指定方法@（解約）()の戻り値が”<BR>
     *    4：口数指定”の場合は<BR>
     * 　@　@QuantityTypeEnum.数量を返す。<BR>
     * <BR>
     * ２）　@引数.指定方法@の値が”2：全部”で<BR>
     *     ない場合は、以下の処理を行う。<BR>
     * 　@ －引数.指定方法@の値が”3：金額指定”<BR>
     *    の場合はQuantityTypeEnum.<BR>
     *    金額を返す。<BR>
     *  　@－引数.指定方法@の値が”4：口数指定”の場合は<BR>
     *    QuantityTypeEnum.数量を返す。<BR>
     * <BR>
     * @@param l_designMethod - 2：全部<BR>
     * 3：金額指定<BR>
     * 4：口数指定<BR>
     * @@param l_expandProduct - 拡張累投銘柄<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum
     * @@roseuid 40A33B360264
     */
    public QuantityTypeEnum getOrderQuantityType(
        String l_designMethod,
        WEB3RuitoProduct l_expandProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getOrderQuantityType(String l_designMethod, "
            + "WEB3RuitoProduct l_expandProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_expandProduct == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        
        log.debug("l_designMethod = " + l_designMethod);
        QuantityTypeEnum l_QuantityTypeEnum = null;

        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_designMethod))
        {
            String l_strMethod = l_expandProduct.getPaymentMethodSell();
            
            log.debug("l_strMethod = " + l_strMethod);

            if (WEB3DesignateMethodDef.SELECT.equals(l_strMethod))
            {
                l_QuantityTypeEnum = QuantityTypeEnum.AMOUNT;
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_strMethod))
            {
                l_QuantityTypeEnum = QuantityTypeEnum.AMOUNT;
            }
            else if (WEB3DesignateMethodDef.NUMBER.equals(l_strMethod))
            {
                l_QuantityTypeEnum = QuantityTypeEnum.QUANTITY;
            }
        }
        else if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_designMethod))
        {
            l_QuantityTypeEnum = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_designMethod))
        {
            l_QuantityTypeEnum = QuantityTypeEnum.QUANTITY;
        }

        log.exiting(STR_METHOD_NAME);
        return l_QuantityTypeEnum;
    }
}
@
