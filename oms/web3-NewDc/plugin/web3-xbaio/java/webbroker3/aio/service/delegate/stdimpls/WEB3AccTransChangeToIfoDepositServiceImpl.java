head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeToIfoDepositServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金への振替サービスImpl(WEB3AccTransChangeToIfoDepositServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/26 周勇(中訊) レビュー
                   2004/12/09 周勇 (中訊) 残対応
Revesion History : 2007/08/23 武波 (中訊) 仕様変更・モデル752
Revesion History : 2009/03/16 車進 (中訊) 仕様変更・モデル1142
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioTransferOrderUpdateInterceptor;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteRequest;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteResponse;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmRequest;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeToIfoDepositService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3DateUtility;

/**
 * (証拠金への振替サービスImpl)<BR>
 * 証拠金への振替サービス実装クラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeToIfoDepositServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AccTransChangeToIfoDepositService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeToIfoDepositServiceImpl.class);

    /**
     * 証拠金への振替サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型によりvalidate注文()、<BR>
     * またはsubmit注文()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4135AEFC01AC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AccTransChangeToIfoDepositConfirmRequest)
        {
            // validate注文()
            l_response =
                this.validateOrder(
                    (WEB3AccTransChangeToIfoDepositConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AccTransChangeToIfoDepositCompleteRequest)
        {
            // submit注文()
            l_response =
                this.submitOrder(
                    (WEB3AccTransChangeToIfoDepositCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                STR_METHOD_NAME
                    + "リクエストデータが"
                    + " WEB3AccTransChangeToIfoDepositConfirmRequest "
                    + " と WEB3AccTransChangeToIfoDepositCompleteRequest以外である, but is " + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate注文)<BR>
     * 振替注文の発注審査を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（証拠金への振替）validate注文」参照。<BR>
     * ========================================================<BR>
     * シーケンス図(「(証拠金振替サービスモデル) / 証拠金への振替 」<BR>
     * （証拠金への振替）validate注文 )<BR>
     * 　@　@　@:  1.11.calc顧客勘定残高(OrderTypeEnum)<BR>   
     *     リクエストデータ.振替金額 > calc顧客勘定残高()の戻り値の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00761<BR>
     * <BR>
     * ==========================================================<BR>
     * 1.20 戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01306 <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)
     * 
     * @@return webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4135AEFC01AE
     */
    protected WEB3AccTransChangeToIfoDepositConfirmResponse validateOrder(
        WEB3AccTransChangeToIfoDepositConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(" +
            "WEB3AccTransChangeToIfoDepositConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1) リクエストデータの整合性をチェックする
        l_request.validate();
        
        // 1.2)補助口座オブジェクトを取得する。 
        //補助口座タイプ： 7（証拠金口座） 
        SubAccount l_subAccountOptions = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_tradingModule.getOrderManager();
        
        // 1.3)　@validate注文(SubAccount)
        // －受付時間チェック 
        // －システム停止中チェック 
        // －顧客のチェック（Ｙ客、管理ロック等）
        l_orderManager.validateOrder(l_subAccountOptions);
        
        // 1.4) 先物取引口座を開設しているかをチェックする
        l_orderManager.validateOpenFuturesTradedAccount(l_subAccountOptions);
        
        // 1.5) get発注日()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        // 1.6) 振替回数が、振替可能回数を超えてないかチェックする
        l_orderManager.validateTransferPossibleCount(l_subAccountOptions,l_datBizDate, OrderCategEnum.CASH_TRANSFER);

        //========remain zhou-yong NO.1 begin ===========
        
        //1.9) get証拠金計算(補助口座 : 補助口座)
        //アイテムの定義
        //証拠金計算インスタンスを生成する。
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService) Services.getService(
                WEB3IfoDepositCalcService.class);
        
        WEB3IfoDepositCalc l_ifoDepositCalc = l_service.getIfoDepositCalc(
            (WEB3GentradeSubAccount)l_subAccountOptions);

        //証拠金振替の指定日を取得する。
        //[引数] 発注日： get発注日()の戻り値
        int l_intBizDate =
            l_orderManager.getMarginTransferDesignatedDate(l_datBizDate);

        //1.10)  calc証拠金残高(指定日 : int)
        //証拠金残高を算出する
        //[引数] 
        //指定日： get証拠金振替指定日()の戻り値
        double l_dblCalcIfoDepositBalance = 
            l_ifoDepositCalc.calcIfoDepositBalance(l_intBizDate);
        
        //1.11) calc未入金額( )
        //アイテムの定義
        //証拠金未入金額を算出する。 
        double l_dblCalcNonPayAmount = l_ifoDepositCalc.calcNonPayAmount();
        
        //1.12) get補助口座(SubAccountTypeEnum)
        //アイテムの定義
        //補助口座オブジェクトを取得する。
        //[引数] 
        //補助口座タイプ： 1（預り金口座） 
        SubAccount l_subAccountEquity = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.13) 商品ID（銘柄ID）を取得する。 
        //[引数] 
        //証券会社： 補助口座.get取引店().getInstitution()の戻り値 
        long l_lngProductId = 
            l_orderManager.getProductId(l_subAccountOptions.getInstitution());        
        
        //1.14) 入出金注文内容インスタンスを生成する。 
        //[引数] 
        //代理入力者： null 
        //注文種別： 1007（振替注文（預り金から株先証拠金）） 
        //振替タイプ： 2（出金） 
        //商品ID： get商品ID()の戻り値 
        //金額： リクエストデータ.振替金額 
        //記述： null 
        //振替予定日： get発注日()の戻り値 
        //決済期間ID： null 
        //注文ID： null 
        
        WEB3AioNewOrderSpec l_aioNewOrderSpecOut = 
            new WEB3AioNewOrderSpec(
                null, 
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN, 
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                Double.parseDouble(l_request.changeAmt), 
                null, 
                l_datBizDate, 
                null, 
                null);
        
        //1.15) 振替注文更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //入出金注文内容： 入出金注文内容オブジェクト（振替元注文） 
        WEB3AioTransferOrderUpdateInterceptor l_orderUpdateInterceptorOut =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpecOut);
        
        //1.16)(*1)プロパティセット 
        l_orderUpdateInterceptorOut.setBizDate(l_datBizDate);
        l_orderUpdateInterceptorOut.setDeliveryDate(l_datBizDate);
        
        //1.17) 入出金注文内容インスタンスを生成する。 
        //[引数] 
        //代理入力者： null 
        //注文種別： 1007（振替注文（預り金から株先証拠金）） 
        //振替タイプ： 1（入金） 
        //商品ID： get商品ID()の戻り値 
        //金額： リクエストデータ.振替金額 
        //記述： null 
        //振替予定日： get発注日()の戻り値 
        //決済期間ID： null 
        //注文ID： null 
        WEB3AioNewOrderSpec l_aioNewOrderSpecIn = 
            new WEB3AioNewOrderSpec(
                null, 
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN, 
                AssetTransferTypeEnum.CASH_IN, 
                l_lngProductId, 
                Double.parseDouble(l_request.changeAmt), 
                null, 
                l_datBizDate, 
                null, 
                null);
        
        //1.18) 振替注文更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //入出金注文内容： 入出金注文内容オブジェクト（振替先注文） 
        WEB3AioTransferOrderUpdateInterceptor l_orderUpdateInterceptorIn =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpecIn);
        
        //1.19) (*2)プロパティセット 
        l_orderUpdateInterceptorIn.setBizDate(l_datBizDate);
        l_orderUpdateInterceptorIn.setDeliveryDate(l_datBizDate);
        
        //注文内容インタセプタの配列
        WEB3AioTransferOrderUpdateInterceptor[] l_orderUpdateInterceptor = 
            {l_orderUpdateInterceptorOut, l_orderUpdateInterceptorIn};
            
        //注文内容の配列
        WEB3AioNewOrderSpec[] l_aioNewOrderSpec = 
            {l_aioNewOrderSpecOut, l_aioNewOrderSpecIn}; 
        
        //1.20) 余力のチェックを行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値（預り金口座） 
        //注文内容インタセプタ： 振込元注文と振込先注文のインタセプタの配列 
        //注文内容： 振込元注文と振込先注文の注文内容の配列 
        //注文種別： 1007（振替注文（預り金から株先証拠金）） 
        //余力更新フラグ： false 
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
            l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccountEquity, 
                l_orderUpdateInterceptor, 
                l_aioNewOrderSpec, 
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN, 
                false);
        
        //取引余力結果.get取引可能額()の戻り値
        double l_dblTradingPower = l_powerResult.getTradingPower();
        
        //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす
        if (l_powerResult.isResultFlg() == false)
        {
            log.debug("取引余力チェックエラー。"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力チェックエラー。");     
        }
        //1.21) createNewOrderId( )
        //アイテムの定義
        //新規の注文IDを取得する。 
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        
        //1.22) createResponse( )
        //アイテムの定義
        //レスポンスデータを生成する。 
        WEB3AccTransChangeToIfoDepositConfirmResponse l_response = 
            (WEB3AccTransChangeToIfoDepositConfirmResponse)l_request.createResponse();
        
        //1.23)  (*) プロパティセット
        //(*) 以下のとおり、プロパティをセットする。
        //レスポンス.振替後振替可能額 = 取引余力結果.get取引可能額()の戻り値
        l_response.aftChangePossAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblTradingPower);        
        
        //レスポンス.振替前証拠金 = 証拠金計算.calc証拠金残高()の戻り値
        l_response.preIfoDeposit = WEB3StringTypeUtility.formatNumber(l_dblCalcIfoDepositBalance);
        
        //レスポンス.振替後証拠金 = 証拠金計算.calc証拠金残高()の戻り値 + リクエストデータ.振替金額
        l_response.aftIfoDeposit = 
            WEB3StringTypeUtility.formatNumber(l_dblCalcIfoDepositBalance + Double.parseDouble(l_request.changeAmt));
        
        //レスポンス.振替後未入金額 = 証拠金計算.calc未入金額()の戻り値 - リクエストデータ.振替金額
        //  （※計算結果 < 0 の場合は、0をセットする。）
        double l_dblAftNonPayAmt = l_dblCalcNonPayAmount - Double.parseDouble(l_request.changeAmt);
        if (l_dblAftNonPayAmt < 0)
        {
            l_dblAftNonPayAmt = 0;
        }
        l_response.aftNonPayAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblAftNonPayAmt);

        //レスポンス.確認時発注日 = 取引時間管理.get発注日()の戻り値
        l_response.checkDate = l_datBizDate;
        
        //レスポンス.注文ID = AIO注文マネージャ.createNewOrderId()の戻り値
        l_response.orderId = String.valueOf(l_lngNewOrderId);
        
        //========remain zhou-yong NO.1 end =========== 
        
        // リターン           
        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * 振替注文の登録を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（証拠金への振替）submit注文１、２」参照。<BR>
     *  ========================================================<BR>
     * シーケンス図(「(証拠金振替サービスモデル) / 証拠金への振替 」<BR>
     * （証拠金への振替）submit注文 )<BR>
     * 　@　@　@:  1.8.calc顧客勘定残高(OrderTypeEnum)<BR>   
     *     リクエストデータ.振替金額 > calc顧客勘定残高()の戻り値の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00761<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)
     * 
     * @@return webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4135AEFC01B0
     */
    protected WEB3AccTransChangeToIfoDepositCompleteResponse submitOrder(
        WEB3AccTransChangeToIfoDepositCompleteRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(" +
            "WEB3AccTransChangeToIfoDepositCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) シーケンス図
        //（証拠金への振替）submit注文１
        
        //submit注文1
        //1.1) validate( )
        //アイテムの定義
        //リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //1.2) get補助口座(SubAccountTypeEnum)  (証拠金口座)
        //アイテムの定義
        //補助口座オブジェクトを取得する。
        //[引数] 
        //補助口座タイプ： 7（証拠金口座） 
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
        
        //1.3) validate注文(SubAccount)
        //アイテムの定義
        //以下のチェックを行う。 
        //　@－受付時間チェック 
        //　@－システム停止中チェック 
        //　@－顧客のチェック（Ｙ客、管理ロック等）
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_tradingModule.getOrderManager();
        
        l_orderManager.validateOrder(l_subAccount);
        
        //1.4) validate先物取引口座開設(SubAccount)
        //アイテムの定義
        //先物取引口座を開設しているかをチェックする。
        //[引数] 
        //補助口座： get補助口座()の戻り値
        l_orderManager.validateOpenFuturesTradedAccount(l_subAccount);
        
        //1.5) get発注日(確認時発注日 : Date)
        //アイテムの定義
        //発注日を取得する。 
        //引数の確認時発注日と取得した発注日が一致しない場合は、例外をスローする。
        //[引数] 
        //確認時発注日： リクエストデータ.確認時発注日 
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //1.6) validate振替可能回数(SubAccount, Date, OrderCategEnum)
        //アイテムの定義
        //振替回数が、振替可能回数を超えてないかチェックする。
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        //注文カテゴリ：　@13（振替）
        l_orderManager.validateTransferPossibleCount(l_subAccount, l_datOrderBizDate, OrderCategEnum.CASH_TRANSFER);
        
        //1.7) get補助口座(SubAccountTypeEnum) （預り金口座)
        //アイテムの定義
        //補助口座オブジェクトを取得する。
        //[引数] 
        //補助口座タイプ： 1（預り金口座） 
        SubAccount l_subAccount2 = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        WEB3GentradeSubAccount l_gentradeSubAccount2 = (WEB3GentradeSubAccount)l_subAccount2;
        
        //1.8) get代理入力者( )
        //アイテムの定義
        //代理入力者オブジェクトを取得する。
        Trader l_trader = this.getTrader();
        
        //1.9)  get新規識別コード(String, String, ProductTypeEnum) （識別コード①@)
        //アイテムの定義
        //新規の識別コードを取得する。
        //[引数] 
        //証券会社コード： 補助口座.get取引店().getInstitution().getInstitutionCode()の戻り値 
        //部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
        //銘柄タイプ： 5（現金）
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class); 
        
        String l_strNewNumber1 = l_hostReqOrderNumberManageService.getNewNumber(
            l_subAccount.getInstitution().getInstitutionCode(), 
            l_subAccount.getMainAccount().getBranch().getBranchCode(), 
            ProductTypeEnum.CASH);

        //1.10) get商品ID(Institution)
        //アイテムの定義
        //商品ID（銘柄ID）を取得する。 
        //[引数] 
        //証券会社： 補助口座.get取引店().getInstitution()の戻り値 
        long l_lngProductId = l_orderManager.getProductId(l_subAccount.getInstitution());
        
        //1.11) 入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, long,
        //double, String, Date, String, Long) （振替元注文①@）
        //アイテムの定義
        //入出金注文内容インスタンスを生成する。
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //注文種別： 1007（振替注文（預り金から株先証拠金）） 
        //振替タイプ： 2（出金） 
        //商品ID： get商品ID()の戻り値 
        //金額： リクエストデータ.振替金額 
        //記述： null 
        //振替予定日： get発注日()の戻り値 
        //決済期間ID： null 
        //注文ID： null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec1 = new WEB3AioNewOrderSpec(
            l_trader,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
            AssetTransferTypeEnum.CASH_OUT,
            l_lngProductId,
            Double.parseDouble(l_request.changeAmt),
            null,
            l_datOrderBizDate,
            null,
            null); 
        
        //1.12) 振替注文更新インタセプタ(入出金注文内容)
        //アイテムの定義
        //振替注文更新インタセプタのインスタンスを生成する。
        //[引数] 
        //入出金注文内容： 入出金注文内容オブジェクト（振替元注文①@） 
        WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor1 =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec1);
        
        //1.13) (*1)プロパティセット
        //(*1)(*2) 以下のとおりに、プロパティをセットする。
        //インタセプタ.発注日 = get発注日()の戻り値
        l_transferOrderUpdateInterceptor1.setBizDate(l_datOrderBizDate);
        
        //インタセプタ.受渡日 = get発注日()の戻り値
        l_transferOrderUpdateInterceptor1.setDeliveryDate(l_datOrderBizDate);
        
        //インタセプタ.識別コード = get新規識別コード()の戻り値（識別コード①@）
        l_transferOrderUpdateInterceptor1.setOrderRequestNumber(l_strNewNumber1);
        
        l_transferOrderUpdateInterceptor1.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
        
        //1.14) 入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
        //long, double, String, Date, String, Long) (振替先注文①@)
        //アイテムの定義
        //入出金注文内容インスタンスを生成する。
        // [引数] 
        //代理入力者： get代理入力者()の戻り値 
        //注文種別： 1007（振替注文（預り金から株先証拠金）） 
        //振替タイプ： 1（入金） 
        //商品ID： get商品ID()の戻り値 
        //金額： リクエストデータ.振替金額 
        //記述： null 
        //振替予定日： get発注日()の戻り値 
        //決済期間ID： null 
        //注文ID： null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec2 = new WEB3AioNewOrderSpec(
            l_trader,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
            AssetTransferTypeEnum.CASH_IN,
            l_lngProductId,
            Double.parseDouble(l_request.changeAmt),
            null,
            l_datOrderBizDate,
            null,
            null); 
        
        //1.15) 振替注文更新インタセプタ(入出金注文内容)
        //アイテムの定義
        //振替注文更新インタセプタのインスタンスを生成する。
        //[引数] 
        //入出金注文内容： 入出金注文内容オブジェクト（振替先注文①@） 
        WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor2 =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec2);
        
        //1.16)  (*2)プロパティセット
        //(*1)(*2) 以下のとおりに、プロパティをセットする。
        //インタセプタ.発注日 = get発注日()の戻り値
        l_transferOrderUpdateInterceptor2.setBizDate(l_datOrderBizDate);
        
        //インタセプタ.受渡日 = get発注日()の戻り値
        l_transferOrderUpdateInterceptor2.setDeliveryDate(l_datOrderBizDate);
        
        //インタセプタ.識別コード = get新規識別コード()の戻り値（識別コード①@）
        l_transferOrderUpdateInterceptor2.setOrderRequestNumber(l_strNewNumber1);
        
        //MQステータス = 1:送信済み
        l_transferOrderUpdateInterceptor2.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
        
        //1.17) validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 
        //注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
        //アイテムの定義
        //余力残高の更新を行う。
        //[引数] 
        //補助口座： get補助口座()の戻り値（預り金口座） 
        //注文内容インタセプタ： 振込元注文①@と振込先注文①@のインタセプタの配列 
        //注文内容： 振込元注文①@と振込先注文①@の注文内容の配列 
        //注文種別： 1007（振替注文（預り金から株先証拠金）） 
        //余力更新フラグ： false
        Object[] l_objCashoutCancelUpdate= {l_transferOrderUpdateInterceptor1,
                l_transferOrderUpdateInterceptor2};
        
        Object[] l_objNewOrderSpec= {l_aioNewOrderSpec1,l_aioNewOrderSpec2};
        
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_tPTradingPowerResult = 
            l_service.validateTradingPower(l_gentradeSubAccount2, l_objCashoutCancelUpdate, 
                l_objNewOrderSpec, OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN, false);
        
        //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローする。
        if(!l_tPTradingPowerResult.isResultFlg())
        {
            log.debug("取引余力チェックエラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力結果.判定フラグ == false");            
        }        

        WEB3GentradeMainAccount l_genMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();        
        
        boolean l_blnMarginAccountEstablished =
            l_genMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        double l_dblTransferAmountToEquitySubAcount = 0.0;
        SubAccount l_subAccount3 = null;
        
        //補助口座.getMainAccount().is信用口座開設(弁済区分（=”指定なし”）)の戻り値 = true の場合、実施
        if(l_blnMarginAccountEstablished)
        {
            //1.18)保証金口座から預り金口座へ振替が必要な額を取得する。 
            //[引数] 
            //補助口座： get補助口座()の戻り値（保証金口座） 
            //必要現金： リクエストデータ.振替金額 
            //受渡日： get発注日()の戻り値 
            l_subAccount3 = this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);          
       
            WEB3GentradeSubAccount l_gentradeSubAccount3 = (WEB3GentradeSubAccount)l_subAccount3;
            
            //1.19) get預り金への振替額(補助口座 : 補助口座, 必要現金 : double, 受渡日 : Date)
            //保証金口座から預り金口座へ振替が必要な額を取得する。 
            //[引数] 
            //補助口座： get補助口座()の戻り値（保証金口座） 
            //必要現金： リクエストデータ.振替金額 
            //受渡日： get発注日()の戻り値            
            l_dblTransferAmountToEquitySubAcount = 
                l_service.getTransferAmountToEquitySubAcount(
                    l_gentradeSubAccount3, 
                    Double.parseDouble(l_request.changeAmt), 
                    l_datOrderBizDate);
           
        }
        //1.20) submit振替注文(SubAccount, ProductTypeEnum, OrderTypeEnum, NewOrderSpec, 
        //AioOrderManagerPersistenceInterceptor, long, String)
        //アイテムの定義
        //振替元の振替注文を登録する。
        //[引数] 
        //補助口座： get補助口座()の戻り値（預り金口座） 
        //銘柄タイプ： 5（現金） 
        //注文種別： 1007（振替注文（預り金から株先証拠金）） 
        //注文内容： 入出金注文内容（振替元注文①@） 
        //インタセプタ： 振替注文更新インタセプタ（振替元注文①@） 
        //注文ID： リクエストデータ.注文ID 
        //パスワード： リクエストデータ.暗証番号 
        l_orderManager.submitTransferOrder(
            l_subAccount2,
            ProductTypeEnum.CASH,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
            l_aioNewOrderSpec1,
            l_transferOrderUpdateInterceptor1,
            Long.parseLong(l_request.orderId),
            l_request.password);
        
        //1.21) createNewOrderId( ) (注文ID①@)
        //アイテムの定義
        //反対注文用の注文IDを取得する。
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        
        //1.22) submit振替注文(SubAccount, ProductTypeEnum, OrderTypeEnum, NewOrderSpec,
        //AioOrderManagerPersistenceInterceptor, long, String)
        //アイテムの定義
        //振替先の振替注文を登録する。
        //[引数] 
        //補助口座： get補助口座()の戻り値（証拠金口座） 
        //銘柄タイプ： 5（現金） 
        //注文種別： 1007（振替注文（預り金から株先証拠金）） 
        //注文内容： 入出金注文内容（振替先注文①@） 
        //インタセプタ： 振替注文更新インタセプタ（振替先注文①@） 
        //注文ID： createNewOrderId()の戻り値（注文ID①@） 
        //パスワード： リクエストデータ.暗証番号 
        l_orderManager.submitTransferOrder(
            l_subAccount,
            ProductTypeEnum.CASH,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
            l_aioNewOrderSpec2,
            l_transferOrderUpdateInterceptor2,
            l_lngNewOrderId,
            l_request.password);
        
        //1.23) シーケンス図
        //（証拠金への振替）submit注文２
        
		//　@業務日付の取得（yyyyMMdd）
		String l_strBizDate1 = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd");
		Date l_datBizDate1 = WEB3DateUtility.getDate(l_strBizDate1, "yyyyMMdd");
		
		//　@発注日をyyyyMMdd型に変更
		String l_strOrderBizdat1 = WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
		Date l_datOrderBizDate1 = WEB3DateUtility.getDate(l_strOrderBizdat1, "yyyyMMdd");
		
		//1.2) is保証金振替(補助口座 l_subAccount, Date 業務日付, Date 注文単位.発注日)
		boolean l_blnIsDepositTransfer = l_orderManager.isDepositTransfer(l_subAccount, l_datBizDate1, l_datOrderBizDate1);
      
        //1.3) 補助口座.getMainAccount().is信用口座開設()の戻り値 = true and
        //get当日預り金への振替額()の戻り値 > 0 and
        //is保証金振替()の戻り値 = trueの場合
        //信用保証金から預り金への振替注文も登録する。
        if (l_blnMarginAccountEstablished && 
            l_dblTransferAmountToEquitySubAcount > 0 &&
		    l_blnIsDepositTransfer)
        {
            //1.3.1) get新規識別コード(String, String, ProductTypeEnum)
            //新規の識別コードを取得する。
            //[引数] 
            //証券会社コード： 補助口座.get取引店().getInstitution().getInstitutionCode()の戻り値 
            //部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
            //銘柄タイプ： 5（現金）
            String l_strNewNumber2 = l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                ProductTypeEnum.CASH);
            
            //1.3.2) 入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, long,
            //double, String, Date, String, Long) (振替元注文 ②)
            //入出金注文内容インスタンスを生成する。
            //[引数] 
            //代理入力者： get代理入力者()の戻り値 
            //注文種別： 1006（振替注文（信用保証金から預り金）） 
            //振替タイプ： 2（出金） 
            //商品ID： get商品ID()の戻り値 
            //金額： get当日預り金への振替額()の戻り値 
            //記述： null 
            //振替予定日： get発注日()の戻り値 
            //決済期間ID： null 
            //注文ID： null 
            WEB3AioNewOrderSpec l_aioNewOrderSpec3 = new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                l_dblTransferAmountToEquitySubAcount,
                null,
                l_datOrderBizDate,
                null,
                null); 
            
            //1.3.3) 振替注文更新インタセプタ(入出金注文内容)
            //アイテムの定義
            //振替注文更新インタセプタのインスタンスを生成する。
            //[引数] 
            //入出金注文内容： 入出金注文内容オブジェクト（振替元注文②） 
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor3 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec3);
            
            //1.3.4) (*3)プロパティセット
            //(*3)(*4) 以下のとおりに、プロパティをセットする。
            //インタセプタ.発注日 = get発注日()の戻り値
            l_transferOrderUpdateInterceptor3.setBizDate(l_datOrderBizDate);
            
            //インタセプタ.受渡日 = get発注日()の戻り値
            l_transferOrderUpdateInterceptor3.setDeliveryDate(l_datOrderBizDate);
            
            //インタセプタ.識別コード = get新規識別コード()の戻り値（識別コード②）
            l_transferOrderUpdateInterceptor3.setOrderRequestNumber(l_strNewNumber2);
            
            //MQステータス = 1:送信済み
            l_transferOrderUpdateInterceptor3.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            
            //1.3.5) 入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
            //long, double, String, Date, String, Long) (振替先注文②)
            //入出金注文内容インスタンスを生成する。
            //[引数] 
            //代理入力者： get代理入力者()の戻り値 
            //注文種別： 1006（振替注文（信用保証金から預り金）） 
            //振替タイプ： 1（入金） 
            //商品ID： get商品ID()の戻り値 
            //金額： get当日預り金への振替額()の戻り値
            //記述： null 
            //振替予定日： get発注日()の戻り値 
            //決済期間ID： null 
            //注文ID： null 
            WEB3AioNewOrderSpec l_aioNewOrderSpec4 = new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                AssetTransferTypeEnum.CASH_IN,
                l_lngProductId,
                l_dblTransferAmountToEquitySubAcount,
                null,
                l_datOrderBizDate,
                null,
                null); 
            
            //1.3.6) 振替注文更新インタセプタ(入出金注文内容)
            //アイテムの定義
            //振替注文更新インタセプタのインスタンスを生成する。
            //[引数] 
            //入出金注文内容： 入出金注文内容オブジェクト（振替先注文②） 
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor4 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec4);
            
            //1.3.7) (*4)プロパティセット
            //(*3)(*4) 以下のとおりに、プロパティをセットする。
            //インタセプタ.発注日 = get発注日()の戻り値
            l_transferOrderUpdateInterceptor4.setBizDate(l_datOrderBizDate);
            
            //インタセプタ.受渡日 = get発注日()の戻り値
            l_transferOrderUpdateInterceptor4.setDeliveryDate(l_datOrderBizDate);
            
            //インタセプタ.識別コード = get新規識別コード()の戻り値（識別コード②）
            l_transferOrderUpdateInterceptor4.setOrderRequestNumber(l_strNewNumber2);
            
            //MQステータス = 1:送信済み
            l_transferOrderUpdateInterceptor4.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            
            //1.3.8) createNewOrderId( ) (注文ID②)
            //アイテムの定義
            //保証金振替注文用の注文IDを取得する
            long l_lngNewOrderId2 = l_orderManager.createNewOrderId();
            
            //1.3.9) submit振替注文(SubAccount, ProductTypeEnum, OrderTypeEnum,
            //NewOrderSpec, AioOrderManagerPersistenceInterceptor, long, String)
            //アイテムの定義
            //信用振替注文（振替元）を登録する。
            //[引数] 
            //補助口座： get補助口座()の戻り値（保証金口座） 
            //銘柄タイプ： 5（現金） 
            //注文種別： 1006（振替注文（信用保証金から預り金）） 
            //注文内容： 入出金注文内容（振替元注文②） 
            //インタセプタ： 振替注文更新インタセプタ（振替元注文②） 
            //注文ID： createNewOrderId()の戻り値（注文ID②） 
            //パスワード： リクエストデータ.暗証番号
            l_orderManager.submitTransferOrder(
                l_subAccount3,
                ProductTypeEnum.CASH,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                l_aioNewOrderSpec3,
                l_transferOrderUpdateInterceptor3,
                l_lngNewOrderId2,
                l_request.password);
            
            //1.3.10) createNewOrderId( ) (注文ID③)
            //アイテムの定義
            //保証金振替反対注文用の注文IDを取得する。
            long l_lngNewOrderId3 = l_orderManager.createNewOrderId();

            //1.3.11) submit振替注文()            
            //信用振替の反対注文（振替先）を登録する。
            //[引数] 
            //補助口座： get補助口座()の戻り値（預り金口座） 
            //銘柄タイプ： 5（現金） 
            //注文種別： 1006（振替注文（信用保証金から預り金）） 
            //注文内容： 入出金注文内容（振替先注文②） 
            //インタセプタ： 振替注文更新インタセプタ（振替先注文②） 
            //注文ID： createNewOrderId()の戻り値（注文ID③） 
            //パスワード： リクエストデータ.暗証番号 
            l_orderManager.submitTransferOrder(
                l_subAccount2,
                ProductTypeEnum.CASH,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                l_aioNewOrderSpec4,
                l_transferOrderUpdateInterceptor4,
                l_lngNewOrderId3,
                l_request.password);
        }
        
        //1.4)余力の再計算を行う。 
        //[引数] 
        //補助口座： 補助口座オブジェクト（預り金口座） 
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(l_gentradeSubAccount2);
        
        //1.5)  getOrder(long)(AIO注文マネージャ::getOrder)
        //アイテムの定義
        //注文を取得する。
        //[引数] 
        //注文ID： リクエストデータ.注文ID 
        Order l_order = null;
        try
        {
            l_order = l_orderManager.getOrder(Long.parseLong(l_request.orderId));
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文を取得する: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //1.6)  createResponse( )(証拠金への振替完了リクエスト::createResponse)
        ///アイテムの定義
        //レスポンスデータを生成する        
        WEB3AccTransChangeToIfoDepositCompleteResponse l_response = 
            (WEB3AccTransChangeToIfoDepositCompleteResponse)l_request.createResponse();
        //1.7) (*) プロパティセット
        //(*) 以下のとおり、プロパティをセットする。
        //レスポンス.更新時間 = 注文.更新日付
        l_response.lastUpdatedTimestamp = 
            ((AioOrderRow)l_order.getDataSourceObject()).getLastUpdatedTimestamp();
        //レスポンス.注文ID = リクエストデータ.注文ID
        l_response.orderId = l_request.orderId;
        
        return l_response;
    }
}
@
