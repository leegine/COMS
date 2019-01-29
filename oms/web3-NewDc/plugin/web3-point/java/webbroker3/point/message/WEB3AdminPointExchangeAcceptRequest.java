head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointExchangeAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント交換受付リクエスト(WEB3AdminPointExchangeAcceptRequest.java)
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
 * (ポイント交換受付リクエスト)<BR>
 * ポイント交換受付リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointExchangeAcceptRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointExchangeAcceptRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_exchangeAccept";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290104L;
    
    /**
     * (申込ID)<BR>
     * 選択された申込IDの配列<BR>
     */
    public String[] applyId;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;
    
    /**
     * @@roseuid 41D1254E0251
     */
    public WEB3AdminPointExchangeAcceptRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）申込ID<BR>
     * <BR>
     *    this.申込ID = null or<BR>
     *    this.申込ID.length() = 0 or <BR>
     *    this.申込IDの各要素 != 数字 <BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01732<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01733<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01885<BR>
     * @@roseuid 419021C4009E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）申込ID
        if (this.applyId == null)
        {
            String l_strMessage = "申込IDerror! " + this.applyId;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01732,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        if (this.applyId.length == 0)
        {
            String l_strMessage = "申込ID[] length = 0! ";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01733,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        int l_intCount = this.applyId.length;
        for (int i = 0; i < l_intCount; i++)
        {
            if (this.applyId[i] == null 
                || "".equals(this.applyId[i].trim()))
            {
                String l_strMessage = "申込IDerror! " + this.applyId[i];
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01732,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }
            if (!WEB3StringTypeUtility.isDigit(this.applyId[i]) )
            {
                String l_strMessage = "申込IDerror! " + this.applyId[i];
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01885,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254E0271
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointExchangeAcceptResponse(this);
    }
}
@
