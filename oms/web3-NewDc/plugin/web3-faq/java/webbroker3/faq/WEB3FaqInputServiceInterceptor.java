head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �⍇���Ǘ����̓T�[�r�X�C���^�Z�v�^(WEB3FaqInputServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ����� (���u) �V�K�쐬
*/

package webbroker3.faq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.faq.message.WEB3FaqCompleteRequest;
import webbroker3.faq.message.WEB3FaqConfirmRequest;
import webbroker3.faq.message.WEB3FaqInputRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�⍇���Ǘ����̓T�[�r�X�C���^�Z�v�^)<BR>
 * �⍇���Ǘ����̓T�[�r�X�C���^�Z�v�^<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3FaqInputServiceInterceptor implements Interceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqInputServiceInterceptor.class);

    /**
     * @@roseuid 41C25D3000EA
     */
    public WEB3FaqInputServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
     * <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = ���،���ЃR�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �����X�R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�F���̑��h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * ������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �@@���@@�،���ЃR�[�h�C���X�R�[�h<BR>
     * �@@�@@�e���N�G�X�g�f�[�^���ҏW����B<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     * @@roseuid 41ABF1CF02C4
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

        //����J�����_�R���e�L�X�g�𐶐�����
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        if (l_serviceParam == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B"
            );
        }

        try
        {
            //�،���ЃR�[�h�A���X�R�[�h�͊e���N�G�X�g�f�[�^���ҏW����B
            String l_strInstitutionCode = null;
            String l_strBranchCode = null;
            
            if (l_serviceParam[0] instanceof WEB3FaqInputRequest)
            {
                WEB3FaqInputRequest l_request = (WEB3FaqInputRequest)l_serviceParam[0];
                l_strInstitutionCode = l_request.institutionCode;
                l_strBranchCode = l_request.branchCode;
            }
            else if (l_serviceParam[0] instanceof WEB3FaqConfirmRequest)
            {
                WEB3FaqConfirmRequest l_request = (WEB3FaqConfirmRequest)l_serviceParam[0];
                l_strInstitutionCode = l_request.institutionCode;
                l_strBranchCode = l_request.branchCode;
            }
            else if (l_serviceParam[0] instanceof WEB3FaqCompleteRequest)
            {
                WEB3FaqCompleteRequest l_request = (WEB3FaqCompleteRequest)l_serviceParam[0];
                l_strInstitutionCode = l_request.institutionCode;
                l_strBranchCode = l_request.branchCode;
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�p�����[�^�^�C�v�s���B"
                );
            }
            

            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = ���،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);

            //����J�����_�R���e�L�X�g.���X�R�[�h = �����X�R�[�h
            l_context.setBranchCode(l_strBranchCode);

            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h00�F���̑��h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);

            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

            //������ԃR���e�L�X�g���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context
            );

            //��t�����A���t���[�����Z�b�g����
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
                );
        }

        log.exiting(STR_METHOD_NAME);

        return l_context;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@������ԊǗ�.OFFSET_TAG <BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG <BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 41ABF1D00073
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

        //����J�����_�R���e�L�X�g�A����ю����̃N���A�����B

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
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@������ԊǗ�.OFFSET_TAG <BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG <BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 41ABF1D0019B
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //����J�����_�R���e�L�X�g�A����ю����̃N���A�����B

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
