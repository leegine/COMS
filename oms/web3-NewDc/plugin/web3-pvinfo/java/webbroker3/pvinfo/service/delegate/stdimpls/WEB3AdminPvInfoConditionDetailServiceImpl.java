head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionDetailServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者表示設定詳細サービスImpl(WEB3AdminPvInfoConditionDetailServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/27 李弘毅(中訊) 作成
Revesion History : 2006/5/22 凌建平(中訊) 仕様変更No.063修正
Revesion History : 2008/12/02 SCS森 仕様変更No.113修正
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
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailResponse;
import webbroker3.pvinfo.message.WEB3PvInfoAccountInformationUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDetailService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者表示設定詳細サービスImpl)<BR>
 * 管理者表示設定詳細サービス実装クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionDetailServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoConditionDetailService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionDetailServiceImpl.class);

    /**
     * 表示設定詳細画面表示処理を行う。<BR>
     * <BR>
     * this.get表示設定詳細画面()メソッドを<BR>
     * コールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415CB9920364
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminPvInfoConditionDetailRequest)
        {
            l_response = this.getConditionDetailScreen((WEB3AdminPvInfoConditionDetailRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = "パラメータの類型が不正、該当するWEB3AdminPvInfoConditionDetailRequest類型。";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get表示設定詳細画面)<BR>
     * 表示設定詳細画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者表示設定詳細サービス)get表示設定詳細画面」参照<BR>
     * ========================================================<BR>
     * get表示内容Params(表示内容ID：　@long)<BR>
     * <BR>
     * nullが返却された場合は、<BR>
     * 「該当データ未存在」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定詳細リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailResponse
     * @@roseuid 415CB9B30018
     */
    protected WEB3AdminPvInfoConditionDetailResponse getConditionDetailScreen(WEB3AdminPvInfoConditionDetailRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getConditionDetailScreen(WEB3AdminPvInfoConditionDetailRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1 validate()
        l_request.validate();

        //2 getInstanceFromログイン情報()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //3 validate権限(機@能コード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, false);

        //4 get表示内容Params(long)
        long l_lngDisplayContentsId = Long.parseLong(l_request.displayContentsId);
        DisplayContentsParams l_dispContentsParams = l_dataManager.getDisplayContentsParams(l_lngDisplayContentsId);

        if (l_dispContentsParams == null)
        {
            log.error("データ未存在する");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5 表示内容情報オブジェクトを生成する。
        WEB3PvInfoDisplayContentsUnit l_dispContentsUnits = new WEB3PvInfoDisplayContentsUnit();

        //6 (*)プロパティセット
        l_dispContentsUnits.displayContentsId = WEB3StringTypeUtility.formatNumber(l_dispContentsParams.display_contents_id);
        log.debug("表示内容情報.displayContentsId" + "=" + l_dispContentsUnits.displayContentsId);
        l_dispContentsUnits.conditionNumber = l_dispContentsParams.condition_no;
        log.debug("表示内容情報.conditionNumber" + "=" + l_dispContentsUnits.conditionNumber);
        l_dispContentsUnits.priorityDiv = l_dispContentsParams.priority_div;
        log.debug("表示内容情報.priorityDiv" + "=" + l_dispContentsUnits.priorityDiv);
        l_dispContentsUnits.listStartDate = WEB3DateUtility.formatDate(l_dispContentsParams.term_from, "yyyyMMddHHmm");
        log.debug("表示内容情報.listStartDate" + "=" + l_dispContentsUnits.listStartDate);
        l_dispContentsUnits.listEndDate = WEB3DateUtility.formatDate(l_dispContentsParams.term_to, "yyyyMMddHHmm");
        log.debug("表示内容情報.listEndDate" + "=" + l_dispContentsUnits.listEndDate);
        l_dispContentsUnits.displayTitle = l_dispContentsParams.display_title;
        log.debug("表示内容情報.displayTitle" + "=" + l_dispContentsUnits.displayTitle);
        l_dispContentsUnits.displayMessage = l_dispContentsParams.display_message;
        log.debug("表示内容情報.displayMessage" + "=" + l_dispContentsUnits.displayMessage);
        l_dispContentsUnits.displayColor = l_dispContentsParams.display_color;
        log.debug("表示内容情報.displayColor" + "=" + l_dispContentsUnits.displayColor);

        if (WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_dispContentsParams.blink_display_flag))
        {
            l_dispContentsUnits.blinkDisplayFlag = true;
        }
        else
        {
            l_dispContentsUnits.blinkDisplayFlag = false;
        }
        log.debug("表示内容情報.blinkDisplayFlag" + "=" + l_dispContentsUnits.blinkDisplayFlag);

        l_dispContentsUnits.displayUrl = l_dispContentsParams.display_url;
        log.debug("表示内容情報.displayUrl" + "=" + l_dispContentsUnits.displayUrl);

        if (WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_dispContentsParams.last_update_time_display_flag))
        {
            l_dispContentsUnits.lastUpdateTimeDisplayFlag = false;
        }
        else
        {
            l_dispContentsUnits.lastUpdateTimeDisplayFlag = true;
        }
        log.debug("表示内容情報.lastUpdateTimeDisplayFlag" + "=" + l_dispContentsUnits.lastUpdateTimeDisplayFlag);

        if (WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_dispContentsParams.effective_flag))
        {
            l_dispContentsUnits.effectiveFlag = false;
        }
        else
        {
            l_dispContentsUnits.effectiveFlag = true;
        }
        log.debug("表示内容情報.effectiveFlag" + "=" + l_dispContentsUnits.effectiveFlag);

        l_dispContentsUnits.displayDevice = l_dispContentsParams.display_device;
        log.debug("表示内容情報.displayDevice" + "=" + l_dispContentsUnits.displayDevice);
        l_dispContentsUnits.lastUpdateMember = l_dispContentsParams.last_update_member;
        log.debug("表示内容情報.lastUpdateMember" + "=" + l_dispContentsUnits.lastUpdateMember);
        l_dispContentsUnits.lastUpdatedTimestamp = l_dispContentsParams.last_updated_timestamp;
        log.debug("表示内容情報.lastUpdatedTimestamp" + "=" + l_dispContentsUnits.lastUpdatedTimestamp);

        //7 分岐フロー
		//  get表示内容Params()の戻り値.表示条件番号が
		//     以下のいづれかに該当する場合、処理を実施する。
		//     　@・"1001：入金請求発生&信用口座開設"
		//     　@・"1002：入金請求発生&信用口座未開設"
		//     　@・"1003：立替金発生"
		//     　@・"1005：証拠金不足"
		//     　@・"1007：　@決済期限間近（一週間前）の建玉保有"
		//     　@・"1041：　@20％割れ1日＆30％割れ5日以下"
		//     　@・"1042：　@20％割れ1日＆30％割れ6日"
		//     　@・"1043：　@20％割れ2日＆30％割れ6日以下"
		//     　@・"1044：　@20％割れ3日以上"
		//     　@・"1045：　@30％割れ2～4日"
		//     　@・"1046：　@30％割れ5日"
		//     　@・"1047：　@30％割れ6日"
		//     　@・"1048：　@30％割れ7日以上"
		//     　@・"1054：　@不足金発生＆信用口座未開設"
		//     　@・"1055：　@不足金発生＆信用口座開設"
		//     　@・"1056：　@第一水準追証発生"
		//     　@・"1057：　@第二水準追証発生"
        String l_strConditionNo = l_dispContentsParams.condition_no;
        WEB3PvInfoAccountInformationUnit[] l_nonReadAccountInformationUnits = null;
        WEB3PvInfoAccountInformationUnit[] l_readAccountInformationUnits = null;
        if (WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_3DAY_OVER.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_2TO4DAY.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_5DAY.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_6DAY.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_7DAY_OVER.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_OPEN.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.FIRST_ADDITIONAL_DEPOSIT_OCCUR.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.SECOND_ADDITIONAL_DEPOSIT_OCCUR.equals(l_strConditionNo))
        {
            //7.1 create対象顧客情報一覧(表示内容Params, boolean)
            l_readAccountInformationUnits = this.createTargetAccountInfoList(l_dispContentsParams, true);
        }

        //8 分岐フロー
        if (WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_strConditionNo))
        {
            //8.1 create対象顧客情報一覧(表示内容Params, boolean)
            l_nonReadAccountInformationUnits = this.createTargetAccountInfoList(l_dispContentsParams, false);

            //8.2 create対象顧客情報一覧(表示内容Params, boolean)
            l_readAccountInformationUnits = this.createTargetAccountInfoList(l_dispContentsParams, true);
        }

        //9 createResponse()
        WEB3AdminPvInfoConditionDetailResponse l_response = (WEB3AdminPvInfoConditionDetailResponse)l_request.createResponse();

        //10 プロパティセット
        l_response.displayContentsUnit = l_dispContentsUnits;
        if (l_readAccountInformationUnits != null)
        {
            l_response.readAccountUnits = l_readAccountInformationUnits;
        }
        else
        {
            l_response.readAccountUnits = null;
        }

        if(l_nonReadAccountInformationUnits != null)
        {
            l_response.nonReadAccountUnits = l_nonReadAccountInformationUnits;
        }
        else
        {
            l_response.nonReadAccountUnits = null;
        }

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
     * 　@　@表示内容ID = パラメータ.表示内容Params.表示内容ID　@かつ<BR>
     * 　@　@未読既読フラグ = (パラメータ.is既読顧客の値による)<BR>
     * <BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = " institution_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "display_contents_id = ? and "<BR>
     * １－１－１）作成した検索条件文字列に未読／既読の条件を追加する。 <BR>
     *     [パラメータ.is既読顧客 == falseの場合] <BR>
     *        検索条件文字列 += "last_read_timestamp is null" <BR>
     *      [上記以外の場合]<BR>
     *          検索条件文字列 += "last_read_timestamp is not null"  <BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。<BR>
     * 　@　@※文字列変換してセットすること。<BR>
     * 　@　@　@・パラメータ.表示内容Params.証券会社コード<BR>
     * 　@　@　@・パラメータ.表示内容Params.表示内容ID<BR>
     * <BR>
     * ２）ソート条件を作成する。<BR>
     * 　@[パラメータ.is既読顧客 == falseの場合]<BR>
     * 　@　@○部店 昇順、顧客 昇順<BR>
     * 　@　@ソート条件 = " branch_code asc, account_code asc"<BR>
     * <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@○部店 昇順、最終閲覧日時 昇順<BR>
     * 　@　@ソート条件 = " branch_code asc, last_updated_timestamp asc"<BR>
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
     * 　@　@　@　@対象顧客情報.最終閲覧日時 = 閲覧履歴Params.更新日付<BR>
     * 　@５－３）生成したArrayListに追加する。<BR>
     * <BR>
     * ６）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_displayContentsParams - (表示内容Params)<BR>
     * 表示内容Params<BR>
     * @@param l_isReadAccount - (is既読顧客)<BR>
     * 取得対象が既読顧客かどうかのフラグ<BR>
     * <BR>
     * false：　@未読顧客<BR>
     * true：　@既読顧客<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoAccountInformationUnit[]
     * @@roseuid 415CBE520354
     */
    protected WEB3PvInfoAccountInformationUnit[] createTargetAccountInfoList(DisplayContentsParams l_displayContentsParams, boolean l_isReadAccount) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createTargetAccountInfoList(DisplayContentsParams, boolean)";
        log.entering(STR_METHOD_NAME);

        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        //１）閲覧履歴テーブルを検索する条件の作成
        //１－１）上記検索条件を基に、検索条件文字列を作成する。
        String l_strQueryString = " institution_code = ? and "
                                    + "display_contents_id = ? and ";

        //１－１－１）作成した検索条件文字列に未読／既読の条件を追加する。
        //[パラメータ.is既読顧客 == falseの場合]  
        if (l_isReadAccount == false)
        {
            l_strQueryString += "last_read_timestamp is null";
        }
        else
        {
            l_strQueryString += "last_read_timestamp is not null";
        }
        //１－２）"?"にセットするためのパラメータセットを作成する。
        List l_lisArrayList = new ArrayList();
        l_lisArrayList.add(l_displayContentsParams.institution_code);
        l_lisArrayList.add(WEB3StringTypeUtility.formatNumber(l_displayContentsParams.display_contents_id));
        String[] l_strArrayLists = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strArrayLists);

        //２）ソート条件を作成する。
        String l_strSortString = null;
        if (!l_isReadAccount)
        {
            l_strSortString = " branch_code asc, account_code asc";
        }
        else
        {
            l_strSortString = " branch_code asc, last_updated_timestamp asc";
        }
        log.debug("l_strSortString" + "=" + l_strSortString);

        //３）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.get閲覧履歴Params一覧()メソッドをコールする。
        List l_lisBrowseHistoryParams = l_dataManager.getBrowseHistoryParamsList(l_strQueryString, l_strArrayLists, l_strSortString);
        if (l_lisBrowseHistoryParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //４）ArrayListを作成する。
        List l_lisAccountInformation = new ArrayList();

        //５）３）のメソッドの戻り値の要素(=閲覧履歴Params)数分、以下の処理を繰り返す。
        int l_intParamSize = l_lisBrowseHistoryParams.size();
        for (int i = 0; i < l_intParamSize; i++)
        {
            BrowseHistoryParams l_browseHistoryParams = (BrowseHistoryParams)l_lisBrowseHistoryParams.get(i);
            if (l_browseHistoryParams != null)
            {
                //５－１）対象顧客情報オブジェクトを生成する。
                WEB3PvInfoAccountInformationUnit l_accountInformationUnit = new WEB3PvInfoAccountInformationUnit();

                //５－２）対象顧客情報に以下のプロパティをセットする。
                l_accountInformationUnit.accountCode = l_browseHistoryParams.account_code;
                log.debug("対象顧客情報.accountCode" + "=" + l_accountInformationUnit.accountCode);
                l_accountInformationUnit.branchCode = l_browseHistoryParams.branch_code;
                log.debug("対象顧客情報.branchCode" + "=" + l_accountInformationUnit.branchCode);
                l_accountInformationUnit.lastBrowseDate = l_browseHistoryParams.last_read_timestamp;
                log.debug("対象顧客情報.lastBrowseDate" + "=" + l_accountInformationUnit.lastBrowseDate);

                //５－３）生成したArrayListに追加する。
                l_lisAccountInformation.add(l_accountInformationUnit);
            }
        }

        //６）生成したArrayList.toArray()の戻り値を返却する。
        WEB3PvInfoAccountInformationUnit[] l_accountInformationUnit = new WEB3PvInfoAccountInformationUnit[l_lisAccountInformation.size()];
        l_lisAccountInformation.toArray(l_accountInformationUnit);

        log.exiting(STR_METHOD_NAME);
        return l_accountInformationUnit;
    }
}
@
