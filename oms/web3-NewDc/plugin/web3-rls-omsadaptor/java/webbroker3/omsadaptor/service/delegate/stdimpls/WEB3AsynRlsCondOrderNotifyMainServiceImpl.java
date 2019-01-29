head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3AsynRlsCondOrderNotifyMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@非同期処理ルールエンジン通知メインサービス実装(WEB3AsynRlsCondOrderNotifyMainServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/01 劉(FLJ) 新規作成
　@　@　@　@　@　@　@　@　@　@　@ 2008/08/31　@劉(FLJ)　@修正　@発注異常対応処理として、自動リトライ処理を行う
 */

package webbroker3.omsadaptor.service.delegate.stdimpls;

import java.util.*;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;
import webbroker3.common.define.*;
import webbroker3.gentrade.*;
import webbroker3.gentrade.data.*;
import webbroker3.omsadaptor.message.*;
import webbroker3.rlsgateway.data.*;
import webbroker3.rlsgateway.define.*;
import webbroker3.util.*;

/**
 * （非同期対応ルールエンジン通知メインサービス実装クラス）。
 * @@author  : 劉（FLJ）
 * @@version : 1.0
 */
public class WEB3AsynRlsCondOrderNotifyMainServiceImpl
    implements Runnable
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynRlsCondOrderNotifyMainServiceImpl.class);

    /**
     * ルールエンジン通知メインリクエスト
     */
    private WEB3RlsCondOrderNotifyMainRequest request;

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3AsynRlsCondOrderNotifyMainServiceImpl(
        WEB3RlsCondOrderNotifyMainRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynRlsCondOrderNotifyMainServiceImpl：run()";
        log.entering(STR_METHOD_NAME);

        try
        {

            // 1.1. WEB3RlsCondOrderNotifyMainRequest.validate()
            request.validate();

            // 1.2. getDefaultProcessor()
            try
            {
                Processors.getDefaultProcessor();
            }
            catch (Exception l_e)
            {
                log.error(l_e.getMessage(), l_e);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_e.getMessage(), l_e);
            }

            // 1.3. ルールエンジン通知メインTransactionCallback()
            WEB3RlsCondOrderNotifyMainTransactionCallback l_transactionCallBack =
                new WEB3RlsCondOrderNotifyMainTransactionCallback();

            // 1.4.
            //seFrom口座ＩＤ()
            l_transactionCallBack.setFromAccountId(this.request.fromAccountId.longValue());
            //seＴｏ口座ＩＤ()
            l_transactionCallBack.setToAccountId(this.request.toAccountId.longValue());
            //setThreadNo()
            l_transactionCallBack.setThreadNo(request.threadNo);
            //setRlsNotifyOrderType()
            l_transactionCallBack.setRlsNotifyOrderType(request.rlsNotifyOrderType);

            // 1.5. doTransaction()
            try
            {
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                l_queryProcesser.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    l_transactionCallBack);
            }
            catch (DataQueryException l_dqe)
            {
                log.error(l_dqe.getMessage(), l_dqe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(), l_dqe);
            }

        }
        catch (Throwable e)
        {

            log.error(e.getMessage(), e);

        }
        //スレッド開放
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(request.threadNo.
                longValue());
        }
        catch (Throwable t)
        {
            log.error(t.getMessage(), t);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (ルールエンジン通知メインTransactionCallback)<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
     */
    public class WEB3RlsCondOrderNotifyMainTransactionCallback
        implements TransactionCallback
    {
        /**
         * Thread No<BR>
         */
        private Long threadNo;

        /**
         * (From口座ID)
         */
        protected long fromAccountId;

        /**
         * (To口座ID)
         */
        protected long toAccountId;

        /**
         * 処理タイプ<BR>
         */
        protected int rlsNotifyOrderTypes[];

        /**
         * コンストラクタ。<BR>
         */
        public WEB3RlsCondOrderNotifyMainTransactionCallback()
        {
        }

        /**
         * (set引数のThreadNo)<BR>
         * <BR>
         * 引数のThreadNoをプロパティにセットする。<BR>
         * @@params Long - ThreadNo<BR>
         */
        public void setThreadNo(Long l_threadNo)
        {
            this.threadNo = l_threadNo;
        }

        /**
         * (setFrom口座ID)
         * 引数.From口座IDをthis.From口座IDにセットする。
         * @@param l_lngFromAccountId - (From口座ID)
         */
        public void setFromAccountId(long l_lngFromAccountId)
        {
            this.fromAccountId = l_lngFromAccountId;
        }

        /**
         * (setTo口座ID)
         * 引数.To口座IDをthis.To口座IDにセットする。
         * @@param l_lngToAccountId - (To口座ID)
         */
        public void setToAccountId(long l_lngToAccountId)
        {
            this.toAccountId = l_lngToAccountId;
        }

        /**
         * (set処理タイプ)
         * 引数.処理タイプをthis.処理タイプにセットする。
         * @@param l_intTypes - (処理タイプ)
         */
        public void setRlsNotifyOrderType(int l_intTypes[])
        {
            this.rlsNotifyOrderTypes = l_intTypes;
        }

        /**
         * 約定処理Threadをロックする<BR>
         * <BR>
         * Thread番号<BR>
         * @@return boolean<BR>
         */
        private boolean lockThread(long l_lngThreadNo)
        {
            final String STR_METHOD_NAME = "lockThread(long)";
            log.entering(STR_METHOD_NAME);
            boolean l_blnResult = false;
            try
            {
                String l_strWhere =
                    "thread_no = ? ";
                String l_strCondition = "for update nowait";

                Object l_bindVars[] =
                    {
                    new Long(l_lngThreadNo)
                };
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                List l_lisRows = l_queryProcesser.doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere, l_strCondition, l_bindVars);
                if (l_lisRows.size() > 0)
                {
                    l_blnResult = true;
                }
                else
                {
                    l_blnResult = false;
                }

            }
            catch (Exception e)
            {
                log.error(e.getMessage(), e);
                l_blnResult = false;
            }

            log.exiting(STR_METHOD_NAME);
            return l_blnResult;
        }

        /**
         * (process)<BR>
         * <BR>
         * トランザクション処理を実施する。<BR>
         * @@return Object
         * @@throws DataNetworkException, DataQueryException, DataCallbackException
         */
        public Object process() throws DataNetworkException, DataQueryException,
            DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            // 1.１. 処理スレッド占有
            if (lockThread(this.threadNo.longValue()) == false)
            {
                log.error("処理スレッド占有ロック取得できないため、処理中止!");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            // 1.2. ルールエンジン通知キューテーブル読み込み
            StringBuffer l_sbWhere = new StringBuffer();
            String l_strWhere = "account_id >= ? and account_id <= ? and status = ?";
            l_sbWhere.append(l_strWhere);
            int l_intLength = 0;
            if (rlsNotifyOrderTypes != null)
            {
                l_intLength = rlsNotifyOrderTypes.length;
            }
            if (l_intLength > 0)
            {
                l_sbWhere.append(" and (");
                for (int i = 0; i < l_intLength; i++)
                {
                    if (i > 0)
                    {
                        l_sbWhere.append(" or ");
                    }
                    l_sbWhere.append("order_type = ?");
                }
                l_sbWhere.append(")");
            }

            Object[] l_bindVars = new Object[l_intLength + 3];
            l_bindVars[0] = new Long(this.fromAccountId);
            l_bindVars[1] = new Long(this.toAccountId);
            l_bindVars[2] = WEB3RlsNotifyStatusDef.NOT_DEAL;

            log.debug("WHERE=" + l_sbWhere.toString());
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);

            for (int i = 0; i < l_intLength; i++)
            {
                l_bindVars[i + 3] = new Integer(rlsNotifyOrderTypes[i]);
                log.debug("BindVars[" + (i + 3) + "]=" + l_bindVars[i + 3]);
            }

            String l_strOrderBy =
                " account_id,order_type,parent_order_id,serial_no_in_parent asc ";

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
                RlsConOrderHitNotifyParams.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_bindVars);

            // 1.3. 取得したキューテーブルのレコード数分、Loop
            RlsConOrderHitNotifyParams l_params = null;
            int l_intNum = l_lisSearchResult.size();

            if (l_intNum == 0)
            {
                log.debug("ルールエンジン通知データ存在しません.");
            }

            for (int i = 0; i < l_intNum; i++)
            {
                try
                {
                    log.debug("ルールエンジン通知メイン：Loop処理 i = " + i);

                    l_params = (RlsConOrderHitNotifyParams)
                        l_lisSearchResult.get(i);
                    // 一件処理
                    // 一件TransactionCallback生成
                    WEB3AsynRlsCondOrderNotifyTransactionCallback
                        l_transactionCallback =
                        new WEB3AsynRlsCondOrderNotifyTransactionCallback(l_params);
                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
                catch (Exception l_exp)
                {
                    log.error(l_exp.getMessage(), l_exp);
                    //l_params.setStatus(WEB3StatusDef.PROGRAM_ERROR);
                    //2008/08/31　@失敗後処理として、自動リトライ処理へ変更
                    l_params.setStatus(WEB3StatusDef.NOT_DEAL);
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
