head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLCashOutStopReleaseServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3AdminAioSLCashOutStopReleaseServiceImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/09/20 �����i���u�j�V�K�쐬
 */
package webbroker3.aio.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLCashOutStopReleaseServiceImplForMock extends WEB3AdminAioSLCashOutStopReleaseServiceImpl
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AdminAioSLCashOutStopReleaseServiceImplForMock.class);

    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl", "execute",
                new Class[]
                { WEB3GenRequest.class }, new Object[]
                { l_request });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl", "execute",
                new Class[]
                { WEB3GenRequest.class }))
        {
            log
                    .debug("webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImplForMock.execute()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class }).asWEB3BaseException();
            return (WEB3GenResponse) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class }).asObject();
        }
        return super.execute(l_request);
    }
}
@
