head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSInputExecCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁE�����iPTS�j�o�����͊������X�|���X�iWEB3AdminEquityPTSInputExecCompleteResponse.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/22 ���� (���u) �V�K�쐬���f��173
 */

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�Ǘ��ҁE�����iPTS�j�o�����͊������X�|���X)<BR>
 * �Ǘ��ҁE�����iPTS�j�o�����͊������X�|���X�N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputExecCompleteResponse extends WEB3AdminEquityPTSInputCancelExecCommonResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_input_exec_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801231019L;

    /**
     * (�Ǘ��ҁE�����iPTS�j�o�����͊������X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 4795A0F803AD
     */
    public WEB3AdminEquityPTSInputExecCompleteResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminEquityPTSInputExecCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
