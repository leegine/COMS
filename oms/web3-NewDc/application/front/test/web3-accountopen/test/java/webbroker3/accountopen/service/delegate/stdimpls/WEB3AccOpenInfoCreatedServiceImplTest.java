head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenInfoCreatedServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.message.WEB3AccOpenAgencyInfo;
import webbroker3.accountopen.message.WEB3AccOpenApplyInfo;
import webbroker3.accountopen.message.WEB3AccOpenVoucherInfo;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenInfoCreatedServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenInfoCreatedServiceImplTest.class);

    public WEB3AccOpenInfoCreatedServiceImplTest(String arg0)
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
     * Test method for 'webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInfoCreatedServiceImpl.toAccOpenApplyInfo(WEB3AccOpenExpAccountOpen)'
     */
    public void testToAccOpenApplyInfoCase1()
    {
        final String STR_METHOD_NAME = "testToAccOpenApplyInfoCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //ExpAccountOpenRow
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setBranchCode("101");
            l_expAccountOpenParams.setAccountCode("2512246");
            l_expAccountOpenParams.setDeleteFlag(null);
            l_expAccountOpenParams.setDeleteTimestamp(null);
            l_expAccountOpenParams.setPrintFlag(null);
            l_expAccountOpenParams.setReceiptFlag(null);
            l_expAccountOpenParams.setAgreementFlag(null);
            l_expAccountOpenParams.setForeignFlag(null);
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen("0D", "101", "2512246");
            
            WEB3AccOpenApplyInfo l_applyInfo = l_impl.toAccOpenApplyInfo(l_accOpenExpAccountOpen);
            //削除フラグ
            assertNull(l_applyInfo.deleteFlag);
            //削除日時
            assertNull(l_applyInfo.deleteDate);
            //印刷フラグ
            assertNull(l_applyInfo.printFlag);
            //受領フラグ
            assertNull(l_applyInfo.receiveFlag);
            //承諾フラグ
            assertNull(l_applyInfo.approveFlag);
            //外国人フラグ
            assertNull(l_applyInfo.foreignerFlag);
            //フリガナ1
            assertNull(l_applyInfo.agencyInfo.agencyAccNameKana1);
            //フリガナ2
            assertNull(l_applyInfo.agencyInfo.agencyAccNameKana2);
            //名称1
            assertNull(l_applyInfo.agencyInfo.agencyAccName1);
            //名称2
            assertNull(l_applyInfo.agencyInfo.agencyAccName2);
            //住所1
            assertNull(l_applyInfo.agencyInfo.agencyAddress1);
            //住所2
            assertNull(l_applyInfo.agencyInfo.agencyAddress2);
            //代表者の役職
            assertNull(l_applyInfo.agencyInfo.agencyRepPost);
            //代表者のフリガナ1
            assertNull(l_applyInfo.agencyInfo.agencyRepNameKana1);
            //代表者のフリガナ2
            assertNull(l_applyInfo.agencyInfo.agencyRepNameKana2);
            //代表者の氏名1
            assertNull(l_applyInfo.agencyInfo.agencyRepName1);
            //代表者の氏名2
            assertNull(l_applyInfo.agencyInfo.agencyRepName2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testToAccOpenApplyInfoCase2()
    {
        final String STR_METHOD_NAME = "testToAccOpenApplyInfoCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //ExpAccountOpenRow
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setBranchCode("101");
            l_expAccountOpenParams.setAccountCode("2512246");
            //削除フラグ
            l_expAccountOpenParams.setDeleteFlag(BooleanEnum.TRUE);
            //削除日時
            l_expAccountOpenParams.setDeleteTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            //印刷フラグ
            l_expAccountOpenParams.setPrintFlag("0");
            //受領フラグ
            l_expAccountOpenParams.setReceiptFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setAgreementFlag(BooleanEnum.TRUE);
            l_expAccountOpenParams.setForeignFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen("0D", "101", "2512246");
            
            WEB3AccOpenApplyInfo l_applyInfo = l_impl.toAccOpenApplyInfo(l_accOpenExpAccountOpen);
            //削除フラグ
            assertEquals("1",l_applyInfo.deleteFlag);
            //削除日時
            //印刷フラグ
            assertEquals("0",l_applyInfo.printFlag);
            //受領フラグ
            assertEquals("1",l_applyInfo.receiveFlag);
            //承諾フラグ
            assertEquals("1",l_applyInfo.approveFlag);
            //外国人フラグ
            assertEquals("1", l_applyInfo.foreignerFlag);
            //フリガナ1
            assertNull(l_applyInfo.agencyInfo.agencyAccNameKana1);
            //フリガナ2
            assertNull(l_applyInfo.agencyInfo.agencyAccNameKana2);
            //名称1
            assertNull(l_applyInfo.agencyInfo.agencyAccName1);
            //名称2
            assertNull(l_applyInfo.agencyInfo.agencyAccName2);
            //住所1
            assertNull(l_applyInfo.agencyInfo.agencyAddress1);
            //住所2
            assertNull(l_applyInfo.agencyInfo.agencyAddress2);
            //代表者の役職
            assertNull(l_applyInfo.agencyInfo.agencyRepPost);
            //代表者のフリガナ1
            assertNull(l_applyInfo.agencyInfo.agencyRepNameKana1);
            //代表者のフリガナ2
            assertNull(l_applyInfo.agencyInfo.agencyRepNameKana2);
            //代表者の氏名1
            assertNull(l_applyInfo.agencyInfo.agencyRepName1);
            //代表者の氏名2
            assertNull(l_applyInfo.agencyInfo.agencyRepName2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInfoCreatedServiceImpl.toAccOpenExpAccountOpen(WEB3AccOpenApplyInfo)'
     */
    public void testToAccOpenExpAccountOpenCase1()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            WEB3AccOpenApplyInfo l_applyInfo = new WEB3AccOpenApplyInfo();
            
//            //削除フラグ
//            l_applyInfo.deleteFlag = "1";
//            //削除日時
//            //印刷フラグ
//            l_applyInfo.printFlag = "2";
//            //受領フラグ
//            l_applyInfo.receiveFlag = "1";
//            //承諾フラグ
//            l_applyInfo.approveFlag = "1";
//            //外国人フラグ
//            l_applyInfo.foreignerFlag = "1";
//            //フリガナ1
//            l_applyInfo.agencyInfo.agencyAccNameKana1 = "aaa";
//            //フリガナ2
//            l_applyInfo.agencyInfo.agencyAccNameKana2 = "bbb";
//            //名称1
//            l_applyInfo.agencyInfo.agencyAccName1 = "ccc";
//            //名称2
//            l_applyInfo.agencyInfo.agencyAccName2 = "ddd";
//            //住所1
//            l_applyInfo.agencyInfo.agencyAddress1 = "eee";
//            //住所2
//            l_applyInfo.agencyInfo.agencyAddress2 = "fff";
//            //代表者の役職
//            l_applyInfo.agencyInfo.agencyRepPost = "111";
//            //代表者のフリガナ1
//            l_applyInfo.agencyInfo.agencyRepNameKana1 = "ggg";
//            //代表者のフリガナ2
//            l_applyInfo.agencyInfo.agencyRepNameKana2 = "hhh";
//            //代表者の氏名1
//            l_applyInfo.agencyInfo.agencyRepName1 = "iii";
//            //代表者の氏名2
//            l_applyInfo.agencyInfo.agencyRepName2 = "jjj";
            
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                l_impl.toAccOpenExpAccountOpen(l_applyInfo);
            
            ExpAccountOpenParams l_params = (ExpAccountOpenParams)l_accOpenExpAccountOpen.getDataSourceObject();
            //削除フラグ
            assertNull(l_params.getDeleteFlag());
            //削除日時
            //印刷フラグ
            assertNull(l_params.getPrintFlag());
            //受領フラグ
            assertNull(l_params.getReceiptFlag());
            //承諾フラグ
            assertNull(l_params.getAgreementFlag());
            //外国人フラグ
            assertNull(l_params.getForeignFlag());
            //フリガナ1
            assertNull(l_params.getAgencyAccNameKana1());
            //フリガナ2
            assertNull( l_params.getAgencyAccNameKana2());
            //名称1
            assertNull(l_params.getAgencyAccName1());
            //名称2
            assertNull(l_params.getAgencyAccName2());
            //住所1
            assertNull(l_params.getAgencyAddressLine1());
            //住所2
            assertNull(l_params.getAgencyAddressLine2());
            //代表者の役職
            assertNull( l_params.getAgencyRepPost());
            //代表者のフリガナ1
            assertNull(l_params.getAgencyRepNameKana1());
            //代表者のフリガナ2
            assertNull(l_params.getAgencyRepNameKana2());
            //代表者の氏名1
            assertNull(l_params.getAgencyRepName1());
            //代表者の氏名2
            assertNull(l_params.getAgencyRepName2());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testToAccOpenExpAccountOpenCase2()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            WEB3AccOpenApplyInfo l_applyInfo = new WEB3AccOpenApplyInfo();
            
            //削除フラグ
            l_applyInfo.deleteFlag = "1";
            //削除日時
            //印刷フラグ
            l_applyInfo.printFlag = "2";
            //受領フラグ
            l_applyInfo.receiveFlag = "1";
            //承諾フラグ
            l_applyInfo.approveFlag = "1";
            //外国人フラグ
            l_applyInfo.foreignerFlag = "1";
            WEB3AccOpenAgencyInfo agencyInfo = new WEB3AccOpenAgencyInfo();
            //フリガナ1
            agencyInfo.agencyAccNameKana1 = "aaa";
            //フリガナ2
            agencyInfo.agencyAccNameKana2 = "bbb";
            //名称1
            agencyInfo.agencyAccName1 = "ccc";
            //名称2
            agencyInfo.agencyAccName2 = "ddd";
            //住所1
            agencyInfo.agencyAddress1 = "eee";
            //住所2
            agencyInfo.agencyAddress2 = "fff";
            //代表者の役職
            agencyInfo.agencyRepPost = "111";
            //代表者のフリガナ1
            agencyInfo.agencyRepNameKana1 = "ggg";
            //代表者のフリガナ2
            agencyInfo.agencyRepNameKana2 = "hhh";
            //代表者の氏名1
            agencyInfo.agencyRepName1 = "iii";
            //代表者の氏名2
            agencyInfo.agencyRepName2 = "jjj";
            l_applyInfo.agencyInfo = agencyInfo;
            
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
                l_impl.toAccOpenExpAccountOpen(l_applyInfo);
            
            ExpAccountOpenParams l_params = (ExpAccountOpenParams)l_accOpenExpAccountOpen.getDataSourceObject();
            //削除フラグ
            assertEquals("1", l_params.getDeleteFlag().intValue() + "");
            //削除日時
            //印刷フラグ
            assertEquals("2",l_params.getPrintFlag());
            //受領フラグ
            assertEquals("1",l_params.getReceiptFlag().intValue() + "");
            //承諾フラグ
            assertEquals("1",l_params.getAgreementFlag().intValue() + "");
            //外国人フラグ
            assertEquals("1", l_params.getForeignFlag().intValue() + "");
            //フリガナ1
            assertEquals("aaa", l_params.getAgencyAccNameKana1());
            //フリガナ2
            assertEquals("bbb", l_params.getAgencyAccNameKana2());
            //名称1
            assertEquals("ccc", l_params.getAgencyAccName1());
            //名称2
            assertEquals("ddd", l_params.getAgencyAccName2());
            //住所1
            assertEquals("eee", l_params.getAgencyAddressLine1());
            //住所2
            assertEquals("fff", l_params.getAgencyAddressLine2());
            //代表者の役職
            assertEquals("111", l_params.getAgencyRepPost());
            //代表者のフリガナ1
            assertEquals("ggg", l_params.getAgencyRepNameKana1());
            //代表者のフリガナ2
            assertEquals("hhh", l_params.getAgencyRepNameKana2());
            //代表者の氏名1
            assertEquals("iii", l_params.getAgencyRepName1());
            //代表者の氏名2
            assertEquals("jjj", l_params.getAgencyRepName2());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInfoCreatedServiceImpl.toColumnSymbolName(String)'
     */
    //deleteFlag削除フラグ
    public void testToColumnSymbolNameCase1()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("deleteFlag");
            assertEquals("delete_flag", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //deleteDate削除日時
    public void testToColumnSymbolNameCase2()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("deleteDate");
            assertEquals("delete_timestamp", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //printFlag印刷フラグ
    public void testToColumnSymbolNameCase3()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("printFlag");
            assertEquals("print_flag", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //receiveFlag受領フラグ
    public void testToColumnSymbolNameCase4()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("receiveFlag");
            assertEquals("receipt_flag", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //approveFlag承諾フラグ
    public void testToColumnSymbolNameCase5()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase5()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("approveFlag");
            assertEquals("agreement_flag", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //foreignerFlag外国人フラグ
    public void testToColumnSymbolNameCase6()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase6()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("foreignerFlag");
            assertEquals("foreign_flag", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //フリガナ1
    public void testToColumnSymbolNameCase7()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase7()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("agencyInfo.agencyAccNameKana1");
            assertEquals("agency_acc_name_kana1", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //フリガナ2
    public void testToColumnSymbolNameCase8()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase8()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("agencyInfo.agencyAccNameKana2");
            assertEquals("agency_acc_name_kana2", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyAccName1名称1
    public void testToColumnSymbolNameCase9()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase9()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("agencyInfo.agencyAccName1");
            assertEquals("agency_acc_name1", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyAccName1名称2
    public void testToColumnSymbolNameCase10()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase10()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("agencyInfo.agencyAccName2");
            assertEquals("agency_acc_name2", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyAddress1住所1
    public void testToColumnSymbolNameCase11()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase11()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("agencyInfo.agencyAddress1");
            assertEquals("agency_address_line1", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyAddress2住所2
    public void testToColumnSymbolNameCase12()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase12()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("agencyInfo.agencyAddress2");
            assertEquals("agency_address_line2", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyRepPost代表者の役職
    public void testToColumnSymbolNameCase13()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase13()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("agencyInfo.agencyRepPost");
            assertEquals("agency_rep_post", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //agencyInfo.agencyRepNameKana1代表者のフリガナ1
    public void testToColumnSymbolNameCase14()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase14()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("agencyInfo.agencyRepNameKana1");
            assertEquals("agency_rep_name_kana1", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //agencyInfo.agencyRepNameKana2代表者のフリガナ2
    public void testToColumnSymbolNameCase15()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase15()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("agencyInfo.agencyRepNameKana2");
            assertEquals("agency_rep_name_kana2", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyRepName1代表者の氏名1
    public void testToColumnSymbolNameCase16()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase16()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("agencyInfo.agencyRepName1");
            assertEquals("agency_rep_name1", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyRepName2代表者の氏名2
    public void testToColumnSymbolNameCase17()
    {
        final String STR_METHOD_NAME = "testToAccOpenExpAccountOpenCase17()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toColumnSymbolName("agencyInfo.agencyRepName2");
            assertEquals("agency_rep_name2", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInfoCreatedServiceImpl.toMessageItemName(String)'
     */
    public void testToMessageItemNameCase1()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("delete_flag");
            assertEquals("deleteFlag", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    //deleteDate削除日時
    public void testToMessageItemNameCase2()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("delete_timestamp");
            assertEquals("deleteDate", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //printFlag印刷フラグ
    public void testToMessageItemNameCase3()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("print_flag");
            assertEquals("printFlag", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //receiveFlag受領フラグ
    public void testToMessageItemNameCase4()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("receipt_flag");
            assertEquals("receiveFlag", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //approveFlag承諾フラグ
    public void testToMessageItemNameCase5()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase5()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agreement_flag");
            assertEquals("approveFlag", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //foreignerFlag外国人フラグ
    public void testToMessageItemNameCase6()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase6()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("foreign_flag");
            assertEquals("foreignerFlag", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //フリガナ1
    public void testToMessageItemNameCase7()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase7()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agency_acc_name_kana1");
            assertEquals("agencyInfo.agencyAccNameKana1", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //フリガナ2
    public void testToMessageItemNameCase8()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase8()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agency_acc_name_kana2");
            assertEquals("agencyInfo.agencyAccNameKana2", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyAccName1名称1
    public void testToMessageItemNameCase9()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase9()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agency_acc_name1");
            assertEquals("agencyInfo.agencyAccName1", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyAccName1名称2
    public void testToMessageItemNameCase10()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase10()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agency_acc_name2");
            assertEquals("agencyInfo.agencyAccName2", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyAddress1住所1
    public void testToMessageItemNameCase11()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase11()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agency_address_line1");
            assertEquals("agencyInfo.agencyAddress1", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyAddress2住所2
    public void testToMessageItemNameCase12()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase12()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agency_address_line2");
            assertEquals("agencyInfo.agencyAddress2", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyRepPost代表者の役職
    public void testToMessageItemNameCase13()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase13()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agency_rep_post");
            assertEquals("agencyInfo.agencyRepPost", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //agencyInfo.agencyRepNameKana1代表者のフリガナ1
    public void testToMessageItemNameCase14()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase14()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agency_rep_name_kana1");
            assertEquals("agencyInfo.agencyRepNameKana1", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //agencyInfo.agencyRepNameKana2代表者のフリガナ2
    public void testToMessageItemNameCase15()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase15()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agency_rep_name_kana2");
            assertEquals("agencyInfo.agencyRepNameKana2", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyRepName1代表者の氏名1
    public void testToMessageItemNameCase16()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase16()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agency_rep_name1");
            assertEquals("agencyInfo.agencyRepName1", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    // agencyInfo.agencyRepName2代表者の氏名2
    public void testToMessageItemNameCase17()
    {
        final String STR_METHOD_NAME = "testToMessageItemNameCase17()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            String l_strName =  l_impl.toMessageItemName("agency_rep_name2");
            assertEquals("agencyInfo.agencyRepName2", l_strName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testToAccOpenVoucherInfoCase1()
    {
        final String STR_METHOD_NAME = "testToAccOpenVoucherInfoCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AccOpenVoucherStatusRow.TYPE);
            AccOpenVoucherStatusParams l_voucherStatusParams1 = TestDBUtility.getAccOpenVoucherStatusRow();
            l_voucherStatusParams1.setRequestCode("GI858");
            l_voucherStatusParams1.setErrorCode("123");
            l_voucherStatusParams1.setVoucherStatus("0");
            TestDBUtility.insertWithDel(l_voucherStatusParams1);
            
            TestDBUtility.deleteAll(AccOpenVoucherStatusRow.TYPE);
            AccOpenVoucherStatusParams l_voucherStatusParams2 = TestDBUtility.getAccOpenVoucherStatusRow();
            l_voucherStatusParams2.setRequestCode("GI858");
            l_voucherStatusParams2.setErrorCode(null);
            l_voucherStatusParams2.setVoucherStatus("1");
            TestDBUtility.insertWithDel(l_voucherStatusParams2);
            
            TestDBUtility.deleteAll(AccOpenVoucherStatusRow.TYPE);
            AccOpenVoucherStatusParams l_voucherStatusParams3 = TestDBUtility.getAccOpenVoucherStatusRow();
            l_voucherStatusParams3.setRequestCode("GI854");
            l_voucherStatusParams3.setErrorCode(null);
            TestDBUtility.insertWithDel(l_voucherStatusParams3);
            
            WEB3AccOpenInfoCreatedServiceImpl l_impl = new WEB3AccOpenInfoCreatedServiceImpl();
            WEB3AccOpenVoucherCreatedStatus[] l_accOpenVoucherCreatedStatuses = new WEB3AccOpenVoucherCreatedStatus[3];
            WEB3AccOpenVoucherCreatedStatus l_accOpenVoucherCreatedStatuses1 =
                new WEB3AccOpenVoucherCreatedStatus(l_voucherStatusParams1);
            WEB3AccOpenVoucherCreatedStatus l_accOpenVoucherCreatedStatuses2 =
                new WEB3AccOpenVoucherCreatedStatus(l_voucherStatusParams2);
            WEB3AccOpenVoucherCreatedStatus l_accOpenVoucherCreatedStatuses3 =
                new WEB3AccOpenVoucherCreatedStatus(l_voucherStatusParams3);
            l_accOpenVoucherCreatedStatuses[0] = l_accOpenVoucherCreatedStatuses1;
            l_accOpenVoucherCreatedStatuses[1] = l_accOpenVoucherCreatedStatuses2;
            l_accOpenVoucherCreatedStatuses[2] = l_accOpenVoucherCreatedStatuses3;
            
            WEB3AccOpenVoucherInfo voucherInfo = 
                l_impl.toAccOpenVoucherInfo(l_accOpenVoucherCreatedStatuses);
            
            assertEquals("2",  voucherInfo.agencyVoucherDiv.length+"");
            assertEquals("0:123", voucherInfo.agencyVoucherDiv[0]);
            assertEquals("1", voucherInfo.agencyVoucherDiv[1]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
