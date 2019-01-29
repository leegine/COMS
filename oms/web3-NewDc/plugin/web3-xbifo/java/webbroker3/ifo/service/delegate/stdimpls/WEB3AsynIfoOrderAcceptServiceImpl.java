head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AsynIfoOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  先物OP注文受付サービス実装クラス(WEB3AsynIfoOrderAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 同期版履歴：
                    2004/06/15 鄒鋭 (中訊) 新規作成
                   非同期対応版履歴：
                    2005/03/11 李志強　@非同期実行対応（新規クラス）
*/


package webbroker3.ifo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.ifo.data.HostFotypeOrderAcceptRow;
import webbroker3.ifo.message.WEB3IfoOrderAcceptRequest;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;

/**
 * （非同期対応先物OP注文受付サービス実装クラス）。
 * @@author  : 李志強（日本中訊）
 * @@version : 1.0
 */
public class WEB3AsynIfoOrderAcceptServiceImpl
    implements Runnable
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynIfoOrderAcceptServiceImpl.class);

    /**
     * 注文受付リクエスト
     */
    private WEB3IfoOrderAcceptRequest request;


    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3AsynIfoOrderAcceptServiceImpl(WEB3IfoOrderAcceptRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynIfoOrderAcceptServiceImpl：run()";
        log.entering(STR_METHOD_NAME);
        
        try
        {    
            try
            {
                WEB3IfoOrderAcceptRequest l_ifoOrderAcceptRequest =
                    (WEB3IfoOrderAcceptRequest) request;
                    
                log.debug("Enter the try panth");
                //クエリプロセッサのインスタンスを取得する
                //スロー DataNetworkException, DataFindException
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //先物OP注文受付TransactionCallbackオブジェクトを生成
                WEB3IfoOrderAcceptTransactionCallback l_ifoOrderAcceptTransactionCallback =
                    new WEB3IfoOrderAcceptTransactionCallback();
                    
                
                //set識別コードプレフィクス一覧()
                l_ifoOrderAcceptTransactionCallback.setOrderRequestNumberPrefixGroup(l_ifoOrderAcceptRequest.
                    orderRequestNumberPrefixGroup);

                l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_ifoOrderAcceptTransactionCallback);
                log.debug("Exit the try path.");
            }
            catch (DataQueryException l_dqex)
            {
                //DBアクセスが失敗の場合
                log.error( getClass().getName() + STR_METHOD_NAME ,l_dqex);
                //例外をスローする
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataNetworkException l_dnex)
            {
                //DBアクセスが失敗の場合
                log.error( getClass().getName() + STR_METHOD_NAME ,l_dnex);
                //例外をスローする
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        
        //スレッド開放
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(request.threadNo.
                longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 先物OP注文受付TransactionCallback<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     */
    private class WEB3IfoOrderAcceptTransactionCallback implements TransactionCallback
    {

        /**
         * (先物OP注文受付TransactionCallback)<BR>
         * デフォルトコンストラクタ<BR>
         * @@return webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptServiceImpl.WEB3IfoOrderAcceptTransactionCallback
         * @@roseuid 408359FA002B
         */
        public WEB3IfoOrderAcceptTransactionCallback()
        {

        }
        /**
         * 識別コードプレフィクス一覧<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * (set引数の識別コードプレフィクス一覧)<BR>
         * <BR>
         * 引数の識別コードプレフィクス一覧をプロパティにセットする。<BR>
         * @@params String[] - 識別コードプレフィクス一覧<BR>
         */
        public void setOrderRequestNumberPrefixGroup(String[]
            l_orderRequestNumberPrefixGroup)
        {
            orderRequestNumberPrefixGroup = l_orderRequestNumberPrefixGroup;
        }

        /**
         * (get引数の識別コードプレフィクス一覧)<BR>
         * <BR>
         * 識別コードプレフィクス一覧を取得する。<BR>
         * @@return String[]<BR>
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return orderRequestNumberPrefixGroup;
        }

        /**
         * 注文受付処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（先物OP注文受付）process」参照。<BR>
         * <BR>
         * １） 処理対象レコード取得<BR>
         * 注文受付キューテーブルを、行ロック（select for update）にて読み込む。<BR>
         * <BR>
         * 　@[検索条件]<BR>
         * 　@先物OP注文受付キューテーブル.データコード == <BR>
         *   ”オプション注文受付”（EI80A）<BR>
         *   または、”先物注文受付”（EI80C）<BR>
         * 　@先物OP注文受付キューテーブル.処理区分 == ”未処理”<BR>
         * <BR>
         * 以降の処理は、検索結果の注文受付キューテーブルの各行に対して実施する。<BR>
         * <BR>
         * ２） インタセプタセット<BR>
         * 先物OP注文受付インタセプタオブジェクトを生成し、<BR>
         * setThreadLocalPersistenceEventInterceptor()にて、<BR>
         * 注文マネージャのThreadLocalにセットする。<BR>
         * <BR>
         * [コンストラクタの引数]<BR>
         * エラーコード：　@先物OP注文受付キューテーブル.エラーメッセージ<BR>
         * <BR>
         * ３） キューに該当する注文単位取得<BR>
         * 注文マネージャ.get注文単位()をコールし、<BR>
         * 処理対象キューに該当する注文単位オブジェクトを取得する。<BR>
         * <BR>
         * 　@[引数]<BR>
         * 　@証券会社コード　@：注文受付キューテーブル.証券会社コード<BR>
         * 　@部店コード　@：注文受付キューテーブル.部店コード<BR>
         * 　@商品タイプ　@：”先物オプション”<BR>
         * 　@識別コード　@：注文受付キューテーブル.識別コード<BR>
         * <BR>
         * ４） コールバックサービス（注文更新処理）実行<BR>
         * 処理対象キューの内容を判定し、以下の処理を実施する。<BR>
         * <BR>
         * ・注文受付キューテーブル.注文受付結果 == "注文受付完了"であれば、<BR>
         * 　@DefaultNewOrderAcceptedMarketResponseMessageを生成する。<BR>
         * <BR>
         * 　@IfoMarketResponseReceiverCallbackServiceクラスを取得し、<BR>
         * 　@processメソッドをコールする。<BR>
         * <BR>
         * 　@[引数]<BR>
         * 　@（生成したパラメータオブジェクト）<BR>
         * <BR>
         * 　@時価サーバに通知（notify()）を行う。<BR>
         * <BR>
         * 　@[引数]<BR>
         * 　@注文単位.getOrderId<BR>
         * <BR>
         * ・注文受付キューテーブル.注文受付結果 == "エラー"であれば、<BR>
         * 　@DefaultNewOrderRejectMarketResponseMessageを生成する。<BR>
         * <BR>
         * 　@IfoMarketResponseReceiverCallbackServiceクラスを取得し、<BR>
         * 　@processメソッドをコールする。<BR>
         * <BR>
         * 　@[引数]<BR>
         * 　@（生成したパラメータオブジェクト）<BR>
         * <BR>
         * ５） キューテーブルに処理済を更新<BR>
         * 以下の通り、処理対象キューレコードを更新する。<BR>
         * <BR>
         * 　@[更新内容]<BR>
         * 　@（各行毎の処理で例外が発生した場合）<BR>
         * 　@先物OP注文受付キューテーブル.処理区分 == ”エラー”<BR>
         * <BR>
         * 　@（以外の場合）<BR>
         * 　@先物OP注文受付キューテーブル.処理区分 == "処理済"<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4083597C01D1
         */
        public java.lang.Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "execute(l_request)";
            log.entering( STR_METHOD_NAME);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //[検索条件]
            //先物OP注文受付キューテーブル.データコード == ”オプション注文受付”（EI80A）または、”先物注文受付”（EI80C）
            //先物OP注文受付キューテーブル.処理区分 == ”未処理”
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" (request_code = ? "); //データコード == ”オプション注文受付”
            l_sbWhere.append(" or request_code = ?) "); //データコード ==”先物注文受付”
            l_sbWhere.append(" and status = ? "); //処理区分 == ”未処理”

            int l_intPrefixGroupLength = 0;
            if (orderRequestNumberPrefixGroup != null)
            {
                l_intPrefixGroupLength = orderRequestNumberPrefixGroup.length;
            }
            if (l_intPrefixGroupLength > 0)
            {
                l_sbWhere.append(" and (");
                for (int i = 0; i < l_intPrefixGroupLength; i++)
                {
                    if (i > 0)
                    {
                        l_sbWhere.append(" or ");
                    }
                    l_sbWhere.append("order_request_number like ?");
                }
                l_sbWhere.append(")");
            }
            
            Object[] l_objIfoOrderUnitWhere = new Object[l_intPrefixGroupLength + 3];
            
            l_objIfoOrderUnitWhere[0] = WEB3HostRequestCodeDef.OPTION_ORDER_ACCEPT;
            l_objIfoOrderUnitWhere[1] = WEB3HostRequestCodeDef.FUTURES_ORDER_ACCEPT;
            l_objIfoOrderUnitWhere[2] = WEB3StatusDef.NOT_DEAL;
            for (int i = 0; i < l_intPrefixGroupLength; i++)
            {
                l_objIfoOrderUnitWhere[i + 3] = orderRequestNumberPrefixGroup[i] + "%";
            }

            //スロー DataNetworkException, DataFindException
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            //注文受付キューテーブルの結果リスト
            List l_lisSearchResult = null;

            //スロー DataQueryExeption,DataNetworkException,
            //doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[])
            l_lisSearchResult = l_QueryProcessor.doFindAllQuery(
                HostFotypeOrderAcceptRow.TYPE,
                l_sbWhere.toString(),
                null,
                null,
                l_objIfoOrderUnitWhere
                );
            log.debug("Get the number of the records." + l_lisSearchResult);
            if (l_lisSearchResult == null)
            {
                log.error(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }

            //先物OP注文受付キューテーブルROWオブジェクト
            HostFotypeOrderAcceptParams  l_hostFotypeOrderAcceptParams = null;

            //注文単位オブジェクト
            OrderUnit l_orderUnit = null;

            int i = 0;
            int l_intLen = 0;
            l_intLen = l_lisSearchResult.size();
            log.debug("Output the number of the records"  + l_intLen);

            for ( i = 0; i < l_intLen; i ++)
            { 
                l_hostFotypeOrderAcceptParams = new HostFotypeOrderAcceptParams((HostFotypeOrderAcceptRow)l_lisSearchResult.get(i));               
                try
                {                            
                    // TransactionCallback生成
                    WEB3IfoOrderAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3IfoOrderAcceptNormalTransactionCallback(l_hostFotypeOrderAcceptParams);

                    // doTransaction()
                    l_QueryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
                catch (Exception l_exp)
                {
                    //--------------------
                    //処理対象キューUPDATE　@(エラー時)
                    //--------------------
                    if (l_exp instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_exp;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
                        {
                            log.debug("口座ロック失敗：");
                            continue;
                        }
                    }

                    //エラーがそれ以外の場合　@(=>エラー)
                    log.debug("一件処理にてエラー発生：");
                    Map l_mapChanges = new HashMap();
                    l_mapChanges.put("status",WEB3StatusDef.DATA_ERROR);
                    l_mapChanges.put("last_updated_timestamp",GtlUtils.getSystemTimestamp());
                    l_QueryProcessor.doUpdateQuery(l_hostFotypeOrderAcceptParams.getPrimaryKey(), l_mapChanges);
                }
            }
            log.exiting( STR_METHOD_NAME);
            return null;

        }
    }
}
        
        
        
        
        @
