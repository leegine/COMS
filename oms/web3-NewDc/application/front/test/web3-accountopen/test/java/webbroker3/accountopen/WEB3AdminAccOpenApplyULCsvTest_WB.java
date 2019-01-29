head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenApplyULCsvTest_WB.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.WEB3FPTUploadCsv;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccOpenApplyULCsvTest_WB extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyULCsvTest_WB.class);

    public WEB3AdminAccOpenApplyULCsvTest_WB(String arg0)
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

    public class WEB3AdminAccOpenApplyULCsvTest1 extends WEB3AdminAccOpenApplyULCsv
    {
        public String getRequestNumber(int l_intNo)
        {
            return null;
        }

        public String getAccountDiv(int l_intNo)
        {
            return null;
        }
    }

    public class WEB3AdminAccOpenApplyULCsvTest2 extends WEB3AdminAccOpenApplyULCsv
    {
        public String getRequestNumber(int l_intNo)
        {
            return "123456789012";
        }

        public String getAccountDiv(int l_intNo)
        {
            return "2";
        }
    }

    public class WEB3AdminAccOpenApplyULCsvTest3 extends WEB3AdminAccOpenApplyULCsv
    {
        public String getRequestNumber(int l_intNo)
        {
            return "123456aaa0123";
        }

        public String getAccountDiv(int l_intNo)
        {
            return "1";
        }
    }

    public class WEB3AdminAccOpenApplyULCsvTest4 extends WEB3AdminAccOpenApplyULCsv
    {
        public String getRequestNumber(int l_intNo)
        {
            return "1234567890123";
        }

        public String getAccountDiv(int l_intNo)
        {
            return "0";
        }
    }

    public void testValidateRequestNumber()
    {
        final String STR_METHOD_NAME = "testValidateRequestNumber()";
        log.entering(STR_METHOD_NAME);


        WEB3AdminAccOpenApplyULCsvTest1 l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsvTest1();

        try
        {
            l_adminAccOpenApplyULCsv.validateRequestNumber(0);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01309, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRequestNumber1()
    {
        final String STR_METHOD_NAME = "testValidateRequestNumber1()";
        log.entering(STR_METHOD_NAME);


        WEB3AdminAccOpenApplyULCsvTest2 l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsvTest2();

        try
        {
            l_adminAccOpenApplyULCsv.validateRequestNumber(0);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01310, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRequestNumber2()
    {
        final String STR_METHOD_NAME = "testValidateRequestNumber2()";
        log.entering(STR_METHOD_NAME);


        WEB3AdminAccOpenApplyULCsvTest3 l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsvTest3();

        try
        {
            l_adminAccOpenApplyULCsv.validateRequestNumber(0);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01312, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateAccountType()
    {
        final String STR_METHOD_NAME = "testValidateAccountType()";
        log.entering(STR_METHOD_NAME);


        WEB3AdminAccOpenApplyULCsvTest1 l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsvTest1();

        try
        {
            l_adminAccOpenApplyULCsv.validateAccountType(0);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01309, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateAccountType1()
    {
        final String STR_METHOD_NAME = "testValidateAccountType1()";
        log.entering(STR_METHOD_NAME);


        WEB3AdminAccOpenApplyULCsvTest2 l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsvTest2();

        try
        {
            l_adminAccOpenApplyULCsv.validateAccountType(0);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01738, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateAccountType2()
    {
        final String STR_METHOD_NAME = "testValidateAccountType1()";
        log.entering(STR_METHOD_NAME);


        WEB3AdminAccOpenApplyULCsvTest3 l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsvTest3();
        WEB3AdminAccOpenApplyULCsvTest4 l_adminAccOpenApplyULCsv1 = new WEB3AdminAccOpenApplyULCsvTest4();

        try
        {
            l_adminAccOpenApplyULCsv.validateAccountType(0);
            l_adminAccOpenApplyULCsv1.validateAccountType(0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRequestNumber3()
    {
        final String STR_METHOD_NAME = "testValidateRequestNumber3()";
        log.entering(STR_METHOD_NAME);


        WEB3AdminAccOpenApplyULCsvTest4 l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsvTest4();

        try
        {
            l_adminAccOpenApplyULCsv.validateRequestNumber(0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateColumnHeader()
    {
        final String STR_METHOD_NAME = "testGetUploadScreen_case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULCsv l_adminAccOpenApplyULCsv = new WEB3AdminAccOpenApplyULCsv();
            l_adminAccOpenApplyULCsv.createColumnHeader();
            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel0 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.recordNumberLabel);
            assertEquals(0, l_gentradeCsvColumnModel0.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel1 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.requestNumberLabel);
            assertEquals(1, l_gentradeCsvColumnModel1.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel2 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.branchCodeLabel);
            assertEquals(2, l_gentradeCsvColumnModel2.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel3 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.accountCodeLabel);
            assertEquals(3, l_gentradeCsvColumnModel3.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel4 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.sonarTraderCodeLabel);
            assertEquals(4, l_gentradeCsvColumnModel4.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel5 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.accountTypeLabel);
            assertEquals(5, l_gentradeCsvColumnModel5.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel6 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.inputDivLabel);
            assertEquals(6, l_gentradeCsvColumnModel6.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel7 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.infoClaimDateLabel);
            assertEquals(7, l_gentradeCsvColumnModel7.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel8 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.accountFamilyNameLabel);
            assertEquals(8, l_gentradeCsvColumnModel8.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel9 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.accountNameLabel);
            assertEquals(9, l_gentradeCsvColumnModel9.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel10 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.accountFamilyNameKanaLabel);
            assertEquals(10, l_gentradeCsvColumnModel10.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel11 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.accountNameKanaLabel);
            assertEquals(11, l_gentradeCsvColumnModel11.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel12 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.sexLabel);
            assertEquals(12, l_gentradeCsvColumnModel12.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel13 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.eraBornLabel);
            assertEquals(13, l_gentradeCsvColumnModel13.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel14 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.bornDateLabel);
            assertEquals(14, l_gentradeCsvColumnModel14.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel15 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.mailAddressLabel);
            assertEquals(15, l_gentradeCsvColumnModel15.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel16 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.zipCodeLabel);
            assertEquals(16, l_gentradeCsvColumnModel16.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel17 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.address1Label);
            assertEquals(17, l_gentradeCsvColumnModel17.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel18 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.address2Label);
            assertEquals(18, l_gentradeCsvColumnModel18.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel19 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.address3Label);
            assertEquals(19, l_gentradeCsvColumnModel19.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel20 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.addressKana1Label);
            assertEquals(20, l_gentradeCsvColumnModel20.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel21 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.addressKana2Label);
            assertEquals(21, l_gentradeCsvColumnModel21.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel22 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.addressKana3Label);
            assertEquals(22, l_gentradeCsvColumnModel22.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel23 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.telephoneAreaCodeLabel);
            assertEquals(23, l_gentradeCsvColumnModel23.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel24 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.telephoneExchangeNumberLabel);
            assertEquals(24, l_gentradeCsvColumnModel24.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel25 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.telephoneNumberLabel);
            assertEquals(25, l_gentradeCsvColumnModel25.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel26 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.mobileAreaCodeLabel);
            assertEquals(26, l_gentradeCsvColumnModel26.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel27 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.mobileExchangeNumberLabel);
            assertEquals(27, l_gentradeCsvColumnModel27.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel28 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.mobileNumberLabel);
            assertEquals(28, l_gentradeCsvColumnModel28.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel29 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.occupationDivLabel);
            assertEquals(29, l_gentradeCsvColumnModel29.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel30 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.officeLabel);
            assertEquals(30, l_gentradeCsvColumnModel30.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel31 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.officeZipCodeLabel);
            assertEquals(31, l_gentradeCsvColumnModel31.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel32 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.officeAddressLabel);
            assertEquals(32, l_gentradeCsvColumnModel32.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel33 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.officeTelephone1Label);
            assertEquals(33, l_gentradeCsvColumnModel33.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel34 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.officeTelephone2Label);
            assertEquals(34, l_gentradeCsvColumnModel34.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel35 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.officeTelephone3Label);
            assertEquals(35, l_gentradeCsvColumnModel35.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel36 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.departmentLabel);
            assertEquals(36, l_gentradeCsvColumnModel36.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel37 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.postLabel);
            assertEquals(37, l_gentradeCsvColumnModel37.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel38 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.houseHolderFamilyRelationLabel);
            assertEquals(38, l_gentradeCsvColumnModel38.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel39 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.houseHolderLabel);
            assertEquals(39, l_gentradeCsvColumnModel39.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel40 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.houseHolderOfficeLabel);
            assertEquals(40, l_gentradeCsvColumnModel40.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel41 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.houseHolderPostLabel);
            assertEquals(41, l_gentradeCsvColumnModel41.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel42 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.transferLabel);
            assertEquals(42, l_gentradeCsvColumnModel42.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel43 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.finInstitutionCodeLabel);
            assertEquals(43, l_gentradeCsvColumnModel43.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel44 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.finInstitutionNameLabel);
            assertEquals(44, l_gentradeCsvColumnModel44.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel45 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.finBranchCodeLabel);
            assertEquals(45, l_gentradeCsvColumnModel45.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel46 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.finBranchNameLabel);
            assertEquals(46, l_gentradeCsvColumnModel46.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel47 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.finSaveDivLabel);
            assertEquals(47, l_gentradeCsvColumnModel47.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel48 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.finAccountNoLabel);
            assertEquals(48, l_gentradeCsvColumnModel48.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel49 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.postalSaveCodeLabel);
            assertEquals(49, l_gentradeCsvColumnModel49.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel50 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.postalSaveNoLabel);
            assertEquals(50, l_gentradeCsvColumnModel50.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel51 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.experienceEquityDivLabel);
            assertEquals(51, l_gentradeCsvColumnModel51.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel52 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.experienceMarginDivLabel);
            assertEquals(52, l_gentradeCsvColumnModel52.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel53 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.experienceBondDivLabel);
            assertEquals(53, l_gentradeCsvColumnModel53.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel54 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.experienceOptionsDivLabel);
            assertEquals(54, l_gentradeCsvColumnModel54.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel55 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.experienceFundSkDivLabel);
            assertEquals(55, l_gentradeCsvColumnModel55.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel56 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.experienceFundBdDivLabel);
            assertEquals(56, l_gentradeCsvColumnModel56.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel57 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.experienceFuturesDivLabel);
            assertEquals(57, l_gentradeCsvColumnModel57.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel58 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.experienceFEquityDivLabel);
            assertEquals(58, l_gentradeCsvColumnModel58.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel59 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.experienceEtcDivLabel);
            assertEquals(59, l_gentradeCsvColumnModel59.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel60 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.interestEquityFlagLabel);
            assertEquals(60, l_gentradeCsvColumnModel60.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel61 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.interestMarginFlagLabel);
            assertEquals(61, l_gentradeCsvColumnModel61.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel62 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.interestBondFlagLabel);
            assertEquals(62, l_gentradeCsvColumnModel62.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel63 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.interestFundFlagLabel);
            assertEquals(63, l_gentradeCsvColumnModel63.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel64 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.interestFuturesFlagLabel);
            assertEquals(64, l_gentradeCsvColumnModel64.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel65 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.interestOptionsFlagLabel);
            assertEquals(65, l_gentradeCsvColumnModel65.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel66 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.annualIncomeDivLabel);
            assertEquals(66, l_gentradeCsvColumnModel66.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel67 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.assetValueDivLabel);
            assertEquals(67, l_gentradeCsvColumnModel67.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel68 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.specialAccEquityLabel);
            assertEquals(68, l_gentradeCsvColumnModel68.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel69 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.insiderFlagLabel);
            assertEquals(69, l_gentradeCsvColumnModel69.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel70 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.productNameLabel);
            assertEquals(70, l_gentradeCsvColumnModel70.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel71 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.typeDivLabel);
            assertEquals(71, l_gentradeCsvColumnModel71.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel72 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.incomeDormDivLable);
            assertEquals(72, l_gentradeCsvColumnModel72.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel73 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.insiderRelationDivLabel);
            assertEquals(73, l_gentradeCsvColumnModel73.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel74 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.investPurposeDivLabel);
            assertEquals(74, l_gentradeCsvColumnModel74.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel75 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.appliMotivatDivLabel);
            assertEquals(75, l_gentradeCsvColumnModel75.getColumnNumber());

            WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel76 =
                l_adminAccOpenApplyULCsv.getColumnModel(l_adminAccOpenApplyULCsv.idConfirmDocDivLabel);
            assertEquals(76, l_gentradeCsvColumnModel76.getColumnNumber());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

}
@
