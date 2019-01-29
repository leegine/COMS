head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : トリガー発行処理サービスImpl(WEB3AdminDirSecTriggerIssueServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/17 柴双紅(中訊) 新規作成 モデルNo.116、No.118、No.119、No.120、No.121、No.123
                                             モデルNo.127、No.129
                    : 2008/07/14 清水 勇希(SCS) MQメッセージ送信処理の修正
                                 ・修正前の送信方法@…WEB3MQGatewayServiceクラスのsend(引数:WEB3MQMessageSpec)
Revision History    : 2008/08/06 劉剣(中訊) モデルNo.137
Revision History    : 2008/08/07 劉剣(中訊) モデルNo.138
*/

package webbroker3.dirsec.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.util.rac.RoundRobinBasedMultiPoolDataSource;
import com.fitechlabs.xtrade.plugin.util.rac.data.MpdsSettingsDao;
import com.fitechlabs.xtrade.plugin.util.rac.data.MpdsSettingsRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3ProductHandlingDivDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.data.SubmitTriggerInfoRow;
import webbroker3.dirsec.define.WEB3AdminDirSecSortKeyItemDef;
import webbroker3.dirsec.define.WEB3AdminDirTableNameDef;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueRecordDetail;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueSortKey;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecTriggerIssueService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mqgateway.manager.message.WEB3MQSendMessageRequest;
import webbroker3.mqgateway.manager.service.delegate.WEB3MQManagementService;
import webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー発行処理サービスImpl)<BR>
 * トリガー発行処理サービスImplクラス。<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueServiceImpl implements WEB3AdminDirSecTriggerIssueService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueServiceImpl.class);

    /**
     * 受付時間区分
     */
    private String tradingTimeType;

    /**
     * @@roseuid 4806E05302F0
     */
    public WEB3AdminDirSecTriggerIssueServiceImpl()
    {

    }

    /**
     * 管理者・トリガー発行処理を開始する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・トリガー発行処理一覧リクエストの場合<BR>
     * 　@this.getトリガー発行処理一覧画面表示()をコールする。<BR>
     * <BR>
     * ○管理者・トリガー発行処理入力リクエストの場合<BR>
     * 　@this.getトリガー発行処理入力画面表示()をコールする。<BR>
     * <BR>
     * ○管理者・トリガー発行処理確認リクエストの場合<BR>
     * 　@ｔhis.validateトリガー発行処理確認画面表示()をコールする。<BR>
     * <BR>
     * ○管理者・トリガー発行処理完了リクエストの場合<BR>
     * 　@this.submitトリガー発行処理完了画面表示()をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C2665A0340
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_resposne;

        //管理者・トリガー発行処理一覧リクエストの場合
        if (l_request instanceof WEB3AdminDirSecTriggerIssueListRequest)
        {
            l_resposne =
                this.getTriggerIssueListScreenDisplay(
                    (WEB3AdminDirSecTriggerIssueListRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecTriggerIssueInputRequest)
        {
            l_resposne =
                this.getTriggerIssueInputScreenDisplay(
                    (WEB3AdminDirSecTriggerIssueInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecTriggerIssueConfirmRequest)
        {
            l_resposne =
                this.validateTriggerIssueConfirmScreenDisplay(
                    (WEB3AdminDirSecTriggerIssueConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecTriggerIssueCompleteRequest)
        {
            l_resposne =
                this.submitTriggerIssueCompleteScreenDisplay(
                    (WEB3AdminDirSecTriggerIssueCompleteRequest)l_request);
        }

        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_resposne;
    }

    /**
     * (getトリガー発行処理一覧画面表示)<BR>
     * トリガー発行処理一覧画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「getトリガー発行処理一覧」参照。<BR>
     * ==================================================<BR>
     * 　@　@具体位置 : DIR管理者以外（isDIR管理者()==false）の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・トリガー発行処理一覧リクエスト。<BR>
     * @@return WEB3AdminDirSecTriggerIssueListResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C27BA400BD
     */
    protected WEB3AdminDirSecTriggerIssueListResponse getTriggerIssueListScreenDisplay(
        WEB3AdminDirSecTriggerIssueListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTriggerIssueListScreenDisplay(" +
            "WEB3AdminDirSecTriggerIssueListRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限チェックをする。
        //[validate権限()に指定する引数]
        //機@能カテゴリコード："Z0101"(システム管理 トリガー発行処理)
        //is更新：false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, false);

        //ログイン管理者が"証券会社管理者"
        //（isDIR管理者()==false）の場合、 例外をスローする。
        if (!l_administrator.isDirAdministrator())
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857 ,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //データ取得条件の文字列を生成する。
        String l_strSearchConditionList =
            this.createQueryString(WEB3AdminDirTableNameDef.SUBMIT_TRIGGER_INFO);

        //データ取得条件のデータコンテナを生成する。
        //[create検索条件データコンテナ()にセットするパラメータ]
        //参照先テーブル名 ： "submit_trigger_info"
        //証券会社コード    ： get証券会社コード() の戻り値
        //リクエストデータ     ： null
        Object[] l_objWheres = this.createQueryDataContainer(
            WEB3AdminDirTableNameDef.SUBMIT_TRIGGER_INFO,
            l_strInstitutionCode,
            null);

        //ソート条件文字列を編集する。
        String l_strSortKey = this.createSortKey(l_request.sortKeys);

        try
        {
            //すべてのクエリに利用できるデフォルトクエリプロセッサを取得。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //「トリガー発行情報テーブル」に対して作成したSQLを実行する処理。
            //[doFindAllQuery()にセットするパラメータ]
            //arg0 ： トリガー発行情報テーブルRowTypeオブジェクト
            //arg1 ： create取得条件文字列()の戻り値
            //arg2 ： createソートキー()の戻り値
            //arg3 ： null
            //arg4 ： create検索条件データコンテナ()の戻り値
            List l_lisTriggerIssueInfos = l_queryProcessor.doFindAllQuery(
                SubmitTriggerInfoRow.TYPE,
                l_strSearchConditionList,
                l_strSortKey,
                null,
                l_objWheres);

            //要求により、リストのページをめくる処理を行いる。
            //[WEB3PageIndexInfo（）に指定する引数]
            //l_list ： QueryProcessor.doFindAllQuery()の戻り値
            //l_intRequestPageIndex ：　@リクエストデータ.表示ページ番号をint型に変換した値
            //l_intRequestPageSize ：　@リクエストデータ.ページ内表示行数をint型に変換した値
            WEB3PageIndexInfo l_lisViewPageIndexInfo =
                new WEB3PageIndexInfo(
                    l_lisTriggerIssueInfos,
                    Integer.parseInt(l_request.pageIndex),
                    Integer.parseInt(l_request.pageSize));

            //明細データ一覧のリストを取得する。
            List l_lisReturnedInfos = l_lisViewPageIndexInfo.getListReturned();

            //トリガー発行情報Paramsより、一覧情報を作成する。
            //[createトリガー発行情報一覧情報（）に指定する引数]
            //トリガー発行情報一覧List ： getListReturned（）で取得した値。
            WEB3AdminDirSecTriggerIssueRecordDetail[] l_adminDirSecTriggerIssueRecordDetails =
            	this.createTriggerIssueInfoList(l_lisReturnedInfos);

            //レスポンスデータを生成する。
            WEB3AdminDirSecTriggerIssueListResponse l_response =
                (WEB3AdminDirSecTriggerIssueListResponse)l_request.createResponse();

            //プロパティセット
            //トリガー発行情報一覧:createトリガー発行情報一覧の戻り値
            l_response.triggerIssueInfo =
                l_adminDirSecTriggerIssueRecordDetails;
            //総ページ数:WEB3StringTypeUtility.formatNumber(getTotalPages()の戻り値)
            l_response.totalPages =
                WEB3StringTypeUtility.formatNumber(l_lisViewPageIndexInfo.getTotalPages());
            //総レコード数:WEB3StringTypeUtility.formatNumber(getTotalRecords()の戻り値)
            l_response.totalRecords =
                WEB3StringTypeUtility.formatNumber(l_lisViewPageIndexInfo.getTotalRecords());
            //表示ページ番号:リクエスト.表示ページ番号
            l_response.pageIndex = l_request.pageIndex;

            return l_response;
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (getトリガー発行処理入力画面表示)<BR>
     * トリガー発行処理入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「getトリガー発行処理入力」参照。<BR>
     * ==================================================<BR>
     * 　@　@具体位置 : DIR管理者以外（isDIR管理者()==false）の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・トリガー発行処理入力リクエスト。<BR>
     * @@return WEB3AdminDirSecTriggerIssueInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C27BC7004F
     */
    protected WEB3AdminDirSecTriggerIssueInputResponse getTriggerIssueInputScreenDisplay(
        WEB3AdminDirSecTriggerIssueInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTriggerIssueInputScreenDisplay(WEB3AdminDirSecTriggerIssueInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        //　@機@能カテゴリコード："Z0101"(システム管理 トリガー発行処理)
        //　@is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
            true);

        //isDIR管理者( )
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //DIR管理者以外（isDIR管理者()==false）の場合、例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857 ,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //createResponse( )
        WEB3AdminDirSecTriggerIssueInputResponse l_response =
            (WEB3AdminDirSecTriggerIssueInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validateトリガー発行処理確認画面表示)<BR>
     * トリガー発行処理確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validateトリガー発行処理確認」参照。<BR>
     * ==================================================<BR>
     * 　@　@具体位置 : DIR管理者以外（isDIR管理者()==false）の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * <BR>
     * ==================================================<BR>
     * 　@　@具体位置 : データコードが存在しなかった場合<BR>
     * 　@　@　@　@　@　@　@　@(doFindAlQuery()の戻り値.size = 0) はエラー文言を返す<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_03070<BR>
     * ==================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・トリガー発行処理確認リクエスト。<BR>
     * @@return WEB3AdminDirSecTriggerIssueConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C27BDD0234
     */
    protected WEB3AdminDirSecTriggerIssueConfirmResponse validateTriggerIssueConfirmScreenDisplay(
        WEB3AdminDirSecTriggerIssueConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTriggerIssueConfirmScreenDisplay(WEB3AdminDirSecTriggerIssueConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //[validate権限()に指定する引数]
        //　@機@能カテゴリコード："Z0101"(システム管理 トリガー発行処理)
        //　@is更新：true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR管理者( )
        //管理者が"DIR管理者"である場合、trueを返却する。
        //管理者が"証券会社管理者"である場合、falseを返却する。
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR管理者以外（isDIR管理者()==false）の場合、例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //トリガー発行情報テーブル再検索処理(String, String, String)
        //[トリガー発行情報テーブル再検索処理()にセットするパラメータ]
        //　@証券会社コード ： get証券会社コード() の戻り値
        //　@データコード 　@： リクエストデータ.トリガー発行情報.データコード
        //　@シェル名称 ： リクエストデータ.トリガー発行情報.シェル名称
        List l_lisReQueryResults = this.triggerIssueInfoTableReQuery(
            l_strInstitutionCode,
            l_request.triggerIssueInfo.dataCode,
            l_request.triggerIssueInfo.shellName);

        List l_lisTradingTimeRows = new ArrayList();
        WEB3AdminDirSecTriggerIssueRecordDetail l_adminDirSecTriggerIssueRecord = null;
        //トリガー発行情報テーブル再検索処理の戻り値.size() != 0 の場合
        //トリガー発行情報テーブル再検索処理の戻り値.size() = 0
        //の場合は、create警告メッセージ() の処理へ移行。
        if (l_lisReQueryResults.size() != 0)
        {
            //createトリガー発行レコード明細(List)
            //[トリガー発行情報テーブル再検索結果取得処理()にセットするパラメータ]
            //再検索結果 ： トリガー発行情報テーブル再検索処理() の戻り値
            l_adminDirSecTriggerIssueRecord = this.createTriggerIssueRecordDetail(l_lisReQueryResults);

            //create検索条件文字列(String)
            //[create検索条件文字列()にセットするパラメータ]
            //テーブル名 ：  "MQ_MESSAGE_ID_MAPPINGS"
            String l_strMqMessageQueryString =
                this.createQueryString(WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS);

            //create検索条件データコンテナ(String, String, トリガー発行レコード詳細)
            //[create検索条件データコンテナ()にセットするパラメータ]
            //　@参照先テーブル名 ： "MQ_MESSAGE_ID_MAPPINGS"
            //　@証券会社コード    ： get証券会社コード() の戻り値
            //　@検索情報           ： リクエストデータ.トリガー発行情報
            Object[] l_mqMessageSearchDataContainers = this.createQueryDataContainer(
                WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS,
                l_strInstitutionCode,
                l_request.triggerIssueInfo);

            //doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : Object[])
            //「MQ_MESSAGE_ID_MAPPING」に対して作成したSQLを実行する処理。
            //[doFindAllQuery()にセットするパラメータ]
            //　@arg0 ： MQ_MESSAGE_ID_MAPPINGテーブルRowTypeオブジェクト
            //　@arg1 ： create取得条件文字列() の戻り値
            //　@arg2 ： create検索条件データコンテナ() の戻り値
            //※ arg1、arg2 の値にはMQ_MESSAGE_ID_MAPPINGテーブルを参照するように設定した戻り値をセットする事。
            List l_lisMqMessageIdMappingsRows = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisMqMessageIdMappingsRows = l_queryProcessor.doFindAllQuery(
                    MqMessageIdMappingsRow.TYPE,
                    l_strMqMessageQueryString,
                    l_mqMessageSearchDataContainers);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() +  "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() +  "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //メッセージ データコードが存在しなかった場合(doFindAlQuery()の戻り値.size = 0) はエラー文言を返す。
            //メッセージ内容「MQ_MESSAGE_ID_MAPPINGSテーブルにデータがありません。」を出力する。
            if (l_lisMqMessageIdMappingsRows.size() == 0)
            {
                log.debug("MQ_MESSAGE_ID_MAPPINGSテーブルにデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03070,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "MQ_MESSAGE_ID_MAPPINGSテーブルにデータがありません。");
            }

            //create検索条件文字列(String)
            //データ取得条件の文字列を生成する。
            //[create検索条件文字列()にセットするパラメータ]
            //　@テーブル名 ：  "trading_time"
            String l_strTradingTimeQueryString =
                this.createQueryString(WEB3AdminDirTableNameDef.TRADING_TIME);

            //create検索条件データコンテナ(String, String, トリガー発行レコード詳細)
            //[create検索条件データコンテナ()にセットするパラメータ]
            //　@参照先テーブル名 ： "trading_time"
            //　@証券会社コード    ： get証券会社コード() の戻り値
            //　@検索情報           ： リクエストデータ.トリガー発行情報
            Object[] l_tradingTimeDataContainers = this.createQueryDataContainer(
                WEB3AdminDirTableNameDef.TRADING_TIME,
                l_strInstitutionCode,
                l_request.triggerIssueInfo);

            //is取引時間テーブル検索(String, Object[])
            //[is取引時間テーブル検索にセットするパラメータ]
            //　@取得条件文字列 = create検索条件文字列()の戻り値
            //　@データコンテナ = creata検索条件データコンテナ()の戻り値
            l_lisTradingTimeRows = this.isTradingTimeTableQuery(
                l_strTradingTimeQueryString,
                l_tradingTimeDataContainers);
        }

        //[create警告メッセージ() にセットするパラメータ]
        //　@再検索結果 ： トリガー発行情報テーブル再検索処理 の戻り値
        //　@取引時間TBLレコード一覧 ： is取引時間テーブル検索() の戻り値
        String[] l_strWarningMessages =
            this.createWarningMessage(l_lisReQueryResults, l_lisTradingTimeRows);

        //レスポンスデータを生成する。
        WEB3AdminDirSecTriggerIssueConfirmResponse l_response =
            (WEB3AdminDirSecTriggerIssueConfirmResponse)l_request.createResponse();

        //プロパティセット
        //レスポンスデータに以下の内容を設定する。
        //トリガー発行情報 : createトリガー発行レコード明細()の戻り値
        //警告メッセージ : create警告メッセ
        l_response.triggerIssueInfo = l_adminDirSecTriggerIssueRecord;
        l_response.messageWarning = l_strWarningMessages;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitトリガー発行処理完了画面表示)<BR>
     * トリガー発行処理完了画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submitトリガー発行処理完了」参照。<BR>
     * ==================================================<BR>
     * 　@　@具体位置 : DIR管理者以外（isDIR管理者()==false）の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * <BR>
     * ==================================================<BR>
     * 　@　@具体位置 : データコードが存在しなかった場合<BR>
     * 　@　@　@　@　@　@　@　@(doFindAlQuery()の戻り値.size = 0) はエラー文言を返す<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_03070<BR>
     * ==================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・トリガー発行処理完了リクエスト。<BR>
     * @@return WEB3AdminDirSecTriggerIssueCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C27BFA00EC
     */
    protected WEB3AdminDirSecTriggerIssueCompleteResponse submitTriggerIssueCompleteScreenDisplay(
        WEB3AdminDirSecTriggerIssueCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitTriggerIssueCompleteScreenDisplay(WEB3AdminDirSecTriggerIssueCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //[validate権限()に指定する引数]
        //　@機@能カテゴリコード："Z0101"(システム管理 トリガー発行処理)
        //　@is更新：true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR管理者( )
        //管理者が"DIR管理者"である場合、trueを返却する。
        //管理者が"証券会社管理者"である場合、falseを返却する。
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR管理者以外（isDIR管理者()==false）の場合、例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //validate取引パスワード(パスワード : String)
        //[validate取引パスワード()にセットするパラメータ]
        //　@パスワード ： リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);

        //get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //create検索条件文字列(String)
        //[create検索条件文字列()にセットするパラメータ]
        //テーブル名 ：  "MQ_MESSAGE_ID_MAPPINGS"
        String l_strQueryString =
            this.createQueryString(WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS);

        //create検索条件データコンテナ(String, String, トリガー発行レコード詳細)
        //[create検索条件データコンテナ()にセットするパラメータ]
        //　@参照先テーブル名 ： "MQ_MESSAGE_ID_MAPPINGS"
        //　@証券会社コード    ： get証券会社コード() の戻り値
        //　@検索情報           ： リクエストデータ.トリガー発行情報
        Object[] l_queryDataContainers = this.createQueryDataContainer(
            WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS,
            l_strInstitutionCode,
            l_request.triggerIssueInfo);

        //getDefaultProcessor( )
        //すべてのクエリに利用できるデフォルトクエリプロセッサを取得。
        List l_lisMqMessageIdMappingsRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : Object[])
            //「MQ_MESSAGE_ID_MAPPING」に対して作成したSQLを実行する処理。
            //[doFindAllQuery()にセットするパラメータ]
            //　@arg0 ： MQ_MESSAGE_ID_MAPPINGテーブルRowTypeオブジェクト
            //　@arg1 ： create取得条件文字列() の戻り値
            //　@arg2 ： create検索条件データコンテナ() の戻り値
            l_lisMqMessageIdMappingsRows = l_queryProcessor.doFindAllQuery(
                MqMessageIdMappingsRow.TYPE,
                l_strQueryString,
                l_queryDataContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //データコードが存在しなかった場合(doFindAlQuery()の戻り値.size = 0) はエラー文言を返す。
        //メッセージ内容「MQ_MESSAGE_ID_MAPPINGSテーブルにデータがありません。」を出力する。
        if (l_lisMqMessageIdMappingsRows.size() == 0)
        {
            log.debug("MQ_MESSAGE_ID_MAPPINGSテーブルにデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03070,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "MQ_MESSAGE_ID_MAPPINGSテーブルにデータがありません。");
        }

        /* 2008/07/14 add start */
        //MQリクエストの作成
        WEB3MQSendMessageRequest mqRequest = new WEB3MQSendMessageRequest();

        //リクエストパラメータのセット
        mqRequest.institutionCode = l_strInstitutionCode;
        mqRequest.dataCode = l_request.triggerIssueInfo.dataCode;
        mqRequest.userData = l_request.triggerIssueInfo.userData;

        //顧客ID(自～至)の設定
        mqRequest.accountIdStart = "100000000000000";
        mqRequest.accountIdEnd   = "999999999999999";

        // OracleSID 取得処理
        mqRequest.oracleSID = getDefaultOracleSid();

        //現在日付の取得
        Timestamp l_mqSystemTimestamp = GtlUtils.getSystemTimestamp();
        String l_strSystemDate =
        	WEB3DateUtility.formatDate(l_mqSystemTimestamp, "yyyyMMdd");
        mqRequest.bizDate = l_strSystemDate;

        //MQメッセージ送信処理
        WEB3MQManagementService l_mqManagementService =
        	(WEB3MQManagementService)Services.getService(WEB3MQManagementService.class);
        l_mqManagementService.execute(mqRequest);
        /* 2008/07/14 add end */

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminDirSecTriggerIssueCompleteResponse l_response =
            (WEB3AdminDirSecTriggerIssueCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * データを取得する為の条件を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）条件文の作成<BR>
     * 　@２－１） １）で作成した空の文字列に条件文をセット。<BR>
     * 　@　@　@　@　@　@セットする条件文は引数で渡されたテーブル名によって変化。<BR>
     * <BR>
     * 　@(テーブル名 ： 条件文)<BR>
     * 　@　@・submit_trigger_info ： "institution_code=? and<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@product_handling_div = ?"<BR>
     * <BR>
     * 　@　@・MQ_MESSAGE_ID_MAPPINGS ： "institution_code=? and<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@data_code=?"<BR>
     * <BR>
     * 　@　@・trading_time ： "institution_code = ? and<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@branch_code = ? and<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@trading_time_type = ? and<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@biz_date_type = ? and<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@start_time <= ? and<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@end_time >= ?　@and<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@submit_market_trigger = ?"<BR>
     * <BR>
     * ３） 文字列を返却する。<BR>
     * @@param l_strTableName - (テーブル名)<BR>
     * 検索対象先のテーブル名。<BR>
     * @@return String
     * @@roseuid 47C6153100BD
     */
    private String createQueryString(String l_strTableName)
    {
        final String STR_METHOD_NAME = "createQueryString(String)";
        log.entering(STR_METHOD_NAME);

        //空の文字列を生成する。
        String l_strSearchConditionList = null;

        //作成した空の文字列に条件文をセット。
        //セットする条件文は引数で渡されたテーブル名によって変化。
        //submit_trigger_info ： "institution_code=? and
        //product_handling_div = ?"
        if (WEB3AdminDirTableNameDef.SUBMIT_TRIGGER_INFO.equals(l_strTableName))
        {
        	l_strSearchConditionList = "institution_code = ? and product_handling_div = ?";
        }
        //MQ_MESSAGE_ID_MAPPINGS ： "institution_code = ? and data_code = ?"
        else if (WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS.equals(l_strTableName))
        {
        	l_strSearchConditionList = "institution_code = ? and data_code = ?";
        }
        //trading_time ： "institution_code = ? and
        //        branch_code = ? and
        //　@　@　@　@trading_time_type = ? and
        //　@　@　@　@biz_date_type = ? and
        //　@　@　@　@start_time <= ? and
        //　@　@　@　@end_time >= ?　@and
        //　@　@　@　@submit_market_trigger = ?"
        else if (WEB3AdminDirTableNameDef.TRADING_TIME.equals(l_strTableName))
        {
        	l_strSearchConditionList =
                "institution_code = ? and " +
                "branch_code = ? and " +
                "trading_time_type = ? and " +
                "biz_date_type = ? and " +
                "start_time <= ? and " +
                "end_time >= ? and " +
                "submit_market_trigger = ?";
        }

        //文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strSearchConditionList;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 取得条件データコンテナ作成。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）引数：証券会社コード を１）のArrayListに追加する。<BR>
     * <BR>
     * ３）引数：参照先テーブル名 によってセットする値を変化させる。<BR>
     * <BR>
     * 　@３－１）テーブル名 ： submit_trigger_info の場合。<BR>
     * 　@　@　@３－１－１） １）のArrayListに "0" を追加する。<BR>
     * <BR>
     * 　@３－２）テーブル名 ： MQ_MESSAGE_ID_MAPPINGS の場合。<BR>
     * 　@　@　@３－２－１） 引数：検索情報.データコード を１）のArrayListに追加する。<BR>
     * <BR>
     * 　@３－３）テーブル名 ： trading_time の場合。<BR>
     * 　@　@　@３－３－１） 管理者クラス「WEB3Administrator」のインスタンスを生成する。<BR>
     * <BR>
     * 　@　@　@３－３－２） ３－３－１）で作成したインスタンス.getBranchCode()を実行。<BR>
     * 　@　@　@　@　@　@　@　@　@　@getBranchCode() の戻り値を１）のArrayListに追加する。<BR>
     * <BR>
     * 　@　@　@３－３－３） 受付時間区分のセット判定。<BR>
     * 　@　@　@　@３－３－３－１） this.get受付時間区分() の戻り値 != null の場合<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@this.get受付時間区分() の戻り値 を１）のArrayListに追加。<BR>
     * <BR>
     * 　@　@　@　@３－３－３－２） this.get受付時間区分() の戻り値 == null の場合<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@return null を実行し処理を抜ける。<BR>
     * <BR>
     * 　@　@　@３－３－４） 取引時間管理クラスから、getBizDateType() を呼び出す。<BR>
     * 　@　@　@　@　@　@　@　@　@　@getBizDateType() の戻り値を１）のArrayListに追加する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@[getBizDateTypeにセットするパラメータ]<BR>
     * 　@　@　@　@　@　@　@　@　@　@TimeStamp ： TradingSystem.getSystemTimestamp()の戻り値<BR>
     * <BR>
     * 　@　@　@３－３－５） １）のArrayListに 現在時刻 を追加。<BR>
     * 　@　@　@　@　@　@　@　@　@　@(条件文 start_time に対応)<BR>
     * <BR>
     * 　@　@　@３－３－６） １）のArrayListに 現在時刻 を追加。<BR>
     * 　@　@　@　@　@　@　@　@　@　@(条件文 end_time に対応)<BR>
     * <BR>
     * 　@　@　@３－３－７） １）のArrayListに "1" を追加。<BR>
     * <BR>
     * ４）追加したArrayListに対して、toArray()を実行。<BR>
     * 　@　@配列を取得し、返却する。<BR>
     * @@param l_strReferencedTableName - (参照先テーブル名)<BR>
     * from にあたるテーブル名を保持<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_queryInfo - (検索情報)<BR>
     * 前処理から受け取ったリクエストデータを保持<BR>
     * @@return Object[]
     * @@roseuid 47C6163F00EE
     */
    private Object[] createQueryDataContainer(
        String l_strReferencedTableName,
        String l_strInstitutionCode,
        WEB3AdminDirSecTriggerIssueRecordDetail l_queryInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String," +
            " String, WEB3AdminDirSecTriggerIssueRecordDetail)";
        log.entering(STR_METHOD_NAME);

        //空のArrayListを生成する。
        List l_lisSearchConditionDataContainers = new ArrayList();

        //引数：証券会社コード を１）のArrayListに追加する。
        l_lisSearchConditionDataContainers.add(l_strInstitutionCode);

        //引数：参照先テーブル名 によってセットする値を変化させる。
        //テーブル名 ： submit_trigger_info の場合。ArrayListに "0" を追加する。
        if (WEB3AdminDirTableNameDef.SUBMIT_TRIGGER_INFO.equals(l_strReferencedTableName))
        {
            l_lisSearchConditionDataContainers.add(WEB3ProductHandlingDivDef.HANDLING);
        }
        //テーブル名 ： MQ_MESSAGE_ID_MAPPINGS の場合。
        //３－２－１） 引数：検索情報.データコード を１）のArrayListに追加する。
        else if (WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS.equals(l_strReferencedTableName))
        {
            l_lisSearchConditionDataContainers.add(l_queryInfo.dataCode);
        }
        //テーブル名 ： trading_time の場合。
        else if (WEB3AdminDirTableNameDef.TRADING_TIME.equals(l_strReferencedTableName))
        {
            //管理者クラス「WEB3Administrator」のインスタンスを生成する。
            WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

            //作成したインスタンス.getBranchCode() を実行。getBranchCode() の戻り値を１）のArrayListに追加する。
            l_lisSearchConditionDataContainers.add(l_administrator.getBranchCode());

            //受付時間区分のセット判定
            String l_strTradingTimeType = this.getTradingTimeType();
            if (l_strTradingTimeType != null)
            {
                //this.get受付時間区分() の戻り値 != null の場合
                //this.get受付時間区分() の戻り値 を１）のArrayListに追加
                l_lisSearchConditionDataContainers.add(l_strTradingTimeType);
            }
            else
            {
                //this.get受付時間区分() の戻り値 == null の場合
                // return null を実行し処理を抜ける
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //取引時間管理クラスから、getBizDateType() を呼び出す。
            //  getBizDateType() の戻り値を１）のArrayListに追加する
            String l_strBizDateType =
                WEB3GentradeTradingTimeManagement.getBizDateType(GtlUtils.getSystemTimestamp());
            l_lisSearchConditionDataContainers.add(l_strBizDateType);

            //ArrayListに 現在時刻 を追加。(条件文 start_time に対応)
            l_lisSearchConditionDataContainers.add(
                WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.TIME_FORMAT_HMS));

            //ArrayListに 現在時刻 を追加。(条件文 end_time に対応)
            l_lisSearchConditionDataContainers.add(
                WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.TIME_FORMAT_HMS));

            //ArrayListに "1" を追加。
            l_lisSearchConditionDataContainers.add(WEB3SubmitMarketTriggerDef.TRIGGER);
        }

        //追加したArrayListに対して、toArray()を実行。
        Object[] l_searchConditionDataContainers = new Object[l_lisSearchConditionDataContainers.size()];
        l_lisSearchConditionDataContainers.toArray(l_searchConditionDataContainers);

        //配列を取得し、返却する。
        log.exiting(STR_METHOD_NAME);
        return l_searchConditionDataContainers;
    }

    /**
     * (createソートキー)<BR>
     * ソート条件文字列を編集する。<BR>
     * <BR>
     * テーブル列物理名を使用し、以下の通り、ソート条件文字列（order by句）を編集する。<BR>
     * <BR>
     * １）パラメータ.ソートキーの要素数分以下の処理を繰り返し、<BR>
     * 　@　@　@ソート条件文字列を作成する。<BR>
     * <BR>
     * 　@１－１）ソートキー.キー項目に対応するテーブル列物理名をソート条件に追加する。<BR>
     * <BR>
     * 　@　@[ソートキー.キー項目 = データコード の場合]<BR>
     * 　@　@　@トリガー発行情報テーブル.request_code<BR>
     * <BR>
     * 　@　@[ソートキー.キー項目 = 再発行可能フラグ の場合]<BR>
     * 　@　@　@トリガー発行情報テーブル.enable_submit_trigger_flag<BR>
     * <BR>
     * 　@１－２）ソート条件に該当するソート条件を編集する。<BR>
     * 　@　@　@　@　@昇順：asc<BR>
     * 　@　@　@　@　@降順：desc<BR>
     * <BR>
     * ２） 作成したソート条件文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキー保持<BR>
     * @@return String
     * @@roseuid 47C6199A0357
     */
    private String createSortKey(WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortKey(WEB3AdminDirSecTriggerIssueSortKey[])";
        log.entering(STR_METHOD_NAME);

        //ソート条件文字列オブジェクト(：String)を作成する。
        StringBuffer l_sbSortKey = new StringBuffer();

        //ソートキー.キー項目に対応するテーブル列物理名をソート条件に追加する
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            if (WEB3AdminDirSecSortKeyItemDef.DATA_CODE.equals(l_sortKeys[i].keyItem))
            {
                //[ソートキー.キー項目 = データコード の場合]
                // トリガー発行情報テーブル.request_code
                l_sbSortKey.append(" request_code ");
            }
            else if (WEB3AdminDirSecSortKeyItemDef.REISSUE_POSSIBLE_FLAG.equals(l_sortKeys[i].keyItem))
            {
                //[ソートキー.キー項目 = 再発行可能フラグ の場合]
                //トリガー発行情報テーブル.enable_submit_trigger_flag
                l_sbSortKey.append(" enable_submit_trigger_flag ");
            }
            else
            {
                continue;
            }

            //ソート条件に該当するソート条件を編集する
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                //昇順：asc
                l_sbSortKey.append(" ASC ,");
            }
            else
            {
                //降順：desc
                l_sbSortKey.append(" DESC ,");
            }
        }

        //作成したソート条件文字列を返却する。
        if (l_sbSortKey.length() > 0)
        {
            l_sbSortKey = l_sbSortKey.deleteCharAt(l_sbSortKey.length() - 1);
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbSortKey.toString();
    }

    /**
     * (createトリガー発行情報一覧)<BR>
     * トリガー発行情報Paramsより、一覧情報を作成する。<BR>
     * <BR>
     * １）　@ArrayListオブジェクトの生成。<BR>
     * <BR>
     * ２）　@引数 : トリガー発行情報一覧Listの要素分、Loop処理を行う。<BR>
     * <BR>
     * 　@２－１）　@トリガー発行レコード詳細クラスのオブジェクトを生成。<BR>
     * 　@２－２）　@トリガー発行情報一覧Listからトリガー発行情報Rowを取り出す。<BR>
     * 　@２－３）　@２－１）で生成したオブジェクトに以下の内容をセットする。<BR>
     * <BR>
     * 　@　@・ トリガー発行レコード詳細オブジェクト.シェル名称<BR>
     * 　@　@　@　@　@= トリガー発行情報テーブルRow.getシェル名称<BR>
     * 　@　@・ トリガー発行レコード詳細オブジェクト.トリガー名称<BR>
     * 　@　@　@　@　@= トリガー発行情報テーブルRow.getトリガー名称<BR>
     * 　@　@・ トリガー発行レコード詳細オブジェクト.再発行可能フラグ<BR>
     * 　@　@　@　@　@= トリガー発行情報テーブルRow.get再発行可能フラグ<BR>
     * 　@　@・ トリガー発行レコード詳細オブジェクト.ユーザーデータ<BR>
     * 　@　@　@　@　@= トリガー発行情報テーブルRow.getユーザーデータ<BR>
     * 　@　@・ トリガー発行レコード詳細オブジェクト.データコード<BR>
     * 　@　@　@　@　@= トリガー発行情報テーブルRow.getデータコード<BR>
     * <BR>
     * 　@２－４）　@１）で生成したArrayListオブジェクト<BR>
     * 　@　@にトリガー発行レコード詳細オブジェクトをadd()する。<BR>
     * <BR>
     * ３）　@トリガー発行レコード詳細クラス型の配列オブジェクト<BR>
     * 　@　@をArrayListオブジェクトのサイズで生成する。<BR>
     * <BR>
     * ４）　@toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。<BR>
     * <BR>
     * 　@　@ArrayListオブジェクト.toArray(トリガー発行レコード詳細クラス型の配列オブジェクト); <BR>
     * <BR>
     * ５）　@変換した配列オブジェクトを返却する。<BR>
     * @@param l_lisTriggerIssueInfos - (トリガー発行情報一覧List)<BR>
     * トリガー発行情報一覧List<BR>
     * @@return WEB3AdminDirSecTriggerIssueRecordDetail[]
     * @@roseuid 47E0E17D0011
     */
    private WEB3AdminDirSecTriggerIssueRecordDetail[] createTriggerIssueInfoList(List l_lisTriggerIssueInfos)
    {
        final String STR_METHOD_NAME = "createTriggerIssueInfoList(List)";
        log.entering(STR_METHOD_NAME);

        //ArrayListオブジェクトの生成。
        List l_lisTriggerIssueInfoLists = new ArrayList();

        //引数 : トリガー発行情報一覧Listの要素分、Loop処理を行う。
        Iterator l_iterator = l_lisTriggerIssueInfos.iterator();
        WEB3AdminDirSecTriggerIssueRecordDetail l_adminDirSecTriggerIssueRecordDetail = null;
        SubmitTriggerInfoRow l_submitTriggerInfoRow = null;
        while (l_iterator.hasNext())
        {
        	//トリガー発行レコード詳細クラスのオブジェクトを生成。
        	l_adminDirSecTriggerIssueRecordDetail = new WEB3AdminDirSecTriggerIssueRecordDetail();
        	//トリガー発行情報一覧Listからトリガー発行情報Rowを取り出す。
        	l_submitTriggerInfoRow = (SubmitTriggerInfoRow)l_iterator.next();

            //生成したオブジェクトに以下の内容をセットする。
            //トリガー発行レコード詳細オブジェクト.シェル名称=トリガー発行情報テーブルRow.getシェル名称
            //トリガー発行レコード詳細オブジェクト.トリガー名称=トリガー発行情報テーブルRow.getトリガー名称
            //トリガー発行レコード詳細オブジェクト.再発行可能フラグ=トリガー発行情報テーブルRow.get再発行可能フラグ
            //トリガー発行レコード詳細オブジェクト.ユーザーデータ=トリガー発行情報テーブルRow.getユーザーデータ
            //トリガー発行レコード詳細オブジェクト.データコード=トリガー発行情報テーブルRow.getデータコード
        	l_adminDirSecTriggerIssueRecordDetail.shellName = l_submitTriggerInfoRow.getJobId();
        	l_adminDirSecTriggerIssueRecordDetail.triggerName = l_submitTriggerInfoRow.getTriggerId();
            l_adminDirSecTriggerIssueRecordDetail.reissuePossibleFlag =
                l_submitTriggerInfoRow.getEnableSubmitTriggerFlag();
        	l_adminDirSecTriggerIssueRecordDetail.userData = l_submitTriggerInfoRow.getUserData();
        	l_adminDirSecTriggerIssueRecordDetail.dataCode = l_submitTriggerInfoRow.getRequestCode();

        	//生成したArrayListオブジェクトにトリガー発行レコード詳細オブジェクトをadd()する。
        	l_lisTriggerIssueInfoLists.add(l_adminDirSecTriggerIssueRecordDetail);
        }

        //トリガー発行レコード詳細クラス型の配列オブジェクトをArrayListオブジェクトのサイズで生成する。
        WEB3AdminDirSecTriggerIssueRecordDetail[] l_adminDirSecTriggerIssueRecordDetails =
        	new WEB3AdminDirSecTriggerIssueRecordDetail[l_lisTriggerIssueInfoLists.size()];

        //toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。
        l_lisTriggerIssueInfoLists.toArray(l_adminDirSecTriggerIssueRecordDetails);

        //変換した配列オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_adminDirSecTriggerIssueRecordDetails;
    }

    /**
     * (トリガー発行情報テーブル再検索処理)<BR>
     * リクエストから渡された値を元に、トリガー発行情報テーブルを再度検索を行う処理。<BR>
     * <BR>
     * １）処理前準備。<BR>
     * 　@１－１） 下記の変数を用意。<BR>
     * 　@　@①@ 空のString<BR>
     * 　@　@② 空のArrayList<BR>
     * <BR>
     * 　@１－２） ①@の変数に下記の文字列を格納する。<BR>
     * 　@　@　@"institution_code = ? and request_code = ? and job_id = ?"<BR>
     * <BR>
     * 　@１－３） ②の変数に引数で渡された値<BR>
     * 　@　@　@を「証券会社コード」「データコード」「シェル名称」の順で追加。<BR>
     * <BR>
     * 　@　@　@　@　@　@ArrayList.add(引数:証券会社コード);<BR>
     * 　@　@　@　@　@　@ArrayList.add(引数:データコード);<BR>
     * 　@　@　@　@　@　@ArrayList.add(引数:シェル名称);<BR>
     * <BR>
     * ２）getDefaultProcessor() を実行。<BR>
     * <BR>
     * ３）doFindAllQuery() を実行、引数は以下の通り。<BR>
     * 　@　@arg0 ： トリガー発行情報テーブルRowTypeオブジェクト<BR>
     * 　@　@arg1 ： ①@の変数<BR>
     * 　@　@arg2 ： ②の変数.toArray<BR>
     * <BR>
     *４）受付時間区分を取得。<BR>
     * 　@４－１） ３）の戻り値からRowオブジェクトを取得する。<BR>
     * <BR>
     * 　@４－２） this.set受付時間区分を呼び出す。<BR>
     * 　@　@　@　@[set受付時間区分にセットするパラメータ]<BR>
     * 　@　@　@　@再検索後受付時間区分 ：<BR>
     * 　@　@　@　@　@４－１）で取得したRowオブジェクト.getColumn("trading_time_type")の戻り値。<BR>
     * <BR>
     * ５） ３）の戻り値を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * トリガー発行処理入力画面から取得した"証券会社コード"<BR>
     * @@param l_strDataCode - (データコード)<BR>
     * トリガー発行処理入力画面から取得した"データコード"<BR>
     * @@param l_strShellName - (シェル名称)<BR>
     * トリガー発行処理入力画面から取得した"シェル名称"<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 47E712BF001B
     */
    private List triggerIssueInfoTableReQuery(
        String l_strInstitutionCode,
        String l_strDataCode,
        String l_strShellName) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "triggerIssueInfoTableReQuery(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //Stringの変数に下記の文字列を格納する。
        //"institution_code = ? and request_code = ? and job_id = ?"
        String l_strSql = " institution_code = ? and request_code = ? and job_id = ? ";

        //ArrayListの変数に引数で渡された値を「証券会社コード」「データコード」「シェル名称」
        //の順で追加。
        //  ArrayList.add(引数:証券会社コード);
        //  ArrayList.add(引数:データコード);
        //  ArrayList.add(引数:シェル名称);
        List l_lisSqlValues = new ArrayList();
        l_lisSqlValues.add(l_strInstitutionCode);
        l_lisSqlValues.add(l_strDataCode);
        l_lisSqlValues.add(l_strShellName);

        //doFindAllQuery() を実行、引数は以下の通り。
        //　@arg0 ： トリガー発行情報テーブルRowTypeオブジェクト
        //　@arg1 ： ①@の変数
        //　@arg2 ： ②の変数.toArray
        List l_lisResults;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                SubmitTriggerInfoRow.TYPE,
                l_strSql,
                l_lisSqlValues.toArray());
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //受付時間区分を取得。
        if (l_lisResults.size() > 0)
        {
            SubmitTriggerInfoRow l_submitTriggerInfoRow =
                (SubmitTriggerInfoRow)l_lisResults.get(0);
            //this.set受付時間区分を呼び出す。
            //　@[set受付時間区分にセットするパラメータ]
            //　@再検索後受付時間区分 ：
            //   ４－１）で取得したRowオブジェクト.getColumn("trading_time_type")の戻り値。
            this.setTradingTimeType(l_submitTriggerInfoRow.getTradingTimeType());
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisResults;
    }

    /**
     * (set受付時間区分)<BR>
     * トリガー発行情報テーブル再検索処理() にて取得した受付時間区分をセットするメソッド。<BR>
     * <BR>
     * set受付時間区分(再検索後受付時間区分){<BR>
     * 　@　@this.受付時間区分 = 再検索後受付時間区分<BR>
     * }<BR>
     * @@param l_strTradingTimeType - (再検索後受付時間区分)<BR>
     * 再検索後受付時間区分を保持。<BR>
     */
    private void setTradingTimeType(String l_strTradingTimeType)
    {
        final String STR_METHOD_NAME = "setTradingTimeType(String)";
        log.entering(STR_METHOD_NAME);

        //this.受付時間区分 = 再検索後受付時間区分
        this.tradingTimeType = l_strTradingTimeType;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get受付時間区分)<BR>
     * トリガー発行情報テーブル再検索処理() にて取得した受付時間区分を取得するメソッド。<BR>
     * <BR>
     * get受付時間区分(){<BR>
     * 　@　@return this.受付時間区分<BR>
     * }<BR>
     * @@return String
     */
    private String getTradingTimeType()
    {
        return this.tradingTimeType;
    }

    /**
     * (createトリガー発行レコード明細)<BR>
     * 「トリガー発行情報テーブル再検索処理」の戻り値から再検索結果を取得する。<BR>
     * <BR>
     * １）トリガー発行レコード明細オブジェクトを生成。<BR>
     * <BR>
     * ２）引数:再検索結果からトリガー発行情報テーブルRowオブジェクトを取得する。<BR>
     * <BR>
     * ３） １）で作成したオブジェクトに対して、再検索結果をセットする。<BR>
     * 　@　@・ トリガー発行レコード詳細オブジェクト.シェル名称<BR>
     * 　@　@　@　@　@= トリガー発行情報テーブルRow.getシェル名称<BR>
     * 　@　@・ トリガー発行レコード詳細オブジェクト.トリガー名称<BR>
     * 　@　@　@　@　@= トリガー発行情報テーブルRow.getトリガー名称<BR>
     * 　@　@・ トリガー発行レコード詳細オブジェクト.再発行可能フラグ<BR>
     * 　@　@　@　@　@= トリガー発行情報テーブルRow.get再発行可能フラグ<BR>
     * 　@　@・ トリガー発行レコード詳細オブジェクト.ユーザーデータ<BR>
     * 　@　@　@　@　@= トリガー発行情報テーブルRow.getユーザーデータ<BR>
     * 　@　@・ トリガー発行レコード詳細オブジェクト.データコード<BR>
     * 　@　@　@　@　@= トリガー発行情報テーブルRow.getデータコード<BR>
     * <BR>
     * ４）処理結果を返却する。<BR>
     * @@param l_lisReQueryResults - (再検索結果)<BR>
     * 「トリガー発行情報テーブル再検索処理」の戻り値を保持。<BR>
     * @@return WEB3AdminDirSecTriggerIssueRecordDetail
     * @@roseuid 47E754020156
     */
    private WEB3AdminDirSecTriggerIssueRecordDetail createTriggerIssueRecordDetail(List l_lisReQueryResults)
    {
        final String STR_METHOD_NAME = "createTriggerIssueRecordDetail(List)";
        log.entering(STR_METHOD_NAME);

        //トリガー発行レコード明細オブジェクトを生成。
        WEB3AdminDirSecTriggerIssueRecordDetail l_adminDirSecTriggerIssueRecordDetail =
            new WEB3AdminDirSecTriggerIssueRecordDetail();

        SubmitTriggerInfoRow l_submitTriggerInfoRow = (SubmitTriggerInfoRow)l_lisReQueryResults.get(0);
        //トリガー発行レコード詳細オブジェクト.シェル名称= トリガー発行情報テーブルRow.getシェル名称
        l_adminDirSecTriggerIssueRecordDetail.shellName = l_submitTriggerInfoRow.getJobId();
        //トリガー発行レコード詳細オブジェクト.トリガー名称= トリガー発行情報テーブルRow.getトリガー名称
        l_adminDirSecTriggerIssueRecordDetail.triggerName = l_submitTriggerInfoRow.getTriggerId();
        //トリガー発行レコード詳細オブジェクト.再発行可能フラグ= トリガー発行情報テーブルRow.get再発行可能フラグ
        l_adminDirSecTriggerIssueRecordDetail.reissuePossibleFlag =
            l_submitTriggerInfoRow.getEnableSubmitTriggerFlag();
        //トリガー発行レコード詳細オブジェクト.ユーザーデータ= トリガー発行情報テーブルRow.getユーザーデータ
        l_adminDirSecTriggerIssueRecordDetail.userData = l_submitTriggerInfoRow.getUserData();
        //トリガー発行レコード詳細オブジェクト.データコード= トリガー発行情報テーブルRow.getデータコード
        l_adminDirSecTriggerIssueRecordDetail.dataCode = l_submitTriggerInfoRow.getRequestCode();

        log.exiting(STR_METHOD_NAME);
        return l_adminDirSecTriggerIssueRecordDetail;
    }

    /**
     * (is取引時間テーブル検索)<BR>
     * create検索条件データコンテナ() の戻り値によって、<BR>
     * 取引時間テーブルに検索を行うか判定を行う。<BR>
     * <BR>
     * １）引数.データコンテナ == null の場合、下記の処理<BR>
     * <BR>
     * 　@　@List lst = new ArrayList();<BR>
     * <BR>
     * 　@　@を実行したListを戻り値としてリターンする。<BR>
     * 　@　@　@※上記の処理は次処理のcreate警告メッセージ() で<BR>
     * 　@　@　@　@「ただいまの時間はMQトリガーを発行できない可能性があります」<BR>
     * 　@　@　@　@を出力する為に行っている処理です。<BR>
     * <BR>
     * ２）引数.データコンテナ != null の場合、<BR>
     * 　@　@「取引時間テーブル」に対してdoFindAllQuery() を実行し、<BR>
     * 　@　@戻り値をリターンする。<BR>
     * <BR>
     * [doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0 ： 取引時間テーブルRowTypeオブジェクト<BR>
     * 　@arg1 ： 引数.取得条件文字列<BR>
     * 　@arg2 ： 引数.データコンテナ<BR>
     * @@param l_strQueryString - (取得条件文字列)<BR>
     * 取得条件文字列<BR>
     * @@param l_dateContainers - (データコンテナ)<BR>
     * データコンテナ<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    private List isTradingTimeTableQuery(
        String l_strQueryString,
        Object[] l_dateContainers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTradingTimeTableQuery(String, Object[])";
        log.entering(STR_METHOD_NAME);

        List l_lisResults = new ArrayList();

        if (l_dateContainers == null)
        {
            //引数.データコンテナ == null の場合、下記の処理
            log.exiting(STR_METHOD_NAME);

            return l_lisResults;
        }
        else
        {
            //引数.データコンテナ != null の場合
            //「取引時間テーブル」に対してdoFindAllQuery() を実行し、
            //　@戻り値をリターンする。
            //[doFindAllQuery()にセットするパラメータ]
            //　@arg0 ： 取引時間テーブルRowTypeオブジェクト
            //　@arg1 ： 引数.取得条件文字列
            //　@arg2 ： 引数.データコンテナ
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisResults = l_queryProcessor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_strQueryString,
                    l_dateContainers);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisResults;
    }

    /**
     * (create警告メッセージ)<BR>
     * 不正な値に対して警告メッセージを作成する。<BR>
     * <BR>
     * １）String型の変数を配列で用意する。(String[] str = new String[2];)<BR>
     * <BR>
     * ２）トリガー発行情報テーブル再検索処理の警告メッセージ作成<BR>
     * 　@２－１）引数：再検索結果.size() == 0 の場合、<BR>
     * 　@　@　@　@　@警告メッセージ「トリガー発行情報テーブルに入力したデータコードが存在しません」<BR>
     * 　@　@　@　@　@を String[0]に格納する。<BR>
     * 　@　@　@　@　@引数：再検索結果.size() != 0 の場合は null を格納。<BR>
     * <BR>
     * ３）取引時間テーブル検索結果用の警告メッセージ作成<BR>
     * 　@３－１）引数：取引時間テーブルレコード一覧.size() == 0 の場合、<BR>
     * 　@　@　@　@　@警告メッセージ「ただいまの時間はMQトリガーを発行できない可能性があります」<BR>
     * 　@　@　@　@　@をString[1]に格納する。<BR>
     * 　@　@　@　@　@引数：取引時間テーブルレコード一覧.size() の値 != 0 の場合は nullを格納。<BR>
     * <BR>
     * ４） １）にて定義した変数をリターン。<BR>
     * @@param l_lisReQueryResults - (再検索結果)<BR>
     * トリガー発行情報テーブルを再検索した結果を保持。<BR>
     * @@param l_lisTradingTimeTableRecordLists - (取引時間テーブルレコード一覧)<BR>
     * 取引時間テーブルを参照した結果を保持。<BR>
     * @@return String[]
     * @@roseuid 47C66F3403C0
     */
    private String[] createWarningMessage(
        List l_lisReQueryResults,
        List l_lisTradingTimeTableRecordLists)
    {
        final String STR_METHOD_NAME = "createWarningMessage(List, List)";
        log.entering(STR_METHOD_NAME);

        //String型の変数を配列で用意する。(String[] str = new String[2];)
        String[] l_strWarningMessages = new String[2];

        //トリガー発行情報テーブル再検索処理の警告メッセージ作成
        //　@引数：再検索結果.size() == 0 の場合、
        //　@　@　@　@　@警告メッセージ 「トリガー発行情報テーブルに入力したデータコードが存在しません」
        //　@　@　@　@　@を String[0]に格納する。
        //　@　@　@　@　@引数：再検索結果.size() != 0 の場合は null を格納。
        if (l_lisReQueryResults.size() == 0)
        {
            l_strWarningMessages[0] = "トリガー発行情報テーブルに入力したデータコードが存在しません";
        }
        else
        {
            l_strWarningMessages[0] = null;
        }

        //取引時間テーブル検索結果用の警告メッセージ作成
        //　@引数：取引時間テーブルレコード一覧.size() == 0 の場合、
        //　@　@　@　@　@警告メッセージ 「ただいまの時間はMQトリガーを発行できない可能性があります」
        //　@　@　@　@　@をString[1]に格納する。
        //　@　@　@　@　@引数：取引時間テーブルレコード一覧.size() の値 != 0 の場合は null を格納。
        if (l_lisTradingTimeTableRecordLists.size() == 0)
        {
            l_strWarningMessages[1] = "ただいまの時間はMQトリガーを発行できない可能性があります";
        }
        else
        {
            l_strWarningMessages[1] = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strWarningMessages;
    }

    /* 2008/07/14 add start */
    /**
     * コンテキスト情報が設定されていない場合に使用されるOracleSIDを
     * ラウンドロビン・ロジックで取得する。
     * 
     * @@return OracleSID
     * @@throws WEB3SystemLayerException
     */
    private String getDefaultOracleSid() throws WEB3SystemLayerException
    {
        String l_strJndiName = getRoundRobinBasedJndiName();
        return getOracleSid(l_strJndiName);
    }

    /**
     * RoundRobinBasedMultiPoolDataSourceより使用するデータソースのJNDI名を取得する。
     * 
     * @@return JNDI名
     */
    private String getRoundRobinBasedJndiName()
    {
        RoundRobinBasedMultiPoolDataSource l_ds =
            (RoundRobinBasedMultiPoolDataSource) Services.getService(
                RoundRobinBasedMultiPoolDataSource.class);
        return l_ds.getJndiName();
    }
    /**
     * 使用するDB接続プールにマッピングされているOracleSIDを取得する。
     * 
     * @@param l_strJndiName
     *            DB接続プールのJNDI名
     * @@return Oracle SID
     * @@throws WEB3SystemLayerException
     */
    private String getOracleSid(String l_strJndiName) throws WEB3SystemLayerException
    {
        try
        {
            MpdsSettingsRow l_mpdsSetting = MpdsSettingsDao.findRowByPk(
                    "db.cluster.sid", l_strJndiName);
            return l_mpdsSetting.getSettingValue();
        } catch (DataFindException l_dfe)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getSid(String)", 
                    l_dfe.getMessage(), 
                    l_dfe);
        } catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass().getName() + ".getSid(String)", 
                    l_de.getMessage(), 
                    l_de);
        }
    }
    /* 2008/07/14 add end */
}
@
