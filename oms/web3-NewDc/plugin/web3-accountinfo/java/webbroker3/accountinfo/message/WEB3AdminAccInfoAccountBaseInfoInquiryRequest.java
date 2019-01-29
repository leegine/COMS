head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.56.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccountBaseInfoInquiryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報顧客基本情報問合せ入力リクエスト(WEB3AdminAccInfoAccountBaseInfoInquiryRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 李海波 (中訊) 新規作成
*/


package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報顧客基本情報問合せ入力リクエスト)<BR>
 * 管理者お客様情報顧客基本情報問合せ入力リクエスト<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoAccountBaseInfoInquiryRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_accountBaseInfoInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082150L;

    /**
     * @@roseuid 418F386102FD
     */
    public WEB3AdminAccInfoAccountBaseInfoInquiryRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoAccountBaseInfoInquiryResponse(this);
    }
}
@
