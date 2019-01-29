head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�������������T�[�r�X�C���^�Z�v�^�iWEB3EquityPTSOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 �И��� (���u) �V�K�쐬���f��No.1215
Revision History : 2008/01/24 �����F (���u) ���f��1294
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
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.util.WEB3LogUtility;


/**
 * ((PTS)�������������T�[�r�X�C���^�Z�v�^)<BR>
 * (PTS)�������������T�[�r�X�C���^�Z�v�^<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3EquityPTSOrderServiceInterceptor implements Interceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSOrderServiceInterceptor.class);

    /**
     * @@roseuid 4766071E0332
     */
    public WEB3EquityPTSOrderServiceInterceptor()
    {

    }

    /**
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁA<BR>
     * �@@�@@OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = ���N�G�X�g�f�[�^.�s��R�[�h��ҏW�B <BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h  <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "�����E�M�p"<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = "����" <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     * �@@�@@�@@�@@���N�G�X�g�f�[�^��蔻�肵�ĕҏW(*1) <BR>
     * <BR>
     * �@@(*1)���N�G�X�g�f�[�^�̌^���u�����������t�����m�F���N�G�X�g�v <BR>
     * �@@�@@�@@�@@�܂��́u�����������t�����������N�G�X�g�v�ł���΁A�h���t�h�B <BR>
     * �@@�@@�@@�@@��L�ȊO�ł���΁A�h���t�h�B <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�<BR>
     * �@@�@@������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �@@�@@�ݒ�L�[�FPTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|PTS������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@���N�G�X�g�f�[�^�̌^���u�����������t�����������N�G�X�g�v�܂��� <BR>
     * �@@�@@�@@�u�����������t�����������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B <BR>
     * <BR>
     * �@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@��������OpLoginSecurityService���ҏW�B <BR>
     * @@param l_method - (���\�b�h)<BR>
     * @@param l_serviceParams - (�T�[�r�X�̈���)<BR>
     * @@return Object
     * @@roseuid 474A9A78016C
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        //�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        Object l_request = l_serviceParams[0];
        //�s��R�[�h
        String l_strMarketCode = null;
        //������t�g�����U�N�V����
        String l_strOrderAccTransction = null;
        //�����������t�����m�F���N�G�X�g
        if (l_request instanceof WEB3EquityBuyConfirmRequest)
        {
            WEB3EquityBuyConfirmRequest l_buyConfirmRequest =
                (WEB3EquityBuyConfirmRequest)l_request;
            //�s��R�[�h
            l_strMarketCode = l_buyConfirmRequest.marketCode;
            //������t�g�����U�N�V���� �h���t�h
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
        }
        //�����������t�����������N�G�X�g
        else if (l_request instanceof WEB3EquityBuyCompleteRequest)
        {
            WEB3EquityBuyCompleteRequest l_buyCompleteRequest =
                (WEB3EquityBuyCompleteRequest)l_request;
            //�s��R�[�h
            l_strMarketCode = l_buyCompleteRequest.marketCode;
            //������t�g�����U�N�V���� �h���t�h
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
        }
        //�����������t�����������N�G�X�g
        else if (l_request instanceof WEB3EquitySellCompleteRequest)
        {
            WEB3EquitySellCompleteRequest l_sellCompleteRequest =
                (WEB3EquitySellCompleteRequest)l_request;
            //�s��R�[�h
            l_strMarketCode = l_sellCompleteRequest.marketCode;
            //������t�g�����U�N�V���� �h���t�h
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
        }
        //�����������t�����m�F���N�G�X�g
        else if (l_request instanceof WEB3EquitySellConfirmRequest)
        {
            WEB3EquitySellConfirmRequest l_sellConfirmRequest =
                (WEB3EquitySellConfirmRequest)l_request;
            //�s��R�[�h
            l_strMarketCode = l_sellConfirmRequest.marketCode;
            //������t�g�����U�N�V���� �h���t�h
            l_strOrderAccTransction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        //����J�����_�R���e�L�X�g
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        OpLoginSecurityService l_opLoginService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        try
        {
            long l_lngAccountId = l_opLoginService.getAccountId();
            l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
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

        //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_strInstitutionCode);
        //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_strBranchCode);
        //����J�����_�R���e�L�X�g.�s��R�[�h = ���N�G�X�g�f�[�^.�s��R�[�h��ҏW�B
        l_context.setMarketCode(l_strMarketCode);
        //����J�����_�R���e�L�X�g.��t���ԋ敪 = "�����E�M�p"
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        //����J�����_�R���e�L�X�g.������t���i = "����"
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = ���N�G�X�g�f�[�^��蔻�肵��
        l_context.setOrderAcceptTransaction(l_strOrderAccTransction);
        //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        //������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        try
        {
            //��t�����A���t���[�����Z�b�g����B
            WEB3EquityPTSTradingTimeManagement.setTimestamp();

            //���N�G�X�g�f�[�^�̌^���u�����������t�����������N�G�X�g�v�܂���
            //�u�����������t�����������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B
            if (l_request instanceof WEB3EquityBuyCompleteRequest
                || l_request instanceof WEB3EquitySellCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_mainAccount.getAccountCode());
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
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
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * PTS�������������T�[�r�X�I�����ɃR�[�������B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * PTS������ԊǗ�.TIMESTAMP_TAG <BR>
     * PTS������ԊǗ�.OFFSET_TAG <BR>
     * PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 474A9ABD0012
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        //PTS������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG, null);

        //PTS������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.OFFSET_TAG, null);

        //PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * ��O�������ɃR�[�������B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * PTS������ԊǗ�.TIMESTAMP_TAG <BR>
     * PTS������ԊǗ�.OFFSET_TAG <BR>
     * PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 474A9B0600AC
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        //PTS������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG, null);

        //PTS������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.OFFSET_TAG, null);

        //PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
