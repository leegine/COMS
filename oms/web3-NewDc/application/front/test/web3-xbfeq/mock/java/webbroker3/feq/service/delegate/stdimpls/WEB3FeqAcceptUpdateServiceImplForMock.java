head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqAcceptUpdateServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqAcceptUpdateServiceImplForMock extends WEB3FeqAcceptUpdateServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3FeqAcceptUpdateServiceImplForMock.class);

    /**
     * (update受付) <BR>
     * 注文受付更新処理を行う。
     * @@param l_marketResponseMessage - (市場レスポンスメッセージ)
     * @@throws WEB3BaseException
     * @@roseuid 42A579180083
     */
    public void updateAccept(MarketResponseMessage l_marketResponseMessage) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAccept(MarketResponseMessage)";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.feq.service.delegate.stdimpls.WEB3FeqAcceptUpdateServiceImpl",
                "updateAccept",
                new Class[]{MarketResponseMessage.class},
                new Object[]{l_marketResponseMessage});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.feq.service.delegate.stdimpls.WEB3FeqAcceptUpdateServiceImpl",
            "updateAccept",
            new Class[]{MarketResponseMessage.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.service.delegate.stdimpls.WEB3FeqAcceptUpdateServiceImpl",
                "updateAccept",
                new Class[]{MarketResponseMessage.class}).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.feq.service.delegate.stdimpls.WEB3FeqAcceptUpdateServiceImpl",
                "updateAccept",
                new Class[]{MarketResponseMessage.class}).asVoid();
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.updateAccept(l_marketResponseMessage);
    }
}
@
