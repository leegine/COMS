head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�����T�[�r�X�C���^�Z�v�^�N���X(WEB3MstkSellServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 ���C�g (���u) �V�K�쐬
                   2004/12/10 �����a��(SRA) �c�Č��Ή� �m��.�Q�X�T
                   2004/12/29 �����a��(SRA) JavaDoc�C��
                   2005/01/05 �����a��(SRA) �������b�N�C��
*/

package webbroker3.equity;

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
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3MstkSellCompleteRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkSellServiceImpl;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j�������t�����T�[�r�X�C���^�Z�v�^�j�B<br>
 * <br>
 * �����~�j�������t�����T�[�r�X�C���^�Z�v�^�N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellServiceInterceptor implements Interceptor 
{
    
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellServiceImpl.class);
    
    /**
     * 
     */
    public WEB3MstkSellServiceInterceptor() 
    {
     
    }
    
    /**
     * �ionCall�j�B<br>
     * <br>
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService��<BR>
     * ���e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     * OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     * OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h05�F�����~�j�����h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h02�F�����~�j�����h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =  <BR>
     * �h02�F���t�i�V�K�����j�h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * ������ԃR���e�L�X�g���Z�b�g����B <BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �R�j�@@���N�G�X�g�f�[�^�̌^���u�����~�j�������t�����������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�@@�@@��������OpLoginSecurityService���ҏW�B
     * @@param l_method (arg0)
     * @@param l_serviceParams (arg1)
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        
        final String STR_METHOD_NAME = " onCall(Method,Object[])";
         log.entering(STR_METHOD_NAME);

         //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
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
             log.debug("getService(OpLoginSecurityService.class): " + l_opLoginSec);

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
             log.debug("������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g: ENTER");
             //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
             l_context.setInstitutionCode(l_strInstitutionCode);
             //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
             l_context.setBranchCode(l_strBranchCode);
             //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
             l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
             //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h05�F�����~�j�����h
             l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MINI_STOCK);
             //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
             l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
             //����J�����_�R���e�L�X�g.������t���i = �h02�F�����~�j�����h 
             l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MINI_STOCK);
             //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h02�F���t�i�V�K�����j�h
             l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
             log.debug("������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g: END");

             //������ԃR���e�L�X�g���Z�b�g����B
             log.debug("������ԃR���e�L�X�g���Z�b�g: ENTER");
             ThreadLocalSystemAttributesRegistry.setAttribute(
                 WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                 l_context);
             log.debug("������ԃR���e�L�X�g���Z�b�g: END");

             //�Q�j�@@��t�����A���t���[�����Z�b�g����B
             //WEB3SystemLayerException
             log.debug("��t�����A���t���[�����Z�b�g: ENTER");
             WEB3GentradeTradingTimeManagement.setTimestamp();
             log.debug("��t�����A���t���[�����Z�b�g: END");

             log.exiting(STR_METHOD_NAME);
             
             WEB3GenRequest l_request =(WEB3GenRequest) l_serviceParams[0];
             
             if(l_request instanceof WEB3MstkSellCompleteRequest)
             {
                 log.debug("�g���A�J�E���g�}�l�[�W���̌��������b�N���܂��B");
                 WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                 // �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
                 l_accountManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_mainAccount.getAccountCode());
             }
             return l_context;
         }
         catch (NotFoundException l_ex)
         {
             log.error(
                 l_ex.getMessage(),
                 new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     l_ex.toString(),
                     l_ex));
         }
         catch (WEB3BaseException l_be)
         {   
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseRuntimeException(
                 l_be.getErrorInfo(),                
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_be.getMessage(), l_be);          
         }
         
         log.exiting(STR_METHOD_NAME);
         return null;
    }
    
    /**
     * �ionReturn�j�B<br>
     * <BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context (arg0)
     * @@param l_returnValue (arg1)
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object,Object)";
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
     * �ionThrowable�j�B<br>
     * <br>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj (arg0)
     * @@param l_throwable (arg1)
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object,Throwable)";
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
