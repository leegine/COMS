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
filename	WEB3MarginContractTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p���敪Comparator(WEB3MarginContractTypeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
                   2005/01/06 �����a�� (SRA) JavaDoc�C��
*/

package webbroker3.equity;

import java.util.Comparator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.equity.WEB3MarginContractInfo;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.message.WEB3MarginCloseMarginGroup;

/**
 * �i�M�p���敪Comparator�j�B<BR>
 * <BR>
 * �M�p���敪Comparator
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginContractTypeComparator implements Comparator 
{
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3MarginContractTypeComparator.class);   
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 4142B3290248
     */
    public WEB3MarginContractTypeComparator() 
    {
     
    }
    
    /**
     * (�M�p���敪Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * ����.orderBy��this.orderBy�ɃZ�b�g����B<BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * <BR>
     * @@return WEB3MarginContractTypeComparator<BR>
     * @@roseuid 40F337F70204
     */
    public WEB3MarginContractTypeComparator(String l_strOrderBy) 
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * �icompare�̎����j <BR>
     * <BR>
     * ���敪�̔�r���s���B <BR>
     * <BR>
     * �P�j�@@�����̃I�u�W�F�N�g�̔���<BR>
     * instanceof�ɂāA�����̃I�u�W�F�N�g�������1�A<BR>
     * �������2���ȉ��̃N���X�̂ǂ��炩�𔻒肷��B <BR>
     * <BR>
     * �M�p����������N���X<BR>
     * �M�p������ψꗗ�s�N���X<BR>
     * <BR>
     * �Q�j�@@������cast <BR>
     * �@@�����̌������1�A�������2���A�P�j�Ŕ��肵���N���X�̌^��cast����B <BR>
     * <BR>
     * �R�j�@@��r <BR>
     * �@@�Q�j��cast�����������1�A�������2�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i�������1.���敪 < �������2.���敪�j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i�������1.���敪 == �������2.���敪�j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i�������1.���敪 > �������2.���敪�j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i�������1.���敪 < �������2.���敪�j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * �@@�E�i�������1.���敪 == �������2.���敪�j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i�������1.���敪 > �������2.���敪�j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * @@param l_obj1 - (�������1)<BR>
     * �M�p����������I�u�W�F�N�g1�A�܂��́A�M�p������ψꗗ�s�I�u�W�F�N�g1
     * @@param l_obj2 - (�������2)<BR>
     * �M�p����������I�u�W�F�N�g2�A�܂��́A�M�p������ψꗗ�s�I�u�W�F�N�g2
     * @@return int
     * @@roseuid 40F337F70214
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        //obj1�̌���
        String l_obj1ContractType = null;
        //obj2�̌���
        String l_obj2ContractType = null;
        
        //������cast
        if (l_obj1 instanceof WEB3MarginContractInfo && l_obj2 instanceof WEB3MarginContractInfo)
        {
            //�M�p����������N���X
            l_obj1ContractType = ((WEB3MarginContractInfo)l_obj1).contractType;
            l_obj2ContractType = ((WEB3MarginContractInfo)l_obj2).contractType;            
        }
        else if (l_obj1 instanceof WEB3MarginCloseMarginGroup && l_obj2 instanceof WEB3MarginCloseMarginGroup)
        {
            //�M�p������ψꗗ�s�N���X
            l_obj1ContractType = ((WEB3MarginCloseMarginGroup)l_obj1).contractType;
            l_obj2ContractType = ((WEB3MarginCloseMarginGroup)l_obj2).contractType;
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3MarginContractInfo' �܂��� 'WEB3MarginCloseMarginGroup' �ތ^�B");
        }
        
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //�����w��̏ꍇ
            if (Double.parseDouble(l_obj1ContractType) < Double.parseDouble(l_obj2ContractType))
            {
                return -1;
            }
            else if (Double.parseDouble(l_obj1ContractType) == Double.parseDouble(l_obj2ContractType))
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
            if (Double.parseDouble(l_obj1ContractType) < Double.parseDouble(l_obj2ContractType))
            {
                return 1;
            }
            else if (Double.parseDouble(l_obj1ContractType) == Double.parseDouble(l_obj2ContractType))
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
     * �iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B <BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 40F337F70217
     */
    public boolean equals(Object l_arg0) 
    {
        //�X�[�p�[�N���X��equals���R�[������B
        if (l_arg0 instanceof  WEB3MarginContractTypeComparator)
        {
            WEB3MarginContractTypeComparator l_comparator =
                    ( WEB3MarginContractTypeComparator) l_arg0;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false; 
    }
}
@
