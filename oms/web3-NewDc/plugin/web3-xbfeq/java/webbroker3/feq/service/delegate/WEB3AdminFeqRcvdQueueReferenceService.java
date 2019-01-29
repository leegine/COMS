head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqRcvdQueueReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式RCVDキュー照会サービス(WEB3AdminFeqRcvdQueueReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 徐大方 (中訊) 新規作成
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者外国株式RCVDキュー照会サービス)<BR>
 * 管理者外国株式RCVDキュー照会サービスインタフェース<BR>
 * <BR>
 * （トランザクション属性：設定不要）<BR>
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public interface WEB3AdminFeqRcvdQueueReferenceService extends WEB3BusinessService 
{
    /**
     * 外国株式（管）ＲＣＶＤキュー照会処理を実施する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
