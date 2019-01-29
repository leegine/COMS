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
filename	WEB3AsynEquityReceiveCloseOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式失効通知サービスImpl(WEB3AsynEquityReceiveCloseOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
                   同期対応版履歴：
Revesion History : 2004/05/24 李綱 (中訊) 新規作成
Revesion History : 2004/09/20 鄒政 (中訊) 修正
Revesion History : 2004/12/13 水落 (SRA) 残案件対応のため修正
Revesion History : 2005/01/06 岡村 (SRA) JavaDoc修正
　@　@　@　@　@　@　@　@　@  非同期対応版履歴：
Revesion History : 2005/03/09 劉（FLJ）非同期実行対応（新規クラス）
Revesion History : 2006/11/20 張騰宇(中訊) モデル 1046
Revesion History : 2007/04/17 趙林鵬 (中訊) モデル 1139
Revesion History : 2007/06/11 丁昭奎 (中訊) モデル 1179
Revesion History : 2008/03/20 金傑 (中訊) モデル 1308
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyRow;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.message.WEB3EquityCloseOrderRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;

/**
 * （株式失効通知サービスImpl）。<BR>
 * <BR>
 * 株式失効通知サービス実装クラス。<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@author 鄒政
 * @@version 1.0
 */
public class WEB3AsynEquityReceiveCloseOrderServiceImpl
    implements Runnable
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynEquityReceiveCloseOrderServiceImpl.class);


    /**
     *　@株式失効通知処理リクエスト
     */
    private WEB3EquityCloseOrderRequest l_equityCloseOrderRequest;

    /**
     * @@roseuid 40AD626100FC
     */
    public WEB3AsynEquityReceiveCloseOrderServiceImpl(WEB3EquityCloseOrderRequest
        l_equityCloseOrderRequest)
    {
        this.l_equityCloseOrderRequest=l_equityCloseOrderRequest;
    }

    /**
     * 失効処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（失効通知サービス）失効通知」参照<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4137CFEA0176
     */
    public void run()
    {
        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME);

        // 株式失効通知処理を行う
        try
        {
            //1.2 getDefaultProcesser
            QueryProcessor l_qp = Processors.getDefaultProcessor();

            //1.3 株式失効通知TransactionCallback()
            WEB3EquityReceiveCloseOrderTransactionCallback l_callback
                         = new WEB3EquityReceiveCloseOrderTransactionCallback();

            //1.4 set識別コードプレフィクス (識別コードプレフィクス一覧 : String[])
            l_callback.setOrderRequestNumberPrefixGroup(l_equityCloseOrderRequest.orderRequestNumberPrefixGroup);

            //1.5 doTransaction()（1件ごとにコミットするようにトランザクション属性を設定。）
            l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callback);

        }
        catch (DataException l_exp)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("",new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp));
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
            new WEB3GentradeDaemonTriggerManager().releaseThread(l_equityCloseOrderRequest.threadNo.longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (株式失効通知TransactionCallback)<BR>
     * 株式失効通知ハンドラ処理を行うトランザクション・コールバッククラス。<BR>
     */
    private class WEB3EquityReceiveCloseOrderTransactionCallback
        implements TransactionCallback
    {

        /**
         * (識別コードプレフィクス一覧)<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * (株式失効通知TransactionCallback)<BR>
         * 株式失効通知TransactionCallbackクラス<BR>
         * コンストラクタ。
         * @@return
         * webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderTransactionCallback
         * .WEB3EquityReceiveCloseOrderTransactionCallback
         */
        public WEB3EquityReceiveCloseOrderTransactionCallback()
        {

        }

        /**
         * トランザクション処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（失効通知サービス）process」参照。<BR>
         * @@return WEB3BaseException（内部処理にてWEB3BaseException例外が発生した場合）
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4137CFEA0290
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME =
                "WEB3EquityReceiveCloseOrderTransactionCallback.process()";
            log.entering(STR_METHOD_NAME);

            HostEqtypeCloseOrderNotifyRow l_hostEqtypeCloseOrderNotifyRow;
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderMgr =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            // ----------------------------------
            // 1.1 get識別コードプレフィクス一覧()
            // ----------------------------------
            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();

            // ----------------------------------
            // 1.2 株式失効通知キューテーブル検索
            // ----------------------------------
            //     データコード =  株式失効通知
            //     処理区分 = 未処理
            //     識別コードの先頭2桁が、get識別コードプレフィクス一覧()の戻り値配列のいずれかと一致
            String l_strWhere = "request_code=? and status=?";
            //String l_strCondition = "for update";
            String l_strCondition = null;
            int l_intLength = 0;
            if (l_orderRequestNumberPrefixGroup != null)
            {
                l_intLength = l_orderRequestNumberPrefixGroup.length;
            }
            if (l_intLength > 0)
            {
                l_strWhere = l_strWhere + " and (";
                for(int i = 0;i < l_intLength;i++)
                {
                    if (i == 0)
                    {
                        l_strWhere = l_strWhere + "order_request_number like ?";
                    }
                    else if (0 < i)
                    {
                        l_strWhere = l_strWhere + " or order_request_number like ?";
                    }
                    log.debug("識別コードプレフィクス一覧：" + l_orderRequestNumberPrefixGroup[i]);
                }
                l_strWhere = l_strWhere + ")";
            }
            log.debug("検索条件：[" + l_strWhere + "]");

            Object l_objWhere[] = new Object[l_intLength + 2];
            l_objWhere[0] = WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE;
            l_objWhere[1] = WEB3HostStatusDef.NOT_STARTED_PROCESS;
            for (int i = 0;i < l_intLength;i++)
            {
                l_objWhere[i + 2] = l_orderRequestNumberPrefixGroup[i] + "%";
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    HostEqtypeCloseOrderNotifyRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_objWhere);

            //取得したキューレコード数分のLoop処理
            int l_intSize = l_lisRecords.size();
            for (int i = 0; i < l_intSize; i++)
            {
                log.debug("株式失効通知：Loop処理 i = " + i);

                //処理区分
                String l_strStatus = null;

                //get 株式失効通知キューParams
                l_hostEqtypeCloseOrderNotifyRow =
                    (HostEqtypeCloseOrderNotifyRow)l_lisRecords.get(i);
                l_hostEqtypeCloseOrderNotifyParams =
                    new HostEqtypeCloseOrderNotifyParams(l_hostEqtypeCloseOrderNotifyRow);
                log.debug("　@　@識別コード： [" + l_hostEqtypeCloseOrderNotifyParams.getOrderRequestNumber() + "]");

                WEB3GentradeAccountManager l_accountMgr =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                try
                {
                    // ---------------------------------
                    // 1.3.2. 失効対象注文単位を取得する
                    // ---------------------------------
                    //       証券会社コード：　@株式失効通知キューParams.証券会社コード
                    //       部店コード：　@株式失効通知キューParams.部店コード
                    //       商品タイプ：　@ProductTypeEnum.株式
                    //       識別コード：　@株式失効通知キューParams.識別コード
                    EqTypeOrderUnit l_eqtypeOrderUnit = l_orderMgr.getOrderUnit(
                        l_hostEqtypeCloseOrderNotifyParams.getInstitutionCode(),
                        l_hostEqtypeCloseOrderNotifyParams.getBranchCode(),
                        ProductTypeEnum.EQUITY,
                        l_hostEqtypeCloseOrderNotifyParams.getOrderRequestNumber());
                    log.debug("　@　@注文単位ID： [" + l_eqtypeOrderUnit.getOrderUnitId() + "]");

                    //株式失効通知キューテーブル.処理区分更新処理をWEB3EquityReceiveCloseOrderUnitTransactionCallback内部移す
                    WEB3EquityReceiveCloseOrderUnitTransactionCallback l_callback =
                        new WEB3EquityReceiveCloseOrderUnitTransactionCallback(
                            l_hostEqtypeCloseOrderNotifyParams,
                            l_eqtypeOrderUnit);
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_callback);
                }
                catch (Exception l_e)
                {
                    if (l_e instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_e;
                        if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
                        {
                            //口座ロック()に失敗した場合
                            log.debug(l_wre.getMessage(), l_wre);
                            continue;
                        }
                    }
                    else if (l_e instanceof DataCallbackException)
                    {
                        DataCallbackException l_dce = 
                            (DataCallbackException) l_e;
                        
                        if (l_dce.getDetails() != null)
                        {
                            WEB3BaseException l_wbe = (WEB3BaseException) l_dce.getDetails();                            
                            if (l_wbe.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_01975)
                            {
                                //「注文が受付未済/訂正中」の例外の場合
                                log.debug(l_wbe.getMessage(), l_wbe);
                                continue;
                            }
                        }
                    }
                    else if (l_e instanceof WEB3SystemLayerException)
                    {
                        WEB3SystemLayerException l_sle = (WEB3SystemLayerException)l_e;
                        if (l_sle.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80005 &&
                            l_sle.getException() == null)
                        {
                            try
                            {
                                //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                                //証券会社コード：　@株式失効通知キューParams.証券会社コード
                                //部店コード：　@　@　@　@株式失効通知キューParams.部店コード
                                //口座コード：　@　@　@　@株式失効通知キューParams口座コード
                                l_accountMgr.getMainAccount(
                                    l_hostEqtypeCloseOrderNotifyParams.getInstitutionCode(),
                                    l_hostEqtypeCloseOrderNotifyParams.getBranchCode(),
                                    l_hostEqtypeCloseOrderNotifyParams.getAccountCode());
                            }
                            catch(WEB3BaseException l_ex)
                            {
                                log.debug(l_ex.getMessage(), l_ex);
                                l_strStatus = WEB3StatusDef.DEALT;
                                l_hostEqtypeCloseOrderNotifyParams.setStatus(l_strStatus);
                                l_hostEqtypeCloseOrderNotifyParams.setLastUpdatedTimestamp(
                                    GtlUtils.getSystemTimestamp());
                                l_queryProcessor.doUpdateQuery(l_hostEqtypeCloseOrderNotifyParams);
                                continue;
                            }
                        }
                    }

                    log.error(l_e.getMessage(), l_e);
                    l_strStatus = WEB3HostStatusDef.DATA_ERROR;
                }

                // ------------------------------------------------------------
                // 1.3.3. 株式失効通知キューテーブル.処理区分をupdateしcommitする
                // ------------------------------------------------------------
                if(WEB3StatusDef.DATA_ERROR.equals(l_strStatus)){
                    l_hostEqtypeCloseOrderNotifyParams.setStatus(l_strStatus);
                    l_hostEqtypeCloseOrderNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_hostEqtypeCloseOrderNotifyParams);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }


        /**
         * (get識別コードプレフィクス一覧)<BR>
         * <BR>
         * 識別コードプレフィクス一覧を取得する。
         * @@return 識別コードプレフィクス一覧
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return this.orderRequestNumberPrefixGroup;
        }

        /**
         * (set識別コードプレフィクス一覧)<BR>
         * <BR>
         * 引数の識別コードプレフィクス一覧をプロパティにセットする。
         * @@param orderRequestNumberPrefixGroup 識別コードプレフィクス一覧
         */
        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup)
        {
            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
        }
        
    }

    /**
     * (株式失効通知一件TransactionCallback)<BR>
     * <BR>
     * 株式失効通知一件TransactionCallbackクラス。<BR>
     */
    public class WEB3EquityReceiveCloseOrderUnitTransactionCallback
        implements TransactionCallback
    {
        /**
         * (株式失効通知キューParams。)
         */
        private HostEqtypeCloseOrderNotifyParams hostEqtypeCloseOrderNotifyParams;

        /**
         * (注文単位。)
         */
        private EqTypeOrderUnit orderUnit;

        /**
         * (株式失効通知一件TransactionCallback)<BR>
         * <BR>
         * コンストラクタ。<BR>
         * 引数の値を同名プロパティにセットする。<BR>
         * @@param l_hostEqtypeCloseOrderNotifyParams - (株式失効通知キューParams)<BR>
         * 【株式失効通知キューテーブル】の１レコード。<BR>
         * @@param l_orderUnit - (注文単位)<BR>
         * 注文単位オブジェクト<BR>
         */
        public WEB3EquityReceiveCloseOrderUnitTransactionCallback(
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams,
            EqTypeOrderUnit l_orderUnit)
        {
            hostEqtypeCloseOrderNotifyParams = l_hostEqtypeCloseOrderNotifyParams;
            orderUnit = l_orderUnit;
        }

        /**
         * (process)<BR>
         * <BR>
         * トランザクション処理を実施する。<BR>
         *
         * シーケンス図<BR>
         * 「（失効通知サービス）process」参照。<BR>
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            try
            {
                // notify失効()
                WEB3EquityReceiveCloseOrderUnitService l_service =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(WEB3EquityReceiveCloseOrderUnitService.class);
                l_service.notifyCloseOrder(hostEqtypeCloseOrderNotifyParams, orderUnit);

                // 余力再計算（）
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                WEB3GentradeSubAccount l_subAccount = null;
                l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    orderUnit.getAccountId(),
                    orderUnit.getSubAccountId());
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                    (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new DataCallbackException();
            }
            catch (WEB3BaseException l_exp)
            {
                log.debug(STR_METHOD_NAME, l_exp);
                throw new DataCallbackException(null, l_exp);
            }
            log.exiting(STR_METHOD_NAME);

            // ------------------------------------------------------------
            // 株式失効通知キューテーブル.処理区分をupdateしcommitする
            // ------------------------------------------------------------
            String l_strStatus = null;
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit = null;
            try
            {
                l_orderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new DataCallbackException();
            }
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            
            // <失効通知キュー.失効数量に値がセットされている場合>
            //  失効通知キュー.失効数量 < (注文単位.市場から確認済みの数量-注文単位.約定数量)
            // <失効通知キュー.失効数量に値がセットされていない場合>
            // 注文単位.約定数量 < 失効通知キュー.約定数量
            if ((!hostEqtypeCloseOrderNotifyParams.getCloseQuantityIsNull()
                    && (hostEqtypeCloseOrderNotifyParams.getCloseQuantity() < (l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity())))
                || (hostEqtypeCloseOrderNotifyParams.getCloseQuantityIsNull()
                    && (l_orderUnitRow.getExecutedQuantity() < hostEqtypeCloseOrderNotifyParams.getExecutedQuantity())))
            {
                l_strStatus = WEB3StatusDef.DEALING;
            }
            // 以上でない場合
            else
            {
                l_strStatus = WEB3StatusDef.DEALT;   
            }
            hostEqtypeCloseOrderNotifyParams.setStatus(l_strStatus);
            hostEqtypeCloseOrderNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            Processors.getDefaultProcessor().doUpdateQuery(hostEqtypeCloseOrderNotifyParams);
            return null;
        }
    }
}@
