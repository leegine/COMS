head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BackRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���菈���p���N�G�X�g�̊��N���X(WEB3BackRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �����@@���F(SRA) �V�K�쐬
*/
package webbroker3.common.message;

import com.fitechlabs.xtrade.kernel.message.Request;

/**
 * ���菈���p���N�G�X�g�̊��N���X�B<BR>
 *<BR>
 * @@author �����@@���F(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.message.Request
 */
public abstract class WEB3BackRequest extends Request
{
    /**
     * �R���X�g���N�^�B
     */
    public WEB3BackRequest()
    {
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public abstract WEB3BackResponse createResponse();
}
@
