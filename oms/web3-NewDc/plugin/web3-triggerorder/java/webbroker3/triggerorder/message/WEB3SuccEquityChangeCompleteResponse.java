head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式注文訂正完了レスポンス(WEB3SuccEquityChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  魏馨(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3EquityChangeCompleteResponse;


/**
 * (（連続）現物株式注文訂正完了レスポンス)<BR>
 * （連続）現物株式注文訂正完了レスポンス。<BR>
 * 
 * @@ author 魏馨 <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3SuccEquityChangeCompleteResponse extends WEB3EquityChangeCompleteResponse 
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityChangeComplete";
    
    /**
     * @@roseuid 4369CC8201F4
     */
    public WEB3SuccEquityChangeCompleteResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccEquityChangeCompleteResponse(WEB3SuccEquityChangeCompleteRequest l_request)
    {
        super(l_request);
    }     
}
@
