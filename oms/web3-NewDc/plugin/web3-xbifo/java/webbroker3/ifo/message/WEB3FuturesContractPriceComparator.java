head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesContractPriceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨���P��Comparator(WEBFuturesContractPriceComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/06 ������ (���u) �V�K�쐬
*/
package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨���P��Comparator)<BR>
 * �敨���P��Comparator�N���X<BR>
 * @@author ������
 * @@version 1.0 
 */
public class WEB3FuturesContractPriceComparator implements Comparator
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesContractPriceComparator.class);

    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * �敨���P��Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     * @@return Void
     * @@roseuid 407BBC2902FD
     */
    public WEB3FuturesContractPriceComparator(String l_strOrderBy)
    {
        if (l_strOrderBy == null
            || (!l_strOrderBy.equals(WEB3AscDescDef.ASC)
                && !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * �����A�~���̎w��ɂ��ƂÂ����P���̔�r���s���B<BR>
     * �P�j�@@�����̃L���X�g<BR> 
     * �@@�p�����[�^.����1�����2���A�����w���敨���ʖ��ׂɃL���X�g����B <BR> 
     * <BR>
     * �Q�j�@@��r<BR>
     * �@@�P�j����1�Ɩ���2�̌��P���ɂ��Ĕ�r���s��<BR>
     * �@@[�����w��̏ꍇ(this.orderBy == �h�����h)<BR>
     * �@@�E(����1.���P�� < ����2.���P��)�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * �@@�E(����1.���P�� == ����2.���P��)�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���P�� > ����2.���P��)�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h)]<BR>
     * �@@�E(����1.���P�� < ����2.���P��)�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * �@@�E(����1.���P�� == ����2.���P��)�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���P�� > ����2.���P��)�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * @@param l_obj1 - obj1<BR>
     * @@param l_obj2 - obj2<BR>
     * @@return int<BR>
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        String l_strMethodName = "compare(Object l_obj1, Object l_obj2)";
        log.entering(l_strMethodName);

        double l_dblVal1 = 0;
        double l_dblVal2 = 0;

        String l_strAttr1 = null;
        String l_strAttr2 = null;

        if ((l_obj1 instanceof WEB3FuturesOptionsContractUnit)
            && (l_obj2 instanceof WEB3FuturesOptionsContractUnit))
        {
            l_strAttr1 =
                ((WEB3FuturesOptionsContractUnit) l_obj1).contractPrice;
            l_strAttr2 =
                ((WEB3FuturesOptionsContractUnit) l_obj2).contractPrice;
        }
        else
        {
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3FuturesOptionsContractUnit'�@@" + "'�ތ^");
        }

        if (l_strAttr1 == null || l_strAttr2 == null)
        {
            int l_intResult;

            if (l_strAttr1 == null && l_strAttr2 == null)
            {
                l_intResult = 0;
            }
            else if (l_strAttr1 == null)
            {
                l_intResult =
                    (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult =
                    (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }

            return l_intResult;
        }

        l_dblVal1 = Double.parseDouble(l_strAttr1);
        l_dblVal2 = Double.parseDouble(l_strAttr2);

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

    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B<BR>
     * @@param l_obj - obj
     * @@return boolean
     * @@roseuid 407BBA72009C
     */
    public boolean equals(Object l_obj)
    {
        if (l_obj instanceof WEB3FuturesContractPriceComparator)
        {
            WEB3FuturesContractPriceComparator l_comparator =
                (WEB3FuturesContractPriceComparator) l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
