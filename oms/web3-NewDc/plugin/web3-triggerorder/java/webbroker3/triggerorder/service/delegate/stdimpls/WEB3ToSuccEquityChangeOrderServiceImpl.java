head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株式注文訂正サービスImpl(WEB3ToSuccEquityChangeOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 鄭海良(中訊) 新規作成
Revesion History : 2006/08/30 柴雙紅(中訊) 仕様変更モデル165
Revesion History : 2007/01/11 徐宏偉(中訊) モデル216
Revesion History : 2007/01/19 肖志偉(中訊) モデル225
Revesion History : 2007/08/20 武波(中訊) モデル242
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityCommonRequest;
import webbroker3.equity.message.WEB3EquityConfirmCommonResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccEqtypeChangeOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）株式注文訂正サービスImpl)<BR>
 * （連続）株式注文訂正サービスの実装クラス。<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccEquityChangeOrderServiceImpl extends WEB3ToSuccEquityOrderServiceImpl 
    implements WEB3ToSuccEquityChangeOrderService 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityChangeOrderServiceImpl.class);
    
    /**
     * @@roseuid 436ACF720242
     */
    public WEB3ToSuccEquityChangeOrderServiceImpl() 
    {
     
    }
    
    /**
     * (validate注文訂正)<BR>
     * （連続）現物株式注文訂正確認処理を実施する。<BR>
     * <BR>
     * シーケンス図「（（連続）株式注文訂正サービス）validate注文訂正」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * 
     * @@return WEB3EquityChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4338D6E10317
     */
    protected WEB3SuccEquityChangeConfirmResponse validateChangeOrder(WEB3SuccEquityChangeConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateChangeOrder(WEB3SuccEquityChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }

        //1.1 validate( )
        l_request.validate();  //WEB3BusinessLayerException
        
        //1.2 get株式予約注文単位(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);// NotFoundException, WEB3BaseException 
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "テーブルに該当するデータがありません。「注文ID:" + l_lngOrderId + "」";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        
        //1.3 validate訂正可能状態( )
        l_orderUnit.validateOrderForChangeability();
        
        //1.4 is買注文( )
        boolean l_blnIsBuyOrder = l_orderUnit.isBuyOrder();
        
        WEB3EquityCommonRequest l_commonRequest = null;
        if (l_blnIsBuyOrder)
        {
            //1.5 create買付注文確認リクエスト(（連続）現物株式注文訂正確認リクエスト, 株式予約注文単位Impl)
            l_commonRequest = this.createBuyConfirmRequest(l_request, l_orderUnit);
        }
        else
        {
            //1.6 create売付注文確認リクエスト(（連続）現物株式注文訂正確認リクエスト, 株式予約注文単位Impl)
            l_commonRequest = this.createSellConfirmRequest(l_request, l_orderUnit);
        }
        
        //1.7 validate注文(WEB3GenRequest)
        WEB3EquityConfirmCommonResponse l_confirmCommonResponse = 
            (WEB3EquityConfirmCommonResponse)this.validateOrder(l_commonRequest); //WEB3BaseException
        
        //1.8 get取引銘柄( )
        WEB3EquityTradedProduct l_tradedProduct = 
            (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
        
        //1.9 validate訂正時取引余力(補助口座, double, 取引銘柄, 株式予約注文単位Impl)
        this.validateChangeTradingPower(
            this.getSubAccount(),
            Double.parseDouble(l_confirmCommonResponse.estimatedPrice),
            l_tradedProduct,
            l_orderUnit);
            
        //1.10 createResponse( )
        WEB3SuccEquityChangeConfirmResponse l_response = (WEB3SuccEquityChangeConfirmResponse)l_request.createResponse();
        
        //1.11 （(*) プロパティセット）
        //確認時発注日：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.checkDate = l_confirmCommonResponse.checkDate;

        //概算受渡代金：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.estimatedPrice = l_confirmCommonResponse.estimatedPrice;
        
        //取引終了警告市場コード一覧：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
        l_response.messageSuspension = l_confirmCommonResponse.messageSuspension;
        
        //内出来株数：　@nullをセット
        l_response.partContQuantity = null;
        
        if (l_confirmCommonResponse instanceof WEB3SuccEquityBuyConfirmResponse)
        {
            WEB3SuccEquityBuyConfirmResponse l_buyConfirmResponse = 
                (WEB3SuccEquityBuyConfirmResponse)l_confirmCommonResponse; 

            //手数料情報：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            l_response.commissionInfo = l_buyConfirmResponse.commissionInfo;

            //確認時単価：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            l_response.checkPrice = l_buyConfirmResponse.checkPrice;

            //注文有効期限：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            l_response.expirationDate = l_buyConfirmResponse.expirationDate;

            //    調整後単価：　@
            //   　@リクエスト.単価調整値情報≠null（±指値指定）の場合
            //   　@　@　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            //   　@上記以外の場合
            //   　@　@　@nullをセット
            WEB3SuccEquityBuyConfirmRequest l_equityBuyConfirmRequest =
                (WEB3SuccEquityBuyConfirmRequest)l_commonRequest;
            if (l_equityBuyConfirmRequest.priceAdjustmentValueInfo != null)
            {
                l_response.afterAdjustmentPrice = l_buyConfirmResponse.afterAdjustmentPrice;
            }

            //インサイダー警告表示フラグ：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            l_response.insiderWarningFlag = l_buyConfirmResponse.insiderWarningFlag;
        }
        else if (l_confirmCommonResponse instanceof WEB3SuccEquitySellConfirmResponse)
        {
            WEB3SuccEquitySellConfirmResponse l_sellConfirmResponse = 
                (WEB3SuccEquitySellConfirmResponse)l_confirmCommonResponse; 

            //概算簿価単価(*1)：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            if (!l_blnIsBuyOrder && !l_orderUnit.isReversingTrade()) //WEB3BaseException
            {
                l_response.estimatedBookPrice = l_sellConfirmResponse.estimatedBookPrice;
            }

            //手数料情報：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            l_response.commissionInfo = l_sellConfirmResponse.commissionInfo;

            //確認時単価：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            l_response.checkPrice = l_sellConfirmResponse.checkPrice;

            //注文有効期限：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            l_response.expirationDate = l_sellConfirmResponse.expirationDate;

            //    調整後単価：　@
            //   　@リクエスト.単価調整値情報≠null（±指値指定）の場合
            //   　@　@　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            //   　@上記以外の場合
            //   　@　@　@nullをセット
            WEB3SuccEquitySellConfirmRequest l_equitySellConfirmRequest =
                (WEB3SuccEquitySellConfirmRequest)l_commonRequest;
            if (l_equitySellConfirmRequest.priceAdjustmentValueInfo != null)
            {
                l_response.afterAdjustmentPrice = l_sellConfirmResponse.afterAdjustmentPrice;
            }

            //インサイダー警告表示フラグ：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            l_response.insiderWarningFlag = l_sellConfirmResponse.insiderWarningFlag;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文訂正)<BR>
     * （連続）現物株式注文訂正完了処理を実施する。<BR>
     * <BR>
     * シーケンス図「（（連続）株式注文訂正サービス）submit注文訂正」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * 
     * @@return WEB3EquityChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4338D7A50078
     */
    protected WEB3SuccEquityChangeCompleteResponse submitChangeOrder(WEB3SuccEquityChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitChangeOrder(WEB3SuccEquityChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        //1.1 validate( )
        l_request.validate();  //WEB3BusinessLayerException

        //1.2 get株式予約注文単位(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);// NotFoundException, WEB3BaseException 
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "テーブルに該当するデータがありません。「注文ID:" + l_lngOrderId + "」";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }

        //1.3 validate訂正可能状態( )
        l_orderUnit.validateOrderForChangeability();

        //1.4 is買注文( )
        boolean l_blnIsBuyOrder = l_orderUnit.isBuyOrder();

        WEB3EquityCommonRequest l_commonRequest = null;
        if (l_blnIsBuyOrder)
        {
            //1.5 create買付注文完了リクエスト(（連続）現物株式注文訂正完了リクエスト, 株式予約注文単位Impl)
            l_commonRequest = this.createBuyCompleteRequest(l_request, l_orderUnit);
        }
        else
        {
            //1.6 create売付注文完了リクエスト(（連続）現物株式注文訂正完了リクエスト, 株式予約注文単位Impl)
            l_commonRequest = this.createSellCompleteRequest(l_request, l_orderUnit);
        }
        
        //1.7 submit注文(WEB3GenRequest)
        WEB3GenResponse l_submitOrderResponse = 
            this.submitOrder(l_commonRequest); //WEB3BaseException

        //1.8 get取引銘柄( )
        WEB3EquityTradedProduct l_tradedProduct = 
            (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();

        //1.9 validate訂正時取引余力(補助口座, double, 取引銘柄, 株式予約注文単位Impl)
        this.validateChangeTradingPower(
            this.getSubAccount(),
            Double.parseDouble(l_request.estimatedPrice),
            l_tradedProduct,
            l_orderUnit);
            
        //1.10 get代理入力者( )
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader(); //WEB3SystemLayerException
        
        //1.12 create株式予約注文訂正内容(long, double, double, double, double, Date, boolean, 扱者, Double)
        double l_dblOrderPrice = 0;
        if(!WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblOrderPrice = Double.parseDouble(l_request.limitPrice);
        }

        // is出来るまで注文単位( )
        boolean l_blnCarried = false;
        Date l_date = l_request.checkDate;
        if(!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_date = l_request.expirationDate;
            l_blnCarried = true;
        }
        
        Double l_afterAdjust = null;
        if (l_request.priceAdjustmentValueInfo != null)
        {
            l_afterAdjust = new Double(
                l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
        }
         
        WEB3ToSuccEqtypeChangeOrderSpec l_spec = WEB3ToSuccEqtypeChangeOrderSpec.createEqtypeChangeOrderSpec(
            l_orderUnit.getOrderId(),
            Double.parseDouble(l_request.orderQuantity),
            l_dblOrderPrice, 
            Double.parseDouble(l_request.estimatedPrice),
            Double.parseDouble(l_request.checkPrice),
            l_date,
            l_blnCarried,
            l_trader,
            l_afterAdjust);
            
        //1.13 submit株式訂正予約注文(SubAccount, 株式予約注文訂正内容, String, 株式予約注文単位Impl)
        l_toOrderManager.submitEqtypeChangeOrder(
            this.getSubAccount(),
            l_spec,
            l_request.password,
            l_orderUnit);
            
        //1.14 createResponse( )
        WEB3SuccEquityChangeCompleteResponse l_response = 
            (WEB3SuccEquityChangeCompleteResponse)l_request.createResponse();

        //1.15 （(*) プロパティセット）
        if (l_submitOrderResponse instanceof WEB3SuccEquityBuyCompleteResponse)
        {
            WEB3SuccEquityBuyCompleteResponse l_buyCompleteResponse = 
                (WEB3SuccEquityBuyCompleteResponse)l_submitOrderResponse;
                
            //更新時間：　@superクラスのsubmit注文()のレスポンスの同名プロパティをセット
            l_response.lastUpdatedTimestamp = l_buyCompleteResponse.lastUpdatedTimestamp;
            
            //識別番号：　@superクラスのsubmit注文()のレスポンスの同名プロパティをセット
            l_response.orderActionId = l_buyCompleteResponse.orderActionId;
            
            //インサイダー警告表示フラグ：　@superクラスのsubmit注文()のレスポンスの同名プロパティをセット
            l_response.insiderWarningFlag = l_buyCompleteResponse.insiderWarningFlag;

            //注文有効期限：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            l_response.expirationDate = l_buyCompleteResponse.expirationDate;
        }
        if (l_submitOrderResponse instanceof WEB3SuccEquitySellCompleteResponse)
        {
            WEB3SuccEquitySellCompleteResponse l_sellCompleteResponse = 
                (WEB3SuccEquitySellCompleteResponse)l_submitOrderResponse;
                
            //更新時間：　@superクラスのsubmit注文()のレスポンスの同名プロパティをセット
            l_response.lastUpdatedTimestamp = l_sellCompleteResponse.lastUpdatedTimestamp;
            
            //識別番号：　@superクラスのsubmit注文()のレスポンスの同名プロパティをセット
            l_response.orderActionId = l_sellCompleteResponse.orderActionId;
            
            //インサイダー警告表示フラグ：　@superクラスのsubmit注文()のレスポンスの同名プロパティをセット
            l_response.insiderWarningFlag = l_sellCompleteResponse.insiderWarningFlag;

            //注文有効期限：　@superクラスのvalidate注文()のレスポンスの同名プロパティをセット
            l_response.expirationDate = l_sellCompleteResponse.expirationDate;
        }

        //連続注文設定フラグ：　@false（固定）
        l_response.succSettingFlag = false;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * （連続）株式注文訂正処理を実行する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文訂正()またはsubmit注文訂正()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4338D6E10346
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccEquityChangeConfirmRequest)
        {
            //validate注文訂正)()
            l_response = 
                this.validateChangeOrder((WEB3SuccEquityChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SuccEquityChangeCompleteRequest)
        {
            //submit注文訂正()()
            l_response = 
                this.submitChangeOrder((WEB3SuccEquityChangeCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create買付注文確認リクエスト)<BR>
     * 連続注文の、買付注文確認リクエストを作成する。<BR>
     * <BR>
     * １）　@戻り値インスタンスを生成する。<BR>
     * <BR>
     * ２）　@共通リクエストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@this.set現物株式共通リクエスト(生成した戻り値インスタンス, 引数のリクエストデータ)を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * ３）　@当リクエスト固有のプロパティをセットする。<BR>
     * <BR>
     * 　@　@＜現物株式買付注文確認リクエスト＞<BR>
     * 　@　@銘柄コード：　@引数の訂正対象注文単位.get銘柄().銘柄コード<BR>
     * 　@　@市場コード：　@引数の訂正対象注文単位.get市場().市場コード<BR>
     * 　@　@口座区分：　@株式データアダプタ.get口座区分(引数の訂正対象注文単位.getTaxType)<BR>
     * 　@　@取引区分：　@"現物買付注文"<BR>
     * <BR>
     * 　@　@＜（連続）現物株式買付注文確認リクエスト＞<BR>
     * 　@　@連続注文共通情報：　@引数の訂正対象注文単位.create連続注文共通情報()<BR>
     * 　@　@単価調整値情報：　@引数のリクエストデータの同名プロパティ<BR>
     * <BR>
     * ４）　@戻り値インスタンスを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * 
     * @@param l_orderUnit - (訂正対象注文単位)<BR>
     * 訂正対象注文単位。<BR>
     * @@return WEB3SuccEquityBuyConfirmRequest
     * @@throws WEB3BaseException
     * @@roseuid 433B543603D9
     */
    protected WEB3SuccEquityBuyConfirmRequest createBuyConfirmRequest(
        WEB3SuccEquityChangeConfirmRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createBuyConfirmRequest(" +
            "WEB3SuccEquityChangeConfirmRequest" +
            ", WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "訂正対象注文単位が未指定(null)です。");
        }

        //１）　@戻り値インスタンスを生成する。
        WEB3SuccEquityBuyConfirmRequest l_succEquityBuyConfirmRequest = 
            new WEB3SuccEquityBuyConfirmRequest();

        //２）　@共通リクエストのプロパティをセットする。
        //　@　@this.set現物株式共通リクエスト(生成した戻り値インスタンス, 引数のリクエストデータ)を
        //　@　@コールする。
        this.setEquityCommonRequest(l_succEquityBuyConfirmRequest, l_request);

        //３）　@当リクエスト固有のプロパティをセットする。
        //　@　@＜現物株式買付注文確認リクエスト＞
        //　@　@銘柄コード：　@引数の訂正対象注文単位.get銘柄().銘柄コード
        EqTypeProduct l_eqtypeProduct = (EqTypeProduct)l_orderUnit.getProduct();
        if (l_eqtypeProduct == null)
        {
            String l_strMessage = "テーブルに該当するデータがありません。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        l_succEquityBuyConfirmRequest.productCode = l_eqtypeProduct.getProductCode();
        
        //　@　@市場コード：　@引数の訂正対象注文単位.get市場().市場コード
        Market l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();  //NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "テーブルに該当するデータがありません。";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        if (l_market != null)
        {
            l_succEquityBuyConfirmRequest.marketCode = l_market.getMarketCode();
        }
        
        //口座区分：　@株式データアダプタ.get口座区分(引数の訂正対象注文単位.getTaxType)
        l_succEquityBuyConfirmRequest.taxType = 
            WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
        
        //　@　@取引区分：　@"現物買付注文"
        l_succEquityBuyConfirmRequest.tradingType = WEB3TradingTypeDef.BUY_ORDER;

        //　@　@＜（連続）現物株式買付注文確認リクエスト＞
        //　@　@連続注文共通情報：　@引数の訂正対象注文単位.create連続注文共通情報()
        l_succEquityBuyConfirmRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        //　@　@単価調整値情報：　@引数のリクエストデータの同名プロパティ 
        l_succEquityBuyConfirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        //４）　@戻り値インスタンスを返却する。

        log.exiting(STR_METHOD_NAME);
        return l_succEquityBuyConfirmRequest;
    }
    
    /**
     * (create売付注文確認リクエスト)<BR>
     * 連続注文の、売付注文確認リクエストを作成する。<BR>
     * <BR>
     * １）　@戻り値インスタンスを生成する。<BR>
     * <BR>
     * ２）　@共通リクエストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@this.set現物株式共通リクエスト(生成した戻り値インスタンス, 引数のリクエストデータ)を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * ３）　@当リクエスト固有のプロパティをセットする。<BR>
     * <BR>
     * 　@　@＜現物株式売付注文確認リクエスト＞<BR>
     * 　@　@ID（＝資産ID）：　@反対取引(*1)の場合は、nullをセット。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@反対取引でない場合は、株式ポジションマネージャ.get保有資産(<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@口座ID, 補助口座ID, 銘柄ID, 税区分)で取得した<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@保有資産.資産IDをセット。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@※get保有資産()の引数は、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@引数の訂正対象注文単位の同名プロパティをセット。<BR>
     * 　@　@市場コード：　@引数の訂正対象注文単位.get市場().市場コード<BR>
     * <BR>
     * 　@　@(*1)反対取引かどうかの判定<BR>
     * 　@　@　@引数の訂正対象注文単位.is反対売買取引()==trueの場合は、反対取引である。<BR>
     * 　@　@　@以外、反対取引でない。<BR>
     * <BR>
     * 　@　@＜（連続）現物株式売付注文確認リクエスト＞<BR>
     * 　@　@連続注文共通情報：　@引数の訂正対象注文単位.create連続注文共通情報()<BR>
     * 　@　@単価調整値情報：　@引数のリクエストデータの同名プロパティ<BR>
     * <BR>
     * ４）　@戻り値インスタンスを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * 
     * @@param l_orderUnit - (訂正対象注文単位)<BR>
     * 訂正対象注文単位。<BR>
     * @@return WEB3SuccEquitySellConfirmRequest
     * @@throws WEB3BaseException
     * @@roseuid 433BB093020C
     */
    protected WEB3SuccEquitySellConfirmRequest createSellConfirmRequest(
        WEB3SuccEquityChangeConfirmRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSellConfirmRequest(" +
            "WEB3SuccEquityChangeConfirmRequest" +
            ", WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "訂正対象注文単位が未指定(null)です。");
        }

        // １）　@戻り値インスタンスを生成する。
        WEB3SuccEquitySellConfirmRequest l_succEquitySellConfirmRequest =
            new WEB3SuccEquitySellConfirmRequest();

        // ２）　@共通リクエストのプロパティをセットする。
        // 　@　@this.set現物株式共通リクエスト(生成した戻り値インスタンス, 引数のリクエストデータ)を
        // 　@　@コールする。
        this.setEquityCommonRequest(l_succEquitySellConfirmRequest, l_request);

        // ３）　@当リクエスト固有のプロパティをセットする。
        // 　@　@＜現物株式売付注文確認リクエスト＞
        // 　@　@ID（＝資産ID）：　@反対取引(*1)の場合は、nullをセット。
        //   反対取引でない場合は、株式ポジションマネージャ.get保有資産(
        //   口座ID, 補助口座ID, 銘柄ID, 税区分)で取得した
        //   保有資産.資産IDをセット。
        //   ※get保有資産()の引数は、
        //   引数の訂正対象注文単位の同名プロパティをセット。
        if (l_orderUnit.isReversingTrade()) //WEB3BaseException
        {
            l_succEquitySellConfirmRequest.id = null;
        }
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_orderUnit.getDataSourceObject();
            Asset l_asset = l_positionManager.getAsset(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId(),
                l_rsvEqOrderUnitRow.getProductId(),
                l_orderUnit.getTaxType()); //WEB3BaseException
            if (l_asset == null)
            {
                String l_strMessage = "テーブルに該当するデータがありません。";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage);
            }
                            
            l_succEquitySellConfirmRequest.id = l_asset.getAssetId() + "";
        }
        
        // 　@　@市場コード：　@引数の訂正対象注文単位.get市場().市場コード
        Market l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();  //NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "テーブルに該当するデータがありません。";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }        
        if (l_market != null)
        {
            l_succEquitySellConfirmRequest.marketCode = l_market.getMarketCode(); 
        }
        
        // 　@　@＜（連続）現物株式売付注文確認リクエスト＞
        // 　@　@連続注文共通情報：　@引数の訂正対象注文単位.create連続注文共通情報()
        l_succEquitySellConfirmRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        // 　@　@単価調整値情報：　@引数のリクエストデータの同名プロパティ
        l_succEquitySellConfirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        // ４）　@戻り値インスタンスを返却する。

        log.exiting(STR_METHOD_NAME);
        return l_succEquitySellConfirmRequest;
    }
    
    /**
     * (create買付注文完了リクエスト)<BR>
     * 連続注文の、買付注文完了リクエストを作成する。<BR>
     * <BR>
     * １）　@戻り値インスタンスを生成する。<BR>
     * <BR>
     * ２）　@共通リクエストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@this.set現物株式共通リクエスト(生成した戻り値インスタンス, 引数のリクエストデータ)を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * ３）　@当リクエスト固有のプロパティをセットする。<BR>
     * <BR>
     * 　@　@＜現物株式買付注文完了リクエスト＞<BR>
     * 　@　@注文ID：　@引数の訂正対象注文単位.注文ID<BR>
     * 　@　@銘柄コード：　@引数の訂正対象注文単位.get銘柄().銘柄コード<BR>
     * 　@　@市場コード：　@引数の訂正対象注文単位.get市場().市場コード<BR>
     * 　@　@口座区分：　@株式データアダプタ.get口座区分(引数の訂正対象注文単位.getTaxType) <BR>
     * 　@　@取引区分：　@"現物買付注文"<BR>
     * 　@　@確認時単価：　@引数のリクエストデータの同名プロパティ<BR>
     * 　@　@確認時発注日：　@引数のリクエストデータの同名プロパティ<BR>
     * 　@　@暗証番号：　@引数のリクエストデータの同名プロパティ<BR>
     * <BR>
     * 　@　@＜（連続）現物株式買付注文完了リクエスト＞<BR>
     * 　@　@連続注文共通情報：　@引数の訂正対象注文単位.create連続注文共通情報()<BR>
     * 　@　@単価調整値情報：　@引数のリクエストデータの同名プロパティ<BR>
     * 　@　@調整後単価：　@引数のリクエストデータの同名プロパティ<BR>
     * <BR>
     * ４）　@戻り値インスタンスを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * 
     * @@param l_orderUnit - (訂正対象注文単位)<BR>
     * 訂正対象注文単位。<BR>
     * @@return WEB3SuccEquityBuyCompleteRequest
     * @@throws WEB3BaseException
     * @@roseuid 433BBAF00085
     */
    protected WEB3SuccEquityBuyCompleteRequest createBuyCompleteRequest(
        WEB3SuccEquityChangeCompleteRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createBuyCompleteRequest(" +
            "WEB3SuccEquityChangeCompleteRequest" +
            ", WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "訂正対象注文単位が未指定(null)です。");
        }

        //１）　@戻り値インスタンスを生成する。
        WEB3SuccEquityBuyCompleteRequest l_succEquityBuyCompleteRequest 
            = new WEB3SuccEquityBuyCompleteRequest();

        //２）　@共通リクエストのプロパティをセットする。
        //　@　@this.set現物株式共通リクエスト(生成した戻り値インスタンス, 引数のリクエストデータ)を
        //　@　@コールする。
        this.setEquityCommonRequest(l_succEquityBuyCompleteRequest, l_request);

        //３）　@当リクエスト固有のプロパティをセットする。
        //　@　@＜現物株式買付注文完了リクエスト＞
        //　@　@注文ID：　@引数の訂正対象注文単位.注文ID
        l_succEquityBuyCompleteRequest.orderId = l_orderUnit.getOrderId() + ""; 
        
        //　@　@銘柄コード：　@引数の訂正対象注文単位.get銘柄().銘柄コード
        EqTypeProduct l_eqtypeProduct = (EqTypeProduct)l_orderUnit.getProduct();
        if (l_eqtypeProduct == null)
        {
            String l_strMessage = "テーブルに該当するデータがありません。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        l_succEquityBuyCompleteRequest.productCode = l_eqtypeProduct.getProductCode();
        
        //　@　@市場コード：　@引数の訂正対象注文単位.get市場().市場コード
        Market l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();  //NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "テーブルに該当するデータがありません。";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        if (l_market != null)
        {
            l_succEquityBuyCompleteRequest.marketCode = l_market.getMarketCode();
        }        
        
        //口座区分：　@株式データアダプタ.get口座区分(引数の訂正対象注文単位.getTaxType) 
        l_succEquityBuyCompleteRequest.taxType = 
            WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
        
        //　@　@取引区分：　@"現物買付注文"
        l_succEquityBuyCompleteRequest.tradingType = WEB3TradingTypeDef.BUY_ORDER;
        
        //　@　@確認時単価：　@引数のリクエストデータの同名プロパティ
        l_succEquityBuyCompleteRequest.checkPrice = l_request.checkPrice;
        
        //　@　@確認時発注日：　@引数のリクエストデータの同名プロパティ
        l_succEquityBuyCompleteRequest.checkDate = l_request.checkDate;
        
        //　@　@暗証番号：　@引数のリクエストデータの同名プロパティ
        l_succEquityBuyCompleteRequest.password = l_request.password;

        //　@　@＜（連続）現物株式買付注文完了リクエスト＞
        //　@　@連続注文共通情報：　@引数の訂正対象注文単位.create連続注文共通情報()
        l_succEquityBuyCompleteRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        //　@　@単価調整値情報：　@引数のリクエストデータの同名プロパティ 
        l_succEquityBuyCompleteRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

        //    調整後単価：　@引数のリクエストデータの同名プロパティ
        l_succEquityBuyCompleteRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;

        //４）　@戻り値インスタンスを返却する。

        log.exiting(STR_METHOD_NAME);
        return l_succEquityBuyCompleteRequest;
    }
    
    /**
     * (create売付注文完了リクエスト)<BR>
     * 連続注文の、売付注文完了リクエストを作成する。<BR>
     * <BR>
     * １）　@戻り値インスタンスを生成する。<BR>
     * <BR>
     * ２）　@共通リクエストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@this.set現物株式共通リクエスト(生成した戻り値インスタンス, 引数のリクエストデータ)を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * ３）　@当リクエスト固有のプロパティをセットする。<BR>
     * <BR>
     * 　@　@＜現物株式売付注文完了リクエスト＞<BR>
     * 　@　@注文ID：　@引数の訂正対象注文単位.注文ID<BR>
     * 　@　@ID（＝資産ID）：　@反対取引(*1)の場合は、nullをセット。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@反対取引でない場合は、株式ポジションマネージャ.get保有資産(<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@口座ID, 補助口座ID, 銘柄ID, 税区分)で取得した<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@保有資産.資産IDをセット。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@※get保有資産()の引数は、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@引数の訂正対象注文単位の同名プロパティをセット。<BR>
     * 　@　@市場コード：　@引数の訂正対象注文単位.get市場().市場コード<BR>
     * 　@　@確認時単価：　@引数のリクエストデータの同名プロパティ<BR>
     * 　@　@確認時発注日：　@引数のリクエストデータの同名プロパティ<BR>
     * 　@　@暗証番号：　@引数のリクエストデータの同名プロパティ<BR>
     * <BR>
     * 　@　@(*1)反対取引かどうかの判定<BR>
     * 　@　@　@引数の訂正対象注文単位.is反対売買取引()==trueの場合は、反対取引である。<BR>
     * 　@　@　@以外、反対取引でない。<BR>
     * <BR>
     * 　@　@＜（連続）現物株式売付注文完了リクエスト＞<BR>
     * 　@　@連続注文共通情報：　@引数の訂正対象注文単位.create連続注文共通情報()<BR>
     * 　@　@単価調整値情報：　@引数のリクエストデータの同名プロパティ<BR>
     * 　@　@調整後単価：　@引数のリクエストデータの同名プロパティ<BR>
     * <BR>
     * ４）　@戻り値インスタンスを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * 
     * @@param l_orderUnit - (訂正対象注文単位)<BR>
     * 訂正対象注文単位。<BR>
     * @@return WEB3SuccEquitySellCompleteRequest
     * @@throws WEB3BaseException
     * @@roseuid 433BBB150392
     */
    protected WEB3SuccEquitySellCompleteRequest createSellCompleteRequest(
        WEB3SuccEquityChangeCompleteRequest l_request, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSellCompleteRequest(" +
            "WEB3SuccEquityChangeCompleteRequest" +
            ", WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "訂正対象注文単位が未指定(null)です。");
        }

        // １）　@戻り値インスタンスを生成する。
        WEB3SuccEquitySellCompleteRequest l_succEquitySellCompleteRequest = 
            new WEB3SuccEquitySellCompleteRequest();

        // ２）　@共通リクエストのプロパティをセットする。
        // 　@　@this.set現物株式共通リクエスト(生成した戻り値インスタンス, 引数のリクエストデータ)を
        // 　@　@コールする。
        this.setEquityCommonRequest(l_succEquitySellCompleteRequest, l_request);

        // ３）　@当リクエスト固有のプロパティをセットする。
        // 　@　@＜現物株式売付注文完了リクエスト＞
        // 　@　@注文ID：　@引数の訂正対象注文単位.注文ID
        l_succEquitySellCompleteRequest.orderId = l_orderUnit.getOrderId() + "";
        
        // 　@　@ID（＝資産ID）：　@反対取引(*1)の場合は、nullをセット。
        //   反対取引でない場合は、株式ポジションマネージャ.get保有資産(
        //   口座ID, 補助口座ID, 銘柄ID, 税区分)で取得した
        //   保有資産.資産IDをセット。
        if (l_orderUnit.isReversingTrade()) //WEB3BaseException
        {
            l_succEquitySellCompleteRequest.id = null;
        }
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_orderUnit.getDataSourceObject();
            Asset l_asset = l_positionManager.getAsset(
                l_orderUnit.getAccountId(),
                l_orderUnit.getSubAccountId(),
                l_rsvEqOrderUnitRow.getProductId(),
                l_orderUnit.getTaxType()); //WEB3BaseException
            if (l_asset == null)
            {
                String l_strMessage = "テーブルに該当するデータがありません。";
                log.error(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage);
            }
                            
            l_succEquitySellCompleteRequest.id = l_asset.getAssetId() + "";
        }
        
        // 　@　@市場コード：　@引数の訂正対象注文単位.get市場().市場コード
        Market l_market = null;
        try
        {
            l_market = l_orderUnit.getMarket();  //NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "テーブルに該当するデータがありません。";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        if (l_market != null)
        {
            l_succEquitySellCompleteRequest.marketCode = l_market.getMarketCode();
        }        
        
        // 　@　@確認時単価：　@引数のリクエストデータの同名プロパティ
        l_succEquitySellCompleteRequest.checkPrice = l_request.checkPrice;
        
        // 　@　@確認時発注日：　@引数のリクエストデータの同名プロパティ
        l_succEquitySellCompleteRequest.checkDate = l_request.checkDate;
        
        // 　@　@暗証番号：　@引数のリクエストデータの同名プロパティ
        l_succEquitySellCompleteRequest.password = l_request.password;

        // 　@　@(*1)反対取引かどうかの判定
        // 　@　@　@引数の訂正対象注文単位.is反対売買取引()==trueの場合は、反対取引である。
        // 　@　@　@以外、反対取引でない。

        // 　@　@＜（連続）現物株式売付注文完了リクエスト＞
        // 　@　@連続注文共通情報：　@引数の訂正対象注文単位.create連続注文共通情報()
        l_succEquitySellCompleteRequest.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        
        // 　@　@単価調整値情報：　@引数のリクエストデータの同名プロパティ 
        l_succEquitySellCompleteRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;;

        //    調整後単価：　@引数のリクエストデータの同名プロパティ
        l_succEquitySellCompleteRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;
        
        // ４）　@戻り値インスタンスを返却する。

        log.exiting(STR_METHOD_NAME);
        return l_succEquitySellCompleteRequest;
    }
    
    /**
     * (set現物株式共通リクエスト)<BR>
     * 指定された「（output）現物株式共通リクエスト」のインスタンスに、プロパティをセットする。<BR>
     * <BR>
     * 以下のプロパティに、「（input）現物株式共通リクエスト」の同名プロパティの値をセットする。<BR>
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
     * @@param l_outputRequest - (（output）現物株式共通リクエスト)<BR>
     * （output）現物株式共通リクエスト。<BR>
     * @@param l_intputRequest - (（input）現物株式共通リクエスト。)<BR>
     * （input）現物株式共通リクエスト。<BR>
     * （（連続）現物株式注文訂正確認リクエスト or （連続）現物株式注文訂正完了リクエスト）<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B68E103B1
     */
    protected void setEquityCommonRequest(
        WEB3EquityCommonRequest l_outputRequest, 
        WEB3EquityCommonRequest l_intputRequest)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setEquityCommonRequest(" +
            "WEB3EquityCommonRequest" +
            ", WEB3EquityCommonRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_intputRequest == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "（input）現物株式共通リクエストが未指定(null)です。");
        }

        if (l_outputRequest == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "（output）現物株式共通リクエストが未指定(null)です。");
        }

        //　@注文株数
        l_outputRequest.orderQuantity = l_intputRequest.orderQuantity;
        
        //　@注文単価区分
        l_outputRequest.orderPriceDiv = l_intputRequest.orderPriceDiv;
        
        //　@注文単価
        l_outputRequest.limitPrice = l_intputRequest.limitPrice;
        
        //　@値段条件
        l_outputRequest.priceCondType = l_intputRequest.priceCondType;
        
        //　@執行条件
        l_outputRequest.execCondType = l_intputRequest.execCondType;
        
        //　@注文期限区分
        l_outputRequest.expirationDateType = l_intputRequest.expirationDateType;
        
        //　@注文有効期限
        l_outputRequest.expirationDate = l_intputRequest.expirationDate;
        
        //　@発注条件区分
        l_outputRequest.orderCondType = l_intputRequest.orderCondType;
        
        //　@逆指値用発注条件単価
        l_outputRequest.stopOrderCondPrice = l_intputRequest.stopOrderCondPrice;
        
        //　@逆指値用発注条件演算子
        l_outputRequest.stopOrderCondOperator = l_intputRequest.stopOrderCondOperator;
        
        //　@W指値用発注条件単価
        l_outputRequest.wlimitOrderCondPrice = l_intputRequest.wlimitOrderCondPrice;
        
        //　@W指値用発注条件演算子
        l_outputRequest.wlimitOrderCondOperator = l_intputRequest.wlimitOrderCondOperator;
        
        //　@W指値用注文単価区分
        l_outputRequest.wLimitOrderPriceDiv = l_intputRequest.wLimitOrderPriceDiv;
        
        //　@W指値用注文単価
        l_outputRequest.wLimitPrice = l_intputRequest.wLimitPrice;

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate取引余力)<BR>
     * 取引余力をチェックし、取引余力結果オブジェクトを返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_orderSpec - (株式注文内容)<BR>
     * 株式注文内容オブジェクト。<BR>
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ。<BR>
     * （false：　@確認時、true：　@完了時）<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト。<BR>
     * @@return WEB3TPTradingPowerResult
     * @@roseuid 433B7CA501CD
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityNewCashBasedOrderSpec l_orderSpec, 
        boolean l_blnUpdateFlg, 
        WEB3EquityTradedProduct l_tradedProduct) 
    {
        return null;
    }
    
    /**
     * (validate訂正時取引余力)<BR>
     * 取引余力をチェックし、取引余力結果オブジェクトを返却する。<BR>
     * <BR>
     * 引数の訂正前注文単位.is買注文()==false（売注文）の場合は、<BR>
     * 何もせずにreturnする。（nullを返却）<BR>
     * <BR>
     * 引数の訂正前注文単位.is買注文()==true（買注文）の場合は、<BR>
     * 余力チェックを実施する部店(*2)の場合のみ、<BR>
     * 買付可能額(*1)と引数の概算受渡代金を比較し、<BR>
     * （概算受渡代金 > 買付可能額(*1)）の場合は、<BR>
     * 「取引余力不足」の例外をthrowする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_01306<BR>
     * <BR>
     * 以外、nullを返却する。<BR>
     * <BR>
     * (*1)買付可能額<BR>
     * 取引余力サービス.get株式買付可能額～連続注文～(<BR>
     * 引数の補助口座, 引数の取引銘柄.受渡日, <BR>
     * 引数の訂正前注文単位.概算受渡代金)の戻り値を、<BR>
     * 買付可能額とする。<BR>
     * <BR>
     * (*2)余力チェックを実施する部店<BR>
     * 連続注文マネージャImpl.is余力チェック実施部店()==trueの場合は、<BR>
     * 余力チェックを実施する部店であると判定する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_dblEstimatedPrice - (概算受渡代金)<BR>
     * 概算受渡代金。<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト。<BR>
     * @@param l_orderUnit - (訂正前注文単位)<BR>
     * 訂正対象の株式予約注文単位オブジェクト。<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     * @@roseuid 433B7CD70392
     */
    protected WEB3TPTradingPowerResult validateChangeTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        double l_dblEstimatedPrice, 
        WEB3EquityTradedProduct l_tradedProduct, 
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChangeTradingPower(" +
            "WEB3GentradeSubAccount" +
            ", double" +
            ", WEB3EquityTradedProduct" +
            ", WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "取引銘柄オブジェクトが未指定(null)です。");
        }

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "訂正対象の株式予約注文単位オブジェクトが未指定(null)です。");
        }

        if (!l_orderUnit.isBuyOrder())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        if (l_toOrderManager.isCheckTradingPowerBranch(l_subAccount) == false)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3TPTradingPowerService l_trdingPowerService = 
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_orderUnit.getDataSourceObject();
        Double l_estimatedPrice = null;
        if (l_rsvEqOrderUnitRow != null && !l_rsvEqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_estimatedPrice = new Double(l_rsvEqOrderUnitRow.getEstimatedPrice());
        }
        double l_dblBuyPower = l_trdingPowerService.getSuccEquityTradingPower(
            l_subAccount,
            l_tradedProduct.getDailyDeliveryDate(),
            l_estimatedPrice);
        if (l_dblEstimatedPrice > l_dblBuyPower)
        {
            String l_strMessage = "取引余力チェックエラー。"
                + "「概算受渡代金(" + l_dblEstimatedPrice 
                + ") > 買付可能額(" + l_dblBuyPower + ")」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //以外、nullを返却する。

        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (validate連続注文最大設定数)<BR>
     * 連続注文の最大設定数を超過してしまわないかどうかをチェックする。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@roseuid 433B846303C1
     */
    protected void validateSuccOrderMaxQuantity(EqTypeOrderUnit l_parentOrderUnit) 
    {
    }
    
    /**
     * (submit現物株式注文)<BR>
     * 予約注文を登録する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_orderSpec - (株式注文内容)<BR>
     * 株式注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 予約注文の注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_requestAdaptor - (（連続）株式注文リクエストアダプタ)<BR>
     * （連続）株式注文リクエストアダプタ。<BR>
     * @@roseuid 433B8A6B0315
     */
    protected void submitNewCashBasedOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityNewCashBasedOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        WEB3EquityOrderRequestAdapter l_requestAdaptor) 
    {
    }
    
    /**
     * (notify予約注文登録)<BR>
     * 予約注文の登録をルールエンジンサーバに通知する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * @@param l_lngRsvOrderId - (子注文の注文ID)<BR>
     * 子注文の注文ID。<BR>
     */
    protected void notifyRsvOrderRegister(long l_lngRsvOrderId) throws WEB3BaseException
    {
    }
}
@
