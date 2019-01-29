head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����c���Ɖ�Comparator(WEB3MarginBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3MarginBalanceReferenceDetailUnit;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�M�p����c���Ɖ�Comparator�j�B<BR>
 * <BR>
 * �M�p����c���Ɖ�Comparator<BR>
 */
public class WEB3MarginBalanceReferenceComparator implements Comparator 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginBalanceReferenceComparator.class);

    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F����  <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;
    
    /**
     * (��r����)<BR>
     * <BR>
     * ��r����<BR>
     * <BR>
     * �ȉ��̍��ڂ��w�肳���B<BR>
     * �@@�M�p����c���Ɖ��.�����R�[�h<BR>
     * �@@�M�p����c���Ɖ��.�s��R�[�h<BR>
     * �@@�M�p����c���Ɖ��.�����敪<BR>
     * �@@�M�p����c���Ɖ��.���敪<BR>
     * �@@�M�p����c���Ɖ��.����<BR>
     * �@@�M�p����c���Ɖ��.����<BR>
     * �@@�M�p����c���Ɖ��.�ٍ�.�ٍϋ敪<BR>
     * �@@�M�p����c���Ɖ��.�ٍ�.�ٍϊ����l<BR>
     * �@@�M�p����c���Ɖ��.�]�����v<BR>
     * �@@�M�p����c���Ɖ��.�]�����v�i���o��l���j<BR>
     * <BR>
     */
    private String compareItem;
    
    /**
     * @@roseuid 4206CDD503B5<BR>
     */
    public WEB3MarginBalanceReferenceComparator() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �����̒l��this.orderBy�Cthis.��r���ڂɃZ�b�g����B<BR>
     * @@param l_strOrderBy - (orderBy) �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * @@param l_strCompareItem - (��r����) compare�ɂĎg�p�����r����<BR>
     * <BR>
     * �ȉ��̍��ڂ��w�肳���B<BR>
     * �@@�M�p����c���Ɖ��.�����R�[�h<BR>
     * �@@�M�p����c���Ɖ��.�s��R�[�h<BR>
     * �@@�M�p����c���Ɖ��.�����敪<BR>
     * �@@�M�p����c���Ɖ��.���敪<BR>
     * �@@�M�p����c���Ɖ��.����<BR>
     * �@@�M�p����c���Ɖ��.����<BR>
     * �@@�M�p����c���Ɖ��.�ٍ�.�ٍϋ敪<BR>
     * �@@�M�p����c���Ɖ��.�ٍ�.�ٍϊ����l<BR>
     * �@@�M�p����c���Ɖ��.�]�����v<BR>
     * �@@�M�p����c���Ɖ��.�]�����v�i���o��l���j<BR>
     * <BR>
     * @@return WEB3MarginBalanceReferenceComparator<BR>
     * @@roseuid 41C2995502C7<BR>
     */
    public WEB3MarginBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem) 
    {
        final String STR_METHOD_NAME ="WEB3MarginBalanceReferenceComparator(String, String)";
        log.entering(STR_METHOD_NAME);
        log.debug("����1�̒l = " + l_strOrderBy);
        log.debug("����2�̒l = " + l_strCompareItem);
        
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");
        }
        this.orderBy = l_strOrderBy;
        
        if(l_strCompareItem == null || 
            (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.TRADEMARKET.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.OPEN_DATE.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.CLOSEDATE.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.REPAYMENTNUM.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.INCOME.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.INCOME_COST.equals(l_strCompareItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̒l��'�����R�[�h'�A'�s��R�[�h'�A'�����敪'�A'���敪'�A'����'�A'����'�A" +
                "'�ٍϋ敪'�A'�ٍϊ����l'�A'�]�����v'�A'�]�����v�i���o��l���j'�ȊO�ł�" );
        }
        this.compareItem = l_strCompareItem;         
        
        log.debug("WEB3MarginBalanceReferenceComparator().....OK>>>>>");
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * �icompare�̎����j <BR>
     * <BR>
     * ���ׂP�C�Q�ɂ��āAthis.��r���ڂŎw�肵�����ڂ̔�r���s���B  <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �p�����[�^.����1�����2���A�M�p����c���Ɖ�׌^��cast����B<BR>
     * <BR>
     * �R�j�@@��r <BR>
     * �@@�Q�j��cast��������1�A����2�ɂ��� <BR>
     * �@@this.��r���ڂɊY�����鍀�ڂ̒l���r���A���ʂ�ԋp����B<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * <BR>
     * @@param l_obj1 - (����1) �M�p����c���Ɖ�׃I�u�W�F�N�g1<BR>
     * @@param l_obj2 - (����2) �M�p����Ɖ�׃I�u�W�F�N�g2<BR>
     * @@return int<BR>
     * @@roseuid 41C2995502CA<BR>
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginBalanceReferenceDetailUnit l_unit1 = null;
        WEB3MarginBalanceReferenceDetailUnit l_unit2 = null;

        if((l_obj1 instanceof WEB3MarginBalanceReferenceDetailUnit) && (l_obj2 instanceof WEB3MarginBalanceReferenceDetailUnit))
        {
            l_unit1 = (WEB3MarginBalanceReferenceDetailUnit)l_obj1;
            l_unit2 = (WEB3MarginBalanceReferenceDetailUnit)l_obj2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̌^��WEB3MarginBalanceReferenceDetailUnit�ȊO�ł��B"); 
        }
        
        // ��r���� == �����R�[�h or �����敪 or ���敪 or �ٍϋ敪�̏ꍇ
        if (this.compareItem.equals(WEB3EquityKeyItemDef.PRODUCTCODE)
            || this.compareItem.equals(WEB3EquityKeyItemDef.ACCOUNTTYPE)
            || this.compareItem.equals(WEB3EquityKeyItemDef.CONTRACT_DIVISION)
            || this.compareItem.equals(WEB3EquityKeyItemDef.REPAYMENT_DIV))
        {
            String l_strKeyItem1 = null;
            String l_strKeyItem2 = null;
            
            if(WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.compareItem))
            {
                // ��r���� = �����R�[�h
                l_strKeyItem1 = l_unit1.productCode;
                l_strKeyItem2 = l_unit2.productCode;
            }
            else if (WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(this.compareItem))
            {
                // ��r���� = �����敪
                l_strKeyItem1 = l_unit1.taxType;
                l_strKeyItem2 = l_unit2.taxType;
            }
            else if (WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(this.compareItem))
            {
                // ��r���� = ���敪
                l_strKeyItem1 = l_unit1.contractType;
                l_strKeyItem2 = l_unit2.contractType;
            }
            else if (WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(this.compareItem))
            {
                // ��r���� = �ٍϋ敪
                l_strKeyItem1 = l_unit1.repayment.repaymentDiv;
                l_strKeyItem2 = l_unit2.repayment.repaymentDiv;
            }
            
            if (l_strKeyItem1 == null && l_strKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // �����\�[�g�̏ꍇ
            if(WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                if (l_strKeyItem2 == null || (l_strKeyItem1 != null && 
                    l_strKeyItem1.compareTo(l_strKeyItem2) > 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else if (l_strKeyItem1 == null || (l_strKeyItem2 != null &&
                    l_strKeyItem1.compareTo(l_strKeyItem2) < 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
            }
            // �~���\�[�g�̏ꍇ
            else
            {
                if (l_strKeyItem2 == null || (l_strKeyItem1 != null &&
                    l_strKeyItem1.compareTo(l_strKeyItem2) > 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else if (l_strKeyItem1 == null || (l_strKeyItem2 != null &&
                    l_strKeyItem1.compareTo(l_strKeyItem2) < 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
            }
        }
        // ��r���� == ���� or �����̏ꍇ
        else if (this.compareItem.equals(WEB3EquityKeyItemDef.OPEN_DATE)
            || this.compareItem.equals(WEB3EquityKeyItemDef.CLOSEDATE))
        {
            Date l_datKeyItem1 = null;
            Date l_datKeyItem2 = null;
            if (WEB3EquityKeyItemDef.OPEN_DATE.equals(this.compareItem))
            {
                l_datKeyItem1 = l_unit1.openDate;
                l_datKeyItem2 = l_unit2.openDate;
            }
            else
            {
                l_datKeyItem1 = l_unit1.closeDate;
                l_datKeyItem2 = l_unit2.closeDate;
            }
            if (l_datKeyItem1 == null && l_datKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // �����\�[�g�̏ꍇ
            if(WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                if (l_datKeyItem2 == null || (l_datKeyItem1 != null && 
                    l_datKeyItem1.compareTo(l_datKeyItem2) > 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else if (l_datKeyItem1 == null || (l_datKeyItem2 != null &&
                    l_datKeyItem1.compareTo(l_datKeyItem2) < 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
            }
            // �~���\�[�g�̏ꍇ
            else
            {
                if (l_datKeyItem2 == null || (l_datKeyItem1 != null &&
                    l_datKeyItem1.compareTo(l_datKeyItem2) > 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else if (l_datKeyItem1 == null || (l_datKeyItem2 != null &&
                    l_datKeyItem1.compareTo(l_datKeyItem2) < 0))
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
            }
        }
        // ��r���� == �s��R�[�h or �ٍϊ����l�̏ꍇ
        else if (this.compareItem.equals(WEB3EquityKeyItemDef.TRADEMARKET)
		   	|| this.compareItem.equals(WEB3EquityKeyItemDef.REPAYMENTNUM))
        {
            int l_intKeyItem1 = 0;
            int l_intKeyItem2 = 0;
            if (WEB3EquityKeyItemDef.TRADEMARKET.equals(this.compareItem))
            {
                // ��r���� == �s��R�[�h�̏ꍇ
                l_intKeyItem1 = Integer.parseInt(l_unit1.marketCode);
                l_intKeyItem2 = Integer.parseInt(l_unit2.marketCode);
            }
			else if (WEB3EquityKeyItemDef.REPAYMENTNUM.equals(this.compareItem))
			{
				// ��r���� = �ٍϊ����l
				l_intKeyItem1 = Integer.parseInt(l_unit1.repayment.repaymentTimeLimit);
				l_intKeyItem2 = Integer.parseInt(l_unit2.repayment.repaymentTimeLimit);
			}
            
            if (l_intKeyItem1 == l_intKeyItem2)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // �����\�[�g�̏ꍇ
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                if (l_intKeyItem1 < l_intKeyItem2)
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
            }
            // �~���\�[�g�̏ꍇ
            else
            {
                if (l_intKeyItem1 < l_intKeyItem2)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
            }
        }
        // ��r���� == �]�����v or �]�����v�i���o��l���j�̏ꍇ
        else
        {
            String l_strKeyItem1 = "";
            String l_strKeyItem2 = "";
            if (WEB3EquityKeyItemDef.INCOME.equals(this.compareItem))
            {
                // ��r���� == �]�����v�̏ꍇ
                l_strKeyItem1 = l_unit1.appraisalProfitLoss;
                l_strKeyItem2 = l_unit2.appraisalProfitLoss;
            }
            else if (WEB3EquityKeyItemDef.INCOME_COST.equals(this.compareItem))
            {
                // ��r���� == �]�����v�i���o��l���j�̏ꍇ
                l_strKeyItem1 = l_unit1.appraisalProfitLossCost;
                l_strKeyItem2 = l_unit2.appraisalProfitLossCost;
            }
            
            if (l_strKeyItem1 == null && l_strKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            if (l_strKeyItem1 == null || l_strKeyItem2 == null)
            {
	            // �����\�[�g�̏ꍇ
	            if(WEB3AscDescDef.ASC.equals(this.orderBy))
	            {
	                if (l_strKeyItem2 == null || (l_strKeyItem1 != null && 
	                    l_strKeyItem1.compareTo(l_strKeyItem2) > 0))
	                {
	                    log.exiting(STR_METHOD_NAME);
	                    return 1;
	                }
	                else if (l_strKeyItem1 == null || (l_strKeyItem2 != null &&
	                    l_strKeyItem1.compareTo(l_strKeyItem2) < 0))
	                {
	                    log.exiting(STR_METHOD_NAME);
	                    return -1;
	                }
	            }
	            // �~���\�[�g�̏ꍇ
	            else
	            {
	                if (l_strKeyItem2 == null || (l_strKeyItem1 != null &&
	                    l_strKeyItem1.compareTo(l_strKeyItem2) > 0))
	                {
	                    log.exiting(STR_METHOD_NAME);
	                    return -1;
	                }
	                else if (l_strKeyItem1 == null || (l_strKeyItem2 != null &&
	                    l_strKeyItem1.compareTo(l_strKeyItem2) < 0))
	                {
	                    log.exiting(STR_METHOD_NAME);
	                    return 1;
	                }
	            }
            }
            
            double l_dblKeyItem1 = 0.0D;
            double l_dblKeyItem2 = 0.0D;
            
            l_dblKeyItem1 = Double.parseDouble(l_strKeyItem1);
            l_dblKeyItem2 = Double.parseDouble(l_strKeyItem2); 
            
            if (l_dblKeyItem1 == l_dblKeyItem2)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // �����\�[�g�̏ꍇ
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                if (l_dblKeyItem1 < l_dblKeyItem2)
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
            }
            // �~���\�[�g�̏ꍇ
            else
            {
                if (l_dblKeyItem1 < l_dblKeyItem2)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
            }
        }
    }
    
    /**
     * �iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B <BR>
     * @@param arg0<BR>
     * @@return boolean<BR>
     * @@roseuid 41C2995502D6<BR>
     */
    public boolean equals(Object arg0) 
    {
        return false;
    }
}
@
