head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �⍇���Ǘ����⍇�����̓��X�|���X(WEB3FaqInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�⍇���Ǘ����⍇�����̓��X�|���X)<BR>
 * �⍇���Ǘ����⍇�����̓��X�|���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3FaqInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "faq_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171301L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * <BR>
     * �����O�C���ς̏ꍇ�A�Z�b�g����B<BR>
     * �����O�C�����Ă��Ȃ��ꍇ�Anull�B<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * <BR>
     * <BR>
     * �����O�C���ς̏ꍇ�A�Z�b�g����B<BR>
     * �����O�C�����Ă��Ȃ��ꍇ�Anull�B<BR>
     */
    public String accountCode;

    /**
     * (�ڋq��)<BR>
     * �ڋq��<BR>
     * <BR>
     * �����O�C���ς̏ꍇ�A�Z�b�g����B<BR>
     * �����O�C�����Ă��Ȃ��ꍇ�Anull�B<BR>
     */
    public String accountName;

    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     * <BR>
     * �����O�C���ς̏ꍇ�A�Z�b�g����B<BR>
     * �����O�C�����Ă��Ȃ��ꍇ�Anull�B<BR>
     */
    public String mailAddress;

    /**
     * @@roseuid 41C25C060119
     */
    public WEB3FaqInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FaqInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
