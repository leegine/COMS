head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSInputCancelExecCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁE����(PTS)�o�����͎�����ʃ��X�|���X�iWEB3AdminEquityPTSInputCancelExecCommonResponse.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/22 ���� (���u) �V�K�쐬���f��172
 */
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE����(PTS)�o�����͎�����ʃ��X�|���X)<BR>
 * �Ǘ��ҁE����(PTS)�o�����͎�����ʃ��X�|���X�N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputCancelExecCommonResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_input_cancel_exec_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801221700L;

    /**
     * (�����ڍ�)<BR>
     */
    public WEB3AdminEquityPTSOrderDetailUnit orderDetail;

    /**
     * (��藚��)<BR>
     */
    public WEB3AdminEquityPTSExecHistory[] execHistories = null;

    /**
     *
     * (�Ǘ��ҁE����(PTS)�o�����͎�����ʃ��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 4795A0F80236
     */
    public WEB3AdminEquityPTSInputCancelExecCommonResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminEquityPTSInputCancelExecCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
