head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCancelExecutionConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来約定取消確認レスポンス(WEB3AdminFeqCancelExecutionConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式出来約定取消確認レスポンス)<BR>
 * 管理者外国株式出来約定取消確認レスポンスクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqCancelExecutionConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_cancelExecutionConfirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * (外国株式注文約定詳細)<BR>
     * 外国株式約定入力情報オブジェクト
     */
    public WEB3FeqOrderAndExecutionUnit orderAndExecutionUnit;
    
    /**
     * @@roseuid 42CE39FD0196
     */
    public WEB3AdminFeqCancelExecutionConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqCancelExecutionConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
