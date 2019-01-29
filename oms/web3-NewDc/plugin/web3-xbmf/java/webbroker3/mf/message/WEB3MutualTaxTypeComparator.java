head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTaxTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����敪Comparator�����N���X(WEB3MutualTaxTypeComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ��O�� (���u) �V�K�쐬
                   2004/08/24 ���� (���u) ���r���[
*/

package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * �����敪Comparator�����N���X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualTaxTypeComparator implements Comparator 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualTaxTypeComparator.class);
    
    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;   

    /**
     * �����敪Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     * @@roseuid 40B5A04B026B
     */
    public WEB3MutualTaxTypeComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3TaxTypeComparator(String l_strOrderBy)";
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
     * �����A�~���̎w��ɂ��ƂÂ������敪�̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�̃I�u�W�F�N�g�̔���<BR>
     * <BR>
     * instanceof�ɂāA�p�����[�^�̃I�u�W�F�N�gobj1�A<BR>
     * obj2���ȉ��̃N���X�̂ǂ��炩�𔻒肷��B<BR>
     * <BR>
     *     ���M���抷�����ꗗ�s�N���X<BR>
     *     ���M�����Ɖ���P�ʃN���X<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �P�j�Ŕ��肵���N���X�̃C���X�^���Xobj1��obj2���ꂼ��̌����敪�ɂ���<BR>
     * <BR>
     * �����w��̏ꍇ�A<BR>
     * �p�����[�^.obj1�̌����敪���A�p�����[�^.obj2��<BR>
     * �����敪��菬�����ꍇ�͕��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.obj1�̌����敪���A�p�����[�^.obj2��<BR>
     * �����敪���傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     * �~���w��̏ꍇ�A<BR>
     * �p�����[�^.obj1�̌����敪���A�p�����[�^.obj2��<BR>
     * �����敪��菬�����ꍇ�͐��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.obj1�̌����敪���A�p�����[�^.obj2��<BR>
     * �����敪���傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     * ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 40B5A06F0123
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);           
        
        String l_strVal1 = null;
        String l_strVal2 = null;
        
        if ((l_obj1 instanceof WEB3MutualSellSwitchingProductGroup) //���M���抷�����ꗗ�s
            && (l_obj2 instanceof WEB3MutualSellSwitchingProductGroup))
        {
            log.debug("WEB3MutualSellSwitchingProductGroup: ENTER");
            l_strVal1 = ((WEB3MutualSellSwitchingProductGroup)l_obj1).taxType;
            l_strVal2 = ((WEB3MutualSellSwitchingProductGroup)l_obj2).taxType;
            log.debug("WEB3MutualSellSwitchingProductGroup: END");
        }
        else if ((l_obj1 instanceof WEB3MutualOrderGroup)  //���M�����Ɖ���P��
            && (l_obj2 instanceof WEB3MutualOrderGroup))               
        {
            log.debug("WEB3MutualOrderGroup: ENTER");
            l_strVal1 = ((WEB3MutualOrderGroup)l_obj1).taxType;
            l_strVal2 = ((WEB3MutualOrderGroup)l_obj2).taxType;
            log.debug("WEB3MutualOrderGroup: END");
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������" +
                "'WEB3MutualSellSwitchingProductGroup' �܂��� " +
                "'WEB3MutualOrderGroup' �ތ^�B");
        }

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
     * @@roseuid 40B5A06F0133
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }
}
@
