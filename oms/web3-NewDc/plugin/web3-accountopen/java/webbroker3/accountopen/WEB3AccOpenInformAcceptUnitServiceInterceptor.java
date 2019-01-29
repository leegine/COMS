head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInformAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �e��A����tUnitService�C���^�Z�v�^(WEB3AccOpenInformAcceptUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/05/28 �đo�g (���u) �V�K�쐬 ���f�� No.123
*/

package webbroker3.accountopen;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�e��A����tUnitService�C���^�Z�v�^)<BR>
 * �e��A����t1���T�[�r�X�C���^�Z�v�^<BR>
 * <BR>
 * �e��A����t1���T�[�r�X��plugin����B<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AccOpenInformAcceptUnitServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AccOpenInformAcceptUnitServiceInterceptor.class);

    /**
     * (onCall)<BR>
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g���B<BR>
     * �@@�|�T�[�r�X�̈���[0]�������J�ݓ`�[�o�^��t�L���[Params�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|�����J�ݓ`�[�o�^��t�L���[Params�I�u�W�F�N�g�̓��e���A<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �����J�ݓ`�[�o�^�̓�������<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �����J�ݓ`�[�o�^�̓�������<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h22�F�����J�݁h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method
     * @@param l_objects
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_objParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_objParams == null
                || l_objParams.length == 0
                || !(l_objParams[0] instanceof HostAccOpenAcceptParams))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�����[�^�l�s���B");
            }

            //�T�[�r�X�̈���[0]�������J�ݓ`�[�o�^��t�L���[Params�I�u�W�F�N�g�ɃL���X�g����
            HostAccOpenAcceptParams l_hostAcceptParams =
                (HostAccOpenAcceptParams)l_objParams[0];

            //����J�����_�����p����R���e�L�X�g�𐶐�����
            WEB3GentradeTradingClendarContext l_tradingClendarContext =
                new WEB3GentradeTradingClendarContext();

            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �����J�ݓ`�[�o�^�̓�������
            l_tradingClendarContext.setInstitutionCode(l_hostAcceptParams.getInstitutionCode());

            //����J�����_�R���e�L�X�g.���X�R�[�h = �����J�ݓ`�[�o�^�̓�������
            l_tradingClendarContext.setBranchCode(l_hostAcceptParams.getBranchCode());

            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_tradingClendarContext.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h22�F�����J�݁h
            l_tradingClendarContext.setTradingTimeType(WEB3TradingTimeTypeDef.ACCOUNT_OPEN);

            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_tradingClendarContext.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h
            l_tradingClendarContext.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);

            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07:�Ɖ�h
            l_tradingClendarContext.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

            //�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_tradingClendarContext);

            //��t�����A���t���[�����Z�b�g����B
            //�@@�|������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();

            log.exiting(STR_METHOD_NAME);
            return l_tradingClendarContext;
        }
        catch (WEB3BaseException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_exc.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (onReturn)<BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * <BR>
     * @@param l_objContext
     * @@param l_objReturn
     */
    public void onReturn(Object l_objContext, Object l_objReturn)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (onThrowable)<BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * <BR>
     * @@param l_object
     * @@param l_throwable
     */
    public void onThrowable(Object l_object, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
