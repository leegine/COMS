head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.38.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignRegistAccListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : ŠÇ—ŽÒ‚¨‹q—lî•ñŽè”—¿Š„ˆø·¬ÝÍß°Ý“o˜^ŒÚ‹qÆ‰ïØ¸´½ÄƒeƒXƒg(WEB3AdminAccInfoCampaignRegistAccListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/6  êŽ‰Ï(’†u) V‹Kì¬
*/

package webbroker3.accountinfo.message;

import test.util.TestSpecialClassUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignRegistAccListRequestTest extends TestBaseForMock
{

    public final String CompaignName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    public final String validCompaignName = "aaaaaaaaa";
    
    WEB3AdminAccInfoCampaignRegistAccListRequest l_request = null;
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignRegistAccListRequestTest.class);
    
    public WEB3AdminAccInfoCampaignRegistAccListRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_request = new WEB3AdminAccInfoCampaignRegistAccListRequest();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testValidate501()
    {
        final String STR_METHOD_NAME = "testValidate501()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = CompaignName;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02709,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate501>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate502()
    {
        final String STR_METHOD_NAME = "testValidate502()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "ab";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate502>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate503()
    {
        final String STR_METHOD_NAME = "testValidate503()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "1234567";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate503>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidate504()
    {
        final String STR_METHOD_NAME = "testValidate504()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "123456";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01912,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate504>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidate505()
    {
        final String STR_METHOD_NAME = "testValidate505()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "101";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02082,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate505>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate506()
    {
        final String STR_METHOD_NAME = "testValidate506()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "abc";
            l_request.pageSize = "10";
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            WEB3AccInfoSortKey l_key = new WEB3AccInfoSortKey();
            l_key.keyItem = "branchCode";
            l_sortKeys[0] = l_key;
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            log.info("l_request.pageIndex ====  " + l_request.pageIndex);
            assertEquals("1", l_request.pageIndex);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate506>>>>>>>>>>>>>>>test pass !!");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate506>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate507()
    {
        final String STR_METHOD_NAME = "testValidate507()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1a";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate507>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate508()
    {
        final String STR_METHOD_NAME = "testValidate508()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "-1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate508>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidate509()
    {
        final String STR_METHOD_NAME = "testValidate509()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1";
            l_request.pageSize = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate509>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testValidate510()
    {
        final String STR_METHOD_NAME = "testValidate510()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1";
            l_request.pageSize = "a";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate510>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate511()
    {
        final String STR_METHOD_NAME = "testValidate511()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1";
            l_request.pageSize = "-1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate511>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate512()
    {
        final String STR_METHOD_NAME = "testValidate512()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.sortKeys = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate512>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate513()
    {
        final String STR_METHOD_NAME = "testValidate513()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            WEB3AccInfoSortKey l_key = new WEB3AccInfoSortKey();
            l_key.keyItem = "a";
            l_key.ascDesc = "A";
            l_sortKeys[0] = l_key;
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate513>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate5141()
    {
        final String STR_METHOD_NAME = "testValidate5141()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            WEB3AccInfoSortKey l_key = new WEB3AccInfoSortKey();
            l_key.keyItem = "branchCode";
            l_key.ascDesc = "A";
            l_sortKeys[0] = l_key;
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate5141>>>>>>>>>>>>>>>test pass !!");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate5142()
    {
        final String STR_METHOD_NAME = "testValidate5142()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            WEB3AccInfoSortKey l_key = new WEB3AccInfoSortKey();
            l_key.keyItem = "accountCode";
            l_key.ascDesc = "A";
            l_sortKeys[0] = l_key;
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate5142>>>>>>>>>>>>>>>test pass !!");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    public void testValidate5143()
    {
        final String STR_METHOD_NAME = "testValidate5143()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            WEB3AccInfoSortKey l_key = new WEB3AccInfoSortKey();
            l_key.keyItem = "traderCode";
            l_key.ascDesc = "A";
            l_sortKeys[0] = l_key;
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate5143>>>>>>>>>>>>>>>test pass !!");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    public void testValidate5144()
    {
        final String STR_METHOD_NAME = "testValidate5144()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            WEB3AccInfoSortKey l_key = new WEB3AccInfoSortKey();
            l_key.keyItem = "collectRate";
            l_key.ascDesc = "A";
            l_sortKeys[0] = l_key;
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate5144>>>>>>>>>>>>>>>test pass !!");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    public void testValidate5145()
    {
        final String STR_METHOD_NAME = "testValidate5145()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            WEB3AccInfoSortKey l_key = new WEB3AccInfoSortKey();
            l_key.keyItem = "targetPeriodFrom";
            l_key.ascDesc = "A";
            l_sortKeys[0] = l_key;
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate5145>>>>>>>>>>>>>>>test pass !!");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    public void testValidate5146()
    {
        final String STR_METHOD_NAME = "testValidate5146()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.campaignName = validCompaignName;
            l_request.branchCode = "abc";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.collectRate = "10";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[1];
            WEB3AccInfoSortKey l_key = new WEB3AccInfoSortKey();
            l_key.keyItem = "targetPeriodTo";
            l_key.ascDesc = "A";
            l_sortKeys[0] = l_key;
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate5146>>>>>>>>>>>>>>>test pass !!");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    public void testValidate515()
    {
        final String STR_METHOD_NAME = "testValidate515()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestSpecialClassUtility.testCreateResponse(WEB3AdminAccInfoCampaignRegistAccListRequest.class);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
