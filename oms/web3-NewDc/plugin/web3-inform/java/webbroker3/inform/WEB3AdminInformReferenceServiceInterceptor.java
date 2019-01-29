head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A����񌟍��T�[�r�X�C���^�Z�v�^�N���X(WEB3AdminInformReferenceServiceInterceptor.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 ������(���u) �쐬
*/

package webbroker3.inform;

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
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.inform.message.WEB3AdminInformDetailRequest;
import webbroker3.inform.message.WEB3AdminInformDownLoadRequest;
import webbroker3.inform.message.WEB3AdminInformInputRequest;
import webbroker3.inform.message.WEB3AdminInformListRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A����񌟍��T�[�r�X�C���^�Z�v�^)<BR>
 * �A����񌟍��T�[�r�X�C���^�Z�v�^�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminInformReferenceServiceInterceptor implements Interceptor 
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformReferenceServiceInterceptor.class);
            
    /**
     * @@roseuid 41EE642E005D
     */
    public WEB3AdminInformReferenceServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g��<BR>
     * �v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�F���̑��h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h26�F�A���Ǘ��h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)
     * �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 41BD7F490075
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
       
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
        //�@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
        if (l_serviceParam[0] instanceof WEB3AdminInformInputRequest)
        {
            WEB3AdminInformInputRequest l_request = (WEB3AdminInformInputRequest)l_serviceParam[0];
            log.debug("���͉�ʁF" + l_request);
        }
        else if (l_serviceParam[0] instanceof WEB3AdminInformListRequest)
        {
            WEB3AdminInformListRequest l_request = (WEB3AdminInformListRequest)l_serviceParam[0];
            log.debug("�ꗗ��ʁF" + l_request);
        }
        else if (l_serviceParam[0] instanceof WEB3AdminInformDetailRequest)
        {
            WEB3AdminInformDetailRequest l_request = (WEB3AdminInformDetailRequest)l_serviceParam[0];
            log.debug("�ڍ׉�ʁF" + l_request);
        }
        else if (l_serviceParam[0] instanceof WEB3AdminInformDownLoadRequest)
        {
            WEB3AdminInformDownLoadRequest l_request = (WEB3AdminInformDownLoadRequest)l_serviceParam[0];
            log.debug("�_�E�����[�h�t�@@�C���F" + l_request);
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }

        // ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        try
        {
            //�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
            //  ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSec.getAccountId();
    
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager(); 
            MainAccount l_mainAccount = null;
            
            String l_strInstitutionCode = null;
            String l_strBranchCode = null;
            
            if(l_opLoginSec.isAccountIdSet())
            {
                //�Ǘ��҂�����ȊO���𔻒肵�A
                //MainAccount���擾
                l_mainAccount = l_accountManager.getMainAccount(l_opLoginSec.getAccountId());    //throw NotFoundException

                //�،���ЃR�[�h���擾
                l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();

                //���X�R�[�h���擾
                l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            }
            else
            {
                //����ID��null���ǂ����ŁA�Ǘ��҂̏ꍇ
                //�Ǘ��҂��擾
                WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

                //�،���ЃR�[�h���擾
                l_strInstitutionCode = l_administrator.getInstitutionCode();

                //���X�R�[�h���擾
                l_strBranchCode = l_administrator.getBranchCode();
            }
            log.debug("�،���ЃR�[�h:" + l_strInstitutionCode);
            log.debug("Institution Code: " + l_strInstitutionCode);
            
            // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
            l_context.setInstitutionCode(l_strInstitutionCode);
    
            // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
            l_context.setBranchCode(l_strBranchCode);
    
            // ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
    
            // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�F���̑��h <BR>
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
    
            // ����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
    
            // ����J�����_�R���e�L�X�g.������t���i = �h26�F�A���Ǘ��h <BR>
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.INFORM_MANAGEMENT);
    
            // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h <BR>
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
    
            //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
            //  �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);         

            //�Q�j��t�����A���t���[�����Z�b�g����B <BR>
            //�|������ԊǗ�.setTimestamp()���R�[������B<BR>
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�
        }
        catch (WEB3BaseException l_wbExp)
        {
            log.debug(STR_METHOD_NAME, l_wbExp);
            throw new WEB3BaseRuntimeException(
                l_wbExp.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbExp.getMessage(),
                l_wbExp);                  
        }
        catch (NotFoundException l_nfExp)
        {
            log.error(l_nfExp.getMessage(),l_nfExp);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_context;
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR> 
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l
     * 
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41BD7F490094
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
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
     * 
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g
     * @@roseuid 41BD7F4900B3
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
    }
}
@
