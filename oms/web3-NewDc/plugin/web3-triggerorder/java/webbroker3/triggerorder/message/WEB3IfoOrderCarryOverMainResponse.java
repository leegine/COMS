head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����J�z���C�����X�|���X(WEB3IfoOrderCarryOverMainResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/6/21 ���^�] (���u) �V�K�쐬 ���f�� 669
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�敨OP�����J�z���C�����X�|���X)<BR>
 * �敨OP�����J�z���C�����X�|���X�N���X<BR>
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3IfoOrderCarryOverMainResponse extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "ifo_order_carryover_main";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200706211550L;

    public WEB3IfoOrderCarryOverMainResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3IfoOrderCarryOverMainResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
