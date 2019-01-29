head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPPaymentRequisitionCustomerSearchCSVTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminTPPaymentRequisitionCustomerSearchCSVTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/17 ������(���u) �V�K�쐬
*/

package webbroker3.tradingpoweradmin;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTPPaymentRequisitionCustomerSearchCSVTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionCustomerSearchCSVTest.class);
    
    WEB3AdminTPPaymentRequisitionCustomerSearchCSV l_csv = null;
    public WEB3AdminTPPaymentRequisitionCustomerSearchCSVTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        l_csv = new WEB3AdminTPPaymentRequisitionCustomerSearchCSV();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testCreateColumnHeader_C001()
    {
        final String STR_METHOD_NAME = "testCreateColumnHeader_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel0 =
            l_csv.getColumnModel(l_csv.BRANCH_CODE_LABEL);
        assertEquals(0, l_gentradeCsvColumnModel0.getColumnNumber());
        assertEquals("���X�R�[�h", l_gentradeCsvColumnModel0.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel0.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel0.getDateFormat());
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel1 =
            l_csv.getColumnModel(l_csv.ACCOUNT_CODE_LABEL);
        assertEquals(1, l_gentradeCsvColumnModel1.getColumnNumber());
        assertEquals("�ڋq�R�[�h", l_gentradeCsvColumnModel1.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel1.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel1.getDateFormat());
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel2 =
            l_csv.getColumnModel(l_csv.FAMILY_NAME_LABEL);
        assertEquals(2, l_gentradeCsvColumnModel2.getColumnNumber());
        assertEquals("�ڋq��", l_gentradeCsvColumnModel2.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel2.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel2.getDateFormat());
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel3 =
            l_csv.getColumnModel(l_csv.TRADER_CODE_LABEL);
        assertEquals(3, l_gentradeCsvColumnModel3.getColumnNumber());
        assertEquals("���҃R�[�h", l_gentradeCsvColumnModel3.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel3.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel3.getDateFormat());
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel4 =
            l_csv.getColumnModel(l_csv.ATTRIBUTE_LABEL);
        assertEquals(4, l_gentradeCsvColumnModel4.getColumnNumber());
        assertEquals("����", l_gentradeCsvColumnModel4.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel4.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel4.getDateFormat());

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel5 =
            l_csv.getColumnModel(l_csv.DEBIT_AMOUNT_LABEL);
        assertEquals(5, l_gentradeCsvColumnModel5.getColumnNumber());
        assertEquals("���֋�", l_gentradeCsvColumnModel5.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel5.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel5.getDateFormat());
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel6 =
            l_csv.getColumnModel(l_csv.SPECIAL_DEBIT_AMOUNT_LABEL);
        assertEquals(6, l_gentradeCsvColumnModel6.getColumnNumber());
        assertEquals("���ʗ��֋�", l_gentradeCsvColumnModel6.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel6.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel0.getDateFormat());
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel7 =
            l_csv.getColumnModel(l_csv.REQUIRED_PAY_AMT_LABEL);
        assertEquals(7, l_gentradeCsvColumnModel7.getColumnNumber());
        assertEquals("�K�v�����z", l_gentradeCsvColumnModel7.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel7.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel7.getDateFormat());
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel8 =
            l_csv.getColumnModel(l_csv.FIRST_DEPOSIT_AMOUNT_LABEL);
        assertEquals(8, l_gentradeCsvColumnModel8.getColumnNumber());
        assertEquals("30������Ǐ؋��z", l_gentradeCsvColumnModel8.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel8.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel8.getDateFormat());
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel9 =
            l_csv.getColumnModel(l_csv.FIRST_DEPOSIT_PASS_DAY_LABEL);
        assertEquals(9, l_gentradeCsvColumnModel9.getColumnNumber());
        assertEquals("30������Ǐ،o�ߓ���", l_gentradeCsvColumnModel9.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel9.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel9.getDateFormat());
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel10 =
            l_csv.getColumnModel(l_csv.SECOND_DEPOSIT_1_LABEL);
        assertEquals(10, l_gentradeCsvColumnModel10.getColumnNumber());
        assertEquals("20������Ǐؐ����i1�j", l_gentradeCsvColumnModel10.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel10.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel10.getDateFormat());
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel11 =
            l_csv.getColumnModel(l_csv.SECOND_DEPOSIT_2_LABEL);
        assertEquals(11, l_gentradeCsvColumnModel11.getColumnNumber());
        assertEquals("20������Ǐؐ����i2�j", l_gentradeCsvColumnModel11.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel11.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel11.getDateFormat());
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel12 =
            l_csv.getColumnModel(l_csv.SECOND_DEPOSIT_NON_PAY_LABEL);
        assertEquals(12, l_gentradeCsvColumnModel12.getColumnNumber());
        assertEquals("20������Ǐؖ�����", l_gentradeCsvColumnModel12.getColumnLabel());
        assertEquals(WEB3GentradeCsvColumnModel.STRINGTYPE, l_gentradeCsvColumnModel12.getColumnType());
        assertEquals(null, l_gentradeCsvColumnModel12.getDateFormat());

        log.exiting(STR_METHOD_NAME);
    }
    
    //setBranchCode
    public void testSetBranchCode_C001()
    {
        final String STR_METHOD_NAME = "testSetBranchCode_C001";
        log.entering(STR_METHOD_NAME);
        
        l_csv.addRow();
        l_csv.setBranchCode(0,"102");
        
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel0 =
            l_csv.getColumnModel(l_csv.BRANCH_CODE_LABEL);
        String l_strBranchCode = (String)l_csv.getValue(0, l_gentradeCsvColumnModel0);
        assertEquals("102", l_strBranchCode);
        log.exiting(STR_METHOD_NAME);
    }
    
    //setAccountCode
    public void testSetAccountCode_C001()
    {
        final String STR_METHOD_NAME = "testSetAccountCode_C001";
        log.entering(STR_METHOD_NAME);
        
        l_csv.addRow();
        l_csv.setAccountCode(0,"100250");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.ACCOUNT_CODE_LABEL);
        
        String l_strAccountCode = (String)l_csv.getValue(0, l_gentradeCsvColumnModel);
        assertEquals("100250", l_strAccountCode);
        log.exiting(STR_METHOD_NAME);
    }
    
    //setFamilyName
    public void testSetFamilyName_C001()
    {
        final String STR_METHOD_NAME = "testSetFamilyName_C001";
        log.entering(STR_METHOD_NAME);
        
        l_csv.addRow();
        l_csv.setFamilyName(0,"Jack");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.FAMILY_NAME_LABEL);
        String l_strFamilyName = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("Jack", l_strFamilyName);
        log.exiting(STR_METHOD_NAME);
    }
    
    //setTraderCode
    public void testSetTraderCode()
    {
        final String STR_METHOD_NAME = "testSetTraderCode";
        log.entering(STR_METHOD_NAME);
        
        l_csv.addRow();
        l_csv.setTraderCode(0,"00250");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.TRADER_CODE_LABEL);
        String l_strTraderCode = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("00250", l_strTraderCode);
        log.exiting(STR_METHOD_NAME);
    }
    
    //setAttribute
    public void testSetAttribute_C001()
    {
        final String STR_METHOE_NAEM = "testSetAttribute_C001";
        log.entering(STR_METHOE_NAEM);
        
        l_csv.addRow();
        l_csv.setAttribute(0,"0");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.ATTRIBUTE_LABEL);
        String l_strAttribute = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("�����i�O�����j", l_strAttribute);
        log.exiting(STR_METHOE_NAEM);
    }

    //setAttribute
    public void testSetAttribute_C002()
    {
        final String STR_METHOE_NAEM = "testSetAttribute_C002";
        log.entering(STR_METHOE_NAEM);
        
        l_csv.addRow();
        l_csv.setAttribute(0,"1");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.ATTRIBUTE_LABEL);
        String l_strAttribute = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("�����i�]�����j", l_strAttribute);
        log.exiting(STR_METHOE_NAEM);
    }

    //setAttribute
    public void testSetAttribute_C003()
    {
        final String STR_METHOE_NAEM = "testSetAttribute_C003";
        log.entering(STR_METHOE_NAEM);
        
        l_csv.addRow();
        l_csv.setAttribute(0,"2");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.ATTRIBUTE_LABEL);
        String l_strAttribute = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("�M�p", l_strAttribute);
        log.exiting(STR_METHOE_NAEM);
    }
    
    //setDebitAmount
    public void testSetDebitAmount_C001()
    {
        final String STR_METHOE_NAEM = "testSetDebitAmount_C001";
        log.entering(STR_METHOE_NAEM);
        
        l_csv.addRow();
        l_csv.setDebitAmount(0, "100");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.DEBIT_AMOUNT_LABEL);
        String l_strDebitAmount = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("100",  l_strDebitAmount);
        log.exiting(STR_METHOE_NAEM);
    }
    
    //setSpecialDebitAmount
    public void testSetSpecialDebitAmount_C001()
    {
        final String STR_METHOE_NAEM = "testSetSpecialDebitAmount_C001";
        log.entering(STR_METHOE_NAEM);
        l_csv.addRow();
        l_csv.setSpecialDebitAmount(0, "50");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.SPECIAL_DEBIT_AMOUNT_LABEL);
        String l_strSpecialDebitAmount = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("50", l_strSpecialDebitAmount);
        log.exiting(STR_METHOE_NAEM);
    }
    
    //setRequiredPayAmt
    public void testSetRequiredPayAmt_C001()
    {
        final String STR_METHOE_NAEM = "testSetRequiredPayAmt_C001";
        log.entering(STR_METHOE_NAEM);
        l_csv.addRow();
        l_csv.setRequiredPayAmt(0, "40");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.REQUIRED_PAY_AMT_LABEL);
        String l_strRequiredPayAmt = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("40", l_strRequiredPayAmt);
        log.exiting(STR_METHOE_NAEM);
    }
    
    //setFirstDepositAmount
    public void testSetFirstDepositAmount_C001()
    {
        final String STR_METHOE_NAEM = "testSetFirstDepositAmount_C001";
        log.entering(STR_METHOE_NAEM);
        l_csv.addRow();
        l_csv.setFirstDepositAmount(0, "30");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.FIRST_DEPOSIT_AMOUNT_LABEL);
        String l_strFirstDepositAmount = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("30", l_strFirstDepositAmount);
        log.exiting(STR_METHOE_NAEM);
    }
    
    //setFirstDepositPassDay
    public void testSetFirstDepositPassDay_C001()
    {
        final String STR_METHOE_NAEM = "testSetFirstDepositPassDay_C001";
        log.entering(STR_METHOE_NAEM);
        l_csv.addRow();
        l_csv.setFirstDepositPassDay(0, "5");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.FIRST_DEPOSIT_PASS_DAY_LABEL);
        String l_strFirstDepositPassDay = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("5", l_strFirstDepositPassDay);
        log.exiting(STR_METHOE_NAEM);
    }
    
    //setSecondDeposit1
    public void testSetSecondDeposit1_C001()
    {
        final String STR_METHOE_NAEM = "testSetSecondDeposit1_C001";
        log.entering(STR_METHOE_NAEM);
        l_csv.addRow();
        l_csv.setSecondDeposit1(0, "4");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.SECOND_DEPOSIT_1_LABEL);
        String l_strSecondDeposit1 = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("4", l_strSecondDeposit1);
        log.exiting(STR_METHOE_NAEM);
    }
    
    //setSecondDeposit2
    public void testsetSecondDeposit2_C001()
    {
        final String STR_METHOE_NAEM = "testsetSecondDeposit2_C001";
        log.entering(STR_METHOE_NAEM);
        l_csv.addRow();
        l_csv.setSecondDeposit2(0, "3");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.SECOND_DEPOSIT_2_LABEL);
        String l_strSecondDeposit2 = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("3", l_strSecondDeposit2);
        log.exiting(STR_METHOE_NAEM);
    }
    
    //setSecondDepositNonPay
    public void testSetSecondDepositNonPay_C001()
    {
        final String STR_METHOE_NAEM = "testSetSecondDepositNonPay_C001";
        log.entering(STR_METHOE_NAEM);
        l_csv.addRow();
        l_csv.setSecondDepositNonPay(0, "2");
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            l_csv.getColumnModel(l_csv.SECOND_DEPOSIT_NON_PAY_LABEL);
        String l_strSecondDepositNonPay = (String)l_csv.getStringValue(0, l_gentradeCsvColumnModel);
        assertEquals("2", l_strSecondDepositNonPay);
        log.exiting(STR_METHOE_NAEM);
    }
}
@
