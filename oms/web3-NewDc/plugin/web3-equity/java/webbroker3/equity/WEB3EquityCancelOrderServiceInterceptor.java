head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCancelOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������T�[�r�X�C���^�Z�v�^(WEB3EquityCancelOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 ���_�� (���u) �V�K�쐬
                   2004/12/20 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquityCancelConfirmRequest;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i������������T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * ������������T�[�r�X�C���^�Z�v�^
 * @@version 1.0
 */
public class WEB3EquityCancelOrderServiceInterceptor implements Interceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityCancelOrderServiceInterceptor.class);

    /**
     * @@roseuid 40AC7CAD00E0
     */
    public WEB3EquityCancelOrderServiceInterceptor() 
    {
    }
    
    /**
     * �ionCall�j�B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^���u����������������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�@@�@@��������OpLoginSecurityService���ҏW�B<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�i*1�j�ɃL���X�g����B<BR>
     * �@@�|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �i*2�j<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �i*2�j<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �i*2�j<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h����h<BR>
     * <BR>
     * �@@�i*1�j�u����������������m�F���N�G�X�g�v�܂��́A<BR>
     * �@@�@@�@@�@@�u����������������������N�G�X�g�v<BR>
     * <BR>
     * �@@�i*2�j���N�G�X�g�f�[�^�I�u�W�F�N�g.ID�ɊY�����钍���P�ʃI�u�W�F�N�g���ҏW�B<BR>
     * �@@�@@�@@getOrderUnits(���N�G�X�g�f�[�^.ID)�Ŏ擾����<BR>
     * �@@�@@�@@�����P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f�̒����P�ʂ��A<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@���s��R�[�h<BR>
     * �@@�@@�@@�@@�����P��.�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h<BR>
     * <BR>
     * �@@�@@�@@����t���ԋ敪<BR>
     * �@@�@@�@@�@@�����P��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ�F�@@"�����E�M�p"<BR>
     * �@@�@@�@@�@@�����P��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ�F�@@"����O����"<BR>
     * <BR>
     * �@@�@@�@@��������t���i<BR>
     * �@@�@@�@@�@@�����P��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ�F�@@"����"<BR>
     * �@@�@@�@@�@@�����P��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ�F�@@"����O����"<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �S�j�@@��������x�e���ԑт̏ꍇ(*3)�̂݁A�L���[�e�[�u���̋��L���b�N���s���B<BR>
     * <BR>
     * �@@�@@���������T�[�r�X.lock������������L���[(����Ώے����P��(*4))���R�[������B<BR>
     * <BR>
     * �@@�@@(*3)��������x�e���ԑт̏ꍇ<BR>
     * �@@�@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ�́A�x�e���ԑтł���Ɣ��肷��B<BR>
     * <BR>
     * �@@�@@(*4)����Ώے����P��<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g.ID�ɊY�����钍��ID���������P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�@@���������݂���ꍇ�́AgetOrderUnits()�̍ŏ��̗v�f���g�p����B<BR>
     * @@param l_method �i���\�b�h�j
     * @@param l_serviceParam �i�T�[�r�X�̈����j<BR>
     * <BR>
     * �T�[�r�X�̃��\�b�h�ɓn���������B<BR>
     * ������������T�[�r�X�̏ꍇ�A����������������m�F���N�G�X�g�I�u�W�F�N�g�A<BR>
     * �܂��͌���������������������N�G�X�g�I�u�W�F�N�g�B
     * @@return Object
     * @@roseuid 4031E93F034B
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
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
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        l_strInstitutionCode = l_account.getInstitution().getInstitutionCode();
        l_strBranchCode = l_account.getBranch().getBranchCode();
        try
        {
            if (l_serviceParams[0] instanceof WEB3EquityCancelCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_account.getAccountCode());
            }
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
        
        Object l_request = l_serviceParams[0];
        String l_strOrderId = null;

        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        // �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        if (l_request instanceof WEB3EquityCancelCompleteRequest)
        {
            // ����������������������N�G�X�g
            WEB3EquityCancelCompleteRequest l_completeRequest =
                (WEB3EquityCancelCompleteRequest)l_request;
            l_strOrderId = l_completeRequest.id;
        }
        else if (l_request instanceof WEB3EquityCancelConfirmRequest)
        {
            // ����������������m�F���N�G�X�g
            WEB3EquityCancelConfirmRequest l_confirmRequest =
                (WEB3EquityCancelConfirmRequest)l_request;
            l_strOrderId = l_confirmRequest.id;
        }
        else
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "request !=����������������������N�G�X�g and request !=����������������m�F���N�G�X�g"));
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        if(l_strOrderId == null){
            throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.BUSINESS_ERROR_00600,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
        // �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B    
        String l_strMarketCode = null;
        EqTypeOrderUnit l_orderUnit = null;
        try
        {
            WEB3EquityOrderManager l_orderManager =
                        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                                   
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_strOrderId));
            l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            long l_lngMarketId = l_orderUnitRow.getMarketId();
            
            WEB3GentradeFinObjectManager l_finObjectManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(l_lngMarketId);
            l_strMarketCode = l_market.getMarketCode();
                               
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            // ����J�����_�R���e�L�X�g.�����R�[�h = 0 �F DEFAULT
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            // ����J�����_�R���e�L�X�g.�s��R�[�h = (*2)
            l_context.setMarketCode(l_strMarketCode);
            
            if(!l_orderUnitRow.getSonarTradedCode().equals(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET))
            {
                //�����P��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ
                ////����J�����_�R���e�L�X�g.��t���ԋ敪 = (*2)
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
                ////����J�����_�R���e�L�X�g.������t���i = (*2)
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
                
            }
            else
            {
                //�����P��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ
                ////����J�����_�R���e�L�X�g.��t���ԋ敪 = (*2)
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET);
                ////����J�����_�R���e�L�X�g.������t���i = (*2)
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
                
            }
            // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);           
            // ������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);         

        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        try
        {
            // �Q�j�@@��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();

            if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
            {
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                l_frontOrderService.lockHostEqtypeOrderAll(l_orderUnit);
            }
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
     * �ionReturn�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_context �ionCall���^�[���l�j
     * @@param l_returnValue �i�T�[�r�X���\�b�h���^�[���l�j
     * @@roseuid 4031E944009C<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue) {
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
     * �ionThrowable�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj �ionCall���^�[���l�j
     * @@param l_throwable �i��O�I�u�W�F�N�g�j
     * @@roseuid 40AC7CAD0221<BR>
     */
    public void onThrowable(Object arg0, Throwable arg1) {
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
