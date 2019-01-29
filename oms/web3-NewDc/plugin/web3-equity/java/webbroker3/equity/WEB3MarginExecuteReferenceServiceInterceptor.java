head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginExecuteReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������Ɖ�T�[�r�X�C���^�Z�v�^�N���X(WEB3MarginExecuteReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/21 䈋� (���u) �V�K�쐬
                   2005/01/06 �����a�� (SRA) JavaDoc�C��
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.equity.message.WEB3MarginCloseMarginContractListRequest;
import webbroker3.equity.message.WEB3MarginExecuteDetailsRequest;
import webbroker3.equity.message.WEB3MarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3MarginOrderHistoryRequest;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����������Ɖ�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �M�p����������Ɖ�T�[�r�X�C���^�Z�v�^�N���X
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceServiceInterceptor
    implements Interceptor
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginExecuteReferenceServiceInterceptor.class);
    /**
     * @@roseuid 4142B32E02DB
     */
    public WEB3MarginExecuteReferenceServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�@@ ������.�T�[�r�X���\�b�h��"search�������Ɖ�"�܂���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@    �@@�@@�@@�@@�@@"search�������ڍ�"�̏ꍇ�̂݁B<BR> 
     * <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁA<BR>
     *    OpLoginSecurityService�̓��e��������ԃR���e�L�X�g��<BR>
     *    �v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *       OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = ���N�G�X�g.�s��R�[�h(*1)<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h<BR>
     * <BR>
     * �@@(*1)���N�G�X�g.�s��R�[�h��null���Z�b�g����Ă���ꍇ�́A<BR>
     *     �@@null�����̂܂܃Z�b�g�B<BR>
     * �@@�@@�@@����.�T�[�r�X���\�b�h��"search�������ڍ�"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�����N�G�X�g.ID�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�������P��.�s��ID�ɊY������s��.�s��R�[�h���擾���Z�b�g����B<BR>
     * <BR>
     * �@@��OpLoginSecurityService���擾��������ID == 0�̏ꍇ�A<BR>
     * �@@�@@���N�G�X�g.����ID�ɊY�����钍��.����ID��LocalThread�ɃZ�b�g����B<BR>
     * �@@�@@�ݒ�L�[�F�@@ACCOUNT_ID<BR>
     *<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - �T�[�r�X���\�b�h����<BR>
     * @@return Object<BR>
     * @@roseuid 405831EE018D
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {

        final String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        WEB3MarginExecuteReferenceRequest l_request = null;
        WEB3MarginExecuteDetailsRequest l_detailsRequest = null;
        if ((l_serviceParams == null) || (l_serviceParams[0] == null))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + "onCall");
        }

        if (l_serviceParams[0] instanceof WEB3MarginExecuteReferenceRequest)
        {
            l_request =
                    (WEB3MarginExecuteReferenceRequest)l_serviceParams[0];
        }
        else if(l_serviceParams[0] instanceof WEB3MarginExecuteDetailsRequest)
        {
            l_detailsRequest =
                    (WEB3MarginExecuteDetailsRequest)l_serviceParams[0];
        }
        else if(l_serviceParams[0] instanceof WEB3MarginOrderHistoryRequest)
        {
        }
        else if(l_serviceParams[0] instanceof WEB3MarginCloseMarginContractListRequest)
        {
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            String l_strInstitutionCode = null; //�،���ЃR�[�h
            String l_strBranchCode = null; //���X�R�[�h

            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            //MainAccount���擾
            long l_lngAccountId = l_opLoginSec.getAccountId();
            // �Ǘ��҃Z�b�V��������R�[�����ꂽ�ꍇ
            if (l_lngAccountId == 0L)
            {
                long l_lngOrderId = 0L;
                // ���N�G�X�g == �M�p����������ڍׂ̏ꍇ
                if (l_serviceParams[0] instanceof WEB3MarginExecuteDetailsRequest)
                {
                    WEB3MarginExecuteDetailsRequest l_detailRequest =
                        (WEB3MarginExecuteDetailsRequest)l_serviceParams[0];
                    l_lngOrderId = Long.parseLong(l_detailRequest.id);
                }
                // ���N�G�X�g == �M�p���������藚���Ɖ�̏ꍇ
                else if (l_serviceParams[0] instanceof WEB3MarginOrderHistoryRequest)
                {
                    WEB3MarginOrderHistoryRequest l_historyRequest =
                        (WEB3MarginOrderHistoryRequest)l_serviceParams[0];
                    l_lngOrderId = Long.parseLong(l_historyRequest.id);
                }
                // ���N�G�X�g == �M�p������ό����ꗗ�̏ꍇ
                else if (l_serviceParams[0] instanceof WEB3MarginCloseMarginContractListRequest)
                {
                    WEB3MarginCloseMarginContractListRequest l_historyRequest =
                        (WEB3MarginCloseMarginContractListRequest)l_serviceParams[0];
                    l_lngOrderId = Long.parseLong(l_historyRequest.id);
                }
                
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                    
                EqTypeOrder l_eqtypeOrder =
                    (EqTypeOrder)l_orderManager.getOrder(l_lngOrderId);
                l_lngAccountId = l_eqtypeOrder.getAccountId();
                
                // ThreadLocal�Ɏ擾��������ID���Z�b�g�B
                ThreadLocalSystemAttributesRegistry.setAttribute(WEB3MarginAttributeNameDef.ACCOUNT_ID, new Long(l_lngAccountId));
            }
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);

            //�،���ЃR�[�h���擾
            Institution l_institution = l_mainAccount.getInstitution();
            l_strInstitutionCode = l_institution.getInstitutionCode();
            //���X�R�[�h���擾
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW        
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h = ���N�G�X�g.�s��R�[�h(*1)
            //���N�G�X�g.�s��R�[�h��null���Z�b�g����Ă���ꍇ�́A<BR>
            //null�����̂܂܃Z�b�g
            if (l_serviceParams[0] instanceof WEB3MarginExecuteReferenceRequest)
            {
                l_context.setMarketCode(l_request.marketCode);
            }
            else if(l_serviceParams[0] instanceof WEB3MarginExecuteDetailsRequest)
            {
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
                String l_strMarketCode = null;
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_eqTypeOrderUnitRow.getMarketId()).getMarketCode();
                
                //�擾�����s��R�[�h������J�����_�R���e�L�X�g.�s��R�[�h�ɃZ�b�g����B
                l_context.setMarketCode(l_strMarketCode);
            }
            else
            {
                l_context.setMarketCode(null);
            }
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            //����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =�h07�F�Ɖ�h
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.REFERENCE);

            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //��t�����A���t���[�����Z�b�g����B   
            WEB3GentradeTradingTimeManagement.setTimestamp();

            log.exiting(STR_METHOD_NAME);
            return l_context;
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.toString(),
                l_nfe);
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
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
    }

    /**
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
     * @@roseuid 405831EE019D
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
            
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.ACCOUNT_ID,
            null);
            
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 405831EE01A0
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
            
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.ACCOUNT_ID,
            null);
            
        log.exiting(STR_METHOD_NAME);

    }
}
@
