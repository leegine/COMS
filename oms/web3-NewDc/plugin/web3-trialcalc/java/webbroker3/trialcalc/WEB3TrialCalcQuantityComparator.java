head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcQuantityComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X����Comparator(WEB3TrialCalcQuantityComparator.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc;

import java.math.BigDecimal;
import java.util.Comparator;

import webbroker3.util.WEB3LogUtility;

import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayProductUnit;

/**
 * �i�v�Z�T�[�r�X����Comparator�j<BR>
 * <BR>
 * WEB3TrialCalcQuantityComparator<BR>
 * @@author Dixit Deepankar
 * @@version 1.0
 */
public class WEB3TrialCalcQuantityComparator implements Comparator
{
    /**
     * Variable for Log
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcQuantityComparator.class);

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
     */
    private String orderBy;

    /**
     * @@roseuid 41E3C564036E
     */
    public WEB3TrialCalcQuantityComparator()
    {
    }

    /**
     * �i�v�Z�T�[�r�X����Comparator�j<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * ����.orderBy��this.orderBy�ɃZ�b�g����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Constructor<BR>
     * Set argument.orderBy to this.orderBy<BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F����<BR>
     * �@@D�F�~��<BR>
     * @@roseuid 41986BCD0189
     */
    public WEB3TrialCalcQuantityComparator(String l_strOrderBy)
    {
        this.orderBy = l_strOrderBy;
    }

    /**
     * �icompare�̎����j<BR>
     * <BR>
     * �����̔�r���s���B<BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@�����̖�������1�A��������2���A�v�Z�T�[�r�X�|�[�g�t�H���I�\���������׌^��cast��
     * ��B<BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast������������1�A��������2�ɂ��āA�ȉ��̔�����s���B<BR>
     * �@@����������1.���� == null�̏ꍇ�́A��������1.������0�Ƃ��Ĉ����B<BR>
     * �@@����������2.���� == null�̏ꍇ�́A��������2.������0�Ƃ��Ĉ����B<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i��������1.���� < ��������2.�����j�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i��������1.���� == ��������2.�����j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i��������1.���� > ��������2.�����j�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i��������1.���� < ��������2.�����j�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�E�i��������1.���� == ��������2.�����j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i��������1.���� > ��������2.�����j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * orderQuantity compare<BR>
     * <BR>
     * 1) Argument <BR>
     *  1-1) Cast argument l_productDetails1, l_productDetails2 into <BR>
     *       WEB3TrialCalcPortfolioDisplayProductUnit format<BR>
     * <BR>
     * 2) Compare <BR>
     * �@@�P�jCompare the following whith the arguments cast at 1-1)<BR>
      * If "l_productDetails1.orderQuantity == null"<BR>
     *        l_productDetails1.orderQuantity = 0<BR>
      * If "l_productDetails2.orderQuantity == null"<BR>
     *        l_productDetails2.orderQuantity = 0<BR>
     * <BR>
     * �@@[Ascending order specification (this.orderBy == "Ascend"�j] <BR>
      *If "l_productDetails1.orderQuantity < l_productDetails2.orderQuantity"<BR>
     *       return '-1'<BR>
      *If "l_productDetails1.orderQuantity ==
     * l_productDetails2.orderQuantity"<BR>
     *       return '0'<BR>
      *If "l_productDetails1.orderQuantity > l_productDetails2.orderQuantity"<BR>
     *       return '1'<BR>
     * <BR>
     * �@@[Descending order specification�ithis.orderBy == �hDescend�h�j] <BR>
     * �@@�EIf "l_productDetails1.orderQuantity < l_productDetails2.orderQuantity"<BR>
     *      return '1'<BR>
     * �@@�EIf "l_productDetails1.orderQuantity == l_productDetails2.orderQuantity"<BR>
     *      return '0'<BR>
     * �@@�EIf "l_productDetails1.orderQuantity > l_productDetails2.orderQuantity"<BR>
     *      return '-1'<BR>
     * @@param l_productDetails1 - �v�Z�T�[�r�X�|�[�g�t�H���I�\���������׃I�u�W�F�N�g�B
     * @@param l_productDetails2 - �v�Z�T�[�r�X�|�[�g�t�H���I�\���������׃I�u�W�F�N�g�B
     * @@return int
     * @@roseuid 41986BCD018B
     */
    public int compare(Object l_productDetails1, Object l_productDetails2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcPortfolioDisplayProductUnit l_displayProductUnit1 =
            (WEB3TrialCalcPortfolioDisplayProductUnit) l_productDetails1;
        WEB3TrialCalcPortfolioDisplayProductUnit l_displayProductUnit2 =
            (WEB3TrialCalcPortfolioDisplayProductUnit) l_productDetails2;

        String l_strOrderQuantity1 = l_displayProductUnit1.orderQuantity;
        String l_strOrderQuantity2 = l_displayProductUnit2.orderQuantity;

        // Check l_strOrderQuantity1 for null , null String
        if (l_strOrderQuantity1 == null || "".equals(l_strOrderQuantity1))
        {
            l_strOrderQuantity1 = "0";
        }

        // Check l_strOrderQuantity2 for null , null String
        if (l_strOrderQuantity2 == null || "".equals(l_strOrderQuantity2))
        {
            l_strOrderQuantity2 = "0";
        }

        BigDecimal l_bdOrderQuantity1 = new BigDecimal(l_strOrderQuantity1);
        BigDecimal l_bdOrderQuantity2 = new BigDecimal(l_strOrderQuantity2);

        // Check Ascending order specification (this.orderBy == "Ascend")
        if ("A".equals(this.orderBy))
        {
            log.exiting(STR_METHOD_NAME);
            return l_bdOrderQuantity1.compareTo(l_bdOrderQuantity2);
        } else
        {
            // Check Descending order specification�ithis.orderBy == �hDescend�h�j
            log.exiting(STR_METHOD_NAME);
            return l_bdOrderQuantity2.compareTo(l_bdOrderQuantity1);
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
     * @@param l_arg0 Object
     * @@return boolean
     * @@roseuid 41986BCD018E
     */
    public boolean equals(Object l_arg0)
    {
        final String STR_METHOD_NAME = "equals(Object)";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return false;
    }
}@
