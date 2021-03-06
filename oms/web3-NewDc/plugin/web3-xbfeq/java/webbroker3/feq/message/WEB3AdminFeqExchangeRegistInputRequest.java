head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExchangeRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式為替登録入力リクエスト(WEB3AdminFeqExchangeRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/03 鄭海良(中訊) レビュー       
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式為替登録入力リクエスト)<BR>
 * 管理者外国株式為替登録入力リクエストクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqExchangeRegistInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_exchangeRegistInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * @@roseuid 42CE39FF036B
     */
    public WEB3AdminFeqExchangeRegistInputRequest() 
    {
     
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqExchangeRegistInputResponse(this);
    }
}
@
