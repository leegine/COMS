head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式注文訂正入力レスポンス(WEB3SuccEquityChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  魏馨(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3EquityChangeInputResponse;


/**
 * (（連続）現物株式注文訂正入力レスポンス)<BR>
 * （連続）現物株式注文訂正入力レスポンス。<BR>
 * 
 * @@ author 魏馨 <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3SuccEquityChangeInputResponse extends WEB3EquityChangeInputResponse 
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityChangeInput";
    
    /**
     * (連続注文共通情報)<BR>
     * 連続注文共通情報。<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;
    
    /**
     * (単価調整値情報)<BR>
     * 単価調整値情報。<BR>
     * 訂正対象注文に±指値が指定されている場合のみセット。<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;
    
    /**
     * (注文期限区分一覧)<BR>
     * 注文期限区分一覧。<BR>
     * （1：当日限り　@2：出来るまで注文）<BR>
     */
    public String[] expirationDateTypeList;
    
    /**
     * @@roseuid 4369CC8400CB
     */
    public WEB3SuccEquityChangeInputResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccEquityChangeInputResponse(WEB3SuccEquityChangeInputRequest l_request)
    {
        super(l_request);
    }     
}
@
