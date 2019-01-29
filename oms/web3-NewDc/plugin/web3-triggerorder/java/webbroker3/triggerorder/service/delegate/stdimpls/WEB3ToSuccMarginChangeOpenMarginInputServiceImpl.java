head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeOpenMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引訂正新規建入力サービスImpl(WEB3ToSuccMarginChangeOpenMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17 呉　@鈞(中訊) 新規作成
Revesion History : 2007/01/20 肖志偉(中訊) 仕様変更モデルNo.224
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeInputRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeOpenMarginInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeOpenMarginInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）信用取引訂正新規建入力サービスImpl)<BR>
 * （連続）信用取引訂正新規建入力サービス実装クラス<BR>
 * 
 * @@author 呉　@鈞(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeOpenMarginInputServiceImpl extends 
    WEB3MarginChangeOpenMarginInputServiceImpl implements WEB3ToSuccMarginChangeOpenMarginInputService 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeOpenMarginInputServiceImpl.class);
    
    /**
     * @@roseuid 436ACF7B0213
     */
    public WEB3ToSuccMarginChangeOpenMarginInputServiceImpl() 
    {
     
    }
    
    /**
     * （連続）信用取引訂正新規建入力サービス処理を実施する。<BR>
     * <BR>
     * this.get訂正新規建入力画面()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433B83F30219
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
        if (l_request instanceof WEB3SuccMarginOpenChangeInputRequest)
        {
            l_response = this.getOpenMarginChangeInputScreen((WEB3SuccMarginOpenChangeInputRequest) l_request);
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
     * (get訂正新規建入力画面)<BR>
     * （連続）信用取引訂正新規建の入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引訂正新規建入力サービス）<BR>
     * get訂正新規建入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccMarginOpenChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 433B845A0006
     */
    protected WEB3SuccMarginOpenChangeInputResponse getOpenMarginChangeInputScreen(
        WEB3SuccMarginOpenChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getOpenMarginChangeInputScreen(WEB3SuccMarginOpenChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 get株式予約注文単位(注文ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try 
        {
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error("株式予約注文単位テーブルに該当するデータがありません");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //1.3 reset市場コード(市場コード : String)
        String l_strMarketCode = null;
        try
        {
            l_strMarketCode = l_orderUnitImpl.getMarket().getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("市場テーブルに該当するデータがありません");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //1.4 get親注文の注文単位( )
        EqTypeOrderUnit l_orderUnit = l_orderUnitImpl.getParentOrderUnit();
        
        //1.5 get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //1.6 validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        RsvEqOrderUnitRow l_orderUnitRow = (RsvEqOrderUnitRow) l_orderUnitImpl.getDataSourceObject();
        
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_orderUnitRow.getReserveOrderTradingType(),
            l_orderUnit);
        
        //1.7 get訂正新規建入力画面(リクエストデータ : 信用取引訂正新規建入力リクエスト)
        WEB3SuccMarginOpenChangeInputResponse l_response = (
            WEB3SuccMarginOpenChangeInputResponse) super.getOpenMarginChangeInputScreen(l_request);

        //注文期限区分一覧、及び（出来るまで注文開始日、最終日、祝日一覧）取得
        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond =
            new WEB3GentradeHandlingOrderCond(l_subAccount.getInstitution().getInstitutionCode(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);
            
        //(*)プロパティセット
        //●（連続）信用取引訂正返済入力レスポンス固有のプロパティ
        l_response.succCommonInfo = l_orderUnitImpl.createSuccCommonInfo();
        l_response.priceAdjustmentValueInfo = l_orderUnitImpl.createSuccPriceAdjustmentValueInfo();
        l_response.expirationDateTypeList =
            l_gentradeHandingOrderCond.getHandlingPossibleExpirationDateType();
        
        //●異なる値をセットするプロパティ（再セット）
        l_response.priceCondList = new String[] {WEB3PriceConditionDef.DEFAULT};
        l_response.execCondList = new String[] {WEB3ExecutionConditionDef.NO_CONDITION};
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
     * 
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 433BA7FD0035
     */
    protected EqTypeOrderUnit getChangeOrderUnit(
        WEB3MarginOpenMarginChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getChangeOrderUnit(WEB3MarginOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
     
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try 
        {
            //１）　@株式予約注文単位を取得する。
            // 　@連続注文マネージャImpl.get株式予約注文単位(リクエストデータ.ID)を
            // 　@コールする。
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //2) 株式注文単位オブジェクトを生成する。
        // 　@連続注文マネージャImpl.create株式注文単位(１）の戻り値)を
        // 　@コールし、戻り値を返却する。
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.createEqtypeOrderUnit(l_orderUnitImpl);

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
     * @@roseuid 433BA7FD0054
     */
    protected void validateOrderForChangeability(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateOrderForChangeability(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;
        try
        {
            //１）　@株式予約注文単位を取得する。
            l_orderUnitImpl = l_toOrderManager.getReserveEqtypeOrderUnit(l_orderUnit.getOrderId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
         
        //２）１）の戻り値.validate訂正可能状態()をコールする。
        l_orderUnitImpl.validateOrderForChangeability();

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get新規建可能額)<BR>
     * 新規建可能額を取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 取引余力サービス.get信用新規建可能額～連続注文～<BR>
     * (補助口座, null)をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 433BA7FD0073
     */
    protected double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getMarginTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        //取引余力サービス.get信用新規建可能額～連続注文～
        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        
        double l_dblMarginTradingPower = l_tradingPowerService.getSuccMarginTradingPower(l_subAccount, null);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblMarginTradingPower;
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
     * @@roseuid 433BA7FD00A2
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
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        EqtypeOrderUnitRow l_firstOrderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        Date l_datBizDate = null;
        try
        {
            //注文有効期限取得に使用する、出来るまで注文from日付を返却する
            l_datBizDate =
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().parse(l_firstOrderUnitRow.getBizDate());
        }
        catch (ParseException l_pex)
        {
            log.error("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_pex.getMessage(),
                l_pex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_datBizDate;
    }
}@
