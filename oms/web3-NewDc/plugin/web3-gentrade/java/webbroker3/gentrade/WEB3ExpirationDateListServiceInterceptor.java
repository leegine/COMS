head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExpirationDateListServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����L�������擾�T�[�r�X�C���^�Z�v�^(WEB3ExpirationDateListServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 ���n(���u) �V�K�쐬���f��319
*/

package webbroker3.gentrade;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.message.WEB3ExpirationDateListRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����L�������擾�T�[�r�X�C���^�Z�v�^)<BR>
 * �����L�������擾�T�[�r�X�C���^�Z�v�^�N���X<BR>
 *
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ExpirationDateListServiceInterceptor implements Interceptor
{
    /**
     * WEB3LogUtility log<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ExpirationDateListServiceInterceptor.class);

    /**
     * @@roseuid 47B3E127007D
     */
    public WEB3ExpirationDateListServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@-�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@-���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e<BR>
     * �@@�@@�@@��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h =<BR>
     * �@@�@@�i���N�G�X�g�f�[�^.���i�敪 = �h���������h�܂��́A �h�M�p����h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�f�[�^.�s��R�[�h<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.���i�敪 = �h�敨�h�܂��́A�h�I�v�V�����h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�hDEFAULT�h �j<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = <BR>
     * �@@�@@�i���N�G�X�g�f�[�^.���i�敪 = �h���������h�܂��́A�h�M�p����h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�hDEFAULT�h <BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.���i�敪 = �h�敨�h�܂��́A�h�I�v�V�����h�̏ꍇ�A<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.�w����� = null�̏ꍇ�A�hDEFAULT�h  �ȊO�A<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�f�[�^.�w����ʁj<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
     * �@@�@@�i���N�G�X�g�f�[�^.���i�敪 = �h���������h�܂��́A�h�M�p����h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�h�����E�M�p�h<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.���i�敪 = �h�敨�h�܂��́A�h�I�v�V�����h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�h�����w���敨OP�h�j<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = <BR>
     * �@@�@@�i���N�G�X�g�f�[�^.���i�敪 = �h���������h�̏ꍇ�A�h�����h<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.���i�敪 = �h�M�p����h�̏ꍇ�A�h�M�p����h <BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.���i�敪 = �h�敨�h�̏ꍇ�A�h�敨�h <BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.���i�敪 = �h�I�v�V�����h�̏ꍇ�A�h�I�v�V�����h�j<BR>
     * <BR>
     * �@@-ThreadLocalSystemAttributesRegistry.setAttribute( )��<BR>
     * �@@�@@�@@�Ď�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@-������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 47A905AF01A9
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        //����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        //-�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        //-���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
        //������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����
        Object l_request = l_serviceParams[0];
        WEB3ExpirationDateListRequest l_expirationDateListRequest = null;
        if (l_request instanceof WEB3ExpirationDateListRequest)
        {
            l_expirationDateListRequest =
                (WEB3ExpirationDateListRequest)l_request;
        }

        long l_lngAccountId; // �����R�[�h
        String l_strInstitutionCode = null; // �،���ЃR�[�h
        String l_strBranchCode = null; // ���X�R�[�h
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        //�Z�L�����e�B�T�[�r�X���擾
        try
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            //AccountId���擾
            l_lngAccountId = l_opLoginSec.getAccountId();
            //FinApp�T�[�r�X���擾
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_acc;
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            l_strBranchCode = l_acc.getBranch().getBranchCode();
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_strInstitutionCode);

        //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_strBranchCode);

        //����J�����_�R���e�L�X�g.�s��R�[�h =
        //���N�G�X�g�f�[�^.���i�敪 = �h���������h�܂��́A �h�M�p����h�̏ꍇ�A���N�G�X�g�f�[�^.�s��R�[�h
        //���N�G�X�g�f�[�^.���i�敪 = �h�敨�h�܂��́A�h�I�v�V�����h�̏ꍇ�A�hDEFAULT�h �j
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setMarketCode(l_expirationDateListRequest.marketCode);
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        }

        //����J�����_�R���e�L�X�g.�����R�[�h =
        //�i���N�G�X�g�f�[�^.���i�敪 = �h���������h�܂��́A�h�M�p����h�̏ꍇ�A�hDEFAULT�h
        //���N�G�X�g�f�[�^.���i�敪 = �h�敨�h�܂��́A�h�I�v�V�����h�̏ꍇ�A
        //���N�G�X�g�f�[�^.�w����� = null�̏ꍇ�A�hDEFAULT�h  �ȊO�A���N�G�X�g�f�[�^.�w����ʁj
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            if (l_expirationDateListRequest.targetProductCode == null)
            {
                l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            }
            else
            {
                l_context.setProductCode(l_expirationDateListRequest.targetProductCode);
            }
        }

        //����J�����_�R���e�L�X�g.��t���ԋ敪 =
        //�i���N�G�X�g�f�[�^.���i�敪 = �h���������h�܂��́A�h�M�p����h�̏ꍇ�A�h�����E�M�p�h
        //���N�G�X�g�f�[�^.���i�敪 = �h�敨�h�܂��́A�h�I�v�V�����h�̏ꍇ�A�h�����w���敨OP�h
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
        }

        //����J�����_�R���e�L�X�g.������t���i =
        //�i���N�G�X�g�f�[�^.���i�敪 = �h���������h�̏ꍇ�A�h�����h
        //���N�G�X�g�f�[�^.���i�敪 = �h�M�p����h�̏ꍇ�A�h�M�p����h
        //���N�G�X�g�f�[�^.���i�敪 = �h�敨�h�̏ꍇ�A�h�敨�h
        //���N�G�X�g�f�[�^.���i�敪 = �h�I�v�V�����h�̏ꍇ�A�h�I�v�V�����h�j
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        else if (WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
        }
        else if (WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
        }

        //ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
        //�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * �����L�������擾�T�[�r�X�I�����ɃR�[�������B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 47A905BE01F0
     */
    public void onReturn(Object l_context, Object l_returnValue)
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
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 47A905D40098
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
