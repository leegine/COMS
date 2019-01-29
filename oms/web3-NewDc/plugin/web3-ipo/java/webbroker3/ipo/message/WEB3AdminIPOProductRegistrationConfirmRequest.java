head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductRegistrationConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄新規登録確認リクエスト(WEB3AdminIPOProductRegistrationConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李頴淵 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄新規登録確認リクエスト)<BR>
 * IPO銘柄新規登録確認リクエストクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIPOProductRegistrationConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productRegistrationConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161043L;
    
    /**
     * (銘柄情報)<BR>
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112DF8D014B
     */
    public WEB3AdminIPOProductRegistrationConfirmRequest() 
    {
     
    }
    
    /**
     * this.IPO銘柄情報.validate() をコールする。
     * @@roseuid 40C410300147
     */
    public void validate() throws WEB3BaseException
    {
        this.ipoProductInfo.validate();
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8D015F
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductRegistrationConfirmResponse(this);
    }
}
@
