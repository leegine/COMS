head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン入金選択画面レスポンス(WEB3AioCashinSelectResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成     
                   2004/10/22 黄建 (中訊) レビュー                  
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (オンライン入金選択画面レスポンス)<BR>
 * オンライン入金選択画面レスポンスクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSelectResponse extends WEB3GenResponse 
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
     * (決済機@関一覧)<BR>
     * オンライン入金の対象となる決済機@関の一覧<BR>
     */
    public WEB3AioSelectSettleInstitutionUnit[] selectSettleInstitutionUnit;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158EB33006D
     */
    public WEB3AioCashinSelectResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashinSelectResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
