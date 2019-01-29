head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ����Ɖ���̓��N�G�X�g(WEB3AdminForcedSettleRefInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 ��іQ (���u) �V�K�쐬  �d�l�ύX���f��No.128
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�������ϒ����Ɖ���̓��N�G�X�g)<BR>
 * �Ǘ��ҁE�������ϒ����Ɖ���̓��N�G�X�g�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminForcedSettleRefInputRequest extends WEB3GenRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminForcedSettleRefInputRequest.class);

    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_ref_input";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;

    /**
     * (���X�R�[�h�ꗗ)<BR>
     * ���X�R�[�h�̔z��<BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă���<BR>
     * �@@�戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     */
    public String[] branchCodeList;

    /**
     * @@roseuid 462CA4290053
     */
    public WEB3AdminForcedSettleRefInputRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.���X�R�[�h�ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01429       <BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.���X�R�[�h�ꗗ�̗v�f�����ȉ��̏������s���B<BR>
     * �@@�@@�P�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E���X�R�[�h != ����<BR>
     * �@@�@@�@@�@@�E���X�R�[�h.length != 3<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00779<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46103B7D02E9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intThree = 3;
        int l_intBranchCodeLength = 0;

        // �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        // �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
        // �P�j�@@���X�R�[�h�`�F�b�N
        // �@@�P�|�P�j�@@this.���X�R�[�h�ꗗ == null�̏ꍇ�A
        // �@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B
        if (this.branchCodeList == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�ꗗ�����w��ł��B");
        }

        // �@@�P�|�Q�j�@@this.���X�R�[�h�ꗗ�̗v�f�����ȉ��̏������s���B
        // �@@�@@�P�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
        // �@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
        // �@@�@@�@@�@@�E���X�R�[�h != ����
        // �@@�@@�@@�@@�E���X�R�[�h.length != 3
        l_intBranchCodeLength = this.branchCodeList.length;
        for (int i = 0;  i < l_intBranchCodeLength; i++)
        {
            if ((!WEB3StringTypeUtility.isDigit(branchCodeList[i]))
                || (WEB3StringTypeUtility.getByteLength(this.branchCodeList[i]) != l_intThree))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̓��͂��s���ł��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminForcedSettleRefInputResponse(this);
    }
}
@
