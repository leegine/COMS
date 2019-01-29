head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.29.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinCooperationNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�g�ʒm�ꌏ�T�[�r�X�C���^�Z�v�^(WEB3AioCashinCooperationNotifyUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/11 ����(���u) �V�K�쐬
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

/**
 * �����A�g�ʒm�ꌏ�T�[�r�X�C���^�Z�v�^<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AioCashinCooperationNotifyUnitServiceInterceptor implements Interceptor 
{
    /**
     *  ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashinCooperationNotifyUnitServiceInterceptor.class);    
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     *       ����J�����_�R���e�L�X�g.�،���ЃR�[�h = �ڋq.�،���ЃR�[�h  <BR>
     * 		 ����J�����_�R���e�L�X�g.���X�R�[�h = �ڋq.���X�R�[�h  <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h14�F�����h  <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h  <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h10�F�����h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     *     ������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@  �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH   <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �R�j���������b�N����B<BR> 
     *   �|�g���A�J�E���g�}�l�[�W��.lock����<BR>
     *     (�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     *     �������͌ڋq���ҏW�B  <BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 40B2EDA703A7
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^��Null�ł���");       
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("Collection�^�C�v�̃p�����[�^Size�͂O�ł��Ȃ��B");        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "Collection�^�C�v�̃p�����[�^Size�͂O�ł��Ȃ��B");      
        }
        
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount) l_serviceParam[1];
        try
        {
            //�،���ЃR�[�h���擾
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();  
            //���X�R�[�h���擾
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            //�����R�[�h���擾
            String l_strAccountCode = l_mainAccount.getAccountCode();

            //���N�G�X�g�f�[�^�̓��e�ƁA
            //OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            
            // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = �ڋq.�،���ЃR�[�h  
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            // ����J�����_�R���e�L�X�g.���X�R�[�h = �ڋq.���X�R�[�h
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h14�F�����h 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.RECIEPT);
            
            //�@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h   
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.PAYMENT);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h10�F�����h  
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CASH_IN);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�
            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);                
            
            //�Q�j��t�����A���t���[�����Z�b�g����
            //WEB3BaseException
            WEB3GentradeTradingTimeManagement.setTimestamp(); 
            
            //�R�j���������b�N����B
            //�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B  
            //�g���A�J�E���g�}�l�[�W���擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManaer = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
            l_gentradeAccountManaer.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);         
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
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
     * 
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41BCF2EB0076
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        String l_strMethodName = "onReturn(Object l_context, Object l_returnValue)";
        log.entering(l_strMethodName);
        
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
        
        log.exiting(l_strMethodName);
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
     * 
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 41BCF2EB0095
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        String l_strMethodName = "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(l_strMethodName);
        
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
        
        log.exiting(l_strMethodName);
    }
}
@
