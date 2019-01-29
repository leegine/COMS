head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontChangeProcessSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・発注経路切替処理選択リクエスト (WEB3AdminFrontOrderChangeProcessSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.116
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・発注経路切替処理選択リクエスト)<BR>
 * <BR>
 * 管理者・発注経路切替処理選択リクエスト（選択画面表示）のリクエストデータ。<BR>
 * <BR>
  * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontChangeProcessSelectRequest extends WEB3GenRequest {

    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_front_change_process_select";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontChangeProcessSelectRequest.class);


    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFrontChangeProcessSelectResponse(this);
    }

    /**
     * @@roseuid 42FFFEF902B2
     */
    public WEB3AdminFrontChangeProcessSelectRequest() 
    {
    
    }
}
@
