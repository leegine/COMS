head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����p���̓T�[�r�X�C���^�Z�v�^(WEB3BondSellInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�����p���̓T�[�r�X�C���^�Z�v�^)<BR>
 * �����p���̓T�[�r�X�C���^�Z�v�^<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondSellInputServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSellInputServiceInterceptor.class);
    
    /**
     * @@roseuid 44FCD0BB029F
     */
    public WEB3BondSellInputServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@-���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     * �@@�@@�@@�@@�@@OpLoginSecurityService���ҏW<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = "0�FDEFAULT"<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "25�F��"<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t���i = "28�F��"<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "02�F���t"<BR>
     * <BR>
     * �@@-ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@�msetAttribute�ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݒ�L�[: ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * �@@�@@�@@�ݒ�l: ������ԃR���e�L�X�g<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h<BR>
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 44E9911F0232
     */
    public java.lang.Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        try
        {
            //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 
            //�@@-���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g�� 
            //�@@�v���p�e�B���Z�b�g����B
            OpLoginSecurityService l_opLoginSecurityService = 
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            
            FinApp l_finapp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finapp.getAccountManager();
            
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            
            //�،���ЃR�[�h
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();
            
            //���X�R�[�h
            String l_strBranchCode =
                l_mainAccount.getBranch().getBranchCode();
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW 
            l_context.setBranchCode(l_strBranchCode);
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = "0�FDEFAULT" 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "25�F��" 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.������t���i = "28�F��" 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.BOND);
            
            //�@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "02�F���t" 
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
            
            //�@@-ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g�� 
            //�@@�Z�b�g����B 
            //�@@�@@�msetAttribute�ɓn���p�����^�n 
            //�@@�@@�@@�ݒ�L�[: ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            //�@@�@@�@@�ݒ�l: ������ԃR���e�L�X�g 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);            

            //�Q�j�@@��t�����A���t���[�����Z�b�g����B 
            //�@@������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getMainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
   
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_returnValue - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_context - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 44E9911F0251
     */
    public void onReturn(Object l_returnValue, Object l_context) 
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object )";
        log.entering(STR_METHOD_NAME);
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //������ԊǗ�.OFFSET_TAG  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 44E9911F0261
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        //������ԊǗ�.TIMESTAMP_TAG  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        //������ԊǗ�.OFFSET_TAG  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(STR_METHOD_NAME);       
    }
}
@