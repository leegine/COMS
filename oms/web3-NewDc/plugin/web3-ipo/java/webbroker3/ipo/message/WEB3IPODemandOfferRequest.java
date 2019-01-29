head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandOfferRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPO申告購入申込リクエストクラス(WEB3IPODemandOfferRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 劉江涛(中訊) 新規作成
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * IPO申告購入申込リクエストクラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IPODemandOfferRequest extends WEB3GenRequest 
{
    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "IPO_demandOffer";

    /**
      * SerialVersionUID<BR>
      */
    public static final long serialVersionUID = 200408101042L;
    
    /**
     * @@roseuid 4112E68202AA
     */
    public WEB3IPODemandOfferRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E44A02FD
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPODemandOfferResponse(this);
    }
}
@
