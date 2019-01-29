head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenAccountRegBrokerageFirmVoucherTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客登録（仲介業）伝票(WEB3AccOpenAccountRegBrokerageFirmVoucherTest)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 趙林鵬(中訊)
*/

package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.AccOpenVoucherItemParams;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.HostAccRegVoucherDao;
import webbroker3.accountopen.data.HostAccRegVoucherRow;
import webbroker3.accountopen.define.WEB3AccountOpenOutputItemSymbolNameDef;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.system.tune.affinity.WEB3OrderReqNumberHead2ManageService;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenAccountRegBrokerageFirmVoucherTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenAccountRegBrokerageFirmVoucherTest.class);

    public WEB3AccOpenAccountRegBrokerageFirmVoucherTest(String arg0)
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

    /**
     * save伝票行
     * 所１（カナ）、住所２（カナ）合計サイズが60，
     * 住所３（カナ）サイズが20，
     * 
     * 
     * ※住所１（カナ）、住所２（カナ）、住所３（カナ）にセットする値の合計サイズが68文字を超過している場合は、
     * 68文字以降を住所３（カナ）より切り捨てる。
     */
    public void testSaveVoucherRowCase0001()
    {
        final String STR_METHOD_NAME = "testSaveVoucherRowCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAddressLine1Kana("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            l_expAccountOpenParams.setAddressLine2Kana("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            l_expAccountOpenParams.setAddressLine3Kana("bbbbbbbbbbbbbbbbbbbb");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3OrderReqNumberHead2ManageService.NOT_GET_NUMBER_FLAG, Boolean.TRUE);
                    
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = new WEB3AccOpenExpAccountOpen(
                "0D","381","2512246");
            
            WEB3AccOpenAccountRegBrokerageFirmVoucher l_Voucher =
                WEB3AccOpenAccountRegBrokerageFirmVoucher.getInstance(l_accOpenExpAccountOpen);
            
            l_Voucher.saveVoucherRow("0");
            
            HostAccRegVoucherRow l_hostAccRegVoucherRow = HostAccRegVoucherDao.findRowByPk(
                    "000000000",
                    "GI845",
                    l_expAccountOpenParams.getInstitutionCode(),
                    l_expAccountOpenParams.getBranchCode(),
                    l_expAccountOpenParams.getAccountCode());
            
            assertEquals("bbbbbbbb", l_hostAccRegVoucherRow.getAddressLine3Kana());
            
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
        
    /**
     * save伝票行
     * 所１（カナ）、住所２（カナ）合計サイズが60，
     * 住所３（カナ）サイズが2，
     * ※住所１（カナ）、住所２（カナ）、住所３（カナ）にセットする値の合計サイズが68文字を超過している場合は、
     * 68文字以降を住所３（カナ）より切り捨てる。
     */
    public void testSaveVoucherRowCase0002()
    {
        final String STR_METHOD_NAME = "testSaveVoucherRowCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAddressLine1Kana("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            l_expAccountOpenParams.setAddressLine2Kana("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            l_expAccountOpenParams.setAddressLine3Kana("bb");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3OrderReqNumberHead2ManageService.NOT_GET_NUMBER_FLAG, Boolean.TRUE);
                    
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = new WEB3AccOpenExpAccountOpen(
                "0D","381","2512246");
            
            WEB3AccOpenAccountRegBrokerageFirmVoucher l_Voucher =
                WEB3AccOpenAccountRegBrokerageFirmVoucher.getInstance(l_accOpenExpAccountOpen);
            
            l_Voucher.saveVoucherRow("0");
            
            HostAccRegVoucherRow l_hostAccRegVoucherRow = HostAccRegVoucherDao.findRowByPk(
                    "000000000",
                    "GI845",
                    l_expAccountOpenParams.getInstitutionCode(),
                    l_expAccountOpenParams.getBranchCode(),
                    l_expAccountOpenParams.getAccountCode());
            
            assertEquals("bb", l_hostAccRegVoucherRow.getAddressLine3Kana());
            
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
        
    /**
     * save伝票行
     * 所１（カナ）、住所２（カナ）合計サイズが20，
     * 住所３（カナ）サイズが60，
     * ※住所１（カナ）、住所２（カナ）、住所３（カナ）にセットする値の合計サイズが68文字を超過している場合は、
     * 68文字以降を住所３（カナ）より切り捨てる。
     */
    public void testSaveVoucherRowCase0003()
    {
        final String STR_METHOD_NAME = "testSaveVoucherRowCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAddressLine1Kana("aaaaaaaaaa");
            l_expAccountOpenParams.setAddressLine2Kana("aaaaaaaaaa");
            l_expAccountOpenParams.setAddressLine3Kana("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3OrderReqNumberHead2ManageService.NOT_GET_NUMBER_FLAG, Boolean.TRUE);
                    
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = new WEB3AccOpenExpAccountOpen(
                "0D","381","2512246");
            
            WEB3AccOpenAccountRegBrokerageFirmVoucher l_Voucher =
                WEB3AccOpenAccountRegBrokerageFirmVoucher.getInstance(l_accOpenExpAccountOpen);
            
            l_Voucher.saveVoucherRow("0");
            
            HostAccRegVoucherRow l_hostAccRegVoucherRow = HostAccRegVoucherDao.findRowByPk(
                    "000000000",
                    "GI845",
                    l_expAccountOpenParams.getInstitutionCode(),
                    l_expAccountOpenParams.getBranchCode(),
                    l_expAccountOpenParams.getAccountCode());
            
            assertEquals("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", l_hostAccRegVoucherRow.getAddressLine3Kana());
            
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }   
    
    //==============
    public void testSaveVoucherRowCase0004()
    {
        final String STR_METHOD_NAME = "testSaveVoucherRowCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAddressLine1Kana("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            l_expAccountOpenParams.setAddressLine2Kana("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            l_expAccountOpenParams.setAddressLine3Kana("bbbbbbbbbbbbbbbbbbbb");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            TestDBUtility.deleteAll(AccOpenVoucherItemParams.TYPE);
            AccOpenVoucherItemParams l_accOpenVoucherItemParams = new AccOpenVoucherItemParams();
            l_accOpenVoucherItemParams.setInstitutionCode("0D");
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setAccountDiv(l_expAccountOpenParams.getAccountDiv());
            l_accOpenVoucherItemParams.setRequestCode("GI845");
            l_accOpenVoucherItemParams.setSerialNo("0");
            
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.PROAM_DIV); //<-------
            l_accOpenVoucherItemParams.setEditWayDiv(WEB3EditWayDivDef.FIXED_VALUE);  
            
            l_accOpenVoucherItemParams.setFixedValue("1234");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);
            
            //*******************************************************************
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_BROADCAST); //<------
            l_accOpenVoucherItemParams.setFixedValue("2345");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);
            
//          *******************************************************************
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_AVIATION); //<------
            l_accOpenVoucherItemParams.setFixedValue("3456");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);
            
//          *******************************************************************
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.FOREIGNER_DIV_NTT); //<------
            l_accOpenVoucherItemParams.setFixedValue("4567");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);
            
