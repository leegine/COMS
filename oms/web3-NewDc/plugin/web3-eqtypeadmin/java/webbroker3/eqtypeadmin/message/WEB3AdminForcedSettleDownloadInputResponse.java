head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleDownloadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ����_�E�����[�h���̓��X�|���X�iWEB3AdminForcedSettleDownloadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 ���n(���u) �V�K�쐬���f��175
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (�Ǘ��ҁE�������ϒ����_�E�����[�h���̓��X�|���X)<BR>
 * �Ǘ��ҁE�������ϒ����_�E�����[�h���̓��X�|���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3AdminForcedSettleDownloadInputResponse extends WEB3AdminForcedSettleReferenceResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_download_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801211105L;

    /**
     * @@roseuid 479031F9030D
     */
    public WEB3AdminForcedSettleDownloadInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminForcedSettleDownloadInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
