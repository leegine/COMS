head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n���̓T�[�r�X�C���^�Z�v�^(WEB3MarginSwapMarginInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ������ (���u) �V�K�쐬
Revesion History : 2004/12/07 �X��   (SRA) �c�Č��Ή�
*/

package webbroker3.equity;

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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.message.WEB3MarginSwapMarginInputRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����������n���̓T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �M�p����������n���̓T�[�r�X�C���^�Z�v�^�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginInputServiceInterceptor implements Interceptor 
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginSwapMarginInputServiceInterceptor.class);
    
    /**
     * (�R���X�g���N�^)�B<BR>
     * @@roseuid 4142B32C026A
     */
    public WEB3MarginSwapMarginInputServiceInterceptor() 
    {
     
    }
    
    /**
     * (onCall)�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁA<BR>
     *     OpLoginSecurityService�̓��e��������ԃR���e�L�X�g��<BR>
     * �@@�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *       OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h19�F�����E���n�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h04�F�����E���n�h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - �T�[�r�X���\�b�h����<BR>
     * @@return Object<BR>
     * @@roseuid 407CA52C02EA
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        //�M�p����������n���̓��N�G�X�g
        Object l_request = l_serviceParams[0];
        if (!(l_request instanceof WEB3MarginSwapMarginInputRequest))
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "request !=�M�p����������n���̓��N�G�X�g"));
            return null;
        }

        long l_lngAccountId;                    // �����R�[�h
        String l_strInstitutionCode = null;     // �،���ЃR�[�h
        String l_strBranchCode = null;          // ���X�R�[�h
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�Z�L�����e�B�T�[�r�X���擾
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        try
        { 
            // �����R�[�h���擾
            l_lngAccountId = l_opLoginSec.getAccountId();
            //FinApp�T�[�r�X���擾
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            // �،���ЃR�[�h
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            // ���X�R�[�h���擾
            l_strBranchCode = l_acc.getBranch().getBranchCode();
        }
        catch (NotFoundException nfe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }
                    
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_strInstitutionCode);
        // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_strBranchCode);
        // ����J�����_�R���e�L�X�g.�s��R�[�h = null
        l_context.setMarketCode(null);
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h19�F�����E���n�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
        // ����J�����_�R���e�L�X�g.�����R�[�h = 0 �F DEFAULT
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // ����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h04�F�����E���n�h
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
        
        // ������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);         

        //�Q�j�@@��t�����A���t���[�����Z�b�g����                    
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);                  
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (onReturn)�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 407CA52C0309
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
     * (onThrowable)�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 407CA52C0319
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