head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.08.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設状況問合せ入力リクエスト(WEB3AdminAccOpenStateInquiryInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設状況問合せ入力リクエスト)<BR>
 * 管理者口座開設状況問合せ入力リクエスト<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3AdminAccOpenStateInquiryInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_stateInquiryInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081606L;

    /**
     * @@roseuid 41B45E7503C8
     */
    public WEB3AdminAccOpenStateInquiryInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenStateInquiryInputResponse(this);
    }
}
@
