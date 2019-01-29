head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoFinTransaction.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �敨OP�ۗL���ʎ�����薾�׃N���X(WEB3IfoFinTransaction.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/12 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.ifodeposit;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;

/**
 * (�敨OP�ۗL���ʎ�����薾��)<BR>
 * ���ʂ��Ƃ̎�����薾�ׂ�\���N���X�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoFinTransaction
{

    /**
     * (�g�����U�N�V����ID)
     */
    public long finTransactionId;

    /**
     * (�g�����U�N�V�����^�C�v)
     */
    public FinTransactionType finTransactionType;

    /**
     * �g�����U�N�V�����J�e�S���B
     */
    public FinTransactionCateg finTransactionCateg;

    /**
     * (�����P��ID)
     */
    public long orderUnitId;

    /**
     * (��n��)
     */
    public Date deliveryDate;

    /**
     * (��n���)<BR>
     * ���I�v�V���������i�V�K�����A�������ԍ�)�̏ꍇ�̓}�C�i�X�l���Z�b�g�����B<BR>
     */
    public double netAmount = 0;

    /**
     * (The�敨OP�ۗL���ʏ��j<BR>
     */
    public WEB3IfoContract web3IfoContract;

    /**
     * @@roseuid 416120D8000D
     */
    public WEB3IfoFinTransaction()
    {
    }

    /**
     * (create�敨OP�ۗL���ʎ�����薾��)<BR>
     * 
     * �敨OP�ۗL���ʎ�����薾�ׂ𐶐�����B<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoFinTransaction
     * @@roseuid 4121CBC402A7
     */
    public static WEB3IfoFinTransaction create()
    {
        return new WEB3IfoFinTransaction();
    }

    /**
     * (is�敨 )<BR>
     * 
     * �Y��������敨�ł��邩�𔻒肷��B<BR>
     * this.�g�����U�N�V�����J�e�S��== 
     * �i�h�敨�V�K����h�A�܂��́A�h�敨�ԍώ���h�j�̏ꍇ�A<BR>
     * true��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4160E3D70174
     */
    public boolean isFutures()
    {
        switch (finTransactionCateg.intValue())
        {
            case (FinTransactionCateg.IntValues.EQTYPE_IDX_FUTURES_OPEN) :
            case (FinTransactionCateg.IntValues.EQTYPE_IDX_FUTURES_CLOSE) :
                return true;
            default :
                return false;
        }
    }
    /**
     * ��n�����擾����B
     * 
     * @@return�@@��n��
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * �g�����U�N�V�����J�e�S�����擾����B
     * 
     * @@return�@@�g�����U�N�V�����J�e�S��
     */
    public FinTransactionCateg getFinTransactionCateg()
    {
        return finTransactionCateg;
    }

    /**
     * �g�����U�N�V����ID���擾����B
     * 
     * @@return�@@�g�����U�N�V����ID
     */
    public long getFinTransactionId()
    {
        return finTransactionId;
    }

    /**
     * �g�����U�N�V�����^�C�v���擾����B
     * 
     * @@return�@@�g�����U�N�V�����^�C�v
     */
    public FinTransactionType getFinTransactionType()
    {
        return finTransactionType;
    }

    /**
     * ��n������擾����B
     * 
     * @@return�@@��n���
     */
    public double getNetAmount()
    {
        return netAmount;
    }

    /**
     * �����P��ID���擾����B
     * 
     * @@return�@@�����P��ID
     */
    public long getOrderUnitId()
    {
        return orderUnitId;
    }

    /**
     * �敨OP�ۗL���ʏ����擾����B
     * 
     * @@return�@@�敨OP�ۗL���ʏ��
     */
    public WEB3IfoContract getWEB3IfoContract()
    {
        return web3IfoContract;
    }

    /**
     * ��n����ݒ肷��B
     * 
     * @@param l_datDeliveryDate�@@��n��
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * �g�����U�N�V�����J�e�S����ݒ肷��B
     * 
     * @@param l_finTransactionCateg�@@�g�����U�N�V�����J�e�S��
     */
    public void setFinTransactionCateg(FinTransactionCateg l_finTransactionCateg)
    {
        finTransactionCateg = l_finTransactionCateg;
    }

    /**
     * �g�����U�N�V����ID��ݒ肷��B
     * 
     * @@param l_finTransactionId�@@�g�����U�N�V����ID
     */
    public void setFinTransactionId(long l_finTransactionId)
    {
        finTransactionId = l_finTransactionId;
    }

    /**
     * �g�����U�N�V�����^�C�v��ݒ肷��B
     * 
     * @@param l_finTransactionType�@@�g�����U�N�V�����^�C�v
     */
    public void setFinTransactionType(FinTransactionType l_finTransactionType)
    {
        finTransactionType = l_finTransactionType;
    }

    /**
     * ��n�����ݒ肷��B
     * 
     * @@param l_dblNetAmount�@@��n���
     */
    public void setNetAmount(double l_dblNetAmount)
    {
        netAmount = l_dblNetAmount;
    }

    /**
     * �����P��ID��ݒ肷��B
     * 
     * @@param l_lngOrderUnitId�@@�����P��ID
     */
    public void setOrderUnitId(long l_lngOrderUnitId)
    {
        orderUnitId = l_lngOrderUnitId;
    }

    /**
     * �敨OP�ۗL���ʏ���ݒ肷��B
     * 
     * @@param l_web3IfoContract�@@�敨OP�ۗL���ʏ��
     */
    public void setWEB3IfoContract(WEB3IfoContract l_web3IfoContract)
    {
        web3IfoContract = l_web3IfoContract;
    }
    
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoFinTransaction={");
        l_sb.append("finTransactionId=").append(getFinTransactionId());
        l_sb.append(",finTransactionType=").append(getFinTransactionType());
        l_sb.append(",finTransactionCateg=").append(getFinTransactionCateg());
        l_sb.append(",orderUnitId=").append(getOrderUnitId());
        l_sb.append(",deliveryDate=").append(getDeliveryDate());
        l_sb.append(",netAmount=").append(getNetAmount());
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
