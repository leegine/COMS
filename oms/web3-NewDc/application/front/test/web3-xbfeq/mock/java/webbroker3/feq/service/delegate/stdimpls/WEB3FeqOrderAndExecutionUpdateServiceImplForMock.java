head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqOrderAndExecutionUpdateServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;

public class WEB3FeqOrderAndExecutionUpdateServiceImplForMock extends WEB3FeqOrderAndExecutionUpdateServiceImpl
{

    /**
     * (update�����) <BR>
     * �o���^���X�V�������s�� <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�����X�V�jupdate�����v�Q�ƁB <BR>
     * @@param l_hostFeqOrderExecNotifyParams - (�O���o���ʒm�L���[�s) <BR>
     * �O���o���ʒm�L���[�s�I�u�W�F�N�g <BR>
     *  <BR>
     * ���O���o���ʒm�L���[Params��DDL��莩����������B <BR>
     * @@throws WEB3BaseException
     * @@roseuid 428C039102C5
     */
    public void updateExecuteUnit(HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams) 
        throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAndExecutionUpdateServiceImpl",
            "updateExecuteUnit",
            new Class[]{HostFeqOrderExecNotifyParams.class},
            new Object[]{l_hostFeqOrderExecNotifyParams});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAndExecutionUpdateServiceImpl",
            "updateExecuteUnit",
            new Class[] {HostFeqOrderExecNotifyParams.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAndExecutionUpdateServiceImpl",
                "updateExecuteUnit",
                new Class[] {HostFeqOrderExecNotifyParams.class}).asWEB3BaseException();
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAndExecutionUpdateServiceImpl",
                "updateExecuteUnit",
                new Class[] {HostFeqOrderExecNotifyParams.class}).asVoid();
            return;
        }
        super.updateExecuteUnit(l_hostFeqOrderExecNotifyParams);
    }
}
@
