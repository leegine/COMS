head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ問合せｽﾎﾟﾝｽ(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ問合せｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ問合せﾚｽﾎﾟﾝｽ
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_hyperBoxCommissionChangeInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082132L;

    /**
     * @@roseuid 418F385D03B9
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse()
    {

    }

    /**
     * (管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ問合ﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse
     * @@roseuid 4166549F00F5
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
