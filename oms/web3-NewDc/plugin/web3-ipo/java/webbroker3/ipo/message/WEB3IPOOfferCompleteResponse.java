head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPO購入申込完了レスポンス(WEB3IPOOfferResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 杜珉 新規作成
*/
package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
* (IPO購入申込完了レスポンス)<BR>
* 
* @@author 杜珉
* @@version 1.0
*/
public class WEB3IPOOfferCompleteResponse extends WEB3GenResponse 
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
     * 更新時間
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * 識別番号
     */
    public String orderActionId;
    
    /**
     * @@roseuid 4112E449022A
     */
    public WEB3IPOOfferCompleteResponse() 
    {
     
    }
    
    /**
     * ( IPO購入申込完了レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40E11B8B02FE
     */
    public WEB3IPOOfferCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
