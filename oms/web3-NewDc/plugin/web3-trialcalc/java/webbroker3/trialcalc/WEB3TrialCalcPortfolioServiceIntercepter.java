head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioServiceIntercepter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X�C���^�Z�v�^(WEB3TrialCalcProfitLossCalServiceInterceptor.java)
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

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X�C���^�Z�v�^�j<BR>
 * <BR>
 * �|�[�g�t�H���I�T�[�r�X�C���^�Z�v�^�N���X�B<BR>
 * * ----<English>--------------------<BR>
 *
 * WEB3TrialCalcPortfolioServiceIntercepter<BR>
 * @@author Honnappa Manjula
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioServiceIntercepter implements Interceptor
{
    /**
      * ���O�o�̓��[�e�B���e�B �B < BR >
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioServiceIntercepter.class);

    /**
      * l_strMethodName is a private variable.
      */
    private String strMethodName = "";

    /**
     * constructor
     */
    public WEB3TrialCalcPortfolioServiceIntercepter()
    {
    }

   /**
    * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
    * <BR>
    * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
    * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
    * <BR>
    * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
    * �@@�|OpLoginSecurityService�̓��e���<BR>
    * �@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
    * <BR>
    * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
    * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
    * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<BR>
    * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "01�F�����E�M�p"<BR>
    * �@@����J�����_�R���e�L�X�g.�����R�[�h = "0�FDEFAULT"<BR>
    * �@@����J�����_�R���e�L�X�g.������t���i = "22�F�ڋq�T�[�r�X"<BR>
    * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "07�F�Ɖ�"<BR>
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
    *   WEB3GentradeTradingClendarContext.marketCode  = null<BR>
    *   WEB3GentradeTradingClendarContext.tradingTimeType = '01'(Equity)<BR>
    *   WEB3GentradeTradingClendarContext.productCode = "0�FDEFAULT"<BR>
    *   WEB3GentradeTradingClendarContext.orderAcceptProduct = '22'(Customer
    * Service)<BR>
    *   WEB3GentradeTradingClendarContext.orderAcceptTransaction  = '07'(Refer)<BR>
    * <BR>
    *  - Set dealings time context using
    * "ThreadLocalSystemAttributesRegistry.setAttribute( )"<BR>
    *    Set key : WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
    * <BR>
    * 2)�@@Set orderDate and dateRoll<BR>
    *  - Call WEB3GentradeTradingTimeManagement.setTimestamp()<BR>
    * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
    * @@param l_serviceParam ���� - �T�[�r�X���\�b�h����
    * @@return java.lang.Object
    * @@roseuid 419338980002
    */
   public Object onCall(Method l_method, Object[] l_serviceParam)
   {
        strMethodName = "onCall(Method,Object[])";
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strMarketCode = null;
       long l_lngAccountId = 0;
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        log.entering(strMethodName);

        try
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            log.debug("getService(OpLoginSecurityService.class): " + l_opLoginSec);

            MainAccount l_mainAccount;
            l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            l_context.setInstitutionCode(l_strInstitutionCode);

            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            l_context.setBranchCode(l_strBranchCode);

        } catch (NotFoundException l_ex)
        {
            log.error(
                l_ex.getMessage(),
                new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + strMethodName,
                    l_ex.toString(),
                    l_ex));
        }

        l_context.setMarketCode(null);
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();

        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(strMethodName, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(strMethodName);

        // to be  seen during testing iof null has to be returned or an object i.e l_context
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
    * <BR>
    * @@param l_objOnCallReturnValue - onCall���^�[���l
    * @@param l_objServiceMethodReturnValue �l - �T�[�r�X���\�b�h���^�[���l
    * @@roseuid 419338980005
    */
   public void onReturn(Object l_objOnCallReturnValue, Object l_objServiceMethodReturnValue)
   {
        strMethodName = "onReturn(Object,Object)";
        log.entering(strMethodName);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

         log.exiting(strMethodName);
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
    * @@param l_objOnCallReturnValue - onCall���^�[���l
    * @@param l_throwable - ��O�I�u�W�F�N�g
    * @@roseuid 419338980008
    */
   public void onThrowable(Object l_objOnCallReturnValue, Throwable l_throwable)
   {
       strMethodName = "onThrowable(Object,Throwable)";
               log.entering(strMethodName);

       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
           null);

       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
           null);

       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.OFFSET_TAG,
           null);

       log.exiting(strMethodName);

   }
}
@
