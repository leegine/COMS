head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : バーチャル口座入金ULサービスImpl(WEB3AdminAioVirtualAccCashinULServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 肖志偉 (中訊) 新規作成
                 : 2006/06/12 呉艶飛 (中訊) モデルNo.592     
                 : 2006/06/22 山田昌和 (SCS) モデルNo.596
                 : 2006/08/31 山田昌和 (SCS) モデルNo.638
                 : 2006/10/18 車進 　@(中訊)  ＤＢ更新仕様　@No.123
                 : 2006/10/20 車進 　@(中訊)  モデル　@No.669
                 : 2006/10/18 車進 　@(中訊)  モデル　@No.671
                 : 2006/11/14 徐宏偉 (中訊)  ＤＢ更新仕様　@No.130
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.aio.WEB3AdminAioVirtualAccCashinULCsv;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.aio.data.BankDepositNotifyRow;
import webbroker3.aio.define.WEB3AioBankDepositNotifyCashTransferDivDef;
import webbroker3.aio.define.WEB3AioBankDepositNotifyTradeDivDef;
import webbroker3.aio.define.WEB3UploadStateDef;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCancelRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCancelResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULInputRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULInputResponse;
import webbroker3.aio.message.WEB3AioUploadHistoryUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioVirtualAccCashinULService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DataLoadDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (バーチャル口座入金ULサービスImpl)<BR>
 * バーチャル口座入金ULサービス実装クラス<BR>
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioVirtualAccCashinULServiceImpl 
    implements WEB3AdminAioVirtualAccCashinULService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioVirtualAccCashinULServiceImpl.class);    
    
    /**
     * バーチャル口座入金アップロード処理を行う。<BR>  
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>  
     * <BR>
     * ○ 引数のリクエストデータが、バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄの場合<BR>  
     * 　@－getアップロード画面()をコールする。  <BR>
     * <BR>
     * ○ 引数のリクエストデータが、バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄの場合<BR>  
     * 　@－validateアップロードファ@イル()をコールする。  <BR>
     * <BR>
     * ○ 引数のリクエストデータが、バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄの場合<BR>  
     * 　@－submitアップロードファ@イル()をコールする。  <BR>
     * <BR>
     * ○ 引数のリクエストデータが、バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄの場合<BR>  
     * 　@－undoアップロード()をコールする。  <BR>
     * <BR>
     * ※サービスメソッドにて例外が発生した場合は、<BR>  
     * 　@例外オブジェクトの追加文字列（WEB3BaseException.errorMessage）<BR>  
     * をレスポンスデータ.errorMessageにセットする。 <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4455B4FC03D6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (l_request instanceof WEB3AdminAioVirtualAccCashinULInputRequest)
        {
            WEB3AdminAioVirtualAccCashinULInputResponse l_response = 
                getUploadScreen((WEB3AdminAioVirtualAccCashinULInputRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAioVirtualAccCashinULConfirmRequest)
        {
            WEB3AdminAioVirtualAccCashinULConfirmResponse l_response =
                validateUploadFile((WEB3AdminAioVirtualAccCashinULConfirmRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAioVirtualAccCashinULCompleteRequest)
        {
            WEB3AdminAioVirtualAccCashinULCompleteResponse l_response =
                submitUploadFile((WEB3AdminAioVirtualAccCashinULCompleteRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAioVirtualAccCashinULCancelRequest)
        {
            WEB3AdminAioVirtualAccCashinULCancelResponse l_response = 
                undoUploadFile((WEB3AdminAioVirtualAccCashinULCancelRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.debug("Error[入力値が不正です]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

    }
    
    /**
     * (getアップロード画面)<BR>
     * バーチャル口座入金アップロード画面表示処理を行う。<BR> 
     * <BR>
     * シーケンス図<BR> 
     * 「入出金サービスモデル（管理者）（バーチャル口座入金ＵＬ）<BR>
     * getアップロード画面」参照。<BR>
     * @@param l_request - (バーチャル口座入金入力リクエスト)<BR>
     * バーチャル口座入金入力リクエスト
     * @@return WEB3AdminAioVirtualAccCashinULInputResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4455B35D01B6
     */
    protected WEB3AdminAioVirtualAccCashinULInputResponse getUploadScreen(
        WEB3AdminAioVirtualAccCashinULInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getUploadScreen(" +
        "WEB3AdminAioVirtualAccCashinULInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1)getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2)validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数] 
        //機@能カテゴリコード ： 出金請求（バーチャル口座入金アップロード（B0103）） 
        //is更新 ： true 
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.VIRTUAL_ACC_CASHIN_UPLOAD, true);
        
        //1.3)validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4)CSVデータモデル( )
        //アップロードファ@イルモデルを生成する。
        WEB3AdminAioVirtualAccCashinULCsv l_aioVirtualAccCashinULCsv =
            new WEB3AdminAioVirtualAccCashinULCsv();
        
        //1.5)getアップロード最新履歴(Stirng, String, String, long)
        //取得したアップロード履歴の一覧から『備考1』に『中止』 
        //を含まない最新のデータを取得する。 
        //
        //[getアップロード最新履歴()の引数] 
        //証券会社コード ： this.get証券会社コード() 
        //アップロードファ@イルＩＤ ： this.getアップロードファ@イルＩＤ() 
        //銘柄タイプ ： this.get銘柄タイプ() 
        //データキー：0
        WEB3FXDataControlService l_service = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        AdministratorUploadRow l_row =
            (AdministratorUploadRow)l_service.getUploadNewHistory(
            l_aioVirtualAccCashinULCsv.getInstitutionCode(),
            l_aioVirtualAccCashinULCsv.getUploadFileId(),
            l_aioVirtualAccCashinULCsv.getProductType().intValue() + "",
            0);

        //1.6)*1アップロード履歴が存在する場合、処理実施
        WEB3AioUploadHistoryUnit l_unit = null;       
        
        if (l_row != null)
        {           
            l_unit = new WEB3AioUploadHistoryUnit();
            
            //1.6.1)プロパティセット
            //　@　@（アップロード行.アップロード終了日時 == null）の場合、"アップロード中"
            //　@　@（アップロード行.アップロード終了日時 != null）の場合、"アップロード済み"
            if (l_row.getUploadEndTimestamp() == null)
            {
                l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOADING;
            }
            else
            {
                l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOAD_COMPLETE;
            }          
            
            //　@アップロード件数：　@アップロード行.アップロード件数
            l_unit.uploadNumber = String.valueOf(l_row.getUploadCount());
            
            //　@アップロード開始日時：　@アップロード行.アップロード開始日時
            l_unit.uploadStartDate = l_row.getUploadStartTimestamp();
            //　@アップロード終了日時：　@アップロード行.アップロード終了日時
            l_unit.uploadEndDate = l_row.getUploadEndTimestamp();
            
            //　@お客様エラー番号：　@アップロード行.エラーメッセージ
            l_unit.aioErrorId = l_row.getMessageCode();                       
                                   
        }
        
        //1.7)createResponse()
        WEB3AdminAioVirtualAccCashinULInputResponse l_response = 
            (WEB3AdminAioVirtualAccCashinULInputResponse) l_request.createResponse();
        
        //1.8)プロパティセット
        //(*2)レスポンスデータプロパティに値をセットする。
        //アップロード履歴一覧：
        //　@アップロード履歴が存在する場合、バーチャル口座入金アップロード履歴明細オブジェクト 
        //アップロード履歴が存在しない場合、null
        l_response.uploadHistoryList = l_unit;        
               
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validateアップロードファ@イル)<BR>
     * バーチャル口座入金アップロード確認処理を行う。 <BR>
     * <BR>
     * シーケンス図<BR> 
     * 「入出金サービスモデル（管理者）（バーチャル口座入金ＵＬ）<BR>
     * validateアップロードファ@イル」参照。<BR>
     * @@param l_request - (バーチャル口座入金UL確認リクエスト)<BR>
     * バーチャル口座入金UL確認リクエスト
     * @@return WEB3AdminAioVirtualAccCashinULConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4455B55B0257
     */
    protected WEB3AdminAioVirtualAccCashinULConfirmResponse validateUploadFile(
        WEB3AdminAioVirtualAccCashinULConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateUploadFile(" +
        "WEB3AdminAioVirtualAccCashinULConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1)リクエストデータの整合性チェックを行う。
        l_request.validate();
        
        //1.2)getInstanceFromログイン情報( )
        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3)validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.VIRTUAL_ACC_CASHIN_UPLOAD, true);
        
        //1.4)validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5)バーチャル口座入金CSV( )
        WEB3AdminAioVirtualAccCashinULCsv l_aioVirtualAccCashinULCsv =
            new WEB3AdminAioVirtualAccCashinULCsv();
        
        //1.6)validate同時アップロード(アップロードＩＤ : long)
        l_aioVirtualAccCashinULCsv.validateSameTimeUpload(null);
        
        //1.7)get管理者コード( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        
        //1.8)saveアップロード開始(データキー : long, 備考１ : String, 
        //備考２ : String, 備考３ : String, 更新者コード : String)
        l_aioVirtualAccCashinULCsv.saveUpLoadStart(0, null, null, null,l_strAdministratorCode);
        
        //1.9)リクエストデータ.アップロードファ@イル[]の各要素毎のLOOP処理
        int l_intUploadFilelength = l_request.uploadFile.length;
        for (int i = 0; i < l_intUploadFilelength; i++)
        {
            //1.9.1)空行の場合（リクエストデータ.アップロードファ@イル.length < 0）、
            //当該要素の処理を中断（continue;）
            if (l_request.uploadFile[i] == null || "".equals(l_request.uploadFile[i]))
            {
                continue;
            }
            
            //1.9.2)アップロードファ@イルのチェックを行い、処理対象か判別する。 
            boolean l_blnIsSkipReadRecord = 
                l_aioVirtualAccCashinULCsv.isSkipReadRecord(i, l_request.financialInstitutionCode, l_request.uploadFile);
            
            //1.9.3)is読み飛ばしレコード（）の戻り値 = falseの場合、当該要素の処理を中断（continue;）します。
            if (!l_blnIsSkipReadRecord)
            {
                continue;
            }
            
            //1.9.4)checkデータ区分
            int l_intDataDiv = l_aioVirtualAccCashinULCsv.checkDataDiv(l_request.uploadFile[i].substring(0,1));
            
            try
            {
                //checkデータ区分の戻り値が1の場合、ヘッダレコードのvalidateを行う。 
                if (l_intDataDiv == 1)
                {
                    //1.9.5)validateヘッダーレコード()
                    l_aioVirtualAccCashinULCsv.validateHeaderRecord(i, l_request);
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //1.9.6.1)saveアップロードエラー(エラー情報 : ErrorInfo)
                l_aioVirtualAccCashinULCsv.saveUploadError(l_ex.getErrorInfo());
                
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage(),
                    l_ex);
            } 
            try
            {
                //1.9.7checkデータ区分の戻り値が2の場合、データレコードのvalidateを行う。           
                if (l_intDataDiv == 2)
                {
                    l_aioVirtualAccCashinULCsv.validateDataRecord(i, l_request);
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //1.9.8.1)saveアップロードエラー(エラー情報 : ErrorInfo)
                l_aioVirtualAccCashinULCsv.saveUploadError(l_ex.getErrorInfo());
                
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage(),
                    l_ex);
            } 
            
            //1.9.9)checkデータ区分の戻り値が9の場合、checkデータレコード件数( )
            try 
            {
                                
                if (l_intDataDiv == 9)
                {
                    l_aioVirtualAccCashinULCsv.checkDataRecordCount();
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //1.9.10.1)saveアップロードエラー(エラー情報 : ErrorInfo)
                l_aioVirtualAccCashinULCsv.saveUploadError(l_ex.getErrorInfo());
                
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage(),
                    l_ex);
            }
        }  
        
        //1.10)calc読み飛ばし件数()
        l_aioVirtualAccCashinULCsv.calcSkipReadCount();
        
        //1.11)getアップロードＩＤ
        long l_lngUploadId = l_aioVirtualAccCashinULCsv.getAdministratorUploadId();
        
        //1.12)saveアップロードファ@イル()
        l_aioVirtualAccCashinULCsv.saveUploadFile(l_request.uploadFile);
        
        //1.13)createResponse( )
        WEB3AdminAioVirtualAccCashinULConfirmResponse l_response =
            (WEB3AdminAioVirtualAccCashinULConfirmResponse) l_request.createResponse();
        
        //1.14)プロパティセット
        //アップロード件数 ： this.トータル件数
        l_response.uploadNumber = l_aioVirtualAccCashinULCsv.totalCount + "";
        
        //ヘッダレコード件数 ： this.ヘッダレコード件数
        l_response.headerNumber = l_aioVirtualAccCashinULCsv.headerRecordCount + "";
        
        //データレコード件数 ： this.データレコード件数
        l_response.dataNumber = l_aioVirtualAccCashinULCsv.dataRecordCount + "";
        
        //トレーラーレコード件数 ： this.トレーラーレコード件数
        l_response.trailerNumber = l_aioVirtualAccCashinULCsv.trailerRecordCount + "";
        
        //エンドレコード件数 ： this.エンドレコード件数
        l_response.endNumber = l_aioVirtualAccCashinULCsv.endRecordCount + "";
        
        //読み飛ばしたレコード件数 ： this.読み飛ばしたレコード件数
        l_response.skipOverNumber = l_aioVirtualAccCashinULCsv.skipReadRecordCount + "";
        
        //アップロードID ： getアップロードID()の戻り値
        l_response.uploadID = l_lngUploadId + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;           
               
    }
    
    /**
     * (submitアップロードファ@イル)<BR>
     * バーチャル口座入金アップロード完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「入金サービスモデル（管理者）（バーチャル口座入金アドレスＵＬ）<BR>
     * submitアップロードファ@イル」参照。 <BR>
     * @@param l_request - (バーチャル口座入金入力確認リクエスト)<BR>
     * バーチャル口座入金UL確認リクエスト
     * @@return WEB3AdminAioVirtualAccCashinULCompleteResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4455B7590133
     */
    protected WEB3AdminAioVirtualAccCashinULCompleteResponse submitUploadFile(
            WEB3AdminAioVirtualAccCashinULCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitUploadFile(" +
        "WEB3AdminAioVirtualAccCashinULCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1)リクエストデータの整合性チェックを行う。
        l_request.validate();
        
        //1.2)getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3)validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.VIRTUAL_ACC_CASHIN_UPLOAD, true);
        
        //1.4)validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5)バーチャル口座入金CSV(long)        
        //  [引数]
        //　@アップロードID：　@リクエストデータ.アップロードID
        WEB3AdminAioVirtualAccCashinULCsv l_aioVirtualAccCashinULCsv =
            new WEB3AdminAioVirtualAccCashinULCsv(Long.parseLong(l_request.uploadID));
        
        try
        {
            //1.6) check入金処理()
            l_aioVirtualAccCashinULCsv.checkCashinTransaction();
        }
        catch (WEB3BaseException l_ex)
        {
            //1.7.1)saveアップロードエラー(エラー情報 : ErrorInfo)
            l_aioVirtualAccCashinULCsv.saveUploadError(l_ex.getErrorInfo());
            
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getErrorMessage(),
                l_ex);
        }

        //1.8)validate取引パスワード(パスワード : String)
        l_admin.validateTradingPassword(l_request.password);

        //1.9)stop入金デーモン()
        l_aioVirtualAccCashinULCsv.stopCashinDaemon();
        
        //1.10)validate同時アップロード(アップロードＩＤ : long)
        l_aioVirtualAccCashinULCsv.validateSameTimeUpload(new Long(l_request.uploadID));
 
        //1.11)setDataFromアップロードTemp(アップロードＩＤ : long)
        l_aioVirtualAccCashinULCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadID));
        
        //1.12)get明細行数()
        int l_intRowCount = l_aioVirtualAccCashinULCsv.getRowCount();
        
        //1.13)get明細行()
        String[] l_strRows = l_aioVirtualAccCashinULCsv.getRows();        
        
        //[銀行入金通知テーブルIDの最大値]
        List l_lisRows = null;
        long l_lngBankDepositNotifyId = 0;
        try 
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    BankDepositNotifyRow.TYPE,
                    null,
                    "bank_deposit_notify_id desc",
                    null,
                    null); 
                    
        } 
        catch (DataException l_ex) 
        {
            log.debug("__DataException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (l_lisRows != null && l_lisRows.size() != 0)
        {
            BankDepositNotifyRow l_row = (BankDepositNotifyRow)l_lisRows.get(0);
            l_lngBankDepositNotifyId = l_row.getBankDepositNotifyId();
        }
        
        //1.14)(*1)明細行の数分LOOP処理
        for (int i = 0; i < l_intRowCount; i++) 
        {            
            //1.14.1)is読み飛ばしレコード(int, String, String[])

            //読み飛ばしレコードかどうかチェックする。 

            //[引数の設定]  
            //行番号：　@index 
            //銀行コード：　@バーチャル口座入金UL完了リクエストデータ.銀行コード 
            //明細行：　@get明細行()の戻り値 
            boolean l_blnIsSkipReadRecord = 
                l_aioVirtualAccCashinULCsv.isSkipReadRecord(i, l_request.financialInstitutionCode, l_strRows);
            
            //1.14.2) (*1.2) is読み飛ばしレコード（）の戻り値 = falseの場合、当該要素の処理を中断（continue;）

            if (!l_blnIsSkipReadRecord)
            {
                continue;
            }
            
            //1.14.3)checkデータ区分
            int l_intDataDiv = l_aioVirtualAccCashinULCsv.checkDataDiv(l_strRows[i].substring(0, 1));
            
            //1.14.4)(*3)checkデータ区分の戻り値()が1の場合
            if (l_intDataDiv == 1)
            {
                //1.14.4.1) setヘッダーレコード(int)
                l_aioVirtualAccCashinULCsv.setHeaderRecord(i);               
            }
            
            //1.14.5)(*4)checkデータ区分()の戻り値が2の場合
            else if (l_intDataDiv == 2)
            {
                BankDepositNotifyParams l_params = new BankDepositNotifyParams();               
                
                //1.14.5.1)get銀行支店コード(int)                
                String l_strBankBranchCode = l_aioVirtualAccCashinULCsv.getBankBranchCode(
                    i, l_request.financialInstitutionCode);
                l_params.setBankBranchCode(l_strBankBranchCode);

                //1.14.5.2)get勘定日(int)
                String l_strSettlementDate = l_aioVirtualAccCashinULCsv.getSettlementDate(i);
                l_params.setDepositDataAccountDate(l_strSettlementDate);
                
                //1.14.5.3)get起算日(int)
                String l_strBaseDate = l_aioVirtualAccCashinULCsv.getBaseDate(i);
                l_params.setDepositDataBaseDate(l_strBaseDate);
                
                //1.14.5.4)get金額(int, String)
                String l_strAmount = l_aioVirtualAccCashinULCsv.getAmount(
                    i, l_request.financialInstitutionCode);
                l_params.setDepositDataDepositAmount(l_strAmount);
                
                //1.14.5.5) get依頼人コード(int)
                String l_strPersonCode = l_aioVirtualAccCashinULCsv.getPersonCode(
                        i, l_request.financialInstitutionCode);
                l_params.setDepositDataTransPersonCode(l_strPersonCode);
                
                //1.14.5.6)get依頼人名(int, String)
                String l_strPersonName = l_aioVirtualAccCashinULCsv.getPersonName(
                    i, l_request.financialInstitutionCode);
                l_params.setDepositDataTransPersonName(l_strPersonName);
                
                //1.14.5.7)get仕向銀行名(int)
                String l_strBankName = l_aioVirtualAccCashinULCsv.getDeliveredBankName(i);
                l_params.setDeliveredBankName(l_strBankName);
                
                //1.14.5.8)get仕向店名(int)
                String l_strBankBranchName = l_aioVirtualAccCashinULCsv.getDeliveredBranchName(i);
                l_params.setDeliveredBankBranchName(l_strBankBranchName);
                
                //1.14.5.9)getEDI情報(int, String)
                String l_strEDIInfo = l_aioVirtualAccCashinULCsv.getEDIInfo(
                    i, l_request.financialInstitutionCode);
                l_params.setEdiInfo(l_strEDIInfo);
                
                //アップロードファ@イルの内容を銀行入金通知テーブルに更新する。 
                //※更新内容は【XTrade】補足資料.DB更新「バーチャル口座入金UL_銀行入金通知テーブル更新仕様.xls」を参照する。                
                //通番（[銀行入金通知テーブルIDの最大値]　@+　@1）
                l_lngBankDepositNotifyId = l_lngBankDepositNotifyId + 1;
                l_params.setBankDepositNotifyId(l_lngBankDepositNotifyId);
                
                //管理者オブジェクト.証券会社コード
                l_params.setInstitutionCode(l_admin.getInstitutionCode());
                
                //銀行コード
                l_params.setBankCode(l_aioVirtualAccCashinULCsv.bankCode);
                
                //口座番号
                l_params.setBankAccountNo(l_aioVirtualAccCashinULCsv.accountCode);
                
                //照会番号
                l_params.setDepositDataReferenceNo(Long.toString(l_lngBankDepositNotifyId));
                
                //預金種目
                l_params.setDepositDataBankAccountType(l_aioVirtualAccCashinULCsv.depositBankAccountType);
                
                //入払区分
                l_params.setCashTransferDiv(WEB3AioBankDepositNotifyCashTransferDivDef.CASH_IN);
                
                //取引区分
                l_params.setTradeDiv(WEB3AioBankDepositNotifyTradeDivDef.PAY_BY_TRANSFER);
                
                //処理区分
                l_params.setStatus(WEB3StatusDef.NOT_DEAL);
                
                //エラー履歴最終通番
                l_params.setLastErrorHistorySerialNo(0);
                
                //作成日付
                l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //更新日付
                l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                //データ取込区分
                l_params.setDataLoadDiv(WEB3DataLoadDivDef.BAATYARU_ACCOUNT_CHAHIN_UL);
                    
                //1.14.5.11)doInsertQuery(arg0 : Row)
                try 
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doInsertQuery(l_params);
                }
                catch (DataQueryException l_ex)
                {
                    log.debug("DBへのアクセスに失敗しました: ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.debug("DBへのアクセスに失敗しました: ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                }
            
            }                          
        }
        //1.15)start入金デーモン()
        l_aioVirtualAccCashinULCsv.startCashinDaemon();
        
        //1.16)saveアップロード終了()
        l_aioVirtualAccCashinULCsv.saveUploadEnd();
        
        //1.17)deleteアップロードTemp()
        l_aioVirtualAccCashinULCsv.deleteUploadTemp();
        
        //1.18)createResponse()
        WEB3AdminAioVirtualAccCashinULCompleteResponse l_response =
            (WEB3AdminAioVirtualAccCashinULCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undoアップロードファ@イル)<BR>
     * バーチャル口座入金処理のアップロード中止処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「入出金サービスモデル（管理者）（バーチャル口座入金アドレスＵＬ）<BR>
     * undoアップロードファ@イル」参照。<BR>
     * @@param l_request - (バーチャル口座入金中止リクエスト)<BR>
     * バーチャル口座入金中止リクエスト
     * @@return WEB3AdminAioVirtualAccCashinULCancelResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4455B899006C
     */
    protected WEB3AdminAioVirtualAccCashinULCancelResponse undoUploadFile(
        WEB3AdminAioVirtualAccCashinULCancelRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "undoUploadFile(" +
        "WEB3AdminAioVirtualAccCashinULCancelRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1)validate()
        l_request.validate();
        
        //1.2)バーチャル口座入金CSV(long)
        WEB3AdminAioVirtualAccCashinULCsv l_aioVirtualAccCashinULCsv =
            new WEB3AdminAioVirtualAccCashinULCsv(Long.parseLong(l_request.uploadID));
        
        //1.3)deleteアップロードTemp()
        l_aioVirtualAccCashinULCsv.deleteUploadTemp();
        
        //1.4)saveアップロード中止()
        l_aioVirtualAccCashinULCsv.saveUploadStop();
        
        //1.5)createResponse()
        WEB3AdminAioVirtualAccCashinULCancelResponse l_response =
            (WEB3AdminAioVirtualAccCashinULCancelResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }


}
@
