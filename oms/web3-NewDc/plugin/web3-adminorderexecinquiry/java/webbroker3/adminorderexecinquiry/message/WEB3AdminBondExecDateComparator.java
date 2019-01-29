head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondExecDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (��)��������Comparator(WEB3AdminBondExecDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q(���u) �V�K�쐬  
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((��)��������Comparator)<BR>
 * (��)��������Comparator<BR>
 *  
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3AdminBondExecDateComparator implements Comparator 
{
    

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecDateComparator.class);
    
    /**
     * orderBy<BR>
     * A�F����<BR>
     * D�F�~��
     */
    private String orderBy;
       
    /**
     * ((��)����Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����
     * @@param l_strOrderBy - (orderBy)<BR>
     * �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��
     * @@roseuid 44C03D7D0307
     */
    public  WEB3AdminBondExecDateComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AdminBondExecDateComparator(String l_strOrderBy)";
            log.entering(STR_METHOD_NAME);
        
        if(l_strOrderBy == null ||
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
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B
     * @@param l_obj - (Object)<BR>
     * ��r�Ώۂ̎Q�ƃI�u�W�F�N�g
     * @@return boolean
     * @@roseuid 44C04FB602CB
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * �����A�~���̎w��ɂ��ƂÂ������̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�����Ǘ��Ғ������Ɖ�s�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j��r<BR>
     * �@@�P�j�ŃL���X�g��������1�A����2�̖������r���A���ʂ�ԋp����B<BR>
     * <BR>
     * �@@�����w��̏ꍇ�A<BR>
     * �@@�@@����1�̖������A����2�̖�����菬�����ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�@@�������������ꍇ��0��ԋp����B<BR>
     * �@@�@@����1�̖������A����2�̖������傫���ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@�~���w��̏ꍇ�A<BR>
     * �@@�@@����1�̖������A����2�̖�����菬�����ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�@@�������������ꍇ��0��ԋp����B<BR>
     * �@@�@@����1�̖������A����2�̖������傫���ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * <BR>
     * �@@���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����
     * @@param l_obj1 - (����1)<BR>
     * ���Ǘ��Ғ������Ɖ�s�I�u�W�F�N�g�P
     * @@param l_obj2 - (����2)<BR>
     * ���Ǘ��Ғ������Ɖ�s�I�u�W�F�N�g�Q
     * @@return int
     * @@roseuid 44C050500197
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        
        
        //�P�j�p�����[�^�����Ǘ��Ғ������Ɖ�s�ɃL���X�g����B     
        Date l_datVal1 = null;
        Date l_datVal2 = null;
        if(l_obj1 instanceof WEB3AdminORBondExecRefUnit && 
           l_obj2 instanceof WEB3AdminORBondExecRefUnit)
        {
            l_datVal1 = ((WEB3AdminORBondExecRefUnit) l_obj1).domesticExecutionDate;
            l_datVal2 = ((WEB3AdminORBondExecRefUnit) l_obj2).domesticExecutionDate;
        }
        else
        {

            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3AdminORBondExecRefUnit'�ތ^�B");
        }
        
        
        // �Q�j��r
        int l_intReturn = 0;
        if(l_datVal1 == null || l_datVal2 == null)
        {
            if(l_datVal1 == null && l_datVal2 == null)
            {
                l_intReturn = 0;
            }
            else if(l_datVal1 == null)
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
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
            if(l_datVal1.equals(l_datVal2))
            {
                l_intReturn = 0;
            }
            else if(l_datVal1.compareTo(l_datVal2) >0)
            {
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = 1;
                }
                else
                {
                    l_intReturn = -1;
                }
            }
            else if(l_datVal1.compareTo(l_datVal2) < 0)
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
        }
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
    
}
@
