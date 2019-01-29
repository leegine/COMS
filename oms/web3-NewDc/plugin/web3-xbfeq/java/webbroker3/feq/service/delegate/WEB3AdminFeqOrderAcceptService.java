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
filename	WEB3AdminFeqOrderAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文受付取消認証サービス(WEB3AdminFeqOrderAcceptService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 郭英 (中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者外国株式注文受付取消認証サービス)<BR>
 * 管理者外国株式注文受付取消認証サービスインタフェイス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3AdminFeqOrderAcceptService extends WEB3BusinessService 
{
    
    /**
     * 外国株式注文（注文取消）受付入力処理を実施する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FECAF006D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
