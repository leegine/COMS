head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMMStopStartChgConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・市場別取引停止再開変更完了リクエスト(WEB3AdminTMMStopStartChgConfirmResponse.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 管理者・市場別取引停止再開変更完了リクエスト<BR>
 * <BR>
 * WEB3AdminTMMStopStartChgConfirmResponse<BR>
 * <BR>
 * WEB3AdminTMMStopStartChgConfirmResponse class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMMStopStartChgConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmm_stop_start_chg_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
     * （現在日時）<BR>
     * <BR>
     * 現在日時<BR>
     * <BR>
     * currentDate<BR>
     */
    public Date currentDate;

    /**
     * (市場別取引状況一覧)
     * <BR>
     * 市場別取引状況一覧<BR>
     */
    public WEB3AdminTMMarketTradingStatusUnit[] marketTradingStatusList;

    /**
     * @@roseuid 41DD3C0B0001
     */
    public WEB3AdminTMMStopStartChgConfirmResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminTMMStopStartChgConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
