head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynEquityChangeCancelNotifyMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正取消通知メインサービスImpl(WEB3AsynEquityChangeCancelNotifyMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 中尾寿彦(SRA) 新規作成
Revesion History : 2005/01/05 岡村和明(SRA) 口座ロック対応
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2005/03/23 劉（FLJ）非同期実行対応（新規クラス）
Revesion History : 2006/11/06 唐性峰　@(中訊)モデルNo.1032
Revesion History : 2007/04/24 肖志偉　@(中訊)モデルNo.1139
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptRow;
import webbroker3.equity.message.WEB3EquityChangeCancelNotifyMainRequest;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCancelEventService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveChangeEventService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyCancelUnitService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyChangeUnitService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;

/**
 * （株式訂正取消通知メインサービス実装クラス）。
 * @@author 中尾寿彦
 * @@version 1.0
 */
public class WEB3AsynEquityChangeCancelNotifyMainServiceImpl
     implements Runnable
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynEquityChangeCancelNotifyMainServiceImpl.class);

    /**
     *　@株式訂正取消通知処理リクエスト
     */
    private WEB3EquityChangeCancelNotifyMainRequest l_changeCancelNotifyRequest;


    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3AsynEquityChangeCancelNotifyMainServiceImpl(WEB3EquityChangeCancelNotifyMainRequest l_changeCancelNotifyRequest)
    {
        this.l_changeCancelNotifyRequest = l_changeCancelNotifyRequest;
    }

    /**
     * 株式訂正取消通知メインサービス処理を実施する。<BR>
     * <BR>
     * シーケンス図「（株式訂正取消通知メインサービス）訂正取消通知」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public void run()
    {
        final String STR_METHOD_NAME = "WEB3AsynEquityChangeCancelNotifyMainServiceImpl：run(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        // 株式訂正取消受付処理を行う
        try
        {

            // 1.2. getDefaultProcessor()
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
                    l_dfe.getMessage(), l_dfe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(), l_dne);
            }

            // 1.3. 株式訂正取消通知メインTransactionCallback()
            WEB3EquityChangeCancelNotifyMainTransactionCallback l_transactionCallBack =
                new WEB3EquityChangeCancelNotifyMainTransactionCallback();

            // 1.4. set識別コードプレフィクス一覧()
            l_transactionCallBack.setOrderRequestNumberPrefixGroup(
                l_changeCancelNotifyRequest.orderRequestNumberPrefixGroup);

            // 1.5. doTransaction()
            try
            {
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                l_queryProcesser.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    l_transactionCallBack);
            }
            catch (DataFindException l_dfe)
            {
                log.error(l_dfe.getMessage(), l_dfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dfe.getMessage(), l_dfe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(), l_dne);
            }
            catch (DataCallbackException l_dce)
            {
                log.error(l_dce.getMessage(), l_dce);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dce.getMessage(), l_dce);
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
        catch (Exception l_e)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e));
        }

        //スレッド開放
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(l_changeCancelNotifyRequest.threadNo.longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * （株式訂正取消通知メインTransactionCallback)<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
     */
    public class WEB3EquityChangeCancelNotifyMainTransactionCallback implements TransactionCallback
    {
        /**
         * 識別コードプレフィクス一覧<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * コンストラクタ。<BR>
         */
        public WEB3EquityChangeCancelNotifyMainTransactionCallback()
        {
        }

        /**
         * （set引数の識別コードプレフィクス一覧）<BR>
         * <BR>
         * 引数の識別コードプレフィクス一覧をプロパティにセットする。<BR>
         * @@params String[] - 識別コードプレフィクス一覧<BR>
         */
        public void setOrderRequestNumberPrefixGroup(String[] l_orderRequestNumberPrefixGroup)
        {
            orderRequestNumberPrefixGroup = l_orderRequestNumberPrefixGroup;
        }

        /**
         * （get引数の識別コードプレフィクス一覧）<BR>
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
         * 「（株式訂正取消通知メインサービス）process」参照。<BR>
         * @@return Object
         * @@throws DataQueryException, DataNetworkException, DataCallbackException
         */
        public Object process()
            throws DataQueryException, DataNetworkException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            // 1.2. 株式注文訂正取消通知キューテーブル読み込み
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("request_code = ? and status = ?");
            int l_intLength = 0;
            if (orderRequestNumberPrefixGroup != null)
            {
                l_intLength = orderRequestNumberPrefixGroup.length;
            }
            if (l_intLength > 0)
            {
                l_sbWhere.append(" and (");
                for (int i = 0;i < l_intLength;i++)
                {
                    if (i > 0)
                    {
                        l_sbWhere.append(" or ");
                    }
                    l_sbWhere.append("order_request_number like ?");
                }
                l_sbWhere.append(")");
            }
            Object[] l_objWhere = new Object[l_intLength + 2];
            l_objWhere[0] = WEB3HostRequestCodeDef.EQUITY_CHANGE_CANCEL_NOTICE;
            l_objWhere[1] = WEB3StatusDef.NOT_DEAL;
            for (int i = 0;i < l_intLength;i++)
            {
                l_objWhere[i + 2] = orderRequestNumberPrefixGroup[i] + "%";
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
                HostEqtypeOrderClmdReceiptRow.TYPE,
                l_sbWhere.toString(),
                null,
                null,
                l_objWhere);

            // 1.3. 取得したキューテーブルのレコード数分、Loop
            int l_intNum = 0;
            if (l_lisSearchResult != null)
            {
                l_intNum = l_lisSearchResult.size();
            }
            for (int i = 0;i < l_intNum; i++)
            {
                log.debug("株式訂正取消通知メイン：Loop処理 i = " + i);

                HostEqtypeOrderClmdReceiptParams l_params =
                    (HostEqtypeOrderClmdReceiptParams)l_lisSearchResult.get(i);
                log.debug("　@　@識別コード： [" + l_params.getOrderRequestNumber() + "]");
                try
                {
                    //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                    WEB3GentradeAccountManager l_accountManager =
                        (WEB3GentradeAccountManager) l_finApp.getAccountManager();

                    try
                    {
                        l_accountManager.getMainAccount(
                            l_params.getInstitutionCode(),
                            l_params.getBranchCode(),
                            l_params.getAccountCode());
                    }
                    catch(WEB3BaseException l_ex)
                    {
                        log.debug(l_ex.getMessage(), l_ex);
                        l_params.setStatus(WEB3StatusDef.DEALT);
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_params);
                        continue;
                    }

                    // 1.3.1. 株式訂正取消通知一件TransactionCallback()
                    WEB3EquityChangeCancelNotifyUnitTransactionCallback l_transactionCallBack =
                        new WEB3EquityChangeCancelNotifyUnitTransactionCallback(l_params);

                    // 1.3.2. doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallBack);

                    // 1.3.5. 訂正取消通知キューテーブル.処理区分をupdateする
                    // 通知キューParams.訂正取消通知区分 == ”訂正完了” または　@”訂正失敗”
                    //ステータス更新は株式訂正取消通知一件TransactionCallback()内へ移す .start
                    //if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_params.getCanmodReceiptType()) ||
                    //    WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_params.getCanmodReceiptType()))
                    //{
                    //    l_params.setStatus(WEB3StatusDef.DEALT);
                    //}
                    //else
                    //{
                    //    l_params.setStatus(l_strStatus);
                    //}
                    //l_queryProcessor.doUpdateQuery(l_params);
                    // .end
                }
                catch (DataCallbackException l_dce)
                {
                    log.error(l_dce.getMessage(), (WEB3BaseException)l_dce.getDetails());
                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                }
                catch (DataException l_de)
                {
                    log.error(l_de.getMessage(), l_de);
                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                }
                catch (Exception l_e)
                {
                    if (l_e instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_e;
                        if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
                        {
                            log.debug("口座ロック失敗：" + l_params.toString());
                            continue;
                        }
                    }
                    log.error(l_e.getMessage(), l_e);
                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (株式訂正取消通知一件TransactionCallback。)<BR>
     */
    public class WEB3EquityChangeCancelNotifyUnitTransactionCallback implements TransactionCallback
    {
        /**
         * (株式訂正取消通知キューParams)<BR>
         * 株式訂正取消通知キューParams。<BR>
         */
        private HostEqtypeOrderClmdReceiptParams hostEqtypeOrderClmdReceiptParams;

        /**
         * (注文単位)<BR>
         * 注文単位。<BR>
         */
        private EqTypeOrderUnit orderUnit;

        /**
         * (株式訂正取消通知一件TransactionCallback)<BR>
         * <BR>
         * コンストラクタ。<BR>
         * 引数の値を同名プロパティにセットする。<BR>
         * <BR>
         * @@param l_hostEqtypeOrderClmdReceiptParams - (株式訂正取消通知キューParams)<BR>
         * 【株式注文訂正取消通知キューテーブル】の１レコード。<BR>
         * @@param l_orderUnit - (注文単位)<BR>
         * 注文単位オブジェクト<BR>
         */
        public WEB3EquityChangeCancelNotifyUnitTransactionCallback(
            HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams)
        {
            this.hostEqtypeOrderClmdReceiptParams = l_hostEqtypeOrderClmdReceiptParams;
        }

        /**
         * (process)<BR>
         * <BR>
         * トランザクション処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（株式訂正取消通知メインサービス）process」参照。<BR>
         * <BR>
         * @@return Object
         * @@throws DataQueryException, DataNetworkException, DataCallbackException
         */
        public Object process()
            throws DataQueryException, DataNetworkException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            String l_strStatus = null;

            try
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager = 
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                    
                // lock口座
                l_accountManager.lockAccount(
                    hostEqtypeOrderClmdReceiptParams.getInstitutionCode(), 
                    hostEqtypeOrderClmdReceiptParams.getBranchCode(), 
                    hostEqtypeOrderClmdReceiptParams.getAccountCode());

                // get注文単位()
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                    hostEqtypeOrderClmdReceiptParams.getInstitutionCode(),
                    hostEqtypeOrderClmdReceiptParams.getBranchCode(),
                    ProductTypeEnum.EQUITY,
                    hostEqtypeOrderClmdReceiptParams.getOrderRequestNumber()
                );
                log.debug("　@　@注文単位ID： [" + orderUnit.getOrderUnitId() + "]");
                    
                // 注文が受付未済の場合、当該注文の訂正取消通知処理をスキップする
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)this.orderUnit.getDataSourceObject();
                if (l_orderUnitRow.getConfirmedQuantityIsNull())
                {
                    log.debug("注文が受付未済なので、当該注文の訂正取消通知処理をスキップする。");
                    hostEqtypeOrderClmdReceiptParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    Processors.getDefaultProcessor().doUpdateQuery(hostEqtypeOrderClmdReceiptParams);
                    return WEB3StatusDef.NOT_DEAL;
                }
                // SONAR入力の訂正取消通知 かつ キュー.発注経路区分≠注文単位.発注経路区分 かつ WEBⅢによる訂正取消中の場合、
                // 処理をスキップする
                if (hostEqtypeOrderClmdReceiptParams.getModSubmitOrderRouteDiv() != null &&
                    !hostEqtypeOrderClmdReceiptParams.getModSubmitOrderRouteDiv().equals(l_orderUnitRow.getSubmitOrderRouteDiv()) &&
                    (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitRow.getOrderStatus()) ||
                    OrderStatusEnum.MODIFYING.equals(l_orderUnitRow.getOrderStatus()) ||
                    OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitRow.getOrderStatus()) ||
                    OrderStatusEnum.CANCELLING.equals(l_orderUnitRow.getOrderStatus())))
                {
                    log.debug("SONAR入力の訂正取消通知（異なる発注経路からの訂正取消）かつ WEBⅢによる訂正取消中の場合、当該注文の訂正取消通知処理をスキップする。");
                    hostEqtypeOrderClmdReceiptParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    Processors.getDefaultProcessor().doUpdateQuery(hostEqtypeOrderClmdReceiptParams);
                    return WEB3StatusDef.NOT_DEAL;
                }

                // 1.3.4.1.1. 取得した注文単位＝現物株式の場合
                if (OrderCategEnum.ASSET.equals(orderUnit.getOrderCateg()))
                {
                    // 1.3.4.1.1.1. 通知キューParams.訂正取消通知区分 == ”訂正完了” または　@”訂正失敗”
                    if (WEB3CanmodReceiptTypeDef.CHANGED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()) ||
                        WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
                    {
                        // 1.3.4.1.1.1.1. notify訂正()
                        WEB3EquityReceiveChangeEventService l_unitService =
                            (WEB3EquityReceiveChangeEventService)Services.getService(
                                WEB3EquityReceiveChangeEventService.class);
                        l_unitService.notifyChange(hostEqtypeOrderClmdReceiptParams, orderUnit);
                    }
                    // 1.3.4.1.1.2. 通知キューParams.訂正取消通知区分 == ”取消完了” または　@”取消失敗”
                    else if (WEB3CanmodReceiptTypeDef.CANCEL.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()) ||
                        WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
                    {
                        // 1.3.4.1.1.2.1. notify取消()
                        WEB3EquityReceiveCancelEventService l_unitService =
                            (WEB3EquityReceiveCancelEventService)Services.getService(
                                WEB3EquityReceiveCancelEventService.class);
                        l_strStatus = l_unitService.notifyCancel(hostEqtypeOrderClmdReceiptParams, orderUnit);
                    }
                    // 上記以外の場合
                    else
                    {
                        String l_strMessage =
                            "株式訂正取消通知キュー.訂正取消通知区分≠（\"訂正完了\"or\"訂正失敗\"or\"取消完了\"or\"取消失敗\"）";
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80025,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strMessage);
                    }
                }
                // 1.3.4.1.2. 取得した注文単位＝信用取引の場合
                else if (OrderCategEnum.OPEN_MARGIN.equals(orderUnit.getOrderCateg()) ||
                          OrderCategEnum.CLOSE_MARGIN.equals(orderUnit.getOrderCateg()) ||
                          OrderCategEnum.SWAP_MARGIN.equals(orderUnit.getOrderCateg()))
                {
                    // 1.3.4.1.2.1. 通知キューParams.訂正取消通知区分 == ”訂正完了” または　@”訂正失敗”
                    if (WEB3CanmodReceiptTypeDef.CHANGED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()) ||
                        WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
                    {
                        // 1.3.4.1.2.1.1. notify訂正()
                        WEB3MarginChangeCancelNotifyChangeUnitService l_unitService =
                            (WEB3MarginChangeCancelNotifyChangeUnitService)Services.getService(
                                WEB3MarginChangeCancelNotifyChangeUnitService.class);
                        l_unitService.notifyChange(hostEqtypeOrderClmdReceiptParams, orderUnit);
                    }
                    // 1.3.4.1.2.2. 通知キューParams.訂正取消通知区分 == ”取消完了” または　@”取消失敗”
                    else if (WEB3CanmodReceiptTypeDef.CANCEL.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()) ||
                        WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
                    {
                        // 1.3.4.1.2.2.1. notify取消()
                        WEB3MarginChangeCancelNotifyCancelUnitService l_unitService =
                            (WEB3MarginChangeCancelNotifyCancelUnitService)Services.getService(
                                WEB3MarginChangeCancelNotifyCancelUnitService.class);
                        l_strStatus = l_unitService.notifyCancel(hostEqtypeOrderClmdReceiptParams, orderUnit);
                    }
                    // 上記以外の場合
                    else
                    {
                        String l_strMessage =
                            "株式訂正取消通知キュー.訂正取消通知区分≠（\"訂正完了\"or\"訂正失敗\"or\"取消完了\"or\"取消失敗\"）";
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80025,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strMessage);
                    }
                }
                // 1.3.4.1.3. 上記以外の場合
                else
                {
                    String l_strMessage =
                        "株式注文単位.注文カテゴリ≠（\"現物注文\"or\"新規建注文\"or\"返済注文\"or\"現引・現渡注文\"）";
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80025,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strMessage);
                }
            }
            catch (WEB3BaseException l_exp)
            {
                throw new DataCallbackException(
                    l_exp.getMessage(),
                    l_exp);
            }
            //ステータス更新は株式訂正取消通知一件TransactionCallback()内へ移す部分 .start
            if (WEB3CanmodReceiptTypeDef.CHANGED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()) ||
                WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
            {
                hostEqtypeOrderClmdReceiptParams.setStatus(WEB3StatusDef.DEALT);
            }
            else
            {
                hostEqtypeOrderClmdReceiptParams.setStatus(l_strStatus);
            }
            hostEqtypeOrderClmdReceiptParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            Processors.getDefaultProcessor().doUpdateQuery(hostEqtypeOrderClmdReceiptParams);
            //.end
            log.exiting(STR_METHOD_NAME);
            return l_strStatus;
        }
    }
}
@
