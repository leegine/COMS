head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiServiceDetailServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminSrvRegiServiceDetailServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/13 金傑 (中訊) 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceDetailsRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceDetailsResponse;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiServiceDetailServiceImplTest extends TestBaseForMock
{
    private WEB3AdminSrvRegiServiceDetailServiceImpl l_serviceImpl = null;
    
    private WEB3AdminSrvRegiServiceDetailsRequest l_srvDetailsRequest = null;
    
    private WEB3AdminSrvRegiServiceDetailsResponse l_srvDetailsResponse = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AdminSrvRegiServiceDetailServiceImplTest.class);
    
    public WEB3AdminSrvRegiServiceDetailServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3AdminSrvRegiServiceDetailServiceImpl();
        this.l_srvDetailsRequest = new WEB3AdminSrvRegiServiceDetailsRequestForTest();
        this.initData();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        this.l_srvDetailsRequest = null;
        this.l_srvDetailsResponse = null;
        super.checkMethodValue();
        super.tearDown();
    }
    
    /**
     * 無料対象期間 = 2
     *
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setInstitutionCode("1D");
            l_srvRegiSetupParams.setSupplyDiv("1");
            l_srvRegiSetupParams.setTrialPeriod(2);
            l_srvRegiSetupParams.setAppliDateFrom(2);
            l_srvRegiSetupParams.setAppliDateTo(3);
            l_srvRegiSetupParams.setSendMailInterval(10);
            l_srvRegiSetupParams.setFreeCoverageLength(2);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
            
            this.l_srvDetailsRequest.serviceDiv = "1234";
            this.l_srvDetailsResponse = (WEB3AdminSrvRegiServiceDetailsResponse) 
                this.l_serviceImpl.execute(this.l_srvDetailsRequest);
            
            
            
            
            assertNotNull(this.l_srvDetailsResponse);
            assertEquals("2",this.l_srvDetailsResponse.freeTargetPeriod);
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail(); 
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * 無料対象期間 = null
     *
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setInstitutionCode("1D");
            l_srvRegiSetupParams.setSupplyDiv("1");
            l_srvRegiSetupParams.setLotDiv("1");
            l_srvRegiSetupParams.setTrialPeriod(2);
            l_srvRegiSetupParams.setAppliDateFrom(2);
            l_srvRegiSetupParams.setAppliDateTo(3);
            l_srvRegiSetupParams.setSendMailInterval(10);
            l_srvRegiSetupParams.setFreeCoverageLength(2);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
            
            this.l_srvDetailsRequest.serviceDiv = "1234";
            this.l_srvDetailsResponse = (WEB3AdminSrvRegiServiceDetailsResponse) 
                this.l_serviceImpl.execute(this.l_srvDetailsRequest);
            
            assertNotNull(this.l_srvDetailsResponse);
            assertNull(this.l_srvDetailsResponse.freeTargetPeriod);
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail(); 
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("1D");
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator,"C0601",true,true);
            WEB3AdministratorForMock.mockIsDirAdministrator(l_expectAdministrator,false);
            
            TestDBUtility.deleteAll(SrvRegiMasterRow.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();       
            l_srvRegiMasterParams.setInstitutionCode("1D");
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setOfferingDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private class WEB3AdminSrvRegiServiceDetailsRequestForTest extends WEB3AdminSrvRegiServiceDetailsRequest
    {
        public void validate() throws WEB3BaseException 
        {
            log.debug("WEB3AdminSrvRegiServiceDetailsRequestForTest.validate()");
        }
    }

}
@
