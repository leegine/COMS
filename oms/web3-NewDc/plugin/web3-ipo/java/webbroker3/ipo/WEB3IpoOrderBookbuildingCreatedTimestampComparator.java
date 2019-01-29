head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderBookbuildingCreatedTimestampComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\��.�V�K�\������Comparator(WEB3IpoOrderBookbuildingCreatedTimestampComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * IPO�\��.�V�K�\������Comparator
 * 
 * @@author ���C�g
 * @@version 1.0 
 */
public class WEB3IpoOrderBookbuildingCreatedTimestampComparator implements Comparator 
{
    
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderBookbuildingCreatedTimestampComparator.class);
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     */
    private String orderBy;

    /**
     * (IPO�\��.�V�K�\������Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     * 
     * @@return webbroker3.ipo.WEB3IpoOrderBookbuildingCreatedTimestampComparator
     * @@roseuid 40EB6C2A0258
     */
    public WEB3IpoOrderBookbuildingCreatedTimestampComparator(String l_strOrderBy) 
    {
        
        final String STR_METHOD_NAME =
            " WEB3IpoOrderBookbuildingCreatedTimestampComparator(String)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�����̒l��this.orderBy�ɃZ�b�g����");
        log.debug("�����̒l = " + l_strOrderBy);
        
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");
            
        }
        
        this.orderBy = l_strOrderBy;        
        
        log.debug("WEB3IpoOrderBookbuildingCreatedTimestampComparator().....OK>>>>>"); 
        log.exiting(STR_METHOD_NAME);       
     
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * �V�K�\�������̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@������IPO�\���P�AIPO�\���Q��IPO�\���^��cast����B<BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast����IPO�\���P�AIPO�\���Q�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�V�K�\������ < <BR>
     * IPO�\���Q.IPO�\���s.�V�K�\�������j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�V�K�\������ = <BR>
     * IPO�\���Q.IPO�\���s.�V�K�\�������j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�V�K�\������ > <BR>
     * IPO�\���Q.IPO�\���s.�V�K�\�������j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�V�K�\������ <<BR>
     *  IPO�\���Q.IPO�\���s.�V�K�\�������j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�V�K�\������ =<BR>
     *  IPO�\���Q.IPO�\���s.�V�K�\�������j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�iIPO�\���P.IPO�\���s.�V�K�\������ ><BR>
     *  IPO�\���Q.IPO�\���s.�V�K�\�������j�̏ꍇ�A���̐����i-1�j��ԋp����B
     * @@param l_ipoOrder1 - (IPO�\���P)<BR>
     * IPO�\���I�u�W�F�N�g�P
     * 
     * @@param l_ipoOrder2 - (IPO�\���Q)<BR>
     * IPO�\���I�u�W�F�N�g�Q
     * 
     * @@return int
     * @@roseuid 40EB6C1B03B0
     */
    public int compare(Object l_ipoOrder1, Object l_ipoOrder2) 
    {
        
        final String STR_METHOD_NAME =
            " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        Timestamp l_dblVal1 = null;
        Timestamp l_dblVal2 = null;
        IpoOrderRow l_orderRow1 = null;
        IpoOrderRow l_orderRow2 = null;
         
        if((l_ipoOrder1 instanceof WEB3IpoOrderImpl) && (l_ipoOrder2 instanceof WEB3IpoOrderImpl))
        {
          
            l_orderRow1 = (IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder1).getDataSourceObject());
            l_dblVal1 = l_orderRow1.getBookbuildingCreatedTimestamp();
            
            l_orderRow2 = (IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder2).getDataSourceObject());
            l_dblVal2 = l_orderRow2.getBookbuildingCreatedTimestamp();            
                    
        }
        else
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3IpoOrder'"); 
                
        }
        if (l_dblVal1 == l_dblVal2)
        {
            
            log.debug("��t��Ԃ̔�r.....OK>>>>>");
            log.exiting(STR_METHOD_NAME);
            return 0;
            
        }
        else if (WEB3DateUtility.compare(l_dblVal1, l_dblVal2) > 0) 
        {
            
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                
                log.debug("��t��Ԃ̔�r.....OK>>>>>");
                log.exiting(STR_METHOD_NAME);
                return 1;
                
            }
            else 
            {
                
                log.debug("��t��Ԃ̔�r.....OK>>>>>");
                log.exiting(STR_METHOD_NAME);
                return -1; 
                
            }
            
        }
        else
        {
            
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                
                log.debug("��t��Ԃ̔�r.....OK>>>>>");
                log.exiting(STR_METHOD_NAME);
                return -1;
                
            }
            else 
            {
                
                log.debug("��t��Ԃ̔�r.....OK>>>>>");
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
     * @@roseuid 40EB6C1B03B3
     */
    public boolean equals(Object l_arg0) 
    {

        return false;

    }
}
@