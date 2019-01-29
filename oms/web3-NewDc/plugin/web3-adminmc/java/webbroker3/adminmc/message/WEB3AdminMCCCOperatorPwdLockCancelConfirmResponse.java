head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.56.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除確認ﾚｽﾎﾟﾝｽ(WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/19 Tongwei (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除確認ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除確認ﾚｽﾎﾟﾝｽ<BR>
 * 
 * @@author Tongwei
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorPwdLockCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * (管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除確認ﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse
     * @@roseuid 417E28D5020B
     */
    public WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
