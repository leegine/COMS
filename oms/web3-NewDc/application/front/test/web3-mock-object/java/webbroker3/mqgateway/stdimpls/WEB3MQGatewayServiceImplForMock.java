head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MQGatewayServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3MQGatewayServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/05 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mqgateway.stdimpls;

import webbroker3.mock.TestBaseForMock;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.util.WEB3LogUtility;

/**
 * MQGatewayService�N���X//TODO
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3MQGatewayServiceImplForMock extends WEB3MQGatewayServiceImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3MQGatewayServiceImplForMock.class);

    public WEB3MQSendResult send(WEB3MQMessageSpec l_messageSpec)
    {
        final String STR_METHOD_NAME = "isMarketNotifyingOrderInBreakTimeZone(IfoOrderUnit)-ForMock";
        log.entering(STR_METHOD_NAME);

        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send",
            new Class[] {WEB3MQMessageSpec.class},
            new Object[]{l_messageSpec});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send",
            new Class[] {WEB3MQMessageSpec.class}))
        {
            //2)MockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (WEB3MQSendResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
                "send",
                new Class[] {WEB3MQMessageSpec.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.send(l_messageSpec);
    }
}
@
