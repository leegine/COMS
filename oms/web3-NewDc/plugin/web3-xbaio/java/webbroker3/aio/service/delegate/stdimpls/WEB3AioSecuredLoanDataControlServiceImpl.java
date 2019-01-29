head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecuredLoanDataControlServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローンデータ制御サービスImpl(WEB3AioSecuredLoanDataControlServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 柴双紅 (中訊) 新規作成 仕様変更 モデルNo.755、No.761、No.776
Revision History : 2007/09/20 柴双紅 (中訊) モデルNo.786、No.787、No.788、No.795、No.796
Revision History : 2007/10/18 柴双紅 (中訊) モデルNo.809
Revision History : 2007/10/25 柴双紅 (中訊) モデルNo.815
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import webbroker3.aio.define.WEB3AioSecuredLoanOfferStateSortKeyDef;
import webbroker3.aio.message.WEB3SLSortKey;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanDataControlService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.gentrade.data.CommSerialNumbersPK;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (証券担保ローンデータ制御サービスImpl)<BR>
 * 証券担保ローンデータ制御サービスImpl<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AioSecuredLoanDataControlServiceImpl implements WEB3AioSecuredLoanDataControlService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecuredLoanDataControlServiceImpl.class);

    /**
     * @@roseuid 46E0BE470242
     */
    public WEB3AioSecuredLoanDataControlServiceImpl()
    {

    }

    /**
     * (insert株券担保ローン)<BR>
     * 株券担保ローン口座テーブルにinsert処理を行う。<BR>
     * <BR>
     * 挿入する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「証券担保ローン_株券担保ローン口座テーブル.xls」<BR>
     * <BR>
     * @@param l_strStockLoanAccountCode - (ストックローン口座番号)<BR>
     * ストックローン口座番号<BR>
     * @@param l_mainAccountParams - (顧客Params)<BR>
     * 顧客Params<BR>
     * @@throws WEB3BaseException
     */
    public void insertStockSecuredLoan(String l_strStockLoanAccountCode,
        MainAccountParams l_mainAccountParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertStockBondSecurityLoan(String, MainAccountParams)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccountParams == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // 株券担保ローン口座テーブルにinsert処理を行う。
        // 挿入する行の内容は下記を参照。
        // 「証券担保ローン_株券担保ローン口座テーブル.xls」
        StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();

        // ストックローン口座番号:  引数.ストックローン口座番号
        l_stockSecuredLoanParams.setStockLoanAccountCode(l_strStockLoanAccountCode);

        // 口座ID:  get顧客行.口座ID
        l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());

        // 証券会社コード:  get顧客行.証券会社コード
        l_stockSecuredLoanParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());

        // 部店コード:  get顧客行.部店コード
        l_stockSecuredLoanParams.setBranchCode(l_mainAccountParams.getBranchCode());

        // 口座コード:  get顧客行.口座コード
        l_stockSecuredLoanParams.setAccountCode(l_mainAccountParams.getAccountCode());

        // 開設状況:  0：申込
        l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.REQUEST);

        // 申込日時:  現在日時
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_stockSecuredLoanParams.setAppliDate(l_tsSystemTime);

        // 申込時Y客情報:  get顧客行.Ｙ客区分
        l_stockSecuredLoanParams.setYCustomerData(l_mainAccountParams.getYellowCustomer());

        // 申込時ロック客情報（考査ロック）:  get顧客行.考査ロック
        l_stockSecuredLoanParams.setExaminLockFlag(l_mainAccountParams.getExaminLockFlag());

        // 申込時ロック客情報（支店ロック）:  get顧客行.支店ロック
        l_stockSecuredLoanParams.setBranchLock(l_mainAccountParams.getBranchLock());

        // 申込時ロック客情報（管理ロック）:  get顧客行.管理ロック
        l_stockSecuredLoanParams.setMngLockFlag(l_mainAccountParams.getMngLockFlag());

        // 申込時ロック客情報（管理ロック理由・立替金）
        // get顧客行.管理ロック理由フラグ（立替金）
        l_stockSecuredLoanParams.setMngLockFlagAdvance(l_mainAccountParams.getMngLockFlagAdvance());

        // 申込時ロック客情報（管理ロック理由・保証金未入）
        // get顧客行.管理ロック理由フラグ（保証金未入）
        l_stockSecuredLoanParams.setMngLockFlagUnpayMargin(l_mainAccountParams.getMngLockFlagUnpayMargin());

        // 申込時ロック客情報（管理ロック理由・適格担保不足）
        // get顧客行.管理ロック理由フラグ（適格担保不足）
        l_stockSecuredLoanParams.setMngLockFlagShortSecurity(l_mainAccountParams.getMngLockFlagShortSecurity());

        // 申込時ロック客情報（管理ロック理由・預り証長期未差換）
        // get顧客行.管理ロック理由フラグ（預り証長期未差換）
        l_stockSecuredLoanParams.setMngLockFlagUnsubstitDepo(l_mainAccountParams.getMngLockFlagUnsubstitDepo());

        // 更新者コード:  セッションより取得したログインID
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        long l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();
        l_stockSecuredLoanParams.setLastUpdater(l_lngLoginId + "");

        // 作成日時:  現在日時
        l_stockSecuredLoanParams.setCreatedTimestamp(l_tsSystemTime);

        // 更新日時:  現在日時
        l_stockSecuredLoanParams.setLastUpdatedTimestamp(l_tsSystemTime);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBアクセスエラー", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get株券担保ローン顧客情報)<BR>
     * 株券担保ローン口座オブジェクトを返却する。<BR>
     * 株券担保ローン口座テーブルよりデータを取得する。<BR>
     * <BR>
     * １）　@Object配列を生成し、以下を要素に設定<BR>
     * Object[0]（引数）口座ID<BR>
     * Object[1]（引数）申込日時<BR>
     * <BR>
     * ２）　@株券担保ローン口座テーブルにレコードが存在するか検索する。<BR>
     * QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * [doFindAllQuery()にセットするパラメータ]<BR>
     * arg0： 株券担保ローン口座テーブルRowType<BR>
     * arg1： "account_id=?<BR>
     * and to_char(appli_date, 'YYYYMMDD')=?"<BR>
     * <BR>
     * ３）　@２）の戻り値Listの長さ > 0 の場合、２）の戻り値を返却する。<BR>
     * <BR>
     * ４）　@２）の戻り値Listの長さ = 0 の場合、nullを返却する。<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_tsDate - (日付)<BR>
     * YYYYMMDD<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getStockSecuredLoanAccInfo(long l_lngAccountId, Timestamp l_tsDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getStockBondSecurityLoanAccInfo(long, Timestamp)";
        log.entering(STR_METHOD_NAME);

        // 株券担保ローン口座オブジェクトを返却する。
        // １） 株券担保ローン口座テーブルよりデータを取得する。
        // １） Object配列を生成し、以下を要素に設定
        Object[] l_values = new Object[]{
            new Long(
                l_lngAccountId),
                WEB3DateUtility.formatDate(l_tsDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD)};
        String l_strSql = " account_id=? and to_char(appli_date, 'YYYYMMDD') = ? ";

        List l_lisRecords = new ArrayList(); 
        // ２） 株券担保ローン口座テーブルにレコードが存在するか検索する。
        // QueryProcessor.doFindAllQuery()メソッドをコールする。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                StockSecuredLoanRow.TYPE,
                l_strSql,
                l_values);
        }
        catch (DataQueryException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBアクセスエラー", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ３） ２）の戻り値Listの長さ > 0 の場合、２）の戻り値を返却する。
        int l_intLength = l_lisRecords.size();
        if (l_intLength > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return l_lisRecords;
        }
        // ４） ２）の戻り値Listの長さ = 0 の場合、nullを返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get株券担保ローン一覧)<BR>
     * 指定された条件に合致する株券担保ローン口座テーブルを検索し、<BR>
     * その結果を株券担保ローンParamsオブジェクトの配列にして返却する。<BR>
     * <BR>
     * 1) ソート条件の作成<BR>
     * 　@引数.ソート条件がnull以外であり、かつ要素数＞0の場合、<BR>
     * 　@引数.ソート条件の件数分、以下を繰り返す。<BR>
     *  1-1) 対応するテーブルの列物理名を昇順／降順指定を付加しセットする。<BR>
     * <BR>
     * 　@　@○キー項目とテーブルの列名称との対応は以下の通り。<BR>
     * 　@　@　@※キー項目文字列(シンボル名)は、メッセージ定義書を参照。<BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照。<BR>
     * <BR>
     * 　@　@　@　@・顧客　@　@　@　@　@　@　@　@　@　@=株券担保ローン口座テーブル.顧客コード<BR>
     * 　@　@　@　@・ストックローン口座番号　@=株券担保ローン口座テーブル.ストックローン口座番号<BR>
     * 　@　@　@　@・申込日　@　@　@　@　@　@　@　@　@=株券担保ローン口座テーブル.申込日<BR>
     * <BR>
     * 　@　@○昇順／降順指定は、ソートキー.昇順／降順 指定に従い設定する。<BR>
     * <BR>
     * 2) 以下の検索条件で、「株券担保ローン口座テーブル」を検索する。<BR>
     * 　@[検索条件] <BR>
     * 　@　@○証券会社コード=引数.証券会社コード <BR>
     * 　@　@○部店コード=引数.部店コード<BR>
     * 　@　@　@　@---------（ただし、引数.部店コードがnullでは無い場合に限る）<BR>
     * 　@　@○口座コード like 引数.顧客コード + "%" <BR>
     * 　@　@　@　@---------（ただし、引数.顧客コードがnullでは無い場合に限る）<BR>
     * 　@　@○ストックローン口座番号 like 引数.ストックローン口座番号 + "%"<BR>
     * 　@　@　@（前方一致検索を行う。ただし、引数.ストックローン口座番号がnullでは無い場合に限る）<BR>
     * 　@　@○申込状況=引数.申込状況<BR>
     * 　@　@　@　@---------（ただし、引数.申込状況がnullでは無い場合に限る）<BR>
     * 　@　@○申込日<BR>
     * 　@　@　@*申込日From != null && 申込日To == null場合<BR>
     * 　@　@　@ 申込日(YYYYMMDD) >= 引数.申込日From<BR>
     * 　@　@　@*申込日From == null && 申込日To != null場合<BR>
     * 　@　@　@ 申込日(YYYYMMDD) <= 引数.申込日To<BR>
     * 　@　@　@*申込日From != null && 申込日To != null場合<BR>
     * 　@　@　@ 申込日(YYYYMMDD) between 引数.申込日From and 引数.申込日To<BR>
     * <BR>
     * 　@　@○開設日<BR>
     * 　@　@　@*開設日From != null && 開設日To == null場合<BR>
     * 　@　@　@ 開設日 >= 引数.開設日From<BR>
     * 　@　@　@*開設日From == null && 開設日To != null場合<BR>
     * 　@　@　@ 開設日 <= 引数.開設日To<BR>
     * 　@　@　@*開設日From != null && 開設日To != null場合<BR>
     * 　@　@　@ 開設日 between 引数.開設日From and 引数.開設日To<BR>
     * <BR>
     * 　@　@○成約日<BR>
     * 　@　@　@*成約日From != null && 成約日To == null場合<BR>
     * 　@　@　@ 成約日(YYYYMMDD) >= 引数.成約日From<BR>
     * 　@　@　@*成約日From == null && 成約日To != null場合<BR>
     * 　@　@　@ 成約日(YYYYMMDD) <= 引数.成約日To<BR>
     * 　@　@　@*成約日From != null && 成約日To != null場合<BR>
     * 　@　@　@ 成約日(YYYYMMDD) between 引数.成約日From and 引数.成約日To<BR>
     * <BR>
     * 　@　@○解約日<BR>
     * 　@　@　@*解約日From != null && 解約日To == null場合<BR>
     * 　@　@　@ 解約日(YYYYMMDD) >= 引数.解約日From<BR>
     * 　@　@　@*解約日From == null && 解約日To != null場合<BR>
     * 　@　@　@ 解約日(YYYYMMDD) <= 引数.解約日To<BR>
     * 　@　@　@*解約日From != null && 解約日To != null場合<BR>
     * 　@　@　@ 解約日(YYYYMMDD) between 引数.解約日From and 引数.解約日To<BR>
     * <BR>
     * 　@　@○閉鎖日<BR>
     * 　@　@　@*閉鎖日From != null && 閉鎖日To == null場合<BR>
     * 　@　@　@ 閉鎖日 >= 引数.閉鎖日From<BR>
     * 　@　@　@*閉鎖日From == null && 閉鎖日To != null場合<BR>
     * 　@　@　@ 閉鎖日 <= 引数.閉鎖日To<BR>
     * 　@　@　@*閉鎖日From != null && 閉鎖日To != null場合<BR>
     * 　@　@　@ 閉鎖日 between 引数.閉鎖日From and 引数.閉鎖日To<BR>
     * <BR>
     * 　@[並び順]<BR>
     * 　@　@1)で生成したソート条件<BR>
     * <BR>
     * 3) 2)の検索結果を返却する。<BR>
     * <BR>
     * @@param l_stockSecuredLoans - (株券担保ローン配列[])<BR>
     * 株券担保ローン配列[]<BR>
     * @@return StockSecuredLoanParams[]
     * @@throws WEB3BaseException
     * @@roseuid 46CE708F01B3
     */
    public StockSecuredLoanParams[] getStockSecuredLoanList(Object[] l_stockSecuredLoans)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getStockBondSecurityLoanListo(Object[])";
        log.entering(STR_METHOD_NAME);

        //ソート条件の作成
        //引数.ソート条件がnull以外であり、かつ要素数＞0の場合
        StringBuffer l_sbSortKey = new StringBuffer();
        if (l_stockSecuredLoans[15] != null
            && ((Object[])l_stockSecuredLoans[15]).length > 0)
        {
            Object[] l_queryValues = (Object[])l_stockSecuredLoans[15];
            for (int i = 0; i < l_queryValues.length; i++)
            {
                WEB3SLSortKey l_sortKey = (WEB3SLSortKey)l_queryValues[i];
                // ・顧客　@　@　@　@　@　@　@　@　@　@=引数.顧客コード
                if (WEB3AioSecuredLoanOfferStateSortKeyDef.ACCOUNT_CODE.equals(l_sortKey.keyItem))
                {
                    l_sbSortKey.append(" account_code ");
                }
                // ・ストックローン口座番号　@=引数.ストックローン口座番号
                else if (WEB3AioSecuredLoanOfferStateSortKeyDef.STOCK_LOAN_ACCOUNT.equals(l_sortKey.keyItem))
                {
                    l_sbSortKey.append(" stock_loan_account_code ");
                }
                //・申込日　@　@　@　@　@　@　@　@　@=引数.申込日
                else if (WEB3AioSecuredLoanOfferStateSortKeyDef.APPLY_DATE.equals(l_sortKey.keyItem))
                {
                    l_sbSortKey.append(" appli_date ");
                }
                else
                {
                    continue;
                }

                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_sbSortKey.append(" ASC ");
                }
                else
                {
                    l_sbSortKey.append(" DESC ");
                }

                if (i < l_queryValues.length - 1)
                {
                    l_sbSortKey.append(" , ");
                }
            }
        }

        //以下の検索条件で、「株券担保ローン口座テーブル」を検索する。
        //[検索条件]
        StringBuffer l_sbSql = new StringBuffer();
        List l_lisValues = new ArrayList();

        //　@　@○証券会社コード=引数.証券会社コード
        l_sbSql.append(" institution_code = ? ");
        l_lisValues.add(l_stockSecuredLoans[0]);

        //　@　@○部店コード=引数.部店コード
        //---------（ただし、引数.部店コードがnullでは無い場合に限る）
        if (l_stockSecuredLoans[1] != null)
        {
            l_sbSql.append(" and branch_code = ? ");
            l_lisValues.add(l_stockSecuredLoans[1]);
        }

        //　@　@○口座コード like 引数.顧客コード + "%"
        //---------（ただし、引数.顧客コードがnullでは無い場合に限る）
        if (l_stockSecuredLoans[2] != null)
        {
            l_sbSql.append(" and account_code like ? || '%' ");
            l_lisValues.add(l_stockSecuredLoans[2]);
        }

        //　@　@○ストックローン口座番号 like 引数.ストックローン口座番号 + "%"
        //       （前方一致検索を行う。ただし、引数.ストックローン口座番号がnullでは無い場合に限る）
        if (l_stockSecuredLoans[3] != null)
        {
            l_sbSql.append(" and stock_loan_account_code like ? || '%' ");
            l_lisValues.add(l_stockSecuredLoans[3]);
        }

        //　@　@○申込状況=引数.申込状況
        //---------（ただし、引数.申込状況がnullでは無い場合に限る）
        if (l_stockSecuredLoans[4] != null)
        {
            l_sbSql.append(" and account_open_status = ? ");
            l_lisValues.add(l_stockSecuredLoans[4]);
        }

        //　@　@○申込日
        //　@　@　@*申込日From != null && 申込日To == null場合
        //　@　@　@ 申込日(YYYYMMDD) >= 引数.申込日From
        if (l_stockSecuredLoans[5] != null && l_stockSecuredLoans[6] == null)
        {
            l_sbSql.append(" and to_date(to_char(appli_date, 'YYYYMMDD')) >= ? ");
            l_lisValues.add(l_stockSecuredLoans[5]);
        }

        //　@　@　@*申込日From == null && 申込日To != null場合
        //　@　@　@ 申込日(YYYYMMDD) <= 引数.申込日To
        if (l_stockSecuredLoans[5] == null && l_stockSecuredLoans[6] != null)
        {
            l_sbSql.append(" and to_date(to_char(appli_date, 'YYYYMMDD')) <= ? ");
            l_lisValues.add(l_stockSecuredLoans[6]);
        }

        //　@　@　@*申込日From != null && 申込日To != null場合
        //　@　@　@ 申込日(YYYYMMDD) between 引数.申込日From and 引数.申込日To
        if (l_stockSecuredLoans[5] != null && l_stockSecuredLoans[6] != null)
        {
            l_sbSql.append(" and to_date(to_char(appli_date, 'YYYYMMDD')) between ? and ? ");
            l_lisValues.add(l_stockSecuredLoans[5]);
            l_lisValues.add(l_stockSecuredLoans[6]);
        }

        //　@　@○開設日
        //　@　@　@*開設日From != null && 開設日To == null場合
        //　@　@　@ 開設日 >= 引数.開設日From
        if (l_stockSecuredLoans[7] != null && l_stockSecuredLoans[8] == null)
        {
            l_sbSql.append(" and account_open_date >= ? ");
            l_lisValues.add(l_stockSecuredLoans[7]);
        }

        //　@　@　@*開設日From == null && 開設日To != null場合
        //　@　@　@ 開設日 <= 引数.開設日To
        if (l_stockSecuredLoans[7] == null && l_stockSecuredLoans[8] != null)
        {
            l_sbSql.append(" and account_open_date <= ? ");
            l_lisValues.add(l_stockSecuredLoans[8]);
        }

        //　@　@　@*開設日From != null && 開設日To != null場合
        //　@　@　@ 開設日 between 引数.開設日From and 引数.開設日To
        if (l_stockSecuredLoans[7] != null && l_stockSecuredLoans[8] != null)
        {
            l_sbSql.append(" and account_open_date between ? and ? ");
            l_lisValues.add(l_stockSecuredLoans[7]);
            l_lisValues.add(l_stockSecuredLoans[8]);
        }

        //　@　@○成約日
        //　@　@　@*成約日From != null && 成約日To == null場合
        //　@　@　@ 成約日(YYYYMMDD) >= 引数.成約日From
        if (l_stockSecuredLoans[9] != null && l_stockSecuredLoans[10] == null)
        {
            l_sbSql.append(" and to_date(to_char(order_data_reception_date, 'YYYYMMDD')) >= ? ");
            l_lisValues.add(l_stockSecuredLoans[9]);
        }

        //　@　@　@*成約日From == null && 成約日To != null場合
        //　@　@　@ 成約日(YYYYMMDD) <= 引数.成約日To
        if (l_stockSecuredLoans[9] == null && l_stockSecuredLoans[10] != null)
        {
            l_sbSql.append(" and to_date(to_char(order_data_reception_date, 'YYYYMMDD')) <= ? ");
            l_lisValues.add(l_stockSecuredLoans[10]);
        }

        //　@　@　@*成約日From != null && 成約日To != null場合
        //　@　@　@ 成約日(YYYYMMDD) between 引数.成約日From and 引数.成約日To
        if (l_stockSecuredLoans[9] != null && l_stockSecuredLoans[10] != null)
        {
            l_sbSql.append(" and to_date(to_char(order_data_reception_date, 'YYYYMMDD')) between ? and ? ");
            l_lisValues.add(l_stockSecuredLoans[9]);
            l_lisValues.add(l_stockSecuredLoans[10]);
        }

        //　@　@○解約日
        //　@　@　@*解約日From != null && 解約日To == null場合
        //　@　@　@ 解約日(YYYYMMDD) >= 引数.解約日From
        if (l_stockSecuredLoans[11] != null && l_stockSecuredLoans[12] == null)
        {
            l_sbSql.append(" and to_date(to_char(cancel_data_reception_date, 'YYYYMMDD')) >= ? ");
            l_lisValues.add(l_stockSecuredLoans[11]);
        }

        //　@　@　@*解約日From == null && 解約日To != null場合
        //　@　@　@ 解約日(YYYYMMDD) <= 引数.解約日To
        if (l_stockSecuredLoans[11] == null && l_stockSecuredLoans[12] != null)
        {
            l_sbSql.append(" and to_date(to_char(cancel_data_reception_date, 'YYYYMMDD')) <= ? ");
            l_lisValues.add(l_stockSecuredLoans[12]);
        }

        //　@　@　@*解約日From != null && 解約日To != null場合
        //　@　@　@ 解約日(YYYYMMDD) between 引数.解約日From and 引数.解約日To
        if (l_stockSecuredLoans[11] != null && l_stockSecuredLoans[12] != null)
        {
            l_sbSql.append(" and to_date(to_char(cancel_data_reception_date, 'YYYYMMDD')) between ? and ? ");
            l_lisValues.add(l_stockSecuredLoans[11]);
            l_lisValues.add(l_stockSecuredLoans[12]);
        }

        //　@　@○閉鎖日
        //　@　@　@*閉鎖日From != null && 閉鎖日To == null場合
        //　@　@　@ 閉鎖日 >= 引数.閉鎖日From
        if (l_stockSecuredLoans[13] != null && l_stockSecuredLoans[14] == null)
        {
            l_sbSql.append(" and close_date >= ? ");
            l_lisValues.add(l_stockSecuredLoans[13]);
        }

        //　@　@　@*閉鎖日From == null && 閉鎖日To != null場合
        //　@　@　@ 閉鎖日 <= 引数.閉鎖日To
        if (l_stockSecuredLoans[13] == null && l_stockSecuredLoans[14] != null)
        {
            l_sbSql.append(" and close_date <= ? ");
            l_lisValues.add(l_stockSecuredLoans[14]);
        }

        //　@　@　@*閉鎖日From != null && 閉鎖日To != null場合
        //　@　@　@ 閉鎖日 between 引数.閉鎖日From and 引数.閉鎖日To
        if (l_stockSecuredLoans[13] != null && l_stockSecuredLoans[14] != null)
        {
            l_sbSql.append(" and close_date between ? and ? ");
            l_lisValues.add(l_stockSecuredLoans[13]);
            l_lisValues.add(l_stockSecuredLoans[14]);
        }

        //　@[並び順]
        //　@　@1)で生成したソート条件
        Object[] l_sqlValues = new Object[l_lisValues.size()];
        l_lisValues.toArray(l_sqlValues);
        String l_strSortKey = null;
        if (!WEB3StringTypeUtility.isEmpty(l_sbSortKey.toString()))
        {
            l_strSortKey = l_sbSortKey.toString();
        }
        List l_lisQueryRows;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisQueryRows = l_queryProcessor.doFindAllQuery(
                StockSecuredLoanRow.TYPE,
                l_sbSql.toString(),
                l_strSortKey,
                null,
                l_sqlValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
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
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //3) 2)の検索結果を返却する。
        StockSecuredLoanParams[] l_stockSecuredLoanParams =
            new StockSecuredLoanParams[l_lisQueryRows.size()];
        if (!l_lisQueryRows.isEmpty())
        {
            for (int i = 0; i < l_lisQueryRows.size(); i++)
            {
                StockSecuredLoanRow l_stockSecuredLoanRow =
                    (StockSecuredLoanRow)l_lisQueryRows.get(i);
                l_stockSecuredLoanParams[i] =
                    new StockSecuredLoanParams(l_stockSecuredLoanRow);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_stockSecuredLoanParams;
    }

    /**
     * (update採番テーブル)<BR>
     * 以下の更新条件を元に、採番テーブルテーブルにupdate処理を行う。<BR>
     * <BR>
     * 【更新条件】<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@採番項目名 = 引数.採番項目名<BR>
     * <BR>
     * 更新内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「証券担保ローン_採番テーブル.xls」<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strSerialNumberName - (採番項目名)<BR>
     * 採番項目名<BR>
     * @@param l_strSerialNumber - (シリアルナンバー)<BR>
     * シリアルナンバー<BR>
     * @@throws WEB3BaseException
     */
    public void updateCommSerialNumbers(
        String l_strInstitutionCode,
        String l_strSerialNumberName,
        String l_strSerialNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateCommSerialNumbers(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //【更新条件】
            //証券会社コード = 引数.証券会社コード
            //採番項目名 = 引数.採番項目名
            CommSerialNumbersPK l_commSerialNumbersPK =
                new CommSerialNumbersPK(l_strInstitutionCode, l_strSerialNumberName);
            Map l_mapSpac = new HashMap();

            //採番コード:  引数.シリアルナンバー
            l_mapSpac.put("serial_number", l_strSerialNumber);

            //更新日時:  現在日時
            l_mapSpac.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(
                l_commSerialNumbersPK,
                l_mapSpac);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
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
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
