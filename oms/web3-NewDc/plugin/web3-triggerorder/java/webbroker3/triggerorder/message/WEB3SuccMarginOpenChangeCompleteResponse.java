head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.44.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginOpenChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引注文訂正新規建完了レスポンスクラス(WEB3SuccMarginOpenChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭徳懇(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteResponse;

/**
 * (（連続）信用取引注文訂正新規建完了レスポンスクラス)<BR>
 * （連続）信用取引注文訂正新規建完了レスポンスクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3SuccMarginOpenChangeCompleteResponse extends WEB3MarginOpenMarginChangeCompleteResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginOpenChangeComplete";
    
    /**
     * @@roseuid 4369CC3F01B5
     */
    public WEB3SuccMarginOpenChangeCompleteResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccMarginOpenChangeCompleteResponse(WEB3SuccMarginOpenChangeCompleteRequest l_request)
    {
        super(l_request);
    }     
}
@
