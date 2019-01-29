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
filename	WEB3AsynEquityOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 株式注文受付サービスクラス(WEB3AsynEquityOrderAcceptServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History :同期版履歴：
                   2004/02/24 中尾　@寿彦(SRA) 新規作成
                   2004/09/20 鄒政 (中訊) 修正
                   2004/11/05 法@旭 修正
                   2004/12/15 森川  (SRA) 残案件対応
                   2005/01/06 岡村和明(SRA) JavaDoc修正
 　@　@　@　@　@　@　@　@　@　@　@非同期対応版履歴：
                   2005/03/09 劉（FLJ）非同期実行対応（新規クラス）
 */
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.data.HostEqtypeOrderAcceptRow;
import webbroker3.equity.message.WEB3EquityOrderAcceptRequest;
import webbroker3.equity.service.delegate.WEB3EquityOrderAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.gentrade.WEB3GentradeRequestCodesForRead;

import java.util.List;

/**
 * （非同期対応株式注文受付サービスImpl）。<BR>
 * <BR>
 * 非同期対応株式注文受付サービスクラス。
 * @@author  : 劉（FLJ）
 * @@version 1.0
 */
public class WEB3AsynEquityOrderAcceptServiceImpl
    implements Runnable
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynEquityOrderAcceptServiceImpl.class);

    /**
     * (株式注文受付リクエスト)。<BR>
     */
    private WEB3EquityOrderAcceptRequest request;

    /**
     * (コンストラクタ)。<BR>
     * @@roseuid 4042ED5D016D
     */
    public WEB3AsynEquityOrderAcceptServiceImpl(WEB3EquityOrderAcceptRequest l_request)
    {
        this.request = l_request;
    }

    /**
     * (注文受付処理を実施する)。<BR>
     * <BR>
     * シーケンス図「（株式注文受付サービス）注文受付」参照。<BR>
     * <BR>
     */
    public void run()
    {
        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME);
        WEB3EquityOrderAcceptRequest l_eqOrderAcceptRequest =
            (WEB3EquityOrderAcceptRequest) request;

        try
        {
            //getDefaultProcessor
            QueryProcessor l_qp = Processors.getDefaultProcessor();

            //株式注文受付TransactionCallback
            WEB3EquityOrderAcceptServiceTransactionCallback l_eqOdrAptSrvTranCallback;
            l_eqOdrAptSrvTranCallback = new
                WEB3EquityOrderAcceptServiceTransactionCallback();

            //set識別コードプレフィクス一覧
            l_eqOdrAptSrvTranCallback.setOrderRequestNumberPrefixGroup(
                l_eqOrderAcceptRequest.orderRequestNumberPrefixGroup);

            //doTransaction
            l_qp.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING,
                l_eqOdrAptSrvTranCallback);
        }
        catch (DataCallbackException l_dce)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", (WEB3BaseException)l_dce.getDetails());
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de));
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
     * （株式注文受付TransactionCallback）。<BR>
     * <BR>
     * 株式注文受付処理を行うトランザクション・コールバッククラス。
     */
    private class WEB3EquityOrderAcceptServiceTransactionCallback
        implements TransactionCallback
    {
        /**
         * （識別コードプレフィクス一覧）。
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * （get識別コードプレフィクス一覧）。<BR>
         * <BR>
         * 識別コードプレフィクス一覧を取得する。
         * @@return 識別コードプレフィクス一覧
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return this.orderRequestNumberPrefixGroup;
        }

        /**
         * （set識別コードプレフィクス一覧）。<BR>
         * <BR>
         * 引数の識別コードプレフィクス一覧をプロパティにセットする。
         * @@param l_prefixGroup - (識別コードプレフィクス一覧)<BR>
         * 識別コードプレフィクス一覧。
         */
        public void setOrderRequestNumberPrefixGroup(String[] l_prefixGroup)
        {
            this.orderRequestNumberPrefixGroup = l_prefixGroup;
        }

        /**
         * （処理区分をupdateする）。
         * @@param l_hostEqtypeOrderAcceptParams - (株式注文受付キューParams)
         * @@param l_strStatus - (処理区分)
         * @@roseuid 4042ED5D016E
         */
        public void updateStatus(
            HostEqtypeOrderAcceptParams l_params,
            String l_strStatus)
            throws WEB3SystemLayerException
        {
            final String STR_METHOD_NAME =
                "updateStatus(HostEqtypeOrderAcceptParams, String)";
            log.entering(STR_METHOD_NAME);
            
            l_params.setStatus(l_strStatus);
            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_params);
            }
            catch (DataException l_de)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
            }
            log.exiting(STR_METHOD_NAME);
        }

        /**
         * （process）。<BR>
         * <BR>
         * トランザクション処理を実施する。 <BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（株式注文受付サービス）process」参照。 <BR>
         * <BR>
         * @@return WEB3BaseException（内部処理にてWEB3BaseException例外が発生した場合）<BR>
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4050215102CD
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME =
                "WEB3EquityOrderAcceptServiceTransactionCallback.process()";
            log.entering(STR_METHOD_NAME);
            
            // 1.1. get識別コードプレフィクス一覧()
            String[] l_odrReqNumPfxGrp = this.getOrderRequestNumberPrefixGroup();
            
            // 1.2. get処理対象データコード一覧()
            String[] l_requestCodesForReadList = null;
            try
            {
                l_requestCodesForReadList =
                    WEB3GentradeRequestCodesForRead.getRequestCodesForReadList(
                        WEB3EquityOrderAcceptRequest.PTYPE);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            
            // 1.3. 注文受付キューテーブル読み込み
            int l_intOdrReqNumPfxGrpCnt = 0;
            if (l_odrReqNumPfxGrp != null)
            {
                l_intOdrReqNumPfxGrpCnt = l_odrReqNumPfxGrp.length;
            }
            int l_intRequestCodesForReadListCnt = 0;
            if (l_requestCodesForReadList != null)
            {
                l_intRequestCodesForReadListCnt = l_requestCodesForReadList.length;
            }
            
            StringBuffer l_sbWhere = new StringBuffer();
            if (l_intRequestCodesForReadListCnt > 0)
            {
                l_sbWhere.append("(request_code = ?");
                for (int i = 1; i < l_intRequestCodesForReadListCnt; i++)
                {
                    l_sbWhere.append(" or request_code = ?");
                }
                l_sbWhere.append(") and ");
            }
            if (l_intOdrReqNumPfxGrpCnt > 0)
            {
                l_sbWhere.append("(order_request_number like ?");
                for (int i = 1; i < l_intOdrReqNumPfxGrpCnt; i++)
                {
                    l_sbWhere.append(" or order_request_number like ?");
                }
                l_sbWhere.append(") and ");
            }
            l_sbWhere.append("status = ?");
            log.debug("検索条件文字列:[" + l_sbWhere.toString() + "]");
            
            Object[] l_objWhere = new Object[l_intRequestCodesForReadListCnt + l_intOdrReqNumPfxGrpCnt + 1];
            int l_intCnt = 0;
            for (int i = 0; i < l_intRequestCodesForReadListCnt; i++)
            {
                l_objWhere[l_intCnt++] = l_requestCodesForReadList[i];
                log.debug("処理対象データコード一覧[" + i + "]：[" + l_requestCodesForReadList[i] + "]");
            }
            for (int i = 0; i < l_intOdrReqNumPfxGrpCnt; i++)
            {
                l_objWhere[l_intCnt++] = l_odrReqNumPfxGrp[i]+ "%";
                log.debug("識別コードプレフィクス一覧[" + i + "]：[" + l_odrReqNumPfxGrp[i] + "%]");
            }
            l_objWhere[l_intCnt] = WEB3HostStatusDef.NOT_STARTED_PROCESS;
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                HostEqtypeOrderAcceptRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
            
            // 1.4. 取得したキューテーブルのレコード数分、Loop処理
            if (l_lisRecords != null && !l_lisRecords.isEmpty())
            {
                WEB3EquityOrderAcceptUnitService l_service =
                    (WEB3EquityOrderAcceptUnitService)Services.getService(
                        WEB3EquityOrderAcceptUnitService.class);
                int l_intSize = l_lisRecords.size();
                for (int i = 0; i < l_intSize; i++)
                {
                    String l_strStatus = WEB3HostStatusDef.COMPLETE_PROCESS;
                    
                    HostEqtypeOrderAcceptParams l_params =
                        new HostEqtypeOrderAcceptParams(
                            (HostEqtypeOrderAcceptRow)l_lisRecords.get(i));
                    // 1.4.1. notify注文受付()
                    try
                    {
                        //株式注文受付一件サービス.notify注文受付
                        //キューの処理完了更新は株式受付一件サービスへ移す
                        String l_strAcceptStatus = l_params.getAcceptStatus();
                        if (WEB3AcceptStatusDef.OVER.equals(l_strAcceptStatus) ||
                            WEB3AcceptStatusDef.ERROR.equals(l_strAcceptStatus))
                        {
                            l_service.notifyOrderAccept(l_params);
                        }
                        else
                        {
                            l_service.notifyOrderAcceptOvertime(l_params);
                        }
                    }
                    catch (Exception l_e)
                    {
                        //--------------------
                        //処理対象キューUPDATE　@(エラー時)
                        //--------------------
                        if (l_e instanceof WEB3BaseRuntimeException)
                        {
                            WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_e;
                            if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
                            {
                                log.error("口座ロック失敗：" + l_params.toString());
                                continue;
                            }
                        }
    
                        //エラーがそれ以外の場合　@(=>エラー)
                        log.error("一件処理にてエラー発生：" + l_params.toString(), l_e);
                        l_strStatus = WEB3HostStatusDef.DATA_ERROR;
                    }
    
                    // 1.4.2. 株式注文受付キューテーブル.処理区分をupdateする
                    if (!WEB3HostStatusDef.COMPLETE_PROCESS.equals(l_strStatus))
                    {
                        try
                        {
                            updateStatus(l_params, l_strStatus);
                        }
                        catch (WEB3SystemLayerException l_wse)
                        {
                            throw new DataCallbackException(l_wse.getMessage(), l_wse);
                        }
                    }
                }
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
