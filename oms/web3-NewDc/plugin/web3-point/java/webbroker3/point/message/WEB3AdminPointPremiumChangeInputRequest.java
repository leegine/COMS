head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品訂正入力リクエスト(WEB3AdminPointPremiumChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (景品訂正入力リクエスト)<BR>
 * 景品訂正入力リクエストクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumChangeInputRequest extends WEB3GenRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumChangeInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_premiumChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290078L;
    
    /**
     * (景品番号)<BR>
     * 景品番号<BR>
     */
    public String premiumNo;
    
    /**
     * @@roseuid 41D125490232
     */
    public WEB3AdminPointPremiumChangeInputRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）景品番号<BR>
     * <BR>
     *    this.景品番号 = null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01727<BR>
     * @@roseuid 41933AD4003D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）景品番号
        if (this.premiumNo == null || "".equals(this.premiumNo.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01727,
                getClass().getName() + STR_METHOD_NAME,
                "this.景品番号 = nullの場合、例外をスローする");
                
            log.debug("ポイントシステム.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;    
        }
        
        log.exiting(STR_METHOD_NAME);   
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D125490251
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointPremiumChangeInputResponse(this);
    }
}
@
