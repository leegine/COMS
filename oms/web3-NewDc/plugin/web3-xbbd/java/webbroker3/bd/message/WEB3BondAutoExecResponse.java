head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������背�X�|���X(WEB3BondAutoExecResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 ����� (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (��������背�X�|���X)<BR>
 * ��������背�X�|���X�N���X<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3BondAutoExecResponse extends WEB3BackResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_auto_execution";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610051109L;

    /**
     * (��������背�X�|���X ())<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3BondAutoExecResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondAutoExecResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
