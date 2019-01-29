head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionCancelOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP��������T�[�r�X�C���^�Z�v�^(WEB3OptionCancelOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/19 ���� �V�K�쐬
Revesion History : 2007/01/26 �����q (���u) �d�l�ύX�@@���f��NO.615
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.ifo.message.WEB3OptionsCancelCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmRequest;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;

/**
 * (OP��������T�[�r�X�C���^�Z�v�^)<BR>
 * �����w���I�v�V������������T�[�r�X�C���^�Z�v�^�N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionCancelOrderServiceInterceptor implements Interceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionCancelOrderServiceInterceptor.class);    
           
    /**
     * @@roseuid 40C07B4301A5
     */
    public WEB3OptionCancelOrderServiceInterceptor() 
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
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁA<BR>OpLoginSecurityService�̓��e���<BR>������ԃR
     * ���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h<BR> = 
     * OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h<BR> = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h<BR> = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪<BR> = 
     * �h12�F�����w���敨OP�i��������j�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h<BR> = �i*1) �����Y�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i<BR> = �h06�F�I�v�V�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V����<BR> = �h06�F����h<BR>
     * <BR>
     *  (*1) �����Y�����R�[�h�̎擾���@@<BR>
     * �@@���N�G�X�g�f�[�^.�h�c�ɊY������<BR>�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@����.getOrderUnits()[0].getProductId()<BR>�ɂĖ����h�c���擾����B<BR>
     * �@@�����h�c�ɊY������敨OP�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������敨OP����.get�����Y�����R�[�h()�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( 
     * )<BR>�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.<BR>TRADING_CAL_CONTEXT_PATH<BR>
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
     * @@param l_method - (�T�[�r�X���\�b�h)
     * �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 405562F70108
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        try
        {    
            long l_lngOrderId   = 0;
            long l_lngProductId = 0;
            long l_lngAccountId = 0;
            
            //���N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g������擾����
            Object l_request = l_serviceParam[0];
            if (l_request == null)
            {   
                log.error("parameter is null type");            
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);   
            }
            
            if (l_request instanceof WEB3OptionsCancelConfirmRequest)
            {
                WEB3OptionsCancelConfirmRequest  l_cancelRequest =
                    (WEB3OptionsCancelConfirmRequest) l_request;
                l_lngOrderId = Long.parseLong(l_cancelRequest.id);
            }
            else if (l_request instanceof WEB3OptionsCancelCompleteRequest)
            {
                WEB3OptionsCancelCompleteRequest  l_cancelRequest =
                    (WEB3OptionsCancelCompleteRequest) l_request;
                l_lngOrderId = Long.parseLong(l_cancelRequest.id);
            }        
            else
            {
                log.error("�p�����[�^�^�C�v�s���B");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);                       
            }
            
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                    OpLoginSecurityService.class);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);  
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

            //�����}�l�[�W�����擾����
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();   
            l_lngAccountId = l_opLoginSec.getAccountId();                     

            //�������擾����
            MainAccount l_mainAccount = 
                l_accountMananger.getMainAccount(l_lngAccountId);

            //�،���ЃR�[�h           
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();     

            //���X�R�[�h
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();

            // �P�j�@@���N�G�X�g�f�[�^�̌^���u�����w���I�v�V��������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR> 
            // �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
            // ��������OpLoginSecurityService���ҏW�B<BR>
            if (l_request instanceof WEB3OptionsCancelCompleteRequest)
            {
                l_accountMananger.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_mainAccount.getAccountCode());
            }

            //�����}�l�[�W�����擾����      
            WEB3OptionOrderManagerImpl l_orderManager = 
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();                
                
            //�����h�c���w�肵�����P�ʎ擾            
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);    
            OrderUnit l_orderUnit = null;
            
            if (l_orderUnits.length==0)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
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
            //����J�����_�R���e�L�X�g.������t���i = �h06�F�I�v�V�����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h06�F����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
        
            //������ԃR���e�L�X�g���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);     
           
            // �R�j�@@��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();

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

            log.exiting(STR_METHOD_NAME);               
            return l_context;
        }
        catch (NotFoundException l_nfe)
        {           
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }  
        catch (WEB3BaseException l_webex)
        {   
            log.error(l_webex.getMessage(),l_webex);
            throw new WEB3BaseRuntimeException(
                l_webex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry<BR>�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 405562F70118
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME =
             "onReturn(Object,Object)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry<BR>�̈ȉ��̓��e���N���A����
     * �B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 405562F70137
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME =
             "onThrowable(Object,Throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);          
    }
}
@
