head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.30.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginTransferServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3MarginTransferServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 金傑（中訊）新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginTransferServiceImplForMock extends WEB3MarginTransferServiceImpl
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginTransferServiceImplForMock.class);
    
    public void submitMarginTransfer(
            WEB3GentradeMainAccount l_mainAccount,
            Date l_datDeliveryDate,
            double l_dblCashinAmt,
            String l_strPassword,
            Trader l_trader)throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class}, new Object[]
                { l_mainAccount, l_datDeliveryDate, new Double(l_dblCashinAmt), l_strPassword, l_trader});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                new Class[]
                { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class}))
        {
            log.debug("webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImplForMock.submitMarginTransfer()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl", "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class}).asVoid();
            return;
        }
        super.submitMarginTransfer(l_mainAccount, l_datDeliveryDate, l_dblCashinAmt, l_strPassword, l_trader);
    }
}
@
