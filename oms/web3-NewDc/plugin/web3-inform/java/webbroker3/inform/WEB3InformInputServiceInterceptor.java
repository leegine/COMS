head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�����̓T�[�r�X�C���^�Z�v�^(WEB3InformInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 䈋� (���u) �V�K�쐬
*/
package webbroker3.inform;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.inform.message.WEB3InformCompleteRequest;
import webbroker3.inform.message.WEB3InformConfirmRequest;
import webbroker3.inform.message.WEB3InformInputRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A�����̓T�[�r�X�C���^�Z�v�^)<BR>
 * �A�����̓T�[�r�X�C���^�Z�v�^�N���X
 */
public class WEB3InformInputServiceInterceptor implements Interceptor
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformInputServiceInterceptor.class);

    /**
     * @@roseuid 41EE642D01B5
     */
    public WEB3InformInputServiceInterceptor()
    {

    }

    /**
     *�T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     *<BR>
     *����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     *�ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     *<BR>
     *�����O�C���O�ł��A���N�G�X�g�f�[�^���A�����̓��N�G�X�g�̏ꍇ�́A���������ɏ������I������B<BR>
     *<BR>
     *�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     *�@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     *�@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     *<BR>
     *�����O�C����̏����̏ꍇ<BR>
     *�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     *�@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     *<BR>
     *�����O�C���O�̏����̏ꍇ<BR>
     *   �����N�G�X�g�f�[�^���A�A���m�F���N�G�X�g�������͘A���������N�G�X�g�̏ꍇ<BR>
     *�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = ���N�G�X�g�f�[�^.�A�����.�،���ЃR�[�h<BR>
     *<BR>
     *������<BR>
     *�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     *�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�F���̑��h <BR>
     *�@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     *�@@����J�����_�R���e�L�X�g.������t���i = �h26�F�A���Ǘ��h <BR>
     *�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h <BR>
     *<BR>
     *�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     *�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     *<BR>
     *�Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     *�@@�|������ԊǗ�.setTimestamp()���R�[������B <BR> 
     * �����O�C����̏����̏ꍇ�̂݁A���{����B<BR>    
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����
     * 
     * @@return Object
     * @@roseuid 41AEDE8E0032
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 
        //�@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B 
        //�@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B 
        //
        if (l_serviceParam == null)
        {
            log.error("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strInstitutionCode = "";
        String l_strBranchCode = "";
        boolean l_isLogin = true;
        //Collection�^�C�v�̃p�����[�^Size�͂O�ł��Ȃ�
        //�p�����[�^�^�C�v�s��
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        if (l_serviceParam[0] instanceof  WEB3InformInputRequest)
        {
            //return null;
        }
        else if (l_serviceParam[0] instanceof  WEB3InformConfirmRequest)
        {
            WEB3InformConfirmRequest l_request = (WEB3InformConfirmRequest)l_serviceParam[0];
            l_strInstitutionCode = l_request.informInfoUnit.institutionCode;
            l_strBranchCode = l_request.informInfoUnit.branchCode;
        }
        else if (l_serviceParam[0] instanceof  WEB3InformCompleteRequest)
        {
            WEB3InformCompleteRequest l_request = (WEB3InformCompleteRequest)l_serviceParam[0];
            l_strInstitutionCode = l_request.informInfoUnit.institutionCode;
            l_strBranchCode = l_request.informInfoUnit.branchCode;
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s��");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        AccountManager l_accMgr = l_finApp.getAccountManager();


        try
        {
            long l_lngLoginId = l_opLoginSec.getLoginId();       
                
            if (l_lngLoginId == 0)
            {
                if (l_serviceParam[0] instanceof  WEB3InformInputRequest)
                {
                    return null;
                }
                log.info("�z�[���y�[�W����A�N�Z�X�̏ꍇ"); 
                l_isLogin = false;
                l_context.setInstitutionCode(l_strInstitutionCode);              
            } 
            else
            {      
                long l_lngAccountId = l_opLoginSec.getAccountId();
                MainAccount l_mainAccount = null;
                try
                {       
                    l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);     
                }
                catch(NotFoundException l_ex)
                {
                    log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex
                        );
                }
                //�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW         
                l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
                //�@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW 
                l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());   
            }
        }
        catch (IllegalSessionStateException l_ex)
        {          
            if (l_serviceParam[0] instanceof  WEB3InformInputRequest)
            {
                return null;
            }
            log.info("�z�[���y�[�W����A�N�Z�X�̏ꍇ");
            l_isLogin = false;   
            l_context.setInstitutionCode(l_strInstitutionCode);
        }


        //�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�F���̑��h 
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
        //�@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        //�@@����J�����_�R���e�L�X�g.������t���i = �h26�F�A���Ǘ��h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.INFORM_MANAGEMENT); 
        //�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE); 
        //
        //�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B 
        //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
        //
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        //�Q�j�@@��t�����A���t���[�����Z�b�g����B 
        //�@@�|������ԊǗ�.setTimestamp()���R�[������B 
        //��t�����A���t���[�����Z�b�g����
        //�����O�C����̏����̏ꍇ�̂݁A���{����B
        if (l_isLogin)
        {
            try
            {
                WEB3GentradeTradingTimeManagement.setTimestamp();
            }
            catch(WEB3SystemLayerException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                    );
            }
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
     * onCall���^�[���l
     * 
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41AEDE8E0051
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn";
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
     * ����J�����_�R���e�L�X�g�N���A�����B<BR> 
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l
     * 
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g
     * @@roseuid 41AEDE8E0070
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable";
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
