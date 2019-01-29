head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消受付正常処理一件TransactionCallback(WEB3MutualCancelAcceptNormalTransactionCallback.java)
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
import webbroker3.common.define.WEB3CancelAcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.data.HostXbmfCancelAcceptParams;
import webbroker3.mf.data.HostXbmfCancelAcceptRow;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptUnitService;
import webbroker3.util.WEB3LogUtility;


/**
 * （投資信託取消受付正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3MutualCancelAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelAcceptNormalTransactionCallback.class);

    /**
      * 投信注文単位オブジェクト。<BR>
      */
    private MutualFundOrderUnit mfOrderUnit;

    /**
      * 投信受付確定インタセプタオブジェクト。<BR>
      */
    private WEB3MutualFundAcceptConfirmInterceptor confirmInterceptor;

    /**
      * 投信取消受付キューParamsオブジェクト。<BR>
      */
    private HostXbmfCancelAcceptParams cancelAcceptParams;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_mfOrderUnit - (投信注文単位)
     * @@params l_confirmInterceptor - (投信受付確定インタセプタ)
     * @@params l_cancelAcceptParams - (投信取消受付キューParams)
     */
    public WEB3MutualCancelAcceptNormalTransactionCallback(
        MutualFundOrderUnit l_mfOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor,
        HostXbmfCancelAcceptParams l_cancelAcceptParams)
    {
        mfOrderUnit = l_mfOrderUnit;
        confirmInterceptor = l_confirmInterceptor;
        cancelAcceptParams = l_cancelAcceptParams;
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

        // ２）　@投信取消受付UnitServiceを取得する
        WEB3MutualCancelAcceptUnitService l_cancelAcceptUnitService =
            (WEB3MutualCancelAcceptUnitService) Services.getService(
                WEB3MutualCancelAcceptUnitService.class);

        MutualFundOrderUnit l_mfOrderUnit = mfOrderUnit;
        WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor = confirmInterceptor;
        HostXbmfCancelAcceptParams l_cancelAcceptParams = cancelAcceptParams;

        String l_strAcceptStatus = l_cancelAcceptParams.getAcceptStatus();

        try
        {
            //－投信取消受付キューParams.get取消通知区分()の戻り値が”1：取消完了”の場合
            if (WEB3CancelAcceptStatusDef.COMPLETE.equals(l_strAcceptStatus))
            {
                //投信取消受付UnitService.notify取消受付完了()をコールする
                l_cancelAcceptUnitService.notifyCancelAcceptComplete(
                    l_mfOrderUnit, l_confirmInterceptor);
            }
            else
            {
                //投信取消受付キューParams.get取消通知区分()の戻り値が ”2：取消失敗”の場合
                if (WEB3CancelAcceptStatusDef.FAIL.equals(l_strAcceptStatus))
                {
                    //投信取消受付UnitService.notify取消受付失敗()をコールする
                    l_cancelAcceptUnitService.notifyCancelAcceptFail(
                        l_mfOrderUnit, l_confirmInterceptor);
                }
                else
                {
                    log.debug("予期しないシステムエラーが発生しました。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                }
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
        // 処理対象の取消受付キューレコード.処理区分を設定用
        String l_strUpdateWhere = " institution_code = ? "
                           + " and branch_code = ? "
                           + " and order_request_number = ?";
        String[] l_strArrayUpdateParams = {
            l_cancelAcceptParams.getInstitutionCode(),
            l_cancelAcceptParams.getBranchCode(),
            l_cancelAcceptParams.getOrderRequestNumber()
        };

        l_map.put("status", WEB3StatusDef.DEALT);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        // ８）　@処理対象の取消受付キューレコード.処理区分を設定”
        l_queryProcessor.doUpdateAllQuery(
            HostXbmfCancelAcceptRow.TYPE,
            l_strUpdateWhere,
            l_strArrayUpdateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}
@
