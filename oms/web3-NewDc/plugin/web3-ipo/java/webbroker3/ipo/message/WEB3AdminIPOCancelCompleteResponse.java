head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO中止完了レスポンスクラス(WEB3AdminIPOCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO中止完了レスポンスクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIPOCancelCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_cancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131106L;
    
    /**
     * @@roseuid 4112DAD600B9
     */
    public WEB3AdminIPOCancelCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D140A203AF
     */
    public WEB3AdminIPOCancelCompleteResponse(WEB3GenRequest l_request) 
    {
        
        super(l_request);
        
    }
}
@
