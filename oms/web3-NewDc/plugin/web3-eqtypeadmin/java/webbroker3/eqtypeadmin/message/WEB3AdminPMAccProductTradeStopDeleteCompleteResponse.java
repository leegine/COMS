head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopDeleteCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・顧客銘柄別取引停止削除完了レスポンス 
                        (WEB3AdminPMAccProductTradeStopDeleteCompleteResponse.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （管理者・顧客銘柄別取引停止削除完了レスポンス）<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopDeleteCompleteResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopDeleteCompleteResponse extends WEB3GenResponse
{
    /**
    * PTYPE<BR>
    */
    public final static  String PTYPE = "admin_p_m_acc_product_trade_stop_delete_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static  long serialVersionUID = 200502011606L;

    /**
     * （現在日時）<BR>
     * <BR>
     * 現在日時<BR>
     * <BR>
     * currentDate<BR>
     * <BR>
     */
    public Date currentDate;

    /**
     * @@roseuid 41FD936F01F4
     */
    public WEB3AdminPMAccProductTradeStopDeleteCompleteResponse()
    {

    }

    /**
    *
    * @@param l_request WEB3GenRequest
    */
    public WEB3AdminPMAccProductTradeStopDeleteCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
