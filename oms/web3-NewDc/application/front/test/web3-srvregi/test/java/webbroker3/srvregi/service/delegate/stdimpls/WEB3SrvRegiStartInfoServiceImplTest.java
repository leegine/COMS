head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiStartInfoServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvRegiKeyParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiStartInfoServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStartInfoServiceImplTest.class);
    WEB3SrvRegiStartInfoServiceImpl l_impl = null;
    
    public WEB3SrvRegiStartInfoServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3SrvRegiStartInfoServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    public void testCreateEncryptionISAOConnectInfoT_01()
//    {
//        final String STR_METHOD_NAME = "testCreateEncryptionISAOConnectInfoT_01()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
//            l_impl.createEncryptionISAOConnectInfo(null, "jiddk", l_tsTime);
//            fail();
//        }
//        catch (WEB3SystemLayerException l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
//        }
//        catch(Exception l_exc)
//        {
//            log.error(STR_METHOD_NAME, l_exc);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//   
//    public void testCreateEncryptionISAOConnectInfoT_02()
//    {
//        final String STR_METHOD_NAME = "testCreateEncryptionISAOConnectInfoT_03()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
//
//            //SrvRegiKeyParams
//            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
//            SrvRegiKeyParams l_srvregiKeyParams = this.getSrvRegiKeyParams();
//            l_srvregiKeyParams.setSrvUseKey("jiddk");
//            TestDBUtility.insertWithDel(l_srvregiKeyParams);
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            List l_lisResult = l_processor.doFindAllQuery(SrvRegiKeyParams.TYPE);
//            SrvRegiKeyRow l_rsvRegiKeyRow = (SrvRegiKeyRow)l_lisResult.get(0);
//
//            WEB3SrvRegiServiceUseKeyTest l_srvRegiServiceUseKey =
//                new WEB3SrvRegiServiceUseKeyTest(l_rsvRegiKeyRow);
//
//            String l_strResult = l_impl.createEncryptionISAOConnectInfo(
//                l_srvRegiServiceUseKey,
//                "123456789",
//                l_tsTime);
//        }
//        catch (WEB3SystemLayerException l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
//        }
//        catch(Exception l_exc)
//        {
//            log.error(STR_METHOD_NAME, l_exc);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testCreateHashValueT_01()
//    {
//        final String STR_METHOD_NAME = "testCreateHashValueT_01()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
//
//            //SrvRegiKeyParams
//            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
//            SrvRegiKeyParams l_srvregiKeyParams = this.getSrvRegiKeyParams();
//            l_srvregiKeyParams.setSrvUseKey("6");
//            l_srvregiKeyParams.setSrvUseKeyType("5");
//            l_srvregiKeyParams.setSrvUseId(1l);
//            TestDBUtility.insertWithDel(l_srvregiKeyParams);
//            
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            l_srvregiKeyParams.setSrvUseKeyType("1");
//            l_processor.doInsertQuery(l_srvregiKeyParams);
//            
//            //SrvRegiMasterRow
//            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
//            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
//            l_srvRegiMasterParams.setInstitutionCode(l_srvregiKeyParams.getInstitutionCode());
//            l_srvRegiMasterParams.setSrvDiv(l_srvregiKeyParams.getSrvDiv());
//            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
//
//
//            String l_strResult = l_impl.createHashValue(
//                l_srvregiKeyParams.institution_code,
//                l_srvregiKeyParams.srv_div,
//                "624",
//                "123456789",
//                l_tsTime,
//                "1",
//                "1001",
//                null,
//                null);
//            
//        }
//        catch (WEB3SystemLayerException l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
//        }
//        catch(Exception l_exc)
//        {
//            log.error(STR_METHOD_NAME, l_exc);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//
//
//    public SrvRegiKeyParams getSrvRegiKeyParams()
//    {
//        SrvRegiKeyParams l_params = new SrvRegiKeyParams();
//        //証券会社コードinstitution_code VARCHAR23   NotNull
//        l_params.setInstitutionCode("0D");
//        //サービス区分srv_div   VARCHAR24   NotNull
//        l_params.setSrvDiv("1001");
//        //利用キー種別区分srv_use_key_type    VARCHAR22   NotNull
//        l_params.setSrvUseKeyType("10");
//        //サービス利用キーIDsrv_use_id    NUMBER18    NotNull
//        l_params.setSrvUseId(123456789l);
//        //サービス利用キーsrv_use_key VARCHAR2256 NotNull
//        l_params.setSrvUseKey("jiddk");
//        //更新者コードlast_updater  VARCHAR220  NotNull
//        l_params.setLastUpdater("20070202");
//        //作成日付created_timestamp   DATE    NotNull
//        Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
//        l_params.setCreatedTimestamp(l_tsTime);
//        //更新日付last_updated_timestamp  DATE    NotNull
//        l_params.setLastUpdatedTimestamp(l_tsTime);
//        return l_params;
//    }
//    
//    public class WEB3SrvRegiServiceUseKeyTest extends WEB3SrvRegiServiceUseKey
//    {
//
//        protected WEB3SrvRegiServiceUseKeyTest(SrvRegiKeyRow l_srvRegiKeyRow) throws WEB3BaseException
//        {
//            super(l_srvRegiKeyRow);
//            // TODO Auto-generated constructor stub
//        }
//        
//    }
    public void testGetCDKey_Case001()
    {
        final String STR_METHOD_NAME = "testGetCDKey_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            Services.unregisterService(WEB3SrvRegiStartInfoService.class);
            Services.registerService(
                WEB3SrvRegiStartInfoService.class,
                new WEB3SrvRegiStartInfoServiceImpl());

            l_queryProcessor.doDeleteAllQuery(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_SrvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_queryProcessor.doInsertQuery(l_SrvRegiMasterParams);
            
            l_queryProcessor.doDeleteAllQuery(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_SrvRegiKeyParams =
                TestDBUtility.getSrvRegiKeyRow();
            l_SrvRegiKeyParams.setInstitutionCode(l_SrvRegiMasterParams.getInstitutionCode());
            l_SrvRegiKeyParams.setSrvDiv(l_SrvRegiMasterParams.getSrvDiv());
            l_SrvRegiKeyParams.setSrvUseKeyType("1");
            
            //1111
            l_queryProcessor.doInsertQuery(l_SrvRegiKeyParams);
            
            //222
            l_SrvRegiKeyParams.setSrvUseKeyType("4");
            l_SrvRegiKeyParams.setSrvUseId(1);
            l_SrvRegiKeyParams.setSrvUseKey("3");
            l_queryProcessor.doInsertQuery(l_SrvRegiKeyParams);
            
            WEB3SrvRegiStartInfoService l_service =
                (WEB3SrvRegiStartInfoService)Services.getService(
                    WEB3SrvRegiStartInfoService.class);
            String l_strReturn = l_service.getCDKey(
                "624",
                "1234567",
                l_SrvRegiMasterParams.getSrvDiv(),
                l_SrvRegiMasterParams.getInstitutionCode());
            assertEquals(l_strReturn.length(), 30);
            //2009/05/22
            //assertEquals("3248246912e09bc774509aa3514a22", l_strReturn);
            assertEquals("3248246912", l_strReturn.substring(0, 10));
            
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetCDKey_Case002()
    {
        final String STR_METHOD_NAME = "testGetCDKey_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            Services.unregisterService(WEB3SrvRegiStartInfoService.class);
            Services.registerService(
                WEB3SrvRegiStartInfoService.class,
                new WEB3SrvRegiStartInfoServiceImpl());

            l_queryProcessor.doDeleteAllQuery(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_SrvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_queryProcessor.doInsertQuery(l_SrvRegiMasterParams);
            
            l_queryProcessor.doDeleteAllQuery(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_SrvRegiKeyParams =
                TestDBUtility.getSrvRegiKeyRow();
            l_SrvRegiKeyParams.setInstitutionCode(l_SrvRegiMasterParams.getInstitutionCode());
            l_SrvRegiKeyParams.setSrvDiv(l_SrvRegiMasterParams.getSrvDiv());
            l_SrvRegiKeyParams.setSrvUseKeyType("1");
            l_SrvRegiKeyParams.setSrvUseKey("12345678901234567890123456789012345678901234567890" +
                    "123456789012345678901234567890123456789012345678901234567890");
            
            //1111
            l_queryProcessor.doInsertQuery(l_SrvRegiKeyParams);
            
            //222
            l_SrvRegiKeyParams.setSrvUseKeyType("4");
            l_SrvRegiKeyParams.setSrvUseId(1);
            l_SrvRegiKeyParams.setSrvUseKey("3");
            l_queryProcessor.doInsertQuery(l_SrvRegiKeyParams);
            
            WEB3SrvRegiStartInfoService l_service =
                (WEB3SrvRegiStartInfoService)Services.getService(
                    WEB3SrvRegiStartInfoService.class);
            String l_strReturn = l_service.getCDKey(
                "624",
                null,
                l_SrvRegiMasterParams.getSrvDiv(),
                l_SrvRegiMasterParams.getInstitutionCode());
            assertEquals(l_strReturn.length(), 24);
            assertEquals("3248", l_strReturn.substring(0, 4));
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetCDKey_Case003()
    {
        final String STR_METHOD_NAME = "testGetCDKey_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            Services.unregisterService(WEB3SrvRegiStartInfoService.class);
            Services.registerService(
                WEB3SrvRegiStartInfoService.class,
                new WEB3SrvRegiStartInfoServiceImpl());

            l_queryProcessor.doDeleteAllQuery(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_SrvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_queryProcessor.doInsertQuery(l_SrvRegiMasterParams);
            
            l_queryProcessor.doDeleteAllQuery(SrvRegiKeyParams.TYPE);
            
            WEB3SrvRegiStartInfoService l_service =
                (WEB3SrvRegiStartInfoService)Services.getService(
                    WEB3SrvRegiStartInfoService.class);
            String l_strReturn = l_service.getCDKey(
                "624",
                null,
                l_SrvRegiMasterParams.getSrvDiv(),
                l_SrvRegiMasterParams.getInstitutionCode());
            fail();
            
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetCDKey_Case004()
    {
        final String STR_METHOD_NAME = "testGetCDKey_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            Services.unregisterService(WEB3SrvRegiStartInfoService.class);
            Services.registerService(
                WEB3SrvRegiStartInfoService.class,
                new WEB3SrvRegiStartInfoServiceImpl());

            l_queryProcessor.doDeleteAllQuery(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_SrvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_queryProcessor.doInsertQuery(l_SrvRegiMasterParams);
            
            l_queryProcessor.doDeleteAllQuery(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_SrvRegiKeyParams =
                TestDBUtility.getSrvRegiKeyRow();
            l_SrvRegiKeyParams.setInstitutionCode(l_SrvRegiMasterParams.getInstitutionCode());
            l_SrvRegiKeyParams.setSrvDiv(l_SrvRegiMasterParams.getSrvDiv());
            l_SrvRegiKeyParams.setSrvUseKeyType("1");
            l_SrvRegiKeyParams.setSrvUseKey("12345678901234567890123456789012345678901234567890" +
                    "123456789012345678901234567890123456789012345678901234567890");
            
            //1111
            l_queryProcessor.doInsertQuery(l_SrvRegiKeyParams);
            
            //222
            l_SrvRegiKeyParams.setSrvUseKeyType("4");
            l_SrvRegiKeyParams.setSrvUseId(1);
            l_SrvRegiKeyParams.setSrvUseKey("3");
            l_queryProcessor.doInsertQuery(l_SrvRegiKeyParams);
            
            WEB3SrvRegiStartInfoService l_service =
                (WEB3SrvRegiStartInfoService)Services.getService(
                    WEB3SrvRegiStartInfoService.class);
            String l_strReturn = l_service.getCDKey(
                "624",
                "12345",
                l_SrvRegiMasterParams.getSrvDiv(),
                l_SrvRegiMasterParams.getInstitutionCode());
            assertEquals(l_strReturn.length(), 29);
            assertEquals("324824690", l_strReturn.substring(0, 9));
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetAccountHolder_Case001()
    {
        final String STR_METHOD_NAME = "testGetAccountHolder_Case001()";
        log.entering(STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Start!!!");
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setFamilyNameAlt1("ナイトウ ｼﾛｳ");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            Class l_serviceClass = WEB3SrvRegiStartInfoServiceImpl.class;
            Method l_getAccountHolderMethod = l_serviceClass.getDeclaredMethod("getAccountHolder",
                new Class[]{String.class, String.class, String.class});
            l_getAccountHolderMethod.setAccessible(true);
            
            WEB3SrvRegiStartInfoServiceImpl l_service = new WEB3SrvRegiStartInfoServiceImpl();
            
            String l_strAccountName =
                (String)l_getAccountHolderMethod.invoke(l_service, new Object[]{"0D", "381", "2512246"});

            assertEquals("ﾅｲﾄｳ ｼﾛｳ           ", l_strAccountName);
            
            log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Pass!!!");
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Fail!!!");
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
            }
            
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public void testGetAccTransValue_Case001()
    {
        final String STR_METHOD_NAME = "testGetAccTransValue_Case001()";
        log.entering(STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Start!!!");
        
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            TestDBUtility.deleteAllAndCommit(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegiKeyParams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegiKeyParams.setSrvUseKeyType("5");
            l_srvRegiKeyParams.setSrvUseId(1);
            l_srvRegiKeyParams.setSrvUseKey("8");
            TestDBUtility.insertWithDelAndCommit(l_srvRegiKeyParams);
            
            SrvRegiKeyParams l_srvRegiKeyParams2 = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegiKeyParams2.setSrvUseKeyType("1");
            l_srvRegiKeyParams2.setSrvUseKey("8");
            TestDBUtility.insertWithDelAndCommit(l_srvRegiKeyParams2);
            
            SrvRegiKeyParams l_srvRegiKeyParams3 = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegiKeyParams3.setSrvUseKeyType("4");
            l_srvRegiKeyParams3.setSrvUseId(1);
            l_srvRegiKeyParams3.setSrvUseKey("6");
            TestDBUtility.insertWithDelAndCommit(l_srvRegiKeyParams3);
            
            TestDBUtility.deleteAllAndCommit(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDelAndCommit(l_srvRegiMasterParams);
            
            
            WEB3SrvRegiStartInfoService l_service = (WEB3SrvRegiStartInfoService)Services.getService(
                WEB3SrvRegiStartInfoService.class);
            
            String l_strValue = l_service.getAccTransValue("0D", "1234", "381", "2512246");
            
            assertEquals("381251224ﾅｲﾄｳ ｼﾛｳ           "
                +"58b09d0c69b0b2c887e66fe5bb14a491ba46290c3d6eb81017c22626895cba31ef9e8417af3299e482de468ff901449f47ea3912ed97d227d6ea1fa2d4cbec6c",
                l_strValue);
            
            log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Pass!!!");
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Fail!!!");
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testCreateHashValue_Case002()
    {
        final String STR_METHOD_NAME = "testCreateHashValue_Case002()";
        log.entering(STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Start!!!");
        
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            
            TestDBUtility.deleteAllAndCommit(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegiKeyParams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegiKeyParams.setSrvUseKeyType("5");
            l_srvRegiKeyParams.setSrvUseId(1);
            l_srvRegiKeyParams.setSrvUseKey("8");
            TestDBUtility.insertWithDelAndCommit(l_srvRegiKeyParams);
            
            SrvRegiKeyParams l_srvRegiKeyParams2 = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegiKeyParams2.setSrvUseKeyType("1");
            l_srvRegiKeyParams2.setSrvUseKey("8");
            TestDBUtility.insertWithDelAndCommit(l_srvRegiKeyParams2);
            
            SrvRegiKeyParams l_srvRegiKeyParams3 = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegiKeyParams3.setSrvUseKeyType("4");
            l_srvRegiKeyParams3.setSrvUseId(1);
            l_srvRegiKeyParams3.setSrvUseKey("6");
            TestDBUtility.insertWithDelAndCommit(l_srvRegiKeyParams3);
            
            TestDBUtility.deleteAllAndCommit(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDelAndCommit(l_srvRegiMasterParams);
            
            
            WEB3SrvRegiStartInfoService l_service = (WEB3SrvRegiStartInfoService)Services.getService(
                WEB3SrvRegiStartInfoService.class);
            
            l_service.createHashValue("0D", "1234", "381", "2512246",
                null, null, null, null, null, null);
            
            log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Fail!!!");
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
            
            log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Pass!!!");
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Fail!!!");
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(SrvRegiKeyParams.TYPE);
                TestDBUtility.deleteAllAndCommit(SrvRegiMasterParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
            }
            
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public void testGetControlTimestamp_Case001()
    {
        final String STR_METHOD_NAME = "testGetControlTimestamp_Case001()";
        log.entering(STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Start!!!");
        
        try
        {
            WEB3SrvRegiStartInfoService l_service = (WEB3SrvRegiStartInfoService)Services.getService(
                WEB3SrvRegiStartInfoService.class);
            
            Date l_datControlTimestamp = l_service.getControlTimestamp(null);
            
            assertEquals(null, l_datControlTimestamp);
            log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Pass!!!");
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Fail!!!");
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetControlTimestamp_Case002()
    {
        final String STR_METHOD_NAME = "testGetControlTimestamp_Case002()";
        log.entering(STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Start!!!");
        
        try
        {
            WEB3SrvRegiStartInfoService l_service = (WEB3SrvRegiStartInfoService)Services.getService(
                WEB3SrvRegiStartInfoService.class);
            
            Date l_datCurrentTime = WEB3DateUtility.getDate("2010/08/18 16:00:00", "yyyy/MM/dd HH:mm:ss");
            Timestamp l_stCurrentTime = new Timestamp(l_datCurrentTime.getTime());
            
            Date l_datControlTimestamp = l_service.getControlTimestamp(l_stCurrentTime);
            
            String l_strCurrentTime = WEB3DateUtility.formatDate(l_datControlTimestamp, "yyyy/MM/dd HH:mm:ss");
            
            assertEquals("2010/08/18 16:00:00", l_strCurrentTime);
            
            log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Pass!!!");
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.info(STR_METHOD_NAME + "---------------------->>>>>>>>>>>>Test Fail!!!");
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
}
@
