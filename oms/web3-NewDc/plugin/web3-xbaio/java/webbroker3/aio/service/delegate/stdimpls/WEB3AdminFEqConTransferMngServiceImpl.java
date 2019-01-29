head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConTransferMngServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株振替管理サービスImpl(WEB3AdminFEqConTransferMngServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 周勇 (中訊) 新規作成
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.data.UwgTransferStatusRow;
import webbroker3.aio.define.WEB3AioFEqConTransferReportUnitMessageDef;
import webbroker3.aio.define.WEB3AioTransferOperationDivDef;
import webbroker3.aio.message.WEB3AdminFEqConTransferListInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConTransferListInputResponse;
import webbroker3.aio.message.WEB3AdminFEqConTransferListRequest;
import webbroker3.aio.message.WEB3AdminFEqConTransferListResponse;
import webbroker3.aio.message.WEB3FEqConTransferReportUnit;
import webbroker3.aio.service.delegate.WEB3AdminFEqConTransferMngService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FeqTransOperationDivDef;
import webbroker3.common.define.WEB3FeqTransResultCodeDef;
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
 * (外株振替管理サービスImpl)<BR>
 * 外株振替管理サービス実装クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConTransferMngServiceImpl implements WEB3AdminFEqConTransferMngService 
{
    
    /**
     * @@roseuid 42356517030D
     */
    public WEB3AdminFEqConTransferMngServiceImpl() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConTransferMngServiceImpl.class);

    
    /**
     * 外株口座への振替管理処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *   get入力画面()<BR>
     *   get一覧画面()
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E398E40123
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
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
        
        WEB3GenResponse l_response = null;
        if(l_request instanceof  WEB3AdminFEqConTransferListInputRequest)
        {
            l_response = 
                this.getInputScreen((WEB3AdminFEqConTransferListInputRequest) l_request);
        }
        else if(l_request instanceof WEB3AdminFEqConTransferListRequest)
        {
            l_response = 
                this.getListScreen((WEB3AdminFEqConTransferListRequest) l_request);
        }
        else
        {
            log.debug(
                    "リクエストデータが"
                    + " WEB3AdminFEqConTransferListInputRequest "
                    + " と WEB3AdminFEqConTransferListRequest以外である, but is "
                    + l_request.getClass().getName());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株振替管理）get入力画面」 参照
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConTransferListInputResponse
     * @@roseuid 41E399F60152
     */
    protected WEB3AdminFEqConTransferListInputResponse getInputScreen(
        WEB3AdminFEqConTransferListInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminFEqConTransferListInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate権限
        //アイテムの定義
        //権限チェックを行う。
        //[引数] 
        //機@能カテゴリコード： 機@能カテゴリコード.外国株式振替連携（振替管理） 
        //is更新： false 
        //※ 機@能カテゴリコードは、DBレイアウト「管理者権限テーブル.xls#（補足資料）機@能カテゴリコード一覧」参照。
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_TRANSFER_MANAGE,
            false);
        
        //1.4)  validate部店権限(String[])
        //アイテムの定義
        //部店権限のチェックを行う。
        //[引数] 
        //部店コード： リクエストデータ.部店コード 
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5) createResponse( )
        WEB3AdminFEqConTransferListInputResponse l_respose = 
            (WEB3AdminFEqConTransferListInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_respose;
    }
    
    /**
     * (get一覧画面)<BR>
     * 一覧画面取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株振替管理）get一覧画面」 参照
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConTransferListResponse
     * @@roseuid 41E399F60172
     */
    protected WEB3AdminFEqConTransferListResponse getListScreen(
        WEB3AdminFEqConTransferListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getListScreen(WEB3AdminFEqConTransferListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2)getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3) validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //アイテムの定義
        //権限チェックを行う。 
        //[引数] 
        //機@能カテゴリコード： 機@能カテゴリコード.外国株式振替連携（振替管理） 
        //is更新： false 
        //※ 機@能カテゴリコードは、DBレイアウト「管理者権限テーブル.xls#（補足資料）機@能カテゴリコード一覧」参照。
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_TRANSFER_MANAGE,
            false);

        //1.4)  validate部店権限(String[])
        //アイテムの定義
        //部店権限のチェックを行う。
        //[引数] 
        //部店コード： リクエストデータ.部店コード 
        l_administrator.validateBranchPermission(l_request.branchCode);

        //1.5) create取得条件文字列(String[], String, String, String, String, String, String)
        //アイテムの定義
        //取得条件の文字列を生成する。
        //[引数] 
        //部店コード： リクエストデータ.部店コード 
        //顧客コード： リクエストデータ.顧客コード 
        //振替区分： リクエストデータ.振替区分 
        //受付日（自）： リクエストデータ.受付日（自） 
        //受付日（至）： リクエストデータ.受付日（至） 
        //振替日： リクエストデータ.振替日 
        //ステータス区分： リクエストデータ.ステータス区分 
        
        String l_strCreateQueryString = this.createQueryString(
            l_request.branchCode,
            l_request.accountCode,
            l_request.transferDiv,
            l_request.receiptDateFrom,
            l_request.receiptDateTo,
            l_request.transferDate,
            l_request.statusDiv);
        
        //1.6)  create取得条件データコンテナ(String, String[], String, String, String, String, String, String)
        //アイテムの定義
        //取得条件のデータコンテナを生成する。 
        //[引数] 
        //証券会社コード： 管理者.証券会社コード 
        //部店コード： リクエストデータ.部店コード 
        //顧客コード： リクエストデータ.顧客コード 
        //振替区分： リクエストデータ.振替区分 
        //受付日（自）： リクエストデータ.受付日（自） 
        //受付日（至）： リクエストデータ.受付日（至） 
        //振替日： リクエストデータ.振替日 
        //ステータス区分： リクエストデータ.ステータス区分 
        Object[] l_strCreateQueryContainer = this.createQueryContainer(
            l_administrator.getInstitutionCode(),
            l_request.branchCode,
            l_request.accountCode,
            l_request.transferDiv,
            l_request.receiptDateFrom,
            l_request.receiptDateTo,
            l_request.transferDate,
            l_request.statusDiv);
        
        //1.7) getDefaultProcessor( )
        //1.8) doFindAllQuery(RowType, String, String, String, Object[], int, int)
        //アイテムの定義
        //UWG振替状況テーブルからレコードを取得する。
        //[引数] 
        //Rowタイプ： UWG振替状況Row.TYPE 
        //Where： create取得条件文字列()の戻り値 
        //orderBy： "created_timestamp desc" 
        //condition： null 
        //リスト： create取得条件データコンテナ()の戻り値 
        //ページサイズ： リクエストデータ.ページ内表示行数 
        //ページ番号： リクエストデータ.要求ページ番号 - 1 
        WEB3PageIndexInfo l_pageInfo = null;
        try
        {            
            List l_listPage  = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    UwgTransferStatusRow.TYPE,
                    l_strCreateQueryString,  
                    "created_timestamp desc",
                    null,
                    l_strCreateQueryContainer); 
            
            l_pageInfo = 
                new WEB3PageIndexInfo(
                    l_listPage,
                    Integer.parseInt(l_request.pageIndex),
                    Integer.parseInt(l_request.pageSize)
                    );    
                                     
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
        
        //1.9)  ArrayList( )
        List l_lisFEqConTransferReportUnits = new Vector();
        
        int l_intLength = 0;
        List l_lisReturn = l_pageInfo.getListReturned();
        if(l_pageInfo.getListReturned() != null)
        {
            l_intLength = l_lisReturn.size();
        }
        log.debug("l_intLength = " + l_intLength);
        
        //1.10) 取得したレコード毎にLoop処理
        for(int i = 0; i < l_intLength; i++)
        {
            UwgTransferStatusRow l_uwgTransferStatusRow =
                (UwgTransferStatusRow) l_lisReturn.get(i);
                       
            String l_strDisplayAccountName;
			String l_strMessageCode;
            try
            {
				//1.10.1) get顧客(String, String, String)
				//アイテムの定義
				//顧客インスタンスを取得する。
				//[引数] 
				//証券会社コード： UWG振替状況Params.証券会社コード 
				//部店コード： UWG振替状況Params.部店コード 
				//口座コード： UWG振替状況Params.顧客コード 
				FinApp l_finApp = (FinApp) Services.getService(FinApp.class);           
				WEB3GentradeAccountManager l_gentradeAccountManager = 
					(WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
            
				WEB3GentradeMainAccount l_mainAccount = 
					l_gentradeAccountManager.getMainAccount(
						l_uwgTransferStatusRow.getInstitutionCode(),
						l_uwgTransferStatusRow.getBranchCode(),
						l_uwgTransferStatusRow.getAccountCode().substring(0, 6));

				//1.10.2) get顧客表示名( )
				//アイテムの定義
				//顧客名を取得する。
				l_strDisplayAccountName = l_mainAccount.getDisplayAccountName();
				
				//1.10.3) 未確定事項あり
				//getメッセージコード(String, String, String, String)
				//アイテムの定義
				//メッセージコードを取得する。
				//[引数] 
				//振替状況区分： UWG振替状況Params.振替状況区分 
				//送受信区分： UWG振替状況Params.送受信区分 
				//受付結果コード： UWG振替状況Params.受付結果コード 
				//エラー理由コード： UWG振替状況Params.エラー理由コード
				log.debug("l_uwgTransferStatusRow[" + i + "].TransferStatusDiv = " + l_uwgTransferStatusRow.getTransferStatusDiv());
				log.debug("l_uwgTransferStatusRow[" + i + "].SendRcvDiv = " + l_uwgTransferStatusRow.getSendRcvDiv());
				log.debug("l_uwgTransferStatusRow[" + i + "].ResultCode = " + l_uwgTransferStatusRow.getResultCode());
				log.debug("l_uwgTransferStatusRow[" + i + "].ErrorReasonCode = " + l_uwgTransferStatusRow.getErrorReasonCode());
				l_strMessageCode = this.getMessageCode(
					l_uwgTransferStatusRow.getTransferStatusDiv(),
					l_uwgTransferStatusRow.getSendRcvDiv(),
					l_uwgTransferStatusRow.getResultCode(),
					l_uwgTransferStatusRow.getErrorReasonCode());
            }
            catch(WEB3BaseException l_ex)
            {
				l_strDisplayAccountName = null;
				l_strMessageCode = WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_90000009;
            }
                       
            //1.10.4)
            //外株振替レポート明細( )
            //アイテムの定義
            //外株振替レポート明細インスタンスを生成する
            WEB3FEqConTransferReportUnit l_fEqConTransferReportUnit = 
                new WEB3FEqConTransferReportUnit();  
            
            //1.10.5) プロパティセット
            //(*2) 以下のとおりに、プロパティをセットする。

            //外株振替レポート明細.振替区分 = （以下のとおり）
            //    UWG振替状況Params.処理区分 == 01（証券口座から外国株式口座へ） の場合、2（出金）
            //    UWG振替状況Params.処理区分 == 02（外国株式口座から証券口座へ） の場合、1（入金）
            if(WEB3FeqTransOperationDivDef.TRANSFER_TO_FEQ.equals(l_uwgTransferStatusRow.getOperationDiv()))
            {
                l_fEqConTransferReportUnit.transferDiv = WEB3AioTransferOperationDivDef.CASH_OUT;
            }
            else if(WEB3FeqTransOperationDivDef.TRANSFER_FROM_FEQ.equals(l_uwgTransferStatusRow.getOperationDiv()))
            {
                l_fEqConTransferReportUnit.transferDiv = WEB3AioTransferOperationDivDef.CASH_IN;
            }
            
            //外株振替レポート明細.顧客コード = UWG振替状況Params.顧客コード
            l_fEqConTransferReportUnit.accountCode = l_uwgTransferStatusRow.getAccountCode().substring(0, 6);
            
            //外株振替レポート明細.名前（姓） = get顧客表示名()の戻り値
            //get顧客()にて例外が発生した場合、「NULL」をセット
            l_fEqConTransferReportUnit.familyName = l_strDisplayAccountName;
            
            //外株振替レポート明細.識別コード = UWG振替状況Params.識別コード
            l_fEqConTransferReportUnit.requestNumber = l_uwgTransferStatusRow.getOrderRequestNumber();
            
            //外株振替レポート明細.受付日時 =UWG振替状況Params.作成日付
            l_fEqConTransferReportUnit.receptionDate = l_uwgTransferStatusRow.getCreatedTimestamp();
            
            //外株振替レポート明細.振替日 = UWG振替状況Params.受渡予定日
            l_fEqConTransferReportUnit.transferDate = l_uwgTransferStatusRow.getTransferDate();
            
            //外株振替レポート明細.振替金額 = UWG振替状況Params.金額
            l_fEqConTransferReportUnit.changeAmt = String.valueOf(l_uwgTransferStatusRow.getAmount());
            
            //外株振替レポート明細.外株口座番号 = UWG振替状況Params.口座番号
            l_fEqConTransferReportUnit.fstkAccountCode = l_uwgTransferStatusRow.getFeqAccountCode();
            
            //外株振替レポート明細.UWG受付日時 = UWG振替状況Params.作成日付
            l_fEqConTransferReportUnit.uwgReceptionDate = l_uwgTransferStatusRow.getCreatedTimestamp();
            
            //外株振替レポート明細.ステータス区分 = UWG振替状況Params.振替状況区分
            l_fEqConTransferReportUnit.statusDiv = l_uwgTransferStatusRow.getTransferStatusDiv();
            
            //外株振替レポート明細.メッセージ = getメッセージコード()の戻り値
            //get顧客()にて例外が発生した場合、「90000009(口座抹消)」をセット
            l_fEqConTransferReportUnit.message = l_strMessageCode;
            
            //1.10.6)  add(arg0 : Object)
            //アイテムの定義
            //リストに外株振替レポート明細オブジェクトを追加する。
            //[引数] 
            //arg0： 外株振替レポート明細オブジェクト 
            l_lisFEqConTransferReportUnits.add(l_fEqConTransferReportUnit);
        }
        
        //1.11) toArray( )
        //アイテムの定義
        //リストから配列を取得する。
        WEB3FEqConTransferReportUnit[] l_fEqConTransferReportUnits = 
            new WEB3FEqConTransferReportUnit[l_lisFEqConTransferReportUnits.size()];
        l_lisFEqConTransferReportUnits.toArray(l_fEqConTransferReportUnits);
        
        //1.12) pageNumber( )
        //アイテムの定義
        //表示ページ番号を取得する。
        //1.13) totalPages( )
        //アイテムの定義
        //総ページ数を取得する。 
        //1.14)totalSize( )
        //アイテムの定義
        //総レコード数を取得する。 
        //1.15) createResponse( )
        //アイテムの定義
        //レスポンスデータを生成する。
        WEB3AdminFEqConTransferListResponse l_response = 
            (WEB3AdminFEqConTransferListResponse) l_request.createResponse();
        
        //1.16) (*3) 以下のとおりに、プロパティをセットする。
        //レスポンス.外株振替レポート = 外株振替レポート明細の配列
        l_response.fstkTransferReport = l_fEqConTransferReportUnits;
        
        //レスポンス.表示ページ番号 = pageNumber()の戻り値
        l_response.pageIndex = String.valueOf(l_pageInfo.getPageIndex());
        
        //レスポンス.総ページ数 = totalPages()の戻り値
        l_response.totalPages = String.valueOf(l_pageInfo.getTotalPages());
        
        //レスポンス.総レコード数 = totalSize()の戻り値
        l_response.totalRecords = String.valueOf(l_pageInfo.getTotalRecords());
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create取得条件文字列)<BR>
     * UWG振替状況テーブルからデータを取得する際の条件を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     *   "institution_code=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * <BR>
     * ３－１）引数.部店コード.length() == 1 の場合<BR>
     * <BR>
     *   " and branch_code=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ３－２）引数.部店コード.length() > 1 の場合<BR>
     * <BR>
     *   " and (branch_code=? or branch_code=? or ... or branch_code=?)"<BR>
     *      を１）の文字列に追加する。<BR>
     * <BR>
     *   ※"branch_code=?"の数は、部店コードの要素数と同じ<BR>
     * <BR>
     * ４）顧客コード<BR>
     * <BR>
     *   引数.顧客コード != null の場合<BR>
     * <BR>
     *   " and account_code=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ５）振替区分<BR>
     * <BR>
     *   引数.振替区分 != null の場合<BR>
     * <BR>
     *   " and operation_div=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ６）受付日（自）<BR>
     * <BR>
     *   引数.受付日（自） != null の場合<BR>
     * <BR>
     *   " and created_timestamp>=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ７）受付日（至）<BR>
     * <BR>
     *   引数.受付日（至） != null の場合<BR>
     * <BR>
     *   " and created_timestamp<=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ８）振替日<BR>
     * <BR>
     *   引数.振替日 != null の場合<BR>
     * <BR>
     *   " and transfer_date=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ９）ステータス区分<BR>
     * <BR>
     * ９－１）引数.ステータス区分 == 1（決済完了） の場合<BR>
     * <BR>
     *   " and transfer_status_div=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ９－２）引数.ステータス区分 == 5（その他） の場合<BR>
     * <BR>
     *   " and transfer_status_div!=?"を１）の文字列に追加する。<BR>
     * <BR>
     * １０）文字列を返却する。
     * @@param l_strBranchCodes - 部店コードの配列
     * 
     * @@param l_strAccountCode - 顧客コード
     * 
     * @@param l_strTransferDiv - 振替区分
     * 
     * @@param l_strReceiptDateFrom - 受付日（自）
     * 
     * @@param l_strReceiptDateTo - 受付日（至）
     * 
     * @@param l_strTransferDate - 振替日
     * 
     * @@param l_strStatusDiv - ステータス区分
     * 
     * @@return String
     * @@roseuid 41E7555001CE
     */
    protected String createQueryString(
            String[] l_strBranchCodes, 
            String l_strAccountCode, 
            String l_strTransferDiv, 
            Date l_strReceiptDateFrom, 
            Date l_strReceiptDateTo, 
            Date l_strTransferDate, 
            String l_strStatusDiv) 
    {
        final String STR_METHOD_NAME = 
            "createQueryString(String[] l_strBranchCodes,String l_strAccountCode," +
            "String l_strTransferDiv,String l_strReceiptDateFrom, String l_strReceiptDateTo," +
            "String l_strTransferDate,String l_strStatusDiv)";
        log.entering(STR_METHOD_NAME);
        
        //１）空の文字列を生成する。 
        //２）証券会社コード 
        //"institution_code=?"を１）の文字列に追加する。
        StringBuffer l_strBuff = new StringBuffer();
        l_strBuff.append("institution_code = ? ");
        
        //３）部店コード 
        //３－１）引数.部店コード.length() == 1 の場合
        //" and branch_code=?"を１）の文字列に追加する。
        if(l_strBranchCodes.length == 1)
        {
            l_strBuff.append(" and branch_code = ? ");
        }
        
        //３－２）引数.部店コード.length() > 1 の場合 
        //" and (branch_code=? or branch_code=? or ... or branch_code=?)"を１）の文字列に追加する。
        //※"branch_code=?"の数は、部店コードの要素数と同じ
        if(l_strBranchCodes.length > 1)
        {
            l_strBuff.append(" and ( branch_code = ? ");
            
            for(int i = 0; i < l_strBranchCodes.length -1; i++)
            {
                l_strBuff.append(" or branch_code = ? ");
            }
            
            l_strBuff.append(")");
            
        }
        
        //４）顧客コード 
        //引数.顧客コード != null の場合
        //" and account_code=?"を１）の文字列に追加する。
        if(!WEB3StringTypeUtility.isEmpty(l_strAccountCode))
        {
            l_strBuff.append(" and substr(account_code, 1, 6) = ? ");
        }
        
        //５）振替区分
        //引数.振替区分 != null の場合
        //" and operation_div=?"を１）の文字列に追加する。 
        if(!WEB3StringTypeUtility.isEmpty(l_strTransferDiv))
        {
            l_strBuff.append(" and operation_div = ? ");
        }
        
        //６）受付日（自） 
        //引数.受付日（自） != null の場合
        //" and created_timestamp>=?"を１）の文字列に追加する。 
        if(l_strReceiptDateFrom != null)
        {
            l_strBuff.append(" and to_char(created_timestamp, 'YYYYMMDD') >= ? ");
        }
        
        //７）受付日（至） 
        //引数.受付日（至） != null の場合
        //" and created_timestamp<=?"を１）の文字列に追加する。 
        if(l_strReceiptDateTo != null)
        {
            l_strBuff.append(" and to_char(created_timestamp, 'YYYYMMDD') <= ? ");
        }
        
        //８）振替日 
        //引数.振替日 != null の場合
        //" and transfer_date=?"を１）の文字列に追加する。 
        if(l_strTransferDate != null)
        {
            l_strBuff.append(" and transfer_date = ? ");
        }
        
        //９）ステータス区分 
        //９－１）引数.ステータス区分 == 1（決済完了） の場合
        //" and transfer_status_div=?"を１）の文字列に追加する。 
        if(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(l_strStatusDiv))
        {
            l_strBuff.append(" and transfer_status_div = ? ");
        }
        
        // ９－２）引数.ステータス区分 == 5（その他） の場合
        //" and transfer_status_div!=?"を１）の文字列に追加する。 
        if(WEB3TransferStatusDivDef.OTHER.equals(l_strStatusDiv))
        {
            l_strBuff.append(" and transfer_status_div <> ? ");
        }
        
        //１０）文字列を返却する。 
        
        log.exiting(STR_METHOD_NAME);
        return l_strBuff.toString();
    }
    
    /**
     * (create取得条件データコンテナ)<BR>
     * UWG振替状況テーブルからデータを取得する際の条件のデータコンテナを生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     *   引数.証券会社コードの各要素を１）のListに追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * <BR>
     *   引数.部店コードの各要素を１）のListに追加する。<BR>
     * <BR>
     * ４）顧客コード<BR>
     * <BR>
     *   引数.顧客コード != null の場合<BR>
     * <BR>
     *   引数.顧客コードを１）のListに追加する。<BR>
     * <BR>
     * ５）振替区分<BR>
     * <BR>
     *   引数.振替区分 != null の場合<BR>
     * <BR>
     *   引数.振替区分==”入金”の場合は、”外国株式口座から証券口座へ”<BR>
     *   引数.振替区分==”出金”の場合は、”証券口座から外国株式口座へ” <BR> 
     *   を１）のListに追加する。<BR>
     * <BR>
     * ６）受付日（自）<BR>
     * <BR>
     *   引数.受付日（自） != null の場合<BR>
     * <BR>
     *   引数.受付日（自）を１）のListに追加する。<BR>
     * <BR>
     * ７）受付日（至）<BR>
     * <BR>
     *   引数.受付日（至） != null の場合<BR>
     * <BR>
     *   引数.受付日（至）を１）のListに追加する。<BR>
     * <BR>
     * ８）振替日<BR>
     * <BR>
     *   引数.振替日 != null の場合<BR>
     * <BR>
     *   引数.振替日を１）のListに追加する。<BR>
     * <BR>
     * ９）ステータス区分<BR>
     * <BR>
     *   引数.ステータス区分 != null の場合<BR>
     * <BR>
     *   振替状況区分.決済完了(1)をListに追加する。<BR>
     * <BR>
     * １０）Listから配列を取得して、返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * 
     * @@param l_strBranchCodes - 部店コードの配列
     * 
     * @@param l_strAccountCode - 顧客コード
     * 
     * @@param l_strTransferDiv - 振替区分
     * 
     * @@param l_strReceiptDateFrom - 受付日（自）
     * 
     * @@param l_strReceiptDateTo - 受付日（至）
     * 
     * @@param l_strTransferDate - 振替日
     * 
     * @@param l_strStatusDiv - ステータス区分
     * 
     * @@return Object[]
     * @@roseuid 41E7555001DE
     */
    protected Object[] createQueryContainer(
            String l_strInstitutionCode, 
            String[] l_strBranchCodes, 
            String l_strAccountCode, 
            String l_strTransferDiv, 
            Date l_datReceiptDateFrom, 
            Date l_datReceiptDateTo, 
            Date l_datTransferDate, 
            String l_strStatusDiv) 
    {
        final String STR_METHOD_NAME = 
            "createQueryContainer(String l_strInstitutionCode, String[] l_strBranchCodes," +
            "String l_strAccountCode,String l_strTransferDiv, Date l_datReceiptDateFrom, " +
            "Date l_datReceiptDateTo, Date l_datTransferDate, String l_strStatusDiv)";
        log.entering(STR_METHOD_NAME);
        
        //１）空のArrayListを生成する。
        //２）証券会社コード
        //引数.証券会社コードの各要素を１）のListに追加する。 
        List l_lisQueryContainers = new Vector();
        l_lisQueryContainers.add(l_strInstitutionCode);
        
        //３）部店コード 
        //引数.部店コードの各要素を１）のListに追加する。 
        for(int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisQueryContainers.add(l_strBranchCodes[i]);
        }
        
        //４）顧客コード
        //引数.顧客コード != null の場合
        //引数.顧客コードを１）のListに追加する。 
        if(!WEB3StringTypeUtility.isEmpty(l_strAccountCode))
        {
            l_lisQueryContainers.add(l_strAccountCode);
        }
        
        //５）振替区分
        //引数.振替区分 != null の場合
        //引数.振替区分==”入金”の場合は、”外国株式口座から証券口座へ” 
        //引数.振替区分==”出金”の場合は、”証券口座から外国株式口座へ” 
        //を１）のListに追加する。。 
        if(!WEB3StringTypeUtility.isEmpty(l_strTransferDiv))
        {
            if(WEB3AioTransferOperationDivDef.CASH_IN.equals(l_strTransferDiv))
            {
                l_lisQueryContainers.add(WEB3FeqTransOperationDivDef.TRANSFER_FROM_FEQ);
            }
            else if(WEB3AioTransferOperationDivDef.CASH_OUT.equals(l_strTransferDiv))
            {
                l_lisQueryContainers.add(WEB3FeqTransOperationDivDef.TRANSFER_TO_FEQ);
            }
        }
        
        //６）受付日（自）
        //引数.受付日（自） != null の場合
        //引数.受付日（自）を１）のListに追加する。 
        if(l_datReceiptDateFrom != null)
        {       
            log.debug("引数.受付日（自）= " + l_datReceiptDateFrom);
            log.debug("引数.受付日（自）= " + WEB3DateUtility.formatDate(l_datReceiptDateFrom, "yyyyMMdd"));
            l_lisQueryContainers.add(WEB3DateUtility.formatDate(l_datReceiptDateFrom, "yyyyMMdd"));
        }
        
        //７）受付日（至）
        //引数.受付日（至） != null の場合
        //引数.受付日（至）を１）のListに追加する。 
        if(l_datReceiptDateTo != null)
        {
            log.debug("引数.受付日（至）= " + l_datReceiptDateTo);
            log.debug("引数.受付日（至）= " + WEB3DateUtility.formatDate(l_datReceiptDateTo, "yyyyMMdd"));
            l_lisQueryContainers.add(WEB3DateUtility.formatDate(l_datReceiptDateTo, "yyyyMMdd"));
        }
        
        //８）振替日
        //引数.振替日 != null の場合
        //引数.振替日を１）のListに追加する。 
        if(l_datTransferDate != null)
        {
            l_lisQueryContainers.add(WEB3DateUtility.formatDate(l_datTransferDate, "yyyyMMdd"));
        }
        
        //９）ステータス区分 
        //引数.ステータス区分 != null の場合
        //振替状況区分.決済完了(1)をListに追加する。 
        if(!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_lisQueryContainers.add(WEB3TransferStatusDivDef.PROCESS_COMPLETE);
        }
        
        //１０）Listから配列を取得して、返却する。
        
        log.exiting(STR_METHOD_NAME);
        return l_lisQueryContainers.toArray();
    }
    
    /**
     * (getメッセージコード)<BR>
     * メッセージコードを取得する。<BR>     
     * <BR>
     * １）引数.振替状況区分 == ”決済中” の場合 <BR>
     * <BR>
     * １－１）引数.送受信区分 == ”未送信” の場合 <BR>
     * <BR>
     * ”受付済”を返却する。 <BR>
     * <BR>
     * １－２）引数.送受信区分 == ”送信済” の場合 <BR>
     * <BR>
     * ”決済中”を返却する。 <BR>
     * <BR>
     * １－３）それ以外 の場合 <BR>
     * <BR>
     * ”決済失敗（システムエラー）”を返却する。 <BR>
     * <BR>
     * ２）引数.振替状況区分 == ”決済完了” の場合 <BR>
     * <BR>
     * ２－１）引数.送受信区分 == ”受信済” and 引数.受付結果コード == ”決済完了” の場合 <BR>
     * <BR>
     * ”決済完了”を返却する。 <BR>
     * <BR>
     * ２－２）それ以外 の場合 <BR>
     * <BR>
     * ”決済失敗（システムエラー）”を返却する。” <BR>
     * <BR>
     * ３）引数.振替状況区分 == ”決済エラー” の場合 <BR>
     * <BR>
     * ３－１）引数.送受信区分 == ”受信済” の場合 <BR>
     * <BR>
     * 引数.エラー理由コードを返却する。 <BR>
     * <BR>
     * ３－２）それ以外 の場合 <BR>
     * <BR>
     * ”決済失敗（システムエラー）”を返却する。 <BR>
     * <BR>
     * ４）引数.振替状況区分 == ”取消” の場合 <BR>
     * <BR>
     * ４－１）引数.送受信区分 == ”未送信” の場合 <BR>
     * <BR>
     * ”取消済”を返却する。 <BR>
     * <BR>
     * ４－２）それ以外 の場合 <BR>
     * <BR>
     * ”決済失敗（システムエラー）”を返却する。 <BR>
     * <BR>
     * ５）上記の条件のいずれにも一致しない場合 <BR>
     * <BR>
     * ”決済失敗（システムエラー）”を返却する。 <BR>
     * <BR> 
     * @@param l_strTransferStatusDiv - 振替状況区分
     * 
     * @@param l_strSendRcvDiv - 送受信区分
     * 
     * @@param l_strResultCode - 受付結果コード
     * 
     * @@return String
     * @@roseuid 41E7555001E6
     */
    protected String getMessageCode(
            String l_strTransferStatusDiv, 
            String l_strSendRcvDiv, 
            String l_strResultCode,
            String l_strErrorReasonCode) 
    {
        final String STR_METHOD_NAME = 
            "getMessageCode(String l_strTransferStatusDiv, String l_strSendRcvDiv," +
            "String l_strResultCode, String l_strErrorReasonCode)";
        log.entering(STR_METHOD_NAME);
       
        //１）引数.振替状況区分 == ”決済中” の場合 
        //１－１）引数.送受信区分 == ”未送信” の場合 
        //  ”受付済”を返却する。 
        //１－２）引数.送受信区分 == ”送信済” の場合 
        //  ”決済中”を返却する。 
        //１－３）それ以外 の場合 
        //  ”決済失敗（システムエラー）”を返却する。 
        if(WEB3TransferStatusDivDef.PROCESSING.equals(l_strTransferStatusDiv))
        {
            if(WEB3SendRcvDivDef.NOT_SEND.equals(l_strSendRcvDiv))
            {
                return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_10000000;
            }
            if(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_strSendRcvDiv))
            {
                return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_20000000;
            }            
            return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_99999999;
        }
        
        //２）引数.振替状況区分 == ”決済完了” の場合 
        //２－１）引数.送受信区分 == ”受信済” and 引数.受付結果コード == ”決済完了” の場合 
        //  ”決済完了”を返却する。 
        //２－２）それ以外 の場合 
        //  ”決済失敗（システムエラー）”を返却する。”  
        if(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(l_strTransferStatusDiv))
        {
            if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_strSendRcvDiv)
                && WEB3FeqTransResultCodeDef.TRANSFER_COMPLETE.equals(l_strResultCode))
            {
                return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_00000000;
            }
            
            return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_99999999;
        }        

        //３）引数.振替状況区分 == ”決済エラー” の場合 
        //３－１）引数.送受信区分 == ”受信済” の場合 
        //  引数.エラー理由コードを返却する。 
        //３－２）それ以外 の場合 
        //  ”決済失敗（システムエラー）”を返却する。 
        if(WEB3TransferStatusDivDef.PROCESS_ERROR.equals(l_strTransferStatusDiv))
        {
            if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_strSendRcvDiv))
            {
                return l_strErrorReasonCode;                                               
            }
            
            return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_99999999;
        }

        //４）引数.振替状況区分 == ”取消” の場合 
        //４－１）引数.送受信区分 == ”未送信” の場合 
        //  ”取消済”を返却する。 
        //４－２）それ以外 の場合 
        //  ”決済失敗（システムエラー）”を返却する。 
        if(WEB3TransferStatusDivDef.CANCEL.equals(l_strTransferStatusDiv))
        {
            if(WEB3SendRcvDivDef.NOT_SEND.equals(l_strSendRcvDiv))
            {
                return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_90000000;
            }
            
            return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_99999999;
        }

        //５）上記の条件のいずれにも一致しない場合 
        //  ”決済失敗（システムエラー）”を返却する。
        log.exiting(STR_METHOD_NAME);
        return WEB3AioFEqConTransferReportUnitMessageDef.ACCEPT_RESULT_CODE_99999999;
    }
}
@
