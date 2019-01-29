head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.05.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionCancelOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）OP取消注文サービ（WEB3ToSuccOptionCancelOrderService.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 金傑 (中訊) 新規作成 モデルNo.280
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 *  (（連続）OP取消注文サービ)<BR>
 * （連続）OP取消注文サービスインターフェイス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public interface WEB3ToSuccOptionCancelOrderService extends WEB3BusinessService
{

    /**
     * （連続）OP取消サービス処理を実施する。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A97840019F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
