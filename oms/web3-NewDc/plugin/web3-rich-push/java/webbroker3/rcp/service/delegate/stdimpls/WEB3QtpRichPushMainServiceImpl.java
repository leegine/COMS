head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@QTPリッチクライアントプッシュメインサービス実装クラス(WEB3QtpRichPushMainServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/24 孫(FLJ) 新規作成
 */
package webbroker3.rcp.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.*;
import webbroker3.common.*;
import webbroker3.common.message.*;
import webbroker3.gentrade.*;
import webbroker3.rcp.message.*;
import webbroker3.rcp.service.delegate.*;
import webbroker3.system.tune.threadpool.*;
import webbroker3.util.*;

/**
 * （QTPリッチクライアントプッシュメインサービス実装クラス）。
 * @@author  : 孫(FLJ)
 * @@version : 1.0
 */
public class WEB3QtpRichPushMainServiceImpl
    implements WEB3QtpRichPushMainService
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpRichPushMainServiceImpl.class);

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3QtpRichPushMainServiceImpl()
    {
    }

    /**
     * QTPリッチクライアントプッシュメインサービス処理を実施する。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "WEB3QtpRichPushMainServiceImpl：execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3QtpRichPushMainRequest l_notifyRequest =
            (WEB3QtpRichPushMainRequest) l_request;

        // 1.1. validate()
        l_notifyRequest.validate();

        // 1.2. スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_notifyRequest.threadNo.
            longValue());

        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3QtpAsynRichPushMainServiceImpl(
            l_notifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }

}
@
