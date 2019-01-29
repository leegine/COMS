head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレスアップロード完了レスポンス(WEB3AdminAccInfoMailAddressUploadCancelResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 劉広燁 (中訊) 新規作成                   
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報メールアドレスアップロード完了レスポンス)<BR>
 * 管理者お客様情報メールアドレスアップロード完了レスポンス<BR>
 * 
 * @@author 劉広燁(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressUploadCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressUploadComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603141726L; 

    /**
     * @@roseuid 4158E8E201DD
     */
    public WEB3AdminAccInfoMailAddressUploadCompleteResponse() 
    {
     
    }
     
    /**
     * (管理者お客様情報メールアドレスアップロード完了レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     */
    protected WEB3AdminAccInfoMailAddressUploadCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
