head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqRcvdQueueReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������RCVD�L���[�Ɖ�ꗗ���N�G�X�g(WEB3AdminFeqRcvdQueueReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
                   2006/11/23 ����� (���u) ��Q�Ǘ� K00010
Revesion History : 2008/10/02 ����(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.465 �i�����jNo.027
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum; 
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO������RCVD�L���[�Ɖ�ꗗ���N�G�X�g)<BR>
 * �Ǘ��ҊO������RCVD�L���[�Ɖ�ꗗ���N�G�X�g�N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3AdminFeqRcvdQueueReferenceRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_rcvd_queue_reference";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD00AB
     */
    public WEB3AdminFeqRcvdQueueReferenceRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFeqRcvdQueueReferenceRequest.class);
    
    /**
     * (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     */
    public String updaterCode;    
    
    /**
     * (�����敪)<BR>
     * 0�F�����҂�<BR>
     * 1�F�����ς�<BR>
     * 7�F��菈����<BR>
     * 8�F�����ȗ�<BR>
     * 9�F�����G���[<BR>
     */
    public String transactionDiv;
    
    /**
     * (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     */
    public String managementCode;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;  
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (�o�H�敪)<BR>
     * �o�H�敪<BR>
     * <BR>
     * 0�F�o���ʒm<BR> 
     * 1�F�o������<BR> 
     * 2�F��茋�ʈꊇ����<BR> 
     * 3�F������t<BR>
     * 4�F������t����F��<BR>
     * 5�F������t���ʈꊇ����<BR>
     */
    public String orderRootDiv;
    
    /**
     * (��t�敪)<BR>
     * ��t�敪 <BR>
     * <BR>
     * 01�F������t��<BR> 
     * 09�F������t�G���[<BR> 
     * 03�F������t�ώ��<BR>
     * 11�F������<BR>
     * 19�F�����G���[<BR> 
     * 21�F�����<BR>
     * 29�F����G���[<BR>
     * 31�F�o����<BR>
     */
    public String acceptDiv;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �O�������\�[�g�L�[�I�u�W�F�N�g�̔z��<BR>
     */
    public WEB3ForeignSortKey[] sortKeys;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ� <BR>
     * <BR>
     * �\�����������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR> 
     *�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     *<BR>
     *�P�j�����敪�`�F�b�N<BR> 
     *�@@this.�����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR> 
     *�@@�P�|�P�jthis.�����敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR> 
     *�@@�@@�@@�@@�u�����敪������`�̒l�v�̗�O���X���[����B<BR> 
     *�@@�@@�@@�E0�F�����҂� <BR>
     *�@@�@@�@@�E1�F�����ς� <BR>
     *�@@�@@�@@�E7�F��菈���� <BR>
     *�@@�@@�@@�E8�F�����ȗ� <BR>
     *�@@�@@�@@�E9�F�����G���[ <BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_01250<BR>
     *<BR>
     * this.�o�H�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �P�|�Q�jthis.�o�H�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR> 
     *�@@�@@�@@�u�o�H�敪������`�̒l�v�̗�O���X���[����B <BR>
     *�@@�@@�@@�E0�F�o���ʒm <BR>
     *�@@�@@�@@�E1�F�o������ <BR>
     *�@@�@@�@@�E2�F��茋�ʈꊇ����<BR> 
     *�@@�@@�@@�E3�F������t <BR>
     *�@@�@@�@@�E4�F������t����F��<BR> 
     *�@@�@@�@@�E5�F������t���ʈꊇ����<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_02651<BR>
     *<BR>
     * this.��t�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �P�|�R�jthis.��t�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR> 
     *�@@�@@�@@�u��t�敪������`�̒l�v�̗�O���X���[����B<BR>
     *�@@�@@�@@�E01�F������t�� <BR>
     *�@@�@@�@@�E09�F������t�G���[ <BR>
     *�@@�@@�@@�E03�F������t�ώ�� <BR>
     *�@@�@@�@@�E11�F������ <BR>
     *�@@�@@�@@�E19�F�����G���[ <BR>
     *�@@�@@�@@�E21�F����� <BR>
     *�@@�@@�@@�E29�F����G���[ <BR>
     *�@@�@@�@@�E31�F�o���� <BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_00790<BR>
     *<BR>
     * �P�|�S�jthis.�o�H�敪 == null�A�܂���this.�o�H�敪�ɉ��L�̍���<BR> 
     *�@@�@@�@@���ݒ肳��Ă��āA����this.��t�敪�ɍ��ڂ��ݒ肳��Ă���<BR> 
     *�@@�@@�@@�ꍇ�́A�u�o�H�敪�Ǝ�t�敪�̑g�ݍ��킹���s���v�̗�O���X���[����B<BR> 
     *�@@�@@�@@�E0�F�o���ʒm <BR>
     *�@@�@@�@@�E1�F�o������ <BR>
     *�@@�@@�@@�E2�F��茋�ʈꊇ����<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_02652<BR>
     *<BR>
     *�Q�j�@@�\�[�g�L�[�`�F�b�N<BR> 
     *�@@�Q�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ<BR> 
     *�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_00231<BR> 
     *<BR>
     *�@@�Q�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ<BR> 
     *�@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_00232<BR>
     *<BR>
     *�@@�Q�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂���<BR> 
     *�@@�@@�@@�@@���L�̃`�F�b�N���s���B<BR>
     *�@@�@@�Q�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR> 
     *<BR>
     *�@@�@@�Q�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��<BR> 
     *�@@�@@�@@�ݒ肳��Ă�����A<BR>
     *�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR> 
     *�@@�@@�@@�E�u�X�V�҃R�[�h�v<BR>
     *�@@�@@�@@�E�u�^�p�R�[�h�v<BR> 
     *�@@�@@�@@�E�u���X�R�[�h�v<BR> 
     *�@@�@@�@@�E�u�ڋq�R�[�h�v<BR> 
     *�@@�@@�@@�E�u�o�H�敪�v<BR> 
     *      �E�u��t�敪�v<BR>
     *�@@�@@�@@�E�u�����敪�v<BR> 
     *�@@�@@�@@�E�u�쐬���t�v<BR> 
     *�@@�@@�@@�E�u�X�V���t�v<BR> 
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_00086<BR>
     *<BR>
     *�R�j�v���y�[�W�ԍ��`�F�b�N<BR> 
     *�@@�R�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A<BR> 
     *�@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_00089<BR>
     *<BR>
     *�@@�R�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR> 
     *�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_00090<BR>
     *<BR>
     *�@@�R�|�R�jthis.�v���y�[�W�ԍ� �� 0�ł������ꍇ�A<BR> 
     *�@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_00616<BR>
     *<BR>
     *�S�j�y�[�W���\���s���`�F�b�N<BR> 
     *�@@�S�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A<BR> 
     *�@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_02224<BR>
     *<BR>
     *�@@�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR> 
     *�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_00092<BR> 
     *<BR>
     *�@@�S�|�R�jthis.�y�[�W���\���s�� �� 0�ł������ꍇ�A<BR> 
     *�@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException<BR>
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
     
        //�P�j�����敪�`�F�b�N 
        //this.�����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.transactionDiv != null)
        {
            // �P�|�P�jthis.�����敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A 
            //�@@�@@�u�����敪������`�̒l�v�̗�O���X���[����B 
            //�@@�@@�E0�F�����҂� 
            //�@@�@@�E1�F�����ς�
            //�@@  �E7�F��菈���� 
            //�@@�@@�E8�F�����ȗ� 
            //�@@�@@�E9�F�����G���[ 
            String l_strTodo = SleRcvdqProcStatusEnum.TODO.intValue() + "";
            String l_strProcessed = SleRcvdqProcStatusEnum.PROCESSED.intValue() + "";
            String l_strExecProcessing = SleRcvdqProcStatusEnum.EXEC_PROCESSING.intValue() + "";
            String l_strSkipProcessingIgnore = SleRcvdqProcStatusEnum.SKIP_PROCESSING_IGNORE.intValue() + "";
            String l_strSkipProcessedError = SleRcvdqProcStatusEnum.SKIP_PROCESSING_ERROR.intValue() + "";

            if ((!l_strTodo.equals(this.transactionDiv))
                &&(!l_strProcessed.equals(this.transactionDiv))
                &&(!l_strExecProcessing.equals(this.transactionDiv))
                &&(!l_strSkipProcessingIgnore.equals(this.transactionDiv))
                &&(!l_strSkipProcessedError.equals(this.transactionDiv)))
            {  
                log.debug("�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                   this.getClass().getName() + STR_METHOD_NAME,
                    "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B" + this.transactionDiv);
            }
        }
        
        //�P�|�Q�jthis.�o�H�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A 
        //�@@�@@�u�o�H�敪������`�̒l�v�̗�O���X���[����B 
        //�@@�@@�E0�F�o���ʒm 
        //�@@�@@�E1�F�o������ 
        //�@@�@@�E2�F��茋�ʈꊇ���� 
        //�@@�@@�E3�F������t 
        //�@@�@@�E4�F������t����F�� 
        //�@@�@@�E5�F������t���ʈꊇ����
        if(this.orderRootDiv != null)
        {    
            if ((!WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(this.orderRootDiv))
                &&(!WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(this.orderRootDiv))
                &&(!WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(this.orderRootDiv))
                &&(!WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT.equals(this.orderRootDiv))
                &&(!WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_CANCEL_AUTHENTICATE.equals(this.orderRootDiv))
                &&(!WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_RESULT_UPLOAD.equals(this.orderRootDiv)))
            {
                log.debug("�o�H�敪������`�̒l�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02651,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�o�H�敪������`�̒l�B" + this.orderRootDiv);
            }
        }
        
        //�P�|�R�jthis.��t�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A 
        //�@@�@@�u��t�敪������`�̒l�v�̗�O���X���[����B 
        //�@@  �E01�F������t�� 
        //�@@�@@�E09�F������t�G���[ 
        //�@@�@@�E03�F������t�ώ�� 
        //�@@�@@�E11�F������ 
        //�@@�@@�E19�F�����G���[ 
        //�@@�@@�E21�F����� 
        //�@@�@@�E29�F����G���[ 
        //�@@�@@�E31�F�o���� 
        if(this.acceptDiv != null)
        {
            if ((!WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.CHANGED.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.CANCEL.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(this.acceptDiv)))
            {
                log.debug("������t�敪�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00790,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "������t�敪�����݂��Ȃ��R�[�h�l�ł��B" + this.acceptDiv);
            }
        }

        //�P�|�S�jthis.�o�H�敪 == null�A�܂���this.�o�H�敪�ɉ��L�̍��� 
        //�@@�@@���ݒ肳��Ă��āA����this.��t�敪�ɍ��ڂ��ݒ肳��Ă��� 
        //�@@�@@�ꍇ�́A�u�o�H�敪�Ǝ�t�敪�̑g�ݍ��킹���s���v�̗�O���X���[����B 
        //�@@�@@�E0�F�o���ʒm 
        //�@@�@@�E1�F�o������ 
        //�@@�@@�E2�F��茋�ʈꊇ���� 
        if ((this.orderRootDiv == null || 
            WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(this.orderRootDiv) 
            ||WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(this.orderRootDiv)
            ||WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(this.orderRootDiv))
            && this.acceptDiv != null)
        {
            log.debug("�o�H�敪�Ǝ�t�敪�̑g�ݍ��킹���s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02652,
                this.getClass().getName() + STR_METHOD_NAME,
                "�o�H�敪�Ǝ�t�敪�̑g�ݍ��킹���s��");
        }
        
        //�Q�j�@@�\�[�g�L�[�`�F�b�N 
        //�Q�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ 
        //�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[��null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[��null." + this.sortKeys);
        }
        
        //�Q�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ 
        //�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[.�v�f����0�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[.�v�f����0�B" + this.sortKeys.length);
        }  
        
        //�Q�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��� 
        //�@@�@@�@@�@@���L�̃`�F�b�N���s���B 
        //  �@@�@@�Q�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B 
        //
        //    �@@�Q�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO�� 
        //  �@@�@@�ݒ肳��Ă�����A 
        //   �@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B 
        //  �@@�@@�@@�E�u�X�V�҃R�[�h�v 
        //  �@@�@@�@@�E�u�^�p�R�[�h�v 
        //  �@@�@@�@@�E�u���X�R�[�h�v 
        //  �@@�@@�@@�E�u�ڋq�R�[�h�v 
        //  �@@�@@�@@�E�u�o�H�敪�v
        //        �E�u��t�敪�v 
        //  �@@�@@�@@�E�u�����敪�v 
        //        �E�u�쐬���t�v 
        //    �@@�@@�E�u�X�V���t�v
        int l_intSortKeysLength = 0;
 
        l_intSortKeysLength = this.sortKeys.length;
        
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            this.sortKeys[i].validate();
            if ((!WEB3FeqSortKeyItemNameDef.UPDATER_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.ORDER_ROOT_DIV.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.ACCEPT_DIV.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.TRANSACTION_DIV.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.CREATED_TIMESTAMP.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.LAST_UPDATED_TIMESTAMP.equals(this.sortKeys[i].keyItem)))
            {
                log.debug("�\�[�g�L�[.�L�[���ڂ�����`�̒l�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[.�L�[���ڂ�����`�̒l�B" + this.sortKeys[i].keyItem);
            }
        }
        
        //�R�j�v���y�[�W�ԍ��`�F�b�N 
        //�R�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A 
        //�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B" + this.pageIndex);       
        }
        
        //�R�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A 
        //�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B 
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�B" + this.pageIndex);      
        }
        
        //�R�|�R�jthis.�v���y�[�W�ԍ� �� 0�ł������ꍇ�A 
        //�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ���0�ȉ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ���0�ȉ��B" + this.pageIndex);      
        }
        
        //�S�j�y�[�W���\���s���`�F�b�N 
        //�S�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A 
        //�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B" + this.pageSize);      
        }
        
        //�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A 
        //�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�B" + this.pageSize);      
        }
        
        //�S�|�R�jthis.�y�[�W���\���s�� �� 0�ł������ꍇ�A 
        //�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s����0�ȉ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s����0�ȉ��B" + this.pageSize);      
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
        return new WEB3AdminFeqRcvdQueueReferenceResponse(this);
    }
    
}
@
