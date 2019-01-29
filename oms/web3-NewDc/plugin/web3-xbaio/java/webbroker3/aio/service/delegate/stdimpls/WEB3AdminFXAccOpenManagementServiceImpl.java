head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenManagementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者FX口座開設管理サービス実装クラス(WEB3AdminFXAccOpenManagementServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 于美麗 (中訊) 新規作成
                 : 2006/02/08 譚漢江 (中訊) 仕様変更・モデル466、467、476、482
                 : 2006/02/23 情野（SRA） 仕様変更・モデル501
                 : 2006/02/24 黄浩澎 (中訊) 仕様変更・モデル486
Revesion History : 2008/05/20 柴双紅 (中訊) 仕様変更・モデル860、870
Revesion History : 2008/09/08 許  丹 (中訊) 仕様変更・モデル935
Revesion History : 2008/09/08 許  丹 (中訊) 実装の問題・No016
Revesion History : 2008/09/22 武　@波 (中訊) 仕様変更・モデル1016,1017,1018,1019,1034,1035,1036,1069
                 : 2008/10/29 吉原 (SCS) 仕様変更・モデル1081
                 : 2008/12/16 大嶋 (SCS) 仕様変更・モデル1088,1089,1090
Revesion History : 2009/03/21  車進 (中訊) 仕様変更・モデル1133,ＤＢ更新仕様215
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.WEB3AdminFXAccOpenApplyDownloadCsv;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftAccountOpenStatusRow;
import webbroker3.aio.define.WEB3AioAccOpenRemarkDef;
import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.aio.define.WEB3AioFxAccountOpenDivDef;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyDownloadRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyDownloadResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListConditionRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListConditionResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdInputResponse;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXAccOpenApplyUnit;
import webbroker3.aio.message.WEB3FXTradeAgreementUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXAccOpenManagementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3AccOpenRealUpdateDef;
import webbroker3.common.define.WEB3AccTypeDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3QuestionDivDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeAccOpenDiv;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionAnswerRow;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者FX口座開設管理サービスImpl) <BR>
 * 管理者FX口座開設管理サービス実装クラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenManagementServiceImpl extends
    WEB3ClientRequestService implements WEB3AdminFXAccOpenManagementService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenManagementServiceImpl.class);

    /**
     * (execute) <BR>
     * 管理者FX口座開設管理処理を行う。 <BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドを呼び分ける。 <BR>
     * <BR>
     * ○管理者・FX口座開設申込一覧条件入力リクエストの場合 <BR>
     * this.get条件入力画面()メソッドをコールする。 <BR>
     * <BR>
     * ○管理者・FX口座開設申込一覧リクエストの場合 <BR>
     * this.get一覧画面()メソッドをコールする。 <BR>
     * <BR>
     * ○管理者・FX口座開設ステータス更新入力リクエストの場合 <BR>
     * this.getステータス更新入力画面()メソッドをコールする。 <BR>
     * <BR>
     * ○管理者・FX口座開設ステータス更新確認リクエストの場合 <BR>
     * this.validateステータス更新()メソッドをコールする。 <BR>
     * <BR>
     * ○管理者・FX口座開設ステータス更新完了リクエストの場合 <BR>
     * this.submitステータス更新()メソッドをコールする。
     * <BR>
     * ○管理者・FX口座開設申込ダウンロードリクエストの場合 <BR>
     * this.getダウンロードファ@イル()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD54B9005A
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
        
        if (l_request instanceof WEB3AdminFXAccOpenApplyListConditionRequest)
        {
            // ○管理者・FX口座開設申込一覧条件入力リクエストの場合 
            // this.get条件入力画面()メソッドをコールする。 
            WEB3AdminFXAccOpenApplyListConditionResponse l_Response = 
                this.getCondInputScreen((WEB3AdminFXAccOpenApplyListConditionRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXAccOpenApplyDownloadRequest)
        {
            // ○管理者・FX口座開設申込ダウンロードリクエストの場合
            // this.getダウンロードファ@イル()メソッドをコールする。
            WEB3AdminFXAccOpenApplyDownloadResponse l_response =
                this.getDownloadFile((WEB3AdminFXAccOpenApplyDownloadRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminFXAccOpenApplyListRequest)
        {
            // ○管理者・FX口座開設申込一覧リクエストの場合 
            // this.get一覧画面()メソッドをコールする。 
            WEB3AdminFXAccOpenApplyListResponse l_Response = 
                this.getListScreen((WEB3AdminFXAccOpenApplyListRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXAccOpenStatusUpdInputRequest)
        {
            // ○管理者・FX口座開設ステータス更新入力リクエストの場合 
            // this.getステータス更新入力画面()メソッドをコールする。
            WEB3AdminFXAccOpenStatusUpdInputResponse l_Response = 
                this.getStatusUpdInput((WEB3AdminFXAccOpenStatusUpdInputRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXAccOpenStatusUpdConfirmRequest)
        {
            // ○管理者・FX口座開設ステータス更新確認リクエストの場合 
            // this.validateステータス更新()メソッドをコールする。
            WEB3AdminFXAccOpenStatusUpdConfirmResponse l_Response = 
                this.validateStatusUpd((WEB3AdminFXAccOpenStatusUpdConfirmRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }        
        else if (l_request instanceof WEB3AdminFXAccOpenStatusUpdCompleteRequest)
        {
            // ○管理者・FX口座開設ステータス更新完了リクエストの場合 
            // this.submitステータス更新()メソッドをコールする。
            WEB3AdminFXAccOpenStatusUpdCompleteResponse l_Response = 
                this.submitStatusUpd((WEB3AdminFXAccOpenStatusUpdCompleteRequest) l_request);
                
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
     * (管理者FX口座開設管理)口座開設申込検索条件 <BR>
     * 入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座開設管理サービス)get条件入力画面」参照
     * @@param l_request - 管理者・FX口座開設申込一覧条件入力リクエストオブジェクト
     * @@return WEB3AdminFXAccOpenApplyListConditionResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD556E0079
     */
    protected WEB3AdminFXAccOpenApplyListConditionResponse getCondInputScreen(
        WEB3AdminFXAccOpenApplyListConditionRequest l_request) throws WEB3BaseException
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
        
        // 1 ) リクエストデータの整合性をチェックする。
        l_request.validate();
        
        // 2 ) ログイン情報から管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) 管理者権限のチェックを行う。 
        // [引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.為替保証金管理(口座管理・口座開設管理) 
        // is更新：　@false(更新なし) 
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
                false
                );
        
        // 4 ) 部店権限のチェックを行う。 
        // [引数] 
        // 部店コード：　@リクエストデータ.部店コード一覧
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        // 5 ) レスポンスデータを生成する。
        WEB3AdminFXAccOpenApplyListConditionResponse l_response = 
            (WEB3AdminFXAccOpenApplyListConditionResponse) l_request.createResponse();
        
        // 6 ) プロパティセット
        // 現在の時刻を取得
        Timestamp l_datSystemDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        // 現在時刻の前営業日を取得
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(l_datSystemDate);
        Timestamp l_datLastBizDate = l_gentradeBizDate.roll(-1);
        // 現在時刻の前日を取得
        Date l_datBeforeDay = WEB3DateUtility.addDay(l_datSystemDate, -1L);
        
        // レスポンスデータ.申込日（自）= 現在時刻の前営業日
        l_response.applyDateFrom = 
            WEB3DateUtility.formatDate(l_datLastBizDate, "yyyyMMdd");
        // レスポンスデータ.申込日（至）= 現在時刻の前日
        l_response.applyDateTo = 
            WEB3DateUtility.formatDate(l_datBeforeDay, "yyyyMMdd");
        
        // 7 ) レスポンスデータを返す。
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get一覧画面) <BR>
     * (管理者FX口座開設管理)口座開設申込検索結果一覧画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座開設管理サービス)get一覧画面」参照 <BR>
     * @@param l_request - 管理者・FX口座開設申込一覧リクエストオブジェクト
     * @@return WEB3AdminFXAccOpenApplyListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD556E0098
     */
    protected WEB3AdminFXAccOpenApplyListResponse getListScreen(
        WEB3AdminFXAccOpenApplyListRequest l_request) throws WEB3BaseException
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
        
        // 1 ) リクエストデータの整合性をチェックする。
        l_request.validate();
        
        // 2 ) ログイン情報から管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) 管理者権限のチェックを行う。 
        // [引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.為替保証金管理(口座管理・口座開設管理) 
        // is更新：　@false(更新なし) 
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
                false
                );
        
        // 4 ) 部店権限のチェックを行う。 
        // [引数] 
        // 部店コード：　@リクエストデータ.部店コード
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        // 5 ) 証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //create取得条件文字列(String, String[], String, String, String, String, String)
        // [引数] 
        // 証券会社コード：　@get証券会社コード()の戻り値 
        // 部店コード：　@リクエストデータ.部店コード 
        // ステータス区分：　@リクエストデータ.ステータス区分 
        // 申込日（自）：　@リクエストデータ.申込日（自） 
        // 申込日（至）：　@リクエストデータ.申込日（至）
        // 約諾書区分：　@リクエストデータ.約諾書区分
        // FXシステムコード：　@リクエストデータ.FXシステムコード
        String l_strWhere = 
            this.createQueryConditionList(
                l_strInstitutionCode,
                l_request.branchCodeList,
                l_request.statusDiv,
                l_request.applyHourFrom,
                l_request.applyHourTo,
                l_request.agreementDiv,
                l_request.fxSystemCode);
        
        //create取得条件データコンテナ(String, String[], String, String, String, String, String)
        // [引数] 
        // 証券会社コード：　@get証券会社コード()の戻り値 
        // 部店コード：　@リクエストデータ.部店コード 
        // ステータス区分：　@リクエストデータ.ステータス区分 
        // 申込日（自）：　@リクエストデータ.申込日（自） 
        // 申込日（至）：　@リクエストデータ.申込日（至）
        // 約諾書区分：　@リクエストデータ.約諾書区分
        // FXシステムコード：　@リクエストデータ.FXシステムコード
        String[] l_arrVars = 
            this.createQueryDataContainer(
                l_strInstitutionCode,
                l_request.branchCodeList,
                l_request.statusDiv,
                l_request.applyHourFrom,
                l_request.applyHourTo,
                l_request.agreementDiv,
                l_request.fxSystemCode);
        
        // 8 ) ソート条件を作成する
        String l_strSort = this.createSortCond();
        
        // 9 ) GFT口座開設状況の一覧を取得する。 
        // [引数] 
        // 検索条件文字列：　@create検索条件文字列()の戻り値 
        // 検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値 
        // ソート条件：　@createソート条件()の戻り値
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class);  
        GftAccountOpenStatusParams[] l_statusParamses = 
            l_controlService.getGFTAccountOpenStatuses(
                l_strWhere,
                l_arrVars,
                l_strSort
                );
        
        // 10 ) 指定されたMRF口座開設区分に該当する 
        // 口座開設状況の一覧を取得する。 
        // [引数] 
        // 口座開設状況一覧：　@getGFT口座開設状況()の戻り値 
        // MRF口座開設区分：　@
        //                 [リクエストデータ.MRF口座開設区分 == "2：未開設"の場合]
        //                      "0：DEFAULT(口座なし)"をセット。
        //                 [上記以外]  リクエストデータ.MRF口座開設区分をセット。
        String l_strMrfAccountStatusDiv = l_request.mrfAccountStatusDiv;
        if(WEB3AioFxAccountOpenDivDef.NOT_OPEN.equals(l_strMrfAccountStatusDiv))
        {
            l_strMrfAccountStatusDiv = WEB3AccountOpenDef.NOT_OPEN;
        }
        GftAccountOpenStatusParams[] l_arrOpenStatusParams = 
            this.getObjGFTAccOpenStatusList(
                l_statusParamses,
                l_strMrfAccountStatusDiv
                );        

        // 11 ) FX口座開設申込明細を格納するArrayListを生成する。
        List l_lisAccOpenApplyUnit = new Vector();
        
        // 12 ) get対象GFT口座開設状況一覧()の戻り値のうち、
        // 表示対象行(fromIndex～toIndex)の間、Loop処理を実施する
        // [表示対象行（fromIndex，toIndex）の計算]
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_arrOpenStatusParams,
                l_intPageIndex,
                l_intPageSize);
        
        // 指定したレコードを取得(基本設計と異なり、「ページをめくる処理クラス」を利用したので)
        GftAccountOpenStatusParams[] l_selectedAccpenStatusParams = 
            (GftAccountOpenStatusParams[])l_pageIndexInfo.getArrayReturned(GftAccountOpenStatusParams.class); 
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        //拡張アカウントマネージャ取得する    
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        for(int i = 0; i < l_selectedAccpenStatusParams.length; i++)
        {
            GftAccountOpenStatusParams l_params = 
                l_selectedAccpenStatusParams[i];
            // 12 - 1 ) FX口座情報の一覧を作成する。 
            // [引数] 
            // GFT口座開設状況：　@GFT口座開設状況Params
            WEB3FXAccInformationUnit[] l_arrFXAccInformationUnits = 
                this.createFXAccInfoList(l_params);
            
            // 12 - 2 ) 質問回答Paramsの一覧を取得する。 
            //[引数] 
            //＜リクエストデータ.FXシステムコードがnull以外の場合＞ 
            // 証券会社コード：　@GFT口座開設状況Params.証券会社コード 
            // 部店コード：　@GFT口座開設状況Params.部店コード 
            // 質問区分 :  リクエストデータ.FXシステムコード
            // 識別コード：　@GFT口座開設状況Params.識別コード
            //
            //＜リクエストデータ.FXシステムコードがnullの場合＞
            // 証券会社コード：　@GFT口座開設状況Params.証券会社コード 
            // 部店コード：　@GFT口座開設状況Params.部店コード 
            // 質問区分 :  0001 (為替保証金)
            // 識別コード：　@GFT口座開設状況Params.識別コード
            
            String l_strQuestionDiv = l_request.fxSystemCode;
            
            if(l_request.fxSystemCode == null){
            	
            	l_strQuestionDiv = WEB3QuestionDivDef.FX;
            	
            }
            
            QuestionAnswerParams[] l_arrQuestionAnswers = 
                l_controlService.getQuestionAnswers(
                    l_params.getInstitutionCode(),
                    l_params.getBranchCode(),
                    l_strQuestionDiv,
                    l_params.getOrderRequestNumber()
                    );
            
            // 12 - 3 ) FX取引同意質問情報の一覧を作成する。 
            // [引数] 
            // 質問回答一覧：　@get質問回答()の戻り値
            WEB3FXTradeAgreementUnit[] l_arrTradeAgreementUnits = 
                this.createFXTradeAgreeQuestionInfoList(l_arrQuestionAnswers);
            
			String l_strOperationDiv;
            String l_mainAccountName;
            boolean l_blnIsMRFBalanceHolding = true;
            boolean l_mrfAccountFlag = true;   
            boolean l_mainAccountFlag = true;                          
            try
            {
				// 12 - 4 ) 顧客オブジェクトを取得する。 
				// [引数] 
				// 証券会社コード：　@GFT口座開設状況Params.証券会社コード 
				// 部店コード：　@GFT口座開設状況Params.部店コード 
				// 口座コード：　@GFT口座開設状況Params.顧客コード 
				WEB3GentradeMainAccount l_mainAccount = 
					l_accountManager.getMainAccount(
						l_params.getInstitutionCode(),
						l_params.getBranchCode(),
						l_params.getAccountCode()
						);
						
				// 12 - 5 ) 顧客がMRF残高を保有しているかどうか判別する。 
				// [引数] 
				// 顧客：　@get顧客()の戻り値		
				l_blnIsMRFBalanceHolding = this.isMRFBalanceHolding(l_mainAccount);
				
				// 12 - 6 ) 処理区分を取得する。 
				// [引数] 
				// GFT口座開設状況Params：　@GFT口座開設状況Params
				l_strOperationDiv = this.getOperationDiv(l_params);
				
				//get顧客()の戻り値.顧客名
				l_mainAccountName = l_mainAccount.getDisplayAccountName();
				
				//get顧客()の戻り値.MRF口座開設区分 == "開設済"の場合、trueをセット。
				//以外、falseをセット。
				MainAccountRow l_mainAccountRow = 
					(MainAccountRow) l_mainAccount.getDataSourceObject();
				if(WEB3AccountOpenDef.OPEN.equals(
					l_mainAccountRow.getMrfAccOpenDiv()))
				{
					l_mrfAccountFlag = true;
				}
				else
				{
					l_mrfAccountFlag = false;
				}																				 					
            }
            catch(WEB3BaseException l_ex)
            {
				l_blnIsMRFBalanceHolding = false;
				l_strOperationDiv = WEB3AioAccOpenRemarkDef.ACCEPT_RESULT_CODE_90000009;
				l_mainAccountName = null;
				l_mrfAccountFlag = false;
				l_mainAccountFlag = false;  	           	         	
            }
                                  
            // 12 - 7 ) FX口座開設申込明細インスタンスを生成する。
            WEB3FXAccOpenApplyUnit l_accOpenApplyUnit = 
                new WEB3FXAccOpenApplyUnit();
            
            // 12 - 8 ) FX口座開設申込明細に以下のプロパティをセットする。
            //
            //選択可能フラグ	＝　@（（GFT口座開設状況Params.口座開設状況区分 == "口座開設中" 
            //        　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@または、GFT口座開設状況Params.口座開設状況区分 == ”ダウンロード済”）
            //                                 　@　@　@　@       かつ、GFT口座開設状況Params.送受信区分 == "送信済"）
            //    　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@または、GFT口座開設状況Params.口座開設状況区分 == "口座開設エラー" の場合、trueをセット。
            // 		　@　@　@以外、falseをセット。
            
            if(l_mainAccountFlag)
            {
				if(((WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(l_params.getAccountOpenStatusDiv())
                    || WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE.equals(l_params.getAccountOpenStatusDiv()))
					&& WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_params.getSendRcvDiv()))
					|| WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(l_params.getAccountOpenStatusDiv()))
				{
					l_accOpenApplyUnit.selectableFlag = true;
				}
				else
				{
					l_accOpenApplyUnit.selectableFlag = false;
				}
            }
            else
            {
				l_accOpenApplyUnit.selectableFlag = false;
            }

            
            // 識別コード		＝　@GFT口座開設状況Params.識別コード
            l_accOpenApplyUnit.requestNumber = l_params.getOrderRequestNumber();
            
            // 部店コード		＝　@GFT口座開設状況Params.部店コード
            l_accOpenApplyUnit.branchCode = l_params.getBranchCode();
            
            // 顧客コード ＝ GFT口座開設状況Params.顧客コード
            String l_strAccountCodeToSet = l_params.getAccountCode();
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
            {
                l_strAccountCodeToSet = l_params.getAccountCode().substring(0, 6);
            }
            l_accOpenApplyUnit.accountCode = l_strAccountCodeToSet;
            
            // 顧客名		＝　@get顧客()の戻り値.顧客名
            // get顧客()にて例外が発生した場合は、「NULL」をセット
			l_accOpenApplyUnit.accountName = l_mainAccountName;
			            	
            // 申込日時 ＝ GFT口座開設状況Params.作成日付
            l_accOpenApplyUnit.applyTime = l_params.getCreatedTimestamp();
            
            // ステータス区分	＝　@GFT口座開設状況Params.口座開設状況区分
            l_accOpenApplyUnit.statusDiv = l_params.getAccountOpenStatusDiv();
            
            // 送受信区分	＝　@GFT口座開設状況Params.送受信区分
            l_accOpenApplyUnit.sendRcvDiv = l_params.getSendRcvDiv();
            
            // (FX)名前(姓)	＝　@GFT口座開設状況Params.名前(姓)
            l_accOpenApplyUnit.fxLastName = l_params.getLastName();
            
            // (FX)名前(名)	＝　@GFT口座開設状況Params.名前(名)
            l_accOpenApplyUnit.fxFirstName = l_params.getFirstName();
            
            // (FX)ログインID	＝　@GFT口座開設状況Params.GFTログインID
            l_accOpenApplyUnit.fxLoginId = l_params.getLoginId();
            
            // (FX)メールアドレス	＝　@GFT口座開設状況Params.GFTメールアドレス
            l_accOpenApplyUnit.fxMailAddress = l_params.getMailAddress();
            
            // 約諾書区分    ＝　@GFT口座開設状況Params.約諾書区分
            l_accOpenApplyUnit.agreementDiv = l_params.getAgreementDiv();
            
            // FX口座情報一覧	＝　@createFX口座情報一覧()の戻り値
            l_accOpenApplyUnit.fxAccInformationList = l_arrFXAccInformationUnits;
            
            // 備考		＝　@get処理区分()の戻り値
            // get顧客()にて例外が発生した場合は、「90000009(口座抹消)」をセット
            l_accOpenApplyUnit.fxRemark = l_strOperationDiv;
            
			// MRF残高フラグ	＝　@isMRF残高保有()の戻り値
			//get顧客()にて例外が発生した場合は、「false」をセット
			l_accOpenApplyUnit.mrfBalanceFlag = l_blnIsMRFBalanceHolding;
				
			// MRF口座フラグ	＝　@get顧客()の戻り値.MRF口座開設区分 == "開設済"の場合、trueをセット。
			// 以外、falseをセット。
			//get顧客()にて例外が発生した場合は、「false」をセット
			l_accOpenApplyUnit.mrfAccountFlag = l_mrfAccountFlag;
            
            // FX取引同意質問情報一覧　@＝　@createFX取引同意質問情報一覧()の戻り値
            l_accOpenApplyUnit.fxTradeAgreementList = l_arrTradeAgreementUnits;

            //FXシステムコード = GFT口座開設状況Params.FXシステムコード
            l_accOpenApplyUnit.fxSystemCode = l_params.getFxSystemCode();

            // 12 - 9 ) ArrayListにFX口座開設申込明細を追加する。 
            // [引数] 
            // arg0：　@createFX口座開設申込明細()の戻り値
            l_lisAccOpenApplyUnit.add(l_accOpenApplyUnit);
        }
        
        // 13 ) FX口座開設申込明細の配列を生成する。
        WEB3FXAccOpenApplyUnit[] l_arrAccOpenApplyUnits = 
            new WEB3FXAccOpenApplyUnit[l_lisAccOpenApplyUnit.size()];
        l_lisAccOpenApplyUnit.toArray(l_arrAccOpenApplyUnits);
        
        // 14 ) レスポンスデータを生成する。
        WEB3AdminFXAccOpenApplyListResponse l_response = 
            (WEB3AdminFXAccOpenApplyListResponse) l_request.createResponse();
        
        // 15 ) レスポンスデータにプロパティをセットする。      
        // 部店コード   ＝　@リクエストデータ.部店コードの要素数 == 1 の場合、その要素をセット
        //   　@　@　@リクエストデータ.部店コードの要素数 > 1 の場合、nullをセット
        if(l_request.branchCodeList.length == 1)
        {
            l_response.branchCode = l_request.branchCodeList[0];
        }
        else
        {
            l_response.branchCode = null;
        }
        
        // ステータス区分  ＝　@リクエストデータ.ステータス区分
        l_response.statusDiv = l_request.statusDiv;
        
        // MRF口座状況区分  ＝　@リクエストデータ.MRF口座状況区分
        l_response.mrfAccountStatusDiv = l_request.mrfAccountStatusDiv;
        
        // 申込日（自）  ＝　@リクエストデータ.申込日（自）
        l_response.applyHourFrom = l_request.applyHourFrom;
        
        // 申込日（至）  ＝　@リクエストデータ.申込日（至）
        l_response.applyHourTo = l_request.applyHourTo;
        
        // 約諾書区分        ＝　@リクエストデータ.約諾書区分
        l_response.agreementDiv = l_request.agreementDiv;
        
        // FX口座開設申込明細一覧 ＝　@toArray()の戻り値
        l_response.fxAccOpenApplyList = l_arrAccOpenApplyUnits;
        
        // 総ページ数  ＝　@総レコード数 / リクエストデータ.ページ内表示行数
        //   　@　@　@※計算結果は小数点以下一位を切り上げた整数値とする。
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        
        // 総レコード数  ＝　@get対象GFT口座開設状況一覧()の戻り値.length
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        
        // 表示ページ番号  ＝　@toIndex /　@リクエストデータ.ページ内表示行数
        //   　@　@　@※計算結果は小数点以下一位を切り上げた整数値とする。
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getステータス更新入力画面) <BR>
     * (管理者FX口座開設管理)口座開設ステータス <BR>
     * 更新入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座開設管理サービス)getステータス更新入力画面」参照 <BR>
     * ======================================================== <BR>
     * シーケンス図(「為替保証金サービスモデル（管理者）/ 管理者・FX口座開設管理」) <BR>
     * （getステータス更新入力画面）getStatusUpdInput <BR>
     * : 1.6 getGFT口座開設状況(String, String, String)戻り値がnull<BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== <BR>
     * @@param l_request - 管理者・FX口座開設ステータス更新入力リクエストオブジェクト
     * @@return WEB3AdminFXAccOpenStatusUpdInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD554801A2
     */
    protected WEB3AdminFXAccOpenStatusUpdInputResponse getStatusUpdInput(
        WEB3AdminFXAccOpenStatusUpdInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStatusUpdInput()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1 ) リクエストデータの整合性をチェックする。
        l_request.validate();
        
        // 2 ) ログイン情報から管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) 管理者権限のチェックを行う。 
        // [引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.為替保証金管理(口座管理・口座開設管理) 
        // is更新：　@true(更新あり) 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
            true
            );
        
        // 4 ) 部店権限のチェックを行う。 
        // [引数] 
        // 部店コード：　@リクエストデータ.FX検索条件.部店コード
        l_admin.validateBranchPermission(
            l_request.fxSearchConditionUnit.branchCode);
        
        // 5 ) 証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // 6 ) GFT口座開設状況を取得する。 
        // [引数] 
        // 証券会社コード：　@get証券会社コード()の戻り値 
        // 部店コード：　@リクエストデータ.FX検索条件.部店コード 
        // 識別コード：　@リクエストデータ.FX検索条件.識別コード
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class);  
        GftAccountOpenStatusParams l_accOpenStatusParams = 
            l_controlService.getGFTAccountOpenStatus(
                l_strInstitutionCode,
                l_request.fxSearchConditionUnit.branchCode,
                l_request.fxSearchConditionUnit.requestNumber
                );
        
        if( l_accOpenStatusParams == null)
        {
            log.debug("GFT口座開設状況取得エラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT口座開設状況取得エラー");
        }
        
        // 7 ) 口座開設中チェック
        // getGFT口座開設状況()の戻り値.口座開設状況区分 != "口座開設中"の場合、
        // 且つgetGFT口座開設状況()の戻り値.口座開設状況区分 != "ダウンロード済"の場合、
        // 且つgetGFT口座開設状況()の戻り値.口座開設状況区分 != "口座開設エラー"の場合、例外をスローする。
        if(!WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
            l_accOpenStatusParams.getAccountOpenStatusDiv())
            && !WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE.equals(l_accOpenStatusParams.getAccountOpenStatusDiv())
            && !WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(l_accOpenStatusParams.getAccountOpenStatusDiv()))
        {
            log.debug("口座開設中チェックエラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01807,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設中チェックエラー");
        }
        
        // 8 ) FX口座情報の一覧を作成する。 
        // [引数] 
        // GFT口座開設状況：　@getGFT口座開設状況()の戻り値
        WEB3FXAccInformationUnit[] l_arrAccInformationUnits = 
            this.createFXAccInfoList(l_accOpenStatusParams);
        
        // 9 ) レスポンスデータを生成する。
        WEB3AdminFXAccOpenStatusUpdInputResponse l_response = 
            (WEB3AdminFXAccOpenStatusUpdInputResponse) l_request.createResponse();
        
        // 10 ) レスポンスデータにプロパティをセットする。
        
        // 部店コード   ＝　@getGFT口座開設状況()の戻り値.部店コード
        l_response.branchCode = l_accOpenStatusParams.getBranchCode();
        
        // 顧客コード ＝ getGFT口座開設状況()の戻り値.顧客コード
        String l_strAccountCodeToSet = l_accOpenStatusParams.getAccountCode();
        if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
        {
            l_strAccountCodeToSet = l_accOpenStatusParams.getAccountCode().substring(0, 6);
        }
        l_response.accountCode = l_strAccountCodeToSet;
        
        // (FX)ログインID  ＝　@getGFT口座開設状況()の戻り値.GFTログインID
        l_response.fxLoginId = l_accOpenStatusParams.getLoginId();
        
        // (FX)名前(姓)  ＝　@getGFT口座開設状況()の戻り値.名前(姓)
        l_response.fxLastName = l_accOpenStatusParams.getLastName();
        
        // (FX)名前(名)  ＝　@getGFT口座開設状況()の戻り値.名前(名)
        l_response.fxFirstName = l_accOpenStatusParams.getFirstName();
        
        // (FX)メールアドレス  ＝　@getGFT口座開設状況()の戻り値.GFTメールアドレス
        l_response.fxMailAddress = l_accOpenStatusParams.getMailAddress();
        
        // FX口座情報一覧  ＝　@createFX口座情報一覧()の戻り値
        l_response.fxAccInformationList = l_arrAccInformationUnits;
            
        // ステータス区分  ＝　@getGFT口座開設状況()の戻り値.口座開設状況区分
        l_response.statusDiv = l_accOpenStatusParams.getAccountOpenStatusDiv();
        
        // 約諾書区分　@       ＝　@getGFT口座開設状況()の戻り値.約諾書区分
        l_response.agreementDiv = l_accOpenStatusParams.agreement_div;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validateステータス更新) <BR>
     * (管理者FX口座開設管理)口座開設ステータス更新確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座開設管理サービス)validateステータス更新」参照 <BR>
     * ======================================================== <BR>
     * シーケンス図(「為替保証金サービスモデル（管理者）/ 管理者・FX口座開設管理」) <BR>
     * validateステータス更新 <BR>
     * : 1.6 getGFT口座開設状況(String, String, String)戻り値がnull<BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - 管理者・FX口座開設ステータス更新確認リクエストオブジェクト
     * @@return WEB3AdminFXAccOpenStatusUpdConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD554801C1
     */
    protected WEB3AdminFXAccOpenStatusUpdConfirmResponse validateStatusUpd(
        WEB3AdminFXAccOpenStatusUpdConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateStatusUpd()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1 ) リクエストデータの整合性をチェックする。
        l_request.validate();
        
        // 2 ) ログイン情報から管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) 管理者権限のチェックを行う。 
        // [引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.為替保証金管理(口座管理・口座開設管理) 
        // is更新：　@true(更新あり) 
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
                true
                );
        
        // 4 ) 部店権限のチェックを行う。 
        // [引数] 
        // 部店コード：　@リクエストデータ.FX検索条件.部店コード
        l_admin.validateBranchPermission(
            l_request.fxSearchConditionUnit.branchCode);
        
        // 5 ) 証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // 6 ) GFT口座開設状況を取得する。 
        // [引数] 
        // 証券会社コード：　@get証券会社コード()の戻り値 
        // 部店コード：　@リクエストデータ.FX検索条件.部店コード 
        // 識別コード：　@リクエストデータ.FX検索条件.識別コード
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class);  
        GftAccountOpenStatusParams l_accOpenStatusParams = 
            l_controlService.getGFTAccountOpenStatus(
                l_strInstitutionCode,
                l_request.fxSearchConditionUnit.branchCode,
                l_request.fxSearchConditionUnit.requestNumber
                );
        
        if( l_accOpenStatusParams == null)
        {
            log.debug("GFT口座開設状況取得エラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT口座開設状況取得エラー");
        }
        
        // 7 ) ステータスの変更が可能かどうかチェックする。
        this.validateStatusChangePossible(
            l_accOpenStatusParams.getAccountOpenStatusDiv(),
            l_request.updatedStatusDiv,
            l_accOpenStatusParams.getAgreementDiv(),            
            l_request.updatedAgreementDiv);

        // 8 ) レスポンスデータを生成する。
        WEB3AdminFXAccOpenStatusUpdConfirmResponse l_response = 
            (WEB3AdminFXAccOpenStatusUpdConfirmResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitステータス更新) <BR>
     * (管理者FX口座開設管理)口座開設ステータス更新完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者FX口座開設管理サービス)submitステータス更新」参照 <BR>
     * ======================================================== <BR>
     * シーケンス図(「為替保証金サービスモデル（管理者）/ 管理者・FX口座開設管理」) <BR>
     * （submitステータス更新）submitStatusUpd <BR>
     * : 1.8 getGFT口座開設状況(String, String, String)戻り値がnull<BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== <BR>
     * @@param l_request - 管理者・FX口座開設ステータス更新完了リクエストオブジェクト
     * @@return WEB3AdminFXAccOpenStatusUpdCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD554801E0
     */
    protected WEB3AdminFXAccOpenStatusUpdCompleteResponse submitStatusUpd(
        WEB3AdminFXAccOpenStatusUpdCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitStatusUpd()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1 ) リクエストデータの整合性をチェックする。
        l_request.validate();
        
        // 2 ) ログイン情報から管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) 管理者権限のチェックを行う。 
        // [引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.為替保証金管理(口座管理・口座開設管理) 
        // is更新：　@true(更新あり) 
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
                true
                );
        
        // 4 ) 部店権限のチェックを行う。 
        // [引数] 
        // 部店コード：　@リクエストデータ.FX検索条件.部店コード
        l_admin.validateBranchPermission(
            l_request.fxSearchConditionUnit.branchCode);
        
        // 5 ) 暗証番号のチェックを行う。 
        // [引数] 
        // パスワード：　@リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);

        // 6 ) 管理者コードを取得する。
        String l_strAdminCode =  l_admin.getAdministratorCode();
        
        // 7 ) 証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // 8 ) GFT口座開設状況を取得する。 
        // [引数] 
        // 証券会社コード：　@get証券会社コード()の戻り値 
        // 部店コード：　@リクエストデータ.FX検索条件.部店コード 
        // 識別コード：　@リクエストデータ.FX検索条件.識別コード
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class);  
        GftAccountOpenStatusParams l_accOpenStatusParams = 
            l_controlService.getGFTAccountOpenStatus(
                l_strInstitutionCode,
                l_request.fxSearchConditionUnit.branchCode,
                l_request.fxSearchConditionUnit.requestNumber
                );
        
        if( l_accOpenStatusParams == null)
        {
            log.debug("GFT口座開設状況取得エラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT口座開設状況取得エラー");
        }

        //get同時開設FXシステムコード
        //証券会社コード：　@get証券会社コード()の戻り値
        String l_strSameTimeFxSystemCode =
            l_controlService.getSameTimeFxSystemCode(l_strInstitutionCode);

        //get会社別FXシステム条件
        //証券会社コード：　@get証券会社コード()の戻り値
        //部店コード：　@リクエストデータ.FX検索条件.部店コード
        //FXシステムコード：　@getGFT口座開設状況()の戻り値.FXシステムコード
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = l_controlService.getCompFxCondition(
                l_strInstitutionCode,
                l_request.fxSearchConditionUnit.branchCode,
                l_accOpenStatusParams.getFxSystemCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 9 ) ステータスの変更が可能かどうかチェックする。
        this.validateStatusChangePossible(
            l_accOpenStatusParams.getAccountOpenStatusDiv(),
            l_request.updatedStatusDiv,
            l_accOpenStatusParams.getAgreementDiv(),            
            l_request.updatedAgreementDiv);
        
        // 10 ) リクエストデータ.更新後ステータス区分 == "口座開設完了"の場合
        if(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(
            l_request.updatedStatusDiv))
        { 
            // 10 - 1 ) FX顧客テーブルにデータを登録する。 
            // [引数] 
            // GFT口座開設状況Params：　@getGFT口座開設状況Params()の戻り値 
            // 更新者コード：　@get管理者コード()の戻り値
            l_controlService.insertFXAccount(
                l_accOpenStatusParams,
                l_strAdminCode
                );

            //get同時開設FXシステムコード()の戻り値がnullでない場合
            if (l_strSameTimeFxSystemCode != null)
            {
                //insert同時口座開設
                //GFT口座開設状況Params：　@getGFT口座開設状況Params()の戻り値
                //同時口座開設FXシステムコード：　@get同時口座開設FXシステムコード()の戻り値
                // 更新者コード：　@get管理者コード()の戻り値
                l_controlService.insertSimultaneousAccountOpen(
                    l_accOpenStatusParams,
                    l_strSameTimeFxSystemCode,
                    l_strAdminCode);
            }
            // 10 - 2 ) リクエストデータ.FX口座情報一覧の要素数分Loop処理
            for(int i = 0; i < l_request.fxAccInformationList.length; i++)
            {
                WEB3FXAccInformationUnit l_accInformationUnit = 
                    l_request.fxAccInformationList[i];
                
                // 10 - 2 - 1 ) FX口座番号テーブルにデータを登録する。 
                // [引数] 
                // GFT口座開設状況Params：　@getGFT口座開設状況Params()の戻り値 
                // FX口座情報：　@処理対象のFX口座情報 
                // 更新者コード：　@get管理者コード()の戻り値
                //同時開設FXシステムコード：　@get同時開設FXシステムコード()の戻り値
                l_controlService.insertFXAccountCode(
                    l_accOpenStatusParams,
                    l_accInformationUnit,
                    l_strAdminCode,
                    l_strSameTimeFxSystemCode);
            }
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
            //拡張アカウントマネージャ取得する    
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            // 10 - 3 ) 顧客オブジェクトを取得する。 
            // [引数] 
            // 証券会社コード：　@getGFT口座開設状況()の戻り値.証券会社コード 
            // 部店コード：　@getGFT口座開設状況()の戻り値.部店コード 
            // 口座コード：　@getGFT口座開設状況()の戻り値.顧客コード
            WEB3GentradeMainAccount l_mainAccount = 
                l_accountManager.getMainAccount(
                    l_accOpenStatusParams.getInstitutionCode(),
                    l_accOpenStatusParams.getBranchCode(),
                    l_accOpenStatusParams.getAccountCode()
                    );

            //口座開設区分：
            //１) get会社別FXシステム条件（）の戻り値.口座区分リアル更新＝"０：リアル更新しない"の場合
            //"0:未開設"
            //２) get会社別FXシステム条件（）の戻り値.口座区分リアル更新＝"１：リアル更新"の場合
            //"1:開設済"
            WEB3GentradeAccOpenDiv l_genAccOpenDiv = new WEB3GentradeAccOpenDiv();
            long l_lngAccountId = l_mainAccount.getAccountId();
            String l_strAccOpenDiv = "";
            if (WEB3AccOpenRealUpdateDef.REAL_UPDATE.equals(
                l_compFxConditionParams.getAccOpenRealUpdate()))
            {
                l_strAccOpenDiv = WEB3AccountOpenDef.OPEN;
            }
            else if (WEB3AccOpenRealUpdateDef.NOT_REAL_UPDATE.equals(
                l_compFxConditionParams.getAccOpenRealUpdate()))
            {
                l_strAccOpenDiv = WEB3AccountOpenDef.NOT_OPEN;
            }

            //get会社別FXシステム条件（）の戻り値.口座種別＝"01：(FX)"の場合
            if (WEB3AccTypeDef.FX.equals(l_compFxConditionParams.getAccType()))
            {
                //updateFX口座開設区分(証券会社コード : String, 部店コード : String,
                // 顧客コード : String, 更新後FX口座開設区分 : String, 更新者コード : String)
                //[引数]
                // 証券会社コード：　@getGFT口座開設状況()の戻り値.証券会社コード
                // 部店コード：　@getGFT口座開設状況()の戻り値.部店コード
                // 顧客コード：　@getGFT口座開設状況()の戻り値.顧客コード
                // 更新後FX口座開設区分：　@（以下のとおり） 
                // 　@１) get会社別FXシステム条件（）の戻り値.口座区分リアル更新＝"０：リアル更新しない"の場合
                //　@　@　@"0:未開設"
                // 　@２) get会社別FXシステム条件（）の戻り値.口座区分リアル更新＝"１：リアル更新"の場合
                //　@　@　@"1:開設済"
                // 更新者コード：　@get管理者コード()の戻り値
                l_controlService.updateFXAccountOpenDiv(
                    l_accOpenStatusParams.getInstitutionCode(),
                    l_accOpenStatusParams.getBranchCode(),
                    l_accOpenStatusParams.getAccountCode(),
                    l_strAccOpenDiv,
                    l_strAdminCode);
            }

            //insert口座開設区分(口座ID : long, 口座種別 : String,
            //  口座開設区分 : String, 更新者コード : String)
            //口座開設区分テーブル(acc_open_div)に行のinsertを行う
            // [引数]
            // 口座ID：顧客マスタテーブルより、引数の証券会社コード、部店コード、
            //         口座コードに該当する口座IDを取得し返却する。
            // 口座種別：get会社別FXシステム条件（）の戻り値.口座種別
            // 口座開設区分：（以下のとおり）
            // １) get会社別FXシステム条件（）の戻り値.口座区分リアル更新＝"０：リアル更新しない"の場合
            //　@ 　@"0:未開設"
            // ２) get会社別FXシステム条件（）の戻り値.口座区分リアル更新＝"１：リアル更新"の場合
            //　@　@　@"1:開設済"
            //更新者コード：get管理者コード()の戻り値
            l_genAccOpenDiv.insertAccOpenDiv(
                l_lngAccountId,
                l_compFxConditionParams.getAccType(),
                l_strAccOpenDiv,
                l_strAdminCode);

            //get同時開設FXシステムコード(証券会社コード : String)の戻り値がnullでない場合
            if (l_strSameTimeFxSystemCode != null)
            {
                //insert口座開設区分(口座ID : long, 口座種別 : String,
                //  口座開設区分 : String, 更新者コード : String)
                //口座開設区分テーブル(acc_open_div)に行のinsertを行う
                //[引数]
                // 口座ID：顧客マスタテーブルより、引数の証券会社コード、部店コード、
                //        口座コードに該当する口座IDを取得し返却する。
                // 口座種別："02：CFD"
                // 口座開設区分：（以下のとおり）
                // １) get会社別FXシステム条件（）の戻り値.口座区分リアル更新＝"０：リアル更新しない"の場合
                //　@ "0:未開設"
                // ２) get会社別FXシステム条件（）の戻り値.口座区分リアル更新＝"１：リアル更新"の場合
                //　@ "1:開設済"
                //更新者コード：get管理者コード()の戻り値
                l_genAccOpenDiv.insertAccOpenDiv(
                    l_lngAccountId,
                    WEB3AccTypeDef.CFD,
                    l_strAccOpenDiv,
                    l_strAdminCode);
            }
        }
        // 11 ) GFT口座開設状況テーブルを更新する。 
        // [引数] 
        // GFT口座開設状況Params：　@getGFT口座開設状況Params()の戻り値 
        // 更新後ステータス区分：　@リクエストデータ.更新後ステータス区分 
        // 更新後FX口座情報一覧：　@リクエストデータ.FX口座情報一覧 
        // 更新者コード：　@get管理者コード()の戻り値
        //更新後約諾書区分：　@リクエストデータ.更新後約諾書区分
        l_controlService.updateGFTAccountOpenStatus(
            l_accOpenStatusParams,
            l_request.updatedStatusDiv,
            l_request.fxAccInformationList,
            l_strAdminCode,
            l_request.updatedAgreementDiv);
        
        // 12 ) レスポンスデータを生成する。
        WEB3AdminFXAccOpenStatusUpdCompleteResponse l_response = 
            (WEB3AdminFXAccOpenStatusUpdCompleteResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列) <BR>
     * 検索条件文字列を作成する。 <BR>
     * <BR>
     * １）空の検索条件文字列(：String)を生成する。 <BR>
     * <BR>
     * ２）証券会社条件を検索条件に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += " institution_code = ? "<BR>
     * <BR>
     * ３）部店条件を検索条件に追加する。 <BR>
     * パラメータ.部店コード[]の要素数分"?"を追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and branch_code in (?,?,,,) "<BR>
     * <BR>
     * ４）パラメータ.ステータス区分 != nullの場合、 <BR>
     * 口座開設状況区分を検索条件に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and account_open_status_div = ? "<BR>
     * <BR>
     * ５）パラメータ.申込日（自） != nullの場合、 <BR>
     * 申込日の下限を検索条件に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and to_char(created_timestamp, <BR>
     * 'YYYYMMDDHH24') >= ? "<BR>
     * <BR>
     * ６）パラメータ.申込日（至） != nullの場合、 <BR>
     * 申込日の下限を検索条件に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and to_char(created_timestamp, <BR>
     * 'YYYYMMDDHH24') < ? "<BR>
     * <BR>
     * ７）パラメータ.約諾書区分 != nullの場合、<BR>
     * 約諾書区分を検索条件に追加する。<BR>
     * <BR>
     * 検索条件文字列 += "and agreement_div = ? "<BR>
     * <BR>
     * ８）作成した検索条件文字列を返却する。
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コードの配列
     * @@param l_strStatusDiv - ステータス区分
     * 0：口座開設中 1：口座開設完了 2：口座開設エラー 3:ダウンロード済 9：削除
     * @@param l_strApplyHourFrom - 申込日（自） (YYYYMMDDhh)
     * @@param l_strApplyHourTo - 申込日（至） (YYYYMMDDhh)
     * @@param l_strAgreementDiv - 約諾書区分
     * 0：無し 1：有り null：全て
     * @@return String
     * @@roseuid 41BD62BE01A2
     */
    protected String createQueryString(String l_strInstitutionCode,
        String[] l_strBranchCodes, String l_strStatusDiv,
        String l_strApplyHourFrom, String l_strApplyHourTo, String l_strAgreementDiv)
    {
        final String STR_METHOD_NAME = "createQueryString()";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodes == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）空の検索条件文字列(：String)を生成する。
        String l_strWhere = "";
        
        // ２）証券会社条件を検索条件に追加する。 
        // 検索条件文字列 += " institution_code = ? " 
        l_strWhere += " institution_code = ? ";
        
        // ３）部店条件を検索条件に追加する。 
        // パラメータ.部店コード[]の要素数分"?"を追加する。 
        // 検索条件文字列 += "and branch_code in (?,?,,,) "
        l_strWhere += " and branch_code in ( ? ";
        
        for(int i = 1; i < l_strBranchCodes.length; i++)
        {
            l_strWhere += " , ? ";
        }
        
        l_strWhere += " ) ";
        
        // ４）パラメータ.ステータス区分 != nullの場合、 
        // 口座開設状況区分を検索条件に追加する。 
        // 検索条件文字列 += "and account_open_status_div = ? " 
        if(!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_strWhere += " and account_open_status_div = ? ";
        }
        
        // ５）パラメータ.申込日（自） != nullの場合、 
        // 申込日の下限を検索条件に追加する。 
        // 検索条件文字列 += "and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? "
        if(!WEB3StringTypeUtility.isEmpty(l_strApplyHourFrom))
        {
            l_strWhere += " and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? ";
        }
        
        // ６）パラメータ.申込日（至） != nullの場合、 
        // 申込日の下限を検索条件に追加する。 
        // 検索条件文字列 += "and to_char(created_timestamp, 'YYYYMMDDHH24') < ? " 
        if(!WEB3StringTypeUtility.isEmpty(l_strApplyHourTo))
        {
            l_strWhere += " and to_char(created_timestamp, 'YYYYMMDDHH24') < ? ";
        }
        
        // ７）パラメータ.約諾書区分 != nullの場合、
        //　@約諾書区分を検索条件に追加する。
        //  検索条件文字列 += "and agreement_div = ? "
        if (null != l_strAgreementDiv)
        {
            l_strWhere += " and agreement_div = ? ";
        }
        
        // ８）作成した検索条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strWhere;
    }

    /**
     * (create検索条件データコンテナ) <BR>
     * 検索条件文字列を作成する。 <BR>
     * <BR>
     * １）ArrayListを生成する。 <BR>
     * <BR>
     * ２）証券会社条件を追加する。 <BR>
     * パラメータ.証券会社コードをArrayListに追加する。 <BR>
     * <BR>
     * ３）部店条件を追加する。 <BR>
     * パラメータ.部店コード[]の全要素をArrayListに追加する。 <BR>
     * <BR>
     * ４）パラメータ.ステータス区分 != nullの場合、 <BR>
     * パラメータ.ステータス区分をArrayListに追加する。 <BR>
     * <BR>
     * ５）パラメータ.申込日（自） != nullの場合、 <BR>
     * パラメータ.申込日（自）をArrayListに追加する。 <BR>
     * <BR>
     * ６）パラメータ.申込日（至） != nullの場合、 <BR>
     * パラメータ.申込日（至）をArrayListに追加する。 <BR>
     * <BR>
     * ７）パラメータ.約諾書区分 != nullの場合、<BR>
     * パラメータ.約諾書区分をArrayListに追加する。<BR>
     * <BR>
     * ８）生成したArrayList.toArray()の戻り値を返却する。
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コードの配列
     * @@param l_strStatusDiv - ステータス区分
     * 0：口座開設中 1：口座開設完了 2：口座開設エラー 3:ダウンロード済 9：削除
     * @@param l_strApplyHourFrom - 申込日（自） (YYYYMMDDhh)
     * @@param l_strApplyHourTo - 申込日（至） (YYYYMMDDhh)
     * @@param l_strAgreementDiv - 約諾書区分
     * 0：無し 1：有り null：全て
     * @@return String[]
     * @@roseuid 41BD675401A2
     */
    protected String[] createQueryContainer(String l_strInstitutionCode,
        String[] l_strBranchCodes, String l_strStatusDiv,
        String l_strApplyHourFrom, String l_strApplyHourTo, String l_strAgreementDiv)
    {
        final String STR_METHOD_NAME = "createQueryContainer()";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodes == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）ArrayListを生成する。 
        List l_lisVars = new Vector();
        
        // ２）証券会社条件を追加する。 
        // パラメータ.証券会社コードをArrayListに追加する。 
        l_lisVars.add(l_strInstitutionCode);
        
        // ３）部店条件を追加する。 
        // パラメータ.部店コード[]の全要素をArrayListに追加する。 
        for(int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisVars.add(l_strBranchCodes[i]);
        }
        
        // ４）パラメータ.ステータス区分 != nullの場合、 
        // パラメータ.ステータス区分をArrayListに追加する。
        if(!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_lisVars.add(l_strStatusDiv);
        }
        
        // ５）パラメータ.申込日（自） != nullの場合、 
        // パラメータ.申込日（自）をArrayListに追加する。 
        if(!WEB3StringTypeUtility.isEmpty(l_strApplyHourFrom))
        {
            l_lisVars.add(l_strApplyHourFrom);
        }
        
        // ６）パラメータ.申込日（至） != nullの場合、 
        // パラメータ.申込日（至）をArrayListに追加する。 
        if(!WEB3StringTypeUtility.isEmpty(l_strApplyHourTo))
        {
            l_lisVars.add(l_strApplyHourTo);
        }
        
        // ７）パラメータ.約諾書区分 != nullの場合、
        // パラメータ.約諾書区分をArrayListに追加する。
        if (null != l_strAgreementDiv)
        {
            l_lisVars.add(l_strAgreementDiv);
        }
        
        // ８）生成したArrayList.toArray()の戻り値を返却する。
        String[] l_arrVars = new String[l_lisVars.size()];
        l_lisVars.toArray(l_arrVars);
        
        log.exiting(STR_METHOD_NAME);
        return l_arrVars;
    }

    /**
     * (createソート条件) <BR>
     * ソート条件を作成する。 <BR>
     * <BR>
     * １）空のソート条件文字列(：String)を生成する。 <BR>
     * <BR>
     * ２）以下のソート条件を表すソート条件文字列を作成する。 <BR>
     * 作成日付　@降順 <BR>
     * <BR>
     * ソート条件文字列 = " created_timestamp desc" <BR>
     * <BR>
     * ３）作成したソート条件文字列を返却する。
     * 
     * @@return String
     * @@roseuid 41BD6B0C0377
     */
    protected String createSortCond()
    {
        final String STR_METHOD_NAME = "createSortCond()";
        log.entering(STR_METHOD_NAME);
        
        // １）空のソート条件文字列(：String)を生成する。
        String l_strSort = "";
        
        // ２）以下のソート条件を表すソート条件文字列を作成する。 
        // 作成日付　@降順 
        // ソート条件文字列 = " created_timestamp  desc" 
        l_strSort += " created_timestamp desc";
        
        // ３）作成したソート条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strSort;
    }

    /**
     * (get対象GFT口座開設状況一覧) <BR>
     * 引数のMRF口座開設区分に該当する <BR>
     * GFT口座開設状況の一覧を返却する。 <BR>
     * <BR>
     * １）パラメータ.MRF口座開設区分 == nullの場合、 <BR>
     * パラメータ.GFT口座開設状況一覧を返却して終了する。 <BR>
     * <BR>
     * ２）ArrayListを生成する。 <BR>
     * <BR>
     * ３）パラメータ.GFT口座開設状況一覧の要素数分、 <BR>
     * 以下の処理を繰り返す。 <BR>
     * ３－１）拡張アカウントマネージャ.get顧客()メソッドをコールする。 <BR>
     * <BR>
     * [get顧客()にセットするパラメータ] <BR>
     * 証券会社コード： GFT口座開設状況.証券会社コード <BR>
     * 部店コード： GFT口座開設状況.部店コード <BR>
     * 口座コード： GFT口座開設状況.顧客コード <BR>
     * <BR>
     * ３－２）３－１）の戻り値.MRF口座開設区分 == パラメータ.MRF口座開設区分の場合、<BR>
     * 生成したArrayListにGFT口座開設状況を追加する。 <BR>
     * <BR>
     * ４）生成したArrayList.toArray()の戻り値を返却する。
     * 
     * @@param l_gftAccountOpenSatusParamses - GFT口座開設状況Paramsオブジェクトの配列
     * @@param l_strMrfAccountStatusDiv - 1：開設 2：未開設
     * @@return GftAccountOpenStatusParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BD7F1A0044
     */
    protected GftAccountOpenStatusParams[] getObjGFTAccOpenStatusList(
        GftAccountOpenStatusParams[] l_gftAccountOpenSatusParamses,
        String l_strMrfAccountStatusDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getObjGFTAccOpenStatusList()";
        log.entering(STR_METHOD_NAME);
        
        if (l_gftAccountOpenSatusParamses == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）パラメータ.MRF口座開設区分 == nullの場合、 
        // パラメータ.GFT口座開設状況一覧を返却して終了する。
        if(WEB3StringTypeUtility.isEmpty(l_strMrfAccountStatusDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return l_gftAccountOpenSatusParamses;
        }
        
        // ２）ArrayListを生成する。
        List l_lisAccountOpenStatusParams = new Vector();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        //拡張アカウントマネージャ取得する    
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        // ３）パラメータ.GFT口座開設状況一覧の要素数分、 
        // 以下の処理を繰り返す。 
        for(int i = 0; i < l_gftAccountOpenSatusParamses.length; i++)
        {
            GftAccountOpenStatusRow l_accountOpenStatusRow = 
                (GftAccountOpenStatusRow) l_gftAccountOpenSatusParamses[i];
            
            // ３－１）拡張アカウントマネージャ.get顧客()メソッドをコールする。 
            // 　@[get顧客()にセットするパラメータ] 
            // 　@　@証券会社コード：　@GFT口座開設状況.証券会社コード 
            // 　@　@部店コード：　@GFT口座開設状況.部店コード 
            // 　@　@口座コード：　@GFT口座開設状況.顧客コード 
            
            try
            {
				WEB3GentradeMainAccount l_mainAccount = 
					l_accountManager.getMainAccount(
						l_accountOpenStatusRow.getInstitutionCode(),
						l_accountOpenStatusRow.getBranchCode(),
						l_accountOpenStatusRow.getAccountCode()
						);
				// ３－２）３－１）の戻り値.MRF口座開設区分 == 
				// 　@パラメータ.MRF口座開設区分の場合、 
				// 　@生成したArrayListにGFT口座開設状況を追加する。
				MainAccountRow l_mainAccountRow = 
					(MainAccountRow) l_mainAccount.getDataSourceObject();
				if(l_strMrfAccountStatusDiv.equals(l_mainAccountRow.getMrfAccOpenDiv()))
				{
					l_lisAccountOpenStatusParams.add(l_accountOpenStatusRow);
				}
            }
            catch(WEB3BaseException l_ex)
            {
            	if(l_strMrfAccountStatusDiv.equals(WEB3AccountOpenDef.NOT_OPEN))
            	{
					l_lisAccountOpenStatusParams.add(l_accountOpenStatusRow);
            	}
            }           

        }
        
        // ４）生成したArrayList.toArray()の戻り値を返却する。
        GftAccountOpenStatusParams[] l_arrAccountOpenStatusParams = null;
        //※返却する配列の要素数が0の場合は、要素数が0の配列が返却する。 
        if(l_lisAccountOpenStatusParams.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            l_arrAccountOpenStatusParams = new GftAccountOpenStatusParams[0];
            return l_arrAccountOpenStatusParams;
        }
        l_arrAccountOpenStatusParams = 
            new GftAccountOpenStatusParams[l_lisAccountOpenStatusParams.size()];
        l_lisAccountOpenStatusParams.toArray(l_arrAccountOpenStatusParams);
         
        log.exiting(STR_METHOD_NAME);
        return l_arrAccountOpenStatusParams;
    }

    /**
     * (createFX口座情報一覧) <BR>
     * 引数のGFT口座開設状況より、FX口座情報の一覧を作成する。 <BR>
     * <BR>
     * １）ArrayListを生成する。 <BR>
     * <BR>
     * ２）1万通貨コースの口座情報作成 <BR>
     * ①@FX口座情報インスタンスを生成し、以下のプロパティをセットする。 <BR>
     * <BR>
     * コース区分： "1万通貨コース" <BR>
     * 口座番号： パラメータ.GFT口座開設状況.口座番号(1万通貨コース) <BR>
     * <BR>
     * ②FX口座情報インスタンス.口座番号がnullでない場合、 <BR>
     *   生成したArrayListにプロパティセットしたインスタンスを追加する。 <BR>
     * <BR>
     * ３）10万通貨コースの口座情報作成 <BR>
     * ①@FX口座情報インスタンスを生成し、以下のプロパティをセットする。 <BR>
     * <BR>
     * コース区分： "10万通貨コース" <BR>
     * 口座番号： パラメータ.GFT口座開設状況.口座番号(10万通貨コース) <BR>
     * <BR>
     * ②FX口座情報インスタンス.口座番号がnullでない場合、 <BR>
     *   生成したArrayListにプロパティセットしたインスタンスを追加する。 <BR>
     * <BR>
     * ４）連携用口座番号の口座情報作成<BR>
     * 　@①@FX口座情報インスタンスを生成し、以下のプロパティをセットする。<BR>
     * <BR>
     * 　@　@コース区分：　@"CFDコース"<BR>
     * 　@　@口座番号：　@パラメータ.GFT口座開設状況.連携用口座番号<BR>
     * <BR>
     * 　@②FX口座情報インスタンス.口座番号がnullでない場合、 <BR>
     *     生成したArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * ５）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * 
     * @@param l_gftAccountOpenSatusParams - GFT口座開設状況Paramオブジェクト
     * @@return WEB3FXAccInformationUnit[]
     * @@roseuid 41BD8EA30214
     */
    protected WEB3FXAccInformationUnit[] createFXAccInfoList(
        GftAccountOpenStatusParams l_gftAccountOpenSatusParams)
    {
        final String STR_METHOD_NAME = "createFXAccInfoList()";
        log.entering(STR_METHOD_NAME);
        
        if (l_gftAccountOpenSatusParams == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）ArrayListを生成する。 
        List l_lisFXAccInformationUnits = new Vector();
        
        // ２）1万通貨コースの口座情報作成 
        // ２－１）FX口座情報インスタンスを生成し、以下のプロパティをセットする。
        // 　@コース区分：　@"1万通貨コース" 
        // 　@口座番号：　@パラメータ.GFT口座開設状況.口座番号(1万通貨コース) 
        WEB3FXAccInformationUnit l_accInfoUnit01 = 
            new WEB3FXAccInformationUnit();
        l_accInfoUnit01.fxCourseDiv = 
            WEB3GftTransStatusCourseDivDef.ONE_COSE;
        l_accInfoUnit01.fxAccountCode = 
            l_gftAccountOpenSatusParams.getFxAccountCode01();
        
        // ２－２）FX口座情報インスタンス.口座番号がnullでない場合、
        //         生成したArrayListにプロパティセットしたインスタンスを追加する。
        if (l_accInfoUnit01.fxAccountCode != null)
        {
            l_lisFXAccInformationUnits.add(l_accInfoUnit01);
        }

        // ３）10万通貨コースの口座情報作成 
        // ３－１）FX口座情報インスタンスを生成し、以下のプロパティをセットする。 
        // 　@コース区分：　@"10万通貨コース" 
        // 　@口座番号：　@パラメータ.GFT口座開設状況.口座番号(10万通貨コース) 
        WEB3FXAccInformationUnit l_accInfoUnit10 = 
            new WEB3FXAccInformationUnit();
        l_accInfoUnit10.fxCourseDiv = WEB3GftTransStatusCourseDivDef.TEN_COSE;
        l_accInfoUnit10.fxAccountCode = 
            l_gftAccountOpenSatusParams.getFxAccountCode10();
        
        // ３－２）FX口座情報インスタンス.口座番号がnullでない場合、
        //         生成したArrayListにプロパティセットしたインスタンスを追加する。
        if (l_accInfoUnit10.fxAccountCode != null)
        {
            l_lisFXAccInformationUnits.add(l_accInfoUnit10);
        }

        //４）連携用口座番号の口座情報作成
        //①@FX口座情報インスタンスを生成し、以下のプロパティをセットする。
        //コース区分：　@"CFDコース"
        //口座番号：　@パラメータ.GFT口座開設状況.連携用口座番号
        WEB3FXAccInformationUnit l_accInfoUnitCFD =
            new WEB3FXAccInformationUnit();
        l_accInfoUnitCFD.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE;
        l_accInfoUnitCFD.fxAccountCode =
            l_gftAccountOpenSatusParams.getExtAccountCode();
        //②FX口座情報インスタンス.口座番号がnullでない場合、
        //  生成したArrayListにプロパティセットしたインスタンスを追加する。
        if (l_accInfoUnitCFD.fxAccountCode != null)
        {
            l_lisFXAccInformationUnits.add(l_accInfoUnitCFD);
        }

        // ５）生成したArrayList.toArray()の戻り値を返却する。
        WEB3FXAccInformationUnit[] l_arrFXAccInformationUnits = 
            new WEB3FXAccInformationUnit[l_lisFXAccInformationUnits.size()];
        l_lisFXAccInformationUnits.toArray(l_arrFXAccInformationUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_arrFXAccInformationUnits;
    }

    /**
     * (createFX取引同意質問情報一覧) <BR>
     * 引数の質問回答一覧より、FX取引同意質問情報の一覧を作成する。 <BR>
     * <BR>
     * １）ArrayListを生成する。 <BR>
     * <BR>
     * ２）パラメータ.質問回答一覧の要素数分、以下の処理を繰り返す。 <BR>
     * ２－１）FX取引同意質問情報インスタンスを生成する。 <BR>
     * <BR>
     * ２－２）生成したインスタンスに以下のプロパティセットを行う。 <BR>
     * 質問番号 = 質問回答Params.質問番号 <BR>
     * 質問内容 = null <BR>
     * 質問回答 = 質問回答Params.質問回答 <BR>
     * <BR>
     * ２－３）生成したArrayListにプロパティセットしたインスタンスを追加する。 <BR>
     * <BR>
     * ３）生成したArrayList.toArray()の戻り値を返却する。
     * 
     * @@param l_questionAnswerParamses - 質問回答Paramsオブジェクトの配列
     * @@return WEB3FXTradeAgreementUnit[]
     * @@roseuid 41BD977802B3
     */
    protected WEB3FXTradeAgreementUnit[] createFXTradeAgreeQuestionInfoList(
        QuestionAnswerParams[] l_questionAnswerParamses)
    {
        final String STR_METHOD_NAME = "createFXTradeAgreeQuestionInfoList()";
        log.entering(STR_METHOD_NAME);
        
        if (l_questionAnswerParamses == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）ArrayListを生成する。 
        List l_lisFXTradeAgreementUnits = new Vector();
        
        // ２）パラメータ.質問回答一覧の要素数分、 
        // 以下の処理を繰り返す。 
        for(int i = 0; i < l_questionAnswerParamses.length; i++)
        {
            QuestionAnswerRow l_questionAnswerRow = l_questionAnswerParamses[i];
            
            // ２－１）FX取引同意質問情報インスタンスを生成する。 
            WEB3FXTradeAgreementUnit l_tradeAgreementUnit = 
                new WEB3FXTradeAgreementUnit();
            
            // ２－２）生成したインスタンスに以下のプロパティセットを行う。 
            // 　@質問番号 = 質問回答Params.質問番号 
            // 　@質問内容 = null 
            // 　@質問回答 = 質問回答Params.質問回答 
            l_tradeAgreementUnit.questionNumber = 
                l_questionAnswerRow.getQuestionNo();
            l_tradeAgreementUnit.questionContents = null;
            l_tradeAgreementUnit.questionAnswer = 
                l_questionAnswerRow.getQuestionAnswer();

            // ２－３）生成したArrayListにプロパティセットしたインスタンスを追加する。
            l_lisFXTradeAgreementUnits.add(l_tradeAgreementUnit);
        } 

        // ３）生成したArrayList.toArray()の戻り値を返却する。
        WEB3FXTradeAgreementUnit[] l_arrFXTradeAgreementUnits = 
            new WEB3FXTradeAgreementUnit[l_lisFXTradeAgreementUnits.size()];
        l_lisFXTradeAgreementUnits.toArray(l_arrFXTradeAgreementUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_arrFXTradeAgreementUnits;
    }

    /**
     * (isMRF残高保有) <BR>
     * 引数の顧客がMRF残高を保有しているかどうか判別する。 <BR>
     * <BR>
     * [戻り値] <BR>
     * false： 保有していない <BR>
     * true： 保有している <BR>
     * <BR>
     * １）DB検索 <BR>
     * 以下の条件で、顧客勘定残高(tp_cash_balance)を検索する。 <BR>
     * <BR>
     * 口座ID = パラメータ.顧客.口座ID <BR>
     * 補助口座ID = 補助口座(*1).補助口座ID <BR>
     * <BR>
     * ２）検索結果.MRF残高 == (null or 0)の場合、falseを返却する。<BR>
     * 以外、trueを返却する。 <BR>
     * <BR>
     * (*1)パラメータ.顧客.getSubAccount(SubAccountTypeEnum.株式取引口座)にて <BR>
     * 取得した補助口座
     * 
     * @@param l_mainAccount - 顧客オブジェクト
     * @@return Boolean
     * @@throws WEB3BaseException
     * @@roseuid 41BE44B8027C
     */
    protected boolean isMRFBalanceHolding(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isMRFBalanceHolding()";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // １）DB検索 
        // 以下の条件で、顧客勘定残高(tp_cash_balance)を検索する。 
        // 口座ID = パラメータ.顧客.口座ID 
        // 補助口座ID = 補助口座(*1).補助口座ID 
        List l_lisRow = null;
        try
        {   
            // (*1)パラメータ.顧客.getSubAccount(SubAccountTypeEnum.株式取引口座)にて 
            // 取得した補助口座
            SubAccount l_subAccount = 
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            String l_strWhere = " account_id = ? and sub_account_id = ? ";
            Object[] l_objVars = {
                new Long(l_mainAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId())
                };
            l_lisRow = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    TpCashBalanceRow.TYPE,
                    l_strWhere,                    
                    null,
                    l_objVars);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
        
        // ２）検索結果.MRF残高 == (null or 0)の場合、falseを返却する。 
        // 以外、trueを返却する。 
        if(l_lisRow != null 
            && l_lisRow.size() > 0
            && ((TpCashBalanceRow) l_lisRow.get(0)).getMrfBalance() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (get処理区分) <BR>
     * 引数のGFT口座開設状況Paramsより、現在の処理区分を判別し、返却する。 <BR>
     * ※返却される値は、FX口座開設申込明細.備考のアイテム定義を参照。 <BR>
     * <BR>
     * [口座開設状況区分 == "口座開設済"の場合]
     * "口座開設完了"を返却する。<BR>
     * <BR>
     * [（口座開設状況区分 == "口座開設中" または "ダウンロード済")かつ<BR>
     * 送受信区分 == "送信済"の場合]<BR>
     * "口座開設中"を返却する。 <BR>
     * <BR>
     * [口座開設状況区分 == "口座開設エラー" かつ"送受信区分" == "受信済"の場合] <BR>
     * パラメータ.GFT口座開設状況Params.受付結果コードを返却する。 <BR>
     * <BR>
     * [口座開設区分 == "削除"の場合]"削除"を返却する。 <BR>
     * <BR>
     * [上記以外の場合] <BR>
     * "システムエラー"を返却する。
     * 
     * @@param l_gftAccountOpenSatusParams - GFT口座開設状況Paramsオブジェクト
     * @@return String
     * @@roseuid 41CBF57802ED
     */
    protected String getOperationDiv(
        GftAccountOpenStatusParams l_gftAccountOpenSatusParams)
    {
        final String STR_METHOD_NAME = "getOperationDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (l_gftAccountOpenSatusParams == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strOpenStatusDiv = l_gftAccountOpenSatusParams.getAccountOpenStatusDiv();
        String l_strSendRcvDiv = l_gftAccountOpenSatusParams.getSendRcvDiv();
        
        // [口座開設状況区分 == "口座開設済"の場合] 
        // "口座開設完了"を返却する。 
        if(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(
                l_strOpenStatusDiv))           
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioAccOpenRemarkDef.ACCEPT_RESULT_CODE_00000000;
        }

        // [（口座開設状況区分 == "口座開設中" または "ダウンロード済")かつ 
        // 　@送受信区分 == "送信済"の場合] 
        // 　@"口座開設中"を返却する。 
        else if((WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_strOpenStatusDiv)
            || WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE.equals(l_strOpenStatusDiv))    
            && (WEB3SendRcvDivDef.SEND_COMPLETE.equals(
                l_strSendRcvDiv))
            )
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioAccOpenRemarkDef.ACCEPT_RESULT_CODE_10000000;
        }

        // [口座開設状況区分 == "口座開設エラー" かつ 
        // 　@"送受信区分" == "受信済"の場合] 
        // 　@パラメータ.GFT口座開設状況Params.受付結果コードを返却する。 
        else if(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(
                l_strOpenStatusDiv)
            && (WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(
                l_strSendRcvDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            return l_gftAccountOpenSatusParams.getResultCode();
        }
        
        // [口座開設区分 == "削除"の場合] 
        // 　@"削除"を返却する。 
        else if(WEB3AccountOpenStatusDivDef.DELETE.equals(
            l_strOpenStatusDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioAccOpenRemarkDef.ACCEPT_RESULT_CODE_90000000;
        }
        
        // [上記以外の場合] 
        // 　@"システムエラー"を返却する。 
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioAccOpenRemarkDef.ACCEPT_RESULT_CODE_99999999;
        }
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * (管理者FX口座開設管理)FX口座開設申込ダウンロードファ@イルデータ取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     *「(管理者FX口座開設管理サービス)getダウンロードファ@イル」 参照<BR>
     * 1.10.doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : String, arg3 : String,<BR>
     * arg4 : Object[])該当するレコードが存在しなかった場合、例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00398<BR>
     * <BR>
     * @@param  l_request - 管理者・FX口座開設申込ダウンロードリクエスト
     * @@return WEB3AdminFXAccOpenApplyDownloadResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminFXAccOpenApplyDownloadResponse getDownloadFile(
        WEB3AdminFXAccOpenApplyDownloadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminFXAccOpenApplyDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE, true);
        
        //1.4 validate部店権限(部店コード : String[])
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        //1.5 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 create検索条件文字列(String, String[], String, String, String, String)
        String l_strWhere = this.createQueryString(
            l_strInstitutionCode,
            l_request.branchCodeList,
            l_request.statusDiv,
            l_request.applyHourFrom,
            l_request.applyHourTo,
            l_request.agreementDiv);
        
        //1.7 create検索条件データコンテナ(String, String[], String, String, String, String)
        String[] l_strVars = this.createQueryContainer(
            l_strInstitutionCode,
            l_request.branchCodeList,
            l_request.statusDiv,
            l_request.applyHourFrom,
            l_request.applyHourTo,
            l_request.agreementDiv);
        
        //1.8 createソート条件( )
        String l_strSort = this.createSortCond();

        QueryProcessor l_queryProcessor = null;
        List l_lisRows = null;
        try
        {
            //1.9 getDefaultProcessor( )
            l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.10 doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : String, arg3 : String, arg4 : Object[])
            //[引数] 
            //rowType：　@ GFT口座開設状況Row.TYPE 
            //where：　@   create検索条件文字列()の戻り値 
            //orderBy：　@ createソート条件()の戻り値
            //conditions："for update"
            //bindVars：　@create検索条件データコンテナ()の戻り値
            l_lisRows = l_queryProcessor.doFindAllQuery(
                GftAccountOpenStatusRow.TYPE,
                l_strWhere,
                l_strSort,
                "for update",
                l_strVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //該当するレコードが存在しなかった場合、例外をスローする。
        if (null == l_lisRows || 0 == l_lisRows.size())
        {
            log.debug("該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }
        
        //1.11 管理者FX口座開設申込ダウンロードCSV( )
        WEB3AdminFXAccOpenApplyDownloadCsv l_downloadCsv = new WEB3AdminFXAccOpenApplyDownloadCsv();
        
        //1.12 取得したGFT口座開設状況行毎に、Loop処理を実施する
        GftAccountOpenStatusParams[] l_params = new GftAccountOpenStatusParams[l_lisRows.size()];
        l_lisRows.toArray(l_params);
        for (int i = 0; i < l_params.length; i++)
        {
            //1.12.1 add明細行( )
            int l_intLineNumber = l_downloadCsv.addRow();
            
            //1.12.2 set追加変更区分(int)
            l_downloadCsv.setAddModifiedDiv(l_intLineNumber);
            
            //1.12.3 set利用者コード(int, String)
            l_downloadCsv.setUserCode(l_intLineNumber, l_params[i].login_id.substring(1));
            
            //1.12.4 set利用者名(int, String)
            l_downloadCsv.setUserName(l_intLineNumber, l_params[i].getLastName());
            
            //1.12.5 setログインID(int, String)
            l_downloadCsv.setLoginId(l_intLineNumber, l_params[i].login_id.substring(1));
            
            //1.12.6 setログインパスワード(int)
            l_downloadCsv.setLoginPassword(l_intLineNumber);
            
            //1.12.7 set発注パスワード(int)
            l_downloadCsv.setOrderPassword(l_intLineNumber);
            
            //1.12.8 setメールアドレス１(int, String)
            l_downloadCsv.setMailAddress1(l_intLineNumber,l_params[i].getMailAddress());
            
            //1.12.9 setメールアドレス２(int)
            l_downloadCsv.setMailAddress2(l_intLineNumber);
            
            //1.12.10 set自己受託区分(int)
            l_downloadCsv.setSelfTrustDiv(l_intLineNumber);

            //set利用者属性(int)
            l_downloadCsv.setUserAttribute(l_intLineNumber);
            
            //set決済方法@(int)
            l_downloadCsv.setTransferMethod(l_intLineNumber);
            
            //1.12.11 setロスカット区分(int)
            l_downloadCsv.setLossCutDiv(l_intLineNumber);
            
            //1.12.12 set手数料区分(int)
            l_downloadCsv.setCommissionDiv(l_intLineNumber);
            
            //1.12.13 set取引可能区分(int)
            l_downloadCsv.setTradingDiv(l_intLineNumber);

            //set電子交付承諾日(int, Date)
            l_downloadCsv.setReportAcceptDate(l_intLineNumber,l_params[i].getCreatedTimestamp());

            //set取引説明書確認日(int, Date)
            l_downloadCsv.setTradeInstructionsConfirmDate(l_intLineNumber,l_params[i].getCreatedTimestamp());

            //set約諾書番号(int)
            l_downloadCsv.setContractCode(l_intLineNumber);
            
            //1.12.14 set備考(int String)
            l_downloadCsv.setRemark(l_intLineNumber, l_params[i].getOrderRequestNumber());
            
            //1.12.15 set商品コード１(int)
            l_downloadCsv.setProductCode1(l_intLineNumber);
            
            //1.12.16 set発注上限数量１(int)
            l_downloadCsv.setOrderQuantityUpper1(l_intLineNumber);
            
            //1.12.17 set商品コード２(int)
            l_downloadCsv.setProductCode2(l_intLineNumber);
            
            //1.12.18 set発注上限数量２(int)
            l_downloadCsv.setOrderQuantityUpper2(l_intLineNumber);
            
            //1.12.19 set商品コード３(int)
            l_downloadCsv.setProductCode3(l_intLineNumber);
            
            //1.12.20 set発注上限数量３(int)
            l_downloadCsv.setOrderQuantityUpper3(l_intLineNumber);
            
            //1.12.21 set商品コード４(int)
            l_downloadCsv.setProductCode4(l_intLineNumber);
            
            //1.12.22 set発注上限数量４(int)
            l_downloadCsv.setOrderQuantityUpper4(l_intLineNumber);
            
            //1.12.23 set商品コード５(int)
            l_downloadCsv.setProductCode5(l_intLineNumber);
            
            //1.12.24 set発注上限数量５(int)
            l_downloadCsv.setOrderQuantityUpper5(l_intLineNumber);
            
            //1.12.25 set商品コード６(int)
            l_downloadCsv.setProductCode6(l_intLineNumber);
            
            //1.12.26 set発注上限数量６(int)
            l_downloadCsv.setOrderQuantityUpper6(l_intLineNumber);
            
            //1.12.27 set商品コード７(int)
            l_downloadCsv.setProductCode7(l_intLineNumber);
            
            //1.12.28 set発注上限数量７(int)
            l_downloadCsv.setOrderQuantityUpper7(l_intLineNumber);

            //set商品コード８(int)
            l_downloadCsv.setProductCode8(l_intLineNumber);

            //set発注上限数量８(int)
            l_downloadCsv.setOrderQuantityUpper8(l_intLineNumber);

            //set商品コード９(int)
            l_downloadCsv.setProductCode9(l_intLineNumber);

            //set発注上限数量９(int)
            l_downloadCsv.setOrderQuantityUpper9(l_intLineNumber);

            //set商品コード１０(int)
            l_downloadCsv.setProductCode10(l_intLineNumber);

            //set発注上限数量１０(int)
            l_downloadCsv.setOrderQuantityUpper10(l_intLineNumber);

            //set商品コード１１(int)
            l_downloadCsv.setProductCode11(l_intLineNumber);

            //set発注上限数量１１(int)
            l_downloadCsv.setOrderQuantityUpper11(l_intLineNumber);

            //set商品コード１２(int)
            l_downloadCsv.setProductCode12(l_intLineNumber);

            //set発注上限数量１２(int)
            l_downloadCsv.setOrderQuantityUpper12(l_intLineNumber);

            //set商品コード１３(int)
            l_downloadCsv.setProductCode13(l_intLineNumber);

            //set発注上限数量１３(int)
            l_downloadCsv.setOrderQuantityUpper13(l_intLineNumber);

            //set商品コード１４(int)
            l_downloadCsv.setProductCode14(l_intLineNumber);

            //set発注上限数量１４(int)
            l_downloadCsv.setOrderQuantityUpper14(l_intLineNumber);

            //set商品コード１５(int)
            l_downloadCsv.setProductCode15(l_intLineNumber);

            //set発注上限数量１５(int)
            l_downloadCsv.setOrderQuantityUpper15(l_intLineNumber);

            //set商品コード１６(int)
            l_downloadCsv.setProductCode16(l_intLineNumber);

            //set発注上限数量１６(int)
            l_downloadCsv.setOrderQuantityUpper16(l_intLineNumber);

            //set商品コード１７(int)
            l_downloadCsv.setProductCode17(l_intLineNumber);

            //set発注上限数量１７(int)
            l_downloadCsv.setOrderQuantityUpper17(l_intLineNumber);

            //set商品コード１８(int)
            l_downloadCsv.setProductCode18(l_intLineNumber);

            //set発注上限数量１８(int)
            l_downloadCsv.setOrderQuantityUpper18(l_intLineNumber);

            //set商品コード１９(int)
            l_downloadCsv.setProductCode19(l_intLineNumber);

            //set発注上限数量１９(int)
            l_downloadCsv.setOrderQuantityUpper19(l_intLineNumber);

            //set商品コード２０(int)
            l_downloadCsv.setProductCode20(l_intLineNumber);

            //set発注上限数量２０(int)
            l_downloadCsv.setOrderQuantityUpper20(l_intLineNumber);

            //set商品コード２１(int)
            l_downloadCsv.setProductCode21(l_intLineNumber);

            //set発注上限数量２１(int)
            l_downloadCsv.setOrderQuantityUpper21(l_intLineNumber);

            //set商品コード２２(int)
            l_downloadCsv.setProductCode22(l_intLineNumber);

            //set発注上限数量２２(int)
            l_downloadCsv.setOrderQuantityUpper22(l_intLineNumber);

            //set商品コード２３(int)
            l_downloadCsv.setProductCode23(l_intLineNumber);

            //set発注上限数量２３(int)
            l_downloadCsv.setOrderQuantityUpper23(l_intLineNumber);

            //set商品コード２４(int)
            l_downloadCsv.setProductCode24(l_intLineNumber);

            //set発注上限数量２４(int)
            l_downloadCsv.setOrderQuantityUpper24(l_intLineNumber);

            //set商品コード２５(int)
            l_downloadCsv.setProductCode25(l_intLineNumber);

            //set発注上限数量２５(int)
            l_downloadCsv.setOrderQuantityUpper25(l_intLineNumber);
        }

        //1.13 getCSVファ@イル行( )
        String[] l_strCsvFileLines = l_downloadCsv.getCsvFileLines();
        
        //1.14 doUpdateAllQuery(arg0 : RowType, arg1 : String, arg2 : Object[], arg3 : Map)
        //[引数] 
        //rowType：　@ GFT口座開設状況Row.TYPE
        //where：　@   create検索条件文字列()の戻り値
        //bindVars：　@create検索条件データコンテナ()の戻り値
        HashMap l_hashMap = new HashMap();
        l_hashMap.put("account_open_status_div", WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE);
        l_hashMap.put("last_updater", l_admin.getAdministratorCode());
        l_hashMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        
        try
        {
            l_queryProcessor.doUpdateAllQuery(
                GftAccountOpenStatusRow.TYPE,
                l_strWhere,
                l_strVars,
                l_hashMap);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
            
        //1.15 createResponse( )
        WEB3AdminFXAccOpenApplyDownloadResponse l_response = 
            (WEB3AdminFXAccOpenApplyDownloadResponse) l_request.createResponse();
        
        //1.16 (*)プロパティセット
        //ダウンロードファ@イル   ：getCSVファ@イル行()の戻り値
        l_response.downloadFile = l_strCsvFileLines;

        //現在日時              ：システムタイムスタンプ
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validateステータス変更可能)<BR>
     * ステータスの変更が可能かどうかチェックする。<BR>
     * <BR>
     * １）口座開設中チェック<BR>
     * 　@１－１）下記の条件と一致する場合、例外「口座開設中チェックエラー。」をスローする。<BR>
     * 　@　@　@　@　@・更新前口座開設状況区分（"口座開設中"または"ダウンロード済"）以外の場合<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag  : BUSINESS_ERROR_01807<BR>
     * <BR>
     * ２）約諾書区分更新チェック<BR>
     * 　@２－１）下記の条件と一致する場合、例外をスローする。<BR>
     * 　@　@　@　@　@・更新後約諾書区分が"送信済"の場合、更新前約諾書区分が"未送信"以外の場合。<BR>
     * 　@　@　@　@　@・更新後約諾書区分が"未送信"または"受領済"の場合、更新前約諾書区分が"送信済"以外の場合。<BR>
     * <BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag  : BUSINESS_ERROR_02403<BR>
     * <BR>
     * ３）口座開設完了更新チェック<BR>
     *  (更新後約諾書区分が"口座開設完了"の場合にチェックする。)<BR>
     * 　@３－１）下記の条件と一致する場合、例外「約諾書無しエラー。」をスローする。<BR>
     * 　@　@　@　@　@・更新後約諾書区分が"未送信"または"送信済"の場合。<BR>
     * 　@　@　@　@　@・更新後約諾書区分がnullの場合、更新前約諾書区分が"受領済"以外の場合。<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag  : BUSINESS_ERROR_02350<BR>
     * <BR>
     * @@param l_strBeforeUpdOpenStatusDiv - 更新前口座開設状況区分
     * @@param l_strAfterUpdOpenStatusDiv  - 更新後口座開設状況区分 
     * @@param l_strBeforeUpdAgreementDiv  - 更新前約諾書区分
     * @@param l_strAfterUpdAgreementDiv   - 更新後約諾書区分
     * @@throws WEB3BaseException
     */
    protected void validateStatusChangePossible(
        String l_strBeforeUpdOpenStatusDiv,
        String l_strAfterUpdOpenStatusDiv,
        String l_strBeforeUpdAgreementDiv,        
        String l_strAfterUpdAgreementDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateStatusChangePossible(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBeforeUpdOpenStatusDiv == null || l_strBeforeUpdAgreementDiv == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）口座開設中チェック
        //・更新前口座開設状況区分（"口座開設中"または"ダウンロード済"または"口座開設エラー"）以外の場合
        if (!(WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(l_strBeforeUpdOpenStatusDiv)
             || WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE.equals(l_strBeforeUpdOpenStatusDiv)
             || WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(l_strBeforeUpdOpenStatusDiv)))
        {
            log.debug("口座開設中チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01807,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設中チェックエラー");
        }
        
        //２）約諾書区分更新チェック
        //・更新後約諾書区分が"送信済"の場合、更新前約諾書区分が"未送信"以外の場合。
        if (WEB3AioAgreementDivDef.SENDED.equals(l_strAfterUpdAgreementDiv) 
            && !(WEB3AioAgreementDivDef.NOT_SEND.equals(l_strBeforeUpdAgreementDiv)))
        {
            log.debug("約諾書区分更新チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02403,
                this.getClass().getName() + STR_METHOD_NAME,
                "約諾書区分更新チェックエラー。");
            
        }
        //・更新後約諾書区分が"未送信"または"受領済"の場合、更新前約諾書区分が"送信済"以外の場合。
        if ((WEB3AioAgreementDivDef.NOT_SEND.equals(l_strAfterUpdAgreementDiv)
            || WEB3AioAgreementDivDef.RECIEVED.equals(l_strAfterUpdAgreementDiv))
            && !WEB3AioAgreementDivDef.SENDED.equals(l_strBeforeUpdAgreementDiv))
        {
            log.debug("約諾書区分更新チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02403,
                this.getClass().getName() + STR_METHOD_NAME,
                "約諾書区分更新チェックエラー。");
            
        }
        
        //３）口座開設完了更新チェック
        // (更新後約諾書区分が"口座開設完了"の場合にチェックする。)
        if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(l_strAfterUpdOpenStatusDiv))
        {
            //・更新後約諾書区分が"未送信"または"送信済"の場合。
            if (WEB3AioAgreementDivDef.NOT_SEND.equals(l_strAfterUpdAgreementDiv)
                || WEB3AioAgreementDivDef.SENDED.equals(l_strAfterUpdAgreementDiv))
            {
                log.debug("約諾書無しエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02350,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "約諾書無しエラー。");
            }
            //・更新後約諾書区分がnullの場合、更新前約諾書区分が"受領済"以外の場合。
            if (l_strAfterUpdAgreementDiv == null 
                && !WEB3AioAgreementDivDef.RECIEVED.equals(l_strBeforeUpdAgreementDiv))
            {
                log.debug("約諾書無しエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02350,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "約諾書無しエラー。");
            }   
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create取得条件文字列)<BR>
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
     * 　@検索条件文字列 += "and branch_code in (?,?,,,) "<BR>
     * <BR>
     * ４）パラメータ.ステータス区分 != nullの場合、<BR>
     * 　@口座開設状況区分を検索条件に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and account_open_status_div = ? "<BR>
     * <BR>
     * ５）パラメータ.申込日（自） != nullの場合、<BR>
     * 　@申込日の下限を検索条件に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? "<BR>
     * <BR>
     * ６）パラメータ.申込日（至） != nullの場合、<BR>
     * 　@申込日の下限を検索条件に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and to_char(created_timestamp, 'YYYYMMDDHH24') < ? "<BR>
     * <BR>
     * ７）パラメータ.約諾書区分 != nullの場合、<BR>
     * 　@約諾書区分を検索条件に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and agreement_div = ? "<BR>
     * <BR>
     * ８）パラメータ.FXシステムコード != nullの場合、<BR>
     * 　@FXシステムコードを検索条件に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and fx_system_code = ? "<BR>
     * <BR>
     * <BR>
     * ９）作成した検索条件文字列を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コードの配列<BR>
     * @@param l_strStatusDiv - (ステータス区分)<BR>
     * ステータス区分<BR>
     * <BR>
     * 0：口座開設中<BR>
     * 1：口座開設完了<BR>
     * 2：口座開設エラー<BR>
     * 9：削除<BR>
     * <BR>
     * @@param l_strApplyHourFrom - (申込日（自）)<BR>
     * 申込日（自）<BR>
     * (YYYYMMDDhh)<BR>
     * @@param l_strApplyHourTo - (申込日（至）)<BR>
     * 申込日（至）<BR>
     * (YYYYMMDDhh)<BR>
     * @@param l_strAgreementDiv - (約諾書区分)<BR>
     * 約諾書区分<BR>
     * <BR>
     * 0：無し<BR>
     * 1：有り<BR>
     * null：全て<BR>
     * <BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@return String
     */
    protected String createQueryConditionList(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strStatusDiv,
        String l_strApplyHourFrom,
        String l_strApplyHourTo,
        String l_strAgreementDiv,
        String l_strFxSystemCode)
    {
        final String STR_METHOD_NAME =
            "createQueryConditionList(String, String[], String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //空の検索条件文字列(：String)を生成する
        StringBuffer l_sbSql = new StringBuffer();

        //証券会社条件を検索条件に追加する。
        //　@検索条件文字列 += " institution_code = ? "
        l_sbSql.append(" institution_code = ? ");

        //部店条件を検索条件に追加する。
        //　@パラメータ.部店コード[]の要素数分"?"を追加する。
        //　@検索条件文字列 += "and branch_code in (?,?,,,) "
        l_sbSql.append(" and branch_code in (? ");
        for (int i = 1; i < l_strBranchCodes.length; i++)
        {
            l_sbSql.append(", ? ");
        }
        l_sbSql.append(" ) ");

        //パラメータ.ステータス区分 != nullの場合、
        //　@口座開設状況区分を検索条件に追加する。
        //　@検索条件文字列 += "and account_open_status_div = ? "
        if (l_strStatusDiv != null)
        {
            l_sbSql.append(" and account_open_status_div = ? ");
        }

        //パラメータ.申込日（自） != nullの場合、
        //　@申込日の下限を検索条件に追加する。
        //　@検索条件文字列 += "and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? "
        if (l_strApplyHourFrom != null)
        {
            l_sbSql.append(" and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? ");
        }

        //パラメータ.申込日（至） != nullの場合、
        //　@申込日の下限を検索条件に追加する。
        //　@検索条件文字列 += "and to_char(created_timestamp, 'YYYYMMDDHH24') < ? "
        if (l_strApplyHourTo != null)
        {
            l_sbSql.append(" and to_char(created_timestamp, 'YYYYMMDDHH24') < ? ");
        }

        //パラメータ.約諾書区分 != nullの場合、
        //　@約諾書区分を検索条件に追加する。
        //　@検索条件文字列 += "and agreement_div = ? "
        if (l_strAgreementDiv != null)
        {
            l_sbSql.append(" and agreement_div = ? ");
        }

        //パラメータ.FXシステムコード != nullの場合、
        //　@FXシステムコードを検索条件に追加する。
        //　@検索条件文字列 += "and fx_system_code = ? "
        if (l_strFxSystemCode != null)
        {
            l_sbSql.append(" and fx_system_code = ? ");
        }

        //作成した検索条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbSql.toString();
    }

    /**
     * (create取得条件データコンテナ)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）証券会社条件を追加する。<BR>
     * 　@パラメータ.証券会社コードをArrayListに追加する。<BR>
     * <BR>
     * ３）部店条件を追加する。<BR>
     * 　@パラメータ.部店コード[]の全要素をArrayListに追加する。<BR>
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
     * ７）パラメータ.約諾書区分 != nullの場合、<BR>
     * 　@パラメータ.約諾書区分をArrayListに追加する。<BR>
     * <BR>
     * ８）パラメータ.FXシステムコード != nullの場合、<BR>
     * 　@パラメータ.FXシステムコードをArrayListに追加する。<BR>
     * <BR>
     * ９）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コードの配列<BR>
     * @@param l_strStatusDiv - (ステータス区分)<BR>
     * ステータス区分<BR>
     * <BR>
     * 0：口座開設中<BR>
     * 1：口座開設完了<BR>
     * 2：口座開設エラー<BR>
     * 9：削除<BR>
     * <BR>
     * @@param l_strApplyHourFrom - (申込日（自）)<BR>
     * 申込日（自）<BR>
     * (YYYYMMDDhh)<BR>
     * @@param l_strApplyHourTo - (申込日（至）)<BR>
     * 申込日（至）<BR>
     * (YYYYMMDDhh)<BR>
     * @@param l_strAgreementDiv - (約諾書区分)<BR>
     * 約諾書区分<BR>
     * <BR>
     * 0：無し<BR>
     * 1：有り<BR>
     * null：全て<BR>
     * <BR>
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード<BR>
     * @@return String[]
     */
    protected String[] createQueryDataContainer(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strStatusDiv,
        String l_strApplyHourFrom,
        String l_strApplyHourTo,
        String l_strAgreementDiv,
        String l_strFxSystemCode)
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(String, String[], String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //ArrayListを生成する
        List l_lisValues = new ArrayList();

        //証券会社条件を追加する。
        //　@パラメータ.証券会社コードをArrayListに追加する。
        l_lisValues.add(l_strInstitutionCode);

        //３）部店条件を追加する。
        //　@パラメータ.部店コード[]の全要素をArrayListに追加する。
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisValues.add(l_strBranchCodes[i]);
        }

        //パラメータ.ステータス区分 != nullの場合、
        //　@パラメータ.ステータス区分をArrayListに追加する。
        if (l_strStatusDiv != null)
        {
            l_lisValues.add(l_strStatusDiv);
        }

        //パラメータ.申込日（自） != nullの場合、
        //　@パラメータ.申込日（自）をArrayListに追加する。
        if (l_strApplyHourFrom != null)
        {
            l_lisValues.add(l_strApplyHourFrom);
        }

        //パラメータ.申込日（至） != nullの場合、
        //　@パラメータ.申込日（至）をArrayListに追加する。
        if (l_strApplyHourTo != null)
        {
            l_lisValues.add(l_strApplyHourTo);
        }

        //パラメータ.約諾書区分 != nullの場合、
        //　@パラメータ.約諾書区分をArrayListに追加する。
        if (l_strAgreementDiv != null)
        {
            l_lisValues.add(l_strAgreementDiv);
        }

        //パラメータ.FXシステムコード != nullの場合、
        //　@パラメータ.FXシステムコードをArrayListに追加する。
        if (l_strFxSystemCode != null)
        {
            l_lisValues.add(l_strFxSystemCode);
        }

        //生成したArrayList.toArray()の戻り値を返却する
        String[] l_strSqlValues = new String[l_lisValues.size()];
        l_lisValues.toArray(l_strSqlValues);

        log.exiting(STR_METHOD_NAME);
        return l_strSqlValues;
    }
}@
