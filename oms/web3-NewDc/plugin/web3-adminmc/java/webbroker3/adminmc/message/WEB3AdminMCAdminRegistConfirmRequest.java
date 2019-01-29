head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.49.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 管理者メニュー制御管理者登録確認リクエスト(WEB3AdminMCAdminRegistConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 王敏 (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御管理者登録確認リクエスト)<BR>
 * 管理者メニュー制御管理者登録確認リクエスト<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3AdminMCAdminRegistConfirmRequest extends WEB3GenRequest 
{

    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminRegistConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminRegistConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * (管理者登録情報)<BR>
     * 管理者登録情報<BR>
     */
    public WEB3AdminMCAdminRegistUnit adminRegistUnit;
    
    /**
     * @@roseuid 4198642103D8
     */
    public WEB3AdminMCAdminRegistConfirmRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@管理者登録情報のチェック<BR>
     * 　@１−１）　@管理者登録情報.validate()をコールする。<BR>
     * 　@１−２）　@管理者登録情報.validateパスワード()をコールする。<BR>
     * <BR>
     * @@roseuid 417CB56102B9
     */
    public void validate() throws WEB3BaseException
    {
         final  String  STR_METHOD_NAME = " validate()";
         log.entering(STR_METHOD_NAME);
         
         adminRegistUnit.validate();
         adminRegistUnit.validatePassword();
         
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41986422002E
     */
    public WEB3GenResponse createResponse() 
    {
         return new WEB3AdminMCAdminRegistConfirmResponse(this);
    }
}
@
