head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesContractDivisionComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨���敪Comparator(WEB3FuturesContractDivisionComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20   ���Ō� (Sinocom) �V�K�쐬 
*/

package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨���敪Comparator)<BR>
 */
public class WEB3FuturesContractDivisionComparator implements Comparator
{

    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesContractDivisionComparator.class);

    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * @@roseuid 40F7AE14033C
     */
    public WEB3FuturesContractDivisionComparator()
    {

    }

    /**
     * (�敨���敪Comparator)<BR>
     * �敨���敪Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     * @@return Void
     * @@roseuid 40CFEC9301CC
     */
    public WEB3FuturesContractDivisionComparator(String l_strOrderBy)
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * �����A�~���̎w��ɂ��ƂÂ����敪�̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�̃I�u�W�F�N�g�̔���<BR>
     * <BR>
     * �@@instanceof�ɂāA�����̖���1�A����2���ȉ��̃N���X�̂ǂꂩ�𔻒肷��B<BR>
     * <BR>
     *     �����w���敨�ԍψꗗ�s�N���X<BR>
     *     �����w���敨���ʏƉ�׃N���X<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �@@�P�j����1�Ɩ���2�̌��敪�ɂ��Ĕ�r���s��<BR>
     * <BR>
     * �@@[�����w��̏ꍇ(this.orderBy == �h�����h)]<BR>
     * <BR>
     * �@@�E(����1.���敪 < ����2.���敪)�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * �@@�E(����1.���敪 == ����2.���敪)�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���敪 > ����2.���敪)�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h)]<BR>
     * <BR>
     * �@@�E(����1.���敪 < ����2.���敪)�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * �@@�E(����1.���敪 == ����2.���敪)�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���敪 > ����2.���敪)�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * @@param l_obj1<BR>
     * @@param l_obj2<BR>
     * @@return int
     * @@roseuid 40CFEC9301CE
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        //obj1�̌��敪
        String l_obj1ContractType = null;
        //obj2�̌��敪
        String l_obj2ContractType = null;

        if (l_obj1 instanceof WEB3FuturesCloseMarginGroup
            && l_obj2 instanceof WEB3FuturesCloseMarginGroup)
        {
            l_obj1ContractType = ((WEB3FuturesCloseMarginGroup)l_obj1).contractType;
            l_obj2ContractType = ((WEB3FuturesCloseMarginGroup)l_obj2).contractType;
        }
        else if (l_obj1 instanceof WEB3FuturesContractReferenceUnit
            && l_obj2 instanceof WEB3FuturesContractReferenceUnit)
        {
            l_obj1ContractType = ((WEB3FuturesContractReferenceUnit)l_obj1).contractType;
            l_obj2ContractType = ((WEB3FuturesContractReferenceUnit)l_obj2).contractType;
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3FuturesCloseMarginGroup' �܂��� 'WEB3FuturesContractReferenceUnit' �ތ^�B");
        }

        if (l_obj1ContractType == null || l_obj2ContractType == null)
        {
            int l_intResult;
               
            if (l_obj1ContractType == null && l_obj2ContractType == null)
            {
                l_intResult = 0;
            }
            else if (l_obj1ContractType == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
                        
            return l_intResult;            
        }

        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //�����w��̏ꍇ
            return l_obj1ContractType.compareTo(l_obj2ContractType);
        }
        else
        {
            //�~���w��̏ꍇ
            return - (l_obj1ContractType.compareTo(l_obj2ContractType));
        }
    }

    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B<BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 40CFEC9301DE
     */
    public boolean equals(Object l_obj)
    {
        //�X�[�p�[�N���X��equals���R�[������B
        if (l_obj instanceof WEB3FuturesContractDivisionComparator)
            //        if (l_obj instanceof  WEB3OpenDateComparator)
        {
            WEB3FuturesContractDivisionComparator l_comparator =
                (WEB3FuturesContractDivisionComparator)l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
