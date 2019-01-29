head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.48.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : (管理者メニュー制御管理者変更入力リクエスト(WEB3AdminMCAdminChangeInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 王敏 (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御管理者変更入力リクエスト)<BR>
 * 管理者メニュー制御管理者変更入力リクエスト<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3AdminMCAdminChangeInputRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminChangeInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
        
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (管理者コード)<BR>
     * 管理者コード<BR>
     */
    public String administratorCode;
    
    /**
     * @@roseuid 41986417032C
     */
    public WEB3AdminMCAdminChangeInputRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00833           <BR>
     * <BR>
     * ２）　@管理者コードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01215           <BR>
     * <BR>
     * @@roseuid 417CB5F4026B
     */
    public void validate() throws WEB3BaseException
    {
         final  String  STR_METHOD_NAME = " validate()";
         log.entering(STR_METHOD_NAME);
       
         //１） 部店コードのチェック
         //未入力の場合、例外をスローする。
         if (this.branchCode == null || "".equals(this.branchCode))
         {
             //例外
             log.error("「部店コード未入力」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                                                                   this.getClass().getName() + STR_METHOD_NAME);
         }
         //２） 管理者コードのチェック
         //未入力の場合、例外をスローする。
         if (this.administratorCode == null || "".equals(this.administratorCode))
         {
             //例外
             log.error("「管理者コード未入力」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01215,
                                                                   this.getClass().getName() + STR_METHOD_NAME);
         }
         
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641703A9
     */
    public WEB3GenResponse createResponse() 
    {
         return new WEB3AdminMCAdminChangeInputResponse(this);
    }
}
@
