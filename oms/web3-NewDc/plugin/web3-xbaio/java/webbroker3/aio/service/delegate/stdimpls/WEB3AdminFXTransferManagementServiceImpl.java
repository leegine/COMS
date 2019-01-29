head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferManagementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替管理サービス実装クラス(WEB3AdminFXTransferManagementServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 于美麗 (中訊) 新規作成
                   2006/04/24 周捷 (中訊) 仕様変更 NO.532
                   2006/07/12 丁昭奎 (中訊) 仕様変更 NO.595
                   2006/07/18 韋念瓊 (中訊) 仕様変更 NO.603
                   2006/08/04 鈴木 (SCS) 仕様変更 NO.610
Revesion History : 2007/09/12 周墨洋 (中訊) 仕様変更・モデルNo.762
Revesion History : 2008/04/08 武波 (中訊) 仕様変更・モデルNo.839
Revesion History : 2008/05/20 柴双紅 (中訊) 仕様変更・モデル861
Revesion History : 2008/06/26 柴双紅 (中訊) 仕様変更・モデル907
Revesion History : 2008/09/23 馮海濤 (中訊) 仕様変更・モデルNo.999,1056,1071
Revesion History : 2009/03/11 王志葵 (中訊) 仕様変更・モデルNo.1116
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioFxTransferDivDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.define.WEB3AioTransferOperationDivDef;
import webbroker3.aio.message.WEB3AdminFXTransferCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXTransferCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXTransferCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXTransferCancelConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXTransferListConditionRequest;
import webbroker3.aio.message.WEB3AdminFXTransferListConditionResponse;
import webbroker3.aio.message.WEB3AdminFXTransferListRequest;
import webbroker3.aio.message.WEB3AdminFXTransferListResponse;
import webbroker3.aio.message.WEB3FXSearchConditionUnit;
import webbroker3.aio.message.WEB3FXTransferDetailUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferManagementService;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferManagementUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FxTransStatusOperationDivDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX振替管理サービスImpl) <BR>
 * FX振替管理サービス実装クラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AdminFXTransferManagementServiceImpl implements
    WEB3AdminFXTransferManagementService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferManagementServiceImpl.class);

    /**
     * (execute) <BR>
     * FX振替管理サービス処理を行う。 <BR>
     * <BR>
     * リクエストの型によって、 <BR>
     * <BR>
     * get条件入力画面() <BR>
     * get一覧画面() <BR>
     * validate取消() <BR>
     * submit取消() <BR>
     * <BR>
     * のメソッドをコールする。
     * 
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C0FBEC003D
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
        
        if (l_request instanceof WEB3AdminFXTransferCancelConfirmRequest)
        {
            //リクエストデータの具象データ型が「管理者・FX振替取消確認リクエスト」の場合
            WEB3AdminFXTransferCancelConfirmResponse l_Response = 
                this.validateCancel((WEB3AdminFXTransferCancelConfirmRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXTransferCancelCompleteRequest)
        {
            //リクエストデータの具象データ型が「管理者・FX振替取消完了リクエストクラス」の場合
            WEB3AdminFXTransferCancelCompleteResponse l_Response = 
                this.submitCancel((WEB3AdminFXTransferCancelCompleteRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXTransferListRequest)
        {
            //リクエストデータの具象データ型が「管理者・FX振替一覧リクエストクラス」の場合
            WEB3AdminFXTransferListResponse l_Response = 
                this.getListScreen((WEB3AdminFXTransferListRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXTransferListConditionRequest)
        {
            //リクエストデータの具象データ型が「管理者・FX振替一覧条件入力リクエストクラス」の場合
            WEB3AdminFXTransferListConditionResponse l_Response = 
                this.getCondInputScreen((WEB3AdminFXTransferListConditionRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else
        {
            log.debug("パラメータタイプ不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (get条件入力画面) <BR>
     * 条件入力画面表示データの取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FX振替管理）get条件入力画面」 参照
     * 
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFXTransferListConditionResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C0FC300185
     */
    protected WEB3AdminFXTransferListConditionResponse getCondInputScreen(
        WEB3AdminFXTransferListConditionRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCondInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1. リクエストデータのチェックを行う。
        l_request.validate();
        
        // 1.2. 管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3. 権限チェックを行う。 
        // [引数] 
        // 機@能カテゴリコード： "B0402" 
        // is更新： false 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE,
            false
            );
        
        // 1.4. 部店権限のチェックを行う。 
        // [引数] 
        // 部店コード： リクエストデータ.部店コード
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        // 1.5. レスポンスデータを生成する。
        WEB3AdminFXTransferListConditionResponse l_response = 
            (WEB3AdminFXTransferListConditionResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get一覧画面) <BR>
     * 一覧画面表示データの取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FX振替管理）get一覧画面」 参照
     * 
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFXTransferListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C0FC3800F8
     */
    protected WEB3AdminFXTransferListResponse getListScreen(
        WEB3AdminFXTransferListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getListScreen()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1. リクエストデータのチェックを行う。
        l_request.validate();
        
        // 1.2. 管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3. 権限チェックを行う。 
        // [引数] 
        // 機@能カテゴリコード： "B0402" 
        // is更新： false 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE,
            false
            );
        
        // 1.4.部店権限のチェックを行う。 
        // [引数] 
        // 部店コード： リクエストデータ.部店コード
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        // 1.5. 取得条件の文字列を生成する。 
        // [引数] 
        // 部店コード： リクエストデータ.部店コード 
        // 顧客コード： リクエストデータ.顧客コード 
        // 振替区分： リクエストデータ.振替区分 
        // 受付日（自）： リクエストデータ.受付日（自） 
        // 受付日（至）： リクエストデータ.受付日（至） 
        // 振替日： リクエストデータ.振替日 
        // ステータス区分： リクエストデータ.ステータス区分
        // FXシステムコード：リクエストデータ.FXシステムコード
        String l_strWhere = 
            this.createQueryString(
                l_request.branchCodeList,
                l_request.accountCode,
                l_request.fxTransferDiv,
                l_request.receiptDateFrom,
                l_request.receiptDateTo,
                WEB3DateUtility.formatDate(l_request.transferDate, "yyyyMMdd"),
                l_request.statusDiv,
                l_request.fxSystemCode);
        
        // 1.6. 取得条件のデータコンテナを生成する。 
        // [引数] 
        // 証券会社コード： 管理者.証券会社コード 
        // 部店コード： リクエストデータ.部店コード 
        // 顧客コード： リクエストデータ.顧客コード 
        // 振替区分： リクエストデータ.振替区分 
        // 受付日（自）： リクエストデータ.受付日（自） 
        // 受付日（至）： リクエストデータ.受付日（至） 
        // 振替日： リクエストデータ.振替日 
        // ステータス区分： リクエストデータ.ステータス区分
        // FXシステムコード：リクエストデータ.FXシステムコード
        Object[] l_objVars = 
            this.createQueryContainer(
                l_admin.getInstitutionCode(),
                l_request.branchCodeList,
                l_request.accountCode,
                l_request.fxTransferDiv,
                l_request.receiptDateFrom,
                l_request.receiptDateTo,
                WEB3DateUtility.formatDate(l_request.transferDate, "yyyyMMdd"),
                l_request.statusDiv,
                l_request.fxSystemCode);
        
        List l_lisRows = null;
        WEB3PageIndexInfo l_pageInfo = null;
        try
        {
            // 1.7. クエリプロセッサを取得する。
            QueryProcessor l_queryProcessor = 
                Processors.getDefaultProcessor();
            
            // 1.8. GFT振替状況テーブルからレコードを取得する。 
            // [引数] 
            // Rowタイプ： GFT振替状況Row.TYPE 
            // Where： create取得条件文字列()の戻り値 
            // orderBy： "created_timestamp desc" 
            // condition： null 
            // リスト： create取得条件データコンテナ()の戻り値 
            // ページサイズ： リクエストデータ.ページ内表示行数 
            // ページ番号： リクエストデータ.要求ページ番号 - 1 
            List l_lisRowTemp = 
                l_queryProcessor.doFindAllQuery(
                    GftTransferStatusRow.TYPE,
                    l_strWhere,                    
                    " created_timestamp desc ",
                    null,
                    l_objVars,
                    Integer.parseInt(l_request.pageSize),
                    Integer.parseInt(l_request.pageIndex) - 1);
                    
            //log for test
            log.debug("*************** l_lisRowTemp.size = " + l_lisRowTemp.size());                    
            
            l_pageInfo = 
                new WEB3PageIndexInfo(
                    l_lisRowTemp,
                    Integer.parseInt(l_request.pageIndex),
                    Integer.parseInt(l_request.pageSize)
                    );    
            l_lisRows = l_pageInfo.getListReturned();
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // 1.9. 空のリストを生成する。
        List l_lisFXTrsfDetailUnits = new Vector();
        
        // 1.10. 取得したレコード毎にLoop処理
        int l_intLength = 0;
        if(l_lisRows != null)
        {
            l_intLength = l_lisRows.size();
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        //拡張アカウントマネージャ取得する    
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        for(int i = 0; i < l_intLength; i++)
        {
            GftTransferStatusRow l_gftTrsStatusRow = 
                (GftTransferStatusRow) l_lisRows.get(i);

            String l_strMsgCode;
			String l_strAccountName;
			boolean l_mainAccountFlag = true; 
            try
            {
				// 1.10.1. 顧客インスタンスを取得する。 
				// [引数] 
				// 証券会社コード： GFT振替状況Params.証券会社コード 
				// 部店コード： GFT振替状況Params.部店コード 
				// 口座コード： GFT振替状況Params.顧客コード 
				WEB3GentradeMainAccount l_mainAccount = 
					l_accountManager.getMainAccount(
						l_gftTrsStatusRow.getInstitutionCode(),
						l_gftTrsStatusRow.getBranchCode(),
						l_gftTrsStatusRow.getAccountCode()
						);
            
				// 1.10.2. 顧客名を取得する。 
				l_strAccountName = l_mainAccount.getDisplayAccountName();
				
				// 1.10.3. メッセージコードを取得する。 
				// [引数] 
				// 振替状況区分： GFT振替状況Params.振替状況区分 
				// 送受信区分： GFT振替状況Params.送受信区分 
				// 受付結果コード： GFT振替状況Params.受付結果コード 
				l_strMsgCode = this.getMessageCode(
						l_gftTrsStatusRow.getTransferStatusDiv(),
						l_gftTrsStatusRow.getSendRcvDiv(),
						l_gftTrsStatusRow.getResultCode()
						);
            	
            }
            catch(WEB3BaseException l_ex)
            {
				l_strAccountName = null;
				l_strMsgCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_90000009;
				l_mainAccountFlag = false;
            }
            
            // 1.10.4. FX振替明細を生成
            WEB3FXTransferDetailUnit l_detailUnit = 
                new WEB3FXTransferDetailUnit();
            
            // 1.10.5. 以下のとおりに、プロパティをセットする。
            // FX振替明細.選択可能フラグ = （以下のとおり）（※１）
            //     GFT振替状況Params.振替状況区分 == 0（決済中） の場合、true
            //     GFT振替状況Params.振替状況区分 == 2（決済エラー) 
            //        && （GFT振替状況Params.受信結果コード == 00000801（2重受信エラー）
            //            || GFT振替状況Params.受信結果コード == 00000990 （GFT接続エラー））の場合、true
            //     GFT振替状況Params.振替状況区分 != 0（決済中） の場合、false　@（※上記条件（2重受信エラー時、GFT接続エラー時）は除く）
            if(l_mainAccountFlag)
            {
				if(WEB3TransferStatusDivDef.PROCESSING.equals(
					l_gftTrsStatusRow.getTransferStatusDiv()))
				{
					l_detailUnit.selectableFlag = true;
				}
				else if(WEB3TransferStatusDivDef.PROCESS_ERROR.equals(
					l_gftTrsStatusRow.getTransferStatusDiv()) &&
					(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals(l_gftTrsStatusRow.getResultCode())
                        || WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000990.equals(
                            l_gftTrsStatusRow.getResultCode())))
				{
					l_detailUnit.selectableFlag = true;
				}
				else
				{
					l_detailUnit.selectableFlag = false;
				}	
            }
            else
            {
				l_detailUnit.selectableFlag = false;
            }          
            
            // FX振替明細.振替区分 = （以下のとおり）
            //    GFT振替状況Params.処理区分 == 01（証券口座から為替保証金へ） の場合、2：出金（FX)
            //    GFT振替状況Params.処理区分 == 02（為替保証金から証券口座へ） の場合、1：入金（FX)
            //    GFT振替状況Params.処理区分 == 03（証券口座から株先証拠金へ） の場合、3：出金（先OP）
            //    GFT振替状況Params.処理区分 == 04（株先証拠金から証券口座へ） の場合、4：入金（先OP）
            if(WEB3FxTransStatusOperationDivDef.TO_FX.equals(
                l_gftTrsStatusRow.getOperationDiv()))
            {
                l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_FX;
            }
            else if(WEB3FxTransStatusOperationDivDef.FROM_FX.equals(
                l_gftTrsStatusRow.getOperationDiv()))
            {
                l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_FX;
            }
            else if(WEB3FxTransStatusOperationDivDef.TO_FUOP.equals(
                l_gftTrsStatusRow.getOperationDiv()))
            {
                l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_OP;
            }
            else if(WEB3FxTransStatusOperationDivDef.FROM_FUOP.equals(
                l_gftTrsStatusRow.getOperationDiv()))
            {
                l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_OP;
            }
            
            // FX振替明細.部店コード = GFT振替状況Params.部店コード
            l_detailUnit.branchCode = l_gftTrsStatusRow.getBranchCode();
            
            // FX振替明細.顧客コード = GFT振替状況Params.顧客コード
            String l_strAccountCodeToSet = l_gftTrsStatusRow.getAccountCode();
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
            {
                l_strAccountCodeToSet = l_gftTrsStatusRow.getAccountCode().substring(0, 6);
            }

            l_detailUnit.accountCode = l_strAccountCodeToSet;
            
            // FX振替明細.顧客名 = get顧客表示名()の戻り値（※２）
            // get顧客()にて例外がスローされた場合は、「NULL」をセット
            l_detailUnit.accountName = l_strAccountName;
            
            // FX振替明細.識別コード = GFT振替状況Params.識別コード
            l_detailUnit.requestNumber = 
                l_gftTrsStatusRow.getOrderRequestNumber();
            
            // FX振替明細.受付日時 =GFT振替状況Params.作成日付
            l_detailUnit.receiptTime = l_gftTrsStatusRow.getCreatedTimestamp();
            
            // FX振替明細.振替日 = GFT振替状況Params.受渡予定日
            l_detailUnit.transferDate = 
                WEB3DateUtility.getDate(l_gftTrsStatusRow.getTransferDate(), "yyyyMMdd");
            
            // FX振替明細.振替金額 = GFT振替状況Params.金額
            l_detailUnit.transferAmount = l_gftTrsStatusRow.getAmount() + "";
            
            // FX振替明細.（FX）口座番号 = GFT振替状況Params.口座番号
            l_detailUnit.fxAccountCode = l_gftTrsStatusRow.getFxAccountCode();
            
            // FX振替明細.（FX）コース区分 = GFT振替状況Params.コース区分
            l_detailUnit.fxCourseDiv = l_gftTrsStatusRow.getCourseDiv();
            
            // FX振替明細.FXシステム受付日時 = GFT振替状況Params.処理時間（受信）
            l_detailUnit.fxReceiptTime = l_gftTrsStatusRow.getReceiveTime();
            
            // FX振替明細.ステータス区分 = GFT振替状況Params.振替状況区分
            l_detailUnit.statusDiv = l_gftTrsStatusRow.getTransferStatusDiv();
            
            // FX振替明細.メッセージ = getメッセージコード()の戻り値（※３）
            // get顧客()にて例外がスローされた場合は、「90000009(口座抹消）」をセット
            l_detailUnit.fxMessage = l_strMsgCode;

            //FX振替明細.FXシステムコード = GFT振替状況Params.FXシステムコード
            l_detailUnit.fxSystemCode = l_gftTrsStatusRow.getFxSystemCode();

            //（※１）get顧客()で例外が発生した場合は、「false」をセット。
            //（※２）get顧客()で例外が発生した場合は、「NULL」をセット。
            //（※３）get顧客()で例外が発生した場合は、「90000009（口座抹消）」をセットする。
            
            // 1.10.6. リストにFX振替明細オブジェクトを追加する。 
            // [引数] 
            // arg0： FX振替明細オブジェクト
            l_lisFXTrsfDetailUnits.add(l_detailUnit);
        }

        //振替出金
        long l_lngFxTotalDepositToGuaranty = 0;
        //振替入金
        long l_lngFxTotalGuarantyToDeposit = 0;
        boolean l_blnFxTransFlag = false;

        //1.11リクエストデータ.振替日 != null
        if (l_request.transferDate != null)
        {
            //(*4)create取得条件文字列()の戻り値に以下の文字列を追加
            //"and transfer_status_div=1 and send_rcv_div=2 and result_code=00000000"
            String l_strQueryString =
                l_strWhere + " and transfer_status_div=1 and send_rcv_div=2 and result_code=00000000 ";

            //doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : Object[])
            //アイテムの定義
            //GFT振替状況テーブルからレコードを取得する。
            //[引数]
            //　@　@arg0 ： GFT振替状況Row.TYPE
            //　@　@arg1 ： (*4)にて生成した文字列
            //　@　@arg2 ：create取得条件データコンテナ()の戻り値
            List l_lisGftTransferStatusRows = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisGftTransferStatusRows = l_queryProcessor.doFindAllQuery(
                     GftTransferStatusRow.TYPE,
                     l_strQueryString,
                     l_objVars);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //(*5)取得したレコード毎にLoop処理
            for (int i = 0; i < l_lisGftTransferStatusRows.size(); i++)
            {
                GftTransferStatusRow l_gftTransferStatusRow =
                    (GftTransferStatusRow)l_lisGftTransferStatusRows.get(i);
                l_blnFxTransFlag = true;
                // (*6)振替金額の計算
                //GFT振替状況Params.処理区分 = 01(証券口座から為替保証金へ）の場合
                //　@　@振替出金 = 振替出金 + GFT振替状況Params.金額
                if(WEB3FxTransStatusOperationDivDef.TO_FX.equals(
                    l_gftTransferStatusRow.getOperationDiv()))
                {
                    l_lngFxTotalDepositToGuaranty =
                        l_lngFxTotalDepositToGuaranty
                            + l_gftTransferStatusRow.getAmount();
                }
                //GFT振替状況Params.処理区分 = 02(為替保証金から証券口座へ）の場合
                //　@　@　@振替入金 = 振替入金 + GFT振替状況Params.金額
                if(WEB3FxTransStatusOperationDivDef.FROM_FX.equals(
                    l_gftTransferStatusRow.getOperationDiv()))
                {
                    l_lngFxTotalGuarantyToDeposit =
                        l_lngFxTotalGuarantyToDeposit
                            + l_gftTransferStatusRow.getAmount();
                }
            }
        }

        // 1.11. リストから配列を取得する。
        WEB3FXTransferDetailUnit[] l_arrFXTrsfDetailUnits = 
            new WEB3FXTransferDetailUnit[l_lisFXTrsfDetailUnits.size()];
        l_lisFXTrsfDetailUnits.toArray(l_arrFXTrsfDetailUnits);
        
        // 1.12. 表示ページ番号を取得する。
        int l_intPageIndex = l_pageInfo.getPageIndex();
        
        // 1.13. 総ページ数を取得する。
        int l_intTotalPages = l_pageInfo.getTotalPages();
        //log for test
        log.debug("********************** l_intTotalPages = " + l_intTotalPages);
        
        // 1.14. 総レコード数を取得する。
        int l_intTotalRecords = l_pageInfo.getTotalRecords();
        //log for test
        log.debug("********************** l_intTotalRecords = " + l_intTotalRecords);
        
        // 1.15. レスポンスデータを生成する。
        WEB3AdminFXTransferListResponse l_response = 
            (WEB3AdminFXTransferListResponse) l_request.createResponse();
        
        // 1.16. 以下のとおりに、プロパティをセットする。
        // リクエストデータ.部店コードの要素数が1つの場合：レスポンス.部店コードにその要素をセット
        // リクエストデータ.部店コードの要素数が複数の場合：レスポンス.部店コードにnullをセット
        if(l_request.branchCodeList.length == 1)
        {
            l_response.branchCode = l_request.branchCodeList[0];
        }
        else
        {
            l_response.branchCode = null;
        }
        
        // レスポンス.顧客コード = リクエストデータ.顧客コード
        l_response.accountCode = l_request.accountCode;
        
        // レスポンス.振替区分 = リクエストデータ.振替区分
        l_response.fxTransferDiv = l_request.fxTransferDiv;
        
        // レスポンス.受付日（自） = リクエストデータ.受付日（自）
        l_response.receiptDateFrom = l_request.receiptDateFrom;
        
        // レスポンス.受付日（至） = リクエストデータ.受付日（至）
        l_response.receiptDateTo = l_request.receiptDateTo;
        
        // レスポンス.振替日 = リクエストデータ.振替日
        l_response.transferDate= l_request.transferDate;
        
        // レスポンス.ステータス区分 = リクエストデータ.ステータス区分
        l_response.statusDiv = l_request.statusDiv;
        
        // レスポンス.FX振替明細一覧 = FX振替明細の配列
        l_response.fxTransferDetailList = l_arrFXTrsfDetailUnits;
        
        // レスポンス.表示ページ番号 = pageNumber()の戻り値
        l_response.pageIndex = l_intPageIndex + "";
        
        // レスポンス.総ページ数 = totalPages()の戻り値
        l_response.totalPages = l_intTotalPages + "";
        
        // レスポンス.総レコード数 = totalSize()の戻り値
        l_response.totalRecords = l_intTotalRecords + "";

        //※振替出金、振替入金、振替合計は振替金額の計算が行われなかった場合、nullを返却する。
        if (l_blnFxTransFlag)
        {
            //レスポンス.振替出金 = 振替出金の計算結果
            l_response.fxTotalDepositToGuaranty = l_lngFxTotalDepositToGuaranty + "";

            //レスポンス.振替入金 = 振替入金の計算結果
            l_response.fxTotalGuarantyToDeposit = l_lngFxTotalGuarantyToDeposit + "";

            //レスポンス.振替合計 = 振替入金の計算結果 - 振替出金の計算結果
            l_response.fxTransferTotal = (l_lngFxTotalGuarantyToDeposit - l_lngFxTotalDepositToGuaranty) + "";
        }
        else
        {
            //レスポンス.振替出金 = 振替出金の計算結果
            l_response.fxTotalDepositToGuaranty = null;

            //レスポンス.振替入金 = 振替入金の計算結果
            l_response.fxTotalGuarantyToDeposit = null;

            //レスポンス.振替合計 = 振替入金の計算結果 - 振替出金の計算結果
            l_response.fxTransferTotal = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate取消) <BR>
     * 取消注文の確認を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FX振替管理）validate取消」 参照
     * 
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFXTransferCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C0FC3D006C
     */
    protected WEB3AdminFXTransferCancelConfirmResponse validateCancel(
        WEB3AdminFXTransferCancelConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancel()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1. リクエストデータのチェックを行う。
        l_request.validate();
        
        // 1.2. 管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3. 権限チェックを行う。 
        // [引数] 
        // 機@能カテゴリコード： "B0402" 
        // is更新： true 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE,
            true
            );
        
        // 1.4. 空のリストを生成する。
        List l_lisFXTransferDetailUnits = new Vector();
        
        // 1.5. FX検索条件一覧の各要素毎にLoop処理
        WEB3FXSearchConditionUnit[] l_arrFXSearchConditionUnits = 
            l_request.fxSearchConditionList;

        // AIO注文マネージャを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        //拡張アカウントマネージャ取得する    
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        for(int i = 0; i < l_arrFXSearchConditionUnits.length; i++)
        {
            WEB3FXSearchConditionUnit l_conditionUnit = 
                l_arrFXSearchConditionUnits[i];
            
            // 1.5.1. 部店権限をチェックする。 
            // [引数] 
            // 部店コード： FX検索条件.部店コード
            l_admin.validateBranchPermission(l_conditionUnit.branchCode);
            
            // 1.5.2. GFT振替状況行オブジェクトを取得する。 
            // [引数] 
            // 証券会社コード： 管理者.get証券会社コード()の戻り値 
            // 部店コード： FX検索条件.部店コード 
            // 識別コード： FX検索条件.識別コード 
            WEB3FXDataControlService l_controlService = 
                (WEB3FXDataControlService) Services.getService(
                    WEB3FXDataControlService.class);               
            
            GftTransferStatusRow l_gftStatusRow = 
                l_controlService.getGFTTransferStatus(
                    l_admin.getInstitutionCode(),
                    l_conditionUnit.branchCode,
                    l_conditionUnit.requestNumber
                    );
            
            // 1.5.3. 
            // ①@ GFT振替状況Params.振替状況区分 == 0（決済中）
            // ② GFT振替状況Params.振替状況区分 == 2（決済エラー） && （GFT振替状況Params.受付結果コード == 00000801（2重受信エラー）
            //|| GFT振替状況Params.受信結果コード == 00000990（GFT接続エラー））
            //     の場合、実施
            if((WEB3TransferStatusDivDef.PROCESSING.equals(
                l_gftStatusRow.getTransferStatusDiv())) || 
                (WEB3TransferStatusDivDef.PROCESS_ERROR.equals(
                l_gftStatusRow.getTransferStatusDiv()) &&
                (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals( l_gftStatusRow.getResultCode())
                    || WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000990.equals(
                        l_gftStatusRow.getResultCode()))))
            {
                // 1.5.3.1. 顧客インスタンスを取得する。 
                // [引数] 
                // 証券会社コード： GFT振替状況Params.証券会社コード 
                // 部店コード： GFT振替状況Params.部店コード 
                // 口座コード： GFT振替状況Params.顧客コード 
                WEB3GentradeMainAccount l_mainAccount = 
                    l_accountManager.getMainAccount(
                        l_gftStatusRow.getInstitutionCode(),
                        l_gftStatusRow.getBranchCode(),
                        l_gftStatusRow.getAccountCode()
                        );
                
                // 1.5.3.2. 顧客名を取得する。
                String l_strAccountName = 
                    l_mainAccount.getDisplayAccountName();
                
                // 1.5.3.3. メッセージコードを取得する。 
                // [引数] 
                // 振替状況区分： GFT振替状況Params.振替状況区分 
                // 送受信区分： GFT振替状況Params.送受信区分 
                // 受付結果コード： GFT振替状況Params.受付結果コード 
                String l_strMsgCode = this.getMessageCode(
                    l_gftStatusRow.getTransferStatusDiv(),
                    l_gftStatusRow.getSendRcvDiv(),
                    l_gftStatusRow.getResultCode()
                    );
                
                // 1.5.3.4. FX振替明細インスタンスを生成する。
                WEB3FXTransferDetailUnit l_detailUnit = 
                    new WEB3FXTransferDetailUnit();
                
                // 1.5.3.5. 以下のとおりに、プロパティをセットする。
                // FX振替明細.選択可能フラグ = true
                l_detailUnit.selectableFlag = true;
                
                // FX振替明細.振替区分 = （以下のとおり）
                //    GFT振替状況Params.処理区分 == 01（証券口座から為替保証金へ） の場合、2：出金（FX)
                //    GFT振替状況Params.処理区分 == 02（為替保証金から証券口座へ） の場合、1：入金（FX)
                //    GFT振替状況Params.処理区分 == 03（証券口座から株先証拠金へ） の場合、3：出金（先OP）
                //    GFT振替状況Params.処理区分 == 04（株先証拠金から証券口座へ） の場合、4：入金（先OP）
                if(WEB3FxTransStatusOperationDivDef.TO_FX.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_FX;
                }
                else if(WEB3FxTransStatusOperationDivDef.FROM_FX.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_FX;
                }
                else if(WEB3FxTransStatusOperationDivDef.TO_FUOP.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_OP;
                }
                else if(WEB3FxTransStatusOperationDivDef.FROM_FUOP.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_OP;
                }
                
                // FX振替明細.部店コード = GFT振替状況Params.部店コード
                l_detailUnit.branchCode = l_gftStatusRow.getBranchCode();
                
                // FX振替明細.顧客コード = GFT振替状況Params.顧客コード
                String l_strAccountCodeToSet = l_gftStatusRow.getAccountCode();
                if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
                {
                    l_strAccountCodeToSet = l_gftStatusRow.getAccountCode().substring(0, 6);
                }

                l_detailUnit.accountCode = l_strAccountCodeToSet;
                
                // FX振替明細.顧客名 = get顧客表示名()の戻り値
                l_detailUnit.accountName = l_strAccountName;
                
                // FX振替明細.識別コード = GFT振替状況Params.識別コード
                l_detailUnit.requestNumber = 
                    l_gftStatusRow.getOrderRequestNumber();
                
                // FX振替明細.受付日時 =GFT振替状況Params.作成日付
                l_detailUnit.receiptTime = l_gftStatusRow.getCreatedTimestamp();
                
                // FX振替明細.振替日 = GFT振替状況Params.受渡予定日
                l_detailUnit.transferDate = 
                    WEB3DateUtility.getDate(l_gftStatusRow.getTransferDate(), "yyyyMMdd");
                
                // FX振替明細.振替金額 = GFT振替状況Params.金額
                l_detailUnit.transferAmount = l_gftStatusRow.getAmount() + "";
                
                // FX振替明細.（FX）口座番号 = GFT振替状況Params.口座番号
                l_detailUnit.fxAccountCode = l_gftStatusRow.getFxAccountCode();
                
                // FX振替明細.（FX）コース区分 = GFT振替状況Params.コース区分
                l_detailUnit.fxCourseDiv = l_gftStatusRow.getCourseDiv();
                
                // FX振替明細.FXシステム受付日時 = GFT振替状況Params.処理時間（受信）
                l_detailUnit.fxReceiptTime = l_gftStatusRow.getReceiveTime();
                
                // FX振替明細.ステータス区分 = GFT振替状況Params.振替状況区分
                l_detailUnit.statusDiv = l_gftStatusRow.getTransferStatusDiv();
                
                // FX振替明細.メッセージ = getメッセージコード()の戻り値
                l_detailUnit.fxMessage = l_strMsgCode;

                // FX振替明細.FXシステムコード = GFT振替状況Params.FXシステムコード
                l_detailUnit.fxSystemCode = l_gftStatusRow.getFxSystemCode();

                // 1.5.3.6. リストにFX振替明細オブジェクトを追加する。 
                // [引数] 
                // arg0： FX振替明細オブジェクト 
                l_lisFXTransferDetailUnits.add(l_detailUnit);
            }
        }
        
        // 1.6. リストから配列を取得する。
        WEB3FXTransferDetailUnit[] l_arrFXTransferDetailUnits = 
            new WEB3FXTransferDetailUnit[l_lisFXTransferDetailUnits.size()];
        l_lisFXTransferDetailUnits.toArray(l_arrFXTransferDetailUnits);
        
        // 1.7. レスポンスデータを生成する。
        WEB3AdminFXTransferCancelConfirmResponse l_response = 
            (WEB3AdminFXTransferCancelConfirmResponse) l_request.createResponse();
        
        // 1.8. プロパティセット
        l_response.fxTransferDetailList = l_arrFXTransferDetailUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit取消) <BR>
     * 注文の取消を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FX振替管理）submit取消」 参照
     * 
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFXTransferCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C0FC41029E
     */
    protected WEB3AdminFXTransferCancelCompleteResponse submitCancel(
        WEB3AdminFXTransferCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancel()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }       
        
        // 1.1. リクエストデータのチェックを行う。
        l_request.validate();
        
        // 1.2. 管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3. 権限チェックを行う。 
        // [引数] 
        // 機@能カテゴリコード： "B0402" 
        // is更新： true 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE,
            true
            );
        
        // 1.4. パスワードのチェックを行う。 
        // [引数] 
        // パスワード： リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);
        
        // 1.5. 空のリストを生成する。
        List l_lisFXTransferDetailUnits = new Vector();
        
        // 1.6. FX検索条件一覧の各要素毎にLoop処理
        WEB3FXSearchConditionUnit[] l_arrFXSearchConditionUnits = 
            l_request.fxSearchConditionList;

        // AIO注文マネージャを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        //拡張アカウントマネージャ取得する    
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        for(int i = 0; i < l_arrFXSearchConditionUnits.length; i++)
        {
            WEB3FXSearchConditionUnit l_conditionUnit = 
                l_arrFXSearchConditionUnits[i];
            
            // 1.6.1. 部店権限をチェックする。 
            // [引数] 
            // 部店コード： FX検索条件.部店コード
            l_admin.validateBranchPermission(l_conditionUnit.branchCode);
            
            // 1.6.2. GFT振替状況行オブジェクトを取得する。 
            // [引数] 
            // 証券会社コード： 管理者.get証券会社コード()の戻り値 
            // 部店コード： FX検索条件.部店コード 
            // 識別コード： FX検索条件.識別コード 
            WEB3FXDataControlService l_controlService = 
                (WEB3FXDataControlService) Services.getService(
                    WEB3FXDataControlService.class);
                       
            GftTransferStatusRow l_gftStatusRow = 
                l_controlService.getGFTTransferStatus(
                    l_admin.getInstitutionCode(),
                    l_conditionUnit.branchCode,
                    l_conditionUnit.requestNumber
                    );
            
            // 1.6.3. 
            // ①@ GFT振替状況Params.振替状況区分 == 0（決済中）
            // ② GFT振替状況Params.振替状況区分 == 2（決済エラー） && （GFT振替状況Params.受付結果コード == 00000801（2重受信エラー）
            //  || GFT振替状況Params.受信結果コード == 00000990（GFT接続エラー））
            //     の場合、実施 
            if((WEB3TransferStatusDivDef.PROCESSING.equals(
                l_gftStatusRow.getTransferStatusDiv())) || 
                (WEB3TransferStatusDivDef.PROCESS_ERROR.equals(
                l_gftStatusRow.getTransferStatusDiv()) &&
                (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals( l_gftStatusRow.getResultCode())
                    || WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000990.equals(
                        l_gftStatusRow.getResultCode()))))
            {
                // 1.6.3.1. 顧客インスタンスを取得する。 
                // [引数] 
                // 証券会社コード： GFT振替状況Params.証券会社コード 
                // 部店コード： GFT振替状況Params.部店コード 
                // 口座コード： GFT振替状況Params.顧客コード 
                WEB3GentradeMainAccount l_mainAccount = 
                    l_accountManager.getMainAccount(
                        l_gftStatusRow.getInstitutionCode(),
                        l_gftStatusRow.getBranchCode(),
                        l_gftStatusRow.getAccountCode()
                        );
                
                // 1.6.3.2. 顧客名を取得する。
                String l_strAccountName = 
                    l_mainAccount.getDisplayAccountName();
                
                // 1.6.3.3. メッセージコードを取得する。 
                // [引数] 
                // 振替状況区分： GFT振替状況Params.振替状況区分 
                // 送受信区分： GFT振替状況Params.送受信区分 
                // 受付結果コード： GFT振替状況Params.受付結果コード 
                String l_strMsgCode = this.getMessageCode(
                    l_gftStatusRow.getTransferStatusDiv(),
                    l_gftStatusRow.getSendRcvDiv(),
                    l_gftStatusRow.getResultCode()
                    );
                
                // 1.6.3.4. FX振替明細インスタンスを生成する。
                WEB3FXTransferDetailUnit l_detailUnit = 
                    new WEB3FXTransferDetailUnit();
                
                // 1.6.3.5. 以下のとおりに、プロパティをセットする。
                // FX振替明細.選択可能フラグ = true
                l_detailUnit.selectableFlag = true;
                
                // FX振替明細.振替区分 = （以下のとおり）
                //    GFT振替状況Params.処理区分 == 01（証券口座から為替保証金へ） の場合、2：出金（FX)
                //    GFT振替状況Params.処理区分 == 02（為替保証金から証券口座へ） の場合、1：入金（FX)
                //    GFT振替状況Params.処理区分 == 03（証券口座から株先証拠金へ） の場合、3：出金（先OP）
                //    GFT振替状況Params.処理区分 == 04（株先証拠金から証券口座へ） の場合、4：入金（先OP）
                if(WEB3FxTransStatusOperationDivDef.TO_FX.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_FX;
                }
                else if(WEB3FxTransStatusOperationDivDef.FROM_FX.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_FX;
                }
                else if(WEB3FxTransStatusOperationDivDef.TO_FUOP.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_OUT_OP;
                }
                else if(WEB3FxTransStatusOperationDivDef.FROM_FUOP.equals(
                    l_gftStatusRow.getOperationDiv()))
                {
                    l_detailUnit.fxTransferDiv = WEB3AioFxTransferDivDef.CASH_IN_OP;
                }
                
                // FX振替明細.部店コード = GFT振替状況Params.部店コード
                l_detailUnit.branchCode = l_gftStatusRow.getBranchCode();
                
                // FX振替明細.顧客コード = GFT振替状況Params.顧客コード
                String l_strAccountCodeToSet = l_gftStatusRow.getAccountCode();
                if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
                {
                    l_strAccountCodeToSet = l_gftStatusRow.getAccountCode().substring(0, 6);
                }

                l_detailUnit.accountCode = l_strAccountCodeToSet;
                
                // FX振替明細.顧客名 = get顧客表示名()の戻り値
                l_detailUnit.accountName = l_strAccountName;
                
                // FX振替明細.識別コード = GFT振替状況Params.識別コード
                l_detailUnit.requestNumber = 
                    l_gftStatusRow.getOrderRequestNumber();
                
                // FX振替明細.受付日時 =GFT振替状況Params.作成日付
                l_detailUnit.receiptTime = l_gftStatusRow.getCreatedTimestamp();
                
                // FX振替明細.振替日 = GFT振替状況Params.受渡予定日
                l_detailUnit.transferDate = 
                    WEB3DateUtility.getDate(l_gftStatusRow.getTransferDate(), "yyyyMMdd");
                
                // FX振替明細.振替金額 = GFT振替状況Params.金額
                l_detailUnit.transferAmount = l_gftStatusRow.getAmount() + "";
                
                // FX振替明細.（FX）口座番号 = GFT振替状況Params.口座番号
                l_detailUnit.fxAccountCode = l_gftStatusRow.getFxAccountCode();
                
                // FX振替明細.（FX）コース区分 = GFT振替状況Params.コース区分
                l_detailUnit.fxCourseDiv = l_gftStatusRow.getCourseDiv();
                
                // FX振替明細.FXシステム受付日時 = GFT振替状況Params.処理時間（受信）
                l_detailUnit.fxReceiptTime = l_gftStatusRow.getReceiveTime();
                
                // FX振替明細.ステータス区分 = GFT振替状況Params.振替状況区分
                l_detailUnit.statusDiv = l_gftStatusRow.getTransferStatusDiv();
                
                // FX振替明細.メッセージ = getメッセージコード()の戻り値
                l_detailUnit.fxMessage = l_strMsgCode;

                // FX振替明細.FXシステムコード = GFT振替状況Params.FXシステムコード
                l_detailUnit.fxSystemCode = l_gftStatusRow.getFxSystemCode();

                // 1.6.3.6. リストにFX振替明細オブジェクトを追加する。 
                // [引数] 
                // arg0： FX振替明細オブジェクト 
                l_lisFXTransferDetailUnits.add(l_detailUnit);
                
                // 1.6.3.7. 取消処理を行う。 
                // [引数] 
                // GFT振替状況Params： GFT振替状況行オブジェクト 
                // 管理者： 管理者オブジェクト 
                // パスワード： 引数.パスワード
                WEB3AdminFXTransferManagementUnitService l_unitService = 
                    (WEB3AdminFXTransferManagementUnitService) Services.getService(
                        WEB3AdminFXTransferManagementUnitService.class);
                GftTransferStatusParams l_gftStatusParams = 
                    new GftTransferStatusParams(l_gftStatusRow);
                l_unitService.submitCancel(l_gftStatusParams, l_admin, l_request.password);
            }
        }
        
        // 1.7. リストから配列を取得する。
        WEB3FXTransferDetailUnit[] l_arrFXSearchConditionUnitsForRsp = 
            new WEB3FXTransferDetailUnit[l_lisFXTransferDetailUnits.size()];
        l_lisFXTransferDetailUnits.toArray(l_arrFXSearchConditionUnitsForRsp);
        
        // 1.8. レスポンスデータを生成する。
        WEB3AdminFXTransferCancelCompleteResponse l_response = 
            (WEB3AdminFXTransferCancelCompleteResponse) l_request.createResponse();
        
        // 1.9. プロパティセット
        l_response.fxTransferDetailList = l_arrFXSearchConditionUnitsForRsp;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create取得条件文字列) <BR>
     * GFT振替状況テーブルからデータを取得する際の条件を生成する。 <BR>
     * <BR>
     * １）空の文字列を生成する。 <BR>
     * <BR>
     * ２）証券会社コード <BR>
     * <BR>
     * "institution_code=?"を１）の文字列に追加する。<BR>
     * ３）部店コード <BR>
     * <BR>
     * ３－１）引数.部店コード.length() == 1 の場合 <BR>
     * <BR>
     * "branch_code=?"を１）の文字列に追加する。 <BR>
     * <BR>
     * ３－２）引数.部店コード.length() > 1 の場合 <BR>
     * <BR>
     * "(branch_code=? or branch_code=? or ... or branch_code=?)"を１）の文字列に追加する。<BR>
     * <BR>
     * ※"branch_code=?"の数は、部店コードの要素数と同じ <BR>
     * <BR>
     * ４）顧客コード <BR>
     * <BR>
     * 引数.顧客コード != null の場合 <BR>
     * <BR>
     * " and account_code=?"を１）の文字列に追加する。 <BR>
     * <BR>
     * ５）振替区分 <BR>
     * <BR>
     * 引数.振替区分 != null の場合 <BR>
     * <BR>
     * " and operation_div=?"を１）の文字列に追加する。 <BR>
     * <BR>
     * ６）受付日（自） <BR>
     * <BR>
     * 引数.受付日（自） != null の場合 <BR>
     * <BR>
     * " and created_timestamp>=?"を１）の文字列に追加する。 <BR>
     * <BR>
     * ７）受付日（至） <BR>
     * <BR>
     * 引数.受付日（至） != null の場合 <BR>
     * <BR>
     * " and created_timestamp <=?"を１）の文字列に追加する。 <BR>
     * <BR>
     * ８）振替日 <BR>
     * <BR>
     * 引数.振替日 != null の場合 <BR>
     * <BR>
     * " and transfer_date=?"を１）の文字列に追加する。 <BR>
     * <BR>
     * ９）ステータス区分 <BR>
     * <BR>
     * ９－１）引数.ステータス区分 == 1（決済完了） の場合 <BR>
     * <BR>
     * " and transfer_status_div=?"を１）の文字列に追加する。 <BR>
     * <BR>
     * ９－２）引数.ステータス区分 == 5（その他） の場合 <BR>
     * <BR>
     * " and transfer_status_div!=?"を１）の文字列に追加する。 <BR>
     * <BR>
     * １０）FXシステムコード<BR>
     * <BR>
     * 　@引数.FXシステムコード != nullの場合<BR>
     * 　@" and fx_system_code=?"を１）の文字列に追加する。<BR>
     * <BR>
     * １１）文字列を返却する。<BR>
     * <BR>
     * @@param l_strBranchCodes - 部店コードの配列
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strFxTransferDiv - 振替区分
     * @@param l_datReceiptDateFrom - 受付日（自）
     * @@param l_datReceiptDateTo - 受付日（至）
     * @@param l_strTransferDate - 振替日
     * @@param l_strStatusDiv - ステータス区分
     * @@param l_strFxSystemCode - FXシステムコード
     * @@return String
     * @@roseuid 41C1144B0127
     */
    protected String createQueryString(String[] l_strBranchCodes,
        String l_strAccountCode, String l_strFxTransferDiv,
        Date l_datReceiptDateFrom, Date l_datReceiptDateTo,
        String l_strTransferDate, String l_strStatusDiv,
        String l_strFxSystemCode)
    {
        final String STR_METHOD_NAME =
            "createQueryString(String[], String, String, Date, Date, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodes == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）空の文字列を生成する
        StringBuffer l_strWhere = new StringBuffer();
        
        // ２）証券会社コード 
        // "institution_code=?"を１）の文字列に追加する。
        l_strWhere.append(" institution_code = ? ");
        
        // ３）部店コード  
        if(l_strBranchCodes.length > 0)
        {
            // ３－１）引数.部店コード.length() == 1 の場合 
            // " and branch_code=?"を１）の文字列に追加する。
            l_strWhere.append(" and ( branch_code = ? ");
            
            // ３－２）引数.部店コード.length() > 1 の場合 
            // " and (branch_code=? or branch_code=? or ... or branch_code=?)"を１）の文字列に追加する。 
            // ※"branch_code=?"の数は、部店コードの要素数と同じ
            for(int i = 1; i < l_strBranchCodes.length; i++)
            {
                l_strWhere.append(" or branch_code = ? ");
            }
            
            l_strWhere.append(" ) ");
        }
        
        // ４）顧客コード 
        // 引数.顧客コード != null の場合 
        // " and account_code=?"を１）の文字列に追加する。
        //検索条件文字列 += "and substr(account_code, 0, 6) = ? "
        if(!WEB3StringTypeUtility.isEmpty(l_strAccountCode))
        {
            l_strWhere.append(" and substr(account_code, 0, 6) = ? ");
        }
        
        // ５）振替区分 
        // 引数.振替区分 != null の場合 
        // " and operation_div=?"を１）の文字列に追加する。
        if(!WEB3StringTypeUtility.isEmpty(l_strFxTransferDiv))
        {
            l_strWhere.append(" and operation_div = ? ");
        }
        
        // ６）受付日（自） 
        // 引数.受付日（自） != null の場合 
        // " and created_timestamp>=?"を１）の文字列に追加する。
        if(l_datReceiptDateFrom != null)
        {
            l_strWhere.append(" and to_char(created_timestamp, 'YYYYMMDD') >= ? ");
        }
        
        // ７）受付日（至） 
        // 引数.受付日（至） != null の場合 
        // " and created_timestamp<=?"を１）の文字列に追加する。
        if(l_datReceiptDateTo != null)
        {
            l_strWhere.append(" and to_char(created_timestamp, 'YYYYMMDD') <= ? ");
        }
        
        // ８）振替日 
        // 引数.振替日 != null の場合 
        // " and transfer_date=?"を１）の文字列に追加する。
        if(!WEB3StringTypeUtility.isEmpty(l_strTransferDate))
        {
            l_strWhere.append(" and transfer_date = ? ");
        }
        
        // ９）ステータス区分 
        // ９－１）引数.ステータス区分 == 1（決済完了） の場合 
        // " and transfer_status_div=?"を１）の文字列に追加する。 
        if(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(l_strStatusDiv))
        {
            l_strWhere.append(" and transfer_status_div = ? ");
        }
        else
        {
            // ９－２）引数.ステータス区分 == 5（その他） の場合 
            // " and transfer_status_div!=?"を１）の文字列に追加する。
            if(WEB3TransferStatusDivDef.OTHER.equals(l_strStatusDiv))
            {
                l_strWhere.append(" and transfer_status_div != ? ");
            }
        }

        //引数.FXシステムコード != nullの場合
        // " and fx_system_code=?"を１）の文字列に追加する。
        if (l_strFxSystemCode != null)
        {
            l_strWhere.append(" and fx_system_code = ? ");
        }

        log.debug("l_strWhere = " + l_strWhere);
        //文字列を返却する。
        
        //log for test
        log.debug("************** the StringWhere = " + l_strWhere);
        
        log.exiting(STR_METHOD_NAME);
        return l_strWhere.toString();
    }

    /**
     * (create取得条件データコンテナ) <BR>
     * GFT振替状況テーブルからデータを取得 <BR>
     * する際の条件のデータコンテナを生成する。 <BR>
     * <BR>
     * １）空のArrayListを生成する。 <BR>
     * <BR>
     * ２）証券会社コード <BR>
     * <BR>
     * 引数.証券会社コードの各要素を１）のListに追加する。<BR>
     * ３）部店コード <BR>
     * <BR>
     * 引数.部店コードの各要素を１）のListに追加する。 <BR>
     * <BR>
     * ４）顧客コード <BR>
     * <BR>
     * 引数.顧客コード != null の場合 <BR>
     * <BR>
     * 引数.顧客コードを１）のListに追加する。 <BR>
     * <BR>
     * ５）振替区分 <BR>
     * <BR>
     * 引数.振替区分 != null の場合 <BR>
     * <BR>
     * 引数.振替区分==”入金(FX)”の場合は、”為替保証金から証券口座へ” <BR> 
     * 引数.振替区分==”出金(FX)”の場合は、”証券口座から為替保証金へ” <BR> 
     * 引数.振替区分==”出金(先OP)”の場合は、”預り金から株先証拠金へ” <BR> 
     * 引数.振替区分==”入金(先OP)”の場合は、”株先証拠金から預り金へ” <BR> 
     * <BR>
     * を１）のListに追加する。<BR>
     * <BR>
     * ６）受付日（自） <BR>
     * <BR>
     * 引数.受付日（自） != null の場合 <BR>
     * <BR>
     * 引数.受付日（自）を１）のListに追加する。 <BR>
     * <BR>
     * ７）受付日（至） <BR>
     * <BR>
     * 引数.受付日（至） != null の場合 <BR>
     * <BR>
     * 引数.受付日（至）を１）のListに追加する。 <BR>
     * <BR>
     * ８）振替日 <BR>
     * <BR>
     * 引数.振替日 != null の場合 <BR>
     * <BR>
     * 引数.振替日を１）のListに追加する。 <BR>
     * <BR>
     * ９）ステータス区分 <BR>
     * <BR>
     * 引数.ステータス区分 != null の場合 <BR>
     * <BR>
     * 振替状況区分.決済完了(1)をListに追加する。 <BR>
     * <BR>
     * １０）FXシステムコード<BR>
     * <BR>
     * 　@引数.FXシステムコード != null の場合<BR>
     * <BR>
     * 　@引数.FXシステムコードを１）のListに追加する。<BR>
     * <BR>
     * １１）Listから配列を取得して、返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コードの配列
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strFxTransferDiv - 振替区分
     * @@param l_datReceiptDateFrom - 受付日（自）
     * @@param l_datReceiptDateTo - 受付日（至）
     * @@param l_strTransferDate - 振替日
     * @@param l_strStatusDiv - ステータス区分
     * @@param l_strFxSystemCode - FXシステムコード
     * @@return Object[]
     * @@roseuid 41C1221801F2
     */
    protected Object[] createQueryContainer(String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode, String l_strFxTransferDiv,
        Date l_datReceiptDateFrom, Date l_datReceiptDateTo,
        String l_strTransferDate, String l_strStatusDiv,
        String l_strFxSystemCode)
    {
        final String STR_METHOD_NAME =
            "createQueryContainer(String, String[], String, String, Date, Date, String, String, String)";
        log.entering(STR_METHOD_NAME);
                
        if (l_strBranchCodes == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）空のArrayListを生成する。
        List l_lisVars = new Vector();
        
        // ２）証券会社コード 
        // 引数.証券会社コードの各要素を１）のListに追加する。
        l_lisVars.add(l_strInstitutionCode);
        
        // ３）部店コード 
        // 引数.部店コードの各要素を１）のListに追加する。
        for(int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisVars.add(l_strBranchCodes[i]);   
        }
        
        // ４）顧客コード 
        //引数.顧客コード != null の場合 
        //引数.顧客コードを１）のListに追加する。
        if(!WEB3StringTypeUtility.isEmpty(l_strAccountCode))
        {
            l_lisVars.add(l_strAccountCode.substring(0, 6));
        }
        
        // ５）振替区分 
        // 引数.振替区分 != null の場合 
        if(!WEB3StringTypeUtility.isEmpty(l_strFxTransferDiv))
        {
            if (WEB3AioTransferOperationDivDef.CASH_IN.equals(l_strFxTransferDiv))
            {
                // 引数.振替区分==”入金(FX)”の場合は、”為替保証金から証券口座へ”
                l_lisVars.add(WEB3FxTransStatusOperationDivDef.FROM_FX);
            }
            else if(WEB3AioTransferOperationDivDef.CASH_OUT.equals(l_strFxTransferDiv))
            {
                // 引数.振替区分==”出金(FX)”の場合は、”証券口座から為替保証金へ”
                l_lisVars.add(WEB3FxTransStatusOperationDivDef.TO_FX);
            }
            else if(WEB3AioTransferOperationDivDef.FUOP_OUT.equals(l_strFxTransferDiv))
            {
                // 引数.振替区分==”出金(先OP)”の場合は、”預り金から株先証拠金へ”
                l_lisVars.add(WEB3FxTransStatusOperationDivDef.TO_FUOP);

            }
            else if(WEB3AioTransferOperationDivDef.FUOP_IN.equals(l_strFxTransferDiv))
            {
                // 引数.振替区分==”入金(先OP)”の場合は、”株先証拠金から預り金へ”
                l_lisVars.add(WEB3FxTransStatusOperationDivDef.FROM_FUOP);
            }
        }
        
        // ６）受付日（自） 
        // 引数.受付日（自） != null の場合 
        // 引数.受付日（自）を１）のListに追加する。
        if(l_datReceiptDateFrom != null)
        {
            l_lisVars.add(WEB3DateUtility.formatDate(l_datReceiptDateFrom, "yyyyMMdd"));
            //log for test
            log.debug("******************* 受付日（自） = " + l_datReceiptDateFrom);
        }
        
        // ７）受付日（至） 
        // 引数.受付日（至） != null の場合 
        // 引数.受付日（至）を１）のListに追加する。
        if(l_datReceiptDateTo != null)
        {
            l_lisVars.add(WEB3DateUtility.formatDate(l_datReceiptDateTo, "yyyyMMdd"));
            //log for test
            log.debug("******************* 受付日（至） = " + l_datReceiptDateTo);
        }
        
        // ８）振替日 
        // 引数.振替日 != null の場合 
        // 引数.振替日を１）のListに追加する。
        if(!WEB3StringTypeUtility.isEmpty(l_strTransferDate))
        {
            l_lisVars.add(l_strTransferDate);
        }
        
        // ９）ステータス区分
        // 引数.ステータス区分 != null の場合 
        // 振替状況区分.決済完了(1)をListに追加する。
        if(!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_lisVars.add(WEB3TransferStatusDivDef.PROCESS_COMPLETE);
        }

        //FXシステムコード
        // 引数.FXシステムコード != null の場合
        // 引数.FXシステムコードを１）のListに追加する。
        if (l_strFxSystemCode != null)
        {
            l_lisVars.add(l_strFxSystemCode);
        }

        //Listから配列を取得して、返却する。
        String[] l_strArrVars = new String[l_lisVars.size()]; 
        l_lisVars.toArray(l_strArrVars);
        
        //log for test
        for (int i = 0; i < l_strArrVars.length; i++)
        {
            log.debug("************* StringValue[" + i + "]= " + l_strArrVars[i]);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strArrVars;
    }

    /**
     * (getメッセージコード) <BR>
     * メッセージコードを取得する。 <BR>
     * <BR>
     * １）引数.振替状況区分 == 0（決済中） の場合 <BR>
     * <BR>
     * １－１）引数.送受信区分 == 1（送信済） の場合 <BR>
     * <BR>
     * ”決済中”を返却する。 <BR>
     * <BR>
     * １－２）それ以外 の場合 <BR>
     * <BR>
     * ”決済失敗（システムエラー）”を返却する。 <BR>
     * <BR>
     * ２）引数.振替状況区分 == 1（決済完了） の場合 <BR>
     * <BR>
     * ２－１）引数.送受信区分 == 2（受信済） <BR>
     * and 引数.受付結果コード == 00000000 の場合 <BR>
     * <BR>
     * ”決済完了”を返却する。 <BR>
     * <BR>
     * ２－２）それ以外 の場合 <BR>
     * <BR>
     * ”決済失敗（システムエラー）”を返却する。” <BR>
     * <BR>
     * ３）引数.振替状況区分 == 2（決済エラー） の場合 <BR>
     * <BR>
     * ３－１）引数.送受信区分 == 2（受信済） の場合 <BR>
     * <BR>
     * 引数.受付結果コードを返却する。 <BR>
     * <BR>
     * ３－２）それ以外 の場合 <BR>
     * <BR>
     * ”決済失敗（システムエラー）”を返却する。 <BR>
     * <BR>
     * ４）引数.振替状況区分 == 3（取消） の場合 <BR>
     * <BR>
     * ４－１）引数.送受信区分 == 1（送信済） の場合 <BR>
     * <BR>
     * ”取消完了”を返却する。 <BR>
     * <BR>
     * ４－２）それ以外 の場合 <BR>
     * <BR>
     * ”決済失敗（システムエラー）”を返却する。 <BR>
     * <BR>
     * ５）上記の条件のいずれにも一致しない場合 <BR>
     * <BR>
     * ”決済失敗（システムエラー）”を返却する。
     * 
     * @@param l_strTransferStatusDiv - 振替状況区分
     * @@param l_strSendRcvDiv - 送受信区分
     * @@param l_strResultCode - 受付結果コード
     * @@return String
     * @@roseuid 41C12F750398
     */
    protected String getMessageCode(String l_strTransferStatusDiv,
        String l_strSendRcvDiv, String l_strResultCode)
    {
        final String STR_METHOD_NAME = "getMessageCode()";
        log.entering(STR_METHOD_NAME);
        
        // １）引数.振替状況区分 == 0（決済中） の場合 
        if(WEB3TransferStatusDivDef.PROCESSING.equals(l_strTransferStatusDiv))
        {
            //１－１）引数.送受信区分 == 1（送信済） の場合 
            //”決済中”を返却する。 
            if(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_strSendRcvDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_10000000;
            }
            else
            {
                //１－２）それ以外 の場合 
                //”決済失敗（システムエラー）”を返却する。  
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_99999999;
            } 
        }
        
        // ２）引数.振替状況区分 == 1（決済完了） の場合 
        else if(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(l_strTransferStatusDiv))
        {
            // ２－１）引数.送受信区分 == 2（受信済） and 引数.受付結果コード == 00000000 の場合 
            // ”決済完了”を返却する。 
            if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_strSendRcvDiv) &&
                WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000000.equals(
                    l_strResultCode))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000000;
            }
            else
            {
                // ２－２）それ以外 の場合 
                // ”決済失敗（システムエラー）”を返却する。”  
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_99999999;
            }
        }
        
        // ３）引数.振替状況区分 == 2（決済エラー） の場合 
        else if(WEB3TransferStatusDivDef.PROCESS_ERROR.equals(l_strTransferStatusDiv))
        {
            // ３－１）引数.送受信区分 == 2（受信済） の場合 
            // 引数.受付結果コードを返却する。 
            if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_strSendRcvDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return l_strResultCode;
            }
            else
            {
                // ３－２）それ以外 の場合 
                // ”決済失敗（システムエラー）”を返却する。  
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_99999999;
            }   
        }
        
        // ４）引数.振替状況区分 == 3（取消） の場合 
        else if(WEB3TransferStatusDivDef.CANCEL.equals(l_strTransferStatusDiv))
        {
            // ４－１）引数.送受信区分 == 1（送信済） の場合 
            // ”取消完了”を返却する。 
            if(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_strSendRcvDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_90000000;
            }
            else
            {
                // ４－２）それ以外 の場合 
                // ”決済失敗（システムエラー）”を返却する。  
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_99999999;
            } 
        }
        
        // ５）上記の条件のいずれにも一致しない場合 
        // ”決済失敗（システムエラー）”を返却する。 
        else
        {
            // ４－２）それ以外 の場合 
            // ”決済失敗（システムエラー）”を返却する。  
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_99999999;
        }
    }
}@
