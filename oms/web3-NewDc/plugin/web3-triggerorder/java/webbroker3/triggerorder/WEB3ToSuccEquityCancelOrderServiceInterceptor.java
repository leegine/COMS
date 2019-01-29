head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityCancelOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j������������T�[�r�X�C���^�Z�v�^(WEB3ToSuccEquityCancelOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  鰊](���u) �V�K�쐬
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�A���j������������T�[�r�X�C���^�Z�v�^)<BR>
 *
 * @@ author 鰊] <BR>
 * @@ version 1.0 <BR>
 */  
public class WEB3ToSuccEquityCancelOrderServiceInterceptor implements Interceptor 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityCancelOrderServiceInterceptor.class);

    /**
     * @@roseuid 436ACB9E0280
     */
    public WEB3ToSuccEquityCancelOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^���u�i�A���j����������������������N�G�X�g�v��<BR>
     * �ꍇ�̂݁A���������b�N����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g���Ĕ��ʂ���B<BR>
     * <BR>
     * �@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)<BR>
     * ���R�[������B<BR>
     * �@@��������OpLoginSecurityService���ҏW�B<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|OpLoginSecurityService�̓��e���<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * �@@�i*1�j�u�i�A���j����������������m�F���N�G�X�g�v�܂��́A<BR>
     * �@@�@@�@@�@@�u�i�A���j����������������������N�G�X�g�v<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = ���N�G�X�g.ID�ɊY������\��<BR>
     * �����P��.�s��ID�ɊY������s��.�s��R�[�h<BR>
     * �@@�@@���\�񒍕��P�ʂ́A�A�������}�l�[�W��.get�����\�񒍕��P��<BR>
     * (���N�G�X�g.ID)�ɂ��擾�B<BR>
     *   ����J�����_�R���e�L�X�g.�����R�[�h = �hDEFAULT�h<BR>  
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h����h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * ������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - (���\�b�h)<BR>
     * ���\�b�h<BR>
     * @@param l_serviceParam - (�T�[�r�X�̈���)<BR>
     * �T�[�r�X�̃��\�b�h�ɓn���������B<BR>
     * ���������T�[�r�X�̏ꍇ�A�����������N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@return Object
     * @@roseuid 4340CD3300DF
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);  
        
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.debug("�T�[�r�X�̈��� = null or length = 0");
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�T�[�r�X�̈��� = null or length = 0");
        }
        
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        WEB3GentradeMainAccount l_account = null;
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
                
        long l_lngAccountId = l_opLoginSec.getAccountId();
        try
        {
            l_account =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_account.getInstitution().getInstitutionCode();
            l_strBranchCode = l_account.getBranch().getBranchCode();
            
            Object l_request = l_serviceParam[0];
            String l_strOrderId = null;
    
            if (l_request instanceof WEB3SuccEquityCancelCompleteRequest)
            {
                // �P�j�@@���N�G�X�g�f�[�^�̌^���u�i�A���j����������������������N�G�X�g�v��
                //     �@@�ꍇ�̂݁A���������b�N����B
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_account.getAccountCode());
                
                // �i�A���j����������������������N�G�X�g
                WEB3SuccEquityCancelCompleteRequest l_completeRequest =
                    (WEB3SuccEquityCancelCompleteRequest)l_request;
                l_strOrderId = l_completeRequest.id;
            }
            else if (l_request instanceof WEB3SuccEquityCancelConfirmRequest)
            {
                // �i�A���j����������������m�F���N�G�X�g
                WEB3SuccEquityCancelConfirmRequest l_confirmRequest =
                    (WEB3SuccEquityCancelConfirmRequest)l_request;
                l_strOrderId = l_confirmRequest.id;
            }
            else
            {
                String l_strErrorMsg = "request !=�i�A���j����������������������N�G�X�g and " +
                    "request !=�i�A���j����������������m�F���N�G�X�g";
                log.debug(l_strErrorMsg);
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMsg);
            }
            
            String l_strMarketCode = null;
            WEB3ToSuccOrderManagerImpl l_managerImpl =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnitImpl = null;    
            
            l_orderUnitImpl = l_managerImpl.getReserveEqtypeOrderUnit(Long.parseLong(l_strOrderId));
                                                                       
            Market l_market = l_orderUnitImpl.getMarket();          
            if (l_market != null)
            {
                l_strMarketCode = l_market.getMarketCode();                   
            }         
            // �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B                               
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            // ����J�����_�R���e�L�X�g.�s��R�[�h = ���N�G�X�g.ID�ɊY������\��
            //�����P��.�s��ID�ɊY������s��.�s��R�[�h
            l_context.setMarketCode(l_strMarketCode);
            //����J�����_�R���e�L�X�g.�����R�[�h = �hDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            //����J�����_�R���e�L�X�g.������t���i = �h�����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
            // ������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);         
            // �R�j�@@��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            log.exiting(STR_METHOD_NAME);
        
            return l_context;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
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
    }
    
    /**
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * �i�A���j������������T�[�r�X�I�����ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 4340CD3300E2
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
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * ��O�������ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 4340CD3300F1
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
