head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金額確認レスポンス(WEB3AioCashinConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成            
                   2004/10/22 黄建 (中訊) レビュー     
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (入金額確認レスポンス)<BR>
 * 入金額確認レスポンスクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinConfirmResponse extends WEB3AioCashinCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "aio_cashin_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;     
    
    /**
     * (入金金額)<BR>
     * 今回入金しようとしている入金額<BR>
     */
    public String cashinAmt;
    
    /**
     * (入金回数)<BR>
     * 今回の入金を含めた入金回数<BR>
     */
    public String cashinTimes;
    
    /**
     * (入金合計金額)<BR>
     * 今回の入金を含めた入金合計金額<BR>
     */
    public String cashinTotalAmt;
    
    /**
     * (確認時発注日)<BR>
     * 確認処理時の発注日<BR>
     * （画面表示なし）<BR>
     * <BR>
     */
    public Date checkDate;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158E9B70052
     */
    public WEB3AioCashinConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashinConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
