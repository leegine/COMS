head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadCancelResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレスアップロード中止レスポンス(WEB3AdminAccInfoMailAddressUploadCancelResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 劉広燁 (中訊) 新規作成                   
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報メールアドレスアップロード中止レスポンス)<BR>
 * 管理者お客様情報メールアドレスアップロード中止レスポンス<BR>
 * 
 * @@author 劉広燁(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressUploadCancelResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressUploadCancel";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603141706L; 
    
    /**
     * @@roseuid 4158E8E201DD
     */
    public WEB3AdminAccInfoMailAddressUploadCancelResponse() 
    {
     
    }     
    
    /**
     * (管理者お客様情報メールアドレスアップロード中止レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     */
    protected WEB3AdminAccInfoMailAddressUploadCancelResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@
