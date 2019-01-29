head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�̓T�[�r�X�C���^�Z�v�^(WEB3TPTradingPowerAfterRepayServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/13 nakazato(ACT) �V�K�쐬
Revesion History : 2008/10/22 �И��� (���u) �d�l�ύX���f��311
Revesion History : 2008/10/31 ������ (���u) �d�l�ύX���f��353
*/
package webbroker3.tradingpower;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]�̓T�[�r�X�C���^�Z�v�^)<BR>
 * �]�̓T�[�r�X�C���^�Z�v�^�B<BR>
 */
public class WEB3TPTradingPowerServiceInterceptor implements Interceptor
{

    /**
     * ���O
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerServiceInterceptor.class);

    /**
     * �R���X�g���N�^
     */
    public WEB3TPTradingPowerServiceInterceptor()
    {

    }

    /**
     * (onCall)<BR>
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * �@@�E����]�̓T�[�r�X<BR>
     * �@@�E�ԍό�]�̓T�[�r�X<BR>
     * �@@�E�������ώ���]�̓T�[�r�X<BR>
     * �@@�E���V���v���N�X�A�g�T�[�r�X<BR>
     * �@@�E���������Ǘ��T�[�r�X<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�X���b�h���[�J�����A������ԃR���e�L�X�g�I�u�W�F�N�g���擾<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * <BR>
     * �@@(*)�擾����������ԃR���e�L�X�g==null�̏ꍇ <BR>
     * �@@�@@�T�[�r�X�̈���[0]�̃f�[�^�^�𔻒f���� <BR>
     * �@@�@@�@@(*)�T�[�r�X�̈���[0]�̃f�[�^�^���⏕�����̏ꍇ�A�⏕�����I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�@@�@@�@@�|�T�[�r�X�̈���[0]��⏕�����I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�@@�@@�@@�|�L���X�g�����⏕�����I�u�W�F�N�g���A�ڋq�I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�@@�@@���⏕�����I�u�W�F�N�g.getMainAccount()���R�[�� <BR>
     * �@@�@@�@@(*)�T�[�r�X�̈���[0]�̃f�[�^�^���ڋq�̏ꍇ�A�ڋq�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * <BR>
     * �@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �ڋq�I�u�W�F�N�g.getInstitution().getInstitutionCode()<BR>
     * �@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = �ڋq�I�u�W�F�N�g.getBranch().getBranchCode()<BR>
     * �@@�@@����J�����_�R���e�L�X�g.�s��R�[�h =�擾����������ԃR���e�L�X�g�I�u�W�F�N�g.�s��R�[�h <BR>
     * �@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = 01�F�����E�M�p <BR>
     * �@@�@@����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT<BR>
     * <BR>
     * �@@(*)�擾����������ԃR���e�L�X�g��null�̏ꍇ<BR>
     * �@@�@@����J�����_�R���e�L�X�g = �P�j�Ŏ擾����������ԃR���e�L�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * �|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �i���j�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�X���b�h���[�J���Ɏ�����ԃR���e�L�X�g�I�u�W�F�N�g���ݒ肳��Ă��Ȃ��ꍇ�A�^�C���X�^���v��ݒ肷��B<BR>
     * �@@�i�擾����������ԃR���e�L�X�g�I�u�W�F�N�g==null)<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param method
     * @@param arguments
     */
    public java.lang.Object onCall(Method method, Object[] arguments)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        /*
         * �X���b�h���[�J�����A�I���W�i��������ԃR���e�L�X�g�I�u�W�F�N�g���擾
         */
        WEB3GentradeTradingClendarContext l_originalContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        /*
         * ������ԃR���e�L�X�g�ɓ��e���Z�b�g����
         */
        // ������ԃR���e�L�X�g�𐶐�
        WEB3GentradeTradingClendarContext l_context = null;

        //�I���W�i��������ԃR���e�L�X�g==null
        if(l_originalContext == null)
        {
            l_context = new WEB3GentradeTradingClendarContext();

            MainAccount l_mainAccount = null;

            //(*)�T�[�r�X�̈���[0]�̃f�[�^�^���⏕�����̏ꍇ�A�⏕�����I�u�W�F�N�g�ɃL���X�g����B
            if (arguments[0] instanceof WEB3GentradeSubAccount)
            {
                WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)arguments[0];
                //�ڋq�I�u�W�F�N�g���擾
                l_mainAccount = l_subAccount.getMainAccount();
            }
            else if (arguments[0] instanceof MainAccount)
            {
                //(*)�T�[�r�X�̈���[0]�̃f�[�^�^���ڋq�̏ꍇ�A�ڋq�I�u�W�F�N�g�ɃL���X�g����B
                l_mainAccount = (MainAccount)arguments[0];
            }

            //�،���ЃR�[�h
            l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
            //���X�R�[�h
            l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
            //�s��R�[�h = 1:����
            l_context.setMarketCode(WEB3MarketCodeDef.TOKYO);
            //��t���ԋ敪 = 01�F�����E�M�p
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            //���i�R�[�h = 0�FDEFAULT
            l_context.setProductCode("0");
        }
        //�ȊO�̏ꍇ
        else
        {
            l_context = l_originalContext;
        }


        //������ԃR���e�L�X�g���Z�b�g
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        log.debug("--------------------------------------------------");
        log.debug("TradingCalendarContext.institutionCode=" + l_context.getInstitutionCode());
        log.debug("TradingCalendarContext.branchCode=" + l_context.getBranchCode());
        log.debug("TradingCalendarContext.MarketCode=" + l_context.getMarketCode());
        log.debug("TradingCalendarContext.tradingTimeType=" + l_context.getTradingTimeType());
        log.debug("TradingCalendarContext.productCode=" + l_context.getProductCode());
        log.debug("--------------------------------------------------");

        /*
         * �X���b�h���[�J���Ɏ�����ԃR���e�L�X�g�I�u�W�F�N�g���ݒ肳��Ă��Ȃ��ꍇ�A�^�C���X�^���v��ݒ肷��B
         */

        //�I���W�i��������ԃR���e�L�X�g==null
        if(l_originalContext == null)
        {
            //xTrade�����p����Ɩ��������Z�b�g����
            try 
            {
                WEB3GentradeTradingTimeManagement.setTimestamp();
            } 
            catch (WEB3SystemLayerException e) 
            {
                log.error(e.getMessage(), e);
                throw new WEB3BaseRuntimeException(
                    e.getErrorInfo(),
                    e.getErrorMethod(),
                    e.getErrorMessage(),
                    e.getException());
            }
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * 
     * @@param context
     * @@param returnValue
     */
    public void onReturn(Object context, Object returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * 
     * @@param context
     * @@param thrownObject
     */
    public void onThrowable(Object context, Throwable thrownObject)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
    }

}
@
