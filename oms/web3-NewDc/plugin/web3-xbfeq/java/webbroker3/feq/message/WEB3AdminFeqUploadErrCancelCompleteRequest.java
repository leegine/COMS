head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqUploadErrCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除完了リクエスト(WEB3AdminFeqUploadErrCancelCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqErrorCancelTargetDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除完了リクエスト)<BR>
 * 管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除完了リクエスト
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqUploadErrCancelCompleteRequest extends WEB3GenRequest 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqUploadErrCancelCompleteRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_uploadErrCancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (エラー解除対象機@能区分)<BR>
     * エラー解除対象機@能区分<BR>
     * <BR>
     * 1：　@注文受付<BR>
     * 2：　@約定
     */
    public String errorCancelTargetDiv;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号
     */
    public String password;
    
    /**
     * @@roseuid 42CE3A0300BB
     */
    public WEB3AdminFeqUploadErrCancelCompleteRequest() 
    {
     
    }
    
    /**
     * リクエストデータをチェックする。<BR>
     * <BR>
     * １）　@エラー解除対象機@能区分<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02048<BR>
     * 　@１－２）　@有効なコード値でない場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02049<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42BBB4840108
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@エラー解除対象機@能区分
        //１－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.errorCancelTargetDiv))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02048,
                this.getClass().getName() + STR_METHOD_NAME,
                " エラー解除対象機@能区分が未入力です。"); 
        }
        //１－２）　@有効なコード値でない場合、例外をスローする。
        if (!(WEB3FeqErrorCancelTargetDivDef.ORDER_ACCEPT.equals(this.errorCancelTargetDiv) || 
            WEB3FeqErrorCancelTargetDivDef.EXECUTED.equals(this.errorCancelTargetDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02049,
                this.getClass().getName() + STR_METHOD_NAME,
                "エラー解除対象機@能区分が不正な値です:" + this.errorCancelTargetDiv); 
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
        return new WEB3AdminFeqUploadErrCancelCompleteResponse(this);
    }
}
@
