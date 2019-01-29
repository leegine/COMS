head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqNettingExchangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.service.delegate.stdimpls;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManagerHelper;
import webbroker3.feq.data.FeqOrderexecutionEndParams;
import webbroker3.feq.data.FeqOrderexecutionEndRow;
import webbroker3.feq.message.WEB3FeqNettingExchangeRequest;
import webbroker3.feq.message.WEB3FeqNettingExchangeResponse;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqNettingExchangeServiceImpl;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqNettingExchangeServiceImplTest extends TestBaseForMock{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqNettingExchangeServiceImplTest.class);
    
    private WEB3FeqNettingExchangeServiceImpl l_impl = null;

    public WEB3FeqNettingExchangeServiceImplTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_impl = new WEB3FeqNettingExchangeServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * �O���o���I���e�[�u�����擾�ł��Ȃ��ꍇ�́A�V�X�e���G���[�h�Y���f�[�^�Ȃ��h��ԋp����B
     */
    public void testGetOrderBizDateCase001()
    {
        final String STR_METHOD_NAME = "testGetOrderBizDateCase001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {

            Method l_method = WEB3FeqNettingExchangeServiceImpl.class.getDeclaredMethod(
                    "getOrderBizDate",
                    new Class[]{String.class});

            l_method.setAccessible(true);
            Object[] l_obj = {new String("1")};
            String l_strReturnValue = (String) l_method.invoke(l_impl, l_obj);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug("exception =" + ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo().getErrorMessage());
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
    }
    
    
    /*
     *         //�P�j�@@�����P�ʌ���
        //[����]
        //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        // �������ʂ́A�ȉ��̍��ڂŏ����\�[�g���A�擾���邱�ƁB
         //�@@�O����{��
     */
    public void testGetOrderBizDateCase002()
    {
        final String STR_METHOD_NAME = "testGetOrderBizDateCase002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
       
            preGetDate();
            
            Method l_method = WEB3FeqNettingExchangeServiceImpl.class.getDeclaredMethod(
                    "getOrderBizDate",
                    new Class[]{String.class});

            l_method.setAccessible(true);
            Object[] l_obj = {new String("0D")};
            Date l_strReturnValue = (Date) l_method.invoke(l_impl, l_obj);

            assertEquals((WEB3DateUtility.getDate("20100906","yyyyMMdd")),l_strReturnValue);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
    }
    
    public void testGetNettingMainAccountListCase001()
    {
        try
        {
            final String STR_METHOD_NAME = "testGetNettingMainAccountListCase001()";
            log.entering(TEST_START + STR_METHOD_NAME);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "getNettingOrderUnit",
                    new Class[] {Long.class,String.class,Date.class},
                    null);
            
            Method l_method = WEB3FeqNettingExchangeServiceImpl.class.getDeclaredMethod(
                    "getNettingMainAccountList",
                    new Class[]{String.class,Date.class});

            l_method.setAccessible(true);
            Object[] l_obj = {new String("0D"),WEB3DateUtility.getDate("20100906","yyyyMMdd")};
            MainAccount[] l_strReturnValue = (MainAccount[]) l_method.invoke(l_impl, l_obj);
            
            assertEquals(null,l_strReturnValue);            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testGetNettingMainAccountListCase002()
    {
        try
        {
            preGetList();
            final String STR_METHOD_NAME = "testGetNettingMainAccountListCase002()";
            log.entering(TEST_START + STR_METHOD_NAME);

            WEB3FeqOrderUnit[] l_feqOrderUnits = new WEB3FeqOrderUnit[5];
            Constructor c = WEB3FeqOrderUnit.class.getDeclaredConstructor(new Class[]{long.class});
            c.setAccessible(true);
            
            l_feqOrderUnits[0] = (WEB3FeqOrderUnit)c.newInstance(new Object[]{new Long(1000)});
            l_feqOrderUnits[1] = (WEB3FeqOrderUnit)c.newInstance(new Object[]{new Long(1001)});
            l_feqOrderUnits[2] = (WEB3FeqOrderUnit)c.newInstance(new Object[]{new Long(1002)});
            l_feqOrderUnits[3] = (WEB3FeqOrderUnit)c.newInstance(new Object[]{new Long(1003)});
            l_feqOrderUnits[4] = (WEB3FeqOrderUnit)c.newInstance(new Object[]{new Long(1004)});

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "getNettingOrderUnit",
                    new Class[] {Long.class,String.class,Date.class},
                    l_feqOrderUnits);
            
            Method l_method = WEB3FeqNettingExchangeServiceImpl.class.getDeclaredMethod(
                    "getNettingMainAccountList",
                    new Class[]{String.class,Date.class});

            l_method.setAccessible(true);
            Object[] l_obj = {new String("0D"),WEB3DateUtility.getDate("20100906","yyyyMMdd")};
            MainAccount[] l_strReturnValue = (MainAccount[]) l_method.invoke(l_impl, l_obj);
            
            assertEquals(3,l_strReturnValue.length);
            assertEquals(3000,l_strReturnValue[0].getAccountId());
            assertEquals(3001,l_strReturnValue[1].getAccountId());
            assertEquals(3003,l_strReturnValue[2].getAccountId());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void preGetDate()  throws Exception
    {
        TestDBUtility.deleteAll(FeqOrderexecutionEndRow.TYPE);
        
        FeqOrderexecutionEndParams l_ifoOrderUnitParams = TestDBUtility.getFeqOrderexecutionEndRow();
        l_ifoOrderUnitParams.setLastExecuteDate((WEB3DateUtility.getDate("20100908","yyyyMMdd")));
        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

        l_ifoOrderUnitParams = TestDBUtility.getFeqOrderexecutionEndRow();
        l_ifoOrderUnitParams.setMarketCode("CJ");
        l_ifoOrderUnitParams.setLastExecuteDate((WEB3DateUtility.getDate("20100909","yyyyMMdd")));
        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        
        l_ifoOrderUnitParams = TestDBUtility.getFeqOrderexecutionEndRow();
        l_ifoOrderUnitParams.setMarketCode("BJ");
        l_ifoOrderUnitParams.setLastExecuteDate((WEB3DateUtility.getDate("20100906","yyyyMMdd")));
        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        
        
        l_ifoOrderUnitParams = TestDBUtility.getFeqOrderexecutionEndRow();
        l_ifoOrderUnitParams.setInstitutionCode("0E");
        l_ifoOrderUnitParams.setMarketCode("BJ");
        l_ifoOrderUnitParams.setLastExecuteDate((WEB3DateUtility.getDate("20100910","yyyyMMdd")));
        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        
        l_ifoOrderUnitParams = TestDBUtility.getFeqOrderexecutionEndRow();
        l_ifoOrderUnitParams.setInstitutionCode("0E");
        l_ifoOrderUnitParams.setMarketCode("SP");
        l_ifoOrderUnitParams.setLastExecuteDate((WEB3DateUtility.getDate("20100903","yyyyMMdd")));
        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
    }
    public void preGetList() throws Exception
    {
        TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
        
        FeqOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
        l_ifoOrderUnitParams.setOrderUnitId(1000l);
        l_ifoOrderUnitParams.setAccountId(3000l);
        l_ifoOrderUnitParams.setOrderEmpCode("1");
        l_ifoOrderUnitParams.setOrderRequestNumber("100");
        l_ifoOrderUnitParams.setBranchId(10);
        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        
        FeqOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
        l_ifoOrderUnitParams1.setOrderUnitId(1001l);
        l_ifoOrderUnitParams1.setAccountId(3001l);
        l_ifoOrderUnitParams1.setOrderEmpCode("2");
        l_ifoOrderUnitParams1.setOrderRequestNumber("200");
        l_ifoOrderUnitParams1.setBranchId(11);
        TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);

        
        FeqOrderUnitParams l_ifoOrderUnitParams2 = TestDBUtility.getFeqOrderUnitRow();
        l_ifoOrderUnitParams2.setOrderUnitId(1002l);
        l_ifoOrderUnitParams2.setAccountId(3000l);
        l_ifoOrderUnitParams2.setOrderEmpCode("3");
        l_ifoOrderUnitParams2.setOrderRequestNumber("300");
        l_ifoOrderUnitParams2.setBranchId(12);
        TestDBUtility.insertWithDel(l_ifoOrderUnitParams2);
        
        FeqOrderUnitParams l_ifoOrderUnitParams3 = TestDBUtility.getFeqOrderUnitRow();
        l_ifoOrderUnitParams3 = TestDBUtility.getFeqOrderUnitRow();
        l_ifoOrderUnitParams3.setOrderUnitId(1003l);
        l_ifoOrderUnitParams3.setAccountId(3001l);
        l_ifoOrderUnitParams3.setOrderEmpCode("4");
        l_ifoOrderUnitParams3.setOrderRequestNumber("400");
        l_ifoOrderUnitParams3.setBranchId(13);
        TestDBUtility.insertWithDel(l_ifoOrderUnitParams3);
        
        FeqOrderUnitParams l_ifoOrderUnitParams4 = TestDBUtility.getFeqOrderUnitRow();
        l_ifoOrderUnitParams4 = TestDBUtility.getFeqOrderUnitRow();
        l_ifoOrderUnitParams4.setOrderUnitId(1004l);
        l_ifoOrderUnitParams4.setAccountId(3003l);
        l_ifoOrderUnitParams4.setOrderEmpCode("5");
        l_ifoOrderUnitParams4.setOrderRequestNumber("500");
        l_ifoOrderUnitParams4.setBranchId(14);
        TestDBUtility.insertWithDel(l_ifoOrderUnitParams4);
        
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        MainAccountParams l_mainAccountParam = TestDBUtility.getMainAccountRow();
        l_mainAccountParam.setAccountId(3000);
        l_mainAccountParam.setAccountCode("cj1");
        TestDBUtility.insertWithDel(l_mainAccountParam);
        
         l_mainAccountParam = TestDBUtility.getMainAccountRow();
        l_mainAccountParam.setAccountId(3001);
        l_mainAccountParam.setAccountCode("cj2");
        TestDBUtility.insertWithDel(l_mainAccountParam);
        
        l_mainAccountParam = TestDBUtility.getMainAccountRow();
        l_mainAccountParam.setAccountId(3003);
        l_mainAccountParam.setAccountCode("cj3");
        TestDBUtility.insertWithDel(l_mainAccountParam);
        
        TestDBUtility.deleteAll(BranchRow.TYPE);
        BranchParams l_branchParam = TestDBUtility.getBranchRow();
        TestDBUtility.insertWithDel(l_branchParam);

    }
    
    public void testExcuteCase001()
    {
        try
        {
            WEB3FeqNettingExchangeRequest request = new WEB3FeqNettingExchangeRequest();
            request.institutionCode = "0D";
            this.preGetDate();
            this.preGetList();
            
            WEB3FeqOrderUnit[] l_feqOrderUnits = new WEB3FeqOrderUnit[5];
            Constructor c = WEB3FeqOrderUnit.class.getDeclaredConstructor(new Class[]{long.class});
            c.setAccessible(true);
            
            l_feqOrderUnits[0] = (WEB3FeqOrderUnit)c.newInstance(new Object[]{new Long(1000)});
            l_feqOrderUnits[1] = (WEB3FeqOrderUnit)c.newInstance(new Object[]{new Long(1001)});
            l_feqOrderUnits[2] = (WEB3FeqOrderUnit)c.newInstance(new Object[]{new Long(1002)});
            l_feqOrderUnits[3] = (WEB3FeqOrderUnit)c.newInstance(new Object[]{new Long(1003)});
            l_feqOrderUnits[4] = (WEB3FeqOrderUnit)c.newInstance(new Object[]{new Long(1004)});

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqOrderManager",
                    "getNettingOrderUnit",
                    new Class[] {Long.class,String.class,Date.class},
                    l_feqOrderUnits);
            TestDBUtility.deleteAll(LoginUsernameRow.TYPE);
            
            WEB3FeqNettingExchangeServiceImplMock m = new 
            WEB3FeqNettingExchangeServiceImplMock();
            m.execute(request);
            
        }

        catch(Exception e)
        {
            fail();
        }
        
    }

}
 class WEB3FeqNettingExchangeServiceImplMock extends WEB3FeqNettingExchangeServiceImpl
 {
     /**
      * ���O�o�̓��[�e�B���e�B�B<BR>
      */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqNettingExchangeServiceImpl.class);

     /**
      * @@roseuid 42CE39F5007D
      */
     public WEB3FeqNettingExchangeServiceImplMock()
     {

     }

     /**
      * (execute)<BR>
      * �בփl�b�e�B���O�������s���B<BR>
      * <BR>
      * �V�[�P���X�}<BR>
      * �u�i�O�������בփl�b�e�B���O�T�[�r�X�j�בփl�b�e�B���O�����v�Q�ƁB<BR>
      * @@param l_web3BackRequest - (���N�G�X�g�f�[�^)<BR>
      * ���N�G�X�g�f�[�^�I�u�W�F�N�g
      * @@return WEB3BackResponse
      * @@throws WEB3BaseException
      * @@roseuid 42B8A39B0394
      */
     public WEB3BackResponse  execute(WEB3BackRequest  l_web3BackRequest)
         throws WEB3BaseException
     {
         final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
         log.entering(STR_METHOD_NAME);

         if (l_web3BackRequest == null)
         {
             log.debug("�p�����[�^�l��NULL");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
         }
         else
         {
             if (l_web3BackRequest instanceof WEB3FeqNettingExchangeRequest)
             {
                 WEB3FeqNettingExchangeRequest l_feqNettingExchangeRequest =
                     (WEB3FeqNettingExchangeRequest)l_web3BackRequest;

                 //�،���ЃR�[�h
                 String l_strInstitutionCode = l_feqNettingExchangeRequest.institutionCode;

                 //���������擾����B
                 //�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h
                 Date l_datOrderBizDate = this.getOrderBizDate(l_strInstitutionCode);

                 //�l�b�e�B�������Ώیڋq�ꗗ���擾����B
                 //�،���ЃR�[�h�F ���N�G�X�g.�،���ЃR�[�h
                 //�������F�@@get������()�̖߂�l
                 MainAccount[] l_mainAccounts = this.getNettingMainAccountList(
                     l_strInstitutionCode,
                     l_datOrderBizDate);

                 //�����敪
                 String l_strStatus = WEB3StatusDef.DEALT;

                 QueryProcessor l_queryProcessor = null;
                 try
                 {
                     l_queryProcessor =  l_queryProcessor = Processors.getDefaultProcessor();
                 }
                 catch (DataFindException l_ex)
                 {
                     log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                     throw new WEB3SystemLayerException(
                          WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                          this.getClass().getName() + STR_METHOD_NAME);
                 }
                 catch (DataNetworkException l_ex)
                 {
                     log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                     throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                           this.getClass().getName() + STR_METHOD_NAME);
                 }

                 //get�����Ώیڋq�ꗗ()�̗v�f����Loop����
                 if (l_mainAccounts != null)
                 {
                     FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                     //�g���A�J�E���g�}�l�[�W�����擾����B
                     WEB3GentradeAccountManager l_accountManager =
                         (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                     //�O�����������}�l�[�W�����擾����B
                     TradingModule l_tradingModule =
                         l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
                     WEB3FeqOrderManager l_feqOrderManager =
                         (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

                     l_strInstitutionCode = null;

                     //���X�R�[�h
                     String l_strBranchCode = null;

                     //�����R�[�h
                     String l_strAccountCode = null;

                     for (int i = 0; i < l_mainAccounts.length; i++)
                     {
                         //���������b�N����B
                         //�،���ЃR�[�h�F�@@�ڋq.�،���ЃR�[�h
                         //���X�R�[�h�F�@@�ڋq.���X�R�[�h
                         //�����R�[�h�F�@@�ڋq.�����R�[�h
                         l_strInstitutionCode =  l_mainAccounts[i].getInstitution().getInstitutionCode();
                         l_strBranchCode = l_mainAccounts[i].getBranch().getBranchCode();
                         l_strAccountCode =  l_mainAccounts[i].getAccountCode();

                         l_accountManager.lockAccount(
                         l_strInstitutionCode,
                         l_strBranchCode,
                         l_strAccountCode);

                         //�l�b�e�B���ΏۂƂȂ钍���P�ʂ��擾����B
                         //����ID�F�@@�����Ώۂ̗v�f.�ڋq.����ID
                         //�،���ЃR�[�h�F�@@�p�����[�^.�ڋq.�،���ЃR�[�h
                         //�������F�@@get������()�̖߂�l
                         long l_strAccountId = l_mainAccounts[i].getAccountId();

                         WEB3FeqOrderUnit[] l_orderUnits = l_feqOrderManager.getNettingOrderUnit(
                             new Long(l_strAccountId),
                             l_strInstitutionCode,
                             l_datOrderBizDate);

                         //�����P��ID�ꗗ�Fget�l�b�e�B���Ώے����P��()�̖߂�l��蒊�o���������P��ID�̈ꗗ
                         long[] l_lngOrderUnitIdList = null;

                         int l_intCnt = 0;

                         if (l_orderUnits != null && l_orderUnits.length > 0)
                         {
                             l_intCnt = l_orderUnits.length;
                             int l_intCnt2 = 0;

                             for (int j = 0; j < l_intCnt; j++)
                             {
                                 WEB3FeqOrderUnit l_orderUnit = l_orderUnits[j];

                                 if (l_orderUnit != null)
                                 {
                                     l_intCnt2++;
                                 }
                             }
                             if (l_intCnt2 > 0)
                             {
                                 l_lngOrderUnitIdList = new long[l_intCnt2];
                                 int l_intNo = 0;

                                 for (int k = 0; k < l_intCnt; k++)
                                 {
                                     WEB3FeqOrderUnit l_orderUnit = l_orderUnits[k];

                                     if (l_orderUnit != null)
                                     {
                                         l_lngOrderUnitIdList[l_intNo] = l_orderUnit.getOrderUnitId(); 
                                         l_intNo++;
                                     }
                                 }
                             }
                         }
                         try
                         {
                             //update�g�����U�N�V����TransactionCallback
                             WEB3FEQNettingUpdateTransactionCallback l_callBack =
                                 new WEB3FEQNettingUpdateTransactionCallback();

                             l_callBack.setOrderUnitIdList(l_lngOrderUnitIdList);
                             l_callBack.setOrderBizDate(l_datOrderBizDate);

                             l_queryProcessor.doTransaction(TransactionalInterceptor.TX_CREATE_NEW,l_callBack);
                         }
                         catch(Exception l_ex)
                         {
                             l_strStatus = WEB3StatusDef.DATA_ERROR;
                             continue;
                         }
                     }
                 }

                 //�،���Ђ��擾����B
                 l_strInstitutionCode = l_feqNettingExchangeRequest.institutionCode;
                 WEB3GentradeInstitution l_institution = null;
                 try
                 {
                     l_institution = new WEB3GentradeInstitution(l_strInstitutionCode);
                 }
                 catch (DataNetworkException l_ex)
                 {
                     log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���" , l_ex);
                     log.exiting(STR_METHOD_NAME);
                     throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                         this.getClass().getName() + "." + STR_METHOD_NAME,
                         l_ex.getMessage(),
                         l_ex);
                 }
                 catch (DataQueryException l_ex)
                 {
                     log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���" , l_ex);
                     log.exiting(STR_METHOD_NAME);
                     throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                         this.getClass().getName() + "." + STR_METHOD_NAME,
                         l_ex.getMessage(),
                         l_ex);
                 }

                 //�،���ЃR�[�h�̑Ή��̑S�Ă̕��X���擾����B
                 Branch[] l_branches = l_institution.getBranches();
                 //�擾�����������ʐ���LOOP����
                 //�v���Z�X�Ǘ�Params�𐶐�����B
                 ProcessManagementParams l_processManagementParams =
                     new ProcessManagementParams();

                 //�v���Z�X�h�c:'0014'
                 //TODO
                 l_processManagementParams.setProcessId("0014");

                 //���͂̏،���ЃR�[�h���Z�b�g�B
                 l_processManagementParams.setInstitutionCode(
                     l_strInstitutionCode);

                 //�����敪:
                 l_processManagementParams.setStatus(l_strStatus);

                 //�ŏI�X�V��:null
                 l_processManagementParams.setLastUpdater(null);

                 for (int i = 0; i < l_branches.length; i++)
                 {
                     //���X�R�[�h:�،����.���X�R�[�h[i]
                     l_processManagementParams.setBranchCode(l_branches[i].getBranchCode());

                     //�ŏI�X�V����:"���ݓ���(GtlUtils.getSystemTimestamp())"
                     l_processManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                     try
                     {
                         l_queryProcessor.doInsertQuery(l_processManagementParams);
                     }
                     catch (DataQueryException l_ex)
                     {
                         log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                         log.exiting(STR_METHOD_NAME);

                         throw new WEB3SystemLayerException(
                             WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                             this.getClass().getName() + "." + STR_METHOD_NAME,
                             l_ex.getMessage(),
                             l_ex);
                     }
                     catch (DataNetworkException l_ex)
                     {
                         log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                         log.exiting(STR_METHOD_NAME);
                         throw new WEB3SystemLayerException(
                             WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                             this.getClass().getName() + "." + STR_METHOD_NAME,
                             l_ex.getMessage(),
                             l_ex);
                     }
                 }

                 WEB3FeqNettingExchangeResponse l_feqNettingExchangeResponse =
                     (WEB3FeqNettingExchangeResponse)l_web3BackRequest.createResponse();

                 log.exiting(STR_METHOD_NAME);
                 return l_feqNettingExchangeResponse;
             }
             else
             {
                 log.debug("�p�����[�^�^�C�v�s��");
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                     this.getClass().getName() + "." + STR_METHOD_NAME);
             }
         }
     }

     /**
      * (get�l�b�e�B�������Ώیڋq�ꗗ)<BR>
      * �l�b�e�B�������ΏۂƂȂ钍����ێ�����B<BR>
      * �ڋq�̈ꗗ��ԋp����B<BR>
      * <BR>
      * �P�j�@@�����P�ʌ���<BR>
      * �O�����������}�l�[�W��.get�l�b�e�B���Ώے����P��()���R�[������B<BR>
      * <BR>
      * [get�l�b�e�B���Ώے����P��()�Ɏw�肷�����]<BR>
      * ����ID�F�@@null<BR>
      * �،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
      * �������F�@@get������()�̖߂�l<BR>
      * ��null���ԋp���ꂽ�ꍇ�Anull��ԋp����B<BR>
      * <BR>
      * �Q�j�@@�ڋq�I�u�W�F�N�g�쐬<BR>
      * �Q�|�P�j�P�j�̌������ʂɂ��āA���j�[�N�Ȍ���ID�̈ꗗ���쐬����B<BR>
      * �Q�|�Q�j�Q�|�P�j�ɂč쐬��������ID�̈ꗗ���A�ڋq�I�u�W�F�N�g���쐬���A<BR>
      * �z��Ƃ��ĕԋp����B<BR>
      * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
      * �،���ЃR�[�h�I�u�W�F�N�g<BR>
      * @@param l_datOrderBizDate - (������)<BR>
      * �������I�u�W�F�N�g<BR>
      * @@return MainAccount[]
      * @@throws WEB3BaseException
      */
     private MainAccount[] getNettingMainAccountList(String l_strInstitutionCode,
         Date l_datOrderBizDate) throws WEB3BaseException
     {
         final String STR_METHOD_NAME = "getNettingMainAccountList(String, Date)";
         log.entering(STR_METHOD_NAME);

         //�P�j�@@�����P�ʌ���
         //�O�����������}�l�[�W��.get�l�b�e�B���Ώے����P��()���R�[������B
         FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
         TradingModule l_tradingModule =
             l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

         WEB3FeqOrderManager l_feqOrderManager =
             (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

         //[get�l�b�e�B���Ώے����P��()�Ɏw�肷�����]
         //����ID�F�@@null
         //�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h
         //�������F�@@get������()�̖߂�l
         WEB3FeqOrderUnit[] l_feqOrderUnits = l_feqOrderManager.getNettingOrderUnit(
             null,
             l_strInstitutionCode,
             this.getOrderBizDate(l_strInstitutionCode));

         //��null���ԋp���ꂽ�ꍇ�Anull��ԋp����B
         if (l_feqOrderUnits == null)
         {
             log.exiting(STR_METHOD_NAME);
             return null;
         }
         //�Q�j�@@�ڋq�I�u�W�F�N�g�쐬
         else
         {
             //�Q�|�P�j�P�j�̌������ʂɂ��āA���j�[�N�Ȍ���ID�̈ꗗ���쐬����B
             //�Q�|�Q�j�Q�|�P�j�ɂč쐬��������ID�̈ꗗ���A�ڋq�I�u�W�F�N�g���쐬���A�z��Ƃ��ĕԋp����B
             WEB3GentradeMainAccount[] l_mainAccounts= null;
             List l_lisMainAccounts = new ArrayList();
             WEB3GentradeAccountManager l_accMgr =
                 (WEB3GentradeAccountManager)l_finApp.getAccountManager();
             int l_intCnt = l_feqOrderUnits.length;

             List l_lisAccountId = new ArrayList();

             for (int i = 0; i < l_intCnt; i++)
             {
                 WEB3FeqOrderUnit l_orderUnit = l_feqOrderUnits[i];

                 if (l_orderUnit != null)
                 {
                     Long l_accountId = new Long(l_orderUnit.getAccountId());

                     if (!l_lisAccountId.contains(l_accountId))
                     {
                         l_lisAccountId.add(l_accountId);
                     }
                 }
             }

             int l_intAccountCnt = l_lisAccountId.size();

             try
             {
                 for (int i = 0; i < l_intAccountCnt; i++)
                 {
                     Long l_accountId = (Long)l_lisAccountId.get(i);

                     WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)
                         l_accMgr.getMainAccount(l_accountId.longValue());//NotFoundException
                     l_lisMainAccounts.add(l_mainAccount);
                 }
             }
             catch (NotFoundException l_ex)
             {
                 log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                 log.exiting(STR_METHOD_NAME);

                 throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_ex.getMessage(),
                     l_ex);
             }

             if (!l_lisMainAccounts.isEmpty())
             {
                 l_mainAccounts = new WEB3GentradeMainAccount[l_lisMainAccounts.size()];
                 l_lisMainAccounts.toArray(l_mainAccounts);
             }
             log.exiting(STR_METHOD_NAME);   
             return l_mainAccounts;
         }
     }

     /**
      * (get�������j<BR>
      * <BR>
      * �P�j�@@�����P�ʌ���<BR>
      * �ȉ��̏����ɂĊO���o���I���e�[�u������������B<BR>
      * �@@[����]<BR>
      * �،���ЃR�[�h�@@=�@@�p�����[�^.�،���ЃR�[�h<BR>
      * <BR>
      * �������ʂ́A�ȉ��̍��ڂŏ����\�[�g���A�擾���邱�ƁB<BR>
      * �@@�@@�@@�O����{��<BR>
      * <BR>
      * �@@���O���o���I���e�[�u�����擾�ł��Ȃ��ꍇ�́A�V�X�e���G���[�h�Y���f�[�^�Ȃ��h��ԋp����B<BR>
      * <BR>
      * �Q�j�������擾<BR>
      * �@@�O���o���I��List(0)�D�O����{����ԋp����B<BR>
      * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
      * �،���ЃR�[�h�I�u�W�F�N�g<BR>
      * @@return Date
      * @@throws WEB3BaseException
      */
     private Date getOrderBizDate(String l_strInstitutionCode) throws WEB3BaseException
     {
         final String STR_METHOD_NAME = "getOrderBizDate(String)";
         log.entering(STR_METHOD_NAME);

         //�P�j�@@�����P�ʌ���
         //[����]
         //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
         // �������ʂ́A�ȉ��̍��ڂŏ����\�[�g���A�擾���邱�ƁB
          //�@@�O����{��
         StringBuffer l_sbWhere = new StringBuffer();
         l_sbWhere.append(" institution_code = ? ");
         // �\�[�g����
         String l_strOrderBy = " last_execute_date asc  ";

         List l_lisRecords = null;

         try
         {
             QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
             l_lisRecords = l_queryProcessor.doFindAllQuery(
                 FeqOrderexecutionEndRow.TYPE,
                 l_sbWhere.toString(),
                 l_strOrderBy,
                 null,
                 new Object[]{l_strInstitutionCode}
                 );
         }
         catch (DataQueryException l_ex)
         {
             log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
             log.exiting(STR_METHOD_NAME);

             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }
         catch (DataNetworkException l_ex)
         {
             log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
             log.exiting(STR_METHOD_NAME);

             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }

         //���O���o���I���e�[�u�����擾�ł��Ȃ��ꍇ�́A�V�X�e���G���[�h�Y���f�[�^�Ȃ��h��ԋp����B
         if (l_lisRecords.size() == 0)
         {
             log.error("�Y���f�[�^�Ȃ�");
             log.exiting(STR_METHOD_NAME);

             throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
         }
         //�O���o���I��List(0)�D�O����{����ԋp����B
         else
         {
             FeqOrderexecutionEndRow l_feqOrderexecutionEnd = (FeqOrderexecutionEndRow)l_lisRecords.get(0);

             log.exiting(STR_METHOD_NAME);
             return l_feqOrderexecutionEnd.getLastExecuteDate();
         }
     }

     /**
      * update�g�����U�N�V����TransactionCallback<BR>
      * <BR>
      * �g�����U�N�V�������������{��������N���X�B<BR>
      */
     public class WEB3FEQNettingUpdateTransactionCallback implements TransactionCallback
     {
         /**
          *�����P�ʂh�c�ꗗ<BR>
          */
         private long[] l_lngOrderUnitIdLists;

         /**
          *���<BR>
          */
         private Date l_datOrderBizDate;

         /**
          * (set�����P�ʂh�c�ꗗ)<BR>
          * <BR>
          * �����̒����P�ʂh�c�ꗗ���v���p�e�B�ɃZ�b�g����B<BR>
          * @@params long[] - �����P�ʂh�c�ꗗ<BR>
          */
         public void setOrderUnitIdList(long[] l_lngOrderUnitIdLists)
         {
             this.l_lngOrderUnitIdLists = l_lngOrderUnitIdLists;
         }

         /**
          * (set���)<BR>
          * <BR>
          * �����̊�����v���p�e�B�ɃZ�b�g����B<BR>
          * @@params Date - ���<BR>
          */
         public void setOrderBizDate(Date l_datOrderBizDate)
         {
             this.l_datOrderBizDate = l_datOrderBizDate;
         }

         /**
          * �f�t�H���g�R���X�g���N�^
          * @@return WEB3FeqNettingExchangeServiceImpl.WEB3FEQNettingUpdateTransactionCallback
          * @@roseuid 4088F56A0131
          */
         public WEB3FEQNettingUpdateTransactionCallback()
         {

         }

         /**
          * �l�b�e�B���O�בւƂȂ�g�����U�N�V�����̋��z���Čv�Z����B<BR>
          * <BR>
          * <BR>
          * @@return Object
          * @@roseuid 4088F56A0122
          */
         public Object process()
         {
             final String STR_METHOD_NAME = "process()";
             log.entering(STR_METHOD_NAME);

             //�O�������|�W�V�����w���p�[���擾����B
             WEB3FeqPositionManagerHelperMock l_feqPositionManagerHelper =
                 new WEB3FeqPositionManagerHelperMock();

             try
             {
                 //update�g�����U�N�V�����i�l�b�e�B���O�̗p�j(�����P�ʂh�c�ꗗ : long[], ��� : Date)
                 l_feqPositionManagerHelper.updateTransactionNettingAdoption(l_lngOrderUnitIdLists, l_datOrderBizDate);
             }
             catch(Exception l_ex)
             {
                 log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     l_ex.getMessage(),
                     l_ex);
             }

             log.exiting(STR_METHOD_NAME);
             return null;
         }
     }
 }
 
 class WEB3FeqPositionManagerHelperMock
 {
     public void updateTransactionNettingAdoption(long[] ids, Date orderDate)
     {
         try
         {
             LoginUsernameParams lp = TestDBUtility.getLoginUsernameRow();
             lp.setLoginId(ids[0]);
             lp.setUsername(ids[0]+"");
             TestDBUtility.insertWithDel(lp);
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }

         
     }
 }
@
