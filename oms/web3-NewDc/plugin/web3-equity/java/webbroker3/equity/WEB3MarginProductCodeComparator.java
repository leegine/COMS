head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginProductCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p�����R�[�hComparator(WEB3MarginProductCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 ������ (���u) �V�K�쐬
*/
package webbroker3.equity;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.message.WEB3MarginCloseMarginGroup;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�M�p�����R�[�hComparator�j�B<BR>
 * <BR>
 * �M�p�����R�[�hComparator
 * @@version 1.0
 */
public class WEB3MarginProductCodeComparator implements Comparator 
{ 
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginProductCodeComparator.class);    
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 4142B32A0268
     */
    public WEB3MarginProductCodeComparator() 
    {
     
    }
    
    /**
     * (�M�p�����R�[�hComparator)<BR>
     * �M�p�����R�[�hComparator�̃R���X�g���N�^�B<BR>
     * <BR>
     * ����.orderBy��this.orderBy�ɃZ�b�g����B <BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F����  <BR>
     * �@@D�F�~��  <BR>
     * @@return WEB3MarginProductCodeComparator
     * @@roseuid 40F341650168
     */
    public WEB3MarginProductCodeComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME="WEB3MarginProductCodeComparator(String)";
        log.entering(STR_METHOD_NAME);
        if (l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("�����̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy=l_strOrderBy;
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *�icompare�̎����j <BR>
     * <BR>
     * �����R�[�h�̔�r���s���B <BR>
     * <BR>
     * �P�j�@@�����̃I�u�W�F�N�g�̔���<BR>
     * instanceof�ɂāA�����̃I�u�W�F�N�g�������1�A<BR>
     * �������2���ȉ��̃N���X�̂ǂ��炩�𔻒肷��B <BR>
     * <BR>
     * �M�p����������N���X<BR>
     * �M�p������ψꗗ�s�N���X<BR>
     * <BR>
     * �Q�j�@@������cast <BR>
     * �@@�����̌������1�A�������2���A�P�j�Ŕ��肵���N���X�̌^��cast����B<BR> 
     * <BR>
     * �R�j�@@��r <BR>
     * �@@�Q�j��cast�����������1�A�������2�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i�������1.�����R�[�h < �������2.�����R�[�h�j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i�������1.�����R�[�h == �������2.�����R�[�h�j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i�������1.�����R�[�h > �������2.�����R�[�h�j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i�������1.�����R�[�h < �������2.�����R�[�h�j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * �@@�E�i�������1.�����R�[�h == �������2.�����R�[�h�j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i�������1.�����R�[�h > �������2.�����R�[�h�j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * @@param l_obj1 - (�������1)<BR>
     * �M�p����������I�u�W�F�N�g1�A�܂��́A�M�p������ψꗗ�s�I�u�W�F�N�g1
     * @@param l_obj2 - (�������2)<BR>
     * �M�p����������I�u�W�F�N�g2�A�܂��́A�M�p������ψꗗ�s�I�u�W�F�N�g2
     * @@return int
     * @@roseuid 40F34165016C
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME="compare(object,object)";
        log.entering(STR_METHOD_NAME);
    
        String l_strproductCode1;
        String l_strproductCode2;
        if ((l_obj1 instanceof WEB3MarginContractInfo) && (l_obj2 instanceof WEB3MarginContractInfo))
        {
            l_strproductCode1=((WEB3MarginContractInfo)l_obj1).productCode;
            l_strproductCode2=((WEB3MarginContractInfo)l_obj2).productCode;
        }else if ((l_obj1 instanceof WEB3MarginCloseMarginGroup ) && (l_obj2 instanceof WEB3MarginCloseMarginGroup))
        {
            l_strproductCode1=((WEB3MarginCloseMarginGroup)l_obj1).productCode;
            l_strproductCode2=((WEB3MarginCloseMarginGroup)l_obj2).productCode;
        }
        else
        {        
            String l_strErrorMessage = "�����̗ތ^���s��";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,STR_METHOD_NAME);
        }
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (Double.parseDouble(l_strproductCode1) <Double.parseDouble(l_strproductCode2))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }else if (Double.parseDouble(l_strproductCode1) == Double.parseDouble(l_strproductCode2))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }else
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }    
        }
        if (WEB3AscDescDef.DESC.equals(this.orderBy))
        {
            if (Double.parseDouble(l_strproductCode1) < Double.parseDouble(l_strproductCode2))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }else if (Double.parseDouble(l_strproductCode1) == Double.parseDouble(l_strproductCode2))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }else
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }    
        }
        log.exiting(STR_METHOD_NAME);
     return 0;
    }
    
    /**
     *�iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B <BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 40F34165016A
     */
    public boolean equals(Object l_arg0) 
    {
     return true;
    }
}
@
