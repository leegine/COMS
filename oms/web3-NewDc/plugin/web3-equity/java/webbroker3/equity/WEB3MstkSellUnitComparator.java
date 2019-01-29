head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellUnitComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t����Comparator(WEB3MstkSellUnitComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���C�g (���u) �V�K�쐬
                   2004/01/05 �����a�� (SRA) JavaDoc�C��
*/

package webbroker3.equity;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3MstkSellUnit;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�����~�j�������t����Comparator�j�B<BR>
 * <BR>
 * �����~�j�������t����Comparator
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellUnitComparator implements Comparator 
{
    
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MstkSellUnitComparator.class);
    /**
     * �iorderBy�j�B<BR>
     * <BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F����<BR>
     * �@@D�F�~��
     */
    private String orderBy;
    
    /**
     * �i��r���ځj�B<BR>
     * <BR>
     * �ȉ��̂ǂ��炩�̍��ڂ��w�肳���B<BR>
     * �@@�����~�j�������t����.�����R�[�h<BR>
     * �@@�����~�j�������t����.�s��R�[�h
     */
    private String compareItem;
    
    /**
     * 
     */
    public WEB3MstkSellUnitComparator() 
    {
     
    }
    
    /**
     * �i�����~�j�������t����Comparator�j�B<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * �����̒l��this.orderBy�Cthis.��r���ڂɃZ�b�g����B<BR>
     * @@param l_strOrderBy �iorderBy�j<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F����<BR>
     * �@@D�F�~��
     * @@param l_strCompareItem (��r����)<BR>
     * compare�ɂĎg�p�����r����<BR>
     * <BR>
     * �ȉ��̂ǂ��炩�̍��ڂ��w�肳���B<BR>
     * �@@�����~�j�������t����.�����R�[�h<BR>
     * �@@�����~�j�������t����.�s��R�[�h
     */
    public WEB3MstkSellUnitComparator(String l_strOrderBy, String l_strCompareItem) 
    {

        final String STR_METHOD_NAME =" WEB3MstkSellUnitComparator(String, String)";
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
            (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_strCompareItem) && !WEB3EquityKeyItemDef.TRADEMARKET.equals(l_strCompareItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("�p�����[�^�̒l��'�����R�[�h'�A'�s��R�[�h'�ȊO�ł�");
        }
        this.compareItem = l_strCompareItem;         
        
        log.debug("WEB3IpoOrderAcceptStatusComparator().....OK>>>>>");
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * �icompare�j�B<BR>
     * <BR>
     * �icompare�̎����j<BR>
     * <BR>
     * �����~�j�������t���ׂP�C�Q�ɂ��āA<BR>
     * this.��r���ڂŎw�肵�����ڂ̔�r���s���B  <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̊����~�j�������t���ׂP�A<BR>
     * �����~�j�������t���ׂQ�������~�j�������t���׌^��cast����B <BR>
     * <BR>
     * �Q�j�@@��r  <BR>
     * �@@�P�j��cast���������~�j�������t���ׂP�A<BR>
     * �����~�j�������t���ׂQ�ɂ��āA <BR>
     * this.��r���ڂɊY�����鍀�ڂ̒l���r���A���ʂ�ԋp����B<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �E�i�����~�j�������t���ׂP.��r���� < <BR>�����~�j�������t���ׂQ.��r���ځj�̏ꍇ�A<BR>
     *     ���̐����i-1�j��ԋp����B <BR>
     * �E�i�����~�j�������t���ׂP.��r���� = �����~�j�������t���ׂQ.��r���ځj�̏ꍇ�A<BR>
     *     0��ԋp����B <BR>
     * �E�i�����~�j�������t���ׂP.��r���� > �����~�j�������t���ׂQ.��r���ځj�̏ꍇ�A<BR>
     *     ���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �E�i�����~�j�������t���ׂP.��r���� < �����~�j�������t���ׂQ.��r���ځj�̏ꍇ�A<BR>
     *    ���̐����i1�j��ԋp����B <BR>
     * �E�i�����~�j�������t���ׂP.��r���� = �����~�j�������t���ׂQ.��r���ځj�̏ꍇ�A<BR>
     *    0��ԋp����B <BR>
     * �E�i�����~�j�������t���ׂP.��r���� > �����~�j�������t���ׂQ.��r���ځj�̏ꍇ�A<BR>
     *    ���̐����i-1�j��ԋp����B <BR>
     * @@param l_obj1 �i�����~�j�������t���ׂP�j<BR>
     * �����~�j�������t���׃I�u�W�F�N�g�P
     * @@param l_obj2 �i�����~�j�������t���ׂQ�j<BR>
     * �����~�j�������t���׃I�u�W�F�N�g�Q
     * @@return int
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkSellUnit l_unit1 = null;
        WEB3MstkSellUnit l_unit2 = null;

        if((l_obj1 instanceof WEB3MstkSellUnit) && (l_obj2 instanceof WEB3MstkSellUnit))
        {
            l_unit1 = (WEB3MstkSellUnit)l_obj1;
            l_unit2 = (WEB3MstkSellUnit)l_obj2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3MstkSellUnit'"); 
        }
        

        Comparable l_cmpKeyItem1;
        Comparable l_cmpKeyItem2;
        
        if(WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.compareItem))
        {
            l_cmpKeyItem1 = l_unit1.productCode;
            l_cmpKeyItem2 = l_unit2.productCode;
        }
        else
        {
            l_cmpKeyItem1 = Integer.valueOf(l_unit1.marketCode);
            l_cmpKeyItem2 = Integer.valueOf(l_unit2.marketCode);
        }
        
        if (l_cmpKeyItem1 == null && l_cmpKeyItem2 == null)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        if (l_cmpKeyItem1.compareTo(l_cmpKeyItem2) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        if(WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_cmpKeyItem2 == null || (l_cmpKeyItem1 != null && 
                l_cmpKeyItem1.compareTo(l_cmpKeyItem2) > 0))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if (l_cmpKeyItem1 == null || (l_cmpKeyItem2 != null &&
                l_cmpKeyItem1.compareTo(l_cmpKeyItem2) < 0))
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
        else
        {
            if (l_cmpKeyItem2 == null || (l_cmpKeyItem1 != null &&
                l_cmpKeyItem1.compareTo(l_cmpKeyItem2) > 0))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if (l_cmpKeyItem1 == null || (l_cmpKeyItem2 != null &&
                l_cmpKeyItem1.compareTo(l_cmpKeyItem2) < 0))
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
    
    /**
     * �iequals�j�B<BR>
     * <BR>
     * �iequals�̎����j<BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B<BR>
     * @@param l_arg0 �iarg0�j
     * @@return boolean
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
