head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductRegistrationCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄新規登録完了リクエスト(WEB3AdminIPOProductRegistrationCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李頴淵 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄新規登録完了リクエスト)<BR>
 * IPO銘柄新規登録完了リクエストクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIPOProductRegistrationCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productRegistrationComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161053L;
    
    /**
     * 暗証番号
     */
    public String password;
    
    /**
     * (銘柄情報)<BR>
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112DF8D023B
     */
    public WEB3AdminIPOProductRegistrationCompleteRequest() 
    {
     
    }
    
    /**
     * this.IPO銘柄情報.validate() をコールする。
     * @@roseuid 40C418980212
     */
    public void validate() throws WEB3BaseException
    {
        this.ipoProductInfo.validate();    
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8D0259
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductRegistrationCompleteResponse(this);
    }
}
@
