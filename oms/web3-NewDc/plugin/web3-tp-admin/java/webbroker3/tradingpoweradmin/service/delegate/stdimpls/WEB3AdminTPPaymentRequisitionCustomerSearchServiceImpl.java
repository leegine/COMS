head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索サービスImpl(WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/06 安陽(中訊) 新規作成 モデルNo.027
Revision History : 2008/10/10 安陽(中訊) 仕様変更 モデルNo.029 030
Revision History : 2008/10/14 安陽(中訊) 仕様変更 モデルNo.031 033
Revision History : 2008/10/17 安陽(中訊) 仕様変更 モデルNo.038
Revision History : 2008/10/27 安陽(中訊) 仕様変更 モデルNo.041
Revision History : 2008/11/05 劉剣(中訊) 仕様変更 モデルNo.043 044
*/

package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountAttributeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPPaymentRequisitionAccountDetailInfo;
import webbroker3.tradingpower.WEB3TPSecondAdddepositInfo;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.data.PaymentRequisitMngRow;
import webbroker3.tradingpower.service.delegate.WEB3TPPaymentRequisitionManageService;
import webbroker3.tradingpoweradmin.WEB3AdminTPPaymentRequisitionCustomerSearchCSV;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPClaimReasonDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPCustomerAttributeDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPDaysDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPShortfallGenerationStateDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPaAdditionalGenerationStateDivDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFirstAdditionalInfo;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionCommonRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListUnit;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPSecondAdditionalInfo;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPShortfallGenerationInfo;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionCustomerSearchService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (入金請求顧客検索サービスImpl)<BR>
 * （入金請求顧客検索サービスImpl）<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl extends WEB3ClientRequestService
    implements WEB3AdminTPPaymentRequisitionCustomerSearchService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class);

    /**
     * @@roseuid 48E9E2520249
     */
    public WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * <BR>
     * シーケンス図「execute」参照<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 48E9E25202C6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        //入金請求顧客検索入力リクエストであった場合
        if (l_request instanceof WEB3AdminTPPaymentRequisitionInputRequest)
        {
            //get入金請求顧客検索入力(入金請求顧客検索入力リクエスト)
            l_response = getPaymentRequisitionCustomerSearchInput(
                (WEB3AdminTPPaymentRequisitionInputRequest)l_request);
        }
        //入金請求顧客検索一覧リクエストであった場合
        else if (l_request instanceof WEB3AdminTPPaymentRequisitionListRequest)
        {
            //get入金請求顧客検索一覧(入金請求顧客検索一覧リクエスト)
            l_response = getPaymentRequisitionCustomerSearchList(
                (WEB3AdminTPPaymentRequisitionListRequest)l_request);
        }
        //入金請求顧客検索詳細リクエストであった場合
        else if (l_request instanceof WEB3AdminTPPaymentRequisitionDetailRequest)
        {
            //get入金請求顧客検索詳細(入金請求顧客検索詳細リクエスト)
            l_response = getPaymentRequisitionCustomerSearchDetail(
                (WEB3AdminTPPaymentRequisitionDetailRequest)l_request);
        }
        //入金請求顧客検索ダウンロードリクエストであった場合
        else if (l_request instanceof WEB3AdminTPPaymentRequisitionDownLoadRequest)
        {
            //get入金請求顧客検索ダウンロード(入金請求顧客検索ダウンロードリクエスト)
            l_response = getPaymentRequisitionCustomerSearchDownLoad(
                (WEB3AdminTPPaymentRequisitionDownLoadRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入金請求顧客検索入力)<BR>
     * (get入金請求顧客検索入力)<BR>
     * <BR>
     * シーケンス図「get入金請求顧客検索入力」参照<BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3AdminTPPaymentRequisitionInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C9FF830140
     */
    private WEB3AdminTPPaymentRequisitionInputResponse getPaymentRequisitionCustomerSearchInput(
        WEB3AdminTPPaymentRequisitionInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchInput(WEB3AdminTPPaymentRequisitionInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックを行う。
        //管理者権限チェック
        //・カテゴリコード="A0201"（余力管理者）
        //・更新フラグ=false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.TRADING_POWER, false);

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTPPaymentRequisitionInputResponse l_response =
            (WEB3AdminTPPaymentRequisitionInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入金請求顧客検索一覧)<BR>
     * (get入金請求顧客検索一覧)<BR>
     * <BR>
     * シーケンス図「get入金請求顧客検索一覧」参照<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3AdminTPPaymentRequisitionListResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C9D243037A
     */
    private WEB3AdminTPPaymentRequisitionListResponse getPaymentRequisitionCustomerSearchList(
        WEB3AdminTPPaymentRequisitionListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchList(WEB3AdminTPPaymentRequisitionListRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックを行う。
        //管理者権限チェック
        //・カテゴリコード="A0201"（余力管理者）
        //・更新フラグ=false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.TRADING_POWER, false);

        //get入金請求顧客情報Params一覧(入金請求顧客検索共通リクエスト)
        //入金請求管理テーブルのデータを取得する。
        //[引数]
        //・リクエストデータ
        List l_lisPaymentRequisitMngParams = getPaymentRequisitMngParamsList(l_request);

        //WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //ページ処理クラスのインスタンス生成
        //・get入金請求顧客情報Params一覧()の戻り値
        //・リクエストデータ.要求ページ番号
        //・リクエストデータ.ページ内表示行数
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisPaymentRequisitMngParams,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        List l_lisUnits = new ArrayList();
        Object[] l_objUnits = l_pageIndexInfo.getArrayReturned();
        for (int i = 0; i < l_objUnits.length; i++)
        {
            l_lisUnits.add(l_objUnits[i]);
        }

        PaymentRequisitMngParams[] l_paymentRequisitMngParams =
            new PaymentRequisitMngParams[l_lisUnits.size()];
        l_lisUnits.toArray(l_paymentRequisitMngParams);

        //create入金請求顧客検索一覧ユニット(入金請求管理Params[])
        //入金請求顧客検索一覧ユニットにプロパティをセットする。
        //[引数]
        //・　@WEB3PageIndexInfoの戻り値.getArrayReturned()
        WEB3AdminTPPaymentRequisitionListUnit[] l_paymentRequisitionListUnits =
            createPaymentRequisitionListUnit(l_paymentRequisitMngParams);

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTPPaymentRequisitionListResponse l_response =
            (WEB3AdminTPPaymentRequisitionListResponse)l_request.createResponse();

        //レスポンスデータにプロパティをセットする。
        //※レコードが０件の場合は「前営業日」をセットする。
        if (l_lisPaymentRequisitMngParams.size() == 0)
        {
            Timestamp l_tsSysTimestamp = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsSysTimestamp);
            l_response.calcDate = l_genBizDate.roll(-1);
        }
        else
        {
            //計算日         ＝　@get入金請求顧客情報Params一覧()の戻り値の１レコード目の計算日
            l_response.calcDate = WEB3DateUtility.toDay(
                ((PaymentRequisitMngParams)l_lisPaymentRequisitMngParams.get(0)).getCalcDate());
        }
        //請求事由            ＝　@リクエストデータ.請求事由
        l_response.claimReason = l_request.claimReason;
        //日数          ＝　@リクエストデータ.日数
        l_response.days = l_request.days;
        //総ページ数       ＝　@WEB3PageIndexInfoの戻り値.getTotalPages()
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        //表示ページ番号     ＝　@WEB3PageIndexInfoの戻り値.getPageIndex()
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        //総レコード数      ＝　@WEB3PageIndexInfoの戻り値.getTotalRecords()
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        //入金請求顧客検索情報一覧    ＝　@create入金請求顧客検索一覧ユニット()の戻り値の配列
        l_response.paymentRequisitionListUnit = l_paymentRequisitionListUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入金請求顧客検索詳細)<BR>
     * (get入金請求顧客検索詳細)<BR>
     * <BR>
     * シーケンス図「get入金請求顧客検索詳細」参照<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3AdminTPPaymentRequisitionDetailResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C9D3260171
     */
    private WEB3AdminTPPaymentRequisitionDetailResponse getPaymentRequisitionCustomerSearchDetail(
        WEB3AdminTPPaymentRequisitionDetailRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchDetail(WEB3AdminTPPaymentRequisitionDetailRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックを行う。
        //管理者権限チェック
        //・カテゴリコード="A0201"（余力管理者）
        //・更新フラグ=false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.TRADING_POWER, false);

        //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
        //顧客オブジェクトを取得する。
        //[引数]
        //証券会社コード = セッションの.証券会社コード
        //部店コード = リクエストデータ.部店コード
        //顧客コード = リクエストデータ.顧客コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_mainAccount =
            l_gentradeAccountManager.getMainAccount(
                l_administrator.getInstitutionCode(),
                l_request.branchCode,
                l_request.accountCode);

        //get入金請求顧客詳細情報(顧客 : 顧客)
        //該当する顧客の入金請求顧客情報を取得する。
        //[引数]
        //get顧客()
        WEB3TPPaymentRequisitionManageService l_paymentRequisitionManageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(
                WEB3TPPaymentRequisitionManageService.class);

        WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
            l_paymentRequisitionManageService.getPaymentRequisitionAccountDetailInfo(l_mainAccount);

        //getAccountId( )
        //取得した顧客のアカウントIDを取得する。
        long l_lngAccountId = l_mainAccount.getAccountId();

        //get顧客余力条件Params(Long)
        //取得した顧客.口座IDを基に顧客余力条件を取得する。
        //[引数]
        //・getAccountId()で取得したアカウントID
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            getTradingPowerCalcConditionParams(new Long(l_lngAccountId));

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTPPaymentRequisitionDetailResponse l_response =
            (WEB3AdminTPPaymentRequisitionDetailResponse)l_request.createResponse();

        //インスタンス生成（不足金発生情報）
        //不足金発生情報オブジェクトのインスタンスを生成する。
        WEB3AdminTPShortfallGenerationInfo l_shortfallGenerationInfo =
            new WEB3AdminTPShortfallGenerationInfo();

        //プロパティセット（不足金発生情報）
        //get入金請求顧客詳細情報()で取得した
        //入金請求顧客詳細情報.不足金発生情報の各項目値を
        //生成した不足金発生情報にセットする。
        //１）不足金発生情報のセット
        //不足金発生情報.期日(T+0) = get入金請求顧客詳細情報.不足金発生情報.期日(T+0)
        l_shortfallGenerationInfo.closeDate0 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate0);
        //不足金発生情報.期日(T+1) = get入金請求顧客詳細情報.不足金発生情報.期日(T+1)
        l_shortfallGenerationInfo.closeDate1 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate1);
        //不足金発生情報.期日(T+2) = get入金請求顧客詳細情報.不足金発生情報.期日(T+2)
        l_shortfallGenerationInfo.closeDate2 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate2);
        //不足金発生情報.期日(T+3) = get入金請求顧客詳細情報.不足金発生情報.期日(T+3)
        l_shortfallGenerationInfo.closeDate3 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate3);
        //不足金発生情報.期日(T+4) = get入金請求顧客詳細情報.不足金発生情報.期日(T+4)
        l_shortfallGenerationInfo.closeDate4 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate4);
        //不足金発生情報.期日(T+5) = get入金請求顧客詳細情報.不足金発生情報.期日(T+5)
        l_shortfallGenerationInfo.closeDate5 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate5);
        //不足金発生情報.必要入金額(T+0) = get入金請求顧客詳細情報.不足金発生情報.必要入金額(T+0)
        l_shortfallGenerationInfo.requiredPayAmt0 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt0);
        //不足金発生情報.必要入金額(T+1) = get入金請求顧客詳細情報.不足金発生情報.必要入金額(T+1)
        l_shortfallGenerationInfo.requiredPayAmt1 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt1);
        //不足金発生情報.必要入金額(T+2) = get入金請求顧客詳細情報.不足金発生情報.必要入金額(T+2)
        l_shortfallGenerationInfo.requiredPayAmt2 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt2);
        //不足金発生情報.必要入金額(T+3) = get入金請求顧客詳細情報.不足金発生情報.必要入金額(T+3)
        l_shortfallGenerationInfo.requiredPayAmt3 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt3);
        //不足金発生情報.必要入金額(T+4) = get入金請求顧客詳細情報.不足金発生情報.必要入金額(T+4)
        l_shortfallGenerationInfo.requiredPayAmt4 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt4);
        //不足金発生情報.必要入金額(T+5) = get入金請求顧客詳細情報.不足金発生情報.必要入金額(T+5)
        l_shortfallGenerationInfo.requiredPayAmt5 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt5);
        //不足金発生情報.日計り拘束金(T+0) = get入金請求顧客詳細情報.不足金発生情報.日計り拘束金(T+0)
        l_shortfallGenerationInfo.dayTradeRestraint0 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().dayTradeRestraint0);
        //不足金発生情報.日計り拘束金(T+1) = get入金請求顧客詳細情報.不足金発生情報.日計り拘束金(T+1)
        l_shortfallGenerationInfo.dayTradeRestraint1 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().dayTradeRestraint1);
        //不足金発生情報.保証金からの振替額(T+0) = get入金請求顧客詳細情報.不足金発生情報.保証金からの振替額(T+0)
        l_shortfallGenerationInfo.transferFromMarginDeposit0 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().transferFromMarginDeposit0);
        //不足金発生情報.保証金からの振替額(T+1) = get入金請求顧客詳細情報.不足金発生情報.保証金からの振替額(T+1)
        l_shortfallGenerationInfo.transferFromMarginDeposit1 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().transferFromMarginDeposit1);
        //不足金発生情報.精算額(T+0) = get入金請求顧客詳細情報.不足金発生情報.精算額(T+0)
        l_shortfallGenerationInfo.adjustedAmt0 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().adjustedAmt0);
        //不足金発生情報.精算額(T+1) = get入金請求顧客詳細情報.不足金発生情報.精算額(T+1)
        l_shortfallGenerationInfo.adjustedAmt1 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().adjustedAmt1);

        //get入金請求顧客詳細情報().顧客属性 = 2(信用) の場合
        if (WEB3AccountAttributeDef.MARGIN.equals(
            l_paymentRequisitionAccountDetailInfo.getAccountAttribute()))
        {
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
                l_paymentRequisitionAccountDetailInfo.getAdddepositGenerationInfo();

            //get入金請求顧客詳細情報().入金請求顧客詳細情報.追証発生情報 != null and
            //get入金請求顧客詳細情報().入金請求顧客詳細情報.追証発生情報.追証情報 != nullの場合
            if (l_adddepositGenerationInfo != null
                && l_adddepositGenerationInfo.getAdddepositInfo() != null)
            {
                //get入金請求顧客詳細情報().追証発生情報.追証情報が第一水準追証情報のインスタンスの場合
                if (l_adddepositGenerationInfo.getAdddepositInfo() instanceof WEB3TPFirstAdddepositInfo)
                {
                    WEB3TPFirstAdddepositInfo l_firstAdddepositInfo =
                        (WEB3TPFirstAdddepositInfo)l_adddepositGenerationInfo.getAdddepositInfo();

                    //インスタンス生成（第一水準追証情報）
                    //第一水準追証情報オブジェクトのインスタンスを生成する。
                    WEB3AdminTPFirstAdditionalInfo l_firstAdditionalInfo = new WEB3AdminTPFirstAdditionalInfo();

                    //プロパティセット（第一水準追証情報）
                    //get入金請求顧客詳細情報()で取得した
                    //入金請求顧客詳細情報.第一水準追証情報の各項目値を
                    //生成した第一水準追証情報とレスポンスデータにセットする。

                    //１）第一水準追証情報のセット
                    //第一水準追証情報.経過日数 = get入金請求顧客詳細情報.第一水準追証情報.経過日数
                    l_firstAdditionalInfo.firstDepositPassDay =
                        l_firstAdddepositInfo.firstDepositPassDay + "";
                    //第一水準追証情報.有効経過日数 = get入金請求顧客詳細情報.第一水準追証情報.有効経過日数
                    l_firstAdditionalInfo.firstDepositPassDayValid =
                        l_firstAdddepositInfo.firstDepositPassDayValid + "";
                    //第一水準追証情報.発生日 = get入金請求顧客詳細情報.第一水準追証情報.発生日
                    l_firstAdditionalInfo.firstDepositOccurredDate =
                        WEB3DateUtility.toDay(l_firstAdddepositInfo.firstDepositOccurredDate);
                    //第一水準追証情報.保証金率 = get入金請求顧客詳細情報.第一水準追証情報.保証金率
                    l_firstAdditionalInfo.firstMarginDepositRate =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstMarginDepositRate);
                    //第一水準追証情報.保証金維持率 = get入金請求顧客詳細情報.第一水準追証情報.保証金維持率
                    l_firstAdditionalInfo.firstDepositRate =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstDepositRate);
                    //第一水準追証情報.追証金額 = get入金請求顧客詳細情報.第一水準追証情報.追証金額
                    l_firstAdditionalInfo.firstDepositAmount =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstDepositAmount);
                    //第一水準追証情報.追証決済必要額 = get入金請求顧客詳細情報.第一水準追証情報.追証決済必要額
                    l_firstAdditionalInfo.firstSettlement =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstSettlement);
                    //第一水準追証情報.保証金増減 = get入金請求顧客詳細情報.第一水準追証情報.保証金増減
                    l_firstAdditionalInfo.firstMarginDepositInDe =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstMarginDepositInDe);
                    //第一水準追証情報.保証金増減(見込金額) = get入金請求顧客詳細情報.第一水準追証情報.保証金増減(見込金額)
                    l_firstAdditionalInfo.firstMarginDepositInDeExpect =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstMarginDepositInDeExpect);
                    //第一水準追証情報.決済済建玉 = get入金請求顧客詳細情報.第一水準追証情報.決済済建玉
                    l_firstAdditionalInfo.firstSettledContract =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstSettledContract);
                    //第一水準追証情報.未解消金額 = get入金請求顧客詳細情報.第一水準追証情報.未解消金額
                    l_firstAdditionalInfo.firstUncancelAmt =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstUncancelAmt);
                    //第一水準追証情報.未解消決済必要額 = get入金請求顧客詳細情報.第一水準追証情報.未解消決済必要額
                    l_firstAdditionalInfo.firstUncancelSettleRequiredAmt =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstUncancelSettleRequiredAmt);

                    //２）レスポンスデータのセット
                    //レスポンスデータ.追証発生状況 = 1
                    l_response.additionalGenerationStateDiv =
                        WEB3AdminTPaAdditionalGenerationStateDivDef.FIRST_LEVEL_OCCUR;
                    //レスポンスデータ.第一水準追証情報 = 上記１）でプロパティセットした第一水準追証情報
                    l_response.firstAdditionalInfo = l_firstAdditionalInfo;
                    //レスポンスデータ.第二水準追証情報 = null
                    l_response.secondAdditionalInfo = null;
                }

                //get入金請求顧客詳細情報().追証発生情報.追証情報が第二水準追証情報のインスタンスの場合
                else if (l_adddepositGenerationInfo.getAdddepositInfo() instanceof WEB3TPSecondAdddepositInfo)
                {
                    WEB3TPSecondAdddepositInfo l_secondAdddepositInfo =
                        (WEB3TPSecondAdddepositInfo)l_adddepositGenerationInfo.getAdddepositInfo();

                    //インスタンス生成（第二水準追証情報）
                    //第二水準追証情報オブジェクトのインスタンスを生成する。
                    WEB3AdminTPSecondAdditionalInfo l_secondAdditionalInfo = new WEB3AdminTPSecondAdditionalInfo();

                    //get入金請求顧客詳細情報()で取得した
                    //入金請求顧客詳細情報.第二水準追証情報の各項目値を
                    //生成した第二水準追証情報とレスポンスデータにセットする。
                    //１）第二水準追証情報のセット
                    //第二水準追証情報.期日(請求2) = get入金請求顧客詳細情報.第二水準追証情報.期日(請求2)
                    String l_strSecondCloseDate2 =
                        WEB3DateUtility.formatDate(
                            l_secondAdddepositInfo.secondCloseDate2,
                            WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    l_secondAdditionalInfo.closeDate2 =
                        WEB3DateUtility.getDate(l_strSecondCloseDate2,
                            WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    //第二水準追証情報.期日(請求1) = get入金請求顧客詳細情報.第二水準追証情報.期日(請求1)
                    String l_strSecondCloseDate1 =
                        WEB3DateUtility.formatDate(
                            l_secondAdddepositInfo.secondCloseDate1,
                            WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    l_secondAdditionalInfo.closeDate1 =
                        WEB3DateUtility.getDate(l_strSecondCloseDate1,
                        WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    //第二水準追証情報.期日(請求見込) = get入金請求顧客詳細情報.第二水準追証情報.期日(請求見込)
                    String l_strSecondCloseDateExpect =
                        WEB3DateUtility.formatDate(
                            l_secondAdddepositInfo.secondCloseDateExpect,
                            WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    l_secondAdditionalInfo.closeDateExpect =
                        WEB3DateUtility.getDate(l_strSecondCloseDateExpect,
                        WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    //第二水準追証情報.発生日(請求2) = get入金請求顧客詳細情報.第二水準追証情報.発生日(請求2)
                    l_secondAdditionalInfo.secondDepositOccurredDate2 =
                        WEB3DateUtility.toDay(l_secondAdddepositInfo.secondDepositOccurredDate2);
                    //第二水準追証情報.発生日(請求1) = get入金請求顧客詳細情報.第二水準追証情報.発生日(請求1)
                    l_secondAdditionalInfo.secondDepositOccurredDate1 =
                        WEB3DateUtility.toDay(l_secondAdddepositInfo.secondDepositOccurredDate1);
                    //第二水準追証情報.発生日(請求見込) = get入金請求顧客詳細情報.第二水準追証情報.発生日(請求見込)
                    l_secondAdditionalInfo.secondDepositOccurredDateExpect =
                        WEB3DateUtility.toDay(l_secondAdddepositInfo.secondDepositOccurredDateExpect);
                    //第二水準追証情報.保証金維持率 = get入金請求顧客詳細情報.第二水準追証情報.保証金維持率
                    l_secondAdditionalInfo.secondDepositRate =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondDepositRate);
                    //第二水準追証情報.保証金戻し維持率 = get入金請求顧客詳細情報.第二水準追証情報.保証金戻し維持率
                    l_secondAdditionalInfo.secondDepositBackRate =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondDepositBackRate);
                    //第二水準追証情報.保証金率(請求2) = get入金請求顧客詳細情報.第二水準追証情報.保証金率(請求2)
                    l_secondAdditionalInfo.secondMarginDepositRate2 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondMarginDepositRate2);
                    //第二水準追証情報.保証金率(請求1) = get入金請求顧客詳細情報.第二水準追証情報.保証金率(請求1)
                    l_secondAdditionalInfo.secondMarginDepositRate1 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondMarginDepositRate1);
                    //第二水準追証情報.保証金率(請求見込) = get入金請求顧客詳細情報.第二水準追証情報.保証金率(請求見込)
                    l_secondAdditionalInfo.secondMarginDepositRateExpect =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondMarginDepositRateExpect);
                    //第二水準追証情報.追証金額(未入金) = get入金請求顧客詳細情報.第二水準追証情報.追証金額(未入金)
                    l_secondAdditionalInfo.secondDepositNonPay =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondDepositNonPay);
                    //第二水準追証情報.追証金額(請求2) = get入金請求顧客詳細情報.第二水準追証情報.追証金額(請求2)
                    l_secondAdditionalInfo.secondDeposit2 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondDeposit2);
                    //第二水準追証情報.追証金額(請求1) = get入金請求顧客詳細情報.第二水準追証情報.追証金額(請求1)
                    l_secondAdditionalInfo.secondDeposit1 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondDeposit1);
                    //第二水準追証情報.追証決済必要額(未入金) = get入金請求顧客詳細情報.第二水準追証情報.追証決済必要額(未入金)
                    l_secondAdditionalInfo.secondSettlementNonPay =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondSettlementNonPay);
                    //第二水準追証情報.追証決済必要額(請求2) = get入金請求顧客詳細情報.第二水準追証情報.追証決済必要額(請求2)
                    l_secondAdditionalInfo.secondSettlement2 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondSettlement2);
                    //第二水準追証情報.追証決済必要額(請求1) = get入金請求顧客詳細情報.第二水準追証情報.追証決済必要額(請求1)
                    l_secondAdditionalInfo.secondSettlement1 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondSettlement1);
                    //第二水準追証情報.保証金増減 = get入金請求顧客詳細情報.第二水準追証情報.保証金増減
                    l_secondAdditionalInfo.secondMarginDepositInDe =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondMarginDepositInDe);
                    //第二水準追証情報.保証金増減(見込金額) = get入金請求顧客詳細情報.第二水準追証情報.保証金増減(見込金額)
                    l_secondAdditionalInfo.secondMarginDepositInDeExpect =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondMarginDepositInDeExpect);
                    //第二水準追証情報.決済済建玉 = get入金請求顧客詳細情報.第二水準追証情報.決済済建玉
                    l_secondAdditionalInfo.secondSettledContract =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondSettledContract);
                    //第二水準追証情報.未解消金額(未入金) = get入金請求顧客詳細情報.第二水準追証情報.未解消金額(未入金)
                    l_secondAdditionalInfo.secondUncancelAmtNonPay =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelAmtNonPay);
                    //第二水準追証情報.未解消金額(請求2) = get入金請求顧客詳細情報.第二水準追証情報.未解消金額(請求2)
                    l_secondAdditionalInfo.secondUncancelAmt2 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelAmt2);
                    //第二水準追証情報.未解消金額(請求1) = get入金請求顧客詳細情報.第二水準追証情報.未解消金額(請求1)
                    l_secondAdditionalInfo.secondUncancelAmt1 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelAmt1);
                    //第二水準追証情報.未解消金額(請求見込) = get入金請求顧客詳細情報.第二水準追証情報.未解消金額(請求見込)
                    l_secondAdditionalInfo.secondUncancelAmtExpect =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelAmtExpect);
                    //第二水準追証情報.未解消決済必要額(未入金) = get入金請求顧客詳細情報.第二水準追証情報.未解消決済必要額(未入金)
                    l_secondAdditionalInfo.secondUncancelSettleRequiredAmtNonPay =
                        WEB3StringTypeUtility.formatNumber(
                            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay);
                    //第二水準追証情報.未解消決済必要額(請求2) = get入金請求顧客詳細情報.第二水準追証情報.未解消決済必要額(請求2)
                    l_secondAdditionalInfo.secondUncancelSettleRequiredAmt2 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2);
                    //第二水準追証情報.未解消決済必要額(請求1) = get入金請求顧客詳細情報.第二水準追証情報.未解消決済必要額(請求1)
                    l_secondAdditionalInfo.secondUncancelSettleRequiredAmt1 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelSettleRequiredAmt1);
                    //第二水準追証情報.未解消決済必要額(請求見込) = get入金請求顧客詳細情報.第二水準追証情報.未解消決済必要額(請求見込)
                    l_secondAdditionalInfo.secondUncancelSettleRequiredAmtExpect =
                        WEB3StringTypeUtility.formatNumber(
                            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtExpect);

                    //２）レスポンスデータのセット
                    //レスポンスデータ.追証発生状況 = 2
                    l_response.additionalGenerationStateDiv =
                        WEB3AdminTPaAdditionalGenerationStateDivDef.SECOND_LEVEL_OCCUR;
                    //レスポンスデータ.第一水準追証情報 = null
                    l_response.firstAdditionalInfo = null;
                    //レスポンスデータ.第二水準追証情報 = 上記１）でプロパティセットした第二水準追証情報
                    l_response.secondAdditionalInfo = l_secondAdditionalInfo;
                }
            }
        }

        //レスポンスデータにプロパティをセットする。
        //（不足金や追証に関するレスポンスの項目は除く）
        //レスポンスデータ.計算日 = get入金請求顧客詳細情報.計算日
        l_response.calcDate = WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getCalcDate());
        //レスポンスデータ.部店コード = リクエストデータ.部店コード
        l_response.branchCode = l_request.branchCode;
        //レスポンスデータ.顧客コード = リクエストデータ.顧客コード
        l_response.accountCode = l_request.accountCode;
        //レスポンスデータ.顧客名 = 顧客.名前（苗字）
        l_response.accountName = l_mainAccount.getMainAccountRow().getFamilyName();
        //レスポンスデータ.扱者コード = 顧客.扱者コード(SONAR)
        l_response.traderCode = l_mainAccount.getMainAccountRow().getSonarTraderCode();
        //レスポンスデータ.属性 = get入金請求顧客詳細情報.顧客属性
        l_response.attribute = l_paymentRequisitionAccountDetailInfo.getAccountAttribute();
        //レスポンスデータ.取引停止区分 = get顧客余力条件Params.取引停止区分
        l_response.tradeStopDiv = l_tradingpowerCalcConditionParams.getTradingStop();
        //レスポンスデータ.追証未入金区分 = get顧客余力条件Params.追証未入金区分
        l_response.additionalDepositStop = l_tradingpowerCalcConditionParams.getAdditionalDepositStop();
        //レスポンスデータ.信用新規建余力区分 = get顧客余力条件Params.信用新規建余力区分
        l_response.marginOpenPositionStop = l_tradingpowerCalcConditionParams.getMarginOpenPositionStop();
        //レスポンスデータ.先物OP新規建余力区分 = get顧客余力条件Params.先物OP新規建余力区分
        l_response.ifoOpenPositionStop = l_tradingpowerCalcConditionParams.getIfoOpenPositionStop();
        //レスポンスデータ.出金余力区分 = get顧客余力条件Params.出金余力区分
        l_response.paymentStop = l_tradingpowerCalcConditionParams.getPaymentStop();
        //レスポンスデータ.その他商品買付余力区分 = get顧客余力条件Params.その他商品買付余力区分
        l_response.otherTradingStop = l_tradingpowerCalcConditionParams.getOtherTradingStop();
        //レスポンスデータ.立替金 = get入金請求顧客詳細情報.不足金発生情報.立替金
        l_response.debitAmount =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().debitAmount);
        //レスポンスデータ.特別立替金 = get入金請求顧客詳細情報.不足金発生情報.特別立替金
        l_response.specialDebitAmount =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().specialDebitAmount);
        //レスポンスデータ.保証金自動振替後判定フラグ = get入金請求顧客詳細情報.保証金自動振替後判定フラグ
        l_response.autoTransferAfterJudgeFlag =
            l_paymentRequisitionAccountDetailInfo.isDepositAutoTransferDivFlag();

        //レスポンスデータ.不足金発生状況
        //・不足金発生情報.必要入金額(T+0)～(T+5)が全て0の場合、
        if ("0".equals(l_shortfallGenerationInfo.requiredPayAmt0)
            && "0".equals(l_shortfallGenerationInfo.requiredPayAmt1)
            && "0".equals(l_shortfallGenerationInfo.requiredPayAmt2)
            && "0".equals(l_shortfallGenerationInfo.requiredPayAmt3)
            && "0".equals(l_shortfallGenerationInfo.requiredPayAmt4)
            && "0".equals(l_shortfallGenerationInfo.requiredPayAmt5))
        {
            //0(不足金未発生)をセットする。
            l_response.shortfallGenerationStateDiv =
                WEB3AdminTPShortfallGenerationStateDivDef.SHORTFALL_NOT_OCCUR;
        }
        //・get入金請求顧客詳細情報().顧客属性 = 0(現物/前金制)　@or 1(現物/預り証券評価制) の場合、
        else if (WEB3AccountAttributeDef.EQUITY_NOT_ASSET_EVAL.equals(
            l_paymentRequisitionAccountDetailInfo.getAccountAttribute())
            || WEB3AccountAttributeDef.EQUITY_ASSET_EVAL.equals(
            l_paymentRequisitionAccountDetailInfo.getAccountAttribute()))
        {
            //1（不足金発生<現物顧客>）をセットする。
            l_response.shortfallGenerationStateDiv =
                WEB3AdminTPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_EQUITY_CUST;
        }
        //・get入金請求顧客詳細情報().顧客属性 = 2(信用) の場合、
        else if (WEB3AccountAttributeDef.MARGIN.equals(
            l_paymentRequisitionAccountDetailInfo.getAccountAttribute()))
        {
            //2（不足金発生<信用顧客>）をセットする。
            l_response.shortfallGenerationStateDiv =
                WEB3AdminTPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_MARGIN_CUST;
        }

        //レスポンスデータ.不足金発生情報 = 上記１）でプロパティセットした不足金発生情報をセットする。
        l_response.shortfallGenerationInfo = l_shortfallGenerationInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入金請求顧客検索ダウンロード)<BR>
     * (get入金請求顧客検索ダウンロード)<BR>
     * <BR>
     * シーケンス図「get入金請求顧客検索ダウンロード」参照<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3AdminTPPaymentRequisitionDownLoadResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C9D35C0170
     */
    private WEB3AdminTPPaymentRequisitionDownLoadResponse getPaymentRequisitionCustomerSearchDownLoad(
        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchDownLoad(WEB3AdminTPPaymentRequisitionDownLoadRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者の権限チェックを行う。
        //管理者権限チェック
        //・カテゴリコード="A0201"（余力管理者）
        //・更新フラグ=false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.TRADING_POWER, false);

        //get入金請求顧客情報Params一覧(入金請求顧客検索共通リクエスト)
        //入金請求管理テーブルのデータを取得する。
        //[引数]
        //・リクエストデータ
        List l_lisPaymentRequisitMngParams = getPaymentRequisitMngParamsList(l_request);

        //入金請求顧客検索CSV( )
        //CSVデータモデルを生成する。
        WEB3AdminTPPaymentRequisitionCustomerSearchCSV l_paymentRequisitionCustomerSearchCSV =
            new WEB3AdminTPPaymentRequisitionCustomerSearchCSV();

        //get入金請求顧客情報Params一覧で取得したレコード件数分LOOP
        int l_intLength = l_lisPaymentRequisitMngParams.size();
        for (int i = 0; i < l_intLength; i++)
        {
            PaymentRequisitMngRow l_paymentRequisitMngRow =
                (PaymentRequisitMngRow)l_lisPaymentRequisitMngParams.get(i);

            //add明細行( )
            int l_intLineNumber = l_paymentRequisitionCustomerSearchCSV.addRow();

            //set部店コード(int, String)
            l_paymentRequisitionCustomerSearchCSV.setBranchCode(
                l_intLineNumber,
                l_paymentRequisitMngRow.getBranchCode());

            //set顧客コード(int, String)
            l_paymentRequisitionCustomerSearchCSV.setAccountCode(
                l_intLineNumber,
                l_paymentRequisitMngRow.getAccountCode());

            //set顧客名(int, String)
            l_paymentRequisitionCustomerSearchCSV.setFamilyName(
                l_intLineNumber,
                l_paymentRequisitMngRow.getFamilyName());

            //set扱者コード(int, String)
            l_paymentRequisitionCustomerSearchCSV.setTraderCode(
                l_intLineNumber,
                l_paymentRequisitMngRow.getTraderCode());

            //set属性(int, String)
            l_paymentRequisitionCustomerSearchCSV.setAttribute(
                l_intLineNumber,
                l_paymentRequisitMngRow.getAccountAttribute());

            //set立替金(int, String)
            l_paymentRequisitionCustomerSearchCSV.setDebitAmount(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getDebitAmount()));

            //set特別立替金(int, String)
            l_paymentRequisitionCustomerSearchCSV.setSpecialDebitAmount(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getSpecialDebitAmount()));

            //set必要入金額(int, String)
            l_paymentRequisitionCustomerSearchCSV.setRequiredPayAmt(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getLackAccountBalance()));

            //set第一水準追証金額(int, String)
            l_paymentRequisitionCustomerSearchCSV.setFirstDepositAmount(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getFirstDepositAmount()));

            //set第一水準追証経過日数(int, String)
            String l_strFirstDepositPassDay = null;
            if (!l_paymentRequisitMngRow.getFirstDepositPassDayIsNull())
            {
                l_strFirstDepositPassDay =
                    WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getFirstDepositPassDay());
            }
            l_paymentRequisitionCustomerSearchCSV.setFirstDepositPassDay(
                l_intLineNumber,
                l_strFirstDepositPassDay);

            //set第二水準追証請求（1）(int, String)
            l_paymentRequisitionCustomerSearchCSV.setSecondDeposit1(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getSecondDeposit1()));

            //set第二水準追証請求（2）(int, String)
            l_paymentRequisitionCustomerSearchCSV.setSecondDeposit2(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getSecondDeposit2()));

            //set第二水準追証未入金(int, String)
            l_paymentRequisitionCustomerSearchCSV.setSecondDepositNonPay(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getSecondDepositNonPay()));
        }

        //getCSVファ@イル行( )
        //CSVファ@イル行を取得する。
        String[] l_strCsvFileLines = l_paymentRequisitionCustomerSearchCSV.getCsvFileLines();

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTPPaymentRequisitionDownLoadResponse l_response =
            (WEB3AdminTPPaymentRequisitionDownLoadResponse)l_request.createResponse();

        //プロパティセット
        //以下の通り、プロパティセットを行う。
        //　@－レスポンスデータ.ダウンロードファ@イル = getCSVファ@イルの戻り値
        l_response.downloadFile = l_strCsvFileLines;
        //　@－レスポンスデータ.現在日時 = TradingSystem.getSystemTimestamp()の戻り値
        l_response.currentDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入金請求顧客情報Params一覧)<BR>
     * 入金請求管理テーブルより以下の条件でレコードを取得する。<BR>
     * <BR>
     * １）入金請求管理テーブル.計算日 = 前営業日（営業日計算ユーティリティクラスを利用して取得）<BR>
     * <BR>
     * ２）入金請求管理テーブル.会社コード = 管理者クラス.get証券会社<BR>
     * <BR>
     * ３）入金請求管理テーブル.部店コード = リクエスト.部店コード<BR>
     * <BR>
     * ４）リクエスト.顧客コードが"null以外"の場合<BR>
     * 　@入金請求管理テーブル.顧客コード like リクエスト.顧客コード<BR>
     * 　@※Like検索で「リクエスト.顧客コード」の後の文字をワイルドカードとする。<BR>
     * <BR>
     * ５）リクエスト.扱者コードが"null以外"の場合<BR>
     * 　@入金請求管理テーブル.扱者コード = リクエスト.扱者コード<BR>
     * <BR>
     * ６）顧客属性に関する条件設定<BR>
     * 　@６－１）リクエスト.顧客属性が"0"（現物）の場合<BR>
     * 　@　@　@　@入金請求管理テーブル.顧客属性 = "0"(現物/前金制) or "1"(現物/預り証券評価制)<BR>
     * 　@６－２）リクエスト.顧客属性が"1"（信用）の場合<BR>
     * 　@　@　@　@入金請求管理テーブル.顧客属性 = "2"(信用)<BR>
     * <BR>
     * ７）請求事由 および日数に関する条件設定<BR>
     * 　@７－１）リクエスト.請求事由が"1"（立替金/特別立替金）の場合<BR>
     * 　@　@　@　@入金請求管理テーブル.立替金 > 0 or 入金請求管理テーブル.特別立替金 > 0<BR>
     * 　@７－２）リクエスト.請求事由が"2"（不足金（当日））の場合<BR>
     * 　@　@　@　@入金請求管理テーブル.預り金不足額(T+0) > 0<BR>
     * 　@７－３）リクエスト.請求事由が"3"（第一水準追証）の場合<BR>
     * 　@　@　@a)リクエスト.日数が"8"(第一水準指定最大日数)の場合<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第一水準追証経過日数 >= リクエスト.日数<BR>
     * 　@　@　@b)リクエスト.日数が"0"(摘要日数すべて)の場合<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第一水準追証経過日数 > リクエスト.日数<BR>
     * 　@　@　@c)リクエスト.日数が上記以外の場合<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第一水準追証経過日数 = リクエスト.日数<BR>
     * 　@７－４）リクエスト.請求事由が"4"（第二水準追証）の場合<BR>
     * 　@　@　@a)リクエスト.日数が"3"(第二水準指定最大日数)の場合<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第二水準追証未入金 > 0<BR>
     * 　@　@　@b)リクエスト.日数が"2"の場合<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第二水準追証請求(2) > 0<BR>
     * 　@　@　@c)リクエスト.日数が"1"の場合<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第二水準追証請求(1) > 0<BR>
     * 　@　@　@d)リクエスト.日数が"0"(摘要日数すべて)の場合<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第二水準追証未入金 > 0 or<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第二水準追証請求(2) > 0 or<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第二水準追証請求(1) > 0<BR>
     * 　@７－５）リクエスト.請求事由"5"（指定なし）が選択された場合<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.立替金 > 0 or<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.特別立替金 > 0 or<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.預り金不足額(T+0) > 0 or<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第一水準追証経過日数 > 0 or<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第二水準追証未入金 > 0 or<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第二水準追証請求(2) > 0 or<BR>
     * 　@　@　@　@　@　@入金請求管理テーブル.第二水準追証請求(1) > 0<BR>
     * <BR>
     * ８）入金請求管理テーブルを上記１）～７）の条件をもとに検索する。<BR>
     * 　@各検索条件は、ANDで連結する。<BR>
     * 　@ソート順は顧客コードの昇順とする。<BR>
     * <BR>
     * ９）取得したレコードをListに格納し、返却する。<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 48D0AD0D022F
     */
    private List getPaymentRequisitMngParamsList(
        WEB3AdminTPPaymentRequisitionCommonRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitMngParamsList(WEB3AdminTPPaymentRequisitionCommonRequest)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbQueryCond = new StringBuffer();
        List l_lisArrayList = new ArrayList();

        //１）入金請求管理テーブル.計算日 = 前営業日（営業日計算ユーティリティクラスを利用して取得）
        l_sbQueryCond.append("calc_date = ? ");
        Timestamp l_tsSysTimestamp = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsSysTimestamp);
        l_lisArrayList.add(l_genBizDate.roll(-1));

        //２）入金請求管理テーブル.会社コード = 管理者クラス.get証券会社
        l_sbQueryCond.append("and institution_code = ? ");
        l_lisArrayList.add(WEB3Administrator.getInstanceFromLoginInfo().getInstitutionCode());

        //３）入金請求管理テーブル.部店コード = リクエスト.部店コード
        l_sbQueryCond.append("and branch_code = ? ");
        l_lisArrayList.add(l_request.branchCode);

        //４）リクエスト.顧客コードが"null以外"の場合
        if (l_request.accountCode != null)
        {
            //入金請求管理テーブル.顧客コード like リクエスト.顧客コード
            //※Like検索で「リクエスト.顧客コード」の後の文字をワイルドカードとする。
            l_sbQueryCond.append("and account_code like ? || '%' ");
            l_lisArrayList.add(l_request.accountCode);
        }

        //５）リクエスト.扱者コードが"null以外"の場合
        if (l_request.traderCode != null)
        {
            //入金請求管理テーブル.扱者コード = リクエスト.扱者コード
            l_sbQueryCond.append("and trader_code = ? ");
            l_lisArrayList.add(l_request.traderCode);
        }

        //６）顧客属性に関する条件設定
        //　@６－１）リクエスト.顧客属性が"0"（現物）の場合
        if (WEB3AdminTPCustomerAttributeDef.EQUITY_CUST.equals(l_request.customerAttribute))
        {
            //入金請求管理テーブル.顧客属性 = "0"(現物/前金制) or "1"(現物/預り証券評価制)
            l_sbQueryCond.append("and account_attribute in (0, 1) ");
        }
        //　@６－２）リクエスト.顧客属性が"1"（信用）の場合
        else if (WEB3AdminTPCustomerAttributeDef.MARGIN_CUST.equals(l_request.customerAttribute))
        {
            //入金請求管理テーブル.顧客属性 = "2"(信用)
            l_sbQueryCond.append("and account_attribute = 2 ");
        }

        //７）請求事由 および日数に関する条件設定

        //　@７－１）リクエスト.請求事由が"1"（立替金/特別立替金）の場合
        if (WEB3AdminTPClaimReasonDef.DEBIT_AMOUNT_SPECIAL.equals(l_request.claimReason))
        {
            //入金請求管理テーブル.立替金 > 0 or 入金請求管理テーブル.特別立替金 > 0
            l_sbQueryCond.append("and (debit_amount > 0 or special_debit_amount > 0) ");
        }

        //　@７－２）リクエスト.請求事由が"2"（不足金（当日））の場合
        else if (WEB3AdminTPClaimReasonDef.SHORT_FALL_GENERATION_TODAY.equals(l_request.claimReason))
        {
            //入金請求管理テーブル.預り金不足額(T+0) > 0
            l_sbQueryCond.append("and lack_account_balance > 0 ");
        }

        //７－３）リクエスト.請求事由が"3"（第一水準追証）の場合
        else if (WEB3AdminTPClaimReasonDef.BREAK30_ADDITIONAL.equals(l_request.claimReason))
        {
            //a)リクエスト.日数が"8"(第一水準指定最大日数)の場合
            if (WEB3AdminTPDaysDef.DAYS_8.equals(l_request.days))
            {
                //入金請求管理テーブル.第一水準追証経過日数 >= リクエスト.日数
                l_sbQueryCond.append("and first_deposit_pass_day >= ? ");
                l_lisArrayList.add(l_request.days);
            }
            //b)リクエスト.日数が"0"(摘要日数すべて)の場合
            else if (WEB3AdminTPDaysDef.DAYS_0.equals(l_request.days))
            {
                //入金請求管理テーブル.第一水準追証経過日数 > リクエスト.日数
                l_sbQueryCond.append("and first_deposit_pass_day > ? ");
                l_lisArrayList.add(l_request.days);
            }
            //c)リクエスト.日数が上記以外の場合
            else
            {
                //入金請求管理テーブル.第一水準追証経過日数 = リクエスト.日数
                l_sbQueryCond.append("and first_deposit_pass_day = ? ");
                l_lisArrayList.add(l_request.days);
            }
        }

        //７－４）リクエスト.請求事由が"4"（第二水準追証）の場合
        else if (WEB3AdminTPClaimReasonDef.BREAK20_ADDITIONAL.equals(l_request.claimReason))
        {
            //a)リクエスト.日数が"3"(第二水準指定最大日数)の場合
            if (WEB3AdminTPDaysDef.DAYS_3.equals(l_request.days))
            {
                //入金請求管理テーブル.第二水準追証未入金 > 0
                l_sbQueryCond.append("and second_deposit_non_pay > 0 ");
            }
            //b)リクエスト.日数が"2"の場合
            else if (WEB3AdminTPDaysDef.DAYS_2.equals(l_request.days))
            {
                //入金請求管理テーブル.第二水準追証請求(2) > 0
                l_sbQueryCond.append("and second_deposit_2 > 0 ");
            }
            //c)リクエスト.日数が"1"の場合
            else if (WEB3AdminTPDaysDef.DAYS_1.equals(l_request.days))
            {
                //入金請求管理テーブル.第二水準追証請求(1) > 0
                l_sbQueryCond.append("and second_deposit_1 > 0 ");
            }
            //d)リクエスト.日数が"0"(摘要日数すべて)の場合
            else if (WEB3AdminTPDaysDef.DAYS_0.equals(l_request.days))
            {
                //入金請求管理テーブル.第二水準追証未入金 > 0 or
                //入金請求管理テーブル.第二水準追証請求(2) > 0 or
                //入金請求管理テーブル.第二水準追証請求(1) > 0
                l_sbQueryCond.append("and (second_deposit_non_pay > 0 or ");
                l_sbQueryCond.append("second_deposit_2 > 0 or ");
                l_sbQueryCond.append("second_deposit_1 > 0) ");
            }
        }

        //７－５）リクエスト.請求事由"5"（指定なし）が選択された場合
        else if (WEB3AdminTPClaimReasonDef.DEFAULT.equals(l_request.claimReason))
        {
            //入金請求管理テーブル.立替金 > 0 or
            l_sbQueryCond.append("and (debit_amount > 0 or ");
            //入金請求管理テーブル.特別立替金 > 0 or
            l_sbQueryCond.append("special_debit_amount > 0 or ");
            //入金請求管理テーブル.預り金不足額(T+0) > 0 or
            l_sbQueryCond.append("lack_account_balance > 0 or ");
            //入金請求管理テーブル.第一水準追証経過日数 > 0 or
            l_sbQueryCond.append("first_deposit_pass_day > 0 or ");
            //入金請求管理テーブル.第二水準追証未入金 > 0 or
            l_sbQueryCond.append("second_deposit_non_pay > 0 or ");
            //入金請求管理テーブル.第二水準追証請求(2) > 0 or
            l_sbQueryCond.append("second_deposit_2 > 0 or ");
            //入金請求管理テーブル.第二水準追証請求(1) > 0
            l_sbQueryCond.append("second_deposit_1 > 0) ");
        }

        //８）入金請求管理テーブルを上記１）～７）の条件をもとに検索する。
        //　@各検索条件は、ANDで連結する。
        //　@ソート順は顧客コードの昇順とする。
        String l_strOrderBy = " account_code ";

        Object[] l_objValues = new Object[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_objValues);

        List l_lisPaymentRequisitMngParams = null;

        try
        {
            l_lisPaymentRequisitMngParams =
                Processors.getDefaultProcessor().doFindAllQuery(
                    PaymentRequisitMngParams.TYPE,
                    l_sbQueryCond.toString(),
                    l_strOrderBy,
                    null,
                    l_objValues);
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

        //９）取得したレコードをListに格納し、返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisPaymentRequisitMngParams;
    }

    /**
     * (create入金請求顧客検索一覧ユニット)<BR>
     * get入金請求顧客情報Params一覧で取得した情報を<BR>
     * 入金請求顧客検索一覧ユニットにセットする。<BR>
     * <BR>
     * １）引数.入金請求顧客情報Paramsの配列の数だけ作成した<BR>
     * 　@　@入金請求顧客検索一覧ユニットの配列に、以下の通り値をセットする。<BR>
     * <BR>
     * 　@　@入金請求顧客検索一覧ユニット.部店コード = 入金請求管理Params.部店コード<BR>
     * 　@　@入金請求顧客検索一覧ユニット.顧客コード = 入金請求管理Params.顧客コード<BR>
     * 　@　@入金請求顧客検索一覧ユニット.顧客名 = 入金請求管理Params.顧客名<BR>
     * 　@　@入金請求顧客検索一覧ユニット.扱者コード = 入金請求管理Params.扱者コード<BR>
     * 　@　@入金請求顧客検索一覧ユニット.属性 = 入金請求管理Params.顧客属性<BR>
     * 　@　@入金請求顧客検索一覧ユニット.立替金 = 入金請求管理Params.立替金<BR>
     * 　@　@入金請求顧客検索一覧ユニット.特別立替金 = 入金請求管理Params.特別立替金<BR>
     * 　@　@入金請求顧客検索一覧ユニット.必要入金額 =<BR>
     * 　@　@　@　@入金請求管理Params.預り金不足額(T+0)<BR>
     * 　@　@入金請求顧客検索一覧ユニット.第一水準追証金額 =<BR>
     * 　@　@　@　@入金請求管理Params.第一水準追証金額<BR>
     * 　@　@入金請求顧客検索一覧ユニット.第一水準追証経過日数 =<BR>
     * 　@　@　@　@入金請求管理Params.第一水準追証経過日数<BR>
     * 　@　@入金請求顧客検索一覧ユニット.第二水準追証請求(1) =<BR>
     * 　@　@　@　@入金請求管理Params.第二水準追証請求(1)<BR>
     * 　@　@入金請求顧客検索一覧ユニット.第二水準追証請求(2) =<BR>
     * 　@　@　@　@入金請求管理Params.第二水準追証請求(2)<BR>
     * 　@　@入金請求顧客検索一覧ユニット.第二水準追証未入金 =<BR>
     * 　@　@　@　@入金請求管理Params.第二水準追証未入金<BR>
     * <BR>
     * ３）生成した入金請求顧客検索一覧ユニットの配列を返却する。<BR>
     * @@param l_paymentRequisitMngParams - (入金請求管理Params)<BR>
     * @@return WEB3AdminTPPaymentRequisitionListUnit[]
     * @@roseuid 48DB5C52023F
     */
    private WEB3AdminTPPaymentRequisitionListUnit[] createPaymentRequisitionListUnit(
        PaymentRequisitMngParams[] l_paymentRequisitMngParams)
    {
        final String STR_METHOD_NAME =
            "createPaymentRequisitionListUnit(PaymentRequisitMngParams[])";
        log.entering(STR_METHOD_NAME);

        //１）引数.入金請求顧客情報Paramsの配列の数だけ作成した
        //　@　@入金請求顧客検索一覧ユニットの配列に、以下の通り値をセットする。
        int l_intLength = l_paymentRequisitMngParams.length;

        WEB3AdminTPPaymentRequisitionListUnit[] l_paymentRequisitionListUnits =
            new WEB3AdminTPPaymentRequisitionListUnit[l_intLength];

        for (int i = 0; i < l_intLength; i++)
        {
            l_paymentRequisitionListUnits[i] = new WEB3AdminTPPaymentRequisitionListUnit();

            //入金請求顧客検索一覧ユニット.部店コード = 入金請求管理Params.部店コード
            l_paymentRequisitionListUnits[i].branchCode =
                l_paymentRequisitMngParams[i].getBranchCode();

            //入金請求顧客検索一覧ユニット.顧客コード = 入金請求管理Params.顧客コード
            l_paymentRequisitionListUnits[i].accountCode =
                l_paymentRequisitMngParams[i].getAccountCode();

            //入金請求顧客検索一覧ユニット.顧客名 = 入金請求管理Params.顧客名
            l_paymentRequisitionListUnits[i].accountName =
                l_paymentRequisitMngParams[i].getFamilyName();

            //入金請求顧客検索一覧ユニット.扱者コード = 入金請求管理Params.扱者コード
            l_paymentRequisitionListUnits[i].traderCode =
                l_paymentRequisitMngParams[i].getTraderCode();

            //入金請求顧客検索一覧ユニット.属性 = 入金請求管理Params.顧客属性
            l_paymentRequisitionListUnits[i].attribute =
                l_paymentRequisitMngParams[i].getAccountAttribute();

            //入金請求顧客検索一覧ユニット.立替金 = 入金請求管理Params.立替金
            l_paymentRequisitionListUnits[i].debitAmount =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getDebitAmount());

            //入金請求顧客検索一覧ユニット.特別立替金 = 入金請求管理Params.特別立替金
            l_paymentRequisitionListUnits[i].specialDebitAmount =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getSpecialDebitAmount());

            //入金請求顧客検索一覧ユニット.必要入金額 = 入金請求管理Params.預り金不足額(T+0)
            l_paymentRequisitionListUnits[i].requiredPayAmt =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getLackAccountBalance());

            //入金請求顧客検索一覧ユニット.第一水準追証金額 = 入金請求管理Params.第一水準追証金額
            l_paymentRequisitionListUnits[i].firstDepositAmount =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getFirstDepositAmount());

            //入金請求顧客検索一覧ユニット.第一水準追証経過日数 = 入金請求管理Params.第一水準追証経過日数
            String l_strFirstDepositPassDay = null;
            if (!l_paymentRequisitMngParams[i].getFirstDepositPassDayIsNull())
            {
                l_strFirstDepositPassDay =
                    WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getFirstDepositPassDay());
            }
            l_paymentRequisitionListUnits[i].firstDepositPassDay = l_strFirstDepositPassDay;

            //入金請求顧客検索一覧ユニット.第二水準追証請求(1) = 入金請求管理Params.第二水準追証請求(1)
            l_paymentRequisitionListUnits[i].secondDeposit1 =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getSecondDeposit1());

            //入金請求顧客検索一覧ユニット.第二水準追証請求(2) = 入金請求管理Params.第二水準追証請求(2)
            l_paymentRequisitionListUnits[i].secondDeposit2 =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getSecondDeposit2());

            //入金請求顧客検索一覧ユニット.第二水準追証未入金 = 入金請求管理Params.第二水準追証未入金
            l_paymentRequisitionListUnits[i].secondDepositNonPay =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getSecondDepositNonPay());
        }

        //３）生成した入金請求顧客検索一覧ユニットの配列を返却する。
        return l_paymentRequisitionListUnits;
    }

    /**
     * (get顧客余力条件Params)<BR>
     * 顧客余力条件テーブルより、以下の条件でレコードを検索し結果を返す。<BR>
     * <BR>
     * 条件：<BR>
     * 口座ID = 引数のアカウントID<BR>
     * @@param l_accountId - (アカウントID)<BR>
     * @@return TradingpowerCalcConditionParams
     * @@throws WEB3BaseException
     * @@roseuid 48E0671C0145
     */
    private TradingpowerCalcConditionParams getTradingPowerCalcConditionParams(
        Long l_accountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcConditionParams(Long)";
        log.entering(STR_METHOD_NAME);

        TradingpowerCalcConditionRow l_tradingpowerCalcConditionRow = null;

        try
        {
            l_tradingpowerCalcConditionRow =
                TradingpowerCalcConditionDao.findRowByAccountId(l_accountId.longValue());
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

        log.exiting(STR_METHOD_NAME);
        return new TradingpowerCalcConditionParams(l_tradingpowerCalcConditionRow);
    }

}
@
