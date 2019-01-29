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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍό㌚�ʕϓ�(WEB3TPHistoryAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) �V�K�쐬
*/

package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ԍό㌚�ʕϓ�)
 */
public class WEB3TPHistoryAfterRepay extends WEB3TPHistory
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPHistoryAfterRepay.class);

    /**
     * (�����P��ID)
     */
    private long orderUnitId;

    /**
     * @@roseuid 41E383A102C8
     */
    public WEB3TPHistoryAfterRepay()
    {

    }

    /**
     * (create�ԍό㌚�ʕϓ�)<BR>
     * �ԍό㌚�ʕϓ��𐶐�����B<BR>
     * @@param l_targetContract - (�Ώی���)
     * @@param l_calcCondition - (�]�͌v�Z����)
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
     * (get�����P��ID)
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
     * (set�����P��ID)
     * @@param l_lngOrderUnitId - (�����P��ID)
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
