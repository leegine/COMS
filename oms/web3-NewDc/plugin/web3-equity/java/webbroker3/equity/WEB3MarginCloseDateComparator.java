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
filename	WEB3MarginCloseDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����Comparator(WEB3MarginCloseDateComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
                   2005/01/06 �����a�� (SRA) JavaDoc�C��
*/

package webbroker3.equity;

import java.util.Comparator;
import java.util.Date;
import webbroker3.util.WEB3LogUtility;
import webbroker3.equity.WEB3MarginContractInfo;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;

/**
 * �i�M�p����Comparator�j�B<BR>
 * <BR>
 * �M�p����Comparator
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseDateComparator implements Comparator 
{
    /**
    * (���O���[�e�B���e�B)<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3MarginCloseDateComparator.class);    
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 4142B3280369
     */
    public WEB3MarginCloseDateComparator() 
    {
     
    }
    
    /**
     * (�M�p����Comparator)<BR>
     * �M�p����Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * ����.orderBy��this.orderBy�ɃZ�b�g����B <BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * @@return WEB3MarginCloseDateComparator
     * @@roseuid 40F338890010
     */
    public WEB3MarginCloseDateComparator(String l_strOrderBy) 
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
     * �����̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̌������1�A�������2���A�M�p����������̌^��cast����B <BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast�����������1�A�������2�ɂ���<BR> 
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i�������1.���� < �������2.�����j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i�������1.���� == �������2.�����j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i�������1.���� > �������2.�����j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i�������1.���� < �������2.�����j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * �@@�E�i�������1.���� == �������2.�����j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i�������1.���� > �������2.�����j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * @@param l_obj1 - (�������1)<BR>
     * �M�p����������I�u�W�F�N�g1
     * @@param l_obj2 - (�������2)<BR>
     * �M�p����������I�u�W�F�N�g2
     * @@return int
     * @@roseuid 40F28EBF01BE
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        //obj1�̌���
        Date l_obj1CloseDate = null;
        //obj2�̌���
        Date l_obj2CloseDate = null;
        
        //������cast
        if (l_obj1 instanceof WEB3MarginContractInfo && l_obj2 instanceof WEB3MarginContractInfo)
        {
            //�M�p����������N���X
            l_obj1CloseDate = ((WEB3MarginContractInfo)l_obj1).closeDate;
            l_obj2CloseDate = ((WEB3MarginContractInfo)l_obj2).closeDate;            
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3MarginContractInfo' �ތ^�B");
        }
        
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //�����w��̏ꍇ
            if (WEB3DateUtility.compareToDay(l_obj1CloseDate, l_obj2CloseDate) < 0)
            {
                return -1;
            }
            else if (WEB3DateUtility.compareToDay(l_obj1CloseDate, l_obj2CloseDate) == 0)
            {
                return 0;
            }
            else{
                return 1;
            }
        }
        else
        {
            //�~���w��̏ꍇ
            if (WEB3DateUtility.compareToDay(l_obj1CloseDate, l_obj2CloseDate) < 0)
            {
                return 1;
            }
            else if (WEB3DateUtility.compareToDay(l_obj1CloseDate, l_obj2CloseDate) == 0)
            {
                return 0;
            }
            else{
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
     * @@roseuid 40F28EC403A2
     */
    public boolean equals(Object l_arg0) 
    {
        //�X�[�p�[�N���X��equals���R�[������B
        if (l_arg0 instanceof  WEB3MarginCloseDateComparator)
        {
            WEB3MarginCloseDateComparator l_comparator =
                    ( WEB3MarginCloseDateComparator) l_arg0;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false; 
    }
}
@