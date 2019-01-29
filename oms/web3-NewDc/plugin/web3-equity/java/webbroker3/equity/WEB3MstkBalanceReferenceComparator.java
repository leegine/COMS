head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����c���Ɖ�Comparator(WEB3MstkBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3MstkBalanceReferenceDetailUnit;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�����~�j�����c���Ɖ�Comparator�j�B<BR>
 * <BR>
 * �����~�j�����c���Ɖ�Comparator<BR>
 */
public class WEB3MstkBalanceReferenceComparator implements Comparator 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBalanceReferenceComparator.class);
        
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
     * �ȉ��̍��ڂ��w�肳���B<BR>
     * �@@�����~�j�����c���Ɖ��.�����R�[�h<BR>
     * �@@�����~�j�����c���Ɖ��.�����敪<BR>
     * <BR>
     */
    private String compareItem;
    
    /**
     * @@roseuid 4206CD4903B5<BR>
     */
    public WEB3MstkBalanceReferenceComparator() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �����̒l��this.orderBy�Cthis.��r���ڂɃZ�b�g����B<BR>
     * @@param orderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F����  <BR>
     * �@@D�F�~�� <BR>
     * @@param l_strOrderBy - (��r����) compare�ɂĎg�p�����r����<BR>
     * <BR>
     * �ȉ��̍��ڂ��w�肳���B<BR>
     * �@@�����~�j�����c���Ɖ��.�����R�[�h<BR>
     * �@@�����~�j�����c���Ɖ��.�����敪<BR>
     * <BR>
     * @@return WEB3MstkBalanceReferenceComparator<BR>
     * @@roseuid 41C66866001E<BR>
     */
    public WEB3MstkBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem ) 
    {
        final String STR_METHOD_NAME ="WEB3MstkBalanceReferenceComparator(String, String)";
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
            && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_strCompareItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̒l��'�����R�[�h'�A'�����敪'�ȊO�ł�" );
        }
        this.compareItem = l_strCompareItem;         
        
        log.debug("WEB3EquityBalanceReferenceComparator().....OK>>>>>");
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * �icompare�̎����j <BR>
     * <BR>
     * �����~�j�����c���Ɖ�ׂP�C�Q�ɂ��āAthis.��r���ڂŎw�肵�����ڂ̔�r���s��<BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̊����~�j�����c���Ɖ�ׂP�A�����~�j�����c���Ɖ�ׂQ�������~�j�����c��<BR>
     * <BR>
     * �Ɖ�׌^��cast����B <BR>
     * <BR>
     * �Q�j�@@��r  <BR>
     * �@@�P�j��cast���������~�j�����c���Ɖ�ׂP�A�����~�j�����c���Ɖ�ׂQ�ɂ��āA<BR>
     * <BR>
     * <BR>
     * this.��r���ڂɊY�����鍀�ڂ̒l���r���A���ʂ�ԋp����B<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i�����~�j�����c���Ɖ�ׂP.��r���� < <BR>
     * �����~�j�����c���Ɖ�ׂQ.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i�����~�j�����c���Ɖ�ׂP.��r���� = <BR>
     * �����~�j�����c���Ɖ�ׂQ.��r���ځj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i�����~�j�����c���Ɖ�ׂP.��r���� > <BR>
     * �����~�j�����c���Ɖ�ׂQ.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i�����~�j�����c���Ɖ�ׂP.��r���� < <BR>
     * �����~�j�����c���Ɖ�ׂQ.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�E�i�����~�j�����c���Ɖ�ׂP.��r���� = <BR>
     * �����~�j�����c���Ɖ�ׂQ.��r���ځj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i�����~�j�����c���Ɖ�ׂP.��r���� > <BR>
     * �����~�j�����c���Ɖ�ׂQ.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * <BR>
     * @@param l_obj1 - (�����~�j�����c���Ɖ�ׂP) �����~�j�����c���Ɖ�׃I�u�W�F�N�g�P<BR>
     * @@param l_obj2 - (�����~�j�����c���Ɖ�ׂQ) �����~�j�����c���Ɖ�׃I�u�W�F�N�g�Q<BR>
     * @@return int<BR>
     * @@roseuid 41C66866003E<BR>
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkBalanceReferenceDetailUnit l_unit1 = null;
        WEB3MstkBalanceReferenceDetailUnit l_unit2 = null;

        if((l_obj1 instanceof WEB3MstkBalanceReferenceDetailUnit) && (l_obj2 instanceof WEB3MstkBalanceReferenceDetailUnit))
        {
            l_unit1 = (WEB3MstkBalanceReferenceDetailUnit)l_obj1;
            l_unit2 = (WEB3MstkBalanceReferenceDetailUnit)l_obj2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̌^��WEB3MstkBalanceReferenceDetailUnit�ȊO�ł��B"); 
        }
        

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
    
    /**
     * �iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B <BR>
     * <BR>
     * @@param arg0<BR>
     * @@return boolean<BR>
     * @@roseuid 41C66866005D<BR>
     */
    public boolean equals(Object arg0) 
    {
        return false;
    }
}
@
