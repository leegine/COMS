head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPO購入申込入力レスポンス(WEB3IPOOfferInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 杜珉 新規作成
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;

/**
* (IPO購入申込入力レスポンス)<BR>
* 
* @@author 杜珉
* @@version 1.0
*/
public class WEB3IPOOfferInputResponse extends WEB3IPOOfferCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_offerInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408191032L;
    
    /**
     * 申告数量
     */
    public String demandQuantity;
    
    /**
     * 口座区分一覧<BR>
     * <BR>
     * 　@0：　@一般<BR>
     * 　@1：　@特定<BR>
     */
    public String[] taxTypeList;
    
    /**
     * 購入申込数量変更可能フラグ<BR>
     * <BR>
     * 　@true：　@購入申込数量入力可能（表示）<BR>
     * 　@false：　@購入申込数量を当選数量に固定（非表示）<BR>
     */
    public boolean offerQuantityFlag;
    
    /**
     * 目論見書閲覧チェック結果<BR>
     */
    public WEB3GentradeProspectusResult prospectusResult;
    
    /**
     * @@roseuid 4112E44902D4
     */
    public WEB3IPOOfferInputResponse() 
    {
     
    }
    
    /**
     * (IPO購入申込入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40DBBC390029
     */
    public WEB3IPOOfferInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
