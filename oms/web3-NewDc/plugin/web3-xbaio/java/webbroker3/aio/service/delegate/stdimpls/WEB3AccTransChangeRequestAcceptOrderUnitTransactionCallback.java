head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替請求受付注文単位処理TransactionCallback(WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/05 李志強(日本中訊) 新規作成
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostTransferAcceptRow;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （振替請求受付正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback.class);

    /**
      * 振替請求受付キューRowオブジェクト。<BR>
      */
    private HostTransferAcceptRow hostTransferAcceptRow;

    /**
      * 注文単位オブジェクト。<BR>
      */
    private AioOrderUnit aioOrderUnit;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostTransferReceiptParams - (振替請求受付キューRow)
     * @@params l_aioOrderUnit - (注文単位)
     */
    public WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback(
        HostTransferAcceptRow l_hostTransferAcceptRow,
        AioOrderUnit l_aioOrderUnit)
    {
        hostTransferAcceptRow = l_hostTransferAcceptRow;
        aioOrderUnit = l_aioOrderUnit;
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

        WEB3AccTransChangeAcceptUnitService l_AcceptService =
            (WEB3AccTransChangeAcceptUnitService) Services.getService(
                WEB3AccTransChangeAcceptUnitService.class);

        WEB3AccTransChangeCompleteUnitService l_CompleteService =
            (WEB3AccTransChangeCompleteUnitService) Services.getService(
                WEB3AccTransChangeCompleteUnitService.class);
        try
        {
            l_AcceptService.execute(
                aioOrderUnit,
                hostTransferAcceptRow.getErrorMessage(),
                hostTransferAcceptRow.getAcceptDiv());

            if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(
                hostTransferAcceptRow.getAcceptDiv()))
            {
                // 2 - 2 - 2)振替完了処理に伴う注文データの更新と
                // トランザクションデータの生成を行う。
                // [引数]
                // 注文単位： 注文単位オブジェクト
                l_CompleteService.completeChange(
                aioOrderUnit);
            }
        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}
@
