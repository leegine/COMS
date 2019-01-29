head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資売買注文通知正常処理一件TransactionCallback(WEB3RuitoTradeOrderNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 李志強(日本中訊) 新規作成
*/

package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;

import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyUnitService;
import webbroker3.xbruito.data.HostRuitoOrderNotifyRow;
import webbroker3.xbruito.data.HostRuitoOrderNotifyParams;
import webbroker3.xbruito.WEB3RuitoTradedOrderNotifyDecisionInterceptor;

/**
 * （累積投資注文受付正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3RuitoTradeOrderNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoTradeOrderNotifyNormalTransactionCallback.class);

    /**
      * 累投注文通知キューParamsオブジェクト。<BR>
      */
    private HostRuitoOrderNotifyParams hostRuitoOrderNotifyParams;

    /**
      * 累投売買注文通知確定インタセプタオブジェクト。<BR>
      */
    private WEB3RuitoTradedOrderNotifyDecisionInterceptor ruitoInterceptor;


    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostRuitoOrderNotifyParams - (累投注文通知キューParams)
     * @@params l_ruitoInterceptor - (累投売買注文通知確定インタセプタ)
     */
    public WEB3RuitoTradeOrderNotifyNormalTransactionCallback(
        HostRuitoOrderNotifyParams l_hostRuitoOrderNotifyParams,
        WEB3RuitoTradedOrderNotifyDecisionInterceptor l_ruitoInterceptor)
    {
        hostRuitoOrderNotifyParams = l_hostRuitoOrderNotifyParams;
        ruitoInterceptor = l_ruitoInterceptor;
    }

   /**
     * トランザクション処理を実施する。<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        WEB3RuitoTradeOrderNotifyUnitService l_ruitoUnitService =
            (WEB3RuitoTradeOrderNotifyUnitService) Services.getService(
                WEB3RuitoTradeOrderNotifyUnitService.class);

        try
        {
            // 1.4.1 注文処理を行う。
            l_ruitoUnitService.notifyTradeOrderNotify(
                hostRuitoOrderNotifyParams,
                ruitoInterceptor);
            //注文処理が正常終了した場合、
            //処理対象の累投注文通知キューレコード.処理区分に”1：処理済”をセットし更新する。
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        HashMap l_map = new HashMap();
        l_map.put("status", WEB3StatusDef.DEALT);
        //1.4.2 キューテーブルに処理済を更新
        String l_strUpdateWhere = " institution_code = ? "
                + " and branch_code = ? " + " and order_request_number = ? ";
        String[] l_strUpdateWhereValues = {
            hostRuitoOrderNotifyParams.getInstitutionCode(),
            hostRuitoOrderNotifyParams.getBranchCode(),
            hostRuitoOrderNotifyParams.getOrderRequestNumber() };

        QueryProcessor l_qp = Processors.getDefaultProcessor();
        l_qp.doUpdateAllQuery(
            HostRuitoOrderNotifyRow.TYPE,
            l_strUpdateWhere,
            l_strUpdateWhereValues,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}














@
