head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.08.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityManualExpireServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AdminEquityManualExpireServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/12/27 趙林鵬 (中訊) 新規作成
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.Calendar;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputResponse;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.BranchMarketDealtCondParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

/**
 * 管理者・株式手動失効サービスImplのテスト<BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public class WEB3AdminEquityManualExpireServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityManualExpireServiceImplTest.class);
    
    public WEB3AdminEquityManualExpireServiceImplTest(String name)
    {
        super(name);
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
    
    WEB3AdminEquityManualExpireServiceImpl l_adminEquityManualExpireServiceImpl =
        new WEB3AdminEquityManualExpireServiceImpl();

    /**
     * (get入力画面)<BR>
     * 株式手動失効入力画面表示処理を行う。<BR>
     * test_getInputScreen_0001()<BR>
     */
    public void test_getInputScreen_0001() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getInputScreen_0001()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[]{},
                new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[]{},
                new Long(33381330001L));

        WEB3AdminEquityManualLapseInputRequest l_request = new WEB3AdminEquityManualLapseInputRequest();
    	//部店コード
        l_request.branchCode = new String[]{"381"};

        try
        {
        	//administratorテーブルRowを作成 AdministratorParams
        	AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        	l_administratorParams.setLoginId(33381330001L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            //AdminPermissionテーブルRowを作成AdminPermissionRow 管理者権限
            AdminPermissionParams l_adminPermissionParams1 = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
            
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0107");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
        	//（部店市場別）取扱条件Rowを作成 BranchMarketDealtCondRow
        	BranchMarketDealtCondParams l_branchMarketDealCondParams1 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams1.setInstitutionCode("0D");
            l_branchMarketDealCondParams1.setBranchCode("381");
            l_branchMarketDealCondParams1.setMarketCode("22");
            l_branchMarketDealCondParams1.setMartCanDealtEquity("1");
            TestDBUtility.deleteAll(l_branchMarketDealCondParams1.TYPE);
            TestDBUtility.insertWithDel(l_branchMarketDealCondParams1);

        	//(部店市場別.PTS)取扱条件Rowを作成 BranchMarketPtsDealtCondParams            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setBranchCode("381");
            l_branchMarketPtsDealtCondParams1.setMarketCode("11");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams1.TYPE);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);

        	WEB3AdminEquityManualLapseInputResponse l_response = new WEB3AdminEquityManualLapseInputResponse();

        	l_response = l_adminEquityManualExpireServiceImpl.getInputScreen(l_request);

        	assertEquals(2, l_response.marketList.length);
        	assertEquals("11", l_response.marketList[0]);
        	assertEquals("22", l_response.marketList[1]);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * (get入力画面)<BR>
     * 株式手動失効入力画面表示処理を行う。<BR>
     * test_getInputScreen_0002()<BR>
     */
    public void test_getInputScreen_0002() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getInputScreen_0002()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[]{},
                new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[]{},
                new Long(33381330001L));

        WEB3AdminEquityManualLapseInputRequest l_request = new WEB3AdminEquityManualLapseInputRequest();
    	//部店コード
        l_request.branchCode = new String[]{"381"};

        try
        {
        	//administratorテーブルRowを作成 AdministratorParams
        	AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        	l_administratorParams.setLoginId(33381330001L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            //AdminPermissionテーブルRowを作成AdminPermissionRow 管理者権限
            AdminPermissionParams l_adminPermissionParams1 = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
            
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0107");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
        	//（部店市場別）取扱条件Rowを作成 BranchMarketDealtCondRow
        	BranchMarketDealtCondParams l_branchMarketDealCondParams1 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams1.setInstitutionCode("0D");
            l_branchMarketDealCondParams1.setBranchCode("381");
            l_branchMarketDealCondParams1.setMarketCode("11");
            l_branchMarketDealCondParams1.setMartCanDealtEquity("1");
            
            BranchMarketDealtCondParams l_branchMarketDealCondParams2 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams2.setInstitutionCode("0D");
            l_branchMarketDealCondParams2.setBranchCode("381");
            l_branchMarketDealCondParams2.setMarketCode("33");
            l_branchMarketDealCondParams2.setMartCanDealtEquity("1");
            
            //DeleteAll
            TestDBUtility.deleteAll(l_branchMarketDealCondParams1.TYPE);
            TestDBUtility.deleteAll(l_branchMarketDealCondParams2.TYPE);
            //InsertRecord1
            TestDBUtility.insertWithDel(l_branchMarketDealCondParams1);
            //InsertRecord2
            TestDBUtility.insertWithDel(l_branchMarketDealCondParams2);

        	//(部店市場別.PTS)取扱条件Rowを作成 BranchMarketPtsDealtCondParams            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setBranchCode("381");
            l_branchMarketPtsDealtCondParams1.setMarketCode("11");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams2 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams2.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams2.setBranchCode("381");
            l_branchMarketPtsDealtCondParams2.setMarketCode("44");
            l_branchMarketPtsDealtCondParams2.setMartCanDealtEquity("1");
            
            //DeleteAll
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams1.TYPE);
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams2.TYPE);
            //InsertRecord1
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            //InsertRecord2
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams2);

        	WEB3AdminEquityManualLapseInputResponse l_response = new WEB3AdminEquityManualLapseInputResponse();

        	l_response = l_adminEquityManualExpireServiceImpl.getInputScreen(l_request);

        	assertEquals(3, l_response.marketList.length);
        	assertEquals("11", l_response.marketList[0]);
        	assertEquals("33", l_response.marketList[1]);
        	assertEquals("44", l_response.marketList[2]);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * (get入力画面)<BR>
     * 株式手動失効入力画面表示処理を行う。<BR>
     * test_getInputScreen_0003()<BR>
     */
    public void test_getInputScreen_0003() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getInputScreen_0003()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[]{},
                new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[]{},
                new Long(33381330001L));

        WEB3AdminEquityManualLapseInputRequest l_request = new WEB3AdminEquityManualLapseInputRequest();
    	//部店コード
        l_request.branchCode = new String[]{"381"};

        try
        {
        	//administratorテーブルRowを作成 AdministratorParams
        	AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        	l_administratorParams.setLoginId(33381330001L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            //AdminPermissionテーブルRowを作成AdminPermissionRow 管理者権限
            AdminPermissionParams l_adminPermissionParams1 = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
            
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0107");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
        	//（部店市場別）取扱条件Rowを作成 BranchMarketDealtCondRow
        	BranchMarketDealtCondParams l_branchMarketDealCondParams1 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams1.setInstitutionCode("0D");
            l_branchMarketDealCondParams1.setBranchCode("381");
            l_branchMarketDealCondParams1.setMarketCode("22");
            l_branchMarketDealCondParams1.setMartCanDealtEquity("1");
            
            BranchMarketDealtCondParams l_branchMarketDealCondParams2 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams2.setInstitutionCode("0D");
            l_branchMarketDealCondParams2.setBranchCode("381");
            l_branchMarketDealCondParams2.setMarketCode("33");
            l_branchMarketDealCondParams2.setMartCanDealtEquity("1");
            
            //DeleteAll
            TestDBUtility.deleteAll(l_branchMarketDealCondParams1.TYPE);
            TestDBUtility.deleteAll(l_branchMarketDealCondParams2.TYPE);
            //InsertRecord1
            TestDBUtility.insertWithDel(l_branchMarketDealCondParams1);
            //InsertRecord2
            TestDBUtility.insertWithDel(l_branchMarketDealCondParams2);

        	//(部店市場別.PTS)取扱条件Rowを作成 BranchMarketPtsDealtCondParams            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setBranchCode("381");
            l_branchMarketPtsDealtCondParams1.setMarketCode("22");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams2 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams2.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams2.setBranchCode("381");
            l_branchMarketPtsDealtCondParams2.setMarketCode("44");
            l_branchMarketPtsDealtCondParams2.setMartCanDealtEquity("1");
            
            //DeleteAll
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams1.TYPE);
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams2.TYPE);
            //InsertRecord1
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            //InsertRecord2
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams2);

        	WEB3AdminEquityManualLapseInputResponse l_response = new WEB3AdminEquityManualLapseInputResponse();

        	l_response = l_adminEquityManualExpireServiceImpl.getInputScreen(l_request);

        	assertEquals(3, l_response.marketList.length);
        	assertEquals("22", l_response.marketList[0]);
        	assertEquals("33", l_response.marketList[1]);
        	assertEquals("44", l_response.marketList[2]);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * (get入力画面)<BR>
     * 株式手動失効入力画面表示処理を行う。<BR>
     * test_getInputScreen_0004()<BR>
     */
    public void test_getInputScreen_0004() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getInputScreen_0004()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[]{},
                new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[]{},
                new Long(33381330001L));

        WEB3AdminEquityManualLapseInputRequest l_request = new WEB3AdminEquityManualLapseInputRequest();
    	//部店コード
        l_request.branchCode = new String[]{"381"};

        try
        {
        	//administratorテーブルRowを作成 AdministratorParams
        	AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        	l_administratorParams.setLoginId(33381330001L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            //AdminPermissionテーブルRowを作成AdminPermissionRow 管理者権限
            AdminPermissionParams l_adminPermissionParams1 = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
            
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0107");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
        	//（部店市場別）取扱条件Rowを作成 BranchMarketDealtCondRow
        	BranchMarketDealtCondParams l_branchMarketDealCondParams1 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams1.setInstitutionCode("0D");
            l_branchMarketDealCondParams1.setBranchCode("381");
            l_branchMarketDealCondParams1.setMarketCode("11");
            l_branchMarketDealCondParams1.setMartCanDealtEquity("1");
            
            BranchMarketDealtCondParams l_branchMarketDealCondParams2 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams2.setInstitutionCode("0D");
            l_branchMarketDealCondParams2.setBranchCode("381");
            l_branchMarketDealCondParams2.setMarketCode("33");
            l_branchMarketDealCondParams2.setMartCanDealtEquity("1");
            
            //DeleteAll
            TestDBUtility.deleteAll(l_branchMarketDealCondParams1.TYPE);
            TestDBUtility.deleteAll(l_branchMarketDealCondParams2.TYPE);
            //InsertRecord1
            TestDBUtility.insertWithDel(l_branchMarketDealCondParams1);
            //InsertRecord2
            TestDBUtility.insertWithDel(l_branchMarketDealCondParams2);

        	//(部店市場別.PTS)取扱条件Rowを作成 BranchMarketPtsDealtCondParams            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setBranchCode("381");
            l_branchMarketPtsDealtCondParams1.setMarketCode("11");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams2 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams2.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams2.setBranchCode("381");
            l_branchMarketPtsDealtCondParams2.setMarketCode("33");
            l_branchMarketPtsDealtCondParams2.setMartCanDealtEquity("1");
            
            //DeleteAll
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams1.TYPE);
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams2.TYPE);
            //InsertRecord1
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            //InsertRecord2
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams2);

        	WEB3AdminEquityManualLapseInputResponse l_response = new WEB3AdminEquityManualLapseInputResponse();

        	l_response = l_adminEquityManualExpireServiceImpl.getInputScreen(l_request);

        	assertEquals(2, l_response.marketList.length);
        	assertEquals("11", l_response.marketList[0]);
        	assertEquals("33", l_response.marketList[1]);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * (get入力画面)<BR>
     * 株式手動失効入力画面表示処理を行う。<BR>
     * test_getInputScreen_0005()<BR>
     */
    public void test_getInputScreen_0005() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getInputScreen_0005()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[]{},
                new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[]{},
                new Long(33381330001L));

        WEB3AdminEquityManualLapseInputRequest l_request = new WEB3AdminEquityManualLapseInputRequest();
    	//部店コード
        l_request.branchCode = new String[]{"381"};

        try
        {
        	//administratorテーブルRowを作成 AdministratorParams
        	AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        	l_administratorParams.setLoginId(33381330001L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            //AdminPermissionテーブルRowを作成AdminPermissionRow 管理者権限
            AdminPermissionParams l_adminPermissionParams1 = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
            
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0107");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
        	//（部店市場別）取扱条件Rowを作成 BranchMarketDealtCondRow
        	BranchMarketDealtCondParams l_branchMarketDealCondParams1 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams1.setInstitutionCode("0D");
            l_branchMarketDealCondParams1.setBranchCode("381");
            l_branchMarketDealCondParams1.setMarketCode("11");
            l_branchMarketDealCondParams1.setMartCanDealtEquity("1");
            
            BranchMarketDealtCondParams l_branchMarketDealCondParams2 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams2.setInstitutionCode("0D");
            l_branchMarketDealCondParams2.setBranchCode("381");
            l_branchMarketDealCondParams2.setMarketCode("33");
            l_branchMarketDealCondParams2.setMartCanDealtEquity("1");
            
            //DeleteAll
            TestDBUtility.deleteAll(l_branchMarketDealCondParams1.TYPE);
            TestDBUtility.deleteAll(l_branchMarketDealCondParams2.TYPE);
            //InsertRecord1
            TestDBUtility.insertWithDel(l_branchMarketDealCondParams1);
            //InsertRecord2
            TestDBUtility.insertWithDel(l_branchMarketDealCondParams2);

        	//(部店市場別.PTS)取扱条件Rowを作成 BranchMarketPtsDealtCondParams            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setBranchCode("381");
            l_branchMarketPtsDealtCondParams1.setMarketCode("22");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams2 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams2.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams2.setBranchCode("381");
            l_branchMarketPtsDealtCondParams2.setMarketCode("44");
            l_branchMarketPtsDealtCondParams2.setMartCanDealtEquity("1");
            
            //DeleteAll
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams1.TYPE);
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams2.TYPE);
            //InsertRecord1
