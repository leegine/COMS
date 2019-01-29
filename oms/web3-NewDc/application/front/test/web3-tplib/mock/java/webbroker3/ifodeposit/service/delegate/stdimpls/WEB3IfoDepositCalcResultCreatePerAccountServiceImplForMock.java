head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositCalcResultCreatePerAccountServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3IfoDepositCalcResultCreatePerAccountServiceImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/21 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.ifodeposit.service.delegate.stdimpls;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifodeposit.data.IfoDepositCalcResultParams;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultCreatePerAccountServiceImpl;
import webbroker3.mock.TestBaseForMock;

public class WEB3IfoDepositCalcResultCreatePerAccountServiceImplForMock extends
        WEB3IfoDepositCalcResultCreatePerAccountServiceImpl
{

    public WEB3IfoDepositCalcResultCreatePerAccountServiceImplForMock()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public IfoDepositCalcResultParams createIfoDepositCalcResult(WEB3GentradeSubAccount l_subAccount) throws WEB3SystemLayerException
    {

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultCreatePerAccountServiceImpl",
                "createIfoDepositCalcResult", new Class[]
                { WEB3GentradeSubAccount.class }, new Object[]
                { l_subAccount });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultCreatePerAccountServiceImpl",
                "createIfoDepositCalcResult", new Class[]
                { WEB3GentradeSubAccount.class }))
            {
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultCreatePerAccountServiceImpl",
                        "createIfoDepositCalcResult", new Class[]
                       { WEB3GentradeSubAccount.class }).asWEB3SystemLayerException();

                return (IfoDepositCalcResultParams)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultCreatePerAccountServiceImpl",
                        "createIfoDepositCalcResult", new Class[]
                       { WEB3GentradeSubAccount.class }).asObject();
            }
        return super.createIfoDepositCalcResult(l_subAccount);
    }
}
@
