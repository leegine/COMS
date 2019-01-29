head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToEquityOrderReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�C���^�Z�v�^(WEB3AdminToEquityOrderReferenceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06�@@������(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.admintriggerorder.define.WEB3AdminToOrderAccProductDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�C���^�Z�v�^)<BR>
 * �g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�C���^�Z�v�^<BR>
 * 
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminToEquityOrderReferenceServiceInterceptor implements Interceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToEquityOrderReferenceServiceInterceptor.class);

    
    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3AdminToEquityOrderReferenceServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR> 
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR> 
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g��<BR> 
     * �@@�@@�@@�v���p�e�B���Z�b�g����B<BR> 
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR> 
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR> 
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<BR> 
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h<BR> 
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR> 
     * �@@����J�����_�R���e�L�X�g.������t���i = �h00�FDEFAULT�h<BR> 
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h<BR> 
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� <BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�Ǘ��҂̏ꍇ�͊Ǘ��҃I�u�W�F�N�g����擾����
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //����J�����_�����p����R���e�L�X�g�𐶐�����B
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �،���ЃR�[�h
            l_context.setInstitutionCode(l_admin.getInstitutionCode());
            //����J�����_�R���e�L�X�g.���X�R�[�h = ���X�R�[�h
            l_context.setBranchCode(l_admin.getBranchCode());
            //�s��R�[�h = null
            l_context.setMarketCode(null);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.������t���i = �h00�FDEFAULT�h
            l_context.setOrderAcceptProduct(WEB3AdminToOrderAccProductDef.DEFAULT);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
         
            //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�
            //  ������ԃR���e�L�X�g���Z�b�g����B
            //  �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);        
            //��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }    
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
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
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

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
     * �T�[�r�X���\�b�h��O���ɃR�[�������B<BR> 
     * ����J�����_�R���e�L�X�g�N���A�����B<BR> 
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR> 
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR> 
     * ������ԊǗ�.OFFSET_TAG<BR> 
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

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
