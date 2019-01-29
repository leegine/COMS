head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン入金入力レスポンス(WEB3AioCashinInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー                       
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (オンライン入金入力レスポンス)<BR>
 * オンライン入金入力レスポンスクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinInputResponse extends WEB3AioCashinCommonResponse 
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
     * (上限金額)<BR>
     * 1回のオンライン入金で扱える上限の金額<BR>
     */
    public String maxAmount;
    
    /**
     * (下限金額)<BR>
     * 1回のオンライン入金で扱える下限の金額<BR>
     */
    public String minAmount;
    
    /**
     * (振込単位)<BR>
     * オンライン入金で扱える振込の単位<BR>
     */
    public String transferUnit;
    
    /**
     * (総入金上限額)<BR>
     * 1日に行える最大入金額<BR>
     */
    public String maxTotalAmount;
    
    /**
     * (入金上限回数)<BR>
     * 1日に行える最大の入金回数<BR>
     */
    public String cashinMaxTimes;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158E9B70175
     */
    public WEB3AioCashinInputResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashinInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
