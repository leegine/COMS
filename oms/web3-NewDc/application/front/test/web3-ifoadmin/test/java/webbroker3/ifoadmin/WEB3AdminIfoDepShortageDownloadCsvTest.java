head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoDepShortageDownloadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifoadmin;

import java.lang.reflect.Field;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;

public class WEB3AdminIfoDepShortageDownloadCsvTest extends TestBaseForMock
{

    public WEB3AdminIfoDepShortageDownloadCsvTest(String name)
    {
        super(name);
    }
    Date l_dat = GtlUtils.getSystemTimestamp();
    public void test_createKeyHeader_0001()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.createKeyHeader(l_dat);
        Field l_field = null;
//        WEB3GentradeCsvDataModel l_model = new WEB3GentradeCsvDataModel();
        try
        {
            l_field = WEB3GentradeCsvDataModel.class.getDeclaredField("keyHeader");
            l_field.setAccessible(true);
            String[] l_str = (String[])l_field.get(l_csv);
            assertEquals(
                "CSVèoóÕì˙",
                l_str[0]);
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS),
                l_str[1]);
            assertEquals(
                    "î≠ê∂ì˙ït",
                    l_str[2]);
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.DATE_SPLIT_YMD),
                l_str[3]);
        } catch (Exception e)
        {
            fail();
        }
    }
    
    public void test_createColumnHeader_0001()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.createColumnHeader();
        Field l_field = null;
        try
        {
            l_field = WEB3GentradeCsvDataModel.class.getDeclaredField("columnHeader");
            l_field.setAccessible(true);
            WEB3GentradeCsvColumnModel[] l_model = (WEB3GentradeCsvColumnModel[])l_field.get(l_csv);
            assertEquals("ïîìX", l_model[0].getColumnLabel());
            assertEquals("0", l_model[0].getColumnNumber() + "");
            assertEquals("0", l_model[0].getColumnType() + "");
            assertNull(l_model[0].getDateFormat());
            
            assertEquals("å⁄ãq", l_model[1].getColumnLabel());
            assertEquals("1", l_model[1].getColumnNumber() + "");
            assertEquals("0", l_model[1].getColumnType() + "");
            assertNull(l_model[1].getDateFormat());
            
            assertEquals("å⁄ãqñº", l_model[2].getColumnLabel());
            assertEquals("2", l_model[2].getColumnNumber() + "");
            assertEquals("0", l_model[2].getColumnType() + "");
            assertNull(l_model[2].getDateFormat());
            
            assertEquals("êøãÅäz", l_model[3].getColumnLabel());
            assertEquals("3", l_model[3].getColumnNumber() + "");
            assertEquals("0", l_model[3].getColumnType() + "");
            assertNull(l_model[3].getDateFormat());
            
            assertEquals("åªç›ÇÃñ¢âè¡ã‡äz", l_model[4].getColumnLabel());
            assertEquals("4", l_model[4].getColumnNumber() + "");
            assertEquals("0", l_model[4].getColumnType() + "");
            assertNull(l_model[4].getDateFormat());
            
            assertEquals("åªç›ÇÃèÿãíã‡èäóväz", l_model[5].getColumnLabel());
            assertEquals("5", l_model[5].getColumnNumber() + "");
            assertEquals("0", l_model[5].getColumnType() + "");
            assertNull(l_model[5].getDateFormat());
            
            assertEquals("åöã óLñ≥ÅiOPîÉåöèúÇ≠Åj", l_model[6].getColumnLabel());
            assertEquals("6", l_model[6].getColumnNumber() + "");
            assertEquals("0", l_model[6].getColumnType() + "");
            assertNull(l_model[6].getDateFormat());
            
            assertEquals("íçï∂óLñ≥ÅiOPîÉåöèúÇ≠Åj", l_model[7].getColumnLabel());
            assertEquals("7", l_model[7].getColumnNumber() + "");
            assertEquals("0", l_model[7].getColumnType() + "");
            assertNull(l_model[7].getDateFormat());
            
        } catch (Exception e)
        {
            fail();
        }
    }
    
    public void test_setBranchCode_0001()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.addRow();
        l_csv.setBranchCode(0, "BranchCode");
        try
        {
            WEB3GentradeCsvColumnModel l_models =
                new WEB3GentradeCsvColumnModel("ïîìX",  0,  WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            assertEquals("BranchCode", l_csv.getValue(0, l_models));
        } catch (Exception e)
        {
            fail();
        }
    }
    
    public void test_setAccountCode_0001()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.addRow();
        l_csv.setAccountCode(0, "AccountCode");
        try
        {
            WEB3GentradeCsvColumnModel l_models =
                new WEB3GentradeCsvColumnModel("å⁄ãq", 1,  WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            assertEquals("Accoun", l_csv.getValue(0, l_models));
        } catch (Exception e)
        {
            fail();
        }
    }
    
    public void test_setAccountName_0001()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.addRow();
        l_csv.setAccountName(0, "AccountName");
        try
        {
            WEB3GentradeCsvColumnModel l_models =
                new WEB3GentradeCsvColumnModel("å⁄ãqñº", 2,  WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            assertEquals("AccountName", l_csv.getValue(0, l_models));
        } catch (Exception e)
        {
            fail();
        }
    }
    
    public void test_setClaimAmount_0001()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.addRow();
        l_csv.setClaimAmount(0, "ClaimAmount");
        try
        {
            WEB3GentradeCsvColumnModel l_models =
                new WEB3GentradeCsvColumnModel("êøãÅäz", 3,  WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            assertEquals("ClaimAmount", l_csv.getValue(0, l_models));
        } catch (Exception e)
        {
            fail();
        }
    }
    
    /**
     * [à¯êî.åªç›ñ¢ì¸ã‡äzÅÅÅÅ null ÇÃèÍçá] 
     * "-" 
     *
     */
    public void test_setCurNonPayAmt_0001()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.addRow();
        l_csv.setCurNonPayAmt(0, "CurNonPayAmt");
        try
        {
            WEB3GentradeCsvColumnModel l_models =
                new WEB3GentradeCsvColumnModel("åªç›ÇÃñ¢ì¸ã‡äz", 4,  WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            assertEquals("CurNonPayAmt", l_csv.getValue(0, l_models));
        } catch (Exception e)
        {
            fail();
        }
    }
    
    /**
     * [à¯êî.åªç›ñ¢ì¸ã‡äz! ÅÅ null ÇÃèÍçá] 
     * à¯êî.åªç›ñ¢ì¸ã‡äz
     *
     */
    public void test_setCurNonPayAmt_0002()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.addRow();
        l_csv.setCurNonPayAmt(0, null);
        try
        {
            WEB3GentradeCsvColumnModel l_models =
                new WEB3GentradeCsvColumnModel("åªç›ÇÃñ¢ì¸ã‡äz", 4,  WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            assertEquals("-", l_csv.getValue(0, l_models));
        } catch (Exception e)
        {
            fail();
        }
    }
    
    public void test_setCurIfoDepositNecessaryAmt_0001()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.addRow();
        l_csv.setCurIfoDepositNecessaryAmt(0, "CurIfoDepositNecessaryAmt");
        try
        {
            WEB3GentradeCsvColumnModel l_models =
                new WEB3GentradeCsvColumnModel("åªç›ÇÃèÿãíã‡èäóväz", 5,  WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            assertEquals("CurIfoDepositNecessaryAmt", l_csv.getValue(0, l_models));
        } catch (Exception e)
        {
            fail();
        }
    }
    
    public void test_setContractExistFlag_0001()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.addRow();
        l_csv.setContractExistFlag(0, false);
        try
        {
            WEB3GentradeCsvColumnModel l_models =
                new WEB3GentradeCsvColumnModel("åöã óLñ≥ÅiOPîÉåöèúÇ≠Åj", 6,  WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            assertEquals("ñ≥", l_csv.getValue(0, l_models));
        } catch (Exception e)
        {
            fail();
        }
    }
    
    public void test_setContractExistFlag_0002()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.addRow();
        l_csv.setContractExistFlag(0, true);
        try
        {
            WEB3GentradeCsvColumnModel l_models =
                new WEB3GentradeCsvColumnModel("åöã óLñ≥ÅiOPîÉåöèúÇ≠Åj", 6,  WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            assertEquals("óL", l_csv.getValue(0, l_models));
        } catch (Exception e)
        {
            fail();
        }
    }
    
    public void test_setOrderExistFlag_0001()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.addRow();
        l_csv.setOrderExistFlag(0, false);
        try
        {
            WEB3GentradeCsvColumnModel l_models =
                new WEB3GentradeCsvColumnModel("íçï∂óLñ≥ÅiOPîÉåöèúÇ≠Åj", 7,  WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            assertEquals("ñ≥", l_csv.getValue(0, l_models));
        } catch (Exception e)
        {
            fail();
        }
    }
    
    public void test_setOrderExistFlag_0002()
    {
        WEB3AdminIfoDepShortageDownloadCsv l_csv = new WEB3AdminIfoDepShortageDownloadCsv(l_dat);
        l_csv.addRow();
        l_csv.setOrderExistFlag(0, true);
        try
        {
            WEB3GentradeCsvColumnModel l_models =
                new WEB3GentradeCsvColumnModel("íçï∂óLñ≥ÅiOPîÉåöèúÇ≠Åj", 7,  WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            assertEquals("óL", l_csv.getValue(0, l_models));
        } catch (Exception e)
        {
            fail();
        }
    }
}
@
