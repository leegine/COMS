head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン入金共通レスポンス(WEB3AioCashinCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成     
                   2004/10/22 黄建 (中訊) レビュー                  
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (オンライン入金共通レスポンス)<BR>
 * オンライン入金共通レスポンスクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinCommonResponse extends WEB3GenResponse 
{        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;       
        
    /**
     * (決済機@関ID)<BR>
     */
    public String paySchemeId;
    
    /**
     * (決済機@関名)<BR>
     * 決済機@関の名称<BR>
     */
    public String paySchemeName;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158E9B602FA
     */
    public WEB3AioCashinCommonResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashinCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
