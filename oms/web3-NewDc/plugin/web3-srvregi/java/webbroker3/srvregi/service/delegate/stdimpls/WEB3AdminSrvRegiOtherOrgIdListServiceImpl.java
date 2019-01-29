head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽImpl(WEB3AdminSrvRegiOtherOrgIdListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/20 周墨洋 (中訊) 新規作成・モデルNo.335, No.350, No.351, No.356
Revision History : 2008/03/27 武波  (中訊) 実装の問題003
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OtherOrgStatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdListService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽImpl)<BR>
 * サービス利用管理者外部連携ID一覧照会サービス実装クラス<BR>
 * <BR>
 * @@author 周墨洋<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminSrvRegiOtherOrgIdListServiceImpl
    implements WEB3AdminSrvRegiOtherOrgIdListService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdListServiceImpl.class);

    /**
     * @@roseuid 47D1113002D1
     */
    public WEB3AdminSrvRegiOtherOrgIdListServiceImpl()
    {

    }

    /**
     * サービス利用管理者外部連携ID一覧照会処理を行う。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、<BR>
     * 　@サービス利用管理者外部連携ID一覧条件入力ﾘｸｴｽﾄの場合<BR>
     * 　@－get一覧条件画面()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、サービス利用管理者外部連携ID一覧照会ﾘｸｴｽﾄの場合<BR>
     * 　@－get一覧照会画面()をコールする。<BR>
     * <BR>
     * ※サービスメソッドにて例外が発生した場合は、<BR>
     * 　@例外オブジェクトの追加文字列（WEB3BaseException.errorMessage）<BR>
     * 　@をレスポンスデータ.errorMessageにセットする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B931050100
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １）　@リクエストデータの型により、以下の通りメソッドをコールする。
        if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdListSearchRequest)
        {
            // ○ 引数のリクエストデータが、
            // 　@サービス利用管理者外部連携ID一覧条件入力ﾘｸｴｽﾄの場合
            // 　@－get一覧条件画面()をコールする。BR>
            WEB3AdminSrvRegiOtherOrgIdListSearchResponse l_response =
                getListSearchScreen(
                    (WEB3AdminSrvRegiOtherOrgIdListSearchRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdListReferenceRequest)
        {
            // ○ 引数のリクエストデータが、
            // 　@サービス利用管理者外部連携ID一覧照会ﾘｸｴｽﾄの場合
            // 　@－get一覧照会画面()をコールする。
            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                getListReferenceScreen(
                    (WEB3AdminSrvRegiOtherOrgIdListReferenceRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
    }

    /**
     * (get一覧条件画面)<BR>
     * サービス利用管理者外部連携ID一覧条件入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(サービス利用)外部連携ID一覧照会・get一覧条件画面」参照。<BR>
     * @@param l_request - リクエストデータ<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdListSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B931170112
     */
    public WEB3AdminSrvRegiOtherOrgIdListSearchResponse getListSearchScreen(
        WEB3AdminSrvRegiOtherOrgIdListSearchRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getListSearchScreen(WEB3AdminSrvRegiOtherOrgIdListSearchRequest)";
        log.entering(STR_METHOD_NAME);

        // getInstanceFromログイン情報()
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        // [引数]
        // 機@能カテゴリコード：機@能カテゴリコード.サービス利用管理(外部連携)
        // is更新：false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
            false);

        // get証券会社コード()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get特殊処理サービスマスター一覧(String, String)
        // [引数]
        // 　@証券会社コード=管理者オブジェクト.get証券会社コード( )の戻り値
        // 　@特殊処理区分=リクエストデータ.特殊処理区分
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement =
            new WEB3SrvRegiServiceInfoManagement();

        WEB3SrvRegiServiceMaster[] l_srvRegiServiceMasters =
            l_srvRegiServiceInfoManagement.getSpecialProcessSrvMasterList(
                l_strInstitutionCode,
                l_request.specialProcessDiv);

        int l_intLength = l_srvRegiServiceMasters.length;

        // サービス区分
        String[] l_strSrvDivs = new String[l_intLength];

        // サービス名称
        String[] l_strSrvNames = new String[l_intLength];

        // 取得したサービスマスターの件数分のLOOP
        for (int i = 0; i < l_intLength; i++)
        {
            // getサービス区分()
            l_strSrvDivs[i] = l_srvRegiServiceMasters[i].getSrvDiv();

            // getサービス名称()
            l_strSrvNames[i] = l_srvRegiServiceMasters[i].getSrvName();
        }

        // サービス利用管理者外部連携ID一覧条件入力ﾚｽﾎﾟﾝｽ()
        // [コンストラクタの引数]
        // l_request：　@リクエストデータ
        WEB3AdminSrvRegiOtherOrgIdListSearchResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdListSearchResponse)l_request.createResponse();

        // プロパティセット
        // サービス区分
        l_response.serviceDiv = l_strSrvDivs;

        // サービス名称
        l_response.serviceName = l_strSrvNames;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get一覧照会画面)<BR>
     * サービス利用管理者外部連携ID一覧照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(サービス利用)外部連携ID一覧照会・get一覧照会画面」参照。<BR>
     * <BR>
     * ==========================================================<BR>
     * シーケンス図(「(サービス利用)外部連携ID一覧照会・get一覧照会画面」): <BR>
     * 　@　@　@1.9: <ページング制御><BR>
     * 　@　@　@　@　@２）設定後、レスポンス.総ページ数＝0 の場合は、<BR>
     * 　@　@　@　@　@レスポンス.サービス利用管理者外部連携ID一覧照会明細行<BR>
     * 　@　@　@　@　@(サービス利用管理者外部連携ID一覧照会明細行[ ])<BR>
     * 　@　@　@　@　@にnullをセットし例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_03053<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ<BR>
     * リクエストデータ<BR>
     * @@return WEB3BaseException
     * @@roseuid 47B94A6502D4
     */
    public WEB3AdminSrvRegiOtherOrgIdListReferenceResponse getListReferenceScreen(
        WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getListReferenceScreen(WEB3AdminSrvRegiOtherOrgIdListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // getInstanceFromログイン情報()
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        // [引数]
        // 機@能カテゴリコード：機@能カテゴリコード.サービス利用管理(外部連携)
        // is更新：false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
            false);

        // validate部店権限(部店コード : String[])
        // [引数]
        // 　@部店コード：リクエストデータ.部店コードを配列として引き渡す
        l_administrator.validateBranchPermission(l_request.branchCode);

        // get証券会社コード()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get外部連携情報一覧(
        //    long, String, String, String, String, String[],
        //    String, Timestamp, Timestamp, サービス利用ソートキー[ ])
        // [引数]
        // 　@　@通番：リクエストデータ.通番
        // 　@　@サービス区分：リクエストデータ.サービス区分
        // 　@　@ID：リクエストデータ.ID
        // 　@　@ステータス：リクエストデータ.ステータス
        // 　@　@証券会社コード：get証券会社コード( )の戻り値
        // 　@　@部店コード：リクエストデータ.部店コードの配列
        // 　@　@口座コード：リクエストデータ.口座コード
        // 　@　@適用開始日（自）：リクエストデータ.適用開始日（自）
        // 　@　@適用開始日（至）：リクエストデータ.適用開始日（至）
        // 　@　@ソート条件：リクエストデータ.サービス利用ソートキー
        WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
            (WEB3SrvRegiOtherOrgService)Services.getService(
                WEB3SrvRegiOtherOrgService.class);

        Timestamp l_tsAppliStartFrom = null;
        if (l_request.appliStartFrom != null)
        {
            l_tsAppliStartFrom =
                new Timestamp(l_request.appliStartFrom.getTime());
        }

        Timestamp l_tsAppliStartTo = null;
        if (l_request.appliStartTo != null)
        {
            l_tsAppliStartTo =
                new Timestamp(l_request.appliStartTo.getTime());
        }

        OtherOrgInfoAdminParams[] l_otherOrgInfoAdminParams =
            l_srvRegiOtherOrgService.getOtherOrgInfoList(
                l_request.seqNumber,
                l_request.serviceDiv,
                l_request.id,
                l_request.status,
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode,
                l_tsAppliStartFrom,
                l_tsAppliStartTo,
                l_request.sortKeys);

        int l_intLength = l_otherOrgInfoAdminParams.length;

        // サービス利用管理者外部連携ID一覧照会明細行
        WEB3AdminSrvRegiOtherOrgIdGroup[] l_adminSrvRegiOtherOrgIdGroups =
            new WEB3AdminSrvRegiOtherOrgIdGroup[l_intLength];

        // 取得した外部連携情報Paramsオブジェクトの配列の件数分のLOOP
        for (int i = 0; i < l_intLength; i++)
        {
            // サービス利用管理者外部連携ID一覧照会明細行()(
            WEB3AdminSrvRegiOtherOrgIdGroup l_adminSrvRegiOtherOrgIdGroup =
                new WEB3AdminSrvRegiOtherOrgIdGroup();

            // プロパティセット
            // (*1.1)以下の通り、プロパティセットを行う。

            // サービス利用管理者外部連携ID一覧照会明細行.通番=
            // 取得した外部連携情報Paramsオブジェクト.get通番( )
            // をString型に変換してセット
            l_adminSrvRegiOtherOrgIdGroup.seqNumber =
                String.valueOf(l_otherOrgInfoAdminParams[i].getSequenceNumber());


            // サービス利用管理者外部連携ID一覧照会明細行.ID=
            // 取得した外部連携情報Paramsオブジェクト.getID( )
            l_adminSrvRegiOtherOrgIdGroup.id =
                l_otherOrgInfoAdminParams[i].getId();

            // サービス利用管理者外部連携ID一覧照会明細行.パスワード=
            // 取得した外部連携情報Paramsオブジェクト.getパスワード( )
            l_adminSrvRegiOtherOrgIdGroup.password =
                l_otherOrgInfoAdminParams[i].getPassword();

            // サービス利用管理者外部連携ID一覧照会明細行.ステータス=
            // 取得した外部連携情報Paramsオブジェクト.getステータス( )
            String l_strStatus = l_otherOrgInfoAdminParams[i].getStatus();
            l_adminSrvRegiOtherOrgIdGroup.status = l_strStatus;

            // サービス利用管理者外部連携ID一覧照会明細行.部店コード=
            // 取得した外部連携情報Paramsオブジェクト.get部店コード( )
            l_adminSrvRegiOtherOrgIdGroup.branchCode =
                l_otherOrgInfoAdminParams[i].getBranchCode();

            // サービス利用管理者外部連携ID一覧照会明細行.口座コード=
            // 取得した外部連携情報Paramsオブジェクト.get口座コード( ).substring(0,6)
            // 　@（7桁→6桁）
            if (l_otherOrgInfoAdminParams[i].getAccountCode() != null)
            {
                l_adminSrvRegiOtherOrgIdGroup.accountCode =
                    l_otherOrgInfoAdminParams[i].getAccountCode().substring(0, 6);
            }
            else
            {
                l_adminSrvRegiOtherOrgIdGroup.accountCode = null;
            }

            // サービス利用管理者外部連携ID一覧照会明細行.適用期間From=
            // 取得した外部連携情報Paramsオブジェクト.get適用期間From( )
            l_adminSrvRegiOtherOrgIdGroup.appliStartDate =
                l_otherOrgInfoAdminParams[i].getAppliStartDate();

            // サービス利用管理者外部連携ID一覧照会明細行.適用期間To=
            // 取得した外部連携情報Paramsオブジェクト.get適用期間To( )
            l_adminSrvRegiOtherOrgIdGroup.appliEndDate =
                l_otherOrgInfoAdminParams[i].getAppliEndDate();

            if (WEB3OtherOrgStatusDef.DEFAULT.equals(l_strStatus))
            {
                // 取得した外部連携情報Paramsオブジェクト.getステータス( )=
                // '9：未使用'の場合
                // 　@　@サービス利用管理者外部連携ID一覧照会明細行.最終更新日=null
                l_adminSrvRegiOtherOrgIdGroup.lastUpdateTime = null;

                // 　@　@サービス利用管理者外部連携ID一覧照会明細行.最終更新者=null
                l_adminSrvRegiOtherOrgIdGroup.lastUpdater = null;
            }
            else
            {
                // 取得した外部連携情報Paramsオブジェクト.getステータス( )=
                // '9：未使用'以外の場合
                // 　@　@サービス利用管理者外部連携ID一覧照会明細行.最終更新日=
                // 　@　@取得した外部連携情報Paramsオブジェクト.get更新日付( )
                l_adminSrvRegiOtherOrgIdGroup.lastUpdateTime =
                    l_otherOrgInfoAdminParams[i].getLastUpdatedTimestamp();

                // 　@　@サービス利用管理者外部連携ID一覧照会明細行.最終更新者=
                // 　@　@取得した外部連携情報Paramsオブジェクト.get更新者コード( )
                l_adminSrvRegiOtherOrgIdGroup.lastUpdater =
                    l_otherOrgInfoAdminParams[i].getLastUpdater();
            }

            l_adminSrvRegiOtherOrgIdGroups[i] = l_adminSrvRegiOtherOrgIdGroup;
        }

        // サービス利用管理者外部連携ID一覧照会ﾚｽﾎﾟﾝｽ()
        // [コンストラクタの引数]
        // l_request：　@リクエストデータ
        WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdListReferenceResponse)l_request.createResponse();

        // <ページング制御>
        // 　@１）レスポンスの以下の項目を設定する。
        //
        // 　@○レスポンス.総ページ数＝
        // 　@　@サービス利用管理者外部連携ID一覧照会明細の要素数
        // 　@　@÷リクエスト.ページ内表示行数
        // 　@　@　@※余りが出る場合は、＋１した値を設定。
        // 　@　@　@※サービス利用管理者外部連携ID一覧照会明細の要素数＝0
        // 　@　@　@(表示対象データなし)の場合、0をセット。
        // 　@○レスポンス.総レコード数:
        // 　@　@サービス利用管理者外部連携ID一覧照会明細の要素数
        // 　@○レスポンス.表示ページ番号(表示が何ページ目にあたるか):
        // 　@　@　@　@以下の条件に合致するのであれば、リクエスト.要求ページ番号。
        // [サービス利用管理者外部連携ID一覧照会明細の要素数 >
        // (リクエスト.ページ内表示行数×(リクエスト.要求ページ番号-1) )]
        // 　@　@　@　@上記以外の場合は、レスポンス.総ページ数をそのまま設定。
        // 　@　@　@※検索結果がPR層からの要求ページ番号に満たない場合は、
        // 　@　@　@　@最終ページに該当するデータをレスポンスに設定する。
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_adminSrvRegiOtherOrgIdGroups,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));

        // 表示ページ番号
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());

        // 総ページ数
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();
        l_response.totalPages = String.valueOf(l_intTotalPages);

        // 総レコード数
        l_response.totalRecords =
            String.valueOf(l_pageIndexInfo.getTotalRecords());

        // 　@２）設定後、レスポンス.総ページ数＝0 の場合は、
        // 　@レスポンス.サービス利用管理者外部連携ID一覧照会明細行
        // 　@(サービス利用管理者外部連携ID一覧照会明細行[ ])
        // 　@にnullをセットし例外をスローする。
        //
        // 【確定したサービス利用管理者外部連携ID一覧照会明細のうち、
        // 　@レスポンスに設定する明細を決める。】
        if (l_intTotalPages == 0)
        {
            l_response.otherOrgIdList = null;

            log.debug("サービス利用管理者外部連携ID一覧照会明細行を取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03053,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "サービス利用管理者外部連携ID一覧照会明細行を取得できません。");
        }

        // 　@　@１)　@(リクエスト.ページ内表示行数×(レスポンス.表示ページ番号-1)数分、
        // 　@　@　@　@　@確定したサービス利用管理者外部連携ID一覧照会明細の要素を
        // 　@　@　@　@　@スキップする。
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
        int l_intResponsePageIndex = Integer.parseInt(l_response.pageIndex);
        int l_intRolledCount = l_intRequestPageSize * (l_intResponsePageIndex - 1);
        int l_intValidRecordCount = l_adminSrvRegiOtherOrgIdGroups.length - l_intRolledCount;
        if (l_intValidRecordCount > l_intRequestPageSize)
        {
            l_intValidRecordCount = l_intRequestPageSize;
        }

        // 　@　@２)　@上記で決定したサービス利用管理者外部連携ID一覧照会明細の要素番号～
        // 　@　@　@　@(サービス利用管理者外部連携ID一覧照会明細の要素番号
        // 　@　@　@　@ ＋リクエスト.ページ内表示行数)
        // 　@　@　@　@までに該当するサービス利用管理者外部連携ID一覧照会明細を、
        // 　@　@　@　@レスポンスデータ.サービス利用管理者外部連携ID一覧照会明細行セットする。
        //
        // 【レスポンスの設定】
        // 　@○レスポンス.サービス利用管理者外部連携ID一覧照会明細行＝
        // 　@ページング制御を行って確定させたサービス利用管理者外部連携ID一覧照会明細の配列
        WEB3AdminSrvRegiOtherOrgIdGroup[] l_adminSrvRegiOtherOrgIdGroupsValidRecordCount =
            new WEB3AdminSrvRegiOtherOrgIdGroup[l_intValidRecordCount];

        for (int i = 0; i < l_intValidRecordCount; i++)
        {
            l_adminSrvRegiOtherOrgIdGroupsValidRecordCount[i] =
                l_adminSrvRegiOtherOrgIdGroups[l_intRolledCount + i];
        }

        l_response.otherOrgIdList = l_adminSrvRegiOtherOrgIdGroupsValidRecordCount;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
