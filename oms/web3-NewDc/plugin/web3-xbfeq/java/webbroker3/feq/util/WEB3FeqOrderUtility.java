head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����̃��[�e�B���e�B(WEB3FeqOrderUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 ���Ō� (���u) �V�K�쐬
*/
package webbroker3.feq.util;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����̃��[�e�B���e�B�N���X�B<BR>
 * <BR>
 * 
 * @@author ���Ō�(���u)
 * @@version 1.0
 */
public class WEB3FeqOrderUtility
{
    public static String FLOOR = "0";
    public static String CEIL = "1";
    public static String ROUND = "3";
    public static String CUTOFF = "4"; 
    /**
     * 
     */
    public WEB3FeqOrderUtility()
    {
        super();
    }

    /**
     * (get���s����)<BR>
     * �����̎��s�������AWEB3�̎��s�������擾���ԋp����B<BR>
     * �O�����������}�l�[�W��.get���s�����ɈϏ��ideligate�j����B<BR>
     * @@param l_strExecutionConditionType - (���s����)<BR>
     * SONAR�̎��s����<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType
     * @@throws WEB3BaseException
     * @@roseuid 4296E5D801E8
     */
    public static FeqExecutionConditionType getExecutionCondition(
        String l_strExecutionConditionType) throws WEB3BaseException
    {
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        return l_orderManager.getExecutionCondition(l_strExecutionConditionType);
        
    }

    /**
     * (get���s�����iSONAR�j)<BR>
     * �����̎��s�������ASONAR�̎��s�������擾���ԋp����B<BR>
     * <BR>
     * �O�����������}�l�[�W��.get���s�����ɈϏ��ideligate�j����B<BR>
     * @@param l_strExecutionConditionType - (���s����)<BR>
     * WEB3�̎��s����<BR>
     *
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4296E826011D
     */
    public static String getExecutionConditionTypeSonar(
        String l_strExecutionConditionType) throws WEB3BaseException
    {
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        return l_orderManager.getExecutionConditionTypeSonar(l_strExecutionConditionType);
    }
    
    /**
     * (get��������敪)<BR>
     * �����̎��s�������A��������敪���擾���ԋp����B<BR>
     * <BR>
     * @@param l_strTaxTypeType- (��������敪)<BR>
     * WEB3�̓�������敪<BR>
     *
     * @@return TaxTypeEnum
     * @@throws WEB3BaseException
     * @@roseuid 4296E826011D
     */
    public static TaxTypeEnum getTaxType(
        String l_strTaxTypeType) throws WEB3BaseException
    {
        // �P�j�����̓�������敪���h0�F��ʌ����h �̏ꍇ
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxTypeType))
        {
            return TaxTypeEnum.NORMAL;
        }
        else 
        {
            //�Q�j�����̓�������敪���h1:��������h �̏ꍇ
            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strTaxTypeType))
            {
                return TaxTypeEnum.SPECIAL;
            }
        }
        return null;
    }
    
    /**
     * (get��������敪)<BR>
     * �����̎��s�������A��������敪���擾���ԋp����B<BR>
     * <BR>
     * @@param l_taxTypeEnum- (��������敪)<BR>
     * WEB3�̓�������敪<BR>
     *
     * @@return String 
     * @@throws WEB3BaseException
     * @@roseuid 4296E826011D
     */
    public static String getTaxType(
        TaxTypeEnum l_taxTypeEnum) throws WEB3BaseException
    {
        // �P�j�����̓�������敪���h0�F��ʌ����h �̏ꍇ
        if (TaxTypeEnum.NORMAL.equals(l_taxTypeEnum))
        {
            return WEB3TaxTypeSpecialDef.NORMAL;
        }
        else 
        {
            //�Q�j�����̓�������敪���h1:��������h �̏ꍇ
            if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum))
            {
                return WEB3TaxTypeSpecialDef.SPECIAL;
            }
        }
        return null;
    }
    
    /**
     * (get�ۂߒl)<BR>
     * �����̊ۂߕ����Ə����������Adouble�l���ۂ߂�B<BR>
     * ��F<BR>
     *  FLOOR(�ȉ�)�̏ꍇ
     * �@@�@@1234.2345678,2  �ԋp�l 1234.23<BR>
     * �@@�@@1234.2345678,3  �ԋp�l 1234.234<BR>
     * �@@�@@-1234.2345678,2 �ԋp�l-1234.24<BR>
     * �@@�@@-1234.2345678,3 �ԋp�l-1234.235<BR>
     * <BR>
     *  CEIL(�ȏ�)�̏ꍇ
     * �@@�@@1234.2345678,2  �ԋp�l 1234.24<BR>
     * �@@�@@1234.2345678,3  �ԋp�l 1234.235<BR>
     * �@@�@@-1234.2345678,2 �ԋp�l-1234.23<BR>
     * �@@�@@-1234.2345678,3 �ԋp�l-1234.234<BR>
     * <BR>
     *  ROUND(�ʏ�̎l�̌ܓ�)�̏ꍇ
     * �@@�@@1234.2345678,2  �ԋp�l 1234.23<BR>
     * �@@�@@1234.2345678,3  �ԋp�l 1234.235<BR>
     * �@@�@@-1234.2345678,2 �ԋp�l-1234.23<BR>
     * �@@�@@-1234.2345,3    �ԋp�l-1234.235<BR>
     * �@@�@@-1234.2345678,3 �ԋp�l-1234.235<BR>
     * <BR>
     *  CUTOFF(�؎�)�̏ꍇ
     * �@@�@@1234.2345678,2  �ԋp�l 1234.23<BR>
     * �@@�@@1234.2345678,3  �ԋp�l 1234.234<BR>
     * �@@�@@-1234.2345678,2 �ԋp�l-1234.23<BR>
     * �@@�@@-1234.2345678,3 �ԋp�l-1234.234<BR>
     * <BR>
     * @@param l_dblValueForCut �ۂ߂����l
     * @@param l_intSacle ����
     * 0��菬�����Ȃ��ꍇ�A�����̊ۂ߂̌���<BR>
     * 0��菬�����ꍇ�A�����̊ۂ߂̌���<BR>
     * @@param l_strDealType �ۂߕ���
     * 0 �ȉ� 1 �ȏ� 2 Java�̎l�̌ܓ� 3 �ʏ�̎l�̌ܓ� 4 �؎�<BR>  
     * @@return �ۂߌ�̒l
     */
    public static double getCutOutValue(
        double l_dblValueForCut, 
        int l_intSacle, 
        String l_strDealType)
    {
        double l_dblResult = 0.0D;
        
        if (FLOOR.equals(l_strDealType))
        {
            BigDecimal l_bdValue =
                new BigDecimal(String.valueOf(l_dblValueForCut)).setScale(l_intSacle, BigDecimal.ROUND_FLOOR);
            l_dblResult = l_bdValue.doubleValue();
        }
        else if (CEIL.equals(l_strDealType))
        {
            BigDecimal l_bdValue =
                new BigDecimal(String.valueOf(l_dblValueForCut)).setScale(l_intSacle, BigDecimal.ROUND_CEILING);
            l_dblResult = l_bdValue.doubleValue();
        }
        else if (ROUND.equals(l_strDealType))
        {
            BigDecimal l_bdValue =
               new BigDecimal(String.valueOf(l_dblValueForCut)).setScale(l_intSacle, BigDecimal.ROUND_HALF_UP);
            l_dblResult = l_bdValue.doubleValue();
        }
        else if (CUTOFF.equals(l_strDealType))
        {
            BigDecimal l_bdValue =
                new BigDecimal(String.valueOf(l_dblValueForCut)).setScale(l_intSacle, BigDecimal.ROUND_DOWN);
            l_dblResult = l_bdValue.doubleValue();
        }
        
        return l_dblResult;
    }


    /**
     * (Remainder���[���̔��f)<BR>
     * BigIngeter��mod
     * 
     * isRemainderZero(100 , 2) : true
     * isRemainderZero(100 , 0.2) : true
     * 
     * @@param l_dbl1 
     * @@param l_dbl1
     * @@return boolean
     * true Remainder = 0 
     * @@roseuid 4296E826011D
     */
    public static boolean isRemainderZero(double l_dbl1, double l_dbl2)
    {
        return (getRemainder(l_dbl1, l_dbl2) == 0.0D);
    }
    
    /**
     * (Remainder���擾)<BR>
     * BigIngeter��mod
     * 
     * getRemainder(100 , 2) : 0
     * getRemainder(100 , 0.3) : 0.1
     * 
     * @@param l_dbl1 
     * @@param l_dbl1
     * @@return double
     * @@roseuid 4296E826011D
     */
    public static double getRemainder(double l_dbl1, double l_dbl2)
    {
        BigDecimal l_bdNum1 =
            new BigDecimal(String.valueOf(l_dbl1));
        BigDecimal l_bdNum2 =
            new BigDecimal(String.valueOf(l_dbl2));
        BigDecimal l_bdNum3 =
            l_bdNum1.divide(l_bdNum2, 0, BigDecimal.ROUND_DOWN);
        BigDecimal l_bdNum4 = l_bdNum2.multiply(l_bdNum3);
        
        double l_dblRemainder = l_bdNum1.subtract(l_bdNum4).doubleValue();
        
        return l_dblRemainder;
    }

    /**
     * (minus operate)<BR>
     * BigDecimal�Ń}�C�i�X���A�����̍ő�̏����������ԋp�l�̏��������Ƃ���A<BR>
     * �ۂߕ����͐؎̂ł��B<BR>
     * <BR>
     * @@param l_dbl1 
     * @@param l_dbl1
     * @@return double
     * @@roseuid 4296E826011D
     */
    public static double decimalMinus(double l_dbl1, double l_dbl2)
    {
        int l_intPos1 = 
            WEB3StringTypeUtility.getFractionDigits(new Double(l_dbl1).toString());
        int l_intPos2 =
            WEB3StringTypeUtility.getFractionDigits(new Double(l_dbl2).toString());

        int l_intPos = Math.max(l_intPos1, l_intPos2);
        
        BigDecimal l_bdNum1 = 
            new BigDecimal(l_dbl1).setScale(l_intPos, BigDecimal.ROUND_HALF_UP);
        BigDecimal l_bdNum2 =  
            new BigDecimal(l_dbl2).setScale(l_intPos, BigDecimal.ROUND_HALF_UP);
        
        BigDecimal l_bdReturn = l_bdNum1.subtract(l_bdNum2);  

        return l_bdReturn.doubleValue();
    }

    /**
     * (plus operate)<BR>
     * BigDecimal�Ńv���X���A�����̍ő�̏����������ԋp�l�̏��������Ƃ���A<BR>
     * �ۂߕ����͐؎̂ł��B<BR>
     * <BR>
     * @@param l_dbl1 
     * @@param l_dbl1
     * @@return double
     * @@roseuid 4296E826011D
     */
    public static double decimalPlus(double l_dbl1, double l_dbl2)
    {
        return decimalMinus(l_dbl1, (-1) * l_dbl2);
    }
}
@
