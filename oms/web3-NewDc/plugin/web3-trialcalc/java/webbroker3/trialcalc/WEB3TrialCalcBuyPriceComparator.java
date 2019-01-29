head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcBuyPriceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X���t�P��Comparator(WEB3TrialCalcBuyPriceComparator.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc;

import java.math.BigDecimal;
import java.util.Comparator;

import webbroker3.util.WEB3LogUtility;

import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayProductUnit;

/**
 * �i�v�Z�T�[�r�X���t�P��Comparator�j<BR>
 * <BR>
 * �v�Z�T�[�r�X����Comparator�N���X�B<BR>
 * <BR>
 * WEB3TrialCalcBuyPriceComparator<BR>
 * <BR>
 * @@author Babu Prasad
 * @@version 1.0
 */
public class WEB3TrialCalcBuyPriceComparator implements Comparator
{
    /**
     * Variable for Log
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcBuyPriceComparator.class);

   /**
    * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
    * <BR>
    * �@@A�F����<BR>
    * �@@D�F�~��<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Flag that specifies ascending order (:asc) and descending order (:desc). <BR>
    * <BR>
    *  A : Ascend<BR>
    *  D : Descend<BR>
    * <BR>
    */
   private String orderBy;

   /**
    * @@roseuid 41E3C567011C
    */
   public WEB3TrialCalcBuyPriceComparator()
   {

   }

   /**
    * �i�v�Z�T�[�r�X���t�P��Comparator�j<BR>
    * <BR>
    * �R���X�g���N�^�B<BR>
    * <BR>
    * ����.orderBy��this.orderBy�ɃZ�b�g����B<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Constructor<BR>
    * Set argument.orderBy to this.orderBy<BR>
    * <BR>
    * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
    * <BR>
    * �@@A�F����<BR>
    * �@@D�F�~��<BR>
    * @@roseuid 41986BD101C7
    */
   public WEB3TrialCalcBuyPriceComparator(String l_strOrderBy)
   {
       this.orderBy = l_strOrderBy;
   }

   /**
    * �icompare�̎����j<BR>
    * <BR>
    * �P�j�@@������cast<BR>
    * �@@�����̖�������1�A��������2���A�v�Z�T�[�r�X�|�[�g�t�H���I�\���������׌^��cast��
    * ��B<BR>
    * <BR>
    * �Q�j�@@��r <BR>
    * �@@�P�j��cast������������1�A��������2�ɂ��āA�ȉ��̔�����s���B<BR>
    * �@@����������1.���t�P�� == null�̏ꍇ�́A��������1.���t�P����0�Ƃ��Ĉ����B<BR>
    * �@@����������2.���t�P�� == null�̏ꍇ�́A��������2.���t�P����0�Ƃ��Ĉ����B<BR>
    * <BR>
    * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
    * �@@�E�i��������1.���t�P�� <
    * ��������2.���t�P���j�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
    * �@@�E�i��������1.���t�P�� == ��������2.���t�P���j�̏ꍇ�A0��ԋp����B <BR>
    * �@@�E�i��������1.���t�P�� >
    * ��������2.���t�P���j�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
    * <BR>
    * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
    * �@@�E�i��������1.���t�P�� <
    * ��������2.���t�P���j�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
    * �@@�E�i��������1.���t�P�� == ��������2.���t�P���j�̏ꍇ�A0��ԋp����B <BR>
    * �@@�E�i��������1.���t�P�� >
    * ��������2.���t�P���j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * buyPrice compare<BR>
    * <BR>
    * 1) Argument <BR>
    *  1-1) Cast argument l_productDetails1, l_productDetails2 into <BR>
    *       WEB3TrialCalcPortfolioDisplayProductUnit format<BR>
    * <BR>
    * 2) Compare <BR>
    * �@@�P�jCompare the following whith the arguments cast at 1-1)<BR>
     * If "l_productDetails1.buyPrice == null"<BR>
    *        l_productDetails1.buyPrice = 0<BR>
     * If "l_productDetails2.buyPrice == null"<BR>
    *        l_productDetails2.buyPrice = 0<BR>
    * <BR>
    * �@@[Ascending order specification (this.orderBy == "Ascend"�j] <BR>
     *If "l_productDetails1.buyPrice < l_productDetails2.buyPrice"<BR>
    *       return '-1'<BR>
     *If "l_productDetails1.buyPrice == l_productDetails2.buyPrice"<BR>
    *       return '0'<BR>
     *If "l_productDetails1.buyPrice > l_productDetails2.buyPrice"<BR>
    *       return '1'<BR>
    * <BR>
    * �@@[Descending order specification�ithis.orderBy == �hDescend�h�j] <BR>
    * �@@�EIf "l_productDetails1.buyPrice < l_productDetails2.buyPrice"<BR>
    *      return '1'<BR>
    * �@@�EIf "l_productDetails1.buyPrice == l_productDetails2.buyPrice"<BR>
    *      return '0'<BR>
    * �@@�EIf "l_productDetails1.buyPrice > l_productDetails2.buyPrice"<BR>
    *      return '-1'<BR>
    * <BR>
    * @@param l_productDetails1 - �v�Z�T�[�r�X�|�[�g�t�H���I�\���������׃I�u�W�F�N�g�B
    * @@param l_productDetails2 - �v�Z�T�[�r�X�|�[�g�t�H���I�\���������׃I�u�W�F�N�g�B
    * @@return int
    * @@roseuid 41986BD101D7
    */
   public int compare(Object l_productDetails1, Object l_productDetails2)
   {
       final String STR_METHOD_NAME = "compare(Object, Object)";
       log.entering(STR_METHOD_NAME);

       WEB3TrialCalcPortfolioDisplayProductUnit l_displayProductUnit1 =
           (WEB3TrialCalcPortfolioDisplayProductUnit) l_productDetails1;
       WEB3TrialCalcPortfolioDisplayProductUnit l_displayProductUnit2 =
           (WEB3TrialCalcPortfolioDisplayProductUnit) l_productDetails2;

       String l_strBuyPrice1 = l_displayProductUnit1.buyPrice;
       String l_strBuyPrice2 = l_displayProductUnit2.buyPrice;

       // Check l_strBuyPrice1 for null , Empty String
       if (l_strBuyPrice1 == null || "".equals(l_strBuyPrice1))
       {
           l_strBuyPrice1 = "0";
       }

       // Check l_strBuyPrice2 for null , Empty String
       if (l_strBuyPrice2 == null || "".equals(l_strBuyPrice2))
       {
           l_strBuyPrice2 = "0";
       }

       BigDecimal l_bdBuyPrice1 = new BigDecimal(l_strBuyPrice1);
       BigDecimal l_bdBuyPrice2 = new BigDecimal(l_strBuyPrice2);

       // Check Ascending order specification (this.orderBy == "Ascend")
       if ("A".equals(this.orderBy))
       {
           log.exiting(STR_METHOD_NAME);
           return l_bdBuyPrice1.compareTo(l_bdBuyPrice2);
       } else
       {
           // Check Descending order specification�ithis.orderBy == �hDescend�h�j
           log.exiting(STR_METHOD_NAME);
           return l_bdBuyPrice2.compareTo(l_bdBuyPrice1);
       }
   }

   /**
    * �iequals�̎����j <BR>
    * <BR>
    * ���g�p�B <BR>
    * false��ԋp����B <BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Unused .  False is returned. <BR>
    * <BR>
    * @@param l_arg0 Object
    * @@return boolean
    * @@roseuid 41986BD101DA
    */
   public boolean equals(Object l_arg0)
   {
       final String STR_METHOD_NAME = "equals(Object)";
       log.entering(STR_METHOD_NAME);

       log.exiting(STR_METHOD_NAME);
       return false;
   }
}
@
