head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替通知正常処理一件TransactionCallback(WEB3AioSecurityTransferNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/28 李志強(日本中訊) 新規作成
*/


package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostMrgsecTransAcceptRow;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.util.WEB3LogUtility;




/**
 * （証券振替通知正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3AioSecurityTransferNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferNotifyNormalTransactionCallback.class);

    /**
      * 代用振替受付キューRowオブジェクト。<BR>
      */
    private HostMrgsecTransAcceptRow hostMrgsecTransAcceptRow;

    /**
      * 注文単位オブジェクト。<BR>
      */
    private AioOrderUnit[] aioOrderUnits;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostMrgsecTransAcceptRow - (代用振替受付キューRow)
     * @@params l_aioOrderUnits - (注文単位)
     */
    public WEB3AioSecurityTransferNotifyNormalTransactionCallback(
        HostMrgsecTransAcceptRow l_hostMrgsecTransAcceptRow,
        AioOrderUnit[] l_aioOrderUnits)
    {
        hostMrgsecTransAcceptRow = l_hostMrgsecTransAcceptRow;
        aioOrderUnits = l_aioOrderUnits;
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

        HostMrgsecTransAcceptRow l_hostMrgsecTransAcceptRow = hostMrgsecTransAcceptRow;
        AioOrderUnit[] l_aioOrderUnits = aioOrderUnits;

        try
        {
            WEB3AioSecurityTransferNotifyUnitService l_unitService =
                (WEB3AioSecurityTransferNotifyUnitService)
                    Services.getService(WEB3AioSecurityTransferNotifyUnitService.class);

            //1.2.2 execute(AioOrderUnit[], String, String)
            //[引数]
            // 注文単位： get振替注文単位()の戻り値
            // エラーコード： 代用振替受付キュー.エラーメッセージ
            // 受付通知区分： 代用振替受付キュー.受付通知区分
            l_unitService.execute(
                l_aioOrderUnits,
                l_hostMrgsecTransAcceptRow.getErrorMessage(),
                l_hostMrgsecTransAcceptRow.getAcceptDiv());
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
            " institution_code = ? and branch_code = ? and account_code = ? and order_request_number = ? ";
        String[] l_strUpdateWhereValues = {
                l_hostMrgsecTransAcceptRow.getInstitutionCode(),
                l_hostMrgsecTransAcceptRow.getBranchCode(),
                l_hostMrgsecTransAcceptRow.getAccountCode(),
                l_hostMrgsecTransAcceptRow.getOrderRequestNumber() };

        l_map.put("status", WEB3HostStatusDef.COMPLETE_PROCESS);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        //  1.2.3 キューテーブルのレコードの処理区分の更新
        l_queryProcessor.doUpdateAllQuery(
            HostMrgsecTransAcceptRow.TYPE,
            l_strUpdateWhere,
            l_strUpdateWhereValues,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}




@
