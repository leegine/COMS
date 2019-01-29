head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOrderDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������Comparator�N���X(WEB3AioOrderDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/25 �����(���u) ���r���[                                       
*/

package webbroker3.aio.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (��������Comparator)<BR>
 * ��������Comparator�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */

public class WEB3AioOrderDateComparator implements Comparator 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOrderDateComparator.class);
        
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F����  <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;
    
    /**
     * (�R���X�g���N�^)<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B <BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F����  <BR>
     * �@@D�F�~�� <BR>
     * @@return webbroker3.aio.message.WEB3AioOrderDateComparator
     * @@roseuid 4109ADFB0311
     */
    public WEB3AioOrderDateComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AioOrderDateComparator(String l_strOrderBy)";
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
     * �icompare�̎����j <BR>
     * <BR>
     * ���������̔�r���s���B  <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̏o���\���⍇�����ׂP�A�o���\���⍇�����ׂQ���o���\���⍇��<BR>
     * ���׌^��cast����B <BR>
     * <BR>
     * �Q�j�@@��r  <BR>
     * 
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i�o���\���⍇�����ׂP.�������� < <BR>
     * �o���\���⍇�����ׂQ.���������j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i�o���\���⍇�����ׂP.�������� = <BR>
     * �o���\���⍇�����ׂQ.���������j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i�o���\���⍇�����ׂP.�������� > <BR>
     * �o���\���⍇�����ׂQ.���������j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i�o���\���⍇�����ׂP.�������� < <BR>
     * �o���\���⍇�����ׂQ.���������j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * �@@�E�i�o���\���⍇�����ׂP.�������� = <BR>
     * �o���\���⍇�����ׂQ.���������j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i�o���\���⍇�����ׂP.�������� > <BR>
     * �o���\���⍇�����ׂQ.���������j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * <BR>
     * @@param l_obj1 - (�o���\���⍇�����׃I�u�W�F�N�g�P)
     * @@param l_obj2 - (�o���\���⍇�����׃I�u�W�F�N�g�Q)
     * @@return int
     * @@roseuid 4109ADFB030C
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);  
         
        Date l_datVal1 = null;
        Date l_datVal2 = null;
        
        //�P�j�����̏o���\���⍇�����ׂP�A�o���\���⍇�����ׂQ���o���\���⍇��<BR>
        //���׌^��cast����B
        if ((l_obj1 instanceof WEB3AioCashoutInqUnit)    //�o���\���⍇������
            && (l_obj2 instanceof WEB3AioCashoutInqUnit))
        {
            l_datVal1 = ((WEB3AioCashoutInqUnit)l_obj1).orderDate;
            l_datVal2 = ((WEB3AioCashoutInqUnit)l_obj2).orderDate;
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������" +
                        "'WEB3AioCashoutInqUnit'�ތ^�B");
        }
        //�Q�j��r
        int l_intReturn = 0;
        
        if (l_datVal1 == null || l_datVal2 == null)
        {
            if (l_datVal1 == null && l_datVal2 == null)
            {
                l_intReturn = 0;       
            }
            else if (l_datVal2 == null && l_datVal1 != null)
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
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;       
    }
    
    /**
     * �iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B <BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 4109ADFB030F
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}
@