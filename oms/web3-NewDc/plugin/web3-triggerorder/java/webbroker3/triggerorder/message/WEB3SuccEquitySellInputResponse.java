head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquitySellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式売付注文入力レスポンス(WEB3SuccEquitySellInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3EquitySellInputResponse;


/**
 * (（連続）現物株式売付注文入力レスポンス)<BR>
 * （連続）現物株式売付注文入力レスポンス。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3SuccEquitySellInputResponse extends WEB3EquitySellInputResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equitySellInput";
    
    /**
     * @@roseuid 434896050128
     */
    public WEB3SuccEquitySellInputResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccEquitySellInputResponse(WEB3SuccEquitySellInputRequest l_request)
    {
        super(l_request);
    }     
}
@
