head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信注文受付正常処理一件TransactionCallback(WEB3MutualOrderAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 李志強(日本中訊) 新規作成
*/


package webbroker3.mf.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.data.HostXbmfOrderAcceptParams;
import webbroker3.mf.data.HostXbmfOrderAcceptRow;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * （投信注文受付正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3MutualOrderAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderAcceptNormalTransactionCallback.class);

    /**
      * 投信注文単位オブジェクト。<BR>
      */
    private MutualFundOrderUnit mfOrderUnit;

    /**
      * 投信受付確定インタセプタオブジェクト。<BR>
      */
    private WEB3MutualFundAcceptConfirmInterceptor confirmInterceptor;

    /**
      * 投信注文受付キューParamsオブジェクト。<BR>
      */
    private HostXbmfOrderAcceptParams orderAcceptParams;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_mfOrderUnit - (投信注文単位)
     * @@params l_confirmInterceptor - (投信受付確定インタセプタ)
     * @@params l_orderAcceptParams - (投信注文受付キューParams)
     */
    public WEB3MutualOrderAcceptNormalTransactionCallback(
        MutualFundOrderUnit l_mfOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor,
        HostXbmfOrderAcceptParams l_orderAcceptParams)
    {
        mfOrderUnit = l_mfOrderUnit;
        confirmInterceptor = l_confirmInterceptor;
        orderAcceptParams = l_orderAcceptParams;
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

        // ２）　@投信注文受付UnitServiceを取得する
        WEB3MutualOrderAcceptUnitService l_OrderAcceptUnitService =
            (WEB3MutualOrderAcceptUnitService) Services.getService(
                WEB3MutualOrderAcceptUnitService.class);

        MutualFundOrderUnit l_mfOrderUnit = mfOrderUnit;
        WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor = confirmInterceptor;
        HostXbmfOrderAcceptParams l_orderAcceptParams = orderAcceptParams;

        String l_strAcceptStatus =
            l_orderAcceptParams.getAcceptStatus();
        try
        {
            if (WEB3AcceptStatusDef.OVER.equals(l_strAcceptStatus))
            {
                //投信注文受付UnitService.notify注文受付完了()をコールする
                l_OrderAcceptUnitService.notifyOrderAcceptComplete(
                    l_mfOrderUnit,
                    l_confirmInterceptor);
            }
            else if (
                WEB3AcceptStatusDef.ERROR.equals(l_strAcceptStatus))
            {
                //投信注文受付UnitService.notify注文受付失敗()をコールする
                l_OrderAcceptUnitService.notifyOrderAcceptFail(
                    l_mfOrderUnit,
                    l_confirmInterceptor);
            }
            else
            {
                log.error("予期しないシステムエラーが発生しました。");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
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
        String l_strUpdateWhere =
            " institution_code = ? " +
            " and branch_code = ? " +
            " and order_request_number = ?";
        String[] l_updateParams =
            {l_orderAcceptParams.getInstitutionCode(),
             l_orderAcceptParams.getBranchCode(),
             l_orderAcceptParams.getOrderRequestNumber()};

        l_map.put("status", WEB3StatusDef.DEALT);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        // do update
        l_queryProcessor.doUpdateAllQuery(
            HostXbmfOrderAcceptRow.TYPE,
            l_strUpdateWhere,
            l_updateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}
@
