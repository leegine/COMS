head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報携帯番号・勤務先情報変更申込入力レスポンス(WEB3AccInfoMobileOfficeRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報携帯番号・勤務先情報変更申込入力レスポンス)<BR>
 * お客様情報携帯番号・勤務先情報変更申込入力レスポンス<BR>
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeRegistInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mobileOfficeRegistInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082153L;

    /**
     * (変更後情報)<BR>
     * <BR>
     * ※　@変更申込済みで未承認の場合のみセット。以外null。<BR>
     */
    public WEB3AccInfoMobileOfficeInfo changedMobileOfficeInfo;

    /**
     * @@roseuid 418F39F60222
     */
    public WEB3AccInfoMobileOfficeRegistInputResponse()
    {

    }

    /**
     * (お客様情報携帯番号・勤務先情報変更申込入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputResponse
     * @@roseuid 41368E510073
     */
    public WEB3AccInfoMobileOfficeRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
