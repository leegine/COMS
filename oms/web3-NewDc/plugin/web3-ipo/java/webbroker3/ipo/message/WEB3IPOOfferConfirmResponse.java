head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPO購入申込確認レスポンス(WEB3IPOOfferConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 杜珉 新規作成
*/
package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
* (IPO購入申込確認レスポンス)<BR>
* 
* @@author 杜珉
* @@version 1.0
*/
public class WEB3IPOOfferConfirmResponse extends WEB3GenResponse 
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
     * 公開価格
     */
    public String publicOfferingPrice;
    
    /**
     * 購入申込代金
     */
    public String offerPrice;
    
    /**
     * 確認時発注日
     */
    public Date checkDate;
    
    /**
     * @@roseuid 4112E44A0022
     */
    public WEB3IPOOfferConfirmResponse() 
    {
     
    }
    
    /**
     * (IPO購入申込確認レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40E11B7D006E
     */
    public WEB3IPOOfferConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
