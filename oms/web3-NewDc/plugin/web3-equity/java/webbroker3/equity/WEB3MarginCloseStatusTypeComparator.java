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
filename	WEB3MarginCloseStatusTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p���Ϗ�ԋ敪Comparator(WEB3MarginSettlementStatusTypeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
                   2005/01/06 �����a�� (SRA) JavaDoc�C��
*/
package webbroker3.equity;

import java.util.Comparator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3AscDescDef;

/**
 * �i�M�p���Ϗ�ԋ敪Comparator�j�B<BR>
 * <BR>
 * �M�p���Ϗ�ԋ敪Comparator
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseStatusTypeComparator implements Comparator 
{
    /**
    * (���O���[�e�B���e�B)<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3MarginCloseStatusTypeComparator.class);   
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 4142B32B0133
     */
    public WEB3MarginCloseStatusTypeComparator() 
    {
     
    }
    
    /**
     * (�M�p���Ϗ�ԋ敪Comparator)<BR>
     * �M�p���Ϗ�ԋ敪Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * ����.orderBy��this.orderBy�ɃZ�b�g����B<BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * @@return WEB3MarginSettlementStatusTypeComparator
     * @@roseuid 40F5E7E002E0
     */
    public WEB3MarginCloseStatusTypeComparator(String l_strOrderBy) 
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
     * ���Ϗ�ԋ敪�̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̌������1�A�������2���A�M�p����������̌^��cast����B <BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast�����������1�A�������2�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i�������1.���Ϗ�ԋ敪 < �������2.���Ϗ�ԋ敪�j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i�������1.���Ϗ�ԋ敪 == �������2.���Ϗ�ԋ敪�j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i�������1.���Ϗ�ԋ敪 > �������2.���Ϗ�ԋ敪�j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B<BR> 
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i�������1.���Ϗ�ԋ敪 < �������2.���Ϗ�ԋ敪�j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * �@@�E�i�������1.���Ϗ�ԋ敪 == �������2.���Ϗ�ԋ敪�j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i�������1.���Ϗ�ԋ敪 > �������2.���Ϗ�ԋ敪�j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B<BR>
     * @@param l_obj1 - (�������1)<BR>
     * �M�p����������I�u�W�F�N�g1
     * @@param l_obj2 - (�������2)<BR>
     * �M�p����������I�u�W�F�N�g2
     * @@return int
     * @@roseuid 40F5E7E0030F
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        //obj1�̌���
        String l_obj1ClosingStatusType = null;
        //obj2�̌���
        String l_obj2ClosingStatusType = null;
        
        //������cast
        if (l_obj1 instanceof WEB3MarginContractInfo && l_obj2 instanceof WEB3MarginContractInfo)
        {
            //�M�p����������N���X
            l_obj1ClosingStatusType = ((WEB3MarginContractInfo)l_obj1).closingStatusType;
            l_obj2ClosingStatusType = ((WEB3MarginContractInfo)l_obj2).closingStatusType;            
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3MarginContractInfo' �ތ^�B");
        }
        
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //�����w��̏ꍇ
            if (Double.parseDouble(l_obj1ClosingStatusType) < Double.parseDouble(l_obj2ClosingStatusType))
            {
                return -1;
            }
            else if (Double.parseDouble(l_obj1ClosingStatusType) == Double.parseDouble(l_obj2ClosingStatusType))
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
            if (Double.parseDouble(l_obj1ClosingStatusType) < Double.parseDouble(l_obj2ClosingStatusType))
            {
                return 1;
            }
            else if (Double.parseDouble(l_obj1ClosingStatusType) == Double.parseDouble(l_obj2ClosingStatusType))
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
     * @@roseuid 40F5E7E0033E
     */
    public boolean equals(Object l_arg0) 
    {
        //�X�[�p�[�N���X��equals���R�[������B
        if (l_arg0 instanceof  WEB3MarginCloseStatusTypeComparator)
        {
            WEB3MarginCloseStatusTypeComparator l_comparator =
                    ( WEB3MarginCloseStatusTypeComparator) l_arg0;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false; 
    }
}
@
