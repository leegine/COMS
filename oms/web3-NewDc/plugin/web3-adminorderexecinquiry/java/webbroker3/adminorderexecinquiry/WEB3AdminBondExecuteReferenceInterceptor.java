head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondExecuteReferenceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������Ɖ�C���^�Z�v�^(WEB3AdminBondExecuteReferenceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q (���u) �V�K�쐬  
*/

package webbroker3.adminorderexecinquiry;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
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
 * (���Ǘ��Ғ������Ɖ�C���^�Z�v�^)<BR>
 * ���Ǘ��Ғ������Ɖ�C���^�Z�v�^�N���X<BR>
 * 
 * @@author �����q�i���u�j
 * @@version 1.0
 */
public  class WEB3AdminBondExecuteReferenceInterceptor implements Interceptor 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteReferenceInterceptor.class);
    
    /**
     * @@roseuid 44E335A30271
     */
    public WEB3AdminBondExecuteReferenceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR> 
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR> 
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR> 
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR> 
     * �@@�|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR> 
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = "0:DEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = "0:DEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "25:��"<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = "28:��"<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "07�F�Ɖ�"<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR> 
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR> 
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 44C6E6030365
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
                
        //�P�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����
        try
        {
            //�Ǘ���
            WEB3Administrator l_administrator = 
                WEB3Administrator.getInstanceFromLoginInfo();
            
            //���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
            //������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����
            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();
            
            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
            
            //���X�R�[�h���擾����
            String l_strBranchCode = l_administrator.getBranchCode();
    
            //����J�����_�R���e�L�X�g.set�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //����J�����_�R���e�L�X�g.set���X�R�[�h
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.�����R�[�h = "0:DEFAULT" 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = "25:��" 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
            
            //����J�����_�R���e�L�X�g.������t���i = "28:��" 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.BOND);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "07�F�Ɖ�" 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g���� 
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //�Q�j��t�����A���t���[�����Z�b�g����
            //������ԊǗ�.setTimestamp()���R�[������   
            WEB3GentradeTradingTimeManagement.setTimestamp();

            log.exiting(STR_METHOD_NAME);
            return null;
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR> 
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR> 
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR> 
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR> 
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l
     * @@param l_returnValue - (�r�W�l�X���\�b�h���^�[���l)<BR>
     * �r�W�l�X���\�b�h���^�[���l
     * @@roseuid 44C6E6150336
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
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR> 
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR> 
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR> 
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR> 
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l
     * @@param l_throwable - (�r�W�l�X���\�b�h���^�[���l)<BR>
     * �r�W�l�X���\�b�h���^�[���l
     * @@roseuid 44C6E62F01DF
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    { 
        String STR_METHOD_NAME = "onThrowable(Object l_obj, Throwable l_throwable)";
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
