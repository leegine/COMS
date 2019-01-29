head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託銘柄条件登録完了リクエスト(WEB3AdminMutualConditionsCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
                   2004/12/10 于美麗 (中訊) 残対応
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託銘柄条件登録完了リクエスト<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsCompleteRequest 
    extends WEB3MutualProductConditionsCommonRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131115L;
    
    /**
     * 暗証番号<BR>
     */
    public String password;
       
    /**
     * (投信銘柄条件登録完了リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40DF815400B2
     */
    public WEB3AdminMutualConditionsCompleteRequest() 
    {
     
    }
        
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信銘柄条件登録完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF816102A6
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualConditionsCompleteResponse(this);
    }
    

}@
