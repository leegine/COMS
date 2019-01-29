head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsSettlementStatusTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ϗ�ԋ敪Comparator(WEB3SettlementStatusTypeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ���o�� (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;


/**
 * (���Ϗ�ԋ敪Comparator)<BR>
 * ���Ϗ�ԋ敪Comparator�N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionsSettlementStatusTypeComparator implements Comparator
{
    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * ���Ϗ�ԋ敪Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_orderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��
     * @@return Void
     * @@roseuid 4085D0160399
     */
    public WEB3OptionsSettlementStatusTypeComparator(String l_strOrderBy)
    {
        if (l_strOrderBy == null ||
           (!l_strOrderBy.equals(WEB3AscDescDef.ASC) &&
            !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * �����A�~���̎w��ɂ��ƂÂ����Ϗ�ԋ敪�̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�̃I�u�W�F�N�g�̔���<BR>
     * <BR>
     * �@@instanceof�ɂāA�����̖���1�A����2���ȉ��̃N���X�̂ǂꂩ�𔻒肷��B<BR>
     * <BR>
     *     �����w���I�v�V�������ʏƉ�׃N���X<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �@@�P�j����1�Ɩ���2�̌��Ϗ�ԋ敪�ɂ��Ĕ�r���s��<BR>
     * <BR>
     * �@@[�����w��̏ꍇ(this.orderBy == �h�����h)]<BR>
     * <BR>
     * �@@�E(����1.���Ϗ�ԋ敪 < ����2.���Ϗ�ԋ敪)�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * �@@�E(����1.���Ϗ�ԋ敪 == ����2.���Ϗ�ԋ敪)�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���Ϗ�ԋ敪 > ����2.���Ϗ�ԋ敪)�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h)]<BR>
     * <BR>
     * �@@�E(����1.���Ϗ�ԋ敪 < ����2.���Ϗ�ԋ敪)�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * �@@�E(����1.���Ϗ�ԋ敪 == ����2.���Ϗ�ԋ敪)�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���Ϗ�ԋ敪 > ����2.���Ϗ�ԋ敪)�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * @@param l_obj1<BR>
     * @@param l_obj2<BR>
     * @@return int<BR>
     * @@roseuid 4085D01603C8
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        String l_strVal1 = null;
        String l_strVal2 = null;
        
        if ((l_obj1 instanceof WEB3OptionsContractReferenceUnit)          //�����w���I�v�V�������ʏƉ��
         && (l_obj2 instanceof WEB3OptionsContractReferenceUnit))
        {
            l_strVal1 = ((WEB3OptionsContractReferenceUnit)l_obj1).settlementState;
            l_strVal2 = ((WEB3OptionsContractReferenceUnit)l_obj2).settlementState;
        }
        else if((l_obj1 instanceof WEB3OptionsCloseMarginGroup)          //�����w���I�v�V�����ԍψꗗ�s
              && (l_obj2 instanceof WEB3OptionsCloseMarginGroup))
        {
            l_strVal1 = ((WEB3OptionsCloseMarginGroup)l_obj1).settlementState;
            l_strVal2 = ((WEB3OptionsCloseMarginGroup)l_obj2).settlementState;
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3OptionsContractReferenceUnit' �܂��� 'WEB3OptionsCloseMarginGroup' �ތ^�B");
        }

        if (l_strVal1 == null || l_strVal2 == null)
        {
            int l_intResult;
               
            if (l_strVal1 == null && l_strVal2 == null)
            {
                l_intResult = 0;
            }
            else if (l_strVal1 == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
                        
            return l_intResult;            
        }

        if (l_strVal1 == l_strVal2)
        {
            return 0;
        }
        else if (l_strVal1.compareTo(l_strVal2) > 0)
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
     * @@param l_obj
     * @@return boolean
     * @@roseuid 4085D01603D7
     */
    public boolean equals(Object l_obj)
    {
        if (l_obj instanceof WEB3OptionsSettlementStatusTypeComparator)
        {
            WEB3OptionsSettlementStatusTypeComparator l_comparator =
                (WEB3OptionsSettlementStatusTypeComparator) l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
