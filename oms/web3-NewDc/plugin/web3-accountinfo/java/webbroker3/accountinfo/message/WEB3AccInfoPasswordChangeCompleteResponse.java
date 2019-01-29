head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoPasswordChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報暗証番号変更レスポンス(WEB3AccInfoPasswordChangeCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報暗証番号変更レスポンス)<BR>
 * お客様情報暗証番号変更レスポンス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoPasswordChangeCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_passwordChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082152L;

    /**
     * @@roseuid 418F39F700AB
     */
    public WEB3AccInfoPasswordChangeCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AccInfoPasswordChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
