head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccAdditionalContentsSelectService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 追加内容選択サービス(WEB3ToSuccAdditionalContentsSelectService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 呉艶飛(中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (追加内容選択サービス)<BR>
 * 追加内容選択サービスインタフェイス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public interface WEB3ToSuccAdditionalContentsSelectService extends WEB3BusinessService 
{
    
    /**
     * 追加内容選択サービス処理を行う。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D173F0068
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
