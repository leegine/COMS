head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorProductListComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
  Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
  File Name        : �Ǘ��җ���O���������ꗗComparator(WEB3AdminOffFloorProductListComparator.java)
  Author Name      : Daiwa Institute of Research
  Revesion History : 2006/04/03 ���(SRA) �V�K�쐬
 */
package webbroker3.eqtypeadmin.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;

/**
  * �Ǘ��җ���O���������ꗗComparator
  * @@version 1.0
  */
 
public class WEB3AdminOffFloorProductListComparator implements Comparator
{
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B  
     * 
     * �@@A�F����   
     * �@@D�F�~��
     */
    private String orderBy;

    /**
     * ��r����<BR>
     * �ȉ��̂��Âꂩ�̒l���Z�b�g�����B<BR>
     * �@@�E�����R�[�h<BR>
     * �@@�E�s��R�[�h<BR>
     * �@@�E��t�J�n����<BR>
     * �@@�E��t�I������<BR>
     */
    private String compareItem;

    /**
     * �icompare�̎����j<BR>
     * <BR>
     * ���ׂP�C�Q�ɂ��āAthis.��r���ڂŎw�肵�����ڂ̔�r���s���B<BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �p�����[�^.����1�����2���A�Ǘ��җ���O�����������׌^��cast����B<BR>
     * <BR>
     * �R�j�@@��r  <BR>
     * �@@�Q�j��cast��������1�A����2�ɂ���  <BR>
     * �@@this.��r���ڂɊY�����鍀�ڂ̒l���r���A���ʂ�ԋp����B <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]  <BR>
     * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]  <BR>
     * �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * @@param l_obj1 - (���ׂP)<BR>
     * �Ǘ��җ���O�����������׃I�u�W�F�N�g�P<BR>
     * @@param l_obj2 - (���ׂQ)<BR>
     * �Ǘ��җ���O�����������׃I�u�W�F�N�g�Q<BR>
     * @@return int
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        // �P�j�@@������cast 
        // �p�����[�^.����1�����2���A�Ǘ��җ���O�����������׌^��cast����B 
        WEB3AdminOffFloorProductGroup l_adminOffFloorProductGroup1 = (WEB3AdminOffFloorProductGroup)l_obj1;
        WEB3AdminOffFloorProductGroup l_adminOffFloorProductGroup2 = (WEB3AdminOffFloorProductGroup)l_obj2;
        int l_compare = 0;
       
        // �R�j�@@��r  
        // �@@�Q�j��cast��������1�A����2�ɂ���  
        // �@@this.��r���ڂɊY�����鍀�ڂ̒l���r���A���ʂ�ԋp����B 
        // 
        // �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]  
        // �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B  
        // �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B  
        // �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B  
        //
        // �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]  
        // �@@�E�i����1.��r���� < ����2.��r���ځj�̏ꍇ�A���̐����i1�j��ԋp����B  
        // �@@�E�i����1.��r���� == ����2.��r���ځj�̏ꍇ�A0��ԋp����B  
        // �@@�E�i����1.��r���� > ����2.��r���ځj�̏ꍇ�A���̐����i-1�j��ԋp����B
          
        //�@@��r���� == �����R�[�h �̏ꍇ<BR>
        if ("productCode".equals(this.compareItem))
        {
            l_compare = compare(l_adminOffFloorProductGroup1.productKey.productCode,
                                    l_adminOffFloorProductGroup2.productKey.productCode);
        }
        //�@@��r���� == �s��R�[�h �̏ꍇ<BR>
        else if ("marketCode".equals(this.compareItem))
        {
            l_compare = compare(l_adminOffFloorProductGroup1.productKey.marketCode,
                                    l_adminOffFloorProductGroup2.productKey.marketCode);
        }
        //�@@��r���� == ��t�J�n���� �̏ꍇ<BR>
        else if ("orderStartDatetime".equals(this.compareItem))
        {
            l_compare = compare(l_adminOffFloorProductGroup1.orderStartDatetime,
                                    l_adminOffFloorProductGroup2.orderStartDatetime);
        }
        //�@@��r���� == ��t�I������ �̏ꍇ<BR>
        else if ("orderEndDatetime".equals(this.compareItem))
        {
            l_compare = compare(l_adminOffFloorProductGroup1.productKey.orderEndDatetime,
                                    l_adminOffFloorProductGroup2.productKey.orderEndDatetime);
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̌^��'WEB3AdminOffFloorProductGroup' �ȊO�ł��B");
        }
        return l_compare;
    }

    /**
     * �icompare�̎����j<BR> 
     * �����A�~���̎w��ɂ��ƂÂ�String�^�̔�r���s���B<BR>
     * <BR>
     * �����w��̏ꍇ�A<BR>
     * �p�����[�^.l_strVal1���p�����[�^l_strVal2��菬�����ꍇ�͕��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_strVal1���p�����[�^.l_strVal2���傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     * �~���w��̏ꍇ�A<BR>
     * �p�����[�^.l_strVal1���p�����[�^.l_strVal2��菬�����ꍇ�͐��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_strVal1���p�����[�^.l_strVal2���傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     * ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * @@param l_strVal1 - ��r����1
     * @@param l_strVal2 - ��r����2
     * @@return int
     */
    private int compare(String l_strVal1,String l_strVal2)
    {
        if (l_strVal1.equals(l_strVal2))
        {
            return 0;
        }
        else if (l_strVal1.compareTo(l_strVal2) > 0)
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
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }
    
