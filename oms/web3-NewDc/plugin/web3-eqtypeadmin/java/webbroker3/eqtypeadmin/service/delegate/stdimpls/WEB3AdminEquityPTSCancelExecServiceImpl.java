head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSCancelExecServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����iPTS�j�o������T�[�r�XImpl(WEB3AdminEquityPTSCancelExecServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 ���n(���u) �V�K�쐬 ���f��178
Revision History : 2008/01/29 ���n(���u) �d�l�ύX ���f��No.189,192,193,DB�ύX�d�l020
Revision History : 2008/02/18 ��іQ(���u) �d�l�ύX ���f��No.197
Revesion History : 2008/04/02 �k�v�u(���u) �d�l�ύX ���f��No.204
Revesion History : 2008/04/03 �k�v�u(���u) �d�l�ύX ���f��No.205
Revesion History : 2008/04/09 �k�v�u(���u) �d�l�ύX ���f��No.206
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSExecHistory;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputCancelExecCommonRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSOrderDetailUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSCancelExecService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�����iPTS�j�o������T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�����iPTS�j�o������T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3AdminEquityPTSCancelExecServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminEquityPTSCancelExecService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSCancelExecServiceImpl.class);

    /**
     * @@roseuid 4795B085031C
     */
    public WEB3AdminEquityPTSCancelExecServiceImpl()
    {

    }

    /**
     * �����iPTS�j�o������������s���B<BR>
     * <BR>
     * ����.���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�����iPTS�j�o��������̓��N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^:�@@����.���N�G�X�g�f�[�^<BR>
     * �@@<BR>
     * ���Ǘ��ҁE�����iPTS�j�o������m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.validate�o�����()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^:�@@����.���N�G�X�g�f�[�^<BR>
     * <BR>
     * ���Ǘ��ҁE�����iPTS�j�o������������N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.submit�o�����()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^:�@@����.���N�G�X�g�f�[�^<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4769CBBE0346
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        //�Ǘ��ҁE�����iPTS�j�o��������̓��N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminEquityPTSCancelExecInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminEquityPTSCancelExecInputRequest)l_request);
        }
        //�Ǘ��ҁE�����iPTS�j�o������m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminEquityPTSCancelExecConfirmRequest)
        {
            l_response = this.validateCancelExec((WEB3AdminEquityPTSCancelExecConfirmRequest)l_request);
        }
        //�Ǘ��ҁE�����iPTS�j�o������������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminEquityPTSCancelExecCompleteRequest)
        {
            l_response = this.submitCancelExec((WEB3AdminEquityPTSCancelExecCompleteRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * �o��������͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i�Ǘ��ҁE�����iPTS�j�o������jget���͉�ʁv �Q�ƁB <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu:get��藚��()�̖߂�l��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�f�[�^�s�����v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class:WEB3SystemLayerException        <BR>
     * �@@�@@�@@�@@�@@�@@tag:SYSTEM_ERROR_80006<BR>
     * =============================================== <BR>
     * @@param l_request - (����f�[�^)<BR>
     * �Ǘ��ҁE����(PTS)�o��������N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminEquityPTSCancelExecInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4769CBC60315
     */
    protected WEB3AdminEquityPTSCancelExecInputResponse getInputScreen(
        WEB3AdminEquityPTSCancelExecInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminEquityPTSCancelExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����
        //validate( )
        l_request.validate();

        //�����P�ʂ��擾����B
        //[����]
        //arg0�F���N�G�X�g�f�[�^.����ID
        //�擾���������I�u�W�F�N�g�̔z��̐擪���璍���P�ʂ��擾����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = null;
        EqTypeOrderUnit l_orderUnit = null;
        EqtypeOrderUnitRow l_orderUnitRow = null;
        l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //���O�C�������Ǘ��҃C���X�^���X���擾����
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ������`�F�b�N����
        //[����]
        //�Ǘ��ҁF�擾�����Ǘ��҃I�u�W�F�N�g
        //���XID�F�擾���������P��.���XID
        //validate�Ǘ��Ҍ���(�Ǘ���, long)
        this.validateManagerAuthority(l_admin, l_orderUnitRow.getBranchId());

        //�o������\�Ȓ����P�ʂ��`�F�b�N����
        //[����]
        //�����P�ʁF�@@�擾���������P��
        //validate�o������\����(�����P��)
        this.validateCancelExecEnableOrder(l_orderUnit);

        //����J�����_�R���e�L�X�g�ɒ����P�ʂ���擾�����l���Z�b�g����
        //[����]
        //�����P�ʁF�@@�擾���������P��
        //set����J�����_�R���e�L�X�g(�����P��)
        this.setTradingClendarContext(l_orderUnit);

        //�o��������\�Ȏ��ԑт��ǂ������`�F�b�N����
        // validate�o�����͏o������\���ԑ�( )
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        //�����̒����P�ʂ̒l���Ǘ��ҁE�����iPTS�j�����ڍ�Unit�Ɋi�[����
        //[����]
        //�����P�ʁF�@@�擾���������P��
        //get�����ڍ�(�����P��)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_orderUnit);


        //�����̒����P�ʂɕR�Â���藚�����擾����
        //[����]
        //�����P�ʁF�@@�擾���������P��
        //get��藚��(�����P��)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_orderUnit);

        //get��藚��()�̖߂�l��null�̏ꍇ�A�u�f�[�^�s�����v�̗�O���X���[����
        if (l_ptsExecHistories == null)
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        //���͉�ʗp�̖�藚���̕ҏW���s��
        //[����]
        //��藚���F�@@���i�Ǘ�(����)�f�[�^�}�l�[�W��.get��藚��()�Ŏ擾������藚��
        //get��藚���i���́j(�Ǘ��ҁE�����iPTS�j��藚��[])
        l_ptsExecHistories =
            this.getHistoryInput(l_ptsExecHistories);


        //���X�|���X�f�[�^�𐶐�����
        WEB3AdminEquityPTSCancelExecInputResponse l_response =
            (WEB3AdminEquityPTSCancelExecInputResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        // ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����
        //�����ڍׁF  ���i�Ǘ�(����)�f�[�^�}�l�[�W��.get�����ڍ�()�̖߂�l
        //��藚���F  this.get��藚���i���́j()�̖߂�l
        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_ptsExecHistories;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�o�����)<BR>
     * �o������m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i�Ǘ��ҁE�����iPTS�j�o������jvalidate�o������v �Q�ƁB <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu:get��藚��()�̖߂�l��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�f�[�^�s�����v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class:WEB3SystemLayerException        <BR>
     * �@@�@@�@@�@@�@@�@@tag:SYSTEM_ERROR_80006<BR>
     * =============================================== <BR>
     * @@param l_request - (����f�[�^)<BR>
     * �Ǘ��ҁE�����iPTS�j�o������m�F���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminEquityPTSCancelExecConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4769CBE202C1
     */
    protected WEB3AdminEquityPTSCancelExecConfirmResponse validateCancelExec(
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancelExec(WEB3AdminEquityPTSCancelExecConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����
        //validate( )
        l_request.validate();

        //�����P�ʂ��擾����
        //[����]
        //�����P��ID�F���N�G�X�g�f�[�^.����ID
        //�擾���������I�u�W�F�N�g�̔z��̐擪���璍���P�ʂ��擾����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = null;
        EqTypeOrderUnit l_orderUnit = null;
        EqtypeOrderUnitRow l_orderUnitRow = null;
        l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //���O�C�������Ǘ��҃C���X�^���X���擾����
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ������`�F�b�N����
        //[����]
        //�Ǘ��ҁF�擾�����Ǘ��҃I�u�W�F�N�g
        //���XID�F�擾���������P��.���XID
        //validate�Ǘ��Ҍ���(�Ǘ���, long)
        this.validateManagerAuthority(l_admin, l_orderUnitRow.getBranchId());

        //�o������\�Ȓ����P�ʂ��`�F�b�N����
        //[����]
        //�����P�ʁF�@@�擾���������P��
        //validate�o������\����(�����P��)
        this.validateCancelExecEnableOrder(l_orderUnit);

        //�o������\�Ȗ��f�[�^���擾����
        //����]
        //���N�G�X�g�f�[�^�F ����.���N�G�X�g�f�[�^
        //�����P�ʁF �擾���������P��
        //validate�o������\���(�Ǘ��ҁE�����iPTS�j�o�����͎�����ʃ��N�G�X�g, �����P��)
        OrderExecution l_orderExecution =
            this.validateCancelExecEnableExecute(l_request, l_orderUnit);

        //����J�����_�R���e�L�X�g�ɒ����P�ʂ���擾�����l���Z�b�g����
        //[����]
        //�����P�ʁF�@@�擾���������P��
        //set����J�����_�R���e�L�X�g(�����P��)
        this.setTradingClendarContext(l_orderUnit);

        //�o��������\�Ȏ��ԑт��ǂ������`�F�b�N����
        // validate�o�����͏o������\���ԑ�( )
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        //�����̒����P�ʂ̒l���Ǘ��ҁE�����iPTS�j�����ڍ�Unit�Ɋi�[����
        //[����]
        //�����P�ʁF�@@�擾���������P��
        //get�����ڍ�(�����P��)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_orderUnit);

        //�����̒����P�ʂɕR�Â���藚�����擾����
        //[����]
        //�����P�ʁF�擾���������P��
        //get��藚��(�����P��)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_orderUnit);

        //get��藚��()�̖߂�l��null�̏ꍇ�A�u�f�[�^�s�����v�̗�O���X���[����
        if (l_ptsExecHistories == null)
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        //�擾������藚���ɏo������f�[�^��ǉ����A�ԋp����
        //[����]
        //��藚���F���i�Ǘ�(����)�f�[�^�}�l�[�W��.get��藚��()�Ŏ擾������藚��
        //���Fthis.validate�o������\���()�Ŏ擾�������f�[�^
        //�Ǘ��ҁF�擾�����Ǘ���
        //get��藚���i�m�F�E�����j(�Ǘ��ҁE�����iPTS�j��藚��[], OrderExecution, �Ǘ���)(
        l_ptsExecHistories = this.getHistoryConfirmComplete(
            l_ptsExecHistories, l_orderExecution, l_admin);

        //���X�|���X�f�[�^�𐶐�����
        WEB3AdminEquityPTSCancelExecConfirmResponse l_response =
            (WEB3AdminEquityPTSCancelExecConfirmResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B��
        //�����ڍׁF  ���i�Ǘ�(����)�f�[�^�}�l�[�W��.get�����ڍ�()�̖߂�l
        //��藚���F  this.get���ڍׁi�m�F�E�����j()�̖߂�l
        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_ptsExecHistories;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�o�����)<BR>
     * �o����������������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i�Ǘ��ҁE�����iPTS�j�o������jsubmit�o������v �Q�ƁB <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu:get��藚��()�̖߂�l��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�f�[�^�s�����v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class:WEB3SystemLayerException        <BR>
     * �@@�@@�@@�@@�@@�@@tag:SYSTEM_ERROR_80006<BR>
     * =============================================== <BR>
     * @@param l_request - (����f�[�^)<BR>
     * �Ǘ��ҁE�����iPTS�j�o������������N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminEquityPTSCancelExecCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4769CEE901BB
     */
    protected WEB3AdminEquityPTSCancelExecCompleteResponse submitCancelExec(
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancelExec(WEB3AdminEquityPTSCancelExecCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����
        //validate( )
        l_request.validate();

        //�����P�ʂ��擾����
        //[����]
        //arg0�F���N�G�X�g�f�[�^.����ID
        //���擾���������I�u�W�F�N�g�̔z��̐擪���璍���P�ʂ��擾����
        //getOrderUnits(arg0 : long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = null;
        EqTypeOrderUnit l_orderUnit = null;
        EqtypeOrderUnitRow l_orderUnitRow = null;
        l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //���O�C�������Ǘ��҃C���X�^���X���擾����
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ������`�F�b�N����
        //[����]
        //�Ǘ��ҁF�擾�����Ǘ��҃I�u�W�F�N�g
        //���XID�F�擾���������P��.���XID
        this.validateManagerAuthority(l_admin, l_orderUnitRow.getBranchId());

        //�Ïؔԍ��̃`�F�b�N���s��
        //[����]
        //�p�X���[�h: ���N�G�X�g�f�[�^.�Ïؔԍ�
        //validate����p�X���[�h(�p�X���[�h : String)
        l_admin.validateTradingPassword(l_request.password);

        //�ڋq�I�u�W�F�N�g���擾����
        //[����]
        //arg0: �擾���������P��.getAccountId()
        //getMainAccount(arg0 : long)
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accountManager.getMainAccount(l_orderUnit.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        //�o������Ώے����̌��������b�N����
        //[����]
        //�،���ЃR�[�h�F�@@�ڋq.getInstitution().getInstitutionCode()
        //���X�R�[�h�F�@@�ڋq.getBranch().getBranchCode()
        //�ڋq�R�[�h�F�@@�ڋq.getAccountCode()
        //lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //�،���ЃR�[�h
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();

        //���X�R�[�h
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();

        //�ڋq�R�[�h
        String l_strAccountCode = l_mainAccount.getAccountCode();

        l_accountManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

        // getOrderUnit
        // [����]
        // �����P��ID�F�@@�擾���������P��.�����P��ID
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnitRow.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�o������\�Ȓ����P�ʂ��`�F�b�N����
        //[����]
        //�����P�ʁF�@@�擾���������P��
        //validate�o������\����(�����P��)
        this.validateCancelExecEnableOrder(l_orderUnit);

        //�o������\�Ȗ��f�[�^���擾����
        //[����]
        //���N�G�X�g�f�[�^�F ����.���N�G�X�g�f�[�^
        //�����P�ʁF �擾���������P��
        //validate�o������\���(�Ǘ��ҁE�����iPTS�j�o�����͎�����ʃ��N�G�X�g, �����P��)
        OrderExecution l_orderExecution =
            this.validateCancelExecEnableExecute(l_request, l_orderUnit);

        //����J�����_�R���e�L�X�g�ɒ����P�ʂ���擾�����l���Z�b�g����
        //[����]
        //�����P�ʁF�@@�擾���������P��
        //set����J�����_�R���e�L�X�g(�����P��)
        this.setTradingClendarContext(l_orderUnit);

        //�o��������\�Ȏ��ԑт��ǂ������`�F�b�N����
        // validate�o�����͏o������\���ԑ�( )
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        //�����̒����P�ʂ̒l���Ǘ��ҁE�����iPTS�j�����ڍ�Unit�Ɋi�[����
        //[����]
        //�����P�ʁF�@@�擾���������P��
        //get�����ڍ�(�����P��)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_orderUnit);

        //�����̒����P�ʂɕR�Â���藚�����擾����
        //[����]
        //�����P�ʁF�擾���������P��
        //get��藚��(�����P��)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_orderUnit);

        //get��藚��()�̖߂�l��null�̏ꍇ�A�u�f�[�^�s�����v�̗�O���X���[����
        if (l_ptsExecHistories == null)
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        //�擾������藚���ɏo������f�[�^��ǉ����A�ԋp����
        //[����]
        //��藚���F���i�Ǘ�(����)�f�[�^�}�l�[�W��.get��藚��()�Ŏ擾������藚��
        //���Fthis.validate�o������\���()�Ŏ擾�������f�[�^
        //�Ǘ��ҁF�擾�����Ǘ���
        //get��藚���i�m�F�E�����j(�Ǘ��ҁE�����iPTS�j��藚��[], OrderExecution, �Ǘ���)
        l_ptsExecHistories = this.getHistoryConfirmComplete(l_ptsExecHistories, l_orderExecution, l_admin);

        //�o���ʒm�L���[�e�[�u���ɏo������f�[�^��Insert����
        //[����]
        //�����P�ʁF�擾���������P��
        //���f�[�^�Fthis.validate�o������\���()�Ŏ擾�������f�[�^
        //�ڋq�F�擾�����ڋq�I�u�W�F�N�g
        //�Ǘ��ҁF�擾�����Ǘ��҃I�u�W�F�N�g
        //insert�o���ʒm(�����P��, OrderExecution, �ڋq, �Ǘ���)
        this.insertExecNotify(l_orderUnit, l_orderExecution, (WEB3GentradeMainAccount)l_mainAccount, l_admin);

        //���X�|���X�f�[�^�𐶐�����
        //createResponse( )
        WEB3AdminEquityPTSCancelExecCompleteResponse l_response =
            (WEB3AdminEquityPTSCancelExecCompleteResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����
        //�����ڍׁF�@@ ���i�Ǘ�(����)�f�[�^�}�l�[�W��.get�����ڍ�()�̖߂�l
        //��藚���F�@@ this.get��藚���i�m�F�E�����j()�̖߂�l
        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_ptsExecHistories;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�o������\����)<BR>
     * �o������������\�Ȓ������`�F�b�N����B<BR>
     * <BR>
     * �P�jPTS�s�ꂩ�ǂ����`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�P�|�P�j �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�s��I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@�s��ID:�@@�����̒����P��.�s��ID <BR>
     *  <BR>
     * �@@�@@�P�|�Q�jPTS�s��łȂ��ꍇ(�s��.isPTS�s��() == false)�A <BR>
     * �@@�@@�@@�@@�@@�@@�uPTS�s��łȂ��ꍇ�͏o������s�v�̗�O���X���[����B <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_03003<BR>
     *  <BR>
     * �Q�j���L�����ɊY�����Ȃ��ꍇ�A <BR>
     *    �u������Ԃ��o������s�v�̗�O���X���[����B <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02986<BR>
     *  <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@�@@�����P��.isUnExecuted() == false (��肠��)   ���@@ <BR>
     * �@@�@@�@@�@@����ID�̔����� == �Ɩ����t <BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 476B126A00FE
     */
    protected void validateCancelExecEnableOrder(EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancelExecEnableOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //PTS�s�ꂩ�ǂ����`�F�b�N����

        // �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[����
        //�s��I�u�W�F�N�g���擾����B
        //[����]
        //�s��ID:�@@�����̒����P��.�s��ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //PTS�s��łȂ��ꍇ(�s��.isPTS�s��() == false)�A
        //�uPTS�s��łȂ��ꍇ�͏o������s�v�̗�O���X���[����
        if (!l_market.isPTSMarket())
        {
            log.debug("PTS�s��łȂ��ꍇ�͏o������s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "PTS�s��łȂ��ꍇ�͏o������s�B");
        }

        //���L�����ɊY�����Ȃ��ꍇ
        //�u������Ԃ��o������s�v�̗�O���X���[����B
        //[����]
        //�@@�@@�@@�����P��.isUnExecuted() == false (��肠��)   ����
        //�@@�@@�@@����ID�̔����� == �Ɩ����t
        Date l_datOrderIdDate =
            WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        if (!(!l_eqTypeOrderUnit.isUnexecuted() && WEB3DateUtility.compareToDay(l_datOrderIdDate, l_datBizDate) == 0))
        {
            log.debug("������Ԃ��o������s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02986,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������Ԃ��o������s�B");
        }
    }

    /**
     * (validate�o������\���)<BR>
     * �w�肳�ꂽ�������A��萔�ʁA���P���̃f�[�^���o������\���ǂ����`�F�b�N���A<BR>
     * ����\�Ȗ��f�[�^��ԋp����B<BR>
     * <BR>
     * �P�j �����̒����P��.getExecutions( )�ŁA<BR>
     * �@@�@@�Ώے����ɕR�t�����f�[�^��S�Ď擾����B<BR>
     * <BR>
     * �@@�@@�Y��������f�[�^�����݂��Ȃ��ꍇ�A�u���Ȃ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00676<BR>
     * <BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�������f�[�^�̒�����A�ȉ��̏����ɍ��v����f�[�^��<BR>
     * �@@�@@�������A���v�������f�[�^���擾����B<BR>
     * <BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@���f�[�^.������ �� ����.���N�G�X�g�f�[�^.������<BR>
     * �@@�@@���f�[�^.��萔�� �� ����.���N�G�X�g�f�[�^.��萔��<BR>
     * �@@�@@���f�[�^.���P�� �� ����.���N�G�X�g�f�[�^.���P��<BR>
     * <BR>
     * �@@�@@�����������ɍ��v����f�[�^�����������݂���ꍇ�A<BR>
     * �@@�@@�@@��菇�ԍ�����ԏ������f�[�^�i���ł��ߋ��̖��Ƃ��Ĉ����j<BR>
     * �@@�@@�@@���擾����B<BR>
     * �@@�@@�����������ɍ��v����f�[�^�����݂��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�u���Ȃ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00676<BR>
     * <BR>
     * �R�j ����.�����P��.������� �� �����������@@�̏ꍇ�̂݁A<BR>
     * �@@�@@�ȉ��ۗ̕L���Y���ʃ`�F�b�N���s���B<BR>
     * <BR>
     * <BR>
     * �R�|�P�j�����|�W�V�����}�l�[�W��.get�ۗL���Y()���R�[�����A<BR>
     * �@@�@@�@@�@@�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@����ID�F����.�����P��.����ID<BR>
     * �@@�@@�@@�@@�⏕����ID�F����.�����P��.�⏕����ID<BR>
     * �@@�@@�@@�@@����ID�F����.�����P��.����ID<BR>
     * �@@�@@�@@�@@�ŋ敪�F����.�����P��.�ŋ敪<BR>
     * <BR>
     * �@@�@@�@@���ۗL���Y���擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�u�ۗL���Y�Y���f�[�^�Ȃ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00204<BR>
     * <BR>
     * �R�|�Q�j�ۗL���Y.���ʁ|�ۗL���Y.���b�N������ �� ����.���N�G�X�g�f�[�^.��萔��<BR>
     * �@@�@@�@@�@@�̏ꍇ�A�u�ۗL���Y�c���ʃ`�F�b�N�G���[�B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_01931<BR>
     * <BR>
     * �S�j�@@�Q�j�Ŏ擾�������f�[�^��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE����(PTS)�o�����͎�����ʃ��N�G�X�g�f�[�^
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return OrderExecution
     * @@throws WEB3BaseException
     * @@roseuid 4772368B027D
     */
    protected OrderExecution validateCancelExecEnableExecute(
        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request,
        EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancelExecEnableExecute(WEB3AdminEquityPTSInputCancelExecCommonRequest, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�����̒����P��.getExecutions( )��
        //�Ώے����ɕR�t�����f�[�^��S�Ď擾����
        //�Y��������f�[�^�����݂��Ȃ��ꍇ�A�u���Ȃ��v�̗�O���X���[����

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        OrderExecution[] l_orderExecutions = l_eqTypeOrderUnit.getExecutions();
        OrderExecution[] l_finalOrderExecutions = null;
        OrderExecution l_finalOrderExecution = null;
        List l_lisOrderExecutions = new ArrayList();
        if (l_orderExecutions == null || l_orderExecutions.length == 0)
        {
            log.debug("����Ώۂ̖��f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00676,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����Ώۂ̖��f�[�^�����݂��Ȃ��B");
        }
        //�P�j�Ŏ擾�������f�[�^�̒�����A�ȉ��̏����ɍ��v����f�[�^���������A
        //���v�������f�[�^��ԋp����B
        //[��������]
        //���f�[�^.������ �� ����.���N�G�X�g�f�[�^.������
        //���f�[�^.��萔�� �� ����.���N�G�X�g�f�[�^.��萔��
        //���f�[�^.���P�� �� ����.���N�G�X�g�f�[�^.���P��
        //�����������ɍ��v����f�[�^�����������݂���ꍇ�A
        //�@@��菇�ԍ�����ԏ������f�[�^�i���ł��ߋ��̖��Ƃ��Ĉ����j
        //�@@��ԋp����B
        //�����������ɍ��v����f�[�^�����݂��Ȃ��ꍇ�A
        //�u���Ȃ��v�̗�O���X���[����B
        else
        {
            int l_intOrderExecutionsCnt = l_orderExecutions.length;
            for (int i = 0; i < l_intOrderExecutionsCnt; i++)
            {
                if (WEB3DateUtility.compareToSecond(l_orderExecutions[i].getExecutionTimestamp(),
                    l_request.executionTimeStamp) == 0
                    && l_orderExecutions[i].getExecutionQuantity() == Double.parseDouble(l_request.execQuantity)
                    && l_orderExecutions[i].getExecutionPrice() == Double.parseDouble(l_request.execPrice))
                {
                    l_lisOrderExecutions.add(l_orderExecutions[i]);
                }
            }

            l_finalOrderExecutions = new OrderExecution[l_lisOrderExecutions.size()];
            l_lisOrderExecutions.toArray(l_finalOrderExecutions);

            if (l_finalOrderExecutions == null || l_finalOrderExecutions.length == 0)
            {
                log.debug("����Ώۂ̖��f�[�^�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00676,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����Ώۂ̖��f�[�^�����݂��Ȃ��B");
            }
            else
            {
                int l_intTemp = l_finalOrderExecutions[0].getExecutionSerialNo();
                int l_intIndex = 0;
                int l_intFinalOrderExecutionsCnt = l_finalOrderExecutions.length;
                for (int i = 1; i < l_intFinalOrderExecutionsCnt; i++)
                {
                    if (l_finalOrderExecutions[i].getExecutionSerialNo() < l_intTemp)
                    {
                        l_intTemp = l_finalOrderExecutions[i].getExecutionSerialNo();
                        l_intIndex = i;
                    }
                }

                l_finalOrderExecution = l_finalOrderExecutions[l_intIndex];
            }
        }
        //�R�j ����.�����P��.������� �� �����������@@�̏ꍇ�̂݁A
        //�ȉ��ۗ̕L���Y���ʃ`�F�b�N���s���B
        if (OrderTypeEnum.EQUITY_BUY.equals(l_eqTypeOrderUnit.getOrderType()))
        {
            //�R�|�P�j�����|�W�V�����}�l�[�W��.get�ۗL���Y()���R�[�����A
            //�@@�@@�ۗL���Y�I�u�W�F�N�g���擾����B
            //  [����]
            //�@@����ID�F����.�����P��.����ID
            //�@@�⏕����ID�F����.�����P��.�⏕����ID
            //�@@����ID�F����.�����P��.����ID
            //�@@�ŋ敪�F����.�����P��.�ŋ敪
            //���ۗL���Y���擾�ł��Ȃ��ꍇ�A
            //�u�ۗL���Y�Y���f�[�^�Ȃ��v�̗�O���X���[����B 
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

            EqTypeAsset l_eqTypeAsset =
                l_positionManager.getAsset(
                    l_eqTypeOrderUnit.getAccountId(),
                    l_eqTypeOrderUnit.getSubAccountId(),
                    l_eqTypeOrderUnit.getProduct().getProductId(),
                    l_eqTypeOrderUnit.getTaxType());

            if (l_eqTypeAsset == null)
            {
                log.debug("�ۗL���Y�Y���f�[�^�Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ۗL���Y�Y���f�[�^�Ȃ��B");
            }

            //�R�|�Q�j�ۗL���Y.���ʁ|�ۗL���Y.���b�N������ �� ����.���N�G�X�g�f�[�^.��萔��  
            //        �̏ꍇ�A�u�ۗL���Y�c���ʃ`�F�b�N�G���[�B�v�̗�O���X���[����B 
            if (Double.parseDouble(l_request.execQuantity) > (l_eqTypeAsset.getQuantity() - l_eqTypeAsset.getLockedQuantity()))
            {
                log.debug("�ۗL���Y�c���ʃ`�F�b�N�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ۗL���Y�c���ʃ`�F�b�N�G���[�B");
            }
        }
        //�S�j�@@�Q�j�Ŏ擾�������f�[�^��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_finalOrderExecution;

    }

    /**
     * (set����J�����_�R���e�L�X�g)<BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * <BR>
     * �P�j�@@���X�I�u�W�F�N�g���擾�B <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@arg0�F�@@����.�����P��.���XID �̖߂�l <BR>
     * <BR>
     * �Q�j�@@�،���ЃI�u�W�F�N�g���擾�B <BR>
     * �@@�@@�@@getBranch()�̖߂�l.getInstitution()���R�[������B <BR>
     * <BR>
     * �R�j�@@�s��I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@arg0�F�@@����.�����P��.�s��ID<BR>
     * <BR>
     * �S�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�p�����[�^.�����f�[�^�̓��e������J�����_�R���e�L�X�g�� <BR>
     * �@@�@@�@@�v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = getInstitution()�̖߂�l.getInstitutionCode() <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = getBranch()�̖߂�l.getBranchCode() <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = getMarket()�̖߂�l.getMarketCode() <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g���Z�b�g����B <BR>
     * �@@�@@�@@�ݒ�L�[�F PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �T�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|PTS������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4773375C0284
     */
    protected void setTradingClendarContext(EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingClendarContext(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //���X�I�u�W�F�N�g���擾�B
        //�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������
        //[����]
        //arg0�F�@@����.�����P��.���XID �̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_accMgr.getBranch(l_eqTypeOrderUnit.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        //�،���ЃI�u�W�F�N�g���擾�B
        //getBranch()�̖߂�l.getInstitution()���R�[������
        Institution l_institution = l_branch.getInstitution();

        //�s��I�u�W�F�N�g���擾����B
        //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������
        //[����]
        //arg0�F�@@����.�����P��.�s��ID
        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�S�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        // �|�p�����[�^.�����f�[�^�̓��e��������ԃR���e�L�X�g��
        // �v���p�e�B���Z�b�g����B
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = getInstitution()�̖߂�l.getInstitutionCode()
        l_context.setInstitutionCode(l_institution.getInstitutionCode());

        // ����J�����_�R���e�L�X�g.���X�R�[�h = getBranch()�̖߂�l.getBranchCode()
        l_context.setBranchCode(l_branch.getBranchCode());

        // ����J�����_�R���e�L�X�g.�s��R�[�h = getMarket()�̖߂�l.getMarketCode()
        l_context.setMarketCode(l_market.getMarketCode());

        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

        // ����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        // ����J�����_�R���e�L�X�g.������t���i = �h�����h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);

        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null
        l_context.setOrderAcceptTransaction(null);

        //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�
        //������ԃR���e�L�X�g���Z�b�g����B
        //�ݒ�L�[�F PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        // �T�j�@@��t�����A���t���[�����Z�b�g����B
        // �|PTS������ԊǗ�.setTimestamp()���R�[������B
        WEB3EquityPTSTradingTimeManagement.setTimestamp();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get��藚���i���́j)<BR>
     * �o������\�Ȗ�藚���̎���\�t���O��true���Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j����.��藚���̔z��̑S�v�f�ɂ��Ĉȉ��̏������s���B<BR>
     * <BR>
     * <BR>
     * �@@�@@�@@�@@��藚��.���E������敪��"4" (������łȂ�) �@@���� <BR>
     * �@@�@@�@@�@@�@@��藚��.�����敪��"1"(������)�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�@@��藚��.����\�t���O��true���Z�b�g<BR>
     * <BR>
     * <BR>
     * �Q�j��藚���̔z���ԋp����B<BR>
     * @@param l_histories - (��藚��)<BR>
     * �Ǘ��ҁE�����iPTS�j��藚���̔z��<BR>
     * @@return WEB3AdminEquityPTSExecHistory[]
     * @@roseuid 4781F97702E5
     */
    protected WEB3AdminEquityPTSExecHistory[] getHistoryInput(WEB3AdminEquityPTSExecHistory[] l_histories)
    {
        final String STR_METHOD_NAME = "getHistoryInput(WEB3AdminEquityPTSExecHistory)";
        log.entering(STR_METHOD_NAME);

        //����.��藚���̔z��̑S�v�f�ɂ��Ĉȉ��̏������s��
        //��藚��.���E������敪��"4" (������łȂ�) �@@����
        //�@@�@@��藚��.�����敪��"1"(������)�̏ꍇ�A
        //��藚��.����\�t���O��true���Z�b�g
        int l_intHistoriesCnt = l_histories.length;
        for (int i = 0; i < l_intHistoriesCnt; i++)
        {
            if (!WEB3DealedTypeDef.CANCEL.equals(l_histories[i].inputExecCancelExecDiv)
                && WEB3StatusDef.DEALT.equals(l_histories[i].inputExecCancelExecProcDiv))
            {
                l_histories[i].cancelFlag = true;
            }
        }

        //��藚���̔z���ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_histories;
    }

    /**
     * (get��藚���i�m�F�E�����j)<BR>
     * �擾������藚���ɏo������f�[�^��ǉ����A�ԋp����B<BR>
     * <BR>
     * �P�jArrayList�𐶐����A����.�Ǘ��ҁE�����iPTS�j��藚���̔z��̑S�v�f��<BR>
     *  ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �Q�j�Ǘ��ҁE�����iPTS�j��藚���C���X�^���X�𐶐����A���L���ڂ��Z�b�g����B<BR>
     * <BR>
     * �@@�@@�E����\�t���O�F  false<BR>
     * �@@�@@�E�������F�@@����.���f�[�^.������<BR>
     * �@@�@@�E��芔���F�@@����.���f�[�^.��芔��<BR>
     * �@@�@@�E���P���F�@@����.���f�[�^.���P��<BR>
     * �@@�@@�E���E������敪�F�@@ "4"(�����)<BR>
     * �@@�@@�E�X�V�҃R�[�h�F�@@ ����.�Ǘ���.get�Ǘ��҃R�[�h()<BR>
     * �@@�@@�E�����敪�F�@@"0"(������)<BR>
     * <BR>
     * �R�j�@@�Q)��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�jArrayList��z��ɕϊ����A�ԋp����B<BR>
     * @@param l_histories - (��藚��)<BR>
     * �Ǘ��ҁE�����iPTS�j��藚���̔z��<BR>
     * @@param l_orderExecution - (���f�[�^)<BR>
     * ���f�[�^<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminEquityPTSExecHistory[]
     * @@roseuid 4781FD920365
     */
    protected WEB3AdminEquityPTSExecHistory[] getHistoryConfirmComplete(
        WEB3AdminEquityPTSExecHistory[] l_histories,
        OrderExecution l_orderExecution,
        WEB3Administrator l_administrator) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getHistoryConfirmComplete(WEB3AdminEquityPTSExecHistory, OrderExecution, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        if (l_orderExecution == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //ArrayList�𐶐����A����.�Ǘ��ҁE�����iPTS�j��藚���̔z��̑S�v�f��ArrayList�ɒǉ�����
        List l_lisExecHistory = new ArrayList();
        int l_intHistoriesCnt = l_histories.length;
        for (int i = 0; i < l_intHistoriesCnt; i++)
        {
            l_lisExecHistory.add(l_histories[i]);
        }

        //�Ǘ��ҁE�����iPTS�j��藚���C���X�^���X�𐶐�����
        WEB3AdminEquityPTSExecHistory l_equityPTSExecHistory =
            new WEB3AdminEquityPTSExecHistory();

        //  �E����\�t���O�F  false
        l_equityPTSExecHistory.cancelFlag = false;

        //�@@�E�������F�@@����.���f�[�^.������
        l_equityPTSExecHistory.executionTimeStamp =
            l_orderExecution.getExecutionTimestamp();

        //�@@�E��芔���F�@@����.���f�[�^.��芔��
        l_equityPTSExecHistory.execQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderExecution.getExecutionQuantity());

        //�@@�E���P���F�@@����.���f�[�^.���P��
        l_equityPTSExecHistory.execPrice =
            WEB3StringTypeUtility.formatNumber(l_orderExecution.getExecutionPrice());

        //�@@�E���E������敪�F�@@ "4"(�����)
        l_equityPTSExecHistory.inputExecCancelExecDiv = WEB3DealedTypeDef.CANCEL;

        //�@@�E�X�V�҃R�[�h�F�@@ ����.�Ǘ���.get�Ǘ��҃R�[�h()
        l_equityPTSExecHistory.updaterCode = l_administrator.getAdministratorCode();

        //�@@�E�����敪�F�@@"0"(������)
        l_equityPTSExecHistory.inputExecCancelExecProcDiv = WEB3StatusDef.NOT_DEAL;

        //�Q)��ArrayList�ɒǉ�����
        l_lisExecHistory.add(l_equityPTSExecHistory);

        //ArrayList��z��ɕϊ����A�ԋp����
        WEB3AdminEquityPTSExecHistory[] l_execHistories =
            new WEB3AdminEquityPTSExecHistory[l_lisExecHistory.size()];
        l_lisExecHistory.toArray(l_execHistories);

        log.exiting(STR_METHOD_NAME);
        return l_execHistories;
    }

    /**
     * (insert�o���ʒm)<BR>
     * �����o���ʒm�L���[�e�[�u����1���f�[�^��o�^����B<BR>
     * <BR>
     * �P�j�����o���ʒm�L���[Params�𐶐����A�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * ���ݒ�l��DB�X�V�d�l�u�o�����_�����o���ʒm�L���[�e�[�u���v<BR>
     * �Q��<BR>
     * <BR>
     * <BR>
     * �Q�jQueryProcessor.doInsertQuery()���R�[������B <BR>
     * <BR>
     * �@@�@@[����]   <BR>
     * �@@�@@arg0�F�@@�����o���ʒm�L���[Params<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_orderExecution - (���f�[�^)<BR>
     * ���f�[�^<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47832C4B01FD
     */
    protected void insertExecNotify(EqTypeOrderUnit l_eqTypeOrderUnit,
        OrderExecution l_orderExecution, WEB3GentradeMainAccount l_mainAccount,
        WEB3Administrator l_administrator) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertExecNotify(EqTypeOrderUnit, OrderExecution, WEB3GentradeMainAccount, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�����o���ʒm�L���[Params�𐶐����A�v���p�e�B���Z�b�g����
        // ���ݒ�l��DB�X�V�d�l�u�o�����_�����o���ʒm�L���[�e�[�u���v�Q��
        HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();

        // �f�[�^�R�[�h AI811(�Œ�)
        l_hostEquityOrderExecNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_EXEC_NOTICE);

        // �،���ЃR�[�h �ڋq�̏،���ЃR�[�h
        l_hostEquityOrderExecNotifyParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());

        // ���X�R�[�h �ڋq�̕��X�R�[�h
        l_hostEquityOrderExecNotifyParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());

        // �ڋq�R�[�h �ڋq�̌����R�[�h
        l_hostEquityOrderExecNotifyParams.setAccountCode(l_mainAccount.getAccountCode());

        // ���҃R�[�h null
        l_hostEquityOrderExecNotifyParams.setTraderCode(null);

        // ���ʃR�[�h �����P�ʂ̎��ʃR�[�h
        //�����P��
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        l_hostEquityOrderExecNotifyParams.setOrderRequestNumber(l_eqOrderUnitRow.getOrderRequestNumber());

        //��芔�� ���f�[�^�̖�芔��
        l_hostEquityOrderExecNotifyParams.setExecQuantity(l_orderExecution.getExecutionQuantity());

        // ���P�� ���f�[�^�̖��P��
        l_hostEquityOrderExecNotifyParams.setExecPrice(l_orderExecution.getExecutionPrice());

        // ������ ���f�[�^�̖�����
        l_hostEquityOrderExecNotifyParams.setExecTimestamp(l_orderExecution.getExecutionTimestamp());

        //�o���ʒm�敪 4�F�����
        l_hostEquityOrderExecNotifyParams.setDealedType(WEB3DealedTypeDef.CANCEL);

        //���z�T�[�oNo.�i�s��jnull
        l_hostEquityOrderExecNotifyParams.setVirtualServerNumberMarket(null);

        //�ʒm��� null
        l_hostEquityOrderExecNotifyParams.setNoticeType(null);

        //�ʒm�ԍ� null
        l_hostEquityOrderExecNotifyParams.setNoticeNumber(null);

        //���ʒm�ԍ� null
        l_hostEquityOrderExecNotifyParams.setExecNumber(null);

        //�����敪 0�i�Œ�j
        l_hostEquityOrderExecNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        // �쐬���t ���ݓ���
        l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(l_tsSystemTimestamp);

        // �X�V���t ���ݓ���
        l_hostEquityOrderExecNotifyParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

        // �X�V�҃R�[�h �Ǘ��҃R�[�h
        l_hostEquityOrderExecNotifyParams.setLastUpdater(l_administrator.getAdministratorCode());
        try
        {
            //QueryProcessor.doInsertQuery()���R�[������
            //[����]
            //arg0�F�@@�����o���ʒm�L���[Params
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_hostEquityOrderExecNotifyParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (validate�Ǘ��Ҍ���)<BR>
     * �Ǘ��ҁE���X�������`�F�b�N����B<BR>
     * <BR>
     * 1�j�@@�Ǘ���.validate����()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���������i�o�����́E�o������j<BR>
     * �@@�@@is�X�V�F�@@true(�X�V����)<BR>
     * <BR>
     * �@@�@@���@@�\�J�e�S���R�[�h�́A<BR>
     * �@@�@@DB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@arg0�F�@@����.���XID<BR>
     * <BR>
     * �R�j�@@�Ǘ���.validate���X����()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@���X�R�[�h�F�@@getBranch().get���X�R�[�h()�̖߂�l<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4785C65902BF
     */
    protected void validateManagerAuthority(WEB3Administrator l_administrator,
        long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateManagerAuthority(WEB3Administrator, long)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeBranch l_branch = null;

        //�Ǘ���.validate����()���R�[������
        //[����]
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���������i�o�����́E�o������j
        //is�X�V�F�@@true(�X�V����)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_EXEC_INPUT_CANCEL, true);

        //�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������
        //[����]
        //arg0�F�@@����.���XID
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_lngBranchId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //����.�Ǘ���.validate���X����()���R�[������
        //[����]
        //���X�R�[�h�F�@@getBranch().get���X�R�[�h()�̖߂�l
        l_administrator.validateBranchPermission(l_branch.getBranchCode());

        log.exiting(STR_METHOD_NAME);
    }
}
@
