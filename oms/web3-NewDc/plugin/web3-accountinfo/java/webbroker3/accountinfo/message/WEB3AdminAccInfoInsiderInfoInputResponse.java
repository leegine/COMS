head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報内部情報一覧入力レスポンス(WEB3AdminAccInfoInsiderInfoInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 李海波 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報内部情報一覧入力レスポンス)<BR>
 * 管理者お客様情報内部者情報一覧入力リクエスト<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoInputResponse extends WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_InsiderInfoInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412211207L;
    
    /**
     * (管理者お客様情報内部者情報一覧入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     */
    public WEB3AdminAccInfoInsiderInfoInputResponse (WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
