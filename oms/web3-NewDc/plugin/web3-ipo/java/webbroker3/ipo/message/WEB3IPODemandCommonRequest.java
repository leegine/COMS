head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO申告共通リクエスト(WEB3IPODemandCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO申告共通リクエスト
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IPODemandCommonRequest extends WEB3GenRequest 
{
    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IPODemandCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_demandCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408112004L;
    
    /**
     * 申告数量
     */
    public String demandQuantity;
    
    /**
     * 申告価格区分<BR>
     * 　@0：成行<BR>
     * 　@1：指値
     */
    public String demandPriceDiv;
    
    /**
     * 申告価格
     */
    public String demandPrice;
    
    /**
     * @@roseuid 4112E79E017C
     */
    public WEB3IPODemandCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@申告価格のチェック<BR>
     * 　@this.申告価格が数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00491<BR>
     * <BR>
     * ２）　@申告価格区分のチェック<BR>
     * 　@this.申告価格区分 == ”成行” &&<BR>
     * 　@this.申告価格に入力がある（0またはnull以外）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00492<BR>
     * <BR>
     * 　@this.申告価格区分 == ”指値” &&<BR>
     * 　@this.申告価格が未入力（0またはnull）の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00493<BR>
     * <BR>
     * ３）　@申告数量のチェック<BR>
     * 　@this.申告数量が数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00494<BR>
     * @@roseuid 40D7CEA10078
     */
    public void validate() throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validate()";
        
        log.entering(STR_METHOD_NAME);
        
        //１）　@申告価格のチェック
        log.debug("１）　@申告価格のチェック: ENTER");
        if (this.demandPrice != null)
        {
            if(!WEB3StringTypeUtility.isNumber(this.demandPrice))
            {
                //this.申告価格が数値でない場合、例外をスローする。
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00491,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        log.debug("１）　@申告価格のチェック: END");
        
        //２）　@申告価格区分のチェック
        log.debug("２）-1　@申告価格区分のチェック: ENTER");
        if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.demandPriceDiv) && this.demandPrice != null && !"0".equals(this.demandPrice))
        {
            //申告価格区分 == ”成行” && 申告価格に入力がある（0またはnull以外）の場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00492,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("２）-1　@申告価格区分のチェック: END");

        log.debug("２）-2　@申告価格区分のチェック: ENTER");
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.demandPriceDiv) && (this.demandPrice == null || "0".equals(this.demandPrice)))
        {
            //申告価格区分 == ”指値” && 申告価格が未入力（0またはnull）の場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00493,
                getClass().getName() + STR_METHOD_NAME);         
        }
        log.debug("２）-2　@申告価格区分のチェック: END");
        
        //３）　@申告数量のチェック
        log.debug("３）　@申告数量のチェック: ENTER");
        if(!WEB3StringTypeUtility.isNumber(demandQuantity))
        {
            //申告数量が数値でない場合
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00494,
                getClass().getName() + STR_METHOD_NAME);   
        }
        log.debug("３）　@申告数量のチェック: END");
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E79E019A
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPODemandCommonResponse(this);
    }
}
@
