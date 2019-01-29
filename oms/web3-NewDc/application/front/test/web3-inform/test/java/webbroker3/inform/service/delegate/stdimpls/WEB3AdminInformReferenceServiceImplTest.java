head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.inform.data.InformDlFormMasterParams;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3AdminInformCommonRequest;
import webbroker3.inform.message.WEB3InformSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformReferenceServiceImplTest.class);
    WEB3AdminInformReferenceServiceImpl interceptor = null;
    
    public static StringBuffer expectMethodParam = new StringBuffer(); 

    public WEB3AdminInformReferenceServiceImplTest(String arg0)
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

    public void testCreateGetCondString_0001()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {"12345"};
        l_request.branchCode = l_branchCode;

        String l_expectSQL = "inform_div = ? and institution_code = ? and branch_code = ?";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0002()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;

        String l_expectSQL = "inform_div = ? and institution_code = ?";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0003()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {"12345" , "23456"};
        l_request.branchCode = l_branchCode;

        String l_expectSQL = "inform_div = ? and institution_code = ? and (branch_code = ? or branch_code = ?)";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0004()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;
        //識別コード
        l_request.requestNumber = "12";

        String l_expectSQL = "inform_div = ? and institution_code = ? and request_number like ? || '%'";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0005()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;

        String l_expectSQL = "inform_div = ? and institution_code = ?";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0006()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;
        //顧客コード
        l_request.accountCode = "12";

        String l_expectSQL = "inform_div = ? and institution_code = ? and account_code like ? || '%'";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0007()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;
        //顧客名
        l_request.accountName = "あああ";

        String l_expectSQL = "inform_div = ? and institution_code = ? and account_name like '%' || ? || '%'";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0008()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;
        //受付日時（自）
        l_request.receptionDateFrom = WEB3DateUtility.getDate("20070606" , "yyyyMMdd");

        String l_expectSQL = "inform_div = ? and institution_code = ? and created_timestamp >= ?";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0009()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;
        //受付日時（至）
        l_request.receptionDateTo = WEB3DateUtility.getDate("20070606" , "yyyyMMdd");

        String l_expectSQL = "inform_div = ? and institution_code = ? and created_timestamp < ?";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0010()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;
        //受付日時（自）
        l_request.receptionDateFrom = WEB3DateUtility.getDate("20070606" , "yyyyMMdd");
        //受付日時（至）
        l_request.receptionDateTo = WEB3DateUtility.getDate("20070606" , "yyyyMMdd");

        String l_expectSQL = "inform_div = ? and institution_code = ? and created_timestamp >= ? and created_timestamp < ?";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0011()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;
        //銘柄コード
        l_request.productCode = "123";

        String l_expectSQL = "inform_div = ? and institution_code = ? and fund_code = ?";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0012()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;
        //扱者コード
        l_request.traderCode = "123";

        String l_expectSQL = "inform_div = ? and institution_code = ? and sonar_trader_code = ?";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0013()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;
        //伝票作成状況
        String[] l_voucherInfoList = {"123"};
        l_request.voucherInfoList = l_voucherInfoList;

        String l_expectSQL = "inform_div = ? and institution_code = ? and status = ?";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateGetCondString_0014()
    {
        String STR_METHOD_NAME = "testCreateGetCondString_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
        //部店コード
        String[] l_branchCode = {};
        l_request.branchCode = l_branchCode;
        //伝票作成状況
        String[] l_voucherInfoList = {"123" , "234"};
        l_request.voucherInfoList = l_voucherInfoList;

        String l_expectSQL = "inform_div = ? and institution_code = ? and (status = ? or status = ?)";

        WEB3AdminInformReferenceServiceImpl l_service = new WEB3AdminInformReferenceServiceImpl();
        String l_actSQL = l_service.createGetCondString(l_request);

        assertEquals(l_expectSQL , l_actSQL);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetInformListDisplay_0001()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl
            );
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0303");
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            TestDBUtility.deleteAll(l_administratorTypeParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            WEB3AdminInformListRequestForTest l_request = new WEB3AdminInformListRequestForTest();
            String[] l_branchCode = {};
            l_request.branchCode = l_branchCode;
            WEB3InformSortKey l_informSortKey = new WEB3InformSortKey();
            l_informSortKey.keyItem = "requestNumber";
            l_informSortKey.ascDesc = "D";
            WEB3InformSortKey[] l_sortKeys = {l_informSortKey};
            l_request.sortKeys = l_sortKeys;
            l_request.pageSize = "1";
            l_request.pageIndex = "1";
            
            VariousInformParams l_variousInformParams = new VariousInformParams();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setInformDiv("11");
            l_variousInformParams.setRequestNumber("abcdef");
            l_variousInformParams.setBranchCode("381");
            l_variousInformParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_variousInformParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(l_variousInformParams.getRowType());
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            WEB3AdminInformReferenceServiceImplForTest l_adminInformReferenceServiceImplForTest = 
                new WEB3AdminInformReferenceServiceImplForTest();
            l_adminInformReferenceServiceImplForTest.getInformListDisplay(l_request);
            
            String l_expectValue = " createGetCondString(WEB3AdminInformCommonRequest l_request) createGetCondDataContainer(String l_strInstitutionCode, WEB3AdminInformCommonRequest l_request)";
            assertEquals(l_expectValue, this.expectMethodParam.toString());
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }
    }
    
    /**
     * (create取得条件データコンテナ)<BR>
     * testCreateGetCondDataContainer_0001<BR>
     */
    public void testCreateGetCondDataContainer_0001()
    {
        final String STR_METHOD_NAME = " testCreateGetCondDataContainer_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            String l_strInstitutionCode = null;
            WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
            //２）連絡種別
            l_request.informType = "A";
            //３）証券会社コード
            l_strInstitutionCode = "1";
            //４）部店コード
            l_request.branchCode = new String[]{"001", "002", "003", "004"};
            //５）識別コード
            l_request.requestNumber = "1234";
            //６）顧客コード
            l_request.accountCode = "7890";
            //７）顧客名
            l_request.accountName = "あいうえお";
            //８）受付日時（自）
            l_request.receptionDateFrom = WEB3DateUtility.getDate("20070508", "yyyyMMdd");
            //８）受付日時（至）
            l_request.receptionDateTo = WEB3DateUtility.getDate("20070515", "yyyyMMdd");
            //９）銘柄コード
            l_request.productCode = "4567";
            //１０）扱者コード
            l_request.traderCode = "02ASW";
            //１１）伝票作成状況
            l_request.voucherInfoList = new String[]{"T","Y", "U", "I"};

            interceptor = new WEB3AdminInformReferenceServiceImpl();
            Object[] objArray = interceptor.createGetCondDataContainer(l_strInstitutionCode, l_request);
            
            assertEquals(17, objArray.length);

            assertEquals("A", objArray[0]);
            assertEquals("1", objArray[1]);
            assertEquals("001", objArray[2]);
            assertEquals("002", objArray[3]);
            assertEquals("003", objArray[4]);
            assertEquals("004", objArray[5]);
            assertEquals("1234", objArray[6]);
            assertEquals("7890", objArray[7]);
            assertEquals("あいうえお", objArray[8]);

            assertEquals("20070508", WEB3DateUtility.formatDate((Date)objArray[9], "yyyyMMdd"));
            assertEquals("20070515", WEB3DateUtility.formatDate((Date)objArray[10], "yyyyMMdd"));
            assertEquals("4567", objArray[11]);
            assertEquals("02ASW", objArray[12]);
            assertEquals("T", objArray[13]);
            assertEquals("Y", objArray[14]);
            assertEquals("U", objArray[15]);
            assertEquals("I", objArray[16]);
                
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * (create取得条件データコンテナ)<BR>
     * testCreateGetCondDataContainer_0002<BR>
     */
    public void testCreateGetCondDataContainer_0002()
    {
        final String STR_METHOD_NAME = " testCreateGetCondDataContainer_0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            String l_strInstitutionCode = null;
            WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
            //２）連絡種別
            l_request.informType = "A";
            //３）証券会社コード
            l_strInstitutionCode = "1";
            //４）部店コード
            l_request.branchCode = new String[]{"001", "002", "003", "004"};

            interceptor = new WEB3AdminInformReferenceServiceImpl();
            Object[] objArray = interceptor.createGetCondDataContainer(l_strInstitutionCode, l_request);
            
            assertEquals(6, objArray.length);

            assertEquals("A", objArray[0]);
            assertEquals("1", objArray[1]);
            assertEquals("001", objArray[2]);
            assertEquals("002", objArray[3]);
            assertEquals("003", objArray[4]);
            assertEquals("004", objArray[5]);
                
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * (create取得条件データコンテナ)<BR>
     * testCreateGetCondDataContainer_0003<BR>
     */
    public void testCreateGetCondDataContainer_0003()
    {
        final String STR_METHOD_NAME = " testCreateGetCondDataContainer_0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            String l_strInstitutionCode = null;
            WEB3AdminInformCommonRequest l_request = new WEB3AdminInformCommonRequest();
            //２）連絡種別
            l_request.informType = "A";
            //３）証券会社コード
            l_strInstitutionCode = "1";
            //４）部店コード
            l_request.branchCode = new String[]{"001", "002", "003", "004"};

            //１１）伝票作成状況
            l_request.voucherInfoList = new String[]{};

            interceptor = new WEB3AdminInformReferenceServiceImpl();
            Object[] objArray = interceptor.createGetCondDataContainer(l_strInstitutionCode, l_request);
            
            assertEquals(6, objArray.length);

            assertEquals("A", objArray[0]);
            assertEquals("1", objArray[1]);
            assertEquals("001", objArray[2]);
            assertEquals("002", objArray[3]);
            assertEquals("003", objArray[4]);
            assertEquals("004", objArray[5]);
                
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testEditSortCond_0001()
    {
        WEB3InformSortKey[] l_informSortKeys = new WEB3InformSortKey[2];
        WEB3InformSortKey l_informSortKey1 = new WEB3InformSortKey();
        l_informSortKey1.keyItem = "accountCode";
        l_informSortKey1.ascDesc = "A";
        WEB3InformSortKey l_informSortKey2 = new WEB3InformSortKey();
        l_informSortKey2.keyItem = "requestNumber";
        l_informSortKey2.ascDesc = "D";
        l_informSortKeys[0] = l_informSortKey1;
        l_informSortKeys[1] = l_informSortKey2;
        
        WEB3AdminInformReferenceServiceImpl l_adminInformReferenceServiceImpl = new WEB3AdminInformReferenceServiceImpl();
        WEB3InformSortKey[] l_WEB3InformSortKeys = l_adminInformReferenceServiceImpl.editSortCond(l_informSortKeys);

        String[] excKeyItem = {"branchCode", "accountCode", "requestNumber"};
        String[] excAscDesc = {"A", "A", "D"};
        for (int i = 0; i < 3; i++)
        {
            assertEquals(excKeyItem[i] , l_WEB3InformSortKeys[i].keyItem);
            assertEquals(excAscDesc[i] , l_WEB3InformSortKeys[i].ascDesc);
        }
    }
    
    public void testEditSortCond_0002()
    {
        WEB3InformSortKey[] l_informSortKeys = null;
        
        WEB3AdminInformReferenceServiceImpl l_adminInformReferenceServiceImpl = new WEB3AdminInformReferenceServiceImpl();
        WEB3InformSortKey[] l_WEB3InformSortKeys = l_adminInformReferenceServiceImpl.editSortCond(l_informSortKeys);

        assertNull(l_WEB3InformSortKeys);
    }
    
    public void testGetDownLoadFile_0001()
    {
        String STR_METHOD_NAME = "testGetDownLoadFile_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl
            );
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            l_administratorParams.setBranchCode("333");
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0303");
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            TestDBUtility.deleteAll(l_administratorTypeParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            VariousInformParams l_variousInformParams = new VariousInformParams();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setInformDiv("11");
            l_variousInformParams.setRequestNumber("abcdef");
            l_variousInformParams.setBranchCode("381");
            l_variousInformParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_variousInformParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(l_variousInformParams.getRowType());
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            InformDlFormMasterParams l_informDlFormMasterParams = new InformDlFormMasterParams();
            l_informDlFormMasterParams.setInstitutionCode("0D");
            l_informDlFormMasterParams.setBranchCode("381");
            l_informDlFormMasterParams.setInformDiv("11");
            l_informDlFormMasterParams.setColumnNumber(1);
            l_informDlFormMasterParams.setColumnType(1);
            l_informDlFormMasterParams.setInputItemSymbolName("aaaa");
            TestDBUtility.deleteAll(l_informDlFormMasterParams.getRowType());
            TestDBUtility.insertWithDel(l_informDlFormMasterParams);
            
            WEB3AdminInformDownLoadRequestForTest l_request = new WEB3AdminInformDownLoadRequestForTest();
            String[] l_branchCode = {"000"};
            l_request.branchCode = l_branchCode;
            l_request.informType = "11";
            WEB3InformSortKey l_informSortKey = new WEB3InformSortKey();
            l_informSortKey.keyItem = "requestNumber";
            l_informSortKey.ascDesc = "D";
            WEB3InformSortKey[] l_sortKeys = {l_informSortKey};
            l_request.sortKeys = l_sortKeys;

            WEB3AdminInformReferenceServiceImplForTest l_adminInformReferenceServiceImplForTest = 
                new WEB3AdminInformReferenceServiceImplForTest();
            l_adminInformReferenceServiceImplForTest.getDownLoadFile(l_request);
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

}
@
