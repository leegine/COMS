head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.09.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenAccountRegVoucherTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客登録伝票(WEB3AccOpenAccountRegVoucherTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 趙林鵬(中訊)
*/

package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.AccOpenVoucherItemParams;
import webbroker3.accountopen.data.AccOpenVoucherMasterParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
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

public class WEB3AccOpenAccountRegVoucherTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenAccountRegVoucherTest.class);

    public WEB3AccOpenAccountRegVoucherTest(String arg0)
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
            
            WEB3AccOpenAccountRegVoucher l_Voucher =
                WEB3AccOpenAccountRegVoucher.getInstance(l_accOpenExpAccountOpen);
            
            l_Voucher.saveVoucherRow("0");
            
            HostAccRegVoucherRow l_hostAccRegVoucherRow = HostAccRegVoucherDao.findRowByPk(
                    "000000000",
                    "GI821",
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
            
            WEB3AccOpenAccountRegVoucher l_Voucher =
                WEB3AccOpenAccountRegVoucher.getInstance(l_accOpenExpAccountOpen);
            
            l_Voucher.saveVoucherRow("0");
            
            HostAccRegVoucherRow l_hostAccRegVoucherRow = HostAccRegVoucherDao.findRowByPk(
                    "000000000",
                    "GI821",
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
            
            WEB3AccOpenAccountRegVoucher l_Voucher =
                WEB3AccOpenAccountRegVoucher.getInstance(l_accOpenExpAccountOpen);
            
            l_Voucher.saveVoucherRow("0");
            
            HostAccRegVoucherRow l_hostAccRegVoucherRow = HostAccRegVoucherDao.findRowByPk(
                    "000000000",
                    "GI821",
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
            l_accOpenVoucherItemParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST);
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
            
            WEB3AccOpenAccountRegVoucher l_Voucher =
                WEB3AccOpenAccountRegVoucher.getInstance(l_accOpenExpAccountOpen);
            
            l_Voucher.saveVoucherRow("0");
            
            HostAccRegVoucherRow l_hostAccRegVoucherRow = HostAccRegVoucherDao.findRowByPk(
                    "000000000",
                    "GI821",
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
    
    public void testGetConfirmedItemName_T01()
    {
        try
        {
            
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
           
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
          
            TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
            
            AccOpenVoucherStatusParams l_AccOpenVoucherStatusParams = new AccOpenVoucherStatusParams();
            
//            INSTITUTION_CODE        VARCHAR2(3) not null,
            l_AccOpenVoucherStatusParams.setInstitutionCode("0D");
            
//            ACC_OPEN_REQUEST_NUMBER VARCHAR2(13) not null,
            l_AccOpenVoucherStatusParams.setAccOpenRequestNumber("0");
            
//            REQUEST_CODE            VARCHAR2(5) not null,
            l_AccOpenVoucherStatusParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST);
//            SERIAL_NO               VARCHAR2(3) not null,
            l_AccOpenVoucherStatusParams.setSerialNo("1");
            
            
            l_AccOpenVoucherStatusParams.setVoucherStatus("1");
            
//            VOUCHER_STATUS          VARCHAR2(1) not null,
            l_AccOpenVoucherStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            LAST_UPDATED_TIMESTAMP  DATE not null
            l_AccOpenVoucherStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            
            TestDBUtility.insertWithDel(l_AccOpenVoucherStatusParams);
            
            
            
            
            TestDBUtility.deleteAll(AccOpenVoucherMasterParams.TYPE);
            AccOpenVoucherMasterParams l_accOpenVoucherMasterParams = new AccOpenVoucherMasterParams();
            l_accOpenVoucherMasterParams.setInstitutionCode("0D");
            l_accOpenVoucherMasterParams.setBranchCode("381");
            l_accOpenVoucherMasterParams.setAccountDiv("0");
            l_accOpenVoucherMasterParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST);
            l_accOpenVoucherMasterParams.setSerialNo("1");
            TestDBUtility.insertWithDel(l_accOpenVoucherMasterParams);
            
            
            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = new WEB3AccOpenExpAccountOpen(
                    "0D","381","2512246");

            
                WEB3AccOpenAccountRegVoucher l_Voucher =
                    WEB3AccOpenAccountRegVoucher.getInstance(l_accOpenExpAccountOpen);
                
                    
                String[] l_str = l_Voucher.getConfirmedItemName();
                
                
                
                
                
        }    
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
        
    }
    
}
@
