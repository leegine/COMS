head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�c���Ɖ�Comparator(WEB3FuturesOptionsBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ �V�K�쐬         
*/
package webbroker3.ifo.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.util.WEB3DateUtility;


/**
 * (�敨OP�c���Ɖ�Comparator)<BR>
 * �敨OP�c���Ɖ�Comparator<BR>
 * @@author ������
 * @@version 1.0  
 */
public class WEB3FuturesOptionsBalanceReferenceComparator implements Comparator
{
   
    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;
   
    /**
     * ��r����<BR>
     * <BR>
     * �ȉ��̍��ڂ��w�肳���B<BR>
     * �@@�敨OP�c���Ɖ��.�����R�[�h<BR>
     * �@@�敨OP�c���Ɖ��.���敪<BR>
     * �@@�敨OP�c���Ɖ��.����<BR>
     * �@@�敨OP�c���Ɖ��.���v<BR>
     * �@@�敨OP�c���Ɖ��.���v(���o��j<BR>
     */
    private String compareItem;
   
    /**
     * (�敨OP�c���Ɖ�Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * �����̒l��this.orderBy�Cthis.��r���ڂɃZ�b�g����B<BR>
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     * @@param l_strCompareItem - ��r����
     * <BR>
     * �ȉ��̍��ڂ��w�肳���B<BR>
     * �@@�敨OP�c���Ɖ��.�����R�[�h<BR>
     * �@@�敨OP�c���Ɖ��.���敪<BR>
     * �@@�敨OP�c���Ɖ��.����<BR>
     * �@@�敨OP�c���Ɖ��.���v<BR>
     * �@@�敨OP�c���Ɖ��.���v(���o��j<BR>
     * @@roseuid 41B7F16403C3
     */
    public WEB3FuturesOptionsBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem) 
    {
        if (l_strOrderBy == null
            ||( !WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
       
        if (l_strCompareItem == null
            || (!WEB3IfoKeyItemDef.BR_PRODUCTCODE.equals(l_strCompareItem)
            && !WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(l_strCompareItem)
            && !WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strCompareItem)
            && !WEB3IfoKeyItemDef.INCOME.equals(l_strCompareItem)
            && !WEB3IfoKeyItemDef.INCOME_COST.equals(l_strCompareItem)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'�����R�[�h�A'���敪�A����'�A���v�A���v�i���o��j�ȊO�ł��B");
        }
       
        this.compareItem = l_strCompareItem;
    }
   
    /**
     * �icompare�̎����j<BR> 
     * <BR>
     * ���ׂP�C�Q�ɂ��āAthis.��r���ڂŎw�肵�����ڂ̔�r���s���B<BR>  
     * <BR>
     * �P�j�@@������cast<BR>
     * �p�����[�^.����1�����2���A�敨OP�c���Ɖ�׌^��cast����B<BR>
     * <BR>
     * �R�j�@@��r<BR> 
     * �@@�Q�j��cast��������1�A����2�ɂ���<BR> 
     * �@@this.��r���ڂɊY�����鍀�ڂ̒l���r���A���ʂ�ԋp����B<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR> 
     * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B<BR> 
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B<BR> 
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR> 
     * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B<BR> 
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * @@param l_obj1 - �敨OP�c���Ɖ�׃I�u�W�F�N�g1<BR>
     * @@param l_obj2 - �敨OP�c���Ɖ�׃I�u�W�F�N�g2<BR>
     * @@return int
     * @@roseuid 41B7F16403E2
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        int l_intReturnValue;
        String l_strVal1 = null;
        String l_strVal2 = null;
        
        if (l_obj1 instanceof WEB3FuturesOptionsDetailUnit 
            && l_obj2 instanceof WEB3FuturesOptionsDetailUnit)
        {
           // ��r���� == �����R�[�h�̏ꍇ
           if (WEB3IfoKeyItemDef.BR_PRODUCTCODE.equals(this.compareItem))
           {
               l_strVal1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).productCode;
               l_strVal2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).productCode;

               if (l_strVal1 == null || l_strVal2 == null)
               {
                   int l_intResult;
               
                   if (l_strVal1 == null && l_strVal2 == null)
                   {
                       l_intResult = 0;
                   }
                   else if (l_strVal1 == null)
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
                   }
                   else
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
                   }
                        
                   return l_intResult;            
               }

               l_intReturnValue = this.compare(l_strVal1,l_strVal2);               
               return l_intReturnValue;

           }
           // ��r���� == ���敪�̏ꍇ
           else if (WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(this.compareItem))
           {
               l_strVal1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).contractType;
               l_strVal2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).contractType;

               if (l_strVal1 == null || l_strVal2 == null)
               {
                   int l_intResult;
               
                   if (l_strVal1 == null && l_strVal2 == null)
                   {
                       l_intResult = 0;
                   }
                   else if (l_strVal1 == null)
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
                   }
                   else
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
                   }
                        
                   return l_intResult;            
               }

               l_intReturnValue = this.compare(l_strVal1,l_strVal2);               
               return l_intReturnValue;
           }
           // ��r���� == ���v or ���v�i���o��j�̏ꍇ
           else if (WEB3IfoKeyItemDef.INCOME.equals(this.compareItem)
           || WEB3IfoKeyItemDef.INCOME_COST.equals(this.compareItem))
           {
               //��r���� == ���v�̏ꍇ
               if (WEB3IfoKeyItemDef.INCOME.equals(this.compareItem))
               {
                   l_strVal1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).income;
                   l_strVal2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).income;
               }
               //��r���� == ���v�i���o��l���j�̏ꍇ
               else
               {
                   l_strVal1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).incomeCost;
                   l_strVal2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).incomeCost;
               }
               
               if (l_strVal1 == null || l_strVal2 == null)
               {
                   int l_intResult;
               
                   if (l_strVal1 == null && l_strVal2 == null)
                   {
                       l_intResult = 0;
                   }
                   else if (l_strVal1 == null)
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
                   }
                   else
                   {
                       l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
                   }
                        
                   return l_intResult;            
                }
                double l_dblVal1 = 0;
                double l_dblVal2 = 0;
                
                //��r���� == ���v�̏ꍇ
                if (WEB3IfoKeyItemDef.INCOME.equals(this.compareItem))
                {
                   l_dblVal1 = Double.parseDouble(((WEB3FuturesOptionsDetailUnit)l_obj1).income);
                   l_dblVal2 = Double.parseDouble(((WEB3FuturesOptionsDetailUnit)l_obj2).income);
                }
                //��r���� == ���v�i���o��l���j�̏ꍇ
                else
                {
                   l_dblVal1 = Double.parseDouble(((WEB3FuturesOptionsDetailUnit)l_obj1).incomeCost);
                   l_dblVal2 = Double.parseDouble(((WEB3FuturesOptionsDetailUnit)l_obj2).incomeCost);
                }
                l_intReturnValue = this.compare(l_dblVal1,l_dblVal2);           
               
                return l_intReturnValue;
            }
            // ��r���� == �����̏ꍇ
            else
            {
                Date l_datOpenDate1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).openDate;
                Date l_datOpenDate2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).openDate;

                if (l_datOpenDate1 == null || l_datOpenDate2 == null)
                {
                    int l_intResult;
               
                    if (l_datOpenDate1 == null && l_datOpenDate2 == null)
                    {
                        l_intResult = 0;
                    }
                    else if (l_datOpenDate1 == null)
                    {
                        l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
                    }
                    else
                    {
                        l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
                    }
                        
                    return l_intResult;            
                }
               
                l_intReturnValue = this.compare(l_datOpenDate1, l_datOpenDate2);               
                return l_intReturnValue;
            }
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̌^��WEB3FuturesOptionsDetailUnit�ȊO�ł��B");
        }
    }
   
    /**
     * �iequals�̎����j  <BR>
     * <BR>
     * ���g�p�B<BR>  
     * false��ԋp����B<BR>
     * @@param obj
     * @@return boolean
     * @@roseuid 41B7F1650019
     */
    public boolean equals(Object l_obj) 
    {
        if (l_obj instanceof WEB3FuturesOptionsBalanceReferenceComparator)
        {
            WEB3FuturesOptionsBalanceReferenceComparator l_comparator = (WEB3FuturesOptionsBalanceReferenceComparator)l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
   
    /**
     * �icompare�̎����j<BR> 
     * �����A�~���̎w��ɂ��ƂÂ�String�^�̔�r���s���B<BR>
     * <BR>
     * �����w��̏ꍇ�A<BR>
     * �p�����[�^.l_strVal1���p�����[�^l_strVal2��菬�����ꍇ�͕��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_strVal1���p�����[�^.l_strVal2���傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     * �~���w��̏ꍇ�A<BR>
     * �p�����[�^.l_strVal1���p�����[�^.l_strVal2��菬�����ꍇ�͐��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_strVal1���p�����[�^.l_strVal2���傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     * ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * @@param l_strVal1 - ��r����1
     * @@param l_strVal2 - ��r����2
     * @@return int
     * @@roseuid 407BC2700167
     */
    private int compare(String l_strVal1,String l_strVal2)
    {
        if (l_strVal1.equals(l_strVal2))
        {
            return 0;
        }
        else if (l_strVal1.compareTo(l_strVal2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }
    
    /**
     * �icompare�̎����j<BR> 
     * �����A�~���̎w��ɂ��ƂÂ�Date�^�̔�r���s���B<BR>
     * <BR>
     * �����w��̏ꍇ�A<BR>
     * �p�����[�^.l_datVal1���p�����[�^l_datVal2��菬�����ꍇ�͕��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_datVal1���p�����[�^.l_datVal2���傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     * �~���w��̏ꍇ�A<BR>
     * �p�����[�^.l_datVal1���p�����[�^.l_datVal2��菬�����ꍇ�͐��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_datVal1���p�����[�^.l_datVal2���傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     * ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * @@param l_datVal1 - ��r����1
     * @@param l_datVal2 - ��r����2
     * @@return int
     * @@roseuid 407BC2700167
     */
    private int compare(Date l_datVal1 ,Date l_datVal2)
    {
        if (WEB3DateUtility.compareToDay(l_datVal1, l_datVal2) == 0)
        {
            return 0;
        }
        else if (WEB3DateUtility.compareToDay(l_datVal1,l_datVal2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;        
            }
            else
            {
                return 1;
            }
        }
    }
    
    /**
     * �icompare�̎����j<BR> 
     * �����A�~���̎w��ɂ��ƂÂ�double�^�̔�r���s���B<BR>
     * <BR>
     * �����w��̏ꍇ�A<BR>
     * �p�����[�^.l_dblVal1���p�����[�^l_dblVal2��菬�����ꍇ�͕��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_dblVal1���p�����[�^.l_dblVal2���傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     * �~���w��̏ꍇ�A<BR>
     * �p�����[�^.l_dblVal1���p�����[�^.l_dblVal2��菬�����ꍇ�͐��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_dblVal1���p�����[�^.l_dblVal2���傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     * ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * @@param l_dblVal1 - ��r����1
     * @@param l_dblVal2 - ��r����2
     * @@return int
     * @@roseuid 407BC2700167
     */
    private int compare(double l_dblVal1, double l_dblVal2)
    {
                
        if (l_dblVal1 == l_dblVal2)
        {
            return 0;
             
        }
        else if (l_dblVal1 > l_dblVal2) 
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return 1;
            }
            else 
            {
                return -1; 
            }
                          
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;
            }
            else 
            {
                return 1; 
            }                                     
        }         
    }
}
@
