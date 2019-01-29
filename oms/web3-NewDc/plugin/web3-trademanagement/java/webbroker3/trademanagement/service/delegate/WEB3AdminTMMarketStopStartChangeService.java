head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMMarketStopStartChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者市場別取引停止再開変更サービス(WEB3AdminTMMarketStopStartChangeService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trademanagement.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者市場別取引停止再開変更サービス)<BR>
 * <BR>
 * 管理者市場別取引停止再開変更サービスインタフェイス<BR>
 * <BR>
 * WEB3AdminTMMarketStopStartChangeService<BR>
 * <BR>
 * WEB3AdminTMMarketStopStartChangeService interface<BR>
 * <BR>
 * @@author Dixit Deepankar
 * @@version 1.0
 */
public interface WEB3AdminTMMarketStopStartChangeService
    extends WEB3BusinessService
{

    /**
     * 市場別取引停止再開変更処理を行う。<BR>
     * <BR>
     * Execute WEB3AdminTMMarketStopStartChangeService<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 41770C460091
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
