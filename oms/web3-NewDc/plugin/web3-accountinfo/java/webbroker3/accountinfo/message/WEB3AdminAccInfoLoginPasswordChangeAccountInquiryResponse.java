head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客問合せﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客問合せﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客問合せﾚｽﾎﾟﾝｽ<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_loginPasswordChangeAccountInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082124L;

    /**
     * (前営業日)<BR>
     * 前営業日<BR>
     */
    public Date previousBizDate;

    /**
     * (前日)<BR>
     * 前日<BR>
     */
    public Date previousDate;

    /**
     * @@roseuid 418F385C0186
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse()
    {

    }

    /**
     * (管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客問合せﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse
     * @@roseuid 415A5C7A005C
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
