head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleErrorResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済エラーレスポンス(WEB3AioCashinSettleErrorResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成         
                   2004/10/22 黄建 (中訊) レビュー              
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (決済エラーレスポンス)<BR>
 * 決済エラーレスポンスクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettleErrorResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_settle_error";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158EB3400FA
     */
    public WEB3AioCashinSettleErrorResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashinSettleErrorResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
