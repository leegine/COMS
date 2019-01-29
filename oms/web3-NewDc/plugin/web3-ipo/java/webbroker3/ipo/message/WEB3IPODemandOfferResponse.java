head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandOfferResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPO申告購入申込レスポンスクラス(WEB3IPODemandOfferResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 劉江涛(中訊) 新規作成
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ipo.message.WEB3IPODemandOfferProductUnit;


/**
 * IPO申告購入申込レスポンスクラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IPODemandOfferResponse extends WEB3GenResponse 
{
    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "IPO_demandOffer";

    /**
      * SerialVersionUID<BR>
      */
    public static final long serialVersionUID = 200408101044L;
    
    /**
     * 新規上場一覧
     */
    public WEB3IPODemandOfferProductUnit[] newListingList;
    
    /**
     * 既上場一覧
     */
    public WEB3IPODemandOfferProductUnit[] listingList;
    
    /**
     * @@roseuid 4112E44A0271
     */
    public WEB3IPODemandOfferResponse() 
    {
     
    }
    
    /**
     * ( IPO購入申込入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40DC03E50071
     */
    public WEB3IPODemandOfferResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
