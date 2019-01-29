head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引取消サービスImpl(WEB3ToSuccMarginCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/09 黄　@浩澎（中訊) 新規作成
                   2006/08/30 柴雙紅(中訊) 仕様変更モデル165
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCancelService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）信用取引取消サービスImpl)<BR>
 * （連続）信用取引取消サービス実装クラス<BR>
 * 
 * @@author 黄　@浩澎（中訊) 
 * @@version 1.0
 */
public class WEB3ToSuccMarginCancelServiceImpl extends WEB3MarginClientRequestService 
    implements WEB3ToSuccMarginCancelService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCancelServiceImpl.class);
    
    /**
     * @@roseuid 436ACF770186
     */
    public WEB3ToSuccMarginCancelServiceImpl() 
    {
     
    }
    
    /**
     * （連続）信用取引取消処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()、<BR>
     * submit注文()メソッドのいずれかをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433A2403034B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ.リクエストデータがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccMarginCancelConfirmRequest)
        {
            l_response =
                this.validateOrder((WEB3SuccMarginCancelConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginCancelCompleteRequest)
        {
            l_response =
                this.submitOrder((WEB3SuccMarginCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug("パラメータ.リクエストデータの型が不正です。");
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
     * （連続）信用取引注文取消発注審査を行う<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引取消サービス）validate注文」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引注文取消確認リクエストオブジェクト<BR>
     * @@return WEB3SuccMarginCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 433A2D9501A5
     */
    protected WEB3SuccMarginCancelConfirmResponse validateOrder(WEB3SuccMarginCancelConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        //1.2 get補助口座( )
        WEB3GentradeSubAccount l_gentradeSubAccount = getSubAccount();
        //1.3 get株式予約注文単位(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ne)
        {
            log.error("テーブルに該当するデータがありません。", l_ne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_orderUnitImpl.getDataSourceObject();
        
        //1.4 validate信用注文取消(補助口座, 株式予約注文単位Impl)
        l_toOrderManager.validateMarginCancelOrder(l_gentradeSubAccount, l_orderUnitImpl);
        //1.5 create建株明細ByOrder(株式予約注文単位Impl)
        WEB3MarginContractUnit[] l_marginContractUnits = l_toOrderManager.createContractUnitByOrder(l_orderUnitImpl);
        //1.6 get取引銘柄( )
        EqTypeTradedProduct l_tradeProduct = (EqTypeTradedProduct) l_orderUnitImpl.getTradedProduct();
        //1.7 get表示用時価情報(取引銘柄 : EqTypeTradedProduct, 補助口座 : 補助口座)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productMgr = 
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        WEB3EquityProductQuote l_equityProductQuote =
            l_productMgr.getDisplayEquityProductQuote(l_tradeProduct, l_gentradeSubAccount);

        //1.8 get時価区分( )
        String l_strQuoteTypeDiv = l_equityProductQuote.getQuoteTypeDiv();
        //1.9 get時価( )
        double l_dblQuote = l_equityProductQuote.getQuote();
        //1.10 get前日比( )
        double l_dblComparePreviousDay = l_equityProductQuote.getComparedPreviousDay();
        //1.11 get時価発表時間( )
        Timestamp l_tsQuoteTime = l_equityProductQuote.getQuoteTime();
        //1.12 get銘柄( )
        EqTypeProduct l_product = (EqTypeProduct) l_orderUnitImpl.getProduct();
        //1.13 get市場閉局警告市場(部店 : 部店, 銘柄タイプ : ProductTypeEnum, 信用取引区分 : String)
        String[] l_tradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_gentradeSubAccount.getWeb3GenBranch(),
            ProductTypeEnum.EQUITY,
            l_rsvEqOrderUnitRow.getRepaymentType());
        
        //1.14 createResponse( )        
        WEB3SuccMarginCancelConfirmResponse l_response =
            (WEB3SuccMarginCancelConfirmResponse) l_request.createResponse();
        //(*)レスポンスデータに以下の通りプロパティをセットする。
        //確認時発注日      ＝　@取引時間管理.get発注日()
        l_response.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //概算受渡代金      ＝　@予約注文単位.概算受渡代金
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getEstimatedPrice());
        //取引終了警告市場コード一覧   ＝　@get市場閉局警告市場()の戻り値
        l_response.messageSuspension = l_tradeCloseMarkets;
        //銘柄コード           ＝　@get銘柄()の戻り値.銘柄コード
        l_response.productCode = l_product.getProductCode();
        //銘柄名         ＝　@get銘柄()の戻り値.getDataSourceObject().getStandardName()
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        
        try
        {
            //市場コード           ＝　@予約注文単位.get市場().市場コード
            l_response.marketCode = l_orderUnitImpl.getMarket().getMarketCode();
        }
        catch (NotFoundException l_ne)
        {
            log.error("テーブルに該当するデータがありません。", l_ne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }
        
        //口座区分：　@株式データアダプタ.get口座区分(予約注文単位.getTaxType())
        l_response.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitImpl.getTaxType());
        //取引区分            ＝　@予約注文単位.getメッセージ用取引区分()
        l_response.tradingType = l_orderUnitImpl.getMsgTradingType();
        
        //弁済          ＝　@信用取引弁済オブジェクト
        WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
        //信用取引弁済インスタンスを生成し、以下のプロパティをセット。
        //　@弁済区分   ＝　@予約注文単位.弁済区分
        //　@弁済期限値  ＝　@予約注文単位.弁済期限値        
        l_repaymentUnit.repaymentDiv = l_rsvEqOrderUnitRow.getRepaymentType();
        l_repaymentUnit.repaymentTimeLimit = String.valueOf(l_rsvEqOrderUnitRow.getRepaymentNum());
        l_response.repayment = l_repaymentUnit;
        
        //注文株数            ＝　@予約注文単位.注文数量
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getQuantity());
        //内出来株数       ＝　@null
        l_response.partContQuantity = null;
        //注文単価区分      ＝　@予約注文単位.getメッセージ用注文単価区分()
        l_response.orderPriceDiv = l_orderUnitImpl.getMsgOrderPriceDiv();
        //注文単価            ＝　@予約注文単位.getメッセージ用注文単価()
        l_response.limitPrice  = l_orderUnitImpl.getMsgLimitPrice();              
        //決済順序区分      ＝　@予約注文単位.決済順序区分
        l_response.closingOrder = l_rsvEqOrderUnitRow.getClosingOrderType();
        //建株明細一覧      ＝　@create建株明細ByOrder()の戻り値
        l_response.contractUnits = l_marginContractUnits;
        //現引先現渡元口座区分  ＝　@予約注文単位.getメッセージ用現引先現渡元口座区分()
        l_response.swapTaxType = l_orderUnitImpl.getMsgSwapTaxType();
        //値段条件            ＝　@予約注文単位.getメッセージ用値段条件()
        l_response.priceCondType = l_orderUnitImpl.getMsgPriceCondType();
        //執行条件            ＝　@予約注文単位.getメッセージ用執行条件()
        l_response.execCondType = l_orderUnitImpl.getMsgExecCondType();
        //注文期限区分      ＝　@予約注文単位.getメッセージ用注文期限区分()
        l_response.expirationDateType = l_orderUnitImpl.getMsgExpirationDateType();
        //注文有効期限      ＝　@予約注文単位.getメッセージ用注文有効期限()
        l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitImpl.getMsgExpirationDate());
        //発注条件区分      ＝　@予約注文単位.getメッセージ用発注条件区分()
        l_response.orderCondType = l_orderUnitImpl.getMsgOrderCondType();
        //逆指値用発注条件単価  ＝　@null
        l_response.stopOrderCondPrice = null;
        //逆指値用発注条件演算子 ＝　@null
        l_response.stopOrderCondOperator = null;
        //W指値用発注条件単価  ＝　@null
        l_response.wlimitOrderCondPrice = null;
        //W指値用発注条件演算子 ＝　@null
        l_response.wlimitOrderCondOperator = null;
        //W指値用注文単価区分  ＝　@null
        l_response.wLimitOrderPriceDiv = null;
        //W指値用注文単価        ＝　@null
        l_response.wLimitPrice = null;
        //時価区分            ＝　@get時価区分()の戻り値
        l_response.currentPriceDiv = l_strQuoteTypeDiv;
        //時価（現在値）     ＝　@get時価()の戻り値
        l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblQuote);
        //前日比         ＝　@get前日比()の戻り値
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblComparePreviousDay);
        //取引時間(時価発表時間)    ＝　@get時価発表時間()の戻り値
        l_response.currentPriceTime = l_tsQuoteTime;
        //単価調整値情報     ＝　@予約注文単位.create単価調整値情報()
        l_response.priceAdjustmentValueInfo = l_orderUnitImpl.createSuccPriceAdjustmentValueInfo();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * （連続）信用取引注文取消処理を行う<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引取消サービス）submit注文」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引注文取消完了リクエストオブジェクト
     * @@return WEB3SuccMarginCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 433A2D9501C5
     */
    protected WEB3SuccMarginCancelCompleteResponse submitOrder(WEB3SuccMarginCancelCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 get発注日(確認時発注日 : Date)
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.3 get補助口座( )
        WEB3GentradeSubAccount l_gentradeSubAccount = getSubAccount();
        
        //1.4 get株式予約注文単位(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ne)
        {
            log.error("テーブルに該当するデータがありません。", l_ne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }

        //1.5 validate信用注文取消(補助口座, 株式予約注文単位Impl)
        l_toOrderManager.validateMarginCancelOrder(l_gentradeSubAccount, l_orderUnitImpl);
        //1.6 submit予約注文取消(SubAccount, 株式予約注文単位Impl, String)
        l_toOrderManager.submitEqtypeCancelOrder(l_gentradeSubAccount, l_orderUnitImpl, l_request.password);
        //1.7 createResponse()        
        WEB3SuccMarginCancelCompleteResponse l_response =
            (WEB3SuccMarginCancelCompleteResponse) l_request.createResponse();
        //(*)レスポンスデータにプロパティをセットする。
        //更新時間    ＝　@現在日時（＝GtlUtils.getSystemTimestamp()）
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        //識別番号    ＝　@リクエスト.ID（＝予約注文単位.注文ID）
        l_response.orderActionId = String.valueOf(l_orderUnitImpl.getOrderId());
        //連続注文設定フラグ　@＝　@false（固定）
        l_response.succSettingFlag = false;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
