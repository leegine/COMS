head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.21.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashoutTradingPowerUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���]�̓`�F�b�NUnitService�C���^�Z�v�^(WEB3AioCashoutTradingPowerUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
                   2004/12/09 ���E (���u) �c�Ή�
                   2006/02/21 ��O�� (���u) �d�l�ύX�E���f��No.498
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.data.HostPaymentOrderParams;
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
 * (�o���]�̓`�F�b�NUnitService�C���^�Z�v�^)<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3AioCashoutTradingPowerUnitServiceInterceptor 
    implements Interceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutTradingPowerUnitServiceInterceptor.class);
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|����.�T�[�r�X�̈���[0]���o�����������L���[Params�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��� <BR>
     * ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     * �o�����������L���[Params.�،���ЃR�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �o�����������L���[Params.���X�R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h16�F�o���h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h08�F�o���h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�� <BR>
     * �Ď�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@���������b�N����B <BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����( <BR>
     * �،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B <BR>
     *   �������͏o�����������L���[Params����擾�B <BR> 
     * 
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * @@return Object
     * @@roseuid 4141790E0031
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
       
        if (l_serviceParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_serviceParams.length == 0)
        {
            log.debug("Collection�^�C�v�̃p�����[�^Size�͂O�ł��Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
       
        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        
        //�|����.�T�[�r�X�̈���[0]���o�����������L���[Params�I�u�W�F�N�g�ɃL���X�g����B
        HostPaymentOrderParams l_hostPaymentOrderParams = 
            (HostPaymentOrderParams) l_serviceParams[0];
        
        try
        {
            //============================FinApp==============================
            FinApp l_finApp = 
                (FinApp) Services.getService(FinApp.class);
                    
            //�A�J�E���g�}�l�[�W���擾����
            AccountManager l_accMgr = l_finApp.getAccountManager();            
            
            //�،���ЃR�[�h�̎擾
            String l_strInstitutionCode = l_hostPaymentOrderParams.getInstitutionCode();
            
            //���X�R�[�h�̎擾
            String l_strBranchCode = l_hostPaymentOrderParams.getBranchCode();
            
            //�|����J�����_�R���e�L�X�g�ɂ̎擾
            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �o�����������L���[Params.�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);

            //����J�����_�R���e�L�X�g.���X�R�[�h = �o�����������L���[Params.���X�R�[�h
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h16�F�o���h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.PAYMENT);
        
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
            //����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.PAYMENT);
        
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =  �h08�F�o�� 
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.PAYMENT);
        
            //�|ThreadLocalSystemAttributesRegistry.setAttribute()
            // �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B 
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                  WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                  l_context);
            
            // �Q�j�@@��t�����A���t���[�����Z�b�g����B
            //�|������ԊǗ�.setTimestamp()���R�[������B 
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            // �R�j�@@���������b�N����B 
            //�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
            //�������͏o�����������L���[Params����擾�B 
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_accMgr;
            String l_strAccountCode = l_hostPaymentOrderParams.getAccountCode();
            
            try
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
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
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()", l_ex);
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
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * @@param l_returnValue
     * @@roseuid 4141790E0050
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME =
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
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
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * @@param l_obj
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * @@roseuid 4141790E0060
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
            
        log.exiting(STR_METHOD_NAME);   
    }
}
@
