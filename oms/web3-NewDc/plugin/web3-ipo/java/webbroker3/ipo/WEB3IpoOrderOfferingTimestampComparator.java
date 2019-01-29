head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderOfferingTimestampComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\��.�w���\���^���ޓ���Comparator(WEB3IpoOrderOfferingTimestampComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo;

import java.util.Comparator;
import java.sql.Timestamp;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO�\��.�w���\���^���ޓ���Comparator
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3IpoOrderOfferingTimestampComparator implements Comparator 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderOfferingTimestampComparator.class);
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     */
    private String orderBy;
    
    /**
     * @@roseuid 411305D90379
     */
    public WEB3IpoOrderOfferingTimestampComparator() 
    {
     
    }
    
    /**
     * (IPO�\��.�w���\���^���ޓ���Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     * 
     * @@return webbroker3.ipo.WEB3IpoOrderOfferingTimestampComparator
     * @@roseuid 40F1EB44031E
     */
    public WEB3IpoOrderOfferingTimestampComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3IpoOrderOfferingTimestampComparator(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strOrderBy == null
            || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        
        this.orderBy = l_strOrderBy;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * �w���\���^���ޓ����̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@������IPO�\���P�AIPO�\���Q��IPO�\���^��cast����B<BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast����IPO�\���P�AIPO�\���Q�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���^���ޓ��� < <BR>
     * IPO�\���Q.IPO�\���s.�w���\���^���ޓ����j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���^���ޓ��� =<BR>
     *  IPO�\���Q.IPO�\���s.�w���\���^���ޓ����j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���^���ޓ��� > <BR>
     * IPO�\���Q.IPO�\���s.�w���\���^���ޓ����j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���^���ޓ��� < <BR>
     * IPO�\���Q.IPO�\���s.�w���\���^���ޓ����j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���^���ޓ��� = <BR>
     * IPO�\���Q.IPO�\���s.�w���\���^���ޓ����j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�w���\���^���ޓ��� > <BR>
     * IPO�\���Q.IPO�\���s.�w���\���^���ޓ����j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * @@param l_ipoOrder1 - (IPO�\���P)<BR>
     * IPO�\���I�u�W�F�N�g�P
     * 
     * @@param l_ipoOrder2 - (IPO�\���Q)<BR>
     * IPO�\���I�u�W�F�N�g�Q
     * 
     * @@return int
     * @@roseuid 40F1EB44030E
     */
    public int compare(Object l_ipoOrder1, Object l_ipoOrder2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        Timestamp l_tsOfferTime1 = null;
        Timestamp l_tsOfferTime2 = null;
        
        if (l_ipoOrder1 instanceof WEB3IpoOrderImpl && l_ipoOrder2 instanceof WEB3IpoOrderImpl)
        {
            l_tsOfferTime1 = ((IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder1).getDataSourceObject())).getOfferingTimestamp();
            l_tsOfferTime2 = ((IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder2).getDataSourceObject())).getOfferingTimestamp();
        }
        else
        {
            throw new IllegalArgumentException("�p�����[�^�̗ތ^���s���A�Y������'WEB3IpoOrder' �ތ^�B");
        }
        
        if (WEB3DateUtility.compare(l_tsOfferTime1, l_tsOfferTime2) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        else if (WEB3DateUtility.compare(l_tsOfferTime1, l_tsOfferTime2) > 0)
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
     * @@roseuid 40F1EB440311
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
