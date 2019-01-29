head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3AsynRlsCondOrderNotifyTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : ルールエンジン通知一件TransactionCallback(WEB3AsynRlsCondOrderNotifyTransactionCallback.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/01 劉(FLJ) 新規作成
 */

package webbroker3.omsadaptor.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.message.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;
import webbroker3.omsadaptor.service.delegate.*;
import webbroker3.rlsgateway.data.*;
import webbroker3.rlsgateway.define.*;
import webbroker3.util.*;

/**
 * （ルールエンジン通知一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 劉
 * @@version 1.0
 */
public class WEB3AsynRlsCondOrderNotifyTransactionCallback
    implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynRlsCondOrderNotifyTransactionCallback.class);

    /**
     * ルールエンジン通知Paramsオブジェクト。
     */
    private RlsConOrderHitNotifyParams notifyParams;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_orderUnit - (注文単位)
     * @@params l_orderExecNotifyParams - (株式出来通知キューParams)
     */
    public WEB3AsynRlsCondOrderNotifyTransactionCallback(
        RlsConOrderHitNotifyParams l_notifyParams)
    {
        notifyParams = l_notifyParams;
    }

    /**
     * トランザクション処理を実施する。<BR>
     * ルールエンジン通知一件TransactionCallback.process()部分参照。<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process() throws DataQueryException, DataNetworkException,
        DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        WEB3RlsCondOrderSubmitService service = (
            WEB3RlsCondOrderSubmitService) Services.getService(
            WEB3RlsCondOrderSubmitService.class);

        String l_strSubmitResult = WEB3RlsOrderSubmitErrorCodeDef.SUCCEED;

        try
        {

            //発注
            l_strSubmitResult = service.submitRlsCondOrder(notifyParams);
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }
        //キューの更新はルールエンジン通知一件TransactionCallback内部で行う
        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
        java.sql.Timestamp l_lastTime=GtlUtils.getSystemTimestamp();
        if (WEB3RlsOrderSubmitErrorCodeDef.DELAY.equals(l_strSubmitResult))
        {
            notifyParams.setStatus(WEB3RlsNotifyStatusDef.PROGRAM_ERROR);
        }
        else
        {
            notifyParams.setStatus(WEB3RlsNotifyStatusDef.DEAL);
            notifyParams.setOrderSubmitTimestamp(l_lastTime);
        }

        notifyParams.setOrderSubmitErrorCode(l_strSubmitResult);
        notifyParams.setLastUpdatedTimestamp(l_lastTime);
        l_QueryProcessor.doUpdateQuery(notifyParams);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
