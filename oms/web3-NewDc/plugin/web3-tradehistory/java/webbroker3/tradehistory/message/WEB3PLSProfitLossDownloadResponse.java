head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���v���׃t�@@�C���_�E�����[�h���X�|���X(WEB3PLSProfitLossDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/19 ��іQ (���u) �V�K�쐬
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���v���׃t�@@�C���_�E�����[�h���X�|���X)<BR>
 * ���v���׃t�@@�C���_�E�����[�h���X�|���X
 *
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3PLSProfitLossDownloadResponse extends WEB3GenResponse
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "pLS_profit_loss_download";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610191300L;

    /**
     * (�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C��<BR>
     * ��CSV�t�@@�C���s�̔z��<BR>
     */
    public String[] downloadFile;

    /**
     * (���ݓ���)<BR>
     * ���ݓ���<BR>
     */
    public Date currentDate;

    /**
     * @@roseuid 44E33635003E
     */
    public WEB3PLSProfitLossDownloadResponse()
    {

    }

    /**
     *�@@�R���X�g���N�^�B<BR>
     *�@@�w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@@@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3PLSProfitLossDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
