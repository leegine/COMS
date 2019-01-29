head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractUnitAppraisalProfitOrLossComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������_�]�����vComparator(WEB3MarginContractUnitAppraisalProfitOrLossComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
                   2005/01/06 �����a�� (SRA) JavaDoc�C��
*/

package webbroker3.equity;

import java.util.Comparator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.common.define.WEB3AscDescDef;

/**
 * �i��������_�]�����vComparator�j�B<BR>
 * <BR>
 * ��������_�]�����vComparator
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginContractUnitAppraisalProfitOrLossComparator implements Comparator 
{
    /**
    * (���O���[�e�B���e�B)<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3MarginContractUnitAppraisalProfitOrLossComparator.class);    

    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 4142B32902F2
     */
    public WEB3MarginContractUnitAppraisalProfitOrLossComparator() 
    {
     
    }
    
    /**
     * (��������_�]�����vComparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * ����.orderBy��this.orderBy�ɃZ�b�g����B <BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * @@return WEB3MarginContractUnitEvaluationIncomeComparator
     * @@roseuid 41060BA801CA
     */
    public WEB3MarginContractUnitAppraisalProfitOrLossComparator(String l_strOrderBy) 
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     *�icompare�̎����j <BR>
     * <BR>
     * �]�����v�̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̌�������1�A��������2���A�M�p����������׌^��cast����B <BR>
     * <BR>
     * �R�j�@@��r <BR>
     * �@@�Q�j��cast������������1�A��������2�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i��������1.�]�����v < ��������2.�]�����v�j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i��������1.�]�����v == ��������2.�]�����v�j�̏ꍇ�A0��ԋp����B<BR> 
     * �@@�E�i��������1.�]�����v > ��������2.�]�����v�j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i��������1.�]�����v < ��������2.�]�����v�j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * �@@�E�i��������1.�]�����v == ��������2.�]�����v�j�̏ꍇ�A0��ԋp����B<BR> 
     * �@@�E�i��������1.�]�����v > ��������2.�]�����v�j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * @@param l_obj1 - (��������1)<BR>
     * �M�p����������׃I�u�W�F�N�g
     * 
     * @@param l_obj2 - (��������2)<BR>
     * �M�p����������׃I�u�W�F�N�g
     * @@return int
     * @@roseuid 41060BA801CC
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        //obj1�̌���
        String l_obj1AppraisalProfitLoss = null;
        //obj2�̌���
        String l_obj2AppraisalProfitLoss = null;
        
        //������cast
        if (l_obj1 instanceof WEB3MarginContractUnit && l_obj2 instanceof WEB3MarginContractUnit)
        {
            //�M�p����������׌^�N���X
            l_obj1AppraisalProfitLoss = ((WEB3MarginContractUnit)l_obj1).appraisalProfitLoss;
            l_obj2AppraisalProfitLoss = ((WEB3MarginContractUnit)l_obj2).appraisalProfitLoss;            
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3MarginContractUnit' �ތ^�B");
        }
        
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //�����w��̏ꍇ
            if (Double.parseDouble(l_obj1AppraisalProfitLoss) < Double.parseDouble(l_obj2AppraisalProfitLoss))
            {
                return -1;
            }
            else if (Double.parseDouble(l_obj1AppraisalProfitLoss) == Double.parseDouble(l_obj2AppraisalProfitLoss))
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
            if (Double.parseDouble(l_obj1AppraisalProfitLoss) < Double.parseDouble(l_obj2AppraisalProfitLoss))
            {
                return 1;
            }
            else if (Double.parseDouble(l_obj1AppraisalProfitLoss) == Double.parseDouble(l_obj2AppraisalProfitLoss))
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
     *�iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B <BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 41060BA801DA
     */
    public boolean equals(Object l_arg0) 
    {
        //�X�[�p�[�N���X��equals���R�[������B
        if (l_arg0 instanceof  WEB3MarginContractUnitAppraisalProfitOrLossComparator)
        {
            WEB3MarginContractUnitAppraisalProfitOrLossComparator l_comparator =
                    ( WEB3MarginContractUnitAppraisalProfitOrLossComparator) l_arg0;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false; 
    }
}
@
