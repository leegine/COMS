head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPO購入申込確認リクエスト(WEB3IPOOfferConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 杜珉 新規作成
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
* (IPO購入申込確認リクエスト)<BR>
* 
* @@author 杜珉
* @@version 1.0
*/
public class WEB3IPOOfferConfirmRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_offerConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408191030L;
    
    /**
     * (ＩＤ)<BR>
     * IPO申告ＩＤ
     */
    public String id;
    
    /**
     * 購入申込数量
     */
    public String offerQuantity;
    
    /**
     * 口座区分<BR>
     * <BR>
     * 　@0：　@一般<BR>
     * 　@1：　@特定<BR>
     */
    public String taxType;
    
    /**
     * @@roseuid 4112E682017E
     */
    public WEB3IPOOfferConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E44A011D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOOfferConfirmResponse(this);
    }
}
@
