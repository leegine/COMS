head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3OptionsOrderCarryOverResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������J�z���X�|���X(WEB3OptionsOrderCarryOverResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 羉s (���u) �V�K�쐬
Revesion History : 2006/06/21 �Ј��� (���u) �d�l�ύX ���f�� 670
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3BackRequest;


/**
 * (�����w���I�v�V���������J�z���X�|���X)<BR>
 * �����w���I�v�V���������J�z���X�|���X�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsOrderCarryOverResponse extends WEB3IfoOrderCarryOverMainResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_orderCarryOver";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111040L;

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsOrderCarryOverResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }

}
@
