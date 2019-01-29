head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.32.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AccInfoAccountBaseInfoCreatedServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo;
import webbroker3.accountinfo.message.WEB3AccInfoAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoBatoInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCfdAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCorporationInfo;
import webbroker3.accountinfo.message.WEB3AccInfoFxAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoInsiderInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressInfoUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressTypeUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.accountinfo.message.WEB3AccInfoStockOptionInfo;
import webbroker3.accountinfo.message.WEB3AccInfoStopInfo;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountCodeRow;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.data.AccountMailAddressParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.FTransFinInstitutionParams;
import webbroker3.gentrade.data.MailAssortmentParams;
import webbroker3.gentrade.data.TransferedFinInstitutionParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoAccountBaseInfoCreatedServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AccInfoAccountBaseInfoCreatedServiceImplTest.class);

    public WEB3AccInfoAccountBaseInfoCreatedServiceImplTest(String arg0)
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

    public void testCreateTransferAccountInfo_0001()
    {
        String STR_METHOD_NAME = "testCreateTransferAccountInfo_0001 ()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TransferedFinInstitutionParams l_transferedFinInstitutionParams = TestDBUtility
                    .getTransferedFinInstitutionRow();
            l_transferedFinInstitutionParams.setAccountCode("2512246");
            l_transferedFinInstitutionParams.setDesignateDiv("A");
            l_transferedFinInstitutionParams.setTransferDiv(WEB3TransferDivDef.POSTAL_TRANSFER);
            TestDBUtility.deleteAll(l_transferedFinInstitutionParams.getRowType());
            TestDBUtility.insertWithDel(l_transferedFinInstitutionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        MainAccountParams l_mainAccountParams = new MainAccountParams();
        l_mainAccountParams.setInstitutionId(33);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountCode("2512246");
        l_mainAccountParams.setBankAccountRegi("2");
        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_accInfoAccountBaseInfoCreatedServiceImpl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();
        try
        {
            WEB3AccInfoAccountInfo l_accountInfo = l_accInfoAccountBaseInfoCreatedServiceImpl
                    .createTransferAccountInfo(l_mainAccount);

            assertEquals("0", l_accountInfo.bankAccountRegi);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testCreateTransferAccountInfo_0002()
    {
        String STR_METHOD_NAME = "testCreateTransferAccountInfo_0002 ()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TransferedFinInstitutionParams l_transferedFinInstitutionParams = TestDBUtility
                    .getTransferedFinInstitutionRow();
            l_transferedFinInstitutionParams.setAccountCode("2512246");
            l_transferedFinInstitutionParams.setDesignateDiv("A");
            l_transferedFinInstitutionParams.setTransferDiv(WEB3TransferDivDef.POSTAL_TRANSFER);
            TestDBUtility.deleteAll(l_transferedFinInstitutionParams.getRowType());
            TestDBUtility.insertWithDel(l_transferedFinInstitutionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        MainAccountParams l_mainAccountParams = new MainAccountParams();
        l_mainAccountParams.setInstitutionId(33);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountCode("2512246");
        l_mainAccountParams.setBankAccountRegi("1");
        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_accInfoAccountBaseInfoCreatedServiceImpl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();
        try
        {
            WEB3AccInfoAccountInfo l_accountInfo = l_accInfoAccountBaseInfoCreatedServiceImpl
                    .createTransferAccountInfo(l_mainAccount);

            assertEquals("1", l_accountInfo.bankAccountRegi);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testCreateTransferAccountInfo_0003()
    {
        String STR_METHOD_NAME = "testCreateTransferAccountInfo_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TransferedFinInstitutionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TransferedFinInstitutionParams l_transferedFinInstitutionParams = TestDBUtility
                    .getTransferedFinInstitutionRow();
            l_transferedFinInstitutionParams.setAccountCode("2512246");
            l_transferedFinInstitutionParams.setDesignateDiv("A");
            l_transferedFinInstitutionParams.setTransferDiv(WEB3TransferDivDef.POSTAL_TRANSFER);
            l_transferedFinInstitutionParams.setTransCommission("4");
            l_transferedFinInstitutionParams.setTransDealDiv("2");
            TestDBUtility.deleteAll(l_transferedFinInstitutionParams.getRowType());
            TestDBUtility.insertWithDel(l_transferedFinInstitutionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        MainAccountParams l_mainAccountParams = new MainAccountParams();
        l_mainAccountParams.setInstitutionId(33);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountCode("2512246");
        l_mainAccountParams.setBankAccountRegi("1");
        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_accInfoAccountBaseInfoCreatedServiceImpl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();
        try
        {
            WEB3AccInfoAccountInfo l_accountInfo = l_accInfoAccountBaseInfoCreatedServiceImpl
                    .createTransferAccountInfo(l_mainAccount);

            assertEquals("1", l_accountInfo.bankAccountRegi);
            assertEquals("4", l_accountInfo.transferAccountDiv);
            assertEquals("2", l_accountInfo.tradeHandleDiv);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * class:WEB3AccInfoAccountBaseInfoCreatedServiceImpl
     * Method:createFXAccInformationUnit
     * 測試CaseNo:470
     * 会社別FXシステム条件テーブル
     * 檢索不到數據的場合、
     * 会社別FXシステム条件テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode() And  
     * 会社別FXシステム条件テーブル.部店コード = 顧客.getBranch().getBranchCode() And 
     * FXシステム区分 ！= null 
     */
    public void test_createFXAccInformationUnit_c0001()
    {
        String STR_METHOD_NAME = "test_createFXAccInformationUnit_c0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //清空[会社別FXシステム条件テーブル]數據
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);

            //插入一條數據
            //l_compFxConditionParams.setInstitutionCode("0D");
            //l_compFxConditionParams.setBranchCode("381");
            //l_compFxConditionParams.setFxSystemCode("02");
            //l_compFxConditionParams.setGroupName("name");
            //l_compFxConditionParams.setUrl("url");
            //l_compFxConditionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            //l_compFxConditionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            //l_compFxConditionParams.setFxHeadOfLoginId("159");
            //l_compFxConditionParams.setSingleSignOnUrl("url");
            //l_compFxConditionParams.setValidTerm("12");
            //l_compFxConditionParams.setFxSystemDiv("1");
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");

            //InstitutionCode:0D
            //InstitutionId:33
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());

        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        MainAccountParams l_mainAccountParams = new MainAccountParams();
        l_mainAccountParams.setInstitutionId(33);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountCode("2512246");
        l_mainAccountParams.setBankAccountRegi("1");
        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_accInfoAccountBaseInfoCreatedServiceImpl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();

        try
        {

            WEB3AccInfoFxAccountInfo[] l_accInfoFxs = l_accInfoAccountBaseInfoCreatedServiceImpl
                    .createFXAccInformationUnit(l_mainAccount);

            assertNull(l_accInfoFxs);
        }
        catch (Exception ex1)
        {
            log.error(TEST_END + STR_METHOD_NAME, ex1);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /**
     * class:WEB3AccInfoAccountBaseInfoCreatedServiceImpl
     * Method:createFXAccInformationUnit
     * 測試CaseNo:471
     * 会社別FXシステム条件テーブル
     * 檢索不到數據的場合、
     * 会社別FXシステム条件テーブル.証券会社コード ！= 顧客.getInstitution().getInstitutionCode() And  
     * 会社別FXシステム条件テーブル.部店コード = 顧客.getBranch().getBranchCode() And 
     * FXシステム区分 == null 
     */
    public void test_createFXAccInformationUnit_c0002()
    {
        String STR_METHOD_NAME = "test_createFXAccInformationUnit_c0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //清空[会社別FXシステム条件テーブル]數據
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);

            //插入一條數據
            //l_compFxConditionParams.setInstitutionCode("1D");
            //l_compFxConditionParams.setBranchCode("381");
            //l_compFxConditionParams.setFxSystemCode("02");
            //l_compFxConditionParams.setGroupName("name");
            //l_compFxConditionParams.setUrl("url");
            //l_compFxConditionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            //l_compFxConditionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            //l_compFxConditionParams.setFxHeadOfLoginId("159");
            //l_compFxConditionParams.setSingleSignOnUrl("url");
            //l_compFxConditionParams.setValidTerm("12");
            //l_compFxConditionParams.setFxSystemDiv("1");
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("1D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemDiv(null);
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            //InstitutionCode:0D
            //InstitutionId:33
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());

        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        MainAccountParams l_mainAccountParams = new MainAccountParams();
        l_mainAccountParams.setInstitutionId(33);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountCode("2512246");
        l_mainAccountParams.setBankAccountRegi("1");
        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_accInfoAccountBaseInfoCreatedServiceImpl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();

        try
        {

            WEB3AccInfoFxAccountInfo[] l_accInfoFxs = l_accInfoAccountBaseInfoCreatedServiceImpl
                    .createFXAccInformationUnit(l_mainAccount);

            assertNull(l_accInfoFxs);
        }
        catch (Exception ex1)
        {
            log.error(TEST_END + STR_METHOD_NAME, ex1);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * class:WEB3AccInfoAccountBaseInfoCreatedServiceImpl
     * Method:createFXAccInformationUnit
     * 測試CaseNo:472
     * 会社別FXシステム条件テーブル
     * 檢索不到數據的場合、
     * 会社別FXシステム条件テーブル.証券会社コード == 顧客.getInstitution().getInstitutionCode() And  
     * 会社別FXシステム条件テーブル.部店コード ！= 顧客.getBranch().getBranchCode() And 
     * FXシステム区分 == null 
     */
    public void test_createFXAccInformationUnit_c0003()
    {
        String STR_METHOD_NAME = "test_createFXAccInformationUnit_c0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //清空[会社別FXシステム条件テーブル]數據
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);

            //插入一條數據
            //l_compFxConditionParams.setInstitutionCode("0D");
            //l_compFxConditionParams.setBranchCode("624");
            //l_compFxConditionParams.setFxSystemCode("02");
            //l_compFxConditionParams.setGroupName("name");
            //l_compFxConditionParams.setUrl("url");
            //l_compFxConditionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            //l_compFxConditionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            //l_compFxConditionParams.setFxHeadOfLoginId("159");
            //l_compFxConditionParams.setSingleSignOnUrl("url");
            //l_compFxConditionParams.setValidTerm("12");
            //l_compFxConditionParams.setFxSystemDiv("1");
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemDiv(null);
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            //InstitutionCode:0D
            //InstitutionId:33
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());

        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        MainAccountParams l_mainAccountParams = new MainAccountParams();
        l_mainAccountParams.setInstitutionId(33);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountCode("2512246");
        l_mainAccountParams.setBankAccountRegi("1");
        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_accInfoAccountBaseInfoCreatedServiceImpl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();

        try
        {

            WEB3AccInfoFxAccountInfo[] l_accInfoFxs = l_accInfoAccountBaseInfoCreatedServiceImpl
                    .createFXAccInformationUnit(l_mainAccount);

            assertNull(l_accInfoFxs);
        }
        catch (Exception ex1)
        {
            log.error(TEST_END + STR_METHOD_NAME, ex1);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * class:WEB3AccInfoAccountBaseInfoCreatedServiceImpl
     * Method:createFXAccInformationUnit
     * 測試CaseNo:473
     * 会社別FXシステム条件テーブル
     * 檢索到一條數據的場合、
     * ＦＸ顧客テーブル沒有檢索到數據的場合。
     */
    public void test_createFXAccInformationUnit_c0004()
    {
        String STR_METHOD_NAME = "test_createFXAccInformationUnit_c0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //清空[会社別FXシステム条件テーブル]數據
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);

            //插入一條數據
            //l_compFxConditionParams.setInstitutionCode("0D");
            //l_compFxConditionParams.setBranchCode("381");
            //l_compFxConditionParams.setFxSystemCode("02");
            //l_compFxConditionParams.setGroupName("name");
            //l_compFxConditionParams.setUrl("url");
            //l_compFxConditionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            //l_compFxConditionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            //l_compFxConditionParams.setFxHeadOfLoginId("159");
            //l_compFxConditionParams.setSingleSignOnUrl("url");
            //l_compFxConditionParams.setValidTerm("12");
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemDiv(null);
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            //InstitutionCode:0D
            //InstitutionId:33
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());

        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        MainAccountParams l_mainAccountParams = new MainAccountParams();
        l_mainAccountParams.setInstitutionId(33);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountCode("2512246");
        l_mainAccountParams.setBankAccountRegi("1");
        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_accInfoAccountBaseInfoCreatedServiceImpl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();

        try
        {

            WEB3AccInfoFxAccountInfo[] l_accInfoFxs = l_accInfoAccountBaseInfoCreatedServiceImpl
                    .createFXAccInformationUnit(l_mainAccount);

            assertNull(l_accInfoFxs);
        }
        catch (Exception ex1)
        {
            log.error(TEST_END + STR_METHOD_NAME, ex1);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * class:WEB3AccInfoAccountBaseInfoCreatedServiceImpl
     * Method:createFXAccInformationUnit
     * 測試CaseNo:474
     * 会社別FXシステム条件テーブル
     * 檢索到3條數據的場合、
     * ＦＸ顧客テーブル對應第一條檢索到。
     * ＦＸ顧客テーブル對應第二條沒有檢索到。
     * ＦＸ顧客テーブル對應第3條檢索到。
     * ＦＸ口座番号テーブル 都有數據。
     */
    public void test_createFXAccInformationUnit_c0005()
    {
        String STR_METHOD_NAME = "test_createFXAccInformationUnit_c0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //清空[会社別FXシステム条件テーブル]數據
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);

            //插入3條數據
            //l_compFxConditionParams.setInstitutionCode("0D");
            //l_compFxConditionParams.setBranchCode("381");
            //l_compFxConditionParams.setFxSystemCode("02");
            //l_compFxConditionParams.setGroupName("name");
            //l_compFxConditionParams.setUrl("url");
            //l_compFxConditionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            //l_compFxConditionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            //l_compFxConditionParams.setFxHeadOfLoginId("159");
            //l_compFxConditionParams.setSingleSignOnUrl("url");
            //l_compFxConditionParams.setValidTerm("12");
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemDiv(null);
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemDiv(null);
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            l_compFxConditionParams.setFxSystemCode("03");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemDiv(null);
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            //ＦＸ顧客テーブル
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxLoginId(1111);
            TestDBUtility.insertWithDel(l_fxAccountParams);

            l_fxAccountParams.setFxAccountId(333812512247L);
            l_fxAccountParams.setFxSystemCode("03");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxLoginId(33333);
            TestDBUtility.insertWithDel(l_fxAccountParams);

            //ＦＸ口座番号テーブル
            //            l_fxAccountCodeParams.setInstitutionCode("0D");
            //            l_fxAccountCodeParams.setBranchCode("624");
            //            l_fxAccountCodeParams.setFxSystemCode("02");
            //            l_fxAccountCodeParams.setAccountCode("2512246");
            //            l_fxAccountCodeParams.setFxCourseDiv("1");
            //            l_fxAccountCodeParams.setFxAccountCode("25");
            //            l_fxAccountCodeParams.setLastUpdater("123456");
            //            l_fxAccountCodeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            //            l_fxAccountCodeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            FxAccountCodeParams l_fxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setBranchCode("381");
            l_fxAccountCodeParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);

            l_fxAccountCodeParams.setFxSystemCode("03");
            l_fxAccountCodeParams.setFxCourseDiv("2");
            l_fxAccountCodeParams.setAccountCode("2512246");
            l_fxAccountCodeParams.setFxAccountCode("765");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);

            //InstitutionCode:0D
            //InstitutionId:33
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());

        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        MainAccountParams l_mainAccountParams = new MainAccountParams();
        l_mainAccountParams.setInstitutionId(33);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountCode("2512246");
        l_mainAccountParams.setBankAccountRegi("1");
        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_accInfoAccountBaseInfoCreatedServiceImpl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();

        try
        {

            WEB3AccInfoFxAccountInfo[] l_accInfoFxs = l_accInfoAccountBaseInfoCreatedServiceImpl
                    .createFXAccInformationUnit(l_mainAccount);
            assertEquals(2, l_accInfoFxs.length);

            assertEquals("1111", l_accInfoFxs[0].fxLoginId);
            assertEquals("33333", l_accInfoFxs[1].fxLoginId);
            assertEquals("25", l_accInfoFxs[0].fxAccountCode1);
            assertEquals(null, l_accInfoFxs[0].fxAccountCode2);
            assertEquals("765", l_accInfoFxs[1].fxAccountCode2);
            assertEquals(null, l_accInfoFxs[1].fxAccountCode1);

        }
        catch (Exception ex1)
        {
            log.error(TEST_END + STR_METHOD_NAME, ex1);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void test_createAccountBaseInfo_1()
    {
        String STR_METHOD_NAME = "test_createAccountBaseInfo_1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            long loginId = 33381;
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeMainAccount",
                    "getLoginId", new Class[]
                    {}, new Long(33381L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", "getLoginAttributes",
                    new Class[]
                    {long.class}, new HashMap());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", "getLoginInfo",
                    new Class[]
                    {long.class}, l_loginInfoForTest);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "getLoginTypeAttributes", new Class[]
                    {long.class}, new HashMap());

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("369");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setRuitoAccOpenDiv("3");
            l_mainAccountParams.setBankAccountRegi("3");
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setProamDiv("2");
            l_mainAccountParams.setPtsAccOpenDiv("1");

            l_mainAccountParams.setBroadcastLaw("1");
            l_mainAccountParams.setAviationLaw("1");
            l_mainAccountParams.setNttLaw("1");
            l_mainAccountParams.setDividendTransDesignate("1");
            l_mainAccountParams.setStandingProxy("0");
            l_mainAccountParams.setStatutoryAgent("0");
            l_mainAccountParams.setAffiliateAccountCode("1001");
            l_mainAccountParams.setAgencyNotifyCmpDiv("0");

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //BranchPreferencesParams
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("gentrade.bizlogicprovider.div");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.deleteAllAndCommit(l_branchPreferencesParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_branchPreferencesParams);

            //MobileOfficeInfoRegistParams
            MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = TestDBUtility.getMobileOfficeInfoRegistRow();
            l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
            l_mobileOfficeInfoRegistParams.setBranchId(33381);
            l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);

            TestDBUtility.deleteAllAndCommit(l_mobileOfficeInfoRegistParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_mobileOfficeInfoRegistParams);

            TestDBUtility.deleteAllAndCommit(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getInstitutionRow());

            TestDBUtility.deleteAllAndCommit(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getBranchRow());

            FTransFinInstitutionParams l_fTransFinInstitutionParams = new FTransFinInstitutionParams();
            l_fTransFinInstitutionParams.setInstitutionCode("0D");
            TestDBUtility.deleteAllAndCommit(l_fTransFinInstitutionParams.getRowType());

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(l_mainAccountParams);
            WEB3AccInfoAccountBaseInfoCreatedServiceImplForTest l_accInfoAccountBaseInfoCreatedServiceImplForTest = new WEB3AccInfoAccountBaseInfoCreatedServiceImplForTest();
            WEB3AccInfoAccountBaseInfo l_accountBaseInfo = l_accInfoAccountBaseInfoCreatedServiceImplForTest
                    .createAccountBaseInfo(l_mainAccount);

            assertEquals("1", l_accountBaseInfo.broadcastLaw);
            assertEquals("1", l_accountBaseInfo.aviationLaw);
            assertEquals("1", l_accountBaseInfo.nttLaw);
            assertEquals("1", l_accountBaseInfo.dividendTransDesignate);
            assertEquals("0", l_accountBaseInfo.standingProxy);
            assertEquals("0", l_accountBaseInfo.statutoryAgent);
            assertEquals("1001", l_accountBaseInfo.affiliateAccountCode);
            assertEquals("0", l_accountBaseInfo.agencyNotifyCmpDiv);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    public void test_createMailAddressInfo_case0001()
    {
        String STR_METHOD_NAME = "test_createMailAddressInfo_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("369");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setRuitoAccOpenDiv("3");
            l_mainAccountParams.setBankAccountRegi("3");
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setProamDiv("2");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            l_mainAccountParams.setBroadcastLaw("1");
            l_mainAccountParams.setAviationLaw("1");
            l_mainAccountParams.setNttLaw("1");
            l_mainAccountParams.setDividendTransDesignate("1");
            l_mainAccountParams.setStandingProxy("0");
            l_mainAccountParams.setStatutoryAgent("0");
            l_mainAccountParams.setAffiliateAccountCode("1001");
            l_mainAccountParams.setAgencyNotifyCmpDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(AccountMailAddressParams.TYPE);
            WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_impl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(l_mainAccountParams);
            WEB3AccInfoMailAddressInfoUnit[] l_unit = l_impl.createMailAddressInfo(l_mainAccount);

            assertNull(l_unit);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    public void test_createMailAddressInfo_case0002()
    {
        String STR_METHOD_NAME = "test_createMailAddressInfo_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);       
        try
        {
            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("369");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setRuitoAccOpenDiv("3");
            l_mainAccountParams.setBankAccountRegi("3");
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setProamDiv("2");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            l_mainAccountParams.setBroadcastLaw("1");
            l_mainAccountParams.setAviationLaw("1");
            l_mainAccountParams.setNttLaw("1");
            l_mainAccountParams.setDividendTransDesignate("1");
            l_mainAccountParams.setStandingProxy("0");
            l_mainAccountParams.setStatutoryAgent("0");
            l_mainAccountParams.setAffiliateAccountCode("1001");
            l_mainAccountParams.setAgencyNotifyCmpDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(AccountMailAddressParams.TYPE);
            AccountMailAddressParams l_accountMailAddressParams = new AccountMailAddressParams();
            l_accountMailAddressParams.setInstitutionCode("0D");
            l_accountMailAddressParams.setBranchCode("381");
            l_accountMailAddressParams.setAccountCode("190600");
            l_accountMailAddressParams.setEmailAddressNumber(1);
            l_accountMailAddressParams.setAddressDiv("1");
            l_accountMailAddressParams.setEmailAddress("test@@sinocom.cn");
            l_accountMailAddressParams.setEmailLastUpdater("190600");
            l_accountMailAddressParams.setEmailLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_accountMailAddressParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_accountMailAddressParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_accountMailAddressParams);
            WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_impl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(l_mainAccountParams);
            WEB3AccInfoMailAddressInfoUnit[] l_unit = l_impl.createMailAddressInfo(l_mainAccount);

            assertEquals(1, l_unit.length);
       }
       catch (Exception l_ex)
       {
           log.exiting(STR_METHOD_NAME);
           log.error(STR_METHOD_NAME, l_ex);
           log.info(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(STR_METHOD_NAME);
       log.info(TEST_END + STR_METHOD_NAME);
     }

    public void test_createMailAddressInfo_case0003()
    {
        String STR_METHOD_NAME = "test_createMailAddressInfo_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);       
        try
        {
            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("369");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setRuitoAccOpenDiv("3");
            l_mainAccountParams.setBankAccountRegi("3");
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setProamDiv("2");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            l_mainAccountParams.setBroadcastLaw("1");
            l_mainAccountParams.setAviationLaw("1");
            l_mainAccountParams.setNttLaw("1");
            l_mainAccountParams.setDividendTransDesignate("1");
            l_mainAccountParams.setStandingProxy("0");
            l_mainAccountParams.setStatutoryAgent("0");
            l_mainAccountParams.setAffiliateAccountCode("1001");
            l_mainAccountParams.setAgencyNotifyCmpDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(AccountMailAddressParams.TYPE);
            AccountMailAddressParams l_accountMailAddressParams = new AccountMailAddressParams();
            l_accountMailAddressParams.setInstitutionCode("0D");
            l_accountMailAddressParams.setBranchCode("381");
            l_accountMailAddressParams.setAccountCode("190600");
            l_accountMailAddressParams.setEmailAddressNumber(1);
            l_accountMailAddressParams.setAddressDiv("1");
            l_accountMailAddressParams.setEmailAddress("test@@sinocom.cn");
            l_accountMailAddressParams.setEmailLastUpdater("190600");
            l_accountMailAddressParams.setEmailLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_accountMailAddressParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_accountMailAddressParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_accountMailAddressParams);

            l_accountMailAddressParams.setInstitutionCode("0D");
            l_accountMailAddressParams.setBranchCode("381");
            l_accountMailAddressParams.setAccountCode("190600");
            l_accountMailAddressParams.setEmailAddressNumber(2);
            l_accountMailAddressParams.setAddressDiv("2");
            l_accountMailAddressParams.setEmailAddress("test1@@sinocom.cn");
            l_accountMailAddressParams.setEmailLastUpdater("190600");
            l_accountMailAddressParams.setEmailLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_accountMailAddressParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_accountMailAddressParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_accountMailAddressParams);
            WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_impl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(l_mainAccountParams);
            WEB3AccInfoMailAddressInfoUnit[] l_unit = l_impl.createMailAddressInfo(l_mainAccount);

            assertEquals(2, l_unit.length);
       }
       catch (Exception l_ex)
       {
           log.exiting(STR_METHOD_NAME);
           log.error(STR_METHOD_NAME, l_ex);
           log.info(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(STR_METHOD_NAME);
       log.info(TEST_END + STR_METHOD_NAME);
     }

    public void test_createMailAddressTypeInfo_case0001()
    {
        String STR_METHOD_NAME = "test_createMailAddressTypeInfo_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("369");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setRuitoAccOpenDiv("3");
            l_mainAccountParams.setBankAccountRegi("3");
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setProamDiv("2");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            l_mainAccountParams.setBroadcastLaw("1");
            l_mainAccountParams.setAviationLaw("1");
            l_mainAccountParams.setNttLaw("1");
            l_mainAccountParams.setDividendTransDesignate("1");
            l_mainAccountParams.setStandingProxy("0");
            l_mainAccountParams.setStatutoryAgent("0");
            l_mainAccountParams.setAffiliateAccountCode("1001");
            l_mainAccountParams.setAgencyNotifyCmpDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(MailAssortmentParams.TYPE);
            WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_impl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(l_mainAccountParams);
            WEB3AccInfoMailAddressTypeUnit[] l_unit = l_impl.createMailAddressTypeInfo(l_mainAccount);

            assertNull(l_unit);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    public void test_createMailAddressTypeInfo_case0002()
    {
        String STR_METHOD_NAME = "test_createMailAddressTypeInfo_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);       
        try
        {
            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("369");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setRuitoAccOpenDiv("3");
            l_mainAccountParams.setBankAccountRegi("3");
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setProamDiv("2");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            l_mainAccountParams.setBroadcastLaw("1");
            l_mainAccountParams.setAviationLaw("1");
            l_mainAccountParams.setNttLaw("1");
            l_mainAccountParams.setDividendTransDesignate("1");
            l_mainAccountParams.setStandingProxy("0");
            l_mainAccountParams.setStatutoryAgent("0");
            l_mainAccountParams.setAffiliateAccountCode("1001");
            l_mainAccountParams.setAgencyNotifyCmpDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(MailAssortmentParams.TYPE);
            MailAssortmentParams l_mailAssortmentParams = TestDBUtility.getMailAssortmentRow();

            TestDBUtility.insertWithDel(l_mailAssortmentParams);
            WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_impl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(l_mainAccountParams);
            WEB3AccInfoMailAddressTypeUnit[] l_unit = l_impl.createMailAddressTypeInfo(l_mainAccount);

            assertEquals(1, l_unit.length);
       }
       catch (Exception l_ex)
       {
           log.exiting(STR_METHOD_NAME);
           log.error(STR_METHOD_NAME, l_ex);
           log.info(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(STR_METHOD_NAME);
       log.info(TEST_END + STR_METHOD_NAME);
     }

    public void test_createMailAddressTypeInfo_case0003()
    {
        String STR_METHOD_NAME = "test_createMailAddressTypeInfo_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);       
        try
        {
            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("369");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setRuitoAccOpenDiv("3");
            l_mainAccountParams.setBankAccountRegi("3");
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setProamDiv("2");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            l_mainAccountParams.setBroadcastLaw("1");
            l_mainAccountParams.setAviationLaw("1");
            l_mainAccountParams.setNttLaw("1");
            l_mainAccountParams.setDividendTransDesignate("1");
            l_mainAccountParams.setStandingProxy("0");
            l_mainAccountParams.setStatutoryAgent("0");
            l_mainAccountParams.setAffiliateAccountCode("1001");
            l_mainAccountParams.setAgencyNotifyCmpDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(MailAssortmentParams.TYPE);
            MailAssortmentParams l_mailAssortmentParams = TestDBUtility.getMailAssortmentRow();
            TestDBUtility.insertWithDel(l_mailAssortmentParams);
            l_mailAssortmentParams.setInstitutionCode("0D");
            l_mailAssortmentParams.setBranchCode("381");
            l_mailAssortmentParams.setAccountCode("190600");
            l_mailAssortmentParams.setEmailAddressNumber(2);
            TestDBUtility.insertWithDel(l_mailAssortmentParams);
            WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_impl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(l_mainAccountParams);
            WEB3AccInfoMailAddressTypeUnit[] l_unit = l_impl.createMailAddressTypeInfo(l_mainAccount);

            assertEquals(2, l_unit.length);
       }
       catch (Exception l_ex)
       {
           log.exiting(STR_METHOD_NAME);
           log.error(STR_METHOD_NAME, l_ex);
           log.info(TEST_END + STR_METHOD_NAME);
           fail();
       }
       log.exiting(STR_METHOD_NAME);
       log.info(TEST_END + STR_METHOD_NAME);
     }
    
    public void test_createCFDAccountInfoCase0001()
    {
        String STR_METHOD_NAME = "test_createCFDAccountInfoCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("369");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setRuitoAccOpenDiv("3");
            l_mainAccountParams.setBankAccountRegi("3");
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            l_mainAccountParams.setProamDiv("2");
            l_mainAccountParams.setPtsAccOpenDiv("1");
            l_mainAccountParams.setBroadcastLaw("1");
            l_mainAccountParams.setAviationLaw("1");
            l_mainAccountParams.setNttLaw("1");
            l_mainAccountParams.setDividendTransDesignate("1");
            l_mainAccountParams.setStandingProxy("0");
            l_mainAccountParams.setStatutoryAgent("0");
            l_mainAccountParams.setAffiliateAccountCode("1001");
            l_mainAccountParams.setAgencyNotifyCmpDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setFxSystemCode("11");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("11");
            l_fxAccountParams.setAccountCode("190600");
            TestDBUtility.insertWithDel(l_fxAccountParams);
            
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("381");
            l_fxAccountCodeParams.setFxSystemCode("11");
            l_fxAccountCodeParams.setAccountCode("190600");
            l_fxAccountCodeParams.setFxCourseDiv(WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            l_fxAccountCodeParams.setFxAccountCode("100000");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            l_fxAccountCodeParams.setFxCourseDiv(WEB3GftTransStatusCourseDivDef.CFD_COURSE_2);
            l_fxAccountCodeParams.setFxAccountCode("100002");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            l_fxAccountCodeParams.setFxCourseDiv(WEB3GftTransStatusCourseDivDef.CFD_COURSE_3);
            l_fxAccountCodeParams.setFxAccountCode("100003");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            l_fxAccountCodeParams.setFxCourseDiv(WEB3GftTransStatusCourseDivDef.CFD_COURSE_4);
            l_fxAccountCodeParams.setFxAccountCode("100004");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            l_fxAccountCodeParams.setFxCourseDiv(WEB3GftTransStatusCourseDivDef.CFD_COURSE_5);
            l_fxAccountCodeParams.setFxAccountCode("100005");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            l_fxAccountCodeParams.setFxCourseDiv(WEB3GftTransStatusCourseDivDef.CFD_COURSE_6);
            l_fxAccountCodeParams.setFxAccountCode("100006");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            l_fxAccountCodeParams.setFxCourseDiv(WEB3GftTransStatusCourseDivDef.CFD_COURSE_7);
            l_fxAccountCodeParams.setFxAccountCode("100007");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            l_fxAccountCodeParams.setFxCourseDiv(WEB3GftTransStatusCourseDivDef.CFD_COURSE_8);
            l_fxAccountCodeParams.setFxAccountCode("100008");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            
            WEB3AccInfoAccountBaseInfoCreatedServiceImpl l_impl = new WEB3AccInfoAccountBaseInfoCreatedServiceImpl();
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(l_mainAccountParams);
            WEB3AccInfoCfdAccountInfo[] l_cfdAccountInfo = l_impl.createCFDAccountInfo(l_mainAccount);

            assertEquals(1, l_cfdAccountInfo.length);
            
       }
       catch (Exception l_ex)
       {
           log.exiting(STR_METHOD_NAME);
           log.error(STR_METHOD_NAME, l_ex);
           log.info(TEST_END + STR_METHOD_NAME);
           fail();
       }
        
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }
    
     public class LoginInfoForTest implements LoginInfo
     {

        public long getAccountId()
        {
            return 1;
        }

        public LoginTypeInfo getLoginTypeInfo()
        {
            return null;
        }

        public long getLoginId()
        {
            return 0;
        }

        public long getLoginTypeId()
        {
            return 0;
        }

        public String getUsername()
        {
            return null;
        }

        public String getInitialPassword()
        {
            return null;
        }

        public Set getSubordinateLoginGroups()
        {
            return null;
        }

        public boolean isDisabled()
        {
            return false;
        }

        public Set getReachableAccountIds()
        {
            return null;
        }

        public Set getReachableLoginIds()
        {
            return null;
        }

        public Set getReachableLogins()
        {
            return null;
        }

        public Map getAttributes()
        {
            return null;
        }

        public boolean isAccountReachable(long arg0)
        {
            return false;
        }

        public boolean hasFailedLogin()
        {
            return false;
        }

        public int getFailureCount()
        {
            return 0;
        }

        public Date getLastFailureTimestamp()
        {
            return null;
        }
    }

    public class WEB3AccInfoAccountBaseInfoCreatedServiceImplForTest extends
            WEB3AccInfoAccountBaseInfoCreatedServiceImpl
    {
        protected WEB3AccInfoInsiderInfo[] createInsiderInfo(WEB3GentradeMainAccount l_mainAccount)
                throws WEB3BaseException
        {
            return null;
        }

        protected WEB3AccInfoCommissionCourseChangeInfo[] createEquityCommissionCourseRegistInfo(
                WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
        {
            return null;
        }

        public WEB3AccInfoStopInfo createStopInfo(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
        {
            return null;
        }

        protected WEB3AccInfoMobileOfficeInfo createMobileOfficeInfo(WEB3GentradeMainAccount l_mainAccount)
                throws WEB3BaseException
        {
            return null;
        }

        protected WEB3AccInfoAccountInfo createExclusiveTransferAccountInfo(WEB3GentradeMainAccount l_mainAccount)
                throws WEB3BaseException
        {
            return null;
        }

        protected WEB3AccInfoAccountInfo createTransferAccountInfo(WEB3GentradeMainAccount l_mainAccount)
                throws WEB3BaseException
        {
            return null;
        }

        protected WEB3AccInfoBatoInfo createBatoInfo(WEB3GentradeMainAccount l_mainAccount)
        {
            return null;
        }

        protected WEB3AccInfoFxAccountInfo[] createFXAccInformationUnit(WEB3GentradeMainAccount l_account)
                throws WEB3BaseException
        {
            return null;
        }

        public String getOrixSubAccountCode(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
        {
            return null;
        }

        protected WEB3AccInfoCorporationInfo createCorporationInfo(WEB3GentradeMainAccount l_mainAccount)
                throws WEB3BaseException
        {
            return null;
        }

        protected WEB3AccInfoStockOptionInfo[] createStockOptionInfo(WEB3GentradeMainAccount l_mainAccount)
                throws WEB3BaseException
        {
            return null;
        }

        protected WEB3AccInfoAccountInfo[] createForeignTransferAccountInfo(WEB3GentradeMainAccount l_mainAccount)
                throws WEB3BaseException
        {
            return null;
        }
    }

}
@
