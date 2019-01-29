head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOOfferStopResumeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO募集停止再開確認リクエストクラス(WEB3AdminIPOOfferStopResumeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO募集停止再開確認リクエストクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIPOOfferStopResumeConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_offerStopResumeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131155L;
    
    /**
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * @@roseuid 4112DAD302B3
     */
    public WEB3AdminIPOOfferStopResumeConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD302C7
     */
    public WEB3GenResponse createResponse() 
    {

        return new WEB3AdminIPOOfferStopResumeConfirmResponse(this);

    }
}
@
