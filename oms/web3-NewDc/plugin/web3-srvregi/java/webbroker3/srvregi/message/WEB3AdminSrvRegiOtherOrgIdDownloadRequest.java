head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
 Author Name         : Daiwa Institute of Research
 File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰��ظ���(WEB3AdminSrvRegiOtherOrgIdDownloadRequest.java)
 Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338
 */

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰��ظ���)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰��ظ��ăN���X<BR>
 * <BR>
 *
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadRequest extends WEB3AdminSrvRegiOtherOrgIdCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_download";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101401L;

    /**
     * Logger<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdDownloadRequest.class);

    /**
     * (�T�[�r�X���p�\�[�g�L�[)<BR>
     * �Ώۍ���<BR>
     * �ʔ�<BR>
     */
    public WEB3SrvRegiSortKey[] sortKeys;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰��ظ���)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     *
     * @@roseuid 47B8D6BD00BC
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j �X�[�p�[�N���X��validate���\�b�h���Ăяo���B<BR>
     * <BR>
     * �Q�j �T�[�r�X���p�\�[�g�L�[�̃`�F�b�N<BR>
     * �Q�|�P�j this.�T�[�r�X���p�\�[�g�L�[==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00231<BR>
     * �Q�|�Q�j this.�T�[�r�X���p�\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00232<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B3E8680235
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();

        // �T�[�r�X���p�\�[�g�L�[�̃`�F�b�N
        // this.�T�[�r�X���p�\�[�g�L�[==null�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName()+ "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        // this.�T�[�r�X���p�\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName()+ "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
    }

    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�ރ��X�|���X�𐶐����ĕԋp����B<BR>
     * <BR>
     *
     * @@return WEB3GenResponse
     * @@roseuid 47B8D6C90037
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSrvRegiOtherOrgIdDownloadResponse(this);
    }
}
@
