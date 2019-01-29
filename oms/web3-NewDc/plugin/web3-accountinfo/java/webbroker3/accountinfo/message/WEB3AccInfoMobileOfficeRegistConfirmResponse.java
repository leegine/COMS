head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報携帯番号・勤務先情報変更申込確認レスポンス(WEB3AccinfoMobileOfficeRegistConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報携帯番号・勤務先情報変更申込確認レスポンス)<BR>
 * お客様情報携帯番号・勤務先情報変更申込確認レスポンス<BR>
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeRegistConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mobileOfficeRegistConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082154L;

    /**
     * @@roseuid 418F39F60167
     */
    public WEB3AccInfoMobileOfficeRegistConfirmResponse()
    {

    }

    /**
     * (お客様情報携帯番号・勤務先情報変更申込確認レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccinfoMobileOfficeRegistConfirmResponse
     * @@roseuid 41368E350064
     */
    public WEB3AccInfoMobileOfficeRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
