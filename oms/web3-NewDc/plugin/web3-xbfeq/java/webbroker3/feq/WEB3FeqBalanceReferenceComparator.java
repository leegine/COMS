head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������c���Ɖ�Comparator(WEB3FeqBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬  
                 : 2005/08/01 �s�p(���u) ���r���[       
*/

package webbroker3.feq;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3FeqBalanceReferenceDetailUnit;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������c���Ɖ�Comparator)<BR>
 * �O�������c���Ɖ�Comparator
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqBalanceReferenceComparator implements Comparator 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBalanceReferenceComparator.class);
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;
    
    /**
     * (��r����)<BR>
     * compare�ɂĎg�p�����r����<BR>
     * <BR>
     * �ȉ��̍��ڂ��w�肳���B<BR>
     * �@@�O�������c���Ɖ��.�����R�[�h<BR>
     * �@@�O�������c���Ɖ��.���n�����R�[�h<BR>
     * �@@�O�������c���Ɖ��.�s��R�[�h<BR>
     * �@@�O�������c���Ɖ��.��������敪<BR>
     * �@@�O�������c���Ɖ��.�T�Z�]���z(�c������)<BR>
     * �@@�O�������c���Ɖ��.�T�Z�]�����v(�c������)<BR>
     */
    private String compareItem;
    
    /**
     * (�O�������c���Ɖ�Comparator)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * ������this.orderBy�A��r���ڂɃZ�b�g����B<BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     * @@param l_strCompareItem - (��r����)<BR>
     * compare�ɂĎg�p�����r����<BR>
     * <BR>
     * �ȉ��̍��ڂ��w�肳���B<BR>
     * �@@�O�������c���Ɖ��.�����R�[�h<BR>
     * �@@�O�������c���Ɖ��.���n�����R�[�h<BR>
     * �@@�O�������c���Ɖ��.�s��R�[�h<BR>
     * �@@�O�������c���Ɖ��.��������敪<BR>
     * �@@�O�������c���Ɖ��.�T�Z�]���z(�c������)<BR>
     * �@@�O�������c���Ɖ��.�T�Z�]�����v(�c������)<BR>
     * @@roseuid 42A8483B00B8
     */
    public WEB3FeqBalanceReferenceComparator(
        String l_strOrderBy, String l_strCompareItem) 
    {
        final String STR_METHOD_NAME =
            "WEB3FeqBalanceReferenceComparator(" +
                "String, String) ";
        log.entering(STR_METHOD_NAME);

        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy) &&  
                !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException(
                "�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        
        if (!WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_strCompareItem) && 
            !WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(
                l_strCompareItem) &&
            !WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(l_strCompareItem) && 
            !WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(l_strCompareItem) && 
            !WEB3FeqSortKeyItemNameDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(
                l_strCompareItem) && 
            !WEB3FeqSortKeyItemNameDef.ESTIMATED_APPRAISAL_PROFIT_LOSS_BALANCE_QUANTITY.equals(
                l_strCompareItem)
            )
        {
            throw new IllegalArgumentException(
                "�p�����[�^�̒l��'�����R�[�h'�A'���n�����R�[�h'�A'�s��R�[�h'�A'��������敪'�A" +
                "'�T�Z�]���z(�c������)'�A'�T�Z�]�����v(�c������)'�ȊO�ł��B");
        }
        
        this.orderBy = l_strOrderBy;
        this.compareItem = l_strCompareItem;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * ���ׂP�C�Q�ɂ��āAthis.��r���ڂŎw�肵�����ڂ̔�r���s���B<BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �p�����[�^.���ׂP����тQ���A�O�������c���Ɖ�׌^��cast����B<BR>
     * <BR>
     * �R�j�@@��r<BR>
     * �@@�Q�j��cast��������1�A����2�ɂ���<BR>
     * �@@this.��r���ڂɊY�����鍀�ڂ̒l���r���A���ʂ�ԋp����B<BR>
     * �@@���T�Z�]���P��(�c������)�A�T�Z�]�����v(�c������)�͐��l�Ȃ̂ŁA<BR>
     * �@@�@@double�^�Ŕ�r���邱�ƁB<BR>
     * �@@�@@��L�ȊO��String�^�Ŕ�r����B<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]�E<BR>
     * �i����1.��r���� < ����2.��r���ځj�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B<BR>
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B<BR>
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B<BR>
     * @@param l_obj1 - (���ׂP)<BR>
     * �O�������c���Ɖ�׃I�u�W�F�N�g�P
     * @@param l_obj2 - (���ׂQ)<BR>
     * �O�������c���Ɖ�׃I�u�W�F�N�g�Q
     * @@return int
     * @@roseuid 42A849890377
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);  
        
        //�P�j�@@������cast 
        //�p�����[�^.���ׂP����тQ���A�O�������c���Ɖ�׌^��cast����B 
        
        WEB3FeqBalanceReferenceDetailUnit l_balanceReferenceDetailUnit1;
        WEB3FeqBalanceReferenceDetailUnit l_balanceReferenceDetailUnit2;
        
        if (l_obj1 instanceof WEB3FeqBalanceReferenceDetailUnit && 
            l_obj2 instanceof WEB3FeqBalanceReferenceDetailUnit)
        {
            l_balanceReferenceDetailUnit1 = 
                (WEB3FeqBalanceReferenceDetailUnit)l_obj1;
            l_balanceReferenceDetailUnit2 =
                (WEB3FeqBalanceReferenceDetailUnit)l_obj2;
        }
        else
        {
            log.debug("�p�����[�^�̗ތ^���s��");

            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3FeqBalanceReferenceDetailUnit' �ތ^�B");
        }
        //�Q�j�@@��r 
        //�P�j��cast��������1�A����2�ɂ��� 
        //this.��r���ڂɊY�����鍀�ڂ̒l���r���A���ʂ�ԋp����B 
        //���T�Z�]���P��(�c������)�A�T�Z�]�����v(�c������)�͐��l�Ȃ̂ŁA 
        //�@@double�^�Ŕ�r���邱�ƁB 
        //�@@��L�ȊO��String�^�Ŕ�r����B
        String l_strItem1 = null;
        String l_strItem2 = null;
        int l_intCompareResult = 0;
        
        //�O�������c���Ɖ��.�T�Z�]���z(�c������) 
        if (WEB3FeqSortKeyItemNameDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(
                this.compareItem))         
        {
            l_strItem1 = 
                l_balanceReferenceDetailUnit1.estimatedAssetBalanceQuantity;
            
            l_strItem2 = 
                l_balanceReferenceDetailUnit2.estimatedAssetBalanceQuantity;
            
            //double�^�Ŕ�r����
            l_intCompareResult = 
                this.compareDouble(l_strItem1, l_strItem2);
            
            log.exiting(STR_METHOD_NAME);
            return l_intCompareResult;
        }
        //�O�������c���Ɖ��.�T�Z�]�����v(�c������)
        else if (WEB3FeqSortKeyItemNameDef.ESTIMATED_APPRAISAL_PROFIT_LOSS_BALANCE_QUANTITY.equals(
            this.compareItem))
        {
            l_strItem1 = 
                l_balanceReferenceDetailUnit1.estimatedAppraisalProfitLossBalanceQuantity;
            
            l_strItem2 = 
                l_balanceReferenceDetailUnit2.estimatedAppraisalProfitLossBalanceQuantity;
            
            //double�^�Ŕ�r����
            l_intCompareResult = 
                this.compareDouble(l_strItem1, l_strItem2);
            
            log.exiting(STR_METHOD_NAME);
            return l_intCompareResult;
        }
        //�O�������c���Ɖ��.�����R�[�h 
        else if (WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(
            this.compareItem))
        {
            l_strItem1 = l_balanceReferenceDetailUnit1.productCode;
            
            l_strItem2 = l_balanceReferenceDetailUnit2.productCode;
            
            //String�^�Ŕ�r����
            l_intCompareResult = 
                this.compareString(l_strItem1, l_strItem2);
        }       
        //�O�������c���Ɖ��.���n�����R�[�h 
        else if (WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(
            this.compareItem))
        {
            l_strItem1 = l_balanceReferenceDetailUnit1.localProductCode;
            
            l_strItem2 = l_balanceReferenceDetailUnit2.localProductCode;
            
            //String�^�Ŕ�r����
            l_intCompareResult = 
                this.compareString(l_strItem1, l_strItem2); 
        }
        //�O�������c���Ɖ��.�s��R�[�h 
        else if (WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(
            this.compareItem))
        {
            l_strItem1 = l_balanceReferenceDetailUnit1.marketCode;
            
            l_strItem2 = l_balanceReferenceDetailUnit2.marketCode;
            
            //String�^�Ŕ�r����
            l_intCompareResult = 
                this.compareString(l_strItem1, l_strItem2);
        }
        //�O�������c���Ɖ��.��������敪
        else if (WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(
            this.compareItem))
        {
            l_strItem1 = l_balanceReferenceDetailUnit1.taxType;
            
            l_strItem2 = l_balanceReferenceDetailUnit2.taxType;
            
            //String�^�Ŕ�r����
            l_intCompareResult = 
                this.compareString(l_strItem1, l_strItem2);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_intCompareResult;
    }
    
    /**
     * �icompareString�j<BR>
     * <BR>
     * String�^�Ŕ�r����B
     * [�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B<BR> 
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * <BR>
     * @@param l_strItem1
     * @@param l_strItem2
     * @@return int
     * @@roseuid 42A84989037A
     */
    protected int compareString(String l_strItem1, String l_strItem2)
    {
        final String STR_METHOD_NAME = "compareString(String, String)";
        log.entering(STR_METHOD_NAME);  
        
        //[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]         
        int l_intCompare;
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_strItem1 == null || l_strItem2 == null)
            {
                if (l_strItem1 == null && l_strItem2 != null)
                {
                    l_intCompare = -1;     
                }
                else if (l_strItem1 == null && l_strItem2 == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = 1;    
                }
            }
            else
            {
                //�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B 
                if (l_strItem1.compareTo(l_strItem2) < 0)
                {
                    l_intCompare = -1;    
                }
                //�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B
                else if (l_strItem1.compareTo(l_strItem2) == 0)
                {
                    l_intCompare = 0;    
                }
                //�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B
                else
                {
                    l_intCompare = 1;    
                }
            }
        }
        //[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] 
        else 
        {
            if (l_strItem1 == null || l_strItem2 == null)
            {
                if (l_strItem1 == null && l_strItem2 != null)
                {
                    l_intCompare = 1;     
                }
                else if (l_strItem1 == null && l_strItem2 == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = -1;    
                }
            }
            else
            {
                //�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B
                if (l_strItem1.compareTo(l_strItem2) < 0)
                {
                    l_intCompare = 1;    
                }
                //�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B
                else if (l_strItem1.compareTo(l_strItem2) == 0)
                {
                    l_intCompare = 0;    
                }
                //�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B
                else
                {
                    l_intCompare = -1;    
                } 
            }          
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_intCompare;
    }    
    
    /**
     * �icompareDouble�j<BR>
     * <BR>
     * double�^�Ŕ�r����B
     * [�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B<BR> 
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * <BR>s
     * @@param l_strItem1
     * @@param l_strItem2
     * @@return int
     * @@roseuid 42A84989037A
     */
    protected int compareDouble(String l_strItem1, String l_strItem2)
    {
        final String STR_METHOD_NAME = "compareDouble(String, String)";
        log.entering(STR_METHOD_NAME);  
        
        //[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]         
        int l_intCompare;
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_strItem1 == null || l_strItem2 == null)
            {
                if (l_strItem1 == null && l_strItem2 != null)
                {
                    l_intCompare = -1;     
                }
                else if (l_strItem1 == null && l_strItem2 == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = 1;    
                }
            }
            else
            {
                double l_dblItem1 = Double.parseDouble(l_strItem1);
                double l_dblItem2 = Double.parseDouble(l_strItem2);
                
                //�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B
                if (l_dblItem1 < l_dblItem2)
                {
                    l_intCompare = -1;    
                }
                //�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B 
                else if (l_dblItem1 == l_dblItem2)
                {
                    l_intCompare = 0;    
                }
                //�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B 
                else
                {
                    l_intCompare = 1;
                }
            }
        }
        //[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] 
        else 
        {
            if (l_strItem1 == null || l_strItem2 == null)
            {
                if (l_strItem1 == null && l_strItem2 != null)
                {
                    l_intCompare = 1;     
                }
                else if (l_strItem1 == null && l_strItem2 == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = -1;    
                }
            }
            else
            {
                double l_dblItem1 = Double.parseDouble(l_strItem1);
                double l_dblItem2 = Double.parseDouble(l_strItem2);
                
                //�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B 
                if (l_dblItem1 < l_dblItem2)
                {
                    l_intCompare = 1;    
                }
                //�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B 
                else if (l_dblItem1 == l_dblItem2)
                {
                    l_intCompare = 0;    
                }
                //�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B
                else
                {
                    l_intCompare = -1;    
                }
            }            
        }
        log.exiting(STR_METHOD_NAME);
        
        return l_intCompare;
    }    
    
    
    /**
     * �iequals�̎����j<BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B<BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 42A84989037A
     */
    public boolean equals(Object l_obj) 
    {
        return false;
    }
    

}
@
