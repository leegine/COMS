head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.43.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderAcceptUnitServiceImplForMork.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP������tUnitServiceImplForMork(WEB3IfoOrderAcceptUnitServiceImplForMork.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/01 ���G�� (���u) �V�K�쐬
*/
package webbroker3.ifo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �敨OP������tUnitServiceImplForMork
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3IfoOrderAcceptUnitServiceImplForMork extends WEB3IfoOrderAcceptUnitServiceImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3IfoOrderAcceptUnitServiceImplForMork.class);

    /**
     * (notify������t(Mork))<BR>
     * ������t�������s���B<BR>
     * <BR>
     * @@param l_hostFotypeOrderAcceptParams - ������t�L���[Params�I�u�W�F�N�g
     */
    public void notifyOrderAccept(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".notifyOrderAccept(HostFotypeOrderAcceptParams)-ForMork";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
            "notifyOrderAccept",
            new Class[] {HostFotypeOrderAcceptParams.class},
            new Object[]{l_hostFotypeOrderAcceptParams});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
            "notifyOrderAccept", new Class[] {HostFotypeOrderAcceptParams.class}))
        {
            log.debug("webbroker3.ifo.service.delegate.stdimpls"
                + ".WEB3IfoOrderAcceptUnitServiceImpl.notifyOrderAccept(HostFotypeOrderAcceptParams)-ForMork");

            //2�jMorkFor ??�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
                "notifyOrderAccept",
                new Class[] {HostFotypeOrderAcceptParams.class}).asWEB3BaseException();

            //3)MorkFor ??�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
                "notifyOrderAccept",
                new Class[] {HostFotypeOrderAcceptParams.class}).asVoid();

            log.exiting(STR_METHOD_NAME);
            return;
        }
        super.notifyOrderAccept(l_hostFotypeOrderAcceptParams);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify��t���ԊO)<BR>
     * ��t���ԊO�������s���B<BR>
     * <BR>
     * @@param l_hostFotypeOrderAcceptParams - ������t�L���[Params�I�u�W�F�N�g
     */
    public void notifyOrderAcceptOvertime(
        HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            ".notifyOrderAcceptOvertime(HostFotypeOrderAcceptParams)-ForMork";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
            "notifyOrderAcceptOvertime",
            new Class[] {HostFotypeOrderAcceptParams.class},
            new Object[]{l_hostFotypeOrderAcceptParams});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
            "notifyOrderAcceptOvertime", new Class[] {HostFotypeOrderAcceptParams.class}))
        {
            log.debug("webbroker3.ifo.service.delegate.stdimpls"
                + ".WEB3IfoOrderAcceptUnitServiceImpl.notifyOrderAcceptOvertime(HostFotypeOrderAcceptParams)-ForMork");

            //2�jMorkFor ??�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptOvertime",
                new Class[] {HostFotypeOrderAcceptParams.class}).asWEB3BaseException();

            //3)MorkFor ??�r asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptOvertime",
                new Class[] {HostFotypeOrderAcceptParams.class}).asVoid();

            log.exiting(STR_METHOD_NAME);
            return;
        }

        super.notifyOrderAcceptOvertime(l_hostFotypeOrderAcceptParams);
        log.exiting(STR_METHOD_NAME);

    }
}
@
