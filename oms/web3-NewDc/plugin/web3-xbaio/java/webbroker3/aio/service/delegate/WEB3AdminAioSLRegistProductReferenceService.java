head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSLRegistProductReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保登録銘柄照会サービスクラス(WEB3AdminAioSLRegistProductReferenceService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 張騰宇 (中訊) 新規作成 モデル760
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (担保登録銘柄照会サービス)<BR>
 * 担保登録銘柄照会サービス<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AdminAioSLRegistProductReferenceService extends WEB3BusinessService
{
    /**
     * 担保登録銘柄照会処理を実施する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
