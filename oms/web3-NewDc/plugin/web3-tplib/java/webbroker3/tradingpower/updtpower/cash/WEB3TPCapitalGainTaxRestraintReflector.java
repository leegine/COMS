head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCapitalGainTaxRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 �x�� �a��(FLJ) �V�K�쐬
*/

package webbroker3.tradingpower.updtpower.cash;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���n�v�ōS����)<BR>
 * ���n�v�ōS������\������B<BR>
 */
public class WEB3TPCapitalGainTaxRestraintReflector
    extends WEB3TPRestraintReflector
{

    /** ���O�@@���[�e�B���e�B�@@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPCapitalGainTaxRestraintReflector.class);

    /**
     * (���n�v�ŗ�)<BR>
     */
    private double taxRate;

    /**
     * (��n��)<BR>
     */
    private Date deliveryDate;

    /**
     * (���n���v<����>)<BR>
     */
    private webbroker3.tradingpower.updtpower.cash.WEB3TPCapitalGain[] capitalGain;

    /**
     * (�ݐϏ��n���v<�m��>)<BR>
     */
    private WEB3TPAccumulatedCapitalGain accumuratedCapitalGain;

    /**
     * @@roseuid 41049159030B
     */
    public WEB3TPCapitalGainTaxRestraintReflector()
    {

    }

    /**
     * (create���n�v�ōS����)<BR>
     * ���n�v�ōS�����𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * <BR>
     * �Q�j�@@�l��ݒ�<BR>
     * 	���n�v�ŗ������n�v�ŗ�<BR>
     * 	��n������n��<BR>
     * 	�ݐϏ��n���v���ݐϏ��n���v<BR>
     * 	���n���v�����n���v<BR>
     * 	�S������calc���n���v��()<BR>
     * 	�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f���i��n���j<BR>
     * <BR>
     * �R�j�@@�C���X�^���X��ԋp<BR>
     * <BR>
     * @@param l_dblTaxRate - (���n�v�ŗ�)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_accumuratedCapitalGain - (�ݐϏ��n��)
     * @@param l_capitalGain - (���n���v)
     * @@return WEB3TPCapitalGainTaxRestraintReflector
     * @@roseuid 40DABDF8004C
     */
    public static WEB3TPCapitalGainTaxRestraintReflector create(WEB3TPCalcCondition
        l_calcCondition, double l_dblTaxRate, Date l_datDeliveryDate,
        WEB3TPAccumulatedCapitalGain l_accumuratedCapitalGain,
        WEB3TPCapitalGain[] l_capitalGain)
    {
        WEB3TPCapitalGainTaxRestraintReflector l_instance = new
            WEB3TPCapitalGainTaxRestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setTaxRate(l_dblTaxRate);
        l_instance.setDeliveryDate(l_datDeliveryDate);
        l_instance.setAccumuratedCapitalGain(l_accumuratedCapitalGain);
        l_instance.setCapitalGain(l_capitalGain);
        l_instance.setAmount(l_instance.calcCapitalGainTax(l_datDeliveryDate));
        l_instance.calcReflectDay(l_datDeliveryDate);

        return l_instance;

    }

    /**
     * (get���n�v�ŗ�)<BR>
     * ���n�v�ŗ���Ԃ��B<BR>
     * @@return double
     * @@roseuid 40EE3525027A
     */
    public double getTaxRate()
    {
        return taxRate;
    }

    /**
     * (set���n�v�ŗ�)<BR>
     * ���������n�v�ŗ��ɃZ�b�g����B<BR>
     * @@param l_taxRate - (���n�v�ŗ�)
     * @@roseuid 40EE353200C4
     */
    public void setTaxRate(double l_dblTaxRate)
    {
        taxRate = l_dblTaxRate;
    }

    /**
     * (get��n��)<BR>
     * ��n����Ԃ��B<BR>
     * @@return Date
     * @@roseuid 40EE7FA703DA
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * (set��n��)<BR>
     * ��������n���ɃZ�b�g����B<BR>
     * @@param l_datDeliveryDate - (��n��)
     * @@roseuid 40EE7F9F011A
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * (get�ݐϏ��n���v)<BR>
     * �ݐϏ��n���v��Ԃ��B<BR>
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPAccumulatedCapitalGain
     * @@roseuid 40EE8185013A
     */
    public WEB3TPAccumulatedCapitalGain getAccumuratedCapitalGain()
    {
        return accumuratedCapitalGain;
    }

    /**
     * (set�ݐϏ��n���v)<BR>
     * ������ݐϏ��n���v�ɃZ�b�g����B<BR>
     * @@param l_accumuratedCapitalGain - (�ݐϏ��n���v)
     * @@roseuid 40EE8AE9037C
     */
    public void setAccumuratedCapitalGain(WEB3TPAccumulatedCapitalGain
                                          l_accumuratedCapitalGain)
    {
        accumuratedCapitalGain = l_accumuratedCapitalGain;
    }

    /**
     * (get���n���v)<BR>
     * ���n���v�̔z�񒆁A<BR>
     * ���n���v.get��n��()������.��n����<BR>
     * ���n���v��Ԃ��B<BR>
     * 
     * @@param l_datDeliveryDate (��n��)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPCapitalGain
     * @@throws WEB3BaseRuntimeException
     * @@roseuid 40EEA216032E
     */
    public WEB3TPCapitalGain getCapitalGain(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "getCapitalGain(Date l_datDeliveryDate)";
        
        for (int i = 0; i < capitalGain.length; i++)
        {
            if (WEB3DateUtility.compareToDay(capitalGain[i].getDeliveryDate(), l_datDeliveryDate) == 0)
            {
                return capitalGain[i];
            }
        }
        
        throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);

    }

    /**
     * (get���n���v)<BR>
     * ���n���v�̔z���ԋp����B<BR>
     * @@return WEB3TPCapitalGain[]
     */
    public WEB3TPCapitalGain[] getCapitalGain()
    {
        return capitalGain;
    }

    /**
     * (set���n���v)<BR>
     * ���������n���v�ɃZ�b�g����B<BR>
     * @@param l_capitalGain - (���n���v)
     * @@roseuid 40EE89D902EF
     */
    public void setCapitalGain(WEB3TPCapitalGain[] l_capitalGain)
    {
        capitalGain = l_capitalGain;
    }

    /**
     * (calc���n�v��)<BR>
     * �w���(n)�ɂ��Y������v�Z���W�b�N�Ōv�Z����B<BR>
     * <BR>  
     * 
     * �P�j�@@�c�Ɠ�(T+0)����c�Ɠ� (T+5)�܂œ��N�̏ꍇ<BR>
     * <BR>
     * 	���n�v��(T+3)  =  Max((���n���v(T+3) - �������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * 	���n�v��(T+4)  =  Max((���n���v(T+3)  +  ���n���v(T+4) -�@@�������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * 	���n�v��(T+5)  =  Max((���n���v(T+3)  +  ���n���v(T+4)  + ���n���v(T+5) - �������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * <BR>
     * �Q�j�@@�c�Ɠ�(T+3)�ȑO�ŗ��N�ɂȂ�ꍇ<BR>
     * <BR>
     * 	���n�v��(T+3)  =�@@Max((���n���v(T+3) - �������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * 	���n�v��(T+4)  =�@@Max((���n���v(T+3) + ���n���v(T+4) - �������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * 	���n�v��(T+5)  =  Max((���n���v(T+3) + ���n���v(T+4) + ���n���v(T+5) - �������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * <BR>
     * �R�j�@@�c�Ɠ�(T+4)�ŗ��N�ɂȂ�ꍇ<BR>
     * <BR>
     * 	���n�v��(T+3)  =  Max((���n���v(T+3) - �������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * 	���n�v��(T+4)  =  Max((���n���v(T+4) - �������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * 	���n�v��(T+5)  =  Max((���n���v(T+4)  +  ���n���v(T+5) - �������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * <BR>
     * �S�j�@@�c�Ɠ�(T+5)�ŗ��N�ɂȂ�ꍇ<BR>
     * <BR>
     * 	���n�v��(T+3)  =  Max((���n���v(T+3) - �������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * 	���n�v��(T+4)  =  Max((���n���v(T+3) + ���n���v(T+4) - �������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * 	���n�v��(T+5)  =  Max((���n���v(T+5) - �������n��), 0)<BR>
     * 	 �~�@@���n�v�ŗ�<BR>
     * <BR>
     * �T�j�@@��L�̂悤�ɋ��߂�����n�v��(n)��ԋp����B<BR>
     * <BR>  
     *     ���e�l�̎擾���@@<BR>
     * �E	���n���v(T+3)�E�E�Ethis.get���n���v(��n��<T+3>)<BR>
     * �E	���n���v(T+4)�E�E�Ethis.get���n���v(��n��<T+4>)<BR>
     * �E	���n���v(T+5)�E�E�Ethis.get���n���v(��n��<T+5>)<BR>
     * �E	�������n���E�E�EMin(this.get�ݐϏ��n���v().get�������n���v(), 0)<BR>
     * �E	�������n���E�E�EMin(this.get�ݐϏ��n���v().get�������n���v(), 0)<BR>
     * <BR>
     * 
     * @@param l_datDate (�w���)
     * @@return double
     * @@roseuid 40EE3F6C0076
     */
    public double calcCapitalGainTax(Date l_datDate)
    {
        Date l_datT0 = getCalcCondition().getBizDate(0);
        Date l_datT2 = getCalcCondition().getBizDate(2);
        Date l_datT3 = getCalcCondition().getBizDate(3);
        Date l_datT4 = getCalcCondition().getBizDate(4);
        Date l_datT5 = getCalcCondition().getBizDate(5);

        double l_capitalGain = 0.0d;        
        
        //�c�Ɠ�(T+0)����c�Ɠ�(T+5)�܂œ��N�̏ꍇ
        if (compareToYear(l_datT0, l_datT5) == 0)
        {   
            l_capitalGain = Math.max((calcTodaysCapitalGainTotal(l_datDate) -
                                    accumuratedCapitalGain.getCurrentTermLoss()), 0.0d);            
        }

        //�c�Ɠ�(T+0)����c�Ɠ�(T+3)�ŗ��N�ɂȂ�ꍇ<BR>
        else if (compareToYear(l_datT0, l_datT3) < 0)
        {
            l_capitalGain =
                Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                           accumuratedCapitalGain.geNextMonthLoss()), 0.0d);
                           
        }

        //�c�Ɠ�(T+4)�ŗ��N�ɂȂ�ꍇ<BR>
        else if (compareToYear(l_datT3, l_datT4) < 0)
        {
            if (WEB3DateUtility.compareToDay(l_datT3, l_datDate) == 0)
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.getCurrentTermLoss()), 0.0d);
                               
            }
            else if ((WEB3DateUtility.compareToDay(l_datT4, l_datDate) == 0) || 
                (WEB3DateUtility.compareToDay(l_datT5, l_datDate) == 0))
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.geNextMonthLoss()), 0.0d);
                               
            }
        }

        //�c�Ɠ�(T+5)�ŗ��N�ɂȂ�ꍇ<BR>
        else if (compareToYear(l_datT4, l_datT5) < 0)
        {
            if ((WEB3DateUtility.compareToDay(l_datT3, l_datDate) == 0) || 
                (WEB3DateUtility.compareToDay(l_datT4, l_datDate) == 0))
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.getCurrentTermLoss()), 0.0d);
                               
            }
            else if (WEB3DateUtility.compareToDay(l_datT5, l_datDate) == 0)
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.geNextMonthLoss()), 0.0d);
            }
        }
                
        return (l_capitalGain == 0.0d) ? 0.0d : Math.floor(l_capitalGain * taxRate);

    }
        
    /**
     * (calc�ϓ����f��)<BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������ȉ��̂悤�ɃZ�b�g����B<BR>
     * <BR>
     * �ϓ����f�J�n��	��	����.��n��<BR>
     * <BR>
     * [�ϓ����f�J�n��(����.��n��)�̗��c�Ɠ����痂�N�ƂȂ�ꍇ]<BR>
     * 	�ϓ����f�I����	��	�c�Ɠ�[5]<BR>
     * [��L�ȊO]<BR>
     * 	�ϓ����f�I����	��	����.��n��
     * @@param l_datDeliveryDate - (��n��)
     * @@roseuid 40E520DA02A1
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        
        //�ϓ����f�J�n��
        setReflectStartDay(l_datDeliveryDate);
                
        //�ϓ����f�I����       
        Date l_datNextBizDate = getCalcCondition().rollBizDate(l_datDeliveryDate, 1);
        
        //��n���Ǝ�n������(���c�Ɠ��j�ŔN���܂����ꍇ
        if (compareToYear(l_datDeliveryDate, l_datNextBizDate) < 0)
        {
            //���f�I������T+5
            setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));
        }
        else
        {
            //���N�̏ꍇ�͎�n��
            setReflectEndDay(l_datDeliveryDate);
        }

    }

    /**
     * (calc���n���v�݌v<����>)<BR>
     * ��n���ɂ����铖���ȍ~���n���v�̗݌v��ԋp����B<BR>
     * @@param l_datDeliveryDate (��n��)
     * @@return double
     */
    private double calcTodaysCapitalGainTotal(Date l_datDeliveryDate)
    {
        double l_dblCapitalGainTotal = 0.0d;

        Date l_datT0 = getCalcCondition().getBizDate(0);
        Date l_datT3 = getCalcCondition().getBizDate(3);
        Date l_datT4 = getCalcCondition().getBizDate(4);
        Date l_datT5 = getCalcCondition().getBizDate(5);

        //T+3�̏ꍇ
        if (WEB3DateUtility.compareToDay(l_datT3, l_datDeliveryDate) == 0)
        {
            l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount();
            return l_dblCapitalGainTotal;
        }
        //T+4 or T+5�̏ꍇ
        else
        {
            //T+0�`T+5�܂œ��N �܂��� T+3�`T+5�̊Ԃœ��N
            if ((compareToYear(l_datT0, l_datT5) == 0) ||
                (compareToYear(l_datT0, l_datT3) < 0))
            {
                //T+4�̏ꍇ
                if (WEB3DateUtility.compareToDay(l_datT4, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount() +
                        getCapitalGain(l_datT4).getAmount();
                }
                //T+5�̏ꍇ
                else if (WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount() +
                        getCapitalGain(l_datT4).getAmount() +
                        getCapitalGain(l_datT5).getAmount();
                }
            }

            //T+4�ŗ��N
            else if (compareToYear(l_datT3, l_datT4) < 0)
            {
                //T+4�̏ꍇ
                if (WEB3DateUtility.compareToDay(l_datT4, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT4).getAmount();
                }
                //T+5�̏ꍇ
                else if (WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT4).getAmount() +
                        getCapitalGain(l_datT5).getAmount();
                }
            }

            else if (compareToYear(l_datT4, l_datT5) < 0)
            {
                //T+4�̏ꍇ
                if (WEB3DateUtility.compareToDay(l_datT4, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount() +
                        getCapitalGain(l_datT4).getAmount();
                }
                //T+5�̏ꍇ
                else if (WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT5).getAmount();
                }
            }
        }

        //��L�ȊO�̃P�[�X�̏ꍇ0��Ԃ��B
        return l_dblCapitalGainTotal;
    }

    /**
     * (compare�N)<BR>
     * ��̓��t���r����(���x�͔N�܂łƂ���)�B<BR>
     * <BR>
     * l_dat1��l_dat2�̌�̏ꍇ�A�O���傫��������ԋp����B<BR>
     * l_dat1��l_dat2�̑O�̏ꍇ�A�O��菬����������ԋp����B<BR>
     * l_dat1��l_dat2�����l�̏ꍇ�A�O��ԋp����B<BR>
     * 
     * @@param l_dat1 (���t1)
     * @@param l_dat2 (���t2)
     * @@return int
     */
    private static int compareToYear(Date l_dat1, Date l_dat2) 
    {
        if (l_dat1 == null) 
        {
            l_dat1 = new Date(0);
        } 
        
        if (l_dat2 == null) 
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat = GtlUtils.getThreadSafeSimpleDateFormat("yyyy");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }


    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("taxRate", this.getTaxRate())
            .append("capitalGain[]", this.getCapitalGain())
            .append("accumuratedCapitalGain", this.getAccumuratedCapitalGain())
            .append("deliveryDate", this.getDeliveryDate())
            .appendSuper(super.toString())
            .toString();
    }

}
@
