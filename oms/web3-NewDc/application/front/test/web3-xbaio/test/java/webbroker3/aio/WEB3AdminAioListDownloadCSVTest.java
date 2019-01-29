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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV�e�X�g(WEB3AdminAioListDownloadCSVTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/06 ���� (���u) �V�K�쐬
Revision History : 2007/03/16 �Ԑi (���u) �C��*/
package webbroker3.aio;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV�e�X�g�j<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminAioListDownloadCSVTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
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
                "��n��",
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
                    "���X�R�[�h",
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
                "�ڋq�R�[�h",
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
                "�ڋq��",
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
                "��������",
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
                "���͌o�H",
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
        //����.���͌o�H == "1(�R�[���Z���^�[)"�j�̏ꍇ
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "���͌o�H",
                9,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        try
        {
            l_csv.addRow();
            l_csv.setInputRoot(0, WEB3OrderRootDivDef.CALLCENTER);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("�R�[��", l_strReturn);
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
        //����.���͌o�H == "1(�R�[���Z���^�[)"�j�̏ꍇ
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "���͌o�H",
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
        //����.���͌o�H == "1(�R�[���Z���^�[)"�j�̏ꍇ
        WEB3AdminAioListDownloadCSV l_csv = new WEB3AdminAioListDownloadCSV();
        WEB3GentradeCsvColumnModel l_model = 
            new WEB3GentradeCsvColumnModel(
                "���͌o�H",
                9,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        try
        {
            l_csv.addRow();
            l_csv.setInputRoot(0, WEB3OrderRootDivDef.ADMIN);
            String l_strReturn = (String)l_csv.getValue(0, l_model);
            assertEquals("�Ǘ���", l_strReturn);
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
                "���͎�",
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
                "�������z",
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
                "�o�����z",
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
                "�X�e�[�^�X",
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
            assertEquals("����", l_strReturn);
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
                "�X�e�[�^�X",
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
            assertEquals("������", l_strReturn);
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
                "�X�e�[�^�X",
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
            assertEquals("������", l_strReturn);
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
                "�X�e�[�^�X",
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
            assertEquals("�G���[", l_strReturn);
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
                "�������",
                11,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strBankCode = "111";
        String l_strBankBranchCode = "123456";
        String l_strAccountType = "1";
        String l_strAccountCode = "654321";
        String l_str = l_strBankCode + l_strBankBranchCode + "���ʗa��" + l_strAccountCode;
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
                "�������",
                11,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strBankCode = "111";
        String l_strBankBranchCode = "123456";
        String l_strAccountType = "2";
        String l_strAccountCode = "654321";
        String l_str = l_strBankCode + l_strBankBranchCode + "�����a��" + l_strAccountCode;
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
                "�������",
                11,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strBankCode = "111";
        String l_strBankBranchCode = "123456";
        String l_strAccountType = "3";
        String l_strAccountCode = "654321";
        String l_str = l_strBankCode + l_strBankBranchCode + "���̑�" + l_strAccountCode;
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
                "�������",
                11,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        String l_strBankCode = "111";
        String l_strBankBranchCode = "123456";
        String l_strAccountType = "4";
        String l_strAccountCode = "654321";
        String l_str = l_strBankCode + l_strBankBranchCode + "���~�a��" + l_strAccountCode;
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
                "�������",
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
                "�������",
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
            assertEquals("SONAR����", l_strReturn);
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
                "�������",
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
            assertEquals("�o�[�`��������", l_strReturn);
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
                "�������",
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
            assertEquals("�l�b�g����", l_strReturn);
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
                "�������",
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
            assertEquals("�U��(����؋�������a���)", l_strReturn);
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
                "�������",
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
            assertEquals("�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���)", l_strReturn);
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
                "�������",
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
            assertEquals("���̑��U��(X����a���)", l_strReturn);
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
                "�������",
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
            assertEquals("�o��", l_strReturn);
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
                "�������",
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
            assertEquals("�U��(�a������犔��؋���)", l_strReturn);
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
                "�������",
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
            assertEquals("�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�)", l_strReturn);
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
                "�������",
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
            assertEquals("���̑��U��(�a�������X)", l_strReturn);
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
