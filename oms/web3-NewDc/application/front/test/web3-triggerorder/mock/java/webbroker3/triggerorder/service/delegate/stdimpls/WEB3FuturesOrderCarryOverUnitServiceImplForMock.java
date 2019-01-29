head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.06.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOrderCarryOverUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�����J�zUnitServiceImplForMock(WEB3FuturesOrderCarryOverUnitServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/27 ���G�� (���u) �V�K�쐬
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �敨�����J�zUnitServiceImplForMock
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3FuturesOrderCarryOverUnitServiceImplForMock extends
    WEB3FuturesOrderCarryOverUnitServiceImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3OptionOrderCarryOverUnitServiceImplForMock.class);

    /**
     * (expire�J�z������)<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     */
    public void expireCarryOverOriginOrder(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireCarryOverOrder()";
        log.entering(STR_METHOD_NAME);


        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
            "expireCarryOverOriginOrder",
            new Class[] {OrderUnit.class},
            new Object[]{l_orderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
            "expireCarryOverOriginOrder",
            new Class[] {OrderUnit.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                "expireCarryOverOriginOrder",
                new Class[] {OrderUnit.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                "expireCarryOverOriginOrder",
                new Class[] {OrderUnit.class}).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.expireCarryOverOriginOrder(l_orderUnit);
    }
    
    /**
     * (create�V�K����������)<BR>
     * <BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     */
    public void createOpenContractNextOrder(OrderUnit l_orderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOpenContractNextOrder()";
        log.entering(STR_METHOD_NAME);


        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
            "createOpenContractNextOrder",
            new Class[] {OrderUnit.class, List.class},
            new Object[]{l_orderUnit, l_lisRsvIfoOrderUnits});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
            "createOpenContractNextOrder",
            new Class[] {OrderUnit.class, List.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                "createOpenContractNextOrder",
                new Class[] {OrderUnit.class, List.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                "createOpenContractNextOrder",
                new Class[] {OrderUnit.class, List.class}).asVoid();

            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.createOpenContractNextOrder(l_orderUnit, l_lisRsvIfoOrderUnits);
    }
    
    public void createSettleContractNextOrder(OrderUnit l_orderUnit, List l_lisRsvIfoOrderUnits) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                "createSettleContractNextOrder", new Class[]
                { OrderUnit.class, List.class}, new Object[]
                { l_orderUnit, l_lisRsvIfoOrderUnits});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                "createSettleContractNextOrder", new Class[]
                { OrderUnit.class, List.class}))
        {
            log
                    .debug("webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImplForMock.createSettleContractNextOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                    "createSettleContractNextOrder", new Class[]
                    { OrderUnit.class, List.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl",
                    "createSettleContractNextOrder", new Class[]
                    { OrderUnit.class, List.class}).asVoid();
            return;
        }
        super.createSettleContractNextOrder(l_orderUnit, l_lisRsvIfoOrderUnits);
    }
}
@
