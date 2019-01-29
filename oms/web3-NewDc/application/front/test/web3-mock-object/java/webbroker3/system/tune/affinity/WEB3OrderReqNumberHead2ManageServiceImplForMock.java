head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OrderReqNumberHead2ManageServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.tune.affinity;

import webbroker3.mock.TestBaseForMock;

public class WEB3OrderReqNumberHead2ManageServiceImplForMock extends
        WEB3OrderReqNumberHead2ManageServiceImpl
{

    public WEB3OrderReqNumberHead2ManageServiceImplForMock()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getOrderReqNumberHead2()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.system.tune.affinity.WEB3OrderReqNumberHead2ManageServiceImpl",
                "getOrderReqNumberHead2",
                new Class[] {},
                null);
        
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.system.tune.affinity.WEB3OrderReqNumberHead2ManageServiceImpl",
                "getOrderReqNumberHead2",
                new Class[] {}))
        {
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.system.tune.affinity.WEB3OrderReqNumberHead2ManageServiceImpl",
                    "getOrderReqNumberHead2",
                    new Class[] {}).asObject();
        }
        return super.getOrderReqNumberHead2();
    }
}
@
