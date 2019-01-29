head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODeclineConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO辞退確認レスポンスクラス(WEB3IPODeclineConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 彭巍 (中訊) 新規作成
*/
package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * IPO辞退確認レスポンスクラス
 * 
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3IPODeclineConfirmResponse extends WEB3IPOOfferCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_declineConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408100942L;
    
    /**
     * 公開価格
     */
    public String publicOfferingPrice;
    
    /**
     * 購入申込代金
     */
    public String offerPrice;
    
    /**
     * 購入申込数量変更可能フラグ<BR>
     * <BR>
     * 　@true：　@購入申込数量入力可能（表示）<BR>
     * 　@false：　@購入申込数量を当選数量に固定（非表示）<BR>
     */
    public boolean offerQuantityFlag;
    
    /**
     * 確認時発注日
     */
    public Date checkDate;
    
    /**
     * @@roseuid 4112E44B0038
     */
    public WEB3IPODeclineConfirmResponse() 
    {
                              
    }
    
    /**
     * ( IPO辞退確認レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40E11B98006E
     */
    public WEB3IPODeclineConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);             
    }
}
    @
