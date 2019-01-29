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
filename	WEB3MarginContractUnitContractPriceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������_���P��Comparator(WEB3MarginContractUnitContractPriceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/20 �������F (SRA) �V�K�쐬
*/

package webbroker3.equity;

import java.util.Comparator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.common.define.WEB3AscDescDef;

/**
 * �i��������_���P��Comparator�j�B<BR>
 * <BR>
 * ��������_���P��Comparator
 * @@author �������F
 * @@version 1.0
 */
public class WEB3MarginContractUnitContractPriceComparator implements Comparator 
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3MarginContractUnitContractPriceComparator.class);
    
    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;
    
    /**
     * (��������_���P��Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * ����.orderBy��this.orderBy�ɃZ�b�g����B <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * @@return WEB3MarginContractUnitContractPriceComparator
     */
    public WEB3MarginContractUnitContractPriceComparator(String l_strOrderBy) 
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy) &&
            !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException(
                "�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * (compare)<BR>
     * �icompare�̎����j<BR>
     * <BR>
     * ���P���̔�r���s���B<BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@�����̌�������1�A��������2���A�M�p����������׌^��cast����B<BR>
     * <BR>
     * �R�j�@@��r<BR>
     * �@@�Q�j��cast������������1�A��������2�ɂ���<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�i��������1.���P�� �� ��������2.���P���j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�E�i��������1.���P�� == ��������2.���P���j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�i��������1.���P�� �� ��������2.���P���j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�i��������1.���P�� �� ��������2.���P���j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�E�i��������1.���P�� == ��������2.���P���j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�i��������1.���P�� �� ��������2.���P���j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * <BR>
     * @@param l_objFirst - (��������1)<BR>
     * �M�p����������׃I�u�W�F�N�g
     * @@param l_objSecond - (��������2)<BR>
     * �M�p����������׃I�u�W�F�N�g
     * @@return int
     */
    public int compare(Object l_objFirst, Object l_objSecond) 
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblFirstContractPrice = 0D;
        double l_dblSecondContractPrice = 0D;
        if (l_objFirst instanceof WEB3MarginContractUnit &&
            l_objSecond instanceof WEB3MarginContractUnit)
        {
            l_dblFirstContractPrice =
                Double.parseDouble(((WEB3MarginContractUnit)l_objFirst).contractPrice);
            l_dblSecondContractPrice =
                Double.parseDouble(((WEB3MarginContractUnit)l_objSecond).contractPrice);            
        }
        else
        {
            throw new IllegalArgumentException(
                "�p�����[�^�͐M�p����������׃N���X�ł���܂���B");
        }
        
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //�����w��̏ꍇ
            if (l_dblFirstContractPrice < l_dblSecondContractPrice)
            {
                return -1;
            }
            else if (l_dblFirstContractPrice == l_dblSecondContractPrice)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            //�~���w��̏ꍇ
            if (l_dblFirstContractPrice < l_dblSecondContractPrice)
            {
                return 1;
            }
            else if (l_dblFirstContractPrice == l_dblSecondContractPrice)
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }
    }
    
    /**
     * (equals)
     * �iequals�̎����j<BR>
     * <BR>
     * ���g�p�B<BR> 
     * false��ԋp����B<BR>
     * <BR>
     * @@param l_object - (��r�ΏƂ̃I�u�W�F�N�g)<BR>
     * @@return boolean
     */
    public boolean equals(Object l_object)
    {
        return false;
    }
}
@
