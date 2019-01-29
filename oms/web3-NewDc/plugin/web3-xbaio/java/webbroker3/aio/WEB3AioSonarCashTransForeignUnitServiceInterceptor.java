head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.27.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransForeignUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  SONAR���o���i�O�݁jUnitService�C���^�Z�v�^(WEB3AioSonarCashTransForeignUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.data.HostForeignCashTransferParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderDivDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 *  SONAR���o���i�O�݁jUnitService�C���^�Z�v�^<BR>
 * (SONAR���o���i�O�݁jUnitService�C���^�Z�v�^�N���X)<BR>
 *<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignUnitServiceInterceptor implements Interceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransForeignUnitServiceInterceptor.class);

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>  
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>  
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>  
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>  
     * �@@�|����.�T�[�r�X�̈���[0]���O�ݓ��o��Params�I�u�W�F�N�g�ɃL���X�g����B<BR>  
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g��<BR>
     * �@@�v���p�e�B���Z�b�g����B<BR>  
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �O�ݓ��o��Params.�،���ЃR�[�h  <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �O�ݓ��o��Params.���X�R�[�h  <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h  <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1)��t���ԋ敪 <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h  <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h  <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*2)������t�g�����U�N�V����  <BR>
     * <BR>
     * �@@(*1)��t���ԋ敪�̎擾���@@ <BR>
     * �@@�@@�O�ݓ��o��Params.���o���敪 = 1�F�o�� �̏ꍇ�A�h16�F�o���h <BR>
     * �@@�@@�O�ݓ��o��Params.���o���敪 = 2�F���� �̏ꍇ�A�h14�F�����h <BR>
     * <BR>
     * �@@(*2)������t�g�����U�N�V�����̎擾���@@ <BR>
     * �@@�@@�O�ݓ��o��Params.���o���敪 = 1�F�o�� �̏ꍇ�A�h08�F�o���h <BR>
     * �@@�@@�O�ݓ��o��Params.���o���敪 = 2�F���� �̏ꍇ�A�h10�F�����h<BR> 
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>  
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B  <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B  <BR>
     * <BR>
     * �R�j�@@���������b�N����B  <BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>  
     * �@@�������͊O�ݓ��o��Params���ҏW�B <BR>
     * <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
       
        if (l_serviceParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_serviceParams.length == 0)
        {
            log.debug("Collection�^�C�v�̃p�����[�^Size�͂O�ł��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B  
        // �@@�|����.�T�[�r�X�̈���[0]���O�ݓ��o��Params�I�u�W�F�N�g�ɃL���X�g����B  
        // �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B  
        HostForeignCashTransferParams l_hostForeignCashTransferParams = 
            (HostForeignCashTransferParams)l_serviceParams[0];
        
        //   (*1)��t���ԋ敪�̎擾���@@ 
        //     �O�ݓ��o��Params.���o���敪 = 1�F�o�� �̏ꍇ�A�h16�F�o���h 
        //     �O�ݓ��o��Params.���o���敪 = 2�F���� �̏ꍇ�A�h14�F�����h 
        String l_strTradingTimeType = null;
        if ((WEB3OrderDivDef.CASHOUT).equals(
            (l_hostForeignCashTransferParams.getOrderDiv())))
        {
            l_strTradingTimeType = WEB3TradingTimeTypeDef.PAYMENT;
        }
        else if ((WEB3OrderDivDef.CASHIN).equals(
            (l_hostForeignCashTransferParams.getOrderDiv())))
        {
            l_strTradingTimeType = WEB3TradingTimeTypeDef.RECIEPT;
        }
        
        //   (*2)������t�g�����U�N�V�����̎擾���@@ 
        //     �O�ݓ��o��Params.���o���敪 = 1�F�o�� �̏ꍇ�A�h08�F�o���h 
        //     �O�ݓ��o��Params.���o���敪 = 2�F���� �̏ꍇ�A�h10�F�����h 
        String l_strOrderAccTransaction = null;
        if ((WEB3OrderDivDef.CASHOUT).equals(
            (l_hostForeignCashTransferParams.getOrderDiv())))
        {
            l_strOrderAccTransaction = WEB3OrderAccTransactionDef.PAYMENT;
        }
        else if ((WEB3OrderDivDef.CASHIN).equals(
            (l_hostForeignCashTransferParams.getOrderDiv())))
        {
            l_strOrderAccTransaction = WEB3OrderAccTransactionDef.CASH_IN; 
        }
        
        //�|����J�����_�R���e�L�X�g�ɂ̎擾
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
        
        // �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �O�ݓ��o��Params.�،���ЃR�[�h
        l_context.setInstitutionCode(l_hostForeignCashTransferParams.getInstitutionCode());
        
        // �@@����J�����_�R���e�L�X�g.���X�R�[�h = �O�ݓ��o��Params.���X�R�[�h  
        l_context.setBranchCode(l_hostForeignCashTransferParams.getBranchCode());
        
        // �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h  
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
        // �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1)��t���ԋ敪 
        l_context.setTradingTimeType(l_strTradingTimeType);
        
        // �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h  
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        // �@@����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h  
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.PAYMENT);
        
        // �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*2)������t�g�����U�N�V����  
        l_context.setOrderAcceptTransaction(l_strOrderAccTransaction);
        
        // �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B  
        // �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        
        //����J�����_�R���e�L�X�g�ɓ��e���Z�b�g���閾��
        
        try
        {
            // �Q�j�@@��t�����A���t���[�����Z�b�g����B  
            // �@@�|������ԊǗ�.setTimestamp()���R�[������B  
            WEB3GentradeTradingTimeManagement.setTimestamp();
                  
            // �R�j�@@���������b�N����B  
            //�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B  
            //   �������͊O�ݓ��o��Params���ҏW�B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            l_accountManager.lockAccount(
                l_hostForeignCashTransferParams.getInstitutionCode(),
                l_hostForeignCashTransferParams.getBranchCode(),
                l_hostForeignCashTransferParams.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in setTimestamp__", l_ex);
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
     * ����J�����_�R���e�L�X�g�N���A�����B  <BR>
     *  <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B  <BR>
     *  <BR>
     * ������ԊǗ�.TIMESTAMP_TAG  <BR>
     * ������ԊǗ�.OFFSET_TAG  <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * @@param l_context - (onCall�p�^�[���l)<BR>
     * onCall�p�^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h�p�^�[���l)<BR>
     * �T�[�r�X���\�b�h�p�^�[���l<BR>
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
     *  <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR> 
     *  <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B  <BR>
     *  <BR>
     * ������ԊǗ�.TIMESTAMP_TAG  <BR>
     * ������ԊǗ�.OFFSET_TAG  <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
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