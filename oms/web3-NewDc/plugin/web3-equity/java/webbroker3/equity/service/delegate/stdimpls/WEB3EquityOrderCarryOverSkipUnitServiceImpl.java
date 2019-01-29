head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverSkipUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������J�z�X�L�b�v�����ʒm�ꌏ�T�[�r�XImpl(WEB3EquityOrderCarryOverSkipPartServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 羐� (���u) �V�K�쐬
Revesion History : 2004/09/21 Ḗ@@��(���u) �C��
Revesion History : 2004/12/13 �������F(SRA) �c�Č��Ή��ɂ��C��
Revesion History : 2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/11/20 ������@@(���u)���f��No.1043
Revesion History : 2007/01/31 ������@@(���u)���f��No.1117
Revesion History : 2008/12/05 �������@@(���u)���f��No.1326
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.data.HostEquityCarryoverSkipParams;
import webbroker3.equity.data.OrderCarryoverSkipProdDao;
import webbroker3.equity.data.OrderCarryoverSkipProdParams;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipObjectCheckService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3RegistDivisionDef;
import webbroker3.common.define.WEB3SkipMarketCodeDef;
import webbroker3.common.define.WEB3SkipRegistTypeDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;

/**
 * �i���������J�z�X�L�b�v�����ʒm�ꌏ�T�[�r�XImpl�j�B
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverSkipUnitServiceImpl
    implements WEB3EquityOrderCarryOverSkipUnitService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderCarryOverSkipUnitServiceImpl.class);

    private final String ALL_PRODUCT = "ALL";

    /**
     * @@roseuid 40B2D544007F
     */
    public WEB3EquityOrderCarryOverSkipUnitServiceImpl()
    {

    }

    /**
     * (�����J�z���ʍX�V)<BR>
     * �X�L�b�v�����̓o�^�^�o�^�����ʒm���e�A<BR>
     * �y�ђ����J�z�����̎��s�󋵁i�������^�����ρj�ɂ��A<BR>
     * �K�v�ȌJ�z�������ʂ̍X�V���s���B<BR>
     * <BR>
     * �P�j�@@�����̏o���I��List����A�ȉ��̏����ɊY�����郌�R�[�h��<BR>
     * �@@�u�����J�z�����敪�v���擾����B<BR>
     * <BR>
     * �@@�@@�@@���擾������<BR>
     * �@@�@@�@@�،���ЃR�[�h�������̒ʒm�L���[Params.�،���ЃR�[�h<BR>
     * �@@�@@�@@���@@�����^�C�v������<BR>
     * <BR>
     * �Q�j�@@this.update�X�L�b�v�����e�[�u��(�����̒����J�z�X�L�b�v�����ʒm�L���[Params)���R�[������B<BR>
     * <BR>
     * �R�j  �Y���f�[�^�Ȃ��̏ꍇ�A�܂��� �擾�����f�[�^�̒����J�z�����敪�������� �̏ꍇ�́A<BR>
     *       ����������return����B<BR>   
     * <BR>
     * �S�j�@@�擾�����f�[�^�̒����J�z�����敪�������� �̏ꍇ�́A<BR>
     * �@@�����̒����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�o�^�敪�̒l�ɂ��<BR>
     * �@@�������򂷂�B<BR>
     * <BR>
     * �@@���X�L�b�v�o�^�敪���o�^�@@�̏ꍇ��<BR>
     * �@@undo�����J�z( )���R�[������B�i�J�z�����̍폜�j<BR>
     * <BR>
     * �@@���X�L�b�v�o�^�敪�������@@�̏ꍇ��<BR>
     * �@@call�����J�z( )���R�[������B�i�J�z�����̍쐬�j<BR>
     * @@param l_hostEquityCarryoverSkipParams - (�����J�z�X�L�b�v�����ʒm�L���[Params)<BR>
     * @@param l_lisOrderExecEndList (�o���I��List)<BR>
     * �y�o���I���e�[�u���z���R�[�h��List�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4137CF99002F
     */
    public void updateOrderCarryOverResult(
        HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams, 
        List l_lisOrderExecEndList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateOrderCarryOverResult(HostEquityCarryoverSkipParams)";        
        log.entering(STR_METHOD_NAME);
        
        if (l_hostEquityCarryoverSkipParams == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        if(l_lisOrderExecEndList == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        int l_intNum = l_lisOrderExecEndList.size();
        log.debug("l_intNum = " + l_intNum);
        
        String l_strInstitutionCode = l_hostEquityCarryoverSkipParams.getInstitutionCode();
        OrderexecutionEndParams l_orderExeutionParamas = null;
        OrderexecutionEndParams l_orderExeutionParamasPer = null;
        
        // �P�j�@@�����̏o���I��List����A�ȉ��̏����ɊY�����郌�R�[�h��
        // �@@�u�����J�z�����敪�v���擾����B
        // 
        // �@@�@@�@@���擾������
        // �@@�@@�@@�،���ЃR�[�h�������̒ʒm�L���[Params.�،���ЃR�[�h
        // �@@�@@�@@���@@�����^�C�v������
        for (int i = 0;i < l_intNum; i++)
        {
            l_orderExeutionParamasPer = new OrderexecutionEndParams((OrderexecutionEndRow)l_lisOrderExecEndList.get(i));
            String l_strInstitutionCode1 = l_orderExeutionParamasPer.getInstitutionCode();
            ProductTypeEnum l_productType = l_orderExeutionParamasPer.getProductType();
            log.debug("l_strInstitutionCode1 = " + l_strInstitutionCode1);
            log.debug("l_productType = " + l_productType);
            if(l_strInstitutionCode.equals(l_strInstitutionCode1)
                && ProductTypeEnum.EQUITY.equals(l_productType))
            {
                l_orderExeutionParamas = l_orderExeutionParamasPer;
                break;                      
            }
        }
                
		//�Q�j�@@this.update�X�L�b�v�����e�[�u��(�����̒����J�z�X�L�b�v�����ʒm�L���[Params)���R�[������B
		this.updateSkipProdTable(l_hostEquityCarryoverSkipParams);
        
        //�R�j�@@�Y���f�[�^�Ȃ��̏ꍇ�A�܂��� �擾�����f�[�^�̒����J�z�����敪�������� �̏ꍇ�́A
        // �@@ �@@����������return����B
        if (l_orderExeutionParamas == null)
        {
            log.debug("�Y���f�[�^�Ȃ��̏ꍇ");
            return;    
        }
        else if (WEB3CarryoverEndTypeDef.NOT_STARTED_PROCESS.equals(l_orderExeutionParamas.getCarryoverEndType()))
        {
            log.debug("�����J�z�����敪�������� �̏ꍇ");
            return;
        }        
        
        // �S�j�@@�擾�����f�[�^�̒����J�z�����敪�������� �̏ꍇ�́A
        // �@@�����̒����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�o�^�敪�̒l�ɂ��
        // �@@�������򂷂�B
        if (WEB3CarryoverEndTypeDef.COMPLETE_PROCESS.equals(l_orderExeutionParamas.getCarryoverEndType()))
        {
            //�����J�z�����敪�������� �̏ꍇ            
            log.debug("�����J�z�����敪�������� �̏ꍇ");
            
            String l_strRegType = l_hostEquityCarryoverSkipParams.getSkipRegistType();
            log.debug("l_strRegType = " + l_strRegType);
            // �@@���X�L�b�v�o�^�敪���o�^�@@�̏ꍇ��
            if (WEB3SkipRegistTypeDef.REGISTRATION.equals(l_strRegType))
            {
                // �@@undo�����J�z( )���R�[������B�i�J�z�����̍폜�j
                log.debug("undo�����J�z( )���R�[������");
                undoOrderCarryOver(l_hostEquityCarryoverSkipParams);
            }
            // �@@���X�L�b�v�o�^�敪�������@@�̏ꍇ��
            else if (WEB3SkipRegistTypeDef.DELETE.equals(l_strRegType))
            {
                // �@@call�����J�z( )���R�[������B�i�J�z�����̍쐬�j
                log.debug("call�����J�z( )���R�[������");
                callOrderCarryOver(l_hostEquityCarryoverSkipParams);
            }    
        }
    }

    /**
     * (undo�����J�z)<BR>
     * �����J�z�X�L�b�v�����̓o�^���A�����J�z���ʂɔ��f����B<BR>
     * �i���ɓ��Y�،���Ђɂ��Ē����J�z�������I�����Ă���ꍇ�A<BR>
     * �@@�X�L�b�v�����o�^�ɂ���ĕK�v�ƂȂ����A�J�z���ʂ̗��������̍폜���s���j<BR>
     * <BR>
     * �V�[�P���X�}�u�i�X�L�b�v�����ʒm�jprocess�vundo�����J�z( )�����Q�ƁB<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ɍ��v����A�X�L�b�v�����̒����P�ʃI�u�W�F�N�g�̂����A<BR>
     * �@@�@@�@@�����J�z�����ō쐬���ꂽ�f�[�^���擾����B<BR>
     * <BR>
     * �@@�@@�@@�����o������<BR>
     * �@@�@@�@@���XID�������J�z�X�L�b�v�����ʒm�L���[Params.�،���ЃR�[�h �ɊY������<BR>
     * �@@�@@�@@�@@�@@�،���ЃI�u�W�F�N�g.getBranches( )�Ŏ擾�����S���X�I�u�W�F�N�g�̕��XID�̂����ꂩ<BR>
     * �@@�@@�@@���@@����ID�������J�z�X�L�b�v�����ʒm�L���[Params.�����R�[�h �ɊY������<BR>
     * �@@�@@�@@�@@�@@�����I�u�W�F�N�g�̖���ID<BR>
     * �@@�@@�@@���@@�s��ID�������J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h �ɊY������<BR>
     * �@@�@@�@@�@@�@@�s��I�u�W�F�N�g�̎s��ID�̂����ꂩ<BR>
     * �@@�@@�@@���@@�����L����ԁiOrderOpenStatus�j���I�[�v��<BR>
     * �@@�@@�@@���@@������������<BR>
     * �@@�@@�@@���@@���񒍕��̒����P��ID���inull or 0�j<BR>
     * �@@�@@�@@������ID�����w��B<BR>
     * <BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�����R�[�h��ALL�i�S�����j�̏ꍇ�́A<BR>
     * �@@�@@�@@���o�����ɖ����R�[�h�͎w�肵�Ȃ��B<BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��F�i�S�s��j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���o�����Ɏs��R�[�h�͎w�肵�Ȃ��B<BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��1�i�����j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���o�����Ɏs��R�[�h�i�����j�B<BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��2�i���y��NNM�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���o�����Ɏs��R�[�h�i��さNNM�j�B<BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��3�i���É��j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���o�����Ɏs��R�[�h�i���É��j�B<BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��6�iJASDAQ�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���o�����Ɏs��R�[�h�iJASDAQ�j�B<BR>
     * <BR>
     * �@@�@@�@@���ڋq���g�ɂ���ČJ�z����������������������Ă���ꍇ�́A<BR>
     * �@@�@@�@@����L���o�����ɍ��v�����J�z��undo�ΏۂƂȂ�Ȃ��B<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�����X�L�b�v�����̒����P�ʃI�u�W�F�N�g(*1)�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�@@�ȉ��A�f�[�^�x�[�X��update�^delete�́A�S�ăN�G���v���Z�b�T���g�p��<BR>
     * �@@�@@�@@SQL���𔭍s���邱�Ƃōs���B<BR>
     * <BR>
     * �Q�|�P�j�@@�ڋq�P�ʁiLoop�̈ꌏ�ځA�܂��͌���ID���u���C�N�j�Ō��������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�@@�@@�@@�@@�������́A�����P��.����ID�ɊY������ڋq�I�u�W�F�N�g���ҏW����B<BR>
     * <BR>
     * �Q�|�Q�j�@@���[���G���W���T�[�o�ɁA�����t�����̒����J�z�X�L�b�v��ʒm����B<BR>
     * <BR>
     * �Q�|�Q�|�P�j�@@�g�����������}�l�[�W��.notify���[���G���W���T�[�o()���R�[������B<BR>
     * �@@�@@�@@------------------------------------------------------------ <BR>
     * �@@�@@�@@��notify���[���G���W���T�[�o()�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾�����Y���̒����P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@�����F�@@ORDER_INVALIDATED_BY_MKT <BR>
     * �@@�@@�@@------------------------------------------------------------ <BR>
     * �Q�|�Q�|�Q�j�@@notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@catch���ď������p������B�i���[���o�b�N�͂����Ȃ�Ȃ��j<BR>
     * <BR>
     * �Q�|�R�j�@@�J�z�������̒����G���[���R�R�[�h ��update����B<BR>
     * <BR>
     * �Q�|�R�|�P�j�@@�J�z�������̒����P�ʃ��R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���擾������<BR>
     * �@@�@@�@@�@@�@@�i�����P��ID �܂��� ���񒍕��̒����P��ID�j���A<BR>
     * �@@�@@�@@�@@�@@(*1)�̒����P��.���񒍕��̒����P��ID �Ɠ������A<BR>
     * �@@�@@�@@�@@�@@���@@�����L����ԁiOrderOpenStatus�j��CLOSED �ɊY������f�[�^�̂����A<BR>
     * �@@�@@�@@�@@�@@�쐬���t���ł��V�������́B<BR>
     * <BR>
     * �Q�|�R�|�Q�j�@@�Q�|�R�|�P�j�Ŏ擾���������P�ʃ��R�[�h.�����G���[���R�R�[�h ��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�����J�z�X�L�b�v�����G���[�v�ɁA<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V���t�����ݓ����ɁA���ꂼ��X�V����B<BR>
     * <BR>
     * �Q�|�R�|�R�j�@@�J�z�������̒��������́A�ŏI�������R�[�h�̒����G���[���R�R�[�h ��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@update����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����P��ID���Q�|�R�|�P�j�Ŏ擾���������P�ʃ��R�[�h.�����P��ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���@@��������ԍ����Q�|�R�|�P�j�Ŏ擾���������P�ʃ��R�[�h.���������ŏI�ʔ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɊY�����钍���������R�[�h.�����G���[���R�R�[�h���u�����J�z�X�L�b�v�����G���[�v�ɁA<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V���t�����ݓ����ɁA���ꂼ��X�V����B<BR>
     * <BR>
     * �Q�|�R�|�S�j�@@�J�z�������̒����i�w�b�_�j�̍X�V������update����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����ID���Q�|�R�|�P�j�Ŏ擾���������P�ʃ��R�[�h.����ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɊY�����钍���i�w�b�_�j���R�[�h.�X�V���t�����ݓ����ɍX�V����B<BR>
     * <BR>
     * �Q�|�S�j�@@�y������������L���[�e�[�u���z����A�Y������J�z�����f�[�^��delete����B<BR>
     * �@@�@@�@@�@@�@@�폜�����ɂ́A�P�j�Ŏ擾�����X�L�b�v�����̒����P�� �̃v���p�e�B���w�肷��B<BR>
     * <BR>
     * �@@�@@�@@���폜������<BR>
     * �@@�@@�@@�،���ЃR�[�h�������P��.���XID �̕��X�I�u�W�F�N�g.�،���ЃR�[�h<BR>
     * �@@�@@�@@���@@���X�R�[�h�������P��.���XID �̕��X�I�u�W�F�N�g.���X�R�[�h<BR>
     * �@@�@@�@@���@@�����R�[�h���g���v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID).�����R�[�h<BR>
     * �@@�@@�@@���@@���ʃR�[�h�������P��.���ʃR�[�h<BR>
     * �@@�@@�@@���@@�����敪��������<BR>
     * <BR>
     * �Q�|�T�j�@@�J�z�����ō쐬���ꂽ�X�L�b�v�����̒�����<BR>
     * �@@�@@�@@�@@�@@�L���ȗ\�񒍕����ݒ肳��Ă����ꍇ�A�\�񒍕��̍폜���s���B<BR>
     * <BR>
     * �Q�|�T�|�P�j�@@�g�����������}�l�[�W��.is�\�񒍕��m�F�v()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̒����P�ʂɂ́A�P�j�Ŏ擾���������P�ʂ��w�肷��B<BR>
     * <BR>
     * �Q�|�T�|�Q�j�@@�Q�|�T�|�P�j�̖߂�l��true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����\�񒍕��X�V�T�[�r�XImpl.deleteAll�\�񒍕��P��()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̐e�����̒���ID�ɂ́A�P�j�Ŏ擾���������P��.����ID ���w�肷��B<BR>
     * <BR>
     * �Q�|�U�j�@@�J�z�����ō쐬���ꂽ�X�L�b�v�����̒��������f�[�^���A<BR>
     * �@@�@@�@@�@@�@@�y���������e�[�u���z���delete����B<BR>
     * �@@�@@�@@�@@�@@�폜�L�[�ɂ́A�P�j�Ŏ擾���������P��.����ID ���w�肷��B<BR>
     * <BR>
     * �Q�|�V�j�@@�J�z�����ō쐬���ꂽ�X�L�b�v�����̒����P��.�����J�e�S��=="�ԍϒ���"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�ԍϒ��������ʂ����Z�i���b�N�������j���Ă���A�����ԍώw����f�[�^��<BR>
     * �@@�@@�@@�@@�@@�y�����ԍώw����e�[�u���z���delete����B<BR>
     * <BR>
     * �Q�|�V�|�P�j�@@�����P��.getContractsToClose()�ɂ��A�����ԍώw����̈ꗗ���擾����B<BR>
     * <BR>
     * �Q�|�V�|�Q�j�@@�擾���������ԍώw����̐����A�ȉ��̏������J��Ԃ��A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ԍώw��Ή��̌��̕ԍϒ��������ʂ����Z������ŁA<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ԍώw��f�[�^��delete����B<BR>
     * <BR>
     * ���������� START LOOP ����������<BR>
     * <BR>
     * �Q�|�V�|�Q�|�P�j�@@�ԍϐ��ʂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�ԍϐ���(*1) = �����ԍώw����.getQuantity()<BR>
     * <BR>
     * �Q�|�V�|�Q�|�Q�j�@@�ԍϒ��������ʂ𒲐�����B<BR>
     * <BR>
     * �@@�@@�@@�����|�W�V�����}�l�[�W��.updateLockedQuantity(long, Contract, double)���R�[������B<BR>
     * �@@�@@�@@------------------------------------------------------------<BR>
     * �@@�@@�@@��updateLockedQuantity()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P��ID�F�@@�P�j�Ŏ擾���������P��.�����P��ID<BR>
     * �@@�@@�@@�����F�@@�擾���������ԍώw����.����ID�ɊY�����錚��<BR>
     * �@@�@@�@@�������ʁF�@@�ԍϐ���(*1)�~�i�|�P�j<BR>
     * �@@�@@�@@------------------------------------------------------------<BR>
     * <BR>
     * ���������� END LOOP ����������<BR>
     * <BR>
     * �Q�|�V�|�R�j�@@�����ԍώw����f�[�^��delete����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�폜�L�[�ɂ́A�P�j�Ŏ擾���������P��.����ID ���w�肷��B<BR>
     * <BR>
     * �Q�|�W�j�@@�J�z�����ō쐬���ꂽ�X�L�b�v�����̒����P��.�������=="����������"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@���t���������ʂ����Z����B�i���b�N����������j<BR>
     * <BR>
     * �Q�|�W�|�P�j�@@���t���ʂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@���t����(*2) = �P�j�Ŏ擾���������P��.getQuantity()<BR>
     * <BR>
     * �Q�|�W�|�Q�j�@@���t���������ʂ𒲐�����B<BR>
     * <BR>
     * �@@�@@�@@�����|�W�V�����}�l�[�W��.update���b�N������(long, long, long, long, double)���R�[������B<BR>
     * �@@�@@�@@------------------------------------------------------------<BR>
     * �@@�@@�@@��update���b�N������()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@����ID�F�@@�P�j�Ŏ擾���������P��.����ID<BR>
     * �@@�@@�@@�⏕����ID�F�@@�P�j�Ŏ擾���������P��.�⏕����ID<BR>
     * �@@�@@�@@�����P��ID�F�@@�P�j�Ŏ擾���������P��.�����P��ID<BR>
     * �@@�@@�@@����ID�F�@@�P�j�Ŏ擾���������P��.����ID<BR>
     * �@@�@@�@@�������ʁF�@@���t����(*2)�~�i�|�P�j<BR>
     * �@@�@@�@@------------------------------------------------------------<BR>
     * <BR>
     * �Q�|�X�j�@@�J�z�����ō쐬���ꂽ�X�L�b�v�����̒����P�ʃf�[�^���A<BR>
     * �@@�@@�@@�@@�@@�y�����P�ʃe�[�u���z���delete����B<BR>
     * �@@�@@�@@�@@�@@�폜�L�[�ɂ́A�P�j�Ŏ擾���������P��.����ID ���w�肷��B<BR>
     * <BR>
     * �Q�|�P�O�j�@@�J�z�����ō쐬���ꂽ�X�L�b�v�����̒����f�[�^���A<BR>
     * �@@�@@�@@�@@�@@�y�����e�[�u���i�w�b�_�j�z���delete����B<BR>
     * �@@�@@�@@�@@�@@�폜�L�[�ɂ́A�P�j�Ŏ擾���������P��.����ID ���w�肷��B<BR>
     * <BR>
     * �Q�|�P�P�j�@@�]�͍X�V����<BR>
     * �@@�@@�ڋq�P�ʁi����ID���u���C�N�����ꍇ�A�܂��͍Ō�̒����P�ʂ̏������j�ɁA<BR>
     * �@@�@@.�]�͍Čv�Z�T�[�r�X.�]�͍Čv�Z(�⏕����)���R�[������B<BR>
     * <BR>
     * �@@�@@�����̕⏕�����́A<BR>
     * �@@�@@����ID�̌ڋq�I�u�W�F�N�g.getSubAccount(SubAccountTypeEnum.�����������)<BR>
     * �@@�@@�ɂĎ擾����B<BR>
     * @@param l_hostEquityCarryoverSkipParams <BR>
     * - �����J�z�X�L�b�v�����ʒm�L���[Params<BR>
     * @@throws WEB3BaseLayerException
     * @@roseuid 4137CF99008A
     */
    public void undoOrderCarryOver(HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "undoOrderCarryOver(HostEquityCarryoverSkipParams)";
        log.entering(STR_METHOD_NAME);
        if (l_hostEquityCarryoverSkipParams == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        // �P�j�@@�ȉ��̏����ɍ��v����A�X�L�b�v�����̒����P�ʃI�u�W�F�N�g�̂����A
        // �@@�@@�@@�����J�z�����ō쐬���ꂽ�f�[�^���擾����B
        //get�،���ЃR�[�h
        String l_strInstitutionCode =
            l_hostEquityCarryoverSkipParams.getInstitutionCode();
        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);
        
        //get�����R�[�h
        String l_strProductCode =
            l_hostEquityCarryoverSkipParams.getProductCode();
        log.debug("l_strProductCode = " + l_strProductCode);
        if (ALL_PRODUCT.equals(l_strProductCode))
        {
            l_strProductCode = null;
        }
        //get�X�L�b�v�s��R�[�h
        String l_strSkipMarketCode =
            l_hostEquityCarryoverSkipParams.getSkipMarketCode();
        log.debug("l_strSkipMarketCode = " + l_strSkipMarketCode);
        
        //get�s��R�[�hList
        String[] l_strMarketCodes = new String[2];
        String l_strMarketCode = null;
        if (WEB3SkipMarketCodeDef.TOKYO.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.TOKYO;
        }
        else if (WEB3SkipMarketCodeDef.OSAKA_AND_NNM.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes[0] = WEB3MarketCodeDef.OSAKA;
            l_strMarketCodes[1] = WEB3MarketCodeDef.NNM;
        }
        else if (WEB3SkipMarketCodeDef.NAGOYA.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.NAGOYA;
        }
		else if (WEB3SkipMarketCodeDef.JASDAQ.equals(l_strSkipMarketCode))
		{
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.JASDAQ;
		}
		else
		{
			l_strMarketCodes = null;
			l_strMarketCode = null;
		}

        log.debug("l_strMarketCodes = " + l_strMarketCodes);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        WEB3EquityProductManager l_eqProductManager =
            (WEB3EquityProductManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
        
        WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
        
        Institution l_institution = null;
        Long l_lngProductId = null;
        Long[] l_lngMarketIds = new Long[2];
        Long l_lngMarketId = null;
        
        try
        {
            //get�،���ЃI�u�W�F�N�g
            l_institution =
                l_accountManager.getInstitution(l_strInstitutionCode);
            log.debug("l_strProductCode = " + l_strProductCode);
            if (l_strProductCode != null)
            {
                //get����ID
                l_lngProductId = new Long(
                    l_eqProductManager.getProduct(l_institution, l_strProductCode).getProductId());
            }
            else
            {
                l_lngProductId = null;
            }
            log.debug("l_lngProductId = " + l_lngProductId);
            
            //get�s��ID
            if (l_strMarketCodes != null)
            {
                l_lngMarketIds[0] = new Long(
                    l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCodes[0]).getMarketId());
                log.debug("l_lngMarketIds[0] = " + l_lngMarketIds[0]);
                l_lngMarketIds[1] = new Long(
                    l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCodes[1]).getMarketId());
                log.debug("l_lngMarketIds[1] = " + l_lngMarketIds[1]);
            }
			else if (l_strMarketCode != null)
			{
				l_lngMarketIds = null;
				l_lngMarketId = 
					new Long(
						l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCode).getMarketId());
			}
			else
			{
				l_lngMarketIds = null;
			}
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

        }
        
        //get���X�I�u�W�F�N�g
        Branch[] l_branchs = l_institution.getBranches();
        //get����
        Timestamp l_tsCurTime =
            GtlUtils.getTradingSystem().getSystemTimestamp();
            
        String l_strCurTime = WEB3DateUtility.formatDate(l_tsCurTime, "yyyyMMdd");

        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objWhere = null;
        ArrayList l_lisObjWhere = new ArrayList();
        //�S���X�I�u�W�F�N�g�̕��XID
        int l_intSize = l_branchs.length;        
        l_sbWhere.append(" (branch_id = ? ");        
        l_lisObjWhere.add(new Long(l_branchs[0].getBranchId()));
        for (int i = 1; i < l_intSize; i++)
        {
            l_sbWhere.append(" or branch_id = ? ");
            l_lisObjWhere.add(new Long(l_branchs[i].getBranchId()));
        }
        l_sbWhere.append(") ");
        
        log.debug("l_sbWhere = " + l_sbWhere);
        log.debug("l_lstObjWhere = " + l_lisObjWhere);
        
        //�����J�z�X�L�b�v�����ʒm�L���[Params.�����R�[�h��ALL�i�S�����j�̏ꍇ�́A
        //���o�����ɖ����R�[�h�͎w�肵�Ȃ��B
        if (l_lngProductId != null)
        {
            l_sbWhere.append(" and product_id = ? ");            
            l_lisObjWhere.add(l_lngProductId);
        }
        
        //�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��F�i�S�s��j�̏ꍇ��
        //���o�����Ɏs��R�[�h�͎w�肵�Ȃ��B
        if (l_lngMarketIds != null)
        {
            l_sbWhere.append(" and (market_id = ? or market_id = ?) ");
            l_lisObjWhere.add(l_lngMarketIds[0]);
            l_lisObjWhere.add(l_lngMarketIds[1]);
        }
		else if (l_lngMarketId != null)
		{
			l_sbWhere.append(" and market_id = ? ");
			l_lisObjWhere.add(l_lngMarketId);
		}        

        //�����L����ԁiOrderOpenStatus�j���I�[�v��
        l_sbWhere.append(" and order_open_status = ? ");
        l_lisObjWhere.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));
        //������������
        l_sbWhere.append(" and  biz_date  > ? ");
        l_lisObjWhere.add(l_strCurTime);
        //�J�z�������P��ID��null ���� �J�z�������P��ID��0
        l_sbWhere.append(" and  (first_order_unit_id != 0) ");
        l_sbWhere.append(" and  (first_order_unit_id is not null) ");
        log.debug("l_sbWhere = " + l_sbWhere);
        // ������ID�����w��
        String l_strSort = "account_id asc";
        
        l_intSize = l_lisObjWhere.size();
        l_objWhere = new Object[l_intSize];
        
        l_lisObjWhere.toArray(l_objWhere);
        
        List l_lisRecords;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    l_strSort,
                    null,
                    l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        //�����`�F�b�N
        int l_intSize1 = 0;
        if(l_lisRecords != null)
        {
            l_intSize1 = l_lisRecords.size();
        }
        if (l_lisRecords.size() == 0)
        {
			log.exiting(STR_METHOD_NAME);
            return;
        }
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {

            // �Q�j�@@�P�j�Ŏ擾�����X�L�b�v�����̒����P�ʃI�u�W�F�N�g(*1)�����A�ȉ��̏������J��Ԃ��B
            EqtypeOrderUnitRow l_oldOrderUnitRow;
            long l_lngAccountId = 0L;
            for (int i = 0; i < l_intSize1; i++)
            {
                l_oldOrderUnitRow =
                    (EqtypeOrderUnitRow) l_lisRecords.get(i);
                
                // �Q�|�P�j�@@�ڋq�P�ʁiLoop�̈ꌏ�ځA�܂��͌���ID���u���C�N�j�Ō��������b�N����B
                if (l_lngAccountId != l_oldOrderUnitRow.getAccountId())
                {
                    l_lngAccountId = l_oldOrderUnitRow.getAccountId();
                    l_mainAccount =
                        (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
                    l_accountManager.lockAccount(
                        l_mainAccount.getInstitution().getInstitutionCode(),
                        l_mainAccount.getBranch().getBranchCode(),
                        l_mainAccount.getAccountCode());
                }

                //�Q�|�Q�j�@@���[���G���W���T�[�o�ɁA�����t�����̒����J�z�X�L�b�v��ʒm����B
                //
                //�Q�|�Q�|�P�j�@@�g�����������}�l�[�W��.notify���[���G���W���T�[�o()���R�[������B
                //�@@�@@�@@------------------------------------------------------------
                //�@@�@@�@@��notify���[���G���W���T�[�o()�F�����ݒ�d�l��
                //
                //�@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾�����Y���̒����P�ʃI�u�W�F�N�g
                //�@@�@@�@@�����F�@@ORDER_INVALIDATED_BY_MKT
                //�@@�@@�@@------------------------------------------------------------
                EqTypeOrderUnit l_orderUnit =
                    (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_oldOrderUnitRow);
                try
                {
                    l_orderManager.notifyRLS(
                        l_orderUnit,
                        OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT);
                }
                //�Q�|�Q�|�Q�j�@@notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
                //�@@�@@�@@�@@�@@�@@�@@catch���ď������p������B�i���[���o�b�N�͂����Ȃ�Ȃ��j
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ, ���[���o�b�N�͂����Ȃ�Ȃ�");
                }

                // �Q�|�Q�j�@@�J�z�������̒����G���[���R�R�[�h ��update����B
                
                // �Q�|�Q�|�P�j�@@�J�z�������̒����P�ʃ��R�[�h���擾����B
                // 
                // �@@�@@�@@�@@�@@���擾������
                // �@@�@@�@@�@@�@@�i�����P��ID �܂��� ���񒍕��̒����P��ID�j���A
                // �@@�@@�@@�@@�@@(*1)�̒����P��.���񒍕��̒����P��ID �Ɠ������A
                // �@@�@@�@@�@@�@@���@@�����L����ԁiOrderOpenStatus�j��CLOSED �ɊY������f�[�^�̂����A
                // �@@�@@�@@�@@�@@�쐬���t���ł��V�������́B
                StringBuffer l_sbOrderUnitWhere = new StringBuffer();
                l_sbOrderUnitWhere.append("(order_unit_id = ?");
                l_sbOrderUnitWhere.append(" or first_order_unit_id = ?)");
                l_sbOrderUnitWhere.append(" and (order_open_status = ?)");
                //���񒍕��̒����P��ID
                long l_lgnFirstOrderUnitId = l_oldOrderUnitRow.getFirstOrderUnitId();
                Object[] l_objOrderUnitWhere =
                    {new Long(l_lgnFirstOrderUnitId),new Long(l_lgnFirstOrderUnitId),OrderOpenStatusEnum.CLOSED};
                    
                QueryProcessor l_queryProcessor =
                    Processors.getDefaultProcessor();
                List l_listRecords =
                    l_queryProcessor.doFindAllQuery(
                        EqtypeOrderUnitRow.TYPE,
                        l_sbOrderUnitWhere.toString(),
                        "created_timestamp desc",
                        null,
                        l_objOrderUnitWhere);
                if(l_listRecords == null || l_listRecords.size() == 0 )
                {
                    throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                       this.getClass().getName() + "." + STR_METHOD_NAME);            
                }  
                EqtypeOrderUnitParams  l_orderUnitParams = 
                    new EqtypeOrderUnitParams((EqtypeOrderUnitRow) l_listRecords.get(0));
                
                // �Q�|�Q�|�Q�j�@@�Q�|�Q�|�P�j�Ŏ擾���������P�ʃ��R�[�h.�����G���[���R�R�[�h ��
                // �@@�@@�@@�@@�@@�@@�@@�@@�u�����J�z�X�L�b�v�����G���[�v�ɁA
                // �@@�@@�@@�@@�@@�@@�@@�@@�X�V���t�����ݓ����ɁA���ꂼ��X�V����B
                l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR); 
                l_orderUnitParams.setLastUpdatedTimestamp(l_tsCurTime);
                l_queryProcessor.doUpdateQuery(l_orderUnitParams);
                                
                // �Q�|�Q�|�R�j�@@�J�z�������̒��������́A�ŏI�������R�[�h�̒����G���[���R�R�[�h ��
                // �@@�@@�@@�@@�@@�@@�@@�@@update����B
                // 
                // �@@�@@�@@�@@�@@�@@�@@�@@�����P��ID���Q�|�Q�|�P�j�Ŏ擾���������P�ʃ��R�[�h.�����P��ID
                // �@@�@@�@@�@@�@@�@@�@@�@@���@@��������ԍ����Q�|�Q�|�P�j�Ŏ擾���������P�ʃ��R�[�h.���������ŏI�ʔ�
                // �@@�@@�@@�@@�@@�@@�@@�@@�ɊY�����钍���������R�[�h.�����G���[���R�R�[�h���u�����J�z�X�L�b�v�����G���[�v�ɁA
                // �@@�@@�@@�@@�@@�@@�@@�@@�X�V���t�����ݓ����ɁA���ꂼ��X�V����B
                long l_lngOrderUnitId = l_orderUnitParams.getOrderUnitId();
                int l_intActionSerialNo = l_orderUnitParams.getLastOrderActionSerialNo();
                StringBuffer l_sbOrderActionWhere = new StringBuffer();
                l_sbOrderActionWhere.append("order_unit_id = ?");
                l_sbOrderActionWhere.append(" and order_action_serial_no = ?");
                Object[] l_objOrderActionWhere =
                    {new Long(l_lngOrderUnitId),new Integer(l_intActionSerialNo)};
                List l_listOrderAction =l_queryProcessor.doFindAllQuery(
                    EqtypeOrderActionRow.TYPE,
                    l_sbOrderActionWhere.toString(),
                    null,
                    null,
                    l_objOrderActionWhere);
                if (l_listOrderAction == null || l_listOrderAction.size() == 0  )
                {
                    throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                       this.getClass().getName() + "." + STR_METHOD_NAME);            
                }  
                EqtypeOrderActionParams l_orderActionParams = 
                    new EqtypeOrderActionParams((EqtypeOrderActionRow) l_listOrderAction.get(0));
                l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR);
                l_orderActionParams.setLastUpdatedTimestamp(l_tsCurTime);
                l_queryProcessor.doUpdateQuery(l_orderActionParams);

                // �Q�|�Q�|�S�j�@@�J�z�������̒����i�w�b�_�j�̍X�V������update����B
                // 
                // �@@�@@�@@�@@�@@�@@�@@�@@����ID���Q�|�Q�|�P�j�Ŏ擾���������P�ʃ��R�[�h.����ID
                // �@@�@@�@@�@@�@@�@@�@@�@@�ɊY�����钍���i�w�b�_�j���R�[�h.�X�V���t�����ݓ����ɍX�V����B
                long l_lngOrderId = l_orderUnitParams.getOrderId();
                EqtypeOrderParams l_eqtypeOrderParams = 
                    new EqtypeOrderParams(EqtypeOrderDao.findRowByPk(l_lngOrderId));
                l_eqtypeOrderParams.setLastUpdatedTimestamp(l_tsCurTime);
                l_queryProcessor.doUpdateQuery(l_eqtypeOrderParams);

                // �Q�|�R�j�@@�y������������L���[�e�[�u���z����A�Y������J�z�����f�[�^��delete����B
                // �@@�@@�@@�@@�@@�폜�����ɂ́A�P�j�Ŏ擾�����X�L�b�v�����̒����P�� �̃v���p�e�B���w�肷��B
                // 
                // �@@�@@�@@���폜������
                // �@@�@@�@@�،���ЃR�[�h�������P��.���XID �̕��X�I�u�W�F�N�g.�،���ЃR�[�h
                // �@@�@@�@@���@@���X�R�[�h�������P��.���XID �̕��X�I�u�W�F�N�g.���X�R�[�h
                // �@@�@@�@@���@@�����R�[�h���g���v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID).�����R�[�h
                // �@@�@@�@@���@@���ʃR�[�h�������P��.���ʃR�[�h
                // �@@�@@�@@���@@�����敪��������
                StringBuffer l_sbHostEqtypeOrderWhere = new StringBuffer();
                l_sbHostEqtypeOrderWhere.append(" institution_code = ? ");
                l_sbHostEqtypeOrderWhere.append(" and branch_code = ? ");
                l_sbHostEqtypeOrderWhere.append(" and product_code = ? ");
                l_sbHostEqtypeOrderWhere.append(" and order_request_number = ? ");
                l_sbHostEqtypeOrderWhere.append(" and status = ? ");

                //get���X�I�u�W�F�N�g&�����I�u�W�F�N�g
                Branch l_branch = null;
                EqTypeProduct l_product = null;

                l_branch =
                    l_accountManager.getBranch(l_oldOrderUnitRow.getBranchId());
                l_product =
                    (EqTypeProduct)l_eqProductManager.getProduct(
                        l_oldOrderUnitRow.getProductId());

                Object[] l_objHostEqtypeOrderWhere =
                    {
                        l_branch.getInstitution().getInstitutionCode(),
                        l_branch.getBranchCode(),
                        l_product.getProductCode(),
                        l_oldOrderUnitRow.getOrderRequestNumber(),
                        WEB3HostStatusDef.NOT_STARTED_PROCESS };

                l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doDeleteAllQuery(
                    HostEqtypeOrderAllRow.TYPE,
                    l_sbHostEqtypeOrderWhere.toString(),
                    l_objHostEqtypeOrderWhere);
                
                if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
                {
                    l_updateService.deleteAllOrderUnit(l_orderUnit.getOrderId());
                }

                // �Q�|�T�j�@@�J�z�����ō쐬���ꂽ�X�L�b�v�����̒��������f�[�^���A
                // �@@�@@�@@�@@�@@�y���������e�[�u���z���delete����B
                // �@@�@@�@@�@@�@@�폜�L�[�ɂ́A�P�j�Ŏ擾���������P��.����ID ���w�肷��B
                l_queryProcessor.doDeleteAllQuery(
                    EqtypeOrderActionRow.TYPE,
                    "order_id = ?",
                    new Object[]{new Long(l_oldOrderUnitRow.getOrderId())});
                
                if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    EqTypeClosingContractSpec[] l_closingContractSpecs =
                        ((EqTypeContractSettleOrderUnit)l_orderUnit).getContractsToClose();
                    int l_intSpecLength = 0;
                    if (l_closingContractSpecs != null)
                    {
                        l_intSpecLength = l_closingContractSpecs.length;
                    }
                    for (int j = 0;j < l_intSpecLength;j++)
                    {
                        long l_lngContractId =
                            l_closingContractSpecs[j].getContractId();
                        double l_dblAdjustQuantity = l_closingContractSpecs[j].getQuantity() * -1D;
                        l_positionManager.updateLockedQuantity(
                            l_orderUnit.getOrderUnitId(),
                            l_positionManager.getContract(l_lngContractId),
                            l_dblAdjustQuantity);
                    }
                    
                    l_queryProcessor.doDeleteAllQuery(
                        EqtypeClosingContractSpecRow.TYPE,
                        "order_id = ?",
                        new Long[] { new Long(l_orderUnit.getOrderId())});
                }
                
                if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType()))
                {
                    double l_dblAdjustQuantity = l_orderUnit.getQuantity() * -1D;
                    l_positionManager.updateLockedQuantity(
                        l_orderUnit.getAccountId(),
                        l_orderUnit.getSubAccountId(),
                        l_orderUnit.getOrderUnitId(),
                        l_orderUnit.getProduct().getProductId(),
                        l_dblAdjustQuantity);
                }
                
                // �Q�|�W�j�@@�J�z�����ō쐬���ꂽ�X�L�b�v�����̒����P�ʃf�[�^���A
                // �@@�@@�@@�@@�@@�y�����P�ʃe�[�u���z���delete����B
                // �@@�@@�@@�@@�@@�폜�L�[�ɂ́A�P�j�Ŏ擾���������P��.����ID ���w�肷��B
                l_queryProcessor.doDeleteQuery(
                    l_oldOrderUnitRow.getPrimaryKey());
                    
                // �Q�|�X�j�@@�J�z�����ō쐬���ꂽ�X�L�b�v�����̒����f�[�^���A
                // �@@�@@�@@�@@�@@�y�����e�[�u���i�w�b�_�j�z���delete����B
                // �@@�@@�@@�@@�@@�폜�L�[�ɂ́A�P�j�Ŏ擾���������P��.����ID ���w�肷��B
                Object[] l_objOrderWhere =  {new Long(l_oldOrderUnitRow.getOrderId())};
                l_queryProcessor.doDeleteAllQuery(EqtypeOrderRow.TYPE,"order_id = ?",l_objOrderWhere);
                
                // �Q�|�P�O�j�@@�]�͍X�V����
                // �@@�@@�ڋq�P�ʁi����ID���u���C�N�����ꍇ�A�܂��͍Ō�̒����P�ʂ̏������j�ɁA
                // �@@�@@.�]�͍Čv�Z�T�[�r�X�̃C���X�^���X�𐶐����A�]�͍Čv�Z(�⏕����)���R�[������B
                // 
                // �@@�@@�����̕⏕�����́A
                // �@@�@@����ID�̌ڋq�I�u�W�F�N�g.getSubAccount(SubAccountTypeEnum.�����������)
                // �@@�@@�ɂĎ擾����B
                WEB3GentradeMainAccount l_gentradeMainAccount = 
                    new WEB3GentradeMainAccount(l_oldOrderUnitRow.getAccountId());
                WEB3GentradeSubAccount l_gentradeSubAccount = 
                    (WEB3GentradeSubAccount)
                    l_gentradeMainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                    (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower(l_gentradeSubAccount);
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_nfe.getMessage());
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        catch (WEB3BaseException l_wbe)
        {
            if (l_wbe.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
            {
                log.error(
                    "�������b�N�Ɏ��s���܂����B�F " +
                    "�،���ЃR�[�h=[" + l_mainAccount.getInstitution().getInstitutionCode() + "], " +
                    "���X�R�[�h=[" + l_mainAccount.getBranch().getBranchCode() + "], " +
                    "�ڋq�R�[�h=[" + l_mainAccount.getAccountCode() + "]");
            }
            throw l_wbe;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (call�����J�z)<BR>
     * �����J�z�X�L�b�v�����̓o�^�������A�����J�z���ʂɔ��f����B<BR>
     * �i���ɓ��Y�،���Ђɂ��Ē����J�z�������I�����Ă���ꍇ�A<BR>
     * �@@�X�L�b�v�����o�^�����ɂ���ĕK�v�ƂȂ��������J�z�������s���j<BR>
     * <BR>
     * �V�[�P���X�}�u�i�X�L�b�v�����ʒm�jprocess�vcall�����J�z( )�����Q�ƁB<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ɍ��v����A�X�L�b�v�o�^���������̒����f�[�^�̂����A<BR>
     * �@@�@@�@@�����J�z�ΏۂƂȂ�u�o����܂Œ����v�̒����P�ʃI�u�W�F�N�g�̌����擾����B<BR>
     * �@@�@@�@@���N�G���v���Z�b�T���g�p����B<BR>
     * <BR>
     * �@@�@@�@@�����o������<BR>
     * �@@�@@�@@���XID�������J�z�X�L�b�v�����ʒm�L���[Params.�،���ЃR�[�h �ɊY������<BR>
     * �@@�@@�@@�@@�@@�،���ЃI�u�W�F�N�g.getBranches( )�Ŏ擾�����S���X�I�u�W�F�N�g�̕��XID�̂����ꂩ<BR>
     * �@@�@@�@@���@@����ID�������J�z�X�L�b�v�����ʒm�L���[Params.�����R�[�h �ɊY������<BR>
     * �@@�@@�@@�@@�@@�����I�u�W�F�N�g�̖���ID<BR>
     * �@@�@@�@@���@@�s��ID�������J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h �ɊY������<BR>
     * �@@�@@�@@�@@�@@�s��I�u�W�F�N�g�̎s��ID�̂����ꂩ<BR>
     * �@@�@@�@@���@@�����L����ԁiOrderOpenStatus�j��CLOSED<BR>
     * �@@�@@�@@���@@�����̎�����ԁiOrderExpirationStatus�j��EXPIRED<BR>
     * �@@�@@�@@���@@�����G���[���R�R�[�h�������J�z�X�L�b�v�����G���[<BR>
     * �@@�@@�@@������ID�����w��B<BR>
     * <BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�����R�[�h��ALL�i�S�����j�̏ꍇ�́A<BR>
     * �@@�@@�@@���o�����ɖ����R�[�h�͎w�肵�Ȃ��B<BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��F�i�S�s��j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���o�����Ɏs��R�[�h�͎w�肵�Ȃ��B<BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��1�i�����j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���o�����Ɏs��R�[�h�i�����j�B<BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��2�i���y��NNM�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���o�����Ɏs��R�[�h�i��さNNM�j�B<BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��3�i���É��j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���o�����Ɏs��R�[�h�i���É��j�B<BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��6�iJASDAQ�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���o�����Ɏs��R�[�h�iJASDAQ�j�B<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾���������P�ʃI�u�W�F�N�g�����A<BR>
     * �@@�@@�@@�����J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�XImpl.is�J�z�����P��( )���R�[������B<BR>
     * �@@�@@�@@�߂�l��false�̏ꍇ�́A���Y�����P�ʃI�u�W�F�N�g��<BR>
     * �@@�@@�@@�P�j�Ŏ擾�����J�z�Ώۂ̒����P�ʃI�u�W�F�N�g�̌�₩��O���B<BR>
     * <BR>
     * �R�j�@@�Q�j�ŌJ�z�ΏۂƂȂ��������P�ʃI�u�W�F�N�g����(*1)�ALoop�ňȉ��̏������s���B<BR>
     * <BR>
     * �R�|�P�j�@@�ڋq�P�ʁiLoop�̈ꌏ�ځA�܂��͌���ID���u���C�N�j�Ō��������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�@@�@@�@@�@@�������́A�����P��.����ID�ɊY������ڋq�I�u�W�F�N�g���ҏW����B<BR>
     * <BR>
     * �R�|�Q�j�@@�J�z�������쐬����B<BR>
     * <BR>
     * �@@�@@�@@�����J�z�ꌏ�T�[�r�XImpl.insert�J�z����( �Q�j�ŌJ�z�ΏۂƂȂ��������P�ʃI�u�W�F�N�g(*1))��<BR>
     * �@@�@@�@@�R�[�����A<BR>
     * �@@�@@�@@�J�z�������쐬����B<BR>
     * <BR>
     * �@@�@@�@@(*1)�����P�ʃI�u�W�F�N�g��lock����( )�̑O�Ɏ擾���Ă���̂ŁA<BR>
     * �@@�@@�@@�@@�@@�������ɂ��X�V����Ă���\�������邽�߁A�g�����������}�l�[�W��.getOrderUnit( )��<BR>
     * �@@�@@�@@�@@�@@�Ď擾���Ă���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�J�z�����쐬�ɂ��DB�̓o�^�^�X�V���e�́A�����J�z�T�[�r�X��<BR>
     * �@@�@@�@@�u�����J�z_�����i�w�b�_�j�e�[�u���d�l.xls�v<BR>
     * �@@�@@�@@�u�����J�z_�����P�ʃe�[�u���d�l.xls�v<BR>
     * �@@�@@�@@�u�����J�z_���������e�[�u���d�l.xls�v<BR>
     * �@@�@@�@@�u�M�p�����J�z_�����i�w�b�_�j�e�[�u���d�l.xls�v<BR>
     * �@@�@@�@@�u�M�p�����J�z_�����P�ʃe�[�u���d�l.xls�v<BR>
     * �@@�@@�@@�u�M�p�����J�z_���������e�[�u���d�l.xls�v���Q�ƁB<BR>
     * <BR>
     * �R�|�R�j�@@�J�z��������update����B<BR>
     * <BR>
     * �@@�@@�@@�����J�z�ꌏ�T�[�r�XImpl.update�J�z������()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�����͈ȉ��̒ʂ�ݒ肷��B<BR>
     * �@@�@@�@@�����P��  ���@@�����P��(�J�z��)<BR>
     * �@@�@@�@@�G���[���R�R�[�h  ���@@�h0000:����h<BR>
     * <BR>
     * �R�|�S�j�@@�]�͍X�V����<BR>
     * �@@�@@�ڋq�P�ʁi����ID���u���C�N�����ꍇ�A�܂��͍Ō�̒����P�ʂ̏������j�ɁA<BR>
     * �@@�@@.�]�͍Čv�Z�T�[�r�X�̃C���X�^���X�𐶐����A�]�͍Čv�Z(�⏕����)���R�[������B<BR>
     * <BR>
     * �@@�@@�����̕⏕�����́A<BR>
     * �@@�@@����ID�̌ڋq�I�u�W�F�N�g.getSubAccount(SubAccountTypeEnum.�����������)<BR>
     * �@@�@@�ɂĎ擾����B<BR>
     * �����P��.getSubAccountId()�ɊY������⏕�����I�u�W�F�N�g<BR>
     * @@param l_hostEquityCarryoverSkipParams <BR>
     * - �����J�z�X�L�b�v�����ʒm�L���[Params<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4137CF9900E4
     */
    public void callOrderCarryOver(HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "callOrderCarryOver(HostEquityCarryoverSkipParams)";
        log.entering(STR_METHOD_NAME);
        if (l_hostEquityCarryoverSkipParams == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        // �P�j�@@�ȉ��̏����ɍ��v����A�X�L�b�v�o�^���������̒����f�[�^�̂����A
        // �@@�@@�@@�����J�z�ΏۂƂȂ�u�o����܂Œ����v�̒����P�ʃI�u�W�F�N�g�̌����擾����B
        //get�،���ЃR�[�h
        String l_strInstitutionCode =
            l_hostEquityCarryoverSkipParams.getInstitutionCode();
        //get�����R�[�h
        String l_strProductCode =
            l_hostEquityCarryoverSkipParams.getProductCode();
        if (ALL_PRODUCT.equals(l_strProductCode))
        {
            l_strProductCode = null;
        }
        //get�X�L�b�v�s��R�[�h
        String l_strSkipMarketCode =
            l_hostEquityCarryoverSkipParams.getSkipMarketCode();
        //get�s��R�[�h
        String[] l_strMarketCodes = new String[2];
        String l_strMarketCode = null;
        if (WEB3SkipMarketCodeDef.TOKYO.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.TOKYO;
        }
        else if (WEB3SkipMarketCodeDef.OSAKA_AND_NNM.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes[0] = WEB3MarketCodeDef.OSAKA;
            l_strMarketCodes[1] = WEB3MarketCodeDef.NNM;
        }
        else if (WEB3SkipMarketCodeDef.NAGOYA.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.NAGOYA;
        }
        else if (WEB3SkipMarketCodeDef.JASDAQ.equals(l_strSkipMarketCode))
        {
            l_strMarketCodes = null;
            l_strMarketCode = WEB3MarketCodeDef.JASDAQ;
        }
        else
        {
            l_strMarketCodes = null;
            l_strMarketCode = null;
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityProductManager l_eqProductManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        Branch[] l_branchs = null;
        Institution l_institution = null;
        Long l_productId = null;
        Long[] l_marketIds = new Long[2];
        Long l_marketId = null;
        try
        {
            //get�،���ЃI�u�W�F�N�g
            l_institution =
                l_accountManager.getInstitution(l_strInstitutionCode);
            //get�S���X�I�u�W�F�N�g
            l_branchs = l_institution.getBranches();
            //get����ID
            if (l_strProductCode != null)
            {
                l_productId =
                    new Long(
                        l_eqProductManager.getProduct(l_institution, l_strProductCode).getProductId());
            }
            else
            {
                l_productId = null;
            }
            //get�s��ID
            if (l_strMarketCodes != null)
            {
                l_marketIds[0] =
                    new Long(
                        l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCodes[0]).getMarketId());
                l_marketIds[1] =
                    new Long(
                        l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCodes[1]).getMarketId());
            }
            else if (l_strMarketCode != null)
            {
			    l_marketIds = null;
				l_marketId = 
				    new Long(
				        l_finApp.getFinObjectManager().getMarket(l_institution, l_strMarketCode).getMarketId());
            }
            else
            {
                l_marketIds = null;
            }

        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

        }
        
        // �@@�@@�@@�����o������
        // �@@�@@�@@���XID�������J�z�X�L�b�v�����ʒm�L���[Params.�،���ЃR�[�h �ɊY������
        // �@@�@@�@@�@@�@@�،���ЃI�u�W�F�N�g.getBranches( )�Ŏ擾�����S���X�I�u�W�F�N�g�̕��XID�̂����ꂩ
        // �@@�@@�@@���@@����ID�������J�z�X�L�b�v�����ʒm�L���[Params.�����R�[�h �ɊY������
        // �@@�@@�@@�@@�@@�����I�u�W�F�N�g�̖���ID
        // �@@�@@�@@���@@�s��ID�������J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h �ɊY������
        // �@@�@@�@@�@@�@@�s��I�u�W�F�N�g�̎s��ID�̂����ꂩ
        // �@@�@@�@@���@@�����L����ԁiOrderOpenStatus�j��CLOSED
        // �@@�@@�@@���@@�����̎�����ԁiOrderExpirationStatus�j��EXPIRED
        // �@@�@@�@@���@@�����G���[���R�R�[�h�������J�z�X�L�b�v�����G���[
        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objWhere = null;
        ArrayList l_lstObjWhere = new ArrayList();
        //�S���X�I�u�W�F�N�g�̕��XID
        int l_intSize = l_branchs.length;
        l_sbWhere.append(" (branch_id = ? ");
        l_lstObjWhere.add(new Long(l_branchs[0].getBranchId()));
        for (int i = 1; i < l_intSize; i++)
        {
            l_sbWhere.append(" or branch_id = ? ");
            l_lstObjWhere.add(new Long(l_branchs[i].getBranchId()));
        }
        l_sbWhere.append(") ");
        //�����J�z�X�L�b�v�����ʒm�L���[Params.�����R�[�h��ALL�i�S�����j�̏ꍇ�́A
        //���o�����ɖ����R�[�h�͎w�肵�Ȃ��B
        if (l_productId != null)
        {
            l_sbWhere.append(" and product_id = ? ");
            l_lstObjWhere.add(l_productId);
        }
        //�����J�z�X�L�b�v�����ʒm�L���[Params.�X�L�b�v�s��R�[�h��F�i�S�s��j�̏ꍇ��
        //���o�����Ɏs��R�[�h�͎w�肵�Ȃ��B
        if (l_marketIds != null)
        {
            l_sbWhere.append(" and (market_id = ? or market_id = ?) ");
            l_lstObjWhere.add(l_marketIds[0]);
            l_lstObjWhere.add(l_marketIds[1]);
        }
        else if (l_marketId != null)
        {
			l_sbWhere.append(" and market_id = ? ");
			l_lstObjWhere.add(l_marketId);
        }
        
        //�����L����ԁiOrderOpenStatus�j��CLOSED
        l_sbWhere.append(" and order_open_status = ? ");
        l_lstObjWhere.add(OrderOpenStatusEnum.CLOSED);
        //�����̎�����ԁiOrderExpirationStatus�j��EXPIRED
        l_sbWhere.append(" and expiration_status = ? ");
        l_lstObjWhere.add(OrderExpirationStatusEnum.EXPIRED);
        //�����G���[���R�R�[�h���X�L�b�v�����G���[
        l_sbWhere.append(" and error_reason_code = ? ");
        l_lstObjWhere.add(WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR);

        l_intSize = l_lstObjWhere.size();
        l_objWhere = new Object[l_intSize];
        l_lstObjWhere.toArray(l_objWhere);

        List l_lisRecords;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);

        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        //�����`�F�b�N
        int l_intSize1 = 0;
        if(l_lisRecords != null)
        {
            l_intSize1 = l_lisRecords.size();
        }
        
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            //�擾�����X�L�b�v�����̒����P�ʃI�u�W�F�N�g�����A�ȉ��̏������J��Ԃ��B
            OrderUnit l_orderUnit = null;
            EqtypeOrderUnitRow l_oldOrderUnitRow = null;
            long l_lngAccountId = 0L;
            for (int i = 0; i < l_intSize1; i++)
            { 
                l_oldOrderUnitRow =
                    (EqtypeOrderUnitRow) l_lisRecords.get(i);

                // �����P�ʎ擾
                OrderManager l_orderManager =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                l_orderUnit =
                    l_orderManager.getOrderUnit(
                        l_oldOrderUnitRow.getOrderUnitId());

                //�����J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�X���擾����
                WEB3EquityOrderCarryOverSkipObjectCheckService l_objectCheckService =
                    (WEB3EquityOrderCarryOverSkipObjectCheckService)Services.getService(
                            WEB3EquityOrderCarryOverSkipObjectCheckService.class);

                // �Q�j�@@�P�j�Ŏ擾���������P�ʃI�u�W�F�N�g�����A
                // �@@�@@�@@�����J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�XImpl.is�J�z�����P��( )���R�[������B
                // �@@�@@�@@�߂�l��false�̏ꍇ�́A���Y�����P�ʃI�u�W�F�N�g��
                // �@@�@@�@@�P�j�Ŏ擾�����J�z�Ώۂ̒����P�ʃI�u�W�F�N�g�̌�₩��O���B
                if (l_objectCheckService.isCarryOverOrderUnit(l_orderUnit))
                {
                    // �R�j�@@�Q�j�ŌJ�z�ΏۂƂȂ��������P�ʃI�u�W�F�N�g����(*1)�ALoop�ňȉ��̏������s���B
                    // �R�|�P�j�@@�ڋq�P�ʁiLoop�̈ꌏ�ځA�܂��͌���ID���u���C�N�j�Ō��������b�N����B
                    if (l_lngAccountId != l_oldOrderUnitRow.getAccountId())
                    {
                        l_lngAccountId = l_oldOrderUnitRow.getAccountId();
                        l_mainAccount =
                            (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
                        l_accountManager.lockAccount(
                            l_mainAccount.getInstitution().getInstitutionCode(),
                            l_mainAccount.getBranch().getBranchCode(),
                            l_mainAccount.getAccountCode());
                    }
                    // �R�|�Q�j�@@�J�z�������쐬����B
                    l_orderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
                    WEB3EquityOrderCarryOverUnitService l_carryOverPartService =
                        (WEB3EquityOrderCarryOverUnitService)Services.getService(
                        WEB3EquityOrderCarryOverUnitService.class);
                    boolean l_blnOk = l_carryOverPartService.insertCarryOverOrder(l_orderUnit);

                    // �R�|�R�j�@@�J�z��������update����B
                    if (l_blnOk)
                    {
                        l_carryOverPartService.updateOriginalOrder(
                            (EqTypeOrderUnit)l_orderUnit, WEB3ErrorReasonCodeDef.NORMAL);
                    }
                    
                    // �R�|�S�j�@@�]�͍X�V����
                    WEB3GentradeMainAccount l_gentradeMainAccount = 
                        new WEB3GentradeMainAccount(l_oldOrderUnitRow.getAccountId());
                    WEB3GentradeSubAccount l_gentradeSubAccount = 
                        (WEB3GentradeSubAccount)
                        l_gentradeMainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                        (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
                    l_tpTradingPowerReCalcService.reCalcTradingPower(l_gentradeSubAccount);
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage());
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        catch (WEB3BaseException l_wbe)
        {
            if (l_wbe.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
            {
                log.error(
                    "�������b�N�Ɏ��s���܂����B�F " +
                    "�،���ЃR�[�h=[" + l_mainAccount.getInstitution().getInstitutionCode() + "], " +
                    "���X�R�[�h=[" + l_mainAccount.getBranch().getBranchCode() + "], " +
                    "�ڋq�R�[�h=[" + l_mainAccount.getAccountCode() + "]");
            }
            throw l_wbe;
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * (update�X�L�b�v�����e�[�u��)<BR>
     * �����̊��������J�z�X�L�b�v�����ʒm�L���[�̓��e�ɏ]���A<BR>
     * �y�����J�z�X�L�b�v�����e�[�u���z���X�V����B<BR>
     * <BR>
     * �X�V�d�l�́A�X�L�b�v�o�^�敪�̒l�i�o�^�^�o�^�̖����j�ɂ��A<BR>
     * �ȉ��̂Q�p�^�[���ɕ������B<BR>
     * <BR>
     * �y�����J�z�X�L�b�v�����e�[�u���z�̓o�^�^�X�V���e�́A<BR>
     * �u�����J�z�X�L�b�v�����ʒm_�����J�z�X�L�b�v�����e�[�u��.xls�v���Q�ƁB<BR>
     * <BR>
     * ====================================================================<BR>
	     * ���X�L�b�v�o�^�敪���o�^�@@�̏ꍇ��<BR>
	     * <BR>
     * �E�ʒm�L���[Params.�X�L�b�v�s��R�[�h�̒l�ɂ��A<BR>
     * �@@�ȉ��̂P�j�܂��͂Q�j�̂ǂ��炩�̏������s���B<BR>
     * <BR>
     * �P�j�@@�ʒm�L���[Params.�X�L�b�v�s��R�[�h��2�i���y��NNM�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�ʒm�L���[Params�̓��e���A<BR>
     * �@@�@@�y�����J�z�X�L�b�v�����e�[�u���z�ɁA(*1)�̊Y������s��R�[�h�����A<BR>
     * �@@�@@���R�[�h��o�^����B<BR>
     * �@@�@@��L�[���d�����郌�R�[�h�����ɑ��݂���ꍇ�́A���Y���R�[�h���X�V����B<BR>
     * <BR>
     * �Q�j�@@�ʒm�L���[Params.�X�L�b�v�s��R�[�h����L�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�ʒm�L���[Params�̓��e���A<BR>
     * �@@�@@�y�����J�z�X�L�b�v�����e�[�u���z�ɂP���R�[�h��o�^����B<BR>
     * �@@�@@��L�[���d�����郌�R�[�h�����ɑ��݂���ꍇ�́A���Y���R�[�h���X�V����B<BR>
     * <BR>
     * <BR>
     * ====================================================================<BR>
     * ���X�L�b�v�o�^�敪�������@@�̏ꍇ��<BR>
     * <BR>
     * �E�ʒm�L���[Params.�X�L�b�v�s��R�[�h�̒l�ɂ��A<BR>
     * �@@�ȉ��̂P�j�܂��͂Q�j�̂ǂ��炩�̏������s���B<BR>
     * <BR>
     * �E�X�V���ɊY���f�[�^�����݂��Ȃ��ꍇ�́A��O��throw����B<BR>
     * -�Y���f�[�^�Ȃ�-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80005<BR>
     * <BR>
     * �P�j�@@�ʒm�L���[Params.�X�L�b�v�s��R�[�h��2�i���y��NNM�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�y�����J�z�X�L�b�v�����e�[�u���z�̈ȉ��̏����ɊY�����郌�R�[�h�̓o�^�敪���A<BR>
     * �@@�@@�u�����J�z�X�L�b�v����i�o�^�����j�v�ɍX�V����B<BR>
     * <BR>
     * �@@�@@���X�V������<BR>
     * �@@�@@�،���ЃR�[�h���ʒm�L���[Params.�،���ЃR�[�h<BR>
     * �@@�@@���@@�����R�[�h���ʒm�L���[Params.�����R�[�h<BR>
     * �@@�@@���@@�s��R�[�h��(*1)�̊Y������s��R�[�h��S�Ďw��<BR>
     * <BR>
     * �Q�j�@@�ʒm�L���[Params.�X�L�b�v�s��R�[�h����L�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�y�����J�z�X�L�b�v�����e�[�u���z�̈ȉ��̏����ɊY�����郌�R�[�h�̓o�^�敪���A<BR>
     * �@@�@@�u�����J�z�X�L�b�v����i�o�^�����j�v�ɍX�V����B<BR>
     * <BR>
     * �@@�@@���X�V������<BR>
     * �@@�@@�،���ЃR�[�h���ʒm�L���[Params.�،���ЃR�[�h<BR>
     * �@@�@@���@@�����R�[�h���ʒm�L���[Params.�����R�[�h<BR>
     * �@@�@@���@@�s��R�[�h���ʒm�L���[Params.�X�L�b�v�s��R�[�h<BR>
     * <BR>
     * -------------------------------------------------------------<BR>
     * (*1)<BR>
     * �Y������WEB3MarketCodeDef�Œ�`����Ă���R�[�h���w�肷��B<BR>
     * �E�ʒm�L���[Params.�X�L�b�v�s��R�[�h��2�i���y��NNM�j�̏ꍇ�́A<BR>
     * �@@���A�y��NNM�̗������w�肷��B<BR>
     * �E��L�ȊO�̏ꍇ�́A�ʒm�L���[Params.�X�L�b�v�s��R�[�h�̒l�����̂܂܎w�肷��B<BR>
     * <BR>
     * @@param l_hostEquityCarryoverSkipParams - �����J�z�X�L�b�v�����ʒm�L���[Params<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40639A0800E3
     */
    public void updateSkipProdTable(HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateSkipProdTable(HostEquityCarryoverSkipParams)";
        log.entering(STR_METHOD_NAME);
        if (l_hostEquityCarryoverSkipParams == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        //�����J�z�X�L�b�v�����L���[�e�[�u��.�،���ЃR�[�h
        String l_strInstitutionCode =
            l_hostEquityCarryoverSkipParams.getInstitutionCode();

        //�����J�z�X�L�b�v�����L���[�e�[�u��.�����R�[�h
        String l_strProductCode =
            l_hostEquityCarryoverSkipParams.getProductCode();

        //�����J�z�X�L�b�v�����L���[�e�[�u��.�X�L�b�v�s��R�[�h�ɑ�������s��R�[�h
        ArrayList l_lstMarketCodes = new ArrayList();
        String l_strSkipMarketCode =
            l_hostEquityCarryoverSkipParams.getSkipMarketCode();
        // �P�j�@@�ʒm�L���[Params.�X�L�b�v�s��R�[�h��2�i���y��NNM�j�̏ꍇ
        if (WEB3SkipMarketCodeDef.OSAKA_AND_NNM.equals(l_strSkipMarketCode))
        {
            l_lstMarketCodes.add(WEB3MarketCodeDef.OSAKA);
            l_lstMarketCodes.add(WEB3MarketCodeDef.NNM);
        }
        // �Q�j�@@�ʒm�L���[Params.�X�L�b�v�s��R�[�h����L�ȊO�̏ꍇ
        else if (WEB3SkipMarketCodeDef.TOKYO.equals(l_strSkipMarketCode))
        {
            l_lstMarketCodes.add(WEB3MarketCodeDef.TOKYO);
        }
        else if (WEB3SkipMarketCodeDef.JASDAQ.equals(l_strSkipMarketCode))
        {
            l_lstMarketCodes.add(WEB3MarketCodeDef.JASDAQ);
        }
        else
        {
            l_lstMarketCodes.add(l_strSkipMarketCode);
        }

        int l_intSize = l_lstMarketCodes.size();
        OrderCarryoverSkipProdParams l_orderCarryoverSkipProdParams = null;
        String l_strSkipRegistType =
            l_hostEquityCarryoverSkipParams.getSkipRegistType();
        // ���X�L�b�v�o�^�敪���o�^�@@�̏ꍇ��
        if (WEB3SkipRegistTypeDef.REGISTRATION.equals(l_strSkipRegistType))
        {
            // �@@�@@�ʒm�L���[Params�̓��e���A
            // �@@�@@�y�����J�z�X�L�b�v�����e�[�u���z�ɁA(*1)�̊Y������s��R�[�h�����A
            // �@@�@@���R�[�h��o�^����B
            for (int i = 0; i < l_intSize; i++)
            {
                l_orderCarryoverSkipProdParams = null;
                try
                {                
                    l_orderCarryoverSkipProdParams = 
                        (OrderCarryoverSkipProdParams)
                        OrderCarryoverSkipProdDao.findRowByPk(
                            l_strInstitutionCode,
                            l_strProductCode,
                            (String)l_lstMarketCodes.get(i));
                }
                catch (DataFindException l_ex)
                {
                }
                catch (DataQueryException l_ex)
                {
                    log.error(STR_METHOD_NAME,l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_e)
                {
                    log.error(STR_METHOD_NAME,l_e);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_e.getMessage(),
                        l_e);
                }

                try
                {
                    QueryProcessor l_QueryProcessor =
                        Processors.getDefaultProcessor();
                    Timestamp l_tsCurTime =
                        GtlUtils.getTradingSystem().getSystemTimestamp();
                    if (l_orderCarryoverSkipProdParams == null)
                    {
                        log.debug("�X�L�b�v�o�^�敪���o�^���A��L�[���d�����郌�R�[�h�����݂��Ȃ��ꍇ");
                        l_orderCarryoverSkipProdParams =
                            new OrderCarryoverSkipProdParams();
                        //set�،���ЃR�[�h
                        l_orderCarryoverSkipProdParams.setInstitutionCode(
                            l_strInstitutionCode);
                        //set�����R�[�h
                        l_orderCarryoverSkipProdParams.setProductCode(
                            l_strProductCode);
                        //set�s��R�[�h
                        l_orderCarryoverSkipProdParams.setMarketCode(
                            (String)l_lstMarketCodes.get(i));
                        //set�o�^�敪(1�F�����J�z�X�L�b�v)
                        l_orderCarryoverSkipProdParams.setRegistDivision(
                            WEB3RegistDivisionDef.ORDER_TRANSFER_STOP);
                        //set�쐬���t
                        l_orderCarryoverSkipProdParams.setCreatedTimestamp(l_tsCurTime);
                        //set�X�V���t
                        l_orderCarryoverSkipProdParams.setLastUpdatedTimestamp(l_tsCurTime);
                        //���R�[�h��o�^����
                        l_QueryProcessor.doInsertQuery(
                            l_orderCarryoverSkipProdParams);
                    }
                    else
                    {
                        log.debug("�X�L�b�v�o�^�敪���o�^���A��L�[���d�����郌�R�[�h�����݂���ꍇ");

						OrderCarryoverSkipProdParams 
						    l_cloneOrderCarryoverSkipProdParams = 
						        new OrderCarryoverSkipProdParams();

						GtlUtils.copyRow2Params(
						    l_orderCarryoverSkipProdParams,
							l_cloneOrderCarryoverSkipProdParams);

                        //set�o�^�敪(1�F�����J�z�X�L�b�v)
						l_cloneOrderCarryoverSkipProdParams.setRegistDivision(
                            WEB3RegistDivisionDef.ORDER_TRANSFER_STOP);
                        //set�X�V���t
						l_cloneOrderCarryoverSkipProdParams.setLastUpdatedTimestamp(l_tsCurTime);
                        //���R�[�h���X�V����
                        l_QueryProcessor.doUpdateQuery(
						    l_cloneOrderCarryoverSkipProdParams);
                    }
                }
                catch (DataQueryException l_e)
                {
                    log.error(STR_METHOD_NAME,l_e);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_e.getMessage(),
                        l_e);
                }
                catch (DataNetworkException l_e)
                {
                    log.error(STR_METHOD_NAME,l_e);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_e.getMessage(),
                        l_e);
                }

            }
        }
        // ���X�L�b�v�o�^�敪�������@@�̏ꍇ��
        else if (WEB3SkipRegistTypeDef.DELETE.equals(l_strSkipRegistType))
        {
            for (int i = 0; i < l_intSize; i++)
            {
                try
                {
                    l_orderCarryoverSkipProdParams =
                        (OrderCarryoverSkipProdParams)OrderCarryoverSkipProdDao.findRowByPk(
                            l_strInstitutionCode,
                            l_strProductCode,
                            (String)l_lstMarketCodes.get(i));

                    QueryProcessor l_QueryProcessor =
                        Processors.getDefaultProcessor();
                    
					OrderCarryoverSkipProdParams 
					    l_cloneOrderCarryoverSkipProdParams = 
					        new OrderCarryoverSkipProdParams();
					
					GtlUtils.copyRow2Params(
					    l_orderCarryoverSkipProdParams,
					    l_cloneOrderCarryoverSkipProdParams);

                    //set�o�^�敪(0�F�����J�z�X�L�b�v����i�o�^�����j)
					l_cloneOrderCarryoverSkipProdParams.setRegistDivision(
                        WEB3RegistDivisionDef.ORDER_TRANSFER_ACTIVE);
                    //set�X�V���t
					l_cloneOrderCarryoverSkipProdParams.setLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    //���R�[�h���X�V����
                    l_QueryProcessor.doUpdateQuery(
					    l_cloneOrderCarryoverSkipProdParams);
                }
                catch (DataFindException l_dfe)
                {
                    log.error(STR_METHOD_NAME,l_dfe);
                    // �E�X�V���ɊY���f�[�^�����݂��Ȃ��ꍇ�́A��O��throw����B
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_dfe.getMessage(),
                        l_dfe);
                }
                catch (DataNetworkException l_dne)
                {
                    log.error(STR_METHOD_NAME,l_dne);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_dne.getMessage(),
                        l_dne);
                }
                catch (DataQueryException l_dqe)
                {
                    log.error(STR_METHOD_NAME,l_dqe);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_dqe.getMessage(),
                        l_dqe);
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