    /**
     * �icompare�̎����j<BR> 
     * �����A�~���̎w��ɂ��ƂÂ�Date�^�̔�r���s���B<BR>
     * <BR>
     * �����w��̏ꍇ�A<BR>
     * �p�����[�^.l_datVal1���p�����[�^l_datVal2��菬�����ꍇ�͕��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_datVal1���p�����[�^.l_datVal2���傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     * �~���w��̏ꍇ�A<BR>
     * �p�����[�^.l_datVal1���p�����[�^.l_datVal2��菬�����ꍇ�͐��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_datVal1���p�����[�^.l_datVal2���傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     * ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * @@param l_datVal1 - ��r����1
     * @@param l_datVal2 - ��r����2
     * @@return int
     */
    private int compare(Date l_datVal1 ,Date l_datVal2)
    {
        if (WEB3DateUtility.compareToMinute(l_datVal1, l_datVal2) == 0)
        {
            return 0;
        }
        else if (WEB3DateUtility.compareToMinute(l_datVal1,l_datVal2) > 0)
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
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;        
            }
            else
            {
                return 1;
            }
        }
    }
    
    /**
     * �icompare�̎����j<BR> 
     * �����A�~���̎w��ɂ��ƂÂ�double�^�̔�r���s���B<BR>
     * <BR>
     * �����w��̏ꍇ�A<BR>
     * �p�����[�^.l_dblVal1���p�����[�^l_dblVal2��菬�����ꍇ�͕��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_dblVal1���p�����[�^.l_dblVal2���傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     * �~���w��̏ꍇ�A<BR>
     * �p�����[�^.l_dblVal1���p�����[�^.l_dblVal2��菬�����ꍇ�͐��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.l_dblVal1���p�����[�^.l_dblVal2���傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     * ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * @@param l_dblVal1 - ��r����1
     * @@param l_dblVal2 - ��r����2
     * @@return int
     */
    private int compare(double l_dblVal1, double l_dblVal2)
    {       
        if (l_dblVal1 == l_dblVal2)
        {
            return 0;
        }
        else if (l_dblVal1 > l_dblVal2) 
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
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;
            }
            else 
            {
                return 1; 
            }                                     
        }         
    }

    /**
     * �iequals�̎����j<BR>
     * ���g�p<BR>
     * <BR>
     * false��ԋp����B<BR>
     * @@param arg0
     * @@return boolean
     */
    public boolean equals(java.lang.Object arg0)
    {
        return false;
    }

    /**
     * (�Ǘ��җ���O���������ꗗComparator)<BR>
     * �R���X�g���N�^�B<BR>
     * �����̒l��this.orderBy�A��r���ڂɃZ�b�g����B<BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>  
     *  <BR>
     *�@@A�F���� <BR>
     *�@@D�F�~�� <BR>
     * @@param l_strComarator - ��r����<BR>
     *<BR>
     * �ȉ��̂��Âꂩ�̒l���Z�b�g�����B<BR>
     *�@@�E�����R�[�h<BR>
     *�@@�E�s��R�[�h<BR>
     *�@@�E��t�J�n����<BR>
     *�@@�E��t�I������<BR>
     */
    public WEB3AdminOffFloorProductListComparator(
        String l_strOrderBy,
        String l_strComparator)
    {
        this.orderBy = l_strOrderBy;
        this.compareItem = l_strComparator;
    }
}
@
