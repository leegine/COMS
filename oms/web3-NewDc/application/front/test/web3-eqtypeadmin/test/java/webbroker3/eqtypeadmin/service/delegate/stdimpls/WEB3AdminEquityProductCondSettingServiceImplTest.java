head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.08.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityProductCondSettingServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.eqtypeadmin.data.EqtypeProductConditionParams;
import webbroker3.eqtypeadmin.data.EqtypeProductConditionRow;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfigUnit;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityProductCondSettingServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityProductCondSettingServiceImplTest.class);

    public WEB3AdminEquityProductCondSettingServiceImplTest(String arg0)
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
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondSettingServiceImpl.getKey(String, String)'
     */
    public void testGetKeyCase1()
    {
        final String STR_METHOD_NAME = "testGetKeyCase1";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityProductCondSettingServiceImpl l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImpl();
            assertEquals("01AAAA", l_serviceImpl.getKey("1", "AAAA"));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetKeyCase2()
    {
        final String STR_METHOD_NAME = "testGetKeyCase2";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityProductCondSettingServiceImpl l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImpl();
            assertEquals("10AAAA", l_serviceImpl.getKey("10", "AAAA"));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateDepositRateList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateDepositRateList_C0001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String[] l_strMarketCodeList = new String[]{"1", "10"};
            HashMap l_map = new HashMap();
            EqtypeProductConditionParams l_eqtypeProductConditionParams1 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams1.setEqtypeProductConditionId(50051L);
            l_map.put("0140", l_eqtypeProductConditionParams1);
            EqtypeProductConditionParams l_eqtypeProductConditionParams2 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams2.setEqtypeProductConditionId(50081L);
            l_map.put("0141", l_eqtypeProductConditionParams2);
            EqtypeProductConditionParams l_eqtypeProductConditionParams3 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(60061L);
            l_map.put("0142", l_eqtypeProductConditionParams3);
            EqtypeProductConditionParams l_eqtypeProductConditionParams4 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams4.setEqtypeProductConditionId(60081L);
            l_map.put("0143", l_eqtypeProductConditionParams4);
            EqtypeProductConditionParams l_eqtypeProductConditionParams5 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams5.setEqtypeProductConditionId(50052L);
            l_map.put("1040", l_eqtypeProductConditionParams5);
            EqtypeProductConditionParams l_eqtypeProductConditionParams6 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams6.setEqtypeProductConditionId(50082L);
            l_map.put("1041", l_eqtypeProductConditionParams6);
            EqtypeProductConditionParams l_eqtypeProductConditionParams7 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams7.setEqtypeProductConditionId(60062L);
            l_map.put("1042", l_eqtypeProductConditionParams7);
            EqtypeProductConditionParams l_eqtypeProductConditionParams8 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams8.setEqtypeProductConditionId(60082L);
            l_map.put("1043", l_eqtypeProductConditionParams8);
            
            WEB3AdminEquityProductCondSettingServiceImplForTest l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTest();
            
                l_serviceImpl.createDepositRateList(
                    l_strMarketCodeList,
                    null,
                    null,
                    l_map);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateStockMarginInfoList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateStockMarginInfoList_C0001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(80808L);
            l_branchParams.setMarginSysDiv("1");
            l_branchParams.setMarginGenDiv("1");
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(80808L);
            
            String[] l_strMarketCodeList = new String[]{"1", "10"};
            HashMap l_map = new HashMap();
            EqtypeProductConditionParams l_eqtypeProductConditionParams1 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams1.setEqtypeProductConditionId(5005L);
            l_map.put("0138", l_eqtypeProductConditionParams1);
            EqtypeProductConditionParams l_eqtypeProductConditionParams2 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams2.setEqtypeProductConditionId(5008L);
            l_map.put("0139", l_eqtypeProductConditionParams2);
            EqtypeProductConditionParams l_eqtypeProductConditionParams3 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(6006L);
            l_map.put("1038", l_eqtypeProductConditionParams3);
            EqtypeProductConditionParams l_eqtypeProductConditionParams4 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams4.setEqtypeProductConditionId(6008);
            l_map.put("1039", l_eqtypeProductConditionParams4);
            
            WEB3AdminEquityProductCondSettingServiceImplForTest l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTest();
            
            l_serviceImpl.createStockMarginInfoList(
                l_branch,
                l_strMarketCodeList,
                null,
                null,
                l_map);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSubstituteSecurityInfoListt_C0001()
    {
        final String STR_METHOD_NAME = "testCreateSubstituteSecurityInfoListt_C0001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(80808L);
            l_branchParams.setMarginSysDiv("1");
            l_branchParams.setMarginGenDiv("1");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("1");
            l_institutionParams.setStockEvaluation("1");
            TestDBUtility.insertWithDel(l_institutionParams);            
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(80808L);
            
            HashMap l_map = new HashMap();
            EqtypeProductConditionParams l_eqtypeProductConditionParams1 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams1.setEqtypeProductConditionId(5005L);
            l_map.put("0044", l_eqtypeProductConditionParams1);
            EqtypeProductConditionParams l_eqtypeProductConditionParams2 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams2.setEqtypeProductConditionId(5008L);
            l_map.put("0046", l_eqtypeProductConditionParams2);
            EqtypeProductConditionParams l_eqtypeProductConditionParams3 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(5009L);
            l_map.put("0045", l_eqtypeProductConditionParams3);
            
            WEB3AdminEquityProductCondSettingServiceImplForTest l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTest();
            
            l_serviceImpl.createSubstituteSecurityInfoList(
                l_branch,
                null,
                l_map);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreatePriceInfoList_C0001()
    {
        final String STR_METHOD_NAME = "testCreatePriceInfoList_C0001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(80808L);
            l_branchParams.setMarginSysDiv("1");
            l_branchParams.setMarginGenDiv("1");
            TestDBUtility.insertWithDel(l_branchParams);       
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(80808L);
            
            String[] l_strMarketCodeList = new String[]{"1", "10"};            
            
            HashMap l_map = new HashMap();
            EqtypeProductConditionParams l_eqtypeProductConditionParams0 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams0.setEqtypeProductConditionId(5000L);
            l_map.put("0045", l_eqtypeProductConditionParams0);
            
            EqtypeProductConditionParams l_eqtypeProductConditionParams1 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams1.setEqtypeProductConditionId(5001L);
            l_map.put("0147", l_eqtypeProductConditionParams1);
            EqtypeProductConditionParams l_eqtypeProductConditionParams2 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams2.setEqtypeProductConditionId(5002L);
            l_map.put("0156", l_eqtypeProductConditionParams2);
            EqtypeProductConditionParams l_eqtypeProductConditionParams3 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(5003L);
            l_map.put("0148", l_eqtypeProductConditionParams3);
            EqtypeProductConditionParams l_eqtypeProductConditionParams4 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams4.setEqtypeProductConditionId(5004L);
            l_map.put("0151", l_eqtypeProductConditionParams4);
            EqtypeProductConditionParams l_eqtypeProductConditionParams5 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams5.setEqtypeProductConditionId(5005L);
            l_map.put("0152", l_eqtypeProductConditionParams5);
            EqtypeProductConditionParams l_eqtypeProductConditionParams6 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams6.setEqtypeProductConditionId(5006L);
            l_map.put("0153", l_eqtypeProductConditionParams6);
            
            EqtypeProductConditionParams l_eqtypeProductConditionParams7 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams7.setEqtypeProductConditionId(5007L);
            l_map.put("1047", l_eqtypeProductConditionParams7);
            EqtypeProductConditionParams l_eqtypeProductConditionParams8 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams8.setEqtypeProductConditionId(5008L);
            l_map.put("1056", l_eqtypeProductConditionParams8);
            EqtypeProductConditionParams l_eqtypeProductConditionParams9 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams9.setEqtypeProductConditionId(5009L);
            l_map.put("1048", l_eqtypeProductConditionParams9);
            EqtypeProductConditionParams l_eqtypeProductConditionParams10 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams10.setEqtypeProductConditionId(5010L);
            l_map.put("1051", l_eqtypeProductConditionParams10);
            EqtypeProductConditionParams l_eqtypeProductConditionParams11 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams11.setEqtypeProductConditionId(5011L);
            l_map.put("1052", l_eqtypeProductConditionParams11);
            EqtypeProductConditionParams l_eqtypeProductConditionParams12 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams12.setEqtypeProductConditionId(5012L);
            l_map.put("1053", l_eqtypeProductConditionParams12);

            WEB3AdminEquityProductCondSettingServiceImplForTest l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTest();
                  
            l_serviceImpl.createPriceInfoList(
                l_branch,
                l_strMarketCodeList,
                null,
                null,
                null,
                l_map);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateTradingRegulationList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateTradingRegulationList_C0001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(80808L);
            l_branchParams.setMarginSysDiv("1");
            l_branchParams.setMarginGenDiv("1");
            l_branchParams.setMstkDiv("1");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3333L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(80808L);
            
            String[] l_strMarketCodeList = new String[]{"1"};
            
            HashMap l_tradedProductsTwoDaysLater = new HashMap();
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                new EqtypeTradedProductParams();
            l_eqtypeTradedProductParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_eqtypeTradedProductParams.setMarketId(3333L);
            l_tradedProductsTwoDaysLater.put("1", l_eqtypeTradedProductParams);
            
            HashMap l_map = new HashMap();
            EqtypeProductConditionParams l_eqtypeProductConditionParams0 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams0.setEqtypeProductConditionId(5000L);
            l_map.put("000", l_eqtypeProductConditionParams0);       
            EqtypeProductConditionParams l_eqtypeProductConditionParams1 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams1.setEqtypeProductConditionId(5001L);
            l_map.put("001", l_eqtypeProductConditionParams1);
            EqtypeProductConditionParams l_eqtypeProductConditionParams2 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams2.setEqtypeProductConditionId(5002L);
            l_map.put("002", l_eqtypeProductConditionParams2);
                     
            EqtypeProductConditionParams l_eqtypeProductConditionParams3 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(5003L);
            l_map.put("013", l_eqtypeProductConditionParams3);
            EqtypeProductConditionParams l_eqtypeProductConditionParams4 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams4.setEqtypeProductConditionId(5004L);
            l_map.put("014", l_eqtypeProductConditionParams4);
            EqtypeProductConditionParams l_eqtypeProductConditionParams5 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams5.setEqtypeProductConditionId(5005L);
            l_map.put("015", l_eqtypeProductConditionParams5);
            EqtypeProductConditionParams l_eqtypeProductConditionParams6 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams6.setEqtypeProductConditionId(5006L);
            l_map.put("016", l_eqtypeProductConditionParams6);           
            EqtypeProductConditionParams l_eqtypeProductConditionParams7 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams7.setEqtypeProductConditionId(5007L);
            l_map.put("017", l_eqtypeProductConditionParams7);
            EqtypeProductConditionParams l_eqtypeProductConditionParams8 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams8.setEqtypeProductConditionId(5008L);
            l_map.put("018", l_eqtypeProductConditionParams8);
            EqtypeProductConditionParams l_eqtypeProductConditionParams9 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams9.setEqtypeProductConditionId(5009L);
            l_map.put("019", l_eqtypeProductConditionParams9);
            EqtypeProductConditionParams l_eqtypeProductConditionParams10 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams10.setEqtypeProductConditionId(5010L);
            l_map.put("0110", l_eqtypeProductConditionParams10);
            EqtypeProductConditionParams l_eqtypeProductConditionParams11 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams11.setEqtypeProductConditionId(5011L);
            l_map.put("0111", l_eqtypeProductConditionParams11);
            EqtypeProductConditionParams l_eqtypeProductConditionParams12 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams12.setEqtypeProductConditionId(5012L);
            l_map.put("0112", l_eqtypeProductConditionParams12);
            EqtypeProductConditionParams l_eqtypeProductConditionParams13 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams13.setEqtypeProductConditionId(5013L);
            l_map.put("0113", l_eqtypeProductConditionParams13);
            EqtypeProductConditionParams l_eqtypeProductConditionParams14 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams14.setEqtypeProductConditionId(5014L);
            l_map.put("0114", l_eqtypeProductConditionParams14);
            EqtypeProductConditionParams l_eqtypeProductConditionParams15 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams15.setEqtypeProductConditionId(5015L);
            l_map.put("0115", l_eqtypeProductConditionParams15);
            EqtypeProductConditionParams l_eqtypeProductConditionParams16 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams16.setEqtypeProductConditionId(5016L);
            l_map.put("0116", l_eqtypeProductConditionParams16);           
            EqtypeProductConditionParams l_eqtypeProductConditionParams17 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams17.setEqtypeProductConditionId(5017L);
            l_map.put("0117", l_eqtypeProductConditionParams17);
            EqtypeProductConditionParams l_eqtypeProductConditionParams18 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams18.setEqtypeProductConditionId(5018L);
            l_map.put("0118", l_eqtypeProductConditionParams18);
            EqtypeProductConditionParams l_eqtypeProductConditionParams19 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams19.setEqtypeProductConditionId(5019L);
            l_map.put("0119", l_eqtypeProductConditionParams19);
            EqtypeProductConditionParams l_eqtypeProductConditionParams20 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams20.setEqtypeProductConditionId(5020L);
            l_map.put("0120", l_eqtypeProductConditionParams20);
            EqtypeProductConditionParams l_eqtypeProductConditionParams21 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams21.setEqtypeProductConditionId(5021L);
            l_map.put("0121", l_eqtypeProductConditionParams21);
            EqtypeProductConditionParams l_eqtypeProductConditionParams22 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams22.setEqtypeProductConditionId(5022L);
            l_map.put("0122", l_eqtypeProductConditionParams22);
            EqtypeProductConditionParams l_eqtypeProductConditionParams23 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams23.setEqtypeProductConditionId(5023L);
            l_map.put("0123", l_eqtypeProductConditionParams23);
            EqtypeProductConditionParams l_eqtypeProductConditionParams24 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams24.setEqtypeProductConditionId(5024L);
            l_map.put("0124", l_eqtypeProductConditionParams24);
            EqtypeProductConditionParams l_eqtypeProductConditionParams25 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams25.setEqtypeProductConditionId(5025L);
            l_map.put("0125", l_eqtypeProductConditionParams25);
            EqtypeProductConditionParams l_eqtypeProductConditionParams26 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams26.setEqtypeProductConditionId(5026L);
            l_map.put("0126", l_eqtypeProductConditionParams26);
            
            EqtypeProductConditionParams l_eqtypeProductConditionParams27 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams27.setEqtypeProductConditionId(5027L);
            l_map.put("0127", l_eqtypeProductConditionParams27);
            EqtypeProductConditionParams l_eqtypeProductConditionParams28 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams28.setEqtypeProductConditionId(5028L);
            l_map.put("0128", l_eqtypeProductConditionParams28);   

            WEB3AdminEquityProductCondSettingServiceImplForTest l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTest();
                  
            l_serviceImpl.createTradingRegulationList(
                l_branch,
                l_strMarketCodeList,
                null,
                null,
                null,
                l_tradedProductsTwoDaysLater,
                l_map);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateBasicInfoList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateBasicInfoList_C0001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(80808L);
            l_branchParams.setMstkDiv("1");
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(80808L);
            
            String[] l_strMarketCodeList = new String[]{"1"};
            
            HashMap l_tradedProductsTwoDaysLater = new HashMap();
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                new EqtypeTradedProductParams();
//            l_eqtypeTradedProductParams.setMiniStockCanDealt(BooleanEnum.TRUE);
//            l_eqtypeTradedProductParams.setMarketId(3333L);
            l_tradedProductsTwoDaysLater.put("1", l_eqtypeTradedProductParams);
            
            HashMap l_map = new HashMap();
            EqtypeProductConditionParams l_eqtypeProductConditionParams0 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams0.setEqtypeProductConditionId(5000L);
            l_map.put("0029", l_eqtypeProductConditionParams0);       
            EqtypeProductConditionParams l_eqtypeProductConditionParams1 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams1.setEqtypeProductConditionId(5001L);
            l_map.put("0054", l_eqtypeProductConditionParams1);
            EqtypeProductConditionParams l_eqtypeProductConditionParams2 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams2.setEqtypeProductConditionId(5002L);
            l_map.put("0055", l_eqtypeProductConditionParams2);                    
            EqtypeProductConditionParams l_eqtypeProductConditionParams3 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(5003L);
            l_map.put("0030", l_eqtypeProductConditionParams3);
            
            EqtypeProductConditionParams l_eqtypeProductConditionParams4 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams4.setEqtypeProductConditionId(5004L);
            l_map.put("0131", l_eqtypeProductConditionParams4);
            EqtypeProductConditionParams l_eqtypeProductConditionParams5 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams5.setEqtypeProductConditionId(5005L);
            l_map.put("0132", l_eqtypeProductConditionParams5);
            EqtypeProductConditionParams l_eqtypeProductConditionParams6 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams6.setEqtypeProductConditionId(5006L);
            l_map.put("0133", l_eqtypeProductConditionParams6);           
            EqtypeProductConditionParams l_eqtypeProductConditionParams7 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams7.setEqtypeProductConditionId(5007L);
            l_map.put("0134", l_eqtypeProductConditionParams7);
            
            EqtypeProductConditionParams l_eqtypeProductConditionParams8 =
                new EqtypeProductConditionParams();
            l_eqtypeProductConditionParams8.setEqtypeProductConditionId(5008L);
            l_map.put("0035", l_eqtypeProductConditionParams8);

            WEB3AdminEquityProductCondSettingServiceImplForTest l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTest();
                  
            l_serviceImpl.createBasicInfoList(
                l_branch,
                l_strMarketCodeList,
                null,
                null,
                null,
                null,
                l_tradedProductsTwoDaysLater,
                l_map);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }
    
    private class WEB3AdminEquityProductCondSettingServiceImplForTest extends WEB3AdminEquityProductCondSettingServiceImpl
    {
        public WEB3AdminPMProductCondConfigUnit createProductCondConfigUnit(
            String l_strMarketCode,
            String l_largeItemDiv,
            String l_strSmallItemDivList,
            ProductParams l_productParams,
            WEB3EquityProduct l_equityProduct,
            EqtypeTradedProductParams l_tradedProductsToday,
            EqtypeTradedProductParams l_tradedProductsTwoDaysLater,
            EqtypeProductConditionParams l_eqtypeProductConditionParams)
            throws DataFindException, DataQueryException,
            DataNetworkException, WEB3BaseException, NotFoundException
        {
            final String STR_METHOD_NAME =
                "createProductCondConfigUnit(String, String, String, Product, WEB3EquityProduct, "
                + "EqtypeTradedProductParams, EqtypeTradedProductParams, "
                + "EqtypeProductConditionParams)";
            log.entering(STR_METHOD_NAME + " ForTest");
            
            System.out.println(l_eqtypeProductConditionParams.getEqtypeProductConditionId());
            
            log.entering(STR_METHOD_NAME + " ForTest");
            return new WEB3AdminPMProductCondConfigUnit();
        }
        
        protected String[] createListedMarketCodeList(
            HashMap l_tradedProductsToday,
            HashMap l_tradedProductsNextDay)
        {
            final String STR_METHOD_NAME = "createListedMarketCodeList(HashMap, HashMap)";
            log.entering(STR_METHOD_NAME + " ForTest");
                      
            String[] l_strMarketCodeList = new String[]{"1"};
            
            log.exiting(STR_METHOD_NAME + " ForTest");
            return l_strMarketCodeList;
        }

    }
        
    
    public void testValidateConditionSettingCase1()
    {
        final String STR_METHOD_NAME = "testValidateConditionSettingCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionRowParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionRowParams.setInstitutionCode("0D");
            l_AdminPermissionRowParams.setPermissionLevel("770");
            l_AdminPermissionRowParams.setTransactionCategory("C0102");
            l_AdminPermissionRowParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionRowParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_AdministratorParams = TestDBUtility.getAdministratorRow();
            l_AdministratorParams.setAdministratorId(33381330001L);
            l_AdministratorParams.setInstitutionCode("0D");
            l_AdministratorParams.setBranchCode("381");
            l_AdministratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorParams);
            
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(new WEB3Administrator(l_AdministratorParams));
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketId(3303);
            l_MarketParams.setMarketCode("33");
            TestDBUtility.insertWithDel(l_MarketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_EqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_EqtypeTradedProductParams.setProductId(3304148080000L);
            l_EqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_EqtypeTradedProductUpdqParams = new EqtypeTradedProductUpdqParams();
            l_EqtypeTradedProductUpdqParams.setProductId(3304148080000L);
            l_EqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_EqtypeTradedProductUpdqParams.setInstitutionCode("0D");
            l_EqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_EqtypeTradedProductUpdqParams.setValidUntilBizDate("20080618");
            l_EqtypeTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setListType("1");
            l_EqtypeTradedProductUpdqParams.setNewListType("1");
            l_EqtypeTradedProductUpdqParams.setListedDate(WEB3DateUtility.getDate("20080618", "yyyyMMdd"));
            l_EqtypeTradedProductUpdqParams.setMarginableFlag(BooleanEnum.FALSE);
            l_EqtypeTradedProductUpdqParams.setShortableFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setLotSize(10);
            l_EqtypeTradedProductUpdqParams.setLastClosingPrice(100);
            l_EqtypeTradedProductUpdqParams.setMiniStockFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setBasePrice(100);
            l_EqtypeTradedProductUpdqParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_EqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductUpdqParams);
            
            EqtypeProductConditionParams l_EqtypeProductConditionParams = new EqtypeProductConditionParams();
            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
            l_EqtypeProductConditionParams.setEqtypeProductConditionId(111);
            l_EqtypeProductConditionParams.setInstitutionCode("0D");
            l_EqtypeProductConditionParams.setProductCode("0");
            l_EqtypeProductConditionParams.setProductId(3304148080000L);
            l_EqtypeProductConditionParams.setMarketId(3303L);
            l_EqtypeProductConditionParams.setMarketCode("33");
            l_EqtypeProductConditionParams.setTableName("AA");
            l_EqtypeProductConditionParams.setColumnName("bb");
            l_EqtypeProductConditionParams.setSmallItemDiv("1");
            l_EqtypeProductConditionParams.setLargeItemDiv("1");
            l_EqtypeProductConditionParams.setDeleteFlg("0");
            l_EqtypeProductConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_EqtypeProductConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_EqtypeProductConditionParams);
            
            WEB3AdminPMProductCondConfigUnit l_unit = new WEB3AdminPMProductCondConfigUnit();
            l_unit.smallItemDiv = "1";
            l_unit.marketCode = "1";
            WEB3AdminPMProductCondConfigUnit[] l_units = new WEB3AdminPMProductCondConfigUnit[1];
            l_units[0] = l_unit;
            
            WEB3AdminEquityProductCondSettingServiceImplForTests l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTests();
            WEB3AdminPMProductCondConfConfirmRequestTest l_request = new WEB3AdminPMProductCondConfConfirmRequestTest();
            l_request.productCode = "0";
            l_request.tradingRegulationList = l_units;
            l_request.basicInfoList = l_units;
            l_request.stockMarginInfoList = l_units;
            l_request.depositRateList = l_units;
            l_request.substituteSecurityInfoList = l_units;
            l_request.priceInfoList = l_units;
            l_serviceImpl.validateConditionSetting(l_request);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateConditionSettingCase2()
    {
        final String STR_METHOD_NAME = "testValidateConditionSettingCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionRowParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionRowParams.setInstitutionCode("0D");
            l_AdminPermissionRowParams.setPermissionLevel("770");
            l_AdminPermissionRowParams.setTransactionCategory("C0102");
            l_AdminPermissionRowParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionRowParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_AdministratorParams = TestDBUtility.getAdministratorRow();
            l_AdministratorParams.setAdministratorId(33381330001L);
            l_AdministratorParams.setInstitutionCode("0D");
            l_AdministratorParams.setBranchCode("381");
            l_AdministratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorParams);
            
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(new WEB3Administrator(l_AdministratorParams));
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketId(3303);
            l_MarketParams.setMarketCode("33");
            TestDBUtility.insertWithDel(l_MarketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_EqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_EqtypeTradedProductParams.setProductId(3304148080000L);
            l_EqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_EqtypeTradedProductUpdqParams = new EqtypeTradedProductUpdqParams();
            l_EqtypeTradedProductUpdqParams.setProductId(3304148080000L);
            l_EqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_EqtypeTradedProductUpdqParams.setInstitutionCode("0D");
            l_EqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_EqtypeTradedProductUpdqParams.setValidUntilBizDate("20080618");
            l_EqtypeTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setListType("1");
            l_EqtypeTradedProductUpdqParams.setNewListType("1");
            l_EqtypeTradedProductUpdqParams.setListedDate(WEB3DateUtility.getDate("20080618", "yyyyMMdd"));
            l_EqtypeTradedProductUpdqParams.setMarginableFlag(BooleanEnum.FALSE);
            l_EqtypeTradedProductUpdqParams.setShortableFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setLotSize(10);
            l_EqtypeTradedProductUpdqParams.setLastClosingPrice(100);
            l_EqtypeTradedProductUpdqParams.setMiniStockFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setBasePrice(100);
            l_EqtypeTradedProductUpdqParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_EqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductUpdqParams);
            
            EqtypeProductConditionParams l_EqtypeProductConditionParams = new EqtypeProductConditionParams();
            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
            l_EqtypeProductConditionParams.setEqtypeProductConditionId(111);
            l_EqtypeProductConditionParams.setInstitutionCode("0D");
            l_EqtypeProductConditionParams.setProductCode("0");
            l_EqtypeProductConditionParams.setProductId(3304148080000L);
            l_EqtypeProductConditionParams.setMarketId(3303L);
            l_EqtypeProductConditionParams.setMarketCode("33");
            l_EqtypeProductConditionParams.setTableName("AA");
            l_EqtypeProductConditionParams.setColumnName("bb");
            l_EqtypeProductConditionParams.setSmallItemDiv("1");
            l_EqtypeProductConditionParams.setLargeItemDiv("1");
            l_EqtypeProductConditionParams.setDeleteFlg("0");
            l_EqtypeProductConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_EqtypeProductConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_EqtypeProductConditionParams);
            
            WEB3AdminPMProductCondConfigUnit l_unit = new WEB3AdminPMProductCondConfigUnit();
            l_unit.smallItemDiv = "1";
            l_unit.marketCode = "1";
            WEB3AdminPMProductCondConfigUnit[] l_units = new WEB3AdminPMProductCondConfigUnit[1];
            l_units[0] = l_unit;
            
            WEB3AdminPMProductCondConfigUnit l_unit1 = new WEB3AdminPMProductCondConfigUnit();
            l_unit1.smallItemDiv = "27";
            l_unit1.marketCode = "1";
            WEB3AdminPMProductCondConfigUnit[] l_units1 = new WEB3AdminPMProductCondConfigUnit[1];
            l_units1[0] = l_unit1;
            
            WEB3AdminPMProductCondConfigUnit l_unit2 = new WEB3AdminPMProductCondConfigUnit();
            l_unit2.smallItemDiv = "35";
            l_unit2.marketCode = "1";
            WEB3AdminPMProductCondConfigUnit[] l_units2 = new WEB3AdminPMProductCondConfigUnit[1];
            l_units2[0] = l_unit2;
            
            WEB3AdminEquityProductCondSettingServiceImplForTests l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTests();
            WEB3AdminPMProductCondConfConfirmRequestTest l_request = new WEB3AdminPMProductCondConfConfirmRequestTest();
            l_request.productCode = "0";
            l_request.tradingRegulationList = l_units1;
            l_request.basicInfoList = l_units2;
            l_request.stockMarginInfoList = l_units;
            l_request.depositRateList = l_units;
            l_request.substituteSecurityInfoList = l_units;
            l_request.priceInfoList = l_units;
            l_serviceImpl.validateConditionSetting(l_request);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateConditionSettingCase3()
    {
        final String STR_METHOD_NAME = "testValidateConditionSettingCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionRowParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionRowParams.setInstitutionCode("0D");
            l_AdminPermissionRowParams.setPermissionLevel("770");
            l_AdminPermissionRowParams.setTransactionCategory("C0102");
            l_AdminPermissionRowParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionRowParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_AdministratorParams = TestDBUtility.getAdministratorRow();
            l_AdministratorParams.setAdministratorId(33381330001L);
            l_AdministratorParams.setInstitutionCode("0D");
            l_AdministratorParams.setBranchCode("381");
            l_AdministratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorParams);
            
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(new WEB3Administrator(l_AdministratorParams));
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketId(3303);
            l_MarketParams.setMarketCode("33");
            TestDBUtility.insertWithDel(l_MarketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_EqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_EqtypeTradedProductParams.setProductId(3304148080000L);
            l_EqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_EqtypeTradedProductUpdqParams = new EqtypeTradedProductUpdqParams();
            l_EqtypeTradedProductUpdqParams.setProductId(3304148080000L);
            l_EqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_EqtypeTradedProductUpdqParams.setInstitutionCode("0D");
            l_EqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_EqtypeTradedProductUpdqParams.setValidUntilBizDate("20080618");
            l_EqtypeTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setListType("1");
            l_EqtypeTradedProductUpdqParams.setNewListType("1");
            l_EqtypeTradedProductUpdqParams.setListedDate(WEB3DateUtility.getDate("20080618", "yyyyMMdd"));
            l_EqtypeTradedProductUpdqParams.setMarginableFlag(BooleanEnum.FALSE);
            l_EqtypeTradedProductUpdqParams.setShortableFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setLotSize(10);
            l_EqtypeTradedProductUpdqParams.setLastClosingPrice(100);
            l_EqtypeTradedProductUpdqParams.setMiniStockFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setBasePrice(100);
            l_EqtypeTradedProductUpdqParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_EqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductUpdqParams);
            
            EqtypeProductConditionParams l_EqtypeProductConditionParams = new EqtypeProductConditionParams();
            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
            l_EqtypeProductConditionParams.setEqtypeProductConditionId(111);
            l_EqtypeProductConditionParams.setInstitutionCode("0D");
            l_EqtypeProductConditionParams.setProductCode("0");
            l_EqtypeProductConditionParams.setProductId(3304148080000L);
            l_EqtypeProductConditionParams.setMarketId(3303L);
            l_EqtypeProductConditionParams.setMarketCode("33");
            l_EqtypeProductConditionParams.setTableName("AA");
            l_EqtypeProductConditionParams.setColumnName("bb");
            l_EqtypeProductConditionParams.setSmallItemDiv("1");
            l_EqtypeProductConditionParams.setLargeItemDiv("1");
            l_EqtypeProductConditionParams.setDeleteFlg("0");
            l_EqtypeProductConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_EqtypeProductConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_EqtypeProductConditionParams);
            
            WEB3AdminPMProductCondConfigUnit l_unit = new WEB3AdminPMProductCondConfigUnit();
            l_unit.smallItemDiv = "1";
            l_unit.marketCode = "11";
            WEB3AdminPMProductCondConfigUnit[] l_units = new WEB3AdminPMProductCondConfigUnit[1];
            l_units[0] = l_unit;
            
            WEB3AdminPMProductCondConfigUnit l_unit1 = new WEB3AdminPMProductCondConfigUnit();
            l_unit1.smallItemDiv = "27";
            l_unit1.marketCode = "1";
            WEB3AdminPMProductCondConfigUnit[] l_units1 = new WEB3AdminPMProductCondConfigUnit[1];
            l_units1[0] = l_unit1;
            
            WEB3AdminPMProductCondConfigUnit l_unit2 = new WEB3AdminPMProductCondConfigUnit();
            l_unit2.smallItemDiv = "30";
            l_unit2.marketCode = "12";
            WEB3AdminPMProductCondConfigUnit[] l_units2 = new WEB3AdminPMProductCondConfigUnit[1];
            l_units2[0] = l_unit2;
            
            WEB3AdminEquityProductCondSettingServiceImplForTests l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTests();
            WEB3AdminPMProductCondConfConfirmRequestTest l_request = new WEB3AdminPMProductCondConfConfirmRequestTest();
            l_request.productCode = "0";
            l_request.tradingRegulationList = l_units1;
            l_request.basicInfoList = l_units2;
            l_request.stockMarginInfoList = l_units;
            l_request.depositRateList = l_units;
            l_request.substituteSecurityInfoList = l_units;
            l_request.priceInfoList = l_units;
            l_serviceImpl.validateConditionSetting(l_request);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitConditionSettingCase1()
    {
        final String STR_METHOD_NAME = "testSubmitConditionSettingCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionRowParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionRowParams.setInstitutionCode("0D");
            l_AdminPermissionRowParams.setPermissionLevel("770");
            l_AdminPermissionRowParams.setTransactionCategory("C0102");
            l_AdminPermissionRowParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionRowParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_AdministratorParams = TestDBUtility.getAdministratorRow();
            l_AdministratorParams.setAdministratorId(33381330001L);
            l_AdministratorParams.setInstitutionCode("0D");
            l_AdministratorParams.setBranchCode("381");
            l_AdministratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorParams);
            
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(new WEB3Administrator(l_AdministratorParams));
            WEB3AdministratorForMock.mockValidateTradingPassword("333", true);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_EqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_EqtypeTradedProductParams.setProductId(3304148080000L);
            l_EqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_EqtypeTradedProductUpdqParams = new EqtypeTradedProductUpdqParams();
            l_EqtypeTradedProductUpdqParams.setProductId(3304148080000L);
            l_EqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_EqtypeTradedProductUpdqParams.setInstitutionCode("0D");
            l_EqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_EqtypeTradedProductUpdqParams.setValidUntilBizDate("20080618");
            l_EqtypeTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setListType("1");
            l_EqtypeTradedProductUpdqParams.setNewListType("1");
            l_EqtypeTradedProductUpdqParams.setListedDate(WEB3DateUtility.getDate("20080618", "yyyyMMdd"));
            l_EqtypeTradedProductUpdqParams.setMarginableFlag(BooleanEnum.FALSE);
            l_EqtypeTradedProductUpdqParams.setShortableFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setLotSize(10);
            l_EqtypeTradedProductUpdqParams.setLastClosingPrice(100);
            l_EqtypeTradedProductUpdqParams.setMiniStockFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setBasePrice(100);
            l_EqtypeTradedProductUpdqParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_EqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductUpdqParams);
            
            WEB3AdminPMProductCondConfigUnit l_unit = new WEB3AdminPMProductCondConfigUnit();
            l_unit.smallItemDiv = "1";
            l_unit.marketCode = "1";
            WEB3AdminPMProductCondConfigUnit[] l_units = new WEB3AdminPMProductCondConfigUnit[1];
            l_units[0] = l_unit;
            
            WEB3AdminEquityProductCondSettingServiceImplForTests l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTests();
            WEB3AdminPMProductCondConfCompleteRequestTest l_request = new WEB3AdminPMProductCondConfCompleteRequestTest();
            l_request.password = "333";
            l_request.productCode = "0";
            l_request.tradingRegulationList = l_units;
            l_request.basicInfoList = l_units;
            l_request.stockMarginInfoList = l_units;
            l_request.depositRateList = l_units;
            l_request.substituteSecurityInfoList = l_units;
            l_request.priceInfoList = l_units;
            l_serviceImpl.submitConditionSetting(l_request);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitConditionSettingCase2()
    {
        final String STR_METHOD_NAME = "testSubmitConditionSettingCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionRowParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionRowParams.setInstitutionCode("0D");
            l_AdminPermissionRowParams.setPermissionLevel("770");
            l_AdminPermissionRowParams.setTransactionCategory("C0102");
            l_AdminPermissionRowParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionRowParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_AdministratorParams = TestDBUtility.getAdministratorRow();
            l_AdministratorParams.setAdministratorId(33381330001L);
            l_AdministratorParams.setInstitutionCode("0D");
            l_AdministratorParams.setBranchCode("381");
            l_AdministratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorParams);
            
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(new WEB3Administrator(l_AdministratorParams));
            WEB3AdministratorForMock.mockValidateTradingPassword("333", true);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_EqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_EqtypeTradedProductParams.setProductId(3304148080000L);
            l_EqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_EqtypeTradedProductUpdqParams = new EqtypeTradedProductUpdqParams();
            l_EqtypeTradedProductUpdqParams.setProductId(3304148080000L);
            l_EqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_EqtypeTradedProductUpdqParams.setInstitutionCode("0D");
            l_EqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_EqtypeTradedProductUpdqParams.setValidUntilBizDate("20080618");
            l_EqtypeTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setListType("1");
            l_EqtypeTradedProductUpdqParams.setNewListType("1");
            l_EqtypeTradedProductUpdqParams.setListedDate(WEB3DateUtility.getDate("20080618", "yyyyMMdd"));
            l_EqtypeTradedProductUpdqParams.setMarginableFlag(BooleanEnum.FALSE);
            l_EqtypeTradedProductUpdqParams.setShortableFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setLotSize(10);
            l_EqtypeTradedProductUpdqParams.setLastClosingPrice(100);
            l_EqtypeTradedProductUpdqParams.setMiniStockFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setBasePrice(100);
            l_EqtypeTradedProductUpdqParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_EqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductUpdqParams);
            
            WEB3AdminPMProductCondConfigUnit l_unit = new WEB3AdminPMProductCondConfigUnit();
            l_unit.smallItemDiv = "1";
            l_unit.marketCode = "1";
            WEB3AdminPMProductCondConfigUnit[] l_units = new WEB3AdminPMProductCondConfigUnit[1];
            l_units[0] = l_unit;
            
            WEB3AdminPMProductCondConfigUnit l_unit1 = new WEB3AdminPMProductCondConfigUnit();
            l_unit1.smallItemDiv = "27";
            l_unit1.marketCode = "1";
            WEB3AdminPMProductCondConfigUnit[] l_units1 = new WEB3AdminPMProductCondConfigUnit[1];
            l_units1[0] = l_unit1;
            
            WEB3AdminPMProductCondConfigUnit l_unit2 = new WEB3AdminPMProductCondConfigUnit();
            l_unit2.smallItemDiv = "35";
            l_unit2.marketCode = "12";
            WEB3AdminPMProductCondConfigUnit[] l_units2 = new WEB3AdminPMProductCondConfigUnit[1];
            l_units2[0] = l_unit2;
            
            WEB3AdminEquityProductCondSettingServiceImplForTests l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTests();
            WEB3AdminPMProductCondConfCompleteRequestTest l_request = new WEB3AdminPMProductCondConfCompleteRequestTest();
            l_request.password = "333";
            l_request.productCode = "0";
            l_request.tradingRegulationList = l_units1;
            l_request.basicInfoList = l_units2;
            l_request.stockMarginInfoList = l_units;
            l_request.depositRateList = l_units;
            l_request.substituteSecurityInfoList = l_units;
            l_request.priceInfoList = l_units;
            l_serviceImpl.submitConditionSetting(l_request);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitConditionSettingCase3()
    {
        final String STR_METHOD_NAME = "testSubmitConditionSettingCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionRowParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionRowParams.setInstitutionCode("0D");
            l_AdminPermissionRowParams.setPermissionLevel("770");
            l_AdminPermissionRowParams.setTransactionCategory("C0102");
            l_AdminPermissionRowParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionRowParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_AdministratorParams = TestDBUtility.getAdministratorRow();
            l_AdministratorParams.setAdministratorId(33381330001L);
            l_AdministratorParams.setInstitutionCode("0D");
            l_AdministratorParams.setBranchCode("381");
            l_AdministratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorParams);
            
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(new WEB3Administrator(l_AdministratorParams));
            WEB3AdministratorForMock.mockValidateTradingPassword("333", true);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_EqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_EqtypeTradedProductParams.setProductId(3304148080000L);
            l_EqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_EqtypeTradedProductUpdqParams = new EqtypeTradedProductUpdqParams();
            l_EqtypeTradedProductUpdqParams.setProductId(3304148080000L);
            l_EqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_EqtypeTradedProductUpdqParams.setInstitutionCode("0D");
            l_EqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_EqtypeTradedProductUpdqParams.setValidUntilBizDate("20080618");
            l_EqtypeTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setListType("1");
            l_EqtypeTradedProductUpdqParams.setNewListType("1");
            l_EqtypeTradedProductUpdqParams.setListedDate(WEB3DateUtility.getDate("20080618", "yyyyMMdd"));
            l_EqtypeTradedProductUpdqParams.setMarginableFlag(BooleanEnum.FALSE);
            l_EqtypeTradedProductUpdqParams.setShortableFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setLotSize(10);
            l_EqtypeTradedProductUpdqParams.setLastClosingPrice(100);
            l_EqtypeTradedProductUpdqParams.setMiniStockFlag(BooleanEnum.TRUE);
            l_EqtypeTradedProductUpdqParams.setBasePrice(100);
            l_EqtypeTradedProductUpdqParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_EqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_EqtypeTradedProductParams.set
            TestDBUtility.insertWithDel(l_EqtypeTradedProductUpdqParams);
            
            WEB3AdminPMProductCondConfigUnit l_unit = new WEB3AdminPMProductCondConfigUnit();
            l_unit.smallItemDiv = "1";
            l_unit.marketCode = "1";
            WEB3AdminPMProductCondConfigUnit[] l_units = new WEB3AdminPMProductCondConfigUnit[1];
            l_units[0] = l_unit;
            
            WEB3AdminPMProductCondConfigUnit l_unit1 = new WEB3AdminPMProductCondConfigUnit();
            l_unit1.smallItemDiv = "27";
            l_unit1.marketCode = "1";
            WEB3AdminPMProductCondConfigUnit[] l_units1 = new WEB3AdminPMProductCondConfigUnit[1];
            l_units1[0] = l_unit1;
            
            WEB3AdminPMProductCondConfigUnit l_unit2 = new WEB3AdminPMProductCondConfigUnit();
            l_unit2.smallItemDiv = "30";
            l_unit2.marketCode = "12";
            WEB3AdminPMProductCondConfigUnit[] l_units2 = new WEB3AdminPMProductCondConfigUnit[1];
            l_units2[0] = l_unit2;
            
            WEB3AdminEquityProductCondSettingServiceImplForTests l_serviceImpl =
                new WEB3AdminEquityProductCondSettingServiceImplForTests();
            WEB3AdminPMProductCondConfCompleteRequestTest l_request = new WEB3AdminPMProductCondConfCompleteRequestTest();
            l_request.password = "333";
            l_request.productCode = "0";
            l_request.tradingRegulationList = l_units1;
            l_request.basicInfoList = l_units2;
            l_request.stockMarginInfoList = l_units;
            l_request.depositRateList = l_units;
            l_request.substituteSecurityInfoList = l_units;
            l_request.priceInfoList = l_units;
            l_serviceImpl.submitConditionSetting(l_request);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3AdminEquityProductCondSettingServiceImplForTests extends WEB3AdminEquityProductCondSettingServiceImpl
    {
        protected String getKey(String l_strMarketCode, String l_strSmallItemDiv)
        {
            final String STR_METHOD_NAME = "getKey(String, String)";
            log.entering(STR_METHOD_NAME);
            
            log.debug("l_strMarketCode = " + l_strMarketCode);
            log.debug("l_strSmallItemDiv = " + l_strSmallItemDiv);
            // １）　@引数.市場コード.length() > 1 場合
            if (l_strMarketCode.length() > 1)
            {
                log.debug("getKey return : " + l_strMarketCode + l_strSmallItemDiv);
                log.exiting(STR_METHOD_NAME);
                return l_strMarketCode + l_strSmallItemDiv;
            }
            // 上記以外の場合
            else
            {
                log.debug("getKey return : " + "0" + l_strMarketCode + l_strSmallItemDiv);
                log.exiting(STR_METHOD_NAME);
                return "0" + l_strMarketCode + l_strSmallItemDiv;
            }
        }
    }
    
    public class WEB3AdminPMProductCondConfConfirmRequestTest extends WEB3AdminPMProductCondConfConfirmRequest
    {
        public void validate() throws WEB3BusinessLayerException
        {
            
        }
    }
    
    public class WEB3AdminPMProductCondConfCompleteRequestTest extends WEB3AdminPMProductCondConfCompleteRequest
    {
        public void validate() throws WEB3BusinessLayerException
        {
            
        }
    }
}
@
