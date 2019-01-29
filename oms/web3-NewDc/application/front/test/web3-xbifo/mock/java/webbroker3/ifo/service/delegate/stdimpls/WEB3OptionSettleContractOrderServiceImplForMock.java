head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.44.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionSettleContractOrderServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : （WEB3OptionSettleContractOrderServiceImplForMock.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/04/25 トウ鋒鋼 (中訊) 新規作成
 */
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoSettleContractOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderRequestAdapter;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionSettleContractOrderServiceImplForMock extends WEB3OptionSettleContractOrderServiceImpl
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionSettleContractOrderServiceImplForMock.class);
    
    public NewOrderValidationResult validateOptionsSettleContractOrder(SubAccount l_subAccount,
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                        "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractOrderServiceImpl",
                        "validateOptionsSettleContractOrder", new Class[]
                        {SubAccount.class, WEB3IfoSettleContractOrderSpec.class,
                                WEB3OptionSettleContractOrderRequestAdapter.class}, new Object[]
                        {l_subAccount, l_settleContractOrderSpec, l_requestAdapter});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractOrderServiceImpl",
                        "validateOptionsSettleContractOrder", new Class[]
                        {SubAccount.class, WEB3IfoSettleContractOrderSpec.class,
                                WEB3OptionSettleContractOrderRequestAdapter.class}))
        {
            log.debug("webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractOrderServiceImplForMock.validateOptionsSettleContractOrder()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractOrderServiceImpl",
                    "validateOptionsSettleContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3IfoSettleContractOrderSpec.class,
                            WEB3OptionSettleContractOrderRequestAdapter.class}).asWEB3BaseException();
            return (NewOrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractOrderServiceImpl",
                    "validateOptionsSettleContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3IfoSettleContractOrderSpec.class,
                            WEB3OptionSettleContractOrderRequestAdapter.class}).asObject();
        }
        return super.validateOptionsSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_requestAdapter);
    }
}
@
