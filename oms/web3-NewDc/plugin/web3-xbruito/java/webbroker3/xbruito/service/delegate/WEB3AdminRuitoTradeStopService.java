head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者累投銘柄別売買停止サービス (WEB3AdminRuitoTradeStopService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * 管理者累投銘柄別売買停止サービス<BR>
 */
public interface WEB3AdminRuitoTradeStopService extends WEB3BusinessService 
{
    /**
     * 管理者累投銘柄別売買停止サービス。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4069320902DE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
