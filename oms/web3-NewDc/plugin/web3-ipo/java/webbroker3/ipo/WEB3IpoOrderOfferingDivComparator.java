head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderOfferingDivComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\��.�w���\���敪Comparator(WEB3IpoOrderOfferingDivComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.util.WEB3LogUtility;


/**
 * IPO�\��.�w���\���敪Comparator
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3IpoOrderOfferingDivComparator implements Comparator 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderOfferingDivComparator.class);
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     */
    private String orderBy;
    
    /**
     * @@roseuid 411305D902D9
     */
    public WEB3IpoOrderOfferingDivComparator() 
    {
     
    }
    
    /**
     * (IPO�\��.�w���\���敪Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     * 
     * @@return WEB3IpoOrderOfferingDivComparator
     * @@roseuid 40ECEB5A0093
     */
    public WEB3IpoOrderOfferingDivComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3IpoOrderOfferingDivComparator(String)";
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
     * �w���\���敪�̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@������IPO�\���P�AIPO�\���Q��IPO�\���^��cast����B<BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast����IPO�\���P�AIPO�\���Q�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���敪 <<BR>
     *  IPO�\���Q.IPO�\���s.�w���\���敪�j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���敪 =<BR>
     *  IPO�\���Q.IPO�\���s.�w���\���敪�j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���敪 ><BR>
     *  IPO�\���Q.IPO�\���s.�w���\���敪�j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���敪 < <BR>
     * IPO�\���Q.IPO�\���s.�w���\���敪�j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���敪 =<BR>
     *  IPO�\���Q.IPO�\���s.�w���\���敪�j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���敪 ><BR>
     *  IPO�\���Q.IPO�\���s.�w���\���敪�j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * @@param l_ipoOrder1 - (IPO�\���P)<BR>
     * IPO�\���I�u�W�F�N�g�P
     * 
     * @@param l_ipoOrder2 - (IPO�\���Q)<BR>
     * IPO�\���I�u�W�F�N�g�Q
     * 
     * @@return int
     * @@roseuid 40ECEB5A008E
     */
    public int compare(Object l_ipoOrder1, Object l_ipoOrder2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        String l_strOfferDiv1 = null;
        String l_strOfferDiv2 = null;
        
        if (!(l_ipoOrder1 instanceof WEB3IpoOrderImpl) || !(l_ipoOrder2 instanceof WEB3IpoOrderImpl))
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3IpoOrder' �ތ^�B");
        }
        
        l_strOfferDiv1 = ((IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder1).getDataSourceObject())).getOfferingDiv();
        l_strOfferDiv2 = ((IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder2).getDataSourceObject())).getOfferingDiv();
                
        if (l_strOfferDiv1 == null && l_strOfferDiv2 == null)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_strOfferDiv2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if (l_strOfferDiv1 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if (l_strOfferDiv1.compareTo(l_strOfferDiv2) > 0) 
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if (l_strOfferDiv1.compareTo(l_strOfferDiv2) < 0)
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
            if (l_strOfferDiv2 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if (l_strOfferDiv1 == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if (l_strOfferDiv1.compareTo(l_strOfferDiv2) > 0) 
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if (l_strOfferDiv1.compareTo(l_strOfferDiv2) < 0)
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
     * @@roseuid 40ECEB5A0091
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
