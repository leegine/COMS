head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptResultUploadStopResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文受付結果アップロード中止レスポンス(WEB3AdminFeqOrderAcceptResultUploadStopResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式注文受付結果アップロード中止レスポンス)<BR>
 * 管理者外国株式注文受付結果アップロード中止レスポンスクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptResultUploadStopResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAcceptResultUploadStop";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * @@roseuid 42CE39FC0271
     */
    public WEB3AdminFeqOrderAcceptResultUploadStopResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqOrderAcceptResultUploadStopResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
