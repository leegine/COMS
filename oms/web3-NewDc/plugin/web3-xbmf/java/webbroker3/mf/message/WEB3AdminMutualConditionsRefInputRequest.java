head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託銘柄条件登録入力リクエスト(WEB3AdminMutualConditionsRefInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託銘柄条件登録入力リクエスト<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsRefInputRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_ref_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131330L;
 
    /**
     * (投信銘柄条件登録照会入力リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40DF772F01FA
     */
    public WEB3AdminMutualConditionsRefInputRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信銘柄条件登録照会入力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF773A02C6
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualConditionsRefInputResponse(this);
    }
}
@
