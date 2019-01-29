head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformHostReqOrderNumberManageServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3InformHostReqOrderNumberManageServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/08 金傑 (中訊) 新規作成
*/
package webbroker3.inform.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformHostReqOrderNumberManageServiceImplForMock extends WEB3InformHostReqOrderNumberManageServiceImpl
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3InformHostReqOrderNumberManageServiceImplForMock.class);
     
     public String getNewOrderRequestCode(
             String l_strInstitutionCode,
             String l_strInformDiv
             )throws WEB3BaseException 
             {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
                "getNewOrderRequestCode", new Class[]
                { String.class, String.class }, new Object[]
                { l_strInstitutionCode, l_strInformDiv });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
                "getNewOrderRequestCode", new Class[]
                { String.class, String.class }))
        {
            log
                    .debug("webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImplForMock.getNewOrderRequestCode()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
                    "getNewOrderRequestCode", new Class[]
                    { String.class, String.class }).asWEB3BaseException();
            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
                    "getNewOrderRequestCode", new Class[]
                    { String.class, String.class }).asObject();
        }
        return super.getNewOrderRequestCode(l_strInstitutionCode, l_strInformDiv);
    }
}
@
