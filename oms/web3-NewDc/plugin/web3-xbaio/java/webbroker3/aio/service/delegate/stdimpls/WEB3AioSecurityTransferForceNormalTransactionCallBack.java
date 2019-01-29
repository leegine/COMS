head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferForceNormalTransactionCallBack.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替強制正常処理一件TransactionCallback(WEB3AioSecurityTransferForceNormalTransactionCallBack.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/29 李志強(日本中訊) 新規作成
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

import webbroker3.aio.data.HostMrgsecTransNotifyParams;
import webbroker3.aio.data.HostMrgsecTransNotifyRow;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferForceUnitService;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.util.WEB3LogUtility;



/**
 * （証券振替強制正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3AioSecurityTransferForceNormalTransactionCallBack implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferForceNormalTransactionCallBack.class);

    /**
      * 代用振替強制キューRowオブジェクト。<BR>
      */
    private HostMrgsecTransNotifyRow hostMrgsecTransNotifyRow;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostMrgsecTransNotifyRow - (代用振替強制キューRow)
     */
    public WEB3AioSecurityTransferForceNormalTransactionCallBack(
    HostMrgsecTransNotifyRow l_hostMrgsecTransNotifyRow)
    {
        hostMrgsecTransNotifyRow = l_hostMrgsecTransNotifyRow;
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

        //証券振替強制UnitService
        WEB3AioSecurityTransferForceUnitService l_aioSecurityTransferForceUnitService =
            (WEB3AioSecurityTransferForceUnitService)Services.getService(
                WEB3AioSecurityTransferForceUnitService.class);

        HostMrgsecTransNotifyRow l_hostMrgsecTransNotifyRow = hostMrgsecTransNotifyRow;

        //get the params
        HostMrgsecTransNotifyParams l_hostMrgsecTransNotifyParams =
            new HostMrgsecTransNotifyParams(l_hostMrgsecTransNotifyRow);

        try
        {
            //throw WEB3BaseException
            AioOrderUnit[] l_aioOrderUnits =
                l_aioSecurityTransferForceUnitService.submitOrder(l_hostMrgsecTransNotifyParams);

            if (l_aioOrderUnits == null || l_aioOrderUnits.length < 1)
            {
                log.debug("エラー：該当の振替注文単位がなし");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.2.2 証券振替通知処理を行う。
            //[引数]
            //注文単位： submit注文()の戻り値 --l_aioOrderUnit
            //エラーコード： "0000"（正常）--WEB3ErrorReasonCodeDef.NORMAL
            //受付通知区分： "1"（受付完了）--WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE

            //証券振替通知UnitService
            WEB3AioSecurityTransferNotifyUnitService l_aioSecurityTransferNotifyUnitService =
                (WEB3AioSecurityTransferNotifyUnitService)Services.getService(
                    WEB3AioSecurityTransferNotifyUnitService.class);

            //throw WEB3BaseException
            l_aioSecurityTransferNotifyUnitService.execute(
                l_aioOrderUnits,
                WEB3ErrorReasonCodeDef.NORMAL,
                WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.2.3 代用振替強制キュー.処理区分に以下の値をセットして更新する。
        //"9"（エラー）： 上記処理（Loop内の処理）で例外が発生した場合
        //"1"（処理済）： それ以外の場合
        HashMap l_map = new HashMap();

        //b> where
        String l_strWhereUpdate =
            " institution_code = ? and branch_code = ? and account_code = ? and order_request_number = ? ";

        //c> data
        Object[] l_bindVarsUpdate =
            {l_hostMrgsecTransNotifyRow.getInstitutionCode(),
                l_hostMrgsecTransNotifyRow.getBranchCode(),
                l_hostMrgsecTransNotifyRow.getAccountCode(),
                l_hostMrgsecTransNotifyRow.getOrderRequestNumber()};

        l_map.put("status", WEB3HostStatusDef.COMPLETE_PROCESS);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        //update
        l_queryProcessor.doUpdateAllQuery(
            HostMrgsecTransNotifyRow.TYPE,
            l_strWhereUpdate,
            l_bindVarsUpdate,
            l_map);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}@
