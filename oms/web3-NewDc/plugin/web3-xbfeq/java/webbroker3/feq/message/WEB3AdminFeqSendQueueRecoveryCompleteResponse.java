head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqSendQueueRecoveryCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式SENDキューリカバリ完了レスポンス(WEB3AdminFeqSendQueueRecoveryCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 何文敏 (中訊) 新規作成
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者外国株式SENDキューリカバリ完了レスポンス)<BR>
 * 管理者外国株式SENDキューリカバリ完了レスポンスクラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3AdminFeqSendQueueRecoveryCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_send_queue_recovery_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD005D
     */
    public WEB3AdminFeqSendQueueRecoveryCompleteResponse() 
    {
     
    }
    
    /**
     * (エラー情報)<BR>
     * エラー情報<BR>
     */
    public String[] errorInfomations;
    
    /**
     * (更新件数)<BR>
     * 更新件数<BR>
     */   
    public String updateNumber;
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqSendQueueRecoveryCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
