head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴データマネージャ(WEB3HistoryTradeHistoryDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26範慧琴 (中訊) 新規作成
Revesion History : 2004/10/29賈元春 (中訊) 作成
Revesion History : 2004/11/11賈元春 (中訊) 作成
                   2006/10/19  張騰宇 (中訊) モデル 056
*/
package webbroker3.tradehistory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BookValueRecordDivDef;
import webbroker3.common.define.WEB3BookValueRemarkDef;
import webbroker3.common.define.WEB3DepositMarginDivDef;
import webbroker3.common.define.WEB3ProfitLossRecordDivDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.tradehistory.data.BookValueSpecParams;
import webbroker3.tradehistory.data.BookValueSpecRow;
import webbroker3.tradehistory.data.ProfitLossSpecParams;
import webbroker3.tradehistory.data.ProfitLossSpecRow;
import webbroker3.tradehistory.data.SettleDetailHistoryDao;
import webbroker3.tradehistory.data.SettleDetailHistoryParams;
import webbroker3.tradehistory.data.SettleDetailHistoryRow;
import webbroker3.tradehistory.data.TradeDetailHistoryDao;
import webbroker3.tradehistory.data.TradeDetailHistoryParams;
import webbroker3.tradehistory.data.TradeDetailHistoryRow;
import webbroker3.tradehistory.data.TradeHistoryRow;
import webbroker3.tradehistory.data.TransactionHistoryRow;
import webbroker3.tradehistory.define.WEB3PlsBvsDetailOrderRecDef;
import webbroker3.tradehistory.define.WEB3PlsBvsDisplayTermDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryTransactionDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (取引履歴データマネージャ)<BR>
 * 取引履歴のDB I/Oを管理するクラス<BR>
 *
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3HistoryTradeHistoryDataManager
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3HistoryTradeHistoryDataManager.class);


    /**
     * @@roseuid 41789C2F01C5
     */
    public WEB3HistoryTradeHistoryDataManager()
    {

    }

    /**
     * ( get取引履歴一覧)<BR>
     * (staticメソッド)<BR>
     * 指定された検索条件に該当する取引履歴テーブルのデータを取得し、返却する。<BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()をコールする。<BR>
     * <BR>
     * [doFindAllQuery()にセットするパラメータ]<BR>
     * arg0：　@”取引履歴テーブル(trade_history)”<BR>
     * arg1：　@パラメータ.検索条件文字列<BR>
     * arg2：　@パラメータ.ソート条件<BR>
     * arg3：　@null<BR>
     * arg4：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * ２）検索結果を返却する。<BR>
     * <BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_strQueryDataContainer - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 413C1B470250
     */
    public static List getTradeHistoryList(String l_strQueryString, String[] l_strQueryDataContainer, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradeHistoryList()";

        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {

            //１）QueryProcessor.doFindAllQuery()をコールする。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradeHistoryRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryDataContainer
                );
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        //２）検索結果を返却する。
        return l_lisRecords;
    }

    /**
     * (get顧客勘定残高履歴一覧)<BR>
     * (staticメソッド)<BR>
     * 引数の条件に該当する顧客勘定残高履歴データを<BR>
     * 返却する。<BR>
     * <BR>
     * １）以下の条件で、顧客勘定残高履歴テーブルを検索する。<BR>
     * <BR>
     * 　@【検索条件】<BR>
     * 　@証券会社コード = パラメータ.証券会社コード　@かつ<BR>
     * 　@部店コード = パラメータ.部店コード　@かつ<BR>
     * 　@顧客コード = パラメータ.口座コード　@かつ<BR>
     * 　@受渡日 = パラメータ.受渡日　@かつ<BR>
     * 　@預り区分 IN ("1：預り金", "4：保証金",<BR>
     * 　@　@　@　@　@　@　@　@　@　@"5：先物証拠金", "M：MRF")<BR>
     * <BR>
     * 　@１－１）上記条件に該当する検索条件文字列を作成する。<BR>
     * 　@　@　@　@検索条件文字列 = "institution_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and branch_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and account_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and delivery_date = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and deposit_margin_div in (?, ?, ?, ?) "<BR>
     * <BR>
     * 　@１－２）ArrayListを生成する。<BR>
     * 　@１－３）検索条件文字列の"?"にセットするパラメータを<BR>
     * 　@　@　@　@　@以下の順で生成したArrayListに追加する。<BR>
     * 　@　@　@　@　@　@・パラメータ.証券会社コード<BR>
     * 　@　@　@　@　@　@・パラメータ.部店コード<BR>
     * 　@　@　@　@　@　@・パラメータ.口座コード<BR>
     * 　@　@　@　@　@　@・パラメータ.受渡日<BR>
     * 　@　@　@　@　@　@・"1：預り金"<BR>
     * 　@　@　@　@　@　@・"4：保証金"<BR>
     * 　@　@　@　@　@　@・"5：先物証拠金"<BR>
     * 　@　@　@　@　@　@・"M：MRF"<BR>
     * 　@１－４）QueryProcessor.doFindAllQuery()メソッドをコールし、<BR>
     * 　@　@　@　@　@DBを検索する。<BR>
     * <BR>
     * 　@　@　@　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@　@　@　@arg0：　@"顧客勘定残高履歴テーブル(transaction_history)"<BR>
     * 　@　@　@　@　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@　@　@　@　@arg2：　@作成したArrayList.toArray()の戻り値<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ２）取得した検索結果を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 413C1E7201C9
     */
    public static List getTransactionHistoryList(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, Date l_datDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTransactionHistoryList()";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //１－１）検索条件文字列を作成する
            String l_strWhere = "institution_code = ? " +
                 "and branch_code = ? " +
                 "and account_code = ? " +
                 "and delivery_date = ? " +
                 "and deposit_margin_div in (?, ?, ?, ?) ";

            Object l_bindVars[] = {l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_datDeliveryDate,
                WEB3DepositMarginDivDef.FROM_DEPOSIT,
                WEB3DepositMarginDivDef.GUARANTEE,
                WEB3DepositMarginDivDef.FUTURES_DEPOSIT,
                WEB3DepositMarginDivDef.MRF
                };

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TransactionHistoryRow.TYPE,
                l_strWhere,
                l_bindVars
                );

            //検索結果が取得できなかった場合は、nullを返却する。<BR>
            if (l_lisRecords != null)
            {
                int l_intCnt = l_lisRecords.size();
                if (l_intCnt == 0)
                {
                    l_lisRecords = null;
                }
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        //２）取得した検索結果を返却する。
        return l_lisRecords;
    }

    /**
     * (get取引明細履歴)<BR>
     * (staticメソッド)<BR>
     * 引数の明細管理番号に該当する<BR>
     * 取引明細履歴Paramsを返却する。<BR>
     * <BR>
     * １）取引明細履歴テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@【検索条件】<BR>
     * 　@取引明細履歴ID　@＝　@パラメータ.明細管理番号<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ２）検索結果を返却する。<BR>
     * <BR>
     * @@param l_strDetailsManagementNo - (明細管理番号)<BR>
     * 明細管理番号<BR>
     * @@return 取引明細履歴Params
     * @@throws WEB3BaseException
     * @@roseuid 413C23FE00E0
     */
    public static TradeDetailHistoryParams getTradeDetailHistory(String l_strDetailsManagementNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradeDetailHistory()";
        log.entering(STR_METHOD_NAME);

        TradeDetailHistoryParams l_tradeDetailHistoryParams = null;
        if (l_strDetailsManagementNo == null)
        {
            return l_tradeDetailHistoryParams;
        }

        try
        {
            TradeDetailHistoryRow l_tradeDetailHistoryRow =
                TradeDetailHistoryDao.findRowByTradeDetailHistoryId(Long.parseLong(l_strDetailsManagementNo));
            if (l_tradeDetailHistoryRow != null)
            {
                l_tradeDetailHistoryParams = new TradeDetailHistoryParams(l_tradeDetailHistoryRow);
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに重複する該当データが存在します", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        //２）取得した検索結果を返却する。
        return l_tradeDetailHistoryParams;

    }

    /**
     * (get決済明細履歴)<BR>
     * (staticメソッド)<BR>
     * 引数の明細管理番号に該当する<BR>
     * 決済明細履歴Paramsを返却する。<BR>
     * <BR>
     * １）決済明細履歴テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@【検索条件】<BR>
     * 　@決済明細履歴ID　@＝　@パラメータ.明細管理番号<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ２）検索結果を返却する。<BR>
     * <BR>
     * @@param l_strDetailsManagementNo - (明細管理番号)<BR>
     * 明細管理番号<BR>
     * @@return SettleDetailHistoryParams
     * @@throws WEB3BaseException
     * @@roseuid 413C2C2700EB
     */
    public static SettleDetailHistoryParams getSettleDetailHistory(String l_strDetailsManagementNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettleDetailHistory()";
        log.entering(STR_METHOD_NAME);

        SettleDetailHistoryParams l_settleDetailHistoryParams = null;
        if (l_strDetailsManagementNo == null)
        {
            return l_settleDetailHistoryParams;
        }

        try
        {
            SettleDetailHistoryRow l_settleDetailHistoryRow =
                SettleDetailHistoryDao.findRowBySettleDetailHistoryId(Long.parseLong(l_strDetailsManagementNo));
            if (l_settleDetailHistoryRow != null)
            {
                l_settleDetailHistoryParams = new SettleDetailHistoryParams(l_settleDetailHistoryRow);
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに重複する該当データが存在します。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        //２）取得した検索結果を返却する。
        return l_settleDetailHistoryParams;
    }

    /**
     * (get損益明細件数)<BR>
     *顧客に該当する損益明細データの件数を返却する。<BR> 
     *<BR>
     *１）2ヶ月表示（パラメータ.処理区分 = null）の場合 <BR>
     *<BR>
     *　@１－１）検索条件文字列&検索条件データコンテナ  <BR>
     *　@　@以下の検索条件を表す、検索条件文字列と  <BR>
     *　@　@ArrayList(検索条件データコンテナ)を作成する。  <BR>
     *<BR>
     *　@　@[検索条件]  <BR>
     *　@　@　@証券会社コード = パラメータ.顧客.証券会社コード　@かつ  <BR>
     *　@　@　@部店コード = パラメータ.顧客.部店コード　@かつ <BR>
     *　@　@　@顧客コード = パラメータ.顧客.口座コード　@かつ <BR>
     *　@　@　@( <BR>
     *　@　@　@　@レコード区分 = "10：残高レコード"　@または <BR>
     *　@　@　@　@(レコード区分 in ("20：明細レコード", "21：入出金レコード") <BR>
     * 　@　@　@　@かつ　@計算年月日 <= 作業年月日) <BR><BR>
     *　@　@　@) <BR>
     *<BR>
     *　@１－２）上記検索条件を基に、検索条件文字列を作成する。  <BR>
     *<BR>
     *　@　@　@検索条件文字列 = "institution_code = ? and "  <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "branch_code = ? and "  <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "account_code = ? and "  <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(rec_div = ? or "  <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(rec_div in (?, ?) and calc_date <= work_date)) " <BR>
     *<BR>
     *　@　@１－３）"?"にセットするためのパラメータセットを作成する。  <BR>
     *　@　@　@ArrayListを生成し、以下の値を上から順にセットする。  <BR>
     *　@　@　@　@・パラメータ.顧客.証券会社コード <BR>
     *　@　@　@　@・パラメータ.顧客.部店コード <BR>
     *　@　@　@　@・パラメータ.顧客.口座コード <BR>
     *　@　@　@　@・"10：残高レコード" <BR>
     *　@　@　@　@・"20：明細レコード" <BR>
     *　@　@　@　@・"21：入出金レコード" <BR>
     *<BR>
     *２）18ヶ月表示（パラメータ.処理区分 = 01）の場合 <BR>
     *<BR>
     *　@２－１）検索条件文字列&検索条件データコンテナ  <BR>
     *　@　@以下の検索条件を表す、検索条件文字列と  <BR>
     *　@　@ArrayList(検索条件データコンテナ)を作成する。  <BR>
     *<BR>
     *　@　@[検索条件]  <BR>
     *　@　@　@証券会社コード = パラメータ.顧客.証券会社コード　@かつ  <BR>
     *　@　@　@部店コード = パラメータ.顧客.部店コード　@かつ <BR>
     *　@　@　@顧客コード = パラメータ.顧客.口座コード　@かつ <BR>
     *　@　@　@( <BR>
     *　@　@　@　@レコード区分 = "10：残高レコード"　@または <BR>
     *　@　@　@　@レコード区分 in ("20：明細レコード", "21：入出金レコード") <BR>
     *　@　@　@) <BR>
     *<BR>
     *　@２－２）上記検索条件を基に、検索条件文字列を作成する。  <BR>
     *<BR>
     *　@　@　@検索条件文字列 = "institution_code = ? and "  <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "branch_code = ? and "  <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "account_code = ? and "  <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(rec_div = ? or "  <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(rec_div in (?, ?) )) " <BR>
     *<BR>
     *　@　@２－３）"?"にセットするためのパラメータセットを作成する。  <BR>
     *　@　@　@ArrayListを生成し、以下の値を上から順にセットする。  <BR>
     *　@　@　@　@・パラメータ.顧客.証券会社コード <BR>
     *　@　@　@　@・パラメータ.顧客.部店コード <BR>
     *　@　@　@　@・パラメータ.顧客.口座コード <BR>
     *　@　@　@　@・"10：残高レコード" <BR>
     *　@　@　@　@・"20：明細レコード" <BR>
     *　@　@　@　@・"21：入出金レコード" <BR>
     *<BR>
     *３）QueryProcessor.doGetCountQuery()にて、損益明細件数を取得する。 <BR> 
     *<BR>
     *　@[doGetCountQuery()にセットするパラメータ]  <BR>
     *　@arg0：　@"損益明細テーブル(profit_loss_spec)"  <BR>
     *　@arg1：　@作成した検索条件文字列 <BR>
     *　@arg2：　@作成したArrayList.toArray()  <BR>
     *<BR>
     *４）取得した件数を返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strTransactionDiv - (処理区分)<BR>
     * リクエストデータ.処理区分<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 417DCF6900A9
     */
    public int getProfitLossSpecCount(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strTransactionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getProfitLossSpecCount(WEB3GentradeMainAccount l_mainAccount, String l_strTransactionDiv) ";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbQueryString = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        //１）2ヶ月表示（パラメータ.処理区分 = null）の場合 
        if (WEB3StringTypeUtility.isEmpty(l_strTransactionDiv))
        {
            //１－１）検索条件文字列&検索条件データコンテナ
            //１－２）上記検索条件を基に、検索条件文字列を作成する。
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("account_code = ? and ");
            l_sbQueryString.append("(rec_div = ? or ");
            l_sbQueryString.append("(rec_div in (?, ?) and calc_date <= work_date)) ");

            //１－３）"?"にセットするためのパラメータセットを作成する。
            l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
            l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
            l_lisQueryVars.add(l_mainAccount.getAccountCode());
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.BALANCE_REC);
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
        
        }
        // ２）18ヶ月表示（パラメータ.処理区分 = 01）の場合
        else if (WEB3TradeHistoryTransactionDivDef.EIGHTEEN_MONTH_DISPLAY.equals(l_strTransactionDiv))
        {
            //２－１）検索条件文字列&検索条件データコンテナ
            //２－２）上記検索条件を基に、検索条件文字列を作成する。
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("account_code = ? and ");
            l_sbQueryString.append("(rec_div = ? or ");
            l_sbQueryString.append("(rec_div in (?, ?))) ");

            //２－３）"?"にセットするためのパラメータセットを作成する。
            l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
            l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
            l_lisQueryVars.add(l_mainAccount.getAccountCode());
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.BALANCE_REC);
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
            
        }

        //３）QueryProcessor.doGetCountQuery()にて、損益明細件数を取得する。
        int l_intReturnRecordCnt = 0;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_lisQueryVars.size()];
            l_lisQueryVars.toArray(l_objVars);
            l_intReturnRecordCnt = l_queryProcessor.doGetCountQuery(ProfitLossSpecRow.TYPE, l_sbQueryString.toString(), l_objVars);
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
        //４）取得した件数を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_intReturnRecordCnt;
    }

    /**
     * (get累計損益)<BR>
     * 累計損益を取得し、返却する。<BR>
     * <BR>
     * １）パラメータ.表示期間 == "0：前月月初以降"の場合<BR>
     * 　@１－１）this.get残高レコードfrom損益明細()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@　@[get残高レコードfrom損益明細()にセットするパラメータ]<BR>
     * 　@　@　@　@　@　@顧客：　@パラメータ.顧客<BR>
     * <BR>
     * 　@１－２）１－１）のメソッドの戻り値(損益明細Params).損益の値を返却する。<BR>
     * <BR>
     * ２）パラメータ.表示期間 != "0：前月月初以降"の場合<BR>
     * 　@２－１）this.get最終計算年月日from損益明細()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[get最終計算年月日from損益明細()にセットするパラメータ]<BR>
     * 　@　@　@　@　@顧客：　@パラメータ.顧客<BR>
     * 　@　@　@　@　@表示期間：　@パラメータ.表示期間<BR>
     * <BR>
     * 　@　@　@　@メソッドの戻り値がnullの場合、this.get累計損益()メソッドをコールし、<BR>
     * 　@　@　@　@戻り値を返却して終了する。<BR>
     * <BR>
     * 　@　@　@　@[get累計損益にセットするパラメータ]<BR>
     * 　@　@　@　@　@顧客：　@パラメータ.顧客<BR>
     * 　@　@　@　@　@表示期間：　@"0：前月月初以降"<BR>
     * <BR>
     * 　@２－２）以下の検索条件を表す、検索条件文字列を作成する。 <BR>
     * <BR>
     * 　@　@[検索条件] <BR>
     * 　@　@　@レコード区分 = "20：明細レコード"　@かつ<BR>
     * 　@　@　@計算年月日 = ２－１）の戻り値<BR>
     * <BR>
     * 　@　@上記検索条件を基に、検索条件文字列を作成する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 = "and rec_div = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and calc_date = ?"<BR>
     * <BR>
     * 　@２－３）"?"にセットするためのパラメータセットを作成する。 <BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。 <BR>
     * 　@　@　@・"20：明細レコード"<BR>
     * 　@　@　@・２－１）の戻り値<BR>
     * <BR>
     * 　@２－４）以下のソート条件を作成する。<BR>
     * 　@　@　@・SORT-NO 降順<BR>
     * <BR>
     * 　@　@　@ソート条件 = " sort_no desc"<BR>
     * <BR>
     * 　@２－５）this.get損益明細一覧()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@[get損益明細一覧()にセットするパラメータ]<BR>
     * 　@　@　@　@　@検索条件文字列：　@作成した検索条件文字列<BR>
     * 　@　@　@　@　@検索条件データコンテナ：　@作成したArrayList.toArray()の戻り値<BR>
     * 　@　@　@　@　@ソート条件：　@作成したソート条件<BR>
     * <BR>
     * 　@　@　@　@nullが返却された場合は、0を返却し、終了する。<BR>
     * <BR>
     * 　@２－６）検索結果(損益明細ParamsのList)の0番目の要素.累計損益<BR>
     * 　@　@　@　@　@の値を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strDisplayTerm - (表示期間)<BR>
     * 表示期間<BR>
     * <BR>
     * 0：　@前月月初以降(DEFAULT)<BR>
     * 1：　@1ヶ月分<BR>
     * 2：　@1週間分<BR>
     * 3：　@前日1日分<BR>
     * @@return double
     * @@roseuid 417DCF6900B9
     */
    public double getTotalProlossAmount(WEB3GentradeMainAccount l_mainAccount, String l_strDisplayTerm) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTotalProlossAmount(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        //１）パラメータ.表示期間 == "0：前月月初以降"の場合
        if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.DEFAULT))
        {
            //１－１）this.get残高レコードfrom損益明細()メソッドをコールする。
            ProfitLossSpecParams l_dblprofitLossSpecParams = this.getBalanceRecordFromProfitLoss(l_mainAccount);
            //１－２）１－１）のメソッドの戻り値(損益明細Params).損益の値を返却する。
            if (l_dblprofitLossSpecParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            log.exiting(STR_METHOD_NAME);
            return l_dblprofitLossSpecParams.getProlossAmount();

        }
        //２）パラメータ.表示期間 != "0：前月月初以降"の場合
        else
        {
            //２－１）this.get最終計算年月日from損益明細()をコールする。
            Date l_datfinalCalcDateFromProfitLossSpec = this.getFinalCalcDateFromProfitLossSpec(l_mainAccount, l_strDisplayTerm);
            if (l_datfinalCalcDateFromProfitLossSpec == null)
            {
                log.exiting(STR_METHOD_NAME);
                return this.getTotalProlossAmount(l_mainAccount, WEB3PlsBvsDisplayTermDef.DEFAULT);
            }
            //２－２）以下の検索条件を表す、検索条件文字列を作成する。
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("and rec_div = ? ");
            l_sbQueryString.append("and calc_date = ?");
            List l_lisArrayList = new ArrayList();
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
            l_lisArrayList.add(l_datfinalCalcDateFromProfitLossSpec);
            Object[] l_objArray = new Object[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_objArray);
            String l_strSortCond = " sort_no desc";
            //２－５）this.get損益明細一覧()メソッドをコールする。
            List l_lisprofitLossSpecParams =
                    this.getProfitLossSpecList(l_mainAccount, l_sbQueryString.toString(), l_objArray, l_strSortCond);
            if (l_lisprofitLossSpecParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //２－６）検索結果(損益明細ParamsのList)の0番目の要素.累計損益
            double l_dblTotalProlossAmount =
                    ((ProfitLossSpecParams)(l_lisprofitLossSpecParams.get(0))).getTotalProlossAmount();
            log.exiting(STR_METHOD_NAME);
            return l_dblTotalProlossAmount;
        }
    }

    /**
     * (get最終計算年月日from損益明細)<BR>
     * 指定された表示期間の表示期間前最終計算日を返却する。<BR>
     * (損益明細用)<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列と <BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@　@レコード区分 = "20：明細レコード"<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 = " and rec_div = ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。 <BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。 <BR>
     * 　@　@　@・"20：明細レコード"<BR>
     * <BR>
     * 　@１－３）パラメータ.表示期間の値により、以下の条件を<BR>
     * 　@　@　@　@検索条件に追加する。<BR>
     * <BR>
     * 　@　@　@　@パラメータ.表示期間が、<BR>
     * 　@　@　@　@　@["1：1ヶ月分"の場合]<BR>
     * 　@　@　@　@　@　@検索条件文字列 += "and calc_date <= add_months(work_date, -1)"<BR>
     * <BR>
     * 　@　@　@　@　@["2：1週間分"の場合]<BR>
     * 　@　@　@　@　@　@検索条件文字列 += "and calc_date <= work_date - 7"<BR>
     * <BR>
     * 　@　@　@　@　@["3：前日1日分"の場合]<BR>
     * 　@　@　@　@　@　@検索条件文字列 += "and calc_date <= work_date - 1"<BR>
     * <BR>
     * 　@１－４）以下のソート条件を作成する。<BR>
     * 　@　@　@　@・計算年月日 降順<BR>
     * <BR>
     * 　@　@　@　@ソート条件 = " calc_date desc"<BR>
     * <BR>
     * 　@１－５）this.get損益明細一覧()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@[get損益明細一覧()にセットするパラメータ]<BR>
     * 　@　@　@　@　@顧客：　@パラメータ.顧客<BR>
     * 　@　@　@　@　@検索条件文字列：　@作成した検索条件文字列<BR>
     * 　@　@　@　@　@検索条件データコンテナ：　@作成したArrayList.toArray()の戻り値<BR>
     * 　@　@　@　@　@ソート条件：　@作成したソート条件<BR>
     * <BR>
     * 　@　@　@　@検索結果が取得できなかった場合は、nullを返却し、終了する。<BR>
     * <BR>
     * 　@１－６）検索結果(損益明細ParamsのList)の0番目の要素.計算年月日<BR>
     * 　@　@　@　@　@の値を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strDisplayTerm - (表示期間)<BR>
     * 表示期間<BR>
     * <BR>
     * 0：　@前月月初以降(DEFAULT)<BR>
     * 1：　@1ヶ月分<BR>
     * 2：　@1週間分<BR>
     * 3：　@前日1日分<BR>
     * @@return Date
     * @@roseuid 417DCF6900C8
     */
    public Date getFinalCalcDateFromProfitLossSpec(WEB3GentradeMainAccount l_mainAccount, String l_strDisplayTerm) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getFinalCalcDateFromProfitLossSpec(WEB3GentradeMainAccount, String) ";
        log.entering(STR_METHOD_NAME);

        //１）以下の検索条件を表す、検索条件文字列とArrayList(検索条件データコンテナ)を作成する。
        //１－１）上記検索条件を基に、検索条件文字列を作成する。
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" and rec_div = ? ");

        //１－２）"?"にセットするためのパラメータセットを作成する。
        List l_lisArrayList = new ArrayList();
        l_lisArrayList.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
       
        //１－３）パラメータ.表示期間の値により、以下の条件を検索条件に追加する。
        if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_MONTH))
        {
            l_sbQueryString.append("and calc_date <= add_months(work_date, ?)");
            l_lisArrayList.add(new Integer(-1));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_WEEK))
        {
            l_sbQueryString.append("and calc_date <= work_date - ?");
            l_lisArrayList.add(new Integer(7));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY))
        {
            l_sbQueryString.append("and calc_date <= work_date - ?");
            l_lisArrayList.add(new Integer(1));
        }
        //１－４）以下のソート条件を作成する。
        String l_strSortCond = " calc_date desc";
        
        Object[] l_objArray = new Object[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_objArray);
        //１－５）this.get損益明細一覧()メソッドをコールする。
        List l_lisProfitLossSpec =
                this.getProfitLossSpecList(l_mainAccount, l_sbQueryString.toString(), l_objArray, l_strSortCond);
        //検索結果が取得できなかった場合は、nullを返却し、終了する。
        if (l_lisProfitLossSpec == null || l_lisProfitLossSpec.size() == 0)
        {
            return null;
        }
        //１－６）検索結果(損益明細ParamsのList)の0番目の要素.計算年月日の値を返却する。
        Date l_datFinalCalcDateFromProfitLossSpec =
                (Timestamp)(((ProfitLossSpecParams)(l_lisProfitLossSpec.get(0))).getCalcDate());
        log.exiting(STR_METHOD_NAME);
        return l_datFinalCalcDateFromProfitLossSpec;
    }

    /**
     * (get損益明細一覧)<BR>
     * 引数の検索条件に該当する損益明細の一覧を返却する。<BR>
     * <BR>
     * １）以下の顧客情報を検索条件に追加する。<BR>
     * 　@・証券会社コード<BR>
     * 　@・部店コード<BR>
     * 　@・口座コード<BR>
     * <BR>
     * 　@○追加検索条件文字列 =  "institution_code = ? and " <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "branch_code = ? and " <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "account_code = ? "<BR>
     * <BR>
     * 　@　@　@作成した検索条件文字列 = 追加検索条件文字列<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ パラメータ.検索条件文字列とする。<BR>
     * <BR>
     * 　@○追加検索条件データコンテナ<BR>
     * 　@(ArrayListを生成し、以下の値を上から順に追加する)<BR>
     * 　@　@・パラメータ.顧客.証券会社コード<BR>
     * 　@　@・パラメータ.顧客.部店コード<BR>
     * 　@　@・パラメータ.顧客.口座コード<BR>
     * 　@　@・パラメータ.検索条件データコンテナの要素全て(0番目から順次追加)<BR>
     * <BR>
     * 　@　@作成した検索条件データコンテナ = 追加検索条件データコンテナとする。<BR>
     * <BR>
     * ２）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuary()にセットするパラメータ]<BR>
     * 　@　@arg0：　@"損益明細テーブル(profit_loss_spec)"<BR>
     * 　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@arg2：　@パラメータ.ソート条件<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@作成した検索条件データコンテナ<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ３）２）の結果を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_queryDataContainer - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return List
     * @@roseuid 417DCF6900D8
     */
    public List getProfitLossSpecList(WEB3GentradeMainAccount l_mainAccount, String l_strQueryString, Object[] l_queryDataContainer, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProfitLossSpecList(WEB3GentradeMainAccount, String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        //１）以下の顧客情報を検索条件に追加する。
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("institution_code = ? and ");
        l_sbQueryString.append("branch_code = ? and ");
        l_sbQueryString.append("account_code = ? ");
        l_sbQueryString.append(l_strQueryString);
        
        List l_lisArrayList = new ArrayList();
        l_lisArrayList.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisArrayList.add(l_mainAccount.getBranch().getBranchCode());
        l_lisArrayList.add(l_mainAccount.getAccountCode());
        for (int i = 0; i < l_queryDataContainer.length; i++)
        {
            l_lisArrayList.add(l_queryDataContainer[i]);
        }
        Object[] l_objArray = new Object[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_objArray);
        //２）QueryProcessor.doFindAllQuery()メソッドをコールする。
        List l_lisReslut = null;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReslut = l_queryProcessor.doFindAllQuery(
                    ProfitLossSpecRow.TYPE, l_sbQueryString.toString(), l_strSortCond, null, l_objArray);
                if (l_lisReslut == null || l_lisReslut.size() == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                 }
        }
        catch (DataFindException l_ex)
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

        //３）２）の結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisReslut;
    }

    /**
     * (get損益明細一覧)<BR>
     * (get損益明細一覧のオーバーロード)<BR>
     * 引数の顧客、表示期間、レコード区分、<BR>
     * 作業年月日に該当する損益明細一覧を取得する。<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列と <BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@　@レコード区分 = (パラメータ.取得対象レコード区分による)<BR>
     * <BR>
     * 　@１－１）ArrayListを生成する。<BR>
     * <BR>
     * 　@１－２）上記検索条件を基に検索条件文字列、パラメータセット(ArrayList)<BR>
     * 　@　@　@　@　@を作成する。<BR>
     * <BR>
     * 　@　@　@パラメータ.取得対象レコード区分が、<BR>
     * 　@　@　@["0：残高レコード"の場合]<BR>
     * 　@　@　@　@○検索条件文字列 = " and rec_div = ? "<BR>
     * 　@　@　@　@○生成したArrayListに"10：残高レコード"をセット。<BR>
     * <BR>
     * 　@　@　@["1：明細レコード"の場合]<BR>
     * 　@　@　@　@○検索条件文字列 = " and rec_div = ? "<BR>
     * 　@　@　@　@○生成したArrayListに"20：明細レコード"をセット。<BR>
     * <BR>
     * 　@　@　@["2：入出金レコード"の場合]<BR>
     * 　@　@　@　@○検索条件文字列 = " and rec_div = ? "<BR>
     * 　@　@　@　@○生成したArrayListに"21：入出金レコード"をセット。<BR>
     * <BR>
     * 　@　@　@["3：明細&入出金レコード"の場合]<BR>
     * 　@　@　@　@○検索条件文字列 = " and rec_div in (?, ?) "<BR>
     * 　@　@　@　@○生成したArrayListに以下の値をセット。<BR>
     * 　@　@　@　@　@　@・"20：明細レコード"<BR>
     * 　@　@　@　@　@　@・"21：入出金レコード"<BR>
     * <BR>
     * 　@１－３）パラメータ.作業年月日 != nullの場合、以下の条件を<BR>
     * 　@　@　@　@検索条件に追加する。<BR>
     * <BR>
     * 　@　@　@　@検索条件文字列 += " and to_char(delivery_date, YYYY) = ?"<BR>
     * 　@　@　@　@生成したArrayListにthis.get当期年度()メソッドの戻り値を追加。<BR>
     * <BR>
     * 　@　@　@　@　@[get当期年度()にセットするパラメータ]<BR>
     * 　@　@　@　@　@　@基準日：　@パラメータ.作業年月日<BR>
     * <BR>
     * 　@１－４）パラメータ.表示期間の値により、以下の条件を<BR>
     * 　@　@　@　@検索条件に追加する。<BR>
     * <BR>
     * 　@　@　@　@パラメータ.表示期間が、<BR>
     * 　@　@　@　@　@["0：前月月初以降"の場合]<BR>
     * 　@　@　@　@　@　@検索条件文字列 += "and calc_date <= work_date "<BR>
     * <BR>
     * 　@　@　@　@　@["1：1ヶ月分"の場合]<BR>
     * 　@　@　@　@　@　@検索条件文字列 += "and calc_date > add_months(work_date, -1) "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and calc_date <= work_date "<BR>
     * <BR>
     * 　@　@　@　@　@["2：1週間分"の場合]<BR>
     * 　@　@　@　@　@　@検索条件文字列 += "and calc_date > work_date - 7"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and calc_date <= work_date "<BR>
     * <BR>
     * 　@　@　@　@　@["3：前日1日分"の場合]<BR>
     * 　@　@　@　@　@　@検索条件文字列 += "and calc_date = work_date "<BR>
     * <BR>
     * ２）以下のソート条件を作成する。<BR>
     * 　@－計算年月日　@昇順<BR>
     * 　@－SORT-NO　@昇順<BR>
     * <BR>
     * 　@ソート条件 = "calc_date asc, sort_no asc "<BR>
     * <BR>
     * ３）this.get損益明細一覧()メソッドをコールする。<BR>
     * <BR>
     * 　@[get損益明細一覧()にセットするパラメータ]<BR>
     * 　@　@顧客：　@パラメータ.顧客<BR>
     * 　@　@検索条件文字列：　@作成した検索条件文字列<BR>
     * 　@　@検索条件データコンテナ：　@作成したArrayList.toArray()の戻り値<BR>
     * 　@　@ソート条件：　@作成したソート条件<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却し、終了する。<BR>
     * <BR>
     * ４）３）の戻り値を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strGetObjectRecDiv - (取得対象レコード区分)<BR>
     * 取得対象レコード区分<BR>
     * <BR>
     * 0：　@残高レコード<BR>
     * 1：　@明細レコード<BR>
     * 2：　@入出金レコード<BR>
     * 3：　@明細&入出金レコード<BR>
     * @@param l_strDisplayTerm - (表示期間)<BR>
     * 表示期間<BR>
     * <BR>
     * 0：　@前月月初以降(DEFAULT)<BR>
     * 1：　@1ヶ月分<BR>
     * 2：　@1週間分<BR>
     * 3：　@前日1日分<BR>
     * @@param l_datWorkDate - (作業年月日)<BR>
     * 残高レコードの作業年月日<BR>
     * @@return List
     * @@roseuid 417DCF6900E7
     */
    public List getProfitLossSpecList(WEB3GentradeMainAccount l_mainAccount, String l_strGetObjectRecDiv, String l_strDisplayTerm, Date l_datWorkDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProfitLossSpecList(WEB3GentradeMainAccount, String, String, Date)";
        log.entering(STR_METHOD_NAME);

        //１）以下の検索条件を表す、検索条件文字列とArrayList(検索条件データコンテナ)を作成する。
        //１－１）ArrayListを生成する。
        List l_lisArrayList = new ArrayList();
        StringBuffer l_sbQueryString = new StringBuffer();
        //１－２）上記検索条件を基に検索条件文字列、パラメータセット(ArrayList)
        if (l_strGetObjectRecDiv.equals(WEB3PlsBvsDetailOrderRecDef.BALANCE_REC))
        {
            l_sbQueryString.append(" and rec_div = ? ");
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.BALANCE_REC);
        }
        else if (l_strGetObjectRecDiv.equals(WEB3PlsBvsDetailOrderRecDef.DETAIL_REC))
        {
            l_sbQueryString.append(" and rec_div = ? ");
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
        }
        else if (l_strGetObjectRecDiv.equals(WEB3PlsBvsDetailOrderRecDef.PAYMENT_REC))
        {
            l_sbQueryString.append(" and rec_div = ? ");
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
        }
        else if (l_strGetObjectRecDiv.equals(WEB3PlsBvsDetailOrderRecDef.DETAIL_ORDER_REC))
        {
            l_sbQueryString.append(" and rec_div in (?, ?) ");
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
        }

        //１－３）パラメータ.作業年月日 != nullの場合、以下の条件を検索条件に追加する。
        if (l_datWorkDate != null)
        {
            l_sbQueryString.append(" and to_char(delivery_date, 'YYYY') = ?");
            l_lisArrayList.add(this.getCurrentYear(l_datWorkDate));
        }

        //１－４）パラメータ.表示期間の値により、以下の条件を検索条件に追加する。
        if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.DEFAULT))
        {
            l_sbQueryString.append("and calc_date <= work_date ");
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_MONTH))
        {
            l_sbQueryString.append("and calc_date > add_months(work_date, ?) ");
            l_sbQueryString.append("and calc_date <= work_date ");
            l_lisArrayList.add(new Integer(-1));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_WEEK))
        {
            l_sbQueryString.append("and calc_date > work_date - ?");
            l_sbQueryString.append("and calc_date <= work_date ");
            l_lisArrayList.add(new Integer(7));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY))
        {
            l_sbQueryString.append("and calc_date = work_date ");
        }
        //ソート条件 = "calc_date asc, sort_no asc "
        String l_strSortCond = "calc_date asc, sort_no asc ";

        //３）this.get損益明細一覧()メソッドをコールする。
        Object[] l_objArray = new Object[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_objArray);
        
        List l_lisgetProfitLossSpecList =
                this.getProfitLossSpecList(l_mainAccount, l_sbQueryString.toString(), l_objArray, l_strSortCond);

        if (l_lisgetProfitLossSpecList == null || l_lisgetProfitLossSpecList.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //４）３）の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisgetProfitLossSpecList;
    }

    /**
     * (get残高レコードfrom損益明細)<BR>
     * 顧客に該当する損益明細の残高レコードを取得する。<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列と <BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@　@レコード区分 = "10：残高レコード"<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 = " and rec_div = ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。 <BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。 <BR>
     * 　@　@　@・"10：残高レコード"<BR>
     * <BR>
     * 　@１－３）this.get損益明細一覧()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@[get損益明細一覧()にセットするパラメータ]<BR>
     * 　@　@　@　@　@顧客：　@パラメータ.顧客<BR>
     * 　@　@　@　@　@検索条件文字列：　@作成した検索条件文字列<BR>
     * 　@　@　@　@　@検索条件データコンテナ：　@作成したArrayList.toArray()の戻り値<BR>
     * 　@　@　@　@　@ソート条件：　@null<BR>
     * <BR>
     * 　@　@　@　@検索結果が取得できなかった場合は、nullを返却し、終了する。<BR>
     * <BR>
     * 　@１－４）１－３）の戻り値を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return 損益明細Params
     * @@roseuid 417DCF6900F7
     */
    public ProfitLossSpecParams getBalanceRecordFromProfitLoss(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = " getBalanceRecordFromProfitLoss(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);

        //１）以下の検索条件を表す、検索条件文字列と
        //ArrayList(検索条件データコンテナ)を作成する。
        //１－１）上記検索条件を基に、検索条件文字列を作成する。
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" and rec_div = ? ");

        //１－２）"?"にセットするためのパラメータセットを作成する。
        List l_lisArrayList = new ArrayList();
        l_lisArrayList.add(WEB3ProfitLossRecordDivDef.BALANCE_REC);
        Object[] l_objArray = new Object[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_objArray);
        
        //１－３）this.get損益明細一覧()メソッドをコールする。
        List l_lisProfitLossSpecList =
                (this.getProfitLossSpecList(l_mainAccount, l_sbQueryString.toString(), l_objArray, null));

        if (l_lisProfitLossSpecList == null || l_lisProfitLossSpecList.size() == 0)
        {
            return null;
        }
                
        ProfitLossSpecParams l_profitLossSpecParams = (ProfitLossSpecParams)(l_lisProfitLossSpecList.get(0));
        
        log.exiting(STR_METHOD_NAME);
        return l_profitLossSpecParams;
    }

    /**
     * (get当期年度)<BR>
     * 当期の年度(yyyy)を返却する。<BR>
     * <BR>
     * 引数の基準日の翌営業日が12月の場合、<BR>
     * 引数の基準日の年を返却する。<BR>
     * 以外は、基準日の翌営業日の年を返却する。<BR>
     * <BR>
     * １）this.is１２月()メソッドをコールする。<BR>
     * <BR>
     * 　@[is１２月()にセットするパラメータ]<BR>
     * 　@　@基準日：　@パラメータ.基準日<BR>
     * <BR>
     * ３）１）の戻り値がtrueであれば、パラメータ.作業年月日を<BR>
     * 　@　@文字列変換(フォーマット：yyyy)して返却する。<BR>
     * 　@以外、this.get翌営業日(パラメータ.基準日)の戻り値を<BR>
     * 　@文字列変換(フォーマット：yyyy)して返却する。<BR>
     * @@param l_datBaseDate - (基準日)<BR>
     * 基準日<BR>
     * @@return String
     * @@roseuid 417DCF6900F9
     */
    public String getCurrentYear(Date l_datBaseDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCurrentYear(Date l_datBaseDate)";
        log.entering(STR_METHOD_NAME);

        //１）this.is１２月()メソッドをコールする。
        boolean l_bln = this.isDecember(l_datBaseDate);

        //３）１）の戻り値がtrueであれば、パラメータ.作業年月日を
        // 文字列変換(フォーマット：yyyy)して返却する。
        if (l_bln)
        {
            String l_strBaseDate = WEB3DateUtility.formatDate(l_datBaseDate, "yyyy");
            return l_strBaseDate;
        }
        
        //this.get翌営業日(パラメータ.基準日)の戻り値を
        //文字列変換(フォーマット：yyyy)して返却する。
        Date l_datNextBizDate = this.getNextBizDate(l_datBaseDate);
        String l_strNextBizDate = WEB3DateUtility.formatDate(l_datNextBizDate, "yyyy");

        log.exiting(STR_METHOD_NAME);
        return l_strNextBizDate;
    }

    /**
     * (get翌営業日)<BR>
     * 引数の基準日の翌営業日を取得する。<BR>
     * <BR>
     * １）営業日計算インスタンスを生成する。<BR>
     * <BR>
     * 　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@基準日：　@パラメータ.基準日　@※Timestamp型に変換する。<BR>
     * <BR>
     * ２）営業日計算.roll()メソッドをコールする。<BR>
     * <BR>
     * 　@[roll()にセットするパラメータ]<BR>
     * 　@　@加算／減算日数：　@1(翌営業日)<BR>
     * <BR>
     * ３）２）の戻り値を返却する。<BR>
     * @@param l_datBaseDate - (基準日)<BR>
     * 基準日<BR>
     * @@return Date
     * @@roseuid 417DCF690107
     */
    public Date getNextBizDate(Date l_datBaseDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNextBizDate(Date l_datBaseDate) ";
        log.entering(STR_METHOD_NAME);

        //１）営業日計算インスタンスを生成する。
        Timestamp l_tsBaseDate = new Timestamp(l_datBaseDate.getTime());
        log.info("l_tsBaseDate = " + l_tsBaseDate );

        //２）営業日計算.roll()メソッドをコールする。
        WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(l_tsBaseDate);
        Date l_datNextBizDate = l_bizDate.roll(1);
        log.info("l_datNextBizDate = " + l_datNextBizDate );

        //３）２）の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_datNextBizDate;
    }
    /**
     * (is１２月)<BR>
     * 引数の基準日の翌営業日の月が12月であるか判別する。<BR>
     * <BR>
     * 12月である場合は、trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * <BR>
     * １）this.get翌営業日()メソッドをコールする。<BR>
     * <BR>
     * 　@[get翌営業日()にセットするパラメータ]<BR>
     * 　@　@基準日：　@パラメータ.基準日<BR>
     * <BR>
     * ３）１）の戻り値の月が12月であれば、trueを返却する。<BR>
     * 　@以外、falseを返却する。<BR>
     * @@param l_datBaseDate - (基準日)<BR>
     * 基準日<BR>
     * @@return boolean
     * @@roseuid 417DCF690116
     */
    public boolean isDecember(Date l_datBaseDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isDecember(Date l_datBaseDate) ";
        log.entering(STR_METHOD_NAME);

        //１）this.get翌営業日()メソッドをコールする。
        Date l_datNextBizDate = this.getNextBizDate(l_datBaseDate);

        //３）１）の戻り値の月が12月であれば、trueを返却する。
        String l_strMonth = WEB3DateUtility.formatDate(l_datNextBizDate, "MM");

        if (l_strMonth.equals("12"))
        {
            return true;
        }
        //falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get簿価単価明細件数)<BR>
     * 顧客に該当する簿価単価明細データの件数を返却する。<BR>
     * <BR>
     * １）検索条件文字列&検索条件データコンテナ <BR>
     * 　@以下の検索条件を表す、検索条件文字列と <BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@　@証券会社コード = パラメータ.顧客.証券会社コード　@かつ <BR>
     * 　@　@部店コード = パラメータ.顧客.部店コード　@かつ<BR>
     * 　@　@顧客コード = パラメータ.顧客.口座コード　@かつ<BR>
     * 　@　@(<BR>
     * 　@　@レコード区分 = "1：残高レコード"　@または<BR>
     * 　@　@　@(レコード区分 = "2：明細レコード"　@かつ<BR>
     * 　@　@　@　@計算年月日 <= 作業年月日)<BR>
     * 　@　@)　@かつ<BR>
     * 　@　@商品コード = パラメータ.商品コード　@かつ<BR>
     * 　@　@銘柄コード = パラメータ.銘柄コード　@かつ<BR>
     * 　@　@備考 != "1：取消済"<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 = "institution_code = ? and " <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "branch_code = ? and " <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "account_code = ? and " <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(rec_div = ? or " <BR>
     *                             + "(rec_div = ? and calc_date <= work_date)) and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "commodity_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "product_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "remark != ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。 <BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。 <BR>
     * 　@　@　@・パラメータ.顧客.証券会社コード<BR>
     * 　@　@　@・パラメータ.顧客.部店コード<BR>
     * 　@　@　@・パラメータ.顧客.口座コード<BR>
     * 　@　@　@・"1：残高レコード"<BR>
     * 　@　@　@・"2：明細レコード"<BR>
     * 　@　@　@・パラメータ.商品コード<BR>
     * 　@　@　@・パラメータ.銘柄コード<BR>
     * 　@　@　@・"1：取消済"<BR>
     * <BR>
     * ２）QueryProcessor.doGetCountQuery()にて、簿価単価明細件数を取得する。 <BR>
     * <BR>
     * 　@[doGetCountQuery()にセットするパラメータ] <BR>
     * 　@arg0：　@"簿価単価明細テーブル(book_value_spec)" <BR>
     * 　@arg1：　@作成した検索条件文字列<BR>
     * 　@arg2：　@作成したArrayList.toArray() <BR>
     * <BR>
     * ３）取得した件数を返却する。 <BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strCommodityCode - (商品コード)<BR>
     * 商品コード<BR>
     * <BR>
     * 10:　@株式<BR>
     * 11:　@信用<BR>
     * 20:　@投信<BR>
     * 21:　@外投<BR>
     * 22:　@累投<BR>
     * 23:　@MRF<BR>
     * 30:　@債券<BR>
     * 40:　@外株<BR>
     * 50:　@株先<BR>
     * 51:　@株指数OP<BR>
     * 52:　@債先<BR>
     * 53:　@債先OP<BR>
     * 54:　@店OP<BR>
     * 55:　@外先<BR>
     * 56:　@外先OP<BR>
     * 57:　@株OP<BR>
     * 60:　@外債<BR>
     * 70:　@金<BR>
     * 71:　@金GP<BR>
     * 80:　@特殊<BR>
     * 91:　@CD<BR>
     * 92:　@CP<BR>
     * 93:　@BA<BR>
     * 99:　@金銭<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@return int
     * @@roseuid 417DCF690126
     */
    public int getBookValueSpecCount(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBookValueSpecCount(WEB3GentradeMainAccount, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）検索条件文字列&検索条件データコンテナ
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("institution_code = ? and ");
        l_sbQueryString.append("branch_code = ? and ");
        l_sbQueryString.append("account_code = ? and ");
        l_sbQueryString.append("(rec_div = ? or ");
        l_sbQueryString.append("(rec_div = ? and calc_date <= work_date)) and ");
        l_sbQueryString.append("commodity_code = ? and ");
        l_sbQueryString.append("product_code = ? and ");
        l_sbQueryString.append("remark != ? ");

        //"?"にセットするためのパラメータセットを作成する。
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
        l_lisQueryVars.add(l_mainAccount.getAccountCode());
        l_lisQueryVars.add(WEB3BookValueRecordDivDef.BALANCE_REC);
        l_lisQueryVars.add(WEB3BookValueRecordDivDef.DETAIL_REC);
        l_lisQueryVars.add(l_strCommodityCode);
        l_lisQueryVars.add(l_strProductCode);
        l_lisQueryVars.add(WEB3BookValueRemarkDef.CANCEL);

        //２）QueryProcessor.doGetCountQuery()にて、簿価単価明細件数を取得する。
        int l_intReturnRecordCnt = 0;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_lisQueryVars.size()];
            l_lisQueryVars.toArray(l_objVars);
            l_intReturnRecordCnt = l_queryProcessor.doGetCountQuery(BookValueSpecRow.TYPE, l_sbQueryString.toString(), l_objVars);
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

        //３）取得した件数を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_intReturnRecordCnt;
    }

    /**
     * (get最終計算年月日from簿価単価明細)<BR>
     * 指定された表示期間の表示期間前最終計算日を返却する。<BR>
     * (簿価単価明細用)<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列と <BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@　@レコード区分 = "2：明細レコード"<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 = " and rec_div = ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。 <BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。 <BR>
     * 　@　@　@・"2：明細レコード"<BR>
     * <BR>
     * 　@１－３）パラメータ.表示期間の値により、以下の条件を<BR>
     * 　@　@　@　@検索条件に追加する。<BR>
     * <BR>
     * 　@　@　@　@パラメータ.表示期間が、<BR>
     * 　@　@　@　@　@["0：前月月初以降"]<BR>
     * 　@　@　@　@　@　@検索条件文字列 += "and calc_date <= last_day(add_months(work_date, -2))"<BR>
     * <BR>
     * 　@　@　@　@　@["1：1ヶ月分"の場合]<BR>
     * 　@　@　@　@　@　@検索条件文字列 += "and calc_date <= add_months(work_date, -1)"<BR>
     * <BR>
     * 　@　@　@　@　@["2：1週間分"の場合]<BR>
     * 　@　@　@　@　@　@検索条件文字列 += "and calc_date <= work_date - 7"<BR>
     * <BR>
     * 　@　@　@　@　@["3：前日1日分"の場合]<BR>
     * 　@　@　@　@　@　@検索条件文字列 += "and calc_date <= work_date - 1"<BR>
     * <BR>
     * 　@１－４）以下のソート条件を作成する。<BR>
     * 　@　@　@　@・計算年月日 降順<BR>
     * <BR>
     * 　@　@　@　@ソート条件 = " calc_date desc"<BR>
     * <BR>
     * 　@１－５）this.get簿価単価明細一覧()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@[get簿価単価明細一覧()にセットするパラメータ]<BR>
     * 　@　@　@　@　@顧客：　@パラメータ.顧客<BR>
     * 　@　@　@　@　@商品コード：　@パラメータ.商品コード<BR>
     * 　@　@　@　@　@銘柄コード：　@パラメータ.銘柄コード<BR>
     * 　@　@　@　@　@検索条件文字列：　@作成した検索条件文字列<BR>
     * 　@　@　@　@　@検索条件データコンテナ：　@作成したArrayList.toArray()の戻り値<BR>
     * 　@　@　@　@　@ソート条件：　@作成したソート条件<BR>
     * <BR>
     * 　@　@　@　@検索結果が取得できなかった場合は、nullを返却し、終了する。<BR>
     * <BR>
     * 　@１－６）検索結果(損益明細ParamsのList)の0番目の要素.計算年月日<BR>
     * 　@　@　@　@　@の値を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strCommodityCode - (商品コード)<BR>
     * 商品コード<BR>
     * <BR>
     * 10:　@株式<BR>
     * 11:　@信用<BR>
     * 20:　@投信<BR>
     * 21:　@外投<BR>
     * 22:　@累投<BR>
     * 23:　@MRF<BR>
     * 30:　@債券<BR>
     * 40:　@外株<BR>
     * 50:　@株先<BR>
     * 51:　@株指数OP<BR>
     * 52:　@債先<BR>
     * 53:　@債先OP<BR>
     * 54:　@店OP<BR>
     * 55:　@外先<BR>
     * 56:　@外先OP<BR>
     * 57:　@株OP<BR>
     * 60:　@外債<BR>
     * 70:　@金<BR>
     * 71:　@金GP<BR>
     * 80:　@特殊<BR>
     * 91:　@CD<BR>
     * 92:　@CP<BR>
     * 93:　@BA<BR>
     * 99:　@金銭<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strDisplayTerm - (表示期間)<BR>
     * 表示期間<BR>
     * <BR>
     * 0：　@前月月初以降(DEFAULT)<BR>
     * 1：　@1ヶ月分<BR>
     * 2：　@1週間分<BR>
     * 3：　@前日1日分<BR>
     * @@return Date
     * @@roseuid 417DCF690136
     */
    public Date getFinalCalcDateFromBookValueSpec(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode, String l_strDisplayTerm) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBookValueSpecCount(WEB3GentradeMainAccount, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）以下の検索条件を表す、検索条件文字列とArrayList(検索条件データコンテナ)を作成する。
        //１－１）検索条件文字列を作成する。
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" and rec_div = ? ");
        
        //１－２）"?"にセットするためのパラメータセットを作成する。
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(WEB3BookValueRecordDivDef.DETAIL_REC);

        
        //１－３）パラメータ.表示期間の値により、以下の条件を検索条件に追加する。
        if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.DEFAULT))
        {
            l_sbQueryString.append("and calc_date <= last_day(add_months(work_date, ?))");
            l_lisQueryVars.add(new Integer(-2));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_MONTH))
        {
            l_sbQueryString.append("and calc_date <= add_months(work_date, ?)");
            l_lisQueryVars.add(new Integer(-1));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_WEEK))
        {
            l_sbQueryString.append("and calc_date <= work_date - ?");
            l_lisQueryVars.add(new Integer(7));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY))
        {
            l_sbQueryString.append("and calc_date <= work_date - ?");
            l_lisQueryVars.add(new Integer(1));
        }

        //１－４）以下のソート条件を作成する。
        String l_strSortCond = " calc_date desc";
        Object[] l_objVars = new Object[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_objVars);
        //１－５）this.get簿価単価明細一覧()メソッドをコールする。
        List l_lisgetBookValueSpecList =
                this.getBookValueSpecList(l_mainAccount, l_strCommodityCode, l_strProductCode, l_sbQueryString.toString(), l_objVars, l_strSortCond);

        if (l_lisgetBookValueSpecList == null || l_lisgetBookValueSpecList.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //１－６）検索結果(損益明細ParamsのList)の0番目の要素.計算年月日の値を返却する。
        Date l_datResult = (Timestamp)(((BookValueSpecParams)(l_lisgetBookValueSpecList.get(0))).getCalcDate());
        log.exiting(STR_METHOD_NAME);
        return l_datResult;
    }

    /**
     * (get簿価単価明細一覧)<BR>
     * 引数の検索条件に該当する簿価単価明細の一覧を返却する。<BR>
     * <BR>
     * １）以下の顧客情報を検索条件に追加する。<BR>
     * 　@・証券会社コード<BR>
     * 　@・部店コード<BR>
     * 　@・口座コード<BR>
     * 　@・商品コード<BR>
     * 　@・銘柄コード<BR>
     * 　@・備考<BR>
     * <BR>
     * 　@○追加検索条件文字列 =  "institution_code = ? and " <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "branch_code = ? and " <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "account_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "commodity_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "product_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "remark != ? "<BR>
     * <BR>
     * 　@　@　@作成した検索条件文字列 = 追加検索条件文字列<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ パラメータ.検索条件文字列とする。<BR>
     * <BR>
     * 　@○追加検索条件データコンテナ<BR>
     * 　@(ArrayListを生成し、以下の値を上から順に追加する)<BR>
     * 　@　@・パラメータ.顧客.証券会社コード<BR>
     * 　@　@・パラメータ.顧客.部店コード<BR>
     * 　@　@・パラメータ.顧客.口座コード<BR>
     * 　@　@・パラメータ.商品コード<BR>
     * 　@　@・パラメータ.銘柄コード<BR>
     * 　@　@・"1：取消済み"<BR>
     * 　@　@・パラメータ.検索条件データコンテナの要素全て(0番目から順次追加)<BR>
     * <BR>
     * 　@　@作成した検索条件データコンテナ = 追加検索条件データコンテナとする。<BR>
     * <BR>
     * ２）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuary()にセットするパラメータ]<BR>
     * 　@　@arg0：　@"簿価単価明細テーブル(book_value_spec)"<BR>
     * 　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@arg2：　@パラメータ.ソート条件<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@作成した検索条件データコンテナ<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ３）２）の結果を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strCommodityCode - (商品コード)<BR>
     * 商品コード<BR>
     * <BR>
     * 10:　@株式<BR>
     * 11:　@信用<BR>
     * 20:　@投信<BR>
     * 21:　@外投<BR>
     * 22:　@累投<BR>
     * 23:　@MRF<BR>
     * 30:　@債券<BR>
     * 40:　@外株<BR>
     * 50:　@株先<BR>
     * 51:　@株指数OP<BR>
     * 52:　@債先<BR>
     * 53:　@債先OP<BR>
     * 54:　@店OP<BR>
     * 55:　@外先<BR>
     * 56:　@外先OP<BR>
     * 57:　@株OP<BR>
     * 60:　@外債<BR>
     * 70:　@金<BR>
     * 71:　@金GP<BR>
     * 80:　@特殊<BR>
     * 91:　@CD<BR>
     * 92:　@CP<BR>
     * 93:　@BA<BR>
     * 99:　@金銭<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_queryDataContainer - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return List
     * @@roseuid 417DCF690164
     */
    public List getBookValueSpecList(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode, String l_strQueryString, Object[] l_queryDataContainer, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBookValueSpecList(WEB3GentradeMainAccount, String, String, String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        //１）以下の顧客情報を検索条件に追加する。
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("institution_code = ? and ");
        l_sbQueryString.append("branch_code = ? and ");
        l_sbQueryString.append("account_code = ? and ");
        l_sbQueryString.append("commodity_code = ? and ");
        l_sbQueryString.append("product_code = ? and ");
        l_sbQueryString.append("remark != ? ");
        l_sbQueryString.append(l_strQueryString);

        //追加検索条件データコンテナ
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
        l_lisQueryVars.add(l_mainAccount.getAccountCode());
        l_lisQueryVars.add(l_strCommodityCode);
        l_lisQueryVars.add(l_strProductCode);
        l_lisQueryVars.add(WEB3BookValueRemarkDef.CANCEL);
        for (int i = 0; i < l_queryDataContainer.length; i++)
        {
            l_lisQueryVars.add(l_queryDataContainer[i]);
        }
        Object[] l_objArray = new Object[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_objArray);
        //２）QueryProcessor.doFindAllQuery()メソッドをコールする
        List l_lisRecords = null;
        try
        {

            //１）QueryProcessor.doFindAllQuery()をコールする。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                BookValueSpecRow.TYPE,
                l_sbQueryString.toString(),
                l_strSortCond,
                null,
                l_objArray
                );
            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            this.getClass().getName() + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            this.getClass().getName() + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        //３）２）の結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (get残高レコードfrom簿価単価明細)<BR>
     * 顧客に該当する簿価単価明細の残高レコードを取得する。<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列と <BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@　@レコード区分 = "1：残高レコード"<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 = " and rec_div = ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。 <BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。 <BR>
     * 　@　@　@・"1：残高レコード"<BR>
     * <BR>
     * 　@１－３）this.get簿価単価明細一覧()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@[get簿価単価明細一覧()にセットするパラメータ]<BR>
     * 　@　@　@　@　@顧客：　@パラメータ.顧客<BR>
     * 　@　@　@　@　@商品コード：　@パラメータ.商品コード<BR>
     * 　@　@　@　@　@銘柄コード：　@パラメータ.銘柄コード<BR>
     * 　@　@　@　@　@検索条件文字列：　@作成した検索条件文字列<BR>
     * 　@　@　@　@　@検索条件データコンテナ：　@作成したArrayList.toArray()の戻り値<BR>
     * 　@　@　@　@　@ソート条件：　@null<BR>
     * <BR>
     * 　@　@　@　@検索結果が取得できなかった場合は、nullを返却し、終了する。<BR>
     * <BR>
     * 　@１－４）１－３）の戻り値を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strCommodityCode - (商品コード)<BR>
     * 商品コード<BR>
     * <BR>
     * 10:　@株式<BR>
     * 11:　@信用<BR>
     * 20:　@投信<BR>
     * 21:　@外投<BR>
     * 22:　@累投<BR>
     * 23:　@MRF<BR>
     * 30:　@債券<BR>
     * 40:　@外株<BR>
     * 50:　@株先<BR>
     * 51:　@株指数OP<BR>
     * 52:　@債先<BR>
     * 53:　@債先OP<BR>
     * 54:　@店OP<BR>
     * 55:　@外先<BR>
     * 56:　@外先OP<BR>
     * 57:　@株OP<BR>
     * 60:　@外債<BR>
     * 70:　@金<BR>
     * 71:　@金GP<BR>
     * 80:　@特殊<BR>
     * 91:　@CD<BR>
     * 92:　@CP<BR>
     * 93:　@BA<BR>
     * 99:　@金銭<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@return List
     * @@roseuid 417DCF690174
     */
    public List getBalanceRecordFromBookValueSpec(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBalanceRecordFromBookValueSpec(WEB3GentradeMainAccount, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）１－１）上記検索条件を基に、検索条件文字列を作成する。
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" and rec_div = ? ");

        //１－２）"?"にセットするためのパラメータセットを作成する。
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(WEB3BookValueRecordDivDef.BALANCE_REC);
        Object[] l_objArray = new Object[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_objArray);
        //１－３）this.get簿価単価明細一覧()メソッドをコールする。
        List l_lisgetBookValueSpecList = 
            this.getBookValueSpecList(l_mainAccount, l_strCommodityCode, l_strProductCode, l_sbQueryString.toString(), l_objArray, null);
        if (l_lisgetBookValueSpecList == null || l_lisgetBookValueSpecList.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //１－４）１－３）の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisgetBookValueSpecList;
    }

    /**
     * (get簿価単価明細一覧)<BR>
     * (get簿価単価明細一覧のオーバーロード)<BR>
     * 引数の顧客、商品コード、銘柄コード、表示期間、<BR>
     * に該当する簿価単価明細一覧(明細レコードのみ)を取得する。<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列と <BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@　@レコード区分 = "2：明細レコード"<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 = " and rec_div = ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。 <BR>
     * 　@　@ArrayListを生成し、以下の値をセットする。 <BR>
     * 　@　@　@・"2：明細レコード"<BR>
     * <BR>
     * 　@１－３）パラメータ.表示期間、is表示期間前の値により、以下の条件を<BR>
     * 　@　@　@　@検索条件に追加する。<BR>
     * <BR>
     * 　@　@　@　@【パラメータ.is表示期間前 == trueの場合】<BR>
     * 　@　@　@　@(表示期間前の明細データを取得する。)<BR>
     * <BR>
     * 　@　@　@　@　@パラメータ.表示期間が、<BR>
     * 　@　@　@　@　@　@["0：前月月初以降"]<BR>
     * 　@　@　@　@　@　@　@検索条件文字列 += "and calc_date <= last_day(add_months(work_date, -2))"<BR>
     * <BR>
     * 　@　@　@　@　@　@["1：1ヶ月分"の場合]<BR>
     * 　@　@　@　@　@　@　@検索条件文字列 += "and calc_date <= add_months(work_date, -1)"<BR>
     * <BR>
     * 　@　@　@　@　@　@["2：1週間分"の場合]<BR>
     * 　@　@　@　@　@　@　@検索条件文字列 += "and calc_date <= work_date - 7"<BR>
     * <BR>
     * 　@　@　@　@　@　@["3：前日1日分"の場合]<BR>
     * 　@　@　@　@　@　@　@検索条件文字列 += "and calc_date <= work_date - 1"<BR>
     * <BR>
     * 　@　@　@　@【上記以外の場合】<BR>
     * 　@　@　@　@(表示期間以降の明細データを取得する。)<BR>
     * <BR>
     * 　@　@　@　@　@パラメータ.表示期間が、<BR>
     * 　@　@　@　@　@　@["0：前月月初以降"]<BR>
     * 　@　@　@　@　@　@　@検索条件文字列 += "and calc_date > last_day(add_months(work_date, -2)) "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ " and calc_date <= work_date "<BR>
     * <BR>
     * 　@　@　@　@　@　@["1：1ヶ月分"の場合]<BR>
     * 　@　@　@　@　@　@　@検索条件文字列 += "and calc_date > add_months(work_date, -1)"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ " and calc_date <= work_date "<BR>
     * <BR>
     * 　@　@　@　@　@　@["2：1週間分"の場合]<BR>
     * 　@　@　@　@　@　@　@検索条件文字列 += "and calc_date > work_date - 7"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ " and calc_date <= work_date "<BR>
     * <BR>
     * 　@　@　@　@　@　@["3：前日1日分"の場合]<BR>
     * 　@　@　@　@　@　@　@検索条件文字列 += "and calc_date = work_date "<BR>
     * <BR>
     * ２）以下のソート条件を作成する。<BR>
     * 　@－計算年月日　@昇順<BR>
     * 　@－SORT-NO　@昇順<BR>
     * <BR>
     * 　@ソート条件 = "calc_date asc, sort_no asc "<BR>
     * <BR>
     * ３）this.get簿価単価明細一覧()メソッドをコールする。<BR>
     * <BR>
     * 　@[get簿価単価明細一覧()にセットするパラメータ]<BR>
     * 　@　@顧客：　@パラメータ.顧客<BR>
     * 　@　@商品コード：　@パラメータ.商品コード<BR>
     * 　@　@銘柄コード：　@パラメータ.銘柄コード<BR>
     * 　@　@検索条件文字列：　@作成した検索条件文字列<BR>
     * 　@　@検索条件データコンテナ：　@作成したArrayList.toArray()の戻り値<BR>
     * 　@　@ソート条件：　@作成したソート条件<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却し、終了する。<BR>
     * <BR>
     * ４）３）の戻り値を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strCommodityCode - (商品コード)<BR>
     * 商品コード<BR>
     * <BR>
     * 10:　@株式<BR>
     * 11:　@信用<BR>
     * 20:　@投信<BR>
     * 21:　@外投<BR>
     * 22:　@累投<BR>
     * 23:　@MRF<BR>
     * 30:　@債券<BR>
     * 40:　@外株<BR>
     * 50:　@株先<BR>
     * 51:　@株指数OP<BR>
     * 52:　@債先<BR>
     * 53:　@債先OP<BR>
     * 54:　@店OP<BR>
     * 55:　@外先<BR>
     * 56:　@外先OP<BR>
     * 57:　@株OP<BR>
     * 60:　@外債<BR>
     * 70:　@金<BR>
     * 71:　@金GP<BR>
     * 80:　@特殊<BR>
     * 91:　@CD<BR>
     * 92:　@CP<BR>
     * 93:　@BA<BR>
     * 99:　@金銭<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strDisplayTerm - (表示期間)<BR>
     * 表示期間<BR>
     * <BR>
     * 0：　@前月月初以降(DEFAULT)<BR>
     * 1：　@1ヶ月分<BR>
     * 2：　@1週間分<BR>
     * 3：　@前日1日分<BR>
     * @@param l_isBeforeDisplayTerm - (is表示期間前)<BR>
     * 表示期間前のデータを取得するかどうかのフラグ<BR>
     * <BR>
     * false：　@表示期間以降のデータを取得<BR>
     * true：　@表示期間前のデータを取得<BR>
     * @@return List
     * @@roseuid 417DCF690184
     */
    public List getBookValueSpecList(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode, String l_strDisplayTerm, boolean l_isBeforeDisplayTerm) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBookValueSpecList(WEB3GentradeMainAccount, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //１）以下の検索条件を表す、検索条件文字列とArrayList(検索条件データコンテナ)を作成する。
        //１－１）上記検索条件を基に、検索条件文字列を作成する。
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" and rec_div = ? ");
        //１－２）"?"にセットするためのパラメータセットを作成する。 <BR>
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(WEB3BookValueRecordDivDef.DETAIL_REC);

        //１－３）パラメータ.表示期間、is表示期間前の値により、以下の条件を検索条件に追加する。
        if (l_isBeforeDisplayTerm)
        {
            if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.DEFAULT))
            {
                l_sbQueryString.append("and calc_date <= last_day(add_months(work_date, ?))");
                l_lisQueryVars.add(new Integer(-2));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_MONTH))
            {
                l_sbQueryString.append("and calc_date <= add_months(work_date, ?)");
                l_lisQueryVars.add(new Integer(-1));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_WEEK))
            {
                l_sbQueryString.append("and calc_date <= work_date - ?");
                l_lisQueryVars.add(new Integer(7));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY))
            {
                l_sbQueryString.append("and calc_date <= work_date - ?");
                l_lisQueryVars.add(new Integer(1));
            }
        }
        else
        {
            if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.DEFAULT))
            {
                l_sbQueryString.append("and calc_date > last_day(add_months(work_date, ?)) ");
                l_sbQueryString.append(" and calc_date <= work_date ");
                l_lisQueryVars.add(new Integer(-2));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_MONTH))
            {
                l_sbQueryString.append("and calc_date > add_months(work_date, ?)");
                l_sbQueryString.append(" and calc_date <= work_date ");
                l_lisQueryVars.add(new Integer(-1));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_WEEK))
            {
                l_sbQueryString.append("and calc_date > work_date - ?");
                l_sbQueryString.append(" and calc_date <= work_date ");
                l_lisQueryVars.add(new Integer(7));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY))
            {
                l_sbQueryString.append("and calc_date = work_date ");

            }
        }
        //２）以下のソート条件を作成する。
        String l_strSortCond = "calc_date asc, sort_no asc ";
        Object[] l_objArray = new Object[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_objArray);
        //３）this.get簿価単価明細一覧()メソッドをコールする。
        List l_lisgetBookValueSpecList =
                this.getBookValueSpecList(l_mainAccount, l_strCommodityCode, l_strProductCode, l_sbQueryString.toString(), l_objArray, l_strSortCond);
        if (l_lisgetBookValueSpecList == null || l_lisgetBookValueSpecList.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //４）３）の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisgetBookValueSpecList;
    }
    
    /**
     * (get計算年月日)<BR>
     *引数の検索条件に該当する「損益明細レコード」から「計算年月日」を返却する。 <BR>
     *<BR>
     *１） 以下の顧客情報を検索条件に追加する。 <BR>
     *　@　@　@・証券会社コード <BR>
     *　@　@　@・部店コード <BR>
     *　@　@　@・口座コード <BR>
     *<BR>
     *　@○追加検索条件文字列 =  "institution_code = ? and "  <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "branch_code = ? and "  <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "account_code = ? " <BR>
     *<BR>
     *　@○作成した検索条件文字列 = 追加検索条件文字列 + パラメータ.検索条件文字列とする。 <BR>
     *<BR>
     *　@○追加検索条件データコンテナ <BR>
     *　@　@　@(ArrayListを生成し、以下の値を上から順に追加する) <BR>
     *　@　@　@　@・パラメータ.顧客.証券会社コード <BR>
     *　@　@　@　@・パラメータ.顧客.部店コード <BR>
     *　@　@　@　@・パラメータ.顧客.口座コード <BR>
     *　@　@　@　@・パラメータ.検索条件データコンテナの要素全て(0番目から順次追加) <BR>
     *<BR>
     *　@○作成した検索条件データコンテナ = 追加検索条件データコンテナとする。 <BR>
     *<BR>
     *２） QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>
     *<BR>
     *　@[doFindAllQuary()にセットするパラメータ] <BR>
     *　@　@arg0：　@"損益明細テーブル(profit_loss_spec)" <BR>
     *　@　@arg1：　@作成した検索条件文字列 <BR>
     *　@　@arg2：　@パラメータ.ソート条件 <BR>
     *　@　@arg3：　@null <BR>
     *　@　@arg4：　@作成した検索条件データコンテナ <BR>
     *　@　@arg5：　@1 <BR>
     *　@　@arg6：　@0 <BR>
     *<BR>
     *３） ２）の戻り値ListPageより「計算年月日」を取得し返却する。  <BR>
     *　@※ListPageの長さ==0 OR ListPage==nullの場合、 nullを返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_objQueryDataContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getCalcDate(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strQueryString, 
        Object[] l_objQueryDataContainers, 
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCalcDate(WEB3GentradeMainAccount, String, Object[], String)";
        log.entering(STR_METHOD_NAME);
        //１）以下の顧客情報を検索条件に追加する。
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("institution_code = ? and ");
        l_sbQueryString.append("branch_code = ? and ");
        l_sbQueryString.append("account_code = ? ");
        l_sbQueryString.append(l_strQueryString);
        
        //追加検索条件データコンテナ
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
        l_lisQueryVars.add(l_mainAccount.getAccountCode());
        int l_intLength = l_objQueryDataContainers.length;
        for (int i = 0; i < l_intLength; i++)
        {
            l_lisQueryVars.add(l_objQueryDataContainers[i]);
        }
        
        //２） QueryProcessor.doFindAllQuery()メソッドをコールする。
        Object[] l_objArrays = new Object[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_objArrays);
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                ProfitLossSpecRow.TYPE,
                l_sbQueryString.toString(),
                l_strSortCond,
                null,
                l_objArrays,
                1,
                0);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //３） ２）の戻り値ListPageより「計算年月日」を取得し返却する。  
        //　@※ListPageの長さ==0 OR ListPage==nullの場合、 nullを返却する。
        Date l_datCalcDate = null;
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            ProfitLossSpecRow l_profitLossSpecRow = (ProfitLossSpecRow)l_lisRecords.get(0);
            l_datCalcDate = l_profitLossSpecRow.getCalcDate();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_datCalcDate;
    }
    
}
@
