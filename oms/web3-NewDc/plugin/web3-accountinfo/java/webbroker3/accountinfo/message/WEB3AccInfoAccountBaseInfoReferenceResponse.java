head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountBaseInfoReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報基本情報照会レスポンス(WEB3AccInfoAccountBaseInfoReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報基本情報照会レスポンス)<BR>
 * お客様情報基本情報照会レスポンス<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoAccountBaseInfoReferenceResponse extends WEB3GenResponse
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
     * (顧客基本情報)<BR>
     * 顧客基本情報<BR>
     */
    public WEB3AccInfoAccountBaseInfo accountBaseInfo;

    /**
     * @@roseuid 418F39ED030D
     */
    public WEB3AccInfoAccountBaseInfoReferenceResponse()
    {

    }

    /**
     * (お客様情報基本情報照会レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * @@roseuid 41368E2102A6
     */
    public WEB3AccInfoAccountBaseInfoReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
