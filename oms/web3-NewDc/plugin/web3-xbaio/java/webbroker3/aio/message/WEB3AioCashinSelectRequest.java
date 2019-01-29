head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン入金選択画面リクエスト(WEB3AioCashinSelectRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成        
                   2004/10/22 黄建 (中訊) レビュー               
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (オンライン入金選択画面リクエスト)<BR>
 * オンライン入金選択画面リクエストクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSelectRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_select";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * @@roseuid 4158EB32038D
     */
    public WEB3AioCashinSelectRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB3203A1
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3AioCashinSelectResponse(this);
    }
}
@
