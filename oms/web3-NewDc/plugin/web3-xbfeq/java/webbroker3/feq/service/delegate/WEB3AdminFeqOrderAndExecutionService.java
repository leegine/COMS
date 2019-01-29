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
filename	WEB3AdminFeqOrderAndExecutionService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式約定入力サービス(WEB3AdminFeqOrderAndExecutionService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/24 王暁傑 (中訊) 新規作成
                 : 2005/08/02 韋念瓊(中訊) レビュー   
*/
package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者外国株式約定入力サービス)<BR>
 * 管理者外国株式約定入力サービスインタフェイス<BR>
 * 
 * @@author 王暁傑
 * @@version 1.0
 */
public interface WEB3AdminFeqOrderAndExecutionService extends WEB3BusinessService 
{
    
    /**
     * 外国株式約定入力処理を実施する。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FFB1F004E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
