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
filename	WEB3MarginChangeOpenMarginServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������V�K���T�[�r�X�C���^�Z�v�^(WEB3MarginChangeOpenMarginServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/8 Ḗ@@��(���u) �V�K�쐬
                   2004/12/15 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/06 ����   (SRA) JavaDoc�C��
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
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
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmRequest;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p��������V�K���T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �M�p��������V�K���T�[�r�X�C���^�Z�v�^�N���X
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginChangeOpenMarginServiceInterceptor implements Interceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginChangeOpenMarginServiceInterceptor.class);
    /**
     * @@roseuid 4142B6790338
     */
    public WEB3MarginChangeOpenMarginServiceInterceptor() 
    {
     
    }
    
    /**
     * (onCall)<BR>
     * <BR>
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^���u�M�p�����������_�V�K���������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�@@�@@��������OpLoginSecurityService���ҏW�B<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * �@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �����Ώے����P�ʂ̎s��ID�ɊY������s��R�[�h(*1)<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h05�F�����h<BR>
     * <BR>
     * �@@(*1) �����Ώے����P�ʂ̎s��ID�ɊY������s��R�[�h<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g.ID�ɊY�����钍��ID���������P�ʃI�u�W�F�N�g.getMarket().�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�������P�ʂ��������݂���ꍇ�́AgetOrderUnits()�̍ŏ��̗v�f���g�p����B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �S�j�@@��������x�e���ԑт̏ꍇ(*2)�̂݁A�L���[�e�[�u���̋��L���b�N���s���B<BR>
     * <BR>
     * �@@�@@���������T�[�r�X.lock������������L���[(�����Ώے����P��(*3))���R�[������B<BR>
     * <BR>
     * �@@�@@(*2)��������x�e���ԑт̏ꍇ<BR>
     * �@@�@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ�́A�x�e���ԑтł���Ɣ��肷��B<BR>
     * <BR>
     * �@@�@@(*3)�����Ώے����P��<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g.ID�ɊY�����钍��ID���������P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�@@���������݂���ꍇ�́AgetOrderUnits()�̍ŏ��̗v�f���g�p����B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - �T�[�r�X���\�b�h����<BR>
     * @@return Object<BR>
     * @@roseuid 40581DBC02EB
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
            
        log.entering(STR_METHOD_NAME);
        if (l_serviceParams[0] == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        long l_lngAccountId = l_opLoginSec.getAccountId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() +"." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        //�،���ЃR�[�h���擾
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h���擾
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        try
        {
            if (l_serviceParams[0] instanceof WEB3MarginOpenMarginChangeCompleteRequest)
            {
                l_accMgr.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_mainAccount.getAccountCode());
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() +"." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        // �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        try
        {      
            Object l_request = l_serviceParams[0];
            log.debug("OnCall�̈����^�C�v���`�F�b�N_start");    
            if (l_request == null)
            {   
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);                                
            }
            long l_lngOrderId = 0L;
            if (l_request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
            {
                WEB3MarginOpenMarginChangeConfirmRequest l_request0 =
                    (WEB3MarginOpenMarginChangeConfirmRequest) l_request;
                l_lngOrderId = Long.parseLong(l_request0.id);
            }
            else if (l_request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
            {
                WEB3MarginOpenMarginChangeCompleteRequest l_request0 =
                    (WEB3MarginOpenMarginChangeCompleteRequest) l_request;
                l_lngOrderId = Long.parseLong(l_request0.id);
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);                       
            }                                
            // �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
            // �@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            TradingModule l_tradingModule = 
                (TradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = 
                (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            WEB3GentradeFinObjectManager l_finObjManager =
                (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            
            //�����Ώے����P��
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //�s��R�[�h���擾
            Market l_market = l_finObjManager.getMarket(l_orderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode); 
            //����J�����_�R���e�L�X�g.�s��R�[�h = �����Ώے����P�ʂ̎s��ID�ɊY������s��R�[�h
            l_context.setMarketCode(l_strMarketCode);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 =   �h01�F�����E�M�p�h�j�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY); 
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);        
            //����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p���
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);         
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h05�F����
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
            
            //������ԃR���e�L�X�g���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);         
            
            // �Q�j�@@��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();

            if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
            {
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                l_frontOrderService.lockHostEqtypeOrderAll(l_orderUnit);
            }
            
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() +"." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }    
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() +"." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }
    
    /**
     * (onReturn)<BR>
     * <BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 40581DBC02FB
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object,Object)";
            
        log.entering(STR_METHOD_NAME);
        log.debug("������ԊǗ�.TIMESTAMP_TAG = " + WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        log.debug("������ԊǗ�.OFFSET_TAG = " + WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.debug("������ԊǗ�.TRADING_CAL_CONTEXT_PATH = " + WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (onThrowable)<BR>
     * <BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 40581DBC02FE
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";           
        log.entering(STR_METHOD_NAME);
        
        log.debug("������ԊǗ�.TIMESTAMP_TAG = " + WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        log.debug("������ԊǗ�.OFFSET_TAG = " + WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.debug("������ԊǗ�.TRADING_CAL_CONTEXT_PATH = " + WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
