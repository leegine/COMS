head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁEPTS�����J�ݏ󋵕ύX���̓��X�|���X(WEB3AdminInformPTSAccOpenStateChangeInpResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 �đo�g(���u) �V�K�쐬 ���f��No.128
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ��ҁEPTS�����J�ݏ󋵕ύX���̓��X�|���X<BR>
 * �Ǘ��ҁEPTS�����J�ݏ󋵕ύX���̓��X�|���X�N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccOpenStateChangeInpResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_inform_pts_acc_open_state_change_inp";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200802281454L;

    /**
     * (�ڋq��)<BR>
     * �ڋq��
     */
    public String accountName;

    /**
     * (�ύX�O�\���敪)<BR>
     * �ύX�O�\���敪<BR>
     * <BR>
     * 0�F���J��<BR>
     * 1�F�J��<BR>
     */
    public String beforePtsAccOpenDiv;

    /**
     * @@roseuid 47C522D4025E
     */
    public WEB3AdminInformPTSAccOpenStateChangeInpResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     */
    protected WEB3AdminInformPTSAccOpenStateChangeInpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
