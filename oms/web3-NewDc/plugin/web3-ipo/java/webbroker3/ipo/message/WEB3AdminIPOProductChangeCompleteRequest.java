head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄訂正完了リクエスト(WEB3AdminIPOProductChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 彭巍 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄訂正完了リクエスト)<BR>
 * 管理者IPO銘柄訂正完了リクエストデータクラス
 * 
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AdminIPOProductChangeCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_productChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408161024L;
    
    /**
     * (ＩＤ)<BR>
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * 暗証番号
     */
    public String password;
    
    /**
     * (銘柄情報)<BR>
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112E37F03C4
     */
    public WEB3AdminIPOProductChangeCompleteRequest() 
    {
     
    }
    
    /**
     * this.IPO銘柄情報.validate() をコールする。
     * @@roseuid 40CE765D0113
     */
    public void validate() throws WEB3BaseException 
    {
        this.ipoProductInfo.validate();
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E3800018
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductChangeCompleteResponse(this);
    }
}
@
