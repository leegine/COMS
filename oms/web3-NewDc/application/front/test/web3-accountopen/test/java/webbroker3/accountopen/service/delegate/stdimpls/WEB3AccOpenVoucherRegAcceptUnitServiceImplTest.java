head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenVoucherRegAcceptUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設伝票登録受付UnitServiceImplTest(WEB3AccOpenVoucherRegAcceptUnitServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/20 張騰宇 (中訊) 仕様変更 モデル146
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenVoucherRegAcceptUnitServiceImplTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AccOpenVoucherRegAcceptUnitServiceImplTest.class);
    
    public WEB3AccOpenVoucherRegAcceptUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl.notifyVoucherRegAccept(HostAccOpenAcceptParams)'
     */
    public void testNotifyVoucherRegAcceptCase1()
    {
        final String STR_METHOD_NAME = "testNotifyVoucherRegAcceptCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            HostConditionRegVoucherParams l_conditionRegVoucherParams =
                TestDBUtility.getHostConditionRegVoucherRow();
            l_conditionRegVoucherParams.setAccOpenRequestNumber("1");//識別コード
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setRequestCode("GI84C");
            
            
            TestDBUtility.deleteAll(HostConditionRegVoucherRow.TYPE);
            TestDBUtility.insertWithDel(l_conditionRegVoucherParams);
            
            WEB3AccOpenVoucherRegAcceptUnitServiceImpl l_impl = new WEB3AccOpenVoucherRegAcceptUnitServiceImpl();
            
            String l_strStatusDef= l_impl.notifyVoucherRegAccept(l_hostAccOpenAcceptParams);
            
            assertEquals(l_strStatusDef, "1");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }

    public void testNotifyVoucherRegAcceptCase2()
    {
        final String STR_METHOD_NAME = "testNotifyVoucherRegAcceptCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            HostConditionRegVoucherParams l_conditionRegVoucherParams =
                TestDBUtility.getHostConditionRegVoucherRow();
            l_conditionRegVoucherParams.setAccOpenRequestNumber("1");//識別コード
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setRequestCode("GI84G");
            
            
            TestDBUtility.deleteAll(HostConditionRegVoucherRow.TYPE);
            TestDBUtility.insertWithDel(l_conditionRegVoucherParams);
            
            WEB3AccOpenVoucherRegAcceptUnitServiceImpl l_impl = new WEB3AccOpenVoucherRegAcceptUnitServiceImpl();
            
            String l_strStatusDef= l_impl.notifyVoucherRegAccept(l_hostAccOpenAcceptParams);
            
            assertEquals(l_strStatusDef, "9");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }

    public void testNotifyVoucherRegAcceptCase3()
    {
        final String STR_METHOD_NAME = "testNotifyVoucherRegAcceptCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            HostAgencyNotifyVoucherParams l_hostAgencyNotifyVoucherParams =
                TestDBUtility.getHostAgencyNotifyVoucherRow();
            l_hostAgencyNotifyVoucherParams.setAccOpenRequestNumber("1");
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setRequestCode("GI85H");
            
            
            TestDBUtility.deleteAll(HostConditionRegVoucherRow.TYPE);
            TestDBUtility.insertWithDel(l_hostAgencyNotifyVoucherParams);
            
            WEB3AccOpenVoucherRegAcceptUnitServiceImpl l_impl = new WEB3AccOpenVoucherRegAcceptUnitServiceImpl();
            
            String l_strStatusDef= l_impl.notifyVoucherRegAccept(l_hostAccOpenAcceptParams);
            
            assertEquals(l_strStatusDef, "9");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }
}
@
