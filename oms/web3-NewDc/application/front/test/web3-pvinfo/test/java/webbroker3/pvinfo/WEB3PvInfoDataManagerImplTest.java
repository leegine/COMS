head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3PvInfoDataManagerImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3PvInfoDataManagerImplTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/27 金傑(中訊) 新規作成
 Revision History : 2007/07/13 謝旋(中訊) 仕様変更モデル083
*/
package webbroker3.pvinfo;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.accountinfo.data.CommCampaignAccHistoryRow;
import webbroker3.accountinfo.data.CommCampaignProductMstParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CategCodeDef;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬImplのテスト<BR>
 * 
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3PvInfoDataManagerImplTest extends TestBaseForMock
{

	private WEB3PvInfoDataManagerImpl l_pvInfoManagerImpl = null;
	
	private CommCampaignAccHistoryParams l_commCampaignAccHistoryParams[] = null;

	private static int l_intCount = 0;
	/**
	 * Logger
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoDataManagerImplTest.class);

	public WEB3PvInfoDataManagerImplTest(String name)
	{
		super(name);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
		this.l_pvInfoManagerImpl = new WEB3PvInfoDataManagerImplForTest();
		this.prepareData();
		this.getMock();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
		this.l_pvInfoManagerImpl = null;
	}

	/**
	 * 顧客 == null の場合
	 *
	 */
	public void test_isAccInfoCampaign_C0001()
	{
		final String STR_METHOD_NAME = "test_isAccInfoCampaign_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			boolean l_blnResult = l_pvInfoManagerImpl.isAccInfoCampaign(null);
			fail();
		}
		catch (WEB3BaseRuntimeException l_web3RunTimeException)
		{
			log.error(STR_METHOD_NAME,l_web3RunTimeException);
			assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_web3RunTimeException.getErrorInfo());
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
	
	/**
	 * 取得できなかった場合はfalseを返却する
	 *
	 */
	public void test_isAccInfoCampaign_C0002()
	{
		final String STR_METHOD_NAME = "test_isAccInfoCampaign_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
			boolean l_blnResult = l_pvInfoManagerImpl.isAccInfoCampaign(l_mainAccount);
			assertFalse(l_blnResult);
			
		}
		catch (WEB3BaseException l_web3BaseException)
		{
			log.error(STR_METHOD_NAME,l_web3BaseException);
			fail();
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
	
	/**
	 * 検索結果が取得できた場合はtrue
	 *
	 */
	public void test_isAccInfoCampaign_C0003()
	{
		final String STR_METHOD_NAME = "test_isAccInfoCampaign_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
			boolean l_blnResult = l_pvInfoManagerImpl.isAccInfoCampaign(l_mainAccount);
			assertTrue(l_blnResult);
		}
		catch (WEB3BaseException l_web3BaseException)
		{
			log.error(STR_METHOD_NAME,l_web3BaseException);
			fail();
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
	
	/**
	 * 商品コードを取得する
	 *
	 */
	public void test_getCommProductCodes_C0001()
	{
		final String STR_METHOD_NAME = "test_getCommProductCodes_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			String[] l_arrProductCodes = this.l_pvInfoManagerImpl.getCommProductCodes(2008000019L);
			assertEquals(3,l_arrProductCodes.length);
			assertEquals("19",l_arrProductCodes[0]);
			assertEquals("20",l_arrProductCodes[1]);
			assertEquals("21",l_arrProductCodes[2]);
			
		}
		catch (WEB3BaseException l_web3BaseException)
		{
			log.error(STR_METHOD_NAME,l_web3BaseException);
			fail();
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
	
	/**
	 * 商品コードを取得しない
	 *
	 */
	public void test_getCommProductCodes_C0002()
	{
		final String STR_METHOD_NAME = "test_getCommProductCodes_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			String[] l_arrProductCodes = this.l_pvInfoManagerImpl.getCommProductCodes(2008000009L);
			fail();
			
		}
		catch (WEB3SystemLayerException l_web3SystemLayerException)
		{
			log.error(STR_METHOD_NAME,l_web3SystemLayerException);
			assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003,l_web3SystemLayerException.getErrorInfo());
			
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
	
	/**
	 * 顧客 == null の場合
	 *
	 */
	public void test_getCommCampaignAccHistoryParams_C0001()
	{
		final String STR_METHOD_NAME = "test_getCommCampaignAccHistoryParams_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			WEB3PvInfoDataManagerImpl l_web3PvInfoDataManagerImpl = new WEB3PvInfoDataManagerImpl();
			CommCampaignAccHistoryParams[] l_commCampaignAccHistoryParams = 
				l_web3PvInfoDataManagerImpl.getCommCampaignAccHistoryParams(null);
			fail();
			
		}
		catch (WEB3BaseRuntimeException l_web3RunTimeException)
		{
			log.error(STR_METHOD_NAME,l_web3RunTimeException);
			assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_web3RunTimeException.getErrorInfo());
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
	
	/**
	 * 検索結果を返却する
	 *
	 */
	public void test_getCommCampaignAccHistoryParams_C0002()
	{
		final String STR_METHOD_NAME = "test_getCommCampaignAccHistoryParams_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			WEB3PvInfoDataManagerImpl l_web3PvInfoDataManagerImpl = new WEB3PvInfoDataManagerImpl();
			WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
			CommCampaignAccHistoryParams[] l_commCampaignAccHistoryParams = 
				l_web3PvInfoDataManagerImpl.getCommCampaignAccHistoryParams(l_mainAccount);
            
			assertNotNull(l_commCampaignAccHistoryParams);
			assertEquals(4,l_commCampaignAccHistoryParams.length);
            
			
			assertEquals(new Long(10010010018L).longValue(),l_commCampaignAccHistoryParams[0].getCampaignId());
            assertEquals(0,WEB3DateUtility.compareToDay(
                    l_commCampaignAccHistoryParams[0].getAppliStartDate(),
                    WEB3DateUtility.getDate("2007/02/17", "yyyy/MM/dd")));
			
            
            assertEquals(new Long(10010010022L).longValue(),l_commCampaignAccHistoryParams[1].getCampaignId());
            assertEquals(0,WEB3DateUtility.compareToDay(
                    l_commCampaignAccHistoryParams[1].getAppliStartDate(),
                    WEB3DateUtility.getDate("2007/02/18", "yyyy/MM/dd")));
            

            assertEquals(new Long(10010010019L).longValue(),l_commCampaignAccHistoryParams[2].getCampaignId());
            assertEquals(0,WEB3DateUtility.compareToDay(
                    l_commCampaignAccHistoryParams[2].getAppliStartDate(),
                    WEB3DateUtility.getDate("2007/02/20", "yyyy/MM/dd")));
            
            
            assertEquals(new Long(10010010020L).longValue(),l_commCampaignAccHistoryParams[3].getCampaignId());
            assertEquals(0,WEB3DateUtility.compareToDay(
                    l_commCampaignAccHistoryParams[3].getAppliStartDate(),
                    WEB3DateUtility.getDate("2007/02/20", "yyyy/MM/dd")));
            
       
		}
		catch (WEB3BaseRuntimeException l_web3RunTimeException)
		{
			log.error(STR_METHOD_NAME,l_web3RunTimeException);
			fail();
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
	
	/**
	 * 検索結果が取得できなかった場合はnullを返却する
	 *
	 */
	public void test_getCommCampaignAccHistoryParams_C0003()
	{
		final String STR_METHOD_NAME = "test_getCommCampaignAccHistoryParams_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			WEB3PvInfoDataManagerImpl l_web3PvInfoDataManagerImpl = new WEB3PvInfoDataManagerImpl();
			WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(52525257L);
			CommCampaignAccHistoryParams[] l_commCampaignAccHistoryParams = 
				l_web3PvInfoDataManagerImpl.getCommCampaignAccHistoryParams(l_mainAccount);
			
			assertNull(l_commCampaignAccHistoryParams);
		}
		catch (WEB3BaseRuntimeException l_web3RunTimeException)
		{
			log.error(STR_METHOD_NAME,l_web3RunTimeException);
			fail();
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
	
	/**
	 * 【登録タイプ="２：強制個別顧客指定"】のデータが含まれる場合はnullを返却する
	 *
	 */
	public void test_getCommCampaignAccHistoryParams_C0004()
	{
		final String STR_METHOD_NAME = "test_getCommCampaignAccHistoryParams_C0004()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{			
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_queryProcessor.doDeleteAllQuery(CommCampaignAccHistoryRow.TYPE);
			
			CommCampaignAccHistoryParams l_commCampaignAccHistoryParams1 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams1.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams1.setCampaignId(10010010018L);
	        l_commCampaignAccHistoryParams1.setCommCampaignName("おいabc");
	        l_commCampaignAccHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        l_commCampaignAccHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070219","yyyyMMdd"));
	        l_commCampaignAccHistoryParams1.setValidDiv("1");
	        l_commCampaignAccHistoryParams1.setRegistType("0");
	        l_commCampaignAccHistoryParams1.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams1.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams1.setAccountChargeRatio(123.41235D);
	        l_commCampaignAccHistoryParams1.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070227","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams1);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams2 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams2.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams2.setCampaignId(10010010019L);
	        l_commCampaignAccHistoryParams2.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams2.setAppliStartDate(WEB3DateUtility.getDate("20070219","yyyyMMdd"));
	        l_commCampaignAccHistoryParams2.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams2.setValidDiv("1");
	        l_commCampaignAccHistoryParams2.setRegistType("0");
	        l_commCampaignAccHistoryParams2.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams2.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams2.setAccountChargeRatio(321.41235D);
	        l_commCampaignAccHistoryParams2.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070227","yyyyMMdd"));
	        l_commCampaignAccHistoryParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070228","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams2);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams3 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams3.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams3.setCampaignId(10010010020L);
	        l_commCampaignAccHistoryParams3.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams3.setAppliStartDate(WEB3DateUtility.getDate("20070218","yyyyMMdd"));
	        l_commCampaignAccHistoryParams3.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams3.setValidDiv("1");
	        l_commCampaignAccHistoryParams3.setRegistType("0");
	        l_commCampaignAccHistoryParams3.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams3.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams3.setAccountChargeRatio(322.41235D);
	        l_commCampaignAccHistoryParams3.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams3.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams3.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams3);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams4 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams4.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams4.setCampaignId(10010010021L);
	        l_commCampaignAccHistoryParams4.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams4.setAppliStartDate(WEB3DateUtility.getDate("20070217","yyyyMMdd"));
	        l_commCampaignAccHistoryParams4.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams4.setValidDiv("1");
	        l_commCampaignAccHistoryParams4.setRegistType("0");
	        l_commCampaignAccHistoryParams4.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams4.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams4.setAccountChargeRatio(123.41235D);
	        l_commCampaignAccHistoryParams4.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams4.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams4.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams4);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams5 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams5.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams5.setCampaignId(10010010022L);
	        l_commCampaignAccHistoryParams5.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams5.setAppliStartDate(WEB3DateUtility.getDate("20070218","yyyyMMdd"));
	        l_commCampaignAccHistoryParams5.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams5.setValidDiv("1");
	        l_commCampaignAccHistoryParams5.setRegistType("1");
	        l_commCampaignAccHistoryParams5.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams5.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams5.setAccountChargeRatio(322.41235D);
	        l_commCampaignAccHistoryParams5.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams5.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams5.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070221","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams5);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams6 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams6.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams6.setCampaignId(10010010023L);
	        l_commCampaignAccHistoryParams6.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams6.setAppliStartDate(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
	        l_commCampaignAccHistoryParams6.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams6.setValidDiv("1");
	        l_commCampaignAccHistoryParams6.setRegistType("0");
	        l_commCampaignAccHistoryParams6.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams6.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams6.setAccountChargeRatio(322.41235D);
	        l_commCampaignAccHistoryParams6.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams6.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams6.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070221","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams6);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams7 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams7.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams7.setCampaignId(10010010024L);
	        l_commCampaignAccHistoryParams7.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams7.setAppliStartDate(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        l_commCampaignAccHistoryParams7.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams7.setValidDiv("0");
	        l_commCampaignAccHistoryParams7.setRegistType("0");
	        l_commCampaignAccHistoryParams7.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams7.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams7.setAccountChargeRatio(322.41235D);
	        l_commCampaignAccHistoryParams7.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams7.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams7.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070221","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams7);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams8 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams8.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams8.setCampaignId(10010010025L);
	        l_commCampaignAccHistoryParams8.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams8.setAppliStartDate(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        l_commCampaignAccHistoryParams8.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams8.setValidDiv("1");
	        l_commCampaignAccHistoryParams8.setRegistType("2");
	        l_commCampaignAccHistoryParams8.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams8.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams8.setAccountChargeRatio(322.41235D);
	        l_commCampaignAccHistoryParams8.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams8.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams8.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070221","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams8);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams9 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams9.setAccountId(333812512247L);
	        l_commCampaignAccHistoryParams9.setCampaignId(10010010026L);
	        l_commCampaignAccHistoryParams9.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams9.setAppliStartDate(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        l_commCampaignAccHistoryParams9.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams9.setValidDiv("1");
	        l_commCampaignAccHistoryParams9.setRegistType("0");
	        l_commCampaignAccHistoryParams9.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams9.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams9.setAccountChargeRatio(322.41235D);
	        l_commCampaignAccHistoryParams9.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams9.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams9.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070221","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams9);
			WEB3PvInfoDataManagerImpl l_web3PvInfoDataManagerImpl = new WEB3PvInfoDataManagerImpl();
			WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
			CommCampaignAccHistoryParams[] l_commCampaignAccHistoryParams = 
				l_web3PvInfoDataManagerImpl.getCommCampaignAccHistoryParams(l_mainAccount);
			assertNull(l_commCampaignAccHistoryParams);

		}
		catch (WEB3BaseRuntimeException l_web3RunTimeException)
		{
			log.error(STR_METHOD_NAME,l_web3RunTimeException);
			fail();
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	private void prepareData()
	{
		final String STR_METHOD_NAME = "prepareData()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_queryProcessor.doDeleteAllQuery(MainAccountRow.TYPE);
			
			MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
			TestDBUtility.insertWithDel(l_mainAccountParams1);
			
			MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
			l_mainAccountParams2.setAccountId(52525257L);
			l_mainAccountParams2.setInstitutionCode("1D");
			l_mainAccountParams2.setInstitutionId(35L);
			TestDBUtility.insertWithDel(l_mainAccountParams2);
			
            this.l_commCampaignAccHistoryParams = new CommCampaignAccHistoryParams[3];
//            this.l_commCampaignAccHistoryParams[0] = new CommCampaignAccHistoryParams();
            this.l_commCampaignAccHistoryParams[0] = TestDBUtility.getCommCampaignAccHistoryRow();
			
			
			l_queryProcessor.doDeleteAllQuery(CommCampaignAccHistoryRow.TYPE);
			
			CommCampaignAccHistoryParams l_commCampaignAccHistoryParams1 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams1.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams1.setCampaignId(10010010018L);
	        l_commCampaignAccHistoryParams1.setCommCampaignName("おいabc");
	        l_commCampaignAccHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070217","yyyyMMdd"));
	        l_commCampaignAccHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070219","yyyyMMdd"));
	        l_commCampaignAccHistoryParams1.setValidDiv("1");
	        l_commCampaignAccHistoryParams1.setRegistType("0");
	        l_commCampaignAccHistoryParams1.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams1.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams1.setAccountChargeRatio(123.41235D);
	        l_commCampaignAccHistoryParams1.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070227","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams1);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams2 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams2.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams2.setCampaignId(10010010019L);
	        l_commCampaignAccHistoryParams2.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams2.setAppliStartDate(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        l_commCampaignAccHistoryParams2.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams2.setValidDiv("1");
	        l_commCampaignAccHistoryParams2.setRegistType("0");
	        l_commCampaignAccHistoryParams2.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams2.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams2.setAccountChargeRatio(321.41235D);
	        l_commCampaignAccHistoryParams2.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070227","yyyyMMdd"));
	        l_commCampaignAccHistoryParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070228","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams2);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams3 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams3.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams3.setCampaignId(10010010020L);
	        l_commCampaignAccHistoryParams3.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams3.setAppliStartDate(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        l_commCampaignAccHistoryParams3.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams3.setValidDiv("1");
	        l_commCampaignAccHistoryParams3.setRegistType("0");
	        l_commCampaignAccHistoryParams3.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams3.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams3.setAccountChargeRatio(322.41235D);
	        l_commCampaignAccHistoryParams3.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams3.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams3.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams3);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams4 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams4.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams4.setCampaignId(10010010021L);
	        l_commCampaignAccHistoryParams4.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams4.setAppliStartDate(WEB3DateUtility.getDate("20070222","yyyyMMdd"));
	        l_commCampaignAccHistoryParams4.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams4.setValidDiv("1");
	        l_commCampaignAccHistoryParams4.setRegistType("0");
	        l_commCampaignAccHistoryParams4.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams4.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams4.setAccountChargeRatio(123.41235D);
	        l_commCampaignAccHistoryParams4.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams4.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams4.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams4);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams5 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams5.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams5.setCampaignId(10010010022L);
	        l_commCampaignAccHistoryParams5.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams5.setAppliStartDate(WEB3DateUtility.getDate("20070218","yyyyMMdd"));
	        l_commCampaignAccHistoryParams5.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams5.setValidDiv("1");
	        l_commCampaignAccHistoryParams5.setRegistType("1");
	        l_commCampaignAccHistoryParams5.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams5.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams5.setAccountChargeRatio(322.41235D);
	        l_commCampaignAccHistoryParams5.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams5.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams5.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070221","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams5);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams6 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams6.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams6.setCampaignId(10010010023L);
	        l_commCampaignAccHistoryParams6.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams6.setAppliStartDate(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
	        l_commCampaignAccHistoryParams6.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams6.setValidDiv("1");
	        l_commCampaignAccHistoryParams6.setRegistType("0");
	        l_commCampaignAccHistoryParams6.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams6.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams6.setAccountChargeRatio(322.41235D);
	        l_commCampaignAccHistoryParams6.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams6.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams6.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070221","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams6);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams7 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams7.setAccountId(333812512246L);
	        l_commCampaignAccHistoryParams7.setCampaignId(10010010024L);
	        l_commCampaignAccHistoryParams7.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams7.setAppliStartDate(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        l_commCampaignAccHistoryParams7.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams7.setValidDiv("0");
	        l_commCampaignAccHistoryParams7.setRegistType("0");
	        l_commCampaignAccHistoryParams7.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams7.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams7.setAccountChargeRatio(322.41235D);
	        l_commCampaignAccHistoryParams7.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams7.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams7.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070221","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams7);
	        
