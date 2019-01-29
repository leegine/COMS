head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSCancelExecConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式（PTS）出来取消確認リクエスト(WEB3AdminEquityPTSCancelExecConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 于瀟(中訊) 新規作成モデル174
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・株式（PTS）出来取消確認リクエスト)<BR>
 * 管理者・株式（PTS）出来取消確認リクエストクラス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3AdminEquityPTSCancelExecConfirmRequest extends WEB3AdminEquityPTSInputCancelExecCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_cancel_exec_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801231102L;

    /**
     * @@roseuid 4795B0860232
     */
    public WEB3AdminEquityPTSCancelExecConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminEquityPTSCancelExecConfirmResponse(this);
    }

}
@
