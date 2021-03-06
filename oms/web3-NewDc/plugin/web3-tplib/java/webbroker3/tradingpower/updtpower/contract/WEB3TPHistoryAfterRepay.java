head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPHistoryAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÔÏãÊÏ®(WEB3TPHistoryAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) VKì¬
*/

package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.util.WEB3LogUtility;

/**
 * (ÔÏãÊÏ®)
 */
public class WEB3TPHistoryAfterRepay extends WEB3TPHistory
{

    /**
     * OoÍ[eBeBB
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPHistoryAfterRepay.class);

    /**
     * (¶PÊID)
     */
    private long orderUnitId;

    /**
     * @@roseuid 41E383A102C8
     */
    public WEB3TPHistoryAfterRepay()
    {

    }

    /**
     * (createÔÏãÊÏ®)<BR>
     * ÔÏãÊÏ®ð¶¬·éB<BR>
     * @@param l_targetContract - (ÎÛÊ)
     * @@param l_calcCondition - (]ÍvZð)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPHistoryAfterRepay
     */
    public static WEB3TPHistoryAfterRepay createWEB3TPHistoryAfterRepay(
        WEB3TPTargetContract l_targetContract,
        WEB3TPCalcCondition l_calcCondition)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPHistoryAfterRepay(WEB3TPTargetContract, WEB3TPCalcCondition)";
        log.entering(STR_METHOD_NAME);

        WEB3TPHistoryAfterRepay l_thisInstance = new WEB3TPHistoryAfterRepay();
        l_thisInstance.setTargetContract(l_targetContract);
        l_thisInstance.setCalcCondition(l_calcCondition);

        log.exiting(STR_METHOD_NAME);
        return l_thisInstance;
    }

    /**
     * (get¶PÊID)
     * @@return long
     * @@roseuid 41C7D13C022C
     */
    public long getOrderUnitId()
    {
        final String STR_METHOD_NAME = "getOrderUnitId()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.orderUnitId;
    }

    /**
     * (set¶PÊID)
     * @@param l_lngOrderUnitId - (¶PÊID)
     * @@roseuid 41C7D1470141
     */
    public void setOrderUnitId(long l_lngOrderUnitId)
    {
        final String STR_METHOD_NAME = "setOrderUnitId(long)";
        log.entering(STR_METHOD_NAME);

        this.orderUnitId = l_lngOrderUnitId;

        log.exiting(STR_METHOD_NAME);
    }
}
@
