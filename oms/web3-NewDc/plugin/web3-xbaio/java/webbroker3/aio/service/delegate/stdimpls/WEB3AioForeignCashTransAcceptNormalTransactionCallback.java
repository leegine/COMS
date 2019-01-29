head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioForeignCashTransAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨入出金受付正常処理一件TransactionCallback(WEB3AioForeignCashTransAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/03 徐宏偉 (中訊) 新規作成
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

import webbroker3.aio.data.HostFCashTransOrderAcceptParams;
import webbroker3.aio.data.HostFCashTransOrderAcceptRow;
import webbroker3.aio.service.delegate.WEB3AioCashTransferAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashTransferCompleteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （外貨入出金受付正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioForeignCashTransAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioForeignCashTransAcceptNormalTransactionCallback.class);

    /**
      * 注文単位オブジェクト。<BR>
      */
    private AioOrderUnit aioOrderUnit;

    /**
      * 外貨入出金伝票受付キューParamsオブジェクト。<BR>
      */
    private HostFCashTransOrderAcceptParams hostFCashTransOrderAcceptParams;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_aioOrderUnit - (注文単位)
     * @@params l_hostFCashTransOrderAcceptParams - (外貨入出金伝票受付キューParams)
     */
    public WEB3AioForeignCashTransAcceptNormalTransactionCallback(
        AioOrderUnit l_aioOrderUnit,
        HostFCashTransOrderAcceptParams l_hostFCashTransOrderAcceptParams)
    {
        aioOrderUnit = l_aioOrderUnit;
        hostFCashTransOrderAcceptParams = l_hostFCashTransOrderAcceptParams;
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

        //入出金受付UnitServiceインターフェイス
        WEB3AioCashTransferAcceptUnitService l_aioCashTransferAcceptUnitService =
            (WEB3AioCashTransferAcceptUnitService) Services.getService(
                WEB3AioCashTransferAcceptUnitService.class);

        //入出金完了UnitServiceインターフェイス
        WEB3AioCashTransferCompleteUnitService l_aioCashTransferCompleteUnitService =
            (WEB3AioCashTransferCompleteUnitService) Services.getService(
                WEB3AioCashTransferCompleteUnitService.class);

        AioOrderUnit l_aioOrderUnit = aioOrderUnit;
        HostFCashTransOrderAcceptParams l_hostFCashTransOrderAcceptParams = 
            hostFCashTransOrderAcceptParams;

        try
        {
            // 1.2.2)入出金受付DB更新処理を行う。  
            //[引数]  
            //注文単位： 取得した注文単位オブジェクト  
            //エラーコード： 外貨入出金伝票受付キューテーブル.エラーメッセージ  
            //受付通知区分： 外貨入出金伝票受付キューテーブル.受付通知区分 
            l_aioCashTransferAcceptUnitService.execute(
                l_aioOrderUnit,
                l_hostFCashTransOrderAcceptParams.getErrorMessage(),
                l_hostFCashTransOrderAcceptParams.getAcceptDiv());

            // 外貨入出金伝票受付キューテーブル.受付通知区分 = "1"（受付完了）の場合
            if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(
                l_hostFCashTransOrderAcceptParams.getAcceptDiv()))
            {
                //1.2.3)受付完了の場合
                // 1.2.3.1)入出金完了処理に伴う注文データの更新とトランザクションデータの生成を行う。
                //[引数]
                //注文単位： get注文単位()で取得した注文単位オブジェクト
                l_aioCashTransferCompleteUnitService.completeCashTransfer(l_aioOrderUnit);
            }
        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }

        //1.2.4)外貨入出金伝票受付キューテーブルの処理区分の更新
        //外貨入出金伝票受付キューテーブル.処理区分に以下の値をセットして更新する。
        //"1"（処理済）： 
        HashMap l_hashMap = new HashMap();
        l_hashMap.put("status", WEB3StatusDef.DEALT);

        String l_strUpdateWhere = "request_code = ? and institution_code = ? " +
            "and branch_code = ? and account_code = ? and order_request_number = ? ";

        String l_strRequestCode = 
            l_hostFCashTransOrderAcceptParams.getRequestCode();
        String l_strInstitutionCode = 
            l_hostFCashTransOrderAcceptParams.getInstitutionCode();
        String l_strBranchCode = 
            l_hostFCashTransOrderAcceptParams.getBranchCode();
        String l_strAccountCode = 
            l_hostFCashTransOrderAcceptParams.getAccountCode();
        String l_strOrderRequestNumber = 
            l_hostFCashTransOrderAcceptParams.getOrderRequestNumber();

        Object[] l_objUpdaeWereValues = {
            l_strRequestCode, 
            l_strInstitutionCode,
            l_strBranchCode, 
            l_strAccountCode, 
            l_strOrderRequestNumber};

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doUpdateAllQuery(
            HostFCashTransOrderAcceptRow.TYPE,
            l_strUpdateWhere,
            l_objUpdaeWereValues,
            l_hashMap);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}

@
