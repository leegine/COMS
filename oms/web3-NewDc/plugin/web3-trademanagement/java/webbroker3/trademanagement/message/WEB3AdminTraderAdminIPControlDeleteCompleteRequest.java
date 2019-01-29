head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlDeleteCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP登録情報削除完了リクエスト(WEB3AdminTraderAdminIPControlDeleteCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・ログイン拒否IP登録情報削除完了リクエスト)<BR>
 * 管理者・ログイン拒否IP登録情報削除完了リクエストクラス。<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlDeleteCompleteRequest
    extends WEB3AdminTraderAdminIPControlUpdateCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_delete_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221735L;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * @@roseuid 48D75CD702FC
     */
    public WEB3AdminTraderAdminIPControlDeleteCompleteRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）super.validate()をコールする。<BR>
     * @@roseuid 48C0C51A00CE
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        super.validate();
    }

    /**
     * レスポンスデータを作成する。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminIPControlDeleteCompleteResponse(this);
    }
}
@
