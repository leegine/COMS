head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.25.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMProductStopStartReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�T�[�r�X�C(WEB3AdminTMProductStopStartReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trademanagement;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.trademanagement.define.WEB3AdminTMLoginStopStartOrderAcceptProductDef;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

/**
  * �Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�T�[�r�X�C<BR>
 * <BR>
 *
 * �Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�T�[�r�X�C class<BR>
 * WEB3AdminTMProductStopStartReferenceServiceInterceptor<BR>
 * <BR>
 * WEB3AdminTMProductStopStartReferenceServiceInterceptor class<BR>
 * <BR>
 * @@author Sudhindra Kinnal
 * @@version 1.0
 */
public class WEB3AdminTMProductStopStartReferenceServiceInterceptor implements Interceptor
{
    /**
    * ���O�o�̓��[�e�B���e�B�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3AdminTMProductStopStartReferenceServiceInterceptor.class);
    /**
     * @@roseuid 41DD417700DC
     */
    public WEB3AdminTMProductStopStartReferenceServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g��<BR>
     * �@@�@@�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�F���̑��h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h00�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * -----<English>---------------------------<BR>
     * <BR>
     * When the service method begins, it is called.<BR>
     * <BR>
     * Generate WEB3GentradeTradingClendarContext that the trade calender uses.<BR>
     * (It is called from the xTrade kernel  before service is executed. )<BR>
     * <BR>
     * �P�j�@@Set the content to Dealings time context. <BR>
     * �@@�|Set the property of WEB3GentradeTradingClendarContext�@@from the content of
     * OpLoginSecurityService. <BR>
     * <BR>
     * �@@WEB3GentradeTradingClendarContext.InstitutionCode = Edit deal calendar
     * context from OpLoginSecurityService.<BR>
     * �@@WEB3GentradeTradingClendarContext.branchCode =  Edit deal calendar context
     * from OpLoginSecurityService.<BR>
     *   WEB3GentradeTradingClendarContext.marketCode = �h0�FDef.DEFAULT�h <BR>
     * �@@WEB3GentradeTradingClendarContext.tradingTimeType = �h00�FDef.DEFALT�h <BR>
     * �@@WEB3GentradeTradingClendarContext.productCode = �h0�FDef.DEFAULT�h <BR>
     * �@@WEB3GentradeTradingClendarContext.orderAcceptProduct = �h00�FDef.DEFAULT�h
     * <BR>
     * �@@WEB3GentradeTradingClendarContext.orderAcceptTransaction =
     * �h07�FDef.REFERENCE�h<BR>
     * <BR>
     * �@@�|WEB3GentradeTradingClendarContext is set with
     * ThreadLocalSystemAttributesRegistry.setAttribute( ). <BR>
     *      Set key�F WEB3GentradeTradingTimeManagement,TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@Set dateRoll and orderDate. <BR>
     * �@@�|Call  WEB3GentradeTradingTimeManagement.setTimestamp().<BR>
     * @@param l_method �T�[�r�X���\�b�h - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam �T�[�r�X���\�b�h���� - �T�[�r�X���\�b�h����
     * @@return java.lang.Object
     * @@roseuid 417DF900025E
     */
    public Object onCall(java.lang.reflect.Method l_method, java.lang.Object[] l_serviceParam)
    {
         final String STR_METHOD_NAME = "onCall(Method,Object[])";
         log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        try
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            log.debug("getService(OpLoginSecurityService.class): " + l_opLoginSec);

            long l_lngInstitutionId =
                Long.parseLong(l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.INSTITUTION_ID));
            long l_lngBranchId =
                Long.parseLong(l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.BRANCH_ID));
                
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            l_strInstitutionCode = l_accMgr.getInstitution(l_lngInstitutionId).getInstitutionCode();
            l_context.setInstitutionCode(l_strInstitutionCode);

            l_strBranchCode = l_accMgr.getBranch(l_lngBranchId).getBranchCode();
            l_context.setBranchCode(l_strBranchCode);
        } catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.toString());
        }
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3AdminTMLoginStopStartOrderAcceptProductDef.DEFAULT);

        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
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
     * ----<English>--------------<BR>
     * <BR>
     * When the service method ends, it is called. <BR>
     * WEB3GentradeTradingClendarContext clear process<BR>
     * <BR>
     * Clear the following content of ThreadLocalSystemAttributesRegistry.<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_objOnCallReturnValue ���^�[���l - onCall���^�[���l
     * @@param l_objServiceMethodReturnValue �T�[�r�X���\�b�h���^�[���l - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 417DF900028D
     */
    public void onReturn(
        java.lang.Object l_objOnCallReturnValue,
        java.lang.Object l_objServiceMethodReturnValue)
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
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * -----<English>-----------------------<BR>
     * <BR>
     * When the service method ends, it is called.<BR>
     * WEB3GentradeTradingClendarContext clear process.<BR>
     * <BR>
     * Clear the following content of ThreadLocalSystemAttributesRegistry.<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_objOnCallReturnValue ���^�[���l - onCall���^�[���l
     * @@param l_throwable ��O�I�u�W�F�N�g - ��O�I�u�W�F�N�g
     * @@roseuid 417DF90002AD
     */
    public void onThrowable(
        java.lang.Object l_objOnCallReturnValue,
        java.lang.Throwable l_throwable)
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
