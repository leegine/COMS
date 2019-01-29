head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.28.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3UpdateAccessTimeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : セッションアクセス時刻更新の為のリクエスト・ハンドラ(WEB3UpdateAccessTimeHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26　@菊地(SRA) 新規作成
 */
package webbroker3.login.handler;

import webbroker3.login.message.WEB3UpdateAccessTimeRequest;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * セッションアクセス時刻更新の為のリクエスト・ハンドラ。<BR>
 * @@author      菊地(SRA)
 * @@version     0.01
 */
public final class WEB3UpdateAccessTimeHandler implements MessageHandler
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3UpdateAccessTimeHandler.class);

    /**
     * デフォルトコンストラクタ。<BR>
     * @@roseuid 403EF3C9020A
     */
    public WEB3UpdateAccessTimeHandler()
    {

    }

    /**
     * (セッション・アクセス時刻更新)<BR>
     * セッションアクセス時刻更新の為のリクエストを受け付ける。<BR>
     * 何もしない。負荷軽減の為、ログもデバッグのみ出力する。<BR>
     * @@param l_request
     * @@return Response
     * @@roseuid 403DBE5E0222
     */
    public Response updateAccessTime(WEB3UpdateAccessTimeRequest l_request)
    {
        final String STR_METHOD_NAME =
            "updateAccessTime(WEB3UpdateAccessTimeRequest)";
        log.debug(STR_METHOD_NAME + " .... セッション・アクセス時刻更新");
        return l_request.createResponse();
    }
};
@
