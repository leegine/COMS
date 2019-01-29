head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.40.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccReservationEqTypeOrderUpdateServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3ToSuccReservationEqTypeOrderUpdateServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/08 ���� (���u) �V�K�쐬
*/
package webbroker3.triggerorder.base.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �����\�񒍕��X�V�T�[�r�XImplForMock
 * 
 * @@author ���� (���u)
 * @@version 1.0
 */
public class WEB3ToSuccReservationEqTypeOrderUpdateServiceImplForMock extends
        WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToSuccReservationEqTypeOrderUpdateServiceImplForMock.class);
   
    public void invalidateOrderUnit(RsvEqOrderUnitRow l_rsvEqOrderUnitRow, String l_strErrorCode)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl",
                "invalidateOrderUnit",
                new Class[] {RsvEqOrderUnitRow.class,String.class},
                new Object[]{l_rsvEqOrderUnitRow,l_strErrorCode});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl",
                "invalidateOrderUnit",
                new Class[] {RsvEqOrderUnitRow.class,String.class}))
        {
            log.debug("webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationEqTypeOrderUpdateServiceImplForMock.invalidateOrderUnit");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl",
                    "invalidateOrderUnit",
                    new Class[] {RsvEqOrderUnitRow.class,String.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl",
                    "invalidateOrderUnit",
                    new Class[] {RsvEqOrderUnitRow.class,String.class}).asVoid();
            return;
            
        }
        super.invalidateOrderUnit(l_rsvEqOrderUnitRow,l_strErrorCode);
    }
}
@
