head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.42.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondBranchCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (��)���X�R�[�hComparator(WEB3AdminBondBranchCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/9 ������(���u) �V�K�쐬   ���f��No.100
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((��)���X�R�[�hComparator)<BR>
 * (��)���X�R�[�hComparator<BR>
 * <BR>
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AdminBondBranchCodeComparator implements Comparator
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondBranchCodeComparator.class);

    /**
     * orderBy<BR>
     * A�F����<BR>
     * D�F�~��
     */
    private String orderBy;

    /**
     * ((��)���X�R�[�hComparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����
     * @@param l_strOrderBy - (orderBy)<BR>
     * �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��
     */
    public WEB3AdminBondBranchCodeComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME =
            "WEB3AdminBondBranchCodeComparator(String)";
        log.entering(STR_METHOD_NAME);

        if(l_strOrderBy == null ||
            (!l_strOrderBy.equals(WEB3AscDescDef.ASC) &&
            !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
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
     * @@param l_obj - (Object)<BR>
     * ��r�Ώۂ̎Q�ƃI�u�W�F�N�g
     * @@return boolean
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }

    /**
     * �icompare�̎����j<BR>
     * <BR>
     * ����1�C2�ɂ��āA���X�R�[�h�̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�����Ǘ��Ғ������Ɖ�s�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j��r<BR>
     * �@@�P�j�ŃL���X�g��������1�A����2�̕��X�R�[�h���r���A���ʂ�ԋp����B<BR>
     * <BR>
     *  �����w��̏ꍇ�A<BR>
     *�@@�@@����1�̕��X�R�[�h���A����2�̕��X�R�[�h��菬�����ꍇ�A���̐����i-1�j��ԋp����B<BR>
     *�@@�@@�������������ꍇ�A0��ԋp����B<BR>
     *�@@�@@����1�̕��X�R�[�h���A����2�̕��X�R�[�h���傫���ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     *  �~���w��̏ꍇ�A<BR>
     *�@@�@@����1�̕��X�R�[�h���A����2�̕��X�R�[�h��菬�����ꍇ�A���̐����i1�j��ԋp����B<BR>
     *�@@�@@�������������ꍇ�A0��ԋp����B<BR>
     *�@@�@@����1�̕��X�R�[�h���A����2�̕��X�R�[�h���傫���ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * <BR>
     *  ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * @@param l_obj1 - (����1)<BR>
     * ���Ǘ��Ғ������Ɖ�s�I�u�W�F�N�g�P
     * @@param l_obj2 - (����2)<BR>
     * ���Ǘ��Ғ������Ɖ�s�I�u�W�F�N�g�Q
     * @@return int
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //�P�j�p�����[�^�����Ǘ��Ғ������Ɖ�s�ɃL���X�g����B
        String l_strVal1 = null;
        String l_strVal2 = null;
        if (l_obj1 instanceof WEB3AdminORBondExecRefUnit &&
           l_obj2 instanceof WEB3AdminORBondExecRefUnit)
        {
            l_strVal1 = ((WEB3AdminORBondExecRefUnit)l_obj1).branchCode;
            l_strVal2 = ((WEB3AdminORBondExecRefUnit)l_obj2).branchCode;
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
                l_intReturn = 0;
            }
            else if (l_strVal1 == null)
            {
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = -1;
                }
                else
                {
                    l_intReturn = 1;
                }
            }
            else
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = 1;
                }
                else
                {
                    l_intReturn = -1;
                }
            }
        }
        else
        {
            if (l_strVal1.equals(l_strVal2))
            {
                l_intReturn = 0;
            }
            else if (l_strVal1.compareTo(l_strVal2) > 0)
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = 1;
                }
                else
                {
                    l_intReturn = -1;
                }
            }
            else if (l_strVal1.compareTo(l_strVal2) < 0)
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = -1;
                }
                else
                {
                    l_intReturn = 1;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
}
@
