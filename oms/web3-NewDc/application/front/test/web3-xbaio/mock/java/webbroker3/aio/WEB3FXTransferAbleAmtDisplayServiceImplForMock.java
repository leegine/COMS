head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.41.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransferAbleAmtDisplayServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3FXTransferAbleAmtDisplayServiceImplForMock.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/07/02 武波 (中訊) 新規作成
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTransferAbleAmtUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;

public class WEB3FXTransferAbleAmtDisplayServiceImplForMock extends WEB3FXTransferAbleAmtDisplayServiceImpl
{
    public WEB3FXTransferAbleAmtUnit getFXTransferAbleAmtCheck(
        SubAccount l_subAccount, CompFxConditionParams l_compFxConditionParams,
        String l_strTransferAmount, String l_strCourseDiv) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3FXTransferAbleAmtDisplayServiceImpl",
                "getFXTransferAbleAmtCheck", new Class[]
                { SubAccount.class,  CompFxConditionParams.class, String.class, String.class},
                new Object[]
                { l_subAccount, l_compFxConditionParams, l_strTransferAmount, l_strCourseDiv });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3FXTransferAbleAmtDisplayServiceImpl",
                "getFXTransferAbleAmtCheck", new Class[]
               { SubAccount.class,  CompFxConditionParams.class, String.class, String.class}))
        {
            return (WEB3FXTransferAbleAmtUnit)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXTransferAbleAmtDisplayServiceImpl",
                    "getFXTransferAbleAmtCheck", new Class[]
                   { SubAccount.class,  CompFxConditionParams.class, String.class, String.class}).asObject();
        }
        return super.getFXTransferAbleAmtCheck(
            l_subAccount, l_compFxConditionParams, l_strTransferAmount, l_strCourseDiv);
    }

}
@
