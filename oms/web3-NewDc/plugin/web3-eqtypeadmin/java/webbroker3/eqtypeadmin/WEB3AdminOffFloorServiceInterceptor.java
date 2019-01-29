head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O�����T�[�r�X�C���^�Z�v�^(WEB3AdminOffFloorServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

/**
 * (�Ǘ��җ���O�����T�[�r�X�C���^�Z�v�^)<BR>
 * <BR>
 * �Ǘ��җ���O�����@@�\���ʂ́A�T�[�r�X�C���^�Z�v�^�N���X<BR>
 * <BR>
 * WEB3AdminOffFloorServiceInterceptor<BR>
 * <BR>
 * @@author Anil
 * @@version 1.0
 */

public class WEB3AdminOffFloorServiceInterceptor implements Interceptor
{
    /**
      * WEB3LogUtility log<BR>
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorServiceInterceptor.class);
    /**
     * @@roseuid 421AE4BA02C2
     */
    public WEB3AdminOffFloorServiceInterceptor()
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
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g��<BR>
     * �@@�@@�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h����O�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h����O�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�Ɖ�h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * ---<English>----------------<BR>
     * <BR>
     * When the service method begins, it is called.<<BR>
     * <BR>
     * Create WEB3GentradeTradingClendarContext that the trade calendar uses.<BR>
     * (It is called from the xTrade kernel before service is executed. )<BR>
     * <BR>
     * 1)Set the content to WEB3GentradeTradingClendarContext. <BR>
     * �@@�|Set the properties of WEB3GentradeTradingClendarContext from the content of
     * OpLoginSecurityService. <BR>
     * <BR>
     * �@@WEB3GentradeTradingClendarContext.InstitutionCode = Edit context from
     * OpLoginSecurityService.<BR>
     * �@@WEB3GentradeTradingClendarContext.branchCode = Edit from
     * OpLoginSecurityService.<BR>
     * �@@WEB3GentradeTradingClendarContext.marketCode = null<BR>
     * �@@WEB3GentradeTradingClendarContext.productCode = 0: Def.DEFAULT<BR>
     * �@@WEB3GentradeTradingClendarContext.tradingTimeType =
     * Def.SALES_OUTSIDE_MARKET<BR>
     * �@@WEB3GentradeTradingClendarContext.orderAcceptProduct =
     * Def.SALES_OUTSIDE_MARKET<BR>
     * �@@WEB3GentradeTradingClendarContext.orderAcceptTransaction = 07:
     * Def.REFERENCE<BR>
     * <BR>
     * �@@�|Set WEB3GentradeTradingClendarContext at
     * ThreadLocalSystemAttributesRegistry.setAttribute( ). <BR>
     * Set key: WEB3GentradeTradingTimeManagement,Def.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * 2)Set dateRoll and orderDate. <BR>
     * �@@�|Call WEB3GentradeTradingTimeManagement.setTimestamp().<BR>
     * <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * <BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * <BR>
     * -----<English>------------<BR>
     * <BR>
     * l_method<BR>
     * <BR>
     * service method object<BR>
     * <BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * <BR>
     * �T�[�r�X���\�b�h����<BR>
     * <BR>
     * l_serviceParams<BR>
     * <BR>
     * @@return Object
     * @@roseuid 41BCF919020F
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strMarketCode = null;
        try
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            long l_lngInstitutionId =
                Long.parseLong(l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.INSTITUTION_ID));
            long l_lngBranchId =
                Long.parseLong(l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.BRANCH_ID));
                
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            l_strInstitutionCode = l_accMgr.getInstitution(l_lngInstitutionId).getInstitutionCode();

            l_strBranchCode = l_accMgr.getBranch(l_lngBranchId).getBranchCode();
            
            l_context.setInstitutionCode(l_strInstitutionCode);
            l_context.setBranchCode(l_strBranchCode);
            l_context.setMarketCode(l_strMarketCode);
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET);
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            WEB3GentradeTradingTimeManagement.setTimestamp();
        } catch (NotFoundException l_nfe)
        {
            WEB3BaseRuntimeException l_runtimeException =
                new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            log.error(STR_METHOD_NAME, l_runtimeException);
            throw l_runtimeException;
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
     * <BR>
     * ----<English>--------------<BR>
     * <BR>
     * When the service method ends, it is called. <BR>
     * WEB3GentradeTradingClendarContext clear process<BR>
     * <BR>
     * Clear the following contents of ThreadLocalSystemAttributesRegistry.<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.Def.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.Def.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.Def.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_returnValue - (onCall���^�[���l)<BR>
     * <BR>
     * onCall���^�[���l<BR>
     * <BR>
     * l_returnValue<BR>
     * <BR>
     * @@param l_context - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * <BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * <BR>
     * service method return value<BR>
     * <BR>
     * @@roseuid 41BCF919021F
     */
    public void onReturn(Object l_returnValue, Object l_context)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
    }

    /**
     * (onThrowable)<BR>
     * <BR>
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
     * Clear the following contents of ThreadLocalSystemAttributesRegistry.<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.Def.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.Def.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.Def.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * <BR>
     * onCall���^�[���l<BR>
     * <BR>
     * l_obj<BR>
     * <BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * <BR>
     * ��O�I�u�W�F�N�g<BR>
     * <BR>
     * l_throwable<BR>
     * <BR>
     * @@roseuid 41BCF9190222
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
