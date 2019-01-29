head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeBranchMarketPTSDealtCondTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : ÅiïîìXésèÍï ÅEPTSÅjéÊàµèåè(WEB3GentradeBranchMarketPTSDealtCond.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/17 âΩï∂ïq (íÜêu) êVãKçÏê¨
*/
package webbroker3.gentrade;


import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;

/**
 * ÅiïîìXésèÍï ÅEPTSÅjéÊàµèåè<BR>
 * <BR>
 * @@author âΩï∂ïq
 * @@version 1.0
 */
public class WEB3GentradeBranchMarketPTSDealtCondTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3GentradeBranchMarketPTSDealtCondTest.class);

    public WEB3GentradeBranchMarketPTSDealtCondTest(String arg0)
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
    
    public void testGetDataSourceObject()
    {
        final String STR_METHOD_NAME = "testGetDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            WEB3GentradeBranchMarketPTSDealtCond l_cond =
                new WEB3GentradeBranchMarketPTSDealtCond(l_branchMarketPtsDealtCondParams);
            Object l_obj = l_cond.getDataSourceObject();
            assertEquals(((BranchMarketPtsDealtCondParams)l_obj).getBranchCode(), "624");
            assertEquals(((BranchMarketPtsDealtCondParams)l_obj).getInstitutionCode(), "0D");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testWEB3GentradeBranchMarketPTSDealtCond_case0001()
    {
        final String STR_METHOD_NAME = "testWEB3GentradeBranchMarketPTSDealtCond_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            WEB3GentradeBranchMarketPTSDealtCond l_cond =
                new WEB3GentradeBranchMarketPTSDealtCond("0D", "624", "11");
            Object l_obj = l_cond.getDataSourceObject();
            assertEquals(((BranchMarketPtsDealtCondParams)l_obj).getMartCanDealtEquity(), "1");
            assertEquals(((BranchMarketPtsDealtCondParams)l_obj).getMaxHandlingPrice(), 120L);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testWEB3GentradeBranchMarketPTSDealtCond_case0002()
    {
        final String STR_METHOD_NAME = "testWEB3GentradeBranchMarketPTSDealtCond_case0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            new WEB3GentradeBranchMarketPTSDealtCond("0D", "625", "11");
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testWEB3GentradeBranchMarketPTSDealtCond_case0003()
    {
        final String STR_METHOD_NAME = "testWEB3GentradeBranchMarketPTSDealtCond_case0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            new WEB3GentradeBranchMarketPTSDealtCond(null);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsHandlingPossible_case0001()
    {
        final String STR_METHOD_NAME = "testIsHandlingPossible_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            WEB3GentradeBranchMarketPTSDealtCond l_cond =
            new WEB3GentradeBranchMarketPTSDealtCond("0D", "624", "11");
            l_cond.isHandlingPossible(ProductTypeEnum.BOND);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsHandlingPossible_case0002()
    {
        final String STR_METHOD_NAME = "testIsHandlingPossible_case0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            WEB3GentradeBranchMarketPTSDealtCond l_cond =
            new WEB3GentradeBranchMarketPTSDealtCond("0D", "624", "11");
            boolean l_bln = l_cond.isHandlingPossible(ProductTypeEnum.EQUITY);
            
            System.out.println("******************!"+ProductTypeEnum.EQUITY);
            assertTrue(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsHandlingPossible_case0003()
    {
        final String STR_METHOD_NAME = "testIsHandlingPossible_case0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("0");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            WEB3GentradeBranchMarketPTSDealtCond l_cond =
                new WEB3GentradeBranchMarketPTSDealtCond("0D", "624", "11");
            boolean l_bln = l_cond.isHandlingPossible(ProductTypeEnum.EQUITY);
            assertFalse(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetBranchMarketPTSDealtCond()
    {
        final String STR_METHOD_NAME = "testGetBranchMarketPTSDealtCond()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_branchMarketPtsDealtCondParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_branchMarketPtsDealtCondParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            Institution l_institution = new InstitutionImpl(l_institutionParams);
            WEB3GentradeBranch l_gentradeBranch = new WEB3GentradeBranch(l_institution, l_branchParams.getBranchCode());
            WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
                WEB3GentradeBranchMarketPTSDealtCond.getBranchMarketPTSDealtCond(l_gentradeBranch);
            boolean l_bln = l_branchMarketPTSDealtConds[0].isHandlingPossible(ProductTypeEnum.EQUITY);
            assertTrue(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBranchMarketPTSDealtCond_case0001()
    {
        final String STR_METHOD_NAME = "testGetBranchMarketPTSDealtCond_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
                WEB3GentradeBranchMarketPTSDealtCond.getBranchMarketPTSDealtCond(l_branchMarketPtsDealtCondParams.getInstitutionCode());
            boolean l_bln = l_branchMarketPTSDealtConds[0].isHandlingPossible(ProductTypeEnum.EQUITY);
            assertTrue(l_bln);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetMarketCode()
    {
        final String STR_METHOD_NAME = "testGetMarketCode()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            WEB3GentradeBranchMarketPTSDealtCond l_cond =
                new WEB3GentradeBranchMarketPTSDealtCond(l_branchMarketPtsDealtCondParams);
            String l_strMarketCode = l_cond.getMarketCode();
            assertEquals(l_strMarketCode, "11");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetHandlingPossibleMarkete_case0001()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarkete_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_branchMarketPtsDealtCondParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_branchMarketPtsDealtCondParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            Institution l_institution = new InstitutionImpl(l_institutionParams);
            WEB3GentradeBranch l_gentradeBranch = new WEB3GentradeBranch(l_institution, l_branchParams.getBranchCode());
            String[] l_strMarketCodes =
                WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                    l_gentradeBranch, ProductTypeEnum.EQUITY);
            assertEquals(l_strMarketCodes[0], "11");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetHandlingPossibleMarkete_case0002()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarkete_case0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setBranchCode("624");
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams1.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams1.setMarketCode("10");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams1.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_branchMarketPtsDealtCondParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_branchMarketPtsDealtCondParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            Institution l_institution = new InstitutionImpl(l_institutionParams);
            WEB3GentradeBranch l_gentradeBranch = new WEB3GentradeBranch(l_institution, l_branchParams.getBranchCode());
            String[] l_strMarketCodes =
                WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                    l_gentradeBranch, ProductTypeEnum.EQUITY);
            assertEquals(l_strMarketCodes[0], "10");
            assertEquals(l_strMarketCodes[1], "11");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetHandlingPossibleMarkete_case0003()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarkete_case0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("625");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_branchMarketPtsDealtCondParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            Institution l_institution = new InstitutionImpl(l_institutionParams);
            WEB3GentradeBranch l_gentradeBranch = new WEB3GentradeBranch(l_institution, l_branchParams.getBranchCode());
            String[] l_strMarketCodes =
                WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                    l_gentradeBranch, ProductTypeEnum.EQUITY);
            assertEquals(l_strMarketCodes.length, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetHandlingPossibleMarkete_case0004()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarkete_case0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_branchMarketPtsDealtCondParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            Institution l_institution = new InstitutionImpl(l_institutionParams);
            WEB3GentradeBranch l_gentradeBranch = new WEB3GentradeBranch(l_institution, l_branchParams.getBranchCode());
            WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                l_gentradeBranch, ProductTypeEnum.AIO);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetHandlingPossibleMarkete_case0005()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarkete_case0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);

            String[] l_strMarketCodes =
                WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                    l_branchMarketPtsDealtCondParams.getInstitutionCode(), ProductTypeEnum.EQUITY);
            assertEquals(l_strMarketCodes[0], "11");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetHandlingPossibleMarkete_case0006()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarkete_case0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setBranchCode("624");
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams1.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams1.setMarketCode("10");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams1.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            
            String[] l_strMarketCodes =
                WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                    l_branchMarketPtsDealtCondParams.getInstitutionCode(), ProductTypeEnum.EQUITY);
            assertEquals(l_strMarketCodes[0], "10");
            assertEquals(l_strMarketCodes[1], "11");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetHandlingPossibleMarkete_case0007()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarkete_case0007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            String[] l_strMarketCodes =
                WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                    "0C", ProductTypeEnum.EQUITY);
            assertEquals(l_strMarketCodes.length, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetHandlingPossibleMarkete_case0008()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarkete_case0008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(120L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);

                WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                    l_branchMarketPtsDealtCondParams.getInstitutionCode(), ProductTypeEnum.AIO);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetMaxHandlingPrice()
    {
        final String STR_METHOD_NAME = "testGetMaxHandlingPrice()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(1234567890L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            WEB3GentradeBranchMarketPTSDealtCond l_cond =
                new WEB3GentradeBranchMarketPTSDealtCond(l_branchMarketPtsDealtCondParams);
            double l_dblMaxHandLingPrice = l_cond.getMaxHandlingPrice();
            assertEquals(l_dblMaxHandLingPrice, 1234567890D, 0D);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetLimitedUnit()
    {
        final String STR_METHOD_NAME = "testGetLimitedUnit()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setBranchCode("624");
            l_branchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_branchMarketPtsDealtCondParams.setLimitedUnit(10);
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(1234567890L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            WEB3GentradeBranchMarketPTSDealtCond l_cond =
                new WEB3GentradeBranchMarketPTSDealtCond(l_branchMarketPtsDealtCondParams);
            double l_dblMaxHandLingPrice = l_cond.getLimitedUnit();
            assertEquals(l_dblMaxHandLingPrice, 10D, 0D);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
