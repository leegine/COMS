head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託銘柄条件登録完了レスポンス(WEB3AdminMutualConditionsCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託銘柄条件登録完了レスポンス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsCompleteResponse extends WEB3GenResponse 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131205L;
    
    /**
     * (投信銘柄条件登録完了レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40DF81CD0055
     */
    public WEB3AdminMutualConditionsCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminMutualConditionsCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }        
}
@
