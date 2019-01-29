head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderAccountCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\��.�ڋq�R�[�hComparator(WEB3IpoOrderAccountCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 �ė� (���u) �V�K�쐬
*/


package webbroker3.ipo;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO�\��.�ڋq�R�[�hComparator
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3IpoOrderAccountCodeComparator implements Comparator 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderAccountCodeComparator.class);
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     */
    private String orderBy;
    
    /**
     * @@roseuid 411305DC012E
     */
    public WEB3IpoOrderAccountCodeComparator() 
    {
     
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * �ڋq�R�[�h�̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@������IPO�\���P�AIPO�\���Q��IPO�\���^��cast����B<BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast����IPO�\���P�AIPO�\���Q�ɂ��āA<BR>
     * ���ꂼ���IPO�\��.get�����h�c()�ɊY������ڋq���擾����B<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�iIPO�\���P.�ڋq.getAccountCode() < IPO�\���Q.�ڋq.getAccountCode()�j<BR>
     * �̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.�ڋq.getAccountCode() = IPO�\���Q.�ڋq.getAccountCode()�j<BR>�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.�ڋq.getAccountCode() > IPO�\���Q.�ڋq.getAccountCode()�j<BR>�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�iIPO�\���P.�ڋq.getAccountCode() < IPO�\���Q.�ڋq.getAccountCode()�j<BR>�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.�ڋq.getAccountCode() = IPO�\���Q.�ڋq.getAccountCode()�j<BR>�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.�ڋq.getAccountCode() > IPO�\���Q.�ڋq.getAccountCode()�j<BR>�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * @@param l_ipoOrder1 - (IPO�\���P)<BR>
     * IPO�\���I�u�W�F�N�g�P
     * 
     * @@param l_ipoOrder2 - (IPO�\���Q)<BR>
     * IPO�\���I�u�W�F�N�g�Q
     * 
     * @@return int
     * @@roseuid 40EB693A0391
     */
    public int compare(Object l_ipoOrder1, Object l_ipoOrder2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        String l_strACode1 = null;
        String l_strACode2 = null;
        
        if (l_ipoOrder1 instanceof WEB3IpoOrderImpl && l_ipoOrder2 instanceof WEB3IpoOrderImpl)
        {
            l_strACode1 = ((WEB3IpoOrderImpl)l_ipoOrder1).getMainAccount().getAccountCode();
            l_strACode2 = ((WEB3IpoOrderImpl)l_ipoOrder2).getMainAccount().getAccountCode();
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3IpoOrder' �ތ^�B");
        }
        
        if (l_strACode1.equals(l_strACode2))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else if (l_strACode1.compareTo(l_strACode2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
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
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
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
    
    /**
     * �iequals�̎����j<BR>
     * <BR>
     * ���g�p�B<BR>
     * false��ԋp����B
     * @@param l_arg0 - (arg0)
     * @@return boolean
     * @@roseuid 40EB6B7E02B6
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
    
    /**
     * (IPO�\��.�ڋq�R�[�hComparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     * 
     * @@roseuid 40EB6C670093
     */
    public WEB3IpoOrderAccountCodeComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3IpoOrderAccountCodeComparator(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strOrderBy == null
            || (!l_strOrderBy.equals(WEB3AscDescDef.ASC) && !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        
        this.orderBy = l_strOrderBy;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
