head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointManageCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント管理共通リクエスト(WEB3AdminPointManageCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (ポイント管理共通リクエスト)<BR>
 * ポイント管理共通リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointManageCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointManageCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_manageCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290086L;
    
    /**
     * (部店コード)<BR>
     * 入力された部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 入力された顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * @@roseuid 41D1254D006D
     */
    public WEB3AdminPointManageCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コード<BR>
     * <BR>
     *    this.部店コード.length() != 3 or<BR>
     *    this.部店コード != 数字<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01729<BR>
     * <BR>
     * ２）顧客コード<BR>
     * <BR>
     *    this.顧客コード.length() != 6 or<BR>
     *    this.顧客コード != 数字<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * @@roseuid 41944A040242
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）部店コード
        if (this.branchCode == null || "".equals(this.branchCode.trim()) || WEB3StringTypeUtility.getByteLength(this.branchCode) != 3)
        {
            String l_strMessage = "部店コード = null !";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            String l_strMessage = "部店コード != 数字 " + this.branchCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        
        // ２）顧客コード
        if (this.accountCode == null || "".equals(this.accountCode.trim()) || WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            String l_strMessage = "顧客コード = null !";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            String l_strMessage = "顧客コード != 数字 " + this.accountCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254D008C
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
