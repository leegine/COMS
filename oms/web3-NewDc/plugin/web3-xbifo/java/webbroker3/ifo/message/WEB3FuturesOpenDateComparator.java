head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨����Comparator(WEB3FuturesOpenDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20   ���Ō� (Sinocom) �V�K�쐬 
*/
package webbroker3.ifo.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�敨����Comparator)<BR>
 */
public class WEB3FuturesOpenDateComparator implements Comparator 
{
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesOpenDateComparator.class);
            
    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 40F7AE110109
     */
    public WEB3FuturesOpenDateComparator() 
    {     
    }
    
    /**
     * (�敨����Comparator�̃R���X�g)<BR>
     * �敨����Comparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * �p�����[�^.orderBy���t�B�[���h��orderBy�ɃZ�b�g����<BR>
     * @@param l_strOrderBy - �\�[�g�L�[�̏����~���������B<BR>
     * <BR>
     * A�F����<BR>
     * D�F�~��<BR>
     * @@return Void
     * @@roseuid 40CFEBE90391
     */
    public WEB3FuturesOpenDateComparator(String l_strOrderBy) 
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy) 
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * �����A�~���̎w��ɂ��ƂÂ������̔�r���s���B<BR>
     * <BR>
     * �P�j�p�����[�^�̃I�u�W�F�N�g�̔���<BR>
     * <BR>
     * �@@instanceof�ɂāA�����̖���1�A����2���ȉ��̃N���X�̂ǂꂩ�𔻒肷��B<BR>
     * <BR>
     *     ���ʖ��׃N���X<BR>
     *     �����w���敨���ʏƉ�׃N���X<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �@@�P�j����1�Ɩ���2�̌����ɂ��Ĕ�r���s��<BR>
     * <BR>
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
     * @@param l_obj1 - (obj1)<BR>
     * @@param l_obj2 - (obj2)<BR>
     * @@return int
     * @@roseuid 40CFEBE90393
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        //obj1�̌���
        Date l_obj1OpenDate = null;
        //obj2�̌�����
        Date l_obj2OpenDate = null;
        
        if (l_obj1 instanceof WEB3FuturesOptionsContractUnit && l_obj2 instanceof WEB3FuturesOptionsContractUnit)
        {
            //�����w���I�v�V�������ʖ��׃N���X
            l_obj1OpenDate = ((WEB3FuturesOptionsContractUnit)l_obj1).openDate;
            l_obj2OpenDate = ((WEB3FuturesOptionsContractUnit)l_obj2).openDate;            
        }
        else if (l_obj1 instanceof WEB3FuturesContractReferenceUnit && l_obj2 instanceof WEB3FuturesContractReferenceUnit)
        {
            //�����w���敨���ʏƉ�׃N���X
            l_obj1OpenDate = ((WEB3FuturesContractReferenceUnit)l_obj1).openDate;
            l_obj2OpenDate = ((WEB3FuturesContractReferenceUnit)l_obj2).openDate;
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3FuturesCloseMarginGroup' �܂��� 'WEB3FuturesContractReferenceUnit' �ތ^�B");
        }     
        
        if (l_obj1OpenDate == null || l_obj2OpenDate == null)
        {
            int l_intResult;
               
            if (l_obj1OpenDate == null && l_obj2OpenDate == null)
            {
                l_intResult = 0;
            }
            else if (l_obj1OpenDate == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
                        
            return l_intResult;            
        }

        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //�����w��̏ꍇ
            return WEB3DateUtility.compareToDay(l_obj1OpenDate, l_obj2OpenDate);
        }
        else
        {
            //�~���w��̏ꍇ
            return -(WEB3DateUtility.compareToDay(l_obj1OpenDate, l_obj2OpenDate));
        }
    }
    
    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B<BR>
     * @@param l_obj - (obj)
     * @@return boolean
     * @@roseuid 40CFEBE90396
     */
    public boolean equals(Object l_obj) 
    {
        //�X�[�p�[�N���X��equals���R�[������B
        if (l_obj instanceof  WEB3FuturesOpenDateComparator)
        {
            WEB3FuturesOpenDateComparator l_comparator =
                    ( WEB3FuturesOpenDateComparator) l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false; 
    }
}
@
