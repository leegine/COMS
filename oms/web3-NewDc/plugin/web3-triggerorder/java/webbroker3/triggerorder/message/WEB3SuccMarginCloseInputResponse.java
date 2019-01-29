head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginCloseInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引返済注文入力レスポンス(WEB3SuccMarginCloseInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭徳懇(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginCloseMarginInputResponse;

/**
 * (（連続）信用取引返済注文入力レスポンス)<BR>
 * （連続）信用取引返済注文入力レスポンス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0 
 */
public class WEB3SuccMarginCloseInputResponse extends WEB3MarginCloseMarginInputResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginCloseInput";
    
    /**
     * (注文株数)<BR>
     * 注文株数<BR>
     * （反対取引の場合のみセット）<BR>
     */
    public String orderQuantity;
    
    /**
     * @@roseuid 4369CBEA007D
     */
    public WEB3SuccMarginCloseInputResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccMarginCloseInputResponse(WEB3SuccMarginCloseInputRequest l_request)
    {
        super(l_request);
    }     
}
@
