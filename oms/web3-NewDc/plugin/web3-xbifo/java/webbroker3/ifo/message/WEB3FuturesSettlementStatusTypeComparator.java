head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesSettlementStatusTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�敨���Ϗ�ԋ敪Comparator(WEB3FuturesSettlementStatusTypeComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20   ���Ō� (Sinocom) �V�K�쐬 
*/
package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨���Ϗ�ԋ敪Comparator)
 */
public class WEB3FuturesSettlementStatusTypeComparator implements Comparator
{

    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesSettlementStatusTypeComparator.class);
        
    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * @@roseuid 40C07547038A
     */
    public WEB3FuturesSettlementStatusTypeComparator()
    {

    }

    /**
     * �敨���Ϗ�ԋ敪Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_orderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��
     * @@return Void
     * @@roseuid 4085D0160399
     */
    public WEB3FuturesSettlementStatusTypeComparator(String l_strOrderBy)
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy) 
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
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
     *     �����w���敨���ʏƉ�׃N���X<BR>
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
     * @@return int
     * @@roseuid 4085D01603C8
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);

        //obj1�̌��敪
        String l_obj1SettlementState = null;
        //obj2�̌��敪
        String l_obj2SettlementState = null;
        
        if (l_obj1 instanceof WEB3FuturesContractReferenceUnit 
            && l_obj2 instanceof WEB3FuturesContractReferenceUnit)
        {
            //�����w���敨���ʏƉ�׃N���X
            l_obj1SettlementState = ((WEB3FuturesContractReferenceUnit)l_obj1).settlementState;
            l_obj2SettlementState = ((WEB3FuturesContractReferenceUnit)l_obj2).settlementState;
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3FuturesContractReferenceUnit'�ތ^�B");
        }     

        if (l_obj1SettlementState == null || l_obj2SettlementState == null)
        {
            int l_intResult;
               
            if (l_obj1SettlementState == null && l_obj2SettlementState == null)
            {
                l_intResult = 0;
            }
            else if (l_obj1SettlementState == null)
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
            return l_obj1SettlementState.compareTo(l_obj2SettlementState);
        }
        else
        {
            //�~���w��̏ꍇ
            return -(l_obj1SettlementState.compareTo(l_obj2SettlementState));
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
        //�X�[�p�[�N���X��equals���R�[������B
        if (l_obj instanceof  WEB3FuturesSettlementStatusTypeComparator)
        {
            WEB3FuturesSettlementStatusTypeComparator l_comparator =
                (WEB3FuturesSettlementStatusTypeComparator) l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false; 
    }
}
@
