head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCancelOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨��������T�[�r�X�C���^�Z�v�^(WEB3FuturesCancelOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 Ḗ@@�� (���u) �V�K�쐬
Revesion History : 2007/01/29 �����q (���u) �d�l�ύX�@@���f��NO.625
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.message.WEB3FuturesCancelCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmRequest;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;


/**
 * (�敨��������T�[�r�X�C���^�Z�v�^)<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesCancelOrderServiceInterceptor implements Interceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesCancelOrderServiceInterceptor.class);    
    /**
     * @@roseuid 40F7B0E00280
     */
    public WEB3FuturesCancelOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^���u�����w���I�v�V��������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR> 
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
     * ��������OpLoginSecurityService���ҏW�B<BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e<BR>��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h12�F�����w���敨OP�i��������j�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h06�F����h<BR>
     * <BR>
     *  (*1) �����Y�����R�[�h�̎擾���@@<BR>
     * �@@���N�G�X�g�f�[�^.�h�c�ɊY�����钍���I�u�W�F�N�g�𐶐�����B<BR>
     * �@@����.getOrderUnits()[0].getProductId()�ɂĖ����h�c���擾����B<BR>
     * �@@�����h�c�ɊY������敨OP�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������敨OP����.get�����Y�����R�[�h()�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �S�j�@@��������x�e���ԑт̏ꍇ(*2)�̂݁A�L���[�e�[�u���̋��L���b�N���s���B<BR>
     * <BR>
     * �@@�@@�敨OP�����T�[�r�X.lock�敨OP��������L���[(����Ώے����P��(*3))���R�[������B<BR>
     * <BR>
     * �@@�@@(*2)��������x�e���ԑт̏ꍇ<BR>
     * �@@�@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ�́A�x�e���ԑтł���Ɣ��肷��B<BR>
     * <BR>
     * �@@�@@(*3)����Ώے����P��<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g.ID�ɊY�����钍��ID���������P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�@@���������݂���ꍇ�́AgetOrderUnits()�̍ŏ��̗v�f���g�p����B<BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 40A81C72033E
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String METHOD_NAME = "onCall(Method,Object[])";
        log.entering(METHOD_NAME);

        try
        {    
            long l_lngOrderId   = 0;
            long l_lngProductId = 0;
            long l_lngAccountId = 0;
            
            //���N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g������擾����
            if(l_serviceParam == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);                           
            }

            if(l_serviceParam.length ==0)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);                
            }

            Object l_request = l_serviceParam[0];
            if (l_request instanceof WEB3FuturesCancelConfirmRequest)
            {
                WEB3FuturesCancelConfirmRequest  l_cancelRequest =
                    (WEB3FuturesCancelConfirmRequest) l_request;
                l_lngOrderId = Long.parseLong(l_cancelRequest.id);
            }
            else if (l_request instanceof WEB3FuturesCancelCompleteRequest)
            {
                WEB3FuturesCancelCompleteRequest  l_cancelRequest =
                    (WEB3FuturesCancelCompleteRequest) l_request;
                l_lngOrderId = Long.parseLong(l_cancelRequest.id);
            }        
            else
            {
                log.error("�p�����[�^�^�C�v�s���B");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + METHOD_NAME);                       
            }
            
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                    OpLoginSecurityService.class);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            //�����}�l�[�W�����擾����
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();   
            l_lngAccountId = l_opLoginSec.getAccountId();

            //�������擾����
            MainAccount l_mainAccount = l_accountMananger.getMainAccount(l_lngAccountId);

            //�،���ЃR�[�h                
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();   
            log.debug("�،���ЃR�[�h" + l_strInstitutionCode);                                           

            //���X�R�[�h
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            log.debug("���X�R�[�h" + l_strBranchCode);

            // �P�j�@@���N�G�X�g�f�[�^�̌^���u�����w���I�v�V��������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR> 
            // �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
            // ��������OpLoginSecurityService���ҏW�B<BR>
            if (l_request instanceof WEB3FuturesCancelCompleteRequest)
            {
                    l_accountMananger.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_mainAccount.getAccountCode());
            }

            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);  
            
            //�����}�l�[�W�����擾����      
            WEB3FuturesOrderManagerImpl l_orderManager = 
                (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();                
                
            //�����h�c���w�肵�����P�ʎ擾            
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);    
            OrderUnit l_orderUnit = null;
            if (l_orderUnits.length==0)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);   
            } 
            else 
            {
                l_orderUnit = l_orderUnits[0]; 
            }       
            
            IfoOrderUnitRow l_ifoOrderUnitRow = 
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject(); 
                                       
            //�����h�c���擾����        
            l_lngProductId = l_ifoOrderUnitRow.getProductId();

            //�敨OP�����}�l�[�W�����擾����          
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager(); 
                                       
            //�敨OP�����I�u�W�F�N�g�𐶐�����
            WEB3IfoProductImpl l_ifoProduct = 
                (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
                                        
            //�����Y�����R�[�h���擾����
            String l_strProductCode = l_ifoProduct.getUnderlyingProductCode();            
            log.debug("�����Y�����R�[�h���擾:" + l_strProductCode);

            //����J�����_�R���e�L�X�g�𐶐�����
            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();

            // �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h =  �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h12�F�����w���敨OP�i��������j�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL);            
            //����J�����_�R���e�L�X�g.�����R�[�h = �����Y�����R�[�h
            l_context.setProductCode(l_strProductCode);
            //����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h06�F����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            // �R�j�@@��t�����A���t���[�����Z�b�g����B
            try 
            {
                WEB3GentradeTradingTimeManagement.setTimestamp();
            } 
            catch (WEB3SystemLayerException l_ex) 
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + METHOD_NAME);           
            }
            
            // �S�j�@@��������x�e���ԑт̏ꍇ(*2)�̂݁A�L���[�e�[�u���̋��L���b�N���s���B
            // ������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ�́A�x�e���ԑтł���Ɣ��肷��B
            if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
            {
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnits[0];
                // �敨OP�����T�[�r�X.lock�敨OP��������L���[(�����Ώے����P��(*3))���R�[������B
                WEB3IfoFrontOrderService l_ifoOrderService =
                    (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
                l_ifoOrderService.lockHostFotypeOrderAll(l_ifoOrderUnit);
            }

            return l_context;
        }
        catch (WEB3BaseException l_ex)
        {                      
            log.error("The Exception happened.");
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + METHOD_NAME); 
        }
        catch (NotFoundException l_nfe)
        {                      
            log.error("The Exception happened.");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME); 
        }  
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 40A81C72035D
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String METHOD_NAME =
             "onReturn(Object,Object)";
        log.entering(METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        log.exiting(METHOD_NAME);        
          
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 40A81C72036D
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String METHOD_NAME =
             "onThrowable(Object,Throwable)";
        log.entering(METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(METHOD_NAME);          
     
    }
}
@
