head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.55.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqOrderVoucherCSVTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqOrderVoucherCSVTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderVoucherCSVTest.class);

    public WEB3FeqOrderVoucherCSVTest(String arg0)
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

    public class WEB3FeqOrderVoucherCSVTest1 extends WEB3FeqOrderVoucherCSV
    {
        public void setValue(
                int l_intLineNumber,
                WEB3GentradeCsvColumnModel l_csvColumnModel,
                Object l_objValue)
        {
            assertEquals("test", l_objValue);
        }
    }

    public void testSetCurrencyName_case0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_feqProductParams);

            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setCurrencyName("test");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            String l_strAddRow = "";
            for (int i = 0; i < 38; i++)
            {
                if (i < 37 && i != 19)
                {
                    l_strAddRow += "1,";
                }
                else if (i == 19)
                {
                    l_strAddRow += "2008/01/24 10:10:10,";
                }
                else
                {
                    l_strAddRow += "1";
                }
            }
            WEB3FeqOrderVoucherCSV l_csv = new WEB3FeqOrderVoucherCSV();
            l_csv.addRow(l_strAddRow);
            l_csv.createColumnHeader();
            l_csv.setCurrencyName(0, 1001L);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
