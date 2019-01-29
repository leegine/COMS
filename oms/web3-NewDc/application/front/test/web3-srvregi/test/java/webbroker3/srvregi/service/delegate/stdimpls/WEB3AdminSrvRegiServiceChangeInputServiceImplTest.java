head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiServiceChangeInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminSrvRegiServiceChangeInputServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/12 金傑 (中訊) 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.util.Calendar;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.MailInfoParams;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeInputResponse;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiServiceChangeInputServiceImplTest extends TestBaseForMock
{
    
    private WEB3AdminSrvRegiServiceChangeInputRequest l_request = null;
    
    private WEB3AdminSrvRegiServiceChangeInputServiceImpl l_service = null;
    
    private WEB3AdminSrvRegiServiceChangeInputResponse l_response = null;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AdminSrvRegiServiceChangeInputServiceImplTest.class);

    public WEB3AdminSrvRegiServiceChangeInputServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_request = new WEB3AdminSrvRegiServiceChangeInputRequestForTest();
        this.l_service = new WEB3AdminSrvRegiServiceChangeInputServiceImpl();
        this.initData();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_request = null;
        this.l_service = null;
        this.l_response = null;
        super.checkMethodValue();
        super.tearDown();
    }
    
    /**
     * 無料対象期間 == null
     *
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("1D");
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator,"C0601",true,true);
            WEB3AdministratorForMock.mockIsDirAdministrator(l_expectAdministrator,true);
            
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setInstitutionCode("1D");
            l_srvRegiSetupParams.setLotDiv("1");
            l_srvRegiSetupParams.setSupplyDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
            
            this.l_request.serviceDiv = "1234";
            this.l_response = (WEB3AdminSrvRegiServiceChangeInputResponse) this.l_service.execute(this.l_request);
            assertNotNull(this.l_response);
            assertNull(this.l_response.freeTargetPeriod);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * 無料対象期間 == 2
     *
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("1D");
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator,"C0601",true,true);
            WEB3AdministratorForMock.mockIsDirAdministrator(l_expectAdministrator,true);
            
            
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setInstitutionCode("1D");
            l_srvRegiSetupParams.setSupplyDiv("1");
            l_srvRegiSetupParams.setFreeCoverageLength(2);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
            
            this.l_request.serviceDiv = "1234";
            this.l_response = (WEB3AdminSrvRegiServiceChangeInputResponse) this.l_service.execute(this.l_request);
            assertNotNull(this.l_response);
            assertEquals("2",this.l_response.freeTargetPeriod);
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
            TestDBUtility.deleteAll(SrvRegiMasterRow.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();       
            l_srvRegiMasterParams.setInstitutionCode("1D");
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setOfferingDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
            

            
            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams1 = new MailInfoParams();
            l_mailInfoParams1.setInstitutionCode("1D");
            l_mailInfoParams1.setSendmailDiv("0101");
            l_mailInfoParams1.setDiscernmentId("1234");
            l_mailInfoParams1.setSubject("あいうえおabced1258");
            l_mailInfoParams1.setLastUpdater("0213");
            l_mailInfoParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mailInfoParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_mailInfoParams1);
            
            MailInfoParams l_mailInfoParams2 = new MailInfoParams();
            l_mailInfoParams2.setInstitutionCode("1D");
            l_mailInfoParams2.setSendmailDiv("0102");
            l_mailInfoParams2.setDiscernmentId("1234");
            l_mailInfoParams2.setSubject("あいうえおabced1258");
            l_mailInfoParams2.setLastUpdater("0213");
            l_mailInfoParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mailInfoParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_mailInfoParams2);

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private class WEB3AdminSrvRegiServiceChangeInputRequestForTest extends WEB3AdminSrvRegiServiceChangeInputRequest
    {
        public void validate() throws WEB3BaseException 
        {
            log.debug("WEB3AdminSrvRegiServiceChangeInputRequestForTest.validate()");
        }
    }
}
@
