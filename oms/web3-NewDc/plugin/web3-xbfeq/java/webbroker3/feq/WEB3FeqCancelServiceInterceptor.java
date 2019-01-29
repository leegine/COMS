head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O����������T�[�r�X�C���^�Z�v�^(WEB3FeqCancelServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬 
                 : 2005/08/03 �A�C��(���u) ���r���[     
Revesion History : 2008/02/01 �g�C�� (���u) ���f�� No.389
                 : 2008/07/18 ���(SRA) ���f��No�F451 �Ή�
                 : 2008/07/23 ����(SRA) ���f��No�F455 ����No:016,017 �Ή�
                 : 2008/07/29 ����(SRA) ���f��No�F457,460 ����No:020,022 �Ή�
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.message.WEB3FeqCancelCompleteRequest;
import webbroker3.feq.message.WEB3FeqCancelConfirmRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�O����������T�[�r�X�C���^�Z�v�^)<BR>
 * �O����������T�[�r�X�C���^�Z�v�^�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqCancelServiceInterceptor implements Interceptor 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCancelServiceInterceptor.class);
    
    /**
     * @@roseuid 42D0D1FF0128
     */
    public WEB3FeqCancelServiceInterceptor() 
    {
     
    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     *   �|OpLoginSecurityService��<BR>
     * �@@�@@���e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     *   ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     *   ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     *   ����J�����_�R���e�L�X�g.�s��R�[�h = (*) <BR>
     *   ����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     *   ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�O�������i����j�h<BR>
     *   ����J�����_�R���e�L�X�g.������t���i = �h�O�����h<BR>
     *   ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h����h<BR>
     * <BR>
     *   �|ThreadLocalSystemAttributesRegistry.setAttribute( )��<BR>
     * �Ď�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *      �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     *  (*)�s��R�[�h�̃Z�b�g���@@ <BR>
     *     a) ���N�G�X�g�f�[�^.����ID���璍���P�ʃI�u�W�F�N�g���擾����B <BR>
     *     b) ����J�����_�R���e�L�X�g.�s��R�[�h = �����P��.�s��ID����擾�����s��R�[�h <BR>
     * <BR>
     * �Q�j��t�����A���t���[�����Z�b�g����B<BR>
     *   �|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �R�j���������b�N����B<BR>
     *    ���N�G�X�g�f�[�^�̌^ == �h�O����������������N�G�X�g�h �̏ꍇ�A���{����B<BR>
     * <BR>
     *    �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)��<BR>
     * �@@�@@�R�[������B<BR>
     *    ��������OpLoginSecurityService���ҏW�B<BR>
     * <BR>
     * �S�j�����L���[�����b�N����B<BR>
     * �@@���N�G�X�g�f�[�^�̌^==�h�O����������������N�G�X�g�h�@@����<BR> 
     * �@@�����P��.get�s��.is�V�X�e���A���@@== true ����<BR>
     * �@@Double.isNaN(�����P��.getConfirmedQuantity()) == true�@@�̏ꍇ�݈̂ȉ����������{����B<BR>
     * <BR> 
     *�@@�S�|�P�j�O�����������L���[TransactionCallback�𐶐�����<BR>
     *�@@�@@�@@�@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     *�@@�@@�@@�@@�@@�����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     *�@@�S�|�Q�j�g�����U�N�V���������s����<BR>
     *�@@�@@�@@�@@�@@�i�g�����U�N�V���������F TX_JOIN_EXISTING�j<BR>
     * <BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 429ADF3C0352
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);         
        }
        
        //�P�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 
        //�|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B 
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(
            OpLoginSecurityService.class);
        
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();            
                
        long l_lngAccountId = l_opLoginSecurityService.getAccountId();
        
        try
        {
            MainAccount l_mainAccount = 
                l_accountManager.getMainAccount(l_lngAccountId);
            
            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();
            
            //���X�R�[�h���擾����
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            //�����R�[�h
            String l_accountCode = l_mainAccount.getAccountCode();
            
            //(*)�s��R�[�h�̃Z�b�g���@@ 
            //a) ���N�G�X�g�f�[�^.����ID���璍���P�ʃI�u�W�F�N�g���擾����B 
            //b) ����J�����_�R���e�L�X�g.�s��R�[�h = �����P��.�s��ID����擾�����s��R�[�h 

            WEB3GentradeMarket l_market = null;            
            long l_lngOrderId = 0;
            
            if (l_serviceParams[0] instanceof WEB3FeqCancelConfirmRequest)
            {
                WEB3FeqCancelConfirmRequest l_confirmRequest = 
                    (WEB3FeqCancelConfirmRequest)l_serviceParams[0];
                
                l_lngOrderId = Long.parseLong(l_confirmRequest.orderId);
            }
            else if (l_serviceParams[0] instanceof WEB3FeqCancelCompleteRequest)
            {
                WEB3FeqCancelCompleteRequest l_completeRequest = 
                    (WEB3FeqCancelCompleteRequest)l_serviceParams[0];
                
                l_lngOrderId = Long.parseLong(l_completeRequest.orderId);
            }
            
            FeqOrderUnit l_feqOrderUnit = 
                l_feqOrderManager.getOrderUnitByOrderId(l_lngOrderId);

            FeqOrderUnitRow l_feqOrderUnitRow = 
                    (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();            

            l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                l_feqOrderUnitRow.getMarketId());
            
            //����J�����_�R���e�L�X�g.set�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.set���X�R�[�h
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h = (*)  
            l_context.setMarketCode(l_market.getMarketCode());
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�O�������i����j�h 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FEQ_CANCEL);
            //����J�����_�R���e�L�X�g.������t���i = �h�O�����h 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�
            //������ԃR���e�L�X�g���Z�b�g���� 
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //�Q�j��t�����A���t���[�����Z�b�g����
            //������ԊǗ�.setTimestamp()���R�[������  
            WEB3GentradeTradingTimeManagement.setTimestamp();   
            
            //�R�j���������b�N����B 
            //���N�G�X�g�f�[�^�̌^ == �h�O����������������N�G�X�g�h �̏ꍇ�A���{����B 
            //�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
            //��������OpLoginSecurityService���ҏW�B 
            if (l_serviceParams[0] instanceof WEB3FeqCancelCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_accountCode);
            
                //�S�j�����L���[�����b�N����B
                //�����P��.get�s��.is�V�X�e���A���@@== true ���� 
                //Double.isNaN(�����P��.getConfirmedQuantity()) == true�@@�̏ꍇ�̂ݎ��{����B
                //�S�|�P�j�O�����������L���[TransactionCallback�𐶐�����
                if (l_market.isSystemInterLock() == true &&
                    Double.isNaN(l_feqOrderUnit.getConfirmedQuantity()) == true)
                {
                    WEB3FeqOrderQueueTransactionCallback l_transactionCallback =
                        new WEB3FeqOrderQueueTransactionCallback(l_feqOrderUnit);

                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                        l_processor.doTransaction(
                            QueryProcessor.TX_JOIN_EXISTING,
                            l_transactionCallback);
                }
            }
        }
        catch (DataException l_ex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getMainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp or lockAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return null;
    }
    
    /**
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * �T�[�r�X�I�����ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 429ADF3C0371
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);        

        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * ��O�������ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 429ADF3C0391
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);  
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
