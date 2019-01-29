head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�������������T�[�r�X�C���^�Z�v�^(WEB3ToSuccEquityChangeOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
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
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;


/**
 * (�i�A���j�������������T�[�r�X�C���^�Z�v�^)<BR>
 * �i�A���j�������������T�[�r�X�C���^�Z�v�^�B<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3ToSuccEquityChangeOrderServiceInterceptor implements Interceptor 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityChangeOrderServiceInterceptor.class);
    
    /**
     * @@roseuid 436ACB9F01D4
     */
    public WEB3ToSuccEquityChangeOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^���u�i�A���j�����������������������N�G�X�g�v<BR>
     *     �̏ꍇ�̂݁A���������b�N����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g���Ĕ��ʂ���B<BR>
     * <BR>
     * �@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h,<BR>
     *   ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@��������OpLoginSecurityService���ҏW�B<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|OpLoginSecurityService�̓��e���<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * �@@�i*1�j�u�i�A���j�����������������m�F���N�G�X�g�v�܂��́A<BR>
     * �@@�@@�@@�@@�u�i�A���j�����������������������N�G�X�g�v<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = ���N�G�X�g.ID<BR>
     *    �ɊY������\�񒍕��P��.�s��ID�ɊY������s��.�s��R�[�h<BR>
     * �@@�@@���\�񒍕��P�ʂ́A<BR>
     *    �A�������}�l�[�W��.get�����\�񒍕��P��(���N�G�X�g.ID)�ɂ��擾�B<BR>
     *   ����J�����_�R���e�L�X�g.�����R�[�h = �hDEFAULT�h<BR>  
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�����h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - ���\�b�h<BR>
     * @@param l_serviceParam - (�T�[�r�X�̈���)<BR>
     * �T�[�r�X�̃��\�b�h�ɓn���������B<BR>
     * ���������T�[�r�X�̏ꍇ�A�����������N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@return Object
     * @@roseuid 433BCE9B0269
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        //check parameter
        if (l_serviceParam == null 
            && l_serviceParam.length == 0)
        {
            log.error("�p�����[�^�l�s���B");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);   
        }
        
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
    
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            MainAccount l_mainAccount = 
                l_accountManager.getMainAccount(l_lngAccountId);//NotFoundException

            // �P�j�@@���N�G�X�g�f�[�^�̌^���u�i�A���j�����������������������N�G�X�g�v
            //     �̏ꍇ�̂݁A���������b�N����B
            // �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g���Ĕ��ʂ���B
            // 
            // �@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h,
            //   ���X�R�[�h, �����R�[�h)���R�[������B
            // �@@��������OpLoginSecurityService���ҏW�B

            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            String l_strAccountCode = l_mainAccount.getAccountCode();
            
            long l_lngOrderId = 0;
            if (l_serviceParam[0] instanceof WEB3SuccEquityChangeCompleteRequest)
            {
                WEB3SuccEquityChangeCompleteRequest l_completeRequest = 
                    (WEB3SuccEquityChangeCompleteRequest)l_serviceParam[0];
                l_lngOrderId = Long.parseLong(l_completeRequest.id);
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode);
            }
            else if (l_serviceParam[0] instanceof WEB3SuccEquityChangeConfirmRequest)
            {
                WEB3SuccEquityChangeConfirmRequest l_confirmRequest = 
                    (WEB3SuccEquityChangeConfirmRequest)l_serviceParam[0];
                l_lngOrderId = Long.parseLong(l_confirmRequest.id);
            }
            else
            {
                log.debug("�p�����[�^�^�C�v�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
                   
            //�Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B        
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = ���N�G�X�g.ID�ɊY������\�񒍕��P��.�s��ID�ɊY������s��.�s��R�[�h
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = 
                l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);//NotFoundException,WEB3BaseException
            WEB3GentradeMarket l_market = l_orderUnit.getMarket();
            if (l_market != null)
            {
                l_context.setMarketCode(l_market.getMarketCode());
            }
            //����J�����_�R���e�L�X�g.�����R�[�h = �hDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            
            //����J�����_�R���e�L�X�g.������t���i = �h�����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = ����
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
            
            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        
            //������ԊǗ�
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException
            log.exiting(STR_METHOD_NAME);
            return l_context;        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);                
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
    }
    
    /**
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * �i�A���j�������������T�[�r�X�I�����ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 433BCE9B0288
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
         final String STR_METHOD_NAME = " onReturn(Object, Object)";
         log.entering(STR_METHOD_NAME);

         //������ԊǗ�.TIMESTAMP_TAG
         ThreadLocalSystemAttributesRegistry.setAttribute(
             WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
             null);
        
         //������ԊǗ�.OFFSET_TAG
         ThreadLocalSystemAttributesRegistry.setAttribute(
             WEB3GentradeTradingTimeManagement.OFFSET_TAG,
             null);
            
         //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
         ThreadLocalSystemAttributesRegistry.setAttribute(
             WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
             null);

         log.exiting(STR_METHOD_NAME);
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
     * @@roseuid 433BCE9B02A7
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
