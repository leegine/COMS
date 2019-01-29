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
filename	WEB3AdminIfoDepClaimAmountComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�؋���)�����zComparator(WEB3AdminIfoDepClaimAmountComparator.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 ���ʗ�(���u) �V�K�쐬 ���f��No.004
*/
package webbroker3.ifoadmin.message;

import java.util.Comparator;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((�؋���)�����zComparator)<BR>
 * (�؋���)�����zComparator�N���X<BR>
 * <BR>
 * @@author ���ʗ�(���u)
 * @@version 1.0
 */
public class WEB3AdminIfoDepClaimAmountComparator implements Comparator
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepClaimAmountComparator.class);

    /**
     * A�F���� <BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * @@roseuid 49A7485503C8
     */
    public WEB3AdminIfoDepClaimAmountComparator()
    {

    }

    /**
     * ((�؋���)�����zComparator)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g���� <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * orderBy<BR>
     * @@roseuid 499915AD01C1
     */
    public WEB3AdminIfoDepClaimAmountComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME = "WEB3AdminIfoDepClaimAmountComparator(String)";
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
     * @@roseuid 4999150C0381
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }

    /**
     * compare�̎���<BR>
     * <BR>
     * �����A�~���̎w��ɂ��ƂÂ������z�̔�r���s���B <BR>
     * <BR>
     * �P�j�p�����[�^���؋����s���󋵏��ɃL���X�g����B <BR>
     * <BR>
     * �Q�j��r <BR>
     * �@@�P�j�ŃL���X�g��������1�A����2�̐����z���r���A���ʂ�ԋp����B <BR>
     * <BR>
     * �@@�����w��̏ꍇ�A <BR>
     * �@@�@@����1�̐����z���A����2�̐����z��菬�����ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�@@�������������ꍇ��0��ԋp����B <BR>
     * �@@�@@����1�̐����z���A����2�̐����z���傫���ꍇ�A���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@�~���w��̏ꍇ�A <BR>
     * �@@�@@����1�̐����z���A����2�̐����z��菬�����ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�@@�������������ꍇ��0��ԋp����B <BR>
     * �@@�@@����1�̐����z���A����2�̐����z���傫���ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * <BR>
     * �@@���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p���� <BR>
     * <BR>
     * @@param l_unit1 - (����1)<BR>
     * ����1<BR>
     * @@param l_unit2 - (����2)<BR>
     * ����2<BR>
     * @@return int
     * @@roseuid 499915670142
     */
    public int compare(Object l_unit1, Object l_unit2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^���؋����s���󋵏��ɃL���X�g����B
        String l_strVal1 = null;
        String l_strVal2 = null;
        if (l_unit1 instanceof WEB3IfoDepShortageInfo && l_unit2 instanceof WEB3IfoDepShortageInfo)
        {
            l_strVal1 = ((WEB3IfoDepShortageInfo)l_unit1).claimAmount;
            l_strVal2 = ((WEB3IfoDepShortageInfo)l_unit2).claimAmount;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3IfoDepShortageInfo'�ތ^�B");
        }

        //��r
        //�ŃL���X�g��������1�A����2�̐����z���r���A���ʂ�ԋp����B
        int l_intReturn = 0;
        if (l_strVal1 == null || l_strVal2 == null)
        {
            if (l_strVal1 == null && l_strVal2 == null)
            {
                l_intReturn = 0;
            }
            else if (l_strVal1 == null)
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
            double l_dblVal1 = Double.parseDouble(l_strVal1);
            double l_dblVal2 = Double.parseDouble(l_strVal2);

            if (l_dblVal1 < l_dblVal2)
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
            else if (GtlUtils.Double.isEqual(l_dblVal1, l_dblVal2))
            {
                l_intReturn = 0;
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

        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
}
@
