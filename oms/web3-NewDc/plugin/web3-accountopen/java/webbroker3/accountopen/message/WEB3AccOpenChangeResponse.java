head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.01.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenChangeResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐؑփ��X�|���X�iWEB3AccOpenChangeResponse.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/13 ���g (���u) �V�K�쐬 ���f��No.164
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��Ҍ����J�ݐؑփ��X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݐؑփ��X�|���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AccOpenChangeResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "acc_open_change";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908131114L;

    /**
     * (�Ǘ��Ҍ����J�ݐؑփ��X�|���X)<BR>
     * �Ǘ��Ҍ����J�ݐؑփ��X�|���X<BR>
     */
    public WEB3AccOpenChangeResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AccOpenChangeResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
