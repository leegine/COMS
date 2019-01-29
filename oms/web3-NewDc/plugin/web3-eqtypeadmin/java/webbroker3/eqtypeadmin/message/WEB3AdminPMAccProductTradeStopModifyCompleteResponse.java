head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopModifyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・顧客銘柄別取引停止変更完了レスポンス
                        (WEB3AdminPMAccProductTradeStopModifyCompleteResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （管理者・顧客銘柄別取引停止変更完了レスポンス）<BR>
 * <BR>
 * 管理者・顧客銘柄別取引停止変更完了レスポンスクラス<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopModifyCompleteResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopModifyCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_acc_product_trade_stop_modify_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

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
     * （顧客銘柄別取引停止情報）
     * 顧客銘柄別取引停止情報
     * ----<English>--------------------
     * accProductTradeStopInfo
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit accProductTradeStopInfo;

    /**
     * @@roseuid 41FD94160000
     */
    public WEB3AdminPMAccProductTradeStopModifyCompleteResponse()
    {

    }

    /**
     * @@roseuid 41FD94160000
     * @@param l_request l_request
     */
    public WEB3AdminPMAccProductTradeStopModifyCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
