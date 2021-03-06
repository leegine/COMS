head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionReflectorAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : ิฯใๆ๘๎๑(WEB3TPTransactionReflectorAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) VK์ฌ
                   2006/09/15 ิi@@  (u)fNo.29
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.util.WEB3LogUtility;

/**
 * (ิฯใๆ๘๎๑)
 */
public class WEB3TPTransactionReflectorAfterRepay extends WEB3TPTransactionReflector
{

    /**
     * Ooอ[eBeBB<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTransactionReflectorAfterRepay.class);

    /**
     * (ข๑่ชิฯฯนv)
     */
    private double unexecutedRepaySettleProfitLoss;

    /**
     * (ก๑ญชิฯฯนv)
     */
    private double currOrderRepaySettleProfitLoss;

    /**
     * @@roseuid 41E383D6022C
     */
    public WEB3TPTransactionReflectorAfterRepay()
    {

    }

    /**
     * (static)(createิฯใๆ๘๎๑)<BR>
     * <BR>
     * ิฯใๆ๘๎๑๐์ฌตAิpท้B<BR>
     * <BR>
     * 1)ิฯใๆ๘๎๑CX^X(="ิฯใๆ๘๎๑")๐ถฌท้B<BR> 
     * @@-ftHgRXgN^๐R[ <BR>
     * <BR>
     * 2)ถฌตฝิฯใๆ๘๎๑CX^Xฬฎซษl๐Zbg<BR> 
     * <BR>
     * @@|"ิฯใๆ๘๎๑".set]อvZ๐(:]อvZ๐ = ๘.]อvZ๐)<BR> 
     * @@|"ิฯใๆ๘๎๑".setมฟ^Cv(:ProductionType = ๘.มฟ^Cv)<BR>
     * @@|"ิฯใๆ๘๎๑".setมฟID(:long = ๘.มฟID)<BR>
     * @@|"ิฯใๆ๘๎๑".setgUNV^Cv(:FinTransactionType = ๘.gUNV^Cv)<BR> 
     * @@|"ิฯใๆ๘๎๑".setgUNVญถ๚(:Date = ๘.gUNVญถ๚)<BR>
     * @@|"ิฯใๆ๘๎๑".set๓n๚(:Date = ๘.๓n๚)<BR>
     * @@|"ิฯใๆ๘๎๑".setข๑่ส(:double = ๘.ข๑่ส)<BR> 
     * @@|"ิฯใๆ๘๎๑".setข๑่ใเ(:double = ๘.ข๑่ใเ)<BR>
     * @@|"ิฯใๆ๘๎๑".set๑่ฯส(:double = ๘.๑่ฯส)<BR>
     * @@|"ิฯใๆ๘๎๑".set๑่ฯใเ(:double = ๘.๑่ฯใเ)<BR>
     * @@|"ิฯใๆ๘๎๑".setลๆช(:TaxTypeEnum = ๘.ลๆช)<BR>
     * @@|"ิฯใๆ๘๎๑".calcฯฎฝf๚(:Date = ๘.๓n๚)<BR>
     * <BR>
     * @@|"ิฯใๆ๘๎๑".setข๑่ิฯฯนv(:double = ๘.ข๑่ิฯฯนv)<BR> 
     * @@|"ิฯใๆ๘๎๑".setก๑ญชิฯฯนv(:double = ๘.ก๑ญชิฯฯนv)<BR> 
     * <BR>
     * 3)ิฯใๆ๘๎๑CX^X๐ิpท้B<BR> 
     * <BR>
     * <BR>
     * @@param l_calcCondition - (]อvZ๐)
     * @@param l_productType - (มฟ^Cv)
     * @@param l_lngProductId - (มฟID)
     * @@param l_finTransactionType - (gUNV^Cv)
     * @@param l_finTransactionDate - (gUNVญถ๚)
     * @@param l_dblUnexecutedQuantity - (ข๑่ส)
     * @@param l_dblUnexecutedAmount - (ข๑่ใเ)
     * @@param l_dblExecutedQuantity - (๑่ฯส)
     * @@param l_dblExecutedAmount - (๑่ฯใเ)
     * @@param l_datDeliveryDate - (๓n๚)
     * @@param l_taxType - (ลๆช)
     * @@param l_dblUnexecutedRepaySettleProfitLoss - (ข๑่ิฯฯนv)
     * @@param l_dblCurrOrderRepaySettleProfitLoss - (ก๑ญชิฯฯนv)
     * @@return 
     * webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflectorAfterRepay
     * @@roseuid 41C95EA00020
     */
    public static WEB3TPTransactionReflectorAfterRepay createWEB3TPTransactionReflectorAftreRepay(
        WEB3TPCalcCondition l_calcCondition,
        ProductTypeEnum l_productType,
        long l_lngProductId,
        FinTransactionType l_finTransactionType,
        Date l_finTransactionDate,
        double l_dblUnexecutedQuantity,
        double l_dblUnexecutedAmount,
        double l_dblExecutedQuantity,
        double l_dblExecutedAmount,
        Date l_datDeliveryDate,
        TaxTypeEnum l_taxType,
        double l_dblUnexecutedRepaySettleProfitLoss,
        double l_dblCurrOrderRepaySettleProfitLoss)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPTransactionReflectorAftreRepay(WEB3TPCalcCondition, ProductTypeEnum, long, FinTransactionType, Date, double, double, double, double, Date, double, double)";
        log.entering(STR_METHOD_NAME);

