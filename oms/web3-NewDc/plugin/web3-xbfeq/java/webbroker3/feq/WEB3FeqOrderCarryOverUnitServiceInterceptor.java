head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCarryOverUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������J�zUnitService�C���^�Z�v�^(WEB3FeqOrderCarryOverUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[ 
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�O�����������J�zUnitService�C���^�Z�v�^)<BR>
 * �O�����������J�zUnitService�C���^�Z�v�^
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqOrderCarryOverUnitServiceInterceptor implements Interceptor 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderCarryOverUnitServiceInterceptor.class);
    
    /**
     * @@roseuid 42D0D7C7036B
     */
    public WEB3FeqOrderCarryOverUnitServiceInterceptor() 
    {
     
    }
    
    /**
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]���ڋq�I�u�W�F�N�g�ɃL���X�g���A<BR>
     * �@@�@@�ȉ��̒ʂ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �ڋq.�،���ЃR�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �ڋq.���X�R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h10�F�O�������h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h04�F�O�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�Ɖ�h<BR>
     * <BR>
     * �@@�������J�z�́A�o�b�`���^�V�X�e���ً}��~���̏ꍇ�̂ݎ��s�s�̂��߁A<BR>
     * �@@�@@������t�g�����U�N�V�����ɂ́h�Ɖ�h��ݒ肷��B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )��<BR>
     * �Ď�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X�̈����z��
     * @@return Object
     * @@roseuid 42B8A3F80078
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);         
        }
        
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
        
        try
        {
            // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            //�|�T�[�r�X�̈���[0]���ڋq�I�u�W�F�N�g�ɃL���X�g���A
            //�ȉ��̒ʂ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B  
            
            MainAccount l_mainAccount = (MainAccount)l_serviceParams[0];            
            
            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();
            
            //���X�R�[�h���擾����
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
             
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �ڋq.�،���ЃR�[�h  
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = �ڋq.���X�R�[�h  
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h = null
            l_context.setMarketCode(null);            
            //����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�O�������h 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            //����J�����_�R���e�L�X�g.������t���i = �h�O�����h 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�Ɖ�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�
            //������ԃR���e�L�X�g���Z�b�g���� 
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //�Q�j��t�����A���t���[�����Z�b�g����
            //������ԊǗ�.setTimestamp()���R�[������  
            WEB3GentradeTradingTimeManagement.setTimestamp();        
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp", l_ex);
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
     * @@param l_context - onCall�ԋp�l
     * @@param l_returnValue - �T�[�r�X���\�b�h�ԋp�l
     * @@roseuid 42B8A3F800A6
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
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
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - onCall�ԋp�l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 42B8A3F800B6
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
