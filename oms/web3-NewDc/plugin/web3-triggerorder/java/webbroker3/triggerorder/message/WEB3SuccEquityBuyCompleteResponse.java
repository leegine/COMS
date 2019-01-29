head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityBuyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式買付注文完了レスポンス(WEB3SuccEquityBuyCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3EquityBuyCompleteResponse;


/**
 * (（連続）現物株式買付注文完了レスポンス)<BR>
 * （連続）現物株式買付注文完了レスポンス。<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3SuccEquityBuyCompleteResponse extends WEB3EquityBuyCompleteResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityBuyComplete";
    
    /**
     * @@roseuid 434896060119
     */
    public WEB3SuccEquityBuyCompleteResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccEquityBuyCompleteResponse(WEB3SuccEquityBuyCompleteRequest l_request)
    {
        super(l_request);
    }     
}
@
