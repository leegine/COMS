head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍψꗗ��ʕ\�����X�|���X(WEB3FuturesCloseMarginListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����w���敨�ԍψꗗ��ʕ\�����X�|���X)<BR>
 * �����w���敨�ԍψꗗ��ʕ\�����X�|���X�N���X
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginListResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191511L;

    /**
     * (�����w���敨�ԍψꗗ�s)<BR>
     */
    public WEB3FuturesCloseMarginGroup[] closeMarginGroups;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * ���ۂɕ\������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���
     */
    public String pageIndex;

    /**
     * (���y�[�W��)<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     */
    public String totalRecords;

    /**
     * (����I���x������)<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[
     */
    public String[] messageSuspension;

    /**
     * (�����w���敨�I�v�V���������R�[�h����)<BR>
     * �����w���敨�I�v�V���������R�[�h����<BR>
     * (���������\���Ɏg�p)
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;

    /**
     * @@roseuid 40F7AE16003E
     */
    public WEB3FuturesCloseMarginListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesCloseMarginListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
