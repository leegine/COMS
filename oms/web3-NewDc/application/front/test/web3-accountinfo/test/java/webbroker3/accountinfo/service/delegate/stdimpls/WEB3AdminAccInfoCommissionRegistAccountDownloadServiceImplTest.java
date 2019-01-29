head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.31.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/11/10 張少傑 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.data.CommissionCourseRegistRow;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionChangeAccountInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.CommCodeChgMstParams;
import webbroker3.gentrade.data.CommCodeChgMstRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import test.util.TestDBUtility;

public class WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImplTest.class);

    public WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //　@　@　@　@部店ID = 引数.部店ID
    //　@　@　@　@手数料商品コード = 引数.手数料商品コード
    //　@　@　@　@適用開始日 ≦ 引数.適用開始日
    //　@　@　@　@手数料コースコード = 引数.手数料コースコード
    public void testGetCommissionNo_case_001()
    {
        String STR_METHOD_NAME = " testGetCommissionNo_case_001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(CommCodeChgMstRow.TYPE);
            CommCodeChgMstParams l_commCodeChgMstParams = TestDBUtility.getCommCodeChgMstRow();
            TestDBUtility.insertWithDel(l_commCodeChgMstParams);
            long l_branchId = 3301;
            String l_strCommissionProductCode = "10";
            Timestamp l_tsAppliStartDate =
                new Timestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd").getTime());
            String l_strCommissionCourseCode = "02";
            WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl l_impl = 
                new WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl();
            String l_commissionNo = l_impl.getCommissionNo(l_branchId, l_strCommissionProductCode,
                l_tsAppliStartDate,l_strCommissionCourseCode);
            assertEquals(l_commCodeChgMstParams.getCommissionNo(),l_commissionNo);
            log.exiting(STR_METHOD_NAME);
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    //　@　@　@　@部店ID = 引数.部店ID
    //　@　@　@　@手数料商品コード = 引数.手数料商品コード
    //　@　@　@　@適用開始日 ≦ 引数.適用開始日
    //　@　@　@　@手数料コースコード = "99"
    public void testGetCommissionNo_case_002()
    {
        String STR_METHOD_NAME = " testGetCommissionNo_case_002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(CommCodeChgMstRow.TYPE);
            CommCodeChgMstParams l_commCodeChgMstParams = TestDBUtility.getCommCodeChgMstRow();
            l_commCodeChgMstParams.setCommissionCourseDiv("99");
            TestDBUtility.insertWithDel(l_commCodeChgMstParams);
            long l_branchId = 3301;
            String l_strCommissionProductCode = "10";
            Timestamp l_tsAppliStartDate =
                new Timestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd").getTime());
            String l_strCommissionCourseCode = "99";
            WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl l_impl = 
                new WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl();
            String l_commissionNo = l_impl.getCommissionNo(l_branchId, l_strCommissionProductCode,
                l_tsAppliStartDate,l_strCommissionCourseCode);
            assertEquals(l_commCodeChgMstParams.getCommissionNo(),l_commissionNo);
            log.exiting(STR_METHOD_NAME);
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    //レコードが取得できない場合は例外をスローする。
    public void testGetCommissionNo_case_003()
    {
        String STR_METHOD_NAME = " testGetCommissionNo_case_003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(CommCodeChgMstRow.TYPE);
            CommCodeChgMstParams l_commCodeChgMstParams = TestDBUtility.getCommCodeChgMstRow();
            TestDBUtility.insertWithDel(l_commCodeChgMstParams);
            long l_branchId = 3301;
            String l_strCommissionProductCode = "10";
            Timestamp l_tsAppliStartDate =
                new Timestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd").getTime());
            String l_strCommissionCourseCode = "99";
            WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl l_impl = 
                new WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl();
            String l_commissionNo = l_impl.getCommissionNo(l_branchId, l_strCommissionProductCode,
                l_tsAppliStartDate,l_strCommissionCourseCode);
            System.out.print("aaa");
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            System.out.print("bbb");
            log.error(l_ex.getErrorMessage());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //call getCommissionNo
    public void testgetDownloadData_case_001()
    {
        String STR_METHOD_NAME = " testgetDownloadData_case_001";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(CommissionCourseRegistRow.TYPE);
            CommissionCourseRegistParams l_commissionCourseRegistParams =
                TestDBUtility.getCommissionCourseRegistRow();
            TestDBUtility.insertWithDel(l_commissionCourseRegistParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams= TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(63101);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(63);
            l_branchParams.setBranchCode("101");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParamss= TestDBUtility.getMainAccountRow();
            l_mainAccountParamss.setAccountId(631012512203L);
            TestDBUtility.insertWithDel(l_mainAccountParamss);
            
            
            TestDBUtility.deleteAll(CommCodeChgMstRow.TYPE);
            CommCodeChgMstParams l_commCodeChgMstParams = TestDBUtility.getCommCodeChgMstRow();
            l_commCodeChgMstParams.setBranchId(63101);
            l_commCodeChgMstParams.setCommissionNo("23");
            TestDBUtility.insertWithDel(l_commCodeChgMstParams);
            
            String l_strInstitutionCode = "0D";
            Date l_datAppliStartDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            boolean l_blnIsDownloadData = true;
            WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl l_impl = 
                new WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl();
            WEB3AccInfoCommissionChangeAccountInfo[] l_AccInfoCommissionChangeAccountInfos=
                l_impl.getDownloadData(
                l_strInstitutionCode,
                l_datAppliStartDate,
                l_administrator,
                l_blnIsDownloadData);
            assertEquals("23",l_AccInfoCommissionChangeAccountInfos[0].commissionNo);
        }
        catch(WEB3BaseException l_ex)
        {
            fail();
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
