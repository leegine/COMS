head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeSrcResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁEPTS�����J�ݏ󋵕ύX�������X�|���X(WEB3AdminInformPTSAccOpenStateChangeSrcResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 �đo�g(���u) �V�K�쐬 ���f��No.128
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ��ҁEPTS�����J�ݏ󋵕ύX�������X�|���X<BR>
 * �Ǘ��ҁEPTS�����J�ݏ󋵕ύX�������X�|���X�N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccOpenStateChangeSrcResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_inform_pts_acc_open_state_change_src";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200802281243L;

    /**
     * @@roseuid 47C522D40200
     */
    public WEB3AdminInformPTSAccOpenStateChangeSrcResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     */
    protected WEB3AdminInformPTSAccOpenStateChangeSrcResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
