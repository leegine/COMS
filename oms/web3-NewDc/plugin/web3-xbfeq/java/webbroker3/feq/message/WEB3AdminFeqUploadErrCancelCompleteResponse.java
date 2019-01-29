head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqUploadErrCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除完了レスポンス(WEB3AdminFeqUploadErrCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/
package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除完了レスポンス)<BR>
 * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除完了レスポンス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqUploadErrCancelCompleteResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_uploadErrCancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * @@roseuid 42CE3A030109
     */
    public WEB3AdminFeqUploadErrCancelCompleteResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqUploadErrCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
