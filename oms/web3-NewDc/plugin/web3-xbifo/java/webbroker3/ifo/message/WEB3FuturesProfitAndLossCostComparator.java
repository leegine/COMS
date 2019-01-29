head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesProfitAndLossCostComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�敨���v(���o�)Comparator(WEB3FuturesProfitAndLossCostComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 �L�R �ˎq(SRA) �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨���v(���o�)Comparator)<BR>
 */
public class WEB3FuturesProfitAndLossCostComparator implements Comparator
{

   /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
	        WEB3FuturesProfitAndLossCostComparator.class);

    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FuturesProfitAndLossCostComparator()
    {

    }

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     * @@return Void
     */
    public WEB3FuturesProfitAndLossCostComparator(String l_strOrderBy)
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
     * �@@�P�j����1�Ɩ���2�̑��v(���o�)�ɂ��Ĕ�r���s��<BR>
     * <BR>
     * �@@[�����w��̏ꍇ(this.orderBy == �h�����h)]<BR>
     * <BR>
     * �@@�E(����1.���v(���o�) < ����2.���v(���o�))�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * �@@�E(����1.���v(���o�) == ����2.���v(���o�))�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���v(���o�) > ����2.���v(���o�))�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h)]<BR>
     * <BR>
     * �@@�E(����1.���v(���o�) < ����2.���v(���o�))�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * �@@�E(����1.���v(���o�) == ����2.���v(���o�))�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���v(���o�) > ����2.���v(���o�))�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * @@param l_obj1<BR>
     * @@param l_obj2<BR>
     * @@return int
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblVal1 = 0;
        double l_dblVal2 = 0;
        
        String l_strAttr1 = null;
        String l_strAttr2 = null; 

        if (l_obj1 instanceof WEB3FuturesCloseMarginGroup
            && l_obj2 instanceof WEB3FuturesCloseMarginGroup)
        {
            //�����w���敨�ԍψꗗ�s�N���X�̏ꍇ
            l_strAttr1 = ((WEB3FuturesCloseMarginGroup)l_obj1).incomeCost;
            l_strAttr2 = ((WEB3FuturesCloseMarginGroup)l_obj2).incomeCost; 
        }
        else if (l_obj1 instanceof WEB3FuturesContractReferenceUnit
            && l_obj2 instanceof WEB3FuturesContractReferenceUnit)
        {
            //�����w���敨���ʏƉ�׃N���X
            l_strAttr1 = ((WEB3FuturesContractReferenceUnit)l_obj1).incomeCost;
            l_strAttr2 = ((WEB3FuturesContractReferenceUnit)l_obj2).incomeCost; 
        }
        else if (l_obj1 instanceof WEB3FuturesOptionsContractUnit
            && l_obj2 instanceof WEB3FuturesOptionsContractUnit)
        {
            //�����w���I�v�V�������ʖ��׃N���X
            l_strAttr1 = ((WEB3FuturesOptionsContractUnit)l_obj1).incomeCost;
            l_strAttr2 = ((WEB3FuturesOptionsContractUnit)l_obj2).incomeCost; 
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̌^���s���ł��B");
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
                        
            log.exiting(STR_METHOD_NAME);
            return l_intResult;            
        }
            
		l_dblVal1 = Double.parseDouble(l_strAttr1);
		l_dblVal2 = Double.parseDouble(l_strAttr2);
        
        // �l���������ꍇ
		if (l_dblVal1 == l_dblVal2)
		{
            log.exiting(STR_METHOD_NAME);
			return 0;
		}
		
		// �����\�[�g�̏ꍇ
		if (WEB3AscDescDef.ASC.equals(this.orderBy))
		{
			if (l_dblVal1 < l_dblVal2)
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
			if (l_dblVal1 < l_dblVal2)
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

    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B<BR>
     * @@param l_obj
     * @@return boolean
     */
    public boolean equals(Object l_obj)
    {
        //�X�[�p�[�N���X��equals���R�[������B
        if (l_obj instanceof WEB3FuturesProfitAndLossCostComparator)
        {
			WEB3FuturesProfitAndLossCostComparator l_comparator =
                (WEB3FuturesProfitAndLossCostComparator)l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}

@
