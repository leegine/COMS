head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesProfitAndLossComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�敨���vComparator(WEB3FuturesProfitAndLossComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20   ���Ō� (Sinocom) �V�K�쐬 
*/

package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨���vComparator)<BR>
 */
public class WEB3FuturesProfitAndLossComparator implements Comparator
{

    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesProfitAndLossComparator.class);

    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * @@roseuid 40F7AE0C0261
     */
    public WEB3FuturesProfitAndLossComparator()
    {

    }

    /**
     * �敨���vComparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     * @@return Void
     * @@roseuid 40CFEAFE0111
     */
    public WEB3FuturesProfitAndLossComparator(String l_strOrderBy)
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * �����A�~���̎w��ɂ��ƂÂ����v�̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�̃I�u�W�F�N�g�̔���<BR>
     * <BR>
     * �@@instanceof�ɂāA�����̖���1�A����2���ȉ��̃N���X�̂ǂꂩ�𔻒肷��B<BR>
     * <BR>
     *     �����w���敨�ԍψꗗ�s�N���X<BR>
     *     �����w���敨���ʏƉ�׃N���X<BR>
     *     ���ʖ��׃N���X<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �@@�P�j����1�Ɩ���2�̑��v�ɂ��Ĕ�r���s��<BR>
     * <BR>
     * �@@[�����w��̏ꍇ(this.orderBy == �h�����h)]<BR>
     * <BR>
     * �@@�E(����1.���v < ����2.���v)�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * �@@�E(����1.���v == ����2.���v)�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���v > ����2.���v)�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h)]<BR>
     * <BR>
     * �@@�E(����1.���v < ����2.���v)�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * �@@�E(����1.���v == ����2.���v)�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���v > ����2.���v)�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * @@param l_obj1<BR>
     * @@param l_obj2<BR>
     * @@return int
     * @@roseuid 40CFEAFE0120
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        
        //obj1�̌��敪
        double l_obj1Income = 0D;
        //obj2�̌��敪
        double l_obj2Income = 0D;
        
        String l_strAttr1 = null;
        String l_strAttr2 = null; 

        if (l_obj1 instanceof WEB3FuturesCloseMarginGroup
            && l_obj2 instanceof WEB3FuturesCloseMarginGroup)
        {
            //�����w���敨�ԍψꗗ�s�N���X�̏ꍇ
            l_strAttr1 = ((WEB3FuturesCloseMarginGroup)l_obj1).income;
            l_strAttr2 = ((WEB3FuturesCloseMarginGroup)l_obj2).income; 
        }
        else if (l_obj1 instanceof WEB3FuturesContractReferenceUnit
            && l_obj2 instanceof WEB3FuturesContractReferenceUnit)
        {
            //�����w���敨���ʏƉ�׃N���X
            l_strAttr1 = ((WEB3FuturesContractReferenceUnit)l_obj1).income;
            l_strAttr2 = ((WEB3FuturesContractReferenceUnit)l_obj2).income; 
        }
        else if (l_obj1 instanceof WEB3FuturesOptionsContractUnit
            && l_obj2 instanceof WEB3FuturesOptionsContractUnit)
        {
            //�����w���I�v�V�������ʖ��׃N���X
            l_strAttr1 = ((WEB3FuturesOptionsContractUnit)l_obj1).income;
            l_strAttr2 = ((WEB3FuturesOptionsContractUnit)l_obj2).income; 
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3FuturesCloseMarginGroup' �܂��� 'WEB3FuturesContractReferenceUnit' �܂��� 'WEB3FuturesOptionsContractUnit' �ތ^�B");
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
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
                        
            return l_intResult;            
        }
            
        l_obj1Income = Double.parseDouble(l_strAttr1);
        l_obj2Income = Double.parseDouble(l_strAttr2);

        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //�����w��̏ꍇ
            return Double.compare(l_obj1Income,l_obj2Income);
        }
        else
        {
            //�~���w��̏ꍇ
            return - Double.compare(l_obj1Income,l_obj2Income);
        }
    }

    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B<BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 40CFEAFE0123
     */
    public boolean equals(Object l_obj)
    {
        //�X�[�p�[�N���X��equals���R�[������B
        if (l_obj instanceof WEB3FuturesProfitAndLossComparator)
            //        if (l_obj instanceof  WEB3OpenDateComparator)
        {
            WEB3FuturesProfitAndLossComparator l_comparator =
                (WEB3FuturesProfitAndLossComparator)l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
