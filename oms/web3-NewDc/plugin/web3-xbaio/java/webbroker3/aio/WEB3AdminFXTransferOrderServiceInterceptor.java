head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.33.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXTransferOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�U�֒����A�b�v���[�h�T�[�r�X�C���^�Z�v�^(WEB3AdminFXTransferOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/24 �A����(���u) �V�K�쐬
                 : 2006/03/07 �A���� (���u) �d�l�ύX�E���f��515
*/
package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX�U�֒����A�b�v���[�h�T�[�r�X�C���^�Z�v�^)<BR>
 * FX�U�֒����A�b�v���[�h�T�[�r�X�C���^�Z�v�^�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXTransferOrderServiceInterceptor implements Interceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferOrderServiceInterceptor.class);
    
    /**
     * @@roseuid 43F49D5D01A5
     */
    public WEB3AdminFXTransferOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>                                                       
     * <BR>                                                                                              
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>                                               
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>                                           
     * <BR>                                                                                              
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>                                           
     * �@@?���O�C����񂩂�Ǘ��҃I�u�W�F�N�g���擾����B <BR>                                            
     * �@@?�Ǘ��҃I�u�W�F�N�g�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>                
     * <BR>                                                                                              
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()�̖߂�l <BR>               
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.get���X�R�[�h()�̖߂�l <BR>                       
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>                                       
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h17�F�A�b�v���[�h�i�Ǘ��ҁj�h <BR>                                     
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h03�F�U�ցi�����j�h <BR>                                 
     * �@@����J�����_�R���e�L�X�g.������t���i = �h23�F�ב֕ۏ؋��h <BR>                                 
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h09�F�U�ցh<BR>                            
     * <BR>                                                                                              
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR> 
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>                                             
     * <BR>                                                                                              
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>                                                     
     * �@@?������ԊǗ�.setTimestamp()���R�[������B <BR>                                                 
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * @@return java.lang.Object
     * @@roseuid 43E03BB1024F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 
        // �@@�|���O�C����񂩂�Ǘ��҃I�u�W�F�N�g���擾����B 
        // �@@�|�Ǘ��҃I�u�W�F�N�g�̓��e��������ԃR���e�L�X�g��
        // �@@�v���p�e�B���Z�b�g����B
        try
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()�̖߂�l
            l_context.setInstitutionCode(l_admin.getInstitutionCode());
            
            // ����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.get���X�R�[�h()�̖߂�l
            l_context.setBranchCode(l_admin.getBranchCode());
            
            // ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h17�F�A�b�v���[�h�i�Ǘ��ҁj�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD);
            
            // ����J�����_�R���e�L�X�g.���i�R�[�h = �h03�F�U�ցi�����j�h
            l_context.setProductCode(WEB3ProductCodeDef.TRANSFER_RECIEPT);
            
            // ����J�����_�R���e�L�X�g.������t���i = �h23�F�ב֕ۏ؋��h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.EXCHANGE_GUARANTEE);
            
            // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h09�F�U�ցh
            // (�T�[�r�X���p�\��)(�ב֕ۏ؋������J��)�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.TRANSFER);
            
            //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�
            //������ԃR���e�L�X�g���Z�b�g����B
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            
            // �Q�j�@@��t�����A���t���[�����Z�b�g����B 
            // �@@�|������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
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
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 43E03BB1026E
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
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
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
     * @@roseuid 43E03BA600F7
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
