head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartCmpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁE���菈�������N���������N�G�X�g(WEB3AdminDirSecAPMngForcedStartCmpRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/21 �k�v�u(���u) �V�K�쐬 ���f�� 132
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE���菈�������N���������N�G�X�g)<BR>
 * �Ǘ��ҁE���菈�������N���������N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartCmpRequest extends WEB3AdminDirSecAPMngForcedStartCommonRequest
{

    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecAPMngForcedStartCmpRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200807211659L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_apmng_forced_start_cmp";

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ��B<BR>
     */
    public String password;

    /**
     * @@roseuid 488437FD03C1
     */
    public WEB3AdminDirSecAPMngForcedStartCmpRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���R�[������B<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 487D5AA40084
     */
    public void validate() throws WEB3BaseException
    {
        //�P�j�X�[�p�[�N���X��validate()���R�[������B
        super.validate();
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecAPMngForcedStartCmpResponse(this);
    }
}
@