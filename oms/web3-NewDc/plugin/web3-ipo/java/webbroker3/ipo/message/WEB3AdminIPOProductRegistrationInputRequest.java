head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductRegistrationInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄新規登録入力リクエスト(WEB3AdminIPOProductRegistrationInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李頴淵 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄新規登録入力リクエスト)<BR>
 * IPO銘柄新規登録入力リクエストクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIPOProductRegistrationInputRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productRegistrationInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161058L;
    
    /**
     * @@roseuid 4112DF8D005A
     */
    public WEB3AdminIPOProductRegistrationInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8D006E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductRegistrationInputResponse(this);
    }
}
@
