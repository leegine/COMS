head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderApplicationQuantityComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\��.�w���\������Comparator(WEB3IpoOrderApplicationQuantityComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.util.WEB3LogUtility;


/**
 * IPO�\��.�w���\������Comparator
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3IpoOrderApplicationQuantityComparator implements Comparator 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderApplicationQuantityComparator.class);
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     */
    private String orderBy;
    
    /**
     * @@roseuid 411305D90238
     */
    public WEB3IpoOrderApplicationQuantityComparator() 
    {
     
    }
    
    /**
     * (IPO�\��.�w���\������Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     * 
     * @@return webbroker3.ipo.WEB3IpoOrderApplicationQuantityComparator
     * @@roseuid 40F1EC8902EF
     */
    public WEB3IpoOrderApplicationQuantityComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3IpoOrderApplicationQuantityComparator(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strOrderBy == null
            || (!l_strOrderBy.equals(WEB3AscDescDef.ASC) && !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        
        this.orderBy = l_strOrderBy;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * �w���\�����ʂ̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@������IPO�\���P�AIPO�\���Q��IPO�\���^��cast����B<BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast����IPO�\���P�AIPO�\���Q�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\������ <<BR>
     *  IPO�\���Q.IPO�\���s.�w���\�����ʁj�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\������ =<BR>
     *  IPO�\���Q.IPO�\���s.�w���\�����ʁj�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\������ ><BR>
     *  IPO�\���Q.IPO�\���s.�w���\�����ʁj�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\������ <<BR>
     *  IPO�\���Q.IPO�\���s.�w���\�����ʁj�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\������ =<BR>
     *  IPO�\���Q.IPO�\���s.�w���\�����ʁj�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\������ ><BR>
     *  IPO�\���Q.IPO�\���s.�w���\�����ʁj�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * @@param l_ipoOrder1 - (IPO�\���P)<BR>
     * IPO�\���I�u�W�F�N�g�P
     * 
     * @@param l_ipoOrder2 - (IPO�\���Q)
     * IPO�\���I�u�W�F�N�g�Q
     * 
     * @@return int
     * @@roseuid 40F1EC8902DF
     */
    public int compare(Object l_ipoOrder1, Object l_ipoOrder2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblAppQua1 = 0;
        double l_dblAppQua2 = 0;
        IpoOrderRow l_ipoOrderRow1 = null;
        IpoOrderRow l_ipoOrderRow2 = null;
        
        if (!(l_ipoOrder1 instanceof WEB3IpoOrderImpl) || !(l_ipoOrder2 instanceof WEB3IpoOrderImpl))
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3IpoOrder' �ތ^�B");
        }
        
        l_ipoOrderRow1 = (IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder1).getDataSourceObject());
        l_ipoOrderRow2 = (IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder2).getDataSourceObject());
        
        l_dblAppQua1 = l_ipoOrderRow1.getApplicationQuantity();
        l_dblAppQua2 = l_ipoOrderRow2.getApplicationQuantity();
            
        if (l_ipoOrderRow1.getApplicationQuantityIsNull() && l_ipoOrderRow2.getApplicationQuantityIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_ipoOrderRow2.getApplicationQuantityIsNull() || l_dblAppQua1 > l_dblAppQua2)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if (l_ipoOrderRow1.getApplicationQuantityIsNull() || l_dblAppQua1 < l_dblAppQua2)
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
            if (l_ipoOrderRow2.getApplicationQuantityIsNull() || l_dblAppQua1 > l_dblAppQua2)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if (l_ipoOrderRow1.getApplicationQuantityIsNull() || l_dblAppQua1 < l_dblAppQua2)
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
     * @@roseuid 40F1EC8902E2
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
