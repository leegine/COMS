head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountInfoAccountCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �߽ܰ�ޕύX�ڋq���.�ڋq�R�[�hComparator(WEB3AdminAccInfoLoginPasswordChangeAccountInfoAccountCodeComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo;

import java.util.Comparator;

import webbroker3.accountinfo.message.WEB3AccInfoLoginPasswordChangeAccountInfo;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�߽ܰ�ޕύX�ڋq���.�ڋq�R�[�hComparator)<BR>
 * �߽ܰ�ޕύX�ڋq���.�ڋq�R�[�hComparator<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountInfoAccountCodeComparator implements Comparator 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountInfoAccountCodeComparator.class);        
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;
    
    /**
     * (�߽ܰ�ޕύX�ڋq���.�ڋq�R�[�hComparator)<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B <BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountInfoAccountCodeComparator
     * @@roseuid 415A711B0369
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountInfoAccountCodeComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3AdminAccInfoLoginPasswordChangeAccountInfoAccountCodeComparator(String)";
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
     * �ڋq�R�[�h�̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�������߽ܰ�ޕύX�ڋq���P�A�߽ܰ�ޕύX�ڋq���Q��<BR>
     * �߽ܰ�ޕύX�ڋq���^��cast����B <BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast�����߽ܰ�ޕύX�ڋq���P�A�߽ܰ�ޕύX�ڋq���Q�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�ڋq�R�[�h < �߽ܰ�ޕύX�ڋq���Q.�ڋq�R�[�h�j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�ڋq�R�[�h = �߽ܰ�ޕύX�ڋq���Q.�ڋq�R�[�h�j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�ڋq�R�[�h > �߽ܰ�ޕύX�ڋq���Q.�ڋq�R�[�h�j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�ڋq�R�[�h < �߽ܰ�ޕύX�ڋq���Q.�ڋq�R�[�h�j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�ڋq�R�[�h = �߽ܰ�ޕύX�ڋq���Q.�ڋq�R�[�h�j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i�߽ܰ�ޕύX�ڋq���P.�ڋq�R�[�h > �߽ܰ�ޕύX�ڋq���Q.�ڋq�R�[�h�j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * @@param l_loginPasswordChangeAccountInfo1 - �߽ܰ�ޕύX�ڋq���P
     * @@param l_loginPasswordChangeAccountInfo2 - �߽ܰ�ޕύX�ڋq���Q
     * @@return int
     * @@roseuid 415A711B0379
     */
    public int compare(Object l_loginPasswordChangeAccountInfo1, Object l_loginPasswordChangeAccountInfo2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        String l_strAccountCode1 = null;
        String l_strAccountCode2 = null;
         
        if ((l_loginPasswordChangeAccountInfo1 instanceof WEB3AccInfoLoginPasswordChangeAccountInfo) 
            && (l_loginPasswordChangeAccountInfo2 instanceof WEB3AccInfoLoginPasswordChangeAccountInfo))
        {          
            l_strAccountCode1 = ((WEB3AccInfoLoginPasswordChangeAccountInfo)l_loginPasswordChangeAccountInfo1).accountCode;            
            l_strAccountCode2 = ((WEB3AccInfoLoginPasswordChangeAccountInfo)l_loginPasswordChangeAccountInfo2).accountCode;
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3AccInfoLoginPasswordChangeAccountInfo'");
        }
        
        int l_intResult;
        
        if (l_strAccountCode1 == null || l_strAccountCode2 == null)
        {
               
            if (l_strAccountCode1 == null && l_strAccountCode2 == null)
            {
                l_intResult = 0;
            }
            else if (l_strAccountCode1 == null)
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
            if (l_strAccountCode1.compareTo(l_strAccountCode2) < 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else if (l_strAccountCode1.compareTo(l_strAccountCode2) > 0)
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
     * @@roseuid 415A711B037C
     */
    public boolean equals(Object l_obj) 
    {
        return false;
    }
}
@
