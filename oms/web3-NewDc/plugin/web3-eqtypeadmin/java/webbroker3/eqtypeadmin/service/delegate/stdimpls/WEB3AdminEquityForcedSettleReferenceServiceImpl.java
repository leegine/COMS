head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文照会サービスImpl(WEB3AdminEquityForcedSettleReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 趙林鵬 (中訊) 新規作成 モデルNo.129
Revision History : 2007/07/24 何文敏 (中訊) 新規作成 モデルNo.159
Revision History : 2008/01/17 孟亞南 (中訊) 仕様変更 モデルNo.181
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedSettleReasonType;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReasonUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleTemporaryOrderUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleReferenceService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者・強制決済注文照会サービスImpl)<BR>
 * 管理者・強制決済注文照会サービス実装クラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleReferenceServiceImpl implements WEB3AdminEquityForcedSettleReferenceService
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleReferenceServiceImpl.class);

    /**
     * @@roseuid 462CA423019B
     */
    public WEB3AdminEquityForcedSettleReferenceServiceImpl()
    {

    }

    /**
     * 強制決済注文照会処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * リクエストデータが、<BR>
     * [管理者・強制決済注文照会入力リクエストの場合]<BR>
     * 　@this.get入力画面()をコールする。<BR>
     * <BR>
     * [管理者・強制決済注文照会リクエストの場合]<BR>
     * 　@this.get照会画面()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4601E9290128
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
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

        //管理者・強制決済注文照会入力リクエストの場合
        //this.get入力画面()をコールする。
        if (l_request instanceof WEB3AdminForcedSettleRefInputRequest)
        {
            l_response =
                this.getInputScreen(
                    (WEB3AdminForcedSettleRefInputRequest)l_request);
        }
        //管理者・強制決済注文照会リクエストの場合
        //this.get照会画面()をコールする。
        else if (l_request instanceof WEB3AdminForcedSettleReferenceRequest)
        {
            l_response =
                this.getReferenceScreen(
                    (WEB3AdminForcedSettleReferenceRequest)l_request);
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
     * (get入力画面)<BR>
     * 強制決済注文照会入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者強制決済注文照会サービス）get入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済注文照会入力リクエストオブジェクト<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4601E99B0176
     */
    protected WEB3AdminForcedSettleRefInputResponse getInputScreen(WEB3AdminForcedSettleRefInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminForcedSettleRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード：　@機@能カテゴリコード.国内株式（強制決済）
        //is更新：　@false（更新なし）
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, false);

        //getBranch
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;

        try
        {
            l_branch = l_gentradeAccountManager.getWeb3GenBranch(
                l_admin.getInstitutionCode(),
                l_admin.getBranchCode());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get取扱可能市場(部店 : 部店, 弁済区分 : String, 弁済期限値 : double)
        //部店：　@管理者の証券会社コード、部店コードに該当する部店オブジェクト
        //弁済区分：　@"DEFAULT"（指定なし）
        //弁済期限値：　@"DEFAULT"（指定なし）
        String[] l_strHandlingPossibleMarkets =
            WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
            l_branch, WEB3GentradeRepaymentDivDef.DEFAULT, 0D);

        //get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //create強制決済期日一覧(String, String[])
        //証券会社コード：　@get証券会社コード()の戻り値
        //部店コード一覧：　@リクエストデータ.部店コード一覧
        Date[] l_datForcedSettleCloseDateList = this.createForcedSettleCloseDateList(
            l_strInstitutionCode,
            l_request.branchCodeList);

        //create強制決済理由情報一覧(部店)
        //部店：　@管理者の証券会社コード、部店コードに該当する部店オブジェクト
        WEB3AdminForcedSettleReasonUnit[] l_adminForcedSettleReasonUnits =
            this.createForcedSettleReasonUnitList(l_branch);

        // createResponse( )
        WEB3AdminForcedSettleRefInputResponse l_response =
            (WEB3AdminForcedSettleRefInputResponse)l_request.createResponse();

        //プロパティセット
        //発注日一覧     ＝　@翌営業日 + 当営業日（業務日付）を含めた
        //過去30日の間の営業日を降順でセット。
        Date[] l_datOrderBizDateList = this.createOrderBizDateList();

        l_response.orderBizDateList = l_datOrderBizDateList;
        //決済期日一覧      ＝　@create強制決済期日一覧()の戻り値
        l_response.settleTimeLimitList = l_datForcedSettleCloseDateList;

        //市場コード一覧     ＝　@get取扱可能市場()の戻り値
        l_response.marketCodeList = l_strHandlingPossibleMarkets;

        //強制決済理由一覧        ＝　@create強制決済理由情報一覧()の戻り値
        l_response.forcedSettleReasonList = l_adminForcedSettleReasonUnits;

        //注文エラー理由コード一覧
        //    (*1)以下の値の配列（コード値の参照は、インターフェイス定義クラスを使用すること）
        //・"建株残高不足エラー"
        //・"売買停止銘柄エラー"
        //・"決済期日到来済エラー"
        //・"現引・現渡注文登録済エラー"
        //・"その他エラー"
        String[] l_strErrorReason =
        {
            WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR,
            WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR,
            WEB3ErrorReasonCodeDef.SETTLEDAY_CAME_ERROR,
            WEB3ErrorReasonCodeDef.SWAP_MARGIN_REGISTED_ERROR,
            WEB3ErrorReasonCodeDef.OTHRE_ERROR
        };
        l_response.errorReason = l_strErrorReason;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get照会画面)<BR>
     * 強制決済注文照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者強制決済注文照会サービス）get照会画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済注文照会リクエストオブジェクト<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 4601E9EB00AB
     */
    protected WEB3AdminForcedSettleReferenceResponse getReferenceScreen(WEB3AdminForcedSettleReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminForcedSettleReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード：　@機@能カテゴリコード.国内株式（強制決済）
        //is更新：　@false（更新なし）
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, false);

        //validate部店権限(部店コード : String[])
        //部店コード：　@リクエストデータ.部店コード一覧
        l_admin.validateBranchPermission(l_request.branchCodeList);

        //get証券会社( )
        Institution l_institution = l_admin.getInstitution();

        //get強制決済注文一覧(証券会社, 管理者・強制決済注文照会リクエスト)
        //証券会社：　@get証券会社()の戻り値
        //リクエストデータ：　@リクエストデータ
        AdminEqForcedSettleOrderRow[] l_forcedSettleOrderList =
            WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                l_institution,
                l_request);

        int l_intPageIndex = 0;
        int l_intTotalPages = 0;
        int l_intTotalRecords = 0;

        if (l_forcedSettleOrderList == null
            || l_forcedSettleOrderList.length == 0)
        {
            //空のレスポンスデータを生成し、初期値をセットして返却する。
            WEB3AdminForcedSettleReferenceResponse l_response =
                (WEB3AdminForcedSettleReferenceResponse)l_request.createResponse();

            //総ページ数
            l_response.totalPages = l_intTotalPages + "";

            //総レコード数
            l_response.totalRecords = l_intTotalRecords + "";

            //表示ページ番号
            l_response.pageIndex = l_intPageIndex + "";

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //WEB3PageIndexInfo(l_objs : 論理ビュー::java::lang::Object[],
        //l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //arg0：　@get強制決済注文一覧()の戻り値
        //arg1：　@リクエストデータ.要求ページ番号
        //arg2：　@リクエストデータ.ページ内表示行数
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_forcedSettleOrderList,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        //getPageIndex( )
        l_intPageIndex = l_pageIndexInfo.getPageIndex();

        //getTotalPages( )
        l_intTotalPages = l_pageIndexInfo.getTotalPages();

        //getTotalRecords( )
        l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        //getArrayReturned(l_classType : Class)
        //arg0：強制決済注文Row.class
        AdminEqForcedSettleOrderRow[] l_adminEqForcedSettleOrderRows =
            (AdminEqForcedSettleOrderRow[])l_pageIndexInfo.getArrayReturned(
                AdminEqForcedSettleOrderRow.class);

        //create強制決済注文照会情報一覧(強制決済注文Row[], String)
        //強制決済注文一覧：　@getArrayReturned()の戻り値
        //承認区分：　@リクエストデータ.承認区分
        WEB3AdminForcedSettleTemporaryOrderUnit[] l_forcedSettleTemporaryOrderUnitList =
            WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(
            l_adminEqForcedSettleOrderRows,
            l_request.approveType);

        //createResponse( )
        WEB3AdminForcedSettleReferenceResponse l_response =
            (WEB3AdminForcedSettleReferenceResponse)l_request.createResponse();

        //総ページ数     ＝　@getTotalPages()の戻り値
        l_response.totalPages = l_intTotalPages + "";

        //総レコード数      ＝　@getTotalRecords()の戻り値
        l_response.totalRecords = l_intTotalRecords + "";

        //表示ページ番号     ＝　@getPageIndex()の戻り値
        l_response.pageIndex = l_intPageIndex + "";

        //強制決済注文一覧        ＝　@create強制決済注文照会情報一覧()の戻り値
        l_response.forcedSettleTemporaryOrderList = l_forcedSettleTemporaryOrderUnitList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create強制決済期日一覧)<BR>
     * 強制決済期日一覧を作成する。<BR>
     * <BR>
     * １）　@部店IDの取得<BR>
     * 　@商品管理（株式）データマネージャ.get部店ID一覧()を<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[get部店ID一覧()に指定する引数]<BR>
     * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@部店コード一覧：　@パラメータ.部店コード一覧<BR>
     * <BR>
     * ２）　@以下の条件で強制決済注文テーブルを<BR>
     * 　@検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@部店ID in (取得した部店ID一覧)<BR>
     * <BR>
     * 　@[ソート条件]<BR>
     * 　@　@期日　@降順<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合はnullを返却する。<BR>
     * <BR>
     * ３）　@ArrayListを生成する。<BR>
     * <BR>
     * ４）　@２）の戻り値について、以下の処理を繰り返す。<BR>
     * 　@４－１）　@生成したArrayList.contains() == falseの場合、<BR>
     * 　@　@生成したArrayList.add()をコールする。<BR>
     * <BR>
     * 　@　@[contains()、add()に指定する引数]<BR>
     * 　@　@　@arg0：　@処理対象の要素.期日<BR>
     * <BR>
     * ５）　@生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCodeList - (部店コード一覧)<BR>
     * 部店コード一覧<BR>
     * @@return Date[]
     * @@throws WEB3BaseException
     * @@roseuid 46103A2B0143
     */
    protected Date[] createForcedSettleCloseDateList(String l_strInstitutionCode, String[] l_strBranchCodeList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createForcedCloseDateList(String,  String[])";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMEquityDataManager l_adminPMEquityDataManager = new WEB3AdminPMEquityDataManager();

        List l_lisAdminEqForcedSettleOrderList = new ArrayList();
        List l_lisArrayList = new ArrayList();

        try
        {
            //商品管理（株式）データマネージャ.get部店ID一覧()をコールする。
            String[] l_strBranchIdList =
                l_adminPMEquityDataManager.getBranchId(l_strInstitutionCode, l_strBranchCodeList);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            StringBuffer l_sbQuery = new StringBuffer();

            l_sbQuery.append(" branch_id in ( ");

            String l_strSortCond = " close_date desc ";

            if (l_strBranchIdList != null && l_strBranchIdList.length != 0)
            {
                int l_intBranchIdListLength = l_strBranchIdList.length;
                for (int i = 0; i < l_intBranchIdListLength; i++)
                {
                    l_sbQuery.append(" ? ,");
                    l_lisArrayList.add(l_strBranchIdList[i]);
                }
            }

            l_sbQuery.deleteCharAt(l_sbQuery.length() - 1);
            l_sbQuery.append(") ");

            String[] l_strValues = new String[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_strValues);

            l_lisAdminEqForcedSettleOrderList = l_queryProcessor.doFindAllQuery(
                AdminEqForcedSettleOrderRow.TYPE,
                l_sbQuery.toString(),
                l_strSortCond,
                null,
                l_strValues);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //検索結果が取得できなかった場合はnullを返却する
        if (l_lisAdminEqForcedSettleOrderList == null
            || l_lisAdminEqForcedSettleOrderList.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //ArrayListを生成する。
        List l_lisCloseDateList = new ArrayList();

        //の戻り値について、以下の処理を繰り返す。
        int l_intEqForcedSettleOrderListSize = l_lisAdminEqForcedSettleOrderList.size();
        for (int i = 0; i < l_intEqForcedSettleOrderListSize; i++)
        {
            //　@生成したArrayList.contains() == falseの場合
            //生成したArrayList.add()をコールする。
            AdminEqForcedSettleOrderRow l_adminEqForcedSettleOrderRow =
                (AdminEqForcedSettleOrderRow)l_lisAdminEqForcedSettleOrderList.get(i);

            if (!l_lisCloseDateList.contains(l_adminEqForcedSettleOrderRow.getCloseDate()))
            {
                //生成したArrayList.add()をコールする。
                l_lisCloseDateList.add(l_adminEqForcedSettleOrderRow.getCloseDate());
            }
        }

        //生成したArrayList.toArray()の戻り値を返却する。
        Date[] l_datForcedSettleCloseDateList = new Date[l_lisCloseDateList.size()];
        l_lisCloseDateList.toArray(l_datForcedSettleCloseDateList);

        log.exiting(STR_METHOD_NAME);
        return l_datForcedSettleCloseDateList;
    }

    /**
     * (create強制決済理由情報一覧)<BR>
     * 強制決済理由情報の一覧を作成する。<BR>
     * <BR>
     * １）　@戻り値を格納するArrayListを生成する。<BR>
     * <BR>
     * ２）　@強制決済理由：決済期日到来の作成<BR>
     * 　@２－１）　@強制決済理由情報インタスタンスを生成する。<BR>
     * 　@　@商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@強制決済理由区分：　@"期日到来"<BR>
     * 　@　@　@部店：　@引数.部店<BR>
     * <BR>
     * ２－２）　@２－１）の戻り値をArrayListに追加する。<BR>
     * <BR>
     * <BR>
     * ３）　@強制決済理由：保証金維持率割（オンライン開始前・軽度）の作成<BR>
     * 　@３－１）　@強制決済理由情報を作成する。<BR>
     * 　@　@商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@強制決済理由区分：　@"保証金維持率割れ（オンライン開始前・軽度）"<BR>
     * 　@　@　@部店：　@引数.部店<BR>
     * <BR>
     * 　@３－２）　@３－１）の戻り値がnullでない場合、ArrayListに追加する。<BR>
     * <BR>
     * <BR>
     * ４）　@強制決済理由：保証金維持率割（オンライン開始前・重度）の作成<BR>
     * 　@４－１）　@強制決済理由情報を作成する。<BR>
     * 　@　@商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@強制決済理由区分：　@"保証金維持率割れ（オンライン開始前・重度）"<BR>
     * 　@　@　@部店：　@引数.部店<BR>
     * <BR>
     * 　@４－２）　@４－１）の戻り値がnullでない場合、ArrayListに追加する。<BR>
     * <BR>
     * <BR>
     * ５）　@強制決済理由：保証金維持率割（オンライン開始前・法@定）の作成 <BR>
     * 　@５－１）　@強制決済理由情報を作成する。 <BR>
     * 　@　@商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@強制決済理由区分：　@"保証金維持率割れ（オンライン開始前・法@定）" <BR>
     * 　@　@　@部店：　@引数.部店<BR>
     * <BR>
     * 　@５－２）　@５－１）の戻り値がnullでない場合、ArrayListに追加する。<BR>
     * <BR>
     * <BR>
     * ６）　@強制決済理由：保証金維持率割（場間）の作成<BR>
     * 　@６－１）　@強制決済理由情報を作成する。<BR>
     * 　@　@商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@強制決済理由区分：　@"保証金維持率割れ（場間）"<BR>
     * 　@　@　@部店：　@引数.部店<BR>
     * <BR>
     * 　@６－２）　@６－１）の戻り値がnullでない場合、ArrayListに追加する。<BR>
     * <BR>
     * <BR>
     * ７）　@強制決済理由：手動強制決済の作成<BR>
     * 　@７－１）　@強制決済理由情報インタスタンスを生成する。<BR>
     * 　@　@商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@強制決済理由区分：　@"手動強制決済"<BR>
     * 　@　@　@部店：　@引数.部店<BR>
     * <BR>
     * 　@７－２）　@７－１）の戻り値をArrayListに追加する。<BR>
     * <BR>
     * <BR>
     * ８）　@生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_branch - (部店)<BR>
     * 部店オブジェクト<BR>
     * @@return WEB3AdminForcedSettleReasonUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 46022E76006C
     */
    protected WEB3AdminForcedSettleReasonUnit[] createForcedSettleReasonUnitList(Branch l_branch)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createForcedSettleReasonUnitList(Branch)";
        log.entering(STR_METHOD_NAME);

        // １）戻り値を格納するArrayListを生成する。
        List l_lisArrayList = new ArrayList();

        // ２）　@強制決済理由：決済期日到来の作成
        // ２－１）　@強制決済理由情報を作成する。
        // 商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。
        // 強制決済理由区分：　@"期日到来"
        // 部店：　@引数.部店 
        WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.FIXED_DATE_COMING,
                l_branch);

        // ２－２）　@２－１）の戻り値をArrayListに追加する。
        l_lisArrayList.add(l_forcedSettleReasonUnit);

        // ３）　@強制決済理由：保証金維持率割（オンライン開始前・軽度）の作成
        // 商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。
        // 強制決済理由区分：　@"保証金維持率割れ（オンライン開始前・軽度）"
        // 部店：　@引数.部店
        WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit1 =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS,
                l_branch);

        // 　@３－２）　@３－１）の戻り値がnullでない場合、ArrayListに追加する。
        if (l_forcedSettleReasonUnit1 != null)
        {
            l_lisArrayList.add(l_forcedSettleReasonUnit1);
        }

        // ４）　@強制決済理由：保証金維持率割（オンライン開始前・重度）の作成
        // ４－１）　@強制決済理由情報を作成する。
        // 商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。
        // 強制決済理由区分：　@"保証金維持率割れ（オンライン開始前・重度）"
        // 部店：　@引数.部店
        WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit2 =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS,
                l_branch);

        // ４－２）　@４－１）の戻り値がnullでない場合、ArrayListに追加する。
        if (l_forcedSettleReasonUnit2 != null)
        {
            l_lisArrayList.add(l_forcedSettleReasonUnit2);
        }

        // ５）　@強制決済理由：保証金維持率割（オンライン開始前・法@定）の作成
        // ５－１）　@強制決済理由情報を作成する。
        // 商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。
        // 強制決済理由区分：　@"保証金維持率割れ（オンライン開始前・法@定）"
        // 部店：　@引数.部店
        WEB3AdminForcedSettleReasonUnit l_adminForcedSettleReasonUnit =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL,
                l_branch);

        // ５－２）　@５－１）の戻り値がnullでない場合、ArrayListに追加する。
        if (l_adminForcedSettleReasonUnit != null)
        {
            l_lisArrayList.add(l_adminForcedSettleReasonUnit);
        }

        // ６）　@強制決済理由：保証金維持率割（場間）の作成
        // ６－１）　@強制決済理由情報を作成する。
        // 商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。
        // 強制決済理由区分：　@"保証金維持率割れ（場間）"
        // 部店：　@引数.部店
        WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit3 =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET,
                l_branch);

        // ６－２）　@６－１）の戻り値がnullでない場合、ArrayListに追加する。
        if (l_forcedSettleReasonUnit3 != null)
        {
            l_lisArrayList.add(l_forcedSettleReasonUnit3);
        }

        // ７）　@強制決済理由：手動強制決済の作成
        // 　@７－１）　@強制決済理由情報を作成する。
        // 商品管理（株式）データマネージャ.create強制決済理由情報()をcallする。
        // 強制決済理由区分：　@"手動強制決済"
        // 部店：　@引数.部店
        WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit4 =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE,
                l_branch);

        // ７－２）　@７－１）の戻り値をArrayListに追加する。
        if (l_forcedSettleReasonUnit4 != null)
        {
            l_lisArrayList.add(l_forcedSettleReasonUnit4);
        }

        // ８）　@生成したArrayList.toArray()の戻り値を返却する。
        WEB3AdminForcedSettleReasonUnit[] l_forcedSettleReasonUnitList =
            new WEB3AdminForcedSettleReasonUnit[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_forcedSettleReasonUnitList);

        log.exiting(STR_METHOD_NAME);
        return l_forcedSettleReasonUnitList;
    }
    
    /**
     * 翌営業日 + 当営業日（業務日付）を含めた
     * 過去30日の間の営業日を降順。
     * @@return Date[]
     * @@throws WEB3BaseException
     */
    private Date[] createOrderBizDateList()
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOrderBizDateList()";
        log.entering(STR_METHOD_NAME);

        //当営業日
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        //翌営業日
        Date l_datNextBizDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(1);

        List l_lisArrayList = new ArrayList();

        l_lisArrayList.add(l_datNextBizDate);
        l_lisArrayList.add(l_datBizDate);

        //過去30日の間の営業日を降順。
        Date[] l_datOrderBizDates = new Date[30];
        for(int i = 0; i < 30; i++)
        {
            l_datOrderBizDates[i] = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(-(i+1));

            Date l_datOrderBizDate = WEB3DateUtility.addDay(l_datOrderBizDates[i], 30);
            int l_intReturn = WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datBizDate);
            if (l_intReturn < 0)
            {
                break;
            }

            l_lisArrayList.add(l_datOrderBizDates[i]);
        }

        Date[] l_datOrderBizDateList = new Date[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_datOrderBizDateList);

        log.exiting(STR_METHOD_NAME);
        return l_datOrderBizDateList;
    }
}
@
