head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.49.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS�����J�ݐ\�����̓��X�|���X(WEB3InformPTSAccOpenApplyInpResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 �Ӑ� (���u) �V�K�쐬 ���f��No.123
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (PTS�����J�ݐ\�����̓��X�|���X)<BR>
 * PTS�����J�ݐ\�����̓��X�|���X
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyInpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "inform_pts_acc_open_apply_inp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802181642L;

    /**
     * (�ڋq��)<BR>
     * �ڋq��
     */
    public String accountName;

    /**
     * (PTS������ӎ�����ꗗ)<BR>
     * PTS������ӎ�����ꗗ
     */
    public WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList;

    /**
     * @@roseuid 47B9271A0270
     */
    public WEB3InformPTSAccOpenApplyInpResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     */
    protected WEB3InformPTSAccOpenApplyInpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
