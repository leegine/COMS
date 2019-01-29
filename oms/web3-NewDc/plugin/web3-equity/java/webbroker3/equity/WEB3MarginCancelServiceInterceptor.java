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
filename	WEB3MarginCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�������T�[�r�X�C���^�Z�v�^(WEB3MarginCancelServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 ������ (���u) �V�K�쐬
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
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
import webbroker3.equity.message.WEB3MarginCancelCompleteRequest;
import webbroker3.equity.message.WEB3MarginCancelConfirmRequest;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�������T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �M�p�������T�[�r�X�C���^�Z�v�^�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCancelServiceInterceptor implements Interceptor 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginCancelServiceInterceptor.class);
            
    /**
     * @@roseuid 4142B32C0351
     */
    public WEB3MarginCancelServiceInterceptor() 
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
     * �P�j�@@���N�G�X�g�f�[�^�̌^���u�M�p�����������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�@@�@@��������OpLoginSecurityService���ҏW�B<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = (*)<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*)<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h06�F����h<BR>
     * <BR>
     * �@@(*)���N�G�X�g�f�[�^�I�u�W�F�N�g.ID�ɊY�����钍���P�ʃI�u�W�F�N�g���ҏW�B<BR>
     * �@@�@@�@@getOrderUnits(���N�G�X�g�f�[�^.ID)�Ŏ擾����<BR>
     * �@@�@@�@@�����P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f�̒����P�ʂ��A<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@���s��R�[�h<BR>
     * �@@�@@�@@�@@�����P��.�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h<BR>
     * <BR>
     * �@@�@@�@@����t���ԋ敪<BR>
     * �@@�@@�@@�@@�����P��.�����J�e�S����"�����E���n"�̏ꍇ�F�@@"�����E���n"<BR>
     * �@@�@@�@@�@@�����P��.�����J�e�S����"�����E���n"�̏ꍇ�F�@@"�����E�M�p"<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �S�j�@@����Ώے������������n�ȊO(*2)�A���@@��������x�e���ԑт̏ꍇ(*3)�̂݁A<BR>
     * �@@�@@�@@�L���[�e�[�u���̋��L���b�N���s���B<BR>
     * <BR>
     * �@@�@@���������T�[�r�X.lock������������L���[(����Ώے����P��(*4))���R�[������B<BR>
     * <BR>
     * �@@�@@(*2)����Ώے������������n�ȊO<BR>
     * �@@�@@�@@�@@�����P��.�����J�e�S����"�����E���n"�̏ꍇ�A�������n�ȊO�ł���Ɣ��肷��B<BR>
     * <BR>
     * �@@�@@(*3)��������x�e���ԑт̏ꍇ<BR>
     * �@@�@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ�́A�x�e���ԑтł���Ɣ��肷��B<BR>
     * <BR>
     * �@@�@@(*4)����Ώے����P��<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g.ID�ɊY�����钍��ID���������P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�@@���������݂���ꍇ�́AgetOrderUnits()�̍ŏ��̗v�f���g�p����B<BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - �T�[�r�X���\�b�h����<BR>
     * @@return Object<BR>
     * @@roseuid 405808BF0300
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        //--------------------
        // �ϐ��E�I�u�W�F�N�g�̏������E�ݒ�
        //--------------------
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            
        Market l_market = null;
        WEB3GentradeMainAccount l_account = null;
        
        OrderUnit[] l_orderUnits = null;
        EqTypeOrderUnit l_orderUnit = null;
        EqtypeOrderUnitRow l_orderUnitRow = null;
        
        String l_strMarketCode = null;
        String l_strTradingTimeType = null;
        String l_strOrderId = null;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;

        try
        {
            l_account = (WEB3GentradeMainAccount)
                l_accountManager.getMainAccount(l_opLoginSec.getAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
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
            if (l_serviceParams[0] instanceof WEB3MarginCancelCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_account.getAccountCode());
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //--------------------
        // ���N�G�X�g���璍��ID���擾����
        //--------------------
        if (l_serviceParams[0] instanceof WEB3MarginCancelConfirmRequest)
        {
            WEB3MarginCancelConfirmRequest l_request = (WEB3MarginCancelConfirmRequest) l_serviceParams[0];
            l_strOrderId = l_request.id;
        }
        else if (l_serviceParams[0] instanceof WEB3MarginCancelCompleteRequest)
        {
            WEB3MarginCancelCompleteRequest l_request = (WEB3MarginCancelCompleteRequest) l_serviceParams[0];
            l_strOrderId = l_request.id;
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." +STR_METHOD_NAME);
        }


        //--------------------
        // �����P�ʂ��擾����
        //--------------------
        l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_strOrderId));
        l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();


        //--------------------
        // �s��R�[�h���擾����
        //--------------------
        try {
            l_market = l_finObjManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe){
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        l_strMarketCode = l_market.getMarketCode();


        //--------------------
        // ��t���ԋ敪���擾����
        //--------------------
        if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
        {
            l_strTradingTimeType = WEB3TradingTimeTypeDef.SWAP;
        }
        else
        {
            l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
        }


        //--------------------
        // ����J�����_�R���e�L�X�g��ݒ�
        //--------------------
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        l_context.setInstitutionCode(l_strInstitutionCode);
        l_context.setBranchCode(l_strBranchCode);
        l_context.setMarketCode(l_strMarketCode);
        l_context.setTradingTimeType(l_strTradingTimeType);
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);         


        try
        {
            
            //--------------------
            // ��t�����A���t���[�����Z�b�g����B
            //--------------------
            WEB3GentradeTradingTimeManagement.setTimestamp();

            if (!OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
            {
                if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                {
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    l_frontOrderService.lockHostEqtypeOrderAll(l_orderUnit);
                }
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
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
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
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
     * @@roseuid 405808BF031F
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
     * (onThrowable)<BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 405808BF032F
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
