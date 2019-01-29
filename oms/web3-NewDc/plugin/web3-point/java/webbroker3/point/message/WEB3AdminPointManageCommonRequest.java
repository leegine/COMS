head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointManageCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�Ǘ����ʃ��N�G�X�g(WEB3AdminPointManageCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�|�C���g�Ǘ����ʃ��N�G�X�g)<BR>
 * �|�C���g�Ǘ����ʃ��N�G�X�g�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointManageCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointManageCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_manageCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290086L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���͂��ꂽ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * ���͂��ꂽ�ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * @@roseuid 41D1254D006D
     */
    public WEB3AdminPointManageCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���X�R�[�h<BR>
     * <BR>
     *    this.���X�R�[�h.length() != 3 or<BR>
     *    this.���X�R�[�h != ����<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01729<BR>
     * <BR>
     * �Q�j�ڋq�R�[�h<BR>
     * <BR>
     *    this.�ڋq�R�[�h.length() != 6 or<BR>
     *    this.�ڋq�R�[�h != ����<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * @@roseuid 41944A040242
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j���X�R�[�h
        if (this.branchCode == null || "".equals(this.branchCode.trim()) || WEB3StringTypeUtility.getByteLength(this.branchCode) != 3)
        {
            String l_strMessage = "���X�R�[�h = null !";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            String l_strMessage = "���X�R�[�h != ���� " + this.branchCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        
        // �Q�j�ڋq�R�[�h
        if (this.accountCode == null || "".equals(this.accountCode.trim()) || WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            String l_strMessage = "�ڋq�R�[�h = null !";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            String l_strMessage = "�ڋq�R�[�h != ���� " + this.accountCode;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254D008C
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
