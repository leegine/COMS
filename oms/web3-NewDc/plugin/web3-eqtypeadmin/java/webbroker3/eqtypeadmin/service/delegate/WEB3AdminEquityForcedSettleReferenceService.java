head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文照会サービス(WEB3AdminEquityForcedSettleReferenceService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 趙林鵬 (中訊) 新規作成 モデルNo.129
*/

package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者・強制決済注文照会サービス)<BR>
 * 管理者・強制決済注文照会サービスインターフェイス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public interface WEB3AdminEquityForcedSettleReferenceService extends WEB3BusinessService
{
    /**
     * 強制決済注文照会処理を行う。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4601E8E70137
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
