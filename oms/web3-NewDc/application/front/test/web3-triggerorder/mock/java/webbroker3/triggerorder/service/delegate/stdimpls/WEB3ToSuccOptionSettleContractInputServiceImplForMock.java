head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.04.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionSettleContractInputServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �iWEB3ToSuccOptionSettleContractInputServiceImplForMock.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/21 �g�E�N�| (���u) �V�K�쐬
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionSettleContractInputServiceImplForMock extends
    WEB3ToSuccOptionSettleContractInputServiceImpl
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractInputServiceImplForMock.class);
    
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class}, new Object[]
                {l_request});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImpl",
                "execute", new Class[]
                {WEB3GenRequest.class}))
        {
            log.debug("webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImplForMock.execute()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImpl",
                    "execute", new Class[]
                    {WEB3GenRequest.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImpl",
                    "execute", new Class[]
                    {WEB3GenRequest.class}).asWEB3BaseRuntimeException();
            return (WEB3GenResponse) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImpl",
                    "execute", new Class[]
                    {WEB3GenRequest.class}).asObject();
        }
        return super.execute(l_request);
    }
}
@