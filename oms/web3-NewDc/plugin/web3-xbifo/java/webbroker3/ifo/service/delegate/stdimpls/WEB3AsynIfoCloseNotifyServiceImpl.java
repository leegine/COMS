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
filename	WEB3AsynIfoCloseNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP失効通知サービスImpl(WEB3AsynIfoCloseNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 同期版履歴：
Revesion History : 2004/6/18 盧法@旭 (中訊) 新規作成
Revesion History : 001: 2004/07/23 王暁傑 (中訊) WEB3HostRequestCodeDefでWEB3IfoRequestCodeTypeDefを差し替える
Revesion History : 002: 2004/07/29 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000063 execute()を修正
Revesion History : 003: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
Revesion History : 非同期対応版履歴：
Revesion History : 2005/03/11 李志強　@非同期実行対応（新規クラス）
Revesion History : 2007/04/24 孟亜南(中訊) 仕様変更モデルNo.636
Revesion History : 2008/03/20 金傑(中訊) 仕様変更モデルNo.852
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.ArrayList;
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
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyRow;
import webbroker3.ifo.message.WEB3IfoCloseOrderRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * （非同期対応先物OP失効通知メインサービス実装クラス）。
 * @@author  : 李志強（日本中訊）
 * @@version : 1.0
 */
public class WEB3AsynIfoCloseNotifyServiceImpl
    implements Runnable
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynIfoCloseNotifyServiceImpl.class);

    /**
     * 失効通知リクエスト
     */
    private WEB3IfoCloseOrderRequest request;


    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3AsynIfoCloseNotifyServiceImpl(WEB3IfoCloseOrderRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynIfoCloseNotifyServiceImpl：run()";
        log.entering(STR_METHOD_NAME);

        //2: クエリプロセッサのインスタンスを取得する
        QueryProcessor l_queryProcessor= null;
        try
        {
            WEB3IfoCloseOrderRequest l_closeOrderRequest =
                (WEB3IfoCloseOrderRequest) request;

            try
            {
                log.debug("Get the object Processor.");
                l_queryProcessor = Processors.getDefaultProcessor();
                log.debug("Succeeded to get the object.");
            }
            catch (DataFindException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                      WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                      this.getClass().getName() + STR_METHOD_NAME);
            }
            //3: 先物OP失効通知TransactionCallback
            WEB3IfoCloseNotifyTransactionCallback l_callBack =
                new WEB3IfoCloseNotifyTransactionCallback();

            //set識別コードプレフィクス一覧()
            l_callBack.setOrderRequestNumberPrefixGroup(l_closeOrderRequest.orderRequestNumberPrefixGroup);

            //4: メッセージ 処理対象パラメータ（4/22詳細未定）セット
            //5: DBトランザクション処理を実施する
            try
            {
                log.debug("Enter the try path:db update.");
                l_queryProcessor.doTransaction(TransactionalInterceptor.TX_CREATE_NEW,l_callBack);
                log.debug("Succeeded to  update the database.");
            }
            catch (DataCallbackException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataRollbackException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataQueryException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME);
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
     * 先物OP失効通知TransactionCallback<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     */
    public class WEB3IfoCloseNotifyTransactionCallback implements TransactionCallback
    {
        /**
         * 識別コードプレフィクス一覧<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * (set識別コードプレフィクス一覧)<BR>
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
         * (get識別コードプレフィクス一覧)<BR>
         * <BR>
         * 識別コードプレフィクス一覧を取得する。<BR>
         * @@return String[]<BR>
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return orderRequestNumberPrefixGroup;
        }
        
        /**
         * デフォルトコンストラクタ
         * @@return webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyServiceImpl.WEB3IfoCloseNotifyTransactionCallback
         * @@roseuid 4088F56A0131
         */
        public WEB3IfoCloseNotifyTransactionCallback()
        {

        }

        /**
         * 失効通知処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（先物OP失効通知）process」参照。<BR>
         * <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 4088F56A0122
         */
        public Object process() throws DataNetworkException, DataQueryException
        {
            final String STR_METHOD_NAME = "Object process()";
            log.entering(STR_METHOD_NAME);
            //約定数量
            double l_dblExecQuantity  = 0;

            //失効理由コード
            String l_strCloseReasonCode = null;

            //get失効通知区分
            String l_strCloseNotifyType = null;

            //OrderUnit
            OrderUnit l_orderUnit = null;

            HostFotypeCloseOrderNotifyParams l_orderAcceptedParams = null;


            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("(request_code = ?");
            l_sbWhere.append("or request_code = ?)");
            l_sbWhere.append(" and status= ?");

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

            Object[] l_objWhere = new Object[l_intPrefixGroupLength + 3];
            l_objWhere[0] = WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE;
            l_objWhere[1] = WEB3HostRequestCodeDef.FUTURES_CLOSE_NOTICE;
            l_objWhere[2] = WEB3StatusDef.NOT_DEAL;
            for (int i = 0; i < l_intPrefixGroupLength; i++)
            {
                l_objWhere[i + 3] = orderRequestNumberPrefixGroup[i] + "%";
            }

            List l_listRecords = new ArrayList();

            QueryProcessor l_queryProcessor = null;
            try
            {
                log.debug("Get the processor object.");
                l_queryProcessor = Processors.getDefaultProcessor();
                log.debug("Succeeded to get the project processor.");

                log.debug("Get the records that satisfys the specified condition.");
                l_listRecords = l_queryProcessor.doFindAllQuery(
                    HostFotypeCloseOrderNotifyRow.TYPE,
                    l_sbWhere.toString() ,
                    null,
                    null,
                    l_objWhere) ;
                log.debug("Succeeded to get the records that satisfied th specified conditon.");

            }
            catch (DataFindException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            catch (DataQueryException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);

            }


            int l_intLength;
            l_intLength = l_listRecords.size();
            log.debug("the number of records:" + l_intLength);
            log.debug("The number of records that searched just for:" + l_intLength);
            if (l_intLength > 0)
            {
                for(int i=0 ;i < l_intLength; i++)
                {
                    try
                    {
                        l_orderAcceptedParams = new HostFotypeCloseOrderNotifyParams((HostFotypeCloseOrderNotifyRow)l_listRecords.get(i));

                        String l_strInstitutionCode = l_orderAcceptedParams.getInstitutionCode();

                        String l_strBranchCode = l_orderAcceptedParams.getBranchCode();
                        log.debug("The BranchCode is :" + l_strBranchCode);
                        String l_strAccountCode = l_orderAcceptedParams.getAccountCode();
                        log.debug("The Account Code is:" + l_strAccountCode);

                        //5: インタセプタをOrderManagerにセットする
                        //取得OrderManager
                        TradingModule l_tradingMod =
                            l_finApp.getTradingModule(ProductTypeEnum.IFO);
                        WEB3OptionOrderManagerImpl l_orderMgr =
                            (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
                        //6: get注文単位
                        String l_strOrderRequestNumber = l_orderAcceptedParams.getOrderRequestNumber();
                        log.debug("get注文単位: " + l_strOrderRequestNumber);
                        l_orderUnit = l_orderMgr.getOrderUnit(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            ProductTypeEnum.IFO,
                            l_strOrderRequestNumber);
                        if (l_orderUnit == null)
                        {
                            log.error("The orderUnit is null.");
                            throw new WEB3BaseRuntimeException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }

                        //7:notify失効
                        //約定数量
                        // 失効通知キュー.失効数量 ! = NULL の場合
                        if (!l_orderAcceptedParams.getCloseQuantityIsNull())
                        {
                            // 注文単位.市場から確認済みの数量-失効通知キュー.失効数量
                            l_dblExecQuantity =
                                l_orderUnit.getConfirmedQuantity() - l_orderAcceptedParams.getCloseQuantity();
                        }
                        // 失効通知キュー.失効数量 = NULL の場合
                        else
                        {
                            // 先物OP失効通知キューテーブル.約定数量
                            l_dblExecQuantity = l_orderAcceptedParams.getExecutedQuantity();
                        }

                        if (Double.isNaN(l_dblExecQuantity))
                        {
                            l_dblExecQuantity = 0D;
                        }
                        log.debug("約定数量:" + l_dblExecQuantity);
                        //失効理由コード
                        l_strCloseReasonCode = l_orderAcceptedParams.getReasonCode();
                        log.debug("失効理由コード" + l_strCloseReasonCode);
                        //get失効通知区分
                        l_strCloseNotifyType = l_orderAcceptedParams.getCloseNotifyType();
                        log.debug("失効通知区分:" + l_strCloseNotifyType);

                        // TransactionCallback生成
                        WEB3IfoCloseNotifyNotifyCloseTransactionCallback l_transactionCallback =
                            new WEB3IfoCloseNotifyNotifyCloseTransactionCallback(
                                l_orderUnit,
                                l_dblExecQuantity,
                                l_strCloseReasonCode,
                                l_strCloseNotifyType,
                                l_orderAcceptedParams);

                        // doTransaction()
                        l_queryProcessor.doTransaction(
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
                                log.debug("口座ロック失敗：" + l_orderAcceptedParams.toString());
                                continue;
                            }
                        }
                        
                        if (l_exp instanceof DataCallbackException)
                        {
                            DataCallbackException l_wre = (DataCallbackException) l_exp;
                            if (WEB3ErrorCatalog.BUSINESS_ERROR_01975.equals((ErrorInfo)l_wre.getDetails()))
                            {
                                log.debug("該当注文は受付未済／変更の受付済／発注中の状態：" +
                                    l_orderAcceptedParams.toString());
                                l_orderAcceptedParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                                l_queryProcessor.doUpdateQuery(l_orderAcceptedParams);
                                continue;
                            }
                        }

                        //エラーがそれ以外の場合　@(=>エラー)
                        log.debug("一件処理にてエラー発生：" + l_orderAcceptedParams.toString());
                        l_orderAcceptedParams.setStatus(WEB3StatusDef.DATA_ERROR);
                        l_orderAcceptedParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_orderAcceptedParams);
                    }
                }
            }
        log.debug("Exit the one loop.");
        log.exiting(STR_METHOD_NAME);
        return null;
        }
    }

}






@
