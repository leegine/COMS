head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.25.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginStopStartChangeServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�X�C���^�Z�v�^(WEB3AdminTMLoginStopStartChangeServiceInterceptor.java)
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

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.trademanagement.define.WEB3AdminTMLoginStopStartOrderAcceptProductDef;

/**
* (�Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�X�C���^�Z�v�^)<BR>
* <BR>
* �Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�X�C���^�Z�v�^ �N���X<BR>
* <BR>
* WEB3AdminTMLoginStopStartChangeServiceInterceptor<BR>
* <BR>
* WEB3AdminTMLoginStopStartChangeServiceInterceptor class<BR>
* <BR>
* @@author Umadevi
* @@version 1.0
*/

public class WEB3AdminTMLoginStopStartChangeServiceInterceptor implements Interceptor
{
    /**
    * ���O�o�̓��[�e�B���e�B�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginStopStartChangeServiceInterceptor.class);
    /**
     * @@roseuid 41DD41190214
     */
    public WEB3AdminTMLoginStopStartChangeServiceInterceptor()
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
     * �P�j�@@Set the content to WEB3GentradeTradingClendarContext. <BR>
     * �@@�|Set the property of WEB3GentradeTradingClendarContext from the content of
     * OpLoginSecurityService. <BR>
     * <BR>
     * �@@WEB3GentradeTradingClendarContext.InstitutionCode = Edit context from
     * OpLoginSecurityService.<BR>
     * �@@WEB3GentradeTradingClendarContext.branchCode =  Edit from
     * OpLoginSecurityService.<BR>
     *   WEB3GentradeTradingClendarContext.marketCode = �h0�FDef.DEFAULT�h <BR>
     * �@@WEB3GentradeTradingClendarContext.tradingTimeType = �h00�FDef.DEFALT�h <BR>
     * �@@WEB3GentradeTradingClendarContext.productCode = �h0�FDef.DEFAULT�h <BR>
     * �@@WEB3GentradeTradingClendarContext.orderAcceptProduct = �h00�FDef.DEFAULT�h
     * <BR>
     * �@@WEB3GentradeTradingClendarContext.orderAcceptTransaction =
     * �h07�FDef.REFERENCE�h<BR>
     * <BR>
     * �@@�|Dealings time context is set with
     * ThreadLocalSystemAttributesRegistry.setAttribute( ). <BR>
     *      Set key�F WEB3GentradeTradingTimeManagement,TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@Set the date roll and the reception date. <BR>
     * �@@�|Call  WEB3GentradeTradingTimeManagement.setTimestamp().<BR>
     * <BR>
     * @@param l_arg0 Method
     * @@param l_arg1 Object
     * @@return java.lang.Object
     * @@roseuid 417DF7A301C2
     */
    public Object onCall(java.lang.reflect.Method l_arg0, java.lang.Object[] l_arg1)
    {
        String STR_METHOD_NAME = "onCall(Method,Object[])";
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
     * When  the service method ends, it is called. <BR>
     * WEB3GentradeTradingClendarContext clear process<BR>
     * <BR>
     * Clear the following content of ThreadLocalSystemAttributesRegistry.<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_arg0 Method
     * @@param l_arg1 Object
     * @@roseuid 417DF7A301E1
     */
    public void onReturn(java.lang.Object l_arg0, java.lang.Object l_arg1)
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
     * @@param l_arg0 Method
     * @@param l_arg1 Object
     * @@roseuid 417DF7A30201
     */
    public void onThrowable(java.lang.Object l_arg0, java.lang.Throwable l_arg1)
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
