head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.56.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointUploadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�b�v���[�h���̓��X�|���X(WEB3AdminPointUploadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�A�b�v���[�h���̓��X�|���X)<BR>
 * �A�b�v���[�h���̓��X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointUploadInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_uploadInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290011L;
    
    /**
     * (�A�b�v���[�h������ԋ敪)<BR>
     * �O��A�b�v���[�h������ԋ敪<BR>
     * <BR>
     * 0�F�@@�A�b�v���[�h�҂�<BR>
     * 1�F�@@�A�b�v���[�h��<BR>
     * 2�F�@@�A�b�v���[�h��<BR>
     */
    public String uploadState;
    
    /**
     * (�A�b�v���[�h��������)<BR>
     * �O��A�b�v���[�h��������<BR>
     */
    public String uploadCount;
    
    /**
     * (�A�b�v���[�h�J�n����)<BR>
     * �O��A�b�v���[�h�J�n����<BR>
     */
    public Date uploadStartDate;
    
    /**
     * (�A�b�v���[�h�I������)<BR>
     * �O��A�b�v���[�h�I������<BR>
     */
    public Date uploadEndDate;
    
    /**
     * (�A�b�v���[�h�G���[�ԍ�)<BR>
     * �O��A�b�v���[�h�G���[�ԍ�<BR>
     * <BR>
     * �� ErrorMessage�e�[�u����PK<BR>
     */
    public String uploadErrorNo;
    
    /**
     * @@roseuid 41D1254F01E4
     */
    public WEB3AdminPointUploadInputResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminPointUploadInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
