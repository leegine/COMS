head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����E���z���E���p����U���挟�����̓��X�|���X�N���X(WEB3AdminInformProfDistSellTransSrcInpResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 �Ӑ�(���u) �V�K�쐬 ���f��No.045
*/

package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����E���z���E���p����U���挟�����̓��X�|���X�N���X)<BR>
 * �����E���z���E���p����U���挟�����̓��X�|���X�N���X<BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcInpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_sell_trans_src_inp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200705242212L;

    /**
     * (�o�^���i���j)<BR>
     * �o�^���i���j
     */
    public Date registDateFrom;

    /**
     * (�o�^���i���j)<BR>
     * �o�^���i���j
     */
    public Date registDateTo;

    /**
     * @@roseuid 4655937502AE
     */
    public WEB3AdminInformProfDistSellTransSrcInpResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminInformProfDistSellTransSrcInpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
