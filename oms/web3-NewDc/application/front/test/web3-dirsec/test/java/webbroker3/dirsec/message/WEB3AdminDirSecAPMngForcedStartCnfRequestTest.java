head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAPMngForcedStartCnfRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AdminDirSecAPMngForcedStartCnfRequestTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/23 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecAPMngForcedStartCnfRequestTest extends TestBaseForMock
{

    private WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAPMngForcedStartCnfRequestTest.class);
    
    WEB3AdminDirSecAPMngForcedStartCnfRequest l_cnfRequest = null;
    public WEB3AdminDirSecAPMngForcedStartCnfRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_cnfRequest = new WEB3AdminDirSecAPMngForcedStartCnfRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //１）スーパークラスのvalidate()をコールする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_cnfRequest.pType= null;
            l_cnfRequest.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03105, l_ex.getErrorInfo());
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
                + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_cnfRequest.pType= "001";
            l_cnfRequest.validate();
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
                + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
