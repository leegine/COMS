head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBizDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������Comparator(WEB3FeqBizDateComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 �s�p (���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[     
*/

package webbroker3.feq;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (������Comparator)<BR>
 * ������Comparator�N���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3FeqBizDateComparator implements Comparator 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBizDateComparator.class);
        
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     *   A�F����<BR>
     *   D�F�~��<BR>
     */
    private String orderBy;
    
    /**
     * (�J�����ԍ�)<BR>
     * ���������ڂ̃J�����ԍ�<BR>
     */
    private int columnNo;
    
    /**
     * @@roseuid 42D0CF7E00BB
     */
    public WEB3FeqBizDateComparator() 
    {
     
    }
    
    /**
     * (������Comparator)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̒l���e�p�����[�^�ɃZ�b�g����B<BR>
     * @@param l_strOrderBy - ����/�~���������t���O
     * 
     * @@param l_intColumnNo - (�J�����ԍ�)<BR>
     * �J�����ԍ�<BR>
     * @@roseuid 42AFD8FE03A8
     */
    public WEB3FeqBizDateComparator(String l_strOrderBy, int l_intColumnNo) 
    {
        final String STR_METHOD_NAME =  " WEB3FeqBizDateComparator(String, int)";
        log.entering(STR_METHOD_NAME);
        
        if(!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");
            
        }
        this.orderBy = l_strOrderBy;
        this.columnNo = l_intColumnNo;
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * �������̔�r���s���B<BR>
     * <BR>
     * �P�j������cast<BR>
     * �@@�����̖��׍s�P�A���׍s�Q��String��cast����B<BR>
     * <BR>
     * �Q�j��r<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�i���׍s�P.split(",")[this.�J�����ԍ�] < ���׍s�Q.split(",")<BR>
     * [this.�J�����ԍ�]�j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�E�i���׍s�P.split(",")[this.�J�����ԍ�] = ���׍s�Q.split(",")<BR>
     * [this.�J�����ԍ�]�j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�i���׍s�P.split(",")[this.�J�����ԍ�] > ���׍s�Q.split(",")<BR>
     * [this.�J�����ԍ��l]�j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�i���׍s�P.split(",")[this.�J�����ԍ�] < ���׍s�Q.split(",")<BR>
     * [this.�J�����ԍ��l]�j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�E�i���׍s�P.split(",")[this.�J�����ԍ�] = ���׍s�Q.split(",")<BR>
     * [this.�J�����ԍ��l]�j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�i���׍s�P.split(",")[this.�J�����ԍ�] > ���׍s�Q.split(",")<BR>
     * [this.�J�����ԍ�]�j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * @@param l_objDetailLine1 - (���׍s�P)<BR>
     * ���׍s�I�u�W�F�N�g�P<BR>
     * 
     * @@param l_objDetailLine2 - (���׍s�Q)<BR>
     * ���׍s�I�u�W�F�N�g�Q<BR>
     * 
     * @@return int
     * @@roseuid 42AFD7A4031C
     */
    public int compare(Object l_objDetailLine1, Object l_objDetailLine2) 
    {
        final String STR_METHOD_NAME =  " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j������cast
        //�����̖��׍s�P�A���׍s�Q��String��cast����B
        if ( ! (l_objDetailLine1 instanceof String) || 
            !(l_objDetailLine2 instanceof String))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);

            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�̗ތ^���s���A�Y������'String' �ތ^�B");
        }
        
        //�Q�j��r
        String l_strDetailLine1 = (String)l_objDetailLine1;
        String l_strDetailLine2 = (String)l_objDetailLine2;
       
        //[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {        
            //�E�i���׍s�P.split(",")[this.�J�����ԍ�] < ���׍s�Q.split(",")
            //[this.�J�����ԍ�]�j�̏ꍇ�A���̐����i-1�j��ԋp����B
            //�E�i���׍s�P.split(",")[this.�J�����ԍ�] = ���׍s�Q.split(",")
            //[this.�J�����ԍ�]�j�̏ꍇ�A0��ԋp����B 
            //�E�i���׍s�P.split(",")[this.�J�����ԍ�] > ���׍s�Q.split(",")
            //[this.�J�����ԍ�]�j�̏ꍇ�A���̐����i1�j��ԋp����B             
        
            String[] l_strDetailLine1s = l_strDetailLine1.split(",");
            String[] l_strDetailLine2s = l_strDetailLine2.split(",");
            
            int l_intCnt1 = l_strDetailLine1s.length;
            int l_intCnt2 = l_strDetailLine2s.length;
            
            if (l_intCnt1 - 1 < this.columnNo || l_intCnt2 - 1 < this.columnNo)
            {
                log.exiting(STR_METHOD_NAME); 
                return 0;
            }
            
            String l_str1 = l_strDetailLine1s[this.columnNo];
            String l_str2 = l_strDetailLine2s[this.columnNo];       
        
            int l_intFlag = l_str1.compareTo(l_str2);
            if (l_intFlag < 0)
            {
                log.exiting(STR_METHOD_NAME); 
                return -1;
            }
            else if (l_intFlag == 0)
            {
                log.exiting(STR_METHOD_NAME); 
                return 0;
            }
            else
            {
                log.exiting(STR_METHOD_NAME); 
                return 1;
            }
        }
        else if (WEB3AscDescDef.DESC.equals(this.orderBy))
        {        
            //�E�i���׍s�P.split(",")[this.�J�����ԍ�] < ���׍s�Q.split(",")
            //[this.�J�����ԍ�]�j�̏ꍇ�A���̐����i1�j��ԋp����B
            //�E�i���׍s�P.split(",")[this.�J�����ԍ�] = ���׍s�Q.split(",")
            //[this.�J�����ԍ�]�j�̏ꍇ�A0��ԋp����B 
            //�E�i���׍s�P.split(",")[this.�J�����ԍ�] > ���׍s�Q.split(",")
            //[this.�J�����ԍ�]�j�̏ꍇ�A���̐����i-1�j��ԋp����B             
        
            String[] l_strDetailLine1s = l_strDetailLine1.split(",");
            String[] l_strDetailLine2s = l_strDetailLine2.split(",");
            
            int l_intCnt1 = l_strDetailLine1s.length;
            int l_intCnt2 = l_strDetailLine2s.length;
            
            if (l_intCnt1 - 1 < this.columnNo || l_intCnt2 - 1 < this.columnNo)
            {
                log.exiting(STR_METHOD_NAME); 
                return 0;
            }
            
            String l_str1 = l_strDetailLine1s[this.columnNo];
            String l_str2 = l_strDetailLine2s[this.columnNo];                    
        
            int l_intFlag = l_str1.compareTo(l_str2);
            if (l_intFlag < 0)
            {
                log.exiting(STR_METHOD_NAME); 
                return 1;
            }
            else if (l_intFlag == 0)
            {
                log.exiting(STR_METHOD_NAME); 
                return 0;
            }
            else
            {
                log.exiting(STR_METHOD_NAME); 
                return -1;
            }
        }
        else 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�����A�~�� undefined.");
        }                
    }
    
    /**
     * �iequals�̎����j<BR>
     * <BR>
     * ���g�p�B<BR>
     * false��ԋp����B<BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 42AFD7A4033B
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
