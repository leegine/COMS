head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・証拠金不足状況照会入力リクエスト(WEB3AdminIfoDepShortageRefInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 李玉玲(中訊) 新規作成 モデルNo.004
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・証拠金不足状況照会入力リクエスト)<BR>
 * 管理者・証拠金不足状況照会入力リクエストクラス<BR>
 * <BR>
 * @@author 李玉玲(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageRefInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_ifo_dep_shortage_ref_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902271152L;

    /**
     * @@roseuid 49A7485501E4
     */
    public WEB3AdminIfoDepShortageRefInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminIfoDepShortageRefInputResponse(this);
    }
}
@
