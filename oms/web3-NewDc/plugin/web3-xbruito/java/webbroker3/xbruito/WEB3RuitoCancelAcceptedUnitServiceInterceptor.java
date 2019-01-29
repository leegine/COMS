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
filename	WEB3RuitoCancelAcceptedUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������tUnitService�C���^�Z�v�^  
                   (WEB3RuitoCancelAcceptedUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 �����F (���u) �V�K�쐬
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
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
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

/**
 * �ݐϓ��������tUnitService�C���^�Z�v�^�N���X<BR>
 */
public class WEB3RuitoCancelAcceptedUnitServiceInterceptor
    implements Interceptor
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3RuitoCancelAcceptedUnitServiceInterceptor.class);

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|����.�T�[�r�X�̈���[0]��ݓ������P�ʃI�u�W�F�N�g�ɃL���X�g����B<BR>
     * <BR>
     * �@@�|���X�I�u�W�F�N�g�̎擾<BR>
     * �@@�@@�A�J�E���g�}�l�[�W��.getBranch()���R�[�����ĕ��X�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@[getBranch�ɓn���p�����^]<BR>
     * �@@�@@�@@���XID�F �擾�����ݓ������P��.getBranchId()�̖߂�l<BR>
     * <BR>
     * �@@�|�،���ЃI�u�W�F�N�g�̎擾<BR>
     *  �@@�@@�擾�������X�I�u�W�F�N�g.getInstitution()��<BR>
     *       �R�[�����ď،���ЃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�|�擾�����ݓ������P�ʃI�u�W�F�N�g.getDataSourceObject()���R�[�����A<BR>
     *            �ݓ������P��Params���擾����B<BR>
     * �@@<BR>
     * �@@�|������t�g�����U�N�V�������擾����B<BR>
     * �@@�@@(*)�ݓ������P��Params.getOrderType == OrderTypeEnum.RUITO_BUY�̏ꍇ�A<BR>
     *              ������t�g�����U�N�V�����̒l�́h01�F���t�i�V�K�����j�h<BR>
     * �@@�@@(*)�ݓ������P��Params.getOrderType == OrderTypeEnum.RUITO_SELL�̏ꍇ�A<BR>
     *              ������t�g�����U�N�V�����̒l�́h02�F���t�i�V�K�����j�h<BR>
     * <BR>
     * �@@�|��t���ԋ敪���擾����B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��RuitoTypeEnum. <BR>
     *               �������t�@@���h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@      ��t���ԋ敪�̒l��WEB3TradingTimeTypeDef.����F�B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��RuitoTypeEnum.MMF<BR>
     *               
     * �Œ�����t�g�����U�N�V�����̒l���h01�F���t�i�V�K�����j�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@      ��t���ԋ敪�̒l��WEB3TradingTimeTypeDef.MMF�i�ݒ�j�B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��RuitoTypeEnum.MMF<BR>
     *               
     * �Œ�����t�g�����U�N�V�����̒l���h02�F���t�i�V�K�����j�h�̏ꍇ�A<BR>
     * �@@�@@�@@      �@@��t���ԋ敪�̒l��WEB3TradingTimeTypeDef.MMF�i�ݒ���j�B<BR>
     * <BR>
     * �@@�|������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *                �擾�����،���ЃI�u�W�F�N�g.getInstitutionCode()�̖߂�l<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     *                �擾�������X�I�u�W�F�N�g.getBranchCode()�̖߂�l<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �擾������t���ԋ敪<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     *                �擾����������t�g�����U�N�V����<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     *               ������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@ �msetAttribute�ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݒ�L�[�F ������ԃR���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * �@@�@@�@@�ݒ�l�F ������ԃR���e�L�X�g<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * �R�j�@@���������b�N����B <BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(
     *          �،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
     * �������͗ݓ������t�L���[Params���ҏW�B <BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g <BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h���� <BR>
     * @@return Object
     * @@roseuid 4087C0F0035B
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String STR_METHOD_NAME = "onCall()";
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
        RuitoOrderUnit l_ruitoOrderUnit = null; //�ݓ������P�ʃI�u�W�F�N�g
        l_ruitoOrderUnit = (RuitoOrderUnit)l_serviceParam[0];
        //���X�I�u�W�F�N�g�̎擾
        FinApp l_finApp = null;
        Branch l_branch = null; //���X�I�u�W�F�N�g
        Institution l_institution = null; //�،���ЃI�u�W�F�N�g
        l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        WEB3GentradeTradingClendarContext l_context = null;
        RuitoOrderUnitParams l_ruitoOrderUnitParams = null; //�ݓ������P��Params
        try
        {
            log.debug("l_ruitoOrderUnit.getBranchId() =" + l_ruitoOrderUnit.getBranchId());
            l_branch = l_accMgr.getBranch(l_ruitoOrderUnit.getBranchId());

            //�،���ЃI�u�W�F�N�g�̎擾
            l_institution = l_branch.getInstitution();
            //�擾�����ݓ������P�ʃI�u�W�F�N�g.getDataSourceObject()���R�[�����A
            //�ݓ������P��Params���擾����B
            l_ruitoOrderUnitParams = new RuitoOrderUnitParams();
            l_ruitoOrderUnitParams = (RuitoOrderUnitParams)
                    l_ruitoOrderUnit.getDataSourceObject();
            
            //������t�g�����U�N�V�������擾����
            String l_strOrderAcceptTransaction = null;
            if (OrderTypeEnum.RUITO_BUY.equals(
                l_ruitoOrderUnitParams.getOrderType()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            else if (OrderTypeEnum.RUITO_SELL.equals(
                l_ruitoOrderUnitParams.getOrderType()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }            
           
            //��t���ԋ敪���擾����
            String l_strAcceptTimeDiv = null;
            int l_intRuitoType = l_ruitoOrderUnitParams.getRuitoType().intValue();
            log.debug("�ݓ��^�C�v = " + l_intRuitoType);

            if (l_intRuitoType == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
            {
                log.debug("test1");
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
            }
            else if (l_intRuitoType == RuitoTypeEnum.MMF.intValue() && 
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN.equals(l_strOrderAcceptTransaction))
            {
                log.debug("test2");
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MMF_SET;
            }
            else if (l_intRuitoType == RuitoTypeEnum.MMF.intValue() && 
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN.equals(l_strOrderAcceptTransaction))
            {
                log.debug("test3");
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MMF_SET_CANCEL;
            }
            log.debug("�،���ЃR�[�h = " + l_institution.getInstitutionCode());
            log.debug("���X�R�[�h = " + l_branch.getBranchCode());
            log.debug("��t���ԋ敪 = " + l_strAcceptTimeDiv);
            
            //������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����
            l_context = new WEB3GentradeTradingClendarContext();
            //�|������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h
            l_context.setInstitutionCode(l_institution.getInstitutionCode());
            //����J�����_�R���e�L�X�g.���X�R�[�h
            l_context.setBranchCode(l_branch.getBranchCode());
            //����J�����_�R���e�L�X�g.�s��R�[�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.�����R�[�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪
            l_context.setTradingTimeType(l_strAcceptTimeDiv);
            //����J�����_�R���e�L�X�g.������t���i
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V����
            l_context.setOrderAcceptTransaction(l_strOrderAcceptTransaction);

            //�Q�j��t�����A���t���[�����Z�b�g����B
            // ������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            log.debug("test");
            //WEB3BaseException
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�
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
        return l_context;

    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     *    �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@   ������ԊǗ�.OFFSET_TAG<BR>
     *    �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCall���^�[���l <BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l <BR>
     * @@roseuid 4087C0F0037A
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
     *    �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@   ������ԊǗ�.OFFSET_TAG<BR>
     * �@@   ����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_obj - onCall���^�[���l <BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g <BR>
     * @@roseuid 4087C0F0038A
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
