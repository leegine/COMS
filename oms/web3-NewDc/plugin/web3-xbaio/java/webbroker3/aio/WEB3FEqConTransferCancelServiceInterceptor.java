head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FEqConTransferCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ւ̐U�֎���T�[�r�X�C���^�Z�v�^�N���X(WEB3FEqConTransferCancelServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ��O�� (���u) �V�K�쐬       
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.message.WEB3FEqConTransferCancelCompleteRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������ւ̐U�֎���T�[�r�X�C���^�Z�v�^)<BR>
 * �O�������ւ̐U�֎���T�[�r�X�C���^�Z�v�^�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferCancelServiceInterceptor implements Interceptor 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferCancelServiceInterceptor.class);
    
    /**
     * @@roseuid 42355E710251
     */
    public WEB3FEqConTransferCancelServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     *      ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h24�F�O�������U�֘A�g�h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h02�F�U�ցh <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h27�F�O�������U�֘A�g�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h06�F����h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     *      ������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@���N�G�X�g�f�[�^�̌^���u�O�������ւ̐U�֎���������N�G�X�g�v�̏ꍇ�̂݁A<BR>
     *      ���������b�N����B <BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)<BR>
     *      ���R�[������B <BR>
     *   ��������OpLoginSecurityService���ҏW�B
     * @@param l_method - �T�[�r�X���\�b�h
     * @@param l_serviceParams - �T�[�r�X���\�b�h����
     * @@return java.lang.Object
     * @@roseuid 41E3949F0327
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 
        //�@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B 
        //�@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B 
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        
        long l_lngAccountId = l_opLoginSecurityService.getAccountId();
        try
        {
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            
            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            
            //���X�R�[�h���擾����
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            //�����R�[�h
            String l_strAccountCode = l_mainAccount.getAccountCode();
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW 
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW 
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h24�F�O�������U�֘A�g�h  
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FEQ_CON);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h02�F�U�ցh 
            l_context.setProductCode(WEB3ProductCodeDef.TRANSFER_PAYMENT);
            
            //����J�����_�R���e�L�X�g.������t���i = �h27�F�O�������U�֘A�g�h 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FEQ_CON);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h06�F����h 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g���� 
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //�Q�j��t�����A���t���[�����Z�b�g����
            //������ԊǗ�.setTimestamp()���R�[������ 
            //throw SystemLayerException  
            WEB3GentradeTradingTimeManagement.setTimestamp(); 
            
            //�R�j�@@���N�G�X�g�f�[�^�̌^���u�O�������ւ̐U�֎���������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B 
            //�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
            //��������OpLoginSecurityService���ҏW�B 
            if (l_serviceParams[0] instanceof WEB3FEqConTransferCancelCompleteRequest)
            {
                //throw WEB3BaseException(NotFoundException, ResourceBusyException)
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode);
            }      
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in l_accountManager.getMainAccount__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in lockAccount__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                            
        log.exiting(STR_METHOD_NAME);        
        return null;
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41E3949F0346
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object l_context, Object l_returnValue)";
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
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 41E3949F0356
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object l_obj, Throwable l_throwable)";
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