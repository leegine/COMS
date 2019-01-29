head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingOrderActionCreatedTimestampComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �u�b�N�r���f�B���O�\������.�쐬����Comparator(WEB3IpoBookbuildingOrderActionCreatedTimestampComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �u�b�N�r���f�B���O�\������.�쐬����Comparator
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingOrderActionCreatedTimestampComparator implements Comparator 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IpoBookbuildingOrderActionCreatedTimestampComparator.class);        
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     */
    private String orderBy;
    
    /**
     * @@roseuid 4113083400CD
     */
    public WEB3IpoBookbuildingOrderActionCreatedTimestampComparator() 
    {
     
    }
    
    /**
     * (�u�b�N�r���f�B���O�\������.�쐬����Comparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     * 
     * @@return webbroker3.ipo.WEB3IpoBookbuildingOrderActionCreatedTimestampComparator
     * @@roseuid 40EB6D17020A
     */
    public WEB3IpoBookbuildingOrderActionCreatedTimestampComparator(String l_strOrderBy) 
    {
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");
            
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * �쐬�����̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@�����̃u�b�N�r���f�B���O�\�������P�A�u�b�N�r���f�B���O�\�������Q��<BR>
     * �u�b�N�r���f�B���O�\�������^��cast����B<BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast�����u�b�N�r���f�B���O�\�������P�A�u�b�N�r���f�B���O�\�������Q�ɂ���<BR> 
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�i�u�b�N�r���f�B���O�\�������P.get�쐬����() < �u�b�N�r���f�B���O<BR>
     * �\�������Q.get�쐬����()�j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�E�i�u�b�N�r���f�B���O�\�������P.get�쐬����() = �u�b�N�r���f�B���O<BR>
     * �\�������Q.get�쐬����()�j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�i�u�b�N�r���f�B���O�\�������P.get�쐬����() > �u�b�N�r���f�B���O<BR>
     * �\�������Q.get�쐬����()�j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�i�u�b�N�r���f�B���O�\�������P.get�쐬����() < �u�b�N�r���f�B���O<BR>
     * �\�������Q.get�쐬����()�j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�E�i�u�b�N�r���f�B���O�\�������P.get�쐬����() = �u�b�N�r���f�B���O<BR>
     * �\�������Q.get�쐬����()�j�̏ꍇ�A0��ԋp����B
     * �@@�E�i�u�b�N�r���f�B���O�\�������P.get�쐬����() > �u�b�N�r���f�B���O<BR>
     * �\�������Q.get�쐬����()�j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * <BR>
     * @@param l_bookbuildingOrderAction1 - (�ޯ�����ިݸސ\�������P)<BR>
     * �ޯ�����ިݸސ\�������I�u�W�F�N�g�P
     * 
     * @@param l_bookbuildingOrderAction2 - �ޯ�����ިݸސ\�������I�u�W�F�N�g�Q
     * 
     * @@return int
     * @@roseuid 40EB6D1701FA
     */
    public int compare(Object l_bookbuildingOrderAction1, Object l_bookbuildingOrderAction2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@������cast
        if( ! (l_bookbuildingOrderAction1 instanceof WEB3IpoBookbuildingOrderActionImpl) 
            || !(l_bookbuildingOrderAction2 instanceof WEB3IpoBookbuildingOrderActionImpl))
        {
            String l_strErrorMessage = 
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3IpoBookbuildingOrderActionImpl' �ތ^�B";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }
        
        WEB3IpoBookbuildingOrderActionImpl l_bookBuildingIpoOrderAction1 = 
            (WEB3IpoBookbuildingOrderActionImpl )l_bookbuildingOrderAction1;
        WEB3IpoBookbuildingOrderActionImpl l_bookBuildingIpoOrderAction2 = 
            (WEB3IpoBookbuildingOrderActionImpl )l_bookbuildingOrderAction2;

        // �Q�j�@@��r
        Timestamp l_orderActionTimestamp1 = l_bookBuildingIpoOrderAction1.getOrderActionTimestamp();
        Timestamp l_orderActionTimestamp2 = l_bookBuildingIpoOrderAction2.getOrderActionTimestamp();
        
        //      compare
        if(WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if(WEB3DateUtility.compare(l_orderActionTimestamp1, l_orderActionTimestamp2) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if(WEB3DateUtility.compare(l_orderActionTimestamp1, l_orderActionTimestamp2) == 0)
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
        else if(WEB3AscDescDef.DESC.equals(this.orderBy))
        { 
            if(WEB3DateUtility.compare(l_orderActionTimestamp1, l_orderActionTimestamp2) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if(WEB3DateUtility.compare(l_orderActionTimestamp1, l_orderActionTimestamp2) == 0)
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
            String l_strErrorMessage = 
                "�����A�~�� undefined.";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }
    }
    
    /**
     * �iequals�̎����j<BR>
     * <BR>
     * ���g�p�B<BR>
     * false��ԋp����B
     * @@param l_arg0 - (arg0)
     * @@return boolean
     * @@roseuid 40EB6D1701FD
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
