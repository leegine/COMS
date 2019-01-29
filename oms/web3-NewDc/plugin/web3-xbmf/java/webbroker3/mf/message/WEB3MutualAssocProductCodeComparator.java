head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualAssocProductCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M��������R�[�hComparator(WEB3MutualAssocProductCodeComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M��������R�[�hComparator)<BR>
 * ���M��������R�[�hComparator�����N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualAssocProductCodeComparator implements Comparator 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualAssocProductCodeComparator.class);
    
    /**
     * (orderBy)<BR>
     * A�F����<BR>
     * D�F�~��
     */
    private String orderBy;
    
    /**
     * (���M��������R�[�hComparator)<BR>
     * ���M��������R�[�hComparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��
     * @@roseuid 4153C0820259
     */
    public WEB3MutualAssocProductCodeComparator(String l_strOrderBy)
    {
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * (compare)<BR>
     * �����A�~���̎w��ɂ��ƂÂ����M��������R�[�h�̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^���Ǘ��Җ����\�������o�^�ꗗ�s�N���X�ň������L���X�g����B<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �P�j�Ŕ��肵���N���X�̃C���X�^���Xobj1��obj2���ꂼ��̓��M��������R�[�h�ɂ�    
     * ����<BR>
     * <BR>
     *     �����w��̏ꍇ�A<BR>
     *     �p�����[�^.obj1�̓��M��������R�[�h���A<BR>
     *     �p�����[�^.obj2�̓��M��������R�[�h���<BR>
     *     �������ꍇ�͕��̐����A�������������ꍇ��0�A<BR>
     *     �p�����[�^.obj1�̓��M��������R�[�h���A<BR>
     *     �p�����[�^.obj2�̓��M��������R�[�h���<BR>
     *     �傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     *     �~���w��̏ꍇ�A<BR>
     *     �p�����[�^.obj1�̓��M��������R�[�h���A<BR>
     *     �p�����[�^.obj2�̓��M��������R�[�h���<BR>
     *     �������ꍇ�͐��̐����A�������������ꍇ��0�A<BR>
     *     �p�����[�^.obj1�̓��M��������R�[�h���A<BR>
     *     �p�����[�^.obj2�̓��M��������R�[�h���<BR>
     *     �傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     *     ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 4153C0820269
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�p�����[�^���Ǘ��Җ����\�������o�^�ꗗ�s�N���X�ň������L���X�g����B
        String l_strVal1 = null;
        String l_strVal2 = null;
        if (l_obj1 instanceof WEB3MutualDisplayOrderGroup && 
            l_obj2 instanceof WEB3MutualDisplayOrderGroup)
        {
            l_strVal1 = 
                ((WEB3MutualDisplayOrderGroup) l_obj1).mutualAssocProductCode;
            l_strVal2 = 
                ((WEB3MutualDisplayOrderGroup) l_obj2).mutualAssocProductCode;
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������" 
                + "'WEB3MutualDisplayOrderGroup'�ތ^�B");
        }
        
        //�Q�j��r
        int l_intReturn = 0;
        
        if (l_strVal1 == null || l_strVal2 == null)
        {
               
            if (l_strVal1 == null && l_strVal2 == null)
            {
                l_intReturn = 0;
            }
            else if (l_strVal1 == null)
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
            //�������������ꍇ��0
            if (l_strVal1.equals(l_strVal2))
            {
                l_intReturn = 0;
            }
            
            //�p�����[�^.obj1�̓��M��������R�[�h���p�����[�^.obj2��
            //���M��������R�[�h���傫���ꍇ�͐��̐�����ԋp����
            else if (l_strVal1.compareTo(l_strVal2) > 0)
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
            
            //�p�����[�^.obj1�̓��M��������R�[�h���p�����[�^.obj2��
            //���M��������R�[�h��菬�����ꍇ�͕��̐���
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
