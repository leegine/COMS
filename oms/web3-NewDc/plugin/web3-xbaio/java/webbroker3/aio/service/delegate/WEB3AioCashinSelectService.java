head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.15.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinSelectService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン入金選択サービスインターフェイス(WEB3AioCashinSelectService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 屈陽 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー    
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (オンライン入金選択サービス)<BR>
 * オンライン入金選択サービスインターフェイス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public interface WEB3AioCashinSelectService extends WEB3BusinessService 
{
    
    /**
     * オンライン入金選択サービス処理を実施する。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40FE64A103D0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
