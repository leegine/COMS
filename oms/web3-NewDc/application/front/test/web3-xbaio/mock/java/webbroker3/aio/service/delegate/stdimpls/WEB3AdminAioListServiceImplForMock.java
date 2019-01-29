head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioListServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғ��o���ꗗ�T�[�r�XImplForMock(WEB3AdminAioListServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �Ǘ��ғ��o���ꗗ�T�[�r�XImplForMock
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioListServiceImplForMock extends WEB3AdminAioListServiceImpl
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioListServiceImplForMock.class);

    /**
     * (Mock)<BR>
     * �Ǘ��ғ��o���ꗗ�������s���B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImpl",
            "execute",
            new Class[] {WEB3GenRequest.class},
            new Object[]{l_request});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImpl",
            "execute",  new Class[] {WEB3GenRequest.class}))
        {
            //2�jMockFor --�r WEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}).asWEB3BaseException();

            //3)MockFor --�r asVoid
            return (WEB3GenResponse)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.execute(l_request);
    }
}
@
