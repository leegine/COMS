head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwTargetListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�抷������ꗗ���N�G�X�g�N���X(WEB3MutualSwTargetListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 ��O�� (���u) �V�K�쐬                
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ���M�抷������ꗗ���N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualSwTargetListRequest extends WEB3MutualCommonRequest
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwTargetListRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sw_target_list";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200510181028L;

    /**
     * (���YID)<BR>
     * �抷���̎��YID <BR>
     */
    public String id;

    /**
     * (���M�����J�e�S���[�R�[�h)<BR>
     * ���M�����J�e�S���[�R�[�h <BR>
     * (null�̏ꍇ�͎w�薳���Ƃ���)<BR>
     */
    public String categoryCode;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ� <BR>
     * �\�����������y�[�W�ʒu���w�� <BR>
     * ���擪�y�[�W��"1"�Ƃ��� <BR>
     * <BR>
     * (���y�[�W���O���s��Ȃ��ꍇ�A"1"�Œ�Ƃ���B)<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s�� <BR>
     * 1�y�[�W���ɕ\�����������s�����w�� <BR>
     * <BR>
     * (���y�[�W���O���s��Ȃ��ꍇ�A�ő�l�ƂȂ鐔�l���w�肷��)<BR>
     */
    public String pageSize;    
    
    /**
     * (���M�抷������ꗗ���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A8A1C4018D
     */
    public WEB3MutualSwTargetListRequest()
    {

    }

    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * ���M�抷������ꗗ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8A1CE00E1
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualSwTargetListResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j���YID�`�F�b�N <BR>
     * <BR>
     * this.���YID==null�̏ꍇ�A��O���X���[����B <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00080 <BR>
     * <BR>
     * �Q�j�v���y�[�W�ԍ��`�F�b�N <BR>
     * <BR>
     * �Q�|�P�jthis.�v���y�[�W�ԍ�==null�̏ꍇ�A��O���X���[����B <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00089 <BR>
     * <BR>
     * �Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00090 <BR>
     * <BR>
     * �R�j�y�[�W���\���s���`�F�b�N <BR>
     * <BR>
     * �R�|�P�jthis.�y�[�W���\���s��==null�̏ꍇ�A��O���X���[����B <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00091 <BR>
     * <BR>
     * �R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00092 <BR>
     * <BR>
     * @@roseuid 40A8A74B0220
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���YID�`�F�b�N 
        //this.���YID==null�̏ꍇ�A��O���X���[����B
        
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("���M���Y�h�c�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���M���Y�h�c�����w��ł��B");
        }
        
        //�Q�j�v���y�[�W�ԍ��`�F�b�N 
        //�Q�|�P�jthis.�v���y�[�W�ԍ�==null�̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        //�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        
        //�R�j�y�[�W���\���s���`�F�b�N 
        //�R�|�P�jthis.�y�[�W���\���s��==null�̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091 ,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }
        
        //�R�|�Qthis.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
