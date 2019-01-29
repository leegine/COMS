head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.32.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.JunitTestBase;
import test.util.TestDBUtility;

import webbroker3.accountinfo.message.WEB3AccInfoAccountMailAddressInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.gentrade.data.AccountMailAddressParams;
import webbroker3.gentrade.data.MailAssortmentDao;
import webbroker3.gentrade.data.MailAssortmentPK;
import webbroker3.gentrade.data.MailAssortmentParams;
import webbroker3.gentrade.data.MailAssortmentRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImplTest extends JunitTestBase
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImplTest.class);

    public WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImplTest(String arg0)
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
    
    public void testCase1()
    {
        final String STR_METHOD_NAME = "testCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MailAssortmentParams.TYPE);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            MailAssortmentParams l_params1 = TestDBUtility.getMailAssortmentRow();
            l_params1.setInstitutionCode("0D");
            l_params1.setBranchCode("381");
            l_params1.setMailAssortmentDiv("7");
            l_params1.setAccountCode("1000041");
            l_params1.setEmailAddressNumber(5L);
            l_params1.setLastUpdater("100003");
            l_params1.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());

            l_queryProcessor.doInsertQuery(l_params1);
            String l_strQuery =
                " institution_code = ? "
                + " and branch_code = ? "
                + " and account_code = ? ";

            l_strQuery += " and mail_assortment_div = ? ";
            Object[] l_objQuery = new Object[]{
                    "0D",
                    "381",
                    "1000041",
                    "7"};
            
            Map l_map = new HashMap();

