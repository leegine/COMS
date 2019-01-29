head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionDelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者表示設定削除サービスImpl(WEB3AdminPvInfoConditionDelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/28 李丁銀(中訊) 作成
Revesion History : 2004/11/2  魏馨(中訊) 修正
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.data.BrowseHistoryParams;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmResponse;
import webbroker3.pvinfo.message.WEB3PvInfoAccountInformationUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDelService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者表示設定削除サービスImpl)<BR>
 * 管理者表示設定削除サービス実装クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionDelServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoConditionDelService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionDelServiceImpl.class);

    /**
     * 管理者表示設定削除処理を行う。<BR>
     * <BR>
     * 引数の型により、以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・表示設定削除確認リクエストの場合<BR>
     * 　@this.validate表示設定削除()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・表示設定削除完了リクエストの場合<BR>
     * 　@this.submit表示設定削除()メソッドをコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415D2A6D01F4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3AdminPvInfoConditionDelConfirmRequest)
        {
            l_response = this.validateConditionDel((WEB3AdminPvInfoConditionDelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoConditionDelCompleteRequest)
        {
            l_response = this.submitConditionDel((WEB3AdminPvInfoConditionDelCompleteRequest)l_request);
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
     * (validate表示設定削除)<BR>
     * 表示設定削除確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者表示設定削除サービス)validate表示設定削除」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定削除確認リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmResponse
     * @@roseuid 415D2AA800BC
     */
    protected WEB3AdminPvInfoConditionDelConfirmResponse validateConditionDel(WEB3AdminPvInfoConditionDelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateConditionDel(WEB3AdminPvInfoConditionDelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionDelConfirmResponse l_response = null;

        //1.1.validate()
        l_request.validate();
        log.debug("validate() を執行します");
        
        //1.2.getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報() を執行します");

        //1.3.validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限() を執行します");

        //1.4.get表示内容Params(long)
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        long l_lngDisplayContentsId = Long.parseLong(l_request.displayContentsId);
        DisplayContentsParams l_displayContentsParams = l_dataMgr.getDisplayContentsParams(l_lngDisplayContentsId);
        log.debug("get表示内容Params() を執行します");

        if(l_displayContentsParams == null)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01037.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.5.表示内容情報()
        WEB3PvInfoDisplayContentsUnit l_displayContentsUnit = new WEB3PvInfoDisplayContentsUnit();
        log.debug("表示内容情報() を執行します");
        
        //1.6. (*)プロパティセット
        l_displayContentsUnit.displayContentsId =
            WEB3StringTypeUtility.formatNumber(l_displayContentsParams.display_contents_id);
        log.debug("表示内容ID = " + l_displayContentsUnit.displayContentsId);
        
        l_displayContentsUnit.conditionNumber = l_displayContentsParams.condition_no;
        log.debug("表示条件番号 = " + l_displayContentsUnit.conditionNumber);
        
        l_displayContentsUnit.priorityDiv = l_displayContentsParams.priority_div;
        log.debug("優先区分 = " + l_displayContentsUnit.priorityDiv);
             
        l_displayContentsUnit.listStartDate = WEB3DateUtility.formatDate(l_displayContentsParams.term_from, "yyyyMMddHHmm");
        log.debug("表示期間From = " + l_displayContentsUnit.listStartDate);
        
        l_displayContentsUnit.listEndDate = WEB3DateUtility.formatDate(l_displayContentsParams.term_to, "yyyyMMddHHmm");
        log.debug("表示期間To = " + l_displayContentsUnit.listEndDate);
        
        l_displayContentsUnit.displayTitle = l_displayContentsParams.display_title;
        log.debug("表示タイトル = " + l_displayContentsUnit.displayTitle);
        
        l_displayContentsUnit.displayMessage = l_displayContentsParams.display_message;
        log.debug("表示文章 = " + l_displayContentsUnit.displayMessage);
        
        l_displayContentsUnit.displayColor = l_displayContentsParams.display_color;
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
        l_displayContentsUnit.displayUrl = l_displayContentsParams.display_url;
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
        
        l_displayContentsUnit.lastUpdateMember = l_displayContentsParams.last_update_member;
        log.debug("最終更新者 = " + l_displayContentsUnit.lastUpdateMember);
        
        l_displayContentsUnit.lastUpdatedTimestamp = l_displayContentsParams.last_updated_timestamp;
        log.debug("最終更新日時 = " + l_displayContentsUnit.lastUpdatedTimestamp);

        //1.7.(*)分岐フロー
        WEB3PvInfoAccountInformationUnit[] l_accountInformationUnits = null;

        if(WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_displayContentsParams.condition_no))
        {
            //1.7.1.create対象顧客情報一覧(表示内容Params)
            l_accountInformationUnits = this.createTargetAccountInfoList(l_displayContentsParams);
            log.debug("create対象顧客情報一覧() を執行します");            
        }

        //1.8.createResponse()
        l_response = (WEB3AdminPvInfoConditionDelConfirmResponse)l_request.createResponse();
        log.debug("createResponse() を執行します");

        //1.9. (*)プロパティセット
        l_response.displayContentsUnit = l_displayContentsUnit;
        if(l_accountInformationUnits == null || !WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_displayContentsParams.condition_no))
        {
            l_response.accountInformationUnits = null;
        }
        else
        {
            l_response.accountInformationUnits = l_accountInformationUnits;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit表示設定削除)<BR>
     * 表示設定削除完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者表示設定削除サービス)submit表示設定削除」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定削除完了リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteResponse
     * @@roseuid 415D2AA800DB
     */
    protected WEB3AdminPvInfoConditionDelCompleteResponse submitConditionDel(WEB3AdminPvInfoConditionDelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitConditionDel(WEB3AdminPvInfoConditionDelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionDelCompleteResponse l_response = null;

        //1.1.validate()
        l_request.validate();
        log.debug("validate() を執行します");
        
        //1.2.getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報() を執行します");

        //1.3validate権限(機@能コード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限() を執行します");

        //1.4.validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        log.debug("validate取引パスワード() を執行します");
        
        //1.5.delete閲覧履歴(long)
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        log.debug("delete閲覧履歴() を執行します");        
        long l_lngDisplayContentsID = Long.parseLong(l_request.displayContentsId);
        l_dataMgr.deleteBrowseHistory(l_lngDisplayContentsID);

        //1.6.delete表示内容(long)
        l_dataMgr.deleteDisplayContents(l_lngDisplayContentsID);
        log.debug("delete表示内容() を執行します");        

        //1.7.createResponse()
        l_response = (WEB3AdminPvInfoConditionDelCompleteResponse)l_request.createResponse();
        log.debug("createResponse() を執行します");

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create対象顧客情報一覧)<BR>
     * 対象顧客情報の一覧を作成し、返却する。<BR>
     * <BR>
     * １）閲覧履歴テーブルを検索する条件の作成<BR>
     * 　@(検索条件文字列&検索条件データコンテナ)<BR>
     * <BR>
     * 　@以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@証券会社コード = パラメータ.表示内容Params.証券会社コード　@かつ<BR>
     * 　@　@表示内容ID = パラメータ.表示内容Params.表示内容ID<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = " institution_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "display_contents_id = ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。<BR>
     * 　@　@　@・パラメータ.表示内容Params.証券会社コード<BR>
     * 　@　@　@・パラメータ.表示内容Params.表示内容ID<BR>
     * <BR>
     * ２）ソート条件を作成する。<BR>
     * 　@　@○部店 昇順、顧客 昇順<BR>
     * 　@　@ソート条件 = " branch_code asc, account_code asc"<BR>
     * <BR>
     * ３）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.get閲覧履歴Params一覧()<BR>
     * 　@メソッドをコールする。<BR>
     * <BR>
     * 　@[get閲覧履歴Params一覧()にセットするパラメータ]<BR>
     * 　@　@検索条件文字列：　@作成した検索条件文字列<BR>
     * 　@　@検索条件データコンテナ：　@作成したArrayList.toArray()<BR>
     * 　@　@ソート条件：　@作成したソート条件<BR>
     * <BR>
     * 　@メソッドの戻り値 == nullの場合は、nullを返却する。<BR>
     * <BR>
     * ４）ArrayListを作成する。<BR>
     * <BR>
     * ５）３）のメソッドの戻り値の要素(=閲覧履歴Params)数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@５－１）対象顧客情報オブジェクトを生成する。<BR>
     * 　@５－２）対象顧客情報に以下のプロパティをセットする。<BR>
     * 　@　@　@　@対象顧客情報.部店コード = 閲覧履歴Params.部店コード<BR>
     * 　@　@　@　@対象顧客情報.顧客コード = 閲覧履歴Params.顧客コード<BR>
     * 　@　@　@　@対象顧客情報.最終閲覧日時 = 閲覧履歴Params.最終閲覧日時 <BR>
     * 　@５－３）生成したArrayListに追加する。<BR>
     * <BR>
     * ６）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_displayContentsParams - (表示内容Params)<BR>
     * 表示内容Params<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoAccountInformationUnit[]
     * @@roseuid 4160FFA00338
     */
    protected WEB3PvInfoAccountInformationUnit[] createTargetAccountInfoList(DisplayContentsParams l_displayContentsParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createTargetAccountInfoList(DisplayContentsParams)";
        log.entering(STR_METHOD_NAME);

        //１）閲覧履歴テーブルを検索する条件の作成
        //１－１）上記検索条件を基に、検索条件文字列を作成する。
        String l_strQuery = " institution_code = ? and display_contents_id = ? " ;

        //１－２）"?"にセットするためのパラメータセットを作成する。
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(l_displayContentsParams.institution_code);
        log.debug("証券会社コード = " + l_displayContentsParams.institution_code);
        
        l_lisQueryVars.add(WEB3StringTypeUtility.formatNumber(l_displayContentsParams.display_contents_id));
        log.debug("表示内容ID = " + l_displayContentsParams.display_contents_id);

        //２）ソート条件を作成する。
        String l_strSort = " branch_code asc, account_code asc";

        //３）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.get閲覧履歴Params一覧()
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        String[] l_strArryQueryVars = new String[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_strArryQueryVars);
        List l_lisBrowseHistoryParams = l_dataMgr.getBrowseHistoryParamsList(l_strQuery, l_strArryQueryVars, l_strSort);
        log.debug("get閲覧履歴Params一覧() を執行します");
                
        if(l_lisBrowseHistoryParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //４）ArrayListを作成する。
        List l_lisAccountInformationUnit = new ArrayList();

        //５）３）のメソッドの戻り値の要素(=閲覧履歴Params)数分、
        int l_intLisBrowseHistoryParamsCnt = l_lisBrowseHistoryParams.size();
        for(int i = 0; i < l_intLisBrowseHistoryParamsCnt; i++)
        {
            //５－１）対象顧客情報オブジェクトを生成する。
            WEB3PvInfoAccountInformationUnit l_accountInformationUnit = new WEB3PvInfoAccountInformationUnit();

            //５－２）対象顧客情報に以下のプロパティをセットする。
            BrowseHistoryParams l_bhParams = (BrowseHistoryParams)l_lisBrowseHistoryParams.get(i);
            
            l_accountInformationUnit.branchCode  = l_bhParams.getBranchCode();
            log.debug("部店コード = " + l_accountInformationUnit.branchCode);
            
            l_accountInformationUnit.accountCode = l_bhParams.getAccountCode();
            log.debug("顧客コード = " + l_accountInformationUnit.accountCode);
            
            l_accountInformationUnit.lastBrowseDate = l_bhParams.getLastReadTimestamp();
            log.debug("最終閲覧日時 = " + l_accountInformationUnit.lastBrowseDate);

            //５－３）生成したArrayListに追加する。
            l_lisAccountInformationUnit.add(l_accountInformationUnit);
        }

        //６生成したArrayList.toArray()の戻り値を返却する。
        WEB3PvInfoAccountInformationUnit[] l_arryAccountInformationUnit = new WEB3PvInfoAccountInformationUnit[l_lisAccountInformationUnit.size()];
        l_lisAccountInformationUnit.toArray(l_arryAccountInformationUnit);

        log.exiting(STR_METHOD_NAME);
        return l_arryAccountInformationUnit;
    }
}
@
