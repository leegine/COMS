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
filename	WEB3AdminEquityPTSInputExecServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�XImpl�iWEB3AdminEquityPTSInputExecServiceImpl.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/22 ���� (���u) �V�K�쐬���f��177�A�c�a�X�V�d�l 019
Revision History : 2008/01/22 ���� (���u) ���f��187
Revision History : 2008/01/29 ���� (���u) ���f��192
Revision History : 2008/02/18 ��іQ(���u) ���f��No.197
Revision History : 2008/02/27 ��іQ(���u) ���f��No.198,201
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.math.BigDecimal;
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
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
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
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSExecHistory;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputCancelExecCommonRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSOrderDetailUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSInputExecService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�����N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputExecServiceImpl extends WEB3ClientRequestService
    implements WEB3AdminEquityPTSInputExecService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputExecServiceImpl.class);

    /**
     * (�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�XImpl)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 4795A0F70284
     */
    public WEB3AdminEquityPTSInputExecServiceImpl()
    {

    }

    /**
     * �����iPTS�j�o�����͏������s���B<BR>
     * <BR>
     * �����̃��N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�����iPTS�j�o�����̓��N�G�X�g�̏ꍇ <BR>
     * �@@�@@this.get���͉��()���R�[������B <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@���N�G�X�g�f�[�^�F�@@����.���N�G�X�g�f�[�^ <BR>
     * <BR>
     * ���Ǘ��ҁE�����iPTS�j�o�����͊m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�@@this.validate�o������()���R�[������B <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@���N�G�X�g�f�[�^�F�@@����.���N�G�X�g�f�[�^ <BR>
     * <BR>
     * ���Ǘ��ҁE�����iPTS�j�o�����͊������N�G�X�g�̏ꍇ <BR>
     * �@@�@@this.submit�o������()���R�[������B <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@���N�G�X�g�f�[�^�F�@@����.���N�G�X�g�f�[�^ <BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47723D36009F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)  throws WEB3BaseException
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
        //�����̊����������N�G�X�g�̃I�u�W�F�N�g�^���A
        //�T�[�r�X���\�b�h�𔻒肵�R�[������B

        // �Ǘ��ҁE�����iPTS�j�o�����̓��N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminEquityPTSInputExecInputRequest)
        {
            //�@@this.get���͉��()���R�[������B
            l_response = this.getInputScreen((WEB3AdminEquityPTSInputExecInputRequest)l_request);
        }
        // �Ǘ��ҁE�����iPTS�j�o�����͊m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminEquityPTSInputExecConfirmRequest)
        {
            // this.validate�o������()���R�[������B
            l_response = this.validateInputExec((WEB3AdminEquityPTSInputExecConfirmRequest)l_request);
        }
        // �Ǘ��ҁE�����iPTS�j�o�����͊������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminEquityPTSInputExecCompleteRequest)
        {
            // this.submit�o������()���R�[������B
            l_response = this.submitInputExec((WEB3AdminEquityPTSInputExecCompleteRequest)l_request);
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
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * �o�����͂̓��͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�Ǘ��ҁE�����iPTS�j�o�����́jget���͉�ʁv �Q�ƁB <BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * �Ǘ��ҁE����(PTS)�o�����̓��N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminEquityPTSInputExecInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47723C820077
     */
    protected WEB3AdminEquityPTSInputExecInputResponse getInputScreen(
        WEB3AdminEquityPTSInputExecInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEquityPTSInputExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        // validate()
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        // getOrderUnits
        // [����]
        // arg0�F�@@���N�G�X�g�f�[�^.����ID
        // ���擾���������I�u�W�F�N�g�̔z��̐擪�v�f���璍���P�ʂ��擾����B
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();

        // getInstanceFrom
        // ���O�C�������Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        // �Ǘ��Ҍ������`�F�b�N����B
        // [����]
        // �Ǘ��ҁF�@@getInstanceFrom���O�C�����()�̖߂�l
        // ���XID�F�@@�擾���������P��.���XID
        this.validateAdminAuthorities(l_administrator, l_orderUnitRow.getBranchId());

        // validate�o�����͉\����(�����P��)
        this.validateInputExecPossibilityOrder(l_eqOrderUnit);

        // set����J�����_�R���e�L�X�g(�����P��)
        // [����]
        // �����P�ʁF�@@�擾���������P��
        this.setTradingClendarContext(l_eqOrderUnit);

        // validate�o�����͏o������\���ԑ�()
        // �o�����͂��\�Ȏ��ԑт��`�F�b�N����B
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        // get�����ڍ�(�����P��)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_eqOrderUnit);

        // get��藚��(�����P��)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_eqOrderUnit);

        WEB3AdminEquityPTSInputExecInputResponse l_response =
            (WEB3AdminEquityPTSInputExecInputResponse)l_request.createResponse();

        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_ptsExecHistories;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�o������)<BR>
     * �o�����͂̊m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�Ǘ��ҁE�����iPTS�j�o�����́jvalidate�o�����́v �Q�ƁB <BR>
     *
     * @@param l_request - (���̓f�[�^)<BR>
     * �Ǘ��ҁE����(PTS)�o�����͊m�F���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminEquityPTSInputExecConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47723C88021C
     */
    protected WEB3AdminEquityPTSInputExecConfirmResponse validateInputExec(
        WEB3AdminEquityPTSInputExecConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInputExec(WEB3AdminEquityPTSInputExecConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // validate()
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        // getOrderUnits
        // [����]
        // arg0�F�@@���N�G�X�g�f�[�^.����ID
        // ���擾���������I�u�W�F�N�g�̔z��̐擪�v�f���璍���P�ʂ��擾����B
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();

        // getInstanceFrom
        // ���O�C�������Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // �Ǘ��Ҍ������`�F�b�N����B
        // [����]
        // �Ǘ��ҁF�@@getInstanceFrom���O�C�����()�̖߂�l
        // ���XID�F�@@�擾���������P��.���XID
        this.validateAdminAuthorities(l_administrator, l_orderUnitRow.getBranchId());

        // validate�o�����͉\����(�����P��)
        this.validateInputExecPossibilityOrder(l_eqOrderUnit);

        // validate�o�����͉\���
        // [����]
        // �����P�ʁF�@@�擾���������P��
        // ���͖�芔���F�@@���N�G�X�g.��芔��
        // ���͖������F�@@���N�G�X�g.������
        this.validateInputExecPossibilityExecuted(
            l_eqOrderUnit, l_request.execQuantity, l_request.executionTimeStamp);

        // set����J�����_�R���e�L�X�g(�����P��)
        // [����]
        // �����P�ʁF�@@�擾���������P��
        this.setTradingClendarContext(l_eqOrderUnit);

        // validate�o�����͏o������\���ԑ�()
        // �o�����͂��\�Ȏ��ԑт��`�F�b�N����B
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        // get�����ڍ�(�����P��)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_eqOrderUnit);

        // get��藚��(�����P��)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_eqOrderUnit);

        // get��藚���i�m�F�E�����j
        // [����]
        // �Ǘ��ҁF�@@getInstanceFrom���O�C�����()�̖߂�l
        // ��藚���F�@@get��藚��()�̖߂�l
        // ���N�G�X�g�f�[�^�F�@@����.���N�G�X�g�f�[�^
        WEB3AdminEquityPTSExecHistory[] l_histories = this.getExecutedHistory(
            l_administrator, l_ptsExecHistories, l_request);

        WEB3AdminEquityPTSInputExecConfirmResponse l_response =
            (WEB3AdminEquityPTSInputExecConfirmResponse)l_request.createResponse();

        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_histories;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�o������)<BR>
     * �o�����͂̊�����ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�Ǘ��ҁE�����iPTS�j�o�����́jsubmit�o�����́v �Q�ƁB <BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * �Ǘ��ҁE�����iPTS�j�o�����͊������N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminEquityPTSInputExecCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47723C8F0269
     */
    protected WEB3AdminEquityPTSInputExecCompleteResponse submitInputExec(
        WEB3AdminEquityPTSInputExecCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitInputExec(WEB3AdminEquityPTSInputExecCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // validate()
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        // getOrderUnits
        // [����]
        // arg0�F�@@���N�G�X�g�f�[�^.����ID
        // ���擾���������I�u�W�F�N�g�̔z��̐擪�v�f���璍���P�ʂ��擾����B
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();

        // getInstanceFrom
        // ���O�C�������Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // �Ǘ��Ҍ������`�F�b�N����B
        // [����]
        // �Ǘ��ҁF�@@getInstanceFrom���O�C�����()�̖߂�l
        // ���XID�F�@@�擾���������P��.���XID
        this.validateAdminAuthorities(l_administrator, l_orderUnitRow.getBranchId());

        //�Ïؔԍ��̃`�F�b�N���s��
        //[����]
        //�p�X���[�h: ���N�G�X�g�f�[�^.�Ïؔԍ�
        //validate����p�X���[�h(�p�X���[�h : String)
        l_administrator.validateTradingPassword(l_request.password);

        // �ڋq�I�u�W�F�N�g���擾����B
        // [����]
        // ����ID�F�@@�擾���������P��.����ID
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                l_orderUnitRow.getAccountId());
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
        // lock����
        // ���������b�N����B
        // [����]
        // �،���ЃR�[�h�F�@@�ڋq.getInstitution().getInstitutionCode()
        // ���X�R�[�h�F�@@�ڋq.getBranch().getBranchCode()
        // �ڋq�R�[�h�F�@@�ڋq.getAccountCode()
        l_accountManager.lockAccount(
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_mainAccount.getAccountCode());

        // getOrderUnit
        // [����]
        // �����P��ID�F�@@�擾���������P��.�����P��ID
        try
        {
            l_eqOrderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnitRow.getOrderUnitId());
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

        // validate�o�����͉\����(�����P��)
        // [����]
        // �����P�ʁF�@@�擾���������P��
        this.validateInputExecPossibilityOrder(l_eqOrderUnit);

        // validate�o�����͉\���(�����P��, String, Date)
        // [����]
        // �����P�ʁF�@@�擾���������P��
        // ���͖�芔���F�@@���N�G�X�g.��芔��
        // ���͖������F�@@���N�G�X�g.������
        this.validateInputExecPossibilityExecuted(
            l_eqOrderUnit, l_request.execQuantity, l_request.executionTimeStamp);

        // set����J�����_�R���e�L�X�g(�����P��)
        // �����P�ʂ̃f�[�^������J�����_�R���e�L�X�g�ɃZ�b�g����B
        // [����]
        // �����P�ʁF�@@�擾���������P��
        this.setTradingClendarContext(l_eqOrderUnit);

        // validate�o�����͏o������\���ԑ�()
        // �o�����͂��\�Ȏ��ԑт��`�F�b�N����B
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        // ��������t���ς̏ꍇ�i�����P��.�s�ꂩ��m�F�ς݂̐��� == null
        if (l_orderUnitRow.getConfirmedQuantityIsNull())
        {
            //  insert������t(�ڋq, �����P��)
            // ������t�L���[�ɂP��insert����B

            // [����]
            // �ڋq�F�@@get�ڋq()�̖߂�l
            // �����P�ʁF�@@�擾���������P��
            this.insertOrderAccept(l_mainAccount, l_eqOrderUnit);
        }
        // get�����ڍ�(�����P��)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_eqOrderUnit);

        // get��藚��(�����P��)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_eqOrderUnit);

        // get��藚���i�m�F�E�����j
        // [����]
        // �Ǘ��ҁF�@@getInstanceFrom���O�C�����()�̖߂�l
        // ��藚���F�@@get��藚��()�̖߂�l
        // ���N�G�X�g�f�[�^�F�@@����.���N�G�X�g�f�[�^
        WEB3AdminEquityPTSExecHistory[] l_histories = this.getExecutedHistory(
            l_administrator, l_ptsExecHistories, l_request);

        //  insert�o���ʒm
        // �����o���ʒm�L���[��1��insert����B

        // [����]
        // �ڋq�F�@@get�ڋq()�̖߂�l
        // �Ǘ��ҁF�@@getInstanceFrom���O�C�����()�̖߂�l
        // �����P�ʁF�@@�擾���������P��
        // ���N�G�X�g�f�[�^�F�@@����.���N�G�X�g�f�[�^
        this.insertExecNotify(l_mainAccount, l_administrator, l_eqOrderUnit, l_request);

        WEB3AdminEquityPTSInputExecCompleteResponse l_response =
            (WEB3AdminEquityPTSInputExecCompleteResponse)l_request.createResponse();

        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_histories;

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (validate�Ǘ��Ҍ���)<BR>
     * �Ǘ��ҁE���X�������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@����.�Ǘ���.validate����()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���������i�o�����́E�o������j<BR>
     * �@@�@@is�X�V�F�@@true(�X�V����)<BR>
     * <BR>
     * �@@�@@���@@�\�J�e�S���R�[�h�́ADB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls<BR>
     * �@@�@@�@@�@@#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@arg0�F�@@����.���XID<BR>
     * <BR>
     * �R�j�@@����.�Ǘ���.validate���X����()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@���X�R�[�h�F�@@getBranch().get���X�R�[�h()�̖߂�l<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 477253830158
     */
    protected void validateAdminAuthorities(WEB3Administrator l_administrator, long l_lngBranchId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAdminAuthorities(WEB3Administrator, long)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeBranch l_branch = null;

        // �P�j�@@����.�Ǘ���.validate����()���R�[������B

        // [����]
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���������i�o�����́E�o������j
        // is�X�V�F�@@true(�X�V����)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_EXEC_INPUT_CANCEL, true);

        // �Q�j�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B

        // [����]
        // arg0�F�@@����.���XID
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

        // �R�j�@@����.�Ǘ���.validate���X����()���R�[������B

        //�@@[����]
        //�@@���X�R�[�h�F�@@getBranch().get���X�R�[�h()�̖߂�l
        l_administrator.validateBranchPermission(l_branch.getBranchCode());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set����J�����_�R���e�L�X�g)<BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * <BR>
     * �P�j�@@���X�I�u�W�F�N�g���擾�B <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@arg0�F�@@����.�����P��.���XID<BR>
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
     * �@@�|�p�����[�^.�����f�[�^�̓��e��������ԃR���e�L�X�g�� <BR>
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
     * �@@�@@�@@����J�����_�R���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@�@@�ݒ�L�[�F PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �T�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|PTS������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4773292D013C
     */
    protected void setTradingClendarContext(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingClendarContext(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        try
        {
            // �P�j�@@���X�I�u�W�F�N�g���擾�B
            // �g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B

            // [����]
            // arg0�F�@@����.�����P��.���XID
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(
                l_eqOrderUnitRow.getBranchId());

            // �Q�j�@@�،���ЃI�u�W�F�N�g���擾�B
            // getBranch()�̖߂�l.getInstitution()���R�[������B
            WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_branch.getInstitution();

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            //�R�j�@@�s��I�u�W�F�N�g���擾����B
            // �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������B

            // [����]
            // arg0�F�@@����.�����P��.�s��ID
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                l_eqOrderUnitRow.getMarketId());

            // �S�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�o�����͉\����)<BR>
     * �o�����͏������\�Ȓ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@PTS�s�ꂩ�ǂ����`�F�b�N����B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[����<BR>
     * �@@�@@�@@�@@�@@�@@�s��I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@�@@�@@�s��ID�F�@@����.�����P��.�s��ID <BR>
     * <BR>
     * �@@�P�|�Q�j�@@PTS�s��łȂ��ꍇ(�s��.isPTS�s��() == false)�A<BR>
     * �@@�@@�@@�@@�@@�@@�u������PTS�s��łȂ����߁A�o�����͕s�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02988<BR>
     * <BR>
     * �Q�j�@@�����L����Ԃ�CLOSED�̏ꍇ�A<BR>
     * �@@�@@�@@�u������Ԃ��o�����͕s�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02983<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47724F4200EE
     */
    protected void validateInputExecPossibilityOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInputExecPossibilityOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        // �P�j�@@PTS�s�ꂩ�ǂ����`�F�b�N����B

        // �P�|�P�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[����
        // �s��I�u�W�F�N�g���擾����B

        // [����]
        // �s��ID�F�@@����.�����P��.�s��ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_eqOrderUnitRow.getMarketId());
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

        // �P�|�Q�j�@@PTS�s��łȂ��ꍇ(�s��.isPTS�s��() == false)
        //�u������PTS�s��łȂ����߁A�o�����͕s�v�̗�O���X���[����B
        if (!l_market.isPTSMarket())
        {
            log.debug("������PTS�s��łȂ����߁A�o�����͕s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02988,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������PTS�s��łȂ����߁A�o�����͕s�B");
        }

        // �Q�j�@@�����L����Ԃ�CLOSED�̏ꍇ�A
        //�u������Ԃ��o�����͕s�v�̗�O���X���[����B
        if ((OrderOpenStatusEnum.CLOSED).equals(l_eqOrderUnitRow.getOrderOpenStatus()))
        {
            log.debug("������Ԃ��o�����͕s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02983,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������Ԃ��o�����͕s�B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�o�����͉\���)<BR>
     * ���͂��ꂽ���e�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@��萔�ʃ`�F�b�N<BR>
     * <BR>
     * �@@�@@����.���͖�芔�� �� ����萔�ʁi*1�j �̏ꍇ�A<BR>
     * �@@�@@�u���͂�����芔��������芔���𒴂��Ă���v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02987<BR>
     * <BR>
     * �@@�@@�i*1�j ����萔�� �� �����P��.�s�ꂩ��m�F�ς݂̐��ʁi*2�j �| �����P��.��萔��<BR>
     * �@@�@@�i*2�j ������t���ς̏ꍇ�i�����P��.�s�ꂩ��m�F�ς݂̐��� == null�j�A<BR>
     * �@@�@@�@@�@@�@@ �����P��.�������ʂ̒l���g�p����B<BR>
     * <BR>
     * �Q�j�@@�������`�F�b�N<BR>
     * <BR>
     * �@@�@@����.���͖����� �� �����P��.�󒍓��� �̏ꍇ�A<BR>
     * �@@�@@�u���͂������������A�󒍓������ߋ������v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02984<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_strInputExecQuantity -(���͖�芔��)<BR>
     * ���͖�芔��<BR>
     * @@param l_datInputExecutionTimeStamp -(���͖�����)<BR>
     * ���͖�����<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47725A7D0024
     */
    protected void validateInputExecPossibilityExecuted(
        EqTypeOrderUnit l_orderUnit,
        String l_strInputExecQuantity, Date l_datInputExecutionTimeStamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInputExecPossibilityExecuted(EqTypeOrderUnit, String, Date)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@��萔�ʃ`�F�b�N

        // ����.���͖�芔�� �� ����萔�ʁi*1�j �̏ꍇ�A
        //�u���͂�����芔��������芔���𒴂��Ă���v�̗�O���X���[����B

        //�i*1�j ����萔�� �� �����P��.�s�ꂩ��m�F�ς݂̐��ʁi*2�j �| �����P��.��萔��
        //�i*2�j ������t���ς̏ꍇ�i�����P��.�s�ꂩ��m�F�ς݂̐��� == null�j
        // �����P��.�������ʂ̒l���g�p����B
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        BigDecimal l_bdConfirmedQuantity = null;

        // ������t���ς̏ꍇ�i�����P��.�s�ꂩ��m�F�ς݂̐��� == null�j
        if (l_eqOrderUnitRow.getConfirmedQuantityIsNull())
        {
            // �����P��.�������ʂ̒l���g�p����B
            l_bdConfirmedQuantity = new BigDecimal(String.valueOf(l_eqOrderUnitRow.getQuantity()));

        }
        else
        {
            l_bdConfirmedQuantity = new BigDecimal(String.valueOf(l_eqOrderUnitRow.getConfirmedQuantity()));
        }

        // ����萔�� �� �����P��.�s�ꂩ��m�F�ς݂̐��� �| �����P��.��萔��

        double l_dbNotExectedQuantity = (l_bdConfirmedQuantity.subtract(
            new BigDecimal(String.valueOf(l_eqOrderUnitRow.getExecutedQuantity())))).doubleValue();

        // ����.���͖�芔�� �� ����萔�ʁi*1�j �̏ꍇ�A
        //�u���͂�����芔��������芔���𒴂��Ă���v�̗�O���X���[����B
        if (Double.parseDouble(l_strInputExecQuantity) > l_dbNotExectedQuantity)
        {
            log.debug("���͂�����芔��������芔���𒴂��Ă���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02987,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͂�����芔��������芔���𒴂��Ă���B");
        }

        // �Q�j�@@�������`�F�b�N
        //����.���͖����� �� �����P��.�󒍓��� �̏ꍇ�A
        //�u���͂������������A�󒍓������ߋ������v�̗�O���X���[����B
        if (WEB3DateUtility.compareToSecond(
            l_datInputExecutionTimeStamp, l_eqOrderUnitRow.getReceivedDateTime()) < 0)
        {
            log.debug("���͂������������A�󒍓������ߋ������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02984,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͂������������A�󒍓������ߋ������B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get��藚���i�m�F�E�����j)<BR>
     * ���͂��������e���Ǘ��ҁE�����iPTS�j��藚���ɐݒ肵�ԋp����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐����A����.��藚���̑S�v�f�i*�j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�i*�j����.��藚�� != null �̏ꍇ�̂݁B<BR>
     * <BR>
     * �Q�j�@@�Ǘ��ҁE�����iPTS�j��藚���I�u�W�F�N�g���쐬���A<BR>
     * �@@�@@�@@�ȉ����ڂɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�E����\�t���O�F�@@false<BR>
     * �@@�@@�@@�E�������F�@@����.���N�G�X�g�f�[�^.������<BR>
     * �@@�@@�@@�E��芔���F�@@����.���N�G�X�g�f�[�^.��芔��<BR>
     * �@@�@@�@@�E���P���F�@@����.���N�G�X�g�f�[�^.���P��<BR>
     * �@@�@@�@@�E���E����敪�F�@@"�ꕔ���"<BR>
     * �@@�@@�@@�E�X�V�҃R�[�h�F�@@�Ǘ���.�Ǘ��҃R�[�h<BR>
     * �@@�@@�@@�E�����敪�F�@@"������"<BR>
     * <BR>
     * �R�j�@@�Q�j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�@@ArrayList���藚���̔z��ɕϊ���A�ԋp����B<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@param l_executedHistory - (��藚��)<BR>
     * ��藚��<BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * �Ǘ��ҁE����(PTS)�o�����͎�����ʃ��N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminEquityPTSExecHistory[]
     * @@throws WEB3BaseException
     * @@roseuid 477349920093
     */
    protected WEB3AdminEquityPTSExecHistory[] getExecutedHistory(
        WEB3Administrator l_administrator,
        WEB3AdminEquityPTSExecHistory[] l_executedHistory,
        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutedHistory(WEB3Administrator, WEB3AdminEquityPTSExecHistory[],"
            + "WEB3AdminEquityPTSInputCancelExecCommonRequest)";
        log.entering(STR_METHOD_NAME);

        List l_lisPtsExecHistories = new ArrayList();

        // ����.��藚�� != null �̏ꍇ�̂݁B
        // �P�j�@@ArrayList�𐶐����A����.��藚���̑S�v�f�i*�j��ArrayList�ɒǉ�����B

        if (l_executedHistory != null)
        {
            int l_intPtsExecHistoryCnt = l_executedHistory.length;
            for (int i = 0; i < l_intPtsExecHistoryCnt; i++)
            {
                l_lisPtsExecHistories.add(l_executedHistory[i]);
            }
        }


        //�Q�j�@@�Ǘ��ҁE�����iPTS�j��藚���I�u�W�F�N�g���쐬���A
        // �ȉ����ڂɒl���Z�b�g����B
        WEB3AdminEquityPTSExecHistory l_ptsExecHistory = new WEB3AdminEquityPTSExecHistory();

        // �E����\�t���O�F�@@false
        l_ptsExecHistory.cancelFlag = false;
        // �E�������F�@@����.���N�G�X�g�f�[�^.������
        l_ptsExecHistory.executionTimeStamp = l_request.executionTimeStamp;
        // �E��芔���F�@@����.���N�G�X�g�f�[�^.��芔��
        l_ptsExecHistory.execQuantity = l_request.execQuantity;
        // �E���P���F�@@����.���N�G�X�g�f�[�^.���P��
        l_ptsExecHistory.execPrice = l_request.execPrice;
        // �E���E����敪�F�@@"�ꕔ���"
        l_ptsExecHistory.inputExecCancelExecDiv = WEB3DealedTypeDef.PARTIALLY_EXECUTED;
        // �E�X�V�҃R�[�h�F�@@�Ǘ���.�Ǘ��҃R�[�h
        l_ptsExecHistory.updaterCode = l_administrator.getAdministratorCode();
        // �E�����敪�F�@@"������"
        l_ptsExecHistory.inputExecCancelExecProcDiv = WEB3StatusDef.NOT_DEAL;

        // �R�j�@@�Q�j��ArrayList�ɒǉ�����B
        l_lisPtsExecHistories.add(l_ptsExecHistory);

        // �S�j�@@ArrayList���藚���̔z��ɕϊ���A�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return (WEB3AdminEquityPTSExecHistory[])l_lisPtsExecHistories.toArray(
            new WEB3AdminEquityPTSExecHistory[l_lisPtsExecHistories.size()]);
    }

    /**
     * (insert������t)<BR>
     * ��t���ϒ����̏ꍇ�A����������t�L���[�e�[�u����1���f�[�^��o�^����B<BR>
     * <BR>
     * �P�j�@@����.����������t�L���[Params�Ɉȉ��v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@���ݒ�l��DB�X�V�d�l�u�o������_����������t�L���[�e�[�u���v�Q��<BR>
     * <BR>
     * �Q�j�@@QueryProcessor.doInsertQuery()���R�[������B <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@arg0�F�@@����������t�L���[Params<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 478CA9480009
     */
    protected void insertOrderAccept(MainAccount l_mainAccount, EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertOrderAccept(MainAccount, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����.����������t�L���[Params�Ɉȉ��v���p�e�B���Z�b�g����B
        // ���ݒ�l��DB�X�V�d�l�u�o������_����������t�L���[�e�[�u���v�Q��
        HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams = new HostEqtypeOrderAcceptParams();

        // �f�[�^�R�[�h AI80A�i�Œ�j
        l_hostEqtypeOrderAcceptParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_ACCEPT);

        // �،���ЃR�[�h �ڋq�̏،���ЃR�[�h
        l_hostEqtypeOrderAcceptParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());

        // ���X�R�[�h �ڋq�̕��X�R�[�h
        l_hostEqtypeOrderAcceptParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());

        // �ڋq�R�[�h �ڋq�̌����R�[�h
        l_hostEqtypeOrderAcceptParams.setAccountCode(l_mainAccount.getAccountCode());

        // ���҃R�[�h null
        l_hostEqtypeOrderAcceptParams.setTraderCode(null);

        // ���ʃR�[�h �����P�ʂ̎��ʃR�[�h
        //�����P��
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        l_hostEqtypeOrderAcceptParams.setOrderRequestNumber(l_eqOrderUnitRow.getOrderRequestNumber());

        // ������t���� 1:������t����
        l_hostEqtypeOrderAcceptParams.setAcceptStatus(WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE);

        // �G���[���b�Z�[�W null
        l_hostEqtypeOrderAcceptParams.setErrorMessage(null);

        // �����o�H�敪 �����P�ʂ̔����o�H
        l_hostEqtypeOrderAcceptParams.setSubmitOrderRouteDiv(l_eqOrderUnitRow.getSubmitOrderRouteDiv());

        // ���z�T�[�oNo.�i�s��j  null
        l_hostEqtypeOrderAcceptParams.setVirtualServerNumberMarket(null);

        // �ʒm���  null
        l_hostEqtypeOrderAcceptParams.setNoticeType(null);

        // �ʒm�ԍ� null
        l_hostEqtypeOrderAcceptParams.setNoticeNumber(null);

        // ������t�ԍ� null
        l_hostEqtypeOrderAcceptParams.setAcceptNumber(null);

        // �����敪 0�F������
        l_hostEqtypeOrderAcceptParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        // �쐬���t ���ݓ���
        l_hostEqtypeOrderAcceptParams.setCreatedTimestamp(l_tsSystemTimestamp);

        // �X�V���t ���ݓ���
        l_hostEqtypeOrderAcceptParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

        try
        {
            // �Q�j�@@QueryProcessor.doInsertQuery()���R�[������B
            //�@@[����]
            //�@@arg0�F�@@����������t�L���[Params
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_hostEqtypeOrderAcceptParams);
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
     * (insert�o���ʒm)<BR>
     * �����o���ʒm�L���[�e�[�u����1���f�[�^��o�^����B<BR>
     * <BR>
     * �P�j�@@�����o���ʒm�L���[Params�𐶐����A�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@���ݒ�l��DB�X�V�d�l�u�o������_�����o���ʒm�L���[�e�[�u���v�Q��<BR>
     * <BR>
     * �Q�j�@@QueryProcessor.doInsertQuery()���R�[������B <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@arg0�F�@@�����o���ʒm�L���[Params<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * �Ǘ��ҁE����(PTS)�o�����͎�����ʃ��N�G�X�g�f�[�^<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47844EC903A3
     */
    protected void insertExecNotify(
        MainAccount l_mainAccount,
        WEB3Administrator l_administrator,
        EqTypeOrderUnit l_orderUnit,
        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertExecNotify(MainAccount, WEB3Adminstrator,"
            + "EqTypeOrderUnit, WEB3AdminEquityPTSInputCancelExecCommonRequest)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����o���ʒm�L���[Params�𐶐����A�v���p�e�B���Z�b�g����B
        // ���ݒ�l��DB�X�V�d�l�u�o������_�����o���ʒm�L���[�e�[�u���v�Q��
        HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams =
            new HostEquityOrderExecNotifyParams();

        // �f�[�^�R�[�h AI811�i�Œ�j
        l_hostEquityOrderExecNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_EXEC_NOTICE);

        // �،���ЃR�[�h �ڋq�̏،���ЃR�[�h
        l_hostEquityOrderExecNotifyParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());

        // ���X�R�[�h �ڋq�̕��X�R�[�h
        l_hostEquityOrderExecNotifyParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());

        // �����R�[�h �ڋq�̌����R�[�h
        l_hostEquityOrderExecNotifyParams.setAccountCode(l_mainAccount.getAccountCode());

        // ���҃R�[�h null
        l_hostEquityOrderExecNotifyParams.setTraderCode(null);

        // ���ʃR�[�h �����P�ʂ̎��ʃR�[�h
        //�����P��
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        // �����P�ʂ̎��ʃR�[�h
        l_hostEquityOrderExecNotifyParams.setOrderRequestNumber(l_eqOrderUnitRow.getOrderRequestNumber());

        // ��芔�� ���N�G�X�g�f�[�^�̖�芔��
        l_hostEquityOrderExecNotifyParams.setExecQuantity(Double.parseDouble(l_request.execQuantity));

        // ���P�� ���N�G�X�g�f�[�^�̖��P��
        l_hostEquityOrderExecNotifyParams.setExecPrice(Double.parseDouble(l_request.execPrice));

        // ������ ���N�G�X�g�f�[�^�̖�����
        l_hostEquityOrderExecNotifyParams.setExecTimestamp(l_request.executionTimeStamp);

        // �o���ʒm�敪 2�F�ꕔ���
        l_hostEquityOrderExecNotifyParams.setDealedType(WEB3DealedTypeDef.PARTIALLY_EXECUTED);

        // ���z�T�[�oNo.�i�s��jnull
        l_hostEquityOrderExecNotifyParams.setVirtualServerNumberMarket(null);

        // �ʒm��� null
        l_hostEquityOrderExecNotifyParams.setNoticeType(null);

        // �ʒm�ԍ� null
        l_hostEquityOrderExecNotifyParams.setNoticeNumber(null);

        // ���ʒm�ԍ� null
        l_hostEquityOrderExecNotifyParams.setExecNumber(null);

        // �����敪 0�F������
        l_hostEquityOrderExecNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        // �쐬���t ���ݓ���
        l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(l_tsSystemTimestamp);

        // �X�V���t ���ݓ���
        l_hostEquityOrderExecNotifyParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

        // �X�V�҃R�[�h �Ǘ��҂̊Ǘ��҃R�[�h
        l_hostEquityOrderExecNotifyParams.setLastUpdater(l_administrator.getAdministratorCode());
        try
        {
            // �Q�j�@@QueryProcessor.doInsertQuery()���R�[������B
            //�@@[����]
            //�@@arg0�F�@@�����o���ʒm�L���[Params
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
}
@
