head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.43.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiApplicationRequiredServiceTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3SrvRegiApplicationRequiredServiceTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/13 金傑 (中訊) 新規作成
*/
package webbroker3.srvregi;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiApplicationRequiredServiceTest extends TestBaseForMock
{

    private WEB3SrvRegiApplicationRequiredService l_service = null;
    
    private String l_strFreeTargetPeriod = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3SrvRegiApplicationRequiredServiceTest.class);
    
    public WEB3SrvRegiApplicationRequiredServiceTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
  
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();   
    }

    /**
     * get無料対象期間 = null
     *
     */
    public void testGetFreeTargetPeriod_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_service = new WEB3SrvRegiApplicationRequiredService(TestDBUtility.getSrvRegiSetupRow());
            this.l_strFreeTargetPeriod = this.l_service.getFreeTargetPeriod();
            assertNull(this.l_strFreeTargetPeriod);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * get無料対象期間 = 2
     *
     */
    public void testGetFreeTargetPeriod_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setFreeCoverageLength(2);
            this.l_service = new WEB3SrvRegiApplicationRequiredService(l_srvRegiSetupParams);
            this.l_strFreeTargetPeriod = this.l_service.getFreeTargetPeriod();
            assertEquals("2",this.l_strFreeTargetPeriod);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
}
@