//            l_map.put("email_address_number", new Long(5L));
            l_map.put("last_updater", "100003");

            l_map.put("last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());
            l_queryProcessor.doUpdateAllQuery(
                MailAssortmentRow.TYPE,
                l_strQuery,
                l_objQuery,
                l_map);
            
            List l_lis = l_queryProcessor.doFindAllQuery(
                MailAssortmentRow.TYPE,
                l_strQuery,
                l_objQuery);
            MailAssortmentRow l_row = (MailAssortmentRow)l_lis.get(0);
            assertEquals(l_row.getLastUpdater(),"100003");
            MailAssortmentParams l_params  = new MailAssortmentParams(l_row);
            l_params.setLastUpdater("12345");
            l_queryProcessor.doUpdateQuery(l_params);
            List l_lis1 = l_queryProcessor.doFindAllQuery(
                    MailAssortmentRow.TYPE,
                    l_strQuery,
                    l_objQuery);
                MailAssortmentRow l_row2 = (MailAssortmentRow)l_lis1.get(0);
            assertEquals(l_row2.getLastUpdater(),"12345");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl.getDownloadData(String, String[], Date, Date, WEB3AccInfoSortKey[], String)'
     */
    public void testGetDownloadDataCase1()
    {
        final String STR_METHOD_NAME = "testGetDownloadDataCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.initData();
            
            String[] l_strBranchCodes = new String[]{"381", "382", "383"};

            WEB3AccInfoSortKey l_sortKey1 = new WEB3AccInfoSortKey();
            l_sortKey1.keyItem = "branchCode";
            l_sortKey1.ascDesc = "A";
            WEB3AccInfoSortKey l_sortKey2 = new WEB3AccInfoSortKey();
            l_sortKey2.keyItem = "accountCode";
            l_sortKey2.ascDesc = "A";
            WEB3AccInfoSortKey l_sortKey3 = new WEB3AccInfoSortKey();
            l_sortKey3.keyItem = "updateDate";
            l_sortKey3.ascDesc = "A";
            WEB3AccInfoSortKey[] l_sortKeys = new WEB3AccInfoSortKey[]{l_sortKey1, l_sortKey2, l_sortKey3};
            WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl l_impl = new WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl();
            WEB3AccInfoAccountMailAddressInfo[] l_mailAddressInfos = 
                l_impl.getDownloadData("0D", l_strBranchCodes,
                    WEB3DateUtility.getDate("20091204", "yyyyMMdd"),
                    WEB3DateUtility.getDate("20101204", "yyyyMMdd"),
                    l_sortKeys,
                     "1");
            //顧客メールアドレス情報.証券会社コード = 顧客メールアドレス行.証券会社コード
            assertEquals("100001",  l_mailAddressInfos[0].accountCode);
            //顧客メールアドレス情報.顧客名 = 顧客マスタ行.名前（苗字）※顧客名（漢字）として使用
            assertEquals("劉",  l_mailAddressInfos[0].accountName);
            //(顧客メールアドレス情報.更新者コード = 顧客メールアドレス.メールアドレス更新者コード
            assertEquals("100001",  l_mailAddressInfos[0].updaterCode);
            //顧客メールアドレス情報.送信フラグ = （*2）
            //取得した顧客メールアドレス行.証券会社コード、部店コード、顧客コード及
            //びメール種別区分が”6:案内メール”であることに該当するメール種別オブジェクトを取得する。
            //レコードが存在 の場合1
            assertEquals(null,  l_mailAddressInfos[0].sendFlag);
            
            //顧客メールアドレス情報.証券会社コード = 顧客メールアドレス行.証券会社コード
            assertEquals("100002",  l_mailAddressInfos[1].accountCode);
            //顧客メールアドレス情報.顧客名 = 顧客マスタ行.名前（苗字）※顧客名（漢字）として使用
            assertEquals("劉",  l_mailAddressInfos[1].accountName);
            //(顧客メールアドレス情報.更新者コード = 顧客メールアドレス.メールアドレス更新者コード
            assertEquals("100009",  l_mailAddressInfos[1].updaterCode);
            
            //顧客メールアドレス情報.証券会社コード = 顧客メールアドレス行.証券会社コード
            assertEquals("100003",  l_mailAddressInfos[2].accountCode);
            //顧客メールアドレス情報.顧客名 = 顧客マスタ行.名前（苗字）※顧客名（漢字）として使用
            assertEquals("劉",  l_mailAddressInfos[2].accountName);
            //(顧客メールアドレス情報.更新者コード = 顧客メールアドレス.メールアドレス更新者コード
            assertEquals("100003",  l_mailAddressInfos[2].updaterCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void initData()
    {
        try
        {
            TestDBUtility.deleteAll(AccountMailAddressParams.TYPE);
            AccountMailAddressParams l_AccountMailAddressParams1 = new AccountMailAddressParams();
            l_AccountMailAddressParams1.setInstitutionCode("0D");
            l_AccountMailAddressParams1.setBranchCode("381");   
            l_AccountMailAddressParams1.setAccountCode("1000011");
            l_AccountMailAddressParams1.setEmailAddressNumber(1);
            l_AccountMailAddressParams1.setAddressDiv("1");
            l_AccountMailAddressParams1.setEmailAddress("yu@@sinocom.cn");
            l_AccountMailAddressParams1.setEmailLastUpdater("001");
            l_AccountMailAddressParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_AccountMailAddressParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_AccountMailAddressParams1);
            AccountMailAddressParams l_AccountMailAddressParams2 = new AccountMailAddressParams();
            l_AccountMailAddressParams2.setInstitutionCode("0D");
            l_AccountMailAddressParams2.setBranchCode("381");   
            l_AccountMailAddressParams2.setAccountCode("1000021");
            l_AccountMailAddressParams2.setEmailAddressNumber(1);
            l_AccountMailAddressParams2.setAddressDiv("1");
            l_AccountMailAddressParams2.setEmailAddress("yu@@sinocom.cn");
            l_AccountMailAddressParams2.setEmailLastUpdater("002");
            l_AccountMailAddressParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_AccountMailAddressParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_AccountMailAddressParams2);
            AccountMailAddressParams l_AccountMailAddressParams3 = new AccountMailAddressParams();
            l_AccountMailAddressParams3.setInstitutionCode("0D");
            l_AccountMailAddressParams3.setBranchCode("381");   
            l_AccountMailAddressParams3.setAccountCode("1000031");
            l_AccountMailAddressParams3.setEmailAddressNumber(1);
            l_AccountMailAddressParams3.setAddressDiv("1");
            l_AccountMailAddressParams3.setEmailAddress("yu@@sinocom.cn");
            l_AccountMailAddressParams3.setEmailLastUpdater("003");
            l_AccountMailAddressParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_AccountMailAddressParams3.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_AccountMailAddressParams3);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(333812512241L);
            l_mainAccountParams1.setInstitutionCode("0D");
            l_mainAccountParams1.setBranchCode("381");
            l_mainAccountParams1.setAccountCode("1000011");
            l_mainAccountParams1.setInformationMailFlag(BooleanEnum.FALSE);//.案内メール送信フラグ
            l_mainAccountParams1.setFamilyName("劉");
            l_mainAccountParams1.setEmailLastUpdater("1000011");
            l_mainAccountParams1.setEmailLastUpdatedTimestamp(WEB3DateUtility.getDate("20100204", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams1);
            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams2.setAccountId(333812512242L);
            l_mainAccountParams2.setInstitutionCode("0D");
            l_mainAccountParams2.setBranchCode("381");
            l_mainAccountParams2.setAccountCode("1000021");
            l_mainAccountParams2.setInformationMailFlag(BooleanEnum.FALSE);//.案内メール送信フラグ
            l_mainAccountParams2.setFamilyName("劉");
            l_mainAccountParams2.setEmailLastUpdater("100009");
            l_mainAccountParams2.setEmailLastUpdatedTimestamp(WEB3DateUtility.getDate("20100304", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams2);
            MainAccountParams l_mainAccountParams3 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams3.setAccountId(333812512243L);
            l_mainAccountParams3.setInstitutionCode("0D");
            l_mainAccountParams3.setBranchCode("381");
            l_mainAccountParams3.setAccountCode("1000031");
            l_mainAccountParams3.setInformationMailFlag(BooleanEnum.FALSE);//.案内メール送信フラグ
            l_mainAccountParams3.setFamilyName("劉");
            l_mainAccountParams3.setEmailLastUpdater("1000031");
            l_mainAccountParams3.setEmailLastUpdatedTimestamp(WEB3DateUtility.getDate("20100404", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams3);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
