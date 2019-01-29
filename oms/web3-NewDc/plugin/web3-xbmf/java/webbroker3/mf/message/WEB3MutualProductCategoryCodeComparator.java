head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductCategoryCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�����J�e�S���[Comparator(WEB3MutualProductCategoryCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/24 �h�C (���u) �V�K�쐬 
*/
package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�����J�e�S���[Comparator)<BR>
 *  ���M�����J�e�S���[Comparator�����N���X
 * @@author �h�C(���u)
 * @@version 1.0 
 */
public class WEB3MutualProductCategoryCodeComparator implements Comparator
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualProductCategoryCodeComparator.class);
    
    /**
     * (orderBy)<BR>
     * A�F���� <BR>
     * D�F�~��<BR>
     */
    private String orderBy;
    
    /**
     * (���M�����J�e�S���[Comparator)<BR>
     * ���M�����J�e�S���[Comparator�̃R���X�g���N�^�B<BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     */
    public WEB3MutualProductCategoryCodeComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME = 
            "WEB3MutualProductCategoryCodeComparator(String)";
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
     * �����A�~���̎w��ɂ��ƂÂ����M�����J�e�S���[�R�[�h�̔�r���s���B <BR>
     * <BR>
     * �P�j�p�����[�^�𓊐M�莞��z���t�����s�N���X�ň������L���X�g����B <BR>
     * <BR>
     * �Q�j��r <BR>
     * <BR>
     * �P�j�Ŕ��肵���N���X�̃C���X�^���Xobj1��obj2���ꂼ��̓��M�����J�e�S���[�R�[�h�ɂ��� <BR>
     * <BR>
     * �����w��̏ꍇ�A <BR>
     * �p�����[�^.obj1�̓��M�����J�e�S���[�R�[�h���A<BR>
     * �p�����[�^.obj2�̓��M�����J�e�S���[�R�[�h��菬�����ꍇ�͕��̐����A <BR>
     * �������������ꍇ��0�A <BR>
     * �p�����[�^.obj1�̓��M�����J�e�S���[�R�[�h���A<BR>
     * �p�����[�^.obj2�̓��M�����J�e�S���[�R�[�h���傫���ꍇ�͐��̐�����ԋp���� <BR>
     * <BR>
     * �~���w��̏ꍇ�A <BR>
     * �p�����[�^.obj1�̓��M�����J�e�S���[�R�[�h���A<BR>
     * �p�����[�^.obj2�̓��M�����J�e�S���[�R�[�h��菬�����ꍇ�͐��̐����A <BR>
     * �������������ꍇ��0�A <BR>
     * �p�����[�^.obj1�̓��M�����J�e�S���[�R�[�h���A<BR>
     * �p�����[�^.obj2�̓��M�����J�e�S���[�R�[�h���傫���ꍇ�͕��̐�����ԋp���� <BR>
     * <BR>
     * ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p���� <BR>
     * <BR>
     * @@param l_obj1 - (obj1)<BR>
     * @@param l_obj2 - (obj1)<BR>
     * @@return int<BR>
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = "compare(Object, Object) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�p�����[�^�𓊐M�莞��z���t�����s�N���X�ň������L���X�g����B
        String l_strVal1 = null;
        String l_strVal2 = null;
        if (l_obj1 instanceof WEB3MutualFixedBuyConditionUnit && 
            l_obj2 instanceof WEB3MutualFixedBuyConditionUnit)
        {
            l_strVal1 = 
                ((WEB3MutualFixedBuyConditionUnit) l_obj1).categoryCode;
            l_strVal2 = 
                ((WEB3MutualFixedBuyConditionUnit) l_obj2).categoryCode;
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������" 
                + "'WEB3MutualFixedBuyConditionUnit'�ތ^�B");
        }
        //�Q�j��r
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
            //�����w��̏ꍇ
            if(WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                //�p�����[�^.obj1�̓��M�����J�e�S���[�R�[�h���A
                //�p�����[�^.obj2�̓��M�����J�e�S���[�R�[�h��菬�����ꍇ�͕��̐���
                if(l_strVal1.compareTo(l_strVal2) < 0)
                {
                    l_intReturn = -1;
                }
                //�������������ꍇ��0
                else if(l_strVal1.equals(l_strVal2))
                {
                    l_intReturn = 0;
                }
                //�p�����[�^.obj1�̓��M�����J�e�S���[�R�[�h���A
                //�p�����[�^.obj2�̓��M�����J�e�S���[�R�[�h���傫���ꍇ�͐��̐�����ԋp����
                else
                {
                    l_intReturn = 1;
                }
            }
            //�~���w��̏ꍇ
            else
            {
                //�p�����[�^.obj1�̓��M�����J�e�S���[�R�[�h���A
                //�p�����[�^.obj2�̓��M�����J�e�S���[�R�[�h��菬�����ꍇ�͐��̐���
                if(l_strVal1.compareTo(l_strVal2) < 0)
                {
                    l_intReturn = 1;
                }
                //�������������ꍇ��0
                else if(l_strVal1.equals(l_strVal2))
                {
                    l_intReturn = 0;
                }
                //�p�����[�^.obj1�̓��M�����J�e�S���[�R�[�h���A
                //�p�����[�^.obj2�̓��M�����J�e�S���[�R�[�h���傫���ꍇ�͕��̐�����ԋp����
                else
                {
                    l_intReturn = -1;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
    
    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * �X�[�p�[�N���X��equals���R�[������B<BR>
     * @@param l_obj - (obj)<BR>
     * @@return boolean<BR>
     */
    public boolean equals(Object l_obj)
    {
        return super.equals(l_obj);
    }
}
@
