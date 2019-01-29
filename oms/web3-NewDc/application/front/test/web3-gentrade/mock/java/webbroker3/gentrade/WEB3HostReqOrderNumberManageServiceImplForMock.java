head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.17.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3HostReqOrderNumberManageServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3HostReqOrderNumberManageServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/08 金傑 (中訊) 新規作成
*/
package webbroker3.gentrade;


import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3HostReqOrderNumberManageServiceImplForMock extends WEB3HostReqOrderNumberManageServiceImpl
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3HostReqOrderNumberManageServiceImplForMock.class);
     
     public String getNewNumber(
             String l_strInstitutionCode,
             String l_strBranchCode,
             ProductTypeEnum l_productType
             )throws WEB3BaseException 
             {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class }, new Object[]
                { l_strInstitutionCode, l_strBranchCode, l_productType });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class }))
        {
            log.debug("webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImplForMock.getNewNumber()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                    { String.class, String.class, ProductTypeEnum.class }).asWEB3BaseException();
            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                    { String.class, String.class, ProductTypeEnum.class }).asObject();
        }
        return super.getNewNumber(l_strInstitutionCode, l_strBranchCode, l_productType);
    }
     
}
@
