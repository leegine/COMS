head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExchangeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : O®×Öîñ(WEB3FeqExchangeUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 sp (u) VKì¬
                 : 2005/08/02 ¤ûU (u) r[
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.define.WEB3FeqRateDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (O®×Öîñ)<BR>
 * O®×ÖîñNX
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3FeqExchangeUnit extends Message 
{
    
    /**
     * OoÍ[eBeBB<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqExchangeUnit.class);
    
    /**
     * (ÊÝR[h)<BR>
     * ÊÝR[h
     */
    public String currencyCode;
    
    /**
     * ([gæª)<BR>
     * [gæª<BR>
     * <BR>
     * 0Fî×Ö<BR>
     * 1Fñè×Ö
     */
    public String rateDiv;
    
    /**
     * (t×Ö[g)<BR>
     * t×Ö[g<BR>
     * <BR>
     * ¦o^üÍX|XÅZbg³êélÍAÏXOÌlB<BR>
     *    »êÈOÅZbg³êélÍAÏXãÌlB<BR>
     *    iüÍ³êÄÈ¢êÍAnullj
     */
    public String sellExchangeRate;
    
    /**
     * (t×Ö[g)<BR>
     * t×Ö[g<BR>
     * <BR>
     * ¦o^üÍX|XÅZbg³êélÍAÏXOÌlB<BR>
     *    »êÈOÅZbg³êélÍAÏXãÌlB<BR>
     *    iüÍ³êÄÈ¢êÍAnullj
     */
    public String buyExchangeRate;
    
    /**
     * (o^ú)<BR>
     * o^ú
     */
    public Date registDatetime;
    
    /**
     * (O®×Öîñ)<BR>
     * RXgN^
     * @@roseuid 4200A6850060
     */
    public WEB3FeqExchangeUnit() 
    {
     
    }
    
    /**
     * ×Öîñf[^Ì®«ð`FbN·éB<BR>
     * <BR>
     * Pj@@t×Ö[gÌ`FbN<BR>
     * @@üÍª éêit×Ö[g != nulljÌÝAÈºÌÀ{<BR>
     * <BR>
     * @@P|Pj@@lÅÈ¢êAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02013<BR>
     * @@P|Qj@@lÉÏ·µ½ÌLøªA®RC<BR>
     * @@¬SÌÍÍOÅ êÎAáO¦ðX[·éB<BR>
     * @@@@¦[gæªÉæÁÄáObZ[Wðª¯é<BR>
     * @@@@i[gæª == hî×ÖhjÌêAutî×ÖG[v<BR>
     * @@@@class: WEB3BusinessLayerException<BR>
     * @@@@tag:   BUSINESS_ERROR_02014<BR>@@
     * @@@@i[gæª == hñè×ÖhjÌêAutñè×ÖG[v<BR>
     * @@@@class: WEB3BusinessLayerException<BR>
     * @@@@tag:   BUSINESS_ERROR_02015<BR>@@
     * @@P|Rj@@t×Ö[g <= 0 ÌêAáO¦ðX[·éB<BR>
     * @@@@¦[gæªÉæÁÄáObZ[Wðª¯é<BR>
     * @@@@i[gæª == hî×ÖhjÌêAutî×ÖG[v<BR>
     * @@@@class: WEB3BusinessLayerException<BR>
     * @@@@tag:   BUSINESS_ERROR_02014<BR>@@
     * @@@@i[gæª == hñè×ÖhjÌêAutñè×ÖG[v<BR>
     * @@@@class: WEB3BusinessLayerException<BR>
     * @@@@tag:   BUSINESS_ERROR_02015<BR>@@
     * <BR>
     * Qj@@t×Ö[gÌ`FbN<BR>
     * @@üÍª éêit×Ö[g != nulljÌÝAÈºÌÀ{<BR>
     * <BR>
     * @@Q|Pj@@lÅÈ¢êAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02016<BR>
     * @@Q|Qj@@lÉÏ·µ½ÌLøªA®RC<BR>
     * ¬SÌÍÍOÅ êÎAáO¦ðX[·éB<BR>
     * @@@@¦[gæªÉæÁÄáObZ[Wðª¯é<BR>
     * @@@@i[gæª == hî×ÖhjÌêAutî×ÖG[v<BR>
     * @@@@class: WEB3BusinessLayerException<BR>
     * @@@@tag:   BUSINESS_ERROR_02017<BR>@@
     * @@@@i[gæª == hñè×ÖhjÌêAutñè×ÖG[v<BR>
     * @@@@class: WEB3BusinessLayerException<BR>
     * @@@@tag:   BUSINESS_ERROR_02018<BR>@@
     * @@Q|Rj@@t×Ö[g <= 0 ÌêAáO¦ðX[·éB<BR>
     * @@@@¦[gæªÉæÁÄáObZ[Wðª¯é<BR>
     * @@@@i[gæª == hî×ÖhjÌêAutî×ÖG[v<BR>
     * @@@@class: WEB3BusinessLayerException<BR>
     * @@@@tag:   BUSINESS_ERROR_02017<BR>@@
     * @@@@i[gæª == hñè×ÖhjÌêAutñè×ÖG[v<BR>
     * @@@@class: WEB3BusinessLayerException<BR>
     * @@@@tag:   BUSINESS_ERROR_02018<BR>@@
     * @@throws WEB3BaseException
     * @@roseuid 42BA5C6E005D
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //Pj@@t×Ö[gÌ`FbN
        //üÍª éêit×Ö[g != nulljÌÝAÈºÌÀ{
        if (!WEB3StringTypeUtility.isEmpty(this.sellExchangeRate))
        {
            //P|Pj@@lÅÈ¢êAáOðX[·éB
            if (!WEB3StringTypeUtility.isNumber(this.sellExchangeRate))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02013,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " t×Ö[gªlÈOÌlÅ·:" + this.sellExchangeRate); 
            }
            
            //P|Qj@@lÉÏ·µ½ÌLøªA®RC
            //¬SÌÍÍOÅ êÎAáO¦ðX[·éB
            int l_intIntCnt = WEB3StringTypeUtility.getIntegerDigits(this.sellExchangeRate);
            int l_intFacCnt = WEB3StringTypeUtility.getFractionDigits(this.sellExchangeRate);
                
            if (l_intIntCnt > 3 || l_intFacCnt > 4)
            {
                //¦[gæªÉæÁÄáObZ[Wðª¯é 
                //i[gæª == hî×ÖhjÌêAutî×ÖG[v
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02014,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " t×Ö[g(î×Ö)ª®RC¬SÌÍÍOÅ·:" + this.sellExchangeRate); 
                }                   
                //i[gæª == hñè×ÖhjÌêAutñè×ÖG[v 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02015,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " t×Ö[g(ñè×Ö)ª®RC¬SÌÍÍOÅ·:" + this.sellExchangeRate); 
                }
            }

            double l_dblSellExecRate = Double.parseDouble(this.sellExchangeRate);
            //P|Rj@@t×Ö[g <= 0 ÌêAáO¦ðX[·éB
            if (l_dblSellExecRate <= 0)
            {
                //¦[gæªÉæÁÄáObZ[Wðª¯é 
                //i[gæª == hî×ÖhjÌêAutî×ÖG[v
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02014,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " t×Ö[g(î×Ö)ª<= 0Å·:" + this.sellExchangeRate); 
                }                   
                //i[gæª == hñè×ÖhjÌêAutñè×ÖG[v 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02015,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " t×Ö[g(ñè×Ö)ª<= 0Å·:" + this.sellExchangeRate); 
                }
            }                
        }            
            
        //Qj@@t×Ö[gÌ`FbN
        //üÍª éêit×Ö[g != nulljÌÝAÈºÌÀ{
        if (!WEB3StringTypeUtility.isEmpty(this.buyExchangeRate))
        {
            //Q|Pj@@lÅÈ¢êAáOðX[·éB
            if (!WEB3StringTypeUtility.isNumber(this.buyExchangeRate))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02016,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " t×Ö[gªlÈOÌlÅ·:" + this.buyExchangeRate); 
            }
                
            //Q|Qj@@lÉÏ·µ½ÌLøªA®RC
            //¬SÌÍÍOÅ êÎAáO¦ðX[·éB    
            int l_intIntCnt = WEB3StringTypeUtility.getIntegerDigits(this.buyExchangeRate);
            int l_intFacCnt = WEB3StringTypeUtility.getFractionDigits(this.buyExchangeRate);
                
            if (l_intIntCnt > 3 || l_intFacCnt > 4)
            {
                //¦[gæªÉæÁÄáObZ[Wðª¯é 
                //i[gæª == hî×ÖhjÌêAutî×ÖG[v
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02017,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " t×Ö[g(î×Ö)ª®RC¬SÌÍÍOÅ·:" + this.buyExchangeRate); 
                }                   
                //i[gæª == hñè×ÖhjÌêAutñè×ÖG[v 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02018,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " t×Ö[g(ñè×Ö)ª®RC¬SÌÍÍOÅ·:" + this.buyExchangeRate); 
                }
            }
            
            double l_dblBuyExecRate = Double.parseDouble(this.buyExchangeRate);
            //Q|Rj@@t×Ö[g <= 0 ÌêAáO¦ðX[·éB
            if (l_dblBuyExecRate <= 0)
            {
                //¦[gæªÉæÁÄáObZ[Wðª¯é 
                //i[gæª == hî×ÖhjÌêAutî×ÖG[v
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02017,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " t×Ö[g(î×Ö)ª<= 0Å·:" + this.buyExchangeRate); 
                }                   
                //i[gæª == hñè×ÖhjÌêAutñè×ÖG[v 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {                    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02018,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " t×Ö[g(ñè×Ö)ª<= 0Å·:" + this.buyExchangeRate); 
                }
            }
        }                                    
        
        log.exiting(STR_METHOD_NAME);       
    }
}
@
