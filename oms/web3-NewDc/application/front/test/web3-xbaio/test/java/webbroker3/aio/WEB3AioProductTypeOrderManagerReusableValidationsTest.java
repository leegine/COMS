head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.33.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioProductTypeOrderManagerReusableValidationsTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;

import test.util.JunitTestBase;
import test.util.TestDBUtility;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftAccountOpenStatusRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.AccOpenDivParams;
import webbroker3.gentrade.data.AccOpenDivRow;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioProductTypeOrderManagerReusableValidationsTest extends JunitTestBase
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioProductTypeOrderManagerReusableValidations.class);

	public WEB3AioProductTypeOrderManagerReusableValidationsTest(String name)
	{
		super(name);
		// TODO Auto-generated constructor stub
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
	 * 引数.補助口座 == null
	 */
	public void testValidateCFDChangePoss_C0001()
	{
		final String STR_METHOD_NAME = "testValidateCFDChangePoss_C0001()";
        log.entering(STR_METHOD_NAME);

        SubAccount l_subAccount = null;

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	l_reusableValidations.validateCFDChangePoss(l_subAccount, "10");
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }

        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * getFX顧客()でFX顧客Paramsが取得できなかった場合(CFD口座未開設）、例外をthrowする。
	 */
	public void testValidateCFDChangePoss_C0002()
	{
		final String STR_METHOD_NAME = "testValidateCFDChangePoss_C0002()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("11");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        	l_reusableValidations.validateCFDChangePoss(l_subAccount, "10");
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01866);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }

        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * getFX顧客()で取得したFX顧客Params.FX口座区分 = ”2：振替停止”の場合、例外をthrowする。 
	 */
	public void testValidateCFDChangePoss_C0003()
	{
		final String STR_METHOD_NAME = "testValidateCFDChangePoss_C0003()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("2");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        	l_reusableValidations.validateCFDChangePoss(l_subAccount, "10");
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02440);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }

        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * 　@getFX顧客()で取得したFX顧客Params.FX口座区分 != ”1：開設済”の場合、例外をthrowする 
	 */
	public void testValidateCFDChangePoss_C0004()
	{
		final String STR_METHOD_NAME = "testValidateCFDChangePoss_C0004()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("9");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        	l_reusableValidations.validateCFDChangePoss(l_subAccount, "10");
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01867);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }

        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * 　@部店用プリファ@レンステーブルからレコードを取得できなかった
	 * 顧客.MRF口座開設区分 != ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)、例外をthrowする。 
	 */
	public void testValidateCFDChangePoss_C0005()
	{
		final String STR_METHOD_NAME = "testValidateCFDChangePoss_C0005()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setMrfAccOpenDiv("1");
        	l_mainAccountParams.setCfdAccOpenDiv("0");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        	BranchPreferencesParams l_branchPreferencesParams =
        		TestDBUtility.getBranchPreferencesRow();
        	l_branchPreferencesParams.setBranchId(33381L);
        	l_branchPreferencesParams.setName("fx.deliverydate.insert.check");
        	l_branchPreferencesParams.setNameSerialNo(2);
        	TestDBUtility.insertWithDel(l_branchPreferencesParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        	l_reusableValidations.validateCFDChangePoss(l_subAccount, "10");
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01868);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }

        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * 取得したプリファ@レンス値 !="受渡日をセットしない"の場合
	 * 顧客.MRF口座開設区分 != ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)
	 * 顧客.CFD口座開設区分 != ”口座開設” の場合、例外をthrowする。
	 */
	public void testValidateCFDChangePoss_C0006()
	{
		final String STR_METHOD_NAME = "testValidateCFDChangePoss_C0006()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setMrfAccOpenDiv("1");
        	l_mainAccountParams.setCfdAccOpenDiv("0");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        	BranchPreferencesParams l_branchPreferencesParams =
        		TestDBUtility.getBranchPreferencesRow();
        	l_branchPreferencesParams.setBranchId(33381L);
        	l_branchPreferencesParams.setName("fx.deliverydate.insert.check");
        	l_branchPreferencesParams.setNameSerialNo(1);
        	l_branchPreferencesParams.setValue("1");
        	TestDBUtility.insertWithDel(l_branchPreferencesParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        	l_reusableValidations.validateCFDChangePoss(l_subAccount, "10");
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01866);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }

        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * 取得したプリファ@レンス値=="受渡日をセットしない"の場合、
	 * 引数.補助口座.getBranch().isFX_MRF口座開設チェック実施()== false の場合は、２）－３の処理をスキップする。
	 * 顧客.MRF口座開設区分 != ”0：DEFAULT(口座なし)”
	 * 顧客.CFD口座開設区分 != ”口座開設” の場合 
	 */
	public void testValidateCFDChangePoss_C0007()
	{
		final String STR_METHOD_NAME = "testValidateCFDChangePoss_C0007()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setMrfAccOpenDiv("1");
        	l_mainAccountParams.setCfdAccOpenDiv("0");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        	BranchPreferencesParams l_branchPreferencesParams =
        		TestDBUtility.getBranchPreferencesRow();
        	l_branchPreferencesParams.setBranchId(33381L);
        	l_branchPreferencesParams.setName("fx.deliverydate.insert.check");
        	l_branchPreferencesParams.setNameSerialNo(1);
        	l_branchPreferencesParams.setValue("1");
        	TestDBUtility.insertWithDel(l_branchPreferencesParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        	l_reusableValidations.validateCFDChangePoss(l_subAccount, "10");
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01866);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }

        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * 取得したプリファ@レンス値=="受渡日をセットしない"の場合、
	 * 引数.補助口座.getBranch().isFX_MRF口座開設チェック実施()== true の場合は、２）－３の処理をスキップする。
	 * 顧客.MRF口座開設区分 != ”0：DEFAULT(口座なし)”
	 * 顧客.CFD口座開設区分 != ”口座開設” の場合 
	 */
	public void testValidateCFDChangePoss_C0008()
	{
		final String STR_METHOD_NAME = "testValidateCFDChangePoss_C0008()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setMrfAccOpenDiv("1");
        	l_mainAccountParams.setCfdAccOpenDiv("0");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        	BranchPreferencesParams l_branchPreferencesParams =
        		TestDBUtility.getBranchPreferencesRow();
        	l_branchPreferencesParams.setBranchId(33381L);
        	l_branchPreferencesParams.setName("fx.deliverydate.insert.check");
        	l_branchPreferencesParams.setNameSerialNo(1);
        	l_branchPreferencesParams.setValue("0");
        	TestDBUtility.insertWithDel(l_branchPreferencesParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        	l_reusableValidations.validateCFDChangePoss(l_subAccount, "10");
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01868);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }

        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * 取得したプリファ@レンス値=="受渡日をセットしない"の場合、
	 * 引数.補助口座.getBranch().isFX_MRF口座開設チェック実施()== true の場合は、２）－３の処理をスキップする。
	 * 顧客.MRF口座開設区分 == ”0：DEFAULT(口座なし)”
	 * 顧客.CFD口座開設区分 == ”口座開設” の場合 
	 */
	public void testValidateCFDChangePoss_C0009()
	{
		final String STR_METHOD_NAME = "testValidateCFDChangePoss_C0009()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setMrfAccOpenDiv("0");
        	l_mainAccountParams.setCfdAccOpenDiv("1");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        	BranchPreferencesParams l_branchPreferencesParams =
        		TestDBUtility.getBranchPreferencesRow();
        	l_branchPreferencesParams.setBranchId(33381L);
        	l_branchPreferencesParams.setName("fx.deliverydate.insert.check");
        	l_branchPreferencesParams.setNameSerialNo(1);
        	l_branchPreferencesParams.setValue("0");
        	TestDBUtility.insertWithDel(l_branchPreferencesParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        	l_reusableValidations.validateCFDChangePoss(l_subAccount, "10");

            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
	}

	/**
	 * getFX顧客()でFX顧客Paramsが取得できた場合
	 * 引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）の場合
	 */
	public void testValidateFXAccOpenPossible_C0001()
	{
		final String STR_METHOD_NAME = "testValidateFXAccOpenPossible_C0001()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setMrfAccOpenDiv("0");
        	l_mainAccountParams.setCfdAccOpenDiv("1");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
        	CompFxConditionParams l_compFxConditionParams =
        		TestDBUtility.getCompFxConditionRow();
        	l_compFxConditionParams.setBranchCode("381");
        	l_compFxConditionParams.setFxSystemCode("10");
        	l_compFxConditionParams.setFxSystemDiv("2");
        	TestDBUtility.insertWithDel(l_compFxConditionParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            l_reusableValidations.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03133, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
        	log.debug(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * getFX顧客()でFX顧客Paramsが取得できた場合
	 * 引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）以外の場合
	 */
	public void testValidateFXAccOpenPossible_C0002()
	{
		final String STR_METHOD_NAME = "testValidateFXAccOpenPossible_C0002()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setMrfAccOpenDiv("0");
        	l_mainAccountParams.setCfdAccOpenDiv("1");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
        	CompFxConditionParams l_compFxConditionParams =
        		TestDBUtility.getCompFxConditionRow();
        	l_compFxConditionParams.setBranchCode("381");
        	l_compFxConditionParams.setFxSystemCode("10");
        	l_compFxConditionParams.setFxSystemDiv("1");
        	TestDBUtility.insertWithDel(l_compFxConditionParams);
        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            l_reusableValidations.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02423, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
        	log.debug(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * getFX顧客()でFX顧客Paramsが取得できない場合
	 * 引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）の場合
	 * 引数.会社別FXシステム条件Params.オンライン口座開設実施区分 == ０：オンライン口座開設未実施
     * FXデータ制御サービス.getGFT口座開設状況()レコードが取得できた
	 */
	public void testValidateFXAccOpenPossible_C0003()
	{
		final String STR_METHOD_NAME = "testValidateFXAccOpenPossible_C0003()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setMrfAccOpenDiv("0");
        	l_mainAccountParams.setCfdAccOpenDiv("1");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("9");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
        	CompFxConditionParams l_compFxConditionParams =
        		TestDBUtility.getCompFxConditionRow();
        	l_compFxConditionParams.setBranchCode("381");
        	l_compFxConditionParams.setFxSystemCode("10");
        	l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setOnlineAccOpen("0");
        	TestDBUtility.insertWithDel(l_compFxConditionParams);


        	TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        	BranchPreferencesParams l_branchPreferencesParams =
        		TestDBUtility.getBranchPreferencesRow();
        	l_branchPreferencesParams.setBranchId(33381L);
        	l_branchPreferencesParams.setName("fx.deliverydate.insert.check");
        	l_branchPreferencesParams.setNameSerialNo(1);
        	l_branchPreferencesParams.setValue("1");
        	TestDBUtility.insertWithDel(l_branchPreferencesParams);

        	TestDBUtility.deleteAll(GftAccountOpenStatusRow.TYPE);
        	GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
        		TestDBUtility.getGftAccountOpenStatusRow();
        	l_gftAccountOpenStatusParams.setAccountCode("2512246");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("381");
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("0");
        	TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            l_reusableValidations.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03133, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
        	log.debug(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * FXデータ制御サービス.getGFT口座開設状況()にてレコードが取得できた場合
	 * FXデータ制御サービス.get会社別FXシステム条件()の戻り値.FXシステム区分==2（CFDシステム）の場合
	 * FX口座開設処理中として例外をthrowする。
	 */
	public void testValidateFXAccOpenPossible_C0004()
	{
		final String STR_METHOD_NAME = "testValidateFXAccOpenPossible_C0004()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setMrfAccOpenDiv("0");
        	l_mainAccountParams.setCfdAccOpenDiv("1");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("9");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
        	CompFxConditionParams l_compFxConditionParams =
        		TestDBUtility.getCompFxConditionRow();
        	l_compFxConditionParams.setBranchCode("381");
        	l_compFxConditionParams.setFxSystemCode("10");
        	l_compFxConditionParams.setFxSystemDiv("1");
            l_compFxConditionParams.setOnlineAccOpen("0");
        	TestDBUtility.insertWithDel(l_compFxConditionParams);


        	TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        	BranchPreferencesParams l_branchPreferencesParams =
        		TestDBUtility.getBranchPreferencesRow();
        	l_branchPreferencesParams.setBranchId(33381L);
        	l_branchPreferencesParams.setName("fx.deliverydate.insert.check");
        	l_branchPreferencesParams.setNameSerialNo(1);
        	l_branchPreferencesParams.setValue("1");
        	TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusRow.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("2512246");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("381");
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("0");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            l_reusableValidations.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02423, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
        	log.debug(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
	}

	/**
	 * 正常
	 */
	public void testValidateFXAccOpenPossible_C0005()
	{
		final String STR_METHOD_NAME = "testValidateFXAccOpenPossible_C0005()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
        	new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setMrfAccOpenDiv("0");
        	l_mainAccountParams.setCfdAccOpenDiv("1");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
        	CompFxConditionParams l_compFxConditionParams =
        		TestDBUtility.getCompFxConditionRow();
        	l_compFxConditionParams.setBranchCode("381");
        	l_compFxConditionParams.setFxSystemCode("9");
        	l_compFxConditionParams.setFxSystemDiv("0");
            l_compFxConditionParams.setOnlineAccOpen("0");
        	TestDBUtility.insertWithDel(l_compFxConditionParams);


        	TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        	BranchPreferencesParams l_branchPreferencesParams =
        		TestDBUtility.getBranchPreferencesRow();
        	l_branchPreferencesParams.setBranchId(33381L);
        	l_branchPreferencesParams.setName("fx.deliverydate.insert.check");
        	l_branchPreferencesParams.setNameSerialNo(1);
        	l_branchPreferencesParams.setValue("1");
        	TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusRow.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("2512246");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("012");
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("0");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            l_reusableValidations.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);

        }
        catch (Exception l_ex)
        {
        	log.debug(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
	}

    /**
     * 正常
     */
    public void testValidateFXAccOpenPossible_C0006()
    {
        final String STR_METHOD_NAME = "testValidateFXAccOpenPossible_C0006()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations =
            new WEB3AioProductTypeOrderManagerReusableValidations();

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setMrfAccOpenDiv("0");
            l_mainAccountParams.setCfdAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("2512246");
            l_fxAccountParams.setFxSystemCode("10");
            l_fxAccountParams.setFxAccountDiv("1");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("9");
            l_compFxConditionParams.setFxSystemDiv("0");
            l_compFxConditionParams.setOnlineAccOpen("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);


            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381L);
            l_branchPreferencesParams.setName("fx.deliverydate.insert.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusRow.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("2512246");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("012");
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("0");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            l_reusableValidations.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);

        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * １）口座開設チェック
     * getFX顧客()でFX顧客Paramsが取得できなかった場合(FX口座未開設）、例外をthrowする。
     */
    public void testＶalidateTransferTradePossible_C0001()
    {
        final String STR_METHOD_NAME = "testＶalidateTransferTradePossible_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxSystemCode("02");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("100");
            l_fxAccountParams.setFxAccountDiv("1");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(124);
            l_subAccountParams.setAccountId(100);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(100);
            l_mainAccountParams.setAccountCode("100");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams = TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(100);
            l_accOpenDivParams.setAccType("03");
            l_accOpenDivParams.setAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_accOpenDivParams);

            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setMrfAllowDiv("1");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setAccType("03");

            WEB3AioProductTypeOrderManagerReusableValidations l_validations =
                new WEB3AioProductTypeOrderManagerReusableValidations();
            l_validations.validateTransferTradePossible(
                l_subAccount,
                l_compFxConditionParams);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01866, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * １）口座開設チェック
     * getFX顧客()で取得したFX顧客Params.FX口座区分 = ”2：振替停止”の場合、例外をthrowする。 
     */
    public void testＶalidateTransferTradePossible_C0002()
    {
        final String STR_METHOD_NAME = "testＶalidateTransferTradePossible_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("100");
            l_fxAccountParams.setFxAccountDiv("2");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(124);
            l_subAccountParams.setAccountId(100);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(100);
            l_mainAccountParams.setAccountCode("100");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams = TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(100);
            l_accOpenDivParams.setAccType("03");
            l_accOpenDivParams.setAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_accOpenDivParams);

            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setMrfAllowDiv("1");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setAccType("03");

            WEB3AioProductTypeOrderManagerReusableValidations l_validations =
                new WEB3AioProductTypeOrderManagerReusableValidations();
            l_validations.validateTransferTradePossible(
                l_subAccount,
                l_compFxConditionParams);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02440, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * １）口座開設チェック
     * getFX顧客()で取得したFX顧客Params.FX口座区分 != ”1：開設済”の場合、例外をthrowする。
     */
    public void testＶalidateTransferTradePossible_C0003()
    {
        final String STR_METHOD_NAME = "testＶalidateTransferTradePossible_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("100");
            l_fxAccountParams.setFxAccountDiv("9");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(124);
            l_subAccountParams.setAccountId(100);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(100);
            l_mainAccountParams.setAccountCode("100");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams = TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(100);
            l_accOpenDivParams.setAccType("03");
            l_accOpenDivParams.setAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_accOpenDivParams);

            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setMrfAllowDiv("1");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setAccType("03");

            WEB3AioProductTypeOrderManagerReusableValidations l_validations =
                new WEB3AioProductTypeOrderManagerReusableValidations();
            l_validations.validateTransferTradePossible(
                l_subAccount,
                l_compFxConditionParams);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01867, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ２）MRF口座開設チェック
     * 2-1）引数.会社別FXシステム条件Params.MRF口座許可区分 = "０：不可"の場合
     * 顧客.MRF口座開設区分 ！= ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)、例外をthrowする。
     */
    public void testＶalidateTransferTradePossible_C0004()
    {
        final String STR_METHOD_NAME = "testＶalidateTransferTradePossible_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("100");
            l_fxAccountParams.setFxAccountDiv("1");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(124);
            l_subAccountParams.setAccountId(100);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(100);
            l_mainAccountParams.setAccountCode("100");
            l_mainAccountParams.setMrfAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams = TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(100);
            l_accOpenDivParams.setAccType("03");
            l_accOpenDivParams.setAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_accOpenDivParams);

            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setMrfAllowDiv("0");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setAccType("03");

            WEB3AioProductTypeOrderManagerReusableValidations l_validations =
                new WEB3AioProductTypeOrderManagerReusableValidations();
            l_validations.validateTransferTradePossible(
                l_subAccount,
                l_compFxConditionParams);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01868, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ３）口座開設区分チェック
     * 引数.会社別FXシステム条件Params.MRF口座許可区分 != "０：不可"の場合
     * 顧客.MRF口座開設区分 ！= ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)
     * get口座開設区分()戻り値 = nullの場合(口座未開設）、例外をthrowする。
     */
    public void testＶalidateTransferTradePossible_C0005()
    {
        final String STR_METHOD_NAME = "testＶalidateTransferTradePossible_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("100");
            l_fxAccountParams.setFxAccountDiv("1");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(124);
            l_subAccountParams.setAccountId(100);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(100);
            l_mainAccountParams.setAccountCode("100");
            l_mainAccountParams.setMrfAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams = TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(100);
            l_accOpenDivParams.setAccType("04");
            l_accOpenDivParams.setAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_accOpenDivParams);

            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setMrfAllowDiv("1");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setAccType("03");

            WEB3AioProductTypeOrderManagerReusableValidations l_validations =
                new WEB3AioProductTypeOrderManagerReusableValidations();
            l_validations.validateTransferTradePossible(
                l_subAccount,
                l_compFxConditionParams);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01866, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ３）口座開設区分チェック
     * 引数.会社別FXシステム条件Params.MRF口座許可区分 != "０：不可"の場合
     * 顧客.MRF口座開設区分 ！= ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)
     * get口座開設区分()戻り値 ！= ”1:開設済” の場合、例外をthrowする。 
     */
    public void testＶalidateTransferTradePossible_C0006()
    {
        final String STR_METHOD_NAME = "testＶalidateTransferTradePossible_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("100");
            l_fxAccountParams.setFxAccountDiv("1");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(124);
            l_subAccountParams.setAccountId(100);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(100);
            l_mainAccountParams.setAccountCode("100");
            l_mainAccountParams.setMrfAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams = TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(100);
            l_accOpenDivParams.setAccType("03");
            l_accOpenDivParams.setAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_accOpenDivParams);

            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setMrfAllowDiv("1");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setAccType("03");

            WEB3AioProductTypeOrderManagerReusableValidations l_validations =
                new WEB3AioProductTypeOrderManagerReusableValidations();
            l_validations.validateTransferTradePossible(
                l_subAccount,
                l_compFxConditionParams);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01866, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ３）口座開設区分チェック
     * 引数.会社別FXシステム条件Params.MRF口座許可区分 = "０：不可"の場合
     * 顧客.MRF口座開設区分 = ”0：DEFAULT(口座なし)”の場合(MRF口座開設済)
     * get口座開設区分()戻り値 ！= ”1:開設済” の場合、例外をthrowする。 
     */
    public void testＶalidateTransferTradePossible_C0007()
    {
        final String STR_METHOD_NAME = "testＶalidateTransferTradePossible_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("100");
            l_fxAccountParams.setFxAccountDiv("1");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(124);
            l_subAccountParams.setAccountId(100);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(100);
            l_mainAccountParams.setAccountCode("100");
            l_mainAccountParams.setMrfAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams = TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(100);
            l_accOpenDivParams.setAccType("03");
            l_accOpenDivParams.setAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_accOpenDivParams);

            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setMrfAllowDiv("0");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setAccType("03");

            WEB3AioProductTypeOrderManagerReusableValidations l_validations =
                new WEB3AioProductTypeOrderManagerReusableValidations();
            l_validations.validateTransferTradePossible(
                l_subAccount,
                l_compFxConditionParams);

        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
