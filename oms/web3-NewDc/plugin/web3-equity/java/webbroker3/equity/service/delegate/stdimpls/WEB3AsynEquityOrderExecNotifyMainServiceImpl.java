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
filename	WEB3AsynEquityOrderExecNotifyMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式出来通知メインサービスImpl(WEB3AsynEquityOrderExecNotifyMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 同期版履歴：
Revesion History : 2004/12/01 中尾寿彦(SRA) 新規作成
Revesion History : 2005/01/05 岡村和明(SRA) 口座ロック対応
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 非同期対応版履歴：
Revesion History : 2005/03/09 非同期実行対応（新規クラス）
Revesion History : 2007/04/20 趙林鵬 (中訊) モデル 1139 1140
Revesion History : 2007/06/11 キョウ再平 (中訊) モデル 1178
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyRow;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptRow;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.data.HostEquityOrderExecNotifyRow;
import webbroker3.equity.message.WEB3EquityExecNotifyMainRequest;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.gentrade.data.DaemonTriggerRow;

/**
 * （非同期対応株式出来通知メインサービス実装クラス）。
 * @@author  : 劉（FLJ）
 * @@version : 1.0
 */
public class WEB3AsynEquityOrderExecNotifyMainServiceImpl
    implements Runnable
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynEquityOrderExecNotifyMainServiceImpl.class);

    /**
     * 出来通知メインリクエスト
     */
    private WEB3EquityExecNotifyMainRequest request;

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3AsynEquityOrderExecNotifyMainServiceImpl(WEB3EquityExecNotifyMainRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynEquityOrderExecNotifyMainServiceImpl：run()";
        log.entering(STR_METHOD_NAME);

        try
        {

            WEB3EquityExecNotifyMainRequest l_execNotifyRequest =
                (WEB3EquityExecNotifyMainRequest) request;

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
            WEB3EquityOrderExecNotifyMainTransactionCallback l_transactionCallBack =
                new WEB3EquityOrderExecNotifyMainTransactionCallback();

            // 1.4. set識別コードプレフィクス一覧()
            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_execNotifyRequest.
                orderRequestNumberPrefixGroup);
            l_transactionCallBack.setThreadNo(request.threadNo);


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
     * (株式出来通知メインTransactionCallback)<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
     */
    public class WEB3EquityOrderExecNotifyMainTransactionCallback
        implements TransactionCallback
    {
        /**
         * Thread No<BR>
         */
        private Long threadNo;

        /**
         * 識別コードプレフィクス一覧<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * コンストラクタ。<BR>
         */
        public WEB3EquityOrderExecNotifyMainTransactionCallback()
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
         * <BR>
         * シーケンス図<BR>
         * 「（株式出来通知メインサービス）process」参照。<BR>
         * @@return Object
         * @@throws DataNetworkException, DataQueryException, DataCallbackException
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            if(lockThread(this.threadNo.longValue())==false){
                log.error("処理スレッド占有ロック取得できないため、処理中止!");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            // 1.2. 株式出来通知キューテーブル読み込み
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
                for (int i = 0; i < l_intLength; i++)
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
            l_objWhere[0] = WEB3HostRequestCodeDef.EQUITY_ORDER_EXEC_NOTICE;
            l_objWhere[1] = WEB3StatusDef.NOT_DEAL;
            for (int i = 0; i < l_intLength; i++)
            {
                l_objWhere[i + 2] = orderRequestNumberPrefixGroup[i] + "%";
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
                HostEquityOrderExecNotifyRow.TYPE,
                l_sbWhere.toString(),
                null,
                null,
                l_objWhere);

            // 1.3. 取得したキューテーブルのレコード数分、Loop
            HostEquityOrderExecNotifyParams l_params = null;
            EqTypeOrderUnit l_orderUnit = null;

            int l_intNum = l_lisSearchResult.size();

            for (int i = 0; i < l_intNum; i++)
            {
                try
                {
                    log.debug("株式出来通知メイン：Loop処理 i = " + i);

                    l_params = (HostEquityOrderExecNotifyParams) l_lisSearchResult.get(i);
                    log.debug("　@　@識別コード： [" + l_params.getOrderRequestNumber() + "]");
                    this.validateEquityOrderExecNotifyParams(l_params);

                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

                    // 1.3.1. get注文単位(証券会社コード, 部店コード, 商品タイプ, 識別番号)
                    try
                    {
                        WEB3EquityOrderManager l_orderManager =
                            (WEB3EquityOrderManager) l_finApp.getTradingModule(
                            ProductTypeEnum.EQUITY).getOrderManager();
                        l_orderUnit = (EqTypeOrderUnit) l_orderManager.getOrderUnit(
                            l_params.getInstitutionCode(),
                            l_params.getBranchCode(),
                            ProductTypeEnum.EQUITY,
                            l_params.getOrderRequestNumber()
                            );
                        log.debug("　@　@注文単位ID： [" + l_orderUnit.getOrderUnitId() + "]");
                    }
                    catch(WEB3BaseException l_ex)
                    {
                        if (l_ex instanceof WEB3SystemLayerException)
                        {
                            WEB3SystemLayerException l_wre = (WEB3SystemLayerException)l_ex;
                            if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80005 &&
                                l_wre.getException() == null)
                            {
                                //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                                //証券会社コード：　@株式出来通知キューParams.証券会社コード
                                //部店コード：　@　@　@　@株式出来通知キューParams.部店コード
                                //口座コード：　@　@　@　@株式出来通知キューParams口座コー
                                WEB3GentradeAccountManager l_accountMgr =
                                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                                try
                                {
                                    l_accountMgr.getMainAccount(
                                        l_params.getInstitutionCode(),
                                        l_params.getBranchCode(),
                                        l_params.getAccountCode());
                                }
                                catch(WEB3BaseException l_bex)
                                {
                                    log.debug(l_bex.getMessage(), l_bex);
                                    l_params.setStatus(WEB3StatusDef.DEALT);
                                    l_params.setLastUpdatedTimestamp(
                                        GtlUtils.getSystemTimestamp());
                                    l_queryProcessor.doUpdateQuery(l_params);
                                    continue;
                                }

                                String l_strValue = GtlUtils.getTradingSystem().getPreference(
                                    WEB3SystemPreferencesNameDef.EXEC_NOTIFY_WAIT_SECONDS);

                                if (l_strValue == null)
                                {
                                    l_strValue = "0";
                                }

                                Date l_datSystemTimestamp = GtlUtils.getSystemTimestamp();

                                Date l_datCreatedTimestamp = WEB3DateUtility.addSecond(
                                    l_params.getCreatedTimestamp(), Long.parseLong(l_strValue));

                                if (WEB3DateUtility.compareToSecond(
                                    l_datSystemTimestamp, l_datCreatedTimestamp) > 0)
                                {
                                    log.exiting(STR_METHOD_NAME);
                                    throw new WEB3BusinessLayerException(
                                        WEB3ErrorCatalog.BUSINESS_ERROR_02752,
                                        this.getClass().getName() + "." + STR_METHOD_NAME,
                                        "注文単位が取得出来ない。");
                                }
                                else
                                {
                                    l_params.setLastUpdatedTimestamp(
                                        GtlUtils.getSystemTimestamp());
                                    l_queryProcessor.doUpdateQuery(l_params);
                                    continue;
                                }
                            }
                        }
                        throw new WEB3BaseException(
                            l_ex.getErrorInfo(),
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage());
                    }

                    // 株式出来通知一件TransactionCallback生成
                    WEB3EquityOrderExecNotifyTransactionCallback l_transactionCallback =
                        new WEB3EquityOrderExecNotifyTransactionCallback(l_orderUnit,
                        l_params);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                    //キューの更新は株式出来通知一件TransactionCallback内部へ移す
                    //l_params.setStatus(WEB3StatusDef.DEALT);
                    //l_QueryProcessor.doUpdateQuery(l_params);
                }
                catch (WEB3BaseException l_exp)
                {
                    log.error(l_exp.getMessage(), l_exp);
                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                }
                catch (DataCallbackException l_exp)
                {
                    ErrorInfo l_errorInfo = (ErrorInfo)l_exp.getDetails();
                    if (l_errorInfo.equals(WEB3ErrorCatalog.BUSINESS_ERROR_01975))
                    {
                        // 注文受付未済／変更の受付済／発注中のキューは出来処理スキップ
                        log.debug("出来処理スキップ（該当注文受付未済／変更の受付済／発注中） " + l_params.toString());
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_params);
                        continue;
                    }
                    else if (l_errorInfo.equals(WEB3ErrorCatalog.SYSTEM_ERROR_80076))
                    {
                        log.debug("口座ロック失敗：" + l_params.toString());
                        continue;
                    }
                    else if (l_errorInfo.equals(WEB3ErrorCatalog.BUSINESS_ERROR_01931))
                    {
                        log.debug("保有資産残数量チェックエラー：" + l_params.toString());
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_params);
                        continue;
                    }
                    else if (l_errorInfo.equals(WEB3ErrorCatalog.BUSINESS_ERROR_01934))
                    {
                        log.debug("建株残数量チェックエラー：" + l_params.toString());
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_params);
                        continue;
                    }

                    log.error(l_exp.getMessage(), l_exp);
                    if (l_errorInfo.error_class.equals(WEB3BusinessLayerException.class.
                        getName()))
                    {
                        l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                    }
                    else
                    {
                        l_params.setStatus(WEB3StatusDef.PROGRAM_ERROR);
                    }
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);

                    // 失効通知処理が失敗した場合
                    if (l_errorInfo == WEB3ErrorCatalog.BUSINESS_ERROR_01961 ||
                        l_errorInfo == WEB3ErrorCatalog.SYSTEM_ERROR_80077)
                    {
                        String l_strWhereClose = " request_code = ? "
                                           + " and institution_code = ? "
                                           + " and branch_code = ? "
                                           + " and account_code = ? "
                                           + " and order_request_number = ? "
                                           + " and status = ? ";
                        String l_strAccountCode = l_params.getAccountCode();
                        if (l_strAccountCode == null ||
                            Integer.parseInt(l_strAccountCode.trim()) == 0)
                        {
                            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                            WEB3GentradeAccountManager l_accountManager =
                                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                            try
                            {
                                MainAccount l_account =
                                    l_accountManager.getMainAccount(l_orderUnit.getAccountId());
                                l_strAccountCode = l_account.getAccountCode();
                            }
                            catch (NotFoundException l_nfe)
                            {
                                log.error("注文単位.口座IDに対応する顧客データが存在しません", l_nfe);
                            }
                        }
                        Object[] l_objWhereClose =
                        {
                            WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE,
                            l_params.getInstitutionCode(),
                            l_params.getBranchCode(),
                            l_strAccountCode,
                            l_params.getOrderRequestNumber(),
                            WEB3StatusDef.DEALING
                        };
                        List l_lisSearchResultClose =
                            l_queryProcessor.doFindAllQuery(
                            HostEqtypeCloseOrderNotifyRow.TYPE,
                            l_strWhereClose,
                            null,
                            "for update",
                            l_objWhereClose);
                        if (l_lisSearchResultClose.size() > 0)
                        {
                            HostEqtypeCloseOrderNotifyParams l_closeParams =
                                (HostEqtypeCloseOrderNotifyParams)l_lisSearchResultClose.get(0);
                            l_closeParams.setStatus(WEB3StatusDef.DATA_ERROR);
                            l_closeParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                            l_queryProcessor.doUpdateQuery(l_closeParams);
                        }
                    }
                    // 取消通知処理が失敗した場合
                    else if (l_errorInfo == WEB3ErrorCatalog.BUSINESS_ERROR_01962 ||
                              l_errorInfo == WEB3ErrorCatalog.SYSTEM_ERROR_80078)
                    {
                        String l_strWhereCancel = " request_code = ? "
                                          + " and institution_code = ? "
                                          + " and branch_code = ? "
                                          + " and account_code = ? "
                                          + " and order_request_number = ? "
                                          + " and canmod_receipt_type = ? "
                                          + " and status = ? ";
                        String l_strAccountCode = l_params.getAccountCode();
                        if (l_strAccountCode == null ||
                            Integer.parseInt(l_strAccountCode.trim()) == 0)
                        {
                            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                            WEB3GentradeAccountManager l_accountManager =
                                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                            try
                            {
                                MainAccount l_account =
                                    l_accountManager.getMainAccount(l_orderUnit.getAccountId());
                                l_strAccountCode = l_account.getAccountCode();
                            }
                            catch (NotFoundException l_nfe)
                            {
                                log.error("注文単位.口座IDに対応する顧客データが存在しません", l_nfe);
                            }
                        }
                        Object[] l_objWhereCancel =
                        {
                            WEB3HostRequestCodeDef.EQUITY_CHANGE_CANCEL_NOTICE,
                            l_params.getInstitutionCode(),
                            l_params.getBranchCode(),
                            l_strAccountCode,
                            l_params.getOrderRequestNumber(),
                            WEB3CanmodReceiptTypeDef.CANCEL,
                            WEB3StatusDef.DEALING
                        };
                        List l_lisSearchResultCancel =
                            l_queryProcessor.doFindAllQuery(
                                HostEqtypeOrderClmdReceiptRow.TYPE,
                                l_strWhereCancel,
                                null,
                                "for update",
                                l_objWhereCancel);
                        if (l_lisSearchResultCancel.size() > 0)
                        {
                            HostEqtypeOrderClmdReceiptParams l_cancelParams =
                                (HostEqtypeOrderClmdReceiptParams)l_lisSearchResultCancel.get(0);
                            l_cancelParams.setStatus(WEB3StatusDef.DATA_ERROR);
                            l_cancelParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                            l_queryProcessor.doUpdateQuery(l_cancelParams);
                        }
                    }
                }
                catch (Exception l_exp)
                {
                    log.error(l_exp.getMessage(), l_exp);
                    l_params.setStatus(WEB3StatusDef.PROGRAM_ERROR);
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (validate株式出来通知キュー)<BR>
         * <BR>
         * 取得した株式出来通知キューParamsの必須プロパティの not nullチェックを行う。<BR>
         * チェック対象プロパティのいずれか一つにでもnullが入っていた場合は、<BR>
         * 「株式出来通知キュー・必須設定項目がnull」の例外をthrowする。<BR>
         * <BR>
         * ○チェック対象プロパティ<BR>
         * <BR>
         * 　@証券会社コード<BR>
         * 　@部店コード<BR>
         * 　@識別コード<BR>
         * 　@約定株数<BR>
         * 　@約定単価<BR>
         * 　@約定日時<BR>
         * <BR>
         * @@param l_equityOrderExecNotifyParams - 株式出来通知キューParamsオブジェクト。
         * @@throws WEB3BaseException
         */
        public void validateEquityOrderExecNotifyParams(
            HostEquityOrderExecNotifyParams l_equityOrderExecNotifyParams)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "validateEquityOrderExecNotifyParams(HostEquityOrderExecNotifyParams)";
            log.entering(STR_METHOD_NAME);

            if (l_equityOrderExecNotifyParams.getInstitutionCode() == null ||
                l_equityOrderExecNotifyParams.getBranchCode() == null      ||
                l_equityOrderExecNotifyParams.getOrderRequestNumber() == null     ||
                l_equityOrderExecNotifyParams.getExecQuantityIsNull()      ||
                l_equityOrderExecNotifyParams.getExecPriceIsNull()         ||
                l_equityOrderExecNotifyParams.getExecTimestamp() == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00658,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            log.exiting(STR_METHOD_NAME);
        }
    }
}
@
