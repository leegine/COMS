head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqOrderEmpCodeManageServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3FeqOrderEmpCodeManageServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/05 武波 (中訊) 新規作成
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqOrderEmpCodeManageServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderEmpCodeManageServiceImplTest.class);
    public WEB3FeqOrderEmpCodeManageServiceImplTest(String arg0)
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

    public void test_process_c0001()
    {
        final String STR_METHOD_NAME = "test_process_c0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NW");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            WEB3FeqOrderEmpCodeManageServiceImpl l_impl =
                new WEB3FeqOrderEmpCodeManageServiceImpl();
            assertEquals("NW10001", l_impl.getNewEmpCode("0D", "1", new Date()));
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

}
@
