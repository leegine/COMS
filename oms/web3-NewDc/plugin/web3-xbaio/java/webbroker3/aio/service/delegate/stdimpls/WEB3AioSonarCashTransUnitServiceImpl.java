head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金UnitServiceImpl(WEB3AioSonarCashTransUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 黄建 (中訊) 新規作成
                   2004/10/26 屈陽 (中訊) レビュー
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSonarCashTransUpdateInterceptor;
import webbroker3.aio.data.HostCashTransferParams;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (SONAR入出金UnitServiceImpl)<BR>
 * SONAR入出金UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioSonarCashTransUnitServiceImpl 
    implements WEB3AioSonarCashTransUnitService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransUnitServiceImpl.class);  
    
    /**
     * (submit注文)<BR>
     * SONARからの注文の登録を行い、新規注文の注文IDを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（SONAR入出金）submit注文」 参照<BR>
     * @@param l_hostCashTransferParams - (入出金Params)<BR>
     * 入出金Paramsオブジェクト<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@return long
     * throws WEB3BaseException
     * @@roseuid 41009F530148
     */
    public long submitOrder(HostCashTransferParams l_hostCashTransferParams)
        throws  WEB3BaseException
    {
         
        final String STR_METHOD_NAME = 
            "submitOrder(HostCashTransferParams l_hostCashTransferParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_hostCashTransferParams == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //========================FinApp=============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //アカウントマネージャ取得する
        AccountManager l_accMgr = l_finApp.getAccountManager();
        
        //１.１）InstitutionImpl(証券会社コード : String)
        //証券会社オブジェクトを取得する。
        //[引数] 
        //証券会社コード： 引数.入出金Params.証券会社コード 
        
        //証券会社コードを取得する
        String l_strInstitutionCode = l_hostCashTransferParams.getInstitutionCode();
        log.debug("証券会社コードを取得する ===========" + l_strInstitutionCode);
        try
        {
            //===========================NotFoundException==================== 
            Institution l_institution = l_accMgr.getInstitution(l_strInstitutionCode);
            
            //１.２）TraderImpl(証券会社 : Institution, 扱者コード : String, 部店コード : String)
            //代理入力者オブジェクトを取得する。 
            //[引数] 
            //証券会社：証券会社オブジェクト
            //扱者コード： 引数.入出金Params.扱者コード 
            //部店コード： 引数.入出金Params.部店コード 
            
            //扱者コードを取得する
            String l_strTraderCode = l_hostCashTransferParams.getTraderCode();
            
            //部店コードを取得する
            String l_strBranchCode = l_hostCashTransferParams.getBranchCode();
            
            //===========================NotFoundException====================
            FinObjectManager l_finObjMgr = GtlUtils.getFinObjectManager();
            Trader l_trader = null;
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strTraderCode))
            {
                l_trader = l_finObjMgr.getTrader(
                    l_institution, 
                    l_strTraderCode, 
                    l_strBranchCode);
            }
                    
            log.debug("扱者コードを取得する ================" + l_strTraderCode);    
            
            //１.３）get商品ID(Institution)
            //入出金用の商品IDを取得する。
            //[引数] 証券会社： 証券会社オブジェクト
            
            //AIO注文マネージャを取得する 
            WEB3AioOrderManager l_AioOrderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();     
            
            //入出金用の商品IDを取得する
            long l_lngProductId = l_AioOrderManager.getProductId(l_institution);
            log.debug("入出金用の商品IDを取得する=============" + l_lngProductId);
            
            //１.４）
            //入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
            //long, double, String, Date, String, long)
            //入出金注文内容インスタンスを生成する。
               //[引数]
               //代理入力者： 扱者オブジェクト
               //注文種別： （以下のとおり）
               //引数.入出金Params.入出金区分="1"（出金）の場合、1001（出金注文）をセットする。
               //引数.入出金Params.入出金区分="2"（入金）の場合、1002（入金注文）をセットする。
               //振替タイプ： （以下のとおり）
               //引数.入出金Params.入出金区分="1"（出金）の場合、2（出金）をセットする。
               //引数.入出金Params.入出金区分="2"（入金）の場合、1（入金）をセットする。
               //商品ID： get商品ID()の戻り値
               //金額： 引数.入出金Params.入出金金額
               //記述： null
               //振替予定日： 引数.入出金Params.入出金日
               //決済機@関ID： null
               //注文ID： null
            
            //注文種別を取得する
            OrderTypeEnum l_orderType = null;
            
            //振替タイプを取得する
            AssetTransferTypeEnum l_assetTransferType = null;
            
            //引数.入出金Params.入出金区分="1"（出金）の場合、1001（出金注文）をセットする。
            //引数.入出金Params.入出金区分="1"（出金）の場合、2（出金）をセットする。
            if ((WEB3OrderDivDef.CASHOUT).equals(l_hostCashTransferParams.getOrderDiv()))
            {
                l_orderType = OrderTypeEnum.CASH_OUT;
                l_assetTransferType = AssetTransferTypeEnum.CASH_OUT;
            }
            else 
            {
                //引数.入出金Params.入出金区分="2"（入金）の場合、1002（入金注文）をセットする。
                //引数.入出金Params.入出金区分="2"（入金）の場合、1（入金）をセットする。
                if (((WEB3OrderDivDef.CASHIN).equals(l_hostCashTransferParams.getOrderDiv())))
                {
                    l_orderType = OrderTypeEnum.CASH_IN;
                    l_assetTransferType = AssetTransferTypeEnum.CASH_IN;
                }
            }
            
            //入出金注文内容インスタンスを生成する
            WEB3AioNewOrderSpec l_aioNewOrderSpec =
                new WEB3AioNewOrderSpec(
                    l_trader, 
                    l_orderType, 
                    l_assetTransferType, 
                    l_lngProductId, 
                    l_hostCashTransferParams.getAmount(), 
                    null,
                    l_hostCashTransferParams.getCashTransDate(),
                    null,
                    null);   
            
            //１.５） MainAccountImpl(証券会社ID : long, 部店コード : String, 顧客コード : String)
            //顧客オブジェクトを取得する。
            //[引数]<BR>
            //証券会社ID： 証券会社.getInstitutionId()の戻り値
            //部店コード： 引数.入出金Params.部店コード
            //顧客コード： 引数.入出金Params.顧客コード
            
            //証券会社IDを取得する
            long l_lngInstitutionId = l_institution.getInstitutionId();
            
            //顧客コードを取得する
            String l_strMainAccountCode = l_hostCashTransferParams.getAccountCode();
            
            //========================NotFoundException======================
            MainAccount l_mainAccount = 
                l_accMgr.getMainAccount(
                    l_lngInstitutionId,
                    l_strBranchCode,
                    l_strMainAccountCode);     
          
            //１.６）getSubAccount(補助口座タイプ : SubAccountTypeEnum)
            //補助口座オブジェクトを取得する。
            //[引数] 補助口座タイプ： 1（預り金口座）
            //================NotFoundException====================  
            SubAccount l_subAccount = 
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            //１.７）createNewOrderId( )            
            //新規注文IDを取得する。
            long l_lngNewOrderId = l_AioOrderManager.createNewOrderId();
            log.debug("新規注文IDを取得する ======== " + l_lngNewOrderId);
            
            //１.８）SONAR入出金更新インタセプタ(入出金注文内容)
            //SONAR入出金更新インタセプタのインスタンスを生成する。
            //[引数]入出金注文内容： 入出金注文内容オブジェクト
            WEB3AioSonarCashTransUpdateInterceptor 
                l_aioSonarCashTransUpdateInterceptor = 
                    new WEB3AioSonarCashTransUpdateInterceptor(
                        l_aioNewOrderSpec);
            
            //１.９）(*) 以下のとおりにインタセプタのプロパティをセットする。
            //インタセプタ.受渡日 = 入出金注文内容.振替予定日        
            l_aioSonarCashTransUpdateInterceptor.setDeliveryDate(
                l_aioNewOrderSpec.getEstimatedTransferDate());  
            
            //インタセプタ.受注日時 = 入出金Params.タイムスタンプ
            l_aioSonarCashTransUpdateInterceptor.setReceivedDateTime(
                WEB3DateUtility.getDate(
                    l_hostCashTransferParams.getCreatedTimestamp(), "yyyyMMddHHmmss"));     
            
            //１.１０）setThreadLocalPersistenceEventInterceptor(
            //SONAR入出金更新インタセプタ : AioOrderManagerPersistenceEventInterceptor)
            //[引数] SONAR入出金更新インタセプタ： 
             //SONAR入出金更新インタセプタオブジェクト
            l_AioOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioSonarCashTransUpdateInterceptor);
            
            //１.１１）submitNewOrder(SubAccount, ProductTypeEnum, NewOrderSpec,
            // long, 論理ビュー::java::lang::String, boolean)
            //注文登録処理を行う。
            //[引数]
            //補助口座： 補助口座オブジェクト
            //商品タイプ： 5（現金）
            //注文内容： 入出金注文内容オブジェクト
            //注文ID： createNewOrderId()の戻り値
            //パスワード： 顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの
            //isSkip発注審査： true
            
            //文字列の暗号化と復号を行うクラス
            WEB3Crypt l_webCrypt = new WEB3Crypt();
            OrderSubmissionResult l_submitNewOrderResult =
                l_AioOrderManager.submitNewOrder(
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
                throw new WEB3SystemLayerException(
                    l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);
            }           
            
            //１.１ ２） return
            //注文IDを返却す
            log.exiting(STR_METHOD_NAME);  
            return l_lngNewOrderId;
        }
        catch (NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);  
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
    }
}
@
