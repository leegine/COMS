head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����Comparator(WEB3OptionsOpenDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ������ �V�K�쐬
Revesion History : 2007/07/11 �����F (���u) ���f��773
*/

package webbroker3.ifo.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;


/**
 * (����Comparator)<BR>
 * ����Comparator�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsOpenDateComparator implements Comparator
{
    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;

    /**
     * (����Comparator)<BR>
     * ����Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - (�\�[�g�L�[�̏����~���������B)<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     * @@return Void
     * @@roseuid 407BC27001C4
     */
    public WEB3OptionsOpenDateComparator(String l_strOrderBy)
    {
        if (l_strOrderBy == null
            ||( !WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
    }

    /**
     * 
     * �����A�~���̎w��ɂ��ƂÂ������̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�̃I�u�W�F�N�g�̔���<BR>
     * <BR>
     * �@@instanceof�ɂāA�����̖���1�A����2���ȉ��̃N���X�̂ǂꂩ�𔻒肷��B<BR>
     *     ���ʖ��׃N���X<BR>
     *     �����w���I�v�V�������ʏƉ�׃N���X<BR>
     *     �����w���敨�I�v�V�����c���Ɖ�׃N���X<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �@@�P�j����1�Ɩ���2�̌����ɂ��Ĕ�r���s��<BR>
     * �@@[�����w��̏ꍇ(this.orderBy == �h�����h)]<BR>
     * <BR>
     * �@@�E(����1.���� < ����2.����)�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * �@@�E(����1.���� == ����2.����)�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���� > ����2.����)�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h)]<BR>
     * <BR>
     * �@@�E(����1.���� < ����2.����)�̏ꍇ�A���̐���(1)��ԋp����<BR>
     * �@@�E(����1.���� == ����2.����)�̏ꍇ�A0��ԋp����<BR>
     * �@@�E(����1.���� > ����2.����)�̏ꍇ�A���̐���(-1)��ԋp����<BR>
     * @@param l_obj1 - obj1<BR>
     * @@param l_obj2 - obj2<BR>
     * @@return int<BR>
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        Date l_datVal1 = null;
        Date l_datVal2 = null;

        if (l_obj1 instanceof WEB3OptionsContractReferenceUnit 
            && l_obj2 instanceof WEB3OptionsContractReferenceUnit)
        {
            l_datVal1 = ((WEB3OptionsContractReferenceUnit)l_obj1).openDate;
            l_datVal2 = ((WEB3OptionsContractReferenceUnit)l_obj2).openDate;
        }
        else if (l_obj1 instanceof WEB3FuturesOptionsContractUnit 
            && l_obj2 instanceof WEB3FuturesOptionsContractUnit)
        {
            l_datVal1 = ((WEB3FuturesOptionsContractUnit)l_obj1).openDate;
            l_datVal2 = ((WEB3FuturesOptionsContractUnit)l_obj2).openDate;
        }
        else if (l_obj1 instanceof WEB3FuturesOptionsDetailUnit 
            && l_obj2 instanceof WEB3FuturesOptionsDetailUnit)
        {
            l_datVal1 = ((WEB3FuturesOptionsDetailUnit)l_obj1).openDate;
            l_datVal2 = ((WEB3FuturesOptionsDetailUnit)l_obj2).openDate;
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������" +
                "'WEB3OptionsContractReferenceUnit' " +
                "�܂��� 'WEB3FuturesOptionsContractUnit' " +
                "�܂��� 'WEB3FuturesOptionsDetailUnit' �ތ^�B");
        }

        if (l_datVal1 == null || l_datVal2 == null)
        {
            int l_intResult;
               
            if (l_datVal1 == null && l_datVal2 == null)
            {
                l_intResult = 0;
            }
            else if (l_datVal1 == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
                        
            return l_intResult;            
        }

        if (WEB3DateUtility.compareToDay(l_datVal1, l_datVal2) ==0)
        {
            return 0;
        }
        else if (WEB3DateUtility.compareToDay(l_datVal1, l_datVal2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.DESC.equals(this.orderBy))
            {
                return 1;        
            }
            else
            {
                return -1;
            }
        }
    }

    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B<BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 407BC718034B
     */
    public boolean equals(Object l_obj)
    {
        //�X�[�p�[�N���X��equals���R�[������B
        if (l_obj instanceof  WEB3OptionsOpenDateComparator)
        {
            WEB3OptionsOpenDateComparator l_comparator =
                    ( WEB3OptionsOpenDateComparator) l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false;
    }
}
@
