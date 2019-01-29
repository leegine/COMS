head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・証拠金不足状況ダウンロードサービスImpl(WEB3AdminIfoDepShortageDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27　@劉剣(中訊) 新規作成 モデルNo.006
Revision History : 2009/03/09　@劉剣(中訊) 新規作成 モデルNo.014
Revision History : 2009/04/10　@張騰宇(中訊) モデルNo.017
*/
package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.ifoadmin.WEB3AdminIfoDataManager;
import webbroker3.ifoadmin.WEB3AdminIfoDepShortageDownloadCsv;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageDownloadRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageDownloadResponse;
import webbroker3.ifoadmin.message.WEB3IfoDepShortageInfo;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoDepShortageDownloadService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・証拠金不足状況ダウンロードサービスImpl)<BR>
 * 管理者・証拠金不足状況ダウンロードサービス実装クラス<BR>
 * <BR>
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageDownloadServiceImpl extends WEB3AdminIfoDepShortageReferenceServiceImpl
    implements WEB3AdminIfoDepShortageDownloadService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageDownloadServiceImpl.class);

    /**
     * @@roseuid 49A76E52003E
     */
    public WEB3AdminIfoDepShortageDownloadServiceImpl()
    {

    }

    /**
     * 管理者・証拠金不足状況ダウンロードサービス処理を行う。 <BR>
     * <BR>
     * 以下のメソッドをコールする。 <BR>
     * <BR>
     * getダウンロードファ@イル() <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 499B92EA01BE
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

        if (!(l_request instanceof WEB3AdminIfoDepShortageDownloadRequest))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        WEB3AdminIfoDepShortageDownloadRequest l_depShortageDownloadRequest =
            (WEB3AdminIfoDepShortageDownloadRequest)l_request;

        //getダウンロードファ@イル()
        WEB3AdminIfoDepShortageDownloadResponse l_response =
            this.getDownloadFile(l_depShortageDownloadRequest);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getダウンロードファ@イル)<BR>
     * ダウンロードファ@イル取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者・証拠金不足状況ダウンロード）getダウンロードファ@イル」 参照 <BR>
     * ===================================================<BR>
     * 　@シーケンス図 : (管理者・証拠金不足状況ダウンロードサービス)getダウンロードファ@イル<BR>
     * 　@具体位置 : is証拠金不足メール送信済(String, String)<BR>
     * 　@　@　@　@　@　@リクエストデータ ．検索日付 が　@「当営業日」(*)　@＆＆　@<BR>
     * 　@　@　@　@　@　@is証拠金不足メール送信済（）の戻り値 == falseの場合<BR>
     * 　@　@　@　@　@　@「本日の証拠金不足はまだ確認していません。」の例外をスローする。<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag : BUSINESS_ERROR_03157<BR>
     * ===================================================<BR>
     * ===================================================<BR>
     * 　@シーケンス図 : (管理者・証拠金不足状況ダウンロードサービス)getダウンロードファ@イル<BR>
     * 　@具体位置 : create証拠金不足状況一覧の戻り値の要素数が0の場合<BR>
     * 　@　@　@　@　@　@「条件に該当する証拠金不足状況情報がありません」の例外をthrowする<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag : BUSINESS_ERROR_03158<BR>
     * ===================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminIfoDepShortageDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 499B94240224
     */
    protected WEB3AdminIfoDepShortageDownloadResponse getDownloadFile(
        WEB3AdminIfoDepShortageDownloadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminIfoDepShortageDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード：　@機@能カテゴリコード.証拠金不足状況照会
        //is更新：　@false（更新なし）
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DEPOSIT_SHORTAGE_REFERENCE, false);

        //get証券会社()
        Institution l_institution = l_admin.getInstitution();

        //validate部店権限(部店コード : String[])
        //[引数]
        //部店コード：　@リクエストデータ.部店コード一覧
        l_admin.validateBranchPermission(l_request.branchCode);

        //is証拠金不足メール送信済(String, String)
        //[引数]
        //証券会社コード：　@get証券会社()の戻り値．証券会社コード
        //部店コード ：　@リクエストデータ.部店コード一覧[0]
        boolean l_blnIfoDepositMailFlag =
            WEB3AdminIfoDataManager.isIfoDepositMailFlag(
                l_institution.getInstitutionCode(),
                l_request.branchCode[0]);

        //当営業日
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        //リクエストデータ ．検索日付 が　@「当営業日」(*)　@＆＆
        //is証拠金不足メール送信済（）の戻り値 == falseの場合
        //「本日の証拠金不足はまだ確認していません。」の例外をスローする。
        if (WEB3DateUtility.compareToDay(l_request.searchDate, l_datBizDate) == 0 && !l_blnIfoDepositMailFlag)
        {
            log.debug("本日の証拠金不足はまだ確認していません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03157,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "本日の証拠金不足はまだ確認していません。");
        }

        //get証拠金情報一覧(証券会社, 管理者・証拠金不足状況照会リクエスト)
        //[引数]
        //証券会社：　@get証券会社()の戻り値
        //リクエストデータ：　@証拠金不足状況ダウンロードリクエスト
        IfoDepositRow[] l_ifoDepositRows =
            WEB3AdminIfoDataManager.getDepositInfoList(l_institution, l_request);

        //create証拠金不足状況一覧(String, 証拠金Row[], 管理者・証拠金不足状況照会リクエスト)
        //[引数]
        //証券会社コード：get証券会社()の戻り値．証券会社コード
        //証拠金情報一覧：　@get証拠金情報一覧()の戻り値
        //リクエストデータ：証拠金不足状況ダウンロードリクエスト
        WEB3IfoDepShortageInfo[] l_ifoDepShortageInfos =
            this.createDepShortageList(
                l_institution.getInstitutionCode(),
                l_ifoDepositRows,
                l_request);

        //(*)create証拠金不足状況一覧の戻り値の要素数が0の場合
        if (l_ifoDepShortageInfos.length == 0)
        {
            log.debug("条件に該当する証拠金不足状況情報がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03158,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当する証拠金不足状況情報がありません。");
        }

        //sort証拠金不足状況一覧(証拠金不足状況情報[], 証拠金不足状況ソートキー[])
        //[引数]
        //証拠金不足状況一覧 ：create証拠金不足状況一覧()の戻り値
        //ソートキー配列：リクエストデータ.ソートキー
        WEB3IfoDepShortageInfo[] l_sortIfoDepShortageInfos =
            this.sortDepShortageList(l_ifoDepShortageInfos, l_request.sortKeys);

        //証拠金不足状況ダウンロードCSV()
        WEB3AdminIfoDepShortageDownloadCsv l_depShortageDownloadCsv =
            new WEB3AdminIfoDepShortageDownloadCsv(l_request.searchDate);

        //(*)sort証拠金不足状況一覧の戻り値の要素数分、Loop処理
        int l_intLength = l_sortIfoDepShortageInfos.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //add明細行()
            int l_intAddRow = l_depShortageDownloadCsv.addRow();

            //set部店コード(int, String)
            //[引数]
            //行番号： add明細行()の戻り値
            //部店コード：sort証拠金不足状況一覧の戻り値[index].部店コード
            l_depShortageDownloadCsv.setBranchCode(
                l_intAddRow, l_sortIfoDepShortageInfos[i].branchCode);

            //set顧客コード(int, String)
            //[引数]
            //行番号： add明細行()の戻り値
            //顧客コード：sort証拠金不足状況一覧の戻り値[index].顧客コード
            l_depShortageDownloadCsv.setAccountCode(
                l_intAddRow, l_sortIfoDepShortageInfos[i].accountCode);

            //set顧客名(int, String)
            //[引数]
            //行番号： add明細行()の戻り値
            //顧客名：　@sort証拠金不足状況一覧の戻り値[index].顧客名
            l_depShortageDownloadCsv.setAccountName(
                l_intAddRow, l_sortIfoDepShortageInfos[i].accountName);

            //set請求額(int, String)
            //[引数]
            //行番号： add明細行()の戻り値
            //請求額：　@sort証拠金不足状況一覧の戻り値[index].請求額
            l_depShortageDownloadCsv.setClaimAmount(
                l_intAddRow, l_sortIfoDepShortageInfos[i].claimAmount);

            //set現在未入金額(int, String)
            //[引数]
            //行番号： add明細行()の戻り値
            //現在未入金額：　@sort証拠金不足状況一覧の戻り値[index].現在未入金額
            l_depShortageDownloadCsv.setCurNonPayAmt(
                l_intAddRow, l_sortIfoDepShortageInfos[i].curNonPayAmt);

            //set現在証拠金所要額(int, String)
            //[引数]
            //行番号： add明細行()の戻り値
            //現在証拠金所要額：　@sort証拠金不足状況一覧の戻り値[index].現在証拠金所要額
            l_depShortageDownloadCsv.setCurIfoDepositNecessaryAmt(
                l_intAddRow, l_sortIfoDepShortageInfos[i].curIfoDepositNecessaryAmt);

            //set建玉有無フラグ(int, boolean)
            //[引数]
            //行番号： add明細行()の戻り値
            //建玉有無フラグ：　@sort証拠金不足状況一覧の戻り値[index].建玉有無フラグ
            l_depShortageDownloadCsv.setContractExistFlag(
                l_intAddRow, l_sortIfoDepShortageInfos[i].contractExistFlag);

            //set注文有無フラグ(int, boolean)
            //[引数]
            //行番号： add明細行()の戻り値
            //注文有無フラグ：　@sort証拠金不足状況一覧の戻り値[index].注文有無フラグ
            l_depShortageDownloadCsv.setOrderExistFlag(
                l_intAddRow, l_sortIfoDepShortageInfos[i].orderExistFlag);
        }

        //getCSVファ@イル行()
        String[] l_strCsvFileLines = l_depShortageDownloadCsv.getCsvFileLines();

        //createResponse()
        WEB3AdminIfoDepShortageDownloadResponse l_response =
            (WEB3AdminIfoDepShortageDownloadResponse)l_request.createResponse();

        //(*)プロパティセット
        l_response.downloadFile = l_strCsvFileLines;
        l_response.dispDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
