head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.00.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointApplyTimestampComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �\������Comparator(WEB3AdminPointApplyTimestampComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.point.message.WEB3AdminPointApplyDetail;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�\������Comparator)<BR>
 * �\������Comparator�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointApplyTimestampComparator implements Comparator 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPointApplyTimestampComparator.class);        
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F����<BR>
     * �@@D�F�~��<BR>
     */
    private String orderBy;
    
    /**
     * (�\������Comparator)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B<BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F����<BR>
     * �@@D�F�~��<BR>
     * 
     * @@roseuid 419C09A1028A
     */
    public WEB3AdminPointApplyTimestampComparator(String l_strOrderBy) 
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
     * �\�������̔�r���s���B<BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@�����̃|�C���g�\�����ׂP�A�|�C���g�\�����ׂQ���|�C���g�\�����׌^��<BR>
     * cast����B<BR>
     * <BR>
     * �Q�j�@@��r<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�i�|�C���g�\�����ׂP.�\������ < �|�C���g�\�����ׂQ.�\�������j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B<BR>
     * �@@�E�i�|�C���g�\�����ׂP.�\������ = �|�C���g�\�����ׂQ.�\�������j�̏ꍇ�A<BR>
     * 0��ԋp����B<BR>
     * �@@�E�i�|�C���g�\�����ׂP.�\������ > �|�C���g�\�����ׂQ.�\�������j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�i�|�C���g�\�����ׂP.�\������ < �|�C���g�\�����ׂQ.�\�������j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B<BR>
     * �@@�E�i�|�C���g�\�����ׂP.�\������ = �|�C���g�\�����ׂQ.�\�������j�̏ꍇ�A<BR>
     * 0��ԋp����B<BR>
     * �@@�E�i�|�C���g�\�����ׂP.�\������ > �|�C���g�\�����ׂQ.�\�������j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B<BR>
     * @@param l_pointApplyDetail1 - (�|�C���g�\�����ׂP)<BR>
     * �|�C���g�\�����׃I�u�W�F�N�g<BR>
     * 
     * @@param l_pointApplyDetail2 - (�|�C���g�\�����ׂQ)<BR>
     * �|�C���g�\�����׃I�u�W�F�N�g<BR>
     * 
     * @@return int
     * @@roseuid 419C09A102A9
     */
    public int compare(Object l_pointApplyDetail1, Object l_pointApplyDetail2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME );
        
        //�P�j�@@������cast
        if( ! (l_pointApplyDetail1 instanceof WEB3AdminPointApplyDetail) 
            || !(l_pointApplyDetail2 instanceof WEB3AdminPointApplyDetail))
        {
            String l_strErrorMessage = 
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3AdminPointApplyDetail' �ތ^�B";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }
        
        WEB3AdminPointApplyDetail l_pointApplyDetail1_alias = (WEB3AdminPointApplyDetail )l_pointApplyDetail1;
        WEB3AdminPointApplyDetail l_pointApplyDetail2_alias = (WEB3AdminPointApplyDetail )l_pointApplyDetail2;
        
        //�Q�j�@@��r
        if(WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_pointApplyDetail1_alias.applyDate == null && l_pointApplyDetail2_alias.applyDate != null)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            if (l_pointApplyDetail1_alias.applyDate == null && l_pointApplyDetail2_alias.applyDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            if (l_pointApplyDetail1_alias.applyDate != null && l_pointApplyDetail2_alias.applyDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }            
            
            if (WEB3DateUtility.compareToSecond(l_pointApplyDetail1_alias.applyDate, l_pointApplyDetail2_alias.applyDate) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            if (WEB3DateUtility.compareToSecond(l_pointApplyDetail1_alias.applyDate, l_pointApplyDetail2_alias.applyDate) == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            log.exiting(STR_METHOD_NAME);
            return 1;
        }
        if (WEB3AscDescDef.DESC.equals(this.orderBy))
        { 
            if (l_pointApplyDetail1_alias.applyDate == null && l_pointApplyDetail2_alias.applyDate != null)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            if (l_pointApplyDetail1_alias.applyDate == null && l_pointApplyDetail2_alias.applyDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            if (l_pointApplyDetail1_alias.applyDate != null && l_pointApplyDetail2_alias.applyDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }            
            
            if (WEB3DateUtility.compareToSecond(l_pointApplyDetail1_alias.applyDate, l_pointApplyDetail2_alias.applyDate) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            if (WEB3DateUtility.compareToSecond(l_pointApplyDetail1_alias.applyDate, l_pointApplyDetail2_alias.applyDate) == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            log.exiting(STR_METHOD_NAME);
            return -1;
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
     * false��ԋp����B<BR>
     * @@param l_arg0 - (arg0)
     * @@return boolean
     * @@roseuid 419C09A102C8
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