//          *******************************************************************
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.DIVIDEND_TRANSFER_DIV); //<------
            l_accOpenVoucherItemParams.setFixedValue("5678");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);
            
//          *******************************************************************
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.AGENT_DIV_PERMANENT); //<------
            l_accOpenVoucherItemParams.setFixedValue("6789");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);
            
//          *******************************************************************
            l_accOpenVoucherItemParams.setBranchCode("381");
            l_accOpenVoucherItemParams.setOutputItemSymbolName(WEB3AccountOpenOutputItemSymbolNameDef.AGENT_DIV_LEGAL); //<------
            l_accOpenVoucherItemParams.setFixedValue("7890");
            TestDBUtility.insertWithDel(l_accOpenVoucherItemParams);
            
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3OrderReqNumberHead2ManageService.NOT_GET_NUMBER_FLAG, Boolean.TRUE);
                    
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = new WEB3AccOpenExpAccountOpen(
                "0D","381","2512246");
            
            WEB3AccOpenAccountRegBrokerageFirmVoucher l_Voucher =
                WEB3AccOpenAccountRegBrokerageFirmVoucher.getInstance(l_accOpenExpAccountOpen);
            
            l_Voucher.saveVoucherRow("0");
            
            HostAccRegVoucherRow l_hostAccRegVoucherRow = HostAccRegVoucherDao.findRowByPk(
                    "000000000",
                    "GI845",
                    l_expAccountOpenParams.getInstitutionCode(),
                    l_expAccountOpenParams.getBranchCode(),
                    l_expAccountOpenParams.getAccountCode());
            
            assertEquals("bbbbbbbb", l_hostAccRegVoucherRow.getAddressLine3Kana());
            assertEquals("1", l_hostAccRegVoucherRow.getProamDiv());
            assertEquals("2", l_hostAccRegVoucherRow.getForeignerDivBroadcast());
            assertEquals("3", l_hostAccRegVoucherRow.getForeignerDivAviation());
            assertEquals("4", l_hostAccRegVoucherRow.getForeignerDivNtt());
            assertEquals("5", l_hostAccRegVoucherRow.getDividendTransferDiv());
            assertEquals("6", l_hostAccRegVoucherRow.getAgentDivPermanent());
            assertEquals("7", l_hostAccRegVoucherRow.getAgentDivLegal());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    
}
@
