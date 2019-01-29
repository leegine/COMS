head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransferCompleteUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o������UnitService�C���^�Z�v�^�N���X(WEB3AioCashTransferCompleteUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ���E (���u) �V�K�쐬 
                   2004/10/22 ���� (���u) ���r���[   
                   2004/12/09 ���E (���u) �c�Ή�                   
*/
package webbroker3.aio;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (���o������UnitService�C���^�Z�v�^)<BR>
 * ���o������UnitService�C���^�Z�v�^�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashTransferCompleteUnitServiceInterceptor implements Interceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransferCompleteUnitServiceInterceptor.class);
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|����.�T�[�r�X�̈���[0]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService��<BR>
     * ���e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *   �����P��.�،���Е��X�h�c�ɊY�����镔�X�̏،���ЃR�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     *   �����P��.�،���Е��X�h�c�ɊY�����镔�X�̕��X�R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1)��t���ԋ敪<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     * (*2)������t�g�����U�N�V���� <BR>
     * <BR>
     *   (*1)��t���ԋ敪�̎擾���@@<BR>
     *     �����P��.������� = 1001�F�o������ �̏ꍇ�A�h16�F�o���h<BR>
     *     �����P��.������� = 1002�F�������� �̏ꍇ�A�h14�F�����h<BR>
     * <BR>
     *   (*2)������t�g�����U�N�V�����̎擾���@@<BR>
     *     �����P��.������� = 1001�F�o������ �̏ꍇ�A�h08�F�o���h<BR>
     *     �����P��.������� = 1002�F�������� �̏ꍇ�A�h10�F�����h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@���������b�N����B <BR>
�@@   * �|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)<BR>
     * ���R�[������B <BR>
     * �������͒����P�ʂ��ҏW�B<BR>
     * <BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 41416CF0007F
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("l_serviceParams is null or l_serviceParams length is 0");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 
        //�@@�|����.�T�[�r�X�̈���[0]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B 
        //�@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B 
        AioOrderUnit l_aioOrderUnit =
            (AioOrderUnit) l_serviceParams[0];
        log.debug("l_serviceParams[0].getClass().getName() = " + l_serviceParams[0].getClass().getName());
        log.debug("l_aioOrderUnit.getClass().getName() = " + l_aioOrderUnit.getClass().getName());
        
        long l_lngAccountId = l_aioOrderUnit.getAccountId();  
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �����P��.�،���Е��X�h�c�ɊY�����镔�X�̏،���ЃR�[�h  
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = �����P��.�،���Е��X�h�c�ɊY�����镔�X�̕��X�R�[�h
            l_context.setBranchCode(l_strBranchCode);
            
            //�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1)��t���ԋ敪 
            //(*1)��t���ԋ敪�̎擾���@@ 
            //�����P��.������� = 1001�F�o������ �̏ꍇ�A�h16�F�o���h 
            //�����P��.������� = 1002�F�������� �̏ꍇ�A�h14�F�����h 
            if(OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnit.getOrderType()))
            {
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.PAYMENT);
            }
            else if(OrderTypeEnum.CASH_IN.equals(l_aioOrderUnit.getOrderType()))
            {
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.RECIEPT);
            }
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.PAYMENT);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*2)������t�g�����U�N�V����
            //(*2)������t�g�����U�N�V�����̎擾���@@ 
            //�����P��.������� = 1001�F�o������ �̏ꍇ�A�h08�F�o���h 
            //�����P��.������� = 1002�F�������� �̏ꍇ�A�h10�F����
            if(OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.PAYMENT);
            }
            else if(OrderTypeEnum.CASH_IN.equals(l_aioOrderUnit.getOrderType()))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CASH_IN);
            }
            
           //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B 
           // �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            // �Q�j�@@��t�����A���t���[�����Z�b�g����B 
               // �@@�|������ԊǗ�.setTimestamp()���R�[������B 
               WEB3GentradeTradingTimeManagement.setTimestamp();
               
            //============remain zhou-yong NO.1 begin ============   
               
            //�R�j�@@���������b�N����B 
            //  �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
            //   �������͒����P�ʂ��ҏW�B
               WEB3GentradeAccountManager l_accountManager =
                   (WEB3GentradeAccountManager) l_accMgr;

               l_accountManager.lockAccount(
                   l_strInstitutionCode,
                   l_strBranchCode,
                   l_mainAccount.getAccountCode());
               
            //============remain zhou-yong NO.1 begin ============
        }       
        catch(NotFoundException l_ex)
        {
            log.error("no find AccountId", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()", l_ex);
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
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 41416CF0008F
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object l_context, Object l_returnValue)";
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
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 41416CF000AE
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object l_obj, Throwable l_throwable)";
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
