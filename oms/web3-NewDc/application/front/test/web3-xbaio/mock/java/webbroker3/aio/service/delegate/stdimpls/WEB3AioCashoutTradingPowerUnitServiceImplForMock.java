head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioCashoutTradingPowerUnitServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���]�̓`�F�b�NUnitServiceImplForMock(WEB3AioCashoutTradingPowerUnitServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/03/09 ꎉ� (���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate.stdimpls;

import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �o���]�̓`�F�b�NUnitServiceImplForMock
 *
 * @@author ꎉ�(���u)
 * @@version 1.0
 */
public class WEB3AioCashoutTradingPowerUnitServiceImplForMock extends
    WEB3AioCashoutTradingPowerUnitServiceImpl
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutTradingPowerUnitServiceImplForMock.class);
    
    /**
     * (Mock)<BR>
     * �����̗]�̓`�F�b�N�������s���B<BR>
     * @@param l_hostPaymentOrderParams - (�o�����������L���[�̍s�I�u�W�F�N�g)
     * @@param l_strProcessFlag - (�����t���O)
     * @@param l_blnTriggerIssueFlag - (�g���K���s�t���O)
     * @@param l_strDbCurrentPriceCheckDiv - (DB�����]�̓`�F�b�N�敪)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public void execute(HostPaymentOrderParams l_hostPaymentOrderParams,
        String l_strProcessFlag, boolean l_blnTriggerIssueFlag, String l_strDbCurrentPriceCheckDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(HostPaymentOrderParams, String, boolean, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerUnitServiceImpl",
            "execute",
            new Class[] {HostPaymentOrderParams.class, String.class, boolean.class, String.class},
            new Object[]{l_hostPaymentOrderParams,
                l_strProcessFlag, 
                new Boolean(l_blnTriggerIssueFlag), 
                l_strDbCurrentPriceCheckDiv});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerUnitServiceImpl",
            "execute",  
            new Class[] {
                HostPaymentOrderParams.class, 
                String.class, 
                boolean.class, 
                String.class}))
        {
            //2�jMockFor --�r WEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerUnitServiceImpl",
                "execute",
                new Class[] {
                    HostPaymentOrderParams.class, 
                    String.class, 
                    boolean.class, 
                    String.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerUnitServiceImpl",
                "execute",
                new Class[] {
                    HostPaymentOrderParams.class,
                    String.class, 
                    boolean.class, 
                    String.class}).asVoid();
            return;

        }

        log.exiting(STR_METHOD_NAME);
        super.execute(
            l_hostPaymentOrderParams,
            l_strProcessFlag, 
            l_blnTriggerIssueFlag, 
            l_strDbCurrentPriceCheckDiv);
    }
}
@
