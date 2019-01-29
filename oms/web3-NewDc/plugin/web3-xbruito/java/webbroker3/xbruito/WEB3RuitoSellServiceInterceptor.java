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
filename	WEB3RuitoSellServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������T�[�r�X�C���^�Z�v�^�N���X(WEB3RuitoSellServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 ���u�� (���u) �V�K�쐬
*/

package webbroker3.xbruito;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3RuitoCommonRequest;
import webbroker3.xbruito.message.WEB3RuitoSellCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoSellConfirmRequest;

/**
 * �ݐϓ������T�[�r�X�C���^�Z�v�^�N���X<BR>
 */
public class WEB3RuitoSellServiceInterceptor implements Interceptor
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellServiceInterceptor.class);
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]��<BR>
     *       ���N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|�،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�A�J�E���g�}�l�[�W��.getInstitution()���R�[�����āA<BR>
     *     �،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@[getInstitution�ɓn���p�����^]<BR>
     * �@@�@@�،���ЃR�[�h�F OpLoginSecurityService���ҏW<BR>
     *   �@@�|�ݓ������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�g���ݓ������}�l�[�W��.get�ݓ�����()���R�[�����āA<BR>
     *      �g���ݓ������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@[get�ݓ������ɓn���p�����^]<BR>
     * �@@�@@�،���ЁF�@@�擾�����،���ЃI�u�W�F�N�g<BR>
     * �@@�@@�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h<BR>
     * <BR>
     *     0�@@�|��t���ԋ敪���擾����B<BR>
     * �@@(*) �g���ݓ�����.get�ݓ��^�C�v()�̒l<BR>
     *       ��RuitoTypeEnum.<BR>
     *    �������t�@@���h�̏ꍇ�A<BR>
     * �@@��t���ԋ敪��WEB3TradingTimeTypeDef.����F�B<BR>
     * �@@(*) �g���ݓ�����.get�ݓ��^�C�v()�̒l��<BR>
     *    RuitoTypeEnum.MMF�̏ꍇ�A��t���ԋ敪��<BR>
     *    WEB3TradingTimeTypeDef.MMF�i�ݒ�j�B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁA<BR>
     *    OpLoginSecurityService�̓��e��������ԃR���e�L�X�g<BR>
     *    �̃v���p�e�B���Z�b�g����B<BR>
     *  �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h <BR>
     *               =  OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h  <BR>
     *               = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪  <BR>
     *               = �擾������t���ԋ敪<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� <BR>
     *               =  �h02�F���t�i�V�K�����j�h<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *    �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�msetAttribute�ɓn���p�����^�n<BR>
     * �@@�ݒ�L�[�F <BR>
     *   ������ԃR���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * �@@�ݒ�l�F ������ԃR���e�L�X�g<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     *   �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �R�j�@@���N�G�X�g�f�[�^�̌^���u�ݓ���񊮗����N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B <BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
     * �@@�@@��������OpLoginSecurityService���ҏW�B<BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 4058165D0225
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
		final String STR_METHOD_NAME =
			"onCall(Method l_method, Object[] l_serviceParam)";
		log.entering(STR_METHOD_NAME);
		
        if (l_serviceParam == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");            
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("�p�����[�^Size�͂O�ł��Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^Size�͂O�ł��Ȃ�");
        }

		Object l_request = l_serviceParam[0];
		WEB3RuitoCommonRequest l_inputRequest = null; //�ݐϓ������ʃ��N�G�X�g

        if (l_request instanceof WEB3RuitoSellConfirmRequest)
        {
        	log.debug("l_request ==  WEB3RuitoSellConfirmRequest");
            l_inputRequest = (WEB3RuitoSellConfirmRequest) l_request;
        }
		else if (l_request instanceof WEB3RuitoSellCompleteRequest)
		{
			log.debug("l_request ==  WEB3RuitoSellCompleteRequest");
			l_inputRequest = (WEB3RuitoSellCompleteRequest) l_request;
		}
        else
        {
            log.debug(
                "__request_error__",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "onCall"));
			log.exiting(STR_METHOD_NAME);
            return null;
        }
	
		String l_strRuitoProductCode = null; //.�����R�[�h

        //�N�G�X�g�f�[�^.�����R�[�h
        l_strRuitoProductCode = l_inputRequest.ruitoProductCode;
		log.debug("l_strRuitoProductCode = " + l_strRuitoProductCode);        

        //�،���ЃI�u�W�F�N�g���擾����
        long l_lngAccountId = 0L; // �����R�[�h

        String l_strInstitutionCode = null; // �،���ЃR�[�h
        Institution l_institution = null;   //�،����
        String l_strBranchCode = null;      // ���X�R�[�h

        //�Z�L�����e�B�T�[�r�X���擾
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        //AccountId���擾
        l_lngAccountId = l_opLoginSec.getAccountId();
		log.debug("l_lngAccountId = " + l_lngAccountId);   

        //FinApp�T�[�r�X���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        RuitoProduct l_ruitoProduct = null;
        MainAccount l_mainAccount = null;

        try
        {            
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
			l_institution = l_mainAccount.getInstitution();
			l_strInstitutionCode = l_institution.getInstitutionCode();

            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
			log.debug("l_strBranchCode = " + l_strBranchCode);   

            //�ݓ������I�u�W�F�N�g���擾����
            WEB3RuitoProductManager l_web3RuitoProductManager =
                (WEB3RuitoProductManager) l_finApp
                    .getTradingModule(ProductTypeEnum.RUITO)
                    .getProductManager();

            l_ruitoProduct =
                l_web3RuitoProductManager.getRuitoProduct(
                    l_institution,
                    l_strRuitoProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        RuitoTypeEnum l_ruitoTypeEnum = null; //�ݓ��^�C�v
        String l_midmmfOrdHostOrdRcvDiv = null; //��t���ԋ敪

        l_ruitoTypeEnum = l_ruitoProduct.getRuitoType();
		log.debug("l_ruitoTypeEnum = " + l_ruitoTypeEnum);   
        if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum))
        {
            l_midmmfOrdHostOrdRcvDiv = WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
        }
        else if (RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum))
        {
            l_midmmfOrdHostOrdRcvDiv = WEB3TradingTimeTypeDef.MMF_SET;
        }
		log.debug("l_midmmfOrdHostOrdRcvDiv = " + l_midmmfOrdHostOrdRcvDiv);   
		
        //���N�G�X�g�f�[�^�̓��e�ƁA
        //OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_strInstitutionCode);
        // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_strBranchCode);
        //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        //����J�����_�R���e�L�X�g.��t���ԋ敪 = �擾������t���ԋ敪
        l_context.setTradingTimeType(l_midmmfOrdHostOrdRcvDiv);
        //����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h02�F���t�i�V�K�����j�h 
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);

        //ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //��t�����A���t���[�����Z�b�g����
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        try
        {
            if(l_request instanceof WEB3RuitoSellCompleteRequest)
            {
                String l_strAccountCode = l_mainAccount.getAccountCode();
            
                //�R�j�@@���������b�N����B 
                WEB3GentradeAccountManager l_gentradeAccMgr = 
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();       
                l_gentradeAccMgr.lockAccount(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
            }
        }       
        catch (WEB3BaseException l_ex)
        {
            log.debug("__l_gentradeAccMgr.lockAccount Error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
		log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry��<BR>
     *        �ȉ��̓��e���N���A����B<BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 4058165D0228
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
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry��<BR>
     *    �ȉ��̓��e���N���A����B<BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 4058165D022B
     */
    public void onThrowable(Object l_context, Throwable l_throwable)
    {
		final String STR_METHOD_NAME = 
			"onThrowable(Object l_context, Object l_returnValue)";
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
