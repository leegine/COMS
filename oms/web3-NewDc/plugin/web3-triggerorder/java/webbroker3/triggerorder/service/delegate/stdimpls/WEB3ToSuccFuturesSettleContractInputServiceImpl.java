head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesSettleContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�敨�ԍϓ��̓T�[�r�XImpl�iWEB3ToSuccFuturesSettleContractInputServiceImpl.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/17 �И���(���u) �V�K�쐬���f��No.255 No.298 No.320
 Revision History : 2008/04/19 �И���(���u) �d�l�ύX���f��No.335 No.337
 Revision History : 2008/05/19 ����(���u) �d�l�ύX���f��No.318
 */

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractInputServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�敨�ԍϓ��̓T�[�r�XImpl)<BR>
 * �i�A���j�敨�ԍϓ��̓T�[�r�X�����N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3ToSuccFuturesSettleContractInputServiceImpl
    extends WEB3FuturesSettleContractInputServiceImpl
    implements WEB3ToSuccFuturesSettleContractInputService
{

    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesSettleContractInputServiceImpl.class);

    /**
     * @@roseuid 47D659350138
     */
    public WEB3ToSuccFuturesSettleContractInputServiceImpl()
    {

    }

    /**
     * �i�A���j�����w���敨�ԍϓ��͉�ʕ\�����������{����B<BR>
     * <BR>
     * this.get�ԍϓ��͉��()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94B8701C6
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

        if (!(l_request instanceof WEB3SuccFuturesCloseInputRequest))
        {
            log.debug("�p�����[�^�^�C�v�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s��");
        }

        WEB3SuccFuturesCloseInputRequest l_inputRequest =
            (WEB3SuccFuturesCloseInputRequest)l_request;

        WEB3SuccFuturesCloseInputResponse l_response =
            this.getSettleContractInputScreen(l_inputRequest);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ԍϓ��͉��)<BR>
     * �i�A���j�����w���敨�ԍϓ��͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�敨�ԍϓ��́jget�ԍϓ��͉�ʁv�Q��<BR>
     * ========================================================<BR>
     * �V�[�P���X�} �F(�i�i�A���j�敨�ԍϓ��́jget�ԍϓ��͉��) <BR>
     * ��̈ʒu�F(reset�����R�[�h(�����R�[�h : String))<BR>
     * class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_02248<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccFuturesCloseInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94B8701E6
     */
    protected WEB3SuccFuturesCloseInputResponse getSettleContractInputScreen(
        WEB3SuccFuturesCloseInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSettleContractInputScreen(WEB3SuccFuturesCloseInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get�敨OP�e�����̒����P��(long)
        //�i�e�����j����ID�F�@@���N�G�X�g.�A���������ʏ��̓�����
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_request.succCommonInfo.parentOrderId));
        //get�⏕����
        SubAccount l_subAccount = this.getSubAccount();
        //is���Δ������
        boolean l_blnReversingTrade =
            l_orderManager.isReversingTrade(l_request.succCommonInfo.succTradingType, l_ifoOrderUnit);

        String l_strProductCode = null;
        //�A�������}�l�[�W��.is���Δ������()==true�̏ꍇ
        if (l_blnReversingTrade)
        {
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_ifoOrderUnit.getProduct();
            //�����R�[�h�F�@@�e�����̒����P��.�敨OP����.�����Y�����R�[�h
            l_strProductCode = l_product.getUnderlyingProductCode();
        }
        else
        {
            //�����R�[�h�F�@@���ʃI�u�W�F�N�g.�敨OP����.�����Y�����R�[�h(*1)
            //(*1)�敨OP�|�W�V�����}�l�[�W��.getContract(���N�G�X�g�f�[�^.ID[0])���猚�ʃI�u�W�F�N�g�𐶐�
            WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl =
                (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

            //���N�G�X�g�f�[�^.ID==null�̏ꍇ
            //�u�A�������戵�s�ł��B�v�̗�O���X���[����B
            if (l_request.id == null)
            {
                log.debug("�A�������戵�s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02248,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�A�������戵�s�ł��B");
            }
            //���ʃI�u�W�F�N�g���擾����B
            try
            {
                WEB3IfoContractImpl l_ifoContractImpl =
                    (WEB3IfoContractImpl)l_ifoPositionManagerImpl.getContract(
                        Long.parseLong(l_request.id[0]));
                WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_ifoContractImpl.getProduct();
                l_strProductCode = l_ifoProduct.getUnderlyingProductCode();
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        //reset�����R�[�h
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //validate�A������
        l_orderManager.validateSuccOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.FUTURES,
            l_request.succCommonInfo.succTradingType,
            l_ifoOrderUnit);

        //validate�A�������ő�ݒ萔
        l_orderManager.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

        //get�ԍϓ��͉��(���N�G�X�g�f�[�^ : �����w���敨�ԍϓ��͉�ʃ��N�G�X�g)
        WEB3SuccFuturesCloseInputResponse l_response =
            (WEB3SuccFuturesCloseInputResponse)super.getColseMarginInput(l_request);
        //�A�������}�l�[�W��.is���Δ������()==true�̏ꍇ
        if (l_blnReversingTrade)
        {
            l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getQuantity());
        }
        //���s�����ꗗ
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};
        //���������敪�ꗗ
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
        //�v�w�l�p���s�����ꗗ
        l_response.wlimitExecCondList = null;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get����)<BR>
     * ���ʂ��擾���ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���N�G�X�g�f�[�^��<BR>
     * �@@�i�A���j�����w���敨�ԍϓ��͉�ʃ��N�G�X�g�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j�@@�e�����̒����P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�敨OP�e�����̒����P��()��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[�w�肷�����]<BR>
     * �@@�@@�i�e�����j����ID�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓�����<BR>
     * <BR>
     * �R�j�@@���Δ���(*1)�̏ꍇ�A<BR>
     * �@@�A�������}�l�[�W��Impl.create����()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[�w�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�Q�j�Ŏ擾�����e�����̒����P��<BR>
     * <BR>
     * �S�j�@@�����c�ɑ΂���ԍρi�R�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.get����()���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[�w�肷�����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * <BR>
     * (*1)�A�������}�l�[�W��Impl.is���Δ������() == true�B<BR>
     * �@@[�w�肷�����]<BR>
     * �@@�@@�A����������敪�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓�����<BR>
     * �@@�@@�e�����̒����P�ʁF�@@�Q�j�Ŏ擾�����e�����̒����P��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ԍϒ������̓��N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@return WEB3IfoContractImpl
     * @@throws WEB3BaseException
     * @@roseuid 47B13D8100C5
     */
    protected WEB3IfoContractImpl getContract(WEB3FuturesCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract(WEB3FuturesCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3SuccFuturesCloseInputRequest l_inputRequest = (WEB3SuccFuturesCloseInputRequest)l_request;
        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get�敨OP�e�����̒����P��(long)
        //�i�e�����j����ID�F�@@���N�G�X�g.�A���������ʏ��̓�����
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_inputRequest.succCommonInfo.parentOrderId));

        //�A�������}�l�[�W��Impl.is���Δ������()
        boolean l_blnReversingTrade =
            l_orderManager.isReversingTrade(
                l_inputRequest.succCommonInfo.succTradingType,
                l_ifoOrderUnit);

        WEB3IfoContractImpl l_ifoContract = null;
        if (l_blnReversingTrade)
        {
            //create����()
            l_ifoContract = l_orderManager.createIfoContract(l_ifoOrderUnit);
        }
        else
        {
            //super.get����()
            l_ifoContract = super.getContract(l_request);
        }

        log.exiting(STR_METHOD_NAME);
        return l_ifoContract;
    }

    /**
     * (create���ʏƉ�׈ꗗ)<BR>
     * ���N�G�X�g�f�[�^��茚�ʏƉ�ׂ̈ꗗ���쐬����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���N�G�X�g�f�[�^��<BR>
     * �@@�i�A���j�����w���敨�ԍϓ��͉�ʃ��N�G�X�g�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j�@@�e�����̒����P�ʂ��擾����B<BR>
     * �@@�A�������}�l�[�W��Impl.get�敨OP�e�����̒����P��()��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[�w�肷�����]<BR>
     * �@@�@@�i�e�����j����ID�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓�����<BR>
     * <BR>
     * �R�j�@@���Δ���(*1)�̏ꍇ�A�ȉ��̎菇�ɂČ��ʏƉ�ׂ��쐬����B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�A�������}�l�[�W��Impl.create���ʏƉ��()��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@[�w�肷�����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�Q�j�Ŏ擾�����e�����̒����P��<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�v���p�e�B�Z�b�g�����C���X�^���X�݂̂�v�f�Ƃ���<BR>
     * �@@�@@�z��𐶐����A�ԋp����B<BR>
     * <BR>
     * �S�j�@@�����c�ɑ΂���ԍρi�R�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.create���ʏƉ�׈ꗗ()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[�w�肷�����]<BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B<BR>
     * <BR>
     * (*1)�A�������}�l�[�W��Impl.is���Δ������() == true�B<BR>
     * �@@[�w�肷�����]<BR>
     * �@@�@@�A����������敪�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓�����<BR>
     * �@@�@@�e�����̒����P�ʁF�@@�Q�j�Ŏ擾�����e�����̒����P��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ԍϒ������̓��N�G�X�g�f�[�^�I�u�W�F�N�g�B<BR>
     * @@return WEB3FuturesContractReferenceUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 47B1710C00A0
     */
    protected  WEB3FuturesContractReferenceUnit[] createContractReferenceUnitList(
        WEB3FuturesCloseMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractReferenceUnitList(WEB3FuturesCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3SuccFuturesCloseInputRequest l_inputRequest = (WEB3SuccFuturesCloseInputRequest)l_request;
        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get�敨OP�e�����̒����P��(long)
        //�i�e�����j����ID�F�@@���N�G�X�g.�A���������ʏ��̓�����
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_inputRequest.succCommonInfo.parentOrderId));

        //�A�������}�l�[�W��Impl.is���Δ������()
        boolean l_blnReversingTrade =
            l_orderManager.isReversingTrade(
                l_inputRequest.succCommonInfo.succTradingType,
                l_ifoOrderUnit);

        WEB3FuturesContractReferenceUnit[] l_referenceUnits = null;
        if (l_blnReversingTrade)
        {
            //this.create���ʏƉ��()
            WEB3FuturesContractReferenceUnit l_referenceUnit =
                this.createContractReferenceUnit(l_ifoOrderUnit);
            l_referenceUnits = new WEB3FuturesContractReferenceUnit[]{l_referenceUnit};
        }
        else
        {
            //super.create���ʏƉ�׈ꗗ()
            l_referenceUnits = super.createContractReferenceUnitList(l_request);
        }

        log.exiting(STR_METHOD_NAME);
        return l_referenceUnits;
    }

    /**
     * (sort���ʖ��׈ꗗ)<BR>
     * �����̌��ʖ��׈ꗗ���\�[�g����B <BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * �P�j�@@�p�����[�^.���N�G�X�g�f�[�^�� <BR>
     * �@@�i�A���j�����w���敨�ԍϓ��͉�ʃ��N�G�X�g�ɃL���X�g����B <BR>
     * <BR>
     * �Q�j�@@�e�����̒����P�ʂ��擾����B <BR>
     * �@@�A�������}�l�[�W��Impl.get�敨OP�e�����̒����P��()�� <BR>
     * �@@�R�[������B <BR>
     * <BR>
     * �@@[�w�肷�����] <BR>
     * �@@�@@�i�e�����j����ID�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓����� <BR>
     * <BR>
     * �R�j�@@���Δ���(*1)�̏ꍇ�A <BR>
     * �@@�ʕԍς݂̂Ń\�[�g�s�v�ł���ׁA <BR>
     * �@@�������I������B <BR>
     * <BR>
     * �S�j�@@�����c�ɑ΂���ԍρi�R�j�ȊO�j�̏ꍇ�A <BR>
     * �@@super.sort���ʖ��׈ꗗ()���R�[������B <BR>
     * <BR>
     * �@@[�w�肷�����] <BR>
     * �@@�@@�{���\�b�h�̈��������̂܂ܐݒ�B <BR>
     * <BR>
     * (*1)�A�������}�l�[�W��Impl.is���Δ������() == true�B <BR>
     * �@@[�w�肷�����] <BR>
     * �@@�@@�A����������敪�F�@@�P�j�Ŏ擾�������N�G�X�g.�A���������ʏ��̓����� <BR>
     * �@@�@@�e�����̒����P�ʁF�@@�Q�j�Ŏ擾�����e�����̒����P��<BR>
     * @@param l_contractUnits - (���ʖ��׈ꗗ)<BR>
     * ���ʖ��ׂ̔z��B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ԍϒ������̓��N�G�X�g�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B242EC0166
     */
    protected void sortContractUnitList(
        WEB3FuturesOptionsContractUnit[] l_contractUnits,
        WEB3FuturesCloseMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "sortContractUnitList(WEB3FuturesOptionsContractUnit[], WEB3FuturesCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseInputRequest l_inputRequest = (WEB3SuccFuturesCloseInputRequest)l_request;
        //�A�������}�l�[�W�����擾����
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get�敨OP�e�����̒����P��(long)
        //�i�e�����j����ID�F�@@���N�G�X�g.�A���������ʏ��̓�����
        IfoOrderUnit l_ifoOrderUnit =
            l_orderManager.getIfoParentOrderUnit(
                Long.parseLong(l_inputRequest.succCommonInfo.parentOrderId));

        //�A�������}�l�[�W��Impl.is���Δ������()
        boolean l_blnReversingTrade =
            l_orderManager.isReversingTrade(
                l_inputRequest.succCommonInfo.succTradingType,
                l_ifoOrderUnit);

        //�A�������}�l�[�W��Impl.is���Δ������() == true
        //�ʕԍς݂̂Ń\�[�g�s�v�ł���ׁA�������I������B
        if (l_blnReversingTrade)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            //super.sort���ʖ��׈ꗗ()
            super.sortContractUnitList(l_contractUnits, l_request);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���ʏƉ��)<BR>
     * �����̒����P�ʂ�芔���w���敨���ʏƉ�ׂ��쐬����B <BR>
     * <BR>
     * �����Δ������Ɏg�p����B <BR>
     * <BR>
     * �P�j�@@�ȉ��̎菇�ɂĊ����w���敨���ʏƉ�ׂ��쐬����B <BR>
     * �@@�P�|�P�j�@@�����w���敨���ʏƉ�׃C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �@@�P�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * �@@�@@�@@�@@�@@�@@���ȉ��̍��ڈȊO��null���Z�b�g����B <BR>
     * <BR>
     * �@@�@@�w����� = �����P��.�敨OP����.�����Y�����R�[�h <BR>
     * �@@�@@���� = �����P��.�敨OP����.���� <BR>
     * �@@�@@����s�� = �����P��.�s��R�[�h�iSONAR�j <BR>
     * �@@�@@���敪 = �����P��.getSide() == "��"�̏ꍇ�A"����" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A"����" <BR>
     * �@@�@@���� = �����P��.������ <BR>
     * �@@�@@������ = �����P��.�������� <BR>
     * <BR>
     * �@@�P�|�R�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��ԋp����B<BR>
     * @@param l_ifoOrderUnit  - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return WEB3FuturesContractReferenceUnit
     * @@throws WEB3BaseException
     */
    public WEB3FuturesContractReferenceUnit createContractReferenceUnit(
        IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractReferenceUnit(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�����w���敨���ʏƉ�׃C���X�^���X�𐶐�����
        WEB3FuturesContractReferenceUnit l_futuresContractReferenceUnit =
            new WEB3FuturesContractReferenceUnit();

        //�w����� = �����P��.�敨OP����.�����Y�����R�[�h
        IfoProduct l_ifoProduct = (IfoProduct)l_ifoOrderUnit.getProduct();
        l_futuresContractReferenceUnit.targetProductCode =
            l_ifoProduct.getUnderlyingProductCode();

        //���� = �����P��.�敨OP����.����
        l_futuresContractReferenceUnit.delivaryMonth = l_ifoProduct.getMonthOfDelivery();

        //����s�� = �����P��.�s��R�[�h�iSONAR�j
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        l_futuresContractReferenceUnit.marketCode = l_ifoOrderUnitRow.getSonarMarketCode();

        //���敪
        if (SideEnum.BUY.equals(l_ifoOrderUnit.getSide()))
        {
            //�����P��.getSide() == "��"�̏ꍇ�A"����"
            l_futuresContractReferenceUnit.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
        }
        else
        {
            //�ȊO�A"����"
            l_futuresContractReferenceUnit.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
        }

        //���� = �����P��.������
        l_futuresContractReferenceUnit.openDate =
            WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //������ = �����P��.��������
        l_futuresContractReferenceUnit.contractOrderQuantity =
            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getQuantity());

        log.exiting(STR_METHOD_NAME);
        return l_futuresContractReferenceUnit;
    }
}
@
