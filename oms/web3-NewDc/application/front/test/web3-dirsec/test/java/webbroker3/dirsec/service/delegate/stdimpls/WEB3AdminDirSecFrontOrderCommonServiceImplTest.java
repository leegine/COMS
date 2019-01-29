head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecFrontOrderCommonServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FrontOrderSystemCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.dirsec.message.WEB3AdminFrontProcessNumberInfoUnit;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontOrderCommonService;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.gentrade.data.VirtualServerChangeParams;
import webbroker3.gentrade.data.VirtualServerInformationParams;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecFrontOrderCommonServiceImplTest extends TestBaseForMock
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecFrontOrderCommonServiceImplTest.class);

    public WEB3AdminDirSecFrontOrderCommonServiceImplTest(String arg0)
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
    
    public void testGetOrderRouteSwitchingRows_0001()
    {
        String STR_METHOD_NAME = "testGetOrderRouteSwitchingRows_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);//1：株式,
        l_orderSwitchingParams.setMarketCode("1");//1：東京
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        } catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        String l_strInstitutionCode = "0D";
        try
        {
            OrderSwitchingRow[] l_orderSwitchingRow = 
                l_adminDirSecFrontOrderCommonServiceImpl.getOrderRouteSwitchingRows(l_strInstitutionCode);
            assertNotNull(l_orderSwitchingRow);
            
            if (l_orderSwitchingRow.length != 1)
            {
                fail();
            }
            
            OrderSwitchingRow l_orderSwitchingRow1 = l_orderSwitchingRow[0];
            assertEquals("0D" , l_orderSwitchingRow1.getInstitutionCode());
            assertEquals(ProductTypeEnum.EQUITY , l_orderSwitchingRow1.getProductType());
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetOrderRouteSwitchingRows_0002()
    {
        String STR_METHOD_NAME = "testGetOrderRouteSwitchingRows_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);//6：先物オプション
        l_orderSwitchingParams.setMarketCode("1");//1：東京
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        String l_strInstitutionCode = "0D";
        try
        {
            OrderSwitchingRow[] l_orderSwitchingRow = 
                l_adminDirSecFrontOrderCommonServiceImpl.getOrderRouteSwitchingRows(l_strInstitutionCode);
            assertNotNull(l_orderSwitchingRow);
            
            if (l_orderSwitchingRow.length != 1)
            {
                fail();
            }
            
            OrderSwitchingRow l_orderSwitchingRow1 = l_orderSwitchingRow[0];
            assertEquals("0D" , l_orderSwitchingRow1.getInstitutionCode());
            assertEquals(ProductTypeEnum.IFO , l_orderSwitchingRow1.getProductType());
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetOrderRouteSwitchingRows_0003()
    {
        String STR_METHOD_NAME = "testGetOrderRouteSwitchingRows_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setProductType(ProductTypeEnum.BOND);
        l_orderSwitchingParams.setMarketCode("1");//1：東京
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        } catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        String l_strInstitutionCode = "0D";
        try
        {
            l_adminDirSecFrontOrderCommonServiceImpl.getOrderRouteSwitchingRows(l_strInstitutionCode);
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02216 , l_ex.getErrorInfo());
//            log.debug(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();            
        }
    }
    
    public void testGetOrderRouteSwitchingRows_0004()
    {
        String STR_METHOD_NAME = "testGetOrderRouteSwitchingRows_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);//1：株式,
        l_orderSwitchingParams.setMarketCode("1");//1：東京
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        String l_strInstitutionCode = "0D";
        try
        {
            OrderSwitchingRow[] l_orderSwitchingRow = 
                l_adminDirSecFrontOrderCommonServiceImpl.getOrderRouteSwitchingRows(l_strInstitutionCode);
            assertNotNull(l_orderSwitchingRow);
            
            if (l_orderSwitchingRow.length != 1)
            {
                fail();
            }
            
            OrderSwitchingRow l_orderSwitchingRow1 = l_orderSwitchingRow[0];
            assertEquals("0D" , l_orderSwitchingRow1.getInstitutionCode());
            assertEquals("1" , l_orderSwitchingRow1.getMarketCode());
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetOrderRouteSwitchingRows_0005()
    {
        String STR_METHOD_NAME = "testGetOrderRouteSwitchingRows_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);//1：株式,
        l_orderSwitchingParams.setMarketCode("2");// 2：大阪
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        String l_strInstitutionCode = "0D";
        try
        {
            OrderSwitchingRow[] l_orderSwitchingRow = 
                l_adminDirSecFrontOrderCommonServiceImpl.getOrderRouteSwitchingRows(l_strInstitutionCode);
            assertNotNull(l_orderSwitchingRow);
            
            if (l_orderSwitchingRow.length != 1)
            {
                fail();
            }
            
            OrderSwitchingRow l_orderSwitchingRow1 = l_orderSwitchingRow[0];
            assertEquals("0D" , l_orderSwitchingRow1.getInstitutionCode());
            assertEquals("2" , l_orderSwitchingRow1.getMarketCode());
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetOrderRouteSwitchingRows_0006()
    {
        String STR_METHOD_NAME = "testGetOrderRouteSwitchingRows_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);//1：株式,
        l_orderSwitchingParams.setMarketCode("3");// 3：名古屋
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        String l_strInstitutionCode = "0D";
        try
        {
            OrderSwitchingRow[] l_orderSwitchingRow = 
                l_adminDirSecFrontOrderCommonServiceImpl.getOrderRouteSwitchingRows(l_strInstitutionCode);
            assertNotNull(l_orderSwitchingRow);
            
            if (l_orderSwitchingRow.length != 1)
            {
                fail();
            }
            
            OrderSwitchingRow l_orderSwitchingRow1 = l_orderSwitchingRow[0];
            assertEquals("0D" , l_orderSwitchingRow1.getInstitutionCode());
            assertEquals("3" , l_orderSwitchingRow1.getMarketCode());
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetOrderRouteSwitchingRows_0007()
    {
        String STR_METHOD_NAME = "testGetOrderRouteSwitchingRows_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);//1：株式,
        l_orderSwitchingParams.setMarketCode("9");// 9：NNM
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        String l_strInstitutionCode = "0D";
        try
        {
            OrderSwitchingRow[] l_orderSwitchingRow = 
                l_adminDirSecFrontOrderCommonServiceImpl.getOrderRouteSwitchingRows(l_strInstitutionCode);
            assertNotNull(l_orderSwitchingRow);
            
            if (l_orderSwitchingRow.length != 1)
            {
                fail();
            }
            
            OrderSwitchingRow l_orderSwitchingRow1 = l_orderSwitchingRow[0];
            assertEquals("0D" , l_orderSwitchingRow1.getInstitutionCode());
            assertEquals("9" , l_orderSwitchingRow1.getMarketCode());
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetOrderRouteSwitchingRows_0008()
    {
        String STR_METHOD_NAME = "testGetOrderRouteSwitchingRows_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);//1：株式,
        l_orderSwitchingParams.setMarketCode("10");// 10：JASDAQ
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        String l_strInstitutionCode = "0D";
        try
        {
            OrderSwitchingRow[] l_orderSwitchingRow = 
                l_adminDirSecFrontOrderCommonServiceImpl.getOrderRouteSwitchingRows(l_strInstitutionCode);
            assertNotNull(l_orderSwitchingRow);
            
            if (l_orderSwitchingRow.length != 1)
            {
                fail();
            }
            
            OrderSwitchingRow l_orderSwitchingRow1 = l_orderSwitchingRow[0];
            assertEquals("0D" , l_orderSwitchingRow1.getInstitutionCode());
            assertEquals("10" , l_orderSwitchingRow1.getMarketCode());
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetOrderRouteSwitchingRows_0009()
    {
        String STR_METHOD_NAME = "testGetOrderRouteSwitchingRows_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);//1：株式
        l_orderSwitchingParams.setMarketCode("0");//other
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        } catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        String l_strInstitutionCode = "0D";
        try
        {
            l_adminDirSecFrontOrderCommonServiceImpl.getOrderRouteSwitchingRows(l_strInstitutionCode);
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02216 , l_ex.getErrorInfo());
            log.debug(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();            
        }
    }
    
    public void testGetOrderRouteSwitchingRows_0010()
    {
        String STR_METHOD_NAME = "testGetOrderRouteSwitchingRows_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);//1：株式,
        l_orderSwitchingParams.setMarketCode("1");// 1：東京
        l_orderSwitchingParams.setSubmitOrderRouteDiv("0");//発注経路区分
        l_orderSwitchingParams.setFrontOrderSystemCode("1");//フロント発注システム区分

        OrderSwitchingParams l_orderSwitchingParams1 = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams1.setProductType(ProductTypeEnum.IFO);//1：株式,
        l_orderSwitchingParams1.setMarketCode("1");// 1：東京
        l_orderSwitchingParams1.setSubmitOrderRouteDiv("0");//発注経路区分
        l_orderSwitchingParams1.setFrontOrderSystemCode("1");//フロント発注システム区分

        OrderSwitchingParams l_orderSwitchingParams2 = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams2.setProductType(ProductTypeEnum.IFO);//1：株式,
        l_orderSwitchingParams2.setMarketCode("2");// 2：大阪
        l_orderSwitchingParams2.setSubmitOrderRouteDiv("0");//発注経路区分
        l_orderSwitchingParams2.setFrontOrderSystemCode("1");//フロント発注システム区分

        OrderSwitchingParams l_orderSwitchingParams3 = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams3.setProductType(ProductTypeEnum.IFO);//1：株式,
        l_orderSwitchingParams3.setMarketCode("2");// 3：名古屋
        l_orderSwitchingParams3.setSubmitOrderRouteDiv("1");//発注経路区分
        l_orderSwitchingParams3.setFrontOrderSystemCode("1");//フロント発注システム区分

        OrderSwitchingParams l_orderSwitchingParams4 = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams4.setProductType(ProductTypeEnum.IFO);//1：株式,
        l_orderSwitchingParams4.setMarketCode("2");// 3：名古屋
        l_orderSwitchingParams4.setSubmitOrderRouteDiv("1");//発注経路区分
        l_orderSwitchingParams4.setFrontOrderSystemCode("2");//フロント発注システム区分

        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams1);
            TestDBUtility.insertWithDel(l_orderSwitchingParams2);
            TestDBUtility.insertWithDel(l_orderSwitchingParams3);
            TestDBUtility.insertWithDel(l_orderSwitchingParams4);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        String l_strInstitutionCode = "0D";
        ProductTypeEnum[] l_productType = {
            ProductTypeEnum.EQUITY, 
            ProductTypeEnum.IFO, 
            ProductTypeEnum.IFO, 
            ProductTypeEnum.IFO, 
            ProductTypeEnum.IFO
        };
        String[] l_marketCode = {
            "1", "1", "2", "2", "2"
        };
        String[] l_submitOrderRouteDiv = {
            "0", "0", "0", "1", "1"
        };
        String[] l_frontOrderSystemCode = {
            "1", "1", "1", "1", "2"
        };
        try
        {
            OrderSwitchingRow[] l_orderSwitchingRow = 
                l_adminDirSecFrontOrderCommonServiceImpl.getOrderRouteSwitchingRows(l_strInstitutionCode);
            assertNotNull(l_orderSwitchingRow);
            
            for (int i = 0; i < l_orderSwitchingRow.length; i++)
            {
                assertEquals(l_productType[i] , l_orderSwitchingRow[i].getProductType());
                assertEquals(l_marketCode[i] , l_orderSwitchingRow[i].getMarketCode());
                assertEquals(l_submitOrderRouteDiv[i] , l_orderSwitchingRow[i].getSubmitOrderRouteDiv());
                assertEquals(l_frontOrderSystemCode[i] , l_orderSwitchingRow[i].getFrontOrderSystemCode());
            }
            log.exiting(STR_METHOD_NAME);
        } catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateSonarCheck_0001()
    {
        String STR_METHOD_NAME = "testValidateSonarCheck_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        VirtualServerChangeParams l_virtualServerChangeParams = getVirtualServerChangeRow();
        VirtualServerChangeParams l_virtualServerChangeParams1 = getVirtualServerChangeRow();
        l_virtualServerChangeParams1.setFrontOrderExchangeCode("2");
        try
        {
            TestDBUtility.deleteAll(l_virtualServerChangeParams.getRowType());
            TestDBUtility.insertWithDel(l_virtualServerChangeParams);
            TestDBUtility.insertWithDel(l_virtualServerChangeParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();

        try
        {
            l_adminDirSecFrontOrderCommonServiceImpl.validateSonarCheck("123", "3", "1", "6");
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02213 , l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateSonarCheck_0002()
    {
        String STR_METHOD_NAME = "testValidateSonarCheck_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        VirtualServerChangeParams l_virtualServerChangeParams = getVirtualServerChangeRow();
        VirtualServerChangeParams l_virtualServerChangeParams1 = getVirtualServerChangeRow();
        l_virtualServerChangeParams1.setFrontOrderExchangeCode("2");
        try
        {
            TestDBUtility.deleteAll(l_virtualServerChangeParams.getRowType());
            TestDBUtility.insertWithDel(l_virtualServerChangeParams);
            TestDBUtility.insertWithDel(l_virtualServerChangeParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();

        try
        {
            l_adminDirSecFrontOrderCommonServiceImpl.validateSonarCheck("123", "1", "1", "6");
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02213 , l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateSonarCheck_0003()
    {
        String STR_METHOD_NAME = "testValidateSonarCheck_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        VirtualServerChangeParams l_virtualServerChangeParams = getVirtualServerChangeRow();
        VirtualServerChangeParams l_virtualServerChangeParams1 = getVirtualServerChangeRow();
        l_virtualServerChangeParams1.setProductType(ProductTypeEnum.EQUITY);
        try
        {
            TestDBUtility.deleteAll(l_virtualServerChangeParams.getRowType());
            TestDBUtility.insertWithDel(l_virtualServerChangeParams);
            TestDBUtility.insertWithDel(l_virtualServerChangeParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();

        try
        {
            l_adminDirSecFrontOrderCommonServiceImpl.validateSonarCheck("123", "3", "1", "8");
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02213 , l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateSonarCheck_0004()
    {
        String STR_METHOD_NAME = "testValidateSonarCheck_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        VirtualServerChangeParams l_virtualServerChangeParams = getVirtualServerChangeRow();
        VirtualServerChangeParams l_virtualServerChangeParams1 = getVirtualServerChangeRow();
        l_virtualServerChangeParams1.setProductType(ProductTypeEnum.EQUITY);
        try
        {
            TestDBUtility.deleteAll(l_virtualServerChangeParams.getRowType());
            TestDBUtility.insertWithDel(l_virtualServerChangeParams);
            TestDBUtility.insertWithDel(l_virtualServerChangeParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();

        try
        {
            l_adminDirSecFrontOrderCommonServiceImpl.validateSonarCheck("123", "1", "1", "6");
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02213 , l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetIfoGrayOrder_0001()
    {
        String STR_METHOD_NAME = "testGetIfoGrayOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = getHostFotypeOrderAllRow();
        //口座ＩＤ
        l_hostFotypeOrderAllParams1.setAccountId(2);
        //証券会社コード 
        l_hostFotypeOrderAllParams1.setInstitutionCode("123");
        //フロント発注取引所区分コード 
        l_hostFotypeOrderAllParams1.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_hostFotypeOrderAllParams1.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_hostFotypeOrderAllParams1.setFrontOrderTradeCode("1");
        //発注経路区分
        l_hostFotypeOrderAllParams1.setSubmitOrderRouteDiv("2");
        //処理区分
        l_hostFotypeOrderAllParams1.setStatus("9");
        //社内処理項目
        l_hostFotypeOrderAllParams1.setProductCode("2");
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = getHostFotypeOrderAllRow();
        //口座ＩＤ
        l_hostFotypeOrderAllParams2.setAccountId(3);
        //証券会社コード 
        l_hostFotypeOrderAllParams2.setInstitutionCode("111");
        //フロント発注取引所区分コード 
        l_hostFotypeOrderAllParams2.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_hostFotypeOrderAllParams2.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_hostFotypeOrderAllParams2.setFrontOrderTradeCode("1");
        //発注経路区分
        l_hostFotypeOrderAllParams2.setSubmitOrderRouteDiv("2");
        //処理区分
        l_hostFotypeOrderAllParams2.setStatus("9");
        //社内処理項目
        l_hostFotypeOrderAllParams2.setProductCode("3");
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = getHostFotypeOrderAllRow();
        //口座ＩＤ
        l_hostFotypeOrderAllParams3.setAccountId(4);
        //証券会社コード 
        l_hostFotypeOrderAllParams3.setInstitutionCode("123");
        //フロント発注取引所区分コード 
        l_hostFotypeOrderAllParams3.setFrontOrderExchangeCode("2");
        //フロント発注システム区分
        l_hostFotypeOrderAllParams3.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_hostFotypeOrderAllParams3.setFrontOrderTradeCode("1");
        //発注経路区分
        l_hostFotypeOrderAllParams3.setSubmitOrderRouteDiv("2");
        //処理区分
        l_hostFotypeOrderAllParams3.setStatus("9");
        //社内処理項目
        l_hostFotypeOrderAllParams3.setProductCode("4");
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = getHostFotypeOrderAllRow();
        //口座ＩＤ
        l_hostFotypeOrderAllParams4.setAccountId(5);
        //証券会社コード 
        l_hostFotypeOrderAllParams4.setInstitutionCode("123");
        //フロント発注取引所区分コード 
        l_hostFotypeOrderAllParams4.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_hostFotypeOrderAllParams4.setFrontOrderSystemCode("3");
        //フロント発注取引区分コード
        l_hostFotypeOrderAllParams4.setFrontOrderTradeCode("1");
        //発注経路区分
        l_hostFotypeOrderAllParams4.setSubmitOrderRouteDiv("2");
        //処理区分
        l_hostFotypeOrderAllParams4.setStatus("9");
        //社内処理項目
        l_hostFotypeOrderAllParams4.setProductCode("5");
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams5 = getHostFotypeOrderAllRow();
        //口座ＩＤ
        l_hostFotypeOrderAllParams5.setAccountId(6);
        //証券会社コード 
        l_hostFotypeOrderAllParams5.setInstitutionCode("123");
        //フロント発注取引所区分コード 
        l_hostFotypeOrderAllParams5.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_hostFotypeOrderAllParams5.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_hostFotypeOrderAllParams5.setFrontOrderTradeCode("2");
        //発注経路区分
        l_hostFotypeOrderAllParams5.setSubmitOrderRouteDiv("2");
        //処理区分
        l_hostFotypeOrderAllParams5.setStatus("9");
        //社内処理項目
        l_hostFotypeOrderAllParams5.setProductCode("6");

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams6 = getHostFotypeOrderAllRow();
        //口座ＩＤ
        l_hostFotypeOrderAllParams6.setAccountId(7);
        //証券会社コード 
        l_hostFotypeOrderAllParams6.setInstitutionCode("123");
        //フロント発注取引所区分コード 
        l_hostFotypeOrderAllParams6.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_hostFotypeOrderAllParams6.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_hostFotypeOrderAllParams6.setFrontOrderTradeCode("1");
        //発注経路区分
        l_hostFotypeOrderAllParams6.setSubmitOrderRouteDiv("4");
        //処理区分
        l_hostFotypeOrderAllParams6.setStatus("9");
        //社内処理項目
        l_hostFotypeOrderAllParams6.setProductCode("7");

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams7 = getHostFotypeOrderAllRow();
        //口座ＩＤ
        l_hostFotypeOrderAllParams7.setAccountId(8);
        //証券会社コード 
        l_hostFotypeOrderAllParams7.setInstitutionCode("123");
        //フロント発注取引所区分コード 
        l_hostFotypeOrderAllParams7.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_hostFotypeOrderAllParams7.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_hostFotypeOrderAllParams7.setFrontOrderTradeCode("1");
        //発注経路区分
        l_hostFotypeOrderAllParams7.setSubmitOrderRouteDiv("3");
        //処理区分
        l_hostFotypeOrderAllParams7.setStatus("8");
        //社内処理項目
        l_hostFotypeOrderAllParams7.setProductCode("7");

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams8 = getHostFotypeOrderAllRow();
        //口座ＩＤ
        l_hostFotypeOrderAllParams8.setAccountId(9);
        //証券会社コード 
        l_hostFotypeOrderAllParams8.setInstitutionCode("123");
        //フロント発注取引所区分コード 
        l_hostFotypeOrderAllParams8.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_hostFotypeOrderAllParams8.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_hostFotypeOrderAllParams8.setFrontOrderTradeCode("1");
        //発注経路区分
        l_hostFotypeOrderAllParams8.setSubmitOrderRouteDiv("2");
        //処理区分
        l_hostFotypeOrderAllParams8.setStatus("1");
        //社内処理項目
        l_hostFotypeOrderAllParams8.setProductCode("8");

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams9 = getHostFotypeOrderAllRow();
        //口座ＩＤ
        l_hostFotypeOrderAllParams9.setAccountId(10);
        //証券会社コード 
        l_hostFotypeOrderAllParams9.setInstitutionCode("123");
        //フロント発注取引所区分コード 
        l_hostFotypeOrderAllParams9.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_hostFotypeOrderAllParams9.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_hostFotypeOrderAllParams9.setFrontOrderTradeCode("1");
        //発注経路区分
        l_hostFotypeOrderAllParams9.setSubmitOrderRouteDiv("2");
        //処理区分
        l_hostFotypeOrderAllParams9.setStatus("2");
        //社内処理項目
        l_hostFotypeOrderAllParams9.setProductCode("9");
        
        try
        {
            TestDBUtility.deleteAll(l_hostFotypeOrderAllParams1.getRowType());
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams3);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams4);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams5);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams6);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams7);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams8);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams9);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
                new WEB3AdminDirSecFrontOrderCommonServiceImpl();
            
            WEB3AdminFrontProcessNumberInfoUnit l_infoUnit = new WEB3AdminFrontProcessNumberInfoUnit();

            l_adminDirSecFrontOrderCommonServiceImpl.getIfoGrayOrder("123", "1", "1", l_infoUnit);
            
            assertEquals("2" , l_infoUnit.beforeNumber);
            assertEquals("1" , l_infoUnit.inNumber);
            assertEquals("1" , l_infoUnit.afterNumber);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetSwitchRouteResRcord_0001()
    {
        String STR_METHOD_NAME = "testGetSwitchRouteResRcord_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        VirtualServerChangeParams l_virtualServerChangeParams = getVirtualServerChangeRow();
        //切替指示応答区分
        l_virtualServerChangeParams.setChangeReqResDiv("08");
        
        VirtualServerChangeParams l_virtualServerChangeParams1 = getVirtualServerChangeRow();
        //切替指示応答区分
        l_virtualServerChangeParams1.setChangeReqResDiv("08");
        //銘柄タイプ
        l_virtualServerChangeParams.setProductType(ProductTypeEnum.EQUITY);
        
        try
        {
            TestDBUtility.deleteAll(l_virtualServerChangeParams.getRowType());
            TestDBUtility.insertWithDel(l_virtualServerChangeParams);
            TestDBUtility.insertWithDel(l_virtualServerChangeParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();

        try
        {
            List l_list = l_adminDirSecFrontOrderCommonServiceImpl.getSwitchRouteResRcord("123", "1", "1", "6");
            int[] l_productType = {6};
            
            for (int i = 0; i < l_list.size(); i++)
            {
                assertEquals(l_productType[i] , ((VirtualServerChangeParams)l_list.get(i)).getProductType().intValue());
            }
        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetSwitchRouteReqRcord_0001()
    {
        String STR_METHOD_NAME = "testGetSwitchRouteReqRcord_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        VirtualServerChangeParams l_virtualServerChangeParams = getVirtualServerChangeRow();
        //切替指示応答区分
        l_virtualServerChangeParams.setChangeReqResDiv("07");
        
        VirtualServerChangeParams l_virtualServerChangeParams1 = getVirtualServerChangeRow();
        //切替指示応答区分
        l_virtualServerChangeParams1.setChangeReqResDiv("07");
        //銘柄タイプ
        l_virtualServerChangeParams.setProductType(ProductTypeEnum.EQUITY);
        
        try
        {
            TestDBUtility.deleteAll(l_virtualServerChangeParams.getRowType());
            TestDBUtility.insertWithDel(l_virtualServerChangeParams);
            TestDBUtility.insertWithDel(l_virtualServerChangeParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();

        try
        {
            List l_list = l_adminDirSecFrontOrderCommonServiceImpl.getSwitchRouteResRcord("123", "1", "1", "6");
            int[] l_productType = {6};
            
            for (int i = 0; i < l_list.size(); i++)
            {
                assertEquals(l_productType[i] , ((VirtualServerChangeParams)l_list.get(i)).getProductType().intValue());
            }
        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetFrontSwitchRouteTarget_0001()
    {
        String STR_METHOD_NAME = "testGetFrontSwitchRouteTarget_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        //2   銘柄タイプ    product_type    NUMBER   1   NotNull
        l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
        //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION);
        
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        try
        {
            List list = l_adminDirSecFrontOrderCommonServiceImpl.getFrontSwitchRouteTarget("0D");
            
            int[] l_expect = {1};
            
            for (int i = 0; i < list.size(); i++)
            {
                assertEquals(l_expect[i] , ((OrderSwitchingParams)list.get(i)).getProductType().intValue());
            }
        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

    }

    public void testGetFrontSwitchRouteTarget_0002()
    {
        String STR_METHOD_NAME = "testGetFrontSwitchRouteTarget_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        //2   銘柄タイプ    product_type    NUMBER   1   NotNull
        l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);
        //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION);
        
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        try
        {
            List list = l_adminDirSecFrontOrderCommonServiceImpl.getFrontSwitchRouteTarget("0D");
            
            int[] l_expect = {6};
            
            for (int i = 0; i < list.size(); i++)
            {
                assertEquals(l_expect[i] , ((OrderSwitchingParams)list.get(i)).getProductType().intValue());
            }
        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testGetFrontSwitchRouteTarget_0003()
    {
        String STR_METHOD_NAME = "testGetFrontSwitchRouteTarget_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        //2   銘柄タイプ    product_type    NUMBER   1   NotNull
        l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
        //3   市場コード    market_code    VARCHAR2   2   NotNull
        l_orderSwitchingParams.setMarketCode(WEB3MarketCodeDef.SAPPORO);
        //10  フロント発注システム区分    front_order_system_code    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK);
        //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION);
        
        OrderSwitchingParams l_orderSwitchingParams1 = TestDBUtility.getOrderSwitchingRow();
        //2   銘柄タイプ    product_type    NUMBER   1   NotNull
        l_orderSwitchingParams1.setProductType(ProductTypeEnum.IFO);
        //3   市場コード    market_code    VARCHAR2   2   NotNull
        l_orderSwitchingParams1.setMarketCode(WEB3MarketCodeDef.SAPPORO);
        //10  フロント発注システム区分    front_order_system_code    VARCHAR2   1   NotNull
        l_orderSwitchingParams1.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK);
        //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams1.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION);
        
        OrderSwitchingParams l_orderSwitchingParams2 = TestDBUtility.getOrderSwitchingRow();
        //2   銘柄タイプ    product_type    NUMBER   1   NotNull
        l_orderSwitchingParams2.setProductType(ProductTypeEnum.IFO);
        //3   市場コード    market_code    VARCHAR2   2   NotNull
        l_orderSwitchingParams2.setMarketCode(WEB3MarketCodeDef.NNM);
        //10  フロント発注システム区分    front_order_system_code    VARCHAR2   1   NotNull
        l_orderSwitchingParams2.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK);
        //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams2.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION);
        
        OrderSwitchingParams l_orderSwitchingParams3 = TestDBUtility.getOrderSwitchingRow();
        //2   銘柄タイプ    product_type    NUMBER   1   NotNull
        l_orderSwitchingParams3.setProductType(ProductTypeEnum.IFO);
        //3   市場コード    market_code    VARCHAR2   2   NotNull
        l_orderSwitchingParams3.setMarketCode(WEB3MarketCodeDef.NNM);
        //10  フロント発注システム区分    front_order_system_code    VARCHAR2   1   NotNull
        l_orderSwitchingParams3.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.JASDAQ_MM);
        //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams3.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION);
        
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams1);
            TestDBUtility.insertWithDel(l_orderSwitchingParams2);
            TestDBUtility.insertWithDel(l_orderSwitchingParams3);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        try
        {
            List list = l_adminDirSecFrontOrderCommonServiceImpl.getFrontSwitchRouteTarget("0D");
            
            int[] l_intProductTypeExpect = {1, 6, 6, 6};
            String[] l_strMarketCodeExpect = {"8", "8", "9", "9"};
            String[] l_strFrontOrderSystemCodeExpect = {"1", "1", "1", "3"};
            
            for (int i = 0; i < list.size(); i++)
            {
                assertEquals(l_intProductTypeExpect[i] , ((OrderSwitchingParams)list.get(i)).getProductType().intValue());
                assertEquals(l_strMarketCodeExpect[i] , ((OrderSwitchingParams)list.get(i)).getMarketCode());
                assertEquals(l_strFrontOrderSystemCodeExpect[i] , ((OrderSwitchingParams)list.get(i)).getFrontOrderSystemCode());
            }
        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetVitualServerCount_0001()
    {
        String STR_METHOD_NAME = "testGetVitualServerCount_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        VirtualServerInformationParams l_virtualServerInformationParams = getVirtualServerInformationRow();
        VirtualServerInformationParams l_virtualServerInformationParams1 = getVirtualServerInformationRow();
        //仮想サーバNo.（JSOES）
        l_virtualServerInformationParams1.setVirtualServerNumberJsoes("123457");
        l_virtualServerInformationParams1.setProductType(ProductTypeEnum.EQUITY);
        
        try
        {
            TestDBUtility.deleteAll(l_virtualServerInformationParams.getRowType());
            TestDBUtility.insertWithDel(l_virtualServerInformationParams);
            TestDBUtility.insertWithDel(l_virtualServerInformationParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();

        WEB3AdminFrontProcessNumberInfoUnit l_infoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
        try
        {
            l_adminDirSecFrontOrderCommonServiceImpl.getVitualServerCount("123", "1", "1", "6", l_infoUnit);
            
            assertEquals("1" , l_infoUnit.virtualServerQuantity);
        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testGetVitualServerCount_0002()
    {
        String STR_METHOD_NAME = "testGetVitualServerCount_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        VirtualServerInformationParams l_virtualServerInformationParams = getVirtualServerInformationRow();
        //銘柄タイプ
        l_virtualServerInformationParams.setProductType(ProductTypeEnum.EQUITY);
        VirtualServerInformationParams l_virtualServerInformationParams1 = getVirtualServerInformationRow();
        //仮想サーバNo.（JSOES）
        l_virtualServerInformationParams1.setVirtualServerNumberJsoes("123457");
        //銘柄タイプ
        l_virtualServerInformationParams1.setProductType(ProductTypeEnum.EQUITY);
        
        try
        {
            TestDBUtility.deleteAll(l_virtualServerInformationParams.getRowType());
            TestDBUtility.insertWithDel(l_virtualServerInformationParams);
            TestDBUtility.insertWithDel(l_virtualServerInformationParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();

        WEB3AdminFrontProcessNumberInfoUnit l_infoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
        try
        {
            l_adminDirSecFrontOrderCommonServiceImpl.getVitualServerCount("123", "1", "1", "1", l_infoUnit);
            
            assertEquals("2" , l_infoUnit.virtualServerQuantity);
        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testGetVitualServerCount_0003()
    {
        String STR_METHOD_NAME = "testGetVitualServerCount_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        VirtualServerInformationParams l_virtualServerInformationParams = getVirtualServerInformationRow();
        VirtualServerInformationParams l_virtualServerInformationParams1 = getVirtualServerInformationRow();
        //仮想サーバNo.（JSOES）
        l_virtualServerInformationParams1.setVirtualServerNumberJsoes("123457");
        
        try
        {
            TestDBUtility.deleteAll(l_virtualServerInformationParams.getRowType());
            TestDBUtility.insertWithDel(l_virtualServerInformationParams);
            TestDBUtility.insertWithDel(l_virtualServerInformationParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();

        WEB3AdminFrontProcessNumberInfoUnit l_infoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
        try
        {
            l_adminDirSecFrontOrderCommonServiceImpl.getVitualServerCount("123", "11", "1", "1", l_infoUnit);
            
            assertEquals("0" , l_infoUnit.virtualServerQuantity);
        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testGetVitualServerCount_0004()
    {
        String STR_METHOD_NAME = "testGetVitualServerCount_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        VirtualServerInformationParams l_virtualServerInformationParams = getVirtualServerInformationRow();
        VirtualServerInformationParams l_virtualServerInformationParams1 = getVirtualServerInformationRow();
        //仮想サーバNo.（JSOES）
        l_virtualServerInformationParams1.setVirtualServerNumberJsoes("123457");
        
        try
        {
            TestDBUtility.deleteAll(l_virtualServerInformationParams.getRowType());
            TestDBUtility.insertWithDel(l_virtualServerInformationParams);
            TestDBUtility.insertWithDel(l_virtualServerInformationParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();

        WEB3AdminFrontProcessNumberInfoUnit l_infoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
        try
        {
            l_adminDirSecFrontOrderCommonServiceImpl.getVitualServerCount("123", "1", "1", "6", l_infoUnit);
            
            assertEquals("2" , l_infoUnit.virtualServerQuantity);
        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testGetVitualServerCount_0005()
    {
        String STR_METHOD_NAME = "testGetVitualServerCount_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        VirtualServerInformationParams l_virtualServerInformationParams = getVirtualServerInformationRow();
        VirtualServerInformationParams l_virtualServerInformationParams1 = getVirtualServerInformationRow();
        //仮想サーバNo.（JSOES）
        l_virtualServerInformationParams1.setVirtualServerNumberJsoes("123457");
        
        try
        {
            TestDBUtility.deleteAll(l_virtualServerInformationParams.getRowType());
            TestDBUtility.insertWithDel(l_virtualServerInformationParams);
            TestDBUtility.insertWithDel(l_virtualServerInformationParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();

        WEB3AdminFrontProcessNumberInfoUnit l_infoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
        try
        {
            l_adminDirSecFrontOrderCommonServiceImpl.getVitualServerCount("123", "11", "1", "6", l_infoUnit);
            
            assertEquals("0" , l_infoUnit.virtualServerQuantity);
        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testGetNowOrderRoute_0001()
    {
        String STR_METHOD_NAME = "testGetNowOrderRoute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        //6   有効フラグ    valid_flag    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setValidFlag(WEB3ValidFlag.ON);
        
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();            
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
        try
        {
            String l_submitOrderRouteDiv = l_adminDirSecFrontOrderCommonServiceImpl.getNowOrderRoute("0D", "N1", "1", "6", l_processInfoUnit);
            
            assertEquals("0" , l_submitOrderRouteDiv);
        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();            
        }
    }
    
    public void testGetNowOrderRoute_0002()
    {
        String STR_METHOD_NAME = "testGetNowOrderRoute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        //6   有効フラグ    valid_flag    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setValidFlag(WEB3ValidFlag.ON);
        
        try
        {
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();            
        }
        
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_adminDirSecFrontOrderCommonServiceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
        try
        {
            String l_submitOrderRouteDiv = l_adminDirSecFrontOrderCommonServiceImpl.getNowOrderRoute("0D", "N1", "1", "1", l_processInfoUnit);
            
            assertEquals("9" , l_submitOrderRouteDiv);
        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();            
        }
    }
    
    private VirtualServerInformationParams getVirtualServerInformationRow()
    {
        VirtualServerInformationParams l_virtualServerInformationParams = new VirtualServerInformationParams();
        //仮想サーバNo.（JSOES）
        l_virtualServerInformationParams.setVirtualServerNumberJsoes("123456");
        //証券会社コード
        l_virtualServerInformationParams.setInstitutionCode("123");
        //フロント発注取引所区分コード
        l_virtualServerInformationParams.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_virtualServerInformationParams.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_virtualServerInformationParams.setFrontOrderTradeCode("1");
        //仮想サーバNo.（市場）
        l_virtualServerInformationParams.setVirtualServerNumberMarket("12345");
        //クライアントNo.
        l_virtualServerInformationParams.setClientNumber("123");
        //通知ファ@イルNo.
        l_virtualServerInformationParams.setNoticeFileNumber("123");
        //作成日付
        l_virtualServerInformationParams.setCreatedTimestamp(new Date());
        //更新日付
        l_virtualServerInformationParams.setLastUpdatedTimestamp(new Date());
        //銘柄タイプ
        l_virtualServerInformationParams.setProductType(ProductTypeEnum.IFO);
        
        return l_virtualServerInformationParams;
    }
    
    private HostFotypeOrderAllParams getHostFotypeOrderAllRow()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        // 取消区分
        l_hostFotypeOrderAllParams.setCancelDiv("1");
        // 社内処理項目
        l_hostFotypeOrderAllParams.setCorpCode("1234567890");
        // 全訂正処理区分
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("1");
        
        return l_hostFotypeOrderAllParams;
    }

    private VirtualServerChangeParams getVirtualServerChangeRow()
    {
        VirtualServerChangeParams l_virtualServerChangeParams = new VirtualServerChangeParams();
        //仮想サーバNo.（市場）
        l_virtualServerChangeParams.setVirtualServerNumberMarket("12345");
        //切替指示応答区分 TODO
        l_virtualServerChangeParams.setChangeReqResDiv("09");
        //通知種別
        l_virtualServerChangeParams.setNoticeType("12");
        //証券会社コード TODO
        l_virtualServerChangeParams.setInstitutionCode("123");
        //フロント発注取引所区分コード TODO
        l_virtualServerChangeParams.setFrontOrderExchangeCode("1");
        //フロント発注システム区分 TODO
        l_virtualServerChangeParams.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード TODO
        l_virtualServerChangeParams.setFrontOrderTradeCode("1");
        //社内処理項目
        l_virtualServerChangeParams.setCorpCode("123456789");
        //取消区分
        l_virtualServerChangeParams.setCancelDiv("1");
        //SONAR代行先仮想サーバNo.（市場）
        l_virtualServerChangeParams.setVirtualServerNumberSonar("12345");
        //クライアントNo.
        l_virtualServerChangeParams.setClientNumber("123");
        //通知ファ@イルNo.
        l_virtualServerChangeParams.setNoticeFileNumber("123");
        //再送要求通知番号from
        l_virtualServerChangeParams.setResendNoticeNumberFrom(1234567890);
        //エラーメッセージ
        l_virtualServerChangeParams.setErrorMessage("1234");
        //実行回数
        l_virtualServerChangeParams.setActionCount(12);
        //処理区分
        l_virtualServerChangeParams.setStatus("1");
        //作成日時
        l_virtualServerChangeParams.setCreatedTimestamp(new Date(20060606));
        //更新日付
        l_virtualServerChangeParams.setLastUpdatedTimestamp(new Date(20060606));
        //銘柄タイプ TODO
        l_virtualServerChangeParams.setProductType(ProductTypeEnum.IFO);
        
        return l_virtualServerChangeParams;
    }
    
    public void test_isFrontRoute_0001()
    {
        WEB3AdminDirSecFrontOrderCommonServiceImpl l_serviceImpl = 
            new WEB3AdminDirSecFrontOrderCommonServiceImpl();
        //証券会社コード
        String l_strInstitutionCode = "456";
        //市場コード
        String l_strMarketCode = "2";
        //フロント発注システム区分
        String l_frontSysDiv = "6";
        //銘柄タイプ
        String l_strProductType = "6";
        
        
        try
        {
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
            OrderSwitchingParams l_order = new OrderSwitchingParams();
            
            //証券会社コード
            l_order.setInstitutionCode("456");
            //銘柄タイプ
            l_order.setProductType(ProductTypeEnum.IFO);
            //市場コード
            l_order.setMarketCode("2");
            //市場コード（SONAR）
            l_order.setSonarMarketCode("2");
            //発注経路区分
            l_order.setSubmitOrderRouteDiv("2");
            //有効フラグ
            l_order.setValidFlag("1");
            //訂正取消可能フラグ
            l_order.setChangeCancelEnableFlag("1");
            //作成日付
            l_order.setCreatedTimestamp(Calendar.getInstance().getTime());
            //更新日付
            l_order.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            //フロント発注システム区分
            l_order.setFrontOrderSystemCode("6");
            //MQトリガ発行
            l_order.setSubmitMqTrigger("1");
            //発注エンジン区分
            l_order.setOrderEngineDiv("1");
            
            TestDBUtility.insertWithDel(l_order);
            
            assertTrue(l_serviceImpl.isFrontRoute(l_strInstitutionCode,l_strMarketCode,l_frontSysDiv,l_strProductType));
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            fail();
        }
    }
    
    //getFrontOrderMarketCode
    //     １）　@市場コードがJASDAQ　@or　@NNMの場合、市場コードを"２"（大証）に変換する。
    //１）　@市場コードがJASDAQ 10
    //返回21
    public void testGetFrontOrderMarketCode_Case1()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderMarketCode_Case1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecFrontOrderCommonService l_service =
                ( WEB3AdminDirSecFrontOrderCommonService)Services.getService( WEB3AdminDirSecFrontOrderCommonService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderMarketCode("10", "1");
            assertEquals("21", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //getFrontOrderMarketCode
    //     １）　@市場コードがJASDAQ　@or　@NNMの場合、市場コードを"２"（大証）に変換する。
    //１）　@市場コードがNNM 9
    //返回21
    public void testGetFrontOrderMarketCode_Case2()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderMarketCode_Case2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecFrontOrderCommonService l_service =
                ( WEB3AdminDirSecFrontOrderCommonService)Services.getService( WEB3AdminDirSecFrontOrderCommonService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderMarketCode("9", "1");
            assertEquals("21", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //getFrontOrderMarketCode
    //     １）　@市場コードがJASDAQ　@or　@NNMの場合、市場コードを"２"（大証）に変換する。
    //１）　@市場コードが東京 1
    //返回11
    public void testGetFrontOrderMarketCode_Case3()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderMarketCode_Case3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecFrontOrderCommonService l_service =
                ( WEB3AdminDirSecFrontOrderCommonService)Services.getService( WEB3AdminDirSecFrontOrderCommonService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderMarketCode("1", "1");
            assertEquals("11", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
