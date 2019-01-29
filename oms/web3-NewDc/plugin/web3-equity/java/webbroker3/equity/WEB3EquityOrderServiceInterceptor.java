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
filename	WEB3EquityOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������T�[�r�X�C���^�Z�v�^(WEB3EquityOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 �^��(���u) �V�K�쐬
Revesion History : 2004/12/20 �������F(SRA) �c�Č��Ή��ɂ��C��
Revesion History : 2005/01/05 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/12/25 �đo�g(���u) ���f��No.1087
*/
package webbroker3.equity;

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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR> 
 * ���������T�[�r�X�ɓo�^����C���^�Z�v�^�N���X�B
 * @@version 1.0
 */
public class WEB3EquityOrderServiceInterceptor
    implements Interceptor
{

    /**
     * �i�R���X�g���N�^�j�B
     * @@roseuid 402219B1036A
     */
    public WEB3EquityOrderServiceInterceptor()
    {
    }

    /**
     * �iLogger�j�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderServiceInterceptor.class);
    
    /**
     * �ionCall�j�B<BR>
     *<BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �s��.is�D��s��R�[�h( )==true�̏ꍇnull�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A���N�G�X�g�f�[�^.�s��R�[�h��ҏW�B<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1)<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = (*2)<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = ���N�G�X�g�f�[�^��蔻�肵�ĕҏW(*3)<BR>
     * <BR>
     * �@@(*1)��t���ԋ敪<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^�̌^���u�����������t�����m�F���N�G�X�g�v<BR>
     * �@@�@@�@@�@@�܂��́u�����������t�����������N�G�X�g�v�̏ꍇ�́A�ȉ��̒ʂ�B<BR>
     * �@@�@@�@@�@@�|����敪=="����������"�̏ꍇ�́A"�����E�M�p"�B<BR>
     * �@@�@@�@@�@@�|����敪����L�ȊO�̏ꍇ�́A"����O����"�B<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^�̌^����L�ȊO�̏ꍇ�́A"�����E�M�p"�B<BR>
     * <BR>
     * �@@(*2)������t���i<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^�̌^���u�����������t�����m�F���N�G�X�g�v<BR>
     * �@@�@@�@@�@@�܂��́u�����������t�����������N�G�X�g�v�̏ꍇ�́A�ȉ��̒ʂ�B<BR>
     * �@@�@@�@@�@@�|����敪=="����������"�̏ꍇ�́A"����"�B<BR>
     * �@@�@@�@@�@@�|����敪����L�ȊO�̏ꍇ�́A"����O����"�B<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^�̌^����L�ȊO�̏ꍇ�́A"����"�B<BR>
     * <BR>
     * �@@(*3)���N�G�X�g�f�[�^�̌^���u�����������t�����m�F���N�G�X�g�v<BR>
     * �@@�@@�@@�@@�܂��́u�����������t�����������N�G�X�g�v�ł���΁A�h���t�h�B<BR>
     * �@@�@@�@@�@@��L�ȊO�ł���΁A�h���t�h�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �R�j�@@���N�G�X�g�f�[�^�̌^���u�����������t�����������N�G�X�g�v�܂���<BR>
     * �@@�@@�@@�u�����������t�����������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�@@�@@��������OpLoginSecurityService���ҏW�B
     * @@param l_method �i���\�b�h�j�B<BR>
     * �@@�@@�@@���\�b�h�B
     * @@param l_serviceParam -�i�T�[�r�X�̈����j�B<BR>
     * �@@�@@�@@�T�[�r�X�̃��\�b�h�ɓn���������B<BR>
     * �@@�@@�@@���������T�[�r�X�̏ꍇ�A�����������N�G�X�g�I�u�W�F�N�g�B
     * @@return Object<BR>
     * @@roseuid 4014AC0201A0
     */
    public java.lang.Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        // �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        Object l_request = l_serviceParam[0];
        String l_strMarketCode = null;
        String l_strTradingTimeType = null;
        String l_strOrderAccProduct = null;
        String l_strOrderAccTransction = null;
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            WEB3EquityBuyConfirmRequest l_buyConfirmRequest =
                (WEB3EquityBuyConfirmRequest)l_request;
            l_strMarketCode = l_buyConfirmRequest.marketCode;
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_buyConfirmRequest.tradingType))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
                l_strOrderAccProduct = WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET;
            }
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
        }
        else if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            WEB3EquityBuyCompleteRequest l_buyCompleteRequest =
                (WEB3EquityBuyCompleteRequest)l_request;
            l_strMarketCode = l_buyCompleteRequest.marketCode;
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_buyCompleteRequest.tradingType))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
            }
            else
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
                l_strOrderAccProduct = WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET;
            }
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
        }
        else if (l_request instanceof WEB3EquitySellConfirmRequest)
        {
            WEB3EquitySellConfirmRequest l_sellConfirmRequest =
                (WEB3EquitySellConfirmRequest)l_request;
            l_strMarketCode = l_sellConfirmRequest.marketCode;
            l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
            l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
        }
        else if (l_request instanceof WEB3EquitySellCompleteRequest)
        {
            WEB3EquitySellCompleteRequest l_sellCompleteRequest =
                (WEB3EquitySellCompleteRequest)l_request;
            l_strMarketCode = l_sellCompleteRequest.marketCode;
            l_strTradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
            l_strOrderAccProduct = WEB3OrderAccProductDef.STOCK;
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
        }
        else
        {
            log.error("__���������T�[�r�X�͖��Ή��ł�__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
        // �@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        WEB3GentradeMainAccount l_account = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        OpLoginSecurityService l_opLoginSec =
              (OpLoginSecurityService) Services.getService(
                  OpLoginSecurityService.class);
        try
        {
            long l_lngAccountId = l_opLoginSec.getAccountId();
            l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_account.getInstitution().getInstitutionCode();
            l_strBranchCode = l_account.getBranch().getBranchCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_strInstitutionCode);
        // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_strBranchCode);
        // ����J�����_�R���e�L�X�g.�s��R�[�h = �s��.is�D��s��R�[�h( )==true�̏ꍇnull�B
        // �ȊO�A���N�G�X�g�f�[�^.�s��R�[�h��ҏW�B
        if (WEB3GentradeMarket.isPriorityMarket(l_strMarketCode))
        {
            l_context.setMarketCode(null);
        }
        else
        {
            l_context.setMarketCode(l_strMarketCode);
        }
        // ����J�����_�R���e�L�X�g.��t���ԋ敪
        l_context.setTradingTimeType(l_strTradingTimeType);
        // ����J�����_�R���e�L�X�g.������t���i
        l_context.setOrderAcceptProduct(l_strOrderAccProduct);
        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V����
        l_context.setOrderAcceptTransaction(l_strOrderAccTransction);
        //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT); 

        // ������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
              l_context);
        try
        {
            // �Q�j�@@��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            // �R�j�@@���N�G�X�g�f�[�^�̌^���u�����������t�����������N�G�X�g�v�܂���
            // �@@�@@�@@�u�����������t�����������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B
            if (l_request instanceof WEB3EquityBuyCompleteRequest ||
                l_request instanceof WEB3EquitySellCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_account.getAccountCode());
            }
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
     * �ionReturn�j�B<BR>
     *<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * ���������T�[�r�X�I�����ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_context �ionCall���^�[���l�j�B
     * @@param l_returnValue �i�T�[�r�X���\�b�h���^�[���l�j�B
     * @@roseuid 4014AC0202C9
     */
    public void onReturn(
        Object l_context,
        Object l_returnValue)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
    }

    /**
     * �ionThrowable�j�B<BR>
     *<BR> 
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * ��O�������ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj �ionCall���^�[���l�j�B
     * @@param l_throwable �i��O�I�u�W�F�N�g�j�B
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
    }
    
}
@
