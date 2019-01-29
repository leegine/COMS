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
filename	WEB3AdminIfoDepShortageReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・証拠金不足状況照会サービスImpl(WEB3AdminIfoDepShortageReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 李玉玲(中訊) 新規作成 モデルNo.005,No.009,No.010
Revision History : 2009/03/09 劉剣(中訊) モデルNo.012
Revision History : 2009/03/16 張騰宇(中訊) モデルNo.015,モデルNo.016
*/
package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.ifoadmin.WEB3AdminIfoDataManager;
import webbroker3.ifoadmin.define.WEB3AdminIfoCurNonPayAmtDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoSortKeyItemNameDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoUnCancelDivDef;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepAccountCodeComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepBranchCodeComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepClaimAmountComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepCurNonPayAmtComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepCurIfoDepositNecessaryAmtComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepContractExistFlagComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepOrderExistFlagComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceResponse;
import webbroker3.ifoadmin.message.WEB3IfoDepShortageInfo;
import webbroker3.ifoadmin.message.WEB3IfoDepShortageSortKey;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoDepShortageReferenceService;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcCondition;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・証拠金不足状況照会サービスImpl)<BR>
 * 管理者・証拠金不足状況照会サービス実装クラス<BR>
 * <BR>
 * @@author 李玉玲(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageReferenceServiceImpl implements WEB3AdminIfoDepShortageReferenceService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageReferenceServiceImpl.class);

    /**
     * @@roseuid 49A748560271
     */
    public WEB3AdminIfoDepShortageReferenceServiceImpl()
    {

    }

    /**
     * (get入力画面)<BR>
     * 管理者証拠金不足状況照会入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者証拠金不足状況照会サービス）get入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminIfoDepShortageRefInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 498FD76F00BF
     */
    protected WEB3AdminIfoDepShortageRefInputResponse getInputScreen(
        WEB3AdminIfoDepShortageRefInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminIfoDepShortageRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;

        //getInstanceFromログイン情報
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DEPOSIT_SHORTAGE_REFERENCE, false);

        //createResponse
        WEB3AdminIfoDepShortageRefInputResponse l_response =
            (WEB3AdminIfoDepShortageRefInputResponse)l_request.createResponse();

        //レスポンスデータに以下のプロパティをセットする。
        //検索日付一覧 ＝ 前営業日と当営業日の配列
        //当営業日：GtlUtils.getTradingSystem().getBizDate()
        //前営業日：営業日計算．roll()メソッドにて前営業日を取得する
        Date[] l_datList = new Date[2];

        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        l_datList[0] = l_bizDate.roll(-1);
        l_datList[1] = l_datBizDate;
        l_response.searchDateList = l_datList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get照会画面)<BR>
     * 管理者証拠金不足状況照会処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者証拠金不足状況照会サービス）get照会画面」参照<BR>
     * ===================================================<BR>
     * 　@シーケンス図　@:（管理者証拠金不足状況照会サービス）get照会画面<BR>
     * 　@具体位置　@　@　@: is証拠金不足メール送信済(String, String)<BR>
     * 　@　@　@　@　@　@　@リクエストデータ ．検索日付 が　@「当営業日」(*)　@＆＆　@<BR>
     * 　@　@　@　@　@　@　@is証拠金不足メール送信済（）の戻り値 == falseの場合<BR>
     * 　@　@　@　@　@　@　@「本日の証拠金不足はまだ確認していません。」の例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_03157<BR>
     * ===================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminIfoDepShortageReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 498FDBA80092
     */
    protected WEB3AdminIfoDepShortageReferenceResponse getReferenceScreen(
        WEB3AdminIfoDepShortageReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminIfoDepShortageReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();

        //getInstanceFromログイン情報
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DEPOSIT_SHORTAGE_REFERENCE, false);

        //get証券会社
        Institution l_institution = l_administrator.getInstitution();
        String l_strInstitutionCode = l_institution.getInstitutionCode();

        //validate部店権限(部店コード : String[])
        //[引数]
        //部店コード：　@リクエストデータ.部店コード一覧
        l_administrator.validateBranchPermission(l_request.branchCode);

        //当営業日：GtlUtils.getTradingSystem().getBizDate()
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //is証拠金不足メール送信済
        //[引数]
        //証券会社コード：　@get証券会社()の戻り値．証券会社コード
        //部店コード ：　@リクエストデータ.部店コード一覧[0]
        boolean l_blnIfoDepositMailFlag =
            WEB3AdminIfoDataManager.isIfoDepositMailFlag(l_strInstitutionCode, l_request.branchCode[0]);

        //リクエストデータ ．検索日付 が　@「当営業日」　@＆＆　@
        //is証拠金不足メール送信済（）の戻り値 == falseの場合
        //「本日の証拠金不足はまだ確認していません。」の例外をスローする
        if (WEB3DateUtility.compareToDay(l_datBizDate, l_request.searchDate) == 0 && !l_blnIfoDepositMailFlag)
        {
            log.debug("本日の証拠金不足はまだ確認していません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03157,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "本日の証拠金不足はまだ確認していません。");
        }

        //get証拠金情報一覧
        //[引数]
        // 証券会社：　@get証券会社()の戻り値
        // リクエストデータ：　@リクエストデータ
        IfoDepositRow[] l_ifoDepositRows =
            WEB3AdminIfoDataManager.getDepositInfoList(l_institution, l_request);

        //create証拠金不足状況一覧
        //[引数]
        //証券会社コード：get証券会社()の戻り値．証券会社コード
        //証拠金情報一覧：　@get証拠金情報一覧()の戻り値
        //リクエストデータ：リクエストデータ
        WEB3IfoDepShortageInfo[] l_ifoDepShortageInfos =
            this.createDepShortageList(l_strInstitutionCode, l_ifoDepositRows, l_request);

        //(*)create証拠金不足状況一覧の戻り値の要素数が0の場合
        //空のレスポンスを生成し、初期値をセットして返却
        //レスポンスデータ.表示日時 =  TradingSystem.getSystemTimestamp()
        if (l_ifoDepShortageInfos.length == 0)
        {
            //createResponse
            WEB3AdminIfoDepShortageReferenceResponse l_response =
                (WEB3AdminIfoDepShortageReferenceResponse)l_request.createResponse();

            l_response.dispDate = GtlUtils.getSystemTimestamp();
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //sort証拠金不足状況一覧
        //[引数]
        //証拠金不足状況一覧 ：create証拠金不足状況一覧()の戻り値
        //ソートキー配列：リクエストデータ.ソートキー
        WEB3IfoDepShortageInfo[] l_sortIfoDepShortageInfos =
            this.sortDepShortageList(l_ifoDepShortageInfos, l_request.sortKeys);

        //WEB3PageIndexInfo
        //l_objs：this.sort証拠金不足状況一覧()の戻り値
        //l_intRequestPageIndex：リクエストの要求ページ番号
        //l_intRequestPageSize：リクエストのページ内表示行数
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_sortIfoDepShortageInfos,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        //create注意情報一覧
        //createResponse
        WEB3AdminIfoDepShortageReferenceResponse l_response =
            (WEB3AdminIfoDepShortageReferenceResponse)l_request.createResponse();

        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());
        //getArrayReturned(l_classType : Class)
        //画面に表示する分の証拠金不足状況一覧の配列を取得する。
        l_response.ifoDepShortageInfos =
            (WEB3IfoDepShortageInfo[])l_pageIndexInfo.getArrayReturned(WEB3IfoDepShortageInfo.class);
        //レスポンスデータ.表示日時
        l_response.dispDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * 管理者・証拠金不足状況照会処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、 <BR>
     * 以下のメソッドを呼び分ける。 <BR>
     * <BR>
     * ○管理者・証拠金不足状況照会入力リクエストの場合 <BR>
     * 　@this.get入力画面()をコールする。 <BR>
     * <BR>
     * ○管理者・証拠金不足状況照会リクエストの場合 <BR>
     * 　@this.get照会画面()をコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 498FFDCF0285
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

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminIfoDepShortageRefInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminIfoDepShortageRefInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIfoDepShortageReferenceRequest)
        {
           l_response = this.getReferenceScreen((WEB3AdminIfoDepShortageReferenceRequest)l_request);
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
        return l_response;
    }

    /**
     * (create証拠金不足状況一覧)<BR>
     * 引数の証拠金情報一覧より、証拠金不足状況一覧を <BR>
     * 作成し、返却する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者証拠金不足状況照会サービス）create証拠金不足状況一覧」参照<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_depInfoList - (証拠金情報一覧)<BR>
     * 証拠金情報一覧<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3IfoDepShortageInfo[]
     * @@throws WEB3BaseException
     * @@roseuid 49912D2E02B9
     */
    protected WEB3IfoDepShortageInfo[] createDepShortageList(
        String l_strInstitutionCode,
        IfoDepositRow[] l_depInfoList,
        WEB3AdminIfoDepShortageReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createDepShortageList(String, IfoDepositRow[], WEB3AdminIfoDepShortageReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        ArrayList l_lisIfoDepShortageInfos = new ArrayList();

        //is証拠金不足メール送信済
        //[引数]
        //証券会社コード：　@引数．証券会社コード
        //部店コード ：　@リクエストデータ.部店コード一覧[0]
        boolean l_blnIfoDepositMailFlag =
            WEB3AdminIfoDataManager.isIfoDepositMailFlag(l_strInstitutionCode, l_request.branchCode[0]);

        //is清算値速報受信済
        //[引数]
        //証券会社コード：　@引数．証券会社コード
        //部店コード ：　@リクエストデータ.部店コード一覧[0]
        boolean l_blnIsQuickReportReceived =
            WEB3AdminIfoDataManager.isQuickReportReceived(l_strInstitutionCode, l_request.branchCode[0]);

        for (int i = 0; i < l_depInfoList.length; i++)
        {
            IfoDepositRow l_ifoDepositRow = l_depInfoList[i];

            //証拠金計算サービスを取得
            WEB3IfoDepositCalcService l_service =
                (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            //補助口座：　@処理対象の要素の証券会社コード，部店コード，
            //顧客コードに該当する顧客オブジェクト.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT)
            WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_ifoDepositRow.getInstitutionCode(),
                l_ifoDepositRow.getBranchCode(),
                l_ifoDepositRow.getAccountCode());
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //証拠金計算オブジェクトを取得
            WEB3IfoDepositCalc l_ifoDepCalc = l_service.getIfoDepositCalc(l_subAccount);

            //get証拠金計算条件( )
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition =
                l_ifoDepCalc.getIfoDepositCalcCondition();

            //get証拠金計算基準日( )
            int l_intIfoDepositBaseDate = l_ifoDepositCalcCondition.getIfoDepositBaseDate();

            //証拠金不足状況情報( )
            WEB3IfoDepShortageInfo l_ifoDepShortageInfo = new WEB3IfoDepShortageInfo();
            //部店コード                ＝  証拠金Row.部店コード
            l_ifoDepShortageInfo.branchCode = l_ifoDepositRow.getBranchCode();
            //顧客コード                ＝  証拠金Row.顧客コードの左6byte
            l_ifoDepShortageInfo.accountCode = l_ifoDepositRow.getAccountCode().substring(0, 6);
            //顧客名                    ＝　@getMainAccount().get顧客表示名()
            l_ifoDepShortageInfo.accountName = l_mainAccount.getDisplayAccountName();

            //請求額                    ＝  (*1)
            //リクエストデータ.検索日付が「当営業日」の場合、 calc翌日請求額( )の戻り値をセット
            //リクエストデータ.検索日付が「前営業日」の場合、get当日請求額( )の戻り値をセット
            //当営業日
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            if (WEB3DateUtility.compareToDay(l_datBizDate, l_request.searchDate) == 0)
            {
                //calc翌日請求額
                double l_dblNextBizDateDemandAmount = l_ifoDepCalc.calcNextBizDateDemandAmount();
                l_ifoDepShortageInfo.claimAmount = WEB3StringTypeUtility.formatNumber(l_dblNextBizDateDemandAmount);
            }

            //前営業日
            Date l_datBeforeBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(-1);
            if (WEB3DateUtility.compareToDay(l_datBeforeBizDate, l_request.searchDate) == 0)
            {
                //get当日請求額
                double l_dblCurrentBizDateDemandAmount = l_ifoDepCalc.getCurrentBizDateDemandAmount();
                l_ifoDepShortageInfo.claimAmount = WEB3StringTypeUtility.formatNumber(l_dblCurrentBizDateDemandAmount);
            }

            //現在未入金額              ＝  (*2)
            // （ is清算値速報受信済()== true && is証拠金不足メール送信済()== false）
            //　@|| (リクエストデータ.検索日付 が「前営業日」&& is証拠金不足メール送信済()==true)
            // 　@の場合は nullをセット
            // 上記以外の場合　@calc未入金額( )の戻り値をセット。
            if ((l_blnIsQuickReportReceived && !l_blnIfoDepositMailFlag)
                || (WEB3DateUtility.compareToDay(l_datBeforeBizDate, l_request.searchDate) == 0
                    && l_blnIfoDepositMailFlag))
            {
                l_ifoDepShortageInfo.curNonPayAmt = null;
            }
            else
            {
                //calc未入金額
                double l_dblNonPayAmount = l_ifoDepCalc.calcNonPayAmount();
                l_ifoDepShortageInfo.curNonPayAmt = WEB3StringTypeUtility.formatNumber(l_dblNonPayAmount);
            }

            //calc証拠金所要額
            double l_dblIfoDepositRequiredAmount = l_ifoDepCalc.calcIfoDepositRequiredAmount();
            //現在証拠金所要額          ＝　@calc証拠金所要額( )
            l_ifoDepShortageInfo.curIfoDepositNecessaryAmt =
                WEB3StringTypeUtility.formatNumber(l_dblIfoDepositRequiredAmount);

            //[a]------ calc買ポジション建玉()
            //[引数の設定]
            //指定日：　@get証拠金計算基準日( )の戻り値
            BigDecimal l_bdBuyContractQty =
                new BigDecimal(l_ifoDepCalc.calcBuyContractQty(l_intIfoDepositBaseDate) + "");

            // 　@[b]------ calc売ポジション建玉()
            //[引数の設定]
            //指定日：　@get証拠金計算基準日( )の戻り値
            BigDecimal l_bdSellContractQty =
                new BigDecimal(l_ifoDepCalc.calcSellContractQty(l_intIfoDepositBaseDate) + "");

            // 　@[c]------ calc注文中買ポジション建玉()
            //[引数の設定]
            //指定日：　@get証拠金計算基準日( )の戻り値
            BigDecimal l_bdBuyOrderQty =
                new BigDecimal(l_ifoDepCalc.calcBuyOrderQty(l_intIfoDepositBaseDate) + "");

            // 　@[d]------ calc注文中売ポジション建玉()
            //[引数の設定]
            //指定日：　@get証拠金計算基準日( )の戻り値
            BigDecimal l_bdSellOrderQty =
                new BigDecimal(l_ifoDepCalc.calcSellOrderQty(l_intIfoDepositBaseDate) + "");

            //建玉有無フラグ            ＝  (*3)
            //a-c >0またはb-d >0の場合　@trueをセット（※）
            //上記以外の場合　@falseをセット
            if (l_bdBuyContractQty.subtract(l_bdBuyOrderQty).doubleValue() > 0
                || l_bdSellContractQty.subtract(l_bdSellOrderQty).doubleValue() > 0)
            {
                l_ifoDepShortageInfo.contractExistFlag = true;
            }
            else
            {
                l_ifoDepShortageInfo.contractExistFlag = false;
            }

            //注文有無フラグ            ＝  (*4)
            //  c >0またはd >0の場合　@trueをセット（※）
            //上記以外の場合　@falseをセット
            if (l_bdBuyOrderQty.doubleValue() > 0 || l_bdSellOrderQty.doubleValue() > 0)
            {
                l_ifoDepShortageInfo.orderExistFlag = true;
            }
            else
            {
                l_ifoDepShortageInfo.orderExistFlag = false;
            }

            //（*）(リクエストデー タ．未解消客区分が[0：未解消客] &&
            // ( 現在未入金額 == 0  || 現在未入金額 == null ) ) 以外の場合
            if (!(WEB3AdminIfoUnCancelDivDef.UN_CANCEL.equals(l_request.uncancelDiv)
                && (l_ifoDepShortageInfo.curNonPayAmt == null
                    || WEB3AdminIfoCurNonPayAmtDef.ZERO.equals(l_ifoDepShortageInfo.curNonPayAmt))))
            {
                l_lisIfoDepShortageInfos.add(l_ifoDepShortageInfo);
            }
        }

        WEB3IfoDepShortageInfo[] l_ifoDepShortageInfos =
            new WEB3IfoDepShortageInfo[l_lisIfoDepShortageInfos.size()];
        l_lisIfoDepShortageInfos.toArray(l_ifoDepShortageInfos);

        log.exiting(STR_METHOD_NAME);
        return l_ifoDepShortageInfos;
    }

    /**
     * (sort証拠金不足状況一覧)<BR>
     * 指定されたソートキー、<BR>
     * 昇降順にもどついて証拠金不足状況一覧データのソートを行う。 <BR>
     * <BR>
     * １）ArrayListを生成する。 <BR>
     * <BR>
     * ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。 <BR>
     * 　@　@　@①@ソートキー.キー項目の値に対応する比較項目Comparatorを生成する。 <BR>
     * 　@　@　@　@　@　@[コンストラクタにセットするパラメータ] <BR>
     * 　@　@　@　@　@　@orderBy： ソートキー.昇順／降順 <BR>
     * <BR>
     * 　@　@　@②ArrayListに生成したComparatorを追加する。 <BR>
     * <BR>
     * ３）ArrayListからComparatorの配列を作成し、<BR>
     * WEB3ArraysUtility.sort()メソッドをコールする。 <BR>
     * <BR>
     * ４)ソートした証拠金不足状況一覧の配列を返却する。 <BR>
     * @@param l_sortDepShortageList - (証拠金不足状況一覧)<BR>
     * 証拠金不足状況一覧<BR>
     * @@param l_sortKeys - (ソートキー配列)<BR>
     * ソートキー配列<BR>
     * @@return WEB3IfoDepShortageInfo[]
     * @@roseuid 499907CC03D0
     */
    protected WEB3IfoDepShortageInfo[] sortDepShortageList(
        WEB3IfoDepShortageInfo[] l_sortDepShortageList, WEB3IfoDepShortageSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "sortDepShortageList(WEB3IfoDepShortageInfo[], WEB3IfoDepShortageSortKey[])";
        log.entering(STR_METHOD_NAME);

        //ArrayListを生成する。
        List l_lisSorts = new ArrayList();

        int l_intSortKeyLength = l_sortKeys.length;
        //パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            //①@ソートキー.キー項目の値に対応する比較項目Comparatorを生成する。
            //　@　@　@[コンストラクタにセットするパラメータ]
            //　@　@　@orderBy： ソートキー.昇順／降順
            //②ArrayListに生成したComparatorを追加する。
            String l_strKeyItem = l_sortKeys[i].keyItem;

            if (WEB3AdminIfoSortKeyItemNameDef.BRANCH_CODE.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepBranchCodeComparator l_comparator =
                    new WEB3AdminIfoDepBranchCodeComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.ACCOUNT_CODE.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepAccountCodeComparator l_comparator =
                    new WEB3AdminIfoDepAccountCodeComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.CLAIM_AMOUNT.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepClaimAmountComparator l_comparator =
                    new WEB3AdminIfoDepClaimAmountComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.CUR_NON_PAY_AMT.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepCurNonPayAmtComparator l_comparator =
                    new WEB3AdminIfoDepCurNonPayAmtComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.CUR_IFO_DEPOSIT_NECESSARY_AMT.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepCurIfoDepositNecessaryAmtComparator l_comparator =
                    new WEB3AdminIfoDepCurIfoDepositNecessaryAmtComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.CONTRACT_EXIST_FLAG.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepContractExistFlagComparator l_comparator =
                    new WEB3AdminIfoDepContractExistFlagComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.ORDER_EXIST_FLAG.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepOrderExistFlagComparator l_comparator =
                    new WEB3AdminIfoDepOrderExistFlagComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
        }

        //ArrayListからComparatorの配列を作成し、
        // WEB3ArraysUtility.sort()メソッドをコールする。
        Comparator[] l_comparatorList = new Comparator[l_lisSorts.size()];
        l_lisSorts.toArray(l_comparatorList);
        WEB3ArraysUtility.sort(l_sortDepShortageList, l_comparatorList);

        log.exiting(STR_METHOD_NAME);
        return l_sortDepShortageList;
    }
}
@
