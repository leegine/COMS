head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderReceiveHistoryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғ�����t�����Ɖ�T�[�r�XImpl(WEB3AdminBondOrderReceiveHistoryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.217
Revision History : 2007/08/24 ���g (���u) �d�l�ύX�E���f��No.253
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.bd.WEB3BondBranchCondition;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.data.BondOrderAcceptActionRow;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryRequest;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryResponse;
import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.bd.message.WEB3BondOrderAcceptHistoryUnit;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderReceiveHistoryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchRecruitLimitDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��Ғ�����t�����Ɖ�T�[�r�XImpl)<BR>
 * �Ǘ��Ғ�����t�����Ɖ�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminBondOrderReceiveHistoryServiceImpl implements WEB3AdminBondOrderReceiveHistoryService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderReceiveHistoryServiceImpl.class);

    /**
     * @@roseuid 46A473FC02BF
     */
    public WEB3AdminBondOrderReceiveHistoryServiceImpl()
    {

    }

    /**
     * �Ǘ��Ғ�����t�����Ɖ�T�[�r�X�����s����B<BR>
     * <BR>
     * �V�[�P���X�}�F�u�Ǘ��Ғ�����t�����Ɖ�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ғ�����t�����Ɖ�N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 468895EB0227
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        if (l_request instanceof WEB3AdminBondOrderReceiveHistoryRequest)
        {
            WEB3AdminBondOrderReceiveHistoryRequest l_historyRequest =
                (WEB3AdminBondOrderReceiveHistoryRequest)l_request;

            //validate( )
            l_historyRequest.validate();

            //getInstanceFrom���O�C�����( )
            WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

            //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
            //[����]
            // �@@�\�J�e�S���R�[�h�@@�F�@@�@@�\�J�e�S���R�[�h�D���i�����Ǘ��j
            // is�X�V�@@�F�@@false
            l_administrator.validateAuthority(
                WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
                false);

            //get�،���ЃR�[�h( )
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            //get���X�R�[�h( )
            String l_strBranchCode = l_administrator.getBranchCode();

            //get���X(�،���ЃR�[�h : String, ���X�R�[�h : String)
            //[����]
            //�،���ЃR�[�h�F�@@�擾�����،���ЃR�[�h
            //���X�R�[�h�F�@@�@@�@@�@@�擾�������X�R�[�h
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.BOND);

            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Branch l_branch = null;
            try
            {
                l_branch = l_gentradeAccountManager.getWeb3GenBranch(
                    l_strInstitutionCode,
                    l_strBranchCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //getBranchId( )
            long l_lngBranchId = l_branch.getBranchId();

            //�����X�ʏ���(long)
            //[����]
            //�@@���XID�F�擾�������XID
            WEB3BondBranchCondition l_bondBranchCondition =
                new WEB3BondBranchCondition(l_lngBranchId);

            //get����g���X�ʊǗ��敪( )
            String l_strBranchRecruitLimitDiv =
                l_bondBranchCondition.getBranchRecruitLimitDiv();

            //�����򏈗���get����g���X�ʊǗ��敪�i�j�̖߂�l == �h���X�ʊǗ�����h�@@�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ�
            if (WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT.equals(l_strBranchRecruitLimitDiv))
            {
                //validate���X����(���X�R�[�h : String)
                //[����]
                // ���X�R�[�h�@@�F�@@���N�G�X�g�f�[�^�D���X�R�[�h
                l_administrator.validateBranchPermission(l_historyRequest.branchCode);
            }

            //get������(long)
            //[����]
            // ����ID�@@�F�@@���N�G�X�g�f�[�^�D����ID
            WEB3BondProductManager l_productManager =
                (WEB3BondProductManager)l_tradingModule.getProductManager();
            long l_lngProductID = Long.parseLong(l_historyRequest.productID);
            WEB3BondProduct l_bondProduct =
                (WEB3BondProduct)l_productManager.getBondProduct(l_lngProductID);

            //create���������X�ʉ���g���(long, String, String)
            //[����]
            // ����ID�@@�F�@@���N�G�X�g�f�[�^�D����ID
            // �،���ЃR�[�h�@@�F�@@�擾�����،���ЃR�[�h
            // ���X�R�[�h�@@�F�@@���N�G�X�g�f�[�^�D���X�R�[�h
            WEB3BondDomesticBranchRecruitLimitInfo[] l_branchRecruitLimitInfos =
                l_productManager.createAdminBondDomesticRecruitLimitInfo(
                    l_lngProductID,
                    l_strInstitutionCode,
                    l_historyRequest.branchCode);

            //get��������t�����ꗗ(String, String, String)
            //[����]
            // ����ID�@@�F�@@���N�G�X�g�f�[�^�D����ID
            // �،���ЃR�[�h�@@�F�@@�擾�����،���ЃR�[�h
            // ���X�R�[�h�@@�F�@@���N�G�X�g�f�[�^�D���X�R�[�h
            List l_lisBondOrderReceiveHistorys =
                l_productManager.getBondOrderReceiveHistoryList(
                    l_historyRequest.productID,
                    l_strInstitutionCode,
                    l_historyRequest.branchCode);

            //get��������t�����ꗗ�@@�̖߂�l�̗v�f�����@@LOOP����
            int l_intCnt = l_lisBondOrderReceiveHistorys.size();
            WEB3BondOrderAcceptHistoryUnit[] l_orderAcceptHistorys =
                new WEB3BondOrderAcceptHistoryUnit[l_intCnt];

            for (int i = 0; i < l_intCnt; i++)
            {
                //������t����( )
                l_orderAcceptHistorys[i] = new WEB3BondOrderAcceptHistoryUnit();

                //�v���p�e�B�Z�b�g
                BondOrderAcceptActionRow l_orderAcceptActionRow =
                    (BondOrderAcceptActionRow)l_lisBondOrderReceiveHistorys.get(i);

                //������t���F��������t�����s�D������t���t
                l_orderAcceptHistorys[i].orderDate =
                    l_orderAcceptActionRow.getOrderAcceptDate();

                //�������z�@@ �F��������t�����s�D�������z
                if (l_orderAcceptActionRow.getOrderAmountIsNull())
                {
                    l_orderAcceptHistorys[i].orderAmount = "";
                }
                else
                {
                    l_orderAcceptHistorys[i].orderAmount =
                        WEB3StringTypeUtility.formatNumber(l_orderAcceptActionRow.getOrderAmount());
                }

                //���������@@ �F��������t�����s�D��������
                if (l_orderAcceptActionRow.getOrderCountIsNull())
                {
                    l_orderAcceptHistorys[i].orderNumber = "";
                }
                else
                {
                    l_orderAcceptHistorys[i].orderNumber =
                        l_orderAcceptActionRow.getOrderCount() + "";
                }

                //�݌v�@@�@@�@@�@@ �F��������t�����s�D�������z�݌v
                if ( l_orderAcceptActionRow.getTotalOrderAmountIsNull())
                {
                    l_orderAcceptHistorys[i].accumulatedTotal = "";
                }
                else
                {
                    l_orderAcceptHistorys[i].accumulatedTotal =
                        WEB3StringTypeUtility.formatNumber(l_orderAcceptActionRow.getTotalOrderAmount());
                }
            }

            //createResponse( )
            WEB3AdminBondOrderReceiveHistoryResponse l_response =
                (WEB3AdminBondOrderReceiveHistoryResponse)l_historyRequest.createResponse();

            //�������@@�@@�@@�F�@@�������I�u�W�F�N�g�D������
            l_response.productName = l_bondProduct.getProductName();

            //����J�n���F�@@�������I�u�W�F�N�g�D�戵�J�n����
            l_response.recruitStartDate = l_bondProduct.getTradeStartDate();

            //����I�����F�@@�������I�u�W�F�N�g�D�戵�I������
            l_response.recruitEndDate = l_bondProduct.getTradeEndDate();

            //������t�����F�@@�쐬����������t�����̔z��
            l_response.orderAcceptHistory = l_orderAcceptHistorys;

            //���������X�ʉ���g���F�@@�쐬�������������X�ʉ���g���̔z��̂P�߂̗v�f
            l_response.bondDomesticBranchRecruitLimitInfo = l_branchRecruitLimitInfos[0];

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }
    }
}
@
