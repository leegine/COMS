head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualAppraisalProfitLossComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�����vComparator�����N���X(WEB3MutualAppraisalProfitLossComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���E (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * �]�����vComparator�����N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualAppraisalProfitLossComparator implements Comparator 
{
    
    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;
    
    /**
     * �]�����vComparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     * @@roseuid 40B1B86400AA
     */
    public WEB3MutualAppraisalProfitLossComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AppraisalProfitLossComparator(String l_strOrderBy)";
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
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualAppraisalProfitLossComparator.class);   
              
    /**
     * �����A�~���̎w��ɂ��ƂÂ��]�����v�̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�𓊐M���抷�����ꗗ�s�N���X�ň������L���X�g����B<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �P�j�Ŕ��肵���N���X�̃C���X�^���Xobj1��obj2���ꂼ��̕]�����v�ɂ���<BR>
     * <BR>
     * �����w��̏ꍇ�A<BR>
     * �p�����[�^.obj1�̕]�����v���A<BR>
     * �p�����[�^.obj2�̕]�����v��菬�����ꍇ�͕��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.obj1�̕]�����v���A<BR>
     * �p�����[�^.obj2�̕]�����v���傫���ꍇ�͐��̐�����ԋp����<BR>
     * <BR>
     * �~���w��̏ꍇ�A<BR>
     * �p�����[�^.obj1�̕]�����v���A<BR>
     * �p�����[�^.obj2�̕]�����v��菬�����ꍇ�͐��̐����A<BR>
     * �������������ꍇ��0�A<BR>
     * �p�����[�^.obj1�̕]�����v���A<BR>
     * �p�����[�^.obj2�̕]�����v���傫���ꍇ�͕��̐�����ԋp����<BR>
     * <BR>
     * ���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����<BR>
     * <BR>
     * @@param l_obj1
     * @@param l_obj2
     * @@return int
     * @@roseuid 40B1B86400CA
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);   
      
        WEB3MutualSellSwitchingProductGroup l_mutualSellSwitchingProductGroup1 = null;
        WEB3MutualSellSwitchingProductGroup l_mutualSellSwitchingProductGroup2 = null;
        
        if(l_obj1 instanceof WEB3MutualSellSwitchingProductGroup
                && l_obj2 instanceof WEB3MutualSellSwitchingProductGroup)
        {
            l_mutualSellSwitchingProductGroup1 = (WEB3MutualSellSwitchingProductGroup)l_obj1;
            l_mutualSellSwitchingProductGroup2 = (WEB3MutualSellSwitchingProductGroup)l_obj2;   
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������" 
                    + "'WEB3MutualSellSwitchingProductGroup'�ތ^�B");
        }
        
        //U00889�Ή�
        //srart
        if (l_mutualSellSwitchingProductGroup1.appraisalProfitLoss == null &&
              l_mutualSellSwitchingProductGroup2.appraisalProfitLoss == null)
        {
            return 0;
        }
        
        if (l_mutualSellSwitchingProductGroup1.appraisalProfitLoss == null)
        {
            return 1;
        }
        
        else if (l_mutualSellSwitchingProductGroup2.appraisalProfitLoss == null)
        {
            return -1;
        }
        
        //�����w��̏ꍇ
        long l_longObj1 = Long.parseLong(l_mutualSellSwitchingProductGroup1.appraisalProfitLoss);
        long l_longObj2 = Long.parseLong(l_mutualSellSwitchingProductGroup2.appraisalProfitLoss);
        if(WEB3AscDescDef.ASC.equals(orderBy))
        {
            if(l_longObj1 < l_longObj2)
            {
                return -1;
            }
            else if(l_longObj1 == l_longObj2)
            {
                return 0;
            }
            else if(l_longObj1 > l_longObj2)
            {
                return 1;
            }            
        }
        //�~���w��̏ꍇ
        else if(WEB3AscDescDef.DESC.equals(orderBy))
        {
            if(l_longObj1 < l_longObj2)
            {
                return 1;
            }
            else if(l_longObj1 == l_longObj2)
            {
                return 0;
            }
            else if(l_longObj1 > l_longObj2)
            {
                return -1;
            }
        }
                
        log.exiting(STR_METHOD_NAME); 
        return 0;
    }
    //end
    
    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B<BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 40B1B86400D9
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}@
