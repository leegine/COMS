head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoBalanceReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�c���Ɖ�T�[�r�X�C���^�Z�v�^(WEB3IfoBalanceReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ �V�K�쐬         
*/
package webbroker3.ifo;

import java.lang.reflect.Method;
import java.util.Hashtable;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalRequest;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�敨OP�c���Ɖ�T�[�r�X�C���^�Z�v�^)<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3IfoBalanceReferenceServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoBalanceReferenceServiceInterceptor.class);
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@�����̕ϐ����Z�b�g����B<BR>
     * ThreadLocalSystemAttributesRegistry.setAttribute()<BR>
     * �ɂ�ThreadLocal�Ɏ����̕ϐ����Z�b�g����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ݒ�L�[�F�@@CURRENT_PRICE_INFO<BR>
     * �@@�@@�@@�@@�@@�@@�@@�l�F�@@Hashtable�i�V�K�ɐ�������Hashtable�j<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService��<BR>
     * ���e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = null<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i =<BR>
     * �@@�@@[���N�G�X�g�f�[�^.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ]<BR>
     * �@@�@@�@@�h05�F�敨�h���Z�b�g�B<BR>
     * �@@�@@[���N�G�X�g�f�[�^.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�@@�h06�F�I�v�V�����h���Z�b�g�B<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *     �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParams - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 41AACA560276
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = getClass().getName() + ".onCall(Method,Object[])";
  
        log.entering(STR_METHOD_NAME);
  
        //Hashtable�i�V�K�ɐ�������Hashtable�j
        Hashtable l_currentPriceVariable = new Hashtable();
  
        //�P�j�@@�����̕ϐ����Z�b�g����B
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE_INFO, l_currentPriceVariable);
  
        //�Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        String l_strInstitutionCode = null; //�،���ЃR�[�h
        String l_strBranchCode = null; //���X�R�[�h
  
        try
        {
            long l_lngAccountId; // �����R�[�h
            FinApp l_finApp; //FinancialApplication
            AccountManager l_accMgr;
            MainAccount l_mainAccount;
  
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
  
            //MainAccount���擾
            l_lngAccountId = l_opLoginSec.getAccountId();           
            l_finApp = (FinApp)Services.getService(FinApp.class);
            l_accMgr = l_finApp.getAccountManager();
            //NotFoundException
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
  
            //�،���ЃR�[�h���擾
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            //���X�R�[�h���擾
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
  
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
  
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            //����J�����_�R���e�L�X�g.���i�R�[�h = null
            l_context.setProductCode(null);
            if (l_serviceParams[0]instanceof WEB3FuturesOptionsBalanceReferenceTotalRequest)
            {
                WEB3FuturesOptionsBalanceReferenceTotalRequest l_request = (WEB3FuturesOptionsBalanceReferenceTotalRequest)l_serviceParams[0];
                if (WEB3FuturesOptionDivDef.OPTION.equals(l_request.fuOpDiv))
                {
                    //����J�����_�R���e�L�X�g.������t���i = �h06�F�I�v�V�����h
                    l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
                }
                else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_request.fuOpDiv))
                {
                    //����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h���Z�b�g�B�h
                    l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
                }
            }
            else if (l_serviceParams[0]instanceof WEB3FuturesOptionsBalanceReferenceRequest)
            {
                WEB3FuturesOptionsBalanceReferenceRequest l_request = (WEB3FuturesOptionsBalanceReferenceRequest)l_serviceParams[0];
                  if (WEB3FuturesOptionDivDef.OPTION.equals(l_request.fuOpDiv))
                  {
                      //����J�����_�R���e�L�X�g.������t���i = �h06�F�I�v�V�����h
                      l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
                  }
                  else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_request.fuOpDiv))
                  {
                      //����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h���Z�b�g�B�h
                      l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
                  }
            }
  
  
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =�h07�F�Ɖ�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
  
            //������ԃR���e�L�X�g���Z�b�g����B
  
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
  
            //�R�j�@@��t�����A���t���[�����Z�b�g����B
            //WEB3SystemLayerException
  
            WEB3GentradeTradingTimeManagement.setTimestamp();
  
            log.exiting(STR_METHOD_NAME);
  
            return l_context;
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "�e�[�u���ɊY������f�[�^������܂���B",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex));
  
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(
                "�Ɩ��A�v���P�[�V�����Ŏg�p����V�X�e���G���[�̗�O",
                    l_ex);
  
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�A����ю����̃N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE_INFO<BR>
     * 
     * @@param l_context
     *  - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41AACA560295
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = getClass().getName() + ".onReturn(Object,Object)";
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
  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3IfoAttributeNameDef.CURRENT_PRICE_INFO,
            null);
  
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�A����ю����̃N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE_INFO<BR>
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 41AACA5602A5
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = getClass().getName() + ".onThrowable(Object,Throwable)";
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
  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3IfoAttributeNameDef.CURRENT_PRICE_INFO,
            null);
  
        log.exiting(STR_METHOD_NAME);
    }
}
@
