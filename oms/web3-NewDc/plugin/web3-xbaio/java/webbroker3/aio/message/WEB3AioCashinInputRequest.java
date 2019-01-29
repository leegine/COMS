head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン入金入力リクエスト(WEB3AioCashinInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成     
                   2004/10/22 黄建 (中訊) レビュー                  
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (オンライン入金入力リクエスト)<BR>
 * オンライン入金入力リクエストクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinInputRequest extends WEB3AioCashinCommonRequest 
{
 
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "aio_cashin_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L; 
       
    /**
     * デフォルトコンストラク
     * @@roseuid 4158E9B700F3
     */
    public WEB3AioCashinInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B40347
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3AioCashinInputResponse(this);
    }
}
@
