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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPO購入申込完了リクエスト(WEB3IPOOfferCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 杜珉 新規作成
*/
package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
* (IPO購入申込完了リクエスト)<BR>
* 
* @@author 杜珉
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
     * 暗証番号
     */
    public String password;
    
    /**
     * 確認時発注日
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
