head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�������Ɖ�X�|���X�N���X(WEB3FuturesExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 ����(���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���敨�������Ɖ�X�|���X)<BR>
 * �����w���敨�������Ɖ�X�|���X�N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3FuturesExecuteReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_executeReference";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407211147L;
    /**
     * (�������Ɖ���P��)<BR>
     */
    public WEB3FuturesExecuteGroup[] futExecuteGroups;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * ���ۂɕ\������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (���y�[�W��)<BR>
     */
    public String totalPages;
    /**
     * (�������ꗗ)<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (�����R�[�h��)<BR>
     */
    public String totalRecords;
    
    /**
     * (ID�ꗗ)<BR>
     * ���������ɊY������S�����h�c<BR>
     * �i�\�[�g���ꂽ��ԁj<BR>
     * <BR>
     * �o�b�ł̏ꍇ�ݒ�<BR>
     */
    public String[] idList;
    
    /**
     * (����I���x������)<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (�����w���敨�I�v�V���������R�[�h����)<BR>
     * ���������\���p<BR>
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;
    
    /**
     * (�ڋq���b�N�敪)<BR>
     * true�F���b�N�ڋq�ł���@@false�F���b�N�ڋq�łȂ�<BR>
     */
    public boolean accountLock;
    
    /**
     * @@roseuid 40F7AE100203
     */
    public WEB3FuturesExecuteReferenceResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesExecuteReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
