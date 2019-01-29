head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSExecEndNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)株式出来終了通知サービスImpl(WEB3EquityPTSExecEndNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 趙林鵬(中訊) 新規作成 モデルNo.1285，1295
Revision History : 2008/02/18 趙林鵬(中訊) モデルNo.1302
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3MarginUpdateEventInterceptor;
import webbroker3.equity.message.WEB3EquityPTSExecEndNotifyRequest;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSExecEndNotifyService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ((PTS)株式出来終了通知サービスImpl)<BR>
 * (PTS)株式出来終了通知サービス実装クラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3EquityPTSExecEndNotifyServiceImpl
    implements WEB3EquityPTSExecEndNotifyService
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSExecEndNotifyServiceImpl.class);

    /**
     * @@roseuid 40B1BACD02E9
     */
    public WEB3EquityPTSExecEndNotifyServiceImpl()
    {

    }

    /**
     * (PTS)株式出来終了通知サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図「（（PTS）出来終了通知サービス）出来終了通知」参照<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
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

        WEB3EquityPTSExecEndNotifyRequest l_ptsExecEndNotifyRequest =
            (WEB3EquityPTSExecEndNotifyRequest)l_request;

        //vaidate( )
        l_ptsExecEndNotifyRequest.validate();

        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //(PTS)オンライン実行結果TransactionCallbackオブジェクトを生成する。
            //引数の設定仕様は以下の通り
            //証券会社コード : リクエスト.証券会社コード
            //From口座ID : リクエスト.From口座ID
            //To口座ID : リクエスト.To口座ID
            WEB3EquityPTSOnlineRunStatusTransactionCallback l_onlineRunStatusTransactionCallback =
                new WEB3EquityPTSOnlineRunStatusTransactionCallback(
                    l_ptsExecEndNotifyRequest.institutionCode,
                    l_ptsExecEndNotifyRequest.rangeFrom,
                    l_ptsExecEndNotifyRequest.rangeTo);

            // doTransaction(トランザクション属性 : int, TransactionCallback  : TransactionCallback)
            //トランザクション属性 : TX_CREATE_NEW
            //TransactionCallback : 生成した(PTS)オンライン実行結果TransactionCallbackオブジェクト
            WEB3GentradeOnlineRunStatus l_onlineRunStatus =
                (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    l_onlineRunStatusTransactionCallback);

            //（PTS）出来終了通知TransactionCallback(String, long, long, オンライン実行結果, String)
            //証券会社コード : リクエスト.証券会社コード
            //From口座ID : リクエスト.From口座ID
            //To口座ID : リクエスト.To口座ID
            //オンライン実行結果 :
            //　@(PTS)オンライン実行結果TransactionCallback.process( )の戻り値であるオンライン実行結果オブジェクト
            //市場コード : リクエスト.市場コード
            WEB3EquityPTSExecEndNotifyTransactionCallback l_execEndNotifyTransactionCallback =
                new WEB3EquityPTSExecEndNotifyTransactionCallback(
                    l_ptsExecEndNotifyRequest.institutionCode,
                    l_ptsExecEndNotifyRequest.rangeFrom,
                    l_ptsExecEndNotifyRequest.rangeTo,
                    l_onlineRunStatus,
                    l_ptsExecEndNotifyRequest.marketCode);

            //doTransaction(トランザクション属性 : int, TransactionCallback : TransactionCallback)
            //トランザクション属性 : TX_CREATE_NEW
            //TransactionCallback : 生成した(PTS)出来終了通知TransactionCallbackオブジェクト
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_execEndNotifyTransactionCallback);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            //process( )で例外がthrowされた場合
            //throwされた例外オブジェクトより、エラー情報を取得して再throwする。
            WEB3BaseException l_ex = (WEB3BaseException)l_dataCallbackException.getDetails();

            log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw l_ex;
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

        log.exiting(STR_METHOD_NAME);
        return l_ptsExecEndNotifyRequest.createResponse();
    }

    /**
     * ((PTS)出来終了通知TransactionCallback)<BR>
     * （PTS）出来終了通知TransactionCallbackクラス<BR>
     */
    public class WEB3EquityPTSExecEndNotifyTransactionCallback
        implements TransactionCallback
    {
        /**
         * (証券会社コード)<BR>
         * 証券会社コード<BR>
         */
        private String institutionCode;

        /**
         * (From口座ID)<BR>
         * From口座ID<BR>
         */
        private long rangeFrom;

        /**
         * (To口座ID)<BR>
         * To口座ID<BR>
         */
        private long rangeTo;

        /**
         * (オンライン実行結果)<BR>
         * オンライン実行結果<BR>
         */
        private WEB3GentradeOnlineRunStatus onlineRunStatus;

        /**
         * (市場コード)<BR>
         * 市場コード<BR>
         */
        private String marketCode;

        /**
         * (（PTS）出来終了通知TransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 当クラスのプロパティに、引数の同項目をセットする。<BR>
         * <BR>
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード。<BR>
         * @@param l_lngRangeFrom - (From口座ID)<BR>
         * From口座ID。<BR>
         * @@param l_lngRangeTo - (To口座ID)<BR>
         * To口座ID。<BR>
         * @@param l_onlineRunStatus - (オンライン実行結果)<BR>
         * オンライン実行結果。<BR>
         * @@param l_strMarketCode - (市場コード)<BR>
         * 市場コード<BR>
         */
        public WEB3EquityPTSExecEndNotifyTransactionCallback(
            String l_strInstitutionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo,
            WEB3GentradeOnlineRunStatus l_onlineRunStatus,
            String l_strMarketCode)
        {
            institutionCode = l_strInstitutionCode;
            rangeFrom = l_lngRangeFrom;
            rangeTo = l_lngRangeTo;
            onlineRunStatus = l_onlineRunStatus;
            marketCode = l_strMarketCode;
        }

        /**
         * (get処理対象顧客一覧)<BR>
         * 有効注文を持つ顧客の一覧を作成し返す。<BR>
         * <BR>
         * １）　@PTS市場の市場IDを取得する。<BR>
         * 　@　@　@市場IDは、拡張金融オブジェクトマネージャ.get市場( )<BR>
         * 　@　@　@で取得した市場オブジェクトから取得する。<BR>
         * 　@　@　@　@[get市場( )の引数設定]<BR>
         * 　@　@　@　@　@証券会社コード：　@this.証券会社コード<BR>
         * 　@　@　@　@　@市場コード：　@this.市場コード<BR>
         * <BR>
         * ２）　@有効注文の注文単位オブジェクトを全て取得する。<BR>
         * <BR>
         * 　@　@　@クエリプロセッサを使用し、<BR>
         * 　@　@　@以下の条件に合致する注文単位オブジェクトを全て取得する。<BR>
         * 　@　@　@（口座ID昇順指定）<BR>
         * <BR>
         * 　@　@　@----------------------------------------------------------<BR>
         * 　@　@　@＜抽出条件＞<BR>
         * <BR>
         * 　@　@　@　@　@　@部店ID in this.証券会社コード<BR>
         * 　@　@　@　@　@　@に該当する証券会社.getBranches( )の戻り値のいずれか<BR>
         * 　@　@　@かつ　@銘柄タイプ == "株式"<BR>
         * 　@　@　@かつ　@注文種別 != （"株式ミニ株買注文"、"株式ミニ株売注文"）<BR>
         * 　@　@　@かつ　@注文有効状態 == "オープン"<BR>
         * 　@　@　@かつ　@this.From口座ID <= 口座ID<BR>
         * 　@　@　@かつ　@口座ID <= this.To口座ID<BR>
         * 　@　@　@かつ　@発注日 < PTS取引時間管理.getPTS発注日( )(*1)<BR>
         * 　@　@　@かつ　@市場ID == １）で取得した市場ID<BR>
         * <BR>
         * 　@　@　@※(*1)当日のPTS市場開局時間帯に発注した注文のみを取得するための条件。<BR>
         * 　@　@　@----------------------------------------------------------<BR>
         * <BR>
         * ３）　@顧客オブジェクトの一覧を作成する。<BR>
         * <BR>
         * 　@　@　@まず、２）で取得した注文単位オブジェクト.口座IDの一意な一覧を作成する。<BR>
         * 　@　@　@各口座ID毎に、該当する顧客オブジェクトを取得し、<BR>
         * 　@　@　@返却用の顧客オブジェクトの一覧に追加していく。<BR>
         * <BR>
         * ４）　@作成した顧客オブジェクトの一覧を返却する。<BR>
         * 　@　@　@※該当データなし時はnullを返却する。<BR>
         * @@return WEB3GentradeMainAccount[]
         * @@throws WEB3BaseException
         */
        protected WEB3GentradeMainAccount[] getMainAccounts() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getMainAccounts()";
            log.entering(STR_METHOD_NAME);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            Institution l_institution = null;
            WEB3GentradeMarket l_market = null;
            try
            {
                //１）　@PTS市場の市場IDを取得する。
                //市場IDは、拡張金融オブジェクトマネージャ.get市場( )で取得した市場オブジェクトから取得する。
                //証券会社コード：　@this.証券会社コード
                //市場コード：　@this.市場コード
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                    this.institutionCode,
                    this.marketCode);

                //this.証券会社コードに該当する証券会社
                l_institution = l_accountManager.getInstitution(this.institutionCode);
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

            //２）　@有効注文の注文単位オブジェクトを全て取得する。
            StringBuffer l_sbWhere = new StringBuffer();

            //部店ID in this.証券会社コードに該当する証券会社.getBranches( )の戻り値のいずれか
            Branch[] l_branchs = l_institution.getBranches();
            if (l_branchs != null && l_branchs.length > 0)
            {
                l_sbWhere.append("branch_id in (" + l_branchs[0].getBranchId());
                for (int i = 1; i < l_branchs.length; i++)
                {
                    l_sbWhere.append("," + l_branchs[i].getBranchId());
                }
                l_sbWhere.append(") and ");
            }

            //銘柄タイプ == "株式"
            //かつ　@注文種別 != （"株式ミニ株買注文"、"株式ミニ株売注文"）
            //かつ　@注文有効状態 == "オープン"
            //かつ　@this.From口座ID <= 口座ID
            //かつ　@口座ID <= this.To口座ID
            //かつ　@発注日 < PTS取引時間管理.getPTS発注日( )(*1)
            //かつ　@市場ID == １）で取得した市場ID
            l_sbWhere.append(" product_type = ?");
            l_sbWhere.append(" and order_type not in (?,?)");
            l_sbWhere.append(" and order_open_status = ?");
            l_sbWhere.append(" and ? <= account_id");
            l_sbWhere.append(" and account_id <= ?");
            l_sbWhere.append(" and biz_date < ?");
            l_sbWhere.append(" and market_id = ?");

            String l_strBizDate = WEB3DateUtility.formatDate(
                WEB3EquityPTSTradingTimeManagement.getPTSOrderBizDate(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            Object[] l_objWheres =
            {
                ProductTypeEnum.EQUITY,
                OrderTypeEnum.MINI_STOCK_BUY,
                OrderTypeEnum.MINI_STOCK_SELL,
                OrderOpenStatusEnum.OPEN,
                new Long(rangeFrom),
                new Long(rangeTo),
                l_strBizDate,
                new Long(l_market.getMarketId())
            };

            //有効注文の注文単位オブジェクトを全て取得する
            List l_lisRecords = new ArrayList();
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                String l_strSort = "account_id asc";

                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    l_strSort,
                    null,
                    l_objWheres);
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

            //３）　@顧客オブジェクトの一覧を作成する。
            WEB3GentradeMainAccount[] l_mainAccounts = null;
            if (l_lisRecords != null && !l_lisRecords.isEmpty())
            {
                long l_accountId = 0L;
                int l_intSize = l_lisRecords.size();
                List l_lisAccounts = new ArrayList();
                for (int i = 0; i < l_intSize; i++)
                {
                    //２）で取得した注文単位オブジェクト.口座IDの一意な一覧を作成する。
                    EqtypeOrderUnitRow l_orderUnitRow =
                        (EqtypeOrderUnitRow)l_lisRecords.get(i);

                    if (l_accountId != l_orderUnitRow.getAccountId())
                    {
                        l_accountId = l_orderUnitRow.getAccountId();

                        WEB3GentradeMainAccount l_mainAccount = null;
                        try
                        {
                            //各口座ID毎に、該当する顧客オブジェクトを取得し、
                            l_mainAccount =
                                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_accountId);
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

                        l_lisAccounts.add(l_mainAccount);
                    }
                }

                //返却用の顧客オブジェクトの一覧に追加していく。
                l_mainAccounts = new WEB3GentradeMainAccount[l_lisAccounts.size()];
                l_lisAccounts.toArray(l_mainAccounts);
            }

            //４）　@作成した顧客オブジェクトの一覧を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_mainAccounts;
        }

        /**
         * (set取引カレンダコンテキスト)<BR>
         * 取引カレンダが利用するコンテキストを生成する。<BR>
         * <BR>
         * １）　@取引カレンダコンテキストに内容をセットする。<BR>
         * <BR>
         * 　@取引カレンダコンテキスト.証券会社コード = this.証券会社コード<BR>
         * 　@取引カレンダコンテキスト.部店コード =<BR>
         * 　@　@（部店市場別・PTS）取扱条件.get（部店市場別・PTS）取扱条件( )をコールし、<BR>
         * 　@　@戻り値リストの0番目の要素の（部店市場別・PTS）取扱条件オブジェクトより<BR>
         * 　@　@部店コードを取得する。<BR>
         * 　@　@[get（部店市場別・PTS）取扱条件( )の引数設定]<BR>
         * 　@　@　@証券会社コード：　@this.証券会社コード<BR>
         * 　@取引カレンダコンテキスト.市場コード = this.市場コード<BR>
         * 　@取引時間コンテキスト.受付時間区分 = ”株式・信用”<BR>
         * 　@取引時間コンテキスト.銘柄コード = ”0：DEFAULT”<BR>
         * <BR>
         * 　@※注文受付商品、注文受付トランザクションは設定不要。<BR>
         * <BR>
         * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
         * 　@　@にて取引時間コンテキストをセットする。<BR>
         * 設定キー：　@PTS取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
         * <BR>
         * ２）　@受付日時、日付ロールをセットする。<BR>
         * 　@－PTS取引時間管理.setTimestamp()をコールする。<BR>
         * @@throws WEB3BaseException
         */
        protected void setTradingClendarContext() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "setTradingClendarContext()";
            log.entering(STR_METHOD_NAME);

            //取引カレンダが利用するコンテキストを生成する。
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();

            //１）　@取引カレンダコンテキストに内容をセットする。
            //取引カレンダコンテキスト.証券会社コード = this.証券会社コード
            l_context.setInstitutionCode(this.institutionCode);

            //取引カレンダコンテキスト.部店コード =
            //（部店市場別・PTS）取扱条件.get（部店市場別・PTS）取扱条件( )をコールし、
            //戻り値リストの0番目の要素の（部店市場別・PTS）取扱条件オブジェクトより
            //部店コードを取得する。
            WEB3GentradeBranchMarketPTSDealtCond[] l_BranchMarketPTSDealtConds =
                WEB3GentradeBranchMarketPTSDealtCond.getBranchMarketPTSDealtCond(this.institutionCode);

            BranchMarketPtsDealtCondRow l_branchMarketPtsDealtCondRow =
                (BranchMarketPtsDealtCondRow)l_BranchMarketPTSDealtConds[0].getDataSourceObject();
            l_context.setBranchCode(l_branchMarketPtsDealtCondRow.getBranchCode());

            //取引カレンダコンテキスト.市場コード = this.市場コード
            l_context.setMarketCode(this.marketCode);

            //取引時間コンテキスト.受付時間区分 = ”株式・信用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

            //取引時間コンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。
            //設定キー：　@PTS取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //２）　@受付日時、日付ロールをセットする。
            //　@－PTS取引時間管理.setTimestamp()をコールする。
            WEB3EquityPTSTradingTimeManagement.setTimestamp();
            log.exiting(STR_METHOD_NAME);
        }

        /**
         * トランザクション処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（（PTS）出来終了通知サービス）process」参照。<BR>
         * <BR>
         * @@return Object
         * @@throws DataCallbackException
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            WEB3GentradeMainAccount[] l_mainAccounts = null;
            try
            {
                //set取引カレンダコンテキスト( )
                this.setTradingClendarContext();

                //get処理対象顧客一覧( )
                l_mainAccounts = this.getMainAccounts();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }

            //取得した顧客オブジェクト数分、Loop
            int l_intSize = 0;
            if (l_mainAccounts != null)
            {
                l_intSize = l_mainAccounts.length;
            }

            //以下の顧客オブジェクト数分のLoop内で、処理中にエラー（システムエラー等）が発生した場合は、
            //その顧客の出来終了結果をrollbackし、次の顧客の「出来終了実行」を行う。
            String l_strStatus = WEB3RunStatusDivDef.DEALED;
            for (int i = 0; i < l_intSize; i++)
            {
                //（PTS）顧客単位出来終了TransactionCallback(顧客, String)
                //顧客 : 取得した顧客オブジェクト配列の要素
                //市場コード : this.市場コード
                WEB3EquityPTSAccountExecEndTransactionCallback l_accountExecEndTransactionCallback =
                    new WEB3EquityPTSAccountExecEndTransactionCallback(
                        l_mainAccounts[i],
                        this.marketCode);

                //doTransaction(トランザクション属性 : int, TransactionCallback : TransactionCallback)
                //トランザクション属性 : TX_CREATE_NEW
                //TransactionCallback : 生成した(PTS)顧客単位出来終了TransactionCallbackオブジェクト
                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_accountExecEndTransactionCallback);
                }
                catch (Exception l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    //一部の顧客でも異常終了した場合、"エラー"
                    l_strStatus = WEB3RunStatusDivDef.ERROR;
                }
            }

            //update実行ステータス区分(実行ステータス区分 : String)
            try
            {
                this.onlineRunStatus.updateRunStatusDiv(l_strStatus);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * ((PTS)顧客単位出来終了TransactionCallback)<BR>
         * （PTS）顧客単位出来終了TransactionCallbackクラス<BR>
         */
        public class WEB3EquityPTSAccountExecEndTransactionCallback
            implements TransactionCallback
        {
            /**
             * (顧客)。<BR>
             * 顧客オブジェクト。<BR>
             */
            private WEB3GentradeMainAccount mainAccount;

            /**
             * (市場コード)<BR>
             * 市場コード<BR>
             */
            private String marketCode;

            /**
             * (（PTS）顧客単位出来終了TransactionCallback)<BR>
             * コンストラクタ。<BR>
             * <BR>
             * 当クラスのプロパティに、引数の同項目をセットする。<BR>
             * <BR>
             * @@param l_mainAccount - (顧客)<BR>
             * 顧客オブジェクト。<BR>
             * @@param l_strMarketCode - (市場コード)<BR>
             * 市場コード<BR>
             */
            public WEB3EquityPTSAccountExecEndTransactionCallback(
                WEB3GentradeMainAccount l_mainAccount,
                String l_strMarketCode)
            {
                mainAccount = l_mainAccount;
                marketCode = l_strMarketCode;
            }

            /**
             * (is出来終了注文単位)<BR>
             * 指定の注文単位オブジェクトが出来終了対象かどうかを判定する。<BR>
             * <BR>
             * 引数の注文単位.注文失効日 < PTS取引時間管理.getPTS発注日( )(*1)の場合は、<BR>
             * 出来終了対象であると判定しtrueを返す。<BR>
             * 上記以外の場合は、falseを返す。<BR>
             * <BR>
             * (*1)注文有効期限が当日までの注文<BR>
             * <BR>
             * @@param l_orderUnit - (注文単位)<BR>
             * 注文単位オブジェクト。<BR>
             * @@return boolean
             */
            protected boolean isOrderExecEnd(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
            {
                final String STR_METHOD_NAME = "isOrderExecEnd(EqTypeOrderUnit)";
                log.entering(STR_METHOD_NAME);

                //PTS取引時間管理.getPTS発注日( )
                Date l_datBizDate = WEB3EquityPTSTradingTimeManagement.getPTSOrderBizDate();
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

                //注文単位.注文失効日
                Date l_datExpirationDate = l_eqtypeOrderUnitRow.getExpirationDate();

                //引数の注文単位.注文失効日 < PTS取引時間管理.getPTS発注日( )の場合
                if (WEB3DateUtility.compareToDay(l_datExpirationDate, l_datBizDate) < 0)
                {
                    //出来終了対象であると判定しtrueを返す。
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }

                //上記以外の場合は、falseを返す。
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            /**
             * トランザクション処理を実施する。<BR>
             * <BR>
             * シーケンス図<BR>
             * 「（（PTS）出来終了通知）顧客単位出来終了実行」参照。<BR>
             * @@throws DataCallbackException
             * @@return Object
             */
            public Object process() throws DataCallbackException
            {
                final String STR_METHOD_NAME = "process()";
                log.entering(STR_METHOD_NAME);

                //lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
                //証券会社コード : this.顧客.証券会社コード
                //部店コード : this.顧客.部店コード
                //口座コード : this.顧客.口座コード
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                MainAccountRow l_mainAccountRow = (MainAccountRow)mainAccount.getDataSourceObject();
                try
                {
                    l_accountManager.lockAccount(
                        l_mainAccountRow.getInstitutionCode(),
                        l_mainAccountRow.getBranchCode(),
                        l_mainAccountRow.getAccountCode());
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new DataCallbackException(
                        l_ex.getMessage(),
                        l_ex);
                }

                //is信用口座開設()
                boolean l_blnIsMarginAccountEstablished =
                    mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

                // getSubAccount(補助口座タイプ : SubAccountTypeEnum)
                WEB3GentradeSubAccount l_subAccount = null;
                WEB3GentradeMarket l_market = null;

                try
                {
                    if (l_blnIsMarginAccountEstablished)
                    {
                        //信用口座開設顧客（this.顧客.is信用口座開設( )＝true）の場合、"株式信用取引口座（保証金）"
                        l_subAccount =
                            (WEB3GentradeSubAccount)mainAccount.getSubAccount(
                                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    }
                    else
                    {
                        //信用口座未開設顧客（this.顧客.is信用口座開設( )＝false）の場合、"株式取引口座（預り金）"
                        l_subAccount =
                            (WEB3GentradeSubAccount)mainAccount.getSubAccount(
                                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }

                    // get市場(証券会社コード : String, 市場コード : String)
                    //証券会社コード : this.顧客.証券会社コード
                    //市場コード : this.市場コード
                    WEB3GentradeFinObjectManager l_finObjectManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

                    l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_mainAccountRow.getInstitutionCode(),
                        this.marketCode);
                }
                catch (NotFoundException l_nfe)
                {
                    WEB3SystemLayerException l_ex =
                        new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_nfe.getMessage(),
                            l_nfe);

                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new DataCallbackException(
                        l_ex.getMessage(),
                        l_ex);
                }

                try
                {
                    //[抽出条件]
                    // 注文種別 != （"株式ミニ株買注文"、"株式ミニ株売注文"）
                    // 注文有効状態 ＝ "オープン"
                    // 発注日 ＜　@PTS取引時間管理.getPTS発注日( )
                    // 市場ID ＝ 取得した市場オブジェクト.市場ID
                    String l_strWhere = "order_type not in (?,?) and order_open_status = ?"
                        + " and biz_date < ? and market_id = ?";

                    String l_strBizDate = WEB3DateUtility.formatDate(
                        WEB3EquityPTSTradingTimeManagement.getPTSOrderBizDate(),
                        WEB3GentradeTimeDef.DATE_FORMAT_YMD);

                    Object[] l_objWhere =
                    {
                        OrderTypeEnum.MINI_STOCK_BUY,
                        OrderTypeEnum.MINI_STOCK_SELL,
                        OrderOpenStatusEnum.OPEN,
                        l_strBizDate,
                        new Long(l_market.getMarketId()),
                    };

                    TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                    WEB3EquityPTSOrderManager l_orderManager =
                        (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
                    //get注文単位一覧(補助口座, ProductTypeEnum, String, String[], String)
                    //補助口座 : 取得した補助口座オブジェクト
                    //銘柄タイプ : "株式"
                    //検索条件文字列 : 抽出条件はシーケンスのノートアンカー参照
                    //検索条件データコンテナ : 抽出条件はシーケンスのノートアンカー参照
                    //ソート条件 : null（ソート指定なし）
                    List l_lisOrderUnits = l_orderManager.getOrderUnits(
                        l_subAccount,
                        ProductTypeEnum.EQUITY,
                        l_strWhere,
                        l_objWhere,
                        null);

                    //取得した注文単位オブジェクト数分Loop処理
                    int l_intOrderUnitsSize = 0;
                    if (l_lisOrderUnits != null)
                    {
                        l_intOrderUnitsSize = l_lisOrderUnits.size();
                    }
                    for (int i = 0; i < l_intOrderUnitsSize; i++)
                    {
                        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnits.get(i);

                        //注文が未約定、または一部約定の場合のみ実施
                        if (l_orderUnit.isUnexecuted() || l_orderUnit.isPartiallyExecuted())
                        {
                            WEB3EquityExecutedMailSenderService l_execMailSenderService =
                                (WEB3EquityExecutedMailSenderService)Services.getService(
                                    WEB3EquityExecutedMailSenderService.class);

                            //sendMailProcess()
                            l_execMailSenderService.sendMailProcess(l_orderUnit, null, true);

                        }

                        //is出来終了注文単位(注文単位)
                        //注文単位 : 取得した注文単位オブジェクト配列の要素
                        boolean l_blnIsOrderExecEnd = this.isOrderExecEnd(l_orderUnit);

                        //is出来終了注文単位( )＝falseの場合、次の注文単位を処理する（continue）
                        if (!l_blnIsOrderExecEnd)
                        {
                            continue;
                        }

                        //更新イベントインタセプタ
                        WEB3MarginUpdateEventInterceptor l_interceptor = new WEB3MarginUpdateEventInterceptor();

                        //setThreadLocalPersistenceEventInterceptor(更新イベントインタセプタ
                        //: EqTypeOrderManagerPersistenceEventInterceptor)
                        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

                        // expireOrder(注文ID : long)
                        //注文ID :  取得した注文単位オブジェクト配列の要素.注文ID
                        ProcessingResult l_processingResult =
                            l_orderManager.expireOrder(l_orderUnit.getOrderId());

                        if (l_processingResult.isFailedResult())
                        {
                            log.debug(l_processingResult.getErrorInfo().getErrorMessage());
                            throw new WEB3BaseException(
                                l_processingResult.getErrorInfo(),
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                l_processingResult.getErrorInfo().getErrorMessage());
                        }
                    }

                    //余力再計算()
                    //補助口座 : 取得した補助口座オブジェクト
                    WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                        (WEB3TPTradingPowerReCalcService)Services.getService(
                            WEB3TPTradingPowerReCalcService.class);

                    l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new DataCallbackException(
                        l_ex.getMessage(),
                        l_ex);
                }

                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
    }

    /**
     * ((PTS)オンライン実行結果TransactionCallback)<BR>
     * (PTS)オンライン実行結果TransactionCallbackクラス。<BR>
     */
    public class WEB3EquityPTSOnlineRunStatusTransactionCallback
        implements TransactionCallback
    {
        /**
         * (証券会社コード)<BR>
         * 証券会社コード<BR>
         */
        private String institutionCode;

        /**
         * (From口座ID)<BR>
         * From口座ID<BR>
         */
        private long rangeFrom;

        /**
         * (To口座ID)<BR>
         * To口座ID<BR>
         */
        private long rangeTo;

        /**
         * (（PTS）オンライン実行結果TransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 当クラスのプロパティに、引数の同項目をセットする。<BR>
         * <BR>
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード。<BR>
         * @@param l_lngRangeFrom - (From口座ID)<BR>
         * From口座ID。<BR>
         * @@param l_lngRangeTo - (To口座ID)<BR>
         * To口座ID。<BR>
         */
        public WEB3EquityPTSOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo)
        {
            institutionCode = l_strInstitutionCode;
            rangeFrom = l_lngRangeFrom;
            rangeTo = l_lngRangeTo;
        }

        /**
         * トランザクション処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（（PTS）出来終了通知サービス）出来終了通知」参照。<BR>
         * @@throws DataCallbackException
         * @@return Object
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //set処理中
            // 証券会社コード：　@this.証券会社コード
            // 銘柄タイプ : "株式"
            // 先物／オプション区分 : "DEFAULT"  
            // オンラインサービス区分 : "(PTS)出来終了通知"
            // From口座ID：　@this.From口座ID
            // To口座ID：　@this.To口座ID
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
                    this.institutionCode,
                    ProductTypeEnum.EQUITY,
                    WEB3FuturesOptionDivDef.DEFAULT,
                    WEB3OnlineServiceDiv.PTS_ORDER_EXEC_END,
                    this.rangeFrom,
                    this.rangeTo);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }

            //set処理中()の戻り値を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }
}
@
