head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���g�єԍ��E�Ζ�����ύX�\���m�F���N�G�X�g(WEB3AccinfoMobileOfficeRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
                   2006/10/30 ����� (���u) �d�l�ύX���f��No.139
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (���q�l���g�єԍ��E�Ζ�����ύX�\���m�F���N�G�X�g)<BR>
 * ���q�l���g�єԍ��E�Ζ�����ύX�\���m�F���N�G�X�g<BR>
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeRegistConfirmRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mobileOfficeRegistConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082154L;
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeRegistConfirmRequest.class);


    /**
     * (�ύX����)<BR>
     * �ύX����<BR>
     */
    public WEB3AccInfoMobileOfficeInfo changedMobileOfficeInfo;
    
    /**
     * (�ύX�O���)<BR>
     * �ύX�O���<BR>
     */
    public WEB3AccInfoMobileOfficeInfo preMobileOfficeInfo;

    /**
     * @@roseuid 418F39F6009C
     */
    public WEB3AccInfoMobileOfficeRegistConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoMobileOfficeRegistConfirmResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * this.�ύX����.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4140279500F6
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        if(this.changedMobileOfficeInfo != null)
        {
            
            //this.�ύX����.validate()���R�[������
            this.changedMobileOfficeInfo.validate();
        }
                
        log.exiting(STR_METHOD_NAME);

    }
}
@
