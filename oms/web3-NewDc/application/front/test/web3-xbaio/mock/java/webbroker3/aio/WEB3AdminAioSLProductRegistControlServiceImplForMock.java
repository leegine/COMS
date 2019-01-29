head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.41.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLProductRegistControlServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminAioSLProductRegistControlServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/27 金傑（中訊）新規作成
*/
package webbroker3.aio;

import java.util.Date;
import java.util.List;

import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLProductRegistControlServiceImplForMock extends WEB3AdminAioSLProductRegistControlServiceImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductRegistControlServiceImplForMock.class);
    
    public int compareChangeInfo(SecurityProductRow l_changeBeforeSecurityProductInfo,
            WEB3SLProductInfoUnit l_changeAfterSecurityProductInfo) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "compareChangeInfo", new Class[]
                { SecurityProductRow.class, WEB3SLProductInfoUnit.class }, new Object[]
                { l_changeBeforeSecurityProductInfo, l_changeAfterSecurityProductInfo });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl",
                "compareChangeInfo", new Class[]
                { SecurityProductRow.class, WEB3SLProductInfoUnit.class }))
        {
            log.debug("webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImplForMock.compareChangeInfo()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "compareChangeInfo", new Class[]
                    { SecurityProductRow.class, WEB3SLProductInfoUnit.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "compareChangeInfo", new Class[]
                    { SecurityProductRow.class, WEB3SLProductInfoUnit.class }).asInt();
        }
        return super.compareChangeInfo(l_changeBeforeSecurityProductInfo, l_changeAfterSecurityProductInfo);
    }
    
    public List getSecurityProductInfo(long l_lngProductId) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "getSecurityProductInfo", new Class[]
                { long.class }, new Object[]
                { new Long(l_lngProductId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl",
                "getSecurityProductInfo", new Class[]
                { long.class }))
        {
            log.debug("webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImplForMock.getSecurityProductInfo()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "getSecurityProductInfo",
                    new Class[]
                    { long.class }).asWEB3BaseException();
            return (List) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "getSecurityProductInfo",
                    new Class[]
                    { long.class }).asObject();
        }
        return super.getSecurityProductInfo(l_lngProductId);
    }
    
    public void validateSecurityProductSameTerm(List l_lisSecurityProductInfos, Date l_datTargetPeriodFrom,
            Date l_datTargetPeriodTo) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "validateSecurityProductSameTerm",
                new Class[]
                { List.class, Date.class, Date.class }, new Object[]
                { l_lisSecurityProductInfos, l_datTargetPeriodFrom, l_datTargetPeriodTo });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl",
                "validateSecurityProductSameTerm", new Class[]
                { List.class, Date.class, Date.class }))
        {
            log
                    .debug("webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImplForMock.validateSecurityProductSameTerm()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "validateSecurityProductSameTerm",
                    new Class[]
                    { List.class, Date.class, Date.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "validateSecurityProductSameTerm",
                    new Class[]
                    { List.class, Date.class, Date.class }).asVoid();
            return;
        }
        super.validateSecurityProductSameTerm(l_lisSecurityProductInfos, l_datTargetPeriodFrom, l_datTargetPeriodTo);
    }
}
@
