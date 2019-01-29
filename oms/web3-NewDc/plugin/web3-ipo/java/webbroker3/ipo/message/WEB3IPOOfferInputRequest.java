head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPO購入申込入力リクエスト(WEB3IPOOfferInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 杜珉 新規作成
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
* (IPO購入申込入力リクエスト)<BR>
* 
* @@author 杜珉
* @@version 1.0
*/
public class WEB3IPOOfferInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_offerInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408191031L;
    
    /**
     * (ＩＤ)<BR>
     * IPO申告ＩＤ
     */
    public String id;
    
    /**
     * (電子鳩チェックフラグ)<BR>
     * true：チェックする <BR>
     * false：チェックしない<BR>
     */
    public boolean batoCheckFlag;
    
    /**
     * (種別コード（IPO目論見書）)<BR>
     */
    public String typeCode;
    
    /**
     * @@roseuid 4112E68200BF
     */
    public WEB3IPOOfferInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E4490388
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOOfferInputResponse(this);
    }
}
@
