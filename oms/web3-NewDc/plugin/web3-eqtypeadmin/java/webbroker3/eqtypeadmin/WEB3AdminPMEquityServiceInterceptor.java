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
filename	WEB3AdminPMEquityServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���i�Ǘ�(����)�T�[�r�X�C���^�Z�v�^(WEB3AdminPMEquityServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin;

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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

/**
 * �i���i�Ǘ�(����)�T�[�r�X�C���^�Z�v�^�j<BR>
 * <BR>
 * ���i�Ǘ�(����)�T�[�r�X�C���^�Z�v�^<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminPMEquityServiceInterceptor<BR>
 * <BR>
 * WEB3AdminPMEquityServiceInterceptor class<BR>
 * <BR>
 * @@author Saravanan Krishnamoorthy
 * @@version 1.0
 */
public class WEB3AdminPMEquityServiceInterceptor implements Interceptor
{

    /**
      * WEB3LogUtility log<BR>
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMEquityServiceInterceptor.class);

   /**
    * @@roseuid 41FD8E5F001F
    */
   public WEB3AdminPMEquityServiceInterceptor()
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
    * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h1�F�����h <BR>
    * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h <BR>
    * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h <BR>
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
    * ----<English>--------------------<BR>
    * <BR>
    * Generate WEB3GentradeTradingClendarContext that the trade calender uses.<BR>
    * (It is called from the xTrade kernel  before service is executed. )<BR>
    * <BR>
    * �P�j�@@Set the value of WEB3GentradeTradingClendarContext. <BR>
    * �@@�|Set the property of WEB3GentradeTradingClendarContext�@@from the content of
    * OpLoginSecurityService. <BR>
    * <BR>
    * �@@WEB3GentradeTradingClendarContext.InstitutionCode = Edit from
    * OpLoginSecurityService.<BR>
    * �@@WEB3GentradeTradingClendarContext.branchCode =  Edit
    * OpLoginSecurityService.<BR>
    *   WEB3GentradeTradingClendarContext.marketCode = �h1: Def.TOKYO�h <BR>
    * �@@WEB3GentradeTradingClendarContext.tradingTimeType = �h01: Def.EQUITY�h <BR>
    * �@@WEB3GentradeTradingClendarContext.productCode = �h0: Def.DEFAULT�h <BR>
    * �@@WEB3GentradeTradingClendarContext.orderAcceptProduct = �h00: Def.DEFAULT�h
    * <BR>
    * �@@WEB3GentradeTradingClendarContext.orderAcceptTransaction = �h07:
    * Def.REFERENCE�h<BR>
    * �|WEB3GentradeTradingClendarContext is set with
    * ThreadLocalSystemAttributesRegistry.setAttribute( ). <BR>
    *      Set key�F
    * WEB3GentradeTradingTimeManagement.Def.TRADING_CAL_CONTEXT_PATH<BR>
    * <BR>
    * �Q�j�@@Set dateRoll and orderDate. <BR>
    * �@@�|Call  WEB3GentradeTradingTimeManagement.setTimestamp().<BR>
    * @@param l_arg0 Method
    * @@param l_arg1 Object[]
    * @@return Object
    * @@roseuid 41996AA901D4
    */
   public Object onCall(java.lang.reflect.Method l_arg0, Object[] l_arg1)
   {
       final String STR_METHOD_NAME = "onCall(Method,Object[])";
       log.entering(STR_METHOD_NAME);
       WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
       String l_strInstitutionCode = null;
       String l_strBranchCode = null;
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
       l_context.setMarketCode(WEB3MarketCodeDef.TOKYO);
       l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
       l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
       l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
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
       return l_context;
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
    * When the servce method method ends, it is called. <BR>
    * WEB3GentradeTradingClendarContext clear process<BR>
    * <BR>
    * Clear the following content of ThreadLocalSystemAttributesRegistry.<BR>
    * <BR>
    * WEB3GentradeTradingTimeManagement.Def.TIMESTAMP_TAG<BR>
    * WEB3GentradeTradingTimeManagement.Def.OFFSET_TAG<BR>
    * WEB3GentradeTradingTimeManagement.Def.TRADING_CAL_CONTEXT_PATH<BR>
    * <BR>
    * @@param l_arg0 Object
    * @@param l_arg1 Object
    * @@roseuid 41996AA90203
    */
   public void onReturn(Object l_arg0, Object l_arg1)
   {
       final String STR_METHOD_NAME = "onReturn(Object,Object)";
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

       log.exiting(STR_METHOD_NAME);
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
    * When the servce method method ends, it is called. <BR>
    * WEB3GentradeTradingClendarContext clear process<BR>
    * <BR>
    * Clear the following content of ThreadLocalSystemAttributesRegistry.<BR>
    * <BR>
    * WEB3GentradeTradingTimeManagement.Def.TIMESTAMP_TAG<BR>
    * WEB3GentradeTradingTimeManagement.Def.OFFSET_TAG<BR>
    * WEB3GentradeTradingTimeManagement.Def.TRADING_CAL_CONTEXT_PATH<BR>
    * <BR>
    * @@param l_arg0 Object
    * @@param l_arg1 Throwable
    * @@roseuid 41996AA90222
    */
   public void onThrowable(Object l_arg0, java.lang.Throwable l_arg1)
   {
       final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
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

       log.exiting(STR_METHOD_NAME);
   }
}
@
