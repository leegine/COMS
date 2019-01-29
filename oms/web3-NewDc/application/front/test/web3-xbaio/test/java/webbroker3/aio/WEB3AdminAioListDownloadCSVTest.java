head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.32.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioListDownloadCSVTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者入出金一覧ダウンロードCSVテスト(WEB3AdminAioListDownloadCSVTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/06 周捷 (中訊) 新規作成
Revision History : 2007/03/16 車進 (中訊) 修正*/
package webbroker3.aio;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （管理者入出金一覧ダウンロードCSVテスト）<BR>
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AdminAioListDownloadCSVTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3AdminAioListDownloadCSVTest.class);

    public WEB3AdminAioListDownloadCSVTest(String arg0)
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
    
    public void testSetDeliveryDate()
    {
        final String STR_METHOD_NAME = "testSetDeliveryDate()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "受渡日",
                1,
                WEB3GentradeCsvColumnModel.DATETYPE,
                new SimpleDateFormat("yyyy/MM/dd"));
        try
        {
            l_csv.addRow();
            l_csv.setDeliveryDate(0, new Date("2007/02/08"));
            Date l_datReturn = (Date)l_csv.getValue(0, l_model);
            assertEquals(new Date("2007/02/08"), l_datReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.aio.WEB3AdminAioListDownloadCSV.setBranchCode(int, String)'
     */
    public void testSetBranchCode()
    {
        final String STR_METHOD_NAME = "testSetBranchCode()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        try
        {
            l_csv.addRow();
            l_csv.setBranchCode(0, "628");
            String l_strReturn = 
                (String)l_csv.getValue(0, new WEB3GentradeCsvColumnModel(
                    "部店コード",
                    2,
                    WEB3GentradeCsvColumnModel.STRINGTYPE,
                    null));
            assertEquals("628", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetAccountCode()
    {
        final String STR_METHOD_NAME = "testSetAccountCode()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "顧客コード",
                3,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        try
        {
            l_csv.addRow();
            l_csv.setAccountCode(0, "123456");
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("123456", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetAccountName()
    {
        final String STR_METHOD_NAME = "testSetAccountName()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "顧客名",
                4,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        try
        {
            l_csv.addRow();
            l_csv.setAccountName(0, "123456");
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("123456", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetOrderDate()
    {
        final String STR_METHOD_NAME = "testSetOrderDate()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "注文日時",
                5,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        try
        {
            l_csv.addRow();
            l_csv.setOrderDate(0, new Date("2007/02/08"));
            Date l_datReturn = (Date)l_csv.getValue(0, l_model);
            assertEquals(new Date("2007/02/08"), l_datReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetInputRoot()
    {
        final String STR_METHOD_NAME = "testSetInputRoot()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "入力経路",
                9,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        try
        {
            l_csv.addRow();
            l_csv.setInputRoot(0, "123456");
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("WEB", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetInputRoot_001()
    {
        final String STR_METHOD_NAME = "testSetInputRoot_001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //引数.入力経路 == "1(コールセンター)"）の場合
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "入力経路",
                9,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        try
        {
            l_csv.addRow();
            l_csv.setInputRoot(0, WEB3OrderRootDivDef.CALLCENTER);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("コール", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetInputRoot_002()
    {
        final String STR_METHOD_NAME = "testSetInputRoot_002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //引数.入力経路 == "1(コールセンター)"）の場合
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "入力経路",
                9,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        try
        {
            l_csv.addRow();
            l_csv.setInputRoot(0, WEB3OrderRootDivDef.HOST);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("SONAR", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetInputRoot_003()
    {
        final String STR_METHOD_NAME = "testSetInputRoot_003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //引数.入力経路 == "1(コールセンター)"）の場合
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "入力経路",
                9,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        try
        {
            l_csv.addRow();
            l_csv.setInputRoot(0, WEB3OrderRootDivDef.ADMIN);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("管理者", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetTrader()
    {
        final String STR_METHOD_NAME = "testSetTrader()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "入力者",
                10,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        try
        {
            l_csv.addRow();
            l_csv.setTrader(0, "123456");
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("123456", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetCash1()
    {
        final String STR_METHOD_NAME = "testSetCash1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "入金金額",
                7,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strAioDiv = "0";
        try
        {
            l_csv.addRow();
            l_csv.setCash(0, "123456", l_strAioDiv);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("123456", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetCash2()
    {
        final String STR_METHOD_NAME = "testSetCash2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "出金金額",
                8,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strAioDiv = "1";
        try
        {
            l_csv.addRow();
            l_csv.setCash(0, "654321", l_strAioDiv);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("654321", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetStatus1()
    {
        final String STR_METHOD_NAME = "testSetStatus1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "ステータス",
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strStatus = OrderStatusEnum.ORDERED.intValue() + "";
        String l_strOrderCancelDiv = "0";
        try
        {
            l_csv.addRow();
            l_csv.setStatus(0, l_strStatus, l_strOrderCancelDiv);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("完了", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSetStatus2()
    {
        final String STR_METHOD_NAME = "testSetStatus2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "ステータス",
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strStatus = OrderStatusEnum.ACCEPTED.intValue() + "";
        String l_strOrderCancelDiv = "0";
        try
        {
            l_csv.addRow();
            l_csv.setStatus(0, l_strStatus, l_strOrderCancelDiv);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("未処理", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetStatus3()
    {
        final String STR_METHOD_NAME = "testSetStatus3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "ステータス",
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strStatus = OrderStatusEnum.ORDERING.intValue() + "";
        String l_strOrderCancelDiv = "0";
        try
        {
            l_csv.addRow();
            l_csv.setStatus(0, l_strStatus, l_strOrderCancelDiv);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("未処理", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetStatus4()
    {
        final String STR_METHOD_NAME = "testSetStatus4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "ステータス",
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strStatus = OrderStatusEnum.NOT_ORDERED.intValue() + "";
        String l_strOrderCancelDiv = "0";
        try
        {
            l_csv.addRow();
            l_csv.setStatus(0, l_strStatus, l_strOrderCancelDiv);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("エラー", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetAccountInfo1()
    {
        final String STR_METHOD_NAME = "testSetAccountInfo1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "口座情報",
                11,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strBankCode = "111";
        String l_strBankBranchCode = "123456";
        String l_strAccountType = "1";
        String l_strAccountCode = "654321";
        String l_str = l_strBankCode + l_strBankBranchCode + "普通預金" + l_strAccountCode;
        try
        {
            l_csv.addRow();
            l_csv.setAccountInfo(0, l_strBankCode, l_strBankBranchCode, l_strAccountType, l_strAccountCode);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals(l_str, l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetAccountInfo2()
    {
        final String STR_METHOD_NAME = "testSetAccountInfo2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "口座情報",
                11,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strBankCode = "111";
        String l_strBankBranchCode = "123456";
        String l_strAccountType = "2";
        String l_strAccountCode = "654321";
        String l_str = l_strBankCode + l_strBankBranchCode + "当座預金" + l_strAccountCode;
        try
        {
            l_csv.addRow();
            l_csv.setAccountInfo(0, l_strBankCode, l_strBankBranchCode, l_strAccountType, l_strAccountCode);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals(l_str, l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetAccountInfo3()
    {
        final String STR_METHOD_NAME = "testSetAccountInfo3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "口座情報",
                11,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strBankCode = "111";
        String l_strBankBranchCode = "123456";
        String l_strAccountType = "3";
        String l_strAccountCode = "654321";
        String l_str = l_strBankCode + l_strBankBranchCode + "その他" + l_strAccountCode;
        try
        {
            l_csv.addRow();
            l_csv.setAccountInfo(0, l_strBankCode, l_strBankBranchCode, l_strAccountType, l_strAccountCode);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals(l_str, l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetAccountInfo4()
    {
        final String STR_METHOD_NAME = "testSetAccountInfo4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "口座情報",
                11,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strBankCode = "111";
        String l_strBankBranchCode = "123456";
        String l_strAccountType = "4";
        String l_strAccountCode = "654321";
        String l_str = l_strBankCode + l_strBankBranchCode + "貯蓄預金" + l_strAccountCode;
        try
        {
            l_csv.addRow();
            l_csv.setAccountInfo(0, l_strBankCode, l_strBankBranchCode, l_strAccountType, l_strAccountCode);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals(l_str, l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetAccountInfo5()
    {
        final String STR_METHOD_NAME = "testSetAccountInfo5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "口座情報",
                11,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strBankCode = "111";
        String l_strBankBranchCode = "123456";
        String l_strAccountType = "8";
        String l_strAccountCode = "654321";
        String l_str = l_strBankCode + l_strBankBranchCode + l_strAccountCode;
        try
        {
            l_csv.addRow();
            l_csv.setAccountInfo(0, l_strBankCode, l_strBankBranchCode, l_strAccountType, l_strAccountCode);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals(l_str, l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetOrderType1()
    {
        final String STR_METHOD_NAME = "testSetOrderType1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "注文種別",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE, 
                null);
        String l_strOrderType = "1002" ;
        String l_strOrderRootDiv = "9";
        String l_strComdebitNumber = null;
        try
        {
            l_csv.addRow();
            l_csv.setOrderType(0, l_strOrderType, l_strOrderRootDiv, l_strComdebitNumber);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("SONAR入金", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetOrderType2()
    {
        final String STR_METHOD_NAME = "testSetOrderType2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "注文種別",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strOrderType = "1002" ;
        String l_strOrderRootDiv = "D";
        String l_strComdebitNumber = null;
        try
        {
            l_csv.addRow();
            l_csv.setOrderType(0, l_strOrderType, l_strOrderRootDiv, l_strComdebitNumber);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("バーチャル入金", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetOrderType3()
    {
        final String STR_METHOD_NAME = "testSetOrderType3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "注文種別",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strOrderType = "1002" ;
        String l_strOrderRootDiv = "8";
        String l_strComdebitNumber = "11";
        try
        {
            l_csv.addRow();
            l_csv.setOrderType(0, l_strOrderType, l_strOrderRootDiv, l_strComdebitNumber);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("ネット入金", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetOrderType4()
    {
        final String STR_METHOD_NAME = "testSetOrderType4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "注文種別",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strOrderType = "1008" ;
        String l_strOrderRootDiv = "D";
        String l_strComdebitNumber = "11";
        try
        {
            l_csv.addRow();
            l_csv.setOrderType(0, l_strOrderType, l_strOrderRootDiv, l_strComdebitNumber);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("振替(株先証拠金から預り金)", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetOrderType5()
    {
        final String STR_METHOD_NAME = "testSetOrderType5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "注文種別",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strOrderType = "1012" ;
        String l_strOrderRootDiv = "D";
        String l_strComdebitNumber = "11";
        try
        {
            l_csv.addRow();
            l_csv.setOrderType(0, l_strOrderType, l_strOrderRootDiv, l_strComdebitNumber);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("為替保証金振替(為替保証金から預り金)", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetOrderType6()
    {
        final String STR_METHOD_NAME = "testSetOrderType6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "注文種別",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strOrderType = "1018" ;
        String l_strOrderRootDiv = "D";
        String l_strComdebitNumber = "11";
        try
        {
            l_csv.addRow();
            l_csv.setOrderType(0, l_strOrderType, l_strOrderRootDiv, l_strComdebitNumber);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("その他振替(Xから預り金)", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetOrderType7()
    {
        final String STR_METHOD_NAME = "testSetOrderType7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "注文種別",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strOrderType = "1001" ;
        String l_strOrderRootDiv = "D";
        String l_strComdebitNumber = "11";
        try
        {
            l_csv.addRow();
            l_csv.setOrderType(0, l_strOrderType, l_strOrderRootDiv, l_strComdebitNumber);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("出金", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetOrderType8()
    {
        final String STR_METHOD_NAME = "testSetOrderType8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "注文種別",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strOrderType = "1007" ;
        String l_strOrderRootDiv = "D";
        String l_strComdebitNumber = "11";
        try
        {
            l_csv.addRow();
            l_csv.setOrderType(0, l_strOrderType, l_strOrderRootDiv, l_strComdebitNumber);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("振替(預り金から株先証拠金)", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetOrderType9()
    {
        final String STR_METHOD_NAME = "testSetOrderType9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "注文種別",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strOrderType = "1011" ;
        String l_strOrderRootDiv = "D";
        String l_strComdebitNumber = "11";
        try
        {
            l_csv.addRow();
            l_csv.setOrderType(0, l_strOrderType, l_strOrderRootDiv, l_strComdebitNumber);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("為替保証金振替(預り金から為替保証金)", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetOrderType10()
    {
        final String STR_METHOD_NAME = "testSetOrderType10()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "注文種別",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strOrderType = "1017" ;
        String l_strOrderRootDiv = "D";
        String l_strComdebitNumber = "11";
        try
        {
            l_csv.addRow();
            l_csv.setOrderType(0, l_strOrderType, l_strOrderRootDiv, l_strComdebitNumber);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("その他振替(預り金からX)", l_strReturn);
            log.debug(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>> test pass !");
        }
        catch(Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
