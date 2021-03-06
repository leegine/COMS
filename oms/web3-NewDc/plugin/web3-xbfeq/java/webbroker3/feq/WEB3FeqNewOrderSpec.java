head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : O®VK¶àe(WEB3FeqNewOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13 ¤ûU (u) VKì¬
                 : 2005/07/25 °I(u) r[
*/
package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.ordersubmitter.io.FeqNewOrderSpec;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (O®VK¶àe)<BR>
 * O®VK¶àeNX<BR>
 * 
 * @@author ¤ûU(u)
 * @@version 1.0 
 */
public class WEB3FeqNewOrderSpec extends FeqNewOrderSpec 
{

    /**
     * (OoÍ[eBeB)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqNewOrderSpec.class);

     /**
     * (ØïÐR[h)<BR>
     * ØïÐR[h<BR>
     */
    private String institutionCode;
    
    /**
     * (­ú)<BR>
     * ­ú<BR>
     */
    private Date bizDate;

    /**
     * (­ð)<BR>
     * ­ð<BR>
     * <BR>
     * 0FDEFAULTiðwèÈµj<BR>
     * 1Ftwl<BR>
     * 2FWwl<BR>
     */
    private String orderConditionType;
    
    /**
     * (iWwljù³wl)<BR>
     * iWwljù³wl<BR>
     */
    private double wLimitPrice;
    
    /**
     * (Ïæª)<BR>
     * Ïæª<BR>
     * <BR>
     * 0F~Ý<BR>
     * 1FOÝ<BR>
     */
    private String settleDiv;
    
    /**
     * (ñ¶Ì¶PÊID) <BR>
     * ñ¶Ì¶PÊIDB <BR>
     */
    private Long firstOrderUnitId;   
  
    /**
     * @@roseuid 42CE39EA009C
     */
    public WEB3FeqNewOrderSpec(
        Trader trader, 
        boolean isBuyOrder, 
        String productCode, 
        String marketCode, 
        double quantity, 
        double limitPrice, 
        FeqExecutionConditionType execType, 
        Date orderExpDate, 
        TaxTypeEnum taxType, 
        String currencyCode) 
    {
        super(
            trader, 
            isBuyOrder, 
            productCode, 
            marketCode, 
            quantity, 
            limitPrice, 
            execType, 
            orderExpDate, 
            taxType, 
            currencyCode);                
    }
    
    /**
     * (createVK¶àe)<BR>
     * istatic\bhj<BR>
     * O®VK¶àeIuWFNgð¶¬µAÔp·éB<BR>
     * <BR>
     * V[PX}uiO¶jcreateVK¶àev QÆ<BR>
     * @@param l_strInstitutionCode - (ØïÐR[h)<BR>
     * ØïÐR[h<BR>
     * 
     * @@param l_trader - (µÒ)<BR>
     * µÒIuWFNg<BR>
     * 
     * @@param l_blnIsBuyOrder - (ist¶)<BR>
     * t¶©Ç¤©ÌtO<BR>
     * 
     * 
     * @@param l_strProductCode - (Á¿R[h)<BR>
     * Á¿R[h<BR>
     * 
     * @@param l_strMarketCode - (sêR[h)<BR>
     * sêR[h<BR>
     * 
     * @@param l_dblQuantity - (¶Ê)<BR>
     * ¶Ê<BR>
     * 
     * @@param l_dblPrice - (¶P¿)<BR>
     * ¶P¿<BR>
     * 
     * @@param l_executionCond - (·sð)<BR>
     * ·sð<BR>
     * 
     * @@param l_datExpirationDate - (¶¸øú)<BR>
     * ¶¸øú<BR>
     * 
     * @@param l_taxType - (Åæª)<BR>
     * Åæª<BR>
     * 
     * @@param l_strCurrencyCode - (ÊÝR[h)<BR>
     * ÊÝR[h<BR>
     * 
     * @@param l_strOrderConditionType - (­ð)<BR>
     * ­ð<BR>
     * 
     * @@param l_dblWLimitPrice - (iWwljù³wl)<BR>
     * iWwljù³wl<BR>
     * 
     * @@param l_strSettleDiv - (Ïæª)<BR>
     * Ïæª<BR>
     * 
     * @@param l_firstOrderUnitId - (ñ¶Ì¶PÊID)<BR>
     * ñ¶Ì¶PÊID<BR>
     * 
     * @@return webbroker3.feq.WEB3FeqNewOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 428B4376005C
     */
    public static WEB3FeqNewOrderSpec createNewOrderSpec(
        String l_strInstitutionCode,
        WEB3GentradeTrader l_trader, 
        boolean l_blnIsBuyOrder, 
        String l_strProductCode,
        String l_strMarketCode,                         
        double l_dblQuantity,
        double l_dblPrice,
        FeqExecutionConditionType l_executionCond,        
        Date l_datExpirationDate,  
        TaxTypeEnum l_taxType,                                   
        String l_strCurrencyCode,  
        String l_strOrderConditionType,
        double l_dblWLimitPrice,
        String l_strSettleDiv,                                   
        Long l_firstOrderUnitId) 
        throws WEB3SystemLayerException    
    {
        final String NEW_ORDER_SPEC =     
            "createNewOrderSpec("
            + "String, µÒ, boolean, String, String, double, double," 
            + "FeqExecutionConditionType, Date, TaxTypeEnum,"
            + "String, String, double, String, Long)";
        log.entering(NEW_ORDER_SPEC);

        //1.1æøÔÇ.get­ú( )Åæ¾µ½útð­úÉZbg·é
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //1.2 X[p[NXÌRXgN^ÉÄCX^Xð¶¬

        //ø.¶¸øú¦ø.¶¸øú==null ÌêÍAget­ú()Ìßèl  
        if (l_datExpirationDate == null)
        {
            l_datExpirationDate = l_datBizDate;    
        }

        //[ø] 
        //µÒF ø.µÒIuWFNg 
        //ist¶F ø.ist¶ 
        //Á¿R[hF ø.Á¿R[h 
        //sêR[hFø.sêR[h 
        //¶ÊF ø.¶Ê 
        //¶P¿F ø.¶P¿ 
        //·sðF ø.·sð 
        //¶¸øúF iÈºÌÆ¨èj
        //ÅæªF ø.Åæª 
        //ÊÝR[hF ø.ÊÝR[h 
        WEB3FeqNewOrderSpec l_feqNewOrderSpec
            = new WEB3FeqNewOrderSpec(
                l_trader, 
                l_blnIsBuyOrder, 
                l_strProductCode, 
                l_strMarketCode, 
                l_dblQuantity, 
                l_dblPrice, 
                l_executionCond, 
                l_datExpirationDate, 
                l_taxType, 
                l_strCurrencyCode 
            );
 
        //1.3ØïÐR[hðZbg·éB
        l_feqNewOrderSpec.setInstitutionCode(l_strInstitutionCode);
      
        //1.4­úðZbg·éBB
        l_feqNewOrderSpec.setBizDate(l_datBizDate);
      
        //1.5­ððZbg·éB
        l_feqNewOrderSpec.setOrderConditionType(l_strOrderConditionType);
      
        //1.6iWwljù³wlðZbg·éB 
        l_feqNewOrderSpec.setWLimitPrice(l_dblWLimitPrice);
      
        //1.7ÏæªðZbg·éB
        l_feqNewOrderSpec.setSettleDiv(l_strSettleDiv);

        //1.8ñ¶Ì¶PÊIDðZbg·éB
        l_feqNewOrderSpec.setFirstOrderUnitId(l_firstOrderUnitId);
      
        return l_feqNewOrderSpec;
    }

     /**
      * (set­ú)<BR>
      * ­úðZbg·éB<BR>
      * @@param l_datBizDate - (­ú)<BR>
      * ­ú<BR>
      * @@roseuid 428B42AC000E
      */
     public void setBizDate(Date l_datBizDate) 
     {
         this.bizDate = l_datBizDate; 
     }

     /**
     * (get­ú)<BR>
     * ­úðæ¾·éB<BR>
     * <BR>
     * this.­úðÔp·éB<BR>
     * @@return Date
     * @@roseuid 428B42AC002D
     */
    public Date getBizDate()
    {
        return this.bizDate;
    }
    
    /**
     * (setØïÐR[h)<BR>
     * ØïÐR[hðZbg·éB<BR>
     * @@param l_strInstitutionCode - (ØïÐR[h)<BR>
     * ØïÐR[h<BR>
     * @@roseuid 428B42000108
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;
    }
    
    /**
     * (getØïÐR[h)<BR>
     * ØïÐR[hðæ¾·éB<BR>
     * <BR>
     * this.ØïÐR[hðÔp·éB<BR>
     * @@return String
     * @@roseuid 428B423D02DD
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode;
    }
     
    /**
     * (set­ð)<BR>
     * ­ððZbg·éB<BR>
     * @@param l_strOrderConditionType - (­ð)<BR>
     * ­ð<BR>
     * @@roseuid 428B42E001F3
     */
    public void setOrderConditionType(String l_strOrderConditionType) 
    {
        this.orderConditionType = l_strOrderConditionType;
    }
    
    /**
     * (get­ð)<BR>
     * ­ððæ¾·éB<BR>
     * <BR>
     * this.­ððÔp·éB<BR>
     * @@return String
     * @@roseuid 428B42E00212
     */
    public String getOrderConditionType() 
    {
        return this.orderConditionType;
    }
    
    /**
     * (setiWwljù³wl)<BR>
     * iWwljù³wlðZbg·éB<BR>
     * @@param l_dblWLimitPriceChange - (iWwljù³wl)<BR>
     * iWwljù³wl<BR>
     * @@roseuid 428B42E0029E
     */
    public void setWLimitPrice(double l_dblWLimitPriceChange) 
    {
        this.wLimitPrice = l_dblWLimitPriceChange;   
    }
    
    /**
     * (getiWwljù³w)<BR>
     * iWwljù³wlðæ¾·éB<BR>
     * <BR>
     * this.iWwljù³wlðÔp·éB<BR>
     * @@return double
     * @@roseuid 428B42E002BE
     */
    public double getWLimitPrice() 
    {
        return this.wLimitPrice;
    }
    
    /**
     * (setÏæª)<BR>
     * ÏæªðZbg·éB<BR>
     * @@param l_strSettleDiv - (Ïæª)<BR>
     * Ïæª<BR>
     * @@roseuid 428C5D580380
     */
    public void setSettleDiv(String l_strSettleDiv) 
    {
        this.settleDiv = l_strSettleDiv;      
    }
    
    /**
     * (getÏæª)<BR>
     * Ïæªðæ¾·éB<BR>
     * <BR>
     * this.ÏæªðÔp·éB<BR>
     * @@return String
     * @@roseuid 428C5D580382
     */
    public String getSettleDiv() 
    {
        return this.settleDiv;
    }
    
    /**
     * (setñ¶Ì¶PÊID)<BR>
     * ñ¶Ì¶PÊIDðZbg·éB<BR>
     * @@param l_firstOrderUnitId - (ñ¶Ì¶PÊID)<BR>
     * ñ¶Ì¶PÊID<BR>
     */
    public void setFirstOrderUnitId(Long l_firstOrderUnitId) 
    {
        this.firstOrderUnitId = l_firstOrderUnitId;      
    }
    
    /**
     * (getñ¶Ì¶PÊID)<BR>
     * ñ¶Ì¶PÊIDðæ¾·éB<BR>
     * <BR>
     * this.ñ¶Ì¶PÊIDðÔp·éB<BR>
     * @@return Long
     */
    public Long getFirstOrderUnitId() 
    {
        return this.firstOrderUnitId;
    }

    /**
     * (isoéÜÅ¶) <BR>
     * Y¶ªoéÜÅ¶Ìêtrue <BR>
     * úÌÝ¶ÌêfalseðÔ·B <BR>
     * <BR>
     * Pj@@this.ñ¶Ì¶PÊID != nullÌêÍAtrueðÔ·B <BR>
     * @@@@  this.ñ¶Ì¶PÊID == nullÌêÍAfalseðÔ·B 
     * @@return boolean
     */
    public boolean isOrderUntilDeadLine()
    {
        return (this.firstOrderUnitId != null) ? true : false; 
    }
}
@
