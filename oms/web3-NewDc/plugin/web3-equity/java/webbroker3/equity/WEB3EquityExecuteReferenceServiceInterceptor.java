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
filename	WEB3EquityExecuteReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������Ɖ�T�[�r�X�C���^�Z�v�^(WEB3EquityExecuteReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 �� �� (���u) �V�K�쐬
                   2004/12/24 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.equity.message.WEB3EquityExecuteDetailsRequest;
import webbroker3.equity.message.WEB3EquityExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityOrderHistoryRequest;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������������Ɖ�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * ���������������Ɖ�T�[�r�X�C���^�Z�v�^
 * @@version 1.0
 */
public class WEB3EquityExecuteReferenceServiceInterceptor
    implements Interceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityExecuteReferenceServiceInterceptor.class);

    /**
     * @@roseuid 40A288C501B5
     */
    public WEB3EquityExecuteReferenceServiceInterceptor()
    {
        super();
    }

    /**
     * (onCall)<BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�@@�@@������.�T�[�r�X���\�b�h��"search�������Ɖ�"�܂���<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"search�������ڍ�"�̏ꍇ�̂݁B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h =
     * �@@�@@�@@����.�T�[�r�X���\�b�h��"search�������Ɖ�"�̏ꍇ�A
     *�@@�@@�@@�@@�@@�����N�G�X�g�f�[�^���ҏW
     * �@@�@@�@@����.�T�[�r�X���\�b�h��"search�������ڍ�"�̏ꍇ�A
     *�@@�@@�@@�@@�@@�����N�G�X�g.ID�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B
     *�@@�@@�@@�@@�@@�������P��.�s��ID�ɊY������s��.�s��R�[�h���擾���Z�b�g����B
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h<BR>
     * <BR>
     * �@@��OpLoginSecurityService���擾��������ID == 0�̏ꍇ�A<BR>
     * �@@�@@���N�G�X�g.����ID�ɊY�����钍��.����ID��LocalThread�ɃZ�b�g����B<BR>
     * �@@�@@�ݒ�L�[�F�@@ACCOUNT_ID<BR>
     *<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * ������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 4060DF0D017B
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        Object l_request = l_serviceParam[0];

        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        // �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        // �@@�@@�@@������.�T�[�r�X���\�b�h��"search�������Ɖ�"�̏ꍇ�̂�
        String l_strMarketCode = null;
        if (l_request instanceof WEB3EquityExecuteReferenceRequest)
        {
            WEB3EquityExecuteReferenceRequest l_referenceRequest =
                (WEB3EquityExecuteReferenceRequest)l_request;
            l_strMarketCode = l_referenceRequest.marketCode;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        try
        {
            if (l_request instanceof WEB3EquityExecuteDetailsRequest)
            {
                WEB3EquityExecuteDetailsRequest l_detailsRequest =
                    (WEB3EquityExecuteDetailsRequest)l_request;
                //���N�G�X�g.ID�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                long l_lngOrderId = Long.parseLong(l_detailsRequest.id);
                OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
                EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
                EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
                    (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        
                //�����P��.�s��ID�ɊY������s��.�s��R�[�h���擾����B
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_eqTypeOrderUnitRow.getMarketId()).getMarketCode();
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        // �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
        // �@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);

        try
        {
            long l_lngAccountId = l_opLoginSec.getAccountId();
            
            // �Ǘ��҃Z�b�V��������R�[�����ꂽ�ꍇ
            if (l_lngAccountId == 0L)
            {
                long l_lngOrderId = 0L;
                // ���N�G�X�g == �����������ڍׂ̏ꍇ
                if (l_request instanceof WEB3EquityExecuteDetailsRequest)
                {
                    WEB3EquityExecuteDetailsRequest l_detailRequest =
                        (WEB3EquityExecuteDetailsRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_detailRequest.id);
                }
                // ���N�G�X�g == ����������藚���Ɖ�̏ꍇ
                else if (l_request instanceof WEB3EquityOrderHistoryRequest)
                {
                    WEB3EquityOrderHistoryRequest l_historyRequest =
                        (WEB3EquityOrderHistoryRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_historyRequest.id);
                }
                
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                    
                EqTypeOrder l_eqtypeOrder =
                    l_eqtypeOrder = (EqTypeOrder)l_orderManager.getOrder(l_lngOrderId);
                l_lngAccountId = l_eqtypeOrder.getAccountId();
                
                // ThreadLocal�Ɏ擾��������ID���Z�b�g�B
                ThreadLocalSystemAttributesRegistry.setAttribute(WEB3MarginAttributeNameDef.ACCOUNT_ID, new Long(l_lngAccountId));
            }

            MainAccount l_account = l_accountManager.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_account.getInstitution().getInstitutionCode();
            l_strBranchCode = l_account.getBranch().getBranchCode();
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_strInstitutionCode);
        // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_strBranchCode);
        // ����J�����_�R���e�L�X�g.�s��R�[�h = ����.�T�[�r�X���\�b�h��"search�������Ɖ�"��
        // �ꍇ�̂݁A���N�G�X�g�f�[�^���ҏW
        l_context.setMarketCode(l_strMarketCode);
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        // ����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // ����J�����_�R���e�L�X�g.������t���i = �h�����h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =�h07�F�Ɖ�h
        l_context.setOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.REFERENCE);

        //�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�
        // �@@�@@������ԃR���e�L�X�g���Z�b�g����B
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        // �Q�j�@@��t�����A���t���[�����Z�b�g����B
        // �@@�|������ԊǗ�.setTimestamp()���R�[������B
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
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
     * (onReturn)<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_objOnCallReturnValue - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_objServiceMethodReturnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 4060DF0D019A
     */
    public void onReturn(
        Object l_objOnCallReturnValue,
        Object l_objServiceMethodReturnValue)
    {
        String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.ACCOUNT_ID,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (onThrowable)<BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_objOnCallReturnValue - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 406411F702F4
     */
    public void onThrowable(
        Object l_objOnCallReturnValue,
        Throwable l_throwable)
    {
        String STR_METHOD_NAME = "onReturn(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.ACCOUNT_ID,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
