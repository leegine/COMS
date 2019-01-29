head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentRequisitionManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ǘ�(WEB3TPPaymentRequisitionManagement.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 �И��� (���u) �d�l�ύX���f��308,316,
Revision History : 2008/10/20 ������ (���u) �d�l�ύX���f��317,329,338
Revision History : 2008/10/22 ���� (���u) �d�l�ύX���f��340,342,344
Revision History : 2008/10/27 ���� (���u) �d�l�ύX���f��346
Revision History : 2008/10/28 �И��� (���u) �d�l�ύX���f��347
Revision History : 2008/10/29 ������ (���u) �d�l�ύX���f��348
Revision History : 2008/10/31 �И��� (���u) �d�l�ύX���f��349,350,354
Revision History : 2008/11/04 ������ (���u) �d�l�ύX���f��357
Revision History : 2008/11/06 �И��� (���u) �d�l�ύX���f��358,359,360,361
Revision History : 2008/11/07 �И��� (���u) �d�l�ύX���f��362
Revision History : 2008/11/12 �O���~��Y (SCS) �d�l�ύX���f��366
Revision History : 2008/11/18 �O���~��Y (SCS) �d�l�ύX���f��368,369
Revision History : 2008/11/21 �O���~��Y (SCS) �d�l�ύX���f��373
Revision History : 2008/11/21 �O���~��Y (SCS) �����̖��007
Revision History : 2009/05/27 �����F (���u) �d�l�ύX���f��390
*/
package webbroker3.tradingpower;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountAttributeDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3TodayClearanceDeterminationDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.data.PaymentRequisitMngDao;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.data.PaymentRequisitMngRow;
import webbroker3.tradingpower.data.TpCashBalanceDao;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.define.WEB3TPMarginEquityJudgeFlagDef;
import webbroker3.tradingpower.define.WEB3TPProcessManagementIdDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���������Ǘ�)<BR>
 * (���������Ǘ�)<BR>
 * <BR>
 * ���������Ǘ�Params�Ǝ��Y�]�͏����A�e��v�Z���s���N���X<BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3TPPaymentRequisitionManagement
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPPaymentRequisitionManagement.class);

    /**
     * (���������Ǘ�Params)<BR>
     * (���������Ǘ�Params)<BR>
     * <BR>
     * ���������Ǘ��e�[�u���̌������ʂ̃f�[�^��ێ�����N���X<BR>
     */
    protected PaymentRequisitMngParams paymentRequisitMngParams;

    /**
     * (�ڋq����c��(�}�X�^���)<�ۏ؋�>)<BR>
     * (�ڋq����c��(�}�X�^���)<�ۏ؋�>)<BR>
     * <BR>
     * �ڋq����c��(�}�X�^���)�e�[�u���̌������ʂ̃f�[�^��ێ�����N���X<BR>
     */
    protected TpCashBalanceParams tpCashBalanceParams;

    /**
     * (���Y�]�͏��<�����ڋq>)<BR>
     * (���Y�]�͏��<�����ڋq>)<BR>
     * <BR>
     * �]�͍X�V����<�����ڋq>���A�e�����\�z���v�Z����N���X<BR>
     * �i�M�p�ڋq�̏ꍇ�Anull��ݒ�j<BR>
     */
    protected WEB3TPTradingPowerCalcEquity tpCalcEquity;

    /**
     * (���Y�]�͏��<�M�p�ڋq>)<BR>
     * (���Y�]�͏��<�M�p�ڋq>)<BR>
     * <BR>
     * �]�͍X�V����<�M�p�ڋq>���A�e�����\�z���v�Z����N���X<BR>
     * �i�����ڋq�̏ꍇ�Anull��ݒ�j<BR>
     */
    protected WEB3TPTradingPowerCalcMargin tpCalcMargin;

    /**
     * (���o�������P�ʈꗗ(�a����˕ۏ؋�))<BR>
     * (���o�������P�ʈꗗ(�a����˕ۏ؋�))<BR>
     * <BR>
     * ��n����T+0�ȍ~�ŁA�i�a����˕ۏ؋��j�U�ւ̃f�[�^��ێ�����List<BR>
     */
    protected List aioOrderUnitListFromDepositToMargins;

    /**
     * (���o�������P�ʈꗗ(�ۏ؋��˗a���))<BR>
     * (���o�������P�ʈꗗ(�ۏ؋��˗a���))<BR>
     * <BR>
     * ��n����T+0�ȍ~�ŁA�i�ۏ؋��˗a����j�U�ւ̃f�[�^��ێ�����List<BR>
     */
    protected List aioOrderUnitListFromMarginToDeposits;

    /**
     * (�M�p��������t���O)<BR>
     * (�M�p��������t���O)<BR>
     * <BR>
     * �M�p�ڋq�ƌ����ڋq�̔�����s���t���O<BR>
     * 0�F�����ڋq<BR>
     * 1�F�M�p�ڋq<BR>
     */
    protected String marginEquityJudgeFlag;

    /**
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * <BR>
     * �ۏ؋������U�֑O��̔�����s���t���O<BR>
     * false�F�ۏ؋������U�֑O<BR>
     * true �F�ۏ؋������U�֌�<BR>
     */
    protected boolean depositAutoTransferDivFlag;

    /**
     * (�����Ǐؔ����l���t���O)<BR>
     * (�����Ǐؔ����l���t���O)<BR>
     * <BR>
     * �Ǐؔ����̔��f�̍ہA�Ǐ؋��z���l�����邩�ǂ����𔻒肷��t���O�B<BR>
     * <BR>
     * true�F�@@�ۏ؋������U�֑O�́A�Ǐ؋��z�̔����������ĒǏؔ����Ƃ���B<BR>
     * false�F�@@�ۏ؋������U�֑O��ɂ�����炸�A�Ǐؖ��������z�̔����������ĒǏؔ����Ƃ���B<BR>
     */
    protected boolean firstOpenAdddepositFlag = false;

    /**
     * (���������Ǘ�)<BR>
     * (�R���X�g���N�^)<BR>
     * @@roseuid 486C243503D4
     */
    public WEB3TPPaymentRequisitionManagement()
    {

    }

    /**
     * (create���������Ǘ�)<BR>
     * (static���\�b�h)(create���������Ǘ�)<BR>
     * <BR>
     * ���������Ǘ��C���X�^���X���쐬���� <BR>
     * <BR>
     * �P�j�@@create���������Ǘ�()���R�[�����A�߂�l��ԋp����B<BR>
     * [����]<BR>
     * �E�ڋq�F�@@����.�ڋq<BR>
     * �E�����Ǐؔ����l���t���O�F�@@false<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * (�ڋq)<BR>
     * @@return WEB3TPPaymentRequisitionManagement
     * @@throws WEB3BaseException
     */
    public static WEB3TPPaymentRequisitionManagement createPaymentRequisitionManagement(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        return createPaymentRequisitionManagement(l_mainAccount, false);
    }

    /**
     * (create���������Ǘ�)<BR>
     * (static���\�b�h)(create���������Ǘ�)<BR>
     * <BR>
     * ���������Ǘ��C���X�^���X���쐬���� <BR>
     * <BR>
     * ���V�[�P���X�}�u(���������Ǘ�)create���������Ǘ��v�Q��<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * (�ڋq)<BR>
     * @@param l_blnFirstOpenAdddepositFlag - (�����Ǐؔ����l���t���O)<BR>
     * (�����Ǐؔ����l���t���O)<BR>
     * @@return WEB3TPPaymentRequisitionManagement
     * @@roseuid 486C243503D5
     * @@throws WEB3BaseException
     */
    public static WEB3TPPaymentRequisitionManagement createPaymentRequisitionManagement(
        MainAccount l_mainAccount,
        boolean l_blnFirstOpenAdddepositFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createPaymentRequisitionManagement(MainAccount, boolean)";
        log.entering(STR_METHOD_NAME);

        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            new WEB3TPPaymentRequisitionManagement();

        //getAccountId()
        long l_lngAccountId = l_mainAccount.getAccountId();

        //���������Ǘ��e�[�u��������
        //�m���������n
        //�@@�@@�E����ID�@@=�@@getAccountId()�̖߂�l
        PaymentRequisitMngRow l_paymentRequisitMngRow = null;
        try
        {
            l_paymentRequisitMngRow =
                PaymentRequisitMngDao.findRowByAccountId(l_lngAccountId);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�،���ЃI�u�W�F�N�g���擾����B
        Institution l_institution = l_mainAccount.getInstitution();
        //���X�I�u�W�F�N�g���擾����B
        Branch l_branch = l_mainAccount.getBranch();

        //�ۏ؋������U�֌ォ�ǂ������肷��B
        //[����]
        //�،���ЃR�[�h�F�@@getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h�F�@@getBranch().getBranchCode()�̖߂�l
        boolean l_blnDepositAutoTransfer =
            isDepositAutoTransfer(l_institution.getInstitutionCode(), l_branch.getBranchCode());

        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_mainAccount;
        //is�M�p�����J��(�ٍϋ敪 : String)
        boolean l_blnIsMarginAccountEstablished =
            l_gentradeMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        WEB3GentradeSubAccount l_subAccount = null;

        //is�M�p�����J��()�̖߂�l == true(�M�p�ڋq)�̏ꍇ�A
        //�ȉ��̏��������{����B
        if (l_blnIsMarginAccountEstablished)
        {
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = null;
            try
            {
                //getSubAccount
                //[����]
                //2�F�@@�����M�p��������i�ۏ؋��j
                l_subAccount =
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //create�]�͌v�Z����<�W��>(�⏕����)
            WEB3TPCalcCondition l_tpCalcCondition =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //find�]�͌v�Z����<�M�p�ڋq>�`�����h�c�w��`(long)
            List l_lisCalcResultMarginParams =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //���Y�]�͏��<�M�p�ڋq>(List, �]�͌v�Z����)
            l_tpCalcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResultMarginParams, l_tpCalcCondition);

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                //�ڋq����c��(�}�X�^���)�e�[�u��������
                //�m���������n
                //�@@�@@�E����ID�@@�@@�@@�@@=�@@getAccountId()�̖߂�l
                //�@@�@@�E�⏕����ID�@@=�@@
                //getSubAccount(�g2:�����M�p��������i�ۏ؋��j�h).getSubAccountId()�̖߂�l
                TpCashBalanceRow l_tpCashBalanceRow =
                    TpCashBalanceDao.findRowByAccountIdSubAccountId(
                        l_lngAccountId, l_subAccount.getSubAccountId());

                //���o�������P�ʃe�[�u���������i�a����˕ۏ؋��j
                //�P�j�@@���o�������P�ʃe�[�u������������B
                //�@@�m���������n
                //�@@�@@�E����ID�@@�@@�@@�@@=�@@getAccountId()�̖߂�l
                //�@@�@@�E�⏕����ID�@@=�@@getSubAccount(�g2:�����M�p��������i�ۏ؋��j�h).getSubAccountId()�̖߂�l
                //�@@�@@�E������ʁ@@�@@ =�@@�h1005�h�i�U�֒���(�a�������M�p�ۏ؋�)�j
                //�@@�@@�E��n���@@>=�@@���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.get�c�Ɠ�(T+0)
                //�@@�@@�E������� NOT IN(�g6:�������s(�V�K����)�h, �g14:������(�������)�h)
                //�Q�j�@@�������ʂ̌������AList�ɐݒ肷��B
                StringBuffer l_sbWhereDeposit = new StringBuffer();
                l_sbWhereDeposit.append(" account_id = ? ");
                l_sbWhereDeposit.append(" and sub_account_id = ? ");
                l_sbWhereDeposit.append(" and order_type = ? ");
                l_sbWhereDeposit.append(" and delivery_date >= ? ");
                l_sbWhereDeposit.append(" and order_status not in(?, ?) ");
                Object[] l_aioWhereDeposits =
                    {new Long(l_lngAccountId),
                    new Long(l_subAccount.getSubAccountId()),
                    OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                    l_tpCalcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0),
                    OrderStatusEnum.NOT_ORDERED,
                    OrderStatusEnum.CANCELLED};

                List l_lisAioOrderUnitDeposits = l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_sbWhereDeposit.toString(),
                    l_aioWhereDeposits);

                //���o�������P�ʃe�[�u���������i�ۏ؋��˗a����j
                //�P�j�@@���o�������P�ʃe�[�u������������B
                //�@@�m���������n
                //�@@�@@�E����ID�@@�@@�@@�@@=�@@getAccountId()�̖߂�l
                //�@@�@@�E�⏕����ID�@@=�@@getSubAccount(�g2:�����M�p��������i�ۏ؋��j�h).getSubAccountId()�̖߂�l
                //�@@�@@�E������ʁ@@�@@ =�@@�h1006�h�i�U�֒���(�M�p�ۏ؋�����a���)�j
                //�@@�@@�E��n���@@>=�@@���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.get�c�Ɠ�(T+0)
                //�@@�@@�E������� NOT IN(�g6:�������s(�V�K����)�h, �g14:������(�������)�h)
                //�Q�j�@@�������ʂ̌������AList�ɐݒ肷��B
                StringBuffer l_sbWhereMargin = new StringBuffer();
                l_sbWhereMargin.append(" account_id = ? ");
                l_sbWhereMargin.append(" and sub_account_id = ? ");
                l_sbWhereMargin.append(" and order_type = ? ");
                l_sbWhereMargin.append(" and delivery_date >= ? ");
                l_sbWhereMargin.append(" and order_status not in(?, ?) ");
                Object[] l_aioWhereMargins =
                    {new Long(l_lngAccountId),
                    new Long(l_subAccount.getSubAccountId()),
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                    l_tpCalcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0),
                    OrderStatusEnum.NOT_ORDERED,
                    OrderStatusEnum.CANCELLED};

                List l_lisAioOrderUnitMargins = l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_sbWhereMargin.toString(),
                    l_aioWhereMargins);

                //���������Ǘ��̑����ɃZ�b�g����B
                //�@@�m�M�p�ڋq�̏ꍇ�n
                //�@@�@@�@@�Ethis.���������Ǘ�Params�@@=�@@���������Ǘ�Params  ���������Ǘ�Params��0���̏ꍇ��null���Z�b�g
                if (l_paymentRequisitMngRow == null)
                {
                    l_paymentRequisitionManagement.paymentRequisitMngParams = null;
                }
                else
                {
                    l_paymentRequisitionManagement.paymentRequisitMngParams =
                        new PaymentRequisitMngParams(l_paymentRequisitMngRow);
                }
                //�@@�@@�@@�Ethis.�ڋq����c��(�}�X�^���)<�ۏ؋�>�@@=�@@�ڋq����c��(�}�X�^���)Params
                //        �ڋq����c��(�}�X�^���)Params��0���̏ꍇ��null���Z�b�g
                if (l_tpCashBalanceRow == null)
                {
                    l_paymentRequisitionManagement.tpCashBalanceParams = null;
                }
                else
                {
                    l_paymentRequisitionManagement.tpCashBalanceParams =
                        new TpCashBalanceParams(l_tpCashBalanceRow);
                }
                //�@@�@@�@@�Ethis.���Y�]�͏��<�M�p�ڋq>�@@=�@@���Y�]�͏��<�M�p�ڋq>
                l_paymentRequisitionManagement.tpCalcMargin = l_tpCalcMargin;
                //�@@�@@�@@�Ethis.���Y�]�͏��<�����ڋq>�@@=�@@NULL
                l_paymentRequisitionManagement.tpCalcEquity = null;
                //�@@�@@�@@�Ethis.���o�������P�ʈꗗ(�a���)�@@=
                //�@@�@@�@@�@@���o�������P�ʃe�[�u���̌������ʁi�a����˕ۏ؋��j
                l_paymentRequisitionManagement.aioOrderUnitListFromDepositToMargins = l_lisAioOrderUnitDeposits;
                //�@@�@@�@@�Ethis.���o�������P�ʈꗗ(�ۏ؋�)�@@=
                //�@@�@@�@@�@@���o�������P�ʃe�[�u���̌������ʁi�ۏ؋��˗a����j
                l_paymentRequisitionManagement.aioOrderUnitListFromMarginToDeposits = l_lisAioOrderUnitMargins;
                //�@@�@@�@@�Ethis.�M�p��������t���O�@@=�@@�h1�h�i�M�p�ڋq�j
                l_paymentRequisitionManagement.marginEquityJudgeFlag =
                    WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;
                //   �Ethis.�ۏ؋������U�֌㔻��t���O�@@=�@@is�ۏ؋������U�֌�()�̖߂�l
                l_paymentRequisitionManagement.depositAutoTransferDivFlag = l_blnDepositAutoTransfer;
                //   �Ethis.�����Ǐؔ����l���t���O�@@=�@@����.�����Ǐؔ����l���t���O
                l_paymentRequisitionManagement.firstOpenAdddepositFlag = l_blnFirstOpenAdddepositFlag;
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        //is�M�p�����J��()�̖߂�l == false(�����ڋq)�̏ꍇ�A
        //�ȉ��̏��������{����B
        else
        {
            WEB3TPTradingPowerCalcEquity l_tpCalcEquity = null;
            try
            {
                //getSubAccount
                //[����]
                //1�F�@@������������i�a����j
                l_subAccount =
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //create�]�͌v�Z����<�W��>(�⏕����)
            WEB3TPCalcCondition l_tpCalcCondition =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //find�]�͌v�Z����<�����ڋq>�`�����h�c�w��`(long)
            List l_lisCalcResultEquityParams =
                WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

            //���Y�]�͏��<�����ڋq>(List, �]�͌v�Z����)
            l_tpCalcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResultEquityParams, l_tpCalcCondition);

            //���������Ǘ��̑����ɃZ�b�g����B
            //�@@�m�����ڋq�̏ꍇ�n
            //�@@�@@�@@�Ethis.���������Ǘ�Params�@@=�@@���������Ǘ�Params  ���������Ǘ�Params��0���̏ꍇ��null���Z�b�g
            if (l_paymentRequisitMngRow == null)
            {
                l_paymentRequisitionManagement.paymentRequisitMngParams = null;
            }
            else
            {
                l_paymentRequisitionManagement.paymentRequisitMngParams =
                    new PaymentRequisitMngParams(l_paymentRequisitMngRow);
            }
            //�@@�@@�@@�Ethis.�ڋq����c��(�}�X�^���)<�ۏ؋�>�@@=�@@NULL
            l_paymentRequisitionManagement.tpCashBalanceParams = null;
            //�@@�@@�@@�Ethis.���Y�]�͏��<�M�p�ڋq>�@@=�@@NULL
            l_paymentRequisitionManagement.tpCalcMargin = null;
            //�@@�@@�@@�Ethis.���Y�]�͏��<�����ڋq>�@@=�@@���Y�]�͏��<�����ڋq>
            l_paymentRequisitionManagement.tpCalcEquity = l_tpCalcEquity;
            //�@@�@@�@@�Ethis.���o�������P�ʈꗗ(�a���)�@@=�@@NULL
            l_paymentRequisitionManagement.aioOrderUnitListFromDepositToMargins = null;
            //�@@�@@�@@�Ethis.���o�������P�ʈꗗ(�ۏ؋�)�@@=�@@NULL
            l_paymentRequisitionManagement.aioOrderUnitListFromMarginToDeposits = null;
            //�@@�@@�@@�Ethis.�M�p��������t���O�@@=�@@�h0�h�i�����ڋq�j
            l_paymentRequisitionManagement.marginEquityJudgeFlag =
                WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT;
            //   �Ethis.�ۏ؋������U�֌㔻��t���O�@@=�@@is�ۏ؋������U�֌�()�̖߂�l
            l_paymentRequisitionManagement.depositAutoTransferDivFlag = l_blnDepositAutoTransfer;
            //   �Ethis.�����Ǐؔ����l���t���O�@@=�@@����.�����Ǐؔ����l���t���O
            l_paymentRequisitionManagement.firstOpenAdddepositFlag = l_blnFirstOpenAdddepositFlag;
        }
        log.exiting(STR_METHOD_NAME);
        return l_paymentRequisitionManagement;
    }

    /**
     * (get���������Ǘ�Params)<BR>
     * (get���������Ǘ�Params)<BR>
     * <BR>
     * this.���������Ǘ�Params��ԋp����B<BR>
     * @@return PaymentRequisitMngParams
     * @@roseuid 486C243503D7
     */
    public PaymentRequisitMngParams getPaymentRequisitMngParams()
    {
        return this.paymentRequisitMngParams;
    }

    /**
     * (set���������Ǘ�Params)<BR>
     * (set���������Ǘ�Params)<BR>
     * <BR>
     * ����.���������Ǘ�Params��this.���������Ǘ�Params�ɃZ�b�g����B<BR>
     * @@param l_paymentRequisitMngParams - (���������Ǘ�Params)<BR>
     * (���������Ǘ�Params)<BR>
     * @@roseuid 486C243503D8
     */
    public void setPaymentRequisitMngParams(PaymentRequisitMngParams l_paymentRequisitMngParams)
    {
        this.paymentRequisitMngParams = l_paymentRequisitMngParams;
    }

    /**
     * (get�ڋq����c��(�}�X�^���)<�ۏ؋�>)<BR>
     * (get�ڋq����c��(�}�X�^���)<�ۏ؋�>)<BR>
     * <BR>
     * this.�ڋq����c��(�}�X�^���)<�ۏ؋�>��ԋp����B<BR>
     * @@return TpCashBalanceParams
     * @@roseuid 486C243503DA
     */
    public TpCashBalanceParams getTpCashBalanceParams()
    {
        return this.tpCashBalanceParams;
    }

    /**
     * (set�ڋq����c��(�}�X�^���)<�ۏ؋�>)<BR>
     * (set�ڋq����c��(�}�X�^���)<�ۏ؋�>)<BR>
     * <BR>
     * ����.�ڋq����c��(�}�X�^���)Params��this.�ڋq����c��(�}�X�^���)<�ۏ؋�>�ɃZ�b�g����B<BR>
     * @@param l_tpCashBalanceParams - (�ڋq����c��(�}�X�^���)Params)<BR>
     * (�ڋq����c��(�}�X�^���)Params)<BR>
     * @@roseuid 486C243503E4
     */
    public void setTpCashBalanceParams(TpCashBalanceParams l_tpCashBalanceParams)
    {
        this.tpCashBalanceParams = l_tpCashBalanceParams;
    }

    /**
     * (get���Y�]�͏��<�����ڋq>)<BR>
     * (get���Y�]�͏��<�����ڋq>)<BR>
     * <BR>
     * this.���Y�]�͏��<�����ڋq>��ԋp����B<BR>
     * @@return WEB3TPTradingPowerCalcEquity
     * @@roseuid 486C243503E6
     */
    public WEB3TPTradingPowerCalcEquity getTpCalcEquity()
    {
        return this.tpCalcEquity;
    }

    /**
     * (set���Y�]�͏��<�����ڋq>)<BR>
     * (set���Y�]�͏��<�����ڋq>)<BR>
     * <BR>
     * ����.���Y�]�͏��<�����ڋq>��this.���Y�]�͏��<�����ڋq>�ɃZ�b�g����B<BR>
     * @@param l_tpCalcEquity - (���Y�]�͏��<�����ڋq>)<BR>
     * (���Y�]�͏��<�����ڋq>)<BR>
     * @@roseuid 486C243503E7
     */
    public void setTpCalcEquity(WEB3TPTradingPowerCalcEquity l_tpCalcEquity)
    {
        this.tpCalcEquity = l_tpCalcEquity;
    }

    /**
     * (get���Y�]�͏��<�M�p�ڋq>)<BR>
     * (get���Y�]�͏��<�M�p�ڋq>)<BR>
     * <BR>
     * this.���Y�]�͏��<�M�p�ڋq>��ԋp����B<BR>
     * @@return WEB3TPTradingPowerCalcMargin
     * @@roseuid 486C243503E9
     */
    public WEB3TPTradingPowerCalcMargin getTpCalcMargin()
    {
        return this.tpCalcMargin;
    }

    /**
     * (set���Y�]�͏��<�M�p�ڋq>)<BR>
     * (set���Y�]�͏��<�M�p�ڋq>)<BR>
     * <BR>
     * ����.���Y�]�͏��<�M�p�ڋq>��this.���Y�]�͏��<�M�p�ڋq>�ɃZ�b�g����B<BR>
     * @@param l_tpCalcMargin - (���Y�]�͏��<�M�p�ڋq>)<BR>
     * (���Y�]�͏��<�M�p�ڋq>)<BR>
     * @@roseuid 486C243503EA
     */
    public void setTpCalcMargin(WEB3TPTradingPowerCalcMargin l_tpCalcMargin)
    {
        this.tpCalcMargin = l_tpCalcMargin;
    }

    /**
     * (get���o�������P�ʈꗗ(�a����˕ۏ؋�))<BR>
     * (get���o�������P�ʈꗗ(�a����˕ۏ؋�))<BR>
     * <BR>
     * this.���o�������P�ʈꗗ(�a����˕ۏ؋�)��ԋp����B<BR>
     * @@return List
     * @@roseuid 486C243503EC
     */
    public List getAioOrderUnitListFromDepositToMargins()
    {
        return this.aioOrderUnitListFromDepositToMargins;
    }

    /**
     * (set���o�������P�ʈꗗ(�a����˕ۏ؋�))<BR>
     * (set���o�������P�ʈꗗ(�a����˕ۏ؋�))<BR>
     * <BR>
     * ����.���o�������P�ʈꗗ(�a����˕ۏ؋�)��this.���o�������P�ʈꗗ(�a����˕ۏ؋�)�ɃZ�b�g����B<BR>
     * @@param l_lisAioOrderUnitListFromDepositToMargins - (���o�������P�ʈꗗ(�a����˕ۏ؋�))<BR>
     * (���o�������P�ʈꗗ(�a����˕ۏ؋�))<BR>
     * @@roseuid 486C243503ED
     */
    public void setAioOrderUnitListFromDepositToMargins(List l_lisAioOrderUnitListFromDepositToMargins)
    {
        this.aioOrderUnitListFromDepositToMargins = l_lisAioOrderUnitListFromDepositToMargins;
    }

    /**
     * (get���o�������P�ʈꗗ(�ۏ؋��˗a���))<BR>
     * (get���o�������P�ʈꗗ(�ۏ؋��˗a���))<BR>
     * <BR>
     * this.���o�������P�ʈꗗ(�ۏ؋��˗a���)��ԋp����B<BR>
     * @@return List
     * @@roseuid 486C243503EF
     */
    public List getAioOrderUnitListFromMarginToDeposits()
    {
        return this.aioOrderUnitListFromMarginToDeposits;
    }

    /**
     * (set���o�������P�ʈꗗ(�ۏ؋��˗a���))<BR>
     * (set���o�������P�ʈꗗ(�ۏ؋��˗a���))<BR>
     * <BR>
     * ����.���o�������P�ʈꗗ(�ۏ؋��˗a���)��this.���o�������P�ʈꗗ(�ۏ؋��˗a���)�ɃZ�b�g����B<BR>
     * @@param l_lisAioOrderUnitListFromMarginToDeposits - (���o�������P�ʈꗗ(�ۏ؋��˗a���))<BR>
     * (���o�������P�ʈꗗ(�ۏ؋��˗a���))<BR>
     * @@roseuid 486C243503F0
     */
    public void setAioOrderUnitListFromMarginToDeposits(List l_lisAioOrderUnitListFromMarginToDeposits)
    {
        this.aioOrderUnitListFromMarginToDeposits = l_lisAioOrderUnitListFromMarginToDeposits;
    }

    /**
     * (get�M�p��������t���O)<BR>
     * (get�M�p��������t���O)<BR>
     * <BR>
     * this.�M�p��������t���O��ԋp����B<BR>
     * @@return String
     * @@roseuid 486C243503F2
     */
    public String getMarginEquityJudgeFlag()
    {
        return this.marginEquityJudgeFlag;
    }

    /**
     * (set�M�p��������t���O)<BR>
     * (set�M�p��������t���O)<BR>
     * <BR>
     * ����.�M�p��������t���O��this.�M�p��������t���O�ɃZ�b�g����B<BR>
     * @@param l_strMarginEquityJudgeFlag - (�M�p��������t���O)<BR>
     * (�M�p��������t���O)<BR>
     * @@roseuid 486C2436000C
     */
    public void setMarginEquityJudgeFlag(String l_strMarginEquityJudgeFlag)
    {
        this.marginEquityJudgeFlag = l_strMarginEquityJudgeFlag;
    }

    /**
     * (is�ۏ؋������U�֌�)<BR>
     * (static���\�b�h)(is�ۏ؋������U�֌�)<BR>
     * <BR>
     * �v���Z�X�Ǘ��e�[�u���̌������ʂ����ɁA�ۏ؋������U�ւ̔��茋�ʂ�ԋp����B<BR>
     * <BR>
     * �P�D�v���Z�X�Ǘ��e�[�u������������B<BR>
     * <BR>
     * �@@�m���������n<BR>
     * �@@�@@�@@�E�v���Z�XID�@@=�@@�h0005�h�i�ۏ؋������U�֏I���j<BR>
     * �@@�@@�@@�E�،���ЃR�[�h�@@=�@@����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�E���X�R�[�h�@@=�@@����.���X�R�[�h<BR>
     * <BR>
     * �Q�D�������ʂ����ɁA���茋�ʂ�ԋp����B<BR>
     * <BR>
     * �@@�i�P�j �v���Z�X�Ǘ��e�[�u���̌������ʁ@@==�@@NULL�@@or�@@�v���Z�X�Ǘ��e�[�u���̌������ʂ�0���̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@�@@FALSE�i�ۏ؋������U�֑O�j<BR>
     * �@@�i�Q�j (1)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@�@@TRUE�i�ۏ؋������U�֌�j<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * (���X�R�[�h)<BR>
     * @@return boolean
     * @@roseuid 48DB5FAC02A7
     */
    public static boolean isDepositAutoTransfer(String l_strInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDepositAutoTransfer(String, String)";
        log.entering(STR_METHOD_NAME);

        ProcessManagementRow l_processManagementRow = null;
        try
        {
            //�P�D�v���Z�X�Ǘ��e�[�u������������B
            //�m���������n
            //�E�v���Z�XID�@@=�@@�h0005�h�i�ۏ؋������U�֏I���j
            //�E�،���ЃR�[�h�@@=�@@����.�،���ЃR�[�h
            //�E���X�R�[�h�@@=�@@����.���X�R�[�h
            l_processManagementRow =
                ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    WEB3TPProcessManagementIdDef.DEPOSIT_AUTO_TRANSFER_STOP,
                    l_strInstitutionCode,
                    l_strBranchCode);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�@@�i�P�j �v���Z�X�Ǘ��e�[�u���̌������ʁ@@==�@@NULL�@@or�@@�v���Z�X�Ǘ��e�[�u���̌������ʂ�0���̏ꍇ
        //�m�ԋp�l�n
        //FALSE�i�ۏ؋������U�֑O�j
        //�@@�i�Q�j (1)�ȊO�̏ꍇ
        //�m�ԋp�l�n
        //TRUE�i�ۏ؋������U�֌�j
        if (l_processManagementRow != null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is�ۏ؋������U�֌㔻��t���O)<BR>
     * (is�ۏ؋������U�֌㔻��t���O)<BR>
     * <BR>
     * this.�ۏ؋������U�֌㔻��t���O��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 486C24F703C8
     */
    public boolean isDepositAutoTransferDivFlag()
    {
        return this.depositAutoTransferDivFlag;
    }

    /**
     * (set�ۏ؋������U�֌㔻��t���O)<BR>
     * (set�ۏ؋������U�֌㔻��t���O)<BR>
     * <BR>
     * ����.�ۏ؋������U�֌㔻��t���O��this.�ۏ؋������U�֌㔻��t���O�ɃZ�b�g����B<BR>
     * @@param l_blnDepositAutoTransferDivFlag - (�ۏ؋������U�֌㔻��t���O)<BR>
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * @@roseuid 486C24F703C9
     */
    public void setDepositAutoTransferDivFlag(boolean l_blnDepositAutoTransferDivFlag)
    {
        this.depositAutoTransferDivFlag = l_blnDepositAutoTransferDivFlag;
    }

    /**
     * (get�����Ǐؔ����l���t���O)<BR>
     * (get�����Ǐؔ����l���t���O)<BR>
     * <BR>
     * this.�����Ǐؔ����l���t���O��ԋp����B<BR>
     * @@return boolean
     */
    public boolean getFirstOpenAdddepositFlag()
    {
        return this.firstOpenAdddepositFlag;
    }

    /**
     * (set�����Ǐؔ����l���t���O)<BR>
     * (set�����Ǐؔ����l���t���O)<BR>
     * <BR>
     * ����.�����Ǐؔ����l���t���O��this.�����Ǐؔ����l���t���O�ɃZ�b�g����B<BR>
     * @@param l_blnFirstOpenAdddepositFlag - (�����Ǐؔ����l���t���O)<BR>
     * (�����Ǐؔ����l���t���O)<BR>
     */
    public void setFirstOpenAdddepositFlag(boolean l_blnFirstOpenAdddepositFlag)
    {
        this.firstOpenAdddepositFlag = l_blnFirstOpenAdddepositFlag;
    }

    /**
     * (is�s��������)<BR>
     * (is�s��������)<BR>
     * <BR>
     * �s�������������Ă��邩������s���A���茋�ʂ�ԋp����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(���������Ǘ�)is�s���������v�Q��<BR>
     * @@return boolean
     * @@roseuid 48EC3FB90012
     */
    public boolean isShortfallGeneration()
    {
        final String STR_METHOD_NAME = "isShortfallGeneration()";
        log.entering(STR_METHOD_NAME);

        for (int i = 0; i <= 5; i++)
        {
            //get�a����s���z(T+n)�̖߂�l > 0
            //TRUE(�s��������)��ԋp
            double l_dbLackAccountBalance = this.getLackAccountBalance(i);
            if (l_dbLackAccountBalance > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is��ꐅ���Ǐؔ���)<BR>
     * (is��ꐅ���Ǐؔ���)<BR>
     * <BR>
     * ��ꐅ���Ǐ؂��������Ă��邩������s���A���茋�ʂ�ԋp����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(���������Ǘ�)is��ꐅ���Ǐؔ����v�Q��<BR>
     * @@return boolean
     * @@roseuid 48EC4088024A
     */
    public boolean isFirstAdddeposit()
    {
        final String STR_METHOD_NAME = "isFirstAdddeposit()";
        log.entering(STR_METHOD_NAME);

        //��ꐅ���Ǐؖ��������z���擾����B
        double l_dblFirstAdddepositUncancelAmt = this.getFirstAdddepositUncancelAmt();

        //this.�����Ǐؔ����l���t���O == false �̏ꍇ
        if (!this.firstOpenAdddepositFlag)
        {
            //get��ꐅ���Ǐؖ��������z() > 0 �̏ꍇ
            if (l_dblFirstAdddepositUncancelAmt > 0)
            {
                //TRUE(��ꐅ���Ǐؔ���)��ԋp
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //this.�����Ǐؔ����l���t���O == true �̏ꍇ
        else
        {
            //�ۏ؋������U�֌㔻��t���O���擾����B
            boolean l_blnDepositAutoTransferDivFlag = this.isDepositAutoTransferDivFlag();

            //is�ۏ؋������U�֌㔻��t���O() = true ���� get��ꐅ���Ǐؖ��������z() > 0 �̏ꍇ
            if (l_blnDepositAutoTransferDivFlag && l_dblFirstAdddepositUncancelAmt > 0)
            {
                //TRUE(��ꐅ���Ǐؔ���)��ԋp
                log.exiting(STR_METHOD_NAME);
                return true;
            }

            //��ꐅ���Ǐ؋��z���擾����B
            double l_dblFirstAdddepositAmount = this.getFirstAdddepositAmount();

            //is�ۏ؋������U�֌㔻��t���O() = false ���� get��ꐅ���Ǐ؋��z() > 0 �̏ꍇ
            if (!l_blnDepositAutoTransferDivFlag && l_dblFirstAdddepositAmount > 0)
            {
                //TRUE(��ꐅ���Ǐؔ���)��ԋp
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //FALSE(��ꐅ���Ǐؖ�����)��ԋp
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is��񐅏��Ǐؔ���)<BR>
     * (is��񐅏��Ǐؔ���)<BR>
     * <BR>
     * ��񐅏��Ǐ؂��������Ă��邩������s���A���茋�ʂ�ԋp����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(���������Ǘ�)is��񐅏��Ǐؔ����v�Q��<BR>
     * @@return boolean
     * @@roseuid 48EC408F00B3
     */
    public boolean isSecondAdddeposit()
    {
        final String STR_METHOD_NAME = "isSecondAdddeposit()";
        log.entering(STR_METHOD_NAME);

        //��񐅏��Ǐؖ����������擾����B
        WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo =
            this.createSecondAdddepositNotClearInfo();

        //this.�����Ǐؔ����l���t���O == false �̏ꍇ
        if (!this.firstOpenAdddepositFlag)
        {
            //��񐅏��Ǐؖ��������.�Ǐ؋��z(������) > 0 ����
            //��񐅏��Ǐؖ��������.�Ǐ؋��z(����2) > 0�@@����
            //��񐅏��Ǐؖ��������.�Ǐ؋��z(����1) > 0 ����
            //��񐅏��Ǐؖ��������.�Ǐ؋��z(�������z) > 0 �̏ꍇ
            if (l_secondAdddepositNotClearInfo.secondDepositNonPay > 0
                || l_secondAdddepositNotClearInfo.secondDeposit2 > 0
                || l_secondAdddepositNotClearInfo.secondDeposit1 > 0
                || l_secondAdddepositNotClearInfo.secondDepositExpect > 0)
            {
                //TRUE(��񐅏��Ǐؔ���)��ԋp
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //this.�����Ǐؔ����l���t���O == true �̏ꍇ
        else
        {
            //�ۏ؋������U�֌㔻��t���O���擾����B
            boolean l_blnDepositAutoTransferDivFlag = this.isDepositAutoTransferDivFlag();

            //is�ۏ؋������U�֌㔻��t���O() = true ����
            //(��񐅏��Ǐؖ��������.�Ǐ؋��z(������) > 0 ����
            //��񐅏��Ǐؖ��������.�Ǐ؋��z(����2) > 0�@@����
            //��񐅏��Ǐؖ��������.�Ǐ؋��z(����1) > 0 ����
            //��񐅏��Ǐؖ��������.�Ǐ؋��z(�������z) > 0) �̏ꍇ
            if (l_blnDepositAutoTransferDivFlag
                && (l_secondAdddepositNotClearInfo.secondDepositNonPay > 0
                    || l_secondAdddepositNotClearInfo.secondDeposit2 > 0
                    || l_secondAdddepositNotClearInfo.secondDeposit1 > 0
                    || l_secondAdddepositNotClearInfo.secondDepositExpect > 0))
            {
                //TRUE(��񐅏��Ǐؔ���)��ԋp
                log.exiting(STR_METHOD_NAME);
                return true;
            }

            //��񐅏��Ǐ؋��z�i�������j���擾����B
            double l_dblSecondAdddepositDepositNonPay = this.getSecondAdddepositDepositNonPay();
            //��񐅏��Ǐ؋��z�i����2�j���擾����B
            double l_dblSecondAdddepositDeposit2 = this.getSecondAdddepositDeposit2();
            //��񐅏��Ǐ؋��z�i����1�j���擾����B
            double l_dblSecondAdddepositDeposit1 = this.getSecondAdddepositDeposit1();

            //is�ۏ؋������U�֌㔻��t���O() = false ����
            //(get��񐅏��Ǐ؋��z�i�������j > 0 ����
            //get��񐅏��Ǐ؋��z�i����2�j > 0 ����
            //get��񐅏��Ǐ؋��z�i����1�j > 0 )�̏ꍇ
            if (!l_blnDepositAutoTransferDivFlag
                && (l_dblSecondAdddepositDepositNonPay > 0
                    || l_dblSecondAdddepositDeposit2 > 0
                    || l_dblSecondAdddepositDeposit1 > 0))
            {
                //TRUE(��񐅏��Ǐؔ���)��ԋp
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //FALSE(��񐅏��Ǐؖ�����)��ԋp
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�ڋq����)<BR>
     * (get�ڋq����)<BR>
     * <BR>
     * �u�ڋq�����v��ԋp����B<BR>
     * <BR>
     *  (a)this.���������Ǘ�Params == null �̏ꍇ<BR>
     *  �@@(1)this.�M�p��������t���O == "0"�i�����ڋq�j ���� <BR>
     * �@@�@@�@@�@@�@@�@@this.���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�a��،��]����() == false �̏ꍇ<BR>
     * �@@ �@@�m�ԋp�l�n<BR>
     * �@@�@@�@@"0"�i�����E�O�����j<BR>
     *  �@@(2)this.�M�p��������t���O == "0"�i�����ڋq�j ���� <BR>
     * �@@�@@�@@�@@�@@�@@this.���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�a��،��]����() == true �̏ꍇ<BR>
     * �@@ �@@�m�ԋp�l�n<BR>
     * �@@�@@�@@"1"�i�����E�a��،��]�����j<BR>
     *  �@@(3)this.�M�p��������t���O == "1"�i�M�p�ڋq�j�̏ꍇ<BR>
     * �@@ �@@�m�ԋp�l�n<BR>
     * �@@�@@�@@"2"�i�M�p�j<BR>
     *  (b)this.���������Ǘ�Params != null �̏ꍇ<BR>
     *  �@@�m�ԋp�l�n  <BR>
     *  �@@this.���������Ǘ�Params.get�ڋq�����i�j<BR>
     * @@return String
     * @@roseuid 48C8BCC8000A
     */
    public String getAccountAttribute()
    {
        final String STR_METHOD_NAME = "getAccountAttribute()";
        log.entering(STR_METHOD_NAME);

        //(a)this.���������Ǘ�Params == null �̏ꍇ
        if (this.paymentRequisitMngParams == null)
        {
            //(1)this.�M�p��������t���O == "0"�i�����ڋq�j ����
            //this.���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�a��،��]����() == false �̏ꍇ
            //�@@ �@@�m�ԋp�l�n
            //   "0"�i�����E�O�����j
            if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag)
                && !this.tpCalcEquity.calcCondition.isEquityEvalDiv())
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountAttributeDef.EQUITY_NOT_ASSET_EVAL;
            }

            //(2)this.�M�p��������t���O == "0"�i�����ڋq�j ����
            //this.���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�a��،��]����() == true �̏ꍇ
            //�@@ �@@�m�ԋp�l�n
            //   "1"�i�����E�a��،��]�����j
            if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag)
                && this.tpCalcEquity.calcCondition.isEquityEvalDiv())
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountAttributeDef.EQUITY_ASSET_EVAL;
            }

            //(3)this.�M�p��������t���O == "1"�i�M�p�ڋq�j�̏ꍇ
            //�m�ԋp�l�n
            //"2"�i�M�p�j
            if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountAttributeDef.MARGIN;
            }
        }
        //(b)this.���������Ǘ�Params != null �̏ꍇ
        //�m�ԋp�l�n
        //this.���������Ǘ�Params.get�ڋq�����i�j
        else
        {
            log.exiting(STR_METHOD_NAME);
            return this.paymentRequisitMngParams.getAccountAttribute();
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get�v�Z��)<BR>
     * (get�v�Z��)<BR>
     * <BR>
     * �u�v�Z���v��ԋp����B<BR>
     * <BR>
     *  (a)this.���������Ǘ�Params == null �̏ꍇ<BR>
     *  �@@(1)this.�M�p��������t���O == "0"�i�����ڋq�j<BR>
     * �@@ �@@�m�ԋp�l�n<BR>
     * �@@�@@�@@ this.���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�c�Ɠ�(-1)<BR>
     *  �@@(2)this.�M�p��������t���O == "1"�i�M�p�ڋq�j�̏ꍇ<BR>
     * �@@ �@@�m�ԋp�l�n<BR>
     * �@@�@@�@@ this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get�c�Ɠ�(-1)<BR>
     * <BR>
     *  (b)this.���������Ǘ�Params != null �̏ꍇ<BR>
     *  �@@�m�ԋp�l�n  <BR>
     *  �@@this.���������Ǘ�Params.get�v�Z���i�j<BR>
     * <BR>
     * @@return Date
     * @@roseuid 48C8BD340091
     */
    public Date getCalcDate()
    {
        final String STR_METHOD_NAME = "getCalcDate()";
        log.entering(STR_METHOD_NAME);

        //(a)this.���������Ǘ�Params == null �̏ꍇ
        if (this.paymentRequisitMngParams == null)
        {
            //(1)this.�M�p��������t���O == "0"�i�����ڋq�j
            //�m�ԋp�l�n
            //this.���Y�]�͏��<�����ڋq>.�]�͌v�Z����.get�c�Ɠ�(-1)
            if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
            {
                log.exiting(STR_METHOD_NAME);
                return this.tpCalcEquity.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_MINUS1);
            }
            //(2)this.�M�p��������t���O == "1"�i�M�p�ڋq�j�̏ꍇ
            //�m�ԋp�l�n
            //this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get�c�Ɠ�(-1)
            else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
            {
                log.exiting(STR_METHOD_NAME);
                return this.tpCalcMargin.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_MINUS1);
            }
        }
        else
        {
            //(b)this.���������Ǘ�Params != null �̏ꍇ
            //�m�ԋp�l�n
            //this.���������Ǘ�Params.get�v�Z���i�j
            log.exiting(STR_METHOD_NAME);
            return this.paymentRequisitMngParams.getCalcDate();
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get���֋�)<BR>
     * (get���֋�)<BR>
     * <BR>
     * �u���֋��v��ԋp����B<BR>
     * <BR>
     *  (a)this.���������Ǘ�Params == null �̏ꍇ<BR>
     *  �@@�m�ԋp�l�n<BR>
     *  �@@0<BR>
     * <BR>
     *  (b)this.���������Ǘ�Params != null �̏ꍇ<BR>
     *  �@@�m�ԋp�l�n<BR>
     *  �@@this.���������Ǘ�Params.get���֋��i�j<BR>
     * @@return double
     * @@roseuid 48C8BE2D01AF
     */
    public double getDebitAmount()
    {
        final String STR_METHOD_NAME = "getDebitAmount()";
        log.entering(STR_METHOD_NAME);

        //(a)this.���������Ǘ�Params == null �̏ꍇ
        //�m�ԋp�l�n
        //0
        if (this.paymentRequisitMngParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //(b)this.���������Ǘ�Params != null �̏ꍇ
        else
        {
            //this.���������Ǘ�Params.get���֋��i�j
            log.exiting(STR_METHOD_NAME);
            return this.paymentRequisitMngParams.getDebitAmount();
        }
    }

    /**
     * (get���ʗ��֋�)<BR>
     * (get���ʗ��֋�)<BR>
     * <BR>
     * �u���ʗ��֋��v��ԋp����B<BR>
     * <BR>
     *  (a)this.���������Ǘ�Params == null �̏ꍇ<BR>
     *  �@@�m�ԋp�l�n<BR>
     *  �@@0<BR>
     * <BR>
     *  (b)this.���������Ǘ�Params != null �̏ꍇ<BR>
     *  �@@�m�ԋp�l�n<BR>
     *  �@@this.���������Ǘ�Params.get���ʗ��֋��i�j<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C8BE6602C6
     */
    public double getSpecialDebitAmount()
    {
        final String STR_METHOD_NAME = "getSpecialDebitAmount()";
        log.entering(STR_METHOD_NAME);

        //(a)this.���������Ǘ�Params == null �̏ꍇ
        //�m�ԋp�l�n
        //0
        if (this.paymentRequisitMngParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //(b)this.���������Ǘ�Params != null �̏ꍇ
        else
        {
            //this.���������Ǘ�Params.get���ʗ��֋��i�j
            log.exiting(STR_METHOD_NAME);
            return this.paymentRequisitMngParams.getSpecialDebitAmount();
        }
    }

    /**
     * (get�a����s���z)<BR>
     * (get�a����s���z)<BR>
     * <BR>
     * �ڋq�����̔����A�u�a����s���z�v��ԋp����B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.calc�a����s���z(�����ڋq)(T+n)<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.calc�a����s���z(�M�p�ڋq)(T+n)<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48C9BD670052
     */
    public double getLackAccountBalance(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getLackAccountBalance(int)";
        log.entering(STR_METHOD_NAME);

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        //this.calc�a����s���z(�����ڋq)(T+n)
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return this.calcAccountBalanceShortfallEquity(l_intSpecifiedPoint);
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�m�ԋp�l�n
        //this.calc�a����s���z(�M�p�ڋq)(T+n)
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return this.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);
        }

        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (get����)<BR>
     * (get����)<BR>
     * <BR>
     * �ڋq�����̔����A�����Ŏw�肳�ꂽ�w���(=n)�́u�����v��ԋp����B <BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * �@@n��-2�ȏ�5�ȉ��łȂ����Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�����̔�����s���A�����Ŏw�肳�ꂽ�w���(=n)�́u�����v��ԋp����B<BR>
     * �@@�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq�j�̏ꍇ<BR>
     * �@@�@@[�ԋp�l] <BR>
     * �@@�@@this.���Y�]�͏��<�����ڋq>.get�]�͌v�Z����.get�c�Ɠ��iT+n�j<BR>
     * <BR>
     * �@@�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq�j�̏ꍇ<BR>
     * �@@�@@[�ԋp�l] <BR>
     * �@@�@@this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.get�c�Ɠ��iT+n�j<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return Date
     * @@roseuid 48D30BFC0001
     */
    public Date getDate(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getLackAccountBalance(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����`�F�b�N���s���B
        //n��-2�ȏ�5�ȉ��łȂ����Anull��ԋp����B
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_MINUS2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_MINUS1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq�j�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //this.���Y�]�͏��<�����ڋq>.get�]�͌v�Z����.get�c�Ɠ��iT+n�j
            log.exiting(STR_METHOD_NAME);
            return this.tpCalcEquity.getCalcCondition().getBizDate(l_intSpecifiedPoint);
        }

        //�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq�j�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.get�c�Ɠ��iT+n�j
            log.exiting(STR_METHOD_NAME);
            return this.tpCalcMargin.getCalcCondition().getBizDate(l_intSpecifiedPoint);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (calc���Z�z)<BR>
     * (calc���Z�z)<BR>
     * <BR>
     * �ڋq�����̔����A�����̎w���(=n)�ɑΉ������u���Z�z�v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * �@@n��0�ȏ�1�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@���Z�z(T+n)�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �R�j�@@�a����s���z(T+n)�̔�����s���B<BR>
     * �@@�ia�j�a����s���z(T+n)�@@==�@@0�̏ꍇ<BR>
     * �@@�@@���Z�z(T+n)�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�j�a����s���z(T+n)�@@!=�@@0�̏ꍇ<BR>
     * �@@�@@�S�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�a����s���z(T+n)�@@�@@�@@�E�E�Ethis.calc�a����s���z(�M�p�ڋq)(T+n)<BR>
     * <BR>
     * �S�j�@@�u���Z�z�v���v�Z����B<BR>
     * �@@�ia�j�w���(=n)�@@==�@@0�@@�̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@���Z�z(T+0)�@@=�@@�ڋq����(T+0)�@@�|�@@�U�֊z(�ہ˗a)(T+0)<BR>
     * <BR>
     * �@@�ib�j�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@�i�P�j�ڋq����(T+0) > 0 �̏ꍇ<BR>
     * �@@�@@�@@���Z�z(T+1)�@@=�@@�ڋq����(T+1)�@@�|�@@�U�֊z(�ہ˗a)(T+1)<BR>
     * �@@�@@�i�Q�j��L�i�P�j�ȊO�̏ꍇ<BR>
     * �@@�@@�@@���Z�z(T+1)�@@=�@@�ڋq����(T+1)�@@�|�@@�ڋq����(T+0)�@@�|�@@�U�֊z(�ہ˗a)(T+1)<BR>
     * <BR>
     * �@@�ic�j�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@���Z�z(T+1)�@@=�@@0<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�ڋq����(T+n)�@@�@@�@@�E�E�Ethis.calc�ڋq����(T+n)<BR>
     * �@@�@@�E�U�֊z(�ہ˗a)(T+n)�@@�@@�@@�E�E�Ethis.calc�ۏ؋�����̐U�֊z(���t�w��)(T+n)<BR>
     * <BR>
     * �T�j�@@�v�Z�������Z�z(T+n)��ԋp����B<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48C9BD2F025F
     */
    public double calcAdjustedAmt(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAdjustedAmt(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����`�F�b�N���s���B
        //n��0�ȏ�1�ȉ��łȂ����A0��ԋp����B
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //���Z�z(T+n)�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�E�a����s���z(T+n)�@@�@@�@@�E�E�Ethis.calc�a����s���z(�M�p�ڋq)(T+n)
            double l_dblAccountBalanceShortfallMargin =
                this.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);

            //�R�j�@@�a����s���z(T+n)�̔�����s���B
            //�ia�j�a����s���z(T+n)�@@==�@@0�̏ꍇ
            //���Z�z(T+n)�@@=�@@0��ݒ肵�A�ԋp����B
            if (GtlUtils.Double.isZero(l_dblAccountBalanceShortfallMargin))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //�S�j�@@�u���Z�z�v���v�Z����B
            //�ia�j�w���(=n)�@@==�@@0�@@�̏ꍇ
            //�m�v�Z���n
            //���Z�z(T+0)�@@=�@@�ڋq����(T+0)�@@�|�@@�U�֊z(�ہ˗a)(T+0)
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
            {
                //�E�ڋq����(T+n)�@@�@@�@@�E�E�Ethis.calc�ڋq����(T+n)
                double l_dblAccountCalculate = this.calcAccountCalculate(WEB3TPSpecifiedPointDef.T_0);
                //�E�U�֊z(�ہ˗a)(T+n)�@@�@@�@@�E�E�Ethis.calc�ۏ؋�����̐U�֊z(���t�w��)(T+n)
                double l_dblTransferFromMarginDeposit = this.calcTransferFromMarginDeposit(WEB3TPSpecifiedPointDef.T_0);

                BigDecimal l_bdAccountCalculate = new BigDecimal(l_dblAccountCalculate + "");
                BigDecimal l_bdTransferFromMarginDeposit = new BigDecimal(l_dblTransferFromMarginDeposit + "");

                log.exiting(STR_METHOD_NAME);
                return l_bdAccountCalculate.subtract(l_bdTransferFromMarginDeposit).doubleValue();
            }

            //�ib�j�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && this.isDepositAutoTransferDivFlag())
            {
                //�E�ڋq����(T+n)�@@�@@�@@�E�E�Ethis.calc�ڋq����(T+n)
                double l_dblAccountCalculate0 = this.calcAccountCalculate(WEB3TPSpecifiedPointDef.T_0);
                double l_dblAccountCalculate1 = this.calcAccountCalculate(WEB3TPSpecifiedPointDef.T_1);
                //�E�U�֊z(�ہ˗a)(T+n)�@@�@@�@@�E�E�Ethis.calc�ۏ؋�����̐U�֊z(���t�w��)(T+n)
                double l_dblTransferFromMarginDeposit = this.calcTransferFromMarginDeposit(WEB3TPSpecifiedPointDef.T_1);

                BigDecimal l_bdAccountCalculate0 = new BigDecimal(l_dblAccountCalculate0 + "");
                BigDecimal l_bdAccountCalculate1 = new BigDecimal(l_dblAccountCalculate1 + "");
                BigDecimal l_bdTransferFromMarginDeposit = new BigDecimal(l_dblTransferFromMarginDeposit + "");

                log.exiting(STR_METHOD_NAME);
                //�m�v�Z���n
                //�i�P�j�ڋq����(T+0) > 0 �̏ꍇ<BR>
                //�@@���Z�z(T+1)�@@=�@@�ڋq����(T+1)�@@�|�@@�U�֊z(�ہ˗a)(T+1)<BR>
                if (l_dblAccountCalculate0 > 0)
                {
                    return l_bdAccountCalculate1.subtract(
                            l_bdTransferFromMarginDeposit).doubleValue();   
                }
                //�i�Q�j��L�i�P�j�ȊO�̏ꍇ<BR>
                //�@@���Z�z(T+1)�@@=�@@�ڋq����(T+1)�@@�|�@@�ڋq����(T+0)�@@�|�@@�U�֊z(�ہ˗a)(T+1)<BR>
                else
                {
                    return l_bdAccountCalculate1.subtract(
                            l_bdAccountCalculate0).subtract(
                            l_bdTransferFromMarginDeposit).doubleValue();
                }
            }
            
            //�ic�j�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ
            //�m�v�Z���n
            //���Z�z(T+1)�@@=�@@0
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && !this.isDepositAutoTransferDivFlag())
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc���v��S����)<BR>
     * (calc���v��S����)<BR>
     * <BR>
     * �ڋq�����̔����A�����̎w���(=n)�ɑΉ������u���v��S�����v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * �@@n��0�ȏ�1�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@���v��S����(T+n)�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �R�j�@@�a����s���z(T+n)�̔�����s���B<BR>
     * �@@�ia�j�a����s���z(T+n)�@@==�@@0�̏ꍇ<BR>
     * �@@�@@���v��S����(T+n)�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�j�a����s���z(T+n)�@@!=�@@0�̏ꍇ<BR>
     * �@@�@@�S�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�a����s���z(T+n)�@@�@@�@@�E�E�Ethis.calc�a����s���z(�M�p�ڋq)(T+n)<BR>
     * <BR>
     * �S�j�@@�u���v��S�����v���v�Z����B<BR>
     * �@@�ia�j�w���(=n)�@@==�@@0�@@�̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@���v��S����(T+0)�@@=�@@MAX�i���v��S����(T+0)�@@�|�@@�U�֊z(�a�˕�)(T+0),�@@���ʗ��֋�����,�@@0�j<BR>
     * <BR>
     * �@@�ib�j�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@���v��S����(T+1)�@@=�@@MAX�i���v��S����(T+1)�@@�|�@@�U�֊z(�a�˕�)(T+1),�@@0�j<BR>
     * <BR>
     * �@@�ic�j�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@���v��S����(T+1)�@@=�@@0<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E���v��S����(T+n)�@@�@@�@@�E�E�Ethis.get���v��S����(T+n)<BR>
     * �@@�@@�E�U�֊z(�a�˕�)(T+n)�@@�@@�@@�E�E�Ethis.calc�a�������̐U�֊z(���t�w��)(T+n)<BR>
     * �@@�@@�E���ʗ��֋����с@@�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.get�ʗ��֋����сi�j<BR>
     * <BR>
     * �T�j�@@�W�v�������v��S����(T+n)��ԋp����B<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48C8DCD30244
     */
    public double calcDayTradeRestraint(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcDayTradeRestraint(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����`�F�b�N���s���B
        //n��0�ȏ�1�ȉ��łȂ����A0��ԋp����B
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //���v��S����(T+n)�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�E�a����s���z(T+n)�@@�@@�@@�E�E�Ethis.calc�a����s���z(�M�p�ڋq)(T+n)
            double l_dblAccountBalanceShortfallMargin =
                this.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);

            //�ia�j�a����s���z(T+n)�@@==�@@0�̏ꍇ
            //���v��S����(T+n)�@@=�@@0��ݒ肵�A�ԋp����B
            if (GtlUtils.Double.isZero(l_dblAccountBalanceShortfallMargin))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //�ia�j�w���(=n)�@@==�@@0�@@�̏ꍇ
            //�m�v�Z���n
            //���v��S����(T+0)�@@=�@@MAX�i���v��S����(T+0)�@@�|�@@�U�֊z(�a�˕�)(T+0),�@@���ʗ��֋�����,�@@0�j
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
            {
                //�@@�@@�E���v��S����(T+n)�@@�@@�@@�E�E�Ethis.get���v��S����(T+n)
                double l_dblDayTradeRestraint =
                    this.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_0);
                //�@@�@@�E�U�֊z(�a�˕�)(T+n)�@@�@@�@@�E�E�Ethis.calc�a�������̐U�֊z(���t�w��)(T+n)
                double l_dblAccountBalanceFromMarginDeposit =
                    this.calcAccountBalanceFromMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
                //�@@�@@�E���ʗ��֋����с@@�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.get���ʗ��֋����сi�j
                double l_dblSpecialDebitAmount =
                    this.tpCalcMargin.getCalcCondition().getSpecialDebitAmount();

                BigDecimal l_bdDayTradeRestraint =
                    new BigDecimal(l_dblDayTradeRestraint + "");
                BigDecimal l_bdAccountBalanceFromMarginDeposit =
                    new BigDecimal(l_dblAccountBalanceFromMarginDeposit + "");

                double l_dblMaxAmount = Math.max(l_bdDayTradeRestraint.subtract(
                    l_bdAccountBalanceFromMarginDeposit).doubleValue(), l_dblSpecialDebitAmount);
                log.exiting(STR_METHOD_NAME);
                return Math.max(l_dblMaxAmount, 0);
            }

            //�ib�j�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ
            //�m�v�Z���n
            //���v��S����(T+1)�@@=�@@MAX�i���v��S����(T+1)�@@�|�@@�U�֊z(�a�˕�)(T+1),�@@0�j
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && this.isDepositAutoTransferDivFlag())
            {
                //�@@�@@�E���v��S����(T+n)�@@�@@�@@�E�E�Ethis.get���v��S����(T+n)
                double l_dblDayTradeRestraint =
                    this.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_1);
                //�@@�@@�E�U�֊z(�a�˕�)(T+n)�@@�@@�@@�E�E�Ethis.calc�a�������̐U�֊z(���t�w��)(T+n)
                double l_dblAccountBalanceFromMarginDeposit =
                    this.calcAccountBalanceFromMarginDeposit(WEB3TPSpecifiedPointDef.T_1);

                BigDecimal l_bdDayTradeRestraint =
                    new BigDecimal(l_dblDayTradeRestraint + "");
                BigDecimal l_bdAccountBalanceFromMarginDeposit =
                    new BigDecimal(l_dblAccountBalanceFromMarginDeposit + "");

                log.exiting(STR_METHOD_NAME);
                return Math.max(l_bdDayTradeRestraint.subtract(
                        l_bdAccountBalanceFromMarginDeposit).doubleValue(), 0);
            }

            //�ic�j�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ
            //�m�v�Z���n
            //���v��S����(T+1)�@@=�@@0
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && !this.isDepositAutoTransferDivFlag())
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (get�ۏ؋�����̐U�֊z)<BR>
     * (get�ۏ؋�����̐U�֊z)<BR>
     * <BR>
     * �ڋq�����̔����A�����̎w���(=n)�ɑΉ������u�ۏ؋�����̐U�֊z�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * �@@n��0�ȏ�1�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�ۏ؋�����̐U�֊z�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �R�j�@@�a����s���z(T+n)�̔�����s���B<BR>
     * �@@�ia�j�a����s���z(T+n)�@@==�@@0�̏ꍇ<BR>
     * �@@�@@�ۏ؋�����̐U�֊z�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�j�a����s���z(T+n)�@@!=�@@0�̏ꍇ<BR>
     * �@@�@@�S�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�a����s���z(T+n)�@@�@@�@@�E�E�Ethis.calc�a����s���z(�M�p�ڋq)(T+n)<BR>
     * <BR>
     * �S�j�@@�u�ۏ؋�����̐U�֊z�v��ݒ肷��B<BR>
     * <BR>
     * �@@(a)�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ<BR>
     * �@@�@@�m�ݒ�l�n<BR>
     * �@@�@@�ۏ؋�����̐U�֊z�@@=�@@0<BR>
     * <BR>
     * �@@(b)��L(a)�ȊO �̏ꍇ<BR>
     * �@@�@@�m�ݒ�l�n<BR>
     * �@@�@@�ۏ؋�����̐U�֊z�@@=�@@�W�v��U�֊z<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�W�v��U�֊z�@@�@@�@@�E�E�Ethis.calc�ۏ؋�����̐U�֊z(���t�w��)(T+n)<BR>
     * <BR>
     * �T�j�@@�W�v�����ۏ؋�����̐U�֊z(T+n)��ԋp����B<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48C9BCE102E4
     */
    public double getTransferFromMarginDeposit(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getTransferFromMarginDeposit(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����`�F�b�N���s���B
        //n��0�ȏ�1�ȉ��łȂ����A0��ԋp����B
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�ۏ؋�����̐U�֊z�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�E�a����s���z(T+n)�@@�@@�@@�E�E�Ethis.calc�a����s���z(�M�p�ڋq)(T+n)
            double l_accountBalanceShortfallMargin =
                this.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);

            //�ia�j�a����s���z(T+n)�@@==�@@0�̏ꍇ
            //�ۏ؋�����̐U�֊z�@@=�@@0��ݒ肵�A�ԋp����B
            if (GtlUtils.Double.isZero(l_accountBalanceShortfallMargin))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //�ia�j�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && !this.isDepositAutoTransferDivFlag())
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //(b)��L(a)�ȊO �̏ꍇ
            //�ۏ؋�����̐U�֊z�@@=�@@�W�v��U�֊z
            //�W�v��U�֊z�@@�@@�@@�E�E�Ethis.calc�ۏ؋�����̐U�֊z(���t�w��)(T+n)
            else
            {
                log.exiting(STR_METHOD_NAME);
                return this.calcTransferFromMarginDeposit(l_intSpecifiedPoint);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (get��ꐅ���Ǐ،o�ߓ���)<BR>
     * (get��ꐅ���Ǐ،o�ߓ���)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐ،o�ߓ����v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐ،o�ߓ����@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�\�������̔�����s���A�l��ԋp����B<BR>
     * �@@�ia�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@true �̏ꍇ<BR>
     * �@@�@@�i�P�j��ꐅ���Ǐ؋��z�@@>�@@0�̏ꍇ<BR>
     * �@@�@@�@@[1]��ꐅ���Ǐؖ��������z�@@<=�@@0�̏ꍇ<BR>
     * �@@�@@�@@�@@�m�ݒ�l�n<BR>
     * �@@�@@�@@�@@��ꐅ���Ǐ،o�ߓ����@@=�@@1<BR>
     * <BR>
     * �@@�@@�@@[2]  [1]�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�m�ݒ�l�n<BR>
     * �@@�@@�@@�@@��ꐅ���Ǐ،o�ߓ����@@=�@@�o�ߓ����@@�{�@@1<BR>
     * <BR>
     * �@@�@@�i�Q�j(1)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�m�ݒ�l�n<BR>
     * �@@�@@�@@��ꐅ���Ǐ،o�ߓ����@@=�@@0<BR>
     * <BR>
     * �@@�ib�j(a)�ȊO�̏ꍇ<BR>
     * �@@�@@�i�P�jthis.���������Ǘ�Params == null�̏ꍇ<BR>
     * �@@�@@�m�ݒ�l�n<BR>
     * �@@�@@��ꐅ���Ǐ،o�ߓ����@@=�@@0<BR>
     * <BR>
     * �@@�@@�i�Q�jthis.���������Ǘ�Params.get��ꐅ���Ǐ،o�ߓ����i�j == 0 �̏ꍇ<BR>
     * �@@�@@�m�ݒ�l�n<BR>
     * �@@�@@��ꐅ���Ǐ،o�ߓ����@@=�@@0<BR>
     * <BR>
     * �@@�@@�i�R�j��L�i�P�j�i�Q�j�ȊO�̏ꍇ <BR>
     * �@@�@@�m�ݒ�l�n<BR>
     * �@@�@@��ꐅ���Ǐ،o�ߓ����@@=�@@�o�ߓ����@@�{�@@1<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E��ꐅ���Ǐ؋��z�@@�@@�@@�E�E�Ethis.calc��ꐅ���Ǐ؋��z�i�j<BR>
     * �@@�@@�E��ꐅ���Ǐؖ��������z�@@�@@�@@�E�E�Ethis.calc��ꐅ���Ǐؖ��������z�i�j<BR>
     * �@@�@@�E�o�ߓ����@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��ꐅ���Ǐ،o�ߓ����i�j<BR>
     * �@@�@@�@@�������Athis.���������Ǘ�Params == null�̏ꍇ�́A�o�ߓ��� = 0 �Ƃ���B<BR>
     * <BR>
     * �R�j�@@�ݒ肵����ꐅ���Ǐ،o�ߓ�����ԋp����B<BR>
     * @@return int
     * @@roseuid 48C9BE4F0301
     */
    public int getFirstAdddepositPassDay()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositPassDay()";
        log.entering(STR_METHOD_NAME);

        int l_intFirstAdddepositPassDay = 0;

        //��ꐅ���Ǐ؋��z���擾����B
        double l_dblFirstAdddepositAmount = this.calcFirstAdddepositAmount();

        //��ꐅ���Ǐؖ��������z���擾����B
        double l_dblFirstAdddepositUncancelAmt = this.calcFirstAdddepositUncancelAmt();

        //�o�ߓ������擾����B
        double l_dblPassDay = 0;
        if (this.paymentRequisitMngParams == null)
        {
            l_dblPassDay = 0;
        }
        else
        {
            l_dblPassDay = this.paymentRequisitMngParams.getFirstDepositPassDay();
        }

        //�P�j�@@�ڋq�����̔�����s���B
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            //��ꐅ���Ǐ،o�ߓ����@@=�@@0��ݒ肵�A�ԋp����B
            return 0;
        }

        //b�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�Q�j�ȍ~�̏������s���B
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�Q�j�@@�\�������̔�����s���A�l��ԋp����B
            //�ia�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@true �̏ꍇ
            if (this.depositAutoTransferDivFlag)
            {
                //�i�P�j��ꐅ���Ǐ؋��z�@@>�@@0�̏ꍇ
                if (l_dblFirstAdddepositAmount > 0)
                {
                    //[1]��ꐅ���Ǐؖ��������z�@@<=�@@0�̏ꍇ
                    if (l_dblFirstAdddepositUncancelAmt < 0 || GtlUtils.Double.isZero(l_dblFirstAdddepositUncancelAmt))
                    {
                        //�m�ݒ�l�n
                        //��ꐅ���Ǐ،o�ߓ����@@=�@@1
                        l_intFirstAdddepositPassDay = 1;
                    }
                    //[2] [1]�ȊO�̏ꍇ
                    else
                    {
                        //�m�ݒ�l�n
                        //��ꐅ���Ǐ،o�ߓ����@@=�@@�o�ߓ����@@�{�@@1
                        l_intFirstAdddepositPassDay =
                            new BigDecimal(l_dblPassDay + "").add(new BigDecimal("1")).intValue();
                    }
                }
                //�i�Q�j(1)�ȊO�̏ꍇ
                else
                {
                    //�m�ݒ�l�n
                    //��ꐅ���Ǐ،o�ߓ����@@=�@@0
                    l_intFirstAdddepositPassDay = 0;
                }
            }
            //�ib�j(a)�ȊO�̏ꍇ
            else
            {
                //�i�P�jthis.���������Ǘ�Params == null�̏ꍇ
                //�m�ݒ�l�n 
                //��ꐅ���Ǐ،o�ߓ����@@=�@@0
                if (this.paymentRequisitMngParams == null)
                {
                    l_intFirstAdddepositPassDay = 0;
                }
                //�i�Q�jthis.���������Ǘ�Params.get��ꐅ���Ǐ،o�ߓ����i�j == 0 �̏ꍇ
                //�m�ݒ�l�n
                //��ꐅ���Ǐ،o�ߓ����@@=�@@0
                else if (GtlUtils.Double.isZero(this.paymentRequisitMngParams.getFirstDepositPassDay()))
                {
                    l_intFirstAdddepositPassDay = 0;
                }
                //�i�R�j��L�i�P�j�i�Q�j�ȊO�̏ꍇ
                //�m�ݒ�l�n
                //��ꐅ���Ǐ،o�ߓ����@@=�@@�o�ߓ����@@�{�@@1
                else
                {
                    l_intFirstAdddepositPassDay =
                        new BigDecimal(l_dblPassDay + "").add(new BigDecimal("1")).intValue();
                }

            }
        }

        log.exiting(STR_METHOD_NAME);

        //�R�j�@@�ݒ肵����ꐅ���Ǐ،o�ߓ�����ԋp����B
        return l_intFirstAdddepositPassDay;
    }

    /**
     * (get��ꐅ���ǏؗL���o�ߓ���)<BR>
     * (get��ꐅ���ǏؗL���o�ߓ���)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���ǏؗL���o�ߓ����v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@ 0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * <BR>
     * �@@�@@(1)this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����(<BR>
     * �@@�@@�@@�@@"first.margin.pass.day1") == null�@@�̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@�@@999<BR>
     * <BR>
     * �@@�@@(2)this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����(<BR>
     * �@@�@@�@@�@@"first.margin.pass.day1") != null�@@�̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@�@@this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����<BR>
     * �@@�@@�@@�@@("first.margin.pass.day1")<BR>
     * @@return int
     * @@roseuid 48C9BF0201EA
     */
    public int getFirstAdddepositPassDayValid()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositPassDayValid()";
        log.entering(STR_METHOD_NAME);

        int l_intFirstAdddepositPassDayValid = 0;

        //�P�j�@@�ڋq�����̔�����s���B
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //0
            l_intFirstAdddepositPassDayValid = 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //(1)this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����(
            //"first.margin.pass.day1") == null�@@�̏ꍇ
            if (this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.FIRST_MARGIN_PASS_DAY1) == null)
            {
                //�m�ԋp�l�n
                //999
                l_intFirstAdddepositPassDayValid = 999;
            }
            //(2)this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("first.margin.pass.day1") != null�@@�̏ꍇ
            else
            {
                //�m�ԋp�l�n
                //this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("first.margin.pass.day1")
                String l_strInstBranCalcCondition =
                    this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                        WEB3BranchPreferencesNameDef.FIRST_MARGIN_PASS_DAY1);

                l_intFirstAdddepositPassDayValid = Integer.parseInt(l_strInstBranCalcCondition);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_intFirstAdddepositPassDayValid;
    }

    /**
     * (get��ꐅ���Ǐؔ�����)<BR>
     * (get��ꐅ���Ǐؔ�����)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐؔ������v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐؔ������@@=�@@NULL��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�u��ꐅ���Ǐؔ������v��ݒ肷��B<BR>
     * �@@�ia�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@true �̏ꍇ<BR>
     * �@@�@@�i�P�j�u��ꐅ���Ǐ،o�ߓ����@@==�@@0�v�̏ꍇ<BR>
     * �@@�@@�@@�m�ݒ�l�n<BR>
     * �@@�@@�@@��ꐅ���Ǐؔ������@@=�@@NULL<BR>
     * <BR>
     * �@@�@@�i�Q�j�u��ꐅ���Ǐ،o�ߓ����@@==�@@1�v�̏ꍇ<BR>
     * �@@�@@�@@�m�ݒ�l�n<BR>
     * �@@�@@�@@��ꐅ���Ǐؔ������@@=�@@����(T+0)<BR>
     * <BR>
     * �@@�@@�i�R�j(1)�A(2)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�m�ݒ�l�n<BR>
     * �@@�@@�@@��ꐅ���Ǐؔ������@@=�@@������<BR>
     * <BR>
     * �@@�ib�j(a)�ȊO�̏ꍇ<BR>
     * �@@�@@�m�ݒ�l�n<BR>
     * �@@�@@��ꐅ���Ǐؔ������@@=�@@������<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E��ꐅ���Ǐ،o�ߓ����@@�@@�@@�E�E�Ethis.get��ꐅ���Ǐ،o�ߓ����i�j<BR>
     * �@@�@@�E�������@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��ꐅ���Ǐؔ������i�j<BR>
     * �@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A������ = null �Ƃ���B<BR>
     * �@@�@@�E����(T+0)�@@�@@�@@�E�E�Ethis.get����(T+0)<BR>
     * <BR>
     * �R�j�@@�ݒ肵����ꐅ���Ǐؔ�������ԋp����B<BR>
     * @@return Date
     * @@roseuid 48C9BF590317
     */
    public Date getFirstAdddepositOccurredDate()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositOccurredDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datFirstAdddepositOccurredDate = null;

        //��ꐅ���Ǐ،o�ߓ������擾����B
        int l_intFirstAdddepositPassDay = this.getFirstAdddepositPassDay();

        //���������擾����B
        Timestamp l_tsOccurredDate = null;
        if (this.paymentRequisitMngParams == null)
        {
            l_tsOccurredDate = null;
        }
        else
        {
            l_tsOccurredDate = this.paymentRequisitMngParams.getFirstDepositOccurredDate();
        }

        //����(T+0)���擾����B
        Date l_dat = this.getDate(WEB3TPSpecifiedPointDef.T_0);

        //�P�j�@@�ڋq�����̔�����s���B
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            //��ꐅ���Ǐؔ������@@=�@@NULL��ݒ肵�A�ԋp����B
            return null;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�Q�j�ȍ~�̏������s���B
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�Q�j�@@�u��ꐅ���Ǐؔ������v��ݒ肷��B
            //�ia�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@true �̏ꍇ
            if (this.depositAutoTransferDivFlag)
            {
                //�i�P�j�u��ꐅ���Ǐ،o�ߓ����@@==�@@0�v�̏ꍇ
                if (l_intFirstAdddepositPassDay == 0)
                {
                    //�m�ݒ�l�n
                    //��ꐅ���Ǐؔ������@@=�@@NULL
                    l_datFirstAdddepositOccurredDate = null;
                }
                //�i�Q�j�u��ꐅ���Ǐ،o�ߓ����@@==�@@1�v�̏ꍇ
                else if (l_intFirstAdddepositPassDay == 1)
                {
                    //�m�ݒ�l�n
                    //��ꐅ���Ǐؔ������@@=�@@����(T+0)
                    l_datFirstAdddepositOccurredDate = l_dat;
                }
                //�i�R�j(1)�A(2)�ȊO�̏ꍇ
                else
                {
                    //�m�ݒ�l�n
                    //��ꐅ���Ǐؔ������@@=�@@������
                    l_datFirstAdddepositOccurredDate = l_tsOccurredDate;
                }
            }
            //�ib�j(a)�ȊO�̏ꍇ
            else
            {
                //�m�ݒ�l�n
                //��ꐅ���Ǐؔ������@@=�@@������
                l_datFirstAdddepositOccurredDate = l_tsOccurredDate;
            }
        }

        log.exiting(STR_METHOD_NAME);

        //�R�j�@@�ݒ肵����ꐅ���Ǐؔ�������ԋp����B
        return l_datFirstAdddepositOccurredDate;
    }

    /**
     * (get��ꐅ���Ǐؕۏ؋���)<BR>
     * (get��ꐅ���Ǐؕۏ؋���)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐؕۏ؋����v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐؕۏ؋����@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@��ꐅ���Ǐؕۏ؋�����ԋp����B<BR>
     * �@@�ia�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@true �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.calc�ۏ؋����i�j<BR>
     * <BR>
     * �@@�ib�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@false ���� this.���������Ǘ�Params == NULL �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ic�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@false ���� this.���������Ǘ�Params != NULL �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.���������Ǘ�Params.get�ۏ؋��a����()<BR>
     * @@return double
     * @@roseuid 48C9C0810268
     */
    public double getFirstAdddepositMarginDepositRate()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositMarginDepositRate()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositMarginDepositRate = 0;

        //�P�j�@@�ڋq�����̔�����s���B
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            //��ꐅ���Ǐؕۏ؋����@@=�@@0��ݒ肵�A�ԋp����B
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�Q�j�ȍ~�̏������s���B
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�Q�j�@@��ꐅ���Ǐؕۏ؋�����ԋp����B
            //�ia�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@true �̏ꍇ
            if (this.depositAutoTransferDivFlag)
            {
                //�m�ԋp�l�n
                //this.calc�ۏ؋����i�j
                l_dblFirstAdddepositMarginDepositRate = this.calcMarginDepositRate();
            }

            //�ib�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@false ���� this.���������Ǘ�Params == NULL �̏ꍇ
            if (!this.depositAutoTransferDivFlag && this.paymentRequisitMngParams == null)
            {
                //�m�ԋp�l�n
                //0
                l_dblFirstAdddepositMarginDepositRate = 0;
            }

            //�ic�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@false ���� this.���������Ǘ�Params != NULL �̏ꍇ
            if (!this.depositAutoTransferDivFlag && this.paymentRequisitMngParams != null)
            {
                //�m�ԋp�l�n
                //this.���������Ǘ�Params.get�ۏ؋��a����()
                l_dblFirstAdddepositMarginDepositRate = this.paymentRequisitMngParams.getMarginDepositRate();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositMarginDepositRate;
    }

    /**
     * (get��ꐅ���Ǐؕۏ؋��ێ���)<BR>
     * (get��ꐅ���Ǐؕۏ؋��ێ���)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐؕۏ؋��ێ����v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null ����<BR>
     * �@@�@@�@@�@@ this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("first.deposit.rate1") == NULL�̏ꍇ<BR>
     * �@@ �@@�m�ԋp�l�n<BR>
     * �@@ �@@0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params == null ����<BR>
     * �@@�@@�@@�@@ this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("first.deposit.rate1") != NULL�̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@ this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("first.deposit.rate1")<BR>
     * <BR>
     * �@@�@@�i3�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get��ꐅ���ۏ؋��ێ����i�j<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C1790105
     */
    public double getFirstAdddepositDepositRate()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositDepositRate()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositDepositRate = 0;

        //�P�j�@@�ڋq�����̔�����s���B
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //0
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositDepositRate;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null ����
            //this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("first.deposit.rate1") == NULL�̏ꍇ
            if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE1) == null)
            {
                //�m�ԋp�l�n
                //0
                l_dblFirstAdddepositDepositRate = 0;
            }
            //�i2�jthis.���������Ǘ�Params == null ����
            //this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("first.deposit.rate1") != NULL�̏ꍇ
            else if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE1) != null)
            {
                //�m�ԋp�l�n
                //this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("first.deposit.rate1")
                l_dblFirstAdddepositDepositRate = Double.parseDouble(
                    this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                        WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE1));
            }
            //�i3�jthis.���������Ǘ�Params != null �̏ꍇ
            else if (this.paymentRequisitMngParams != null)
            {
                //�m�ԋp�l�n
                //this.���������Ǘ�Params.get��ꐅ���ۏ؋��ێ����i�j
                l_dblFirstAdddepositDepositRate = this.paymentRequisitMngParams.getFirstDepositRate();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositDepositRate;
    }

    /**
     * (get��ꐅ���Ǐ؋��z)<BR>
     * (get��ꐅ���Ǐ؋��z)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐ؋��z�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null �̏ꍇ<BR>
     * �@@�@@�@@�i1�jthis.���������Ǘ�Params == null �̏ꍇ<BR>
     * �@@�@@�@@ 0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get��ꐅ���Ǐ؋��z�i�j<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C51E02F7
     */
    public double getFirstAdddepositAmount()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositAmount()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositAmount = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        //0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositAmount;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null �̏ꍇ
            //�m�ԋp�l�n
            //0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return l_dblFirstAdddepositAmount;
            }
            //�i2�jthis.���������Ǘ�Params != null �̏ꍇ
            //�m�ԋp�l�n
            //this.���������Ǘ�Params.get��ꐅ���Ǐ؋��z�i�j
            if (this.paymentRequisitMngParams != null)
            {
                log.exiting(STR_METHOD_NAME);
                return this.paymentRequisitMngParams.getFirstDepositAmount();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositAmount;
    }

    /**
     * (get��ꐅ���Ǐ،��ϕK�v�z)<BR>
     * (get��ꐅ���Ǐ،��ϕK�v�z)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐ،��ϕK�v�z�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@ 0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get��ꐅ���Ǐ،��ϕK�v�z�i�j<BR>
     * <BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C51E0307
     */
    public double getFirstAdddepositSettlement()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositSettlement()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositSettlement = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        //0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositSettlement;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null �̏�
            //�m�ԋp�l�n
            //0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return l_dblFirstAdddepositSettlement;
            }
            //�i2�jthis.���������Ǘ�Params != null �̏ꍇ
            //�m�ԋp�l�n
            //this.���������Ǘ�Params.get��ꐅ���Ǐ،��ϕK�v�z�i�j
            if (this.paymentRequisitMngParams != null)
            {
                log.exiting(STR_METHOD_NAME);
                return this.paymentRequisitMngParams.getFirstSettlement();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositSettlement;
    }

    /**
     * (get��ꐅ���Ǐؕۏ؋�����)<BR>
     * (get��ꐅ���Ǐؕۏ؋�����)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐؕۏ؋������v��ԋp����B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.calc�ۏ؋������i�j<BR>
     * @@return double
     * @@roseuid 48C9C5870332
     */
    public double getFirstAdddepositMarginDepositInDe()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositMarginDepositInDe()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositMarginDepositInDe = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        //0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositMarginDepositInDe;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�m�ԋp�l�n
        //this.calc�ۏ؋������i�j
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return this.calcMarginDepositInDe();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositMarginDepositInDe;
    }

    /**
     * (get��ꐅ���Ǐؕۏ؋������i�������z�j)<BR>
     * (get��ꐅ���Ǐؕۏ؋�����(�������z))<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐؕۏ؋�����(�������z)�v��ԋp����B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.calc�ۏ؋�����(�������z)�i�j<BR>
     * @@return double
     * @@roseuid 48C9C67D033A
     */
    public double getFirstAdddepositMarginDepositInDeExpect()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositMarginDepositInDeExpect()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositMarginDepositInDeExpect = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        //0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositMarginDepositInDeExpect;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�m�ԋp�l�n
        //this.calc�ۏ؋�����(�������z)�i�j
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return this.calcMarginDepositInDeExpect();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositMarginDepositInDeExpect;
    }

    /**
     * (get��ꐅ���Ǐ،��ύό���)<BR>
     * (get��ꐅ���Ǐ،��ύό���)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐ،��ύό��ʁv��ԋp����B<BR>
     * <BR>
     * �@@�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ <BR>
     * �@@�@@�m�ԋp�l�n  <BR>
     * �@@�@@0 <BR>
     * <BR>
     * �@@�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ <BR>
     * �@@�@@�m�ԋp�l�n  <BR>
     * �@@�@@this.get�����ԍό��ʑ���i�j<BR>
     * @@return double
     * @@roseuid 48C9C72101DD
     */
    public double getFirstAdddepositSettledContract()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositSettledContract()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositSettledContract = 0;

        //�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        //0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�m�ԋp�l�n
        //this.get�����ԍό��ʑ���i�j
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblFirstAdddepositSettledContract = this.getTodayRepayContractAmount();
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositSettledContract;
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositSettledContract;
    }

    /**
     * (get��ꐅ���Ǐؖ��������z)<BR>
     * (get��ꐅ���Ǐؖ��������z)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐؖ��������z�v���Z�o���A�ԋp����B <BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i�P�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@true �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.calc��ꐅ���Ǐ؋��z�i�j<BR>
     * <BR>
     * �@@�@@�i�Q�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@false �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.calc��ꐅ���Ǐؖ��������z�i�j<BR>
     * @@return double
     * @@roseuid 48C9C57502D5
     */
    public double getFirstAdddepositUncancelAmt()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositUncancelAmt()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositUncancelAmt = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //0
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositUncancelAmt;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i�P�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@true �̏ꍇ
            if (this.depositAutoTransferDivFlag)
            {
                //�m�ԋp�l�n
                //this.calc��ꐅ���Ǐ؋��z�i�j
                l_dblFirstAdddepositUncancelAmt = this.calcFirstAdddepositAmount();
            }
            //�i�Q�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@false �̏ꍇ
            else
            {
                //�m�ԋp�l�n
                //this.calc��ꐅ���Ǐؖ��������z�i�j
                l_dblFirstAdddepositUncancelAmt = this.calcFirstAdddepositUncancelAmt();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositUncancelAmt;
    }

    /**
     * (get��ꐅ���Ǐؖ��������ϕK�v�z)<BR>
     * (get��ꐅ���Ǐؖ��������ϕK�v�z)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐؖ��������ϕK�v�z�v���Z�o���A�ԋp����B <BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i�P�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@true �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.calc��ꐅ���Ǐ،��ϕK�v�z�i�j<BR>
     * <BR>
     * �@@�@@�i�Q�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@false �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.calc��ꐅ���Ǐؖ��������ϕK�v�z�i�j<BR>
     * @@return double
     * @@roseuid 48C9C7D202D1
     */
    public double getFirstAdddepositUncancelSettleRequiredAmt()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositUncancelSettleRequiredAmt()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositUncancelSettleRequiredAmt = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //0
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositUncancelSettleRequiredAmt;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i�P�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@true �̏ꍇ
            if (this.depositAutoTransferDivFlag)
            {
                //�m�ԋp�l�n
                //this.calc��ꐅ���Ǐ،��ϕK�v�z�i�j
                l_dblFirstAdddepositUncancelSettleRequiredAmt = this.calcFirstAdddepositSettlement();
            }
            //�i�Q�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@false �̏ꍇ
            else
            {
                //�m�ԋp�l�n
                //this.calc��ꐅ���Ǐؖ��������ϕK�v�z�i�j
                l_dblFirstAdddepositUncancelSettleRequiredAmt = this.calcFirstAdddepositUncancelSettleRequiredAmt();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositUncancelSettleRequiredAmt;
    }

    /**
     * (get��񐅏��Ǐ؊����i����2�j)<BR>
     * (get��񐅏��Ǐ؊���(����2))<BR>
     * <BR>
     * �ڋq�����̔����A��񐅏��Ǐ؊���(����2)��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@NULL<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�����J�n�������擾����B<BR>
     * �@@�ia�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@==�@@NULL�̏ꍇ<BR>
     * �@@�@@�m�擾��n <BR>
     * �@@�@@�����J�n�����@@=�@@0000<BR>
     * <BR>
     * �@@�ib�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@!=�@@NULL�̏ꍇ<BR>
     * �@@�@@�m�擾��n <BR>
     * �@@�@@�����J�n�����@@=�@@this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j<BR>
     * <BR>
     * �R�j�@@��񐅏��Ǐ؊���(����2)��ԋp����B<BR>
     * �@@�m�ԋp�l�n<BR>
     * �@@��񐅏��Ǐ؊���(����2)�@@=�@@����(T+0)�@@�{�@@�����J�n����<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E����(T+0)�@@�@@�@@�E�E�Ethis.get����(T+0)<BR>
     * �@@�@@�E�����J�n�����@@�@@�@@�E�E�E�Q�j�Ŏ擾���������J�n����<BR>
     * @@return Date
     * @@roseuid 48C9C8770302
     */
    public Date getSecondAdddepositCloseDate2()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositCloseDate2()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositCloseDate2 = null;

        //����(T+0)���擾����B
        Date l_dat0 = this.getDate(WEB3TPSpecifiedPointDef.T_0);

        //�����J�n����
        String l_strStartTime = null;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //NULL
            log.exiting(STR_METHOD_NAME);
            return l_datSecondAdddepositCloseDate2;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�Q�j�ȍ~�̏������s���B
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�Q�j�@@�����J�n�������擾����B
            //�ia�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
            //get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@==�@@NULL�̏ꍇ
            if (this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT) == null)
            {
                //�m�擾��n
                //�����J�n�����@@=�@@0000
                l_strStartTime = "0000";
            }
            //�ib�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
            //get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@!=�@@NULL�̏ꍇ
            else
            {
                //�m�擾��n
                //�����J�n�����@@=�@@this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
                //get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h)
                l_strStartTime = this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT);
            }

            //�R�j�@@��񐅏��Ǐ؊���(����2)��ԋp����B
            //�m�ԋp�l�n
            //��񐅏��Ǐ؊���(����2)�@@=�@@����(T+0)�@@�{�@@�����J�n����
            String l_strSecondAdddepositCloseDate2 =
                WEB3DateUtility.formatDate(l_dat0, WEB3GentradeTimeDef.DATE_FORMAT_YMD) + l_strStartTime;

            l_datSecondAdddepositCloseDate2 = WEB3DateUtility.getDate(
                l_strSecondAdddepositCloseDate2,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HM);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositCloseDate2;
    }

    /**
     * (get��񐅏��Ǐ؊����i����1�j)<BR>
     * (get��񐅏��Ǐ؊���(����1))<BR>
     * <BR>
     * �ڋq�����̔����A��񐅏��Ǐ؊���(����1)��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@NULL<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�����J�n�������擾����B<BR>
     * �@@�ia�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@==�@@NULL�̏ꍇ<BR>
     * �@@�@@�m�擾��n <BR>
     * �@@�@@�����J�n�����@@=�@@0000<BR>
     * <BR>
     * �@@�ib�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@!=�@@NULL�̏ꍇ<BR>
     * �@@�@@�m�擾��n <BR>
     * �@@�@@�����J�n�����@@=�@@this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j<BR>
     * <BR>
     * �R�j�@@��񐅏��Ǐ؊���(����1)��ԋp����B<BR>
     * �@@�m�ԋp�l�n<BR>
     * �@@��񐅏��Ǐ؊���(����1)�@@=�@@����(T+1)�@@�{�@@�����J�n����<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E����(T+1)�@@�@@�@@�E�E�Ethis.get����(T+1)<BR>
     * �@@�@@�E�����J�n�����@@�@@�@@�E�E�E�Q�j�Ŏ擾���������J�n����<BR>
     * @@return Date
     * @@roseuid 48C9C8AF0268
     */
    public Date getSecondAdddepositCloseDate1()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositCloseDate1()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositCloseDate1 = null;

        //����(T+1)���擾����B
        Date l_dat1 = this.getDate(WEB3TPSpecifiedPointDef.T_1);

        //�����J�n����
        String l_strStartTime = null;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //NULL
            l_datSecondAdddepositCloseDate1 = null;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�Q�j�ȍ~�̏������s���B
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�Q�j�@@�����J�n�������擾����B
            //�ia�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
            //get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@==�@@NULL�̏ꍇ
            if (this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT) == null)
            {
                //�m�擾��n
                //�����J�n�����@@=�@@0000
                l_strStartTime = "0000";
            }
            //�ib�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
            //get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@!=�@@NULL�̏ꍇ
            else
            {
                //�m�擾��n
                //�����J�n�����@@=�@@this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
                //get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h)
                l_strStartTime = this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT);
            }

            //�R�j�@@��񐅏��Ǐ؊���(����1)��ԋp����B
            //�m�ԋp�l�n
            //��񐅏��Ǐ؊���(����1)�@@=�@@����(T+1)�@@�{�@@�����J�n����
            String l_strSecondAdddepositCloseDate1 =
                WEB3DateUtility.formatDate(l_dat1, WEB3GentradeTimeDef.DATE_FORMAT_YMD) + l_strStartTime;

            l_datSecondAdddepositCloseDate1 = WEB3DateUtility.getDate(
                l_strSecondAdddepositCloseDate1,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HM);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositCloseDate1;
    }

    /**
     * (get��񐅏��Ǐ؊����i�������z�j)<BR>
     * (get��񐅏��Ǐ؊���(�������z))<BR>
     * <BR>
     * �ڋq�����ƕۏ؋������U�֌㔻��t���O�̔����A��񐅏��Ǐ؊���(�������z)��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@NULL<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�ۏ؋������U�֌㔻��t���O�̔�����s���B<BR>
     * <BR>
     * �@@�ia�jis�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �@@�ib�jis�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@NULL<BR>
     * <BR>
     * �R�j�@@�����J�n�������擾����B<BR>
     * �@@�ia�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@==�@@NULL�̏ꍇ<BR>
     * �@@�@@�m�擾��n <BR>
     * �@@�@@�����J�n�����@@=�@@0000<BR>
     * <BR>
     * �@@�ib�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@!=�@@NULL�̏ꍇ<BR>
     * �@@�@@�m�擾��n <BR>
     * �@@�@@�����J�n�����@@=�@@this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j<BR>
     * <BR>
     * �S�j�@@��񐅏��Ǐ؊���(�������z)��ԋp����B<BR>
     * �@@�m�ԋp�l�n<BR>
     * �@@��񐅏��Ǐ؊���(�������z)�@@=�@@����(T+2)�@@�{�@@�����J�n����<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E����(T+2)�@@�@@�@@�E�E�Ethis.get����(T+2)<BR>
     * �@@�@@�E�����J�n�����@@�@@�@@�E�E�E�R�j�Ŏ擾���������J�n����<BR>
     * @@return Date
     * @@roseuid 48C9C8AF03C0
     */
    public Date getSecondAdddepositCloseDateExpect()
    {
        final String STR_METHOD_NAME = "SecondAdddepositCloseDateExpect()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositCloseDateExpect = null;

        //����(T+2)���擾����B
        Date l_dat2 = this.getDate(WEB3TPSpecifiedPointDef.T_2);

        //�����J�n����
        String l_strStartTime = null;

        //�P�j�@@�ڋq�����̔�����s���B
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //NULL
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�Q�j�ȍ~�̏������s���B
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�Q�j�@@�ۏ؋������U�֌㔻��t���O�̔�����s���B
            //�ia�jis�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ
            //�R�j�ȍ~�̏������s���B
            if (this.isDepositAutoTransferDivFlag())
            {
                //�R�j�@@�����J�n�������擾����B
                //�ia�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
                //get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@==�@@NULL�̏ꍇ
                if (this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT) == null)
                {
                    //�m�擾��n
                    //�����J�n�����@@=�@@0000
                    l_strStartTime = "0000";
                }
                //�ib�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
                //get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j�@@!=�@@NULL�̏ꍇ
                else
                {
                    //�m�擾��n
                    //�����J�n�����@@=�@@this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
                    //get��Е��X�ʗ]�͌v�Z�����i�hsecond.deposit.compulsory.execution.timelimit�h�j
                    l_strStartTime = this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                        WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT);
                }
            }
            //�ib�jis�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ
            else
            {
                //�m�ԋp�l�n
                //NULL
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //�S�j�@@��񐅏��Ǐ؊���(�������z)��ԋp����B
            //�m�ԋp�l�n
            //��񐅏��Ǐ؊���(�������z)�@@=�@@����(T+2)�@@�{�@@�����J�n����
            String l_strSecondAdddepositCloseDateExpect =
                WEB3DateUtility.formatDate(l_dat2, WEB3GentradeTimeDef.DATE_FORMAT_YMD) + l_strStartTime;

            l_datSecondAdddepositCloseDateExpect = WEB3DateUtility.getDate(
                l_strSecondAdddepositCloseDateExpect,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HM);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositCloseDateExpect;
    }

    /**
     * (get��񐅏��Ǐؔ������i����2�j)<BR>
     * (get��񐅏��Ǐؔ�����(����2))<BR>
     * <BR>
     * �ڋq�����̔����A��񐅏��Ǐؔ�����(����2)��ԋp����B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@NULL<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.get����(T-2)<BR>
     * @@return Date
     * @@roseuid 48C9C9150135
     */
    public Date getSecondAdddepositDepositOccurredDate2()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositOccurredDate2()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositDepositOccurredDate2 = null;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //NULL
            log.exiting(STR_METHOD_NAME);
            return l_datSecondAdddepositDepositOccurredDate2;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //this.get����(T-2)
            log.exiting(STR_METHOD_NAME);
            return this.getDate(WEB3TPSpecifiedPointDef.T_MINUS2);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositDepositOccurredDate2;
    }

    /**
     * (get��񐅏��Ǐؔ������i����1�j)<BR>
     * (get��񐅏��Ǐؔ�����(����1))<BR>
     * <BR>
     * �ڋq�����̔����A��񐅏��Ǐؔ�����(����1)��ԋp����B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@NULL<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.get����(T-1)<BR>
     * @@roseuid 48C9C93A0146
     * @@return Date
     */
    public Date getSecondAdddepositDepositOccurredDate1()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositOccurredDate1()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositDepositOccurredDate1 = null;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //NULL
            log.exiting(STR_METHOD_NAME);
            return l_datSecondAdddepositDepositOccurredDate1;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //this.get����(T-1)
            log.exiting(STR_METHOD_NAME);
            return this.getDate(WEB3TPSpecifiedPointDef.T_MINUS1);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositDepositOccurredDate1;
    }

    /**
     * (get��񐅏��Ǐؔ������i�������z�j)<BR>
     * (get��񐅏��Ǐؔ�����(�������z))<BR>
     * <BR>
     * �ڋq�����ƕۏ؋������U�֌㔻��t���O�̔����A��񐅏��Ǐؔ�����(�������z)��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@NULL<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�ۏ؋������U�֌㔻��t���O�̔�����s���B<BR>
     * <BR>
     * �@@�ia�jis�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.get����(T+0)<BR>
     * <BR>
     * �@@�ib�jis�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@NULL<BR>
     * @@return Date
     * @@roseuid 48C9C93A0250
     */
    public Date getSecondAdddepositDepositOccurredDateExpect()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositOccurredDateExpect()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositDepositOccurredDateExpect = null;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�m�ԋp�l�n
            //NULL
            log.exiting(STR_METHOD_NAME);
            return l_datSecondAdddepositDepositOccurredDateExpect;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�Q�j�ȍ~�̏������s���B
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�Q�j�@@�ۏ؋������U�֌㔻��t���O�̔�����s���B
            //�ia�jis�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ
            if (this.isDepositAutoTransferDivFlag())
            {
                //�m�ԋp�l�n
                //this.get����(T+0)
                log.exiting(STR_METHOD_NAME);
                return this.getDate(WEB3TPSpecifiedPointDef.T_0);
            }
            //�ib�jis�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ
            else
            {
                //�m�ԋp�l�n
                //NULL
                log.exiting(STR_METHOD_NAME);
                return l_datSecondAdddepositDepositOccurredDateExpect;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositDepositOccurredDateExpect;
    }

    /**
     * (get��񐅏��Ǐؕۏ؋��ێ���)<BR>
     * (get��񐅏��Ǐؕۏ؋��ێ���)<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐؕۏ؋��ێ����v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null ����<BR>
     * �@@�@@ this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.rate") == NULL �̏ꍇ<BR>
     * �@@ �@@�m�ԋp�l�n<BR>
     * �@@�@@ 0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params == null ����<BR>
     * �@@�@@ this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.rate") != NULL �̏ꍇ<BR>
     * �@@ �@@�m�ԋp�l�n<BR>
     * �@@�@@  this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.rate")<BR>
     * <BR>
     * �@@�@@�i3�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get��񐅏��ۏ؋��ێ����i�j<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C977034C
     */
    public double getSecondAdddepositDepositRate()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositRate()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositDepositRate = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        //�@@0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null ����
            //this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.rate") == NULL �̏ꍇ
            //�m�ԋp�l�n
            //0
            if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_RATE) == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //�i2�jthis.���������Ǘ�Params == null ����
            //this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.rate") != NULL �̏ꍇ
            //�m�ԋp�l�n
            //this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.rate")
            if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_RATE) != null)
            {
                l_dblSecondAdddepositDepositRate =
                    Double.parseDouble(
                        this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_RATE));
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDepositRate;
            }
            else if (this.paymentRequisitMngParams != null)
            {
                //�i3�jthis.���������Ǘ�Params != null �̏ꍇ
                //�m�ԋp�l�n
                // this.���������Ǘ�Params.get��񐅏��ۏ؋��ێ����i�j
                l_dblSecondAdddepositDepositRate = this.paymentRequisitMngParams.getSecondDepositRate();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDepositRate;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositDepositRate;
    }

    /**
     * (get��񐅏��Ǐؕۏ؋��߂��ێ���)<BR>
     * (get��񐅏��Ǐؕۏ؋��߂��ێ���)<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐؕۏ؋��߂��ێ����v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null ����<BR>
     * �@@�@@�@@this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.back.rate") == NULL �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@ 0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params == null ����<BR>
     * �@@�@@�@@this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.back.rate") != NULL �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@ this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.back.rate")<BR>
     * <BR>
     * �@@�@@�i3�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@ this.���������Ǘ�Params.get��񐅏��ۏ؋��߂��ێ����i�j<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C993008D
     */
    public double getSecondAdddepositDepositBackRate()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositBackRate()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositDepositBackRate = 0;
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null ����
            //this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.back.rate") == NULL �̏ꍇ
            //�m�ԋp�l�n
            //0
            if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_BACK_RATE) == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //�i2�jthis.���������Ǘ�Params == null ����
            //this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.back.rate") != NULL �̏ꍇ
            //�m�ԋp�l�n
            //this.���Y�]�͏��<�M�p�ڋq>.�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����("second.deposit.back.rate")
            if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_BACK_RATE) != null)
            {
                l_dblSecondAdddepositDepositBackRate =
                    Double.parseDouble(
                        this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_BACK_RATE));
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDepositBackRate;
            }
            else
            {
                //�i3�jthis.���������Ǘ�Params != null �̏ꍇ
                //�m�ԋp�l�n
                //this.���������Ǘ�Params.get��񐅏��ۏ؋��߂��ێ����i�j
                l_dblSecondAdddepositDepositBackRate =
                    this.paymentRequisitMngParams.getSecondDepositBackRate();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDepositBackRate;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositDepositBackRate;
    }

    /**
     * (get��񐅏��Ǐؕۏ؋����i����2�j)<BR>
     * (get��񐅏��Ǐؕۏ؋���(����2))<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐؕۏ؋���(����2)�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get�ۏ؋��a����(�O��)�i�j<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C9FA0179
     */
    public double getSecondAdddepositMarginDepositRate2()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositMarginDepositRate2()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositMarginDepositRate2 = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null �̏ꍇ
            //�m�ԋp�l�n
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            else
            {
                //�i2�jthis.���������Ǘ�Params != null �̏ꍇ
                //�m�ԋp�l�n
                // this.���������Ǘ�Params.get�ۏ؋��a����(�O��)�i�j
                l_dblSecondAdddepositMarginDepositRate2 =
                    this.paymentRequisitMngParams.getMarginDepositRatePrebizdate();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositMarginDepositRate2;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositMarginDepositRate2;
    }

    /**
     * (get��񐅏��Ǐؕۏ؋����i����1�j)<BR>
     * (get��񐅏��Ǐؕۏ؋���(����1))<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐؕۏ؋���(����1)�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get�ۏ؋��a�����i�j<BR>
     * @@return double
     * @@roseuid 48C9CA1C0189
     */
    public double getSecondAdddepositMarginDepositRate1()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositMarginDepositRate1()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositMarginDepositRate1 = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null �̏�
            //�m�ԋp�l�n
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            else
            {
                //�i2�jthis.���������Ǘ�Params != null �̏ꍇ
                //�m�ԋp�l�n
                // this.���������Ǘ�Params.get�ۏ؋��a�����i�j
                l_dblSecondAdddepositMarginDepositRate1 =
                    this.paymentRequisitMngParams.getMarginDepositRate();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositMarginDepositRate1;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositMarginDepositRate1;
    }

    /**
     * (get��񐅏��Ǐؕۏ؋����i�������z�j)<BR>
     * (get��񐅏��Ǐؕۏ؋���(�������z))<BR>
     * <BR>
     * �ڋq�����ƕۏ؋������U�֌㔻��t���O�̔����A�u��񐅏��Ǐؕۏ؋���(�������z)�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�ۏ؋������U�֌㔻��t���O�̔�����s���B<BR>
     * <BR>
     * �@@�ia�jis�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.calc�ۏ؋����i�j<BR>
     * <BR>
     * �@@�ib�jis�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * @@return double
     * @@roseuid 48C9CA1C0254
     */
    public double getSecondAdddepositMarginDepositRateExpect()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositMarginDepositRateExpect()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositMarginDepositRateExpec = 0;

        // �P�j�@@�ڋq�����̔�����s��
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�Q�j�ȍ~�̏������s���B
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            // �Q�j�@@�ۏ؋������U�֌㔻��t���O�̔�����s���B
            //�ia�jis�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ
            //�m�ԋp�l�n
            // this.calc�ۏ؋����i�j
            if (this.isDepositAutoTransferDivFlag())
            {
                l_dblSecondAdddepositMarginDepositRateExpec =
                    this.calcMarginDepositRate();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositMarginDepositRateExpec;
            }
            else
            {
                //�ib�jis�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ
                //�m�ԋp�l�n
                // 0
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositMarginDepositRateExpec;
    }

    /**
     * (get��񐅏��Ǐ؋��z�i�������j)<BR>
     * (get��񐅏��Ǐ؋��z(������))<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐ؋��z(������)�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get��񐅏��Ǐؖ������i�j<BR>
     * @@return double
     * @@roseuid 48C9CA5B0070
     */
    public double getSecondAdddepositDepositNonPay()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositNonPay()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositDepositNonPay = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null �̏ꍇ
            //�m�ԋp�l�n
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //�i2�jthis.���������Ǘ�Params != null �̏ꍇ
            //�m�ԋp�l�n
            // this.���������Ǘ�Params.get��񐅏��Ǐؖ������i�j
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositDepositNonPay =
                    this.paymentRequisitMngParams.getSecondDepositNonPay();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDepositNonPay;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositDepositNonPay;
    }

    /**
     * (get��񐅏��Ǐ؋��z�i����2�j)<BR>
     * (get��񐅏��Ǐ؋��z(����2))<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐ؋��z(����(2))�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get��񐅏��Ǐؐ���(2)�i�j<BR>
     * @@roseuid 48C9CA5B0080
     * @@return double
     */
    public double getSecondAdddepositDeposit2()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDeposit2()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositDeposit2 = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null �̏ꍇ
            //�m�ԋp�l�n
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //�i2�jthis.���������Ǘ�Params != null �̏ꍇ
            //�m�ԋp�l�n
            // this.���������Ǘ�Params.get��񐅏��Ǐؐ���(2)�i�j
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositDeposit2 =
                    this.paymentRequisitMngParams.getSecondDeposit2();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDeposit2;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositDeposit2;
    }

    /**
     * (get��񐅏��Ǐ؋��z�i����1�j)<BR>
     * (get��񐅏��Ǐ؋��z(����1))<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐ؋��z(����(1))�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get��񐅏��Ǐؐ���(1)�i�j<BR>
     * @@return double
     * @@roseuid 48C9CA5B0081
     */
    public double getSecondAdddepositDeposit1()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDeposit1()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositDeposit1 = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏�
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null �̏ꍇ
            //�m�ԋp�l�n
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //�i2�jthis.���������Ǘ�Params != null �̏ꍇ
            //�m�ԋp�l�n
            // this.���������Ǘ�Params.get��񐅏��Ǐؐ���(1)�i�j
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositDeposit1 =
                    this.paymentRequisitMngParams.getSecondDeposit1();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDeposit1;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositDeposit1;
    }

    /**
     * (get��񐅏��Ǐ،��ϕK�v�z�i�������j)<BR>
     * (get��񐅏��Ǐ،��ϕK�v�z(������))<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐ،��ϕK�v�z(������)�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get��񐅏��Ǐؖ��������ϕK�v�z�i�j<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9CAC50013
     */
    public double getSecondAdddepositSettlementNonPay()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositSettlementNonPay()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositSettlementNonPay = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null �̏ꍇ
            //�m�ԋp�l�n
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //�i2�jthis.���������Ǘ�Params != null �̏ꍇ
            //�m�ԋp�l�n
            // this.���������Ǘ�Params.get��񐅏��Ǐؖ��������ϕK�v�z�i�j
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositSettlementNonPay =
                    this.paymentRequisitMngParams.getSecondSettlementNonPay();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositSettlementNonPay;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositSettlementNonPay;
    }

    /**
     * (get��񐅏��Ǐ،��ϕK�v�z�i����2�j)<BR>
     * (get��񐅏��Ǐ،��ϕK�v�z(����2))<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐ،��ϕK�v�z(����(2))�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get��񐅏��Ǐ،��ϕK�v�z(2)�i�j<BR>
     * @@return double
     * @@roseuid 48C9CAC50022
     */
    public double getSecondAdddepositSettlement2()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositSettlement2()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositSettlement2 = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null �̏ꍇ
            //�m�ԋp�l�n
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //�i2�jthis.���������Ǘ�Params != null �̏ꍇ
            //�m�ԋp�l�n
            // this.���������Ǘ�Params.get��񐅏��Ǐ،��ϕK�v�z(2)
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositSettlement2 =
                    this.paymentRequisitMngParams.getSecondSettlement2();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositSettlement2;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositSettlement2;
    }

    /**
     * (get��񐅏��Ǐ،��ϕK�v�z�i����1�j)<BR>
     * (get��񐅏��Ǐ،��ϕK�v�z(����1))<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐ،��ϕK�v�z(����(1))�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�i1�jthis.���������Ǘ�Params == null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@0<BR>
     * <BR>
     * �@@�@@�i2�jthis.���������Ǘ�Params != null �̏ꍇ<BR>
     * �@@�@@�@@�m�ԋp�l�n<BR>
     * �@@�@@�@@this.���������Ǘ�Params.get��񐅏��Ǐ،��ϕK�v�z(1)�i�j<BR>
     * @@return double
     * @@roseuid 48C9CAC50023
     */
    public double getSecondAdddepositSettlement1()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositSettlement1()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositSettlement1 = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�i1�jthis.���������Ǘ�Params == null �̏ꍇ
            //�m�ԋp�l�n
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //�i2�jthis.���������Ǘ�Params != null �̏ꍇ
            //�m�ԋp�l�n
            // this.���������Ǘ�Params.get��񐅏��Ǐ،��ϕK�v�z(1)�i�j
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositSettlement1 =
                    this.paymentRequisitMngParams.getSecondSettlement1();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositSettlement1;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositSettlement1;
    }

    /**
     * (get��񐅏��Ǐؕۏ؋�����)<BR>
     * (get��񐅏��Ǐؕۏ؋�����)<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐؕۏ؋������v��ԋp����B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.calc�ۏ؋������i�j<BR>
     * @@return double
     * @@roseuid 48C9CB1C02D1
     */
    public double getSecondAdddepositMarginDepositInDe()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositMarginDepositInDe()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositMarginDepositInDe = 0;

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // this.calc�ۏ؋������i�j
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblSecondAdddepositMarginDepositInDe = this.calcMarginDepositInDe();
            log.exiting(STR_METHOD_NAME);
            return l_dblSecondAdddepositMarginDepositInDe;
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositMarginDepositInDe;
    }

    /**
     * (get��񐅏��Ǐؕۏ؋������i�������z�j)<BR>
     * (get��񐅏��Ǐؕۏ؋�����(�������z))<BR>
     * <BR>
     * �ڋq�����ƕۏ؋������U�֌㔻��t���O�̔����A�u��񐅏��Ǐؕۏ؋�����(�������z)�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�ۏ؋������U�֌㔻��t���O�̔�����s���B<BR>
     * <BR>
     * �@@�ia�jis�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@this.calc�ۏ؋�����(�������z)�i�j<BR>
     * <BR>
     * �@@�ib�jis�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ<BR>
     * �@@�@@�m�ԋp�l�n<BR>
     * �@@�@@0<BR>
     * @@return double
     * @@roseuid 48C9CB1C02E1
     */
    public double getSecondAdddepositMarginDepositInDeExpect()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositMarginDepositInDeExpect()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositMarginDepositInDeExpect = 0;

        //�P�j�@@�ڋq�����̔�����s���B
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        // �Q�j�ȍ~�̏������s���B
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�Q�j�@@�ۏ؋������U�֌㔻��t���O�̔�����s���B
            //�ia�jis�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ
            //�m�ԋp�l�n
            // this.calc�ۏ؋�����(�������z)�i�j
            if (this.isDepositAutoTransferDivFlag())
            {
                l_dblSecondAdddepositMarginDepositInDeExpect = this.calcMarginDepositInDeExpect();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositMarginDepositInDeExpect;
            }
            //�ib�jis�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ
            //�m�ԋp�l�n
            // 0
            if (!this.isDepositAutoTransferDivFlag())
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositMarginDepositInDeExpect;
    }

    /**
     * (get��񐅏��Ǐ،��ύό���)<BR>
     * (get��񐅏��Ǐ،��ύό���)<BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐ،��ύό��ʁv��ԋp����B<BR>
     * <BR>
     * �@@�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ <BR>
     * �@@�@@�m�ԋp�l�n  <BR>
     * �@@�@@0 <BR>
     * <BR>
     * �@@�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ <BR>
     * �@@�@@�m�ԋp�l�n  <BR>
     * �@@�@@this.get�����ԍό��ʑ���i�j<BR>
     * @@return double
     * @@roseuid 48C9CB1C02E2
     */
    public double getSecondAdddepositSettledContract()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositSettledContract()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositSettledContract = 0;

        //�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // this.get�����ԍό��ʑ���i�j
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblSecondAdddepositSettledContract = this.getTodayRepayContractAmount();
            log.exiting(STR_METHOD_NAME);
            return l_dblSecondAdddepositSettledContract;
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositSettledContract;
    }

    /**
     * (create��񐅏��Ǐؖ��������)<BR>
     * (create��񐅏��Ǐؖ��������)<BR>
     * <BR>
     * �ڋq�����̔����A�v�Z�������ʂ��u��񐅏��Ǐؖ��������v�I�u�W�F�N�g�ɐݒ肵�A�ԋp����B<BR>
     * <BR>
     * �P�j�@@��񐅏��Ǐؖ��������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�@@��񐅏��Ǐؖ��������I�u�W�F�N�g���A���������ۂ̏����l�̂܂ܕԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �R�j�@@�����ۏ؋������z�̌v�Z���s���B<BR>
     * �@@[�v�Z��]<BR>
     * �@@�ia�j�����ۏ؋������z(������)�̌v�Z���s���B<BR>
     * �@@�@@�@@�����ۏ؋������z(������)�@@=�@@MIN�i�Ǐؖ�����,�@@�����ۏ؋������z�j<BR>
     * <BR>
     * �@@�ib�j�����ۏ؋������z(����2)�̌v�Z���s���B<BR>
     * �@@�@@�@@�����ۏ؋������z(����2)�@@=�@@MIN�i�Ǐؐ���(2),�@@�����ۏ؋������z�@@�|�@@(a)�̓����ۏ؋������z(������)�j<BR>
     * <BR>
     * �@@�ic�j�����ۏ؋������z(����1)�̌v�Z���s���B<BR>
     * �@@�@@�@@�����ۏ؋������z(����1)�@@=�@@�����ۏ؋������z�@@�|�@@(b)�̓����ۏ؋������z(����2)�@@�|�@@(a)�̓����ۏ؋������z(������)<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�Ǐؖ������@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؖ������i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؖ����� = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(2)�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؐ���(2)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(2) = 0 �Ƃ���B<BR>
     * �@@�@@�E�����ۏ؋������z�@@�@@�@@�E�E�Ethis.calc�����ۏ؋������z�i�j<BR>
     * <BR>
     * �S�j�@@(�ۏ؋�������)�Ǐ؋��z�̌v�Z���s���B<BR>
     * �@@[�v�Z��]<BR>
     * �@@�ia�j(�ۏ؋�������)�Ǐؖ������̌v�Z���s���B<BR>
     * �@@�@@�@@(�ۏ؋�������)�Ǐؖ������@@=�@@MAX�i�Ǐؖ������@@�|�@@�����ۏ؋������z(������),�@@0�j<BR>
     * <BR>
     * �@@�ib�j(�ۏ؋�������)�Ǐؖ��������ϕK�v�z�̌v�Z���s���B<BR>
     * �@@�@@�i�P�j�u�Ǐؖ������@@=�@@0�v�̏ꍇ<BR>
     * �@@�@@�@@�@@(�ۏ؋�������)�Ǐؖ��������ϕK�v�z�@@=�@@0<BR>
     * �@@�@@�i�Q�j(1)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@(�ۏ؋�������)�Ǐؖ��������ϕK�v�z�@@=�@@�Ǐؖ��������ϕK�v�z�@@�~�@@(a)��(�ۏ؋�������)�Ǐؖ������@@�^�@@�Ǐؖ�����<BR>
     * �@@�@@�@@�@@�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j<BR>
     * <BR>
     * �@@�ic�j(�ۏ؋�������)�Ǐؐ���(2)�̌v�Z���s���B<BR>
     * �@@�@@�@@(�ۏ؋�������)�Ǐؐ���(2)�@@=�@@MAX�i�Ǐؐ���(2)�@@-�@@�����ۏ؋������z(����2),�@@0�j<BR>
     * <BR>
     * �@@�id�j(�ۏ؋�������)�Ǐؐ���(2)���ϕK�v�z�̌v�Z���s���B<BR>
     * �@@�@@�i�P�j�u�Ǐؐ���(2)�@@=�@@0�v�̏ꍇ<BR>
     * �@@�@@�@@�@@(�ۏ؋�������)�Ǐؐ���(2)���ϕK�v�z�@@=�@@0<BR>
     * �@@�@@�i�Q�j(1)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@(�ۏ؋�������)�Ǐؐ���(2)���ϕK�v�z�@@=�@@�Ǐؐ���(2)���ϕK�v�z�@@�~�@@(c)��(�ۏ؋�������)�Ǐؐ���(2)�@@�^�@@�Ǐؐ���(2)<BR>
     * �@@�@@�@@�@@�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j<BR>
     * <BR>
     * �@@�ie�j(�ۏ؋�������)�Ǐؐ���(1)�̌v�Z���s���B<BR>
     * �@@�@@�@@(�ۏ؋�������)�Ǐؐ���(1)�@@=�@@MAX�i�Ǐؐ���(1)�@@-�@@�����ۏ؋������z(����1),�@@0�j<BR>
     * <BR>
     *  �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�Ǐؖ������@@�@@�@@�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؖ������i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؖ����� = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(2)�@@�@@�@@�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؐ���(2)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(2) = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(1)�@@�@@�@@�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؐ���(1)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(1) = 0 �Ƃ���B<BR>
     * �@@�@@�E�����ۏ؋������z(������)�@@�@@�@@�E�E�E�R-(a)�Ōv�Z���������ۏ؋������z(������)<BR>
     * �@@�@@�E�����ۏ؋������z(����2)�@@�@@�@@�E�E�E�R-(b)�Ōv�Z���������ۏ؋������z(����2)<BR>
     * �@@�@@�E�����ۏ؋������z(����1)�@@�@@�@@�E�E�E�R-(c)�Ōv�Z���������ۏ؋������z(����1)<BR>
     * �@@�@@�E�Ǐؖ��������ϕK�v�z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؖ��������ϕK�v�z�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؖ��������ϕK�v�z = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(2)���ϕK�v�z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐ،��ϕK�v�z(2)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(2)���ϕK�v�z = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(1)���ϕK�v�z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐ،��ϕK�v�z(1)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(1)���ϕK�v�z = 0 �Ƃ���B<BR>
     * <BR>
     * �T�j�@@�����ԍό��ʑ���̌v�Z���s���B<BR>
     * �@@[�v�Z��]<BR>
     * �@@�ia�j�����ԍό��ʑ��(������)�̌v�Z���s���B<BR>
     * �@@�@@�@@�����ԍό��ʑ��(������)�@@=�@@MIN�i(�ۏ؋�������)�Ǐؖ��������ϕK�v�z,�@@�����ԍό��ʑ���j<BR>
     * <BR>
     * �@@�ib�j�����ԍό��ʑ��(����2)�̌v�Z���s���B<BR>
     * �@@�@@�@@�����ԍό��ʑ��(����2)�@@=�@@MIN�i(�ۏ؋�������)�Ǐؐ���(2)���ϕK�v�z,�@@�����ԍό��ʑ���@@�|�@@(a)�̓����ԍό��ʑ��(������)�j<BR>
     * <BR>
     * �@@�ic�j�����ԍό��ʑ��(����1)�̌v�Z���s���B<BR>
     * �@@�@@�@@�����ԍό��ʑ��(����1)�@@=�@@�����ԍό��ʑ���@@�|�@@(b)�̓����ԍό��ʑ��(����2)�@@�|�@@(a)�̓����ԍό��ʑ��(������)<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E(�ۏ؋�������)�Ǐؖ��������ϕK�v�z�@@�@@�@@�E�E�E�S-(b)�Ōv�Z����(�ۏ؋�������)�Ǐؖ��������ϕK�v�z<BR>
     * �@@�@@�E(�ۏ؋�������)�Ǐؐ���(2)���ϕK�v�z�@@�@@�@@�E�E�E�S-(d)�Ōv�Z����(�ۏ؋�������)�Ǐؐ���(2)���ϕK�v�z<BR>
     * �@@�@@�E�����ԍό��ʑ���@@�@@�@@�E�E�Ethis.get�����ԍό��ʑ���i�j<BR>
     * <BR>
     * �U�j�@@�v�Z�����̔�����s���B<BR>
     * �@@�ia�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�htoday.clearance.determination.div�h�j�@@==�@@�h1�h�̏ꍇ<BR>
     * �@@�@@�@@�V�j�ȍ~�̏������s���B <BR>
     * <BR>
     * �@@�ib�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�htoday.clearance.determination.div�h�j�@@==�@@NULL<BR>
     * �@@�@@�@@���́Athis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�htoday.clearance.determination.div�h�j�@@!=�@@�h1�h�̏ꍇ<BR>
     * �@@�@@�@@�ȉ��̒l��ݒ肵�A�W�j�ȍ~�̏������s���B<BR>
     * �@@�@@�@@�@@�E�����������݊��Z�z(������)�@@=�@@0<BR>
     * �@@�@@�@@�@@�E�����������݊��Z�z(����2)�@@=�@@0<BR>
     * �@@�@@�@@�@@�E�����������݊��Z�z(����1)�@@=�@@0<BR>
     * <BR>
     * �V�j�@@�����������݊��Z�z�̌v�Z���s���B<BR>
     * �@@[�v�Z��]<BR>
     * �@@�ia�j�����������݊��Z�z(������)�̌v�Z���s���B<BR>
     * �@@�@@�i�P�j�u�Ǐؖ��������ϕK�v�z�@@=�@@0�v�̏ꍇ<BR>
     * �@@�@@�@@�@@�����������݊��Z�z(������)�@@=�@@0<BR>
     * �@@�@@�i�Q�j(1)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�����������݊��Z�z(������)�@@=�@@�Ǐؖ������@@�~�@@�����ԍό��ʑ��(������)�@@�^�@@�Ǐؖ��������ϕK�v�z<BR>
     * �@@�@@�@@�@@�i�v�Z���ʂ̏����_�ȉ���؂�̂ĂƂ���B�j<BR>
     * <BR>
     * �@@�ib�j�����������݊��Z�z(����2)�̌v�Z���s���B<BR>
     * �@@�@@�i�P�j�u�Ǐؐ���(2)���ϕK�v�z�@@=�@@0�v�̏ꍇ<BR>
     * �@@�@@�@@�@@�����������݊��Z�z(����2)�@@=�@@0<BR>
     * �@@�@@�i�Q�j(1)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�����������݊��Z�z(����2)�@@=�@@�Ǐؐ���(2)�@@�~�@@�����ԍό��ʑ��(����2)�@@�^�@@�Ǐؐ���(2)���ϕK�v�z<BR>
     * �@@�@@�@@�@@�i�v�Z���ʂ̏����_�ȉ���؂�̂ĂƂ���B�j<BR>
     * <BR>
     * �@@�ic�j�����������݊��Z�z(����1)�̌v�Z���s���B<BR>
     * �@@�@@�i�P�j�u�Ǐؐ���(1)���ϕK�v�z�@@=�@@0�v�̏ꍇ<BR>
     * �@@�@@�@@�@@�����������݊��Z�z(����1)�@@=�@@0<BR>
     * �@@�@@�i�Q�j(1)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�����������݊��Z�z(����1)�@@=�@@�Ǐؐ���(1)�@@�~�@@�����ԍό��ʑ��(����1)�@@�^�@@�Ǐؐ���(1)���ϕK�v�z<BR>
     * �@@�@@�@@�@@�i�v�Z���ʂ̏����_�ȉ���؂�̂ĂƂ���B�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�Ǐؖ��������ϕK�v�z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؖ��������ϕK�v�z�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؖ��������ϕK�v�z = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(2)���ϕK�v�z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐ،��ϕK�v�z(2)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(2)���ϕK�v�z = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(1)���ϕK�v�z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐ،��ϕK�v�z(1)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(1)���ϕK�v�z = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؖ������@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؖ������i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؖ����� = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(2)�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؐ���(2)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(2) = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(1)�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؐ���(1)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(1) = 0 �Ƃ���B<BR>
     * �@@�@@�E�����ԍό��ʑ��(������)�@@�@@�@@�E�E�E�T-(a)�Ōv�Z���������ԍό��ʑ��(������)<BR>
     * �@@�@@�E�����ԍό��ʑ��(����2)�@@�@@�@@�E�E�E�T-(b)�Ōv�Z���������ԍό��ʑ��(����2)<BR>
     * �@@�@@�E�����ԍό��ʑ��(����1)�@@�@@�@@�E�E�E�T-(c)�Ōv�Z���������ԍό��ʑ��(����1)<BR>
     * <BR>
     * �W�j�@@(������)�Ǐ؋��z��(������)���ϕK�v�z�̌v�Z���s���B<BR>
     * �@@[�v�Z��]<BR>
     * �@@�ia�j�Ǐ؋��z(������)�̌v�Z���s���B<BR>
     * �@@�@@�@@�Ǐ؋��z(������)�@@=�@@MAX�i(�ۏ؋�������)�Ǐؖ������@@�|�@@�����������݊��Z�z(������),�@@0�j<BR>
     * <BR>
     * �@@�ib�j���ϕK�v�z(������)�̌v�Z���s���B<BR>
     * �@@�@@�i�P�j�u�Ǐؖ������@@=�@@0�v�̏ꍇ<BR>
     * �@@�@@�@@�@@���ϕK�v�z(������)�@@=�@@0<BR>
     * �@@�@@�i�Q�j(1)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@���ϕK�v�z(������)�@@=�@@�Ǐؖ��������ϕK�v�z�@@�~�@@(a)�̒Ǐ؋��z(������)�@@�^�@@�Ǐؖ�����<BR>
     * �@@�@@�@@�@@�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j<BR>
     * <BR>
     * �@@�ic�j�Ǐ؋��z(����2)�̌v�Z���s���B<BR>
     * �@@�@@�@@�Ǐ؋��z(����2)�@@=�@@MAX�i(�ۏ؋�������)�Ǐؐ���(2)�@@�|�@@�����������݊��Z�z(����2),�@@0�j<BR>
     * <BR>
     * �@@�id�j���ϕK�v�z(����2)�̌v�Z���s���B<BR>
     * �@@�@@�i�P�j�u�Ǐؐ���(2)�@@=�@@0�v�̏ꍇ<BR>
     * �@@�@@�@@�@@���ϕK�v�z(����2)�@@=�@@0<BR>
     * �@@�@@�i�Q�j(1)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@���ϕK�v�z(����2)�@@=�@@�Ǐؐ���(2)���ϕK�v�z�@@�~�@@(c)�̒Ǐ؋��z(����2)�@@�^�@@�Ǐؐ���(2)<BR>
     * �@@�@@�@@�@@�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j<BR>
     * <BR>
     * �@@�ie�j�Ǐ؋��z(����1)�̌v�Z���s���B<BR>
     * �@@�@@�@@�Ǐ؋��z(����1)�@@=�@@MAX�i(�ۏ؋�������)�Ǐؐ���(1)�@@�|�@@�����������݊��Z�z(����1),�@@0�j<BR>
     * <BR>
     * �@@�if�j���ϕK�v�z(����1)�̌v�Z���s���B<BR>
     * �@@�@@�i�P�j�u�Ǐؐ���(1)�@@=�@@0�v�̏ꍇ<BR>
     * �@@�@@�@@�@@���ϕK�v�z(����1)�@@=�@@0<BR>
     * �@@�@@�i�Q�j(1)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@���ϕK�v�z(����1)�@@=�@@�Ǐؐ���(1)���ϕK�v�z�@@�~�@@(e)�̒Ǐ؋��z(����1)�@@�^�@@�Ǐؐ���(1)<BR>
     * �@@�@@�@@�@@�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E(�ۏ؋�������)�Ǐؖ������@@�@@�@@�E�E�E�S-(a)�Ōv�Z����(�ۏ؋�������)�Ǐؖ�����<BR>
     * �@@�@@�E(�ۏ؋�������)�Ǐؐ���(2)�@@�@@�@@�E�E�E�S-(c)�Ōv�Z����(�ۏ؋�������)�Ǐؐ���(2)<BR>
     * �@@�@@�E(�ۏ؋�������)�Ǐؐ���(1)�@@�@@�@@�E�E�E�S-(e)�Ōv�Z����(�ۏ؋�������)�Ǐؐ���(1)<BR>
     * �@@�@@�E�����������݊��Z�z(������)�@@�@@�@@�E�E�E�U-(b)���͂V-(a)�Ōv�Z���������������݊��Z�z(������)<BR>
     * �@@�@@�E�����������݊��Z�z(����2)�@@�@@�@@�E�E�E�U-(b)���͂V-(b)�Ōv�Z���������������݊��Z�z(����2)<BR>
     * �@@�@@�E�����������݊��Z�z(����1)�@@�@@�@@�E�E�E�U-(b)���͂V-(c)�Ōv�Z���������������݊��Z�z(����1)<BR>
     * �@@�@@�E�Ǐؖ��������ϕK�v�z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؖ��������ϕK�v�z�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؖ��������ϕK�v�z = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(2)���ϕK�v�z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐ،��ϕK�v�z(2)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(2)���ϕK�v�z = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(1)���ϕK�v�z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐ،��ϕK�v�z(1)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(1)���ϕK�v�z = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؖ������@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؖ������i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؖ����� = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(2)�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؐ���(2)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(2) = 0 �Ƃ���B<BR>
     * �@@�@@�E�Ǐؐ���(1)�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��񐅏��Ǐؐ���(1)�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A�Ǐؐ���(1) = 0 �Ƃ���B<BR>
     * <BR>
     * �X�j�@@�ۏ؋������U�ւ̔�����s���B<BR>
     * �@@�ia�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@FALSE(�ۏ؋������U�֑O)�̏ꍇ<BR>
     *  �@@�@@�@@�W�Ōv�Z�������ڂ��񐅏��Ǐؖ��������I�u�W�F�N�g�ɐݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@TRUE(�ۏ؋������U�֌�)�̏ꍇ<BR>
     * �@@�@@�@@�P�O�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �P�O�j�@@�Ǐ؋��z(�������z)�ƌ��ϕK�v�z(�������z)�̌v�Z���s���B<BR>
     * �@@[�v�Z��]<BR>
     * �@@�ia�j�Ǐ؋��z(�������z)�̌v�Z���s���B<BR>
     * �@@�@@�@@�Ǐ؋��z(�������z)�@@=�@@MAX�i�Ǐ؋��z�@@�|�@@�Ǐ؋��z(����1)�@@�|�@@�Ǐ؋��z(����2)�@@�|�@@�Ǐ؋��z(������),�@@0�j<BR>
     * <BR>
     * �@@�ib�j���ϕK�v�z(�������z)�̌v�Z���s���B<BR>
     * �@@�@@�@@���ϕK�v�z(�������z)�@@=�@@MAX�i���ϕK�v�z�@@�|�@@���ϕK�v�z(����1)�@@�|�@@���ϕK�v�z(����2)�@@�|�@@���ϕK�v�z(������),�@@0�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�Ǐ؋��z�@@�@@�@@�E�E�Ethis.calc��񐅏��Ǐ؋��z�i�j<BR>
     * �@@�@@�E�Ǐ؋��z(������)�@@�@@�@@�E�E�E�W-(a)�Ōv�Z�����Ǐ؋��z(������)<BR>
     * �@@�@@�E�Ǐ؋��z(����2)�@@�@@�@@�E�E�E�W-(c)�Ōv�Z�����Ǐ؋��z(����2)<BR>
     * �@@�@@�E���ϕK�v�z�@@�@@�@@�E�E�Ethis.calc��񐅏��Ǐ،��ϕK�v�z�i�j<BR>
     * �@@�@@�E���ϕK�v�z(������)�@@�@@�@@�E�E�E�W-(b)�Ōv�Z�������ϕK�v�z(������)<BR>
     * �@@�@@�E���ϕK�v�z(����2)�@@�@@�@@�E�E�E�W-(d)�Ōv�Z�������ϕK�v�z(����2)<BR>
     * <BR>
     * �P�P�j�@@�W�ƂP�O�Ōv�Z�������ڂ��񐅏��Ǐؖ��������I�u�W�F�N�g�ɐݒ肵�A�ԋp����B<BR>
     * @@return WEB3TPSecondAdddepositNotClearInfo
     * @@roseuid 48CA25000001
     */
    public WEB3TPSecondAdddepositNotClearInfo createSecondAdddepositNotClearInfo()
    {
        final String STR_METHOD_NAME = "createSecondAdddepositNotClearInfo()";
        log.entering(STR_METHOD_NAME);

        //�����ۏ؋������z(������)
        double l_dblTodayDepositPaymentAmountNonPay = 0;
        //�����ۏ؋������z(����2)
        double l_dblTodayDepositPaymentAmount2 = 0;
        //�����ۏ؋������z(����1)
        double l_dblTodayDepositPaymentAmount1 = 0;
        //(�ۏ؋�������)�Ǐؖ�����
        double l_dblSecondDepositNonPayAfter = 0;
        //(�ۏ؋�������)�Ǐؖ��������ϕK�v�z
        double l_dblSecondSettlementNonPayAfter = 0;
        //(�ۏ؋�������)�Ǐؐ���(2)
        double l_dblSecondDeposit2After = 0;
        //(�ۏ؋�������)�Ǐؐ���(2)���ϕK�v�z
        double l_dblSecondSettlement2After = 0;
        //(�ۏ؋�������)�Ǐؐ���(1)
        double l_dblSecondDeposit1After = 0;
        //�����ԍό��ʑ��(������)
        double l_dblTodayRepayContractAmountNonPay = 0;
        //�����ԍό��ʑ��(����2)
        double l_dblTodayRepayContractAmount2 = 0;
        //�����ԍό��ʑ��(����1)
        double l_dblTodayRepayContractAmount1 = 0;
        //�����������݊��Z�z(������)
        double l_dblTodayEliminateAmountNonPay = 0;
        //�����������݊��Z�z(����2)
        double l_dblTodayEliminateAmount2 = 0;
        //�����������݊��Z�z(����1)
        double l_dblTodayEliminateAmount1 = 0;
        //�Ǐ؋��z(������)
        double l_dblDepositNonPay = 0;
        //���ϕK�v�z(������)
        double l_dblSettlementNonPay = 0;
        //�Ǐ؋��z(����2)
        double l_dblDeposit2 = 0;
        //���ϕK�v�z(����2)
        double l_dblSettlement2 = 0;
        //�Ǐ؋��z(����1)
        double l_dblDeposit1 = 0;
        //���ϕK�v�z(����1)
        double l_dblSettlement1 = 0;
        //�Ǐ؋��z(�������z)
        double l_dblDepositExpect = 0;
        //���ϕK�v�z(�������z)
        double l_dblSettlementExpect = 0;

        //�Ǐؖ��������擾����B
        double l_dblSecondDepositNonPay = 0;
        //�Ǐؐ���(2)���擾����B
        double l_dblSecondDeposit2 = 0;
        //�Ǐؐ���(1)���擾����B
        double l_dblSecondDeposit1 = 0;
        //�Ǐؖ��������ϕK�v�z���擾����B
        double l_dblSecondSettlementNonPay = 0;
        //�Ǐؐ���(2)���ϕK�v�z���擾����B
        double l_dblSecondSettlement2 = 0;
        //�Ǐؐ���(1)���ϕK�v�z���擾����B
        double l_dblSecondSettlement1 = 0;
        if (this.paymentRequisitMngParams == null)
        {
            l_dblSecondDepositNonPay = 0;
            l_dblSecondDeposit2 = 0;
            l_dblSecondDeposit1 = 0;
            l_dblSecondSettlementNonPay = 0;
            l_dblSecondSettlement2 = 0;
            l_dblSecondSettlement1 = 0;
        }
        else
        {
            l_dblSecondDepositNonPay = this.paymentRequisitMngParams.getSecondDepositNonPay();
            l_dblSecondDeposit2 = this.paymentRequisitMngParams.getSecondDeposit2();
            l_dblSecondDeposit1 = this.paymentRequisitMngParams.getSecondDeposit1();
            l_dblSecondSettlementNonPay = this.paymentRequisitMngParams.getSecondSettlementNonPay();
            l_dblSecondSettlement2 = this.paymentRequisitMngParams.getSecondSettlement2();
            l_dblSecondSettlement1 = this.paymentRequisitMngParams.getSecondSettlement1();
        }

        //�����ۏ؋������z���擾����B
        double l_dblTodayDepositPaymentAmount = this.calcTodayDepositPaymentAmount();
        //�����ԍό��ʑ�����擾����B
        double l_dblTodayRepayContractAmount = this.getTodayRepayContractAmount();
        //�Ǐ؋��z���擾����B
        double l_dblSecondAdddepositAmount = this.calcSecondAdddepositAmount();
        //���ϕK�v�z���擾����B
        double l_dblSecondAdddepositSettlement = this.calcSecondAdddepositSettlement();

        //�P�j�@@��񐅏��Ǐؖ��������I�u�W�F�N�g�𐶐�����B
        WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo = new WEB3TPSecondAdddepositNotClearInfo();

        //�Q�j�@@�ڋq�����̔�����s���B
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            //��񐅏��Ǐؖ��������I�u�W�F�N�g���A���������ۂ̏����l�̂܂ܕԋp����B
            return l_secondAdddepositNotClearInfo;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�R�j�ȍ~�̏������s���B
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�R�j�@@�����ۏ؋������z�̌v�Z���s���B
            //[�v�Z��]
            //�ia�j�����ۏ؋������z(������)�̌v�Z���s���B
            //�����ۏ؋������z(������)�@@=�@@MIN�i�Ǐؖ�����,�@@�����ۏ؋������z�j
            l_dblTodayDepositPaymentAmountNonPay = Math.min(l_dblSecondDepositNonPay, l_dblTodayDepositPaymentAmount);

            //�ib�j�����ۏ؋������z(����2)�̌v�Z���s���B
            //�����ۏ؋������z(����2)�@@=�@@MIN�i�Ǐؐ���(2),�@@�����ۏ؋������z�@@�|�@@(a)�̓����ۏ؋������z(������)�j
            double l_dblAmount0 =
                new BigDecimal(l_dblTodayDepositPaymentAmount + "").subtract(
                    new BigDecimal(l_dblTodayDepositPaymentAmountNonPay + "")).doubleValue();
            l_dblTodayDepositPaymentAmount2 = Math.min(l_dblSecondDeposit2, l_dblAmount0);

            //�ic�j�����ۏ؋������z(����1)�̌v�Z���s���B
            //�����ۏ؋������z(����1)�@@=�@@�����ۏ؋������z�@@�|�@@(b)�̓����ۏ؋������z(����2)�@@�|�@@(a)�̓����ۏ؋������z(������)
            l_dblTodayDepositPaymentAmount1 =
                new BigDecimal(l_dblTodayDepositPaymentAmount + "").subtract(
                    new BigDecimal(l_dblTodayDepositPaymentAmount2 + "")).subtract(
                        new BigDecimal(l_dblTodayDepositPaymentAmountNonPay + "")).doubleValue();

            //�S�j�@@(�ۏ؋�������)�Ǐ؋��z�̌v�Z���s���B
            //[�v�Z��]
            //�ia�j(�ۏ؋�������)�Ǐؖ������̌v�Z���s���B
            //(�ۏ؋�������)�Ǐؖ������@@=�@@MAX�i�Ǐؖ������@@�|�@@�����ۏ؋������z(������),�@@0�j
            double l_dblAmount1 =
                new BigDecimal(l_dblSecondDepositNonPay + "").subtract(
                    new BigDecimal(l_dblTodayDepositPaymentAmountNonPay + "")).doubleValue();
            l_dblSecondDepositNonPayAfter = Math.max(l_dblAmount1, 0);

            //�ib�j(�ۏ؋�������)�Ǐؖ��������ϕK�v�z�̌v�Z���s���B
            //�i�P�j�u�Ǐؖ������@@=�@@0�v�̏ꍇ
            if (GtlUtils.Double.isZero(l_dblSecondDepositNonPay))
            {
                //(�ۏ؋�������)�Ǐؖ��������ϕK�v�z�@@=�@@0
                l_dblSecondSettlementNonPayAfter = 0;
            }
            //�i�Q�j(1)�ȊO�̏ꍇ
            else
            {
                //(�ۏ؋�������)�Ǐؖ��������ϕK�v�z�@@=�@@�Ǐؖ��������ϕK�v�z�@@�~�@@(a)��(�ۏ؋�������)�Ǐؖ������@@�^�@@�Ǐؖ�����
                //�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j
                l_dblSecondSettlementNonPayAfter =
                    new BigDecimal(l_dblSecondSettlementNonPay + "").multiply(
                        new BigDecimal(l_dblSecondDepositNonPayAfter + "")).divide(
                            new BigDecimal(l_dblSecondDepositNonPay + ""), 0, BigDecimal.ROUND_CEILING).doubleValue();
            }

            //�ic�j(�ۏ؋�������)�Ǐؐ���(2)�̌v�Z���s���B
            //(�ۏ؋�������)�Ǐؐ���(2)�@@=�@@MAX�i�Ǐؐ���(2)�@@-�@@�����ۏ؋������z(����2),�@@0�j
            double l_dblAmount2 =
                new BigDecimal(l_dblSecondDeposit2 + "").subtract(
                    new BigDecimal(l_dblTodayDepositPaymentAmount2 + "")).doubleValue();
            l_dblSecondDeposit2After = Math.max(l_dblAmount2, 0);

            //�id�j(�ۏ؋�������)�Ǐؐ���(2)���ϕK�v�z�̌v�Z���s���B
            //�i�P�j�u�Ǐؐ���(2)�@@=�@@0�v�̏ꍇ
            if (GtlUtils.Double.isZero(l_dblSecondDeposit2))
            {
                //(�ۏ؋�������)�Ǐؐ���(2)���ϕK�v�z�@@=�@@0
                l_dblSecondSettlement2After = 0;
            }
            //�i�Q�j(1)�ȊO�̏ꍇ
            else
            {
                //(�ۏ؋�������)�Ǐؐ���(2)���ϕK�v�z�@@=�@@�Ǐؐ���(2)���ϕK�v�z�@@�~�@@(c)��(�ۏ؋�������)�Ǐؐ���(2)�@@�^�@@�Ǐؐ���(2)
                //�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j
                l_dblSecondSettlement2After =
                    new BigDecimal(l_dblSecondSettlement2 + "").multiply(
                        new BigDecimal(l_dblSecondDeposit2After + "")).divide(
                            new BigDecimal(l_dblSecondDeposit2 + ""), 0, BigDecimal.ROUND_CEILING).doubleValue();
            }

            //�ie�j(�ۏ؋�������)�Ǐؐ���(1)�̌v�Z���s���B
            //(�ۏ؋�������)�Ǐؐ���(1)�@@=�@@MAX�i�Ǐؐ���(1)�@@-�@@�����ۏ؋������z(����1),�@@0�j
            double l_dblAmount3 =
                new BigDecimal(l_dblSecondDeposit1 + "").subtract(
                    new BigDecimal(l_dblTodayDepositPaymentAmount1 + "")).doubleValue();
            l_dblSecondDeposit1After = Math.max(l_dblAmount3, 0);

            //�T�j�@@�����ԍό��ʑ���̌v�Z���s���B
            //[�v�Z��]
            //�ia�j�����ԍό��ʑ��(������)�̌v�Z���s���B
            //�����ԍό��ʑ��(������)�@@=�@@MIN�i(�ۏ؋�������)�Ǐؖ��������ϕK�v�z,�@@�����ԍό��ʑ���j
            l_dblTodayRepayContractAmountNonPay =
                Math.min(l_dblSecondSettlementNonPayAfter,
                    l_dblTodayRepayContractAmount);

            //�ib�j�����ԍό��ʑ��(����2)�̌v�Z���s���B
            //�����ԍό��ʑ��(����2)�@@=�@@MIN�i(�ۏ؋�������)�Ǐؐ���(2)���ϕK�v�z,�@@�����ԍό��ʑ���@@�|�@@(a)�̓����ԍό��ʑ��(������)�j
            double l_dblAmount4 =
                new BigDecimal(l_dblTodayRepayContractAmount + "").subtract(
                    new BigDecimal(l_dblTodayRepayContractAmountNonPay + "")).doubleValue();
            l_dblTodayRepayContractAmount2 = Math.min(l_dblSecondSettlement2After, l_dblAmount4);

            //�ic�j�����ԍό��ʑ��(����1)�̌v�Z���s���B
            //�����ԍό��ʑ��(����1)�@@=�@@�����ԍό��ʑ���@@�|�@@(b)�̓����ԍό��ʑ��(����2)�@@�|�@@(a)�̓����ԍό��ʑ��(������)
            l_dblTodayRepayContractAmount1 =
                new BigDecimal(l_dblTodayRepayContractAmount + "").subtract(
                    new BigDecimal(l_dblTodayRepayContractAmount2 + "")).subtract(
                        new BigDecimal(l_dblTodayRepayContractAmountNonPay + "")).doubleValue();

            //�U�j�@@�v�Z�����̔�����s���B
            //a�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
            //get��Е��X�ʗ]�͌v�Z�����i�htoday.clearance.determination.div�h�j�@@==�@@�h1�h�̏ꍇ
            //�V�j�ȍ~�̏������s���B
            String l_strInstBranCalcCondition =
                this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.TODAY_CLEARANCE_DETERMINATION_DIV);

            if (WEB3TodayClearanceDeterminationDivDef.EXECUTE.equals(l_strInstBranCalcCondition))
            {
                //�V�j�@@�����������݊��Z�z�̌v�Z���s���B
                //[�v�Z��]
                //�ia�j�����������݊��Z�z(������)�̌v�Z���s���B
                //�i�P�j�u�Ǐؖ��������ϕK�v�z�@@=�@@0�v�̏ꍇ
                if (GtlUtils.Double.isZero(l_dblSecondSettlementNonPay))
                {
                    //�����������݊��Z�z(������)�@@=�@@0
                    l_dblTodayEliminateAmountNonPay = 0;
                }
                //�i�Q�j(1)�ȊO�̏ꍇ
                else
                {
                    //�����������݊��Z�z(������)�@@=�@@�Ǐؖ������@@�~�@@�����ԍό��ʑ��(������)�@@�^�@@�Ǐؖ��������ϕK�v�z
                    //�i�v�Z���ʂ̏����_�ȉ���؂�̂ĂƂ���B�j
                    l_dblTodayEliminateAmountNonPay =
                        new BigDecimal(l_dblSecondDepositNonPay + "").multiply(
                            new BigDecimal(l_dblTodayRepayContractAmountNonPay + "")).divide(
                                new BigDecimal(l_dblSecondSettlementNonPay + ""),
                                0, BigDecimal.ROUND_DOWN).doubleValue();
                }

                //�ib�j�����������݊��Z�z(����2)�̌v�Z���s���B
                //�i�P�j�u�Ǐؐ���(2)���ϕK�v�z�@@=�@@0�v�̏ꍇ
                if (GtlUtils.Double.isZero(l_dblSecondSettlement2))
                {
                    //�����������݊��Z�z(����2)�@@=�@@0
                    l_dblTodayEliminateAmount2 = 0;
                }
                //�i�Q�j(1)�ȊO�̏ꍇ
                else
                {
                    //�����������݊��Z�z(����2)�@@=�@@�Ǐؐ���(2)�@@�~�@@�����ԍό��ʑ��(����2)�@@�^�@@�Ǐؐ���(2)���ϕK�v�z
                    //�i�v�Z���ʂ̏����_�ȉ���؂�̂ĂƂ���B�j
                    l_dblTodayEliminateAmount2 =
                        new BigDecimal(l_dblSecondDeposit2 + "").multiply(
                            new BigDecimal(l_dblTodayRepayContractAmount2 + "")).divide(
                                new BigDecimal(l_dblSecondSettlement2 + ""), 0, BigDecimal.ROUND_DOWN).doubleValue();
                }

                //�ic�j�����������݊��Z�z(����1)�̌v�Z���s���B
                //�i�P�j�u�Ǐؐ���(1)���ϕK�v�z�@@=�@@0�v�̏ꍇ
                if (GtlUtils.Double.isZero(l_dblSecondSettlement1))
                {
                    //�����������݊��Z�z(����1)�@@=�@@0
                    l_dblTodayEliminateAmount1 = 0;
                }
                //�i�Q�j(1)�ȊO�̏ꍇ
                else
                {
                    //�����������݊��Z�z(����1)�@@=�@@�Ǐؐ���(1)�@@�~�@@�����ԍό��ʑ��(����1)�@@�^�@@�Ǐؐ���(1)���ϕK�v�z
                    //�i�v�Z���ʂ̏����_�ȉ���؂�̂ĂƂ���B�j
                    l_dblTodayEliminateAmount1 =
                        new BigDecimal(l_dblSecondDeposit1 + "").multiply(
                            new BigDecimal(l_dblTodayRepayContractAmount1 + "")).divide(
                                new BigDecimal(l_dblSecondSettlement1 + ""), 0, BigDecimal.ROUND_DOWN).doubleValue();
                }
            }
            //�ib�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
            //get��Е��X�ʗ]�͌v�Z�����i�htoday.clearance.determination.div�h�j�@@==�@@NULL
            //���́Athis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
            //get��Е��X�ʗ]�͌v�Z�����i�htoday.clearance.determination.div�h�j�@@!=�@@�h1�h�̏ꍇ
            //�ȉ��̒l��ݒ肵�A�W�j�ȍ~�̏������s���B
            else
            {
                //�E�����������݊��Z�z(������)�@@=�@@0
                l_dblTodayEliminateAmountNonPay = 0;
                //�E�����������݊��Z�z(����2)�@@=�@@0
                l_dblTodayEliminateAmount2 = 0;
                //�E�����������݊��Z�z(����1)�@@=�@@0
                l_dblTodayEliminateAmount1 = 0;
            }

            //�W�j�@@(������)�Ǐ؋��z��(������)���ϕK�v�z�̌v�Z���s���B
            //[�v�Z��]
            //�ia�j�Ǐ؋��z(������)�̌v�Z���s���B
            //�Ǐ؋��z(������)�@@=�@@MAX�i(�ۏ؋�������)�Ǐؖ������@@�|�@@�����������݊��Z�z(������),�@@0�j
            double l_dblAmount5 =
                new BigDecimal(l_dblSecondDepositNonPayAfter + "").subtract(
                    new  BigDecimal(l_dblTodayEliminateAmountNonPay + "")).doubleValue();
            l_dblDepositNonPay = Math.max(l_dblAmount5, 0);

            //�ib�j���ϕK�v�z(������)�̌v�Z���s���B
            //�i�P�j�u�Ǐؖ������@@=�@@0�v�̏ꍇ
            if (GtlUtils.Double.isZero(l_dblSecondDepositNonPay))
            {
                //���ϕK�v�z(������)�@@=�@@0
                l_dblSettlementNonPay = 0;
            }
            //�i�Q�j(1)�ȊO�̏ꍇ
            else
            {
                //���ϕK�v�z(������)�@@=�@@�Ǐؖ��������ϕK�v�z�@@�~�@@(a)�̒Ǐ؋��z(������)�@@�^�@@�Ǐؖ�����
                //�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j
                l_dblSettlementNonPay =
                    new BigDecimal(l_dblSecondSettlementNonPay + "").multiply(
                        new BigDecimal(l_dblDepositNonPay + "")).divide(
                            new BigDecimal(l_dblSecondDepositNonPay + ""),
                            0, BigDecimal.ROUND_CEILING).doubleValue();
            }

            //�ic�j�Ǐ؋��z(����2)�̌v�Z���s���B
            //�Ǐ؋��z(����2)�@@=�@@MAX�i(�ۏ؋�������)�Ǐؐ���(2)�@@�|�@@�����������݊��Z�z(����2),�@@0�j
            double l_dblAmount6 =
                new BigDecimal(l_dblSecondDeposit2After + "").subtract(
                    new  BigDecimal(l_dblTodayEliminateAmount2 + "")).doubleValue();
            l_dblDeposit2 = Math.max(l_dblAmount6, 0);

            //�id�j���ϕK�v�z(����2)�̌v�Z���s���B
            //�i�P�j�u�Ǐؐ���(2)�@@=�@@0�v�̏ꍇ
            if (GtlUtils.Double.isZero(l_dblSecondDeposit2))
            {
                //���ϕK�v�z(����2)�@@=�@@0
                l_dblSettlement2 = 0;
            }
            //�i�Q�j(1)�ȊO�̏ꍇ
            else
            {
                //���ϕK�v�z(����2)�@@=�@@�Ǐؐ���(2)���ϕK�v�z�@@�~�@@(c)�̒Ǐ؋��z(����2)�@@�^�@@�Ǐؐ���(2)
                //�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j
                l_dblSettlement2 =
                    new BigDecimal(l_dblSecondSettlement2 + "").multiply(
                        new BigDecimal(l_dblDeposit2 + "")).divide(
                            new BigDecimal(l_dblSecondDeposit2 + ""), 0, BigDecimal.ROUND_CEILING).doubleValue();
            }

            //�ie�j�Ǐ؋��z(����1)�̌v�Z���s���B
            //�Ǐ؋��z(����1)�@@=�@@MAX�i(�ۏ؋�������)�Ǐؐ���(1)�@@�|�@@�����������݊��Z�z(����1),�@@0�j
            double l_dblAmount7 =
                new BigDecimal(l_dblSecondDeposit1After + "").subtract(
                    new  BigDecimal(l_dblTodayEliminateAmount1 + "")).doubleValue();
            l_dblDeposit1 = Math.max(l_dblAmount7, 0);

            //�if�j���ϕK�v�z(����1)�̌v�Z���s���B
            //�i�P�j�u�Ǐؐ���(1)�@@=�@@0�v�̏ꍇ
            if (GtlUtils.Double.isZero(l_dblSecondDeposit1))
            {
                //���ϕK�v�z(����1)�@@=�@@0
                l_dblSettlement1 = 0;
            }
            //�i�Q�j(1)�ȊO�̏ꍇ
            else
            {
                //���ϕK�v�z(����1)�@@=�@@�Ǐؐ���(1)���ϕK�v�z�@@�~�@@(e)�̒Ǐ؋��z(����1)�@@�^�@@�Ǐؐ���(1)
                //�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j
                l_dblSettlement1 =
                    new BigDecimal(l_dblSecondSettlement1 + "").multiply(
                        new BigDecimal(l_dblDeposit1 + "")).divide(
                            new BigDecimal(l_dblSecondDeposit1 + ""), 0, BigDecimal.ROUND_CEILING).doubleValue();
            }

            //�X�j�@@�ۏ؋������U�ւ̔�����s���B
            //�ia�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@FALSE(�ۏ؋������U�֑O)�̏ꍇ
            if (!this.depositAutoTransferDivFlag)
            {
                //�W�Ōv�Z�������ڂ��񐅏��Ǐؖ��������I�u�W�F�N�g�ɐݒ肵�A�ԋp����B
                //�Ǐ؋��z(������)�ݒ�
                l_secondAdddepositNotClearInfo.secondDepositNonPay = l_dblDepositNonPay;
                //���ϕK�v�z(������)�ݒ�
                l_secondAdddepositNotClearInfo.secondSettlementNonPay = l_dblSettlementNonPay;
                //�Ǐ؋��z(����2)�ݒ�
                l_secondAdddepositNotClearInfo.secondDeposit2 = l_dblDeposit2;
                //���ϕK�v�z(����2)�ݒ�
                l_secondAdddepositNotClearInfo.secondSettlement2 = l_dblSettlement2;
                //�Ǐ؋��z(����1)�ݒ�
                l_secondAdddepositNotClearInfo.secondDeposit1 = l_dblDeposit1;
                //���ϕK�v�z(����1)�ݒ�
                l_secondAdddepositNotClearInfo.secondSettlement1 = l_dblSettlement1;
            }
            //�ib�jthis.�ۏ؋������U�֌㔻��t���O�@@==�@@TRUE(�ۏ؋������U�֌�)�̏ꍇ
            //�P�O�j�ȍ~�̏������s���B
            else
            {
                //�P�O�j�@@�Ǐ؋��z(�������z)�ƌ��ϕK�v�z(�������z)�̌v�Z���s���B
                //[�v�Z��]
                //�ia�j�Ǐ؋��z(�������z)�̌v�Z���s���B
                //�Ǐ؋��z(�������z)�@@=�@@MAX�i�Ǐ؋��z�@@�|�@@�Ǐ؋��z(����1)�@@�|�@@�Ǐ؋��z(����2)�@@�|�@@�Ǐ؋��z(������),�@@0�j
                double l_dblAmount8 =
                    new BigDecimal(l_dblSecondAdddepositAmount + "").subtract(
                        new BigDecimal(l_dblDeposit1 + "")).subtract(
                            new BigDecimal(l_dblDeposit2 + "")).subtract(
                                new BigDecimal(l_dblDepositNonPay + "")).doubleValue();
                l_dblDepositExpect = Math.max(l_dblAmount8, 0);

                //�ib�j���ϕK�v�z(�������z)�̌v�Z���s���B
                //���ϕK�v�z(�������z)�@@=�@@MAX�i���ϕK�v�z�@@�|�@@���ϕK�v�z(����1)�@@�|�@@���ϕK�v�z(����2)�@@�|�@@���ϕK�v�z(������),�@@0�j
                double l_dblAmount9 =
                    new BigDecimal(l_dblSecondAdddepositSettlement + "").subtract(
                        new BigDecimal(l_dblSettlement1 + "")).subtract(
                            new BigDecimal(l_dblSettlement2 + "")).subtract(
                                new BigDecimal(l_dblSettlementNonPay + "")).doubleValue();
                l_dblSettlementExpect = Math.max(l_dblAmount9, 0);

                //�P�P�j�@@�W�ƂP�O�Ōv�Z�������ڂ��񐅏��Ǐؖ��������I�u�W�F�N�g�ɐݒ肵�A�ԋp����B
                //�Ǐ؋��z(������)�ݒ�
                l_secondAdddepositNotClearInfo.secondDepositNonPay = l_dblDepositNonPay;
                //���ϕK�v�z(������)�ݒ�
                l_secondAdddepositNotClearInfo.secondSettlementNonPay = l_dblSettlementNonPay;
                //�Ǐ؋��z(����2)�ݒ�
                l_secondAdddepositNotClearInfo.secondDeposit2 = l_dblDeposit2;
                //���ϕK�v�z(����2)�ݒ�
                l_secondAdddepositNotClearInfo.secondSettlement2 = l_dblSettlement2;
                //�Ǐ؋��z(����1)�ݒ�
                l_secondAdddepositNotClearInfo.secondDeposit1 = l_dblDeposit1;
                //���ϕK�v�z(����1)�ݒ�
                l_secondAdddepositNotClearInfo.secondSettlement1 = l_dblSettlement1;
                //�Ǐ؋��z(�������z)�ݒ�
                l_secondAdddepositNotClearInfo.secondDepositExpect = l_dblDepositExpect;
                //���ϕK�v�z(�������z)�ݒ�
                l_secondAdddepositNotClearInfo.secondSettlementExpect = l_dblSettlementExpect;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_secondAdddepositNotClearInfo;
    }

    /**
     * (calc�a����s���z�i�����ڋq�j)<BR>
     * (calc�a����s���z(�����ڋq))<BR>
     * <BR>
     * �����̎w���(=n)�ɑΉ������u�a����s���z�v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * �@@n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����̔�����s���A�a����s���z(T+n)���v�Z����B<BR>
     * �@@(a)�w���(=n)�@@==�@@0�@@�̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@�a����s���z(T+0)�@@=�@@ABS�iMIN�i���o�\����(T+0),�@@0�j�j<BR>
     * <BR>
     * �@@(b)(a)�ȊO�̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@�a����s���z(T+n)�@@=�@@MAX�iABS�iMIN�i���o�\����(T+n),�@@0�j�j�@@�|�@@ABS�iMIN�i���o�\����(T+n-1),�@@0�j�j,�@@0�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�@@�E���o�\����(T+n)�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�����ڋq>.calc���o�\����(T+n)<BR>
     * <BR>
     * �R�j�@@�v�Z�����a����s���z(T+n)��ԋp����B<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48DB7E89039E
     */
    protected double calcAccountBalanceShortfallEquity(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountBalanceShortfallEquity(int)";
        log.entering(STR_METHOD_NAME);

        double l_dblAccountBalanceShortfallEquity = 0;
        //�P�j�@@�����`�F�b�N���s���B
        //n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�Q�j�@@�w����̔�����s���A�a����s���z(T+n)���v�Z����B
        //(a)�w���(=n)�@@==�@@0�@@�̏ꍇ
        //�a����s���z(T+0)�@@=�@@ABS�iMIN�i���o�\����(T+0),�@@0�j�j
        if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblAccountBalanceShortfallEquity =
                Math.abs(Math.min(this.tpCalcEquity.calcActualPaymentBalance(l_intSpecifiedPoint), 0));
        }
        //(b)(a)�ȊO�̏ꍇ
        //�a����s���z(T+n)�@@=�@@MAX�iABS�iMIN�i���o�\����(T+n),�@@0�j�j
        //�@@�|�@@ABS�iMIN�i���o�\����(T+n-1),�@@0�j�j,�@@0�j
        else
        {
            double l_dblActualPaymentBalance =
                this.tpCalcEquity.calcActualPaymentBalance(l_intSpecifiedPoint);
            double l_dblActualPaymentBalance1 =
                this.tpCalcEquity.calcActualPaymentBalance(l_intSpecifiedPoint - 1);
            l_dblAccountBalanceShortfallEquity =
                new BigDecimal(Math.abs(Math.min(l_dblActualPaymentBalance, 0))).subtract(
                    new BigDecimal(Math.abs(Math.min(l_dblActualPaymentBalance1, 0)))).doubleValue();
            l_dblAccountBalanceShortfallEquity = Math.max(l_dblAccountBalanceShortfallEquity, 0);
        }
        log.exiting(STR_METHOD_NAME);

        return l_dblAccountBalanceShortfallEquity;
    }

    /**
     * (calc�a����s���z�i�M�p�ڋq�j)<BR>
     * (calc�a����s���z(�M�p�ڋq))<BR>
     * <BR>
     * �����̎w���(=n)�ɑΉ������u�a����s���z�v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * �@@n��0�ȏ�1�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����̔�����s���A�a����s���z(T+n)���v�Z����B<BR>
     * �@@(a)�w���(=n)�@@==�@@0�@@�̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@�a����s���z(T+0)�@@=�@@ABS�iMIN�i�ڋq����(T+0)�@@�|�@@MAX�i���v��S����(T+0)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�|�@@�U�֊z(�a�˕�)(T+0),�@@���ʗ��֋�����,�@@0�j,�@@0�j�j<BR>
     * <BR>
     * �@@(b)�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     *�@@�@@�i�P�jwk�a����s���z(T+1)���v�Z����B<BR>
     *�@@�@@�@@wk�a����s���z(T+1)�@@=�@@ABS�iMIN�i�ڋq����(T+1)<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�|�@@MAX�i���v��S����(T+1)�@@�|�@@�U�֊z(�a�˕�)(T+1),�@@0�j,�@@0�j�j<BR>
     *�@@�@@�i�Q�j��L�i�P�j�Ōv�Z����wk�a����s���z(T+1)�����Ƃɗa����s���z(T+1)���v�Z����B<BR>
     *�@@�@@�@@�a����s���z(T+1)�@@=�@@MAX(wk�a����s���z(T+1)�@@�|�@@�a����s���z(T+0),�@@0)<BR>
     * <BR>
     * �@@(c)�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ<BR>
     * �@@�@@�a����s���z(T+1) = 0<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�@@�E�ڋq����(T+n)�@@�@@�@@�E�E�Ethis.calc�ڋq����(T+n)<BR>
     * �@@�@@�@@�E���v��S����(T+n)�@@�@@�@@�E�E�Ethis.get���v��S����(T+n)<BR>
     * �@@�@@�@@�E�U�֊z(�a�˕�)(T+n)�@@�@@�@@�E�E�Ethis.calc�a�������̐U�֊z(���t�w��)(T+n)<BR>
     * �@@�@@�@@�E���ʗ��֋����с@@�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.get���ʗ��֋����сi�j<BR>
     * �@@�@@�@@�Ewk�a����s���z(T+1)�@@�@@�@@�E�E�E�Q-(b)�i�P�j�Ōv�Z����wk�a����s���z(T+1)
     *�@@�@@ �@@�E�a����s���z(T+0)�@@�@@�@@�E�E�Ethis.calc�a����s���z�i�M�p�ڋq�j(T+0)
     * <BR>
     * �R�j�@@�v�Z�����a����s���z(T+n)��ԋp����B<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48DB7E8B02E3
     */
    protected double calcAccountBalanceShortfallMargin(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountBalanceShortfallMargin(int)";
        log.entering(STR_METHOD_NAME);

        //�a����s���z
        double l_dblRequiredPayAmt = 0;
        //�ڋq����(T+0)
        double l_dblAccountCalculate0 = this.calcAccountCalculate(WEB3TPSpecifiedPointDef.T_0);
        BigDecimal l_bdAccountCalculate0 = new BigDecimal(l_dblAccountCalculate0 + "");
        //���v��S����(T+0)
        double l_dblDayTradeRestraint0 = this.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_0);
        BigDecimal l_bdDayTradeRestraint0 = new BigDecimal(l_dblDayTradeRestraint0 + "");
        //�U�֊z(�a�˕�)(T+0)
        double l_dblAccountBalanceFromMarginDeposit0 =
            this.calcAccountBalanceFromMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
        BigDecimal l_bdAccountBalanceFromMarginDeposit0 =
            new BigDecimal(l_dblAccountBalanceFromMarginDeposit0 + "");

        //���ʗ��֋�����
        double l_dblSpecialDebitAmount = this.tpCalcMargin.getCalcCondition().getSpecialDebitAmount();
        //�ڋq����(T+1)
        double l_dblAccountCalculate1 = this.calcAccountCalculate(WEB3TPSpecifiedPointDef.T_1);
        BigDecimal l_bdAccountCalculate1 = new BigDecimal(l_dblAccountCalculate1 + "");
        //���v��S����(T+1)
        double l_dblDayTradeRestraint1 = this.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_1);
        BigDecimal l_bdDayTradeRestraint1 = new BigDecimal(l_dblDayTradeRestraint1 + "");
        //�U�֊z(�a�˕�)(T+1)
        double l_dblAccountBalanceFromMarginDeposit1 =
            this.calcAccountBalanceFromMarginDeposit(WEB3TPSpecifiedPointDef.T_1);
        BigDecimal l_bdAccountBalanceFromMarginDeposit1 =
            new BigDecimal(l_dblAccountBalanceFromMarginDeposit1 + "");
        //wk�a����s���z(T+1)
        double l_dblRequiredPayAmtWk1 = 0;

        //MAX�i���v��S����(T+0)�|�@@�U�֊z(�a�˕�)(T+0),�@@���ʗ��֋�����,�@@0�j
        double l_dblMaxAmount0 = Math.max(l_bdDayTradeRestraint0.subtract(
            l_bdAccountBalanceFromMarginDeposit0).doubleValue(), l_dblSpecialDebitAmount);
        double l_dblMaxAmount = Math.max(l_dblMaxAmount0, 0);
        
        double l_dblMaxAmount1 = Math.max(l_bdDayTradeRestraint1.subtract(
            l_bdAccountBalanceFromMarginDeposit1).doubleValue(), 0);

        //�a����s���z(T+0)�@@=�@@ABS�iMIN�i�ڋq����(T+0)�@@�|�@@MAX�i���v��S����(T+0)
        //�@@�@@�@@�|�@@�U�֊z(�a�˕�)(T+0),�@@���ʗ��֋�����,�@@0�j,�@@0�j�j
        //QA:WEB3-TPLIB-A-FT-0082
        double l_dblRequiredPayAmt0 = 
            Math.abs(Math.min(l_bdAccountCalculate0.subtract(
                new BigDecimal(l_dblMaxAmount + "")).doubleValue(), 0));

        //�P�j�@@�����`�F�b�N���s���B
        //n��0�ȏ�1�ȉ��łȂ����A0��ԋp����
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�Q�j�@@�w����̔�����s���A�a����s���z(T+n)���v�Z����B
        //a)�w���(=n)�@@==�@@0�@@�̏ꍇ
        //�@@�@@�a����s���z(T+0)��Ԃ��B
        if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblRequiredPayAmt = l_dblRequiredPayAmt0;
        }

        //(b)�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == TRUE �̏ꍇ
        else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && this.isDepositAutoTransferDivFlag())
        {
            //�i�P�jwk�a����s���z(T+1)���v�Z����B
            //�@@wk�a����s���z(T+1)�@@=�@@ABS�iMIN�i�ڋq����(T+1)
            //�@@�@@  �|�@@MAX�i���v��S����(T+1)�@@�|�@@�U�֊z(�a�˕�)(T+1),�@@0�j,�@@0�j�j
            l_dblRequiredPayAmtWk1 =
                Math.abs(Math.min(l_bdAccountCalculate1.subtract(
                    new BigDecimal(l_dblMaxAmount1)).doubleValue(), 0));
            
            //�i�Q�j��L�i�P�j�Ōv�Z����wk�a����s���z(T+1)�����Ƃɗa����s���z(T+1)���v�Z����B
            //�@@�a����s���z(T+1)�@@=�@@MAX(wk�a����s���z(T+1)�@@�|�@@�a����s���z(T+0),�@@0)
            l_dblRequiredPayAmt = Math.max(new BigDecimal(l_dblRequiredPayAmtWk1).subtract(
                    new BigDecimal(l_dblRequiredPayAmt0)).doubleValue(), 0);
        }

        //c)�w���(=n)�@@==�@@1 ���� is�ۏ؋������U�֌㔻��t���O() == FALSE �̏ꍇ
        //�a����s���z(T+1) = 0
        else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && !this.isDepositAutoTransferDivFlag())
        {
            l_dblRequiredPayAmt = 0;
        }
        log.exiting(STR_METHOD_NAME);

        return l_dblRequiredPayAmt;
    }

    /**
     * (calc�a�������̐U�֊z�i���t�w��j)<BR>
     * (calc�a�������̐U�֊z(���t�w��))<BR>
     * <BR>
     * �ڋq�����̔����A�����̎w���(=n)�ɑΉ������u�a�������̐U�֊z(���t�w��)�v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * �@@n��0�ȏ�1�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�a�������̐U�֊z(���t�w��)�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �R�j�@@(�a����˕ۏ؋�)�U�֊z(T+n)�̒������ʂ��W�v����B<BR>
     * �@@this.���o�������P�ʈꗗ(�a����˕ۏ؋�)(i).get��n���i�j�@@==�@@this.get����(n)�ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�ii=this.���o�������P�ʈꗗ(�a����˕ۏ؋�)�̌����j<BR>
     * �@@�@@�@@�m�W�v�n<BR>
     * �@@�@@�@@�W�v���ʁ@@=�@@�W�v���ʁ@@�{�@@��������<BR>
     * <BR>
     * �@@�@@�@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�@@�@@�E�������ʁ@@�@@�@@�@@�E�E�Ethis.���o�������P�ʈꗗ(�a����˕ۏ؋�)(i).get�������ʁi�j<BR>
     * <BR>
     * �S�j�@@�W�v�����a�������̐U�֊z(���t�w��)(T+n)��ԋp����B<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48DB83A10346
     */
    protected double calcAccountBalanceFromMarginDeposit(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountBalanceFromMarginDeposit(int)";

        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdAccountBalanceFromMarginDeposit =
            new BigDecimal("0");
        //�P�j�@@�����`�F�b�N���s���B
        //n��0�ȏ�1�ȉ��łȂ����A0��ԋp����
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�Q�j�@@�ڋq�����̔�����s���B
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        // �a�������̐U�֊z(���t�w��)�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�R�j�@@(�a����˕ۏ؋�)�U�֊z(T+n)�̒������ʂ��W�v����B
        //this.���o�������P�ʈꗗ(�a����˕ۏ؋�)(i).get��n���i�j�@@==�@@this.get����(n)�̏ꍇ�A�ȉ��̏������s���B
        //�W�v�����ۏ؋�����̐U�֊z(���t�w��)(T+n)��ԋp����B
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            int l_intLength  = this.aioOrderUnitListFromDepositToMargins.size();
            for (int i = 0; i < l_intLength; i++)
            {
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)aioOrderUnitListFromDepositToMargins.get(i);
                if (WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(),
                    this.getDate(l_intSpecifiedPoint)) == 0)
                {
                    l_bdAccountBalanceFromMarginDeposit =
                        l_bdAccountBalanceFromMarginDeposit.add(
                            new BigDecimal(l_aioOrderUnitRow.getQuantity() + ""));
                }
            }
        }
        log.exiting(STR_METHOD_NAME);

        //�S�j�@@�W�v�����a�������̐U�֊z(���t�w��)(T+n)��ԋp����B
        return l_bdAccountBalanceFromMarginDeposit.doubleValue();
    }

    /**
     * (calc�ۏ؋�����̐U�֊z�i���t�w��j)<BR>
     * (calc�ۏ؋�����̐U�֊z(���t�w��))<BR>
     * <BR>
     * �ڋq�����̔����A�����̎w���(=n)�ɑΉ������u�ۏ؋�����̐U�֊z(���t�w��)�v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * �@@n��0�ȏ�1�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�ۏ؋�����̐U�֊z(���t�w��)�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �R�j�@@(�ۏ؋��˗a���)�U�֊z(T+n)�̒������ʂ��W�v����B<BR>
     * �@@this.���o�������P�ʈꗗ(�ۏ؋��˗a���)(i).get��n���i�j�@@==�@@this.get����(n)�ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�ii=this.���o�������P�ʈꗗ(�ۏ؋��˗a���)�̌����j<BR>
     * �@@�@@�@@�m�W�v�n<BR>
     * �@@�@@�@@�W�v���ʁ@@=�@@�W�v���ʁ@@�{�@@��������<BR>
     * <BR>
     * �@@�@@�@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�@@�@@�E�������ʁ@@�@@�@@�@@�E�E�Ethis.���o�������P�ʈꗗ(�ۏ؋��˗a���)(i).get�������ʁi�j<BR>
     * <BR>
     * �S�j�@@�W�v�����ۏ؋�����̐U�֊z(���t�w��)(T+n)��ԋp����B<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48DB8675013B
     */
    protected double calcTransferFromMarginDeposit(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountBalanceFromMarginDeposit(int)";

        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdTransferFromMarginDeposit =
            new BigDecimal("0");

        //�P�j�@@�����`�F�b�N���s���B
        //n��0�ȏ�1�ȉ��łȂ����A0��ԋp����
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�Q�j�@@�ڋq�����̔�����s���B
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        // �ۏ؋�����̐U�֊z(���t�w��)�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�R�j�@@(�ۏ؋��˗a���)�U�֊z(T+n)�̒������ʂ��W�v����B
        //this.���o�������P�ʈꗗ(�ۏ؋��˗a���)(i).get��n���i�j�@@==�@@this.get����(n)�̏ꍇ�A�ȉ��̏������s���B
        //�W�v�����ۏ؋�����̐U�֊z(���t�w��)(T+n)��ԋp����B
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            int l_intLength  = this.aioOrderUnitListFromMarginToDeposits.size();
            for (int i = 0; i < l_intLength; i++)
            {
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)aioOrderUnitListFromMarginToDeposits.get(i);
                if (WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(),
                    this.getDate(l_intSpecifiedPoint)) == 0)
                {
                    l_bdTransferFromMarginDeposit =
                        l_bdTransferFromMarginDeposit.add(
                            new BigDecimal(l_aioOrderUnitRow.getQuantity() + ""));
                }
            }
        }
        log.exiting(STR_METHOD_NAME);

        //�S�j�@@�W�v�����ۏ؋�����̐U�֊z(���t�w��)(T+n)��ԋp����B
        return l_bdTransferFromMarginDeposit.doubleValue();
    }

    /**
     * (get���v��S����)<BR>
     * (get���v��S����)<BR>
     * <BR>
     * �ڋq�����̔����A�����Ŏw�肳�ꂽ�w���(=n)�́u���v��S�����v��ԋp����B <BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B <BR>
     * �@@n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�ڋq�����̔�����s���A�����Ŏw�肳�ꂽ�w���(=n)�́u���v��S�����v��ԋp����B <BR>
     * �@@�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq�j�̏ꍇ<BR>
     * �@@�@@[�ԋp�l] <BR>
     * �@@�@@this.���Y�]�͏��<�����ڋq>.get���v��S�����iT+n�j <BR>
     * <BR>
     * �@@�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq�j�̏ꍇ<BR>
     * �@@�@@[�ԋp�l] <BR>
     * �@@�@@this.���Y�]�͏��<�M�p�ڋq>.get���v��S�����iT+n�j <BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48DB8E690017
     */
    protected double getDayTradeRestraint(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getDayTradeRestraint(int)";
        log.entering(STR_METHOD_NAME);

        double l_dblDayTradeRestraint = 0;

        //�P�j�@@�����`�F�b�N���s���B
        //n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq�j�̏ꍇ
        // this.���Y�]�͏��<�����ڋq>.get���v��S�����iT+n�j
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblDayTradeRestraint =  this.tpCalcEquity.getDayTradeRestraint(l_intSpecifiedPoint);
        }

        //�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq�j�̏ꍇ
        // this.���Y�]�͏��<�M�p�ڋq>.get���v��S�����iT+n�j
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblDayTradeRestraint =  this.tpCalcMargin.getDayTradeRestraint(l_intSpecifiedPoint);
        }
        log.exiting(STR_METHOD_NAME);

        return l_dblDayTradeRestraint;
    }

    /**
     * (calc�ڋq����)<BR>
     * (calc�ڋq����)<BR>
     * <BR>
     * �ڋq�����̔����A�����̎w���(=n)�ɑΉ������u�ڋq����v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * �@@n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�����̔�����s���A�ڋq����(T+n)���v�Z����B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq�j�̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@�ڋq����(T+n)�@@=�@@�a���(T+n)<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq�j�̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@�ڋq����(T+n)�@@=�@@�a���(T+n)�@@�|�@@�����ۏ؋�(T+n)<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�a���(T+n)�@@�@@�@@�@@�@@�@@�@@�@@�@@ �E�E�Ethis.calc�a���(T+n)<BR>
     * �@@�@@�E�����ۏ؋�(T+n)�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋�(T+n)<BR>
     * <BR>
     * �R�j�@@�v�Z�����ڋq����(T+n)��ԋp����B<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48CF028102C8
     */
    protected double calcAccountCalculate(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountCalculate(int)";
        log.entering(STR_METHOD_NAME);

        double l_dblAccountCalculate = 0;
        //�P�j�@@�����`�F�b�N���s���B
        //n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //a�jthis.�M�p��������t���O�@@==�@@0(�����ڋq�j�̏ꍇ
        //   �ڋq����(T+n)�@@=�@@�a���(T+n)
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblAccountCalculate = this.calcAccountBalance(l_intSpecifiedPoint);
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq�j�̏ꍇ
        // �ڋq����(T+n)�@@=�@@�a���(T+n)�@@�|�@@�����ۏ؋�(T+n)
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblAccountCalculate =
                new BigDecimal(this.calcAccountBalance(l_intSpecifiedPoint) + "").subtract(
                    new BigDecimal(this.calcCashMarginDeposit(l_intSpecifiedPoint) + "")).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);

        //�R�j�@@�v�Z�����ڋq����(T+n)��ԋp����B
        return l_dblAccountCalculate;
    }

    /**
     * (calc�a���)<BR>
     * (calc�a���) <BR>
     * <BR>
     * �����̎w���(=n)�ɑΉ������u�a����v���Z�o���A�ԋp����B <BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B <BR>
     * �@@n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�a���(T+n)���v�Z����B <BR>
     * �@@�m�v�Z���n <BR>
     * �@@�a���(T+n)�@@=�@@�a����c��(T+n)�@@�|�@@�������ϑ��(T+n)�@@�|�@@�����������(T+n) <BR>
     * <BR>
     * �@@���@@�v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �@@�@@�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq�j�̏ꍇ <BR>
     * �@@�@@�@@�E�a����c��(T+n)
     * �@@�@@�E�E�Ethis.���Y�]�͏��<�����ڋq>.get�a����c��(T+n) <BR>
     * �@@�@@�@@�E�������ϑ��(T+n)�@@�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�����ڋq>.get�������ϑ��(T+n) <BR>
     * �@@�@@�@@�E�����������(T+n)�@@�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�����ڋq>.get�����������(T+n) <BR>
     * <BR>
     * �@@�@@�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq�j�̏ꍇ <BR>
     * �@@�@@�@@�E�a����c��(T+n)
     * �@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�a����c��(T+n) <BR>
     * �@@�@@�@@�E�������ϑ��(T+n)�@@�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�������ϑ��(T+n) <BR>
     * �@@�@@�@@�E�����������(T+n)�@@�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�����������(T+n) <BR>
     * <BR>
     * �R�j�@@�v�Z�����a���(T+n)��ԋp����B<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48CF05B802C4
     */
    protected double calcAccountBalance(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountBalance(int)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdAccountBalance = new BigDecimal("0");
        //�a����c��(T+n)
        double l_dblAccountBalanceAmount = 0;
        //�������ϑ��(T+n)
        double l_dblTodayExecutedAmount = 0;
        //�����������(T+n)
        double l_dblTodayUnexecutedAmount = 0;

        //�P�j�@@�����`�F�b�N���s���B
        //n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq�j�̏ꍇ
        //�E�a����c��(T+n)�@@�@@�@@�@@�@@ �@@�@@�E�E�Ethis.���Y�]�͏��<�����ڋq>.get�a����c��(T+n)
        //�E�������ϑ��(T+n)�@@�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�����ڋq>.get�������ϑ��(T+n)
        //�E�����������(T+n)�@@�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�����ڋq>.get�����������(T+n)
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblAccountBalanceAmount = this.tpCalcEquity.getAccountBalance(l_intSpecifiedPoint);
            l_dblTodayExecutedAmount = this.tpCalcEquity.getTodayExecutedAmount(l_intSpecifiedPoint);
            l_dblTodayUnexecutedAmount = this.tpCalcEquity.getTodayUnexecutedAmount(l_intSpecifiedPoint);
        }
        //�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq�j�̏ꍇ
        //�E�a����c��(T+n)�@@�@@�@@�@@�@@ �@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�a����c��(T+n)
        //�E�������ϑ��(T+n)�@@�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�������ϑ��(T+n)
        //�E�����������(T+n)�@@�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�����������(T+n)
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblAccountBalanceAmount = this.tpCalcMargin.getAccountBalance(l_intSpecifiedPoint);
            l_dblTodayExecutedAmount = this.tpCalcMargin.getTodayExecutedAmount(l_intSpecifiedPoint);
            l_dblTodayUnexecutedAmount = this.tpCalcMargin.getTodayUnexecutedAmount(l_intSpecifiedPoint);
        }

        BigDecimal l_bdAccountBalanceAmount = new BigDecimal(l_dblAccountBalanceAmount + "");
        BigDecimal l_bdTodayExecutedAmount = new BigDecimal(l_dblTodayExecutedAmount + "");
        BigDecimal l_bdTodayUnexecutedAmount = new BigDecimal(l_dblTodayUnexecutedAmount + "");

        //�Q�j�@@�a���(T+n)���v�Z����B
        l_bdAccountBalance = l_bdAccountBalanceAmount.subtract(
            l_bdTodayExecutedAmount).subtract(l_bdTodayUnexecutedAmount);

        //�R�j�@@�v�Z�����a���(T+n)��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_bdAccountBalance.doubleValue();
    }

    /**
     * (calc�����ۏ؋�)<BR>
     * (calc�����ۏ؋�)<BR>
     * <BR>
     * �ڋq�����̔����A�����̎w���(=n)�ɑΉ������u�����ۏ؋��v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * �@@n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�����ۏ؋��@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �R�j�@@�����ۏ؋��c��(T+n)���擾����B<BR>
     * �@@�m�擾��n<BR>
     * �@@this.�ڋq����c��(�}�X�^���)<�ۏ؋�>.get�c��(����+n)<BR>
     * <BR>
     *�@@��this.�ڋq����c��(�}�X�^���)<�ۏ؋�> == null�̏ꍇ�A�����ۏ؋��c��(T+n) = 0�Ƃ���<BR>
     * <BR>
     * �S�j�@@(�a����˕ۏ؋�)�U�֊z(T+n)�̒������ʂ��AT+0�`T+n�܂ŏW�v����B<BR>
     * �@@this.���o�������P�ʈꗗ(�a����˕ۏ؋�)(i).get��n���i�j�@@<=�@@this.get����(n) ���A<BR>
     * �@@this.���o�������P�ʈꗗ(�a����˕ۏ؋�)(i).get�������i�j�@@>=�@@this.get����(0) �̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�ii=this.���o�������P�ʈꗗ(�a����˕ۏ؋�)�̌����j<BR>
     * �@@�@@�@@�m�W�v�n<BR>
     * �@@�@@�@@��������(�W�v)�@@=�@@��������(�W�v)�@@�{�@@��������<BR>
     * <BR>
     * �@@�@@�@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�@@�@@�E�������ʁ@@�@@�@@�@@�E�E�Ethis.���o�������P�ʈꗗ(�a����˕ۏ؋�)(i).get�������ʁi�j<BR>
     * <BR>
     * �T�j�@@(�ۏ؋��˗a���)�U�֊z(T+n)�̒������ʂ��AT+0�`T+n�܂ŏW�v����B<BR>
     * �@@this.���o�������P�ʈꗗ(�ۏ؋��˗a���)(i).get��n���i�j�@@<=�@@this.get����(n) ���A<BR>
     * �@@this.���o�������P�ʈꗗ(�a����˕ۏ؋�)(i).get�������i�j�@@>=�@@this.get����(0) �̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�ii=this.���o�������P�ʈꗗ(�ۏ؋��˗a���)�̌����j<BR>
     * �@@�@@�@@�m�W�v�n<BR>
     * �@@�@@�@@��������(�W�v)�@@=�@@��������(�W�v)�@@�{�@@��������<BR>
     * <BR>
     * �@@�@@�@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�@@�@@�E�������ʁ@@�@@�@@�@@�E�E�Ethis.���o�������P�ʈꗗ(�ۏ؋��˗a���)(i).get�������ʁi�j<BR>
     * <BR>
     * �U�j�@@�����ۏ؋�(T+n)���v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@�����ۏ؋�(T+n)�@@=�@@�����ۏ؋��c��(T+n)�@@�{<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@sum�i(�a����˕ۏ؋�)�U�֊z(T+0..n)�j�@@�|�@@sum�i(�ۏ؋��˗a���)�U�֊z(T+0..n)�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�����ۏ؋��c��(T+n)�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�R�Ŏ擾���������ۏ؋���(T+n)<BR>
     * �@@�@@�Esum�i(�a����˕ۏ؋�)�U�֊z(T+0..n)�j�@@�@@�@@�E�E�E�S�ŏW�v����(�a����˕ۏ؋�)�U�֊z(T+n)�̒�������(�W�v)<BR>
     * �@@�@@�Esum�i(�ۏ؋��˗a���)�U�֊z(T+0..n)�j�@@�@@�@@�E�E�E�T�ŏW�v����(�ۏ؋��˗a���)�U�֊z(T+n)�̒�������(�W�v)<BR>
     * <BR>
     * �V�j�@@�v�Z���������ۏ؋�(T+n)��ԋp����B<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * (�w���)<BR>
     * @@return double
     * @@roseuid 48CF06800331
     */
    protected double calcCashMarginDeposit(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcCashMarginDeposit(int)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdCashMarginDeposit = new BigDecimal("0");
        //�����ۏ؋��c��(T+n)
        double l_dblCashBalance = 0;
        //(�a����˕ۏ؋�)�U�֊z(T+n)
        BigDecimal l_bdSumQuantityFromDepositToMargins = new BigDecimal("0");
        //(�ۏ؋��˗a���)�U�֊z(T+n)
        BigDecimal l_bdSumQuantityFromMarginsToDeposit = new BigDecimal("0");
        //�P�j�@@�����`�F�b�N���s���B
        //n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1    
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�Q�j�@@�ڋq�����̔�����s���B
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //     �����ۏ؋��@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //  �R�j�ȍ~�̏������s���B
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {

            if (this.tpCashBalanceParams != null)
            {
            
                //�����ۏ؋��c��(T+n)���擾����B
                switch(l_intSpecifiedPoint)
                {
                    //�c���i����+�@@�O���j
                    //this.�ڋq����c��(�}�X�^���)<�ۏ؋�>.get�c��(����+n)
                    case WEB3TPSpecifiedPointDef.T_0:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance0();
                        break;
                    //�c���i����+�@@�P���j
                    case WEB3TPSpecifiedPointDef.T_1:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance1();
                        break;
                    //�c���i����+�@@�Q���j�j
                    case WEB3TPSpecifiedPointDef.T_2:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance2();
                        break;
                    //�c���i����+�@@�R���j
                    case WEB3TPSpecifiedPointDef.T_3:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance3();
                        break;
                    //�c���i����+�@@�S���j
                    case WEB3TPSpecifiedPointDef.T_4:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance4();
                        break;
                    //�c���i����+�@@�T���ȍ~�j
                    default:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance5();
                }

            }

            //�S�j�@@(�a����˕ۏ؋�)�U�֊z(T+n)�̒������ʂ��AT+0�`T+n�܂ŏW�v����B
            //this.���o�������P�ʈꗗ(�a����˕ۏ؋�)(i).get��n���i�j�@@<=�@@this.get����(n) ���A
            //this.���o�������P�ʈꗗ(�a����˕ۏ؋�)(i).get�������i�j�@@>=�@@this.get����(0) �̏ꍇ�A�ȉ��̏������s���B
            //�@@�ii=this.���o�������P�ʈꗗ(�a����˕ۏ؋�)�̌����j
            //�m�W�v�n
            //   ��������(�W�v)�@@=�@@��������(�W�v)�@@�{�@@��������
            int l_intLength  = this.aioOrderUnitListFromDepositToMargins.size();
            for (int i = 0; i < l_intLength; i++)
            {
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)aioOrderUnitListFromDepositToMargins.get(i);
                if ((WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(),
                    this.getDate(l_intSpecifiedPoint)) <= 0) && 
                    (WEB3DateUtility.compareToDay(WEB3DateUtility.getDate(l_aioOrderUnitRow.getBizDate(), "yyyyMMdd"),
                    this.getDate(0)) >= 0))
                {
                    l_bdSumQuantityFromDepositToMargins =
                        l_bdSumQuantityFromDepositToMargins.add(
                            new BigDecimal(l_aioOrderUnitRow.getQuantity() + ""));
                }
            }

            //�T�j�@@(�ۏ؋��˗a���)�U�֊z(T+n)�̒������ʂ��AT+0�`T+n�܂ŏW�v����B
            //this.���o�������P�ʈꗗ(�ۏ؋��˗a���)(i).get��n���i�j�@@<=�@@this.get����(n) ���A
            //this.���o�������P�ʈꗗ(�a����˕ۏ؋�)(i).get�������i�j�@@>=�@@this.get����(0) �̏ꍇ�A�ȉ��̏������s���B
            //�ii=this.���o�������P�ʈꗗ(�ۏ؋��˗a���)�̌����j
            //�@@�@@�m�W�v�n
            //��������(�W�v)�@@=�@@��������(�W�v)�@@�{�@@��������
            int l_intSize = this.aioOrderUnitListFromMarginToDeposits.size();
            for (int i = 0; i < l_intSize; i++)
            {
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)aioOrderUnitListFromMarginToDeposits.get(i);
                if ((WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(),
                    this.getDate(l_intSpecifiedPoint)) <= 0) && 
                    (WEB3DateUtility.compareToDay(WEB3DateUtility.getDate(l_aioOrderUnitRow.getBizDate(), "yyyyMMdd"),
                    this.getDate(0)) >= 0))
                {
                    l_bdSumQuantityFromMarginsToDeposit =
                        l_bdSumQuantityFromMarginsToDeposit.add(
                            new BigDecimal(l_aioOrderUnitRow.getQuantity() + ""));
                }
            }

            BigDecimal l_bdCashBalance = new BigDecimal(l_dblCashBalance + "");

            //�U�j�@@�����ۏ؋�(T+n)���v�Z����B
            //�����ۏ؋�(T+n)�@@=�@@�����ۏ؋��c��(T+n)�@@�{
            //sum�i(�a����˕ۏ؋�)�U�֊z(T+0..n)�j�@@�|�@@sum�i(�ۏ؋��˗a���)�U�֊z(T+0..n)�j
            l_bdCashMarginDeposit =
                l_bdCashBalance.add(l_bdSumQuantityFromDepositToMargins).subtract(
                    l_bdSumQuantityFromMarginsToDeposit);
        }

        log.exiting(STR_METHOD_NAME);
        //�V�j�@@�v�Z���������ۏ؋�(T+n)��ԋp����B
        return l_bdCashMarginDeposit.doubleValue();
    }

    /**
     * (calc�ۏ؋���)<BR>
     * (calc�ۏ؋���) <BR>
     * <BR>
     * �ڋq�����̔����A�u�ۏ؋����v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�ۏ؋����@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@���ʑ���̔�����s���B<BR>
     * �@@�ia�j�u���ʑ���@@=�@@0�v�̏ꍇ<BR>
     * �@@�@@�ۏ؋����@@=�@@999.99��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�j(a)�ȊO�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �R�j�@@�ۏ؋������v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@�ۏ؋����@@=�@@�i�����ۏ؋��@@�^�@@���ʑ���j�@@�~�@@100<BR>
     * �@@�@@�i�v�Z���ʂ̏����_��O�ʈȉ���؂�̂ĂƂ���B�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�����ۏ؋��@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋��i�j<BR>
     * �@@�@@�E���ʑ���@@�@@�@@�@@�@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʋ�( T + 0 )�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ + this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ��( T + 0 )�i�j<BR>
     * <BR>
     * �S�j�@@�v�Z�����ۏ؋�����ԋp����B<BR>
     * @@return double
     * @@roseuid 48D33BFE0166
     */
    protected double calcMarginDepositRate()
    {
        final String STR_MEHTOD_NAME = "calcMarginDepositRate()";
        log.entering(STR_MEHTOD_NAME);

        double l_dblMarginDepositRate = 0;

        //���ʑ��
        //���ʑ���E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʑ��( T + 0 )�i�j
        //+ this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ��( T + 0 )�i�j
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getContractAmount0() + "").add(
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getDayRepayContractAmount0() + ""));

        //�����ۏ؋�
        //�E�����ۏ؋��@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋��i�j
        BigDecimal l_bdRealMargin = new BigDecimal(this.calcRealMargin() + "");
        //�P�j�@@�ڋq�����̔�����s���B
        //�@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�@@�@@�ۏ؋����@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_MEHTOD_NAME);
            return 0;
        }
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�Q�j�@@���ʑ���̔�����s���B
            //�ia�j�u���ʑ���@@=�@@0�v�̏ꍇ
            // �ۏ؋����@@=�@@999.99��ݒ肵�A�ԋp����B
            if (GtlUtils.Double.isZero(l_bdContractAmount.doubleValue()))
            {
                log.exiting(STR_MEHTOD_NAME);
                return 999.99;
            }
            else
            {
                //�R�j�@@�ۏ؋������v�Z����
                //�ۏ؋����@@=�@@�i�����ۏ؋��@@�^�@@���ʑ���j�@@�~�@@100
                //�i�v�Z���ʂ̏����_��O�ʈȉ���؂�̂ĂƂ���B�j
                l_dblMarginDepositRate = l_bdRealMargin.divide(
                        l_bdContractAmount, 10, BigDecimal.ROUND_DOWN).multiply(
                            new BigDecimal(100 + "")).setScale(
                            2, BigDecimal.ROUND_DOWN).doubleValue();
            }
        }
        log.exiting(STR_MEHTOD_NAME);

        //�S�j�@@�v�Z�����ۏ؋�����ԋp����B
        return l_dblMarginDepositRate;
    }

    /**
     * (get�����ԍό��ʑ��)<BR>
     * (get�����ԍό��ʑ��) <BR>
     * <BR>
     * �ڋq�����̔����A�u�����ԍό��ʑ���v��ԋp����B  <BR>
     * <BR>
     * �@@�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ <BR>
     * �@@�@@�m�ԋp�l�n  <BR>
     * �@@�@@0 <BR>
     * <BR>
     * �@@�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ <BR>
     * �@@�@@�m�ԋp�l�n  <BR>
     * �@@�@@this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get�����ԍό��ʑ���i�j<BR>
     * @@return double
     * @@roseuid 48D99BE40061
     */
    protected double getTodayRepayContractAmount()
    {
        final String STR_METHOD_NAME = "getTodayRepayContractAmount()";
        log.entering(STR_METHOD_NAME);

        double l_dblTodayRepayContractAmount = 0;

        //�i�P�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�m�ԋp�l�n
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�i�Q�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get�����ԍό��ʑ���i�j
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblTodayRepayContractAmount =
                this.tpCalcMargin.getCalcResultDetailMargin().getTodayRepayContractAmount();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblTodayRepayContractAmount;
    }

    /**
     * (calc�ۏ؋�����)<BR>
     * (calc�ۏ؋�����)<BR>
     * <BR>
     * �ڋq�����̔����A�u�ۏ؋������v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�ۏ؋������@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@���������Ǘ��e�[�u���̃��R�[�h���`�F�b�N����B<BR>
     * �@@�ia�jthis.���������Ǘ�Params == null�̏ꍇ<BR>
     * �@@�m�v�Z���n<BR>
     * �@@�ۏ؋������@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.���������Ǘ�Params != null�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �R�j�@@�����ۏ؋����������v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@�����ۏ؋��������@@=�@@�����ۏ؋��@@�|�@@�O���J�z�����ۏ؋� <BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�����ۏ؋��@@�@@�@@�E�E�Ethis.calc�����ۏ؋�(T+0)�i�j<BR>
     * �@@�@@�E�O���J�z�����ۏ؋��@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get�����ۏ؋��i�j<BR>
     * <BR>
     * �S�j�@@��p�]���z���������v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@��p�]���z�������@@=�@@��p�،��]���z�@@�|�@@�O���J�z��p�،��]���z<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E��p�،��]���z�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@get��p�،��]���z(�O���P���]��)�i�j<BR>
     * �@@�@@�E�O���J�z��p�،��]���z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��p�،��]���z�i�j<BR>
     * <BR>
     * �T�j�@@�ۏ؋��������v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@�ۏ؋������@@=�@@�����ۏ؋��������@@�{�@@��p�]���z������<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�����ۏ؋��������@@�@@�E�E�E�R�Ōv�Z���������ۏ؋�������<BR>
     * �@@�@@�E��p�]���z�������@@�@@�E�E�E�S�Ōv�Z������p�]���z������<BR>
     * <BR>
     * �U�j�@@�v�Z�����ۏ؋�������ԋp����B<BR>
     * @@return double
     * @@roseuid 48D9E9750019
     */
    protected double calcMarginDepositInDe()
    {
        final String STR_METHOD_NAME = "calcMarginDepositInDe()";
        log.entering(STR_METHOD_NAME);

        //�ۏ؋�����
        BigDecimal l_bdMarginDepositInDe = new BigDecimal("0");
        //�����ۏ؋�������
        BigDecimal l_bdCashMarginDepositInDe = new BigDecimal("0");
        //��p�]���z������
        BigDecimal l_bdSubstituteAssetOldDayValueInDe = new BigDecimal("0");
        //�E�����ۏ؋��@@�@@�@@�E�E�Ethis.calc�����ۏ؋�(T+0)�i�j
        double l_dblCashMarginDeposit = this.calcCashMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
        BigDecimal l_bdCashMarginDeposit = new BigDecimal(l_dblCashMarginDeposit + "");

        //�P�j�@@�ڋq�����̔�����s���B
        //�@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�@@�ۏ؋������@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        //�Q�j�ȍ~�̏������s���B
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�O���J�z�����ۏ؋��@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get�����ۏ؋��i�j
            double l_dblBeforeTodayCashDeposit;
            //�O���J�z��p�،��]���z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��p�،��]���z�i�j
            double l_dblSubstituteSecurityAsset;
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            else
            {
                l_dblBeforeTodayCashDeposit = this.paymentRequisitMngParams.getCashMarginDeposit();
                l_dblSubstituteSecurityAsset = this.paymentRequisitMngParams.getSubstituteSecurityAsset();

                //��p�،��]���z
                double l_dblSubstituteAssetOldDayValue =
                    this.tpCalcMargin.getCalcResultDetailMargin().getSubstituteAssetOldDayValue();
                BigDecimal l_bdSubstituteAssetOldDayValue = new BigDecimal(l_dblSubstituteAssetOldDayValue + "");
                BigDecimal l_bdBeforeTodayCashDeposit = new BigDecimal(l_dblBeforeTodayCashDeposit + "");
                BigDecimal l_bdSubstituteSecurityAsset = new BigDecimal(l_dblSubstituteSecurityAsset + "");

                //�Q�j�@@�����ۏ؋����������v�Z����
                //�����ۏ؋��������@@=�@@�����ۏ؋��@@�|�@@�O���J�z�����ۏ؋�
                l_bdCashMarginDepositInDe = l_bdCashMarginDeposit.subtract(l_bdBeforeTodayCashDeposit);

                //�R�j�@@��p�]���z���������v�Z����B
                //��p�]���z�������@@=�@@��p�،��]���z�@@�|�@@�O���J�z��p�،��]���z
                l_bdSubstituteAssetOldDayValueInDe =
                    l_bdSubstituteAssetOldDayValue.subtract(l_bdSubstituteSecurityAsset);

                //�S�j�@@�ۏ؋��������v�Z����B
                //�ۏ؋������@@=�@@�����ۏ؋��������@@�{�@@��p�]���z������
                l_bdMarginDepositInDe =
                    l_bdCashMarginDepositInDe.add(l_bdSubstituteAssetOldDayValueInDe);
            }
        }
        log.exiting(STR_METHOD_NAME);

        //�T�j�@@�v�Z�����ۏ؋�������ԋp����B
        return  l_bdMarginDepositInDe.doubleValue();
    }

    /**
     * (calc�ۏ؋������i�������z�j)<BR>
     * (calc�ۏ؋�����(�������z))<BR>
     * <BR>
     * �ڋq�����̔����A�u�ۏ؋�����(�������z)�v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�ۏ؋�����(�������z)�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�����ۏ؋����������v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@�����ۏ؋��������@@=�@@�����ۏ؋��@@�|�@@�O���J�z�����ۏ؋�<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�����ۏ؋��@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋�(T+1)<BR>
     * �@@�@@�E�O���J�z�����ۏ؋��@@�@@�@@�E�E�Ethis.calc�����ۏ؋��iT+0�j<BR>
     * <BR>
     * �R�j�@@��p�]���z���������v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@��p�]���z�������@@=�@@��p�،��]���z�@@�|�@@�O���J�z��p�،��]���z<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E��p�،��]���z�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get��p�،��]���z(T+1)�i�j<BR>
     * �@@�@@�E�O���J�z��p�،��]���z�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get��p�،��]���z(T+0)�i�j<BR>
     * <BR>
     * �S�j�@@�ۏ؋�����(�������z)���v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@�ۏ؋�����(�������z)�@@=�@@�����ۏ؋��������@@�{�@@��p�]���z������<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�����ۏ؋��������@@�@@�E�E�E�Q�Ōv�Z���������ۏ؋�������<BR>
     * �@@�@@�E��p�]���z�������@@�@@�E�E�E�R�Ōv�Z������p�]���z������<BR>
     * <BR>
     * �T�j�@@�v�Z�����ۏ؋�����(�������z)��ԋp����B<BR>
     * @@return double
     * @@roseuid 48D9F5FA0225
     */
    protected double calcMarginDepositInDeExpect()
    {
        final String STR_METHOD_NAME = "calcMarginDepositInDeExpect";
        log.entering(STR_METHOD_NAME);

        //�ۏ؋�����(�������z)
        BigDecimal l_bdMarginDepositInDeExpect = new BigDecimal("0");
        //�����ۏ؋�������
        BigDecimal l_bdCashMarginDepositInDe = new BigDecimal("0");
        //�����ۏ؋�
        double l_dblCashMarginDeposit = this.calcCashMarginDeposit(WEB3TPSpecifiedPointDef.T_1);
        BigDecimal l_bdCashMarginDeposit = new BigDecimal(l_dblCashMarginDeposit + "");
        //�O���J�z�����ۏ؋�
        double l_dblBeforeTodayCashDeposit = this.calcCashMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
        BigDecimal l_bdBeforeTodayCashDeposit = new BigDecimal(l_dblBeforeTodayCashDeposit + "");
        //��p�]���z������
        BigDecimal l_bdSubstituteAssetOldDayValueInDe = new BigDecimal("0");
        //��p�،��]���z
        double l_dblSubstituteAssetOldDayValue =
            this.tpCalcMargin.getCalcResultMargin().getSubstituteSecurityAsset1();
        BigDecimal l_bdSubstituteAssetOldDayValue = new BigDecimal(l_dblSubstituteAssetOldDayValue + "");
        //�O���J�z��p�،��]���z
        double l_dblSubstituteSecurityAsset =
            this.tpCalcMargin.getCalcResultMargin().getSubstituteSecurityAsset0();
        BigDecimal l_bdSubstituteSecurityAsset = new BigDecimal(l_dblSubstituteSecurityAsset + "");

        //�P�j�@@�ڋq�����̔�����s���B
        //�ۏ؋�����(�������z)�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        // �Q�j�ȍ~�̏������s���B
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�Q�j�@@�����ۏ؋����������v�Z����B
            //�����ۏ؋��������@@=�@@�����ۏ؋��@@�|�@@�O���J�z�����ۏ؋�
            l_bdCashMarginDepositInDe =
                l_bdCashMarginDeposit.subtract(l_bdBeforeTodayCashDeposit);

            //�R�j�@@��p�]���z���������v�Z����B
            //��p�]���z�������@@=�@@��p�،��]���z�@@�|�@@�O���J�z��p�،��]���z
            l_bdSubstituteAssetOldDayValueInDe =
                l_bdSubstituteAssetOldDayValue.subtract(l_bdSubstituteSecurityAsset);

            //�S�j�@@�ۏ؋�����(�������z)���v�Z����B
            //�ۏ؋�����(�������z)�@@=�@@�����ۏ؋��������@@�{�@@��p�]���z������
            l_bdMarginDepositInDeExpect =
                l_bdCashMarginDepositInDe.add(l_bdSubstituteAssetOldDayValueInDe);
        }

        log.exiting(STR_METHOD_NAME);

        //�T�j�@@�v�Z�����ۏ؋�����(�������z)��ԋp����B
        return l_bdMarginDepositInDeExpect.doubleValue();
    }

    /**
     * (calc��ꐅ���Ǐؓ����������݊��Z�z)<BR>
     * (calc��ꐅ���Ǐؓ����������݊��Z�z)<BR>
     * <BR>
     * �ڋq�����ƌv�Z�����̔����A�u��ꐅ���Ǐؓ����������݊��Z�z�v���Z�o���A�ԋp����B <BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐؓ����������݊��Z�z�@@=�@@0 ��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B <BR>
     * <BR>
     * �Q�j�@@�v�Z�����̔�����s���B<BR>
     * �@@�ia�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�htoday.clearance.determination.div�h�j�@@==�@@�h1�h�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B <BR>
     * <BR>
     * �@@�ib�jthis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�htoday.clearance.determination.div�h�j�@@==�@@NULL<BR>
     * �@@�@@�@@���́Athis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@get��Е��X�ʗ]�͌v�Z�����i�htoday.clearance.determination.div�h�j�@@!=�@@�h1�h�̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐؓ����������݊��Z�z�@@=�@@0 ��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �R�j�@@��ꐅ���Ǐ،��ϕK�v�z���擾����B<BR>
     * �@@�m�擾��n <BR>
     * �@@��ꐅ���Ǐ،��ϕK�v�z�@@=�@@this.���������Ǘ�Params.get��ꐅ���Ǐ،��ϕK�v�z�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A��ꐅ���Ǐ،��ϕK�v�z = 0 �Ƃ���B<BR>
     * <BR>
     * �S�j�@@��ꐅ���Ǐ،��ϕK�v�z�̔�����s���B<BR>
     * �@@�ia�j��ꐅ���Ǐ،��ϕK�v�z�@@==�@@0�̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐؓ����������݊��Z�z�@@=�@@0 ��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�j��ꐅ���Ǐ،��ϕK�v�z�@@!=�@@0�̏ꍇ<BR>
     * �@@�@@�T�j�ȍ~�̏������s���B <BR>
     * <BR>
     * �T�j�@@��ꐅ���Ǐؓ����������݊��Z�z���v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@��ꐅ���Ǐؓ����������݊��Z�z�@@=�@@��ꐅ���Ǐ؋��z�@@�~�@@�����ԍό��ʑ��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�^�@@��ꐅ���Ǐ،��ϕK�v�z<BR>
     * �@@�i�v�Z���ʂ̏����_�ȉ���؂�̂ĂƂ���B�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E��ꐅ���Ǐ؋��z�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��ꐅ���Ǐ؋��z�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A��ꐅ���Ǐ؋��z = 0 �Ƃ���B<BR>
     * �@@�@@�E�����ԍό��ʑ���@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.get�����ԍό��ʑ���i�j<BR>
     * �@@�@@�E��ꐅ���Ǐ،��ϕK�v�z�@@�@@�@@�@@�@@�E�E�E�R�Ŏ擾������ꐅ���Ǐ،��ϕK�v�z<BR>
     * <BR>
     * �U�j�@@�v�Z������ꐅ���Ǐؓ����������݊��Z�z��ԋp����B<BR>
     * @@return double
     * @@roseuid 48DA082100EA
     */
    protected double calcFirstAdddepositEliminateAmount()
    {
        final String STR_METHOD_NAME = "calcFirstAdddepositEliminateAmount()";
        log.entering(STR_METHOD_NAME);

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�����ۏ؋������z�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            String l_strInstBranCalcCondition =
                this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.TODAY_CLEARANCE_DETERMINATION_DIV);

            //this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
            //�@@get��Е��X�ʗ]�͌v�Z�����i�htoday.clearance.determination.div�h�j�@@==�@@NULL
            //���́Athis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����.
            // get��Е��X�ʗ]�͌v�Z�����i�htoday.clearance.determination.div�h�j�@@!=�@@�h1�h�̏ꍇ
            if (l_strInstBranCalcCondition == null
                || !WEB3TodayClearanceDeterminationDivDef.EXECUTE.equals(l_strInstBranCalcCondition))
            {
                //��ꐅ���Ǐؓ����������݊��Z�z�@@=�@@0 ��ݒ肵�A�ԋp����B
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //��ꐅ���Ǐ،��ϕK�v�z�@@=�@@this.���������Ǘ�Params.get��ꐅ���Ǐ،��ϕK�v�z�i�j
            //�������Athis.���������Ǘ�Params == null �̏ꍇ�́A��ꐅ���Ǐ،��ϕK�v�z = 0 �Ƃ���B
            double l_dblFirstSettlement = 0;
            if (this.paymentRequisitMngParams != null)
            {
                l_dblFirstSettlement = this.paymentRequisitMngParams.getFirstSettlement();
            }
            BigDecimal l_bdFirstSettlement = new BigDecimal("" + l_dblFirstSettlement);
            //�ia�j��ꐅ���Ǐ،��ϕK�v�z�@@==�@@0�̏ꍇ
            if (GtlUtils.Double.isZero(l_dblFirstSettlement))
            {
                //��ꐅ���Ǐؓ����������݊��Z�z�@@=�@@0 ��ݒ肵�A�ԋp����B
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //��ꐅ���Ǐ؋��z�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��ꐅ���Ǐ؋��z�i�j
            double l_dblFirstDepositAmount = 0;
            if (this.paymentRequisitMngParams != null)
            {
                l_dblFirstDepositAmount = this.paymentRequisitMngParams.getFirstDepositAmount();
            }
            BigDecimal l_bdFirstDepositAmount = new BigDecimal("" + l_dblFirstDepositAmount);
            //�����ԍό��ʑ���@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.get�����ԍό��ʑ���i�j
            double l_dblTodayRepayContractAmount = this.getTodayRepayContractAmount();
            BigDecimal l_bdTodayRepayContractAmount = new BigDecimal("" + l_dblTodayRepayContractAmount);
            //��ꐅ���Ǐ،��ϕK�v�z�@@�@@ �@@�@@�E�E�E�R�Ŏ擾������ꐅ���Ǐ،��ϕK�v�z
            //�@@��ꐅ���Ǐؓ����������݊��Z�z�@@=�@@��ꐅ���Ǐ؋��z�@@�~�@@�����ԍό��ʑ�� �^�@@��ꐅ���Ǐ،��ϕK�v�z
            //�@@�i�v�Z���ʂ̏����_�ȉ���؂�̂ĂƂ���B�j
            log.exiting(STR_METHOD_NAME);
            return l_bdFirstDepositAmount.multiply(
                    l_bdTodayRepayContractAmount).divide(
                        l_bdFirstSettlement, 0, BigDecimal.ROUND_DOWN).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc�����ۏ؋������z)<BR>
     * (calc�����ۏ؋������z)<BR>
     * <BR>
     * �ڋq�����̔����A�u�����ۏ؋������z�v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�����ۏ؋������z�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�����ۏ؋������z���v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@�����ۏ؋������z�@@=�@@MAX�i�ۏ؋������C�@@0�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�ۏ؋������@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�ۏ؋������i�j<BR>
     * �@@<BR>
     * �R�j�@@�v�Z���������ۏ؋������z��ԋp����B<BR>
     * @@return double
     * @@roseuid 48DA37EE0266
     */
    protected double calcTodayDepositPaymentAmount()
    {
        final String STR_METHOD_NAME = "calcTodayDepositPaymentAmount()";
        log.entering(STR_METHOD_NAME);
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�����ۏ؋������z�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�ۏ؋������@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�ۏ؋������i�j
            double l_dblMarginDepositInDe = this.calcMarginDepositInDe();
            //�����ۏ؋������z�@@=�@@MAX�i�ۏ؋������C�@@0�j
            log.exiting(STR_METHOD_NAME);
            return Math.max(l_dblMarginDepositInDe, 0);
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc�����ۏ؋������z�i�������z�j)<BR>
     * (calc�����ۏ؋������z(�������z))<BR>
     * <BR>
     * �ڋq�����̔����A�u�����ۏ؋������z(�������z)�v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�����ۏ؋������z(�������z)�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�����ۏ؋������z(�������z)���v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@�����ۏ؋������z(�������z)�@@=�@@MAX�i�ۏ؋������C�@@0�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�ۏ؋������@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�ۏ؋�����(�������z)�i�j<BR>
     * �@@<BR>
     * �R�j�@@�v�Z���������ۏ؋������z(�������z)��ԋp����B<BR>
     * @@return double
     * @@roseuid 48DA38D00365
     */
    protected double calcTodayDepositPaymentAmountExpect()
    {
        final String STR_METHOD_NAME = "calcTodayDepositPaymentAmountExpect()";
        log.entering(STR_METHOD_NAME);
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�����ۏ؋������z(�������z)�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�ۏ؋������@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�ۏ؋�����(�������z)�i�j
            double l_dblMarginDepositInDeExpect = this.calcMarginDepositInDeExpect();
            //�����ۏ؋������z(�������z)�@@=�@@MAX�i�ۏ؋������C�@@0�j
            log.exiting(STR_METHOD_NAME);
            return Math.max(l_dblMarginDepositInDeExpect, 0);
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc�����ۏ؋�)<BR>
     * (calc�����ۏ؋�) <BR>
     * <BR>
     * �ڋq�����̔����A�u�����ۏ؋��v���Z�o���A�ԋp����B <BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ <BR>
     * �@@�@@�����ۏ؋��@@=�@@0 ��ݒ肵�A�ԋp����B <BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ <BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B  <BR>
     * <BR>
     * �Q�j�@@�����ۏ؋����v�Z����B <BR>
     * �@@�m�v�Z���n <BR>
     * �@@�����ۏ؋��@@=�@@�i�����ۏ؋��@@�{�@@��p�،��]���z�j <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�|�@@�iABS�iMIN�i���ʕ]�����v�C�@@0�j�j�@@�{�@@���ʏ��o��@@�{�@@����n���ϑ��j <BR>
     * <BR>
     * �@@���@@�v�Z���Ɏg�p����e�l�̎擾���@@ <BR>
     * �@@�@@�E�����ۏ؋��@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋�(T+0)�i�j<BR>
     * �@@�@@�E��p�،��]���z�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get��p�،��]���z(T+0)�i�j<BR>
     * �@@�@@�E���ʕ]�����v�@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʉ����v( T + 0 )()<BR>
     * �@@�@@�E���ʏ��o��@@�@@�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���ʏ��o��(T+0�j�i�j<BR>
     * �@@�@@�E����n���ϑ��@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get����n���ʍϑ�(T+0)�i�j<BR>
     * <BR>
     * �R�j�@@�v�Z���������ۏ؋���ԋp����B<BR>
     * @@return double
     * @@roseuid 48DA24F5022C
     */
    protected double calcRealMargin()
    {
        final String STR_METHOD_NAME = "calcRealMargin()";
        log.entering(STR_METHOD_NAME);
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //�����ۏ؋��@@=�@@0 ��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�����ۏ؋��@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋�(T+0)�i�j
            double l_dblCashMarginDeposit = this.calcCashMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
            BigDecimal l_bdCashMarginDeposit = new BigDecimal("" + l_dblCashMarginDeposit);
            //��p�،��]���z�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get��p�،��]���z(T+0)�i�j
            double l_dblSubstituteSecurityAsset0 =
                this.tpCalcMargin.getCalcResultMargin().getSubstituteSecurityAsset0();
            BigDecimal l_bdSubstituteSecurityAsset0 = new BigDecimal("" + l_dblSubstituteSecurityAsset0);
            //���ʕ]�����v�@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʕ]�����v( T + 0 )()
            double l_dblContractAssetProfitLoss =
                this.tpCalcMargin.getCalcResultMargin().getContractAssetProfitLoss();
            //���ʏ��o��@@�@@�@@�@@�@@�E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���ʏ��o��(T+0�j�i�j
            double l_dblContractTotalCost =
                this.tpCalcMargin.getCalcResultMargin().getContractTotalCost();
            BigDecimal l_bdContractTotalCost = new BigDecimal("" + l_dblContractTotalCost);
            //����n���ϑ��@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get����n���ʌ��ϑ�(T+0)�i�j
            double l_dblUndeliContractLoss0 =
                this.tpCalcMargin.getCalcResultMargin().getUndeliContractLoss0();
            BigDecimal l_bdUndeliContractLoss0 = new BigDecimal("" + l_dblUndeliContractLoss0);

            //�����ۏ؋��@@=�@@�i�����ۏ؋��@@�{�@@��p�،��]���z�j
            //      �|�@@�iABS�iMIN�i���ʕ]�����v�C�@@0�j�j�@@�{�@@���ʏ��o��@@�{�@@����n���ϑ��j
            BigDecimal l_bdValue = new BigDecimal(Math.abs(Math.min(l_dblContractAssetProfitLoss, 0)));

            BigDecimal l_bdCount =
                l_bdValue.add(l_bdContractTotalCost).add(l_bdUndeliContractLoss0);

            log.exiting(STR_METHOD_NAME);
            return l_bdCashMarginDeposit.add(l_bdSubstituteSecurityAsset0).subtract(l_bdCount).doubleValue();
        }

        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc��ꐅ���Ǐؖ��������z)<BR>
     * (calc��ꐅ���Ǐؖ��������z)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐؖ��������z�v���Z�o���A�ԋp����B <BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���A��ꐅ���Ǐؖ��������z���v�Z����B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@�m�v�Z���n <BR>
     * �@@�@@��ꐅ���Ǐؖ��������z�@@=�@@0<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�m�v�Z���n<BR>
     * �@@�@@��ꐅ���Ǐؖ��������z�@@=�@@MAX�i��ꐅ���Ǐ؋��z�@@�|�@@�i�����ۏ؋������z�@@�{�@@�����������݊��Z�z�j�C�@@0�j<BR>
     * <BR>
     * �@@�@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�@@�E��ꐅ���Ǐ؋��z�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��ꐅ���Ǐ؋��z�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A��ꐅ���Ǐ؋��z = 0 �Ƃ���B<BR>
     * �@@�@@�@@�E�����ۏ؋������z�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋������z�i�j<BR>
     * �@@�@@�@@�E�����������݊��Z�z�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc��ꐅ���Ǐؓ����������݊��Z�z�i�j<BR>
     * <BR>
     * �Q�j�@@�v�Z������ꐅ���Ǐؖ��������z��ԋp����B<BR>
     * @@return double
     * @@roseuid 48DA349A0295
     */
    protected double calcFirstAdddepositUncancelAmt()
    {
        final String STR_METHOD_NAME = "calcFirstAdddepositUncancelAmt()";
        log.entering(STR_METHOD_NAME);
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //��ꐅ���Ǐؖ��������z�@@=�@@0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //��ꐅ���Ǐ؋��z�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��ꐅ���Ǐ؋��z�i�j
            //�������Athis.���������Ǘ�Params == null �̏ꍇ�́A��ꐅ���Ǐ؋��z = 0 �Ƃ���B
            double l_dblFirstDepositAmount = 0;
            if (this.paymentRequisitMngParams != null)
            {
                l_dblFirstDepositAmount = this.paymentRequisitMngParams.getFirstDepositAmount();
            }

            BigDecimal l_bdFirstDepositAmount = new BigDecimal("" + l_dblFirstDepositAmount);
            //�����ۏ؋������z�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋������z�i�j
            double l_dblTodayDepositPaymentAmount = this.calcTodayDepositPaymentAmount();
            BigDecimal l_bdTodayDepositPaymentAmount = new BigDecimal("" + l_dblTodayDepositPaymentAmount);
            //�����������݊��Z�z�@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc��ꐅ���Ǐؓ����������݊��Z�z�i�j
            double l_dblFirstAdddepositEliminateAmount = this.calcFirstAdddepositEliminateAmount();
            BigDecimal l_bdFirstAdddepositEliminateAmount = new BigDecimal("" + l_dblFirstAdddepositEliminateAmount);

            //��ꐅ���Ǐؖ��������z�@@=�@@MAX�i��ꐅ���Ǐ؋��z�@@�|�@@�i�����ۏ؋������z�@@�{�@@�����������݊��Z�z�j�C�@@0�j
            BigDecimal l_bdAmount = l_bdTodayDepositPaymentAmount.add(l_bdFirstAdddepositEliminateAmount);

            log.exiting(STR_METHOD_NAME);
            return Math.max(l_bdFirstDepositAmount.subtract(l_bdAmount).doubleValue(), 0);
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc��ꐅ���Ǐؖ��������ϕK�v�z)<BR>
     * (calc��ꐅ���Ǐؖ��������ϕK�v�z)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐؖ��������ϕK�v�z�v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐؖ��������ϕK�v�z�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�[��������s���B<BR>
     * �@@�ia�j��ꐅ���Ǐ؋��z�@@==�@@0�̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐؖ��������ϕK�v�z�@@=�@@0 ��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�j��ꐅ���Ǐ؋��z�@@!=�@@0�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B <BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E��ꐅ���Ǐ؋��z�@@�@@�E�E�Ethis.���������Ǘ�Params.get��ꐅ���Ǐ؋��z�i�j<BR>
     * �@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A��ꐅ���Ǐ؋��z = 0 �Ƃ���B<BR>
     * <BR>
     * �R�j�@@��ꐅ���Ǐؖ��������ϕK�v�z���v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@��ꐅ���Ǐؖ��������ϕK�v�z�@@=�@@��ꐅ���Ǐ،��ϕK�v�z�@@�~�@@��ꐅ���Ǐؖ��������z�@@�^�@@��ꐅ���Ǐ؋��z<BR>
     * �@@�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E��ꐅ���Ǐ،��ϕK�v�z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��ꐅ���Ǐ،��ϕK�v�z�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������Athis.���������Ǘ�Params == null �̏ꍇ�́A��ꐅ���Ǐ،��ϕK�v�z = 0 �Ƃ���B<BR>
     * �@@�@@�E��ꐅ���Ǐؖ��������z�@@�@@�@@�E�E�Ethis.calc��ꐅ���Ǐؖ��������z�i�j<BR>
     * <BR>
     * �S�j�@@�v�Z������ꐅ���Ǐؖ��������ϕK�v�z��ԋp����B<BR>
     * @@return double
     * @@roseuid 48DA3ABB02AC
     */
    protected double calcFirstAdddepositUncancelSettleRequiredAmt()
    {
        final String STR_METHOD_NAME = "calcFirstAdddepositUncancelSettleRequiredAmt()";
        log.entering(STR_METHOD_NAME);
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //��ꐅ���Ǐؖ��������ϕK�v�z�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //��ꐅ���Ǐ؋��z�@@�@@�E�E�Ethis.���������Ǘ�Params.get��ꐅ���Ǐ؋��z�i�j
            //�������Athis.���������Ǘ�Params == null �̏ꍇ�́A��ꐅ���Ǐ؋��z = 0 �Ƃ���B
            double l_dblFirstDepositAmount = 0;
            if (this.paymentRequisitMngParams != null)
            {
                l_dblFirstDepositAmount = this.paymentRequisitMngParams.getFirstDepositAmount();
            }

            BigDecimal l_bdFirstDepositAmount = new BigDecimal("" + l_dblFirstDepositAmount);
            //�ia�j��ꐅ���Ǐ؋��z�@@==�@@0�̏ꍇ
            //��ꐅ���Ǐؖ��������ϕK�v�z�@@=�@@0 ��ݒ肵�A�ԋp����B
            if (GtlUtils.Double.isZero(l_dblFirstDepositAmount))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //��ꐅ���Ǐ،��ϕK�v�z�@@�@@�@@�E�E�Ethis.���������Ǘ�Params.get��ꐅ���Ǐ،��ϕK�v�z�i�j
            //�������Athis.���������Ǘ�Params == null �̏ꍇ�́A��ꐅ���Ǐ،��ϕK�v�z = 0 �Ƃ���B
            double l_dblFirstSettlement = 0;
            if (this.paymentRequisitMngParams != null)
            {
                l_dblFirstSettlement = this.paymentRequisitMngParams.getFirstSettlement();
            }

            BigDecimal l_bdFirstSettlement = new BigDecimal("" + l_dblFirstSettlement);

            //��ꐅ���Ǐؖ��������z�@@�@@�@@�E�E�Ethis.calc��ꐅ���Ǐؖ��������z�i�j
            double l_dblFirstAdddepositUncancelAmt = this.calcFirstAdddepositUncancelAmt();

            BigDecimal l_bdFirstAdddepositUncancelAmt = new BigDecimal("" + l_dblFirstAdddepositUncancelAmt);
            //��ꐅ���Ǐؖ��������ϕK�v�z���v�Z����B
            //�m�v�Z���n
            //��ꐅ���Ǐؖ��������ϕK�v�z�@@=�@@��ꐅ���Ǐ،��ϕK�v�z�@@�~�@@��ꐅ���Ǐؖ��������z�@@�^�@@��ꐅ���Ǐ؋��z
            //�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j
            log.exiting(STR_METHOD_NAME);
            return l_bdFirstSettlement.multiply(
                    l_bdFirstAdddepositUncancelAmt).divide(
                        l_bdFirstDepositAmount, 0, BigDecimal.ROUND_CEILING).doubleValue();
        }

        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc��ꐅ���Ǐ؋��z)<BR>
     * (calc��ꐅ���Ǐ؋��z)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐ؋��z�v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐ؋��z�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@��񐅏��Ǐ؂̔�����s���B<BR>
     * �@@�ia�j��񐅏��Ǐؖ������@@>�@@0�@@���́@@��񐅏��Ǐؐ���(2)�@@>�@@0�@@����<BR>
     * �@@�@@�@@��񐅏��Ǐؐ���(1)�@@>�@@0�@@���́@@��񐅏��Ǐؐ��������@@>�@@0 �̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐ؋��z�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�j(a)�ȊO�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E��񐅏��Ǐؖ������@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(������)<BR>
     * �@@�@@�E��񐅏��Ǐؐ���(2)�@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(����2)<BR>
     * �@@�@@�E��񐅏��Ǐؐ���(1)�@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(����1)<BR>
     * �@@�@@�E��񐅏��Ǐؐ��������@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(�������z)<BR>
     * <BR>
     * �R�j�@@��ꐅ���Ǐ؋��z���v�Z����B<BR>
     * <BR>
     * �@@�ia�j���ʑ���@@==�@@0�̏ꍇ <BR>
     * �@@��ꐅ���Ǐ؋��z�@@=�@@0 ��ݒ肵�A�ԋp����B <BR>
     * <BR>
     * �@@�ib�j���ʑ���@@!=�@@0�̏ꍇ <BR>
     * �@@�m�v�Z���n<BR>
     * �@@��ꐅ���Ǐ؋��z�@@=�@@ABS�iMIN�i�����ۏ؋��@@�|�@@�i���ʑ���@@�~�@@��ꐅ���ۏ؋��ێ����@@�^�@@100�j�C�@@0�j�j<BR>
     * �@@���@@�u���ʑ���@@�~�@@��ꐅ���ۏ؋��ێ����@@�^�@@100�v�̌v�Z���ʂ͏����_�ȉ���؂�グ�Ƃ���B<BR> 
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�����ۏ؋��@@�@@�@@�E�E�Ethis.calc�����ۏ؋��i�j<BR>
     * �@@�@@�E���ʑ���@@�@@�@@�@@�@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʑ��( T + 0 )�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ +  this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ��( T + 0 )�i�j<BR>
     * �@@�@@�E��ꐅ���ۏ؋��ێ����@@�@@�@@�E�E�Ethis.get��ꐅ���Ǐؕۏ؋��ێ����i�j<BR>
     * <BR>
     * �S�j�@@�v�Z������ꐅ���Ǐ؋��z��ԋp����B<BR>
     * @@return double
     * @@roseuid 48DA2CBD0166
     */
    protected double calcFirstAdddepositAmount()
    {
        final String STR_METHOD_NAME = "calcFirstAdddepositAmount()";
        log.entering(STR_METHOD_NAME);

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //��ꐅ���Ǐ؋��z�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�@@�E��񐅏��Ǐؖ������@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(������)
            double l_dblSecondDepositNonPay = this.createSecondAdddepositNotClearInfo().secondDepositNonPay;
            //�@@�E��񐅏��Ǐؐ���(2)�@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(����2)
            double l_dblSecondDeposit2 = this.createSecondAdddepositNotClearInfo().secondDeposit2;
            //�@@�E��񐅏��Ǐؐ���(1)�@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(����1)
            double l_dblSecondDeposit1 = this.createSecondAdddepositNotClearInfo().secondDeposit1;
            //�@@�E��񐅏��Ǐؐ��������@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(�������z)
            double l_dblSecondDepositExpect = this.createSecondAdddepositNotClearInfo().secondDepositExpect;

            //�ia�j��񐅏��Ǐؖ������@@>�@@0�@@���́@@��񐅏��Ǐؐ���(2)�@@>�@@0�@@����
            //��񐅏��Ǐؐ���(1)�@@>�@@0�@@���́@@��񐅏��Ǐؐ��������@@>�@@0 �̏ꍇ
            //��ꐅ���Ǐ؋��z�@@=�@@0��ݒ肵�A�ԋp����B
            if (l_dblSecondDepositNonPay > 0
                || l_dblSecondDeposit2 > 0
                || l_dblSecondDeposit1 > 0
                || l_dblSecondDepositExpect > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //�����ۏ؋��@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋��i�j
            double l_dblRealMargin = this.calcRealMargin();
            BigDecimal l_bdRealMargin = new BigDecimal("" + l_dblRealMargin);
            //���ʑ���@@�@@�@@�@@�@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʑ��( T + 0 )�i�j
            //      + this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ��( T + 0 )�i�j
            BigDecimal l_bdContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getContractAmount0() + "");

            BigDecimal l_bdDayRepayContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getDayRepayContractAmount0() + "");

            //���ʑ��
            BigDecimal l_bdContractAmount = l_bdContractAmount0.add(l_bdDayRepayContractAmount0);

            //���ʑ���@@!=�@@0�̏ꍇ
            if (!GtlUtils.Double.isZero(l_bdContractAmount.doubleValue()))
            {
                //��ꐅ���ۏ؋��ێ����@@�@@�@@�E�E�Ethis.get��ꐅ���Ǐؕۏ؋��ێ����i�j
                double l_dblFirstAdddepositDepositRate = this.getFirstAdddepositDepositRate();
                BigDecimal l_bdFirstAdddepositDepositRate = new BigDecimal("" + l_dblFirstAdddepositDepositRate);
                //��ꐅ���Ǐ؋��z���v�Z����B
                //�m�v�Z���n
                //��ꐅ���Ǐ؋��z�@@=�@@ABS�iMIN�i�����ۏ؋��@@�|�@@�i���ʑ���@@�~�@@��ꐅ���ۏ؋��ێ����@@�^�@@100�j�C�@@0�j�j
                //�u���ʑ���@@�~�@@��ꐅ���ۏ؋��ێ����@@�^�@@100�v�̌v�Z���ʂ͏����_�ȉ���؂�グ�Ƃ���B
                BigDecimal l_bdAmount = l_bdContractAmount.multiply(
                    l_bdFirstAdddepositDepositRate).divide(
                        new BigDecimal(100 + ""), 0, BigDecimal.ROUND_CEILING);
                log.exiting(STR_METHOD_NAME);
                return Math.abs(Math.min(l_bdRealMargin.subtract(l_bdAmount).doubleValue(), 0));
            }
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc��ꐅ���Ǐ،��ϕK�v�z)<BR>
     * (calc��ꐅ���Ǐ،��ϕK�v�z)<BR>
     * <BR>
     * �ڋq�����̔����A�u��ꐅ���Ǐ،��ϕK�v�z�v���Z�o���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B<BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐ،��ϕK�v�z�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ<BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@��񐅏��Ǐ؂̔�����s���B<BR>
     * �@@�ia�j��񐅏��Ǐؖ������@@>�@@0�@@���́@@��񐅏��Ǐؐ���(2)�@@>�@@0�@@����<BR>
     * �@@�@@�@@��񐅏��Ǐؐ���(1)�@@>�@@0�@@���́@@��񐅏��Ǐؐ��������@@>�@@0 �̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐ،��ϕK�v�z�@@=�@@0��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�j(a)�ȊO�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E��񐅏��Ǐؖ������@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(������)<BR>
     * �@@�@@�E��񐅏��Ǐؐ���(2)�@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(����2)<BR>
     * �@@�@@�E��񐅏��Ǐؐ���(1)�@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(����1)<BR>
     * �@@�@@�E��񐅏��Ǐؐ��������@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(�������z)<BR>
     * <BR>
     * �R�j�@@�[��������s���B<BR>
     * �@@�ia�j�����ۏ؋��@@�{�@@��ꐅ���Ǐ؋��z�@@==�@@0�̏ꍇ<BR>
     * �@@�@@��ꐅ���Ǐ،��ϕK�v�z�@@=�@@0 ��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�j�����ۏ؋��@@�{�@@��ꐅ���Ǐ؋��z�@@!=�@@0�̏ꍇ<BR>
     * �@@�@@�S�j�ȍ~�̏������s���B <BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�����ۏ؋��@@�@@�@@�@@�@@�@@ �E�E�Ethis.calc�����ۏ؋��i�j<BR>
     * �@@�@@�E��ꐅ���Ǐ؋��z�@@�@@�E�E�Ethis.calc��ꐅ���Ǐ؋��z�i�j<BR>
     * <BR>
     * �S�j�@@��ꐅ���Ǐ،��ϕK�v�z���v�Z����B<BR>
     * �@@�m�v�Z���n<BR>
     * �@@��ꐅ���Ǐ،��ϕK�v�z�@@=�@@���ʑ���@@�~�@@��ꐅ���Ǐ؋��z�@@�^�@@�i�����ۏ؋��@@�{�@@��ꐅ���Ǐ؋��z�j<BR>
     * �@@�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E���ʑ���@@�@@�@@�@@�@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʑ��( T + 0 )�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ +  this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ��( T + 0 )�i�j<BR>
     * <BR>
     * �S�j�@@�v�Z������ꐅ���Ǐ،��ϕK�v�z��ԋp����B<BR>
     * @@return double
     * @@roseuid 48DA2FB90063
     */
    protected double calcFirstAdddepositSettlement()
    {
        final String STR_METHOD_NAME = "calcFirstAdddepositSettlement()";
        log.entering(STR_METHOD_NAME);
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //��ꐅ���Ǐ،��ϕK�v�z�@@=�@@0��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�@@�E��񐅏��Ǐؖ������@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(������)
            double l_dblSecondDepositNonPay = this.createSecondAdddepositNotClearInfo().secondDepositNonPay;
            //�@@�E��񐅏��Ǐؐ���(2)�@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(����2)
            double l_dblSecondDeposit2 = this.createSecondAdddepositNotClearInfo().secondDeposit2;
            //�@@�E��񐅏��Ǐؐ���(1)�@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(����1)
            double l_dblSecondDeposit1 = this.createSecondAdddepositNotClearInfo().secondDeposit1;
            //�@@�E��񐅏��Ǐؐ��������@@�@@�@@�E�E�Ethis.create��񐅏��Ǐؖ��������i�j.�Ǐ؋��z(�������z)
            double l_dblSecondDepositExpect = this.createSecondAdddepositNotClearInfo().secondDepositExpect;

            //�ia�j��񐅏��Ǐؖ������@@>�@@0�@@���́@@��񐅏��Ǐؐ���(2)�@@>�@@0�@@����
            //��񐅏��Ǐؐ���(1)�@@>�@@0�@@���́@@��񐅏��Ǐؐ��������@@>�@@0 �̏ꍇ
            //��ꐅ���Ǐ،��ϕK�v�z�@@=�@@0��ݒ肵�A�ԋp����B
            if (l_dblSecondDepositNonPay > 0
                || l_dblSecondDeposit2 > 0
                || l_dblSecondDeposit1 > 0
                || l_dblSecondDepositExpect > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //�����ۏ؋��@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋��i�j
            double l_dblRealMargin = this.calcRealMargin();
            BigDecimal l_bdRealMargin = new BigDecimal("" + l_dblRealMargin);

            //��ꐅ���Ǐ؋��z�@@�@@�E�E�Ethis.calc��ꐅ���Ǐ؋��z�i�j
            double l_dblFirstAdddepositAmount = this.calcFirstAdddepositAmount();
            BigDecimal l_bdFirstAdddepositAmount = new BigDecimal("" + l_dblFirstAdddepositAmount);
            //�ia�j�����ۏ؋��@@�{�@@��ꐅ���Ǐ؋��z�@@==�@@0�̏ꍇ
            //��ꐅ���Ǐ،��ϕK�v�z�@@=�@@0 ��ݒ肵�A�ԋp����B
            BigDecimal l_bdAmount = l_bdFirstAdddepositAmount.add(l_bdRealMargin);
            if (GtlUtils.Double.isZero(l_bdAmount.doubleValue()))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //���ʑ���@@�@@�@@�@@�@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʑ��( T + 0 )�i�j
            //      + this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ��( T + 0 )�i�j
            BigDecimal l_bdContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getContractAmount0() + "");

            BigDecimal l_bdDayRepayContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getDayRepayContractAmount0() + "");

            //���ʑ��
            BigDecimal l_bdContractAmount = l_bdContractAmount0.add(l_bdDayRepayContractAmount0);

            //��ꐅ���Ǐ،��ϕK�v�z���v�Z����B
            //�m�v�Z���n
            //��ꐅ���Ǐ،��ϕK�v�z�@@=�@@���ʑ���@@�~�@@��ꐅ���Ǐ؋��z�@@�^�@@�i�����ۏ؋��@@�{�@@��ꐅ���Ǐ؋��z�j
            //�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j
            log.exiting(STR_METHOD_NAME);
            return l_bdContractAmount.multiply(
                    l_bdFirstAdddepositAmount).divide(
                        l_bdAmount, 0, BigDecimal.ROUND_CEILING).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc��񐅏��Ǐ؋��z)<BR>
     * (calc��񐅏��Ǐ؋��z) <BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐ؋��z�v���Z�o���A�ԋp����B <BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ <BR>
     * �@@�@@��񐅏��Ǐ؋��z�@@=�@@0 ��ݒ肵�A�ԋp����B <BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ <BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B  <BR>
     * <BR>
     * �Q�j�@@�ۏ؋��a�����̔�����s���B <BR>
     * �@@�ia�j�ۏ؋��a�����@@>�@@��񐅏��ۏ؋��ێ��� �̏ꍇ <BR>
     * �@@�@@��񐅏��Ǐ؋��z�@@=�@@0 ��ݒ肵�A�ԋp����B <BR>
     * <BR>
     * �@@�ib�j(a)�ȊO�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �@@���@@����Ɏg�p����e�l�̎擾���@@ <BR>
     * �@@�@@�E�ۏ؋��a�����@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�ۏ؋����i�j<BR>
     * �@@�@@�E��񐅏��ۏ؋��ێ����@@�@@�E�E�Ethis.get��񐅏��Ǐؕۏ؋��ێ����i�j<BR>
     * <BR>
     * �R�j�@@��񐅏��Ǐ؋��z���v�Z����B<BR>
     * �@@�m�v�Z���n <BR>
     * �@@��񐅏��Ǐ؋��z�@@=�@@ABS�iMIN�i�����ۏ؋��@@�|�@@���ʑ���@@�~�@@��񐅏��ۏ؋��߂��ێ����@@�^�@@100�C�@@0�j�j<BR>
     * �@@���@@�u���ʑ���@@�~�@@��񐅏��ۏ؋��߂��ێ����@@�^�@@100�v�̌v�Z���ʂ͏����_�ȉ���؂�グ�Ƃ���B<BR> 
     * <BR>
     * �@@���@@�v�Z���Ɏg�p����e�l�̎擾���@@ <BR>
     * �@@�@@�E�����ۏ؋��@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋��i�j<BR>
     * �@@�@@�E���ʑ���@@�@@�@@�@@�@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʑ��( T + 0 )�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ +  this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ��( T + 0 )�i�j<BR>
     * �@@�@@�E��񐅏��ۏ؋��߂��ێ����@@�@@�@@�E�E�Ethis.get��񐅏��Ǐؕۏ؋��߂��ێ����i�j<BR>
     * <BR>
     * �S�j�@@�v�Z������񐅏��Ǐ؋��z��ԋp����B<BR>
     * @@return double
     * @@roseuid 48DAD72501DD
     */
    protected double calcSecondAdddepositAmount()
    {
        final String STR_METHOD_NAME = "calcSecondAdddepositAmount()";
        log.entering(STR_METHOD_NAME);
        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //��񐅏��Ǐ،��ϕK�v�z�@@=�@@0 ��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //�@@�@@�E�ۏ؋��a�����@@�@@�@@�@@�@@�@@�@@�@@�E�E�Ethis.calc�ۏ؋����i�j
            double l_dblMarginDepositRate = this.calcMarginDepositRate();
            // �@@�E��񐅏��ۏ؋��ێ����@@�@@�E�E�Ethis.get��񐅏��Ǐؕۏ؋��ێ����i�j
            double l_dblSecondAdddepositDepositRate = this.getSecondAdddepositDepositRate();

            //�ia�j�ۏ؋��a�����@@>�@@��񐅏��ۏ؋��ێ��� �̏ꍇ
            //��񐅏��Ǐ؋��z�@@=�@@0 ��ݒ肵�A�ԋp����B
            if (l_dblMarginDepositRate > l_dblSecondAdddepositDepositRate)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //�����ۏ؋��@@�@@�@@�@@�@@�E�E�Ethis.calc�����ۏ؋��i�j
            double l_dblRealMargin = this.calcRealMargin();
            BigDecimal l_bdRealMargin = new BigDecimal("" + l_dblRealMargin);
            //���ʑ���@@�@@�@@�@@�@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʑ��( T + 0 )�i�j
            //      + this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ��( T + 0 )�i�j
            BigDecimal l_bdContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getContractAmount0() + "");

            BigDecimal l_bdDayRepayContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getDayRepayContractAmount0() + "");

            //���ʑ��
            BigDecimal l_bdContractAmount = l_bdContractAmount0.add(l_bdDayRepayContractAmount0);
            //��񐅏��ۏ؋��߂��ێ����@@�@@�@@ �E�E�Ethis.get��񐅏��Ǐؕۏ؋��߂��ێ����i�j
            double l_dblSecondAdddepositDepositBackRate = this.getSecondAdddepositDepositBackRate();
            BigDecimal l_bdSecondAdddepositDepositBackRate =
                new BigDecimal(l_dblSecondAdddepositDepositBackRate + "");

            //��񐅏��Ǐ؋��z���v�Z����B
            //�m�v�Z���n
            //��񐅏��Ǐ؋��z�@@=�@@ABS�iMIN�i�����ۏ؋��@@�|�@@���ʑ���@@�~�@@��񐅏��ۏ؋��߂��ێ����@@�^�@@100�C�@@0�j�j
            //�u���ʑ���@@�~�@@��񐅏��ۏ؋��߂��ێ����@@�^�@@100�v�̌v�Z���ʂ͏����_�ȉ���؂�グ�Ƃ���B 
            BigDecimal l_bdAmount = l_bdContractAmount.multiply(
                l_bdSecondAdddepositDepositBackRate).divide(
                    new BigDecimal(100 + ""), 0, BigDecimal.ROUND_CEILING);

            log.exiting(STR_METHOD_NAME);
            return Math.abs(Math.min(l_bdRealMargin.subtract(l_bdAmount).doubleValue(), 0));
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc��񐅏��Ǐ،��ϕK�v�z)<BR>
     * (calc��񐅏��Ǐ،��ϕK�v�z) <BR>
     * <BR>
     * �ڋq�����̔����A�u��񐅏��Ǐ،��ϕK�v�z�v���Z�o���A�ԋp����B <BR>
     * <BR>
     * �P�j�@@�ڋq�����̔�����s���B <BR>
     * �@@�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ <BR>
     * �@@�@@��񐅏��Ǐ،��ϕK�v�z�@@=�@@0 ��ݒ肵�A�ԋp����B <BR>
     * <BR>
     * �@@�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ <BR>
     * �@@�@@�Q�j�ȍ~�̏������s���B  <BR>
     * <BR>
     * �Q�j�@@�[��������s���B<BR>
     * �@@�ia�j�����ۏ؋��@@�{�@@��񐅏��Ǐ؋��z�@@==�@@0�̏ꍇ<BR>
     * �@@�@@��񐅏��Ǐ،��ϕK�v�z�@@=�@@0 ��ݒ肵�A�ԋp����B<BR>
     * <BR>
     * �@@�ib�j�����ۏ؋��@@�{�@@��񐅏��Ǐ؋��z�@@!=�@@0�̏ꍇ<BR>
     * �@@�@@�R�j�ȍ~�̏������s���B <BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E�����ۏ؋��@@�@@�@@�E�E�Ethis.calc�����ۏ؋��i�j<BR>
     * �@@�@@�E��񐅏��Ǐ؋��z�@@�@@�@@�E�E�Ethis.calc��񐅏��Ǐ؋��z�i�j<BR>
     * <BR>
     * �R�j�@@��񐅏��Ǐ،��ϕK�v�z���v�Z����B <BR>
     * �@@�m�v�Z���n <BR>
     * �@@��񐅏��Ǐ،��ϕK�v�z�@@=�@@���ʑ���@@�~�@@��񐅏��Ǐ؋��z�@@�^�@@�i�����ۏ؋��@@�{�@@��񐅏��Ǐ؋��z�j<BR>
     * �@@�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j<BR>
     * <BR>
     * �@@���@@�e�l�̎擾���@@<BR>
     * �@@�@@�E���ʑ���@@�@@�@@�@@�@@�@@�@@ �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʑ��( T + 0 )�i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ +  this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ��( T + 0 )�i�j<BR>
     * <BR>
     * �S�j�@@�v�Z������񐅏��Ǐ،��ϕK�v�z��ԋp����B<BR>
     * @@return double
     * @@roseuid 48DADE880267
     */
    protected double calcSecondAdddepositSettlement()
    {
        final String STR_METHOD_NAME = "calcSecondAdddepositSettlement()";
        log.entering(STR_METHOD_NAME);

        //�ia�jthis.�M�p��������t���O�@@==�@@0(�����ڋq)�̏ꍇ
        //��񐅏��Ǐ،��ϕK�v�z�@@=�@@0 ��ݒ肵�A�ԋp����B
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�ib�jthis.�M�p��������t���O�@@==�@@1(�M�p�ڋq)�̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {

            //�����ۏ؋��@@�@@�@@�E�E�Ethis.calc�����ۏ؋��i�j
            double l_dblRealMargin = this.calcRealMargin();
            //��񐅏��Ǐ؋��z�@@�@@�@@�E�E�Ethis.calc��񐅏��Ǐ؋��z�i�j
            double l_dblSecondAdddepositAmount = this.calcSecondAdddepositAmount();

            BigDecimal l_bdRealMargin = new BigDecimal(l_dblRealMargin + "");
            BigDecimal l_bdSecondAdddepositAmount = new BigDecimal(l_dblSecondAdddepositAmount + "");

            BigDecimal l_bdValue = l_bdRealMargin.add(l_bdSecondAdddepositAmount);

            //�ia�j�����ۏ؋��@@�{�@@��񐅏��Ǐ؋��z�@@==�@@0�̏ꍇ
            if (GtlUtils.Double.isZero(l_bdValue.doubleValue()))
            {
                //��񐅏��Ǐ،��ϕK�v�z�@@=�@@0 ��ݒ肵�A�ԋp����B
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //���ʑ��   �E�E�Ethis.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʑ��( T + 0 )�i�j
            //        + this.���Y�]�͏��<�M�p�ڋq>.get�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ��( T + 0 )�i�j
            BigDecimal l_bdContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getContractAmount0() + "");

            BigDecimal l_bdDayRepayContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getDayRepayContractAmount0() + "");

            //���ʑ��
            BigDecimal l_bdContractAmount = l_bdContractAmount0.add(l_bdDayRepayContractAmount0);
            //��񐅏��Ǐ،��ϕK�v�z���v�Z����B
            //�m�v�Z���n
            //��񐅏��Ǐ،��ϕK�v�z�@@=�@@���ʑ���@@�~�@@��񐅏��Ǐ؋��z�@@�^�@@�i�����ۏ؋��@@�{�@@��񐅏��Ǐ؋��z�j
            //�i�v�Z���ʂ̏����_�ȉ���؂�グ�Ƃ���B�j
            log.exiting(STR_METHOD_NAME);
            return l_bdContractAmount.multiply(
                        l_bdSecondAdddepositAmount).divide(
                            l_bdValue, 0, BigDecimal.ROUND_CEILING).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }
}
@
