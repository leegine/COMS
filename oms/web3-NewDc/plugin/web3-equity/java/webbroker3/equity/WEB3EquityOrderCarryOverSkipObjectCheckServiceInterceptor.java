head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�X�C���^�Z�v�^(WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/04 �������F(SRA) �V�K�쐬
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * interface�u���������J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�X�v�ɑ΂���<BR>
 * �C���^�Z�v�^�B<BR>
 * ������Ԏ��ԊǗ��N���X���g�p���邽�߂̃R���e�L�X�g�ݒ���s���B
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor
    implements Interceptor
{
    /**
       * ���O�o�̓��[�e�B���e�B�B
       */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor.class);

    /**
     * @@roseuid 40B2E7BD02D7
     */
    public WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor()
    {

    }

    /**
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����A�T�[�r�X���\�b�h�J�n���ɃR�[�������j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|�����P�ʃI�u�W�F�N�g�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
     * <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �����P��.���XID �̕��X<BR>
     * �I�u�W�F�N�g�̓�����<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �����P��.���XID �̕��X<BR>
     * �I�u�W�F�N�g�̓�����<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �����P��.�s��ID �̎s��<BR>
     * �I�u�W�F�N�g�̓�����<BR>
     *   ����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = null�i�g�p���Ȃ����߁j<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null�i�g�p���Ȃ����߁j<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F"web3.tradingcalendarcontext"<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 406A4D45028B
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        if (l_serviceParam == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�T�[�r�X�̈���[0]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����
        OrderUnit l_orderUnit = (OrderUnit) l_serviceParam[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        if (l_orderUnitRow == null)
        {
            return null;
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Branch l_branch;
        Market l_market;
        try
        {
            //get���X�I�u�W�F�N�g
            l_branch =
                l_finApp.getAccountManager().getBranch(
                    l_orderUnit.getBranchId());
            //get�s��I�u�W�F�N�g
            l_market =
                l_finApp.getFinObjectManager().getMarket(
                    l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_nfe.getMessage(),
            l_nfe);
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
            
        //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �����P��.���XID ��
        //���X�I�u�W�F�N�g�̓�����
        l_context.setInstitutionCode(
            l_branch.getInstitution().getInstitutionCode());
            
            
        //����J�����_�R���e�L�X�g.���X�R�[�h = �����P��.���XID �̕��X
        // �I�u�W�F�N�g�̓�����
        l_context.setBranchCode(l_branch.getBranchCode());
        
        //����J�����_�R���e�L�X�g.�s��R�[�h = �����P��.�s��ID �̎s��
        //�I�u�W�F�N�g�̓�����
        l_context.setMarketCode(l_market.getMarketCode());
        
        //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //����J�����_�R���e�L�X�g.������t���i = null
        l_context.setOrderAcceptProduct(null);
        
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null
        // �����P��.������ʂ�蔻�肵�ĕҏW
        l_context.setOrderAcceptTransaction(null);
            
        //������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //��t�����A���t���[�����Z�b�g����
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * �T�[�r�X�I�����ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_returnValue - onCall���^�[���l
     * @@param l_context - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 406A4D45029B
     */
    public void onReturn(Object l_returnValue, Object l_context)
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
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 406030FF02C8
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
