head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客問合せ入力ﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報手数料変更申込顧客問合せ入力ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報手数料変更申込顧客問合せ入力ﾚｽﾎﾟﾝｽ<BR>
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountInquiryInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082145L;

    /**
     * @@roseuid 418F38690186
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse()
    {

    }

    /**
     * (管理者お客様情報手数料変更申込顧客問合せ入力ﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse
     * @@roseuid 41665770020E
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
