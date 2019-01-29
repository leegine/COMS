head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������Comparator�����N���X(WEB3MutualOrderDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ��O�� (���u) �V�K�쐬
                   2004/08/24 ���� (���u) ���r���[                          
*/

package webbroker3.mf.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * ��������Comparator�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualOrderDateComparator implements Comparator 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderDateComparator.class);
        
    /**
     * A�F����<BR>
     * D�F�~��
     */
    private String orderBy;
    
    /**
     * ��������Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@roseuid 40B5A1250133
     */
    public WEB3MutualOrderDateComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME =
            "WEB3OrderDateComparator(String l_strOrderBy)";
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
     * �����A�~���̎w��ɂ��ƂÂ����������̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�𓊐M�����Ɖ���P�ʃN���X�ň������L���X�g����B<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �P�j�Ŕ��肵���N���X�̃C���X�^���Xobj1��obj2���ꂼ��̒��������ɂ���<BR>
     * <BR>
     * �����w��̏ꍇ�A<BR>
     * �p�����[�^.obj1�̒����������A�p�����[�^.obj2��<BR>
     * ����������菬�����ꍇ�͕��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.obj1�̒����������A�p�����[�^.obj2��<BR>
     * �����������傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     * �~���w��̏ꍇ�A<BR>
     * �p�����[�^.obj1�̒����������A�p�����[�^.obj2��<BR>
     * ����������菬�����ꍇ�͐��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.obj1�̒����������A�p�����[�^.obj2��<BR>
     * �����������傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     * ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 40B5A1250142
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);  
         
        Date l_datVal1 = null;
        Date l_datVal2 = null;
        
        //�P�j�p�����[�^�𓊐M�����Ɖ���P�ʃN���X�ň������L���X�g����B
        if ((l_obj1 instanceof WEB3MutualOrderGroup)    //���M�����Ɖ���P��
            && (l_obj2 instanceof WEB3MutualOrderGroup))
        {
            log.debug("WEB3MutualOrderGroup: ENTER");
            l_datVal1 = ((WEB3MutualOrderGroup)l_obj1).orderDate;
            l_datVal2 = ((WEB3MutualOrderGroup)l_obj2).orderDate;
            log.debug("WEB3MutualOrderGroup: END");
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������" +
                        "'WEB3MutualOrderGroup'�ތ^�B");
        }
        //�Q�j��r              
        int l_intReturn = 0;
        if (l_datVal1 == null || l_datVal2 == null)
        {
               
            if (l_datVal1 == null && l_datVal2 == null)
            {
                l_intReturn = 0;
            }
            else if (l_datVal1 == null)
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
            if (WEB3DateUtility.compareToSecond(l_datVal1, l_datVal2) == 0)
            {
                l_intReturn = 0;
            }
            else if (WEB3DateUtility.compareToSecond(l_datVal1, l_datVal2) > 0)
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
     * @@roseuid 40B5A1250152
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}
@
