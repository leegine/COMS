head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginOpenChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引訂正新規建入力リクエストクラス(WEB3SuccMarginOpenChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭徳懇(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginOpenMarginChangeInputResponse;

/**
 * (（連続）信用取引訂正新規建入力レスポンスクラス)<BR>
 * （連続）信用取引訂正新規建入力レスポンスクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3SuccMarginOpenChangeInputResponse extends WEB3MarginOpenMarginChangeInputResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginOpenChangeInput";
    
    /**
     * (連続注文共通情報)<BR>
     * 連続注文共通情報。<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * (単価調整値情報)<BR>
     * 単価調整値情報。<BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;
    
    /**
     * (注文期限区分一覧)<BR>
     * 注文期限区分一覧。<BR>
     * （1：当日限り　@2：出来るまで注文）<BR>
     */
    public String[] expirationDateTypeList;
        
    /**
     * @@roseuid 4369CBED000F
     */
    public WEB3SuccMarginOpenChangeInputResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccMarginOpenChangeInputResponse(WEB3SuccMarginOpenChangeInputRequest l_request)
    {
        super(l_request);
    }     
}
@
