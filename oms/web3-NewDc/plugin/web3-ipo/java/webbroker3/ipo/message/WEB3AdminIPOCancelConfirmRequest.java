head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO中止確認リクエストクラス(WEB3AdminIPOCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO中止確認リクエストクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIPOCancelConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_cancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131048L;
    
    /**
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * @@roseuid 4112DAD60041
     */
    public WEB3AdminIPOCancelConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD60055
     */
    public WEB3GenResponse createResponse() 
    {

        return new WEB3AdminIPOCancelConfirmResponse(this);

    }
}
@
