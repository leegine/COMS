head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.48.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : (管理者メニュー制御管理者変更完了リクエスト(WEB3AdminMCAdminChangeCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 王敏 (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者メニュー制御管理者変更完了リクエスト)<BR>
 * 管理者メニュー制御管理者変更完了リクエスト<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3AdminMCAdminChangeCompleteRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminChangeCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
        
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;
    
    /**
     * (管理者登録情報)<BR>
     * 管理者登録情報<BR>
     */
    public WEB3AdminMCAdminRegistUnit adminRegistUnit;
    
    /**
     * @@roseuid 419864160177
     */
    public WEB3AdminMCAdminChangeCompleteRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@管理者登録情報のチェック<BR>
     * 　@１－１）　@管理者登録情報.validate()をコールする。<BR>
     * <BR>
     * @@roseuid 417CB5C10345
     */
    public void validate() throws WEB3BaseException
    {
         final  String  STR_METHOD_NAME = " validate()";
         log.entering(STR_METHOD_NAME);
         //１）　@管理者登録情報のチェック
         adminRegistUnit.validate();
         
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641603D8
     */
    public WEB3GenResponse createResponse() 
    {
         return new WEB3AdminMCAdminChangeCompleteResponse(this);
    }
}
@
