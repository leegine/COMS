head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherChgCmpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�����`�[�ύX�������X�|���X(WEB3AdminInformProfDistVoucherChgCmpResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 �Ӑ�(���u) �V�K�쐬 ���f��No.054
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�����`�[�ύX�������X�|���X)<BR>
 * �Ǘ��ҁE�����`�[�ύX�������X�|���X�N���X
 */
public class WEB3AdminInformProfDistVoucherChgCmpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_voucher_chg_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200706042212L;

    /**
     * @@roseuid 4663A9D70290
     */
    public WEB3AdminInformProfDistVoucherChgCmpResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminInformProfDistVoucherChgCmpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
