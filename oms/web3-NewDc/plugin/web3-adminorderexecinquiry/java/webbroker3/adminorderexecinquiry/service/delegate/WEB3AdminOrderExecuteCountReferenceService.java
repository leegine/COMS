head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOrderExecuteCountReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文約定件数照会サービス
                        (WEB3AdminOrderExecuteCountReferenceService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者注文約定件数照会サービス)<BR>
 * <BR>
 * 管理者注文約定件数照会サービスインタフェイス<BR>
 * <BR>
 * WEB3AdminOrderExecuteCountReferenceService interface<BR>
 * <BR>
 * @@author Rajesh Sharma
 * @@version 1.0
 */
public interface WEB3AdminOrderExecuteCountReferenceService extends WEB3BusinessService
{
    /**
     * 注文約定件数照会処理を行う。<BR>
     * <BR>
     * Execute WEB3AdminOrderExecuteCountReferenceService process<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 419ACF990204
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
