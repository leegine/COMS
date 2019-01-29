head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �߽ܰ�ޕύX�ڋq���.�X�V��Comparator(WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo;

import java.util.Comparator;
import java.util.Date;

import webbroker3.accountinfo.message.WEB3AccInfoLoginPasswordChangeAccountInfo;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�߽ܰ�ޕύX�ڋq���.�X�V��Comparator)<BR>
 * �߽ܰ�ޕύX�ڋq���.�X�V��Comparator<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator implements Comparator 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator.class);   
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;
    
    /**
     * (�߽ܰ�ޕύX�ڋq���.�X�V��Comparator)<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B <BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator
     * @@roseuid 416B96CD0088
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator(String)";
        log.entering(STR_METHOD_NAME);

        
        if (l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");            
        }
        
        this.orderBy = l_strOrderBy;        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icompare�̎����j <BR>
     * <BR>
     * �X�V���̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�������߽ܰ�ޕύX�ڋq���P�A�߽ܰ�ޕύX�ڋq���Q��<BR>
     * �߽ܰ�ޕύX�ڋq���^��cast����B <BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast�����߽ܰ�ޕύX�ڋq���P�A�߽ܰ�ޕύX�ڋq���Q�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�X�V�� < �߽ܰ�ޕύX�ڋq���Q.�X�V���j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�X�V�� = �߽ܰ�ޕύX�ڋq���Q.�X�V���j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�X�V�� > �߽ܰ�ޕύX�ڋq���Q.�X�V���j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�X�V�� < �߽ܰ�ޕύX�ڋq���Q.�X�V���j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�X�V�� = �߽ܰ�ޕύX�ڋq���Q.�X�V���j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�X�V�� > �߽ܰ�ޕύX�ڋq���Q.�X�V���j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * @@param l_loginPasswordChangeAccountInfo1 - �߽ܰ�ޕύX�ڋq���P
     * @@param l_loginPasswordChangeAccountInfo2 - �߽ܰ�ޕύX�ڋq���Q
     * @@return int
     * @@roseuid 416B96CD008A
     */
    public int compare(Object l_loginPasswordChangeAccountInfo1, Object l_loginPasswordChangeAccountInfo2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        Date l_datUpdatedDate1 = null;
        Date l_datUpdatedDate2 = null;
         
        if ((l_loginPasswordChangeAccountInfo1 instanceof WEB3AccInfoLoginPasswordChangeAccountInfo) 
            && (l_loginPasswordChangeAccountInfo2 instanceof WEB3AccInfoLoginPasswordChangeAccountInfo))
        {          
            l_datUpdatedDate1 = ((WEB3AccInfoLoginPasswordChangeAccountInfo)l_loginPasswordChangeAccountInfo1).updateDate;            
            l_datUpdatedDate2 = ((WEB3AccInfoLoginPasswordChangeAccountInfo)l_loginPasswordChangeAccountInfo2).updateDate;
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3AccInfoLoginPasswordChangeAccountInfo'");
        }
        
        int l_intResult;
        
        if (l_datUpdatedDate1 == null || l_datUpdatedDate2 == null)
        {
               
            if (l_datUpdatedDate1 == null && l_datUpdatedDate2 == null)
            {
                l_intResult = 0;
            }
            else if (l_datUpdatedDate1 == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }            
                        
        }    
        else
        {
            if (WEB3DateUtility.compareToSecond(l_datUpdatedDate1, l_datUpdatedDate2) < 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else if (WEB3DateUtility.compareToSecond(l_datUpdatedDate1, l_datUpdatedDate2) > 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
            else
            {
                l_intResult = 0;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_intResult;
    }
    
    /**
     * �iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B <BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 416B96CD008D
     */
    public boolean equals(Object l_obj) 
    {
        return false;
    }

}
@
