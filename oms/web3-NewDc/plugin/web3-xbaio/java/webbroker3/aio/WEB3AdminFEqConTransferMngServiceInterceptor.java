head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.21.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFEqConTransferMngServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���U�֊Ǘ��T�[�r�X�C���^�Z�v�^(WEB3AdminFEqConTransferMngServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/22 ���E (���u) �V�K�쐬
*/
package webbroker3.aio;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
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
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O���U�֊Ǘ��T�[�r�X�C���^�Z�v�^)<BR>
 * �O���U�֊Ǘ��T�[�r�X�C���^�Z�v�^�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConTransferMngServiceInterceptor implements Interceptor 
{
    
    /**
     * @@roseuid 423565180196
     */
    public WEB3AdminFEqConTransferMngServiceInterceptor() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConTransferMngServiceInterceptor.class);
    
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|���O�C����񂩂�Ǘ��҃I�u�W�F�N�g���擾����B <BR>
     * �@@�|�Ǘ��҃I�u�W�F�N�g�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()�̖߂�l <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.get���X�R�[�h()�̖߂�l <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h24�F�O�������U�֘A�g�h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h27�F�O�������U�֘A�g�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     *       ������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B
     * @@param l_method - �T�[�r�X���\�b�h
     * @@param l_serviceParams - �T�[�r�X���\�b�h����
     * @@return java.lang.Object
     * @@roseuid 41E398700152
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        String l_strMethodName = "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(l_strMethodName);
                
        //�P�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����        
        try
        {
			WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
			
			//���N�G�X�g�f�[�^�̓��e�ƁA
			//�Ǘ��҃I�u�W�F�N�g�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
			WEB3GentradeTradingClendarContext l_context =
				new WEB3GentradeTradingClendarContext();
			
			//����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()�̖߂�l
			l_context.setInstitutionCode(l_admin.getInstitutionCode());
			//����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.get���X�R�[�h()�̖߂�l
			l_context.setBranchCode(l_admin.getBranchCode());
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h24�F�O�������U�֘A�g�h 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FEQ_CON);
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.������t���i = �h27�F�O�������U�֘A�g�h 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FEQ_CON);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�h07�F�Ɖ�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g���� 
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
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
                                  
        log.exiting(l_strMethodName);
        
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
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41E398700172
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
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 41E398700191
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
