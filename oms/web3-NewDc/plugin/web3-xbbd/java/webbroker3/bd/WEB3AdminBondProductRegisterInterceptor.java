head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegisterInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Җ����o�^�C���^�Z�v�^(WEB3AdminBondProductRegisterInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 ����(���u) �V�K�쐬         
*/

package webbroker3.bd;

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
 * ���Ǘ��Җ����o�^�C���^�Z�v�^
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminBondProductRegisterInterceptor implements Interceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductRegisterInterceptor.class);
    /**
     * @@roseuid 44E352420242
     */
    public WEB3AdminBondProductRegisterInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     *�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW 
     * <BR>
     *�@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW 
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = "0:DEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = "0:DEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "25:��"<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = "28:��"<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "00�FDEFAULT"
     * <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )��<BR>
     * �Ď�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B
     * @@param l_method - l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h
     * @@param l_serviceParam - l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����
     * @@return java.lang.Object
     * @@roseuid 44D6E62500EA
     */
    public java.lang.Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
                      
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        
        // ���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
        // ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����        
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();  
        try
        {
            WEB3Administrator l_administrator = 
                WEB3Administrator.getInstanceFromLoginInfo();
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
            String l_strBranchCode = l_administrator.getBranchCode(); 
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode); 
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW 
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "25:��" 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
            
            //�@@����J�����_�R���e�L�X�g.������t���i = "28:��" 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.BOND);
            
            //�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "00:DEFAULT" 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);

            //�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B  
            //�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH  
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //�Q�j�@@��t�����A���t���[�����Z�b�g����B  
            //�@@�|������ԊǗ�.setTimestamp()���R�[������B 
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ ",l_ex);
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
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_returnValue - l_returnValue - (onCall���^�[���l)<BR>
     * onCall���^�[���l
     * @@param l_context - l_context - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 44D6E6250119
     */
    public void onReturn(Object l_returnValue, Object l_context) 
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
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l
     * @@param l_throwable - l_throwable - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 44D6E6250138
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
