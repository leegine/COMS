head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMPStopStartCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・商品別取扱停止再開変更完了レスポンス(WEB3AdminTMPStopStartCompleteResponse.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （管理者・商品別取扱停止再開変更完了レスポンス）<BR>
 * <BR>
 * 管理者・市場別取引停止再開変更完了レスポンスクラス<BR>
 * <BR>
 * WEB3AdminTMPStopStartCompleteResponse<BR>
 * <BR>
 * WEB3AdminTMPStopStartCompleteResponse class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMPStopStartCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmp_stop_start_chg_complete";

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
     * (部店別取扱状況一覧)
     * <BR>
     * 部店別取扱状況一覧<BR>
     */
    public WEB3AdminTMBranchTradingStatusUnit[] branchTradingStatusList;

    /**
     * @@roseuid 41DD3CB7032E
     */
    public WEB3AdminTMPStopStartCompleteResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminTMPStopStartCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
