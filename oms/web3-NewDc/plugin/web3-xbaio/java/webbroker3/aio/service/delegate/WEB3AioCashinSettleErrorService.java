head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinSettleErrorService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済エラーサービスインターフェイス(WEB3AioCashinSettleErrorService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 王蘭芬(中訊) 新規作成
                   2004/10/22 周勇(中訊) レビュー
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (決済エラーサービス)<BR>
 * 決済エラーサービスインターフェイス<BR>
 *
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public interface WEB3AioCashinSettleErrorService extends WEB3BusinessService 
{
    
    /**
     * 決済エラー処理を行う。
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 411B30240012
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)throws WEB3BaseException;
}
@
