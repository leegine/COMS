head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoPasswordChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報暗証番号変更入力レスポンス(WEB3AccInfoPasswordChangeInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報暗証番号変更入力レスポンス)<BR>
 * お客様情報暗証番号変更入力レスポンス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoPasswordChangeInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_passwordChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082151L;

    /**
     * (パスワード最小長)<BR>
     * パスワード最小長<BR>
     */
    public String passwordLower;

    /**
     * (パスワード最大長)<BR>
     * パスワード最大長<BR>
     */
    public String passwordUpper;

    /**
     * (パスワードチェック方式)<BR>
     * パスワードチェック方式<BR>
     * <BR>
     * 1：　@数字のみ<BR>
     * 2：　@英数字<BR>
     * 3：　@英数字混在<BR>
     * <BR>
     * ※ WEB3CodeCheckModeDefにて定義済<BR>
     * <BR>
     */
    public String passwordCheckType;

    /**
     * @@roseuid 418F39F70213
     */
    public WEB3AccInfoPasswordChangeInputResponse()
    {

    }

    /**
     * (お客様情報暗証番号変更入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputResponse
     * @@roseuid 416CAA8E0153
     */
    public WEB3AccInfoPasswordChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
