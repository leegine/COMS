head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeCloseMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引訂正返済サービスImpl(WEB3ToSuccMarginChangeCloseMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17 譚漢江(中訊) 新規作成
Revesion History : 2006/10/30 唐性峰(中訊)　@モデルNo.161
Revesion History : 2007/01/11 齊珂  (中訊) 仕様変更モデル216
Revesion History : 2007/01/19 肖志偉(中訊) モデル225
Revesion History : 2007/08/20 武波(中訊) モデル242
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginCommonRequest;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginRequestAdapter;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccMarginChangeSettleContractOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeCloseMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginRequestAdapter;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）信用取引訂正返済サービスImpl)<BR>
 * （連続）信用取引訂正返済サービス実装クラス<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeCloseMarginServiceImpl 
    extends WEB3ToSuccMarginCloseMarginServiceImpl 
    implements WEB3ToSuccMarginChangeCloseMarginService 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeCloseMarginServiceImpl.class);

    /**
     * @@roseuid 436ACF7C01C5
     */
    public WEB3ToSuccMarginChangeCloseMarginServiceImpl() 
    {
     
    }
    
    /**
     * （連続）信用取引訂正返済サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * validate注文とsubmit注文を呼び分ける。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433CFD070141
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
                getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccMarginCloseChangeConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccMarginCloseChangeConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginCloseChangeCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccMarginCloseChangeCompleteRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 信用取引の訂正返済発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引訂正返済サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引注文訂正返済確認リクエストオブジェクト。<BR>
     * @@return WEB3SuccMarginCloseChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 433D001803C2
     */
    protected WEB3SuccMarginCloseChangeConfirmResponse validateOrder(WEB3SuccMarginCloseChangeConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginCloseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 get株式予約注文単位(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 validateリクエストデータat反対取引(WEB3GenRequest, 株式予約注文単位Impl)
        this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);
        
        //1.4 validate訂正可能状態( )
        l_orderUnit.validateOrderForChangeability();
        
        //1.5 validate決済済建株(株式予約注文単位Impl)
        this.validateClosedMarginContract(l_orderUnit);
        
        //1.6 create確認リクエスト(（連続）信用取引注文訂正返済確認リクエスト, 株式予約注文単位Impl)
        WEB3SuccMarginCloseConfirmRequest l_confirmRequest = this.createConfirmRequest(l_request, l_orderUnit);
        
        //1.7 validate注文(（連続）信用取引返済注文確認リクエスト)
        WEB3SuccMarginCloseConfirmResponse l_confirmResponse = super.validateOrder(l_confirmRequest);

        //1.8 createResponse( )
        WEB3SuccMarginCloseChangeConfirmResponse l_response =
            (WEB3SuccMarginCloseChangeConfirmResponse) l_request.createResponse();
        
        //1.9 (*)プロパティセット
        //（*）レスポンスデータに以下のプロパティをセットする。
        // 確認時発注日：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.checkDate = l_confirmResponse.checkDate;
        
        //概算受渡代金：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.estimatedPrice = l_confirmResponse.estimatedPrice;
        
        //取引終了警告市場コード一覧：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.messageSuspension = l_confirmResponse.messageSuspension; 
        
        //建株明細一覧：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.contractUnits = l_confirmResponse.contractUnits;
        
        //手数料情報：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.commissionInfo = l_confirmResponse.commissionInfo;
        
        //確認時単価：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.checkPrice = l_confirmResponse.checkPrice;
        
        //インサイダー警告表示フラグ：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.insiderWarningFlag = l_confirmResponse.insiderWarningFlag;

        //注文有効期限：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.expirationDate = l_confirmResponse.expirationDate;

        //調整後単価
        if (l_confirmRequest.priceAdjustmentValueInfo != null)
        {
            l_response.afterAdjustmentPrice = l_confirmResponse.afterAdjustmentPrice;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * 信用取引の訂正返済注文を登録する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引訂正返済サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引注文訂正返済完了リクエストオブジェクト
     * @@return WEB3SuccMarginCloseChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 433D001803E1
     */
    protected WEB3SuccMarginCloseChangeCompleteResponse submitOrder(WEB3SuccMarginCloseChangeCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginCloseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 get株式予約注文単位(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 validateリクエストデータat反対取引(WEB3GenRequest, 株式予約注文単位Impl)
        this.validateRequestDataAtReversingTrade(l_request, l_orderUnit);

        //1.4 validate訂正可能状態( )
        l_orderUnit.validateOrderForChangeability();

        //1.5 validate決済済建株(株式予約注文単位Impl)
        this.validateClosedMarginContract(l_orderUnit);

        //1.6 create完了リクエスト(（連続）信用取引注文訂正返済完了リクエスト, 株式予約注文単位Impl)
        WEB3SuccMarginCloseCompleteRequest l_completeRequest = this.createCompleteRequest(l_request, l_orderUnit);
        
        //1.7 submit注文(（連続）信用取引返済注文完了リクエスト)
        WEB3SuccMarginCloseCompleteResponse l_completeResponse = super.submitOrder(l_completeRequest);

        //1.8 createリクエストアダプタ(WEB3GenRequest)
        WEB3MarginCloseMarginRequestAdapter l_adapter = this.createRequestAdapter(l_completeRequest);
        
        //1.9 create決済建株エントリ(信用取引決済建株明細[], 信用取引返済リクエストアダプタ)
        EqTypeSettleContractOrderEntry[] l_contractOrderEntrys = 
            this.createClosingContractEntry(l_request.closeMarginContractUnits, l_adapter);
        
        //1.10 create株式予約返済注文訂正内容(注文ID : long, 決済建株エントリ : EqTypeSettleContractOrderEntry[], 
        //     訂正後株数 : double, 訂正後指値 : double, 訂正後概算受渡代金 : double, 訂正後計算単価 : double, 
        //     訂正後注文失効日 : Date, 訂正後is出来るまで注文 : boolean, 代理入力者 : 扱者, 訂正後単価調整値 : Double)
        double l_dbModifiedOrderQuantity = 0.0D;
        double l_dblModifiedCalcUnitPrice = 0.0D;
        Date l_datModifiedExpirationDate = l_request.checkDate;
        boolean l_blnModifiedIsCarriedOrder = false;
        Double l_dblModifiedPriceAdjustValue = null;

        if (l_request.orderQuantity != null)
        {
            //リクエストデータ．注文株数 != nullの場合、訂正後株数：リクエストデータ．注文株数
            l_dbModifiedOrderQuantity = Double.parseDouble(l_request.orderQuantity);
        }
        else
        {
            //以外、訂正後株数：create決済建株エントリ()の戻り値．株数のSUM値
            for (int i = 0; i < l_contractOrderEntrys.length; i++)
            {
                l_dbModifiedOrderQuantity += l_contractOrderEntrys[i].getQuantity();
            }
        }

        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblModifiedCalcUnitPrice = 0;
        }
        else
        {
            l_dblModifiedCalcUnitPrice = Double.parseDouble(l_request.limitPrice);
        }
        
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_datModifiedExpirationDate = l_request.expirationDate;
            l_blnModifiedIsCarriedOrder = true;
        }
        
        if (null != l_request.priceAdjustmentValueInfo)
        {
            l_dblModifiedPriceAdjustValue = new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }
        
        WEB3ToSuccMarginChangeSettleContractOrderSpec l_orderSpec = 
            WEB3ToSuccMarginChangeSettleContractOrderSpec.createMarginChangeSettleContractOrderSpec(
                l_orderUnit.getOrderId(),
                l_contractOrderEntrys,
                l_dbModifiedOrderQuantity,
                l_dblModifiedCalcUnitPrice,
                Double.parseDouble(l_request.estimatedPrice),
                Double.parseDouble(l_request.checkPrice),
                l_datModifiedExpirationDate,
                l_blnModifiedIsCarriedOrder,
                (WEB3GentradeTrader) this.getTrader(),
                l_dblModifiedPriceAdjustValue);
        
        //1.11 submit株式訂正予約返済注文(補助口座 : SubAccount, 予約返済注文訂正内容 : 株式予約返済注文訂正内容, 
        //     取引パスワード : String, 訂正前予約注文単位 : 株式予約注文単位Impl)
        l_toOrderManager.submitEqtypeChangeSettleContractOrder(
            this.getSubAccount(),
            l_orderSpec,
            l_request.password,
            l_orderUnit);
        
        //1.12 createResponse( )
        WEB3SuccMarginCloseChangeCompleteResponse l_response = 
            (WEB3SuccMarginCloseChangeCompleteResponse) l_request.createResponse();
        
        //1.13 (*)プロパティセット
        //（*）レスポンスデータに以下のプロパティをセットする。
        //  更新時間：　@superクラスのsubmit注文()のレスポンスの同名プロパティをセット
        l_response.lastUpdatedTimestamp = l_completeResponse.lastUpdatedTimestamp;
        
        // 識別番号：　@superクラスのsubmit注文()のレスポンスの同名プロパティをセット
        l_response.orderActionId = l_completeResponse.orderActionId;
        
        // インサイダー警告表示フラグ：　@superクラスのsubmit注文()のレスポンスの同名プロパティをセット
        l_response.insiderWarningFlag = l_completeResponse.insiderWarningFlag;
        
        // 連続注文設定フラグ：　@false（固定）
        l_response.succSettingFlag = false;

        //注文有効期限：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.expirationDate = l_completeResponse.expirationDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create決済建株エントリ)<BR>
     * 決済建株エントリを作成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@連続注文マネージャImpl.create決済建株エントリ()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[create決済建株エントリ()に指定する引数]<BR>
     * 　@　@決済建株明細一覧：　@生成した決済建株明細一覧(*1)<BR>
     * <BR>
     * 　@(*1)以下のプロパティをセットした信用取引決済建株明細インスタンスのみを<BR>
     * 　@　@要素とする配列<BR>
     * <BR>
     * 　@　@　@信用取引決済建株明細.注文株数：　@this.get注文株数()の戻り値を設定する。<BR>
     * <BR>
     * 　@　@　@[get注文株数()に指定する引数] <BR>
     * 　@　@　@リクエストアダプタ：　@パラメータ.リクエストアダプタ <BR>
     * <BR>
     * ２）　@１）以外の場合、<BR>
     * 　@拡張株式注文マネージャ.create決済建株エントリ()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[.create決済建株エントリ()に指定する引数]<BR>
     * 　@　@注文単位ID：　@-1（予約注文未発注の状態を表す。）<BR>
     * 　@　@注文株数：　@パラメータ.リクエストアダプタ.get注文株数()戻り値<BR>
     * 　@　@決済建株明細一覧[]：　@パラメータ.決済建株明細一覧[]<BR>
     * @@param l_closeMarginContractUnits - (決済建株明細一覧)<BR>
     * 信用取引決済建株オブジェクトの配列。<BR>
     * （リクエストデータ）<BR>
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト<BR>
     * @@return EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 433D071F0086
     */
    protected EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createClosingContractEntry(WEB3MarginCloseMarginContractUnit[], " +
            "WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries = null;
                                       
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = 
            (WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;

        //１）　@反対売買の場合
        if (l_toRequestAdaptor.isReversingOrder())
        {
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                   (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();

            //(*1)以下のプロパティをセットした信用取引決済建株明細インスタンスのみを要素とする配列
            WEB3MarginCloseMarginContractUnit l_contractUnit = new WEB3MarginCloseMarginContractUnit();
            l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(getOrderQuantityCnt(l_toRequestAdaptor));

            //連続注文マネージャImpl.create決済建株エントリ()をコールし、戻り値を返却する。
            l_settleContractOrderEntries = l_toOrderManager.createClosingContractEntry(
                new WEB3MarginCloseMarginContractUnit[]{l_contractUnit});
        }
        //２）　@１）以外の場合、拡張株式注文マネージャ.create決済建株エントリ()をコールし、戻り値を返却する。
        else
        {
            //拡張株式注文マネージャ
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

            l_settleContractOrderEntries = l_orderManager.createClosingContractEntry(
                -1, 
                l_toRequestAdaptor.getOrderQuantity(),
                l_closeMarginContractUnits);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntries;
    }
    
    /**
     * (submit返済注文)<BR>
     * 予約注文を登録する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (信用返済注文内容)<BR>
     * 信用返済注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 予約注文の注文ID。
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料オブジェクト。<BR>
     * @@param l_profitAndLossCalcResult - (計算結果)<BR>
     * 概算決済損益代金計算結果オブジェクト。<BR>
     * @@param l_requestAdaptor - (返済リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト<BR>
     * @@roseuid 434254C1038D
     */
    protected void submitSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MarginSettleContractOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        WEB3GentradeCommission l_commission, 
        WEB3EquityRealizedProfitAndLossPrice l_profitAndLossCalcResult, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) 
    {
        
    }
    
    /**
     * (validate連続注文最大設定数)<BR>
     * 連続注文の最大設定数を超過してしまわないかどうかをチェックする。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@roseuid 433D0198023B
     */
    protected void validateSuccOrderMaxQuantity(EqTypeOrderUnit l_parentOrderUnit) 
    {
     
    }
    
    /**
     * (create確認リクエスト)<BR>
     * 引数のリクエストデータ、予約注文単位より<BR>
     * （連続）信用取引返済注文確認リクエストオブジェクトを<BR>
     * 作成する。<BR>
     * <BR>
     * １）　@戻り値のインスタンスを生成する。<BR>
     * <BR>
     * ２）　@共通プロパティセット。<BR>
     * 　@this.set信用取引共通リクエスト()をコールする。<BR>
     * <BR>
     * 　@[set信用取引共通リクエスト()にセットするパラメータ]<BR>
     * 　@　@（output）共通リクエスト：　@１）の戻り値<BR>
     * 　@　@（input）共通リクエスト：　@パラメータ.リクエストデータ<BR>
     * <BR>
     * ３）　@生成したインスタンス特有のプロパティをセットする。<BR>
     * 　@決済順序区分 = 予約注文単位.決済順序区分<BR>
     * 　@決済建株一覧 = パラメータ.リクエストデータ.決済建株一覧<BR>
     * 　@要求元区分 = null<BR>
     * 　@連続注文共通情報 = 予約注文単位.create連続注文共通情報()<BR>
     * 　@単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報<BR>
     * <BR>
     * ４）　@プロパティセットした確認リクエストを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@param l_orderUnit - (予約注文単位)<BR>
     * 株式予約注文単位Implオブジェクト<BR>
     * @@return WEB3SuccMarginCloseConfirmRequest
     * @@roseuid 433D019801AF
     */
    protected WEB3SuccMarginCloseConfirmRequest createConfirmRequest(
        WEB3SuccMarginCloseChangeConfirmRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) 
    {
        final String STR_METHOD_NAME = " createConfirmRequest(" +
            "WEB3SuccMarginCloseChangeConfirmRequest" +
            "WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@戻り値のインスタンスを生成する。
        WEB3SuccMarginCloseConfirmRequest l_confirmRequest = new WEB3SuccMarginCloseConfirmRequest();
        
        //２）　@共通プロパティセット。
        this.setMarginCommonRequest(l_confirmRequest, l_request);
        
        //３）　@生成したインスタンス特有のプロパティをセットする。
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //決済順序区分 = 予約注文単位.決済順序区分
        l_confirmRequest.closingOrder = l_rsvEqOrderUnitRow.getClosingOrderType();
        
        //決済建株一覧 = パラメータ.リクエストデータ.決済建株一覧
        l_confirmRequest.closeMarginContractUnits = l_request.closeMarginContractUnits;
        
        //要求元区分 = null
        l_confirmRequest.requestFromType = null;
        
        //連続注文共通情報 = 予約注文単位.create連続注文共通情報()
        l_confirmRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        //単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報
        l_confirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_confirmRequest;
    }
    
    /**
     * (create完了リクエスト)<BR>
     * 引数のリクエストデータ、予約注文単位より<BR>
     * （連続）信用取引返済注文完了リクエストオブジェクトを<BR>
     * 作成する。<BR>
     * <BR>
     * １）　@戻り値のインスタンスを生成する。<BR>
     * <BR>
     * ２）　@共通プロパティセット。<BR>
     * 　@this.set信用取引共通リクエスト()をコールする。<BR>
     * <BR>
     * 　@[set信用取引共通リクエスト()にセットするパラメータ]<BR>
     * 　@　@（output）共通リクエスト：　@１）の戻り値<BR>
     * 　@　@（input）共通リクエスト：　@パラメータ.リクエストデータ<BR>
     * <BR>
     * ３）　@生成したインスタンス特有のプロパティをセットする。<BR>
     * 　@注文ID = 予約注文単位.注文ID<BR>
     * 　@決済順序区分 = 予約注文単位.決済順序区分<BR>
     * 　@決済建株一覧 = パラメータ.リクエストデータ.決済建株一覧<BR>
     * 　@確認時単価 = パラメータ.リクエストデータ.確認時単価<BR>
     * 　@確認時発注日 = パラメータ.リクエストデータ.確認時発注日<BR>
     * 　@暗証番号 = パラメータ.リクエストデータ.暗証番号<BR>
     * 　@連続注文共通情報 = 予約注文単位.create連続注文共通情報()<BR>
     * 　@単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報<BR>
     * 　@調整後単価 = パラメータ.リクエストデータ.調整後単価<BR>
     * <BR>
     * ４）　@プロパティセットした完了リクエストを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@param l_orderUnit - (予約注文単位)<BR>
     * 株式予約注文単位Implオブジェクト<BR>
     * @@return WEB3SuccMarginCloseCompleteRequest
     * @@roseuid 433D019801CE
     */
    protected WEB3SuccMarginCloseCompleteRequest createCompleteRequest(
        WEB3SuccMarginCloseChangeCompleteRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) 
    {
        final String STR_METHOD_NAME = " createCompleteRequest(" +
            "WEB3SuccMarginCloseChangeCompleteRequest, " +
            "WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@戻り値のインスタンスを生成する。
        WEB3SuccMarginCloseCompleteRequest l_completeRequest = new WEB3SuccMarginCloseCompleteRequest();
        
        //２）　@共通プロパティセット。
        this.setMarginCommonRequest(l_completeRequest, l_request);
        
        //３）　@生成したインスタンス特有のプロパティをセットする。
        //注文ID = 予約注文単位.注文ID
        l_completeRequest.orderId = String.valueOf(l_orderUnit.getOrderId());

        //決済順序区分 = 予約注文単位.決済順序区分
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_orderUnit.getDataSourceObject();
        l_completeRequest.closingOrder = l_rsvEqOrderUnitRow.getClosingOrderType();
        
        //決済建株一覧 = パラメータ.リクエストデータ.決済建株一覧
        l_completeRequest.closeMarginContractUnits = l_request.closeMarginContractUnits;
        
        //確認時単価 = パラメータ.リクエストデータ.確認時単価
        l_completeRequest.checkPrice = l_request.checkPrice;
        
        //確認時発注日 = パラメータ.リクエストデータ.確認時発注日
        l_completeRequest.checkDate = l_request.checkDate;
        
        //暗証番号 = パラメータ.リクエストデータ.暗証番号
        l_completeRequest.password = l_request.password;
        
        //連続注文共通情報 = 予約注文単位.create連続注文共通情報()
        l_completeRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        //単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報
        l_completeRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        //調整後単価 = パラメータ.リクエストデータ.調整後単価
        l_completeRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;

        log.exiting(STR_METHOD_NAME);
        return l_completeRequest;
    }
    
    /**
     * (set信用取引共通リクエスト)<BR>
     * 指定された「（output）共通リクエスト」のインスタンスに、プロパティをセットする。<BR>
     * <BR>
     * 以下のプロパティに、「（input）共通リクエスト」の同名プロパティの値をセットする。<BR>
     * <BR>
     * [対象プロパティ]<BR>
     * 　@注文株数<BR>
     * 　@注文単価区分<BR>
     * 　@注文単価<BR>
     * 　@値段条件<BR>
     * 　@執行条件<BR>
     * 　@注文期限区分<BR>
     * 　@注文有効期限<BR>
     * 　@発注条件区分<BR>
     * 　@逆指値用発注条件単価<BR>
     * 　@逆指値用発注条件演算子<BR>
     * 　@W指値用発注条件単価<BR>
     * 　@W指値用発注条件演算子<BR>
     * 　@W指値用注文単価区分<BR>
     * 　@W指値用注文単価<BR>
     * @@param l_outputCommonRequest - (（output）共通リクエスト)<BR>
     * 信用取引共通リクエストオブジェクト<BR>
     * @@param l_inputCommonRequest - (（input）共通リクエスト)<BR>
     * 信用取引共通リクエストオブジェクト<BR>
     * @@return WEB3MarginCommonRequest
     * @@roseuid 433D019801ED
     */
    protected WEB3MarginCommonRequest setMarginCommonRequest(
        WEB3MarginCommonRequest l_outputCommonRequest, 
        WEB3MarginCommonRequest l_inputCommonRequest) 
    {
        final String STR_METHOD_NAME = " setMarginCommonRequest(WEB3MarginCommonRequest, WEB3MarginCommonRequest)";
        log.entering(STR_METHOD_NAME);
        
        //指定された「（output）共通リクエスト」のインスタンスに、プロパティをセットする。
        //以下のプロパティに、「（input）共通リクエスト」の同名プロパティの値をセットする。
        //注文株数
        l_outputCommonRequest.orderQuantity = l_inputCommonRequest.orderQuantity;
        
        //注文単価区分
        l_outputCommonRequest.orderPriceDiv = l_inputCommonRequest.orderPriceDiv;
        
        //注文単価
        l_outputCommonRequest.limitPrice = l_inputCommonRequest.limitPrice;
        
        //値段条件
        l_outputCommonRequest.priceCondType = l_inputCommonRequest.priceCondType;
        
        //執行条件
        l_outputCommonRequest.execCondType = l_inputCommonRequest.execCondType;
        
        //注文期限区分
        l_outputCommonRequest.expirationDateType = l_inputCommonRequest.expirationDateType;

        //注文有効期限
        l_outputCommonRequest.expirationDate = l_inputCommonRequest.expirationDate;
        
        //発注条件区分
        l_outputCommonRequest.orderCondType = l_inputCommonRequest.orderCondType;
        
        //逆指値用発注条件単価
        l_outputCommonRequest.stopOrderCondPrice = l_inputCommonRequest.stopOrderCondPrice;
        
        //逆指値用発注条件演算子
        l_outputCommonRequest.stopOrderCondOperator = l_inputCommonRequest.stopOrderCondOperator;
        
        //W指値用発注条件単価
        l_outputCommonRequest.wlimitOrderCondPrice = l_inputCommonRequest.wlimitOrderCondPrice;
        
        //W指値用発注条件演算子
        l_outputCommonRequest.wlimitOrderCondOperator = l_inputCommonRequest.wlimitOrderCondOperator;
        
        //W指値用注文単価区分
        l_outputCommonRequest.wLimitOrderPriceDiv = l_inputCommonRequest.wLimitOrderPriceDiv;
        
        //W指値用注文単価
        l_outputCommonRequest.wLimitPrice = l_inputCommonRequest.wLimitPrice;
        
        log.exiting(STR_METHOD_NAME);
        return l_outputCommonRequest;
    }
    
    /**
     * (validate決済済建株)<BR>
     * 予約決済注文の対象となっている建株が<BR>
     * 別注文により決済済となっているかどうかチェックする。<BR>
     * <BR>
     * １）　@連続注文マネージャImpl.create建株明細ByOrder()<BR>
     * 　@をコールする。<BR>
     * <BR>
     * 　@[create建株明細ByOrder()にセットするパラメータ]<BR>
     * 　@　@予約注文単位：　@パラメータ.予約注文単位<BR>
     * <BR>
     * 　@nullが返却された場合、<BR>
     * 　@「予約決済対象建株は別注文により決済済」<BR>
     * 　@の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02289<BR>
     * @@param l_rsvEqOrderUnit - (予約注文単位)<BR>
     * 株式予約注文単位オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434E227D0113
     */
    protected void validateClosedMarginContract(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateClosedMarginContract(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //１）　@連続注文マネージャImpl.create建株明細ByOrder()をコールする。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();

        WEB3MarginContractUnit[] l_contractUnits = l_toOrderManager.createContractUnitByOrder(l_rsvEqOrderUnit);
        
        //nullが返却された場合、「予約決済対象建株は別注文により決済済」の例外をスローする。
        if (l_contractUnits == null)
        {
            log.debug("予約決済対象建株は別注文により決済済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02289,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "予約決済対象建株は別注文により決済済です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify予約注文登録)<BR>
     * 予約注文の登録をルールエンジンサーバに通知する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_lngSubOrderId - (子注文の注文ID)<BR>
     * 子注文の注文ID。<BR>
     * @@roseuid 435EE218023C
     */
    protected void notifyRsvOrderRegister(long l_lngSubOrderId) 
    {
        
    }
    
    /**
     * (validateリクエストデータat反対取引)<BR>
     * 反対取引指定時に固有の、リクエストデータのプロパティチェックを行う。<BR>
     * <BR>
     * １）　@パラメータ.予約注文単位.is反対売買取引()==false<BR>
     * 　@　@　@（＝反対取引でない）の場合は、<BR>
     * 　@　@　@リクエストデータ.validateAT既存残取引()をコールし、<BR>
     * 　@　@　@returnする。<BR>
     * <BR>
     * ２）　@リクエストデータ.validateAT反対取引()をコールする。<BR>
     * <BR>
     * ３）　@パラメータ.予約注文単位.決済順序区分 != nul　@かつ<BR>
     * 　@パラメータ.予約注文単位.決済順序区分 == "ランダム"の<BR>
     * 　@場合、以下の処理を実施する。<BR>
     * 　@３－１）　@リクエストデータ.決済建株一覧の要素数分、<BR>
     * 　@　@以下の処理を繰り返す。<BR>
     * 　@　@３－１－１）　@決済建株明細.注文株数 が以下のいずれかの場合は、<BR>
     * 　@　@　@　@　@「決済明細の注文株数指定が不正」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・null <BR>
     * 　@　@　@　@　@　@・数字以外 <BR>
     * 　@　@　@　@　@　@・０以下の数字 <BR>
     * 　@　@　@　@　@　@・８桁を超える数字 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02285<BR>
     * <BR>
     * 　@　@※引数のリクエストデータは、以下のいずれかにキャストすること。<BR>
     * 　@　@　@・（連続）信用取引注文訂正返済確認リクエスト<BR>
     * 　@　@　@・（連続）信用取引注文訂正返済完了リクエスト<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@param l_rsvEqOrderUnit - (予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4361EDE40319
     */
    protected void validateRequestDataAtReversingTrade(
        WEB3GenRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRequestDataAtReversingTrade(" +
            "WEB3GenRequest, WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null || l_rsvEqOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        WEB3SuccMarginCloseChangeConfirmRequest l_confirmRequest = null;
        WEB3SuccMarginCloseChangeCompleteRequest l_completeRequest = null;
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits = null;

        if (l_request instanceof WEB3SuccMarginCloseChangeConfirmRequest)
        {
            //引数のリクエストデータを(連続)信用取引注文訂正返済確認リクエストにキャスト
            l_confirmRequest = (WEB3SuccMarginCloseChangeConfirmRequest) l_request;
            l_closeMarginContractUnits = l_confirmRequest.closeMarginContractUnits;
        }
        else if (l_request instanceof WEB3SuccMarginCloseChangeCompleteRequest)
        {
            //引数のリクエストデータを(連続)信用取引注文訂正返済完了リクエストにキャスト
           l_completeRequest = (WEB3SuccMarginCloseChangeCompleteRequest) l_request;
           l_closeMarginContractUnits = l_completeRequest.closeMarginContractUnits;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        //１）　@パラメータ.予約注文単位.is反対売買取引()==false（＝反対取引でない）の場合は、
        //リクエストデータ.validateAT既存残取引()をコールし、returnする。
        if (!l_rsvEqOrderUnit.isReversingTrade())
        {
            if (l_confirmRequest != null)
            {
                l_confirmRequest.validateATExistingRemainderTrading();
            }
            else if (l_completeRequest != null)
            {
                l_completeRequest.validateATExistingRemainderTrading();
            }
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //２）　@リクエストデータ.validateAT反対取引()をコールする。
        if (l_confirmRequest != null)
        {
            l_confirmRequest.validateAtReverseOrder();
        }
        else if (l_completeRequest != null)
        {
            l_completeRequest.validateAtReverseOrder();
        }
        
        //３）　@パラメータ.予約注文単位.決済順序区分 != null　@かつパラメータ.予約注文単位.決済順序区分 == "ランダム"の
        //      場合、以下の処理を実施する。
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_rsvEqOrderUnit.getDataSourceObject();
        if (null != l_rsvEqOrderUnitRow.getClosingOrderType() && 
            WEB3ClosingOrderDef.RANDOM.equals(l_rsvEqOrderUnitRow.getClosingOrderType()))
        {
            //３－１）　@リクエストデータ.決済建株一覧の要素数分、以下の処理を繰り返す。
            int l_intLength = l_closeMarginContractUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                //３－１－１）　@決済建株明細.注文株数 が以下のいずれかの場合は、
                //             「決済明細の注文株数指定が不正」の例外をスローする。
                if (null == l_closeMarginContractUnits[i].orderQuantity ||
                    !WEB3StringTypeUtility.isInteger(l_closeMarginContractUnits[i].orderQuantity) ||
                    Long.parseLong(l_closeMarginContractUnits[i].orderQuantity) <= 0 ||
                    l_closeMarginContractUnits[i].orderQuantity.length() > 8)
                {
                    log.debug("決済明細の注文株数指定が不正。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02285,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "決済明細の注文株数指定が不正。");
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * get注文株数<BR>
     * <BR>
     * 信用取引建株明細に設定する注文株数を返却する。 <BR>
     * <BR>
     * １）　@注文株数を求める。<BR>
     * 　@　@取得した決済順序区分≠"ランダム"の場合は、 <BR>
     * 　@　@　@　@リクエストアダプタ.リクエストデータ.注文株数 を使用。 <BR>
     * 　@　@取得した決済順序区分=="ランダム"の場合は、 <BR>
     * 　@　@　@　@リクエストアダプタ.リクエストデータ.決済建株一覧の全要素の注文株数のSUM値を使用。<BR> 
     * <BR>
     * ２）　@１）で求めた注文株数 > リクエストアダプタ.親注文の注文単位.注文数量の場合、 <BR>
     * 　@　@「注文株数が親注文の注文数量を超過」の例外をスローする。 <BR>
     * 　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@tag: BUSINESS_ERROR_02290<BR>
     * <BR>
     * ３）　@２）の条件に該当しない場合、１）で求めた注文株数を返却する。<BR>
     * @@param l_toRequestAdapter - (リクエストアダプタ)<BR>
     * （連続）信用取引返済リクエストアダプタオブジェクト。<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    private double getOrderQuantityCnt(
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderQuantityCnt(WEB3ToSuccMarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblOrderCnt = 0;
        WEB3GenRequest l_request = l_toRequestAdapter.request;
        
        String l_strClosingOrder = null;
        String l_strOrderQuantity = null;
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits = null;
        
        if (l_request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginCloseConfirmRequest) l_request;
            l_strClosingOrder = l_confirmRequest.closingOrder;
            l_strOrderQuantity = l_confirmRequest.orderQuantity;
            l_closeMarginContractUnits = l_confirmRequest.closeMarginContractUnits;

        }
        else if (l_request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = 
                (WEB3SuccMarginCloseCompleteRequest) l_request;
            l_strClosingOrder = l_completeRequest.closingOrder;
            l_strOrderQuantity = l_completeRequest.orderQuantity;
            l_closeMarginContractUnits = l_completeRequest.closeMarginContractUnits;

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
        
        if (!WEB3ClosingOrderDef.RANDOM.equals(l_strClosingOrder))
        {
            l_dblOrderCnt = Double.parseDouble(l_strOrderQuantity);
        }
        else
        {
            //リクエストアダプタ.リクエストデータ.決済順序区分=="ランダム"の場合は、
            //　@　@リクエストアダプタ.リクエストデータ.決済建株一覧の全要素の注文株数のSUM値を使用。
            int l_intUnitLength = l_closeMarginContractUnits.length;
            for (int i = 0; i < l_intUnitLength; i++)
            {
                l_dblOrderCnt += 
                    Double.parseDouble(l_closeMarginContractUnits[i].orderQuantity);
            }
        }
        
        //※求めた注文株数 > リクエストアダプタ.親注文の注文単位.注文数量の場合、
        //　@「注文株数が親注文の注文数量を超過」の例外をスローする。
        if (l_dblOrderCnt > l_toRequestAdapter.parentOrderUnit.getQuantity())
        {
            log.debug("注文株数が親注文の注文数量を超過しています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02290,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文株数が親注文の注文数量を超過しています。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblOrderCnt;
    }
}
@
