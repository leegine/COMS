head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeOpenMarginServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������V�K���T�[�r�X�C���^�Z�v�^(WEB3ToSuccMarginChangeOpenMarginServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17�@@���@@��(���u) �V�K�쐬
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
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p��������V�K���T�[�r�X�C���^�Z�v�^)<BR>
 * �i�A���j�M�p��������V�K���T�[�r�X�C���^�Z�v�^<BR>
 * 
 * @@author ���@@��(���u)
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeOpenMarginServiceInterceptor implements Interceptor 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeOpenMarginServiceInterceptor.class);
    
    /**
     * @@roseuid 436ACB32037A
     */
    public WEB3ToSuccMarginChangeOpenMarginServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^���u�i�A���j�M�p������������V�K���������N�G�X�g�v<BR>
     * �̏ꍇ�̂݁A���������b�N����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g���Ĕ��ʂ���B<BR>
     * <BR>
     * �@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@��������OpLoginSecurityService���ҏW�B<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * �@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = (*1)<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =  �h05�F�����h<BR>
     * <BR>
     * �@@(*1) ���N�G�X�g�f�[�^�I�u�W�F�N�g.ID�ɊY������\�񒍕��P�ʃI�u�W�F�N�g���ҏW�B<BR>
     * �@@�@@�@@�A�������}�l�[�W��.get�\�񒍕��P��(���N�G�X�g�f�[�^.ID)�Ŏ擾����<BR>
     * �@@�@@�@@�\�񒍕��P��.get�s��().�s��R�[�h���Z�b�g����B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �@@�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 433BD2F602C5
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
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            MainAccount l_mainAccount = 
                l_accountManager.getMainAccount(l_lngAccountId);
            
            //1) ���N�G�X�g�f�[�^�̌^���u�i�A���j�M�p������������V�K���������N�G�X�g�v
            // �̏ꍇ�̂݁A���������b�N����B
            // �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g���Ĕ��ʂ���B
            // �@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
            // �@@��������OpLoginSecurityService���ҏW�B
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            String l_accountCode = l_mainAccount.getAccountCode();
            long l_lngOrderId = 0;
            if (l_serviceParam[0] instanceof WEB3SuccMarginOpenChangeCompleteRequest)
            {
                WEB3SuccMarginOpenChangeCompleteRequest l_completeRequest = 
                    (WEB3SuccMarginOpenChangeCompleteRequest) l_serviceParam[0];
                
                l_lngOrderId = Long.parseLong(l_completeRequest.id);
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_accountCode);
            }
            else if (l_serviceParam[0] instanceof WEB3SuccMarginOpenChangeConfirmRequest)
            {
                WEB3SuccMarginOpenChangeConfirmRequest l_confirmRequest = 
                    (WEB3SuccMarginOpenChangeConfirmRequest) l_serviceParam[0];
                l_lngOrderId = Long.parseLong(l_confirmRequest.id);
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
            
            //����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            //�Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = 
                l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            WEB3GentradeMarket l_market = l_orderUnit.getMarket();
            if (l_market == null)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h 
            l_context.setMarketCode(l_market.getMarketCode());
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =  �h05�F�����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
            
            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            //������ԊǗ�
            WEB3GentradeTradingTimeManagement.setTimestamp();
            log.exiting(STR_METHOD_NAME);
            return l_context; 
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
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
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 433BD2F602D5
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
       
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
           
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 433BD2F602F4
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
