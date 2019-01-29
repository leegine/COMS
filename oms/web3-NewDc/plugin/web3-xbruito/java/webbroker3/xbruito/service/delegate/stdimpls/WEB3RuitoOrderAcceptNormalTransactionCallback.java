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
filename	WEB3RuitoOrderAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資注文受付正常処理一件TransactionCallback(WEB3RuitoOrderAcceptNormalTransactionCallback.java)
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
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.data.HostRuitoOrderAcceptParams;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptUnitService;

/**
 * （累積投資注文受付正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3RuitoOrderAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderAcceptNormalTransactionCallback.class);

    /**
      * 累投注文単位オブジェクト。<BR>
      */
    private RuitoOrderUnit rtOrderUnit;

    /**
      * 累投受付確定インタセプタオブジェクト。<BR>
      */
    private WEB3RuitoAcceptedDecisionInterceptor decisionInterceptor;

    /**
      * 累投注文受付キューParamsオブジェクト。<BR>
      */
    private HostRuitoOrderAcceptParams ruitoOrderAcceptParams;


    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_rtOrderUnit - (累投注文単位)
     * @@params l_decisionInterceptor - (累投受付確定インタセプタ)
     * @@params l_ruitoOrderAcceptParams - (累投注文受付キューParams)
     */
    public WEB3RuitoOrderAcceptNormalTransactionCallback(
        RuitoOrderUnit l_rtOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_decisionInterceptor,
        HostRuitoOrderAcceptParams l_ruitoOrderAcceptParams)
    {
        rtOrderUnit = l_rtOrderUnit;
        decisionInterceptor = l_decisionInterceptor;
        ruitoOrderAcceptParams = l_ruitoOrderAcceptParams;
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


        //受付通知区分的取得
        String l_strAcceptStatus = ruitoOrderAcceptParams.getAcceptStatus();

        //1.2　@累投注文受付UnitServiceを取得する。
        WEB3RuitoOrderAcceptUnitService l_ruitoOrderAcceptUnitService =
            (WEB3RuitoOrderAcceptUnitService) Services.getService(
                WEB3RuitoOrderAcceptUnitService.class);

        try
        {
            //処理対象の取消受付キューレコード.処理区分を設定用
            if ((WEB3AcceptStatusDef.OVER).equals(
                l_strAcceptStatus))
            {
                //累投注文受付UnitService.notify注文受付完了()
                l_ruitoOrderAcceptUnitService.notifyOrderAcceptComplete(
                    rtOrderUnit,
                    decisionInterceptor);
            }
            else
            {
                //累投注文受付UnitService.notify注文受付失敗()
                l_ruitoOrderAcceptUnitService.notifyOrderAcceptFail(
                    rtOrderUnit,
                    decisionInterceptor);
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
        l_map.put("status", WEB3StatusDef.DEALT);
        QueryProcessor l_processorObject =
            Processors.getDefaultProcessor();
        // 1.4.4　@キューテーブルの処理区分を更新
        //証券会社コード的取得
        String l_strInstatutionCode =
            ruitoOrderAcceptParams.getInstitutionCode();

        //部店コード的取得
        String l_strBranchCode =
            ruitoOrderAcceptParams.getBranchCode();

        //識別コード的取得
        String l_strOrderRequestNumber =
            ruitoOrderAcceptParams.getOrderRequestNumber();

        String l_strUpdateWhere =
            " institution_code = ?" +
            " and branch_code = ?" +
            " and order_request_number = ?";
        String[] l_strArrayUpdateParams =
            {
                l_strInstatutionCode,
                l_strBranchCode,
                l_strOrderRequestNumber};
        // do update
        l_processorObject.doUpdateAllQuery(
            HostRuitoOrderAcceptParams.TYPE,
            l_strUpdateWhere,
            l_strArrayUpdateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}

@
