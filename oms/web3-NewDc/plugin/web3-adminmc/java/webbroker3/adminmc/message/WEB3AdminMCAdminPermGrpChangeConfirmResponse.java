head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.54.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ変更確認レスポンス(WEB3AdminMCAdminPermGrpChangeConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 屈陽 (中訊) 新規作成 
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者メニュー制御管理者権限グループ変更確認レスポンス)<BR>
 * 管理者メニュー制御管理者権限グループ変更確認レスポンス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpChangeConfirmResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_admin_perm_grp_change_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411181438L;
    
    /**
     * (管理者メニュー制御管理者権限グループ変更確認レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmResponse
     * @@roseuid 4175DFF3031F
     */
    public WEB3AdminMCAdminPermGrpChangeConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
