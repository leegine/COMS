head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.37.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignIndiviChangeServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ʌڋq�w��ύX�T�[�r�XImplMock(WEB3AdminAccInfoCampaignIndiviChangeServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 ���G�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �ʌڋq�w��ύX�T�[�r�XImplMock
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviChangeServiceImplForMock extends
    WEB3AdminAccInfoCampaignIndiviChangeServiceImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAccInfoCampaignIndiviChangeServiceImplForMock.class);

    /**
     * (execute(Mock))
     * �ʌڋq�w��ύX���������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImpl",
            "execute",
            new Class[] {WEB3GenRequest.class},
            new Object[]{l_request});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImpl",
            "execute",  new Class[] {WEB3GenRequest.class}))
        {
            //2�jMockFor --�r WEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}).asWEB3BaseException();

            //3)MockFor --�r asObject
            return (WEB3GenResponse)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImpl",
                "execute",
                new Class[] {WEB3GenRequest.class}).asObject();

        }

        log.exiting(STR_METHOD_NAME);
        return super.execute(l_request);
    }
}
@
