head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
 File Name        : ØŒ”•Ï“®(WEB3TPSecurityChange.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 R“c@@‘ìi (FLJ) V‹Kì¬
                    2006/09/14 Ôi@@     (’†u)ƒ‚ƒfƒ‹No.36
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (ØŒ”•Ï“®)
 *
 * —a‚èc‚•Ï“®‚ğ•\Œ»‚·‚é
 */
public abstract class WEB3TPSecurityChange
    extends WEB3TPAssetReflector
{

    /**
     * (ó“n“ú)
     */
    private Date deliveryDate;
    
    /**
     * (—a‚è‹æ•ª)
     */
    private String depositType;

    /**
     * (“Á’èŒûÀƒtƒ‰ƒO)
     */
    private boolean isSpecialAccountFlag;

    /**
     * (•Ï“®”—Ê)
     */
    private double quantity;

    /**
     * (•]‰¿’P‰¿)
     */
    private double unitPrice;

    /**
     * (•]‰¿Š|–Ú)
     */
    private double valuationRatio;

    /**
     * (•]‰¿Šz)
     */
    private double valuationPrice;

    /**
     * (Å‹æ•ª)
     */
    private TaxTypeEnum taxType;  
    
    /**
     * @@roseuid 41087D760060
     */
    public WEB3TPSecurityChange()
    {

    }

    /**
     * (get—a‚è‹æ•ª)<BR>
     * —a‚è‹æ•ª‚ğæ“¾‚·‚é
     * @@return String
     * @@roseuid 40B2D37601FB
     */
    public String getDepositType()
    {
        return depositType;
    }

    /**
     * (set—a‚è‹æ•ª)<BR>
     * —a‚è‹æ•ª‚ğİ’è‚·‚éB
     * @@param l_strDepositType - (—a‚è‹æ•ª)
     * @@roseuid 40B2D3EC02A7
     */
    public void setDepositType(String l_strDepositType)
    {
        depositType = l_strDepositType;
    }

    /**
     * (is“Á’èŒûÀ‹æ•ª)<BR>
     * “Á’èŒûÀ‹æ•ª‚ğæ“¾‚·‚é
     * @@return boolean
     * @@roseuid 40B2D38E0027
     */
    public boolean isSpecialAccount()
    {
        return isSpecialAccountFlag;
    }

    /**
     * (set“Á’èŒûÀ‹æ•ª)<BR>
     * “Á’èŒûÀ‹æ•ª‚ğİ’è‚·‚éB
     * @@param l_isSpecialAccountFlag - (“Á’èŒûÀ‹æ•ª)
     * @@roseuid 40B2D3EC02C6
     */
    public void setSpecialAccountFlag(boolean l_isSpecialAccountFlag)
    {
        isSpecialAccountFlag = l_isSpecialAccountFlag;
    }

    /**
     * (get•Ï“®”—Ê)<BR>
     * •Ï“®”—Ê‚ğæ“¾‚·‚é
     * @@return double
     * @@roseuid 40B2D39F00E2
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * (set•Ï“®”—Ê)<BR>
     * •Ï“®”—Ê‚ğİ’è‚·‚éB
     * @@param l_dblQuantity - (•Ï“®”—Ê)
     * @@roseuid 40B2D3EC02E6
     */
    public void setQuantity(double l_dblQuantity)
    {
        quantity = l_dblQuantity;
    }

    /**
     * (get•]‰¿’P‰¿)<BR>
     * •]‰¿’P‰¿‚ğæ“¾‚·‚éB
     * @@return double
     * @@roseuid 40B2D3A803C0
     */
    public double getUnitPrice()
    {
        return unitPrice;
    }

    /**
     * (set•]‰¿’P‰¿)<BR>
     * •]‰¿’P‰¿‚ğİ’è‚·‚éB
     * @@param l_dblUnitPrice - (•]‰¿’P‰¿)
     * @@roseuid 40B2D3EC0315
     */
    public void setUnitPrice(double l_dblUnitPrice)
    {
        unitPrice = l_dblUnitPrice;
    }

    /**
     * (get•]‰¿Š|–Ú)<BR>
     * •]‰¿Š|–Ú‚ğæ“¾‚·‚éB
     * @@return double
     * @@roseuid 40B2D3CA0007
     */
    public double getValuationRatio()
    {
        return valuationRatio;
    }

    /**
     * (set•]‰¿Š|–Ú)<BR>
     * •]‰¿Š|–Ú‚ğİ’è‚·‚éB
     * @@param l_dblValuationRatio - (•]‰¿Š|–Ú)
     * @@roseuid 40B2D3EC0334
     */
    public void setValuationRatio(double l_dblValuationRatio)
    {
        valuationRatio = l_dblValuationRatio;
    }

    /**
     * (get•]‰¿Šz)<BR>
     * •]‰¿Šz‚ğæ“¾‚·‚éB
     * @@return double
     * @@roseuid 40B3329B02F5
     */
    public double getValuationPrice()
    {
        return valuationPrice;
    }

    /**
     * (set•]‰¿Šz)<BR>
     * •]‰¿Šz‚ğİ’è‚·‚éB
     * @@param l_dblValuationPrice - (•]‰¿Šz)
     * @@roseuid 40B333E102B7
     */
    public void setValuationPrice(double l_dblValuationPrice)
    {
        valuationPrice = l_dblValuationPrice;
    }

    /**
     * (getó“n“ú)<BR>
     * ó“n“ú‚ğæ“¾‚·‚éB
     * @@return deliveryDate ‚ğ–ß‚µ‚Ü‚·B
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * (setó“n“ú)<BR>
     * ó“n“ú‚ğİ’è‚·‚éB
     * @@param deliveryDate deliveryDate ‚ğİ’èB
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        this.deliveryDate = l_datDeliveryDate;
    }

    /**
     * (to“Á’èŒûÀ‹æ•ª)<BR>
     * Å‹æ•ª‚ğ“Á’èŒûÀ‹æ•ª‚Ö•ÏŠ·‚·‚é
     * @@param l_taxType - (Å‹æ•ª)
     * @@return boolean
     */
    public boolean toSpecialAccountFlag(TaxTypeEnum l_taxType)
    {
        if (l_taxType != null && (l_taxType.equals(TaxTypeEnum.SPECIAL)
                                  || l_taxType.equals(TaxTypeEnum.SPECIAL_WITHHOLD)))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * (setÅ‹æ•ª) <BR>
     * <BR>
     * ˆø”.Å‹æ•ª‚ğAthis.Å‹æ•ª‚ÉƒZƒbƒg‚·‚éB<BR>
     * @@param l_taxType  - (Å‹æ•ª)
     */
    public void setTaxType(TaxTypeEnum l_taxType)
    {
        this.taxType = l_taxType;
    }    
    
    /**
     * (getÅ‹æ•ª)<BR>
     * <BR>
     * this.Å‹æ•ª‚ğ•Ô‹p‚·‚éB <BR>
     * @@return taxType
     */
    public TaxTypeEnum getTaxType()
    {
        return taxType;
    }    
    
    /**
     * ‚±‚ÌƒIƒuƒWƒFƒNƒg‚Ì•¶š—ñ•\Œ»‚ğ•Ô‚·B
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("depositType", getDepositType())
            .append("isSpecialAccount", isSpecialAccount())
            .append("quantity", getQuantity())
            .append("unitPrice", getUnitPrice())
            .append("valuationRatio", getValuationRatio())
            .append("valuationPrice", getValuationPrice())
            .append("deliveryDate", getDeliveryDate())
            .append("taxType", getTaxType())
            .toString();
    }
}
@
