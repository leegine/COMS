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
filename	WEB3EquityReceiveCloseOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式失効通知サービスImpl(WEB3EquityReceiveCloseOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 李綱 (中訊) 新規作成
                   2004/09/20 鄒政 (中訊) 修正
                   2004/12/13 水落 (SRA) 残案件対応のため修正
                   2005/01/06 岡村 (SRA) JavaDoc修正
                   2005/03/09 劉（FLJ）キューテーブルによる下り処理のトランザクション制御変更、
 　@　@　@　@　@　@　@　@　@　@非同期処理部分WEB3AsynEquityReceiveCloseOrderServiceImplへ移す（新規クラス）

*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.message.WEB3EquityCloseOrderRequest;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
/**
 * （株式失効通知サービスImpl）。<BR>
 * <BR>
 * 株式失効通知サービス実装クラス。<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@author 鄒政
 * @@version 1.0
 */
public class WEB3EquityReceiveCloseOrderServiceImpl
    implements WEB3EquityReceiveCloseOrderService
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveCloseOrderServiceImpl.class);

    /**
     * @@roseuid 40AD626100FC
     */
    public WEB3EquityReceiveCloseOrderServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * 失効処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（失効通知サービス）失効通知」参照<BR>
     * @@param l_request - 失効通知リクエストオブジェクト
     * @@return WEB3EquityCloseOrderResponse
     * @@throws WEB3BaseException
     * @@roseuid 4137CFEA0176
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1 validate()
        WEB3EquityCloseOrderRequest l_equityCloseOrderRequest = (WEB3EquityCloseOrderRequest)l_request;
        l_equityCloseOrderRequest.validate();

        // 1.2. スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_equityCloseOrderRequest.threadNo.longValue());
        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynEquityReceiveCloseOrderServiceImpl(
            l_equityCloseOrderRequest));

        log.exiting(STR_METHOD_NAME);
        return l_equityCloseOrderRequest.createResponse();
    }
//>>>>>>以降処理WEB3AsynEquityReceiveCloseOrderServiceImplへ移す。2005/03/09  劉（FLJ）

