head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�c���Ɖ�Comparator(WEB3MutualBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 ������ (���u) �V�K�쐬
                   2006/03/08 ��� (SRA) �d�l�ύX�i���f���j�F403
*/

package webbroker3.mf.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (���M�c���Ɖ�Comparator)<BR>
 * ���M�c���Ɖ�Comparator
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceComparator implements Comparator 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBalanceReferenceComparator.class);
   
   /**
    * (orderBy)<BR>
    * A�F����<BR>
    * D�F�~��
    */
   private String orderBy;
   
   /**
    * (��r����)<BR>
    * ��r����<BR>
    * <BR>
    * �ȉ��̍��ڂ��w�肳���B<BR>
    * �@@���M�c���Ɖ��.�����敪<BR>
    * �@@���M�c���Ɖ��.�]���z<BR>
    * �@@���M�c���Ɖ��.�]�����v<BR>
    * �@@���M�c���Ɖ��.������t���؎���<BR>
    *   ���M�c���Ɖ��.����ID<BR>
    */
   private String compareItem;
   
   /**
    * (���M�c���Ɖ�Comparator)<BR>
    * �R���X�g���N�^�B<BR>
    * <BR>
    * ������this.orderBy�Athis.��r���ڂɃZ�b�g����B<BR>
    * @@param orderBy - �\�[�g�L�[�̏����~���������B
    * <BR>
    * A�F���� <BR>
    * D�F�~�� <BR>
    * <BR>
    * @@param compareItem - ��r���� 
    * <BR>
    * �ȉ��̍��ڂ��w�肳���B<BR>
    * �@@���M�c���Ɖ��.�����敪 <BR>
    * �@@���M�c���Ɖ��.�]���z <BR>
    * �@@���M�c���Ɖ��.�]�����v <BR>
    * �@@���M�c���Ɖ��.������t���؎��� <BR>
    *   ���M�c���Ɖ��.����ID<BR>
    * @@roseuid 41B7F69C0104
    */
   public WEB3MutualBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem) 
   {
       final String STR_METHOD_NAME =
           "WEB3MutualBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem)";
       log.entering(STR_METHOD_NAME);  
       
       if (l_strCompareItem == null || 
               (!l_strCompareItem.equals(WEB3MFSortkeyItemDef.TAX_TYPE) && 
               !l_strCompareItem.equals(WEB3MFSortkeyItemDef.MARKET_VALUE) && 
               !l_strCompareItem.equals(WEB3MFSortkeyItemDef.APPRAISAL_PROFIT_LOSS) && 
               !l_strCompareItem.equals(WEB3MFSortkeyItemDef.ORDER_CLOSE_TIME)&&
               !l_strCompareItem.equals(WEB3MFSortkeyItemDef.MUTUAL_PRODUCT_ID)))
       {
               throw new IllegalArgumentException(
                   "�p�����[�^�̒l��'���M�c���Ɖ��.�����敪'�A'���M�c���Ɖ��.�]���z'�A" +
                   "'���M�c���Ɖ��.�]�����v'�A'���M�c���Ɖ��.������t���؎���'�A"+
                   "'���M�c���Ɖ��.����ID'�ȊO�ł��B");
       }  
       this.compareItem = l_strCompareItem;
       
       if (l_strOrderBy == null || 
               (!l_strOrderBy.equals(WEB3AscDescDef.ASC) && 
               !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
       {
               throw new IllegalArgumentException(
                   "�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
       }       
       this.orderBy = l_strOrderBy;
       
       log.exiting(STR_METHOD_NAME);
   }
   
   /**
    * �icompare�̎����j <BR>
    * <BR>
    * ���ׂP�C�Q�ɂ��āAthis.��r���ڂŎw�肵�����ڂ̔�r���s���B<BR>  
    * <BR>
    * �P�j�@@������cast<BR>
    * �p�����[�^.����1�����2���A���M�c���Ɖ�׌^��cast����B<BR>
    * <BR>
    * �R�j�@@��r <BR>
    * �@@�Q�j��cast��������1�A����2�ɂ��� <BR>
    * �@@this.��r���ڂɊY�����鍀�ڂ̒l���r���A���ʂ�ԋp����B<BR>
    * <BR>
    * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
    * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A<BR>
    *        ���̐����i-1�j��ԋp����B <BR>
    * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B<BR> 
    * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A<BR>
    *        ���̐����i1�j��ԋp����B <BR>
    * <BR>
    * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
    * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A<BR>
    *        ���̐����i1�j��ԋp����B <BR>
    * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B <BR>
    * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A<BR>
    *        ���̐����i-1�j��ԋp����B <BR>
    * @@param l_obj1 - ���M�c���Ɖ�׃I�u�W�F�N�g1
    * @@param l_obj2 - ���M�c���Ɖ�׃I�u�W�F�N�g2
    * @@return int
    * @@roseuid 41B7F69C0123
    */
   public int compare(Object l_obj1, Object l_obj2) 
   {
       final String l_strMethodName = "compare()";
       log.entering(l_strMethodName);
       
       WEB3MutualBalanceReferenceDetailUnit l_mfBalanceReferenceDetailUnit1 = null;
       WEB3MutualBalanceReferenceDetailUnit l_mfBalanceReferenceDetailUnit2 = null;
       
       if(l_obj1 instanceof WEB3MutualBalanceReferenceDetailUnit
               && l_obj2 instanceof WEB3MutualBalanceReferenceDetailUnit)
       {
           l_mfBalanceReferenceDetailUnit1 = (WEB3MutualBalanceReferenceDetailUnit)l_obj1;
           l_mfBalanceReferenceDetailUnit2 = (WEB3MutualBalanceReferenceDetailUnit)l_obj2;   
       }
       else
       {
           throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������" 
               + "'WEB3MutualBalanceReferenceDetailUnit'�ތ^�B");
       }

       Object l_compareItem1 = null;
       Object l_compareItem2 = null;     
       
       if(WEB3MFSortkeyItemDef.TAX_TYPE.equals(this.compareItem))
       {
           l_compareItem1 = l_mfBalanceReferenceDetailUnit1.taxType;
           l_compareItem2 = l_mfBalanceReferenceDetailUnit2.taxType;
       }
       
       else if(WEB3MFSortkeyItemDef.MARKET_VALUE.equals(this.compareItem))
       {
           if (l_mfBalanceReferenceDetailUnit1.marketValue != null)
           {
               l_compareItem1 = new Long(l_mfBalanceReferenceDetailUnit1.marketValue);
           }
           if (l_mfBalanceReferenceDetailUnit2.marketValue != null)
           {
               l_compareItem2 = new Long(l_mfBalanceReferenceDetailUnit2.marketValue);
           }
       }
       
       else if(WEB3MFSortkeyItemDef.APPRAISAL_PROFIT_LOSS.equals(this.compareItem))
       {
           if (l_mfBalanceReferenceDetailUnit1.appraisalProfitLoss != null)
           {
               l_compareItem1 = new Long(l_mfBalanceReferenceDetailUnit1.appraisalProfitLoss);
           }
           if (l_mfBalanceReferenceDetailUnit2.appraisalProfitLoss != null)
           {
               l_compareItem2 = new Long(l_mfBalanceReferenceDetailUnit2.appraisalProfitLoss);
           }
       }
       
       else if(WEB3MFSortkeyItemDef.ORDER_CLOSE_TIME.equals(this.compareItem))
       {
           if (l_mfBalanceReferenceDetailUnit1.orderCloseTime != null)
           {
               l_compareItem1 = WEB3DateUtility.getDate(l_mfBalanceReferenceDetailUnit1.orderCloseTime, "HH:mm");
           }
           if (l_mfBalanceReferenceDetailUnit2.orderCloseTime != null)
           {
               l_compareItem2 = WEB3DateUtility.getDate(l_mfBalanceReferenceDetailUnit2.orderCloseTime, "HH:mm");
           }
       }
       
       else if(WEB3MFSortkeyItemDef.MUTUAL_PRODUCT_ID.equals(this.compareItem))
       {
           if (l_mfBalanceReferenceDetailUnit1.mutualProductId != null)
           {
               l_compareItem1 = l_mfBalanceReferenceDetailUnit1.mutualProductId;
           }
           if (l_mfBalanceReferenceDetailUnit2.mutualProductId != null)
           {
               l_compareItem2 = l_mfBalanceReferenceDetailUnit2.mutualProductId;
           }
       }
       
       if (l_compareItem1 == null && l_compareItem2 == null)
       {
           return 0;
       }

       int l_intReturn ;
       if (l_compareItem1 == null || l_compareItem2 == null)
       {
           if (l_compareItem1 == null)
           {
               l_intReturn = 1;
           }
           else
           {
               l_intReturn = -1;
           }            
                        
       } 
       else
       {
           if (compareObj(l_compareItem1, l_compareItem2) < 0)
           {
               l_intReturn = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
           }
           else if (compareObj(l_compareItem1, l_compareItem2) > 0)
           {
               l_intReturn = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
           }
           else
           {
               l_intReturn = 0;
           }
                
       }

       log.exiting(l_strMethodName);
       return l_intReturn;
   }
   
   /**
    * �iequals�̎����j <BR> 
    * <BR>
    * ���g�p�B  <BR>
    * false��ԋp����B
    * @@param arg0
    * @@return Boolean
    * @@roseuid 41B7F69C0142
    */
   public boolean equals(Object arg0) 
   {
       return false;
   }
   
   /**
    * ���Object�̔�r���s���B
    *
    * @@param l_obj1
    * @@param l_obj2
    * @@return 
    */
   private int compareObj(Object l_obj1, Object l_obj2)
   {        
       int l_intResult;
        
       if ((l_obj1 instanceof String) && (l_obj2 instanceof String))
       {
           l_intResult = ((String)l_obj1).compareTo((String)l_obj2);
       }
       else if ((l_obj1 instanceof Date) && (l_obj2 instanceof Date))
       {
           l_intResult = WEB3DateUtility.compareToMinute((Date)l_obj1, (Date)l_obj2);
       }
       else if ((l_obj1 instanceof Long) && (l_obj2 instanceof Long))
       {
           l_intResult = (((Long)l_obj1)).compareTo((Long)l_obj2);
       }
       else
       {
           throw new IllegalArgumentException("[Error]Parameter type is wrong! [l_obj1]=" + l_obj1 + " [l_obj2]" + l_obj2);
       }  
        
       return l_intResult;      
   }  
}
@
