head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.28.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeCompleteUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֊���UnitService�C���^�Z�v�^(WEB3AccTransChangeCompleteUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/22 �����(���u) ���r���[     
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

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
 * (�U�֊���UnitService�C���^�Z�v�^)<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeCompleteUnitServiceInterceptor
    implements Interceptor
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeCompleteUnitServiceInterceptor.class);

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|����.�T�[�r�X�̈���[0]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     * �����P��.�،���Е��X�h�c�ɊY�����镔�X�̏،���ЃR�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     * �����P��.�،���Е��X�h�c�ɊY�����镔�X�̕��X�R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h13�F�؋����U�ցh <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = (*1)������t���i<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h09�F�U�ցh<BR>
     * <BR>
     *   (*1)������t���i�̎擾���@@<BR>
     *     �����P��.������� = 1005�F�U�֒����i�a�������M�p�ۏ؋��j <BR>
     * �̏ꍇ�A�h09�F�M�p�ۏ؋��ւ̐U�ցh<BR>
     *     �����P��.������� = 1006�F�U�֒����i�M�p�ۏ؋�����a����j <BR>
     * �̏ꍇ�A�h10�F�M�p�ۏ؋�����̐U�ցh<BR>
     *     �����P��.������� = 1007�F�U�֒����i�a������犔��؋����j <BR>
     * �̏ꍇ�A�h12�F��OP�؋����ւ̐U�ցh<BR>
     *     �����P��.������� = 1008�F�U�֒����i����؋�������a����j <BR>
     * �̏ꍇ�A�h13�F��OP�؋�������̐U�ցh<BR>
     * 
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * ������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@���������b�N����B<BR> 
�@@   * �|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)<BR>
     * ���R�[������B<BR> 
     * �������͒����P�ʂ��ҏW�B<BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h
     * @@param l_serviceParams - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 414166F80050
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParams == null)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        
        if (l_serviceParams.length == 0)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }       
               
        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 
        // �@@�|����.�T�[�r�X�̈���[0]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B 
        OrderUnit l_orderUnit = (OrderUnit) l_serviceParams[0];
        
        // ���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
        // ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����        
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();  

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                
        long l_lngAccountId = l_orderUnit.getAccountId();
        AccountManager l_accMgr = l_finApp.getAccountManager();
        MainAccount l_acc = null;
        
        try
        {
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            String l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            String l_strBranchCode = l_acc.getBranch().getBranchCode(); 
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = 
            //  �����P��.�،���Е��X�h�c�ɊY�����镔�X�̏،���ЃR�[�h 
            l_context.setInstitutionCode(l_strInstitutionCode); 
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = 
            //  �����P��.�،���Е��X�h�c�ɊY�����镔�X�̕��X�R�[�h  
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //=========== remain wei-nianqiong No.203 start ================
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 =  (*1)��t���ԋ敪
            //(*1)��t���ԋ敪�̎擾���@@ 
            //������ʂ��ȉ��̂����ꂩ�̏ꍇ�A�h23�F�ב֕ۏ؋��h 
            //1011(�ב֕ۏ؋��U�֒���(�a���������ב֕ۏ؋�)) 
            //1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a�����)) 
            //��L�ȊO�̏ꍇ�A �h13�F�؋����U�ցh 
            String l_strTradingTimeType = null;
            
            OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
            log.debug("������� = " + l_orderType);
            
            if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) || 
                OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EXCHANGE_GUARANTEE;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.MARGIN_TRANSFER;
            }
            
            l_context.setTradingTimeType(l_strTradingTimeType);
            
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
            if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderType))
            {
               l_strOrderAcceptProduct = 
                  WEB3OrderAccProductDef.MARGIN_GUARANTEE_MONEY_TRANSFER;
            }
            // �����P��.������� = 1006�F�U�֒����i�M�p�ۏ؋�����a����j �̏ꍇ�A�h
            //      10�F�M�p�ۏ؋�����̐U�ցh 
            else if(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType))
            {
                 l_strOrderAcceptProduct = 
                     WEB3OrderAccProductDef.MARGIN_GUARANTEE_MONEY_TRANSFER_FROM;
            }
            // �����P��.������� = 1007�F�U�֒����i�a������犔��؋����j �̏ꍇ�A�h
            //      12�F��OP�؋����ւ̐U�ցh
            else if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderType))
            {
                 l_strOrderAcceptProduct = 
                     WEB3OrderAccProductDef.FUTURE_OP_GUARANTEE_MONEY_CHANGE;
            }
            // �����P��.������� = 1008�F�U�֒����i����؋�������a����j �̏ꍇ�A�h
            //      13�F��OP�؋�������̐U�ցh
            else if(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderType))
            {
                 l_strOrderAcceptProduct = 
                     WEB3OrderAccProductDef.FUTURE_OP_GUARANTEE_MONEY_CHANGE_FROM;
            }

            //�@@������ʂ��ȉ��̂����ꂩ�̏ꍇ�A�h23�F�ב֕ۏ؋��h 
            //�@@�@@1011(�ב֕ۏ؋��U�֒���(�a���������ב֕ۏ؋�)) 
            //�@@�@@1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a�����)) 
            else if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) || 
                OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType))
            {
                l_strOrderAcceptProduct = WEB3OrderAccProductDef.EXCHANGE_GUARANTEE;
            }
            
            //����J�����_�R���e�L�X�g.������t���i = (*1)������t���i 
            l_context.setOrderAcceptProduct(l_strOrderAcceptProduct);
            
            //=========== remain wei-nianqiong No.203 end ================
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h09�F�U�ցh 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.TRANSFER);
            
            //-ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(
                  WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                  l_context);
            
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //=========remain zhou-yong NO.1 begin ===========
            //�R�j�@@���������b�N����B 
            //�@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
            //�������͒����P�ʂ��ҏW�B
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_accMgr;
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            l_accountManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_mainAccount.getAccountCode());
            
            //=========remain zhou-yong NO.1 end ===========            
            
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__an unexpected error__ ",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()",l_ex);
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
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 414166F8006F
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
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 414166F8008F
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
