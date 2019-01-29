head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �敨OP�ۗL���ʏ��N���X(WEB3IfoContract.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/12 �R�c�@@��i(FLJ) �V�K�쐬
 Revision History : 2007/07/06 hijikata(SRA)  �[��Ή� ���f��No.061�B, No.068, No.071
 Revision History : 2007/08/02 hijikata(SRA)  �[��Ή� ���f��No.100, No.102
 Revision History : 2007/08/13 k.yamashita(SRA)  �[��Ή� U03048,U03049
 
 */
package webbroker3.ifodeposit;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;

/**
 * (�敨OP�ۗL���ʏ��)<BR>
 * �������E�������̌��ʂ̏���\���N���X�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoContract
{
    
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoContract.class);

    /**
     * (����ID)
     */
    public long contractId;

    /**
     * (����ID)
     */
    public long productId;

    /**
     * �s��ID�B
     */
    public long marketId;

    /**
     * (���敪)<BR>
     * 1�F����<BR>
     * 2�F����<BR>
     */
    public ContractTypeEnum contractType;

    /**
     * (����)
     */
    public Date openDate;

    /**
     * ���P���B
     */
    public double contractPrice;

    /**
     * ���ʐ��ʁB�i���A���c)<BR>
     */
    public double quantity = 0;

    /**
     * �敨OP�ۗL���ʎ�����薾�׃��X�g
     */
    public List ifoFinTransactionList;

    /**
     * ����敪
     */
    public String sessionType;

    /**
     * ���ʐ��ʁi�؋����s�����m��)
     */
    public double quantityTemp = 0;

    /**
     * @@roseuid 4158CAED0220
     */
    public WEB3IfoContract()
    {
        ifoFinTransactionList = new ArrayList();
    }

    //  Public methods ----------------------------------------------------------

    /**
     * (create�敨OP�ۗL���ʏ��)<BR>
     * 
     * �敨OP�ۗL���ʏ��𐶐�����B<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoContract
     * @@roseuid 4112EBD60187
     */
    public WEB3IfoContract create()
    {
        return new WEB3IfoContract();
    }

    /**
     * (add�敨OP�ۗL���ʎ�����薾��)<BR>
     * 
     * �敨OP�ۗL���ʎ�����薾�ׂ��쐬���Athis.�敨OP�ۗL���ʎ�����薾�׈ꗗ�ɒǉ�����<BR>
     * 
     * 
     * <BR>
     * 
     * �P�j�@@�I�u�W�F�N�g�̐���<BR>
     * �@@create�敨OP�ۗL���ʎ�����薾��( 
     * )�ɂ��敨OP�ۗL���ʏ��I�u�W�F�N�g�𐶐�����B<BR>
     * 
     * �Q�j�@@�v���p�e�B�̃Z�b�g<BR>
     * �@@���������敨OP�ۗL���ʎ�����薾�ׂɉ��L�v���p�e�B�Z�b�g���s���B<BR>
     * �@@
     * �@@�E�g�����U�N�V����ID�F�@@����.�g�����U�N�V����Params.�g�����U�N�V����ID<BR>
     * �@@�E�g�����U�N�V�����^�C�v�F�@@����.�g�����U�N�V����Params.�g�����U�N�V�����^�C�v<BR>
     * �@@�E�g�����U�N�V�����J�e�S���F�@@����.�g�����U�N�V����Params.�g�����U�N�V�����J�e�S��<BR>
     * �@@�E�����P��ID�F�@@����.�g�����U�N�V����Params.�����P��ID<BR>
     * �@@�E��n���F�@@�����g�����U�N�V����Params..��n��<BR>
     * �@@�E��n����F�@@����.�g�����U�N�V����Params.��n���<BR>
     * �@@�@@�@@
     * �R�j�@@�I�u�W�F�N�g�̒ǉ�<BR>
     * �@@this.�敨OP�ۗL���ʎ�����薾�׈ꗗ.add(
     *       �v���p�e�B�Z�b�g�����敨OP�ۗL���ʎ�����薾��)�ɂ��A
     *   �敨OP�ۗL���ʎ�����薾�ׂ�ǉ�����B<BR>
     * 
     * @@param l_transactionParams - (�g�����U�N�V����Params)
     * @@roseuid 41220927031C
     */
    public void addIfoFinTransaction(IfoFinTransactionParams l_transactionParams)
    {
        WEB3IfoFinTransaction l_transaction = WEB3IfoFinTransaction.create();
        l_transaction.setFinTransactionId(
            l_transactionParams.getFinTransactionId());
        l_transaction.setFinTransactionType(
            l_transactionParams.getFinTransactionType());
        l_transaction.setFinTransactionCateg(
            l_transactionParams.getFinTransactionCateg());
        l_transaction.setOrderUnitId(l_transactionParams.getOrderUnitId());
        l_transaction.setDeliveryDate(l_transactionParams.getDeliveryDate());
        l_transaction.setNetAmount(l_transactionParams.getNetAmount());
        ifoFinTransactionList.add(l_transaction);
        
        log.debug("Added IfoFinTransaction : " + l_transaction.toString());
        
    }

    /**
     * (get�敨OP�ۗL���ʏ��)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �����̌���Params���A�敨OP�ۗL���ʏ��I�u�W�F�N�g���쐬����B<BR>
     * 
     * �P�j�@@�I�u�W�F�N�g�̐���<BR>
     * �@@this.create�敨OP�ۗL���ʏ��( 
     * )�ɂ��敨OP�ۗL���ʏ��I�u�W�F�N�g�𐶐�����B<BR>
     * 
     * �Q�j�@@�v���p�e�B�̃Z�b�g<BR>
     * �@@���������敨OP�ۗL���ʏ��ɉ��L�v���p�e�B�Z�b�g���s���B<BR>
     * �@@
     * �@@�E����ID�F�@@����.����Params.����ID<BR>
     * �@@�E����ID�F�@@����.����Params.����ID<BR>
     * �@@�E�s��ID�F�@@����.����Params.�s��ID<BR>
     * �@@�E���敪�F�@@����.����Params.���敪<BR>
     * �@@�E�����F�@@����.����Params.����<BR>
     * �@@�E���P���F�@@����.����Params.���P��<BR>
     * �@@�E���ʐ��ʁF�@@����.����Params.���ʌ�����<BR>
     *   �E���ʐ��ʁ��؋����s�����m�聄�F�@@����.����Params.���ʌ�����(*) <BR>
     *   �E����敪�F�@@����.����Params.����敪<BR>
     *   �E�敨OP�ۗL���ʎ�����薾�׈ꗗ�F ArrayList��V���ɐ������ăZ�b�g<BR>
     * 
     *    (*)�������A����.������==����.����Params.�����@@���� 
     *       ����.����Params.����敪==�h�[��h�̏ꍇ�A 
     *       ZERO���Z�b�g�B 
     * 
     * �R�j�@@�v���p�e�B�Z�b�g�����敨OP�ۗL���ʏ���ԋp����B
     * �@@
     * 
     * @@param l_contractParams - ����Params�B
     * @@param l_datBizDate - �������B
     * @@return webbroker3.ifodeposit.WEB3IfoContract
     * @@roseuid 413ED3430205
     */
    public static WEB3IfoContract getIfoContract(IfoContractParams l_contractParams,
        Date l_datBizDate)
    {
        WEB3IfoContract l_contract = new WEB3IfoContract();
        l_contract.setContractId(l_contractParams.getContractId());
        l_contract.setProductId(l_contractParams.getProductId());
        l_contract.setMarketId(l_contractParams.getMarketId());
        l_contract.setContractType(l_contractParams.getContractType());
        l_contract.setOpenDate(l_contractParams.getOpenDate());
        l_contract.setContractPrice(l_contractParams.getContractPrice());
        l_contract.setQuantity(l_contractParams.getOriginalQuantity());

        String l_strSessionType = l_contractParams.getSessionType();
        // ���ʐ��ʁ��؋����s�����m�聄
        if (WEB3SessionTypeDef.EVENING_SESSION.equals(l_strSessionType) &&
                WEB3DateUtility.compareToDay(l_datBizDate, 
                    l_contractParams.getOpenDate()) == 0)
        {
            // ����.������==����.����Params.���� ���� ����.����Params.����敪==�h�[��h�̏ꍇ 
            // ZERO���Z�b�g�B
            l_contract.quantityTemp = 0;
        } else {   	
            l_contract.quantityTemp = l_contractParams.getOriginalQuantity();
        }

        l_contract.sessionType = l_strSessionType;
        return l_contract;
    }

    /**
     * (get�敨���ϑ��v[T+1])<BR>
     * 
     * ��n����T+1�̐敨���ϑ��v���擾����B<BR>
     * (��n����T+1�̐敨���ϑ��v�����݂��Ȃ��ꍇ�́A0)<BR>
     * 
     * �P�j�@@�߂�l�ƂȂ�敨���ϑ��v���i�[����ϐ����쐬<BR>
     * �@@�@@�@@double �敨���ϑ��v = 0<BR>
     * 
     * �Q�j�@@this.�敨OP�ۗL���ʎ�����薾�׈ꗗ�v�f���Ƃ�Loop����<BR>
     * 
     * �@@�@@�@@���L�����Ɉ�v����ꍇ�̂݁A�敨���ϑ��v�̉��Z���s���B<BR>
     * 
     * �@@�@@�@@�敨���ϑ��v = �敨���ϑ��v + �敨OP�ۗL���ʎ�����薾��.��n����@@�@@<BR>
     * 
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�敨OP�ۗL���ʎ�����薾��.is�敨 == true &&<BR>
     * �@@�@@�@@�@@�敨OP�ۗL���ʎ�����薾��.��n�� ==�@@����.��n��<BR>
     * 
     * �R�j�@@�敨���ϑ��v��ԋp����B<BR>
     * 
     * 
     * 
     * 
     * �@@
     * 
     * @@param l_datDeliveryDate - (��n��)<BR>
     * @@return double
     * @@roseuid 41249AF6006F
     */
    public double getNextBizDateFuturesCloseProfitLoss(Date l_datDeliveryDate)
    {
        
        double l_dblPL = 0.0;
        for (Iterator l_it = ifoFinTransactionList.iterator(); l_it.hasNext();)
        {
            WEB3IfoFinTransaction l_tran = (WEB3IfoFinTransaction) l_it.next();
            if (l_tran.isFutures()
                && l_datDeliveryDate.getTime()
                    == l_tran.getDeliveryDate().getTime())
            {
                l_dblPL += l_tran.getNetAmount();
            }
        }

        log.debug(
            "Calculated Futures P/L="
                + l_dblPL
                + ",DeliveryDate = "
                + l_datDeliveryDate
                + ","
                + this.toString());
        
        return l_dblPL;
    }


    /**
     * (get�敨���ϑ��v[T+2])<BR>
     * 
     * ��n����T+2�̐敨���ϑ��v���擾����B<BR>
     * (��n����T+2�̐敨���ϑ��v�����݂��Ȃ��ꍇ�́A0)<BR>
     * 
     * �P�j�@@�߂�l�ƂȂ�敨���ϑ��v���i�[����ϐ����쐬<BR>
     * �@@�@@�@@double �敨���ϑ��v = 0<BR>
     * 
     * �Q�j�@@this.�敨OP�ۗL���ʎ�����薾�׈ꗗ�v�f���Ƃ�Loop����<BR>
     * 
     * �@@�@@�@@���L�����Ɉ�v����ꍇ�̂݁A�敨���ϑ��v�̉��Z���s���B<BR>
     * 
     * �@@�@@�@@�敨���ϑ��v = �敨���ϑ��v + �敨OP�ۗL���ʎ�����薾��.��n����@@�@@<BR>
     * 
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�敨OP�ۗL���ʎ�����薾��.is�敨 == true &&<BR>
     * �@@�@@�@@�@@�敨OP�ۗL���ʎ�����薾��.��n�� ==�@@����.��n��<BR>
     * 
     * �R�j�@@�敨���ϑ��v��ԋp����B<BR>
     * 
     * @@param l_datDeliveryDate - (��n��)<BR>
     * @@return double
     */
    public double getNext2BizDateFuturesCloseProfitLoss(Date l_datDeliveryDate)
    {
        
        double l_dblNext2PL = 0.0;
        for (Iterator l_it = ifoFinTransactionList.iterator(); l_it.hasNext();)
        {
            WEB3IfoFinTransaction l_tran = (WEB3IfoFinTransaction) l_it.next();
            if (l_tran.isFutures()
                && l_datDeliveryDate.getTime()
                    == l_tran.getDeliveryDate().getTime())
            {
                l_dblNext2PL += l_tran.getNetAmount();
            }
        }

        log.debug(
            "Calculated Futures P/L="
                + l_dblNext2PL
                + ",DeliveryDate = "
                + l_datDeliveryDate
                + ","
                + this.toString());
        
        return l_dblNext2PL;
    }

    /**
     * (get�I�v�V������n���[T+1])<BR>
     * 
     * ��n����T+1�̃I�v�V������n�����ԋp����B<BR>
     * (��n����T+1�̃I�v�V������n��������݂��Ȃ��ꍇ�́A0)<BR>
     * 
     * �P�j�@@�߂�l�ƂȂ�I�v�V������n������i�[����ϐ����쐬<BR>
     * �@@�@@�@@double �I�v�V������n��� = 0<BR>
     * 
     * �Q�j�@@this.�敨OP�ۗL���ʎ�����薾�׈ꗗ�v�f���Ƃ�Loop����<BR>
     * 
     * �@@�@@�@@���L�����Ɉ�v����ꍇ�̂݁A�I�v�V������n����̉��Z���s���B<BR>
     * 
     * �@@�@@�@@�I�v�V������n��� = �I�v�V������n��� + 
     * �敨OP�ۗL���ʎ�����薾��.��n����@@�@@<BR>
     * 
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�敨OP�ۗL���ʎ�����薾��.is�敨 == false &&<BR>
     * �@@�@@�@@�@@�敨OP�ۗL���ʎ�����薾��.��n�� ==�@@����.��n��<BR>
     * 
     * �R�j�@@�I�v�V������n�����ԋp����B<BR>
     * 
     * 
     * 
     * 
     * �@@
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     * @@roseuid 4124A31A0210
     */
    public double getNextBizDateOptionNetAmount(Date l_datDeliveryDate)
    {
        double l_dblNetAmount = 0.0;
        for (Iterator l_it = ifoFinTransactionList.iterator(); l_it.hasNext();)
        {
            WEB3IfoFinTransaction l_tran = (WEB3IfoFinTransaction) l_it.next();
            if (!l_tran.isFutures()
                && (l_tran.getDeliveryDate().getTime()
                    == l_datDeliveryDate.getTime()))
            {
                l_dblNetAmount += l_tran.getNetAmount();
            }
        }

        log.debug(
            "Calculated Options NetAmount="
                + l_dblNetAmount
                + ",DeliveryDate = "
                + l_datDeliveryDate
                + ","
                + this.toString());
        
        return l_dblNetAmount;
    }


    /**
     * (get�I�v�V������n���[T+2])<BR>
     * 
     * ��n����T+2�̃I�v�V������n�����ԋp����B<BR>
     * (��n����T+2�̃I�v�V������n��������݂��Ȃ��ꍇ�́A0)<BR>
     * 
     * �P�j�@@�߂�l�ƂȂ�I�v�V������n������i�[����ϐ����쐬<BR>
     * �@@�@@�@@double �I�v�V������n��� = 0<BR>
     * 
     * �Q�j�@@this.�敨OP�ۗL���ʎ�����薾�׈ꗗ�v�f���Ƃ�Loop����<BR>
     * 
     * �@@�@@�@@���L�����Ɉ�v����ꍇ�̂݁A�I�v�V������n����̉��Z���s���B<BR>
     * 
     * �@@�@@�@@�I�v�V������n��� = �I�v�V������n��� + 
     * �敨OP�ۗL���ʎ�����薾��.��n����@@�@@<BR>
     * 
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�敨OP�ۗL���ʎ�����薾��.is�敨 == false &&<BR>
     * �@@�@@�@@�@@�敨OP�ۗL���ʎ�����薾��.��n�� ==�@@����.��n��<BR>
     * 
     * �R�j�@@�I�v�V������n�����ԋp����B<BR>
     * 
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     */
    public double getNext2BizDateOptionNetAmount(Date l_datDeliveryDate)
    {
        double l_dblNext2NetAmount = 0.0;
        for (Iterator l_it = ifoFinTransactionList.iterator(); l_it.hasNext();)
        {
            WEB3IfoFinTransaction l_tran = (WEB3IfoFinTransaction) l_it.next();
            if (!l_tran.isFutures()
                && (l_tran.getDeliveryDate().getTime()
                    == l_datDeliveryDate.getTime()))
            {
                l_dblNext2NetAmount += l_tran.getNetAmount();
            }
        }

        log.debug(
            "Calculated Options NetAmount="
                + l_dblNext2NetAmount
                + ",DeliveryDate = "
                + l_datDeliveryDate
                + ","
                + this.toString());
        
        return l_dblNext2NetAmount;
    }

    /**
     * (get��n���)<BR>
     * 
     * �����ɑΉ������n�����ԋp����B<BR>
     * (�����ɑΉ������n��������݂��Ȃ��ꍇ�́A0)<BR>
     * 
     * �P�j�@@this.���� != ����.�����̏ꍇ�́A0��ԋp���A�I������B<BR>
     * 
     * �Q�j�@@�߂�l�ƂȂ��n������i�[����ϐ����쐬<BR>
     * �@@�@@�@@double ��n��� = 0<BR>
     * 
     * �R�j�@@this.�敨OP�ۗL���ʎ�����薾�׈ꗗ�v�f���Ƃ�Loop����<BR>
     * 
     * �@@�@@�@@���L�����Ɉ�v����ꍇ�̂݁A��n����̉��Z���s���B<BR>
     * 
     * �@@�@@�@@��n��� = ��n��� + �敨OP�ۗL���ʎ�����薾��.��n���<BR>�@@�@@
     * 
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�敨OP�ۗL���ʎ�����薾��.�����P��ID == ����.�����P��ID<BR>
     * 
     * �S�j�@@��n�����ԋp����B<BR>
     * �@@
     * @@param l_lngOrderUnitId - �����P��ID�B
     * @@param l_openDate - (����)<BR>
     * @@return double
     * @@roseuid 4129C61F0176
     */
    public double getNetAmount(long l_lngOrderUnitId, Date l_datOpenDate)
    {
        double l_dblNetAmount = 0.0;
        if (WEB3DateUtility.compareToDay(getOpenDate(), l_datOpenDate) != 0)
        {

        } else
        {
            for (Iterator l_it = ifoFinTransactionList.iterator();
                l_it.hasNext();
                )
            {
                WEB3IfoFinTransaction l_tran =
                    (WEB3IfoFinTransaction) l_it.next();
                if (l_tran.getOrderUnitId() == l_lngOrderUnitId)
                {
                    l_dblNetAmount += l_tran.getNetAmount();
                }
            }
        }

        log.debug(
            "Calculated NetAmount="
                + l_dblNetAmount
                + ",OrderUnitId = "
                + l_lngOrderUnitId
                + ",OpenDate="
                + l_datOpenDate
                + ","
                + this.toString());

        return l_dblNetAmount;

    }

    /**
     * (subtract���ʐ���)<BR>
     * 
     * �Ethis.���ʐ��ʂ���ԍό��ʐ��ʂ����Z����B <BR>
     * �Ethis.���ʐ��ʁ��؋����s�����m�聄���� �ԍό��ʐ��ʁ������[��ȊO�������Z���� <BR>
     * 
     * �@@�@@�����ʐ��ʂɂ͗\�ߌ��ʂ̌����ʐ��ʂ��ݒ肳��Ă���B <BR>
     *
     * �P�j�@@�ԍώ���ł��邩�̔���<BR>
     * �@@    ����.�g�����U�N�V�����J�e�S�� == <BR>
     *       (�h�敨�ԍώ���h�A�܂��́A�hOP�ԍώ���h�j�̏ꍇ�̂݁A<BR>
     *       �Q�j�A�R�j�̐��ʂ̌��Z���s���B <BR>
     * 
     * �Q�j�@@���ʂ̌��Z<BR>
     *       this.���ʐ��� = this.���ʐ��� - ����.����<BR>
     * 
     * �R�j�@@�؋����s�����m��p�̐��ʂ̌��Z 
     * �R�|�P�j�ȉ��̏ꍇ�A���Z���s�Ȃ킸�Areturn�B 
     *         ����.����敪==�h�[��h 
     *          �@@�i�[��ԍϖ��͏؋����s�����m��̌v�Z�ɂ͊܂߂Ȃ����߁j 
     * 
     * �R�|�Q�j�R�|�P�j�ȊO�͌��Z���s�Ȃ��B 
     *         this.���ʐ��ʁ��؋����s�����m�聄 = this.���ʐ��ʁ��؋����s�����m�聄 - ����.���� 
     * 
     * 
     * @@param l_transactionCateg - (�g�����U�N�V�����J�e�S��)
     * @@param l_dblQuantity - (����)<BR>
     * @@param l_strSessionType - (����敪)<BR>
     * @@roseuid 4121FDAA0085
     */
    public void subtractQuantity(
        FinTransactionCateg l_transactionCateg,
        double l_dblQuantity,
        String l_strSessionType)
    {
        if (FinTransactionCateg
            .EQTYPE_IDX_FUTURES_CLOSE
            .equals(l_transactionCateg)
            || FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE.equals(
                l_transactionCateg))
        {
            setQuantity(getQuantity() - l_dblQuantity);

            //�؋����s�����m��p�̐��ʂ̌��Z 
            if (WEB3SessionTypeDef.EVENING_SESSION.equals(l_strSessionType))
            {
                //����.����敪==�h�[��h�̏ꍇ�A
                //���Z���s�Ȃ킸�Areturn�B 
            } else {
                //this.���ʐ��ʁ��؋����s�����m�聄 = this.���ʐ��ʁ��؋����s�����m�聄 - ����.����
                this.quantityTemp = this.quantityTemp - l_dblQuantity;    
            }

        }
    }

    /**
     * (is���ύ�)<BR>
     * 
     * ���Y���ʂ����ύςł��邩�𔻒肷��B<BR>
     * 
     * this.���ʐ���==0�̏ꍇ�Atrue��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 411375010214
     */
    public boolean isSettled()
    {
        if (getQuantity() == 0)
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * (is����)<BR>
     * 
     * �Y�����ʂ������ł��邩�𔻒肷��B<BR>
     * 
     * this.���敪==�h�����h�̏ꍇ�Atrue��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 411708CE02E8
     */
    public boolean isBuy()
    {
        if (ContractTypeEnum.LONG.equals(getContractType()))
        {
            return true;
        } else
        {
            return false;
        }
    }
    
    /**
     * (is������)<BR>
     *  
     * ���Y���ʂ��������ł��邩�𔻒肷��B 
     * 
     * �P�j �c�Ɠ�[T-1]�A�c�Ɠ�[T+0]���擾����B 
     * �i���ꂼ��A�ϐ��F�c�Ɠ��iT-1�j�A�c�Ɠ��iT+0�j�Ɋi�[�j 
     * 
     * �E�c�Ɠ��iT-1�j = ����.�؋����v�Z����.get�c�Ɠ�(-1) 
     * �E�c�Ɠ��iT+0�j = ����.�؋����v�Z����.get�c�Ɠ�(0) 
     * 
     * �Q�j�؋����v�Z������擾����B 
     * 
     *  �E�؋����v�Z��� = ����.�؋����v�Z����.get�؋����v�Z���() 
     * 
     * �R�j �؋����v�Z��� == 1:�����������ԑ� �̏ꍇ 
     * 
     * �R�j-1 A or B�̏ꍇ�Atrue��Ԃ� 
     * A. this.����==�c�Ɠ��iT-1�j ���� this.����敪=�[�� 
     * B. this.����==�c�Ɠ��iT+0�j 
     * 
     * �R�j-�Q �R�j-1�ȊO�̏ꍇ,false��Ԃ��B 
     * 
     * �S�j �؋����v�Z��� == 2:�����������ԑ� �̏ꍇ 
     * 
     * �S�j-�P �ȉ��̏ꍇ�Atrue��Ԃ� 
     * �E this.����==�c�Ɠ��iT+0�j ���� this.����敪=�[�� 
     * 
     * �S�j-�Q �S�j-�P�ȊO�̏ꍇ,false��Ԃ��B 
     * 
     *  
     * @@param l_ifoDepositCalcCondition - (�؋����v�Z����)<BR>
     * 
     * @@return boolean
     * @@roseuid 411708CE02E8
     */
    public boolean isTodayContract(WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition)
    {
        // �P�j get�c�Ɠ�[T-1]�Aget�c�Ɠ�[T+0]���擾����B
        Date l_datPreBizDate = l_ifoDepositCalcCondition.getBizDate(-1); 
        Date l_datBizDate    = l_ifoDepositCalcCondition.getBizDate(0);
        
        // �Q�j�؋����v�Z������擾����B
        int l_intIfoDepositCalcBaseDate = l_ifoDepositCalcCondition.getIfoDepositBaseDate();

        // �R�j �؋����v�Z��� == 1:�����������ԑ� �̏ꍇ 		
        if(l_intIfoDepositCalcBaseDate == WEB3IfoReservedDateDef.T_1)
        {
            //�R�j-1 A or B�̏ꍇ�Atrue��Ԃ� 
            //  A. this.����==�c�Ɠ��iT-1�j ���� this.����敪=�[�� 
            //  B. this.����==�c�Ɠ��iT+0�j 
            //�R�j-�Q �R�j-1�ȊO�̏ꍇ,false��Ԃ��B
            if ( ((WEB3DateUtility.compareToDay(this.openDate, l_datPreBizDate) == 0) && 
                     (WEB3SessionTypeDef.EVENING_SESSION.equals(this.sessionType))) ||
                  (WEB3DateUtility.compareToDay(this.openDate, l_datBizDate) == 0)  )
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        // �S�j �؋����v�Z��� == 2:�����������ԑ� �̏ꍇ 
        else if(l_intIfoDepositCalcBaseDate == WEB3IfoReservedDateDef.T_2)
        {
            // �S�j-�P �ȉ��̏ꍇ�Atrue��Ԃ� 
            // �E this.����==�c�Ɠ��iT+0�j ���� this.����敪=�[��
            if ( (WEB3DateUtility.compareToDay(this.openDate, l_datBizDate) == 0) &&
                 (WEB3SessionTypeDef.EVENING_SESSION.equals(this.sessionType)) ) 
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        else
        {
        	return false;
        }		
    }
    /**
     * ����ID���擾����B
     * 
     * @@return�@@����ID
     */
    public long getContractId()
    {
        return contractId;
    }

    /**
     * ���P�����擾����B
     * 
     * @@return�@@���P��
     */
    public double getContractPrice()
    {
        return contractPrice;
    }

    /**
     * ���敪���擾����B
     * 
     * @@return�@@���敪
     */
    public ContractTypeEnum getContractType()
    {
        return contractType;
    }

    /**
     * �s��ID���擾����B
     * 
     * @@return�@@�s��ID
     */
    public long getMarketId()
    {
        return marketId;
    }

    /**
     * �������擾����B
     * 
     * @@return�@@����
     */
    public Date getOpenDate()
    {
        return openDate;
    }

    /**
     * ����ID���擾����B
     * 
     * @@return�@@����ID
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * ���ʂ��擾����B
     * 
     * @@return�@@����
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * ����ID��ݒ肷��B
     * 
     * @@param l_lngContractId�@@����ID
     */
    public void setContractId(long l_lngContractId)
    {
        this.contractId = l_lngContractId;
    }

    /**
     * ���P����ݒ肷��B
     * 
     * @@param l_dblContractPrice�@@���P��
     */
    public void setContractPrice(double l_dblContractPrice)
    {
        this.contractPrice = l_dblContractPrice;
    }

    /**
     * ���敪��ݒ肷��B
     * 
     * @@param l_contractType�@@���敪
     */
    public void setContractType(ContractTypeEnum l_contractType)
    {
        this.contractType = l_contractType;
    }

    /**
     * �s��ID��ݒ肷��B
     * 
     * @@param l_lngMarketId�@@�s��ID
     */
    public void setMarketId(long l_lngMarketId)
    {
        this.marketId = l_lngMarketId;
    }

    /**
     * ������ݒ肷��B
     * 
     * @@param l_datOpenDate�@@����
     */
    public void setOpenDate(Date l_datOpenDate)
    {
        this.openDate = l_datOpenDate;
    }

    /**
     * ����ID��ݒ肷��B
     * 
     * @@param l_lngProductId�@@����ID
     */
    public void setProductId(long l_lngProductId)
    {
        this.productId = l_lngProductId;
    }

    /**
     * ���ʂ�ݒ肷��B
     * 
     * @@param l_dblQuantity�@@����
     */
    public void setQuantity(double l_dblQuantity)
    {
        this.quantity = l_dblQuantity;
    }
    
    /**
     * ���̃I�u�W�F�N�g�̕�����\�����擾����B
     * 
     * @@return ���̃I�u�W�F�N�g�̕�����\��
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoContract={");
        l_sb.append("contractId=").append(getContractId());
        l_sb.append(",productId=").append(getProductId());
        l_sb.append(",marketId=").append(getMarketId());
        l_sb.append(",contractType=").append(getContractType());
        l_sb.append(",openDate=").append(getOpenDate());
        l_sb.append(",contractPrice=").append(getContractPrice());
        l_sb.append(",quantity=").append(getQuantity());
        l_sb.append(",sessionType=").append(this.sessionType);
        l_sb.append(",quantityTemp=").append(this.quantityTemp);
        l_sb.append(",ifoFinTransactionList={");
        int i = 0;
        for (Iterator l_it = ifoFinTransactionList.iterator(); l_it.hasNext();)
        {
            if (i > 0)
            {
                l_sb.append(",");
            }
            l_sb.append(l_it.next().toString());
            i++;
        }
        l_sb.append("}");
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
