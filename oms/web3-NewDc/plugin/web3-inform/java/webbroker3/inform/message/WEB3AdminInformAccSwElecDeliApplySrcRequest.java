head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliApplySrcRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g(WEB3AdminInformAccSwElecDeliApplySrcRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��097
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g)<BR>
 * �Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g�N���X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliApplySrcRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_inform_acc_sw_elec_deli_apply_src";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707182050L;

    public WEB3AdminInformAccSwElecDeliApplySrcRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformAccSwElecDeliApplySrcResponse(this);
    }
}
@
