head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ�����T�[�r�X�C���^�Z�v�^  
                   (WEB3RuitoCancelServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ���u�� (���u) �V�K�쐬
                   2004/12/06 ��O�� (���u) �c�Ή�
*/

package webbroker3.xbruito;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmRequest;

/**
 * �ݐϓ�������T�[�r�X�C���^�Z�v�^<BR>
 */
public class WEB3RuitoCancelServiceInterceptor implements Interceptor
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoCancelServiceInterceptor.class);

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^���u�ݓ�����������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * <BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
     * �@@�@@��������OpLoginSecurityService���ҏW�B<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�g���ݓ������}�l�[�W��.getOrder()���R�[�����āA<BR>
     *           �ݓ������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mgetOrder�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@����ID�F ���N�G�X�g�f�[�^�I�u�W�F�N�g.id<BR>
     * <BR>
     * �@@�|�擾�����ݓ������I�u�W�F�N�g.getOrderUnits()���R�[�����ėݓ������P��<BR>
     *            �I�u�W�F�N�g�̔z����擾����B<BR>
     * <BR>
     * �@@�|�擾�����ݓ������P�ʃI�u�W�F�N�g�̔z��[0] <BR>
     *            .getDataSourceObject()���R�[�����A<BR>
     *            �ݓ������P��Params���擾����B<BR>
     * <BR>
     * �@@�|������t�g�����U�N�V�������擾����B<BR>
     * �@@�@@(*)�ݓ������P��Params.getOrderType == OrderTypeEnum.RUITO_BUY�̏ꍇ�A<BR>
     *         ������t�g�����U�N�V�����̒l�́h01�F���t�i�V�K�����j�h<BR>
     * �@@�@@(*)�ݓ������P��Params.getOrderType == OrderTypeEnum.RUITO_SELL�̏ꍇ�A<BR>
     *         ������t�g�����U�N�V�����̒l�́h02�F���t�i�V�K�����j�h<BR>
     * <BR>
     * �@@�|��t���ԋ敪���擾����B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��RuitoTypeEnum.�������t�@@���h��<BR>
     *        �ꍇ�A��t���ԋ敪�̒l��WEB3TradingTimeTypeDef.����F�B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��RuitoTypeEnum.MMF��<BR>
     *        �ݓ������P��Params.getOrderType == OrderTypeEnum.RUITO_BUY�̏ꍇ�A�̏ꍇ�A<BR>
     * �@@�@@�@@�@@ ��t���ԋ敪�̒l��WEB3TradingTimeTypeDef.MMF�i�ݒ�j�B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��RuitoTypeEnum.MMF��<BR>
     *        �ݓ������P��Params.getOrderType == OrderTypeEnum.RUITO_SELL�̏ꍇ�̏ꍇ�A<BR>
     * �@@�@@�@@�@@ ��t���ԋ敪�̒l��WEB3TradingTimeTypeDef.MMF�i�ݒ���j�B<BR>
     * <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     *          ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =  <BR>
     *                 OpLoginSecurityService���ҏW <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �擾������t���ԋ敪<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h06�F����h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ������<BR>
     *           �R���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@ �msetAttribute�ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݒ�L�[�F ������ԃR���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * �@@�@@�@@�ݒ�l�F ������ԃR���e�L�X�g<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g <BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h���� <BR>
     * @@return Object
     * @@roseuid 40581D3B0041
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
		final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParam)";
		log.entering(STR_METHOD_NAME);
		
        if (l_serviceParam == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("�p�����[�^Size�͂O�ł��Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^Size�͂O�ł��Ȃ�");
        }

        //�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        Object l_request = l_serviceParam[0];
        String l_strId = null;
        
        if (l_request instanceof WEB3RuitoCancelConfirmRequest)
        {
			l_strId = ((WEB3RuitoCancelConfirmRequest)l_request).id;
        }
		else if (l_request instanceof WEB3RuitoCancelCompleteRequest)
		{
			l_strId = ((WEB3RuitoCancelCompleteRequest)l_request).id;
		} 
        else
        {
            log.debug(
                "__request_error__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME));            
			log.exiting(STR_METHOD_NAME);
			return null;
        }
        
        log.debug("l_strId = " + l_strId);
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {  
            long l_lngAccountId; // �����R�[�h
            String l_strInstitutionCode = null; // �،���ЃR�[�h
            String l_strBranchCode = null; // ���X�R�[�h

            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                    OpLoginSecurityService.class);
            l_lngAccountId = l_opLoginSec.getAccountId();
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount;
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            log.debug("�،���ЃR�[�h = " + l_strInstitutionCode);
            log.debug("���X�R�[�h = " + l_strBranchCode);
            
            //�P�j���N�G�X�g�f�[�^�̌^���u�ݓ�����������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B
            if(l_request instanceof WEB3RuitoCancelCompleteRequest)
            {
                String l_strAccountCode = l_mainAccount.getAccountCode();
            
                //���������b�N����B 
                WEB3GentradeAccountManager l_gentradeAccMgr = 
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();       
                l_gentradeAccMgr.lockAccount(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
            }
            
            //�Q�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            // �ݓ������P�ʃI�u�W�F�N�g���擾����B
            WEB3RuitoOrderManager l_ruitoOrderManager =
                 (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                     ProductTypeEnum.RUITO).getOrderManager();
            
            OrderUnit[] l_orderUnits = 
                l_ruitoOrderManager.getOrderUnits(Long.parseLong(l_strId));

			//�ݓ������P��Params���擾����B 
			RuitoOrderUnitParams l_ruitoOrderUnitParams = 
				(RuitoOrderUnitParams) l_orderUnits[0].getDataSourceObject();

			//������t�g�����U�N�V�������擾����
			OrderTypeEnum l_orderTypeEnum = 
			    l_ruitoOrderUnitParams.getOrderType();
			String l_orderAcceptTransaction = null; //������t�g�����U�N�V����
			WEB3GentradeTradingClendarContext l_context =
				new WEB3GentradeTradingClendarContext();
			
			log.debug("l_orderTypeEnum = " + l_orderTypeEnum);				

			//��t���ԋ敪���擾����
			String l_strTradingTimeType = null; //��t���ԋ敪
			RuitoTypeEnum l_ruitoTypeEnum = l_ruitoOrderUnitParams.getRuitoType();
			
			log.debug("�ݓ������P��Params.get�ݓ��^�C�v() = " + l_ruitoTypeEnum.intValue());		
			
            //(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��RuitoTypeEnum.�������t�@@���h�̏ꍇ�A 
			if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum))
			{
				l_strTradingTimeType = WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
			}
            //(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��RuitoTypeEnum.MMF�ŁA 
			//�@@�ݓ������P��Params.getOrderType == OrderTypeEnum.RUITO_BUY�̏ꍇ�A
			else if ((RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum)) && 
			    (OrderTypeEnum.RUITO_BUY.equals(l_ruitoOrderUnitParams.getOrderType())))
			{
				l_strTradingTimeType = WEB3TradingTimeTypeDef.MMF_SET;
			}
            //(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��RuitoTypeEnum.MMF�ŁA 
			//�ݓ������P��Params.getOrderType == OrderTypeEnum.RUITO_SELL�̏ꍇ�A
			else if ((RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum)) && 
			    (OrderTypeEnum.RUITO_SELL.equals(l_ruitoOrderUnitParams.getOrderType())))
			{
				l_strTradingTimeType = WEB3TradingTimeTypeDef.MMF_SET_CANCEL;
			}
			
			log.debug("l_strTradingTimeType = " + l_strTradingTimeType);		

            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW 
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �擾������t���ԋ敪
            l_context.setTradingTimeType(l_strTradingTimeType);
            //����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h 
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h06�F����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);

            //�|ThreadLocalSystemAttributesRegistry.setAttribute( )
            //�ɂĎ�����ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            //�R�j�@@��t�����A���t���[�����Z�b�g����B 
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�
           
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__");
            log.error(
                "__an unexpected error__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex));
			log.exiting(STR_METHOD_NAME);
			return null;
        }

        catch (WEB3BaseException l_ex)
        {
            log.debug(
                "__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
            log.error(
                    "__an unexpected error__",
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            //DB�ւ̃A�N�Z�X�Ɏ��s���܂���
            this.getClass().getName() + "onCall()", l_ex));
			log.exiting(STR_METHOD_NAME);
			return null;
        }
        
		log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCall���^�[���l <BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l <BR>
     * @@roseuid 40581D3B0060
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
		final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
		log.entering(STR_METHOD_NAME);
		
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
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_obj - onCall���^�[���l <BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g <BR>
     * @@roseuid 40581D3B006F
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
		final String STR_METHOD_NAME = 
			"onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

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
