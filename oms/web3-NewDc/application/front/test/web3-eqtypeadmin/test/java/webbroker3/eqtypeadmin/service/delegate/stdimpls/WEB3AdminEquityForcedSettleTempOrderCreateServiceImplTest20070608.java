head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.09.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest20070608.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;

import test.util.TestDBUtility;

import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateRequest;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest20070608 extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest20070608.class);

    WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl l_serviceImpl;

    public WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest20070608(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_serviceImpl = new WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testExecute_T01()
    {
        final String STR_METHOD_NAME = "testExecute_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request = 
                new WEB3AdminEquityForcedSettleTempOrderCreateRequest();
            
            l_request.institutionCode = "10";
            l_request.forcedSettleType = "1";
            l_request.rangeFrom = 0L;
            l_request.rangeTo = 333812512203L;
            
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            l_insParams.setForcedsettleorderDiv("3");
            TestDBUtility.insertWithDel(l_insParams);
            
            //EnableOrderConditionParams
            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableParams.setInstitutionCode("10");
            l_enableParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableParams.setFutureOptionDiv("0");
            l_enableParams.setMarginTradingDiv("0");
            l_enableParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableParams);
            
            //EqtypeContractParams
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setAccountId(333812512203L);
            l_eqtypeContractParams.setQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            WEB3BackResponse l_response = l_serviceImpl.execute(l_request);
            log.debug(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
