head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正取消受付サービス(WEB3EquityChangeCancelAcceptService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 周玲玲 (中訊) 新規作成
                   2004/09/14 李海波 (中訊) 修正(javaDoc)
                   2004/09/22 盧法@旭(中訊) 修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * （株式訂正取消受付サービス）。
 * @@author 法@旭
 * @@version 1.0
 */
public interface WEB3EquityChangeCancelAcceptService
    extends WEB3BackBusinessService
{
    
    /**
     * 注文訂正取消受付処理を実施する<BR>
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
}
@
