head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設管理サービスImpl(WEB3AdminFEqConAccountOpenMngServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/3/21 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.aio.WEB3AdminFEqConAccountOpenMngListCsv;
import webbroker3.aio.WEB3FEqConTransferDataControlService;
import webbroker3.aio.data.UwgAccountOpenStatusParams;
import webbroker3.aio.define.WEB3AioFeqTransferDivMessageDef;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListDownloadRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListDownloadResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngSrcInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngSrcInputResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenApplyUnit;
import webbroker3.aio.message.WEB3FEqConAccountOpenQuestionInfo;
import webbroker3.aio.service.delegate.WEB3AdminFEqConAccountOpenMngService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (外株口座開設管理サービスImpl)<BR>
 * 外株口座開設管理サービス実装クラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngServiceImpl 
    implements WEB3AdminFEqConAccountOpenMngService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountOpenMngServiceImpl.class);  
    
    /**
     * @@roseuid 423563EA0261
     */
    public WEB3AdminFEqConAccountOpenMngServiceImpl() 
    {
     
    }
    
    /**
     * 外株口座開設管理処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *   get入力画面()<BR>
     *   get一覧画面()<BR>
     *   getステータス更新画面()<BR>
     *   validateステータス更新()<BR>
     *   submitステータス更新()<BR>
     *   getダウンロードファ@イル()<BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B1E700F5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        
        //リクエストデータの型により、以下のメソッドをコールする。 
        //get入力画面() 
        //get一覧画面() 
        //getステータス更新画面() 
        //validateステータス更新() 
        //submitステータス更新() 
        if (l_request instanceof WEB3AdminFEqConAccountOpenMngSrcInputRequest)
        {
            l_response = 
                getInputScreen((WEB3AdminFEqConAccountOpenMngSrcInputRequest)l_request);   
        }
        else if (l_request instanceof WEB3AdminFEqConAccountOpenMngListRequest)
        {
            l_response =
                getListScreen((WEB3AdminFEqConAccountOpenMngListRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest)
        {
            l_response =
                getStatusUpdScreen((WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest)
        {
            l_response =
                validateStatusUpd((WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest)
        {
            l_response =
                submitStatusUpd((WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFEqConAccountOpenMngListDownloadRequest)
        {
            l_response =
                getDownloadFile((WEB3AdminFEqConAccountOpenMngListDownloadRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座開設管理）get入力画面」 参照
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountOpenMngSrcInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B370028B
     */
    protected WEB3AdminFEqConAccountOpenMngSrcInputResponse getInputScreen(
        WEB3AdminFEqConAccountOpenMngSrcInputRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getInputScreen(WEB3AdminFEqConAccountOpenMngSrcInputRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.外国株式振替連携(口座管理・口座開設管理) 
        //is更新：　@false(更新なし)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, false);
        
        //1.4 validate部店権限(String[])
        //[引数] 
        //部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 createResponse()
        WEB3AdminFEqConAccountOpenMngSrcInputResponse l_response =
            (WEB3AdminFEqConAccountOpenMngSrcInputResponse)l_request.createResponse();
        
        //1.6 (*)レスポンスデータにプロパティをセットする。
        //(*1)現在時刻 
        //ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TIMESTAMP_TAG)
        //にて取得した現在時刻
        Timestamp l_currentTime = GtlUtils.getSystemTimestamp();
        
        //レスポンスデータ.申込日(自) ＝　@現在時刻(*1)の前営業日
        Date l_datApplyDateFrom = 
            new WEB3GentradeBizDate(new Timestamp(l_currentTime.getTime())).roll(-1);                 
        l_response.applyDateFrom = WEB3DateUtility.formatDate(l_datApplyDateFrom, "yyyyMMdd");               
        log.debug("レスポンスデータ.申込日(自) ＝ " + l_response.applyDateFrom);
        
        //レスポンスデータ.申込日(至) ＝　@現在時刻の前日
        Date l_datApplyDateTo = WEB3DateUtility.addDay(l_currentTime, -1);        
        l_response.applyDateTo = WEB3DateUtility.formatDate(l_datApplyDateTo, "yyyyMMdd");  
        log.debug("レスポンスデータ.申込日(至) ＝ " + l_response.applyDateTo);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get一覧画面)<BR>
     * 一覧画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座開設管理）get一覧画面」 参照
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminFEqConAccountOpenMngListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B37002AA
     */
    protected WEB3AdminFEqConAccountOpenMngListResponse getListScreen(
        WEB3AdminFEqConAccountOpenMngListRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getListScreen(WEB3AdminFEqConAccountOpenMngListRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.外国株式振替連携(口座管理・口座開設管理) 
        //is更新：　@false(更新なし)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, false);
        
        //1.4 get証券会社コード()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5 create検索条件文字列(String[], String, String, String)
        //[引数] 
        //部店コード：　@リクエストデータ.部店コード 
        //ステータス区分：　@リクエストデータ.ステータス区分 
        //申込日（自）：　@リクエストデータ.申込日（自） 
        //申込日（至）：　@リクエストデータ.申込日（至）
        String l_strCondition = 
            this.createQueryString(
                l_request.branchCode,
                l_request.statusDiv,
                l_request.applyDateFrom,
                l_request.applyDateTo);
        log.debug("create検索条件文字列 = " + l_strCondition);
        
        //1.6 create検索条件データコンテナ(String, String[], String, String, String)
        //[引数] 
        //証券会社コード：　@get証券会社コード()の戻り値 
        //部店コード：　@リクエストデータ.部店コード 
        //ステータス区分：　@リクエストデータ.ステータス区分 
        //申込日（自）：　@リクエストデータ.申込日（自） 
        //申込日（至）：　@リクエストデータ.申込日（至）
        String[] l_strValue = 
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.statusDiv,
                l_request.applyDateFrom,
                l_request.applyDateTo);
        
        //1.7 createソート条件()
        String l_strSortCond = this.createSortCond();
        log.debug("createソート条件 = " + l_strSortCond);
        
        //1.8 getUWG口座開設状況(String, String[], String)
        //[引数] 
        //検索条件文字列：　@create検索条件文字列()の戻り値 
        //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値 
        //ソート条件：　@createソート条件()の戻り値
        
        //get 外株振替連携データ制御サービスImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgAccountOpenStatusParams[] l_accountOpenStatusParams =
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_strCondition,
                l_strValue,
                l_strSortCond);
        
        //1.9 ArrayList()
        List l_lisAccountOpenApplyUnit = new ArrayList();
        
        //1.10 getUWG口座開設状況()の戻り値のうち、
        //表示対象行(fromIndex～toIndex)の間、Loop処理を実施する
        //[表示対象行（fromIndex，toIndex）の計算]
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_accountOpenStatusParams,
                l_intPageIndex,
                l_intPageSize);
        
        //指定したレコードを取得(基本設計と異なり、「ページをめくる処理クラス」を利用したので)
        l_accountOpenStatusParams = 
            (UwgAccountOpenStatusParams[])l_pageIndexInfo.getArrayReturned(
                UwgAccountOpenStatusParams.class); 
        
        //拡張アカウントマネージャ取得する   
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        UwgAccountOpenStatusParams l_params = null;
        
        //1.10.5 外株口座開設申込明細インスタンスを生成する

        log.debug("l_accountOpenStatusParams.length = " + l_accountOpenStatusParams.length);
        WEB3FEqConAccountOpenApplyUnit l_accountOpenApplyUnit = null;
        
        for (int i = 0; i < l_accountOpenStatusParams.length; i++)
        {           
            l_params = l_accountOpenStatusParams[i];
            log.debug("UwgAccountOpenStatusParams = " + l_params);
            
            //1.10.1 get質問回答(String, String, String)
            //[引数] 
            //証券会社コード：　@UWG口座開設状況Params.証券会社コード 
            //部店コード：　@UWG口座開設状況Params.部店コード 
            //識別コード：　@UWG口座開設状況Params.識別コード
            QuestionAnswerParams[] l_questionAnswerParams =
                l_conTransferDataControlService.getQuestionAnswer(
                    l_params.getInstitutionCode(),
                    l_params.getBranchCode(),
                    l_params.getOrderRequestNumber());           
            
            //1.10.2 create外株口座開設質問情報一覧(質問回答Params[])
            //[引数] 
            //質問回答一覧：　@get質問回答()の戻り値
            WEB3FEqConAccountOpenQuestionInfo[] l_accountOpenQuestionInfo = 
                this.createFeqAccOpenQuestionInfoList(l_questionAnswerParams);
            
            // update start
            
            String l_strOperationDiv;
            String l_mainAccountName;
            boolean l_mainAccountFlg = true;
            try
            {
				//1.10.3 get顧客(String, String, String)
				//[引数] 
				//証券会社コード：　@UWG口座開設状況Params.証券会社コード 
				//部店コード：　@UWG口座開設状況Params.部店コード 
				//口座コード：　@UWG口座開設状況Params.顧客コード
				WEB3GentradeMainAccount l_mainAccount = 
					l_accountManager.getMainAccount(
						l_params.getInstitutionCode(),
						l_params.getBranchCode(),
						l_params.getAccountCode()
						);
            
				//1.10.4 get処理区分(UWG口座開設状況Params)
				//[引数] 
				//UWG口座開設状況Params：　@UWG口座開設状況Params
				l_strOperationDiv = this.getOperationDiv(l_params);
				
				//顧客名を取得
				l_mainAccountName = l_mainAccount.getDisplayAccountName();
            }
            catch(WEB3BaseException l_ex)
            {
            	l_strOperationDiv = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_90000009;
            	l_mainAccountName = null;
            	l_mainAccountFlg = false;
            }
            
            //1.10.6 (*)外株口座開設申込明細に以下のプロパティをセットする。
            l_accountOpenApplyUnit = new WEB3FEqConAccountOpenApplyUnit();
            
            //識別コード  ＝ UWG口座開設状況Params.識別コード
            l_accountOpenApplyUnit.requestNumber = l_params.getOrderRequestNumber();
            log.debug("識別コード  ＝ " + l_accountOpenApplyUnit.requestNumber);
            
            //部店コード  ＝ UWG口座開設状況Params.部店コード
            l_accountOpenApplyUnit.branchCode = l_params.getBranchCode();
            log.debug("部店コード  ＝ " + l_accountOpenApplyUnit.branchCode);
            
            //顧客コード  ＝ UWG口座開設状況Params.顧客コードの上6桁
            l_accountOpenApplyUnit.accountCode = l_params.getAccountCode().substring(0, 6);
            
            //顧客名   ＝ get顧客()の戻り値.顧客名
            //get顧客()にて例外が発生した場合、「NULL」をセット
			l_accountOpenApplyUnit.accountName = l_mainAccountName;
            log.debug("顧客名 ＝ " + l_accountOpenApplyUnit.accountName);
            
            //申込日時    ＝ UWG口座開設状況Params.作成日付
            l_accountOpenApplyUnit.applyDate = l_params.getCreatedTimestamp();
            log.debug("申込日時 ＝ " + l_accountOpenApplyUnit.applyDate);
            
            //開設日時      ＝　@（以下のとおり）
            //  UWG口座開設状況Params.口座開設状況区分 == "口座開設完了"の場合、UWG口座開設状況Params.更新日付
            if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(
                    l_params.getAccountOpenStatusDiv()))
            {
                l_accountOpenApplyUnit.openDate = l_params.getLastUpdatedTimestamp();
            }
            //UWG口座開設状況Params.口座開設状況区分 != "口座開設完了"の場合、null
            else
            {
                l_accountOpenApplyUnit.openDate = null;
            }
            log.debug("開設日時 ＝ " + l_accountOpenApplyUnit.openDate);

            //ステータス区分 ＝UWG口座開設状況Params.口座開設状況区分
            l_accountOpenApplyUnit.statusDiv = l_params.getAccountOpenStatusDiv();
            log.debug("ステータス区分 ＝ " + l_accountOpenApplyUnit.statusDiv);
            
            //送受信区分  ＝ UWG口座開設状況Params.送受信区分
            l_accountOpenApplyUnit.sendRcvDiv = l_params.getSendRcvDiv();
            log.debug("送受信区分 ＝ " + l_accountOpenApplyUnit.sendRcvDiv);
            
            //名前(姓)    ＝　@UWG口座開設状況Params.名前(姓)
            l_accountOpenApplyUnit.familyName = l_params.getLastName();
            log.debug("名前(姓) ＝ " + l_accountOpenApplyUnit.familyName);
            
            //名前(名)    ＝　@UWG口座開設状況Params.名前(名)
            l_accountOpenApplyUnit.name = l_params.getFirstName();
            log.debug("名前(名) ＝ " + l_accountOpenApplyUnit.name);
            
            //外株口座番号 ＝　@UWG口座開設状況Params.外国株式口座番号
            l_accountOpenApplyUnit.fstkAccountCode = l_params.getFeqAccountCode();
            log.debug("外株口座番号 ＝ " + l_accountOpenApplyUnit.fstkAccountCode);
            
            //メールアドレス  ＝　@UWG口座開設状況Params.メールアドレス
            l_accountOpenApplyUnit.mailAddress = l_params.getMailAddress();
            log.debug("メールアドレス ＝ " + l_accountOpenApplyUnit.mailAddress);
            
            //備考   ＝　@get処理区分()の戻り値
            //get顧客()にて例外が発生した場合、「90000009(口座抹消)」をセット
            l_accountOpenApplyUnit.biko = l_strOperationDiv;
            log.debug("備考 ＝ " + l_accountOpenApplyUnit.biko);
            
            //質問情報一覧 ＝　@create外株口座開設質問情報一覧()の戻り値
            l_accountOpenApplyUnit.questionInfoList = l_accountOpenQuestionInfo;
            
            //更新可能フラグ  ＝（以下のとおり）
            //  UWG口座開設状況Params.口座開設状況区分 == "口座開設中"の場合、true
            if (l_mainAccountFlg)
            {
				if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
						l_params.getAccountOpenStatusDiv()))
				{
					l_accountOpenApplyUnit.updateFlag = true;
				}
				//  UWG口座開設状況Params.口座開設状況区分 != "口座開設中"の場合、false
				else
				{
					l_accountOpenApplyUnit.updateFlag = false;
				}
            }
            else
            {
				l_accountOpenApplyUnit.updateFlag = false;
            }
            
            log.debug("更新可能フラグ  ＝ " + l_accountOpenApplyUnit.updateFlag);

            //1.10.7 ArrayListに外株口座開設申込明細を追加する
            //[引数] 
            //arg0：　@外株口座開設申込明細オブジェクト
            
            l_lisAccountOpenApplyUnit.add(l_accountOpenApplyUnit);
        }
        
        //1.11 外株口座開設申込明細の配列を生成する
        WEB3FEqConAccountOpenApplyUnit[] l_accountOpenApplyUnits = 
            new WEB3FEqConAccountOpenApplyUnit[l_lisAccountOpenApplyUnit.size()];
        
        l_lisAccountOpenApplyUnit.toArray(l_accountOpenApplyUnits);
        
        //1.12 createResponse()
        WEB3AdminFEqConAccountOpenMngListResponse l_response = 
            (WEB3AdminFEqConAccountOpenMngListResponse)l_request.createResponse();
        
        //1.13 (*)レスポンスデータにプロパティをセットする。
        //外株口座開設申込明細一覧  ＝　@toArray()の戻り値
        l_response.fstkAccountOpenApplyList = l_accountOpenApplyUnits;
        //表示ページ番号           ＝　@toIndex / リクエストデータ.ページ内表示行数
        //                            ※計算結果は小数点以下一位を切り上げた整数値とする。
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        log.debug("表示ページ番号  ＝ " + l_response.pageIndex);
        
        //総レコード数             ＝　@getUWG口座開設状況()の戻り値の要素数
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        log.debug("総レコード数  ＝ " + l_response.totalRecords);
        
        //総ページ数               ＝　@総レコード数 / リクエストデータ.ページ内表示行数
        //                           ※計算結果は小数点以下一位を切り上げた整数値とする。
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        log.debug("総ページ数  ＝ " + l_response.totalPages);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * ダウンロードファ@イルの取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座開設管理）getダウンロードファ@イル」 参照
     * ------------------------------------------------
     * 1.8 getUWG口座開設状況(String, String[], String)
     *  nullが返却された場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01948<BR>
     * <BR>
     * ------------------------------------------------
     * 1.11 get明細行数( )
     *  get明細行数()の戻り値 > リクエストデータ.ダウンロード件数 の場合、
     *  例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01957<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountOpenMngListDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 41F8D68400A3
     */
    protected WEB3AdminFEqConAccountOpenMngListDownloadResponse getDownloadFile(
        WEB3AdminFEqConAccountOpenMngListDownloadRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getDownloadFile(WEB3AdminFEqConAccountOpenMngListDownloadRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.外国株式振替連携(口座管理・口座開設管理) 
        //is更新：　@false(更新なし)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, false);
        
        //1.4 get証券会社コード()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5 create検索条件文字列(String[], String, String, String)
        //[引数] 
        //部店コード：　@リクエストデータ.部店コード 
        //ステータス区分：　@リクエストデータ.ステータス区分 
        //申込日（自）：　@リクエストデータ.申込日（自） 
        //申込日（至）：　@リクエストデータ.申込日（至）
        String l_strCondition = 
            this.createQueryString(
                l_request.branchCode,
                l_request.statusDiv,
                l_request.applyDateFrom,
                l_request.applyDateTo);
        log.debug("create検索条件文字列 = " + l_strCondition);
        
        //1.6 create検索条件データコンテナ(String, String[], String, String, String)
        //[引数] 
        //証券会社コード：　@get証券会社コード()の戻り値 
        //部店コード：　@リクエストデータ.部店コード 
        //ステータス区分：　@リクエストデータ.ステータス区分 
        //申込日（自）：　@リクエストデータ.申込日（自） 
        //申込日（至）：　@リクエストデータ.申込日（至）
        String[] l_strValue = 
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.statusDiv,
                l_request.applyDateFrom,
                l_request.applyDateTo);
        
        //1.7 createソート条件()
        String l_strSortCond = this.createSortCond();
        log.debug("createソート条件 = " + l_strSortCond);
        
        //1.8 getUWG口座開設状況(String, String[], String)
        //[引数] 
        //検索条件文字列：　@create検索条件文字列()の戻り値 
        //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値 
        //ソート条件：　@createソート条件()の戻り値
        
        //get 外株振替連携データ制御サービスImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgAccountOpenStatusParams[] l_accountOpenStatusParams =
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_strCondition,
                l_strValue,
                l_strSortCond);
        
        //getUWG口座開設状況(String, String[], String)nullが返却された場合、例外をスローする。
        if (l_accountOpenStatusParams == null)
        {
            log.debug("getUWG口座開設状況(String, String[], String)戻り値がnull");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                this.getClass().getName() + "." + l_strMethodName,
                "getUWG口座開設状況(String, String[], String)戻り値がnull");            
        }
        
        //1.9 外株口座開設管理一覧CSVインスタンスを生成する
        WEB3AdminFEqConAccountOpenMngListCsv l_accountOpenMngListCsv =
            new WEB3AdminFEqConAccountOpenMngListCsv();
        
        log.debug("l_accountOpenStatusParams.length = " + l_accountOpenStatusParams.length);
        
        //1.10 getUWG口座開設状況()の戻り値の各要素について、Loop処理を実施する
        for (int i = 0; i < l_accountOpenStatusParams.length; i++)
        {
            //1.10.1 add明細行()
            int l_intRow = l_accountOpenMngListCsv.addRow();
            
            //1.10.2 set証券会社コード(int, String)
            //[引数] 
            //行番号： add明細行()の戻り値 
            //値： UWG口座開設状況Params.証券会社コード 
            l_accountOpenMngListCsv.setInstitutionCode(
                l_intRow, l_accountOpenStatusParams[i].getInstitutionCode());
            
            //1.10.3 set部店コード(int, String)
            //[引数] 
            //行番号： add明細行()の戻り値 
            //値： UWG口座開設状況Params.部店コード
            l_accountOpenMngListCsv.setBranchCode(
                l_intRow, l_accountOpenStatusParams[i].getBranchCode());
            
            //1.10.4 set顧客コード(int, String)
            //[引数] 
            // 行番号： add明細行()の戻り値 
            // 値： UWG口座開設状況Params.顧客コード 
            l_accountOpenMngListCsv.setAccountCode(
                l_intRow, l_accountOpenStatusParams[i].getAccountCode());
            
            //1.10.5 set識別コード(int, String)
            //[引数] 
            // 行番号： add明細行()の戻り値 
            // 値： UWG口座開設状況Params.識別コード 
            l_accountOpenMngListCsv.setRequestNumber(
                l_intRow, l_accountOpenStatusParams[i].getOrderRequestNumber());
            
            //1.10.6 set名前（姓）(int, String)
            //[引数] 
            // 行番号： add明細行()の戻り値 
            // 値： UWG口座開設状況Params.名前（姓）
            l_accountOpenMngListCsv.setFamilyName(
                l_intRow, l_accountOpenStatusParams[i].getLastName());
            
            //1.10.7 set名前（名）(int, String)
            //[引数] 
            // 行番号： add明細行()の戻り値 
            // 値： UWG口座開設状況Params.名前（名）
            l_accountOpenMngListCsv.setName(
                l_intRow, l_accountOpenStatusParams[i].getFirstName());

            //1.10.8 setメールアドレス(int, String)
            //[引数] 
            // 行番号： add明細行()の戻り値 
            // 値： UWG口座開設状況Params.メールアドレス 
            l_accountOpenMngListCsv.setMailAddress(
                l_intRow, l_accountOpenStatusParams[i].getMailAddress());
            
            //1.10.9 set外国株式口座番号(int, String)
            //[引数] 
            //行番号： add明細行()の戻り値 
            //値： UWG口座開設状況Params.外国株式口座番号
            l_accountOpenMngListCsv.setFeqAccountCode(
                l_intRow, l_accountOpenStatusParams[i].getFeqAccountCode());
            
            //1.10.10 set口座開設状況区分(int, String)
            //[引数] 
            //行番号： add明細行()の戻り値 
            //値： UWG口座開設状況Params.口座開設状況区分
            l_accountOpenMngListCsv.setAccountOpenStatusDiv(
                l_intRow, l_accountOpenStatusParams[i].getAccountOpenStatusDiv());
            
            //1.10.11 set送受信区分(int, String)
            //[引数] 
            //行番号： add明細行()の戻り値 
            //値： UWG口座開設状況Params.送受信区分
            l_accountOpenMngListCsv.setSendRcvDiv(
                l_intRow, l_accountOpenStatusParams[i].getSendRcvDiv());
            
            //1.10.12 set受付結果コード(int, String)
            //[引数] 
            //行番号： add明細行()の戻り値 
            //値： UWG口座開設状況Params.受付結果コード 
            l_accountOpenMngListCsv.setResultCode(
                l_intRow, l_accountOpenStatusParams[i].getResultCode());
            
            //1.10.13 setエラー理由コード(int, String)
            //[引数] 
            //行番号： add明細行()の戻り値 
            //値： UWG口座開設状況Params.エラー理由コード 
            l_accountOpenMngListCsv.setErrorReasonCode(
                l_intRow, l_accountOpenStatusParams[i].getErrorReasonCode());
            
            //1.10.14 set更新者コード(int, String)
            //[引数] 
            //行番号： add明細行()の戻り値 
            //値： UWG口座開設状況Params.更新者コード 
            l_accountOpenMngListCsv.setLastUpdater(
                l_intRow, l_accountOpenStatusParams[i].getLastUpdater());
            
            //1.10.15 set作成日付(int, Date)
            //[引数] 
            //行番号： add明細行()の戻り値 
            //値： UWG口座開設状況Params.作成日付
            l_accountOpenMngListCsv.setCreatedTimestamp(
                l_intRow, l_accountOpenStatusParams[i].getCreatedTimestamp());
            
            //1.10.16 set更新日付(int, Date)
            //[引数] 
            //行番号： add明細行()の戻り値 
            //値： UWG口座開設状況Params.更新日付
            l_accountOpenMngListCsv.setLastUpdatedTimestamp(
                l_intRow, l_accountOpenStatusParams[i].getLastUpdatedTimestamp());            
        }
        
        //1.11 get明細行数()
        int l_intRowCount = l_accountOpenMngListCsv.getRowCount();
        log.debug("get明細行数() ＝ " + l_intRowCount);
        log.debug("リクエストデータ.ダウンロード件数 ＝ " + l_request.downloadNumber);
        
        //get明細行数()の戻り値 > リクエストデータ.ダウンロード件数 の場合、
        //例外をスローする。
        if (l_intRowCount > Integer.parseInt(l_request.downloadNumber))
        {
            log.debug("get明細行数()の戻り値 > リクエストデータ.ダウンロード件数");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01957,
                this.getClass().getName() + "." + l_strMethodName,
                "get明細行数()の戻り値 = [" + l_intRowCount +"], " +
                "リクエストデータ.ダウンロード件数 = [" + l_request.downloadNumber + "]");            
        }
        
        //1.12 getCSVファ@イル行()
        String[] l_strCsvFileLines = l_accountOpenMngListCsv.getCsvFileLines();
         
        //1.13 createResponse()
        WEB3AdminFEqConAccountOpenMngListDownloadResponse l_response = 
            (WEB3AdminFEqConAccountOpenMngListDownloadResponse)l_request.createResponse();
        
        //1.14 (*)レスポンスデータにプロパティをセットする。
        //ダウンロードファ@イル  ＝　@getCSVファ@イル()の戻り値
        l_response.downloadFile = l_strCsvFileLines;
        
        //総レコード数  ＝　@get明細行数()の戻り値
        l_response.totalRecords = l_intRowCount + "";
        log.debug("総レコード数 ＝ " + l_response.totalRecords);
        
        //現在日時  ＝　@システムタイムスタンプ
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        log.debug("現在日時 ＝ " + l_response.currentDate);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (getステータス更新画面)<BR>
     * ステータス更新画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座開設管理）getステータス更新画面」 参照
     * ------------------------------------------------
     * 1.6 getUWG口座開設状況(String, String, String)
     *  nullが返却された場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01948<BR>
     * <BR>
     * ------------------------------------------------
     * 1.7 (*)口座開設中チェック
     *  (口座開設中チェック)
     *  getUWG口座開設状況()の戻り値.口座開設状況区分 != "口座開設中"の場合、
     *  例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01807<BR>
     * <BR>
     * ------------------------------------------------
     * <BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B37002BA
     */
    protected WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse getStatusUpdScreen(
        WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getStatusUpdScreen(WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.外国株式振替連携(口座管理・口座開設管理) 
        //is更新：　@true(更新なし)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, true);
        
        //1.4 validate部店権限(String)
        //[引数] 
        //部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 get証券会社コード()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.6 getUWG口座開設状況(String, String, String)
        //[引数] 
        //証券会社コード：　@get証券会社コード()の戻り値 
        //部店コード：　@リクエストデータ.部店コード 
        //識別コード：　@リクエストデータ.識別コード
        
        //get 外株振替連携データ制御サービスImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgAccountOpenStatusParams l_accountOpenStatusParams =
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber);
        
        //getUWG口座開設状況(String, String, String)nullが返却された場合、例外をスローする。
        if (l_accountOpenStatusParams == null)
        {
            log.debug("getUWG口座開設状況(String, String[], String)戻り値がnull");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                this.getClass().getName() + "." + l_strMethodName,
                "getUWG口座開設状況(String, String[], String)戻り値がnull");            
        }
        
        //1.7 (口座開設中チェック)
        //getUWG口座開設状況()の戻り値.口座開設状況区分 != "口座開設中"の場合、
        //例外をスローする。
        if (!WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_accountOpenStatusParams.getAccountOpenStatusDiv()))
        {
            log.debug("getUWG口座開設状況()の戻り値.口座開設状況区分 != 口座開設中");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01807,
                this.getClass().getName() + "." + l_strMethodName,
                "getUWG口座開設状況()の戻り値.口座開設状況区分 != 口座開設中");            
        }
        
        //1.8 createResponse()
        WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse l_response =
            (WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse)l_request.createResponse();
        
        //1.9 (*)レスポンスデータにプロパティをセットする。
        //部店コード       ＝　@UWG口座開設状況Params.部店コード
        l_response.branchCode = l_accountOpenStatusParams.getBranchCode();
        
        //顧客コード       ＝　@UWG口座開設状況Params.顧客コードの上6桁
        l_response.accountCode = l_accountOpenStatusParams.getAccountCode().substring(0, 6);
        
        //名前(姓)       ＝　@UWG口座開設状況Params.名前(姓)
        l_response.accountFamilyName = l_accountOpenStatusParams.getLastName();
        log.debug("名前(姓) : " + l_response.accountFamilyName);
        
        //名前(名)       ＝　@UWG口座開設状況Params.名前(名)
        l_response.accountName = l_accountOpenStatusParams.getFirstName();
        log.debug("名前(名) : " + l_response.accountName);
        
        //メールアドレス ＝　@UWG口座開設状況Params.メールアドレス
        l_response.mailAddress = l_accountOpenStatusParams.getMailAddress();
        log.debug("メールアドレス : " + l_response.mailAddress);
        
        //外株口座番号  ＝　@UWG口座開設状況Params.外国株式口座番号
        l_response.fstkAccountCode = l_accountOpenStatusParams.getFeqAccountCode();
        log.debug("外株口座番号 : " + l_response.fstkAccountCode);
        
        //ステータス区分 ＝　@UWG口座開設状況Params.口座開設状況区分
        l_response.statusDiv = l_accountOpenStatusParams.getAccountOpenStatusDiv();
        log.debug("ステータス区分 : " + l_response.statusDiv);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (validateステータス更新)<BR>
     * ステータス更新の確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座開設管理）validateステータス更新」 参照
     * ------------------------------------------------
     * 1.6 getUWG口座開設状況(String, String, String)
     *  nullが返却された場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01948<BR>
     * <BR>
     * ------------------------------------------------
     * 1.7 (*)口座開設中チェック
     *  (口座開設中チェック)
     *  getUWG口座開設状況()の戻り値.口座開設状況区分 != "口座開設中"の場合、
     *  例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01807<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B37002D9
     */
    protected WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse validateStatusUpd(
        WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "validateStatusUpd(WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.外国株式振替連携(口座管理・口座開設管理) 
        //is更新：　@true(更新なし)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, true);
        
        //1.4 validate部店権限(String)
        //[引数] 
        //部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 get証券会社コード()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.6 getUWG口座開設状況(String, String, String)
        //[引数] 
        //証券会社コード：　@get証券会社コード()の戻り値 
        //部店コード：　@リクエストデータ.部店コード 
        //識別コード：　@リクエストデータ.識別コード
        
        //get 外株振替連携データ制御サービスImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgAccountOpenStatusParams l_accountOpenStatusParams =
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber);
        
        //getUWG口座開設状況(String, String, String)nullが返却された場合、例外をスローする。
        if (l_accountOpenStatusParams == null)
        {
            log.debug("getUWG口座開設状況(String, String[], String)戻り値がnull");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                this.getClass().getName() + "." + l_strMethodName,
                "getUWG口座開設状況(String, String[], String)戻り値がnull");            
        }
        
        //1.7 (口座開設中チェック)
        //getUWG口座開設状況()の戻り値.口座開設状況区分 != "口座開設中"の場合、
        //例外をスローする。
        if (!WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_accountOpenStatusParams.getAccountOpenStatusDiv()))
        {
            log.debug("getUWG口座開設状況()の戻り値.口座開設状況区分 != 口座開設中");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01807,
                this.getClass().getName() + "." + l_strMethodName,
                "getUWG口座開設状況()の戻り値.口座開設状況区分 != 口座開設中");            
        }
        
        //1.8 createResponse()
        WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse l_response =
            (WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse)l_request.createResponse();
        
        log.exiting(l_strMethodName);
        
     return l_response;
    }
    
    /**
     * (submitステータス更新)<BR>
     * ステータス更新の完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座開設管理）submitステータス更新」 参照
     * ------------------------------------------------
     * 1.8 getUWG口座開設状況(String, String, String)
     *  nullが返却された場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01948<BR>
     * <BR>
     * ------------------------------------------------
     * 1.9 (*)口座開設中チェック
     *  (口座開設中チェック)
     *  getUWG口座開設状況()の戻り値.口座開設状況区分 != "口座開設中"の場合、
     *  例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01807<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B37002F8
     */
    protected WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse submitStatusUpd(
            WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "submitStatusUpd(WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.外国株式振替連携(口座管理・口座開設管理) 
        //is更新：　@true(更新なし)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, true);
        
        //1.4 validate取引パスワード(String)
        //[引数] 
        //パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.5 validate部店権限(String)
        //[引数] 
        //部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.6 get管理者コード()
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        log.debug("get管理者コード() = " + l_strAdministratorCode);
        
        //1.7 get証券会社コード()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.8 getUWG口座開設状況(String, String, String)
        //[引数] 
        //証券会社コード：　@get証券会社コード()の戻り値 
        //部店コード：　@リクエストデータ.部店コード 
        //識別コード：　@リクエストデータ.識別コード
        
        //get 外株振替連携データ制御サービスImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgAccountOpenStatusParams l_accountOpenStatusParams =
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber);
        
        //getUWG口座開設状況(String, String, String)nullが返却された場合、例外をスローする。
        if (l_accountOpenStatusParams == null)
        {
            log.debug("getUWG口座開設状況(String, String[], String)戻り値がnull");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                this.getClass().getName() + "." + l_strMethodName,
                "一覧に表示するデータがありません");            
        }
                 
        log.debug("l_accountOpenStatusParams = " + l_accountOpenStatusParams);
        log.debug("getUWG口座開設状況()の戻り値.口座開設状況区分 = " + 
                l_accountOpenStatusParams.getAccountOpenStatusDiv());
        
        //1.9 (口座開設中チェック)
        //getUWG口座開設状況()の戻り値.口座開設状況区分 != "口座開設中"の場合、
        //例外をスローする。
        if (!WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_accountOpenStatusParams.getAccountOpenStatusDiv()))
        {
            log.debug("getUWG口座開設状況()の戻り値.口座開設状況区分 != 口座開設中");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01807,
                this.getClass().getName() + "." + l_strMethodName,
                "外国株式口座が開設されていません");            
        }
        
        //1.10 updateUWG口座開設状況(UWG口座開設状況Params, String, String)
        //[引数] 
        //UWG口座開設状況Params：　@getUWG口座開設状況Params()の戻り値 
        //更新後ステータス区分：　@リクエストデータ.更新後ステータス区分 
        //更新者コード：　@get管理者コード()の戻り値
        l_conTransferDataControlService.updateUwgAccountOpenStatus(
            l_accountOpenStatusParams,
            l_request.updatedStatusDiv,
            l_strAdministratorCode);
        
        log.debug("リクエストデータ.更新後ステータス区分 = " + l_request.updatedStatusDiv);
        
        //1.11 リクエストデータ.更新後ステータス区分 == "口座開設完了"の場合
        if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(l_request.updatedStatusDiv))
        {
            //1.11.1 insert外国株式顧客(UWG口座開設状況Params, String)
            //[引数] 
            //UWG口座開設状況Params：　@getUWG口座開設状況()の戻り値 
            //更新者コード：　@get管理者コード()の戻り値
            l_conTransferDataControlService.insertFeqAccount(
                l_accountOpenStatusParams, l_strAdministratorCode);
            
            //1.11.2 get顧客(String, String, String)
            //[引数] 
            //証券会社コード：　@getUWG口座開設状況()の戻り値.証券会社コード 
            //部店コード：　@getUWG口座開設状況()の戻り値.部店コード 
            //口座コード：　@getUWG口座開設状況()の戻り値.顧客コード
            
            //拡張アカウントマネージャ取得する   
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            WEB3GentradeMainAccount l_mainAccount = 
                l_accountManager.getMainAccount(
                    l_accountOpenStatusParams.getInstitutionCode(),
                    l_accountOpenStatusParams.getBranchCode(),
                    l_accountOpenStatusParams.getAccountCode());
            
            log.debug("get顧客()の戻り値.MRF口座開設区分 = " + ((MainAccountRow)
                    l_mainAccount.getDataSourceObject()).getMrfAccOpenDiv());
                    
            //1.11.3 MRF口座未開設(get顧客()の戻り値.MRF口座開設区分 == "DEFAULT(口座なし)")の場合
            if (WEB3AccountOpenDef.NOT_OPEN.equals(
                    ((MainAccountRow)l_mainAccount.getDataSourceObject()).getMrfAccOpenDiv()))
            {
                //1.11.3.1 update外株口座開設区分(String, String, String, String, String)
                //[引数] 
                //証券会社コード：　@getUWG口座開設状況()の戻り値.証券会社コード 
                //部店コード：　@getUWG口座開設状況()の戻り値.部店コード 
                //顧客コード：　@getUWG口座開設状況()の戻り値.顧客コード 
                //更新後外株口座開設区分：　@"開設" 
                //更新者コード：　@get管理者コード()の戻り値
                l_conTransferDataControlService.updateFeqAccountOpenDiv(
                        l_accountOpenStatusParams.getInstitutionCode(),
                        l_accountOpenStatusParams.getBranchCode(),
                        l_accountOpenStatusParams.getAccountCode(),
                        WEB3ForeignSecAccOpenDiv.OPEN,
                        l_strAdministratorCode);
            }
        }
        
        //1.12 createResponse()
        WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse l_response =
            (WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse)l_request.createResponse();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）空の検索条件文字列(：String)を生成する。<BR>
     * <BR>
     * ２）証券会社条件を検索条件に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += " institution_code = ? "<BR>
     * <BR>
     * ３）部店条件を検索条件に追加する。<BR>
     * 　@パラメータ.部店コード[]の要素数分"?"を追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += " and branch_code in (?,?,,,) "<BR>
     * <BR>
     * ４）パラメータ.ステータス区分 != nullの場合、<BR>
     * 　@口座開設状況区分を検索条件に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and account_open_status_div = ? "<BR>
     * <BR>
     * ５）パラメータ.申込日（自） != nullの場合、<BR>
     * 　@申込日の下限を検索条件に追加する。<BR>
     * <BR>
     *  検索条件文字列 += "and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? "<BR>
     * <BR>
     * ６）パラメータ.申込日（至） != nullの場合、<BR>
     * 　@申込日の下限を検索条件に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and to_char(created_timestamp, 'YYYYMMDDHH24') < ? "<BR>
     * <BR>
     * ７）作成した検索条件文字列を返却する。
     * @@param l_strBranchCodes - 部店コードの配列
     * @@param l_strStatusDiv - ステータス区分
     * 
     * 0：口座開設中
     * 1：口座開設完了
     * 2：口座開設エラー
     * 9：削除
     * 
     * @@param l_strApplyDateFrom - 申込日（自）
     * (YYYYMMDDhh)
     * @@param l_strApplyDateTo - 申込日（至）
     * (YYYYMMDDhh)
     * @@return String
     * @@roseuid 41E5E3FB01EA
     */
    protected String createQueryString(
            String[] l_strBranchCodes, 
            String l_strStatusDiv, 
            String l_strApplyDateFrom, 
            String l_strApplyDateTo) 
    {
        String l_strMethodName = 
            "createQueryString(String[] l_strBranchCodes, String l_strStatusDiv, String l_strApplyDateFrom, String l_strApplyDateTo)";
        log.entering(l_strMethodName);
        
        if (l_strBranchCodes == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //１）空の検索条件文字列(：String)を生成する。
        StringBuffer l_strBuffer = new StringBuffer();
        
        //２）証券会社条件を検索条件に追加する。       
        //　@検索条件文字列 += " institution_code = ? "
        l_strBuffer.append(" institution_code = ? ");
        
        //３）部店条件を検索条件に追加する。
        //　@パラメータ.部店コード[]の要素数分"?"を追加する。        
        //　@検索条件文字列 += " and branch_code in (?,?,,,) "
        StringBuffer l_strBufferBranch = new StringBuffer();
        
        l_strBufferBranch.append(" and branch_code in ( ? ");        
        for (int i = 1; i < l_strBranchCodes.length; i++)
        {
            l_strBufferBranch.append(", ? ");
        }        
        l_strBufferBranch.append(")");
        
        l_strBuffer.append(l_strBufferBranch);
        
        //４）パラメータ.ステータス区分 != nullの場合、
        //　@口座開設状況区分を検索条件に追加する。        
        //　@検索条件文字列 += "and account_open_status_div = ? "
        if (!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_strBuffer.append(" and account_open_status_div = ? ");
        }
        
        //５）パラメータ.申込日（自） != nullの場合、
        //　@申込日の下限を検索条件に追加する。        
        // 検索条件文字列 += "and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? "
        if (!WEB3StringTypeUtility.isEmpty(l_strApplyDateFrom))
        {
            l_strBuffer.append(" and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? ");
        }
        
        //６）パラメータ.申込日（至） != nullの場合、
        //　@申込日の下限を検索条件に追加する。        
        //　@検索条件文字列 += "and to_char(created_timestamp, 'YYYYMMDDHH24') < ? "
        if (!WEB3StringTypeUtility.isEmpty(l_strApplyDateTo))
        {
            l_strBuffer.append(" and to_char(created_timestamp, 'YYYYMMDDHH24') < ? ");
        }
        
        //７）作成した検索条件文字列を返却する。
        String l_strCondition = l_strBuffer.toString();
        
        log.exiting(l_strMethodName);
        
        return l_strCondition;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）証券会社条件を追加する。<BR>
     * 　@パラメータ.証券会社コードをArrayListに追加する。<BR>
     * <BR>
     * ３）部店条件を追加する。<BR>
     * 　@パラメータ.部店コードの全要素をArrayListに追加する。<BR>
     * <BR>
     * ４）パラメータ.ステータス区分 != nullの場合、<BR>
     * 　@パラメータ.ステータス区分をArrayListに追加する。<BR>
     * <BR>
     * ５）パラメータ.申込日（自） != nullの場合、<BR>
     * 　@パラメータ.申込日（自）をArrayListに追加する。<BR>
     * <BR>
     * ６）パラメータ.申込日（至） != nullの場合、<BR>
     * 　@パラメータ.申込日（至）をArrayListに追加する。<BR>
     * <BR>
     * ７）生成したArrayList.toArray()の戻り値を返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コードの配列
     * @@param l_strStatusDiv - ステータス区分
     * 
     * 0：口座開設中
     * 1：口座開設完了
     * 2：口座開設エラー
     * 9：削除
     * @@param l_strApplyDateFrom - 申込日（自）
     * (YYYYMMDDhh)
     * @@param l_strApplyDateTo - 申込日（至）
     * (YYYYMMDDhh)
     * @@return String[]
     * @@roseuid 41E5E3FB01F0
     */
    protected String[] createQueryContainer(
            String l_strInstitutionCode, 
            String[] l_strBranchCodes, 
            String l_strStatusDiv, 
            String l_strApplyDateFrom, 
            String l_strApplyDateTo) 
    {
        String l_strMethodName = 
            "createQueryContainer(String l_strInstitutionCode, String[] l_strBranchCodes, String l_strStatusDiv, String l_strApplyDateFrom, String l_strApplyDateTo)";
        log.entering(l_strMethodName);
        
        if (l_strBranchCodes == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //１）ArrayListを生成する。
        List l_lisValue = new ArrayList();
        
        //２）証券会社条件を追加する。
        //　@パラメータ.証券会社コードをArrayListに追加する。
        l_lisValue.add(l_strInstitutionCode);
        
        //３）部店条件を追加する。
        //　@パラメータ.部店コードの全要素をArrayListに追加する。
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisValue.add(l_strBranchCodes[i]);
        }       
        
        //４）パラメータ.ステータス区分 != nullの場合、
        //　@パラメータ.ステータス区分をArrayListに追加する。
        if (!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_lisValue.add(l_strStatusDiv);
        }
        
        //５）パラメータ.申込日（自） != nullの場合、
        //　@パラメータ.申込日（自）をArrayListに追加する。
        if (!WEB3StringTypeUtility.isEmpty(l_strApplyDateFrom))
        {
            l_lisValue.add(l_strApplyDateFrom);
        }
        
        //６）パラメータ.申込日（至） != nullの場合、
        //　@パラメータ.申込日（至）をArrayListに追加する。
        if (!WEB3StringTypeUtility.isEmpty(l_strApplyDateTo))
        {
            l_lisValue.add(l_strApplyDateTo);
        }
        
        //７）生成したArrayList.toArray()の戻り値を返却する。
        String[] l_strValue = new String[l_lisValue.size()];
        l_lisValue.toArray(l_strValue);
        
        log.exiting(l_strMethodName);
        
        return l_strValue;
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件を作成する。<BR>
     * <BR>
     * １）空のソート条件文字列(：String)を生成する。<BR>
     * <BR>
     * ２）以下のソート条件を表すソート条件文字列を作成する。<BR>
     * 　@①@更新日付　@降順<BR>
     * <BR>
     * 　@ソート条件文字列 = " last_updated_timestamp desc"<BR>
     * <BR>
     * ３）作成したソート条件文字列を返却する。
     * @@return String
     * @@roseuid 41E5E3FB01F6
     */
    protected String createSortCond() 
    {
        String l_strMethodName = "createSortCond()";
        log.entering(l_strMethodName);
        
        //１）空のソート条件文字列(：String)を生成する。
        String l_strSort = new String();
        
        //２）以下のソート条件を表すソート条件文字列を作成する。
        //①@作成日付　@降順
       //ソート条件文字列 = " created_timestamp desc"
        l_strSort = " created_timestamp desc";
        
        //３）作成したソート条件文字列を返却する。
        log.exiting(l_strMethodName);
        
        return l_strSort;
    }
    
    /**
     * (create外株口座開設質問情報一覧)<BR>
     * 引数の質問回答一覧より、外株口座開設質問情報の<BR>
     * 一覧を作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）パラメータ.質問回答一覧の要素数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@２－１）外株口座開設質問情報インスタンスを生成する。<BR>
     * <BR>
     * 　@２－２）生成したインスタンスに以下のプロパティセットを行う。<BR>
     * 　@　@質問番号 = 質問回答Params.質問番号<BR>
     * 　@　@質問内容 = null<BR>
     * 　@　@質問回答 = 質問回答Params.質問回答<BR>
     * <BR>
     * 　@２－３）生成したArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * ３）生成したArrayList.toArray()の戻り値を返却する。
     * @@param l_questionAnswerParams - 質問回答Paramsオブジェクトの配列
     * @@return WEB3FEqConAccountOpenQuestionInfo[]
     * @@throws WEB3BaseException
     * @@roseuid 41E5F5B402CD
     */
    protected WEB3FEqConAccountOpenQuestionInfo[] createFeqAccOpenQuestionInfoList(
        QuestionAnswerParams[] l_questionAnswerParams) 
    {
        String l_strMethodName = 
            "createfeqAccOpenQuestionInfoList(QuestionAnswerParams[] l_questionAnswerParams)";
        log.entering(l_strMethodName);
        
        if (l_questionAnswerParams == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //１）ArrayListを生成する。
        List l_lisInfo = new ArrayList();
        
        //２）パラメータ.質問回答一覧の要素数分、
        //　@以下の処理を繰り返す。
        WEB3FEqConAccountOpenQuestionInfo l_conAccountOpenQuestionInfo = null;

        for (int i = 0; i < l_questionAnswerParams.length; i++)
        {
            //　@２－１）外株口座開設質問情報インスタンスを生成する。              
            l_conAccountOpenQuestionInfo = new WEB3FEqConAccountOpenQuestionInfo();
            
            //　@２－２）生成したインスタンスに以下のプロパティセットを行う。
            //　@　@質問番号 = 質問回答Params.質問番号
            l_conAccountOpenQuestionInfo.questionNumber = 
                l_questionAnswerParams[i].getQuestionNo();
            //　@　@質問内容 = null
            l_conAccountOpenQuestionInfo.questionContent = null;
            //　@　@質問回答 = 質問回答Params.質問回答 
            l_conAccountOpenQuestionInfo.questionAnswer =
                l_questionAnswerParams[i].getQuestionAnswer();
            
            //　@２－３）生成したArrayListにプロパティセットしたインスタンスを追加する。
            l_lisInfo.add(l_conAccountOpenQuestionInfo);
        }        
        
        //３）生成したArrayList.toArray()の戻り値を返却する。
        WEB3FEqConAccountOpenQuestionInfo[] l_info = 
            new WEB3FEqConAccountOpenQuestionInfo[l_lisInfo.size()];
        l_lisInfo.toArray(l_info);
        
        log.exiting(l_strMethodName);
        
        return l_info;
    }
    
    /**
     * (get処理区分)<BR>
     * 引数のUWG口座開設状況Paramsより、<BR>
     * 現在の処理区分を判別し、返却する。<BR>
     * ※返却される値は、外株口座開設申込明細.備考のアイテム定義を参照。<BR>
     * <BR>
     * ※UWGシステムのリターンコードが未確定のため、仕様変更の可能性あり。<BR>
     * <BR>
     * 　@パラメータ.UWG口座開設状況Paramsの<BR>
     * 　@　@[口座開設状況区分 == "口座開設中" かつ <BR>
     * 　@　@送受信区分 == "未送信" の場合] <BR>
     * 　@　@"口座開設受付済"を返却する。 <BR>
     * <BR>
     * 　@[口座開設状況区分 == "口座開設中" かつ <BR>
     * 　@　@送受信区分 == "送信済" の場合] <BR>
     * 　@　@"口座開設中"を返却する。 <BR>
     * <BR>
     * 　@[口座開設状況区分 == "口座開設済" の場合]<BR>
     *      "口座開設完了"を返却する。<BR>
     * <BR>
     * 　@[口座開設状況区分 == "口座開設エラー" かつ <BR>
     * 　@　@"送受信区分" == "受信済" の場合] <BR>
     * 　@　@パラメータ.UWG口座開設状況Params.エラー理由コードを返却する。 <BR>
     * <BR>
     * 　@[口座開設区分 == "削除"の場合] <BR>
     * 　@　@"取消済"を返却する。 <BR>
     * <BR>
     * 　@[上記以外の場合] <BR>
     * 　@　@"システムエラー"を返却する。 <BR>
     * <BR>
     * @@param l_uwgAccOpenStatusParams - UWG口座開設状況Paramsオブジェクト
     * @@return String
     * @@roseuid 41E5F6DF006B
     */
    protected String getOperationDiv(UwgAccountOpenStatusParams l_uwgAccOpenStatusParams) 
    {
        String l_strMethodName = 
            "getOperationDiv(UwgAccountOpenStatusParams l_uwgAccOpenStatusParams)";
        log.entering(l_strMethodName);
        
        if (l_uwgAccOpenStatusParams == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        String l_strReturn = null;
        
        //　@パラメータ.UWG口座開設状況Paramsの
        //　@[口座開設状況区分 == "口座開設中" かつ 
        //   送受信区分 == "未送信" の場合] 
        //  "口座開設受付済"を返却する。
        if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_uwgAccOpenStatusParams.getAccountOpenStatusDiv()) &&
            WEB3SendRcvDivDef.NOT_SEND.equals(
                l_uwgAccOpenStatusParams.getSendRcvDiv()))
        {
            l_strReturn = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_10000000;
        }
        
        //　@[口座開設状況区分 == "口座開設中" かつ 
        //   送受信区分 == "送信済" の場合] 
        //  "口座開設中"を返却する。
        else if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_uwgAccOpenStatusParams.getAccountOpenStatusDiv()) &&
             WEB3SendRcvDivDef.SEND_COMPLETE.equals(
                l_uwgAccOpenStatusParams.getSendRcvDiv()))
        {
            l_strReturn = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_20000000;
        }
        
        //　@[口座開設状況区分 == "口座開設済"の場合] 
        //  "口座開設完了"を返却する。
        else if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(
                    l_uwgAccOpenStatusParams.getAccountOpenStatusDiv()))                    
        {
            l_strReturn = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_00000000;
        }
        // [口座開設状況区分 == "口座開設エラー" かつ 
        //  "送受信区分" == "受信済" の場合] 
        // パラメータ.UWG口座開設状況Params.エラー理由コードを返却する。
        else if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(
                    l_uwgAccOpenStatusParams.getAccountOpenStatusDiv()) &&
                 WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(
                    l_uwgAccOpenStatusParams.getSendRcvDiv()))                    
        {
            l_strReturn = l_uwgAccOpenStatusParams.getErrorReasonCode();
        }        
        //　@[口座開設状況区分 == "削除"の場合]
        //　@　@"取消済"を返却する。
        else if (WEB3AccountOpenStatusDivDef.DELETE.equals(
                l_uwgAccOpenStatusParams.getAccountOpenStatusDiv()))
        {
            l_strReturn = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_90000000;
        }
        //　@[上記以外の場合]
        //　@　@"システムエラー"を返却する。
        else
        {
            l_strReturn = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_99999999;
        }
        
        log.exiting(l_strMethodName);
        
        return l_strReturn;
    }
}
@
