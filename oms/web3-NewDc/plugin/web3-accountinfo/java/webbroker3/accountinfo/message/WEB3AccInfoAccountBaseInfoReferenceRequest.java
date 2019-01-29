head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountBaseInfoReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報基本情報照会リクエスト(WEB3AccInfoAccountBaseInfoReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報基本情報照会リクエスト)<BR>
 * お客様情報基本情報照会リクエスト<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoAccountBaseInfoReferenceRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_accountBaseInfoReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082155L;

    /**
     * @@roseuid 418F386D033C
     */
    public WEB3AccInfoAccountBaseInfoReferenceRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoAccountBaseInfoReferenceResponse(this);
    }
}
@
