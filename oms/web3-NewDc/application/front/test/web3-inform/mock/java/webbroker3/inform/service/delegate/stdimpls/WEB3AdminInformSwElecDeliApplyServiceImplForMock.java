head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformSwElecDeliApplyServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminInformSwElecDeliApplyServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/23 �����i���u�j�V�K�쐬
*/
package webbroker3.inform.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformSwElecDeliApplyServiceImplForMock extends WEB3AdminInformSwElecDeliApplyServiceImpl
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminInformSwElecDeliApplyServiceImplForMock.class);
     
     public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", "execute",
                new Class[]
                { WEB3GenRequest.class }, new Object[]
                { l_request });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", "execute",
                new Class[]
                { WEB3GenRequest.class }))
        {
            log
                    .debug("webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImplForMock.execute()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class }).asWEB3BaseException();
            return (WEB3GenResponse) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class }).asObject();
        }
        return super.execute(l_request);
    }
}
@