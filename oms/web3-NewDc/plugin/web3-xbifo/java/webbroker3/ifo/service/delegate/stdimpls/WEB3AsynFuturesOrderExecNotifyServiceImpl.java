head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AsynFuturesOrderExecNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 株価指数先物出来通知サービス実装クラス(WEB3AsynFuturesOrderExecNotifyServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 同期版履歴：
 Revesion History : 2004/7/23 艾興 (中訊) 新規作成
 Revesion History : 2007/04/25 崔遠鵬 (中訊) 仕様変更モデルNo.634
 */

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataRollbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyRow;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyParams;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyRow;
import webbroker3.ifo.data.HostOptionOrderExecNotifyParams;
import webbroker3.ifo.data.HostOptionOrderExecNotifyRow;
import webbroker3.ifo.message.WEB3FuturesOrderExecNotifyRequest;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;

/**
 * （非同期対応株価指数先物出来通知サービス実装クラス）。
 * @@author  : 李志強（日本中訊）
 * @@version : 1.0
 */
public class WEB3AsynFuturesOrderExecNotifyServiceImpl
    implements Runnable
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynFuturesOrderExecNotifyServiceImpl.class);

    /**
     * 株式指数先物出来通知リクエスト
     */
    private WEB3FuturesOrderExecNotifyRequest request;


    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3AsynFuturesOrderExecNotifyServiceImpl(WEB3FuturesOrderExecNotifyRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynFuturesOrderExecNotifyServiceImpl：run()";
        log.entering(STR_METHOD_NAME);

        try
        {
            try
            {
                WEB3FuturesOrderExecNotifyRequest l_orderExecNotifyRequest =
                    (WEB3FuturesOrderExecNotifyRequest) request;

                //1.1 getDefaultProcessor()
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //1.2 先物出来通知TransactionCallback()
                WEB3FuturesOrderExecNotifyTransactionCallback l_orderExecNotifyTransactionCallback =
                    new WEB3FuturesOrderExecNotifyTransactionCallback();

                //set識別コードプレフィクス一覧()
                l_orderExecNotifyTransactionCallback.setOrderRequestNumberPrefixGroup(
                    l_orderExecNotifyRequest.orderRequestNumberPrefixGroup);

                //1.4 doTransaction(トランザクション属性 : int,
                //  先物出来通知TransactionCallback : TransactionCallback)
                l_queryProcessor.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    l_orderExecNotifyTransactionCallback);
            }
            catch (DataRollbackException l_dataRollbackException)
            {
                log.error("DBへのアクセスに失敗しました。", l_dataRollbackException);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataRollbackException.getMessage(),
                    l_dataRollbackException);
            }
            catch (DataFindException l_dataFindException)
            {
                log.error("DBへのアクセスに失敗しました。", l_dataFindException);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataFindException.getMessage(),
                    l_dataFindException);
            }
            catch (DataNetworkException l_dataNetworkException)
            {
                log.error("DBへのアクセスに失敗しました。", l_dataNetworkException);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataNetworkException.getMessage(),
                    l_dataNetworkException);
            }
            catch (DataCallbackException l_dataCallbackException)
            {
                log.error("DBへのアクセスに失敗しました。", l_dataCallbackException);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataCallbackException.getMessage(),
                    l_dataCallbackException);
            }
            catch (DataQueryException l_dataQueryException)
            {
                log.error("DBへのアクセスに失敗しました。", l_dataQueryException);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataQueryException.getMessage(),
                    l_dataQueryException);
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
     * (先物出来通知TransactionCallback)<BR>
     * 先物出来通知TransactionCallback<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     */
    public class WEB3FuturesOrderExecNotifyTransactionCallback
        implements TransactionCallback
    {
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
         * トランザクション処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（先物出来通知）process」参照。<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40874E4D02BE
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_orderManager = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
            HostFotypeCloseOrderNotifyParams l_closeParams = null;
            HostFotypeOrderClmdNotifyParams l_cancelParams = null;

            //1.1 先物OP出来通知キューテーブル読込
            //[検索条件]
            //先物OP出来通知キューテーブル.データコード == ”株価指数出来通知”（EI815）
            //先物OP出来通知キューテーブル.処理区分 == ”未処理”
            String l_strWhere = " request_code = ? ";       //データコード = ?
            l_strWhere = l_strWhere + " and   status = ? "; //処理区分 = ?

            int l_intLength = 0;
            if (orderRequestNumberPrefixGroup != null)
            {
                l_intLength = orderRequestNumberPrefixGroup.length;
            }
            if (l_intLength > 0)
            {
                l_strWhere = l_strWhere + " and (";
                for (int i = 0; i < l_intLength; i++)
                {
                    if (i > 0)
                    {
                        l_strWhere = l_strWhere + " or ";
                    }
                    l_strWhere = l_strWhere + "order_request_number like ?";
                }
                l_strWhere = l_strWhere + ")";
            }

            String[] l_strBindValues = new String[l_intLength + 2];
            l_strBindValues[0] = WEB3HostRequestCodeDef.FUTURES_EXEC_NOTICE;//注文単位.識別コード
            l_strBindValues[1] = WEB3StatusDef.NOT_DEAL; //未処理

            for (int i = 0; i < l_intLength; i++)
            {
                l_strBindValues[i + 2] = orderRequestNumberPrefixGroup[i] + "%";
            }

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = l_QueryProcessor.doFindAllQuery(
                HostOptionOrderExecNotifyParams.TYPE,
                l_strWhere,
                null,
                null,
                l_strBindValues);

            if ((l_lisRecords != null) && !l_lisRecords.isEmpty())
            {
                int l_intListRecordCnt = l_lisRecords.size();
                log.debug("l_lisRecords.size() = " + l_intListRecordCnt);

                //1.2 先物OP出来通知キューテーブル検索結果レコードごとのLOOP
                for (int i = 0; i < l_intListRecordCnt; i++)
                {
                    log.debug("LOOP i = " + i);
                    HostOptionOrderExecNotifyRow l_notifyRow = (HostOptionOrderExecNotifyRow)l_lisRecords.get(i);
                    HostOptionOrderExecNotifyParams l_notifyParams = new HostOptionOrderExecNotifyParams(l_notifyRow);
                    try
                    {
                        
                        // TransactionCallback生成
                        WEB3FuturesOrderExecNotifyNormalTransactionCallback l_transactionCallback =
                            new WEB3FuturesOrderExecNotifyNormalTransactionCallback(
                            l_notifyRow,
                            l_notifyParams);

                        // doTransaction()
                        l_QueryProcessor.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            l_transactionCallback);
                    }
                    catch (DataCallbackException l_exp)     
                    {       
                        ErrorInfo l_errorInfo = (ErrorInfo)l_exp.getDetails();  

                        // 失効通知処理が失敗した場合       
                        if ((l_errorInfo==WEB3ErrorCatalog.BUSINESS_ERROR_01961)
                            || (l_errorInfo==WEB3ErrorCatalog.SYSTEM_ERROR_80077))        
                        {       
                            log.debug("失効通知処理失敗");  
                            String l_strWhereClose = " request_code = ? "   
                                + " and institution_code = ? "
                                + " and branch_code = ? "
                                + " and account_code = ? "
                                + " and order_request_number = ? "
                                + " and status = ? ";

                            Object[] l_objWhereClose =  
                            {   
                                WEB3HostRequestCodeDef.FUTURES_CLOSE_NOTICE,
                                l_notifyParams.getInstitutionCode(),
                                l_notifyParams.getBranchCode(),
                                l_notifyParams.getAccountCode(),
                                l_notifyParams.getOrderRequestNumber(),
                                WEB3StatusDef.DEALING
                            };  
        
                            List l_lisSearchResultClose =   
                                l_QueryProcessor.doFindAllQuery(    
                                    HostFotypeCloseOrderNotifyRow.TYPE, 
                                    l_strWhereClose,    
                                    null,   
                                    "for update",   
                                    l_objWhereClose);   
        
                            if (l_lisSearchResultClose.size() > 0)  
                            {   
                                l_closeParams = (HostFotypeCloseOrderNotifyParams)l_lisSearchResultClose.get(0);
                                l_closeParams.setStatus(WEB3StatusDef.DATA_ERROR);
                                l_closeParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                                l_QueryProcessor.doUpdateQuery(l_closeParams);
                            }   
                        }       

                        // 取消通知処理が失敗した場合        
                        if ((l_errorInfo==WEB3ErrorCatalog.BUSINESS_ERROR_01962)
                            || (l_errorInfo==WEB3ErrorCatalog.SYSTEM_ERROR_80078))     
                        {        
                            log.debug("取消通知処理失敗");   
                            String l_strWhereClose = " request_code = ? "    
                                + " and institution_code = ? "
                                + " and branch_code = ? "
                                + " and account_code = ? "
                                + " and order_request_number = ? "
                                + " and status = ? ";

                            Object[] l_objWhereClose =   
                            {    
                                WEB3HostRequestCodeDef.FUTURES_CHANGE_CANCEL_NOTICE,
                                l_notifyParams.getInstitutionCode(),
                                l_notifyParams.getBranchCode(),
                                l_notifyParams.getAccountCode(),
                                l_notifyParams.getOrderRequestNumber(),
                                WEB3StatusDef.DEALING
                            };   
        
                            List l_lisSearchResultClose =    
                                l_QueryProcessor.doFindAllQuery( 
                                    HostFotypeOrderClmdNotifyRow.TYPE,
                                    l_strWhereClose, 
                                    null,    
                                    "for update",    
                                    l_objWhereClose);    
        
                            if (l_lisSearchResultClose.size() > 0)   
                            {    
                                l_cancelParams = (HostFotypeOrderClmdNotifyParams)l_lisSearchResultClose.get(0);
                                l_cancelParams.setStatus(WEB3StatusDef.DATA_ERROR);
                                l_cancelParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                                l_QueryProcessor.doUpdateQuery(l_cancelParams);
                            }    
                        }        
        
                        //市場から確認済みの数量==nullの場合
                        if (l_errorInfo==WEB3ErrorCatalog.BUSINESS_ERROR_01975)
                        {
                            log.debug("該当注文は受付未済／変更の受付済／発注中の状態。");
                            l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                            l_QueryProcessor.doUpdateQuery(l_notifyParams);
                            continue;
                        }

                        //注文単位が取得出来ないの場合
                        if (l_errorInfo==WEB3ErrorCatalog.BUSINESS_ERROR_02752)
                        {
                            log.debug("注文単位が取得出来ない。");
                            l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                            l_QueryProcessor.doUpdateQuery(l_notifyParams);
                            continue;
                        }

                        //エラーがそれ以外の場合　@(=>エラー)        
                        log.debug("一件処理にてエラー発生：" + l_notifyParams.toString());       
                        l_notifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
                        l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_QueryProcessor.doUpdateQuery(l_notifyParams);      
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
                                log.debug("口座ロック失敗：" + l_notifyParams.toString());
                                continue;
                            }
                        }

                        //エラーがそれ以外の場合　@(=>エラー)
                        log.debug("一件処理にてエラー発生：" + l_notifyParams.toString());
                        l_notifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
                        l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_QueryProcessor.doUpdateQuery(l_notifyParams);
                    }
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (OP出来通知TransactionCallback)<BR>
         * コンストラクタ<BR>
         * @@roseuid 40874FA3035B
         */
        public WEB3FuturesOrderExecNotifyTransactionCallback()
        {
        }
    }
}@
