head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3TraderAccountInfosSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CC�I�y���[�^�Ώیڋq�ꗗ�\�[�g�L�[�N���X(WEB3TraderAccountInfosSortKey.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 ���n�m (���u) �V�K�쐬�E���f��No.039
*/

package webbroker3.login.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (CC�I�y���[�^�Ώیڋq�ꗗ�\�[�g�L�[)<BR>
 * CC�I�y���[�^�Ώیڋq�ꗗ�\�[�g�L�[�N���X<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3TraderAccountInfosSortKey extends Message
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3TraderAccountInfosSortKey.class);

    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{����<BR>
     * ���L�[���ڂƂ���B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     */
    public String keyItem;

    /**
     * (����/�~��)<BR>
     * A�F����<BR>
     * D�F�~��<BR>
     */
    public String ascDesc;

    /**
     * (CC�I�y���[�^�Ώیڋq�ꗗ�\�[�g�L�[)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3TraderAccountInfosSortKey()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�L�[���ڃ`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�L�[���� == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�j�@@�����^�~���`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�����^�~�� == null�̏ꍇ�A ��O���X���[����B<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00087<BR>
     * �@@�Q�|�Q�j�@@this.�����^�~�����ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�E"�~��"<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�L�[���ڃ`�F�b�N
        //�P�|�P�j�@@this.�L�[���� == null�̏ꍇ�A��O���X���[����
        if (this.keyItem == null)
        {
            log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
        }

        //�Q�j�@@�����^�~���`�F�b�N
        //�Q�|�P�j�@@this.�����^�~�� == null�̏ꍇ�A ��O���X���[����B
        if (this.ascDesc == null)
        {
            log.debug("�����^�~�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����^�~�������w��ł��B");
        }

        //�Q�|�Q�j�@@this.�����^�~�����ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        //�E"����"
        //�E"�~��"
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.debug("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
