head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.50.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorListInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧入力ﾘｸｴｽﾄ(WEB3AdminMCCCOperatorListInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 範慧琴 (中訊) 新規作成
*/


package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧入力ﾘｸｴｽﾄ)<BR>
 * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧入力ﾘｸｴｽﾄ<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorListInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorListInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * @@roseuid 419864260177
     */
    public WEB3AdminMCCCOperatorListInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198642601A5
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCCCOperatorListInputResponse(this);
    }
}
@
