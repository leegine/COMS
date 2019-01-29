head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.32.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AdminAccInfoCampaignAccOpenChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設条件指定変更サービスImplテスト(WEB3AdminAccInfoCampaignAccOpenChangeServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 魏 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import test.util.TestDBUtility;

import webbroker3.accountinfo.WEB3AdminAccInfoCampaignSearchCondition;
import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqBalanceReferenceRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignAccOpenChangeServiceImplTest extends TestBaseForMock
{

    WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl l_impl
        = new WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl();

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenChangeServiceImplTest.class);

    private class WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock
        extends WEB3AdminAccInfoCampaignAccOpenConfirmRequest
        {
            public void validate()
                {
                    
                }
        
        }
    
    private class WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock
        extends WEB3AdminAccInfoCampaignAccOpenCompleteRequest
    {
        public void validate()
            {
                
            }
    
    }

    public WEB3AdminAccInfoCampaignAccOpenChangeServiceImplTest(String name)
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

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl()'
     */
    public void testWEB3AdminAccInfoCampaignAccOpenChangeServiceImpl()
    {

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.execute(WEB3GenRequest)'
     * Request為null的場合
     */
    public void testExecute_001()
    {
        try
        {
            l_impl.execute(null);
            fail();
        }catch(WEB3BaseRuntimeException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, e.getErrorInfo());
            log.info("Requestがnull場合成功しました");
        }catch(Exception e)
        {
            fail();
        }

    }
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.execute(WEB3GenRequest)'
     * 入力request非期待的request
     */
    public void testExecute_002()
    {
        WEB3FeqBalanceReferenceRequest l_request = new WEB3FeqBalanceReferenceRequest();
        try
        {
            l_impl.execute(l_request);
            fail();
        }catch(WEB3BaseRuntimeException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, e.getErrorInfo());
            log.info("Requestが指定以外の場合成功しました");
        }catch(Exception e)
        {
            fail();
        }
    }
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.execute(WEB3GenRequest)'
     * 入力request為WEB3AdminAccInfoCampaignAccOpenInputRequest
     */
    public void testExecute_003()
    {

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getCampaignCondition",
                    new Class[] {String.class},
                    l_info);
            
            WEB3AdminAccInfoCampaignAccOpenInputRequest l_request
                = new WEB3AdminAccInfoCampaignAccOpenInputRequest();
            
            l_request.updateFlag = WEB3AccInfoUpdateFlagDef.UPDATE;
            l_request.campaignId = "campaignId";

            WEB3AdminAccInfoCampaignAccOpenInputResponse l_response = (WEB3AdminAccInfoCampaignAccOpenInputResponse)l_impl.execute(l_request);
            
            WEB3MockObjectParamsValue l_value =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                          "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                          "getCampaignCondition",
                              new Class[] {String.class});
                                  
            assertEquals("campaignId", l_value.getCalled(0)[0]);
                           
            assertEquals(l_response.commissionCampaignInfo, l_info);

            log.info("入力request為WEB3AdminAccInfoCampaignAccOpenInputRequestの場合 成功しました");
        } catch (WEB3BaseException e1)
        {
            fail();
        }catch (Exception e1)
        {
            fail();
        }

    }
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.execute(WEB3GenRequest)'
     * 入力request為WEB3AdminAccInfoCampaignAccOpenConfirmRequest
     */
    public void testExecute_004()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
                        

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},"TargetPeriod"
                    );      
   
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(true)
                    );      
            
            WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock();
        
            l_request.updateFlag = "1";
            l_request.commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
            WEB3AdminAccInfoCampaignAccOpenConfirmResponse l_response = 
                (WEB3AdminAccInfoCampaignAccOpenConfirmResponse) l_impl.execute(l_request);
                        
            assertEquals(l_response.alertFlag, "TargetPeriod");
            log.info("入力request為WEB3AdminAccInfoCampaignAccOpenConfirmRequest成功完了しました");
            
        
        }catch(WEB3BaseException e)
        {
            fail();

        }catch(Exception e)
        {
            fail();
        }
    
    }
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.execute(WEB3GenRequest)'
     * 入力request為WEB3AdminAccInfoCampaignAccOpenCompleteRequest
     */
    public void testExecute_005()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        try
        {
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
  
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(true)
                    );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getSameCampaignCondition",
                    new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                    null
                    );      
            

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "insertCampaignCondition",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},null
                    );      

            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
        
            l_request.updateFlag = "0";
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.institutionCode = "0D";
            l_info.branchCode = "381";
            l_info.collectRate = "100";
            l_request.commissionCampaignInfo = l_info;
            
            WEB3AdminAccInfoCampaignAccOpenCompleteResponse l_response = 
                (WEB3AdminAccInfoCampaignAccOpenCompleteResponse) l_impl.execute(l_request);
            
            
            WEB3MockObjectParamsValue l_value =
            TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                      "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                      "insertCampaignCondition",
                          new Class[] {WEB3AccInfoCampaignInfo.class,String.class});
            
              
            assertEquals(l_administratorSet.getAdministratorCode(), l_value.getCalled(0)[1]);
            assertEquals(l_response.alertFlag, null);
            log.info("入力request為WEB3AdminAccInfoCampaignAccOpenCompleteRequest成功完了しました");
            
            
        
        }catch(WEB3BaseException e)
        {
            fail();

        }catch(Exception e)
        {
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.getInputScreen(WEB3AdminAccInfoCampaignAccOpenInputRequest)'
     * validate調用チェック
     * 抛出BUSINESS_ERROR_02721
     */
    public void testGetInputScreen_001()
    {
        WEB3AdminAccInfoCampaignAccOpenInputRequest l_request
            = new WEB3AdminAccInfoCampaignAccOpenInputRequest();
        
        l_request.updateFlag = WEB3AccInfoUpdateFlagDef.DELETE;
        
        try
        {
            l_impl.getInputScreen(l_request);
            fail();
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02721, e.getErrorInfo());
            log.info("validate方法@を調用しました");
        }
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.getInputScreen(WEB3AdminAccInfoCampaignAccOpenInputRequest)'
     * validate権限チェック
     * 抛出BUSINESS_ERROR_01056
     */
    public void testGetInputScreen_002()
    {

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, false);
           
            WEB3AdminAccInfoCampaignAccOpenInputRequest l_request
                = new WEB3AdminAccInfoCampaignAccOpenInputRequest();
            
            l_request.updateFlag = WEB3AccInfoUpdateFlagDef.LOGIN;
            
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, e.getErrorInfo());
            log.info("validate権限()を調用しました");
        }catch(Exception l_ex)
        {
            fail();
        }
        
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.getInputScreen(WEB3AdminAccInfoCampaignAccOpenInputRequest)'
     * validate部店権限 チェック
     * 抛出BUSINESS_ERROR_01074
     */
    public void testGetInputScreen_003()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        WEB3Administrator l_administratorSet = null;
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                new WEB3AccInfoCampaignInfo());



            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", false);
            
            
            WEB3AdminAccInfoCampaignAccOpenInputRequest l_request
                = new WEB3AdminAccInfoCampaignAccOpenInputRequest();
            
            l_request.updateFlag = WEB3AccInfoUpdateFlagDef.UPDATE;
            l_request.campaignId = "campaignId";
            
           
            l_impl.getInputScreen(l_request);
            
            WEB3MockObjectParamsValue l_value =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                        "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                        "getCampaignCondition",
                        new Class[] {String.class});
            assertEquals("campaignId", l_value.getCalled(0)[0]);
            
            fail();
        }
        catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, e.getErrorInfo());
            log.info("testGetInputScreen_003 validate部店権限()を調用しました");
        }
        catch (Exception l_ex)
        {
            log.error("Error in here..", l_ex);
            fail();
        }
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.getInputScreen(WEB3AdminAccInfoCampaignAccOpenInputRequest)'
     * updateFlag = 1
     * 正常
     */
    public void testGetInputScreen_004()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getCampaignCondition",
                    new Class[] {String.class},
                    l_info);      
            
            WEB3AdminAccInfoCampaignAccOpenInputRequest l_request
                = new WEB3AdminAccInfoCampaignAccOpenInputRequest();
            
            l_request.updateFlag = WEB3AccInfoUpdateFlagDef.UPDATE;
            l_request.campaignId = "campaignId";

            WEB3AdminAccInfoCampaignAccOpenInputResponse l_response = l_impl.getInputScreen(l_request);
            
            WEB3MockObjectParamsValue l_value =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                        "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                        "getCampaignCondition",
                        new Class[] {String.class});
            assertEquals("campaignId", l_value.getCalled(0)[0]);
            
            assertEquals(l_response.commissionCampaignInfo, l_info);

            log.info("updateFlag = 1の場合 成功しました");
        } catch (WEB3BaseException e1)
        {
            fail();
        }catch (Exception e1)
        {
            fail();
        }

        
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.getInputScreen(WEB3AdminAccInfoCampaignAccOpenInputRequest)'
     * updateFlag = 0
     * 正常
     */
    public void testGetInputScreen_005()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getCampaignCondition",
                    new Class[] {String.class},
                    l_info);      
            
            WEB3AdminAccInfoCampaignAccOpenInputRequest l_request
                = new WEB3AdminAccInfoCampaignAccOpenInputRequest();
            
            l_request.updateFlag = WEB3AccInfoUpdateFlagDef.UPDATE;
            l_request.campaignId = "campaignId";

            WEB3AdminAccInfoCampaignAccOpenInputResponse l_response = l_impl.getInputScreen(l_request);
            
            WEB3MockObjectParamsValue l_value =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                        "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                        "getCampaignCondition",
                        new Class[] {String.class});
            assertEquals("campaignId", l_value.getCalled(0)[0]);
            
            assertEquals(l_response.commissionCampaignInfo, l_info);

            log.info("updateFlag = 0の場合 成功しました");
        } catch (WEB3BaseException e1)
        {
            fail();
        }catch (Exception e1)
        {
            fail();
        }

        
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * validate 例外的場合(request中updateFlag為3的場合)
     * 抛出BUSINESS_ERROR_02710
     */
    public void testValidateChange_001()
    {
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request
        = new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
    
        l_request.updateFlag = "3";
        
        try
        {
            l_impl.validateChange(l_request);
            fail();
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02710, e.getErrorInfo());
            log.info("validate方法@を調用しました");
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * validate権限
     * 抛出BUSINESS_ERROR_01056
     */
    public void testValidateChange_002()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, false);

            WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock();
        
            l_request.updateFlag = "0";
            
            l_impl.validateChange(l_request);
            fail();
        
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, e.getErrorInfo());
            log.info("validate権限方法@を調用しました");
        }catch(Exception e)
        {
            fail();
        }


    }


    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * validate部店権限
     * 抛出BUSINESS_ERROR_01074
     */
    public void testValidateChange_003()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        WEB3Administrator l_administratorSet = null;
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                new WEB3AccInfoCampaignInfo());

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", false);
                        
            WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock();
        
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
            
            l_impl.validateChange(l_request);
            fail();
        
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, e.getErrorInfo());
            log.info("validate部店権限方法@を調用しました");
        }catch(Exception e)
        {
            fail();
        }


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * validate対象期間 例外的場合(更新処理フラグ == 1)
     * 抛出BUSINESS_ERROR_00989
     */
    public void testValidateChange_004()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
                        
            //WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},
                    new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00989, "testValidateChange_004"));      
   
            WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock();
        
            l_request.updateFlag = "1";
            l_request.commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_impl.validateChange(l_request);
            fail();
        
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00989, e.getErrorInfo());
            log.info("validate対象期間方法@を調用しました");
        }catch(Exception e)
        {
            fail();
        }


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * validate対象期間 例外的場合(更新処理フラグ == 2)
     * 抛出BUSINESS_ERROR_00989
     */
    public void testValidateChange_005()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
                        
            //WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},
                    new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00989, "testValidateChange_005"));      
   
            WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock();
        
            l_request.updateFlag = "2";
            l_request.commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_impl.validateChange(l_request);
            fail();
        
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00989, e.getErrorInfo());
            log.info("validate対象期間を調用しました");
        }catch(Exception e)
        {
            fail();
        }


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * is変更情報返回?為false(更新処理フラグ == 1)
     * 抛出BUSINESS_ERROR_02723
     */
    public void testValidateChange_006()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
                        
            //WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},null
                    );      
   
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(false)
                    );      
            
            WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock();
        
            l_request.updateFlag = "1";
            l_request.commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
            l_impl.validateChange(l_request);
            fail();
        
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02723, e.getErrorInfo());
            log.info("is変更情報返回がfalseの場合");
        }catch(Exception e)
        {
            fail();
        }


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * (更新処理フラグ == 1)
     * 正常
     */
    public void testValidateChange_007()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
                        

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},"TargetPeriod"
                    );      
   
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(true)
                    );      
            
            WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock();
        
            l_request.updateFlag = "1";
            l_request.commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
            WEB3AdminAccInfoCampaignAccOpenConfirmResponse l_response = l_impl.validateChange(l_request);
            assertEquals(l_response.alertFlag, "TargetPeriod");
            log.info("ValidateChange()成功完了しました");
            
        
        }catch(WEB3BaseException e)
        {
            fail();

        }catch(Exception e)
        {
            fail();
        }


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * (更新処理フラグ == 0)
     * isBranchがfalseの場合
     * BUSINESS_ERROR_00779
     */
    public void testValidateChange_008()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        
        try
        {
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
                        

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},"TargetPeriod"
                    );      
   
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(true)
                    );      
            
            WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock();
        
            l_request.updateFlag = "0";
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.institutionCode = "A";
            l_info.branchCode = "B";
            l_request.commissionCampaignInfo = l_info;
            
            l_impl.validateChange(l_request);
            fail();
            
            
        
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, e.getErrorInfo());
            log.info("isBranch()調用しました");

        }catch(Exception e)
        {
            fail();
        }


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * (更新処理フラグ == 0)
     * get重複キャンペーン条件が非nullの場合
     * BUSINESS_ERROR_02724
     */
    public void testValidateChange_009()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        try
        {
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
                        

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},"TargetPeriod"
                    );      
   
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(true)
                    );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getSameCampaignCondition",
                    new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                    new WEB3AccInfoCampaignInfo[3]
                    );      
            
            WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock();
        
            l_request.updateFlag = "0";
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.accountOpenDateFrom = WEB3DateUtility.getDate("2008/02/01","yyyy/MM/dd");
            l_info.institutionCode = "0D";
            l_info.branchCode = "381";
            l_request.commissionCampaignInfo = l_info;
            
            l_impl.validateChange(l_request);
            fail();
            
            
        
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02724, e.getErrorInfo());
            log.info("get重複キャンペーン条件()調用しました");

        }catch(Exception e)
        {
            fail();
        }


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * (更新処理フラグ == 0)
     * commissionCampaignInfo和alertFlag都為null
     * 正常
     */
    public void testValidateChange_010()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        try
        {
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
                        

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},"TargetPeriod"
                    );      
   
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(true)
                    );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getSameCampaignCondition",
                    new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                    null
                    );      
            
            WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock();
        
            l_request.updateFlag = "0";
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.institutionCode = "0D";
            l_info.branchCode = "381";
            l_request.commissionCampaignInfo = l_info;
            
            WEB3AdminAccInfoCampaignAccOpenConfirmResponse l_response = l_impl.validateChange(l_request);
            
            assertEquals(l_response.alertFlag, null);
            assertEquals(l_response.commissionCampaignInfo, null);
            log.info("ValidateChange()成功完了しました");            
                   
        }catch(WEB3BaseException e)
        {
            fail();
        }catch(Exception e)
        {
            fail();
        }


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * (更新処理フラグ == 2)
     * commissionCampaignInfo = getキャンペーン条件(String) alertFlag都為null
     * 正常
     */
    public void testValidateChange_011()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
                        
            WEB3AccInfoCampaignInfo l_web3Info = new WEB3AccInfoCampaignInfo();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getCampaignCondition",
                    new Class[] {String.class},
                    l_web3Info
                    );      

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},"TargetPeriod"
                    );      

            WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenConfirmRequestForMock();
        
            l_request.updateFlag = "2";
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.campaignId = "campaignId";
            l_info.institutionCode = "0D";
            l_info.branchCode = "381";
            l_request.commissionCampaignInfo = l_info;
            
            WEB3AdminAccInfoCampaignAccOpenConfirmResponse l_response = l_impl.validateChange(l_request);
            
            WEB3MockObjectParamsValue l_value =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                        "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                        "getCampaignCondition",
                        new Class[] {String.class});
            assertEquals("campaignId", l_value.getCalled(0)[0]);
            
            assertEquals(l_response.alertFlag, "TargetPeriod");
            assertEquals(l_response.commissionCampaignInfo, l_web3Info);
            log.info("ValidateChange()成功完了しました");            
                   
        }catch(WEB3BaseException e)
        {   e.printStackTrace();
            fail();
        }catch(Exception e)
        {   e.printStackTrace();
            fail();
        }


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.submitChange(WEB3AdminAccInfoCampaignAccOpenCompleteRequest)'
     * validate 例外的場合(request中updateFlag為3的場合)
     * 抛出BUSINESS_ERROR_02710
     */
    public void testSubmitChange_001()
    {
        WEB3AdminAccInfoCampaignAccOpenCompleteRequest l_request
        = new WEB3AdminAccInfoCampaignAccOpenCompleteRequest();
        WEB3AccInfoCampaignInfo commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
    
        l_request.updateFlag = "3";
        l_request.commissionCampaignInfo = commissionCampaignInfo;
        
        try
        {
            l_impl.submitChange(l_request);
            fail();
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02710, e.getErrorInfo());
            log.info("validate方法@を調用しました");
        }catch(Exception e)
        {
            fail();
        }
    }
    
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.submitChange(WEB3AdminAccInfoCampaignAccOpenCompleteRequest)'
     * validate権限
     * 抛出BUSINESS_ERROR_01056
     */
    public void testSubmitChange_002()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, false);

            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
        
            l_request.updateFlag = "0";
            
            l_impl.submitChange(l_request);
            fail();
        
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, e.getErrorInfo());
            log.info("validate権限方法@を調用しました");
        }catch(Exception e)
        {
            fail();
        }


    }    

    
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.submitChange(WEB3AdminAccInfoCampaignAccOpenCompleteRequest)'
     * validate部店権限
     * 抛出BUSINESS_ERROR_01074
     */
    public void testSubmitChange_003()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        WEB3Administrator l_administratorSet = null;
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                new WEB3AccInfoCampaignInfo());

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", false);
                        
            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
        
            l_request.updateFlag = "0";
            l_request.commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
            
            l_impl.submitChange(l_request);
            fail();
        
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, e.getErrorInfo());
            log.info("validate部店権限方法@を調用しました");
        }catch(Exception e)
        {
            fail();
        }


    }    

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.submitChange(WEB3AdminAccInfoCampaignAccOpenCompleteRequest)'
     * validate取引パスワード
     * 抛出BUSINESS_ERROR_00009
     */
    public void testSubmitChange_004()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("password", false);
            
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getCampaignCondition",
                    new Class[] {String.class},
                    l_info);      
            
            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
            
            l_request.updateFlag = WEB3AccInfoUpdateFlagDef.UPDATE;
            l_request.commissionCampaignInfo = l_info;

            l_impl.submitChange(l_request);
            fail();

        } catch (WEB3BaseException e1)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, e1.getErrorInfo());
            log.info("validate取引パスワード方法@を調用しました");
 
        }catch (Exception e1)        
        {
            fail();
        }

    }    

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.submitChange(WEB3AdminAccInfoCampaignAccOpenCompleteRequest)'
     * 更新処理フラグ == 1,validate対象期間 例外的場合
     * 抛出BUSINESS_ERROR_00989
     */
    public void testSubmitChange_005()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
            
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getCampaignCondition",
                    new Class[] {String.class},
                    l_info);      
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},
                    new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00989, "testSubmitChange_005"));      

            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
            
            l_request.updateFlag = WEB3AccInfoUpdateFlagDef.UPDATE;
            l_request.commissionCampaignInfo = l_info;

            l_impl.submitChange(l_request);
            fail();

        } catch (WEB3BaseException e1)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00989, e1.getErrorInfo());
            log.info("validate対象期間方法@を調用しました");
 
        }catch (Exception e1)        
        {
            fail();
        }

    }    

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.submitChange(WEB3AdminAccInfoCampaignAccOpenCompleteRequest)'
     * 更新処理フラグ == 2,validate対象期間 例外的場合
     * 抛出BUSINESS_ERROR_00989
     */
    public void testSubmitChange_006()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
            
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getCampaignCondition",
                    new Class[] {String.class},
                    l_info);      
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},
                    new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00989, "testSubmitChange_006"));      

            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
            
            l_request.updateFlag = WEB3AccInfoUpdateFlagDef.DELETE;
            l_request.commissionCampaignInfo = l_info;

            l_impl.submitChange(l_request);
            fail();

        } catch (WEB3BaseException e1)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00989, e1.getErrorInfo());
            log.info("validate対象期間方法@を調用しました");
 
        }catch (Exception e1)        
        {
            fail();
        }

    }    

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.submitChange(WEB3AdminAccInfoCampaignAccOpenCompleteRequest)'
     * 更新処理フラグ == 1,is変更情報がfalse的場合
     * 抛出BUSINESS_ERROR_02723
     */
    public void testSubmitChange_007()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
            
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getCampaignCondition",
                    new Class[] {String.class},
                    l_info);      
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class},
                    "TargetPeriod");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(false)
                    );      


            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
            
            l_request.updateFlag = WEB3AccInfoUpdateFlagDef.UPDATE;
            l_request.commissionCampaignInfo = l_info;

            l_impl.submitChange(l_request);
            fail();

        } catch (WEB3BaseException e1)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02723, e1.getErrorInfo());
            log.info("is変更情報がfalseの場合");
 
        }catch (Exception e1)        
        {
            fail();
        }

    }    

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.submitChange(WEB3AdminAccInfoCampaignAccOpenCompleteRequest)'
     * 更新処理フラグ == 1
     * 正常
     */
    public void testSubmitChange_008()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
            
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();

            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},
                    "TargetPeriod");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(true)
                    );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "updateCampaignCondition",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},null
                    );      

            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
            
            l_request.updateFlag = WEB3AccInfoUpdateFlagDef.UPDATE;
            l_request.commissionCampaignInfo = l_info;

            WEB3AdminAccInfoCampaignAccOpenCompleteResponse l_response = 
                l_impl.submitChange(l_request);
            
            WEB3MockObjectParamsValue l_value =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                        "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                        "updateCampaignCondition",
                        new Class[] {WEB3AccInfoCampaignInfo.class, String.class});
            
            assertEquals(l_administratorParams.getAdministratorCode(), l_value.getCalled(0)[1]);
            
            assertEquals(l_response.alertFlag, "TargetPeriod");
            log.info("submitChange()成功完了しました");

        } catch (WEB3BaseException e1)
        {
           e1.printStackTrace();
           fail();
 
        }catch (Exception e1)        
        {e1.printStackTrace();
            fail();
        }

    }    

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.submitChange(WEB3AdminAccInfoCampaignAccOpenCompleteRequest)'
     * 更新処理フラグ == 2
     * 正常
     */
    public void testSubmitChange_009()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("331");
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
            
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();

            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},
                    "TargetPeriod");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(true)
                    );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "deleteCampaignCondition",
                    new Class[] {String.class,String.class},null
                    );      



            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
            
            l_request.updateFlag = WEB3AccInfoUpdateFlagDef.DELETE;
            l_request.commissionCampaignInfo = l_info;

            WEB3AdminAccInfoCampaignAccOpenCompleteResponse l_response = 
                l_impl.submitChange(l_request);
            


            WEB3MockObjectParamsValue l_value =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                        "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                        "deleteCampaignCondition",
                        new Class[] {String.class,String.class});
            assertEquals(l_administratorSet.getAdministratorCode(), l_value.getCalled(0)[1]);

            assertEquals(l_response.alertFlag, "TargetPeriod");
            log.info("submitChange()成功完了しました");

        } catch (WEB3BaseException e1)
        {
           e1.printStackTrace();
           fail();
 
        }catch (Exception e1)        
        {    e1.printStackTrace();
            fail();
        }

    }    

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * (更新処理フラグ == 0)
     * isBranchがfalseの場合
     * BUSINESS_ERROR_00779
     */
    public void testSubmitChange_010()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        
        try
        {
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
                        

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class},"TargetPeriod"
                    );      
   
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(true)
                    );      
            
            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
        
            l_request.updateFlag = "0";
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.institutionCode = "A";
            l_info.branchCode = "B";
            l_request.commissionCampaignInfo = l_info;
            
            l_impl.submitChange(l_request);
            fail();
            
            
        
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, e.getErrorInfo());
            log.info("isBranch()調用しました");

        }catch(Exception e)
        {
            fail();
        }


    }

    
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * (更新処理フラグ == 0)
     * get重複キャンペーン条件が非nullの場合
     * BUSINESS_ERROR_02724
     */
    public void testSubmitChange_011()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        try
        {
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "validateTargetPeriod",
                    new Class[] {WEB3AccInfoCampaignInfo.class},"TargetPeriod"
                    );      
   
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(true)
                    );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getSameCampaignCondition",
                    new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                    new WEB3AccInfoCampaignInfo[3]
                    );      
            
            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
        
            l_request.updateFlag = "0";
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.institutionCode = "0D";
            l_info.branchCode = "381";
            l_info.accountOpenDateFrom=WEB3DateUtility.getDate("2007/02/01","yyyy/MM/dd");
            l_request.commissionCampaignInfo = l_info;
            
            l_impl.submitChange(l_request);
            fail();
            
            
        
        }catch(WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02724, e.getErrorInfo());
            log.info("get重複キャンペーン条件()調用しました");

        }catch(Exception e)
        {e.printStackTrace();
            fail();
        }


    }


    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest)'
     * (更新処理フラグ == 0)
     * 正常
     */
    public void testSubmitChange_012()
    {
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        try
        {
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "331", true);
            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
  
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "isChangeInfo",
                    new Class[] {WEB3AccInfoCampaignInfo.class},new Boolean(true)
                    );
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "getSameCampaignCondition",
                    new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                    null
                    );      
            

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                    "insertCampaignCondition",
                    new Class[] {WEB3AccInfoCampaignInfo.class,String.class},null
                    );      

            WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock l_request
                = new WEB3AdminAccInfoCampaignAccOpenCompleteRequestForMock();
        
            l_request.updateFlag = "0";
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.institutionCode = "0D";
            l_info.branchCode = "381";
            l_info.collectRate = "100";
            l_request.commissionCampaignInfo = l_info;
            
            WEB3AdminAccInfoCampaignAccOpenCompleteResponse l_response = 
                l_impl.submitChange(l_request);
            
            
            WEB3MockObjectParamsValue l_value =
            TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                      "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                      "insertCampaignCondition",
                          new Class[] {WEB3AccInfoCampaignInfo.class,String.class});
            
              
            assertEquals(l_administratorSet.getAdministratorCode(), l_value.getCalled(0)[1]);
            assertEquals(l_response.alertFlag, null);
            log.info("submitChange()成功完了しました");
            
            
        
        }catch(WEB3BaseException e)
        {
            fail();

        }catch(Exception e)
        {
            fail();
        }


    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.isBranch(String, String)'
     * 引數証券会社コード或部店コード為null的場合
     * 抛出SYSTEM_ERROR_80017
     */
    
    public void testIsBranch_001()
    {
        try
        {
            Method l_method = WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.class.getDeclaredMethod("isBranch",new Class[]{String.class, String.class});
            l_method.setAccessible(true);
            
            try
            {
                l_method.invoke(new WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl(),new Object[]{null,null});
                fail();
            } catch (IllegalArgumentException e)
            {
                fail();
            } catch (IllegalAccessException e)
            {
                fail();
            } catch (InvocationTargetException e)
            {
                WEB3BaseRuntimeException l_ex = (WEB3BaseRuntimeException) e.getTargetException(); 
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo()); 
                log.info("引數がnullの場合");
            }
            
            
        } catch (SecurityException e)
        {
            fail();
        } catch (NoSuchMethodException e)
        {
            fail();
        }
        
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.isBranch(String, String)'
     * 數據不存在的場合
     * return false
     */
    
    public void testIsBranch_002()
    {
        try
        {
            Method l_method = WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.class.getDeclaredMethod("isBranch",new Class[]{String.class, String.class});
            l_method.setAccessible(true);
            
            try
            {
                Boolean l_blnIsBranch = (Boolean)l_method.invoke(new WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl(),new Object[]{"13","60624"});
                
                assertEquals(false, l_blnIsBranch.booleanValue());
                log.info("is部店()の戻り値がfalse");
            } catch (IllegalArgumentException e)
            {
                fail();
            } catch (IllegalAccessException e)
            {
                fail();
            } catch (InvocationTargetException e)
            {
                fail();
            }
            
            
        } catch (SecurityException e)
        {
            fail();
        } catch (NoSuchMethodException e)
        {
            fail();
        }
        
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.isBranch(String, String)'
     * 數據存在的場合
     * return true
     */
    
    public void testIsBranch_003()
    {
        try
        {
            
            Method l_method = WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.class.getDeclaredMethod("isBranch",new Class[]{String.class, String.class});
            
            l_method.setAccessible(true);

            BranchParams l_params = TestDBUtility.getBranchRow();
            l_params.setBranchCode("333");
            l_params.setInstitutionCode("33");
            
            try
            {
                TestDBUtility.insertWithDel(l_params);
            } catch (WEB3BaseException e)
            {
                fail();
            }

            try
            {
                Boolean l_blnIsBranch = (Boolean)l_method.invoke(new WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl(),new Object[]{"33","333"});
                
                assertEquals(true, l_blnIsBranch.booleanValue());
                log.info("is部店()の戻り値がtrue");
            } catch (IllegalArgumentException e)
            {
                fail();
            } catch (IllegalAccessException e)
            {
                fail();
            } catch (InvocationTargetException e)
            {
                fail();
            }
            
            
        } catch (SecurityException e)
        {
            fail();
        } catch (NoSuchMethodException e)
        {
            fail();
        }
        
    }

}
@
