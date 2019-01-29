head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3AsynRichPushMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@非同期処理リッチクライアントプッシュメインサービス実装(WEB3AsynRichPushMainServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.sql.*;
import java.util.*;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;
import webbroker3.gentrade.*;
import webbroker3.gentrade.data.*;
import webbroker3.rcp.*;
import webbroker3.rcp.data.*;
import webbroker3.rcp.define.*;
import webbroker3.rcp.message.*;
import webbroker3.rcp.service.delegate.*;
import webbroker3.system.tune.affinity.*;
import webbroker3.util.*;

/**
 * 非同期対応リッチクライアントプッシュメインサービス実装クラス
 * @@author  : 劉（FLJ）
 * @@version : 1.0
 */
public class WEB3AsynRichPushMainServiceImpl
    implements Runnable
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynRichPushMainServiceImpl.class);

    /**
     * リッチクライアントプッシュメインリクエスト
     */
    private WEB3RichPushMainRequest request;

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3AsynRichPushMainServiceImpl(
        WEB3RichPushMainRequest
        l_request)
    {
        this.request = l_request;
    }

    /**
     * 実行
     */
    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynRichPushMainServiceImpl：run()";
        log.entering(STR_METHOD_NAME);

        WEB3DescendRacCtxService l_sv = (WEB3DescendRacCtxService) Services.getService(
            WEB3DescendRacCtxService.class);
        l_sv.setAccountIdCtx(this.request.fromAccountId.longValue());

        try
        {
            // 1.1. WEB3RichPushMainRequest.validate()
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

            // 1.3. リッチクライアントプッシュメインTransactionCallback()
            WEB3RichPushMainTransactionCallback l_transactionCallBack =
                new WEB3RichPushMainTransactionCallback();

            // 1.4.
            //seFrom口座ＩＤ()
            l_transactionCallBack.setFromAccountId(this.request.fromAccountId.longValue());
            //seTo口座ＩＤ()
            l_transactionCallBack.setToAccountId(this.request.toAccountId.longValue());
            //setThreadNo()
            l_transactionCallBack.setThreadNo(request.threadNo);
            //setデータタイプ配列()
            l_transactionCallBack.setType(request.type);

            //初期化コンテクスト
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3RichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME,
                null);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3RichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME,
                new WEB3RichPushObjectContext());

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
            WEB3RichPushObjectContext l_context = (WEB3RichPushObjectContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3RichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME
                );
            try
            {
                this.saveRichPushHistory(l_context.getPushObjects(),
                                         WEB3RichPushDataStatusDef.PROGRAM_ERROR);
            }
            catch (Throwable t)
            {
                log.error(t.getMessage(), t);
            }

            //クリアコンテクスト
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3RichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME,
                null);
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
        l_sv.clearAccountIdCtx();
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (リッチクライアントプッシュメインTransactionCallback)<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
     */
    public class WEB3RichPushMainTransactionCallback
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
         * データタイプ配列<BR>
         */
        protected String[] type;

        /**
         * コンストラクタ。<BR>
         */
        public WEB3RichPushMainTransactionCallback()
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
         * (setデータタイプ配列)
         * 引数.データタイプ配列をthis.データタイプ配列にセットする。
         * @@param l_strType - (データタイプ配列)
         */
        public void setType(String[] l_strType)
        {
            if (l_strType != null)
            {
                this.type = new String[l_strType.length];
                for (int i = 0; i < l_strType.length; i++)
                {
                    this.type[i] = l_strType[i];
                }
            }
        }

        /**
         * Threadをロックする<BR>
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

            // 1.1. 処理スレッド占有
            if (lockThread(this.threadNo.longValue()) == false)
            {
                log.error("処理スレッド占有ロック取得できないため、処理中止!");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            // 1.2　@リッチクライアントへデータプッシュ
            if (this.type == null)
            {
                // データタイプが指定されていなければ全機@能プッシュ
                // 1.2.1　@株式注文受付データデータプッシュ
                WEB3RichPushEquityMarginOrderAcceptUnitService l_eqOrderAcceptService =
                    (WEB3RichPushEquityMarginOrderAcceptUnitService) Services.getService(
                    WEB3RichPushEquityMarginOrderAcceptUnitService.class);
                l_eqOrderAcceptService.push(this.fromAccountId, this.toAccountId);

                // 1.2.2　@株式現引現渡注文受付データデータプッシュ
                WEB3RichPushSwapOrderAcceptUnitService l_eqSwapOrderAcceptService =
                    (WEB3RichPushSwapOrderAcceptUnitService) Services.getService(
                    WEB3RichPushSwapOrderAcceptUnitService.class);
                l_eqSwapOrderAcceptService.push(this.fromAccountId, this.toAccountId);

                // 1.2.3　@株式訂正取消通知受付データデータプッシュ
                WEB3RichPushEquityMarginChangeCancelUnitService l_eqChangeCancelService =
                    (WEB3RichPushEquityMarginChangeCancelUnitService) Services.getService(
                    WEB3RichPushEquityMarginChangeCancelUnitService.class);
                l_eqChangeCancelService.push(this.fromAccountId, this.toAccountId);

                // 1.2.4　@株式出来通知受付データデータプッシュ
                WEB3RichPushEquityMarginContUnitService l_eqContService =
                    (WEB3RichPushEquityMarginContUnitService) Services.getService(
                    WEB3RichPushEquityMarginContUnitService.class);
                l_eqContService.push(this.fromAccountId, this.toAccountId);

                // 1.2.5　@株式失効通知受付データデータプッシュ
                WEB3RichPushEquityMarginLapseUnitService l_eqLapseService =
                    (WEB3RichPushEquityMarginLapseUnitService) Services.getService(
                    WEB3RichPushEquityMarginLapseUnitService.class);
                l_eqLapseService.push(this.fromAccountId, this.toAccountId);

                // 1.2.6　@先物OP注文受付通知データデータプッシュ
                WEB3RichPushFuOpOrderAcceptUnitService l_fuOpOrderAcceptService =
                    (WEB3RichPushFuOpOrderAcceptUnitService) Services.getService(
                    WEB3RichPushFuOpOrderAcceptUnitService.class);
                l_fuOpOrderAcceptService.push(this.fromAccountId, this.toAccountId);

                // 1.2.7　@先物OP訂正取消通知データデータプッシュ
                WEB3RichPushFuOpChangeCancelUnitService l_fuOpChangeCancelService =
                    (WEB3RichPushFuOpChangeCancelUnitService) Services.getService(
                    WEB3RichPushFuOpChangeCancelUnitService.class);
                l_fuOpChangeCancelService.push(this.fromAccountId, this.toAccountId);

                // 1.2.8　@先物OP出来通知データデータプッシュ
                WEB3RichPushFuOpContUnitService l_fuOpContService =
                    (WEB3RichPushFuOpContUnitService) Services.getService(
                    WEB3RichPushFuOpContUnitService.class);
                l_fuOpContService.push(this.fromAccountId, this.toAccountId);

                // 1.2.9　@先物OP失効通知データデータプッシュ
                WEB3RichPushFuOpLapseUnitService l_fuOpLapseService =
                    (WEB3RichPushFuOpLapseUnitService) Services.getService(
                    WEB3RichPushFuOpLapseUnitService.class);
                l_fuOpLapseService.push(this.fromAccountId, this.toAccountId);
            }
            else
            {
                // データタイプの指定がある場合、指定された機@能のみプッシュ
                for (int i = 0; i < this.type.length; i++)
                {
                    if (WEB3RichPushDataTypeDef.EQUITY_ORDER_ACCEPT.equals(this.type[i]))
                    {
                        // 1.2.1　@株式注文受付データデータプッシュ
                        WEB3RichPushEquityMarginOrderAcceptUnitService
                            l_eqOrderAcceptService =
                            (WEB3RichPushEquityMarginOrderAcceptUnitService) Services.
                            getService(
                            WEB3RichPushEquityMarginOrderAcceptUnitService.class);
                        l_eqOrderAcceptService.push(this.fromAccountId, this.toAccountId);
                    }
                    else if (WEB3RichPushDataTypeDef.SWAP_ORDER_ACCEPT.equals(this.type[i]))
                    {
                        // 1.2.2　@株式現引現渡注文受付データデータプッシュ
                        WEB3RichPushSwapOrderAcceptUnitService l_eqSwapOrderAcceptService =
                            (WEB3RichPushSwapOrderAcceptUnitService) Services.getService(
                            WEB3RichPushSwapOrderAcceptUnitService.class);
                        l_eqSwapOrderAcceptService.push(this.fromAccountId,
                            this.toAccountId);
                    }
                    else if (WEB3RichPushDataTypeDef.EQTYPE_CHANGE_CANCEL.equals(this.
                        type[i]))
                    {
                        // 1.2.3　@株式訂正取消通知受付データデータプッシュ
                        WEB3RichPushEquityMarginChangeCancelUnitService
                            l_eqChangeCancelService =
                            (WEB3RichPushEquityMarginChangeCancelUnitService) Services.
                            getService(
                            WEB3RichPushEquityMarginChangeCancelUnitService.class);
                        l_eqChangeCancelService.push(this.fromAccountId, this.toAccountId);
                    }
                    else if (WEB3RichPushDataTypeDef.EQTYPE_CONT.equals(this.type[i]))
                    {
                        // 1.2.4　@株式出来通知受付データデータプッシュ
                        WEB3RichPushEquityMarginContUnitService l_eqContService =
                            (WEB3RichPushEquityMarginContUnitService) Services.getService(
                            WEB3RichPushEquityMarginContUnitService.class);
                        l_eqContService.push(this.fromAccountId, this.toAccountId);
                    }
                    else if (WEB3RichPushDataTypeDef.EQTYPE_LAPSE.equals(this.type[i]))
                    {
                        // 1.2.5　@株式失効通知受付データデータプッシュ
                        WEB3RichPushEquityMarginLapseUnitService l_eqLapseService =
                            (WEB3RichPushEquityMarginLapseUnitService) Services.
                            getService(
                            WEB3RichPushEquityMarginLapseUnitService.class);
                        l_eqLapseService.push(this.fromAccountId, this.toAccountId);
                    }
                    else if (WEB3RichPushDataTypeDef.IFO_ORDER_ACCEPT.equals(this.type[i]))
                    {
                        // 1.2.6　@先物OP注文受付通知データデータプッシュ
                        WEB3RichPushFuOpOrderAcceptUnitService l_fuOpOrderAcceptService =
                            (WEB3RichPushFuOpOrderAcceptUnitService) Services.getService(
                            WEB3RichPushFuOpOrderAcceptUnitService.class);
                        l_fuOpOrderAcceptService.push(this.fromAccountId,
                            this.toAccountId);
                    }
                    else if (WEB3RichPushDataTypeDef.IFO_CHANGE_CANCEL.equals(this.type[i]))
                    {
                        // 1.2.7　@先物OP訂正取消通知データデータプッシュ
                        WEB3RichPushFuOpChangeCancelUnitService l_fuOpChangeCancelService =
                            (WEB3RichPushFuOpChangeCancelUnitService) Services.getService(
                            WEB3RichPushFuOpChangeCancelUnitService.class);
                        l_fuOpChangeCancelService.push(this.fromAccountId,
                            this.toAccountId);
                    }
                    else if (WEB3RichPushDataTypeDef.IFO_CONT.equals(this.type[i]))
                    {
                        // 1.2.8　@先物OP出来通知データデータプッシュ
                        WEB3RichPushFuOpContUnitService l_fuOpContService =
                            (WEB3RichPushFuOpContUnitService) Services.getService(
                            WEB3RichPushFuOpContUnitService.class);
                        l_fuOpContService.push(this.fromAccountId, this.toAccountId);
                    }
                    else if (WEB3RichPushDataTypeDef.IFO_LAPSE.equals(this.type[i]))
                    {
                        // 1.2.9　@先物OP失効通知データデータプッシュ
                        WEB3RichPushFuOpLapseUnitService l_fuOpLapseService =
                            (WEB3RichPushFuOpLapseUnitService) Services.getService(
                            WEB3RichPushFuOpLapseUnitService.class);
                        l_fuOpLapseService.push(this.fromAccountId, this.toAccountId);
                    }
                }
            }

            WEB3RichPushObjectContext l_context = (WEB3RichPushObjectContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3RichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME
                );

            //ソート
            List l_allList = l_context.getPushObjects();
            //会社毎ソートMapを取得
            Map l_lstMap4SortedMap = sortRichPushObjects(l_allList);

            Set set = l_lstMap4SortedMap.keySet();
            Iterator iterator = set.iterator();
            String key;
            while (iterator.hasNext())
            {
                key = (String) iterator.next();
                Map l_mapTree = (Map) l_lstMap4SortedMap.get(key);
                List l_lstsorted = getListFromTreeMap(l_mapTree);

                //最大送信件数チェック
                int l_intPushMaxMessageSize = getPushMaxMessageSize();
                ArrayList l_lstMax = new ArrayList();
                for (int i = 0; i < l_lstsorted.size(); i++)
                {
                    if (l_lstMax.size() < l_intPushMaxMessageSize)
                    {
                        l_lstMax.add(l_lstsorted.get(i));
                    }
                }

                //送信
                boolean l_ret = forwardGateWay(key, l_lstMax);

                //送信成功ステータス
                String l_strStatus = WEB3RichPushDataStatusDef.DEAL;
                //送信結果=失敗の場合
                if (l_ret == false)
                {
                    l_strStatus = WEB3RichPushDataStatusDef.DATA_ERROR;
                }

                //データプッシュ結果を履歴テーブルに保存
                saveRichPushHistory(l_lstMax,
                                    l_strStatus);

            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * 一回最大プッシュメッセージ数を取得
     *
     * @@return int
     */
    public int getPushMaxMessageSize()
    {

        final String STR_METHOD_NAME = "getPushMaxMessageSize()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return WEB3RichPushPlugin.getPushMaxMessageSize();

    }

    /**
     * ゲートウェイ経由リッチクライアントへデータプッシュ
     *
     * @@param l_strInstitutionCode String
     * @@param l_lstPushObjects String
     * @@param l_mapHistoryRecords Map
     * @@return boolean
     */
    public boolean forwardGateWay(String l_strInstitutionCode, List l_lstPushObjects)
    {

        final String STR_METHOD_NAME = "forwardToGwy()";

        WEB3RichPushGateWayService l_pservice =
            (WEB3RichPushGateWayService) Services.getService(
            WEB3RichPushGateWayService.class);
        boolean l_ret = l_pservice.push(l_strInstitutionCode, l_lstPushObjects);

        log.exiting(STR_METHOD_NAME);
        return l_ret;
    }

    /**
     * プッシュ結果を履歴テーブルに保存
     *
     * @@param l_lstPushRecords List
     * @@param l_mapPushHistoryRecords Hashtable
     * @@param l_strStatus String
     * @@throws DataQueryException
     * @@throws DataNetworkException
     */
    public void saveRichPushHistory(List l_lstPushRecords,
                                    String l_strStatus) throws DataQueryException,
        DataNetworkException
    {

        final String STR_METHOD_NAME = "saveRichPushHistory()";

        if (l_lstPushRecords == null)
        {
            log.error(
                "l_lstPushRecords==null! save fail!");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        Timestamp l_tm = GtlUtils.getSystemTimestamp();
        if (l_lstPushRecords.size() > 0)
        {
            BatchedQuery[] l_bqs = new BatchedQuery[l_lstPushRecords.size()];
            for (int i = 0; i < l_lstPushRecords.size(); i++)
            {
                Row l_pushRow = (Row) l_lstPushRecords.get(i);

                BatchedQuery l_bq = doUpdatePushHistory(l_pushRow.
                    getPrimaryKey().
                    toString(),
                    l_strStatus, l_tm);
                l_bqs[i] = l_bq;
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doQueries(l_bqs);
        }
        log.debug("saveRichPushHistory 件数=l_lstPushRecords.size()");
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * リッチクライアントデータプッシュ結果を履歴テーブルに保存する。
     *
     * @@param l_strSerlnum String
     * @@param l_strStatus String
     * @@throws DataNetworkException
     * @@throws DataQueryException
     */
    private BatchedQuery doUpdatePushHistory(String l_strSerlnum,
                                             String l_strStatus, Timestamp l_tm) throws
        DataNetworkException, DataQueryException
    {

        final String STR_METHOD_NAME = "doUpdatePushHistory()";
        log.entering(STR_METHOD_NAME);

        Map l_changes = new HashMap();
        l_changes.put("status", l_strStatus);
        l_changes.put("last_updated_timestamp", l_tm);

        RichPushHistoryPK l_pk = new RichPushHistoryPK();
        l_pk.serlnum = Long.valueOf(l_strSerlnum).longValue();
        log.exiting(STR_METHOD_NAME);

        return BatchedQuery.createUpdateQuery(l_pk, l_changes);
    }

    /**
     * 会社単位でプッシュオブジェクトをソートする
     *
     * @@param l_lstPushObjects List
     * @@return Map
     */
    public Map sortRichPushObjects(List l_lstPushObjects)
    {

        Map l_tblInstBaseMap4Map = new Hashtable();

        for (int i = 0; i < l_lstPushObjects.size(); i++)
        {

            Row l_row = (Row) l_lstPushObjects.get(i);
            if (l_row instanceof RichPushEquityOrderAcceptRow)
            {
                RichPushEquityOrderAcceptRow l_spRow = (RichPushEquityOrderAcceptRow)
                    l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
            else if (l_row instanceof RichPushSwapOrderAcceptRow)
            {
                RichPushSwapOrderAcceptRow l_spRow = (RichPushSwapOrderAcceptRow)
                    l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);

            }
            else if (l_row instanceof RichPushEquityChangeCancelRow)
            {

                RichPushEquityChangeCancelRow l_spRow = (RichPushEquityChangeCancelRow)
                    l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);

            }
            else if (l_row instanceof RichPushEquityContRow)
            {

                RichPushEquityContRow l_spRow = (RichPushEquityContRow)
                    l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);

            }
            else if (l_row instanceof RichPushEquityLapseRow)
            {
                RichPushEquityLapseRow l_spRow = (RichPushEquityLapseRow)
                    l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
            else if (l_row instanceof RichPushIfoOrderAcceptRow)
            {
                RichPushIfoOrderAcceptRow l_spRow = (RichPushIfoOrderAcceptRow)
                    l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
            else if (l_row instanceof RichPushIfoChangeCancelRow)
            {
                RichPushIfoChangeCancelRow l_spRow = (RichPushIfoChangeCancelRow)
                    l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
            else if (l_row instanceof RichPushIfoContRow)
            {
                RichPushIfoContRow l_spRow = (RichPushIfoContRow)
                    l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
            else if (l_row instanceof RichPushIfoLapseRow)
            {
                RichPushIfoLapseRow l_spRow = (RichPushIfoLapseRow)
                    l_row;

                String l_strInstitutionCode = l_spRow.getInstitutionCode();
                TreeMap l_treeMap = (TreeMap) l_tblInstBaseMap4Map.get(
                    l_strInstitutionCode);
                if (l_treeMap == null)
                {
                    l_treeMap = new TreeMap(new
                                            WEB3RichPushObjectComparator());
                    l_tblInstBaseMap4Map.put(l_strInstitutionCode, l_treeMap);
                }

                WEB3RichPushObjectCompareKey l_sortkey = new WEB3RichPushObjectCompareKey
                    (l_spRow.getAccountId(), l_spRow.getLastUpdatedTimestamp(),
                     l_spRow.getType(), l_spRow.getTpk());
                l_treeMap.put(l_sortkey, l_spRow);
            }
        }

        return l_tblInstBaseMap4Map;
    }

    /**
     * getListFromTreeMap
     *
     * @@param l_treeMap Map
     * @@return List
     */
    public List getListFromTreeMap(Map l_treeMap)
    {
        ArrayList l_lstRet = new ArrayList();
        //イテレータ取得
        Set set = l_treeMap.keySet();
        Iterator iterator = set.iterator();
        Object key;
        while (iterator.hasNext())
        {
            key = iterator.next();
            l_lstRet.add(l_treeMap.get(key));
        }
        return l_lstRet;
    }
}
@
