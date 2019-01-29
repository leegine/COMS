head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来入力リクエスト(WEB3AdminFeqExecutionInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー
Revesion History : 2009/08/03 車進(中訊)   モデル　@No.505対応
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者外国株式出来入力リクエスト)<BR>
 * 管理者外国株式出来入力リクエストクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionInputRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =  WEB3LogUtility.getInstance(WEB3AdminFeqExecutionInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * (運用コード)<BR>
     * 運用コード
     */
    public String managementCode;
    
    /**
     * (発注日)<BR>
     * 発注日
     */
    public Date orderBizDate;
    
    /**
     * @@roseuid 42CE39FE0128
     */
    public WEB3AdminFeqExecutionInputRequest() 
    {
     
    }
    
    /**
     * リクエストデータをチェックする。<BR>
     * <BR>
     * １）　@運用コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02032<BR>
     * 　@１－２）　@5桁の半角数字でない場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_03163<BR>
     * @@throws WEB3BaseException
     * @@roseuid 428C566B0185
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);

        //１－１）運用コード未入力の場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(managementCode)) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02032,
                this.getClass().getName() + STR_METHOD_NAME,
                " 未入力の場合チェック"); 
        } 

        //１－２）5桁の半角数字でない場合、例外をスローする。
        else if (this.managementCode.length() != 5 || 
            !WEB3StringTypeUtility.isDigit(this.managementCode))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03163,
                this.getClass().getName() + STR_METHOD_NAME,
                "運用コードが5桁の半角数字ではありません。"); 
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
        return new WEB3AdminFeqExecutionInputResponse(this);
    }
}
@
