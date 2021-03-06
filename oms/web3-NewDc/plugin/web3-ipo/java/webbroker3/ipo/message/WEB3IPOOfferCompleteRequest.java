head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        :  IPOwü\®¹NGXg(WEB3IPOOfferCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 mûa VKì¬
*/
package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
* (IPOwü\®¹NGXg)<BR>
* 
* @@author mûa
* @@version 1.0
*/
public class WEB3IPOOfferCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_offerComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409200935L;
    
    /**
     * (hc)<BR>
     * IPO\hc
     */
    public String id;
    
    /**
     * wü\Ê
     */
    public String offerQuantity;
    
    /**
     * ûÀæª<BR>
     * <BR>
     * @@0F@@êÊ<BR>
     * @@1F@@Áè<BR>
     */
    public String taxType;
    
    /**
     * ÃØÔ
     */
    public String password;
    
    /**
     * mF­ú
     */
    public Date checkDate;
    
    /**
     * @@roseuid 4112E68201F6
     */
    public WEB3IPOOfferCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E44A01D1
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOOfferCompleteResponse(this);
    }
}
@
