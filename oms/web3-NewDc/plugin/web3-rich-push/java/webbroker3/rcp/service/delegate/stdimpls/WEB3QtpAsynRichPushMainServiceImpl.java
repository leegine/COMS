head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpAsynRichPushMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 非同期対応リッチクライアントプッシュメインサービス実装クラス(WEB3QtpAsynRichPushMainServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/03/10 孫(FLJ) 新規作成
                  : 2009/06/03 毛(FTL) 岩井対応
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.rcp.WEB3QtpRichPushPlugin;
import webbroker3.rcp.WEB3RichPushObjectComparator;
import webbroker3.rcp.WEB3RichPushObjectCompareKey;
import webbroker3.rcp.WEB3RichPushObjectContext;
import webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityContRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushHistoryPK;
import webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoContRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptRow;
import webbroker3.rcp.define.WEB3RichPushDataStatusDef;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.message.WEB3QtpRichPushMainRequest;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushGateWayService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushSwapOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushUnitService;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.util.WEB3LogUtility;

/**
 * 非同期対応リッチクライアントプッシュメインサービス実装クラス
 * @@author  : 孫（FLJ）
 * @@version : 1.0
 */
public class WEB3QtpAsynRichPushMainServiceImpl
    implements Runnable
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpAsynRichPushMainServiceImpl.class);

    /**
     * プッシュサービス
     */
    protected final static HashMap pushServices = new HashMap();

    /**
     * リッチクライアントプッシュメインリクエスト
     */
    private WEB3QtpRichPushMainRequest request;

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3QtpAsynRichPushMainServiceImpl(
        WEB3QtpRichPushMainRequest
        l_request)
    {
        final String STR_METHOD_NAME =
            "WEB3QtpAsynRichPushMainServiceImpl()";
        log.entering(STR_METHOD_NAME);

        if(l_request == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02779,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        this.request = l_request;
        synchronized(pushServices)
        {
            if(pushServices.size() == 0)
            {
                // 現物信用注文受付通知サービス
                pushServices.put(WEB3RichPushDataTypeDef.EQUITY_ORDER_ACCEPT,
                    Services.getService(WEB3QtpRichPushEquityMarginOrderAcceptUnitService.class));
                // 信用現引現渡注文受付通知サービス
                pushServices.put(WEB3RichPushDataTypeDef.SWAP_ORDER_ACCEPT,
                    Services.getService(WEB3QtpRichPushSwapOrderAcceptUnitService.class));
                // 現物信用訂正取消通知サービス
                pushServices.put(WEB3RichPushDataTypeDef.EQTYPE_CHANGE_CANCEL,
                    Services.getService(WEB3QtpRichPushEquityMarginChangeCancelUnitService.class));
                // 現物信用出来通知サービス
                pushServices.put(WEB3RichPushDataTypeDef.EQTYPE_CONT,
                    Services.getService(WEB3QtpRichPushEquityMarginContUnitService.class));
                // 現物信用失効通知サービス
                pushServices.put(WEB3RichPushDataTypeDef.EQTYPE_LAPSE,
                    Services.getService(WEB3QtpRichPushEquityMarginLapseUnitService.class));
                // 先物ＯＰ注文受付通知サービス
                pushServices.put(WEB3RichPushDataTypeDef.IFO_ORDER_ACCEPT,
                    Services.getService(WEB3QtpRichPushFuOpOrderAcceptUnitService.class));
                // 先物ＯＰ訂正取消通知サービス
                pushServices.put(WEB3RichPushDataTypeDef.IFO_CHANGE_CANCEL,
                    Services.getService(WEB3QtpRichPushFuOpChangeCancelUnitService.class));
                // 先物ＯＰ出来通知サービス
                pushServices.put(WEB3RichPushDataTypeDef.IFO_CONT,
                    Services.getService(WEB3QtpRichPushFuOpContUnitService.class));
                // 先物ＯＰ失効通知サービス
                pushServices.put(WEB3RichPushDataTypeDef.IFO_LAPSE,
                    Services.getService(WEB3QtpRichPushFuOpLapseUnitService.class));

                // サービス出来ることのチェック
                Iterator l_iterator = pushServices.entrySet().iterator();
                while(l_iterator.hasNext())
                {
                    Entry l_entry = (Entry)l_iterator.next();
                    if(l_entry.getValue() == null)
                    {
                        throw new NullPointerException("Push service <" + l_entry.getKey() + "> is null!!");
                    }
                }
            }
        }
    }

    /**
     * 実行
     */
    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3QtpAsynRichPushMainServiceImpl：run()";
        log.entering(STR_METHOD_NAME);

        WEB3DescendRacCtxService l_sv = (WEB3DescendRacCtxService) Services.getService(
            WEB3DescendRacCtxService.class);
        l_sv.setAccountIdCtx(this.request.fromAccountId.longValue());

        try
        {
            // 1.1. WEB3QtpRichPushMainRequest.validate()
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
            WEB3QtpRichPushMainTransactionCallback l_transactionCallBack =
                new WEB3QtpRichPushMainTransactionCallback();

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
                WEB3QtpRichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME,
                null);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3QtpRichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME,
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
                        WEB3QtpRichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME
                );
            try
            {
                this.saveQtpRichPushHistory(l_context.getPushObjects(),null);
            }
            catch (Throwable t)
            {
                log.error(t.getMessage(), t);
            }

            //クリアコンテクスト
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3QtpRichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME,
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
    public class WEB3QtpRichPushMainTransactionCallback
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
        public WEB3QtpRichPushMainTransactionCallback()
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
                Iterator l_iterator = pushServices.values().iterator();
                while(l_iterator.hasNext())
                {
                    ((WEB3QtpRichPushUnitService)l_iterator.next()).push(this.fromAccountId, this.toAccountId);
                }
            }
            else
            {
                // データタイプの指定がある場合、指定された機@能のみプッシュ
                for (int i = 0; i < this.type.length; i++)
                {
                    WEB3QtpRichPushUnitService l_pushService = (WEB3QtpRichPushUnitService)pushServices.get(this.type[i]);
                    if(l_pushService != null)
                    {
                        l_pushService.push(this.fromAccountId, this.toAccountId);
                    }
                    else
                    {
                        log.error("未知データタイプ<" + this.type[i] + ">検知された、処理スキップ!"  );
                    }
                }
            }

            WEB3RichPushObjectContext l_context = (WEB3RichPushObjectContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3QtpRichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME
                );

            //ソート
            List l_allList = l_context.getPushObjects();
            //会社毎ソートMapを取得
            Map l_lstMap4SortedMap = sortQtpRichPushObjects(l_allList);

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
                boolean[] l_ret = forwardGateWay(key, l_lstMax);

                //データプッシュ結果を履歴テーブルに保存
                saveQtpRichPushHistory(l_lstMax,
                        l_ret);

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
        return WEB3QtpRichPushPlugin.getPushMaxMessageSize();

    }

    /**
     * ゲートウェイ経由リッチクライアントへデータプッシュ
     *
     * @@param l_strInstitutionCode String
     * @@param l_lstPushObjects String
     * @@param l_mapHistoryRecords Map
     * @@return boolean
     */
    public boolean[] forwardGateWay(String l_strInstitutionCode, List l_lstPushObjects)
    {

        final String STR_METHOD_NAME = "forwardToGwy()";
        log.entering(STR_METHOD_NAME);

        WEB3QtpRichPushGateWayService l_pservice =
            (WEB3QtpRichPushGateWayService) Services.getService(
            WEB3QtpRichPushGateWayService.class);
        boolean[] l_ret = l_pservice.push(l_strInstitutionCode, l_lstPushObjects);

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
    public void saveQtpRichPushHistory(List l_lstPushRecords,
                                    boolean[] l_status) throws DataQueryException,
        DataNetworkException
    {

        final String STR_METHOD_NAME = "saveQtpRichPushHistory()";
        log.entering(STR_METHOD_NAME);
        
        if (l_lstPushRecords == null)
        {
            log.error(
                "l_lstPushRecords==null! save fail!");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        if(l_status == null)
        {
            log.error(
            "l_status==null! save all result as false!");            
            l_status = new boolean[l_lstPushRecords.size()];
            for (int i = 0; i < l_status.length; i++)
            {
                l_status[i] = false;
            }
        }
        if (l_lstPushRecords.size() != l_status.length )
        {
            log.error(
                "the size of l_lstPushRecords is not same as the result status! save fail! data size:"+l_lstPushRecords.size() + ",result size:"+l_status.length);
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
                //送信成功ステータス
                String l_strStatus = WEB3RichPushDataStatusDef.DEAL;
                //送信結果=失敗の場合
                if (l_status[i] == false)
                {
                    l_strStatus = WEB3RichPushDataStatusDef.DATA_ERROR;
                }
                
                BatchedQuery l_bq = doUpdateQtpPushHistory(l_pushRow.
                    getPrimaryKey().
                    toString(),
                    l_strStatus, l_tm);
                l_bqs[i] = l_bq;
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doQueries(l_bqs);
        }
        log.debug("saveQtpRichPushHistory 件数="+l_lstPushRecords.size());
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
    private BatchedQuery doUpdateQtpPushHistory(String l_strSerlnum,
                                             String l_strStatus, Timestamp l_tm) throws
        DataNetworkException, DataQueryException
    {

        final String STR_METHOD_NAME = "doUpdateQtpPushHistory()";
        log.entering(STR_METHOD_NAME);

        Map l_changes = new HashMap();
        l_changes.put("status", l_strStatus);
        l_changes.put("last_updated_timestamp", l_tm);

        QtpRichPushHistoryPK l_pk = new QtpRichPushHistoryPK();
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
    public Map sortQtpRichPushObjects(List l_lstPushObjects)
    {

        Map l_tblInstBaseMap4Map = new Hashtable();

        for (int i = 0; i < l_lstPushObjects.size(); i++)
        {

            Row l_row = (Row) l_lstPushObjects.get(i);

            // QTP現物信用注文受付通知ロー
            if (l_row instanceof QtpRichPushEqOrderacceptRow)
            {
                QtpRichPushEqOrderacceptRow l_spRow = (QtpRichPushEqOrderacceptRow)l_row;

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
            // QTP信用現引現渡注文受付通知ロー
            else if (l_row instanceof QtpRichPushSwOrderacceptRow)
            {
                QtpRichPushSwOrderacceptRow l_spRow = (QtpRichPushSwOrderacceptRow)l_row;

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
            // QTP現物信用訂正取消通知ロー
            else if (l_row instanceof QtpRichPushEqChangecancelRow)
            {

                QtpRichPushEqChangecancelRow l_spRow = (QtpRichPushEqChangecancelRow)l_row;

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
            // QTP現物信用出来通知ロー
            else if (l_row instanceof QtpRichPushEquityContRow)
            {

                QtpRichPushEquityContRow l_spRow = (QtpRichPushEquityContRow)l_row;

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
            // QTP現物信用失効通知ロー
            else if (l_row instanceof QtpRichPushEquityLapseRow)
            {
                QtpRichPushEquityLapseRow l_spRow = (QtpRichPushEquityLapseRow)l_row;

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
            // QTP先物ＯＰ注文受付通知ロー
            else if (l_row instanceof QtpRichPushIfoOrderacceptRow)
            {
                QtpRichPushIfoOrderacceptRow l_spRow = (QtpRichPushIfoOrderacceptRow)l_row;

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
            // QTP先物ＯＰ訂正取消通知ロー
            else if (l_row instanceof QtpRichPushIfoChangecancelRow)
            {
                QtpRichPushIfoChangecancelRow l_spRow = (QtpRichPushIfoChangecancelRow)l_row;

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
            // QTP先物ＯＰ出来通知ロー
            else if (l_row instanceof QtpRichPushIfoContRow)
            {
                QtpRichPushIfoContRow l_spRow = (QtpRichPushIfoContRow)l_row;

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
            // QTP先物ＯＰ失効通知ロー
            else if (l_row instanceof QtpRichPushIfoLapseRow)
            {
                QtpRichPushIfoLapseRow l_spRow = (QtpRichPushIfoLapseRow)l_row;

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
