head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondProductCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (��)�����R�[�h�iWEB3�jComparator(WEB3AdminBondProductCodeComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q(���u) �V�K�쐬
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ((��)�����R�[�h�iWEB3�jComparator)<BR>
 * (��)�����R�[�h�iWEB3�jComparator<BR>
 *  
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3AdminBondProductCodeComparator implements Comparator 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderTypeComparator.class);
    
    /**
     * orderBy<BR>
     * A�F����<BR>
     * D�F�~��
     */
    private String orderBy;
    
    /**
     * ((��)�����R�[�h(WEB3)Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����
     * @@param l_strOrderBy - (orderBy)<BR>
     * �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��
     * @@roseuid 44C03D3101AE
     */
    public WEB3AdminBondProductCodeComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AdminBondProductCodeComparator(String l_strOrderBy)";
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
     * @@roseuid 44C04F9100F6
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * �����A�~���̎w��ɂ��ƂÂ������R�[�h�iWEB3�j�̔�r���s���B<BR>
     * <BR>
     �@@�P�j�p�����[�^�����Ǘ��Ғ������Ɖ�s�ɃL���X�g����B<BR>
     * <BR>
     * �Q�j��r<BR>
     *�@@�@@�P�j�@@�P�j�ŃL���X�g��������1�A����2�̒ʉ݃R�[�h���擾����B �B<BR>
     * �@@ �@@����1�A����2�̒ʉ݂����������O���������肷��B<BR>
     * �@@ �@@�E���������u�ʉ݃R�[�h==NULL�v <BR>
     * �@@ �@@�E�O��������L�ȊO <BR>
     * <BR>
     *�@@�@@�Q�j����1���u�������v�@@���@@����2���u�������v�̏ꍇ<BR>
     *�@@�@@�@@�Q�|�P�j�@@�P�j�ŃL���X�g��������1�A����2�̖����R�[�h�iWEB3�j���̕�����ɕ�������B <BR>
     *�@@�@@�@@�@@A�����F���4�� (��r���̗D�揇��1) <BR>
     *�@@�@@�@@�@@B�����F�擪5�� (��r���̗D�揇2)<BR>  
     *�@@�@@�@@�Q�|�Q�j�����w��̏ꍇ�A<BR>
     *�@@�@@�@@�@@�Q�|�Q�|�P�j����1.A�������A����2.A������菬�����ꍇ�A���̐����i-1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�Q�|�Q�|�Q�j����1.A�������A����2.A�������傫���ꍇ�A���̐����i1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�Q�|�Q�|�R�j����1.A�������A����2.A�����Ɠ������ꍇ�A�A<BR>
     * �@@�@@�@@�@@�@@�Q�|�Q�|�R�|�P�j����1.B�������A����2.B������菬�����ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�@@�@@�@@�@@�Q�|�Q�|�R�|�Q�j����1.B�������A����2.B�������傫���ꍇ�A���̐����i1�j��ԋp���� <BR>
     * �@@�@@�@@�@@�@@�Q�|�Q�|�R�|�R�j����1.B�������A����2.B�����Ɠ������ꍇ�A0��ԋp����B<BR>
     * <BR>
     *�@@�@@�@@�Q�|�R)�~���w��̏ꍇ�A<BR>
     *�@@�@@�@@�@@�Q�|�R�|�P�j����1.A�������A����2.A������菬�����ꍇ�A���̐����i1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�Q�|�R�|�Q�j����1.A�������A����2.A�������傫���ꍇ�A���̐����i-1�j��ԋp����B <BR>  
     *�@@�@@�@@�@@�Q�|�R�|�R�j����1.A�������A����2.A�����Ɠ������ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�Q�|�R�|�R�|�P�j����1.B�������A����2.B������菬�����ꍇ�A���̐����i1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�@@�Q�|�R�|�R�|�Q�j����1.B�������A����2.B�������傫���ꍇ�A���̐����i-1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�@@�Q�|�R�|�R�|�R�j����1.B�������A����2.B�����Ɠ������ꍇ�A0��ԋp����B<BR>
     * <BR>
     *�@@�@@�R�j����1���u�������v�@@���@@����2���u�O�����v�̏ꍇ<BR>
     *�@@�@@�@@�R�|�P�j�����w��̏ꍇ�A���̐����i1�j��ԋp����B <BR> 
     *�@@�@@�@@�R�|�Q�j�~���w��̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * <BR>
     *�@@�@@�S�j����1���u�O�����v�@@���@@����2���u�������v�̏ꍇ<BR>
     *�@@�@@�@@�S�|�P�j�����w��̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     *�@@�@@�@@�S�|�Q�j�~���w��̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     *<BR>
     *�@@�@@�T�j����1���u�O�����v�@@���@@����2���u�O�����v�̏ꍇ <BR>
     *�@@�@@�@@�T�|�P�j�@@�P�j�ŃL���X�g��������1�A����2�̖����R�[�h�iWEB3�j���O�̕�����ɕ�������B<BR> 
     *�@@�@@�@@�@@A�����F�擪2�� (��r���̗D�揇��1)<BR>
     *�@@�@@�@@�@@B�����F���4�� (��r���̗D�揇2) <BR>
     *�@@�@@�@@�@@C�����F����3�� (��r���̗D�揇3)<BR>
     *<BR>
     *�@@�@@�@@�T�|�Q)�����w��̏ꍇ�A<BR>
     *�@@�@@�@@�@@�T�|�Q�|�P�j����1.A�������A����2.A������菬�����ꍇ�A���̐����i-1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�T�|�Q�|�Q�j����1.A�������A����2.A�������傫���ꍇ�A���̐����i1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�T�|�Q�|�R�j����1.A�������A����2.A�����Ɠ������ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�T�|�Q�|�R�|�P�j����1.B�������A����2.B������菬�����ꍇ�A���̐����i-1�j��ԋp����B<BR> 
     *�@@�@@�@@�@@�@@�T�|�Q�|�R�|�Q�j����1.B�������A����2.B�������傫���ꍇ�A���̐����i1�j��ԋp����B<BR> 
     *�@@�@@�@@�@@�@@�T�|�Q�|�R�|�R�j����1.B�������A����2.B�����Ɠ������ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�@@�T�|�Q�|�R�|�R�|�P)����1.C�������A����2.C������菬�����ꍇ�A���̐����i-1�j��ԋp����B<BR> 
     *�@@�@@�@@�@@�@@�@@�T�|�Q�|�R�|�R�|�Q)����1.C�������A����2.C�������傫���ꍇ�A���̐����i1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�@@�@@�T�|�Q�|�R�|�R�|�R)����1.C�������A����2.C�����Ɠ������ꍇ�A0��ԋp����B<BR>
     *<BR>
     *�@@�@@�@@�T�|�R)�~���w��̏ꍇ�A<BR>
     *�@@�@@�@@�@@�T�|�R�|�P�j����1.A�������A����2.A������菬�����ꍇ�A���̐����i1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�T�|�R�|�Q�j����1.A�������A����2.A�������傫���ꍇ�A���̐����i-1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�T�|�R�|�R�j����1.A�������A����2.A�����Ɠ������ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�T�|�R�|�R�|�P�j����1.B�������A����2.B������菬�����ꍇ�A���̐����i1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�@@�T�|�R�|�R�|�Q�j����1.B�������A����2.B�������傫���ꍇ�A���̐����i-1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�@@�T�|�R�|�R�|�R�j����1.B�������A����2.B�����Ɠ������ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�@@�T�|�R�|�R�|�R�|�P)����1.C�������A����2.C������菬�����ꍇ�A���̐����i1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�@@�@@�T�|�R�|�R�|�R�|�Q)����1.C�������A����2.C�������傫���ꍇ�A���̐����i-1�j��ԋp����B<BR>
     *�@@�@@�@@�@@�@@�@@�T�|�R�|�R�|�R�|�R)����1.C�������A����2.C�����Ɠ������ꍇ�A0��ԋp����B<BR>            �@@�@@
     * <BR>
     * �@@���~���̔���̓R���X�g���N�^�ŃZ�b�g���ꂽorderBy�̒l��p����
     * @@param l_obj1 - (����1)<BR>
     * ��r�������I�u�W�F�N�g�P
     * @@param l_obj2 - (����2)<BR>
     * ��r�������I�u�W�F�N�g�Q
     * @@return int
     * @@roseuid 44C0502D0196
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        
        
        //�P�j�p�����[�^�����Ǘ��Ғ������Ɖ�s�ɃL���X�g����B     
        String l_strVal1 = null;
        String l_strVal2 = null;
        String l_strVal3 = null;
        String l_strVal4 = null;
        
        int l_intReturn = 0;
        if(l_obj1 instanceof WEB3AdminORBondExecRefUnit && 
           l_obj2 instanceof WEB3AdminORBondExecRefUnit)
        {
            l_strVal1 = ((WEB3AdminORBondExecRefUnit) l_obj1).productCode;
            l_strVal2 = ((WEB3AdminORBondExecRefUnit) l_obj2).productCode;
            l_strVal3 = ((WEB3AdminORBondExecRefUnit) l_obj1).currencyCode;
            l_strVal4 = ((WEB3AdminORBondExecRefUnit) l_obj2).currencyCode;
        }
        else
        {

            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3AdminORBondExecRefUnit'�ތ^�B");
        }
        
        int l_intVal3Flag = 0;
        int l_intVal4Flag = 0;
        if (l_strVal3 == null)
        {
            l_intVal3Flag = 1; 
        }
        else 
        {
            l_intVal3Flag = 2;
        }
        if (l_strVal4 == null)
        {
            l_intVal4Flag = 1; 
        }
        else 
        {
            l_intVal4Flag = 2;
        }
        
        if (l_strVal1 == null || l_strVal2 == null)
        {
            if (l_strVal1 == null && l_strVal2 == null)
            {
                return 0;
            }
            else if (l_strVal1 == null) 
            {
                if (l_intVal3Flag == 1)
                {
                    if (l_intVal4Flag == 1)
                    {
                        if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                     else
                     {
                         if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                 }
                else
                {
                    if (l_intVal4Flag == 1)
                    {
                        if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                     else
                     {
                         if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
            }
            else
            {
                if (l_intVal3Flag == 1)
                {
                    if (l_intVal4Flag == 1)
                    {
                        if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                         if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                 }
                else
                {
                    if (l_intVal4Flag == 1)
                    {
                        if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                     else
                     {
                         if(WEB3AscDescDef.ASC.equals(this.orderBy))
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
                }
            }
        }
        
        if (l_intVal3Flag == 1)
        {
            //�Q�j����1���u�������v�@@���@@����2���u�������v�̏ꍇ
            if (l_intVal4Flag == 1)
            {
                //A�����F���4�� (��r���̗D�揇��1) 
                //B�����F�擪5�� (��r���̗D�揇2) 
                String l_strA1 = l_strVal1.substring(5,9);
                String l_strB1 = l_strVal1.substring(0,5);
                
                String l_strA2 = l_strVal2.substring(5,9);
                String l_strB2 = l_strVal2.substring(0,5);
                
                //�Q�|�Q�j�����w��̏ꍇ�A
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    //�Q�|�Q�|�P�j����1.A�������A����2.A������菬�����ꍇ�A���̐����i-1�j��ԋp����B
                    if (l_strA1.compareTo(l_strA2) < 0)
                    {
                        log.exiting(STR_METHOD_NAME);
                        l_intReturn = -1;
                    }
                    //�Q�|�Q�|�Q�j����1.A�������A����2.A�������傫���ꍇ�A���̐����i1�j��ԋp����B
                    else if (l_strA1.compareTo(l_strA2) > 0)
                    {
                        log.exiting(STR_METHOD_NAME);
                        l_intReturn = 1;
                    }
                    //�Q�|�Q�|�R�j����1.A�������A����2.A�����Ɠ������ꍇ�A 
                    else
                    {
                        //�Q�|�Q�|�R�|�P�j����1.B�������A����2.B������菬�����ꍇ�A���̐����i-1�j��ԋp����B
                        if (l_strB1.compareTo(l_strB2) < 0)
                        {
                            log.exiting(STR_METHOD_NAME);
                            l_intReturn = -1;
                        }
                        //�Q�|�Q�|�R�|�Q�j����1.B�������A����2.B�������傫���ꍇ�A���̐����i1�j��ԋp����B
                        else if (l_strB1.compareTo(l_strB2) > 0)
                        {
                            l_intReturn = 1;
                        }
                        //�Q�|�Q�|�R�|�R�j����1.B�������A����2.B�����Ɠ������ꍇ�A0��ԋp����B 
                        else
                        {
                            l_intReturn = 0;
                        }
                    } 
                }
                //�Q�|�R)�~���w��̏ꍇ�A �@@�@@�@@ 
                else
                {
                    //�Q�|�R�|�P�j����1.A�������A����2.A������菬�����ꍇ�A���̐����i1�j��ԋp����B 
                    if (l_strA1.compareTo(l_strA2) < 0)
                    {
                        l_intReturn = 1;
                    }
                    //�Q�|�R�|�Q�j����1.A�������A����2.A�������傫���ꍇ�A���̐����i-1�j��ԋp����B
                    else if (l_strB1.compareTo(l_strB2) > 0)
                    {
                        l_intReturn = -1;
                    }
                    //�Q�|�R�|�R�j����1.A�������A����2.A�����Ɠ������ꍇ�A
                    else
                    {
                        //�Q�|�R�|�R�|�P�j����1.B�������A����2.B������菬�����ꍇ�A���̐����i1�j��ԋp����B
                        if (l_strB1.compareTo(l_strB2) < 0)
                        {
                            l_intReturn = 1;
                        }
                        //�Q�|�R�|�R�|�Q�j����1.B�������A����2.B�������傫���ꍇ�A���̐����i-1�j��ԋp����B
                        else if (l_strB1.compareTo(l_strB2) > 0)
                        {
                            l_intReturn = -1;
                        }
                        //�Q�|�R�|�R�|�R�j����1.B�������A����2.B�����Ɠ������ꍇ�A0��ԋp����B 
                        else
                        {
                            l_intReturn = 0;
                        }
                    } 
                }  
            }
            //�@@�R�j����1���u�������v�@@���@@����2���u�O�����v�̏ꍇ 
            else
            {
                //�R�|�P�j�����w��̏ꍇ�A���̐����i1�j��ԋp����B 
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = 1;
                }
                //�R�|�Q�j�~���w��̏ꍇ�A���̐����i-1�j��ԋp����B 
                else
                {
                    l_intReturn = -1;
                }
            }
        }
        else
        {
            //�S�j����1���u�O�����v�@@���@@����2���u�������v�̏ꍇ 
            if (l_intVal4Flag == 1)
            {
                //�S�|�P�j�����w��̏ꍇ�A���̐����i-1�j��ԋp����B
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    l_intReturn = -1;
                }
                //�S�|�Q�j�~���w��̏ꍇ�A���̐����i1�j��ԋp����B
                else
                {
                    l_intReturn = 1;
                }
            }
            //�T�j����1���u�O�����v�@@���@@����2���u�O�����v�̏ꍇ
            else
            {
                //A�����F�擪2�� (��r���̗D�揇��1) 
                //B�����F���4�� (��r���̗D�揇2) 
                //C�����F����3�� (��r���̗D�揇3) 
                String l_strA1 = l_strVal1.substring(0,2);
                String l_strB1 = l_strVal1.substring(5,9);
                String l_strC1 = l_strVal1.substring(2,5);

                String l_strA2 = l_strVal2.substring(0,2);
                String l_strB2 = l_strVal2.substring(5,9);
                String l_strC2 = l_strVal2.substring(2,5);  
                
                //�T�|�Q)�����w��̏ꍇ�A 
                if(WEB3AscDescDef.ASC.equals(this.orderBy))
                {
                    //�T�|�Q�|�P�j����1.A�������A����2.A������菬�����ꍇ�A���̐����i-1�j��ԋp����B
                    if (l_strA1.compareTo(l_strA2) < 0)
                    {
                        l_intReturn = -1;
                    }
                    //�T�|�Q�|�Q�j����1.A�������A����2.A�������傫���ꍇ�A���̐����i1�j��ԋp����B 
                    else if (l_strA1.compareTo(l_strA2) > 0)
                    {
                        l_intReturn = 1;
                    }
                    //�T�|�Q�|�R�j����1.A�������A����2.A�����Ɠ������ꍇ�A 
                    else
                    {
                        //�T�|�Q�|�R�|�P�j����1.B�������A����2.B������菬�����ꍇ�A���̐����i-1�j��ԋp����B 
                        if (l_strB1.compareTo(l_strB2) < 0)
                        {
                            l_intReturn = -1; 
                        }
                        //�T�|�Q�|�R�|�Q�j����1.B�������A����2.B�������傫���ꍇ�A���̐����i1�j��ԋp����B 
                        else if (l_strB1.compareTo(l_strB2) > 0)
                        {
                            l_intReturn = 1;
                        }
                        //�T�|�Q�|�R�|�R�j����1.B�������A����2.B�����Ɠ������ꍇ�A
                        else
                        {
                            //�T�|�Q�|�R�|�R�|�P)����1.C�������A����2.C������菬�����ꍇ�A���̐����i-1�j��ԋp����B
                            if (l_strC1.compareTo(l_strC2) < 0)
                            {
                                l_intReturn = -1;
                            }
                            //�T�|�Q�|�R�|�R�|�Q)����1.C�������A����2.C�������傫���ꍇ�A���̐����i1�j��ԋp����B
                            else if (l_strC1.compareTo(l_strC2) > 0)
                            {
                                l_intReturn = 1;
                            }
                            //�T�|�Q�|�R�|�R�|�R)����1.C�������A����2.C�����Ɠ������ꍇ�A0��ԋp����B
                            else 
                            {
                                l_intReturn = 0;
                            }
                        }
 
                    }
                }
                
                //�T�|�R)�~���w��̏ꍇ�A 
                else
                {
                    //�T�|�R�|�P�j����1.A�������A����2.A������菬�����ꍇ�A���̐����i1�j��ԋp����B
                    if (l_strA1.compareTo(l_strA2) < 0)
                    {
                        l_intReturn = 1;
                    }
                    //�T�|�R�|�Q�j����1.A�������A����2.A�������傫���ꍇ�A���̐����i-1�j��ԋp����B
                    else if (l_strA1.compareTo(l_strA2) > 0)
                    {
                        l_intReturn = -1;
                    }
                    //�T�|�R�|�R�j����1.A�������A����2.A�����Ɠ������ꍇ�A 
                    else
                    {
                        //�T�|�R�|�R�|�P�j����1.B�������A����2.B������菬�����ꍇ�A���̐����i1�j��ԋp����B
                        if (l_strB1.compareTo(l_strB2) < 0)
                        {
                            l_intReturn = 1; 
                        }
                        //�T�|�R�|�R�|�Q�j����1.B�������A����2.B�������傫���ꍇ�A���̐����i-1�j��ԋp����B 
                        else if (l_strB1.compareTo(l_strB2) > 0)
                        {
                            l_intReturn = -1;
                        }
                        //�T�|�R�|�R�|�R�j����1.B�������A����2.B�����Ɠ������ꍇ�A 
                        else
                        {
                            //�T�|�R�|�R�|�R�|�P)����1.C�������A����2.C������菬�����ꍇ�A���̐����i1�j��ԋp����B
                            if (l_strC1.compareTo(l_strC2) < 0)
                            {
                                l_intReturn = 1;
                            }
                            //�T�|�R�|�R�|�R�|�Q)����1.C�������A����2.C�������傫���ꍇ�A���̐����i-1�j��ԋp����B
                            else if (l_strC1.compareTo(l_strC2) > 0)
                            {
                                l_intReturn = -1;
                            }
                            //�T�|�R�|�R�|�R�|�R)����1.C�������A����2.C�����Ɠ������ꍇ�A0��ԋp����B
                            else
                            {
                                l_intReturn = 0;
                            }
                        }
                    }
                }

            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;
    }
}
@
