head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginOpenMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引新規建サービスImpl(WEB3ToSuccMarginOpenMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08　@呉　@鈞(中訊) 新規作成
Revesion History : 2007/01/11  齊  珂(中訊) 仕様変更モデル216
Revesion History : 2007/01/17  齊  珂(中訊) 仕様変更モデル222
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginRequestAdapter;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOpenMarginServiceImpl;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）信用取引新規建サービスImpl)<BR>
 * （連続）信用取引新規建サービス実装クラス<BR>
 * 
 * @@author 呉　@鈞(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccMarginOpenMarginServiceImpl extends WEB3MarginOpenMarginServiceImpl
    implements WEB3ToSuccMarginOpenMarginService 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginOpenMarginServiceImpl.class);
    
    /**
     * @@roseuid 436ACF780119
     */
    public WEB3ToSuccMarginOpenMarginServiceImpl() 
    {
     
    }
    
    /**
     * （連続）信用取引新規建サービス処理を実施する<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * [（連続）信用取引新規建注文確認リクエストの場合]<BR>
     * 　@this.validate注文()をコールする。<BR>
     * [（連続）信用取引新規建注文完了リクエストの場合]<BR>
     * 　@this.submit注文()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 432F76C50123
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3SuccMarginOpenConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccMarginOpenConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginOpenCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccMarginOpenCompleteRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * （連続）信用取引新規建発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引新規建サービス）validate注文」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引新規建注文確認リクエストオブジェクト<BR>
     * @@return WEB3SuccMarginOpenConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 432F77FA0058
     */
    public WEB3SuccMarginOpenConfirmResponse validateOrder(
        WEB3SuccMarginOpenConfirmRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginOpenConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3 get株式親注文の注文単位(（親注文）注文ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3SuccCommonInfo l_commonInfo = l_request.succCommonInfo;
        
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(
            Long.parseLong(l_commonInfo.parentOrderId));
        
        //1.4 validateリクエストデータat反対取引(WEB3GenRequest, 注文単位)
        this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);
        
        //1.5 validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_commonInfo.succTradingType,
            l_orderUnit);
        
        //1.6 validate連続注文最大設定数(親注文の注文単位 : OrderUnit)
        this.validateSuccOrderMaxQuantity(l_orderUnit);
        
        //1.7 validate注文(リクエストデータ : 信用取引新規建注文確認リクエスト)
        WEB3SuccMarginOpenConfirmResponse l_response = (
            WEB3SuccMarginOpenConfirmResponse) super.validateOrder(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * （連続）信用取引新規建注文登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引新規建サービス）submit注文」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引新規建注文完了リクエストオブジェクト<BR>
     * @@return WEB3SuccMarginOpenCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 432F77FA00A6
     */
    protected WEB3SuccMarginOpenCompleteResponse submitOrder(
        WEB3SuccMarginOpenCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginOpenCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3 get株式親注文の注文単位(（親注文）注文ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3SuccCommonInfo l_commonInfo = l_request.succCommonInfo;
        
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(
            Long.parseLong(l_commonInfo.parentOrderId));
        
        //1.4 validateリクエストデータat反対取引(WEB3GenRequest, 注文単位)
        validateRequestDataAtReversingTrade(l_request, l_orderUnit);
        
        //1.5 validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT,
            l_commonInfo.succTradingType,
            l_orderUnit);
 
        //1.6 validate連続注文最大設定数(親注文の注文単位 : OrderUnit)
        this.validateSuccOrderMaxQuantity(l_orderUnit);

        //1.7 super.submit注文()
        WEB3SuccMarginOpenCompleteResponse l_response = (
            WEB3SuccMarginOpenCompleteResponse) super.submitOrder(l_request);
        
        //1.8 notify予約注文登録(long)
        this.notifyRsvOrderRegister(Long.parseLong(l_request.orderId));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * （連続）信用取引新規建リクエストアダプタ.create(引数のリクエスト)を<BR>
     * コールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3MarginOpenMarginRequestAdapter
     * @@roseuid 432FEC2003CB
     */
    protected WEB3MarginOpenMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOpenMarginRequestAdapter l_marginRequestAdpter = 
            WEB3ToSuccMarginOpenMarginRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_marginRequestAdpter;
    }
    
    /**
     * (validate取引余力)<BR>
     * 取引余力をチェックする。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 余力チェックを実施する部店(*3)の場合のみ、<BR>
     * 新規建可能額(*1)と概算受渡代金（建代金(*2)）を比較し、<BR>
     * （概算受渡代金 > 新規建可能額）の場合は、<BR>
     * 「取引余力不足」の例外をthrowする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00667<BR>
     * <BR>
     * 以外、nullを返却する。<BR>
     * <BR>
     * (*1)取引余力サービスImpl.<BR>
     * 　@get信用新規建可能額～連続注文～(パラメータ.補助口座, null)<BR>
     * にて取得。<BR>
     * <BR>
     * (*2)パラメータ.信用新規建注文内容.get建代金()にて取得。<BR>
     * <BR>
     * (*3)余力チェックを実施する部店<BR>
     * 　@連続注文マネージャImpl.is余力チェック実施部店()==trueの場合は、<BR>
     * 　@余力チェックを実施する部店であると判定する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_orderSpec - (信用新規建注文内容)<BR>
     * 信用新規建注文内容オブジェクト。<BR>
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ。<BR>
     * （false：　@確認時、true：　@完了時）<BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料オブジェクト<BR>
     * @@param l_validationResult - (発注審査結果)<BR>
     * 信用新規建新規注文発注審査結果オブジェクト<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     * @@roseuid 432FEC210002
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MarginOpenContractOrderSpec l_orderSpec, 
        boolean l_blnUpdateFlg, 
        WEB3GentradeCommission l_commission, 
        WEB3MarginNewOrderValidationResult l_validationResult) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateTradingPower(" +
            "WEB3GentradeSubAccount, " +
            "WEB3MarginOpenContractOrderSpec, " +
            "boolean, " +
            "WEB3GentradeCommission, " +
            "WEB3MarginNewOrderValidationResult)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderSpec == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager =
        (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        if (l_toOrderManager.isCheckTradingPowerBranch(l_subAccount) == false)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        double l_dblTradingPower = 0.0;

        //新規建可能額
        l_dblTradingPower = l_tradingPowerService.getSuccMarginTradingPower(l_subAccount, null);
        //概算受渡代金
        double l_dblContractAmount = l_orderSpec.getContractAmount();
        
        if (l_dblContractAmount > l_dblTradingPower)
        {
            log.debug("取引余力不足エラー。（信用新規建可能額不足）");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00667,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力不足エラー。（信用新規建可能額不足）");
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (submit新規建注文)<BR>
     * 予約注文を登録する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 連続注文マネージャ.submit信用新規建新規予約注文()をコールする。<BR>
     * <BR>
     * [submit信用新規建新規予約注文()に指定する引数]<BR>
     * 補助口座：　@引数の補助口座<BR>
     * 注文内容：　@引数の信用新規建注文内容<BR>
     * 注文ID：　@引数の注文ID<BR>
     * 取引パスワード：　@引数のパスワード<BR>
     * 連続注文取引区分：　@引数のリクエストアダプタ.リクエスト.連続注文共通情報.<BR>
     * 連続注文取引区分<BR>
     * 単価調整値：　@引数のリクエストアダプタ.リクエスト.単価調整値情報.get単価調整値()<BR>
     * 　@　@※引数のリクエストアダプタ.リクエスト.単価調整値情報==nullの場合は、<BR>
     * nullをセット<BR>
     * 親注文の注文単位：　@引数のリクエストアダプタ.親注文の注文単位<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_orderSpec - (信用新規建注文内容)<BR>
     * 信用新規建注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 予約注文の注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_requestAdaptor - (新規建リクエストアダプタ)<BR>
     * 信用取引新規建リクエストアダプタオブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 432FEC210012
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MarginOpenContractOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        WEB3MarginOpenMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitOpenContractOrder(" +
            "WEB3GentradeSubAccount, " +
            "WEB3MarginOpenContractOrderSpec, " +
            "long, " +
            "String, " +
            "WEB3MarginOpenMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccMarginOpenMarginRequestAdapter l_toSuccEquityOrderRequestAdapter = null;
        if (l_requestAdaptor instanceof WEB3ToSuccMarginOpenMarginRequestAdapter)
        {
            l_toSuccEquityOrderRequestAdapter = 
                (WEB3ToSuccMarginOpenMarginRequestAdapter) l_requestAdaptor;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3SuccCommonInfo l_succCommonInfo = null;
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (l_requestAdaptor.request instanceof WEB3SuccMarginOpenConfirmRequest)
        {
            WEB3SuccMarginOpenConfirmRequest l_openConfirmRequest = 
                (WEB3SuccMarginOpenConfirmRequest) l_requestAdaptor.request;
            l_succCommonInfo = l_openConfirmRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_openConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_requestAdaptor.request instanceof WEB3SuccMarginOpenCompleteRequest)
        {
            WEB3SuccMarginOpenCompleteRequest l_openCompleteRequest = 
                (WEB3SuccMarginOpenCompleteRequest) l_requestAdaptor.request;
            l_succCommonInfo = l_openCompleteRequest.succCommonInfo;
            l_priceAdjustmentValueInfo = l_openCompleteRequest.priceAdjustmentValueInfo;
        }

        //連続注文マネージャ.submit信用新規建新規予約注文
        Double l_dblPrice = null;
        if (l_priceAdjustmentValueInfo == null)
        {
            l_dblPrice = null;
        }
        else 
        {
            l_dblPrice = new Double(l_priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }

        l_toOrderManager.submitEqtypeOpenContractNewOrder(
            l_subAccount, 
            l_orderSpec, 
            l_lngOrderId, 
            l_strTradingPassword, 
            l_succCommonInfo.succTradingType,
            l_dblPrice,
            l_toSuccEquityOrderRequestAdapter.parentOrderUnit); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateリクエストデータat反対取引)<BR>
     * 反対取引指定時に固有の、リクエストデータのプロパティチェックを行う。<BR>
     * <BR>
     * １）　@連続注文マネージャImpl.is反対売買取引(<BR>
     * 　@　@　@引数のリクエスト.連続注文共通情報.連続注文取引区分, 引数<BR>
     * の親注文の注文単位)==false<BR>
     * 　@　@　@（＝反対取引でない）場合は、<BR>
     * 　@　@　@何もせずにreturnする。<BR>
     * <BR>
     * 　@　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@以下の条件のいずれかに該当する場合は、<BR>
     * 　@　@　@「反対取引時の銘柄指定が、親注文と不整合」の例外をthrowする。<BR>
     * 　@　@　@・引数のリクエストデータ.銘柄コード≠<BR>
     * 　@　@　@　@引数の親注文の注文単位.銘柄IDに該当する株式銘柄.銘柄コード<BR>
     * 　@　@　@・引数のリクエストデータ市場コード≠<BR>
     * 　@　@　@　@引数の親注文の注文単位.市場IDに該当する市場.市場コード<BR>
     * 　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@tag: BUSINESS_ERROR_02250<BR>
     * <BR>
     * 　@　@　@※引数のリクエストデータは、以下のいずれかにキャストすること。<BR>
     * 　@　@　@　@・（連続）信用取引新規建注文確認リクエスト<BR>
     * 　@　@　@　@・（連続）信用取引新規建注文完了リクエスト<BR>
     * <BR>
     * ３）　@引数の親注文の注文単位.注文カテゴリ == "現引・現渡注文"の場合、<BR>
     * 　@引数のリクエストデータ.単価調整値情報≠nullであれば、<BR>
     * 　@「親注文が現引現渡注文の場合、±指値は指定不可」の<BR>
     * 　@例外をthrowする。<BR>
     * 　@class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02291<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4343ADAB0371
     */
    protected void validateRequestDataAtReversingTrade(
        WEB3GenRequest l_request, EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRequestDataAtReversingTrade(WEB3GenRequest, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        if (l_parentOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3SuccCommonInfo l_succCommonInfo = null;
        String l_strProductCode = null;
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (l_request instanceof WEB3SuccMarginOpenConfirmRequest)
        {
            WEB3SuccMarginOpenConfirmRequest l_openConfirmRequest = (WEB3SuccMarginOpenConfirmRequest) l_request;
            l_succCommonInfo = l_openConfirmRequest.succCommonInfo;
            l_strProductCode = l_openConfirmRequest.productCode;
            l_priceAdjustmentValueInfo = l_openConfirmRequest.priceAdjustmentValueInfo;
        }
        else if (l_request instanceof WEB3SuccMarginOpenCompleteRequest)
        {
            WEB3SuccMarginOpenCompleteRequest l_openCompleteRequest = (WEB3SuccMarginOpenCompleteRequest) l_request;
            l_succCommonInfo = l_openCompleteRequest.succCommonInfo;
            l_strProductCode = l_openCompleteRequest.productCode;
            l_priceAdjustmentValueInfo = l_openCompleteRequest.priceAdjustmentValueInfo;
        }
        
        //1） 連続注文マネージャImpl.is反対売買取引
        boolean l_blnIsReverTrade = l_toOrderManager.isReversingTrade(
            l_succCommonInfo.succTradingType, l_parentOrderUnit);
        
        if (!l_blnIsReverTrade)
        {
            log.exiting(STR_METHOD_NAME); 
            return;
        }
        
        //2）以下の条件のいずれかに該当する場合は、
        // 　@　@　@「反対取引時の銘柄指定が、親注文と不整合」の例外をthrowする。
        // 　@　@　@・引数のリクエストデータ.銘柄コード≠
        // 　@　@　@　@引数の親注文の注文単位.銘柄IDに該当する株式銘柄.銘柄コード

        // 　@　@　@class: WEB3BusinessLayerException 
        // 　@　@　@tag: BUSINESS_ERROR_02250
        // 　@　@　@※引数のリクエストデータは、以下のいずれかにキャストすること。
        // 　@　@　@　@・（連続）信用取引新規建注文確認リクエスト
        // 　@　@　@　@・（連続）信用取引新規建注文完了リクエスト
        EqTypeProduct l_product = (EqTypeProduct) l_parentOrderUnit.getProduct();

        if ((l_strProductCode == null) || !l_strProductCode.equals(l_product.getProductCode()))
        {
           log.debug("反対取引時の銘柄指定が、親注文と不整合です。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02250,
               getClass().getName() + "." + STR_METHOD_NAME,
               "反対取引時の銘柄指定が、親注文と不整合です。");
        }
        
        //3) 引数の親注文の注文単位.注文カテゴリ == "現引・現渡注文"の場合、
        // 　@引数のリクエストデータ.単価調整値情報≠nullであれば、
        // 　@「親注文が現引現渡注文の場合、±指値は指定不可」の
        // 　@例外をthrowする。
        // 　@class: WEB3BusinessLayerException 
        // 　@tag: BUSINESS_ERROR_02291
        if (OrderCategEnum.SWAP_MARGIN.equals(
            l_parentOrderUnit.getOrderCateg()) && (l_priceAdjustmentValueInfo != null))
        {
            log.debug("親注文が現引現渡注文の場合、±指値は指定不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02291,
                getClass().getName() + "." + STR_METHOD_NAME,
                "親注文が現引現渡注文の場合、±指値は指定不可です。");
        }
        
        log.exiting(STR_METHOD_NAME);   
    }
    
    /**
     * (validate連続注文最大設定数)<BR>
     * 連続注文の最大設定数を超過してしまわないかどうかをチェックする。<BR>
     * <BR>
     * 連続注文マネージャ.validate連続注文最大設定数(引数の親注文の<BR>
     * 注文単位)にdelegateする。<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433C933F02A9
     */
    protected void validateSuccOrderMaxQuantity(EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateSuccOrderMaxQuantity(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        
        l_toOrderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);
        
        log.exiting(STR_METHOD_NAME);   
    }
    
    /**
     * (notify予約注文登録)<BR>
     * 予約注文の登録をルールエンジンサーバに通知する。<BR>
     * <BR>
     * １）　@予約注文単位の取得<BR>
     * 　@連続注文マネージャ.get株式予約注文単位()にて、<BR>
     * 　@予約注文単位を取得する。<BR>
     * <BR>
     * 　@[get株式予約注文単位()に指定する引数]<BR>
     * 　@　@注文ID：　@子注文の注文ID<BR>
     * <BR>
     * ２）　@ルールエンジンに注文の登録を通知する。<BR>
     * 　@拡張株式注文マネージャ.notifyルールエンジンサーバ()<BR>
     * 　@をコールする。<BR>
     * <BR>
     * 　@[notifyルールエンジンサーバ()に指定する引数]<BR>
     * 　@　@注文単位：　@１）の戻り値<BR>
     * 　@　@処理：　@NEW_OPEN_CONTRACT_ORDER<BR>
     * @@param l_lngSubOrderId - (子注文の注文ID)<BR>
     * 子注文の注文ID。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 435EDBFC0375
     */
    protected void notifyRsvOrderRegister(long l_lngSubOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            //１）予約注文単位の取得
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngSubOrderId);
        }
        catch (NotFoundException l_nft)
        {
            log.error(STR_METHOD_NAME, l_nft);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nft.getMessage(),
                l_nft);
        }

        //２）ルールエンジンに注文の登録を通知する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        
        l_orderMgr.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER);
            
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (set単価)<BR>
     * 引数のレスポンス．調整後単価に単価を設定する。 <BR>
     * <BR>
     * １）リクエスト.単価調整値情報≠null（±指値指定）の場合 <BR>
     * 　@　@レスポンス．調整後単価に、引数のリクエストアダプタ．get単価()の戻り値をセットする。 <BR>
     * <BR>
     * ２）上記以外の場合、 <BR>
     * 　@　@何もせずリターンする。 <BR>
     * <BR>
     * @@param l_adapter - (信用取引新規建リクエストアダプタ)<BR>
     * 信用取引新規建リクエストアダプタ。<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンス。<BR>
     * @@throws WEB3BaseException 
     */
    protected void setPrice(WEB3MarginOpenMarginRequestAdapter l_adapter,
        WEB3GenResponse l_response) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setPrice(WEB3MarginOpenMarginRequestAdapter, "
            + "WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginOpenConfirmRequest l_confirmRequest = null;

        if (l_adapter == null || l_response == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

        WEB3GenRequest l_request = l_adapter.request;
        
        if (l_request instanceof WEB3SuccMarginOpenConfirmRequest)
        {
            l_confirmRequest = (WEB3SuccMarginOpenConfirmRequest)l_adapter.request;
        
            //１）リクエスト.単価調整値情報≠null（±指値指定）の場合 
            if (l_confirmRequest.priceAdjustmentValueInfo != null)
            {
                WEB3SuccMarginOpenConfirmResponse l_confirmResponse = 
                    (WEB3SuccMarginOpenConfirmResponse)l_response;
                
                //レスポンス．調整後単価に、引数のリクエストアダプタ．get単価()の戻り値をセットする。 
                l_confirmResponse.afterAdjustmentPrice = 
                    WEB3StringTypeUtility.formatNumber(l_adapter.getPrice());
            }
        }
        log.exiting(STR_METHOD_NAME);  
    }
}@
