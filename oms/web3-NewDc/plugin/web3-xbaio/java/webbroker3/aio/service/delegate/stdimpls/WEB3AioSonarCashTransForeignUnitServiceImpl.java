head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金（外貨）UnitServiceImpl(WEB3AioSonarCashTransForeignUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSonarCashTransForeignUpdateInterceptor;
import webbroker3.aio.data.HostForeignCashTransferParams;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransForeignUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (SONAR入出金（外貨）UnitServiceImpl)<BR>
 * SONAR入出金（外貨）UnitService実装クラス<BR>
 *<BR>
 * Plugin時に自動トランザクションTransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignUnitServiceImpl 
    implements WEB3AioSonarCashTransForeignUnitService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransForeignUnitServiceImpl.class);  
    /**
     * (submit注文)<BR>
     * SONARからの注文の登録を行い、新規注文の注文IDを返却する。<BR> 
     *<BR>
     * シーケンス図 <BR>
     *「（SONAR入出金（外貨））submit注文」 参照<BR>
     * @@param l_hostCashTransferForeignParams - (外貨入出金Paramsオブジェクト)<BR>
     * 外貨入出金Paramsオブジェクト<BR>
     * @@return long<BR>
     * @@throws WEB3BaseException
     */
    public long submitOrder(HostForeignCashTransferParams l_hostForeignCashTransferParams)
        throws  WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(HostForeignCashTransferParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_hostForeignCashTransferParams == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //1.1 get顧客(証券会社コード : String, 部店コード : String, 顧客コード : String)
            //顧客オブジェクトを取得する。
            //[引数]
            //証券会社コード： 引数.外貨入出金Params.証券会社コード
            //部店コード： 引数.外貨入出金Params.部店コード
            //顧客コード： 引数.外貨入出金Params.顧客コード
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            WEB3GentradeMainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_hostForeignCashTransferParams.getInstitutionCode(),
                    l_hostForeignCashTransferParams.getBranchCode(),
                    l_hostForeignCashTransferParams.getAccountCode());
            
            //1.2 getInstitution( )
            //証券会社オブジェクトを取得する。
            String l_strInstitutionCode = 
                l_hostForeignCashTransferParams.getInstitutionCode();
            Institution l_institution = 
                l_accountManager.getInstitution(l_strInstitutionCode);
            
            //1.3 getTrader(証券会社 : Institution, 扱者コード : String, 部店コード : String)
            //扱者オブジェクトを取得する。
            //[引数]
            //証券会社： 証券会社オブジェクト
            //扱者コード： 引数.外貨入出金Params.扱者コード
            //部店コード： 引数.外貨入出金Params.部店コード
            String l_strTraderCode = l_hostForeignCashTransferParams.getTraderCode();
            
            // 部店コード： 引数.振替入力通知キューParams.部店コード
            Trader l_trader = null;
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strTraderCode))
            {
                FinObjectManager l_finObjectManager = GtlUtils.getFinObjectManager();
                l_trader = l_finObjectManager.getTrader(
                    l_institution,
                    l_strTraderCode,
                    l_hostForeignCashTransferParams.getBranchCode());
            }
            
            log.debug("扱者オブジェクト" + l_trader);
            //1.4 get商品ID(証券会社 : Institution)
            //入出金用の商品IDを取得する。
            //[引数]
            //証券会社： 証券会社オブジェクト
            TradingModule l_TradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.AIO);
            WEB3AioOrderManager l_orderManager = 
                (WEB3AioOrderManager)l_TradingModule.getOrderManager();
            long l_lngProductId = l_orderManager.getProductId(l_institution);
            
            //1.5 入出金注文内容(扱者 : Trader, 注文種別 : OrderTypeEnum, 
            //振替タイプ : AssetTransferTypeEnum, 商品ID : long, 金額 : double, 
            //記述 : String, 振替予定日 : Date, 決済機@関ID : String, 注文ID : Long)
            //入出金注文内容インスタンスを生成する。
            //[引数]
            //扱者： 扱者オブジェクト
            //注文種別： （以下のとおり）
            //   引数.外貨入出金Params.入出金区分="1"（出金）の場合、1001（出金注文）をセットする。
            //   引数.外貨入出金Params.入出金区分="2"（入金）の場合、1002（入金注文）をセットする。
            OrderTypeEnum l_orderType = null;
            if (WEB3OrderDivDef.CASHOUT.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                log.debug("引数.外貨入出金Params.入出金区分=1（出金）の場合" + l_orderType);
                l_orderType = OrderTypeEnum.CASH_OUT;
            }
            else if (WEB3OrderDivDef.CASHIN.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                log.debug("引数.外貨入出金Params.入出金区分 = 2（入金）の場合" + l_orderType);
                l_orderType = OrderTypeEnum.CASH_IN;
            }
            
            log.debug("注文種別：" + l_orderType);
            //振替タイプ： （以下のとおり）
            //   引数.外貨入出金Params.入出金区分="1"（出金）の場合、2（出金）をセットする。
            //   引数.外貨入出金Params.入出金区分="2"（入金）の場合、1（入金）をセットする。
            AssetTransferTypeEnum l_assetTransferType = null;
            if (WEB3OrderDivDef.CASHOUT.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                log.debug("引数.外貨入出金Params.入出金区分=1（出金）の場合" + l_assetTransferType);
                l_assetTransferType = AssetTransferTypeEnum.CASH_OUT;
            }
            else if (WEB3OrderDivDef.CASHIN.equals(l_hostForeignCashTransferParams.getOrderDiv()))
            {
                log.debug("引数.外貨入出金Params.入出金区分=2（入金）の場合" + l_assetTransferType);
                l_assetTransferType = AssetTransferTypeEnum.CASH_IN;
            }
            
            log.debug("振替タイプ：" + l_assetTransferType);
            //商品ID： get商品ID()の戻り値
            //金額： 引数.外貨入出金Params.振替入出金額
            //記述： null
            //振替予定日： 引数.外貨入出金Params.入出金日
            //決済機@関ID： null
            //注文ID： null
            WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
                l_trader,
                l_orderType,
                l_assetTransferType,
                l_lngProductId,
                l_hostForeignCashTransferParams.getAmount(),
                null,
                l_hostForeignCashTransferParams.getCashTransDate(),
                null,
                null); 

            //1.6 getSubAccount(補助口座タイプ : SubAccountTypeEnum)
            //補助口座オブジェクトを取得する。
            //[引数]
            //補助口座タイプ： 1（預り金口座）
            //================NotFoundException====================  
            SubAccount l_subAccount = 
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            //1.7 createNewOrderId( )
            //新規注文IDを取得する。
            long l_lngNewOrderId = l_orderManager.createNewOrderId();
            
            //1.8 SONAR入出金（外貨）更新インタセプタ(入出金注文内容 : 入出金注文内容)
            //SONAR入出金（外貨）更新インタセプタのインスタンスを生成する。
            //[引数]
            //入出金注文内容： 入出金注文内容オブジェクト
            WEB3AioSonarCashTransForeignUpdateInterceptor 
            l_aioSonarCashTransForeignUpdateInterceptor = 
                new WEB3AioSonarCashTransForeignUpdateInterceptor(
                    l_aioNewOrderSpec);
            
            //1.9 (*) プロパティセット
            //(*) 以下のとおりにインタセプタのプロパティをセットする。
            //インタセプタ.受渡日 = 入出金注文内容.振替予定日
            l_aioSonarCashTransForeignUpdateInterceptor.setDeliveryDate(
                l_aioNewOrderSpec.getEstimatedTransferDate());
            
            //インタセプタ.受注日時 = 引数.外貨入出金Params.タイムスタンプ
            l_aioSonarCashTransForeignUpdateInterceptor.setReceivedDateTime(
                WEB3DateUtility.getDate(
                    l_hostForeignCashTransferParams.getCreatedTimestamp(), "yyyyMMddHHmmss")); 
                    
            //インタセプタ.通貨コード = 引数.外貨入出金Params.通貨コード
            l_aioSonarCashTransForeignUpdateInterceptor.setCurrencyCode(
                l_hostForeignCashTransferParams.getCurrencyCode());
            
            //インタセプタ.入出金金額(円換算額) = 引数.外貨入出金Params.振替入出金円換算額
            l_aioSonarCashTransForeignUpdateInterceptor.setConvertAmount(
                l_hostForeignCashTransferParams.getConvertAmount());
            
            //1.10 setThreadLocalPersistenceEventInterceptor(
            //SONAR入出金（外貨）更新インタセプタ : AioOrderManagerPersistenceEventInterceptor)
            //インタセプタをセットする。
            //[引数]
            //SONAR入出金（外貨）更新インタセプタ： SONAR入出金（外貨）更新インタセプタオブジェクト
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioSonarCashTransForeignUpdateInterceptor);
            
            //1.11 submitNewOrder(補助口座 : SubAccount, 商品タイプ : ProductTypeEnum, 
            //注文内容 : NewOrderSpec, 注文ID : long, パスワード : String, isSkip発注審査 : boolean)
            //注文登録処理を行う。
            //[引数]
            //補助口座： 補助口座オブジェクト
            //商品タイプ： 5（現金）
            //注文内容： 入出金注文内容オブジェクト
            //注文ID： createNewOrderId()の戻り値
            //パスワード： 顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの
            //isSkip発注審査： true
            WEB3Crypt l_webCrypt = new WEB3Crypt();
            OrderSubmissionResult l_submitNewOrderResult =
                l_orderManager.submitNewOrder(
                    l_subAccount,
                    ProductTypeEnum.CASH,
                    l_aioNewOrderSpec,
                    l_lngNewOrderId,
                    l_webCrypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
                    
            if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
            {
                log.debug("注文登録処理を行う Error" +
                    l_submitNewOrderResult.getProcessingResult().getErrorInfo());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);
            } 
            //1.12 return
            //注文IDを返却する。
            log.exiting(STR_METHOD_NAME);
            return l_lngNewOrderId;
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
    }
}
@
