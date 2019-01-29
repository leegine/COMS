head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����������Ɖ�X�|���X(WEB3OptionsExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;


/**
 * (�����w���I�v�V�����������Ɖ�X�|���X)<BR>
 * �����w���I�v�V�����������Ɖ�X�|���X�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsExecuteReferenceResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_executeReference";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111108L;

    /**
     * �������Ɖ���P��
     */
    public WEB3OptionsExecuteGroup[] opExecuteGroups;

    /**
     * �i�\���y�[�W�ԍ�)
     * <BR>
     * ���ۂɕ\������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;

    /**
     * ���y�[�W��
     */
    public String totalPages;

    /**
     * �����R�[�h��
     */
    public String totalRecords;

    /**
     * (ID�ꗗ)<BR>
     * <BR>
     * ���������ɊY������S�����h�c<BR>
     * �i�\�[�g���ꂽ��ԁj<BR>
     * <BR>
     * �o�b�ł̏ꍇ�ݒ�<BR>
     */
    public String[] idList;
    /**
     * �������ꗗ<BR>
     * ���������\���p<BR>
     */
    public Date[] orderBizDateList;

    /**
     * (����I���x������)<BR>
     * <BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[<BR>
     */
    public String[] messageSuspension;

    /**
     * (�����w���敨�I�v�V���������R�[�h����)<BR>
     * <BR>
     * ���������\���p<BR>
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;

    /**
     * (�ڋq���b�N�敪)<BR>
     * <BR>
     * true�F���b�N�ڋq�ł���@@false�F���b�N�ڋq�łȂ�<BR>
     */
    public boolean accountLock;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsExecuteReferenceResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsExecuteReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
