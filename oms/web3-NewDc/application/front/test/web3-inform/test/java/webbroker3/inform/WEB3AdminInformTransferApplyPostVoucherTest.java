head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.53.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformTransferApplyPostVoucherTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �U�֐\���i�X���j�`�[�N���X(WEB3AdminInformTransferApplyPostVoucherTest)
Author Name         : Daiwa Institute of Research
Revesion History    : 
*/

package webbroker3.inform;

import java.lang.reflect.Field;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import webbroker3.accountopen.data.HostPostalTransVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 *
 */
public class WEB3AdminInformTransferApplyPostVoucherTest extends
        TestBaseForMock
{

    /**
     * �e��A���s
     */
    private VariousInformParams l_variousInformParams = null;

    /**
     * �e��A�����
     */
    private WEB3InformDetailInfoUnit l_informDetailInfoUnit = null;

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminInformTransferApplyPostVoucherTest.class);

    /**
     *
     * @@param arg0
     */
    public WEB3AdminInformTransferApplyPostVoucherTest(String arg0)
    {
        super(arg0);
    }

    /**
    *
    *
    */
    protected void setUp() throws Exception
    {

        super.setUp();

        this.l_variousInformParams = new VariousInformParams();

        // �،���ЃR�[�h
        this.l_variousInformParams.setInstitutionCode("987");

        // �A�����
        this.l_variousInformParams.setInformDiv("98");

        // ���ʃR�[�h
        this.l_variousInformParams.setRequestNumber("1234567890123");

        // ���X�R�[�h
        this.l_variousInformParams.setBranchCode("123");

        // �ڋq�R�[�h
        this.l_variousInformParams.setAccountCode("1234567");

        // ���҃R�[�h
        this.l_variousInformParams.setTraderCode("12345");

        // �ڋq��
        this.l_variousInformParams.setAccountName("�A�C�E�G�I�J�L�N�P�R�T�V�X�Z�\�^�`�c�e�g");

        // �ڋq���[���A�h���X
        this.l_variousInformParams.setEmailAddress("�i�j�k�l�m�n�q�t�w�z�}�~��������������������������");

        // �敪�P
        this.l_variousInformParams.setExtDiv1("11");

        // �敪�Q
        this.l_variousInformParams.setExtDiv2("2");

        // �敪�R
        this.l_variousInformParams.setExtDiv3("3");

        // �敪�S
        this.l_variousInformParams.setExtDiv4("4");

        // �敪�T
        this.l_variousInformParams.setExtDiv5("5");

        // �敪�U
        this.l_variousInformParams.setExtDiv6("6");

        // �敪�V
        this.l_variousInformParams.setExtDiv7("17");

        // �敪�W
        this.l_variousInformParams.setExtDiv8("18");

        // �敪�X
        this.l_variousInformParams.setExtDiv9("19");

        // �敪�P�O
        this.l_variousInformParams.setExtDiv10("20");

        // �敪�P�P
        this.l_variousInformParams.setExtDiv11("21");

        // �敪�P�Q
        this.l_variousInformParams.setExtDiv12("22");

        // �敪�P�R
        this.l_variousInformParams.setExtDiv13("23");

        // �敪�P�S
        this.l_variousInformParams.setExtDiv14("24");

        // �敪�P�T
        this.l_variousInformParams.setExtDiv15("25");

        // �敪�P�U
        this.l_variousInformParams.setExtDiv16("26");

        // �敪�P�V
        this.l_variousInformParams.setExtDiv17("27");

        // �敪�P�W
        this.l_variousInformParams.setExtDiv18("28");

        // �敪�P�X
        this.l_variousInformParams.setExtDiv19("29");

        // �敪�Q�O
        this.l_variousInformParams.setExtDiv20("30");

        // �敪�Q�P
        this.l_variousInformParams.setExtDiv21("31");

        // �敪�Q�Q
        this.l_variousInformParams.setExtDiv22("32");

        // �敪�Q�R
        this.l_variousInformParams.setExtDiv23("33");

        // �敪�Q�S
        this.l_variousInformParams.setExtDiv24("34");

        // �敪�Q�T
        this.l_variousInformParams.setExtDiv25("35");

        // �敪�Q�U
        this.l_variousInformParams.setExtDiv26("36");

        // �敪�Q�V
        this.l_variousInformParams.setExtDiv27("37");

        // �敪�Q�W
        this.l_variousInformParams.setExtDiv28("38");

        // �敪�Q�X
        this.l_variousInformParams.setExtDiv29("39");

        // �敪�R�O
        this.l_variousInformParams.setExtDiv30("40");

        // �敪�R�P
        this.l_variousInformParams.setExtDiv31("41");

        // �敪�R�Q
        this.l_variousInformParams.setExtDiv32("42");

        // �敪�R�R
        this.l_variousInformParams.setExtDiv33("43");

        // �敪�R�S
        this.l_variousInformParams.setExtDiv34("44");

        // �敪�R�T
        this.l_variousInformParams.setExtDiv35("45");

        // �敪�R�U
        this.l_variousInformParams.setExtDiv36("46");

        // �敪�R�V
        this.l_variousInformParams.setExtDiv37("47");

        // �敪�R�W
        this.l_variousInformParams.setExtDiv38("48");

        // �敪�R�X
        this.l_variousInformParams.setExtDiv39("49");

        // �敪�S�O
        this.l_variousInformParams.setExtDiv40("50");

        // �R�[�h�P
        this.l_variousInformParams.setExtCode1("51");

        // �R�[�h�Q
        this.l_variousInformParams.setExtCode2("52");

        // �R�[�h�R
        this.l_variousInformParams.setExtCode3("53");

        // �R�[�h�S
        this.l_variousInformParams.setExtCode4("54");

        // �R�[�h�T
        this.l_variousInformParams.setExtCode5("55");

        // �R�[�h�U
        this.l_variousInformParams.setExtCode6("56");

        // �R�[�h�V
        this.l_variousInformParams.setExtCode7("57");

        // �R�[�h�W
        this.l_variousInformParams.setExtCode8("58");

        // �R�[�h�X
        this.l_variousInformParams.setExtCode9("59");

        // �R�[�h�P�O
        this.l_variousInformParams.setExtCode10("60");

        // �e�L�X�g�P
        this.l_variousInformParams.setExtText1("61");

        // �e�L�X�g�Q
        this.l_variousInformParams.setExtText2("62");

        // �e�L�X�g�R
        this.l_variousInformParams.setExtText3("63");

        // �e�L�X�g�S
        this.l_variousInformParams.setExtText4("64");

        // �e�L�X�g�T
        this.l_variousInformParams.setExtText5("65");

        // �e�L�X�g�U
        this.l_variousInformParams.setExtText6("66");

        // �e�L�X�g�V
        this.l_variousInformParams.setExtText7("67");

        // �e�L�X�g�W
        this.l_variousInformParams.setExtText8("68");

        // �e�L�X�g�X
        this.l_variousInformParams.setExtText9("69");

        // �e�L�X�g�P�O
        this.l_variousInformParams.setExtText10("70");

        // �e�L�X�g�P�P
        this.l_variousInformParams.setExtText11("71");

        // �e�L�X�g�P�Q
        this.l_variousInformParams.setExtText12("72");

        // �e�L�X�g�P�R
        this.l_variousInformParams.setExtText13("73");

        // �e�L�X�g�P�S
        this.l_variousInformParams.setExtText14("74");

        // �e�L�X�g�P�T
        this.l_variousInformParams.setExtText15("75");

        // �e�L�X�g�P�U
        this.l_variousInformParams.setExtText16("76");

        // �e�L�X�g�P�V
        this.l_variousInformParams.setExtText17("77");

        // �e�L�X�g�P�W
        this.l_variousInformParams.setExtText18("78");

        // �e�L�X�g�P�X
        this.l_variousInformParams.setExtText19("79");

        // �e�L�X�g�Q�O
        this.l_variousInformParams.setExtText20("80");

        // �e�L�X�g�Q�P
        this.l_variousInformParams.setExtText21("81");

        // �e�L�X�g�Q�Q
        this.l_variousInformParams.setExtText22("82");

        // �e�L�X�g�Q�R
        this.l_variousInformParams.setExtText23("83");

        // �e�L�X�g�Q�S
        this.l_variousInformParams.setExtText24("84");

        // �e�L�X�g�Q�T
        this.l_variousInformParams.setExtText25("85");

        // �e�L�X�g�Q�U
        this.l_variousInformParams.setExtText26("86");

        // �e�L�X�g�Q�V
        this.l_variousInformParams.setExtText27("87");

        // �e�L�X�g�Q�W
        this.l_variousInformParams.setExtText28("88");

        // �e�L�X�g�Q�X
        this.l_variousInformParams.setExtText29("89");

        // �e�L�X�g�R�O
        this.l_variousInformParams.setExtText30("90");

        // �e�L�X�g�R�P
        this.l_variousInformParams.setExtText31("91");

        // �e�L�X�g�R�Q
        this.l_variousInformParams.setExtText32("92");

        // �e�L�X�g�R�R
        this.l_variousInformParams.setExtText33("93");

        // �e�L�X�g�R�S
        this.l_variousInformParams.setExtText34("94");

        // �e�L�X�g�R�T
        this.l_variousInformParams.setExtText35("95");

        // �e�L�X�g�R�U
        this.l_variousInformParams.setExtText36("96");

        // �e�L�X�g�R�V
        this.l_variousInformParams.setExtText37("97");

        // �e�L�X�g�R�W
        this.l_variousInformParams.setExtText38("98");

        // �e�L�X�g�R�X
        this.l_variousInformParams.setExtText39("99");

        // �e�L�X�g�S�O
        this.l_variousInformParams.setExtText40("A0");

        // ���l�P
        this.l_variousInformParams.setExtValue1(101);

        // ���l�Q
        this.l_variousInformParams.setExtValue2(102);

        // ���l�R
        this.l_variousInformParams.setExtValue3(103);

        // ���l�S
        this.l_variousInformParams.setExtValue4(104);

        // ���l�T
        this.l_variousInformParams.setExtValue5(105);

        // ���l�U
        this.l_variousInformParams.setExtValue6(106);

        // ���l�V
        this.l_variousInformParams.setExtValue7(107);

        // ���l�W
        this.l_variousInformParams.setExtValue8(108);

        // ���l�X
        this.l_variousInformParams.setExtValue9(109);

        // ���l�P�O
        this.l_variousInformParams.setExtValue10(110);

        // ���l�P�P
        this.l_variousInformParams.setExtValue11(111);

        // ���l�P�Q
        this.l_variousInformParams.setExtValue12(112);

        // ���l�P�R
        this.l_variousInformParams.setExtValue13(113);

        // ���l�P�S
        this.l_variousInformParams.setExtValue14(114);

        // ���l�P�T
        this.l_variousInformParams.setExtValue15(115);

        // ���l�P�U
        this.l_variousInformParams.setExtValue16(116);

        // ���l�P�V
        this.l_variousInformParams.setExtValue17(117);

        // ���l�P�W
        this.l_variousInformParams.setExtValue18(118);

        // ���l�P�X
        this.l_variousInformParams.setExtValue19(119);

        // ���l�Q�O
        this.l_variousInformParams.setExtValue20(120);

        // ���l�Q�P
        this.l_variousInformParams.setExtValue21(121);

        // ���l�Q�Q
        this.l_variousInformParams.setExtValue22(122);

        // ���l�Q�R
        this.l_variousInformParams.setExtValue23(123);

        // ���l�Q�S
        this.l_variousInformParams.setExtValue24(124);

        // ���l�Q�T
        this.l_variousInformParams.setExtValue25(125);

        // ���l�Q�U
        this.l_variousInformParams.setExtValue26(126);

        // ���l�Q�V
        this.l_variousInformParams.setExtValue27(127);

        // ���l�Q�W
        this.l_variousInformParams.setExtValue28(128);

        // ���l�Q�X
        this.l_variousInformParams.setExtValue29(129);

        // ���l�R�O
        this.l_variousInformParams.setExtValue30(130);

        // ���l�P
        this.l_variousInformParams.setExtNote1("note1");

        // ���l�Q
        this.l_variousInformParams.setExtNote2("note2");

        // �X�V�҃R�[�h
        this.l_variousInformParams.setLastUpdater("abcdefghijklmnopqrst");

        java.util.Date l_date = new java.util.Date();

        l_date = WEB3DateUtility.getDate("20070607","yyyyMMdd");
        l_date = WEB3DateUtility.addHour(l_date, 10);
        l_date = WEB3DateUtility.addMinute(l_date, 5);
        l_date = WEB3DateUtility.addSecond(l_date, 8);

        // �쐬����
        this.l_variousInformParams.setCreatedTimestamp(l_date);

        l_date = WEB3DateUtility.getDate("20070608","yyyyMMdd");
        l_date = WEB3DateUtility.addHour(l_date, 21);
        l_date = WEB3DateUtility.addMinute(l_date, 2);
        l_date = WEB3DateUtility.addSecond(l_date, 43);

        // �X�V����
        this.l_variousInformParams.setLastUpdatedTimestamp(l_date);

        // �����R�[�h
        this.l_variousInformParams.setFundCode("123123123");

        // ���҃R�[�h�iSONAR�j
        this.l_variousInformParams.setSonarTraderCode("67890");

        // �`�[�쐬��
        this.l_variousInformParams.setStatus("1");

        // �G���[���R�R�[�h
        this.l_variousInformParams.setErrorReasonCode("abcd");

        // �`�[���ʃR�[�h
        this.l_variousInformParams.setOrderRequestNumber("987987987");

        // �f�[�^�R�[�h
        this.l_variousInformParams.setRequestCode("55555");

        l_date = WEB3DateUtility.getDate("20070609","yyyyMMdd");
        l_date = WEB3DateUtility.addHour(l_date, 15);
        l_date = WEB3DateUtility.addMinute(l_date, 3);
        l_date = WEB3DateUtility.addSecond(l_date, 24);

        // �`�[���M����
        this.l_variousInformParams.setSendTimestamp(l_date);

        l_date = WEB3DateUtility.getDate("20070610","yyyyMMdd");
        l_date = WEB3DateUtility.addHour(l_date, 8);
        l_date = WEB3DateUtility.addMinute(l_date, 59);
        l_date = WEB3DateUtility.addSecond(l_date, 59);

        // �`�[��M����
        this.l_variousInformParams.setReceiptTimestamp(l_date);

    }

    /**
    *
    *
    */
    protected void tearDown() throws Exception
    {
        super.tearDown();

        // �e��A���s
        this.l_variousInformParams = null;

        // �e��A�����
        this.l_informDetailInfoUnit = null;
    }

    /**
     *
     *
     */
    public void testWEB3AdminInformTransferApplyPostVoucher_0001()
    {

        String STR_METHOD_NAME = " testWEB3AdminInformTransferApplyPostVoucher_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        // �U�֐\���i�X���j�`�[
        WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
            new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

        try
        {
            Field l_field =
                WEB3AdminInformTransferApplyPostVoucher.class.getDeclaredField("variousInformParams");
            l_field.setAccessible(true);
            VariousInformParams l_reflectVariousInformParams =
                (VariousInformParams)l_field.get(l_aminInformTransferApplyPostVoucher);

            // �،���ЃR�[�h
            assertEquals("987", l_reflectVariousInformParams.getInstitutionCode());

            // �A�����
            assertEquals("98", l_reflectVariousInformParams.getInformDiv());

            // ���ʃR�[�h
            assertEquals("1234567890123", l_reflectVariousInformParams.getRequestNumber());

            // ���X�R�[�h
            assertEquals("123", l_reflectVariousInformParams.getBranchCode());

            // �ڋq�R�[�h
            assertEquals("1234567", l_reflectVariousInformParams.getAccountCode());

            // ���҃R�[�h
            assertEquals("12345", l_reflectVariousInformParams.getTraderCode());

            // �ڋq��
            assertEquals("�A�C�E�G�I�J�L�N�P�R�T�V�X�Z�\�^�`�c�e�g",
                l_reflectVariousInformParams.getAccountName());

            // �ڋq���[���A�h���X
            assertEquals("�i�j�k�l�m�n�q�t�w�z�}�~��������������������������",
                l_reflectVariousInformParams.getEmailAddress());

            // �敪�P
            assertEquals("11", l_reflectVariousInformParams.getExtDiv1());

            // �敪�Q
            assertEquals("2", l_reflectVariousInformParams.getExtDiv2());

            // �敪�R
            assertEquals("3", l_reflectVariousInformParams.getExtDiv3());

            // �敪�S
            assertEquals("4", l_reflectVariousInformParams.getExtDiv4());

            // �敪�T
            assertEquals("5", l_reflectVariousInformParams.getExtDiv5());

            // �敪�U
            assertEquals("6", l_reflectVariousInformParams.getExtDiv6());

            // �敪�V
            assertEquals("17", l_reflectVariousInformParams.getExtDiv7());

            // �敪�W
            assertEquals("18", l_reflectVariousInformParams.getExtDiv8());

            // �敪�X
            assertEquals("19", l_reflectVariousInformParams.getExtDiv9());

            // �敪�P�O
            assertEquals("20", l_reflectVariousInformParams.getExtDiv10());

            // �敪�P�P
            assertEquals("21", l_reflectVariousInformParams.getExtDiv11());

            // �敪�P�Q
            assertEquals("22", l_reflectVariousInformParams.getExtDiv12());

            // �敪�P�R
            assertEquals("23", l_reflectVariousInformParams.getExtDiv13());

            // �敪�P�S
            assertEquals("24", l_reflectVariousInformParams.getExtDiv14());

            // �敪�P�T
            assertEquals("25", l_reflectVariousInformParams.getExtDiv15());

            // �敪�P�U
            assertEquals("26", l_reflectVariousInformParams.getExtDiv16());

            // �敪�P�V
            assertEquals("27", l_reflectVariousInformParams.getExtDiv17());

            // �敪�P�W
            assertEquals("28", l_reflectVariousInformParams.getExtDiv18());

            // �敪�P�X
            assertEquals("29", l_reflectVariousInformParams.getExtDiv19());

            // �敪�Q�O
            assertEquals("30", l_reflectVariousInformParams.getExtDiv20());

            // �敪�Q�P
            assertEquals("31", l_reflectVariousInformParams.getExtDiv21());

            // �敪�Q�Q
            assertEquals("32", l_reflectVariousInformParams.getExtDiv22());

            // �敪�Q�R
            assertEquals("33", l_reflectVariousInformParams.getExtDiv23());

            // �敪�Q�S
            assertEquals("34", l_reflectVariousInformParams.getExtDiv24());

            // �敪�Q�T
            assertEquals("35", l_reflectVariousInformParams.getExtDiv25());

            // �敪�Q�U
            assertEquals("36", l_reflectVariousInformParams.getExtDiv26());

            // �敪�Q�V
            assertEquals("37", l_reflectVariousInformParams.getExtDiv27());

            // �敪�Q�W
            assertEquals("38", l_reflectVariousInformParams.getExtDiv28());

            // �敪�Q�X
            assertEquals("39", l_reflectVariousInformParams.getExtDiv29());

            // �敪�R�O
            assertEquals("40", l_reflectVariousInformParams.getExtDiv30());

            // �敪�R�P
            assertEquals("41", l_reflectVariousInformParams.getExtDiv31());

            // �敪�R�Q
            assertEquals("42", l_reflectVariousInformParams.getExtDiv32());

            // �敪�R�R
            assertEquals("43", l_reflectVariousInformParams.getExtDiv33());

            // �敪�R�S
            assertEquals("44", l_reflectVariousInformParams.getExtDiv34());

            // �敪�R�T
            assertEquals("45", l_reflectVariousInformParams.getExtDiv35());

            // �敪�R�U
            assertEquals("46", l_reflectVariousInformParams.getExtDiv36());

            // �敪�R�V
            assertEquals("47", l_reflectVariousInformParams.getExtDiv37());

            // �敪�R�W
            assertEquals("48", l_reflectVariousInformParams.getExtDiv38());

            // �敪�R�X
            assertEquals("49", l_reflectVariousInformParams.getExtDiv39());

            // �敪�S�O
            assertEquals("50", l_reflectVariousInformParams.getExtDiv40());

            // �R�[�h�P
            assertEquals("51", l_reflectVariousInformParams.getExtCode1());

            // �R�[�h�Q
            assertEquals("52", l_reflectVariousInformParams.getExtCode2());

            // �R�[�h�R
            assertEquals("53", l_reflectVariousInformParams.getExtCode3());

            // �R�[�h�S
            assertEquals("54", l_reflectVariousInformParams.getExtCode4());

            // �R�[�h�T
            assertEquals("55", l_reflectVariousInformParams.getExtCode5());

            // �R�[�h�U
            assertEquals("56", l_reflectVariousInformParams.getExtCode6());

            // �R�[�h�V
            assertEquals("57", l_reflectVariousInformParams.getExtCode7());

            // �R�[�h�W
            assertEquals("58", l_reflectVariousInformParams.getExtCode8());

            // �R�[�h�X
            assertEquals("59", l_reflectVariousInformParams.getExtCode9());

            // �R�[�h�P�O
            assertEquals("60", l_reflectVariousInformParams.getExtCode10());

            // �e�L�X�g�P
            assertEquals("61", l_reflectVariousInformParams.getExtText1());

            // �e�L�X�g�Q
            assertEquals("62", l_reflectVariousInformParams.getExtText2());

            // �e�L�X�g�R
            assertEquals("63", l_reflectVariousInformParams.getExtText3());

            // �e�L�X�g�S
            assertEquals("64", l_reflectVariousInformParams.getExtText4());

            // �e�L�X�g�T
            assertEquals("65", l_reflectVariousInformParams.getExtText5());

            // �e�L�X�g�U
            assertEquals("66", l_reflectVariousInformParams.getExtText6());

            // �e�L�X�g�V
            assertEquals("67", l_reflectVariousInformParams.getExtText7());

            // �e�L�X�g�W
            assertEquals("68", l_reflectVariousInformParams.getExtText8());

            // �e�L�X�g�X
            assertEquals("69", l_reflectVariousInformParams.getExtText9());

            // �e�L�X�g�P�O
            assertEquals("70", l_reflectVariousInformParams.getExtText10());

            // �e�L�X�g�P�P
            assertEquals("71", l_reflectVariousInformParams.getExtText11());

            // �e�L�X�g�P�Q
            assertEquals("72", l_reflectVariousInformParams.getExtText12());

            // �e�L�X�g�P�R
            assertEquals("73", l_reflectVariousInformParams.getExtText13());

            // �e�L�X�g�P�S
            assertEquals("74", l_reflectVariousInformParams.getExtText14());

            // �e�L�X�g�P�T
            assertEquals("75", l_reflectVariousInformParams.getExtText15());

            // �e�L�X�g�P�U
            assertEquals("76", l_reflectVariousInformParams.getExtText16());

            // �e�L�X�g�P�V
            assertEquals("77", l_reflectVariousInformParams.getExtText17());

            // �e�L�X�g�P�W
            assertEquals("78", l_reflectVariousInformParams.getExtText18());

            // �e�L�X�g�P�X
            assertEquals("79", l_reflectVariousInformParams.getExtText19());

            // �e�L�X�g�Q�O
            assertEquals("80", l_reflectVariousInformParams.getExtText20());

            // �e�L�X�g�Q�P
            assertEquals("81", l_reflectVariousInformParams.getExtText21());

            // �e�L�X�g�Q�Q
            assertEquals("82", l_reflectVariousInformParams.getExtText22());

            // �e�L�X�g�Q�R
            assertEquals("83", l_reflectVariousInformParams.getExtText23());

            // �e�L�X�g�Q�S
            assertEquals("84", l_reflectVariousInformParams.getExtText24());

            // �e�L�X�g�Q�T
            assertEquals("85", l_reflectVariousInformParams.getExtText25());

            // �e�L�X�g�Q�U
            assertEquals("86", l_reflectVariousInformParams.getExtText26());

            // �e�L�X�g�Q�V
            assertEquals("87", l_reflectVariousInformParams.getExtText27());

            // �e�L�X�g�Q�W
            assertEquals("88", l_reflectVariousInformParams.getExtText28());

            // �e�L�X�g�Q�X
            assertEquals("89", l_reflectVariousInformParams.getExtText29());

            // �e�L�X�g�R�O
            assertEquals("90", l_reflectVariousInformParams.getExtText30());

            // �e�L�X�g�R�P
            assertEquals("91", l_reflectVariousInformParams.getExtText31());

            // �e�L�X�g�R�Q
            assertEquals("92", l_reflectVariousInformParams.getExtText32());

            // �e�L�X�g�R�R
            assertEquals("93", l_reflectVariousInformParams.getExtText33());

            // �e�L�X�g�R�S
            assertEquals("94", l_reflectVariousInformParams.getExtText34());

            // �e�L�X�g�R�T
            assertEquals("95", l_reflectVariousInformParams.getExtText35());

            // �e�L�X�g�R�U
            assertEquals("96", l_reflectVariousInformParams.getExtText36());

            // �e�L�X�g�R�V
            assertEquals("97", l_reflectVariousInformParams.getExtText37());

            // �e�L�X�g�R�W
            assertEquals("98", l_reflectVariousInformParams.getExtText38());

            // �e�L�X�g�R�X
            assertEquals("99", l_reflectVariousInformParams.getExtText39());

            // �e�L�X�g�S�O
            assertEquals("A0", l_reflectVariousInformParams.getExtText40());

            // ���l�P
            assertEquals(101, l_reflectVariousInformParams.getExtValue1());

            // ���l�Q
            assertEquals(102, l_reflectVariousInformParams.getExtValue2());

            // ���l�R
            assertEquals(103, l_reflectVariousInformParams.getExtValue3());

            // ���l�S
            assertEquals(104, l_reflectVariousInformParams.getExtValue4());

            // ���l�T
            assertEquals(105, l_reflectVariousInformParams.getExtValue5());

            // ���l�U
            assertEquals(106, l_reflectVariousInformParams.getExtValue6());

            // ���l�V
            assertEquals(107, l_reflectVariousInformParams.getExtValue7());

            // ���l�W
            assertEquals(108, l_reflectVariousInformParams.getExtValue8());

            // ���l�X
            assertEquals(109, l_reflectVariousInformParams.getExtValue9());

            // ���l�P�O
            assertEquals(110, l_reflectVariousInformParams.getExtValue10());

            // ���l�P�P
            assertEquals(111, l_reflectVariousInformParams.getExtValue11());

            // ���l�P�Q
            assertEquals(112, l_reflectVariousInformParams.getExtValue12());

            // ���l�P�R
            assertEquals(113, l_reflectVariousInformParams.getExtValue13());

            // ���l�P�S
            assertEquals(114, l_reflectVariousInformParams.getExtValue14());

            // ���l�P�T
            assertEquals(115, l_reflectVariousInformParams.getExtValue15());

            // ���l�P�U
            assertEquals(116, l_reflectVariousInformParams.getExtValue16());

            // ���l�P�V
            assertEquals(117, l_reflectVariousInformParams.getExtValue17());

            // ���l�P�W
            assertEquals(118, l_reflectVariousInformParams.getExtValue18());

            // ���l�P�X
            assertEquals(119, l_reflectVariousInformParams.getExtValue19());

            // ���l�Q�O
            assertEquals(120, l_reflectVariousInformParams.getExtValue20());

            // ���l�Q�P
            assertEquals(121, l_reflectVariousInformParams.getExtValue21());

            // ���l�Q�Q
            assertEquals(122, l_reflectVariousInformParams.getExtValue22());

            // ���l�Q�R
            assertEquals(123, l_reflectVariousInformParams.getExtValue23());

            // ���l�Q�S
            assertEquals(124, l_reflectVariousInformParams.getExtValue24());

            // ���l�Q�T
            assertEquals(125, l_reflectVariousInformParams.getExtValue25());

            // ���l�Q�U
            assertEquals(126, l_reflectVariousInformParams.getExtValue26());

            // ���l�Q�V
            assertEquals(127, l_reflectVariousInformParams.getExtValue27());

            // ���l�Q�W
            assertEquals(128, l_reflectVariousInformParams.getExtValue28());

            // ���l�Q�X
            assertEquals(129, l_reflectVariousInformParams.getExtValue29());

            // ���l�R�O
            assertEquals(130, l_reflectVariousInformParams.getExtValue30());

            // ���l�P
            assertEquals("note1", l_reflectVariousInformParams.getExtNote1());

            // ���l�Q
            assertEquals("note2", l_reflectVariousInformParams.getExtNote2());

            // �X�V�҃R�[�h
            assertEquals("abcdefghijklmnopqrst", l_reflectVariousInformParams.getLastUpdater());

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070607","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            // �쐬����
            assertEquals(l_date, l_reflectVariousInformParams.getCreatedTimestamp());

            l_date = WEB3DateUtility.getDate("20070608","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 21);
            l_date = WEB3DateUtility.addMinute(l_date, 2);
            l_date = WEB3DateUtility.addSecond(l_date, 43);

            // �X�V����
            assertEquals(l_date, l_reflectVariousInformParams.getLastUpdatedTimestamp());

            // �����R�[�h
            assertEquals("123123123", l_reflectVariousInformParams.getFundCode());

            // ���҃R�[�h�iSONAR�j
            assertEquals("67890", l_reflectVariousInformParams.getSonarTraderCode());

            // �`�[�쐬��
            assertEquals("1", l_reflectVariousInformParams.getStatus());

            // �G���[���R�R�[�h
            assertEquals("abcd", l_reflectVariousInformParams.getErrorReasonCode());

            // �`�[���ʃR�[�h
            assertEquals("987987987", l_reflectVariousInformParams.getOrderRequestNumber());

            // �f�[�^�R�[�h
            assertEquals("55555", l_reflectVariousInformParams.getRequestCode());

            l_date = WEB3DateUtility.getDate("20070609","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 15);
            l_date = WEB3DateUtility.addMinute(l_date, 3);
            l_date = WEB3DateUtility.addSecond(l_date, 24);

            // �`�[���M����
            assertEquals(l_date, l_reflectVariousInformParams.getSendTimestamp());

            l_date = WEB3DateUtility.getDate("20070610","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 8);
            l_date = WEB3DateUtility.addMinute(l_date, 59);
            l_date = WEB3DateUtility.addSecond(l_date, 59);

            // �`�[��M����
            assertEquals(l_date, l_reflectVariousInformParams.getReceiptTimestamp());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchFieldException l_exNSF)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exNSF);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (IllegalAccessException l_exIA)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exIA);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     *
     *
     */
    public void testWEB3AdminInformTransferApplyPostVoucher_0002()
    {

        String STR_METHOD_NAME = " testWEB3AdminInformTransferApplyPostVoucher_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        // �`�[���ʃR�[�h
        String l_strVoucherRequestNumber = "888";

        // �e��A�����ʃR�[�h
        String l_strInformCtrlRequestNumber = "999";

        // �U�֐\���i�X���j�`�[
        WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
            new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams, 
                    l_strVoucherRequestNumber, 
                    l_strInformCtrlRequestNumber);

        try
        {
            Field l_field =
                WEB3AdminInformTransferApplyPostVoucher.class.getDeclaredField("variousInformParams");
            l_field.setAccessible(true);
            VariousInformParams l_reflectVariousInformParams =
                (VariousInformParams)l_field.get(l_aminInformTransferApplyPostVoucher);

            // �،���ЃR�[�h
            assertEquals("987", l_reflectVariousInformParams.getInstitutionCode());

            // �A�����
            assertEquals("98", l_reflectVariousInformParams.getInformDiv());

            // ���ʃR�[�h
            assertEquals("999", l_reflectVariousInformParams.getRequestNumber());

            // ���X�R�[�h
            assertEquals("123", l_reflectVariousInformParams.getBranchCode());

            // �ڋq�R�[�h
            assertEquals("1234567", l_reflectVariousInformParams.getAccountCode());

            // ���҃R�[�h
            assertEquals("12345", l_reflectVariousInformParams.getTraderCode());

            // �ڋq��
            assertEquals("�A�C�E�G�I�J�L�N�P�R�T�V�X�Z�\�^�`�c�e�g",
                l_reflectVariousInformParams.getAccountName());

            // �ڋq���[���A�h���X
            assertEquals("�i�j�k�l�m�n�q�t�w�z�}�~��������������������������",
                l_reflectVariousInformParams.getEmailAddress());

            // �敪�P
            assertEquals("11", l_reflectVariousInformParams.getExtDiv1());

            // �敪�Q
            assertEquals("2", l_reflectVariousInformParams.getExtDiv2());

            // �敪�R
            assertEquals("3", l_reflectVariousInformParams.getExtDiv3());

            // �敪�S
            assertEquals("4", l_reflectVariousInformParams.getExtDiv4());

            // �敪�T
            assertEquals("5", l_reflectVariousInformParams.getExtDiv5());

            // �敪�U
            assertEquals("6", l_reflectVariousInformParams.getExtDiv6());

            // �敪�V
            assertEquals("17", l_reflectVariousInformParams.getExtDiv7());

            // �敪�W
            assertEquals("18", l_reflectVariousInformParams.getExtDiv8());

            // �敪�X
            assertEquals("19", l_reflectVariousInformParams.getExtDiv9());

            // �敪�P�O
            assertEquals("20", l_reflectVariousInformParams.getExtDiv10());

            // �敪�P�P
            assertEquals("21", l_reflectVariousInformParams.getExtDiv11());

            // �敪�P�Q
            assertEquals("22", l_reflectVariousInformParams.getExtDiv12());

            // �敪�P�R
            assertEquals("23", l_reflectVariousInformParams.getExtDiv13());

            // �敪�P�S
            assertEquals("24", l_reflectVariousInformParams.getExtDiv14());

            // �敪�P�T
            assertEquals("25", l_reflectVariousInformParams.getExtDiv15());

            // �敪�P�U
            assertEquals("26", l_reflectVariousInformParams.getExtDiv16());

            // �敪�P�V
            assertEquals("27", l_reflectVariousInformParams.getExtDiv17());

            // �敪�P�W
            assertEquals("28", l_reflectVariousInformParams.getExtDiv18());

            // �敪�P�X
            assertEquals("29", l_reflectVariousInformParams.getExtDiv19());

            // �敪�Q�O
            assertEquals("30", l_reflectVariousInformParams.getExtDiv20());

            // �敪�Q�P
            assertEquals("31", l_reflectVariousInformParams.getExtDiv21());

            // �敪�Q�Q
            assertEquals("32", l_reflectVariousInformParams.getExtDiv22());

            // �敪�Q�R
            assertEquals("33", l_reflectVariousInformParams.getExtDiv23());

            // �敪�Q�S
            assertEquals("34", l_reflectVariousInformParams.getExtDiv24());

            // �敪�Q�T
            assertEquals("35", l_reflectVariousInformParams.getExtDiv25());

            // �敪�Q�U
            assertEquals("36", l_reflectVariousInformParams.getExtDiv26());

            // �敪�Q�V
            assertEquals("37", l_reflectVariousInformParams.getExtDiv27());

            // �敪�Q�W
            assertEquals("38", l_reflectVariousInformParams.getExtDiv28());

            // �敪�Q�X
            assertEquals("39", l_reflectVariousInformParams.getExtDiv29());

            // �敪�R�O
            assertEquals("40", l_reflectVariousInformParams.getExtDiv30());

            // �敪�R�P
            assertEquals("41", l_reflectVariousInformParams.getExtDiv31());

            // �敪�R�Q
            assertEquals("42", l_reflectVariousInformParams.getExtDiv32());

            // �敪�R�R
            assertEquals("43", l_reflectVariousInformParams.getExtDiv33());

            // �敪�R�S
            assertEquals("44", l_reflectVariousInformParams.getExtDiv34());

            // �敪�R�T
            assertEquals("45", l_reflectVariousInformParams.getExtDiv35());

            // �敪�R�U
            assertEquals("46", l_reflectVariousInformParams.getExtDiv36());

            // �敪�R�V
            assertEquals("47", l_reflectVariousInformParams.getExtDiv37());

            // �敪�R�W
            assertEquals("48", l_reflectVariousInformParams.getExtDiv38());

            // �敪�R�X
            assertEquals("49", l_reflectVariousInformParams.getExtDiv39());

            // �敪�S�O
            assertEquals("50", l_reflectVariousInformParams.getExtDiv40());

            // �R�[�h�P
            assertEquals("51", l_reflectVariousInformParams.getExtCode1());

            // �R�[�h�Q
            assertEquals("52", l_reflectVariousInformParams.getExtCode2());

            // �R�[�h�R
            assertEquals("53", l_reflectVariousInformParams.getExtCode3());

            // �R�[�h�S
            assertEquals("54", l_reflectVariousInformParams.getExtCode4());

            // �R�[�h�T
            assertEquals("55", l_reflectVariousInformParams.getExtCode5());

            // �R�[�h�U
            assertEquals("56", l_reflectVariousInformParams.getExtCode6());

            // �R�[�h�V
            assertEquals("57", l_reflectVariousInformParams.getExtCode7());

            // �R�[�h�W
            assertEquals("58", l_reflectVariousInformParams.getExtCode8());

            // �R�[�h�X
            assertEquals("59", l_reflectVariousInformParams.getExtCode9());

            // �R�[�h�P�O
            assertEquals("60", l_reflectVariousInformParams.getExtCode10());

            // �e�L�X�g�P
            assertEquals("61", l_reflectVariousInformParams.getExtText1());

            // �e�L�X�g�Q
            assertEquals("62", l_reflectVariousInformParams.getExtText2());

            // �e�L�X�g�R
            assertEquals("63", l_reflectVariousInformParams.getExtText3());

            // �e�L�X�g�S
            assertEquals("64", l_reflectVariousInformParams.getExtText4());

            // �e�L�X�g�T
            assertEquals("65", l_reflectVariousInformParams.getExtText5());

            // �e�L�X�g�U
            assertEquals("66", l_reflectVariousInformParams.getExtText6());

            // �e�L�X�g�V
            assertEquals("67", l_reflectVariousInformParams.getExtText7());

            // �e�L�X�g�W
            assertEquals("68", l_reflectVariousInformParams.getExtText8());

            // �e�L�X�g�X
            assertEquals("69", l_reflectVariousInformParams.getExtText9());

            // �e�L�X�g�P�O
            assertEquals("70", l_reflectVariousInformParams.getExtText10());

            // �e�L�X�g�P�P
            assertEquals("71", l_reflectVariousInformParams.getExtText11());

            // �e�L�X�g�P�Q
            assertEquals("72", l_reflectVariousInformParams.getExtText12());

            // �e�L�X�g�P�R
            assertEquals("73", l_reflectVariousInformParams.getExtText13());

            // �e�L�X�g�P�S
            assertEquals("74", l_reflectVariousInformParams.getExtText14());

            // �e�L�X�g�P�T
            assertEquals("75", l_reflectVariousInformParams.getExtText15());

            // �e�L�X�g�P�U
            assertEquals("76", l_reflectVariousInformParams.getExtText16());

            // �e�L�X�g�P�V
            assertEquals("77", l_reflectVariousInformParams.getExtText17());

            // �e�L�X�g�P�W
            assertEquals("78", l_reflectVariousInformParams.getExtText18());

            // �e�L�X�g�P�X
            assertEquals("79", l_reflectVariousInformParams.getExtText19());

            // �e�L�X�g�Q�O
            assertEquals("80", l_reflectVariousInformParams.getExtText20());

            // �e�L�X�g�Q�P
            assertEquals("81", l_reflectVariousInformParams.getExtText21());

            // �e�L�X�g�Q�Q
            assertEquals("82", l_reflectVariousInformParams.getExtText22());

            // �e�L�X�g�Q�R
            assertEquals("83", l_reflectVariousInformParams.getExtText23());

            // �e�L�X�g�Q�S
            assertEquals("84", l_reflectVariousInformParams.getExtText24());

            // �e�L�X�g�Q�T
            assertEquals("85", l_reflectVariousInformParams.getExtText25());

            // �e�L�X�g�Q�U
            assertEquals("86", l_reflectVariousInformParams.getExtText26());

            // �e�L�X�g�Q�V
            assertEquals("87", l_reflectVariousInformParams.getExtText27());

            // �e�L�X�g�Q�W
            assertEquals("88", l_reflectVariousInformParams.getExtText28());

            // �e�L�X�g�Q�X
            assertEquals("89", l_reflectVariousInformParams.getExtText29());

            // �e�L�X�g�R�O
            assertEquals("90", l_reflectVariousInformParams.getExtText30());

            // �e�L�X�g�R�P
            assertEquals("91", l_reflectVariousInformParams.getExtText31());

            // �e�L�X�g�R�Q
            assertEquals("92", l_reflectVariousInformParams.getExtText32());

            // �e�L�X�g�R�R
            assertEquals("93", l_reflectVariousInformParams.getExtText33());

            // �e�L�X�g�R�S
            assertEquals("94", l_reflectVariousInformParams.getExtText34());

            // �e�L�X�g�R�T
            assertEquals("95", l_reflectVariousInformParams.getExtText35());

            // �e�L�X�g�R�U
            assertEquals("96", l_reflectVariousInformParams.getExtText36());

            // �e�L�X�g�R�V
            assertEquals("97", l_reflectVariousInformParams.getExtText37());

            // �e�L�X�g�R�W
            assertEquals("98", l_reflectVariousInformParams.getExtText38());

            // �e�L�X�g�R�X
            assertEquals("99", l_reflectVariousInformParams.getExtText39());

            // �e�L�X�g�S�O
            assertEquals("A0", l_reflectVariousInformParams.getExtText40());

            // ���l�P
            assertEquals(101, l_reflectVariousInformParams.getExtValue1());

            // ���l�Q
            assertEquals(102, l_reflectVariousInformParams.getExtValue2());

            // ���l�R
            assertEquals(103, l_reflectVariousInformParams.getExtValue3());

            // ���l�S
            assertEquals(104, l_reflectVariousInformParams.getExtValue4());

            // ���l�T
            assertEquals(105, l_reflectVariousInformParams.getExtValue5());

            // ���l�U
            assertEquals(106, l_reflectVariousInformParams.getExtValue6());

            // ���l�V
            assertEquals(107, l_reflectVariousInformParams.getExtValue7());

            // ���l�W
            assertEquals(108, l_reflectVariousInformParams.getExtValue8());

            // ���l�X
            assertEquals(109, l_reflectVariousInformParams.getExtValue9());

            // ���l�P�O
            assertEquals(110, l_reflectVariousInformParams.getExtValue10());

            // ���l�P�P
            assertEquals(111, l_reflectVariousInformParams.getExtValue11());

            // ���l�P�Q
            assertEquals(112, l_reflectVariousInformParams.getExtValue12());

            // ���l�P�R
            assertEquals(113, l_reflectVariousInformParams.getExtValue13());

            // ���l�P�S
            assertEquals(114, l_reflectVariousInformParams.getExtValue14());

            // ���l�P�T
            assertEquals(115, l_reflectVariousInformParams.getExtValue15());

            // ���l�P�U
            assertEquals(116, l_reflectVariousInformParams.getExtValue16());

            // ���l�P�V
            assertEquals(117, l_reflectVariousInformParams.getExtValue17());

            // ���l�P�W
            assertEquals(118, l_reflectVariousInformParams.getExtValue18());

            // ���l�P�X
            assertEquals(119, l_reflectVariousInformParams.getExtValue19());

            // ���l�Q�O
            assertEquals(120, l_reflectVariousInformParams.getExtValue20());

            // ���l�Q�P
            assertEquals(121, l_reflectVariousInformParams.getExtValue21());

            // ���l�Q�Q
            assertEquals(122, l_reflectVariousInformParams.getExtValue22());

            // ���l�Q�R
            assertEquals(123, l_reflectVariousInformParams.getExtValue23());

            // ���l�Q�S
            assertEquals(124, l_reflectVariousInformParams.getExtValue24());

            // ���l�Q�T
            assertEquals(125, l_reflectVariousInformParams.getExtValue25());

            // ���l�Q�U
            assertEquals(126, l_reflectVariousInformParams.getExtValue26());

            // ���l�Q�V
            assertEquals(127, l_reflectVariousInformParams.getExtValue27());

            // ���l�Q�W
            assertEquals(128, l_reflectVariousInformParams.getExtValue28());

            // ���l�Q�X
            assertEquals(129, l_reflectVariousInformParams.getExtValue29());

            // ���l�R�O
            assertEquals(130, l_reflectVariousInformParams.getExtValue30());

            // ���l�P
            assertEquals("note1", l_reflectVariousInformParams.getExtNote1());

            // ���l�Q
            assertEquals("note2", l_reflectVariousInformParams.getExtNote2());

            // �X�V�҃R�[�h
            assertEquals("abcdefghijklmnopqrst", l_reflectVariousInformParams.getLastUpdater());

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070607","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            // �쐬����
            assertEquals(l_date, l_reflectVariousInformParams.getCreatedTimestamp());

            l_date = WEB3DateUtility.getDate("20070608","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 21);
            l_date = WEB3DateUtility.addMinute(l_date, 2);
            l_date = WEB3DateUtility.addSecond(l_date, 43);

            // �X�V����
            assertEquals(l_date, l_reflectVariousInformParams.getLastUpdatedTimestamp());

            // �����R�[�h
            assertEquals("123123123", l_reflectVariousInformParams.getFundCode());

            // ���҃R�[�h�iSONAR�j
            assertEquals("67890", l_reflectVariousInformParams.getSonarTraderCode());

            // �`�[�쐬��
            assertEquals("1", l_reflectVariousInformParams.getStatus());

            // �G���[���R�R�[�h
            assertEquals("abcd", l_reflectVariousInformParams.getErrorReasonCode());

            // �`�[���ʃR�[�h
            assertEquals("888", l_reflectVariousInformParams.getOrderRequestNumber());

            // �f�[�^�R�[�h
            assertEquals("55555", l_reflectVariousInformParams.getRequestCode());

            l_date = WEB3DateUtility.getDate("20070609","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 15);
            l_date = WEB3DateUtility.addMinute(l_date, 3);
            l_date = WEB3DateUtility.addSecond(l_date, 24);

            // �`�[���M����
            assertEquals(l_date, l_reflectVariousInformParams.getSendTimestamp());

            l_date = WEB3DateUtility.getDate("20070610","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 8);
            l_date = WEB3DateUtility.addMinute(l_date, 59);
            l_date = WEB3DateUtility.addSecond(l_date, 59);

            // �`�[��M����
            assertEquals(l_date, l_reflectVariousInformParams.getReceiptTimestamp());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchFieldException l_exNSF)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exNSF);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (IllegalAccessException l_exIA)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exIA);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     *
     *
     */
    public void testWEB3AdminInformTransferApplyPostVoucher_0003()
    {

        String STR_METHOD_NAME = " testWEB3AdminInformTransferApplyPostVoucher_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        // �`�[���ʃR�[�h
        String l_strVoucherRequestNumber = null;

        // �e��A�����ʃR�[�h
        String l_strInformCtrlRequestNumber = null;

        // �U�֐\���i�X���j�`�[
        WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
            new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams, 
                    l_strVoucherRequestNumber, 
                    l_strInformCtrlRequestNumber);

        try
        {
            Field l_field =
                WEB3AdminInformTransferApplyPostVoucher.class.getDeclaredField("variousInformParams");
            l_field.setAccessible(true);
            VariousInformParams l_reflectVariousInformParams =
                (VariousInformParams)l_field.get(l_aminInformTransferApplyPostVoucher);

            // �،���ЃR�[�h
            assertEquals("987", l_reflectVariousInformParams.getInstitutionCode());

            // �A�����
            assertEquals("98", l_reflectVariousInformParams.getInformDiv());

            // ���ʃR�[�h
            assertEquals("1234567890123", l_reflectVariousInformParams.getRequestNumber());

            // ���X�R�[�h
            assertEquals("123", l_reflectVariousInformParams.getBranchCode());

            // �ڋq�R�[�h
            assertEquals("1234567", l_reflectVariousInformParams.getAccountCode());

            // ���҃R�[�h
            assertEquals("12345", l_reflectVariousInformParams.getTraderCode());

            // �ڋq��
            assertEquals("�A�C�E�G�I�J�L�N�P�R�T�V�X�Z�\�^�`�c�e�g",
                l_reflectVariousInformParams.getAccountName());

            // �ڋq���[���A�h���X
            assertEquals("�i�j�k�l�m�n�q�t�w�z�}�~��������������������������",
                l_reflectVariousInformParams.getEmailAddress());

            // �敪�P
            assertEquals("11", l_reflectVariousInformParams.getExtDiv1());

            // �敪�Q
            assertEquals("2", l_reflectVariousInformParams.getExtDiv2());

            // �敪�R
            assertEquals("3", l_reflectVariousInformParams.getExtDiv3());

            // �敪�S
            assertEquals("4", l_reflectVariousInformParams.getExtDiv4());

            // �敪�T
            assertEquals("5", l_reflectVariousInformParams.getExtDiv5());

            // �敪�U
            assertEquals("6", l_reflectVariousInformParams.getExtDiv6());

            // �敪�V
            assertEquals("17", l_reflectVariousInformParams.getExtDiv7());

            // �敪�W
            assertEquals("18", l_reflectVariousInformParams.getExtDiv8());

            // �敪�X
            assertEquals("19", l_reflectVariousInformParams.getExtDiv9());

            // �敪�P�O
            assertEquals("20", l_reflectVariousInformParams.getExtDiv10());

            // �敪�P�P
            assertEquals("21", l_reflectVariousInformParams.getExtDiv11());

            // �敪�P�Q
            assertEquals("22", l_reflectVariousInformParams.getExtDiv12());

            // �敪�P�R
            assertEquals("23", l_reflectVariousInformParams.getExtDiv13());

            // �敪�P�S
            assertEquals("24", l_reflectVariousInformParams.getExtDiv14());

            // �敪�P�T
            assertEquals("25", l_reflectVariousInformParams.getExtDiv15());

            // �敪�P�U
            assertEquals("26", l_reflectVariousInformParams.getExtDiv16());

            // �敪�P�V
            assertEquals("27", l_reflectVariousInformParams.getExtDiv17());

            // �敪�P�W
            assertEquals("28", l_reflectVariousInformParams.getExtDiv18());

            // �敪�P�X
            assertEquals("29", l_reflectVariousInformParams.getExtDiv19());

            // �敪�Q�O
            assertEquals("30", l_reflectVariousInformParams.getExtDiv20());

            // �敪�Q�P
            assertEquals("31", l_reflectVariousInformParams.getExtDiv21());

            // �敪�Q�Q
            assertEquals("32", l_reflectVariousInformParams.getExtDiv22());

            // �敪�Q�R
            assertEquals("33", l_reflectVariousInformParams.getExtDiv23());

            // �敪�Q�S
            assertEquals("34", l_reflectVariousInformParams.getExtDiv24());

            // �敪�Q�T
            assertEquals("35", l_reflectVariousInformParams.getExtDiv25());

            // �敪�Q�U
            assertEquals("36", l_reflectVariousInformParams.getExtDiv26());

            // �敪�Q�V
            assertEquals("37", l_reflectVariousInformParams.getExtDiv27());

            // �敪�Q�W
            assertEquals("38", l_reflectVariousInformParams.getExtDiv28());

            // �敪�Q�X
            assertEquals("39", l_reflectVariousInformParams.getExtDiv29());

            // �敪�R�O
            assertEquals("40", l_reflectVariousInformParams.getExtDiv30());

            // �敪�R�P
            assertEquals("41", l_reflectVariousInformParams.getExtDiv31());

            // �敪�R�Q
            assertEquals("42", l_reflectVariousInformParams.getExtDiv32());

            // �敪�R�R
            assertEquals("43", l_reflectVariousInformParams.getExtDiv33());

            // �敪�R�S
            assertEquals("44", l_reflectVariousInformParams.getExtDiv34());

            // �敪�R�T
            assertEquals("45", l_reflectVariousInformParams.getExtDiv35());

            // �敪�R�U
            assertEquals("46", l_reflectVariousInformParams.getExtDiv36());

            // �敪�R�V
            assertEquals("47", l_reflectVariousInformParams.getExtDiv37());

            // �敪�R�W
            assertEquals("48", l_reflectVariousInformParams.getExtDiv38());

            // �敪�R�X
            assertEquals("49", l_reflectVariousInformParams.getExtDiv39());

            // �敪�S�O
            assertEquals("50", l_reflectVariousInformParams.getExtDiv40());

            // �R�[�h�P
            assertEquals("51", l_reflectVariousInformParams.getExtCode1());

            // �R�[�h�Q
            assertEquals("52", l_reflectVariousInformParams.getExtCode2());

            // �R�[�h�R
            assertEquals("53", l_reflectVariousInformParams.getExtCode3());

            // �R�[�h�S
            assertEquals("54", l_reflectVariousInformParams.getExtCode4());

            // �R�[�h�T
            assertEquals("55", l_reflectVariousInformParams.getExtCode5());

            // �R�[�h�U
            assertEquals("56", l_reflectVariousInformParams.getExtCode6());

            // �R�[�h�V
            assertEquals("57", l_reflectVariousInformParams.getExtCode7());

            // �R�[�h�W
            assertEquals("58", l_reflectVariousInformParams.getExtCode8());

            // �R�[�h�X
            assertEquals("59", l_reflectVariousInformParams.getExtCode9());

            // �R�[�h�P�O
            assertEquals("60", l_reflectVariousInformParams.getExtCode10());

            // �e�L�X�g�P
            assertEquals("61", l_reflectVariousInformParams.getExtText1());

            // �e�L�X�g�Q
            assertEquals("62", l_reflectVariousInformParams.getExtText2());

            // �e�L�X�g�R
            assertEquals("63", l_reflectVariousInformParams.getExtText3());

            // �e�L�X�g�S
            assertEquals("64", l_reflectVariousInformParams.getExtText4());

            // �e�L�X�g�T
            assertEquals("65", l_reflectVariousInformParams.getExtText5());

            // �e�L�X�g�U
            assertEquals("66", l_reflectVariousInformParams.getExtText6());

            // �e�L�X�g�V
            assertEquals("67", l_reflectVariousInformParams.getExtText7());

            // �e�L�X�g�W
            assertEquals("68", l_reflectVariousInformParams.getExtText8());

            // �e�L�X�g�X
            assertEquals("69", l_reflectVariousInformParams.getExtText9());

            // �e�L�X�g�P�O
            assertEquals("70", l_reflectVariousInformParams.getExtText10());

            // �e�L�X�g�P�P
            assertEquals("71", l_reflectVariousInformParams.getExtText11());

            // �e�L�X�g�P�Q
            assertEquals("72", l_reflectVariousInformParams.getExtText12());

            // �e�L�X�g�P�R
            assertEquals("73", l_reflectVariousInformParams.getExtText13());

            // �e�L�X�g�P�S
            assertEquals("74", l_reflectVariousInformParams.getExtText14());

            // �e�L�X�g�P�T
            assertEquals("75", l_reflectVariousInformParams.getExtText15());

            // �e�L�X�g�P�U
            assertEquals("76", l_reflectVariousInformParams.getExtText16());

            // �e�L�X�g�P�V
            assertEquals("77", l_reflectVariousInformParams.getExtText17());

            // �e�L�X�g�P�W
            assertEquals("78", l_reflectVariousInformParams.getExtText18());

            // �e�L�X�g�P�X
            assertEquals("79", l_reflectVariousInformParams.getExtText19());

            // �e�L�X�g�Q�O
            assertEquals("80", l_reflectVariousInformParams.getExtText20());

            // �e�L�X�g�Q�P
            assertEquals("81", l_reflectVariousInformParams.getExtText21());

            // �e�L�X�g�Q�Q
            assertEquals("82", l_reflectVariousInformParams.getExtText22());

            // �e�L�X�g�Q�R
            assertEquals("83", l_reflectVariousInformParams.getExtText23());

            // �e�L�X�g�Q�S
            assertEquals("84", l_reflectVariousInformParams.getExtText24());

            // �e�L�X�g�Q�T
            assertEquals("85", l_reflectVariousInformParams.getExtText25());

            // �e�L�X�g�Q�U
            assertEquals("86", l_reflectVariousInformParams.getExtText26());

            // �e�L�X�g�Q�V
            assertEquals("87", l_reflectVariousInformParams.getExtText27());

            // �e�L�X�g�Q�W
            assertEquals("88", l_reflectVariousInformParams.getExtText28());

            // �e�L�X�g�Q�X
            assertEquals("89", l_reflectVariousInformParams.getExtText29());

            // �e�L�X�g�R�O
            assertEquals("90", l_reflectVariousInformParams.getExtText30());

            // �e�L�X�g�R�P
            assertEquals("91", l_reflectVariousInformParams.getExtText31());

            // �e�L�X�g�R�Q
            assertEquals("92", l_reflectVariousInformParams.getExtText32());

            // �e�L�X�g�R�R
            assertEquals("93", l_reflectVariousInformParams.getExtText33());

            // �e�L�X�g�R�S
            assertEquals("94", l_reflectVariousInformParams.getExtText34());

            // �e�L�X�g�R�T
            assertEquals("95", l_reflectVariousInformParams.getExtText35());

            // �e�L�X�g�R�U
            assertEquals("96", l_reflectVariousInformParams.getExtText36());

            // �e�L�X�g�R�V
            assertEquals("97", l_reflectVariousInformParams.getExtText37());

            // �e�L�X�g�R�W
            assertEquals("98", l_reflectVariousInformParams.getExtText38());

            // �e�L�X�g�R�X
            assertEquals("99", l_reflectVariousInformParams.getExtText39());

            // �e�L�X�g�S�O
            assertEquals("A0", l_reflectVariousInformParams.getExtText40());

            // ���l�P
            assertEquals(101, l_reflectVariousInformParams.getExtValue1());

            // ���l�Q
            assertEquals(102, l_reflectVariousInformParams.getExtValue2());

            // ���l�R
            assertEquals(103, l_reflectVariousInformParams.getExtValue3());

            // ���l�S
            assertEquals(104, l_reflectVariousInformParams.getExtValue4());

            // ���l�T
            assertEquals(105, l_reflectVariousInformParams.getExtValue5());

            // ���l�U
            assertEquals(106, l_reflectVariousInformParams.getExtValue6());

            // ���l�V
            assertEquals(107, l_reflectVariousInformParams.getExtValue7());

            // ���l�W
            assertEquals(108, l_reflectVariousInformParams.getExtValue8());

            // ���l�X
            assertEquals(109, l_reflectVariousInformParams.getExtValue9());

            // ���l�P�O
            assertEquals(110, l_reflectVariousInformParams.getExtValue10());

            // ���l�P�P
            assertEquals(111, l_reflectVariousInformParams.getExtValue11());

            // ���l�P�Q
            assertEquals(112, l_reflectVariousInformParams.getExtValue12());

            // ���l�P�R
            assertEquals(113, l_reflectVariousInformParams.getExtValue13());

            // ���l�P�S
            assertEquals(114, l_reflectVariousInformParams.getExtValue14());

            // ���l�P�T
            assertEquals(115, l_reflectVariousInformParams.getExtValue15());

            // ���l�P�U
            assertEquals(116, l_reflectVariousInformParams.getExtValue16());

            // ���l�P�V
            assertEquals(117, l_reflectVariousInformParams.getExtValue17());

            // ���l�P�W
            assertEquals(118, l_reflectVariousInformParams.getExtValue18());

            // ���l�P�X
            assertEquals(119, l_reflectVariousInformParams.getExtValue19());

            // ���l�Q�O
            assertEquals(120, l_reflectVariousInformParams.getExtValue20());

            // ���l�Q�P
            assertEquals(121, l_reflectVariousInformParams.getExtValue21());

            // ���l�Q�Q
            assertEquals(122, l_reflectVariousInformParams.getExtValue22());

            // ���l�Q�R
            assertEquals(123, l_reflectVariousInformParams.getExtValue23());

            // ���l�Q�S
            assertEquals(124, l_reflectVariousInformParams.getExtValue24());

            // ���l�Q�T
            assertEquals(125, l_reflectVariousInformParams.getExtValue25());

            // ���l�Q�U
            assertEquals(126, l_reflectVariousInformParams.getExtValue26());

            // ���l�Q�V
            assertEquals(127, l_reflectVariousInformParams.getExtValue27());

            // ���l�Q�W
            assertEquals(128, l_reflectVariousInformParams.getExtValue28());

            // ���l�Q�X
            assertEquals(129, l_reflectVariousInformParams.getExtValue29());

            // ���l�R�O
            assertEquals(130, l_reflectVariousInformParams.getExtValue30());

            // ���l�P
            assertEquals("note1", l_reflectVariousInformParams.getExtNote1());

            // ���l�Q
            assertEquals("note2", l_reflectVariousInformParams.getExtNote2());

            // �X�V�҃R�[�h
            assertEquals("abcdefghijklmnopqrst", l_reflectVariousInformParams.getLastUpdater());

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070607","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            // �쐬����
            assertEquals(l_date, l_reflectVariousInformParams.getCreatedTimestamp());

            l_date = WEB3DateUtility.getDate("20070608","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 21);
            l_date = WEB3DateUtility.addMinute(l_date, 2);
            l_date = WEB3DateUtility.addSecond(l_date, 43);

            // �X�V����
            assertEquals(l_date, l_reflectVariousInformParams.getLastUpdatedTimestamp());

            // �����R�[�h
            assertEquals("123123123", l_reflectVariousInformParams.getFundCode());

            // ���҃R�[�h�iSONAR�j
            assertEquals("67890", l_reflectVariousInformParams.getSonarTraderCode());

            // �`�[�쐬��
            assertEquals("1", l_reflectVariousInformParams.getStatus());

            // �G���[���R�R�[�h
            assertEquals("abcd", l_reflectVariousInformParams.getErrorReasonCode());

            // �`�[���ʃR�[�h
            assertEquals("987987987", l_reflectVariousInformParams.getOrderRequestNumber());

            // �f�[�^�R�[�h
            assertEquals("55555", l_reflectVariousInformParams.getRequestCode());

            l_date = WEB3DateUtility.getDate("20070609","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 15);
            l_date = WEB3DateUtility.addMinute(l_date, 3);
            l_date = WEB3DateUtility.addSecond(l_date, 24);

            // �`�[���M����
            assertEquals(l_date, l_reflectVariousInformParams.getSendTimestamp());

            l_date = WEB3DateUtility.getDate("20070610","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 8);
            l_date = WEB3DateUtility.addMinute(l_date, 59);
            l_date = WEB3DateUtility.addSecond(l_date, 59);

            // �`�[��M����
            assertEquals(l_date, l_reflectVariousInformParams.getReceiptTimestamp());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchFieldException l_exNSF)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exNSF);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (IllegalAccessException l_exIA)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exIA);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    /**
     *
     *
     */
    public void testSavePostRegistVoucherHost_0001()
    {

        String STR_METHOD_NAME = " testSavePostRegistVoucherHost_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doDeleteAllQuery(HostPostalTransVoucherParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("987");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);

            fail();
        }

        // �U�֐\���i�X���j�`�[
        WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
            new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

        try
        {
            l_aminInformTransferApplyPostVoucher.savePostRegistVoucherHost();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisHostPostalTransVoucher =
                l_queryProcessor.doFindAllQuery(HostPostalTransVoucherParams.TYPE);

            HostPostalTransVoucherParams l_hostPostalTransVoucherParams =
                (HostPostalTransVoucherParams)l_lisHostPostalTransVoucher.get(0);

            assertNotNull(l_hostPostalTransVoucherParams.getOrderRequestNumber());
            assertEquals("GI828", l_hostPostalTransVoucherParams.getRequestCode());
            assertEquals("987", l_hostPostalTransVoucherParams.getInstitutionCode());
            assertEquals("123", l_hostPostalTransVoucherParams.getBranchCode());
            assertEquals("1234567", l_hostPostalTransVoucherParams.getAccountCode());
            assertEquals("67890", l_hostPostalTransVoucherParams.getTraderCode());
            assertEquals("9999999999999", l_hostPostalTransVoucherParams.getAccOpenRequestNumber());
            assertEquals("0", l_hostPostalTransVoucherParams.getSerialNo());
            assertEquals("2", l_hostPostalTransVoucherParams.getTransferRange());
            assertEquals("123123123", l_hostPostalTransVoucherParams.getProductCodeSpec());
            assertEquals("4", l_hostPostalTransVoucherParams.getRegistDiv());
            assertEquals("5", l_hostPostalTransVoucherParams.getTransferDiv());
            assertEquals("6", l_hostPostalTransVoucherParams.getTransCommission());
            assertEquals("68", l_hostPostalTransVoucherParams.getPostalSaveCode());
            assertEquals("70", l_hostPostalTransVoucherParams.getPostalSaveNo());
            assertEquals("61", l_hostPostalTransVoucherParams.getFinAccountName());
            assertEquals("0", l_hostPostalTransVoucherParams.getStatus());
            assertNull(l_hostPostalTransVoucherParams.getSendTimestamp());
            assertNotNull(l_hostPostalTransVoucherParams.getCreatedTimestamp());
            assertNotNull(l_hostPostalTransVoucherParams.getLastUpdatedTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                l_queryProcessor.doDeleteAllQuery(HostPostalTransVoucherParams.TYPE);

            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

   /**
    *
    *
    */
    public void testDeletePostRegistVoucherHost_0001()
    {

        String STR_METHOD_NAME = " testDeletePostRegistVoucherHost_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doDeleteAllQuery(HostPostalTransVoucherParams.TYPE);

        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            HostPostalTransVoucherParams l_hostPostalTransVoucherParams =
                new HostPostalTransVoucherParams();

            l_hostPostalTransVoucherParams.setOrderRequestNumber("1001");
            l_hostPostalTransVoucherParams.setRequestCode("GI828");
            l_hostPostalTransVoucherParams.setInstitutionCode("000");
            l_hostPostalTransVoucherParams.setBranchCode("111");
            l_hostPostalTransVoucherParams.setAccountCode("7654321");
            l_hostPostalTransVoucherParams.setTraderCode("54321");
            l_hostPostalTransVoucherParams.setAccOpenRequestNumber("1234567890123");
            l_hostPostalTransVoucherParams.setSerialNo("0");
            l_hostPostalTransVoucherParams.setTransferRange("1");
            l_hostPostalTransVoucherParams.setProductCodeSpec("123123123");
            l_hostPostalTransVoucherParams.setRegistDiv("3");
            l_hostPostalTransVoucherParams.setTransferDiv("4");
            l_hostPostalTransVoucherParams.setTransCommission("5");
            l_hostPostalTransVoucherParams.setPostalSaveCode("6");
            l_hostPostalTransVoucherParams.setPostalSaveNo("7");
            l_hostPostalTransVoucherParams.setFinAccountName("8");
            l_hostPostalTransVoucherParams.setStatus("0");

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070608","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 1);
            l_date = WEB3DateUtility.addMinute(l_date, 2);
            l_date = WEB3DateUtility.addSecond(l_date, 3);

            l_hostPostalTransVoucherParams.setSendTimestamp(l_date);
            l_hostPostalTransVoucherParams.setCreatedTimestamp(l_date);
            l_hostPostalTransVoucherParams.setLastUpdatedTimestamp(l_date);

            TestDBUtility.insertWithDel(l_hostPostalTransVoucherParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            fail();
        }

        this.l_variousInformParams.setInstitutionCode("000");
        this.l_variousInformParams.setBranchCode("111");
        this.l_variousInformParams.setAccountCode("7654321");
        this.l_variousInformParams.setSonarTraderCode("54321");
        this.l_variousInformParams.setOrderRequestNumber("1001");
        // �U�֐\���i�X���j�`�[
        WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
            new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

        try
        {
            l_aminInformTransferApplyPostVoucher.deletePostRegistVoucherHost();

        }

        catch (WEB3BaseException l_exB)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exB);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisHostPostalTransVoucher =
                l_queryProcessor.doFindAllQuery(HostPostalTransVoucherParams.TYPE);


            assertEquals(0, l_lisHostPostalTransVoucher.size());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     *
     */
     public void testValidatePostInfo_0001()
     {

         String STR_METHOD_NAME = " testValidatePostInfo_0001()";
         log.entering(TEST_START + STR_METHOD_NAME);

         this.l_informDetailInfoUnit = new WEB3InformDetailInfoUnit();

         this.l_informDetailInfoUnit.div2 = "A";
         this.l_informDetailInfoUnit.div3 = null;
         this.l_informDetailInfoUnit.code1 = null;
         this.l_informDetailInfoUnit.code2 = null;

         // �U�֐\���i�X���j�`�[
         WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
             new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

         try
         {
             l_aminInformTransferApplyPostVoucher.validatePostInfo(this.l_informDetailInfoUnit);

             assertTrue(true);

             log.exiting(TEST_END + STR_METHOD_NAME);
         }
         catch (WEB3BaseException l_exB)
         {
             log.error(TEST_END + STR_METHOD_NAME, l_exB);
             log.exiting(TEST_END + STR_METHOD_NAME);

             fail();
         }
     }

     /**
      *
      *
      */
      public void testValidatePostInfo_0002()
      {

          String STR_METHOD_NAME = " testValidatePostInfo_0002()";
          log.entering(TEST_START + STR_METHOD_NAME);

          this.l_informDetailInfoUnit = new WEB3InformDetailInfoUnit();

          this.l_informDetailInfoUnit.div2 = "B";
          this.l_informDetailInfoUnit.div3 = null;
          this.l_informDetailInfoUnit.code1 = null;
          this.l_informDetailInfoUnit.code2 = null;

          // �U�֐\���i�X���j�`�[
          WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
              new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

          try
          {
              l_aminInformTransferApplyPostVoucher.validatePostInfo(this.l_informDetailInfoUnit);

              assertTrue(true);

              log.exiting(TEST_END + STR_METHOD_NAME);
          }
          catch (WEB3BaseException l_exB)
          {
              log.error(TEST_END + STR_METHOD_NAME, l_exB);
              log.exiting(TEST_END + STR_METHOD_NAME);

              fail();
          }
      }

      /**
       *
       *
       */
       public void testValidatePostInfo_0003()
       {

           String STR_METHOD_NAME = " testValidatePostInfo_0003()";
           log.entering(TEST_START + STR_METHOD_NAME);

           this.l_informDetailInfoUnit = new WEB3InformDetailInfoUnit();

           this.l_informDetailInfoUnit.div2 = "C";
           this.l_informDetailInfoUnit.div3 = null;
           this.l_informDetailInfoUnit.code1 = null;
           this.l_informDetailInfoUnit.code2 = null;

           // �U�֐\���i�X���j�`�[
           WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
               new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

           try
           {
               l_aminInformTransferApplyPostVoucher.validatePostInfo(this.l_informDetailInfoUnit);

               assertTrue(true);

               log.exiting(TEST_END + STR_METHOD_NAME);
           }
           catch (WEB3BaseException l_exB)
           {
               log.error(TEST_END + STR_METHOD_NAME, l_exB);
               log.exiting(TEST_END + STR_METHOD_NAME);

               fail();
           }
       }

       /**
        *
        *
        */
        public void testValidatePostInfo_0004()
        {

            String STR_METHOD_NAME = " testValidatePostInfo_0004()";
            log.entering(TEST_START + STR_METHOD_NAME);

            this.l_informDetailInfoUnit = new WEB3InformDetailInfoUnit();

            this.l_informDetailInfoUnit.div2 = "A";
            this.l_informDetailInfoUnit.div3 = "2";
            this.l_informDetailInfoUnit.code1 = null;
            this.l_informDetailInfoUnit.code2 = null;

            // �U�֐\���i�X���j�`�[
            WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

            try
            {
                l_aminInformTransferApplyPostVoucher.validatePostInfo(this.l_informDetailInfoUnit);

                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
            catch (WEB3BaseException l_exB)
            {
                assertTrue(true);

                log.error(TEST_END + STR_METHOD_NAME, l_exB);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
        }

        /**
         *
         *
         */
         public void testValidatePostInfo_0005()
         {

             String STR_METHOD_NAME = " testValidatePostInfo_0005()";
             log.entering(TEST_START + STR_METHOD_NAME);

             this.l_informDetailInfoUnit = new WEB3InformDetailInfoUnit();

             this.l_informDetailInfoUnit.div2 = "B";
             this.l_informDetailInfoUnit.div3 = null;
             this.l_informDetailInfoUnit.code1 = "12345";
             this.l_informDetailInfoUnit.code2 = null;

             // �U�֐\���i�X���j�`�[
             WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
                 new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

             try
             {
                 l_aminInformTransferApplyPostVoucher.validatePostInfo(this.l_informDetailInfoUnit);

                 log.exiting(TEST_END + STR_METHOD_NAME);

                 fail();
             }
             catch (WEB3BaseException l_exB)
             {
                 assertTrue(true);

                 log.error(TEST_END + STR_METHOD_NAME, l_exB);
                 log.exiting(TEST_END + STR_METHOD_NAME);
             }
         }

         /**
          *
          *
          */
          public void testValidatePostInfo_0006()
          {

              String STR_METHOD_NAME = " testValidatePostInfo_0006()";
              log.entering(TEST_START + STR_METHOD_NAME);

              this.l_informDetailInfoUnit = new WEB3InformDetailInfoUnit();

              this.l_informDetailInfoUnit.div2 = "C";
              this.l_informDetailInfoUnit.div3 = null;
              this.l_informDetailInfoUnit.code1 = null;
              this.l_informDetailInfoUnit.code2 = "54321";

              // �U�֐\���i�X���j�`�[
              WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
                  new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

              try
              {
                  l_aminInformTransferApplyPostVoucher.validatePostInfo(this.l_informDetailInfoUnit);

                  log.exiting(TEST_END + STR_METHOD_NAME);

                  fail();
              }
              catch (WEB3BaseException l_exB)
              {
                  assertTrue(true);

                  log.error(TEST_END + STR_METHOD_NAME, l_exB);
                  log.exiting(TEST_END + STR_METHOD_NAME);
              }
          }

          /**
           *
           *
           */
           public void testValidatePostInfo_0007()
           {

               String STR_METHOD_NAME = " testValidatePostInfo_0007()";
               log.entering(TEST_START + STR_METHOD_NAME);

               this.l_informDetailInfoUnit = new WEB3InformDetailInfoUnit();

               this.l_informDetailInfoUnit.div2 = "1";
               this.l_informDetailInfoUnit.div3 = "2";
               this.l_informDetailInfoUnit.code1 = "12345";
               this.l_informDetailInfoUnit.code2 = "54321";

               // �U�֐\���i�X���j�`�[
               WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
                   new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

               try
               {
                   l_aminInformTransferApplyPostVoucher.validatePostInfo(this.l_informDetailInfoUnit);

                   assertTrue(true);

                   log.exiting(TEST_END + STR_METHOD_NAME);
               }
               catch (WEB3BaseException l_exB)
               {
                   log.error(TEST_END + STR_METHOD_NAME, l_exB);
                   log.exiting(TEST_END + STR_METHOD_NAME);

                   fail();
               }
           }

           /**
            *
            *
            */
            public void testValidatePostInfo_0008()
            {

                String STR_METHOD_NAME = " testValidatePostInfo_0008()";
                log.entering(TEST_START + STR_METHOD_NAME);

                this.l_informDetailInfoUnit = new WEB3InformDetailInfoUnit();

                this.l_informDetailInfoUnit.div2 = "2";
                this.l_informDetailInfoUnit.div3 = null;
                this.l_informDetailInfoUnit.code1 = null;
                this.l_informDetailInfoUnit.code2 = null;

                // �U�֐\���i�X���j�`�[
                WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
                    new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

                try
                {
                    l_aminInformTransferApplyPostVoucher.validatePostInfo(this.l_informDetailInfoUnit);

                    log.exiting(TEST_END + STR_METHOD_NAME);

                    fail();
                }
                catch (WEB3BaseException l_exB)
                {
                    assertTrue(true);

                    log.error(TEST_END + STR_METHOD_NAME, l_exB);
                    log.exiting(TEST_END + STR_METHOD_NAME);
                }
            }

}
@
