head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAndExecutionCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式約定入力完了レスポンス(WEB3AdminFeqOrderAndExecutionCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式約定入力完了レスポンス)<BR>
 * 管理者外国株式約定入力完了レスポンスクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAndExecutionCompleteResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAndExecutionComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * @@roseuid 42CE39F90128
     */
    public WEB3AdminFeqOrderAndExecutionCompleteResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqOrderAndExecutionCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
