head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.52.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除完了ﾘｸｴｽﾄ(WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/19 Tongwei (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除完了ﾘｸｴｽﾄ)<BR>
 * 管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除完了ﾘｸｴｽﾄ<BR>
 * 
 * @@author Tongwei
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorPwdLockCancelComplete";

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
     * (オペレータコード)<BR>
     * オペレータコード<BR>
     */
    public String operatorCode;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     * <BR>
     */
    public String password;
    
    /**
     * @@roseuid 4198642701C5
     */
    public WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest() 
    {
 
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  　@ BUSINESS_ERROR_00833            <BR>
     * <BR>
     * ２）　@オペレータコードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01192              <BR>
     * <BR>
     * @@roseuid 417E27F601EC
     */
    public void validate() throws WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME); 
        
        //１）　@部店コードのチェック
        //１－１）　@未入力の場合、例外をスローする。
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            log.error("部店コード==nullである");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                this.getClass().getName() + STR_METHOD_NAME);          
        } 
        
        //２）　@オペレータコードのチェック
        //２－１）　@未入力の場合、例外をスローする
        if (this.operatorCode == null || "".equals(this.operatorCode))
        {
            log.error("オペレータコードが未入力である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01192, 
                this.getClass().getName() + STR_METHOD_NAME);          
        } 

        log.exiting(STR_METHOD_NAME);          
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198642701F4
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse(this);
    }
}
@
