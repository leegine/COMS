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
filename	WEB3RuitoOrderAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ���������tUnitService�C���^�Z�v�^�N���X (WEB3RuitoOrderAcceptUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito;
import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
/**
 * �ݐϓ���������tUnitService�C���^�Z�v�^�N���X<BR>
 */
public class WEB3RuitoOrderAcceptUnitServiceInterceptor implements Interceptor
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3RuitoOrderAcceptUnitServiceInterceptor.class);
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|����.�T�[�r�X�̈���[0]��ݓ������P��<BR>
     *       �I�u�W�F�N�g�ɃL���X�g����B<BR>
     * <BR>
     * �@@�|���X�I�u�W�F�N�g�̎擾<BR>
     * �@@�@@�A�J�E���g�}�l�[�W��.getBranch()���R�[������<BR>
     *      ���X�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@[getBranch�ɓn���p�����^]<BR>
     * �@@�@@�@@���XID�F �擾�����ݓ������P��.getBranchId()�̖߂�l<BR>
     * <BR>
     * �@@�|�،���ЃI�u�W�F�N�g�̎擾<BR>
     * �@@�@@�擾�������X�I�u�W�F�N�g.getInstitution()��<BR>
     *     �R�[�����ď،����<BR>
     *     �I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�|�擾�����ݓ������P�ʃI�u�W�F�N�g.getDataSourceObject()��<BR>
     *      �R�[�����A�ݓ������P��Params���擾����B<BR>
     * �@@<BR>
     * �@@�|������t�g�����U�N�V�������擾����B<BR>
     * �@@�@@(*)�ݓ������P��Params.getOrderType == <BR>
     *         OrderTypeEnum.RUITO_BUY�̏ꍇ�A<BR>
     *         ������t�g�����U�N�V�����̒l�́h01�F���t�i�V�K�����j�h<BR>
     * �@@�@@(*)�ݓ������P��Params.getOrderType ==<BR>
     *         OrderTypeEnum.RUITO_SELL�̏ꍇ�A<BR>
     *         ������t�g�����U�N�V�����̒l�́h02�F���t�i�V�K�����j�h<BR>
     * <BR>
     * �@@�|��t���ԋ敪���擾����B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��<BR>
     *          RuitoTypeEnum.�������t�@@���h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@��t���ԋ敪�̒l��WEB3TradingTimeTypeDef.����F�B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��<BR>
     *          RuitoTypeEnum.MMF�Œ�����t�g�����U�N�V�����̒l��<BR>
     *          �h01�F���t�i�V�K�����j�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@��t���ԋ敪�̒l��WEB3TradingTimeTypeDef.MMF�i�ݒ�j�B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��<BR>
     *          RuitoTypeEnum.MMF�Œ�����t�g�����U�N�V�����̒l��<BR>
     *          �h02�F���t�i�V�K�����j�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@��t���ԋ敪�̒l��<BR>
     *          WEB3TradingTimeTypeDef.MMF�i�ݒ���j�B<BR>
     * <BR>
     * �@@�|������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *           �擾�����،���ЃI�u�W�F�N�g.getInstitutionCode()�̖߂�l<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     *           �擾�������X�I�u�W�F�N�g.getBranchCode()�̖߂�l<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �擾������t���ԋ敪<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     *                 �擾����������t�g�����U�N�V����<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     *      ������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@�msetAttribute�ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݒ�L�[�F ������ԃR���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * �@@�@@�@@�ݒ�l�F ������ԃR���e�L�X�g<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * �R�j�@@���������b�N����B <BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����( <BR>
     *          �،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
     * �������͗ݓ�������t�L���[Params���ҏW�B <BR>
     * 
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 405A49F40202
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

        //����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����
        OrderUnit l_orderUnit = null; //�ݓ������P��
        l_orderUnit = (OrderUnit) l_serviceParam[0];
        log.debug("l_orderUnit.getBranchId = " + l_orderUnit.getBranchId());
        //���X�I�u�W�F�N�g�̎擾
        Branch l_branch = null; //���X
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager(); //�A�J�E���g�}�l�[�W��
        try
        {
            l_branch = l_accMgr.getBranch(l_orderUnit.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
            //�،���ЃI�u�W�F�N�g�̎擾
            Institution l_institution = null; //�،����
            l_institution = l_branch.getInstitution();

            log.debug("�،���ЃR�[�h = " + l_institution.getInstitutionCode());
            
            //�擾�����ݓ������P�ʃI�u�W�F�N�g.getDataSourceObject()���R�[�����A
            //�ݓ������P��Params���擾����B
            RuitoOrderUnitParams l_ruitoOrderUnitParams = null; //�ݓ������P��Params
            l_ruitoOrderUnitParams =
                (RuitoOrderUnitParams) l_orderUnit.getDataSourceObject();
            log.debug("�ݓ������P��Params = " + l_ruitoOrderUnitParams);
            
            //������t�g�����U�N�V�������擾����
            OrderTypeEnum l_orderTypeEnum = null;
            String l_orderAccTransaction = ""; //������t�g�����U�N�V����
            l_orderTypeEnum = l_ruitoOrderUnitParams.getOrderType();
            log.debug("OrderTypeEnum = " + l_orderTypeEnum);
            
            if (OrderTypeEnum.RUITO_BUY.equals(l_orderTypeEnum))
            {
                l_orderAccTransaction =
                    WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
                log.debug(
                    "������t�g�����U�N�V���� = " + l_orderAccTransaction);
            }
            if (OrderTypeEnum.RUITO_SELL.equals(l_orderTypeEnum))
            {
                l_orderAccTransaction =
                    WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
                log.debug(
                    "������t�g�����U�N�V���� = " + l_orderAccTransaction);
            }
            //��t���ԋ敪���擾����
            int l_intRuitoType = 0;
            String l_strTradingTimeType = null; //��t���ԋ敪
            l_intRuitoType = l_ruitoOrderUnitParams.getRuitoType().intValue();
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            log.debug("l_intRuitoType = " + l_intRuitoType);
            //������ԃR���e�L�X�g         
            if (l_intRuitoType == RuitoTypeEnum.IntValues.CHUUKOKU_FUND)
            {
                log.debug("�ݓ������P��Params.get�ݓ��^�C�v()�̒l��" +
                        "RuitoTypeEnum.�������t�@@���h�̏ꍇ");
                
                l_strTradingTimeType =
                    WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
                log.debug("l_StrTradingTimeType  = " + l_strTradingTimeType);
            }
            if ((l_intRuitoType == RuitoTypeEnum.IntValues.MMF)
                && (WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN.equals(
                        l_orderAccTransaction)))
            {
                log.debug("�ݓ������P��Params.get�ݓ��^�C�v()�̒l��" + 
                        "RuitoTypeEnum.MMF�Œ�����t�g�����U�N�V�����̒l��" + 
                        "�h01�F���t�i�V�K�����j�h�̏ꍇ");

                l_strTradingTimeType = WEB3TradingTimeTypeDef.MMF_SET;
                log.debug("l_StrTradingTimeType = " + l_strTradingTimeType);
            }
            if ((l_intRuitoType == RuitoTypeEnum.IntValues.MMF)
                && (l_orderAccTransaction
                    == WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN))
            {
                log.debug("�ݓ������P��Params.get�ݓ��^�C�v()�̒l��" + 
                        "RuitoTypeEnum.MMF�Œ�����t�g�����U�N�V�����̒l��" +
                        "�h02�F���t�i�V�K�����j�h�̏ꍇ");
                
                l_strTradingTimeType = WEB3TradingTimeTypeDef.MMF_SET_CANCEL;
                log.debug("l_strTradingTimeType =" + l_strTradingTimeType);
            }
            //������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����
            l_context.setInstitutionCode(l_institution.getInstitutionCode());
            l_context.setBranchCode(l_branch.getBranchCode());
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_context.setTradingTimeType(l_strTradingTimeType);
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
            l_context.setOrderAcceptTransaction(l_orderAccTransaction);

            //�ɂĎ�����ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
       try
       {
            log.debug("begin WEB3GentradeTradingTimeManagement.setTimestamp()");
            //��t�����A���t���[�����Z�b�g����
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�
        }

        catch (WEB3BaseException l_ex)
        {
            log.debug("__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
        try
        {
            String l_strAccountCode = l_accMgr.getMainAccount(
                    l_ruitoOrderUnitParams.getAccountId()).getAccountCode();
            
            //�R�j�@@���������b�N����B 
            WEB3GentradeAccountManager l_gentradeAccMgr = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();       
            l_gentradeAccMgr.lockAccount(
                    l_institution.getInstitutionCode(),
                    l_branch.getBranchCode(),
                    l_strAccountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException_");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("__WEB3BaseException__");
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
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 405A49F40212
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
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
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 405A49F40221
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
    }
}
@
