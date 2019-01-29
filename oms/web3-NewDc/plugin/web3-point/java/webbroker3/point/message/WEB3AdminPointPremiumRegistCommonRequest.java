head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品登録共通リクエスト(WEB3AdminPointPremiumRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (景品登録共通リクエスト)<BR>
 * 景品登録共通リクエストクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumRegistCommonRequest extends WEB3GenRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumRegistCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_premiumRegistCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290070L;
    
    /**
     * (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     */
    public String categoryNo;
    
    /**
     * (景品番号)<BR>
     * 景品番号<BR>
     */
    public String premiumNo;
    
    /**
     * (景品名)<BR>
     * 景品名<BR>
     */
    public String premiumName;
    
    /**
     * (必要ポイント)<BR>
     * 必要ポイント<BR>
     */
    public String requiredPoint;
    
    /**
     * (提供開始日時)<BR>
     * 提供開始日時<BR>
     */
    public Date startDate;
    
    /**
     * (提供終了日時)<BR>
     * 提供終了日時<BR>
     */
    public Date endDate;
    
    /**
     * @@roseuid 41D125480222
     */
    public WEB3AdminPointPremiumRegistCommonRequest() 
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
     * @@roseuid 418F61140290
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
     * @@roseuid 41D125480251
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
