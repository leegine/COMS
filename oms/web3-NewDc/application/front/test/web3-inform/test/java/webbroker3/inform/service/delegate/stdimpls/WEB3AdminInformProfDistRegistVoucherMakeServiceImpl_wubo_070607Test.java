head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistRegistVoucherMakeServiceImpl_wubo_070607Test.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金登録伝票作成サービスImplテスト(WEB3AdminInformTransferApplyFinancialInstitutionVoucherTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/07 武波(中訊) 新規作成 モデルNo.056
Revision History    : 2007/06/14 周墨洋(中訊) 修正 モデルNo.082、085
*/
package webbroker3.inform.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.DirectDebitParams;
import webbroker3.gentrade.data.FinInstitutionBankParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInfo;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpRequest;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistRegistVoucherMakeService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mqgateway.stdimpls.WEB3DefaultMQSendResultForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

/**
 * (利金・分配金登録伝票作成サービスImplテスト)<BR>
 * 利金・分配金登録伝票作成サービスImplテスト
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminInformProfDistRegistVoucherMakeServiceImpl_wubo_070607Test
    extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformProfDistRegistVoucherMakeServiceImpl_wubo_070607Test.class);

    public WEB3AdminInformProfDistRegistVoucherMakeServiceImpl_wubo_070607Test(String name) {
        super(name);
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
     * 連絡情報 == null
     */
    public void test_validateTransferRegistInfo_C0001()
    {
        final String STR_METHOD_NAME = " test_validateTransferRegistInfo_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3InformDetailInfoUnit l_informDetailInfoUnit = null;
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateTransferRegistInfo(l_informDetailInfoUnit);
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
     * 連絡情報 != null
     * 登録区分 = 3：削除
     * 検索結果が存在しない場合、エラー
     */
    public void test_validateTransferRegistInfo_C0002()
    {
        final String STR_METHOD_NAME = " test_validateTransferRegistInfo_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(DirectDebitParams.TYPE);

            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setExtCode3(null);
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateTransferRegistInfo(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02784,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 登録区分 = 3：削除
     * 検索結果が存在しない場合、エラー
     */
    public void test_validateTransferRegistInfo_C0003()
    {
        final String STR_METHOD_NAME = " test_validateTransferRegistInfo_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(DirectDebitParams.TYPE);
            DirectDebitParams l_directDebitParams = TestDBUtility.getDirectDebitRow();
            l_directDebitParams.setInstitutionCode("12");
            l_directDebitParams.setBranchCode("000");
            l_directDebitParams.setAccountCode("123456");
            l_directDebitParams.setFundCode(null);
            l_directDebitParams.setDesignateDiv("1");
            l_directDebitParams.setComodity(null);
            l_directDebitParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_directDebitParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setInstitutionCode("12");
            l_mainAccount.setBranchCode("000");
            l_mainAccount.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccount);


            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("12");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setExtCode3(null);
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setExtDiv2("1");
            l_variousInformParams.setExtDiv3("");
            l_variousInformParams.setExtDiv5("1");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode("123456");
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateTransferRegistInfo(l_informInfoUnit);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 登録区分 =1：新規
     * 検索結果が存在する場合、エラー
     */
    public void test_validateTransferRegistInfo_C0004()
    {
        final String STR_METHOD_NAME = " test_validateTransferRegistInfo_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(DirectDebitParams.TYPE);
            DirectDebitParams l_directDebitParams = TestDBUtility.getDirectDebitRow();
            l_directDebitParams.setInstitutionCode("12");
            l_directDebitParams.setBranchCode("000");
            l_directDebitParams.setAccountCode("123456");
            l_directDebitParams.setFundCode("12");
            l_directDebitParams.setDesignateDiv("1");
            l_directDebitParams.setComodity("14");
            l_directDebitParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_directDebitParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setInstitutionCode("12");
            l_mainAccount.setBranchCode("000");
            l_mainAccount.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccount);


            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("12");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setExtCode3(null);
            l_variousInformParams.setFundCode("12");
            l_variousInformParams.setExtDiv4("1");
            l_variousInformParams.setExtDiv2("1");
            l_variousInformParams.setExtDiv3("14");
            l_variousInformParams.setExtDiv5("1");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode("123456");
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateTransferRegistInfo(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02783,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 登録区分 =1：新規
     * 検索結果が存在しない場合、エラー
     */
    public void test_validateTransferRegistInfo_C0005()
    {
        final String STR_METHOD_NAME = " test_validateTransferRegistInfo_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(DirectDebitParams.TYPE);

            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("12");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setExtCode3(null);
            l_variousInformParams.setFundCode("12");
            l_variousInformParams.setExtDiv4("1");
            l_variousInformParams.setExtDiv2("1");
            l_variousInformParams.setExtDiv3("123");
            l_variousInformParams.setExtDiv5("1");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateTransferRegistInfo(l_informInfoUnit);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 == null
     */
    public void test_validateProductCode_C0001()
    {
        final String STR_METHOD_NAME = " test_validateProductCode_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3InformDetailInfoUnit l_informDetailInfoUnit = null;
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateProductCode(l_informDetailInfoUnit);
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
     * 連絡情報 != null
     * 引数:連絡情報.区分３ = 2:投資信託 or
     * 引数:連絡情報.区分３ =R: オープン株投コースの場合
     * 引数:連絡情報.コード１ のレングスが7バイト以外
     */
    public void test_validateProductCode_C0002()
    {
        final String STR_METHOD_NAME = " test_validateProductCode_C0002()";
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
            l_variousInformParams.setExtCode1("1");
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setExtDiv3("2");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateProductCode(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 引数:連絡情報.区分３ = 2:投資信託 or
     * 引数:連絡情報.区分３ =R: オープン株投コースの場合
     * 引数:連絡情報.コード１ のレングスが7バイト以外
     */
    public void test_validateProductCode_C0003()
    {
        final String STR_METHOD_NAME = " test_validateProductCode_C0003()";
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
            l_variousInformParams.setExtCode1(null);
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setExtDiv3("R");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateProductCode(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 引数:連絡情報.区分３ = 2:投資信託 or
     * 引数:連絡情報.区分３ =R: オープン株投コースの場合
     * 引数:連絡情報.コード１ のレングスが7バイト
     */
    public void test_validateProductCode_C0004()
    {
        final String STR_METHOD_NAME = " test_validateProductCode_C0004()";
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
            l_variousInformParams.setExtCode1("1111111");
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setExtDiv3("R");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateProductCode(l_informInfoUnit);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 引数:連絡情報.区分３ = ３:公社債の場合、
     * 引数:連絡情報.コード２ のレングスが9バイト以外は例外
     */
    public void test_validateProductCode_C0005()
    {
        final String STR_METHOD_NAME = " test_validateProductCode_C0005()";
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
            l_variousInformParams.setExtCode2("1111111");
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setExtDiv3("3");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateProductCode(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 引数:連絡情報.区分３ = ３:公社債の場合、
     * 引数:連絡情報.コード２ のレングスが9バイト
     * 引数:連絡情報.コード２ がALL０、またはブランクの場合、例外をスロー
     */
    public void test_validateProductCode_C0006()
    {
        final String STR_METHOD_NAME = " test_validateProductCode_C0006()";
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
            l_variousInformParams.setExtCode2("000000000");
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setExtDiv3("3");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateProductCode(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 引数:連絡情報.区分３ = ３:公社債の場合、
     * 引数:連絡情報.コード２ のレングスが9バイト
     * 引数:連絡情報.コード２ がALL０、またはブランクの場合、例外をスロー
     */
    public void test_validateProductCode_C0007()
    {
        final String STR_METHOD_NAME = " test_validateProductCode_C0007()";
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
            l_variousInformParams.setExtCode2("         ");
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setExtDiv3("3");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateProductCode(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 引数:連絡情報.区分３ = ３:公社債の場合、
     * 引数:連絡情報.コード２ のレングスが9バイト
     */
    public void test_validateProductCode_C0008()
    {
        final String STR_METHOD_NAME = " test_validateProductCode_C0008()";
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
            l_variousInformParams.setExtCode2("111111111");
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setExtDiv3("3");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateProductCode(l_informInfoUnit);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 引数:連絡情報.区分３がnull または １）、２）以外の場合、
     * 引数:連絡情報.コード１ != null　@または　@引数:連絡情報.コード２ != nullの場合、例外をスロー
     */
    public void test_validateProductCode_C0009()
    {
        final String STR_METHOD_NAME = " test_validateProductCode_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setExtCode1(null);
            l_variousInformParams.setExtCode3(null);
            l_variousInformParams.setExtCode2("111111111");
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setExtDiv3(null);
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateProductCode(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 引数:連絡情報.区分３がnull または １）、２）以外の場合、
     * 引数:連絡情報.コード１ != null　@または　@引数:連絡情報.コード２ != nullの場合、例外をスロー
     */
    public void test_validateProductCode_C0010()
    {
        final String STR_METHOD_NAME = " test_validateProductCode_C0010()";
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
            l_variousInformParams.setExtCode1("111111111");
            l_variousInformParams.setExtCode2(null);
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setExtDiv3("B");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateProductCode(l_informInfoUnit);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02785,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 連絡情報 != null
     * 引数:連絡情報.区分３がnull または １）、２）以外の場合、
     * 引数:連絡情報.コード１ == null　@または　@引数:連絡情報.コード２ == nullの場合、例外をスロー
     */
    public void test_validateProductCode_C0011()
    {
        final String STR_METHOD_NAME = " test_validateProductCode_C0011()";
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
            l_variousInformParams.setExtCode1(null);
            l_variousInformParams.setExtCode2(null);
            l_variousInformParams.setExtDiv4("3");
            l_variousInformParams.setExtDiv3("B");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setAccountCode(null);
            WEB3InformDetailInfoUnit l_informInfoUnit = new WEB3InformDetailInfoUnit(l_variousInformParams);

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.validateProductCode(l_informInfoUnit);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * コードが存在しない場合
     */
    public void test_getFinancialInstitutionInfo_C0001()
    {
        final String STR_METHOD_NAME = " test_getFinancialInstitutionInfo_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(FinInstitutionBankParams.TYPE);
            WEB3AdminInformProfDistSellTransSrcInfo l_financialInstitutionInfo =
                new WEB3AdminInformProfDistSellTransSrcInfo();
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_informProfDistRegistVoucherMakeServiceImpl.getFinancialInstitutionInfo(
                "", "", l_financialInstitutionInfo);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01314,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * コードが存在する場合
     */
    public void test_getFinancialInstitutionInfo_C0002()
    {
        final String STR_METHOD_NAME = " test_getFinancialInstitutionInfo_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(FinInstitutionBankParams.TYPE);
            FinInstitutionBankParams l_finInstitutionBankParams = TestDBUtility.getFinInstitutionBankRow();
            l_finInstitutionBankParams.setFinInstitutionCode("1234");
            l_finInstitutionBankParams.setFinBranchCode("123");
            l_finInstitutionBankParams.setFinBranchName("123123");
            l_finInstitutionBankParams.setFinBranchNameKana("123123123");
            l_finInstitutionBankParams.setFinInstitutionName("123133");
            l_finInstitutionBankParams.setFinInstitutionNameKana("123123111");
            l_finInstitutionBankParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_finInstitutionBankParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_finInstitutionBankParams);

            WEB3AdminInformProfDistSellTransSrcInfo l_financialInstitutionInfo =
                new WEB3AdminInformProfDistSellTransSrcInfo();
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            l_financialInstitutionInfo =
                l_informProfDistRegistVoucherMakeServiceImpl.getFinancialInstitutionInfo(
                    "1234", "123", l_financialInstitutionInfo);
            assertEquals("123123", l_financialInstitutionInfo.financialBranchName);
            assertEquals("123123123", l_financialInstitutionInfo.financialBranchNameKana);
            assertEquals("123133", l_financialInstitutionInfo.financialInstitutionName);
            assertEquals("123123111", l_financialInstitutionInfo.financialInstitutionNameKana);

            FinInstitutionBankParams l_finInstitutionBankParams1 = TestDBUtility.getFinInstitutionBankRow();
            l_finInstitutionBankParams1.setFinInstitutionCode("2222");
            l_finInstitutionBankParams1.setFinBranchCode("111");
            l_finInstitutionBankParams1.setFinBranchName("333");
            l_finInstitutionBankParams1.setFinBranchNameKana("4444");
            l_finInstitutionBankParams1.setFinInstitutionName("555");
            l_finInstitutionBankParams1.setFinInstitutionNameKana("6666");
            l_finInstitutionBankParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_finInstitutionBankParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_finInstitutionBankParams1);

            l_financialInstitutionInfo =
                l_informProfDistRegistVoucherMakeServiceImpl.getFinancialInstitutionInfo(
                    "2222", "111", l_financialInstitutionInfo);
            assertEquals("333", l_financialInstitutionInfo.financialBranchName);
            assertEquals("4444", l_financialInstitutionInfo.financialBranchNameKana);
            assertEquals("555", l_financialInstitutionInfo.financialInstitutionName);
            assertEquals("6666", l_financialInstitutionInfo.financialInstitutionNameKana);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * トリガー発行区分 == true or 作成状況 != 1:作成済の場合、
     */
    public void test_validateVoucherCanc_C0001()
    {
        final String STR_METHOD_NAME = " test_validateVoucherCanc_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                    "validateVoucherCanc", new Class[]{String.class, boolean.class});
            method.setAccessible(true);
            method.invoke(l_informProfDistRegistVoucherMakeServiceImpl, new Object[]{"1", Boolean.TRUE});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02798,
                ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * トリガー発行区分 == true or 作成状況 != 1:作成済の場合、
     */
    public void test_validateVoucherCanc_C0002()
    {
        final String STR_METHOD_NAME = " test_validateVoucherCanc_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                    "validateVoucherCanc", new Class[]{String.class, boolean.class});
            method.setAccessible(true);
            method.invoke(l_informProfDistRegistVoucherMakeServiceImpl, new Object[]{"2", Boolean.FALSE});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02798,
                ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * トリガー発行区分 == false or 作成状況 == 1:作成済の場合、
     */
    public void test_validateVoucherCanc_C0003()
    {
        final String STR_METHOD_NAME = " test_validateVoucherCanc_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                    "validateVoucherCanc", new Class[]{String.class, boolean.class});
            method.setAccessible(true);
            method.invoke(l_informProfDistRegistVoucherMakeServiceImpl, new Object[]{"1", Boolean.FALSE});
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数:トリガー発行区分 == true の場合
     * 作成状況が、0：未作成、4：受付エラー のいずれでもない場合、
     */
    public void test_validateVoucherChg_C0001()
    {
        final String STR_METHOD_NAME = " test_validateVoucherChg_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        { 
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                    "validateVoucherChg", new Class[]{String.class, boolean.class});
            method.setAccessible(true);
            method.invoke(l_informProfDistRegistVoucherMakeServiceImpl, new Object[]{"1", Boolean.TRUE});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02786,
                ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数:トリガー発行区分 == true の場合
     * 作成状況が4：受付エラー の場合、
     */
    public void test_validateVoucherChg_C0002()
    {
        final String STR_METHOD_NAME = " test_validateVoucherChg_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        { 
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                    "validateVoucherChg", new Class[]{String.class, boolean.class});
            method.setAccessible(true);
            method.invoke(l_informProfDistRegistVoucherMakeServiceImpl, new Object[]{"4", Boolean.TRUE});
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数:トリガー発行区分 == true の場合
     * 作成状況が0：未作成 の場合、
     */
    public void test_validateVoucherChg_C0003()
    {
        final String STR_METHOD_NAME = " test_validateVoucherChg_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        { 
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                    "validateVoucherChg", new Class[]{String.class, boolean.class});
            method.setAccessible(true);
            method.invoke(l_informProfDistRegistVoucherMakeServiceImpl, new Object[]{"0", Boolean.TRUE});
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数:トリガー発行区分 == false の場合
     * 作成状況が、0：未作成、4：受付エラー、1：作成済み のいずれでもない場合、
     */
    public void test_validateVoucherChg_C0004()
    {
        final String STR_METHOD_NAME = " test_validateVoucherChg_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        { 
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                    "validateVoucherChg", new Class[]{String.class, boolean.class});
            method.setAccessible(true);
            method.invoke(l_informProfDistRegistVoucherMakeServiceImpl, new Object[]{"2", Boolean.FALSE});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02787,
                ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数:トリガー発行区分 == false の場合
     * 作成状況が、0：未作成、 の場合、
     */
    public void test_validateVoucherChg_C0005()
    {
        final String STR_METHOD_NAME = " test_validateVoucherChg_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        { 
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                    "validateVoucherChg", new Class[]{String.class, boolean.class});
            method.setAccessible(true);
            method.invoke(l_informProfDistRegistVoucherMakeServiceImpl, new Object[]{"0", Boolean.FALSE});
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数:トリガー発行区分 == false の場合
     * 作成状況が4：受付エラ の場合、
     */
    public void test_validateVoucherChg_C0006()
    {
        final String STR_METHOD_NAME = " test_validateVoucherChg_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        { 
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                    "validateVoucherChg", new Class[]{String.class, boolean.class});
            method.setAccessible(true);
            method.invoke(l_informProfDistRegistVoucherMakeServiceImpl, new Object[]{"4", Boolean.FALSE});
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数:トリガー発行区分 == false の場合
     * 作成状況が、0：未作成、4：受付エラー、1：作成済み のいずれでもない場合、
     */
    public void test_validateVoucherChg_C0007()
    {
        final String STR_METHOD_NAME = " test_validateVoucherChg_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        { 
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                    "validateVoucherChg", new Class[]{String.class, boolean.class});
            method.setAccessible(true);
            method.invoke(l_informProfDistRegistVoucherMakeServiceImpl, new Object[]{"1", Boolean.FALSE});
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * トリガー発行区分 == true or 作成状況 != 1:作成済の場合、
     */
    public void test_updateVariousInformChgInfo_C0001()
    {
        final String STR_METHOD_NAME = " test_updateInformDetailInfoUnitChgInfo_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getLoginInfo",
//                    new Class[] {},
//                    l_loginInfoImpl
//                    );
//
//            TestDBUtility.deleteAll(AdministratorParams.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.insertWithDel(l_administratorParams);
//
//            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");

            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                "updateVariousInformChgInfo",
                new Class[]{VariousInformParams.class, VariousInformParams.class, String.class, String.class});
            method.setAccessible(true);

            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("000");
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setInformDiv("12");
            l_variousInformParams.setRequestNumber("123");
            l_variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_variousInformParams);

            VariousInformParams l_variousInformParams1 = TestDBUtility.getVariousInformRow();
            l_variousInformParams1.setBranchCode("000");
            l_variousInformParams1.setInstitutionCode("123");
            l_variousInformParams1.setInformDiv("12");
            l_variousInformParams1.setRequestNumber("121");
            l_variousInformParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070608","yyyyMMdd"));
            l_variousInformParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070608","yyyyMMdd"));

            method.invoke(l_informProfDistRegistVoucherMakeServiceImpl,
                new Object[]{l_variousInformParams, l_variousInformParams1, "555", "44"});
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数:振替区分 == 1:銀行振込の場合、true を返却
     * 1:銀行振込以外の場合、false を返却
     */
    public void test_isBankRegistVoucher_C0001()
    {
        final String STR_METHOD_NAME = " test_isFinancialInstitutionPostVoucher_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                "isBankRegistVoucher",
                new Class[]{String.class});
            method.setAccessible(true);
            Boolean l_object = (Boolean)method.invoke(l_informProfDistRegistVoucherMakeServiceImpl,
                    new Object[]{"1"});
            assertEquals(Boolean.TRUE, l_object);

            l_object = (Boolean)method.invoke(l_informProfDistRegistVoucherMakeServiceImpl,
                    new Object[]{"5"});
            assertEquals(Boolean.FALSE, l_object);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数.リクエストデータ.商品 = null の場合
     * 引数.リクエストデータ.銘柄コード = null の場合
     */
    public void test_createQueryDataContainer_C0001()
    {
        final String STR_METHOD_NAME = " test_createQueryDataContainer_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminInformProfDistVoucherMakeInpRequest.class});
            method.setAccessible(true);
            WEB3AdminInformProfDistVoucherMakeInpRequest l_request =
                new WEB3AdminInformProfDistVoucherMakeInpRequest();
            l_request.product = null;
            l_request.productCode = null;
            l_request.branchCode = "123";
            l_request.accountCode = "11";
            l_request.specifyDiv = "22";
            l_request.transferDiv = "33";
            Object[] l_object = (Object[])method.invoke(l_informProfDistRegistVoucherMakeServiceImpl,
                    new Object[]{"1", l_request});
            assertEquals("1", l_object[0]);
            assertEquals("123", l_object[1]);
            assertEquals("11", l_object[2]);
            assertEquals("22", l_object[3]);
            assertEquals("33", l_object[4]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数.リクエストデータ.商品 = null の場合
     * 引数.リクエストデータ.銘柄コード = null の場合
     */
    public void test_createQueryDataContainer_C0002()
    {
        final String STR_METHOD_NAME = " test_createQueryDataContainer_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminInformProfDistVoucherMakeInpRequest.class});
            method.setAccessible(true);
            WEB3AdminInformProfDistVoucherMakeInpRequest l_request =
                new WEB3AdminInformProfDistVoucherMakeInpRequest();
            l_request.product = "10";
            l_request.productCode = "20";
            l_request.branchCode = "123";
            l_request.accountCode = "11";
            l_request.specifyDiv = "22";
            l_request.transferDiv = "33";
            Object[] l_object = (Object[])method.invoke(l_informProfDistRegistVoucherMakeServiceImpl,
                    new Object[]{"1", l_request});
            assertEquals("1", l_object[0]);
            assertEquals("123", l_object[1]);
            assertEquals("11", l_object[2]);
            assertEquals("22", l_object[3]);
            assertEquals("1", l_object[4]);
            assertEquals("20", l_object[5]);
            assertEquals("33", l_object[6]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数.リクエストデータ.商品 = null の場合
     * 引数.リクエストデータ.銘柄コード = null の場合
     */
    public void test_createQueryString_C0001()
    {
        final String STR_METHOD_NAME = " test_createQueryString_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminInformProfDistVoucherMakeInpRequest.class});
            method.setAccessible(true);
            WEB3AdminInformProfDistVoucherMakeInpRequest l_request =
                new WEB3AdminInformProfDistVoucherMakeInpRequest();
            l_request.product = null;
            l_request.productCode = null;
            l_request.branchCode = "123";
            l_request.accountCode = "11";
            l_request.specifyDiv = "22";
            l_request.transferDiv = "33";
            String l_strMethod = (String)method.invoke(l_informProfDistRegistVoucherMakeServiceImpl,
                    new Object[]{l_request});
            StringBuffer l_sbSearchCondition = new StringBuffer();
            l_sbSearchCondition.append(" institution_code = ? ");
            l_sbSearchCondition.append(" and branch_code = ? ");
            l_sbSearchCondition.append(" and account_code like ? || '%' ");
            l_sbSearchCondition.append(" and designate_div = ? ");
            l_sbSearchCondition.append(" and comodity is null ");
            l_sbSearchCondition.append(" and fund_code is null ");
            l_sbSearchCondition.append(" and transfer_div = ? ");
            assertEquals(l_sbSearchCondition.toString(), l_strMethod);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 引数.リクエストデータ.商品 = null の場合
     * 引数.リクエストデータ.銘柄コード = null の場合
     */
    public void test_createQueryString_C0002()
    {
        final String STR_METHOD_NAME = " test_createQueryString_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_informProfDistRegistVoucherMakeServiceImpl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            Method method = WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminInformProfDistVoucherMakeInpRequest.class});
            method.setAccessible(true);
            WEB3AdminInformProfDistVoucherMakeInpRequest l_request =
                new WEB3AdminInformProfDistVoucherMakeInpRequest();
            l_request.product = "123";
            l_request.productCode = "123";
            l_request.branchCode = "123";
            l_request.accountCode = "11";
            l_request.specifyDiv = "22";
            l_request.transferDiv = "33";
            String l_strMethod = (String)method.invoke(l_informProfDistRegistVoucherMakeServiceImpl,
                    new Object[]{l_request});
            StringBuffer l_sbSearchCondition = new StringBuffer();
            l_sbSearchCondition.append(" institution_code = ? ");
            l_sbSearchCondition.append(" and branch_code = ? ");
            l_sbSearchCondition.append(" and account_code like ? || '%' ");
            l_sbSearchCondition.append(" and designate_div = ? ");
            l_sbSearchCondition.append(" and comodity like ? || '%' ");
            l_sbSearchCondition.append(" and fund_code = ? ");
            l_sbSearchCondition.append(" and transfer_div = ? ");
            assertEquals(l_sbSearchCondition.toString(), l_strMethod);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

//    /**
//     * 引数.リクエストデータ.商品 = null の場合
//     * 引数.リクエストデータ.銘柄コード = null の場合
//     */
//    public void test_submitMarketTrigger_C0001()
//    {
//        final String STR_METHOD_NAME = " test_submitMarketTrigger_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            MOCK_MANAGER.setIsMockUsed(true);
//            WEB3AdminInformProfDistVoucherChgCmpRequest l_request =
//                new WEB3AdminInformProfDistVoucherChgCmpRequest();
//            l_request.branchCode = "624";
//            l_request.informType = "1";
//            l_request.requestNumber = "234";
//            WEB3InformDetailInfoUnit l_unit = new WEB3InformDetailInfoUnit();
//            l_unit.informType = "1";
//            l_unit.branchCode = "624";
//            l_unit.accountNumber = "123";
//            l_unit.institutionCode = "60";
//            l_unit.num1 = "1";
//            l_unit.num2 = "2";
//            l_unit.num3 = "3";
//            l_unit.num4 = "4";
//            l_unit.num5 = "5";
//            l_unit.num6 = "6";
//            l_unit.num7 = "7";
//            l_unit.num8 = "8";
//            l_unit.num9 = "9";
//            l_unit.num10 = "10";
//            l_unit.num11 = "11";
//            l_unit.num12 = "12";
//            l_unit.num13 = "13";
//            l_unit.num14 = "14";
//            l_unit.num15 = "15";
//            l_unit.num16 = "16";
//            l_unit.num17 = "17";
//            l_unit.num18 = "18";
//            l_unit.num19 = "19";
//            l_unit.num20 = "20";
//            l_unit.num21 = "21";
//            l_unit.num22 = "22";
//            l_unit.num23 = "23";
//            l_unit.num24 = "24";
//            l_unit.num25 = "25";
//            l_unit.num26 = "26";
//            l_unit.num27 = "27";
//            l_unit.num28 = "28";
//            l_unit.num29 = "29";
//            l_unit.num30 = "30";
//            l_unit.div4 = "4";
//            l_unit.div2 = "4";
//            l_unit.div3 = "2";
//            l_unit.div5 = "1";
//            l_unit.code1 = "1234567";
//            l_unit.code3 = "2";
//            l_unit.code4 = "3";
//            l_request.informInfoUnit = l_unit;
//            TestDBUtility.deleteAll(AdministratorParams.TYPE);
//            TestDBUtility.deleteAll(VariousInformParams.TYPE);
//            AdministratorParams l_administratorParams = new AdministratorParams();
//            l_administratorParams.setAdministratorCode("123456789");
//            l_administratorParams.setAdministratorId(123456l);
//            l_administratorParams.setBranchCode("624");
//            l_administratorParams.setInstitutionCode("60");
//            l_administratorParams.setInstitutionId(60L);
//            l_administratorParams.setLoginId(123456l);
//            l_administratorParams.setPermissionLevel("01");
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
//
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
//
//            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
//            l_adminPermissionParams.setInstitutionCode("60");
//            l_adminPermissionParams.setPermissionLevel("01");
//            l_adminPermissionParams.setTransactionCategory("A0105");
//            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
//            l_adminPermissionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_adminPermissionParams.setUpdateTimestamp(GtlUtils.getSystemTimestamp());
//
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//
//            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
//            l_variousInformParams.setBranchCode("624");
//            l_variousInformParams.setInstitutionCode("60");
//            l_variousInformParams.setInformDiv("1");
//            l_variousInformParams.setRequestNumber("234");
//            l_variousInformParams.setStatus("4");
//            l_variousInformParams.setAccountCode("123");
//            l_variousInformParams.setRequestCode("123");
//            l_variousInformParams.setExtDiv5("5");
//            l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_variousInformParams);
//
//            TradingTimeParams l_tradingTimeParmas = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParmas.setInstitutionCode("0D");
//            l_tradingTimeParmas.setBranchCode("123");
//            l_tradingTimeParmas.setMarketCode("0");
//            l_tradingTimeParmas.setTradingTimeType("35");
//            l_tradingTimeParmas.setProductCode("0");
//            l_tradingTimeParmas.setBizDateType("1");
//            l_tradingTimeParmas.setStartTime("000000");
//            l_tradingTimeParmas.setEndTime("235959");
//            l_tradingTimeParmas.setSubmitMarketTrigger("1");
//            l_tradingTimeParmas.setEnableOrder("0");
//            l_tradingTimeParmas.setBizdateCalcParameter("1");
//            TestDBUtility.insertWithDel(l_tradingTimeParmas);
//
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            MainAccountParams l_mainAccountRow = TestDBUtility.getMainAccountRow();
//            l_mainAccountRow.setBranchCode("123");
//            l_mainAccountRow.setInstitutionCode("0D");
//            l_mainAccountRow.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(l_mainAccountRow);
//
//            TestDBUtility.deleteAll(BranchParams.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchCode("123");
//            l_branchParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionCode("0D");
//            l_institutionParams.setInstitutionId(33);
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            FinInstitutionBankParams l_finInstitutionBankParams = new FinInstitutionBankParams();
//            l_finInstitutionBankParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_finInstitutionBankParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_finInstitutionBankParams.setFinBranchCode("3");
//            l_finInstitutionBankParams.setFinBranchName("sss");
//            l_finInstitutionBankParams.setFinBranchNameKana("ddd");
//            l_finInstitutionBankParams.setFinInstitutionCode("2");
//            l_finInstitutionBankParams.setFinInstitutionName("fff");
//            l_finInstitutionBankParams.setFinInstitutionNameKana("ggg");
//            TestDBUtility.insertWithDel(l_finInstitutionBankParams);
//            WEB3DefaultMQSendResultForMock l_mqSendResult = WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
////            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
////                "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
////                "send",
////                new Class[] {WEB3MQMessageSpec.class},
////                new Object[]{l_mqSendResult});
//            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
//            MOCK_MANAGER.setIsMockUsed(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
//                new Class[] {}, new Long(333812512246L));
//            WEB3AdminInformProfDistRegistVoucherMakeService l_WEB3AdminInformProfDistRegistVoucherMakeService =
//                (WEB3AdminInformProfDistRegistVoucherMakeService)Services.getService(WEB3AdminInformProfDistRegistVoucherMakeService.class);
//
//            WEB3GenResponse l_response =
//                l_WEB3AdminInformProfDistRegistVoucherMakeService.execute(l_request);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//    }
}
@
