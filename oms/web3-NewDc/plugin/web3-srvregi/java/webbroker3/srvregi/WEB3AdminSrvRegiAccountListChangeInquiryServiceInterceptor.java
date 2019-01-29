head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountListChangeInquiryServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�T�[�r�X�C���^�Z�v�^(WEB3AdminSrvRegiAccountListChangeInquiryServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/01 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi;

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
import webbroker3.util.WEB3LogUtility;


/**
 * �T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�T�[�r�X�C���^�Z�v�^<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�T�[�r�X�C���^�Z�v�^�@@�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountListChangeInquiryServiceInterceptor implements Interceptor 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountListChangeInquiryServiceInterceptor.class);
    
    /**
     * @@roseuid 416F50E502CE
     */
    public WEB3AdminSrvRegiAccountListChangeInquiryServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���������<BR>
     * �R���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h27�F�T�[�r�X���p�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h21�F�T�[�r�X���p�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h00�FDEFAULT�h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * @@return Object
     * @@roseuid 411CBDB90388
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method , Object[] )";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                    OpLoginSecurityService.class);
            
            //�،���ЃR�[�h
            String l_strInstitutionCode = null;     
            
            // ���X�R�[�h
            String l_strBranchCode = null;   
            
            if (l_opLoginSec.isAccountIdSet())
            {
                //�����R�[�h
                long l_lngAccountId = l_opLoginSec.getAccountId(); //throw NotFoundException
                
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                AccountManager l_accountMgr = l_finApp.getAccountManager();
         
                MainAccount l_account = l_accountMgr.getMainAccount(l_lngAccountId);
                l_strInstitutionCode = l_account.getInstitution().getInstitutionCode();
                l_strBranchCode = l_account.getBranch().getBranchCode();
            }
            else
            {
                //����ID��null���ǂ����ŁA�Ǘ��҂̏ꍇ
                WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
                l_strInstitutionCode = l_administrator.getInstitutionCode();
                l_strBranchCode = l_administrator.getBranchCode();
            }

            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
                
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW 
            l_context.setInstitutionCode(l_strInstitutionCode);
    
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW  
            l_context.setBranchCode(l_strBranchCode);
    
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h  
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
    
            // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h27�F�T�[�r�X���p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SRVREGI);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h  
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
    
            //����J�����_�R���e�L�X�g.������t���i = �h21�F�T�[�r�X���p�h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.SRV_REGI);
    
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h00�FDEFAULT�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            
            // ������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
    
            //������ԊǗ�
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException
            
            log.exiting(STR_METHOD_NAME);
            return l_context; 
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,  STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(), STR_METHOD_NAME);           
        }

    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@������ԊǗ�.OFFSET_TAG <BR>
     * �@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * @@roseuid 411CBDB9038B
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object , Object ) ";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);      

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

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
     * �@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * @@roseuid 411CBDB90398
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object , Throwable )";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);      

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);    
    }
}
@
