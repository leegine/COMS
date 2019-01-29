head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashoutAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金受付サービスインターフェイス(WEB3AioCashoutAcceptService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 周勇 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー    
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (出金受付サービス)<BR>
 * 出金受付サービスインターフェイス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public interface WEB3AioCashoutAcceptService extends WEB3BackBusinessService 
{
    
    /**
     * 出金受付処理を行う。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3BackResponse
     * @@roseuid 40FE60050065
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
