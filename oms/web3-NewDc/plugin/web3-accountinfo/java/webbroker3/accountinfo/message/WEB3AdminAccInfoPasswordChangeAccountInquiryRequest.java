head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordChangeAccountInquiryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報暗証番号変更顧客問合せﾘｸｴｽﾄ(WEB3AdminAccInfoPasswordChangeAccountInquiryRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報暗証番号変更顧客問合せﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報暗証番号変更顧客問合せﾘｸｴｽﾄ<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0 
 */
public class WEB3AdminAccInfoPasswordChangeAccountInquiryRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_passwordChangeAccountInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082106L;

    /**
     * @@roseuid 418F385F031C
     */
    public WEB3AdminAccInfoPasswordChangeAccountInquiryRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoPasswordChangeAccountInquiryResponse(this);
    }
}
@
