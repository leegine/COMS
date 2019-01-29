head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityManualOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  株式連続注文手動発注UnitServiceImpl(WEB3ToSuccEquityManualOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06 陳琦(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.service.WEB3RlsRequestSenderService;
import webbroker3.triggerorder.WEB3ToRlsCoopDataManager;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3EquityManualUnit;
import webbroker3.triggerorder.message.WEB3ManualCommissionInfoUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityManualOrderUnitService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (株式連続注文手動発注UnitServiceImpl)<BR>
 * 株式連続注文手動発注UnitService実装クラス。<BR>
 * 
 * @@author 陳琦(中訊) <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccEquityManualOrderUnitServiceImpl 
    extends WEB3ToEquityManualOrderUnitServiceImpl
    implements WEB3ToSuccEquityManualOrderUnitService 
{
    
    /**
     * ログオブジェクト
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3ToSuccEquityManualOrderUnitServiceImpl.class);

    /**
     * @@roseuid 4349DEE70222
     */
    public WEB3ToSuccEquityManualOrderUnitServiceImpl() 
    {
     
    }  

    /**
     * (get注文データ)<BR>
     * パラメータ.注文IDに該当する予約注文単位を取得する。<BR> 
     * <BR>
     * １）　@予約注文単位を取得する。 <BR>
     * 　@　@　@連続注文マネージャ.get株式予約注文単位()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[getOrderUnits()に指定する引数] <BR>
     * 　@　@　@　@注文ID：　@パラメータ.注文ID <BR>
     * <BR>
     * <BR>
     * ２）　@該当データが存在しない場合、 <BR>
     * 　@　@　@　@　@nullを返す。 <BR>
     * <BR>
     * 　@　@　@以外の場合、 <BR>
     * 　@　@　@　@　@１）で取得した予約注文単位を返す。 <BR> 
     * @@param l_strOrderId - (注文ID)
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected EqTypeOrderUnit getOrderData(String l_strOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderData(String)";
        log.entering(STR_METHOD_NAME);        
        
        //１）　@予約注文単位を取得する。 
        //      連続注文マネージャ.get株式予約注文単位()をコールする。 
        //
        //　@　@　@　@[getOrderUnits()に指定する引数] 
        //        注文ID：　@パラメータ.注文ID 
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit = null;
            try
            {
                l_rsvEqOrderUnit = (WEB3ToSuccEqTypeOrderUnitImpl) 
                    l_toOrderManager.getReserveEqtypeOrderUnit(
                        Long.parseLong(l_strOrderId));
            }
            catch (NotFoundException l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

        //２）　@該当データが存在しない場合、 
        //         nullを返す。 
        //
        //      以外の場合、 
        //        １）で取得した予約注文単位を返す。 
            log.exiting(STR_METHOD_NAME);
            return l_rsvEqOrderUnit;
            
    }
    
    /**
     * (getUnitレスポンス)<BR>
     * 株式手動発注Unitに連続注文固有のプロパティをセットする。 <BR>
     * <BR>
     * <BR>
     * １）　@予約注文単位から"株式注文単位型"を生成する。 <BR>
     * 　@　@　@（※以下、キャストしたオブジェクトを"注文単位"と表記する。） <BR>
     * <BR>
     * 　@　@１－１）　@注文データを"予約注文単位"にキャストする。 <BR>
     * <BR>
     * 　@　@１－２）　@連続注文マネージャImpl.create株式注文単位()をコールする。<BR> 
     * <BR>
     * 　@　@　@　@　@　@　@　@[create株式注文単位()に設定する引数] <BR>
     * 　@　@　@　@　@　@　@　@予約注文単位：　@予約注文単位 <BR>
     * <BR>
     * ２）　@共通のプロパティに値をセットする。 <BR>
     * 　@　@　@super.getUnitレスポンス()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[getUnitレスポンス()に設定する引数] <BR>
     * 　@　@　@　@注文データ：　@注文単位 <BR>
     * <BR>
     * ３）　@発注状況を取得する。 <BR>
     * 　@　@　@連続注文データ取得サービスImpl.get連続注文発注状況区分()をコールする。<BR> 
     * <BR>
     * 　@　@　@　@[get連続注文発注状況区分()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * <BR>
     * ４）　@手動発注エラーコードを取得する。 <BR>
     * 　@　@　@ルールエンジン連携データマネージャ．get連続注文手動発注エラーコード()を<BR>
     * 　@　@　@コールする。 <BR>
     * <BR>
     * 　@　@　@　@[get連続注文手動発注エラーコード()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * <BR>
     * ５）　@ルールエンジンからの通知データを取得する。 <BR>
     * 　@　@　@ルールエンジン連携データマネージャ．getルールエンジンからの通知データ()を<BR>
     * 　@　@　@コールする。 <BR>
     * <BR>
     * 　@　@　@　@[getルールエンジンからの通知データ()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * 　@　@　@　@条件注文種別：　@"連続注文" <BR>
     * 　@　@　@　@銘柄タイプ：　@ProductTypeEnum.株式 <BR>
     * <BR>
     * ６） 注文単位.注文カテゴリが"現引・現渡注文"以外の場合、<BR>
     * 　@　@　@手数料情報を取得する。<BR>
     * <BR>
     * 　@　@　@this.create手動発注手数料情報()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[create手動発注手数料情報()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * 　@　@　@　@is指値：　@(*1)<BR>
     * 　@　@　@　@　@(*1)予約注文単位.is±指値指定()がtrueの場合、<BR>
     * 　@　@　@　@　@　@　@　@trueをセット。<BR>
     * 　@　@　@　@　@　@　@falseの場合、<BR>
     * 　@　@　@　@　@　@　@　@Not（パラメータ.注文単位.isMarketOrder()）の場合はtrue、<BR>
     *  　@　@　@　@　@　@　@（パラメータ.注文単位.isMarketOrder()）の場合はfalse <BR>
     * <BR>
     * ７）　@注文単位．注文カテゴリ == "返済注文"、または"現引・現渡注文"の場合のみ、<BR>
     * 　@　@　@信用取引建株明細を取得する。 <BR>
     * <BR>
     * 　@　@　@this．create建株明細byOrder()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[create建株明細byOrder()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * <BR>
     * ８）　@処理状況区分を取得する。 <BR>
     * 　@　@　@連続注文データ取得サービスImpl．get処理状況()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get処理状況()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@予約注文単位 <BR>
     * <BR>
     * ９）　@getUnitレスポンス()の戻り値（株式手動発注Unit）に <BR>
     * 　@　@　@連続注文固有のプロパティをセットする。 <BR>
     * <BR>
     * 　@条件注文種別：　@"連続注文"をセット。 <BR>
     * 　@発注状況区分：　@get連続注文発注状況区分()の戻り値 <BR>
     * 　@手動発注エラーコード：　@get連続注文手動発注エラーコード()の戻り値 <BR>
     * 　@時価情報受信時間：　@<BR>
     * 　@　@ルールエンジンからの通知Params．tickヒットタイムスタンプ(*2) <BR>
     * 　@トリガー起動時間：　@<BR>
     * 　@　@ルールエンジンからの通知Params．ルールエンジンファ@イアタイムスタンプ(*2) <BR>
     * 　@発注完了時間：　@ルールエンジンからの通知Params．発注完了タイムスタンプ(*2) <BR>
     * 　@手動発注手数料情報：　@create手動発注手数料情報()の戻り値 <BR>
     * <BR>
     * 　@信用取引建株明細：　@create建株明細byOrder()の戻り値 <BR>
     * 　@処理状況区分：　@get処理状況()の戻り値 <BR>
     * 　@単価調整値：　@注文単位.is±指値指定が"true"の場合、<BR>
     * 　@　@　@　@　@　@　@　@注文単位.単価調整値をセット。<BR>
     * <BR>
     * 　@(*2)getルールエンジンからの通知()の戻り値≠nullの場合セット <BR>
     * <BR>
     * １０）　@プロパティをセットした株式手動発注Unitを返す。 <BR>
     * <BR>
     * @@param l_orderData - (注文データ)
     * @@return WEB3EquityManualUnit
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualUnit getUnitResponse(
        EqTypeOrderUnit l_orderData) throws WEB3BaseException
    {       
        final String STR_METHOD_NAME = " getUnitResponse(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderData == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }
        
        //１）　@予約注文単位から"株式注文単位型"を生成する。 
        //   （※以下、キャストしたオブジェクトを"注文単位"と表記する。） 
        //
        // １－１）　@注文データを"予約注文単位"にキャストする。 
        WEB3ToSuccEqTypeOrderUnitImpl l_toSuccEqTypeOrderUnitImpl = null;
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;
        if (l_orderData instanceof WEB3ToSuccEqTypeOrderUnitImpl)
        {
            l_toSuccEqTypeOrderUnitImpl = 
                (WEB3ToSuccEqTypeOrderUnitImpl) l_orderData;
            l_rsvEqOrderUnitRow = 
                (RsvEqOrderUnitRow)l_orderData.getDataSourceObject();
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
        // １－２）　@連続注文マネージャImpl.create株式注文単位()をコールする。 
        //
        //　@　@　@　@     [create株式注文単位()に設定する引数] 
        //　@　@　@　@     予約注文単位：　@予約注文単位     
        WEB3ToSuccOrderManagerImpl l_toOrderManager =
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        EqTypeOrderUnit l_eqTypeOrderUnit = 
            l_toOrderManager.createEqtypeOrderUnit(l_toSuccEqTypeOrderUnitImpl);
        
        //２）　@共通のプロパティに値をセットする。 
        //   super.getUnitレスポンス()をコールする。 
        //
        //     [getUnitレスポンス()に設定する引数] 
        //     注文データ：　@注文単位 
        WEB3EquityManualUnit l_equityManualUnit = 
            super.getUnitResponse(l_eqTypeOrderUnit);
        
        //３）　@発注状況を取得する。 
        //  連続注文データ取得サービスImpl.get連続注文発注状況区分()をコールする。 
        //
        //    [get連続注文発注状況区分()に設定する引数] 
        //    注文単位：　@注文単位 
                
        WEB3ToSuccDataGettingService l_dataGettingService = 
            (WEB3ToSuccDataGettingService)Services.getService(
                WEB3ToSuccDataGettingService.class);
        
        String l_strToSuccTriggerOrderStatusType = 
            l_dataGettingService.getToSuccTriggerOrderStatusType(l_eqTypeOrderUnit);
        
        //４）　@手動発注エラーコードを取得する。 
        //   ルールエンジン連携データマネージャ．get連続注文手動発注エラーコード()をコールする。 
        //
        //     [get連続注文手動発注エラーコード()に設定する引数] 
        //     注文単位：　@注文単位 
        String l_strToSuccManualOrderErrCode = 
            WEB3ToRlsCoopDataManager.getToSuccManualOrderErrCode(l_eqTypeOrderUnit);
        
        //５）　@ルールエンジンからの通知データを取得する。 
        //   ルールエンジン連携データマネージャ．getルールエンジンからの通知データ()をコールする。 
        //
        //     [getルールエンジンからの通知データ()に設定する引数] 
        //     注文単位：　@注文単位 
        //     条件注文種別：　@"連続注文" 
        //     銘柄タイプ：　@ProductTypeEnum.株式         
        RlsConOrderHitNotifyParams l_rlsConOrderHitNotifyParams = 
            WEB3ToRlsCoopDataManager.getRLSConOrderHitNotifyData(
                l_eqTypeOrderUnit,
                WEB3TriggerOrderTypeDef.SUCC,
                ProductTypeEnum.EQUITY);
        
        //６）　@注文単位.注文カテゴリが"現引・現渡注文"以外の場合、　@
        //　@　@　@　@手数料情報を取得する。
        // 
        //　@　@　@this.create手動発注手数料情報()をコールする。
        //　@　@　@[create手動発注手数料情報()に設定する引数] 
        //　@　@　@注文単位：　@注文単位
        //　@　@　@is指値：　@(*1)
        //　@　@　@　@　@　@(*)予約注文単位.is±指値指定()がtrueの場合、
        //　@　@　@　@　@　@　@　@　@　@trueをセット。
        //　@　@　@　@　@　@　@　@falseの場合、
        //　@　@　@　@　@　@　@　@　@　@Not（パラメータ.注文単位.isMarketOrder()）の場合はtrue、
        // 　@　@　@　@　@　@　@　@　@（パラメータ.注文単位.isMarketOrder()）の場合はfalse
        WEB3ManualCommissionInfoUnit l_manualCommissionInfoUnit = null;
        if (!(OrderCategEnum.SWAP_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg())))
        {
            boolean l_blnIsLimitPrice = false;
            if (l_toSuccEqTypeOrderUnitImpl.isExecPriceOrder())
            {
                l_blnIsLimitPrice = true;
            }
            else
            {
                if (!l_eqTypeOrderUnit.isMarketOrder())
                {
                    l_blnIsLimitPrice = true;
                }                
            }
            
            l_manualCommissionInfoUnit = 
                    this.createManualCommissionInfoUnit(
                        l_eqTypeOrderUnit,
                        l_blnIsLimitPrice);
        }
        
        //７）　@注文単位．注文カテゴリ == "返済注文"、または"現引・現渡注文"の場合のみ、　@　@　@ 
        //   信用取引建株明細を取得する。 
        //
        //   this．create建株明細byOrder()をコールする。 
        //
        //     [create建株明細byOrder()に設定する引数] 
        //     注文単位：　@注文単位 
        WEB3MarginContractUnit[] l_marginContractUnits = null;
        if (OrderCategEnum.CLOSE_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg()) ||
            OrderCategEnum.SWAP_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg()))
        {
            l_marginContractUnits = this.createContractUnitByOrder(l_eqTypeOrderUnit);
        }
        
        //８）　@処理状況区分を取得する。 
        //   連続注文データ取得サービスImpl．get処理状況()をコールする。 
        //
        //     [get処理状況()に設定する引数] 
        //     注文単位：　@予約注文単位 
        String l_strTransactionState = 
            l_dataGettingService.getTransactionState(l_toSuccEqTypeOrderUnitImpl);
             

        //９）　@getUnitレスポンス()の戻り値（株式手動発注Unit）に 
        //      連続注文固有のプロパティをセットする。 
        //
        // 条件注文種別：　@"連続注文"をセット。 
        // 発注状況区分：　@get連続注文発注状況区分()の戻り値 
        // 手動発注エラーコード：　@get連続注文手動発注エラーコード()の戻り値 
        // 時価情報受信時間：　@ルールエンジンからの通知Params．tickヒットタイムスタンプ(*2) 
        // トリガー起動時間：　@ルールエンジンからの通知Params．ルールエンジンファ@イアタイムスタンプ(*2) 
        // 発注完了時間：　@ルールエンジンからの通知Params．発注完了タイムスタンプ(*2) 
        // 
        // 信用取引建株明細：　@create建株明細byOrder()の戻り値 
        // 処理状況区分：　@get処理状況()の戻り値 
        // 単価調整値：　@注文単位.is±指値指定が"true"の場合、
        //　@　@　@　@　@　@　@　@注文単位.単価調整値をセット。
        //
        // (*2)getルールエンジンからの通知()の戻り値≠nullの場合セット 
        l_equityManualUnit.triggerOrderType = WEB3TriggerOrderTypeDef.SUCC;
        l_equityManualUnit.triggerOrderState = l_strToSuccTriggerOrderStatusType;
        l_equityManualUnit.manualOrderErrorCode = l_strToSuccManualOrderErrCode;
        
        if (l_rlsConOrderHitNotifyParams != null)
        {
            l_equityManualUnit.currentPriceInfoAcceptTime = 
                l_rlsConOrderHitNotifyParams.getHitTickTimestamp();
            
            l_equityManualUnit.triggerStartTime = 
                l_rlsConOrderHitNotifyParams.getRlsHitTimestamp();
            
            l_equityManualUnit.orderCompleteTime = 
                l_rlsConOrderHitNotifyParams.getOrderSubmitTimestamp();
            
        }
        l_equityManualUnit.commissionInfo = l_manualCommissionInfoUnit;
        l_equityManualUnit.contractUnits = l_marginContractUnits;
        l_equityManualUnit.transactionStateType = l_strTransactionState;
        
        if (l_toSuccEqTypeOrderUnitImpl.isExecPriceOrder())
        {
            l_equityManualUnit.priceAdjustmentValue = 
                WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getPriceAdjustValue());
        }
        
        //１０）　@プロパティをセットした株式手動発注Unitを返す。       
        return l_equityManualUnit;
    }
    
    /**
     * (create建株明細ByOrder)<BR> 
     * １）　@株式予約注文単位を取得する。  <BR>
     * 　@　@　@連続注文マネージャImpl.get株式予約注文単位()をコールする。  <BR>
     * <BR>
     * 　@　@　@　@[get株式予約注文単位()に指定する引数] <BR>
     * 　@　@　@　@注文ID：　@注文単位.注文ID <BR>
     * <BR>
     * <BR>
     * ２）　@連続注文マネージャImpl.create建株明細ByOrder()をコールし、 <BR>
     * 　@　@　@戻り値を返却する。  <BR>
     * <BR>
     * 　@　@　@　@[get株式予約注文単位()に指定する引数] <BR>
     * 　@　@　@　@株式予約注文単位：　@１）の戻り値 <BR>
     * <BR>
     * 　@　@　@　@※nullが返却された場合、  <BR>
     * 　@　@　@　@　@「予約決済対象建株は別注文により決済済」の例外をスローする。<BR>  
     * <BR>
     *          class :WEB3BusinessLayerException<BR>
     *          tag :  BUSINESS_ERROR_02289<BR>
     * <BR> 
     * @@param l_orderData - (注文単位)
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected WEB3MarginContractUnit[] createContractUnitByOrder(
            EqTypeOrderUnit l_orderData) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractByOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);  
        
        if (l_orderData == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }
        
        //１）　@株式予約注文単位を取得する。  
        //   連続注文マネージャImpl.get株式予約注文単位()をコールする。  
        //
        //     [get株式予約注文単位()に指定する引数] 
        //     注文ID：　@注文単位.注文ID  
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit = null;    
        try
        {
            long l_lngOrderId = l_orderData.getOrderId();
            
            l_rsvEqOrderUnit = (WEB3ToSuccEqTypeOrderUnitImpl) 
                l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //２）　@連続注文マネージャImpl.create建株明細ByOrder()をコールし、 
        //戻り値を返却する。  
        //
        //[get株式予約注文単位()に指定する引数] 
        //    株式予約注文単位：　@１）の戻り値 
        //
        //    ※nullが返却された場合、  
        //　@     「予約決済対象建株は別注文により決済済」の例外をスローする。 
        WEB3MarginContractUnit[] l_contractUnits = 
            l_toOrderManager.createContractUnitByOrder(l_rsvEqOrderUnit);
        
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
    
    /**
     * (sendRLSRequest)<BR>
     * ルールエンジンからの通知テーブルへのINSERTメソッドをコールする。<BR>
     * １）　@補助口座を取得する。<BR>  
     * 　@　@　@拡張アカウントマネージャー.getSubAccount()をコールする。<BR> 
     *<BR> 
     * 　@　@　@　@[getSubAccount()に指定する引数]<BR> 
     * 　@　@　@　@顧客ID：　@注文データ.getAccountId()の戻り値<BR> 
     * 　@　@　@　@補助口座ID：　@注文データ.getSubAccountId()の戻り値<BR> 
     *<BR> 
     * ２）　@注文データを予約注文単位にキャストする。<BR> 
     * ３）　@ルールエンジンからの通知テーブルへINSERTする。  <BR>
     * 　@　@　@WEB3RlsRequestSenderServiceImpl.sendManualSubmitConOrder()をコールする。<BR> 
     *<BR> 
     * 　@　@　@　@[sendManualSubmitConOrder()に指定する引数]<BR> 
     * 　@　@　@　@発注者ログインID：　@パラメータ.発注者ログインID<BR> 
     * 　@　@　@　@通知経路：　@パラメータ.通知経路 <BR>
     * 　@　@　@　@補助口座：　@getSubAccount()の戻り値<BR>  
     * 　@　@　@　@条件付注文タイプ：　@条件注文種別."連続注文"<BR> 
     * 　@　@　@　@注文の銘柄タイプ：　@銘柄タイプ."株式"<BR>  
     * 　@　@　@　@注文ID：　@予約注文単位.注文ID<BR>  
     * 　@　@　@　@親注文の銘柄タイプ：　@銘柄タイプ."株式"<BR> 
     * 　@　@　@　@親注文の注文ID：　@予約注文単位.親注文の注文ID<BR> 
     * 　@　@　@　@発注順番：　@0<BR>  
     *<BR> 
     * @@param l_orderData - (注文データ)
     * @@param l_submitterLoginId - (発注者ログインID)
     * @@param l_strNotifyType - (通知経路)
     * @@throws WEB3BaseException
     */
    protected void sendRLSRequest(
        EqTypeOrderUnit l_orderData,
        Long l_submitterLoginId,
        String l_strNotifyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " sendRLSRequest(EqTypeOrderUnit, Long, String)";
        log.entering(STR_METHOD_NAME);
 
        if (l_orderData == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        } 
        
        //１）　@補助口座を取得する。  
        //  拡張アカウントマネージャー.getSubAccount()をコールする。 
        //
        //    [getSubAccount()に指定する引数] 
        //    顧客ID：　@注文データ.getAccountId()の戻り値 
        //    補助口座ID：　@注文データ.getSubAccountId()の戻り値 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_orderData.getAccountId(),
                    l_orderData.getSubAccountId());
          
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //２）　@注文データを予約注文単位にキャストする。         
        WEB3ToSuccEqTypeOrderUnitImpl l_toSuccEqTypeOrderUnitImpl = null;
        if (l_orderData instanceof WEB3ToSuccEqTypeOrderUnitImpl)
        {
            l_toSuccEqTypeOrderUnitImpl = (WEB3ToSuccEqTypeOrderUnitImpl) l_orderData;
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

        //３）　@ルールエンジンからの通知テーブルへINSERTする。  
        //   WEB3RlsRequestSenderServiceImpl.sendManualSubmitConOrder()をコールする。 
        //
        //     [sendManualSubmitConOrder()に指定する引数] 
        //     発注者ログインID：　@パラメータ.発注者ログインID 
        //     通知経路：　@パラメータ.通知経路 
        //     補助口座：　@getSubAccount()の戻り値  
        //     条件付注文タイプ：　@条件注文種別."連続注文" 
        //     注文の銘柄タイプ：　@銘柄タイプ."株式"  
        //     注文ID：　@予約注文単位.注文ID  
        //     親注文の銘柄タイプ：　@銘柄タイプ."株式" 
        //     親注文の注文ID：　@予約注文単位.親注文の注文ID 
        //     発注順番：　@0    
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
            (RsvEqOrderUnitRow) l_toSuccEqTypeOrderUnitImpl.getDataSourceObject();
        
        long l_lngParentOrderId = l_rsvEqOrderUnitRow.getParentOrderId();
        WEB3RlsRequestSenderService l_requestSenderService =
            (WEB3RlsRequestSenderService) Services.getService(
                WEB3RlsRequestSenderService.class);
        
        l_requestSenderService.sendManualSubmitConOrder(
            l_submitterLoginId,
            l_strNotifyType,
            l_subAccount,
            Integer.parseInt(WEB3TriggerOrderTypeDef.SUCC),
            ProductTypeEnum.EQUITY,
            new Long(l_toSuccEqTypeOrderUnitImpl.getOrderId()),
            ProductTypeEnum.EQUITY,
            new Long(l_lngParentOrderId),
            0);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set取引カレンダコンテキスト)
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * <BR>
     * １）　@部店オブジェクトを取得。 <BR>
     * 　@　@　@拡張アカウントマネージャ.getBranch()をコールする。 <BR>
     * <BR>
     * 　@　@　@[getBranch()に設定する引数] <BR>
     * 　@　@　@arg0：　@注文データ.getBranchId()の戻り値 <BR>
     * <BR>
     * ２）　@証券会社オブジェクトを取得。 <BR>
     * 　@　@　@getBranch()の戻り値.getInstitution()をコールする。 <BR>
     * <BR>
     * ３）　@市場オブジェクトを取得する。<BR>
     * 　@　@３－１）　@予約注文単位Rowを生成する。<BR>
     * 　@　@　@　@　@　@　@　@注文データ.getDataSourceObject()をコールする。<BR>
     * <BR>
     * 　@　@３－２）　@拡張金融オブジェクトマネージャ.getMarket()をコールする。<BR>
     * <BR>
     *　@　@　@　@　@　@　@　@　@[getMarket()に設定する引数]<BR>
     *　@　@　@　@　@　@　@　@　@予約注文単位Row.getMarketId()<BR>
     * <BR>
     * ４）　@取引カレンダコンテキストに内容をセットする。    <BR>
     * 　@－パラメータ.注文データの内容より取引時間コンテキストの <BR>
     * 　@　@　@プロパティをセットする。  <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     * 　@　@　@getInstitution()の戻り値.getInstitutionCode() <BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     * 　@　@　@getBranch()の戻り値.getBranchCode() <BR>
     * 　@取引カレンダコンテキスト.市場コード = <BR>
     * 　@　@　@getMarket()の戻り値.getMarketCode()<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = (*1) <BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = (*2) <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = (*3) <BR>
     * <BR>
     * 　@(*1)受付時間区分 <BR>
     * 　@　@　@・注文カテゴリ(注文データ.getOrderCateg())が”現引・現渡注文”の場合、<BR>
     * 　@　@　@　@　@　@”現引・現渡”をセットする。 <BR>
     * 　@　@　@・注文カテゴリが上記以外の場合、 <BR>
     * 　@　@　@　@　@　@”株式・信用”をセットする。 <BR>
     * <BR>
     * 　@(*2)注文受付商品 <BR>
     * 　@　@　@・注文カテゴリ（注文データ.getOrderCateg()）が”現物注文”の場合、<BR> 
     * 　@　@　@　@　@　@”株式”をセットする。 <BR>
     * 　@　@　@・注文カテゴリが上記以外の場合、 <BR>
     * 　@　@　@　@　@　@”信用”をセットする。 <BR>
     * <BR>
     * 　@(*3)注文受付トランザクション <BR>
     * 　@　@　@・注文種別 == （”現物買注文”または”新規買建注文”）の場合、 <BR>
     * 　@　@　@　@　@”買付（新規建買）”をセットする。 <BR>
     * 　@　@　@・注文種別 == （”現物売注文”または”新規売建注文”）の場合、 <BR>
     * 　@　@　@　@　@”売付（新規建売）”をセットする。 <BR>
     * 　@　@　@・注文カテゴリが”返済”の場合、"返済"をセットする。  <BR>
     * 　@　@　@・注文カテゴリが”現引現渡”の場合、"現引現渡"をセットする。  <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて    <BR>
     * 　@　@　@取引時間コンテキストをセットする。    <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH    <BR>
     * <BR>
     * ５）　@受付日時、日付ロールをセットする。    <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param l_orderData - (注文データ)<BR>
     * @@throws WEB3BaseException<BR>
     */
    protected void setTradingClendarContext(EqTypeOrderUnit l_orderData) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingClendarContext(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderData == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        } 

        //１）　@部店オブジェクトを取得。 
        //　@拡張アカウントマネージャ.getBranch()をコールする。
        //　@[getBranch()に設定する引数] 
        //　@arg0：　@注文データ.getBranchId()の戻り値 
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        Branch l_branch = null;
        
        try
        {
            l_branch = l_accountManager.getBranch(l_orderData.getBranchId());           
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@証券会社オブジェクトを取得。 
        //　@　@getBranch()の戻り値.getInstitution()をコールする。 
        Institution l_institution = l_branch.getInstitution();
        
        //市場オブジェクトを取得する。
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
            (RsvEqOrderUnitRow)l_orderData.getDataSourceObject();
            
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
             
        long l_lngMarketId = l_rsvEqOrderUnitRow.getMarketId();        
        WEB3GentradeMarket l_market = null;
        
        try
        {
            l_market = 
                (WEB3GentradeMarket)l_finObjManager.getMarket(l_lngMarketId);           
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //４）　@取引カレンダコンテキストに内容をセットする。    
        //－パラメータ.注文データの内容より取引時間コンテキストの 
        //　@　@プロパティをセットする。  
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        //　@取引カレンダコンテキスト.証券会社コード = getInstitution()の戻り値.getInstitutionCode() 
        l_context.setInstitutionCode(l_institution.getInstitutionCode());
        
        //　@取引カレンダコンテキスト.部店コード = getBranch()の戻り値.getBranchCode()
        l_context.setBranchCode(l_branch.getBranchCode());
        
        //　@取引カレンダコンテキスト.市場コード = getMarket()の戻り値.getMarketCode() 
        l_context.setMarketCode(l_market.getMarketCode());
        
        //　@取引カレンダコンテキスト.受付時間区分 = (*1) 
        //　@(*1)受付時間区分 
        //　@・注文カテゴリ(注文データ.getOrderCateg())が”現引・現渡注文”の場合、 
        //　@　@　@”現引・現渡”をセットする。 
        
        if (OrderCategEnum.SWAP_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
        }
        //  ・注文カテゴリが上記以外の場合、 
        //　@　@　@”株式・信用”をセットする。
        else
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        }      
        
        //　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //　@取引カレンダコンテキスト.注文受付商品 = (*2) 
        //　@(*2)注文受付商品 
        //　@　@・注文カテゴリ（注文データ.getOrderCateg()）が”現物注文”の場合、 
        //　@　@　@　@”株式”をセットする。 

        if (OrderCategEnum.ASSET.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        //　@  ・注文カテゴリが上記以外の場合、 
        //　@　@　@　@”信用”をセットする。
        else
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        }        
        
        //　@取引カレンダコンテキスト.注文受付トランザクション = (*3) 
        
        //　@(*3)注文受付トランザクション 
        //　@・注文種別 == （”現物買注文”または”新規買建注文”）の場合、         
        //　@　@    ”買付（新規建買）”をセットする。 
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderData.getOrderType()) ||
            OrderTypeEnum.MARGIN_LONG.equals(l_orderData.getOrderType()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
        }
        
        //  ・注文種別 == （”現物売注文”または”新規売建注文”）の場合、 
        //　@　@    ”売付（新規建売）”をセットする。 
        else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderData.getOrderType()) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_orderData.getOrderType()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        }
        
        //  ・注文カテゴリが”返済”の場合、"返済"をセットする。  
        else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CLOSE_MARGIN);
        }
        
        //  ・注文カテゴリが”現引現渡”の場合、"現引現渡"をセットする。 
        else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.SWAP_MARGIN);
        }
        
        //  －ThreadLocalSystemAttributesRegistry.setAttribute( )にて    
        //　@　@取引時間コンテキストをセットする。    
        //    設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH    

        //５）　@受付日時、日付ロールをセットする。    
        //　@－取引時間管理.setTimestamp()をコールする。  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
    
        //取引時間管理
        WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

        log.exiting(STR_METHOD_NAME);
    }

}
@
