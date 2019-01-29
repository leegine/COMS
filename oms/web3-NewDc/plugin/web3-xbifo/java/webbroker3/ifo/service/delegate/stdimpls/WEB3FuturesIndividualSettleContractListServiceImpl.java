head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesIndividualSettleContractListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ʕԍψꗗ�\���T�[�r�X����(WEB3FuturesIndividualSettleContractListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 羉s (���u) �V�K�쐬
Revesion History : 2007/06/21 �����F(���u) ���f��730
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.message.WEB3FuturesIndividualCloseMarginListRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3FuturesSessionTypeComparator;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOpenDateComparator;
import webbroker3.ifo.message.WEB3FuturesIndividualCloseMarginListResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesIndividualSettleContractListService;

/**
 * (�敨�ʕԍψꗗ�\���T�[�r�XImpl)<BR>
 * �����w���敨�ʕԍψꗗ�\���T�[�r�X�����N���X
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesIndividualSettleContractListServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesIndividualSettleContractListService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesIndividualSettleContractListServiceImpl.class);

    /**
     * @@roseuid 40F7A2CE001F
     */
    public WEB3FuturesIndividualSettleContractListServiceImpl()
    {

    }

    /**
     * �����w���敨�ʕԍψꗗ�\���T�[�r�X���������{����B<BR>
     * <BR>
     * �u�i�敨�ԍϓ��́j�ʕԍψꗗ�\���v�Q�ƁB<BR>
     * ==========================================================<BR>
     * �V�[�P���X�}(�u�i�敨�ԍϓ��́j�ʕԍψꗗ�\���v):<BR>
     *  8 get����(Institution, String, String, String, double, String, String)<BR>
     * (�����R�[�h�`�F�b�N)<BR>
     * get�����Ŏw��̖����R�[�h���擾�ł��Ȃ������ꍇ�́A<BR>
     * �u�����R�[�h�`�F�b�N�G���[�v�̗�O��throw����B<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00301<BR>
     * ==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3Futures�hndividualCloseMarginListResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A9929202D7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_inRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesIndividualCloseMarginListRequest l_inRequest = null;
        if (l_request instanceof WEB3FuturesIndividualCloseMarginListRequest)
        {
            l_inRequest = (WEB3FuturesIndividualCloseMarginListRequest) l_request;
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_inRequest.validate();

        WEB3FuturesIndividualCloseMarginListResponse l_response = (WEB3FuturesIndividualCloseMarginListResponse) l_inRequest.createResponse();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl) l_tradingModule.getPositionManager();
        log.debug("get : l_positionManager = " + l_positionManager);
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        log.debug("get : l_productMgr = " + l_productMgr);
        try
        {
            WEB3IfoContractImpl l_contractFirst = (WEB3IfoContractImpl) l_positionManager.getContract(Long.parseLong(l_inRequest.id[0]));
            IfoProduct l_productFirst = (IfoProduct)l_contractFirst.getProduct();
            WEB3GentradeTradingTimeManagement.resetProductCode(l_productFirst.getUnderlyingProductCode());
            //�⏕�������擾����B
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
            log.debug("get : l_subAccount = " + l_subAccount);



            //�ԍϒ�����t�R�������{����B
            WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();
            log.debug("get : l_orderMgr = " + l_orderMgr);

            l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);
            log.debug("l_orderMgr.validateOrder ended!! >>>>");

            ArrayList l_lstContractReferenceUnits = new ArrayList();

            for (int i = 0; i < l_inRequest.id.length; i++)
            {
                //�w�茚��ID�ɊY�����錚�ʃI�u�W�F�N�g���擾����B
                WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl) l_positionManager.getContract(Long.parseLong(l_inRequest.id[i])); //throw NotFoundException
                log.debug("get : l_contract = " + l_contract);
                //�����I�u�W�F�N�g���擾����B
                WEB3IfoProductImpl l_tmpProduct = (WEB3IfoProductImpl) l_productMgr.getProduct(l_contract.getProduct().getProductId());
                try
                {
                    log.debug("l_subAccount.getInstitution() = " + l_subAccount.getInstitution().getInstitutionCode());
                    log.debug("get : l_contract.getProduct().getProductId() = " + l_contract.getProduct().getProductId());
                    WEB3IfoProductImpl l_product = l_productMgr.getIfoProduct(l_subAccount.getInstitution(), l_tmpProduct.getProductCode());

                    log.debug("get : l_product = " + l_product);
                }
                catch (NotFoundException l_wex)
                {
                    //�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
                    throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME);
                }

                //�����ς̌��ʏƉ�ׂ�1���ׂ��쐬����B
                l_positionManager.createFuturesUnSettledContractInquiryDetails(l_lstContractReferenceUnits, //���ʏƉ��
                l_contract //�敨OP����
                );

            }

            log.debug("l_lstContractReferenceUnits.size() = " + l_lstContractReferenceUnits.size());
            WEB3FuturesContractReferenceUnit[] l_contractReferenceUnits = new WEB3FuturesContractReferenceUnit[l_lstContractReferenceUnits.size()];
            l_lstContractReferenceUnits.toArray(l_contractReferenceUnits);
            log.debug("l_contractReferenceUnits.length = " + l_contractReferenceUnits.length);
            List l_lstContractUnits = new ArrayList();

            for (int i = 0; i < l_contractReferenceUnits.length; i++)
            {
                //�w��̌��ʏƉ�ׂ��猚�ʖ��ׂ��쐬����B
                WEB3FuturesOptionsContractUnit l_contractUnit = this.createContractUnit(l_contractReferenceUnits[i]);

                l_lstContractUnits.add(l_contractUnit);
                log.debug("get :l_contractUnit = " + l_contractUnit);
            }

            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
            l_lstContractUnits.toArray(l_contractUnits);
            log.debug("before sorted :l_contractUnits = " + l_contractUnits);

            //�w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č��ʖ��ׂ̃\�[�g���s���B
            l_contractUnits = sortContractUnit(l_contractUnits, l_inRequest.futOpSortKeys);
            log.debug("after sorted :l_contractUnits = " + l_contractUnits);

            //���X�|���X.������ = ���ʏƉ��[0].������
            l_response.futProductName = l_contractReferenceUnits[0].futProductName;
            log.debug("set :l_response.futProductName = " + l_response.futProductName);
            //���X�|���X.���敪 = ���ʏƉ��[0].���敪
            l_response.contractType = l_contractReferenceUnits[0].contractType;
            log.debug("set :l_response.contractType = " + l_response.contractType);
            //���X�|���X.����s�� = ���ʏƉ��[0].����s��
            l_response.marketCode = l_contractReferenceUnits[0].marketCode;
            log.debug("set :l_response.marketCode = " + l_response.marketCode);
            l_response.messageSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.FUTURES);

            WEB3IfoContractImpl l_contract0 = (WEB3IfoContractImpl) l_positionManager.getContract(Long.parseLong(l_contractReferenceUnits[0].id));
            log.debug("l_contract0 = " + l_contract0);

            //���X�|���X.���ݒl = get���ʎ����̕Ԃ�l
            double l_dblCurrentPrice = l_positionManager.getContractCurrentPrice(l_contract0);
            if (Double.isNaN(l_dblCurrentPrice))
            {
                l_dblCurrentPrice = 0D;
            }
            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
            log.debug("get :l_positionManager.getContractCurrentPrice(l_contract0) = " + l_positionManager.getContractCurrentPrice(l_contract0));
            log.debug("set :l_response.currentPrice = " + l_response.currentPrice);
            //���X�|���X.���ʖ��� = sort���ʖ��ׂ̕Ԃ�l
            l_response.contractUnits = l_contractUnits;

        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (create���ʖ���)<BR>
     * �w��̌��ʏƉ�ׂ��猚�ʖ��ׂ��쐬����B<BR>
     * <BR>
     * (1)���ʖ��ׂ̐���<BR>
     * ���ʖ��׃I�u�W�F�N�g�𐶐�����<BR>
     * <BR>
     * (2)�v���p�e�B�̃Z�b�g<BR>
     * �ȉ��̃v���p�e�B�Z�b�g���s��<BR>
     * <BR>
     * ���ʖ���.ID = �p�����[�^.���ʏƉ��.ID<BR>
     * ���ʖ���.���� = �p�����[�^.���ʏƉ��.����<BR>
     * ���ʖ���.���ʐ� = �p�����[�^.���ʏƉ��.������<BR>
     * ���ʖ���.���P�� = �p�����[�^.���ʏƉ��.���P��<BR>
     * ���ʖ���.�������z = �p�����[�^.���ʏƉ��.�������z<BR>
     * ���ʖ���.���萔�� = �p�����[�^.���ʏƉ��.���萔��<BR>
     * ���ʖ���.���v = �p�����[�^.���ʏƉ��.���v<BR>
     * ���ʖ���.���v(���o�) = �p�����[�^.���ʏƉ��.���v(���o�)<BR>
     * ���ʖ���.�ԍϐ��� = NULL<BR>
     * ���ʖ���.�ԍϖ�萔�� = NULL<BR>
     * ���ʖ���.���Ϗ��� = NULL<BR>
     * ���ʖ���.����敪 = �p�����[�^.���ʏƉ��.����敪<BR>
     * <BR>
     * (3)(2)�̌��ʖ��׃I�u�W�F�N�g��ԋp����<BR>
     * @@param l_contractReferenceUnit - (���ʏƉ��)<BR>
     * �����w���敨���ʏƉ�׃I�u�W�F�N�g
     * @@return WEB3FuturesOptionsContractUnit
     * @@roseuid 40A9929202F7
     */
    protected WEB3FuturesOptionsContractUnit createContractUnit(WEB3FuturesContractReferenceUnit l_contractReferenceUnit)
    {
        final String STR_METHOD_NAME = "createContractDetails(WEB3FuturesContractReferenceUnit l_contractReferenceDetails)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesOptionsContractUnit l_contractUnit = new WEB3FuturesOptionsContractUnit();

        l_contractUnit.id = l_contractReferenceUnit.id; //ID
        l_contractUnit.contractPrice = l_contractReferenceUnit.contractPrice; //���P��
        l_contractUnit.openDate = WEB3DateUtility.toDay(l_contractReferenceUnit.openDate); //����
        l_contractUnit.contractQuantity = l_contractReferenceUnit.contractOrderQuantity; //���ʐ�
        l_contractUnit.contractExecPrice = l_contractReferenceUnit.contractExecPrice; //�������z
        l_contractUnit.contractCommission = l_contractReferenceUnit.contractCommission; //���萔��
        l_contractUnit.income = l_contractReferenceUnit.income; //���v
        l_contractUnit.incomeCost = l_contractReferenceUnit.incomeCost; //���v(���o�)
        l_contractUnit.contractOrderQuantity = null; //�ԍϐ���
        l_contractUnit.contractExecQuantity = null; //�ԍϖ�萔��
        l_contractUnit.settlePriority = null; //���Ϗ���
        l_contractUnit.sessionType = l_contractReferenceUnit.sessionType; //����敪

        log.debug("l_contractUnit.id = " + l_contractUnit.id);
        log.debug("l_contractUnit.contractPrice = " + l_contractUnit.contractPrice);
        log.debug("l_contractUnit.openDate = " + l_contractUnit.openDate);
        log.debug("l_contractUnit.contractQuantity = " + l_contractUnit.contractQuantity);
        log.debug("l_contractUnit.contractExecPrice = " + l_contractUnit.contractExecPrice);
        log.debug("l_contractUnit.contractCommission = " + l_contractUnit.contractCommission);
        log.debug("l_contractUnit.income = " + l_contractUnit.income);
        log.debug("l_contractUnit.incomeCost = " + l_contractUnit.incomeCost);
        log.debug("l_contractUnit.sessionType = " + l_contractUnit.sessionType);
        log.exiting(STR_METHOD_NAME);

        return l_contractUnit;
    }

    /**
     * (sort���ʖ���)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č��ʖ��ׂ̃\�[�g���s���B<BR>
     * <BR>
     * (1)ArrayList���쐬<BR>
     * <BR>
     * (2)�p�����[�^.�\�[�g�L�[�̔z�񐔕�Loop����<BR>
     * �@@(2-1)�p�����[�^.�\�[�g�L�[.�L�[���ڂ��擾<BR>
     * <BR>
     * �@@(2-2)�p�����[�^.�\�[�g�L�[.����/�~�����擾<BR>
     * <BR>
     * �@@(2-3)�L�[���ڂɂ�镪�򏈗�<BR>
     * �@@�@@�L�[���ڂ������ł������ꍇ�A<BR>
     * �@@�@@�敨����Comparator�A�敨����敪Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=(2-2)�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@(2-4)(2-3)�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * (3)ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * (4)Comparator�̔z�񏇂̃\�[�g����<BR>
     * (web3-common)WEB3ArraysUtility.sort(�p�����[�^.���ʖ��ׁA<BR>Comparator[])<BR>
     * <BR>
     * (5)�\�[�g���ꂽ���ʖ��ׂ̔z���ԋp<BR>
     * @@param l_contractUnit - (���ʖ���)<BR>
     * ���ʖ��ׂ̔z��
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * �����w���敨�I�v�V�����\�[�g�L�[�̔z��
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@roseuid 40A992920306
     */
    protected WEB3FuturesOptionsContractUnit[] sortContractUnit(WEB3FuturesOptionsContractUnit[] l_contractUnit, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = " sortContractUnit(WEB3FuturesOptionsContractUnit[] l_contractUnit, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {

            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;
            log.debug("l_sortKey[" + i + "].keyItem = " + l_strKeyItem);
            log.debug("l_sortKey[" + i + "].ascDesc = " + l_strAscDesc);

            Comparator l_com = null;

            if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                //�L�[���ڂ������ł������ꍇ�A�敨����Comparator�A�敨����敪Comparator�𐶐�
                l_com = new WEB3FuturesOpenDateComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
                l_com = new WEB3FuturesSessionTypeComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
            }
        }

        log.debug("l_lstComparators.size() = " + l_lstComparators.size());
        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);
        log.debug("l_comparators succeed!>>>>");

        WEB3ArraysUtility.sort(l_contractUnit, l_comparators);
        log.debug("sort completed!>>>>");
        log.exiting(STR_METHOD_NAME);

        return l_contractUnit;
    }

}
@
