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
filename	WEB3OptionChangeOpenContractServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�����V�K���T�[�r�X�C���^�Z�v�^(WEB3OptionChangeOpenContractServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/16 ���o�� (���u) �V�K�쐬
Revesion History : 2004/07/26 ���Ō� (���u) onCall���C�� �Ή��o�b�O IFO_UT-000016
Revesion History : 2004/07/26 ���Ō� (���u) onCall���C�� �Ή��o�b�O IFO_UT-000019
Revesion History : 2007/01/29 �����q (���u) �d�l�ύX�@@���f��NO.590
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmRequest;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;

/**
 * (OP�����V�K���T�[�r�X�C���^�Z�v�^)<BR>
 * �����w���I�v�V���������V�K���T�[�r�X�C���^�Z�v�^�N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionChangeOpenContractServiceInterceptor implements Interceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeOpenContractServiceInterceptor.class);
    
    /**
     * @@roseuid 40C07B410242
     */
    public WEB3OptionChangeOpenContractServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^���u�����w���I�v�V���������V�K���������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR> 
     *�@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B <BR>
     *��������OpLoginSecurityService���ҏW�B <BR> 
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��<BR>
     * �������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =<BR>
     *  OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     * OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
     * �h12�F�����w���敨OP�i��������j�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h06�F�I�v�V�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h05�F�����h<BR>
     * <BR>
     *  (*1) �����Y�����R�[�h�̎擾���@@<BR>
     * �@@���N�G�X�g�f�[�^.�h�c�ɊY�����钍���I�u�W�F�N�g�𐶐�����B<BR>
     * �@@����.getOrderUnits()[0].getProductId()�ɂĖ����h�c���擾����B<BR>
     * �@@�����h�c�ɊY������敨OP�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������敨OP����.get�����Y�����R�[�h()�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �S�j�@@��������x�e���ԑт̏ꍇ(*2)�̂݁A�L���[�e�[�u���̋��L���b�N���s���B<BR>
     * <BR>
     * �@@�@@�敨OP�����T�[�r�X.lock�敨OP��������L���[(�����Ώے����P��(*3))���R�[������B<BR>
     * <BR>
     * �@@�@@(*2)��������x�e���ԑт̏ꍇ<BR>
     * �@@�@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ�́A�x�e���ԑтł���Ɣ��肷��B<BR>
     * <BR>
     * �@@�@@(*3)�����Ώے����P��<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g.ID�ɊY�����钍��ID���������P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�@@���������݂���ꍇ�́AgetOrderUnits()�̍ŏ��̗v�f���g�p����B<BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 4056BF8B023F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME =
            getClass().getName() + ".onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        //�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        String l_strRequestID = null;
        Object l_completeRequest = l_serviceParam[0];
        if (l_serviceParam[0] instanceof WEB3OptionsOpenMarginChangeConfirmRequest)
        {
            WEB3OptionsOpenMarginChangeConfirmRequest l_request = (WEB3OptionsOpenMarginChangeConfirmRequest)l_serviceParam[0];
            l_strRequestID = l_request.id;
        }
        else if (l_serviceParam[0] instanceof WEB3OptionsOpenMarginChangeCompleteRequest)
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request = (WEB3OptionsOpenMarginChangeCompleteRequest)l_serviceParam[0];
            l_strRequestID = l_request.id;
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }
              
        
        String l_strInstitutionCode = null; //�،���ЃR�[�h
        String l_strBranchCode      = null; //���X�R�[�h
        String l_strProductCode = null; //�����Y�����R�[�h
        
        try
        {
            long l_lngAccountId = 0;    // �����R�[�h
            FinApp l_finApp = null;         //FinancialApplication
            AccountManager l_accMgr = null; 
            MainAccount l_mainAccount = null;
            OrderUnit[] l_orderUnit;
            
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
                   
            //MainAccount���擾
            l_lngAccountId = l_opLoginSec.getAccountId();
            l_finApp = (FinApp) Services.getService(FinApp.class);
            l_accMgr = l_finApp.getAccountManager(); 
                       
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            
            //�،���ЃR�[�h���擾
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            //���X�R�[�h���擾
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();

            // �P�j�@@���N�G�X�g�f�[�^�̌^���u�����w���I�v�V���������V�K���������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B            
            if (l_completeRequest instanceof WEB3OptionsOpenMarginChangeCompleteRequest)
            {
            ((WEB3GentradeAccountManager)l_accMgr).lockAccount(l_strInstitutionCode,
                l_strBranchCode,l_mainAccount.getAccountCode());
            }

            WEB3OptionOrderManagerImpl l_orderManager = 
               (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            l_orderUnit = l_orderManager.getOrder(Long.parseLong(l_strRequestID)).getOrderUnits();

            //�����Y�����R�[�h�̎擾
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(l_orderUnit[0].getProduct().getProductId());
             
            l_strProductCode = l_ifoProduct.getUnderlyingProductCode();

            WEB3GentradeTradingClendarContext l_context =
                             new WEB3GentradeTradingClendarContext();

            // �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode); 
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 =   �h12�F�����w���敨OP�i��������j�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL); 
            //����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h  
            l_context.setProductCode(l_strProductCode);       
            //����J�����_�R���e�L�X�g.������t���i = �h06�F�I�v�V�����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);         
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h05�F�����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
        
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
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit[0];
                // �敨OP�����T�[�r�X.lock�敨OP��������L���[(�����Ώے����P��(*3))���R�[������B
                WEB3IfoFrontOrderService l_ifoOrderService =
                    (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
                l_ifoOrderService.lockHostFotypeOrderAll(l_ifoOrderUnit);
            }

            log.exiting(STR_METHOD_NAME);
            return l_context;    
        } 
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex); 
                                  
        }
        catch (DataException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex); 
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex); 
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
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 4056BF8B025F
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME =
            getClass().getName() + ".onReturn(Object,Object)";
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
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 4056BF8B026E
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME =
            getClass().getName() + ".onThrowable(Object,Throwable)";
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
