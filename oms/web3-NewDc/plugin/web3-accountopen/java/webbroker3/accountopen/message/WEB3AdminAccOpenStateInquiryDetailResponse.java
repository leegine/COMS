head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��X�|���X(WEB3AdminAccOpenStateInquiryDetailResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �s�p (���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��X�|���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */

public class WEB3AdminAccOpenStateInquiryDetailResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_stateInquiryDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081607L;

    /**
     * (�ύX�s���ڈꗗ)<BR>
     * �ύX�s���ڈꗗ<BR>
     * <BR>
     * �� �`�[�쐬�ςŁA�ύX���s�\�ȏ�Ԃ̍��ڃV���{������z��ɂăZ�b�g����B<BR>
     * <BR>
     * �� �w�肳��鍀�ڂ́A�����J�ݐ\�������̃v���p�e�B�Ɍ��肷��B<BR>
     * �@@�@@�����J�ݐ\����񂪏W�񂵂Ă���Unit�I�u�W�F�N�g���̌ʂ�<BR>
     * �v���p�e�B���w�肳��邱�Ƃ͂Ȃ��B<BR>
     */
    public String[] changeUnableItemList;

    /**
     * (�X�V�\�t���O)<BR>
     * �X�V�\�t���O<BR>
     * <BR>
     * true�F�@@�X�V�\<BR>
     * false�F�@@�X�V�s��<BR>
     */
    public boolean changeFlag;

    /**
     * (�`�[�쐬�\�t���O)<BR>
     * �`�[�쐬�\�t���O<BR>
     * <BR>
     * true�F�@@�`�[�쐬�\<BR>
     * false�F�@@�`�[�쐬�s��<BR>
     */
    public boolean voucherFlag;

    /**
     * (���l�P)<BR>
     * ���l�P<BR>
     * <BR>
     * ���i�s�����ځj���l��<BR>
     */
    public String bikou1;

    /**
     * (���l�Q)<BR>
     * ���l�Q<BR>
     * <BR>
     * ���i�s�����ځj���l��<BR>
     */
    public String bikou2;

    /**
     * (�����J�ݐ\�����)<BR>
     * �����J�ݐ\�����<BR>
     */
    public WEB3AccOpenApplyInfo accoutOpenApplyInfo;

    /**
     * (�s�����ڏ��ꗗ)<BR>
     * �s�����ڏ��ꗗ<BR>
     */
    public WEB3AccOpenInvalidItemInfo[] invalidItemInfo;

    /**
     * (�`�[�쐬���)<BR>
     * �`�[�쐬���
     */
    public WEB3AccOpenVoucherInfo voucherInfo;

    /**
     * @@roseuid 41B45E76031C
     */
    public WEB3AdminAccOpenStateInquiryDetailResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenStateInquiryDetailResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
