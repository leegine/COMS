head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A����񌟍��ꗗ���N�G�X�g�N���X(WEB3AdminInformListRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 ������(���u) �쐬
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�A����񌟍��ꗗ���N�G�X�g)<BR>
 * �A����񌟍��ꗗ���N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminInformListRequest extends WEB3AdminInformCommonRequest 
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_informList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501251303L;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��
     */
    public String pageSize;
    
    /**
     * @@roseuid 41EE625B037A
     */
    public WEB3AdminInformListRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j�v���y�[�W�ԍ� <BR>
     * <BR>
     *    this.�v���y�[�W�ԍ� == null or <BR>
     * �@@class: WEB3BusinessLayerException<BR> 
     * �@@tag: BUSINESS_ERROR_00089<BR> 
     *    this.�v���y�[�W�ԍ� != ���p���� <BR>
     * �@@class: WEB3BusinessLayerException<BR> 
     * �@@tag: BUSINESS_ERROR_00090
     *    this.�v���y�[�W�ԍ� <= 0 or <BR>
     * �@@class: WEB3BusinessLayerException<BR> 
     * �@@tag: BUSINESS_ERROR_00616<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �R�j�y�[�W���\���s�� <BR>
     * <BR>
     *    this.�y�[�W���\���s�� == null or <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR> 
     * �@@�@@tag: BUSINESS_ERROR_00091<BR> 
     *    this.�y�[�W���\���s�� != ���p���� <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR> 
     * �@@�@@tag: BUSINESS_ERROR_00092<BR> 
     *    this.�y�[�W���\���s�� <= 0 or <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR> 
     * �@@�@@tag: BUSINESS_ERROR_00617<BR> 
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * @@roseuid 41B93FB7002A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B<BR>
        super.validate();

        //�Q�j�v���y�[�W�ԍ� <BR>
        //  this.�v���y�[�W�ԍ� == null�̏ꍇ�A��O���X���[����B<BR>
        // �@@   class: WEB3BusinessLayerException<BR> 
        //   �@@ tag: BUSINESS_ERROR_00089<BR> 
        if (this.pageIndex == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089, 
                getClass().getName() + "validate",
                "�v���y�[�W�ԍ������w��ł��B");
        }
        
        //  this.�v���y�[�W�ԍ� != ���p�����̏ꍇ�A��O���X���[����B<BR>
        //    �@@class: WEB3BusinessLayerException<BR> 
        //    �@@tag: BUSINESS_ERROR_00090
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                getClass().getName() + "validate",
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        //  this.�v���y�[�W�ԍ� <= 0�̏ꍇ�A��O���X���[����B<BR>
        // �@@   class: WEB3BusinessLayerException<BR> 
        //   �@@ tag: BUSINESS_ERROR_00616
        if (Double.parseDouble(this.pageIndex) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                getClass().getName() + "validate",
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        //�R�j�y�[�W���\���s�� <BR>
        //  this.�y�[�W���\���s�� == null�̏ꍇ�A��O���X���[����B<BR>
        //    �@@class: WEB3BusinessLayerException<BR> 
        // �@@ �@@tag: BUSINESS_ERROR_00091<BR> 
        if (this.pageSize == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                getClass().getName() + "validate",
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }

        //  this.�y�[�W���\���s�� != ���p�����̏ꍇ�A��O���X���[����B<BR>
        //  �@@�@@class: WEB3BusinessLayerException<BR> 
        //  �@@�@@tag: BUSINESS_ERROR_00092<BR> 
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                getClass().getName() + "validate",
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        //  this.�y�[�W���\���s�� <= 0�̏ꍇ�A��O���X���[����B<BR>
        //  �@@�@@class: WEB3BusinessLayerException<BR> 
        //  �@@�@@tag: BUSINESS_ERROR_00617<BR> 
        if (Double.parseDouble(this.pageSize) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                getClass().getName() + "validate",
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
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
        return new WEB3AdminInformListResponse(this);
    }
}
@
