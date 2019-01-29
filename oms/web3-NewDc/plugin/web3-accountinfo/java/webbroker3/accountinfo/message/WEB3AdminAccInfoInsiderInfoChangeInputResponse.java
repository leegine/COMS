head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報内部者情報変更入力レスポンス(WEB3AdminAccInfoInsiderInfoChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報内部者情報変更入力レスポンス)<BR>
 * 管理者お客様情報内部者情報変更入力レスポンス<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoChangeInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_insiderInfoChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082129L;
    
    /**
     * (内部者情報)<BR>
     * 内部者情報 <BR>
     */
    public WEB3AccInfoInsiderInfo insiderInfo;
    
    /**
     * @@roseuid 418F3863002E
     */
    public WEB3AdminAccInfoInsiderInfoChangeInputResponse()
    {

    }

    /**
     * (管理者お客様情報内部者情報変更入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeInputResponse
     * @@roseuid 416657DA0191
     */
    public WEB3AdminAccInfoInsiderInfoChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
