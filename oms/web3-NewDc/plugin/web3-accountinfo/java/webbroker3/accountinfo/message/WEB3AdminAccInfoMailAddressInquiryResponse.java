head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件問合せﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoMailAddressInquiryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件問合せﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件問合せﾚｽﾎﾟﾝｽ<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressInquiryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082115L;

    /**
     * @@roseuid 418F38570138
     */
    public WEB3AdminAccInfoMailAddressInquiryResponse()
    {

    }

    /**
     * (管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件問合せﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryResponse
     * @@roseuid 4136B90D03C8
     */
    public WEB3AdminAccInfoMailAddressInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
