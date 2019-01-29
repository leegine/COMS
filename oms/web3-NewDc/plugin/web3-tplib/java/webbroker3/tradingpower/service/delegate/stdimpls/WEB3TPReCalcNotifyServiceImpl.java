head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算通知サービスImpl(WEB3TPReCalcNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ResourceBusyException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyRow;
import webbroker3.tradingpower.define.WEB3TPStatusDef;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyRequest;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyService;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (余力計算通知サービスImpl)
 */
public class WEB3TPReCalcNotifyServiceImpl implements WEB3TPReCalcNotifyService
{
    /**
     * （ログ出力ユーティリティ)。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPReCalcNotifyServiceImpl.class);

    /**
     * @@roseuid 423541390181
     */
    public WEB3TPReCalcNotifyServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * 引数で与えられたリクエストを基に業務処理を行い、処理結果をレスポンスに設定してを返す。<BR>
     * <BR>
     * シーケンス図「（余力再計算通知サービス）execute」参照。<BR>
     *  <BR>
     * @@param l_request - リクエスト
     * @@return 処理結果が設定されたレスポンス
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 41F4A5840007
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3TPReCalcNotifyRequest l_reCalcRequest = (WEB3TPReCalcNotifyRequest)l_request;
        l_reCalcRequest.validate();

        // 1.2. スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_reCalcRequest.threadNo.longValue());
        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService)Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynTPReCalcNotifyServiceImpl(l_reCalcRequest));

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }
}
@
