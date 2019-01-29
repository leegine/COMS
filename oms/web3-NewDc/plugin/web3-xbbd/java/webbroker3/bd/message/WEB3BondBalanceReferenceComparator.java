head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.40.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ�Comparator (WEB3BondBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ���� (���u) �V�K�쐬
*/
package webbroker3.bd.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.bd.define.WEB3BondBalanceReferenceDetailUnitDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (���c���Ɖ�Comparator )<BR>
 * ���c���Ɖ�Comparator 
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3BondBalanceReferenceComparator implements Comparator
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceComparator.class);
    
    /**
     * (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>  
     *<BR>
     *�@@A�F���� <BR>
     *�@@D�F�~�� <BR>
     */
    private String orderBy;
    
    /**
     * (��r����)<BR>
     * ��r���� <BR>
     * �ȉ��̂��Âꂩ�̒l���Z�b�g�����B <BR>
     * �@@�E���c���Ɖ��.��� <BR>
     * �@@�E���c���Ɖ��.�����R�[�h <BR>
     * �@@�E���c���Ɖ��.�� <BR>
     * �@@�E���c���Ɖ��.���p�\���� <BR> 
     * �@@�E���c���Ɖ��.�ʉ�  <BR>
     * �@@�E���c���Ɖ��.�T�Z�]���z�i�~�݁j  <BR>
     * �@@�E���c���Ɖ��.�T�Z�]���z�i�O�݁j <BR> 
     * �@@�E���c���Ɖ��.���s��  <BR>
     * �@@�E���c���Ɖ��.���ғ�  <BR>
     */
    private String compareItem;
    
    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3BondBalanceReferenceComparator() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR> 
     * <BR>
     * �����̒l��this.orderBy�A��r���ڂɃZ�b�g����B<BR>
     * @@param l_strOrderBy - (orderBy) <BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B  <BR> 
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * @@param l_strCompareItem - (��r����)<BR>
     * ��r���� <BR>
     * <BR>
     * �ȉ��̂��Âꂩ�̒l���Z�b�g�����B<BR> 
     * �@@�E���c���Ɖ��.��� <BR>
     * �@@�E���c���Ɖ��.�����R�[�h <BR>
     * �@@�E���c���Ɖ��.�� <BR>
     * �@@�E���c���Ɖ��.���p�\���� <BR> 
     * �@@�E���c���Ɖ��.�ʉ�  <BR>
     * �@@�E���c���Ɖ��.�T�Z�]���z�i�~�݁j  <BR>
     * �@@�E���c���Ɖ��.�T�Z�]���z�i�O�݁j <BR> 
     * �@@�E���c���Ɖ��.���s��  <BR>
     * �@@�E���c���Ɖ��.���ғ�  <BR>
     * @@roseuid 41B80B1E0123<BR>
     */
    public WEB3BondBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem) 
    {
        final String STR_METHOD_NAME ="WEB3BondBalanceReferenceComparator(String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strOrderBy == null || 
            (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");
        }
        this.orderBy = l_strOrderBy;
        
        if (l_strCompareItem == null || 
            (!WEB3BondBalanceReferenceDetailUnitDef.BOND_CATEG_CODE.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_CODE.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_ISSUE_CODE.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.SELL_ABLE_QTY.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.CURRENCY_CODE.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.YEN_ESTIMATED_ASSET.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.FOREIGN_ESTIMATED_ASSET.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.ISSUE_DATE.equals(l_strCompareItem)
            && !WEB3BondBalanceReferenceDetailUnitDef.MATURITY_DATE.equals(l_strCompareItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̒l��'���'�A'�����R�[�h'�A'��'�A'����'�A'�T�Z�]���z'�ȊO�ł�" );
        }
        
        compareItem = l_strCompareItem;         
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icompare�̎����j <BR>
     * <BR>
     * ���ׂP�C�Q�ɂ��āAthis.��r���ڂŎw�肵�����ڂ̔�r���s���B  <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �p�����[�^.����1�����2���A���c���Ɖ�ׂ�cast����B<BR>
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
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * @@param l_obj1 - (����1) <BR>
     * ���c���Ɖ�׃I�u�W�F�N�g�P
     * @@param l_obj2 - (����2) 
     * ���c���Ɖ�׃I�u�W�F�N�g2
     * @@return int<BR>
     * @@roseuid 41B80B1E0133<BR>
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondBalanceReferenceDetailUnit l_unit1 = null;
        WEB3BondBalanceReferenceDetailUnit l_unit2 = null;

        if ((l_obj1 instanceof WEB3BondBalanceReferenceDetailUnit) && (l_obj2 instanceof WEB3BondBalanceReferenceDetailUnit))
        {
            l_unit1 = (WEB3BondBalanceReferenceDetailUnit)l_obj1;
            l_unit2 = (WEB3BondBalanceReferenceDetailUnit)l_obj2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̌^��WEB3BondBalanceReferenceDetailUnit�ȊO�ł��B"); 
        }
        
        // ��r���� == ��� or �����R�[�h or �� or �ʉ݂̏ꍇ
        if (WEB3BondBalanceReferenceDetailUnitDef.BOND_CATEG_CODE.equals(compareItem)
            || WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_CODE.equals(compareItem)
            || WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_ISSUE_CODE.equals(compareItem)
            || WEB3BondBalanceReferenceDetailUnitDef.CURRENCY_CODE.equals(compareItem))
        {
            String l_strKeyItem1 = null;
            String l_strKeyItem2 = null;
            
            if (WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_CODE.equals(compareItem))
            {
                // ��r���� = �����R�[�h
                l_strKeyItem1 = l_unit1.productCode;
                l_strKeyItem2 = l_unit2.productCode;
            }
            else if (WEB3BondBalanceReferenceDetailUnitDef.BOND_CATEG_CODE.equals(compareItem))
            {
                // ��r���� = ���
                l_strKeyItem1 = l_unit1.bondCategCode;
                l_strKeyItem2 = l_unit2.bondCategCode;
            }
            else if (WEB3BondBalanceReferenceDetailUnitDef.CURRENCY_CODE.equals(compareItem))
            {
                // ��r���� = �ʉ�
                l_strKeyItem1 = l_unit1.currencyCode;
                l_strKeyItem2 = l_unit2.currencyCode;
            }
            else
            {
                // ��r���� = ��
                l_strKeyItem1 = l_unit1.productIssueCode;
                l_strKeyItem2 = l_unit2.productIssueCode;
            }
            
            if (l_strKeyItem1 == null && l_strKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // �����\�[�g�̏ꍇ
            if (WEB3AscDescDef.ASC.equals(orderBy))
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
        else if (WEB3BondBalanceReferenceDetailUnitDef.SELL_ABLE_QTY.equals(compareItem)
            || WEB3BondBalanceReferenceDetailUnitDef.YEN_ESTIMATED_ASSET.equals(compareItem)
            || WEB3BondBalanceReferenceDetailUnitDef.FOREIGN_ESTIMATED_ASSET.equals(compareItem))
        {
            String l_strKeyItem1 = "";
            String l_strKeyItem2 = "";
            if (WEB3BondBalanceReferenceDetailUnitDef.SELL_ABLE_QTY.equals(compareItem))
            {
                l_strKeyItem1 = l_unit1.sellAbleQty;
                l_strKeyItem2 = l_unit2.sellAbleQty;
            }
            else if (WEB3BondBalanceReferenceDetailUnitDef.FOREIGN_ESTIMATED_ASSET.equals(compareItem))
            {
                l_strKeyItem1 = l_unit1.foreignEstimatedAsset;
                l_strKeyItem2 = l_unit2.foreignEstimatedAsset;
            }
            else
            {
                l_strKeyItem1 = l_unit1.yenEstimatedAsset;
                l_strKeyItem2 = l_unit2.yenEstimatedAsset;
            }
            
            if (l_strKeyItem1 == null && l_strKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // �����\�[�g�̏ꍇ
            if (l_strKeyItem1 == null || l_strKeyItem2 == null)
            {
                if (WEB3AscDescDef.ASC.equals(orderBy))
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
            if (WEB3AscDescDef.ASC.equals(orderBy))
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
        
        else
        {
            Date l_datKeyItem1 = null;
            Date l_datKeyItem2 = null;
            
            if (WEB3BondBalanceReferenceDetailUnitDef.ISSUE_DATE.equals(compareItem))
            {
                // ��r���� = ���s��
                l_datKeyItem1 = l_unit1.issueDate;
                l_datKeyItem2 = l_unit2.issueDate;
            }
            else
            {
                // ��r���� = ���ғ�
                l_datKeyItem1 = l_unit1.maturityDate;
                l_datKeyItem2 = l_unit2.maturityDate;
            }
            
            if (l_datKeyItem1 == null && l_datKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // �����\�[�g�̏ꍇ
            if (WEB3AscDescDef.ASC.equals(orderBy))
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
    }
    
    /**
     * �iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B<BR>
     * @@param arg0<BR>
     * @@return boolean<BR>
     */
    public boolean equals(Object arg0) 
    {
        return false;
    }
    
}
@
