head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ArraysUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �z�񃆁[�e�B���e�B(WEB3ArraysUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ����� (���u) �V�K�쐬
*/

package webbroker3.util;

import java.util.Comparator;

/**
 * �z��̃\�[�g�������s���֐��������[�e�B���e�B�N���X�B<BR>
 * <BR>
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3ArraysUtility 
{
   
    /**
     * �w�肳�ꂽ�R���p���[�^�̔z��ɂ��ƂÂ��ăI�u�W�F�N�g�z��̃\�[�g���s���B<BR>
     * <BR>
     * �p�����[�^.l_com�Ɋi�[����Ă���R���p���[�^����<BR>
     * �p�����[�^.l_obj�̃\�[�g���s��<BR>
     * <BR>
     * EX)<BR>
     * �p�����[�^.l_com���ȉ��ł������ꍇ�F<BR>
     * l_com[0] = ���敪Comparator<BR>
     * l_com[1] = ���N����Comparator<BR>
     * l_com[2] = ���vComparator<BR>
     * <BR>
     * 1.�p�����[�^.l_obj�����敪�Ń\�[�g����<BR>
     * <BR>
     * 2.1�Ń\�[�g���ꂽl_obj�ɑ΂��Č��敪�������I�u�W�F�N�g�����N�����Ń\�[�g����<BR>
     * <BR>
     * 3.2�Ń\�[�g���ꂽl_obj�ɑ΂��Č��N�����������I�u�W�F�N�g�𑹉v�Ń\�[�g����<BR>
     * @@param l_obj
     * @@param l_com
     * @@roseuid 407BDD7A02A3
     */
    public static void sort(Object[] l_obj, Comparator[] l_com) 
    {
        if (l_obj == null || l_com == null || l_com.length == 0) 
        {
            return;      
        }
             
        for (int i = 0; i < (l_obj.length - 1); i++) 
        {
            for (int j = i + 1; j < l_obj.length; j++) 
            {
                if (compare(l_obj[i], l_obj[j], l_com) > 0) 
                {                    
                    swap(l_obj, i, j);
                }
            }
        }
    }
    
    /**
     * Compare l_obj1 and l_obj2.
     * 
     * @@param l_obj1
     * @@param l_obj2
     * @@param l_com
     */
    private static int compare(Object l_obj1, Object l_obj2, Comparator[] l_com) 
    {
        
        int l_comResult = 0; 
        
        for (int i = 0; i < l_com.length; i++) 
        {
            if (l_com[i] == null) 
            {
                continue;
            }
               
            l_comResult = l_com[i].compare(l_obj1, l_obj2);
            if (l_comResult != 0) 
            {
                break;
            }
        }
        
        return l_comResult;
    }    
   
    /**
     * Swaps x[a] with x[b].
     * 
     * @@param l_x �z��
     * @@param l_a index1
     * @@param l_b index2
     */
    private static void swap(Object[] l_x, int l_a, int l_b) 
    {
        Object l_t = l_x[l_a];
        l_x[l_a] = l_x[l_b];
        l_x[l_b] = l_t;
    }
   
}
@
