head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInfoCreatedServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐ\���T�[�r�X�C���^�Z�v�^(WEB3AccOpenInfoCreatedServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 �s�p (���u) �V�K�쐬
Revesion History : 2009/08/10 �����F(���u) �d�l�ύX ���f��165
*/

package webbroker3.accountopen;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�����J�ݐ\���T�[�r�X�C���^�Z�v�^)<BR>
 * �����J�ݐ\���T�[�r�X�C���^�Z�v�^<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */

public class WEB3AccOpenInfoCreatedServiceInterceptor implements Interceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenInfoCreatedServiceInterceptor.class);

    /**
     * @@roseuid 41B5AB7D02AF
     */
    public WEB3AccOpenInfoCreatedServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = ���،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �����X�R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h22�F�����J�݁h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * ������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �@@���،���ЃR�[�h�C���X�R�[�h<BR>
     * �@@�@@���N�G�X�g�f�[�^�������J�ݐ\�����̓��N�G�X�g�A<BR>
     * �@@�@@�@@���͌����J�݃��[���A�h���X�o�^���̓��N�G�X�g�A <BR>
     * �@@�@@�@@���͌����J�݃��[���A�h���X�o�^�������N�G�X�g�̏ꍇ�A <BR>
     * ���N�G�X�g�f�[�^���ҏW����B<BR>
     * �@@�@@�ȊO�̏ꍇ�A���N�G�X�g�f�[�^.�����J�ݐ\�������ҏW����B<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     * @@roseuid 419C8E5E0283
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = null;
        
        String l_strBranchCode = null;
              
        if (l_serviceParam[0] instanceof WEB3AccOpenApplyInputRequest)
        {
            WEB3AccOpenApplyInputRequest l_request = (WEB3AccOpenApplyInputRequest)l_serviceParam[0];
            
            //�،���ЃR�[�h
            l_strInstitutionCode = l_request.institutionCode;     
            
            // ���X�R�[�h
            l_strBranchCode = l_request.branchCode;   
        }
        else if (l_serviceParam[0] instanceof WEB3AccOpenApplyConfirmRequest)
        {
            WEB3AccOpenApplyConfirmRequest l_request = (WEB3AccOpenApplyConfirmRequest)l_serviceParam[0];
            
            if (l_request.accoutOpenApplyInfo != null)
            {
                //�،���ЃR�[�h
                l_strInstitutionCode = l_request.accoutOpenApplyInfo.institutionCode;     
            
                // ���X�R�[�h
                l_strBranchCode = l_request.accoutOpenApplyInfo.branchCode;
            }
        }
        else if (l_serviceParam[0] instanceof WEB3AccOpenApplyCompleteRequest)
        {
            WEB3AccOpenApplyCompleteRequest l_request = (WEB3AccOpenApplyCompleteRequest)l_serviceParam[0];
            
            if (l_request.accoutOpenApplyInfo != null)
            {
                //�،���ЃR�[�h
                l_strInstitutionCode = l_request.accoutOpenApplyInfo.institutionCode;     
            
                // ���X�R�[�h
                l_strBranchCode = l_request.accoutOpenApplyInfo.branchCode; 
            }
        }
        else if (l_serviceParam[0] instanceof WEB3AccOpenMailAddrRegInputRequest)
        {
            WEB3AccOpenMailAddrRegInputRequest l_request = (WEB3AccOpenMailAddrRegInputRequest)l_serviceParam[0];
            //�،���ЃR�[�h
            l_strInstitutionCode = l_request.institutionCode;

            // ���X�R�[�h
            l_strBranchCode = l_request.branchCode;
        }
        else if (l_serviceParam[0] instanceof WEB3AccOpenMailAddrRegCompleteRequest)
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request = (WEB3AccOpenMailAddrRegCompleteRequest)l_serviceParam[0];
            //�،���ЃR�[�h
            l_strInstitutionCode = l_request.institutionCode;

            // ���X�R�[�h
            l_strBranchCode = l_request.branchCode;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try {
        
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
                
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = ���،���ЃR�[�h 
            l_context.setInstitutionCode(l_strInstitutionCode);
    
            //����J�����_�R���e�L�X�g.���X�R�[�h = �����X�R�[�h  
            l_context.setBranchCode(l_strBranchCode);
    
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h  
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
    
            // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h22�F�����J�݁h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.ACCOUNT_OPEN);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h  
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
    
            //����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
    
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            
            // ������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
    
            //������ԊǗ�
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException
            
            log.exiting(STR_METHOD_NAME);
            return l_context; 
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(), STR_METHOD_NAME);             
        }
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@������ԊǗ�.OFFSET_TAG <BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG <BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 419C8E5E0293
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(STR_METHOD_NAME);     
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@������ԊǗ�.OFFSET_TAG <BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG <BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 419C8E5E0296
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
