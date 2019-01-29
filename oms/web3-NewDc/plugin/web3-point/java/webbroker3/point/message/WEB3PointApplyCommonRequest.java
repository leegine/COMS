head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント申込共通リクエスト(WEB3PointApplyCommonRequest.java)
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


/**
 * (ポイント申込共通リクエスト)<BR>
 * ポイント申込共通リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointApplyCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointApplyCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_applyCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290010L;
    
    /**
     * (景品番号)<BR>
     * 選択された景品番号<BR>
     */
    public String premiumNo;
    
    /**
     * @@roseuid 41D12550032C
     */
    public WEB3PointApplyCommonRequest() 
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
     * @@roseuid 418F525D0388
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）景品番号
        if (this.premiumNo == null || "".equals(this.premiumNo.trim()))
        {
            String l_strMessage = "景品番号 = null";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01727,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D12550034B
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
