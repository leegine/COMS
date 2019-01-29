head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AsynTPReCalcNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算通知非同期サービスImpl(WEB3AsynTPReCalcNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyRow;
import webbroker3.tradingpower.define.WEB3TPStatusDef;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyRequest;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

/**
 * (余力計算通知サービスImpl)
 */
public class WEB3AsynTPReCalcNotifyServiceImpl implements Runnable
{
    /**
     * （ログ出力ユーティリティ)。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynTPReCalcNotifyServiceImpl.class);

    /**
     * 余力計算通知メインリクエスト
     */
    private WEB3TPReCalcNotifyRequest request;

    /**
     * (コンストラクタ)
     */
    public WEB3AsynTPReCalcNotifyServiceImpl(WEB3TPReCalcNotifyRequest l_request)
    {
        this.request = l_request;
    }

    /**
     * (run)<BR>
     * <BR>
     * シーケンス図「（余力再計算通知非同期サービスImpl）run」参照。<BR>
     *  <BR>
     */
    public void run()
    {
        final String STR_METHOD_NAME = "WEB3AsynTPReCalcNotifyServiceImpl：run()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //getDefaultProcessor()
            try
            {
                Processors.getDefaultProcessor();
            }
            catch (DataFindException l_dfe)
            {
                log.error(l_dfe.getMessage(), l_dfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dfe.getMessage(),
                    l_dfe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }

            //余力計算通知TransactionCallback()
            WEB3TPReCalcNotifyTransactionCallback l_transactionCallBack =
                new WEB3TPReCalcNotifyTransactionCallback();

            //seFrom口座ＩＤ()
            l_transactionCallBack.setFromAccountId(this.request.fromAccountId.longValue());
            //seＴｏ口座ＩＤ()
            l_transactionCallBack.setToAccountId(this.request.toAccountId.longValue());

            // 1.5. doTransaction()
            try
            {
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallBack);
            }
            catch (DataFindException l_dfe)
            {
                log.error(l_dfe.getMessage(), l_dfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dfe.getMessage(),
                    l_dfe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            catch (DataCallbackException l_dce)
            {
                log.error(l_dce.getMessage(), l_dce);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dce.getMessage(),
                    l_dce);
            }
            catch (DataQueryException l_dqe)
            {
                log.error(l_dqe.getMessage(), l_dqe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(),
                    l_dqe);
            }

        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        //スレッド開放
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(this.request.threadNo.longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (余力計算通知トランザクションコールバック)
     */
    public class WEB3TPReCalcNotifyTransactionCallback implements TransactionCallback
    {
        /**
         * (From口座ID)
         */
        protected long fromAccountId;

        /**
         * (To口座ID)
         */
        protected long toAccountId;

        /**
         * (コンストラクタ)
         * @@roseuid 4235477D0375
         */
        public WEB3TPReCalcNotifyTransactionCallback()
        {

        }

        /**
         * (process)
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 41F5CBF90150
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "WEB3TPReCalcNotifyTransactionCallback.process()";
            log.entering(STR_METHOD_NAME);

            /*
             * 余力再計算キューテーブルを検索
             */
            //検索条件：
            //　@口座ID >= From口座ID
            //　@口座ID <= To口座ID
            //　@処理区分 >= 0：未処理
            String l_strWhere = "account_id >= ? and account_id <= ? and status = ?";
            Object[] l_bindVars = new Object[3];
            l_bindVars[0] = new Long(this.fromAccountId);
            l_bindVars[1] = new Long(this.toAccountId);
            l_bindVars[2] = WEB3TPStatusDef.NOT_DEAL;

            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);

            //ソート条件:発生区分、作成日付
            String l_strOrderBy = "occurred_div, created_timestamp";
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(
                    TpCalcResultExecNotifyRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_bindVars);

            /*
             * 余力再計算の最大処理件数を取得
             */
            String l_strMaxCount =
                GtlUtils.getTradingSystem().getPreference("system.tradingpower.process.count");
            int l_intMaxCount = Integer.MAX_VALUE;
            if(l_strMaxCount != null)
            {
                l_intMaxCount = Integer.parseInt(l_strMaxCount);
            }

            /*
             * 余力計算通知一件サービスオブジェクトを取得
             */
            WEB3TPReCalcNotifyUnitService l_service = null;

            try
            {
                l_service =
                    (WEB3TPReCalcNotifyUnitService)Services.getService(
                        WEB3TPReCalcNotifyUnitService.class);
            }
            catch (Exception e)
            {
                log.error(e.getMessage(), e);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    e.getMessage(),
                    e);
            }

            /*
             * 処理件数回（MIN(最大処理件数, 検索行数)）LOOP処理
             */
            int l_intCount = Math.min(l_intMaxCount, l_lisRows.size());
            for (int i = 0; i < l_intCount; i++)
            {
                TpCalcResultExecNotifyRow l_row = (TpCalcResultExecNotifyRow)l_lisRows.get(i);
                TpCalcResultExecNotifyParams l_params = new TpCalcResultExecNotifyParams(l_row);

                /*
                 * 余力計算通知一件サービスオブジェクト.notifyReCalc（）をコール
                 */
                try
                {
                    //補助口座を取得
                    WEB3GentradeSubAccount l_subAccount = this.getSubAccount(l_params.getAccountId());
                    //notifyReCalc（）をコール
                    l_service.notifyReCalc(l_subAccount, l_params);
                }
                catch (WEB3BaseRuntimeException bre)
                {
                    /*
                     * システムタイプスタンプを取得
                     */
                    TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
                    Timestamp l_systemTimeStamp = tradingSystem.getSystemTimestamp();

                    log.error(bre.getMessage(), bre);
                    l_params.setStatus(WEB3TPStatusDef.DATA_ERROR);
                    l_params.setLastUpdatedTimestamp(l_systemTimeStamp);
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_params);
                }
                catch (Exception e)
                {
                    /*
                     * システムタイプスタンプを取得
                     */
                    TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
                    Timestamp l_systemTimeStamp = tradingSystem.getSystemTimestamp();

                    log.error(e.getMessage(), e);
                    l_params.setStatus(WEB3TPStatusDef.PROGRAM_ERROR);
                    l_params.setLastUpdatedTimestamp(l_systemTimeStamp);
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_params);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (getFrom口座ID)
         * this.From口座IDを返却する。
         * @@return long
         * @@roseuid 41F5CED5022B
         */
        public long getFromAccountId()
        {
            return this.fromAccountId;
        }

        /**
         * (getTo口座ID)
         * this.To口座IDを返却する。
         * @@return long
         * @@roseuid 4230F63A0259
         */
        public long getToAccountId()
        {
            return this.toAccountId;
        }

        /**
         * (setFrom口座ID)
         * 引数.From口座IDをthis.From口座IDにセットする。
         * @@param l_lngFromAccountId - (From口座ID)
         * @@roseuid 41F5CE6F0037
         */
        public void setFromAccountId(long l_lngFromAccountId)
        {
            this.fromAccountId = l_lngFromAccountId;
        }

        /**
         * (setTo口座ID)
         * 引数.To口座IDをthis.To口座IDにセットする。
         * @@param l_lngToAccountId - (To口座ID)
         * @@roseuid 4230F63A0278
         */
        public void setToAccountId(long l_lngToAccountId)
        {
            this.toAccountId = l_lngToAccountId;
        }

        /**
         * （get補助口座）
         * 
         * １）顧客オブジェクトを生成する。 
         * 　@[引数]
         * 　@　@口座ID：引数.口座ID
         * 
         * ２）顧客.is信用口座開設()の判定
         * 　@[引数]
         *   　@弁済区分：”指定なし”
         * 
         * 　@○未開設の場合(顧客.is信用口座開設()==false)
         * 　@　@　@補助口座<株式取引口座(預り金)>オブジェクトを取得し、リターンする。
         * 
         * 　@○開設の場合(顧客.is信用口座開設()==true)
         * 　@　@　@補助口座<株式信用取引口座(保証金)>オブジェクトを取得し、リターンする。
         * @@param l_lngAccountId - (口座ID)
         * @@return webbroker3.gentrade.WEB3GentradeSubAccount
         * @@roseuid 41F5CCEF0075
         */
        protected WEB3GentradeSubAccount getSubAccount(long l_lngAccountId)
        {
            final String STR_METHOD_NAME =
                "WEB3TPReCalcNotifyTransactionCallback.getSubAccount(long)";
            log.entering(STR_METHOD_NAME);

            try
            {
                //顧客
                WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_lngAccountId);
                //補助口座
                SubAccount l_subAccount = null;

                //信用口座開設の判定
                if (!l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
                {
                    //現物顧客　@銘柄タイプ：株式取引口座(預り金)
                    l_subAccount =
                        l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                }
                else
                {
                    //信用顧客　@銘柄タイプ：株式信用取引口座(保証金)>
                    l_subAccount =
                        l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                }

                //補助口座を返却
                log.exiting(STR_METHOD_NAME);
                return new WEB3GentradeSubAccount(
                    (SubAccountRow)l_subAccount.getDataSourceObject());

            }
            catch (NotFoundException nfe)
            {
                log.error(nfe.getMessage(), nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
            catch (DataQueryException dqe)
            {
                log.error(dqe.getMessage(), dqe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    dqe.getMessage(),
                    dqe);
            }
            catch (DataNetworkException dne)
            {
                log.error(dne.getMessage(), dne);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    dne.getMessage(),
                    dne);
            }
        }
    }
}
@
