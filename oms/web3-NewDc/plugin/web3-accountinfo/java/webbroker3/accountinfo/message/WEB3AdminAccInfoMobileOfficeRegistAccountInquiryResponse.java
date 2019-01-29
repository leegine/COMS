head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾚｽﾎﾟﾝｽ<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistAccountInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082113L;

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
     * @@roseuid 418F385D00FA
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse()
    {

    }

    /**
     * (管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse
     * @@roseuid 415A5D980185
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
