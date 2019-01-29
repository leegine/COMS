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
filename	WEB3EquityOrderExecEndNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出来終了通知サービスImpl(WEB3EquityOrderExecEndNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
                   2006/11/20 張騰宇(中訊) モデル 1024
Revesion History : 2008/01/25 趙林鵬(中訊) モデルNO.1287
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;

import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3MarginUpdateEventInterceptor;
import webbroker3.equity.message.WEB3EquityExecEndNotifyRequest;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecEndNotifyService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * （出来終了通知サービスImpl）。<BR>
 * <BR>
 * 出来終了通知サービス実装クラス
 * @@version 1.0
 */
public class WEB3EquityOrderExecEndNotifyServiceImpl
    implements WEB3EquityOrderExecEndNotifyService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderExecEndNotifyServiceImpl.class);

    /**
     * @@roseuid 40B1BACD02E9
     */
    public WEB3EquityOrderExecEndNotifyServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * 出来終了通知サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出来終了通知サービス）出来終了通知」参照<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4137CFA802E4
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityExecEndNotifyRequest l_execEndNotifyRequest =
            (WEB3EquityExecEndNotifyRequest)l_request;
        l_execEndNotifyRequest.validate();

        try
        {
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            OnlineTransactionCallback l_onTransactionCallback =
                new OnlineTransactionCallback(
                    l_execEndNotifyRequest.institutionCode,
                    l_execEndNotifyRequest.rangeFrom,
                    l_execEndNotifyRequest.rangeTo);
            l_onlineRunStatus =
                (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_onTransactionCallback);
            ServiceTransactionCallback l_transactionCallback =
                new ServiceTransactionCallback(
                    l_execEndNotifyRequest.institutionCode,
                    l_execEndNotifyRequest.rangeFrom,
                    l_execEndNotifyRequest.rangeTo,
                    l_onlineRunStatus);
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_transactionCallback);
        }
        catch (DataCallbackException l_dce)
        {
            WEB3BaseException l_wbe = (WEB3BaseException)l_dce.getDetails();
            throw l_wbe;
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_execEndNotifyRequest.createResponse();
    }
    
    /**
     * (オンライン実行結果TransactionCallback)<BR>
     * オンライン実行結果TransactionCallbackクラス。<BR>
     */
    public class OnlineTransactionCallback
        implements TransactionCallback
    {
        /**
         * (証券会社コード)。<BR>
         * 出来終了対象の証券会社コード。<BR>
         */
        private String institutionCode;
    
        /**
         * (From口座ID)。<BR>
         * From口座ID。<BR>
         */
        private long rangeFrom;
    
        /**
         * (To口座ID)。<BR>
         * To口座ID。<BR>
         */
        private long rangeTo;

        /**
         * (オンライン実行結果TransactionCallback)。<BR>
         * コンストラクタ。<BR>
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード。<BR>
         * @@param l_lngRangeFrom - (From口座ID)<BR>
         * From口座ID。<BR>
         * @@param l_lngRangeTo - (To口座ID)<BR>
         * To口座ID。<BR>
         */
        public OnlineTransactionCallback(
            String l_strInstitutionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo)
        {
            institutionCode = l_strInstitutionCode;
            rangeFrom = l_lngRangeFrom;
            rangeTo = l_lngRangeTo;
        }
        
        /**
         * (process)<BR>
         * オンライン実行結果テーブルへ"処理中"設定を行う。<BR>
         * シーケンス図
         *  「（出来終了通知サービス）process」参照。 
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 40B1C28501F0
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "OnlineTransactionCallback.process()";
            log.entering(STR_METHOD_NAME);

            String l_strStatus = WEB3RunStatusDivDef.DEALED;
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
                    this.institutionCode, ProductTypeEnum.EQUITY,
                    WEB3FuturesOptionDivDef.DEFAULT, WEB3OnlineServiceDiv.ORDER_EXEC_END,
                    this.rangeFrom, this.rangeTo);
            }
            catch (WEB3BaseException l_be)
            {
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(l_be.getMessage(), l_be);
            }
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }
    /**
     * (出来終了通知TransactionCallback)<BR>
     * 出来終了通知TransactionCallbackクラス。<BR>
     */
    public class ServiceTransactionCallback
        implements TransactionCallback
    {
        /**
         * (証券会社コード)。<BR>
         * 出来終了対象の証券会社コード。<BR>
         */
        private String institutionCode;
    
        /**
         * (From口座ID)。<BR>
         * From口座ID。<BR>
         */
        private long rangeFrom;
    
        /**
         * (To口座ID)。<BR>
         * To口座ID。<BR>
         */
        private long rangeTo;
        
        /**
         * (オンライン実行結果)。<BR>
         * オンライン実行結果。<BR>
         */
        private WEB3GentradeOnlineRunStatus onlineRunStatus;
        
        /**
         * (出来終了通知TransactionCallback)。<BR>
         * コンストラクタ。<BR>
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード。<BR>
         * @@param l_lngRangeFrom - (From口座ID)<BR>
         * From口座ID。<BR>
         * @@param l_lngRangeTo - (To口座ID)<BR>
         * To口座ID。<BR>
         * @@param l_onTransactionCallback - (オンライン実行結果)<BR>
         * オンライン実行結果。<BR>
         */
        public ServiceTransactionCallback(
            String l_strInstitutionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo,
            WEB3GentradeOnlineRunStatus l_onTransactionCallback)
        {
            institutionCode = l_strInstitutionCode;
            rangeFrom = l_lngRangeFrom;
            rangeTo = l_lngRangeTo;
            onlineRunStatus = l_onTransactionCallback;
        }
        
        /**
         * (process)<BR>
         * 出来終了通知処理を実施する。<BR>
         * シーケンス図
         *  「（出来終了通知サービス）process」参照。 
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 40B1C28501F0
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "ServiceTransactionCallback.process()";
            log.entering(STR_METHOD_NAME);

            String l_strStatus = WEB3RunStatusDivDef.DEALED;
            try
            {
                // set取引カレンダコンテキスト()
                setTradingClendarContext();

                // get処理対象顧客一覧()
                WEB3GentradeMainAccount[] l_accounts = getMainAccounts();
                
                // 取得した顧客オブジェクト数分、Loop
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                int l_intSize = 0;
                if (l_accounts != null)
                {
                    l_intSize = l_accounts.length;
                }
                for (int i = 0; i < l_intSize; i++)
                {
                    // 現在日時リセット
                    FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                    TradingSystem l_tradingSys = l_finApp.getTradingSystem();
                    ThreadLocalSystemAttributesRegistry.setAttribute(
                        WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

                    java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
                    ThreadLocalSystemAttributesRegistry.setAttribute(
                        WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_processTime);

                    log.debug("口座ID[" + i + "]:[" + l_accounts[i].getAccountId() + "]");
                    AccTransactionCallback l_transactionCallback =
                        new AccTransactionCallback(l_accounts[i]);
                    try
                    {
                        l_queryProcessor.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            l_transactionCallback);
                    }
                    catch (DataCallbackException l_dce)
                    {
                        log.error("顧客単位出来終了エラー発生 :口座ID[" + l_accounts[i].getAccountId() + "]");
                        WEB3BaseException l_wbe =
                            (WEB3BaseException)l_dce.getDetails();
                        log.error(l_wbe.getMessage(), l_wbe);
                        l_strStatus = WEB3RunStatusDivDef.ERROR;
                    }
                    catch (Exception l_exp)
                    {
                        // RuntimeExceptionがthrowされた場合も実行ステータス区分を”9:エラー”
                        // にしなければならないので、Exceptionでcatchする。
                        log.error("顧客単位出来終了エラー発生 :口座ID[" + l_accounts[i].getAccountId() + "]");
                        log.error(l_exp.getMessage(), l_exp);
                        l_strStatus = WEB3RunStatusDivDef.ERROR;
                    }
                }
            }
            catch (Exception l_exp)
            {
                // RuntimeExceptionがthrowされた場合も実行ステータス区分を”9:エラー”
                // にしなければならないので、Exceptionでcatchする。
                log.error(l_exp.getMessage(), l_exp);
                l_strStatus = WEB3RunStatusDivDef.ERROR;
            }

            // system_preferenceのshift.system.timestampを
            // オンライン実行結果テーブル.更新日付に設定するために
            // TIMESTAMP_TAGをリセット
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            try
            {
                onlineRunStatus.updateRunStatusDiv(l_strStatus);
            }
            catch (WEB3BaseException l_wbe)
            {
                log.error(l_wbe.getMessage(), l_wbe);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (get処理対象顧客一覧)<BR>
         * 有効注文を持つ顧客の一覧を作成し返す。<BR>
         * <BR>
         * １）　@市場ID一覧を作成する。<BR>
         * <BR>
         * 　@　@１－１）（部店市場別）取扱条件.get（部店市場別）取扱条件( )をコールして、<BR>
         * 　@　@　@　@　@取扱条件オブジェクトの配列を取得する。<BR>
         * <BR>
         * 　@　@　@　@　@＜get（部店市場別）取扱条件( )にセットする引数＞<BR>
         * 　@　@　@　@　@　@証券会社コード:　@プロパティの証券会社コード<BR>
         * <BR>
         * 　@　@１－２）取得した取扱条件オブジェクトの全要素について以下の処理を行う。<BR>
         * <BR>
         * 　@　@　@　@　@（部店市場別）取扱条件.get市場コード( )により市場コードを取得する。<BR>
         * 　@　@　@　@　@拡張金融オブジェクトマネージャ.get市場( )をコールし、<BR>
         * 　@　@　@　@　@取得した市場オブジェクトの市場IDをリストに追加する。<BR>
         * 　@　@　@　@　@※ただし、既に市場IDがリストに存在する場合は追加しない。<BR>
         * <BR>
         * 　@　@　@　@　@[get市場( )の引数設定]<BR>
         * 　@　@　@　@　@　@証券会社コード:　@プロパティの証券会社コード<BR>
         * 　@　@　@　@　@　@市場コード:　@（部店市場別）取扱条件.get市場コード( )<BR>
         * <BR>
         * ２）　@株・信用の有効注文の注文単位オブジェクトを全て取得する。<BR>
         * <BR>
         * 　@　@　@クエリプロセッサを使用し、以下の条件に合致する注文単位オブジェクトを全て取得する。<BR>
         * 　@　@　@（口座ID昇順指定）<BR>
         * <BR>
         * 　@　@　@----------------------------------------------------------<BR>
         * 　@　@　@＜抽出条件＞<BR>
         * <BR>
         * 　@　@　@　@　@　@部店ID in プロパティの証券会社コードに該当する証券会社.getBranches( )の戻り値のいずれか<BR>
         * 　@　@　@かつ　@銘柄タイプ == "株式"<BR>
         * 　@　@　@かつ　@注文種別 != （"株式ミニ株買注文"、"株式ミニ株売注文"）<BR>
         * 　@　@　@かつ　@注文有効状態 == "オープン"<BR>
         * 　@　@　@かつ　@プロパティのFrom口座ID ≦ 口座ID<BR>
         * 　@　@　@かつ　@口座ID ≦ プロパティのTo口座ID<BR>
         * 　@　@　@かつ　@発注日 ＜ 取引時間管理.get発注日( )(*1)<BR>
         * 　@　@　@かつ　@市場ID in １）で取得した市場ID一覧<BR>
         * <BR>
         * 　@　@　@※(*1)当日の場中に発注した注文のみを取得するための条件。<BR>
         * 　@　@　@※　@　@　@翌日以降も有効な出来るまで注文も取得する。（約定メール送信のため）<BR>
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
            //１－１）（部店市場別）取扱条件.get（部店市場別）取扱条件( )をコールして、
            //取扱条件オブジェクトの配列を取得する。
            WEB3GentradeBranchMarketDealtCond[] l_handingCondBranchMarkets =
                WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(this.institutionCode);

            //１－２）取得した取扱条件オブジェクトの全要素について以下の処理を行う。
            Long[] l_marketIds = null;
            int l_intMarketSize = 0;
            if (l_handingCondBranchMarkets != null && l_handingCondBranchMarkets.length > 0)
            {
                l_intMarketSize = l_handingCondBranchMarkets.length;
                List l_lisMarketIds = new ArrayList();
                for (int i = 0; i < l_intMarketSize; i++)
                {
                    //（部店市場別）取扱条件.get市場コード( )により市場コードを取得する。
                    String l_strMarkerCode = l_handingCondBranchMarkets[i].getMarketCode();

                    //拡張金融オブジェクトマネージャ.get市場( )をコールし
                    //[get市場( )の引数設定]
                    //証券会社コード:　@プロパティの証券会社コード
                    //市場コード:　@（部店市場別）取扱条件.get市場コード( )
                    WEB3GentradeFinObjectManager l_finObjectManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                    WEB3GentradeMarket l_market = null;
                    try
                    {
                        l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                            this.institutionCode,
                            l_strMarkerCode);
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

                    //ただし、既に市場IDがリストに存在する場合は追加しない。
                    if (!l_lisMarketIds.contains(new Long(l_market.getMarketId())))
                    {
                        l_lisMarketIds.add(new Long(l_market.getMarketId()));
                    }
                }

                l_marketIds = new Long[l_lisMarketIds.size()];
                l_lisMarketIds.toArray(l_marketIds);
            }

            WEB3GentradeMainAccount[] l_accounts = null;
        
            StringBuffer l_sbWhere = new StringBuffer();
            
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = null;
            try
            {
                l_institution = l_accountManager.getInstitution(institutionCode);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            Branch[] l_branchs = l_institution.getBranches();
            if (l_branchs != null && l_branchs.length > 0)
            {
                l_sbWhere.append("branch_id in (" + l_branchs[0].getBranchId());
                for (int i = 1;i < l_branchs.length;i++)
                {
                    l_sbWhere.append("," + l_branchs[i].getBranchId());
                }
                l_sbWhere.append(") and ");
            }
            l_sbWhere.append("product_type = ?");
            l_sbWhere.append(" and order_type not in (?,?)");
            l_sbWhere.append(" and order_open_status = ?");
            l_sbWhere.append(" and ? <= account_id");
            l_sbWhere.append(" and account_id <= ?");
            l_sbWhere.append(" and biz_date < ?");

            if (l_marketIds != null && l_marketIds.length > 0)
            {
                l_sbWhere.append(" and market_id in (" + l_marketIds[0]);
                for (int i = 1; i < l_marketIds.length; i++)
                {
                    l_sbWhere.append("," + l_marketIds[i]);
                }
                l_sbWhere.append(")");
            }

            String l_strBizDate =
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate());
            log.debug("発注日:[" + l_strBizDate + "]");
            Object[] l_objWhere =
            {
                ProductTypeEnum.EQUITY,
                OrderTypeEnum.MINI_STOCK_BUY,
                OrderTypeEnum.MINI_STOCK_SELL,
                OrderOpenStatusEnum.OPEN,
                new Long(rangeFrom),
                new Long(rangeTo),
                l_strBizDate
            };
        
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisRecord =
                    l_queryProcessor.doFindAllQuery(
                        EqtypeOrderUnitRow.TYPE,
                        l_sbWhere.toString(),
                        "account_id asc",
                        null,
                        l_objWhere);
                if (l_lisRecord != null && !l_lisRecord.isEmpty())
                {
                    long l_accountId = 0L;
                    int l_intSize = l_lisRecord.size();
                    List l_lisAccount = new ArrayList();
                    for (int i = 0;i < l_intSize;i++)
                    {
                        EqtypeOrderUnitRow l_orderUnitRow =
                            (EqtypeOrderUnitRow)l_lisRecord.get(i);
                        if (l_accountId != l_orderUnitRow.getAccountId())
                        {
                            l_accountId = l_orderUnitRow.getAccountId();
                            WEB3GentradeMainAccount l_account =
                                new WEB3GentradeMainAccount(l_accountId);
                            l_lisAccount.add(l_account);
                        }
                    }
                    l_accounts = new WEB3GentradeMainAccount[l_lisAccount.size()];
                    l_lisAccount.toArray(l_accounts);
                }
            }
            catch (DataException l_de)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
            }
            log.exiting(STR_METHOD_NAME);
            return l_accounts;
        }
        
        /**
         * (set取引カレンダコンテキスト)<BR>
         * 取引カレンダが利用するコンテキストを生成する。<BR>
         * <BR>
         * １）　@取引カレンダコンテキストに内容をセットする。<BR>
         * <BR>
         * 　@取引カレンダコンテキスト.証券会社コード = プロパティの同項目<BR>
         * 　@取引カレンダコンテキスト.部店コード = プロパティの証券会社コードに該当する<BR>
         * 　@　@　@証券会社オブジェクト.getBrahchs( )の戻り値の0番目の要素の部店オブジェクト.部店コード<BR>
         * 　@取引カレンダコンテキスト.市場コード = ”東京”（固定）<BR>
         * 　@取引時間コンテキスト.受付時間区分 = ”株式・信用”<BR>
         * 　@取引時間コンテキスト.銘柄コード = ”0：DEFAULT”<BR>
         * <BR>
         * 　@※注文受付商品、注文受付トランザクションは設定不要。<BR>
         * <BR>
         * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR>
         * 設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
         * <BR>
         * ２）　@受付日時、日付ロールをセットする。<BR>
         * 　@－取引時間管理.setTimestamp()をコールする。<BR>
         * <BR>
         * @@throws WEB3BaseException
         */
        protected void setTradingClendarContext() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "setTradingClendarContext()";
            log.entering(STR_METHOD_NAME);
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            
            // 証券会社コード
            l_context.setInstitutionCode(institutionCode);
            
            // 部店コード
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = null;
            try
            {
                l_institution = l_accountManager.getInstitution(institutionCode);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            l_context.setBranchCode(l_institution.getBranches()[0].getBranchCode());
            
            // 市場コード
            l_context.setMarketCode (WEB3MarketCodeDef.TOKYO);
            
            // 受付時間区分
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            
            // 銘柄コード
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            //受付日時、日付ロールをセットする
            WEB3GentradeTradingTimeManagement.setTimestamp();
            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (顧客単位出来終了TransactionCallback)<BR>
         * 顧客単位出来終了TransactionCallbackクラス。<BR>
         */
        public class AccTransactionCallback
            implements TransactionCallback
        {
            /**
             * (顧客)。<BR>
             * 顧客オブジェクト。<BR>
             */
            private WEB3GentradeMainAccount mainAccount;
            
            /**
             * (顧客単位出来終了TransactionCallback)<BR>
             * コンストラクタ。<BR>
             * @@param l_account - (顧客)<BR>
             * 顧客オブジェクト。<BR>
             */
            public AccTransactionCallback(WEB3GentradeMainAccount l_account)
            {
                mainAccount = l_account;
            }
            
            /**
             * (顧客単位出来終了実行)<BR>
             * 顧客単位の出来終了処理を行う。<BR>
             * <BR>
             * 処理のシーケンスは、<BR>
             * シーケンス図「（出来終了通知）顧客単位出来終了実行」を参照。<BR>
             * @@return Object
             * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
             * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
             * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
             */
            public Object process()
                throws DataNetworkException, DataQueryException, DataCallbackException
            {
                final String STR_METHOD_NAME = "process()";
                log.entering(STR_METHOD_NAME);
                
                try
                {
                    // lock口座()
                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                    WEB3GentradeAccountManager l_accountManager =
                        (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                    MainAccountRow l_accountRow = (MainAccountRow)mainAccount.getDataSourceObject();
                    l_accountManager.lockAccount(
                        l_accountRow.getInstitutionCode(),
                        l_accountRow.getBranchCode(),
                        l_accountRow.getAccountCode());

                    // is信用口座開設()
                    boolean l_isMarginAccountEstablished =
                        mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                    
                    // getSubAccount()
                    WEB3GentradeSubAccount l_subAccount;
                    if (l_isMarginAccountEstablished)
                    {
                        l_subAccount =
                            (WEB3GentradeSubAccount)mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    }
                    else
                    {
                        l_subAccount =
                            (WEB3GentradeSubAccount)mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }

                    //(部店市場別）取扱条件.get取扱可能市場(部店,  銘柄タイプ)の戻り値配列より、
                    //市場ID一覧を作成する。
                    //[get取扱可能市場(部店,  銘柄タイプ)の引数設定]
                    //　@部店：　@顧客.getBranch( )の戻り値
                    //　@銘柄タイプ：　@"株式"
                    String[] l_strHandlingPossibleMarkets =
                        WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                            (WEB3GentradeBranch)this.mainAccount.getBranch(),
                            ProductTypeEnum.EQUITY);

                    Long[] l_marketIds = null;
                    int l_intMarketSize = 0;
                    if (l_strHandlingPossibleMarkets != null && l_strHandlingPossibleMarkets.length > 0)
                    {
                        l_intMarketSize = l_strHandlingPossibleMarkets.length;
                        List l_lisMarketIds = new ArrayList();
                        for (int i = 0; i < l_intMarketSize; i++)
                        {
                            //get市場( )
                            WEB3GentradeFinObjectManager l_finObjectManager =
                                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                            WEB3GentradeMarket l_market = null;
                            try
                            {
                                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                                    l_accountRow.getInstitutionCode(),
                                    l_strHandlingPossibleMarkets[i]);
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

                            //ただし、既に市場IDがリストに存在する場合は追加しない。
                            if (!l_lisMarketIds.contains(new Long(l_market.getMarketId())))
                            {
                                l_lisMarketIds.add(new Long(l_market.getMarketId()));
                            }
                        }

                        l_marketIds = new Long[l_lisMarketIds.size()];
                        l_lisMarketIds.toArray(l_marketIds);
                    }

                    // get注文単位一覧()
                    StringBuffer l_sbWheres = new StringBuffer();
                    l_sbWheres.append("order_type not in (?,?)");
                    l_sbWheres.append(" and order_open_status = ?");
                    l_sbWheres.append(" and biz_date < ?");

                    if (l_marketIds != null && l_marketIds.length > 0)
                    {
                        l_sbWheres.append(" and market_id in (" + l_marketIds[0]);
                        for (int i = 1; i < l_marketIds.length; i++)
                        {
                            l_sbWheres.append("," + l_marketIds[i]);
                        }
                        l_sbWheres.append(")");
                    }

                    String l_strBizDate =
                        GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                            WEB3GentradeTradingTimeManagement.getOrderBizDate());
                    Object[] l_objWhere =
                    {
                        OrderTypeEnum.MINI_STOCK_BUY,
                        OrderTypeEnum.MINI_STOCK_SELL,
                        OrderOpenStatusEnum.OPEN,
                        l_strBizDate
                    };
                    TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                    WEB3EquityOrderManager l_orderManager =
                        (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                    List l_lisOrderUnits = l_orderManager.getOrderUnits(
                        l_subAccount,
                        ProductTypeEnum.EQUITY,
                        l_sbWheres.toString(),
                        l_objWhere,
                        null);
                    
                    // 取得した注文単位オブジェクト数分のLoop処理
                    int l_intSize = 0;
                    if (l_lisOrderUnits != null)
                    {
                        l_intSize = l_lisOrderUnits.size();
                    }
                    WEB3EquityExecutedMailSenderService l_execMailSenderService =
                        (WEB3EquityExecutedMailSenderService)Services.getService(WEB3EquityExecutedMailSenderService.class);
                    for (int i = 0; i < l_intSize; i++)
                    {
                        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnits.get(i);
                        log.debug("注文単位ID[" + i + "]:[" + l_orderUnit.getOrderUnitId() + "]");
                        
                        // 未約定、または一部約定の場合のみ実行
                        if (l_orderUnit.isUnexecuted() || l_orderUnit.isPartiallyExecuted())
                        {
                            l_execMailSenderService.sendMailProcess(l_orderUnit, null, true);
                        }
                        
                        if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
                        {
                            WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                                (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                                    WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                            l_updateService.expireAllOrderUnit(l_orderUnit.getOrderId());
                        }
                        
                        // 出来終了対象ではない場合は、次の注文単位を処理する（continue）
                        if (!isOrderExecEnd(l_orderUnit))
                        {
                            log.debug("出来終了対象ではない。");
                            continue;
                        }
                        
                        // 更新イベントインタセプタ()
                        WEB3MarginUpdateEventInterceptor l_interceptor =
                            new WEB3MarginUpdateEventInterceptor();
                        
                        // setThreadLocalPersistenceEventInterceptor()
                        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
                        
                        // expireOrder()
                        ProcessingResult l_result = l_orderManager.expireOrder(l_orderUnit.getOrderId());
                        if (l_result.isFailedResult())
                        {
                            throw new WEB3BaseException(
                                l_result.getErrorInfo(),
                                this.getClass().getName() + "." + STR_METHOD_NAME);
                        }
                    }
                    
                    // 余力再計算()
                    WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                        (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
                    l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                }
                catch (NotFoundException l_nfe)
                {
                    WEB3SystemLayerException l_wse =
                        new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                    throw new DataCallbackException(l_wse.getMessage(), l_wse);
                }
                catch (WEB3BaseException l_wbe)
                {
                    throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
                }
                
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            /**
             * (is出来終了注文単位)<BR>
             * 指定の注文単位オブジェクトが出来終了対象かどうかを判定する。<BR> 
             * （当メソッドは市場閉局後にコールされる。） <BR>
             * <BR>
             * 引数の注文単位.注文失効日 < 取引時間管理.get発注日( )(*1) <BR>
             * または、拡張株式注文マネージャ.is未発注遅延注文(引数.注文単位) == true<BR> 
             * または、引数の注文単位.リクエストタイプ == "失効" 　@の場合は、<BR> 
             * 出来終了対象であると判定しtrueを返す。 <BR>
             * 上記以外の場合は、falseを返す。 <BR>
             * <BR>
             * (*1)注文有効期限が当日までの注文<BR>
             * <BR>
             * @@param l_orderUnit - (注文単位)<BR>
             * 注文単位オブジェクト。<BR>
             * @@return 出来終了対象の場合はtrueを、そうでない場合はfalseを返す。
             */
            protected boolean isOrderExecEnd(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
            {
                final String STR_METHOD_NAME = "isOrderExecEnd(EqTypeOrderUnit)";
                log.entering(STR_METHOD_NAME);
                
                Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                EqtypeOrderUnitRow l_row = 
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject(); 
                
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                
                log.exiting(STR_METHOD_NAME);
                if (l_datBizDate.compareTo(l_orderUnit.getExpirationTimestamp()) > 0 ||
                    l_orderManager.isNotOrderedDelayOrder(l_orderUnit) ||
                    WEB3RequestTypeDef.INVALIDATE.equals(l_row.getRequestType()))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }
}
@
