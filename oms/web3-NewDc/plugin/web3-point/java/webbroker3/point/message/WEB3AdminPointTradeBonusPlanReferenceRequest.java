head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.56.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointTradeBonusPlanReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者トレードボーナスプラン照会リクエスト(WEB3AdminPointTradeBonusPlanReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/06/14 郭英(中訊) 新規作成
*/

package webbroker3.point.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者トレードボーナスプラン照会リクエスト)<BR>
 * 管理者トレードボーナスプラン照会リクエストクラス<BR>
 */
public class WEB3AdminPointTradeBonusPlanReferenceRequest extends WEB3GenRequest
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointTradeBonusPlanReferenceRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_tradeBonusPlanReference";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200506141000L;
    
    /**
     * (部店コード)<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     */
    public String accountCode;
    
    /**
     * @@roseuid 42AE3533035B
     */
    public WEB3AdminPointTradeBonusPlanReferenceRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コード<BR>
     * <BR>
     *    this.部店コード == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）顧客コード<BR>
     * <BR>
     * ２－１）<BR>
     * <BR>
     *    this.顧客コード == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * <BR>
     * ２－２）<BR>
     * <BR>
     *    this.顧客コード.length() != 6<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * @@roseuid 42A4FC8000AA
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME); 
        
        //１）部店コード
        if (this.branchCode == null)
        {
            String l_strMessage = "部店コード = null";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
            
        //２）顧客コード
        //２－１）this.顧客コード == null
        if (this.accountCode == null)
        {
            String l_strMessage = "顧客コード = null";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        //２－２）this.顧客コード.length() != 6
        if (this.accountCode.length() != 6)
        {
            String l_strMessage = "顧客コード.length() != 6";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
            
        log.exiting(STR_METHOD_NAME);     
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D125500148
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointTradeBonusPlanReferenceResponse(this);
    }
}
@
