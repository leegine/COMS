head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������tUnitService�C���^�Z�v�^�N���X(WEB3MutualCancelAcceptUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/23 ��O�� (���u) ���r���[    
*/

package webbroker3.mf;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M�������tUnitService�C���^�Z�v�^�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualCancelAcceptUnitServiceInterceptor
    implements Interceptor
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualCancelAcceptUnitServiceInterceptor.class);

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|����.�T�[�r�X�̈���[0]�𓊐M�����P�ʃI�u�W�F�N�g�ɃL���X�g����B <BR>
     * <BR>
     * �@@�|���X�I�u�W�F�N�g�̎擾 <BR>
     * �@@�@@�A�J�E���g�}�l�[�W��.getBranch()���R�[�����ĕ��X�I�u�W�F�N�g���擾����B <BR>
     * �@@�@@[getBranch�ɓn���p�����^] <BR>
     * �@@�@@�@@���XID�F �擾�������M�����P��.getBranchId()�̖߂�l <BR>
     * <BR>
     * �@@�|�،���ЃI�u�W�F�N�g�̎擾 <BR>
     * �@@�@@�擾�������X�I�u�W�F�N�g.getInstitution()���R�[������<BR>
     * �،���ЃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�|�擾�������M�����P�ʃI�u�W�F�N�g.getDataSourceObject()���R�[�����A<BR>
     * ���M�����P��Params���擾����B <BR>
     * <BR>
     * �@@�|�g�����M�����I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�g�����M�����}�l�[�W��.getProduct()���R�[�����āA<BR>
     * Product�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mgetProduct�ɓn���p�����^�n<BR>
     * �@@�@@�@@����ID�F �擾�������M�����P��Params.getProductId()�̖߂�l<BR>
     * �@@�@@�g�����M�����}�l�[�W��.to����()���R�[�����āA<BR>
     * �g�����M�����I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mto�����ɓn���p�����^]<BR>
     * �@@�@@�@@Row�I�u�W�F�N�g�F <BR>
     * �擾����Product�I�u�W�F�N�g.getDataSourceObject()�̖߂�l<BR>
     * <BR>
     * �@@�|������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     * �擾�����،���ЃI�u�W�F�N�g.getInstitutionCode()�̖߂�l <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = �擾�������X�I�u�W�F�N�g<BR>
     * .getBranchCode()�̖߂�l <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h = <BR>
     * �擾�����g�����M�����I�u�W�F�N�g.getProductCode()�̖߂�l<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
     * WEB3TradingTimeTypeDef.�����M��<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t���i = �h07�F�����M���h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     * �擾����������t�g�����U�N�V���� <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * ������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �@@�@@�msetAttribute�ɓn���p�����^�n <BR>
     * �@@�@@�@@�ݒ�L�[�F ������ԃR���e�L�X�g.TRADING_CALENDAR_TAG <BR>
     * �@@�@@�@@�ݒ�l�F ������ԃR���e�L�X�g <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@���������b�N����B <BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
     * �@@�@@�������͓��M�����t�L���[����ҏW�B<BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 405660100198
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParam == null)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (l_serviceParam.length == 0)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����
        //�|����.�T�[�r�X�̈���[0]�𓊐M�����P�ʃI�u�W�F�N�g�ɃL���X�g����
        MutualFundOrderUnit l_mutualOrderUnit =
            (MutualFundOrderUnit) l_serviceParam[0];

        //�|���X�I�u�W�F�N�g�̎擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //�A�J�E���g�}�l�[�W��.getBranch()���R�[�����ĕ��X�I�u�W�F�N�g���擾����
            Branch l_branch =
                l_accMgr.getBranch(l_mutualOrderUnit.getBranchId());

            //�|�،���ЃI�u�W�F�N�g�̎擾
            // �擾�������X�I�u�W�F�N�g.getInstitution()���R�[��
            Institution l_institution = l_branch.getInstitution();

            //�|�擾�������M�����P�ʃI�u�W�F�N�g.getDataSourceObject()���R�[��
            MutualFundOrderUnitParams l_mutualOrderUnitParams =
                new MutualFundOrderUnitParams(
                      (MutualFundOrderUnitRow) l_mutualOrderUnit.getDataSourceObject());

            //�|�g�����M�����I�u�W�F�N�g���擾���� 
            //�g�����M�����}�l�[�W�����擾����
            WEB3MutualFundProductManager l_mutualManager =
                (WEB3MutualFundProductManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getProductManager();

            //�g�����M�����}�l�[�W��.getProduct()���R�[��
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualManager.getProduct(
                    l_mutualOrderUnitParams.getProductId());

            //�|������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �،���ЃI�u�W�F�N�g.getInstitutionCode()
            l_context.setInstitutionCode(l_institution.getInstitutionCode());
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = ���X�I�u�W�F�N�g.getBranchCode()
            l_context.setBranchCode(l_branch.getBranchCode());
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.�����R�[�h = �g�����M�����I�u�W�F�N�g.getProductCode()
            l_context.setProductCode(l_mutualFundProduct.getProductCode());
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 =  WEB3TradingTimeTypeDef.�����M��
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            
            //����J�����_�R���e�L�X�g.������t���i = �h07�F�����M���h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MUTUAL_FUND);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �擾����������t�g�����U�N�V����
            l_context.setOrderAcceptTransaction(null);
            
            //-ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
            
            WEB3GentradeTradingTimeManagement.setTimestamp();              
                         
            //�R�j�@@���������b�N����B 
            String l_strAccountCode = l_accMgr.getMainAccount(
                    l_mutualOrderUnitParams.getAccountId()).getAccountCode();      
            l_accMgr.lockAccount(
                    l_institution.getInstitutionCode(),
                    l_branch.getBranchCode(),
                    l_strAccountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__an unexpected error__ when "
                    + " l_manager.getBranch(BranchId)"
                    + " with BranchId = "
                    + l_mutualOrderUnit.getBranchId(),l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()",l_ex);
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
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 4056601001A7
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
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 4056601001B7
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
