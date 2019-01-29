head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSummaryDayTradeSwapContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : “úŒv‚è•ÔÏEŒ»ˆøŒ»“nŒš‹Ê‚ÌWŒv(WEB3TPSummaryDayTradeSwapContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 âV“¡@@‰hO (FLJ) V‹Kì¬
Revesion History : 2008/01/31 ƒgƒE–N|@@(’†u)@@d—l•ÏX@@ƒ‚ƒfƒ‹No.257
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (“úŒv‚è•ÔÏEŒ»ˆøŒ»“nŒš‹Ê‚ÌWŒv)
 */
public class WEB3TPSummaryDayTradeSwapContract extends WEB3TPContractBase 
{
    
    /**
     * (Œ»ˆøŒ»“nŒš‹Ê•]‰¿‘¹)
     */
    private double swapContractAssetLoss;
    
    /**
     * (Œ»ˆøŒ»“nŒš‹Ê•]‰¿‰v)
     */
    private double swapContractAssetProfit;

    /**
     * (Œ»ˆøŒ»“nŒš‹ÊŒˆÏ‘¹)
     */
    private double swapContractSettleLoss;

    /**
     * @@roseuid 4104AE400177
     */
    public WEB3TPSummaryDayTradeSwapContract() 
    {
     
    }
    
    /**
     * (create“úŒv‚è•ÔÏEŒ»ˆøŒ»“nŒš‹Ê‚ÌWŒv)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryDayTradeSwapContract
     * @@roseuid 4100F15A0157
     */
    public static WEB3TPSummaryDayTradeSwapContract create() 
    {
        return new WEB3TPSummaryDayTradeSwapContract();
    }
    
    /**
     * (getŒ»ˆøŒ»“nŒš‹Ê•]‰¿‘¹) <BR>
     * <BR>
     * this.Œ»ˆøŒ»“nŒš‹Ê•]‰¿‘¹‚ğ•Ô‹p‚·‚éB <BR>
     * <BR>
     * @@return double
     */
    public double getSwapContractAssetLoss()
    {
        return swapContractAssetLoss;
    }

    /**
     * (setŒ»ˆøŒ»“nŒš‹Ê•]‰¿‘¹) <BR>
     * <BR>
     * ˆø”.Œ»ˆøŒ»“nŒš‹Ê•]‰¿‘¹‚ğA <BR>
     * this.Œ»ˆøŒ»“nŒš‹Ê•]‰¿‘¹‚ÉƒZƒbƒg‚·‚éB <BR>
     * <BR>
     * @@param l_dblSwapContractAssetLoss - (Œ»ˆøŒ»“nŒš‹Ê•]‰¿‘¹)
     */
    public void setSwapContractAssetLoss(double l_dblSwapContractAssetLoss)
    {
        this.swapContractAssetLoss = l_dblSwapContractAssetLoss;
    }

    /**
     * (getŒ»ˆøŒ»“nŒš‹Ê•]‰¿‰v) <BR>
     * <BR>
     * this.Œ»ˆøŒ»“nŒš‹Ê•]‰¿‰v‚ğ•Ô‹p‚·‚éB <BR>
     * <BR>
     * @@return double
     */
    public double getSwapContractAssetProfit()
    {
        return swapContractAssetProfit;
    }

    /**
     * (setŒ»ˆøŒ»“nŒš‹Ê•]‰¿‰v) <BR>
     * <BR>
     * ˆø”.Œ»ˆøŒ»“nŒš‹Ê•]‰¿‰v‚ğA<BR> 
     * this.Œ»ˆøŒ»“nŒš‹Ê•]‰¿‰v‚ÉƒZƒbƒg‚·‚éB <BR>
     * <BR>
     * @@param l_dblSwapContractAssetProfit - (Œ»ˆøŒ»“nŒš‹Ê•]‰¿‰v)
     */
    public void setSwapContractAssetProfit(double l_dblSwapContractAssetProfit)
    {
        this.swapContractAssetProfit = l_dblSwapContractAssetProfit;
    }

    /**
     * (getŒ»ˆøŒ»“nŒš‹ÊŒˆÏ‘¹)<BR>
     * this.Œ»ˆøŒ»“nŒš‹ÊŒˆÏ‘¹‚ğ•Ô‹p‚·‚éB<BR>
     * @@return double
     */
    public double getSwapContractSettleLoss()
    {
        return swapContractSettleLoss;
    }

    /**
     * (setŒ»ˆøŒ»“nŒš‹ÊŒˆÏ‘¹)<BR>
     * ˆø”.Œ»ˆøŒ»“nŒš‹ÊŒˆÏ‘¹‚ğAthis.Œ»ˆøŒ»“nŒš‹ÊŒˆÏ‘¹‚ÉƒZƒbƒg‚·‚éB<BR>
     * @@param l_dblTradeSwapContractLoss - (Œ»ˆøŒ»“nŒš‹ÊŒˆÏ‘¹)
     */
    public void setSwapContractSettleLoss(double l_dblSwapContractSettleLoss)
    {
        this.swapContractSettleLoss = l_dblSwapContractSettleLoss;
    }

    /**
     * ‚±‚ÌƒIƒuƒWƒFƒNƒg‚Ì•¶š—ñ•\Œ»‚ğ•Ô‚·B
     */
    public String toString() 
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("swapContractAssetLoss", getSwapContractAssetLoss())
            .append("swapContractAssetProfit", getSwapContractAssetProfit())
            .toString();
    }
}
@
