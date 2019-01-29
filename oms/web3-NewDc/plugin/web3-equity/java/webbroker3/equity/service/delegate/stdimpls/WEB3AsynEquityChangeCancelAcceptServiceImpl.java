head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynEquityChangeCancelAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正取消受付サービスImpl(WEB3AsynEquityChangeCancelAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 周玲玲(中訊) 新規作成
                   2004/09/14 李海波(中訊) 修正
                   2004/09/22 盧法@旭(中訊) 修正
                   2004/12/13 岡村和明(SRA) 残案件対応 Ｎｏ.３４２
                   2004/12/21 岡村和明(SRA) JavaDoc修正
                   2005/01/05 岡村和明(SRA) 口座ロック修正対応
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2005/03/09 劉（FLJ）非同期実行対応（新規クラス）
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Iterator;
import java.util.List;

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
import webbroker3.equity.message.WEB3EquityChangeCancelAcceptRequest;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;

import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;

/**
 *  (株式訂正取消受付サービスImpl）。<BR>
 * <BR>
 * 株式訂正取消受付サービス実装クラス
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3AsynEquityChangeCancelAcceptServiceImpl
    implements Runnable
{
    /**
     * <p>（ログ出力ユーティリティ)。</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3AsynEquityChangeCancelAcceptServiceImpl.class);

    /**
     *　@株式訂正取消受付処理リクエスト
     */
    private WEB3EquityChangeCancelAcceptRequest l_eqOrderAcceptRequest;

   /**
     * @@roseuid 40AD626100FC
     */
    public WEB3AsynEquityChangeCancelAcceptServiceImpl(WEB3EquityChangeCancelAcceptRequest
        l_eqOrderAcceptRequest)
    {
        this.l_eqOrderAcceptRequest=l_eqOrderAcceptRequest;
    }

    /**
     * <p>注文訂正取消受付処理を実施する。<br>
     * <br>
     * シーケンス図「（株式訂正取消受付サービス）訂正取消受付」参照。</p>
     * @@param l_request リクエストデータオブジェクト
     * @@return WEB3EquityChangeCancelAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 4137CF3500E0
     */
     public void run()
    {
        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME);

        // 株式訂正取消受付処理を行う
        try
        {
            //1.2 getDefaultProcessor()
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            //1.3 株式訂正取消受付TransactionCallback()
            WEB3EquityChangeCancelAcceptServiceTransactionCallback l_callBack =
                new WEB3EquityChangeCancelAcceptServiceTransactionCallback();
            //1.4 set識別コードプレフィクス一覧()
            l_callBack.setOrderRequestNumberPrefixGroup(
                l_eqOrderAcceptRequest.orderRequestNumberPrefixGroup);
            //1.5 doTransaction()
            l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callBack);
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
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
            new WEB3GentradeDaemonTriggerManager().releaseThread(l_eqOrderAcceptRequest.threadNo.longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * <p>（株式訂正取消受付TransactionCallback）。</p>
     * <p>株式訂正取消受付TransactionCallbackクラス。</p>
     */
    private class WEB3EquityChangeCancelAcceptServiceTransactionCallback
        implements TransactionCallback
    {

        /**
         * <p>（識別コードプレフィクス一覧）。</p>
         * <p>識別コードプレフィクス一覧。</p>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * <p>（株式訂正取消受付TransactionCallback ）。</p>
         * <p>コンストラクタ。</p>
         */
        public WEB3EquityChangeCancelAcceptServiceTransactionCallback()
        {
        }

        /**
         * <p>（process）。</p>
         * <p>トランザクション処理を実施する。<br>
         * <br>
         * シーケンス図<br>
         * 「（株式訂正取消受付サービス）process」参照。</p>
         * @@return WEB3BaseException（内部処理にてWEB3BaseException例外が発生した場合）<BR>
         * @@throws DataCallbackException
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@roseuid 4137CF3501A8
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            // 1.1 get識別コードプレフィクス一覧()
            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();

            // 1.2 [検索条件]注文受付キューテーブル.データコード == "注文訂正取消"（AI80B）
            // 注文受付キューテーブル.処理区分 == "未処理"

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            l_sbWhere.append(" and status = ? ");
            int l_intRequestNumberPrefixGroupCnt = 0;
            if (l_orderRequestNumberPrefixGroup != null)
            {
                l_intRequestNumberPrefixGroupCnt = l_orderRequestNumberPrefixGroup.length;
            }
            if (l_intRequestNumberPrefixGroupCnt > 0)
            {
                l_sbWhere.append(" and (order_request_number like ?");
                for (int i = 1; i < l_intRequestNumberPrefixGroupCnt; i++)
                {
                    l_sbWhere.append(" or order_request_number like ?");
                }
                l_sbWhere.append(")");
            }
            Object[] l_objEquityOrderUnitWhere = new Object[l_intRequestNumberPrefixGroupCnt + 2];
            l_objEquityOrderUnitWhere[0] = WEB3HostRequestCodeDef.EQUITY_CHANGE_CANCEL;
            l_objEquityOrderUnitWhere[1] = WEB3StatusDef.NOT_DEAL;
            for (int i = 0 ; i < l_intRequestNumberPrefixGroupCnt ; i++)
            {
                l_objEquityOrderUnitWhere[i + 2] = l_orderRequestNumberPrefixGroup[i] + "%";
                log.debug("識別コードプレフィクス一覧[" + i + "]：[" + l_orderRequestNumberPrefixGroup[i] + "%]");
            }

            QueryProcessor l_qp = Processors.getDefaultProcessor();

            // 注文受付キューテーブルの読み込み実行
            List l_lisHostEqtypeOrderAccept =
                l_qp.doFindAllQuery(
                    HostEqtypeOrderAcceptRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    null,
                    l_objEquityOrderUnitWhere);
            
            WEB3EquityChangeCancelAcceptUnitService l_changeCancelService =
                (WEB3EquityChangeCancelAcceptUnitService) Services.getService(WEB3EquityChangeCancelAcceptUnitService.class);
            if (l_lisHostEqtypeOrderAccept != null)
            {
                Iterator l_iterator = l_lisHostEqtypeOrderAccept.iterator();
                HostEqtypeOrderAcceptParams l_params;

                // 1.3 * 取得したキューテーブルのレコード数分、Loop処理
                while (l_iterator.hasNext())
                {
                    l_params = new HostEqtypeOrderAcceptParams((HostEqtypeOrderAcceptRow)l_iterator.next());
                    try
                    {
                        // 1.3.1 notify訂正取消受付(株式注文受付キューParams : 株式注文受付キューParams)
                        String l_strAcceptStatus = l_params.getAcceptStatus();
                        if (WEB3AcceptStatusDef.OVER.equals(l_strAcceptStatus) ||
                            WEB3AcceptStatusDef.ERROR.equals(l_strAcceptStatus))
                        {
                            l_changeCancelService.notifyChangeCancelAccept(l_params);
                        }
                        else
                        {
                            l_changeCancelService.notifyChangeCancelAcceptOvertime(l_params);
                        }

                        // 1.3.2 * 株式注文受付キューテーブル.処理区分をupdateする
                         //キューの更新は株式訂正取消受付一件サービスへ移す
                        //log.debug("1.3.2 株式注文受付キューテーブル.処理区分をupdateする");
                        //l_mapChanges.put("status", WEB3StatusDef.DEALT);
                        //l_qp.doUpdateQuery(l_params.getPrimaryKey(),l_mapChanges);
                    }
                    catch (Exception l_ex)
                    {
                        if (l_ex instanceof WEB3BaseRuntimeException)
                        {
                            WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
                            if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
                            {
                                log.debug("口座ロック失敗：" + l_params.toString());
                                continue;
                            }
                        }
                        log.error("一件処理にてエラー発生", l_ex);
                        // 1.3.2 * 株式注文受付キューテーブル.処理区分をupdateする
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                        l_qp.doUpdateQuery(l_params);
                    }
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * <p>（get識別コードプレフィクス一覧）。</p>
         * <p>識別コードプレフィクス一覧を取得する。</p>
         * @@return 識別コードプレフィクス一覧
         */
        public String[] getOrderRequestNumberPrefixGroup() {
            return this.orderRequestNumberPrefixGroup;
        }

        /**
         * <p>（set識別コードプレフィクス一覧）。</p>
         * <p>引数の識別コードプレフィクス一覧をプロパティにセットする。</p>
         * @@param orderRequestNumberPrefixGroup 識別コードプレフィクス一覧
         */
        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup) {
            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
        }
    }
}
@
