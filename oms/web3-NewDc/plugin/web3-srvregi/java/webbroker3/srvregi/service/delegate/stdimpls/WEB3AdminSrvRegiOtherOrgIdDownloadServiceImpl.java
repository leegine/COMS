head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ(WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/20 周墨洋 (中訊) 新規作成・モデルNo.336, No.351, No.356
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdDownloadService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadResponse;
import webbroker3.srvregi.WEB3AdminSrvRegiOtherOrgIdDownloadCsv;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl)<BR>
 * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ実装クラス<BR>
 * <BR>
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl
    implements WEB3AdminSrvRegiOtherOrgIdDownloadService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl.class);

    /**
     * @@roseuid 47D1112F01D7
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl()
    {

    }

    /**
     * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞ処理を行う。<BR>
     * <BR>
     * １）　@業務日時設定<BR>
     * 　@取引時間管理.setBusinessTimestamp()をコールする。<BR>
     * <BR>
     * ２）　@getダウンロードファ@イル()をコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B943240129
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            String l_strErrorMessage = "パラメータ値不正。";
            log.debug(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １）　@業務日時設定
        //　@取引時間管理.setBusinessTimestamp()をコールする。
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

        // ２）　@getダウンロードファ@イル()をコールする。
        if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdDownloadRequest)
        {

            WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response =
                getDownloadFile(
                    (WEB3AdminSrvRegiOtherOrgIdDownloadRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            String l_strErrorMessage = "パラメータタイプ不正。";
            log.debug(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
    }

    /**
     * (getダウンロードファ@イル)<BR>
     * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞファ@イル処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(サービス利用)外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞ・getダウンロードファ@イル」参照<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B94328036B
     */
    protected WEB3AdminSrvRegiOtherOrgIdDownloadResponse getDownloadFile(
        WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDownloadFile(WEB3AdminSrvRegiOtherOrgIdDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // getInstanceFromログイン情報( )
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

        // 外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV( )
        // createカラムヘッダ( )
        WEB3AdminSrvRegiOtherOrgIdDownloadCsv l_adminSrvRegiOtherOrgIdDownloadCsv =
            new WEB3AdminSrvRegiOtherOrgIdDownloadCsv();

        int l_intLength = l_otherOrgInfoAdminParams.length;

        // ダウンロードファ@イル
        String[] l_strDownloadFiles = new String[l_intLength];

        // 取得した外部連携情報Paramsオブジェクトの配列
        // （get外部連携情報一覧()の戻り値）件数分のLOOP
        for (int i = 0; i < l_intLength; i++)
        {
            // add明細行( )
            int l_intLineNo = l_adminSrvRegiOtherOrgIdDownloadCsv.addRow();

            // set通番(int, long)
            // [引数]
            // 行番号　@　@       ：　@add明細行()の戻り値
            // 通番              ：　@外部連携情報Params[index].get通番()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setSequenceNumber(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getSequenceNumber() + "");

            // setID(int, String)
            // [引数]
            // 行番号　@　@       ：　@add明細行()の戻り値
            // ID                 ：　@外部連携情報Params[index].getID()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setId(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getId());

            // setステータス(int, String)
            // [引数]
            // 行番号　@　@       ：　@add明細行()の戻り値
            // ステータス         ：　@外部連携情報Params[index].getステータス()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setStatus(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getStatus());

            // set証券会社コード(int, String)
            // [引数]
            // 行番号　@　@       ：　@add明細行()の戻り値
            // 証券会社コード ：　@外部連携情報Params[index].get証券会社コード()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setInstitutionCode(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getInstitutionCode());

            // set部店コード(int, String)
            // [引数]
            // 行番号　@　@       ：　@add明細行()の戻り値
            // 部店コード       ：　@外部連携情報Params[index].get部店コード()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setBranchCode(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getBranchCode());

            // set口座コード(int, String)
            // [引数]
            // 行番号　@　@       ：　@add明細行()の戻り値
            // 口座コード       ：　@外部連携情報Params[index].get口座コード().substring(0,6)
            // 　@　@　@　@　@　@　@　@　@　@　@（7桁→6桁）
            if (l_otherOrgInfoAdminParams[i].getAccountCode() != null)
            {
                l_adminSrvRegiOtherOrgIdDownloadCsv.setAccountCode(
                    l_intLineNo,
                    l_otherOrgInfoAdminParams[i].getAccountCode().substring(0,6));
            }
            else
            {
                l_adminSrvRegiOtherOrgIdDownloadCsv.setAccountCode(
                    l_intLineNo,
                    null);
            }

            // set適用期間From(int, Date)
            // [引数]
            // 行番号　@　@       ：　@add明細行()の戻り値
            // 適用期間From  ：　@外部連携情報Params[index].get適用期間From()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setAppliStartDate(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getAppliStartDate());

            // set適用期間To(int, Date)
            // [引数]
            // 行番号　@　@       ：　@add明細行()の戻り値
            // 適用期間To     ：　@外部連携情報Params[index].get適用期間To()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setAppliEndDate(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getAppliEndDate());
        }

        // getCSVファ@イル行( )
        l_strDownloadFiles = l_adminSrvRegiOtherOrgIdDownloadCsv.getCsvFileLines();

        // サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ( )
        //[コンストラクタの引数]
        // l_request：　@リクエストデータ
        WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdDownloadResponse)l_request.createResponse();

        // プロパティセット
        // ダウンロードファ@イル
        l_response.lines = l_strDownloadFiles;

        // 現在日時
        l_response.currentDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
