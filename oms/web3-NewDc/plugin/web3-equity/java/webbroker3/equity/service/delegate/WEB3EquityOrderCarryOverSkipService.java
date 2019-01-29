head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverSkipService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文繰越スキップ銘柄通知サービス(WEB3EquityOrderCarryOverSkipService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 鄒政 (中訊) 新規作成
                   2004/12/13 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.service.delegate.WEB3BackBusinessService;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.WEB3BaseException;

/**
 * （株式注文繰越スキップ銘柄通知サービスインタフェース）。<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverSkipService
    extends WEB3BackBusinessService
{

    /**
     * 株式注文繰越スキップ銘柄通知サービス処理を実施する<BR>
     * @@param l_request - リクエスト
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4056DE2802DC
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;

}
@
