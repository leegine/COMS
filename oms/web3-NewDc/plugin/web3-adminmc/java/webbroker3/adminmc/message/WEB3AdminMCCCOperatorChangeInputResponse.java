head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.55.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更入力ﾚｽﾎﾟﾝｽ(WEB3AdminMCCCOperatorChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更入力ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更入力ﾚｽﾎﾟﾝｽ<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorChangeInputResponse extends WEB3GenResponse 
{
 
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;   
    
    /**
     * (管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更入力ﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeInputResponse
     * @@roseuid 417E2A5700F2
     */
    public WEB3AdminMCCCOperatorChangeInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
