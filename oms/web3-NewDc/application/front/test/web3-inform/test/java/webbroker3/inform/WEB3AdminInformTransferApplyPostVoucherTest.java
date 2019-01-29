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
Copyright           : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name           : U‘Ö\i—X’™j“`•[ƒNƒ‰ƒX(WEB3AdminInformTransferApplyPostVoucherTest)
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
     * Šeí˜A—s
     */
    private VariousInformParams l_variousInformParams = null;

    /**
     * Šeí˜A—î•ñ
     */
    private WEB3InformDetailInfoUnit l_informDetailInfoUnit = null;

    /**
     * (ƒƒOo—Íƒ†[ƒeƒBƒŠƒeƒB)
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

        // ØŒ”‰ïĞƒR[ƒh
        this.l_variousInformParams.setInstitutionCode("987");

        // ˜A—í•Ê
        this.l_variousInformParams.setInformDiv("98");

        // ¯•ÊƒR[ƒh
        this.l_variousInformParams.setRequestNumber("1234567890123");

        // •”“XƒR[ƒh
        this.l_variousInformParams.setBranchCode("123");

        // ŒÚ‹qƒR[ƒh
        this.l_variousInformParams.setAccountCode("1234567");

        // ˆµÒƒR[ƒh
        this.l_variousInformParams.setTraderCode("12345");

        // ŒÚ‹q–¼
        this.l_variousInformParams.setAccountName("ƒAƒCƒEƒGƒIƒJƒLƒNƒPƒRƒTƒVƒXƒZƒ\ƒ^ƒ`ƒcƒeƒg");

        // ŒÚ‹qƒ[ƒ‹ƒAƒhƒŒƒX
        this.l_variousInformParams.setEmailAddress("ƒiƒjƒkƒlƒmƒnƒqƒtƒwƒzƒ}ƒ~ƒ€ƒƒ‚ƒ„ƒ†ƒˆƒ‰ƒŠƒ‹ƒŒƒƒƒ’");

        // ‹æ•ª‚P
        this.l_variousInformParams.setExtDiv1("11");

        // ‹æ•ª‚Q
        this.l_variousInformParams.setExtDiv2("2");

        // ‹æ•ª‚R
        this.l_variousInformParams.setExtDiv3("3");

        // ‹æ•ª‚S
        this.l_variousInformParams.setExtDiv4("4");

        // ‹æ•ª‚T
        this.l_variousInformParams.setExtDiv5("5");

        // ‹æ•ª‚U
        this.l_variousInformParams.setExtDiv6("6");

        // ‹æ•ª‚V
        this.l_variousInformParams.setExtDiv7("17");

        // ‹æ•ª‚W
        this.l_variousInformParams.setExtDiv8("18");

        // ‹æ•ª‚X
        this.l_variousInformParams.setExtDiv9("19");

        // ‹æ•ª‚P‚O
        this.l_variousInformParams.setExtDiv10("20");

        // ‹æ•ª‚P‚P
        this.l_variousInformParams.setExtDiv11("21");

        // ‹æ•ª‚P‚Q
        this.l_variousInformParams.setExtDiv12("22");

        // ‹æ•ª‚P‚R
        this.l_variousInformParams.setExtDiv13("23");

        // ‹æ•ª‚P‚S
        this.l_variousInformParams.setExtDiv14("24");

        // ‹æ•ª‚P‚T
        this.l_variousInformParams.setExtDiv15("25");

        // ‹æ•ª‚P‚U
        this.l_variousInformParams.setExtDiv16("26");

        // ‹æ•ª‚P‚V
        this.l_variousInformParams.setExtDiv17("27");

        // ‹æ•ª‚P‚W
        this.l_variousInformParams.setExtDiv18("28");

        // ‹æ•ª‚P‚X
        this.l_variousInformParams.setExtDiv19("29");

        // ‹æ•ª‚Q‚O
        this.l_variousInformParams.setExtDiv20("30");

        // ‹æ•ª‚Q‚P
        this.l_variousInformParams.setExtDiv21("31");

        // ‹æ•ª‚Q‚Q
        this.l_variousInformParams.setExtDiv22("32");

        // ‹æ•ª‚Q‚R
        this.l_variousInformParams.setExtDiv23("33");

        // ‹æ•ª‚Q‚S
        this.l_variousInformParams.setExtDiv24("34");

        // ‹æ•ª‚Q‚T
        this.l_variousInformParams.setExtDiv25("35");

        // ‹æ•ª‚Q‚U
        this.l_variousInformParams.setExtDiv26("36");

        // ‹æ•ª‚Q‚V
        this.l_variousInformParams.setExtDiv27("37");

        // ‹æ•ª‚Q‚W
        this.l_variousInformParams.setExtDiv28("38");

        // ‹æ•ª‚Q‚X
        this.l_variousInformParams.setExtDiv29("39");

        // ‹æ•ª‚R‚O
        this.l_variousInformParams.setExtDiv30("40");

        // ‹æ•ª‚R‚P
        this.l_variousInformParams.setExtDiv31("41");

        // ‹æ•ª‚R‚Q
        this.l_variousInformParams.setExtDiv32("42");

        // ‹æ•ª‚R‚R
        this.l_variousInformParams.setExtDiv33("43");

        // ‹æ•ª‚R‚S
        this.l_variousInformParams.setExtDiv34("44");

        // ‹æ•ª‚R‚T
        this.l_variousInformParams.setExtDiv35("45");

        // ‹æ•ª‚R‚U
        this.l_variousInformParams.setExtDiv36("46");

        // ‹æ•ª‚R‚V
        this.l_variousInformParams.setExtDiv37("47");

        // ‹æ•ª‚R‚W
        this.l_variousInformParams.setExtDiv38("48");

        // ‹æ•ª‚R‚X
        this.l_variousInformParams.setExtDiv39("49");

        // ‹æ•ª‚S‚O
        this.l_variousInformParams.setExtDiv40("50");

        // ƒR[ƒh‚P
        this.l_variousInformParams.setExtCode1("51");

        // ƒR[ƒh‚Q
        this.l_variousInformParams.setExtCode2("52");

        // ƒR[ƒh‚R
        this.l_variousInformParams.setExtCode3("53");

        // ƒR[ƒh‚S
        this.l_variousInformParams.setExtCode4("54");

        // ƒR[ƒh‚T
        this.l_variousInformParams.setExtCode5("55");

        // ƒR[ƒh‚U
        this.l_variousInformParams.setExtCode6("56");

        // ƒR[ƒh‚V
        this.l_variousInformParams.setExtCode7("57");

        // ƒR[ƒh‚W
        this.l_variousInformParams.setExtCode8("58");

        // ƒR[ƒh‚X
        this.l_variousInformParams.setExtCode9("59");

        // ƒR[ƒh‚P‚O
        this.l_variousInformParams.setExtCode10("60");

        // ƒeƒLƒXƒg‚P
        this.l_variousInformParams.setExtText1("61");

        // ƒeƒLƒXƒg‚Q
        this.l_variousInformParams.setExtText2("62");

        // ƒeƒLƒXƒg‚R
        this.l_variousInformParams.setExtText3("63");

        // ƒeƒLƒXƒg‚S
        this.l_variousInformParams.setExtText4("64");

        // ƒeƒLƒXƒg‚T
        this.l_variousInformParams.setExtText5("65");

        // ƒeƒLƒXƒg‚U
        this.l_variousInformParams.setExtText6("66");

        // ƒeƒLƒXƒg‚V
        this.l_variousInformParams.setExtText7("67");

        // ƒeƒLƒXƒg‚W
        this.l_variousInformParams.setExtText8("68");

        // ƒeƒLƒXƒg‚X
        this.l_variousInformParams.setExtText9("69");

        // ƒeƒLƒXƒg‚P‚O
        this.l_variousInformParams.setExtText10("70");

        // ƒeƒLƒXƒg‚P‚P
        this.l_variousInformParams.setExtText11("71");

        // ƒeƒLƒXƒg‚P‚Q
        this.l_variousInformParams.setExtText12("72");

        // ƒeƒLƒXƒg‚P‚R
        this.l_variousInformParams.setExtText13("73");

        // ƒeƒLƒXƒg‚P‚S
        this.l_variousInformParams.setExtText14("74");

        // ƒeƒLƒXƒg‚P‚T
        this.l_variousInformParams.setExtText15("75");

        // ƒeƒLƒXƒg‚P‚U
        this.l_variousInformParams.setExtText16("76");

        // ƒeƒLƒXƒg‚P‚V
        this.l_variousInformParams.setExtText17("77");

        // ƒeƒLƒXƒg‚P‚W
        this.l_variousInformParams.setExtText18("78");

        // ƒeƒLƒXƒg‚P‚X
        this.l_variousInformParams.setExtText19("79");

        // ƒeƒLƒXƒg‚Q‚O
        this.l_variousInformParams.setExtText20("80");

        // ƒeƒLƒXƒg‚Q‚P
        this.l_variousInformParams.setExtText21("81");

        // ƒeƒLƒXƒg‚Q‚Q
        this.l_variousInformParams.setExtText22("82");

        // ƒeƒLƒXƒg‚Q‚R
        this.l_variousInformParams.setExtText23("83");

        // ƒeƒLƒXƒg‚Q‚S
        this.l_variousInformParams.setExtText24("84");

        // ƒeƒLƒXƒg‚Q‚T
        this.l_variousInformParams.setExtText25("85");

        // ƒeƒLƒXƒg‚Q‚U
        this.l_variousInformParams.setExtText26("86");

        // ƒeƒLƒXƒg‚Q‚V
        this.l_variousInformParams.setExtText27("87");

        // ƒeƒLƒXƒg‚Q‚W
        this.l_variousInformParams.setExtText28("88");

        // ƒeƒLƒXƒg‚Q‚X
        this.l_variousInformParams.setExtText29("89");

        // ƒeƒLƒXƒg‚R‚O
        this.l_variousInformParams.setExtText30("90");

        // ƒeƒLƒXƒg‚R‚P
        this.l_variousInformParams.setExtText31("91");

        // ƒeƒLƒXƒg‚R‚Q
        this.l_variousInformParams.setExtText32("92");

        // ƒeƒLƒXƒg‚R‚R
        this.l_variousInformParams.setExtText33("93");

        // ƒeƒLƒXƒg‚R‚S
        this.l_variousInformParams.setExtText34("94");

        // ƒeƒLƒXƒg‚R‚T
        this.l_variousInformParams.setExtText35("95");

        // ƒeƒLƒXƒg‚R‚U
        this.l_variousInformParams.setExtText36("96");

        // ƒeƒLƒXƒg‚R‚V
        this.l_variousInformParams.setExtText37("97");

        // ƒeƒLƒXƒg‚R‚W
        this.l_variousInformParams.setExtText38("98");

        // ƒeƒLƒXƒg‚R‚X
        this.l_variousInformParams.setExtText39("99");

        // ƒeƒLƒXƒg‚S‚O
        this.l_variousInformParams.setExtText40("A0");

        // ”’l‚P
        this.l_variousInformParams.setExtValue1(101);

        // ”’l‚Q
        this.l_variousInformParams.setExtValue2(102);

        // ”’l‚R
        this.l_variousInformParams.setExtValue3(103);

        // ”’l‚S
        this.l_variousInformParams.setExtValue4(104);

        // ”’l‚T
        this.l_variousInformParams.setExtValue5(105);

        // ”’l‚U
        this.l_variousInformParams.setExtValue6(106);

        // ”’l‚V
        this.l_variousInformParams.setExtValue7(107);

        // ”’l‚W
        this.l_variousInformParams.setExtValue8(108);

        // ”’l‚X
        this.l_variousInformParams.setExtValue9(109);

        // ”’l‚P‚O
        this.l_variousInformParams.setExtValue10(110);

        // ”’l‚P‚P
        this.l_variousInformParams.setExtValue11(111);

        // ”’l‚P‚Q
        this.l_variousInformParams.setExtValue12(112);

        // ”’l‚P‚R
        this.l_variousInformParams.setExtValue13(113);

        // ”’l‚P‚S
        this.l_variousInformParams.setExtValue14(114);

        // ”’l‚P‚T
        this.l_variousInformParams.setExtValue15(115);

        // ”’l‚P‚U
        this.l_variousInformParams.setExtValue16(116);

        // ”’l‚P‚V
        this.l_variousInformParams.setExtValue17(117);

        // ”’l‚P‚W
        this.l_variousInformParams.setExtValue18(118);

        // ”’l‚P‚X
        this.l_variousInformParams.setExtValue19(119);

        // ”’l‚Q‚O
        this.l_variousInformParams.setExtValue20(120);

        // ”’l‚Q‚P
        this.l_variousInformParams.setExtValue21(121);

        // ”’l‚Q‚Q
        this.l_variousInformParams.setExtValue22(122);

        // ”’l‚Q‚R
        this.l_variousInformParams.setExtValue23(123);

        // ”’l‚Q‚S
        this.l_variousInformParams.setExtValue24(124);

        // ”’l‚Q‚T
        this.l_variousInformParams.setExtValue25(125);

        // ”’l‚Q‚U
        this.l_variousInformParams.setExtValue26(126);

        // ”’l‚Q‚V
        this.l_variousInformParams.setExtValue27(127);

        // ”’l‚Q‚W
        this.l_variousInformParams.setExtValue28(128);

        // ”’l‚Q‚X
        this.l_variousInformParams.setExtValue29(129);

        // ”’l‚R‚O
        this.l_variousInformParams.setExtValue30(130);

        // ”õl‚P
        this.l_variousInformParams.setExtNote1("note1");

        // ”õl‚Q
        this.l_variousInformParams.setExtNote2("note2");

        // XVÒƒR[ƒh
        this.l_variousInformParams.setLastUpdater("abcdefghijklmnopqrst");

        java.util.Date l_date = new java.util.Date();

        l_date = WEB3DateUtility.getDate("20070607","yyyyMMdd");
        l_date = WEB3DateUtility.addHour(l_date, 10);
        l_date = WEB3DateUtility.addMinute(l_date, 5);
        l_date = WEB3DateUtility.addSecond(l_date, 8);

        // ì¬“ú
        this.l_variousInformParams.setCreatedTimestamp(l_date);

        l_date = WEB3DateUtility.getDate("20070608","yyyyMMdd");
        l_date = WEB3DateUtility.addHour(l_date, 21);
        l_date = WEB3DateUtility.addMinute(l_date, 2);
        l_date = WEB3DateUtility.addSecond(l_date, 43);

        // XV“ú
        this.l_variousInformParams.setLastUpdatedTimestamp(l_date);

        // –Á•¿ƒR[ƒh
        this.l_variousInformParams.setFundCode("123123123");

        // ˆµÒƒR[ƒhiSONARj
        this.l_variousInformParams.setSonarTraderCode("67890");

        // “`•[ì¬ó‹µ
        this.l_variousInformParams.setStatus("1");

        // ƒGƒ‰[——RƒR[ƒh
        this.l_variousInformParams.setErrorReasonCode("abcd");

        // “`•[¯•ÊƒR[ƒh
        this.l_variousInformParams.setOrderRequestNumber("987987987");

        // ƒf[ƒ^ƒR[ƒh
        this.l_variousInformParams.setRequestCode("55555");

        l_date = WEB3DateUtility.getDate("20070609","yyyyMMdd");
        l_date = WEB3DateUtility.addHour(l_date, 15);
        l_date = WEB3DateUtility.addMinute(l_date, 3);
        l_date = WEB3DateUtility.addSecond(l_date, 24);

        // “`•[‘—M“ú
        this.l_variousInformParams.setSendTimestamp(l_date);

        l_date = WEB3DateUtility.getDate("20070610","yyyyMMdd");
        l_date = WEB3DateUtility.addHour(l_date, 8);
        l_date = WEB3DateUtility.addMinute(l_date, 59);
        l_date = WEB3DateUtility.addSecond(l_date, 59);

        // “`•[óM“ú
        this.l_variousInformParams.setReceiptTimestamp(l_date);

    }

    /**
    *
    *
    */
    protected void tearDown() throws Exception
    {
        super.tearDown();

        // Šeí˜A—s
        this.l_variousInformParams = null;

        // Šeí˜A—î•ñ
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

        // U‘Ö\i—X’™j“`•[
        WEB3AdminInformTransferApplyPostVoucher l_aminInformTransferApplyPostVoucher =
            new WEB3AdminInformTransferApplyPostVoucher(this.l_variousInformParams);

        try
        {
            Field l_field =
                WEB3AdminInformTransferApplyPostVoucher.class.getDeclaredField("variousInformParams");
            l_field.setAccessible(true);
            VariousInformParams l_reflectVariousInformParams =
                (VariousInformParams)l_field.get(l_aminInformTransferApplyPostVoucher);

            // ØŒ”‰ïĞƒR[ƒh
            assertEquals("987", l_reflectVariousInformParams.getInstitutionCode());

            // ˜A—í•Ê
            assertEquals("98", l_reflectVariousInformParams.getInformDiv());

            // ¯•ÊƒR[ƒh
            assertEquals("1234567890123", l_reflectVariousInformParams.getRequestNumber());

            // •”“XƒR[ƒh
            assertEquals("123", l_reflectVariousInformParams.getBranchCode());

            // ŒÚ‹qƒR[ƒh
            assertEquals("1234567", l_reflectVariousInformParams.getAccountCode());

            // ˆµÒƒR[ƒh
            assertEquals("12345", l_reflectVariousInformParams.getTraderCode());

            // ŒÚ‹q–¼
            assertEquals("ƒAƒCƒEƒGƒIƒJƒLƒNƒPƒRƒTƒVƒXƒZƒ\ƒ^ƒ`ƒcƒeƒg",
                l_reflectVariousInformParams.getAccountName());

            // ŒÚ‹qƒ[ƒ‹ƒAƒhƒŒƒX
            assertEquals("ƒiƒjƒkƒlƒmƒnƒqƒtƒwƒzƒ}ƒ~ƒ€ƒƒ‚ƒ„ƒ†ƒˆƒ‰ƒŠƒ‹ƒŒƒƒƒ’",
                l_reflectVariousInformParams.getEmailAddress());

            // ‹æ•ª‚P
            assertEquals("11", l_reflectVariousInformParams.getExtDiv1());

            // ‹æ•ª‚Q
            assertEquals("2", l_reflectVariousInformParams.getExtDiv2());

            // ‹æ•ª‚R
            assertEquals("3", l_reflectVariousInformParams.getExtDiv3());

            // ‹æ•ª‚S
            assertEquals("4", l_reflectVariousInformParams.getExtDiv4());

            // ‹æ•ª‚T
            assertEquals("5", l_reflectVariousInformParams.getExtDiv5());

            // ‹æ•ª‚U
            assertEquals("6", l_reflectVariousInformParams.getExtDiv6());

            // ‹æ•ª‚V
            assertEquals("17", l_reflectVariousInformParams.getExtDiv7());

            // ‹æ•ª‚W
            assertEquals("18", l_reflectVariousInformParams.getExtDiv8());

            // ‹æ•ª‚X
            assertEquals("19", l_reflectVariousInformParams.getExtDiv9());

            // ‹æ•ª‚P‚O
            assertEquals("20", l_reflectVariousInformParams.getExtDiv10());

            // ‹æ•ª‚P‚P
            assertEquals("21", l_reflectVariousInformParams.getExtDiv11());

            // ‹æ•ª‚P‚Q
            assertEquals("22", l_reflectVariousInformParams.getExtDiv12());

            // ‹æ•ª‚P‚R
            assertEquals("23", l_reflectVariousInformParams.getExtDiv13());

            // ‹æ•ª‚P‚S
            assertEquals("24", l_reflectVariousInformParams.getExtDiv14());

            // ‹æ•ª‚P‚T
            assertEquals("25", l_reflectVariousInformParams.getExtDiv15());

            // ‹æ•ª‚P‚U
            assertEquals("26", l_reflectVariousInformParams.getExtDiv16());

            // ‹æ•ª‚P‚V
            assertEquals("27", l_reflectVariousInformParams.getExtDiv17());

            // ‹æ•ª‚P‚W
            assertEquals("28", l_reflectVariousInformParams.getExtDiv18());

            // ‹æ•ª‚P‚X
            assertEquals("29", l_reflectVariousInformParams.getExtDiv19());

            // ‹æ•ª‚Q‚O
            assertEquals("30", l_reflectVariousInformParams.getExtDiv20());

            // ‹æ•ª‚Q‚P
            assertEquals("31", l_reflectVariousInformParams.getExtDiv21());

            // ‹æ•ª‚Q‚Q
            assertEquals("32", l_reflectVariousInformParams.getExtDiv22());

            // ‹æ•ª‚Q‚R
            assertEquals("33", l_reflectVariousInformParams.getExtDiv23());

            // ‹æ•ª‚Q‚S
            assertEquals("34", l_reflectVariousInformParams.getExtDiv24());

            // ‹æ•ª‚Q‚T
            assertEquals("35", l_reflectVariousInformParams.getExtDiv25());

            // ‹æ•ª‚Q‚U
            assertEquals("36", l_reflectVariousInformParams.getExtDiv26());

            // ‹æ•ª‚Q‚V
            assertEquals("37", l_reflectVariousInformParams.getExtDiv27());

            // ‹æ•ª‚Q‚W
            assertEquals("38", l_reflectVariousInformParams.getExtDiv28());

            // ‹æ•ª‚Q‚X
            assertEquals("39", l_reflectVariousInformParams.getExtDiv29());

            // ‹æ•ª‚R‚O
            assertEquals("40", l_reflectVariousInformParams.getExtDiv30());

            // ‹æ•ª‚R‚P
            assertEquals("41", l_reflectVariousInformParams.getExtDiv31());

            // ‹æ•ª‚R‚Q
            assertEquals("42", l_reflectVariousInformParams.getExtDiv32());

            // ‹æ•ª‚R‚R
            assertEquals("43", l_reflectVariousInformParams.getExtDiv33());

            // ‹æ•ª‚R‚S
            assertEquals("44", l_reflectVariousInformParams.getExtDiv34());

            // ‹æ•ª‚R‚T
            assertEquals("45", l_reflectVariousInformParams.getExtDiv35());

            // ‹æ•ª‚R‚U
            assertEquals("46", l_reflectVariousInformParams.getExtDiv36());

            // ‹æ•ª‚R‚V
            assertEquals("47", l_reflectVariousInformParams.getExtDiv37());

            // ‹æ•ª‚R‚W
            assertEquals("48", l_reflectVariousInformParams.getExtDiv38());

            // ‹æ•ª‚R‚X
            assertEquals("49", l_reflectVariousInformParams.getExtDiv39());

            // ‹æ•ª‚S‚O
            assertEquals("50", l_reflectVariousInformParams.getExtDiv40());

            // ƒR[ƒh‚P
            assertEquals("51", l_reflectVariousInformParams.getExtCode1());

            // ƒR[ƒh‚Q
            assertEquals("52", l_reflectVariousInformParams.getExtCode2());

            // ƒR[ƒh‚R
            assertEquals("53", l_reflectVariousInformParams.getExtCode3());

            // ƒR[ƒh‚S
            assertEquals("54", l_reflectVariousInformParams.getExtCode4());

            // ƒR[ƒh‚T
            assertEquals("55", l_reflectVariousInformParams.getExtCode5());

            // ƒR[ƒh‚U
            assertEquals("56", l_reflectVariousInformParams.getExtCode6());

            // ƒR[ƒh‚V
            assertEquals("57", l_reflectVariousInformParams.getExtCode7());

            // ƒR[ƒh‚W
            assertEquals("58", l_reflectVariousInformParams.getExtCode8());

            // ƒR[ƒh‚X
            assertEquals("59", l_reflectVariousInformParams.getExtCode9());

            // ƒR[ƒh‚P‚O
            assertEquals("60", l_reflectVariousInformParams.getExtCode10());

            // ƒeƒLƒXƒg‚P
            assertEquals("61", l_reflectVariousInformParams.getExtText1());

            // ƒeƒLƒXƒg‚Q
            assertEquals("62", l_reflectVariousInformParams.getExtText2());

            // ƒeƒLƒXƒg‚R
            assertEquals("63", l_reflectVariousInformParams.getExtText3());

            // ƒeƒLƒXƒg‚S
            assertEquals("64", l_reflectVariousInformParams.getExtText4());

            // ƒeƒLƒXƒg‚T
            assertEquals("65", l_reflectVariousInformParams.getExtText5());

            // ƒeƒLƒXƒg‚U
            assertEquals("66", l_reflectVariousInformParams.getExtText6());

            // ƒeƒLƒXƒg‚V
            assertEquals("67", l_reflectVariousInformParams.getExtText7());

            // ƒeƒLƒXƒg‚W
            assertEquals("68", l_reflectVariousInformParams.getExtText8());

            // ƒeƒLƒXƒg‚X
            assertEquals("69", l_reflectVariousInformParams.getExtText9());

            // ƒeƒLƒXƒg‚P‚O
            assertEquals("70", l_reflectVariousInformParams.getExtText10());

            // ƒeƒLƒXƒg‚P‚P
            assertEquals("71", l_reflectVariousInformParams.getExtText11());

            // ƒeƒLƒXƒg‚P‚Q
            assertEquals("72", l_reflectVariousInformParams.getExtText12());

            // ƒeƒLƒXƒg‚P‚R
            assertEquals("73", l_reflectVariousInformParams.getExtText13());

            // ƒeƒLƒXƒg‚P‚S
            assertEquals("74", l_reflectVariousInformParams.getExtText14());

            // ƒeƒLƒXƒg‚P‚T
            assertEquals("75", l_reflectVariousInformParams.getExtText15());

            // ƒeƒLƒXƒg‚P‚U
            assertEquals("76", l_reflectVariousInformParams.getExtText16());

            // ƒeƒLƒXƒg‚P‚V
            assertEquals("77", l_reflectVariousInformParams.getExtText17());

            // ƒeƒLƒXƒg‚P‚W
            assertEquals("78", l_reflectVariousInformParams.getExtText18());

            // ƒeƒLƒXƒg‚P‚X
            assertEquals("79", l_reflectVariousInformParams.getExtText19());

            // ƒeƒLƒXƒg‚Q‚O
            assertEquals("80", l_reflectVariousInformParams.getExtText20());

            // ƒeƒLƒXƒg‚Q‚P
            assertEquals("81", l_reflectVariousInformParams.getExtText21());

            // ƒeƒLƒXƒg‚Q‚Q
            assertEquals("82", l_reflectVariousInformParams.getExtText22());

            // ƒeƒLƒXƒg‚Q‚R
            assertEquals("83", l_reflectVariousInformParams.getExtText23());

            // ƒeƒLƒXƒg‚Q‚S
            assertEquals("84", l_reflectVariousInformParams.getExtText24());

            // ƒeƒLƒXƒg‚Q‚T
            assertEquals("85", l_reflectVariousInformParams.getExtText25());

            // ƒeƒLƒXƒg‚Q‚U
            assertEquals("86", l_reflectVariousInformParams.getExtText26());

            // ƒeƒLƒXƒg‚Q‚V
            assertEquals("87", l_reflectVariousInformParams.getExtText27());

            // ƒeƒLƒXƒg‚Q‚W
            assertEquals("88", l_reflectVariousInformParams.getExtText28());

            // ƒeƒLƒXƒg‚Q‚X
            assertEquals("89", l_reflectVariousInformParams.getExtText29());

            // ƒeƒLƒXƒg‚R‚O
            assertEquals("90", l_reflectVariousInformParams.getExtText30());

            // ƒeƒLƒXƒg‚R‚P
            assertEquals("91", l_reflectVariousInformParams.getExtText31());

            // ƒeƒLƒXƒg‚R‚Q
            assertEquals("92", l_reflectVariousInformParams.getExtText32());

            // ƒeƒLƒXƒg‚R‚R
            assertEquals("93", l_reflectVariousInformParams.getExtText33());

            // ƒeƒLƒXƒg‚R‚S
            assertEquals("94", l_reflectVariousInformParams.getExtText34());

            // ƒeƒLƒXƒg‚R‚T
            assertEquals("95", l_reflectVariousInformParams.getExtText35());

            // ƒeƒLƒXƒg‚R‚U
            assertEquals("96", l_reflectVariousInformParams.getExtText36());

            // ƒeƒLƒXƒg‚R‚V
            assertEquals("97", l_reflectVariousInformParams.getExtText37());

            // ƒeƒLƒXƒg‚R‚W
            assertEquals("98", l_reflectVariousInformParams.getExtText38());

            // ƒeƒLƒXƒg‚R‚X
            assertEquals("99", l_reflectVariousInformParams.getExtText39());

            // ƒeƒLƒXƒg‚S‚O
            assertEquals("A0", l_reflectVariousInformParams.getExtText40());

            // ”’l‚P
            assertEquals(101, l_reflectVariousInformParams.getExtValue1());

            // ”’l‚Q
            assertEquals(102, l_reflectVariousInformParams.getExtValue2());

            // ”’l‚R
            assertEquals(103, l_reflectVariousInformParams.getExtValue3());

            // ”’l‚S
            assertEquals(104, l_reflectVariousInformParams.getExtValue4());

            // ”’l‚T
            assertEquals(105, l_reflectVariousInformParams.getExtValue5());

            // ”’l‚U
            assertEquals(106, l_reflectVariousInformParams.getExtValue6());

            // ”’l‚V
            assertEquals(107, l_reflectVariousInformParams.getExtValue7());

            // ”’l‚W
            assertEquals(108, l_reflectVariousInformParams.getExtValue8());

            // ”’l‚X
            assertEquals(109, l_reflectVariousInformParams.getExtValue9());

            // ”’l‚P‚O
            assertEquals(110, l_reflectVariousInformParams.getExtValue10());

            // ”’l‚P‚P
            assertEquals(111, l_reflectVariousInformParams.getExtValue11());

            // ”’l‚P‚Q
            assertEquals(112, l_reflectVariousInformParams.getExtValue12());

            // ”’l‚P‚R
            assertEquals(113, l_reflectVariousInformParams.getExtValue13());

            // ”’l‚P‚S
            assertEquals(114, l_reflectVariousInformParams.getExtValue14());

            // ”’l‚P‚T
            assertEquals(115, l_reflectVariousInformParams.getExtValue15());

            // ”’l‚P‚U
            assertEquals(116, l_reflectVariousInformParams.getExtValue16());

            // ”’l‚P‚V
            assertEquals(117, l_reflectVariousInformParams.getExtValue17());

            // ”’l‚P‚W
            assertEquals(118, l_reflectVariousInformParams.getExtValue18());

            // ”’l‚P‚X
            assertEquals(119, l_reflectVariousInformParams.getExtValue19());

            // ”’l‚Q‚O
            assertEquals(120, l_reflectVariousInformParams.getExtValue20());

            // ”’l‚Q‚P
            assertEquals(121, l_reflectVariousInformParams.getExtValue21());

            // ”’l‚Q‚Q
            assertEquals(122, l_reflectVariousInformParams.getExtValue22());

            // ”’l‚Q‚R
            assertEquals(123, l_reflectVariousInformParams.getExtValue23());

            // ”’l‚Q‚S
            assertEquals(124, l_reflectVariousInformParams.getExtValue24());

            // ”’l‚Q‚T
            assertEquals(125, l_reflectVariousInformParams.getExtValue25());

            // ”’l‚Q‚U
            assertEquals(126, l_reflectVariousInformParams.getExtValue26());

            // ”’l‚Q‚V
            assertEquals(127, l_reflectVariousInformParams.getExtValue27());

            // ”’l‚Q‚W
            assertEquals(128, l_reflectVariousInformParams.getExtValue28());

            // ”’l‚Q‚X
            assertEquals(129, l_reflectVariousInformParams.getExtValue29());

            // ”’l‚R‚O
            assertEquals(130, l_reflectVariousInformParams.getExtValue30());

            // ”õl‚P
            assertEquals("note1", l_reflectVariousInformParams.getExtNote1());

            // ”õl‚Q
            assertEquals("note2", l_reflectVariousInformParams.getExtNote2());

            // XVÒƒR[ƒh
            assertEquals("abcdefghijklmnopqrst", l_reflectVariousInformParams.getLastUpdater());

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070607","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            // ì¬“ú
            assertEquals(l_date, l_reflectVariousInformParams.getCreatedTimestamp());

            l_date = WEB3DateUtility.getDate("20070608","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 21);
            l_date = WEB3DateUtility.addMinute(l_date, 2);
            l_date = WEB3DateUtility.addSecond(l_date, 43);

            // XV“ú
            assertEquals(l_date, l_reflectVariousInformParams.getLastUpdatedTimestamp());

            // –Á•¿ƒR[ƒh
            assertEquals("123123123", l_reflectVariousInformParams.getFundCode());

            // ˆµÒƒR[ƒhiSONARj
            assertEquals("67890", l_reflectVariousInformParams.getSonarTraderCode());

            // “`•[ì¬ó‹µ
            assertEquals("1", l_reflectVariousInformParams.getStatus());

            // ƒGƒ‰[——RƒR[ƒh
            assertEquals("abcd", l_reflectVariousInformParams.getErrorReasonCode());

            // “`•[¯•ÊƒR[ƒh
            assertEquals("987987987", l_reflectVariousInformParams.getOrderRequestNumber());

            // ƒf[ƒ^ƒR[ƒh
            assertEquals("55555", l_reflectVariousInformParams.getRequestCode());

            l_date = WEB3DateUtility.getDate("20070609","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 15);
            l_date = WEB3DateUtility.addMinute(l_date, 3);
            l_date = WEB3DateUtility.addSecond(l_date, 24);

            // “`•[‘—M“ú
            assertEquals(l_date, l_reflectVariousInformParams.getSendTimestamp());

            l_date = WEB3DateUtility.getDate("20070610","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 8);
            l_date = WEB3DateUtility.addMinute(l_date, 59);
            l_date = WEB3DateUtility.addSecond(l_date, 59);

            // “`•[óM“ú
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

        // “`•[¯•ÊƒR[ƒh
        String l_strVoucherRequestNumber = "888";

        // Šeí˜A—¯•ÊƒR[ƒh
        String l_strInformCtrlRequestNumber = "999";

        // U‘Ö\i—X’™j“`•[
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

            // ØŒ”‰ïĞƒR[ƒh
            assertEquals("987", l_reflectVariousInformParams.getInstitutionCode());

            // ˜A—í•Ê
            assertEquals("98", l_reflectVariousInformParams.getInformDiv());

            // ¯•ÊƒR[ƒh
            assertEquals("999", l_reflectVariousInformParams.getRequestNumber());

            // •”“XƒR[ƒh
            assertEquals("123", l_reflectVariousInformParams.getBranchCode());

            // ŒÚ‹qƒR[ƒh
            assertEquals("1234567", l_reflectVariousInformParams.getAccountCode());

            // ˆµÒƒR[ƒh
            assertEquals("12345", l_reflectVariousInformParams.getTraderCode());

            // ŒÚ‹q–¼
            assertEquals("ƒAƒCƒEƒGƒIƒJƒLƒNƒPƒRƒTƒVƒXƒZƒ\ƒ^ƒ`ƒcƒeƒg",
                l_reflectVariousInformParams.getAccountName());

            // ŒÚ‹qƒ[ƒ‹ƒAƒhƒŒƒX
            assertEquals("ƒiƒjƒkƒlƒmƒnƒqƒtƒwƒzƒ}ƒ~ƒ€ƒƒ‚ƒ„ƒ†ƒˆƒ‰ƒŠƒ‹ƒŒƒƒƒ’",
                l_reflectVariousInformParams.getEmailAddress());

            // ‹æ•ª‚P
            assertEquals("11", l_reflectVariousInformParams.getExtDiv1());

            // ‹æ•ª‚Q
            assertEquals("2", l_reflectVariousInformParams.getExtDiv2());

            // ‹æ•ª‚R
            assertEquals("3", l_reflectVariousInformParams.getExtDiv3());

            // ‹æ•ª‚S
            assertEquals("4", l_reflectVariousInformParams.getExtDiv4());

            // ‹æ•ª‚T
            assertEquals("5", l_reflectVariousInformParams.getExtDiv5());

            // ‹æ•ª‚U
            assertEquals("6", l_reflectVariousInformParams.getExtDiv6());

            // ‹æ•ª‚V
            assertEquals("17", l_reflectVariousInformParams.getExtDiv7());

            // ‹æ•ª‚W
            assertEquals("18", l_reflectVariousInformParams.getExtDiv8());

            // ‹æ•ª‚X
            assertEquals("19", l_reflectVariousInformParams.getExtDiv9());

            // ‹æ•ª‚P‚O
            assertEquals("20", l_reflectVariousInformParams.getExtDiv10());

            // ‹æ•ª‚P‚P
            assertEquals("21", l_reflectVariousInformParams.getExtDiv11());

            // ‹æ•ª‚P‚Q
            assertEquals("22", l_reflectVariousInformParams.getExtDiv12());

            // ‹æ•ª‚P‚R
            assertEquals("23", l_reflectVariousInformParams.getExtDiv13());

            // ‹æ•ª‚P‚S
            assertEquals("24", l_reflectVariousInformParams.getExtDiv14());

            // ‹æ•ª‚P‚T
            assertEquals("25", l_reflectVariousInformParams.getExtDiv15());

            // ‹æ•ª‚P‚U
            assertEquals("26", l_reflectVariousInformParams.getExtDiv16());

            // ‹æ•ª‚P‚V
            assertEquals("27", l_reflectVariousInformParams.getExtDiv17());

            // ‹æ•ª‚P‚W
            assertEquals("28", l_reflectVariousInformParams.getExtDiv18());

            // ‹æ•ª‚P‚X
            assertEquals("29", l_reflectVariousInformParams.getExtDiv19());

            // ‹æ•ª‚Q‚O
            assertEquals("30", l_reflectVariousInformParams.getExtDiv20());

            // ‹æ•ª‚Q‚P
            assertEquals("31", l_reflectVariousInformParams.getExtDiv21());

            // ‹æ•ª‚Q‚Q
            assertEquals("32", l_reflectVariousInformParams.getExtDiv22());

            // ‹æ•ª‚Q‚R
            assertEquals("33", l_reflectVariousInformParams.getExtDiv23());

            // ‹æ•ª‚Q‚S
            assertEquals("34", l_reflectVariousInformParams.getExtDiv24());

            // ‹æ•ª‚Q‚T
            assertEquals("35", l_reflectVariousInformParams.getExtDiv25());

            // ‹æ•ª‚Q‚U
            assertEquals("36", l_reflectVariousInformParams.getExtDiv26());

            // ‹æ•ª‚Q‚V
            assertEquals("37", l_reflectVariousInformParams.getExtDiv27());

            // ‹æ•ª‚Q‚W
            assertEquals("38", l_reflectVariousInformParams.getExtDiv28());

            // ‹æ•ª‚Q‚X
            assertEquals("39", l_reflectVariousInformParams.getExtDiv29());

            // ‹æ•ª‚R‚O
            assertEquals("40", l_reflectVariousInformParams.getExtDiv30());

            // ‹æ•ª‚R‚P
            assertEquals("41", l_reflectVariousInformParams.getExtDiv31());

            // ‹æ•ª‚R‚Q
            assertEquals("42", l_reflectVariousInformParams.getExtDiv32());

            // ‹æ•ª‚R‚R
            assertEquals("43", l_reflectVariousInformParams.getExtDiv33());

            // ‹æ•ª‚R‚S
            assertEquals("44", l_reflectVariousInformParams.getExtDiv34());

            // ‹æ•ª‚R‚T
            assertEquals("45", l_reflectVariousInformParams.getExtDiv35());

            // ‹æ•ª‚R‚U
            assertEquals("46", l_reflectVariousInformParams.getExtDiv36());

            // ‹æ•ª‚R‚V
            assertEquals("47", l_reflectVariousInformParams.getExtDiv37());

            // ‹æ•ª‚R‚W
            assertEquals("48", l_reflectVariousInformParams.getExtDiv38());

            // ‹æ•ª‚R‚X
            assertEquals("49", l_reflectVariousInformParams.getExtDiv39());

            // ‹æ•ª‚S‚O
            assertEquals("50", l_reflectVariousInformParams.getExtDiv40());

            // ƒR[ƒh‚P
            assertEquals("51", l_reflectVariousInformParams.getExtCode1());

            // ƒR[ƒh‚Q
            assertEquals("52", l_reflectVariousInformParams.getExtCode2());

            // ƒR[ƒh‚R
            assertEquals("53", l_reflectVariousInformParams.getExtCode3());

            // ƒR[ƒh‚S
            assertEquals("54", l_reflectVariousInformParams.getExtCode4());

            // ƒR[ƒh‚T
            assertEquals("55", l_reflectVariousInformParams.getExtCode5());

            // ƒR[ƒh‚U
            assertEquals("56", l_reflectVariousInformParams.getExtCode6());

            // ƒR[ƒh‚V
            assertEquals("57", l_reflectVariousInformParams.getExtCode7());

            // ƒR[ƒh‚W
            assertEquals("58", l_reflectVariousInformParams.getExtCode8());

            // ƒR[ƒh‚X
            assertEquals("59", l_reflectVariousInformParams.getExtCode9());

            // ƒR[ƒh‚P‚O
            assertEquals("60", l_reflectVariousInformParams.getExtCode10());

            // ƒeƒLƒXƒg‚P
            assertEquals("61", l_reflectVariousInformParams.getExtText1());

            // ƒeƒLƒXƒg‚Q
            assertEquals("62", l_reflectVariousInformParams.getExtText2());

            // ƒeƒLƒXƒg‚R
            assertEquals("63", l_reflectVariousInformParams.getExtText3());

            // ƒeƒLƒXƒg‚S
            assertEquals("64", l_reflectVariousInformParams.getExtText4());

            // ƒeƒLƒXƒg‚T
            assertEquals("65", l_reflectVariousInformParams.getExtText5());

            // ƒeƒLƒXƒg‚U
            assertEquals("66", l_reflectVariousInformParams.getExtText6());

            // ƒeƒLƒXƒg‚V
            assertEquals("67", l_reflectVariousInformParams.getExtText7());

            // ƒeƒLƒXƒg‚W
            assertEquals("68", l_reflectVariousInformParams.getExtText8());

            // ƒeƒLƒXƒg‚X
            assertEquals("69", l_reflectVariousInformParams.getExtText9());

            // ƒeƒLƒXƒg‚P‚O
            assertEquals("70", l_reflectVariousInformParams.getExtText10());

            // ƒeƒLƒXƒg‚P‚P
            assertEquals("71", l_reflectVariousInformParams.getExtText11());

            // ƒeƒLƒXƒg‚P‚Q
            assertEquals("72", l_reflectVariousInformParams.getExtText12());

            // ƒeƒLƒXƒg‚P‚R
            assertEquals("73", l_reflectVariousInformParams.getExtText13());

            // ƒeƒLƒXƒg‚P‚S
            assertEquals("74", l_reflectVariousInformParams.getExtText14());

            // ƒeƒLƒXƒg‚P‚T
            assertEquals("75", l_reflectVariousInformParams.getExtText15());

            // ƒeƒLƒXƒg‚P‚U
            assertEquals("76", l_reflectVariousInformParams.getExtText16());

            // ƒeƒLƒXƒg‚P‚V
            assertEquals("77", l_reflectVariousInformParams.getExtText17());

            // ƒeƒLƒXƒg‚P‚W
            assertEquals("78", l_reflectVariousInformParams.getExtText18());

            // ƒeƒLƒXƒg‚P‚X
            assertEquals("79", l_reflectVariousInformParams.getExtText19());

            // ƒeƒLƒXƒg‚Q‚O
            assertEquals("80", l_reflectVariousInformParams.getExtText20());

            // ƒeƒLƒXƒg‚Q‚P
            assertEquals("81", l_reflectVariousInformParams.getExtText21());

            // ƒeƒLƒXƒg‚Q‚Q
            assertEquals("82", l_reflectVariousInformParams.getExtText22());

            // ƒeƒLƒXƒg‚Q‚R
            assertEquals("83", l_reflectVariousInformParams.getExtText23());

            // ƒeƒLƒXƒg‚Q‚S
            assertEquals("84", l_reflectVariousInformParams.getExtText24());

            // ƒeƒLƒXƒg‚Q‚T
            assertEquals("85", l_reflectVariousInformParams.getExtText25());

            // ƒeƒLƒXƒg‚Q‚U
            assertEquals("86", l_reflectVariousInformParams.getExtText26());

            // ƒeƒLƒXƒg‚Q‚V
            assertEquals("87", l_reflectVariousInformParams.getExtText27());

            // ƒeƒLƒXƒg‚Q‚W
            assertEquals("88", l_reflectVariousInformParams.getExtText28());

            // ƒeƒLƒXƒg‚Q‚X
            assertEquals("89", l_reflectVariousInformParams.getExtText29());

            // ƒeƒLƒXƒg‚R‚O
            assertEquals("90", l_reflectVariousInformParams.getExtText30());

            // ƒeƒLƒXƒg‚R‚P
            assertEquals("91", l_reflectVariousInformParams.getExtText31());

            // ƒeƒLƒXƒg‚R‚Q
            assertEquals("92", l_reflectVariousInformParams.getExtText32());

            // ƒeƒLƒXƒg‚R‚R
            assertEquals("93", l_reflectVariousInformParams.getExtText33());

            // ƒeƒLƒXƒg‚R‚S
            assertEquals("94", l_reflectVariousInformParams.getExtText34());

            // ƒeƒLƒXƒg‚R‚T
            assertEquals("95", l_reflectVariousInformParams.getExtText35());

            // ƒeƒLƒXƒg‚R‚U
            assertEquals("96", l_reflectVariousInformParams.getExtText36());

            // ƒeƒLƒXƒg‚R‚V
            assertEquals("97", l_reflectVariousInformParams.getExtText37());

            // ƒeƒLƒXƒg‚R‚W
            assertEquals("98", l_reflectVariousInformParams.getExtText38());

            // ƒeƒLƒXƒg‚R‚X
            assertEquals("99", l_reflectVariousInformParams.getExtText39());

            // ƒeƒLƒXƒg‚S‚O
            assertEquals("A0", l_reflectVariousInformParams.getExtText40());

            // ”’l‚P
            assertEquals(101, l_reflectVariousInformParams.getExtValue1());

            // ”’l‚Q
            assertEquals(102, l_reflectVariousInformParams.getExtValue2());

            // ”’l‚R
            assertEquals(103, l_reflectVariousInformParams.getExtValue3());

            // ”’l‚S
            assertEquals(104, l_reflectVariousInformParams.getExtValue4());

            // ”’l‚T
            assertEquals(105, l_reflectVariousInformParams.getExtValue5());

            // ”’l‚U
            assertEquals(106, l_reflectVariousInformParams.getExtValue6());

            // ”’l‚V
            assertEquals(107, l_reflectVariousInformParams.getExtValue7());

            // ”’l‚W
            assertEquals(108, l_reflectVariousInformParams.getExtValue8());

            // ”’l‚X
            assertEquals(109, l_reflectVariousInformParams.getExtValue9());

            // ”’l‚P‚O
            assertEquals(110, l_reflectVariousInformParams.getExtValue10());

            // ”’l‚P‚P
            assertEquals(111, l_reflectVariousInformParams.getExtValue11());

            // ”’l‚P‚Q
            assertEquals(112, l_reflectVariousInformParams.getExtValue12());

            // ”’l‚P‚R
            assertEquals(113, l_reflectVariousInformParams.getExtValue13());

            // ”’l‚P‚S
            assertEquals(114, l_reflectVariousInformParams.getExtValue14());

            // ”’l‚P‚T
            assertEquals(115, l_reflectVariousInformParams.getExtValue15());

            // ”’l‚P‚U
            assertEquals(116, l_reflectVariousInformParams.getExtValue16());

            // ”’l‚P‚V
            assertEquals(117, l_reflectVariousInformParams.getExtValue17());

            // ”’l‚P‚W
            assertEquals(118, l_reflectVariousInformParams.getExtValue18());

            // ”’l‚P‚X
            assertEquals(119, l_reflectVariousInformParams.getExtValue19());

            // ”’l‚Q‚O
            assertEquals(120, l_reflectVariousInformParams.getExtValue20());

            // ”’l‚Q‚P
            assertEquals(121, l_reflectVariousInformParams.getExtValue21());

            // ”’l‚Q‚Q
            assertEquals(122, l_reflectVariousInformParams.getExtValue22());

            // ”’l‚Q‚R
            assertEquals(123, l_reflectVariousInformParams.getExtValue23());

            // ”’l‚Q‚S
            assertEquals(124, l_reflectVariousInformParams.getExtValue24());

            // ”’l‚Q‚T
            assertEquals(125, l_reflectVariousInformParams.getExtValue25());

            // ”’l‚Q‚U
            assertEquals(126, l_reflectVariousInformParams.getExtValue26());

            // ”’l‚Q‚V
            assertEquals(127, l_reflectVariousInformParams.getExtValue27());

            // ”’l‚Q‚W
            assertEquals(128, l_reflectVariousInformParams.getExtValue28());

            // ”’l‚Q‚X
            assertEquals(129, l_reflectVariousInformParams.getExtValue29());

            // ”’l‚R‚O
            assertEquals(130, l_reflectVariousInformParams.getExtValue30());

            // ”õl‚P
            assertEquals("note1", l_reflectVariousInformParams.getExtNote1());

            // ”õl‚Q
            assertEquals("note2", l_reflectVariousInformParams.getExtNote2());

            // XVÒƒR[ƒh
            assertEquals("abcdefghijklmnopqrst", l_reflectVariousInformParams.getLastUpdater());

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070607","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            // ì¬“ú
            assertEquals(l_date, l_reflectVariousInformParams.getCreatedTimestamp());

            l_date = WEB3DateUtility.getDate("20070608","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 21);
            l_date = WEB3DateUtility.addMinute(l_date, 2);
            l_date = WEB3DateUtility.addSecond(l_date, 43);

            // XV“ú
            assertEquals(l_date, l_reflectVariousInformParams.getLastUpdatedTimestamp());

            // –Á•¿ƒR[ƒh
            assertEquals("123123123", l_reflectVariousInformParams.getFundCode());

            // ˆµÒƒR[ƒhiSONARj
            assertEquals("67890", l_reflectVariousInformParams.getSonarTraderCode());

            // “`•[ì¬ó‹µ
            assertEquals("1", l_reflectVariousInformParams.getStatus());

            // ƒGƒ‰[——RƒR[ƒh
            assertEquals("abcd", l_reflectVariousInformParams.getErrorReasonCode());

            // “`•[¯•ÊƒR[ƒh
            assertEquals("888", l_reflectVariousInformParams.getOrderRequestNumber());

            // ƒf[ƒ^ƒR[ƒh
            assertEquals("55555", l_reflectVariousInformParams.getRequestCode());

            l_date = WEB3DateUtility.getDate("20070609","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 15);
            l_date = WEB3DateUtility.addMinute(l_date, 3);
            l_date = WEB3DateUtility.addSecond(l_date, 24);

            // “`•[‘—M“ú
            assertEquals(l_date, l_reflectVariousInformParams.getSendTimestamp());

            l_date = WEB3DateUtility.getDate("20070610","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 8);
            l_date = WEB3DateUtility.addMinute(l_date, 59);
            l_date = WEB3DateUtility.addSecond(l_date, 59);

            // “`•[óM“ú
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

        // “`•[¯•ÊƒR[ƒh
        String l_strVoucherRequestNumber = null;

        // Šeí˜A—¯•ÊƒR[ƒh
        String l_strInformCtrlRequestNumber = null;

        // U‘Ö\i—X’™j“`•[
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

            // ØŒ”‰ïĞƒR[ƒh
            assertEquals("987", l_reflectVariousInformParams.getInstitutionCode());

            // ˜A—í•Ê
            assertEquals("98", l_reflectVariousInformParams.getInformDiv());

            // ¯•ÊƒR[ƒh
            assertEquals("1234567890123", l_reflectVariousInformParams.getRequestNumber());

            // •”“XƒR[ƒh
            assertEquals("123", l_reflectVariousInformParams.getBranchCode());

            // ŒÚ‹qƒR[ƒh
            assertEquals("1234567", l_reflectVariousInformParams.getAccountCode());

            // ˆµÒƒR[ƒh
            assertEquals("12345", l_reflectVariousInformParams.getTraderCode());

            // ŒÚ‹q–¼
            assertEquals("ƒAƒCƒEƒGƒIƒJƒLƒNƒPƒRƒTƒVƒXƒZƒ\ƒ^ƒ`ƒcƒeƒg",
                l_reflectVariousInformParams.getAccountName());

            // ŒÚ‹qƒ[ƒ‹ƒAƒhƒŒƒX
            assertEquals("ƒiƒjƒkƒlƒmƒnƒqƒtƒwƒzƒ}ƒ~ƒ€ƒƒ‚ƒ„ƒ†ƒˆƒ‰ƒŠƒ‹ƒŒƒƒƒ’",
                l_reflectVariousInformParams.getEmailAddress());

            // ‹æ•ª‚P
            assertEquals("11", l_reflectVariousInformParams.getExtDiv1());

            // ‹æ•ª‚Q
            assertEquals("2", l_reflectVariousInformParams.getExtDiv2());

            // ‹æ•ª‚R
            assertEquals("3", l_reflectVariousInformParams.getExtDiv3());

            // ‹æ•ª‚S
            assertEquals("4", l_reflectVariousInformParams.getExtDiv4());

            // ‹æ•ª‚T
            assertEquals("5", l_reflectVariousInformParams.getExtDiv5());

            // ‹æ•ª‚U
            assertEquals("6", l_reflectVariousInformParams.getExtDiv6());

            // ‹æ•ª‚V
            assertEquals("17", l_reflectVariousInformParams.getExtDiv7());

            // ‹æ•ª‚W
            assertEquals("18", l_reflectVariousInformParams.getExtDiv8());

            // ‹æ•ª‚X
            assertEquals("19", l_reflectVariousInformParams.getExtDiv9());

            // ‹æ•ª‚P‚O
            assertEquals("20", l_reflectVariousInformParams.getExtDiv10());

            // ‹æ•ª‚P‚P
            assertEquals("21", l_reflectVariousInformParams.getExtDiv11());

            // ‹æ•ª‚P‚Q
            assertEquals("22", l_reflectVariousInformParams.getExtDiv12());

            // ‹æ•ª‚P‚R
            assertEquals("23", l_reflectVariousInformParams.getExtDiv13());

            // ‹æ•ª‚P‚S
            assertEquals("24", l_reflectVariousInformParams.getExtDiv14());

            // ‹æ•ª‚P‚T
            assertEquals("25", l_reflectVariousInformParams.getExtDiv15());

            // ‹æ•ª‚P‚U
            assertEquals("26", l_reflectVariousInformParams.getExtDiv16());

            // ‹æ•ª‚P‚V
            assertEquals("27", l_reflectVariousInformParams.getExtDiv17());

            // ‹æ•ª‚P‚W
            assertEquals("28", l_reflectVariousInformParams.getExtDiv18());

            // ‹æ•ª‚P‚X
            assertEquals("29", l_reflectVariousInformParams.getExtDiv19());

            // ‹æ•ª‚Q‚O
            assertEquals("30", l_reflectVariousInformParams.getExtDiv20());

            // ‹æ•ª‚Q‚P
            assertEquals("31", l_reflectVariousInformParams.getExtDiv21());

            // ‹æ•ª‚Q‚Q
            assertEquals("32", l_reflectVariousInformParams.getExtDiv22());

            // ‹æ•ª‚Q‚R
            assertEquals("33", l_reflectVariousInformParams.getExtDiv23());

            // ‹æ•ª‚Q‚S
            assertEquals("34", l_reflectVariousInformParams.getExtDiv24());

            // ‹æ•ª‚Q‚T
            assertEquals("35", l_reflectVariousInformParams.getExtDiv25());

            // ‹æ•ª‚Q‚U
            assertEquals("36", l_reflectVariousInformParams.getExtDiv26());

            // ‹æ•ª‚Q‚V
            assertEquals("37", l_reflectVariousInformParams.getExtDiv27());

            // ‹æ•ª‚Q‚W
            assertEquals("38", l_reflectVariousInformParams.getExtDiv28());

            // ‹æ•ª‚Q‚X
            assertEquals("39", l_reflectVariousInformParams.getExtDiv29());

            // ‹æ•ª‚R‚O
            assertEquals("40", l_reflectVariousInformParams.getExtDiv30());

            // ‹æ•ª‚R‚P
            assertEquals("41", l_reflectVariousInformParams.getExtDiv31());

            // ‹æ•ª‚R‚Q
            assertEquals("42", l_reflectVariousInformParams.getExtDiv32());

            // ‹æ•ª‚R‚R
            assertEquals("43", l_reflectVariousInformParams.getExtDiv33());

            // ‹æ•ª‚R‚S
            assertEquals("44", l_reflectVariousInformParams.getExtDiv34());

            // ‹æ•ª‚R‚T
            assertEquals("45", l_reflectVariousInformParams.getExtDiv35());

            // ‹æ•ª‚R‚U
            assertEquals("46", l_reflectVariousInformParams.getExtDiv36());

            // ‹æ•ª‚R‚V
            assertEquals("47", l_reflectVariousInformParams.getExtDiv37());

            // ‹æ•ª‚R‚W
            assertEquals("48", l_reflectVariousInformParams.getExtDiv38());

            // ‹æ•ª‚R‚X
            assertEquals("49", l_reflectVariousInformParams.getExtDiv39());

            // ‹æ•ª‚S‚O
            assertEquals("50", l_reflectVariousInformParams.getExtDiv40());

            // ƒR[ƒh‚P
            assertEquals("51", l_reflectVariousInformParams.getExtCode1());

            // ƒR[ƒh‚Q
            assertEquals("52", l_reflectVariousInformParams.getExtCode2());

            // ƒR[ƒh‚R
            assertEquals("53", l_reflectVariousInformParams.getExtCode3());

            // ƒR[ƒh‚S
            assertEquals("54", l_reflectVariousInformParams.getExtCode4());

            // ƒR[ƒh‚T
            assertEquals("55", l_reflectVariousInformParams.getExtCode5());

            // ƒR[ƒh‚U
            assertEquals("56", l_reflectVariousInformParams.getExtCode6());

            // ƒR[ƒh‚V
            assertEquals("57", l_reflectVariousInformParams.getExtCode7());

            // ƒR[ƒh‚W
            assertEquals("58", l_reflectVariousInformParams.getExtCode8());

            // ƒR[ƒh‚X
            assertEquals("59", l_reflectVariousInformParams.getExtCode9());

            // ƒR[ƒh‚P‚O
            assertEquals("60", l_reflectVariousInformParams.getExtCode10());

            // ƒeƒLƒXƒg‚P
            assertEquals("61", l_reflectVariousInformParams.getExtText1());

            // ƒeƒLƒXƒg‚Q
            assertEquals("62", l_reflectVariousInformParams.getExtText2());

            // ƒeƒLƒXƒg‚R
            assertEquals("63", l_reflectVariousInformParams.getExtText3());

            // ƒeƒLƒXƒg‚S
            assertEquals("64", l_reflectVariousInformParams.getExtText4());

            // ƒeƒLƒXƒg‚T
            assertEquals("65", l_reflectVariousInformParams.getExtText5());

            // ƒeƒLƒXƒg‚U
            assertEquals("66", l_reflectVariousInformParams.getExtText6());

            // ƒeƒLƒXƒg‚V
            assertEquals("67", l_reflectVariousInformParams.getExtText7());

            // ƒeƒLƒXƒg‚W
            assertEquals("68", l_reflectVariousInformParams.getExtText8());

            // ƒeƒLƒXƒg‚X
            assertEquals("69", l_reflectVariousInformParams.getExtText9());

            // ƒeƒLƒXƒg‚P‚O
            assertEquals("70", l_reflectVariousInformParams.getExtText10());

            // ƒeƒLƒXƒg‚P‚P
            assertEquals("71", l_reflectVariousInformParams.getExtText11());

            // ƒeƒLƒXƒg‚P‚Q
            assertEquals("72", l_reflectVariousInformParams.getExtText12());

            // ƒeƒLƒXƒg‚P‚R
            assertEquals("73", l_reflectVariousInformParams.getExtText13());

            // ƒeƒLƒXƒg‚P‚S
            assertEquals("74", l_reflectVariousInformParams.getExtText14());

            // ƒeƒLƒXƒg‚P‚T
            assertEquals("75", l_reflectVariousInformParams.getExtText15());

            // ƒeƒLƒXƒg‚P‚U
            assertEquals("76", l_reflectVariousInformParams.getExtText16());

            // ƒeƒLƒXƒg‚P‚V
            assertEquals("77", l_reflectVariousInformParams.getExtText17());

            // ƒeƒLƒXƒg‚P‚W
            assertEquals("78", l_reflectVariousInformParams.getExtText18());

            // ƒeƒLƒXƒg‚P‚X
            assertEquals("79", l_reflectVariousInformParams.getExtText19());

            // ƒeƒLƒXƒg‚Q‚O
            assertEquals("80", l_reflectVariousInformParams.getExtText20());

            // ƒeƒLƒXƒg‚Q‚P
            assertEquals("81", l_reflectVariousInformParams.getExtText21());

            // ƒeƒLƒXƒg‚Q‚Q
            assertEquals("82", l_reflectVariousInformParams.getExtText22());

            // ƒeƒLƒXƒg‚Q‚R
            assertEquals("83", l_reflectVariousInformParams.getExtText23());

            // ƒeƒLƒXƒg‚Q‚S
            assertEquals("84", l_reflectVariousInformParams.getExtText24());

            // ƒeƒLƒXƒg‚Q‚T
            assertEquals("85", l_reflectVariousInformParams.getExtText25());

            // ƒeƒLƒXƒg‚Q‚U
            assertEquals("86", l_reflectVariousInformParams.getExtText26());

            // ƒeƒLƒXƒg‚Q‚V
            assertEquals("87", l_reflectVariousInformParams.getExtText27());

            // ƒeƒLƒXƒg‚Q‚W
            assertEquals("88", l_reflectVariousInformParams.getExtText28());

            // ƒeƒLƒXƒg‚Q‚X
            assertEquals("89", l_reflectVariousInformParams.getExtText29());

            // ƒeƒLƒXƒg‚R‚O
            assertEquals("90", l_reflectVariousInformParams.getExtText30());

            // ƒeƒLƒXƒg‚R‚P
            assertEquals("91", l_reflectVariousInformParams.getExtText31());

            // ƒeƒLƒXƒg‚R‚Q
            assertEquals("92", l_reflectVariousInformParams.getExtText32());

            // ƒeƒLƒXƒg‚R‚R
            assertEquals("93", l_reflectVariousInformParams.getExtText33());

            // ƒeƒLƒXƒg‚R‚S
            assertEquals("94", l_reflectVariousInformParams.getExtText34());

            // ƒeƒLƒXƒg‚R‚T
            assertEquals("95", l_reflectVariousInformParams.getExtText35());

            // ƒeƒLƒXƒg‚R‚U
            assertEquals("96", l_reflectVariousInformParams.getExtText36());

            // ƒeƒLƒXƒg‚R‚V
            assertEquals("97", l_reflectVariousInformParams.getExtText37());

            // ƒeƒLƒXƒg‚R‚W
            assertEquals("98", l_reflectVariousInformParams.getExtText38());

            // ƒeƒLƒXƒg‚R‚X
            assertEquals("99", l_reflectVariousInformParams.getExtText39());

            // ƒeƒLƒXƒg‚S‚O
            assertEquals("A0", l_reflectVariousInformParams.getExtText40());

            // ”’l‚P
            assertEquals(101, l_reflectVariousInformParams.getExtValue1());

            // ”’l‚Q
            assertEquals(102, l_reflectVariousInformParams.getExtValue2());

            // ”’l‚R
            assertEquals(103, l_reflectVariousInformParams.getExtValue3());

            // ”’l‚S
            assertEquals(104, l_reflectVariousInformParams.getExtValue4());

            // ”’l‚T
            assertEquals(105, l_reflectVariousInformParams.getExtValue5());

            // ”’l‚U
            assertEquals(106, l_reflectVariousInformParams.getExtValue6());

            // ”’l‚V
            assertEquals(107, l_reflectVariousInformParams.getExtValue7());

            // ”’l‚W
            assertEquals(108, l_reflectVariousInformParams.getExtValue8());

            // ”’l‚X
            assertEquals(109, l_reflectVariousInformParams.getExtValue9());

            // ”’l‚P‚O
            assertEquals(110, l_reflectVariousInformParams.getExtValue10());

            // ”’l‚P‚P
            assertEquals(111, l_reflectVariousInformParams.getExtValue11());

            // ”’l‚P‚Q
            assertEquals(112, l_reflectVariousInformParams.getExtValue12());

            // ”’l‚P‚R
            assertEquals(113, l_reflectVariousInformParams.getExtValue13());

            // ”’l‚P‚S
            assertEquals(114, l_reflectVariousInformParams.getExtValue14());

            // ”’l‚P‚T
            assertEquals(115, l_reflectVariousInformParams.getExtValue15());

            // ”’l‚P‚U
            assertEquals(116, l_reflectVariousInformParams.getExtValue16());

            // ”’l‚P‚V
            assertEquals(117, l_reflectVariousInformParams.getExtValue17());

            // ”’l‚P‚W
            assertEquals(118, l_reflectVariousInformParams.getExtValue18());

            // ”’l‚P‚X
            assertEquals(119, l_reflectVariousInformParams.getExtValue19());

            // ”’l‚Q‚O
            assertEquals(120, l_reflectVariousInformParams.getExtValue20());

            // ”’l‚Q‚P
            assertEquals(121, l_reflectVariousInformParams.getExtValue21());

            // ”’l‚Q‚Q
            assertEquals(122, l_reflectVariousInformParams.getExtValue22());

            // ”’l‚Q‚R
            assertEquals(123, l_reflectVariousInformParams.getExtValue23());

            // ”’l‚Q‚S
            assertEquals(124, l_reflectVariousInformParams.getExtValue24());

            // ”’l‚Q‚T
            assertEquals(125, l_reflectVariousInformParams.getExtValue25());

            // ”’l‚Q‚U
            assertEquals(126, l_reflectVariousInformParams.getExtValue26());

            // ”’l‚Q‚V
            assertEquals(127, l_reflectVariousInformParams.getExtValue27());

            // ”’l‚Q‚W
            assertEquals(128, l_reflectVariousInformParams.getExtValue28());

            // ”’l‚Q‚X
            assertEquals(129, l_reflectVariousInformParams.getExtValue29());

            // ”’l‚R‚O
            assertEquals(130, l_reflectVariousInformParams.getExtValue30());

            // ”õl‚P
            assertEquals("note1", l_reflectVariousInformParams.getExtNote1());

            // ”õl‚Q
            assertEquals("note2", l_reflectVariousInformParams.getExtNote2());

            // XVÒƒR[ƒh
            assertEquals("abcdefghijklmnopqrst", l_reflectVariousInformParams.getLastUpdater());

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070607","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            // ì¬“ú
            assertEquals(l_date, l_reflectVariousInformParams.getCreatedTimestamp());

            l_date = WEB3DateUtility.getDate("20070608","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 21);
            l_date = WEB3DateUtility.addMinute(l_date, 2);
            l_date = WEB3DateUtility.addSecond(l_date, 43);

            // XV“ú
            assertEquals(l_date, l_reflectVariousInformParams.getLastUpdatedTimestamp());

            // –Á•¿ƒR[ƒh
            assertEquals("123123123", l_reflectVariousInformParams.getFundCode());

            // ˆµÒƒR[ƒhiSONARj
            assertEquals("67890", l_reflectVariousInformParams.getSonarTraderCode());

            // “`•[ì¬ó‹µ
            assertEquals("1", l_reflectVariousInformParams.getStatus());

            // ƒGƒ‰[——RƒR[ƒh
            assertEquals("abcd", l_reflectVariousInformParams.getErrorReasonCode());

            // “`•[¯•ÊƒR[ƒh
            assertEquals("987987987", l_reflectVariousInformParams.getOrderRequestNumber());

            // ƒf[ƒ^ƒR[ƒh
            assertEquals("55555", l_reflectVariousInformParams.getRequestCode());

            l_date = WEB3DateUtility.getDate("20070609","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 15);
            l_date = WEB3DateUtility.addMinute(l_date, 3);
            l_date = WEB3DateUtility.addSecond(l_date, 24);

            // “`•[‘—M“ú
            assertEquals(l_date, l_reflectVariousInformParams.getSendTimestamp());

            l_date = WEB3DateUtility.getDate("20070610","yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 8);
            l_date = WEB3DateUtility.addMinute(l_date, 59);
            l_date = WEB3DateUtility.addSecond(l_date, 59);

            // “`•[óM“ú
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

        // U‘Ö\i—X’™j“`•[
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
        // U‘Ö\i—X’™j“`•[
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

         // U‘Ö\i—X’™j“`•[
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

          // U‘Ö\i—X’™j“`•[
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

           // U‘Ö\i—X’™j“`•[
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

            // U‘Ö\i—X’™j“`•[
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

             // U‘Ö\i—X’™j“`•[
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

              // U‘Ö\i—X’™j“`•[
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

               // U‘Ö\i—X’™j“`•[
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

                // U‘Ö\i—X’™j“`•[
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
