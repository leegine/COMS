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
filename	WEB3EquityChangeOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文訂正サービス(WEB3EquityChangeOrderService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 周玲玲 (中訊) 新規作成
                   2004/12/15 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * （株式注文訂正サービス）。<BR>
 * <BR>
 * 株式注文訂正サービスインターフェース
 * @@version 1.0
 */
public interface WEB3EquityChangeOrderService extends WEB3BusinessService
{
    /**
     * (execute)<BR>
     * <BR>
     * 現物株式注文訂正サービス処理を実施する。<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4042C943028C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
