head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.06.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToEquityManualOrderMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式手動発注メインサービス(WEB3ToEquityManualOrderMainService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/3/6 韋念瓊(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (株式手動発注メインサービス)<BR>
 * 
 * @@author 韋念瓊 <BR>
 * @@version 1.0<BR>
 */
public interface WEB3ToEquityManualOrderMainService extends WEB3BusinessService 
{

    /**
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E9AD7C02BF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
    
}
@
