head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPBondSimplexCooperationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���V���v���N�X�A�g�T�[�r�XImpl(WEB3TPBondSimplexCooperationServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/26 ���z(���u) �V�K�쐬 ���f��No.277,279,280,281
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RestraintDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.service.delegate.WEB3TPBondSimplexCooperationService;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���V���v���N�X�A�g�T�[�r�XImpl)<BR>
 * (���V���v���N�X�A�g�T�[�r�XImpl)<BR>
 *
 * @@author ���z
 * @@version 1.0
 */
public class WEB3TPBondSimplexCooperationServiceImpl implements WEB3TPBondSimplexCooperationService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPBondSimplexCooperationServiceImpl.class);

    /**
     * (�S�������)<BR>
     * (�萔)<BR>
     * �S�������<BR>
     */
    private static final String RESTRAINT_DIV = WEB3RestraintDivDef.BOND_BUY_AMOUNT;

    /**
     * (save�����t���)<BR>
     * �isave�����t����j<BR>
     * <BR>
     * �����t�����̑�����S������B<BR>
     * <BR>
     * ���V�[�P���X�}�u(���V���v���b�N�X�A�g�T�[�r�X)save�����t����v���Q�ƁB<BR>
     * ================================================<BR>
     * �V�[�P���X�}�F((���V���v���b�N�X�A�g�T�[�r�X)save�����t���)<BR>
     * ��̈ʒu�F(isExist����No()�̖߂�l���Atrue�̏ꍇ)<BR>
     * class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_03095<BR>
     * ================================================<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * �i����ID�j<BR>
     * @@param l_dblRestraint - (�����t���)<BR>
     * �i�����t����j<BR>
     * @@param l_datFinTransactionDate - (�g�����U�N�V����������)<BR>
     * �i�g�����U�N�V�����������j<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * �i��n���j<BR>
     * @@param l_strOrderNo - (����No)<BR>
     * �i����No�j<BR>
     * @@throws WEB3BaseException
     */
    public void saveBondBuyAmount(long l_lngAccountId, double l_dblRestraint,
        Date l_datFinTransactionDate, Date l_datDeliveryDate, String l_strOrderNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveBondBuyAmount(long, double, Date, Date, String)";
        log.entering(STR_METHOD_NAME);

        //���̑��S�����i���S���j�e�[�u���ɁA
        //����.����No�����݂��Ă��邩�ǂ����̔��ʂ��s���B
        //�@@[����]
        // �@@ ����No = ����.����No
        boolean l_blnIsExistOrderNo = isExistOrderNo(l_strOrderNo);

        //(*)����t���[
        //isExist����No()�̖߂�l���Atrue�̏ꍇ
        //��O�u����No�d���G���[�v���X���[����B
        if (l_blnIsExistOrderNo)
        {
            log.debug("����No�d���G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03095,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����No�d���G���[");
        }

        //�����t����̃f�[�^���A���̑��S�����i���S���j�e�[�u���֑}������B
        //�@@[����]
        //�@@�@@����ID = ����.����ID
        //�@@�@@�S���� = ����.�����t���
        //�@@�@@�g�����U�N�V���������� = ����.�g�����U�N�V����������
        //�@@�@@��n�� = ����.��n��
        //�@@�@@�폜�L�[�P = ����.����No
        //�@@�@@�S������� = this.�S�������
        WEB3TPPersistentDataManager.getInstance().saveOtherRestraint(
            l_lngAccountId, l_dblRestraint, l_datFinTransactionDate, l_datDeliveryDate, l_strOrderNo, RESTRAINT_DIV);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (cancel�����t���)<BR>
     * �icancel�����t����j<BR>
     * <BR>
     * �����t�����̑���̍S������������B<BR>
     * <BR>
     * ���V�[�P���X�}�u(���V���v���b�N�X�A�g�T�[�r�X)cancel�����t����v���Q�ƁB<BR>
     * ================================================<BR>
     * �V�[�P���X�}�F((���V���v���b�N�X�A�g�T�[�r�X)cancel�����t���)<BR>
     * ��̈ʒu�F(isExist����No()�̖߂�l���Afalse�̏ꍇ)<BR>
     * class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_03096<BR>
     * ================================================<BR>
     * <BR>
     * @@param l_strOrderNo - (����No)<BR>
     * �i����No�j<BR>
     * @@throws WEB3BaseException
     */
    public void cancelBondBuyAmount(String l_strOrderNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "cancelBondBuyAmount(String)";
        log.entering(STR_METHOD_NAME);

        //���̑��S�����i���S���j�e�[�u���ɁA
        //����.����No�����݂��Ă��邩�ǂ����̔��ʂ��s���B
        //  [����]
        //    ����No = ����.����No
        boolean l_blnIsExistOrderNo = isExistOrderNo(l_strOrderNo);

        //(*)����t���[
        //isExist����No()�̖߂�l���Afalse�̏ꍇ��
        //��O�u����f�[�^�Ȃ��G���[�v���X���[����B
        if (!l_blnIsExistOrderNo)
        {
            log.debug("����f�[�^�Ȃ��G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03096,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����f�[�^�Ȃ��G���[");
        }

        //���̑��S�����i���S���j�e�[�u�����X�V�i�_���폜�j����B
        //�@@[����]
        //�@@�@@�S������� = this.�S�������
        //�@@�@@�폜�L�[�P = ����.����No
        WEB3TPPersistentDataManager.getInstance().deleteOtherRestraint(RESTRAINT_DIV, l_strOrderNo);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (isExist����No)<BR>
     * (isExist����No)<BR>
     * <BR>
     * ���̑��S�����i���S���j�e�[�u���Ɉ���.����No�����݂��邩�ǂ������f����B<BR>
     * �@@�����R�[�h�����݂���ꍇ�́Atrue ��ԋp����B<BR>
     * �@@�@@���R�[�h�����݂��Ȃ��ꍇ�́Afalse ��ԋp����B<BR>
     * <BR>
     * �@@-�]�̓f�[�^�\�[�X�A�N�Z�X�Ǘ�.isExist���̑��S�����i���S���j()���R�[�����A<BR>
     * �@@�@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�S������� = this.�S�������<BR>
     * �@@�@@�@@�폜�L�[�P = ����.����No<BR>
     * <BR>
     * @@param l_strOrderNo - (����No)<BR>
     * �i����No�j<BR>
     * @@return boolean
     */
    private boolean isExistOrderNo(String l_strOrderNo) throws WEB3BaseException
    {
        //�]�̓f�[�^�\�[�X�A�N�Z�X�Ǘ�.isExist���̑��S�����i���S���j()���R�[�����A�߂�l��ԋp����
        return WEB3TPPersistentDataManager.getInstance().isExistOtherRestraint(RESTRAINT_DIV, l_strOrderNo);
    }

    /**
     * (get�؋����c��)<BR>
     * �iget�؋����c���j<BR>
     * <BR>
     * �؋����c�����擾����B<BR>
     * <BR>
     * �P�j-�]�̓f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�ڋq����c��(�}�X�^���)()���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�E����ID = �ڋq.getAccountId()�̖߂�l<BR>
     * �@@�@@�E�⏕����ID = �ڋq.getSubAccount(<BR>
     * �@@�@@�@@7:�����I�v�V������������i�敨�؋����j).getSubAccountId()�̖߂�l<BR>
     * <BR>
     * �@@�@@���ڋq.getSubAccount()�ŕ⏕�����I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�A0��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�Ŏ擾�����ڋq����c��(�}�X�^���)Row�̎c����ԋp����B<BR>
     * <BR>
     * �@@�؋����c�� = �ڋq����c��(�}�X�^���)Row.�c���i����+n���j<BR>
     * <BR>
     * �@@���� = ����.�w���<BR>
     * �@@���P�j�̖߂�l��null�̏ꍇ�A0��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �i�ڋq�j<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * �i�w����j<BR>
     * @@return double
     */
    private double getDepositBalance(MainAccount l_mainAccount, int l_intReservedDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDepositBalance(MainAccount, int)";
        log.entering(STR_METHOD_NAME);

        //�⏕����ID
        long l_lngSubAccountId = 0L;
        try
        {
            //�ڋq.getSubAccount(7:�����I�v�V������������i�敨�؋����j)
            SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            //�⏕����.getSubAccountId()
            l_lngSubAccountId = l_subAccount.getSubAccountId();
        }
        catch (NotFoundException l_ex)
        {
            //���ڋq.getSubAccount()�ŕ⏕�����I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�A0��ԋp����B
            return 0.0D;
        }

        //�]�̓f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�ڋq����c��(�}�X�^���)()
        TpCashBalanceRow l_tpCashBalanceRow =
            WEB3TPPersistentDataManager.getInstance().getTpCashBalanceRow(
                l_mainAccount.getAccountId(), l_lngSubAccountId);

        double l_dblCash = 0.0D;
        if (l_tpCashBalanceRow != null)
        {
            switch (l_intReservedDate)
            {
                //�ڋq����c��(�}�X�^���)Row.�c���i����+�O���j
                case WEB3TPSpecifiedPointDef.T_0:
                    l_dblCash = l_tpCashBalanceRow.getCashBalance0();
                    break;

                //�ڋq����c��(�}�X�^���)Row.�c���i����+�P���j
                case WEB3TPSpecifiedPointDef.T_1:
                    l_dblCash = l_tpCashBalanceRow.getCashBalance1();
                    break;

                //�ڋq����c��(�}�X�^���)Row.�c���i����+�Q���j
                case WEB3TPSpecifiedPointDef.T_2:
                    l_dblCash = l_tpCashBalanceRow.getCashBalance2();
                    break;

                //�ڋq����c��(�}�X�^���)Row.�c���i����+�R���j
                case WEB3TPSpecifiedPointDef.T_3:
                    l_dblCash = l_tpCashBalanceRow.getCashBalance3();
                    break;

                //�ڋq����c��(�}�X�^���)Row.�c���i����+�S���j
                case WEB3TPSpecifiedPointDef.T_4:
                    l_dblCash = l_tpCashBalanceRow.getCashBalance4();
                    break;

                //�ڋq����c��(�}�X�^���)Row.�c���i����+�T���ȍ~�j
                default :
                    l_dblCash = l_tpCashBalanceRow.getCashBalance5();
                    break;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblCash;
    }

    /**
     * (get���Y�]���z�ꗗ)<BR>
     * �iget���Y�]���z�ꗗ�j<BR>
     * <BR>
     * ���Y�]���z�ꗗ���쐬���A�ԋp����B<BR>
     * <BR>
     * ���V�[�P���X�}�u�i���V���v���N�X�A�g�T�[�r�X�jget���Y�]���z�ꗗ�v���Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * (�⏕����)<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAssetList(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAssetList(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //�M�p�������J�݂��Ă��邩�ǂ����̔��ʂ��s���B
        //true�F�@@�J�ݍ�
        //false�F�@@���J��
        //[����]
        //�ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�]�͌v�Z�����I�u�W�F�N�g�𐶐�����B
        //[����]
        //�⏕�����F�@@����.�⏕����
        WEB3TPCalcCondition l_calcCondition =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //�a����]���z
        BigDecimal l_bdAccountAsset = null;
        //�����]���z
        double l_dblEquityAsset = 0.0D;
        //�����M���]���z
        BigDecimal l_bdMutualFundAsset = null;

        //(*)����t���[
        //is�M�p�����J��()�̖߂�l = false(�����ڋq)
        //�̏ꍇ�͈ȉ��̏��������{����B
        if (!l_blnIsMarginAccountEstablished)
        {
            //�]�͌v�Z����<�����ڋq>���擾����B
            //[����]
            //long�F�@@�⏕����.getAccountId()�̖߂�l
            List l_lisCalcResultEquitys =
                WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

            //���Y�]�͏��<�����ڋq>�I�u�W�F�N�g�𐶐�����B
            //[����]
            //�]�͌v�Z����Params�F�@@find�]�͌v�Z����<�����ڋq>()�̖߂�l
            //�]�͌v�Z�����F�@@create�]�͌v�Z����()�̖߂�l
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResultEquitys, l_calcCondition);

            //�a����c�����擾����B
            double l_dblAccountBalance = l_calcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_5);

            //�؋����c�����擾����B
            //�@@[����]
            //�@@�@@�ڋq = getMainAccount()�̖߂�l
            //�@@�@@�w��� = 5
            double l_dblDepositBalance = getDepositBalance(l_mainAccount, WEB3TPSpecifiedPointDef.T_5);

            //�a����]���z���v�Z����
            BigDecimal l_bdAccountBalance = new BigDecimal(String.valueOf(l_dblAccountBalance));
            BigDecimal l_bdDepositBalance = new BigDecimal(String.valueOf(l_dblDepositBalance));
            l_bdAccountAsset = l_bdAccountBalance.add(l_bdDepositBalance);

            //�]�͌v�Z���ʏڍ�<�����ڋq>Params���擾����B
            TpCalcResultEquityDetailParams l_tpCalcResultEquityDetailParams =
                l_calcEquity.getCalcResultDetailEquity();

            //�����]���z���v�Z����
            l_dblEquityAsset = l_tpCalcResultEquityDetailParams.getEquityAssetExecuted();

            //�����M���]���z =
            //    �]�͌v�Z���ʏڍ�(�����ڋq)Params.�����M���]���z(��c)
            //    + �]�͌v�Z���ʏڍ�(�����ڋq)Params.�ݐϓ����]���z(��c)
            double l_dblMutualFundAssetExecuted = l_tpCalcResultEquityDetailParams.getMutualFundAssetExecuted();
            double l_dblRuitoAssetExecuted = l_tpCalcResultEquityDetailParams.getRuitoAssetExecuted();
            BigDecimal l_bdMutualFundAssetExecuted = new BigDecimal(String.valueOf(l_dblMutualFundAssetExecuted));
            BigDecimal l_bdRuitoAssetExecuted = new BigDecimal(String.valueOf(l_dblRuitoAssetExecuted));
            l_bdMutualFundAsset = l_bdMutualFundAssetExecuted.add(l_bdRuitoAssetExecuted);
        }
        //(*)����t���[
        //is�M�p�����J��()�̖߂�l = true(�M�p�ڋq)
        //�̏ꍇ�͈ȉ��̏��������{����B
        else
        {
            //�]�͌v�Z����(List)���擾
            List l_lisCalcResultMargins =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //���Y�]�͏��
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResultMargins, l_calcCondition);

            //�a����c�����擾����B
            double l_dblAccountBalance = l_calcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_5);

            //�؋����c�����擾����B
            //�@@[����]
            //�@@�@@�ڋq = getMainAccount()�̖߂�l
            //�@@�@@�w��� = 5
            double l_dblDepositBalance = getDepositBalance(l_mainAccount, WEB3TPSpecifiedPointDef.T_5);

            //�a����]���z���v�Z����
            BigDecimal l_bdAccountBalance = new BigDecimal(String.valueOf(l_dblAccountBalance));
            BigDecimal l_bdDepositBalance = new BigDecimal(String.valueOf(l_dblDepositBalance));
            l_bdAccountAsset = l_bdAccountBalance.add(l_bdDepositBalance);

            //�]�͌v�Z���ʏڍ�<�M�p�ڋq>Params���擾����B
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams =
                l_calcMargin.getCalcResultDetailMargin();

            //�����]���z���v�Z����
            l_dblEquityAsset = l_tpCalcResultMarginDetailParams.getEquityAssetExecuted();

            //�����M���]���z =
            //    �]�͌v�Z���ʏڍ�(�M�p�ڋq)Params.�����M���]���z(��c)
            //    + �]�͌v�Z���ʏڍ�(�M�p�ڋq)Params.�ݐϓ����]���z(��c)
            double l_dblMutualFundAssetExecuted = l_tpCalcResultMarginDetailParams.getMutualFundAssetExecuted();
            double l_dblRuitoAssetExecuted = l_tpCalcResultMarginDetailParams.getRuitoAssetExecuted();
            BigDecimal l_bdMutualFundAssetExecuted = new BigDecimal(String.valueOf(l_dblMutualFundAssetExecuted));
            BigDecimal l_bdRuitoAssetExecuted = new BigDecimal(String.valueOf(l_dblRuitoAssetExecuted));
            l_bdMutualFundAsset = l_bdMutualFundAssetExecuted.add(l_bdRuitoAssetExecuted);
        }

        //���Y�]���z�ꗗ�̍쐬
        //�a����]���z�A�����]���z�A�����M���]���z�̏��ɕ�����ɕϊ����Z�b�g����B
        //����؂蕶���̓R����(:)
        //��j�a����]���z300���A�����]���z20���A�����M���]���z105���̏ꍇ
        //  [3000000:200000:1050000]
        String l_strAssetList = 
            WEB3StringTypeUtility.formatNumber(l_bdAccountAsset.doubleValue()) + ":"
            + WEB3StringTypeUtility.formatNumber(l_dblEquityAsset)+ ":"
            + WEB3StringTypeUtility.formatNumber(l_bdMutualFundAsset.doubleValue());

        log.exiting(STR_METHOD_NAME);
        return l_strAssetList;
    }

}
@
