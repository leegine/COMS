head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金受付正常処理一件TransactionCallback(WEB3AioCashoutAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/20 李志強(日本中訊) 新規作成
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
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostPaymentAcceptParams;
import webbroker3.aio.data.HostPaymentAcceptRow;
import webbroker3.aio.service.delegate.WEB3AioCashTransferAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashTransferCompleteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （入金受付正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 李志強
 * @@version 1.0
 */
public class WEB3AioCashoutAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutAcceptNormalTransactionCallback.class);

    /**
      * 注文単位オブジェクト。<BR>
      */
    private AioOrderUnit aioOrderUnit;


    /**
      * 出金請求受付キューParamsオブジェクト。<BR>
      */
    private HostPaymentAcceptParams hostPaymentAcceptParams;


    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_aioOrderUnit - (注文単位)
     * @@params l_hostCashTransOrderAcceptParams - (出金請求受付キューParams)
     */
    public WEB3AioCashoutAcceptNormalTransactionCallback(
        AioOrderUnit l_aioOrderUnit,
        HostPaymentAcceptParams l_hostPaymentAcceptParams)
    {
        aioOrderUnit = l_aioOrderUnit;
        hostPaymentAcceptParams = l_hostPaymentAcceptParams;
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

        WEB3AioCashTransferAcceptUnitService l_aioCashTransferAcceptUnitService =
            (WEB3AioCashTransferAcceptUnitService) Services.getService(
                WEB3AioCashTransferAcceptUnitService.class);

        WEB3AioCashTransferCompleteUnitService l_aioCashTransferCompleteUnitService =
            (WEB3AioCashTransferCompleteUnitService) Services.getService(
                WEB3AioCashTransferCompleteUnitService.class);

        AioOrderUnit l_aioOrderUnit = aioOrderUnit;
        HostPaymentAcceptParams l_hostPaymentAcceptParams = hostPaymentAcceptParams;

        try
        {
            // 1.2.2)入出金受付DB更新処理を行う。
            //[引数]
            //注文単位： 取得した注文単位オブジェクト
            //エラーコード： 入出金伝票受付キューテーブル.エラーメッセージ
            //受付通知区分： 入出金伝票受付キューテーブル.受付通知区分
            l_aioCashTransferAcceptUnitService.execute(l_aioOrderUnit,
                l_hostPaymentAcceptParams.getErrorMessage(),
                l_hostPaymentAcceptParams.getAcceptDiv());

            //出金請求受付キューテーブル.受付通知区分 = "1"（受付完了）の場合
            if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(l_hostPaymentAcceptParams.getAcceptDiv()))
            {
                //1.2.3)受付完了の場合
                //入出金完了処理に伴う注文データの更新とトランザクションデータの生成を行う。
                //[引数]
                //注文単位： get注文単位()で取得した注文単位
                l_aioCashTransferCompleteUnitService.completeCashTransfer(l_aioOrderUnit);
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

        HashMap l_hashMap = new HashMap();
        l_hashMap.put("status", WEB3StatusDef.DEALT);

        String l_strRequestCode = l_hostPaymentAcceptParams.getRequestCode();

        String l_strUpdateWhere = "request_code = ? and institution_code = ? " +
            "and branch_code = ? and account_code = ? and order_request_number = ? ";

        String l_strInstitutionCode = l_hostPaymentAcceptParams.getInstitutionCode();
        String l_strBranchCode = l_hostPaymentAcceptParams.getBranchCode();
        String l_strAccountCode = l_hostPaymentAcceptParams.getAccountCode();
        String l_strOrderRequestNumber = l_hostPaymentAcceptParams.getOrderRequestNumber();

        Object[] l_objUpdateWhereValues = {l_strRequestCode, l_strInstitutionCode,
            l_strBranchCode, l_strAccountCode, l_strOrderRequestNumber};

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doUpdateAllQuery(
            HostPaymentAcceptRow.TYPE,
            l_strUpdateWhere,
            l_objUpdateWhereValues,
            l_hashMap);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}


@
