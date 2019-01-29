head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCalendarRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������J�����_�[�o�^���ʃ��N�G�X�g(WEB3AdminFeqCalendarRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҊO�������J�����_�[�o�^���ʃ��N�G�X�g)<BR>
 * �Ǘ��ҊO�������J�����_�[�o�^���ʃ��N�G�X�g�N���X
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqCalendarRegistCommonRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqCalendarRegistCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_calendarRegistCommon";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;
    
    /**
     * (�J�����_�[���ꗗ)<BR>
     * ���n�J�����_�[���̔z��
     */
    public WEB3AdminFeqLocalCalendarUnit[] feqLocalCalendarUnit;
    
    /**
     * @@roseuid 42CE3A0001C5
     */
    public WEB3AdminFeqCalendarRegistCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�s��R�[�h<BR>
     * <BR>
     *    this.�s��R�[�h == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00443<BR>
     * @@throws WEB3BaseException
     * @@roseuid 421C776F0044
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�s��R�[�h
        //   this.�s��R�[�h == null
        //   �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            log.debug("�s��R�[�h�������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                getClass().getName() + STR_METHOD_NAME,
                "�s��R�[�h�������͂ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
