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
filename	WEB3EquityBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������c���Ɖ�Comparator(WEB3EquityBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3EquityBalanceReferenceDetailUnit;
import webbroker3.util.WEB3LogUtility;


/**
 * �i���������c���Ɖ�Comparator�j�B<BR>
 * <BR>
 * ���������c���Ɖ�Comparator<BR>
 */
public class WEB3EquityBalanceReferenceComparator implements Comparator 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceComparator.class);
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F����  <BR>
     * �@@D�F�~��<BR>
     */
    private String orderBy;
    
    /**
     * (��r����)<BR>
     * <BR>
     * �ȉ��̍��ڂ��w�肳���B<BR>
     * �@@���������c���Ɖ��.�����R�[�h<BR>
     * �@@���������c���Ɖ��.�����敪<BR>
     * �@@���������c���Ɖ��.�T�Z�]���z(�c������)<BR>
     * �@@���������c���Ɖ��.�T�Z�]�����v(�c������)<BR>
     */
    private String compareItem;
    
    /**
     * @@roseuid 4206CC4B00D7<BR>
     */
    public WEB3EquityBalanceReferenceComparator() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �����̒l��this.orderBy�Cthis.��r���ڂɃZ�b�g����B<BR>
     * @@param l_strOrderBy - (����)�i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * @@param l_strCompareItem - (��r����) compare�ɂĎg�p�����r����<BR>
     * <BR>
     * �ȉ��̍��ڂ��w�肳���B<BR>
     * �@@���������c���Ɖ��.�����R�[�h<BR>
     * �@@���������c���Ɖ��.�����敪<BR>
     * �@@���������c���Ɖ��.�T�Z�]���z(�c������)<BR>
     * �@@���������c���Ɖ��.�T�Z�]�����v(�c������)<BR>
     * <BR>
     * @@return <BR>
     * webbroker3.equity.�����T�[�r�X���f��.���������c���Ɖ�.���������c���Ɖ�Comparator<BR>
     * @@roseuid 41B80B1E0123<BR>
     */
    public WEB3EquityBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem) 
    {
        final String STR_METHOD_NAME ="WEB3EquityBalanceReferenceComparator(String, String)";
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
            && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.ESTIMATED_INCOME_BALANCE_QUANTITY.equals(l_strCompareItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̒l��'�����R�[�h'�A'�����敪'�A'�T�Z�]���z�i�c�������j'�A'�T�Z�]�����v�i�c�������j'�ȊO�ł�" );
        }
        this.compareItem = l_strCompareItem;         
        
        log.debug("WEB3EquityBalanceReferenceComparator().....OK>>>>>");
	    log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * �icompare�̎����j <BR>
     * <BR>
     * ���ׂP�C�Q�ɂ��āAthis.��r���ڂŎw�肵�����ڂ̔�r���s���B  <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �p�����[�^.����1�����2���A���������c���Ɖ�׌^��cast����B<BR>
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
     * @@param l_obj1 - (����1) ���������c���Ɖ�׃I�u�W�F�N�g1<BR>
     * @@param l_obj2 - (����2) ���������c���Ɖ�׃I�u�W�F�N�g2<BR>
     * @@return int<BR>
     * @@roseuid 41B80B1E0133<BR>
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityBalanceReferenceDetailUnit l_unit1 = null;
        WEB3EquityBalanceReferenceDetailUnit l_unit2 = null;

        if((l_obj1 instanceof WEB3EquityBalanceReferenceDetailUnit) && (l_obj2 instanceof WEB3EquityBalanceReferenceDetailUnit))
        {
            l_unit1 = (WEB3EquityBalanceReferenceDetailUnit)l_obj1;
            l_unit2 = (WEB3EquityBalanceReferenceDetailUnit)l_obj2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̌^��WEB3EquityBalanceReferenceDetailUnit�ȊO�ł��B"); 
        }
        
        // ��r���� == �����R�[�h or �����敪�̏ꍇ
        if (this.compareItem.equals(WEB3EquityKeyItemDef.PRODUCTCODE)
            || this.compareItem.equals(WEB3EquityKeyItemDef.ACCOUNTTYPE))
        {
	        String l_strKeyItem1 = null;
	        String l_strKeyItem2 = null;
	        
            if(WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.compareItem))
            {
                // ��r���� = �����R�[�h
                l_strKeyItem1 = l_unit1.productCode;
                l_strKeyItem2 = l_unit2.productCode;
            }
            else
            {
                // ��r���� = �����敪
                l_strKeyItem1 = l_unit1.taxType;
                l_strKeyItem2 = l_unit2.taxType;
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
        // ��r���� == �T�Z�]���z�i�c�������j or �T�Z�]�����v�i�c�������j�̏ꍇ
	    else
        {
            String l_strKeyItem1 = "";
            String l_strKeyItem2 = "";
            if (WEB3EquityKeyItemDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(this.compareItem))
            {
                l_strKeyItem1 = l_unit1.estimatedAssetBalanceQuantity;
                l_strKeyItem2 = l_unit2.estimatedAssetBalanceQuantity;
            }
            else if (WEB3EquityKeyItemDef.ESTIMATED_INCOME_BALANCE_QUANTITY.equals(this.compareItem))
            {
                l_strKeyItem1 = l_unit1.estimatedAppraisalProfitLossBalanceQuantity;
                l_strKeyItem2 = l_unit2.estimatedAppraisalProfitLossBalanceQuantity;
            }
            
            if (l_strKeyItem1 == null && l_strKeyItem2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            
            // �����\�[�g�̏ꍇ
            if (l_strKeyItem1 == null || l_strKeyItem2 == null)
            {
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
     * false��ԋp����B<BR>
     * @@param arg0<BR>
     * @@return boolean<BR>
     * @@roseuid 41B80B1E0152<BR>
     */
    public boolean equals(Object arg0) 
    {
        return false;
    }
}
@
