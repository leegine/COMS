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
filename	WEB3AdminForcedSettleDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ����_�E�����[�h���X�|���X�iWEB3AdminForcedSettleDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 ���n(���u) �V�K�쐬���f��175
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (�Ǘ��ҁE�������ϒ����_�E�����[�h���X�|���X)<BR>
 * �Ǘ��ҁE�������ϒ����_�E�����[�h���X�|���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3AdminForcedSettleDownloadResponse extends WEB3AdminForcedSettleReferenceResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_download";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801211115L;

    /**
     * (�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C��<BR>
     * <BR>
     * �� CSV�t�@@�C���s�̔z��<BR>
     */
    public String[] downloadFile;

    /**
     * (���ݓ���)<BR>
     * ���ݓ���<BR>
     */
    public Date currentDate;

    /**
     * @@roseuid 47903208036B
     */
    public WEB3AdminForcedSettleDownloadResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminForcedSettleDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
