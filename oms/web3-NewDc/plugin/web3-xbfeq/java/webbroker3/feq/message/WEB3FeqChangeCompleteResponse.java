head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.25.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式訂正完了レスポンス(WEB3FeqChangeCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (外国株式訂正完了レスポンス)<BR>
 * 外国株式訂正完了レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqChangeCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_changeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (更新時間)<BR>
     * 更新時間<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String orderId;
    
    /**
     * @@roseuid 42CE3A0603C8
     */
    public WEB3FeqChangeCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
