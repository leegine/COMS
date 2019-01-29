head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractUnitFirstOpenDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������_��������Comparator(WEB3MarginContractUnitFirstOpenDateComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/06 ���{ ����q (SRA) �V�K�쐬
*/

package webbroker3.equity;

import java.util.Comparator;
import java.util.Date;
import webbroker3.util.WEB3LogUtility;
import webbroker3.equity.WEB3MarginTempContractUnit;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;

/**
 * �i��������_��������Comparator�j�B<BR>
 * <BR>
 * ��������_��������Comparator
 * @@version 1.0
 */
public class WEB3MarginContractUnitFirstOpenDateComparator implements Comparator 
{
    /**
    * (���O���[�e�B���e�B)<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3MarginContractUnitFirstOpenDateComparator.class);
    
    /**
     * A�F����<BR>
     * D�F�~��<BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 4142B329039D
     */
    public WEB3MarginContractUnitFirstOpenDateComparator() 
    {
     
    }
    
    /**
     * (��������_��������Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * ����.orderBy��this.orderBy�ɃZ�b�g����B <BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * @@return WEB3MarginContractUnitFirstOpenDateComparator
     */
    public WEB3MarginContractUnitFirstOpenDateComparator(String l_strOrderBy) 
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy)
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * �icompare�̎����j <BR>
     * <BR>
     * ���������̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̌�������1�A��������2���A�M�p�����������Temp�^��cast����B <BR>
     * <BR>
     * �R�j�@@��r <BR>
     * �@@�Q�j��cast������������1�A��������2�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i��������1.�������� < ��������2.���������j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR> 
     * �@@�E�i��������1.�������� == ��������2.���������j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i��������1.�������� > ��������2.���������j�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i��������1.�������� < ��������2.���������j�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�E�i��������1.�������� == ��������2.���������j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i��������1.�������� > ��������2.���������j�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * @@param l_obj1 - (��������1)<BR>
     * �M�p����������׃I�u�W�F�N�g
     * 
     * @@param l_obj2 - (��������2)<BR>
     * �M�p����������׃I�u�W�F�N�g
     * @@return int
     * @@roseuid 41060CD90017
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        //obj1�̓�������
        Date l_obj1OpenDate = null;
        //obj2�̓�������
        Date l_obj2OpenDate = null;
        
        //������cast
        if (l_obj1 instanceof WEB3MarginTempContractUnit && l_obj2 instanceof WEB3MarginTempContractUnit)
        {
            //�M�p�����������Temp�^�N���X
            l_obj1OpenDate = ((WEB3MarginTempContractUnit)l_obj1).firstOpenDate;
            l_obj2OpenDate = ((WEB3MarginTempContractUnit)l_obj2).firstOpenDate;            
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3MarginTempContractUnit' �ތ^�B");
        }
        
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //�����w��̏ꍇ
            if (WEB3DateUtility.compareToDay(l_obj1OpenDate, l_obj2OpenDate) < 0)
            {
                return -1;
            }
            else if (WEB3DateUtility.compareToDay(l_obj1OpenDate, l_obj2OpenDate) == 0)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            //�~���w��̏ꍇ
            if (WEB3DateUtility.compareToDay(l_obj1OpenDate, l_obj2OpenDate) < 0)
            {
                return 1;
            }
            else if (WEB3DateUtility.compareToDay(l_obj1OpenDate, l_obj2OpenDate) == 0)
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }
    }
    
    /**
     * �iequals�̎����j <BR>
     * <BR>
     * ���g�p�B<BR> 
     * false��ԋp����B<BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 41060CD9001A
     */
    public boolean equals(Object l_arg0) 
    {
        //�X�[�p�[�N���X��equals���R�[������B
        if (l_arg0 instanceof  WEB3MarginContractUnitFirstOpenDateComparator)
        {
            WEB3MarginContractUnitFirstOpenDateComparator l_comparator =
                    ( WEB3MarginContractUnitFirstOpenDateComparator) l_arg0;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false; 
    }
}
@
