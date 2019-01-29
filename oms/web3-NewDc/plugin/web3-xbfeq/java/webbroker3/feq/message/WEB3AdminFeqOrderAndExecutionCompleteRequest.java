head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAndExecutionCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式約定入力完了リクエスト(WEB3AdminFeqOrderAndExecutionCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者外国株式約定入力完了リクエスト)<BR>
 * 管理者外国株式約定入力完了リクエストクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAndExecutionCompleteRequest extends WEB3AdminFeqOrderAndExecutionCommonRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAndExecutionCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAndExecutionComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (暗証番号)<BR>
     * 暗証番号
     */
    public String password;
    
    /**
     * @@roseuid 42CE39F90177
     */
    public WEB3AdminFeqOrderAndExecutionCompleteRequest() 
    {
     
    }
    
    /**
     * リクエストデータのチェックを行う。<BR>
     * <BR>
     * １）　@約定入力情報のチェック<BR>
     * 　@１－１）　@nullの場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02072<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B7E2050177
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);

        if (orderAndExecutionUnit == null) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02072,
                this.getClass().getName() + STR_METHOD_NAME,
                " 約定入力情報nullの場合"); 
        }
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqOrderAndExecutionCompleteResponse(this);
    }
}
@