//            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            //InsertRecord2
//            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams2);

        	WEB3AdminEquityManualLapseInputResponse l_response = new WEB3AdminEquityManualLapseInputResponse();

        	l_response = l_adminEquityManualExpireServiceImpl.getInputScreen(l_request);

        	assertEquals(2, l_response.marketList.length);
        	assertEquals("11", l_response.marketList[0]);
        	assertEquals("33", l_response.marketList[1]);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * (get入力画面)<BR>
     * 株式手動失効入力画面表示処理を行う。<BR>
     * test_getInputScreen_0006()<BR>
     */
    public void test_getInputScreen_0006() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getInputScreen_0006()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[]{},
                new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[]{},
                new Long(33381330001L));

        WEB3AdminEquityManualLapseInputRequest l_request = new WEB3AdminEquityManualLapseInputRequest();
    	//部店コード
        l_request.branchCode = new String[]{"381"};

        try
        {
        	//administratorテーブルRowを作成 AdministratorParams
        	AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        	l_administratorParams.setLoginId(33381330001L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            //AdminPermissionテーブルRowを作成AdminPermissionRow 管理者権限
            AdminPermissionParams l_adminPermissionParams1 = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
            
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0107");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
        	//（部店市場別）取扱条件Rowを作成 BranchMarketDealtCondRow
        	BranchMarketDealtCondParams l_branchMarketDealCondParams1 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams1.setInstitutionCode("0D");
            l_branchMarketDealCondParams1.setBranchCode("381");
            l_branchMarketDealCondParams1.setMarketCode("11");
            l_branchMarketDealCondParams1.setMartCanDealtEquity("1");
            
            BranchMarketDealtCondParams l_branchMarketDealCondParams2 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams2.setInstitutionCode("0D");
            l_branchMarketDealCondParams2.setBranchCode("381");
            l_branchMarketDealCondParams2.setMarketCode("33");
            l_branchMarketDealCondParams2.setMartCanDealtEquity("1");
            
            //DeleteAll
            TestDBUtility.deleteAll(l_branchMarketDealCondParams1.TYPE);
            TestDBUtility.deleteAll(l_branchMarketDealCondParams2.TYPE);
            //InsertRecord1
            //TestDBUtility.insertWithDel(l_branchMarketDealCondParams1);
            //InsertRecord2
            //TestDBUtility.insertWithDel(l_branchMarketDealCondParams2);

        	//(部店市場別.PTS)取扱条件Rowを作成 BranchMarketPtsDealtCondParams            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setBranchCode("381");
            l_branchMarketPtsDealtCondParams1.setMarketCode("22");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams2 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams2.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams2.setBranchCode("381");
            l_branchMarketPtsDealtCondParams2.setMarketCode("44");
            l_branchMarketPtsDealtCondParams2.setMartCanDealtEquity("1");
            
            //DeleteAll
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams1.TYPE);
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams2.TYPE);
            //InsertRecord1
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            //InsertRecord2
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams2);

        	WEB3AdminEquityManualLapseInputResponse l_response = new WEB3AdminEquityManualLapseInputResponse();

        	l_response = l_adminEquityManualExpireServiceImpl.getInputScreen(l_request);

        	assertEquals(2, l_response.marketList.length);
        	assertEquals("22", l_response.marketList[0]);
        	assertEquals("44", l_response.marketList[1]);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}
@
