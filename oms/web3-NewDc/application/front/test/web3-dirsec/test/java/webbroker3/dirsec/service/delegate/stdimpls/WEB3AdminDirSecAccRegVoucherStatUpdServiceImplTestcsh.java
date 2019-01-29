head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTestcsh.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteRequest;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccVoucherRecordDetail;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTestcsh extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class);

    WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl l_impl;
    public WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTestcsh(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_impl = new WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    public void testExecute_T01()
//    {
//        final String STR_METHOD_NAME = " testExecute_T01()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            WEB3GenRequest l_request = null;
//            l_impl.execute(l_request);
//            fail();
//        }
//        catch(WEB3SystemLayerException l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
//            log.debug(STR_METHOD_NAME + "------------------------>ok");
//        }
//        catch(Exception l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testExecute_T02()
//    {
//        final String STR_METHOD_NAME = " testExecute_T02()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            WEB3AccOpenApplyCompleteRequest l_request =
//                new WEB3AccOpenApplyCompleteRequest();
//            l_impl.execute(l_request);
//            fail();
//        }
//        catch(WEB3SystemLayerException l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_exc.getErrorInfo());
//            log.debug(STR_METHOD_NAME + "------------------------>ok");
//        }
//        catch(Exception l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }

    public void testExecute_T03()
    {
        final String STR_METHOD_NAME = " testExecute_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecAccRegVoucherSearchInpRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchInpRequest();
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);
            
            WEB3AdminDirSecAccRegVoucherSearchInpResponse l_response =
                (WEB3AdminDirSecAccRegVoucherSearchInpResponse)l_impl.execute(l_request);
            log.debug(STR_METHOD_NAME + "------------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testExecute_T04()
    {
        final String STR_METHOD_NAME = " testExecute_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("0D");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("12");
            l_variousInforParams.setBranchCode("3");
            l_variousInforParams.setAccountCode("1234567");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setRequestCode("111");
            l_variousInforParams.setErrorReasonCode("4");
            l_variousInforParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_variousInforParams);
            
            
            WEB3AdminDirSecAccRegVoucherSearchResRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchResRequest();
            l_request.branchCode = l_variousInforParams.getBranchCode();
            l_request.accountCode = l_variousInforParams.getAccountCode();
            l_request.dataCode = l_variousInforParams.getRequestCode();
            l_request.voucherSendDate = "20070808";
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
    
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
    
            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
    
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);
    
    
            //ExpAccountOpenParams
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_openParams = this.getExpAccountOpenParams();
            l_openParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_openParams.setBranchCode(l_request.branchCode);
            l_openParams.setAccountCode(l_request.accountCode);
            TestDBUtility.insertWithDel(l_openParams);
    
            //AccOpenVoucherStatusParams
            TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
            AccOpenVoucherStatusParams l_statusParams =
                this.getAccopenVoucherStatus();
            l_statusParams.setInstitutionCode("0D");
            l_statusParams.setAccOpenRequestNumber("624");
            l_statusParams.setRequestCode(l_request.dataCode);
            l_statusParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_statusParams);
    
            WEB3AdminDirSecAccRegVoucherSearchResResponse l_response =
                (WEB3AdminDirSecAccRegVoucherSearchResResponse)l_impl.execute(l_request);
            WEB3AdminDirSecAccVoucherRecordDetail[] l_accVoucherRecords =
                l_response.accVoucherRecord;
            assertEquals(2, l_accVoucherRecords.length);
            
            //VariousInformRow check
            assertEquals(l_variousInforParams.getInstitutionCode(),
                    l_accVoucherRecords[0].institutionCode);
            assertEquals(l_variousInforParams.getBranchCode(),
                    l_accVoucherRecords[0].branchCode);
            assertEquals(l_variousInforParams.getAccountCode(),
                    l_accVoucherRecords[0].accountCode);
            assertEquals(l_variousInforParams.getRequestNumber(),
                    l_accVoucherRecords[0].requestNumber);
            assertEquals(l_variousInforParams.getRequestCode(),
                    l_accVoucherRecords[0].dataCode);
            assertEquals(l_variousInforParams.getStatus(),
                    l_accVoucherRecords[0].voucherMakeStatus);
            assertEquals(l_variousInforParams.getErrorReasonCode(),
                    l_accVoucherRecords[0].errorReasonCode);
            assertEquals(l_variousInforParams.getSendTimestamp(),
                    l_accVoucherRecords[0].voucherSendTimestamp);
            assertEquals(l_variousInforParams.getReceiptTimestamp(),
                    l_accVoucherRecords[0].voucherRecvTimestamp);
            assertFalse(l_accVoucherRecords[0].voucherFlag);
            assertEquals(l_variousInforParams.getInformDiv(),
                    l_accVoucherRecords[0].infoType);
            assertNull(l_accVoucherRecords[0].voucherNumber);
            
            //AccOpenVoucherStatusRow check
            assertEquals(l_statusParams.getInstitutionCode(),
                    l_accVoucherRecords[1].institutionCode);
            assertEquals(l_request.branchCode, l_accVoucherRecords[1].branchCode);
            assertEquals(l_request.accountCode, l_accVoucherRecords[1].accountCode);
            assertEquals(l_statusParams.getRequestCode(),
                    l_accVoucherRecords[1].dataCode);
            assertEquals(l_statusParams.getAccOpenRequestNumber(),
                    l_accVoucherRecords[1].requestNumber);
            assertEquals(l_statusParams.getVoucherStatus(),
                    l_accVoucherRecords[1].voucherMakeStatus);
            assertEquals(l_statusParams.getErrorCode(), l_accVoucherRecords[1].errorReasonCode);
            assertEquals(l_statusParams.send_timestamp,
                    l_accVoucherRecords[1].voucherSendTimestamp);
            assertEquals(l_statusParams.recv_timestamp,
                    l_accVoucherRecords[1].voucherRecvTimestamp);
            assertTrue(l_accVoucherRecords[1].voucherFlag);
            assertNull(l_accVoucherRecords[1].infoType);
            assertEquals(l_statusParams.getSerialNo(),
                    l_accVoucherRecords[1].voucherNumber);
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_T05()
    {
        final String STR_METHOD_NAME = " testExecute_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecAccRegVoucherStatUpdConfRequest l_request =
                new WEB3AdminDirSecAccRegVoucherStatUpdConfRequest();
            l_request.updateVoucherMakeStatus = "1";
            l_request.updateErrorReasonCode = "123";
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);
            WEB3AdminDirSecAccRegVoucherStatUpdConfResponse l_response =
                (WEB3AdminDirSecAccRegVoucherStatUpdConfResponse)l_impl.execute(l_request);
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_T06()
    {
        final String STR_METHOD_NAME = " testExecute_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("0D");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("12");
            l_variousInforParams.setBranchCode("3");
            l_variousInforParams.setAccountCode("1234567");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setRequestCode("111");
            l_variousInforParams.setErrorReasonCode("4");
            l_variousInforParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_variousInforParams);
            
            //AccOpenVoucherStatusParams
            TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
            AccOpenVoucherStatusParams l_statusParams =
                this.getAccopenVoucherStatus();
            l_statusParams.setInstitutionCode("0D");
            l_statusParams.setAccOpenRequestNumber("624");
            l_statusParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_statusParams);
            
            WEB3AdminDirSecAccRegVoucherStatUpdCompRequest l_request =
                new WEB3AdminDirSecAccRegVoucherStatUpdCompRequest();

            WEB3AdminDirSecAccVoucherRecordDetail[] l_accVoucherRecord =
                new WEB3AdminDirSecAccVoucherRecordDetail[2];
            l_accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_accVoucherRecord[1] = new WEB3AdminDirSecAccVoucherRecordDetail();
            
            l_accVoucherRecord[0].institutionCode = l_variousInforParams.getInstitutionCode();
            l_accVoucherRecord[0].branchCode = l_variousInforParams.getBranchCode();
            l_accVoucherRecord[0].accountCode = l_variousInforParams.getAccountCode();
            l_accVoucherRecord[0].dataCode = l_variousInforParams.getRequestCode();
            l_accVoucherRecord[0].errorReasonCode =l_variousInforParams.getErrorReasonCode();
            l_accVoucherRecord[0].infoType = l_variousInforParams.getInformDiv();
            l_accVoucherRecord[0].requestNumber = l_variousInforParams.getRequestNumber();
            l_accVoucherRecord[0].voucherFlag = false;
            l_accVoucherRecord[0].voucherMakeStatus = l_variousInforParams.getStatus();
            l_accVoucherRecord[0].voucherNumber = null;

            l_accVoucherRecord[1].institutionCode = l_statusParams.getInstitutionCode();
            l_accVoucherRecord[1].branchCode = "3";
            l_accVoucherRecord[1].accountCode = "1234567";
            l_accVoucherRecord[1].dataCode = l_statusParams.getRequestCode();
            l_accVoucherRecord[1].errorReasonCode =l_statusParams.getErrorCode();
            l_accVoucherRecord[1].infoType = null;
            l_accVoucherRecord[1].requestNumber = l_statusParams.getAccOpenRequestNumber();
            l_accVoucherRecord[1].voucherFlag = true;
            l_accVoucherRecord[1].voucherMakeStatus = l_statusParams.getVoucherStatus();
            l_accVoucherRecord[1].voucherNumber = l_statusParams.getSerialNo();

            l_request.accVoucherRecord = l_accVoucherRecord;
            l_request.updateVoucherMakeStatus = "3";
            l_request.updateErrorReasonCode = "1234";
            l_request.password = "123456";

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            //WEB3Administrator
            WEB3AdministratorForMock.mockValidateTradingPassword("1234", true);
            WEB3AdminDirSecAccRegVoucherStatUpdCompResponse l_response =
                (WEB3AdminDirSecAccRegVoucherStatUpdCompResponse)l_impl.execute(l_request);
            WEB3AdminDirSecAccVoucherRecordDetail[] accVoucherRecord =
                l_response.accVoucherRecord;
            assertEquals(2, accVoucherRecord.length);
            assertEquals("3", accVoucherRecord[0].voucherMakeStatus);
            assertEquals("1234", accVoucherRecord[0].errorReasonCode);
            assertEquals("4", accVoucherRecord[1].voucherMakeStatus);
            assertEquals("1234", accVoucherRecord[1].errorReasonCode);

            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchScreen_T01()
    {
        final String STR_METHOD_NAME = "testGetSearchScreen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecAccRegVoucherSearchInpRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchInpRequest();
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(0);
            TestDBUtility.insertWithDel(l_adminTypeParams);
            
            l_impl.getSearchScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_exc.getErrorInfo());
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetSearchScreen_T02()
    {
        final String STR_METHOD_NAME = "testGetSearchScreen_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecAccRegVoucherSearchInpRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchInpRequest();
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);
            
            WEB3AdminDirSecAccRegVoucherSearchInpResponse l_resopnse =
                l_impl.getSearchScreen(l_request);
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchResultScreen_T01()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecAccRegVoucherSearchResRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchResRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "1234567";
            l_request.dataCode = "13245";
            l_request.voucherSendDate = "20070808";
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(0);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            l_impl.getSearchResultScreen(l_request);

        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_exc.getErrorInfo());
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetSearchResultScreen_T02()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecAccRegVoucherSearchResRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchResRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "1234567";
            l_request.dataCode = "13245";
            l_request.voucherSendDate = "20070808";
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            //
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            
            l_impl.getSearchResultScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837, l_exc.getErrorInfo());
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testGetSearchResultScreen_T03()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecAccRegVoucherSearchResRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchResRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "1234567";
            l_request.dataCode = "13245";
            l_request.voucherSendDate = "20070808";
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            //
            TestDBUtility.deleteAll(VariousInformRow.TYPE);

            //ExpAccountOpenParams
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_openParams = this.getExpAccountOpenParams();
            l_openParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_openParams.setBranchCode(l_request.branchCode);
            l_openParams.setAccountCode(l_request.accountCode);
            TestDBUtility.insertWithDel(l_openParams);

            l_impl.getSearchResultScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837, l_exc.getErrorInfo());
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetSearchResultScreen_T04()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("0D");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("12");
            l_variousInforParams.setBranchCode("3");
            l_variousInforParams.setAccountCode("1234567");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setRequestCode("111");
            l_variousInforParams.setErrorReasonCode("4");
            l_variousInforParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_variousInforParams);
            
            
            WEB3AdminDirSecAccRegVoucherSearchResRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchResRequest();
            l_request.branchCode = l_variousInforParams.getBranchCode();
            l_request.accountCode = l_variousInforParams.getAccountCode();
            l_request.dataCode = l_variousInforParams.getRequestCode();
            l_request.voucherSendDate = "20070808";
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);


            //ExpAccountOpenParams
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_openParams = this.getExpAccountOpenParams();
            l_openParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_openParams.setBranchCode(l_request.branchCode);
            l_openParams.setAccountCode(l_request.accountCode);
            TestDBUtility.insertWithDel(l_openParams);

            //AccOpenVoucherStatusParams
            TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
            AccOpenVoucherStatusParams l_statusParams =
                this.getAccopenVoucherStatus();
            l_statusParams.setInstitutionCode("0D");
            l_statusParams.setAccOpenRequestNumber("624");
            l_statusParams.setRequestCode(l_request.dataCode);
            l_statusParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_statusParams);

            WEB3AdminDirSecAccRegVoucherSearchResResponse l_response =
                l_impl.getSearchResultScreen(l_request);
            WEB3AdminDirSecAccVoucherRecordDetail[] l_accVoucherRecords =
                l_response.accVoucherRecord;
            assertEquals(2, l_accVoucherRecords.length);
            
            //VariousInformRow check
            assertEquals(l_variousInforParams.getInstitutionCode(),
                    l_accVoucherRecords[0].institutionCode);
            assertEquals(l_variousInforParams.getBranchCode(),
                    l_accVoucherRecords[0].branchCode);
            assertEquals(l_variousInforParams.getAccountCode(),
                    l_accVoucherRecords[0].accountCode);
            assertEquals(l_variousInforParams.getRequestNumber(),
                    l_accVoucherRecords[0].requestNumber);
            assertEquals(l_variousInforParams.getRequestCode(),
                    l_accVoucherRecords[0].dataCode);
            assertEquals(l_variousInforParams.getStatus(),
                    l_accVoucherRecords[0].voucherMakeStatus);
            assertEquals(l_variousInforParams.getErrorReasonCode(),
                    l_accVoucherRecords[0].errorReasonCode);
            assertEquals(l_variousInforParams.getSendTimestamp(),
                    l_accVoucherRecords[0].voucherSendTimestamp);
            assertEquals(l_variousInforParams.getReceiptTimestamp(),
                    l_accVoucherRecords[0].voucherRecvTimestamp);
            assertFalse(l_accVoucherRecords[0].voucherFlag);
            assertEquals(l_variousInforParams.getInformDiv(),
                    l_accVoucherRecords[0].infoType);
            assertNull(l_accVoucherRecords[0].voucherNumber);
            
            //AccOpenVoucherStatusRow check
            assertEquals(l_statusParams.getInstitutionCode(),
                    l_accVoucherRecords[1].institutionCode);
            assertEquals(l_request.branchCode, l_accVoucherRecords[1].branchCode);
            assertEquals(l_request.accountCode, l_accVoucherRecords[1].accountCode);
            assertEquals(l_statusParams.getRequestCode(),
                    l_accVoucherRecords[1].dataCode);
            assertEquals(l_statusParams.getAccOpenRequestNumber(),
                    l_accVoucherRecords[1].requestNumber);
            assertEquals(l_statusParams.getVoucherStatus(),
                    l_accVoucherRecords[1].voucherMakeStatus);
            assertEquals(l_statusParams.getErrorCode(), l_accVoucherRecords[1].errorReasonCode);
            assertEquals(l_statusParams.send_timestamp,
                    l_accVoucherRecords[1].voucherSendTimestamp);
            assertEquals(l_statusParams.recv_timestamp,
                    l_accVoucherRecords[1].voucherRecvTimestamp);
            assertTrue(l_accVoucherRecords[1].voucherFlag);
            assertNull(l_accVoucherRecords[1].infoType);
            assertEquals(l_statusParams.getSerialNo(),
                    l_accVoucherRecords[1].voucherNumber);
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testGetUpdateConfirmScreen_T01()
    {
        final String STR_METHOD_NAME = "testGetUpdateConfirmScreen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecAccRegVoucherStatUpdConfRequest l_request =
                new WEB3AdminDirSecAccRegVoucherStatUpdConfRequest();
            l_request.updateVoucherMakeStatus = "1";
            l_request.updateErrorReasonCode = "123";
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(0);
            TestDBUtility.insertWithDel(l_adminTypeParams);
            WEB3AdminDirSecAccRegVoucherStatUpdConfResponse l_response =
            (WEB3AdminDirSecAccRegVoucherStatUpdConfResponse)l_impl.getUpdateConfirmScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_exc.getErrorInfo());
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUpdateConfirmScreen_T02()
    {
        final String STR_METHOD_NAME = "testGetUpdateConfirmScreen_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecAccRegVoucherStatUpdConfRequest l_request =
                new WEB3AdminDirSecAccRegVoucherStatUpdConfRequest();
            l_request.updateVoucherMakeStatus = "1";
            l_request.updateErrorReasonCode = "123";
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            l_impl.getUpdateConfirmScreen(l_request);
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetUpdateCompleteScreen_T01()
    {
        final String STR_METHOD_NAME = "testGetUpdateCompleteScreen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecAccRegVoucherStatUpdCompRequest l_request =
                new WEB3AdminDirSecAccRegVoucherStatUpdCompRequest();

            WEB3AdminDirSecAccVoucherRecordDetail l_accVoucherRecord =
                new WEB3AdminDirSecAccVoucherRecordDetail();
            l_accVoucherRecord.institutionCode = "0D";
            l_accVoucherRecord.branchCode = "624";
            l_accVoucherRecord.accountCode = "1234567";
            l_accVoucherRecord.dataCode = "123";
            l_accVoucherRecord.errorReasonCode = "1";
            l_accVoucherRecord.infoType = "0";
            l_accVoucherRecord.requestNumber = "321";
            l_accVoucherRecord.voucherFlag = true;
            l_accVoucherRecord.voucherMakeStatus = "1";
            l_accVoucherRecord.voucherNumber = "90";
            l_request.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[]{l_accVoucherRecord};
            l_request.updateVoucherMakeStatus = "1";
            l_request.updateErrorReasonCode = "1234";
            l_request.password = "123456";
                
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(0);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            l_impl.getUpdateCompleteScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_exc.getErrorInfo());
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUpdateCompleteScreen_T02()
    {
        final String STR_METHOD_NAME = "testGetUpdateCompleteScreen_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("0D");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("12");
            l_variousInforParams.setBranchCode("3");
            l_variousInforParams.setAccountCode("1234567");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setRequestCode("111");
            l_variousInforParams.setErrorReasonCode("4");
            l_variousInforParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_variousInforParams);
            
            //AccOpenVoucherStatusParams
            TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
            AccOpenVoucherStatusParams l_statusParams =
                this.getAccopenVoucherStatus();
            l_statusParams.setInstitutionCode("0D");
            l_statusParams.setAccOpenRequestNumber("624");
            l_statusParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_statusParams);
            
            WEB3AdminDirSecAccRegVoucherStatUpdCompRequest l_request =
                new WEB3AdminDirSecAccRegVoucherStatUpdCompRequest();

            WEB3AdminDirSecAccVoucherRecordDetail[] l_accVoucherRecord =
                new WEB3AdminDirSecAccVoucherRecordDetail[2];
            l_accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_accVoucherRecord[1] = new WEB3AdminDirSecAccVoucherRecordDetail();
            
            l_accVoucherRecord[0].institutionCode = l_variousInforParams.getInstitutionCode();
            l_accVoucherRecord[0].branchCode = l_variousInforParams.getBranchCode();
            l_accVoucherRecord[0].accountCode = l_variousInforParams.getAccountCode();
            l_accVoucherRecord[0].dataCode = l_variousInforParams.getRequestCode();
            l_accVoucherRecord[0].errorReasonCode =l_variousInforParams.getErrorReasonCode();
            l_accVoucherRecord[0].infoType = l_variousInforParams.getInformDiv();
            l_accVoucherRecord[0].requestNumber = l_variousInforParams.getRequestNumber();
            l_accVoucherRecord[0].voucherFlag = false;
            l_accVoucherRecord[0].voucherMakeStatus = l_variousInforParams.getStatus();
            l_accVoucherRecord[0].voucherNumber = null;

            l_accVoucherRecord[1].institutionCode = l_statusParams.getInstitutionCode();
            l_accVoucherRecord[1].branchCode = "3";
            l_accVoucherRecord[1].accountCode = "1234567";
            l_accVoucherRecord[1].dataCode = l_statusParams.getRequestCode();
            l_accVoucherRecord[1].errorReasonCode =l_statusParams.getErrorCode();
            l_accVoucherRecord[1].infoType = null;
            l_accVoucherRecord[1].requestNumber = l_statusParams.getAccOpenRequestNumber();
            l_accVoucherRecord[1].voucherFlag = true;
            l_accVoucherRecord[1].voucherMakeStatus = l_statusParams.getVoucherStatus();
            l_accVoucherRecord[1].voucherNumber = l_statusParams.getSerialNo();

            l_request.accVoucherRecord = l_accVoucherRecord;
            l_request.updateVoucherMakeStatus = "3";
            l_request.updateErrorReasonCode = "1234";
            l_request.password = "123456";

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            //WEB3Administrator
            WEB3AdministratorForMock.mockValidateTradingPassword("1234", true);
            WEB3AdminDirSecAccRegVoucherStatUpdCompResponse l_response =
                l_impl.getUpdateCompleteScreen(l_request);
            WEB3AdminDirSecAccVoucherRecordDetail[] accVoucherRecord =
                l_response.accVoucherRecord;
            assertEquals(2, accVoucherRecord.length);
            assertEquals("3", accVoucherRecord[0].voucherMakeStatus);
            assertEquals("1234", accVoucherRecord[0].errorReasonCode);
            assertEquals("4", accVoucherRecord[1].voucherMakeStatus);
            assertEquals("1234", accVoucherRecord[1].errorReasonCode);
            log.debug(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public static ExpAccountOpenParams getExpAccountOpenParams()
    {
        ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams();

        //1 証券会社コード  institution_codeVARCHAR23Notnull
        l_expAccountOpenParams.setInstitutionCode("123");
        //2 証券会社ID  institution_idNUMBER18Notnull
        l_expAccountOpenParams.setInstitutionId(123);
        //3 部店ＩＤ  branch_idNUMBER18Notnull
        l_expAccountOpenParams.setBranchId(1232);
        //4 部店コード  branch_codeVARCHAR23Notnull
        l_expAccountOpenParams.setBranchCode("624");
        //5 識別コード  acc_open_request_numberVARCHAR213Notnull
        l_expAccountOpenParams.setAccOpenRequestNumber("624");
        //8 既存口座フラグex_account_flagVARCHAR21Notnull
        l_expAccountOpenParams.setExAccountFlag(BooleanEnum.FALSE);
        //11 口座区分account_div VARCHAR21Notnull
        l_expAccountOpenParams.setAccountDiv("1");
        //12 入力区分order_div VARCHAR21Notnull
        l_expAccountOpenParams.setOrderDiv("1");
        //16 顧客姓（漢字）family_name VARCHAR240Notnull
        l_expAccountOpenParams.setFamilyName("1");
        //17 顧客名（漢字）given_name VARCHAR240Notnull
        l_expAccountOpenParams.setGivenName("1");
        //18 顧客姓（カナ）family_name_alt1 VARCHAR240Notnull
        l_expAccountOpenParams.setFamilyNameAlt1("1");
        //19 顧客名（カナ）given_name_alt1 VARCHAR240Notnull
        l_expAccountOpenParams.setGivenNameAlt1("1");
        //20 性別sex VARCHAR21Notnull
        l_expAccountOpenParams.setSex("1");

        //25 郵便番号zip_codeVARCHAR27Notnull
        l_expAccountOpenParams.setZipCode("1");
        //26 住所１address_line1VARCHAR234Notnull
        l_expAccountOpenParams.setAddressLine1("1");
        //29 住所１（カナ）address_line1_kana VARCHAR2 60 Notnull
        l_expAccountOpenParams.setAddressLine1Kana("1");
        //77 投資経験有無現物株式フラグexperience_flag_equityNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
        //78 信用取引フラグexperience_flag_marginNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
        //79 債券フラグexperience_flag_bondNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
        //80 転換社債フラグexperience_flag_wtNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
        //80 投資信託（株式）フラグexperience_flag_fund_skNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
        //81 投資信託（公社債）フラグexperience_flag_fund_bdNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
        //81 先物・オプションフラグexperience_flag_foNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
        //82 外国証券フラグexperience_flag_f_equityNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
        //83 その他フラグexperience_flag_etcNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
        //84 経験年数（自）experience_fromVARCHAR22NULL
        l_expAccountOpenParams.setExperienceFrom("1");
        //85 経験年数（至）experience_toVARCHAR22NULL
        l_expAccountOpenParams.setExperienceTo("1");
        //86 興味のあるお取引現物株式フラグinterest_flag_equityNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagEquity(BooleanEnum.FALSE);
        //87 株式ミニ投資フラグinterest_flag_ministockNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagMinistock(BooleanEnum.FALSE);
        //88 信用取引フラグinterest_flag_marginNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagMargin(BooleanEnum.FALSE);
        //89 債券フラグinterest_flag_bondNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagBond(BooleanEnum.FALSE);
        //90 投資信託フラグinterest_flag_fundNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagFund(BooleanEnum.FALSE);
        //91 先物・オプションフラグinterest_flag_foNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagFEquity(BooleanEnum.FALSE);
        //92 外国証券フラグinterest_flag_f_equityNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagFEquity(BooleanEnum.FALSE);
        //93 その他フラグinterest_flag_etcNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagEtc(BooleanEnum.FALSE);
        //105 検索用区分id_confirm_flagNUMBER1Notnull
        l_expAccountOpenParams.setIdConfirmFlag(BooleanEnum.FALSE);
        //110 内部者登録フラグinsider_flagNUMBER1Notnull

        l_expAccountOpenParams.setInsiderFlag(BooleanEnum.FALSE);
        //147 作成日時created_timestampDATENotnull
        l_expAccountOpenParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        //148 更新日時last_updated_timestampDATENotnull
        l_expAccountOpenParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));

        l_expAccountOpenParams.setExperienceDivEquity("1");
        l_expAccountOpenParams.setExperienceDivMargin("1");
        l_expAccountOpenParams.setExperienceDivBond("1");
        l_expAccountOpenParams.setExperienceDivWt("1");
        l_expAccountOpenParams.setExperienceDivFundSk("1");
        l_expAccountOpenParams.setExperienceDivFundBd("1");
        l_expAccountOpenParams.setExperienceDivFo("1");
        l_expAccountOpenParams.setExperienceDivFEquity("1");
        l_expAccountOpenParams.setExperienceDivEtc("1");

        return l_expAccountOpenParams;
    }

    public static VariousInformParams getVariousInformRow()
    {
        VariousInformParams variousInformParams = new VariousInformParams();
        variousInformParams.setBranchCode("000");
        variousInformParams.setInstitutionCode("123");
        variousInformParams.setInformDiv("1");
        variousInformParams.setRequestNumber("123");
        variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        return variousInformParams;
    }

    public static AccOpenVoucherStatusParams getAccopenVoucherStatus()
    {
        AccOpenVoucherStatusParams l_params = new AccOpenVoucherStatusParams();
        //1 証券会社コードinstitution_codeVARCHAR23NotNull（既存値）
        l_params.setInstitutionCode("123");
        //2 識別コードacc_open_request_numberVARCHAR213Notnull（既存値）
        l_params.setAccOpenRequestNumber("123");
        //3 データコードrequest_codeVARCHAR25NotNull（既存値）
        l_params.setRequestCode("123");
        //4 伝票通番serial_noVARCHAR23NotNull（既存値）
        l_params.setSerialNo("1");
        //5 伝票作成ステータスvoucher_statusVARCHAR21NotNullリクエストデータ.更新_伝票作成状況
        l_params.setVoucherStatus("1");
        //6 送信日時send_timestampDATENULL（既存値）
        l_params.setSendTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        //7 受信日時recv_timestampDATENULL現在日時
        l_params.setRecvTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        //8 エラーコードerror_codeVARCHAR24NULLリクエストデータ.更新_エラー理由コード
        l_params.setErrorCode("1");
        //9 更新者コードlast_updaterVARCHAR220NULL（既存値）
        l_params.setLastUpdater("1");
        //10 作成日時created_timestampDATENotNull（既存値）
        l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        //11 更新日時last_updated_timestampDATENotNull（既存値）
        l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        return l_params;

    }

}
@
