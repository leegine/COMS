head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.53.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpDeleteConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ削除確認リクエスト(WEB3AdminMCAdminPermGrpDeleteConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者メニュー制御管理者権限グループ削除確認リクエスト)<BR>
 * 管理者メニュー制御管理者権限グループ削除確認リクエスト<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPermGrpDeleteConfirmRequest extends WEB3GenRequest 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpDeleteConfirmRequest.class); 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPermGrpDeleteConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (権限レベルコード)<BR>
     * 権限レベルコード<BR>
     */
    public String permissionLevel;
    
    /**
     * @@roseuid 4198641D001F
     */
    public WEB3AdminMCAdminPermGrpDeleteConfirmRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@権限レベルコードのチェック<BR>
     * 　@１−１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01201          <BR>
     * 　@１−２）　@文字数が3byteでない場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01202          <BR>
     * 　@１−３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01203          <BR>
     * <BR>
     * @@roseuid 41773DE70203
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);

        // １）　@権限レベルコードのチェック<BR>
        // 　@１−１）　@未入力の場合、例外をスローする。<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_01201          <BR>
        if (this.permissionLevel == null )
        {
            log.error("権限レベルコード未入力.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01201,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }        
        // 　@１−２）　@文字数が3byteでない場合、例外をスローする。<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :     BUSINESS_ERROR_01202          <BR>
        if (WEB3StringTypeUtility.getByteLength(this.permissionLevel) != 3)
        {
            log.error(" 文字数が3byteでない .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01202,
                            this.getClass().getName() + STR_METHOD_NAME);
        }         
        
        // 　@１−３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :     BUSINESS_ERROR_01203          <BR>
        if (!WEB3StringTypeUtility.isNumber(this.permissionLevel))
        {
            log.error(" 数字以外の文字が含まれる .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01203,
                            this.getClass().getName() + STR_METHOD_NAME);
        }          
             
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641D005D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPermGrpDeleteConfirmResponse(this);
    }
}
@
