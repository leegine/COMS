head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeCloseMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引訂正返済入力サービスImpl(WEB3ToSuccMarginChangeCloseMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 譚漢江(中訊) 新規作成
Revesion History : 2007/01/20 肖志偉(中訊) 仕様変更モデルNo.224
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputRequest;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCloseMarginInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeCloseMarginInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （（連続）信用取引訂正返済入力サービスImpl)<BR>
 * （連続）信用取引訂正返済入力サービス実装クラス<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeCloseMarginInputServiceImpl 
    extends WEB3MarginChangeCloseMarginInputServiceImpl 
    implements WEB3ToSuccMarginChangeCloseMarginInputService 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeCloseMarginInputServiceImpl.class);

    /**
     * @@roseuid 436ACF7D0222
     */
    public WEB3ToSuccMarginChangeCloseMarginInputServiceImpl() 
    {
     
    }
    
    /**
     * （連続）信用取引訂正返済入力サービス処理を実施する。<BR>
     * <BR>
     * this.get訂正返済入力画面()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BBB0800A2
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
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccMarginCloseChangeInputRequest)
        {
            l_response = this.getCloseMarginChangeInputScreen((WEB3SuccMarginCloseChangeInputRequest) l_request);
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
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get訂正返済入力画面)<BR>
     * 信用取引訂正返済の入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引訂正返済入力サービス）get訂正返済入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccMarginCloseChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BBB9402D5
     */
    protected WEB3SuccMarginCloseChangeInputResponse getCloseMarginChangeInputScreen(
        WEB3SuccMarginCloseChangeInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCloseMarginChangeInputScreen(WEB3SuccMarginCloseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();
        
        //get株式予約注文単位(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnit = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_eqTypeOrderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("株式予約注文単位テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //reset市場コード(市場コード : String)
        String l_strMarketCode = null;
        try
        {
            l_strMarketCode = l_eqTypeOrderUnit.getMarket().getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("市場テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //get親注文の注文単位( )
        EqTypeOrderUnit l_orderUnit = l_eqTypeOrderUnit.getParentOrderUnit();
        
        //get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow) l_eqTypeOrderUnit.getDataSourceObject();
        l_toOrderManager.validateSuccOrder(
            l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT, 
            l_rsvEqOrderUnitRow.getReserveOrderTradingType(),
            l_orderUnit);

        //get訂正返済入力画面(リクエストデータ : 信用取引訂正返済入力リクエスト)
        WEB3SuccMarginCloseChangeInputResponse l_response = 
            (WEB3SuccMarginCloseChangeInputResponse) super.getCloseMarginChangeInputScreen(l_request);

        //注文期限区分一覧、及び（出来るまで注文開始日、最終日、祝日一覧）取得
        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond =
            new WEB3GentradeHandlingOrderCond(l_subAccount.getInstitution().getInstitutionCode(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);

        //(*)プロパティセット
        //●（連続）信用取引訂正返済入力レスポンス固有のプロパティ
        l_response.succCommonInfo = l_eqTypeOrderUnit.createSuccCommonInfo();
        l_response.priceAdjustmentValueInfo = l_eqTypeOrderUnit.createSuccPriceAdjustmentValueInfo();
        l_response.expirationDateTypeList = l_gentradeHandingOrderCond.getHandlingPossibleExpirationDateType();

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
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * <BR>
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 433BC53D03B0
     */
    protected EqTypeOrderUnit getChangeOrderUnit(WEB3MarginCloseMarginChangeInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getChangeOrderUnit(WEB3MarginCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //１）　@株式予約注文単位を取得する。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnit = null;
        try
        {
            long l_lngOrderId = Long.parseLong(l_request.id);
            l_eqTypeOrderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
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

        //２）　@株式注文単位オブジェクトを生成する。
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.createEqtypeOrderUnit(l_eqTypeOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }
    
    /**
     * (validate注文訂正可能)<BR>
     * 訂正対象注文が、訂正可能な状態かどうかをチェックする。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@株式予約注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get株式予約注文単位(注文単位.注文ID)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）　@１）の戻り値.validate訂正可能状態()をコールする。<BR>
     * @@param l_orderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433BC53D03CF
     */
    protected void validateOrderForChangeability(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrderForChangeability(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //１）　@株式予約注文単位を取得する。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnit = null;
        try
        {
            long l_lngOrderId = l_orderUnit.getOrderId();
            l_eqTypeOrderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
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

        //２）　@１）の戻り値.validate訂正可能状態()をコールする。
        l_eqTypeOrderUnit.validateOrderForChangeability();

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get出来るまで注文from日付)<BR>
     * 注文有効期限取得に使用する、出来るまで注文from日付を返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 引数の注文単位.発注日を返却する。<BR>
     * @@param l_orderUnit - (株式注文単位)<BR>
     * 株式注文単位オブジェクト。<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 433BC53E0006
     */
    protected Date getCarriedOrderFromDate(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCarriedOrderFromDate(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }
        
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        log.exiting(STR_METHOD_NAME);
        return WEB3DateUtility.getDate(l_eqOrderUnitRow.getBizDate(), "yyyyMMdd");
    }
    
    /**
     * (create建株明細ByOrder)<BR>
     * 引数の注文単位に関連する信用取引建株明細の<BR>
     * 一覧を作成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@株式予約注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get株式予約注文単位(注文単位.注文ID)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）　@連続注文マネージャ.create建株明細ByOrder(１）の戻り値)を<BR>
     * 　@コールし、戻り値を返却する。<BR>
     * 　@※nullが返却された場合、<BR>
     * 　@　@「予約決済対象建株は別注文により決済済」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@tag: BUSINESS_ERROR_02289<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 433BC53E0025
     */
    protected WEB3MarginContractUnit[] createContractUnitByOrder(EqTypeOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createContractUnitByOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //１）　@株式予約注文単位を取得する。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnit = null;
        try
        {
            long l_lngOrderId = l_orderUnit.getOrderId();
            l_eqTypeOrderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
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

        //２）　@連続注文マネージャ.create建株明細ByOrder(１）の戻り値)をコールする。
        WEB3MarginContractUnit[] l_contractUnits = l_toOrderManager.createContractUnitByOrder(l_eqTypeOrderUnit);
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
        return l_contractUnits;
    }
}
@
