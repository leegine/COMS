head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferOrderUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替注文アップロードサービスImpl(WEB3AdminFXTransferOrderUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/27 鄭徳懇(中訊) 新規作成
                 : 2006/03/07 鄭徳懇 (中訊) 仕様変更・モデル513
                 : 2006/04/10 山下(SRA)　@仕様変更・モデル525          
Revesion History : 2008/09/05 王志葵 (中訊) 仕様変更・モデル970,971,972
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.define.WEB3UploadStateDef;
import webbroker3.aio.message.WEB3AdminFXDuplicateOrderUnit;
import webbroker3.aio.message.WEB3AdminFXErrorOrderUnit;
import webbroker3.aio.message.WEB3AdminFXTransferUploadCancelRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadCancelResponse;
import webbroker3.aio.message.WEB3AdminFXTransferUploadCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXTransferUploadConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXTransferUploadInputRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadInputResponse;
import webbroker3.aio.message.WEB3FXUploadHistoryUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferOrderUnitService;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferOrderUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX振替注文アップロードサービスImpl)<BR>
 * FX振替注文アップロードサービス実装クラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXTransferOrderUploadServiceImpl implements WEB3AdminFXTransferOrderUploadService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminFXTransferOrderUploadServiceImpl.class);

   /**
    * @@roseuid 43FC2ED7035B
    */
   public WEB3AdminFXTransferOrderUploadServiceImpl() 
   {
    
   }
   
   /**
    * FX振替注文アップロードサービス処理を行う。<BR>
    * <BR>
    * リクエストの型によって、<BR>
    * <BR>
    *   getアップロード画面()<BR>
    *   validateアップロードファ@イル()<BR>
    *   submitアップロード()<BR>
    *   undoアップロード()<BR>
    * <BR>
    * のメソッドをコールする。<BR>
    * <BR>
    * @@param l_request - (リクエストデータ)<BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException
    * @@roseuid 43C603C50063
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
       if (l_request instanceof WEB3AdminFXTransferUploadInputRequest)
       {
           l_response = this.getUploadScreen((WEB3AdminFXTransferUploadInputRequest) l_request);
       }
       else if (l_request instanceof WEB3AdminFXTransferUploadConfirmRequest)
       {
           l_response = this.validateUploadFile((WEB3AdminFXTransferUploadConfirmRequest) l_request);
       }
       else if (l_request instanceof WEB3AdminFXTransferUploadCompleteRequest)
       {
           l_response = this.submitUpload((WEB3AdminFXTransferUploadCompleteRequest) l_request);
       }
       else if (l_request instanceof WEB3AdminFXTransferUploadCancelRequest)
       {
           l_response = this.undoUpload((WEB3AdminFXTransferUploadCancelRequest) l_request);
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
    * (getアップロード画面)<BR>
    * アップロード画面表示処理を行う。<BR>
    * <BR>
    * シーケンス図<BR>
    * 「為替保証金（FX振替注文ＵＬ）getアップロード画面」 参照<BR>
    * @@param l_request - (リクエストデータ)<BR>
    * @@return WEB3AdminFXTransferUploadInputResponse
    * @@throws WEB3BaseException
    * @@roseuid 43C6051601EF
    */
   protected WEB3AdminFXTransferUploadInputResponse getUploadScreen(WEB3AdminFXTransferUploadInputRequest l_request)
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " getUploadScreen(WEB3AdminFXTransferUploadInputRequest)";
       log.entering(STR_METHOD_NAME);
       
       //1.1 getInstanceFromログイン情報( )
       WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
       //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
       l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE, true);
       
       //1.3 validate注文受付可能( )
       WEB3GentradeTradingTimeManagement.validateOrderAccept();
       
       //1.4 FX振替注文アップロードCSV( )
       WEB3AdminFXTransferOrderUploadCsv l_orderUploadCsv = new WEB3AdminFXTransferOrderUploadCsv();
       
       //1.5 get最新アップロード履歴(データキー : long)
       AdministratorUploadRow l_row = 
           l_orderUploadCsv.getLatestUploadAction(l_admin.getInstitution().getInstitutionId());
       
       //1.6 アップロード履歴が存在する場合処理実施
       WEB3FXUploadHistoryUnit l_unit = null;
       if (l_row != null)
       {
           //1.6.1  FXアップロード履歴明細( )
           l_unit = new WEB3FXUploadHistoryUnit();
           
           //1.6.2 （*1）プロパティセット
           //（*1）FXアップロード履歴明細メッセージオブジェクトプロパティに以下の値をセットする。
           //アップロード処理状態区分：
           //　@（アップロード行.アップロード終了日時 == nullの場合）　@”アップロード中”
           //　@（アップロード行.アップロード終了日時 != nullの場合）　@”アップロード済”
           if (l_row.getUploadEndTimestamp() == null)
           {
               l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOADING;
           }
           else
           {
               l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOAD_COMPLETE;
           }
           
           //アップロード件数：　@アップロード行.アップロード件数
           l_unit.uploadNumber = String.valueOf(l_row.getUploadCount());
           //アップロード開始日時：　@アップロード行.アップロード開始日時
           l_unit.uploadStartDate = l_row.getUploadStartTimestamp();
           //アップロード終了日時：　@アップロード行.アップロード終了日時
           l_unit.uploadEndDate = l_row.getUploadEndTimestamp();
           //FXエラー番号：　@アップロード行.メッセージコード
           l_unit.fxErrorId = l_row.getMessageCode();
       }
       
       //1.7 createResponse( )
       WEB3AdminFXTransferUploadInputResponse l_response = 
           (WEB3AdminFXTransferUploadInputResponse) l_request.createResponse();
       
       //1.8（*2）プロパティセット
       //（*2）レスポンスデータプロパティに以下の通り、値をセットする。
       //アップロード履歴一覧：
       //　@アップロード履歴が存在する場合、FX振替注文アップロード履歴明細オブジェクト。
       //　@アップロード履歴が存在しない場合、null。
       l_response.uploadHistoryUnit = l_unit;
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (validateアップロードファ@イル)<BR>
    * アップロード確認処理を行う。<BR>
    * <BR>
    * シーケンス図<BR>
    * 「為替保証金（FX振替注文ＵＬ）validateアップロードファ@イル」 参照<BR>
    * @@param l_request - (リクエストデータ)<BR>
    * @@return WEB3AdminFXTransferUploadConfirmResponse
    * @@throws WEB3BaseException
    * @@roseuid 43C6064C0223
    */
   protected WEB3AdminFXTransferUploadConfirmResponse validateUploadFile(
       WEB3AdminFXTransferUploadConfirmRequest l_request) throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " validateUploadFile(WEB3AdminFXTransferUploadConfirmRequest)";
       log.entering(STR_METHOD_NAME);

       //1.1 validate( )
       l_request.validate();
       
       //1.2 getInstanceFromログイン情報( )
       WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
       //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
       l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE, true);
       
       //1.4 validate注文受付可能( )
       WEB3GentradeTradingTimeManagement.validateOrderAccept();
       
       //1.5 FX振替注文アップロードCSV( )
       WEB3AdminFXTransferOrderUploadCsv l_orderUploadCsv = new WEB3AdminFXTransferOrderUploadCsv();
       
       //1.6 validate同時アップロード(アップロードＩＤ : long)
       l_orderUploadCsv.validateSameTimeUpload(null);
       
       //1.7 get管理者コード( )
       String l_strAdminCode = l_admin.getAdministratorCode();
       
       //1.8 get発注日( )
       Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
       
       //1.9 ArrayList( )
       ArrayList l_lisDuplicateOrders = new ArrayList();
       
       //1.10 ArrayList( )
       ArrayList l_lisErrorOrders = new ArrayList();
       
       //1.11 saveアップロード開始(データキー : long, 
       //備考１ : String, 備考２ : String, 
       //備考３ : String, 更新者コード : String)
       l_orderUploadCsv.saveUpLoadStart(
           l_admin.getInstitution().getInstitutionId(), 
           null, 
           null, 
           null, 
           l_strAdminCode);
       
       //1.12 リクエストデータ.アップロードファ@イル[]の各要素毎のLOOP処理
       int l_intUploadFilelength = l_request.uploadFile.length;
       int l_intLineNumber = 0;
       for (int i = 0; i < l_intUploadFilelength; i++)
       {
           try
           {
               //1.12.1 add明細行(明細行文字列 : String)
               l_intLineNumber = l_orderUploadCsv.addRow(l_request.uploadFile[i]);
           }
           //1.12.2 add明細行()で例外がスローされた場合
           catch (WEB3SystemLayerException l_ex)
           {
               //1.12.2.1 saveアップロードエラー(エラー情報 : ErrorInfo)
               l_orderUploadCsv.saveUploadError(l_ex.getErrorInfo());
               
               //1.12.2.2 catchした例外を上位に再スローする
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00992,
                       this.getClass().getName() + STR_METHOD_NAME,
                       l_request.uploadFile[i]);
           }
           
           //1.12.3 空行の場合（add明細行()の戻り値 < 0）、
           //当該要素の処理を中断（continue;）
           if (l_intLineNumber < 0)
           {
               continue;
           }
           
           //1.12.4 validate明細行(int, String)
           try
           {
               l_orderUploadCsv.validateDetailsLine(l_intLineNumber,l_request.uploadFile[i]);
           }
           //1.12.5 validate明細行()で例外がスローされた場合
           catch (WEB3BaseException l_ex)
           {
               //利用者コード
               String l_strUserCode = l_orderUploadCsv.getUserCode(l_intLineNumber);
               //入出金番号
               String l_strCashInOutdNumber = l_orderUploadCsv.getCashInOutNumber(l_intLineNumber);
      
               //1.12.5.1 saveアップロードエラー(エラー情報 : ErrorInfo)
               l_orderUploadCsv.saveUploadError(l_ex.getErrorInfo());
               
               //1.12.5.2 catchした例外を上位に再スローする
               log.error(l_ex.getErrorMessage(), l_ex);
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   l_ex.getErrorInfo(),
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_strUserCode + "," + l_strCashInOutdNumber,
                   l_ex);
           }
           
           try
           {
               //1.12.6 validate重複注文(int)
               l_orderUploadCsv.validateDuplicateOrder(l_intLineNumber);
           }
           //1.12.7 validate重複注文()で例外がスローされた場合、
           //追加した行を削除し当該要素の処理を中断（continue;）
           catch (WEB3BaseException l_ex)
           {
               //1.12.7.1 管理者・FX振替重複注文( )
               WEB3AdminFXDuplicateOrderUnit l_duplicateOrderUnit = new WEB3AdminFXDuplicateOrderUnit();
               
               //1.12.7.2 プロパティセット
               //利用者コード
               l_duplicateOrderUnit.userCode = l_orderUploadCsv.getUserCode(l_intLineNumber);
               //入出金番号
               l_duplicateOrderUnit.cashInoutNumber = l_orderUploadCsv.getCashInOutNumber(l_intLineNumber);
               
               //1.12.7.3 add(arg0 : Object)
               l_lisDuplicateOrders.add(l_duplicateOrderUnit);
               
               //1.12.7.4 delete明細行(行番号 : int)
               l_orderUploadCsv.deleteRow(l_intLineNumber);
               
               continue;
           }
           
           //1.12.8 get顧客(int)
           WEB3GentradeMainAccount l_mainAccount = l_orderUploadCsv.getMainAccount(l_intLineNumber);
           SubAccount l_subAccount = null;
           CompFxConditionParams l_conditionParams = null;
           try
           {
               //1.12.9 getSubAccount(arg0 : SubAccountTypeEnum)
               l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
               
               //1.12.10 get会社別FXシステム条件(証券会社コード : String, 
               //部店コード : String)
               WEB3FXDataControlService l_fxDataControlService = 
                   (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
               l_conditionParams = l_fxDataControlService.getCompFxCondition(
                   l_admin.getInstitutionCode(),
                   l_subAccount.getMainAccount().getBranch().getBranchCode());
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
           
           try
           {
               //1.12.11 validateFX振替可能(補助口座 : SubAccount, 
               //FXシステムコード : String)
               FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
               TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
               WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_tradingModule.getOrderManager();
               l_orderManager.validateFXTransferPossible(l_subAccount, l_conditionParams.getFxSystemCode());
               
               //1.12.12 validate振替可能回数(補助口座 : SubAccount, 
               //発注日 : Date, 注文カテゴリ : OrderCategEnum)
               l_orderManager.validateTransferPossibleCount(l_subAccount, l_datOrderBizDate, OrderCategEnum.FX);
           }
           //1.12.13 validateFX振替可能()、validate振替可能回数()で
           //例外がスローされた場合
           catch (WEB3BaseException l_ex)
           {
               //1.12.13.1 管理者・FX振替エラー注文( )
               WEB3AdminFXErrorOrderUnit l_errorOrderUnit = new WEB3AdminFXErrorOrderUnit();
               
               //1.12.13.2 プロパティセット
               //利用者コード
               l_errorOrderUnit.userCode = l_orderUploadCsv.getUserCode(l_intLineNumber);
               //入出金番号
               l_errorOrderUnit.cashInoutNumber = l_orderUploadCsv.getCashInOutNumber(l_intLineNumber);
               
               //1.12.13.3 add(arg0 : Object)
               l_lisErrorOrders.add(l_errorOrderUnit);
               
               //1.12.13.4 delete明細行(行番号 : int)
               l_orderUploadCsv.deleteRow(l_intLineNumber);
           }
       }
       
       //1.13 toArray( )
       WEB3AdminFXDuplicateOrderUnit[] l_duplicateOrderUnits = 
           new WEB3AdminFXDuplicateOrderUnit[l_lisDuplicateOrders.size()];
       l_lisDuplicateOrders.toArray(l_duplicateOrderUnits);
       
       //1.14 toArray( )
       WEB3AdminFXErrorOrderUnit[] l_errorOrderUnits = new WEB3AdminFXErrorOrderUnit[l_lisErrorOrders.size()];
       l_lisErrorOrders.toArray(l_errorOrderUnits);
       
       //1.15 get明細行数( )
       int l_intRowCount = l_orderUploadCsv.getRowCount();
       
       //1.16 getアップロードＩＤ( )
       long l_lngUploadId = l_orderUploadCsv.getAdministratorUploadId();
       
       //1.17 saveアップロードTemp( )
       l_orderUploadCsv.saveUploadTemp();
       
       //1.18 createResponse( )
       WEB3AdminFXTransferUploadConfirmResponse l_response = 
           (WEB3AdminFXTransferUploadConfirmResponse) l_request.createResponse();
       
       //1.19 (*)プロパティセット
       //（*）レスポンスデータプロパティに以下の通り、値をセットする。
       //アップロード件数：　@get明細行数()の戻り値
       l_response.uploadNumber = String.valueOf(l_intRowCount);
       //アップロードID：　@getアップロードID()の戻り値
       l_response.uploadId = String.valueOf(l_lngUploadId);
       //FX振替重複注文：　@FX振替重複注文.toArray()
       l_response.duplicateOrderList = l_duplicateOrderUnits;
       //FX振替エラー注文：　@FX振替エラー注文.toArray()
       l_response.errorOrderList = l_errorOrderUnits;
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (submitアップロード)<BR>
    * アップロード完了処理を行う。<BR>
    * <BR>
    * シーケンス図<BR>
    * 「為替保証金（FX振替注文ＵＬ）submitアップロードファ@イル」 参照<BR>
    * @@param l_request - (リクエストデータ)<BR>
    * @@return WEB3AdminFXTransferUploadCompleteResponse
    * @@throws WEB3BaseException
    * @@roseuid 43C6071301CF
    */
   protected WEB3AdminFXTransferUploadCompleteResponse submitUpload(WEB3AdminFXTransferUploadCompleteRequest l_request)
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " submitUpload(WEB3AdminFXTransferUploadCompleteRequest)";
       log.entering(STR_METHOD_NAME);
       
       //1.1 validate( )
       l_request.validate();
       
       //1.2 getInstanceFromログイン情報( )
       WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
       //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
       l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE, true);
       
       //1.4 validate取引パスワード(パスワード : String)
       l_admin.validateTradingPassword(l_request.password);              
       
       //1.5 validate注文受付可能( )
       WEB3GentradeTradingTimeManagement.validateOrderAccept();

       //1.6 FX振替注文アップロードCSV( )
       WEB3AdminFXTransferOrderUploadCsv l_orderUploadCsv = new WEB3AdminFXTransferOrderUploadCsv();
       
       //1.7 validate同時アップロード(アップロードＩＤ : long)
       l_orderUploadCsv.validateSameTimeUpload(Long.valueOf(l_request.uploadId));
       
       //1.8 get管理者コード( )
       String l_strAdminCode = l_admin.getAdministratorCode();
       
       //1.9 get証券会社( )
       Institution l_institution = l_admin.getInstitution();
       
       //1.10 setDataFromアップロードTemp(アップロードＩＤ : long)
       l_orderUploadCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadId));
       
       //1.11 get明細行数( )
       int l_intRowCount = l_orderUploadCsv.getRowCount();
       
       //1.12 ArrayList( )
       ArrayList l_lisErrors = new ArrayList();
       
       //1.13 明細行の数分Loop処理
       WEB3AdminFXTransferOrderUnitService l_service = 
           (WEB3AdminFXTransferOrderUnitService) Services.getService(WEB3AdminFXTransferOrderUnitService.class);
       
       for (int i = 0; i < l_intRowCount; i++)
       {
           try
           {
               //1.13.1 execute(FX振替注文アップロードCSV, int, String, 
               //Institution, String)
               l_service.execute(l_orderUploadCsv, i, l_strAdminCode, l_institution, l_request.password);
           }
           //1.13.2 executeで例外がスローされた場合
           catch (WEB3BaseException l_ex)
           {
               //1.13.2.1 管理者・FX振替エラー注文( )
               WEB3AdminFXErrorOrderUnit l_errorOrderUnit = new WEB3AdminFXErrorOrderUnit();
               
               //1.13.2.2 プロパティセット
               //利用者コード：アップロードCSV.get利用者コード(index)
               l_errorOrderUnit.userCode = l_orderUploadCsv.getUserCode(i);
               //入出金番号：アップロードCSV.get入出金番号（index）
               l_errorOrderUnit.cashInoutNumber = l_orderUploadCsv.getCashInOutNumber(i);
               
               //1.13.2.3 add(arg0 : Object)
               l_lisErrors.add(l_errorOrderUnit);
               
               //1.13.2.4 delete明細行(行番号 : int)
               l_orderUploadCsv.deleteRow(i);
               
               //1.13.2.5 ループのindexとループ数（明細行数）を1マイナスする。
               l_intRowCount--;
               i--;
           }
       }
       
       //1.14 toArray( )
       WEB3AdminFXErrorOrderUnit[] l_errorOrderUnits = new WEB3AdminFXErrorOrderUnit[l_lisErrors.size()];
       l_lisErrors.toArray(l_errorOrderUnits);
       
       //1.15 get明細行数( )
       l_intRowCount = l_orderUploadCsv.getRowCount();
       
       //1.16 saveアップロード終了( )
       l_orderUploadCsv.saveUploadEnd();
       
       //1.17 deleteアップロードTemp( )
       l_orderUploadCsv.deleteUploadTemp();
       
       //1.18 createResponse( )
       WEB3AdminFXTransferUploadCompleteResponse l_response = 
           (WEB3AdminFXTransferUploadCompleteResponse) l_request.createResponse();
       
       //1.19 (*)プロパティセット
       //(*)以下のとおり、プロパティをセットする。
       //アップロード件数： get明細行数()の戻り値
       l_response.uploadNumber = String.valueOf(l_intRowCount);
       //FXエラー注文一覧： 管理者・FXエラー注文の配
       l_response.errorOrderList = l_errorOrderUnits;
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (undoアップロード)<BR>
    * アップロード中止処理を行う。<BR>
    * <BR>
    * シーケンス図<BR>
    * 「為替保証金（FX振替注文ＵＬ）undoアップロード」 参照<BR>
    * @@param l_request - (リクエストデータ)<BR>
    * @@return WEB3AdminFXTransferUploadCancelResponse
    * @@throws WEB3BaseException
    * @@roseuid 43C607BA0302
    */
   protected WEB3AdminFXTransferUploadCancelResponse undoUpload(WEB3AdminFXTransferUploadCancelRequest l_request) 
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " undoUpload(WEB3AdminFXTransferUploadCancelRequest)";
       log.entering(STR_METHOD_NAME);
       
       //1.1 FX振替注文アップロードCSV(long)
       WEB3AdminFXTransferOrderUploadCsv l_uploadCsv = 
           new WEB3AdminFXTransferOrderUploadCsv(Long.parseLong(l_request.uploadId));
       
       //1.2 deleteアップロードTemp( )
       l_uploadCsv.deleteUploadTemp();
       
       //1.3 saveアップロード中止( )
       l_uploadCsv.saveUploadStop();
       
       //1.4 createResponse( )
       WEB3AdminFXTransferUploadCancelResponse l_response = 
           (WEB3AdminFXTransferUploadCancelResponse) l_request.createResponse();
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
}
@
