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
filename	WEB3BackResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���菈���p���X�|���X�̊��N���X(WEB3BackResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �����@@���F(SRA) �V�K�쐬
*/
package webbroker3.common.message;

import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * ���菈���p���X�|���X�̊��N���X�B<BR>
 *<BR>
 * @@author �����@@���F(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.message.Response
 */
public abstract class WEB3BackResponse extends Response
{
    /**
     * �G���[���B
     */
    public ErrorInfo errorInfo = null;

    /**
     * �R���X�g���N�^�B
     */
    public WEB3BackResponse()
    {
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3BackResponse(WEB3BackRequest l_request)
    {
    }
}
@