head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.56.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointExchangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント交換共通リクエスト(WEB3AdminPointExchangeCommonRequest.java)
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
 * (ポイント交換共通リクエスト)<BR>
 * ポイント交換共通リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointExchangeCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointExchangeCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_exchangeCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290093L;
    
    /**
     * (申込ID)<BR>
     * 選択された申込ID<BR>
     */
    public String applyId;
    
    /**
     * @@roseuid 41D1254C0186
     */
    public WEB3AdminPointExchangeCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）申込ID<BR>
     * <BR>
     *    this.申込ID = null or this.申込ID != 数字<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01732<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01885<BR>
     * @@roseuid 418F4DC20359
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）申込ID
        if (this.applyId == null 
            || "".equals(this.applyId.trim()))
        {
            String l_strMessage = "申込IDerror! " + this.applyId;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01732,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        if (!WEB3StringTypeUtility.isDigit(this.applyId))
        {
            String l_strMessage = "申込IDerror! " + this.applyId;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01885,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254C01A5
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
