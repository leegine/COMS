head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.50.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : (管理者メニュー制御管理者変更確認リクエスト(WEB3AdminMCAdminChangeConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 王敏 (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者メニュー制御管理者変更確認レスポンス)<BR>
 * 管理者メニュー制御管理者変更確認レスポンス<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3AdminMCAdminChangeConfirmResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (管理者メニュー制御管理者変更確認レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmResponse
     * @@roseuid 4175E05E0235
     */
    public WEB3AdminMCAdminChangeConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
