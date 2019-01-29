head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsSessionTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����敪Comparator(WEB3OptionsSessionTypeComparator.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/11 �����F (���u) �V�K�쐬 ���f��692
Revision History : 2007/07/11 �����F (���u) ���f��773
*/
package webbroker3.ifo.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (����敪Comparator)<BR>
 * ����敪Comparator
 * @@author �����F
 * @@version 1.0
 */
public class WEB3OptionsSessionTypeComparator implements Comparator
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsSessionTypeComparator.class);

    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * ����敪Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     */
    public WEB3OptionsSessionTypeComparator(String l_strOrderBy)
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * �����A�~���̎w��ɂ��ƂÂ������̔�r���s���B <BR>
     * <BR>
     * �P�j�p�����[�^�̃I�u�W�F�N�g�̔��� <BR>
     * <BR>
     * �@@instanceof�ɂāA�����̖���1�A����2���ȉ��̃N���X�̂ǂꂩ�𔻒肷��B <BR>
     * <BR>
     *     ���ʖ��׃N���X <BR>
     *     �����w���I�v�V�������ʏƉ�׃N���X <BR>
     *     �����w���敨�I�v�V�����c���Ɖ�׃N���X<BR>
     * <BR>
     * �@@����1.����敪==NULL�̏ꍇ <BR>
     * �@@����1.����敪�@@=�@@0�@@�Ƃ��Ĉȉ���r���s�Ȃ��B <BR>
     * <BR>
     * �@@����2.����敪==NULL�̏ꍇ <BR>
     * �@@����2.����敪�@@=�@@0�@@�Ƃ��Ĉȉ���r���s�Ȃ��B <BR>
     * <BR>
     * <BR>
     * �Q�j��r <BR>
     * <BR>
     * ����r���A0�ƒu������������.����敪�� <BR>
     * �ԋp���A���̒l�ł���NULL�ɖ߂��B <BR>
     * <BR>
     * �@@�P�j����1�Ɩ���2�̗���敪�ɂ��Ĕ�r���s�� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ(this.orderBy == �h�����h)] <BR>
     * <BR>
     * �@@�E(����1.����敪 < ����2.����敪)�̏ꍇ�A���̐���(-1)��ԋp���� <BR>
     * �@@�E(����1.����敪 == ����2.����敪)�̏ꍇ�A0��ԋp���� <BR>
     * �@@�E(����1.����敪 > ����2.����敪)�̏ꍇ�A���̐���(1)��ԋp���� <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h)] <BR>
     * <BR>
     * �@@�E(����1.����敪 < ����2.����敪)�̏ꍇ�A���̐���(1)��ԋp���� <BR>
     * �@@�E(����1.����敪 == ����2.����敪)�̏ꍇ�A0��ԋp���� <BR>
     * �@@�E(����1.����敪 > ����2.����敪)�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * @@param l_obj1 - obj1<BR>
     * @@param l_obj2 - obj2<BR>
     * @@return int<BR>
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        String l_strAttr1 = null;
        String l_strAttr2 = null;

        //�@@instanceof�ɂāA�����̖���1�A����2���ȉ��̃N���X�̂ǂꂩ�𔻒肷��B
        //���ʖ��׃N���X
        //�����w���I�v�V�������ʏƉ�׃N���X
        //�����w���敨�I�v�V�����c���Ɖ�׃N���X
        if ((l_obj1 instanceof WEB3FuturesOptionsContractUnit)
            && (l_obj2 instanceof WEB3FuturesOptionsContractUnit))
        {
            l_strAttr1 = ((WEB3FuturesOptionsContractUnit)l_obj1).sessionType;
            l_strAttr2 = ((WEB3FuturesOptionsContractUnit)l_obj2).sessionType;
        }
        else if ((l_obj1 instanceof WEB3OptionsContractReferenceUnit)
            && (l_obj2 instanceof WEB3OptionsContractReferenceUnit))
        {
            l_strAttr1 = ((WEB3OptionsContractReferenceUnit)l_obj1).sessionType;
            l_strAttr2 = ((WEB3OptionsContractReferenceUnit)l_obj2).sessionType;
        }
        else if ((l_obj1 instanceof WEB3FuturesOptionsDetailUnit)
            && (l_obj2 instanceof WEB3FuturesOptionsDetailUnit))
        {
            l_strAttr1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).sessionType;
            l_strAttr2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).sessionType;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3FuturesOptionsContractUnit'�@@" +
                "�܂��́@@'WEB3OptionsContractReferenceUnit' �܂��� 'WEB3FuturesOptionsDetailUnit'�ތ^");
        }

        //�@@����1.����敪==NULL�̏ꍇ
        //�@@����1.����敪�@@=�@@0�@@�Ƃ��Ĉȉ���r���s�Ȃ��B
        //�@@����2.����敪==NULL�̏ꍇ
        //�@@����2.����敪�@@=�@@0�@@�Ƃ��Ĉȉ���r���s�Ȃ��B
        if (l_strAttr1 == null)
        {
            l_strAttr1 = "0";
        }
        if (l_strAttr2 == null)
        {
            l_strAttr2 = "0";
        }

        if (l_strAttr1.compareTo(l_strAttr2) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else if (l_strAttr1.compareTo(l_strAttr2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
        }
    }

    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B<BR>
     * @@param l_obj - obj
     * @@return boolean
     */
    public boolean equals(Object l_obj)
    {
        if (l_obj instanceof WEB3OptionsSessionTypeComparator)
        {
            WEB3OptionsSessionTypeComparator l_comparator =
                (WEB3OptionsSessionTypeComparator)l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
