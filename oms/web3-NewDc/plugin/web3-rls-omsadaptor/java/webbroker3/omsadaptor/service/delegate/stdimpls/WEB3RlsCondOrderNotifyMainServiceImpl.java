head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsCondOrderNotifyMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@ルールエンジン通知メインサービス実装(WEB33RlsCondOrderNotifyMainServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/01 劉(FLJ) 新規作成
 */
package webbroker3.omsadaptor.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.*;
import webbroker3.common.*;
import webbroker3.common.message.*;
import webbroker3.gentrade.*;
import webbroker3.omsadaptor.message.*;
import webbroker3.omsadaptor.service.delegate.*;
import webbroker3.system.tune.threadpool.*;
import webbroker3.util.*;

/**
 * （ルールエンジン通知メインサービス実装クラス）。
 * @@author  : 劉(FLJ)
 * @@version : 1.0
 */
public class WEB3RlsCondOrderNotifyMainServiceImpl
    implements WEB33RlsCondOrderNotifyMainService
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsCondOrderNotifyMainServiceImpl.class);

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3RlsCondOrderNotifyMainServiceImpl()
    {
    }

    /**
     * ルールエンジン通知メインサービス処理を実施する。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "WEB33RlsCondOrderNotifyMainServiceImpl：execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3RlsCondOrderNotifyMainRequest l_notifyRequest =
            (WEB3RlsCondOrderNotifyMainRequest) l_request;

        // 1.1. validate()
        l_notifyRequest.validate();
        // 1.2. スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_notifyRequest.threadNo.
            longValue());
        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynRlsCondOrderNotifyMainServiceImpl(
            l_notifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }

}
@
