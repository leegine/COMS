head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���菈�������N�����ʃ��N�G�X�g(WEB3AdminDirSecAPMngForcedStartCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/21 �k�v�u(���u) �V�K�쐬 ���f�� 132
Revision History : 2008/07/24 ����(���u) ���f�� 135
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE���菈�������N�����ʃ��N�G�X�g)<BR>
 * �Ǘ��ҁE���菈�������N�����ʃ��N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartCommonRequest extends WEB3GenRequest 
{

    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecAPMngForcedStartCommonRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200807211705L;

    /**
     * PTYPE�B<BR>
     */
    public String pType;

    /**
     * @@roseuid 488437FE0121
     */
    public WEB3AdminDirSecAPMngForcedStartCommonRequest() 
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�jPTYPE�`�F�b�N<BR>
     * �@@�@@this.PTYPE == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03105<BR>
     * @@throws WEB3BaseException
     * @@roseuid 487D58320078
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�jPTYPE�`�F�b�N
        //this.PTYPE == null�̏ꍇ�A��O���X���[����B
        if (this.pType == null)
        {
            log.debug("PTYPE�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03105,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "PTYPE�����w��ł��B");
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
         return null;
     }
}
@
