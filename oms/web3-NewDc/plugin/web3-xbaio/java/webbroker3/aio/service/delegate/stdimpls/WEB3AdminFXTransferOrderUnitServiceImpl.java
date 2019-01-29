head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替注文UnitServiceImpl(WEB3AdminFXTransferOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/23 鄭徳懇(中訊) 新規作成
Revesion History : 2007/07/12 趙林鵬(中訊) モデルNo.733
Revesion History : 2007/07/28 孟亜南(中訊) 仕様変更モデル744
Revesion History : 2009/03/11 王志葵(中訊) 仕様変更モデル1115
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.WEB3FXTransferOrderUpdateInterceptor;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferOrderUnitService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioTransferDivDef;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX振替注文UnitServiceImpl)<BR>
 * FX振替注文UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)を指定する。 <BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXTransferOrderUnitServiceImpl implements WEB3AdminFXTransferOrderUnitService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferOrderUnitServiceImpl.class);

   /**
    * @@roseuid 43FC2F9D029F
    */
   public WEB3AdminFXTransferOrderUnitServiceImpl() 
   {
    
   }
   
   /**
    * 振替注文処理を行う。 <BR>
    * <BR>
    * シーケンス図 <BR>
    * 「為替保証金（FX振替注文ＵＬ）振替注文」 参照<BR>
    * @@param l_fxTransferOrderUploadCsv - (FX振替注文アップロードCSV)<BR>
    * @@param l_intLineNumber - (行番号)<BR>
    * @@param l_strAdministratorCode - (管理者コード)<BR>
    * @@param l_institution - (証券会社)<BR>
    * @@param l_strPassword - (暗証番号)<BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException
    * @@roseuid 43D0AA9D030C
    */
   public WEB3GenResponse execute(
       WEB3AdminFXTransferOrderUploadCsv l_fxTransferOrderUploadCsv,
       int l_intLineNumber,
       String l_strAdministratorCode,
       Institution l_institution,
       String l_strPassword) throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " execute(WEB3AdminFXTransferUploadCsv, int, String, Institution, String)";
       log.entering(STR_METHOD_NAME);
       
       if (l_fxTransferOrderUploadCsv == null || l_institution == null)
       {
           log.debug("パラメータ値不正。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "," + STR_METHOD_NAME,
               "パラメータ値不正。");
       }
       
       //1.1 get発注日( )
       Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
       
       //1.2 get顧客(int)
       WEB3GentradeMainAccount l_mainAccount = l_fxTransferOrderUploadCsv.getMainAccount(l_intLineNumber);

       //lock口座(証券会社コード : String, 部店コード :
       //String, 口座コード : String)
       FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
       WEB3GentradeAccountManager l_accManager =
           (WEB3GentradeAccountManager)l_finApp.getAccountManager();
       String l_strInstitutionCode = l_institution.getInstitutionCode();
       String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
       String l_strAccountCode = l_mainAccount.getAccountCode();
       l_accManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

       //getSubAccount(arg0 : SubAccountTypeEnum)
       SubAccount l_subAccount = null;
       try
       {
          l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
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

       //1.5 get新規識別コード(証券会社コード : String, 部店コード : 
       //String, 銘柄タイプ : ProductTypeEnum)
       WEB3HostReqOrderNumberManageService l_hostReqOrderNumMgrService =
           (WEB3HostReqOrderNumberManageService) Services.getService(WEB3HostReqOrderNumberManageService.class); 
       String l_strNewNumber = l_hostReqOrderNumMgrService.getNewNumber(
           l_strInstitutionCode, 
           l_strBranchCode, 
           ProductTypeEnum.CASH);
       
       //1.6 get会社別FXシステム条件(証券会社コード : String, 部店コード : String) 
       WEB3FXDataControlService l_dataControlService = 
           (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
       CompFxConditionParams l_compFxConditionParams = null;
       FxAccountParams l_fxAccountParams = null;
       FxAccountCodeParams l_fxAccountCodeParams = null;
       FxTransferMasterParams l_fxTransferMasterParams = null;
       
       try
       {
           l_compFxConditionParams = 
               l_dataControlService.getCompFxCondition(l_strInstitutionCode, l_strBranchCode);

           //getFX振替条件マスタ(long, String)
           //FX振替条件マスタParams取得する。
           //【引数】
           //FXシステム条件ID　@= 会社別FXシステム条件.FXシステム条件ID
           //振替区分 = 0：入金
           l_fxTransferMasterParams =
               l_dataControlService.getFxTransferMasterParams(
                   l_compFxConditionParams.getFxSystemId(),
                   WEB3AioTransferDivDef.CASHIN);

           //1.7 getFX顧客(証券会社コード : String, 部店コード :
           //String, FXシステムコード : String, 顧客コード : String)
           l_fxAccountParams = l_dataControlService.getFXAccount(
               l_strInstitutionCode,
               l_strBranchCode,
               l_compFxConditionParams.getFxSystemCode(),
               l_strAccountCode);
           
           //1.8 getFX口座番号(証券会社コード : String, 
           //部店コード : String, 顧客コード : String, コース区分 : String)
           l_fxAccountCodeParams = l_dataControlService.getFXAccountCode(
               l_strInstitutionCode, 
               l_strBranchCode, 
               l_strAccountCode, 
               WEB3GftTransStatusCourseDivDef.ONE_COSE);
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
       
       //1.9 GFT依頼電文明細( )
       WEB3FXGftAskingTelegramUnit l_telUnit = new WEB3FXGftAskingTelegramUnit();
       
       //1.10 （*）プロパティセット
       //(*)GFT依頼電文明細に必要なプロパティをセットする
       //（下記以外のプロパティは設定しない）
       //DIR→GFT送信日時 ：現在時刻（システムタイムスタンプ）
       l_telUnit.dirSendTime = 
           WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHHmmss");
       
       //処理区分 ：02(入金）
       l_telUnit.gftOperationDiv = WEB3GftMessageOperationDef.CASH_IN;
       
       //為替保証金口座番号 ：getFX口座番号()の戻り値
       l_telUnit.fxAccountCode = l_fxAccountCodeParams.getFxAccountCode();
       
       //初期ログインID：FX顧客Params.FXログインID
       l_telUnit.fxFirstLoginId = String.valueOf(l_fxAccountParams.getFxLoginId());
       
       //担当区分：会社別FXシステム条件Params.担当区分
       l_telUnit.groupName = l_compFxConditionParams.getGroupName();
           
       //入出金額 ：引数.アップロードCSV.get出金額(引数.行番号)
       double l_dblCashOutAmt = l_fxTransferOrderUploadCsv.getCashOutAmt(l_intLineNumber);
       l_telUnit.cashinoutAmt = WEB3StringTypeUtility.formatNumber(l_dblCashOutAmt);
       
       //会社コード：引数.証券会社.証券会社コード
       l_telUnit.institutionCode = l_strInstitutionCode;
       
       //部店コード：顧客から取得した部店コード
       l_telUnit.branchCode = l_strBranchCode;
                  
       //顧客コード：顧客から取得した顧客コード
       l_telUnit.accountCode = l_strAccountCode;
       
       //識別コード：get新規識別コード()の戻り値
       l_telUnit.requestNumber = l_strNewNumber;
       
       //1.11 createNewOrderId( )
       WEB3AioOrderManager l_orderManager =
           (WEB3AioOrderManager) l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
       long l_lngOrderId = l_orderManager.createNewOrderId();
       
       //1.12 get商品ID(証券会社 : Institution)
       long l_lngProductId = l_orderManager.getProductId(l_institution);

       //is信用口座開設(弁済区分 : String)
       //弁済区分：　@"0"（指定無し）
       boolean l_blnIsMarginAccountEstablished =
           l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

       Date l_datPaymentDate = l_fxTransferOrderUploadCsv.getPaymentDate(l_intLineNumber);

       //暗証番号：　@OpLoginSecurityServiceより、ログインタイプ属性を取得し、
       OpLoginSecurityService l_securityService =
           (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
       OpLoginAdminService l_adminService = (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
       LoginInfo l_loginInfo = l_securityService.getLoginInfo();
       Map l_mapAttributes = l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());

       String l_strAttribute = (String)l_mapAttributes.get(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);

       //・取引パスワード設定 == ”DEFAULT” の場合、引数.暗証番号
       String l_strTradingPassword = null;
       if (WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
       {
           l_strTradingPassword = l_strPassword;
       }
       //取引パスワード設定 == ”取引パスワード使用” の場合
       else if (WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
       {
           //顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの
           WEB3Crypt l_web3Crypt = new WEB3Crypt();
           l_strTradingPassword = l_web3Crypt.decrypt(l_mainAccount.getTradingPassword());
       }

       //顧客が信用口座を開設している（is信用口座開設()==TRUE）場合、処理を行う
       if (l_blnIsMarginAccountEstablished)
       {
           //発注日==出金日の場合、処理を行う
           //発注日 == 出金日（get発注日() == 引数.アップロードCSV.get出金日(引数.行番号)）の場合、処理を行う
           int l_intCompareToDay = WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datPaymentDate);
           if (l_intCompareToDay == 0)
           {
               //submit保証金振替(顧客, Date, double, String)
               //顧客：　@get顧客()の戻り値
               //受渡日：　@引数.アップロードCSV.get出金日(引数.行番号)
               //入金額：　@引数.アップロードCSV.get入出金額(引数.行番号)
               //暗証番号
               //代理入力者：　@null
               WEB3MarginTransferService l_marginTransferService =
                   (WEB3MarginTransferService)Services.getService(WEB3MarginTransferService.class);

               l_marginTransferService.submitMarginTransfer(
                   l_mainAccount,
                   l_datPaymentDate,
                   l_dblCashOutAmt,
                   l_strTradingPassword,
                   null);
           }
       }

       //1.13 入出金注文内容(代理入力者 : Trader, 注文種別 : OrderTypeEnum, 
       //振替タイプ : AssetTransferTypeEnum, 商品ID : long, 金額 : double, 
       //記述 : String, 振替予定日 : Date, 決済機@関ID : String, 注文ID : Long,
       //摘要コード： String, 摘要名： String)
       WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
           null,
           l_fxTransferMasterParams.getOrderType(),
           AssetTransferTypeEnum.CASH_IN,
           l_lngProductId,
           l_dblCashOutAmt,
           null,
           l_datPaymentDate,
           null,
           null,
           l_fxTransferMasterParams.getRemarkCode(),
           l_fxTransferMasterParams.getRemarkName());
       
       //1.14 FX振替注文更新インタセプタ(入出金注文内容 : 入出金注文内容)
       WEB3FXTransferOrderUpdateInterceptor l_orderUpdateInterceptor = 
           new WEB3FXTransferOrderUpdateInterceptor(l_aioNewOrderSpec);
       
       //1.15 （*）プロパティセット
       //(*)以下のとおりにプロパティをセットする。
       //発注日：　@get発注日()の戻り値
       l_orderUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);
       
       //受渡日：　@引数.アップロードCSV.get出金日(引数.行番号)
       l_orderUpdateInterceptor.setDeliveryDate(l_datPaymentDate);
       
       //識別コード：　@get新規識別コード()の戻り値
       l_orderUpdateInterceptor.setOrderRequestNumber(l_strNewNumber);
       
       //MQステータス：　@1(送信済み)
       l_orderUpdateInterceptor.setMQStatus(WEB3MqStatusDef.MAIL_SENDED);
       
       //1.6 submit振替注文(補助口座 : SubAccount, 銘柄タイプ : ProductTypeEnum, 
       //注文種別 : OrderTypeEnum, 注文内容 : NewOrderSpec, 
       //インタセプタ : AioOrderManagerPersistenceInterceptor, 
       //注文ID : long, パスワード : String)

       l_orderManager.submitTransferOrder(
           l_subAccount,
           ProductTypeEnum.CASH,
           l_fxTransferMasterParams.getOrderType(),
           l_aioNewOrderSpec,
           l_orderUpdateInterceptor,
           l_lngOrderId,
           l_strTradingPassword);

       //1.17 余力再計算(補助口座 : 補助口座)
       WEB3TPTradingPowerService l_tradingPowerService = 
           (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
       l_tradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount) l_subAccount);
       
       //1.18 insertGFT振替状況(GFT依頼電文明細 : GFT依頼電文明細, 
       //コース区分 : String, 受渡予定日 : String, 
       //信用振替用識別コード : String, 入出金番号 : String, 入出金一覧取引区分: String)
       l_dataControlService.insertGFTTransferStatus(
           l_telUnit, 
           WEB3GftTransStatusCourseDivDef.ONE_COSE,
           WEB3DateUtility.formatDate(l_datPaymentDate, "yyyyMMdd"),
           null,
           l_fxTransferOrderUploadCsv.getCashInOutNumber(l_intLineNumber),
           l_fxTransferMasterParams.getIoListTradeDiv());
       
       log.exiting(STR_METHOD_NAME);
       return null;
   }
}
@
