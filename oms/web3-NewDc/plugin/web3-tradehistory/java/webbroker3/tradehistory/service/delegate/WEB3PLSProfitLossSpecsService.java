head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.13.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 損益明細照会サービス(WEB3PLSProfitLossSpecsService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 賈元春(中訊) 新規作成
*/
package webbroker3.tradehistory.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * (損益明細照会サービス)<BR>
 * 損益明細照会サービスインタフェイス<BR>
 * 
 * @@author 賈元春
 * @@version 1.0 
 */
public interface WEB3PLSProfitLossSpecsService extends WEB3BusinessService 
{
    
    /**
     * 損益明細照会処理を行う。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 416CFD220239
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
