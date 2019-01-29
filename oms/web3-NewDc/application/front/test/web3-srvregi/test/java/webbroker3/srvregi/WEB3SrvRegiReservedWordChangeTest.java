head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.43.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiReservedWordChangeTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        :  (WEB3SrvRegiReservedWordChangeTest)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2009/04/28 車進 (中訊) 新規作成 モデル411
Revision History : 2010/08/19 趙天月(中訊) 仕様変更モデルNo.427,431,432
Revesion History : 2010/09/14 劉レイ (中訊) モデル433,434
*/
package webbroker3.srvregi;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvRegiKeyParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiStartInfoServiceImpl;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiReservedWordChangeTest extends TestBaseForMock{

	   /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiReservedWordChangeTest.class);
    
    public WEB3SrvRegiReservedWordChangeTest(String arg0)
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
     * 【WEB3】【サービス利用】単体テスト仕様兼報告書NO.495
     * 予約語：大証FXログインID”の場合
     * サービス情報管理.getサービスマスター().get付加区分()＝＝nullの場合
     * 、「付加区分がnullです。」例外をスローする。
     */
    public void testReplaceReservedWord_001()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_001()";
        log.entering(STR_METHOD_NAME);
 
    	WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
    		new WEB3SrvRegiReservedWordChange(
    				"0D",
    				"1",
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null
    				);
    	
    	String l_strRegValue = "%%OSE_LOGINID%%";
    	try
    	{
    		SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
    		l_srvRegMaster.setInstitutionCode("0D");
    		l_srvRegMaster.setSrvDiv("1");
    	    TestDBUtility.insertWithDel(l_srvRegMaster);

        	l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
    	}
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03160, l_exBE.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    	
    }
    
    /**
     * 【WEB3】【サービス利用】単体テスト仕様兼報告書NO.496
     * 予約語：大証FXログインID”の場合
     * サービス情報管理.getサービスマスター().get付加区分()!＝nullの場合
     * get付加区分()の戻り値+this.顧客コード.substring(0,6)をセットする。 
     */
    public void testReplaceReservedWord_002()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_002()";
        log.entering(STR_METHOD_NAME);
 
    	WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
    		new WEB3SrvRegiReservedWordChange(
    				"0D",
    				"1",
    				null,
    				"12345678",
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null,
    				null
    				);
    	
    	String l_strRegValue = "%%OSE_LOGINID%%";
    	try
    	{
    		SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
    		l_srvRegMaster.setInstitutionCode("0D");
    		l_srvRegMaster.setSrvDiv("1");
    	    TestDBUtility.insertWithDel(l_srvRegMaster);

            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("0D");
            l_srvRegimasterparams.setSrvDiv("1");
            l_srvRegimasterparams.setSrvUseKeyType("8");
            l_srvRegimasterparams.setSrvUseId(1l);
            l_srvRegimasterparams.setSrvUseKey("UserKey");
            TestDBUtility.insertWithDel(l_srvRegimasterparams);
    	    
        	String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
        	assertEquals("UserKey123456",l_strReturnValue);
    	}
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    	
    }
    
    public void testReplaceReservedWord_003()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_003()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1",
                    null,
                    "12345678",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%OSE_LOGINID%%";
        try
        {
            SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
            l_srvRegMaster.setInstitutionCode("0D");
            l_srvRegMaster.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegMaster);

            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("0D");
            l_srvRegimasterparams.setSrvDiv("1");
            l_srvRegimasterparams.setSrvUseKeyType("8");
            l_srvRegimasterparams.setSrvUseId(1l);
            l_srvRegimasterparams.setSrvUseKey("UserKey");
            TestDBUtility.insertWithDel(l_srvRegimasterparams);
            
            String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            assertEquals("UserKey123456",l_strReturnValue);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testReplaceReservedWord_004()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_004()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1",
                    null,
                    "12345678",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%OTHER_SRV_REGI_STATUS%%";
        try
        {
            SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
            l_srvRegMaster.setInstitutionCode("0D");
            l_srvRegMaster.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegMaster);

            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("0D");
            l_srvRegimasterparams.setSrvDiv("1");
            l_srvRegimasterparams.setSrvUseKeyType("8");
            l_srvRegimasterparams.setSrvUseId(2);
            l_srvRegimasterparams.setSrvUseKey("UserKey");
            TestDBUtility.insertWithDel(l_srvRegimasterparams);
            
            TestDBUtility.deleteAll(SrvRegiApplicationRow.TYPE);

            String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            assertEquals("0",l_strReturnValue);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testReplaceReservedWord_005()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_005()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1",
                    "624",
                    "1234567",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%OTHER_SRV_REGI_STATUS%%";
        try
        {
            SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
            l_srvRegMaster.setInstitutionCode("0D");
            l_srvRegMaster.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegMaster);

            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("0D");
            l_srvRegimasterparams.setSrvDiv("1");
            l_srvRegimasterparams.setSrvUseKeyType("8");
            l_srvRegimasterparams.setSrvUseId(2);
            l_srvRegimasterparams.setSrvUseKey("4");
            TestDBUtility.insertWithDel(l_srvRegimasterparams);
            
            TestDBUtility.deleteAll(SrvRegiApplicationRow.TYPE);
            SrvRegiApplicationParams l_SrvRegiApplicationParams =
                TestDBUtility.getSrvRegiApplicationParams();
            l_SrvRegiApplicationParams.setInstitutionCode("0D");
            l_SrvRegiApplicationParams.setBranchCode("624");
            l_SrvRegiApplicationParams.setSrvDiv("4");
            l_SrvRegiApplicationParams.setAccountCode("1234567");
            l_SrvRegiApplicationParams.setCancelDiv("0");
            l_SrvRegiApplicationParams.setEffectiveDiv("0");
            l_SrvRegiApplicationParams.setAppliEndDate(
                WEB3DateUtility.addDay(GtlUtils.getSystemTimestamp(), 1));
            TestDBUtility.insertWithDel(l_SrvRegiApplicationParams);

            String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            assertEquals("1",l_strReturnValue);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_006()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_006()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1",
                    "624",
                    "1234567",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%OTHER_SRV_REGI_STATUS%%";
        try
        {
            SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
            l_srvRegMaster.setInstitutionCode("0D");
            l_srvRegMaster.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegMaster);

            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("0D");
            l_srvRegimasterparams.setSrvDiv("1");
            l_srvRegimasterparams.setSrvUseKeyType("8");
            l_srvRegimasterparams.setSrvUseId(3);
            l_srvRegimasterparams.setSrvUseKey("UserKey");
            TestDBUtility.insertWithDel(l_srvRegimasterparams);
            
            TestDBUtility.deleteAll(SrvRegiApplicationRow.TYPE);

            String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            fail();
        }
        catch (WEB3BusinessLayerException l_exBE)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03160, l_exBE.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_007()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_007()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1",
                    "624",
                    "123456",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%EQUITY_TAXTYPE%%";
        try
        {
            SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
            l_srvRegMaster.setInstitutionCode("0D");
            l_srvRegMaster.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegMaster);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            assertEquals("1", l_strReturnValue);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_008()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_008()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1",
                    "624",
                    "123456",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%EQUITY_TAXTYPE_N%%";
        try
        {
            SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
            l_srvRegMaster.setInstitutionCode("0D");
            l_srvRegMaster.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegMaster);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            assertEquals("2", l_strReturnValue);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_009()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_009()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1",
                    "624",
                    "123456",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%MARGIN_TAXTYPE%%";
        try
        {
            SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
            l_srvRegMaster.setInstitutionCode("0D");
            l_srvRegMaster.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegMaster);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.NORMAL);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            assertEquals("1", l_strReturnValue);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_010()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_010()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1",
                    "624",
                    "1234567",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%MARGIN_TAXTYPE_N%%";
        try
        {
            SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
            l_srvRegMaster.setInstitutionCode("0D");
            l_srvRegMaster.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegMaster);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1234567");
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.SPECIAL);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            assertEquals("2", l_strReturnValue);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_011()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_011()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1",
                    "624",
                    "123456",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%CD_KEY%%";
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
            l_SrvRegiMasterParams.setSrvDiv("1");
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

            String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            assertEquals(l_strReturnValue.length(), 30);
            //2009/05/22
            //assertEquals("3248246912e09bc774509aa3514a22", l_strReturn);
            assertEquals("3248246912", l_strReturnValue.substring(0, 10));
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_012()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_012()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1",
                    "624",
                    "123456",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%MARGIN_TAXTYPE%%";
        try
        {
            SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
            l_srvRegMaster.setInstitutionCode("0D");
            l_srvRegMaster.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegMaster);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setMarginTaxType(null);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            assertEquals("",l_strReturnValue);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_013()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_013()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1",
                    "624",
                    "1234567",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%MARGIN_TAXTYPE_N%%";
        try
        {
            SrvRegiMasterParams l_srvRegMaster= TestDBUtility.getSrvRegiMasterRow();
            l_srvRegMaster.setInstitutionCode("0D");
            l_srvRegMaster.setSrvDiv("1");
            TestDBUtility.insertWithDel(l_srvRegMaster);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1234567");
            l_mainAccountParams.setMarginTaxTypeNext(null);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            String l_strReturnValue = l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            assertEquals("",l_strReturnValue);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_014()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_014()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1234",
                    "381",
                    "2512246",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%ACC_TRANS_VALUE%%";
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

            l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_015()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_015()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1234",
                    "381",
                    "2512246",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%ACCOUNT_NAME_KANA%%";
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setFamilyNameAlt1("アイウアオ");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            String l_strReturnValue =
                l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            
            assertEquals("アイウアオ",l_strReturnValue);
            
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_016()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_016()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1234",
                    "381",
                    "2512246",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%ZIP_CODE%%";
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setZipCode("0359848");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            String l_strReturnValue =
                l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            
            assertEquals("0359848",l_strReturnValue);
            
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_017()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_017()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1234",
                    "381",
                    "2512246",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%ADDRESS1%%";
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAddressLine1("住所１住所１");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            String l_strReturnValue =
                l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            
            assertEquals("住所１住所１",l_strReturnValue);
            
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_018()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_018()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1234",
                    "381",
                    "2512246",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%ADDRESS2%%";
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAddressLine2("住所２住所２");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            String l_strReturnValue =
                l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            
            assertEquals("住所２住所２",l_strReturnValue);
            
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_019()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_019()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1234",
                    "381",
                    "2512246",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%ADDRESS3%%";
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAddressLine3("住所３住所３");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            String l_strReturnValue =
                l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            
            assertEquals("住所３住所３",l_strReturnValue);
            
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_020()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_020()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1234",
                    "123",
                    "1234567",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%FX_LOGIN_ID%%";
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            String l_strReturnValue =
                l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            
            assertEquals("23123456",l_strReturnValue);
            
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testReplaceReservedWord_021()
    {
        final String STR_METHOD_NAME = "testReplaceReservedWord_021()";
        log.entering(STR_METHOD_NAME);
 
        WEB3SrvRegiReservedWordChange l_srvRegResWordChange =
            new WEB3SrvRegiReservedWordChange(
                    "0D",
                    "1234",
                    "123",
                    "1234567",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
        
        String l_strRegValue = "%%ACCOUNT_CODE_6DIGIT%%";
        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            String l_strReturnValue =
                l_srvRegResWordChange.replaceReservedWord(l_strRegValue);
            
            assertEquals("123456",l_strReturnValue);
            
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
}
@
