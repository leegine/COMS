head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLAccountOpenApplyListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҏ،��S�ۃ��[���\���ڋq�ꗗ�X�|���X(WEB3AdminSLAccountOpenApplyListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.756
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҏ،��S�ۃ��[���\���ڋq�ꗗ���X�|���X)<BR>
 * �Ǘ��ҏ،��S�ۃ��[���\���ڋq�ꗗ���X�|���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminSLAccountOpenApplyListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_account_open_apply_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709071027L;

    /**
     * (�S�ۃ��[���\���ڋq����)<BR>
     * �S�ۃ��[���\���ڋq����<BR>
     */
    public WEB3SLAccountOpenApplyDetailUnit[] accountOpenApplyDetailList;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;

    /**
     * @@param l_request - �Ǘ��ҏ،��S�ۃ��[���\���ڋq�ꗗ���N�G�X�g
     * @@roseuid 46E0BE47031C
     */
    public WEB3AdminSLAccountOpenApplyListResponse(
        WEB3AdminSLAccountOpenApplyListRequest l_request)
    {
        super(l_request);
    }
}
@