        WEB3TPTransactionReflectorAfterRepay l_instance =
            new WEB3TPTransactionReflectorAfterRepay();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setProductType(l_productType);
        l_instance.setProductId(l_lngProductId);
        l_instance.setFinTransactionType(l_finTransactionType);
        l_instance.setFinTransactionDate(l_finTransactionDate);
        l_instance.setUnexecutedQuantity(l_dblUnexecutedQuantity);
        l_instance.setUnexecutedAmount(l_dblUnexecutedAmount);
        l_instance.setExecutedQuantity(l_dblExecutedQuantity);
        l_instance.setExecutedAmount(l_dblExecutedAmount);
        l_instance.setDeliveryDate(l_datDeliveryDate);
        l_instance.setTaxType(l_taxType);
        l_instance.calcReflectDay(l_datDeliveryDate);

        l_instance.setUnexecutedRepaySettleProfitLoss(l_dblUnexecutedRepaySettleProfitLoss);
        l_instance.setCurrOrderRepaySettleProfitLoss(l_dblCurrOrderRepaySettleProfitLoss);

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (getข๑่ชิฯฯนv)
     * @@return double
     * @@roseuid 41C94E6E00FB
     */
    public double getUnexecutedRepaySettleProfitLoss()
    {
        final String STR_METHOD_NAME = "getUnexecutedRepaySettleProfitLoss()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.unexecutedRepaySettleProfitLoss;
    }

    /**
     * (setข๑่ชิฯฯนv)
     * @@param l_dblUnexecutedRepaySettleProfitLoss - (ข๑่ชิฯฯนv)
     * @@roseuid 41C94E8302E0
     */
    public void setUnexecutedRepaySettleProfitLoss(double l_dblUnexecutedRepaySettleProfitLoss)
    {
        final String STR_METHOD_NAME = "setUnexecutedRepaySettleProfitLoss(double)";
        log.entering(STR_METHOD_NAME);

        this.unexecutedRepaySettleProfitLoss = l_dblUnexecutedRepaySettleProfitLoss;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getก๑ญชิฯฯนv)
     * @@return double
     * @@roseuid 41C94E83039B
     */
    public double getCurrOrderRepaySettleProfitLoss()
    {
        final String STR_METHOD_NAME = "getCurrOrderRepaySettleProfitLoss()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.currOrderRepaySettleProfitLoss;
    }

    /**
     * (setก๑ญชิฯฯนv)
     * @@param l_dblCurrOrderRepaySettleProfitLoss - (ก๑ิฯชิฯฯนv)
     * @@roseuid 41C94E84008E
     */
    public void setCurrOrderRepaySettleProfitLoss(double l_dblCurrOrderRepaySettleProfitLoss)
    {
        final String STR_METHOD_NAME = "setCurrOrderRepaySettleProfitLoss(double)";
        log.entering(STR_METHOD_NAME);

        this.currOrderRepaySettleProfitLoss = l_dblCurrOrderRepaySettleProfitLoss;

        log.exiting(STR_METHOD_NAME);
    }
}
@
