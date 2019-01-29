head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式注文訂正入力サービスImpl (WEB3ToSuccEquityChangeOrderInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭海良(中訊) 新規作成
Revesion History : 2007/01/20 肖志偉(中訊) 仕様変更モデルNo.224
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3EquityChangeInputRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeInputResponse;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (（連続）現物株式注文訂正入力サービスImpl )<BR>
 * （連続）現物株式注文訂正入力サービス実装クラス。<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccEquityChangeOrderInputServiceImpl extends WEB3EquityChangeOrderInputServiceImpl 
    implements WEB3ToSuccEquityChangeOrderInputService 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityChangeOrderInputServiceImpl.class);
    
    /**
     * @@roseuid 436ACF7303B9
     */
    public WEB3ToSuccEquityChangeOrderInputServiceImpl() 
    {
     
    }
    
    /**
     * （連続）現物株式注文訂正入力サービス処理を実施する。<BR>
     * <BR>
     * get訂正入力画面()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ。<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4337C15B035E
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

        if (l_request instanceof WEB3SuccEquityChangeInputRequest)
        {
            //get訂正入力画面()
            l_response = 
                this.getChangeInputScreen((WEB3SuccEquityChangeInputRequest)l_request);
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
     * (get訂正入力画面)<BR>
     * （連続）現物株式注文訂正入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図「（（連続）現物株式注文訂正入力）get訂正入力画面」参照。<BR>
     * @@param  l_request - リクエストデータ。<BR>
     * 
     * @@return WEB3SuccEquityChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4337C1B600AE
     */
    protected WEB3SuccEquityChangeInputResponse getChangeInputScreen(
        WEB3SuccEquityChangeInputRequest  l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getChangeInputScreen(WEB3SuccEquityChangeInputRequest)";
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

        //1.2 get株式予約注文単位(注文ID : long)
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_toSuccOrderUnit = null;
        try
        {
            l_toSuccOrderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage =
                "テーブルに該当するデータがありません。「注文ID:" + l_lngOrderId + "」";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow =
            (RsvEqOrderUnitRow)l_toSuccOrderUnit.getDataSourceObject();
        
        //1.3 reset市場コード(市場コード : String)
        Market l_market = null;
        String l_strMarketCode = null;
        try
        {
            l_market = l_toSuccOrderUnit.getMarket();
            if (l_market != null)
            {
                l_strMarketCode = l_market.getMarketCode();
            }
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage =
                "テーブルに該当するデータがありません。「市場ID:" + l_rsvEqOrderUnitRow.getMarketId() + "」";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }        

        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
        //1.4 get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount(); //WEB3BaseException
        
        //1.5 get親注文の注文単位( )
        EqTypeOrderUnit l_parentOrderUnit = l_toSuccOrderUnit.getParentOrderUnit();
        
        //1.6 validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_rsvEqOrderUnitRow.getReserveOrderTradingType(),
            l_parentOrderUnit); //WEB3BaseException
            
        //1.7 get訂正入力画面(リクエストデータ : 現物株式注文訂正入力リクエスト)
        WEB3SuccEquityChangeInputResponse l_response = 
            (WEB3SuccEquityChangeInputResponse)super.getChangeInputScreen(l_request);//WEB3BaseException
        
        //1.8 create連続注文共通情報( )
        WEB3SuccCommonInfo l_succCommonInfo = l_toSuccOrderUnit.createSuccCommonInfo();
        
        //1.9 create単価調整値情報( )
        WEB3SuccPriceAdjustmentValueInfo l_succPriceAdjustmentValueInfo = 
            l_toSuccOrderUnit.createSuccPriceAdjustmentValueInfo();
        
        //注文期限区分一覧、及び（出来るまで注文開始日、最終日、祝日一覧）取得
        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond =
            new WEB3GentradeHandlingOrderCond(l_subAccount.getInstitution().getInstitutionCode(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);

        //（(*) プロパティセット
        //●「（連続）現物株式注文訂正入力レスポンス」にのみ存在するプロパティ
        l_response.succCommonInfo = l_succCommonInfo;
        l_response.priceAdjustmentValueInfo = l_succPriceAdjustmentValueInfo;
        l_response.expirationDateTypeList =
            l_gentradeHandingOrderCond.getHandlingPossibleExpirationDateType();
        
        //●異なる値をセットするプロパティ（再セット）
        l_response.priceCondList = new String[]{WEB3PriceConditionDef.DEFAULT};
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};
        //Ｗ指値用執行条件一覧：　@nullをセット。
        l_response.wlimitExecCondList = null;

        if (l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleHandling() &&
            l_response.expirationStartDate == null)
        {
            l_response.expirationStartDate = l_gentradeHandingOrderCond.getOrderUntilDeadLineStartDay();
            l_response.expirationEndDate = l_gentradeHandingOrderCond.getOrderUntilDeadLineEndDay();
            l_response.holidayList = l_gentradeHandingOrderCond.getExpirationDateHoliday();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get訂正対象注文単位)<BR>
     * 訂正対象の注文単位オブジェクトを取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@株式予約注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get株式予約注文単位(リクエストデータ.ID)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）　@株式注文単位オブジェクトを生成する。<BR>
     * 　@連続注文マネージャImpl.create株式注文単位(１）の戻り値)を<BR>
     * 　@コールし、戻り値を返却する。<BR>
     * @@param  l_request - リクエストデータ。<BR>
     * 
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 433A1E150349
     */
    protected EqTypeOrderUnit getChangeOrderUnit(WEB3EquityChangeInputRequest  l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getChangeOrderUnit(WEB3EquityChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }

        // １）　@株式予約注文単位を取得する。
        // 　@連続注文マネージャImpl.get株式予約注文単位(リクエストデータ.ID)を
        // 　@コールする。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        long l_lngOrderId = Long.parseLong(l_request.id);
        try
        {
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
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
        
        // ２）　@株式注文単位オブジェクトを生成する。
        // 　@連続注文マネージャImpl.create株式注文単位(１）の戻り値)を
        // 　@コールし、戻り値を返却する。
        EqTypeOrderUnit l_eqTypeOrderUnit = l_toOrderManager.createEqtypeOrderUnit(l_orderUnit);

        log.exiting(STR_METHOD_NAME);
        return l_eqTypeOrderUnit;
    }
    
    /**
     * (get買付可能額)<BR>
     * 買付可能額を取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@引数の株式予約注文単位.getSide()==SideEnum.SELLの場合は、nullを返却する。<BR>
     * 　@　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@取引余力サービス.get株式買付可能額～連続注文～(引数の補助口座, null, <BR>
     * 　@　@　@訂正前の概算受渡代金(*1))にdelegateする。<BR>
     * <BR>
     * (*1)訂正前の概算受渡代金<BR>
     * 引数の株式予約注文単位.概算受渡代金をセット。<BR>
     * @@param l_orderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@return Double
     * @@throws WEB3BaseException
     * @@roseuid 433A1E1503A7
     */
    protected Double getEquityTradingPower(EqTypeOrderUnit l_orderUnit, WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getEquityTradingPower(EqTypeOrderUnit, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "株式予約注文単位が未指定(null)です。");
        }
        
        // １）　@引数の株式予約注文単位.getSide()==SideEnum.SELLの場合は、nullを返却する。
        // 　@　@　@以外、以下の処理を行う。
        if (SideEnum.SELL.equals(l_orderUnit.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        

        // ２）　@取引余力サービス.get株式買付可能額～連続注文～(引数の補助口座, null, 
        // 　@　@　@訂正前の概算受渡代金(*1))にdelegateする。
        WEB3TPTradingPowerService l_trdingPowerService = 
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        Double l_estimatedPrice = null;
        if (l_eqOrderUnitRow != null && !l_eqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_estimatedPrice = new Double(l_eqOrderUnitRow.getEstimatedPrice());
        }
        double l_dblEquityTradingPower = l_trdingPowerService.getSuccEquityTradingPower(
            l_subAccount,
            null,
            l_estimatedPrice);

        log.exiting(STR_METHOD_NAME);
        return new Double(l_dblEquityTradingPower);
    }
    
    /**
     * (get出来るまで注文from日付)<BR>
     * 注文有効期限取得に使用する、出来るまで注文from日付を返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 引数の株式予約注文単位.発注日を返却する。<BR>
     * @@param l_rsvEqOrderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 433A1E1503AC
     */
    protected Date getCarriedOrderFromDate(EqTypeOrderUnit l_rsvEqOrderUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getCarriedOrderFromDate(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvEqOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "株式予約注文単位が未指定(null)です。");
        }
        Date l_datBizDate = null;
        if (l_rsvEqOrderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl)
        {
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
                (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            l_datBizDate = WEB3DateUtility.getDate(l_rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd");            
        }
        else
        {
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            l_datBizDate = WEB3DateUtility.getDate(l_eqtypeOrderUnitRow.getBizDate(), "yyyyMMdd");
        }

        log.exiting(STR_METHOD_NAME);
        return l_datBizDate;
    }
    
    /**
     * (validate注文訂正可能)<BR>
     * 訂正対象注文が、訂正可能な状態かどうかをチェックする。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@株式予約注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get株式予約注文単位(引数.株式予約注文単位.注文ID)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）　@１）の戻り値.validate訂正可能状態()をコールする。<BR>
     * @@param l_orderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433A1E1503B6
     */
    protected void validateOrderForChangeability(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateOrderForChangeability(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "株式予約注文単位が未指定(null)です。");
        }

        // １）　@株式予約注文単位を取得する。
        // 　@連続注文マネージャImpl.get株式予約注文単位(引数.株式予約注文単位.注文ID)を
        // 　@コールする。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_succEqTypeOrderUnitImpl = null;
        long l_lngOrderId = l_orderUnit.getOrderId();
        try
        {
            l_succEqTypeOrderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
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

        // ２）　@１）の戻り値.validate訂正可能状態()をコールする。
        l_succEqTypeOrderUnitImpl.validateOrderForChangeability();    

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get概算簿価単価)<BR>
     * 概算簿価単価を返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@株式予約注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get株式予約注文単位(引数.株式予約注文単位.注文ID)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）　@１）の戻り値.get概算簿価単価()にdelegateする。<BR>
     * @@param l_orderUnit - (株式予約注文単位)<BR>
     * 株式予約注文単位オブジェクト。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@return String
     * @@throws WEB3BaseException 
     * @@roseuid 433A1E1503B8
     */
    protected String getEstimatedBookPrice(EqTypeOrderUnit l_orderUnit, WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getEstimatedBookPrice(EqTypeOrderUnit, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "株式予約注文単位が未指定(null)です。");
        }

        // １）　@株式予約注文単位を取得する。
        // 　@連続注文マネージャImpl.get株式予約注文単位(引数.株式予約注文単位.注文ID)を
        // 　@コールする。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_succEqTypeOrderUnitImpl = null;
        long l_lngOrderId = l_orderUnit.getOrderId();
        try
        {
            l_succEqTypeOrderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
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

        // ２）　@１）の戻り値.get概算簿価単価()にdelegateする。
        String l_strEstimatedBookPrice = l_succEqTypeOrderUnitImpl.getEstimatedBookPrice();

        log.exiting(STR_METHOD_NAME);
        return l_strEstimatedBookPrice;
    }
}
@
