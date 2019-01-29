head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.43.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiServiceInfoManagementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3SrvRegiServiceInfoManagementTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/08 金傑 (中訊) 新規作成
*/
package webbroker3.srvregi;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiCommCondParams;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.srvregi.data.SrvRegiDealingCommParams;
import webbroker3.srvregi.data.SrvRegiDealingCommRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiServiceInfoManagementTest extends TestBaseForMock
{

    private WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = null;
    
    private String l_strInstitutionCode = null;
    
    private String l_strBranchCode = null;
    
    private String l_strAccountCode = null;
    
    private String l_strSrvDiv = null;
    
    private String l_strUploadDiv = null;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3SrvRegiServiceInfoManagementTest.class);

    public WEB3SrvRegiServiceInfoManagementTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        this.initData();
    }
    
    protected void tearDown() throws Exception
    {
        super.checkMethodValue();
        this.l_srvRegiServiceInfoManagement = null;
        super.tearDown();
        
    }
    
    /**
     * 數據沒有檢索到並且
     * アップロード区分!=null
     *
     */
    public void test_getServiceAppliAttributeInfo_C0001()
    {
        final String STR_METHOD_NAME = "test_getServiceAppliAttributeInfo_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_strInstitutionCode = "320";
            this.l_strBranchCode = "B02";
            this.l_strAccountCode = "66666";
            this.l_strSrvDiv = "1";
            this.l_strUploadDiv = "2";

            List l_lisServiceAppliAttributeInfo = this.l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                this.l_strInstitutionCode,
                this.l_strBranchCode,
                this.l_strAccountCode,
                this.l_strSrvDiv,
                this.l_strUploadDiv);

            assertNull(l_lisServiceAppliAttributeInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * 數據沒有檢索到並且
     * アップロード区分！=null
     *
     */
    public void test_getServiceAppliAttributeInfo_C0002()
    {
        final String STR_METHOD_NAME = "test_getServiceAppliAttributeInfo_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_strInstitutionCode = "320";
            this.l_strBranchCode = "B02";
            this.l_strAccountCode = "66666";
            this.l_strSrvDiv = "1";

            List l_lisServiceAppliAttributeInfo = this.l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                this.l_strInstitutionCode,
                this.l_strBranchCode,
                this.l_strAccountCode,
                this.l_strSrvDiv,
                this.l_strUploadDiv);

            assertNull(l_lisServiceAppliAttributeInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * 數據沒有檢索到並且
     * アップロード区分!=null
     *
     */
    public void test_getServiceAppliAttributeInfo_C0003()
    {
        final String STR_METHOD_NAME = "test_getServiceAppliAttributeInfo_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_strInstitutionCode = "338";
            this.l_strBranchCode = "A01";
            this.l_strAccountCode = "1234567";
            this.l_strSrvDiv = "1234";
            this.l_strUploadDiv = "2";
            
            List l_lisServiceAppliAttributeInfo = this.l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                this.l_strInstitutionCode,
                this.l_strBranchCode,
                this.l_strAccountCode,
                this.l_strSrvDiv,
                this.l_strUploadDiv);

            assertNotNull(l_lisServiceAppliAttributeInfo);
            assertEquals(1,l_lisServiceAppliAttributeInfo.size());
            assertEquals("338",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getInstitutionCode());
            assertEquals("A01",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getBranchCode());
            assertEquals("1234567",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getAccountCode());
            assertEquals("1234",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getSrvDiv());
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * アップロード区分==null並且
     * appli_start_date < 現在日付(YYYYMMDD)並且
     * appli_end_date > 現在日付(YYYYMMDD)
     *
     */
    public void test_getServiceAppliAttributeInfo_C0004()
    {
        final String STR_METHOD_NAME = "test_getServiceAppliAttributeInfo_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_strInstitutionCode = "338";
            this.l_strBranchCode = "A01";
            this.l_strAccountCode = "1234567";
            this.l_strSrvDiv = "1234";
            
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);
            
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                     new Class[]{}, 
                     st);
            
            List l_lisServiceAppliAttributeInfo = this.l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                this.l_strInstitutionCode,
                this.l_strBranchCode,
                this.l_strAccountCode,
                this.l_strSrvDiv,
                this.l_strUploadDiv);

            assertNotNull(l_lisServiceAppliAttributeInfo);
            assertEquals(1,l_lisServiceAppliAttributeInfo.size());
            assertEquals("338",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getInstitutionCode());
            assertEquals("A01",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getBranchCode());
            assertEquals("1234567",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getAccountCode());
            assertEquals("1234",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getSrvDiv());
            
            assertTrue(WEB3DateUtility.compareToDay(((
                SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getAppliStartDate(), 
                GtlUtils.getSystemTimestamp()) < 0);
            
            assertTrue(WEB3DateUtility.compareToDay(((
                    SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getAppliEndDate(), 
                    GtlUtils.getSystemTimestamp()) > 0);
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * アップロード区分==null並且 
     * proc_div == '0' 並且
     * appli_start_date = 現在日付(YYYYMMDD)並且
     * appli_end_date = 現在日付(YYYYMMDD)
     *
     */
    public void test_getServiceAppliAttributeInfo_C0005()
    {
        final String STR_METHOD_NAME = "test_getServiceAppliAttributeInfo_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_strInstitutionCode = "340";
            this.l_strBranchCode = "A03";
            this.l_strAccountCode = "7654323";
            this.l_strSrvDiv = "4323";
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,11);
            
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                     new Class[]{}, 
                     st);
            
            List l_lisServiceAppliAttributeInfo = this.l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                this.l_strInstitutionCode,
                this.l_strBranchCode,
                this.l_strAccountCode,
                this.l_strSrvDiv,
                this.l_strUploadDiv);

            assertNotNull(l_lisServiceAppliAttributeInfo);
            assertEquals(1,l_lisServiceAppliAttributeInfo.size());
            assertEquals("340",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getInstitutionCode());
            assertEquals("A03",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getBranchCode());
            assertEquals("7654323",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getAccountCode());
            assertEquals("4323",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getSrvDiv());
            assertEquals("0",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getProcDiv());
            
            assertTrue(WEB3DateUtility.compareToDay(((
                SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getAppliStartDate(), 
                GtlUtils.getSystemTimestamp()) == 0);
            
            assertTrue(WEB3DateUtility.compareToDay(((
                    SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getAppliEndDate(), 
                    GtlUtils.getSystemTimestamp()) == 0);
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * アップロード区分==null並且
     * appli_start_date is null並且
     * appli_end_date is null
     *
     */
    public void test_getServiceAppliAttributeInfo_C0006()
    {
        final String STR_METHOD_NAME = "test_getServiceAppliAttributeInfo_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_strInstitutionCode = "341";
            this.l_strBranchCode = "A04";
            this.l_strAccountCode = "7654324";
            this.l_strSrvDiv = "4324";
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,11);
            
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                     new Class[]{}, 
                     st);
            
            List l_lisServiceAppliAttributeInfo = this.l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                this.l_strInstitutionCode,
                this.l_strBranchCode,
                this.l_strAccountCode,
                this.l_strSrvDiv,
                this.l_strUploadDiv);

            assertNotNull(l_lisServiceAppliAttributeInfo);
            assertEquals(1,l_lisServiceAppliAttributeInfo.size());
            assertEquals("341",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getInstitutionCode());
            assertEquals("A04",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getBranchCode());
            assertEquals("7654324",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getAccountCode());
            assertEquals("4324",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getSrvDiv());            
            assertNull(((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getAppliStartDate());
            assertNull(((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getAppliEndDate());
            assertNull(((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getProcDiv());
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * アップロード区分==null並且
     * 沒有檢索到數據
     *
     */
    public void test_getServiceAppliAttributeInfo_C0007()
    {
        final String STR_METHOD_NAME = "test_getServiceAppliAttributeInfo_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_strInstitutionCode = "348";
            this.l_strBranchCode = "A04";
            this.l_strAccountCode = "7654324";
            this.l_strSrvDiv = "4324";
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,11);
            
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                     new Class[]{}, 
                     st);
            
            List l_lisServiceAppliAttributeInfo = this.l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                this.l_strInstitutionCode,
                this.l_strBranchCode,
                this.l_strAccountCode,
                this.l_strSrvDiv,
                this.l_strUploadDiv);

            assertNull(l_lisServiceAppliAttributeInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * 口座コードの桁数が6である場合
     * 檢索到數據
     *
     */
    public void test_getServiceAppliAttributeInfo_C0008()
    {
        final String STR_METHOD_NAME = "test_getServiceAppliAttributeInfo_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams.setInstitutionCode("341");
            l_srvAppliAttributeParams.setBranchCode("A04");
            l_srvAppliAttributeParams.setAccountCode("7654321");
            l_srvAppliAttributeParams.setSrvDiv("4324");
            l_srvAppliAttributeParams.setAppliAttribute("2");
            l_srvAppliAttributeParams.setLastUpdater("0218");
            l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_srvAppliAttributeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            this.l_strInstitutionCode = "341";
            this.l_strBranchCode = "A04";
            this.l_strAccountCode = "765432";
            this.l_strSrvDiv = "4324";
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,11);
            
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                    "getSystemTimestamp",
                     new Class[]{}, 
                     st);
            
            List l_lisServiceAppliAttributeInfo = this.l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                this.l_strInstitutionCode,
                this.l_strBranchCode,
                this.l_strAccountCode,
                this.l_strSrvDiv,
                this.l_strUploadDiv);

            assertNotNull(l_lisServiceAppliAttributeInfo);
            assertEquals("7654321",((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getAccountCode());
            assertEquals(this.l_strBranchCode,
                ((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getBranchCode());
            assertEquals(this.l_strInstitutionCode,
                    ((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getInstitutionCode());
            assertEquals(this.l_strSrvDiv,
                    ((SrvAppliAttributeParams)l_lisServiceAppliAttributeInfo.get(0)).getSrvDiv());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    //2007/11/01
    //【サービス利用】【サービス利用ウツミ屋対応】
    //get提供形式()＝ 0 or 1 以外の場合、falseを返却する
    public void testIsCommCondCase1()
    {
        final String STR_METHOD_NAME = "testIsCommCondCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(SrvRegiMasterRow.TYPE);
            SrvRegiMasterParams l_SrvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_SrvRegiMasterParams);
            
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_SrvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_SrvRegiSetupParams.setSupplyDiv("3");
            TestDBUtility.insertWithDel(l_SrvRegiSetupParams);
            
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(333812512246L, 33381251220301L);
            WEB3SrvRegiServiceMaster l_srvServiceMaster = new WEB3SrvRegiServiceMaster(l_SrvRegiMasterParams);
            WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
            
            boolean l_blnIsCommCond = l_management.isCommCond(l_gentradeSubAccount,l_srvServiceMaster);
            assertFalse(l_blnIsCommCond);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    //get提供形式()＝ 0の場合、trueを返却する
    public void testIsCommCondCase2()
    {
        final String STR_METHOD_NAME = "testIsCommCondCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(SrvRegiMasterRow.TYPE);
            SrvRegiMasterParams l_SrvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_SrvRegiMasterParams);
            
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_SrvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_SrvRegiSetupParams.setSupplyDiv("0");
            TestDBUtility.insertWithDel(l_SrvRegiSetupParams);
            
            TestDBUtility.deleteAll(SrvRegiCommCondRow.TYPE);
            SrvRegiCommCondParams l_SrvRegiCommCondParams = TestDBUtility.getSrvRegiCommCondRow();
            l_SrvRegiCommCondParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_SrvRegiCommCondParams);
            
            TestDBUtility.deleteAll(SrvRegiDealingCommRow.TYPE);
            SrvRegiDealingCommParams l_SrvRegiDealingCommParams = new SrvRegiDealingCommParams();
            l_SrvRegiDealingCommParams.setInstitutionCode("0D");
            l_SrvRegiDealingCommParams.setBranchCode("381");
            l_SrvRegiDealingCommParams.setAccountCode("2512246");
            l_SrvRegiDealingCommParams.setAccumulateTerm("200712");
            l_SrvRegiDealingCommParams.setOrderAccProduct("01");
            l_SrvRegiDealingCommParams.setCommAmt(12);
            l_SrvRegiDealingCommParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_SrvRegiDealingCommParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_SrvRegiDealingCommParams);
            
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(333812512246L, 33381251220301L);
            WEB3SrvRegiServiceMaster l_srvServiceMaster = new WEB3SrvRegiServiceMaster(l_SrvRegiMasterParams);
            WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
            
            boolean l_blnIsCommCond = l_management.isCommCond(l_gentradeSubAccount,l_srvServiceMaster);
            assertTrue(!l_blnIsCommCond);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    //get提供形式()＝ 1 の場合、trueを返却する
    public void testIsCommCondCase3()
    {
        final String STR_METHOD_NAME = "testIsCommCondCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(SrvRegiMasterRow.TYPE);
            SrvRegiMasterParams l_SrvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_SrvRegiMasterParams);
            
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_SrvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_SrvRegiSetupParams.setSupplyDiv("1");
            TestDBUtility.insertWithDel(l_SrvRegiSetupParams);
            
            TestDBUtility.deleteAll(SrvRegiCommCondRow.TYPE);
            SrvRegiCommCondParams l_SrvRegiCommCondParams = TestDBUtility.getSrvRegiCommCondRow();
            l_SrvRegiCommCondParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_SrvRegiCommCondParams);
            
            TestDBUtility.deleteAll(SrvRegiDealingCommRow.TYPE);
            SrvRegiDealingCommParams l_SrvRegiDealingCommParams = new SrvRegiDealingCommParams();
            l_SrvRegiDealingCommParams.setInstitutionCode("0D");
            l_SrvRegiDealingCommParams.setBranchCode("381");
            l_SrvRegiDealingCommParams.setAccountCode("2512246");
            l_SrvRegiDealingCommParams.setAccumulateTerm("200712");
            l_SrvRegiDealingCommParams.setOrderAccProduct("01");
            l_SrvRegiDealingCommParams.setCommAmt(12);
            l_SrvRegiDealingCommParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_SrvRegiDealingCommParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_SrvRegiDealingCommParams);
            
            
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(333812512246L, 33381251220301L);
            WEB3SrvRegiServiceMaster l_srvServiceMaster = new WEB3SrvRegiServiceMaster(l_SrvRegiMasterParams);
            WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
            
            boolean l_blnIsCommCond = l_management.isCommCond(l_gentradeSubAccount,l_srvServiceMaster);
            assertTrue(!l_blnIsCommCond);
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
            SrvAppliAttributeParams l_srvAppliAttributeParams1 = new SrvAppliAttributeParams();
            l_srvAppliAttributeParams1.setInstitutionCode("338");
            l_srvAppliAttributeParams1.setBranchCode("A01");
            l_srvAppliAttributeParams1.setAccountCode("1234567");
            l_srvAppliAttributeParams1.setSrvDiv("1234");
            l_srvAppliAttributeParams1.setAppliAttribute("1");
            l_srvAppliAttributeParams1.setAppliStartDate(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_srvAppliAttributeParams1.setAppliEndDate(WEB3DateUtility.getDate("20070616","yyyyMMdd"));
            l_srvAppliAttributeParams1.setLastUpdater("0213");
            l_srvAppliAttributeParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_srvAppliAttributeParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams1);
            
            SrvAppliAttributeParams l_srvAppliAttributeParams2 = new SrvAppliAttributeParams();
            l_srvAppliAttributeParams2.setInstitutionCode("339");
            l_srvAppliAttributeParams2.setBranchCode("A02");
            l_srvAppliAttributeParams2.setAccountCode("7654321");
            l_srvAppliAttributeParams2.setSrvDiv("4321");
            l_srvAppliAttributeParams2.setAppliAttribute("2");
            l_srvAppliAttributeParams2.setAppliStartDate(WEB3DateUtility.getDate("20070608","yyyyMMdd"));
            l_srvAppliAttributeParams2.setAppliEndDate(WEB3DateUtility.getDate("20070617","yyyyMMdd"));
            l_srvAppliAttributeParams2.setLastUpdater("0213");
            l_srvAppliAttributeParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_srvAppliAttributeParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams2);
            
            
            SrvAppliAttributeParams l_srvAppliAttributeParams3 = new SrvAppliAttributeParams();
            l_srvAppliAttributeParams3.setInstitutionCode("340");
            l_srvAppliAttributeParams3.setBranchCode("A03");
            l_srvAppliAttributeParams3.setAccountCode("7654323");
            l_srvAppliAttributeParams3.setSrvDiv("4323");
            l_srvAppliAttributeParams3.setAppliAttribute("2");
            l_srvAppliAttributeParams3.setAppliStartDate(WEB3DateUtility.getDate("20070611","yyyyMMdd"));
            l_srvAppliAttributeParams3.setAppliEndDate(WEB3DateUtility.getDate("20070611","yyyyMMdd"));
            l_srvAppliAttributeParams3.setLastUpdater("0217");
            l_srvAppliAttributeParams3.setProcDiv("0");
            l_srvAppliAttributeParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_srvAppliAttributeParams3.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams3);
            
            SrvAppliAttributeParams l_srvAppliAttributeParams4 = new SrvAppliAttributeParams();
            l_srvAppliAttributeParams4.setInstitutionCode("341");
            l_srvAppliAttributeParams4.setBranchCode("A04");
            l_srvAppliAttributeParams4.setAccountCode("7654324");
            l_srvAppliAttributeParams4.setSrvDiv("4324");
            l_srvAppliAttributeParams4.setAppliAttribute("2");
            l_srvAppliAttributeParams4.setLastUpdater("0218");
            l_srvAppliAttributeParams4.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_srvAppliAttributeParams4.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams4);
            
            SrvAppliAttributeParams l_srvAppliAttributeParams5 = new SrvAppliAttributeParams();
            l_srvAppliAttributeParams5.setInstitutionCode("348");
            l_srvAppliAttributeParams5.setBranchCode("A04");
            l_srvAppliAttributeParams5.setAccountCode("7654324");
            l_srvAppliAttributeParams5.setSrvDiv("4324");
            l_srvAppliAttributeParams5.setAppliAttribute("2");
            l_srvAppliAttributeParams5.setLastUpdater("0218");
            l_srvAppliAttributeParams5.setProcDiv("1");
            l_srvAppliAttributeParams5.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_srvAppliAttributeParams5.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams5);
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    public void testIsNewApply()
    {
        final String STR_METHOD_NAME = "testIsNewApply()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institution);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            String l_strServiceDiv = "1";

            TestDBUtility.deleteAll(SrvRegiApplicationParams.TYPE);
            WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
            assertTrue(l_management.isNewApply(l_subAccount, l_strServiceDiv));

            TestDBUtility.deleteAll(SrvRegiApplicationParams.TYPE);
            SrvRegiApplicationParams l_srvRegiApplicationParams = TestDBUtility.getSrvRegiApplicationParams();
            l_srvRegiApplicationParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_srvRegiApplicationParams.setInstitutionCode("0D");
            l_srvRegiApplicationParams.setBranchCode(l_branchParams.getBranchCode());
            l_srvRegiApplicationParams.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);
            assertTrue(!l_management.isNewApply(l_subAccount, l_strServiceDiv));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    public void testValidateSpecialApplyCase_0001()
    {
        final String STR_METHOD_NAME = "testValidateSpecialApplyCase_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
            l_management.validateSpecialApply(null, "1", "1", "1", true);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    public void testValidateSpecialApplyCase_0002()
    {
        final String STR_METHOD_NAME = "testValidateSpecialApplyCase_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasterParams);
            WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
            l_management.validateSpecialApply(l_srvRegiServiceMaster, "0D", "123", "111111", true);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03019, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    public void testValidateSpecialApplyCase_0003()
    {
        final String STR_METHOD_NAME = "testValidateSpecialApplyCase_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            l_srvRegiMasterParams.setSrvDiv("12");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("123");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasterParams);
            WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
            l_management.validateSpecialApply(l_srvRegiServiceMaster, "0D", "123", "111", true);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03027, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }


    public void testValidateSpecialApplyCase_0004()
    {
        final String STR_METHOD_NAME = "testValidateSpecialApplyCase_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            l_srvRegiMasterParams.setSrvDiv("12");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
//            l_otherOrgInfoAdminParams.setSrvDiv("12");
//            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
//            l_otherOrgInfoAdminParams.setBranchCode("123");
//            l_otherOrgInfoAdminParams.setAccountCode("111");
//            l_otherOrgInfoAdminParams.setStatus("0");
//            l_otherOrgInfoAdminParams.setSequenceNumber(2);
//            l_otherOrgInfoAdminParams.setId("1001");
//            l_otherOrgInfoAdminParams.setPassword("123");
//            l_otherOrgInfoAdminParams.setLastUpdater("1");
//            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode(null);
            l_otherOrgInfoAdminParams.setBranchCode("123");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(3);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasterParams);
            WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
            l_management.validateSpecialApply(l_srvRegiServiceMaster, "0D", "123", "111", true);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }


    public void testValidateSpecialApplyCase_0005()
    {
        final String STR_METHOD_NAME = "testValidateSpecialApplyCase_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasterParams);
            WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
            l_management.validateSpecialApply(l_srvRegiServiceMaster, "0D", "123", "111111", false);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03019, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }


    public void testValidateSpecialApplyCase_0006()
    {
        final String STR_METHOD_NAME = "testValidateSpecialApplyCase_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            l_srvRegiMasterParams.setSrvDiv("12");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("123");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasterParams);
            WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
            l_management.validateSpecialApply(l_srvRegiServiceMaster, "0D", "123", "111", false);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    public void testValidateSpecialApplyCase_0007()
    {
        final String STR_METHOD_NAME = "testValidateSpecialApplyCase_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSpecialProcessDiv("0");
            l_srvRegiMasterParams.setSrvDiv("12");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("123");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasterParams);
            WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
            l_management.validateSpecialApply(l_srvRegiServiceMaster, "0D", "123", "111", false);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    public void testGetSpecialProcessSrvMasterList_case0001()
    {
        final String STR_METHOD_NAME =
            "testGetSpecialProcessSrvMasterList_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.l_srvRegiServiceInfoManagement =
                new WEB3SrvRegiServiceInfoManagement();

            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvStatus("9");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            WEB3SrvRegiServiceMaster[] l_srvRegiServiceMasters =
                l_srvRegiServiceInfoManagement.getSpecialProcessSrvMasterList(
                    "0D",
                    "1");

            assertEquals(1, l_srvRegiServiceMasters.length);
            assertEquals("0D", l_srvRegiServiceMasters[0].getInstitutionCode());
            assertEquals("1234", l_srvRegiServiceMasters[0].getSrvDiv());
            assertEquals("9", l_srvRegiServiceMasters[0].getStatus());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSpecialProcessSrvMasterList_case0002()
    {
        final String STR_METHOD_NAME =
            "testGetSpecialProcessSrvMasterList_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.l_srvRegiServiceInfoManagement =
                new WEB3SrvRegiServiceInfoManagement();

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvStatus("9");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvDiv("5678");
            l_srvRegiMasterParams.setSrvStatus("1");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            WEB3SrvRegiServiceMaster[] l_srvRegiServiceMasters =
                l_srvRegiServiceInfoManagement.getSpecialProcessSrvMasterList(
                    "0D",
                    "1");

            assertEquals(2, l_srvRegiServiceMasters.length);

            assertEquals("0D", l_srvRegiServiceMasters[0].getInstitutionCode());
            assertEquals("1234", l_srvRegiServiceMasters[0].getSrvDiv());
            assertEquals("9", l_srvRegiServiceMasters[0].getStatus());

            assertEquals("0D", l_srvRegiServiceMasters[1].getInstitutionCode());
            assertEquals("5678", l_srvRegiServiceMasters[1].getSrvDiv());
            assertEquals("1", l_srvRegiServiceMasters[1].getStatus());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSpecialProcessSrvMasterList_case0003()
    {
        final String STR_METHOD_NAME =
            "testGetSpecialProcessSrvMasterList_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.l_srvRegiServiceInfoManagement =
                new WEB3SrvRegiServiceInfoManagement();

            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvStatus("9");
            l_srvRegiMasterParams.setSpecialProcessDiv("0");
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            l_srvRegiServiceInfoManagement.getSpecialProcessSrvMasterList("0D", "1");

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_00982,
                l_exBE.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSpecialProcessSrvMasterList_case0004()
    {
        final String STR_METHOD_NAME =
            "testGetSpecialProcessSrvMasterList_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.l_srvRegiServiceInfoManagement =
                new WEB3SrvRegiServiceInfoManagement();

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);

            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus("9");
            l_srvRegiMasterParams.setSpecialProcessDiv(null);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("5678");
            l_srvRegiMasterParams.setSrvStatus("9");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            WEB3SrvRegiServiceMaster[] l_srvRegiServiceMasters =
                l_srvRegiServiceInfoManagement.getSpecialProcessSrvMasterList("0D", null);

            assertEquals(1, l_srvRegiServiceMasters.length);

            assertEquals("0D", l_srvRegiServiceMasters[0].getInstitutionCode());
            assertEquals("1234", l_srvRegiServiceMasters[0].getSrvDiv());
            assertEquals("9", l_srvRegiServiceMasters[0].getStatus());

            Field l_field =
                WEB3SrvRegiServiceMaster.class.getDeclaredField("srvMasterParams");
            l_field.setAccessible(true);
            SrvRegiMasterParams l_resultSrvRegiMasterParams =
                (SrvRegiMasterParams)l_field.get(l_srvRegiServiceMasters[0]);

            assertEquals(null, l_resultSrvRegiMasterParams.getSpecialProcessDiv());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSpecialProcessSrvMasterList_case0005()
    {
        final String STR_METHOD_NAME =
            "testGetSpecialProcessSrvMasterList_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.l_srvRegiServiceInfoManagement =
                new WEB3SrvRegiServiceInfoManagement();

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);

            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus("9");
            l_srvRegiMasterParams.setSpecialProcessDiv(null);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("5678");
            l_srvRegiMasterParams.setSrvStatus("9");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            WEB3SrvRegiServiceMaster[] l_srvRegiServiceMasters =
                l_srvRegiServiceInfoManagement.getSpecialProcessSrvMasterList("0D", "1");

            assertEquals(1, l_srvRegiServiceMasters.length);

            assertEquals("0D", l_srvRegiServiceMasters[0].getInstitutionCode());
            assertEquals("5678", l_srvRegiServiceMasters[0].getSrvDiv());
            assertEquals("9", l_srvRegiServiceMasters[0].getStatus());

            Field l_field =
                WEB3SrvRegiServiceMaster.class.getDeclaredField("srvMasterParams");
            l_field.setAccessible(true);
            SrvRegiMasterParams l_resultSrvRegiMasterParams =
                (SrvRegiMasterParams)l_field.get(l_srvRegiServiceMasters[0]);

            assertEquals("1", l_resultSrvRegiMasterParams.getSpecialProcessDiv());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

}
@
