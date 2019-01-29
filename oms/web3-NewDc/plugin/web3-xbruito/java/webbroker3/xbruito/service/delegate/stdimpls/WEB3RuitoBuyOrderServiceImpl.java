head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoBuyOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資買付注文サービス実装クラス(WEB3RuitoBuyOrderServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 周 勇 (中訊) 新規作成
                   2004/12/08 韋念瓊 (中訊) 残対応
Revesion History : 2007/10/25 趙林鵬 (中訊) モデルNO.094
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3MRFFundCodeDef;
import webbroker3.common.define.WEB3ReturnMethodDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoNewOrderDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoNewOrderSpec;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderService;
/**
 * 累積投資買付注文サービス実装クラス<BR>
 */
public class WEB3RuitoBuyOrderServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoBuyOrderService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoBuyOrderServiceImpl.class);
    /**
     * 累積投資買付注文サービス処理を実施する。<BR> 
     * <BR>
     * リクエストデータの型により、validate買付注文()、submit買付注文()<BR>
     * メソッドのいずれかをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4058282F02F0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else if (l_request instanceof WEB3RuitoBuyConfirmRequest)
        {
            return this.validateBuyOrder(
                (WEB3RuitoBuyConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3RuitoBuyCompleteRequest)
        {
            return this.submitBuyOrder((WEB3RuitoBuyCompleteRequest) l_request);
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[入力値が不正です]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }        
    }
    /**
     * 累積投資買付注文審査を行う。<BR>
     * <BR>
     * シーケンス図「累投買付注文／(累投)買付注文審査」参照
     * <BR>
     * is判定フラグがfalseなら例外をスローする
     * 「取引余力エラー」
     *      classpath:WEB3BusinessLayerException <BR>
     *      tag:      BUSINESS_ERROR_01306 <BR>
     * <BR>
     * @@param l_request - 累積投資買付注文確認リクエストオブジェクト
     * @@return webbroker3.xbruito.message.WEB3RuitoBuyConfirmResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4058287301D7
     */
    protected WEB3RuitoBuyConfirmResponse validateBuyOrder(WEB3RuitoBuyConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateBuyOrder(WEB3RuitoBuyConfirmRequest l_request)";
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

        //1.1　@買付数量チェック
        l_request.validate();
        //1.2　@補助口座取得
        SubAccount l_subAccount = this.getSubAccount();
        log.debug("補助口座 = " + l_subAccount);
        
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
                getClass().getName() + "." + STR_METHOD_NAME);
        }        
        //1.5　@累投銘柄取得    
        WEB3RuitoProduct l_web3RuitoProduct = null; //拡張累投銘柄        
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        
        //拡張累投銘柄マネージャ
        try
        {
            l_web3RuitoProduct =
                (WEB3RuitoProduct) l_web3RuitoProductManager.getRuitoProduct(
                    l_subAccount.getInstitution(),
                    l_request.ruitoProductCode);
            log.debug("l_expansionRuitoProduct = " + l_web3RuitoProduct);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.6　@受付時間チェック、システム取引停止チェック
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        WEB3RuitoOrderManager l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();
        
        log.debug(
            "Double.parseDouble(l_request.ruitoOrderQuantity) = "
                + Double.parseDouble(l_request.ruitoOrderQuantity));
        //1.7 発注審査を行う。 
        NewOrderValidationResult l_newOrderValidationResult =
            l_web3RuitoOrderManager.validateNewOrder(
                l_subAccount,
                l_web3RuitoProduct,
                Double.parseDouble(l_request.ruitoOrderQuantity),
                true,
                null,
                l_request.specifyDiv);
        log.debug("l_newOrderValidationResult = " + l_newOrderValidationResult);

        //電子鳩システム接続サービス
        WEB3GentradeBatoClientService l_service =
            (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);

        //リクエスト.電子鳩チェックフラグ == True の場合、目論見書閲覧チェックを実施する
        //validate目論見書閲覧(種別コード : String, 銘柄コード : String)
        //種別コード：リクエスト.種別コード
        //銘柄コード：累投買付注文確認リクエスト.銘柄コード
        WEB3GentradeProspectusResult l_gentradeProspectusResult = null;
        if (l_request.batoCheckFlag)
        {
            l_gentradeProspectusResult =
                l_service.validateProspectus(l_request.typeCode, l_request.ruitoProductCode);
        }

        //　@取引余力チェック処理
        //1.8 識別コードを採番する 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        
        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.RUITO);
        
        log.debug("新規識別コード = " + l_strNewNumber);
        
        //1.9 －累投新規注文確定インタセプタオブジェクトを生成し、
        WEB3RuitoNewOrderDecisionInterceptor l_ruitoNewOrderInterceptor =
            new WEB3RuitoNewOrderDecisionInterceptor();
        
        //1.10 累投新規注文確定インタセプタに対するプロパティセット
        //識別コード： 注文識別コード採番サービス.get新規識別コード()の戻り値 
        l_ruitoNewOrderInterceptor.setRequestNumber(l_strNewNumber);
        //MRF識別コード： null 
        l_ruitoNewOrderInterceptor.setMRFOrderRequestNumber(null);
        //返還方法@： null 
        l_ruitoNewOrderInterceptor.setReturnMethod(null);
        //受渡方法@： null 
        l_ruitoNewOrderInterceptor.setPaymentMethod(null);
        //累投タイプ： 拡張累投銘柄.getRitoType()の戻り値
        l_ruitoNewOrderInterceptor.setRuitoTypeEnum(l_web3RuitoProduct.getRuitoType());
        log.debug("拡張累投銘柄.getRitoType() = " + l_web3RuitoProduct.getRuitoType());        

        //累投解約区分： null 
        l_ruitoNewOrderInterceptor.setRuitoSellDiv(null);
        
        //注文経路区分：セッション.注文経路区分
		OpLoginSecurityService l_opLoginSecService =
				(OpLoginSecurityService)Services.getService(OpLoginSecurityService.class); 
		l_ruitoNewOrderInterceptor.setOrderRootDiv(
				l_opLoginSecService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
		log.debug("注文経路区分 = " + l_opLoginSecService.getSessionProperty
						(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //注文チャネル： this.getログインチャネル()の戻り値 
        l_ruitoNewOrderInterceptor.setOrderChannel(
            this.getLoginChannel());
                
        //　@(*) 引数.指定方法@が3（金額）の場合はQuantityTypeEnum.金額を指定 
        //  (*) 引数.指定方法@が4（口数）の場合はQuantityTypeEnum.数量を指定 
        QuantityTypeEnum l_quantityType = null;
        if (WEB3DesignateMethodDef.AMOUNT.equals(l_request.specifyDiv))
        {
            l_quantityType = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3DesignateMethodDef.NUMBER.equals(l_request.specifyDiv))
        {
            l_quantityType = QuantityTypeEnum.QUANTITY;
        }
        
        //－新規注文内容の生成 
        WEB3RuitoNewOrderSpec l_ruitoNewOrderSpec =
            new WEB3RuitoNewOrderSpec(
                this.getTrader(),
                true,
                l_request.ruitoProductCode,
                Double.parseDouble(l_request.ruitoOrderQuantity),                
                l_quantityType,
                TaxTypeEnum.UNDEFINED);
        
        //－取引余力サービスを取得してvalidate取引余力( )をコールし
        //  取引余力結果オブジェクトを取得する。
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        
        Object l_orderSpecIntercepters[] = new Object[1];
        l_orderSpecIntercepters[0] = l_ruitoNewOrderInterceptor;
        
        Object[] l_orderSpecs = new Object[1];
        l_orderSpecs[0] = l_ruitoNewOrderSpec;
        
        WEB3TPTradingPowerResult l_tpTradingPowerResult = 
            l_tpTradingPowerService.validateTradingPower(
                l_gentradeSubAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                OrderTypeEnum.RUITO_BUY,
                false);
        
        //－取得した取引余力結果オブジェクト.is判定フラグ( )＝falseの場合、 
        //[取引余力チェックエラー]として例外をスローする。
        if (!l_tpTradingPowerResult.isResultFlg())
        {
            log.debug("取引余力チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "取引余力チェックエラー");
        }
        
        //1.15　@発注日の取得 
        Date l_date = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.16　@注文IDを採番する。 
        //拡張累投注文マネージャ.createNewOrderId()をコールして、注文IDを採番する。　@
        long l_lngNewOrderId = l_web3RuitoOrderManager.createNewOrderId();
        
        //1.17　@累投買付注文確認レスポンスオブジェクトを生成し、リターンする        
        WEB3RuitoBuyConfirmResponse l_ruitoBuyConfirmResponse =
            (WEB3RuitoBuyConfirmResponse)l_request.createResponse();
        
        //確認時発注日： 取得した発注日を設定する。 
        l_ruitoBuyConfirmResponse.checkDate = l_date;
        
        //注文ID：採番した注文ID
        l_ruitoBuyConfirmResponse.orderId = l_lngNewOrderId + "";

        //目論見書閲覧チェック結果
        l_ruitoBuyConfirmResponse.prospectusResult = l_gentradeProspectusResult;

        log.exiting(STR_METHOD_NAME);
        return l_ruitoBuyConfirmResponse;
    }
    /**
     * 累積投資買付注文登録を行う。<BR>
     * <BR>
     * シーケンス図「累投買付注文／(累投)買付注文登録」参照
     * <BR>
     * @@param l_request - 累積投資買付注文完了リクエストデータ
     * @@return webbroker3.xbruito.message.WEB3RuitoBuyCompleteResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405828AF030F
     */
    protected WEB3RuitoBuyCompleteResponse submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)";
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

        //1.1　@リクエスト内容チェック
        l_request.validate();
        //1.2　@発注日取得
        Date l_orderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                l_request.checkDate);
        log.debug("発注日 = " + l_orderBizDate);
        
        //1.3　@補助口座取得
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        log.debug("補助口座 = " + l_subAccount);
        
        //1.4　@顧客別取引停止属性チェック
        //－FinApp.getCommonOrderValidator()をコールし、注文チェックオブジェクトを取得する。
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        //1.5 －注文チェック.validate取引可能顧客()をコールする。
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
                "取引停止顧客チェックエラーの場合");
        }        
        //1.6 －this.get代理入力者( )をコールし、代理入力者オブジェクトを取得する。
        Trader l_trader = this.getTrader();        
        
        //1.7 －validate取引パスワード( )をコールする。
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
        //　@累投銘柄取得
        WEB3RuitoProduct l_web3RuitoProduct = null; //拡張累投銘柄  
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        //拡張累投銘柄マネージャ
        try
        {
            l_web3RuitoProduct =
                (WEB3RuitoProduct) l_web3RuitoProductManager.getRuitoProduct(
                    l_subAccount.getInstitution(),
                    l_request.ruitoProductCode);
            
            log.debug("拡張累投銘柄.getProductId() = " + 
                    l_web3RuitoProduct.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.8　@受付時間チェック、システム取引停止チェック    
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.9　@発注審査を行う。
        WEB3RuitoOrderManager l_ruitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();

        NewOrderValidationResult l_newOrderValidationResult =
            l_ruitoOrderManager.validateNewOrder(
                l_subAccount,
                l_web3RuitoProduct,
                Double.parseDouble(l_request.ruitoOrderQuantity),
                true,
                null,
                l_request.specifyDiv);
        
        log.debug("l_newOrderValidationResult = " + l_newOrderValidationResult);
        //　@受渡日取得
        Date l_datDeliveryDate =
            l_web3RuitoProductManager.getDeliveryDate(
                l_subAccount.getInstitution(),
                l_web3RuitoProduct.getProductCode(),
                true);
        log.debug("受渡日取得 = " + l_datDeliveryDate);      
        
        RuitoTypeEnum l_ruitoTypeEnum = l_web3RuitoProduct.getRuitoType();
        log.debug("l_ruitoTypeEnum = " + l_ruitoTypeEnum);
                
        //1.10　@MRF自動解約チェック 
        double l_dblTransferAmount = 0.0;   //get当日預り金への振替額()の戻り値
        
        //取引余力サービスを取得
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        // 取得した拡張累投銘柄の累投タイプがRuitoTypeEnum.中期国債ファ@ンドの場合
        //  は、MRFの自動解約を行わない。 
        // 取得した受渡日と取得した発注日の日付（年月日）が等しくない場合、
        // MRF自動解約をおこなわない。
        if (!RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum) && 
                WEB3DateUtility.compareToDay(l_datDeliveryDate, l_orderBizDate) == 0)
        {
            //－受渡日と発注日の日付が等しい場合、取引余力サービスを取得して、 
            //get当日預り金への振替額()をコールし、MRFの自動解約要否のチェックを行う。 
            l_dblTransferAmount = 
                l_tpTradingPowerService.getTransferAmountToEquitySubAcount(
                    (WEB3GentradeSubAccount)l_subAccount,
                    Double.parseDouble(l_request.ruitoOrderQuantity),
                    l_datDeliveryDate);
        }
        //1.11　@買付注文処理
        //MRFの自動解約を行う必要がある場合、this.買付注文処理（MRF自動解約有り）()をコールし、
        //注文履歴IDを取得する
        log.debug("get当日預り金への振替額() = " + l_dblTransferAmount);
        if (l_dblTransferAmount > 0)
        {
            log.debug("MRFの自動解約を行う必要がある場合");
            this.buyOrderProcessMRFAutoSell(
                l_subAccount,
                l_web3RuitoProduct,
                Double.parseDouble(l_request.ruitoOrderQuantity),
                l_request.specifyDiv,
                l_dblTransferAmount,
                l_trader,
                l_request.password,
                l_request.orderId);
        }  
        //MRFの自動解約を行う必要がない場合、this.買付注文処理（MRF自動解約なし）()をコールし、
        //注文履歴IDを取得する
        else
        {
            log.debug("MRFの自動解約を行う必要がない場合");
            this.buyOrderProcessNotMRFAutoSell(
                l_subAccount,
                l_web3RuitoProduct,
                Double.parseDouble(l_request.ruitoOrderQuantity),
                l_request.specifyDiv,
                l_trader,
                l_request.password, 
                l_request.orderId);
        }
        
        //1.12　@処理日時の取得
        Date l_datProcessDate = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        
        //1.13　@累投買付注文完了レスポンスオブジェクトを生成し、リターンする。 
        WEB3RuitoBuyCompleteResponse l_response =
            (WEB3RuitoBuyCompleteResponse)l_request.createResponse();

        //更新時間： 取得した処理日時 

        l_response.lastUpdatedTimestamp = l_datProcessDate;
        log.debug("更新時間：" + WEB3DateUtility.formatDate(l_datProcessDate, "yyyyMMdd"));
        
        //識別番号： 累投買付注文完了リクエスト.注文ID
        l_response.orderActionId = l_request.orderId;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (買付注文処理（MRF自動解約有り）) <BR>
     * MRF自動解約を伴う累積投資買付注文登録を行う。<BR>
     * シーケンス図「累投買付注文／(累投)買付注文(MRF自動解約有り)」参照 <BR>
     * <BR>
     * 　@－拡張累投注文マネージャ.submitNewOrder()の戻り値判定<BR>
     * 　@　@拡張累投注文マネージャ.submitNewOrder()の<BR>
     *      戻り値.getProcessingResult().isSuccessfulResult()==false<BR>
     * 　@　@の場合、例外をスローする。<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00191<BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR> 
     * @@param l_expansionRuitoProduct - 拡張累投銘柄<BR> 
     * @@param l_dblBuyPriceQuantity - 買付金額数量<BR>
     * @@param l_strDesignateMethod - 指定方法@<BR>
     * @@param l_dblMRFSellPrice - MRF解約金額<BR>
     * @@param l_proxyInputPerson - 代理入力者<BR>
     * @@param l_strDealingPassword - 取引パスワード<BR>
     * @@param l_orderId - 注文ID<BR>
     * @@return long
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40762733030C
     */
    public void buyOrderProcessMRFAutoSell(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_web3RuitoProduct,
        double l_dblBuyPriceQuantity,
        String l_strDesignateMethod,
        double l_dblMRFSellPrice,
        Trader l_proxyInputPerson,
        String l_strDealingPassword, 
        String l_orderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "buyOrderProcessMRFAutoSell(SubAccount l_subAccount, "
                + "WEB3RuitoProduct l_expansionRuitoProduct, "
                + "double l_dblBuyPriceQuantity, "
                + "String l_strDesignateMethod, double l_dblMRFSellPrice, "
                + "Trader l_proxyInputPerson, String l_strDealingPassword,)"
                + "String l_orderId)";
        log.entering(STR_METHOD_NAME);

        //累投注文識別コード採番サービス
        WEB3HostReqOrderNumberManageService l_WEB3HostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        
        //1.1 MRF解約注文の識別コードを取得する。 
        String l_strMrfSellOrderRequestCode = 
            l_WEB3HostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.RUITO);        

        log.debug("MRF解約注文の識別コード = " + l_strMrfSellOrderRequestCode);
        
        //1.2 中期国債ファ@ンド・MMF買付注文の識別コードを取得する。 
        String l_strFundMmfRequestNumber = 
            l_WEB3HostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.RUITO);        

        log.debug("中期国債ファ@ンド・MMF買付注文の識別コード = " + l_strFundMmfRequestNumber);
        
        //　@中期国債ファ@ンド・MMF買付注文                
        //－累投新規注文確定インタセプタを累投注文マネージャに設定する。 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoOrderManager l_ruitoOrderManager = null;
        //累投注文マネージャ             
        l_ruitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();        

        //1.3 累投新規注文確定インタセプタ       
        WEB3RuitoNewOrderDecisionInterceptor l_ruitoNewOrderDecisionInterceptor =
            new WEB3RuitoNewOrderDecisionInterceptor();
        
        //1.4 インタセプタを設定する。 
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoNewOrderDecisionInterceptor);
        
        //1.5 －累投新規注文確定インタセプタに識別コードを設定する。 
        //識別コード： 中期国債ファ@ンド・MMF買付注文の識別コード
        l_ruitoNewOrderDecisionInterceptor.setRequestNumber(
                l_strFundMmfRequestNumber);
        log.debug(
            "累投新規注文確定インタセプタ.識別コード = "
                + l_ruitoNewOrderDecisionInterceptor.getRequestNumber());
        
        //1.6 －累投新規注文確定インタセプタにMRF識別コードを設定する。
        //MRF識別コード： MRF解約注文の識別コード
        l_ruitoNewOrderDecisionInterceptor.setMRFOrderRequestNumber(
                l_strMrfSellOrderRequestCode);
        log.debug(
            "累投新規注文確定インタセプタ.MRF識別コード = "
                + l_ruitoNewOrderDecisionInterceptor.getMRFOrderRequestNumber());
        
        //1.7 －累投新規注文確定インタセプタに返還方法@を設定する。
        l_ruitoNewOrderDecisionInterceptor.setReturnMethod(null);
        //1.8 －累投新規注文確定インタセプタに受渡方法@を設定する。
        l_ruitoNewOrderDecisionInterceptor.setPaymentMethod(null);
        
        //1.9 －累投新規注文確定インタセプタに累投タイプを設定する。
        l_ruitoNewOrderDecisionInterceptor.setRuitoTypeEnum(
                l_web3RuitoProduct.getRuitoType());
        log.debug(
            "累投新規注文確定インタセプタ.累投タイプ = "
                + l_ruitoNewOrderDecisionInterceptor.getRuitoTypeEnum());
        
        // －累投新規注文確定インタセプタに累投解約区分を設定する。
        l_ruitoNewOrderDecisionInterceptor.setRuitoSellDiv(null);
        
        // －累投新規注文確定インタセプタに注文経路区分を設定する。
		OpLoginSecurityService l_opLoginSecService =
				(OpLoginSecurityService)Services.getService(OpLoginSecurityService.class); 
		l_ruitoNewOrderDecisionInterceptor.setOrderRootDiv(
				l_opLoginSecService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
		log.debug("注文経路区分 = " + l_opLoginSecService.getSessionProperty
						(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
						
        //1.12 －累投新規注文確定インタセプタに注文チャネルを設定する。 
        log.debug("this.getLoginChannel() = " + this.getLoginChannel());
        l_ruitoNewOrderDecisionInterceptor.setOrderChannel(
            this.getLoginChannel());
        
        //1.13 －累投市場リクエスト送信サービスに、市場送信処理を実施しないという設定を行う。 
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        WEB3RuitoMarketRequestSubmitServiceImpl l_ruitoMarketRequestSubmitService =
            (WEB3RuitoMarketRequestSubmitServiceImpl) l_marketAdapter
                .getMarketRequestSenderServce();
        
        l_ruitoMarketRequestSubmitService.setMarketSubmit(false);
        log.debug(
            "累投市場リクエスト送信サービス.市場送信可否 = "
                + l_ruitoMarketRequestSubmitService.isMarketSubmit());
        
        //1.14 －新規注文内容の生成         
        MainAccountRow l_mainaccountRow = null;
        l_mainaccountRow =
            (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
        
        QuantityTypeEnum l_quantityTypeEnum = null;
        if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignateMethod))
        {
            log.debug("引数.指定方法@が3（金額）の場合");
            l_quantityTypeEnum = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignateMethod))
        {            
            log.debug("引数.指定方法@が4（口数）の場合" );
            l_quantityTypeEnum = QuantityTypeEnum.QUANTITY;
        }
        WEB3RuitoNewOrderSpec l_web3RuitoNewOrderSpec =
            new WEB3RuitoNewOrderSpec(
                l_proxyInputPerson,
                true,
                l_web3RuitoProduct.getProductCode(),
                l_dblBuyPriceQuantity,
                l_quantityTypeEnum,
                TaxTypeEnum.UNDEFINED);        

        //　@取引余力チェック処理
        //－取引余力サービスを取得してvalidate取引余力( )をコールし
        //  取引余力結果オブジェクトを取得する。
        WEB3RuitoNewOrderDecisionInterceptor l_web3RuitoNewOrderDecisionInterceptor =
            new WEB3RuitoNewOrderDecisionInterceptor();      
        
        Object[] l_orderSpecIntercepters = new Object[1]; 
        l_orderSpecIntercepters[0] = l_web3RuitoNewOrderDecisionInterceptor;
        Object[] l_orderSpecs = new Object[1];
        l_orderSpecs[0] = l_web3RuitoNewOrderSpec;
        
        // 取引余力サービスを取得
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        //1.15 取引余力のチェックを実施する。 
        WEB3TPTradingPowerResult l_tpTradingPowerResult = 
            l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                OrderTypeEnum.RUITO_BUY,
                true);
        
        //－取得した取引余力結果オブジェクト.is判定フラグ( )＝falseの場合、 
        //[取引余力チェックエラー]として例外をスローする。
        if (!l_tpTradingPowerResult.isResultFlg())
        {
            log.debug("取引余力チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "[取引余力チェックエラー]");
        }
        
        //1.18 新規買付注文
        OrderSubmissionResult l_orderSubmissionResult = 
            l_ruitoOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.RUITO,
                l_web3RuitoNewOrderSpec,
                Long.parseLong(l_orderId),
                l_strDealingPassword,
                true);
        //－拡張累投注文マネージャ.submitNewOrder()の戻り値判定 
        boolean l_blnResult = 
            l_orderSubmissionResult.getProcessingResult().isSuccessfulResult();
        log.debug("拡張累投注文マネージャ.submitNewOrder()の戻り値 = " + l_blnResult);
        
        if (!l_blnResult)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //　@MRF解約注文 
        
        //1.20 累投注文マネージャ.setThreadLocalPersistenceEventIntercepto()をコールし,
        //累投新規累投新規注文確定インタセプタを設定する
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoNewOrderDecisionInterceptor);
        
        //1.21 －累投新規注文確定インタセプタに識別コードを設定する。
        //識別コード： MRF解約注文の識別コード
        l_ruitoNewOrderDecisionInterceptor.setRequestNumber(
            l_strMrfSellOrderRequestCode);
        
        log.debug("識別コード： = "
                + l_ruitoNewOrderDecisionInterceptor.getRequestNumber());
        
        //1.22 －累投新規注文確定インタセプタにMRF識別コードを設定する。
        //MRF識別コード： 中期国債ファ@ンド・MMF買付注文の識別コード
        l_ruitoNewOrderDecisionInterceptor.setMRFOrderRequestNumber(
                l_strFundMmfRequestNumber);
        
        log.debug("MRF識別コード： "
                + l_ruitoNewOrderDecisionInterceptor.getMRFOrderRequestNumber());
        
        //－MRF銘柄の取得
        String l_strMrfFundCode = null; //MRFコード
        WEB3RuitoProduct l_mrfProduct = null; //MRF銘柄
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getProductManager();
        
        //拡張累投銘柄マネージャ       
        SubAccountRow l_subAccountRow = null;
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();

        log.debug("l_mainAccountRow = " + l_mainAccountRow);
        
        l_strMrfFundCode = l_mainAccountRow.getMrfFundCode();
        log.debug("MRFコード： = " + l_strMrfFundCode);
        
        //1.24 MRF銘柄を取得する。 
        l_mrfProduct =
            l_web3RuitoProductManager.getMRFProduct(
                l_subAccount.getInstitution(),
                l_strMrfFundCode);
        log.debug("l_mrfProduct = " + l_mrfProduct);
        
        //1.25 累投新規注文確定インタセプタに返還方法@を設定する
        log.debug("l_mrfProduct.getMRFCode() = " + l_mrfProduct.getMRFCode());
        if (WEB3MRFFundCodeDef.NOMURA.equals(l_mrfProduct.getMRFCode()))
        {
            log.debug(
                "if (l_mrfProduct.getMRFCode().equals(WEB3MRFFundCodeDef.NOMURA))");
            l_ruitoNewOrderDecisionInterceptor.setReturnMethod(
                WEB3ReturnMethodDef.CASHING);
        }
        else
        {
            log.debug(
                "l_ruitoNewOrderDecisionInterceptor.setReturnMethod(WEB3ReturnMethodDef.DAY_SELL)");
            l_ruitoNewOrderDecisionInterceptor.setReturnMethod(
                WEB3ReturnMethodDef.DAY_SELL);
        }
        //1.26 累投新規注文確定インタセプタに受渡方法@を設定する       
        l_ruitoNewOrderDecisionInterceptor.setPaymentMethod(null);
        //1.27 累投新規注文確定インタセプタに累投タイプを設定する
        l_ruitoNewOrderDecisionInterceptor.setRuitoTypeEnum(RuitoTypeEnum.MRF);
        //累投新規注文確定インタセプタに累投解約区分を設定する
        l_ruitoNewOrderDecisionInterceptor.setRuitoSellDiv(null);
        
        //1.28 累投新規注文確定インタセプタに注文経路区分を設定する
		l_ruitoNewOrderDecisionInterceptor.setOrderRootDiv(
				l_opLoginSecService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
		log.debug("注文経路区分 = " + l_opLoginSecService.getSessionProperty
						(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //1.29 累投新規注文確定インタセプタに注文チャネルを設定する getログインチャネル
        l_ruitoNewOrderDecisionInterceptor.setOrderChannel(
            this.getLoginChannel());
        
        //1.30 累投市場リクエスト送信サービスに、市場送信処理を実施するという設定を行う
        log.debug("MRF自動解約を伴う累積投資買付注文登録を行う");
        l_ruitoMarketRequestSubmitService.setMarketSubmit(true);
        log.debug(
            "l_ruitoMarketRequestSubmitService.isMarketSubmit() = "
                + l_ruitoMarketRequestSubmitService.isMarketSubmit());
        
        //1.31 新規注文内容の生成
        l_web3RuitoNewOrderSpec =
                new WEB3RuitoNewOrderSpec(
                    l_proxyInputPerson,
                    false,
                    l_mrfProduct.getProductCode(),
                    l_dblMRFSellPrice,
                    QuantityTypeEnum.AMOUNT,
                    TaxTypeEnum.UNDEFINED //取得税区分
                    );
        log.debug(" l_web3RuitoNewOrderSpec =  " + l_web3RuitoNewOrderSpec);
        //1.32 MRF解約注文
        OrderSubmissionResult l_submissionResult = 
            l_ruitoOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.RUITO,
                l_web3RuitoNewOrderSpec,
                l_ruitoOrderManager.createNewOrderId(),
                l_strDealingPassword,
                true);
        
        //拡張累投注文マネージャ.submitNewOrder()の戻り値判定     
        boolean l_blnSuccessfulResult;
        l_blnSuccessfulResult =
            l_submissionResult.getProcessingResult().isSuccessfulResult();
        log.debug("l_blnSuccessfulResult =  " + l_blnSuccessfulResult);
        
        if (!l_blnSuccessfulResult)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);        
    }
    /**
     * MRF自動解約を伴わない累積投資買付注文登録を行う。<BR>
     * <BR>
     * シーケンス図「累投買付注文／(累投)買付注文(MRF自動解約なし)」参照 <BR>
     * <BR>
     * 　@－拡張累投注文マネージャ.submitNewOrder()の戻り値判定<BR>
     * 　@　@拡張累投注文マネージャ.submitNewOrder()の<BR>
     *      戻り値.getProcessingResult().isSuccessfulResult()==false<BR>
     * 　@　@の場合、例外をスローする。<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00191<BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_expansionRuitoProduct - 拡張累投銘柄<BR>
     * @@param l_dblBuyPriceQuantity - 買付金額数量<BR>
     * @@param l_strDesignateMethod - 指定方法@<BR>
     * @@param l_proxyInputPerson - 代理入力者<BR>
     * @@param l_strDealingPassword - 取引パスワード<BR>
     * @@param l_orderId - 注文ID<BR>
     * @@return long
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40764654003E
     */
    public void buyOrderProcessNotMRFAutoSell(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_web3RuitoProduct,
        double l_dblBuyPriceQuantity,
        String l_strDesignateMethod,
        Trader l_proxyInputPerson,
        String l_strDealingPassword, 
        String l_orderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "buyOrderProcessNotMRFAutoSell(SubAccount l_subAccount, " + 
            "WEB3RuitoProduct l_web3RuitoProduct, double l_dblBuyPriceQuantity, " + 
            "String l_strDesignateMethod, Trader l_proxyInputPerson, " +
            "String l_strDealingPassword)";
        
        log.entering(STR_METHOD_NAME);
        
        log.debug("l_subAccount = " + l_subAccount.getSubAccountId());
        log.debug("l_web3RuitoProduct = " + l_web3RuitoProduct.getProductCode());
        log.debug("l_proxyInputPerson = " + l_proxyInputPerson);
        log.debug("l_strDealingPassword = " + l_strDealingPassword);
        
        //1.1　@識別コードの取得
        //累投注文識別コード採番サービス
        WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        
        String l_strNewRequestNumber = l_reqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.RUITO);      
      
        log.debug(" 注文識別コード採番サービス.get新規識別コード = " + l_strNewRequestNumber);
        
        //　@買付注文処理 
        //累投新規注文確定インタセプタを累投注文マネージャに設定する 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoOrderManager l_ruitoOrderManager = null;
        //累投注文マネージャ
        l_ruitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();
        
        //1.2累投新規注文確定インタセプタ
        WEB3RuitoNewOrderDecisionInterceptor l_ruitoDefaultRuitoOrderDecision =
            new WEB3RuitoNewOrderDecisionInterceptor();
        
        //1.3 インタセプタを設定する。 
        l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoDefaultRuitoOrderDecision);
        log.debug(
            " l_ruitoOrderManager.getThreadLocalPersistenceEventInterceptor() = "
                + l_ruitoOrderManager
                    .getThreadLocalPersistenceEventInterceptor());
        //1.4 累投新規注文確定インタセプタに識別コードを設定する
        l_ruitoDefaultRuitoOrderDecision.setRequestNumber(
            l_strNewRequestNumber);
        //1.5 累投新規注文確定インタセプタにMRF識別コードを設定する
        l_ruitoDefaultRuitoOrderDecision.setMRFOrderRequestNumber(null);
        //1.6 累投新規注文確定インタセプタに返還方法@を設定する
        l_ruitoDefaultRuitoOrderDecision.setReturnMethod(null);
        //1.7 累投新規注文確定インタセプタに受渡方法@を設定する
        l_ruitoDefaultRuitoOrderDecision.setPaymentMethod(null);
        //1.8 累投新規注文確定インタセプタに累投タイプを設定する
        log.debug(
            "l_expansionRuitoProduct.getRuitoType() = "
                + l_web3RuitoProduct.getRuitoType());
        l_ruitoDefaultRuitoOrderDecision.setRuitoTypeEnum(
                l_web3RuitoProduct.getRuitoType());
        log.debug(
            "l_ruitoDefaultRuitoOrderDecision.getRuitoTypeEnum() = "
                + l_ruitoDefaultRuitoOrderDecision.getRuitoTypeEnum());
        //1.9 累投新規注文確定インタセプタに累投解約区分を設定する
        l_ruitoDefaultRuitoOrderDecision.setRuitoSellDiv(null);
        log.debug("this.getLoginChannel() = " + this.getLoginChannel());
        
        //1.10 累投新規注文確定インタセプタに注文経路区分を設定する
		OpLoginSecurityService l_opLoginSecService =
				(OpLoginSecurityService)Services.getService(OpLoginSecurityService.class); 
		l_ruitoDefaultRuitoOrderDecision.setOrderRootDiv(
				l_opLoginSecService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
		log.debug("注文経路区分 = " + l_opLoginSecService.getSessionProperty
						(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
						
        //1.11 累投新規注文確定インタセプタに注文チャネルを設定する  getログインチャネル()
        l_ruitoDefaultRuitoOrderDecision.setOrderChannel(
            this.getLoginChannel());
        
        //1.12 累投市場リクエスト送信サービスに、市場送信処理を実施するという設定を行う
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        WEB3RuitoMarketRequestSubmitServiceImpl l_ruitoMarketRequestSubmitService =
            (WEB3RuitoMarketRequestSubmitServiceImpl) 
                l_marketAdapter.getMarketRequestSenderServce();
        
        log.debug("MRF自動解約を伴わない累積投資買付注文登録を行う");
        l_ruitoMarketRequestSubmitService.setMarketSubmit(true);
        
        //1.14 新規注文内容の生成         
        WEB3RuitoNewOrderSpec l_web3RuitoNewOrderSpec = null; //拡張累投新規注文内容
        MainAccountRow l_mainaccountRow =(MainAccountRow) 
            l_subAccount.getMainAccount().getDataSourceObject();
        
        log.debug("l_mainaccountRow = " + l_mainaccountRow);
        log.debug("l_strDesignateMethod = " + l_strDesignateMethod);
        log.debug(
            "l_mainaccountRow.getTaxType() = " + l_mainaccountRow.getTaxType());
        if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignateMethod))
        {
            l_web3RuitoNewOrderSpec =
                new WEB3RuitoNewOrderSpec(
                    l_proxyInputPerson,
                    true,
                    l_web3RuitoProduct.getProductCode(),
                    l_dblBuyPriceQuantity,
                    QuantityTypeEnum.AMOUNT,
                    TaxTypeEnum.UNDEFINED);
                
            log.debug("l_ruitoNewOrder = " + l_web3RuitoNewOrderSpec);
        }
        else if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignateMethod))
        {
            l_web3RuitoNewOrderSpec =
                new WEB3RuitoNewOrderSpec(
                    l_proxyInputPerson,
                    true,
                    l_web3RuitoProduct.getProductCode(),
                    l_dblBuyPriceQuantity,
                    QuantityTypeEnum.QUANTITY,
                    TaxTypeEnum.UNDEFINED);
            log.debug("l_ruitoNewOrder =" + l_web3RuitoNewOrderSpec);
        }
        
        //===============================================================
        //1.15 －取引余力チェック処理 
        //－取引余力サービスを取得してvalidate取引余力( )をコールし
        //  取引余力結果オブジェクトを取得する。
        WEB3RuitoNewOrderDecisionInterceptor l_web3RuitoNewOrderDecisionInterceptor =
            new WEB3RuitoNewOrderDecisionInterceptor();      
        
        Object[] l_orderSpecIntercepters = new Object[1]; 
        l_orderSpecIntercepters[0] = l_web3RuitoNewOrderDecisionInterceptor;
        Object[] l_orderSpecs = new Object[1];
        l_orderSpecs[0] = l_web3RuitoNewOrderSpec;
        
        //取引余力サービスを取得
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_tpTradingPowerResult = 
            l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                OrderTypeEnum.RUITO_BUY,
                true);
        
        //1.16 －取得した取引余力結果オブジェクト.is判定フラグ( )＝falseの場合、 
        //[取引余力チェックエラー]として例外をスローする。
        if (!l_tpTradingPowerResult.isResultFlg())
        {
            log.debug("取引余力チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "[取引余力チェックエラー]");
        }     
        //=========================================================
        
        //1.17 買付注文     
        OrderSubmissionResult l_orderSubmissionResult = null;
        l_orderSubmissionResult =
            l_ruitoOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.RUITO,
                l_web3RuitoNewOrderSpec,
                Long.parseLong(l_orderId),
                l_strDealingPassword,
                true);
        
        //1.18 拡張累投注文マネージャ.submitNewOrder()の戻り値判定
        boolean l_blnResult =
            l_orderSubmissionResult.getProcessingResult().isSuccessfulResult();
        log.debug("拡張累投注文マネージャ.submitNewOrder()の戻り値 = " + l_blnResult);
        
        if (!l_blnResult)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        log.exiting(STR_METHOD_NAME);        
    }
}@
