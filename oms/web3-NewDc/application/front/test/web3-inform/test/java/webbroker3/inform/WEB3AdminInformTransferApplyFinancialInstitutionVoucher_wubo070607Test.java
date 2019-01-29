head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.53.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformTransferApplyFinancialInstitutionVoucher_wubo070607Test.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 振替申込（銀行）伝票テスト(WEB3AdminInformTransferApplyFinancialInstitutionVoucherTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/07 武波(中訊) 新規作成 モデルNo.056
*/
package webbroker3.inform;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import webbroker3.accountopen.data.HostBankTransVoucherParams;
import webbroker3.accountopen.data.HostBankTransVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (振替申込（銀行）伝票テスト)<BR>
 * 振替申込（銀行）伝票クラステスト
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminInformTransferApplyFinancialInstitutionVoucher_wubo070607Test
    extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformTransferApplyFinancialInstitutionVoucher_wubo070607Test.class);

    /**
     * 振替申込（銀行）伝票テスト<BR>
     * @@param l_strName
     */
    public WEB3AdminInformTransferApplyFinancialInstitutionVoucher_wubo070607Test(String l_strName)
    {
        super(l_strName);
        // TODO Auto-generated constructor stub
    }

    /**
     * setUp<BR>
     * @@param l_strName
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     * tearDown<BR>
     * @@param l_strName
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * 振替申込（銀行）伝票（G26）キューテーブル中插入一條數據
     */
    public void test_saveBankRegistVoucherHost_C0001()
    {
        final String STR_METHOD_NAME = " test_saveBankRegistVoucherHost_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("123");
            l_mainAccountParams.setBranchCode("000");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(HostBankTransVoucherParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode("1234567");
            l_variousInformParams.setOrderRequestNumber("22");
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher l_institutionVoucher =
                new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(l_variousInformParams);
            l_institutionVoucher.saveBankRegistVoucherHost();

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                StringBuffer l_sbSearchCondition = new StringBuffer();
                l_sbSearchCondition.append(" request_code = ? and ");
                l_sbSearchCondition.append(" institution_code = ? and ");
                l_sbSearchCondition.append(" branch_code = ? and ");
                l_sbSearchCondition.append(" account_code = ? ");
                Object[] l_dataValues = {"GI823", "123", "000", "1234567"};
                List l_lisHostBankTransVoucherRows = l_queryProcessor.doFindAllQuery(HostBankTransVoucherRow.TYPE,
                        l_sbSearchCondition.toString(),
                        l_dataValues);
                assertEquals(1,l_lisHostBankTransVoucherRows.size());
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 振替申込（銀行）伝票（G26）キューテーブル中刪除一條數據
     */
    public void test_deleteBankRegistVoucherHost_C0002()
    {
        final String STR_METHOD_NAME = " test_deleteBankRegistVoucherHost_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("123");
            l_mainAccountParams.setBranchCode("000");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(HostBankTransVoucherParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setOrderRequestNumber("44");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode("1234567");

            String l_strAccOpenRequestNumber = null;
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher l_institutionVoucher =
                new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(l_variousInformParams);
            l_institutionVoucher.saveBankRegistVoucherHost();
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                StringBuffer l_sbSearchCondition = new StringBuffer();
                l_sbSearchCondition.append(" request_code = ? and ");
                l_sbSearchCondition.append(" institution_code = ? and ");
                l_sbSearchCondition.append(" branch_code = ? and ");
                l_sbSearchCondition.append(" account_code = ? ");
                Object[] l_dataValues = {"GI823", "123", "000", "1234567"};
                List l_lisHostBankTransVoucherRows = l_queryProcessor.doFindAllQuery(HostBankTransVoucherRow.TYPE,
                        l_sbSearchCondition.toString(),
                        l_dataValues);
                
                assertEquals(1,l_lisHostBankTransVoucherRows.size());
                HostBankTransVoucherParams l_hostBankTransVoucherParams =
                    (HostBankTransVoucherParams)l_lisHostBankTransVoucherRows.get(0);
                l_strAccOpenRequestNumber = l_hostBankTransVoucherParams.getOrderRequestNumber();
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            l_variousInformParams.setOrderRequestNumber(l_strAccOpenRequestNumber);
            l_institutionVoucher =
                new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(l_variousInformParams);
            l_institutionVoucher.deleteBankRegistVoucherHost();
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                StringBuffer l_sbSearchCondition = new StringBuffer();
                l_sbSearchCondition.append(" order_request_number = ? and ");
                l_sbSearchCondition.append(" request_code = ? and ");
                l_sbSearchCondition.append(" institution_code = ? and ");
                l_sbSearchCondition.append(" branch_code = ? and ");
                l_sbSearchCondition.append(" account_code = ? ");
                Object[] l_dataValues = {l_strAccOpenRequestNumber, "GI823", "123", "000", "1234567"};
                List l_lisHostBankTransVoucherRows = l_queryProcessor.doFindAllQuery(HostBankTransVoucherRow.TYPE,
                        l_sbSearchCondition.toString(),
                        l_dataValues);
                assertTrue(l_lisHostBankTransVoucherRows.isEmpty());
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * l_informInfoUnit == null
     */
    public void test_validateFinancialInstitutionInfo_C0003()
    {
        final String STR_METHOD_NAME = " test_validateFinancialInstitutionInfo_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode("1234567");
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher l_institutionVoucher =
                new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(l_variousInformParams);
            WEB3InformDetailInfoUnit l_informInfoUnit = null;
            l_institutionVoucher.validateFinancialInstitutionInfo(l_informInfoUnit);
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * l_informInfoUnit != null
     * 引数:連絡情報.コード３ == null
     */
    public void test_validateFinancialInstitutionInfo_C0004()
    {
        final String STR_METHOD_NAME = " test_validateFinancialInstitutionInfo_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setExtCode3(null);
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            TestDBUtility.insertWithDel(l_variousInformParams);
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher l_institutionVoucher =
                new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(l_variousInformParams);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);
            l_institutionVoucher.validateFinancialInstitutionInfo(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02788,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * l_informInfoUnit != null
     * 引数:連絡情報.コード３ != null
     * 引数:連絡情報.コード4 == null
     */
    public void test_validateFinancialInstitutionInfo_C0005()
    {
        final String STR_METHOD_NAME = " test_validateFinancialInstitutionInfo_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setExtCode3("123");
            l_variousInformParams.setExtCode4(null);
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            TestDBUtility.insertWithDel(l_variousInformParams);
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher l_institutionVoucher =
                new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(l_variousInformParams);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);
            l_institutionVoucher.validateFinancialInstitutionInfo(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02789,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * l_informInfoUnit != null
     * 引数:連絡情報.コード３ != null
     * 引数:連絡情報.コード4 == null
     */
    public void test_validateFinancialInstitutionInfo_C0006()
    {
        final String STR_METHOD_NAME = " test_validateFinancialInstitutionInfo_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setExtCode3("123");
            l_variousInformParams.setExtCode4("123");
            l_variousInformParams.setExtDiv2("4");
            l_variousInformParams.setExtDiv3(null);
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            TestDBUtility.insertWithDel(l_variousInformParams);
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher l_institutionVoucher =
                new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(l_variousInformParams);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);
            l_institutionVoucher.validateFinancialInstitutionInfo(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02790,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * l_informInfoUnit != null
     * 引数:連絡情報.コード３ != null
     * 引数:連絡情報.コード4 == null
     */
    public void test_validateFinancialInstitutionInfo_C0007()
    {
        final String STR_METHOD_NAME = " test_validateFinancialInstitutionInfo_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setExtCode3("123");
            l_variousInformParams.setExtCode4("123");
            l_variousInformParams.setExtDiv2("2");
            l_variousInformParams.setExtDiv3("123");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher l_institutionVoucher =
                new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(l_variousInformParams);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);
            l_institutionVoucher.validateFinancialInstitutionInfo(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02790,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * l_informInfoUnit != null
     * 引数:連絡情報.コード３ != null
     * 引数:連絡情報.コード4 == null
     */
    public void test_validateFinancialInstitutionInfo_C0008()
    {
        final String STR_METHOD_NAME = " test_validateFinancialInstitutionInfo_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setExtCode3("123");
            l_variousInformParams.setExtCode4("123");
            l_variousInformParams.setExtDiv2("4");
            l_variousInformParams.setExtDiv3("123");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher l_institutionVoucher =
                new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(l_variousInformParams);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);
            l_institutionVoucher.validateFinancialInstitutionInfo(l_informInfoUnit);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}@
