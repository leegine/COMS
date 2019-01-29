head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenVoucherCreatedServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AccOpenVoucherCreatedServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 武波 (中訊) 新規作成
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import test.util.TestDBUtility;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenVoucherCreatedServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenVoucherCreatedServiceImplTest.class);
    public WEB3AccOpenVoucherCreatedServiceImplTest(String arg0)
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

    public void testCreateAccOpenVoucher_C0001()
    {
        final String STR_METHOD_NAME = "testCreateAccOpenVoucher_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenVoucherCreatedServiceImpl l_accOpenAgencyInfoRegVoucher =
                new WEB3AccOpenVoucherCreatedServiceImpl();
            l_accOpenAgencyInfoRegVoucher.createAccOpenVoucher(l_accOpenExpAccountOpen);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testDeleteAccOpenVoucher_C0001()
    {
        final String STR_METHOD_NAME = "testDeleteAccOpenVoucher_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenVoucherCreatedServiceImpl l_accOpenAgencyInfoRegVoucher =
                new WEB3AccOpenVoucherCreatedServiceImpl();
            l_accOpenAgencyInfoRegVoucher.deleteAccOpenVoucher(l_accOpenExpAccountOpen);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetChangedImpossibleItemList_C0001()
    {
        final String STR_METHOD_NAME = "testGetChangedImpossibleItemList_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountDiv("4");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
            WEB3AccOpenVoucherCreatedServiceImpl l_accOpenAgencyInfoRegVoucher =
                new WEB3AccOpenVoucherCreatedServiceImpl();
            l_accOpenAgencyInfoRegVoucher.getChangedImpossibleItemList(l_accOpenExpAccountOpen);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
