head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.51.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorDeleteConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除確認ﾘｸｴｽﾄ(WEB3AdminMCCCOperatorDeleteConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/22 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除確認ﾘｸｴｽﾄ)<BR>
 * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除確認ﾘｸｴｽﾄ<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorDeleteConfirmRequest extends WEB3GenRequest 
{

    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorDeleteConfirmRequest.class);    
 
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorDeleteConfirm";

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
     * @@roseuid 4198642503A9
     */
    public WEB3AdminMCCCOperatorDeleteConfirmRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１−１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00833           <BR>
     * <BR>
     * ２）　@オペレータコードのチェック<BR>
     * 　@２−１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01192              <BR>
     * <BR>
     * @@roseuid 417E1FCD0111
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()"; 
        
        log.entering(STR_METHOD_NAME); 
        
        //１）　@部店コードのチェック
        // 　@１−１）　@未入力の場合、例外をスローする。
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            log.error("「部店コードが未入力である」の例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                            this.getClass().getName() + STR_METHOD_NAME);             
        }

        //２）　@オペレータコードのチェック
        // 　@２−１）　@未入力の場合、例外をスローする。<BR>
        if (this.operatorCode == null || "".equals(this.operatorCode))
        {
            log.error("「オペレータコードが未入力である」の例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01192,
                            this.getClass().getName() + STR_METHOD_NAME);             
        }
        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198642503C8
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCCCOperatorDeleteConfirmResponse(this);
    }
}
@
