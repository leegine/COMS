head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.10.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositCalcResultSaveServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3IfoDepositCalcResultSaveServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/20 �k�v�u(���u) �V�K�쐬 ���f�� 132
*/
package webbroker3.ifodeposit.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifodeposit.data.IfoDepositCalcLockParams;
import webbroker3.ifodeposit.data.IfoDepositCalcLockRow;
import webbroker3.ifodeposit.data.IfoDepositCalcResultParams;
import webbroker3.ifodeposit.data.IfoDepositCalcResultRow;
import webbroker3.ifodeposit.define.WEB3IfoDepositCalcResultSaveDiv;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositCalcResultSaveServiceImplTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositCalcResultSaveServiceImplTest.class);
    WEB3IfoDepositCalcResultSaveServiceImpl l_serviceImpl = null;
    public WEB3IfoDepositCalcResultSaveServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3IfoDepositCalcResultSaveServiceImpl(); 
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //�X���b�h�J�n
    //�ڋq���ɏ؋����v�Z���ʃf�[�^���쐬 not is null
    //����getMainAccountTbl�agetSubAccountList
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            IfoDepositCalcResultParams l_param = new IfoDepositCalcResultParams();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultCreatePerAccountServiceImpl",
                    "createIfoDepositCalcResult", new Class[]
                   { WEB3GentradeSubAccount.class },
                   l_param);
            WEB3IfoDepositCalcResultSaveRequest l_request = new WEB3IfoDepositCalcResultSaveRequest();
            l_request.threadNo = new Long(1111);
            l_request.fromAccountId = 333812512245L;
            l_request.toAccountId = 333812512247L;
            WEB3IfoDepositCalcResultSaveServiceImplForTest forTest = new WEB3IfoDepositCalcResultSaveServiceImplForTest();
            WEB3IfoDepositCalcResultSaveResponse l_res = (WEB3IfoDepositCalcResultSaveResponse)forTest.execute(l_request);
            assertEquals(l_res.success, new Long(0));
            assertEquals(l_res.failure, new Long(1));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //�X���b�h�J�n
    //�ڋq���ɏ؋����v�Z���ʃf�[�^���쐬is null
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultCreatePerAccountServiceImpl",
                    "createIfoDepositCalcResult", new Class[]
                   { WEB3GentradeSubAccount.class },
                   null);
            WEB3IfoDepositCalcResultSaveRequest l_request = new WEB3IfoDepositCalcResultSaveRequest();
            l_request.threadNo = new Long(1111);
            l_request.fromAccountId = 333812512245L;
            l_request.toAccountId = 333812512247L;
            WEB3IfoDepositCalcResultSaveServiceImplForTest forTest = new WEB3IfoDepositCalcResultSaveServiceImplForTest();
            WEB3IfoDepositCalcResultSaveResponse l_res = (WEB3IfoDepositCalcResultSaveResponse)forTest.execute(l_request);
            assertEquals(l_res.success, new Long(0));
            assertEquals(l_res.failure, null);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //�X���b�h�J�n�Ȃ�
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            WEB3IfoDepositCalcResultSaveRequest l_request = new WEB3IfoDepositCalcResultSaveRequest();
            l_request.threadNo = new Long(1111);
            l_request.fromAccountId = 333812512247L;
            l_request.toAccountId = 333812512247L;
            WEB3IfoDepositCalcResultSaveServiceImplForTest2 forTest = new WEB3IfoDepositCalcResultSaveServiceImplForTest2();
            WEB3IfoDepositCalcResultSaveResponse l_res = (WEB3IfoDepositCalcResultSaveResponse)forTest.execute(l_request);
            assertEquals(l_res.success, null);
            assertEquals(l_res.failure, null);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * �X���b�h��~���s
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            WEB3IfoDepositCalcResultSaveRequest l_request = new WEB3IfoDepositCalcResultSaveRequest();
            l_request.threadNo = new Long(1111);
            l_request.fromAccountId = 333812512247L;
            l_request.toAccountId = 333812512247L;
            WEB3IfoDepositCalcResultSaveServiceImplForTest3 forTest = new WEB3IfoDepositCalcResultSaveServiceImplForTest3();
            forTest.execute(l_request);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //�S���ۑ�����
    public void testSave2DB_C0001()
    {
        final String STR_METHOD_NAME = "testSave2DB_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getPreference",
                    new Class[] {String.class},
                    "1");

            TestDBUtility.deleteAllAndCommit(IfoDepositCalcResultRow.TYPE);
            IfoDepositCalcResultParams l_param1 = new IfoDepositCalcResultParams();
            IfoDepositCalcResultParams l_param2 = new IfoDepositCalcResultParams();
            l_param1.setDepositInfoId(11);
            l_param1.setAccountId(11);
            l_param1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_param1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_param2.setDepositInfoId(22);
            l_param2.setAccountId(22);
            l_param2.setCreatedTimestamp(WEB3DateUtility.getDate("20080823", "yyyyMMdd"));
            l_param2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080823", "yyyyMMdd"));
            List l_depositCalcResultList = new ArrayList();
            l_depositCalcResultList.add(l_param1);
            l_depositCalcResultList.add(l_param2);

            WEB3IfoDepositCalcResultSaveResponse l_res = new WEB3IfoDepositCalcResultSaveResponse();

            Method l_method = WEB3IfoDepositCalcResultSaveServiceImpl.class.getDeclaredMethod("save2DB",
                new Class[]{ List.class, WEB3IfoDepositCalcResultSaveResponse.class });
            l_method.setAccessible(true);
            l_method.invoke(l_serviceImpl, new Object[]{l_depositCalcResultList, l_res});

            assertEquals(l_res.success, new Long(2));
            assertEquals(l_res.failure, null);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //���L�S���ۑ����� �����ۑ����s
    public void testSave2DB_C0002()
    {
        final String STR_METHOD_NAME = "testSave2DB_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getPreference",
                    new Class[] {String.class},
                    "1");

            TestDBUtility.deleteAllAndCommit(IfoDepositCalcResultRow.TYPE);
            IfoDepositCalcResultParams l_param1 = new IfoDepositCalcResultParams();
            IfoDepositCalcResultParams l_param2 = new IfoDepositCalcResultParams();
            l_param1.setDepositInfoId(33);
            l_param1.setAccountId(33);
            l_param1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_param1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_param2.setAccountId(44);
            l_param2.setCreatedTimestamp(null);
            l_param2.setLastUpdatedTimestamp(null);
            List l_depositCalcResultList = new ArrayList();
            l_depositCalcResultList.add(l_param1);
            l_depositCalcResultList.add(l_param2);


            WEB3IfoDepositCalcResultSaveResponse l_res = new WEB3IfoDepositCalcResultSaveResponse();

            Method l_method = WEB3IfoDepositCalcResultSaveServiceImpl.class.getDeclaredMethod("save2DB",
                new Class[]{ List.class, WEB3IfoDepositCalcResultSaveResponse.class });
            l_method.setAccessible(true);
            l_method.invoke(l_serviceImpl, new Object[]{l_depositCalcResultList, l_res});

            assertEquals(l_res.success, new Long(1));
            assertEquals(l_res.failure, new Long(1));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //�S���ۑ����s
    public void testSave2DB_C0003()
    {
        final String STR_METHOD_NAME = "testSave2DB_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getPreference",
                    new Class[] {String.class},
                    "1");

            TestDBUtility.deleteAllAndCommit(IfoDepositCalcResultRow.TYPE);
            IfoDepositCalcResultParams l_param1 = new IfoDepositCalcResultParams();
            IfoDepositCalcResultParams l_param2 = new IfoDepositCalcResultParams();
            l_param1.setDepositInfoId(55);
            l_param1.setCreatedTimestamp(null);
            l_param1.setLastUpdatedTimestamp(null);
            l_param2.setDepositInfoId(66);
            l_param2.setCreatedTimestamp(null);
            l_param2.setLastUpdatedTimestamp(null);
            List l_depositCalcResultList = new ArrayList();
            l_depositCalcResultList.add(l_param1);
            l_depositCalcResultList.add(l_param2);

            WEB3IfoDepositCalcResultSaveResponse l_res = new WEB3IfoDepositCalcResultSaveResponse();

            Method l_method = WEB3IfoDepositCalcResultSaveServiceImpl.class.getDeclaredMethod("save2DB",
                new Class[]{ List.class, WEB3IfoDepositCalcResultSaveResponse.class });
            l_method.setAccessible(true);
            l_method.invoke(l_serviceImpl, new Object[]{l_depositCalcResultList, l_res});

            assertEquals(l_res.success, new Long(0));
            assertEquals(l_res.failure, new Long(2));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * �������� = 0
     */
    public void testGetSubAccountList_C0001()
    {
        final String STR_METHOD_NAME = "testGetSubAccountList_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            
            Method l_method = WEB3IfoDepositCalcResultSaveServiceImpl.class.getDeclaredMethod("getSubAccountList",
                new Class[]{long.class, long.class});
            l_method.setAccessible(true);
            List l_subAccList = (List)l_method.invoke(l_serviceImpl, new Object[]{new Long(1000), new Long(1002)});
            
            assertTrue(l_subAccList.isEmpty());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * �⏕�����I�u�W�F�N�g���쐬
     */
    public void testGetSubAccountList_C0002()
    {
        final String STR_METHOD_NAME = "testGetSubAccountList_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(1001);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            Method l_method = WEB3IfoDepositCalcResultSaveServiceImpl.class.getDeclaredMethod("getSubAccountList",
                new Class[]{long.class, long.class});
            l_method.setAccessible(true);
            List l_subAccList = (List)l_method.invoke(l_serviceImpl, new Object[]{new Long(1000), new Long(1002)});
            
            assertFalse(l_subAccList.isEmpty());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * �������� = 0
     */
    public void testGetMainAccountTbl_C0001()
    {
        final String STR_METHOD_NAME = "testGetMainAccountTbl_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            
            Method l_method = WEB3IfoDepositCalcResultSaveServiceImpl.class.getDeclaredMethod("getMainAccountTbl",
                new Class[]{long.class, long.class});
            l_method.setAccessible(true);
            Hashtable l_tblMainAcc = (Hashtable)l_method.invoke(l_serviceImpl, new Object[]{new Long(1000), new Long(1002)});
            
            assertTrue(l_tblMainAcc.isEmpty());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * main�����I�u�W�F�N�g���쐬
     */
    public void testGetMainAccountTbl_C0002()
    {
        final String STR_METHOD_NAME = "testGetMainAccountTbl_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            Method l_method = WEB3IfoDepositCalcResultSaveServiceImpl.class.getDeclaredMethod("getMainAccountTbl",
                new Class[]{long.class, long.class});
            l_method.setAccessible(true);
            Hashtable l_tblMainAcc = (Hashtable)l_method.invoke(l_serviceImpl, new Object[]{new Long(1000), new Long(1002)});
            
            assertFalse(l_tblMainAcc.isEmpty());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //�w�肳�ꂽ�X���b�h�̃��b�N������������B
    //2)�擾�����ꍇ�A������Ԃ́u�P�F�������v�ł��邩�ǂ����𔻒f����F
    //2-1)�u�P�F�������v�ł���ꍇ�Afalse��Ԃ�
    public void testLockThread_C0001()
    {
        final String STR_METHOD_NAME = "testLockThread_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {  
            TestDBUtility.deleteAll(IfoDepositCalcLockRow.TYPE);
            IfoDepositCalcLockParams depositCalcLockParams = new IfoDepositCalcLockParams();
            depositCalcLockParams.setServiceName("webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultSaveServiceImpl");
            depositCalcLockParams.setThreadNo(1);
            depositCalcLockParams.setApHostName("LockThread");
            depositCalcLockParams.setStatus(WEB3IfoDepositCalcResultSaveDiv.LOCK_PROC);
            depositCalcLockParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            depositCalcLockParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(depositCalcLockParams);

            TestDBUtility.commit();
            assertFalse(l_serviceImpl.lockThread(1));

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //�w�肳�ꂽ�X���b�h�̃��b�N������������B
    //2)�擾�����ꍇ�A������Ԃ́u�P�F�������v�ł��邩�ǂ����𔻒f����F
    //2-2)�u�P�F�������v�łȂ��ꍇ�A�X���b�hNo�����ɁA������Ԃ��P�F�������ɍX�V����B �X�V����������Atrue��Ԃ�
    public void testLockThread_C0002()
    {
        final String STR_METHOD_NAME = "testLockThread_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoDepositCalcLockRow.TYPE);
            IfoDepositCalcLockParams depositCalcLockParams = new IfoDepositCalcLockParams();
            depositCalcLockParams.setServiceName(""+WEB3IfoDepositCalcResultSaveServiceImpl.class.getName());
            depositCalcLockParams.setThreadNo(2);
            depositCalcLockParams.setApHostName("LockThread");
            depositCalcLockParams.setStatus(WEB3IfoDepositCalcResultSaveDiv.LOCK_NONE);
            depositCalcLockParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            depositCalcLockParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(depositCalcLockParams);
            TestDBUtility.commit();
            assertTrue(l_serviceImpl.lockThread(2));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //1)�擾�ł��Ȃ��ꍇ�A�V�������b�N���R�[�h�ǉ��iThread_no = �X���b�hNo. �@@������ԁ@@= 1:�������jtrue��Ԃ�
    public void testLockThread_C0003()
    {
        final String STR_METHOD_NAME = "testLockThread_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoDepositCalcLockRow.TYPE);
            IfoDepositCalcLockParams depositCalcLockParams = new IfoDepositCalcLockParams();
            depositCalcLockParams.setServiceName(""+WEB3IfoDepositCalcResultSaveServiceImpl.class.getName());
            depositCalcLockParams.setThreadNo(3);
            depositCalcLockParams.setApHostName("LockThread");
            depositCalcLockParams.setStatus(WEB3IfoDepositCalcResultSaveDiv.LOCK_NONE);
            depositCalcLockParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            depositCalcLockParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(depositCalcLockParams);

            assertTrue(l_serviceImpl.lockThread(4));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //�J���������X���b�h������
    //���R�[�h������ꍇ�A�u���ғ��v�ɍX�V����
    public void testReleaseThread_C0001()
    {
        final String STR_METHOD_NAME = "testReleaseThread_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoDepositCalcLockRow.TYPE);
            IfoDepositCalcLockParams depositCalcLockParams = new IfoDepositCalcLockParams();
            depositCalcLockParams.setServiceName(""+WEB3IfoDepositCalcResultSaveServiceImpl.class.getName());
            depositCalcLockParams.setThreadNo(5);
            depositCalcLockParams.setApHostName("LockThread");
            depositCalcLockParams.setStatus(WEB3IfoDepositCalcResultSaveDiv.LOCK_NONE);
            depositCalcLockParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            depositCalcLockParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(depositCalcLockParams);

            l_serviceImpl.releaseThread(5);
            
            final String l_strWhere = "service_name = ? and thread_no = ?";
            final Object l_bindVars[] = { "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultSaveServiceImpl", new Long(5) };
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                IfoDepositCalcLockRow.TYPE,
                l_strWhere, l_bindVars);

            assertEquals(((IfoDepositCalcLockRow)l_lisRows.get(0)).getApHostName(), "NONE");
            assertEquals(((IfoDepositCalcLockRow)l_lisRows.get(0)).getStatus(), WEB3IfoDepositCalcResultSaveDiv.LOCK_NONE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //�X�V�ΏۃX���b�h�����擾�ł��Ȃ������ꍇ
    public void testReleaseThread_C0002() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "testReleaseThread_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoDepositCalcLockRow.TYPE);
            IfoDepositCalcLockParams depositCalcLockParams = new IfoDepositCalcLockParams();
            depositCalcLockParams.setServiceName(""+WEB3IfoDepositCalcResultSaveServiceImpl.class.getName());
            depositCalcLockParams.setThreadNo(6);
            depositCalcLockParams.setApHostName("LockThread");
            depositCalcLockParams.setStatus(WEB3IfoDepositCalcResultSaveDiv.LOCK_NONE);
            depositCalcLockParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            depositCalcLockParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(depositCalcLockParams);

            l_serviceImpl.releaseThread(7);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3IfoDepositCalcResultSaveServiceImplForTest extends WEB3IfoDepositCalcResultSaveServiceImpl
    {
        protected boolean lockThread(
                final long l_lngThreadNo) throws WEB3SystemLayerException
        {
            return true;
            
        }

        protected void releaseThread(final long l_lngThreadNo) throws WEB3SystemLayerException
        {
            
        }
    }
    private class WEB3IfoDepositCalcResultSaveServiceImplForTest2 extends WEB3IfoDepositCalcResultSaveServiceImpl
    {
        protected boolean lockThread(
                final long l_lngThreadNo) throws WEB3SystemLayerException
        {
            return false;
            
        }

        protected void releaseThread(final long l_lngThreadNo) throws WEB3SystemLayerException
        {
            
        }
    }
    private class WEB3IfoDepositCalcResultSaveServiceImplForTest3 extends WEB3IfoDepositCalcResultSaveServiceImpl
    {
        protected boolean lockThread(
                final long l_lngThreadNo) throws WEB3SystemLayerException
        {
            return true;
        }

        protected void releaseThread(final long l_lngThreadNo) throws WEB3SystemLayerException
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "releaseThread");
        }
    }
}
@
