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
filename	WEB3EquityPTSChangeOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�����������������T�[�r�X�C���^�Z�v�^(WEB3EquityPTSChangeOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 �����F (���u) �V�K�쐬 ���f��1241
Revision History : 2008/01/24 �����F (���u) ���f��1294
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * ((PTS)�����������������T�[�r�X�C���^�Z�v�^)<BR>
 * (PTS)�����������������T�[�r�X�ɓo�^����C���^�Z�v�^�N���X�B<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3EquityPTSChangeOrderServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSChangeOrderServiceInterceptor.class);

    /**
     * @@roseuid 4766071F0100
     */
    public WEB3EquityPTSChangeOrderServiceInterceptor()
    {

    }

    /**
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^���u�����������������������N�G�X�g�v�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@���������b�N����B <BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B <BR>
     * �@@�@@�@@��������OpLoginSecurityService���ҏW�B <BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�i*�j�̓��e�ƁAOpLoginSecurityService�̓��e��� <BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * �@@�i*�j�u�����������������m�F���N�G�X�g�v�܂��́A <BR>
     * �@@�@@�@@�u�����������������������N�G�X�g�v <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = ���N�G�X�g�f�[�^���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h�����h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�����h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�<BR>
     * �@@�@@������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �@@�@@�ݒ�L�[�FPTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|PTS������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_method - (���\�b�h)<BR>
     * ���\�b�h<BR>
     * @@param l_serviceParams - (�T�[�r�X�̈���)<BR>
     * �T�[�r�X�̃��\�b�h�ɓn���������B <BR>
     *  (PTS)�����������������T�[�r�X�̏ꍇ�A�����������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return Object
     * @@roseuid 474A776E0054
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

        //�P�j���N�G�X�g�f�[�^�̌^���u�����������������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        long l_lngAccountId = l_opLoginSec.getAccountId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        MainAccount l_acc = null;
        try
        {
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
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
        String l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_acc.getBranch().getBranchCode();

        try
        {
            if (l_serviceParams[0] instanceof WEB3EquityChangeCompleteRequest)
            {
                //�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
                l_accMgr.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_acc.getAccountCode());
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

        Object l_request = l_serviceParams[0];
        long l_lngOrderId = 0L;

        //�Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        // �T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        if (l_request instanceof WEB3EquityChangeCompleteRequest)
        {
            // �����������������������N�G�X�g
            WEB3EquityChangeCompleteRequest l_completeRequest =
                (WEB3EquityChangeCompleteRequest)l_request;
            l_lngOrderId = Long.parseLong(l_completeRequest.id);
        }
        else if (l_request instanceof WEB3EquityChangeConfirmRequest)
        {
            // �����������������m�F���N�G�X�g
            WEB3EquityChangeConfirmRequest l_confirmRequest =
                (WEB3EquityChangeConfirmRequest)l_request;
            l_lngOrderId = Long.parseLong(l_confirmRequest.id);
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
        //���N�G�X�g�f�[�^�i*�j�̓��e�ƁAOpLoginSecurityService�̓��e���
        //�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        String l_strMarketCode = null;
        EqTypeOrderUnit l_orderUnit = null;
        try
        {
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
            l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            long l_lngMarketId = l_orderUnitRow.getMarketId();
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(l_lngMarketId);
            l_strMarketCode = l_market.getMarketCode();
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
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_strInstitutionCode);
        // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_strBranchCode);
        // ����J�����_�R���e�L�X�g.�s��R�[�h = ���N�G�X�g�f�[�^���ҏW
        l_context.setMarketCode(l_strMarketCode);
        // ����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        // ����J�����_�R���e�L�X�g.������t���i = �h�����h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�����h
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);

        //ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����
        //�ݒ�L�[�FPTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        try
        {
            //�R�j�@@��t�����A���t���[�����Z�b�g����B
            //PTS������ԊǗ�.setTimestamp()���R�[������
            WEB3EquityPTSTradingTimeManagement.setTimestamp();
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
     * �������������T�[�r�X�I�����ɃR�[�������B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * PTS������ԊǗ�.TIMESTAMP_TAG <BR>
     * PTS������ԊǗ�.OFFSET_TAG <BR>
     * PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 474A78BB02F5
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
     * PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 474A793400B5
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
