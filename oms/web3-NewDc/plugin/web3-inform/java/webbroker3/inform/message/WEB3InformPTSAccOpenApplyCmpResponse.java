head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyCmpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS�����J�ݐ\���������X�|���X(WEB3InformPTSAccOpenApplyCmpResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 �Ӑ� (���u) �V�K�쐬 ���f��No.123
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (PTS�����J�ݐ\���������X�|���X)<BR>
 * PTS�����J�ݐ\���������X�|���X
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyCmpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final  String PTYPE = "inform_pts_acc_open_apply_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802181638L;

    /**
     * @@roseuid 47B9271A0147
     */
    public WEB3InformPTSAccOpenApplyCmpResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     */
    protected WEB3InformPTSAccOpenApplyCmpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
