head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCancelExecutionInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来約定取消入力リクエスト(WEB3AdminFeqCancelExecutionInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式出来約定取消入力リクエスト)<BR>
 * 管理者外国株式出来約定取消入力リクエストクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqCancelExecutionInputRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_cancelExecutionInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101855L;  
    
    /**
     * @@roseuid 42CE39FD00AB
     */
    public WEB3AdminFeqCancelExecutionInputRequest() 
    {
     
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqCancelExecutionInputResponse(this);
    }
}
@
