head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.52.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/13 金傑 (中訊) 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest extends TestBaseForMock
{
    private String l_strInstitutionCode;

    private String l_strBranchCode;

    private String l_strMainAccountCode;

    private String l_strServiceDiv;

    private String l_strAppliLotDiv;

    private Timestamp l_tsAppliStartDate;

    private Timestamp l_tsAppliEndDate;

    private WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl l_serviceImpl = null;
    
    private List l_lisSearchResult = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl.class);

    public WEB3AdminSrvRegiAccountDataUploadUnitServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
        this.l_serviceImpl = new WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl();
        this.initData();
        this.setMockMethod();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        this.l_lisSearchResult = null;
        super.checkMethodValue();
        TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
        super.tearDown();
    }
    
    /**
     * 插入成功，正常結束
     *
     */
    public void testInsertSrvApplyAttribute_C0001()
    {
        final String STR_METHOD_NAME = "testInsertSrvApplyAttribute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    l_mainAccount);
            
            this.l_strInstitutionCode = "3F";
            this.l_strBranchCode = "368";
            this.l_strMainAccountCode = "1234567";
            this.l_strServiceDiv = "1357";
            this.l_strAppliLotDiv = "1";
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,13);
            
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            this.l_tsAppliStartDate = st;
            
            ca =  Calendar.getInstance();
            ca.set(2007,6-1,17);
            
            date = ca.getTime();
            st = new Timestamp(date.getTime());
            this.l_tsAppliEndDate = st;
            
            this.l_serviceImpl.insertSrvApplyAttribute(
                this.l_strInstitutionCode,
                this.l_strBranchCode,
                this.l_strMainAccountCode,
                this.l_strServiceDiv,
                this.l_strAppliLotDiv,
                this.l_tsAppliStartDate,
                this.l_tsAppliEndDate);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount", 
                    new Class[] { String.class,String.class,String.class });
            
            assertEquals("3F",((String)l_paramsValue2.getFirstCalled()[0]));
            assertEquals("368",((String)l_paramsValue2.getFirstCalled()[1]));
            assertEquals("1234567",((String)l_paramsValue2.getFirstCalled()[2]));
            
            this.getSearchResult();
            
            assertNotNull(this.l_lisSearchResult);
            assertEquals(1,this.l_lisSearchResult.size());
            assertEquals("3F",((SrvAppliAttributeRow)this.l_lisSearchResult.get(0)).getInstitutionCode());
            assertEquals("368",((SrvAppliAttributeRow)this.l_lisSearchResult.get(0)).getBranchCode());
            assertEquals("1234567",((SrvAppliAttributeRow)this.l_lisSearchResult.get(0)).getAccountCode());
            assertEquals("1357",((SrvAppliAttributeRow)this.l_lisSearchResult.get(0)).getSrvDiv());
            
            
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 更新成功，正常結束
     *
     */
    public void testUpdateSrvApplyAttribute_C0002()
    {
        final String STR_METHOD_NAME = "testUpdateSrvApplyAttribute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setInstitutionCode("4F");
                l_mainAccountParams.setBranchCode("369");
                l_mainAccountParams.setAccountCode("7654321");
                TestDBUtility.insertWithDel(l_mainAccountParams);
                
            }
            catch (Exception l_ex)
            {
                log.error("", l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            
            this.l_strInstitutionCode = "4F";
            this.l_strBranchCode = "369";
            this.l_strMainAccountCode = "7654321";
            this.l_strServiceDiv = "2468";
            this.l_strAppliLotDiv = "2";
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,14);
            
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            this.l_tsAppliStartDate = st;
            
            ca =  Calendar.getInstance();
            ca.set(2007,6-1,18);
            
            date = ca.getTime();
            st = new Timestamp(date.getTime());
            this.l_tsAppliEndDate = st;
            
            this.insertSrvAppliAttributeRow();
            
            this.l_serviceImpl.updateSrvApplyAttribute(
                this.l_strInstitutionCode,
                this.l_strBranchCode,
                this.l_strMainAccountCode,
                this.l_strServiceDiv,
                this.l_strAppliLotDiv,
                this.l_tsAppliStartDate,
                this.l_tsAppliEndDate);
            
            
            this.getSearchResult();
            
            assertEquals("4F",((SrvAppliAttributeRow)this.l_lisSearchResult.get(0)).getInstitutionCode());
            assertEquals("369",((SrvAppliAttributeRow)this.l_lisSearchResult.get(0)).getBranchCode());
            assertEquals("7654321",((SrvAppliAttributeRow)this.l_lisSearchResult.get(0)).getAccountCode());
            assertEquals("2468",((SrvAppliAttributeRow)this.l_lisSearchResult.get(0)).getSrvDiv());
            assertEquals("2",((SrvAppliAttributeRow)this.l_lisSearchResult.get(0)).getAppliAttribute());
            assertEquals(0,WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20070614","yyyyMMdd"),
                    ((SrvAppliAttributeRow)this.l_lisSearchResult.get(0)).getAppliStartDate()));
            assertEquals(0,WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20070618","yyyyMMdd"),
                    ((SrvAppliAttributeRow)this.l_lisSearchResult.get(0)).getAppliEndDate()));
            
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「deleteサービス申込属性」刪除成功
     *
     */
    public void testDeleteSrvApplyAttribute_C0001()
    {
        final String STR_METHOD_NAME = "testDeleteSrvApplyAttribute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("1234567");
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    l_mainAccount);
            
            this.l_strInstitutionCode = "1D";
            this.l_strBranchCode = "381";
            this.l_strMainAccountCode = "1234567";
            this.l_strServiceDiv = "1";
            this.l_strAppliLotDiv = "2";
            
            this.insertSrvAppliAttributeRow();

            int l_intDelCount = this.l_serviceImpl.deleteSrvApplyAttribute(this.l_strInstitutionCode,this.l_strBranchCode,this.l_strMainAccountCode,this.l_strServiceDiv);
            
            this.getSearchResult();
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount", 
                    new Class[] { String.class,String.class,String.class });
            
            assertEquals("1D",((String)l_paramsValue1.getFirstCalled()[0]));
            assertEquals("381",((String)l_paramsValue1.getFirstCalled()[1]));
            assertEquals("1234567",((String)l_paramsValue1.getFirstCalled()[2]));
            assertEquals(1,l_intDelCount);
            assertEquals(0,this.l_lisSearchResult.size());
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「deleteサービス申込属性」刪除失敗
     *  
     *
     */
    public void testDeleteSrvApplyAttribute_C0002()
    {
        final String STR_METHOD_NAME = "testDeleteSrvApplyAttribute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("1234567");
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    l_mainAccount);
            
            this.l_strInstitutionCode = "1D";
            this.l_strBranchCode = "387";
            this.l_strMainAccountCode = "1234567";
            this.l_strServiceDiv = "1";
            this.l_strAppliLotDiv = "2";
            
            this.insertSrvAppliAttributeRow();

            int l_intDelCount = this.l_serviceImpl.deleteSrvApplyAttribute("1F","382","7654321","1");
            
            this.getSearchResult();
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount", 
                    new Class[] { String.class,String.class,String.class });
            
            assertEquals("1F",((String)l_paramsValue1.getFirstCalled()[0]));
            assertEquals("382",((String)l_paramsValue1.getFirstCalled()[1]));
            assertEquals("7654321",((String)l_paramsValue1.getFirstCalled()[2]));
            
            assertEquals(0,l_intDelCount);
            assertEquals(1,this.l_lisSearchResult.size());
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("1D");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void setMockMethod()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("1D");
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void getSearchResult()
    {
        final String STR_METHOD_NAME = "getSearchResult()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            List l_lisWheres = new ArrayList();
            l_lisWheres.add(this.l_strInstitutionCode);
            l_lisWheres.add(this.l_strBranchCode);
            l_lisWheres.add(this.l_strMainAccountCode);
            l_lisWheres.add(this.l_strServiceDiv);
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and　@branch_code = ? ");
            l_sbWhere.append(" and　@account_code = ? ");
            l_sbWhere.append(" and　@srv_div = ? ");
            
            Object[] l_objWheres = new Object[l_lisWheres.size()];
            l_lisWheres.toArray(l_objWheres);
            
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            this.l_lisSearchResult = l_queryProcesser.doFindAllQuery(
                SrvAppliAttributeRow.TYPE,
                l_sbWhere.toString(),
                l_objWheres);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void insertSrvAppliAttributeRow()
    {
        final String STR_METHOD_NAME = "insertSrvAppliAttributeRow()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            //サービス申込属性行.証券会社コード = 引数.証券会社コード
            l_srvAppliAttributeParams.setInstitutionCode(l_strInstitutionCode);

            //サービス申込属性行.部店コード = 引数.登録情報.部店コード
            l_srvAppliAttributeParams.setBranchCode(l_strBranchCode);

            //サービス申込属性行.口座コード = １-２-１）で取得した顧客コード
            l_srvAppliAttributeParams.setAccountCode(l_strMainAccountCode);

            //サービス申込属性行.サービス区分 = 引数.サービス区分
            l_srvAppliAttributeParams.setSrvDiv(l_strServiceDiv);

            //サービス申込属性行.申込属性区分 = 引数.申込抽選区分
            l_srvAppliAttributeParams.setAppliAttribute(l_strAppliLotDiv);

            //サービス申込属性行.適用期間From = 引数.適用開始日
            l_srvAppliAttributeParams.setAppliStartDate(l_tsAppliStartDate);

            //サービス申込属性行.適用期間To = 引数.適用終了日
            l_srvAppliAttributeParams.setAppliEndDate(l_tsAppliEndDate);

            //サービス申込属性行.更新者コード = セッションから取得した管理者コード
            String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
            l_srvAppliAttributeParams.setLastUpdater(l_strAdministratorCode);

            //サービス申込属性行.作成日時 = 現在日時
            l_srvAppliAttributeParams.setCreatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());

            //サービス申込属性行.更新日時 = 現在日時
            l_srvAppliAttributeParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_srvAppliAttributeParams);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
