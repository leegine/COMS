head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.17.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSettleReportService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済連携レポートサービスインターフェイス(WEB3AdminAioSettleReportService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 韋念瓊 (中訊) 新規作成   
                   2004/10/27 周勇(中訊) レビュー                 
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (決済連携レポートサービス)<BR>
 * 決済連携レポートサービスインターフェイス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */

public interface WEB3AdminAioSettleReportService extends WEB3BusinessService 
{

    /**
     * 決済連携レポートサービス処理を行う。
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@roseuid 4101014F0232
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
