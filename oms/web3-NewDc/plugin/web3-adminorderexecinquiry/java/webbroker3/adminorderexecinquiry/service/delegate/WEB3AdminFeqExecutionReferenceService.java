head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminFeqExecutionReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文約定照会サービス(WEB3AdminFeqExecutionReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 鄭海良(中訊) 新規作成
*/

package webbroker3.adminorderexecinquiry.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者外国株式注文約定照会サービス)<BR>
 * 管理者外国株式注文約定照会サービスインタフェイス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3AdminFeqExecutionReferenceService extends WEB3BusinessService
{
    
    /**
     * 管理者外国株式注文約定照会サービスを行う。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A67E14012A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
