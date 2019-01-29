head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.53.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminInformReferenceServiceImplTest
Author Name      : Daiwa Institute of Research
Revision History : 2007/05/15 稲本潤に (中訊) 新規作成
*/
package webbroker3.inform;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.bd.WEB3BondProduct;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.inform.data.InformCtrlItemMasterParams;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3InformAddInfoUnit;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (各種連絡)<BR>
 * 各種連絡クラス<BR>
 */
public class WEB3InformTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3Inform.class);
    
    WEB3Inform interceptor;
    
    public WEB3InformTest(String arg0)
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
    
//    /**
//     * validate投信銘柄
//     * testValidateMutualProduct_0001
//     */
//    public void testValidateMutualProduct_0001()
//    {
//        final String STR_METHOD_NAME = " testValidateMutualProduct_0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        interceptor = new WEB3Inform();
//        try
//        {
//            
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.mf.WEB3MutualFundProductManager",
//                    "getMutualFundProduct",
//                    new Class[] {Institution.class, String.class},
//                    null);
//                    
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);//getMainAccountRow
//            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(mainAccountParams);
//            
//            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);//getMutualFundProductRow
//            MutualFundProductParams mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
//            mutualFundProductParams.setInstitutionCode("0D");
//            mutualFundProductParams.setProductIssueCode("0");
//            TestDBUtility.insertWithDel(mutualFundProductParams);
//            
//            VariousInformParams variousInformParams = new VariousInformParams();
//            variousInformParams.setBranchCode("381");//2
//            variousInformParams.setInstitutionCode("0D");//1
//            variousInformParams.setInformDiv("12");//4
//            variousInformParams.setRequestNumber("1234");//3
//            variousInformParams.setAccountCode("2512246");
//            variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//            variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//
//            Field field = WEB3Inform.class.getDeclaredField("variousInformParams");
//            field.setAccessible(true);
//            field.set(interceptor, variousInformParams);
//
//            boolean flag = interceptor.validateMutualProduct(interceptor.getInstitution(), "0001000");//getInstitution
//            assertTrue(flag);
//            log.exiting(STR_METHOD_NAME);
//        } catch (Exception e)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, e);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
//        }
//    }
//    
//    /**
//     * validate投信銘柄
//     * testValidateMutualProduct_0002
//     */
//    public void testValidateMutualProduct_0002()
//    {
//        final String STR_METHOD_NAME = " testValidateMutualProduct_0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        interceptor = new WEB3Inform();
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);//getMainAccountRow
//            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(mainAccountParams);
//            
//            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);//getMutualFundProductRow
//            MutualFundProductParams mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
//            mutualFundProductParams.setInstitutionCode("0D");
//            mutualFundProductParams.setProductIssueCode("0");
//            TestDBUtility.insertWithDel(mutualFundProductParams);
//            
//            VariousInformParams variousInformParams = new VariousInformParams();
//            variousInformParams.setBranchCode("381");//2
//            variousInformParams.setInstitutionCode("0D");//1
//            variousInformParams.setInformDiv("12");//4
//            variousInformParams.setRequestNumber("1234");//3
//            variousInformParams.setAccountCode("2512246");
//            variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//            variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//
//            Field field = WEB3Inform.class.getDeclaredField("variousInformParams");
//            field.setAccessible(true);
//            field.set(interceptor, variousInformParams);
//
//            boolean flag = interceptor.validateMutualProduct(interceptor.getInstitution(), "0001000");//getInstitution
//            assertFalse(flag);
//            log.exiting(STR_METHOD_NAME);
//        } catch (Exception e)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, e);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    
//    /**
//     * validate債券銘柄
//     * testValidateBondProduct_0001
//     */
//    public void testValidateBondProduct_0001()
//    {
//        final String STR_METHOD_NAME = " testValidateBondProduct_0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        interceptor = new WEB3Inform();
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);//getMainAccountRow
//            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(mainAccountParams);
//            
//            TestDBUtility.deleteAll(BondProductRow.TYPE);//getMutualFundProductRow
//            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//            l_bondProductParams.setInstitutionCode("0D");
//            l_bondProductParams.setProductIssueCode("0");
//            TestDBUtility.insertWithDel(l_bondProductParams);
//            
//            VariousInformParams variousInformParams = new VariousInformParams();
//            variousInformParams.setBranchCode("381");//2
//            variousInformParams.setInstitutionCode("0D");//1
//            variousInformParams.setInformDiv("12");//4
//            variousInformParams.setRequestNumber("1234");//3
//            variousInformParams.setAccountCode("2512246");
//            variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//            variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//
//            Field field = WEB3Inform.class.getDeclaredField("variousInformParams");
//            field.setAccessible(true);
//            field.set(interceptor, variousInformParams);
//
//            boolean flag = interceptor.validateBondProduct(interceptor.getInstitution(), "A01010763");//getInstitution
//            assertTrue(flag);
//            log.exiting(STR_METHOD_NAME);
//        } catch (Exception e)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, e);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    
//    /**
//     * validate債券銘柄
//     * testValidateBondProduct_0002
//     */
//    public void testValidateBondProduct_0002()
//    {
//        final String STR_METHOD_NAME = " testValidateBondProduct_0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        interceptor = new WEB3Inform();
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);//getMainAccountRow
//            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(mainAccountParams);
//            
//            TestDBUtility.deleteAll(BondProductRow.TYPE);//getMutualFundProductRow
//            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//            l_bondProductParams.setInstitutionCode("0D");
//            l_bondProductParams.setProductIssueCode("0");
//            TestDBUtility.insertWithDel(l_bondProductParams);
//            
//            VariousInformParams variousInformParams = new VariousInformParams();
//            variousInformParams.setBranchCode("381");//2
//            variousInformParams.setInstitutionCode("0D");//1
//            variousInformParams.setInformDiv("12");//4
//            variousInformParams.setRequestNumber("1234");//3
//            variousInformParams.setAccountCode("2512246");
//            variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//            variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//
//            Field field = WEB3Inform.class.getDeclaredField("variousInformParams");
//            field.setAccessible(true);
//            field.set(interceptor, variousInformParams);
//
//            boolean flag = interceptor.validateBondProduct(interceptor.getInstitution(), "0");//getInstitution
//            assertFalse(flag);
//            log.exiting(STR_METHOD_NAME);
//        } catch (Exception e)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, e);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
    
    /**
     * get各種連絡行
     * testGetVariousInform_0001
     */
    public void testGetVariousInform_0001()
    {
        final String STR_METHOD_NAME = " testGetVariousInform_0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        interceptor = new WEB3Inform();
        try
        {
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = interceptor.getVariousInform("123", "000", "1234", "12");
            assertNull(l_variousInformParams);
            log.exiting(STR_METHOD_NAME);
        } catch (WEB3BaseException e)
        {
            log.error(TEST_END + STR_METHOD_NAME, e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * get各種連絡行
     * testGetVariousInform_0002
     */
    public void testGetVariousInform_0002()
    {
        final String STR_METHOD_NAME = " testGetVariousInform_0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        interceptor = new WEB3Inform();
        try
        {
            VariousInformParams variousInformParams = new VariousInformParams();
            variousInformParams.setBranchCode("000");//2
            variousInformParams.setInstitutionCode("123");//1
            variousInformParams.setInformDiv("12");//4
            variousInformParams.setRequestNumber("1234");//3
            variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            TestDBUtility.insertWithDel(variousInformParams);

            VariousInformParams l_variousInformParams = interceptor.getVariousInform("123", "000", "1234", "12");
            assertEquals("000", l_variousInformParams.getBranchCode());
            log.exiting(STR_METHOD_NAME);
        } catch (WEB3BaseException e)
        {
            log.error(TEST_END + STR_METHOD_NAME, e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * update各種連絡
     * testUpdateInform_0001
     */
    public void testUpdateInform_0001()
    {
        final String STR_METHOD_NAME = " testUpdateInform_0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        interceptor = new WEB3Inform();
        try
        {
            VariousInformParams variousInformParams = new VariousInformParams();
            variousInformParams.setBranchCode("000");//2
            variousInformParams.setInstitutionCode("123");//1
            variousInformParams.setInformDiv("12");//4
            variousInformParams.setRequestNumber("1234");//3
            variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            TestDBUtility.insertWithDel(variousInformParams);

            Field field = WEB3Inform.class.getDeclaredField("variousInformParams");
            field.setAccessible(true);
            field.set(interceptor, variousInformParams);

            Map l_map = new HashMap();
            l_map.put("status", WEB3VoucherCreateStatusDef.NOT_CREATE);
            l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            VariousInformParams l_variousInformParams = interceptor.getVariousInform("123", "000", "1234", "12");
            assertNull(l_variousInformParams.getStatus());
            interceptor.updateInform(l_map);
            VariousInformParams l_variousInformParams1 = interceptor.getVariousInform("123", "000", "1234", "12");
            assertEquals("0", l_variousInformParams1.getStatus());
            log.exiting(STR_METHOD_NAME);
        } catch (Exception e)
        {
            log.error(TEST_END + STR_METHOD_NAME, e);
            log.exiting(STR_METHOD_NAME);
            fail();
        } 
    }
    
//    /**
//     * saveNew各種連絡
//     * testSaveNewInform_0001
//     */
//    public void testSaveNewInform_0001()
//    {
//        final String STR_METHOD_NAME = " testSaveNewInform_0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        interceptor = new WEB3Inform();
//        try
//        {
//            VariousInformParams variousInformParams = new VariousInformParams();
//            variousInformParams.setBranchCode("000");//2
//            variousInformParams.setInstitutionCode("123");//1
//            variousInformParams.setInformDiv("12");//4
//            variousInformParams.setRequestNumber("1234");//3
//            variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//            variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//
//            Field field = WEB3Inform.class.getDeclaredField("variousInformParams");
//            field.setAccessible(true);
//            field.set(interceptor, variousInformParams);
//
//            interceptor.saveNewInform("0012", "5678", "1");
//            VariousInformParams l_variousInformParams1 = interceptor.getVariousInform("123", "000", "5678", "12");
//            assertEquals("0012", l_variousInformParams1.getTraderCode());
//            assertEquals("5678", l_variousInformParams1.getRequestNumber());
//            assertEquals("1", l_variousInformParams1.getStatus());
//            log.exiting(STR_METHOD_NAME);
//        } catch (Exception e)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, e);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        } 
//    }
//    
//    public void testValidateInformDetailInfoUnit_0001()
//    {
//        final String STR_METHOD_NAME = " testValidateInformDetailInfoUnit_0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            WEB3InformDetailInfoUnit l_unit = this.getInformDetailInfoUnit();
//            WEB3Inform l_inform = new WEB3Inform(l_unit);
//            
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountCode("1");
//            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            InformCtrlItemMasterParams l_informCtrlItemMasterParams = new InformCtrlItemMasterParams();
//            l_informCtrlItemMasterParams.setInstitutionCode("0D");
//            l_informCtrlItemMasterParams.setBranchCode("381");
//            l_informCtrlItemMasterParams.setInformDiv("1");
//            l_informCtrlItemMasterParams.setItemSymbolName("branch_code");
//            l_informCtrlItemMasterParams.setNecessaryFlag(1);
//            l_informCtrlItemMasterParams.setItemCheckMode("15");
//            
//            TestDBUtility.deleteAll(l_informCtrlItemMasterParams.getRowType());
//            TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
//            
//            l_inform.validateInformDetailInfoUnit();
//            
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02796, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//    }
//    
//    public void testValidateInformDetailInfoUnit_0002()
//    {
//        final String STR_METHOD_NAME = " testValidateInformDetailInfoUnit_0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            
//            WEB3InformDetailInfoUnit l_unit = this.getInformDetailInfoUnit();
//            WEB3Inform l_inform = new WEB3Inform(l_unit);
//            
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountCode("1");
//            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            InformCtrlItemMasterParams l_informCtrlItemMasterParams = new InformCtrlItemMasterParams();
//            l_informCtrlItemMasterParams.setInstitutionCode("0D");
//            l_informCtrlItemMasterParams.setBranchCode("381");
//            l_informCtrlItemMasterParams.setInformDiv("1");
//            l_informCtrlItemMasterParams.setItemSymbolName("branch_code");
//            l_informCtrlItemMasterParams.setNecessaryFlag(1);
//            l_informCtrlItemMasterParams.setItemCheckMode("16");
//            
//            TestDBUtility.deleteAll(l_informCtrlItemMasterParams.getRowType());
//            TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
//            
//            l_inform.validateInformDetailInfoUnit();
//            
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02797, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//    }
//    
//    public void testGetInformAddInfoUnit_0001()
//    {
//        final String STR_METHOD_NAME = " testGetInformAddInfoUnit_0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            
//            WEB3InformDetailInfoUnit l_unit = this.getInformDetailInfoUnit();
//            WEB3Inform l_inform = new WEB3Inform(l_unit);
//            
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountCode("1");
//            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            String[] l_strItemSymbolNames = {"ext_code1","ext_code2","ext_code3","ext_code4","ext_code5","ext_code6",
//                "ext_code7","ext_code8","ext_code9","ext_code10"};
//
//            TestDBUtility.deleteAll(InformCtrlItemMasterParams.TYPE);
//            for (int i = 0;  i < 10; i++)
//            {
//                InformCtrlItemMasterParams l_informCtrlItemMasterParams = new InformCtrlItemMasterParams();
//                l_informCtrlItemMasterParams.setInstitutionCode("0D");
//                l_informCtrlItemMasterParams.setBranchCode("381");
//                l_informCtrlItemMasterParams.setInformDiv("1");
//                l_informCtrlItemMasterParams.setItemSymbolName(l_strItemSymbolNames[i]);
//                l_informCtrlItemMasterParams.setNecessaryFlag(1);
//                if (i%2 == 0)
//                {
//                    l_informCtrlItemMasterParams.setItemCheckMode("15");
//                }
//                else
//                {
//                    l_informCtrlItemMasterParams.setItemCheckMode("16");
//                }
//                
//                TestDBUtility.insertWithDel(l_informCtrlItemMasterParams);
//            }
//            
//            WEB3MutualFundProduct l_mutualFundProduct = null;
//            try
//            {
//                ProductParams l_productParams = TestDBUtility.getProductRow();
//                l_productParams.setStandardName("投信銘柄");
//                TestDBUtility.deleteAll(ProductRow.TYPE);
//                TestDBUtility.insertWithDel(l_productParams);
//                MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
//                l_mutualFundProductParams.setProductId(3304148080000L);
//                TestDBUtility.deleteAll(l_mutualFundProductParams.getRowType());
//                TestDBUtility.insertWithDel(l_mutualFundProductParams);
//
//                l_mutualFundProduct = new WEB3MutualFundProduct(l_productParams);
//            }
//            catch (Exception l_ex)
//            {
//                log.error(TEST_END + STR_METHOD_NAME, l_ex);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFundProductManager",
//                "getMutualFundProduct",
//                new Class[]{ Institution.class, String.class },
//                l_mutualFundProduct);
//            
//            BondProductParams l_BondProductParams = TestDBUtility.getBondProductRow();
//            l_BondProductParams.setProductName("債券銘柄");
//            WEB3BondProduct l_bondProduct = null;
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(3304140763000L);
//            l_productParams.setStandardName("債券銘柄");
//            try
//            {
//                TestDBUtility.deleteAll(l_productParams.getRowType());
//                TestDBUtility.insertWithDel(l_productParams);
//
//                l_bondProduct = new WEB3BondProduct(l_BondProductParams);
//            }
//            catch (Exception l_ex)
//            {
//                log.error(TEST_END + STR_METHOD_NAME, l_ex);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.bd.WEB3BondProductManager",
//                "getBondProduct",
//                new Class[] {Institution.class, String.class},
//                l_bondProduct);
//            
//            WEB3InformAddInfoUnit l_informAddInfoUnit = l_inform.getInformAddInfoUnit();
//            
//            assertEquals("投信銘柄", l_informAddInfoUnit.addInfo1);
//            assertEquals(null, l_informAddInfoUnit.addInfo2);
//            assertEquals("投信銘柄", l_informAddInfoUnit.addInfo3);
//            assertEquals("債券銘柄", l_informAddInfoUnit.addInfo4);
//            assertEquals(null, l_informAddInfoUnit.addInfo5);
//            assertEquals("債券銘柄", l_informAddInfoUnit.addInfo6);
//            assertEquals(null, l_informAddInfoUnit.addInfo7);
//            assertEquals(null, l_informAddInfoUnit.addInfo8);
//            assertEquals(null, l_informAddInfoUnit.addInfo9);
//            assertEquals("債券銘柄", l_informAddInfoUnit.addInfo10);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02797, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//    }

    public void testGetVariousInform_T01()
    {
        final String STR_METHOD_NAME = "testGetVariousInform_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount =
                new WEB3GentradeMainAccount(l_mainAccountParams);

            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            
            WEB3Inform l_inform = WEB3Inform.getVariousInform(l_mainAccount, "1");
            assertNull(l_inform);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetVariousInform_T02()
    {
        final String STR_METHOD_NAME = "testGetVariousInform_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount =
                new WEB3GentradeMainAccount(l_mainAccountParams);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            
            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_variousInformParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_variousInformParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setRequestNumber("1");
            l_variousInformParams.setLastUpdatedTimestamp(new Date(2007-1900,12,8));
            l_variousInformParams.setExtDiv1("1");
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            //2222222
            l_variousInformParams.setRequestNumber("2");
            l_variousInformParams.setLastUpdatedTimestamp(new Date(2007-1900,12,6));
            l_queryProcessor.doInsertQuery(l_variousInformParams);
            //2222222
            l_variousInformParams.setRequestNumber("3");
            l_variousInformParams.setLastUpdatedTimestamp(new Date(2007-1900,12,9));
            l_queryProcessor.doInsertQuery(l_variousInformParams);
            
            WEB3Inform l_inform = WEB3Inform.getVariousInform(l_mainAccount, "1");
            VariousInformRow l_variousInformRow =
                (VariousInformRow)l_inform.getDataSourceObject();
            assertEquals("3", l_variousInformRow.getRequestNumber());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testCreateNewVariousInform_T01()
    {
        final String STR_METHOD_NAME = "testCreateNewVariousInform_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount =
                new WEB3GentradeMainAccount(l_mainAccountParams);

            WEB3Inform l_inform = WEB3Inform.createNewVariousInform(
                l_mainAccount,
                "1",
                "2",
                "jiddk",
                "3");
            VariousInformRow l_variousInformRow =
                (VariousInformRow)l_inform.getDataSourceObject();
            assertEquals(l_mainAccountParams.getInstitutionCode(), l_variousInformRow.getInstitutionCode());
            assertEquals("1", l_variousInformRow.getInformDiv());
            assertEquals("3", l_variousInformRow.getRequestNumber());
            assertEquals(l_mainAccountParams.getBranchCode(), l_inform.getBranchCode());
            assertEquals(l_mainAccountParams.getAccountCode(), l_variousInformRow.getAccountCode());
            assertEquals(l_mainAccountParams.getFamilyName(), l_variousInformRow.getAccountName());
            assertEquals("1", l_variousInformRow.getExtDiv1());
            assertEquals("2", l_variousInformRow.getExtDiv2());
            assertEquals("jiddk", l_variousInformRow.getLastUpdater());
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHH"),
                WEB3DateUtility.formatDate(l_variousInformRow.getCreatedTimestamp(), "yyyyMMddHH"));
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHH"),
                WEB3DateUtility.formatDate(l_variousInformRow.getLastUpdatedTimestamp(), "yyyyMMddHH"));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

         
        log.exiting(STR_METHOD_NAME);
    }

    public void testSaveNewVariousInform_T01()
    {
        final String STR_METHOD_NAME = "testSaveNewVariousInform_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("624");
            l_variousInformParams.setInformDiv("2");
            l_variousInformParams.setRequestNumber("1");
            l_variousInformParams.setTraderCode("123");
            l_variousInformParams.setCreatedTimestamp(new Date(2007-1900,2,12));
            l_variousInformParams.setLastUpdatedTimestamp(new Date(2007-1900,2,15));
            WEB3Inform l_inform = new WEB3Inform(l_variousInformParams);
            l_inform.saveNewVariousInform();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(VariousInformParams.TYPE);
            VariousInformRow l_variousInformRow =
                (VariousInformRow)l_lisResult.get(0);
            assertEquals("0D", l_variousInformRow.getInstitutionCode());
            assertEquals("624", l_variousInformRow.getBranchCode());
            assertEquals("2", l_variousInformRow.getInformDiv());
            assertEquals("1", l_variousInformRow.getRequestNumber());
            assertNull(l_variousInformRow.getTraderCode());
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHH"),
                WEB3DateUtility.formatDate(l_variousInformRow.getCreatedTimestamp(), "yyyyMMddHH"));
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHH"),
                WEB3DateUtility.formatDate(l_variousInformRow.getLastUpdatedTimestamp(), "yyyyMMddHH"));
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testUpdatePTSVariousInform()
    {
        final String STR_MATHOD_NAME = "testUpdatePTSVariousInform()";
        log.entering(STR_MATHOD_NAME);
        try
        {
            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("624");
            l_variousInformParams.setInformDiv("2");
            l_variousInformParams.setRequestNumber("1");
            l_variousInformParams.setTraderCode("123");
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setLastUpdatedTimestamp(new Date(2007-1900,2,15));
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            WEB3Inform l_inform = new WEB3Inform(l_variousInformParams);
            Map l_update = new HashMap();
            l_update.put("ext_div1", "0");
            l_update.put("last_updater", "jiddk");
            l_update.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            
            l_inform.updateInform(l_update);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(VariousInformParams.TYPE);
            VariousInformRow l_variousInformRow =
                (VariousInformRow)l_lisResult.get(0);
            assertEquals("0D", l_variousInformRow.getInstitutionCode());
            assertEquals("624", l_variousInformRow.getBranchCode());
            assertEquals("2", l_variousInformRow.getInformDiv());
            assertEquals("1", l_variousInformRow.getRequestNumber());
            assertEquals("0", l_variousInformRow.getExtDiv1());
            assertEquals("jiddk", l_variousInformRow.getLastUpdater());
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHH"),
                WEB3DateUtility.formatDate(l_variousInformRow.getLastUpdatedTimestamp(), "yyyyMMddHH"));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_MATHOD_NAME);
    }
    
    public void testGetExtDiv2()
    {
        final String STR_METHOD_NAME = "testGetExtDiv2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setExtDiv2("11");
            
            WEB3Inform l_inform = new WEB3Inform(l_variousInformParams);
            String l_strExtDiv2 = l_inform.getExtDiv2();
            assertEquals("11", l_strExtDiv2);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private WEB3InformDetailInfoUnit getInformDetailInfoUnit()
    {
        WEB3InformDetailInfoUnit l_unit = new
        WEB3InformDetailInfoUnit();
        l_unit.informType = "1";
        l_unit.branchCode = "381";
        l_unit.accountNumber = "1";
        l_unit.institutionCode = "0D";
        l_unit.num1 = "1";
        l_unit.num2 = "2";
        l_unit.num3 = "3";
        l_unit.num4 = "4";
        l_unit.num5 = "5";
        l_unit.num6 = "6";
        l_unit.num7 = "7";
        l_unit.num8 = "8";
        l_unit.num9 = "9";
        l_unit.num10 = "10";
        l_unit.num11 = "11";
        l_unit.num12 = "12";
        l_unit.num13 = "13";
        l_unit.num14 = "14";
        l_unit.num15 = "15";
        l_unit.num16 = "16";
        l_unit.num17 = "17";
        l_unit.num18 = "18";
        l_unit.num19 = "19";
        l_unit.num20 = "20";
        l_unit.num21 = "21";
        l_unit.num22 = "22";
        l_unit.num23 = "23";
        l_unit.num24 = "24";
        l_unit.num25 = "25";
        l_unit.num26 = "26";
        l_unit.num27 = "27";
        l_unit.num28 = "28";
        l_unit.num29 = "29";
        l_unit.num30 = "30";
        l_unit.div4 = "4";
        l_unit.div2 = "4";
        l_unit.div3 = "2";
        l_unit.div5 = "1";
        l_unit.code1 = "1234567";
        l_unit.code3 = "2";
        l_unit.code4 = "3";
        l_unit.code6 = "6";
        l_unit.code10 = "10";
        
        return l_unit;
    }
}
@
