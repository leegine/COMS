head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュメインサービス実装(WEB33RichPushMainServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
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
 * （リッチクライアントプッシュメインサービス実装クラス）。
 * @@author  : 劉(FLJ)
 * @@version : 1.0
 */
public class WEB3RichPushMainServiceImpl
    implements WEB3RichPushMainService
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushMainServiceImpl.class);

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3RichPushMainServiceImpl()
    {
    }

    /**
     * リッチクライアントプッシュメインサービス処理を実施する。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "WEB33RichPushMainServiceImpl：execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3RichPushMainRequest l_notifyRequest =
            (WEB3RichPushMainRequest) l_request;

        // 1.1. validate()
        l_notifyRequest.validate();

        // 1.2. スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_notifyRequest.threadNo.
            longValue());

        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynRichPushMainServiceImpl(
            l_notifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }

}
@
