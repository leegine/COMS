head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMMStopStartChgInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・市場別取引停止再開変更入力リクエスト(WEB3AdminTMMStopStartChgInputRequest.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （管理者・市場別取引停止再開変更入力リクエスト）<BR>
 * <BR>
 * 管理者・市場別取引停止再開変更完了レスポンスクラス<BR>
 * <BR>
 * WEB3AdminTMMStopStartChgInputRequest<BR>
 * <BR>
 * WEB3AdminTMMStopStartChgInputRequest class<BR>
 * <BR>
 *  @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMMStopStartChgInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmm_stop_start_chg_input";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
     * @@roseuid 41DD3A1B008E
     */
    public WEB3AdminTMMStopStartChgInputRequest()
    {

    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTMMStopStartChgInputResponse(this);
    }
}
@
