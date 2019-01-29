head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.53.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformDownLoadCSVTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform;

import java.text.DateFormat;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author tang-xingfeng
 *
 */
public class WEB3AdminInformDownLoadCSVTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformDownLoadCSVTest.class);
    WEB3AdminInformDownLoadCSV l_loadCSV =
        new WEB3AdminInformDownLoadCSV();

    public WEB3AdminInformDownLoadCSVTest(String arg0)
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

    /*
     * Test method for 'webbroker3.inform.WEB3AdminInformDownLoadCSV.setItemValue(int, VariousInformParams)'
     */
    public void testSetItemValue_case1()
    {
        final String STR_METHOD_NAME = "testSetItemValue_case1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            DateFormat l_dateFormat = null;
            WEB3AdminInformColumnModel l_adminInformColumnModel =
                new WEB3AdminInformColumnModel(
                    "5", 
                    0, 
                    12, 
                    l_dateFormat, 
                    "branch_code", 
                    "", 
                    "3");
            WEB3GentradeCsvColumnModel[] l_gentradeCsvColunmModel = {l_adminInformColumnModel};
            l_loadCSV.setColumnHeader(l_gentradeCsvColunmModel);
            l_loadCSV.addRow();
            VariousInformParams l_variousInformParams = this.getVariousInformRow();

            TestDBUtility.deleteAll(VariousInformRow.TYPE);

            TestDBUtility.insertWithDel(l_variousInformParams);
            
            l_loadCSV.setItemValue(0, l_variousInformParams);
            Object l_actualObject = l_loadCSV.getValue(0, l_adminInformColumnModel);
            assertEquals(l_actualObject, (Object)"4");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSetItemValue_case2()
    {

        final String STR_METHOD_NAME = "testSetItemValue_case2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            DateFormat l_dateFormat = null;
            WEB3AdminInformColumnModel l_adminInformColumnModel =
                new WEB3AdminInformColumnModel(
                    "5", 
                    0, 
                    12, 
                    l_dateFormat, 
                    "account_code", 
                    "", 
                    "1");
            WEB3GentradeCsvColumnModel[] l_gentradeCsvColunmModel = {l_adminInformColumnModel};
            l_loadCSV.setColumnHeader(l_gentradeCsvColunmModel);
            l_loadCSV.addRow();
            VariousInformParams l_variousInformParams = this.getVariousInformRow();
            l_variousInformParams.setAccountCode(null);

            TestDBUtility.deleteAll(VariousInformRow.TYPE);

            TestDBUtility.insertWithDel(l_variousInformParams);
            
            l_loadCSV.setItemValue(0, l_variousInformParams);
            Object l_actualObject = l_loadCSV.getValue(0, l_adminInformColumnModel);
            assertEquals(l_actualObject, (Object)"");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testSetItemValue_case3()
    {
        final String STR_METHOD_NAME = "testSetItemValue_case3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            DateFormat l_dateFormat = null;
            WEB3AdminInformColumnModel l_adminInformColumnModel =
                new WEB3AdminInformColumnModel(
                    "5", 
                    0, 
                    12, 
                    l_dateFormat, 
                    "account_code", 
                    "", 
                    "1");
            WEB3GentradeCsvColumnModel[] l_gentradeCsvColunmModel = {l_adminInformColumnModel};
            l_loadCSV.setColumnHeader(l_gentradeCsvColunmModel);
            l_loadCSV.addRow();
            
            
            
            
            
            VariousInformParams l_variousInformParams = this.getVariousInformRow();
            l_variousInformParams.setAccountCode("1234567");

            TestDBUtility.deleteAll(VariousInformRow.TYPE);

            TestDBUtility.insertWithDel(l_variousInformParams);
            
            
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            l_mainAccount.setAccountCode("1234567");
            l_mainAccount.setInstitutionCode("10");
            
            l_mainAccount.setBranchCode("624");
            
            TestDBUtility.insertWithDel(l_mainAccount);
            
            
            
            
            l_loadCSV.setItemValue(0, l_variousInformParams);
            Object l_actualObject = l_loadCSV.getValue(0, l_adminInformColumnModel);
            assertEquals((String)l_actualObject, "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.inform.WEB3AdminInformDownLoadCSV.getCatDelimitter(String)'
     */
    public void testGetCatDelimitter_case1()
    {
        final String STR_METHOD_NAME = "testGetCatDelimitter_case1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strRetrun = l_loadCSV.getCatDelimitter("1");
            assertEquals(l_strRetrun, " ");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetCatDelimitter_case2()
    {
        final String STR_METHOD_NAME = "testGetCatDelimitter_case2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strRetrun = l_loadCSV.getCatDelimitter("2");
            assertEquals(l_strRetrun, "　@");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetCatDelimitter_case3()
    {
        final String STR_METHOD_NAME = "testGetCatDelimitter_case3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strRetrun = l_loadCSV.getCatDelimitter("3");
            assertEquals(l_strRetrun, "-");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testGetCatDelimitter_case4()
    {
        final String STR_METHOD_NAME = "testGetCatDelimitter_case4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strRetrun = l_loadCSV.getCatDelimitter("4");
            assertEquals(l_strRetrun, "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public static VariousInformParams getVariousInformRow()
    {
        VariousInformParams l_variousInformParams = new VariousInformParams();
        
        //証券会社コード,  institution_code,  VARCHAR2,  3,  NotNull
        l_variousInformParams.setInstitutionCode("10");
        //連絡種別,  inform_div,  VARCHAR2,  2,  NotNull
        l_variousInformParams.setInformDiv("2");
        //識別コード,  request_number,  VARCHAR2,  13,  NotNull
        l_variousInformParams.setRequestNumber("1");
        //部店コード,  branch_code,  VARCHAR2,  3,  NotNull
        l_variousInformParams.setBranchCode("624");
        //顧客コード,  account_code,  VARCHAR2,  7,  Null
        l_variousInformParams.setAccountCode("9681");

        //作成日時,  created_timestamp,  DATE,  NotNull
        l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
        //更新日時,  last_updated_timestamp,  DATE,  NotNull
        l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040708","yyyyMMdd"));

        return l_variousInformParams;
    }
}
@