//	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams8 = new CommCampaignAccHistoryParams();
//	        l_commCampaignAccHistoryParams8.setAccountId(333812512246L);
//	        l_commCampaignAccHistoryParams8.setCampaignId(10010010025L);
//	        l_commCampaignAccHistoryParams8.setCommCampaignName("おいabc102");
//	        l_commCampaignAccHistoryParams8.setAppliStartDate(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
//	        l_commCampaignAccHistoryParams8.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
//	        l_commCampaignAccHistoryParams8.setValidDiv("1");
//	        l_commCampaignAccHistoryParams8.setRegistType("2");
//	        l_commCampaignAccHistoryParams8.setInstitutionCode("0D");
//	        l_commCampaignAccHistoryParams8.setAccountCode("2450007");
//	        l_commCampaignAccHistoryParams8.setAccountChargeRatio(322.41235D);
//	        l_commCampaignAccHistoryParams8.setLastUpdater("admin");
//	        l_commCampaignAccHistoryParams8.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
//	        l_commCampaignAccHistoryParams8.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070221","yyyyMMdd"));
//	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams8);
	        
	        CommCampaignAccHistoryParams l_commCampaignAccHistoryParams9 = new CommCampaignAccHistoryParams();
	        l_commCampaignAccHistoryParams9.setAccountId(333812512247L);
	        l_commCampaignAccHistoryParams9.setCampaignId(10010010026L);
	        l_commCampaignAccHistoryParams9.setCommCampaignName("おいabc102");
	        l_commCampaignAccHistoryParams9.setAppliStartDate(WEB3DateUtility.getDate("20070220","yyyyMMdd"));
	        l_commCampaignAccHistoryParams9.setAppliEndDate(WEB3DateUtility.getDate("20070226","yyyyMMdd"));
	        l_commCampaignAccHistoryParams9.setValidDiv("1");
	        l_commCampaignAccHistoryParams9.setRegistType("0");
	        l_commCampaignAccHistoryParams9.setInstitutionCode("0D");
	        l_commCampaignAccHistoryParams9.setAccountCode("2450007");
	        l_commCampaignAccHistoryParams9.setAccountChargeRatio(322.41235D);
	        l_commCampaignAccHistoryParams9.setLastUpdater("admin");
	        l_commCampaignAccHistoryParams9.setCreatedTimestamp(WEB3DateUtility.getDate("20070216","yyyyMMdd"));
	        l_commCampaignAccHistoryParams9.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070221","yyyyMMdd"));
	        TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams9);
	        
			
	        CommCampaignProductMstParams l_commCampaignProductMstParams1 = TestDBUtility.getCommCampaignProductMstRow();
	        l_commCampaignProductMstParams1.setCampaignId(2008000019L);
	        l_commCampaignProductMstParams1.setCommProductCode("19");
	        TestDBUtility.insertWithDel(l_commCampaignProductMstParams1);
	        
			CommCampaignProductMstParams l_commCampaignProductMstParams2 = TestDBUtility.getCommCampaignProductMstRow();
	        l_commCampaignProductMstParams2.setCampaignId(2008000019L);
	        l_commCampaignProductMstParams2.setCommProductCode("20");
	        TestDBUtility.insertWithDel(l_commCampaignProductMstParams2);
	        
			CommCampaignProductMstParams l_commCampaignProductMstParams3 = TestDBUtility.getCommCampaignProductMstRow();
	        l_commCampaignProductMstParams3.setCampaignId(2008000019L);
	        l_commCampaignProductMstParams3.setCommProductCode("21");
	        TestDBUtility.insertWithDel(l_commCampaignProductMstParams3);
		}
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    public void testIsOnlyMobileOpen_0001()
    {
        final String STR_METHOD_NAME = "testIsOnlyMobileOpen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            l_mainAccountParams.setOnlyMobileOpenDiv("1");
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(10100L);

            WEB3PvInfoDataManagerImpl l_pvInfoDataManagerImpl = new WEB3PvInfoDataManagerImpl();
            assertTrue(l_pvInfoDataManagerImpl.isOnlyMobileOpen(l_mainAccount));
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsOnlyMobileOpen_0002()
    {
        final String STR_METHOD_NAME = "testIsOnlyMobileOpen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            l_mainAccountParams.setOnlyMobileOpenDiv("2");
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(10100L);

            WEB3PvInfoDataManagerImpl l_pvInfoDataManagerImpl = new WEB3PvInfoDataManagerImpl();
            assertFalse(l_pvInfoDataManagerImpl.isOnlyMobileOpen(l_mainAccount));
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsOnlyMobileOpen_0003()
    {
        final String STR_METHOD_NAME = "testIsOnlyMobileOpen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3PvInfoDataManagerImpl l_pvInfoDataManagerImpl = new WEB3PvInfoDataManagerImpl();
            l_pvInfoDataManagerImpl.isOnlyMobileOpen(null);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
	
    private void getMock()
	{
    	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20070219","yyyyMMdd"));
	}

	private class WEB3PvInfoDataManagerImplForTest extends WEB3PvInfoDataManagerImpl
	{
		public CommCampaignAccHistoryParams[] getCommCampaignAccHistoryParams(WEB3GentradeMainAccount l_mainAccount)
				throws WEB3BaseException
		{
			l_intCount++;
			log.debug("l_intCount :"+l_intCount);
			if(l_intCount == 1){
				return null;
			}
			return l_commCampaignAccHistoryParams;
		}
	}

    public void testIsDeliveryDate_case0001()
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_mainAccountParams.setBranchId(l_branchParams.getBranchId());
            l_mainAccountParams.setAccountId(333812512243L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            boolean l_blnResult = l_pvInfoManagerImpl.isDeliveryDate(l_mainAccount);
            assertFalse(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsDeliveryDate_case0002()
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("0000000");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070208","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            BatoProductManagementParams l_BatoProductManagementParams = TestDBUtility.getBatoProductManagementRow();
            l_BatoProductManagementParams.setDocumentDiv(WEB3CategCodeDef.DOCUMENT_DELIVERY);
            l_BatoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_BatoProductManagementParams);

            boolean l_blnResult = l_pvInfoManagerImpl.isDeliveryDate(l_mainAccount);
            
            assertTrue(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsDeliveryDate_case0005()
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("0000000");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070108","yyyyMMdd"));
            l_docDeliveryManagementParams.setDeemedDeliveryDate(WEB3DateUtility.getDate("20070108","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            BatoProductManagementParams l_BatoProductManagementParams = TestDBUtility.getBatoProductManagementRow();
            l_BatoProductManagementParams.setDocumentDiv(WEB3CategCodeDef.DOCUMENT_DELIVERY);
            l_BatoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_BatoProductManagementParams);
            
            boolean l_blnResult = l_pvInfoManagerImpl.isDeliveryDate(l_mainAccount);
            assertTrue(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsDeliveryDate_case0003()
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            Date l_datSystemTime = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("0000000");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070308","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            BatoProductManagementParams l_BatoProductManagementParams = TestDBUtility.getBatoProductManagementRow();
            l_BatoProductManagementParams.setDocumentDiv(WEB3CategCodeDef.DOCUMENT_DELIVERY);
            l_BatoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_BatoProductManagementParams);
            
            boolean l_blnResult = l_pvInfoManagerImpl.isDeliveryDate(l_mainAccount);
            assertFalse(!l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsDeliveryDate_case0004()
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            Date l_datSystemTime = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("0000000");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.addMonth(l_datSystemTime, -11));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            BatoProductManagementParams l_BatoProductManagementParams = TestDBUtility.getBatoProductManagementRow();
            l_BatoProductManagementParams.setDocumentDiv(WEB3CategCodeDef.DOCUMENT_DELIVERY);
            l_BatoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_BatoProductManagementParams);
            
            boolean l_blnResult = l_pvInfoManagerImpl.isDeliveryDate(l_mainAccount);
            assertTrue(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsDeliveryDate_case0006()
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            Date l_datSystemTime = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("0000000");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070108","yyyyMMdd"));
            l_docDeliveryManagementParams.setDeemedDeliveryDate(WEB3DateUtility.addMonth(l_datSystemTime, -10));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            BatoProductManagementParams l_BatoProductManagementParams = TestDBUtility.getBatoProductManagementRow();
            l_BatoProductManagementParams.setDocumentDiv(WEB3CategCodeDef.DOCUMENT_DELIVERY);
            l_BatoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_BatoProductManagementParams);
            
            boolean l_blnResult = l_pvInfoManagerImpl.isDeliveryDate(l_mainAccount);
            assertTrue(!l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetDocDeliveryManagementParams_case0001()
    {
        final String STR_METHOD_NAME = "testGetDocDeliveryManagementParams_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            
            assertNull(l_pvInfoManagerImpl.getDocDeliveryManagementParams(l_mainAccount));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetDocDeliveryManagementParams_case0002()
    {
        final String STR_METHOD_NAME = "testGetDocDeliveryManagementParams_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3PvInfoDataManagerImpl l_pvInfoManagerImplA = new WEB3PvInfoDataManagerImpl();
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("0000000");
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            BatoProductManagementParams l_BatoProductManagementParams = TestDBUtility.getBatoProductManagementRow();
            l_BatoProductManagementParams.setDocumentDiv(WEB3CategCodeDef.DOCUMENT_DELIVERY);
            l_BatoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_BatoProductManagementParams);
            
            List l_lisResults = l_pvInfoManagerImplA.getDocDeliveryManagementParams(l_mainAccount);
            DocDeliveryManagementRow l_docDeliveryManagementRow = (DocDeliveryManagementRow)l_lisResults.get(0);

            assertEquals("1", "" + l_lisResults.size());
            assertEquals("381", l_docDeliveryManagementRow.getBranchCode());
            assertEquals("0D", l_docDeliveryManagementRow.getInstitutionCode());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *  ("0"：　@不足金未発生 )
     */
    public void testGetShortfallGenerationStatus_Case001()
    {
        final String STR_METHOD_NAME = "testGetShortfallGenerationStatus_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getLackCashOccurSituation",
    				new Class[] { MainAccount.class },
                    "0");
        	
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();

            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
            String  l_lackCashOccur0 = l_impl.getShortfallGenerationStatus(l_mainAccount);
            assertEquals("0",l_lackCashOccur0);
        }
        catch (Exception l_ex)
        {
            log.error("has errros", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *  ("1"：　@不足金未発生 )
     */
    public void testGetShortfallGenerationStatus_Case002()
    {
        final String STR_METHOD_NAME = "testgetShortfallGenerationStatus_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getLackCashOccurSituation",
    				new Class[] { MainAccount.class },
                    "1");
        	
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();

            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
            String  l_lackCashOccur1 = l_impl.getShortfallGenerationStatus(l_mainAccount);
            assertEquals("1",l_lackCashOccur1);
        }
        catch (Exception l_ex)
        {
            log.error("has errros", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *  ("2"：　@不足金未発生 )
     */
    public void testGetShortfallGenerationStatus_Case003()
    {
        final String STR_METHOD_NAME = "testgetShortfallGenerationStatus_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getLackCashOccurSituation",
    				new Class[] { MainAccount.class },
                    "2");
        	
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();

            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
            String  l_lackCashOccur2 = l_impl.getShortfallGenerationStatus(l_mainAccount);
            assertEquals("2",l_lackCashOccur2);
        }
        catch (Exception l_ex)
        {
            log.error("has errros", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *  ("0"：　@追証未発生)
     */
    public void testGetAdditionalMarginSituation_Case001()
    {
        final String STR_METHOD_NAME = "testgetShortfallGenerationStatus_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdditionalMarginSituation",
    				new Class[] { MainAccount.class },
                    "0");
        	
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();

            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
            String  l_additionalMargin0 = l_impl.getAdddepositGenerationStatus(l_mainAccount);
            assertEquals("0",l_additionalMargin0);
        }
        catch (Exception l_ex)
        {
            log.error("has errros", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *  ("1"：　@第一水準追証発生)
     */
    public void testGetAdditionalMarginSituation_Case002()
    {
        final String STR_METHOD_NAME = "testgetShortfallGenerationStatus_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdditionalMarginSituation",
    				new Class[] { MainAccount.class },
                    "1");
        	
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();

            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
            String  l_additionalMargin1 = l_impl.getAdddepositGenerationStatus(l_mainAccount);
            assertEquals("1",l_additionalMargin1);
        }
        catch (Exception l_ex)
        {
            log.error("has errros", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *  ("2"：　@第二水準追証発生)
     */
    public void testGetAdditionalMarginSituation_Case003()
    {
        final String STR_METHOD_NAME = "testgetShortfallGenerationStatus_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdditionalMarginSituation",
    				new Class[] { MainAccount.class },
                    "2");
        	
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();

            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
            String  l_additionalMargin2 = l_impl.getAdddepositGenerationStatus(l_mainAccount);
            assertEquals("2",l_additionalMargin2);
        }
        catch (Exception l_ex)
        {
            log.error("has errros", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    	
    }

    /*
     * 不足金発生情報
     */
    public void testGetShortfallGenerationInfo_Case001()
    {
        final String STR_METHOD_NAME = "testgetShortfallGenerationStatus_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();

            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);

            WEB3TPShortfallOccurInfo l_shortfallGenerationInfo = new WEB3TPShortfallOccurInfo();
            
            l_shortfallGenerationInfo.debitAmount = 1.0;

            l_shortfallGenerationInfo.specialDebitAmount = 1.0;

            l_shortfallGenerationInfo.closeDate0 = new Date(2007,6,5);
            l_shortfallGenerationInfo.closeDate1 = new Date(2007,6,5);
            l_shortfallGenerationInfo.closeDate2 = new Date(2007,6,5);
            l_shortfallGenerationInfo.closeDate3 = new Date(2007,6,5);
            l_shortfallGenerationInfo.closeDate4 = new Date(2007,6,5);
            l_shortfallGenerationInfo.closeDate5 = new Date(2007,6,5);

            l_shortfallGenerationInfo.requiredPayAmt0 = 1.0;
            l_shortfallGenerationInfo.requiredPayAmt1 = 1.0;
            l_shortfallGenerationInfo.requiredPayAmt2 = 1.0;
            l_shortfallGenerationInfo.requiredPayAmt3 = 1.0;
            l_shortfallGenerationInfo.requiredPayAmt4 = 1.0;
            l_shortfallGenerationInfo.requiredPayAmt5 = 1.0;


            l_shortfallGenerationInfo.adjustedAmt0 = 1.0;
            l_shortfallGenerationInfo.adjustedAmt1 = 1.0;

            l_shortfallGenerationInfo.dayTradeRestraint0 = 1.0;
            l_shortfallGenerationInfo.dayTradeRestraint1 = 1.0;

            l_shortfallGenerationInfo.transferFromMarginDeposit0 = 1.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit1 = 1.0;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getShortfallGenerationInfo",
    				new Class[] { MainAccount.class },
    				l_shortfallGenerationInfo);

            WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
            WEB3TPShortfallOccurInfo  l_shortfallGenerationInfoBk = l_impl.getShortfallGenerationInfo(l_mainAccount);
            
            assertEquals(WEB3DateUtility.toDay(new Date(2007,6,5)),WEB3DateUtility.toDay(l_shortfallGenerationInfoBk.closeDate0));
            assertEquals(new Date(2007,6,5),l_shortfallGenerationInfoBk.closeDate1);
            assertEquals(new Date(2007,6,5),l_shortfallGenerationInfoBk.closeDate2);
            assertEquals(new Date(2007,6,5),l_shortfallGenerationInfoBk.closeDate3);
            assertEquals(new Date(2007,6,5),l_shortfallGenerationInfoBk.closeDate4);
            assertEquals(new Date(2007,6,5),l_shortfallGenerationInfoBk.closeDate5);
            
            assertEquals(1.0,l_shortfallGenerationInfoBk.requiredPayAmt0,0.0001);
            assertEquals(1.0,l_shortfallGenerationInfoBk.requiredPayAmt1,0.0001);
            assertEquals(1.0,l_shortfallGenerationInfoBk.requiredPayAmt2,0.0001);
            assertEquals(1.0,l_shortfallGenerationInfoBk.requiredPayAmt3,0.0001);
            assertEquals(1.0,l_shortfallGenerationInfoBk.requiredPayAmt4,0.0001);
            assertEquals(1.0,l_shortfallGenerationInfoBk.requiredPayAmt5,0.0001);
            
            assertEquals(1.0,l_shortfallGenerationInfoBk.adjustedAmt0,0.0001);
            assertEquals(1.0,l_shortfallGenerationInfoBk.adjustedAmt1,0.0001);
            
            assertEquals(1.0,l_shortfallGenerationInfoBk.dayTradeRestraint0,0.0001);
            assertEquals(1.0,l_shortfallGenerationInfoBk.dayTradeRestraint1,0.0001);
            
            assertEquals(1.0,l_shortfallGenerationInfoBk.transferFromMarginDeposit0,0.0001);
            assertEquals(1.0,l_shortfallGenerationInfoBk.transferFromMarginDeposit1,0.0001);
        }
        catch (Exception l_ex)
        {
            log.error("has errros", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 追証発生情報
     */
    public void testGetAdddepositGenerationInfo_Case002()
    {

        final String STR_METHOD_NAME = "testGetShortfallGenerationStatus_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();

            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);

            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
          
            WEB3TPFirstAdddepositInfo l_firstAdddepositInfo = new WEB3TPFirstAdddepositInfo();
            l_firstAdddepositInfo.firstDepositPassDay = 1;

            l_firstAdddepositInfo.firstDepositPassDayValid = 1;

            l_firstAdddepositInfo.firstDepositOccurredDate = WEB3DateUtility.toDay(new Date(2007,8,4));

            l_firstAdddepositInfo.firstMarginDepositRate = 1.0;

            l_firstAdddepositInfo.firstDepositRate =1.0;
            
            l_firstAdddepositInfo.firstDepositAmount = 1.0;
            
            l_firstAdddepositInfo.firstSettlement = 1.0;

            l_firstAdddepositInfo.firstMarginDepositInDe = 1.0;

            l_firstAdddepositInfo.firstMarginDepositInDeExpect = 1.0;

            l_firstAdddepositInfo.firstSettledContract = 1.0;

            l_firstAdddepositInfo.firstUncancelAmt = 1.0;

            l_firstAdddepositInfo.firstUncancelSettleRequiredAmt = 1.0;
            
            l_adddepositGenerationInfo.setAdddepositInfo(l_firstAdddepositInfo);

            l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
    				new Class[] { MainAccount.class },
    				l_adddepositGenerationInfo);

            WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
            WEB3TPAdddepositGenerationInfo  l_adddepositGenerationInfoBk = l_impl.getAdddepositGenerationInfo(l_mainAccount);
            
            boolean flag = l_adddepositGenerationInfoBk.getDepositAutoTransferDivFlag();
            WEB3TPFirstAdddepositInfo l_firstAdddepositInfoBk =  (WEB3TPFirstAdddepositInfo) l_adddepositGenerationInfoBk.getAdddepositInfo();
            assertEquals(1,l_firstAdddepositInfoBk.firstDepositPassDay);
            assertEquals(1,l_firstAdddepositInfoBk.firstDepositPassDayValid);
            assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_firstAdddepositInfoBk.firstDepositOccurredDate);
            assertEquals(1.0,l_firstAdddepositInfoBk.firstMarginDepositRate,0.0001);
            assertEquals(1.0,l_firstAdddepositInfoBk.firstDepositRate,0.0001);
            assertEquals(1.0,l_firstAdddepositInfoBk.firstDepositAmount,0.0001);
            assertEquals(1.0,l_firstAdddepositInfoBk.firstSettlement,0.0001);
            assertEquals(1.0,l_firstAdddepositInfoBk.firstMarginDepositInDe,0.0001);
            assertEquals(1.0,l_firstAdddepositInfoBk.firstMarginDepositInDeExpect,0.0001);
            assertEquals(1.0,l_firstAdddepositInfoBk.firstSettledContract,0.0001);
            assertEquals(1.0,l_firstAdddepositInfoBk.firstUncancelAmt,0.0001);
            assertEquals(1.0,l_firstAdddepositInfoBk.firstUncancelSettleRequiredAmt,0.0001);
            assertEquals(true,flag);
            
            
        }
        catch (Exception l_ex)
        {
            log.error("has errros", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * WEB3GentradeMainAccount is null
     * 抛出WEB3SystemLayerException異常，異常code為：
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testGetTPTradingStop_Case001()
    {
    	
        final String STR_METHOD_NAME = "testGetTPTradingStop_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
           WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
           l_impl.getTPTradingStop(null);
           fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
			log.error(STR_METHOD_NAME,l_ex);
			assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("has errros", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * l_tradingpowerCalcConditionParams.getTradingStop()
     * (this.get顧客余力条件Paramsの戻り値.get取引停止区分で取得した値を返却する)
     */
    public void testGetTPTradingStop_Case002()
    {
        final String STR_METHOD_NAME = "testGetTPTradingStop_Case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(55464654654L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        	TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
        	TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
        	TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
        	WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
        	String l_tradingStopDiv1= l_impl.getTPTradingStop(l_mainAccount);
        	assertEquals("1",l_tradingStopDiv1);
        	
        }	
        catch (Exception l_ex)
        {
             log.error("has errros", l_ex);
             log.exiting(TEST_END + STR_METHOD_NAME);
             fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 返却値　@=　@"0"
     */
    public void testGetTPTradingStop_Case003()
    {
        final String STR_METHOD_NAME = "testGetTPTradingStop_Case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(55464654654L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);

        	WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
        	String l_tradingStopDiv2= l_impl.getTPTradingStop(l_mainAccount);
        	assertEquals("0",l_tradingStopDiv2);
        	
        }	
        catch (Exception l_ex)
        {
             log.error("has errros", l_ex);
             log.exiting(TEST_END + STR_METHOD_NAME);
             fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 方法@的返回?(検索結果が取得できなかった場合はnullを返却するnull)
     */
    public void testGetTradingpowerCalcConditionParams_Case001()
    {
        final String STR_METHOD_NAME = "testGetTradingpowerCalcConditionParams_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
    	TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
    	TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
    	TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
    	WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
    	TradingpowerCalcConditionParams l_tradingpowerCalcConditionParamsBK= l_impl.getTradingpowerCalcConditionParams(new Long(333812512246L));
    	assertEquals( null,l_tradingpowerCalcConditionParamsBK);
    	
        }
        catch (Exception l_ex)
        {
             log.error("has errros", l_ex);
             log.exiting(TEST_END + STR_METHOD_NAME);
             fail();
        }        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 方法@的返回?(TradingpowerCalcConditionParams)
     */
    public void testGetTradingpowerCalcConditionParams_Case002()
    {
        final String STR_METHOD_NAME = "testGetTradingpowerCalcConditionParams_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
    	TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
    	TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
    	TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

    	WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
    	TradingpowerCalcConditionParams l_tradingpowerCalcConditionParamsBK= l_impl.getTradingpowerCalcConditionParams(new Long(55464654654L));
    	assertEquals( l_tradingpowerCalcConditionParams,l_tradingpowerCalcConditionParamsBK);

        }
        catch (Exception l_ex)
        {
             log.error("has errros", l_ex);
             log.exiting(TEST_END + STR_METHOD_NAME);
             fail();
        }        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 抛出WEB3SystemLayerException異常，異常code為：
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testGetAdditionalDepositStop_Case001()
    {
        final String STR_METHOD_NAME = "testGetAdditionalDepositStop_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
        WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
        l_impl.getAdditionalDepositStop(null);
        fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
			log.error(STR_METHOD_NAME,l_ex);
			assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
             log.error("has errros", l_ex);
             log.exiting(TEST_END + STR_METHOD_NAME);
             fail();
        }        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * l_tradingpowerCalcConditionParams is not null 
     * 返却値　@=　@追証未入金区分
     */
    public void testGetAdditionalDepositStop_Case002()
    {
        final String STR_METHOD_NAME = "testGetAdditionalDepositStop_Case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(55464654654L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        	TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
        	TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
            
            String l_additionalDepositStop = l_impl.getAdditionalDepositStop(l_mainAccount);
            
            assertEquals("1",l_additionalDepositStop);
        }
        catch (Exception l_ex)
        {
             log.error("has errros", l_ex);
             log.exiting(TEST_END + STR_METHOD_NAME);
             fail();
        }   
    }

    /*
     * l_tradingpowerCalcConditionParams is null 
     * 返却値　@=　@追証未入金区分
     */
    public void testGetAdditionalDepositStop_Case003()
    {
        final String STR_METHOD_NAME = "testGetAdditionalDepositStop_Case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        	TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
        	TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
        	TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
            String l_additionalDepositStop = l_impl.getAdditionalDepositStop(l_mainAccount);
            assertEquals("0",l_additionalDepositStop);
        }
        catch (Exception l_ex)
        {
             log.error("has errros", l_ex);
             log.exiting(TEST_END + STR_METHOD_NAME);
             fail();
        }   
    }
}
@
