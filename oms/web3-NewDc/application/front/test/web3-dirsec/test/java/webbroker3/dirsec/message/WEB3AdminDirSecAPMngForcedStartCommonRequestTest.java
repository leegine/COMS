head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.59.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAPMngForcedStartCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AdminDirSecAPMngForcedStartCommonRequestTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/24 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecAPMngForcedStartCommonRequestTest extends TestBaseForMock
{

    private WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminDirSecAPMngForcedStartCommonRequestTest.class);
    
    WEB3AdminDirSecAPMngForcedStartCommonRequest l_commonRequest = null;
    
    public WEB3AdminDirSecAPMngForcedStartCommonRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_commonRequest = new WEB3AdminDirSecAPMngForcedStartCommonRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //１）PTYPEチェック
    //this.PTYPE == nullの場合、例外をスローする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_commonRequest.pType= null;
            l_commonRequest.validate();
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
            l_commonRequest.pType= "1111";
            l_commonRequest.validate();
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
