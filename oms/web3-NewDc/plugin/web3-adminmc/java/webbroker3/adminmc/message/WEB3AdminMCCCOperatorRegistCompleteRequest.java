head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.53.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録完了ﾘｸｴｽﾄ(WEB3AdminMCCCOperatorRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録完了ﾘｸｴｽﾄ)<BR>
 * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録完了ﾘｸｴｽﾄ<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorRegistCompleteRequest extends WEB3GenRequest 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorRegistCompleteRequest.class); 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorRegistComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     * <BR>
     */
    public String password;
    
    /**
     * (CCオペレータ登録情報)<BR>
     * CCオペレータ登録情報<BR>
     */
    public WEB3AdminMCCCOperatorRegistUnit ccOperatorRegistUnit;
    
    /**
     * @@roseuid 419864280157
     */
    public WEB3AdminMCCCOperatorRegistCompleteRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@CCオペレータ登録情報のチェック<BR>
     * 　@１－１）　@CCオペレータ登録情報.validate()をコールする。<BR>
     *   １－２）　@CCオペレータ登録情報.validateパスワード()をコールする。<BR>
     * <BR>
     * @@roseuid 417E1D120259
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１－１）　@CCオペレータ登録情報.validate()をコールする。
        this.ccOperatorRegistUnit.validate();
        //１－２）　@CCオペレータ登録情報.validateパスワード()をコールする。
        this.ccOperatorRegistUnit.validatePassword();
                      
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 419864280177
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCCCOperatorRegistCompleteResponse(this);
    }
}
@
