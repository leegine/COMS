head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCurrentDisplayOrderComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ݕ\����Comparator(WEB3MutualCurrentDisplayOrderComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ݕ\����Comparator)<BR>
 * ���ݕ\����Comparator�����N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualCurrentDisplayOrderComparator implements Comparator 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCurrentDisplayOrderComparator.class);
    
    /**
     * (orderBy)<BR>
     * A�F����<BR>
     * D�F�~��
     */
    private String orderBy;
    
    /**
     * (���ݕ\����Comparator)<BR>
     * ���ݕ\����Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��
     * @@roseuid 4153BF500037
     */
    public WEB3MutualCurrentDisplayOrderComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME =
            "WEB3CurrentDisplayOrderComparator(String l_strOrderBy)";
        log.entering(STR_METHOD_NAME);  
        
        if (l_strOrderBy == null || 
            (!l_strOrderBy.equals(WEB3AscDescDef.ASC) && 
            !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException(
                "�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }        
        this.orderBy = l_strOrderBy;
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (compare)<BR>
     * �����A�~���̎w��ɂ��ƂÂ����ݕ\�����̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^���Ǘ��Җ����\�������o�^�ꗗ�s�N���X�ň������L���X�g����B<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �P�j�Ŕ��肵���N���X�̃C���X�^���Xobj1��obj2���ꂼ��̕\�����ɂ���<BR>
     * <BR>
     *    �����w��̏ꍇ�A<BR>
     *    �p�����[�^.obj1�̌��ݕ\�������A�p�����[�^.obj2�̌��ݕ\�������<BR>
     *    �������ꍇ�͕��̐����A�������������ꍇ��0�A<BR>
     *    �p�����[�^.obj1�̌��ݕ\�������A�p�����[�^.obj2�̌��ݕ\�������<BR>
     *    �傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     *    �~���w��̏ꍇ�A<BR>
     *    �p�����[�^.obj1�̌��ݕ\�������A�p�����[�^.obj2�̌��ݕ\�������<BR>
     *    �������ꍇ�͐��̐����A�������������ꍇ��0�A<BR>
     *    �p�����[�^.obj1�̌��ݕ\�������A�p�����[�^.obj2�̌��ݕ\�������<BR>
     *    �傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     *    ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 4153BF500046
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�p�����[�^���Ǘ��Җ����\�������o�^�ꗗ�s�N���X�ň������L���X�g����B
        Integer l_intVal1 = null;
        Integer l_intVal2 = null;
        if (l_obj1 instanceof WEB3MutualDisplayOrderGroup && 
            l_obj2 instanceof WEB3MutualDisplayOrderGroup)
        {
			if(((WEB3MutualDisplayOrderGroup) l_obj1).displayOrder == null)
			{
				l_intVal1 = null;
			}
			else
			{
			   l_intVal1 = 
					Integer.valueOf(
						((WEB3MutualDisplayOrderGroup) l_obj1).displayOrder);
			}
			if(((WEB3MutualDisplayOrderGroup) l_obj2).displayOrder == null)
			{
				l_intVal2 = null;
			}
			else
			{
				l_intVal2 = 
					Integer.valueOf(
						((WEB3MutualDisplayOrderGroup) l_obj2).displayOrder);
			}
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������" 
                + "'WEB3MutualDisplayOrderGroup'�ތ^�B");
        }
        //�Q�j��r
        int l_intReturn = 0;
        if (l_intVal1 == null || l_intVal2 == null)
        {
               
            if (l_intVal1 == null && l_intVal2 == null)
            {
                l_intReturn = 0;
            }
            else if (l_intVal1 == null)
            {
                l_intReturn = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intReturn = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }            
                        
        }    
        else
        {
            if (l_intVal1.equals(l_intVal2))
            {
                l_intReturn = 0;
            }
            else if (l_intVal1.compareTo(l_intVal2) > 0)
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))      //�����w��̏ꍇ
                {
                    l_intReturn = 1;
                }
                else                                             //�~���w��̏ꍇ
                {
                    l_intReturn = -1;
                }
            }
            else
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))    //�����w��̏ꍇ
                {
                    l_intReturn = -1;
                }
                else                                            //�~���w��̏ꍇ
                {
                    l_intReturn = 1;
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
    
    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B
     * @@param l_obj
     * @@return boolean
     * @@roseuid 4153C0820279
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }
}
@
