head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.50.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ変更入力リクエスト(WEB3AdminMCAdminPermGrpChangeInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 屈陽 (中訊) 新規作成 
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
 * (管理者メニュー制御管理者権限グループ変更入力リクエスト)<BR>
 * 管理者メニュー制御管理者権限グループ変更入力リクエスト<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpChangeInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_admin_perm_grp_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411181540L;
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpChangeInputRequest.class);
    
    /**
     * (権限レベルコード)<BR>
     * 権限レベルコード<BR>
     */
    public String permissionLevel;
    
    /**
     * @@roseuid 4198641C008C
     */
    public WEB3AdminMCAdminPermGrpChangeInputRequest() 
    {
         
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@権限レベルコードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01201          <BR>
     * 　@１－２）　@文字数が3byteでない場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01202          <BR>
　@   *   １－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01203          <BR>
     * <BR>
     * @@roseuid 4177211102EE
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //１）権限レベルコードのチェック
        //１－１）未入力の場合、例外をスローする。
        //       class:WEB3BusinessLayerException 
        //         tag:BUSINESS_ERROR_01201                
        if (this.permissionLevel == null || "".equals(this.permissionLevel))
        {
            log.error("権限レベルコード未入力");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01201,
                this.getClass().getName() + "." + l_strMethodName);                
        }        
        
        // 　@１－２）　@文字数が3byteでない場合、例外をスローする。
        //                class :  WEB3BusinessLayerException 
        //                tag :     BUSINESS_ERROR_01202   
        if (WEB3StringTypeUtility.getByteLength(this.permissionLevel) != 3)
        {
            log.error(" 文字数が3byteでない .");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01202,
                this.getClass().getName() + "." + l_strMethodName);
        }   
        
        //    １－３）　@数字以外の文字が含まれる場合、例外をスローする。
        //                 class :  WEB3BusinessLayerException 
        //                 tag :     BUSINESS_ERROR_01203          
        if (!WEB3StringTypeUtility.isNumber(this.permissionLevel))
        {
            log.error(" 数字以外の文字が含まれる.");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01203,
                this.getClass().getName() + "." + l_strMethodName);
        } 
        
        log.exiting(l_strMethodName);                
               
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641C00AB
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPermGrpChangeInputResponse(this);
    }
}
@
