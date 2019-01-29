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
filename	WEB3EquityOrderExecNotifyMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式出来通知メインサービスImpl(WEB3EquityOrderExecNotifyMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 中尾寿彦(SRA) 新規作成
                   2005/01/05 岡村和明(SRA) 口座ロック対応
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2005/03/09 劉（FLJ）キューテーブルによる下り処理のトランザクション制御変更、
 　@　@　@　@　@　@　@　@　@　@非同期処理部分WEB3AsynEquityOrderExecNotifyMainServiceImplへ移す（新規クラス）
*/

package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.message.WEB3EquityExecNotifyMainRequest;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyMainService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;

/**
 * （株式出来通知メインサービス実装クラス）。
 * @@author  : 中尾寿彦
 * @@version : 1.0
 */
public class WEB3EquityOrderExecNotifyMainServiceImpl implements WEB3EquityOrderExecNotifyMainService
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderExecNotifyMainServiceImpl.class);

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3EquityOrderExecNotifyMainServiceImpl()
    {
    }

    /**
     * 株式出来通知メインサービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（株式出来通知メインサービス）出来通知」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3EquityOrderExecNotifyMainServiceImpl：execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityExecNotifyMainRequest l_execNotifyRequest =
            (WEB3EquityExecNotifyMainRequest)l_request;

        // 1.1. validate()
        l_execNotifyRequest.validate();
        // 1.2. スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_execNotifyRequest.threadNo.longValue());
        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynEquityOrderExecNotifyMainServiceImpl(
            l_execNotifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }

//>>>>>>以降処理WEB3AsynEquityOrderExecNotifyMainServiceImplへ移す。2005/03/09  劉（FLJ）
//        // 1.2. getDefaultProcessor()
//        try
//        {
//            Processors.getDefaultProcessor();
//        }
//        catch (DataFindException l_dfe)
//        {
//            log.error(l_dfe.getMessage(), l_dfe);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dfe.getMessage(), l_dfe);
//        }
//        catch (DataNetworkException l_dne)
//        {
//            log.error(l_dne.getMessage(), l_dne);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dne.getMessage(), l_dne);
//        }
//
//        // 1.3. 株式訂正取消通知メインTransactionCallback()
//        WEB3EquityOrderExecNotifyMainTransactionCallback l_transactionCallBack =
//            new WEB3EquityOrderExecNotifyMainTransactionCallback();
//
//        // 1.4. set識別コードプレフィクス一覧()
//        l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_execNotifyRequest.orderRequestNumberPrefixGroup);
//
//        // 1.5. doTransaction()
//        try
//        {
//            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
//            l_queryProcesser.doTransaction(
//                QueryProcessor.TX_CREATE_NEW,
//                l_transactionCallBack);
//        }
//        catch (DataFindException l_dfe)
//        {
//            log.error(l_dfe.getMessage(),l_dfe);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dfe.getMessage(), l_dfe);
//        }
//        catch (DataNetworkException l_dne)
//        {
//            log.error(l_dne.getMessage(),l_dne);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dne.getMessage(), l_dne);
//        }
//        catch (DataCallbackException l_dce)
//        {
//            log.error(l_dce.getMessage(),l_dce);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dce.getMessage(), l_dce);
//        }
//        catch (DataQueryException l_dqe)
//        {
//            log.error(l_dqe.getMessage(),l_dqe);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dqe.getMessage(), l_dqe);
//        }
//
//        log.exiting(STR_METHOD_NAME);
//        return l_request.createResponse();
//    }
//
//    /**
//     * (株式出来通知メインTransactionCallback)<BR>
//     * <BR>
//     * トランザクション処理を実施する内部クラス。<BR>
//     * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
//     */
//    public class WEB3EquityOrderExecNotifyMainTransactionCallback implements TransactionCallback
//    {
//        /**
//         * 識別コードプレフィクス一覧<BR>
//         */
//        private String[] orderRequestNumberPrefixGroup;
//
//        /**
//         * コンストラクタ。<BR>
//         */
//        public WEB3EquityOrderExecNotifyMainTransactionCallback()
//        {
//        }
//
//        /**
//         * (set引数の識別コードプレフィクス一覧)<BR>
//         * <BR>
//         * 引数の識別コードプレフィクス一覧をプロパティにセットする。<BR>
//         * @@params String[] - 識別コードプレフィクス一覧<BR>
//         */
//        public void setOrderRequestNumberPrefixGroup(String[] l_orderRequestNumberPrefixGroup)
//        {
//            orderRequestNumberPrefixGroup = l_orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * (get引数の識別コードプレフィクス一覧)<BR>
//         * <BR>
//         * 識別コードプレフィクス一覧を取得する。<BR>
//         * @@return String[]<BR>
//         */
//        public String[] getOrderRequestNumberPrefixGroup()
//        {
//            return orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * (process)<BR>
//         * <BR>
//         * トランザクション処理を実施する。<BR>
//         * <BR>
//         * シーケンス図<BR>
//         * 「（株式出来通知メインサービス）process」参照。<BR>
//         * @@return Object
//         * @@throws DataQueryException, DataNetworkException, IllegalStateException
//         */
//        public Object process()
//            throws DataQueryException, DataNetworkException, IllegalStateException
//        {
//            final String STR_METHOD_NAME = "process()";
//            log.entering(STR_METHOD_NAME);
//
//            // 1.2. 株式出来通知キューテーブル読み込み
//            StringBuffer l_sbWhere = new StringBuffer();
//            l_sbWhere.append("request_code = ? and status = ?");
//            int l_intLength = 0;
//            if (orderRequestNumberPrefixGroup != null)
//            {
//                l_intLength = orderRequestNumberPrefixGroup.length;
//            }
//            if (l_intLength > 0)
//            {
//                l_sbWhere.append(" and (");
//                for (int i = 0;i < l_intLength;i++)
//                {
//                    if (i > 0)
//                    {
//                        l_sbWhere.append(" or ");
//                    }
//                    l_sbWhere.append("order_request_number like ?");
//                }
//                l_sbWhere.append(")");
//            }
//            Object[] l_objWhere = new Object[l_intLength + 2];
//            l_objWhere[0] = WEB3HostRequestCodeDef.EQUITY_ORDER_EXEC_NOTICE;
//            l_objWhere[1] = WEB3StatusDef.NOT_DEAL;
//            for (int i = 0;i < l_intLength;i++)
//            {
//                l_objWhere[i + 2] = orderRequestNumberPrefixGroup[i] + "%";
//            }
//            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
//            List l_lisSearchResult = l_QueryProcessor.doFindAllQuery(
//                HostEquityOrderExecNotifyRow.TYPE,
//                l_sbWhere.toString(),
//                null,
//                "for update",
//                l_objWhere);
//
//            // 1.3. 取得したキューテーブルのレコード数分、Loop
//            HostEquityOrderExecNotifyParams l_params = null;
//
//            int l_intNum = l_lisSearchResult.size();
//
//            for (int i = 0; i < l_intNum;i++)
//            {
//                try
//                {
//                    log.debug("株式出来通知メイン：Loop処理 i = " + i);
//
//                    l_params = (HostEquityOrderExecNotifyParams)l_lisSearchResult.get(i);
//                    log.debug("　@　@識別コード： [" + l_params.getOrderRequestNumber() + "]");
//                    // 1.3.1. get注文単位(証券会社コード, 部店コード, 商品タイプ, 識別番号)
//                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//                    WEB3EquityOrderManager l_orderManager =
//                        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
//                    EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(
//                        l_params.getInstitutionCode(),
//                        l_params.getBranchCode(),
//                        ProductTypeEnum.EQUITY,
//                        l_params.getOrderRequestNumber()
//                    );
//                    log.debug("　@　@注文単位ID： [" + l_orderUnit.getOrderUnitId() + "]");
//
//                    // 株式出来通知一件TransactionCallback生成
//                    WEB3EquityOrderExecNotifyTransactionCallback l_transactionCallback =
//                        new WEB3EquityOrderExecNotifyTransactionCallback(l_orderUnit, l_params);
//
//                    // doTransaction()
//                    l_QueryProcessor.doTransaction(
//                        QueryProcessor.TX_CREATE_NEW,
//                        l_transactionCallback);
//
//                    l_params.setStatus(WEB3StatusDef.DEALT);
//                    l_QueryProcessor.doUpdateQuery(l_params);
//                }
//                catch (WEB3BaseException l_exp)
//                {
//                    log.error(l_exp.getMessage(), l_exp);
//                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
//                    l_QueryProcessor.doUpdateQuery(l_params);
//                }
//                catch (DataCallbackException l_exp)
//                {
//                    ErrorInfo l_errorInfo = (ErrorInfo)l_exp.getDetails();
//                    if (l_errorInfo.equals(WEB3ErrorCatalog.BUSINESS_ERROR_01734))
//                    {
//                        // 注文訂正中のキューは出来処理スキップ
//                        log.debug("出来処理スキップ（該当注文訂正中） " + l_params.toString());
//                        continue;
//                    }
//                    else if (l_errorInfo.equals(WEB3ErrorCatalog.SYSTEM_ERROR_80076))
//                    {
//                        log.debug("口座ロック失敗：" + l_params.toString());
//                        continue;
//                    }
//                    else if (l_errorInfo.equals(WEB3ErrorCatalog.BUSINESS_ERROR_01931))
//                    {
//                        log.debug("保有資産残数量チェックエラー：" + l_params.toString());
//                        continue;
//                    }
//                    else if (l_errorInfo.equals(WEB3ErrorCatalog.BUSINESS_ERROR_01934))
//                    {
//                        log.debug("建株残数量チェックエラー：" + l_params.toString());
//                        continue;
//                    }
//
//                    log.error(l_exp.getMessage(),l_exp);
//                    if (l_errorInfo.error_class.equals(WEB3BusinessLayerException.class.getName()))
//                    {
//	                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
//                    }
//                    else
//                    {
//	                    l_params.setStatus(WEB3StatusDef.PROGRAM_ERROR);
//                    }
//                    l_QueryProcessor.doUpdateQuery(l_params);
//                }
//                catch (Exception l_exp)
//                {
//                    log.error(l_exp.getMessage(), l_exp);
//	                l_params.setStatus(WEB3StatusDef.PROGRAM_ERROR);
//	                l_QueryProcessor.doUpdateQuery(l_params);
//                }
//            }
//
//            log.exiting(STR_METHOD_NAME);
//            return null;
//        }
//    }

}
@
