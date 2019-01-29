head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontRouteChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・発注経路切替共通リクエスト (WEB3AdminOffFloorChangeCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.116
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・発注経路切替共通リクエスト)<BR>
 * <BR>
 * 管理者・発注経路切替サービス（確認／完了）リクエストデータのスーパークラス。
 * <BR>
 * <BR>
 * ------<English>---------------<BR>
 * <BR>
 * WEB3AdminFrontRouteChangeCommonRequest<BR>
 * <BR>
 * super class of WEB3AdminFrontRouteChangeService(validate/submit) request data<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public abstract class WEB3AdminFrontRouteChangeCommonRequest extends WEB3GenRequest {

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontRouteChangeCommonRequest.class);
        
    /**
     * 証券会社コード<BR>
     */
    public String institutionCode;
   
    /**
     * 切替処理方式区分<BR>
     */
    public String changeProcessDiv;
   
    /**
     * 変換市場コード<BR>
     */
    public String convertMarketCode;
   
    /**
     * 市場コード<BR>
     */
    public String marketCode;
   
    /**
     * 銘柄タイプ<BR>
     */
    public String productType;
   
    /**
     * 発注経路区分<BR>
     */
    public String submitOrderRouteDiv;
   
    /**
     * 変更後発注経路区分<BR>
     */
    public String changedSubmitOrderRouteDiv;
   
    /**
     * 切替起動区分<BR>
     */
    public String changeStartDiv;
   
    /**
     * @@roseuid 42FFFED4039D
     */
    public WEB3AdminFrontRouteChangeCommonRequest() 
    {
    
    }

        
    /**
     * 当クラスのプロパティの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * @@roseuid 41B7D3A40221
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // 1-1 if institutionCode is null, throw Exception.
        if (this.institutionCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-2 if changeProcessDiv is null, throw Exception.
        else if (this.changeProcessDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02205,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-3 if convertMarketCode is null, throw Exception.
        else if (this.convertMarketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02209,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-4 if marketCode is null, throw Exception.
        else if (this.marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-5 if productType is null, throw Exception.
        else if (this.productType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01109,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-6 if submitOrderRouteDiv is null, throw Exception.
        else if (this.submitOrderRouteDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02210,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-7 if changeStartDiv is null, throw Exception.
        else if (this.changeStartDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02211,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
        
    /** (非 Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public abstract WEB3GenResponse createResponse();
}
@
