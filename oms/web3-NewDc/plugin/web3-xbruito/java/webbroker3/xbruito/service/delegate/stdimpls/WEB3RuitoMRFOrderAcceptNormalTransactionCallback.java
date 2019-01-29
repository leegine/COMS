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
filename	WEB3RuitoMRFOrderAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資MRF注文受付正常処理一件TransactionCallback(WEB3RuitoMRFOrderAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 李志強(日本中訊) 新規作成
*/

package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.data.HostMrfOrderAcceptParams;
import webbroker3.xbruito.data.HostMrfOrderAcceptRow;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptUnitService;

/**
 * （累積投資MRF注文受付正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3RuitoMRFOrderAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFOrderAcceptNormalTransactionCallback.class);

    /**
      * 累投注文単位オブジェクト。<BR>
      */
    private RuitoOrderUnit rtOrderUnit;

    /**
      * 累投受付確定インタセプタオブジェクト。<BR>
      */
    private WEB3RuitoAcceptedDecisionInterceptor decisionInterceptor;

    /**
      * 累投MRF取消受付キューParamsオブジェクト。<BR>
      */
    private HostMrfOrderAcceptParams hostMrfOrderAcceptParams;


    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_rtOrderUnit - (累投注文単位)
     * @@params l_decisionInterceptor - (累投受付確定インタセプタ)
     * @@params l_hostMrfOrderAcceptParams - (累投MRF取消受付キューParams)
     */
    public WEB3RuitoMRFOrderAcceptNormalTransactionCallback(
        RuitoOrderUnit l_rtOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_decisionInterceptor,
        HostMrfOrderAcceptParams l_hostMrfOrderAcceptParams)
    {
        rtOrderUnit = l_rtOrderUnit;
        decisionInterceptor = l_decisionInterceptor;
        hostMrfOrderAcceptParams = l_hostMrfOrderAcceptParams;
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

        //２）　@累投MRF注文受付UnitServiceを取得する。
        WEB3RuitoMRFOrderAcceptUnitService l_unitService =
            (WEB3RuitoMRFOrderAcceptUnitService) Services.getService(WEB3RuitoMRFOrderAcceptUnitService.class);

        try
        {
            //６）　@注文受付処理を行う。
            String l_strAcceptStatus = hostMrfOrderAcceptParams.getAcceptStatus();
            if (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(l_strAcceptStatus))
            {
                log.debug("notify注文受付完了() begin.");
                //累投MRF注文受付UnitService.notify注文受付完了()
                l_unitService.notifyOrderAcceptComplete(
                    rtOrderUnit,
                    decisionInterceptor);
                log.debug("notify注文受付完了() end.");
            }
            else
            {
                log.debug("notify注文受付失敗() begin.");
                //累投MRF注文受付UnitService.notify注文受付失敗()
                l_unitService.notifyOrderAcceptFail(
                    rtOrderUnit,
                    decisionInterceptor);
                log.debug("notify注文受付失敗() end.");
            }

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        HashMap l_map = new HashMap();
        String l_strUpdateWhere = " institution_code = ? "
                + " and branch_code = ? "
                + " and order_request_number = ?";
        String[] l_strArrayUpdateParams = {
            hostMrfOrderAcceptParams.getInstitutionCode(),
            hostMrfOrderAcceptParams.getBranchCode(),
            hostMrfOrderAcceptParams.getOrderRequestNumber() };

        l_map.put("status", WEB3StatusDef.DEALT);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        // ７）　@処理対象の取消受付キューレコード.処理区分を設定”
        l_queryProcessor.doUpdateAllQuery(
            HostMrfOrderAcceptRow.TYPE,
            l_strUpdateWhere,
            l_strArrayUpdateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}







@
