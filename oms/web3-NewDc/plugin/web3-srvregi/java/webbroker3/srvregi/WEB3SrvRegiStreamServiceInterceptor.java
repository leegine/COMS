head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p���A�g�T�[�r�X�C���^�Z�v�^(WEB3SrvRegiStreamServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 �g�C�� �V�K�쐬 ���f��371
*/

package webbroker3.srvregi;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p���A�g�T�[�r�X�C���^�Z�v�^)<BR>
 * �T�[�r�X���p���A�g�T�[�r�X�C���^�Z�v�^<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3SrvRegiStreamServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamServiceInterceptor.class);

   /**
    * @@roseuid 4831260B033C
    */
   public WEB3SrvRegiStreamServiceInterceptor()
   {

   }

   /**
    * �T�[�r�X���\�b�h�J�n���ɃR�[�������B  <BR>
    * <BR>
    * ����J�����_�����p����R���e�L�X�g�𐶐�����B  <BR>
    * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j  <BR>
    * <BR>
    * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B  <BR>
    * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X<BR>
    * �@@�@@�@@�g�̃v���p�e�B���Z�b�g����B  <BR>
    * <BR>
    * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW  <BR>
    * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW  <BR>
    * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
    * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h27�F�T�[�r�X���p�h <BR>
    * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
    * �@@����J�����_�R���e�L�X�g.������t���i = �h21�F�T�[�r�X���p�h <BR>
    * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h <BR>
    * <BR>
    * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B  <BR>
    * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
    * <BR>
    * �Q�j�@@��t�����A���t���[�����Z�b�g����B  <BR>
    * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
    * <BR>
    * @@param l_method - (�T�[�r�X���\�b�h)<BR>
    * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
    * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
    * �T�[�r�X���\�b�h����<BR>
    * @@return Object
    * @@roseuid 48159BFD02D3
    */
   public Object onCall(Method l_method, Object[] l_serviceParams)
   {
       final String STR_METHOD_NAME = "onCall(Method, Object[])";
       log.entering(STR_METHOD_NAME);

       OpLoginSecurityService l_opLoginSec =
           (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

       //����J�����_�R���e�L�X�g�𐶐�����
       WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

       try
       {
           //FinApp�T�[�r�X���擾
           FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

           AccountManager l_accountMgr = l_finApp.getAccountManager();
           MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_opLoginSec.getAccountId());

           //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
           l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());

           //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
           l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());

           //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
           l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

           //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h27�F�T�[�r�X���p�h
           l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SRVREGI);

           //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
           l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

           //����J�����_�R���e�L�X�g.������t���i = �h21�F�T�[�r�X���p�h
           l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.SRV_REGI);

           //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h
           l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

           //������ԃR���e�L�X�g���Z�b�g����B
           ThreadLocalSystemAttributesRegistry.setAttribute(
               WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
               l_context);

           //��t�����A���t���[�����Z�b�g����B
           WEB3GentradeTradingTimeManagement.setTimestamp();

       }
       catch (NotFoundException l_ex)
       {
           log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
       catch (WEB3BaseException l_ex)
       {
           log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
           log.exiting(STR_METHOD_NAME);
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
    * <BR>
    * ����J�����_�R���e�L�X�g�N���A�����B <BR>
    * <BR>
    * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
    * <BR>
    * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
    * �@@������ԊǗ�.OFFSET_TAG <BR>
    * �@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
    * <BR>
    * @@param l_context - (onCall���^�[���l)<BR>
    * onCall���^�[���l<BR>
    * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
    * �T�[�r�X���\�b�h���^�[���l<BR>
    * @@roseuid 48159BFD02E3
    */
   public void onReturn(Object l_context, Object l_returnValue)
   {
       final String STR_METHOD_NAME = "onReturn(Object,Object)";
       log.entering(STR_METHOD_NAME);

       //������ԊǗ�.TIMESTAMP_TAG
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
           null);

       //������ԊǗ�.OFFSET_TAG
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.OFFSET_TAG,
           null);

       //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
           null);

       log.exiting(STR_METHOD_NAME);
   }

   /**
    * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
    * <BR>
    * ����J�����_�R���e�L�X�g�N���A�����B <BR>
    * <BR>
    * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
    * <BR>
    * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
    * �@@������ԊǗ�.OFFSET_TAG <BR>
    * �@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
    * <BR>
    * @@param l_obj - (onCall���^�[���l)<BR>
    * onCall���^�[���l<BR>
    * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
    * ��O�I�u�W�F�N�g<BR>
    * @@roseuid 48159BFD02F2
    */
   public void onThrowable(Object l_obj, Throwable l_throwable)
   {
       final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
       log.entering(STR_METHOD_NAME);

       //������ԊǗ�.TIMESTAMP_TAG
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
           null);

       //������ԊǗ�.OFFSET_TAG
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.OFFSET_TAG,
           null);

       //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
           null);

       log.exiting(STR_METHOD_NAME);
   }
}@