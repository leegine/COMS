head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountMngStateChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設管理ステータス更新共通リクエスト(WEB3AdminFEqConAccountMngStateChangeCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioAccountOpenCompleteDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外株口座開設管理ステータス更新共通リクエスト)<BR>
 * 外株口座開設管理ステータス更新共通リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountMngStateChangeCommonRequest extends WEB3GenRequest 
{
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード
     */
    public String requestNumber;
    
    /**
     * (更新後ステータス区分)<BR>
     * 更新後ステータス区分<BR>
     * <BR>
     * 1：口座開設完了<BR>
     * 9：削除
     */
    public String updatedStatusDiv;
    
    /**
     * @@roseuid 423552EA0280
     */
    public WEB3AdminFEqConAccountMngStateChangeCommonRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountMngStateChangeCommonRequest.class);
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １） 部店コード<BR>
     * 　@未入力の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）　@識別コードのチェック<BR>
     * 　@未入力の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00829<BR>
     * <BR>
     * ３）　@更新後ステータス区分のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     * 　@３－２）　@以下の値以外の場合、例外をスローする。<BR>
     * 　@　@       ・"口座開設完了"<BR>
     * 　@　@       ・"削除"
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01749<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01750<BR>
     * <BR>
     * @@roseuid 41E606A40230
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１） 部店コード 
        //未入力の場合、例外をスローする。
        if(WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        
        //２）　@識別コードのチェック 
        //未入力の場合、例外をスローする。 
        if(WEB3StringTypeUtility.isEmpty(this.requestNumber))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "識別コードが未指定です。");
        }
        
        //３）　@更新後ステータス区分のチェック 
        //３－１）　@未入力の場合、例外をスローする。 
        //３－２）　@以下の値以外の場合、例外をスローする。 
        //・"口座開設完了" 
        //・"削除" 
        if(WEB3StringTypeUtility.isEmpty(this.updatedStatusDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01749,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "更新後ステータス区分が未指定です。");
        }
        
        if(!(WEB3AioAccountOpenCompleteDef.OPEN_COMPLETE.equals(this.updatedStatusDiv) 
            || WEB3AioAccountOpenCompleteDef.DELETE.equals(this.updatedStatusDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01750,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "更新後ステータス区分[" + this.updatedStatusDiv + "]。");
            
        }
         
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 423552EA02DE
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
