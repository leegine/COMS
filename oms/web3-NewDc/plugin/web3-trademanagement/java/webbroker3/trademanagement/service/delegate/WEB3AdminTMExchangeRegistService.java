head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.25.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者為替登録サービス(WEB3AdminTMExchangeRegistService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.trademanagement.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者為替登録サービス)<BR>
 * 管理者為替登録サービスインターフェイス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public interface WEB3AdminTMExchangeRegistService extends WEB3BusinessService
{
    /**
     * 管理者為替登録サービス処理を行う。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
