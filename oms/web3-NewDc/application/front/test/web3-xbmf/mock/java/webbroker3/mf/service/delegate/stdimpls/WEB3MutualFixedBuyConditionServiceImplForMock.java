head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.05.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedBuyConditionServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3MutualFixedBuyConditionServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/17 ���z(���u) �V�K�쐬
*/
package webbroker3.mf.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFixedBuyConditionServiceImplForMock extends WEB3MutualFixedBuyConditionServiceImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionServiceImplForMock.class);

    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl", "execute",
                new Class[]
                {WEB3GenRequest.class}, new Object[]
                {l_request});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl", "execute",
                new Class[]
                {WEB3GenRequest.class}))
        {
            log.debug("webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImplForMock.execute()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl", "execute",
                    new Class[]
                    {WEB3GenRequest.class}).asWEB3BaseException();
            
            log.debug("webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImplForMock.execute()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl", "execute",
                    new Class[]
                    {WEB3GenRequest.class}).asWEB3BaseRuntimeException();
            
            return (WEB3GenResponse) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl", "execute",
                    new Class[]
                    {WEB3GenRequest.class}).asObject();
        }
        return super.execute(l_request);
    }
}
@
