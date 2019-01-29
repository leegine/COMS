head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualRequestDivComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������@@Comparator�����N���X(WEB3MutualRequestDivComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 ���u�� (���{���u) �V�K�쐬�@@�@@U00367�̎b��Ή�
*/

package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * �������@@Comparator�����N���X<BR>
 *
 * @@author ���u�� (���{���u)
 * @@version 1.0
 */
public class WEB3MutualRequestDivComparator implements Comparator
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualRequestDivComparator.class);

    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * (�������@@Comparator)<BR>
     * �������@@Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��
     * @@roseuid 418B2F3900ED
     */
    public WEB3MutualRequestDivComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME =
            "WEB3MutualRequestDivComparator(String l_strOrderBy)";
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
     * �����A�~���̎w��ɂ��ƂÂ��������@@�̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�̃I�u�W�F�N�g�̔��� <BR>
     * <BR>
     *    instanceof�ɂāA�p�����[�^�̃I�u�W�F�N�gobj1�A
     *    obj2���ȉ��̃N���X�̂ǂ��炩�𔻒肷��B <BR>
     * <BR>
     *    ���M�����Ɖ���P�ʃN���X <BR>
     * <BR>
     * �Q�j��r <BR>
     * <BR>
     * �P�j�Ŕ��肵���N���X�̃C���X�^���Xobj1��obj2���ꂼ��̕\�����ɂ��� <BR>
     * <BR>
     *     �����w��̏ꍇ�A<BR>
     *     �p�����[�^.obj1�̐����敪���A<BR>
     *     �p�����[�^.obj2�̐����敪��菬�����ꍇ�͕��̐����A<BR>
     *     �������������ꍇ��0�A <BR>
     *     �p�����[�^.obj1�̐����敪���A<BR>
     *     �p�����[�^.obj2�̐����敪���傫���ꍇ�͐��̐�����ԋp���� <BR>
     * <BR> 
     *    �~���w��̏ꍇ�A <BR>
     *    �p�����[�^.obj1�̐����敪���A<BR>
     *    �p�����[�^.obj2�̐����敪��菬�����ꍇ�͐��̐����A <BR>
     *    �������������ꍇ��0�A <BR>
     *    �p�����[�^.obj1�̐����敪���A<BR>
     *    �p�����[�^.obj2�̐����敪���傫���ꍇ�͕��̐�����ԋp���� <BR>
     * <BR>
     *    ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     *  �Epublic boolean equals (Object obj)<BR>
     *      �i����Comparator�����N���X�Q�Ɓj<BR>
     * <BR>
     *    (*)<BR>
     *    �|�p�����[�^.obj1�̐������@@=null�̏ꍇ�A<BR>
     *       �����w��̏ꍇ�͕��̐�����ԋp����B<BR>
     * �@@    �p�����[�^.obj1�̐������@@=null�̏ꍇ�A<BR>
     *       �~���w��̏ꍇ�͐��̐�����ԋp����B<BR>
     *    �|�p�����[�^.obj2�̐������@@=null�̏ꍇ�A<BR>
     *       �����w��̏ꍇ�͐��̐�����ԋp����B<BR>
     * �@@    �p�����[�^.obj2�̐������@@=null�̏ꍇ�A<BR>
     *       �~���w��̏ꍇ�͕��̐�����ԋp����B<BR>
     *    �|�ǂ���� null �̏ꍇ�́A0��ԋp����B
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 418B2F3900FD
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);

        String l_strVal1 = null;
        String l_strVal2 = null;        

        if ((l_obj1 instanceof WEB3MutualOrderGroup)  //���M�����Ɖ���P��
            && (l_obj2 instanceof WEB3MutualOrderGroup))
        {
            log.debug("WEB3MutualOrderGroup: ENTER");
            l_strVal1 = ((WEB3MutualOrderGroup)l_obj1).sellBuyDiv;
            l_strVal2 = ((WEB3MutualOrderGroup)l_obj2).sellBuyDiv;
            log.debug("WEB3MutualOrderGroup: END");
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������" +
                "'WEB3MutualOrderGroup' �ތ^�B");
        }
        
        if (l_strVal1 == null && l_strVal2 == null)
        {
            return 0;
        }
        
        if (l_strVal1 == null)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))    //�����w��̏ꍇ
            {
                return -1;
            }
            else                                            //�~���w��̏ꍇ
            {
                return 1;
            }
        }

        if (l_strVal2 == null)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))    //�����w��̏ꍇ
            {
                return 1;
            }
            else                                            //�~���w��̏ꍇ
            {
                return -1;
            }
        }

        int l_intReturn = 0;
        if (l_strVal1.equals(l_strVal2))
        {
            l_intReturn = 0;
        }
        else if (l_strVal1.compareTo(l_strVal2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))    //�����w��̏ꍇ
            {
                l_intReturn = 1;
            }
            else                                            //�~���w��̏ꍇ
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
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }

    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B<BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 418B2F39010D
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }
}
@
