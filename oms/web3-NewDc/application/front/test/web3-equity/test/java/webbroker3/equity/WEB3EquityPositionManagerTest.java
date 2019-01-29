head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.11.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPositionManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPositionManagerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityPositionManagerTest.class);

    public WEB3EquityPositionManagerTest(String arg0)
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
    
    public void testGetContracts_0001() 
    {
        String STR_METHOD_NAME = "testGetContracts_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setAccountId(333812512203L);
        l_eqtypeContractParams.setQuantity(1);
        l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        
        try 
        {
            TestDBUtility.deleteAll(l_eqtypeContractParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        long l_rangeFrom = 333812512222L;
        long l_rangeTo = 333812512222L;
        
        try
        {
            List l_list = WEB3EquityPositionManager.getContracts(l_rangeFrom , l_rangeTo);
            
            assertTrue(l_list.isEmpty());
                 
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetContracts_0002() 
    {
        String STR_METHOD_NAME = "testGetContracts_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setAccountId(333812512203L);
        l_eqtypeContractParams.setQuantity(1);
        l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        
        try 
        {
            TestDBUtility.deleteAll(l_eqtypeContractParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        long l_rangeFrom = 333812512200L;
        long l_rangeTo = 333812512200L;
        
        try
        {
            List l_list = WEB3EquityPositionManager.getContracts(l_rangeFrom , l_rangeTo);
            
            assertTrue(l_list.isEmpty());
                 
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetContracts_0003() 
    {
        String STR_METHOD_NAME = "testGetContracts_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setAccountId(333812512203L);
        l_eqtypeContractParams.setQuantity(0);
        l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        
        try 
        {
            TestDBUtility.deleteAll(l_eqtypeContractParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        long l_rangeFrom = 333812512200L;
        long l_rangeTo = 333812512210L;
        
        try
        {
            List l_list = WEB3EquityPositionManager.getContracts(l_rangeFrom , l_rangeTo);
            
            assertTrue(l_list.isEmpty());
                 
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetContracts_0004() 
    {
        String STR_METHOD_NAME = "testGetContracts_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setAccountId(333812512203L);
        l_eqtypeContractParams.setQuantity(1);
        l_eqtypeContractParams.setProductType(ProductTypeEnum.IFO);
        
        try 
        {
            TestDBUtility.deleteAll(l_eqtypeContractParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        long l_rangeFrom = 333812512200L;
        long l_rangeTo = 333812512210L;
        
        try
        {
            List l_list = WEB3EquityPositionManager.getContracts(l_rangeFrom , l_rangeTo);
            
            assertTrue(l_list.isEmpty());
                 
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetContracts_0005() 
    {
        String STR_METHOD_NAME = "testGetContracts_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setContractId(2134566345L);
        l_eqtypeContractParams.setAccountId(333812512203L);
        l_eqtypeContractParams.setQuantity(1);
        l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        
        EqtypeContractParams l_eqtypeContractParams1 = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams1.setContractId(2134566346L);
        l_eqtypeContractParams1.setAccountId(333812512202L);
        l_eqtypeContractParams1.setQuantity(1);
        l_eqtypeContractParams1.setProductType(ProductTypeEnum.EQUITY);
        
        try 
        {
            TestDBUtility.deleteAll(l_eqtypeContractParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            TestDBUtility.insertWithDel(l_eqtypeContractParams1);
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        long l_rangeFrom = 333812512200L;
        long l_rangeTo = 333812512210L;
        
        try
        {
            List l_list = WEB3EquityPositionManager.getContracts(l_rangeFrom , l_rangeTo);
            
            assertFalse(l_list.isEmpty());
            
            assertEquals(2 , l_list.size());
            
            long[] exceptValue = {333812512202L , 333812512203L};
            
            for (int i = 0; i < l_list.size(); i++) 
            {
                EqtypeContractParams l_eqtypeContractParamsT = (EqtypeContractParams)l_list.get(i);
                assertEquals(exceptValue[i] , l_eqtypeContractParamsT.getAccountId());
            }
                 
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetForcedSettleCloseDateContractList_0001() 
    {
        String STR_METHOD_NAME = "testGetForcedSettleCloseDateContractList_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setForcedsettleorderClosedayCnt(2);

        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setAccountId(333812512203L);
        l_eqtypeContractParams.setQuantity(1);
        l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        
        try 
        {
            TestDBUtility.deleteAll(l_eqtypeContractParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        Institution l_institution = null;
        try
        {
            l_institution = new InstitutionImpl(l_institutionParams);
        } catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        long l_rangeFrom = 333812512222L;
        long l_rangeTo = 333812512222L;
        
        try
        {
            List l_list = WEB3EquityPositionManager.getForcedSettleCloseDateContractList(l_institution , l_rangeFrom , l_rangeTo);
            
            assertTrue(l_list.isEmpty());
                 
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testGetForcedSettleCloseDateContractList_0002() 
    {
        String STR_METHOD_NAME = "testGetForcedSettleCloseDateContractList_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setForcedsettleorderClosedayCnt(2);

        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setAccountId(333812512203L);
        l_eqtypeContractParams.setQuantity(1);
        l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        
        try 
        {
            TestDBUtility.deleteAll(l_eqtypeContractParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        Institution l_institution = null;
        try
        {
            l_institution = new InstitutionImpl(l_institutionParams);
        } catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        long l_rangeFrom = 333812512200L;
        long l_rangeTo = 333812512200L;
        
        try
        {
            List l_list = WEB3EquityPositionManager.getForcedSettleCloseDateContractList(l_institution , l_rangeFrom , l_rangeTo);
            
            assertTrue(l_list.isEmpty());
                 
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testGetForcedSettleCloseDateContractList_0003() 
    {
        String STR_METHOD_NAME = "testGetForcedSettleCloseDateContractList_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setForcedsettleorderClosedayCnt(2);

        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setAccountId(333812512203L);
        l_eqtypeContractParams.setQuantity(0);
        l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        
        try 
        {
            TestDBUtility.deleteAll(l_eqtypeContractParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        Institution l_institution = null;
        try
        {
            l_institution = new InstitutionImpl(l_institutionParams);
        } catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        long l_rangeFrom = 333812512200L;
        long l_rangeTo = 333812512210L;
        
        try
        {
            List l_list = WEB3EquityPositionManager.getForcedSettleCloseDateContractList(l_institution , l_rangeFrom , l_rangeTo);
            
            assertTrue(l_list.isEmpty());
                 
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testGetForcedSettleCloseDateContractList_0004() 
    {
        String STR_METHOD_NAME = "testGetForcedSettleCloseDateContractList_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setForcedsettleorderClosedayCnt(2);

        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setAccountId(333812512203L);
        l_eqtypeContractParams.setQuantity(1);
        l_eqtypeContractParams.setProductType(ProductTypeEnum.IFO);
        
        try 
        {
            TestDBUtility.deleteAll(l_eqtypeContractParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        Institution l_institution = null;
        try
        {
            l_institution = new InstitutionImpl(l_institutionParams);
        } catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        long l_rangeFrom = 333812512200L;
        long l_rangeTo = 333812512210L;
        
        try
        {
            List l_list = WEB3EquityPositionManager.getForcedSettleCloseDateContractList(l_institution , l_rangeFrom , l_rangeTo);
            
            assertTrue(l_list.isEmpty());
                 
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testGetForcedSettleCloseDateContractList_0005() 
    {
        String STR_METHOD_NAME = "testGetForcedSettleCloseDateContractList_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setForcedsettleorderClosedayCnt(2);

        Date l_dateGyoumuhiduke = GtlUtils.getTradingSystem().getBizDate();
        Date l_datKyouseikessaikizituTourai = WEB3DateUtility.addDay(l_dateGyoumuhiduke , 10);
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setAccountId(333812512203L);
        l_eqtypeContractParams.setQuantity(1);
        l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeContractParams.setCloseDate(l_datKyouseikessaikizituTourai);
        
        try 
        {
            TestDBUtility.deleteAll(l_eqtypeContractParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        Institution l_institution = null;
        try
        {
            l_institution = new InstitutionImpl(l_institutionParams);
        } catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        long l_rangeFrom = 333812512200L;
        long l_rangeTo = 333812512210L;
        
        try
        {
            List l_list = WEB3EquityPositionManager.getForcedSettleCloseDateContractList(l_institution , l_rangeFrom , l_rangeTo);
            
            assertTrue(l_list.isEmpty());
                 
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
       }
        
    }
    
    public void testGetForcedSettleCloseDateContractList_0006() 
    {
        String STR_METHOD_NAME = "testGetForcedSettleCloseDateContractList_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setForcedsettleorderClosedayCnt(2);

        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setContractId(2134566345L);
        l_eqtypeContractParams.setAccountId(333812512203L);
        l_eqtypeContractParams.setQuantity(1);
        l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeContractParams.setCloseDate(new Date(19980202L));
        
        EqtypeContractParams l_eqtypeContractParams1 = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams1.setContractId(2134566346L);
        l_eqtypeContractParams1.setAccountId(333812512202L);
        l_eqtypeContractParams1.setQuantity(1);
        l_eqtypeContractParams1.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeContractParams1.setCloseDate(new Date(19980202L));

        try 
        {
            TestDBUtility.deleteAll(l_eqtypeContractParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            TestDBUtility.insertWithDel(l_eqtypeContractParams1);
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        Institution l_institution = null;
        try
        {
            l_institution = new InstitutionImpl(l_institutionParams);
        } catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        long l_rangeFrom = 333812512200L;
        long l_rangeTo = 333812512210L;
        
        try
        {
            List l_list = WEB3EquityPositionManager.getForcedSettleCloseDateContractList(l_institution , l_rangeFrom , l_rangeTo);
            
            assertFalse(l_list.isEmpty());
            
            assertEquals(2 , l_list.size());
            
            long[] except = {333812512202L , 333812512203L};
            
            for (int i = 0; i < l_list.size(); i++) 
            {
                EqtypeContractParams l_eqtypeContractParamsT = (EqtypeContractParams)l_list.get(i);
                
                assertEquals(except[i] , l_eqtypeContractParamsT.getAccountId());
            }
                 
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }

}
@
