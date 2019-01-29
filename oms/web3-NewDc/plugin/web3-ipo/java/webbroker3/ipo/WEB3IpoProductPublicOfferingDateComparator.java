head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoProductPublicOfferingDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO����.���J��Comparator(WEB3IpoProductPublicOfferingDateComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ���o�� �V�K�쐬
*/

package webbroker3.ipo;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * IPO����.���J��Comparator
 * 
 * @@author ���o��
 * @@version 1.0 
 */
public class WEB3IpoProductPublicOfferingDateComparator implements Comparator 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoProductPublicOfferingDateComparator.class);
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     */
    private String orderBy;
    
    /**
     * @@roseuid 41130179029C
     */
    public WEB3IpoProductPublicOfferingDateComparator() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     * 
     * @@return webbroker3.ipo.WEB3IpoProductPublicOfferingDateComparator
     * @@roseuid 40FE2C0103B0
     */
    public WEB3IpoProductPublicOfferingDateComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3IpoProductPublicOfferingDateComparator(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strOrderBy == null
                ||( !WEB3AscDescDef.ASC.equals(l_strOrderBy)
                && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B");
        }
        this.orderBy = l_strOrderBy;
        
        log.debug("orderBy = " + orderBy);
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * ���J���̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@������IPO�����P�AIPO�����Q��IPO�����s�^�iIPO����Params�j��cast����B<BR>
     * �@@���@@IPO����Params��DDL��莩����������B<BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast����IPO����Params�P�AIPO����Params�Q�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�iIPO����Params�P.���J�� < IPO����Params�Q.���J���j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B<BR>
     * �@@�E�iIPO����Params�P.���J�� = IPO����Params�Q.���J���j�̏ꍇ�A<BR>
     * 0��ԋp����B<BR>
     * �@@�E�iIPO����Params�P.���J�� > IPO����Params�Q.���J���j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�iIPO����Params�P.���J�� < IPO����Params�Q.���J���j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B<BR>
     * �@@�E�iIPO����Params�P.���J�� = IPO����Params�Q.���J���j�̏ꍇ�A<BR>
     * 0��ԋp����B<BR>
     * �@@�E�iIPO����Params�P.���J�� > IPO����Params�Q.���J���j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B<BR>
     * @@param l_product1 - IPO�����I�u�W�F�N�g�P
     * 
     * @@param l_product2 - IPO�����I�u�W�F�N�g�Q
     * 
     * @@return int
     * @@roseuid 40FE2C0103AB
     */
    public int compare(Object l_product1, Object l_product2) 
    {
        final String STR_METHOD_NAME = " compare(Object,Object)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@������cast
        IpoProductRow l_ipoProductRow1 = null;
        IpoProductRow l_ipoProductRow2 = null;
         
        if((l_product1 instanceof IpoProductParams) && (l_product2 instanceof IpoProductParams))
        {          
            log.debug("�p�����[�^�̗ތ^-------------------OK");
            l_ipoProductRow1 = (IpoProductRow)l_product1;
            
            l_ipoProductRow2 = (IpoProductRow)l_product2;                    
        }
        else
        {            
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'IpoProductParams'");                 
        }
        
        //�����w��̏ꍇ
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            log.debug("l_ipoProductRow1.getPublicOfferingDate() = " + l_ipoProductRow1.getPublicOfferingDate());
            log.debug("l_ipoProductRow2.getPublicOfferingDate() = " + l_ipoProductRow2.getPublicOfferingDate());
            
            if (WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(), l_ipoProductRow2.getPublicOfferingDate()) < 0)
            {
                log.debug("�����w��̏ꍇ--------------------------�P.���J�� < �Q.���J��");
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if(WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(),l_ipoProductRow2.getPublicOfferingDate()) == 0)
            {
                log.debug("�����w��̏ꍇ--------------------------�P.���J�� = �Q.���J��");
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            else if(WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(),l_ipoProductRow2.getPublicOfferingDate()) > 0)
            {
                log.debug("�����w��̏ꍇ--------------------------�P.���J�� > �Q.���J��");
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
        }
        //�~���w��̏ꍇ
        else if (WEB3AscDescDef.DESC.equals(this.orderBy))
        {
            if (WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(),l_ipoProductRow2.getPublicOfferingDate()) < 0)
            {
                log.debug("�~���w��̏ꍇ--------------------------�P.���J�� < �Q.���J��");
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if(WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(),l_ipoProductRow2.getPublicOfferingDate()) == 0)
            {
                log.debug("�~���w��̏ꍇ--------------------------�P.���J�� = �Q.���J��");
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            else if(WEB3DateUtility.compareToDay(l_ipoProductRow1.getPublicOfferingDate(),l_ipoProductRow2.getPublicOfferingDate()) > 0)
            {
                log.debug("�~���w��̏ꍇ--------------------------�P.���J�� > �Q.���J��");
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
        }              
        log.exiting(STR_METHOD_NAME);
        return 0;
    }
    
    /**
     * �iequals�̎����j<BR>
     * <BR>
     * ���g�p�B<BR>
     * false��ԋp����B
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 40FE2C0103AE
     */
    public boolean equals(Object l_arg0) 
    {
        final String STR_METHOD_NAME = " equals(Object)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
