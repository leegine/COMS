head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.42.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondTraderComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (��)����Comparator(WEB3AdminBondTraderComparator.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/9/26 ���g(���u) �V�K�쐬 ���f��No.108
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((��)����Comparator)<BR>
 * (��)����Comparator<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminBondTraderComparator implements Comparator
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondTraderComparator.class);

    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * ((��)����Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     */
    public WEB3AdminBondTraderComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME =
            " WEB3AdminBondTraderComparator(String)";
        log.entering(STR_METHOD_NAME);

        if (l_strOrderBy == null
            || (!l_strOrderBy.equals(WEB3AscDescDef.ASC)
            && !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            log.error("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
            "�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B<BR>
     * @@param l_obj - (obj)<BR>
     * ��r�Ώۂ̎Q�ƃI�u�W�F�N�g<BR>
     * @@return boolean
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }

    /**
     * �icompare�̎����j<BR>
     * <BR>
     * �����A�~���̎w��ɂ��ƂÂ����҃R�[�h�i�r�n�m�`�q�j�̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�����Ǘ��Ғ������Ɖ�s�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j��r<BR>
     * �@@�P�j�ŃL���X�g��������1�A����2�̈��҃R�[�h�i�r�n�m�`�q�j���r���A���ʂ�ԋp����B<BR>
     * <BR>
     * �@@�����w��̏ꍇ�A<BR>
     * �@@�@@����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j��菬�����ꍇ�A<BR>
     * �@@�@@�@@�@@�@@���̐����i-1�j��ԋp����B<BR>
     * �@@�@@�������������ꍇ��0��ԋp����B<BR>
     * �@@�@@����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j���傫���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@�~���w��̏ꍇ�A<BR>
     * �@@�@@����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j��菬�����ꍇ�A<BR>
     * �@@�@@�@@�@@�@@���̐����i1�j��ԋp����B<BR>
     * �@@�@@�������������ꍇ��0��ԋp����B<BR>
     * �@@�@@����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j���傫���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@���̐����i-1�j��ԋp����B<BR>
     * <BR>
     * �@@���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * @@param l_obj1 - (����1)<BR>
     * ���Ǘ��Ғ������Ɖ�s�I�u�W�F�N�g�P<BR>
     * @@param l_obj2 - (����2)<BR>
     * ���Ǘ��Ғ������Ɖ�s�I�u�W�F�N�g�Q<BR>
     * @@return int
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //�P�j�p�����[�^�����Ǘ��Ғ������Ɖ�s�ɃL���X�g����B
        String l_strVal1 = null;
        String l_strVal2 = null;
        if (l_obj1 instanceof WEB3AdminORBondExecRefUnit
            && l_obj2 instanceof WEB3AdminORBondExecRefUnit)
        {
            l_strVal1 = ((WEB3AdminORBondExecRefUnit)l_obj1).sonarTraderCode;
            l_strVal2 = ((WEB3AdminORBondExecRefUnit)l_obj2).sonarTraderCode;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3AdminORBondExecRefUnit'�ތ^�B");
        }

        // �Q�j��r
        int l_intReturn = 0;
        if (l_strVal1 == null || l_strVal2 == null)
        {
            if (l_strVal1 == null && l_strVal2 == null)
            {
                //�������������ꍇ��0��ԋp����B
                l_intReturn = 0;
            }
            else if (l_strVal1 == null)
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    //����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j��菬�����ꍇ�A
                    //���̐����i-1�j��ԋp����B
                    l_intReturn = -1;
                }
                else
                {
                    //����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j��菬�����ꍇ�A
                    //���̐����i1�j��ԋp����B
                    l_intReturn = 1;
                }
            }
            else
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    //����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j���傫���ꍇ�A
                    //���̐����i1�j��ԋp����B
                    l_intReturn = 1;
                }
                else
                {
                    //����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j���傫���ꍇ�A
                    //���̐����i-1�j��ԋp����B
                    l_intReturn = -1;
                }
            }
        }
        else
        {
            if (l_strVal1.equals(l_strVal2))
            {
                //�������������ꍇ��0��ԋp����B
                l_intReturn = 0;
            }
            else if (l_strVal1.compareTo(l_strVal2) > 0)
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    //����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j���傫���ꍇ�A
                    //���̐����i1�j��ԋp����B
                    l_intReturn = 1;
                }
                else
                {
                    //����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j���傫���ꍇ�A
                    //���̐����i-1�j��ԋp����B
                    l_intReturn = -1;
                }
            }
            else if (l_strVal1.compareTo(l_strVal2) < 0)
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    //����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j��菬�����ꍇ�A
                    //���̐����i-1�j��ԋp����B
                    l_intReturn = -1;
                }
                else
                {
                    //����1�̈��҃R�[�h�i�r�n�m�`�q�j���A����2�̈��҃R�[�h�i�r�n�m�`�q�j��菬�����ꍇ�A
                    //���̐����i1�j��ԋp����B
                    l_intReturn = 1;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
}
@
