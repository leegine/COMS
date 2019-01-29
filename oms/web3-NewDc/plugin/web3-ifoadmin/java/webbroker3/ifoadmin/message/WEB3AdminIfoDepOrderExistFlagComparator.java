head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepOrderExistFlagComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�؋���)�����L���t���OComparator(WEB3AdminIfoDepOrderExistFlagComparator.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 ���ʗ�(���u) �V�K�쐬 ���f��No.004
*/
package webbroker3.ifoadmin.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((�؋���)�����L���t���OComparator)<BR>
 * (�؋���)�����L���t���OComparator�N���X<BR>
 * <BR>
 * @@author ���ʗ�(���u)
 * @@version 1.0
 */
public class WEB3AdminIfoDepOrderExistFlagComparator implements Comparator
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepOrderExistFlagComparator.class);

    /**
     * A�F���� <BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * @@roseuid 49A74856003E
     */
    public WEB3AdminIfoDepOrderExistFlagComparator()
    {

    }

    /**
     * ((�؋���)�����L���t���OComparator)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g���� <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * orderBy<BR>
     * @@roseuid 499915C800F7
     */
    public WEB3AdminIfoDepOrderExistFlagComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME = "WEB3AdminIfoDepOrderExistFlagComparator(String)";
        log.entering(STR_METHOD_NAME);

        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }

        this.orderBy = l_strOrderBy;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B <BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B <BR>
     * @@param l_obj - (obj)<BR>
     * obj<BR>
     * @@return boolean
     * @@roseuid 499915210343
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }

    /**
     * �icompare�̎����j <BR>
     * <BR>
     * �����A�~���̎w��ɂ��ƂÂ��̒����L���t���O��r���s���B <BR>
     * <BR>
     * �P�j�p�����[�^���؋����s���󋵏��ɃL���X�g����B <BR>
     * <BR>
     * �Q�j��r <BR>
     * �@@�P�j�ŃL���X�g��������1�A����2�̒����L���t���O���r���A���ʂ�ԋp����B <BR>
     * <BR>
     * �@@�����w��̏ꍇ�A <BR>
     * �@@�@@����1�̒����L���t���O��false�A����2�̒����L���t���O��true�ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�@@�������������ꍇ��0��ԋp����B <BR>
     * �@@�@@����1�̒����L���t���O��true�A����2�̒����L���t���O��false�ꍇ�A���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@�~���w��̏ꍇ�A <BR>
     * �@@�@@����1�̒����L���t���O��false�A����2�̒����L���t���O��true�ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�@@�������������ꍇ��0��ԋp����B <BR>
     * �@@�@@����1�̒����L���t���O��true�A����2�̒����L���t���O��false�ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * <BR>
     * �@@���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p���� <BR>
     * <BR>
     * @@param l_unit1 - (����1)<BR>
     * ����1<BR>
     * @@param l_unit2 - (����2)<BR>
     * ����2<BR>
     * @@return int
     * @@roseuid 499915750152
     */
    public int compare(Object l_unit1, Object l_unit2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^���؋����s���󋵏��ɃL���X�g����B
        boolean l_blnFlag1 = false;
        boolean l_blnFlag2 = false;
        if (l_unit1 instanceof WEB3IfoDepShortageInfo && l_unit2 instanceof WEB3IfoDepShortageInfo)
        {
            l_blnFlag1 = ((WEB3IfoDepShortageInfo)l_unit1).orderExistFlag;
            l_blnFlag2 = ((WEB3IfoDepShortageInfo)l_unit2).orderExistFlag;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3IfoDepShortageInfo'�ތ^�B");
        }

        //��r
        //�ŃL���X�g��������1�A����2�̒����L���t���O���r���A���ʂ�ԋp����B
        int l_intReturn = 0;
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (!l_blnFlag1 && l_blnFlag2)
            {
                l_intReturn = -1;
            }
            else if (l_blnFlag1 == l_blnFlag2)
            {
                l_intReturn = 0;
            }
            else
            {
                l_intReturn = 1;
            }
        }
        else
        {
            if (!l_blnFlag1 && l_blnFlag2)
            {
                l_intReturn = 1;
            }
            else if (l_blnFlag1 == l_blnFlag2)
            {
                l_intReturn = 0;
            }
            else
            {
                l_intReturn = -1;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
}
@