//        // 株式失効通知処理を行う
//        try
//        {
//            //1.2 getDefaultProcesser
//            QueryProcessor l_qp = Processors.getDefaultProcessor();
//
//            //1.3 株式失効通知TransactionCallback()
//            WEB3EquityReceiveCloseOrderTransactionCallback l_callback
//                         = new WEB3EquityReceiveCloseOrderTransactionCallback();
//
//            //1.4 set識別コードプレフィクス (識別コードプレフィクス一覧 : String[])
//            l_callback.setOrderRequestNumberPrefixGroup(l_equityCloseOrderRequest.orderRequestNumberPrefixGroup);
//
//            //1.5 doTransaction()（1件ごとにコミットするようにトランザクション属性を設定。）
//            l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callback);
//        }
//        catch (DataException l_exp)
//        {
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_exp.getMessage(),
//                l_exp);
//        }
//
//        log.exiting(STR_METHOD_NAME);
//
//        return l_request.createResponse();
//    }
//
//    /**
//     * (株式失効通知TransactionCallback)<BR>
//     * 株式失効通知ハンドラ処理を行うトランザクション・コールバッククラス。<BR>
//     */
//    private class WEB3EquityReceiveCloseOrderTransactionCallback
//        implements TransactionCallback
//    {
//
//        /**
//         * (識別コードプレフィクス一覧)<BR>
//         */
//        private String[] orderRequestNumberPrefixGroup;
//
//        /**
//         * (株式失効通知TransactionCallback)<BR>
//         * 株式失効通知TransactionCallbackクラス<BR>
//         * コンストラクタ。
//         * @@return
//         * webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderTransactionCallback
//         * .WEB3EquityReceiveCloseOrderTransactionCallback
//         */
//        public WEB3EquityReceiveCloseOrderTransactionCallback()
//        {
//
//        }
//
//        /**
//         * トランザクション処理を実施する。<BR>
//         * <BR>
//         * シーケンス図<BR>
//         * 「（失効通知サービス）process」参照。<BR>
//         * @@return WEB3BaseException（内部処理にてWEB3BaseException例外が発生した場合）
//         * @@throws DataNetworkException
//         * @@throws DataQueryException
//         * @@throws DataCallbackException
//         * @@roseuid 4137CFEA0290
//         */
//        public Object process()
//            throws DataNetworkException, DataQueryException, DataCallbackException
//        {
//            final String STR_METHOD_NAME =
//                "WEB3EquityReceiveCloseOrderTransactionCallback.process()";
//            log.entering(STR_METHOD_NAME);
//
//            HostEqtypeCloseOrderNotifyRow l_hostEqtypeCloseOrderNotifyRow;
//            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams;
//
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
//            WEB3EquityOrderManager l_orderMgr =
//                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
//
//            // ----------------------------------
//            // 1.1 get識別コードプレフィクス一覧()
//            // ----------------------------------
//            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();
//
//            // ----------------------------------
//            // 1.2 株式失効通知キューテーブル検索
//            // ----------------------------------
//            //     データコード =  株式失効通知
//            //     処理区分 = 未処理
//            //     識別コードの先頭2桁が、get識別コードプレフィクス一覧()の戻り値配列のいずれかと一致
//            String l_strWhere = "request_code=? and status=?";
//            String l_strCondition = "for update";
//
//            int l_intLength = 0;
//            if (l_orderRequestNumberPrefixGroup != null)
//            {
//                l_intLength = l_orderRequestNumberPrefixGroup.length;
//            }
//            if (l_intLength > 0)
//            {
//                l_strWhere = l_strWhere + " and (";
//                for(int i = 0;i < l_intLength;i++)
//                {
//                    if (i == 0)
//                    {
//                        l_strWhere = l_strWhere + "order_request_number like ?";
//                    }
//                    else if (0 < i)
//                    {
//                        l_strWhere = l_strWhere + " or order_request_number like ?";
//                    }
//                    log.debug("識別コードプレフィクス一覧：" + l_orderRequestNumberPrefixGroup[i]);
//                }
//                l_strWhere = l_strWhere + ")";
//            }
//            log.debug("検索条件：[" + l_strWhere + "]");
//
//            Object l_objWhere[] = new Object[l_intLength + 2];
//            l_objWhere[0] = WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE;
//            l_objWhere[1] = WEB3HostStatusDef.NOT_STARTED_PROCESS;
//            for (int i = 0;i < l_intLength;i++)
//            {
//                l_objWhere[i + 2] = l_orderRequestNumberPrefixGroup[i] + "%";
//            }
//            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//            List l_lisRecords =
//                l_queryProcessor.doFindAllQuery(
//                    HostEqtypeCloseOrderNotifyRow.TYPE,
//                    l_strWhere,
//                    l_strCondition,
//                    l_objWhere);
//
//            //取得したキューレコード数分のLoop処理
//            int l_intSize = l_lisRecords.size();
//            for (int i = 0; i < l_intSize; i++)
//            {
//                log.debug("株式失効通知：Loop処理 i = " + i);
//
//                //処理区分
//                String l_strStatus;
//
//                //get 株式失効通知キューParams
//                l_hostEqtypeCloseOrderNotifyRow =
//                    (HostEqtypeCloseOrderNotifyRow)l_lisRecords.get(i);
//                l_hostEqtypeCloseOrderNotifyParams =
//                    new HostEqtypeCloseOrderNotifyParams(l_hostEqtypeCloseOrderNotifyRow);
//                log.debug("　@　@識別コード： [" + l_hostEqtypeCloseOrderNotifyParams.getOrderRequestNumber() + "]");
//
//                try
//                {
//                    // ---------------------------------
//                    // 1.3.2. 失効対象注文単位を取得する
//                    // ---------------------------------
//                    //       証券会社コード：　@株式失効通知キューParams.証券会社コード
//                    //       部店コード：　@株式失効通知キューParams.部店コード
//                    //       商品タイプ：　@ProductTypeEnum.株式
//                    //       識別コード：　@株式失効通知キューParams.識別コード
//                    EqTypeOrderUnit l_eqtypeOrderUnit = l_orderMgr.getOrderUnit(
//                        l_hostEqtypeCloseOrderNotifyParams.getInstitutionCode(),
//                        l_hostEqtypeCloseOrderNotifyParams.getBranchCode(),
//                        ProductTypeEnum.EQUITY,
//                        l_hostEqtypeCloseOrderNotifyParams.getOrderRequestNumber());
//                    log.debug("　@　@注文単位ID： [" + l_eqtypeOrderUnit.getOrderUnitId() + "]");
//
//                    //出来通知待ちの場合
//                    EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();
//                    if (l_orderUnitRow.getExecutedQuantity() < l_hostEqtypeCloseOrderNotifyParams.getExecutedQuantity())
//                    {
//                        //set  処理区分 = 処理中
//                        l_strStatus = WEB3StatusDef.DEALING;
//                    }
//                    else
//                    {
//                        //set  処理区分 = 処理済
//                        l_strStatus = WEB3StatusDef.DEALT;
//                    }
//
//                    WEB3EquityReceiveCloseOrderUnitTransactionCallback l_callback =
//                        new WEB3EquityReceiveCloseOrderUnitTransactionCallback(
//                            l_hostEqtypeCloseOrderNotifyParams,
//                            l_eqtypeOrderUnit);
//
//                    l_queryProcessor.doTransaction(
//                        QueryProcessor.TX_CREATE_NEW,
//                        l_callback);
//                }
//                catch (DataException l_dex)
//                {
//                    log.debug("株式注文失効処理にエラーの場合", l_dex);
//                    //set 処理区分 = エラー
//                    l_strStatus = WEB3StatusDef.DATA_ERROR;
//                }
//                catch (WEB3BaseException wbe)
//                {
//                    log.debug("株式注文失効処理にエラーの場合",wbe);
//                    //set 処理区分 = エラー
//                    l_strStatus = WEB3StatusDef.DATA_ERROR;
//                }
//
//                // ------------------------------------------------------------
//                // 1.3.3. 株式失効通知キューテーブル.処理区分をupdateしcommitする
//                // ------------------------------------------------------------
//                l_hostEqtypeCloseOrderNotifyParams.setStatus(l_strStatus);
//                l_queryProcessor.doUpdateQuery(l_hostEqtypeCloseOrderNotifyParams);
//            }
//
//            log.exiting(STR_METHOD_NAME);
//            return null;
//        }
//
//
//        /**
//         * (get識別コードプレフィクス一覧)<BR>
//         * <BR>
//         * 識別コードプレフィクス一覧を取得する。
//         * @@return 識別コードプレフィクス一覧
//         */
//        public String[] getOrderRequestNumberPrefixGroup()
//        {
//            return this.orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * (set識別コードプレフィクス一覧)<BR>
//         * <BR>
//         * 引数の識別コードプレフィクス一覧をプロパティにセットする。
//         * @@param orderRequestNumberPrefixGroup 識別コードプレフィクス一覧
//         */
//        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup)
//        {
//            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
//        }
//    }
//
//    /**
//     * (株式失効通知一件TransactionCallback)<BR>
//     * <BR>
//     * 株式失効通知一件TransactionCallbackクラス。<BR>
//     */
//    public class WEB3EquityReceiveCloseOrderUnitTransactionCallback
//        implements TransactionCallback
//    {
//        /**
//         * (株式失効通知キューParams。)
//         */
//        HostEqtypeCloseOrderNotifyParams hostEqtypeCloseOrderNotifyParams;
//
//        /**
//         * (注文単位。)
//         */
//        EqTypeOrderUnit orderUnit;
//
//        /**
//         * (株式失効通知一件TransactionCallback)<BR>
//         * <BR>
//         * コンストラクタ。<BR>
//         * 引数の値を同名プロパティにセットする。<BR>
//         * @@param l_hostEqtypeCloseOrderNotifyParams - (株式失効通知キューParams)<BR>
//         * 【株式失効通知キューテーブル】の１レコード。<BR>
//         * @@param l_orderUnit - (注文単位)<BR>
//         * 注文単位オブジェクト<BR>
//         */
//        public WEB3EquityReceiveCloseOrderUnitTransactionCallback(
//            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams,
//            EqTypeOrderUnit l_orderUnit)
//        {
//            hostEqtypeCloseOrderNotifyParams = l_hostEqtypeCloseOrderNotifyParams;
//            orderUnit = l_orderUnit;
//        }
//
//        /**
//         * (process)<BR>
//         * <BR>
//         * トランザクション処理を実施する。<BR>
//         *
//         * シーケンス図<BR>
//         * 「（失効通知サービス）process」参照。<BR>
//
//         */
//        public Object process()
//            throws DataNetworkException, DataQueryException, DataCallbackException
//        {
//            final String STR_METHOD_NAME = "process()";
//            log.entering(STR_METHOD_NAME);
//
//            try
//            {
//                // exec失効()
//                WEB3EquityReceiveCloseOrderUnitService l_service =
//                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(WEB3EquityReceiveCloseOrderUnitService.class);
//                l_service.execCloseOrder(hostEqtypeCloseOrderNotifyParams, orderUnit);
//
//                // 余力再計算（）
//                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//                WEB3GentradeAccountManager l_accountManager =
//                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
//                WEB3GentradeSubAccount l_subAccount = null;
//                l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
//                    orderUnit.getAccountId(),
//                    orderUnit.getSubAccountId());
//                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
//                    (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
//                l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
//            }
//            catch (NotFoundException l_nfe)
//            {
//                log.error(l_nfe.getMessage(), l_nfe);
//                throw new DataCallbackException();
//            }
//            catch (WEB3BaseException l_exp)
//            {
//                log.error(STR_METHOD_NAME, l_exp);
//                throw new DataCallbackException();
//            }
//            log.exiting(STR_METHOD_NAME);
//            return null;
//        }
//    }
}
@
