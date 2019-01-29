head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontChangeProcessSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・発注経路切替処理方式選択レスポンス (WEB3AdminFrontChangeProcessSelectResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.116
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・発注経路切替処理方式選択レスポンス)<BR>
 * <BR>
 * 管理者・発注経路切替処理方式選択レスポンス（選択画面表示）のレスポンスデータ。<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontChangeProcessSelectResponse extends WEB3GenResponse {
    

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_front_change_process_select";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * @@roseuid 42FFFEF90300
     */
    public WEB3AdminFrontChangeProcessSelectResponse() 
    {
    
    }

    /**
      * @@roseuid 
      * @@param l_request l_request
      */
    public WEB3AdminFrontChangeProcessSelectResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
