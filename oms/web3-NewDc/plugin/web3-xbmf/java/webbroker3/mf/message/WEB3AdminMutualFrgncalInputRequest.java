head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託海外市場カレンダー登録入力リクエスト(WEB3AdminMutualFrgncalInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 王蘭芬(中訊) 新規作成
                   2004/08/25 周勇 (中訊) レビュー 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 投資信託海外市場カレンダー登録入力リクエストクラス
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualFrgncalInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_mutual_frgncal_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408151434L;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40C0077E03B3
     */
    public WEB3AdminMutualFrgncalInputRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信海外市場カレンダー登録入力画面レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40C0078800E5
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualFrgncalInputResponse(this);
    }
}
@
