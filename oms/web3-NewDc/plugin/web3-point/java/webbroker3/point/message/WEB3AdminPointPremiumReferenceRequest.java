head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.58.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品一覧リクエスト(WEB3AdminPointPremiumReferenceRequest.java)
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
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (景品一覧リクエスト)<BR>
 * 景品一覧リクエストクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumReferenceRequest extends WEB3GenRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumReferenceRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_premiumReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290072L;
    
    /**
     * (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     */
    public String categoryNo;
    
    /**
     * @@roseuid 41D12547000F
     */
    public WEB3AdminPointPremiumReferenceRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）カテゴリー番号<BR>
     * <BR>
     *    this.カテゴリー番号 = null or <BR>
     *    this.カテゴリー番号 != 数字 <BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01728<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01884<BR>
     * @@roseuid 418F5FCF0138
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）カテゴリー番号
        if (this.categoryNo == null || "".equals(this.categoryNo.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01728,
                getClass().getName() + STR_METHOD_NAME,
                "this.カテゴリー番号 = nullの場合、例外をスローする");
                
            log.debug("ポイントシステム.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;    
        }
        
        if (!WEB3StringTypeUtility.isNumber(this.categoryNo))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01884,
                getClass().getName() + STR_METHOD_NAME,
                "this.カテゴリー番号 != 数字の場合、例外をスローする");
                
            log.debug("ポイントシステム.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D12547002E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointPremiumReferenceResponse(this);
    }
}
@
