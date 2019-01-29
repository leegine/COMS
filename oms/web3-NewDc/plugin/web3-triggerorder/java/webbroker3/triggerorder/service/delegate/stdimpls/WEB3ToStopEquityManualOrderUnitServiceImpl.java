head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityManualOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  株式逆指値注文手動発注UnitServiceImpl(WEB3ToStopEquityManualOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06 李俊(中訊) 新規作成
                   2006/11/29 李俊(中訊) 仕様変更 モデルNo.202
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.service.WEB3RlsRequestSenderService;
import webbroker3.triggerorder.WEB3ToRlsCoopDataManager;
import webbroker3.triggerorder.message.WEB3EquityManualUnit;
import webbroker3.triggerorder.message.WEB3ManualCommissionInfoUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityManualOrderUnitService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株式逆指値注文手動発注UnitServiceImpl)<BR>
 * 株式逆指値注文手動発注UnitService実装クラス。<BR>
 * 
 * @@author 李俊(中訊) <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToStopEquityManualOrderUnitServiceImpl 
    extends WEB3ToEquityManualOrderUnitServiceImpl
    implements WEB3ToStopEquityManualOrderUnitService 
{
    
    /**
     * ログオブジェクト
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3ToStopEquityManualOrderUnitServiceImpl.class);

    /**
     * @@roseuid 4349DEE70222
     */
    public WEB3ToStopEquityManualOrderUnitServiceImpl() 
    {
     
    }
    
    /**
     * (getUnitレスポンス)<BR>
     * 株式手動発注Unitに逆指値注文固有のプロパティをセットする。 <BR>
     * <BR>
     * <BR>
     * １）　@注文データを"株式注文単位型"にキャストする。 <BR>
     * 　@　@（※以下、キャストしたオブジェクトを"注文単位"と表記する。） <BR>
     * <BR>
     * ２）　@共通のプロパティをセットする。 <BR>
     * 　@　@　@super.getUnitレスポンス()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[getUnitレスポンス()に設定する引数] <BR>
     * 　@　@　@　@注文データ：　@１）で生成した注文単位 <BR>
     * <BR>
     * ３）　@逆指値発注状況区分を取得する。 <BR>
     * 　@　@　@株式データアダプタ.get逆指値発注状況区分()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get発注状況区分()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * <BR>
     * ４）　@逆指値注文手動発注エラーコードを取得する。 <BR>
     * 　@　@　@ルールエンジン連携データマネージャ.get逆指値手動発注エラーコード()を<BR>
     * 　@　@　@コールする。 <BR>
     * <BR>
     * 　@　@　@　@[get逆指値手動発注エラーコード()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * <BR>
     * ５）　@ルールエンジンからの通知データを取得する。 <BR>
     * 　@　@　@ルールエンジン連携データマネージャ.getルールエンジンからの<BR>
     * 　@　@　@　@通知データ()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[getルールエンジンからの通知データ()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * 　@　@　@　@条件注文種別：　@”逆指値注文” <BR>
     * 　@　@　@　@銘柄タイプ：　@ProductTypeEnum.株式 <BR>
     * <BR>
     * ６） 手数料情報を取得する。<BR>
     * <BR>
     * 　@　@　@this.create手動発注手数料情報()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[create手動発注手数料情報()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * 　@　@　@　@is指値：　@Not（パラメータ.注文単位.isMarketOrder()）の場合はtrue、<BR>
     *  　@　@　@　@　@　@　@　@（パラメータ.注文単位.isMarketOrder()）の場合はfalse <BR>
     * <BR>
     * ７）　@注文単位．注文カテゴリ == "返済注文"の場合のみ <BR>
     * 　@　@　@信用取引建株明細を取得する。 <BR>
     * <BR>
     * 　@　@　@this.create建株明細byOrder()をコールする。 <BR>
     * 　@　@　@ <BR>
     * 　@　@　@　@[create建株明細byOrder()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * <BR>
     * ８）　@getUnitレスポンス()の戻り値（株式手動発注Unit）に <BR>
     * 　@　@　@逆指値注文固有のプロパティをセットする。 <BR>
     * <BR>
     * 　@条件注文種別：　@"逆指値注文"をセット。 <BR>
     * 　@発注条件演算子：　@注文単位.発注条件演算子 <BR>
     * 　@発注条件単価：　@注文単位.逆指値基準値 <BR>
     * 　@発注状況区分：　@get逆指値発注状況区分()の戻り値 <BR>
     * 　@手動発注エラーコード：　@get逆指値手動発注エラーコード()の戻り値 <BR>
     * 　@時価情報受信時間：　@<BR>
     * 　@　@ルールエンジンからの通知Params.tickヒットタイムスタンプ(*1) <BR>
     * 　@トリガー起動時間：　@<BR>
     * 　@　@ルールエンジンからの通知Params.ルールエンジンファ@イアタイムスタンプ(*1) <BR>
     * 　@発注完了時間：<BR>
     * 　@　@ルールエンジンからの通知Params.発注完了タイムスタンプ(*1) <BR>
     * 　@手動発注手数料情報：　@create手動発注手数料情報()の戻り値 <BR>
     * 　@信用取引建株明細：　@create建株明細byOrder()の戻り値 <BR>
     * <BR>
     * 　@(*1)getルールエンジンからの通知()の戻り値≠nullの場合セット <BR>
     * <BR>
     * ９）　@プロパティをセットした株式手動発注Unitを返す。 <BR>
     * <BR>  
     * @@param l_orderData - (注文データ)
     * @@return WEB3EquityManualUnit
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualUnit getUnitResponse(
        EqTypeOrderUnit l_orderData) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnitResponse(EqTypeOrderUnit)";
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
        //１）　@注文データを"株式注文単位型"にキャストする。
        //（※以下、キャストしたオブジェクトを"注文単位"と表記する。）
        EqTypeOrderUnit l_eqTypeOrderUnit = l_orderData;
        
        //２）　@共通のプロパティをセットする。 
        //super.getUnitレスポンス()をコールする。        
        //　@　@[getUnitレスポンス()に設定する引数] 
        //　@注文データ：　@１）で生成した注文単位
        WEB3EquityManualUnit l_eqtypeMunualUnit =  
            super.getUnitResponse(l_eqTypeOrderUnit);

        //３）　@逆指値発注状況区分を取得する。 
        //株式データアダプタ.get逆指値発注状況区分()をコールする。
        //　@　@　@　@[get発注状況区分()に設定する引数] 
        //　@　@　@　@注文単位：　@注文単位 
        String l_strStopTriggerOrderStatus = 
            WEB3EquityDataAdapter.getStopTriggerOrderStatusType(l_eqTypeOrderUnit);
        
        //４）　@逆指値注文手動発注エラーコードを取得する。 
        //ルールエンジン連携データマネージャ.get逆指値手動発注エラーコード()
        // をコールする。 
        // [get逆指値手動発注エラーコード()に設定する引数] 
        //　@注文単位：　@注文単位
        String l_strStopManualOrderErrCode =
            WEB3ToRlsCoopDataManager.getStopManualOrderErrCode(l_eqTypeOrderUnit);
        
        //５）　@ルールエンジンからの通知データを取得する。 
        //ルールエンジン連携データマネージャ.getルールエンジンからの通知データ()をコールする。 
        //[getルールエンジンからの通知データ()に設定する引数] 
        //注文単位：　@注文単位 
        //　@条件注文種別：　@”逆指値注文” 
        //　@銘柄タイプ：　@ProductTypeEnum.株式 
        RlsConOrderHitNotifyParams l_rlsConOrderHitNotifyParams= 
            WEB3ToRlsCoopDataManager.getRLSConOrderHitNotifyData(
                l_eqTypeOrderUnit,
                WEB3TriggerOrderTypeDef.STOP,
                ProductTypeEnum.EQUITY);
        
        //　@手数料情報を取得する。
        //　@this.create手動発注手数料情報()をコールする。 
        //　@　@[create手動発注手数料情報()に設定する引数] 
        //　@　@注文単位：　@注文単位 
        //　@　@is指値：　@Not（パラメータ.注文単位.isMarketOrder()）の場合はtrue、
        // 　@　@　@　@　@　@（パラメータ.注文単位.isMarketOrder()）の場合はfalse
        boolean l_blnIsLimitPrice = false;
        if (!(l_eqTypeOrderUnit.isMarketOrder()))
        {
            l_blnIsLimitPrice = true;
        }
        WEB3ManualCommissionInfoUnit l_manualCommissionInfoUnit =
                this.createManualCommissionInfoUnit(
                    l_eqTypeOrderUnit,
                    l_blnIsLimitPrice);
        
        // ７）　@注文単位．注文カテゴリ == "返済注文"の場合のみ 
        //信用取引建株明細を取得する。 
        //this.create建株明細byOrder()をコールする。 
        //[create建株明細byOrder()に設定する引数] 
        //注文単位：　@注文単位 
        WEB3MarginContractUnit[] l_marginContractUnits = null;
        if (OrderCategEnum.CLOSE_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg()))
        {
            l_marginContractUnits = 
                this.createContractUnitByOrder(l_eqTypeOrderUnit);
        }
        //８）　@getUnitレスポンス()の戻り値（株式手動発注Unit）に 
        //逆指値注文固有のプロパティをセットする。 
        //条件注文種別：　@"逆指値注文"をセット。
        l_eqtypeMunualUnit.triggerOrderType = WEB3TriggerOrderTypeDef.STOP;
       
        //発注条件演算子：　@注文単位.発注条件演算子
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = 
            (EqtypeOrderUnitRow) l_eqTypeOrderUnit.getDataSourceObject(); 
        l_eqtypeMunualUnit.condOperator = 
            l_eqtypeOrderUnitRow.getOrderCondOperator();
       
        //発注条件単価：　@注文単位.逆指値基準値
        l_eqtypeMunualUnit.orderCondPrice = 
            WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitRow.getStopOrderPrice());
       
        //発注状況区分：　@get逆指値発注状況区分()の戻り値 
        l_eqtypeMunualUnit.triggerOrderState = l_strStopTriggerOrderStatus;
        
        //手動発注エラーコード：　@get逆指値手動発注エラーコード()の戻り値
        l_eqtypeMunualUnit.manualOrderErrorCode = l_strStopManualOrderErrCode;
        
        //時価情報受信時間：　@ルールエンジンからの通知Params.tickヒットタイムスタンプ(*1) 
        //トリガー起動時間：　@ルールエンジンからの通知Params.ルールエンジンファ@イアタイムスタンプ(*1) 
        //発注完了時間：　@ルールエンジンからの通知Params.発注完了タイムスタンプ(*1) 
        //信用取引建株明細：　@create建株明細byOrder()の戻り値 
        //(*1)getルールエンジンからの通知()の戻り値≠nullの場合セット
        if (l_rlsConOrderHitNotifyParams != null)
        {
            l_eqtypeMunualUnit.currentPriceInfoAcceptTime = 
                l_rlsConOrderHitNotifyParams.getHitTickTimestamp();
            l_eqtypeMunualUnit.triggerStartTime = 
                l_rlsConOrderHitNotifyParams.getRlsHitTimestamp();
            l_eqtypeMunualUnit.orderCompleteTime = 
                l_rlsConOrderHitNotifyParams.getOrderSubmitTimestamp();
                
        }
        l_eqtypeMunualUnit.commissionInfo = l_manualCommissionInfoUnit;
        l_eqtypeMunualUnit.contractUnits = l_marginContractUnits;
        
        log.exiting(STR_METHOD_NAME);
        
        //９）　@プロパティをセットした株式手動発注Unitを返す。
        return  l_eqtypeMunualUnit;
    }
    
    /**
     * (sendRLSRequest)<BR>
     * ルールエンジンからの通知テーブルへのINSERTメソッドをコールする。<BR> 
     *  １）　@補助口座を取得する。 <BR> 
     * 　@　@　@拡張アカウントマネージャー.get補助口座()をコールする。<BR>  
     * <BR> 
     * 　@　@　@　@[getSubAccount()に指定する引数]<BR> 
     * 　@　@　@　@顧客ID：　@注文データ.getAccountId()の戻り値  
     * 　@　@　@　@補助口座ID：　@注文データ.getSubAccountId()の戻り値<BR>  
     * <BR> 
     * ２）　@ルールエンジンからの通知テーブルへINSERTする。<BR>   
     * 　@　@　@WEB3RlsRequestSenderServiceImpl.sendManualSubmitConOrder()<BR>
     * 　@　@　@をコールする。<BR>  
     * <BR> 
     * 　@　@　@　@[sendManualSubmitConOrder()に指定する引数]<BR>  
     * 　@　@　@　@発注者ログインID：　@パラメータ.発注者ログインID<BR>  
     * 　@　@　@　@通知経路：　@パラメータ.通知経路<BR>  
     * 　@　@　@　@補助口座：　@getSubAccount()の戻り値  <BR> 
     * 　@　@　@　@条件付注文タイプ：　@条件注文種別."逆指値注文"<BR>  
     * 　@　@　@　@注文の銘柄タイプ：　@銘柄タイプ."株式"<BR>  
     * 　@　@　@　@注文ID：　@注文データ.注文ID<BR>   
     * 　@　@　@　@親注文の銘柄タイプ：　@null<BR>   
     * 　@　@　@　@親注文の注文ID：　@null<BR>   
     * 　@　@　@　@発注順番：　@0<BR> 
     * @@param l_orderData - (注文データ)
     * @@param l_submitterLoginId - (発注者ログインID)
     * @@param l_strNotifyType - (通知経路)
     * @@throws WEB3BaseException
     */
    protected void sendRLSRequest(
        EqTypeOrderUnit l_orderData,
        Long l_submitterLoginId,
        String l_strNotifyType) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "sendRLSRequest(EqTypeOrderUnit,Long,String)";
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

        //  １）　@補助口座を取得する。 <BR> 
        //　@拡張アカウントマネージャー.get補助口座()をコールする。。
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
            log.error("補助口座テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // ２）　@ルールエンジンからの通知テーブルへINSERTする
        
        // 　@　@　@　@[sendManualSubmitConOrder()に指定する引数] 
        //　@　@　@　@発注者ログインID：　@パラメータ.発注者ログインID  
        //　@　@　@　@通知経路：　@パラメータ.通知経路  
        //　@　@　@　@補助口座：　@getSubAccount()の戻り値   
        //　@　@　@　@条件付注文タイプ：　@条件注文種別."逆指値注文"  
        //　@　@　@　@注文の銘柄タイプ：　@銘柄タイプ."株式"
        //　@　@　@　@注文ID：　@注文データ.注文ID  
        //　@　@　@　@親注文の銘柄タイプ：　@null
        //　@　@　@　@親注文の注文ID：　@null
        //　@　@　@　@発注順番：　@0
        WEB3RlsRequestSenderService l_requestSenderService = 
            (WEB3RlsRequestSenderService) Services.getService(WEB3RlsRequestSenderService.class);
        
        l_requestSenderService.sendManualSubmitConOrder(
            l_submitterLoginId,
            l_strNotifyType,
            l_subAccount,
            Integer.parseInt(WEB3TriggerOrderTypeDef.STOP),
            ProductTypeEnum.EQUITY,
            new Long(l_orderData.getOrderId()),
            null,
            null,
            0);
        log.exiting(STR_METHOD_NAME);
    }        
}
 

@
