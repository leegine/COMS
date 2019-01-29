head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeCloseMarginServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������ԍσT�[�r�X�C���^�Z�v�^(WEB3ToSuccMarginChangeCloseMarginServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17 杊��](���u) �V�K�쐬
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p��������ԍσT�[�r�X�C���^�Z�v�^)<BR>
 * �i�A���j�M�p��������ԍσT�[�r�X�C���^�Z�v�^<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeCloseMarginServiceInterceptor implements Interceptor 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeCloseMarginServiceInterceptor.class);

    /**
     * @@roseuid 436ACB3200FA
     */
    public WEB3ToSuccMarginChangeCloseMarginServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^���u�i�A���j�M�p������������ԍϊ������N�G�X�g�v��<BR>
     * �ꍇ�̂݁A���������b�N����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g���Ĕ��ʂ���B<BR>
     * <BR>
     * �@@�g���A�J�E���g�}�l�[�W��.lock����<BR>
     * �@@(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@��������OpLoginSecurityService���ҏW�B<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     *   ����J�����_�R���e�L�X�g.�s��R�[�h = (*1)<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h05�F�����h<BR>
     * <BR>
     * �@@(*1) ���N�G�X�g�f�[�^�I�u�W�F�N�g.ID�ɊY������\�񒍕��P�ʃI�u�W�F�N�g���ҏW�B<BR>
     * �@@�@@�@@�A�������}�l�[�W��.get�\�񒍕��P��(���N�G�X�g�f�[�^.ID)�Ŏ擾����<BR>
     * �@@�@@�@@�\�񒍕��P��.get�s��().�s��R�[�h���Z�b�g����B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 433D00720180
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        try
        {
            //����J�����_�����p����R���e�L�X�g�𐶐�����B
            //�ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j
            //�P�j�@@���N�G�X�g�f�[�^�̌^���u�i�A���j�M�p������������ԍϊ������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B
            //�@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g���Ĕ��ʂ���B
            //�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
            //�@@��������OpLoginSecurityService���ҏW�B
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            long l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //�g���A�J�E���g�}�l�[�W���擾����    
            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager) l_finApp.getAccountManager();  
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);            
            //�،���ЃR�[�h
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            //���X�R�[�h
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            //���N�G�X�g�f�[�^.ID
            String l_strOrderId = null;
            
            if (l_serviceParam[0] instanceof WEB3SuccMarginCloseChangeCompleteRequest)
            {  
                l_accMgr.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_mainAccount.getAccountCode());
                WEB3SuccMarginCloseChangeCompleteRequest l_completeRequest =
                    (WEB3SuccMarginCloseChangeCompleteRequest) l_serviceParam[0];
                l_strOrderId = l_completeRequest.id;
            }
            else if (l_serviceParam[0] instanceof WEB3SuccMarginCloseChangeConfirmRequest)
            {
                WEB3SuccMarginCloseChangeConfirmRequest l_confirmRequest = 
                    (WEB3SuccMarginCloseChangeConfirmRequest) l_serviceParam[0];
                l_strOrderId = l_confirmRequest.id;
            }
            else
            {
                log.debug("�p�����[�^�^�C�v�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�����[�^�^�C�v�s���B");
            }

            //�Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            //�@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
            //�@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            //�،���ЃR�[�h���擾
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            //�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);
            //�@@����J�����_�R���e�L�X�g.���X�R�[�h = ���X�R�[�h
            l_context.setBranchCode(l_strBranchCode);

            //�@@(*)���N�G�X�g�f�[�^�I�u�W�F�N�g.ID�ɊY������\�񒍕��P�ʃI�u�W�F�N�g���ҏW�B
            //�@@�@@�@@�A�������}�l�[�W��.get�\�񒍕��P��(���N�G�X�g�f�[�^.ID)�Ŏ擾����
            //�@@�@@�@@�\�񒍕��P��.get�s��().�s��R�[�h���Z�b�g����B
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            long l_lngOrderId = Long.parseLong(l_strOrderId);
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            
            if (l_orderUnit == null)
            {
                log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "�\�����Ȃ��V�X�e���G���[���������܂����B");
            }

            //����J�����_�R���e�L�X�g.�s��R�[�h�@@=�@@�\�񒍕��P��.get�s��().�s��R�[�h
            l_context.setMarketCode(l_orderUnit.getMarket().getMarketCode()); 
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h05�F�����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
            
            //�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�
            //�@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);        
            //�R�j�@@��t�����A���t���[�����Z�b�g����B
            //�@@�|������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(),
                l_nfe);
        }    
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 433D007201AF
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        //������ԊǗ�.TIMESTAMP_TAG
        log.debug("������ԊǗ�.TIMESTAMP_TAG = " + 
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //������ԊǗ�.OFFSET_TAG
        log.debug("������ԊǗ�.OFFSET_TAG = " + 
            WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        log.debug("������ԊǗ�.TRADING_CAL_CONTEXT_PATH = " + 
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 433D007201BE
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        //������ԊǗ�.TIMESTAMP_TAG
        log.debug("������ԊǗ�.TIMESTAMP_TAG = " + 
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //������ԊǗ�.OFFSET_TAG
        log.debug("������ԊǗ�.OFFSET_TAG = " + 
            WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        log.debug("������ԊǗ�.TRADING_CAL_CONTEXT_PATH = " + 
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
