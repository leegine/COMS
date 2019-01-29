head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.40.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccReservationIfoOrderUpdateServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccReservationIfoOrderUpdateServiceImplForMock extends WEB3ToSuccReservationIfoOrderUpdateServiceImpl
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToSuccReservationIfoOrderUpdateServiceImplForMock.class);

    public boolean expireAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "expireAllOrderUnit", new Class[]
                {long.class}, new Object[]
                {new Long(l_lngParentOrderId)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "expireAllOrderUnit", new Class[]
                {long.class}))
        {
            log.debug("webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl.expireAllOrderUnit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "expireAllOrderUnit", new Class[]
                {long.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "expireAllOrderUnit", new Class[]
                {long.class}).asWEB3BaseRuntimeException();
            return (boolean) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "expireAllOrderUnit", new Class[]
                {long.class}).asBoolean();
        }
        return super.expireAllOrderUnit(l_lngParentOrderId);
    }
    public void invalidateOrderUnit(
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow,
            String l_strOrderErrorCode) throws WEB3BaseException {
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "invalidateOrderUnit", new Class[]
                {RsvIfoOrderUnitRow.class,String.class}, new Object[]
                {l_rsvIfoOrderUnitRow,l_strOrderErrorCode});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "invalidateOrderUnit", new Class[]
                {RsvIfoOrderUnitRow.class,String.class}))
        {
            log.debug("webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl.expireAllOrderUnit()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "invalidateOrderUnit", new Class[]
                {RsvIfoOrderUnitRow.class,String.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "invalidateOrderUnit", new Class[]
                {RsvIfoOrderUnitRow.class,String.class}).asWEB3BaseRuntimeException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "invalidateOrderUnit", new Class[]
                {RsvIfoOrderUnitRow.class,String.class}).asVoid();
            return;
        }
        super.invalidateOrderUnit(l_rsvIfoOrderUnitRow, l_strOrderErrorCode);
	}
    public void insertReserveOrderAction(long l_lngOrderId) throws WEB3BaseException
    {
    	TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "insertReserveOrderAction", new Class[]
                {long.class}, new Object[]
                {new Long(l_lngOrderId)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "insertReserveOrderAction", new Class[]
                {long.class}))
        {
            log.debug("webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl.insertReserveOrderAction()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                "insertReserveOrderAction", new Class[]
                {long.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl",
                    "insertReserveOrderAction", new Class[]
                    {long.class}).asVoid();
            return;
        }
        super.insertReserveOrderAction(l_lngOrderId);
    }
}
@
