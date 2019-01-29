head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.53.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 管理者メニュー制御管理者権限グループ登録入力リクエスト(WEB3AdminMCAdminPermGrpRegistInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 Tongwei (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者メニュー制御管理者権限グループ登録入力リクエスト)<BR>
 * 管理者メニュー制御管理者権限グループ登録入力リクエスト<BR>
 * 
 * @@author Tongwei
 * @@version 1.0
 */

public class WEB3AdminMCAdminPermGrpRegistInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPermGrpRegistInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * @@roseuid 4198641F033C
     */
    public WEB3AdminMCAdminPermGrpRegistInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641F035B
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPermGrpRegistInputResponse(this);
    }
}
@
