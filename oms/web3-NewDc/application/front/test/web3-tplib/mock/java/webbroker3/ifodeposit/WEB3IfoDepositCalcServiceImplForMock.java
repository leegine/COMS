head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.58.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositCalcServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoDepositCalcServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 金傑（中訊）新規作成
*/
package webbroker3.ifodeposit;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositCalcServiceImplForMock extends WEB3IfoDepositCalcServiceImpl
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3IfoDepositCalcServiceImplForMock.class);
    
    public WEB3IfoDepositCalc getIfoDepositCalc(WEB3GentradeSubAccount l_subAccount) throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                "getIfoDepositCalc", new Class[]
                { WEB3GentradeSubAccount.class }, new Object[]
                { l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                "getIfoDepositCalc", new Class[]
                { WEB3GentradeSubAccount.class }))
        {
            log.debug("webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImplForMock.getIfoDepositCalc()");
            return (WEB3IfoDepositCalc) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl", "getIfoDepositCalc", new Class[]
                    { WEB3GentradeSubAccount.class }).asObject();
        }
        return super.getIfoDepositCalc(l_subAccount);
    }
}
@
