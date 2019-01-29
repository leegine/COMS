head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundTradeDivComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����敪(���M)Comparator�����N���X(WEB3MutualFundTradeDivComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���E (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �����敪(���M)Comparator�����N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualFundTradeDivComparator implements Comparator 
{
    
    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundTradeDivComparator.class);    
    /**
     * �����敪(���M)Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     * @@roseuid 40B5A0D70317
     */
    public WEB3MutualFundTradeDivComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3MutualFundTradeDivComparator(String l_strOrderBy)";
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
     * �����A�~���̎w��ɂ��ƂÂ������敪(���M)�̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�𓊐M�����Ɖ���P�ʃN���X�ň������L���X�g����B<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �P�j�Ŕ��肵���N���X�̃C���X�^���Xobj1��obj2���ꂼ��̔����敪(���M)�ɂ���<BR>
     * <BR>
     * �����w��̏ꍇ�A<BR>
     * �p�����[�^.obj1�̔����敪(���M)���A�p�����[�^.obj2�̔����敪(���M)��菬�����ꍇ�͕��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.obj1�̔����敪(���M)���A�p�����[�^.obj2�̔����敪<BR>
     * (���M)���傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     * �~���w��̏ꍇ�A<BR>
     * �p�����[�^.obj1�̔����敪(���M)���A�p�����[�^.obj2�̔����敪<BR>
     * (���M)��菬�����ꍇ�͐��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.obj1�̔����敪(���M)���A�p�����[�^.obj2�̔����敪<BR>
     * (���M)���傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     * ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 40B5A0D70327
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);       
        
        WEB3MutualOrderGroup l_mutualOrderGroupObj1 = null;
        WEB3MutualOrderGroup l_mutualOrderGroupObj2 = null;
        
        if(l_obj1 instanceof WEB3MutualOrderGroup 
                && l_obj2 instanceof WEB3MutualOrderGroup)
        {
            l_mutualOrderGroupObj1 = (WEB3MutualOrderGroup)l_obj1;
            l_mutualOrderGroupObj2 = (WEB3MutualOrderGroup)l_obj2;            
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������" 
                    + "'WEB3MutualOrderGroup'�ތ^�B");
        }
        
        String l_strVal1 = l_mutualOrderGroupObj1.mutualDealingType;
        String l_strVal2 = l_mutualOrderGroupObj2.mutualDealingType;
        
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
     * @@roseuid 40B5A0D70336
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}
@
