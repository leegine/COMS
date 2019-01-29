head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderBranchCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\��.���X�R�[�hComparator(WEB3IpoOrderBranchCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * IPO�\��.���X�R�[�hComparator
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3IpoOrderBranchCodeComparator implements Comparator 
{
    
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderBranchCodeComparator.class);
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     */
    private String orderBy;

    /**
     * (IPO�\��.���X�R�[�hComparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     * 
     * @@return webbroker3.ipo.WEB3IpoOrderBranchCodeComparator
     * @@roseuid 40EB6D8B0316
     */
    public WEB3IpoOrderBranchCodeComparator(String l_strOrderBy) 
    {
        
        final String STR_METHOD_NAME =
            " WEB3IpoOrderBranchCodeComparator(String)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�����̒l��this.orderBy�ɃZ�b�g����");
        log.debug("�����̒l = " + l_strOrderBy);
        
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");
            
        }
        
        this.orderBy = l_strOrderBy;        
        
        log.debug("WEB3IpoOrderBranchCodeComparator().....OK>>>>>");
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * ���X�R�[�h�̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@������IPO�\���P�AIPO�\���Q��IPO�\���^��cast����B<BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast����IPO�\���P�AIPO�\���Q�ɂ��� <BR>
     * ���ꂼ���IPO�\��.IPO�\���s.���X�h�c�ɊY������ڋq���擾����B<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�iIPO�\���P.���X.getBranchCode() < IPO�\���Q.���X.getBranchCode()�j<BR>�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.���X.getBranchCode() = IPO�\���Q.���X.getBranchCode()�j<BR>�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.���X.getBranchCode() > IPO�\���Q.���X.getBranchCode()�j<BR>�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�iIPO�\���P.���X.getBranchCode() < IPO�\���Q.���X.getBranchCode()�j<BR>�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.���X.getBranchCode() = IPO�\���Q.���X.getBranchCode()�j<BR>�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.���X.getBranchCode() > IPO�\���Q.���X.getBranchCode()�j<BR>�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * @@param l_ipoOrder1 - (IPO�\���P)<BR>
     * IPO�\���I�u�W�F�N�g�P
     * 
     * @@param l_ipoOrder2 - (IPO�\���Q)<BR>
     * IPO�\���I�u�W�F�N�g�Q
     * 
     * @@return int
     * @@roseuid 40EB6D8B0304
     */
    public int compare(Object l_ipoOrder1, Object l_ipoOrder2) 
    {

        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        String l_strVal1 = null;
        String l_strVal2 = null;
         
        if((l_ipoOrder1 instanceof WEB3IpoOrderImpl) && (l_ipoOrder2 instanceof WEB3IpoOrderImpl))
        {
          
            l_strVal1 = ((WEB3IpoOrderImpl)l_ipoOrder1).getMainAccount().getBranch().getBranchCode();;
            
            l_strVal2 = ((WEB3IpoOrderImpl)l_ipoOrder2).getMainAccount().getBranch().getBranchCode();;           
                    
        }
        else
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3IpoOrder'"); 
                
        }
        if (l_strVal1.compareTo(l_strVal2) == 0)
        {
            
            log.debug("��t��Ԃ̔�r.....OK>>>>>");
            log.exiting(STR_METHOD_NAME);
            return 0;
            
        }        
        if (l_strVal1 == null && l_strVal2 == null)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_strVal2 == null || (l_strVal1 != null && l_strVal1.compareTo(l_strVal2) > 0))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if (l_strVal1 == null || (l_strVal2 != null && l_strVal1.compareTo(l_strVal2) < 0))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }
        else
        {
            if (l_strVal2 == null || (l_strVal1 != null && l_strVal1.compareTo(l_strVal2) > 0))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if (l_strVal1 == null || (l_strVal2 != null && l_strVal1.compareTo(l_strVal2) < 0))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
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
     * @@roseuid 40EB6D8B0314
     */
    public boolean equals(Object l_arg0) 
    {

        return false;

    }
}
@
