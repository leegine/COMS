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
filename	WEB3AdminForcedSettleDownloadInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文ダウンロード入力リクエスト（WEB3AdminForcedSettleDownloadInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 于瀟(中訊) 新規作成モデル175
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・強制決済注文ダウンロード入力リクエスト)<BR>
 * 管理者・強制決済注文ダウンロード入力リクエスト<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3AdminForcedSettleDownloadInputRequest extends WEB3AdminForcedSettleReferenceRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_download_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801211100L;

    /**
     * @@roseuid 479031F900EA
     */
    public WEB3AdminForcedSettleDownloadInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminForcedSettleDownloadInputResponse(this);
    }

}
@
