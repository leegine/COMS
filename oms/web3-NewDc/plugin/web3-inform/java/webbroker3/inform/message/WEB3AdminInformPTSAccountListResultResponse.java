head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListResultResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁEPTS�\���q�ꗗ�⍇���������ʃ��X�|���X�N���X(WEB3AdminInformPTSAccountListResultResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 �Ӑ�(���u) �V�K�쐬 ���f��No.128
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁEPTS�\���q�ꗗ�⍇���������ʃ��X�|���X�N���X)<BR>
 * �Ǘ��ҁEPTS�\���q�ꗗ�⍇���������ʃ��X�|���X�N���X<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListResultResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_pts_account_list_result";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802281638L;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�
     */
    public String pageIndex;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��
     */
    public String totalRecords;

    /**
     * (PTS�\���q���ꗗ)<BR>
     * PTS�\���q���ꗗ
     */
    public WEB3AdminInformPTSAccountInfoUnit[] ptsAccountInfoList;

    /**
     * @@roseuid 47C522D40377
     */
    public WEB3AdminInformPTSAccountListResultResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminInformPTSAccountListResultResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
