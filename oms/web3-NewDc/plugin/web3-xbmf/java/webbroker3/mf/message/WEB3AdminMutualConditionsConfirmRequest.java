head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託銘柄条件登録確認リクエスト(WEB3AdminMutualConditionsConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
                   2004/12/10 黄建 (中訊) 残對応
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託銘柄条件登録確認リクエスト<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsConfirmRequest 
    extends WEB3MutualProductConditionsCommonRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131015L;
    
    /**
     * (投信銘柄条件登録確認リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40DF812F021A
     */
    public WEB3AdminMutualConditionsConfirmRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信銘柄条件登録確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF81440100
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualConditionsConfirmResponse(this);
    }
}
@
