head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.39.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : ()åa¤ Ø\[VVXeæñ
 File Name        : (WEB3AdminAioListServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/08  Ôi(u) VKì¬
 */
package webbroker3.aio.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.aio.WEB3AdminAioListDownloadCSV;
import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListResponse;
import webbroker3.aio.message.WEB3AioAdminCashTransferListUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.TransferedFinInstitutionParams;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioListServiceImplTest extends TestBaseForMock {
    /**
     * O[eBeB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AdminAioListServiceImplTest.class);

    WEB3AdminAioListServiceImpl l_ListServiceImpl = null;

    public WEB3AdminAioListServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        l_ListServiceImpl = new WEB3AdminAioListServiceImpl();

        // nÔIô¬
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");
        TestBaseForMock.MOCK_MANAGER
                .setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                        "getSystemTimestamp", new Class[] {}, new Timestamp(
                                l_date.getTime()));
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * 
     * validate À³íIîµ TestCaseNO:1
     */
    public void testgetInputScreen001() {
        final String STR_METHOD_NAME = ".testgetInputScreen001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, false);

            WEB3AdminAioCashTransferListInputRequest l_request = new WEB3AdminAioCashTransferListInputRequest();
            WEB3AdminAioCashTransferListInputResponse l_response = l_ListServiceImpl
                    .getInputScreen(l_request);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);

            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * validate À³íIîµ TestCaseNO:2
     */
    public void testgetInputScreen002() {
        final String STR_METHOD_NAME = ".testgetInputScreen002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            WEB3AdminAioCashTransferListInputRequest l_request = new WEB3AdminAioCashTransferListInputRequest();
            WEB3AdminAioCashTransferListInputResponse l_response = l_ListServiceImpl
                    .getInputScreen(l_request);

            String l_strDate = WEB3DateUtility.formatDate(
                    l_response.deliveryDate, "yyyyMMdd");
            assertEquals("20070206", l_strDate);

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * QF1002(üà¶) Iîµ 0(üà)"ðÔp·é
     * 
     * TestCaseNO:3
     */
    public void testgetAioDevCase001() {
        final String STR_METHOD_NAME = ".testgetAioDevCase001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            String l_strParam = "1002";

            Class[] params = new Class[] { String.class };
            Object[] vars = new Object[] { l_strParam };
            Method method = l_ListServiceImpl.getClass().getDeclaredMethod(
                    "getAioDev", params);
            method.setAccessible(true);
            String l_strResult = (String) method
                    .invoke(l_ListServiceImpl, vars);

            assertEquals("0", l_strResult);

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * QF1QF1008(UÖ¶(æØà©çaèà) Iîµ 0(üà)"ðÔp·é
     * 
     * TestCaseNO:4
     */
    public void testgetAioDevCase002() {
        final String STR_METHOD_NAME = ".testgetAioDevCase002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            String l_strParam = "1008";

            Class[] params = new Class[] { String.class };
            Object[] vars = new Object[] { l_strParam };
            Method method = l_ListServiceImpl.getClass().getDeclaredMethod(
                    "getAioDev", params);
            method.setAccessible(true);
            String l_strResult = (String) method
                    .invoke(l_ListServiceImpl, vars);

            assertEquals("0", l_strResult);

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * QF1012(×ÖÛØàUÖ¶(×ÖÛØà©çaèà)) Iîµ 0(üà)"ðÔp·é
     * 
     * TestCaseNO:5
     */
    public void testgetAioDevCase003() {
        final String STR_METHOD_NAME = ".testgetAioDevCase003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            String l_strParam = "1012";

            Class[] params = new Class[] { String.class };
            Object[] vars = new Object[] { l_strParam };
            Method method = l_ListServiceImpl.getClass().getDeclaredMethod(
                    "getAioDev", params);
            method.setAccessible(true);
            String l_strResult = (String) method
                    .invoke(l_ListServiceImpl, vars);

            assertEquals("0", l_strResult);

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * QFQF1018(»Ì¼UÖ¶(X©çaèà) Iîµ 0(üà)"ðÔp·é
     * 
     * TestCaseNO:6
     */
    public void testgetAioDevCase004() {
        final String STR_METHOD_NAME = ".testgetAioDevCase004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            String l_strParam = "1018";

            Class[] params = new Class[] { String.class };
            Object[] vars = new Object[] { l_strParam };
            Method method = l_ListServiceImpl.getClass().getDeclaredMethod(
                    "getAioDev", params);
            method.setAccessible(true);
            String l_strResult = (String) method
                    .invoke(l_ListServiceImpl, vars);

            assertEquals("0", l_strResult);

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * QF1001 Iîµ 1(oà)"ðÔp·é
     * 
     * TestCaseNO:7
     */
    public void testgetAioDevCase005() {
        final String STR_METHOD_NAME = ".testgetAioDevCase005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            String l_strParam = "1001";

            Class[] params = new Class[] { String.class };
            Object[] vars = new Object[] { l_strParam };
            Method method = l_ListServiceImpl.getClass().getDeclaredMethod(
                    "getAioDev", params);
            method.setAccessible(true);
            String l_strResult = (String) method
                    .invoke(l_ListServiceImpl, vars);

            assertEquals("1", l_strResult);

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * QF1007 Iîµ 1(oà)"ðÔp·é
     * 
     * TestCaseNO:8
     */
    public void testgetAioDevCase006() {
        final String STR_METHOD_NAME = ".testgetAioDevCase006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            String l_strParam = "1007";

            Class[] params = new Class[] { String.class };
            Object[] vars = new Object[] { l_strParam };
            Method method = l_ListServiceImpl.getClass().getDeclaredMethod(
                    "getAioDev", params);
            method.setAccessible(true);
            String l_strResult = (String) method
                    .invoke(l_ListServiceImpl, vars);

            assertEquals("1", l_strResult);

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * QF1011 Iîµ 1(oà)"ðÔp·é
     * 
     * TestCaseNO:9
     */
    public void testgetAioDevCase007() {
        final String STR_METHOD_NAME = ".testgetAioDevCase007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            String l_strParam = "1011";

            Class[] params = new Class[] { String.class };
            Object[] vars = new Object[] { l_strParam };
            Method method = l_ListServiceImpl.getClass().getDeclaredMethod(
                    "getAioDev", params);
            method.setAccessible(true);
            String l_strResult = (String) method
                    .invoke(l_ListServiceImpl, vars);

            assertEquals("1", l_strResult);

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * QF1017 Iîµ 1(oà)"ðÔp·é
     * 
     * TestCaseNO:10
     */
    public void testgetAioDevCase008() {
        final String STR_METHOD_NAME = ".testgetAioDevCase008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            String l_strParam = "1017";

            Class[] params = new Class[] { String.class };
            Object[] vars = new Object[] { l_strParam };
            Method method = l_ListServiceImpl.getClass().getDeclaredMethod(
                    "getAioDev", params);
            method.setAccessible(true);
            String l_strResult = (String) method
                    .invoke(l_ListServiceImpl, vars);

            assertEquals("1", l_strResult);

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * QFãLÈOIîµ null"ðÔp·é
     * 
     * TestCaseNO:11
     */
    public void testgetAioDevCase009() {
        final String STR_METHOD_NAME = ".testgetAioDevCase009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            String l_strParam = "9999";

            Class[] params = new Class[] { String.class };
            Object[] vars = new Object[] { l_strParam };
            Method method = l_ListServiceImpl.getClass().getDeclaredMethod(
                    "getAioDev", params);
            method.setAccessible(true);
            String l_strResult = (String) method
                    .invoke(l_ListServiceImpl, vars);

            assertEquals(null, l_strResult);

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * QFBüIÒÉÉg×[null]Iîµ Q1Fnull Q2: 0 Q3: 0 null"ðÔp·é
     * ErrorCode:ErrorCode:BUSINESS_ERROR_01056 ErrorMessage:p[^ls³B
     * TestCaseNO:12
     */
    public void testgetIndicationDetailsCase001() {
        final String STR_METHOD_NAME = ".testgetIndicationDetailsCase001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            l_ListServiceImpl.getIndicationDetails(null, 0, 0);
        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * Q1F·x×0I[oà\â¹¾×Ìzñ] Q2: 0 Q3: 0
     * ErrorCode:ErrorCode:BUSINESS_ERROR_01056 ErrorMessage:p[^ls³B
     * TestCaseNO:13
     */
    public void testgetIndicationDetailsCase002() {
        final String STR_METHOD_NAME = ".testgetIndicationDetailsCase002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            WEB3AioAdminCashTransferListUnit[] l_units = new WEB3AioAdminCashTransferListUnit[0];

            l_ListServiceImpl.getIndicationDetails(l_units, 0, 0);
        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * Q1F·x×3I[oà\â¹¾×Ìzñ] Q2: 2 Q3: 1
     * ErrorCode:ErrorCode:BUSINESS_ERROR_01056 ErrorMessage:p[^ls³B
     * TestCaseNO:14
     */
    public void testgetIndicationDetailsCase003() {
        final String STR_METHOD_NAME = ".testgetIndicationDetailsCase003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            WEB3AioAdminCashTransferListUnit[] l_units = new WEB3AioAdminCashTransferListUnit[3];
            for (int i = 0; i < 3; i++) {
                l_units[i] = new WEB3AioAdminCashTransferListUnit();
            }

            l_ListServiceImpl.getIndicationDetails(l_units, 2, 1);
        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * 
     * Q1F·x×3I[oà\â¹¾×Ìzñ] Q2: 1 Q3: 3
     * ErrorCode:ErrorCode:BUSINESS_ERROR_01056 ErrorMessage:p[^ls³B
     * TestCaseNO:15
     */
    public void testgetIndicationDetailsCase004() {
        final String STR_METHOD_NAME = ".testgetIndicationDetailsCase004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            WEB3AioAdminCashTransferListUnit[] l_units = new WEB3AioAdminCashTransferListUnit[3];
            for (int i = 0; i < 3; i++) {
                l_units[i] = new WEB3AioAdminCashTransferListUnit();
            }

            l_ListServiceImpl.getIndicationDetails(l_units, 1, 3);
        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ³íIê Q1F·x×3I[oà\â¹¾×Ìzñ] Q2: 0 Q3: 0 ÔñIÉg·x×[1]; àe¥ÒÉÉgIæêÉB
     * TestCaseNO:16
     */
    public void testgetIndicationDetailsCase005() {
        final String STR_METHOD_NAME = ".testgetIndicationDetailsCase005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            WEB3AioAdminCashTransferListUnit[] l_units = new WEB3AioAdminCashTransferListUnit[3];
            for (int i = 0; i < 3; i++) {
                l_units[i] = new WEB3AioAdminCashTransferListUnit();
            }
            l_units[0].branchCode = "0001";
            l_units[1].branchCode = "0002";
            l_units[2].branchCode = "0003";

            WEB3AioAdminCashTransferListUnit[] l_result = l_ListServiceImpl
                    .getIndicationDetails(l_units, 0, 0);

            assertEquals(1, l_result.length);
            assertEquals("0001", l_result[0].branchCode);

        }

        catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ³íIê Q1F·x×3I[oà\â¹¾×Ìzñ] Q2: 2 Q3: 2 ÔñIÉg·x×[1]; àe¥ÒÉÉgIÅãêÉB
     * TestCaseNO:17
     */
    public void testgetIndicationDetailsCase006() {
        final String STR_METHOD_NAME = ".testgetIndicationDetailsCase006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            WEB3AioAdminCashTransferListUnit[] l_units = new WEB3AioAdminCashTransferListUnit[3];
            for (int i = 0; i < 3; i++) {
                l_units[i] = new WEB3AioAdminCashTransferListUnit();
            }
            l_units[0].branchCode = "0001";
            l_units[1].branchCode = "0002";
            l_units[2].branchCode = "0003";

            WEB3AioAdminCashTransferListUnit[] l_result = l_ListServiceImpl
                    .getIndicationDetails(l_units, 2, 2);

            assertEquals(1, l_result.length);
            assertEquals("0003", l_result[0].branchCode);

        }

        catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ³íIê Q1F·x×3I[oà\â¹¾×Ìzñ] Q2: 0 Q3: 2 ÔñIÉg·x×[3]; àe¥ÒÉÉgILÉB
     * TestCaseNO:18
     */
    public void testgetIndicationDetailsCase007() {
        final String STR_METHOD_NAME = ".testgetIndicationDetailsCase007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            WEB3AioAdminCashTransferListUnit[] l_units = new WEB3AioAdminCashTransferListUnit[3];
            for (int i = 0; i < 3; i++) {
                l_units[i] = new WEB3AioAdminCashTransferListUnit();
            }
            l_units[0].branchCode = "0001";
            l_units[1].branchCode = "0002";
            l_units[2].branchCode = "0003";

            WEB3AioAdminCashTransferListUnit[] l_result = l_ListServiceImpl
                    .getIndicationDetails(l_units, 0, 2);

            assertEquals(3, l_result.length);
            assertEquals("0001", l_result[0].branchCode);
            assertEquals("0002", l_result[1].branchCode);
            assertEquals("0003", l_result[2].branchCode);

        }

        catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ÒÉ×óIê Q1Fnull ErrorCode:ErrorCode:SYSTEM_ERROR_80017
     * ErrorMessage:p[^ls³B TestCaseNO:19
     */
    public void testgetTransferedFinInstitutionRecordCase001() {
        final String STR_METHOD_NAME = ".testgetTransferedFinInstitutionRecordCase001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            MainAccount l_mainAccount = null;
            l_ListServiceImpl.getTransferedFinInstitutionRecord(l_mainAccount);
        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ûõsÉIîµCCzSQL¶Icheck UæàZ@@Öe[u¦¶ÉF ØïÐR[h F14 CXR[h F130C ÚqR[h
     * F1413020 wèæªF"A" BüÒÉI[Úq]I¢«FØïÐR[h F14C XR[h F131CÚqR[h F1413020
     * ÔñIListI·x×0 TestCaseNO:20
     */
    public void testgetTransferedFinInstitutionRecordCase002() {
        final String STR_METHOD_NAME = ".testgetTransferedFinInstitutionRecordCase002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);

            TransferedFinInstitutionParams l_tranferFinIsPa = TestDBUtility
                    .getTransferedFinInstitutionRow();
            l_tranferFinIsPa.setInstitutionCode("14");
            l_tranferFinIsPa.setBranchCode("130");
            l_tranferFinIsPa.setAccountCode("1413020");
            l_tranferFinIsPa.setDesignateDiv("A");
            TestDBUtility.insertWithDel(l_tranferFinIsPa);

            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("131");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("131");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "131", "1413020");

            log.debug("***************MainAccount'institution_code is:"
                    + l_mainAccount.getInstitution().getInstitutionCode());
            log.debug("***************MainAccount'branch_code is:"
                    + l_mainAccount.getBranch().getBranchCode());
            log.debug("***************MainAccount'account_code is:"
                    + l_mainAccount.getAccountCode());

            List l_lisResult = l_ListServiceImpl
                    .getTransferedFinInstitutionRecord(l_mainAccount);

            assertEquals(0, l_lisResult.size());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ûõsÉIîµCCzSQL¶Icheck UæàZ@@Öe[u¦¶ÉF ØïÐR[h F14 CXR[h F130C ÚqR[h
     * F1413020 wèæªF"A" BüÒÉI[Úq]I¢«FØïÐR[h F14C XR[h F131CÚqR[h F1413021
     * ÔñIListI·x×0 TestCaseNO:21
     */
    public void testgetTransferedFinInstitutionRecordCase003() {
        final String STR_METHOD_NAME = ".testgetTransferedFinInstitutionRecordCase002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);

            // UæàZ@@Öe[u
            TransferedFinInstitutionParams l_tranferFinIsPa = TestDBUtility
                    .getTransferedFinInstitutionRow();
            l_tranferFinIsPa.setInstitutionCode("14");
            l_tranferFinIsPa.setBranchCode("130");
            l_tranferFinIsPa.setAccountCode("1413020");
            l_tranferFinIsPa.setDesignateDiv("A");
            TestDBUtility.insertWithDel(l_tranferFinIsPa);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413021");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413021");

            log.debug("***************MainAccount'institution_code is:"
                    + l_mainAccount.getInstitution().getInstitutionCode());
            log.debug("***************MainAccount'branch_code is:"
                    + l_mainAccount.getBranch().getBranchCode());
            log.debug("***************MainAccount'account_code is:"
                    + l_mainAccount.getAccountCode());

            List l_lisResult = l_ListServiceImpl
                    .getTransferedFinInstitutionRecord(l_mainAccount);

            assertEquals(0, l_lisResult.size());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ûõsÉIîµCCzSQL¶Icheck UæàZ@@Öe[u¦¶ÉF ØïÐR[h F14 CXR[h F130C ÚqR[h
     * F1413020 wèæªF"A" BüÒÉI[Úq]I¢«FØïÐR[h F14C XR[h F130CÚqR[h F1413021
     * ÔñIListI·x×0 TestCaseNO:22
     */
    public void testgetTransferedFinInstitutionRecordCase004() {
        final String STR_METHOD_NAME = ".testgetTransferedFinInstitutionRecordCase002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);

            // UæàZ@@Öe[u
            TransferedFinInstitutionParams l_tranferFinIsPa = TestDBUtility
                    .getTransferedFinInstitutionRow();
            l_tranferFinIsPa.setInstitutionCode("14");
            l_tranferFinIsPa.setBranchCode("130");
            l_tranferFinIsPa.setAccountCode("1413020");
            l_tranferFinIsPa.setDesignateDiv("A");
            TestDBUtility.insertWithDel(l_tranferFinIsPa);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413021");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413021");

            log.debug("***************MainAccount'institution_code is:"
                    + l_mainAccount.getInstitution().getInstitutionCode());
            log.debug("***************MainAccount'branch_code is:"
                    + l_mainAccount.getBranch().getBranchCode());

            List l_lisResult = l_ListServiceImpl
                    .getTransferedFinInstitutionRecord(l_mainAccount);

            assertEquals(0, l_lisResult.size());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ûõsÉIîµCCzSQL¶Icheck UæàZ@@Öe[uLÉF ØïÐR[h F14 CXR[h F130C ÚqR[h
     * F1413020 BüÒÉI[Úq]I¢«FØïÐR[h F14C XR[h F131CÚqR[h F1413021 ÔñIListI·x×0
     * TestCaseNO:23
     */
    public void testgetTransferedFinInstitutionRecordCase005() {
        final String STR_METHOD_NAME = ".testgetTransferedFinInstitutionRecordCase005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413021");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413021");

            log.debug("***************MainAccount'institution_code is:"
                    + l_mainAccount.getInstitution().getInstitutionCode());
            log.debug("***************MainAccount'branch_code is:"
                    + l_mainAccount.getBranch().getBranchCode());
            log.debug("***************MainAccount'account_code is:"
                    + l_mainAccount.getAccountCode());

            List l_lisResult = l_ListServiceImpl
                    .getTransferedFinInstitutionRecord(l_mainAccount);

            assertEquals(0, l_lisResult.size());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ûõsÉIîµCCzSQL¶Icheck UæàZ@@Öe[u¦¶ÉF ØïÐR[h F14 CXR[h F130C ÚqR[h
     * F1413020 wèæªF"A" BüÒÉI[Úq]I¢«FØïÐR[h F15C XR[h F130CÚqR[h F1413020
     * ÔñIListI·x×0 TestCaseNO:24
     */
    public void testgetTransferedFinInstitutionRecordCase006() {
        final String STR_METHOD_NAME = ".testgetTransferedFinInstitutionRecordCase006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);

            // UæàZ@@Öe[u
            TransferedFinInstitutionParams l_tranferFinIsPa = TestDBUtility
                    .getTransferedFinInstitutionRow();
            l_tranferFinIsPa.setInstitutionCode("14");
            l_tranferFinIsPa.setBranchCode("130");
            l_tranferFinIsPa.setAccountCode("1413020");
            l_tranferFinIsPa.setDesignateDiv("A");
            TestDBUtility.insertWithDel(l_tranferFinIsPa);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("15", "130", "1413020");

            log.debug("***************MainAccount'institution_code is:"
                    + l_mainAccount.getInstitution().getInstitutionCode());
            log.debug("***************MainAccount'branch_code is:"
                    + l_mainAccount.getBranch().getBranchCode());
            log.debug("***************MainAccount'account_code is:"
                    + l_mainAccount.getAccountCode());

            List l_lisResult = l_ListServiceImpl
                    .getTransferedFinInstitutionRecord(l_mainAccount);

            assertEquals(0, l_lisResult.size());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ûõÉIîµCCzSQL¶Icheck UæàZ@@Öe[u¦¶ÉF ØïÐR[h F14 CXR[h F130C ÚqR[h
     * F1413020 wèæªF"A" BüÒÉI[Úq]I¢«FØïÐR[h F14C XR[h F130CÚqR[h F1413020
     * ÔñIListI·x×1 TestCaseNO:25
     */
    public void testgetTransferedFinInstitutionRecordCase007() {
        final String STR_METHOD_NAME = ".testgetTransferedFinInstitutionRecordCase007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);

            // UæàZ@@Öe[u
            TransferedFinInstitutionParams l_tranferFinIsPa = TestDBUtility
                    .getTransferedFinInstitutionRow();
            l_tranferFinIsPa.setInstitutionCode("14");
            l_tranferFinIsPa.setBranchCode("130");
            l_tranferFinIsPa.setAccountCode("1413020");
            l_tranferFinIsPa.setDesignateDiv("A");
            TestDBUtility.insertWithDel(l_tranferFinIsPa);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            log.debug("***************MainAccount'institution_code is:"
                    + l_mainAccount.getInstitution().getInstitutionCode());
            log.debug("***************MainAccount'branch_code is:"
                    + l_mainAccount.getBranch().getBranchCode());
            log.debug("***************MainAccount'account_code is:"
                    + l_mainAccount.getAccountCode());

            List l_lisResult = l_ListServiceImpl
                    .getTransferedFinInstitutionRecord(l_mainAccount);

            assertEquals(1, l_lisResult.size());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ªBüIÒÉs¯CvZs¯ResponseÚ¥Û³mBiª¥Û³mj Response.SONARüàvàz=12
     * Response.o[`üàvàz=14 Response.lbgüàvàz=16
     * Response.UÖ(æØà©çaèà)vàz=18 Response.×ÖÛØàUÖ(×ÖÛØà©çaèà)vàz=20
     * Response.»Ì¼UÖ(X©çaèà)vàz=22 Response.oàvàz 24
     * Response.UÖ(aèà©çæØà)vàz 26 Response.×ÖÛØàUÖ(aèà©ç×ÖÛØà)vàz=28
     * qesponse.»Ì¼UÖ(aèà©çX)vàz = 30 TestCaseNO:26
     */
    public void testcalTotalAmountCase001() {
        final String STR_METHOD_NAME = ".testcalTotalAmountCase001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            WEB3AdminAioCashTransferListResponse l_response = new WEB3AdminAioCashTransferListResponse();

            AioOrderUnitParams[] l_orderUnitParams = new AioOrderUnitParams[20];
            for (int i = 0; i < 20; i++) {
                l_orderUnitParams[i] = new AioOrderUnitParams();
                l_orderUnitParams[i].setOrderType(OrderTypeEnum.CASH_IN);
                l_orderUnitParams[i].setQuantity(i + 1);
            }
            l_orderUnitParams[0].setOrderRootDiv("9");
            l_orderUnitParams[10].setOrderRootDiv("9");

            l_orderUnitParams[1].setOrderRootDiv("D");
            l_orderUnitParams[11].setOrderRootDiv("D");

            l_orderUnitParams[2].setOrderRootDiv("T");
            l_orderUnitParams[2].setComDebitNumber("123");
            l_orderUnitParams[12].setOrderRootDiv("T");
            l_orderUnitParams[12].setComDebitNumber("23");

            l_orderUnitParams[3]
                    .setOrderType(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
            l_orderUnitParams[13]
                    .setOrderType(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);

            l_orderUnitParams[4]
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_orderUnitParams[14]
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);

            l_orderUnitParams[5]
                    .setOrderType(OrderTypeEnum.FROM_OTHER_TRANSFER);
            l_orderUnitParams[15]
                    .setOrderType(OrderTypeEnum.FROM_OTHER_TRANSFER);

            l_orderUnitParams[6].setOrderType(OrderTypeEnum.CASH_OUT);
            l_orderUnitParams[16].setOrderType(OrderTypeEnum.CASH_OUT);

            l_orderUnitParams[7]
                    .setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_orderUnitParams[17]
                    .setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);

            l_orderUnitParams[8]
                    .setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_orderUnitParams[18]
                    .setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);

            l_orderUnitParams[9].setOrderType(OrderTypeEnum.TO_OTHER_TRANSFER);
            l_orderUnitParams[19].setOrderType(OrderTypeEnum.TO_OTHER_TRANSFER);

            Class[] params = new Class[] { AioOrderUnitParams.class,
                    WEB3AdminAioCashTransferListResponse.class };
            Method method = l_ListServiceImpl.getClass().getDeclaredMethod(
                    "calTotalAmount", params);
            method.setAccessible(true);
            for (int i = 0; i < 20; i++) {

                Object[] vars = new Object[] { l_orderUnitParams[i], l_response };

                l_response = (WEB3AdminAioCashTransferListResponse) method
                        .invoke(l_ListServiceImpl, vars);
            }

            assertEquals("12", l_response.sonarCashinTotal);
            assertEquals("14", l_response.virtualCashinTotal);
            assertEquals("16", l_response.netCashinTotal);
            assertEquals("18", l_response.transferTotalMarginToDeposit);
            assertEquals("20", l_response.fxTotalGuarantyToDeposit);
            assertEquals("22", l_response.otherTotalXToAccountBalance);
            assertEquals("24", l_response.cashoutTotal);
            assertEquals("26", l_response.transferTotalDepositToMargin);
            assertEquals("28", l_response.fxTotalDepositToGuaranty);
            assertEquals("30", l_response.otherTotalAccountBalanceToX);

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 1 G ¶íÊ != null;
     * ¶íÊ = "000(SÄ)" Xe[^ = null TestCaseNO:27
     */
    public void testQueryAndContainerCase001() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams2.setOrderRootDiv("9");
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams3.setOrderRootDiv("D");
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4th(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(330414808004L);
            l_aioOrderUnitParams4.setBranchId(33381l);
            l_aioOrderUnitParams4.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setOrderRootDiv("9");
            l_aioOrderUnitParams4.setDeliveryDate(l_date);
            l_aioOrderUnitParams4.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setComDebitNumber("34");
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // The 5th(no)
            AioOrderUnitParams l_aioOrderUnitParam5 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParam5.setSubAccountId(11226l);
            l_aioOrderUnitParam5.setOrderUnitId(330414808005L);
            l_aioOrderUnitParam5.setAccountId(333812512246l);
            l_aioOrderUnitParam5.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderRootDiv("9");
            l_aioOrderUnitParam5.setBranchId(33382l);
            l_aioOrderUnitParam5.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParam5.setTraderId(10005);
            l_aioOrderUnitParam5.setComDebitNumber("34");
            l_aioOrderUnitParam5.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParam5);

            // The 6th(no)
            AioOrderUnitParams l_aioOrderUnitParams6 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams6.setSubAccountId(11227l);
            l_aioOrderUnitParams6.setOrderUnitId(330414808006L);
            l_aioOrderUnitParams6.setAccountId(333812512246l);
            l_aioOrderUnitParams6.setDeliveryDate(l_date);
            l_aioOrderUnitParams6.setBranchId(33381l);
            l_aioOrderUnitParams6
                    .setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE);
            l_aioOrderUnitParams6.setTraderId(10006);
            l_aioOrderUnitParams6.setComDebitNumber("34");
            l_aioOrderUnitParams6.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams6);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l };
            String l_strOrderType = "000";

            String l_strCondition = l_ListServiceImpl.createQueryString(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);
            assertEquals(
                    " branch_id=? and account_id=? and delivery_date=? and ((order_type=? and order_root_div=?) or  (order_type=? and order_root_div=?) or  (order_type=? and com_debit_number is not null) or  order_type in(?,?,?,?,?,?,?))",
                    l_strCondition);

            Object[] l_strContainer = l_ListServiceImpl.createQueryContainer(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[1]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[2]);// ónú
            assertEquals(OrderTypeEnum.CASH_IN, l_strContainer[3]);// OrderTypeEnum.1002
            assertEquals("9", l_strContainer[4]);// WEB3OrderRootDivDef.9
            assertEquals(OrderTypeEnum.CASH_IN, l_strContainer[5]);// OrderTypeEnum.1002
            assertEquals("D", l_strContainer[6]);// WEB3OrderRootDivDef.D
            assertEquals(OrderTypeEnum.CASH_IN, l_strContainer[7]);// OrderTypeEnum.1002
            assertEquals(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
                    l_strContainer[8]);// OrderTypeEnum.1008
            assertEquals(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE,
                    l_strContainer[9]);// OrderTypeEnum.1012
            assertEquals(OrderTypeEnum.FROM_OTHER_TRANSFER, l_strContainer[10]);// OrderTypeEnum.1018
            assertEquals(OrderTypeEnum.CASH_OUT, l_strContainer[11]);// OrderTypeEnum.1001
            assertEquals(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
                    l_strContainer[12]);// OrderTypeEnum.1007
            assertEquals(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                    l_strContainer[13]);// OrderTypeEnum.1011
            assertEquals(OrderTypeEnum.TO_OTHER_TRANSFER, l_strContainer[14]);// OrderTypeEnum.1017

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(4, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10001, l_params.getTraderId());
                } else if (i == 1) {
                    assertEquals(10003, l_params.getTraderId());
                } else if (i == 2) {
                    assertEquals(10004, l_params.getTraderId());
                } else if (i == 3) {
                    assertEquals(10002, l_params.getTraderId());
                }

            }

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 1 G ¶íÊ != null;
     * ¶íÊ = "100(üà_SÄ)" Xe[^ = null TestCaseNO:28
     */
    public void testQueryAndContainerCase002() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams2.setOrderRootDiv("9");
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams3.setOrderRootDiv("D");
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4th(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(330414808004L);
            l_aioOrderUnitParams4.setBranchId(33381l);
            l_aioOrderUnitParams4.setAccountId(333812512246l);
            l_aioOrderUnitParams4.setDeliveryDate(l_date);
            l_aioOrderUnitParams4.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setComDebitNumber("34");
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // The 5th(no)
            AioOrderUnitParams l_aioOrderUnitParam5 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParam5.setSubAccountId(11226l);
            l_aioOrderUnitParam5.setOrderUnitId(330414808005L);
            l_aioOrderUnitParam5.setAccountId(333812512246l);
            l_aioOrderUnitParam5.setDeliveryDate(l_date);
            l_aioOrderUnitParam5.setBranchId(33382l);
            l_aioOrderUnitParam5.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParam5.setTraderId(10005);
            l_aioOrderUnitParam5.setComDebitNumber("34");
            l_aioOrderUnitParam5.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParam5);

            // The 6th(no)
            AioOrderUnitParams l_aioOrderUnitParams6 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams6.setSubAccountId(11227l);
            l_aioOrderUnitParams6.setOrderUnitId(330414808006L);
            l_aioOrderUnitParams6.setAccountId(333812512246l);
            l_aioOrderUnitParams6.setDeliveryDate(l_date);
            l_aioOrderUnitParams6.setBranchId(33381l);
            l_aioOrderUnitParams6
                    .setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE);
            l_aioOrderUnitParams6.setTraderId(10006);
            l_aioOrderUnitParams6.setComDebitNumber("34");
            l_aioOrderUnitParams6.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams6);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l };
            String l_strOrderType = "100";

            String l_strCondition = l_ListServiceImpl.createQueryString(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);
            assertEquals(
                    " branch_id=? and account_id=? and delivery_date=? and ((order_type=? and order_root_div=?) or"
                            + "  (order_type=? and order_root_div=?) or"
                            + "  (order_type=? and com_debit_number is not null) or"
                            + "  order_type in(?,?,?))", l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl.createQueryContainer(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[1]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[2]);// ónú
            assertEquals(OrderTypeEnum.CASH_IN, l_strContainer[3]);// OrderTypeEnum.1002
            assertEquals("9", l_strContainer[4]);// WEB3OrderRootDivDef.9
            assertEquals(OrderTypeEnum.CASH_IN, l_strContainer[5]);// OrderTypeEnum.1002
            assertEquals("D", l_strContainer[6]);// WEB3OrderRootDivDef.D
            assertEquals(OrderTypeEnum.CASH_IN, l_strContainer[7]);// OrderTypeEnum.1002
            assertEquals(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
                    l_strContainer[8]);// OrderTypeEnum.1008
            assertEquals(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE,
                    l_strContainer[9]);// OrderTypeEnum.1012
            assertEquals(OrderTypeEnum.FROM_OTHER_TRANSFER, l_strContainer[10]);// OrderTypeEnum.1018

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(4, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10001, l_params.getTraderId());
                } else if (i == 1) {
                    assertEquals(10003, l_params.getTraderId());
                } else if (i == 2) {
                    assertEquals(10004, l_params.getTraderId());
                } else if (i == 3) {
                    assertEquals(10002, l_params.getTraderId());
                }

            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 1 G ¶íÊ != null;
     * ¶íÊ = "101(SONARüà)" Xe[^ = null TestCaseNO:29
     */
    public void testQueryAndContainerCase003() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams2.setOrderRootDiv("9");
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams3.setOrderRootDiv("9");
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l };
            String l_strOrderType = "101";

            String l_strCondition = l_ListServiceImpl.createQueryString(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(
                    " branch_id=? and account_id=? and delivery_date=? and order_type=? and order_root_div=?",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl.createQueryContainer(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[1]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[2]);// ónú
            assertEquals(OrderTypeEnum.CASH_IN, l_strContainer[3]);// OrderTypeEnum.1002
            assertEquals("9", l_strContainer[4]);// WEB3OrderRootDivDef.9

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(2, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10003, l_params.getTraderId());
                } else if (i == 1) {
                    assertEquals(10002, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 1 G ¶íÊ != null;
     * ¶íÊ = "102(o[`üà)" Xe[^ = null TestCaseNO:30
     */
    public void testQueryAndContainerCase004() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams2.setOrderRootDiv("D");
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams3.setOrderRootDiv("D");
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l };
            String l_strOrderType = "102";

            String l_strCondition = l_ListServiceImpl.createQueryString(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(
                    " branch_id=? and account_id=? and delivery_date=? and order_type=? and order_root_div=?",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl.createQueryContainer(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[1]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[2]);// ónú
            assertEquals(OrderTypeEnum.CASH_IN, l_strContainer[3]);// OrderTypeEnum.1002
            assertEquals("D", l_strContainer[4]);// WEB3OrderRootDivDef.9

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(2, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10003, l_params.getTraderId());
                } else if (i == 1) {
                    assertEquals(10002, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 1 G ¶íÊ != null;
     * "103(lbgüà)" Xe[^ = null TestCaseNO:31
     */
    public void testQueryAndContainerCase005() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams2.setComDebitNumber("1");
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams3.setComDebitNumber("2");
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l };
            String l_strOrderType = "103";

            String l_strCondition = l_ListServiceImpl.createQueryString(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(
                    " branch_id=? and account_id=? and delivery_date=? and order_type=? and com_debit_number is not null",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl.createQueryContainer(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[1]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[2]);// ónú
            assertEquals(OrderTypeEnum.CASH_IN, l_strContainer[3]);// OrderTypeEnum.1002

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(2, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10003, l_params.getTraderId());
                } else if (i == 1) {
                    assertEquals(10002, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 1 G ¶íÊ != null;
     * ¶íÊ = "200(oà_SÄ)" Xe[^ = null TestCaseNO:32
     */
    public void testQueryAndContainerCase006() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.CASH_OUT);
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3
                    .setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_aioOrderUnitParams4.setBranchId(33381l);
            l_aioOrderUnitParams4.setAccountId(333812512246l);
            l_aioOrderUnitParams4.setDeliveryDate(l_date);
            l_aioOrderUnitParams4
                    .setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // The 5rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams5 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams5.setSubAccountId(11226l);
            l_aioOrderUnitParams5.setOrderUnitId(330414808005L);
            l_aioOrderUnitParams5.setBranchId(33381l);
            l_aioOrderUnitParams5.setAccountId(333812512246l);
            l_aioOrderUnitParams5.setDeliveryDate(l_date);
            l_aioOrderUnitParams5.setOrderType(OrderTypeEnum.TO_OTHER_TRANSFER);
            l_aioOrderUnitParams5.setTraderId(10005);
            l_aioOrderUnitParams5.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams5);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l };
            String l_strOrderType = "200";

            String l_strCondition = l_ListServiceImpl.createQueryString(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(
                    " branch_id=? and account_id=? and delivery_date=? and order_type in(?,?,?,?)",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl.createQueryContainer(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[1]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[2]);// ónú
            assertEquals(OrderTypeEnum.CASH_OUT, l_strContainer[3]);// OrderTypeEnum.1002

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(4, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10003, l_params.getTraderId());
                } else if (i == 1) {
                    assertEquals(10005, l_params.getTraderId());
                } else if (i == 2) {
                    assertEquals(10002, l_params.getTraderId());
                } else if (i == 3) {
                    assertEquals(10004, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 1 G ¶íÊ != null;
     * ¶íÊ = "201(oà)" Xe[^ = null TestCaseNO:33
     */
    public void testQueryAndContainerCase007() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.CASH_OUT);
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3.setOrderType(OrderTypeEnum.CASH_OUT);
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_aioOrderUnitParams4.setBranchId(33381l);
            l_aioOrderUnitParams4.setAccountId(333812512246l);
            l_aioOrderUnitParams4.setDeliveryDate(l_date);
            l_aioOrderUnitParams4
                    .setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // The 5rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams5 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams5.setSubAccountId(11226l);
            l_aioOrderUnitParams5.setOrderUnitId(330414808005L);
            l_aioOrderUnitParams5.setBranchId(33381l);
            l_aioOrderUnitParams5.setAccountId(333812512246l);
            l_aioOrderUnitParams5.setDeliveryDate(l_date);
            l_aioOrderUnitParams5.setOrderType(OrderTypeEnum.TO_OTHER_TRANSFER);
            l_aioOrderUnitParams5.setTraderId(10005);
            l_aioOrderUnitParams5.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams5);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l };
            String l_strOrderType = "201";

            String l_strCondition = l_ListServiceImpl.createQueryString(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(
                    " branch_id=? and account_id=? and delivery_date=? and order_type=?",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl.createQueryContainer(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[1]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[2]);// ónú
            assertEquals(OrderTypeEnum.CASH_OUT, l_strContainer[3]);// OrderTypeEnum.1002

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(2, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10003, l_params.getTraderId());
                } else if (i == 2) {
                    assertEquals(10002, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 1 G ¶íÊ != null;
     * ¶íÊ = "203(×ÖÛØàUÖ(aèà©ç×ÖÛØà))" Xe[^ = null TestCaseNO:34
     */
    public void testQueryAndContainerCase008() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2
                    .setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3
                    .setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_aioOrderUnitParams4.setBranchId(33381l);
            l_aioOrderUnitParams4.setAccountId(333812512246l);
            l_aioOrderUnitParams4.setDeliveryDate(l_date);
            l_aioOrderUnitParams4
                    .setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // The 5rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams5 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams5.setSubAccountId(11226l);
            l_aioOrderUnitParams5.setOrderUnitId(330414808005L);
            l_aioOrderUnitParams5.setBranchId(33381l);
            l_aioOrderUnitParams5.setAccountId(333812512246l);
            l_aioOrderUnitParams5.setDeliveryDate(l_date);
            l_aioOrderUnitParams5.setOrderType(OrderTypeEnum.TO_OTHER_TRANSFER);
            l_aioOrderUnitParams5.setTraderId(10005);
            l_aioOrderUnitParams5.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams5);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l };
            String l_strOrderType = "202";

            String l_strCondition = l_ListServiceImpl.createQueryString(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(
                    " branch_id=? and account_id=? and delivery_date=? and order_type=?",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl.createQueryContainer(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[1]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[2]);// ónú
            assertEquals(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
                    l_strContainer[3]);// OrderTypeEnum.1002

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(2, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10003, l_params.getTraderId());
                } else if (i == 2) {
                    assertEquals(10002, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 1 G ¶íÊ != null;
     * ¶íÊ = "202(UÖ(aèà©çæØà))" Xe[^ = null TestCaseNO:35
     */
    public void testQueryAndContainerCase009() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2
                    .setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3
                    .setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_aioOrderUnitParams4.setBranchId(33381l);
            l_aioOrderUnitParams4.setAccountId(333812512246l);
            l_aioOrderUnitParams4.setDeliveryDate(l_date);
            l_aioOrderUnitParams4.setOrderType(OrderTypeEnum.TO_OTHER_TRANSFER);
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // The 5rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams5 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams5.setSubAccountId(11226l);
            l_aioOrderUnitParams5.setOrderUnitId(330414808005L);
            l_aioOrderUnitParams5.setBranchId(33381l);
            l_aioOrderUnitParams5.setAccountId(333812512246l);
            l_aioOrderUnitParams5.setDeliveryDate(l_date);
            l_aioOrderUnitParams5.setOrderType(OrderTypeEnum.TO_OTHER_TRANSFER);
            l_aioOrderUnitParams5.setTraderId(10005);
            l_aioOrderUnitParams5.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams5);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l };
            String l_strOrderType = "203";

            String l_strCondition = l_ListServiceImpl.createQueryString(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(
                    " branch_id=? and account_id=? and delivery_date=? and order_type=?",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl.createQueryContainer(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[1]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[2]);// ónú
            assertEquals(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                    l_strContainer[3]);// OrderTypeEnum.1002

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(2, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10003, l_params.getTraderId());
                } else if (i == 2) {
                    assertEquals(10002, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 1 G ¶íÊ != null;
     * ¶íÊ = "204(»Ì¼UÖ(aèà©çX))" Xe[^ = null TestCaseNO:36
     */
    public void testQueryAndContainerCase010() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.TO_OTHER_TRANSFER);
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3.setOrderType(OrderTypeEnum.TO_OTHER_TRANSFER);
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_aioOrderUnitParams4.setBranchId(33381l);
            l_aioOrderUnitParams4.setAccountId(333812512246l);
            l_aioOrderUnitParams4.setDeliveryDate(l_date);
            l_aioOrderUnitParams4.setOrderType(OrderTypeEnum.ASSET_OUT);
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // The 5rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams5 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams5.setSubAccountId(11226l);
            l_aioOrderUnitParams5.setOrderUnitId(330414808005L);
            l_aioOrderUnitParams5.setBranchId(33381l);
            l_aioOrderUnitParams5.setAccountId(333812512246l);
            l_aioOrderUnitParams5.setDeliveryDate(l_date);
            l_aioOrderUnitParams5.setOrderType(OrderTypeEnum.ASSET_OUT);
            l_aioOrderUnitParams5.setTraderId(10005);
            l_aioOrderUnitParams5.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams5);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l };
            String l_strOrderType = "204";

            String l_strCondition = l_ListServiceImpl.createQueryString(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(
                    " branch_id=? and account_id=? and delivery_date=? and order_type=?",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl.createQueryContainer(
                    l_lngBranchs, l_mainAccount, l_date, l_strOrderType, null);

            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[1]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[2]);// ónú
            assertEquals(OrderTypeEnum.TO_OTHER_TRANSFER, l_strContainer[3]);// OrderTypeEnum.1002

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(2, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10003, l_params.getTraderId());
                } else if (i == 2) {
                    assertEquals(10002, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 3 G ¶íÊ = null;
     * Xe[^ != null Xe[^X = "0(SÄ)" TestCaseNO:37
     */
    public void testQueryAndContainerCase011() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setBranchCode("120");
            l_branchParams1.setBranchId(33382L);
            TestDBUtility.insertWithDel(l_branchParams1);

            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setBranchCode("100");
            l_branchParams2.setBranchId(33383L);
            TestDBUtility.insertWithDel(l_branchParams2);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams2.setCancelType("0");
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33382l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderStatus(OrderStatusEnum.ORDERING);
            l_aioOrderUnitParams2.setCancelType("0");
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_aioOrderUnitParams4.setBranchId(33383l);
            l_aioOrderUnitParams4.setAccountId(333812512246l);
            l_aioOrderUnitParams4.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams2.setCancelType("0");
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // The 5rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams5 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams5.setSubAccountId(11226l);
            l_aioOrderUnitParams5.setOrderUnitId(330414808005L);
            l_aioOrderUnitParams5.setBranchId(33381l);
            l_aioOrderUnitParams5.setAccountId(333812512246l);
            l_aioOrderUnitParams5.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams5.setTraderId(10005);
            l_aioOrderUnitParams5.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams5);

            // The 6rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams6 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams6.setSubAccountId(11226l);
            l_aioOrderUnitParams6.setOrderUnitId(330414808005L);
            l_aioOrderUnitParams6.setBranchId(33381l);
            l_aioOrderUnitParams6.setAccountId(333812512246l);
            l_aioOrderUnitParams6.setDeliveryDate(l_date);
            l_aioOrderUnitParams6.setTraderId(10005);
            l_aioOrderUnitParams6.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams6);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l, 33382L, 33383L };
            String l_strOrderStatus = "0";

            String l_strCondition = l_ListServiceImpl
                    .createQueryString(l_lngBranchs, l_mainAccount, l_date,
                            null, l_strOrderStatus);
            assertEquals(
                    " branch_id in ( ?, ? , ? ) and account_id=? and delivery_date=? and ((order_status in(?,?,?) and cancel_type=?) or order_status=?)",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl
                    .createQueryContainer(l_lngBranchs, l_mainAccount, l_date,
                            null, l_strOrderStatus);
            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_lngBranchs[1], ((Long) l_strContainer[1])
                    .longValue());// XID
            assertEquals(l_lngBranchs[2], ((Long) l_strContainer[2])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[3]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[4]);// ónú
            assertEquals(OrderStatusEnum.ACCEPTED, l_strContainer[5]);// OrderStatusEnum.1:ótÏ
            assertEquals(OrderStatusEnum.ORDERING, l_strContainer[6]);// OrderStatusEnum.2:­
            assertEquals(OrderStatusEnum.ORDERED, l_strContainer[7]);// OrderStatusEnum.3:­ÏiVK¶)
            assertEquals(WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                    l_strContainer[8]);// OrderStatusEnum.0:úl
            assertEquals(OrderStatusEnum.NOT_ORDERED, l_strContainer[9]);// OrderStatusEnum.OrderStatusEnum.6:­¸siVK¶j

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(4, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10005, l_params.getTraderId());
                }
                if (i == 1) {
                    assertEquals(10002, l_params.getTraderId());
                } else if (i == 2) {
                    assertEquals(10003, l_params.getTraderId());
                } else if (i == 3) {
                    assertEquals(10004, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 3 G ¶íÊ = null;
     * Xe[^ != null Xe[^X = "1(®¹)" TestCaseNO:38
     */
    public void testQueryAndContainerCase012() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setBranchCode("120");
            l_branchParams1.setBranchId(33382L);
            TestDBUtility.insertWithDel(l_branchParams1);

            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setBranchCode("100");
            l_branchParams2.setBranchId(33383L);
            TestDBUtility.insertWithDel(l_branchParams2);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams2.setCancelType("0");
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33382l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams3.setCancelType("0");
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_aioOrderUnitParams4.setBranchId(33383l);
            l_aioOrderUnitParams4.setAccountId(333812512246l);
            l_aioOrderUnitParams4.setDeliveryDate(l_date);
            l_aioOrderUnitParams4.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams4.setCancelType("0");
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l, 33382L, 33383L };
            String l_strOrderStatus = "1";

            String l_strCondition = l_ListServiceImpl
                    .createQueryString(l_lngBranchs, l_mainAccount, l_date,
                            null, l_strOrderStatus);
            assertEquals(
                    " branch_id in ( ?, ? , ? ) and account_id=? and delivery_date=? and order_status=? and cancel_type=?",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl
                    .createQueryContainer(l_lngBranchs, l_mainAccount, l_date,
                            null, l_strOrderStatus);
            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_lngBranchs[1], ((Long) l_strContainer[1])
                    .longValue());// XID
            assertEquals(l_lngBranchs[2], ((Long) l_strContainer[2])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[3]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[4]);// ónú
            assertEquals(OrderStatusEnum.ORDERED, l_strContainer[5]);// OrderStatusEnum.3:­ÏiVK¶)
            assertEquals(WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                    l_strContainer[6]);// OrderStatusEnum.3:­ÏiVK¶)

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(2, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10003, l_params.getTraderId());
                }
                if (i == 1) {
                    assertEquals(10004, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 3 G ¶íÊ = null;
     * Xe[^ != null Xe[^X = "2(¢)" TestCaseNO:39
     */
    public void testQueryAndContainerCase013() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setBranchCode("120");
            l_branchParams1.setBranchId(33382L);
            TestDBUtility.insertWithDel(l_branchParams1);

            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setBranchCode("100");
            l_branchParams2.setBranchId(33383L);
            TestDBUtility.insertWithDel(l_branchParams2);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams2.setCancelType("0");
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33382l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3.setOrderStatus(OrderStatusEnum.ORDERING);
            l_aioOrderUnitParams3.setCancelType("0");
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_aioOrderUnitParams4.setBranchId(33383l);
            l_aioOrderUnitParams4.setAccountId(333812512246l);
            l_aioOrderUnitParams4.setDeliveryDate(l_date);
            l_aioOrderUnitParams4.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams4.setCancelType("0");
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l, 33382L, 33383L };
            String l_strOrderStatus = "2";

            String l_strCondition = l_ListServiceImpl
                    .createQueryString(l_lngBranchs, l_mainAccount, l_date,
                            null, l_strOrderStatus);
            assertEquals(
                    " branch_id in ( ?, ? , ? ) and account_id=? and delivery_date=? and order_status in(?,?) and cancel_type=?",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl
                    .createQueryContainer(l_lngBranchs, l_mainAccount, l_date,
                            null, l_strOrderStatus);
            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_lngBranchs[1], ((Long) l_strContainer[1])
                    .longValue());// XID
            assertEquals(l_lngBranchs[2], ((Long) l_strContainer[2])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[3]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[4]);// ónú
            assertEquals(OrderStatusEnum.ACCEPTED, l_strContainer[5]);
            assertEquals(OrderStatusEnum.ORDERING, l_strContainer[6]);
            assertEquals(WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                    l_strContainer[7]);

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(2, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10002, l_params.getTraderId());
                }
                if (i == 1) {
                    assertEquals(10003, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * øI?Ú¥Û³m ÛSQL¶\Û³m·s ÚqIuWFNg != null G XIDÌvf = 3 G ¶íÊ = null;
     * Xe[^ != null Xe[^X = "9(G[)" TestCaseNO:40
     */
    public void testQueryAndContainerCase014() {
        final String STR_METHOD_NAME = ".testQueryAndContainerCase014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("14");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");

            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("14");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setBranchCode("120");
            l_branchParams1.setBranchId(33382L);
            TestDBUtility.insertWithDel(l_branchParams1);

            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setBranchCode("100");
            l_branchParams2.setBranchId(33383L);
            TestDBUtility.insertWithDel(l_branchParams2);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246l);
            l_aioOrderUnitParams2.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams2.setDeliveryDate(l_date);
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33382l);
            l_aioOrderUnitParams3.setAccountId(333812512246l);
            l_aioOrderUnitParams3.setDeliveryDate(l_date);
            l_aioOrderUnitParams3.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // AJEg}l[Wæ¾·é
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp
                    .getAccountManager();

            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager
                    .getMainAccount("14", "130", "1413020");

            long[] l_lngBranchs = { 33381l, 33382L, 33383L };
            String l_strOrderStatus = "9";

            String l_strCondition = l_ListServiceImpl
                    .createQueryString(l_lngBranchs, l_mainAccount, l_date,
                            null, l_strOrderStatus);
            assertEquals(
                    " branch_id in ( ?, ? , ? ) and account_id=? and delivery_date=? and order_status=?",
                    l_strCondition);
            Object[] l_strContainer = l_ListServiceImpl
                    .createQueryContainer(l_lngBranchs, l_mainAccount, l_date,
                            null, l_strOrderStatus);
            assertEquals(l_lngBranchs[0], ((Long) l_strContainer[0])
                    .longValue());// XID
            assertEquals(l_lngBranchs[1], ((Long) l_strContainer[1])
                    .longValue());// XID
            assertEquals(l_lngBranchs[2], ((Long) l_strContainer[2])
                    .longValue());// XID
            assertEquals(l_mainAccount.getAccountId(),
                    ((Long) l_strContainer[3]).longValue());// Úq.getAccountId()
            assertEquals(l_date, l_strContainer[4]);// ónú
            assertEquals(OrderStatusEnum.NOT_ORDERED, l_strContainer[5]);

            String l_strSort = "branch_id asc , received_date_time desc";
            List l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, l_strCondition, l_strSort, null,
                    l_strContainer);
            assertEquals(2, l_lisRows.size());
            for (int i = 0; i < l_lisRows.size(); i++) {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows
                        .get(i);
                if (i == 0) {
                    assertEquals(10002, l_params.getTraderId());
                }
                if (i == 1) {
                    assertEquals(10003, l_params.getTraderId());
                }
            }
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ÒÉFª [¶PÊParams.XID] ÝX\(BranchRow)ûõs TestCaseNO:41
     */
    public void testCreateAioListDetailsCase001() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            l_ListServiceImpl.createAioListDetails(l_aioOrderUnitParams);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * ÒÉFª [¶PÊParams.XID] ÝX\(BranchRow)ûõ; ÒÉFª [¶PÊParams.ûÀID]
     * ÝÚq\(MainAccountRow)ûõsIîµ TestCaseNO:42
     */
    public void testCreateAioListDetailsCase002() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            l_ListServiceImpl.createAioListDetails(l_aioOrderUnitParams);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * PÊParams.¶íÊ == "1002" PÊParams.oHæª = "9h PÊParams.¶?Ô = "3h
     * PÊParams.æÁæª = "0h PÊParamsOrderType = OrderTypeEnum.CASH_IN
     * TestCaseNO:43
     */
    public void testCreateAioListDetailsCase003() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("9");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 101G
            // ónú=PÊParams.ónúG
            // XR[h = PÊParams.XR[h G
            // ÚqR[h = PÊParams.ÚqR[h G
            // Úq¼ = PÊParams.Úq¼ G
            // ¶ú = PÊParams.¶ú G
            // Xe[^X = 1
            // üààz = PÊParams¶ÉÊ
            // oààz = null
            // üÍoH = PÊParams.üÍoH
            // üÍÒ = PÊParams.µÒR[h
            // /âsR[h = null
            // xXR[h = null
            // ûÀíÊ = null
            // ûÀÔ = null
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);
            assertEquals(l_unit.orderType, "101");
            assertEquals(WEB3DateUtility.formatDate(l_unit.deliveryDate,
                    "yyyyMMdd"), "20071212");
            assertEquals("130", l_unit.branchCode);
            assertEquals(l_mainAccountParams.getAccountCode().substring(0, 6),
                    l_unit.accountCode);
            assertEquals("à¡@@lY", l_unit.accountName);
            assertEquals("19830206", WEB3DateUtility.formatDate(
                    l_unit.orderDate, "yyyyMMdd"));
            assertEquals("1", l_unit.cashinoutStatus);
            assertEquals("1200", l_unit.cashinAmt);
            assertEquals(null, l_unit.cashoutAmt);
            assertEquals("9", l_unit.orderRoutDiv);
            assertEquals("11124", l_unit.operatorCode);
            assertEquals(null, l_unit.financialBranchCode);
            assertEquals(null, l_unit.financialInstitutionCode);
            assertEquals(null, l_unit.accountTypeCode);
            assertEquals(null, l_unit.financialAccountCode);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * PÊParams.¶íÊ == "1002" PÊParams.oHæª = "Dh PÊParams.¶?Ô = "1h
     * PÊParams.æÁæª = "0h PÊParamsOrderType = OrderTypeEnum.CASH_OUT
     * TestCaseNO:44
     */
    public void testCreateAioListDetailsCase004() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 102G
            // ónú=PÊParams.ónúG
            // XR[h = PÊParams.XR[h G
            // ÚqR[h = PÊParams.ÚqR[h G
            // Úq¼ = PÊParams.Úq¼ G
            // ¶ú = PÊParams.¶ú G
            // Xe[^X = 2
            // üààz = PÊParams¶ÉÊ
            // oààz = null
            // üÍoH = PÊParams.üÍoH
            // üÍÒ = null
            // âsR[h = PÊParams.UÖLq.SubString(0,4)
            // xXR[h = PÊParams.UÖLq.SubString(4,7)
            // ûÀíÊ = PÊParams.UÖLq.SubString(7,8)
            // ûÀÔ =PÊParams.UÖLq.SubString(8,15)
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);
            assertEquals(l_unit.orderType, "102");
            assertEquals(WEB3DateUtility.formatDate(l_unit.deliveryDate,
                    "yyyyMMdd"), "20071212");
            assertEquals("130", l_unit.branchCode);
            assertEquals(l_mainAccountParams.getAccountCode().substring(0, 6),
                    l_unit.accountCode);
            assertEquals("à¡@@lY", l_unit.accountName);
            assertEquals("19830206", WEB3DateUtility.formatDate(
                    l_unit.orderDate, "yyyyMMdd"));
            assertEquals("2", l_unit.cashinoutStatus);
            assertEquals("1200", l_unit.cashinAmt);
            assertEquals(null, l_unit.cashoutAmt);
            assertEquals("D", l_unit.orderRoutDiv);
            assertEquals(null, l_unit.operatorCode);
            assertEquals("567", l_unit.financialBranchCode);
            assertEquals("1234", l_unit.financialInstitutionCode);
            assertEquals("8", l_unit.accountTypeCode);
            assertEquals("9abcdef", l_unit.financialAccountCode);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * PÊParams.¶íÊ == "1002" PÊParams.oHæª = "1h PÊParams.¶?Ô = "2h
     * PÊParams.æÁæª = "0h TestCaseNO:45
     */
    public void testCreateAioListDetailsCase005() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setComDebitNumber("88");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("1");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 103G
            // ónú=PÊParams.ónúG
            // XR[h = PÊParams.XR[h G
            // ÚqR[h = PÊParams.ÚqR[h G
            // Úq¼ = PÊParams.Úq¼ G
            // ¶ú = PÊParams.¶ú G
            // Xe[^X = 2
            // üààz = 1200
            // oààz = null
            // üÍoH = PÊParams.üÍoH
            // üÍÒ = æøÒhc
            // /âsR[h = null
            // xXR[h = null
            // ûÀíÊ = null
            // ûÀÔ = null
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);
            assertEquals(l_unit.orderType, "103");
            assertEquals(WEB3DateUtility.formatDate(l_unit.deliveryDate,
                    "yyyyMMdd"), "20071212");
            assertEquals("130", l_unit.branchCode);
            assertEquals(l_mainAccountParams.getAccountCode().substring(0, 6),
                    l_unit.accountCode);
            assertEquals("à¡@@lY", l_unit.accountName);
            assertEquals("19830206", WEB3DateUtility.formatDate(
                    l_unit.orderDate, "yyyyMMdd"));
            assertEquals("2", l_unit.cashinoutStatus);
            assertEquals("1200", l_unit.cashinAmt);
            assertEquals(null, l_unit.cashoutAmt);
            assertEquals("1", l_unit.orderRoutDiv);
            assertEquals("10001", l_unit.operatorCode);
            assertEquals(null, l_unit.financialBranchCode);
            assertEquals(null, l_unit.financialInstitutionCode);
            assertEquals(null, l_unit.accountTypeCode);
            assertEquals(null, l_unit.financialAccountCode);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * PÊParams.¶íÊ == "1008" PÊParams.oHæª = "Dh PÊParams.¶?Ô = "6h
     * PÊParams.æÁæª = "0h TestCaseNO:46
     */
    public void testCreateAioListDetailsCase006() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setComDebitNumber("88");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 104G
            // ónú=PÊParams.ónúG
            // XR[h = PÊParams.XR[h G
            // ÚqR[h = PÊParams.ÚqR[h G
            // Úq¼ = PÊParams.Úq¼ G
            // ¶ú = PÊParams.¶ú G
            // Xe[^X = 9
            // üààz = 1200
            // oààz = null
            // üÍoH = PÊParams.üÍoH
            // üÍÒ = æøÒhc
            // /âsR[h = null
            // xXR[h = null
            // ûÀíÊ = null
            // ûÀÔ = null
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);
            assertEquals(l_unit.orderType, "104");
            assertEquals(WEB3DateUtility.formatDate(l_unit.deliveryDate,
                    "yyyyMMdd"), "20071212");
            assertEquals("130", l_unit.branchCode);
            assertEquals(l_mainAccountParams.getAccountCode().substring(0, 6),
                    l_unit.accountCode);
            assertEquals("à¡@@lY", l_unit.accountName);
            assertEquals("19830206", WEB3DateUtility.formatDate(
                    l_unit.orderDate, "yyyyMMdd"));
            assertEquals("9", l_unit.cashinoutStatus);
            assertEquals("1200", l_unit.cashinAmt);
            assertEquals(null, l_unit.cashoutAmt);
            assertEquals("D", l_unit.orderRoutDiv);
            assertEquals(null, l_unit.operatorCode);
            assertEquals(null, l_unit.financialBranchCode);
            assertEquals(null, l_unit.financialInstitutionCode);
            assertEquals(null, l_unit.accountTypeCode);
            assertEquals(null, l_unit.financialAccountCode);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * PÊParams.¶íÊ == "1012" PÊParams.oHæª = "Dh PÊParams.¶?Ô = "6h
     * PÊParams.æÁæª = "0h TestCaseNO:47
     */
    public void testCreateAioListDetailsCase007() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setComDebitNumber("88");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 105G
            // ónú=PÊParams.ónúG
            // XR[h = PÊParams.XR[h G
            // ÚqR[h = PÊParams.ÚqR[h G
            // Úq¼ = PÊParams.Úq¼ G
            // ¶ú = PÊParams.¶ú G
            // Xe[^X = 9
            // üààz = 1200
            // oààz = null
            // üÍoH = PÊParams.üÍoH
            // üÍÒ = æøÒhc
            // /âsR[h = null
            // xXR[h = null
            // ûÀíÊ = null
            // ûÀÔ = null
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);
            assertEquals("105", l_unit.orderType);
            assertEquals(WEB3DateUtility.formatDate(l_unit.deliveryDate,
                    "yyyyMMdd"), "20071212");
            assertEquals("130", l_unit.branchCode);
            assertEquals(l_mainAccountParams.getAccountCode().substring(0, 6),
                    l_unit.accountCode);
            assertEquals("à¡@@lY", l_unit.accountName);
            assertEquals("19830206", WEB3DateUtility.formatDate(
                    l_unit.orderDate, "yyyyMMdd"));
            assertEquals("9", l_unit.cashinoutStatus);
            assertEquals("1200", l_unit.cashinAmt);
            assertEquals(null, l_unit.cashoutAmt);
            assertEquals("D", l_unit.orderRoutDiv);
            assertEquals(null, l_unit.operatorCode);
            assertEquals(null, l_unit.financialBranchCode);
            assertEquals(null, l_unit.financialInstitutionCode);
            assertEquals(null, l_unit.accountTypeCode);
            assertEquals(null, l_unit.financialAccountCode);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * PÊParams.¶íÊ == "1018" PÊParams.oHæª = "Dh PÊParams.¶?Ô = "6h
     * PÊParams.æÁæª = "0h TestCaseNO:48
     */
    public void testCreateAioListDetailsCase008() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase008";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setComDebitNumber("88");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.FROM_OTHER_TRANSFER);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 105G
            // ónú=PÊParams.ónúG
            // XR[h = PÊParams.XR[h G
            // ÚqR[h = PÊParams.ÚqR[h G
            // Úq¼ = PÊParams.Úq¼ G
            // ¶ú = PÊParams.¶ú G
            // Xe[^X = 9
            // üààz = 1200
            // oààz = null
            // üÍoH = PÊParams.üÍoH
            // üÍÒ = æøÒhc
            // /âsR[h = null
            // xXR[h = null
            // ûÀíÊ = null
            // ûÀÔ = null
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);
            assertEquals("106", l_unit.orderType);
            assertEquals(WEB3DateUtility.formatDate(l_unit.deliveryDate,
                    "yyyyMMdd"), "20071212");
            assertEquals("130", l_unit.branchCode);
            assertEquals(l_mainAccountParams.getAccountCode().substring(0, 6),
                    l_unit.accountCode);
            assertEquals("à¡@@lY", l_unit.accountName);
            assertEquals("19830206", WEB3DateUtility.formatDate(
                    l_unit.orderDate, "yyyyMMdd"));
            assertEquals("9", l_unit.cashinoutStatus);
            assertEquals("1200", l_unit.cashinAmt);
            assertEquals(null, l_unit.cashoutAmt);
            assertEquals("D", l_unit.orderRoutDiv);
            assertEquals(null, l_unit.operatorCode);
            assertEquals(null, l_unit.financialBranchCode);
            assertEquals(null, l_unit.financialInstitutionCode);
            assertEquals(null, l_unit.accountTypeCode);
            assertEquals(null, l_unit.financialAccountCode);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * PÊParams.¶íÊ == "1001" PÊParams.oHæª = "Dh PÊParams.¶?Ô = "6h
     * PÊParams.æÁæª = "0h TestCaseNO:49
     */
    public void testCreateAioListDetailsCase009() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase009";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setComDebitNumber("88");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_OUT);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 201G
            // ónú=PÊParams.ónúG
            // XR[h = PÊParams.XR[h G
            // ÚqR[h = PÊParams.ÚqR[h G
            // Úq¼ = PÊParams.Úq¼ G
            // ¶ú = PÊParams.¶ú G
            // Xe[^X = 9
            // üààz = null
            // oààz = 1200
            // üÍoH = PÊParams.üÍoH
            // üÍÒ = æøÒhc
            // /âsR[h = null
            // xXR[h = null
            // ûÀíÊ = null
            // ûÀÔ = null
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);
            assertEquals("201", l_unit.orderType);
            assertEquals(WEB3DateUtility.formatDate(l_unit.deliveryDate,
                    "yyyyMMdd"), "20071212");
            assertEquals("130", l_unit.branchCode);
            assertEquals(l_mainAccountParams.getAccountCode().substring(0, 6),
                    l_unit.accountCode);
            assertEquals("à¡@@lY", l_unit.accountName);
            assertEquals("19830206", WEB3DateUtility.formatDate(
                    l_unit.orderDate, "yyyyMMdd"));
            assertEquals("9", l_unit.cashinoutStatus);
            assertEquals(null, l_unit.cashinAmt);
            assertEquals("1200", l_unit.cashoutAmt);
            assertEquals("D", l_unit.orderRoutDiv);
            assertEquals(null, l_unit.operatorCode);
            assertEquals(null, l_unit.financialBranchCode);
            assertEquals(null, l_unit.financialInstitutionCode);
            assertEquals(null, l_unit.accountTypeCode);
            assertEquals(null, l_unit.financialAccountCode);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * PÊParams.¶íÊ == "1001" {û@@²p[ getUæàZ@@ÖR[h ] Ôñ·x×1IList PÊParams.oHæª =
     * "Dh PÊParams.¶?Ô = "6h PÊParams.æÁæª = "0h TestCaseNO:50
     */
    public void testCreateAioListDetailsCase010() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase010";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            WEB3AdminAioListServiceImplForMockche l_implForMock = new WEB3AdminAioListServiceImplForMockche();

            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setComDebitNumber("88");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_OUT);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 201G
            // ónú=PÊParams.ónúG
            // XR[h = PÊParams.XR[h G
            // ÚqR[h = PÊParams.ÚqR[h G
            // Úq¼ = PÊParams.Úq¼ G
            // ¶ú = PÊParams.¶ú G
            // Xe[^X = 9
            // üààz = null
            // oààz = 1200
            // üÍoH = PÊParams.üÍoH
            // üÍÒ = æøÒhc
            // /âsR[h = null
            // xXR[h = null
            // ûÀíÊ = null
            // ûÀÔ = null
            WEB3AioAdminCashTransferListUnit l_unit = l_implForMock
                    .createAioListDetails(l_aioOrderUnitParams);
            assertEquals("201", l_unit.orderType);
            assertEquals(WEB3DateUtility.formatDate(l_unit.deliveryDate,
                    "yyyyMMdd"), "20071212");
            assertEquals("130", l_unit.branchCode);
            assertEquals(l_mainAccountParams.getAccountCode().substring(0, 6),
                    l_unit.accountCode);
            assertEquals("à¡@@lY", l_unit.accountName);
            assertEquals("19830206", WEB3DateUtility.formatDate(
                    l_unit.orderDate, "yyyyMMdd"));
            assertEquals("9", l_unit.cashinoutStatus);
            assertEquals(null, l_unit.cashinAmt);
            assertEquals("1200", l_unit.cashoutAmt);
            assertEquals("D", l_unit.orderRoutDiv);
            assertEquals(null, l_unit.operatorCode);
            assertEquals("119", l_unit.financialBranchCode);
            assertEquals("110", l_unit.financialInstitutionCode);
            assertEquals("1", l_unit.accountTypeCode);
            assertEquals("007", l_unit.financialAccountCode);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * PÊParams.¶íÊ == "1007" PÊParams.oHæª = "Dh PÊParams.¶?Ô = "6h
     * PÊParams.æÁæª = "0h TestCaseNO:51
     */
    public void testCreateAioListDetailsCase011() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase010";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setComDebitNumber("88");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 202G
            // ónú=PÊParams.ónúG
            // XR[h = PÊParams.XR[h G
            // ÚqR[h = PÊParams.ÚqR[h G
            // Úq¼ = PÊParams.Úq¼ G
            // ¶ú = PÊParams.¶ú G
            // Xe[^X = 9
            // üààz = null
            // oààz = PÊParams¶ÉÊ
            // üÍoH = PÊParams.üÍoH
            // üÍÒ = null
            // âsR[h = null
            // xXR[h = null
            // ûÀíÊ = null
            // ûÀÔ = null
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);
            assertEquals("202", l_unit.orderType);
            assertEquals(WEB3DateUtility.formatDate(l_unit.deliveryDate,
                    "yyyyMMdd"), "20071212");
            assertEquals("130", l_unit.branchCode);
            assertEquals(l_mainAccountParams.getAccountCode().substring(0, 6),
                    l_unit.accountCode);
            assertEquals("à¡@@lY", l_unit.accountName);
            assertEquals("19830206", WEB3DateUtility.formatDate(
                    l_unit.orderDate, "yyyyMMdd"));
            assertEquals("9", l_unit.cashinoutStatus);
            assertEquals(null, l_unit.cashinAmt);
            assertEquals("1200", l_unit.cashoutAmt);
            assertEquals("D", l_unit.orderRoutDiv);
            assertEquals(null, l_unit.operatorCode);
            assertEquals(null, l_unit.financialBranchCode);
            assertEquals(null, l_unit.financialInstitutionCode);
            assertEquals(null, l_unit.accountTypeCode);
            assertEquals(null, l_unit.financialAccountCode);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * PÊParams.¶íÊ == "1011" PÊParams.oHæª = "Dh PÊParams.¶?Ô = "6h
     * PÊParams.æÁæª = "0h TestCaseNO:52
     */
    public void testCreateAioListDetailsCase012() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase010";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setComDebitNumber("88");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 203G
            // ónú=PÊParams.ónúG
            // XR[h = PÊParams.XR[h G
            // ÚqR[h = PÊParams.ÚqR[h G
            // Úq¼ = PÊParams.Úq¼ G
            // ¶ú = PÊParams.¶ú G
            // Xe[^X = 9
            // üààz = null
            // oààz = PÊParams¶ÉÊ
            // üÍoH = PÊParams.üÍoH
            // üÍÒ = null
            // âsR[h = null
            // xXR[h = null
            // ûÀíÊ = null
            // ûÀÔ = null
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);
            assertEquals("203", l_unit.orderType);
            assertEquals(WEB3DateUtility.formatDate(l_unit.deliveryDate,
                    "yyyyMMdd"), "20071212");
            assertEquals("130", l_unit.branchCode);
            assertEquals(l_mainAccountParams.getAccountCode().substring(0, 6),
                    l_unit.accountCode);
            assertEquals("à¡@@lY", l_unit.accountName);
            assertEquals("19830206", WEB3DateUtility.formatDate(
                    l_unit.orderDate, "yyyyMMdd"));
            assertEquals("9", l_unit.cashinoutStatus);
            assertEquals(null, l_unit.cashinAmt);
            assertEquals("1200", l_unit.cashoutAmt);
            assertEquals("D", l_unit.orderRoutDiv);
            assertEquals(null, l_unit.operatorCode);
            assertEquals(null, l_unit.financialBranchCode);
            assertEquals(null, l_unit.financialInstitutionCode);
            assertEquals(null, l_unit.accountTypeCode);
            assertEquals(null, l_unit.financialAccountCode);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * PÊParams.¶íÊ == "1017" PÊParams.oHæª = "Dh PÊParams.¶?Ô = "6h
     * PÊParams.æÁæª = "0h TestCaseNO:53
     */
    public void testCreateAioListDetailsCase013() {
        final String STR_METHOD_NAME = ".testCreateAioListDetailsCase010";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setComDebitNumber("88");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_OTHER_TRANSFER);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 204G
            // ónú=PÊParams.ónúG
            // XR[h = PÊParams.XR[h G
            // ÚqR[h = PÊParams.ÚqR[h G
            // Úq¼ = PÊParams.Úq¼ G
            // ¶ú = PÊParams.¶ú G
            // Xe[^X = 9
            // üààz = null
            // oààz = PÊParams¶ÉÊ
            // üÍoH = PÊParams.üÍoH
            // üÍÒ = null
            // âsR[h = null
            // xXR[h = null
            // ûÀíÊ = null
            // ûÀÔ = null
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);
            assertEquals("204", l_unit.orderType);
            assertEquals(WEB3DateUtility.formatDate(l_unit.deliveryDate,
                    "yyyyMMdd"), "20071212");
            assertEquals("130", l_unit.branchCode);
            assertEquals(l_mainAccountParams.getAccountCode().substring(0, 6),
                    l_unit.accountCode);
            assertEquals("à¡@@lY", l_unit.accountName);
            assertEquals("19830206", WEB3DateUtility.formatDate(
                    l_unit.orderDate, "yyyyMMdd"));
            assertEquals("9", l_unit.cashinoutStatus);
            assertEquals(null, l_unit.cashinAmt);
            assertEquals("1200", l_unit.cashoutAmt);
            assertEquals("D", l_unit.orderRoutDiv);
            assertEquals(null, l_unit.operatorCode);
            assertEquals(null, l_unit.financialBranchCode);
            assertEquals(null, l_unit.financialInstitutionCode);
            assertEquals(null, l_unit.accountTypeCode);
            assertEquals(null, l_unit.financialAccountCode);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * ø.¶PÊparams.¶íÊ == "1011(×ÖÛØàUÖ¶(aèà©ç×ÖÛØà))"
     * ûÀíÊF@@ø.¶PÊparams.Ev¼
     */
    public void testCreateAioListDetailsCase014() {
        final String STR_METHOD_NAME = "testCreateAioListDetailsCase014";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setComDebitNumber("88");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            l_aioOrderUnitParams.setRemarkName("test1");

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 1011G
            // ûÀíÊ = ø.¶PÊparams.Ev¼
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);

            assertEquals("5", l_unit.accountTypeCode);
        }
        catch (Exception l_ex)
        {
            log.error("Error" + l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * ø.¶PÊparams.¶íÊ == "1012(×ÖÛØàUÖ(×ÖÛØà©çaèà))"
     * ûÀíÊF@@ø.¶PÊparams.Ev¼
     */
    public void testCreateAioListDetailsCase015() {
        final String STR_METHOD_NAME = "testCreateAioListDetailsCase015";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246l);
            l_aioOrderUnitParams.setDeliveryDate(l_date);
            l_aioOrderUnitParams.setSonarTraderCode("11124");
            l_aioOrderUnitParams.setComDebitNumber("88");
            l_aioOrderUnitParams.setDescription("123456789abcdefg");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setQuantity(1200);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            l_aioOrderUnitParams.setCancelType("0");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20071212", "yyyyMMdd"));
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            l_aioOrderUnitParams.setRemarkName("test2");

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("130");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("15");
            l_mainAccountParams.setBranchCode("130");
            l_mainAccountParams.setAccountCode("1413020");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_insTitutiParams = TestDBUtility
                    .getInstitutionRow();
            l_insTitutiParams.setInstitutionCode("15");
            TestDBUtility.insertWithDel(l_insTitutiParams);

            // ¶íÊ = 1012G
            // ûÀíÊ = ø.¶PÊparams.Ev¼
            WEB3AioAdminCashTransferListUnit l_unit = l_ListServiceImpl
                    .createAioListDetails(l_aioOrderUnitParams);

            assertEquals("5", l_unit.accountTypeCode);
        }
        catch (Exception l_ex)
        {
            log.error("Error" + l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * validate ÀöëIîµ ErrorCode:BUSINESS_ERROR_01056 ErrorMessage:ÇÒ À`FbNG[B
     * TestCaseNO:54
     */
    public void testGetListScreenCase001() {
        final String STR_METHOD_NAME = ".testGetListScreenCase001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, false);

            WEB3AdminAioCashTransferListRequestForMock l_request = new WEB3AdminAioCashTransferListRequestForMock();
            l_ListServiceImpl.getListScreen(l_request);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex
                    .getErrorInfo());
        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * validate À³í; ErrorCode:BUSINESS_ERROR_01074
     * ErrorMessage:ÇÒÍYXÉÎ·é ÀªÈ¢Å·B TestCaseNO:55
     */
    public void testGetListScreenCase002() {
        final String STR_METHOD_NAME = ".testGetListScreenCase002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("628");
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, "624", false);

            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionId(33l);
            TestDBUtility.insertWithDel(l_insParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33l);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdminAioCashTransferListRequestForMock l_request = new WEB3AdminAioCashTransferListRequestForMock();

            l_request.branchCode = new String[] { "624" };
            l_request.accountCode = "141";
            l_ListServiceImpl.getListScreen(l_request);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex
                    .getErrorInfo());

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * validate À³í; ErrorCode:BUSINESS_ERROR_01987
     * ErrorMessage:ÚqR[hÉÎ·éÚqÍo^³êÄ¢Ü¹ñB TestCaseNO:56
     */
    public void testGetListScreenCase003() {
        final String STR_METHOD_NAME = ".testGetListScreenCase001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, "624", true);

            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionId(33l);
            TestDBUtility.insertWithDel(l_insParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33l);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdminAioCashTransferListRequestForMock l_request = new WEB3AdminAioCashTransferListRequestForMock();

            l_request.branchCode = new String[] { "624" };
            l_request.accountCode = "141";
            l_ListServiceImpl.getListScreen(l_request);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01987, l_ex
                    .getErrorInfo());

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * validate À³í; validateX À³í; RequestIXR[hI·x×1iLoop:1) AÝX\ s¶ÝIê
     * ErrorCodeSYSTEM_ERROR_80005 ErrorMessage:e[uÉY·éf[^ª èÜ¹ñB
     * TestCaseNO:57
     */
    public void testGetListScreenCase004() {
        final String STR_METHOD_NAME = ".testGetListScreenCase001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, "624", true);

            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionId(33l);
            TestDBUtility.insertWithDel(l_insParams);

            WEB3AdminAioCashTransferListRequestForMock l_request = new WEB3AdminAioCashTransferListRequestForMock();

            l_request.branchCode = new String[] { "624" };
            l_request.accountCode = "141";
            l_ListServiceImpl.getListScreen(l_request);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * validate À³í; ErrorCode:BUSINESS_ERROR_01987
     * ErrorMessage:ÚqR[hÉÎ·éÚqÍo^³êÄ¢Ü¹ñB TestCaseNO:58
     */
    public void testGetListScreenCase005() {
        final String STR_METHOD_NAME = ".testGetListScreenCase005";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, "624", true);
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, "625", true);

            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionId(33l);
            TestDBUtility.insertWithDel(l_insParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33l);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionId(32l);
            l_branchParams1.setInstitutionCode("1D");
            l_branchParams1.setBranchCode("625");
            l_branchParams1.setBranchId(33382l);
            TestDBUtility.insertWithDel(l_branchParams1);

            WEB3AdminAioCashTransferListRequestForMock l_request = new WEB3AdminAioCashTransferListRequestForMock();

            l_request.branchCode = new String[] { "624" };
            l_request.accountCode = "141";
            l_ListServiceImpl.getListScreen(l_request);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01987, l_ex
                    .getErrorInfo());

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * validate À³í; validateX À³í; RequestIXR[hI·x×1iLoop:1)G NGXgf[^.ÚqR[h !=
     * nul G Úq}X^LG ¶PÊe[u,ûõsoÉIîµ(Loop:0) ErrorCode:BUSINESS_ERROR_01037
     * ErrorMessage:ðÉY·éf[^ª¶ÝµÈ¢B TestCaseNO:59
     */
    public void testGetListScreenCase006() {
        final String STR_METHOD_NAME = ".testGetListScreenCase006";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, "624", true);

            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionId(33l);
            TestDBUtility.insertWithDel(l_insParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33l);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1413021");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3AdminAioCashTransferListRequestForMock l_request = new WEB3AdminAioCashTransferListRequestForMock();

            l_request.branchCode = new String[] { "624" };
            l_request.accountCode = "1413021";
            l_ListServiceImpl.getListScreen(l_request);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex
                    .getErrorInfo());

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * Request.XR[h={624}; äI XID:262624 Úq\LÉB Úq}X^LG ¶PÊe[u,ûõo1ðÉG
     * Request.y[Wà\¦s = 1G Request.vy[WÔ = 1G TestCaseNO:60
     */
    public void testGetListScreenCase008() {
        final String STR_METHOD_NAME = ".testGetListScreenCase008";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, "624", true);

            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionId(33l);
            TestDBUtility.insertWithDel(l_insParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33l);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(33381l);
            l_mainAccountParams.setAccountCode("1413021");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20070206", "yyyyMMdd"));
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            WEB3AdminAioCashTransferListRequestForMock l_request = new WEB3AdminAioCashTransferListRequestForMock();

            l_request.branchCode = new String[] { "624" };
            l_request.accountCode = "1413021";
            l_request.deliveryDate = WEB3DateUtility.getDate("20070206",
                    "yyyyMMdd");
            l_request.pageIndex = "1";
            l_request.pageSize = "1";

            WEB3AdminAioCashTransferListResponse l_response = l_ListServiceImpl
                    .getListScreen(l_request);
            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex
                    .getErrorInfo());

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * Request.XR[h={624}; äI XID:262624 Úq\LÉB ¶PÊe[u,ûõo5ðÉG
     * Request.y[Wà\¦s = 2G Request.vy[WÔ = 2G üoàê¾×ê[]
     * ·x×2G¥¶PÊe[u,ûõoIæ3 æ4IªÉB \¦y[WÔF2 y[WF3 R[hF5
     * 
     * TestCaseNO:61
     */
    public void testGetListScreenCase009() {
        final String STR_METHOD_NAME = ".testGetListScreenCase009";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, "624", true);

            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionId(33l);
            TestDBUtility.insertWithDel(l_insParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33l);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(33381l);
            l_mainAccountParams.setAccountCode("1413021");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            Date l_datDeli = WEB3DateUtility.getDate("20070206", "yyyyMMdd");
            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            WEB3AdminAioCashTransferListRequestForMock l_request = new WEB3AdminAioCashTransferListRequestForMock();

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246L);
            l_aioOrderUnitParams2.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams2.setOrderRootDiv("9");
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246L);
            l_aioOrderUnitParams3.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams3.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams3.setOrderRootDiv("D");
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4th(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(330414808004L);
            l_aioOrderUnitParams4.setBranchId(33381l);
            l_aioOrderUnitParams4.setAccountId(333812512246L);
            l_aioOrderUnitParams4.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams4.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setComDebitNumber("34");
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // The 5th(no)
            AioOrderUnitParams l_aioOrderUnitParam5 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParam5.setSubAccountId(11226l);
            l_aioOrderUnitParam5.setOrderUnitId(330414808005L);
            l_aioOrderUnitParam5.setAccountId(333812512246L);
            l_aioOrderUnitParam5.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParam5.setBranchId(33381l);
            l_aioOrderUnitParam5.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParam5.setTraderId(10005);
            l_aioOrderUnitParam5.setComDebitNumber("34");
            l_aioOrderUnitParam5.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParam5);

            l_request.branchCode = new String[] { "624" };
            l_request.accountCode = "1413021";
            l_request.deliveryDate = WEB3DateUtility.getDate("20070206",
                    "yyyyMMdd");
            l_request.pageIndex = "2";
            l_request.pageSize = "2";

            WEB3AdminAioCashTransferListResponse l_response = l_ListServiceImpl
                    .getListScreen(l_request);
            assertEquals("2", l_response.pageIndex);
            assertEquals("3", l_response.totalPages);
            assertEquals("5", l_response.totalRecords);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex
                    .getErrorInfo());

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * Request.XR[h={624}; äI XID:262624 Úq\LÉB ¶PÊe[u,ûõo5ðÉG
     * Request.y[Wà\¦s = 5G Request.vy[WÔ = 2G üoàê¾×ê[]
     * ·x×2G¥¶PÊe[u,ûõoIæ3 æ4IªÉB \¦y[WÔF1 y[WF1 R[hF5
     * 
     * TestCaseNO:62
     */
    public void testGetListScreenCase010() {
        final String STR_METHOD_NAME = ".testGetListScreenCase009";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, "624", true);

            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionId(33l);
            TestDBUtility.insertWithDel(l_insParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33l);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(33381l);
            l_mainAccountParams.setAccountCode("1413021");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            Date l_datDeli = WEB3DateUtility.getDate("20070206", "yyyyMMdd");
            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            WEB3AdminAioCashTransferListRequestForMock l_request = new WEB3AdminAioCashTransferListRequestForMock();

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246L);
            l_aioOrderUnitParams2.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams2.setOrderRootDiv("9");
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246L);
            l_aioOrderUnitParams3.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams3.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams3.setOrderRootDiv("D");
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4th(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(330414808004L);
            l_aioOrderUnitParams4.setBranchId(33381l);
            l_aioOrderUnitParams4.setAccountId(333812512246L);
            l_aioOrderUnitParams4.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams4.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setComDebitNumber("34");
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // The 5th(no)
            AioOrderUnitParams l_aioOrderUnitParam5 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParam5.setSubAccountId(11226l);
            l_aioOrderUnitParam5.setOrderUnitId(330414808005L);
            l_aioOrderUnitParam5.setAccountId(333812512246L);
            l_aioOrderUnitParam5.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParam5.setBranchId(33381l);
            l_aioOrderUnitParam5.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParam5.setTraderId(10005);
            l_aioOrderUnitParam5.setComDebitNumber("34");
            l_aioOrderUnitParam5.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParam5);

            l_request.branchCode = new String[] { "624" };
            l_request.accountCode = "1413021";
            l_request.deliveryDate = WEB3DateUtility.getDate("20070206",
                    "yyyyMMdd");
            l_request.pageIndex = "1";
            l_request.pageSize = "5";

            WEB3AdminAioCashTransferListResponse l_response = l_ListServiceImpl
                    .getListScreen(l_request);
            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("5", l_response.totalRecords);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex
                    .getErrorInfo());

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * Request.XR[h={624}; äI XID:262624 Úq\LÉB ¶PÊe[u,ûõo5ðÉG
     * Request.y[Wà\¦s = 4G Request.vy[WÔ = 2G üoàê¾×ê[]
     * ·x×2G¥¶PÊe[u,ûõoIæ3 æ4IªÉB \¦y[WÔF1 y[WF1 R[hF5
     * 
     * TestCaseNO:63
     */
    public void testGetListScreenCase011() {
        final String STR_METHOD_NAME = ".testGetListScreenCase009";
        log.entering(TEST_START + STR_METHOD_NAME);

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, "624", true);

            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionId(33l);
            TestDBUtility.insertWithDel(l_insParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33l);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381l);
            TestDBUtility.insertWithDel(l_branchParams);

            // [Úq]
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(33381l);
            l_mainAccountParams.setAccountCode("1413021");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            Date l_datDeli = WEB3DateUtility.getDate("20070206", "yyyyMMdd");
            // ¶PÊe[u
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setBranchId(33381l);
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams
                    .setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setTraderId(10001l);
            l_aioOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19830206", "yyyyMMdd"));
            // The firt(ok)
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            WEB3AdminAioCashTransferListRequestForMock l_request = new WEB3AdminAioCashTransferListRequestForMock();

            // The secned(ok)
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams2.setSubAccountId(11223l);
            l_aioOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_aioOrderUnitParams2.setBranchId(33381l);
            l_aioOrderUnitParams2.setAccountId(333812512246L);
            l_aioOrderUnitParams2.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams2.setOrderRootDiv("9");
            l_aioOrderUnitParams2.setTraderId(10002);
            l_aioOrderUnitParams2.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19810206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);

            // The 3rd(ok)
            AioOrderUnitParams l_aioOrderUnitParams3 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams3.setSubAccountId(11224l);
            l_aioOrderUnitParams3.setOrderUnitId(330414808003L);
            l_aioOrderUnitParams3.setBranchId(33381l);
            l_aioOrderUnitParams3.setAccountId(333812512246L);
            l_aioOrderUnitParams3.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams3.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams3.setOrderRootDiv("D");
            l_aioOrderUnitParams3.setTraderId(10003);
            l_aioOrderUnitParams3.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams3);

            // The 4th(ok)
            AioOrderUnitParams l_aioOrderUnitParams4 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams4.setSubAccountId(11225l);
            l_aioOrderUnitParams4.setOrderUnitId(330414808004L);
            l_aioOrderUnitParams4.setBranchId(33381l);
            l_aioOrderUnitParams4.setAccountId(333812512246L);
            l_aioOrderUnitParams4.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParams4.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams4.setTraderId(10004);
            l_aioOrderUnitParams4.setComDebitNumber("34");
            l_aioOrderUnitParams4.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams4);

            // The 5th(no)
            AioOrderUnitParams l_aioOrderUnitParam5 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParam5.setSubAccountId(11226l);
            l_aioOrderUnitParam5.setOrderUnitId(330414808005L);
            l_aioOrderUnitParam5.setAccountId(333812512246L);
            l_aioOrderUnitParam5.setDeliveryDate(l_datDeli);
            l_aioOrderUnitParam5.setBranchId(33381l);
            l_aioOrderUnitParam5.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParam5.setTraderId(10005);
            l_aioOrderUnitParam5.setComDebitNumber("34");
            l_aioOrderUnitParam5.setReceivedDateTime(WEB3DateUtility.getDate(
                    "19820206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParam5);

            l_request.branchCode = new String[] { "624" };
            l_request.accountCode = "1413021";
            l_request.deliveryDate = WEB3DateUtility.getDate("20070206",
                    "yyyyMMdd");
            l_request.pageIndex = "2";
            l_request.pageSize = "4";

            WEB3AdminAioCashTransferListResponse l_response = l_ListServiceImpl
                    .getListScreen(l_request);
            assertEquals("2", l_response.pageIndex);
            assertEquals("2", l_response.totalPages);
            assertEquals("5", l_response.totalRecords);

        } catch (WEB3BaseException l_ex) {
            log.debug("Error in WEB3BaseException...", l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex
                    .getErrorInfo());

        } catch (Exception e) {
            log.error("Error" + e);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);

    }

    /**
     * üoàê_E[hNGXgIá TestCaseNO:64
     */
    public void testGetDownFiles001() {
        final String STR_METHOD_NAME = " .testGetDownFiles001";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0102", false, false);

            l_ListServiceImpl.getDownloadFile(l_request);
        } catch (WEB3BaseException l_ex) {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex
                    .getErrorInfo());
        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * üoàê_E[hNGXgIá TestCaseNO:65
     */
    public void testGetDownFiles002() {
        final String STR_METHOD_NAME = " .testGetDownFiles002";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[1];
            l_strs[0] = "33381";
            l_request.branchCode = l_strs;
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], false);

            l_ListServiceImpl.getDownloadFile(l_request);
        } catch (WEB3BaseException l_ex) {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex
                    .getErrorInfo());
        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * Request.XR[h={624}; äI XID:262624 TestCaseNO:66
     */
    public void testGetDownFiles003() {
        final String STR_METHOD_NAME = " .testGetDownFiles003";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[1];
            l_strs[0] = "624";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "624";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "1234";
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setAccountCode("12345");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_ListServiceImpl.getDownloadFile(l_request);
        } catch (WEB3BaseException l_ex) {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01987, l_ex
                    .getErrorInfo());
        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * XR[h={624}; äI XID:262624 TestCaseNO:67
     */
    public void testGetDownFiles004() {
        final String STR_METHOD_NAME = " .testGetDownFiles004";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[1];
            l_strs[0] = "624";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "623";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "1234";
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setAccountCode("1234");

            l_ListServiceImpl.getDownloadFile(l_request);
        } catch (WEB3BaseException l_ex) {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex
                    .getErrorInfo());
        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * XR[h={624}; äI XID:262624 TestCaseNO:68
     */
    public void testGetDownFiles005() {
        final String STR_METHOD_NAME = " .testGetDownFiles005";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[1];
            l_strs[0] = "624";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "624";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "1234";
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1234");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            l_ListServiceImpl.getDownloadFile(l_request);
        } catch (WEB3BaseException l_ex) {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex
                    .getErrorInfo());
        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * Request.XR[h={624}; äI XID:262624 ¶PÊe[uûõs TestCaseNO:69
     */
    public void testGetDownFiles006() {
        final String STR_METHOD_NAME = " .testGetDownFiles006";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();

        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionId(33381330001L);
            l_branchParams1.setBranchCode("625");
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setBranchId(33382);
            TestDBUtility.insertWithDel(l_branchParams1);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[2];
            l_strs[0] = "624";
            l_strs[1] = "625";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "624";
            l_request.branchCode[1] = "625";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "1234";
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1234");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            l_ListServiceImpl.getDownloadFile(l_request);
        } catch (WEB3BaseException l_ex) {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex
                    .getErrorInfo());
        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * XR[h={624}; äI XID:262624 Request.XR[h={624}; äI XID:262624 [X\]
     * [¶dÊ\] [Úq\] TestCaseNO:70
     */
    public void testGetDownFiles007() {
        final String STR_METHOD_NAME = " .testGetDownFiles007";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[1];
            l_strs[0] = "624";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "624";
            l_request.orderType = "200";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "123456";
            l_request.deliveryDate = WEB3DateUtility.getDate("20040202",
                    "yyyyMMdd");
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33381330001L);
            l_mainAccountParams.setBranchId(33381330001L);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setAccountId(33381330001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(33381330001L);
            l_aioOrderUnitParams.setBranchId(33381330001L);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_OUT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            WEB3AdminAioCashTransferListDownloadResponse l_response = l_ListServiceImpl
                    .getDownloadFile(l_request);
            log.debug("************************* downloadFile = "
                    + l_response.downloadFile[0]);

        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * XR[h={624}; äI XID:262624 Request.XR[h={624}; äI XID:262624 [X\]
     * [¶dÊ\] [Úq\] [UæàZ@@Ö] ¶PÊe[u.¶íÊ == "1001
     * ä¶dÊ\IæêÉ\ûõogetUæàZ@@ÖR[h æ2ssB TestCaseNO:71
     */
    public void testGetDownFiles008() {
        final String STR_METHOD_NAME = " .testGetDownFiles008";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionId(33381330001L);
            l_branchParams1.setBranchCode("625");
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setBranchId(33382);
            TestDBUtility.insertWithDel(l_branchParams1);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[2];
            l_strs[0] = "624";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "624";
            l_request.branchCode[1] = "625";
            l_request.orderType = "200";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "123456";
            l_request.deliveryDate = WEB3DateUtility.getDate("20040202",
                    "yyyyMMdd");
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33381330001L);
            l_mainAccountParams.setBranchId(33381330001L);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setAccountId(33381330001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(33381330001L);
            l_aioOrderUnitParams.setBranchId(33381330001L);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_OUT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams1.setAccountId(33381330001L);
            l_aioOrderUnitParams1.setBranchId(33381330001L);
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams1.setOrderType(OrderTypeEnum.CASH_OUT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);

            WEB3AdminAioCashTransferListDownloadResponse l_response = l_ListServiceImpl
                    .getDownloadFile(l_request);
            log.debug("************************* downloadFile = "
                    + l_response.downloadFile[0]);

        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * XR[h={624}; äI XID:262624 Request.XR[h={624}; äI XID:262624 [X\]
     * [Úq\] ¶PÊe[u.¶íÊ == "1002 TestCaseNO:72
     */
    public void testGetDownFiles009() {
        final String STR_METHOD_NAME = " .testGetDownFiles009";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionId(33381330001L);
            l_branchParams1.setBranchCode("625");
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setBranchId(33382);
            TestDBUtility.insertWithDel(l_branchParams1);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[2];
            l_strs[0] = "624";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "624";
            l_request.branchCode[1] = "625";
            l_request.orderType = "100";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "123456";
            l_request.deliveryDate = WEB3DateUtility.getDate("20040202",
                    "yyyyMMdd");
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33381330001L);
            l_mainAccountParams.setBranchId(33381330001L);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setAccountId(33381330001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(33381330001L);
            l_aioOrderUnitParams.setBranchId(33381330001L);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams1.setAccountId(33381330001L);
            l_aioOrderUnitParams1.setBranchId(33381330001L);
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams1.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams1.setOrderRootDiv("9");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);

            WEB3AdminAioCashTransferListDownloadResponse l_response = l_ListServiceImpl
                    .getDownloadFile(l_request);
            log.debug("************************* downloadFile = "
                    + l_response.downloadFile[0]);

        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * XR[h={624}; äI XID:262624
     * ªæ2ÉI ûÀID ûõsÉ
     * TestCaseNO:73
     */
    public void testGetDownFiles0010() {
        final String STR_METHOD_NAME = " .testGetDownFiles0010";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[1];
            l_strs[0] = "624";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "624";
            l_request.orderType = "100";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "123456";
            l_request.deliveryDate = WEB3DateUtility.getDate("20040202",
                    "yyyyMMdd");
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33381330001L);
            l_mainAccountParams.setBranchId(33381330001L);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setAccountId(33381330001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(33381330001L);
            l_aioOrderUnitParams.setBranchId(33381330002L);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams1.setAccountId(33381330001L);
            l_aioOrderUnitParams1.setBranchId(33381330001L);
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams1.setOrderType(OrderTypeEnum.CASH_IN);
            l_aioOrderUnitParams1.setOrderRootDiv("9");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);

            WEB3AdminAioCashTransferListDownloadResponse l_response = l_ListServiceImpl
                    .getDownloadFile(l_request);
            log.debug("************************* downloadFile = "
                    + l_response.downloadFile[0]);

        } catch (WEB3BaseException l_ex) {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * ¶PÊRow.¶íÊ == "1011(×ÖÛØàUÖ¶(aèà©ç×ÖÛØà))"
     * and ¶PÊRow.Ev¼ != null
     */
    public void testGetDownFiles011()
    {
        final String STR_METHOD_NAME = "testGetDownFiles011()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionId(33381330001L);
            l_branchParams1.setBranchCode("625");
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setBranchId(33382);
            TestDBUtility.insertWithDel(l_branchParams1);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[2];
            l_strs[0] = "624";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "624";
            l_request.branchCode[1] = "625";
            l_request.orderType = "000";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "123456";
            l_request.deliveryDate = WEB3DateUtility.getDate("20040202",
                    "yyyyMMdd");

            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33381330001L);
            l_mainAccountParams.setBranchId(33381330001L);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setAccountId(33381330001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(33381330001L);
            l_aioOrderUnitParams.setBranchId(33381330001L);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setRemarkName("test1");
            l_aioOrderUnitParams.setComDebitNumber("20");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams1.setAccountId(33381330001L);
            l_aioOrderUnitParams1.setBranchId(33381330001L);
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams1.setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_aioOrderUnitParams1.setOrderRootDiv("9");
            l_aioOrderUnitParams1.setRemarkName("test2");
            l_aioOrderUnitParams1.setComDebitNumber("21");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);

            WEB3AdminAioCashTransferListDownloadResponse l_response = l_ListServiceImpl
                    .getDownloadFile(l_request);
            log.debug("************************* downloadFile = "
                    + l_response.downloadFile[0]);

            int l_intDownloadFileLength = l_response.downloadFile.length;
            String[] l_strSplitResult =
                l_response.downloadFile[l_intDownloadFileLength - 1].split(",");
            assertEquals("åØFX", l_strSplitResult[l_strSplitResult.length - 1]);

        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * ¶PÊRow.¶íÊ == "1012(×ÖÛØàUÖ(×ÖÛØà©çaèà))"
     * and ¶PÊRow.Ev¼ != null
     */
    public void testGetDownFiles012()
    {
        final String STR_METHOD_NAME = "testGetDownFiles012()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionId(33381330001L);
            l_branchParams1.setBranchCode("625");
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setBranchId(33382);
            TestDBUtility.insertWithDel(l_branchParams1);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[2];
            l_strs[0] = "624";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "624";
            l_request.branchCode[1] = "625";
            l_request.orderType = "100";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "123456";
            l_request.deliveryDate = WEB3DateUtility.getDate("20040202",
                    "yyyyMMdd");
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33381330001L);
            l_mainAccountParams.setBranchId(33381330001L);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setAccountId(33381330001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(33381330001L);
            l_aioOrderUnitParams.setBranchId(33381330001L);
            l_aioOrderUnitParams.setRemarkName("test1");
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams1.setAccountId(33381330001L);
            l_aioOrderUnitParams1.setBranchId(33381330001L);
            l_aioOrderUnitParams1.setRemarkName("test2");
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams1.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams1.setOrderRootDiv("9");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);

            WEB3AdminAioCashTransferListDownloadResponse l_response = l_ListServiceImpl
                    .getDownloadFile(l_request);
            log.debug("************************* downloadFile = "
                    + l_response.downloadFile[0]);

            int l_intDownloadFileLength = l_response.downloadFile.length;
            String[] l_strSplitResult =
                l_response.downloadFile[l_intDownloadFileLength - 1].split(",");
            assertEquals("åØFX", l_strSplitResult[l_strSplitResult.length - 1]);

        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * ¶PÊRow.¶íÊ == "1001(×ÖÛØàUÖ(×ÖÛØà©çaèà))"
     * and ¶PÊRow.Ev¼ != null
     */
    public void testGetDownFiles013()
    {
        final String STR_METHOD_NAME = "testGetDownFiles013()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionId(33381330001L);
            l_branchParams1.setBranchCode("625");
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setBranchId(33382);
            TestDBUtility.insertWithDel(l_branchParams1);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[2];
            l_strs[0] = "624";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "624";
            l_request.branchCode[1] = "625";
            l_request.orderType = "000";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "123456";
            l_request.deliveryDate = WEB3DateUtility.getDate("20040202",
                    "yyyyMMdd");
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33381330001L);
            l_mainAccountParams.setBranchId(33381330001L);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setAccountId(33381330001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(33381330001L);
            l_aioOrderUnitParams.setBranchId(33381330001L);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CASH_OUT);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setRemarkName("test1");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams1.setAccountId(33381330001L);
            l_aioOrderUnitParams1.setBranchId(33381330001L);
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams1.setOrderType(OrderTypeEnum.CASH_OUT);
            l_aioOrderUnitParams1.setOrderRootDiv("9");
            l_aioOrderUnitParams.setRemarkName("test2");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);

            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TransferedFinInstitutionParams l_transferedFinInstitutionParams =
                TestDBUtility.getTransferedFinInstitutionRow();
            l_transferedFinInstitutionParams.setInstitutionCode("0D");
            l_transferedFinInstitutionParams.setBranchCode("624");
            l_transferedFinInstitutionParams.setAccountCode("123456");
            l_transferedFinInstitutionParams.setDesignateDiv("A");
            l_transferedFinInstitutionParams.setFinSaveDiv("1");
            l_transferedFinInstitutionParams.setFinInstitutionCode(null);
            l_transferedFinInstitutionParams.setFinBranchCode(null);
            l_transferedFinInstitutionParams.setFinAccountNo(null);
            TestDBUtility.insertWithDel(l_transferedFinInstitutionParams);

            WEB3AdminAioCashTransferListDownloadResponse l_response = l_ListServiceImpl
                    .getDownloadFile(l_request);
            log.debug("************************* downloadFile = "
                    + l_response.downloadFile[0]);

            int l_intDownloadFileLength = l_response.downloadFile.length;
            String[] l_strSplitResult =
                l_response.downloadFile[l_intDownloadFileLength - 1].split(",");
            assertEquals("Êaà", l_strSplitResult[l_strSplitResult.length - 1]);

        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * ¶PÊRow.¶íÊ == "1012(×ÖÛØàUÖ(×ÖÛØà©çaèà))"
     * and ¶PÊRow.Ev¼ = null
     */
    public void testGetDownFiles014()
    {
        final String STR_METHOD_NAME = "testGetDownFiles014()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioCashTransferListDownloadRequestForMock l_request = new WEB3AdminAioCashTransferListDownloadRequestForMock();
        try {
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);

            AdministratorParams l_administratorRow = TestDBUtility
                    .getAdministratorRow();
            l_administratorRow.setBranchCode("624");
            l_administratorRow.setInstitutionId(33381330001L);
            l_administratorRow.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorRow);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionId(33381330001L);
            l_branchParams.setBranchId(33381330001L);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionId(33381330001L);
            l_branchParams1.setBranchCode("625");
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setBranchId(33382);
            TestDBUtility.insertWithDel(l_branchParams1);

            InstitutionParams l_institutionParams = TestDBUtility
                    .getInstitutionRow();
            l_institutionParams.setInstitutionId(33381330001L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(
                    l_administratorRow);

            WEB3AdministratorForMock
                    .mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet,
                    "B0101", false, true);

            String[] l_strs = new String[2];
            l_strs[0] = "624";
            l_request.branchCode = l_strs;
            l_request.branchCode[0] = "624";
            l_request.branchCode[1] = "625";
            l_request.orderType = "100";
            WEB3AdministratorForMock.mockValidateBranchPermission(
                    l_administratorSet, l_request.branchCode[0], true);

            l_request.accountCode = "123456";
            l_request.deliveryDate = WEB3DateUtility.getDate("20040202",
                    "yyyyMMdd");
            MainAccountParams l_mainAccountParams = TestDBUtility
                    .getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33381330001L);
            l_mainAccountParams.setBranchId(33381330001L);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setAccountId(33381330001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(33381330001L);
            l_aioOrderUnitParams.setBranchId(33381330001L);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams.setOrderRootDiv("D");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility
                    .getAioOrderUnitRow();
            l_aioOrderUnitParams1.setAccountId(33381330001L);
            l_aioOrderUnitParams1.setBranchId(33381330001L);
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate(
                    "20040202", "yyyyMMdd"));
            l_aioOrderUnitParams1.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_aioOrderUnitParams1.setOrderRootDiv("9");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);

            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TransferedFinInstitutionParams l_transferedFinInstitutionParams =
                TestDBUtility.getTransferedFinInstitutionRow();
            l_transferedFinInstitutionParams.setInstitutionCode("0D");
            l_transferedFinInstitutionParams.setBranchCode("624");
            l_transferedFinInstitutionParams.setAccountCode("123456");
            l_transferedFinInstitutionParams.setDesignateDiv("A");
            l_transferedFinInstitutionParams.setFinSaveDiv("2");
            l_transferedFinInstitutionParams.setFinInstitutionCode(null);
            l_transferedFinInstitutionParams.setFinBranchCode(null);
            l_transferedFinInstitutionParams.setFinAccountNo(null);
            TestDBUtility.insertWithDel(l_transferedFinInstitutionParams);

            WEB3AdminAioCashTransferListDownloadResponse l_response = l_ListServiceImpl
                    .getDownloadFile(l_request);
            log.debug("************************* downloadFile = "
                    + l_response.downloadFile[0]);

            int l_intDownloadFileLength = l_response.downloadFile.length;
            String[] l_strSplitResult =
                l_response.downloadFile[l_intDownloadFileLength - 1].split(",");
            assertEquals("null", l_strSplitResult[l_strSplitResult.length - 1]);

        } catch (Exception l_ex) {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    public class WEB3AdminAioListServiceImplForMockche extends
            WEB3AdminAioListServiceImpl {
        protected List getTransferedFinInstitutionRecord(
                MainAccount l_mainAccount) throws WEB3BaseException {
            List l_lisResults = new ArrayList();
            TransferedFinInstitutionParams l_tranferFinIsPa = new TransferedFinInstitutionParams();

            l_tranferFinIsPa.setInstitutionCode("14");
            l_tranferFinIsPa.setFinInstitutionCode("110");
            l_tranferFinIsPa.setFinBranchCode("119");
            l_tranferFinIsPa.setBranchCode("130");
            l_tranferFinIsPa.setAccountCode("1413020");
            l_tranferFinIsPa.setDesignateDiv("A");
            l_tranferFinIsPa.setFinAccountNo("007");
            l_tranferFinIsPa.setFinSaveDiv("1");

            l_lisResults.add(l_tranferFinIsPa);
            return l_lisResults;
        }
    }

    public class WEB3AdminAioCashTransferListDownloadRequestForMock extends
            WEB3AdminAioCashTransferListDownloadRequest {
        public void validate() throws WEB3BaseException {

        }
    }

    public class WEB3AdminAioCashTransferListRequestForMock extends
            WEB3AdminAioCashTransferListRequest {
        public void validate() throws WEB3BaseException {

        }
    }
}
@
