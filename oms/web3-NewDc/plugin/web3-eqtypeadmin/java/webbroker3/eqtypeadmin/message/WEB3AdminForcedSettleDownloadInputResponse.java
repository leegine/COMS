head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleDownloadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文ダウンロード入力レスポンス（WEB3AdminForcedSettleDownloadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 于瀟(中訊) 新規作成モデル175
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (管理者・強制決済注文ダウンロード入力レスポンス)<BR>
 * 管理者・強制決済注文ダウンロード入力レスポンス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3AdminForcedSettleDownloadInputResponse extends WEB3AdminForcedSettleReferenceResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_download_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801211105L;

    /**
     * @@roseuid 479031F9030D
     */
    public WEB3AdminForcedSettleDownloadInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminForcedSettleDownloadInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
