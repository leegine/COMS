head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderBuyInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�������̓T�[�r�X�C���^�Z�v�^(WEB3EquityOrderBuyInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/01 �R�w�� (���u) �V�K�쐬
Revesion History : 2004/12/12 �X��   (SRA)  �c�Č��Ή�
Revesion History : 2005/01/05 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/12/25 �đo�g(���u) ���f��No.1087
Revesion History : 2008/04/10 �����Q(���u) ���f��No.1311
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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.equity.message.WEB3EquityBuyInputRequest;
import webbroker3.equity.message.WEB3EquityProductSelectRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����������t�������̓T�[�r�X�C���^�Z�v�^�j�B
 * @@version 1.0
 */
public class WEB3EquityOrderBuyInputServiceInterceptor implements Interceptor
{

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderBuyInputServiceInterceptor.class);

    /**
     * @@roseuid 409B2E04031C
     */
    public WEB3EquityOrderBuyInputServiceInterceptor()
    {

    }

    /**
     * (onCall)�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@?�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@?���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * �@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1) <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = (*2) <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h���t�i�V�K�����j�h <BR>
     * <BR>
     * �@@?ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �@@�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@?������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * (*1)��t���ԋ敪 <BR>
     * �@@���N�G�X�g�f�[�^�̌^=="�����������t�������̓��N�G�X�g"�̏ꍇ�A <BR>
     * �@@�@@?����敪=="����������"�̏ꍇ�́A"�����E�M�p"�B <BR>
     * �@@�@@?����敪����L�ȊO�̏ꍇ�́A"����O����"�B <BR>
     * �@@���N�G�X�g�f�[�^�̌^����L�ȊO�̏ꍇ�́A"�����E�M�p"�B <BR>
     * <BR>
     * (*2)������t���i <BR>
     * �@@���N�G�X�g�f�[�^�̌^=="�����������t�������̓��N�G�X�g"�̏ꍇ�A <BR>
     * �@@�@@?����敪=="����������"�̏ꍇ�́A"����"�B <BR>
     * �@@�@@?����敪����L�ȊO�̏ꍇ�́A"����O����"�B <BR>
     * �@@���N�G�X�g�f�[�^�̌^����L�ȊO�̏ꍇ�́A"����"�B <BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 406286410114
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String STR_METHOD_NAME = "onCall(Method , Object[] )";
        log.entering(STR_METHOD_NAME);        
    
        Object l_request = l_serviceParam[0];
        String l_strTradingTimeType = null;     //��t���ԋ敪
        String l_strOrderAccProduct = null;     //������t���i
        
        //���N�G�X�g�f�[�^�̌^��"�����������t�������̓��N�G�X�g"�̏ꍇ
        if (l_request instanceof WEB3EquityBuyInputRequest)
        {
            //���N�G�X�g�f�[�^�̎擾
            WEB3EquityBuyInputRequest l_inputRequest =
                (WEB3EquityBuyInputRequest) l_request;
                
            //����J�����_�R���e�L�X�g�ݒ�p�� ��t���ԋ敪�A������t���i�̎擾
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_inputRequest.tradingType))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
                l_strOrderAccProduct = WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET;
            }
        }
        //���N�G�X�g�f�[�^�̌^��"�����������t���������I�����N�G�X�g"�̏ꍇ
        else if (l_request instanceof WEB3EquityProductSelectRequest)
        {       
            //����J�����_�R���e�L�X�g�ݒ�p�̖����R�[�h�̎擾
            l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
            l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    
        long l_lngAccountId; // �����R�[�h
        String l_strInstitutionCode = null; // �،���ЃR�[�h
        String l_strBranchCode = null; // ���X�R�[�h
    
        //�Z�L�����e�B�T�[�r�X���擾
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        //AccountId���擾
        l_lngAccountId = l_opLoginSec.getAccountId();
        //FinApp�T�[�r�X���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        try
        {
            MainAccount l_acc;
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            l_strBranchCode = l_acc.getBranch().getBranchCode();
    
        }
        catch (NotFoundException nfe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }
    
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_strInstitutionCode);
        // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_strBranchCode);
        // ����J�����_�R���e�L�X�g.�s��R�[�h = null
        l_context.setMarketCode(null);
        //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(l_strTradingTimeType);
        // ����J�����_�R���e�L�X�g.������t���i = �h�����h
        l_context.setOrderAcceptProduct(l_strOrderAccProduct);
        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =�h���t�h
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
    
        // ������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
    
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);                  
        }
    
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (onReturn)�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * �����������t�������̓T�[�r�X�I�����ɃR�[�������B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * "system_timestamp" <BR>
     * "xblocks.gtl.attributes.bizdate.offset" <BR>
     * "web3.tradingcalendarcontext" <BR>
     * @@param l_returnValue - onCall���^�[���l
     * @@param l_context - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 406286410133
     */
    public void onReturn(Object l_returnValue, Object l_context)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null); 
    }

    /**
     * (onThroable)�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 406286410143
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);    
    }
}
@
