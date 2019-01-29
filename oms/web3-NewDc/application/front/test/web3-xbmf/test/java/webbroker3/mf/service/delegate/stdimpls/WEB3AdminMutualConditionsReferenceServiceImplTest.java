head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.10.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualConditionsReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者投資信託銘柄条件登録照会サービス実装テスト(WEB3AdminMutualConditionsReferenceServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/11 趙林鵬 (中訊) 新規作成
*/

package webbroker3.mf.service.delegate.stdimpls;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （管理者投資信託銘柄条件登録照会サービス実装テスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminMutualConditionsReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminMutualConditionsReferenceServiceImplTest.class);

    public WEB3AdminMutualConditionsReferenceServiceImplTest(String arg0)
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
    
    public void testCreateSearchCondCharacterStringCase0001()
    {
        final String STR_METHOD_NAME = " testCreateSearchCondCharacterStringCase0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        { 
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
    
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            String l_strProductCode = null;
            String l_strMutualAssocProductCode = null;
            String l_strMutualProductCategoryCode = null;
            String l_strmutualFrgnMmfDisplayDiv = "0";
            
            WEB3AdminMutualConditionsReferenceServiceImpl l_impl =
                new WEB3AdminMutualConditionsReferenceServiceImpl();
            
            String l_strWhere = l_impl.createSearchCondCharacterString(
                l_strProductCode,
                l_strMutualAssocProductCode,
                l_strMutualProductCategoryCode,
                l_strmutualFrgnMmfDisplayDiv);
            
            assertEquals(" fund_type <> ? ", l_strWhere);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateSearchCondCharacterStringCase0002()
    {
        final String STR_METHOD_NAME = " testCreateSearchCondCharacterStringCase0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {  
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
    
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            String l_strProductCode = "0001000";
            String l_strMutualAssocProductCode = null;
            String l_strMutualProductCategoryCode = null;
            String l_strmutualFrgnMmfDisplayDiv = "0";
            
            WEB3AdminMutualConditionsReferenceServiceImpl l_impl =
                new WEB3AdminMutualConditionsReferenceServiceImpl();
            
            String l_strWhere = l_impl.createSearchCondCharacterString(
                l_strProductCode,
                l_strMutualAssocProductCode,
                l_strMutualProductCategoryCode,
                l_strmutualFrgnMmfDisplayDiv);
            
            assertEquals(" product_code like ? ||'%'  and fund_type <> ? ", l_strWhere);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateSearchCondCharacterStringCase0003()
    {
        final String STR_METHOD_NAME = " testCreateSearchCondCharacterStringCase0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {  
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
    
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            String l_strProductCode = null;
            String l_strMutualAssocProductCode = null;
            String l_strMutualProductCategoryCode = null;
            String l_strmutualFrgnMmfDisplayDiv = "1";
            
            WEB3AdminMutualConditionsReferenceServiceImpl l_impl =
                new WEB3AdminMutualConditionsReferenceServiceImpl();
            
            String l_strWhere = l_impl.createSearchCondCharacterString(
                l_strProductCode,
                l_strMutualAssocProductCode,
                l_strMutualProductCategoryCode,
                l_strmutualFrgnMmfDisplayDiv);
            
            assertEquals(" fund_type = ? ", l_strWhere);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    public void testCreateSearchCondCharacterStringCase0004()
    {
        final String STR_METHOD_NAME = " testCreateSearchCondCharacterStringCase0004";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {  
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
    
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            String l_strProductCode = "0001000";
            String l_strMutualAssocProductCode = null;
            String l_strMutualProductCategoryCode = null;
            String l_strmutualFrgnMmfDisplayDiv = "1";
            
            WEB3AdminMutualConditionsReferenceServiceImpl l_impl =
                new WEB3AdminMutualConditionsReferenceServiceImpl();
            
            String l_strWhere = l_impl.createSearchCondCharacterString(
                l_strProductCode,
                l_strMutualAssocProductCode,
                l_strMutualProductCategoryCode,
                l_strmutualFrgnMmfDisplayDiv);
            
            assertEquals(" product_code like ? ||'%'  and fund_type = ? ", l_strWhere);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    public void testCreateSearchCondDataContainerCase0001()
    {
        final String STR_METHOD_NAME = " testCreateSearchCondDataContainerCase0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        { 
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
    
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            String l_strProductCode = null;
            String l_strMutualAssocProductCode = null;
            String l_strMutualProductCategoryCode = null;
            String l_strmutualFrgnMmfDisplayDiv = "0";
            
            WEB3AdminMutualConditionsReferenceServiceImpl l_impl =
                new WEB3AdminMutualConditionsReferenceServiceImpl();
            
            String[] l_strWheres = l_impl.createSearchCondDataContainer(
                l_strProductCode,
                l_strMutualAssocProductCode,
                l_strMutualProductCategoryCode,
                l_strmutualFrgnMmfDisplayDiv);
            
            assertEquals("3", l_strWheres[0]);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void testCreateSearchCondDataContainerCase0002()
    {
        final String STR_METHOD_NAME = " testCreateSearchCondDataContainerCase0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        { 
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
    
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            String l_strProductCode = null;
            String l_strMutualAssocProductCode = null;
            String l_strMutualProductCategoryCode = null;
            String l_strmutualFrgnMmfDisplayDiv = "1";
            
            WEB3AdminMutualConditionsReferenceServiceImpl l_impl =
                new WEB3AdminMutualConditionsReferenceServiceImpl();
            
            String[] l_strWheres = l_impl.createSearchCondDataContainer(
                l_strProductCode,
                l_strMutualAssocProductCode,
                l_strMutualProductCategoryCode,
                l_strmutualFrgnMmfDisplayDiv);
            
            assertEquals("3", l_strWheres[0]);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }

}
@
