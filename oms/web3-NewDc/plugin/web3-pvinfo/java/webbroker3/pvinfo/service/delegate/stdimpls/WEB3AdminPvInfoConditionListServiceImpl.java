head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者表示設定一覧サービスImpl(WEB3AdminPvInfoConditionListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/27 李丁銀(中訊) 作成
Revesion History : 2004/11/2  魏馨(中訊) 修正
Revesion History : 2005/12/8 譚漢江(中訊) 仕様変更No.059修正
Revesion History : 2006/5/22 凌建平(中訊) 仕様変更No.063修正
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.data.DisplayContentsRow;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionListService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者表示設定一覧サービスImpl)<BR>
 * 管理者表示設定一覧サービス実装クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionListServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoConditionListService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionListServiceImpl.class);

    /**
     * 表示設定一覧画面表示処理を行う。<BR>
     * <BR>
     * 引数の型により、以下のメソッドを呼び分ける<BR>
     * <BR>
     * ○管理者・表示設定一覧リクエストの場合<BR>
     * 　@this.get表示設定一覧画面()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・現在状況更新リクエストの場合<BR>
     * 　@this.update現在状況()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415BD381010E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if(l_request instanceof WEB3AdminPvInfoConditionListRequest )
        {
            l_response = this.getConditionListScreen((WEB3AdminPvInfoConditionListRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoConditionUpdateRequest)
        {
            l_response = this.updateCondition((WEB3AdminPvInfoConditionUpdateRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = "パラメータの類型が不正";
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80018.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get表示設定一覧画面)<BR>
     * 表示設定一覧画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者表示設定一覧サービス)get表示設定一覧画面」参照<BR>
     * ===============================================<BR>
     * get表示設定一覧画面/(管理者・表示設定一覧サービス)<BR>get表示設定一覧画面/<BR>
     * 6.create表示条件情報一覧(管理者)<BR>
     * nullが返却された場合は、<BR>
     * 「表示条件設定なし」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01036<BR>
     * ===============================================<BR>
     * ========================================================<BR>
     * 顧客オブジェクトが取得できなかった場合、<BR>
     * 「顧客未存在」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定一覧リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListResponse
     * @@roseuid 415BD43F018B
     */
    protected WEB3AdminPvInfoConditionListResponse getConditionListScreen(WEB3AdminPvInfoConditionListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConditionListScreen(WEB3AdminPvInfoConditionListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionListResponse l_response = null;

        //1.1 validate()
        l_request.validate();
        log.debug("validate() を執行します");
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報() を執行します");
        
        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, false);
        log.debug("validate権限() を執行します");
        
        //1.4 get部店コード()
        String l_strBranchCode = l_admin.getBranchCode();
        log.debug("部店コード() = " + l_strBranchCode);

        //1.5 (*)分岐フロー
        WEB3PvInfoDisplayConditionUnit[] l_dispCondUnits = null;
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        if(l_request.conditionNumber == null)
        {
            //1.5.1 create表示条件情報一覧(管理者)
            l_dispCondUnits = l_dataMgr.createDisplayConditionList(l_admin);

            if(l_dispCondUnits == null)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01036.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01036,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //1.6 create検索条件文字列(String)
        String l_strQueryString = this.createQueryString(l_request.conditionNumber);
        log.debug("検索条件文字列() = " + l_strQueryString);

        //1.7  create検索条件データコンテナ(管理者, String)
        String[] l_strQueryDataContainers = this.createQueryDataContainer(l_admin, l_request.conditionNumber);
        log.debug("表示条件番号 = " + l_request.conditionNumber);

        //1.8 get表示内容Params一覧(String, String[], String)
        String l_strSortKeys = " effective_flag asc," + " last_updated_timestamp desc," + " term_to desc";
        List l_lisDisplayContentsParamsListTemp = l_dataMgr.getDisplayContentsParamsList(l_strQueryString, l_strQueryDataContainers, l_strSortKeys);

        //1.9get表示内容Params一覧()の戻り値 == null処理
        if(l_lisDisplayContentsParamsListTemp == null)
        {
            //1.9.1createResponse()
            l_response = (WEB3AdminPvInfoConditionListResponse)l_request.createResponse();
            //1.9.2 プロパティセット

            l_response.displayConditionUnits = l_dispCondUnits;
            l_response.displayContentsUnits = null;
            l_response.totalPages = "1";
            l_response.totalRecords = "0";
            l_response.pageIndex = "1";

            //1.9.3
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        List l_lisDisplayContentsParamsList = new ArrayList();

        int l_intLenTemp = l_lisDisplayContentsParamsListTemp.size();
        for(int i=0; i<l_intLenTemp; i++)
        {
            l_lisDisplayContentsParamsList.add(l_lisDisplayContentsParamsListTemp.get(i));
        }

        //1.10 (*)分岐フロー
        if(l_request.accountCode != null && l_request.branchCodeList != null)
        {
            //1.10.1 get顧客(String, String)
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_gentradeMainAccount = null;
            for (int i = 0; i < l_request.branchCodeList.length; i++)
            {
                try
                {
                    l_gentradeMainAccount = l_gentradeAccountMgr.getMainAccount(l_admin.getInstitutionCode(), l_request.branchCodeList[i], l_request.accountCode);
                }
                catch (WEB3SystemLayerException l_wsex)
                {
                    continue;
                }                
                //顧客オブジェクトが取得できた場合は、breakする。
                if (l_gentradeMainAccount != null)
                {
                    break;
                }
            }
            //Loopが終了した段階で顧客オブジェクトが取得できなかった場合、「顧客未存在」の例外をスローする。
            if(l_gentradeMainAccount == null)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.10.2 (*)get表示内容Params一覧()の戻り値の要素数(=表示内容Params)分Loop処理
            int l_intListCnt = l_lisDisplayContentsParamsList.size() - 1;
            List l_lstHistoryParams = new ArrayList();
            for(int i = l_intListCnt; i >= 0; i--)
            {
                DisplayContentsRow l_displayContnetRow = (DisplayContentsRow)l_lisDisplayContentsParamsList.get(i);

                //1.10.2.1  get閲覧履歴Params(顧客, long)
                l_lstHistoryParams = l_dataMgr.getBrowseHistoryParamsList(l_gentradeMainAccount.getInstitution().getInstitutionCode(), l_request.branchCodeList,l_request.accountCode,l_displayContnetRow.getDisplayContentsId());

                //1.10.2.2 (*)結果取得チェック
                if(l_lstHistoryParams == null)
                {
                    l_lisDisplayContentsParamsList.remove(i);
                }

                //1.10.2.3 (*)残件数チェック
                if(l_lisDisplayContentsParamsList.size() == 0)
                {
                    //1.10.2.3.1 createResponse()
                    l_response = (WEB3AdminPvInfoConditionListResponse)l_request.createResponse();
                    
                    //1.10.2.3.2 プロパティセット
                    l_response.displayConditionUnits = l_dispCondUnits;
                    l_response.displayContentsUnits = null;
                    l_response.totalPages = "1";
                    l_response.totalRecords = "0";
                    l_response.pageIndex = "1";

                    log.exiting(STR_METHOD_NAME);
                    return l_response;
                }
            }
        }

        //1.11 WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisDisplayContentsParamsList, l_intPageIndex, l_intPageSize);
        
        //1.12 getArrayReturned(l_classType : Class) 
        DisplayContentsParams[] l_displayContentsParamses = 
            (DisplayContentsParams[]) l_pageIndexInfo.getArrayReturned(DisplayContentsParams.class);
        
        //1.13 ArrayList()
        List l_lisDisplayContentsUnit = new ArrayList();

        //1.14 getArrayReturned()の戻り値の要素数分Loop処理
        int l_intDisplayContentsParamsCnt = l_displayContentsParamses.length;
        for(int i = 0; i < l_intDisplayContentsParamsCnt; i++)
        {
            //1.14.1 表示内容情報()
            WEB3PvInfoDisplayContentsUnit l_displayContentsUnit = new WEB3PvInfoDisplayContentsUnit();

            //1.14.2 (*)プロパティセット
            DisplayContentsParams l_displayContentsParams = (DisplayContentsParams)l_displayContentsParamses[i];
            l_displayContentsUnit.displayContentsId =
                WEB3StringTypeUtility.formatNumber(l_displayContentsParams.display_contents_id);
            log.debug("表示内容ID = " + l_displayContentsUnit.displayContentsId);
    
            l_displayContentsUnit.conditionNumber = l_displayContentsParams.condition_no;
            log.debug("表示条件番号 = " + l_displayContentsUnit.conditionNumber);
            
            l_displayContentsUnit.priorityDiv = null;
            log.debug("優先区分 = " + l_displayContentsUnit.priorityDiv);
            
            l_displayContentsUnit.listStartDate = null;
            log.debug("表示期間From = " + l_displayContentsUnit.listStartDate);
            
            l_displayContentsUnit.listEndDate = null;
            log.debug("表示期間To = " + l_displayContentsUnit.listEndDate);
            
            l_displayContentsUnit.displayTitle = l_displayContentsParams.display_title;
            log.debug("表示タイトル = " + l_displayContentsUnit.displayTitle);
            
            l_displayContentsUnit.displayMessage = null;
            log.debug("表示文章 = " + l_displayContentsUnit.displayMessage);
            
            l_displayContentsUnit.displayColor = null;
            log.debug("表示色 = " + l_displayContentsUnit.displayColor);

            if(WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_displayContentsParams.blink_display_flag))
            {
                l_displayContentsUnit.blinkDisplayFlag = true;
                log.debug("点滅表示フラグ = " + l_displayContentsUnit.blinkDisplayFlag);
            }
            else
            {
                l_displayContentsUnit.blinkDisplayFlag = false;
                log.debug("点滅表示フラグ = " + l_displayContentsUnit.blinkDisplayFlag);
            }
            l_displayContentsUnit.displayUrl = null;
            log.debug("URL指定 = " + l_displayContentsUnit.displayUrl);

            if(WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_displayContentsParams.last_update_time_display_flag))
            {
                l_displayContentsUnit.lastUpdateTimeDisplayFlag = false;
                log.debug("最終更新日時表示フラグ = " + l_displayContentsUnit.lastUpdateTimeDisplayFlag);
            }
            else
            {
                l_displayContentsUnit.lastUpdateTimeDisplayFlag = true;
                log.debug("最終更新日時表示フラグ = " + l_displayContentsUnit.lastUpdateTimeDisplayFlag);
            }

            if(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_displayContentsParams.effective_flag))
            {
                l_displayContentsUnit.effectiveFlag = false;
                log.debug("有効/無効フラグ = " + l_displayContentsUnit.effectiveFlag);
            }
            else
            {
                l_displayContentsUnit.effectiveFlag = true;
                log.debug("有効/無効フラグ = " + l_displayContentsUnit.effectiveFlag);
            }
            l_displayContentsUnit.displayDevice = l_displayContentsParams.display_device;
            log.debug("表示媒体 = " + l_displayContentsUnit.displayDevice);
            
            l_displayContentsUnit.lastUpdateMember = null;
            log.debug("最終更新者 = " + l_displayContentsUnit.lastUpdateMember);
            
            l_displayContentsUnit.lastUpdatedTimestamp = null;
            log.debug("最終更新日時 = " + l_displayContentsUnit.lastUpdatedTimestamp);

            //1.14.3 add(表示内容情報オブジェクト : Object)
            l_lisDisplayContentsUnit.add(l_displayContentsUnit);
        }
        //1.15 toArray()
        WEB3PvInfoDisplayContentsUnit[] l_displayContentsUnit = new WEB3PvInfoDisplayContentsUnit[l_lisDisplayContentsUnit.size()];
        l_lisDisplayContentsUnit.toArray(l_displayContentsUnit);

        //1.16 createResponse()
        l_response = (WEB3AdminPvInfoConditionListResponse)l_request.createResponse();
        log.debug("createResponse() を執行します");

        //1.17 (*)プロパティセット
        if(l_request.conditionNumber != null)
        {
            l_response.displayConditionUnits = null;
        }
        else
        {
            l_response.displayConditionUnits = l_dispCondUnits;
        }
        l_response.displayContentsUnits = l_displayContentsUnit;
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (update現在状況)<BR>
     * 表示設定一覧画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者表示設定一覧サービス)update現在状況」参照<BR>
     * ========================================================<BR>
     * リクエストデータ.有効/無効区分 != <BR>
     * get表示内容Params()の戻り値.有効/無効フラグの場合、<BR>
     * 「有効/無効エラー」の例外をスローする。 <BR>
     *   class: WEB5BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01038<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・現在状況更新リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateResponse
     * @@roseuid 415BEE3B002D
     */
    protected WEB3AdminPvInfoConditionUpdateResponse updateCondition(WEB3AdminPvInfoConditionUpdateRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateCondition(WEB3AdminPvInfoConditionUpdateRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionUpdateResponse l_response = null;

        //1.1.validate()
        l_request.validate();
        log.debug("validate() を執行します");
        
        //1.2. getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報() を執行します");

        //1.3.validate権限(機@能コード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限() を執行します");

        //1.4.get表示内容Params(long)
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        DisplayContentsParams l_displayContentsParams = l_dataMgr.getDisplayContentsParams(Long.parseLong(l_request.displayContentsId));
        log.debug("get表示内容Params() を執行します");

        if(l_displayContentsParams == null)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01037.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.5.(*)有効/無効同期チェック
        if(l_request.effectiveFlag.equals(l_displayContentsParams.effective_flag))
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01038.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01038,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6.clone表示内容Params(表示内容Params)
        DisplayContentsParams l_cloneDisplayContentsParams = l_dataMgr.cloneDisplayContentsParams(l_displayContentsParams);

        //1.7.(*)プロパティセット
        l_cloneDisplayContentsParams.setEffectiveFlag(
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_request.effectiveFlag) ?
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES :
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_NO);
        l_cloneDisplayContentsParams.setLastUpdateMember(l_admin.getAdministratorCode());
        l_cloneDisplayContentsParams.setLastUpdatedTimestamp((Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        //1.8.update表示内容(表示内容Params)
        l_dataMgr.updateDisplayContents(l_cloneDisplayContentsParams);
        log.debug("update表示内容() を執行します");

        //1.9.createResponse()
        l_response = (WEB3AdminPvInfoConditionUpdateResponse)l_request.createResponse();
        log.debug("createResponse() を執行します");

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列を作成する。<BR>
     * 　@[検索条件]<BR>
     * 　@----------------------------------------------<BR>
     * 　@　@証券会社コード = パラメータ.管理者.get証券会社コード()　@かつ<BR>
     * 　@　@表示条件番号 = パラメータ.表示条件番号<BR>
     * 　@----------------------------------------------<BR>
     * 上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = "institution_code = ? "<BR>
     * <BR>
     * ２）パラメータ.表示条件番号 != nullの場合、以下の処理を行う。<BR>
     * 　@２－１）検索条件文字列に以下の条件を追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and condition_no = ?"<BR>
     * <BR>
     * ３）作成した検索条件文字列を返却する。<BR>
     * @@param l_strConditionNumber - (表示条件番号)<BR>
     * 表示条件番号<BR>
     * <BR>
     * 0000：　@ダイレクト指定<BR>
     * 1001：　@入金請求発生&信用口座開設<BR>
     * 1002：　@入金請求発生&信用口座未開設<BR>
     * 1003：　@立替金発生<BR>
     * 1004：　@立替金実績<BR>
     * 1005：　@証拠金不足<BR>
     * 1006：　@決済期限間近（一ヶ月前）の建玉保有<BR>
     * 1007：　@決済期限間近（一週間前）の建玉保有<BR>
     * 1008：　@信用口座開設<BR>
     * 1009：　@信用口座未開設<BR>
     * 1010：　@オプション口座開設<BR>
     * 1011：　@株式保有<BR>
     * 1012：　@信用建玉保有<BR>
     * 1013：　@投信保有<BR>
     * 1014：　@累投保有<BR>
     * 1015：　@オプション建玉保有<BR>
     * 1016：　@ミニ株保有<BR>
     * 1017：　@先物保有<BR>
     * 1018：　@預り金有り&証券残無し<BR>
     * 1019：　@預り金無し&証券残無し<BR>
     * 1020：　@株式・信用注文発生（当日）<BR>
     * 1021：　@株式・信用注文発生（翌日）<BR>
     * 1022：　@株式・信用約定発生<BR>
     * 1023：　@全顧客<BR>
     * 1024：　@メールアドレス未登録<BR>
     * 1025：　@IPO当選<BR>
     * 1026：　@IPO繰上げ当選<BR>
     * 1027：　@取引停止<BR>
     * 1028：　@ログインパスワード変更要<BR>
     * 1029：　@外国証券口座開設<BR>
     * 1030： 外株保有<BR>
     * 1031： 外株注文発生（当日）<BR>
     * 1032： 外株注文発生（翌日）<BR>
     * 1033： 外株約定発生<BR>
     * 1041：　@20％割れ1日＆30％割れ5日以下<BR>
     * 1042：　@20％割れ1日＆30％割れ6日<BR>
     * 1043：　@20％割れ2日＆30％割れ6日以下<BR>
     * 1044：　@20％割れ3日以上<BR>
     * 1045：　@30％割れ2～4日<BR>
     * 1046：　@30％割れ5日<BR>
     * 1047：　@30％割れ6日<BR>
     * 1048：　@30％割れ7日以上<BR>
     * @@return String
     * @@roseuid 415BD71A037F
     */
    protected String createQueryString(String l_strConditionNumber)
    {
        final String STR_METHOD_NAME = " createQueryString(String)";
        log.entering(STR_METHOD_NAME);

        //１）以下の検索条件を表す、検索条件文字列を作成する。
        String l_strCreateQuery = "institution_code = ? ";

        //２）パラメータ.表示条件番号 != nullの場合、以下の処理を行う。
        if(l_strConditionNumber != null)
        {
            //２－１）検索条件文字列に以下の条件を追加する。
            l_strCreateQuery += " and condition_no = ?";
        }

        log.exiting(STR_METHOD_NAME);
        //３）作成した検索条件文字列を返却する。
        return l_strCreateQuery;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件文字列の"&#63"部分にセットするパラメータリスト(文字列配列)を作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）以下の順番でパラメータをArrayListに追加する。<BR>
     * 　@　@・パラメータ.管理者.get証券会社コード()<BR>
     * <BR>
     * ３）パラメータ.表示条件番号 != nullの場合は、<BR>
     * 　@作成したArrayListに以下のパラメータを追加する。<BR>
     * 　@　@・パラメータ.表示条件番号<BR>
     * <BR>
     * ４）作成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者オブジェクト
     * @@param l_strConditionNumber - (表示条件番号)<BR>
     * 表示条件番号<BR>
     * <BR>
     * 0000：　@ダイレクト指定<BR>
     * 1001：　@入金請求発生&信用口座開設<BR>
     * 1002：　@入金請求発生&信用口座未開設<BR>
     * 1003：　@立替金発生<BR>
     * 1004：　@立替金実績<BR>
     * 1005：　@証拠金不足<BR>
     * 1006：　@決済期限間近（一ヶ月前）の建玉保有<BR>
     * 1007：　@決済期限間近（一週間前）の建玉保有<BR>
     * 1008：　@信用口座開設<BR>
     * 1009：　@信用口座未開設<BR>
     * 1010：　@オプション口座開設<BR>
     * 1011：　@株式保有<BR>
     * 1012：　@信用建玉保有<BR>
     * 1013：　@投信保有<BR>
     * 1014：　@累投保有<BR>
     * 1015：　@オプション建玉保有<BR>
     * 1016：　@ミニ株保有<BR>
     * 1017：　@先物保有<BR>
     * 1018：　@預り金有り&証券残無し<BR>
     * 1019：　@預り金無し&証券残無し<BR>
     * 1020：　@株式・信用注文発生（当日）<BR>
     * 1021：　@株式・信用注文発生（翌日）<BR>
     * 1022：　@株式・信用約定発生<BR>
     * 1023：　@全顧客<BR>
     * 1024：　@メールアドレス未登録<BR>
     * 1025：　@IPO当選<BR>
     * 1026：　@IPO繰上げ当選<BR>
     * 1027：　@取引停止<BR>
     * 1028：　@ログインパスワード変更要<BR>
     * 1029：　@外国証券口座開設<BR>
     * 1030： 外株保有<BR>
     * 1031： 外株注文発生（当日）<BR>
     * 1032： 外株注文発生（翌日）<BR>
     * 1033： 外株約定発生<BR>
     * 1041：　@20％割れ1日＆30％割れ5日以下<BR>
     * 1042：　@20％割れ1日＆30％割れ6日<BR>
     * 1043：　@20％割れ2日＆30％割れ6日以下<BR>
     * 1044：　@20％割れ3日以上<BR>
     * 1045：　@30％割れ2～4日<BR>
     * 1046：　@30％割れ5日<BR>
     * 1047：　@30％割れ6日<BR>
     * 1048：　@30％割れ7日以上<BR>
     * @@return String[]
     * @@roseuid 415BD71A039E
     */
    protected String[] createQueryDataContainer(WEB3Administrator l_administrator, String l_strConditionNumber)
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(WEB3Administrator, String)";
        log.entering(STR_METHOD_NAME);

        //１）ArrayListを生成する。
        List l_lisArrayList = new ArrayList();

        //２）以下の順番でパラメータをArrayListに追加する。
        l_lisArrayList.add(l_administrator.getInstitutionCode());
        log.debug("証券会社コード = " + l_administrator.getInstitutionCode());

        //３）パラメータ.表示条件番号 != nullの場合は、作成したArrayListに以下のパラメータを追加する。
        if(l_strConditionNumber != null)
        {
            l_lisArrayList.add(l_strConditionNumber);
            log.debug("表示条件番号 = " + l_strConditionNumber);
        }

        //４）作成したArrayList.toArray()の戻り値を返却する。
        String[] l_strArray = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strArray);

        log.exiting(STR_METHOD_NAME);
        return l_strArray;
    }
}

@
