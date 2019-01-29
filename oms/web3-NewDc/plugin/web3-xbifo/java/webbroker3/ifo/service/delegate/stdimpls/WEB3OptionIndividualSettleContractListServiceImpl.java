head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionIndividualSettleContractListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�ʕԍψꗗ�\���T�[�r�XImpl(WEB3OptionIndividualSettleContractListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/24 ����� (���u) �V�K�쐬
              001: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
              002: 2004/08/14 ���Ō� �Ή��o�O BUG137
              003: 2004/08/15 ���Ō� �Ή��o�O BUG83
Revesion History : 2007/06/11 �����F ���f���@@690
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
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsOpenDateComparator;
import webbroker3.ifo.message.WEB3OptionsContractReferenceUnit;
import webbroker3.ifo.message.WEB3OptionsIndividualCloseMarginListRequest;
import webbroker3.ifo.message.WEB3OptionsIndividualCloseMarginListResponse;
import webbroker3.ifo.message.WEB3OptionsSessionTypeComparator;
import webbroker3.ifo.service.delegate.WEB3OptionIndividualSettleContractListService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (OP�ʕԍψꗗ�\���T�[�r�XImpl)<BR>
 * <BR>
 * �����w���I�v�V�����ʕԍψꗗ�\���T�[�r�X�����N���X<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3OptionIndividualSettleContractListServiceImpl extends WEB3OptionClientRequestService
    implements WEB3OptionIndividualSettleContractListService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionIndividualSettleContractListServiceImpl.class);


    /**
     * �����w���I�v�V�����ʕԍψꗗ�\���T�[�r�X���������{����B<BR>
     * <BR>
     * �u�iOP�ԍϓ��́j�ʕԍψꗗ�\���v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4083BDA00149
     */
    public WEB3GenResponse execute(WEB3GenRequest l_inRequest) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_inRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsIndividualCloseMarginListRequest l_request = (WEB3OptionsIndividualCloseMarginListRequest)l_inRequest;

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        WEB3OptionsIndividualCloseMarginListResponse l_response = (WEB3OptionsIndividualCloseMarginListResponse)l_request.createResponse();



        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        //�ԍϒ�����t�R�������{����B
        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();

        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

        try
        {
            WEB3IfoContractImpl l_contractFirst = (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_request.id[0])); //throw NotFoundException
            IfoProduct l_productFirst = (IfoProduct)l_contractFirst.getProduct();
            WEB3GentradeTradingTimeManagement.resetProductCode(l_productFirst.getUnderlyingProductCode());
            //�⏕�������擾����B
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
            l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
            ArrayList l_lstContractReferenceUnits = new ArrayList();

            for (int i = 0; i < l_request.id.length; i++)
            {
                //�w�茚��ID�ɊY�����錚�ʃI�u�W�F�N�g���擾����B
                WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_request.id[i])); //throw NotFoundException

                //�����I�u�W�F�N�g���擾����B
                WEB3IfoProductImpl l_tmpProduct =  (WEB3IfoProductImpl)l_productMgr.getProduct(l_contract.getProduct().getProductId());
                try
                {

                    WEB3IfoProductImpl l_product = l_productMgr.getIfoProduct(l_subAccount.getInstitution(), l_tmpProduct.getProductCode());

                }
                catch (NotFoundException l_nfex)
                {
                    //�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                              this.getClass().getName() + STR_METHOD_NAME);
                }

                //�����ς̌��ʏƉ�ׂ�1���ׂ��쐬����B
                l_positionManager.createOptionUnSettledContractInquiryDetails(
                    l_lstContractReferenceUnits, //���ʏƉ��
                    l_contract                   //�敨OP����
                    );

            }

            WEB3OptionsContractReferenceUnit[] l_contractReferenceUnits =
                new WEB3OptionsContractReferenceUnit[l_lstContractReferenceUnits.size()];
            l_lstContractReferenceUnits.toArray(l_contractReferenceUnits);

            List l_lstContractUnits = new ArrayList();

            for (int i = 0; i < l_contractReferenceUnits.length; i++)
            {
                //�w��̌��ʏƉ�ׂ��猚�ʖ��ׂ��쐬����B
                WEB3FuturesOptionsContractUnit l_contractUnit = this.createContractDetails(l_contractReferenceUnits[i]);

                l_lstContractUnits.add(l_contractUnit);

            }

            WEB3FuturesOptionsContractUnit[] l_contractUnits =
                new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
            l_lstContractUnits.toArray(l_contractUnits);


            //�w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č��ʖ��ׂ̃\�[�g���s���B
            l_contractUnits = sortContractDetails(l_contractUnits, l_request.futOpSortKeys);


            //���X�|���X.������ = ���ʏƉ��[0].������
            l_response.opProductName = l_contractReferenceUnits[0].opProductName;

            //���X�|���X.���敪 = ���ʏƉ��[0].���敪
            l_response.contractType = l_contractReferenceUnits[0].contractType;

            //���X�|���X.����s�� = ���ʏƉ��[0].����s��
            l_response.marketCode = l_contractReferenceUnits[0].marketCode;


            WEB3IfoContractImpl l_contract0 = (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_contractReferenceUnits[0].id));

            //���X�|���X.���ݒl = get���ʎ����̕Ԃ�l
            double l_dblCurrentPrice = l_positionManager.getContractCurrentPrice(l_contract0);
            if (Double.isNaN(l_dblCurrentPrice))
            {
                l_dblCurrentPrice = 0D;
            }

            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);

            //���X�|���X.���ʖ��� = sort���ʖ��ׂ̕Ԃ�l
            l_response.contractUnits = l_contractUnits;


            //�s��ǌx���w��
            String[] l_strTradeCloseSuspensions = null;
            l_strTradeCloseSuspensions = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.OPTION);
            l_response.messageSuspension = l_strTradeCloseSuspensions;

        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (create���ʖ���)<BR>
     * <BR>
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
     * ���ʖ���.���Ϗ��� = NULL<BR>
     * ���ʖ���.�ԍϖ�萔�� = NULL<BR>
     * ���ʖ���.����敪 = �p�����[�^.���ʏƉ��.����敪 <BR>
     * <BR>
     * (3)(2)�̌��ʖ��׃I�u�W�F�N�g��ԋp����<BR>
     * @@param l_contractReferenceUnit - �����w���I�v�V�������ʏƉ�׃I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit
     * @@roseuid 4087B22E0211
     */
    protected WEB3FuturesOptionsContractUnit createContractDetails(WEB3OptionsContractReferenceUnit l_contractReferenceUnit)
    {
        final String STR_METHOD_NAME = "createContractDetails(WEB3OptionsContractReferenceUnit l_contractReferenceDetails)";
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
        l_contractUnit.settlePriority = null; //���Ϗ���
        l_contractUnit.contractExecQuantity = null; //�ԍϖ�萔��
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
     * <BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Č��ʖ��ׂ̃\�[�g���s���B<BR>
     * <BR>
     * (1)ArrayList���쐬<BR>
     * <BR>
     * (2)�p�����[�^.�\�[�g�L�[�̔z�񐔕�Loop����<BR>
     * �@@(2-1)�p�����[�^.�\�[�g�L�[.�L�[���ڂ��擾<BR>
     * <BR>
     * �@@(2-2)�p�����[�^.�\�[�g�L�[.����/�~�����擾<BR>
     * <BR>
     * �@@(2-3)�L�[���ڂɂ�镪�򏈗� <BR>
     * �@@�@@�L�[���ڂ������ł������ꍇ�A <BR>
     * �@@�@@�@@����Comparator�A����敪Comparator�𐶐� <BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^(*)=(2-2)�Ŏ擾��������/�~��] <BR>
     * �@@�@@(*)�p�����[�^�Ɍ����̃L�[���ڂŎ擾�����l��ݒ肷��B<BR>
     * <BR>
     * �@@(2-4)(2-3)�ɂč쐬����Comparator��ArrayList�ɒǉ�<BR>
     * <BR>
     * (3)ArrayList����Comparator�̔z����쐬<BR>
     * <BR>
     * (4)Comparator�̔z�񏇂̃\�[�g����<BR>
     * (web3-common)WEB3ArraysUtility.sort(�p�����[�^.���ʖ��ׁAComparator[])<BR>
     * <BR>
     * (5)�\�[�g���ꂽ���ʖ��ׂ̔z���ԋp<BR>
     * @@param l_contractUnits - ���ʖ��ׂ̔z��
     * @@param l_sortKey - �����w���敨�I�v�V�����\�[�g�L�[�̔z��
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit[]
     * @@roseuid 4087B30402FE
     */
    protected WEB3FuturesOptionsContractUnit[] sortContractDetails(WEB3FuturesOptionsContractUnit[] l_contractUnits, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = " sortContractDetails(WEB3FuturesOptionsContractUnit[] l_contractDetails, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {

            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;


            Comparator l_com = null;

            if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                //�L�[���ڂ������ł������ꍇ�A����Comparator�𐶐�
                l_com = new WEB3OptionsOpenDateComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
                //����敪Comparator�𐶐�
                l_com = new WEB3OptionsSessionTypeComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
            }
        }

        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        WEB3ArraysUtility.sort(l_contractUnits, l_comparators);
        log.exiting(STR_METHOD_NAME);

        return l_contractUnits;
    }
}
@
