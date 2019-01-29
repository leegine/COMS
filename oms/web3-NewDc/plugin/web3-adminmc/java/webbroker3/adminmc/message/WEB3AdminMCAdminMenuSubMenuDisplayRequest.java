head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.54.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminMenuSubMenuDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御サブメニュー表示リクエスト(WEB3AdminMCAdminMenuSubMenuDisplayRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者メニュー制御サブメニュー表示リクエスト)<BR>
 * 管理者メニュー制御サブメニュー表示リクエスト<BR>
 * @@author 温顕法@
 * @@version 1.0
 */
 
public class WEB3AdminMCAdminMenuSubMenuDisplayRequest extends WEB3GenRequest 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminMenuSubMenuDisplayRequest.class); 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminMenuSubMenuDisplay";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (機@能カテゴリコード一覧)<BR>
     * 機@能カテゴリコード一覧<BR>
     * <BR>
     */
    public String[] transactionCategoryList;
    
    /**
     * @@roseuid 4198641A0177
     */
    public WEB3AdminMCAdminMenuSubMenuDisplayRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@機@能カテゴリコード一覧のチェック<BR>
     * 　@１－１）　@未入力の場合，配列のサイズが0の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   BUSINESS_ERROR_01214            <BR>
     * <BR>
     * @@roseuid 41773E510119
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);
        // １）　@機@能カテゴリコード一覧のチェック<BR>
        // 　@１－１）　@未入力の場合，配列のサイズが0の場合、例外をスローする。<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :   BUSINESS_ERROR_01214            <BR> 
         
        
        
        if (this.transactionCategoryList == null)
        {
            log.error(" 機@能カテゴリコード一覧未入力の場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01214,
                            this.getClass().getName() + STR_METHOD_NAME);
            
        } 
        int l_intListCount = this.transactionCategoryList.length;
        
        if(l_intListCount == 0)
        {
            log.error(" 機@能カテゴリコード一覧配列のサイズが0の場合 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01214,
                            this.getClass().getName() + STR_METHOD_NAME);
            
        }         
        log.exiting(STR_METHOD_NAME);      
           
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641A0196
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminMenuSubMenuDisplayResponse(this);
    }
}
@
