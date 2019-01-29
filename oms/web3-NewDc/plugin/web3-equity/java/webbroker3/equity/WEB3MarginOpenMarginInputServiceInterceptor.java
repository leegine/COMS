head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K�����̓T�[�r�X�C���^�Z�v�^(WEB3MarginOpenMarginInputServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10   ���Ō� (Sinocom) �V�K�쐬 
                   2005/01/05 ����   (SRA) �g�p����Ȃ�import�����폜
                   2006/12/26 �����F (���u) ���f�� 1087
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginOpenMarginInputRequest;
import webbroker3.equity.message.WEB3MarginProductSelectRequest;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

/**
 * �i�M�p����V�K�����̓T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �M�p����V�K�����̓T�[�r�X�C���^�Z�v�^�N���X
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3MarginOpenMarginInputServiceInterceptor implements Interceptor 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOpenMarginInputServiceInterceptor.class);
            
    /**
     * @@roseuid 4142B32D035C
     */
    public WEB3MarginOpenMarginInputServiceInterceptor() 
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
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *       OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �s��.is�D��s��R�[�h( )==true�̏ꍇnull�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A���N�G�X�g�f�[�^.�s��R�[�h��ҏW�B<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     *      ������t�g�����U�N�V����(*)<BR>
     * <BR>
     * �@@(*)������t�g�����U�N�V����<BR>
     * �@@�|���N�G�X�g�f�[�^.����敪 == <BR>
     *        �h�V�K���������h�̏ꍇ�A�h01�F���t�i�V�K�����j�h<BR>
     * �@@�|���N�G�X�g�f�[�^.����敪 == <BR>
     *        �h�V�K���������h�̏ꍇ�A�h02�F���t�i�V�K�����j�h<BR>
     * �@@�|�ȊO(���N�G�X�g�f�[�^�ݒ�Ȃ���)�A�h01�F���t�i�V�K�����j�h���Z�b�g�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - �T�[�r�X���\�b�h����<BR>
     * @@return Object<BR>
     * @@roseuid 407BBBDB01F3<BR>
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        String l_strMarketCode = null;
        String l_strTradingType = null;
        WEB3MarginOpenMarginInputRequest l_request = null;
        
        log.debug("l_serviceParams[0] = " + l_serviceParams[0].getClass().getName());
        
        if (l_serviceParams[0] instanceof WEB3MarginOpenMarginInputRequest)
        {
            l_request = (WEB3MarginOpenMarginInputRequest)l_serviceParams[0];
            l_strMarketCode = l_request.marketCode;
            l_strTradingType = l_request.tradingType;
        }
        else if ((l_serviceParams[0] instanceof WEB3MarginProductSelectRequest))
        {
            l_strMarketCode = null;            
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        
        try
        {
            
            
            //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
        
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
            OpLoginSecurityService l_opLoginSecurityService = (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        
            MainAccount l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_opLoginSecurityService.getAccountId());
        
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
            
            //�@@����J�����_�R���e�L�X�g.�s��R�[�h = �s��.is�D��s��R�[�h( )==true�̏ꍇnull�B
            //�@@�@@�@@�@@�@@                          �@@�ȊO�A���N�G�X�g�f�[�^.�s��R�[�h��ҏW�B
            if (WEB3GentradeMarket.isPriorityMarket(l_strMarketCode))
            {
                l_strMarketCode = null;
            }
            l_context.setMarketCode(l_strMarketCode);
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = 
            //�i�|���N�G�X�g�f�[�^.����敪 == �h�V�K���������h�̏ꍇ�A�h01�F���t�i�V�K�����j�h
            //  �|���N�G�X�g�f�[�^.����敪 == �h�V�K���������h�̏ꍇ�A�h02�F���t�i�V�K�����j�h
            //  �|�ȊO(���N�G�X�g�f�[�^�ݒ�Ȃ���)�A�h01�F���t�i�V�K�����j�h���Z�b�g�B)
            if (WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(l_strTradingType))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            }
            else if (WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(l_strTradingType))
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
            }
            else
            {
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            }
            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
            
            //��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();
                   
        }
        catch (NotFoundException l_nfex)
        {
            log.error(STR_METHOD_NAME, l_nfex);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_webex)
        {
            log.error(STR_METHOD_NAME, l_webex);
            throw new WEB3BaseRuntimeException(l_webex.getErrorInfo(), STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 407BBBDB0212
     */
    public void onReturn(Object l_context, Object l_returnValue) 
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
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 407BBBDB0222
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
