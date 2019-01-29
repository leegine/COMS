head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossBreakEvenPointCalServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X���v����_�v�Z�T�[�r�X�C���^�Z�v�^(WEB3TrialCalcProfitLossBreakEvenPointCalServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.trialcalc.define.WEB3TrialCalcEquityMiniDivDef;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointRequest;

/**
 * �i�v�Z�T�[�r�X���v����_�v�Z�T�[�r�X�C���^�Z�v�^�j<BR>
 * <BR>
 * ���v����_�v�Z�T�[�r�X�C���^�Z�v�^�N���X�B<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * Break-even point calculation service interceptor class. <BR>
 * @@author Umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcProfitLossBreakEvenPointCalServiceInterceptor implements Interceptor
{
    /**
    * ���O�o�̓��[�e�B���e�B�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3TrialCalcProfitLossBreakEvenPointCalServiceInterceptor.class);

    /**
     * @@roseuid 41C81572004F
     */
    public WEB3TrialCalcProfitLossBreakEvenPointCalServiceInterceptor()
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
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * �@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = (*1)<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*2)<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = "0�FDEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h22�F�ڋq�T�[�r�X�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "07�F�Ɖ�"<BR>
     * <BR>
     * �@@(*1)���N�G�X�g�f�[�^�̌^���u�v�Z�T�[�r�X���v����_�v�Z���̓��N�G�X�g�v�̏ꍇ��
     * �Anull�B<BR>
     * �@@�@@�@@�@@���N�G�X�g�f�[�^�̌^���u�v�Z�T�[�r�X���v����_�v�Z���N�G�X�g�v�̏ꍇ�́A
     * <BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�f�[�^.�����^�~�j���敪��"��������"�̏ꍇ�́A"1�F����"�B<BR>
     * �@@�@@�@@�@@�@@�@@�i�����s�����Ƃ����������Ŏ萔�������߂邽�߁A"����"���Œ�ŃZ�b
     * �g�B�j<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�f�[�^.�����^�~�j���敪��"�~�j��"�̏ꍇ�́A"0�FDEFAULT"�B<BR
     * >
     * <BR>
     * �@@(*2)
     * ���N�G�X�g�f�[�^�̌^���u�v�Z�T�[�r�X���v����_�v�Z���̓��N�G�X�g�v�̏ꍇ�́A"01?
     * �����E�M�p"�B<BR>
     * �@@�@@�@@�@@���N�G�X�g�f�[�^�̌^���u�v�Z�T�[�r�X���v����_�v�Z���N�G�X�g�v�̏ꍇ�́A
     * <BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�f�[�^.�����^�~�j���敪��"��������"�̏ꍇ�́A"01�F�����E�M�p
     * "�B<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�f�[�^.�����^�~�j���敪��"�~�j��"�̏ꍇ�́A"05�F�����~�j����
     * "�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Call when the service method starts.<BR>
     * �iIt is called before service is executed from the xTrade kernel. �j<BR>
     * <BR>
     * 1) Set values in WEB3GentradeTradingClendarContext<BR>
     * <BR>
     *   - Cast the argument[0] of service�@@request to the data object<BR>
     *   - Set the property of "dealings time context" from the request data and
     * OpLoginSecurityService<BR>
     * <BR>
     *   Edit from WEB3GentradeTradingClendarContext.institutionCode =
     * OpLoginSecurityService<BR>
     *   Edit from WEB3GentradeTradingClendarContext.branchCode =
     * OpLoginSecurityService<BR>
     *   WEB3GentradeTradingClendarContext.marketCode  = (*1)<BR>
     *   WEB3GentradeTradingClendarContext.tradingTimeType = (*2)<BR>
     *   WEB3GentradeTradingClendarContext.productCode = "0�FDEFAULT"<BR>
     *   WEB3GentradeTradingClendarContext.orderAcceptProduct = '22'(Customer
     * Service)<BR>
     *   WEB3GentradeTradingClendarContext.orderAcceptTransaction  = '07'(Refer)<BR>
     * <BR>
     * (*1) If "Type of request data = [WEB3TrialCalcProfitLossCalcInputRequest]" ->
     * null<BR>
     *          If "Type of request data = [WEB3TrialCalcProfitLossCalcRequest]<BR>
     *            If "l_request.equityMiniDiv ='equity' -> '1'(TOKYO)<BR>
     *             �iBecause the commission is requested at the order date based on
     * the Tokyo market,<BR>
     *               "Tokyo" is set by fixation. �j<BR>
     *            If "l_request.equityMiniDiv = 'Mini stock division' ->
     * '0'(DEFAULT)<BR>
     * <BR>
     * (*2) If "Type of request data = [WEB3TrialCalcProfitLossCalcInputRequest] ->
     * '01'(equity/trust)<BR>
     *        If "Type of request data = [WEB3TrialCalcProfitLossCalcRequest]<BR>
     *          If "l_request.equityMiniDiv ='equity'"  -> '01'(equity/trust)<BR>
     *          If "l_request.equityMiniDiv = 'Mini stock division' '05'(The stocks
     * mini-investment)<BR>
     *                                              -> '02'(The stocks
     * mini-investment)<BR>
     * <BR>
     *  - Set dealings time context using
     * "ThreadLocalSystemAttributesRegistry.setAttribute( )"<BR>
     *    Set key : WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * 2)�@@Set orderDate and dateRoll<BR>
     *  - Call WEB3GentradeTradingTimeManagement.setTimestamp()<BR>
     * @@param l_method - java.lang.reflect.Method
     * @@param l_serviceParam java.lang.Object
     * @@return java.lang.Object
     * @@roseuid 419085A9035F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String l_methodName = "onCall(Method,Object[])";
             log.entering(l_methodName);
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        Object l_request = l_serviceParam[0];
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        try
        {
            OpLoginSecurityService l_opLoginSec =
                 (OpLoginSecurityService) Services.getService (OpLoginSecurityService.class);
            log.debug("getService(OpLoginSecurityService.class): " + l_opLoginSec);

            MainAccount l_mainAccount;
            long l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)
                                                                    l_finApp.getAccountManager();
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            l_context.setInstitutionCode(l_strInstitutionCode);
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            l_context.setBranchCode(l_strBranchCode);
        } catch (NotFoundException l_ex)
        {
            log.error(
            l_ex.getMessage(), new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + l_methodName,
            l_ex.toString(),
            l_ex));
        }
        if (l_request instanceof WEB3TrialCalcBreakEvenPointInputRequest)
        {
            l_context.setMarketCode(null);
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        } else if (l_request instanceof WEB3TrialCalcBreakEvenPointRequest)
        {
            WEB3TrialCalcBreakEvenPointRequest l_profitLossRequest =
                                    ((WEB3TrialCalcBreakEvenPointRequest) l_request);
            if (l_profitLossRequest.equityMiniDiv.equals(WEB3TrialCalcEquityMiniDivDef.EQUITY))
            {
                l_context.setMarketCode(WEB3MarketCodeDef.TOKYO);
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            } else
            {
                l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MINI_STOCK);
            }
        } else
        {
            String l_strMethodName = null;
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName,
                "INPUT ���N�G�X�g NOT ���v����_�v�Z���N�G�X�g");
        }
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        } catch (WEB3BaseException l_ex)
        {
            log.error(l_methodName, l_ex);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + l_methodName, l_ex.getMessage(), l_ex);
        }
        log.exiting(l_methodName);
        return (Object) l_context;
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
     * ----<English>--------------------<BR>
     * <BR>
     * When the service method ends, it is called. <BR>
     * Dealings calendar context clearn process. <BR>
     * <BR>
     * Clear the next areas of ThreadLocalSystemAttributesRegistry<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_objOnCallReturnValue Object
     * @@param l_objServiceMethodReturnValue Object
     * @@roseuid 419085A90362
     */
    public void onReturn(Object l_objOnCallReturnValue, Object l_objServiceMethodReturnValue)
    {
        String l_strMethodName = "onReturn(Object,Object)";
        log.entering(l_strMethodName);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        log.exiting(l_strMethodName);

    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * When the service method throws out an exception, it is called.  <BR>
     * <BR>
     * Clear the next areas of ThreadLocalSystemAttributesRegistry<BR>
     * WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_objOnCallReturnValue Object
     * @@param l_throwable Throwable
     * @@roseuid 419085A90365
     */
    public void onThrowable(Object l_objOnCallReturnValue, Throwable l_throwable)
    {
        String l_strMethodName = "onThrowable(Object,Throwable)";
        log.entering(l_strMethodName);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        log.exiting(l_strMethodName);
    }
}
@
