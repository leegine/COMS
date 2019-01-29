head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレスアップロード確認レスポンス(WEB3AdminAccInfoMailAddressUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 尹偉鋒 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報メールアドレスアップロード確認レスポンス)<BR>
 * 管理者お客様情報メールアドレスアップロード確認レスポンス<BR>
 * 
 * @@author 尹偉鋒(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressUploadConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressUploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603141715L;

    /**
     * (アップロード件数)<BR>
     */
    public String uploadNumber;
    
    /**
     * (アップロードID)<BR>
     */
    public String uploadID;

    /**
     * @@roseuid 418F38570138
     */
    public WEB3AdminAccInfoMailAddressUploadConfirmResponse()
    {

    }

    /**
     * (管理者お客様情報メールアドレスアップロード確認レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@roseuid 4136B90D03C8
     */
    public WEB3AdminAccInfoMailAddressUploadConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
