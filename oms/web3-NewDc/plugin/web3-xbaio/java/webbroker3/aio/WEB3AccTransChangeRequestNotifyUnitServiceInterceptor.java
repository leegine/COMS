head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.21.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeRequestNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : �U�֐����ʒmUnitService�C���^�Z�v�^(WEB3AccTransChangeRequestNotifyUnitServiceInterceptor)
 * Author Name      : Daiwa Institute of Research
 * Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                      2004/10/26 ���E(���u) ���r���[
                      2004/12/09 ���E (���u) �c�Ή�
*/

package webbroker3.aio;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import webbroker3.aio.data.HostTransferReceiptParams;
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
 * (�U�֐����ʒmUnitService�C���^�Z�v�^)<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestNotifyUnitServiceInterceptor
    implements Interceptor
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestNotifyUnitServiceInterceptor.class);

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|����.�T�[�r�X�̈���[0]��U�֓��͒ʒm�L���[Params�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|����.�T�[�r�X�̈���[1]��OrderTypeEnum�i������ʁj�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e<BR>
     *   ��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *        �U�֓��͒ʒm�L���[Params.�،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     *        �U�֓��͒ʒm�L���[Params.���X�R�[�h<BR> 
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1)��t���ԋ敪 <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = (*2)������t���i <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h09�F�U�ցh <BR>
     * <BR>
     * (*1)��t���ԋ敪�̎擾���@@ <BR>
     * �@@�@@������ʂ��ȉ��̂����ꂩ�̏ꍇ�A�h23�F�ב֕ۏ؋��h <BR>
     * �@@�@@�@@1011(�ב֕ۏ؋��U�֒���(�a���������ב֕ۏ؋�)) <BR>
     * �@@�@@�@@1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a�����)) <BR>
     * �@@�@@��L�ȊO�̏ꍇ�A �h13�F�؋����U�ցh <BR>
     * <BR>
     * (*2)������t���i�̎擾���@@ 
     *    ������� = 1005�F�U�֒����i�a�������M�p�ۏ؋��j �̏ꍇ�A�h09�F�M�p�ۏ؋��ւ̐U�ցh 
     *    ������� = 1006�F�U�֒����i�M�p�ۏ؋�����a����j �̏ꍇ�A�h10�F�M�p�ۏ؋�����̐U�ցh 
     *    ������� = 1007�F�U�֒����i�a������犔��؋����j �̏ꍇ�A�h12�F��OP�؋����ւ̐U�ցh 
     *    ������� = 1008�F�U�֒����i����؋�������a����j �̏ꍇ�A�h13�F��OP�؋�������̐U�ցh 
     * �@@������ʂ��ȉ��̂����ꂩ�̏ꍇ�A�h23�F�ב֕ۏ؋��h 
     * �@@�@@�@@1011(�ב֕ۏ؋��U�֒���(�a���������ב֕ۏ؋�)) 
     * �@@�@@�@@1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a�����)) 
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@���������b�N����B<BR> 
�@@    *  �|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)<BR>
     *  ���R�[������B<BR> 
     * �������͐U�֓��͒ʒm�L���[Params���ҏW�B<BR>
     * <BR> 
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 414167E200EC
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParams == null)
        {
            log.debug("__parameter_error__");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        
        if (l_serviceParams.length == 0)
        {
            log.debug("__parameter_error__");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        if (l_serviceParams.length < 2)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }  

        // ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����
        HostTransferReceiptParams l_hostTransferReceiptParams = null;
        OrderTypeEnum l_orderTypeEnum = null;
        
        if(l_serviceParams[0] instanceof HostTransferReceiptParams)
        {
            // ����.�T�[�r�X�̈���[0]��U�֓��͒ʒm�L���[Params�I�u�W�F�N�g�ɃL���X�g����B 
            l_hostTransferReceiptParams = 
                (HostTransferReceiptParams) l_serviceParams[0];
        }
        else
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if(l_serviceParams[1] instanceof OrderTypeEnum)
        {
            // ����.�T�[�r�X�̈���[1]��OrderTypeEnum�i������ʁj�I�u�W�F�N�g�ɃL���X�g����B 
            l_orderTypeEnum = 
                (OrderTypeEnum) l_serviceParams[1];
        }
        else
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // ���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
        // ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B 
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();  
        
        try
        {
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = 
            //  �U�֓��͒ʒm�L���[Params.�،���ЃR�[�h 
            l_context.setInstitutionCode(
                    l_hostTransferReceiptParams.getInstitutionCode()); 
            
            //����J�����_�R���e�L�X�g.���X�R�[�h =  
            //  �U�֓��͒ʒm�L���[Params.���X�R�[�h  
            l_context.setBranchCode(
                    l_hostTransferReceiptParams.getBranchCode());
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //=========== remain wei-nianqiong No.143 start ================
            //����J�����_�R���e�L�X�g.��t���ԋ敪 =  (*1)��t���ԋ敪
            //(*1)��t���ԋ敪�̎擾���@@ 
            //������ʂ��ȉ��̂����ꂩ�̏ꍇ�A�h23�F�ב֕ۏ؋��h 
            //1011(�ב֕ۏ؋��U�֒���(�a���������ב֕ۏ؋�)) 
            //1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a�����)) 
            //��L�ȊO�̏ꍇ�A �h13�F�؋����U�ցh 
            String l_strTradingTimeType = null;
            if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum) || 
                OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderTypeEnum))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EXCHANGE_GUARANTEE;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.MARGIN_TRANSFER;
            }
            
            l_context.setTradingTimeType(l_strTradingTimeType);
            
            //=========== remain wei-nianqiong No.143 end ================
            
            //(*2)������t���i�̎擾���@@ 
            //������� = 1005�F�U�֒����i�a�������M�p�ۏ؋��j �̏ꍇ�A�h09�F�M�p�ۏ؋��ւ̐U�ցh 
            //������� = 1006�F�U�֒����i�M�p�ۏ؋�����a����j �̏ꍇ�A�h10�F�M�p�ۏ؋�����̐U�ցh 
            //������� = 1007�F�U�֒����i�a������犔��؋����j �̏ꍇ�A�h12�F��OP�؋����ւ̐U�ցh 
            //������� = 1008�F�U�֒����i����؋�������a����j �̏ꍇ�A�h13�F��OP�؋�������̐U�ցh 
            //�@@������ʂ��ȉ��̂����ꂩ�̏ꍇ�A�h23�F�ב֕ۏ؋��h 
            //�@@�@@1011(�ב֕ۏ؋��U�֒���(�a���������ב֕ۏ؋�)) 
            //�@@�@@1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a�����)) 
             String l_strOrderAcceptProduct = null;
            
            // �����P��.������� = 1005�F�U�֒����i�a�������M�p�ۏ؋��j �̏ꍇ�A�h
            //      09�F�M�p�ۏ؋��ւ̐U�ցh 
            if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(
                l_orderTypeEnum))
            {
               l_strOrderAcceptProduct = 
                  WEB3OrderAccProductDef.MARGIN_GUARANTEE_MONEY_TRANSFER;
            }
            // �����P��.������� = 1006�F�U�֒����i�M�p�ۏ؋�����a����j �̏ꍇ�A�h
            //      10�F�M�p�ۏ؋�����̐U�ցh 
            else if(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                    l_orderTypeEnum))
            {
                 l_strOrderAcceptProduct = 
                     WEB3OrderAccProductDef.MARGIN_GUARANTEE_MONEY_TRANSFER_FROM;
            }
            // �����P��.������� = 1007�F�U�֒����i�a������犔��؋����j �̏ꍇ�A�h
            //      12�F��OP�؋����ւ̐U�ցh
            else if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(
                    l_orderTypeEnum))
            {
                 l_strOrderAcceptProduct = 
                     WEB3OrderAccProductDef.FUTURE_OP_GUARANTEE_MONEY_CHANGE;
            }
            // �����P��.������� = 1008�F�U�֒����i����؋�������a����j �̏ꍇ�A�h
            //      13�F��OP�؋�������̐U�ցh
            else if(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(
                    l_orderTypeEnum))
            {
                 l_strOrderAcceptProduct = 
                     WEB3OrderAccProductDef.FUTURE_OP_GUARANTEE_MONEY_CHANGE_FROM;
            }
            //=========== remain wei-nianqiong No.143 start ================
            //�@@������ʂ��ȉ��̂����ꂩ�̏ꍇ�A�h23�F�ב֕ۏ؋��h 
            //�@@�@@1011(�ב֕ۏ؋��U�֒���(�a���������ב֕ۏ؋�)) 
            //�@@�@@1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a�����)) 
            else if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                        l_orderTypeEnum) || 
                    OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
                        l_orderTypeEnum))
            {
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.EXCHANGE_GUARANTEE;
            }
            //=========== remain wei-nianqiong No.143 end ================
            
            //����J�����_�R���e�L�X�g.������t���i = (*2)������t���i 
            l_context.setOrderAcceptProduct(l_strOrderAcceptProduct);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h09�F�U�ցh 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.TRANSFER);
            
            //-ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(
                  WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                  l_context);
            
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //=========== remain zhou-yong NO.1 begin ==========
            
            //�R�j�@@���������b�N����B 
            //�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
            //�������͐U�֓��͒ʒm�L���[Params���ҏW�B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            l_accountManager.lockAccount(
                l_hostTransferReceiptParams.getInstitutionCode(),
                l_hostTransferReceiptParams.getBranchCode(),
                l_hostTransferReceiptParams.getAccountCode());
            
            //=========== remain zhou-yong NO.1 end ==========
            

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ " , l_ex);
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
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 414167E2010C
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
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
     * @@roseuid 414167E2012B
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
