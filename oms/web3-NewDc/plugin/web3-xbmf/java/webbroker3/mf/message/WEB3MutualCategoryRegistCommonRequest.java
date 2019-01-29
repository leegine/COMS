head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCategoryRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��҃J�e�S���[�o�^���ʃ��N�G�X�g(WEB3MutualCategoryRegistCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 ���� (���u) �V�K�쐬 
Revesion History : 2008/04/29 ���g(���u)�@@�d�l�ύX���f��596
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3ProcessAddChangeDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���M�Ǘ��҃J�e�S���[�o�^���ʃ��N�G�X�g)<BR>
 * �����M���Ǘ��҃J�e�S���[�o�^���ʃ��N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualCategoryRegistCommonRequest extends WEB3GenRequest 
{
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412011610L;
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCategoryRegistCommonRequest.class);
    
    /**
     * (���M�����J�e�S���[�R�[�h)<BR>
     * ���M�����J�e�S���[���̂ɑΉ��������M�����J�e�S���[�R�[�h
     */
    public String categoryCode;
    
    /**
     * (���M�����J�e�S���[����)<BR>
     * ���M�����J�e�S���[�R�[�h�ɑΉ��������M�����J�e�S���[����
     */
    public String categoryName;
    
    /**
     * (�e�J�e�S���[�R�[�h)<BR>
     *  �e�J�e�S���[�R�[�h<BR>
     *  �w�肵�Ȃ��ꍇ�c �܂胋�[�g�ɂ���ꍇ�� null �𑗂�
     */
    public String parentCategoryCode;
    
    /**
     * (�����敪)<BR>
     * �����敪�i�ǉ��^�ύX�^�폜�j<BR>
     * 0:�ǉ��@@1:�ύX�@@2:�폜<BR>
     */
    public String procedureDiv;
     
    /**
     * (validate)<BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���M�����J�e�S���[�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�jthis.���M�����J�e�S���[�R�[�h��null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:BUSINESS_ERROR_01243<BR>
     * �@@�P�|�Q�jthis.���M�����J�e�S���[�R�[�h�̒l��2Byte�̏ꍇ�A<BR>
     *           ��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:BUSINESS_ERROR_01244<BR>
     * <BR>
     * �Q�j���M�����J�e�S���[���̂̃`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���M�����J�e�S���[���́�null�̏ꍇ�A��O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:BUSINESS_ERROR_01245<BR>
     * �@@�Q�|�Q�jthis.���M�����J�e�S���[���̂̒l�ɁA<BR>
     *           ���p�J�i���������݂����ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tagBUSINESS_ERROR_01246<BR>
     * �@@�Q�|�R�jthis.���M�����J�e�S���[���̂̒l��100Byte�̏ꍇ�A<BR>
     *           ��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tagBUSINESS_ERROR_01247<BR>
     * <BR>
     * �R�j�e�J�e�S���[�R�[�h�̃`�F�b�N<BR>
     * �@@�R�|�P�jthis.�e�J�e�S���[�R�[�h!=null�ł���A<BR>
     *           ���l��2Byte�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tagBUSINESS_ERROR_01248<BR>
     * �@@�R�|�Q�jthis.�e�J�e�S���[�R�[�h!=null�ł���A����<BR>
     * �@@�@@�@@�@@  this.�e�J�e�S���[�R�[�h��this.���M�����J�e�S���[�R�[�h�̏ꍇ�A<BR>
     *           ��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tagBUSINESS_ERROR_01281<BR>
     * <BR>
     * �S�j�����敪�̃`�F�b�N<BR>
     * �@@�S�|�P�jthis.�����敪��null�̏ꍇ�A��O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tagBUSINESS_ERROR_01249<BR>
     * �@@�S�|�Q�jthis.�����敪�̒l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A<BR>
     *           ��O���X���[����B<BR>
     * �@@�@@�@@    �h�ǉ��h<BR>
     * �@@�@@�@@    �h�ύX�h<BR>
     * �@@�@@�@@    �h�폜�h<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tagBUSINESS_ERROR_01250
     * @@throws WEB3BaseException
     * @@roseuid 4153B64902D6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���M�����J�e�S���[�R�[�h�̃`�F�b�N 
        //  �P�|�P�jthis.���M�����J�e�S���[�R�[�h��null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.categoryCode))
        {
            log.debug("���M�����J�e�S���[�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01243,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���M�����J�e�S���[�R�[�h�����w��ł��B");
        }
        
        //�P�|�Q�jthis.���M�����J�e�S���[�R�[�h�̒l��2Byte�̏ꍇ�A��O���X���[����B
        if (this.categoryCode.getBytes().length > 2)
        {
            log.debug("���M�����J�e�S���[�R�[�h�̒l������l�𒴂��Ă��܂��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01244,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���M�����J�e�S���[�R�[�h�̒l������l�𒴂��Ă��܂��B");
        }
        
        //�Q�j���M�����J�e�S���[���̂̃`�F�b�N 
        //  �Q�|�P�jthis.���M�����J�e�S���[���́�null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.categoryName))
        {
            log.debug("���M�����J�e�S���[���̂����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01245,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���M�����J�e�S���[���̂����w��ł��B");
        }
        
        //�Q�|�Q�jthis.���M�����J�e�S���[���̂̒l�ɁA
        //  ���p�J�i���������݂����ꍇ�A��O���X���[����B   
        if (WEB3StringTypeUtility.has1byteKana(this.categoryName))
        {
            log.debug("���M�����J�e�S���[���̒l�ɔ��p�J�i�������܂܂�Ă���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01246,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���M�����J�e�S���[���̒l�ɔ��p�J�i�������܂܂�Ă���B");
        }
        
        //�Q�|�R�jthis.���M�����J�e�S���[���̂̒l��100Byte�̏ꍇ�A��O���X���[����B 
        if (this.categoryName.getBytes().length > 100)
        {
            log.debug("���M�����J�e�S���[���̂̒l������l�𒴂��Ă��܂��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01247,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���M�����J�e�S���[���̂̒l������l�𒴂��Ă��܂��B");
        }
        
        //�R�j�e�J�e�S���[�R�[�h�̃`�F�b�N 
        //  �R�|�P�jthis.�e�J�e�S���[�R�[�h!=null�ł���A
        //      ���l��2Byte�̏ꍇ�A��O���X���[����B
        if (this.parentCategoryCode != null
            && this.parentCategoryCode.getBytes().length > 2)
        {
            log.debug("�e�J�e�S���[�R�[�h�̒l������l�𒴂��Ă��܂��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01248,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�J�e�S���[�R�[�h�̒l������l�𒴂��Ă��܂��B");
        }
        
        //�R�|�Q�jthis.�e�J�e�S���[�R�[�h!=null�ł���A���� 
        //  this.�e�J�e�S���[�R�[�h��this.���M�����J�e�S���[�R�[�h�̏ꍇ�A��O���X���[����B 
        if (this.parentCategoryCode != null
            && this.categoryCode.equals(this.parentCategoryCode))
        {
            log.debug("�e�J�e�S���[�R�[�h�Ɠ��M�����J�e�S���[�R�[�h�͓����ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01281,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�J�e�S���[�R�[�h�Ɠ��M�����J�e�S���[�R�[�h�͓����ł��B");
        }
        
        //�S�j�����敪�̃`�F�b�N 
        //  �S�|�P�jthis.�����敪��null�̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.procedureDiv))
        {
            log.debug("�����敪�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�����w��ł��B");
        }
        
        //�S�|�Q�jthis.�����敪�̒l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //  �h�ǉ��h 
        //  �h�ύX�h
        //  �h�폜�h
        if (!(WEB3ProcessAddChangeDivDef.ADD.equals(this.procedureDiv)
            || WEB3ProcessAddChangeDivDef.CHANGE.equals(this.procedureDiv)
            || WEB3ProcessAddChangeDivDef.DELETE.equals(this.procedureDiv)))
        {
            log.debug("�����敪�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF81440100
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
