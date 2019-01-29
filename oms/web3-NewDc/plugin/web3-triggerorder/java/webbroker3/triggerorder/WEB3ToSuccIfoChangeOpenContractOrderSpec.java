head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoChangeOpenContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�\��V�K�������������e(WEB3ToSuccIfoChangeOpenContractOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/11 �����Q (���u) �V�K�쐬
Revision History : 2008/03/18 ��іQ (���u) ���f��NO.273�C284, 293
*/
package webbroker3.triggerorder;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeOpenContractOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�\��V�K�������������e)<BR>
 * �敨OP�\�񒍕��������e�̓��͂�\������B<BR>
 * <BR>
 * xTrade��IfoChangeOpenContractOrderSpec���g�������N���X�B<BR>
 * @@author �����Q
 * @@version 1.0
 */
public class WEB3ToSuccIfoChangeOpenContractOrderSpec extends IfoChangeOpenContractOrderSpec
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccIfoChangeOpenContractOrderSpec.class);

    /**
     * @@param l_lngOrderId<BR>
     * @@param l_lngOrderUnitId<BR>
     * @@param l_dblAfterChangeOriginalQty<BR>
     * @@param l_dblAfterChangePrice<BR>
     */
    public WEB3ToSuccIfoChangeOpenContractOrderSpec(
        long l_lngOrderId,
        long l_lngOrderUnitId,
        double l_dblAfterChangeOriginalQty,
        double l_dblAfterChangePrice)
    {
        super(l_lngOrderId, l_lngOrderUnitId, l_dblAfterChangeOriginalQty, l_dblAfterChangePrice);
    }

    /**
     * (������T�Z��n���)<BR>
     * ������T�Z��n����B<BR>
     */
    private double modifiedEstimatedPrice;

    /**
     * (������v�Z�P��)<BR>
     * ������v�Z�P���B<BR>
     */
    private double modifiedCalcUnitPrice;

    /**
     * (�����㒍��������)<BR>
     * �����㒍���������B<BR>
     */
    private Date modifiedExpirationDate;

    /**
     * (����)<BR>
     * �㗝���͈��ҁB<BR>
     */
    private WEB3GentradeTrader trader;

    /**
     * (������P�������l)<BR>
     * ������P�������l�B<BR>
     */
    private Double modifiedPriceAdjustValue;

    /**
     * (���񒍕��̒����P��ID)<BR>
     * ���񒍕��̒����P��ID�B<BR>
     */
    private Long firstOrderUnitId;

    /**
     * (�[��O�J�z�Ώۃt���O)<BR>
     * �[��O�J�z�Ώۃt���O�B<BR>
     */
    private boolean eveningSessionCarryOverFlag;

    /**
     * (���������敪)<BR>
     * ���������敪�B<BR>
     */
    private String expirationDateType;

    /**
     * (create�敨OP�\��V�K�������������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �敨OP�\��V�K�������������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �葱���̓V�[�P���X�}�u�i�A�������jcreate�敨OP�\��V�K�������������e�v���Q�ƁB<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_dblModifiedQuanity - (�����㐔��)<BR>
     * �����㐔��<BR>
     * @@param l_dblModifiedLimitPrice - (������w�l)<BR>
     * ������w�l<BR>
     * @@param l_dblModifiedEstimatedPrice - (������T�Z��n���)<BR>
     * ������T�Z��n���<BR>
     * @@param l_dblModifiedCalcUnitPrice - (������v�Z�P��)<BR>
     * ������v�Z�P��<BR>
     * @@param l_datModifiedExpirationDate - (�����㒍��������)<BR>
     * �����㒍��������<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * ���҃I�u�W�F�N�g<BR>
     * @@param l_modifiedPriceAdjustValue - (������P�������l)<BR>
     * ������P�������l<BR>
     * @@param l_strExpirationDateType - (���������敪)<BR>
     * ���������敪<BR>
     * @@return webbroker3.triggerorder.WEB3ToSuccIfoChangeOpenContractOrderSpec
     * @@throws WEB3BaseException
     */
    public static WEB3ToSuccIfoChangeOpenContractOrderSpec createIfoChangeOpenContractOrderSpec(
        long l_lngOrderId,
        double l_dblModifiedQuanity,
        double l_dblModifiedLimitPrice,
        double l_dblModifiedEstimatedPrice,
        double l_dblModifiedCalcUnitPrice,
        Date l_datModifiedExpirationDate,
        WEB3GentradeTrader l_trader,
        Double l_modifiedPriceAdjustValue,
        String l_strExpirationDateType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoChangeOpenContractOrderSpec(long, "
            + "double, double, double, double, Date, WEB3GentradeTrader,"
            + " Double, String) ";
        log.entering(STR_METHOD_NAME);

        //IfoChangeOpenContractOrderSpec(arg0 : long, arg1 : long, arg2 : double, arg3 : double)
        //����ID �F �p�����[�^.����ID
        //�����P��ID �F -1�i�����������̏�Ԃ�\���j
        //�����㐔�� �F �p�����[�^.�����㐔��
        //������w�l �F �p�����[�^.������w�l
        WEB3ToSuccIfoChangeOpenContractOrderSpec l_ifoChangeOpenContractOrderSpec =
            new WEB3ToSuccIfoChangeOpenContractOrderSpec(
                l_lngOrderId,
                -1,
                l_dblModifiedQuanity,
                l_dblModifiedLimitPrice);

        // set������T�Z��n���(double)
        //������T�Z��n��� �F ����.������T�Z��n���
        l_ifoChangeOpenContractOrderSpec.setModifiedEstimatedPrice(l_dblModifiedEstimatedPrice);

        //set������v�Z�P��(double)
        //������v�Z�P�� �F ����.������v�Z�P��
        l_ifoChangeOpenContractOrderSpec.setModifiedCalcUnitPrice(l_dblModifiedCalcUnitPrice);

        //set�����㒍��������(Date)
        //�����㎸���� �F ����.�����㎸����
        l_ifoChangeOpenContractOrderSpec.setModifiedExpirationDate(l_datModifiedExpirationDate);

        //set����(����)
        //�㗝���͎� �F ����.����
        l_ifoChangeOpenContractOrderSpec.setTrader(l_trader);

        //set������P�������l(Double)
        l_ifoChangeOpenContractOrderSpec.setModifiedPriceAdjustValue(l_modifiedPriceAdjustValue);

        //get���񒍕��̒����P��ID(���������敪 : String)
        //���������敪 �F ����.���������敪
        Long l_firstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_strExpirationDateType);

        //set���񒍕��̒����P��ID(Long)
        //���񒍕��̒����P��ID �F �敨OP�f�[�^�A�_�v�^.get���񒍕��̒����P��ID()
        l_ifoChangeOpenContractOrderSpec.setFirstOrderUnitId(l_firstOrderUnitId);

        //OpLoginSecurityService���ҏW�������XID
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //�������擾����
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accountMananger.getMainAccount(l_opLoginSecurityService.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3ToSuccIfoChangeOpenContractOrderSpec." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���XID
        long l_lngBranchId = l_mainAccount.getBranch().getBranchId();

        //get�[��O�J�z�Ώۃt���O(���������敪 : String, ���XID : long)
        //���������敪 �F ����.���������敪
        //���XID�F�@@OpLoginSecurityService���ҏW�������XID
        boolean l_blnIsEveningSessionCarryOverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(l_strExpirationDateType, l_lngBranchId);

        //set�[��O�J�z�Ώۃt���O(boolean)
        //�[��O�J�z�Ώۃt���O �F �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O()
        l_ifoChangeOpenContractOrderSpec.setEveningSessionCarryOverFlag(l_blnIsEveningSessionCarryOverFlag);

        //set���������敪(String)
        //���������敪 �F ����.���������敪
        l_ifoChangeOpenContractOrderSpec.setExpirationDateType(l_strExpirationDateType);

        log.exiting(STR_METHOD_NAME);
        return l_ifoChangeOpenContractOrderSpec;
    }

    /**
     * (get������T�Z��n���)<BR>
     * ������T�Z��n������擾����B<BR>
     * @@return double
     */
    public double getModifiedEstimatedPrice()
    {
        return this.modifiedEstimatedPrice;
    }

    /**
     * (set������T�Z��n���)<BR>
     * ������T�Z��n������Z�b�g����B<BR>
     * @@param l_dblModifiedEstimatedPrice - (������T�Z��n���)<BR>
     * ������T�Z��n���<BR>
     */
    public void setModifiedEstimatedPrice(double l_dblModifiedEstimatedPrice)
    {
        this.modifiedEstimatedPrice = l_dblModifiedEstimatedPrice;
    }

    /**
     * (get������v�Z�P��)<BR>
     * ������v�Z�P�����擾����B<BR>
     * @@return double
     */
    public double getModifiedCalcUnitPrice()
    {
        return this.modifiedCalcUnitPrice;
    }

    /**
     * (set������v�Z�P��)<BR>
     * ������v�Z�P�����Z�b�g����B<BR>
     * @@param l_dblModifiedCalcUnitPrice - (������v�Z�P��)<BR>
     * ������v�Z�P��<BR>
     */
    public void setModifiedCalcUnitPrice(double l_dblModifiedCalcUnitPrice)
    {
        this.modifiedCalcUnitPrice = l_dblModifiedCalcUnitPrice;
    }

    /**
     * (get�����㒍��������)<BR>
     * �����㒍�����������擾����B<BR>
     * @@return Date
     */
    public Date getModifiedExpirationDate()
    {
        return this.modifiedExpirationDate;
    }

    /**
     * (set�����㒍��������)<BR>
     * �����㒍�����������Z�b�g����B<BR>
     * @@param l_datModifiedExpirationDate - (�����㒍��������)<BR>
     * �����㒍��������<BR>
     */
    public void setModifiedExpirationDate(Date l_datModifiedExpirationDate)
    {
        this.modifiedExpirationDate = l_datModifiedExpirationDate;
    }

    /**
     * (get����)<BR>
     * ���҂��擾����B<BR>
     * @@return WEB3GentradeTrader
     */
    public WEB3GentradeTrader getTrader()
    {
        return this.trader;
    }

    /**
     * (set����)<BR>
     * ���҂��Z�b�g����B<BR>
     * @@param l_trader - (����)<BR>
     * ����<BR>
     */
    public void setTrader(WEB3GentradeTrader l_trader)
    {
        this.trader = l_trader;
    }

    /**
     * (get������P�������l)<BR>
     * ������P�������l���擾����B<BR>
     * @@return Double
     */
    public Double getModifiedPriceAdjustValue()
    {
        return this.modifiedPriceAdjustValue;
    }

    /**
     * (set������P�������l)<BR>
     * ������P�������l���Z�b�g����B<BR>
     * @@param l_modifiedPriceAdjustValue - (������P�������l)<BR>
     * ������P�������l<BR>
     */
    public void setModifiedPriceAdjustValue(Double l_modifiedPriceAdjustValue)
    {
        this.modifiedPriceAdjustValue = l_modifiedPriceAdjustValue;
    }

    /**
     * (get���񒍕��̒����P��ID)<BR>
     * ���񒍕��̒����P��ID���擾����B<BR>
     * @@return Long
     */
    public Long getFirstOrderUnitId()
    {
        return this.firstOrderUnitId;
    }

    /**
     * (set���񒍕��̒����P��ID)<BR>
     * ���񒍕��̒����P��ID���Z�b�g����B<BR>
     * @@param l_firstOrderUnitId - (���񒍕��̒����P��ID)<BR>
     * ���񒍕��̒����P��ID<BR>
     */
    public void setFirstOrderUnitId(Long l_firstOrderUnitId)
    {
        this.firstOrderUnitId = l_firstOrderUnitId;
    }

    /**
     * (get�[��O�J�z�Ώۃt���O)<BR>
     * �[��O�J�z�Ώۃt���O���擾����B<BR>
     * @@return boolean
     */
    public boolean getEveningSessionCarryOverFlag()
    {
        return this.eveningSessionCarryOverFlag;
    }

    /**
     * (set�[��O�J�z�Ώۃt���O)<BR>
     * �[��O�J�z�Ώۃt���O���Z�b�g����B<BR>
     * @@param l_blnIsEveningSessionCarryOverFlag - (�[��O�J�z�Ώۃt���O)<BR>
     * �[��O�J�z�Ώۃt���O<BR>
     */
    public void setEveningSessionCarryOverFlag(boolean l_blnIsEveningSessionCarryOverFlag)
    {
        this.eveningSessionCarryOverFlag = l_blnIsEveningSessionCarryOverFlag;
    }

    /**
     * (get���������敪)<BR>
     * ���������敪���擾����B<BR>
     * @@return String
     */
    public String getExpirationDateType()
    {
        return this.expirationDateType;
    }

    /**
     * (set���������敪)<BR>
     * ���������敪���Z�b�g����B<BR>
     * @@param l_strExpirationDateType - (���������敪)<BR>
     * ���������敪<BR>
     */
    public void setExpirationDateType(String l_strExpirationDateType)
    {
        this.expirationDateType = l_strExpirationDateType;
    }
}
@